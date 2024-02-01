package anso.expensetracker.port.in;

import com.fasterxml.jackson.core.JsonProcessingException;

public interface ImportOfxContent {

  int importOfxContent(String content) throws JsonProcessingException;
}