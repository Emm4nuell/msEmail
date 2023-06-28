package br.com.msEmail.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;

import br.com.msEmail.dto.EmailDto;
import br.com.msEmail.entity.EmailEntity;
import br.com.msEmail.feign.Usuario;
import br.com.msEmail.service.EmailService;

@RestController
@RequestMapping("email")
public class EmailController {
	
	@Autowired
	private EmailService emailService;
	
	
	@GetMapping()
	public String status(){
		return "Teste de email";
	}
	
	@GetMapping(params = "cpf")
	public ResponseEntity<Usuario> findByCpf(@RequestParam("cpf") String cpf) throws JsonProcessingException{
		Usuario usuario = emailService.toFeignUsuario(cpf);
		return ResponseEntity.status(HttpStatus.OK).body(usuario);
	}
	
	@PostMapping
	public ResponseEntity<EmailEntity> sendingEmail(@RequestBody @Valid EmailDto email){
		EmailEntity entity = EmailDto.toEmailEntity(email);
		emailService.sendEmail(entity);
		return ResponseEntity.status(HttpStatus.CREATED).body(entity);
	}
	

	

}
