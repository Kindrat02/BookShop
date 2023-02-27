package com.serve.mentorship.service.implementation;

import com.serve.mentorship.dto.AuthorDTO;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Aspect
@Component
@Profile({"prod"})
public class EmailService {

    @After("execution(* com.serve.mentorship.service.implementation.AuthorServiceImpl.addAuthor(..))")
    public void sendEmailAfterAuthorCreation(JoinPoint joinPoint) {
        AuthorDTO author = (AuthorDTO) joinPoint.getArgs()[0];
        String email = author.getEmail();

        if (email != null) {
            log.info("The letter aws successfully sent to {} author with {} email", author.getName() + author.getSurname(), author.getEmail());
        }
    }
}
