package com.cricket.containers;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class LeaderBoardPlayer {

    private static final ObjectMapper MAPPER = new ObjectMapper();
    private static final Path FOLDER = Paths.get("C:\\Sports\\Cricket\\data");

    // Common (all files)
    @JsonProperty("PlayerId") public Integer playerId;
    @JsonProperty("Player")   public String player;
    @JsonProperty("Team")     public String team;

    // Batting (files 1, 3, 4)
    @JsonProperty("Runs")  public Integer runs;
    @JsonProperty("SR")    public Double strikeRate;
    @JsonProperty("4s")    public Integer fours;
    @JsonProperty("6s")    public Integer sixes;
    @JsonProperty("50s")   public Integer fifties;
    @JsonProperty("100s")  public Integer hundreds;

    // Bowling (files 2, 3, 4)
    @JsonProperty("Wkts")     public Integer wickets;
    @JsonProperty("Econ")     public String economy;      // string in the feed
    @JsonProperty("Dots")     public Integer dots;
    @JsonProperty("3W")       public Integer threeWickets;
    @JsonProperty("3WTop")    public Integer threeWicketsTop;
    @JsonProperty("5W")       public Integer fiveWickets;
    @JsonProperty("HatTrick") public Integer hatTricks;

    // Fielding / awards (files 3, 4)
    @JsonProperty("Catches")             public Integer catches;
    @JsonProperty("Direct Hit Run outs") public Integer directHitRunOuts;
    @JsonProperty("Stumpings")           public Integer stumpings;
    @JsonProperty("PotM")                public Integer potm;

    // Points: file 1 and 2 use "Points", file 3 and 4 use "Total Points"
    @JsonProperty("Points")       public Double points;
    @JsonProperty("Total Points") public Double totalPoints;

    /** Common reader: pass any file name, e.g. "LeaderBoard1.json" */
    public static List<LeaderBoardPlayer> readFile(String fileName) throws Exception {
        String json = Files.readString(FOLDER.resolve(fileName), StandardCharsets.UTF_8);
        JsonNode players = MAPPER.readTree(json).get("Players");
        return MAPPER.convertValue(players, new TypeReference<List<LeaderBoardPlayer>>() {});
    }

    /** Points regardless of which file was read */
    public double getAnyPoints() {
        if (totalPoints != null) return totalPoints;
        return points != null ? points : 0.0;
    }
}