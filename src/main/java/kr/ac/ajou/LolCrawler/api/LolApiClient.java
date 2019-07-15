package kr.ac.ajou.LolCrawler.api;

import kr.ac.ajou.LolCrawler.domain.LeagueEntryDTO;
import kr.ac.ajou.LolCrawler.domain.SummonerDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


import java.util.List;

@Service
public class LolApiClient {
    private final String api_key = "RGAPI-3b37d5bc-89e7-4453-91d5-0d7b6478edc2";
    private final String summonersApiUrl = "https://kr.api.riotgames.com/lol/summoner/v4/summoners/by-name/{summonerName}?api_key={api_key}";
    private final String positionsApiUrl = "https://kr.api.riotgames.com/lol/league/v4/entries/by-summoner/{encryptedSummonerId}?api_key={api_key}";

    @Autowired
    RestTemplate restTemplate;

    public String requestEncryptedSummonerId(String summonerName) {
        SummonerDTO res = restTemplate.exchange(summonersApiUrl, HttpMethod.GET, null, SummonerDTO.class, summonerName, api_key)
                .getBody();
        return res.getId();
    }


    public List<LeagueEntryDTO> requestLeaugePosition(String encryptedSummonerId) {
        ResponseEntity<List<LeagueEntryDTO>> actualExample = restTemplate.exchange(positionsApiUrl, HttpMethod.GET,
                null, new ParameterizedTypeReference<List<LeagueEntryDTO>>() {}, encryptedSummonerId, api_key);
        List<LeagueEntryDTO> leaguePosition = actualExample.getBody();
        return leaguePosition;
    }
}
