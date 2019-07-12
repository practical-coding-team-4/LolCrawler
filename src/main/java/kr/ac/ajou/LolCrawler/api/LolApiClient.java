package kr.ac.ajou.LolCrawler.api;

import kr.ac.ajou.LolCrawler.domain.LeagueEntryDTO;
import kr.ac.ajou.LolCrawler.domain.SummonerDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


import java.util.Set;

@Service
public class LolApiClient {
    private final String api_key = "RGAPI-11baa09c-49d7-4fc1-a16c-cb036f28f4d1";
    private final String summonersApiUrl = "https://kr.api.riotgames.com/lol/summoner/v4/summoners/by-name/{summonerName}?api_key={api_key}";
    private final String positionsApiUrl = "https://kr.api.riotgames.com/lol/league/v4/entries/by-summoner/{encryptedSummonerId}?api_key={api_key}";

    @Autowired
    RestTemplate restTemplate;

    public String requestEncryptedSummonerId(String summonerName) {
        SummonerDTO res = restTemplate.exchange(summonersApiUrl, HttpMethod.GET, null, SummonerDTO.class, summonerName, api_key)
                .getBody();
        return res.getId();
    }


    public Set<LeagueEntryDTO> requestLeaugePosition(String encryptedSummonerId) {
        ResponseEntity<Set<LeagueEntryDTO>> actualExample = restTemplate.exchange(positionsApiUrl, HttpMethod.GET,
                null, new ParameterizedTypeReference<Set<LeagueEntryDTO>>() {}, encryptedSummonerId, api_key);
        Set<LeagueEntryDTO> leaguePosition = actualExample.getBody();
        return leaguePosition;
    }
}
