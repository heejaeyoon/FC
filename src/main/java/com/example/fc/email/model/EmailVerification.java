package com.example.fc.email.model;

import lombok.*;
import org.springframework.stereotype.Component;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Component
public class EmailVerification {
    private Long id;
    private String verifyingCode;
    private int verified;
    private String email;
    private String password;
}
