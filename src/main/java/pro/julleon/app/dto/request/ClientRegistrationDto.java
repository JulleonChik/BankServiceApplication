package pro.julleon.app.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class ClientRegistrationDto {
    private String login;
    private String password;
    private String firstName;
    private String middleName;
    private String lastName;
    private LocalDate dateOfBirth;
    private BigDecimal initialBalance;
    private List<String> phones;
    private List<String> emails;
}
