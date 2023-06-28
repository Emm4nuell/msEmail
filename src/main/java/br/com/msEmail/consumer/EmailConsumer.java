package br.com.msEmail.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.msEmail.dto.EmailDto;
import br.com.msEmail.entity.EmailEntity;
import br.com.msEmail.service.EmailService;

@Component
public class EmailConsumer {
	
	@Autowired
	private EmailService emailService;
	
	@RabbitListener(queues = "${mq.queues.chat-mensagem}")
	public void listen(@Payload String request) throws JsonMappingException, JsonProcessingException {
		
		ObjectMapper mapper = new ObjectMapper();
		EmailDto email = mapper.readValue(request, EmailDto.class);
		EmailEntity entity = EmailDto.toEmailEntity(email); 
		emailService.sendEmail(entity);
	}

}
