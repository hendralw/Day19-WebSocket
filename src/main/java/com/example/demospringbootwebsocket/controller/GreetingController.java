package com.example.demospringbootwebsocket.controller;

import com.example.demospringbootwebsocket.dto.Greeting;
import com.example.demospringbootwebsocket.dto.Message;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

@Controller
public class GreetingController {
  @MessageMapping("/endpointPerson1") // endpoint of person 1
  @SendTo("/topic/greetingsPerson2") // server blast message ke mana
  public Greeting greeting(Message message) throws Exception {
    Thread.sleep(1000); // simulated delay
    return new Greeting(HtmlUtils.htmlEscape(message.getMessageContent()));
  }

  @MessageMapping("/endpointPerson2") // endpoint of person 2
  @SendTo("/topic/greetingsPerson1") // server blast message ke mana
  public Greeting greeting1(Message message) throws Exception {
    Thread.sleep(1000); // simulated delay
    return new Greeting(HtmlUtils.htmlEscape(message.getMessageContent()));
  }

  @MessageMapping("/endpointBroadcast") // endpoint of broadcast
  @SendTo("/topic/greetingsBroadcast") // server blast message ke mana
  public Greeting broadcast(Message message) throws Exception {
    Thread.sleep(1000); // simulated delay
    return new Greeting(HtmlUtils.htmlEscape(message.getMessageContent()));
  }
}
