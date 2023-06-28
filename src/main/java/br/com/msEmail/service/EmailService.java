package br.com.msEmail.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.msEmail.dto.EmailDto;
import br.com.msEmail.entity.EmailEntity;
import br.com.msEmail.enuns.StatusEmail;
import br.com.msEmail.feign.EmailFeign;
import br.com.msEmail.feign.Usuario;
import br.com.msEmail.repository.EmailRepository;

@Service
public class EmailService {

	@Autowired
	private EmailRepository emailRepository;
	
	@Autowired
	private EmailFeign emailFeign;

	@Autowired
	private JavaMailSender mailSender;

	public EmailEntity save(EmailEntity email) {
		return emailRepository.save(email);
	}

	public EmailEntity sendEmail(EmailEntity entity) {

		entity.setSendDateEmail(LocalDateTime.now());

		try {
			SimpleMailMessage message = new SimpleMailMessage();
			message.setFrom(entity.getEmailFrom());
			message.setTo(entity.getEmailTo());
			message.setSubject(entity.getSubject());
			message.setText(entity.getText());
			mailSender.send(message);
			save(entity);

			entity.setStatusEmail(StatusEmail.SENT);

		} catch (MailException e) {
			entity.setStatusEmail(StatusEmail.ERROR);
		}
		return null;
	}
	
	public Usuario toFeignUsuario(String cpf) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		ResponseEntity<Usuario> usuario = emailFeign.findByCpf(cpf);
//		Usuario user = mapper.convertValue(usuario, Usuario.class);
		Usuario user = mapper.convertValue(usuario.getBody(), Usuario.class);
		System.err.println(user);
		return user;
	}

}
