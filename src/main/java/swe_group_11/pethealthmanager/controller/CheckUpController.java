package swe_group_11.pethealthmanager.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import swe_group_11.pethealthmanager.DTO.HealthRecordDTO;
import swe_group_11.pethealthmanager.service.CheckUpService;
import swe_group_11.pethealthmanager.service.HealthRecordService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/checkup")
public class CheckUpController {

    private final CheckUpService checkUpService;

    @PostMapping("/skin")
    public ResponseEntity<HealthRecordDTO> performSkinCheckUp(@RequestParam("petId") Long petId, @RequestParam("base64Image") String base64Image) {
        HealthRecordDTO healthRecordDTO = checkUpService.performCheckUp(petId, base64Image, "skin");
        return ResponseEntity.ok(healthRecordDTO);
    }

    @PostMapping("/eye")
    public ResponseEntity<HealthRecordDTO> performEyeCheckUp(@RequestParam("petId") Long petId, @RequestParam("base64Image") String base64Image) {
        HealthRecordDTO healthRecordDTO = checkUpService.performCheckUp(petId, base64Image, "eye");
        return ResponseEntity.ok(healthRecordDTO);
    }

    @PostMapping("/bcs")
    public ResponseEntity<HealthRecordDTO> performBcsCheckUp(@RequestParam("petId") Long petId, @RequestParam("base64Image") String base64Image) {
        HealthRecordDTO healthRecordDTO = checkUpService.performCheckUp(petId, base64Image, "bcs");
        return ResponseEntity.ok(healthRecordDTO);
    }
}


