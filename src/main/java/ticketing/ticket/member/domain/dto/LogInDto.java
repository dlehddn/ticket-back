package ticketing.ticket.member.domain.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LogInDto {
    private String email;
    private String password;
}
