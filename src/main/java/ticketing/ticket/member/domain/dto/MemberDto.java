package ticketing.ticket.member.domain.dto;

import lombok.Data;

@Data
public class MemberDto {
    private Long memberId;

    private String name;
    private String email;
    private String password;
}
