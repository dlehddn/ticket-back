package ticketing.ticket.performance.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ticketing.ticket.performance.domain.dto.PerfDto;
import ticketing.ticket.performance.domain.entity.Performance;
import ticketing.ticket.performance.service.PerfService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/perform")
public class PerfController {

    private final PerfService perfService;

    @PostMapping("/save")
    public void creatPerformance(@RequestBody PerfDto perfDto) {
        perfService.savePerformance(perfDto);
    }

    @GetMapping("/{performId}")
    public Performance getPerformance(@PathVariable Long performId) {
        return perfService.findById(performId);
    }

    @GetMapping()
    public List<Performance> getAll() {
        return perfService.findAll();
    }

}
