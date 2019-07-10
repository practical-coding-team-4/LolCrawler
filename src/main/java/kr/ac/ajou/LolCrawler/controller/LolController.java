package kr.ac.ajou.LolCrawler.controller;

import kr.ac.ajou.LolCrawler.domain.LeaguePosition;
import kr.ac.ajou.LolCrawler.service.LolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LolController {
    @Autowired
    LolService lolService;

    @GetMapping("/league-position/{summonerName}")
    public LeaguePosition getLeaguePosition(@PathVariable String summonerName) {
        return lolService.getLeaguePosition(summonerName);
    }

}
