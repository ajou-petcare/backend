package swe_group_11.pethealthmanager.DTO;

import lombok.Data;


import java.util.Date;

@Data
public class HealthRecordDTO {
    private Long id;
    private Long petid;
    private Date recordDate;
    private String diagnosis;


}
