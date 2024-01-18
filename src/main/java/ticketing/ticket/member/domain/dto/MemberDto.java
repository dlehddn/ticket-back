package ticketing.ticket.member.domain.dto;

import lombok.Data;

@Data
public class MemberDto {
    private String name;
    private String email;
    private String password;

    public MemberDto(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }
}
