package ticketing.ticket.member.domain.dto;

import lombok.Getter;

@Getter
public class SignUpDto {
    private String name;
    private String email;
    private String password;
}
