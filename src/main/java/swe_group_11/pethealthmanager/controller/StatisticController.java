package swe_group_11.pethealthmanager.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import swe_group_11.pethealthmanager.DTO.StatisticsDTO;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/statistics")
public class StatisticController {

    @GetMapping("/statistics")
    public ResponseEntity<StatisticsDTO> getStatistics(@RequestParam String username, @RequestParam String petname) {
        StatisticsDTO statistics = new StatisticsDTO();
        return ResponseEntity.ok(statistics);
    }
}
