package swe_group_11.pethealthmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import swe_group_11.pethealthmanager.model.HealthRecord;

public interface HealthRecordRepository extends JpaRepository<HealthRecord, Long> {

}
