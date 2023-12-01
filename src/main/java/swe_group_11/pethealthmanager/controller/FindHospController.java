package swe_group_11.pethealthmanager.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import swe_group_11.pethealthmanager.DTO.HospitalDTO;

import java.util.Arrays;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/findHosp")
public class FindHospController {

    @GetMapping("/findNearHosp")
    public ResponseEntity<List<HospitalDTO>> findNearbyHospitals(@RequestParam double latitude, @RequestParam double longitude) {
        // 요청된 위치에 관계없이 미리 정의된 병원 목록을 반환합니다.
        List<HospitalDTO> nearbyHospitals = Arrays.asList(
                // 미리 정의된 병원 데이터를 여기에 삽입
                new HospitalDTO("돌봄동물병원", 440, "경기도 수원시 팔달구 중부대로 251 1,2층 (우만동)", "031-217-7570"),
                new HospitalDTO("수원종합동물병원", 534, "경기도 수원시 영통구 중부대로 263 (원천동)", "031-214-5527"),
                new HospitalDTO("공감플러스동물의료센터", 761, "경기도 수원시 영통구 중부대로 293-3 본메디타워2층 (원천동)", "031-214-0025"),
                new HospitalDTO("라온동물병원", 1400, "경기 수원시 영통구 동탄원천로1109번길 100", "031-215-8588")
        );
        return ResponseEntity.ok(nearbyHospitals);
    }
}
