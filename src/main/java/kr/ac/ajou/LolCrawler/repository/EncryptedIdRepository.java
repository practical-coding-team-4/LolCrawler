package kr.ac.ajou.LolCrawler.repository;

import kr.ac.ajou.LolCrawler.domain.LeagueEntryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EncryptedIdRepository {
    @Autowired
    private MongoTemplate mongoTemplate;

    public void insertLeagueEntryDTO(List<LeagueEntryDTO> leagueEntryDTOS){
        mongoTemplate.insertAll(leagueEntryDTOS);
    }

    public void updateLeagueEntryDTO(List<LeagueEntryDTO> leagueEntryDTOS){

    }

    public LeagueEntryDTO findLeagueEntryDTO(String encryptedSummonerId){
        Query query = new Query();
        query.addCriteria(Criteria.where("summonerId").is(encryptedSummonerId));
        return mongoTemplate.findOne(query,LeagueEntryDTO.class);
    }
}
