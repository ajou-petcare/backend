package swe_group_11.pethealthmanager.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import swe_group_11.pethealthmanager.DTO.HealthRecordDTO;
import swe_group_11.pethealthmanager.model.HealthRecord;
import swe_group_11.pethealthmanager.repository.HealthRecordRepository;

@Service
@RequiredArgsConstructor
public class HealthRecordService {

    private HealthRecordRepository healthRecordRepository;

    public HealthRecordDTO createHealthRecord(HealthRecordDTO healthRecordDTO) {
        HealthRecord healthRecord = new HealthRecord();
        healthRecord.setPetId(healthRecordDTO.getPetId());
        healthRecord.setRecordDate(healthRecordDTO.getRecordDate());
        healthRecord.setDiagnosis(healthRecordDTO.getDiagnosis());
        HealthRecord Record = healthRecordRepository.save(healthRecord);
        return (convertToDTO(healthRecord));
    }

    private HealthRecordDTO convertToDTO(HealthRecord healthRecord) {
        HealthRecordDTO healthRecordDTO = new HealthRecordDTO();
        healthRecordDTO.setId(healthRecord.getId());
        healthRecordDTO.setPetId(healthRecord.getPetId());
        healthRecordDTO.setRecordDate(healthRecord.getRecordDate());
        healthRecordDTO.setDiagnosis(healthRecord.getDiagnosis());
        return healthRecordDTO;
    }
}
