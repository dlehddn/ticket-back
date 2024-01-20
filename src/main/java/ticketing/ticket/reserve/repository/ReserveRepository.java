package ticketing.ticket.reserve.repository;

import ticketing.ticket.reserve.domain.dto.ReserveResponseDto;
import ticketing.ticket.reserve.domain.dto.ReserveSaveDto;

import java.util.List;

public interface ReserveRepository {

    void save(ReserveSaveDto reserveSaveDto);

    List<ReserveResponseDto> findAllByDetail(Long perfDetailId);
}
