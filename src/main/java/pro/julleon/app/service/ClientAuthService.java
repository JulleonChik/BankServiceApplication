package pro.julleon.app.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pro.julleon.app.dto.request.ClientRegistrationDto;
import pro.julleon.app.entity.Client;
import pro.julleon.app.repository.ClientRepository;
import pro.julleon.app.security.userdetails.ClientPrinciple;

@Service
@RequiredArgsConstructor
public class ClientAuthService implements UserDetailsService {

    private final ClientRepository clientRepository;
    private final PasswordEncoder passwordEncoder;

    public void registerClient(ClientRegistrationDto registrationDto) {
        clientRepository.save(Client.builder()
                .login(registrationDto.getLogin())
                .password(passwordEncoder.encode(registrationDto.getPassword()))
                .firstName(registrationDto.getFirstName())
                .middleName(registrationDto.getMiddleName())
                .lastName(registrationDto.getLastName())
                .dateOfBirth(registrationDto.getDateOfBirth())
                .balance(registrationDto.getInitialBalance())
                .phones(registrationDto.getPhones())
                .emails(registrationDto.getEmails())
                .build());
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return clientRepository.findByLogin(username)
                .map(ClientPrinciple::new)
                .orElseThrow(() -> new UsernameNotFoundException("Client was not found by login: " + username));
//                .orElseThrow(() -> new UsernameNotFoundException("Client not found"));
    }

}
