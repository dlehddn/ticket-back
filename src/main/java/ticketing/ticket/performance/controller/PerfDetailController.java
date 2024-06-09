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
@RequestMapping("/perform-details")
public class PerfDetailController {

    private final PerfDetailService perfDetailService;

    @PostMapping("/{perfId}")
    public ResponseEntity<Void> creatPerfDetail(@RequestBody final PerfDetailSaveDto saveDto, @PathVariable Long perfId) {
        perfDetailService.savePerfDetail(saveDto, perfId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{perfId}")
    public ResponseEntity<PerfDetailResponseDto> getPerfDetail(@PathVariable Long perfDetailId) {
        return ResponseEntity.ok(perfDetailService.findById(perfDetailId));
    }

    @PostMapping
    public ResponseEntity<List<PerfDetailResponseDto>> getAllDetails(@RequestBody final PerfSearchDto perfSearchDto) {
        return ResponseEntity.ok(perfDetailService.findNextPage(perfSearchDto));
    }
}
