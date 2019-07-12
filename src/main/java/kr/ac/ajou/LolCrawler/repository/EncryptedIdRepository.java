package kr.ac.ajou.LolCrawler.repository;

import kr.ac.ajou.LolCrawler.domain.LeagueEntryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public class EncryptedIdRepository {
    @Autowired
    private MongoTemplate mongoTemplate;

    public void insertLeagueEntryDTO(Set<LeagueEntryDTO> leagueEntryDTOS)
    {
        mongoTemplate.insertAll(leagueEntryDTOS);
    }

    public Set findLeagueEntryDTO(String summonerId){
        Query query = new Query();
        query.addCriteria(Criteria.where("summonerName").is(summonerId));
        return mongoTemplate.findOne(query, Set.class);
    }
}
