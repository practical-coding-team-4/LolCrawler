package kr.ac.ajou.LolCrawler.domain;

import lombok.Data;

@Data
public class MiniSeriesDTO {
    String progress;
    int losses;
    int target;
    int wins;
}
