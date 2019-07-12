package kr.ac.ajou.LolCrawler.repository;

import kr.ac.ajou.LolCrawler.domain.LeagueEntryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

public class encryptedIdRepository {
    @Autowired
    private MongoTemplate mongoTemplate;

    public void insertCurrentWeather(LeagueEntryDTO leagueEntryDTO)
    {
        mongoTemplate.insert(leagueEntryDTO);
    }

    public LeagueEntryDTO findCurrentWeather(String summonerId){
        Query query = new Query();
        query.addCriteria(Criteria.where("summonerName").is(summonerId));
        return mongoTemplate.findOne(query, LeagueEntryDTO.class);
    }
}
