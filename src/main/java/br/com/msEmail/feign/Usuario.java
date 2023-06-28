package br.com.msEmail.feign;

import java.time.LocalDateTime;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {

		
		private String nome;
		private String cpf;
		private String email;
		private String dataNascimento;
		private String dataCadastro;
		private String processo;
		private String mensagem;
		private String senha;
		private String roles;
	
}
