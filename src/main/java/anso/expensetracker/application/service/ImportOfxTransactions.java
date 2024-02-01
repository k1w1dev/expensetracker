package anso.expensetracker.application.service;

import anso.expensetracker.adapter.in.ofx.OFX;
import anso.expensetracker.port.in.ImportOfxContent;
import anso.expensetracker.port.out.PersistencePort;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ImportOfxTransactions implements ImportOfxContent {

  @Autowired
  private final PersistencePort persistencePort;

  public ImportOfxTransactions(PersistencePort persistencePort) {
    this.persistencePort = persistencePort;
  }

  @Override
  public int importOfxContent(String content) throws JsonProcessingException {
    ObjectMapper objectMapper = new XmlMapper();
    var ofx =  objectMapper.readValue(content, OFX.class);

    var bankAccount = ofx.getBankAccount();
    final var bankId = persistencePort.saveBankAccount(bankAccount);

    var statementTransactions = ofx.getTransactions();
    return persistencePort.saveTransactions(statementTransactions, bankId);
  }

}