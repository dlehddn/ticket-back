package ticketing.ticket.performance.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ticketing.ticket.performance.domain.dto.PerformanceDto;
import ticketing.ticket.performance.domain.entity.Performance;
import ticketing.ticket.performance.service.PerformanceService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/perform")
public class PerformanceController {

    private final PerformanceService performanceService;

    @PostMapping("/save")
    public void creatPerformance(@RequestBody PerformanceDto performanceDto) {
        performanceService.savePerformance(performanceDto);
    }

    @GetMapping("/{performId}")
    public Performance getPerformance(@PathVariable Long performId) {
        return performanceService.findById(performId);
    }

    @GetMapping()
    public List<Performance> getAll() {
        return performanceService.findAll();
    }

}
