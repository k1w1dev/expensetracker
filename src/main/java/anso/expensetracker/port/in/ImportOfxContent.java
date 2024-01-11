package anso.expensetracker.port.in;

import anso.expensetracker.adapter.in.ofx.OFX;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface ImportOfxContent {

  OFX importOfxContent(String content) throws JsonProcessingException;
}