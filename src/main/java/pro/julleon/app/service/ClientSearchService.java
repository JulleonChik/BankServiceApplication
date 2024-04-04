package pro.julleon.app.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pro.julleon.app.entity.Client;
import pro.julleon.app.repository.ClientRepository;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class ClientSearchService {

    private final ClientRepository clientRepository;

    public Client searchClientByEmail(String email) {
        return clientRepository.findByEmailsContains(email)
                .orElseThrow(null);
    }

    public Client searchClientByPhone(String phone) {
        return clientRepository.findByPhonesContains(phone)
                .orElseThrow(null);
    }

    public Page<Client> searchClientsByDateOfBirthIsAfter(LocalDate dateOfBirth, Pageable pageable) {
        return clientRepository.findByDateOfBirthIsGreaterThanEqual(dateOfBirth, pageable);
    }


    public Page<Client> searchClientsByFullName(String fullNamePart, Pageable pageable) {
        return clientRepository.searchByFullName(fullNamePart, pageable);
    }
}
