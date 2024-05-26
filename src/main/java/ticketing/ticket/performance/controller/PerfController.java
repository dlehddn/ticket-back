package ticketing.ticket.performance.controller;

import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ticketing.ticket.performance.domain.dto.PerfDto;
import ticketing.ticket.performance.domain.entity.Performance;
import ticketing.ticket.performance.service.PerfService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/performs")
public class PerfController {

    private final PerfService perfService;

    @PostMapping
    public ResponseEntity<Void> creatPerformance(@RequestBody final PerfDto perfDto) {
        perfService.savePerformance(perfDto);
        return ResponseEntity.ok().build();
    }

//    @GetMapping("/{performId}")
//    public Performance getPerformance(@PathVariable Long performId) {
//        return perfService.findById(performId);
//    }

    @GetMapping
    public ResponseEntity<List<Performance>> getAll() {
        return ResponseEntity.ok(perfService.findAll());
    }

}
