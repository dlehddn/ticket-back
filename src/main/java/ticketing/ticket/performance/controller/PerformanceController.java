package ticketing.ticket.performance.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.service.annotation.GetExchange;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import ticketing.ticket.performance.domain.dto.PerformanceDetailDto;
import ticketing.ticket.performance.domain.dto.PerformanceDto;
import ticketing.ticket.performance.service.PerformanceDetailService;
import ticketing.ticket.performance.service.PerformanceService;
import ticketing.ticket.performance.service.impl.PerformanceDetailServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
@RequestMapping("/perform")
@CrossOrigin(origins = "*")
public class PerformanceController {
    private final PerformanceService performanceService;
    private final PerformanceDetailService performanceDetailService;

    @Autowired
    public PerformanceController(PerformanceService performanceService, PerformanceDetailService performanceDetailService){
        this.performanceService = performanceService;
        this.performanceDetailService = performanceDetailService;
    }
    ///////////////////////////////////// 공연 카테고리 ////////////////////////////////////
    // 공연 카테고리 저장
    @PostMapping("/save")
    public void setPerformance(@RequestBody PerformanceDto performanceDto){
        
        performanceService.setPerformance(performanceDto);
    }


    // 단일 공연 카테고리 조회
    @GetMapping("/get-performance/{performanceId}")
    public PerformanceDto getPerformance(@PathVariable Long performanceId){
        return performanceService.getPerformance(performanceId);
    }
    // 모든 공연 카테고리 조회
    @GetMapping("/all")
    public List<PerformanceDto> getAllPerformance() {
       List<PerformanceDto> dtoList = performanceService.getAllPerformance();
       
        return dtoList;

    }
    // 공연 카테고리 삭제
    @DeleteMapping("/delete-performance/{performanceId}")
    public void deletePerformance(@PathVariable Long performanceId){
        performanceService.deletePerformance(performanceId);
    }

    ///////////////////////////////////공연 디테일 /////////////////////////////////////


    // 공연 디테일 저장
    @PostMapping("/set-performancedetail")
    public void setPerformanceDetail(@RequestBody PerformanceDetailDto performanceDetailDto){
        performanceDetailService.setPerformanceDetail(performanceDetailDto);
    }

    // 공연 디테일 단건조회
    @GetMapping("/get-performancedetail/{performanceDetailId}")
    public PerformanceDetailDto gPerformanceDetail(@PathVariable Long performanceDetailId){
        return performanceDetailService.getPerformanceDetail(performanceDetailId);
    }
    
    // 공연 디테일 모두 조회
    @GetMapping("/get-allperformancedetail")
    public List<PerformanceDetailDto> getAllPerformanceDetail(){
        return performanceDetailService.getAllPerformanceDetail();
    }
    // 공연 카테고리로 디테일 조회
    @GetMapping("/get-performancedetail-by-performanceid/{performanceId}")
    public List<PerformanceDetailDto> getPerformanceDetailByPerformanceId(@PathVariable Long performanceId){
        return performanceDetailService.getPerformanceDetailByPerformanceId(performanceId);
    }

    
    // 공연 디테일 삭제
    @DeleteMapping("/delete-performancedetailId/{performanceDetailId}")
    public void deletePerformanceDetail(@PathVariable Long PerformanceDetailId){
        performanceDetailService.deletePerformanceDetail(PerformanceDetailId);
    }

}
