package anso.expensetracker.adapter.in.parser;

import anso.expensetracker.port.in.OfxContent;
import anso.expensetracker.port.out.PersistencePort;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import anso.expensetracker.domain.OFX;
import anso.expensetracker.port.in.OfxFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;


@Component
class OfxParser implements OfxFile, OfxContent {

  @Autowired
  private PersistencePort persistencePort;

  @Override
  public OFX parseOfxFile(File ofxFile) throws IOException {
    ObjectMapper objectMapper = new XmlMapper();
    InputStream inputStream = new BufferedInputStream(new FileInputStream(ofxFile));
    return objectMapper.readValue(inputStream, OFX.class);
  }

  @Override
  public OFX parseOfxContent(String content) throws JsonProcessingException {
    ObjectMapper objectMapper = new XmlMapper();
    var ofx =  objectMapper.readValue(content, OFX.class);
    persistencePort.saveTransactions(ofx.getBankMsgsRsv1().getStmtTrnRs().getStmtRs().getBankTransList().getTransactions());
    return ofx;
  }
}