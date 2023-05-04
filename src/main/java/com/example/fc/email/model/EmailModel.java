package com.example.fc.email.model;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class EmailModel {
    private String to;
    private String subject;
    private String message;

}
