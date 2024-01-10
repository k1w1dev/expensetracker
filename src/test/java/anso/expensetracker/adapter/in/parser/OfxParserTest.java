package anso.expensetracker.adapter.in.parser;

import org.junit.jupiter.api.Test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

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
  OfxParser underTest;

  @Test
  void fileContentsCanBeParsedCorrectly() throws IOException {
    var testFile = File.createTempFile("test", "ofx");
    FileWriter fileWriter = new FileWriter(testFile, true);
    BufferedWriter bw = new BufferedWriter(fileWriter);
    bw.write(FULL_CONTENT);
    bw.close();

    underTest = new OfxParser();
    var result  = underTest.parseOfxFile(testFile);

    assertNotNull(result);
    assertEquals(2, result.getBankMsgsRsv1().getStmtTrnRs().getStmtRs().getBankTransList().getTransactions().size());

    var txns = result.getBankMsgsRsv1().getStmtTrnRs().getStmtRs().getBankTransList().getTransactions();

    assertTrue(txns.stream().anyMatch(txn -> txn.getType().equals("DEBIT")));
    assertTrue(txns.stream().anyMatch(txn -> txn.getDate().equals("20231230")));
    assertTrue(txns.stream().anyMatch(txn -> txn.getAmount().equals("-305.76")));
    assertTrue(txns.stream().anyMatch(txn -> txn.getName().equals("PAK N SAVE LOWER HUT")));

    assertTrue(txns.stream().anyMatch(txn -> txn.getType().equals("CREDIT")));
    assertTrue(txns.stream().anyMatch(txn -> txn.getDate().equals("20231230")));
    assertTrue(txns.stream().anyMatch(txn -> txn.getAmount().equals("500.00")));
    assertTrue(txns.stream().anyMatch(txn -> txn.getName().equals("Incoming &  EMI")));
  }
}