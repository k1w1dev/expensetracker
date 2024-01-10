package anso.expensetracker.adapter.in.web.controller;

import anso.expensetracker.port.in.OfxContent;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApplicationController {

  @Autowired
  private OfxContent ofxContent;

  @PostMapping(value = "/import")
  public void importOfxContent(@RequestBody String contents) throws JsonProcessingException {
    ofxContent.parseOfxContent(contents);
  }
}