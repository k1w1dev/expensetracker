package anso.expensetracker.port.in;

import anso.expensetracker.domain.OFX;

import java.io.File;
import java.io.IOException;

public interface OfxFile {

  OFX parseOfxFile(File ofxFile) throws IOException;
}