package kr.ac.ajou.LolCrawler.repository;

import kr.ac.ajou.LolCrawler.domain.LeagueEntryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
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
        for(int i=0 ; i<leagueEntryDTOS.size();i++) {
            LeagueEntryDTO leagueEntry = leagueEntryDTOS.get(i);

            String summonerId = leagueEntry.getSummonerId();
            String queueType = leagueEntry.getQueueType();
            Query query = new Query();
            query.addCriteria(Criteria.where("summonerId").is(summonerId));
            query.addCriteria(Criteria.where("queueType").is(queueType));

            Update update = new Update();
            update.set("summonerName", leagueEntry.getSummonerName());
            update.set("hotStreak", leagueEntry.isHotStreak());
            update.set("wins", leagueEntry.getWins());
            update.set("veteran", leagueEntry.isVeteran());
            update.set("losses", leagueEntry.getLosses());
            update.set("rank", leagueEntry.getRank());
            update.set("leagueId", leagueEntry.getLeagueId());
            update.set("inactive", leagueEntry.isInactive());
            update.set("freshBlood", leagueEntry.isFreshBlood());
            update.set("tier", leagueEntry.getTier());
            update.set("summonerId", leagueEntry.getSummonerId());
            update.set("leaguePoints", leagueEntry.getLeaguePoints());
            mongoTemplate.updateFirst(query, update, LeagueEntryDTO.class);

        }
    }

    public LeagueEntryDTO findLeagueEntryDTO(String encryptedSummonerId){
        Query query = new Query();
        query.addCriteria(Criteria.where("summonerId").is(encryptedSummonerId));
        return mongoTemplate.findOne(query,LeagueEntryDTO.class);
    }
}
