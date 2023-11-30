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
import swe_group_11.pethealthmanager.DTO.ImageDTO;
import swe_group_11.pethealthmanager.model.HealthRecord;
import swe_group_11.pethealthmanager.model.Pet;
import swe_group_11.pethealthmanager.repository.HealthRecordRepository;
import swe_group_11.pethealthmanager.repository.PetRepository;

@Service
@RequiredArgsConstructor
public class CheckUpService {

    private final HealthRecordRepository healthRecordRepository;
    private final PetRepository petRepository;
    private RestTemplate restTemplate = new RestTemplate();

    public HealthRecordDTO performCheckUp(ImageDTO imageDTO, String checkUpType) {
        Pet pet = petRepository.findByPetName(imageDTO.getPetName()).orElseThrow(() -> new RuntimeException("Pet not found"));
        String animalType = pet.getSpecies(); // 'dog' 또는 'cat'
        byte[] decodedImage = decodeImage(imageDTO.getBase64Image());
        String diagnosis = sendImageToModelAPI(decodedImage, animalType, checkUpType);

        return createHealthRecord(imageDTO.getPetName(), diagnosis);
    }

    private byte[] decodeImage(String base64Image) {
        return Base64.getDecoder().decode(base64Image);
    }

    private String sendImageToModelAPI(byte[] image, String animalType, String modelType) {
        RestTemplate restTemplate = new RestTemplate();

        // Flask API URL 설정 (예시 URL)
        String flaskApiUrl = String.format("http://localhost:5000/api/diagnose/%s/%s", animalType, modelType);

        // HTTP 헤더 설정
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        // 멀티파트 요청 바디 생성
        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("image", new ByteArrayResource(image) {
            @Override
            public String getFilename() {
                return "image.png"; // 임의의 파일 이름
            }
        });

        // HTTP 요청 생성
        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);

        // Flask API에 요청 보내기
        ResponseEntity<String> response = restTemplate.postForEntity(flaskApiUrl, requestEntity, String.class);

        // Flask API 응답 반환
        return response.getBody();
    }

    private HealthRecordDTO createHealthRecord(String petId, String diagnosis) {
        Pet pet = petRepository.findByPetName(petId).orElseThrow(() -> new RuntimeException("Pet not found"));
        HealthRecord healthRecord = new HealthRecord();
        healthRecord.setPet(pet);
        healthRecord.setRecordDate(new Date());
        healthRecord.setDiagnosis(diagnosis);

        HealthRecord savedRecord = healthRecordRepository.save(healthRecord);
        return convertToDTO(savedRecord);
    }

    private HealthRecordDTO convertToDTO(HealthRecord healthRecord) {
        HealthRecordDTO healthRecordDTO = new HealthRecordDTO();
        healthRecordDTO.setId(healthRecord.getId());
        healthRecordDTO.setPetId(healthRecord.getPet().getId());
        healthRecordDTO.setRecordDate(healthRecord.getRecordDate());
        healthRecordDTO.setDiagnosis(healthRecord.getDiagnosis());
        return healthRecordDTO;
    }
}

