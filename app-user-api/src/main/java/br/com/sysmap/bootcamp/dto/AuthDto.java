package br.com.sysmap.bootcamp.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class AuthDto {

    private String email;
    private String password;
    private Long id;
    private String token;
    private String username;

    public AuthDto(String email, String password, Long id, String token, String username) {
        this.email = email;
        this.password = password;
        this.id = id;
        this.token = token;
        this.username = username;
    }

    public AuthDto() {

    }
}
