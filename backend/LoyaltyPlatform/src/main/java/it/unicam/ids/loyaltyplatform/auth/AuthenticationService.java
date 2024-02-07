package it.unicam.ids.loyaltyplatform.auth;


import it.unicam.ids.loyaltyplatform.account.AccountService;
import it.unicam.ids.loyaltyplatform.account.models.Account;
import it.unicam.ids.loyaltyplatform.config.JWTService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final JWTService jwtService;
    private final AuthenticationManager authenticationManager;
    private final AccountService accountService;

    public String authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        Account account = accountService.findAccount(request.getEmail());
        return jwtService.generateToken(account);
    }
}
