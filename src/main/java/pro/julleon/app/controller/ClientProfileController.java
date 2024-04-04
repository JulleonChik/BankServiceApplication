package pro.julleon.app.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import pro.julleon.app.entity.Client;
import pro.julleon.app.security.userdetails.ClientPrinciple;
import pro.julleon.app.service.ClientProfileService;


//4.	Пользователь может добавить/сменить свои номер телефона и/или email,
// если они еще не заняты другими пользователями.
//5.	Пользователь может удалить свои телефон и/или email. При этом нельзя удалить последний.
@RestController
@RequestMapping("/api/profile")
@RequiredArgsConstructor
public class ClientProfileController {

    private final ClientProfileService clientProfileService;

    @ModelAttribute("currentClient")
    public Client getCurrentClient(JwtAuthenticationToken jwtAuthenticationToken) {
        return ((ClientPrinciple) jwtAuthenticationToken.getPrincipal()).getClient();
    }

//    @ModelAttribute("currentClient")
//    public Client getCurrentClient(UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken) {
//        return ((ClientPrinciple) usernamePasswordAuthenticationToken.getPrincipal()).getClient();
//    }


    @GetMapping
    public Client getClient(
            @ModelAttribute("currentClient") Client client
    ) {
        return client;
    }


    @PostMapping("/phones/add")
    public ResponseEntity<String> addPhone(
            @ModelAttribute("currentClient") Client client,
            @RequestParam String phone
    ) {
        clientProfileService.addPhone(client, phone);
        return ResponseEntity.ok("The phone was successfully added");
    }


    @PutMapping("/phones/replace")
    public ResponseEntity<String> replacePhone(
            @ModelAttribute("currentClient") Client client,
            @RequestParam String oldPhoneNumber,
            @RequestParam String newPhoneNumber
    ) {
        clientProfileService
                .replacePhone(client, oldPhoneNumber, newPhoneNumber);
        return ResponseEntity.ok("The phone was successfully replaced");
    }


    @DeleteMapping("/phones/delete")
    public ResponseEntity<String> deletePhone(
            @ModelAttribute("currentClient") Client client,
            @RequestParam String phone
    ) {
        clientProfileService.deletePhone(client, phone);
        return ResponseEntity.ok("The phone was successfully deleted");
    }


    @PostMapping("/emails/add")
    public ResponseEntity<String> addEmail(
            @ModelAttribute("currentClient") Client client,
            @RequestBody String email
    ) {
        clientProfileService.addEmail(client, email);
        return ResponseEntity.ok("The email was successfully added");
    }


    @PutMapping("/emails/replace")
    public ResponseEntity<String> replaceEmail(
            @ModelAttribute("currentClient") Client client,
            @RequestParam String oldEmailAddress,
            @RequestParam String newEmailAddress
    ) {
        clientProfileService.replaceEmail(client, oldEmailAddress, newEmailAddress);
        return ResponseEntity.ok("The email was successfully replaced");
    }


    @DeleteMapping("/emails/delete")
    public ResponseEntity<String> deleteEmail(
            @ModelAttribute("currentClient") Client client,
            @RequestBody String email
    ) {
        clientProfileService.deleteEmail(client, email);
        return ResponseEntity.ok("The email was successfully deleted");
    }


    @DeleteMapping("/{type}/delete")
    public ResponseEntity<String> deleteEmail(
            @ModelAttribute("currentClient") Client client,
            @PathVariable("type") String type,
            @RequestBody String editData
    ) {
        return switch (type) {
            case "email" -> {
                clientProfileService.deleteEmail(client, editData);
                yield ResponseEntity.ok("The email was successfully deleted");
            }
            case "phone" -> {
                clientProfileService.deletePhone(client, editData);
                yield ResponseEntity.ok("The phone was successfully deleted");
            }
            default ->  ResponseEntity.badRequest().body("Unsupported operation type");
        };
    }

//    @GetMapping
//    public Client getClient(UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken) {
//        return ((Client) usernamePasswordAuthenticationToken.getPrincipal());
//    }

}
