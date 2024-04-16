package ticketing.ticket.performance.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ticketing.ticket.performance.domain.dto.PerfDetailResponseDto;
import ticketing.ticket.performance.domain.dto.PerfDetailSaveDto;
import ticketing.ticket.performance.domain.dto.PerfSearchDto;
import ticketing.ticket.performance.service.PerfDetailService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/perform-detail")
@CrossOrigin(originPatterns = "http://localhost:8080")
public class PerfDetailController {

    private final PerfDetailService perfDetailService;

    @PostMapping("/save/{perfId}")
    public void creatPerfDetail(@RequestBody PerfDetailSaveDto saveDto, @PathVariable Long perfId) {
        perfDetailService.savePerfDetail(saveDto, perfId);
    }

    @GetMapping("/{perfId}")
    public PerfDetailResponseDto getPerfDetail(@PathVariable Long perfDetailId) {
        return perfDetailService.findById(perfDetailId);
    }

    @PostMapping("/all")
    public List<PerfDetailResponseDto> getAllDetails(@RequestBody PerfSearchDto perfSearchDto) {
        System.out.println(perfSearchDto.getSize());
        return perfDetailService.findAllByPerf(perfSearchDto);
    }

}
