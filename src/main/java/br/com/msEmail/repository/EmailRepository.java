package br.com.msEmail.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.msEmail.entity.EmailEntity;

public interface EmailRepository extends JpaRepository<EmailEntity, Long> {

}
