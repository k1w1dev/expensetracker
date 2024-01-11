package anso.expensetracker.adapter.in.ofx;

import org.junit.jupiter.api.Test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class OfxParserTest {

  public static final String FULL_CONTENT = """
          <?xml version="1.0" encoding="UTF-8" standalone="no"?>
          <?OFX OFXHEADER="200" VERSION="200" SECURITY="NONE" OLDFILEUID="NONE" NEWFILEUID="NONE"?>
          <OFX>
              <SIGNONMSGSRSV1>
                  <SONRS>
                      <STATUS>
                          <CODE>0</CODE>
                          <SEVERITY>INFO</SEVERITY>
                          <MESSAGE>OK</MESSAGE>
                      </STATUS>
                      <DTSERVER>20240107093330[+12:NZDT]</DTSERVER>
                      <LANGUAGE>ENG</LANGUAGE>
                  </SONRS>
              </SIGNONMSGSRSV1>
              <BANKMSGSRSV1>
                  <STMTTRNRS>
                      <TRNUID>0205360212562002202401070933</TRNUID>
                      <STATUS>
                          <CODE>0</CODE>
                          <SEVERITY>INFO</SEVERITY>
                          <MESSAGE>OK</MESSAGE>
                      </STATUS>
                      <STMTRS>
                          <CURDEF>NZD</CURDEF>
                          <BANKACCTFROM>
                              <BANKID>020536</BANKID>
                              <ACCTID>0212562002</ACCTID>
                              <ACCTTYPE>CHECKING</ACCTTYPE>
                          </BANKACCTFROM>
                          <BANKTRANLIST>
                              <DTSTART>20231230</DTSTART>
                              <DTEND>20240106</DTEND>
                              <STMTTRN>
                                  <TRNTYPE>CREDIT</TRNTYPE>
                                  <DTPOSTED>20231230</DTPOSTED>
                                  <TRNAMT>500.00</TRNAMT>
                                  <FITID>2533</FITID>
                                  <NAME>Incoming &amp;  EMI</NAME>
                                  <MEMO>INTERNET XFR</MEMO>
                              </STMTTRN>
                              <STMTTRN>
                                  <TRNTYPE>DEBIT</TRNTYPE>
                                  <DTPOSTED>20231230</DTPOSTED>
                                  <TRNAMT>-305.76</TRNAMT>
                                  <FITID>2534</FITID>
                                  <NAME>PAK N SAVE LOWER HUT</NAME>
                                  <MEMO>0135LOWER HUTT451836301642</MEMO>
                              </STMTTRN>
                          </BANKTRANLIST>
                          <LEDGERBAL>
                              <BALAMT>150.66</BALAMT>
                              <DTASOF>20240107</DTASOF>
                          </LEDGERBAL>
                          <AVAILBAL>
                              <BALAMT>150.66</BALAMT>
                              <DTASOF>20240107093330[+12:NZDT]</DTASOF>
                          </AVAILBAL>
                      </STMTRS>
                  </STMTTRNRS>
              </BANKMSGSRSV1>
          </OFX>
          """;
  ImportOfxParser underTest;

  @Test
  void fileContentsCanBeParsedCorrectly() throws IOException {
    var testFile = File.createTempFile("test", "ofx");
    FileWriter fileWriter = new FileWriter(testFile, true);
    BufferedWriter bw = new BufferedWriter(fileWriter);
    bw.write(FULL_CONTENT);
    bw.close();

    underTest = new ImportOfxParser();
    var result  = underTest.parseOfxFile(testFile);

    assertNotNull(result);
    var txns = result.getTransactions();
    assertEquals(2, txns.size());

    assertTrue(txns.stream().anyMatch(txn -> txn.type().getText().equals("DEBIT")));
    assertTrue(txns.stream().anyMatch(txn -> txn.date().equals(LocalDate.of(2023, 12, 30))));
    assertTrue(txns.stream().anyMatch(txn -> txn.amount().equals(new BigDecimal("305.76"))));
    assertTrue(txns.stream().anyMatch(txn -> txn.payee().equals("PAK N SAVE LOWER HUT")));

    assertTrue(txns.stream().anyMatch(txn -> txn.type().getText().equals("CREDIT")));
    assertTrue(txns.stream().anyMatch(txn -> txn.date().equals(LocalDate.of(2023, 12, 30))));
    assertTrue(txns.stream().anyMatch(txn -> txn.amount().equals(new BigDecimal("500.00"))));
    assertTrue(txns.stream().anyMatch(txn -> txn.payee().equals("Incoming &  EMI")));
  }
}