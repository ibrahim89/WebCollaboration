package com.zeedle.controller;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import com.zeedle.model.Message;
import com.zeedle.model.OutputMessage;

@Controller
public class ChatController {
	private static final Logger logger=
			LoggerFactory.getLogger(ChatController.class);
	@MessageMapping("/chat")
	@SendTo("/topic/message")
	public OutputMessage seMessage(Message message){
		logger.debug("Calling the method snedMessage");
		logger.debug("Message:", message.getMessage());
		logger.debug("Message ID:", message.getId());
		return new OutputMessage(message, new Date());
	}

	
}
