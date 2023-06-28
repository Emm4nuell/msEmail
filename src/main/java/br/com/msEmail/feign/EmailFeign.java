package br.com.msEmail.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "msprocesso", path = "/acompanhamento-processo")
public interface EmailFeign {
	
	
	@GetMapping(params = "cpf")
	ResponseEntity<Usuario> findByCpf(@RequestParam("cpf") String cpf);

}
