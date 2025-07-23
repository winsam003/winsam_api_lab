package com.winsam.apilab.winsam_api_lab.domain.lol.web;

import com.winsam.apilab.winsam_api_lab.domain.lol.payload.MatchInfoDTO;
import com.winsam.apilab.winsam_api_lab.domain.lol.payload.PuuidDTO;
import com.winsam.apilab.winsam_api_lab.domain.lol.service.SummornersService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.logging.Logger;

@Slf4j
@RestController
public class SummornersController {

    Logger logger = Logger.getLogger(this.getClass().getName());

    @Autowired
    SummornersService puuidService;

    @GetMapping("/summoners/{tagline}/{by_riot_id}")
    public ResponseEntity<MatchInfoDTO> summornersGetPuuid(@PathVariable String tagline, @PathVariable String by_riot_id) {
        logger.info("SummornersController summornersGetPuuid access Succeed");

        PuuidDTO dto = new PuuidDTO();

        String puuid = puuidService.summornersGetPuuid(by_riot_id, tagline).block();

        String matchId = puuidService.sumornersGetMatchUp(puuid, 0, 20).block().getBody().get(0);

        MatchInfoDTO matchInfo = puuidService.sumornersgetMatchInfo(matchId).block().getBody();

        return ResponseEntity.ok(matchInfo);
    }

}