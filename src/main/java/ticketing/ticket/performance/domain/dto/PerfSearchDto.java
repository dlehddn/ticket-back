package ticketing.ticket.performance.domain.dto;


import lombok.Getter;

@Getter
public class PerfSearchDto {
    private Long perfId;
    private String title;
    private String button;
    private Long index;
    private Integer size;
}
