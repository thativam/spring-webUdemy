package com.brainyit.rest.apirest.dto.v1;

import lombok.*;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class AccountCredentialsDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private String username;
    private String password;
    private String fullName;

    public AccountCredentialsDTO(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
