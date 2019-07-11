package kr.ac.ajou.LolCrawler.service;

import kr.ac.ajou.LolCrawler.api.LolApiClient;
import kr.ac.ajou.LolCrawler.domain.LeagueEntryDTO;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.Set;

@Service
@Slf4j
public class LolService {
    @Autowired
    LolApiClient lolApiClient;

    public Set<LeagueEntryDTO> getLeaguePosition(String summonerId) {
        String encryptedId = lolApiClient.requestEncryptedSummonerId(summonerId);
        log.info("Encrypted Summoner Id: {}", encryptedId);
        return lolApiClient.requestLeaugePosition(encryptedId);
    }

}
