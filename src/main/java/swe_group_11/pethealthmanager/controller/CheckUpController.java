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


    private CheckUpService checkUpService;

    @PostMapping("/api/checkup/skin")
    public ResponseEntity<HealthRecordDTO> performCheckUpEye(@RequestParam("petId") Long petId, @RequestParam("image") String base64Image) {
        HealthRecordDTO healthRecordDTO = checkUpService.performCheckUpSkin(petId, base64Image);
        return ResponseEntity.ok(healthRecordDTO);
    }

    @PostMapping("/api/checkup/eye")
    public ResponseEntity<HealthRecordDTO> performCheckUpSkin(@RequestParam("petId") Long petId, @RequestParam("image") String base64Image) {
        HealthRecordDTO healthRecordDTO = checkUpService.performCheckUpEye(petId, base64Image);
        return ResponseEntity.ok(healthRecordDTO);
    }

    @PostMapping("/api/checkup/bcs")
    public ResponseEntity<HealthRecordDTO> performCheckUpBcs(@RequestParam("petId") Long petId, @RequestParam("image") String base64Image) {
        HealthRecordDTO healthRecordDTO = checkUpService.performCheckUpBcs(petId, base64Image);
        return ResponseEntity.ok(healthRecordDTO);
    }


}

