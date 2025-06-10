package com.brainyit.rest.apirest.dto.v1;

import lombok.*;

import java.io.Serializable;
import java.util.Date;


@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
public class TokenDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private String username;
    private Boolean authenticated;
    private Date created;
    private Date expiration;
    private String accessToken;
    private String refreshToken;



}
