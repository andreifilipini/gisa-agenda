package com.gisa.gisaagenda.dto;

import com.gisa.gisacore.dto.BasicTransactionRequestDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SchedulingRequestDTO extends BasicTransactionRequestDTO {

    private String resourceId;
    private String date;
    private String time;
}
