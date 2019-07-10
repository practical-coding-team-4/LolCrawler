package kr.ac.ajou.LolCrawler.domain;

import lombok.Data;

@Data
public class LeaguePosition {
    private String queueType;
    private String summonerName;
    private boolean hotStreak;
    private MiniSeriesDTO miniSeriesDTO;
    private int wins;
    private boolean veteran;
    private int losses;
    private String rank;
    private String leagueId;
    private boolean inactive;
    private boolean freshBlood;
    private String tier;
    private String summonerId;
    private int leaguePoints;

    @Data
    public static class MiniSeriesDTO{
        private String progress;
        private int losses;
        private int target;
        private int wins;
    }

}
