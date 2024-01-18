package ticketing.ticket.reserve.repository;

import ticketing.ticket.reserve.domain.dto.ReserveResponseDto;

import java.util.List;

public interface ReserveRepository {

    void save(Long memberId, Long seatId, Long perfDetailId);

    List<ReserveResponseDto> findAllByDetail(Long perfDetailId);
}
