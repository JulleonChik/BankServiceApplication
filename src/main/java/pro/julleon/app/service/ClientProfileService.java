package pro.julleon.app.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pro.julleon.app.entity.Client;
import pro.julleon.app.repository.ClientRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientProfileService {

    private final ClientRepository clientRepository;

    public void addPhone(Client client, String phone) {
        client.getPhones().add(phone);
        clientRepository.save(client);
    }

    public void deletePhone(Client client, String phone) {
        client.getPhones().remove(phone);
        clientRepository.save(client);
    }

    public void replacePhone(Client client, String existingPhone, String updatedPhone) {
        List<String> clientPhones = client.getPhones();
        if (clientPhones.contains(existingPhone)) {
            clientPhones.set(clientPhones.indexOf(existingPhone), updatedPhone);
        }
        clientRepository.save(client);
    }

    public void addEmail(Client client, String email) {
        client.getEmails().add(email);
        clientRepository.save(client);
    }

    public void replaceEmail(Client client, String existingEmail, String updatedEmail) {
        List<String> clientEmails = client.getEmails();
        if (clientEmails.contains(existingEmail)) {
            clientEmails.set(clientEmails.indexOf(existingEmail), updatedEmail);
        }
        clientRepository.save(client);
    }

    public void deleteEmail(Client client, String email) {
        client.getEmails().remove(email);
        clientRepository.save(client);
    }
}
