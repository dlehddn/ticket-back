package ticketing.ticket.performance.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ticketing.ticket.performance.domain.dto.PerfDetailResponseDto;
import ticketing.ticket.performance.domain.dto.PerfDetailSaveDto;
import ticketing.ticket.performance.service.PerfDetailService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/perform-detail")
public class PerfDetailController {

    private final PerfDetailService perfDetailService;

    @PostMapping("/save/{perfId}")
    public void creatPerfDetail(@RequestBody PerfDetailSaveDto saveDto, @PathVariable Long perfId) {
        perfDetailService.savePerfDetail(saveDto, perfId);
    }

    @GetMapping("/{perfId}")
    public PerfDetailResponseDto getPerfDetail(@PathVariable Long perfId) {
        return perfDetailService.findById(perfId);
    }

    @GetMapping()
    public List<PerfDetailResponseDto> getAllDetails() {
        return perfDetailService.findAll();
    }
}
