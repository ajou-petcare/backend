package swe_group_11.pethealthmanager.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import swe_group_11.pethealthmanager.DTO.HealthRecordDTO;
import swe_group_11.pethealthmanager.service.HealthRecordService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/health_records")
public class HealthRecordController {

    private HealthRecordService healthRecordService;

    @PostMapping
    public ResponseEntity<?> createHealthRecord(@RequestBody HealthRecordDTO healthRecordDTO) {
        HealthRecordDTO createdRecord = healthRecordService.createHealthRecord(healthRecordDTO);
        return ResponseEntity.ok(createdRecord);
    }


}