package swe_group_11.pethealthmanager.service;

import ai.djl.ModelException;
import lombok.RequiredArgsConstructor;
import org.pytorch.IValue;
import org.pytorch.Module;
import org.pytorch.Tensor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import ai.djl.Model;
import ai.djl.inference.Predictor;
import ai.djl.translate.TranslateException;
import ai.djl.translate.Translator;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;

import org.springframework.stereotype.Service;
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

    //
    public HealthRecordDTO performCheckUp(Long petId, MultipartFile image) {

        String diagnosis = processImage(image);

        Pet pet = petRepository.findById(petId).orElseThrow(() -> new RuntimeException("Pet not found"));
        HealthRecord healthRecord = new HealthRecord();
        healthRecord.setPet(pet);
        healthRecord.setRecordDate(new Date());
        healthRecord.setDiagnosis(diagnosis);

        HealthRecord savedRecord = healthRecordRepository.save(healthRecord);
        return convertToDTO(savedRecord);
    }

    //이 부분에 djl 활용한 pytorch 모델 사용 or 플라스크로 DL API 만들어서 컨트롤러로 불러오는 식으로
    private String processImage(MultipartFile image) {

        String diagnosis = ""; // 여기에 모델 결과값


        return diagnosis;
    }

    private HealthRecordDTO convertToDTO(HealthRecord healthRecord) {
        return ...; // 미정
    }
}

