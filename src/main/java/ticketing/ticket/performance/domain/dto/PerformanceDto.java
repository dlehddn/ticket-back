package ticketing.ticket.performance.domain.dto;

import lombok.Data;


import java.util.*;

@Data
public class PerformanceDto {
    private Long performanceId;
    private String name;
    
    private List<PerformanceDetailDto> performanceDetailDtoList = new ArrayList<>();
}
