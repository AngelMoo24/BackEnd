package com.irojas.demojwt.DTO;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateUserRequest {
    private String firstName;
    private String lastName;
    private String username;
    private String password;

}
