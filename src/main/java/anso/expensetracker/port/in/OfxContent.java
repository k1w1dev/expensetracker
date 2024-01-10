package anso.expensetracker.port.in;

import anso.expensetracker.domain.OFX;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface OfxContent {

  OFX parseOfxContent(String content) throws JsonProcessingException;
}