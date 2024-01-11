package anso.expensetracker.adapter.in.ofx;

import anso.expensetracker.port.in.OfxFile;
import anso.expensetracker.port.out.PersistencePort;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;


@Component
class ImportOfxParser implements OfxFile{

  @Autowired
  private PersistencePort persistencePort;

  @Override
  public OFX parseOfxFile(File ofxFile) throws IOException {
    ObjectMapper objectMapper = new XmlMapper();
    InputStream inputStream = new BufferedInputStream(new FileInputStream(ofxFile));
    return objectMapper.readValue(inputStream, OFX.class);
  }
}