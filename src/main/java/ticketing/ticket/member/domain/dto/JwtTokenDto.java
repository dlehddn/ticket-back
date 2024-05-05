package ticketing.ticket.member.domain.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class JwtTokenDto {
    private String grantType;
    private String accessToken;

    @Builder
    public JwtTokenDto(String grantType, String accessToken) {
        this.grantType = grantType;
        this.accessToken = accessToken;
    }
}
