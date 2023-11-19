package swe_group_11.pethealthmanager.service;

import ai.djl.ModelException;
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
import org.springframework.stereotype.Service;

@Service
public class CheckUpService {

    private final Model model;

    public CheckUpService() throws IOException, ModelException {
        // 모델 파일의 경로와 이름을 여기서 지정합니다.
        // 모델의 위치나 이름이 변경되면 아래 경로를 업데이트해야 합니다.
        Path modelPath = Paths.get("path/to/your/model.pt"); // <-- 모델 경로 수정 필요

        model = Model.newInstance("PyTorch"); // 모델의 종류를 지정 (예: "PyTorch")
        model.load(modelPath, "modelName"); // <-- 모델 이름 수정 필요 (모델 파일명이나 식별자)
    }

    public String diagnose(MultipartFile file) throws IOException, TranslateException {
        // 이미지 파일을 처리하여 입력으로 사용
        // Translator 구현 필요: 이미지를 모델의 입력 형식으로 변환
        Translator<MultipartFile, String> translator = ...; // <-- Translator 구현 필요

        try (Predictor<MultipartFile, String> predictor = model.newPredictor(translator)) {
            String result = predictor.predict(file);
            return result;
        }
    }
}
