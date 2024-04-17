package ticketing.ticket.member.domain.dto;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import ticketing.ticket.member.domain.entity.Member;

import java.util.Collection;

@Getter
public class LogInMemberDto extends User {
    private final Member member;

    public LogInMemberDto(Member member, Collection<? extends GrantedAuthority> authorities) {
        super(member.getUsername(), member.getPassword(), authorities);
        this.member = member;
    }
}
