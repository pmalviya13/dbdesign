package com.dbdesign.dto;

import lombok.Data;
import org.hibernate.annotations.GeneratorType;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

@Entity
@Data
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int code;
    @NotBlank(message = "Email is mandatory")
    private String emailId;
    private String password;
    private String passwordKey;
    private String firstName;
    private String lastName;
    @NotBlank(message = "Mobile number is mandatory")
    private String mobileNumber;
    private char status;
    private int numberOfProjects;
}
