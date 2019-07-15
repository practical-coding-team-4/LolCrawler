package kr.ac.ajou.LolCrawler.repository;

import kr.ac.ajou.LolCrawler.domain.LeagueEntryDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndReplaceOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

import static org.springframework.data.mongodb.core.query.Criteria.where;

@Repository
@Slf4j
public class LeagueEntryRepository {
    @Autowired
    private MongoTemplate mongoTemplate;

    public void updateLeagueEntryDTO(List<LeagueEntryDTO> leagueEntryDTOS){
        for(LeagueEntryDTO leagueEntryDTO : leagueEntryDTOS) {
            mongoTemplate.update(LeagueEntryDTO.class)
                    .matching(Query.query(where("summonerName").is(leagueEntryDTO.getSummonerName())).addCriteria(
                            where("queueType").is(leagueEntryDTO.getQueueType())))
                    .replaceWith(leagueEntryDTO)
                    .withOptions(FindAndReplaceOptions.options().upsert())
                    .as(LeagueEntryDTO.class)
                    .findAndReplace();
        }
    }
}
