package kr.ac.ajou.LolCrawler.api;

import kr.ac.ajou.LolCrawler.domain.LeagueEntryDTO;
import kr.ac.ajou.LolCrawler.domain.SummonerDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


import java.util.Set;

@Service
public class LolApiClient {
    private final String api_key = "RGAPI-b073a3dc-8185-497a-be87-2e6f2437f7ef";
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
        Set<LeagueEntryDTO> leaguePosition = restTemplate.exchange(positionsApiUrl, HttpMethod.GET, null, Set.class, encryptedSummonerId, api_key)
        .getBody();
        return leaguePosition;
    }
}
