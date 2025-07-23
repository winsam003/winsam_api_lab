package com.winsam.apilab.winsam_api_lab.domain.lol.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MatchInfoDTO {
    private InfoDTO info; // InfoDTO를 매핑
}

@AllArgsConstructor
@NoArgsConstructor
@Data
class InfoDTO {
    private List<ParticipantDTO> participants; // participants 리스트를 매핑

    @JsonProperty("gameDuration")
    private String gameDuration; // 게임 길이
    @JsonProperty("queueId")
    private String queueId; // 솔랭 420, 노말 430, 팀랭 440
}

@AllArgsConstructor
@NoArgsConstructor
@Data
class ParticipantDTO {
    @JsonProperty("riotIdGameName")
    private String riotIdGameName; // 닉네임
    @JsonProperty("riotIdTagline")
    private String riotIdTagline; // tagline (해당 유저 세부 검색때 키 값)
    @JsonProperty("individualPosition")
    private String individualPosition; // 라인
    @JsonProperty("teamId")
    private String teamId; // 블루팀 100, 레드팀 200
    @JsonProperty("win")
    private boolean win; // 승패여부
    @JsonProperty("kills")
    private int kills; // 킬 수
    @JsonProperty("assists")
    private int assists; // 어시스트 수
    @JsonProperty("deaths")
    private int deaths; // 데스 수
    @JsonProperty("championId")
    private String championId; // 챔피언 ID
    @JsonProperty("championName")
    private String championName; // 챔피언 이름
    @JsonProperty("champLevel")
    private int champLevel; // 챔피언 레벨
    private challengesDTO challenges; // challengesDTO 매핑
}

@AllArgsConstructor
@NoArgsConstructor
@Data
class challengesDTO{
    @JsonProperty("kda")
    private float KDA; // KDA
}