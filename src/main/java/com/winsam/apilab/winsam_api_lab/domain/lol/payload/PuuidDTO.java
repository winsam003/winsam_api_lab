package com.winsam.apilab.winsam_api_lab.domain.lol.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PuuidDTO {
    private String puuid;
    private String gameName;
    private String tagLine;
}