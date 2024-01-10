package anso.expensetracker.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;


@JsonIgnoreProperties(ignoreUnknown = true)
public class OFX {

    public BankMsgsSrv1 getBankMsgsRsv1() {
        return bankMsgsRsv1;
    }

    public void setBankMsgsRsv1(BankMsgsSrv1 bankMsgsRsv1) {
        this.bankMsgsRsv1 = bankMsgsRsv1;
    }

    @JacksonXmlProperty(localName = "BANKMSGSRSV1")
    private BankMsgsSrv1 bankMsgsRsv1;



}