package swe_group_11.pethealthmanager.service;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.Date;

import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import swe_group_11.pethealthmanager.DTO.HealthRecordDTO;
import swe_group_11.pethealthmanager.model.HealthRecord;
import swe_group_11.pethealthmanager.model.Pet;
import swe_group_11.pethealthmanager.repository.HealthRecordRepository;
import swe_group_11.pethealthmanager.repository.PetRepository;

@Service
@RequiredArgsConstructor
public class CheckUpService {


    private HealthRecordRepository healthRecordRepository;


    private PetRepository petRepository;

    private RestTemplate restTemplate = new RestTemplate();

    //
    public HealthRecordDTO performCheckUpEye(Long petId, String base64Image) {
        byte[] decodedImage = decodeImage(base64Image);
        String diagnosis = sendImageToFlaskAPI(decodedImage);

        Pet pet = petRepository.findById(petId).orElseThrow(() -> new RuntimeException("Pet not found"));
        HealthRecord healthRecord = new HealthRecord();
        healthRecord.setPet(pet);
        healthRecord.setRecordDate(new Date());
        healthRecord.setDiagnosis(diagnosis);

        HealthRecord savedRecord = healthRecordRepository.save(healthRecord);
        return convertToDTO(savedRecord);
    }

    public HealthRecordDTO performCheckUpSkin(Long petId, String base64Image) {
        byte[] decodedImage = decodeImage(base64Image);
        String diagnosis = sendImageToFlaskAPI(decodedImage);

        Pet pet = petRepository.findById(petId).orElseThrow(() -> new RuntimeException("Pet not found"));
        HealthRecord healthRecord = new HealthRecord();
        healthRecord.setPet(pet);
        healthRecord.setRecordDate(new Date());
        healthRecord.setDiagnosis(diagnosis);

        HealthRecord savedRecord = healthRecordRepository.save(healthRecord);
        return convertToDTO(savedRecord);
    }

    public HealthRecordDTO performCheckUpBcs(Long petId, String base64Image) {
        byte[] decodedImage = decodeImage(base64Image);
        String diagnosis = sendImageToFlaskAPI(decodedImage);

        Pet pet = petRepository.findById(petId).orElseThrow(() -> new RuntimeException("Pet not found"));
        HealthRecord healthRecord = new HealthRecord();
        healthRecord.setPet(pet);
        healthRecord.setRecordDate(new Date());
        healthRecord.setDiagnosis(diagnosis);

        HealthRecord savedRecord = healthRecordRepository.save(healthRecord);
        return convertToDTO(savedRecord);
    }
    private byte[] decodeImage(String base64Image) {
        return Base64.getDecoder().decode(base64Image);
    }

    //image 전달
    private String sendImageToFlaskAPI(byte[] image) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("image", new ByteArrayResource(image) {
            @Override
            public String getFilename() {
                return "image.png";
            }
        });

        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);
        String flaskApiUrl = ""; // flaskAPI 주소 만들면 넣기
        ResponseEntity<String> response = restTemplate.postForEntity(flaskApiUrl, requestEntity, String.class);
        return response.getBody();
    }

    private HealthRecordDTO convertToDTO(HealthRecord healthRecord) {
        HealthRecordDTO healthRecordDTO = new HealthRecordDTO();
        healthRecordDTO.setId(healthRecord.getId());
        healthRecordDTO.setPetid(healthRecord.getPet().getId()); // HealthRecord 엔티티에서 Pet ID 추출
        healthRecordDTO.setRecordDate(healthRecord.getRecordDate());
        healthRecordDTO.setDiagnosis(healthRecord.getDiagnosis());
        return healthRecordDTO;
    }
}

