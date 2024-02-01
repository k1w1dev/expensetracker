package anso.expensetracker.adapter.in.web.controller;

import anso.expensetracker.port.in.ImportOfxContent;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApplicationController {

  @Autowired
  private ImportOfxContent importOfxContent;

  @PostMapping(value = "/import")
  public String importOfxContent(@RequestBody String contents) throws JsonProcessingException {
    var importedTransactions = importOfxContent.importOfxContent(contents);
    return "Loaded %d transactions".formatted(importedTransactions);
  }
}