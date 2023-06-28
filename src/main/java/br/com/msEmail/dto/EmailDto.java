package br.com.msEmail.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import br.com.msEmail.entity.EmailEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmailDto {

	@NotBlank
	private String ownerRef;
	@NotBlank
	@Email
	private String emailFrom;
	@NotBlank
	@Email
	private String emailTo;
	@NotBlank
	private String subject;
	@NotBlank
	private String text;
	
	
	public static EmailDto toEmailDto(EmailEntity email) {
		EmailDto dto = new EmailDto(
				email.getOwnerRef(), 
				email.getEmailFrom(), 
				email.getEmailTo(), 
				email.getSubject(), 
				email.getText());
		return dto;
	}
	
	public static EmailEntity toEmailEntity(EmailDto dto) {
		EmailEntity email = new EmailEntity(
				null, 
				dto.ownerRef, 
				dto.emailFrom, 
				dto.emailTo, 
				dto.subject, 
				dto.text, 
				null, 
				null);
		return email;
	}

}
