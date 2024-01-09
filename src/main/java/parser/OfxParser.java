package parser;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import domain.OFX;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class OfxParser {

  public OFX parseFile(File ofxFile) throws IOException {
    ObjectMapper objectMapper = new XmlMapper();
    InputStream inputStream = new BufferedInputStream(new FileInputStream(ofxFile));
    OFX contents = objectMapper.readValue(inputStream, OFX.class);
    return contents;
  }

}