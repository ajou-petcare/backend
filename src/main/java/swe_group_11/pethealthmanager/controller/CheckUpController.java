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

    @PostMapping
    public ResponseEntity<HealthRecordDTO> performCheckUp(@RequestParam("petId") Long petId, @RequestParam("image") String base64Image) {
        HealthRecordDTO healthRecordDTO = checkUpService.performCheckUp(petId, base64Image);
        return ResponseEntity.ok(healthRecordDTO);
    }
}

