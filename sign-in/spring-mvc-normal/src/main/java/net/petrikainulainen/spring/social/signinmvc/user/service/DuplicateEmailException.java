package net.petrikainulainen.spring.social.signinmvc.user.service;


public class DuplicateEmailException extends Exception {

    public DuplicateEmailException(String message) {
        super(message);
    }
}
