package com.test01.regist01.registration;

public record RegistrationRequest(
        String firstName,
        String lastName,
        String email,
        String password,
        String role) {
}
