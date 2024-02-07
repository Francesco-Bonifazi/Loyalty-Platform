package it.unicam.ids.loyaltyplatform.auth;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class AuthenticationRequest {

    private String email;
    private String password;


    public String getEmail() {
        return email;
    }
    public String getPassword() {
        return password;
    }

}
