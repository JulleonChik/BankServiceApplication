package pro.julleon.app.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String login;
    private String password;
    private String firstName;
    private String middleName;
    private String lastName;
    private LocalDate dateOfBirth;
    private BigDecimal balance;
    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> phones;
    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> emails;
}
