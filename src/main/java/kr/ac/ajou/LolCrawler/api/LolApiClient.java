package kr.ac.ajou.LolCrawler.api;

import kr.ac.ajou.LolCrawler.domain.SummonerDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class LolApiClient {
    private final String api_key = "RGAPI-090ffbc6-a810-4fab-b6e8-e7e7f0561b3e";
    private final String summonersApiUrl = "https://kr.api.riotgames.com/lol/summoner/v4/summoners/by-name/{summonerName}?api_key={api_key}";
    private final String positionsApiUrl = "https://kr.api.riotgames.com/lol/league/v4/entries/by-summoner/{encryptedSummonerId}?api_key={api_key}";

    @Autowired
    RestTemplate restTemplate;

    public String requestEncryptedSummonerId(String summonerName) {
        SummonerDTO res = restTemplate.exchange(summonersApiUrl, HttpMethod.GET, null, SummonerDTO.class, summonerName, api_key)
                .getBody();
        return res.getId();
    }

    public String requestLeaugePosition(String encryptedSummonerId) {
        return restTemplate.exchange(positionsApiUrl, HttpMethod.GET, null, String.class, encryptedSummonerId, api_key)
                .getBody();
    }
}
