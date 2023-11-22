package swe_group_11.pethealthmanager.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import swe_group_11.pethealthmanager.DTO.HealthRecordDTO;
import swe_group_11.pethealthmanager.model.HealthRecord;
import swe_group_11.pethealthmanager.model.Pet;
import swe_group_11.pethealthmanager.repository.HealthRecordRepository;
import swe_group_11.pethealthmanager.repository.PetRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HealthRecordService {

    private final HealthRecordRepository healthRecordRepository;
    private final PetRepository petRepository; // PetRepository 추가

    public List<HealthRecordDTO> findAllHealthRecords() {
        List<HealthRecord> records = healthRecordRepository.findAll();
        return records.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public HealthRecordDTO createHealthRecord(HealthRecordDTO healthRecordDTO) {
        Pet pet = petRepository.findById(healthRecordDTO.getPetid()).orElseThrow(); // Pet 찾기
        HealthRecord healthRecord = new HealthRecord();
        healthRecord.setPet(pet); // Pet 객체 설정
        healthRecord.setRecordDate(healthRecordDTO.getRecordDate());
        healthRecord.setDiagnosis(healthRecordDTO.getDiagnosis());
        HealthRecord savedRecord = healthRecordRepository.save(healthRecord);
        return convertToDTO(savedRecord);
    }

    private HealthRecordDTO convertToDTO(HealthRecord healthRecord) {
        HealthRecordDTO healthRecordDTO = new HealthRecordDTO();
        healthRecordDTO.setId(healthRecord.getId());
        healthRecordDTO.setPetid(healthRecord.getPet().getId()); // Pet ID 설정
        healthRecordDTO.setRecordDate(healthRecord.getRecordDate());
        healthRecordDTO.setDiagnosis(healthRecord.getDiagnosis());
        return healthRecordDTO;
    }
}
