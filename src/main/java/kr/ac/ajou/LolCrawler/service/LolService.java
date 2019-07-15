package kr.ac.ajou.LolCrawler.service;

import kr.ac.ajou.LolCrawler.api.LolApiClient;
import kr.ac.ajou.LolCrawler.domain.LeagueEntryDTO;
import kr.ac.ajou.LolCrawler.repository.EncryptedIdRepository;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class LolService {
    @Autowired
    LolApiClient lolApiClient;
    @Autowired
    EncryptedIdRepository encryptedIdRepository = null;

    public List<LeagueEntryDTO> getLeaguePosition(String summonerId) {
        String encryptedId = lolApiClient.requestEncryptedSummonerId(summonerId);
        log.info("Encrypted Summoner Id: {}", encryptedId);
        List<LeagueEntryDTO> leagueEntryDTOS = lolApiClient.requestLeaugePosition(encryptedId);

        if(encryptedIdRepository.findLeagueEntryDTO(encryptedId) != null){
            encryptedIdRepository.updateLeagueEntryDTO(leagueEntryDTOS);
            log.info("LeagueEntryDTOS has been updated successfully in DB: " + leagueEntryDTOS);

        }else {
            encryptedIdRepository.insertLeagueEntryDTO(leagueEntryDTOS);
            log.info("LeagueEntryDTOS has been inserted successfully in DB: " + leagueEntryDTOS);
        }

        return leagueEntryDTOS;
    }

}
