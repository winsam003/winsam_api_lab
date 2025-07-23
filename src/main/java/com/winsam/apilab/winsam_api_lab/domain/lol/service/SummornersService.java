package com.winsam.apilab.winsam_api_lab.domain.lol.service;

import com.winsam.apilab.winsam_api_lab.domain.lol.payload.MatchInfoDTO;
import com.winsam.apilab.winsam_api_lab.domain.lol.payload.PuuidDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.logging.Logger;

@Service
public class SummornersService {

    Logger logger = Logger.getLogger(this.getClass().getName());

    @Autowired
    private WebClient.Builder webClientBuilder;

    // summornersGetPuuid
    public Mono<String> summornersGetPuuid(String by_riot_id, String tagline) {
        logger.info("SummornersService summornersGetPuuid access Succeed");


        String url = "https://asia.api.riotgames.com/riot/account/v1/accounts/by-riot-id";
        String request_URL = url + "/" + by_riot_id + "/" + tagline;

        String User_Agent = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/132.0.0.0 Safari/537.36";
        String Accept_Language = "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7";
        String Accept_Charset = "application/x-www-form-urlencoded; charset=UTF-8";
        String Origin = "https://developer.riotgames.com";
        String api_key = "RGAPI-8dce00ec-8687-476a-98fb-90967ee4d831";

        HttpHeaders headers = new HttpHeaders();
        headers.add("User-Agent", User_Agent);
        headers.add("Accept-Language", Accept_Language);
        headers.add("Accept-Charset", Accept_Charset);
        headers.add("Origin", Origin);
        headers.add("X-Riot-Token", api_key);

        // WebClient 요청
        return webClientBuilder.build()
                .get()
                .uri(request_URL)
                .headers(httpHeaders -> httpHeaders.addAll(headers))
                .retrieve()
                .bodyToMono(PuuidDTO.class)  // 응답을 String으로 받기
                .map(puuidDTO -> puuidDTO.getPuuid());
    }
    // summornersGetPuuid


    // sumornersGetMatchUp
    public Mono<ResponseEntity<List<String>>> sumornersGetMatchUp(String puuid, int start, int count){
        logger.info("SummornersService sumornersGetMatchUp access Succeed");

        String url = "https://asia.api.riotgames.com/lol/match/v5/matches/by-puuid";
        String request_URL = url + "/" + puuid + "/" + "ids?start=" + start + "&count=" + count;

        String User_Agent = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/132.0.0.0 Safari/537.36";
        String Accept_Language = "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7";
        String Accept_Charset = "application/x-www-form-urlencoded; charset=UTF-8";
        String Origin = "https://developer.riotgames.com";
        String api_key = "RGAPI-8dce00ec-8687-476a-98fb-90967ee4d831";

        HttpHeaders headers = new HttpHeaders();
        headers.add("User-Agent", User_Agent);
        headers.add("Accept-Language", Accept_Language);
        headers.add("Accept-Charset", Accept_Charset);
        headers.add("Origin", Origin);
        headers.add("X-Riot-Token", api_key);

        return webClientBuilder.build()
                .get()
                .uri(request_URL)
                .headers(httpHeaders -> httpHeaders.addAll(headers))
                .retrieve()
                .toEntity(new ParameterizedTypeReference<List<String>>() {})  // 응답을 List<String>으로 받기
                .map(response -> ResponseEntity.status(HttpStatus.OK).body(response.getBody()));
    }
    // sumornersGetMatchUp


    // sumornersgetMatchInfo
    public Mono<ResponseEntity<MatchInfoDTO>> sumornersgetMatchInfo(String marchId){
        logger.info("SummornersService sumornersgetMatchInfo access Succeed");

        String url = "https://asia.api.riotgames.com/lol/match/v5/matches";
        String request_URL = url + "/" + marchId;

        String User_Agent = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/132.0.0.0 Safari/537.36";
        String Accept_Language = "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7";
        String Accept_Charset = "application/x-www-form-urlencoded; charset=UTF-8";
        String Origin = "https://developer.riotgames.com";
        String api_key = "RGAPI-8dce00ec-8687-476a-98fb-90967ee4d831";

        HttpHeaders headers = new HttpHeaders();
        headers.add("User-Agent", User_Agent);
        headers.add("Accept-Language", Accept_Language);
        headers.add("Accept-Charset", Accept_Charset);
        headers.add("Origin", Origin);
        headers.add("X-Riot-Token", api_key);

        return webClientBuilder.build()
                .get()
                .uri(request_URL)
                .headers(httpHeaders -> httpHeaders.addAll(headers))
                .retrieve()
                .toEntity(MatchInfoDTO.class)  // 응답을 List<String>으로 받기
                .map(response -> ResponseEntity.status(HttpStatus.OK).body(response.getBody()));
    }
    // sumornersgetMatchInfo

}