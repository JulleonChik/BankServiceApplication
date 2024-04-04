package pro.julleon.app.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pro.julleon.app.dto.request.ClientRegistrationDto;
import pro.julleon.app.dto.responce.ClientAuthenticationDto;
import pro.julleon.app.security.provider.JwtProvider;
import pro.julleon.app.service.ClientAuthService;

@Slf4j
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class ClientAuthController {

    private final JwtProvider jwtProvider;
    private final ClientAuthService clientAuthService;

    @PostMapping("/register")
    public ResponseEntity<?> register(
            @RequestBody ClientRegistrationDto registrationDto
    ) {
        clientAuthService.registerClient(registrationDto);
        return ResponseEntity.ok("Registration successful");
    }

    @PostMapping("/authenticate")
    public ResponseEntity<ClientAuthenticationDto> authenticate(
            Authentication authentication
    ) {
        log.debug("Authentication client: {}", authentication.getName());
        String token = jwtProvider.createToken(authentication);
//        String token = clientAuthService.authenticateClient(authentication);
        return ResponseEntity.ok(new ClientAuthenticationDto(token));
    }
}
