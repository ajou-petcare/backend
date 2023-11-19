package swe_group_11.pethealthmanager.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import swe_group_11.pethealthmanager.service.CheckUpService;
import swe_group_11.pethealthmanager.service.HealthRecordService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/checkup")
public class CheckUpController {


    private CheckUpService checkUpService;


    private HealthRecordService healthRecordService;

    @PostMapping("/diagnose")
    public ResponseEntity<String> uploadImageForDiagnosis(@RequestParam("file") MultipartFile file) {
        try {
            String diagnosisResult = checkUpService.diagnose(file);
            // 진단 결과를 HealthRecord에 저장하는 로직
            healthRecordService.saveDiagnosisResult(diagnosisResult);
            return ResponseEntity.ok(diagnosisResult);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("진단 처리 중 오류 발생");
        }
    }
}

