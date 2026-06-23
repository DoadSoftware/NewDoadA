package com.cricket.captions;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import com.cricket.model.BatBallGriff;
import com.cricket.model.BattingCard;
import com.cricket.model.BestStats;
import com.cricket.model.BowlingCard;
import com.cricket.model.Bugs;
import com.cricket.model.Configuration;
import com.cricket.model.EverestBugs;
import com.cricket.model.ForeignLanguageData;
import com.cricket.model.HeadToHeadPlayer;
import com.cricket.model.Inning;
import com.cricket.model.LeagueTable;
import com.cricket.model.MatchAllData;
import com.cricket.model.MultiLanguageDatabase;
import com.cricket.model.Partnership;
import com.cricket.model.PerformanceBug;
import com.cricket.model.Player;
import com.cricket.model.Setup;
import com.cricket.model.Statistics;
import com.cricket.model.StatsType;
import com.cricket.model.Team;
import com.cricket.model.Tournament;
import com.cricket.model.VariousText;
import com.cricket.service.CricketService;
import com.cricket.util.CricketFunctions;
import com.cricket.util.CricketUtil;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class BugsAndMiniGfx 
{
	public String status = "",homecolor = "", awaycolor = "",WhichGroup = "",containerName = "", previous_sixes = "",today_sixes="",
			previous_fours = "",today_fours="",logoCategory = "",reviewData="";
	public int FirstPlayerId, rowId1 = 0, plyr_count=0, numberOfRows = 0,omo=0,whichTeam;
	public String WhichScoreCard, WhichProfile, WhichStyle, WhichType, 
			containerName_2 = "",containerName_3 = "",containerName_4 = "",logo_name = "" , color_name = "",
			logo_name1 = "" , color_name1 = "",Player_photo="", whichGFX = "",whichtype = "", phaseWiseScore = "",sponsor = "",
			manhattanOrNot = "";

	public boolean isVisited = false;
	int fallOfWickets;
	public static int  index_Player =0;
	int rowId=0, omo_num=0;
	String cont_name = "",text_name = "",stats_text="";
	
	@JsonIgnore
	public List<PrintWriter> print_writers; 
	public Configuration config;
	public List<Bugs> bugs;
	public List<EverestBugs> everestBugs;
	public List<Team> Teams;
	public List<VariousText> VariousText;
	public List<HeadToHeadPlayer> headToHead;
	public List<Player> Players;
	//public List<Bugs> bugs;
	public List<MatchAllData> tournament_matches;
	public List<PerformanceBug> performanceBugs;
	public List<Tournament> past_tournament_stats;
	
	public List<BatBallGriff> griff = new ArrayList<BatBallGriff>();
	public List<String> this_data_str = new ArrayList<String>();
	ArrayList<BestStats> bowler_data = new ArrayList<BestStats>();
	ArrayList<BestStats> batter_data = new ArrayList<BestStats>();
	public List<Tournament> this_series = new ArrayList<Tournament>();
	public List<BestStats> top_batsman_beststats = new ArrayList<BestStats>();
	public List<BestStats> top_bowler_beststats = new ArrayList<BestStats>();
	
	//public FallOfWicket fallOfWickets;
	public BattingCard battingCard;
	public BowlingCard bowlingCard;
	public Partnership partnership,partnership1;
	public VariousText variousText;
	public Inning inning;
	public Player player;
	public Statistics stat;
	public StatsType statsType;
	public List<Statistics> statistics;
	public List<StatsType> statsTypes;
	public List<BattingCard> playerCards;
	public LeagueTable leagueTable;
	
	public MultiLanguageDatabase multilanguagedata;
	public ForeignLanguageData howOut;
	public List<ForeignLanguageData> foreignLanguageDataList;
	public List<ForeignLanguageData> foreignLanguageData = new ArrayList<ForeignLanguageData>();
	public List<ForeignLanguageData> foreignLanguageOmo = new ArrayList<ForeignLanguageData>();
	
	@JsonIgnore
	public CricketService cricketService;
	
	public Team team;
	public Bugs bug;
	public EverestBugs everestBug;
	public PerformanceBug performanceBug;
	public Tournament tournament;
	
	public BugsAndMiniGfx() {
		super();
	}
	
	public BugsAndMiniGfx(List<PrintWriter> print_writers, Configuration config, List<Bugs> bugs, List<PerformanceBug> performanceBugs, List<Team> teams, 
			List<VariousText> VariousText,CricketService cricketService,List<HeadToHeadPlayer> headToHead, List<MatchAllData> tournament_matches,
			List<Statistics> statistics, List<StatsType> statsTypes, List<Tournament> past_tournament_stats,List<EverestBugs> everestBugs,
			List<Player> players) {
		super();
		this.print_writers = print_writers;
		this.config = config;
		this.bugs = bugs;
		this.everestBugs = everestBugs;
		this.Teams = teams;
		this.VariousText = VariousText;
		this.cricketService = cricketService;
		this.headToHead = headToHead;
		this.tournament_matches = tournament_matches;
		this.performanceBugs = performanceBugs;
		this.statistics = statistics;
		this.statsTypes = statsTypes;
		this.past_tournament_stats = past_tournament_stats;
		this.Players = players;
		foreignLanguageData.add(new ForeignLanguageData("", "", "", ""));
		foreignLanguageOmo.add(new ForeignLanguageData("0", "2", "3", "1"));
	}
	public static String getOrdinalMatch(String matchNumber) {
	    // matchNumber looks like "match 01"
	    // Extract the number part
	    String[] parts = matchNumber.split(" ");
	    int num = Integer.parseInt(parts[1]);

	    // Convert number to ordinal word
	    switch (num) {
	        case 1: return "First";
	        case 2: return "Second";
	        case 3: return "Third";
	        case 4: return "Fourth";
	        case 5: return "Fifth";
	        case 6: return "Sixth";
	        case 7: return "Seventh";
	        case 8: return "Eighth";
	        case 9: return "Ninth";
	        case 10: return "Tenth";
	        default: return num + "th"; // fallback
	    }
	}
	public static double customRound(double num) {
        int firstDecimal = (int) ((num * 10) % 10); // get first decimal digit

        if (firstDecimal == 1 || firstDecimal == 6) {
            return Math.ceil(num); // round up to next whole number
        } else {
            return num; // keep same decimal value
        }
    }
	public String populateMiniScorecard(int WhichSide, String whatToProcess, MatchAllData matchAllData) throws StreamReadException, DatabindException, NumberFormatException, FileNotFoundException, IOException {
		if (matchAllData == null || matchAllData.getMatch() == null || matchAllData.getMatch().getInning() == null) {
			status = "populateMiniScorecard match is null Or Inning is null";
		} else {
			inning = matchAllData.getMatch().getInning().stream().filter(inn -> inn.getInningNumber() == Integer.valueOf(whatToProcess.split(",")[1]))
					.findAny().orElse(null);
			if(inning == null) {
				return "populateMiniScorecard Inning is null";
			}
		}
		if(populateMiniBody(WhichSide, whatToProcess.split(",")[0],matchAllData, Integer.valueOf(whatToProcess.split(",")[1])) == Constants.OK) {
			status = Constants.OK;
		}
		return status;
	}
	public String populateMiniBowlingcard(int WhichSide, String whatToProcess, MatchAllData matchAllData) throws StreamReadException, DatabindException, NumberFormatException, FileNotFoundException, IOException {
		if (matchAllData == null || matchAllData.getMatch() == null || matchAllData.getMatch().getInning() == null) {
			status = "populateMiniBowlingcard match is null Or Inning is null";
		} else {
			inning = matchAllData.getMatch().getInning().stream().filter(inn -> inn.getInningNumber() == Integer.valueOf(whatToProcess.split(",")[1]))
					.findAny().orElse(null);
			if(inning == null) {
				return "populateMiniBowlingcard Inning is null";
			}
		}
		if(populateMiniBody(WhichSide, whatToProcess.split(",")[0],matchAllData, Integer.valueOf(whatToProcess.split(",")[1])) == Constants.OK) {
			status = Constants.OK;
		}
		return status;
	}
	public String populatePointsTable(String whatToProcess, MatchAllData matchAllData,int WhichSide) throws InterruptedException, JAXBException, StreamReadException, DatabindException, FileNotFoundException, IOException
	{
		switch (config.getBroadcaster().toUpperCase()) {
		case Constants.ACC:
			if(new File(CricketUtil.CRICKET_DIRECTORY + CricketUtil.LEAGUE_TABLE_DIRECTORY + WhichGroup + CricketUtil.XML_EXTENSION).exists()) {
				leagueTable = (LeagueTable)JAXBContext.newInstance(LeagueTable.class).createUnmarshaller().unmarshal(
						new File(CricketUtil.CRICKET_DIRECTORY + CricketUtil.LEAGUE_TABLE_DIRECTORY + WhichGroup + CricketUtil.XML_EXTENSION));
			}
			break;
		default:
			if(new File(CricketUtil.CRICKET_DIRECTORY + CricketUtil.LEAGUE_TABLE_DIRECTORY + "LeagueTable" + CricketUtil.XML_EXTENSION).exists()) {
				leagueTable = (LeagueTable)JAXBContext.newInstance(LeagueTable.class).createUnmarshaller().unmarshal(
						new File(CricketUtil.CRICKET_DIRECTORY + CricketUtil.LEAGUE_TABLE_DIRECTORY + "LeagueTable" + CricketUtil.XML_EXTENSION));
			}
			break;
		}
		
		if(leagueTable == null) {
			return "populatePointsTable : League Table is null";
		}
		
		if(populateMiniBody(WhichSide, whatToProcess.split(",")[0],matchAllData, 0) == Constants.OK) {
			status = Constants.OK;
		}
		return status;
	}
	public String populateGriff(String whatToProcess,int WhichSide,MatchAllData matchAllData) throws StreamReadException, DatabindException, FileNotFoundException, IOException {
		FirstPlayerId = Integer.valueOf(whatToProcess.split(",")[2]);
		
		inning = matchAllData.getMatch().getInning().stream().filter(inn -> inn.getInningNumber() == Integer.valueOf(whatToProcess.split(",")[1])).findAny().orElse(null);
		if(inning == null) {
			return "populateBatThisMatch: Current Inning NOT found in this match";
		}
		
		player = cricketService.getAllPlayer().stream().filter(plyr ->plyr.getPlayerId() == Integer.valueOf(whatToProcess.split(",")[2])).findAny().orElse(null);
		if(player == null) {
			return "player not found";
		}
		
		team = Teams.stream().filter(tm->tm.getTeamId() == player.getTeamId()).findAny().orElse(null);
		if(team == null) {
			return "Can't find team of the player";
		}
		
		if(whatToProcess.split(",")[0].equalsIgnoreCase("Alt_F1")) {
			griff = CricketFunctions.getBatBallGriffData(CricketUtil.BATSMAN,Integer.valueOf(whatToProcess.split(",")[2]), Teams, tournament_matches, matchAllData);
		}else if(whatToProcess.split(",")[0].equalsIgnoreCase("Alt_F2")) {
			griff = CricketFunctions.getBatBallGriffData(CricketUtil.BOWLER,Integer.valueOf(whatToProcess.split(",")[2]), Teams, tournament_matches, matchAllData);
		}
		if(griff == null) {
			return "Griff is null";
		}
		
		if(populateMiniBody(WhichSide, whatToProcess.split(",")[0], matchAllData, inning.getInningNumber()) == Constants.OK) {
			status = Constants.OK;
		}
		return status;
	}
	public String populateSponsor(int WhichSide, String whatToProcess, MatchAllData matchAllData,List<EverestBugs> bg) {
		
		everestBug = everestBugs.stream().filter(eb -> eb.getBugId() == Integer.valueOf(whatToProcess.split(",")[2])).findAny().orElse(null);
		if(PopulateBugBody(WhichSide, whatToProcess,matchAllData) == Constants.OK) {
			status = Constants.OK;
		}
		return status;
	}
	public String populateBugSixDistance(String whatToProcess, MatchAllData matchAllData, int whichSide) {
		inning = matchAllData.getMatch().getInning().stream().filter(inn -> inn.getInningNumber() == Integer.valueOf(whatToProcess.split(",")[1])).findAny().orElse(null);
		if(inning == null) {
			return "inning is null";
		}
		if(PopulateBugBody(whichSide, whatToProcess,matchAllData) == Constants.OK) {
			status = Constants.OK;
		}
		return status;
	}
	public String populateWicketSequencing(String whatToProcess,MatchAllData matchAllData,int WhichSide)
	{
		if (matchAllData == null || matchAllData.getMatch() == null || matchAllData.getMatch().getInning() == null) {
			status = "populateWicketSequencing match is null Or Inning is null";
		} else {
			inning = matchAllData.getMatch().getInning().stream().filter(inn -> inn.getInningNumber() == Integer.valueOf(whatToProcess.split(",")[1])).findAny().orElse(null);
			if(inning == null) {
				return "populateWicketSequencing Inning is null";
			}
			
			player = CricketFunctions.getPlayerFromMatchData(Integer.valueOf(whatToProcess.split(",")[2]), matchAllData);
			battingCard = inning.getBattingCard().stream().filter(bc->bc.getPlayerId() == player.getPlayerId()).findAny().orElse(null);
			team = Teams.stream().filter(tm -> tm.getTeamId() == player.getTeamId()).findAny().orElse(null);
			if(team == null) {
				return "populateWicketSequencing: Team id [" + battingCard.getPlayer().getTeamId() + "] from database is returning NULL";
			}
		}
		if(PopulateBugBody(WhichSide, whatToProcess,matchAllData) == Constants.OK) {
			status = Constants.OK;
		}
		return status;
	}
	public String populatePerformanceBug(String whatToProcess, int whichSide, MatchAllData matchAllData) {
		performanceBug = performanceBugs.stream().filter(p -> p.getBugId() == Integer.valueOf(whatToProcess.split(",")[2])).findAny().orElse(null);
		System.out.println(performanceBug.toString());
		if(performanceBug == null) {
			return "performanceBug: performanceBug is returning NULL";
		}
		if(performanceBug.getFlag() != null) {
			team = Teams.stream().filter(tm -> tm.getTeamBadge().equalsIgnoreCase(performanceBug.getFlag())).findAny().orElse(null);
			if(team == null) {
				return "performanceBug: Flag in database is returning NULL";
			}
		}
		
		if(PopulateBugBody(whichSide, whatToProcess,matchAllData) == Constants.OK) {
			status = Constants.OK;
		}
		return status;
	}
	public String populateThisMatchFourCounter(String whatToProcess,int WhichSide,MatchAllData matchAllData) throws CloneNotSupportedException {
		
		this_data_str = new ArrayList<String>();
		
		today_fours = String.valueOf(CricketFunctions.extracttournamentFoursAndSixesData("CURRENT_MATCH_DATA", null, matchAllData, null).getTournament_fours());
//		today_fours = String.valueOf(CricketFunctions.extracttournamentFoursAndSixes("CURRENT_MATCH_DATA",tournament_matches, matchAllData, null).getTournament_fours());
		
		if(Integer.valueOf(today_fours) > 0 && matchAllData.getEventFile().getEvents().get(matchAllData.getEventFile().getEvents().size()-1).getEventType().equalsIgnoreCase(CricketUtil.FOUR)) {
			if(matchAllData.getEventFile().getEvents().get(matchAllData.getEventFile().getEvents().size()-1).getEventWasABoundary() != null && 
					matchAllData.getEventFile().getEvents().get(matchAllData.getEventFile().getEvents().size()-1).getEventWasABoundary().equalsIgnoreCase(CricketUtil.YES)) {
				
				today_fours = String.valueOf(Integer.valueOf(today_fours));
			}
		}
		
		this_data_str.add(CricketFunctions.hundredsTensUnits(String.valueOf(Integer.valueOf(today_fours))));
		if(WhichSide == 1) {
			String new_four_value = String.valueOf((Integer.valueOf(today_fours) + 1));
			this_data_str.add(CricketFunctions.hundredsTensUnits(new_four_value));
		}
		
		if(PopulateBugBody(WhichSide, whatToProcess,matchAllData) == Constants.OK) {
			status = Constants.OK;
		}
		return status;
	}
	public String populateThisMatchSixCounter(String whatToProcess,int WhichSide,MatchAllData matchAllData) throws CloneNotSupportedException {
		
		this_data_str = new ArrayList<String>();
		today_sixes = String.valueOf(CricketFunctions.extracttournamentFoursAndSixesData("CURRENT_MATCH_DATA", null, matchAllData, null).getTournament_sixes());
		
//		today_sixes = String.valueOf(CricketFunctions.extracttournamentFoursAndSixes("CURRENT_MATCH_DATA",tournament_matches, matchAllData, null).getTournament_sixes());
		
		if(Integer.valueOf(today_sixes) > 0 && matchAllData.getEventFile().getEvents().get(matchAllData.getEventFile().getEvents().size()-1).getEventType().equalsIgnoreCase(CricketUtil.SIX)) {
			if(matchAllData.getEventFile().getEvents().get(matchAllData.getEventFile().getEvents().size()-1).getEventWasABoundary() != null && 
					matchAllData.getEventFile().getEvents().get(matchAllData.getEventFile().getEvents().size()-1).getEventWasABoundary().equalsIgnoreCase(CricketUtil.YES)) {
				
				today_sixes = String.valueOf(Integer.valueOf(today_sixes));
			}
		}
		
		this_data_str.add(CricketFunctions.hundredsTensUnits(String.valueOf(Integer.valueOf(today_sixes))));
		if(WhichSide == 1) {
			String new_six_value = String.valueOf((Integer.valueOf(today_sixes) + 1));
			this_data_str.add(CricketFunctions.hundredsTensUnits(new_six_value));
		}
		
		if(PopulateBugBody(WhichSide, whatToProcess,matchAllData) == Constants.OK) {
			status = Constants.OK;
		}
		return status;
	}
	public String populateFourCounter(String whatToProcess,int WhichSide,MatchAllData matchAllData) throws CloneNotSupportedException {
		
		this_data_str = new ArrayList<String>();
		today_fours = String.valueOf(CricketFunctions.extracttournamentFoursAndSixesData("CURRENT_MATCH_DATA", headToHead, matchAllData, null).getTournament_fours());
		
		if(Integer.valueOf(today_fours) > 0 && matchAllData.getEventFile().getEvents().get(matchAllData.getEventFile().getEvents().size()-1).getEventType().equalsIgnoreCase(CricketUtil.FOUR)) {
			if(matchAllData.getEventFile().getEvents().get(matchAllData.getEventFile().getEvents().size()-1).getEventWasABoundary() != null && 
					matchAllData.getEventFile().getEvents().get(matchAllData.getEventFile().getEvents().size()-1).getEventWasABoundary().equalsIgnoreCase(CricketUtil.YES)) {
				
				today_fours = String.valueOf(Integer.valueOf(today_fours));
			}
		}
		
		if(previous_fours.isEmpty()) {
			previous_fours = "0";
		}
		
		this_data_str.add(CricketFunctions.hundredsTensUnits(String.valueOf(Integer.valueOf(previous_fours) + Integer.valueOf(today_fours))));
		if(WhichSide == 1) {
			String new_four_value = String.valueOf((Integer.valueOf(previous_fours) + Integer.valueOf(today_fours) + 1));
			this_data_str.add(CricketFunctions.hundredsTensUnits(new_four_value));
		}
		
		if(PopulateBugBody(WhichSide, whatToProcess,matchAllData) == Constants.OK) {
			status = Constants.OK;
		}
		return status;
	}
	
	public String populateCounter(String whatToProcess,int WhichSide,MatchAllData matchAllData) throws CloneNotSupportedException {
		
		this_data_str = new ArrayList<String>();
		today_sixes = String.valueOf(CricketFunctions.extracttournamentFoursAndSixesData("CURRENT_MATCH_DATA", headToHead, matchAllData, null).getTournament_sixes());
		
		if(Integer.valueOf(today_sixes) > 0 && matchAllData.getEventFile().getEvents().get(matchAllData.getEventFile().getEvents().size()-1).getEventType().equalsIgnoreCase(CricketUtil.SIX)) {
			if(matchAllData.getEventFile().getEvents().get(matchAllData.getEventFile().getEvents().size()-1).getEventWasABoundary() != null && 
					matchAllData.getEventFile().getEvents().get(matchAllData.getEventFile().getEvents().size()-1).getEventWasABoundary().equalsIgnoreCase(CricketUtil.YES)) {
				
				today_sixes = String.valueOf(Integer.valueOf(today_sixes));
			}
		}
		
		if(previous_sixes.isEmpty()) {
			previous_sixes = "0";
		}
		System.out.println("previous_sixes - " + previous_sixes);
		System.out.println("today_sixes - " + today_sixes);
		
		this_data_str.add(CricketFunctions.hundredsTensUnits(String.valueOf(Integer.valueOf(previous_sixes) + Integer.valueOf(today_sixes))));
		if(WhichSide == 1) {
			String new_six_value = String.valueOf((Integer.valueOf(previous_sixes) + Integer.valueOf(today_sixes) + 1));
			this_data_str.add(CricketFunctions.hundredsTensUnits(new_six_value));
		}
		if(PopulateBugBody(WhichSide, whatToProcess,matchAllData) == Constants.OK) {
			status = Constants.OK;
		}
		return status;
	}
	public String bugsCurrPartnership(String whatToProcess,MatchAllData matchAllData,int WhichSide) throws Exception {
		
		if (matchAllData == null || matchAllData.getMatch() == null || matchAllData.getMatch().getInning() == null) {
			return "bugsCurrPartnership match is null Or Inning is null";
		} else {
			inning = matchAllData.getMatch().getInning().stream().filter(inn -> inn.getIsCurrentInning().equalsIgnoreCase(CricketUtil.YES)).findAny().orElse(null);
			if(inning == null) {
				return "bugsCurrPartnership Inning is null";
			}
			
//			partnership = inning.getPartnerships().stream().filter(pship -> pship.getPartnershipNumber() == 
//					inning.getPartnerships().get(inning.getPartnerships().size()-1).getPartnershipNumber()).findAny().orElse(null);
			
			List<Partnership > part = CricketFunctions.ConcussedPartnership(matchAllData.getMatch(), inning.getInningNumber());
			partnership = part.get(part.size()-1);
			
//			partnership = part.stream().filter(pship -> pship.getPartnershipNumber() == 
//					part.get(part.size()-1).getPartnershipNumber()).findAny().orElse(null);
			
			for(BattingCard bc : inning.getBattingCard()) {
				if(bc.getPlayerId() == partnership.getFirstBatterNo()) {
					partnership.setFirstPlayer(bc.getPlayer());
				}
				if(bc.getPlayerId() == partnership.getSecondBatterNo()) {
					partnership.setSecondPlayer(bc.getPlayer());
				}
			}
			if(partnership == null) {
				return "bugsCurrPartnership Partnership is null";
			}
			team = Teams.stream().filter(tm -> tm.getTeamId() == inning.getBattingTeamId()).findAny().orElse(null);
			if(team == null) {
				return "bugsCurrPartnership: Team id [" + inning.getBattingTeamId() + "] from database is returning NULL";
			}
		}
		if(PopulateBugBody(WhichSide, whatToProcess,matchAllData) == Constants.OK) {
			status = Constants.OK;
		}
		return status;
	}
	public String bugMultiPartnership(String whatToProcess,MatchAllData matchAllData,int WhichSide) throws Exception {
		
		if (matchAllData == null || matchAllData.getMatch() == null || matchAllData.getMatch().getInning() == null) {
			return "bugMultiPartnership match is null Or Inning is null";
		} else {
			inning = matchAllData.getMatch().getInning().stream().filter(inn -> inn.getInningNumber() == Integer.valueOf(whatToProcess.split(",")[1])).findAny().orElse(null);
			if(inning == null) {
				return "bugMultiPartnership Inning is null";
			}
			
			//partnership = inning.getPartnerships().stream().filter(pship -> pship.getPartnershipNumber() == Integer.valueOf(whatToProcess.split(",")[2])).findAny().orElse(null);
			List<Partnership > part = CricketFunctions.ConcussedPartnership(matchAllData.getMatch(), inning.getInningNumber());
			partnership = part.get(Integer.valueOf(whatToProcess.split(",")[2]) - 1);
			partnership1 = part.get(part.size()-1);
						
			for(BattingCard bc : inning.getBattingCard()) {
				if(bc.getPlayerId() == partnership.getFirstBatterNo()) {
					partnership.setFirstPlayer(bc.getPlayer());
				}
				if(bc.getPlayerId() == partnership.getSecondBatterNo()) {
					partnership.setSecondPlayer(bc.getPlayer());
				}
			}
			if(partnership == null) {
				return "bugMultiPartnership Partnership is null";
			}
			team = Teams.stream().filter(tm -> tm.getTeamId() == inning.getBattingTeamId()).findAny().orElse(null);
			if(team == null) {
				return "bugMultiPartnership: Team id [" + inning.getBattingTeamId() + "] from database is returning NULL";
			}
		}
		if(PopulateBugBody(WhichSide, whatToProcess,matchAllData) == Constants.OK) {
			status = Constants.OK;
		}
		return status;
	}
	public String bugsDB(String whatToProcess,int WhichSide,MatchAllData matchAllData) {
		bug = this.bugs.stream().filter(bug -> bug.getBugId() == Integer.valueOf(whatToProcess.split(",")[2])).findAny().orElse(null);
		if(bug == null) {
			return "bugsDB: bug is returning NULL";
		}
		
		if(bug.getFlag() != null) {
			team = Teams.stream().filter(tm -> tm.getTeamName4().equalsIgnoreCase(bug.getFlag())).findAny().orElse(null);
			if(team == null) {
				return "bugsDB: Flag in database is returning NULL";
			}
		}
		
		if(PopulateBugBody(WhichSide, whatToProcess,matchAllData) == Constants.OK) {
			status = Constants.OK;
		}
		return status;
	}
	public String populateBowlScore(String whatToProcess,MatchAllData matchAllData,int WhichSide)
	{
		if (matchAllData == null || matchAllData.getMatch() == null || matchAllData.getMatch().getInning() == null) {
			status = "populateBowlScore match is null Or Inning is null";
		} else {
			inning = matchAllData.getMatch().getInning().stream().filter(inn -> inn.getInningNumber() == Integer.valueOf(whatToProcess.split(",")[1])).findAny().orElse(null);
			if(inning == null) {
				return "populateBowlScore Inning is null";
			}
			bowlingCard = inning.getBowlingCard().stream().filter(boc -> boc.getPlayerId() == Integer.valueOf(whatToProcess.split(",")[2])).findAny().orElse(null);
			if(bowlingCard == null) {
				return "populateBowlScore Bowling is null";
			}
			team = Teams.stream().filter(tm -> tm.getTeamId() == bowlingCard.getPlayer().getTeamId()).findAny().orElse(null);
			if(team == null) {
				return "populateBowlScore: Team id [" + bowlingCard.getPlayer().getTeamId() + "] from database is returning NULL";
			}
		}
		if(PopulateBugBody(WhichSide, whatToProcess,matchAllData) == Constants.OK) {
			status = Constants.OK;;
		}
		return status;		
	}
	
	public String populateBugTarget(String whatToProcess,MatchAllData matchAllData,int WhichSide)
	{
		if (matchAllData == null || matchAllData.getMatch() == null || matchAllData.getMatch().getInning() == null) {
			status = "populateBugTarget match is null Or Inning is null";
		} else {
			inning = matchAllData.getMatch().getInning().stream().filter(inn -> inn.getInningNumber() == Integer.valueOf(whatToProcess.split(",")[1]))
					.findAny().orElse(null);
				
			if(inning == null) {
				return "populateTarget: Current Inning NOT found in this match";
			}
			
//			if(inning.getInningNumber() == 1) {
//				return "populateTarget: Current Inning is 1";
//			}
		}
		if(PopulateBugBody(WhichSide, whatToProcess,matchAllData) == Constants.OK) {
			status = Constants.OK;
		}
		return status;
	}
	
	public String populateBatScore(String whatToProcess,MatchAllData matchAllData,int WhichSide)
	{
		if (matchAllData == null || matchAllData.getMatch() == null || matchAllData.getMatch().getInning() == null) {
			status = "populateBatScore match is null Or Inning is null";
		} else {
			inning = matchAllData.getMatch().getInning().stream().filter(inn -> inn.getInningNumber() == Integer.valueOf(whatToProcess.split(",")[1])).findAny().orElse(null);
			if(inning == null) {
				return "populateBatScore Inning is null";
			}
			battingCard = inning.getBattingCard().stream().filter(bc -> bc.getPlayerId() == Integer.valueOf(whatToProcess.split(",")[2])).findAny().orElse(null);
			if(battingCard == null) {
				return "populateBatScore Batting is null";
			}
			team = Teams.stream().filter(tm -> tm.getTeamId() == battingCard.getPlayer().getTeamId()).findAny().orElse(null);
			if(team == null) {
				return "populateBatScore: Team id [" + battingCard.getPlayer().getTeamId() + "] from database is returning NULL";
			}
		}
		if(PopulateBugBody(WhichSide, whatToProcess,matchAllData) == Constants.OK) {
			status = Constants.OK;
		}
		return status;
	}
	public String populateBugHighlight(String whatToProcess,MatchAllData matchAllData,int WhichSide, int whichInning)
	{
		if (matchAllData == null || matchAllData.getMatch() == null || matchAllData.getMatch().getInning() == null) {
			status = "populateBatScore match is null Or Inning is null";
		} else {
			inning = matchAllData.getMatch().getInning().stream().filter(inn -> inn.getInningNumber() == whichInning).findAny().orElse(null);
			if(inning == null) {
				return "populateBatScore Inning is null";
			}
		}
		if(PopulateBugBody(WhichSide, whatToProcess,matchAllData) == Constants.OK) {
			status = Constants.OK;
		}
		return status;
	}
	public String bugsToss(String whatToProcess, MatchAllData matchAllData, int WhichSide) {
		if (matchAllData == null || matchAllData.getMatch() == null ||
			matchAllData.getMatch().getInning() == null|| matchAllData.getSetup() == null) {
			status = "BugsToss match is null Or Inning is null";
		} else {
			inning = matchAllData.getMatch().getInning().stream().filter(inn -> inn.getIsCurrentInning().equalsIgnoreCase(CricketUtil.YES)).
					findAny().orElse(null);
			
			switch (config.getBroadcaster()) {
			case Constants.TRI_SERIES:  case Constants.MT20: case Constants.BAN_AFG_SERIES: case Constants.ACC:
				team = Teams.stream().filter(tm->tm.getTeamName4().equalsIgnoreCase(whatToProcess.split(",")[2].split("-")[0])).findAny().orElse(null);
				break;
			case Constants.TG20:
				System.out.println(whatToProcess);
				team = Teams.stream().filter(tm->tm.getTeamName4().equalsIgnoreCase(whatToProcess.split(",")[2])).findAny().orElse(null);
				break;
			default:
				team = Teams.stream().filter(tm->tm.getTeamName4().equalsIgnoreCase(whatToProcess.split(",")[2].split("-")[0])).findAny().orElse(null);
				break;
			}
		}
		if(PopulateBugBody(WhichSide, whatToProcess,matchAllData) == Constants.OK) {
			status = Constants.OK;
		}
		return status;
	}
	public String populatebugPowerplay(String whatToProcess,int WhichSide, MatchAllData matchAllData) {
		if (matchAllData == null || matchAllData.getMatch() == null ||
			matchAllData.getMatch().getInning() == null|| matchAllData.getSetup() == null) {
			status = "BugPowerplay match is null Or Inning is null";
		} else {
			
			switch (config.getBroadcaster().toUpperCase()) {
			case Constants.TRI_SERIES:  case Constants.MT20: case Constants.ACC:
				inning = matchAllData.getMatch().getInning().stream().
				filter(inn -> inn.getInningNumber() == Integer.valueOf(whatToProcess.split(",")[2])).findAny().orElse(null);
				break;
			case Constants.BAN_AFG_SERIES: //case Constants.ACC:
				inning = matchAllData.getMatch().getInning().stream().
				filter(inn -> inn.getInningNumber() == Integer.valueOf(whatToProcess.split(",")[1])).findAny().orElse(null);
				break;	
			default:
				inning = matchAllData.getMatch().getInning().stream().filter(inn -> inn.getIsCurrentInning().equalsIgnoreCase(CricketUtil.YES)).
				findAny().orElse(null);
				break;
			}
		}
		
		if(PopulateBugBody(WhichSide, whatToProcess,matchAllData) == Constants.OK) {
			status = Constants.OK;
		}
		return status;
	}

	public String populateBugResult(String whatToProcess, MatchAllData matchAllData, int whichSide) {
		if (matchAllData == null || matchAllData.getMatch() == null || matchAllData.getMatch().getInning() == null|| matchAllData.getSetup() == null) {
				status = "populateBugResult match is null Or Inning is null";
		}else {
			inning = matchAllData.getMatch().getInning().stream().filter(inn -> inn.getIsCurrentInning().equalsIgnoreCase(CricketUtil.YES)).findAny().orElse(null);
			if(inning == null) {
				return "populateBugResult match is null Or Inning is null";
			}else if(inning.getInningNumber() == 1) {
				return "populateBugResult only work in 2nd Inning";
			}
		}
		if(PopulateBugBody(whichSide, whatToProcess,matchAllData) == Constants.OK) {
			status = Constants.OK;
		}
		return status;
	}
	
	public String populatePerformanceBug(String whatToProcess, MatchAllData matchAllData, int whichSide) {
		if (matchAllData == null || matchAllData.getMatch() == null ||
				matchAllData.getMatch().getInning() == null|| matchAllData.getSetup() == null) {
				status = "populateBugResult match is null Or Inning is null";
			} else {
				inning = matchAllData.getMatch().getInning().stream().
						filter(inn -> inn.getIsCurrentInning().equalsIgnoreCase(CricketUtil.YES)).
						findAny().orElse(null);
			}
			if(PopulateBugBody(whichSide, whatToProcess,matchAllData) == Constants.OK) {
				status = Constants.OK;
			}
			return status;
	}
	public String bugsDismissal(String whatToProcess,MatchAllData matchAllData,int WhichSide) {
		
		if (matchAllData == null || matchAllData.getMatch() == null || matchAllData.getMatch().getInning() == null) {
			return "bugsDismissal match is null Or Inning is null";
		} else {
			inning = matchAllData.getMatch().getInning().stream().filter(inn -> inn.getInningNumber() == Integer.valueOf(whatToProcess.split(",")[1])).findAny().orElse(null);
			if(inning == null) {
				return "bugsDismissal Inning is null";
			}
			battingCard = inning.getBattingCard().stream().filter(bc -> bc.getPlayerId() == Integer.valueOf(whatToProcess.split(",")[2])).findAny().orElse(null);
			if(battingCard == null) {
				return "bugsDismissal Batting Card is null";
			}
			team = Teams.stream().filter(tm -> tm.getTeamId() == battingCard.getPlayer().getTeamId()).findAny().orElse(null);
			if(team == null) {
				return "bugsDismissal: Team id [" + battingCard.getPlayer().getTeamId() + "] from database is returning NULL";
			}
			
			sponsor = whatToProcess.split(",")[3];
		}
		if(PopulateBugBody(WhichSide, whatToProcess,matchAllData) == Constants.OK) {
			status = Constants.OK;
		}
		return status;
	}
	public String populateBugReview(String whatToProcess,int WhichSide,MatchAllData matchAllData) throws IOException {
		
		reviewData = whatToProcess.split(",")[3].toUpperCase();
		team = Teams.stream().filter(tm->tm.getTeamId() == Integer.valueOf(whatToProcess.split(",")[2])).findAny().orElse(null);
		if(team == null) {
			return "Can't find team of the player";
		}
		if(PopulateBugBody(WhichSide, whatToProcess,matchAllData) == Constants.OK) {
			status = Constants.OK;
		}
		return status;
	}
	public String populatePopup(String whatToProcess, int whichSide, MatchAllData matchAllData){
		inning = matchAllData.getMatch().getInning().stream().filter(inn -> inn.getInningNumber() == Integer.valueOf(whatToProcess.split(",")[1])).findAny().orElse(null);
		if(inning == null) {
			return "inning is null";
		}
		switch (whatToProcess.split(",")[0]) {
		case "Control_Shift_U":
			battingCard = inning.getBattingCard().stream().filter(bc->bc.getPlayerId() == Integer.valueOf(whatToProcess.split(",")[2])).findAny().orElse(null);
			if(battingCard == null) {
				return ": player [" + battingCard.getPlayer().getFull_name() + "] is not present in batting card";
			}
			break;

		case "Control_Shift_V":
			bowlingCard = inning.getBowlingCard().stream().filter(boc->boc.getPlayerId() == Integer.valueOf(whatToProcess.split(",")[2])).findAny().orElse(null);
			if(bowlingCard == null) {
				return ": player [" + bowlingCard.getPlayer().getFull_name() + "] is not present in bowlingCard";
			}
			break;
		}
		if(PopulateBugBody(whichSide, whatToProcess,matchAllData) == Constants.OK) {
			status = Constants.OK;
		}
		return status;
	}
	public String populatePopupChangeOn(String whatToProcess, int whichSide, MatchAllData matchAllData){
		inning = matchAllData.getMatch().getInning().stream().filter(inn -> inn.getInningNumber() == Integer.valueOf(whatToProcess.split(",")[1])).findAny().orElse(null);
		if(inning == null) {
			return "inning is null";
		}
		switch (whatToProcess.split(",")[0]) {
		case "Control_Shift_U_change_on":
			battingCard = inning.getBattingCard().stream().filter(bc->bc.getPlayerId() == Integer.valueOf(whatToProcess.split(",")[2])).findAny().orElse(null);
			if(battingCard == null) {
				return ": player [" + battingCard.getPlayer().getFull_name() + "] is not present in batting card";
			}
			break;

		case "Control_Shift_V_change_on":
			bowlingCard = inning.getBowlingCard().stream().filter(boc->boc.getPlayerId() == Integer.valueOf(whatToProcess.split(",")[2])).findAny().orElse(null);
			if(bowlingCard == null) {
				return ": player [" + bowlingCard.getPlayer().getFull_name() + "] is not present in bowlingCard";
			}
			break;
		}
		if(populatePopupSubSideData(whichSide, whatToProcess,matchAllData) == Constants.OK) {
			status = Constants.OK;
		}
		return status;
	}
	public String PopulateBugBody(int WhichSide, String whatToProcess,MatchAllData matchAllData) {
		
		switch (config.getBroadcaster().toUpperCase()) {
		case Constants.BAN_AFG_SERIES: case Constants.ACC:
			switch (whatToProcess.split(",")[0]) {
			case "5":
				
				switch (config.getBroadcaster().toUpperCase()) {
				case Constants.BAN_AFG_SERIES:
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Tournament_Sixes$POP_ALL$band$img_Base2*TEXTURE*IMAGE SET " 
							+ Constants.BAN_AFG_SERIES_BASE2 + "EVENT" +"\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Tournament_Sixes$POP_ALL$base$img_base1*TEXTURE*IMAGE SET " 
							+ Constants.BAN_AFG_SERIES_BASE1 + "EVENT" +"\0", print_writers);
					break;
				}
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Tournament_Sixes$POP_ALL$Sponsor$Sponsor$Rectangle*ACTIVE SET 1\0", print_writers);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Tournament_Sixes$band$txt_Header*GEOM*TEXT SET FOURS - THIS MATCH\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Tournament_Sixes$POP_ALL$Select_Sponsor*FUNCTION*Omo*vis_con SET 1\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Tournament_Sixes$Data$Side_1$txt_Unit*GEOM*TEXT SET " + this_data_str.get(0).split(",")[2] + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Tournament_Sixes$Data$Side_1$txt_Ten*GEOM*TEXT SET " + this_data_str.get(0).split(",")[1] + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Tournament_Sixes$Data$Side_1$txt_Hundread*GEOM*TEXT SET " + this_data_str.get(0).split(",")[0] + "\0", print_writers);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Tournament_Sixes$Data$Side_2$txt_Unit*GEOM*TEXT SET " + this_data_str.get(1).split(",")[2] + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Tournament_Sixes$Data$Side_2$txt_Ten*GEOM*TEXT SET " + this_data_str.get(1).split(",")[1] + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Tournament_Sixes$Data$Side_2$txt_Hundread*GEOM*TEXT SET " + this_data_str.get(1).split(",")[0] + "\0", print_writers);
				
				if(Integer.valueOf(this_data_str.get(0).split(",")[0]) > 0) {
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Tournament_Sixes$Data$Side_1$txt_Hundread*ACTIVE SET 1\0", print_writers);
				}else {
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Tournament_Sixes$Data$Side_1$txt_Hundread*ACTIVE SET 0\0", print_writers);
				}
				
				if(Integer.valueOf(this_data_str.get(1).split(",")[0]) > 0) {
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Tournament_Sixes$Data$Side_2$txt_Hundread*ACTIVE SET 1\0", print_writers);
				}else {
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Tournament_Sixes$Data$Side_2$txt_Hundread*ACTIVE SET 0\0", print_writers);
				}
				
				break;	
			case ";":
				switch (config.getBroadcaster().toUpperCase()) {
				case Constants.BAN_AFG_SERIES:
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Tournament_Sixes$POP_ALL$band$img_Base2*TEXTURE*IMAGE SET " 
							+ Constants.BAN_AFG_SERIES_BASE2 + "EVENT" +"\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Tournament_Sixes$POP_ALL$base$img_base1*TEXTURE*IMAGE SET " 
							+ Constants.BAN_AFG_SERIES_BASE1 + "EVENT" +"\0", print_writers);
					break;
				}
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Tournament_Sixes$POP_ALL$Sponsor$Sponsor$Rectangle*ACTIVE SET 1\0", print_writers);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Tournament_Sixes$band$txt_Header*GEOM*TEXT SET SIXES - THIS MATCH\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Tournament_Sixes$POP_ALL$Select_Sponsor*FUNCTION*Omo*vis_con SET 2\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Tournament_Sixes$Data$Side_1$txt_Unit*GEOM*TEXT SET "+this_data_str.get(0).split(",")[2] + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Tournament_Sixes$Data$Side_1$txt_Ten*GEOM*TEXT SET "+this_data_str.get(0).split(",")[1] + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Tournament_Sixes$Data$Side_1$txt_Hundread*GEOM*TEXT SET "+this_data_str.get(0).split(",")[0] + "\0", print_writers);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Tournament_Sixes$Data$Side_2$txt_Unit*GEOM*TEXT SET "+this_data_str.get(1).split(",")[2] + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Tournament_Sixes$Data$Side_2$txt_Ten*GEOM*TEXT SET "+this_data_str.get(1).split(",")[1] + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Tournament_Sixes$Data$Side_2$txt_Hundread*GEOM*TEXT SET "+this_data_str.get(1).split(",")[0] + "\0", print_writers);
				
				if(Integer.valueOf(this_data_str.get(0).split(",")[0]) > 0) {
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Tournament_Sixes$Data$Side_1$txt_Hundread*ACTIVE SET 1\0", print_writers);
				}else {
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Tournament_Sixes$Data$Side_1$txt_Hundread*ACTIVE SET 0\0", print_writers);
				}
				
				if(Integer.valueOf(this_data_str.get(1).split(",")[0]) > 0) {
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Tournament_Sixes$Data$Side_2$txt_Hundread*ACTIVE SET 1\0", print_writers);
				}else {
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Tournament_Sixes$Data$Side_2$txt_Hundread*ACTIVE SET 0\0", print_writers);
				}
				
				break;
			case "Control_4": case "Control_5":
				
				switch (whatToProcess.split(",")[0]) {
				case "Control_4":
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Tournament_Sixes$POP_ALL$spon01*ACTIVE SET 1\0", print_writers);
					break;
				case "Control_5":
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Tournament_Sixes$POP_ALL$spon01*ACTIVE SET 1\0", print_writers);
					break;
				}
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Tournament_Sixes$POP_ALL$Sponsor$Sponsor$Rectangle*ACTIVE SET 1\0", print_writers);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Tournament_Sixes$POP_ALL$Select_Sponsor*FUNCTION*Omo*vis_con SET 1\0", print_writers);
				switch (config.getBroadcaster().toUpperCase()) {
				case Constants.BAN_AFG_SERIES:
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Tournament_Sixes$POP_ALL$band$img_Base2*TEXTURE*IMAGE SET " 
							+ Constants.BAN_AFG_SERIES_BASE2 + "EVENT" +"\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Tournament_Sixes$POP_ALL$base$img_base1*TEXTURE*IMAGE SET " 
							+ Constants.BAN_AFG_SERIES_BASE1 + "EVENT" +"\0", print_writers);
					break;
				}
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Tournament_Sixes$band$txt_Header*GEOM*TEXT SET THIS SERIES FOURS\0", print_writers);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Tournament_Sixes$Data$Side_1$txt_Unit*GEOM*TEXT SET "+this_data_str.get(0).split(",")[2] + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Tournament_Sixes$Data$Side_1$txt_Ten*GEOM*TEXT SET "+this_data_str.get(0).split(",")[1] + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Tournament_Sixes$Data$Side_1$txt_Hundread*GEOM*TEXT SET "+this_data_str.get(0).split(",")[0] + "\0", print_writers);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Tournament_Sixes$Data$Side_2$txt_Unit*GEOM*TEXT SET "+this_data_str.get(1).split(",")[2] + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Tournament_Sixes$Data$Side_2$txt_Ten*GEOM*TEXT SET "+this_data_str.get(1).split(",")[1] + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Tournament_Sixes$Data$Side_2$txt_Hundread*GEOM*TEXT SET "+this_data_str.get(1).split(",")[0] + "\0", print_writers);
				
				if(Integer.valueOf(this_data_str.get(0).split(",")[0]) > 0) {
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Tournament_Sixes$Data$Side_1$txt_Hundread*ACTIVE SET 1\0", print_writers);
				}else {
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Tournament_Sixes$Data$Side_1$txt_Hundread*ACTIVE SET 0\0", print_writers);
				}
				
				if(Integer.valueOf(this_data_str.get(1).split(",")[0]) > 0) {
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Tournament_Sixes$Data$Side_2$txt_Hundread*ACTIVE SET 1\0", print_writers);
				}else {
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Tournament_Sixes$Data$Side_2$txt_Hundread*ACTIVE SET 0\0", print_writers);
				}
				
				break;	
			case "6": case "Control_7":
				
				switch (whatToProcess.split(",")[0]) {
				case "6":
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Tournament_Sixes$POP_ALL$spon01*ACTIVE SET 1\0", print_writers);
					break;
				case "Control_7":
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Tournament_Sixes$POP_ALL$spon01*ACTIVE SET 1\0", print_writers);
					break;
				}
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Tournament_Sixes$POP_ALL$Sponsor$Sponsor$Rectangle*ACTIVE SET 1\0", print_writers);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Tournament_Sixes$POP_ALL$Select_Sponsor*FUNCTION*Omo*vis_con SET 2\0", print_writers);
				switch (config.getBroadcaster().toUpperCase()) {
				case Constants.BAN_AFG_SERIES:
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Tournament_Sixes$POP_ALL$band$img_Base2*TEXTURE*IMAGE SET " 
							+ Constants.BAN_AFG_SERIES_BASE2 + "EVENT" +"\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Tournament_Sixes$POP_ALL$base$img_base1*TEXTURE*IMAGE SET " 
							+ Constants.BAN_AFG_SERIES_BASE1 + "EVENT" +"\0", print_writers);
					break;
				}
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Tournament_Sixes$band$txt_Header*GEOM*TEXT SET THIS SERIES SIXES\0", print_writers);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Tournament_Sixes$Data$Side_1$txt_Unit*GEOM*TEXT SET "+this_data_str.get(0).split(",")[2] + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Tournament_Sixes$Data$Side_1$txt_Ten*GEOM*TEXT SET "+this_data_str.get(0).split(",")[1] + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Tournament_Sixes$Data$Side_1$txt_Hundread*GEOM*TEXT SET "+this_data_str.get(0).split(",")[0] + "\0", print_writers);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Tournament_Sixes$Data$Side_2$txt_Unit*GEOM*TEXT SET "+this_data_str.get(1).split(",")[2] + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Tournament_Sixes$Data$Side_2$txt_Ten*GEOM*TEXT SET "+this_data_str.get(1).split(",")[1] + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Tournament_Sixes$Data$Side_2$txt_Hundread*GEOM*TEXT SET "+this_data_str.get(1).split(",")[0] + "\0", print_writers);
				
				if(Integer.valueOf(this_data_str.get(0).split(",")[0]) > 0) {
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Tournament_Sixes$Data$Side_1$txt_Hundread*ACTIVE SET 1\0", print_writers);
				}else {
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Tournament_Sixes$Data$Side_1$txt_Hundread*ACTIVE SET 0\0", print_writers);
				}
				
				if(Integer.valueOf(this_data_str.get(1).split(",")[0]) > 0) {
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Tournament_Sixes$Data$Side_2$txt_Hundread*ACTIVE SET 1\0", print_writers);
				}else {
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Tournament_Sixes$Data$Side_2$txt_Hundread*ACTIVE SET 0\0", print_writers);
				}
				
				break;
			case "Shift_C":
				
				switch (config.getBroadcaster().toUpperCase()) {
				case Constants.BAN_AFG_SERIES:
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Six_Distance$img_Base2*TEXTURE*IMAGE SET " 
							+ Constants.BAN_AFG_SERIES_BASE2 + inning.getBatting_team().getTeamBadge() +"\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Six_Distance$img_Text2*TEXTURE*IMAGE SET " 
							+ Constants.BAN_AFG_SERIES_TEXT2 + inning.getBatting_team().getTeamBadge() +"\0", print_writers);
					break;
				}
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Six_Distance$Sponsor$Sponsor*FUNCTION*Omo*vis_con SET 0\0",print_writers);

				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Six_Distance$TextAll$img_Text2$txt_Header*GEOM*TEXT SET SIX DISTANCE\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Six_Distance$TextAll$img_Text2$txt_Distance*ANIMATION*KEY*$S*VALUE SET " 
						+ whatToProcess.split(",")[2] + "\0", print_writers);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Six_Distance$TextAll$img_Text2$Meters*GEOM*TEXT SET METERS\0", print_writers);
				
				break;
				
			case "Control_Shift_F3": 
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Sponsor$Sponsor*FUNCTION*Omo*vis_con SET 0 \0",print_writers);

				String summary = "",team_name = "";
				if(matchAllData.getSetup().getMatchType().equalsIgnoreCase(CricketUtil.SUPER_OVER) && matchAllData.getSetup().getMaxOvers() == 1) {
					
					team_name = inning.getBatting_team().getTeamName1(); 
					summary = "NEED " + CricketFunctions.GetTargetData(matchAllData).getTargetRuns() + " RUNS TO WIN FROM " + (matchAllData.getSetup().getMaxOvers()*6) + " BALLS";
					
				}else {
					if(matchAllData.getSetup().getTargetOvers() != null && matchAllData.getSetup().getTargetRuns() != 0) {
						if(matchAllData.getSetup().getTargetOvers() != null && !matchAllData.getSetup().getTargetOvers().isEmpty()) {
							
							team_name = inning.getBatting_team().getTeamName1();
							summary = "NEED " + CricketFunctions.GetTargetData(matchAllData).getTargetRuns() + " RUNS TO WIN FROM " + CricketFunctions.GetTargetData(matchAllData).getTargetOvers() 
									+ " OVERS" + (matchAllData.getSetup().getTargetType() != null && !matchAllData.getSetup().getTargetType().isEmpty() ? " " + matchAllData.getSetup().getTargetType() : "");
						}
					}else {
						team_name = inning.getBatting_team().getTeamName1();
						summary = "NEED " + CricketFunctions.GetTargetData(matchAllData).getTargetRuns() + " RUNS TO WIN FROM " + CricketFunctions.GetTargetData(matchAllData).getTargetOvers() + " OVERS";
					}
				}
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Select*FUNCTION*Omo*vis_con SET 0 \0",print_writers);
				
				switch (config.getBroadcaster().toUpperCase()) {
				case Constants.BAN_AFG_SERIES:
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Select$Single$All$BugLogo$img_Badges*TEXTURE*IMAGE SET "
							+ Constants.BAN_AFG_SERIES_LOGO + inning.getBatting_team().getTeamBadge() + "\0",print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Select$Single$All$BugLogo$img_Badges02*TEXTURE*IMAGE SET "
							+ Constants.BAN_AFG_SERIES_LOGO + inning.getBatting_team().getTeamBadge() + "\0",print_writers);
					break;
				case Constants.ACC:
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Select$Single$All$BugLogo$img_Badges*TEXTURE*IMAGE SET "
							+ Constants.ACC_FLAG + inning.getBatting_team().getTeamBadge() + "\0",print_writers);
					break;
				}

				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Select$Single$All$DataGrpAll$PlayerNameGrp$Info01"
						+ "*GEOM*TEXT SET " + "" + "\0",print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Select$Single$All$DataGrpAll$PlayerNameGrp$Info02"
						+ "*GEOM*TEXT SET " + " " + "\0",print_writers);

				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Select$Single$All$DataGrpAll$PlayerNameGrp$group$Info03"
						+ "*GEOM*TEXT SET " + team_name + " " + summary + "\0",print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Select$Single$All$DataGrpAll$PlayerNameGrp$group$Info04"
						+ "*GEOM*TEXT SET " + "" + "\0",print_writers);
				
				break;
			case "Control_Shift_R":
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Sponsor$Sponsor*FUNCTION*Omo*vis_con SET 0 \0",print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Select*FUNCTION*Omo*vis_con SET 0 \0",print_writers);

				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Select$Single$All$DataGrpAll$PlayerNameGrp$Info01"
						+ "*GEOM*TEXT SET " + "" + "\0",print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Select$Single$All$DataGrpAll$PlayerNameGrp$Info02"
						+ "*GEOM*TEXT SET " + " " + "\0",print_writers);

				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Select$Single$All$DataGrpAll$PlayerNameGrp$group$"
						+ "Info04*GEOM*TEXT SET " + "" + "\0",print_writers);
				
				switch (config.getBroadcaster().toUpperCase()) {
				case Constants.BAN_AFG_SERIES:
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Select$Single$All$BugLogo$img_Badges*TEXTURE*IMAGE SET "
							+ Constants.BAN_AFG_SERIES_LOGO + inning.getBatting_team().getTeamBadge() + "\0",print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Select$Single$All$BugLogo$img_Badges02*TEXTURE*IMAGE SET "
							+ Constants.BAN_AFG_SERIES_LOGO + inning.getBatting_team().getTeamBadge() + "\0",print_writers);
					break;
				case Constants.ACC:
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Select$Single$All$BugLogo$img_Badges*TEXTURE*IMAGE SET "
							+ Constants.ACC_FLAG + inning.getBatting_team().getTeamBadge() + "\0",print_writers);
					break;
				}
				
				String matchResult = null;
				matchResult = CricketFunctions.GenerateMatchSummaryStatus(inning.getInningNumber(), matchAllData, CricketUtil.FULL, "", 
						config.getBroadcaster(), false).getTargetOrResult().toUpperCase();
				
				if(matchAllData.getSetup().getMatchType().equalsIgnoreCase(CricketUtil.SUPER_OVER)) {
					if(matchResult.contains("tied")) {
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Select$Single$All$BugLogo$img_Badges*TEXTURE*IMAGE SET "
								+ Constants.ACC_FLAG + "TLogo" + "\0",print_writers);
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Select$Single$All$DataGrpAll$PlayerNameGrp$group$"
								+ "Info03*GEOM*TEXT SET " + "SUPER OVER TIED, WINNER WILL BE DECIDED BY ANOTHER SUPER OVER" + "\0",print_writers);
					}else {
						if(matchAllData.getSetup().getMatchType().equalsIgnoreCase(CricketUtil.SUPER_OVER)) {
							if(CricketFunctions.GetTargetData(matchAllData).getRemaningRuns() <= 0) {
								CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Select$Single$All$DataGrpAll$PlayerNameGrp$group$"
										+ "Info03*GEOM*TEXT SET " + matchAllData.getMatch().getInning().get(1).getBatting_team().getTeamName1() + " WIN THE SUPER OVER" + "\0",print_writers);
								
							}else if(matchAllData.getMatch().getInning().get(1).getTotalWickets() >= 10 || 
									matchAllData.getMatch().getInning().get(1).getTotalOvers() >= matchAllData.getSetup().getMaxOvers()) {
								CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Select$Single$All$DataGrpAll$PlayerNameGrp$group$Info03"
										+ "*GEOM*TEXT SET " + matchAllData.getMatch().getInning().get(1).getBatting_team().getTeamName1() + " WIN THE SUPER OVER" + "\0",print_writers);
							}
							
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Select$Single$All$BugLogo$img_Badges*TEXTURE*IMAGE SET "
									+ Constants.ACC_FLAG + matchAllData.getMatch().getInning().get(1).getBatting_team().getTeamBadge() + "\0",print_writers);
							
						}else {
							for(Team tm : cricketService.getTeams()) {
								if(matchResult.contains(tm.getTeamName1())) {
									CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Select$Single$All$BugLogo$img_Badges*TEXTURE*IMAGE SET "
											+ Constants.ACC_FLAG + tm.getTeamBadge() + "\0",print_writers);
								}
							}
							
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Select$Single$All$DataGrpAll$PlayerNameGrp$group$Info03"
									+ "*GEOM*TEXT SET " + matchResult.split("WIN")[0] + "WIN" + matchResult.split("WIN")[1] + "\0",print_writers);
						}
					}
				}else {
					if(matchResult.contains("tied")) {
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Select$Single$All$BugLogo$img_Badges*TEXTURE*IMAGE SET "
								+ Constants.ACC_FLAG + "TLogo" + "\0",print_writers);
						
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Select$Single$All$DataGrpAll$PlayerNameGrp$group$Info03"
								+ "*GEOM*TEXT SET " + "MATCH TIED, WINNER WILL BE DECIDED BY SUPER OVER" + "\0",print_writers);
					}else {
						for(Team tm : cricketService.getTeams()) {
							if(matchResult.contains(tm.getTeamName1())) {
								CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Select$Single$All$BugLogo$img_Badges*TEXTURE*IMAGE SET "
										+ Constants.ACC_FLAG + tm.getTeamBadge() + "\0",print_writers);
							}
						}
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Select$Single$All$DataGrpAll$PlayerNameGrp$group$Info03"
								+ "*GEOM*TEXT SET " + matchResult.split("WIN")[0] + "WIN" + matchResult.split("WIN")[1] + "\0",print_writers);
					}
				}
				break;
			case "Control_Shift_J":
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Sponsor$Sponsor*FUNCTION*Omo*vis_con SET 0 \0",print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Select*FUNCTION*Omo*vis_con SET 3 \0",print_writers);
				
				switch (config.getBroadcaster().toUpperCase()) {
				case Constants.BAN_AFG_SERIES:
					if(performanceBug.getSponsor() != null) {
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Select$Double$All$BandsAll$img_Base2*TEXTURE*IMAGE SET "
								+ Constants.BAN_AFG_SERIES_BASE2 + performanceBug.getSponsor() + " \0",print_writers);
					}else {
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Select$Double$All$BandsAll$img_Base2*TEXTURE*IMAGE SET "
								+ Constants.BAN_AFG_SERIES_BASE2 + "EVENT" + " \0",print_writers);
					}
					break;
				case Constants.ACC:
					if(bug.getSponsor() != null) {	
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Select$Double$All$Logo$img_Badges"
								+ "*TEXTURE*IMAGE SET " + Constants.ACC_FLAG + performanceBug.getSponsor() + " \0",print_writers);
					}else {
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Select$Double$All$Logo$img_Badges"
								+ "*TEXTURE*IMAGE SET " + Constants.ACC_FLAG + "TLogo_White" + " \0",print_writers);
					}
					break;
				}
				
				if(performanceBug.getPlayerName() != null) {
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Select$Double$All$DataGrpAll$PlayerNameGrp$Info01*GEOM*TEXT SET "
							+ performanceBug.getPlayerName() + "\0",print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Select$Double$All$DataGrpAll$PlayerNameGrp$Info02*GEOM*TEXT SET "
							+ "" + "\0",print_writers);
				}else {
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Select$Double$All$DataGrpAll$PlayerNameGrp$Info01*GEOM*TEXT SET "
							+ "" + "\0",print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Select$Double$All$DataGrpAll$PlayerNameGrp$Info02*GEOM*TEXT SET "
							+ "" + "\0",print_writers);
				}
				
				if(performanceBug.getSubheader() != null) {
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Select$Double$All$DataGrpAll$PlayerNameGrp$Info03*GEOM*TEXT SET "
							+ performanceBug.getSubheader() + "\0",print_writers);				
				}else {
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Select$Double$All$DataGrpAll$PlayerNameGrp$Info03*GEOM*TEXT SET "
							+ "" + "\0",print_writers);
				}
				
				if(performanceBug.getScore() != null) {
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Select$Double$All$DataGrpAll$PlayerNameGrp$group$Info04*GEOM*TEXT SET "
							+ performanceBug.getScore() + "\0",print_writers);
				}else {
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Select$Double$All$DataGrpAll$PlayerNameGrp$group$Info04*GEOM*TEXT SET "
							+ "" + "\0",print_writers);
				}
				
				if(performanceBug.getText4() != null) {
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Select$Double$All$DataGrpAll$PlayerNameGrp$group$Info05*GEOM*TEXT SET "
							+ performanceBug.getText4() + "\0",print_writers);
				}else {
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Select$Double$All$DataGrpAll$PlayerNameGrp$group$Info05*GEOM*TEXT SET "
							+ "" + "\0",print_writers);
				}
				
				break;
			case "Control_Shift_U":
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$POP_UP$band$img_Text2$txt_Header*GEOM*TEXT SET " + battingCard.getPlayer().getFull_name() + "\0",print_writers);
				
				switch (config.getBroadcaster().toUpperCase()) {
				case Constants.BAN_AFG_SERIES:
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$POP_UP$band$img_Text2*TEXTURE*IMAGE SET " + Constants.BAN_AFG_SERIES_TEXT2 + inning.getBatting_team().getTeamBadge() + "\0",print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$POP_UP$band$img_Base2*TEXTURE*IMAGE SET "+Constants.BAN_AFG_SERIES_BASE2 + inning.getBatting_team().getTeamBadge() + "\0",print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$POP_UP$Badge$img_Base2*TEXTURE*IMAGE SET " + Constants.BAN_AFG_SERIES_BASE2 + inning.getBatting_team().getTeamBadge() + "\0",print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$POP_UP$Badge$img_Badges*TEXTURE*IMAGE SET " + Constants.BAN_AFG_SERIES_LOGO + inning.getBatting_team().getTeamBadge() + "\0",print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$POP_UP$Badge$img_Badges02*TEXTURE*IMAGE SET " + Constants.BAN_AFG_SERIES_LOGO + inning.getBatting_team().getTeamBadge() + "\0",print_writers);
					break;
				case Constants.ACC:
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$POP_UP$Badge$select_Flag*FUNCTION*Omo*vis_con SET 0\0",print_writers);
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$POP_UP$Badge$img_Badges*TEXTURE*IMAGE SET " + Constants.ACC_FLAG + inning.getBatting_team().getTeamBadge() + "\0",print_writers);
					break;
				}
				
				switch (whatToProcess.split(",")[3].toUpperCase()) {
				case "STRIKERATE":
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$POP_UP$Sub_Header$Side" + WhichSide + "$StatHead1*GEOM*TEXT SET " + "STRIKE RATE"+"\0",print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$POP_UP$ScoreGrp$Side" + WhichSide + "$Seperator*ACTIVE SET 0\0",print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$POP_UP$ScoreGrp$Side" + WhichSide + "$Score*GEOM*TEXT SET " + 
							CricketFunctions.generateStrikeRate(battingCard.getRuns(), battingCard.getBalls(), 1) + "\0",print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$POP_UP$ScoreGrp$Side" + WhichSide + "$Balls*GEOM*TEXT SET \0",print_writers);
					break;

				case "SCORE":
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$POP_UP$Sub_Header$Side" + WhichSide + "$StatHead1*GEOM*TEXT SET " + battingCard.getBalls() + 
							" BALL" + CricketFunctions.Plural(battingCard.getBalls()).toUpperCase()+"\0",print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$POP_UP$ScoreGrp$Side" + WhichSide + "$Seperator*ACTIVE SET 0\0",print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$POP_UP$ScoreGrp$Side" + WhichSide + "$Score*GEOM*TEXT SET " 
							+ (battingCard.getStatus().equalsIgnoreCase(CricketUtil.NOT_OUT) ? battingCard.getRuns() + "*" : battingCard.getRuns()) + "\0",print_writers);
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$POP_UP$ScoreGrp$Side" + WhichSide + "$Balls*GEOM*TEXT SET " + ""+"\0",print_writers);
					
					break;
				case "BOUNDARY":
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$POP_UP$Sub_Header$Side" + WhichSide + "$StatHead1*GEOM*TEXT SET FOURS/SIXES \0",print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$POP_UP$ScoreGrp$Side" + WhichSide + "$Seperator*ACTIVE SET 0\0",print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$POP_UP$ScoreGrp$Side" + WhichSide + "$Score*GEOM*TEXT SET " + 
							battingCard.getFours() + "/" + battingCard.getSixes()+"\0",print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$POP_UP$ScoreGrp$Side" + WhichSide + "$Balls*GEOM*TEXT SET " + "" + "\0",print_writers);
					break;	
				}
				break;
			case "Control_Shift_V":
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$POP_UP$band$img_Text2$txt_Header*GEOM*TEXT SET " + bowlingCard.getPlayer().getFull_name() + "\0",print_writers);
				
				switch (config.getBroadcaster().toUpperCase()) {
				case Constants.BAN_AFG_SERIES:
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$POP_UP$band$img_Text2*TEXTURE*IMAGE SET " + Constants.BAN_AFG_SERIES_TEXT2 + inning.getBowling_team().getTeamBadge() + "\0",print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$POP_UP$band$img_Base2*TEXTURE*IMAGE SET "+Constants.BAN_AFG_SERIES_BASE2 + inning.getBowling_team().getTeamBadge() + "\0",print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$POP_UP$Badge$img_Base2*TEXTURE*IMAGE SET " + Constants.BAN_AFG_SERIES_BASE2 + inning.getBowling_team().getTeamBadge() + "\0",print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$POP_UP$Badge$img_Badges*TEXTURE*IMAGE SET " + Constants.BAN_AFG_SERIES_LOGO + inning.getBowling_team().getTeamBadge() + "\0",print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$POP_UP$Badge$img_Badges02*TEXTURE*IMAGE SET " + Constants.BAN_AFG_SERIES_LOGO + inning.getBowling_team().getTeamBadge() + "\0",print_writers);
					break;
				case Constants.ACC:
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$POP_UP$Badge$select_Flag*FUNCTION*Omo*vis_con SET 0\0",print_writers);
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$POP_UP$Badge$img_Badges*TEXTURE*IMAGE SET " + Constants.ACC_FLAG + inning.getBowling_team().getTeamBadge() + "\0",print_writers);
					break;
				}
				
				switch (whatToProcess.split(",")[3].toUpperCase()) {
				case "ECONOMY":
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$POP_UP$Sub_Header$Side" + WhichSide + "$StatHead1*GEOM*TEXT SET " + "ECONOMY" + "\0",print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$POP_UP$ScoreGrp$Side" + WhichSide + "$Seperator*ACTIVE SET 0\0",print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$POP_UP$ScoreGrp$Side" + WhichSide + "$Score*GEOM*TEXT SET " + 
							bowlingCard.getEconomyRate() + "\0",print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$POP_UP$ScoreGrp$Side" + WhichSide + "$Balls*GEOM*TEXT SET \0",print_writers);
					break;

				case "FIGURE":
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$POP_UP$Sub_Header$Side" + WhichSide + "$StatHead1*GEOM*TEXT SET " + "THIS MATCH"+"\0",print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$POP_UP$ScoreGrp$Side" + WhichSide + "$Seperator*ACTIVE SET 1\0",print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$POP_UP$ScoreGrp$Side" + WhichSide + "$Score*GEOM*TEXT SET " + 
							bowlingCard.getWickets() +"-"+ bowlingCard.getRuns() + "\0",print_writers);
					if(bowlingCard.getOvers() == 0 && bowlingCard.getBalls() >= 0) {
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$POP_UP$ScoreGrp$Side" + WhichSide + "$Balls*GEOM*TEXT SET " + 
								CricketFunctions.OverBalls(bowlingCard.getOvers(), bowlingCard.getBalls()) + " OVERS" + "\0",print_writers);
					}else if(bowlingCard.getOvers() == 1 && bowlingCard.getBalls() == 0) {
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$POP_UP$ScoreGrp$Side" + WhichSide + "$Balls*GEOM*TEXT SET " + 
								CricketFunctions.OverBalls(bowlingCard.getOvers(), bowlingCard.getBalls()) + " OVER" + "\0",print_writers);
					}else if(bowlingCard.getOvers() == 1 && bowlingCard.getBalls() > 0) {
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$POP_UP$ScoreGrp$Side" + WhichSide + "$Balls*GEOM*TEXT SET " + 
								CricketFunctions.OverBalls(bowlingCard.getOvers(), bowlingCard.getBalls()) + " OVERS" + "\0",print_writers);
					}else {
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$POP_UP$ScoreGrp$Side" + WhichSide + "$Balls*GEOM*TEXT SET " + 
								CricketFunctions.OverBalls(bowlingCard.getOvers(), bowlingCard.getBalls()) + " OVERS" + "\0",print_writers);
					}
					break;
				}
				break;
			case "Shift_O":
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Sponsor$Sponsor*TRANSFORMATION*POSITION*Y SET 54.0\0",print_writers);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Sponsor$Sponsor*FUNCTION*Omo*vis_con SET 0\0",print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Select*FUNCTION*Omo*vis_con SET 3 \0",print_writers);

				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Select$Double$All$DataGrpAll$PlayerNameGrp$Info01"
						+ "*GEOM*TEXT SET " + battingCard.getPlayer().getTicker_name() + "\0",print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Select$Double$All$DataGrpAll$PlayerNameGrp$Info02"
						+ "*GEOM*TEXT SET " + "" + "\0",print_writers);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Select$Double$All$DataGrpAll$PlayerNameGrp$group$"
						+ "Info04*GEOM*TEXT SET " + battingCard.getRuns() + " (" + battingCard.getBalls() + ")" + "\0",print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Select$Double$All$DataGrpAll$PlayerNameGrp$group$"
						+ "Info05*GEOM*TEXT SET " + " " + "\0",print_writers);
				
				switch (config.getBroadcaster().toUpperCase()) {
				case Constants.BAN_AFG_SERIES:
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Select$Double$All$BandsAll$img_Base2"
							+ "*TEXTURE*IMAGE SET " + Constants.BAN_AFG_SERIES_BASE2 + inning.getBatting_team().getTeamBadge() + " \0",print_writers);
					break;
				case Constants.ACC:
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Select$Double$All$Logo$img_Badges"
							+ "*TEXTURE*IMAGE SET " + Constants.ACC_FLAG + inning.getBatting_team().getTeamBadge() + " \0",print_writers);
					break;
				}
				
				if(battingCard.getHowOut().equalsIgnoreCase(CricketUtil.RUN_OUT)) {
					if(battingCard.getWasHowOutFielderSubstitute() != null && battingCard.getWasHowOutFielderSubstitute().equalsIgnoreCase(CricketUtil.YES)) {
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Select$Double$All$DataGrpAll$PlayerNameGrp$Info03"
								+ "*GEOM*TEXT SET " + "run out " + " (sub - " + battingCard.getHowOutFielder().getTicker_name() + ")" + "\0",print_writers);
					} else {
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Select$Double$All$DataGrpAll$PlayerNameGrp$Info03"
								+ "*GEOM*TEXT SET " + "run out (" + battingCard.getHowOutFielder().getTicker_name() + ")" + "\0",print_writers);
					}
				}else if(battingCard.getHowOut().equalsIgnoreCase(CricketUtil.STUMPED)) {
					if(battingCard.getWasHowOutFielderSubstitute() != null && battingCard.getWasHowOutFielderSubstitute().equalsIgnoreCase(CricketUtil.YES)) {
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Select$Double$All$DataGrpAll$PlayerNameGrp$Info03*GEOM*TEXT SET " 
								+ "st" +  " (sub - " + battingCard.getHowOutFielder().getTicker_name() + ")  b " + battingCard.getHowOutBowler().getTicker_name() + "\0",print_writers);
					} else {
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Select$Double$All$DataGrpAll$PlayerNameGrp$Info03*GEOM*TEXT SET "
								+ "st " + battingCard.getHowOutFielder().getTicker_name() + "  b " + battingCard.getHowOutBowler().getTicker_name() + "\0",print_writers);
					}
				}else if(battingCard.getHowOut().equalsIgnoreCase(CricketUtil.CAUGHT)) {
					if(battingCard.getWasHowOutFielderSubstitute() != null && battingCard.getWasHowOutFielderSubstitute().equalsIgnoreCase(CricketUtil.YES)) {
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Select$Double$All$DataGrpAll$PlayerNameGrp$Info03*GEOM*TEXT SET "
								+ "c" +  " (sub - " + battingCard.getHowOutFielder().getTicker_name() + ")  b " + battingCard.getHowOutBowler().getTicker_name() + "\0",print_writers);
					} else {
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Select$Double$All$DataGrpAll$PlayerNameGrp$Info03*GEOM*TEXT SET "
								+ "c " + battingCard.getHowOutFielder().getTicker_name() + "  b " + battingCard.getHowOutBowler().getTicker_name() + "\0",print_writers);
					}
				}else {
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Select$Double$All$DataGrpAll$PlayerNameGrp$Info03*GEOM*TEXT SET "
								+ battingCard.getHowOutText() + "\0",print_writers);
				}
				
				break;
			case "Shift_F":
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Sponsor$Sponsor*TRANSFORMATION*POSITION*Y SET 54.0\0",print_writers);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Sponsor$Sponsor*FUNCTION*Omo*vis_con SET 0\0",print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Select*FUNCTION*Omo*vis_con SET 3 \0",print_writers);
				
				switch (config.getBroadcaster().toUpperCase()) {
				case Constants.BAN_AFG_SERIES:
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Select$Double$All$BandsAll$img_Base2*TEXTURE*IMAGE SET "
							+ Constants.BAN_AFG_SERIES_BASE2 + inning.getBatting_team().getTeamBadge() + " \0",print_writers);
					break;
				case Constants.ACC:
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Select$Double$All$Logo$img_Badges"
							+ "*TEXTURE*IMAGE SET " + Constants.ACC_FLAG + inning.getBatting_team().getTeamBadge() + " \0",print_writers);
					break;
				}
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Select$Double$All$DataGrpAll$PlayerNameGrp$Info01*GEOM*TEXT SET "
						+ battingCard.getPlayer().getTicker_name() + "\0",print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Select$Double$All$DataGrpAll$PlayerNameGrp$Info02*GEOM*TEXT SET "
						+ "" + "\0",print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Select$Double$All$DataGrpAll$PlayerNameGrp$Info03*GEOM*TEXT SET "
						+ battingCard.getHowOutText() + "\0",print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Select$Double$All$DataGrpAll$PlayerNameGrp$group$Info04*GEOM*TEXT SET "
						+ battingCard.getRuns() + " (" + battingCard.getBalls() + ")" + "\0",print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Select$Double$All$DataGrpAll$PlayerNameGrp$group$Info05*GEOM*TEXT SET "
						+ " " + "\0",print_writers);				
				break;
			case "Alt_p":
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Sponsor$Sponsor*FUNCTION*Omo*vis_con SET 0 \0",print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Select*FUNCTION*Omo*vis_con SET 0 \0",print_writers);

				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Select$Single$All$DataGrpAll$PlayerNameGrp$Info01"
						+ "*GEOM*TEXT SET " + "" + "\0",print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Select$Single$All$DataGrpAll$PlayerNameGrp$Info02"
						+ "*GEOM*TEXT SET "+ " " + "\0",print_writers);
				
				switch (config.getBroadcaster().toUpperCase()) {
				case Constants.BAN_AFG_SERIES:
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Select$Single$All$BugLogo$img_Base2"
							+ "*TEXTURE*IMAGE SET " + Constants.BAN_AFG_SERIES_BASE2 + team.getTeamBadge() + "\0",print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Select$Single$All$BugLogo$img_Badges"
							+ "*TEXTURE*IMAGE SET " + Constants.BAN_AFG_SERIES_LOGO + team.getTeamBadge() + "\0",print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Select$Single$All$BugLogo$img_Badges02"
							+ "*TEXTURE*IMAGE SET " + Constants.BAN_AFG_SERIES_LOGO + team.getTeamBadge() + "\0",print_writers);
					break;
				case Constants.ACC:
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Select$Single$All$BugLogo$img_Badges"
							+ "*TEXTURE*IMAGE SET " + Constants.ACC_FLAG + team.getTeamBadge() + "\0",print_writers);
					break;
				}
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Select$Single$All$DataGrpAll$PlayerNameGrp$group$"
						+ "Info03*GEOM*TEXT SET " + team.getTeamName1() + " WON THE TOSS & CHOSE TO " + whatToProcess.split(",")[2].split("-")[1] + "\0",print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Select$Single$All$DataGrpAll$PlayerNameGrp$group$"
						+ "Info04*GEOM*TEXT SET " + "" + "\0",print_writers);
							
				break;
			case "Control_k":
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Sponsor$Sponsor*FUNCTION*Omo*vis_con SET 0 \0",print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Select*FUNCTION*Omo*vis_con SET 3 \0",print_writers);

				switch (config.getBroadcaster().toUpperCase()) {
				case Constants.BAN_AFG_SERIES:
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Select$Double$All$BandsAll$img_Base2"
							+ "*TEXTURE*IMAGE SET " + Constants.BAN_AFG_SERIES_BASE2 + inning.getBatting_team().getTeamBadge() + " \0",print_writers);
					break;
				case Constants.ACC:
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Select$Double$All$Logo$img_Badges"
							+ "*TEXTURE*IMAGE SET " + Constants.ACC_FLAG + inning.getBatting_team().getTeamBadge() + " \0",print_writers);
					break;
				}
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Select$Double$All$DataGrpAll$PlayerNameGrp$"
						+ "Info01*GEOM*TEXT SET " + "CURRENT" + "\0",print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Select$Double$All$DataGrpAll$PlayerNameGrp$"
						+ "Info02*GEOM*TEXT SET " + "P'SHIP" + "\0",print_writers);

				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Select$Double$All$DataGrpAll$PlayerNameGrp$group$"
						+ "Info04*GEOM*TEXT SET " + (partnership.getPartnershipNumber()==0? "":partnership.getTotalRuns() + "* (" + partnership.getTotalBalls() + ")") 
						+ "\0",print_writers);

				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Select$Double$All$DataGrpAll$PlayerNameGrp$Info03"
						+ "*GEOM*TEXT SET " + partnership.getFirstPlayer().getTicker_name() + " " + (partnership.getPartnershipNumber()== 0 ? "" : partnership.getFirstBatterRuns()
						+ " ("+partnership.getFirstBatterBalls()+")") + "\0",print_writers);

				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Select$Double$All$DataGrpAll$PlayerNameGrp$group$Info05"
						+ "*GEOM*TEXT SET " + partnership.getSecondPlayer().getTicker_name() + " " + (partnership.getPartnershipNumber()== 0 ? "" : partnership.getSecondBatterRuns()
						+ " ("+partnership.getSecondBatterBalls()+")") + "\0",print_writers);
				break;
			case "Control_y":
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Sponsor$Sponsor"+ "*FUNCTION*Omo*vis_con SET 0\0",print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Select*FUNCTION*Omo*vis_con SET 3 \0",print_writers);

				switch (config.getBroadcaster().toUpperCase()) {
				case Constants.BAN_AFG_SERIES:
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Select$Double$All$BandsAll$img_Base2*TEXTURE*IMAGE SET "
							+ Constants.BAN_AFG_SERIES_BASE2 + inning.getBatting_team().getTeamBadge() + " \0",print_writers);
					break;
				case Constants.ACC:
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Select$Double$All$Logo$img_Badges"
							+ "*TEXTURE*IMAGE SET " + Constants.ACC_FLAG + inning.getBatting_team().getTeamBadge() + " \0",print_writers);
					break;
				}
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Select$Double$All$DataGrpAll$PlayerNameGrp$Info01"
						+ "*GEOM*TEXT SET " + inning.getBatting_team().getTeamName4() + "\0",print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Select$Double$All$DataGrpAll$PlayerNameGrp$Info02"
						+ "*GEOM*TEXT SET " + "" + "\0",print_writers);
				
				switch (config.getBroadcaster().toUpperCase()) {
				case Constants.BAN_AFG_SERIES:
					
//					pp = CricketFunctions.getFirstPowerPlayScore(matchAllData,inning.getInningNumber(), matchAllData.getEventFile().getEvents());
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Select$Double$All$DataGrpAll$PlayerNameGrp$group$"
							+ "Info04*GEOM*TEXT SET " + CricketFunctions.getPowerPlayScore(inning, inning.getInningNumber(), "-", matchAllData) + "\0",print_writers);
					
//					result1 = customRound(Double.parseDouble(inning.getFirstPowerplayStartOver()));
//					result2 = customRound(Double.parseDouble(inning.getFirstPowerplayEndOver()));
		            
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Select$Double$All$DataGrpAll$PlayerNameGrp$Info03"
							+ "*GEOM*TEXT SET "+ "POWERPLAY" + "\0",print_writers);
					
//					if(whatToProcess.split(",")[2].equalsIgnoreCase("p1")) {
//						pp = CricketFunctions.getFirstPowerPlayScore(matchAllData,inning.getInningNumber(), matchAllData.getEventFile().getEvents());
//						
//						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Select$Double$All$DataGrpAll$PlayerNameGrp$group$"
//								+ "Info04*GEOM*TEXT SET " + pp.split(",")[0] + "\0",print_writers);
//						
//						result1 = customRound(Double.parseDouble(inning.getFirstPowerplayStartOver()));
//						result2 = customRound(Double.parseDouble(inning.getFirstPowerplayEndOver()));
//			            
//						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Select$Double$All$DataGrpAll$PlayerNameGrp$Info03"
//								+ "*GEOM*TEXT SET "+ "POWERPLAY 1 " + "\0",print_writers);
//						
//					}else if(whatToProcess.split(",")[2].equalsIgnoreCase("p2")) {
//						pp=CricketFunctions.getSecPowerPlayScore(matchAllData, inning.getInningNumber(), matchAllData.getEventFile().getEvents());
//						
//						result1 = customRound(Double.parseDouble(inning.getSecondPowerplayStartOver()));
//						result2 = customRound(Double.parseDouble(inning.getSecondPowerplayEndOver()));
//						
//						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Select$Double$All$DataGrpAll$PlayerNameGrp$Info03"
//								+ "*GEOM*TEXT SET " + "POWERPLAY 2 " + "\0",print_writers);
//						
//						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Select$Double$All$DataGrpAll$PlayerNameGrp$group$"
//								+ "Info04*GEOM*TEXT SET " + pp.split(",")[0] + "\0",print_writers);
//						
//					}else if(whatToProcess.split(",")[2].equalsIgnoreCase("p3")) {
//						pp=CricketFunctions.getThirdPowerPlayScore(matchAllData, inning.getInningNumber(), matchAllData.getEventFile().getEvents());
//						
//						result1 = customRound(Double.parseDouble(inning.getThirdPowerplayStartOver()));
//						result2 = customRound(Double.parseDouble(inning.getThirdPowerplayEndOver()));
//						
//						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Select$Double$All$DataGrpAll$PlayerNameGrp$Info03"
//								+ "*GEOM*TEXT SET " + "POWERPLAY 3 " + "\0",print_writers);
//						
//						CricketFunctions.DoadWriteCommandToAllViz(
//								"-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Select$Double$All$DataGrpAll$PlayerNameGrp$group$Info04*GEOM*TEXT SET "
//										+ pp.split(",")[0] + "\0",print_writers);
//					}
					break;
//				case Constants.ACC:
//					if(whatToProcess.split(",")[2].equalsIgnoreCase("p1")) {
//						pp = CricketFunctions.getFirstPowerPlayScore(matchAllData,inning.getInningNumber(), matchAllData.getEventFile().getEvents());
//						
//						result1 = customRound(Double.parseDouble(inning.getFirstPowerplayStartOver()));
//						result2 = customRound(Double.parseDouble(inning.getFirstPowerplayEndOver()));
//			            
//						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Select$Double$All$DataGrpAll$PlayerNameGrp$Info03"
//								+ "*GEOM*TEXT SET " + "POWERPLAY 1" + "\0",print_writers);
//						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Select$Double$All$DataGrpAll$PlayerNameGrp$group$"
//								+ "Info04*GEOM*TEXT SET " + pp.split(",")[0] + "\0",print_writers);
//						
//					}else if(whatToProcess.split(",")[2].equalsIgnoreCase("p2")) {
//						pp=CricketFunctions.getSecPowerPlayScore(matchAllData, inning.getInningNumber(), matchAllData.getEventFile().getEvents());
//						
//						result1 = customRound(Double.parseDouble(inning.getSecondPowerplayStartOver()));
//						result2 = customRound(Double.parseDouble(inning.getSecondPowerplayEndOver()));
//						
//						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Select$Double$All$DataGrpAll$PlayerNameGrp$Info03"
//								+ "*GEOM*TEXT SET " + "POWERPLAY 2" + "\0",print_writers);
//						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Select$Double$All$DataGrpAll$PlayerNameGrp$group$"
//								+ "Info04*GEOM*TEXT SET " + pp.split(",")[0] + "\0",print_writers);
//						
//					}else if(whatToProcess.split(",")[2].equalsIgnoreCase("p3")) {
//						pp=CricketFunctions.getThirdPowerPlayScore(matchAllData, inning.getInningNumber(), matchAllData.getEventFile().getEvents());
//						
//						result1 = customRound(Double.parseDouble(inning.getThirdPowerplayStartOver()));
//						result2 = customRound(Double.parseDouble(inning.getThirdPowerplayEndOver()));
//						
//						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Select$Double$All$DataGrpAll$PlayerNameGrp$Info03"
//								+ "*GEOM*TEXT SET " + "POWERPLAY 3" + "\0",print_writers);
//						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Select$Double$All$DataGrpAll$PlayerNameGrp$group$"
//								+ "Info04*GEOM*TEXT SET " + pp.split(",")[0] + "\0",print_writers);	
//					}
//					break;
				default:
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Select$Double$All$DataGrpAll$PlayerNameGrp$Info03"
							+ "*GEOM*TEXT SET " + "POWERPLAY" + "\0",print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Select$Double$All$DataGrpAll$PlayerNameGrp$group$"
							+ "Info04*GEOM*TEXT SET " + CricketFunctions.getPowerPlayScore(inning, inning.getInningNumber(), "-", matchAllData) + "\0",print_writers);
					break;
				}

				
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Select$Double$All$DataGrpAll$PlayerNameGrp$group$"
						+ "Info05*GEOM*TEXT SET " + "" + "\0",print_writers);
				break;
			case "k":
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Sponsor$Sponsor*FUNCTION*Omo*vis_con SET 0\0",print_writers);
				
				if (bug.getText1() != null && bug.getText2() != null && bug.getText3() != null && bug.getText4() != null) {
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Select*FUNCTION*Omo*vis_con SET 3 \0",print_writers);
					
					switch (config.getBroadcaster().toUpperCase()) {
					case Constants.BAN_AFG_SERIES:
						if(bug.getSponsor() != null) {	
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Select$Double$All$BandsAll$img_Base2"
									+ "*TEXTURE*IMAGE SET " + Constants.BAN_AFG_SERIES_BASE2 + bug.getSponsor() + " \0",print_writers);
						}else {
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Select$Double$All$BandsAll$img_Base2"
									+ "*TEXTURE*IMAGE SET " + Constants.BAN_AFG_SERIES_BASE2 + "EVENT" + " \0",print_writers);
						}
						break;
					case Constants.ACC:
						if(bug.getSponsor() != null) {	
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Select$Double$All$Logo$img_Badges"
									+ "*TEXTURE*IMAGE SET " + Constants.ACC_FLAG + bug.getSponsor() + " \0",print_writers);
						}else {
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Select$Double$All$Logo$img_Badges"
									+ "*TEXTURE*IMAGE SET " + Constants.ACC_FLAG + "TLogo_White" + " \0",print_writers);
						}
						break;
					}
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Select$Double$All$DataGrpAll$PlayerNameGrp$"
							+ "Info01*GEOM*TEXT SET " + bug.getText1() + "\0",print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Select$Double$All$DataGrpAll$PlayerNameGrp$"
							+ "Info02*GEOM*TEXT SET " + "" + "\0",print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Select$Double$All$DataGrpAll$PlayerNameGrp$"
							+ "Info03*GEOM*TEXT SET " + bug.getText3() + "\0",print_writers);

					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Select$Double$All$DataGrpAll$PlayerNameGrp$"
							+ "group$Info04*GEOM*TEXT SET " + bug.getText2() + "\0",print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Select$Double$All$DataGrpAll$PlayerNameGrp$"
							+ "group$Info05*GEOM*TEXT SET " + bug.getText4() + "\0",print_writers);

				}else if (bug.getText1() != null && bug.getText2() != null && bug.getText3() != null) {
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Select*FUNCTION*Omo*vis_con SET 3 \0",print_writers);
					
					switch (config.getBroadcaster().toUpperCase()) {
					case Constants.BAN_AFG_SERIES:
						if(bug.getSponsor() != null) {	
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Select$Double$All$BandsAll$img_Base2"
									+ "*TEXTURE*IMAGE SET " + Constants.BAN_AFG_SERIES_BASE2 + bug.getSponsor() + " \0",print_writers);
						}else {
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Select$Double$All$BandsAll$img_Base2"
									+ "*TEXTURE*IMAGE SET " + Constants.BAN_AFG_SERIES_BASE2 + "EVENT" + " \0",print_writers);
						}
						break;
					case Constants.ACC:
						if(bug.getSponsor() != null) {	
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Select$Double$All$Logo$img_Badges"
									+ "*TEXTURE*IMAGE SET " + Constants.ACC_FLAG + bug.getSponsor() + " \0",print_writers);
						}else {
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Select$Double$All$Logo$img_Badges"
									+ "*TEXTURE*IMAGE SET " + Constants.ACC_FLAG + "TLogo_White" + " \0",print_writers);
						}
						break;
					}

					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Select$Double$All$DataGrpAll$PlayerNameGrp$"
							+ "Info01*GEOM*TEXT SET " + bug.getText1() + "\0",print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Select$Double$All$DataGrpAll$PlayerNameGrp$"
							+ "Info02*GEOM*TEXT SET " + "" + "\0",print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Select$Double$All$DataGrpAll$PlayerNameGrp$"
							+ "Info03*GEOM*TEXT SET " + bug.getText3() + "\0",print_writers);

					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Select$Double$All$DataGrpAll$PlayerNameGrp$"
							+ "group$Info04*GEOM*TEXT SET " + bug.getText2() + "\0",print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Select$Double$All$DataGrpAll$PlayerNameGrp$"
							+ "group$Info05*GEOM*TEXT SET " + "" + "\0",print_writers);

				} else if (bug.getText1() != null && bug.getText2() != null) {
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Select*FUNCTION*Omo*vis_con SET 3 \0",print_writers);
					
					switch (config.getBroadcaster().toUpperCase()) {
					case Constants.BAN_AFG_SERIES:
						if(bug.getSponsor() != null) {	
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Select$Double$All$BandsAll$img_Base2"
									+ "*TEXTURE*IMAGE SET " + Constants.BAN_AFG_SERIES_BASE2 + bug.getSponsor() + " \0",print_writers);
						}else {
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Select$Double$All$BandsAll$img_Base2"
									+ "*TEXTURE*IMAGE SET " + Constants.BAN_AFG_SERIES_BASE2 + "EVENT" + " \0",print_writers);
						}
						break;
					case Constants.ACC:
						if(bug.getSponsor() != null) {	
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Select$Double$All$Logo$img_Badges"
									+ "*TEXTURE*IMAGE SET " + Constants.ACC_FLAG + bug.getSponsor() + " \0",print_writers);
						}else {
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Select$Double$All$Logo$img_Badges"
									+ "*TEXTURE*IMAGE SET " + Constants.ACC_FLAG + "TLogo_White" + " \0",print_writers);
						}
						break;
					}

					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Select$Double$All$DataGrpAll$PlayerNameGrp$"
							+ "Info01*GEOM*TEXT SET "+ bug.getText1() + "\0",print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Select$Double$All$DataGrpAll$PlayerNameGrp$"
							+ "Info02*GEOM*TEXT SET "+ "" + "\0",print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Select$Double$All$DataGrpAll$PlayerNameGrp$"
							+ "Info03*GEOM*TEXT SET "+ bug.getText2() + "\0",print_writers);

					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Select$Double$All$DataGrpAll$PlayerNameGrp$"
							+ "group$Info04*GEOM*TEXT SET " + "" + "\0",print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Select$Double$All$DataGrpAll$PlayerNameGrp$"
							+ "group$Info05*GEOM*TEXT SET " + "" + "\0",print_writers);

				}  else if (bug.getText1() != null && bug.getText2() == null) {
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Select*FUNCTION*Omo*vis_con SET 0 \0",print_writers);
					
					switch (config.getBroadcaster().toUpperCase()) {
					case Constants.BAN_AFG_SERIES:
						if(bug.getSponsor() != null) {
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Select$Single$All$BugLogo$img_Base2"
									+ "*TEXTURE*IMAGE SET " + Constants.BAN_AFG_SERIES_BASE2 + bug.getSponsor() + "\0",print_writers);
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Select$Single$All$BugLogo$img_Badges"
									+ "*TEXTURE*IMAGE SET " + Constants.BAN_AFG_SERIES_LOGO + bug.getSponsor() + "\0",print_writers);
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Select$Single$All$BugLogo$img_Badges02"
									+ "*TEXTURE*IMAGE SET " + Constants.BAN_AFG_SERIES_LOGO + bug.getSponsor() + "\0",print_writers);
						}else {
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Select$Single$All$BugLogo$img_Base2"
									+ "*TEXTURE*IMAGE SET " + Constants.BAN_AFG_SERIES_BASE2 + "EVENT" + "\0",print_writers);
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Select$Single$All$BugLogo$img_Badges"
									+ "*TEXTURE*IMAGE SET " + Constants.BAN_AFG_SERIES_LOGO + "EVENT" + "\0",print_writers);
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Select$Single$All$BugLogo$img_Badges02"
									+ "*TEXTURE*IMAGE SET "+ Constants.BAN_AFG_SERIES_LOGO + "EVENT" + "\0",print_writers);
						}
						break;
					case Constants.ACC:
						if(bug.getSponsor() != null) {
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Select$Single$All$BugLogo$img_Badges"
									+ "*TEXTURE*IMAGE SET " + Constants.ACC_FLAG + bug.getSponsor() + "\0",print_writers);
						}else {
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Select$Single$All$BugLogo$img_Badges"
									+ "*TEXTURE*IMAGE SET " + Constants.ACC_FLAG + "TLogo_White" + "\0",print_writers);
						}
						break;
					}

					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Select$Single$All$DataGrpAll$PlayerNameGrp$"
							+ "Info01*GEOM*TEXT SET " + bug.getText1() + "\0",print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Select$Single$All$DataGrpAll$PlayerNameGrp$"
							+ "Info02*GEOM*TEXT SET " + "" + "\0",print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Select$Single$All$DataGrpAll$PlayerNameGrp$"
							+ "group$Info03*GEOM*TEXT SET " + "" + "\0",print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Select$Single$All$DataGrpAll$PlayerNameGrp$"
							+ "group$Info04*GEOM*TEXT SET "+ "" + "\0",print_writers);

				}else {
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Select*FUNCTION*Omo*vis_con SET 0 \0",print_writers);

					switch (config.getBroadcaster().toUpperCase()) {
					case Constants.BAN_AFG_SERIES:
						if(bug.getSponsor() != null) {
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Select$Single$All$BugLogo$img_Base2"
									+ "*TEXTURE*IMAGE SET " + Constants.BAN_AFG_SERIES_BASE2 + bug.getSponsor() + "\0",print_writers);
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Select$Single$All$BugLogo$img_Badges"
									+ "*TEXTURE*IMAGE SET " + Constants.BAN_AFG_SERIES_LOGO + bug.getSponsor() + "\0",print_writers);
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Select$Single$All$BugLogo$img_Badges02"
									+ "*TEXTURE*IMAGE SET " + Constants.BAN_AFG_SERIES_LOGO + bug.getSponsor() + "\0",print_writers);
						}else {
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Select$Single$All$BugLogo$img_Base2"
									+ "*TEXTURE*IMAGE SET " + Constants.BAN_AFG_SERIES_BASE2 + "EVENT" + "\0",print_writers);
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Select$Single$All$BugLogo$img_Badges"
									+ "*TEXTURE*IMAGE SET " + Constants.BAN_AFG_SERIES_LOGO + "EVENT" + "\0",print_writers);
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Select$Single$All$BugLogo$img_Badges02"
									+ "*TEXTURE*IMAGE SET "+ Constants.BAN_AFG_SERIES_LOGO + "EVENT" + "\0",print_writers);
						}
						break;
					case Constants.ACC:
						if(bug.getSponsor() != null) {
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Select$Single$All$BugLogo$img_Badges"
									+ "*TEXTURE*IMAGE SET " + Constants.ACC_FLAG + bug.getSponsor() + "\0",print_writers);
						}else {
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Select$Single$All$BugLogo$img_Badges"
									+ "*TEXTURE*IMAGE SET " + Constants.ACC_FLAG + "TLogo_White" + "\0",print_writers);
						}
						break;
					}
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Select$Single$All$DataGrpAll$PlayerNameGrp$"
							+ "Info01*GEOM*TEXT SET " + bug.getText2() + "\0",print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Select$Single$All$DataGrpAll$PlayerNameGrp$"
							+ "Info02*GEOM*TEXT SET " + "" + "\0",print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Select$Single$All$DataGrpAll$PlayerNameGrp$"
							+ "group$Info03*GEOM*TEXT SET " + "" + "\0",print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Select$Single$All$DataGrpAll$PlayerNameGrp$"
							+ "group$Info04*GEOM*TEXT SET " + "" + "\0",print_writers);
				}
				break;
			case "y":
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Sponsor$Sponsor*TRANSFORMATION*POSITION*Y SET 54.0\0",print_writers);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Sponsor$Sponsor*FUNCTION*Omo*vis_con SET 0\0",print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Select*FUNCTION*Omo*vis_con SET 3 \0",print_writers);
				
				switch (config.getBroadcaster().toUpperCase()) {
				case Constants.BAN_AFG_SERIES:
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Select$Double$All$BandsAll$img_Base2"
							+ "*TEXTURE*IMAGE SET " + Constants.BAN_AFG_SERIES_BASE2 + inning.getBatting_team().getTeamBadge() + " \0",print_writers);
					break;
				case Constants.ACC:
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Select$Double$All$Logo$img_Badges"
							+ "*TEXTURE*IMAGE SET " + Constants.ACC_FLAG + inning.getBatting_team().getTeamBadge() + " \0",print_writers);
					break;
				}
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Select$Double$All$DataGrpAll$PlayerNameGrp"
						+ "$group$Info04*GEOM*TEXT SET " + battingCard.getRuns() + (battingCard.getStatus().equalsIgnoreCase(CricketUtil.NOT_OUT) ? "* " : "") 
						+ "(" + battingCard.getBalls() + ")" + "\0",print_writers);

				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Select$Double$All$DataGrpAll$PlayerNameGrp$"
						+ "Info01*GEOM*TEXT SET " + battingCard.getPlayer().getTicker_name() + "\0",print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Select$Double$All$DataGrpAll$PlayerNameGrp$"
						+ "Info02*GEOM*TEXT SET " + " " + "\0",print_writers);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Select$Double$All$DataGrpAll$PlayerNameGrp$"
						+ "Info03*GEOM*TEXT SET " + "4s: " + battingCard.getFours() + " 6s: " + battingCard.getSixes() + "\0",print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Select$Double$All$DataGrpAll$PlayerNameGrp$"
						+ "group$Info05*GEOM*TEXT SET " + "S/R: " + battingCard.getStrikeRate() + "\0",print_writers);
				break;
			case "g":
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Sponsor$Sponsor*TRANSFORMATION*POSITION*Y SET 47.0\0",print_writers);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Sponsor$Sponsor*FUNCTION*Omo*vis_con SET 0\0",print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Select*FUNCTION*Omo*vis_con SET 0 \0",print_writers);

				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Select$Single$All$DataGrpAll$PlayerNameGrp$Info01"
						+ "*GEOM*TEXT SET " + bowlingCard.getPlayer().getTicker_name() + "\0",print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Select$Single$All$DataGrpAll$PlayerNameGrp$Info02"
						+ "*GEOM*TEXT SET " + " " + "\0",print_writers);
				
				switch (config.getBroadcaster().toUpperCase()) {
				case Constants.BAN_AFG_SERIES:
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Select$Single$All$BugLogo$img_Base2"
							+ "*TEXTURE*IMAGE SET " + Constants.BAN_AFG_SERIES_BASE2 + inning.getBowling_team().getTeamBadge() + "\0",print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Select$Single$All$BugLogo$img_Badges"
							+ "*TEXTURE*IMAGE SET " + Constants.BAN_AFG_SERIES_LOGO + inning.getBowling_team().getTeamBadge() + "\0",print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Select$Single$All$BugLogo$img_Badges02"
							+ "*TEXTURE*IMAGE SET " + Constants.BAN_AFG_SERIES_LOGO + inning.getBowling_team().getTeamBadge() + "\0",print_writers);
					break;
				case Constants.ACC:
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Select$Single$All$BugLogo$img_Badges"
							+ "*TEXTURE*IMAGE SET " + Constants.ACC_FLAG + inning.getBowling_team().getTeamBadge() + "\0",print_writers);
					break;
				}

				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Select$Single$All$DataGrpAll$PlayerNameGrp$group"
						+ "$Info03*GEOM*TEXT SET " + bowlingCard.getWickets() + "-" + bowlingCard.getRuns() + "\0",print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Select$Single$All$DataGrpAll$PlayerNameGrp$group"
						+ "$Info04*GEOM*TEXT SET " + CricketFunctions.OverBalls(bowlingCard.getOvers(), bowlingCard.getBalls()) + "\0",print_writers);
				
				break;
			case "h":
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Sponsor$Sponsor*TRANSFORMATION*POSITION*Y SET 54.0\0",print_writers);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Sponsor$Sponsor*FUNCTION*Omo*vis_con SET 0\0",print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Select*FUNCTION*Omo*vis_con SET 3 \0",print_writers);

				switch (config.getBroadcaster().toUpperCase()) {
				case Constants.BAN_AFG_SERIES:
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Select$Double$All$BandsAll$img_Base2"
							+ "*TEXTURE*IMAGE SET " + Constants.BAN_AFG_SERIES_BASE2 + inning.getBatting_team().getTeamBadge() + " \0",print_writers);
					break;
				case Constants.ACC:
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Select$Double$All$Logo$img_Badges"
							+ "*TEXTURE*IMAGE SET " + Constants.ACC_FLAG + inning.getBatting_team().getTeamBadge() + " \0",print_writers);
					break;
				}
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Select$Double$All$DataGrpAll$PlayerNameGrp$"
						+ "Info03*GEOM*TEXT SET HIGHLIGHTS\0",print_writers);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Select$Double$All$DataGrpAll$PlayerNameGrp$group$"
						+ "Info04*GEOM*TEXT SET " + CricketFunctions.getTeamScore(inning, "-", false) + " (" + CricketFunctions.OverBalls(inning.getTotalOvers(), 
								inning.getTotalBalls()) + ")" + "\0",print_writers);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Select$Double$All$DataGrpAll$PlayerNameGrp$"
						+ "Info01*GEOM*TEXT SET " + inning.getBatting_team().getTeamName4() + "\0",print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Select$Double$All$DataGrpAll$PlayerNameGrp$"
						+ "Info02*GEOM*TEXT SET " + "" + "\0",print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Select$Double$All$DataGrpAll$PlayerNameGrp$"
						+ "group$Info05*GEOM*TEXT SET " + " " + "\0",print_writers);
					
				break;
			case "Shift_F4":
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Sponsor$Sponsor*FUNCTION*Omo*vis_con SET 0 \0",print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Select*FUNCTION*Omo*vis_con SET 3 \0",print_writers);
				
				switch (config.getBroadcaster().toUpperCase()) {
				case Constants.BAN_AFG_SERIES:
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Select$Double$All$BandsAll$img_Base2"
							+ "*TEXTURE*IMAGE SET " + Constants.BAN_AFG_SERIES_BASE2 + inning.getBatting_team().getTeamBadge() + " \0",print_writers);
					break;
				case Constants.ACC:
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Select$Double$All$Logo$img_Badges"
							+ "*TEXTURE*IMAGE SET " + Constants.ACC_FLAG + inning.getBatting_team().getTeamBadge() + " \0",print_writers);
					break;
				}
				
				if(partnership.getPartnershipNumber() == 0) {
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Select$Double$All$DataGrpAll$PlayerNameGrp$Info03"
							+ "*GEOM*TEXT SET \0",print_writers);
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Select$Double$All$DataGrpAll$PlayerNameGrp$group$"
							+ "Info04*GEOM*TEXT SET " + (partnership.getPartnershipNumber() == inning.getPartnerships().size()? "*" :"") + "\0",print_writers);
						
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Select$Double$All$DataGrpAll$PlayerNameGrp$Info01"
							+ "*GEOM*TEXT SET " + partnership.getFirstPlayer().getTicker_name() + " &" + "\0",print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Select$Double$All$DataGrpAll$PlayerNameGrp$Info02"
							+ "*GEOM*TEXT SET " + partnership.getSecondPlayer().getTicker_name() + "\0",print_writers);
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Select$Double$All$DataGrpAll$PlayerNameGrp$group$"
							+ "Info05*GEOM*TEXT SET " + " " + "\0",print_writers);
					
				}else {
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Select$Double$All$DataGrpAll$PlayerNameGrp$"
							+ "Info01*GEOM*TEXT SET " + CricketFunctions.ordinal(partnership.getPartnershipNumber()) + " WICKET" + "\0",print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Select$Double$All$DataGrpAll$PlayerNameGrp$"
							+ "Info02*GEOM*TEXT SET " + "P'SHIP" + "\0",print_writers);

					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Select$Double$All$DataGrpAll$PlayerNameGrp$group$"
							+ "Info04*GEOM*TEXT SET " + partnership.getTotalRuns() + "" + (partnership.getPartnershipNumber() == inning.getPartnerships().size() ? "*" : "") 
							+ " (" + partnership.getTotalBalls() + ")" + "\0",print_writers);

					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Select$Double$All$DataGrpAll$PlayerNameGrp$Info03"
							+ "*GEOM*TEXT SET " + partnership.getFirstPlayer().getTicker_name() + " " + (partnership.getPartnershipNumber()== 0 ? "" : partnership.getFirstBatterRuns()
							+ " ("+partnership.getFirstBatterBalls()+")") + "\0",print_writers);

					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Select$Double$All$DataGrpAll$PlayerNameGrp$group$Info05"
							+ "*GEOM*TEXT SET " + partnership.getSecondPlayer().getTicker_name() + " " + (partnership.getPartnershipNumber()== 0 ? "" : partnership.getSecondBatterRuns()
							+ " ("+partnership.getSecondBatterBalls()+")") + "\0",print_writers);
				}
				break;
			}
			break;
		case Constants.TRI_SERIES:  case Constants.MT20: case Constants.TG20:
			String logoPath, base1Path, base2Path,text1Path,text2Path;
			if (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES)) {
			    logoPath = Constants.TRI_SERIES_LOGO;
			    base1Path = Constants.TRI_SERIES_BASE1;
			    base2Path = Constants.TRI_SERIES_BASE2;
			    text1Path = Constants.TRI_SERIES_TEXT1;
			    text2Path = Constants.TRI_SERIES_TEXT2;
			} else if (config.getBroadcaster().equalsIgnoreCase(Constants.TG20)) {
			    logoPath = Constants.TG20_LOGO;
			    base1Path = Constants.TG20_BASE1;
			    base2Path = Constants.TG20_BASE2;
			    text1Path = Constants.TG20_TEXT1;
			    text2Path = Constants.TG20_TEXT2;
			} else {
			    logoPath = Constants.MT20_LOGO;
			    base1Path = Constants.MT20_BASE1;
			    base2Path = Constants.MT20_BASE2;
			    text1Path = Constants.MT20_TEXT1;
			    text2Path = Constants.MT20_TEXT2;
			}
			
			switch (whatToProcess.split(",")[0]) {
			case "Shift_C":
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Select*FUNCTION*Omo*vis_con SET 0\0", print_writers);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Single$img_Base1*TEXTURE*IMAGE SET " 
						+ base1Path + inning.getBatting_team().getTeamBadge() +"\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Single$img_Base2*TEXTURE*IMAGE SET " 
						+ base2Path + inning.getBatting_team().getTeamBadge() +"\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Single$img_Badges*TEXTURE*IMAGE SET " 
						+ logoPath + inning.getBatting_team().getTeamBadge()+"\0", print_writers);
				
				switch(config.getBroadcaster()) {
				case Constants.TG20:
					CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Single$PlayerNameGrp$"
							+ "select_Language*FUNCTION*Omo*vis_con SET ", config, Constants.TG20, print_writers, foreignLanguageOmo);
					foreignLanguageData = CricketFunctions.AssembleMultiLanguageData(CricketUtil.DICTIONARY, "", multilanguagedata, "SIX DISTANCE", "", 
							null, 0, foreignLanguageDataList);
					CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Single$PlayerNameGrp$"
							+ "English$Info01*GEOM*TEXT SET ", config, Constants.TG20, print_writers, foreignLanguageData);
					
					System.out.println(whatToProcess);
					
					foreignLanguageDataList = CricketFunctions.AssembleMultiLanguageData("", "", multilanguagedata, whatToProcess.split(",")[2], "", null, 1,foreignLanguageDataList);
					foreignLanguageDataList = CricketFunctions.AssembleMultiLanguageData(CricketUtil.DICTIONARY, "", multilanguagedata, "METERS", "", null, 
							2, foreignLanguageDataList);
					foreignLanguageData.add(CricketFunctions.MergeForeignLanguageDataListToSingleObject(foreignLanguageDataList));
					CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Single$PlayerNameGrp$"
							+ "English$Info03*GEOM*TEXT SET ", config, Constants.TG20, print_writers, foreignLanguageData);

					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Single$PlayerNameGrp$English$Info01*ACTIVE SET 1\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Single$PlayerNameGrp$English$Info03*ACTIVE SET 1\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Single$PlayerNameGrp$English$Info02*ACTIVE SET 0\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Single$PlayerNameGrp$English$Info04*ACTIVE SET 0\0", print_writers);
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Single$PlayerNameGrp$Telugu$Info01*ACTIVE SET 1\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Single$PlayerNameGrp$Telugu$Info03*ACTIVE SET 1\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Single$PlayerNameGrp$Telugu$Info02*ACTIVE SET 0\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Single$PlayerNameGrp$Telugu$Info04*ACTIVE SET 0\0", print_writers);
					break;
				}
				break;
			case "r":
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$DRS_Bug$TopLine$Base$img_Base1*TEXTURE*IMAGE SET " + base1Path + team.getTeamBadge() +"\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$DRS_Bug$TopLine$Text$img_Text1*TEXTURE*IMAGE SET " + text1Path + team.getTeamBadge() +"\0", print_writers);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$DRS_Bug$BottomLine$Base$img_Base2*TEXTURE*IMAGE SET " + base2Path + team.getTeamBadge() +"\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$DRS_Bug$BottomLine$Text$img_Text2*TEXTURE*IMAGE SET " + text2Path + team.getTeamBadge() +"\0", print_writers);
				
				CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$DRS_Bug$TopLine$Text$img_Text1$select_Language*FUNCTION*Omo*vis_con SET ", 
						config, Constants.TG20, print_writers, foreignLanguageOmo);
				foreignLanguageDataList = CricketFunctions.AssembleMultiLanguageData(CricketUtil.TEAM, CricketUtil.TEAMNAME_4, multilanguagedata, team.getTeamName1(), "", null, 1,foreignLanguageDataList);
				foreignLanguageDataList = CricketFunctions.AssembleMultiLanguageData(CricketUtil.DICTIONARY, "", multilanguagedata, "REVIEW", "", null, 2,foreignLanguageDataList);
				foreignLanguageData.add(CricketFunctions.MergeForeignLanguageDataListToSingleObject(foreignLanguageDataList));
				CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$DRS_Bug$TopLine$Text$img_Text1$English$txt_TeamName*GEOM*TEXT SET ", 
						config, Constants.TG20, print_writers, foreignLanguageData);
				
				CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$DRS_Bug$BottomLine$Text$img_Text2$Side" + WhichSide + "$select_Language"
						+ "*FUNCTION*Omo*vis_con SET ", config, Constants.TG20, print_writers, foreignLanguageOmo);
				foreignLanguageData = CricketFunctions.AssembleMultiLanguageData(CricketUtil.DICTIONARY, "", multilanguagedata, reviewData, "", null, 0, foreignLanguageDataList);
				CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$DRS_Bug$BottomLine$Text$img_Text2$Side" + WhichSide + "$English$txt_Info"
						+ "*GEOM*TEXT SET ", config, Constants.TG20, print_writers, foreignLanguageData);
				break;
			case "Control_Shift_*":
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Sponsor$TopLine$LogoAll$img_Logos*TEXTURE*IMAGE SET " 
						+ (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_SPONSOR : Constants.MT20_SPONSOR)  
						+ everestBug.getSponsor() +"\0", print_writers);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Sponsor$select_Tagline*FUNCTION*Omo*vis_con SET 1\0", print_writers);
				
				if(everestBug.getText1().equalsIgnoreCase("1")) {
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Sponsor$select_Tag*FUNCTION*Omo*vis_con SET 0\0", print_writers);
				}else if(everestBug.getText1().equalsIgnoreCase("2")) {
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Sponsor$select_Tag*FUNCTION*Omo*vis_con SET 2\0", print_writers);
				}else {
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Sponsor$select_Tagline*FUNCTION*Omo*vis_con SET 0\0", print_writers);
				}
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Sponsor$TaglineGrp$img_Text1$SingleLine$txt_Tagline*GEOM*TEXT SET " 
						+ everestBug.getText2()+"\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Sponsor$TaglineGrp$img_Text1$DoubleLine$txt_Tagline*GEOM*TEXT SET " 
						+ everestBug.getText2()+"\0", print_writers);
				break;
			case "Control_Shift_F3": 
				String summary = "",team_name = "";
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Select*FUNCTION*Omo*vis_con SET 0\0", print_writers);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Single$img_Base1*TEXTURE*IMAGE SET " 
						+ base1Path  + inning.getBatting_team().getTeamBadge() +"\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Single$img_Base2*TEXTURE*IMAGE SET " 
						+ base2Path  + inning.getBatting_team().getTeamBadge() +"\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Single$img_Badges*TEXTURE*IMAGE SET " 
						+ logoPath  +inning.getBatting_team().getTeamBadge()+"\0", print_writers);
				
				switch (config.getBroadcaster()) {
				case Constants.TRI_SERIES:  case Constants.MT20:
					if(matchAllData.getSetup().getMatchType().equalsIgnoreCase(CricketUtil.SUPER_OVER) && matchAllData.getSetup().getMaxOvers() == 1) {
						team_name = inning.getBatting_team().getTeamName1(); 
						summary = "NEED " + CricketFunctions.GetTargetData(matchAllData).getTargetRuns() + " RUNS TO WIN FROM " 
								+ (matchAllData.getSetup().getMaxOvers()*6) + " BALLS";
					}else {
						if(matchAllData.getSetup().getTargetOvers() != null && matchAllData.getSetup().getTargetRuns() != 0) {
							if(matchAllData.getSetup().getTargetOvers() != null && !matchAllData.getSetup().getTargetOvers().isEmpty()) {
								team_name = inning.getBatting_team().getTeamName1();
								summary = "NEED " + CricketFunctions.GetTargetData(matchAllData).getTargetRuns() + " RUNS TO WIN FROM " 
										+ CricketFunctions.GetTargetData(matchAllData).getTargetOvers() + " OVERS" + (matchAllData.getSetup().getTargetType() != null 
										&& !matchAllData.getSetup().getTargetType().isEmpty() ? " (" + matchAllData.getSetup().getTargetType().toUpperCase() +")":"");
							}
						}else {
							team_name = inning.getBatting_team().getTeamName1();
							summary = "NEED " + CricketFunctions.GetTargetData(matchAllData).getTargetRuns() + " RUNS TO WIN FROM " 
									+ CricketFunctions.GetTargetData(matchAllData).getTargetOvers() + " OVERS";
						}
					}
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Single$BaseGrp$img_Text1*TEXTURE*IMAGE SET " 
							+ text1Path  + inning.getBatting_team().getTeamBadge() +"\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Single$Info03*GEOM*TEXT SET " 
							+ team_name + " " + summary+"\0", print_writers);
					break;
				case Constants.TG20:
					ForeignLanguageData target = null;
					String targetType = null;
					
					if(matchAllData.getSetup().getMatchType().equalsIgnoreCase(CricketUtil.SUPER_OVER) && matchAllData.getSetup().getMaxOvers() == 1) {
					    team_name = inning.getBatting_team().getTeamName1();
					    summary = "NEED " + CricketFunctions.GetTargetData(matchAllData).getTargetRuns() + " RUNS TO WIN FROM "
					            + (matchAllData.getSetup().getMaxOvers()*6) + " BALLS";
					    target = CricketFunctions.generateTargetAndEquationForeignLanguage(team_name, summary, "BALLS", null, multilanguagedata);
					} else {
					    if(matchAllData.getSetup().getTargetOvers() != null && matchAllData.getSetup().getTargetRuns() != 0) {
					        if(matchAllData.getSetup().getTargetOvers() != null && !matchAllData.getSetup().getTargetOvers().isEmpty()) {
					            team_name = inning.getBatting_team().getTeamName1();
					            targetType = (matchAllData.getSetup().getTargetType() != null && !matchAllData.getSetup().getTargetType().isEmpty())
					                    ? matchAllData.getSetup().getTargetType().toUpperCase() : null;
					            summary = "NEED " + CricketFunctions.GetTargetData(matchAllData).getTargetRuns() + " RUNS TO WIN FROM "
					                    + CricketFunctions.GetTargetData(matchAllData).getTargetOvers() + " OVERS"
					                    + (targetType != null ? " (" + targetType + ")" : "");
					            
					            target = CricketFunctions.generateTargetAndEquationForeignLanguage(team_name, summary, "OVERS", targetType, multilanguagedata);
					        }
					    } else {
					        team_name = inning.getBatting_team().getTeamName1();
					        summary = "NEED " + CricketFunctions.GetTargetData(matchAllData).getTargetRuns() + " RUNS TO WIN FROM "
					                + CricketFunctions.GetTargetData(matchAllData).getTargetOvers() + " OVERS";
					        target = CricketFunctions.generateTargetAndEquationForeignLanguage(team_name, summary, "OVERS", null, multilanguagedata);
					    }
					}
					
					CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Single$PlayerNameGrp$"
							+ "select_Language*FUNCTION*Omo*vis_con SET ", config, Constants.TG20, print_writers, foreignLanguageOmo);
					foreignLanguageData.add(new ForeignLanguageData(target.getEnglishText(), target.getHindiText(), target.getTamilText(), target.getTeluguText()));
					CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Single$PlayerNameGrp$"
							+ "English$Info03*GEOM*TEXT SET ", config, Constants.TG20, print_writers, foreignLanguageData);
					break;
				}
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Single$PlayerNameGrp$English$Info01*ACTIVE SET 0\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Single$PlayerNameGrp$English$Info03*ACTIVE SET 1\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Single$PlayerNameGrp$English$Info02*ACTIVE SET 0\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Single$PlayerNameGrp$English$Info04*ACTIVE SET 0\0", print_writers);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Single$PlayerNameGrp$Telugu$Info01*ACTIVE SET 0\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Single$PlayerNameGrp$Telugu$Info03*ACTIVE SET 1\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Single$PlayerNameGrp$Telugu$Info02*ACTIVE SET 0\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Single$PlayerNameGrp$Telugu$Info04*ACTIVE SET 0\0", print_writers);
				
				break;
			case "Control_Shift_R":
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Select*FUNCTION*Omo*vis_con SET 0\0", print_writers);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Single$Info01*ACTIVE SET 0\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Single$Info03*ACTIVE SET 1\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Single$Info02*ACTIVE SET 0\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Single$Info04*ACTIVE SET 0\0", print_writers);
				
				String matchResult="", teluguMatchResult = "", whichLogo="";
				matchResult = CricketFunctions.GenerateMatchSummaryStatus(inning.getInningNumber(), matchAllData, CricketUtil.FULL, "", 
						config.getBroadcaster(), false).getTargetOrResult().toUpperCase();
				teluguMatchResult = CricketFunctions.GenerateMatchSummaryStatusForeignLanguage(inning.getInningNumber(), matchAllData, CricketUtil.FULL, "", 
						config.getBroadcaster(), false, multilanguagedata).getTeluguText().toUpperCase();
				
				switch(config.getBroadcaster()) {
				case Constants.TG20:
					if(matchAllData.getSetup().getMatchType().equalsIgnoreCase(CricketUtil.SUPER_OVER)) {
						whichLogo = "EVENT";
					}else {
						if(matchResult.contains(matchAllData.getSetup().getHomeTeam().getTeamName1())) {
							whichLogo = matchAllData.getSetup().getHomeTeam().getTeamBadge();
						}else {
							whichLogo = matchAllData.getSetup().getAwayTeam().getTeamBadge();
						}
					}
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Single$img_Base1*TEXTURE*IMAGE SET " + base1Path + whichLogo +"\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Single$img_Base2*TEXTURE*IMAGE SET " + base2Path + whichLogo +"\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Single$img_Badges*TEXTURE*IMAGE SET " + logoPath + whichLogo +"\0", print_writers);
					
					CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Single$PlayerNameGrp$"
							+ "select_Language*FUNCTION*Omo*vis_con SET ", config, Constants.TG20, print_writers, foreignLanguageOmo);
					foreignLanguageData.add(new ForeignLanguageData(matchResult, "", "", teluguMatchResult));
					CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Single$PlayerNameGrp$"
							+ "English$Info03*GEOM*TEXT SET ", config, Constants.TG20, print_writers, foreignLanguageData);
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Single$PlayerNameGrp$English$Info01*ACTIVE SET 0\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Single$PlayerNameGrp$English$Info03*ACTIVE SET 1\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Single$PlayerNameGrp$English$Info02*ACTIVE SET 0\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Single$PlayerNameGrp$English$Info04*ACTIVE SET 0\0", print_writers);
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Single$PlayerNameGrp$Telugu$Info01*ACTIVE SET 0\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Single$PlayerNameGrp$Telugu$Info03*ACTIVE SET 1\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Single$PlayerNameGrp$Telugu$Info02*ACTIVE SET 0\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Single$PlayerNameGrp$Telugu$Info04*ACTIVE SET 0\0", print_writers);
					break;
				default:
					if(matchAllData.getSetup().getMatchType().equalsIgnoreCase(CricketUtil.SUPER_OVER)) {
						if(matchResult.contains("tied")) {
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide 
									+ "$Single$img_Badges*TEXTURE*IMAGE SET "+(config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_LOGO : Constants.MT20_LOGO) +"EVENT"+"\0", print_writers);
							
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Single" + 
									"$img_Base1*TEXTURE*IMAGE SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_BASE1 : Constants.MT20_BASE1)  + "EVENT" +"\0", print_writers);
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Single" + 
									"$img_Base2*TEXTURE*IMAGE SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_BASE2 : Constants.MT20_BASE2)  + "EVENT" +"\0", print_writers);
							
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Single$BaseGrp" + 
									"$img_Text1*TEXTURE*IMAGE SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_TEXT1 : Constants.MT20_TEXT1)  + "EVENT" +"\0", print_writers);
							
							
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide 
									+ "$Single$Info03*GEOM*TEXT SET SUPER OVER TIED, WINNER WILL BE DECIDED BY ANOTHER SUPER OVER\0", print_writers);
						}else {
							if(matchAllData.getSetup().getMatchType().equalsIgnoreCase(CricketUtil.SUPER_OVER)) {
								if(CricketFunctions.GetTargetData(matchAllData).getRemaningRuns() <= 0) {
									
									CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Single" + 
											"$img_Base1*TEXTURE*IMAGE SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_BASE1 : Constants.MT20_BASE1)  + 
											matchAllData.getMatch().getInning().get(1).getBatting_team().getTeamBadge() +"\0", print_writers);
									CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Single" + 
											"$img_Base2*TEXTURE*IMAGE SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_BASE2 : Constants.MT20_BASE2)  + 
											matchAllData.getMatch().getInning().get(1).getBatting_team().getTeamBadge() +"\0", print_writers);
									
									CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Single$BaseGrp" + 
											"$img_Text1*TEXTURE*IMAGE SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_TEXT1 : Constants.MT20_TEXT1)  + 
											matchAllData.getMatch().getInning().get(1).getBatting_team().getTeamBadge() +"\0", print_writers);
									
									CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Single$img_Badges*TEXTURE*IMAGE SET "
											+ (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_LOGO : Constants.MT20_LOGO) +matchAllData.getMatch().getInning().get(1).getBatting_team().getTeamBadge()+"\0", print_writers);
									
									CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Single$Info03*GEOM*TEXT SET "
											+ (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? matchAllData.getMatch().getInning().get(1).getBatting_team().getTeamName3() 
											: matchAllData.getMatch().getInning().get(1).getBatting_team().getTeamName1()) + " WIN THE SUPER OVER\0", print_writers);
								}else if(matchAllData.getMatch().getInning().get(1).getTotalWickets() >= 10 || 
										matchAllData.getMatch().getInning().get(1).getTotalOvers() >= matchAllData.getSetup().getMaxOvers()) {
									
									CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Single" + 
											"$img_Base1*TEXTURE*IMAGE SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_BASE1 : Constants.MT20_BASE1)  + 
											matchAllData.getMatch().getInning().get(1).getBowling_team().getTeamBadge() +"\0", print_writers);
									CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Single" + 
											"$img_Base2*TEXTURE*IMAGE SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_BASE2 : Constants.MT20_BASE2)  + 
											matchAllData.getMatch().getInning().get(1).getBowling_team().getTeamBadge() +"\0", print_writers);
									
									CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Single$BaseGrp" + 
											"$img_Text1*TEXTURE*IMAGE SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_TEXT1 : Constants.MT20_TEXT1)  + 
											matchAllData.getMatch().getInning().get(1).getBowling_team().getTeamBadge() +"\0", print_writers);
									
									CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Single$img_Badges*TEXTURE*IMAGE SET "
											+ (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_LOGO : Constants.MT20_LOGO) +matchAllData.getMatch().getInning().get(1).getBowling_team().getTeamBadge()+"\0", print_writers);
									
									CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Single$Info03*GEOM*TEXT SET " + 
											(config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? matchAllData.getMatch().getInning().get(1).getBatting_team().getTeamName3() 
													: matchAllData.getMatch().getInning().get(1).getBatting_team().getTeamName1()) + " WIN THE SUPER OVER\0", print_writers);
								}
							}else {
								for(Team tm : cricketService.getTeams()) {
									if(matchResult.contains(tm.getTeamName1())) {
										
										CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Single" + 
												"$img_Base1*TEXTURE*IMAGE SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_BASE1 : Constants.MT20_BASE1)  + 
												tm.getTeamBadge() +"\0", print_writers);
										CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Single" + 
												"$img_Base2*TEXTURE*IMAGE SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_BASE2 : Constants.MT20_BASE2)  + 
												tm.getTeamBadge() +"\0", print_writers);
										
										CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Single$BaseGrp" + 
												"$img_Text1*TEXTURE*IMAGE SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_TEXT1 : Constants.MT20_TEXT1)  + 
												tm.getTeamBadge() +"\0", print_writers);
										
										CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Single$img_Badges"
												+ "*TEXTURE*IMAGE SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_LOGO : Constants.MT20_LOGO) +tm.getTeamBadge()+"\0", print_writers);
									}
								}
								CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Single$Info03*GEOM*TEXT SET " + 
										matchResult.split("WIN")[0] + " WIN " + matchResult.split("WIN")[1] + "\0", print_writers);
							}
						}
					}else {
						if(matchResult.contains("tied")) {
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Single" + 
									"$img_Base1*TEXTURE*IMAGE SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_BASE1 : Constants.MT20_BASE1)  + "EVENT" +"\0", print_writers);
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Single" + 
									"$img_Base2*TEXTURE*IMAGE SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_BASE2 : Constants.MT20_BASE2)  + "EVENT" +"\0", print_writers);
							
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Single$BaseGrp" + 
									"$img_Text1*TEXTURE*IMAGE SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_TEXT1 : Constants.MT20_TEXT1)  + "EVENT" +"\0", print_writers);
							
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide 
									+ "$Single$img_Badges*TEXTURE*IMAGE SET "+(config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_LOGO : Constants.MT20_LOGO) +"EVENT"+"\0", print_writers);
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide 
									+ "$Single$Info03*GEOM*TEXT SET MATCH TIED, WINNER WILL BE DECIDED BY SUPER OVER\0", print_writers);
						}else {
							for(Team tm : cricketService.getTeams()) {
								if(matchResult.contains(tm.getTeamName1())) {
									
									CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Single" + 
											"$img_Base1*TEXTURE*IMAGE SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_BASE1 : Constants.MT20_BASE1)  + 
											tm.getTeamBadge() +"\0", print_writers);
									CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Single" + 
											"$img_Base2*TEXTURE*IMAGE SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_BASE2 : Constants.MT20_BASE2)  + 
											tm.getTeamBadge() +"\0", print_writers);
									
									CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Single$BaseGrp" + 
											"$img_Text1*TEXTURE*IMAGE SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_TEXT1 : Constants.MT20_TEXT1)  + 
											tm.getTeamBadge() +"\0", print_writers);
									
									CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Single$img_Badges"
											+ "*TEXTURE*IMAGE SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_LOGO : Constants.MT20_LOGO) +tm.getTeamBadge()+"\0", print_writers);
								}
							}
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Single$Info03*GEOM*TEXT SET " 
									+ matchResult.split("WIN")[0] + " WIN " + matchResult.split("WIN")[1] + "\0", print_writers);
						}
					}
					break;
				}
				break;
			case "Control_Shift_J":
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Select*FUNCTION*Omo*vis_con SET 4\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$PowerPlayBug$SelectInfo*FUNCTION*Omo*vis_con SET 1\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$PowerPlayBug$SponsorLogo$select_Sponsor"
						+ "*FUNCTION*Omo*vis_con SET 0\0", print_writers);
				
				switch (config.getBroadcaster()) {
				case Constants.TRI_SERIES:  case Constants.MT20:
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$PowerPlayBug$img_Badges*TEXTURE*IMAGE SET " 
							+ logoPath + (performanceBug.getSponsor() != null ? performanceBug.getSponsor() : "EVENT") + "\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$PowerPlayBug$Logo$img_Base1*TEXTURE*IMAGE SET " 
							+ base1Path + (performanceBug.getSponsor() != null ? performanceBug.getSponsor() : "EVENT") +"\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$PowerPlayBug$Logo$img_Base2*TEXTURE*IMAGE SET " 
							+ base2Path + (performanceBug.getSponsor() != null ? performanceBug.getSponsor() : "EVENT") +"\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$PowerPlayBug$BaseGrp$img_Text1*TEXTURE*IMAGE SET " 
							+ text1Path + (performanceBug.getSponsor() != null ? performanceBug.getSponsor() : "EVENT") +"\0", print_writers);
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$PowerPlayBug$Info01" 
							+ "*GEOM*TEXT SET " + performanceBug.getPlayerName() + "\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$PowerPlayBug$Info03" 
							+ "*GEOM*TEXT SET " + performanceBug.getScore() + "\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$PowerPlayBug$Info04" 
							+ "*GEOM*TEXT SET  " + performanceBug.getText4() + "\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$PowerPlayBug$Info02" 
							+ "*GEOM*TEXT SET " + performanceBug.getSubheader() + "\0", print_writers);
					break;
				case Constants.TG20:
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$PowerPlayBug$img_Badges*TEXTURE*IMAGE SET " 
							+ logoPath + (performanceBug.getSponsor() != null ? performanceBug.getSponsor() : "EVENT") + "\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$PowerPlayBug$Logo$img_Base1*TEXTURE*IMAGE SET " 
							+ base1Path + (performanceBug.getSponsor() != null ? performanceBug.getSponsor() : "EVENT") +"\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$PowerPlayBug$Logo$img_Base2*TEXTURE*IMAGE SET " 
							+ base2Path + (performanceBug.getSponsor() != null ? performanceBug.getSponsor() : "EVENT") +"\0", print_writers);
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$PowerPlayBug$Info03" 
							+ "*GEOM*TEXT SET " + performanceBug.getScore() + "\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$PowerPlayBug$Info04" 
							+ "*GEOM*TEXT SET  " + performanceBug.getText4() + "\0", print_writers);
					
					CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$PowerPlayBug$PlayerNameGrp$"
							+ "select_Language*FUNCTION*Omo*vis_con SET ", config, Constants.TG20, print_writers, foreignLanguageOmo);
					foreignLanguageData = CricketFunctions.AssembleMultiLanguageData(CricketUtil.PLAYER, CricketUtil.TICKERNAME, multilanguagedata, performanceBug.getPlayerName(), 
							"", null, 0, foreignLanguageDataList);
					CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$PowerPlayBug$PlayerNameGrp$"
							+ "English$Info01*GEOM*TEXT SET ", config, Constants.TG20, print_writers, foreignLanguageData);
					
					foreignLanguageData = CricketFunctions.AssembleMultiLanguageData(CricketUtil.DICTIONARY, "", multilanguagedata, performanceBug.getSubheader(), 
							"", null, 0, foreignLanguageDataList);
					CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$PowerPlayBug$PlayerNameGrp$"
							+ "English$Info02*GEOM*TEXT SET ", config, Constants.TG20, print_writers, foreignLanguageData);
					break;
				}
				break;
			case "Control_y":
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Select*FUNCTION*Omo*vis_con SET 4\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$PowerPlayBug$SelectInfo*FUNCTION*Omo*vis_con SET 0\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$PowerPlayBug$SponsorLogo$select_Sponsor"
						+ "*FUNCTION*Omo*vis_con SET 0\0", print_writers);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$PowerPlayBug$img_Badges*TEXTURE*IMAGE SET "
						+ logoPath + inning.getBatting_team().getTeamBadge() + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$PowerPlayBug$Logo$img_Base1*TEXTURE*IMAGE SET " 
						+ base1Path + inning.getBatting_team().getTeamBadge() +"\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$PowerPlayBug$Logo$img_Base2*TEXTURE*IMAGE SET " 
						+ base2Path + inning.getBatting_team().getTeamBadge() +"\0", print_writers);
				
				switch (config.getBroadcaster()) {
				case Constants.TRI_SERIES:  case Constants.MT20: 
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$PowerPlayBug$BaseGrp" + 
							"$img_Text1*TEXTURE*IMAGE SET " + text1Path  + inning.getBatting_team().getTeamBadge() +"\0", print_writers);
					break;
				}
				
				switch (config.getBroadcaster()) {
				case Constants.TG20:
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$PowerPlayBug$Info03*GEOM*TEXT SET " 
							+ CricketFunctions.getPowerPlayScore(inning, inning.getInningNumber(), "-", matchAllData) + "\0", print_writers);
					
					CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$PowerPlayBug$PlayerNameGrp$"
							+ "select_Language*FUNCTION*Omo*vis_con SET ", config, Constants.TG20, print_writers, foreignLanguageOmo);
					foreignLanguageData = CricketFunctions.AssembleMultiLanguageData(CricketUtil.DICTIONARY, "", multilanguagedata, "POWERPLAY", 
							"", null, 0, foreignLanguageDataList);
					CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$PowerPlayBug$PlayerNameGrp$"
							+ "English$Info01*GEOM*TEXT SET ", config, Constants.TG20, print_writers, foreignLanguageData);
					
					CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$PowerPlayBug$PlayerNameGrp$"
							+ "select_Language*FUNCTION*Omo*vis_con SET ", config, Constants.TG20, print_writers, foreignLanguageOmo);
					foreignLanguageData = CricketFunctions.AssembleMultiLanguageData(CricketUtil.TEAM, CricketUtil.TEAMNAME_4, multilanguagedata, 
							inning.getBatting_team().getTeamName1(), "", null, 0, foreignLanguageDataList);
					CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$PowerPlayBug$PlayerNameGrp$"
							+ "English$Info02*GEOM*TEXT SET ", config, Constants.TG20, print_writers, foreignLanguageData);
					break;
				default:
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$PowerPlayBug$Info01" 
							+ "*GEOM*TEXT SET POWERPLAY\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$PowerPlayBug$Info02" 
							+ "*GEOM*TEXT SET " + inning.getBatting_team().getTeamName1()+ "\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$PowerPlayBug$Info03*GEOM*TEXT SET " 
							+ CricketFunctions.getPowerPlayScore(inning, inning.getInningNumber(), "-", matchAllData) + "\0", print_writers);
					break;
				}
				break;
			case "5":
				
				switch (config.getBroadcaster()) {
				case Constants.TG20:
					CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$PopUps$Tournament_Sixes$band$img_Text2$select_Language*FUNCTION*Omo*vis_con SET ", 
							config, Constants.TG20, print_writers, foreignLanguageOmo);
					foreignLanguageData = CricketFunctions.AssembleMultiLanguageData(CricketUtil.DICTIONARY, "", multilanguagedata, "THIS MATCH FOURS", "", null, 0, foreignLanguageDataList);
					CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$PopUps$Tournament_Sixes$band$img_Text2$English$txt_Header*GEOM*TEXT SET ", 
							config, Constants.TG20, print_writers, foreignLanguageData);
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$PopUps$Select$Tournament_Sixes$band$select_Sponsor*FUNCTION*Omo*vis_con SET 0\0", print_writers);
					break;
				default:
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$PopUps$Tournament_Sixes$band$img_Base2*TEXTURE*IMAGE SET " + base2Path  + "EVENT" +"\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$PopUps$Tournament_Sixes$band$img_Text2*TEXTURE*IMAGE SET " + text2Path  + "EVENT" +"\0", print_writers);
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$PopUps$Tournament_Sixes$Badge$img_Base01*TEXTURE*IMAGE SET " + base1Path  + "EVENT" +"\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$PopUps$Tournament_Sixes$Badge$img_Base02*TEXTURE*IMAGE SET " + base2Path  + "EVENT" +"\0", print_writers);
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$PopUps$Tournament_Sixes$Data$img_Text1*TEXTURE*IMAGE SET " + text1Path  + "EVENT" +"\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$PopUps$Tournament_Sixes$base$img_base1*TEXTURE*IMAGE SET " + base1Path  + "EVENT" +"\0", print_writers);

					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$PopUps$Tournament_Sixes$band$img_Base2*TEXTURE*IMAGE SET "
							+ "IMAGE*/Default/Essentials/Textures/Color01\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$PopUps$Tournament_Sixes$txt_Header*GEOM*TEXT SET THIS MATCH FOURS\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$PopUps$Select$Tournament_Sixes$Sponsor$Select*FUNCTION*Omo*vis_con SET 0\0", print_writers);
					break;
				}
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$PopUps$Select*FUNCTION*Omo*vis_con SET 0\0", print_writers);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$PopUps$Tournament_Sixes$Data$Side_1$txt_Unit*GEOM*TEXT SET " 
						+ this_data_str.get(0).split(",")[2] + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$PopUps$Tournament_Sixes$Data$Side_1$txt_Ten*GEOM*TEXT SET " 
						+ this_data_str.get(0).split(",")[1] + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$PopUps$Tournament_Sixes$Data$Side_1$txt_Hundread*GEOM*TEXT SET " 
						+ this_data_str.get(0).split(",")[0] + "\0", print_writers);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$PopUps$Tournament_Sixes$Data$Side_2$txt_Unit*GEOM*TEXT SET " 
						+ this_data_str.get(1).split(",")[2] + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$PopUps$Tournament_Sixes$Data$Side_2$txt_Ten*GEOM*TEXT SET " 
						+ this_data_str.get(1).split(",")[1] + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$PopUps$Tournament_Sixes$Data$Side_2$txt_Hundread*GEOM*TEXT SET " 
						+ this_data_str.get(1).split(",")[0] + "\0", print_writers);
				
				break;	
			case ";":
				
				switch (config.getBroadcaster()) {
				case Constants.TG20:
					CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$PopUps$Tournament_Sixes$band$img_Text2$select_Language*FUNCTION*Omo*vis_con SET ", 
							config, Constants.TG20, print_writers, foreignLanguageOmo);
					foreignLanguageData = CricketFunctions.AssembleMultiLanguageData(CricketUtil.DICTIONARY, "", multilanguagedata, "THIS MATCH SIXES", "", null, 0, foreignLanguageDataList);
					CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$PopUps$Tournament_Sixes$band$img_Text2$English$txt_Header*GEOM*TEXT SET ", 
							config, Constants.TG20, print_writers, foreignLanguageData);
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$PopUps$Select$Tournament_Sixes$band$select_Sponsor*FUNCTION*Omo*vis_con SET 0\0", print_writers);
					break;
				default:
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$PopUps$Tournament_Sixes$band$img_Base2*TEXTURE*IMAGE SET " + base2Path  + "EVENT" +"\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$PopUps$Tournament_Sixes$band$img_Text2*TEXTURE*IMAGE SET " + text2Path  + "EVENT" +"\0", print_writers);
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$PopUps$Tournament_Sixes$Badge$img_Base01*TEXTURE*IMAGE SET " + base1Path  + "EVENT" +"\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$PopUps$Tournament_Sixes$Badge$img_Base02*TEXTURE*IMAGE SET " + base2Path  + "EVENT" +"\0", print_writers);
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$PopUps$Tournament_Sixes$Data$img_Text1*TEXTURE*IMAGE SET " + text1Path  + "EVENT" +"\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$PopUps$Tournament_Sixes$base$img_base1*TEXTURE*IMAGE SET " + base1Path  + "EVENT" +"\0", print_writers);

					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$PopUps$Tournament_Sixes$band$img_Base2*TEXTURE*IMAGE SET "
							+ "IMAGE*/Default/Essentials/Textures/Color01\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$PopUps$Tournament_Sixes$txt_Header*GEOM*TEXT SET THIS MATCH SIXES\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$PopUps$Select$Tournament_Sixes$Sponsor$Select*FUNCTION*Omo*vis_con SET 0\0", print_writers);
					break;
				}
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$PopUps$Select*FUNCTION*Omo*vis_con SET 0\0", print_writers);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$PopUps$Tournament_Sixes$Data$Side_1$txt_Unit*GEOM*TEXT SET " 
						+ this_data_str.get(0).split(",")[2] + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$PopUps$Tournament_Sixes$Data$Side_1$txt_Ten*GEOM*TEXT SET " 
						+ this_data_str.get(0).split(",")[1] + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$PopUps$Tournament_Sixes$Data$Side_1$txt_Hundread*GEOM*TEXT SET " 
						+ this_data_str.get(0).split(",")[0] + "\0", print_writers);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$PopUps$Tournament_Sixes$Data$Side_2$txt_Unit*GEOM*TEXT SET " 
						+ this_data_str.get(1).split(",")[2] + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$PopUps$Tournament_Sixes$Data$Side_2$txt_Ten*GEOM*TEXT SET " 
						+ this_data_str.get(1).split(",")[1] + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$PopUps$Tournament_Sixes$Data$Side_2$txt_Hundread*GEOM*TEXT SET " 
						+ this_data_str.get(1).split(",")[0] + "\0", print_writers);
				
				break;
			case "Control_4":
				
				switch (config.getBroadcaster()) {
				case Constants.TG20:
					CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$PopUps$Tournament_Sixes$band$img_Text2$select_Language*FUNCTION*Omo*vis_con SET ", 
							config, Constants.TG20, print_writers, foreignLanguageOmo);
					foreignLanguageData = CricketFunctions.AssembleMultiLanguageData(CricketUtil.DICTIONARY, "", multilanguagedata, "TOURNAMENT FOURS", "", null, 0, foreignLanguageDataList);
					CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$PopUps$Tournament_Sixes$band$img_Text2$English$txt_Header*GEOM*TEXT SET ", 
							config, Constants.TG20, print_writers, foreignLanguageData);
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$PopUps$Select$Tournament_Sixes$band$select_Sponsor*FUNCTION*Omo*vis_con SET 1\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$PopUps$Tournament_Sixes$band$Sponsor$Base*TEXTURE*IMAGE SET "
							+ Constants.TG20_SPONSOR + "SREENIDHI" + "\0", print_writers);
					break;
				default:
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$PopUps$Tournament_Sixes$band$img_Base2*TEXTURE*IMAGE SET " + base2Path  + "EVENT" +"\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$PopUps$Tournament_Sixes$band$img_Text2*TEXTURE*IMAGE SET " + text2Path  + "EVENT" +"\0", print_writers);
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$PopUps$Tournament_Sixes$Badge$img_Base01*TEXTURE*IMAGE SET " + base1Path  + "EVENT" +"\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$PopUps$Tournament_Sixes$Badge$img_Base02*TEXTURE*IMAGE SET " + base2Path  + "EVENT" +"\0", print_writers);
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$PopUps$Tournament_Sixes$Data$img_Text1*TEXTURE*IMAGE SET " + text1Path  + "EVENT" +"\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$PopUps$Tournament_Sixes$base$img_base1*TEXTURE*IMAGE SET " + base1Path  + "EVENT" +"\0", print_writers);

					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$PopUps$Tournament_Sixes$band$img_Base2*TEXTURE*IMAGE SET "
							+ "IMAGE*/Default/Essentials/Textures/Color01\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$PopUps$Tournament_Sixes$txt_Header*GEOM*TEXT SET TOURNAMENT FOURS\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$PopUps$Select$Tournament_Sixes$Sponsor$Select*FUNCTION*Omo*vis_con SET 0\0", print_writers);
					break;
				}
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$PopUps$Select*FUNCTION*Omo*vis_con SET 0\0", print_writers);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$PopUps$Tournament_Sixes$Data$Side_1$txt_Unit*GEOM*TEXT SET " 
						+ this_data_str.get(0).split(",")[2] + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$PopUps$Tournament_Sixes$Data$Side_1$txt_Ten*GEOM*TEXT SET " 
						+ this_data_str.get(0).split(",")[1] + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$PopUps$Tournament_Sixes$Data$Side_1$txt_Hundread*GEOM*TEXT SET "
						+ this_data_str.get(0).split(",")[0] + "\0", print_writers);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$PopUps$Tournament_Sixes$Data$Side_2$txt_Unit*GEOM*TEXT SET " 
						+ this_data_str.get(1).split(",")[2] + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$PopUps$Tournament_Sixes$Data$Side_2$txt_Ten*GEOM*TEXT SET " 
						+ this_data_str.get(1).split(",")[1] + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$PopUps$Tournament_Sixes$Data$Side_2$txt_Hundread*GEOM*TEXT SET " 
						+ this_data_str.get(1).split(",")[0] + "\0", print_writers);
				
				break;	
			case "6":
				
				switch (config.getBroadcaster()) {
				case Constants.TG20:
					CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$PopUps$Tournament_Sixes$band$img_Text2$select_Language*FUNCTION*Omo*vis_con SET ", 
							config, Constants.TG20, print_writers, foreignLanguageOmo);
					foreignLanguageData = CricketFunctions.AssembleMultiLanguageData(CricketUtil.DICTIONARY, "", multilanguagedata, "TOURNAMENT SIXES", "", null, 0, foreignLanguageDataList);
					CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$PopUps$Tournament_Sixes$band$img_Text2$English$txt_Header*GEOM*TEXT SET ", 
							config, Constants.TG20, print_writers, foreignLanguageData);
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$PopUps$Select$Tournament_Sixes$band$select_Sponsor*FUNCTION*Omo*vis_con SET 1\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$PopUps$Tournament_Sixes$band$Sponsor$Base*TEXTURE*IMAGE SET "
							+ Constants.TG20_SPONSOR + "GMR_MAIN" + "\0", print_writers);
					break;
				default:
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$PopUps$Tournament_Sixes$band$img_Base2*TEXTURE*IMAGE SET " + base2Path  + "EVENT" +"\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$PopUps$Tournament_Sixes$band$img_Text2*TEXTURE*IMAGE SET " + text2Path  + "EVENT" +"\0", print_writers);
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$PopUps$Tournament_Sixes$Badge$img_Base01*TEXTURE*IMAGE SET " + base1Path  + "EVENT" +"\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$PopUps$Tournament_Sixes$Badge$img_Base02*TEXTURE*IMAGE SET " + base2Path  + "EVENT" +"\0", print_writers);
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$PopUps$Tournament_Sixes$Data$img_Text1*TEXTURE*IMAGE SET " + text1Path  + "EVENT" +"\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$PopUps$Tournament_Sixes$base$img_base1*TEXTURE*IMAGE SET " + base1Path  + "EVENT" +"\0", print_writers);

					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$PopUps$Tournament_Sixes$band$img_Base2*TEXTURE*IMAGE SET "
							+ "IMAGE*/Default/Essentials/Textures/Color01\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$PopUps$Tournament_Sixes$txt_Header*GEOM*TEXT SET TOURNAMENT SIXES\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$PopUps$Select$Tournament_Sixes$Sponsor$Select*FUNCTION*Omo*vis_con SET 0\0", print_writers);
					break;
				}
				
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$PopUps$Select*FUNCTION*Omo*vis_con SET 0\0", print_writers);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$PopUps$Tournament_Sixes$Data$Side_1$txt_Unit*GEOM*TEXT SET " 
						+ this_data_str.get(0).split(",")[2] + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$PopUps$Tournament_Sixes$Data$Side_1$txt_Ten*GEOM*TEXT SET " 
						+ this_data_str.get(0).split(",")[1] + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$PopUps$Tournament_Sixes$Data$Side_1$txt_Hundread*GEOM*TEXT SET " 
						+ this_data_str.get(0).split(",")[0] + "\0", print_writers);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$PopUps$Tournament_Sixes$Data$Side_2$txt_Unit*GEOM*TEXT SET " 
						+ this_data_str.get(1).split(",")[2] + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$PopUps$Tournament_Sixes$Data$Side_2$txt_Ten*GEOM*TEXT SET " 
						+ this_data_str.get(1).split(",")[1] + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$PopUps$Tournament_Sixes$Data$Side_2$txt_Hundread*GEOM*TEXT SET " 
						+ this_data_str.get(1).split(",")[0] + "\0", print_writers);
				
				break;
			case "Control_Shift_U": 
				
				switch (config.getBroadcaster()) {
				case Constants.TG20:
					CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$PopUps$POP_UP$band$Text$select_Language*FUNCTION*Omo*vis_con SET ", 
							config, Constants.TG20, print_writers, foreignLanguageOmo);
					foreignLanguageData = CricketFunctions.AssembleMultiLanguageData(CricketUtil.PLAYER, CricketUtil.TICKERNAME, multilanguagedata, battingCard.getPlayer().getTicker_name(), 
							"", null, 0, foreignLanguageDataList);
					CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$PopUps$POP_UP$band$Text$English$txt_Header*GEOM*TEXT SET ", 
							config, Constants.TG20, print_writers, foreignLanguageData);
					
					switch (whatToProcess.split(",")[4].toUpperCase()) {
					case "WITHOUT":
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$PopUps$Select$POP_UP$band$select_Sponsor"
								+ "*FUNCTION*Omo*vis_con SET 0\0", print_writers);
						break;
					case "WITH":
						switch (whatToProcess.split(",")[3].toUpperCase()) {
						case "STRIKERATE":
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$PopUps$Select$POP_UP$band$select_Sponsor"
									+ "*FUNCTION*Omo*vis_con SET 1\0", print_writers);
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$PopUps$POP_UP$band$Sponsor$Base*TEXTURE*IMAGE SET "
									+ Constants.TG20_SPONSOR + "CREADAI_MAIN" + "\0", print_writers);
							break;
						default:
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$PopUps$Select$POP_UP$band$select_Sponsor"
									+ "*FUNCTION*Omo*vis_con SET 0\0", print_writers);
							break;
						}
						break;
					}
					break;
				default:
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$PopUps$POP_UP$band$Base*TEXTURE*IMAGE SET " + base1Path + inning.getBatting_team().getTeamBadge() +"\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$PopUps$POP_UP$band$Text*TEXTURE*IMAGE SET " + text1Path  + inning.getBatting_team().getTeamBadge() +"\0", print_writers);
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$PopUps$POP_UP$base$Side" + WhichSide + "$Sub_Header$Text*TEXTURE*IMAGE SET " + text1Path 
							+ inning.getBatting_team().getTeamBadge() +"\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$PopUps$POP_UP$base$Side" + WhichSide + "$ScoreGrp$Text*TEXTURE*IMAGE SET " + text1Path 
							+ inning.getBatting_team().getTeamBadge() +"\0", print_writers);

					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$PopUps$POP_UP$band$img_Base2*TEXTURE*IMAGE SET IMAGE*/Default/Essentials/Textures/Color01\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$PopUps$POP_UP$band$txt_Header*GEOM*TEXT SET " + battingCard.getPlayer().getTicker_name()+ "\0", print_writers);
					
					break;
				}
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$PopUps$POP_UP$Badge$img_Badges*TEXTURE*IMAGE SET " + logoPath + inning.getBatting_team().getTeamBadge() + "\0", print_writers);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$PopUps$POP_UP$Badge$img_Base01*TEXTURE*IMAGE SET " + base1Path  + inning.getBatting_team().getTeamBadge() +"\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$PopUps$POP_UP$Badge$img_Base02*TEXTURE*IMAGE SET " + base2Path + inning.getBatting_team().getTeamBadge() +"\0", print_writers);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$PopUps$Select*FUNCTION*Omo*vis_con SET 1\0", print_writers);
				
				switch (whatToProcess.split(",")[3].toUpperCase()) {
				case "SCORE":
					switch (config.getBroadcaster()) {
					case Constants.TG20:
						CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$PopUps$POP_UP$Side" + WhichSide + "$Sub_Header$select_Language"
								+ "*FUNCTION*Omo*vis_con SET ", config, Constants.TG20, print_writers, foreignLanguageOmo);
						foreignLanguageData = CricketFunctions.AssembleMultiLanguageData(CricketUtil.DICTIONARY, "", multilanguagedata, "THIS MATCH", "", null, 0, foreignLanguageDataList);
						CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$PopUps$POP_UP$Side" + WhichSide + "$Sub_Header$English$StatHead1*GEOM*TEXT SET ", 
								config, Constants.TG20, print_writers, foreignLanguageData);
						break;
					default:
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$PopUps$POP_UP$Side" + WhichSide + "$StatHead1*GEOM*TEXT SET THIS MATCH\0", print_writers);
						break;
					}
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$PopUps$POP_UP$Side" + WhichSide + "$Seperator*ACTIVE SET 1\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$PopUps$POP_UP$Side" + WhichSide + "$Score*GEOM*TEXT SET " + battingCard.getRuns() 
							+ (battingCard.getStatus().equalsIgnoreCase(CricketUtil.NOT_OUT)?"*":"") + "\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$PopUps$POP_UP$Side" + WhichSide + "$Balls*GEOM*TEXT SET " + battingCard.getBalls() + "\0", print_writers);
					break;
				case "STRIKERATE":
					switch (config.getBroadcaster()) {
					case Constants.TG20:
						CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$PopUps$POP_UP$Side" + WhichSide + "$Sub_Header$select_Language"
								+ "*FUNCTION*Omo*vis_con SET ", config, Constants.TG20, print_writers, foreignLanguageOmo);
						foreignLanguageData = CricketFunctions.AssembleMultiLanguageData(CricketUtil.DICTIONARY, "", multilanguagedata, "STRIKE RATE", "", null, 0, foreignLanguageDataList);
						CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$PopUps$POP_UP$Side" + WhichSide + "$Sub_Header$English$StatHead1*GEOM*TEXT SET ", 
								config, Constants.TG20, print_writers, foreignLanguageData);
						break;
					default:
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$PopUps$POP_UP$Side" + WhichSide + "$StatHead1*GEOM*TEXT SET STRIKE RATE \0", print_writers);
						break;
					}
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$PopUps$POP_UP$Side" + WhichSide + "$Score*GEOM*TEXT SET " 
							+ CricketFunctions.generateStrikeRate(battingCard.getRuns(), battingCard.getBalls(), 1) + "\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$PopUps$POP_UP$Side" + WhichSide + "$Seperator*ACTIVE SET 0\0", print_writers);				
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$PopUps$POP_UP$Side" + WhichSide + "$Balls*GEOM*TEXT SET \0", print_writers);
					break;
				
				case "BOUNDARY":
					switch (config.getBroadcaster()) {
					case Constants.TG20:
						CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$PopUps$POP_UP$Side" + WhichSide + "$Sub_Header$select_Language"
								+ "*FUNCTION*Omo*vis_con SET ", config, Constants.TG20, print_writers, foreignLanguageOmo);
						foreignLanguageDataList = CricketFunctions.AssembleMultiLanguageData(CricketUtil.DICTIONARY, "", multilanguagedata, "FOURS", "", null, 1,foreignLanguageDataList);
						foreignLanguageDataList = CricketFunctions.AssembleMultiLanguageData(CricketUtil.DICTIONARY, "", multilanguagedata, "/", "", null, 2,foreignLanguageDataList);
						foreignLanguageDataList = CricketFunctions.AssembleMultiLanguageData(CricketUtil.DICTIONARY, "", multilanguagedata, "SIXES", "", null, 3,foreignLanguageDataList);
						
						foreignLanguageData.add(CricketFunctions.MergeForeignLanguageDataListToSingleObject(foreignLanguageDataList));
						CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$PopUps$POP_UP$Side" + WhichSide + "$Sub_Header$English$StatHead1*GEOM*TEXT SET ", 
								config, Constants.TG20, print_writers, foreignLanguageData);
						break;
					default:
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$PopUps$POP_UP$Side" + WhichSide + "$StatHead1*GEOM*TEXT SET FOURS/SIXES \0", print_writers);
						break;
					}
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$PopUps$POP_UP$Side" + WhichSide + "$Score*GEOM*TEXT SET " 
							+ battingCard.getFours() + "/" + battingCard.getSixes() + "\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$PopUps$POP_UP$Side" + WhichSide + "$Seperator*ACTIVE SET 0\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$PopUps$POP_UP$Side" + WhichSide + "$Balls*GEOM*TEXT SET \0", print_writers);
					break;
				case "BOUNDARY_PERCENT":
				    int fours = battingCard.getFours();
				    int sixes = battingCard.getSixes();
				    int totalRuns = battingCard.getRuns();
				    int boundaryRuns = (fours * 4) + (sixes * 6);
				    double boundaryPercent = 0;
				    if (totalRuns > 0) {
				        boundaryPercent = (boundaryRuns * 100.0) / totalRuns;
				    }
				   
				    switch (config.getBroadcaster()) {
					case Constants.TG20:
						CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$PopUps$POP_UP$Side" + WhichSide + "$Sub_Header$select_Language"
								+ "*FUNCTION*Omo*vis_con SET ", config, Constants.TG20, print_writers, foreignLanguageOmo);
						foreignLanguageDataList = CricketFunctions.AssembleMultiLanguageData(CricketUtil.DICTIONARY, "", multilanguagedata, "BOUNDARY", "", null, 1,foreignLanguageDataList);
						foreignLanguageDataList = CricketFunctions.AssembleMultiLanguageData(CricketUtil.DICTIONARY, "", multilanguagedata, "%", "", null, 2,foreignLanguageDataList);
						
						foreignLanguageData.add(CricketFunctions.MergeForeignLanguageDataListToSingleObject(foreignLanguageDataList));
						CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$PopUps$POP_UP$Side" + WhichSide + "$Sub_Header$English$StatHead1*GEOM*TEXT SET ", 
								config, Constants.TG20, print_writers, foreignLanguageData);
						break;
					default:
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$PopUps$POP_UP$Side" + WhichSide + "$StatHead1*GEOM*TEXT SET FOURS/SIXES \0", print_writers);
						break;
					}
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$PopUps$POP_UP$Side" + WhichSide + "$Score*GEOM*TEXT SET " 
							+ String.format("%.0f", boundaryPercent) + "\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$PopUps$POP_UP$Side" + WhichSide + "$Seperator*ACTIVE SET 0\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$PopUps$POP_UP$Side" + WhichSide + "$Balls*GEOM*TEXT SET \0", print_writers);
				    break;
				}
				break;
			case "Control_Shift_V":
				
				switch (config.getBroadcaster()) {
				case Constants.TG20:
					CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$PopUps$POP_UP$band$Text$select_Language*FUNCTION*Omo*vis_con SET ", 
							config, Constants.TG20, print_writers, foreignLanguageOmo);
					foreignLanguageData = CricketFunctions.AssembleMultiLanguageData(CricketUtil.PLAYER, CricketUtil.TICKERNAME, multilanguagedata, bowlingCard.getPlayer().getTicker_name(), 
							"", null, 0, foreignLanguageDataList);
					CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$PopUps$POP_UP$band$Text$English$txt_Header*GEOM*TEXT SET ", 
							config, Constants.TG20, print_writers, foreignLanguageData);
					
					switch (whatToProcess.split(",")[4].toUpperCase()) {
					case "WITHOUT":
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$PopUps$Select$POP_UP$band$select_Sponsor"
								+ "*FUNCTION*Omo*vis_con SET 0\0", print_writers);
						break;
					case "WITH":
						switch (whatToProcess.split(",")[3].toUpperCase()) {
						case "FIGURE":
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$PopUps$Select$POP_UP$band$select_Sponsor"
									+ "*FUNCTION*Omo*vis_con SET 1\0", print_writers);
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$PopUps$POP_UP$band$Sponsor$Base*TEXTURE*IMAGE SET "
									+ Constants.TG20_SPONSOR + "CREADAI_MAIN" + "\0", print_writers);
							break;
						default:
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$PopUps$Select$POP_UP$band$select_Sponsor"
									+ "*FUNCTION*Omo*vis_con SET 0\0", print_writers);
							break;
						}
						break;
					}
					break;
				default:
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$PopUps$POP_UP$band$Base*TEXTURE*IMAGE SET " + base1Path + inning.getBowling_team().getTeamBadge() +"\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$PopUps$POP_UP$band$Text*TEXTURE*IMAGE SET " + text1Path  + inning.getBowling_team().getTeamBadge() +"\0", print_writers);
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$PopUps$POP_UP$base$Side" + WhichSide + "$Sub_Header$Text*TEXTURE*IMAGE SET " + text1Path 
							+ inning.getBowling_team().getTeamBadge() +"\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$PopUps$POP_UP$base$Side" + WhichSide + "$ScoreGrp$Text*TEXTURE*IMAGE SET " + text1Path 
							+ inning.getBowling_team().getTeamBadge() +"\0", print_writers);

					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$PopUps$POP_UP$band$img_Base2*TEXTURE*IMAGE SET IMAGE*/Default/Essentials/Textures/Color01\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$PopUps$POP_UP$band$txt_Header*GEOM*TEXT SET " + battingCard.getPlayer().getTicker_name()+ "\0", print_writers);
					
					break;
				}
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$PopUps$POP_UP$Badge$img_Badges*TEXTURE*IMAGE SET " + logoPath + inning.getBowling_team().getTeamBadge() + "\0", print_writers);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$PopUps$POP_UP$Badge$img_Base01*TEXTURE*IMAGE SET " + base1Path + inning.getBowling_team().getTeamBadge() +"\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$PopUps$POP_UP$Badge$img_Base02*TEXTURE*IMAGE SET " + base2Path  + inning.getBowling_team().getTeamBadge() +"\0", print_writers);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$PopUps$Select*FUNCTION*Omo*vis_con SET 1\0", print_writers);
				
				switch (whatToProcess.split(",")[3].toUpperCase()) {
				case "FIGURE":
					switch (config.getBroadcaster()) {
					case Constants.TG20:
						CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$PopUps$POP_UP$Side" + WhichSide + "$Sub_Header$select_Language"
								+ "*FUNCTION*Omo*vis_con SET ", config, Constants.TG20, print_writers, foreignLanguageOmo);
						foreignLanguageData = CricketFunctions.AssembleMultiLanguageData(CricketUtil.DICTIONARY, "", multilanguagedata, "THIS MATCH", "", null, 0, foreignLanguageDataList);
						CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$PopUps$POP_UP$Side" + WhichSide + "$Sub_Header$English$StatHead1*GEOM*TEXT SET ", 
								config, Constants.TG20, print_writers, foreignLanguageData);
						break;
					default:
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$PopUps$POP_UP$Side" + WhichSide + "$StatHead1*GEOM*TEXT SET THIS MATCH\0", print_writers);
						break;
					}
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$PopUps$POP_UP$Side" + WhichSide + "$Seperator*ACTIVE SET 1\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$PopUps$POP_UP$Side" + WhichSide + "$Score"
							+ "*GEOM*TEXT SET " + bowlingCard.getWickets() +"-"+ bowlingCard.getRuns() + "\0", print_writers);
					
					switch (config.getBroadcaster()) {
					case Constants.TG20:
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$PopUps$POP_UP$Side" + WhichSide + "$Balls"
								+ "*GEOM*TEXT SET " + CricketFunctions.OverBalls(bowlingCard.getOvers(), bowlingCard.getBalls()) + "\0", print_writers);
						break;
					case Constants.TRI_SERIES:  case Constants.MT20:
						if(bowlingCard.getOvers() == 0 && bowlingCard.getBalls() >= 0) {
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$PopUps$POP_UP$Side" + WhichSide + "$Balls"
									+ "*GEOM*TEXT SET " + CricketFunctions.OverBalls(bowlingCard.getOvers(), bowlingCard.getBalls()) + 
									" OVERS" + "\0", print_writers);
						}else if(bowlingCard.getOvers() == 1 && bowlingCard.getBalls() == 0) {
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$PopUps$POP_UP$Side" + WhichSide + "$Balls"
									+ "*GEOM*TEXT SET " + CricketFunctions.OverBalls(bowlingCard.getOvers(), bowlingCard.getBalls()) + 
									" OVER" + "\0", print_writers);
						}else if(bowlingCard.getOvers() == 1 && bowlingCard.getBalls() > 0) {
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$PopUps$POP_UP$Side" + WhichSide + "$Balls"
									+ "*GEOM*TEXT SET " + CricketFunctions.OverBalls(bowlingCard.getOvers(), bowlingCard.getBalls()) + 
									" OVERS" + "\0", print_writers);
						}else {
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$PopUps$POP_UP$Side" + WhichSide + "$Balls"
									+ "*GEOM*TEXT SET " + CricketFunctions.OverBalls(bowlingCard.getOvers(), bowlingCard.getBalls()) + 
									" OVERS" + "\0", print_writers);
						}
						break;
					}
					break;

				case "ECONOMY":
					switch (config.getBroadcaster()) {
					case Constants.TG20:
						CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$PopUps$POP_UP$Side" + WhichSide + "$Sub_Header$select_Language"
								+ "*FUNCTION*Omo*vis_con SET ", config, Constants.TG20, print_writers, foreignLanguageOmo);
						foreignLanguageData = CricketFunctions.AssembleMultiLanguageData(CricketUtil.DICTIONARY, "", multilanguagedata, "ECONOMY", "", null, 0, foreignLanguageDataList);
						CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$PopUps$POP_UP$Side" + WhichSide + "$Sub_Header$English$StatHead1*GEOM*TEXT SET ", 
								config, Constants.TG20, print_writers, foreignLanguageData);
						break;
					default:
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$PopUps$POP_UP$Side" + WhichSide + "$StatHead1"
								+ "*GEOM*TEXT SET ECONOMY \0", print_writers);
						break;
					}
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$PopUps$POP_UP$Side" + WhichSide + "$Seperator*ACTIVE SET 0\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$PopUps$POP_UP$Side" + WhichSide + "$Score"
							+ "*GEOM*TEXT SET " + bowlingCard.getEconomyRate() + "\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$PopUps$POP_UP$Side" + WhichSide + "$Balls"
							+ "*GEOM*TEXT SET \0", print_writers);
					break;
					
				case "DOT_PERCENT":
				    String[] Count = CricketFunctions.getScoreTypeData(CricketUtil.BOWLER, matchAllData, inning.getInningNumber(),
				            bowlingCard.getPlayerId(),"-",matchAllData.getEventFile().getEvents()).split("-");
				    int dotBalls = Integer.parseInt(Count[0]);
				    int totalBallsBowled = (bowlingCard.getOvers() * 6) + bowlingCard.getBalls();
				    double dotPercent = 0;
				    if (totalBallsBowled > 0) {
				        dotPercent = (dotBalls * 100.0) / totalBallsBowled;
				    }
				    
				    switch (config.getBroadcaster()) {
					case Constants.TG20:
						CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$PopUps$POP_UP$Side" + WhichSide + "$Sub_Header$select_Language"
								+ "*FUNCTION*Omo*vis_con SET ", config, Constants.TG20, print_writers, foreignLanguageOmo);
						foreignLanguageDataList = CricketFunctions.AssembleMultiLanguageData(CricketUtil.DICTIONARY, "", multilanguagedata, "DOT BALL", "", null, 1,foreignLanguageDataList);
						foreignLanguageDataList = CricketFunctions.AssembleMultiLanguageData(CricketUtil.DICTIONARY, "", multilanguagedata, "%", "", null, 2,foreignLanguageDataList);
						
						foreignLanguageData.add(CricketFunctions.MergeForeignLanguageDataListToSingleObject(foreignLanguageDataList));
						CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$PopUps$POP_UP$Side" + WhichSide + "$Sub_Header$English$StatHead1*GEOM*TEXT SET ", 
								config, Constants.TG20, print_writers, foreignLanguageData);
						break;
					default:
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$PopUps$POP_UP$Side" + WhichSide + "$StatHead1*GEOM*TEXT SET DOT BALL %\0", print_writers);
						break;
					}
				    
				    CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$PopUps$POP_UP$Side" + WhichSide + "$Score*GEOM*TEXT SET " 
							+ String.format("%.0f", dotPercent) + "\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$PopUps$POP_UP$Side" + WhichSide + "$Seperator*ACTIVE SET 0\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$PopUps$POP_UP$Side" + WhichSide + "$Balls*GEOM*TEXT SET \0", print_writers);
				    break;
				}
				break;
			case "g":
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$PowerPlayBug" + "$Logo$img_Base1*TEXTURE*IMAGE SET " 
						+ base1Path  + inning.getBowling_team().getTeamBadge() +"\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$PowerPlayBug" + "$Logo$img_Base2*TEXTURE*IMAGE SET " 
						+ base2Path  + inning.getBowling_team().getTeamBadge() +"\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$PowerPlayBug$img_Badges*TEXTURE*IMAGE SET " 
						+ logoPath + inning.getBowling_team().getTeamBadge()+"\0", print_writers);
				
				switch (config.getBroadcaster()) {
				case Constants.TRI_SERIES:  case Constants.MT20: 
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$PowerPlayBug$BaseGrp" + 
							"$img_Text1*TEXTURE*IMAGE SET " + text1Path  + inning.getBowling_team().getTeamBadge() +"\0", print_writers);
					break;
				}
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Select*FUNCTION*Omo*vis_con SET 4\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$PowerPlayBug$SelectInfo*FUNCTION*Omo*vis_con SET 1\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$PowerPlayBug$SponsorLogo$select_Sponsor"
						+ "*FUNCTION*Omo*vis_con SET 0\0", print_writers);
				
				switch (config.getBroadcaster()) {
				case Constants.TG20:
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$PowerPlayBug$Info03" 
							+ "*GEOM*TEXT SET " + bowlingCard.getWickets() + "-" + bowlingCard.getRuns() + "\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$PowerPlayBug$Info04" 
							+ "*GEOM*TEXT SET  " + CricketFunctions.OverBalls(bowlingCard.getOvers(), bowlingCard.getBalls()) + "\0", print_writers);
					
					CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$PowerPlayBug$PlayerNameGrp$"
							+ "select_Language*FUNCTION*Omo*vis_con SET ", config, Constants.TG20, print_writers, foreignLanguageOmo);
					foreignLanguageData = CricketFunctions.AssembleMultiLanguageData(CricketUtil.PLAYER, CricketUtil.TICKERNAME, multilanguagedata, bowlingCard.getPlayer().getTicker_name(), 
							"", null, 0, foreignLanguageDataList);
					CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$PowerPlayBug$PlayerNameGrp$"
							+ "English$Info01*GEOM*TEXT SET ", config, Constants.TG20, print_writers, foreignLanguageData);
					
					foreignLanguageDataList = CricketFunctions.AssembleMultiLanguageData(CricketUtil.DICTIONARY, "", multilanguagedata, "ECONOMY", "", null, 1,foreignLanguageDataList);
					foreignLanguageDataList = CricketFunctions.AssembleMultiLanguageData(CricketUtil.DICTIONARY, "", multilanguagedata, ": ", "", null, 2,foreignLanguageDataList);
					foreignLanguageDataList = CricketFunctions.AssembleMultiLanguageData(CricketUtil.DICTIONARY, "", multilanguagedata, bowlingCard.getEconomyRate(), 
							"", null, 3,foreignLanguageDataList);
					
					foreignLanguageData.add(CricketFunctions.MergeForeignLanguageDataListToSingleObject(foreignLanguageDataList));
					CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$PowerPlayBug$PlayerNameGrp$"
							+ "English$Info02*GEOM*TEXT SET ", config, Constants.TG20, print_writers, foreignLanguageData);
					break;
				default:
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$PowerPlayBug$Info01" 
							+ "*GEOM*TEXT SET " + bowlingCard.getPlayer().getTicker_name() + "\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$PowerPlayBug$Info03" 
							+ "*GEOM*TEXT SET " + bowlingCard.getWickets() + "-" + bowlingCard.getRuns() + "\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$PowerPlayBug$Info04" 
							+ "*GEOM*TEXT SET  " + CricketFunctions.OverBalls(bowlingCard.getOvers(), bowlingCard.getBalls()) + " OVERS" + "\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$PowerPlayBug$Info02" 
							+ "*GEOM*TEXT SET " +"ECONOMY:  " + bowlingCard.getEconomyRate() + "\0", print_writers);
					break;
				}
				break;
				
			case "y":
	
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$PowerPlayBug" + "$Logo$img_Base1*TEXTURE*IMAGE SET " 
						+ base1Path  + inning.getBatting_team().getTeamBadge() +"\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$PowerPlayBug" + "$Logo$img_Base2*TEXTURE*IMAGE SET " 
						+ base2Path  + inning.getBatting_team().getTeamBadge() +"\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$PowerPlayBug$img_Badges*TEXTURE*IMAGE SET " 
						+ logoPath + inning.getBatting_team().getTeamBadge()+"\0", print_writers);
				
				switch (config.getBroadcaster()) {
				case Constants.TRI_SERIES:  case Constants.MT20: 
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$PowerPlayBug$BaseGrp" + 
							"$img_Text1*TEXTURE*IMAGE SET " + text1Path  + inning.getBatting_team().getTeamBadge() +"\0", print_writers);
					break;
				}
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Select*FUNCTION*Omo*vis_con SET 4\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$PowerPlayBug$SelectInfo*FUNCTION*Omo*vis_con SET 1\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$PowerPlayBug$SponsorLogo$select_Sponsor"
						+ "*FUNCTION*Omo*vis_con SET 0\0", print_writers);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$PowerPlayBug$Info03*GEOM*TEXT SET " 
						+ battingCard.getRuns() + (battingCard.getStatus().equalsIgnoreCase(CricketUtil.NOT_OUT)?"*":"") + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$PowerPlayBug$Info04" 
						+ "*GEOM*TEXT SET  " + battingCard.getBalls() + "\0", print_writers);
				
				switch (config.getBroadcaster()) {
				case Constants.TG20:
					CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$PowerPlayBug$PlayerNameGrp$"
							+ "select_Language*FUNCTION*Omo*vis_con SET ", config, Constants.TG20, print_writers, foreignLanguageOmo);
					foreignLanguageData = CricketFunctions.AssembleMultiLanguageData(CricketUtil.PLAYER, CricketUtil.TICKERNAME, multilanguagedata, battingCard.getPlayer().getTicker_name(), 
							"", null, 0, foreignLanguageDataList);
					CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$PowerPlayBug$PlayerNameGrp$"
							+ "English$Info01*GEOM*TEXT SET ", config, Constants.TG20, print_writers, foreignLanguageData);
					
					if(battingCard.getSixes() != 0 && battingCard.getFours() != 0) {
						foreignLanguageDataList = CricketFunctions.AssembleMultiLanguageData(CricketUtil.DICTIONARY, "", multilanguagedata, "FOURS", "", null, 1,foreignLanguageDataList);
						foreignLanguageDataList = CricketFunctions.AssembleMultiLanguageData(CricketUtil.DICTIONARY, "", multilanguagedata, ": ", "", null, 2,foreignLanguageDataList);
						foreignLanguageDataList = CricketFunctions.AssembleMultiLanguageData(CricketUtil.DICTIONARY, "", multilanguagedata, String.valueOf(battingCard.getFours()), 
								"", null, 3,foreignLanguageDataList);
						foreignLanguageDataList = CricketFunctions.AssembleMultiLanguageData(CricketUtil.DICTIONARY, "", multilanguagedata, "SIXES", "", null, 4,foreignLanguageDataList);
						foreignLanguageDataList = CricketFunctions.AssembleMultiLanguageData(CricketUtil.DICTIONARY, "", multilanguagedata, ": ", "", null, 5,foreignLanguageDataList);
						foreignLanguageDataList = CricketFunctions.AssembleMultiLanguageData(CricketUtil.DICTIONARY, "", multilanguagedata, String.valueOf(battingCard.getSixes()), 
								"", null, 6,foreignLanguageDataList);
					}else if(battingCard.getFours() != 0) {
						foreignLanguageDataList = CricketFunctions.AssembleMultiLanguageData(CricketUtil.DICTIONARY, "", multilanguagedata, "FOURS", "", null, 1,foreignLanguageDataList);
						foreignLanguageDataList = CricketFunctions.AssembleMultiLanguageData(CricketUtil.DICTIONARY, "", multilanguagedata, ": ", "", null, 2,foreignLanguageDataList);
						foreignLanguageDataList = CricketFunctions.AssembleMultiLanguageData(CricketUtil.DICTIONARY, "", multilanguagedata, String.valueOf(battingCard.getFours()), 
								"", null, 3,foreignLanguageDataList);
					}else if(battingCard.getSixes() != 0) {
						foreignLanguageDataList = CricketFunctions.AssembleMultiLanguageData(CricketUtil.DICTIONARY, "", multilanguagedata, "SIXES", "", null, 1,foreignLanguageDataList);
						foreignLanguageDataList = CricketFunctions.AssembleMultiLanguageData(CricketUtil.DICTIONARY, "", multilanguagedata, ": ", "", null, 2,foreignLanguageDataList);
						foreignLanguageDataList = CricketFunctions.AssembleMultiLanguageData(CricketUtil.DICTIONARY, "", multilanguagedata, String.valueOf(battingCard.getSixes()), 
								"", null, 3,foreignLanguageDataList);
					}else {
						foreignLanguageDataList = CricketFunctions.AssembleMultiLanguageData(CricketUtil.DICTIONARY, "", multilanguagedata, "STRIKE RATE", "", null, 1,foreignLanguageDataList);
						foreignLanguageDataList = CricketFunctions.AssembleMultiLanguageData(CricketUtil.DICTIONARY, "", multilanguagedata, ": ", "", null, 2,foreignLanguageDataList);
						foreignLanguageDataList = CricketFunctions.AssembleMultiLanguageData(CricketUtil.DICTIONARY, "", multilanguagedata, CricketFunctions.generateStrikeRate(battingCard.getRuns(), 
								battingCard.getBalls(), 1), "", null, 3,foreignLanguageDataList);
					}
					
					foreignLanguageData.add(CricketFunctions.MergeForeignLanguageDataListToSingleObject(foreignLanguageDataList));
					CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$PowerPlayBug$PlayerNameGrp$"
							+ "English$Info02*GEOM*TEXT SET ", config, Constants.TG20, print_writers, foreignLanguageData);
					break;
				default:
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$PowerPlayBug$Info01" 
							+ "*GEOM*TEXT SET " + battingCard.getPlayer().getTicker_name() + "\0", print_writers);

					if(battingCard.getSixes() != 0 && battingCard.getFours() != 0) {
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$PowerPlayBug$Info02" 
								+ "*GEOM*TEXT SET " +"FOURS:  " + battingCard.getFours() + "   SIXES:  "  + battingCard.getSixes() + "\0", print_writers);
					}else if(battingCard.getFours() != 0) {
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$PowerPlayBug$Info02" 
								+ "*GEOM*TEXT SET " +"FOURS:  " + battingCard.getFours() + "\0", print_writers);
					}else if(battingCard.getSixes() != 0) {
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$PowerPlayBug$Info02" 
								+ "*GEOM*TEXT SET " + "SIXES:  " + battingCard.getSixes() + "\0", print_writers);
					}else {
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$PowerPlayBug$Info02" 
								+ "*GEOM*TEXT SET " + "STRIKE RATE: " + CricketFunctions.generateStrikeRate(battingCard.getRuns(), battingCard.getBalls(), 1) + "\0", print_writers);
					}
					break;
				}
				
				break;
			case "Shift_F":
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$PowerPlayBug" + "$Logo$img_Base1*TEXTURE*IMAGE SET " 
						+ base1Path  + inning.getBatting_team().getTeamBadge() +"\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$PowerPlayBug" + "$Logo$img_Base2*TEXTURE*IMAGE SET " 
						+ base2Path  + inning.getBatting_team().getTeamBadge() +"\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$PowerPlayBug$img_Badges*TEXTURE*IMAGE SET " 
						+ logoPath + inning.getBatting_team().getTeamBadge()+"\0", print_writers);
				
				switch (config.getBroadcaster()) {
				case Constants.TRI_SERIES:  case Constants.MT20: 
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$PowerPlayBug$BaseGrp" + 
							"$img_Text1*TEXTURE*IMAGE SET " + text1Path  + inning.getBatting_team().getTeamBadge() +"\0", print_writers);
					break;
				}
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Select*FUNCTION*Omo*vis_con SET 4\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$PowerPlayBug$SelectInfo*FUNCTION*Omo*vis_con SET 1\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$PowerPlayBug$SponsorLogo$select_Sponsor"
						+ "*FUNCTION*Omo*vis_con SET 0\0", print_writers);
				
				switch (config.getBroadcaster()) {
				case Constants.TG20:
					CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$PowerPlayBug$PlayerNameGrp$"
							+ "select_Language*FUNCTION*Omo*vis_con SET ", config, Constants.TG20, print_writers, foreignLanguageOmo);
					foreignLanguageData = CricketFunctions.AssembleMultiLanguageData(CricketUtil.PLAYER, CricketUtil.TICKERNAME, multilanguagedata, battingCard.getPlayer().getTicker_name(), 
							"", null, 0, foreignLanguageDataList);
					CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$PowerPlayBug$PlayerNameGrp$"
							+ "English$Info01*GEOM*TEXT SET ", config, Constants.TG20, print_writers, foreignLanguageData);
					
					if(battingCard.getWasHowOutFielderSubstitute() != null) {
						if(battingCard.getWasHowOutFielderSubstitute().equalsIgnoreCase("NO")) {
							howOut = CricketFunctions.generateBattingCardForeignLanguage(battingCard.getHowOut(), battingCard.getHowOutPartOne(), battingCard.getHowOutPartTwo(), 
									"", multilanguagedata);
						}else {
							howOut = CricketFunctions.generateBattingCardForeignLanguage(battingCard.getHowOut(), battingCard.getHowOutPartOne(), battingCard.getHowOutPartTwo(), 
									battingCard.getHowOutText(), multilanguagedata);
						}
					}else {
						howOut = CricketFunctions.generateBattingCardForeignLanguage(battingCard.getHowOut(), battingCard.getHowOutPartOne(), battingCard.getHowOutPartTwo(), 
								"", multilanguagedata);
					}
					
					foreignLanguageData.add(new ForeignLanguageData(howOut.getEnglishText().replace("|", ""), howOut.getHindiText().replace("|", ""), howOut.getTamilText().replace("|", ""), 
							howOut.getTeluguText().replace("|", "")));
					CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$PowerPlayBug$PlayerNameGrp$"
							+ "English$Info02*GEOM*TEXT SET ", config, Constants.TG20, print_writers, foreignLanguageData);
					break;
				default:
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$PowerPlayBug$Info01*GEOM*TEXT SET " 
							+ battingCard.getPlayer().getTicker_name() +"\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$PowerPlayBug$Info02*GEOM*TEXT SET "
							+ battingCard.getHowOutText() +"\0", print_writers);
					
					break;
				}
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$PowerPlayBug$Info03*GEOM*TEXT SET "+ battingCard.getRuns() +"\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$PowerPlayBug$Info04*GEOM*TEXT SET "+ battingCard.getBalls() +"\0", print_writers);
				
				break;
			case "Shift_O":
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$PowerPlayBug" + "$Logo$img_Base1*TEXTURE*IMAGE SET " 
						+ base1Path  + inning.getBatting_team().getTeamBadge() +"\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$PowerPlayBug" + "$Logo$img_Base2*TEXTURE*IMAGE SET " 
						+ base2Path  + inning.getBatting_team().getTeamBadge() +"\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$PowerPlayBug$img_Badges*TEXTURE*IMAGE SET " 
						+ logoPath + inning.getBatting_team().getTeamBadge()+"\0", print_writers);
				
				switch (config.getBroadcaster()) {
				case Constants.TRI_SERIES:  case Constants.MT20: 
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$PowerPlayBug$BaseGrp" + 
							"$img_Text1*TEXTURE*IMAGE SET " + text1Path  + inning.getBatting_team().getTeamBadge() +"\0", print_writers);
					break;
				}
				
				switch(sponsor.toUpperCase()) {
				case "WITHOUT":
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$PowerPlayBug$SponsorLogo$select_Sponsor"
							+ "*FUNCTION*Omo*vis_con SET 0\0", print_writers);
					break;
				case "WITH":
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$PowerPlayBug$SponsorLogo$select_Sponsor"
							+ "*FUNCTION*Omo*vis_con SET 1\0", print_writers);
					break;
				}
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Select*FUNCTION*Omo*vis_con SET 4\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$PowerPlayBug$SelectInfo*FUNCTION*Omo*vis_con SET 1\0", print_writers);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$PowerPlayBug$Info03" 
						+ "*GEOM*TEXT SET " + battingCard.getRuns() + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$PowerPlayBug$Info04" 
						+ "*GEOM*TEXT SET  " + battingCard.getBalls() + "\0", print_writers);
				
				switch (config.getBroadcaster()) {
				case Constants.TG20:
					CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$PowerPlayBug$PlayerNameGrp$"
							+ "select_Language*FUNCTION*Omo*vis_con SET ", config, Constants.TG20, print_writers, foreignLanguageOmo);
					foreignLanguageData = CricketFunctions.AssembleMultiLanguageData(CricketUtil.PLAYER, CricketUtil.TICKERNAME, multilanguagedata, battingCard.getPlayer().getTicker_name(), 
							"", null, 0, foreignLanguageDataList);
					CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$PowerPlayBug$PlayerNameGrp$"
							+ "English$Info01*GEOM*TEXT SET ", config, Constants.TG20, print_writers, foreignLanguageData);
					
					if(battingCard.getWasHowOutFielderSubstitute() != null) {
						if(battingCard.getWasHowOutFielderSubstitute().equalsIgnoreCase("NO")) {
							howOut = CricketFunctions.generateBattingCardForeignLanguage(battingCard.getHowOut(), battingCard.getHowOutPartOne(), battingCard.getHowOutPartTwo(), 
									"", multilanguagedata);
						}else {
							howOut = CricketFunctions.generateBattingCardForeignLanguage(battingCard.getHowOut(), battingCard.getHowOutPartOne(), battingCard.getHowOutPartTwo(), 
									battingCard.getHowOutText(), multilanguagedata);
						}
					}else {
						howOut = CricketFunctions.generateBattingCardForeignLanguage(battingCard.getHowOut(), battingCard.getHowOutPartOne(), battingCard.getHowOutPartTwo(), 
								"", multilanguagedata);
					}
					
					foreignLanguageData.add(new ForeignLanguageData(howOut.getEnglishText().replace("|", ""), howOut.getHindiText().replace("|", ""), howOut.getTamilText().replace("|", ""), 
							howOut.getTeluguText().replace("|", "")));
					CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$PowerPlayBug$PlayerNameGrp$"
							+ "English$Info02*GEOM*TEXT SET ", config, Constants.TG20, print_writers, foreignLanguageData);
					break;
				default:
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$PowerPlayBug$Info01" 
							+ "*GEOM*TEXT SET " + battingCard.getPlayer().getTicker_name() + "\0", print_writers);
					
					if(battingCard.getHowOut().equalsIgnoreCase(CricketUtil.RUN_OUT)) {
						if(battingCard.getWasHowOutFielderSubstitute() != null && battingCard.getWasHowOutFielderSubstitute().equalsIgnoreCase(CricketUtil.YES)) {
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$PowerPlayBug$Info02" 
									+ "*GEOM*TEXT SET " + "run out " + " (sub - " + battingCard.getHowOutFielder().getTicker_name() + ")" + "\0", print_writers);
						} else {
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$PowerPlayBug$Info02" 
									+ "*GEOM*TEXT SET " + "run out (" + battingCard.getHowOutFielder().getTicker_name() + ")" + "\0", print_writers);
						}
					}else if(battingCard.getHowOut().equalsIgnoreCase(CricketUtil.STUMPED)) {
						if(battingCard.getWasHowOutFielderSubstitute() != null && battingCard.getWasHowOutFielderSubstitute().equalsIgnoreCase(CricketUtil.YES)) {
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$PowerPlayBug$Info02" 
									+ "*GEOM*TEXT SET " + "st" +  " (sub - " + battingCard.getHowOutFielder().getTicker_name() + ")  b " + 
									battingCard.getHowOutBowler().getTicker_name() + "\0", print_writers);
						} else {
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$PowerPlayBug$Info02" 
									+ "*GEOM*TEXT SET " + "st " + battingCard.getHowOutFielder().getTicker_name() + "  b " + 
									battingCard.getHowOutBowler().getTicker_name() + "\0", print_writers);
						}
					}else if(battingCard.getHowOut().equalsIgnoreCase(CricketUtil.CAUGHT)) {
						if(battingCard.getWasHowOutFielderSubstitute() != null && battingCard.getWasHowOutFielderSubstitute().equalsIgnoreCase(CricketUtil.YES)) {
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$PowerPlayBug$Info02" 
									+ "*GEOM*TEXT SET " + "c" +  " (sub - " + battingCard.getHowOutFielder().getTicker_name() + ")  b " + 
									battingCard.getHowOutBowler().getTicker_name() + "\0", print_writers);
						} else {
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$PowerPlayBug$Info02" 
									+ "*GEOM*TEXT SET " + "c " + battingCard.getHowOutFielder().getTicker_name() + "  b " + 
									battingCard.getHowOutBowler().getTicker_name() + "\0", print_writers);
						}
					}else {
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$PowerPlayBug$Info02" 
								+ "*GEOM*TEXT SET " + battingCard.getHowOutText() + "\0", print_writers);
					}
					
					break;
				}
				break;
			case "Control_k":
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Double$Logo$img_Base1*TEXTURE*IMAGE SET " 
						+ base1Path  + inning.getBatting_team().getTeamBadge() +"\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Double$Logo$img_Base2*TEXTURE*IMAGE SET " 
						+ base2Path  + inning.getBatting_team().getTeamBadge() +"\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Double$img_Badges*TEXTURE*IMAGE SET "
						+ logoPath  +inning.getBatting_team().getTeamBadge()+"\0", print_writers);
				
				switch (config.getBroadcaster()) {
				case Constants.TRI_SERIES:  case Constants.MT20: 
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Double$BaseGrp$img_Text1*TEXTURE*IMAGE SET " 
							+ text1Path  + inning.getBatting_team().getTeamBadge() +"\0", print_writers);
					break;
				}
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Select" 
						+ "*FUNCTION*Omo*vis_con SET 3\0", print_writers);
				
				switch (config.getBroadcaster()) {
				case Constants.TG20:
					CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Double$PlayerNameGrp$"
							+ "select_Language*FUNCTION*Omo*vis_con SET ", config, Constants.TG20, print_writers, foreignLanguageOmo);
					foreignLanguageData = CricketFunctions.AssembleMultiLanguageData(CricketUtil.DICTIONARY, "", multilanguagedata, "CURRENT", "", null, 0, foreignLanguageDataList);
					CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Double$PlayerNameGrp$"
							+ "English$Info01*GEOM*TEXT SET ", config, Constants.TG20, print_writers, foreignLanguageData);
					
					foreignLanguageDataList = CricketFunctions.AssembleMultiLanguageData(CricketUtil.DICTIONARY, "", multilanguagedata, "P'SHIP", "", null, 1,foreignLanguageDataList);
					foreignLanguageDataList = CricketFunctions.AssembleMultiLanguageData(CricketUtil.DICTIONARY, "", multilanguagedata, ": ", "", null, 2,foreignLanguageDataList);
					foreignLanguageData.add(CricketFunctions.MergeForeignLanguageDataListToSingleObject(foreignLanguageDataList));
					CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Double$PlayerNameGrp$"
							+ "English$Info02*GEOM*TEXT SET ", config, Constants.TG20, print_writers, foreignLanguageData);
					
					foreignLanguageDataList = CricketFunctions.AssembleMultiLanguageData(CricketUtil.PLAYER, CricketUtil.TICKERNAME, multilanguagedata, partnership.getFirstPlayer().getTicker_name(), 
							"", null, 1,foreignLanguageDataList);
					foreignLanguageDataList = CricketFunctions.AssembleMultiLanguageData(CricketUtil.DICTIONARY, "", multilanguagedata, (partnership.getPartnershipNumber()== 0 ? "" : 
							partnership.getFirstBatterRuns() + " ("+partnership.getFirstBatterBalls()+")"), "", null, 2,foreignLanguageDataList);
					foreignLanguageData.add(CricketFunctions.MergeForeignLanguageDataListToSingleObject(foreignLanguageDataList));
					CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Double$PlayerNameGrp$"
							+ "English$Info03*GEOM*TEXT SET ", config, Constants.TG20, print_writers, foreignLanguageData);
					
					foreignLanguageDataList = CricketFunctions.AssembleMultiLanguageData(CricketUtil.PLAYER, CricketUtil.TICKERNAME, multilanguagedata, partnership.getSecondPlayer().getTicker_name(), 
							"", null, 1,foreignLanguageDataList);
					foreignLanguageDataList = CricketFunctions.AssembleMultiLanguageData(CricketUtil.DICTIONARY, "", multilanguagedata, (partnership.getPartnershipNumber()== 0 ? "" : 
							partnership.getSecondBatterRuns() + " ("+partnership.getSecondBatterBalls()+")"), "", null, 2,foreignLanguageDataList);
					foreignLanguageData.add(CricketFunctions.MergeForeignLanguageDataListToSingleObject(foreignLanguageDataList));
					CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Double$PlayerNameGrp$"
							+ "English$Info05*GEOM*TEXT SET ", config, Constants.TG20, print_writers, foreignLanguageData);
					
					CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Double$PlayerNameGrp$"
							+ "select_Language*FUNCTION*Omo*vis_con SET ", config, Constants.TG20, print_writers, foreignLanguageOmo);
					foreignLanguageData = CricketFunctions.AssembleMultiLanguageData(CricketUtil.DICTIONARY, "", multilanguagedata, (partnership.getPartnershipNumber()==0 ? "": partnership.getTotalRuns() 
							+ "* (" + partnership.getTotalBalls() + ")"), "", null, 0, foreignLanguageDataList);
					CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Double$PlayerNameGrp$"
							+ "English$Info04*GEOM*TEXT SET ", config, Constants.TG20, print_writers, foreignLanguageData);
					break;
				default:
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Double$Info01" 
							+ "*GEOM*TEXT SET CURRENT \0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Double$Info02" 
							+ "*GEOM*TEXT SET P'SHIP: \0", print_writers);
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Double$Info03*GEOM*TEXT SET " 
							+ partnership.getFirstPlayer().getTicker_name() + " " + (partnership.getPartnershipNumber()== 0 ? "" : partnership.getFirstBatterRuns()
							+ " ("+partnership.getFirstBatterBalls()+")") + " \0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Double$Info05*GEOM*TEXT SET " 
							+ partnership.getSecondPlayer().getTicker_name() + " " + (partnership.getPartnershipNumber()== 0 ? "" : partnership.getSecondBatterRuns()
							+ " ("+partnership.getSecondBatterBalls()+")")+ " \0", print_writers);
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Double$Info04*GEOM*TEXT SET " 
							+ (partnership.getPartnershipNumber()==0 ? "": partnership.getTotalRuns() + "* (" + partnership.getTotalBalls() + ")") + " \0", print_writers);
					break;
				}

				break;
			case "h":
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$PowerPlayBug" + "$Logo$img_Base1*TEXTURE*IMAGE SET " 
						+ base1Path  + inning.getBatting_team().getTeamBadge() +"\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$PowerPlayBug" + "$Logo$img_Base2*TEXTURE*IMAGE SET " 
						+ base2Path  + inning.getBatting_team().getTeamBadge() +"\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$PowerPlayBug$img_Badges*TEXTURE*IMAGE SET " 
						+ logoPath + inning.getBatting_team().getTeamBadge()+"\0", print_writers);
				
				switch (config.getBroadcaster()) {
				case Constants.TRI_SERIES:  case Constants.MT20: 
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$PowerPlayBug$BaseGrp" + 
							"$img_Text1*TEXTURE*IMAGE SET " + text1Path  + inning.getBatting_team().getTeamBadge() +"\0", print_writers);
					break;
				}
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Select*FUNCTION*Omo*vis_con SET 4\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$PowerPlayBug$SelectInfo*FUNCTION*Omo*vis_con SET 1\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$PowerPlayBug$SponsorLogo$select_Sponsor"
						+ "*FUNCTION*Omo*vis_con SET 0\0", print_writers);
				
				switch (config.getBroadcaster()) {
				case Constants.TG20:
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$PowerPlayBug$Info04" 
							+ "*GEOM*TEXT SET  " + CricketFunctions.OverBalls(inning.getTotalOvers(), inning.getTotalBalls()) + "\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$PowerPlayBug$Info03" 
							+ "*GEOM*TEXT SET " + CricketFunctions.getTeamScore(inning, "-", false) + "\0", print_writers);
					
					CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$PowerPlayBug$PlayerNameGrp$"
							+ "select_Language*FUNCTION*Omo*vis_con SET ", config, Constants.TG20, print_writers, foreignLanguageOmo);
					foreignLanguageData = CricketFunctions.AssembleMultiLanguageData(CricketUtil.DICTIONARY, "", multilanguagedata, "HIGHLIGHTS", 
							"", null, 0, foreignLanguageDataList);
					CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$PowerPlayBug$PlayerNameGrp$"
							+ "English$Info01*GEOM*TEXT SET ", config, Constants.TG20, print_writers, foreignLanguageData);
					
					CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$PowerPlayBug$PlayerNameGrp$"
							+ "select_Language*FUNCTION*Omo*vis_con SET ", config, Constants.TG20, print_writers, foreignLanguageOmo);
					foreignLanguageData = CricketFunctions.AssembleMultiLanguageData(CricketUtil.TEAM, CricketUtil.TEAMNAME_4, multilanguagedata, 
							inning.getBatting_team().getTeamName1(), "", null, 0, foreignLanguageDataList);
					CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$PowerPlayBug$PlayerNameGrp$"
							+ "English$Info02*GEOM*TEXT SET ", config, Constants.TG20, print_writers, foreignLanguageData);
					break;
				default:
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$PowerPlayBug$Info01" 
							+ "*GEOM*TEXT SET HIGHLIGHTS\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$PowerPlayBug$Info02" 
							+ "*GEOM*TEXT SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? inning.getBatting_team().getTeamName3() 
						    	    : inning.getBatting_team().getTeamName1()) + "\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$PowerPlayBug$Info04" 
							+ "*GEOM*TEXT SET  " + CricketFunctions.OverBalls(inning.getTotalOvers(), inning.getTotalBalls()) + "\0", print_writers);
					if (inning.getTotalWickets() >= 10) {
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$PowerPlayBug$Info03" 
								+ "*GEOM*TEXT SET " + inning.getTotalRuns() + "\0", print_writers);
					} else {
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$PowerPlayBug$Info03" 
								+ "*GEOM*TEXT SET " + inning.getTotalRuns()+ "-"+ inning.getTotalWickets() + "\0", print_writers);
					}
					break;
				}
				break;	
			case "k":
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Select*FUNCTION*Omo*vis_con SET 0\0", print_writers);
				switch (config.getBroadcaster()) {
				case Constants.TRI_SERIES:  case Constants.MT20:
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Single$img_Base1*TEXTURE*IMAGE SET " 
							+ base1Path + (bug.getSponsor() != null ? bug.getSponsor() : "EVENT") +"\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Single$img_Base2*TEXTURE*IMAGE SET " 
							+ base2Path + (bug.getSponsor() != null ? bug.getSponsor() : "EVENT") +"\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Single$BaseGrp$img_Text1*TEXTURE*IMAGE SET " 
							+ text1Path + (bug.getSponsor() != null ? bug.getSponsor() : "EVENT") +"\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Single$img_Badges*TEXTURE*IMAGE SET "
							+ logoPath + (bug.getSponsor() != null ? bug.getSponsor() : "EVENT") +"\0", print_writers);
					break;
				case Constants.TG20:
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Single$img_Base1*TEXTURE*IMAGE SET " 
							+ base1Path + (bug.getSponsor() != null ? bug.getSponsor() : "EVENT") +"\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Single$img_Base2*TEXTURE*IMAGE SET " 
							+ base2Path + (bug.getSponsor() != null ? bug.getSponsor() : "EVENT") +"\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Single$img_Badges*TEXTURE*IMAGE SET "
							+ logoPath + (bug.getSponsor() != null ? bug.getSponsor() : "EVENT") +"\0", print_writers);
					break;
				}
				
				switch (config.getBroadcaster()) {
				case Constants.TG20:
					CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Single$PlayerNameGrp$"
							+ "select_Language*FUNCTION*Omo*vis_con SET ", config, Constants.TG20, print_writers, foreignLanguageOmo);
					
					foreignLanguageData = CricketFunctions.AssembleMultiLanguageData(CricketUtil.DICTIONARY, "", multilanguagedata, (bug.getText4() != null ? 
							bug.getText4() : ""), "", null, 0, foreignLanguageDataList);
					CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Single$PlayerNameGrp$"
							+ "English$Info04*GEOM*TEXT SET ", config, Constants.TG20, print_writers, foreignLanguageData);
					foreignLanguageData = CricketFunctions.AssembleMultiLanguageData(CricketUtil.DICTIONARY, "", multilanguagedata, (bug.getText3() != null ? 
							bug.getText3() : ""), "", null, 0, foreignLanguageDataList);
					CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Single$PlayerNameGrp$"
							+ "English$Info03*GEOM*TEXT SET ", config, Constants.TG20, print_writers, foreignLanguageData);
					foreignLanguageData = CricketFunctions.AssembleMultiLanguageData(CricketUtil.DICTIONARY, "", multilanguagedata, (bug.getText2() != null ? 
							bug.getText2() : ""), "", null, 0, foreignLanguageDataList);
					CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Single$PlayerNameGrp$"
							+ "English$Info02*GEOM*TEXT SET ", config, Constants.TG20, print_writers, foreignLanguageData);
					foreignLanguageData = CricketFunctions.AssembleMultiLanguageData(CricketUtil.DICTIONARY, "", multilanguagedata, (bug.getText1() != null ? 
							bug.getText1() : ""), "", null, 0, foreignLanguageDataList);
					CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Single$PlayerNameGrp$"
							+ "English$Info01*GEOM*TEXT SET ", config, Constants.TG20, print_writers, foreignLanguageData);
					break;
				default:
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Single$Info04*GEOM*TEXT SET " 
							+ (bug.getText4() != null ? bug.getText4() : "") +"\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Single$Info03*GEOM*TEXT SET " 
							+ (bug.getText3() != null ? bug.getText3() : "") +"\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Single$Info02*GEOM*TEXT SET " 
							+ (bug.getText2() != null ? bug.getText2() : "") +"\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Single$Info01*GEOM*TEXT SET " 
							+ (bug.getText1() != null ? bug.getText1() : "") +"\0", print_writers);
					break;
				}
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Single$PlayerNameGrp$English$Info01*ACTIVE SET 1\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Single$PlayerNameGrp$English$Info03*ACTIVE SET 1\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Single$PlayerNameGrp$English$Info02*ACTIVE SET 1\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Single$PlayerNameGrp$English$Info04*ACTIVE SET 1\0", print_writers);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Single$PlayerNameGrp$Telugu$Info01*ACTIVE SET 1\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Single$PlayerNameGrp$Telugu$Info03*ACTIVE SET 1\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Single$PlayerNameGrp$Telugu$Info02*ACTIVE SET 1\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Single$PlayerNameGrp$Telugu$Info04*ACTIVE SET 1\0", print_writers);

				break;
			case "Alt_p":
				switch (config.getBroadcaster()) {
				case Constants.TG20:
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Toss_Bug$Side" + WhichSide + "$Single$img_Base1*TEXTURE*IMAGE SET " 
							+ base1Path  + team.getTeamBadge() +"\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Toss_Bug$Side" + WhichSide + "$Single$img_Base2*TEXTURE*IMAGE SET " 
							+ base2Path  + team.getTeamBadge() +"\0", print_writers);
//					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Toss_Bug$Side" + WhichSide + "$Single$BaseGrp$img_Text1*TEXTURE*IMAGE SET " 
//							+ text1Path  + team.getTeamBadge() +"\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Toss_Bug$Side" + WhichSide + "$Single$img_Badges*TEXTURE*IMAGE SET "
							+ logoPath  + team.getTeamBadge()+"\0", print_writers);
					
					
					CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$Toss_Bug$Side" + WhichSide + "$Single$PlayerNameGrp$"
							+ "select_Language*FUNCTION*Omo*vis_con SET ", config, Constants.TG20, print_writers, foreignLanguageOmo);
					foreignLanguageDataList = CricketFunctions.AssembleMultiLanguageData(CricketUtil.TEAM, "", multilanguagedata,team.getTeamName1(), "", null, 1, foreignLanguageDataList);
					foreignLanguageDataList = CricketFunctions.AssembleMultiLanguageData(CricketUtil.DICTIONARY, "", multilanguagedata, "WON THE TOSS & CHOSE TO " + 
							whatToProcess.split(",")[3], "", null, 2, foreignLanguageDataList);
					foreignLanguageData.add(CricketFunctions.MergeForeignLanguageDataListToSingleObject(foreignLanguageDataList));
					CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$Toss_Bug$Side" + WhichSide + "$Single$PlayerNameGrp$English$txt_info"
							+ "*GEOM*TEXT SET ",config, Constants.TG20, print_writers, foreignLanguageData);
					break;
				default:
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Single$img_Base1*TEXTURE*IMAGE SET " 
							+ base1Path  + team.getTeamBadge() +"\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Single$img_Base2*TEXTURE*IMAGE SET " 
							+ base2Path  + team.getTeamBadge() +"\0", print_writers);
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Single$BaseGrp$img_Text1*TEXTURE*IMAGE SET " 
							+ text1Path  + team.getTeamBadge() +"\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Select*FUNCTION*Omo*vis_con SET 0\0", print_writers);
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Single$img_Badges*TEXTURE*IMAGE SET "
							+ logoPath  +team.getTeamBadge()+"\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Single$Info03*GEOM*TEXT SET " 
							 + team.getTeamName1() + " WON THE TOSS & CHOSE TO " + whatToProcess.split(",")[2].split("-")[1] + "\0", print_writers);
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Single$Info01*ACTIVE SET 0\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Single$Info02*ACTIVE SET 0\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Single$Info03*ACTIVE SET 1\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Single$Info04*ACTIVE SET 0\0", print_writers);
					break;
				}
				break;
			case "Shift_F4":
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Double$Logo$img_Base1*TEXTURE*IMAGE SET " 
						+ base1Path  + inning.getBatting_team().getTeamBadge() +"\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Double$Logo$img_Base2*TEXTURE*IMAGE SET " 
						+ base2Path  + inning.getBatting_team().getTeamBadge() +"\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Double$img_Badges*TEXTURE*IMAGE SET "
						+ logoPath  +inning.getBatting_team().getTeamBadge()+"\0", print_writers);
				
				switch (config.getBroadcaster()) {
				case Constants.TRI_SERIES:  case Constants.MT20: 
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Double$BaseGrp$img_Text1*TEXTURE*IMAGE SET " 
							+ text1Path  + inning.getBatting_team().getTeamBadge() +"\0", print_writers);
					break;
				}
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Select" 
						+ "*FUNCTION*Omo*vis_con SET 3\0", print_writers);
				
				switch (config.getBroadcaster()) {
				case Constants.TG20:
					if(partnership.getPartnershipNumber() == 0) {
						CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Double$PlayerNameGrp$"
								+ "select_Language*FUNCTION*Omo*vis_con SET ", config, Constants.TG20, print_writers, foreignLanguageOmo);
						foreignLanguageData = CricketFunctions.AssembleMultiLanguageData(CricketUtil.DICTIONARY, "", multilanguagedata, "", "", null, 0, foreignLanguageDataList);
						CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Double$PlayerNameGrp$"
								+ "English$Info01*GEOM*TEXT SET ", config, Constants.TG20, print_writers, foreignLanguageData);
						
						CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Double$PlayerNameGrp$"
								+ "select_Language*FUNCTION*Omo*vis_con SET ", config, Constants.TG20, print_writers, foreignLanguageOmo);
						foreignLanguageData = CricketFunctions.AssembleMultiLanguageData(CricketUtil.DICTIONARY, "", multilanguagedata, (partnership.getPartnershipNumber() == 
								inning.getPartnerships().size()? "*" :""), "", null, 0, foreignLanguageDataList);
						CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Double$PlayerNameGrp$"
								+ "English$Info04*GEOM*TEXT SET ", config, Constants.TG20, print_writers, foreignLanguageData);
						
						CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Double$PlayerNameGrp$"
								+ "select_Language*FUNCTION*Omo*vis_con SET ", config, Constants.TG20, print_writers, foreignLanguageOmo);
						foreignLanguageData = CricketFunctions.AssembleMultiLanguageData(CricketUtil.PLAYER, CricketUtil.TICKERNAME, multilanguagedata, partnership.getFirstPlayer().getTicker_name(), 
								"", null, 0, foreignLanguageDataList);
						CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Double$PlayerNameGrp$"
								+ "English$Info04*GEOM*TEXT SET ", config, Constants.TG20, print_writers, foreignLanguageData);
						
						CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Double$PlayerNameGrp$"
								+ "select_Language*FUNCTION*Omo*vis_con SET ", config, Constants.TG20, print_writers, foreignLanguageOmo);
						foreignLanguageData = CricketFunctions.AssembleMultiLanguageData(CricketUtil.PLAYER, CricketUtil.TICKERNAME, multilanguagedata, partnership.getSecondPlayer().getTicker_name(), 
								"", null, 0, foreignLanguageDataList);
						CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Double$PlayerNameGrp$"
								+ "English$Info04*GEOM*TEXT SET ", config, Constants.TG20, print_writers, foreignLanguageData);
					}else {
						CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Double$PlayerNameGrp$"
								+ "select_Language*FUNCTION*Omo*vis_con SET ", config, Constants.TG20, print_writers, foreignLanguageOmo);
						foreignLanguageDataList = CricketFunctions.AssembleMultiLanguageData(CricketUtil.DICTIONARY, "", multilanguagedata, CricketFunctions.ordinal(partnership.getPartnershipNumber()), 
								"", null, 1,foreignLanguageDataList);
						foreignLanguageDataList = CricketFunctions.AssembleMultiLanguageData(CricketUtil.DICTIONARY, "", multilanguagedata, "WICKET", "", null, 2,foreignLanguageDataList);
						foreignLanguageData.add(CricketFunctions.MergeForeignLanguageDataListToSingleObject(foreignLanguageDataList));
						CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Double$PlayerNameGrp$"
								+ "English$Info01*GEOM*TEXT SET ", config, Constants.TG20, print_writers, foreignLanguageData);
						
						foreignLanguageDataList = CricketFunctions.AssembleMultiLanguageData(CricketUtil.PLAYER, CricketUtil.TICKERNAME, multilanguagedata, partnership.getFirstPlayer().getTicker_name(), 
								"", null, 1,foreignLanguageDataList);
						foreignLanguageDataList = CricketFunctions.AssembleMultiLanguageData(CricketUtil.DICTIONARY, "", multilanguagedata, (partnership.getPartnershipNumber()== 0 ? "" : 
								partnership.getFirstBatterRuns() + " ("+partnership.getFirstBatterBalls()+")"), "", null, 2,foreignLanguageDataList);
						foreignLanguageData.add(CricketFunctions.MergeForeignLanguageDataListToSingleObject(foreignLanguageDataList));
						CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Double$PlayerNameGrp$"
								+ "English$Info03*GEOM*TEXT SET ", config, Constants.TG20, print_writers, foreignLanguageData);
						
						foreignLanguageDataList = CricketFunctions.AssembleMultiLanguageData(CricketUtil.PLAYER, CricketUtil.TICKERNAME, multilanguagedata, partnership.getSecondPlayer().getTicker_name(), 
								"", null, 1,foreignLanguageDataList);
						foreignLanguageDataList = CricketFunctions.AssembleMultiLanguageData(CricketUtil.DICTIONARY, "", multilanguagedata, (partnership.getPartnershipNumber()== 0 ? "" : 
								partnership.getSecondBatterRuns() + " ("+partnership.getSecondBatterBalls()+")"), "", null, 2,foreignLanguageDataList);
						foreignLanguageData.add(CricketFunctions.MergeForeignLanguageDataListToSingleObject(foreignLanguageDataList));
						CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Double$PlayerNameGrp$"
								+ "English$Info05*GEOM*TEXT SET ", config, Constants.TG20, print_writers, foreignLanguageData);
						
						if(partnership.getPartnershipNumber() == inning.getPartnerships().size()) {
							CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Double$PlayerNameGrp$"
									+ "select_Language*FUNCTION*Omo*vis_con SET ", config, Constants.TG20, print_writers, foreignLanguageOmo);
							foreignLanguageData = CricketFunctions.AssembleMultiLanguageData(CricketUtil.DICTIONARY, "", multilanguagedata, partnership.getTotalRuns() + 
									"* (" + partnership.getTotalBalls() + ")", "", null, 0, foreignLanguageDataList);
							CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Double$PlayerNameGrp$"
									+ "English$Info04*GEOM*TEXT SET ", config, Constants.TG20, print_writers, foreignLanguageData);
						}else {
							CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Double$PlayerNameGrp$"
									+ "select_Language*FUNCTION*Omo*vis_con SET ", config, Constants.TG20, print_writers, foreignLanguageOmo);
							foreignLanguageData = CricketFunctions.AssembleMultiLanguageData(CricketUtil.DICTIONARY, "", multilanguagedata, partnership.getTotalRuns() + 
									" (" + partnership.getTotalBalls() + ")", "", null, 0, foreignLanguageDataList);
							CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Double$PlayerNameGrp$"
									+ "English$Info04*GEOM*TEXT SET ", config, Constants.TG20, print_writers, foreignLanguageData);
						}
					}
					
					foreignLanguageDataList = CricketFunctions.AssembleMultiLanguageData(CricketUtil.DICTIONARY, "", multilanguagedata, "P'SHIP", "", null, 1,foreignLanguageDataList);
					foreignLanguageDataList = CricketFunctions.AssembleMultiLanguageData(CricketUtil.DICTIONARY, "", multilanguagedata, ": ", "", null, 2,foreignLanguageDataList);
					foreignLanguageData.add(CricketFunctions.MergeForeignLanguageDataListToSingleObject(foreignLanguageDataList));
					CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Double$PlayerNameGrp$"
							+ "English$Info02*GEOM*TEXT SET ", config, Constants.TG20, print_writers, foreignLanguageData);
					break;
				default:
					if(partnership.getPartnershipNumber() == 0) {
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Double$Info01*GEOM*TEXT SET  \0", print_writers);
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Double$Info04*GEOM*TEXT SET " 
								+(partnership.getPartnershipNumber() == inning.getPartnerships().size()? "*" :"") +" \0", print_writers);
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Double$Info03*GEOM*TEXT SET " 
								+ partnership.getFirstPlayer().getTicker_name() + " \0", print_writers);
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Double$Info05*GEOM*TEXT SET " 
								+ partnership.getSecondPlayer().getTicker_name()  + " \0", print_writers);
					}else {
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Double$Info01*GEOM*TEXT SET " 
								+ CricketFunctions.ordinal(partnership.getPartnershipNumber())+" WICKET" +"\0", print_writers);
						if(partnership.getPartnershipNumber() == inning.getPartnerships().size()) {
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Double$Info04*GEOM*TEXT SET " 
									+ partnership.getTotalRuns() + "* (" + partnership.getTotalBalls() + ")"+"\0", print_writers);
						}else {
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Double$Info04*GEOM*TEXT SET "
									+ partnership.getTotalRuns() + " (" + partnership.getTotalBalls() + ")"+"\0", print_writers);
						}
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Double$Info03*GEOM*TEXT SET " 
								+ partnership.getFirstPlayer().getTicker_name() + " "+partnership.getFirstBatterRuns()+" ("+partnership.getFirstBatterBalls()+")"+"\0", print_writers);
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide + "$Double$Info05*GEOM*TEXT SET " 
								+ partnership.getSecondPlayer().getTicker_name()  + " " + partnership.getSecondBatterRuns()+" ("+partnership.getSecondBatterBalls()+")"+"\0", print_writers);
						
					}
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Bugs$Side" + WhichSide 
							+ "$Double$Info02*GEOM*TEXT SET P'SHIP: \0", print_writers);
					break;
				}
				
				break;	
			}
			break;
		}
		return Constants.OK;
	}
	public String populatePopupSubSideData(int WhichSide, String whatToProcess, MatchAllData matchAllData) {
		
		switch (config.getBroadcaster().toUpperCase()) {
		case Constants.BAN_AFG_SERIES: case Constants.ACC:
			switch (whatToProcess.split(",")[0]) {
			case "Control_Shift_U_change_on":
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$POP_UP$band$img_Text2$txt_Header*GEOM*TEXT SET " + battingCard.getPlayer().getFull_name() + "\0",print_writers);
				
				switch (config.getBroadcaster().toUpperCase()) {
				case Constants.BAN_AFG_SERIES:
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$POP_UP$band$img_Text2*TEXTURE*IMAGE SET " + Constants.BAN_AFG_SERIES_TEXT2 + inning.getBatting_team().getTeamBadge() + "\0",print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$POP_UP$band$img_Base2*TEXTURE*IMAGE SET "+Constants.BAN_AFG_SERIES_BASE2 + inning.getBatting_team().getTeamBadge() + "\0",print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$POP_UP$Badge$img_Base2*TEXTURE*IMAGE SET " + Constants.BAN_AFG_SERIES_BASE2 + inning.getBatting_team().getTeamBadge() + "\0",print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$POP_UP$Badge$img_Badges*TEXTURE*IMAGE SET " + Constants.BAN_AFG_SERIES_LOGO + inning.getBatting_team().getTeamBadge() + "\0",print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$POP_UP$Badge$img_Badges02*TEXTURE*IMAGE SET " + Constants.BAN_AFG_SERIES_LOGO + inning.getBatting_team().getTeamBadge() + "\0",print_writers);
					break;
				case Constants.ACC:
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$POP_UP$Badge$select_Flag*FUNCTION*Omo*vis_con SET 0\0",print_writers);
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$POP_UP$Badge$img_Badges*TEXTURE*IMAGE SET " + Constants.ACC_FLAG + inning.getBatting_team().getTeamBadge() + "\0",print_writers);
					break;
				}
				
				switch (whatToProcess.split(",")[3].toUpperCase()) {
				case "STRIKERATE":
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$POP_UP$Sub_Header$Side" + WhichSide + "$StatHead1*GEOM*TEXT SET " + "STRIKE RATE"+"\0",print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$POP_UP$ScoreGrp$Side" + WhichSide + "$Seperator*ACTIVE SET 0\0",print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$POP_UP$ScoreGrp$Side" + WhichSide + "$Score*GEOM*TEXT SET " + 
							CricketFunctions.generateStrikeRate(battingCard.getRuns(), battingCard.getBalls(), 1) + "\0",print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$POP_UP$ScoreGrp$Side" + WhichSide + "$Balls*GEOM*TEXT SET \0",print_writers);
					break;
				case "SCORE":
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$POP_UP$Sub_Header$Side" + WhichSide + "$StatHead1*GEOM*TEXT SET " + battingCard.getBalls() + 
							" BALL" + CricketFunctions.Plural(battingCard.getBalls()).toUpperCase()+"\0",print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$POP_UP$ScoreGrp$Side" + WhichSide + "$Seperator*ACTIVE SET 0\0",print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$POP_UP$ScoreGrp$Side" + WhichSide + "$Score*GEOM*TEXT SET " 
							+ (battingCard.getStatus().equalsIgnoreCase(CricketUtil.NOT_OUT) ? battingCard.getRuns() + "*" : battingCard.getRuns()) + "\0",print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$POP_UP$ScoreGrp$Side" + WhichSide + "$Balls*GEOM*TEXT SET " + ""+"\0",print_writers);
					break;
				case "BOUNDARY":
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$POP_UP$Sub_Header$Side" + WhichSide + "$StatHead1*GEOM*TEXT SET FOURS/SIXES \0",print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$POP_UP$ScoreGrp$Side" + WhichSide + "$Seperator*ACTIVE SET 0\0",print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$POP_UP$ScoreGrp$Side" + WhichSide + "$Score*GEOM*TEXT SET " + 
							battingCard.getFours() + "/" + battingCard.getSixes()+"\0",print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$POP_UP$ScoreGrp$Side" + WhichSide + "$Balls*GEOM*TEXT SET " + "" + "\0",print_writers);
					break;	
				}
				break;
			case "Control_Shift_V_change_on":
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$POP_UP$band$img_Text2$txt_Header*GEOM*TEXT SET " + bowlingCard.getPlayer().getFull_name() + "\0",print_writers);
				
				switch (config.getBroadcaster().toUpperCase()) {
				case Constants.BAN_AFG_SERIES:
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$POP_UP$band$img_Text2*TEXTURE*IMAGE SET " + Constants.BAN_AFG_SERIES_TEXT2 + inning.getBowling_team().getTeamBadge() + "\0",print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$POP_UP$band$img_Base2*TEXTURE*IMAGE SET "+Constants.BAN_AFG_SERIES_BASE2 + inning.getBowling_team().getTeamBadge() + "\0",print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$POP_UP$Badge$img_Base2*TEXTURE*IMAGE SET " + Constants.BAN_AFG_SERIES_BASE2 + inning.getBowling_team().getTeamBadge() + "\0",print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$POP_UP$Badge$img_Badges*TEXTURE*IMAGE SET " + Constants.BAN_AFG_SERIES_LOGO + inning.getBowling_team().getTeamBadge() + "\0",print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$POP_UP$Badge$img_Badges02*TEXTURE*IMAGE SET " + Constants.BAN_AFG_SERIES_LOGO + inning.getBowling_team().getTeamBadge() + "\0",print_writers);
					break;
				case Constants.ACC:
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$POP_UP$Badge$select_Flag*FUNCTION*Omo*vis_con SET 0\0",print_writers);
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$POP_UP$Badge$img_Badges*TEXTURE*IMAGE SET " + Constants.ACC_FLAG + inning.getBowling_team().getTeamBadge() + "\0",print_writers);
					break;
				}
				
				switch (whatToProcess.split(",")[3].toUpperCase()) {
				case "ECONOMY":
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$POP_UP$Sub_Header$Side" + WhichSide + "$StatHead1*GEOM*TEXT SET " + "ECONOMY" + "\0",print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$POP_UP$ScoreGrp$Side" + WhichSide + "$Seperator*ACTIVE SET 0\0",print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$POP_UP$ScoreGrp$Side" + WhichSide + "$Score*GEOM*TEXT SET " + 
							bowlingCard.getEconomyRate() + "\0",print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$POP_UP$ScoreGrp$Side" + WhichSide + "$Balls*GEOM*TEXT SET \0",print_writers);
					break;

				case "FIGURE":
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$POP_UP$Sub_Header$Side" + WhichSide + "$StatHead1*GEOM*TEXT SET " + "THIS MATCH"+"\0",print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$POP_UP$ScoreGrp$Side" + WhichSide + "$Seperator*ACTIVE SET 1\0",print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$POP_UP$ScoreGrp$Side" + WhichSide + "$Score*GEOM*TEXT SET " + 
							bowlingCard.getWickets() +"-"+ bowlingCard.getRuns() + "\0",print_writers);
					if(bowlingCard.getOvers() == 0 && bowlingCard.getBalls() >= 0) {
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$POP_UP$ScoreGrp$Side" + WhichSide + "$Balls*GEOM*TEXT SET " + 
								CricketFunctions.OverBalls(bowlingCard.getOvers(), bowlingCard.getBalls()) + 
								" OVERS" + "\0",print_writers);
					}else if(bowlingCard.getOvers() == 1 && bowlingCard.getBalls() == 0) {
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$POP_UP$ScoreGrp$Side" + WhichSide + "$Balls*GEOM*TEXT SET " + 
								CricketFunctions.OverBalls(bowlingCard.getOvers(), bowlingCard.getBalls()) + 
								" OVER" + "\0",print_writers);
					}else if(bowlingCard.getOvers() == 1 && bowlingCard.getBalls() > 0) {
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$POP_UP$ScoreGrp$Side" + WhichSide + "$Balls*GEOM*TEXT SET " + 
								CricketFunctions.OverBalls(bowlingCard.getOvers(), bowlingCard.getBalls()) + 
								" OVERS" + "\0",print_writers);
					}else {
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$POP_UP$ScoreGrp$Side" + WhichSide + "$Balls*GEOM*TEXT SET " + 
								CricketFunctions.OverBalls(bowlingCard.getOvers(), bowlingCard.getBalls()) + 
								" OVERS" + "\0",print_writers);
					}
					break;
				}
				break;
			}
			break;
		case Constants.TRI_SERIES:  case Constants.MT20: case Constants.TG20:
			String logoPath, base1Path, base2Path,text1Path;
			if (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES)) {
			    logoPath = Constants.TRI_SERIES_LOGO;
			    base1Path = Constants.TRI_SERIES_BASE1;
			    base2Path = Constants.TRI_SERIES_BASE2;
			    text1Path = Constants.TRI_SERIES_TEXT1;
			} else if (config.getBroadcaster().equalsIgnoreCase(Constants.TG20)) {
			    logoPath = Constants.TG20_LOGO;
			    base1Path = Constants.TG20_BASE1;
			    base2Path = Constants.TG20_BASE2;
			    text1Path = Constants.TG20_TEXT1;
			} else {
			    logoPath = Constants.MT20_LOGO;
			    base1Path = Constants.MT20_BASE1;
			    base2Path = Constants.MT20_BASE2;
			    text1Path = Constants.MT20_TEXT1;
			}
			
			switch (whatToProcess.split(",")[0]) {
			case "Control_Shift_U_change_on":
				
				switch (config.getBroadcaster()) {
				case Constants.TG20:
					CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$PopUps$POP_UP$band$Text$select_Language*FUNCTION*Omo*vis_con SET ", 
							config, Constants.TG20, print_writers, foreignLanguageOmo);
					foreignLanguageData = CricketFunctions.AssembleMultiLanguageData(CricketUtil.PLAYER, CricketUtil.TICKERNAME, multilanguagedata, battingCard.getPlayer().getTicker_name(), 
							"", null, 0, foreignLanguageDataList);
					CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$PopUps$POP_UP$band$Text$English$txt_Header*GEOM*TEXT SET ", 
							config, Constants.TG20, print_writers, foreignLanguageData);
					break;
				default:
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$PopUps$POP_UP$band$Base*TEXTURE*IMAGE SET " + base1Path + inning.getBatting_team().getTeamBadge() +"\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$PopUps$POP_UP$band$Text*TEXTURE*IMAGE SET " + text1Path  + inning.getBatting_team().getTeamBadge() +"\0", print_writers);
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$PopUps$POP_UP$base$Side" + WhichSide + "$Sub_Header$Text*TEXTURE*IMAGE SET " + text1Path 
							+ inning.getBatting_team().getTeamBadge() +"\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$PopUps$POP_UP$base$Side" + WhichSide + "$ScoreGrp$Text*TEXTURE*IMAGE SET " + text1Path 
							+ inning.getBatting_team().getTeamBadge() +"\0", print_writers);

					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$PopUps$POP_UP$band$img_Base2*TEXTURE*IMAGE SET IMAGE*/Default/Essentials/Textures/Color01\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$PopUps$POP_UP$band$txt_Header*GEOM*TEXT SET " + battingCard.getPlayer().getTicker_name()+ "\0", print_writers);
					
					break;
				}
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$PopUps$POP_UP$Badge$img_Badges*TEXTURE*IMAGE SET " + logoPath + inning.getBatting_team().getTeamBadge() + "\0", print_writers);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$PopUps$POP_UP$Badge$img_Base01*TEXTURE*IMAGE SET " + base1Path  + inning.getBatting_team().getTeamBadge() +"\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$PopUps$POP_UP$Badge$img_Base02*TEXTURE*IMAGE SET " + base2Path + inning.getBatting_team().getTeamBadge() +"\0", print_writers);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$PopUps$Select*FUNCTION*Omo*vis_con SET 1\0", print_writers);
				
				switch (whatToProcess.split(",")[3].toUpperCase()) {
				case "SCORE":
					switch (config.getBroadcaster()) {
					case Constants.TG20:
						CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$PopUps$POP_UP$Side" + WhichSide + "$Sub_Header$select_Language"
								+ "*FUNCTION*Omo*vis_con SET ", config, Constants.TG20, print_writers, foreignLanguageOmo);
						foreignLanguageData = CricketFunctions.AssembleMultiLanguageData(CricketUtil.DICTIONARY, "", multilanguagedata, "THIS MATCH", "", null, 0, foreignLanguageDataList);
						CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$PopUps$POP_UP$Side" + WhichSide + "$Sub_Header$English$StatHead1*GEOM*TEXT SET ", 
								config, Constants.TG20, print_writers, foreignLanguageData);
						break;
					default:
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$PopUps$POP_UP$Side" + WhichSide + "$StatHead1*GEOM*TEXT SET THIS MATCH\0", print_writers);
						break;
					}
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$PopUps$POP_UP$Side" + WhichSide + "$Seperator*ACTIVE SET 1\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$PopUps$POP_UP$Side" + WhichSide + "$Score*GEOM*TEXT SET " + battingCard.getRuns() 
							+ (battingCard.getStatus().equalsIgnoreCase(CricketUtil.NOT_OUT)?"*":"") + "\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$PopUps$POP_UP$Side" + WhichSide + "$Balls*GEOM*TEXT SET " + battingCard.getBalls() + "\0", print_writers);
					break;
				case "STRIKERATE":
					switch (config.getBroadcaster()) {
					case Constants.TG20:
						CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$PopUps$POP_UP$Side" + WhichSide + "$Sub_Header$select_Language"
								+ "*FUNCTION*Omo*vis_con SET ", config, Constants.TG20, print_writers, foreignLanguageOmo);
						foreignLanguageData = CricketFunctions.AssembleMultiLanguageData(CricketUtil.DICTIONARY, "", multilanguagedata, "STRIKE RATE", "", null, 0, foreignLanguageDataList);
						CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$PopUps$POP_UP$Side" + WhichSide + "$Sub_Header$English$StatHead1*GEOM*TEXT SET ", 
								config, Constants.TG20, print_writers, foreignLanguageData);
						break;
					default:
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$PopUps$POP_UP$Side" + WhichSide + "$StatHead1*GEOM*TEXT SET STRIKE RATE \0", print_writers);
						break;
					}
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$PopUps$POP_UP$Side" + WhichSide + "$Score*GEOM*TEXT SET " 
							+ CricketFunctions.generateStrikeRate(battingCard.getRuns(), battingCard.getBalls(), 1) + "\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$PopUps$POP_UP$Side" + WhichSide + "$Seperator*ACTIVE SET 0\0", print_writers);				
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$PopUps$POP_UP$Side" + WhichSide + "$Balls*GEOM*TEXT SET \0", print_writers);
					break;
				
				case "BOUNDARY":
					switch (config.getBroadcaster()) {
					case Constants.TG20:
						CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$PopUps$POP_UP$Side" + WhichSide + "$Sub_Header$select_Language"
								+ "*FUNCTION*Omo*vis_con SET ", config, Constants.TG20, print_writers, foreignLanguageOmo);
						foreignLanguageDataList = CricketFunctions.AssembleMultiLanguageData(CricketUtil.DICTIONARY, "", multilanguagedata, "FOURS", "", null, 1,foreignLanguageDataList);
						foreignLanguageDataList = CricketFunctions.AssembleMultiLanguageData(CricketUtil.DICTIONARY, "", multilanguagedata, "/", "", null, 2,foreignLanguageDataList);
						foreignLanguageDataList = CricketFunctions.AssembleMultiLanguageData(CricketUtil.DICTIONARY, "", multilanguagedata, "SIXES", "", null, 3,foreignLanguageDataList);
						
						foreignLanguageData.add(CricketFunctions.MergeForeignLanguageDataListToSingleObject(foreignLanguageDataList));
						CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$PopUps$POP_UP$Side" + WhichSide + "$Sub_Header$English$StatHead1*GEOM*TEXT SET ", 
								config, Constants.TG20, print_writers, foreignLanguageData);
						break;
					default:
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$PopUps$POP_UP$Side" + WhichSide + "$StatHead1*GEOM*TEXT SET FOURS/SIXES \0", print_writers);
						break;
					}
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$PopUps$POP_UP$Side" + WhichSide + "$Score*GEOM*TEXT SET " 
							+ battingCard.getFours() + "/" + battingCard.getSixes() + "\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$PopUps$POP_UP$Side" + WhichSide + "$Seperator*ACTIVE SET 0\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$PopUps$POP_UP$Side" + WhichSide + "$Balls*GEOM*TEXT SET \0", print_writers);
					break;
				case "BOUNDARY_PERCENT":
				    int fours = battingCard.getFours();
				    int sixes = battingCard.getSixes();
				    int totalRuns = battingCard.getRuns();
				    int boundaryRuns = (fours * 4) + (sixes * 6);
				    double boundaryPercent = 0;
				    if (totalRuns > 0) {
				        boundaryPercent = (boundaryRuns * 100.0) / totalRuns;
				    }
				   
				    switch (config.getBroadcaster()) {
					case Constants.TG20:
						CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$PopUps$POP_UP$Side" + WhichSide + "$Sub_Header$select_Language"
								+ "*FUNCTION*Omo*vis_con SET ", config, Constants.TG20, print_writers, foreignLanguageOmo);
						foreignLanguageDataList = CricketFunctions.AssembleMultiLanguageData(CricketUtil.DICTIONARY, "", multilanguagedata, "BOUNDARY", "", null, 1,foreignLanguageDataList);
						foreignLanguageDataList = CricketFunctions.AssembleMultiLanguageData(CricketUtil.DICTIONARY, "", multilanguagedata, "%", "", null, 2,foreignLanguageDataList);
						
						foreignLanguageData.add(CricketFunctions.MergeForeignLanguageDataListToSingleObject(foreignLanguageDataList));
						CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$PopUps$POP_UP$Side" + WhichSide + "$Sub_Header$English$StatHead1*GEOM*TEXT SET ", 
								config, Constants.TG20, print_writers, foreignLanguageData);
						break;
					default:
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$PopUps$POP_UP$Side" + WhichSide + "$StatHead1*GEOM*TEXT SET FOURS/SIXES \0", print_writers);
						break;
					}
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$PopUps$POP_UP$Side" + WhichSide + "$Score*GEOM*TEXT SET " 
							+ String.format("%.0f", boundaryPercent) + "\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$PopUps$POP_UP$Side" + WhichSide + "$Seperator*ACTIVE SET 0\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$PopUps$POP_UP$Side" + WhichSide + "$Balls*GEOM*TEXT SET \0", print_writers);
				    break;
				}
				
				break;
			case "Control_Shift_V_change_on":
				
				switch (config.getBroadcaster()) {
				case Constants.TG20:
					CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$PopUps$POP_UP$band$Text$select_Language*FUNCTION*Omo*vis_con SET ", 
							config, Constants.TG20, print_writers, foreignLanguageOmo);
					foreignLanguageData = CricketFunctions.AssembleMultiLanguageData(CricketUtil.PLAYER, CricketUtil.TICKERNAME, multilanguagedata, bowlingCard.getPlayer().getTicker_name(), 
							"", null, 0, foreignLanguageDataList);
					CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$PopUps$POP_UP$band$Text$English$txt_Header*GEOM*TEXT SET ", 
							config, Constants.TG20, print_writers, foreignLanguageData);
					break;
				default:
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$PopUps$POP_UP$band$Base*TEXTURE*IMAGE SET " + base1Path + inning.getBowling_team().getTeamBadge() +"\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$PopUps$POP_UP$band$Text*TEXTURE*IMAGE SET " + text1Path  + inning.getBowling_team().getTeamBadge() +"\0", print_writers);
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$PopUps$POP_UP$base$Side" + WhichSide + "$Sub_Header$Text*TEXTURE*IMAGE SET " + text1Path 
							+ inning.getBowling_team().getTeamBadge() +"\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$PopUps$POP_UP$base$Side" + WhichSide + "$ScoreGrp$Text*TEXTURE*IMAGE SET " + text1Path 
							+ inning.getBowling_team().getTeamBadge() +"\0", print_writers);

					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$PopUps$POP_UP$band$img_Base2*TEXTURE*IMAGE SET IMAGE*/Default/Essentials/Textures/Color01\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$PopUps$POP_UP$band$txt_Header*GEOM*TEXT SET " + battingCard.getPlayer().getTicker_name()+ "\0", print_writers);
					
					break;
				}
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$PopUps$POP_UP$Badge$img_Badges*TEXTURE*IMAGE SET " + logoPath + inning.getBowling_team().getTeamBadge() + "\0", print_writers);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$PopUps$POP_UP$Badge$img_Base01*TEXTURE*IMAGE SET " + base1Path + inning.getBowling_team().getTeamBadge() +"\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$PopUps$POP_UP$Badge$img_Base02*TEXTURE*IMAGE SET " + base2Path  + inning.getBowling_team().getTeamBadge() +"\0", print_writers);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$PopUps$Select*FUNCTION*Omo*vis_con SET 1\0", print_writers);
				
				switch (whatToProcess.split(",")[3].toUpperCase()) {
				case "FIGURE":
					switch (config.getBroadcaster()) {
					case Constants.TG20:
						CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$PopUps$POP_UP$Side" + WhichSide + "$Sub_Header$select_Language"
								+ "*FUNCTION*Omo*vis_con SET ", config, Constants.TG20, print_writers, foreignLanguageOmo);
						foreignLanguageData = CricketFunctions.AssembleMultiLanguageData(CricketUtil.DICTIONARY, "", multilanguagedata, "THIS MATCH", "", null, 0, foreignLanguageDataList);
						CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$PopUps$POP_UP$Side" + WhichSide + "$Sub_Header$English$StatHead1*GEOM*TEXT SET ", 
								config, Constants.TG20, print_writers, foreignLanguageData);
						break;
					default:
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$PopUps$POP_UP$Side" + WhichSide + "$StatHead1*GEOM*TEXT SET THIS MATCH\0", print_writers);
						break;
					}
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$PopUps$POP_UP$Side" + WhichSide + "$Seperator*ACTIVE SET 1\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$PopUps$POP_UP$Side" + WhichSide + "$Score"
							+ "*GEOM*TEXT SET " + bowlingCard.getWickets() +"-"+ bowlingCard.getRuns() + "\0", print_writers);
					
					switch (config.getBroadcaster()) {
					case Constants.TG20:
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$PopUps$POP_UP$Side" + WhichSide + "$Balls"
								+ "*GEOM*TEXT SET " + CricketFunctions.OverBalls(bowlingCard.getOvers(), bowlingCard.getBalls()) + "\0", print_writers);
						break;
					case Constants.TRI_SERIES:  case Constants.MT20:
						if(bowlingCard.getOvers() == 0 && bowlingCard.getBalls() >= 0) {
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$PopUps$POP_UP$Side" + WhichSide + "$Balls"
									+ "*GEOM*TEXT SET " + CricketFunctions.OverBalls(bowlingCard.getOvers(), bowlingCard.getBalls()) + 
									" OVERS" + "\0", print_writers);
						}else if(bowlingCard.getOvers() == 1 && bowlingCard.getBalls() == 0) {
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$PopUps$POP_UP$Side" + WhichSide + "$Balls"
									+ "*GEOM*TEXT SET " + CricketFunctions.OverBalls(bowlingCard.getOvers(), bowlingCard.getBalls()) + 
									" OVER" + "\0", print_writers);
						}else if(bowlingCard.getOvers() == 1 && bowlingCard.getBalls() > 0) {
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$PopUps$POP_UP$Side" + WhichSide + "$Balls"
									+ "*GEOM*TEXT SET " + CricketFunctions.OverBalls(bowlingCard.getOvers(), bowlingCard.getBalls()) + 
									" OVERS" + "\0", print_writers);
						}else {
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$PopUps$POP_UP$Side" + WhichSide + "$Balls"
									+ "*GEOM*TEXT SET " + CricketFunctions.OverBalls(bowlingCard.getOvers(), bowlingCard.getBalls()) + 
									" OVERS" + "\0", print_writers);
						}
						break;
					}
					break;

				case "ECONOMY":
					switch (config.getBroadcaster()) {
					case Constants.TG20:
						CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$PopUps$POP_UP$Side" + WhichSide + "$Sub_Header$select_Language"
								+ "*FUNCTION*Omo*vis_con SET ", config, Constants.TG20, print_writers, foreignLanguageOmo);
						foreignLanguageData = CricketFunctions.AssembleMultiLanguageData(CricketUtil.DICTIONARY, "", multilanguagedata, "TECONOMY", "", null, 0, foreignLanguageDataList);
						CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$PopUps$POP_UP$Side" + WhichSide + "$Sub_Header$English$StatHead1*GEOM*TEXT SET ", 
								config, Constants.TG20, print_writers, foreignLanguageData);
						break;
					default:
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$PopUps$POP_UP$Side" + WhichSide + "$StatHead1"
								+ "*GEOM*TEXT SET ECONOMY \0", print_writers);
						break;
					}
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$PopUps$POP_UP$Side" + WhichSide + "$Seperator*ACTIVE SET 0\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$PopUps$POP_UP$Side" + WhichSide + "$Score"
							+ "*GEOM*TEXT SET " + bowlingCard.getEconomyRate() + "\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$PopUps$POP_UP$Side" + WhichSide + "$Balls"
							+ "*GEOM*TEXT SET \0", print_writers);
					break;
					
				case "DOT_PERCENT":
				    String[] Count = CricketFunctions.getScoreTypeData(CricketUtil.BOWLER, matchAllData, inning.getInningNumber(),
				            bowlingCard.getPlayerId(),"-",matchAllData.getEventFile().getEvents()).split("-");
				    int dotBalls = Integer.parseInt(Count[0]);
				    int totalBallsBowled = (bowlingCard.getOvers() * 6) + bowlingCard.getBalls();
				    double dotPercent = 0;
				    if (totalBallsBowled > 0) {
				        dotPercent = (dotBalls * 100.0) / totalBallsBowled;
				    }
				    
				    switch (config.getBroadcaster()) {
					case Constants.TG20:
						CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$PopUps$POP_UP$Side" + WhichSide + "$Sub_Header$select_Language"
								+ "*FUNCTION*Omo*vis_con SET ", config, Constants.TG20, print_writers, foreignLanguageOmo);
						foreignLanguageDataList = CricketFunctions.AssembleMultiLanguageData(CricketUtil.DICTIONARY, "", multilanguagedata, "DOT BALL", "", null, 1,foreignLanguageDataList);
						foreignLanguageDataList = CricketFunctions.AssembleMultiLanguageData(CricketUtil.DICTIONARY, "", multilanguagedata, "%", "", null, 2,foreignLanguageDataList);
						
						foreignLanguageData.add(CricketFunctions.MergeForeignLanguageDataListToSingleObject(foreignLanguageDataList));
						CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$PopUps$POP_UP$Side" + WhichSide + "$Sub_Header$English$StatHead1*GEOM*TEXT SET ", 
								config, Constants.TG20, print_writers, foreignLanguageData);
						break;
					default:
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$PopUps$POP_UP$Side" + WhichSide + "$StatHead1*GEOM*TEXT SET DOT BALL %\0", print_writers);
						break;
					}
				    
				    CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$PopUps$POP_UP$Side" + WhichSide + "$Score*GEOM*TEXT SET " 
							+ String.format("%.0f", dotPercent) + "\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$PopUps$POP_UP$Side" + WhichSide + "$Seperator*ACTIVE SET 0\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$PopUps$POP_UP$Side" + WhichSide + "$Balls*GEOM*TEXT SET \0", print_writers);
				    break;
				}
				break;
			}
			break;	
		}
		
		return status;
	}	
	public String populateMiniBody(int WhichSide, String whatToProcess, MatchAllData matchAllData, int WhichInning) throws StreamReadException, DatabindException, FileNotFoundException, IOException {
		switch (config.getBroadcaster().toUpperCase()) {
		case Constants.BAN_AFG_SERIES: case Constants.ACC:
			switch (whatToProcess) {
			case "Alt_F7":
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Mini$Side" + WhichSide + "$Select*FUNCTION*Omo*vis_con SET 2\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Mini$Side" + WhichSide + "$Select$Pointstable"
						+ "$AllDataGrp$CardAll$Header$img_Logo*TEXTURE*IMAGE SET " + Constants.ACC_FLAG + "TLogo_White" + "\0",print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Mini$Side" + WhichSide + "$Pointstable$Header$txt_FirstName"
						+ "*GEOM*TEXT SET POINTS TABLE\0", print_writers);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Mini$Side" + WhichSide + "$Pointstable$Header$txt_LastName"
						+ "*GEOM*TEXT SET " + (WhichGroup.equalsIgnoreCase("GroupA") ? "GROUP A" : "GROUP B") + "\0", print_writers);
				
				rowId = 1;
				for(int i=0; i<=leagueTable.getLeagueTeams().size()-1;i++) {
					rowId++;
					
					if(matchAllData.getSetup().getHomeTeam().getTeamBadge().equalsIgnoreCase(leagueTable.getLeagueTeams().get(i).getTeamName())  
							|| matchAllData.getSetup().getAwayTeam().getTeamBadge().equalsIgnoreCase(leagueTable.getLeagueTeams().get(i).getTeamName())) {
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Mini$Side" + WhichSide + "$Pointstable$Data$Row" + rowId + 
								"$Select*FUNCTION*Omo*vis_con SET 1\0", print_writers);
						containerName = "$Highlight";
					}else {
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Mini$Side" + WhichSide + "$Pointstable$Data$Row" + rowId + 
								"$Select*FUNCTION*Omo*vis_con SET 0\0", print_writers);
						containerName = "$Dehighlight";
					}
					
					if(leagueTable.getLeagueTeams().get(i).getQualifiedStatus().trim().equalsIgnoreCase("")) {
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Mini$Side" + WhichSide + "$Pointstable$Data$Row" + rowId + 
								containerName + "$txt_Pos*GEOM*TEXT SET " + (rowId - 1) + "\0", print_writers);
					}else {
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Mini$Side" + WhichSide + "$Pointstable$Data$Row" + rowId + 
								containerName + "$txt_Pos*GEOM*TEXT SET Q\0", print_writers);
					}
					
					for(Team team : Teams) {
						if(team.getTeamBadge().equalsIgnoreCase(leagueTable.getLeagueTeams().get(i).getTeamName())) {
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Mini$Side" + WhichSide + "$Pointstable$Data$Row" + rowId 
									+ containerName + "$txt_Name*GEOM*TEXT SET " + team.getTeamName1() + "\0", print_writers);
						}
					}
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Mini$Side" + WhichSide + "$Pointstable$Data$Row" + rowId 
							+ containerName + "$txt_1*GEOM*TEXT SET " + leagueTable.getLeagueTeams().get(i).getPlayed() + "\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Mini$Side" + WhichSide + "$Pointstable$Data$Row" + rowId 
							+ containerName + "$txt_2*GEOM*TEXT SET " + leagueTable.getLeagueTeams().get(i).getWon() + "\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Mini$Side" + WhichSide + "$Pointstable$Data$Row" + rowId 
							+ containerName + "$txt_4*GEOM*TEXT SET " + leagueTable.getLeagueTeams().get(i).getNetRunRate() + "\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Mini$Side" + WhichSide + "$Pointstable$Data$Row" + rowId 
							+ containerName + "$txt_3*GEOM*TEXT SET " + leagueTable.getLeagueTeams().get(i).getPoints() + "\0", print_writers);
					
				}
				break;
			case "Shift_F1":
				int row_ids = 0, omo_numb = 0, batting_size = 0;
				String cont_name = "";
				
//				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Main$Select*FUNCTION*Omo*vis_con SET 0 \0",print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Mini$Side" + WhichSide + "$Select*FUNCTION*Omo*vis_con SET 0 \0",print_writers);
				
				switch (config.getBroadcaster().toUpperCase()) {
				case Constants.BAN_AFG_SERIES:
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Mini$Side1$Select$Bating$AllDataGrp$CardAll$Header$img_Logo*TEXTURE*IMAGE SET "
							+ Constants.BAN_AFG_SERIES_LOGO + inning.getBatting_team().getTeamBadge() + "\0",print_writers);
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Mini$Side" + WhichSide + "$Select$Bating$AllDataGrp$CardAll$Header$TeamNameGrp$"
							+ "txt_FirstName*GEOM*TEXT SET " + "" + "\0",print_writers);
					break;
				case Constants.ACC:
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Mini$Side1$Select$Bating$AllDataGrp$CardAll$Header$img_Logo*TEXTURE*IMAGE SET "
							+ Constants.ACC_FLAG + inning.getBatting_team().getTeamBadge() + "\0",print_writers);
					break;
				}
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Mini$Side" + WhichSide + "$Select$Bating$AllDataGrp$CardAll$Header$TeamNameGrp$"
						+ "txt_LastName*GEOM*TEXT SET " + inning.getBatting_team().getTeamName1() + "\0",print_writers);

//				CricketFunctions.DoadWriteCommandToAllViz(
//						"-1 RENDERER*FRONT_LAYER*TREE*$Mini$Side1$Select$Bating$AllDesign$DataBaseAll$SummaryBaseScale$img_base1*TEXTURE*IMAGE SET "
//								+ Constants.BAN_AFG_SERIES_BASE1 + inning.getBatting_team().getTeamBadge() + " \0",print_writers);
//				CricketFunctions.DoadWriteCommandToAllViz(
//						"-1 RENDERER*FRONT_LAYER*TREE*$Mini$Side1$Select$Bating$AllDesign$DataBaseAll$SummaryBaseScale$img_base2*TEXTURE*IMAGE SET "
//								+ Constants.BAN_AFG_SERIES_BASE2 + inning.getBatting_team().getTeamBadge() + " \0",print_writers);

				Collections.sort(inning.getBattingCard());

				for (BattingCard bc : inning.getBattingCard()) {

					row_ids = row_ids + 1;

//					if(CricketFunctions.isImpactPlayer(match.getEventFile().getEvents(), whichInning, bc.getPlayerId()).equalsIgnoreCase(CricketUtil.YES)) {
//						CricketFunctions.DoadWriteCommandToAllViz(
//								"-1 RENDERER*FRONT_LAYER*TREE*$Mini$Side1$Select$Bating$AllDataGrp$CardAll$Data$Data$DataGrp$Row"
//										+ row_id + "$RowAnimation$Select_Star*FUNCTION*Omo*vis_con SET 1 \0",print_writers);
//					} else {
//						CricketFunctions.DoadWriteCommandToAllViz(
//								"-1 RENDERER*FRONT_LAYER*TREE*$Mini$Side1$Select$Bating$AllDataGrp$CardAll$Data$Data$DataGrp$Row"
//										+ row_id + "$RowAnimation$Select_Star*FUNCTION*Omo*vis_con SET 0 \0",print_writers);
//					}
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Mini$Side" + WhichSide + "$Select$Bating$AllDataGrp$CardAll$Data$DataGrp$Row"
									+ row_ids + "$RowAnimation$Select_Star*FUNCTION*Omo*vis_con SET 0 \0",print_writers);

					switch (bc.getStatus().toUpperCase()) {
					case CricketUtil.STILL_TO_BAT:
						omo_numb = 0;
						cont_name = "$Dehighlight";
						
						if(bc.getHowOut() != null) {
							if(bc.getHowOut().toUpperCase().equalsIgnoreCase(CricketUtil.RETIRED_HURT)) {
								//System.out.println(bc.getStatus());
								batting_size+=1;
								
								switch (config.getBroadcaster().toUpperCase()) {
								case Constants.BAN_AFG_SERIES:
									CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Mini$Side" + WhichSide + "$Select$Bating$AllDataGrp$CardAll$Data$DataGrp$Row" + row_ids 
											+ "$RowAnimation$Select" + cont_name + "$img_Base2*TEXTURE*IMAGE SET " + Constants.BAN_AFG_SERIES_BASE2 + inning.getBatting_team().getTeamBadge() + "\0",print_writers);
									CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Mini$Side" + WhichSide + "$Select$Bating$AllDataGrp$CardAll$Data$DataGrp$Row" + row_ids 
											+ "$RowAnimation$Select" + cont_name + "$img_Text2*TEXTURE*IMAGE SET " + Constants.BAN_AFG_SERIES_TEXT2 + inning.getBatting_team().getTeamBadge() + "\0",print_writers);
									break;
								}
								
								CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Mini$Side" + WhichSide + "$Select$Bating$AllDataGrp$CardAll$Data$DataGrp"
											+ "*FUNCTION*Grid*num_row SET " + batting_size + " \0",print_writers);
								CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Mini$Side" + WhichSide + "$Select$Bating$AllDataGrp$CardAll$Data$DataGrp$Row"
											+ row_ids + "$RowAnimation$Select*FUNCTION*Omo*vis_con SET " + String.valueOf(omo_numb) + " \0",print_writers);
								CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Mini$Side" + WhichSide + "$Select$Bating$AllDataGrp$CardAll$Data$DataGrp$Row"
											+ row_ids + "$RowAnimation$Select" + cont_name + "$txt_PlayerName*GEOM*TEXT SET " + bc.getPlayer().getTicker_name() + "\0",print_writers);
								CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Mini$Side" + WhichSide + "$Select$Bating$AllDataGrp$CardAll$Data$DataGrp$Row"
											+ row_ids + "$RowAnimation$Select" + cont_name + "$txt_Data1*GEOM*TEXT SET " + bc.getRuns() + "\0",print_writers);
								
								CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Mini$Side" + WhichSide + "$Select$Bating$AllDataGrp$CardAll$Data$DataGrp$Row"
											+ row_ids + "$RowAnimation$Select" + cont_name + "$txt_Star*ACTIVE SET 0\0",print_writers);
								
								CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Mini$Side" + WhichSide + "$Select$Bating$AllDataGrp$CardAll$Data$DataGrp$Row"
											+ row_ids + "$RowAnimation$Select" + cont_name + "$txt_Data2*GEOM*TEXT SET " + String.valueOf(bc.getBalls()) + "\0",print_writers);
								
								CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Mini$Side" + WhichSide + "$Select$Bating$AllDataGrp$CardAll$Data$DataGrp$Row"
											+ row_ids + "$RowAnimation$Select*FUNCTION*Omo SET 0\0",print_writers);
								
//								if(CricketFunctions.isImpactPlayer(match.getEventFile().getEvents(), whichInning, bc.getPlayerId()).equalsIgnoreCase(CricketUtil.YES)) {
//									CricketFunctions.DoadWriteCommandToAllViz(
//											"-1 RENDERER*FRONT_LAYER*TREE*$Mini$Side1$Select$Bating$AllDataGrp$CardAll$Data$DataGrp$Row"
//													+ row_id + "$RowAnimation$Select*FUNCTION*Omo SET 1\0",print_writers);
//								}else {
//									CricketFunctions.DoadWriteCommandToAllViz(
//											"-1 RENDERER*FRONT_LAYER*TREE*$Mini$Side1$Select$Bating$AllDataGrp$CardAll$Data$DataGrp$Row"
//													+ row_id + "$RowAnimation$Select*FUNCTION*Omo SET 0\0",print_writers);
//								}
							}else if(bc.getHowOut().toUpperCase().equalsIgnoreCase(CricketUtil.ABSENT_HURT)) {
								batting_size+=1;
								CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Mini$Side" + WhichSide + "$Select$Bating$AllDataGrp$CardAll$Data$DataGrp"
											+ "*FUNCTION*Grid*num_row SET " + batting_size + " \0",print_writers);
								
								CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Mini$Side" + WhichSide + "$Select$Bating$AllDataGrp$CardAll$Data$DataGrp$Row"
											+ row_ids + "*ACTIVE SET 1 \0",print_writers);

//								CricketFunctions.DoadWriteCommandToAllViz(
//										"-1 RENDERER*FRONT_LAYER*TREE*$Mini$Side1$Select$Bating$AllDataGrp$CardAll$Data$DataGrp$Row"
//												+ row_id + "$RowAnimation$Select" + cont_name + "$img_Base2*TEXTURE*IMAGE SET "
//												+ base_path + "2/" + "KCL" + "\0",print_writers);
								CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Mini$Side" + WhichSide + "$Select$Bating$AllDataGrp$CardAll$Data$DataGrp$Row"
											+ row_ids + "$RowAnimation$Select*FUNCTION*Omo*vis_con SET " + String.valueOf(omo_numb) + " \0",print_writers);

								CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Mini$Side" + WhichSide + "$Select$Bating$AllDataGrp$CardAll$Data$DataGrp$Row"
											+ row_ids + "$RowAnimation$Select" + cont_name + "$txt_PlayerName*GEOM*TEXT SET " + bc.getPlayer().getTicker_name() + "\0",print_writers);
								CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Mini$Side" + WhichSide + "$Select$Bating$AllDataGrp$CardAll$Data$DataGrp$Row"
											+ row_ids + "$RowAnimation$Select" + cont_name + "$txt_Data1*GEOM*TEXT SET " + bc.getRuns() + "\0",print_writers);
								CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Mini$Side" + WhichSide + "$Select$Bating$AllDataGrp$CardAll$Data$DataGrp$Row"
											+ row_ids + "$RowAnimation$Select" + cont_name + "$txt_Star*ACTIVE SET 0\0",print_writers);
								
								CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Mini$Side" + WhichSide + "$Select$Bating$AllDataGrp$CardAll$Data$DataGrp$Row"
											+ row_ids + "$RowAnimation$Select" + cont_name + "$txt_Data2*GEOM*TEXT SET " + String.valueOf(bc.getBalls()) + "\0",print_writers);
								
								CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Mini$Side" + WhichSide + "$Select$Bating$AllDataGrp$CardAll$Data$DataGrp$Row"
											+ row_ids + "$RowAnimation$Select*FUNCTION*Omo SET 0\0",print_writers);
								
//								if(CricketFunctions.isImpactPlayer(match.getEventFile().getEvents(), whichInning, bc.getPlayerId()).equalsIgnoreCase(CricketUtil.YES)) {
//									CricketFunctions.DoadWriteCommandToAllViz(
//											"-1 RENDERER*FRONT_LAYER*TREE*$Mini$Side1$Select$Bating$AllDataGrp$CardAll$Data$DataGrp$Row"
//													+ row_id + "$RowAnimation$Select*FUNCTION*Omo SET 1\0",print_writers);
//								}else {
//									CricketFunctions.DoadWriteCommandToAllViz(
//											"-1 RENDERER*FRONT_LAYER*TREE*$Mini$Side1$Select$Bating$AllDataGrp$CardAll$Data$DataGrp$Row"
//													+ row_id + "$RowAnimation$Select*FUNCTION*Omo SET 0\0",print_writers);
//								}
							}
						}
						break;
					default :
						switch (bc.getStatus().toUpperCase()) {
						case CricketUtil.OUT:
							omo_numb = 0;
							cont_name = "$Dehighlight";
							batting_size = batting_size + 1;
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Mini$Side" + WhichSide + "$Select$Bating$AllDataGrp$CardAll$Data$DataGrp"
										+ "*FUNCTION*Grid*num_row SET " + batting_size + " \0",print_writers);
							break;
						case CricketUtil.NOT_OUT:
							omo_numb = 1;
							cont_name = "$Highlight";
							batting_size = batting_size + 1;
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Mini$Side" + WhichSide + "$Select$Bating$AllDataGrp$CardAll$Data$DataGrp"
										+ "*FUNCTION*Grid*num_row SET " + batting_size + " \0",print_writers);
							break;
						}
						
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Mini$Side" + WhichSide + "$Select$Bating$AllDataGrp$CardAll$Data$DataGrp$Row"
									+ row_ids + "*ACTIVE SET 1 \0",print_writers);
						
						switch (config.getBroadcaster().toUpperCase()) {
						case Constants.BAN_AFG_SERIES:
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Mini$Side" + WhichSide + "$Select$Bating$AllDataGrp$CardAll$Data$DataGrp$Row" + row_ids + 
									"$RowAnimation$Select" + cont_name + "$img_Base2*TEXTURE*IMAGE SET " + Constants.BAN_AFG_SERIES_BASE2 + inning.getBatting_team().getTeamBadge() + "\0",print_writers);
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Mini$Side" + WhichSide + "$Select$Bating$AllDataGrp$CardAll$Data$DataGrp$Row" + row_ids + 
									"$RowAnimation$Select" + cont_name + "$img_Text2*TEXTURE*IMAGE SET " + Constants.BAN_AFG_SERIES_TEXT2 + inning.getBatting_team().getTeamBadge() + "\0",print_writers);
							break;
						}
						
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Mini$Side" + WhichSide + "$Select$Bating$AllDataGrp$CardAll$Data$DataGrp$Row"
									+ row_ids + "$RowAnimation$Select*FUNCTION*Omo*vis_con SET " + String.valueOf(omo_numb) + " \0",print_writers);

						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Mini$Side" + WhichSide + "$Select$Bating$AllDataGrp$CardAll$Data$DataGrp$Row"
									+ row_ids + "$RowAnimation$Select" + cont_name + "$txt_PlayerName*GEOM*TEXT SET " + bc.getPlayer().getTicker_name() + "\0",print_writers);
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Mini$Side" + WhichSide + "$Select$Bating$AllDataGrp$CardAll$Data$DataGrp$Row"
									+ row_ids + "$RowAnimation$Select" + cont_name + "$txt_Data1*GEOM*TEXT SET " + bc.getRuns() + "\0",print_writers);
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Mini$Side" + WhichSide + "$Select$Bating$AllDataGrp$CardAll$Data$DataGrp$Row"
									+ row_ids + "$RowAnimation$Select" + cont_name + "$txt_Data2*GEOM*TEXT SET " + String.valueOf(bc.getBalls()) + "\0",print_writers);
						
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Main*FUNCTION*ControlObject*in SET ON " + "vBatImpact" + row_ids + " SET " + "0" + "\0",print_writers);
						
//						if(CricketFunctions.isImpactPlayer(match.getEventFile().getEvents(), whichInning, 
//								bc.getPlayerId()).equalsIgnoreCase(CricketUtil.YES)) {
//							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Main*FUNCTION*ControlObject*in SET ON " + "vBatImpact" + 
//								row_id + " SET " + "1" + "\0",print_writers);
//						}else {
//							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Main*FUNCTION*ControlObject*in SET ON " + "vBatImpact" + 
//									row_id + " SET " + "0" + "\0",print_writers);
//						}
						
						if (bc.getStatus().toUpperCase().equalsIgnoreCase(CricketUtil.OUT)) {
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Mini$Side" + WhichSide + "$Select$Bating$AllDataGrp$CardAll$Data$DataGrp$Row"
										+ row_ids + "$RowAnimation$Select" + cont_name + "$txt_Star*ACTIVE SET 0 \0",print_writers);
						} else {
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Mini$Side" + WhichSide + "$Select$Bating$AllDataGrp$CardAll$Data$DataGrp$Row"
										+ row_ids + "$RowAnimation$Select" + cont_name + "$txt_Star*ACTIVE SET 1 \0",print_writers);
						}
						break;
					}
				}
				break;
			case "Alt_F1":
				int row_id=0;
				String con_name = "";
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Mini$Side" + WhichSide + "$Select*FUNCTION*Omo*vis_con SET 0 \0",print_writers);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Mini$Side" + WhichSide + "$Select$Bating$AllDataGrp$CardAll$Header$TeamNameGrp$txt_LastName"
						+ "*GEOM*TEXT SET " + player.getTicker_name() + "\0",print_writers);
				
				switch (config.getBroadcaster().toUpperCase()) {
				case Constants.BAN_AFG_SERIES:
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Mini$Side1$Select$Bating$AllDataGrp$CardAll$Header$img_Logo*TEXTURE*IMAGE SET "
							+ Constants.BAN_AFG_SERIES_LOGO + team.getTeamBadge() + "\0",print_writers);
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Mini$Side" + WhichSide + "$Select$Bating$AllDataGrp$CardAll$Header$TeamNameGrp$txt_FirstName"
							+ "*GEOM*TEXT SET " + "" + "\0",print_writers);
					break;
				case Constants.ACC:
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Mini$Side1$Select$Bating$AllDataGrp$CardAll$Header$img_Logo*TEXTURE*IMAGE SET "
							+ Constants.ACC_FLAG + team.getTeamBadge() + "\0",print_writers);
					break;
				}
				
				for(BatBallGriff gf :CricketFunctions.getBatBallGriff(player, CricketUtil.BATSMAN, FirstPlayerId, team, headToHead, matchAllData)){
					
					row_id++;
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Mini$Side" + WhichSide + "$Select$Bating$AllDataGrp$CardAll$Data$DataGrp"
								+ "*FUNCTION*Grid*num_row SET " + row_id + " \0",print_writers);
					
					if(gf.getMatchNumber().equalsIgnoreCase(matchAllData.getMatch().getMatchFileName().replace(".json", ""))) {
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Mini$Side" + WhichSide + "$Select$Bating$AllDataGrp$CardAll$Data$DataGrp$Row"
									+ row_id + "$RowAnimation$Select*FUNCTION*Omo*vis_con SET 1\0",print_writers);
						con_name = "$Highlight";
					}else {
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Mini$Side" + WhichSide + "$Select$Bating$AllDataGrp$CardAll$Data$DataGrp$Row"
									+ row_id + "$RowAnimation$Select*FUNCTION*Omo*vis_con SET 0\0",print_writers);
						con_name = "$Dehighlight";
					}
					
					switch (config.getBroadcaster().toUpperCase()) {
					case Constants.BAN_AFG_SERIES:
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Mini$Side" + WhichSide + "$Select$Bating$AllDataGrp$CardAll$Data$DataGrp$Row" + row_id + 
								"$RowAnimation$Select" + con_name + "$img_Base2*TEXTURE*IMAGE SET " + Constants.BAN_AFG_SERIES_BASE2 + team.getTeamBadge() + "\0",print_writers);
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Mini$Side" + WhichSide + "$Select$Bating$AllDataGrp$CardAll$Data$DataGrp$Row" + row_id + 
								"$RowAnimation$Select" + con_name + "$img_Text2*TEXTURE*IMAGE SET "+ Constants.BAN_AFG_SERIES_TEXT2 + team.getTeamBadge() + "\0",print_writers);
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Mini$Side" + WhichSide + "$Select$Bating$AllDataGrp$CardAll$Data$DataGrp$Row" + row_id + 
								"$RowAnimation$Select" + con_name + "$txt_PlayerName*GEOM*TEXT SET " + getOrdinalMatch(gf.getMatchNumber()).toUpperCase() + " T20I" + "\0",print_writers);
						
						break;
					default:
						if(gf.getMatchIdent().contains("SEMI-FINAL 1")) {
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Mini$Side" + WhichSide + "$Select$Bating$AllDataGrp$CardAll$Data$DataGrp$Row" + row_id + 
									"$RowAnimation$Select" + con_name + "$txt_PlayerName*GEOM*TEXT SET v " + gf.getOpponentTeam().getTeamName4() + ", SF 1" + "\0",print_writers);
						}else if(gf.getMatchIdent().contains("SEMI-FINAL 2")) {
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Mini$Side" + WhichSide + "$Select$Bating$AllDataGrp$CardAll$Data$DataGrp$Row" + row_id + 
									"$RowAnimation$Select" + con_name + "$txt_PlayerName*GEOM*TEXT SET v " + gf.getOpponentTeam().getTeamName4() + ", SF 2" + "\0",print_writers);
						}else if(gf.getMatchIdent().contains("FINAL")) {
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Mini$Side" + WhichSide + "$Select$Bating$AllDataGrp$CardAll$Data$DataGrp$Row" + row_id + 
									"$RowAnimation$Select" + con_name + "$txt_PlayerName*GEOM*TEXT SET v " + gf.getOpponentTeam().getTeamName4() + ", FINAL" + "\0",print_writers);
						}else {
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Mini$Side" + WhichSide + "$Select$Bating$AllDataGrp$CardAll$Data$DataGrp$Row" + row_id + 
									"$RowAnimation$Select" + con_name + "$txt_PlayerName*GEOM*TEXT SET v " + gf.getOpponentTeam().getTeamName4() + "\0",print_writers);
						}
						break;
					}
					
					
					if(gf.getStatus().equalsIgnoreCase(CricketUtil.STILL_TO_BAT)) {
						if(gf.getBallsFaced() != 0) {
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Mini$Side" + WhichSide + "$Select$Bating$AllDataGrp$CardAll$Data$DataGrp$Row"
										+ row_id + "$RowAnimation$Select" + con_name + "$txt_Data1*GEOM*TEXT SET " + gf.getRuns() + "\0",print_writers);
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Mini$Side" + WhichSide + "$Select$Bating$AllDataGrp$CardAll$Data$DataGrp$Row"
										+ row_id + "$RowAnimation$Select" + con_name + "$txt_Data2*GEOM*TEXT SET " + gf.getBallsFaced() + "\0",print_writers);
						}else {
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Mini$Side" + WhichSide + "$Select$Bating$AllDataGrp$CardAll$Data$DataGrp$Row"
										+ row_id + "$RowAnimation$Select" + con_name + "$txt_Data1*GEOM*TEXT SET \0",print_writers);
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Mini$Side" + WhichSide + "$Select$Bating$AllDataGrp$CardAll$Data$DataGrp$Row"
										+ row_id + "$RowAnimation$Select" + con_name + "$txt_Data2*GEOM*TEXT SET DNB\0",print_writers);
						}
					}else if(gf.getStatus().equalsIgnoreCase(CricketUtil.NOT_OUT)) {
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Mini$Side" + WhichSide + "$Select$Bating$AllDataGrp$CardAll$Data$DataGrp$Row"
									+ row_id + "$RowAnimation$Select" + con_name + "$txt_Data1*GEOM*TEXT SET " + gf.getRuns() + "*" + "\0",print_writers);
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Mini$Side" + WhichSide + "$Select$Bating$AllDataGrp$CardAll$Data$DataGrp$Row"
									+ row_id + "$RowAnimation$Select" + con_name + "$txt_Data2*GEOM*TEXT SET " + gf.getBallsFaced() + "\0",print_writers);
					}else if(gf.getStatus().equalsIgnoreCase(CricketUtil.OUT)) {
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Mini$Side" + WhichSide + "$Select$Bating$AllDataGrp$CardAll$Data$DataGrp$Row"
									+ row_id + "$RowAnimation$Select" + con_name + "$txt_Data1*GEOM*TEXT SET " + gf.getRuns() + "\0",print_writers);
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Mini$Side" + WhichSide + "$Select$Bating$AllDataGrp$CardAll$Data$DataGrp$Row"
									+ row_id + "$RowAnimation$Select" + con_name + "$txt_Data2*GEOM*TEXT SET " + gf.getBallsFaced() + "\0",print_writers);
					}else {
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Mini$Side" + WhichSide + "$Select$Bating$AllDataGrp$CardAll$Data$DataGrp$Row"
									+ row_id + "$RowAnimation$Select" + con_name + "$txt_Data1*GEOM*TEXT SET \0",print_writers);
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Mini$Side" + WhichSide + "$Select$Bating$AllDataGrp$CardAll$Data$DataGrp$Row"
									+ row_id + "$RowAnimation$Select" + con_name + "$txt_Data2*GEOM*TEXT SET " + gf.getStatus() + "\0",print_writers);
					}
				}
				break;
			case "Alt_F2":
				int row_no = 0, count = 0;
				String MatchName = "",ident="";
				rowId = 0;
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Mini$Side" + WhichSide + "$Select*FUNCTION*Omo*vis_con SET 1 \0",print_writers);

				switch (config.getBroadcaster().toUpperCase()) {
				case Constants.BAN_AFG_SERIES:
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Mini$Side1$Select$Bowling$AllDataGrp$CardAll$Header$img_Logo*TEXTURE*IMAGE SET "
							+ Constants.BAN_AFG_SERIES_LOGO + team.getTeamBadge() + "\0",print_writers);
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Mini$Side" + WhichSide + "$Select$Bowling$AllDataGrp$CardAll$Header$TeamNameGrp$txt_FirstName"
							+ "*GEOM*TEXT SET " + "" + "\0",print_writers);
					break;
				case Constants.ACC:
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Mini$Side1$Select$Bowling$AllDataGrp$CardAll$Header$img_Logo*TEXTURE*IMAGE SET "
							+ Constants.ACC_FLAG + team.getTeamBadge() + "\0",print_writers);
					break;
				}
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Mini$Side" + WhichSide + "$Select$Bowling$AllDataGrp$CardAll$Header$TeamNameGrp$txt_LastName"
						+ "*GEOM*TEXT SET " + player.getTicker_name() + "\0",print_writers);
				
				for(HeadToHeadPlayer h2h : headToHead) {
					
					if(new File(CricketUtil.CRICKET_DIRECTORY + CricketUtil.SETUP_DIRECTORY + h2h.getMatchFileName()).exists() == true) {
						ident = new ObjectMapper().readValue(new InputStreamReader(new FileInputStream(new File(CricketUtil.CRICKET_DIRECTORY 
								+ CricketUtil.SETUP_DIRECTORY + h2h.getMatchFileName())), StandardCharsets.UTF_8), Setup.class).getMatchIdent();
					}
					
					if(h2h.getTeam().getTeamName4().equalsIgnoreCase(team.getTeamName4())){
						System.out.println("PLAYER ID - " + player.getPlayerId() + " MATCH - " + h2h.getMatchFileName() + " count - " + count);
					}
					
					if(h2h.getPlayerId() == player.getPlayerId() && h2h.getTeam().getTeamName4().equalsIgnoreCase(team.getTeamName4())) {
						row_no++;
						MatchName = h2h.getMatchFileName();
						switch (config.getBroadcaster().toUpperCase()) {
						case Constants.BAN_AFG_SERIES:
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Mini$Side" + WhichSide + "$Select$Bowling$AllDataGrp$CardAll$Data$DataGrp$Row"
										+ row_no + "$RowAnimation$Select$Dehighlight$img_Base2*TEXTURE*IMAGE SET " + Constants.BAN_AFG_SERIES_BASE2 + team.getTeamBadge() + "\0",print_writers);
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Mini$Side" + WhichSide + "$Select$Bowling$AllDataGrp$CardAll$Data$DataGrp$Row"
										+ row_no + "$RowAnimation$Select$Dehighlight$img_Text2*TEXTURE*IMAGE SET " + Constants.BAN_AFG_SERIES_TEXT2 + team.getTeamBadge() + "\0",print_writers);
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Mini$Side" + WhichSide + "$Select$Bowling$AllDataGrp$CardAll$Data$DataGrp$Row" + row_no + 
									"$RowAnimation$Select$Dehighlight$txt_PlayerName*GEOM*TEXT SET " + getOrdinalMatch(h2h.getMatchFileName().replace(".json", "")).toUpperCase() + " T20I" + "\0",print_writers);
							
							break;
						default:
							
							if(ident.contains("SEMI-FINAL 1")) {
								CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Mini$Side" + WhichSide + "$Select$Bowling$AllDataGrp$CardAll$Data$DataGrp$Row" + row_no + 
										"$RowAnimation$Select$Dehighlight$txt_PlayerName*GEOM*TEXT SET v " + h2h.getOpponentTeam().getTeamName4() + ", SF 1" + "\0",print_writers);
							}else if(ident.contains("SEMI-FINAL 2")) {
								CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Mini$Side" + WhichSide + "$Select$Bowling$AllDataGrp$CardAll$Data$DataGrp$Row" + row_no + 
										"$RowAnimation$Select$Dehighlight$txt_PlayerName*GEOM*TEXT SET v " + h2h.getOpponentTeam().getTeamName4() + ", SF 2" + "\0",print_writers);
							}else if(ident.contains("FINAL")) {
								CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Mini$Side" + WhichSide + "$Select$Bowling$AllDataGrp$CardAll$Data$DataGrp$Row" + row_no + 
										"$RowAnimation$Select$Dehighlight$txt_PlayerName*GEOM*TEXT SET v " + h2h.getOpponentTeam().getTeamName4() + ", FINAL" + "\0",print_writers);
							}else {
								CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Mini$Side" + WhichSide + "$Select$Bowling$AllDataGrp$CardAll$Data$DataGrp$Row" + row_no + 
										"$RowAnimation$Select$Dehighlight$txt_PlayerName*GEOM*TEXT SET v " + h2h.getOpponentTeam().getTeamName4() + "\0",print_writers);
							}
							break;
						}
						
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Mini$Side" + WhichSide + "$Select$Bowling$AllDataGrp$CardAll$Data$DataGrp$Row"
									+ row_no + "$RowAnimation$Select*FUNCTION*Omo*vis_con SET 0\0",print_writers);

						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Mini$Side" + WhichSide + "$Select$Bowling$AllDataGrp$CardAll$Data$DataGrp"
								+ "*FUNCTION*Grid*num_row SET " + row_no + "\0",print_writers);
						
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Mini$Side" + WhichSide + "$Select$Bowling$AllDataGrp$CardAll$Data$Row"
								+ row_no + "*ACTIVE SET 1 \0",print_writers);
						
						if(h2h.getBallsBowled() == 0) {
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Mini$Side" + WhichSide + "$Select$Bowling$AllDataGrp$CardAll$Data$DataGrp$Row"
									+ row_no + "$RowAnimation$Select$Dehighlight$txt_Data1*GEOM*TEXT SET \0",print_writers);
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Mini$Side" + WhichSide + "$Select$Bowling$AllDataGrp$CardAll$Data$DataGrp$Row"
									+ row_no + "$RowAnimation$Select$Dehighlight$txt_Data2*GEOM*TEXT SET DNB\0",print_writers);
							
						}else {
							
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Mini$Side" + WhichSide + "$Select$Bowling$AllDataGrp$CardAll$Data$DataGrp$Row"
									+ row_no + "$RowAnimation$Select$Dehighlight$txt_Data1*GEOM*TEXT SET " + h2h.getWickets() +"-"+h2h.getRunsConceded() + "\0",print_writers);

							if(h2h.getBallsBowled()%6 == 0) {
								CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Mini$Side" + WhichSide + "$Select$Bowling$AllDataGrp$CardAll$Data$DataGrp$Row"
										+ row_no + "$RowAnimation$Select$Dehighlight$txt_Data2*GEOM*TEXT SET " + (h2h.getBallsBowled()/6) + "\0",print_writers);
							}else {
								CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Mini$Side" + WhichSide + "$Select$Bowling$AllDataGrp$CardAll$Data$DataGrp$Row"
										+ row_no + "$RowAnimation$Select$Dehighlight$txt_Data2*GEOM*TEXT SET " + (h2h.getBallsBowled()/6)+"."+h2h.getBallsBowled()%6 + "\0",print_writers);
							}
						}						
						count = 0;
					}else if(h2h.getTeam().getTeamName4().equalsIgnoreCase(team.getTeamName4())) {
						if(count == 10) { //For Impact Player use 11 Otherwise use 10
							row_no++;
							switch (config.getBroadcaster().toUpperCase()) {
							case Constants.BAN_AFG_SERIES:
								CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Mini$Side" + WhichSide + "$Select$Bowling$AllDataGrp$CardAll$Data$DataGrp$Row"
											+ row_no + "$RowAnimation$Select$Dehighlight$img_Base2*TEXTURE*IMAGE SET " + Constants.BAN_AFG_SERIES_BASE2 + team.getTeamBadge() + "\0",print_writers);
								CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Mini$Side" + WhichSide + "$Select$Bowling$AllDataGrp$CardAll$Data$DataGrp$Row"
											+ row_no + "$RowAnimation$Select$Dehighlight$img_Text2*TEXTURE*IMAGE SET " + Constants.BAN_AFG_SERIES_TEXT2 + team.getTeamBadge() + "\0",print_writers);
								CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Mini$Side" + WhichSide + "$Select$Bowling$AllDataGrp$CardAll$Data$DataGrp$Row" + row_no + 
										"$RowAnimation$Select$Dehighlight$txt_PlayerName*GEOM*TEXT SET " + getOrdinalMatch(h2h.getMatchFileName().replace(".json", "")).toUpperCase() + " T20I" + "\0",print_writers);
								
								break;
							default:
								
								if(ident.contains("SEMI-FINAL 1")) {
									CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Mini$Side" + WhichSide + "$Select$Bowling$AllDataGrp$CardAll$Data$DataGrp$Row" + row_no + 
											"$RowAnimation$Select$Dehighlight$txt_PlayerName*GEOM*TEXT SET v " + h2h.getOpponentTeam().getTeamName4() + ", SF 1" + "\0",print_writers);
								}else if(ident.contains("SEMI-FINAL 2")) {
									CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Mini$Side" + WhichSide + "$Select$Bowling$AllDataGrp$CardAll$Data$DataGrp$Row" + row_no + 
											"$RowAnimation$Select$Dehighlight$txt_PlayerName*GEOM*TEXT SET v " + h2h.getOpponentTeam().getTeamName4() + ", SF 2" + "\0",print_writers);
								}else if(ident.contains("FINAL")) {
									CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Mini$Side" + WhichSide + "$Select$Bowling$AllDataGrp$CardAll$Data$DataGrp$Row" + row_no + 
											"$RowAnimation$Select$Dehighlight$txt_PlayerName*GEOM*TEXT SET v " + h2h.getOpponentTeam().getTeamName4() + ", FINAL" + "\0",print_writers);
								}else {
									CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Mini$Side" + WhichSide + "$Select$Bowling$AllDataGrp$CardAll$Data$DataGrp$Row" + row_no + 
											"$RowAnimation$Select$Dehighlight$txt_PlayerName*GEOM*TEXT SET v " + h2h.getOpponentTeam().getTeamName4() + "\0",print_writers);
								}
								break;
							}
							
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Mini$Side" + WhichSide + "$Select$Bowling$AllDataGrp$CardAll$Data$DataGrp$Row"
									+ row_no + "$RowAnimation$Select*FUNCTION*Omo*vis_con SET 0\0",print_writers);

							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Mini$Side" + WhichSide + "$Select$Bowling$AllDataGrp$CardAll$Data$DataGrp"
											+ "*FUNCTION*Grid*num_row SET " + row_no + "\0",print_writers);
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Mini$Side" + WhichSide + "$Select$Bowling$AllDataGrp$CardAll$Data$Row"
									+ row_no + "*ACTIVE SET 1 \0",print_writers);
							
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Mini$Side" + WhichSide + "$Select$Bowling$AllDataGrp$CardAll$Data$DataGrp$Row" + row_no 
									+ "$RowAnimation$Select$Dehighlight$txt_Data1*GEOM*TEXT SET \0",print_writers);

							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Mini$Side" + WhichSide + "$Select$Bowling$AllDataGrp$CardAll$Data$DataGrp$Row"
									+ row_no + "$RowAnimation$Select$Dehighlight$txt_Data2*GEOM*TEXT SET DNP\0",print_writers);
							
							count = 0;
						}else if(!MatchName.equalsIgnoreCase(h2h.getMatchFileName()) && count < 11) {
							MatchName = h2h.getMatchFileName();
							count = 1;
						}else {
							if(count==10) {
								count=0;
							}
							count++;
						}
					}
				}
				
				boolean playerIsInBoc = false;
				if(inning.getBowlingCard() != null) {
					for(BowlingCard boc : inning.getBowlingCard()) {
						if(boc.getPlayerId() == player.getPlayerId()) {
							playerIsInBoc = true;
							row_no++;
							
							switch (config.getBroadcaster().toUpperCase()) {
							case Constants.BAN_AFG_SERIES:
								CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Mini$Side" + WhichSide + "$Select$Bowling$AllDataGrp$CardAll$Data$DataGrp$Row"
											+ row_no + "$RowAnimation$Select$Dehighlight$img_Base2*TEXTURE*IMAGE SET " + Constants.BAN_AFG_SERIES_BASE2 + team.getTeamBadge() + "\0",print_writers);
								CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Mini$Side" + WhichSide + "$Select$Bowling$AllDataGrp$CardAll$Data$DataGrp$Row"
											+ row_no + "$RowAnimation$Select$Dehighlight$img_Text2*TEXTURE*IMAGE SET " + Constants.BAN_AFG_SERIES_TEXT2 + team.getTeamBadge() + "\0",print_writers);
								CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Mini$Side" + WhichSide + "$Select$Bowling$AllDataGrp$CardAll$Data$DataGrp$Row" + row_no 
										+ "$RowAnimation$Select$Highlight$txt_PlayerName*GEOM*TEXT SET " + getOrdinalMatch(matchAllData.getMatch().getMatchFileName().replace(".json", "")).toUpperCase() 
										+ " T20I" + "\0",print_writers);
								break;
							default:
								if(matchAllData.getSetup().getMatchIdent().contains("SEMI-FINAL 1")) {
									CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Mini$Side" + WhichSide + "$Select$Bowling$AllDataGrp$CardAll$Data$DataGrp$Row" + row_no 
											+ "$RowAnimation$Select$Highlight$txt_PlayerName*GEOM*TEXT SET v " + inning.getBatting_team().getTeamName4() + ", SF 1" + "\0",print_writers);
								}else if(matchAllData.getSetup().getMatchIdent().contains("SEMI-FINAL 2")) {
									CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Mini$Side" + WhichSide + "$Select$Bowling$AllDataGrp$CardAll$Data$DataGrp$Row" + row_no 
											+ "$RowAnimation$Select$Highlight$txt_PlayerName*GEOM*TEXT SET v " + inning.getBatting_team().getTeamName4() + ", SF 2" + "\0",print_writers);
								}else if(matchAllData.getSetup().getMatchIdent().contains("FINAL")) {
									CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Mini$Side" + WhichSide + "$Select$Bowling$AllDataGrp$CardAll$Data$DataGrp$Row" + row_no 
											+ "$RowAnimation$Select$Highlight$txt_PlayerName*GEOM*TEXT SET v " + inning.getBatting_team().getTeamName4() + ", FINAL" + "\0",print_writers);
								}else {
									CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Mini$Side" + WhichSide + "$Select$Bowling$AllDataGrp$CardAll$Data$DataGrp$Row" + row_no 
											+ "$RowAnimation$Select$Highlight$txt_PlayerName*GEOM*TEXT SET v " + inning.getBatting_team().getTeamName4() + "\0",print_writers);
								}
								break;
							}
							
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Mini$Side" + WhichSide + "$Select$Bowling$AllDataGrp$CardAll$Data$DataGrp$Row"
									+ row_no + "$RowAnimation$Select*FUNCTION*Omo*vis_con SET 1\0",print_writers);

							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Mini$Side" + WhichSide + "$Select$Bowling$AllDataGrp$CardAll$Data$DataGrp"
									+ "*FUNCTION*Grid*num_row SET " + row_no + "\0",print_writers);
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Mini$Side" + WhichSide + "$Select$Bowling$AllDataGrp$CardAll$Data$Row"
									+ row_no + "*ACTIVE SET 1 \0",print_writers);
							
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Mini$Side" + WhichSide + "$Select$Bowling$AllDataGrp$CardAll$Data$DataGrp$Row"
									+ row_no + "$RowAnimation$Select$Highlight$txt_Data1*GEOM*TEXT SET " + boc.getWickets()+"-"+ boc.getRuns() + "\0",print_writers);

							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Mini$Side" + WhichSide + "$Select$Bowling$AllDataGrp$CardAll$Data$DataGrp$Row"
									+ row_no + "$RowAnimation$Select$Highlight$txt_Data2*GEOM*TEXT SET " + CricketFunctions.OverBalls(boc.getOvers(), boc.getBalls()) + "\0",print_writers);
							break;
						}else {
							playerIsInBoc = false;
						}
					}
				}
				
				if(!playerIsInBoc) {
					row_no++;
					
					switch (config.getBroadcaster().toUpperCase()) {
					case Constants.BAN_AFG_SERIES:
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Mini$Side" + WhichSide + "$Select$Bowling$AllDataGrp$CardAll$Data$DataGrp$Row"
									+ row_no + "$RowAnimation$Select$Dehighlight$img_Base2*TEXTURE*IMAGE SET " + Constants.BAN_AFG_SERIES_BASE2 + team.getTeamBadge() + "\0",print_writers);
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Mini$Side" + WhichSide + "$Select$Bowling$AllDataGrp$CardAll$Data$DataGrp$Row"
									+ row_no + "$RowAnimation$Select$Dehighlight$img_Text2*TEXTURE*IMAGE SET " + Constants.BAN_AFG_SERIES_TEXT2 + team.getTeamBadge() + "\0",print_writers);
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Mini$Side" + WhichSide + "$Select$Bowling$AllDataGrp$CardAll$Data$DataGrp$Row" + row_no + 
								"$RowAnimation$Select$Highlight$txt_PlayerName*GEOM*TEXT SET " + getOrdinalMatch(matchAllData.getMatch().getMatchFileName().replace(".json", "")).toUpperCase() + 
								" T20I" + "\0",print_writers);
						break;
					default:
						if(matchAllData.getSetup().getMatchIdent().contains("SEMI-FINAL 1")) {
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Mini$Side" + WhichSide + "$Select$Bowling$AllDataGrp$CardAll$Data$DataGrp$Row" + row_no + 
									"$RowAnimation$Select$Highlight$txt_PlayerName*GEOM*TEXT SET v " + inning.getBatting_team().getTeamName4() + ", SF 1" + "\0",print_writers);
						}else if(matchAllData.getSetup().getMatchIdent().contains("SEMI-FINAL 2")) {
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Mini$Side" + WhichSide + "$Select$Bowling$AllDataGrp$CardAll$Data$DataGrp$Row" + row_no + 
									"$RowAnimation$Select$Highlight$txt_PlayerName*GEOM*TEXT SET v " + inning.getBatting_team().getTeamName4() + ", SF 2" + "\0",print_writers);
						}else if(matchAllData.getSetup().getMatchIdent().contains("FINAL")) {
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Mini$Side" + WhichSide + "$Select$Bowling$AllDataGrp$CardAll$Data$DataGrp$Row" + row_no + 
									"$RowAnimation$Select$Highlight$txt_PlayerName*GEOM*TEXT SET v " + inning.getBatting_team().getTeamName4() + ", FINAL" + "\0",print_writers);
						}else {
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Mini$Side" + WhichSide + "$Select$Bowling$AllDataGrp$CardAll$Data$DataGrp$Row" + row_no + 
									"$RowAnimation$Select$Highlight$txt_PlayerName*GEOM*TEXT SET v " + inning.getBatting_team().getTeamName4() + "\0",print_writers);
						}
						
						break;
					}
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Mini$Side" + WhichSide + "$Select$Bowling$AllDataGrp$CardAll$Data$DataGrp$Row"
							+ row_no + "$RowAnimation$Select*FUNCTION*Omo*vis_con SET 1\0",print_writers);

					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Mini$Side" + WhichSide + "$Select$Bowling$AllDataGrp$CardAll$Data$DataGrp"
							+ "*FUNCTION*Grid*num_row SET " + row_no + "\0",print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Mini$Side" + WhichSide + "$Select$Bowling$AllDataGrp$CardAll$Data$Row"
							+ row_no + "*ACTIVE SET 1 \0",print_writers);
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Mini$Side" + WhichSide + "$Select$Bowling$AllDataGrp$CardAll$Data$DataGrp$Row"
							+ row_no + "$RowAnimation$Select$Highlight$txt_Data1*GEOM*TEXT SET \0",print_writers);

					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Mini$Side" + WhichSide + "$Select$Bowling$AllDataGrp$CardAll$Data$DataGrp$Row"
							+ row_no + "$RowAnimation$Select$Highlight$txt_Data2*GEOM*TEXT SET DNP\0",print_writers);
					
					List<Player> plyrs = (matchAllData.getSetup().getHomeTeamId() == team.getTeamId() ? matchAllData.getSetup().getHomeSquad() : 
						matchAllData.getSetup().getAwaySquad());
					for(Player plyr : plyrs) {
						if(plyr.getPlayerId() == player.getPlayerId()) {
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Mini$Side" + WhichSide + "$Select$Bowling$AllDataGrp$CardAll$Data$DataGrp$Row"
									+ row_no + "$RowAnimation$Select$Highlight$txt_Data2*GEOM*TEXT SET DNB\0",print_writers);
							break;
						}
					}
				}
				break;	
				
			case "Shift_F2":
				int rows_id = 0, omo_numr = 0, i = 0;
				String cont_names = "";
				
//				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Main$Select*FUNCTION*Omo*vis_con SET 0 \0",print_writers);
				CricketFunctions.DoadWriteCommandToAllViz(
						"-1 RENDERER*FRONT_LAYER*TREE*$Mini$Side" + WhichSide + "$Select*FUNCTION*Omo*vis_con SET 1 \0",print_writers);
				
				switch (config.getBroadcaster().toUpperCase()) {
				case Constants.BAN_AFG_SERIES:
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Mini$Side1$Select$Bowling$AllDataGrp$CardAll$Header$img_Logo*TEXTURE*IMAGE SET "
							+ Constants.BAN_AFG_SERIES_LOGO + inning.getBowling_team().getTeamBadge() + "\0",print_writers);
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Mini$Side" + WhichSide + "$Select$Bowling$AllDataGrp$CardAll$Header$TeamNameGrp$txt_FirstName"
							+ "*GEOM*TEXT SET " + "" + "\0",print_writers);
					break;
				case Constants.ACC:
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Mini$Side1$Select$Bowling$AllDataGrp$CardAll$Header$img_Logo*TEXTURE*IMAGE SET "
							+ Constants.ACC_FLAG + inning.getBowling_team().getTeamBadge() + "\0",print_writers);
					break;
				}

				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Mini$Side" + WhichSide + "$Select$Bowling$AllDataGrp$CardAll$Header$TeamNameGrp$txt_LastName"
						+ "*GEOM*TEXT SET " + inning.getBowling_team().getTeamName1() + "\0",print_writers);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Mini$Side" + WhichSide + "$Select$Bowling$AllDataGrp$CardAll$Header$TeamNameGrp$txt_LastName"
						+ "*GEOM*TEXT SET " + inning.getBowling_team().getTeamName1() + "\0",print_writers);

				for (BowlingCard boc : inning.getBowlingCard()) {
					if (boc.getRuns() > 0 || ((boc.getOvers() * 6) + boc.getBalls()) > 0) {
						i = i + 1;
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Mini$Side" + WhichSide + "$Select$Bowling$AllDataGrp$CardAll$Data$DataGrp"
								+ "*FUNCTION*Grid*num_row SET " + i + "\0",print_writers);
					}
					switch (boc.getStatus().toUpperCase()) {
					case (CricketUtil.OTHER + CricketUtil.BOWLER):
						omo_numr = 0;
						cont_names = "$Dehighlight";
						break;
					case (CricketUtil.LAST + CricketUtil.BOWLER):
						omo_numr = 0;
						cont_names = "$Dehighlight";
						break;
					case (CricketUtil.CURRENT + CricketUtil.BOWLER):
						omo_numr = 1;
						cont_names = "$Highlight";
						break;
					}

					rows_id = rows_id + 1;

					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Mini$Side" + WhichSide + "$Select$Bowling$AllDataGrp$CardAll$Data$Row"
							+ rows_id + "*ACTIVE SET 1 \0",print_writers);
					
					switch (config.getBroadcaster().toUpperCase()) {
					case Constants.BAN_AFG_SERIES:
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Mini$Side" + WhichSide + "$Select$Bowling$AllDataGrp$CardAll$Data$DataGrp$Row" + rows_id + 
								"$RowAnimation$Select" + cont_names + "$img_Base2*TEXTURE*IMAGE SET " + Constants.BAN_AFG_SERIES_BASE2 + inning.getBowling_team().getTeamBadge() + "\0",print_writers);
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Mini$Side" + WhichSide + "$Select$Bowling$AllDataGrp$CardAll$Data$DataGrp$Row" + rows_id + 
								"$RowAnimation$Select" + cont_names + "$img_Text2*TEXTURE*IMAGE SET " + Constants.BAN_AFG_SERIES_TEXT2 + inning.getBowling_team().getTeamBadge() + "\0",print_writers);
						break;
					}

					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Mini$Side" + WhichSide + "$Select$Bowling$AllDataGrp$CardAll$Data$DataGrp$Row"
							+ rows_id + "$RowAnimation$Select*FUNCTION*Omo*vis_con SET " + omo_numr + " \0",print_writers);

					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Mini$Side" + WhichSide + "$Select$Bowling$AllDataGrp$CardAll$Data$DataGrp$Row"
							+ rows_id + "$RowAnimation$Select" + cont_names + "$txt_PlayerName*GEOM*TEXT SET " + boc.getPlayer().getTicker_name() + "\0",print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Mini$Side" + WhichSide + "$Select$Bowling$AllDataGrp$CardAll$Data$DataGrp$Row"
							+ rows_id + "$RowAnimation$Select" + cont_names + "$txt_Data1*GEOM*TEXT SET " + boc.getWickets() + "-" + boc.getRuns() + "\0",print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Mini$Side" + WhichSide + "$Select$Bowling$AllDataGrp$CardAll$Data$DataGrp$Row"
							+ rows_id + "$RowAnimation$Select" + cont_names + "$txt_Data2*GEOM*TEXT SET " + CricketFunctions.OverBalls(boc.getOvers(), boc.getBalls()) + "\0",print_writers);

				}
				
				break;	
			}
			break;
		case Constants.TRI_SERIES:  case Constants.MT20: case Constants.TG20:
			String logoPath, base1Path, base2Path,text1Path,text2Path;
			if (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES)) {
			    logoPath = Constants.TRI_SERIES_LOGO;
			    base1Path = Constants.TRI_SERIES_BASE1;
			    base2Path = Constants.TRI_SERIES_BASE2;
			    text1Path = Constants.TRI_SERIES_TEXT1;
			    text2Path = Constants.TRI_SERIES_TEXT2;
			} else if (config.getBroadcaster().equalsIgnoreCase(Constants.TG20)) {
			    logoPath = Constants.TG20_LOGO;
			    base1Path = Constants.TG20_BASE1;
			    base2Path = Constants.TG20_BASE2;
			    text1Path = Constants.TG20_TEXT1;
			    text2Path = Constants.TG20_TEXT2;
			} else {
			    logoPath = Constants.MT20_LOGO;
			    base1Path = Constants.MT20_BASE1;
			    base2Path = Constants.MT20_BASE2;
			    text1Path = Constants.MT20_TEXT1;
			    text2Path = Constants.MT20_TEXT2;
			}
			switch(whatToProcess) {
			case "Shift_F1":
				int battingSize=0;
				cont_name = "";
				omo_num = 0;
				rowId = 0;
				
				switch(config.getBroadcaster()) {
				case Constants.TRI_SERIES:  case Constants.MT20:
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Minis$Side" + WhichSide + "$Batting$TeamColors$img_Base1*TEXTURE*IMAGE SET " 
							+ base1Path + inning.getBatting_team().getTeamBadge() + " \0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Minis$Side" + WhichSide + "$Batting$TeamColors$img_Base2*TEXTURE*IMAGE SET " 
							+ base2Path + inning.getBatting_team().getTeamBadge() + " \0", print_writers);
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Minis$Side" + WhichSide + "$Batting$AllDataGrp$TeamNameGrp$headerBnd$img_Base2*TEXTURE*IMAGE SET " 
							+ base2Path + inning.getBatting_team().getTeamBadge() + " \0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Minis$Side" + WhichSide + "$Batting$AllDataGrp$TeamNameGrp$HeaderText$img_Text2*TEXTURE*IMAGE SET " 
							+ text2Path + inning.getBatting_team().getTeamBadge() + " \0", print_writers);
					break;
				}
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Minis$Side" + WhichSide + "$Batting$AllDataGrp$TeamNameGrp$img_Flag$img_Base02*TEXTURE*IMAGE SET " 
						+ base2Path + inning.getBatting_team().getTeamBadge() + " \0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Minis$Side" + WhichSide + "$Batting$AllDataGrp$TeamNameGrp$img_Flag$img_Base01*TEXTURE*IMAGE SET " 
						+ base1Path + inning.getBatting_team().getTeamBadge() + " \0", print_writers);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Minis$Side" + WhichSide + "$Batting$img_Flag*TEXTURE*IMAGE SET " 
						+ logoPath + inning.getBatting_team().getTeamBadge() + " \0", print_writers);
				
				switch(config.getBroadcaster()) {
				case Constants.TG20:
					CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$Minis$Side" + WhichSide + "$Batting$AllDataGrp$Header$Name$"
							+ "select_Language*FUNCTION*Omo*vis_con SET ", config, Constants.TG20, print_writers, foreignLanguageOmo);
					foreignLanguageData = CricketFunctions.AssembleMultiLanguageData(CricketUtil.TEAM, CricketUtil.TEAMNAME_4, multilanguagedata, inning.getBatting_team().getTeamName1(), 
							"", null, 0, foreignLanguageDataList);
					CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$Minis$Side" + WhichSide + "$Batting$AllDataGrp$Header$Name$English$"
							+ "txt_FirstName*GEOM*TEXT SET ", config, Constants.TG20, print_writers, foreignLanguageData);
					break;
				default:
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Minis$Side" + WhichSide + "$Batting$txt_FirstName*GEOM*TEXT SET " + 
							(config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES)?inning.getBatting_team().getTeamName3():inning.getBatting_team().getTeamName3()) 
							+ " \0", print_writers);
					break;
				}
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Minis$Side" + WhichSide + "*FUNCTION*Omo*vis_con SET 1 \0", print_writers);
				for(int i=1; i<=inning.getBattingCard().size(); i++) {
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Minis$Side" + WhichSide + "$Batting$Row" + i + "*ACTIVE SET 1 \0", print_writers);	
				}
				
				Collections.sort(inning.getBattingCard());
				
				for (BattingCard bc : inning.getBattingCard()) {
					
					rowId = rowId + 1;
					
					switch (bc.getStatus().toUpperCase()) {
						case CricketUtil.OUT:
							omo_num = 1;
							cont_name = "$Out$Out";
							battingSize = battingSize + 1;
							break;
						case CricketUtil.NOT_OUT:
							omo_num = 2;
							cont_name = "$NotOut";
							battingSize = battingSize + 1;
							break;
						case CricketUtil.STILL_TO_BAT:
							if(bc.getHowOut() != null) {
								if(bc.getHowOut().equalsIgnoreCase(CricketUtil.RETIRED_HURT) || 
										bc.getHowOut().equalsIgnoreCase(CricketUtil.CONCUSSED)) {
									omo_num = 1;
									cont_name = "$Out$Out";
									battingSize = battingSize + 1;
								}
							}
							break;	
						}
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Minis$Side" + WhichSide 
							+ "$Batting$DataGrp*FUNCTION*Grid*num_row SET " + battingSize + " \0", print_writers);
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Minis$Side" + WhichSide + "$Batting$Row" + rowId 
							+ "$select_RowType*FUNCTION*Omo*vis_con SET " + String.valueOf(omo_num) + " \0", print_writers);
					
					switch(config.getBroadcaster()) {
					case Constants.TG20:
						CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$Minis$Side" + WhichSide + "$Batting$DataGrp$Row" + rowId + cont_name + 
								"$select_Language*FUNCTION*Omo*vis_con SET ", config, Constants.TG20, print_writers, foreignLanguageOmo);
						foreignLanguageData = CricketFunctions.AssembleMultiLanguageData(CricketUtil.PLAYER, CricketUtil.TICKERNAME, multilanguagedata, bc.getPlayer().getTicker_name(), 
								"", null, 0, foreignLanguageDataList);
						CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$Minis$Side" + WhichSide + "$Batting$DataGrp$Row" + rowId + cont_name + 
								"$English$txt_BatterName*GEOM*TEXT SET ", config, Constants.TG20, print_writers, foreignLanguageData);
						break;
					default:
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Minis$Side" + WhichSide + "$Batting$Row" + rowId + cont_name + 
								"$txt_BatterName*GEOM*TEXT SET " + bc.getPlayer().getTicker_name() + " \0", print_writers);
						break;
					}
					
					String Out_not = "";
					if(bc.getStatus().toUpperCase().equalsIgnoreCase(CricketUtil.NOT_OUT)) {
						
						Out_not = "*";
					}else if(bc.getStatus().toUpperCase().equalsIgnoreCase(CricketUtil.OUT) || 
							bc.getStatus().toUpperCase().equalsIgnoreCase(CricketUtil.STILL_TO_BAT)) {
						
						Out_not = "";
						if(bc.getHowOut() != null) {
							if(bc.getHowOut().equalsIgnoreCase(CricketUtil.RETIRED_HURT)) {
								Out_not = "*";
							}
						}
					}
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Minis$Side" + WhichSide + "$Batting$Row" + rowId + cont_name + 
							"$txt_Runs*GEOM*TEXT SET " + bc.getRuns() +Out_not+ " \0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Minis$Side" + WhichSide + "$Batting$Row" + rowId + cont_name + 
							"$txt_Balls*GEOM*TEXT SET " + bc.getBalls() + " \0", print_writers);
				}
				
				break;
				
				
			case "Alt_F1":
				int omo_num = 0;
				int row_id = 0, counter = 0;
				String MatchNam = "";
				boolean playerFound = false;
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Minis$Side" + WhichSide 
						+ "$Batting$img_Flag*TEXTURE*IMAGE SET " + logoPath  + team.getTeamBadge() + " \0", print_writers);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Minis$Side" + WhichSide + "$Batting$AllDataGrp$TeamNameGrp$img_Flag$img_Base02*TEXTURE*IMAGE SET " 
						+ base2Path + team.getTeamBadge() + " \0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Minis$Side" + WhichSide + "$Batting$AllDataGrp$TeamNameGrp$img_Flag$img_Base01*TEXTURE*IMAGE SET " 
						+ base1Path + team.getTeamBadge() + " \0", print_writers);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Minis$Side" + WhichSide + "$Batting$AllDataGrp$TeamNameGrp$headerBnd$img_Base2*TEXTURE*IMAGE SET " 
						+ base2Path + team.getTeamBadge() + " \0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Minis$Side" + WhichSide + "$Batting$AllDataGrp$TeamNameGrp$HeaderText$img_Text2*TEXTURE*IMAGE SET " 
						+ text2Path + team.getTeamBadge() + " \0", print_writers);
				
				CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$Minis$Side" + WhichSide + "$Batting$AllDataGrp$Header$Name$"
						+ "select_Language*FUNCTION*Omo*vis_con SET ", config, Constants.TG20, print_writers, foreignLanguageOmo);
				foreignLanguageData = CricketFunctions.AssembleMultiLanguageData(CricketUtil.PLAYER, CricketUtil.FULLNAME, multilanguagedata, player.getFull_name(), 
						"", null, 0, foreignLanguageDataList);
				CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$Minis$Side" + WhichSide + "$Batting$AllDataGrp$Header$Name$English$"
						+ "txt_FirstName*GEOM*TEXT SET ", config, Constants.TG20, print_writers, foreignLanguageData);
				
//				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Minis$Side" + WhichSide 
//						+ "$Batting$txt_FirstName*GEOM*TEXT SET " + player.getFull_name() + " \0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Minis$Side" + WhichSide 
						+ "*FUNCTION*Omo*vis_con SET 1 \0", print_writers);
				
				for(int i=1; i<=13; i++) {
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Minis$Side" + WhichSide 
							+ "$Batting$Row" + i + "*ACTIVE SET 0\0", print_writers);
				}
				
				for(HeadToHeadPlayer h2h : headToHead) {
					if(h2h.getPlayerId() == player.getPlayerId() && h2h.getTeam().getTeamName4().equalsIgnoreCase(team.getTeamName4())) {
						row_id++;
						
//						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Minis$Side" + WhichSide 
//								+ "$Batting$Row" + row_id + "$select_RowType$Players_Dehighlight$In_Out*FUNCTION*Omo*vis_con SET 0\0", print_writers);
						
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Minis$Side" + WhichSide 
								+ "$Batting$DataGrp*FUNCTION*Grid*num_row SET " + row_id + " \0", print_writers);
						
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$minis$Side" + WhichSide 
								+ "$Batting$Row" + row_id + "*ACTIVE SET 1\0", print_writers);
						
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Minis$Side" + WhichSide 
								+ "$Batting$Row" + row_id + "$select_RowType*FUNCTION*Omo*vis_con SET 1\0", print_writers);
						
						CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$Minis$Side" + WhichSide + "$Batting$DataGrp$Row" + row_id + 
								"$Out$Out$select_Language*FUNCTION*Omo*vis_con SET ", config, Constants.TG20, print_writers, foreignLanguageOmo);
						foreignLanguageDataList = CricketFunctions.AssembleMultiLanguageData(CricketUtil.DICTIONARY, "", multilanguagedata,"v",
							    "", null, 1,foreignLanguageDataList);
	    					foreignLanguageDataList = CricketFunctions.AssembleMultiLanguageData(CricketUtil.TEAM, CricketUtil.TEAMNAME_3, multilanguagedata,h2h.getOpponentTeam().getTeamName1().trim().toUpperCase(),
								    "", null, 2,foreignLanguageDataList);
	
						foreignLanguageData.add(CricketFunctions.MergeForeignLanguageDataListToSingleObject(foreignLanguageDataList));
						
						CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$Minis$Side" + WhichSide 
								+ "$Batting$Row" + row_id + "$Out$Out$English$txt_BatterName*GEOM*TEXT SET ",config, Constants.BCCI, print_writers, foreignLanguageData);
						
//						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Minis$Side" + WhichSide 
//								+ "$Batting$Row" + row_id + "$Out$txt_Name*GEOM*TEXT SET v " + h2h.getOpponentTeam().getTeamName3() + " \0", print_writers);
						
						MatchNam = h2h.getMatchFileName();

						if(h2h.getInningStarted().contains("Y")) {
							if(h2h.getDismissed().contains("N")) {
								
								CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Minis$Side" + WhichSide 
										+ "$Batting$Row" + row_id + "$Out$txt_Runs*GEOM*TEXT SET " + h2h.getRuns() + "*" + " \0", print_writers);
								
							}else if(h2h.getDismissed().contains("Y")) {
								CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Minis$Side" + WhichSide 
										+ "$Batting$Row" + row_id + "$Out$txt_Runs*GEOM*TEXT SET " + h2h.getRuns() + " \0", print_writers);
							}
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Minis$Side" + WhichSide 
									+ "$Batting$Row" + row_id + "$Out$txt_Balls*GEOM*TEXT SET " + h2h.getBallsFaced() + " \0", print_writers);
							
						}else {
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Minis$Side" + WhichSide 
									+ "$Batting$Row" + row_id + "$Out$txt_Balls*GEOM*TEXT SET \0", print_writers);
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Minis$Side" + WhichSide 
									+ "$Batting$Row" + row_id + "$Out$txt_Runs*GEOM*TEXT SET DNB\0", print_writers);
						}
						counter = 0;
					}else if(h2h.getTeam().getTeamName4().equalsIgnoreCase(team.getTeamName4())) {
						if(!MatchNam.equalsIgnoreCase(h2h.getMatchFileName()) && counter <= 11) {
							MatchNam = h2h.getMatchFileName();
							counter = 1;
						}else if(counter == 11) {
								row_id++;
								
//								CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Minis$Side" + WhichSide 
//										+ "$Batting$Row" + row_id + "$select_RowType$Players_Dehighlight$In_Out*FUNCTION*Omo*vis_con SET 0\0", print_writers);
								
								CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Minis$Side" + WhichSide 
										+ "$Batting$DataGrp*FUNCTION*Grid*num_row SET " + row_id + " \0", print_writers);
								
								CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Minis$Side" + WhichSide 
										+ "$Batting$Row" + row_id + "*ACTIVE SET 1\0", print_writers);
								
								CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Minis$Side" + WhichSide 
										+ "$Batting$Row" + row_id + "$select_RowType*FUNCTION*Omo*vis_con SET 1\0", print_writers);
								
								CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$Minis$Side" + WhichSide + "$Batting$DataGrp$Row" + row_id + 
										"$Out$Out$select_Language*FUNCTION*Omo*vis_con SET ", config, Constants.TG20, print_writers, foreignLanguageOmo);
								foreignLanguageDataList = CricketFunctions.AssembleMultiLanguageData(CricketUtil.DICTIONARY, "", multilanguagedata,"v",
									    "", null, 1,foreignLanguageDataList);
			    					foreignLanguageDataList = CricketFunctions.AssembleMultiLanguageData(CricketUtil.TEAM, CricketUtil.TEAMNAME_3, multilanguagedata,h2h.getOpponentTeam().getTeamName1().trim().toUpperCase(),
										    "", null, 2,foreignLanguageDataList);
			
								foreignLanguageData.add(CricketFunctions.MergeForeignLanguageDataListToSingleObject(foreignLanguageDataList));
								
								CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$Minis$Side" + WhichSide 
										+ "$Batting$Row" + row_id + "$Out$Out$English$txt_BatterName*GEOM*TEXT SET ",config, Constants.BCCI, print_writers, foreignLanguageData);
								
//								CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Minis$Side" + WhichSide 
//										+ "$Batting$Row" + row_id + "$Out$txt_Name*GEOM*TEXT SET v " + h2h.getOpponentTeam().getTeamName3() + " \0", print_writers);
								
								CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Minis$Side" + WhichSide 
										+ "$Batting$Row" + row_id + "$Out$txt_Balls*GEOM*TEXT SET \0", print_writers);
								CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Minis$Side" + WhichSide 
										+ "$Batting$Row" + row_id + "$Out$txt_Runs*GEOM*TEXT SET DNP\0", print_writers);
								
								counter = 0;
						}else {
							counter++;
						}
					}
				}
				for(BattingCard bc : inning.getBattingCard()) {
					if(bc.getPlayerId() == player.getPlayerId()) {
						row_id++;
						playerFound = true;
						if(bc.getStatus().equalsIgnoreCase(CricketUtil.STILL_TO_BAT)) {
							
//							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Minis$Side" + WhichSide 
//									+ "$Batting$Row" + row_id + "$select_RowType$Players_Highlight$In_Out*FUNCTION*Omo*vis_con SET 0\0", print_writers);
							
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Minis$Side" + WhichSide 
									+ "$Batting$DataGrp*FUNCTION*Grid*num_row SET " + row_id + " \0", print_writers);
							
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Minis$Side" + WhichSide 
									+ "$Batting$Row" + row_id + "*ACTIVE SET 1\0", print_writers);
							
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Minis$Side" + WhichSide 
									+ "$Batting$Row" + row_id + "$select_RowType*FUNCTION*Omo*vis_con SET 2\0", print_writers);
							
							CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$Minis$Side" + WhichSide + "$Batting$DataGrp$Row" + row_id + 
									"$NotOut$select_Language*FUNCTION*Omo*vis_con SET ", config, Constants.TG20, print_writers, foreignLanguageOmo);
							foreignLanguageDataList = CricketFunctions.AssembleMultiLanguageData(CricketUtil.DICTIONARY, "", multilanguagedata,"v",
								    "", null, 1,foreignLanguageDataList);
		    					foreignLanguageDataList = CricketFunctions.AssembleMultiLanguageData(CricketUtil.TEAM, CricketUtil.TEAMNAME_3, multilanguagedata,inning.getBowling_team().getTeamName1().trim().toUpperCase(),
									    "", null, 2,foreignLanguageDataList);
		
							foreignLanguageData.add(CricketFunctions.MergeForeignLanguageDataListToSingleObject(foreignLanguageDataList));
							
							CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$Minis$Side" + WhichSide 
									+ "$Batting$Row" + row_id + "$NotOut$English$txt_BatterName*GEOM*TEXT SET ",config, Constants.BCCI, print_writers, foreignLanguageData);
							
//							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Minis$Side" + WhichSide 
//									+ "$Batting$Row" + row_id + "$NotOut$txt_Name*GEOM*TEXT SET v " + inning.getBowling_team().getTeamName3() + " \0", print_writers);
							
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Minis$Side" + WhichSide 
									+ "$Batting$Row" + row_id + "$NotOut$txt_Balls*GEOM*TEXT SET \0", print_writers);
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Minis$Side" + WhichSide 
									+ "$Batting$Row" + row_id + "$NotOut$txt_Runs*GEOM*TEXT SET DNB\0", print_writers);
							
						}else {
							
//							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Minis$Side" + WhichSide 
//									+ "$Batting$Row" + row_id + "$select_RowType$Players_Highlight$In_Out*FUNCTION*Omo*vis_con SET 0\0", print_writers);
							
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Minis$Side" + WhichSide 
									+ "$Batting$DataGrp*FUNCTION*Grid*num_row SET " + row_id + " \0", print_writers);
							
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Minis$Side" + WhichSide 
									+ "$Batting$Row" + row_id + "*ACTIVE SET 1\0", print_writers);
							
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Minis$Side" + WhichSide 
									+ "$Batting$Row" + row_id + "$select_RowType*FUNCTION*Omo*vis_con SET 2\0", print_writers);
							
							CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$Minis$Side" + WhichSide + "$Batting$DataGrp$Row" + row_id + 
									"$NotOut$select_Language*FUNCTION*Omo*vis_con SET ", config, Constants.TG20, print_writers, foreignLanguageOmo);
							foreignLanguageDataList = CricketFunctions.AssembleMultiLanguageData(CricketUtil.DICTIONARY, "", multilanguagedata,"v",
								    "", null, 1,foreignLanguageDataList);
		    					foreignLanguageDataList = CricketFunctions.AssembleMultiLanguageData(CricketUtil.TEAM, CricketUtil.TEAMNAME_3, multilanguagedata,inning.getBowling_team().getTeamName1().trim().toUpperCase(),
									    "", null, 2,foreignLanguageDataList);
		
							foreignLanguageData.add(CricketFunctions.MergeForeignLanguageDataListToSingleObject(foreignLanguageDataList));
							
							CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$Minis$Side" + WhichSide 
									+ "$Batting$Row" + row_id + "$NotOut$English$txt_BatterName*GEOM*TEXT SET ",config, Constants.BCCI, print_writers, foreignLanguageData);
							
//							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Minis$Side" + WhichSide 
//									+ "$Batting$Row" + row_id + "$NotOut$txt_Name*GEOM*TEXT SET v " + inning.getBowling_team().getTeamName3() + " \0", print_writers);
							
							if(bc.getStatus().equalsIgnoreCase(CricketUtil.OUT)) {
								
								CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Minis$Side" + WhichSide 
										+ "$Batting$Row" + row_id + "$NotOut$txt_Runs*GEOM*TEXT SET " + bc.getRuns() + "\0", print_writers);
								
							}else {
								
								CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Minis$Side" + WhichSide 
										+ "$Batting$Row" + row_id + "$NotOut$txt_Runs*GEOM*TEXT SET " + bc.getRuns() + "*" + "\0", print_writers);
							}
							
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Minis$Side" + WhichSide 
									+ "$Batting$Row" + row_id + "$NotOut$txt_Balls*GEOM*TEXT SET " + bc.getBalls() + "\0", print_writers);
							
						}
					}
				}
				if(!playerFound) {
					row_id++;
					
//					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Minis$Side" + WhichSide 
//							+ "$Batting$Row" + row_id + "$select_RowType$Players_Highlight$In_Out*FUNCTION*Omo*vis_con SET 0\0", print_writers);
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Minis$Side" + WhichSide 
							+ "$Batting$DataGrp*FUNCTION*Grid*num_row SET " + row_id + " \0", print_writers);
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Minis$Side" + WhichSide 
							+ "$Batting$Row" + row_id + "*ACTIVE SET 1\0", print_writers);
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Minis$Side" + WhichSide 
							+ "$Batting$Row" + row_id + "$select_RowType*FUNCTION*Omo*vis_con SET 2\0", print_writers);
					
					CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$Minis$Side" + WhichSide + "$Batting$DataGrp$Row" + row_id + 
							"$NotOut$select_Language*FUNCTION*Omo*vis_con SET ", config, Constants.TG20, print_writers, foreignLanguageOmo);
					foreignLanguageDataList = CricketFunctions.AssembleMultiLanguageData(CricketUtil.DICTIONARY, "", multilanguagedata,"v",
						    "", null, 1,foreignLanguageDataList);
    					foreignLanguageDataList = CricketFunctions.AssembleMultiLanguageData(CricketUtil.TEAM, CricketUtil.TEAMNAME_3, multilanguagedata,inning.getBowling_team().getTeamName1().trim().toUpperCase(),
							    "", null, 2,foreignLanguageDataList);

					foreignLanguageData.add(CricketFunctions.MergeForeignLanguageDataListToSingleObject(foreignLanguageDataList));
					
					CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$Minis$Side" + WhichSide 
							+ "$Batting$Row" + row_id + "$NotOut$English$txt_BatterName*GEOM*TEXT SET ",config, Constants.BCCI, print_writers, foreignLanguageData);
					
//					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$miMinisnis$Side" + WhichSide 
//							+ "$Batting$Row" + row_id + "$NotOut$txt_Name*GEOM*TEXT SET v " + inning.getBowling_team().getTeamName3() + " \0", print_writers);
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Minis$Side" + WhichSide 
							+ "$Batting$Row" + row_id + "$NotOut$txt_Balls*GEOM*TEXT SET \0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Minis$Side" + WhichSide 
							+ "$Batting$Row" + row_id + "$NotOut$txt_Runs*GEOM*TEXT SET DNP\0", print_writers);
					
				}
				break;
//			case "Alt_F1":
//				int omo_num = 0, row_id = 0;
//				boolean playerFound = false;
//				
//				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Minis$Side" + WhichSide 
//						+ "$Batting$img_Flag*TEXTURE*IMAGE SET " + logoPath  + team.getTeamBadge() + " \0", print_writers);
//				
//				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Minis$Side" + WhichSide + "$Batting$AllDataGrp$TeamNameGrp$img_Flag$img_Base02*TEXTURE*IMAGE SET " 
//						+ base2Path + team.getTeamBadge() + " \0", print_writers);
//				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Minis$Side" + WhichSide + "$Batting$AllDataGrp$TeamNameGrp$img_Flag$img_Base01*TEXTURE*IMAGE SET " 
//						+ base1Path + team.getTeamBadge() + " \0", print_writers);
//				
//				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Minis$Side" + WhichSide + "$Batting$AllDataGrp$TeamNameGrp$headerBnd$img_Base2*TEXTURE*IMAGE SET " 
//						+ base2Path + team.getTeamBadge() + " \0", print_writers);
//				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Minis$Side" + WhichSide + "$Batting$AllDataGrp$TeamNameGrp$HeaderText$img_Text2*TEXTURE*IMAGE SET " 
//						+ text2Path + team.getTeamBadge() + " \0", print_writers);
//				
//				CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$Minis$Side" + WhichSide + "$Batting$AllDataGrp$Header$Name$"
//						+ "select_Language*FUNCTION*Omo*vis_con SET ", config, Constants.TG20, print_writers, foreignLanguageOmo);
//				foreignLanguageData = CricketFunctions.AssembleMultiLanguageData(CricketUtil.PLAYER, CricketUtil.FULLNAME, multilanguagedata, player.getFull_name(), 
//						"", null, 0, foreignLanguageDataList);
//				CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$Minis$Side" + WhichSide + "$Batting$AllDataGrp$Header$Name$English$"
//						+ "txt_FirstName*GEOM*TEXT SET ", config, Constants.TG20, print_writers, foreignLanguageData);
//				
////				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Minis$Side" + WhichSide 
////						+ "$Batting$txt_FirstName*GEOM*TEXT SET " + player.getFull_name() + " \0", print_writers);
//				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Minis$Side" + WhichSide 
//						+ "*FUNCTION*Omo*vis_con SET 1 \0", print_writers);
//				
//				processedMatches = new HashSet<>();
//				playerMatchData = new HashMap<>();
//
//				// FIRST PASS
//				for(HeadToHeadPlayer h2h : headToHead) {
//					if(h2h.getTeam() == null || h2h.getOpponentTeam() == null)
//						continue;
//					if(!h2h.getTeam().getTeamName4().equalsIgnoreCase(team.getTeamName4()))
//						continue;
//					String matchName = h2h.getMatchFileName();
//
//					// STORE PLAYER DATA
//					if(h2h.getPlayerId() == player.getPlayerId()) {
//						playerMatchData.put(matchName, h2h);
//					}
//				}
//
//				// SECOND PASS
//				for(HeadToHeadPlayer h2h : headToHead) {
//					if(h2h.getTeam() == null || h2h.getOpponentTeam() == null)
//						continue;
//					if(!h2h.getTeam().getTeamName4().equalsIgnoreCase(team.getTeamName4()))
//						continue;
//					String matchName = h2h.getMatchFileName();
//
//					// AVOID DUPLICATE MATCH ROWS
//					if(processedMatches.contains(matchName))
//						continue;
//					processedMatches.add(matchName);
//					row_id++;
//					
//					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Minis$Side" + WhichSide 
//							+ "$Batting$DataGrp*FUNCTION*Grid*num_row SET " + row_id + " \0", print_writers);
//					
//					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$minis$Side" + WhichSide 
//							+ "$Batting$Row" + row_id + "*ACTIVE SET 1\0", print_writers);
//					
//					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Minis$Side" + WhichSide 
//							+ "$Batting$Row" + row_id + "$select_RowType*FUNCTION*Omo*vis_con SET 1\0", print_writers);
//					
//					CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$Minis$Side" + WhichSide + "$Batting$DataGrp$Row" + row_id + 
//							"$Out$Out$select_Language*FUNCTION*Omo*vis_con SET ", config, Constants.TG20, print_writers, foreignLanguageOmo);
//					
//					if(h2h.getMatchFileName().contains("ELIMINATOR")) {
//						foreignLanguageDataList = CricketFunctions.AssembleMultiLanguageData(CricketUtil.DICTIONARY, "", multilanguagedata,"v",
//							    "", null, 1,foreignLanguageDataList);
//	    					foreignLanguageDataList = CricketFunctions.AssembleMultiLanguageData(CricketUtil.TEAM, CricketUtil.TEAMNAME_3, multilanguagedata,h2h.getOpponentTeam().getTeamName1().trim().toUpperCase(),
//								    "", null, 2,foreignLanguageDataList);
//	    					foreignLanguageDataList = CricketFunctions.AssembleMultiLanguageData(CricketUtil.DICTIONARY, "", multilanguagedata,",",
//								    "", null, 3,foreignLanguageDataList);
//	    					foreignLanguageDataList = CricketFunctions.AssembleMultiLanguageData(CricketUtil.DICTIONARY, "", multilanguagedata,"ELM",
//								    "", null, 4,foreignLanguageDataList);
//	    					
//						foreignLanguageData.add(CricketFunctions.MergeForeignLanguageDataListToSingleObject(foreignLanguageDataList));
//						
//						CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$Minis$Side" + WhichSide 
//								+ "$Batting$Row" + row_id + "$Out$Out$English$txt_BatterName*GEOM*TEXT SET ",config, Constants.BCCI, print_writers, foreignLanguageData);
//					}else if(h2h.getMatchFileName().contains("QUALIFIER 1")) {
//						
//						foreignLanguageDataList = CricketFunctions.AssembleMultiLanguageData(CricketUtil.DICTIONARY, "", multilanguagedata,"v",
//							    "", null, 1,foreignLanguageDataList);
//	    					foreignLanguageDataList = CricketFunctions.AssembleMultiLanguageData(CricketUtil.TEAM, CricketUtil.TEAMNAME_3, multilanguagedata,h2h.getOpponentTeam().getTeamName1().trim().toUpperCase(),
//								    "", null, 2,foreignLanguageDataList);
//	    					foreignLanguageDataList = CricketFunctions.AssembleMultiLanguageData(CricketUtil.DICTIONARY, "", multilanguagedata,",",
//								    "", null, 3,foreignLanguageDataList);
//	    					foreignLanguageDataList = CricketFunctions.AssembleMultiLanguageData(CricketUtil.DICTIONARY, "", multilanguagedata,"QF 1",
//								    "", null, 4,foreignLanguageDataList);
//	    					
//						foreignLanguageData.add(CricketFunctions.MergeForeignLanguageDataListToSingleObject(foreignLanguageDataList));
//						
//						CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$Minis$Side" + WhichSide 
//								+ "$Batting$Row" + row_id + "$Out$Out$English$txt_BatterName*GEOM*TEXT SET ",config, Constants.BCCI, print_writers, foreignLanguageData);
//					}else if(h2h.getMatchFileName().contains("QUALIFIER 2")) {
//						
//						foreignLanguageDataList = CricketFunctions.AssembleMultiLanguageData(CricketUtil.DICTIONARY, "", multilanguagedata,"v",
//							    "", null, 1,foreignLanguageDataList);
//	    					foreignLanguageDataList = CricketFunctions.AssembleMultiLanguageData(CricketUtil.TEAM, CricketUtil.TEAMNAME_3, multilanguagedata,h2h.getOpponentTeam().getTeamName1().trim().toUpperCase(),
//								    "", null, 2,foreignLanguageDataList);
//	    					foreignLanguageDataList = CricketFunctions.AssembleMultiLanguageData(CricketUtil.DICTIONARY, "", multilanguagedata,",",
//								    "", null, 3,foreignLanguageDataList);
//	    					foreignLanguageDataList = CricketFunctions.AssembleMultiLanguageData(CricketUtil.DICTIONARY, "", multilanguagedata,"QF 2",
//								    "", null, 4,foreignLanguageDataList);
//	    					
//						foreignLanguageData.add(CricketFunctions.MergeForeignLanguageDataListToSingleObject(foreignLanguageDataList));
//						
//						CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$Minis$Side" + WhichSide 
//								+ "$Batting$Row" + row_id + "$Out$Out$English$txt_BatterName*GEOM*TEXT SET ",config, Constants.BCCI, print_writers, foreignLanguageData);
//					}else if(h2h.getMatchFileName().contains("SEMI-FINAL 1")) {
//						
//						foreignLanguageDataList = CricketFunctions.AssembleMultiLanguageData(CricketUtil.DICTIONARY, "", multilanguagedata,"v",
//							    "", null, 1,foreignLanguageDataList);
//	    					foreignLanguageDataList = CricketFunctions.AssembleMultiLanguageData(CricketUtil.TEAM, CricketUtil.TEAMNAME_3, multilanguagedata,h2h.getOpponentTeam().getTeamName1().trim().toUpperCase(),
//								    "", null, 2,foreignLanguageDataList);
//	    					foreignLanguageDataList = CricketFunctions.AssembleMultiLanguageData(CricketUtil.DICTIONARY, "", multilanguagedata,",",
//								    "", null, 3,foreignLanguageDataList);
//	    					foreignLanguageDataList = CricketFunctions.AssembleMultiLanguageData(CricketUtil.DICTIONARY, "", multilanguagedata,"SF 1",
//								    "", null, 4,foreignLanguageDataList);
//	    					
//						foreignLanguageData.add(CricketFunctions.MergeForeignLanguageDataListToSingleObject(foreignLanguageDataList));
//						
//						CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$Minis$Side" + WhichSide 
//								+ "$Batting$Row" + row_id + "$Out$Out$English$txt_BatterName*GEOM*TEXT SET ",config, Constants.BCCI, print_writers, foreignLanguageData);
//					}else if(h2h.getMatchFileName().contains("SEMI-FINAL 2")) {
//						
//						foreignLanguageDataList = CricketFunctions.AssembleMultiLanguageData(CricketUtil.DICTIONARY, "", multilanguagedata,"v",
//							    "", null, 1,foreignLanguageDataList);
//	    					foreignLanguageDataList = CricketFunctions.AssembleMultiLanguageData(CricketUtil.TEAM, CricketUtil.TEAMNAME_3, multilanguagedata,h2h.getOpponentTeam().getTeamName1().trim().toUpperCase(),
//								    "", null, 2,foreignLanguageDataList);
//	    					foreignLanguageDataList = CricketFunctions.AssembleMultiLanguageData(CricketUtil.DICTIONARY, "", multilanguagedata,",",
//								    "", null, 3,foreignLanguageDataList);
//	    					foreignLanguageDataList = CricketFunctions.AssembleMultiLanguageData(CricketUtil.DICTIONARY, "", multilanguagedata,"SF 2",
//								    "", null, 4,foreignLanguageDataList);
//	    					
//						foreignLanguageData.add(CricketFunctions.MergeForeignLanguageDataListToSingleObject(foreignLanguageDataList));
//						
//						CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$Minis$Side" + WhichSide 
//								+ "$Batting$Row" + row_id + "$Out$Out$English$txt_BatterName*GEOM*TEXT SET ",config, Constants.BCCI, print_writers, foreignLanguageData);
//					}else if(h2h.getMatchFileName().contains("FINAL")) {
//						
//						foreignLanguageDataList = CricketFunctions.AssembleMultiLanguageData(CricketUtil.DICTIONARY, "", multilanguagedata,"v",
//							    "", null, 1,foreignLanguageDataList);
//	    					foreignLanguageDataList = CricketFunctions.AssembleMultiLanguageData(CricketUtil.TEAM, CricketUtil.TEAMNAME_3, multilanguagedata,h2h.getOpponentTeam().getTeamName1().trim().toUpperCase(),
//								    "", null, 2,foreignLanguageDataList);
//	    					foreignLanguageDataList = CricketFunctions.AssembleMultiLanguageData(CricketUtil.DICTIONARY, "", multilanguagedata,",",
//								    "", null, 3,foreignLanguageDataList);
//	    					foreignLanguageDataList = CricketFunctions.AssembleMultiLanguageData(CricketUtil.DICTIONARY, "", multilanguagedata,"FINAL",
//								    "", null, 4,foreignLanguageDataList);
//
//						foreignLanguageData.add(CricketFunctions.MergeForeignLanguageDataListToSingleObject(foreignLanguageDataList));
//						
//						CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$Minis$Side" + WhichSide 
//								+ "$Batting$Row" + row_id + "$Out$Out$English$txt_BatterName*GEOM*TEXT SET ",config, Constants.BCCI, print_writers, foreignLanguageData);
//					}else {
//						
//						foreignLanguageDataList = CricketFunctions.AssembleMultiLanguageData(CricketUtil.DICTIONARY, "", multilanguagedata,"v",
//							    "", null, 1,foreignLanguageDataList);
//	    					foreignLanguageDataList = CricketFunctions.AssembleMultiLanguageData(CricketUtil.TEAM, CricketUtil.TEAMNAME_3, multilanguagedata,h2h.getOpponentTeam().getTeamName1().trim().toUpperCase(),
//								    "", null, 2,foreignLanguageDataList);
//
//						foreignLanguageData.add(CricketFunctions.MergeForeignLanguageDataListToSingleObject(foreignLanguageDataList));
//						
//						CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$Minis$Side" + WhichSide 
//								+ "$Batting$Row" + row_id + "$Out$Out$English$txt_BatterName*GEOM*TEXT SET ",config, Constants.BCCI, print_writers, foreignLanguageData);
//					}
//
//					// PLAYER PLAYED
//					if(playerMatchData.containsKey(matchName)) {
//						HeadToHeadPlayer ply = playerMatchData.get(matchName);
//						if(ply.getInningStarted().contains("Y")) {
//							// NOT OUT
//							if(ply.getDismissed().contains("N")) {
//								CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Minis$Side" + WhichSide 
//										+ "$Batting$Row" + row_id + "$Out$txt_Runs*GEOM*TEXT SET " + ply.getRuns() + "\0", print_writers);
//							}else {
//								CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Minis$Side" + WhichSide 
//										+ "$Batting$Row" + row_id + "$Out$txt_Runs*GEOM*TEXT SET " + ply.getRuns() + "\0", print_writers);
//							}
//
//							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Minis$Side" + WhichSide 
//									+ "$Batting$Row" + row_id + "$Out$txt_Balls*GEOM*TEXT SET " + ply.getBallsFaced() + "\0", print_writers);
//						}else {
//
//							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Minis$Side" + WhichSide 
//									+ "$Batting$Row" + row_id + "$Out$txt_Runs*GEOM*TEXT SET DNB\0", print_writers);
//							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Minis$Side" + WhichSide 
//									+ "$Batting$Row" + row_id + "$Out$txt_Balls*GEOM*TEXT SET \0", print_writers);
//						}
//
//					}
//					// PLAYER DID NOT PLAY
//					else {
//						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Minis$Side" + WhichSide 
//								+ "$Batting$Row" + row_id + "$Out$txt_Runs*GEOM*TEXT SET DNP\0", print_writers);
//						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Minis$Side" + WhichSide 
//								+ "$Batting$Row" + row_id + "$Out$txt_Balls*GEOM*TEXT SET \0", print_writers);
//					}
//				}
//				
//				for(BattingCard bc : inning.getBattingCard()) {
//					if(bc.getPlayerId() == player.getPlayerId()) {
//						row_id++;
//						playerFound = true;
//						if(bc.getStatus().equalsIgnoreCase(CricketUtil.STILL_TO_BAT)) {
//							
//							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Minis$Datas$select_Rows*FUNCTION*Omo*vis_con SET " + row_id + " \0",print_writers);
//							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Minis$Datas$Row" + row_id + "$select_DataType*FUNCTION*Omo*vis_con SET 1\0",print_writers);
//							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Minis$Datas$Row" + row_id + "$Out$NotOut$select_Impact*FUNCTION*Omo*vis_con SET 0\0",print_writers);
//							
//							if(matchAllData.getSetup().getMatchIdent().contains("ELIMINATOR")) {
//								CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Minis$Datas$Row" + row_id + "$Out$NotOut$txt_BatterName*GEOM*TEXT SET v " 
//										+ inning.getBowling_team().getTeamBadge() + ", ELM" + " \0",print_writers);
//							}else if(matchAllData.getSetup().getMatchIdent().contains("QUALIFIER 1")) {
//								CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Minis$Datas$Row" + row_id + "$Out$NotOut$txt_BatterName*GEOM*TEXT SET v " 
//										+ inning.getBowling_team().getTeamBadge() + ", QF 1" + " \0",print_writers);
//							}else if(matchAllData.getSetup().getMatchIdent().contains("QUALIFIER 2")) {
//								CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Minis$Datas$Row" + row_id + "$Out$NotOut$txt_BatterName*GEOM*TEXT SET v " 
//										+ inning.getBowling_team().getTeamBadge() + ", QF 2" + " \0",print_writers);
//							}else if(matchAllData.getSetup().getMatchIdent().contains("SEMI-FINAL 1")) {
//								CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Minis$Datas$Row" + row_id + "$Out$NotOut$txt_BatterName*GEOM*TEXT SET v " 
//										+ inning.getBowling_team().getTeamBadge() + ", SF 1" + " \0",print_writers);
//							}else if(matchAllData.getSetup().getMatchIdent().contains("SEMI-FINAL 2")) {
//								CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Minis$Datas$Row" + row_id + "$Out$NotOut$txt_BatterName*GEOM*TEXT SET v " 
//										+ inning.getBowling_team().getTeamBadge() + ", SF 2" + " \0",print_writers);
//							}else if(matchAllData.getSetup().getMatchIdent().contains("FINAL")) {
//								CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Minis$Datas$Row" + row_id + "$Out$NotOut$txt_BatterName*GEOM*TEXT SET v " 
//										+ inning.getBowling_team().getTeamBadge() + ", FINAL" + " \0",print_writers);
//							}else {
//								CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Minis$Datas$Row" + row_id + "$Out$NotOut$txt_BatterName*GEOM*TEXT SET v " 
//										+ inning.getBowling_team().getTeamBadge() + " \0",print_writers);
//							}
//							
//							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Minis$Datas$Row" + row_id + "$Out$NotOut$txt_Balls*GEOM*TEXT SET \0",print_writers);
//							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Minis$Datas$Row" + row_id + "$Out$NotOut$txt_Scores*GEOM*TEXT SET DNB\0", print_writers);
//							
//						}else {
//							
//							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Minis$Datas$select_Rows*FUNCTION*Omo*vis_con SET " + row_id + " \0",print_writers);
//							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Minis$Datas$Row" + row_id + "$select_DataType*FUNCTION*Omo*vis_con SET 1\0",print_writers);
//							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Minis$Datas$Row" + row_id + "$Out$NotOut$select_Impact*FUNCTION*Omo*vis_con SET 0\0",print_writers);
//							
//							if(matchAllData.getSetup().getMatchIdent().contains("ELIMINATOR")) {
//								CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Minis$Datas$Row" + row_id + "$Out$NotOut$txt_BatterName*GEOM*TEXT SET v " 
//										+ inning.getBowling_team().getTeamBadge() + ", ELM" + " \0",print_writers);
//							}else if(matchAllData.getSetup().getMatchIdent().contains("QUALIFIER 1")) {
//								CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Minis$Datas$Row" + row_id + "$Out$NotOut$txt_BatterName*GEOM*TEXT SET v " 
//										+ inning.getBowling_team().getTeamBadge() + ", QF 1" + " \0",print_writers);
//							}else if(matchAllData.getSetup().getMatchIdent().contains("QUALIFIER 2")) {
//								CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Minis$Datas$Row" + row_id + "$Out$NotOut$txt_BatterName*GEOM*TEXT SET v " 
//										+ inning.getBowling_team().getTeamBadge() + ", QF 2" + " \0",print_writers);
//							}else if(matchAllData.getSetup().getMatchIdent().contains("SEMI-FINAL 1")) {
//								CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Minis$Datas$Row" + row_id + "$Out$NotOut$txt_BatterName*GEOM*TEXT SET v " 
//										+ inning.getBowling_team().getTeamBadge() + ", SF 1" + " \0",print_writers);
//							}else if(matchAllData.getSetup().getMatchIdent().contains("SEMI-FINAL 2")) {
//								CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Minis$Datas$Row" + row_id + "$Out$NotOut$txt_BatterName*GEOM*TEXT SET v " 
//										+ inning.getBowling_team().getTeamBadge() + ", SF 2" + " \0",print_writers);
//							}else if(matchAllData.getSetup().getMatchIdent().contains("FINAL")) {
//								CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Minis$Datas$Row" + row_id + "$Out$NotOut$txt_BatterName*GEOM*TEXT SET v " 
//										+ inning.getBowling_team().getTeamBadge() + ", FINAL" + " \0",print_writers);
//							}else {
//								CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Minis$Datas$Row" + row_id + "$Out$NotOut$txt_BatterName*GEOM*TEXT SET v " 
//										+ inning.getBowling_team().getTeamBadge() + " \0",print_writers);
//							}
//							
//							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Minis$Datas$Row" + row_id + "$Out$NotOut$txt_Balls*GEOM*TEXT SET " + bc.getBalls() + "\0",print_writers);
//							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Minis$Datas$Row" + row_id + "$Out$NotOut$txt_Scores*GEOM*TEXT SET "
//									+ bc.getRuns() + (bc.getStatus().equalsIgnoreCase(CricketUtil.NOT_OUT)?"*":"") +"\0", print_writers);
//						}
//					}
//				}
//				if(!playerFound) {
//					row_id++;
//					
//					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Minis$Datas$select_Rows*FUNCTION*Omo*vis_con SET " + row_id + " \0",print_writers);
//					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Minis$Datas$Row" + row_id + "$select_DataType*FUNCTION*Omo*vis_con SET 1\0",print_writers);
//					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Minis$Datas$Row" + row_id + "$Out$NotOut$select_Impact*FUNCTION*Omo*vis_con SET 0\0",print_writers);
//					
//					if(matchAllData.getSetup().getMatchIdent().contains("ELIMINATOR")) {
//						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Minis$Datas$Row" + row_id + "$Out$NotOut$txt_BatterName*GEOM*TEXT SET v " 
//								+ inning.getBowling_team().getTeamBadge() + ", ELM" + " \0",print_writers);
//					}else if(matchAllData.getSetup().getMatchIdent().contains("QUALIFIER 1")) {
//						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Minis$Datas$Row" + row_id + "$Out$NotOut$txt_BatterName*GEOM*TEXT SET v " 
//								+ inning.getBowling_team().getTeamBadge() + ", QF 1" + " \0",print_writers);
//					}else if(matchAllData.getSetup().getMatchIdent().contains("QUALIFIER 2")) {
//						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Minis$Datas$Row" + row_id + "$Out$NotOut$txt_BatterName*GEOM*TEXT SET v " 
//								+ inning.getBowling_team().getTeamBadge() + ", QF 2" + " \0",print_writers);
//					}else if(matchAllData.getSetup().getMatchIdent().contains("SEMI-FINAL 1")) {
//						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Minis$Datas$Row" + row_id + "$Out$NotOut$txt_BatterName*GEOM*TEXT SET v " 
//								+ inning.getBowling_team().getTeamBadge() + ", SF 1" + " \0",print_writers);
//					}else if(matchAllData.getSetup().getMatchIdent().contains("SEMI-FINAL 2")) {
//						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Minis$Datas$Row" + row_id + "$Out$NotOut$txt_BatterName*GEOM*TEXT SET v " 
//								+ inning.getBowling_team().getTeamBadge() + ", SF 2" + " \0",print_writers);
//					}else if(matchAllData.getSetup().getMatchIdent().contains("FINAL")) {
//						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Minis$Datas$Row" + row_id + "$Out$NotOut$txt_BatterName*GEOM*TEXT SET v " 
//								+ inning.getBowling_team().getTeamBadge() + ", FINAL" + " \0",print_writers);
//					}else {
//						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Minis$Datas$Row" + row_id + "$Out$NotOut$txt_BatterName*GEOM*TEXT SET v " 
//								+ inning.getBowling_team().getTeamBadge() + " \0",print_writers);
//					}
//					
//					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Minis$Datas$Row" + row_id + "$Out$NotOut$txt_Balls*GEOM*TEXT SET \0",print_writers);
//					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Minis$Datas$Row" + row_id + "$Out$NotOut$txt_Scores*GEOM*TEXT SET DNP\0", print_writers);
//					
//				}
//				break;
//			case "Alt_F2":
//				int row_no1 = 0;
//				rowId = 0;
//				
//				for(int i=1;i<=13;i++) {
//					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Minis$Datas$Row" + i + "$Out$Out$img_Base1*TEXTURE*IMAGE SET " + Constants.T20_MUMBAI_Base1 
//							+ inning.getBowling_team().getTeamBadge() + "\0", print_writers);
//					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Minis$Datas$Row" + i + "$Out$NotOut$img_Base1*TEXTURE*IMAGE SET " + Constants.T20_MUMBAI_Base1 
//							+ inning.getBowling_team().getTeamBadge() + "\0", print_writers);
//					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Minis$Datas$Row" + i + "$Out$NotOut$img_Text1*TEXTURE*IMAGE SET " + Constants.T20_MUMBAI_Text1 
//							+ inning.getBowling_team().getTeamBadge() + "\0", print_writers);
//				}
//				
//				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Minis$PositionY$Logo$img_Logos_Shadow*TEXTURE*IMAGE SET " 
//						+ Constants.T20_MUMBAI_Logos + inning.getBowling_team().getTeamBadge() + "\0", print_writers);
//				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Minis$PositionY$Logo$img_Logos*TEXTURE*IMAGE SET " 
//						+ Constants.T20_MUMBAI_Logos + inning.getBowling_team().getTeamBadge() + "\0", print_writers);
//				
//				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Minis$Essentials$img_Base1"
//						+ "*TEXTURE*IMAGE SET " + Constants.T20_MUMBAI_Base1 + inning.getBowling_team().getTeamBadge() + "\0", print_writers);
//				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Minis$PositionY$Elements$img_Base1"
//						+ "*TEXTURE*IMAGE SET " + Constants.T20_MUMBAI_Base1 + inning.getBowling_team().getTeamBadge() + "\0", print_writers);
//				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Minis$PositionY$HeaderGrp$txt_Text"
//						+ "*TEXTURE*IMAGE SET " + Constants.T20_MUMBAI_Text + inning.getBowling_team().getTeamBadge() + "\0", print_writers);
//				
//				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Minis$ColouredBaseInWipe$TeamColourBase1$img_Base1"
//						+ "*TEXTURE*IMAGE SET " + Constants.T20_MUMBAI_Base1 + inning.getBowling_team().getTeamBadge() + "\0", print_writers);
//				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Minis$ColouredBaseInWipe$TeamColourBase2$img_Base2"
//						+ "*TEXTURE*IMAGE SET " + Constants.T20_MUMBAI_Base2 + inning.getBowling_team().getTeamBadge() + "\0", print_writers);
//				
//				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Minis$ColouredBaseOutWipe$TeamColourBase1$img_Base1"
//						+ "*TEXTURE*IMAGE SET " + Constants.T20_MUMBAI_Base1 + inning.getBowling_team().getTeamBadge() + "\0", print_writers);
//				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Minis$ColouredBaseOutWipe$TeamColourBase2$img_Base2"
//						+ "*TEXTURE*IMAGE SET " + Constants.T20_MUMBAI_Base2 + inning.getBowling_team().getTeamBadge() + "\0", print_writers);
//				
//				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Minis$PositionY$HeaderGrp$FirstnName$txt_FirstName"
//						+ "*GEOM*TEXT SET " + (player.getSurname() != null ? player.getFirstname() : "") + "\0", print_writers);
//				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Minis$PositionY$HeaderGrp$LastName$txt_LastName"
//						+ "*GEOM*TEXT SET " + (player.getSurname() != null ? player.getSurname() : player.getFirstname()) + "\0", print_writers);
//				
//				processedMatches = new HashSet<>();
//				playerMatchData = new HashMap<>();
//
//				// FIRST PASS
//				for(HeadToHeadPlayer h2h : headToHead) {
//					if(h2h.getTeam() == null || h2h.getOpponentTeam() == null)
//						continue;
//					if(!h2h.getTeam().getTeamName4().equalsIgnoreCase(team.getTeamName4()))
//						continue;
//					if(h2h.getPlayerId() == player.getPlayerId()) {
//						playerMatchData.put(h2h.getMatchFileName(), h2h);
//					}
//				}
//
//				// SECOND PASS
//				for(HeadToHeadPlayer h2h : headToHead) {
//					if(h2h.getTeam() == null || h2h.getOpponentTeam() == null)
//						continue;
//					if(!h2h.getTeam().getTeamName4().equalsIgnoreCase(team.getTeamName4()))
//						continue;
//					String matchFile = h2h.getMatchFileName();
//					// AVOID DUPLICATE MATCH ROWS
//					if(processedMatches.contains(matchFile))
//						continue;
//					processedMatches.add(matchFile);
//					row_no1++;
//
//					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Minis$Datas$select_Rows*FUNCTION*Omo*vis_con SET " + row_no1 + " \0",print_writers);
//					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Minis$Datas$Row" + row_no1 + "$select_DataType*FUNCTION*Omo*vis_con SET 0\0",print_writers);
//					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Minis$Datas$Row" + row_no1 + "$Out$Out$select_Impact*FUNCTION*Omo*vis_con SET 0\0",print_writers);
//					
//					if(h2h.getMatchFileName().contains("ELIMINATOR")) {
//						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Minis$Datas$Row" + row_no1 + "$Out$Out$txt_BatterName*GEOM*TEXT SET v " 
//								+ h2h.getOpponentTeam().getTeamBadge() + ", ELM" + " \0",print_writers);
//					}else if(h2h.getMatchFileName().contains("QUALIFIER 1")) {
//						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Minis$Datas$Row" + row_no1 + "$Out$Out$txt_BatterName*GEOM*TEXT SET v " 
//								+ h2h.getOpponentTeam().getTeamBadge() + ", QF 1" + " \0",print_writers);
//					}else if(h2h.getMatchFileName().contains("QUALIFIER 2")) {
//						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Minis$Datas$Row" + row_no1 + "$Out$Out$txt_BatterName*GEOM*TEXT SET v " 
//								+ h2h.getOpponentTeam().getTeamBadge() + ", QF 2" + " \0",print_writers);
//					}else if(h2h.getMatchFileName().contains("SEMI-FINAL 1")) {
//						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Minis$Datas$Row" + row_no1 + "$Out$Out$txt_BatterName*GEOM*TEXT SET v " 
//								+ h2h.getOpponentTeam().getTeamBadge() + ", SF 1" + " \0",print_writers);
//					}else if(h2h.getMatchFileName().contains("SEMI-FINAL 2")) {
//						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Minis$Datas$Row" + row_no1 + "$Out$Out$txt_BatterName*GEOM*TEXT SET v " 
//								+ h2h.getOpponentTeam().getTeamBadge() + ", SF 2" + " \0",print_writers);
//					}else if(h2h.getMatchFileName().contains("FINAL")) {
//						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Minis$Datas$Row" + row_no1 + "$Out$Out$txt_BatterName*GEOM*TEXT SET v " 
//								+ h2h.getOpponentTeam().getTeamBadge() + ", FINAL" + " \0",print_writers);
//					}else {
//						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Minis$Datas$Row" + row_no1 + "$Out$Out$txt_BatterName*GEOM*TEXT SET v " 
//								+ h2h.getOpponentTeam().getTeamBadge() + " \0",print_writers);
//					}
//
//					// PLAYER PLAYED
//					if(playerMatchData.containsKey(matchFile)) {
//						HeadToHeadPlayer ply = playerMatchData.get(matchFile);
//						// DID NOT BOWL
//						if(ply.getBallsBowled() == 0) {
//							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Minis$Datas$Row" + row_no1 + "$Out$Out$txt_Balls*GEOM*TEXT SET \0",print_writers);
//							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Minis$Datas$Row" + row_no1 + "$Out$Out$txt_Scores*GEOM*TEXT SET DNB\0", print_writers);
//						}else {
//							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Minis$Datas$Row" + row_no1 + "$Out$Out$txt_Scores*GEOM*TEXT SET "
//									+ ply.getWickets() + "-" + ply.getRunsConceded() + "\0",print_writers);
//							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Minis$Datas$Row" + row_no1 + "$Out$Out$txt_Balls*GEOM*TEXT SET "
//									+ CricketFunctions.OverBalls(0, ply.getBallsBowled()) + "\0", print_writers);
//						}
//					}
//					// PLAYER DID NOT PLAY
//					else {
//						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Minis$Datas$Row" + row_no1 + "$Out$Out$txt_Balls*GEOM*TEXT SET \0",print_writers);
//						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Minis$Datas$Row" + row_no1 + "$Out$Out$txt_Scores*GEOM*TEXT SET DNP\0", print_writers);
//					}
//				}
//				
//				boolean playerIsInBoc1 = false;
//				if(inning.getBowlingCard() != null) {
//					for(BowlingCard boc : inning.getBowlingCard()) {
//						if(boc.getPlayerId() == player.getPlayerId()) {
//							playerIsInBoc1 = true;
//							row_no1++;
//							
//							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Minis$Datas$select_Rows*FUNCTION*Omo*vis_con SET " + row_no1 + " \0",print_writers);
//							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Minis$Datas$Row" + row_no1 + "$select_DataType*FUNCTION*Omo*vis_con SET 1\0",print_writers);
//							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Minis$Datas$Row" + row_no1 + "$Out$NotOut$select_Impact*FUNCTION*Omo*vis_con SET 0\0",print_writers);
//							
//							if(matchAllData.getSetup().getMatchIdent().contains("ELIMINATOR")) {
//								CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Minis$Datas$Row" + row_no1 + "$Out$NotOut$txt_BatterName*GEOM*TEXT SET v " 
//										+ inning.getBatting_team().getTeamBadge() + ", ELM" + " \0",print_writers);
//							}else if(matchAllData.getSetup().getMatchIdent().contains("QUALIFIER 1")) {
//								CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Minis$Datas$Row" + row_no1 + "$Out$NotOut$txt_BatterName*GEOM*TEXT SET v " 
//										+ inning.getBatting_team().getTeamBadge() + ", QF 1" + " \0",print_writers);
//							}else if(matchAllData.getSetup().getMatchIdent().contains("QUALIFIER 2")) {
//								CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Minis$Datas$Row" + row_no1 + "$Out$NotOut$txt_BatterName*GEOM*TEXT SET v " 
//										+ inning.getBatting_team().getTeamBadge() + ", QF 2" + " \0",print_writers);
//							}else if(matchAllData.getSetup().getMatchIdent().contains("SEMI-FINAL 1")) {
//								CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Minis$Datas$Row" + row_no1 + "$Out$NotOut$txt_BatterName*GEOM*TEXT SET v " 
//										+ inning.getBatting_team().getTeamBadge() + ", SF 1" + " \0",print_writers);
//							}else if(matchAllData.getSetup().getMatchIdent().contains("SEMI-FINAL 2")) {
//								CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Minis$Datas$Row" + row_no1 + "$Out$NotOut$txt_BatterName*GEOM*TEXT SET v " 
//										+ inning.getBatting_team().getTeamBadge() + ", SF 2" + " \0",print_writers);
//							}else if(matchAllData.getSetup().getMatchIdent().contains("FINAL")) {
//								CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Minis$Datas$Row" + row_no1 + "$Out$NotOut$txt_BatterName*GEOM*TEXT SET v " 
//										+ inning.getBatting_team().getTeamBadge() + ", FINAL" + " \0",print_writers);
//							}else {
//								CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Minis$Datas$Row" + row_no1 + "$Out$NotOut$txt_BatterName*GEOM*TEXT SET v " 
//										+ inning.getBatting_team().getTeamBadge() + " \0",print_writers);
//							}
//							
//							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Minis$Datas$Row" + row_no1 + "$Out$NotOut$txt_Balls*GEOM*TEXT SET "
//									+ CricketFunctions.OverBalls(boc.getOvers(), boc.getBalls()) + "\0",print_writers);
//							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Minis$Datas$Row" + row_no1 + "$Out$NotOut$txt_Scores*GEOM*TEXT SET "
//									+ boc.getWickets()+"-"+ boc.getRuns() + "\0", print_writers);
//							break;
//						}else {
//							playerIsInBoc1 = false;
//						}
//					}
//				}
//				
//				if(!playerIsInBoc1) {
//					row_no1++;
//					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Minis$Datas$select_Rows*FUNCTION*Omo*vis_con SET " + row_no1 + " \0",print_writers);
//					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Minis$Datas$Row" + row_no1 + "$select_DataType*FUNCTION*Omo*vis_con SET 1\0",print_writers);
//					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Minis$Datas$Row" + row_no1 + "$Out$NotOut$select_Impact*FUNCTION*Omo*vis_con SET 0\0",print_writers);
//					
//					if(matchAllData.getSetup().getMatchIdent().contains("ELIMINATOR")) {
//						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Minis$Datas$Row" + row_no1 + "$Out$NotOut$txt_BatterName*GEOM*TEXT SET v " 
//								+ inning.getBatting_team().getTeamBadge() + ", ELM" + " \0",print_writers);
//					}else if(matchAllData.getSetup().getMatchIdent().contains("QUALIFIER 1")) {
//						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Minis$Datas$Row" + row_no1 + "$Out$NotOut$txt_BatterName*GEOM*TEXT SET v " 
//								+ inning.getBatting_team().getTeamBadge() + ", QF 1" + " \0",print_writers);
//					}else if(matchAllData.getSetup().getMatchIdent().contains("QUALIFIER 2")) {
//						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Minis$Datas$Row" + row_no1 + "$Out$NotOut$txt_BatterName*GEOM*TEXT SET v " 
//								+ inning.getBatting_team().getTeamBadge() + ", QF 2" + " \0",print_writers);
//					}else if(matchAllData.getSetup().getMatchIdent().contains("SEMI-FINAL 1")) {
//						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Minis$Datas$Row" + row_no1 + "$Out$NotOut$txt_BatterName*GEOM*TEXT SET v " 
//								+ inning.getBatting_team().getTeamBadge() + ", SF 1" + " \0",print_writers);
//					}else if(matchAllData.getSetup().getMatchIdent().contains("SEMI-FINAL 2")) {
//						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Minis$Datas$Row" + row_no1 + "$Out$NotOut$txt_BatterName*GEOM*TEXT SET v " 
//								+ inning.getBatting_team().getTeamBadge() + ", SF 2" + " \0",print_writers);
//					}else if(matchAllData.getSetup().getMatchIdent().contains("FINAL")) {
//						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Minis$Datas$Row" + row_no1 + "$Out$NotOut$txt_BatterName*GEOM*TEXT SET v " 
//								+ inning.getBatting_team().getTeamBadge() + ", FINAL" + " \0",print_writers);
//					}else {
//						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Minis$Datas$Row" + row_no1 + "$Out$NotOut$txt_BatterName*GEOM*TEXT SET v " 
//								+ inning.getBatting_team().getTeamBadge() + " \0",print_writers);
//					}
//					
//					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Minis$Datas$Row" + row_no1 + "$Out$NotOut$txt_Balls*GEOM*TEXT SET \0",print_writers);
//					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Minis$Datas$Row" + row_no1 + "$Out$NotOut$txt_Scores*GEOM*TEXT SET DNP\0", print_writers);
//					
//					List<Player> plyrs = (matchAllData.getSetup().getHomeTeamId() == team.getTeamId() ? matchAllData.getSetup().getHomeSquad() : 
//						matchAllData.getSetup().getAwaySquad());
//					for(Player plyr : plyrs) {
//						if(plyr.getPlayerId() == player.getPlayerId()) {
//							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Minis$Datas$Row" + row_no1 + "$Out$NotOut$txt_Scores*GEOM*TEXT SET DNB\0", print_writers);
//							break;
//						}
//					}
//				}
//				break;	
				
			case "Shift_F2":
				
				int bowling_size = 0;
				rowId = 0;
				
				cont_name = "";
				omo_num = 0;
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Minis$Side" + WhichSide + "*FUNCTION*Omo*vis_con SET 0 \0", print_writers);
				
				switch(config.getBroadcaster()) {
				case Constants.TRI_SERIES:  case Constants.MT20:
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Minis$Side" + WhichSide + "$Bowling$TeamColors$img_Base1*TEXTURE*IMAGE SET " 
							+ base1Path + inning.getBowling_team().getTeamBadge() + " \0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Minis$Side" + WhichSide + "$Bowling$TeamColors$img_Base2*TEXTURE*IMAGE SET " 
							+ base2Path  + inning.getBowling_team().getTeamBadge() + " \0", print_writers);
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Minis$Side" + WhichSide + "$Bowling$AllDataGrp$TeamNameGrp$headerBnd$img_Base2*TEXTURE*IMAGE SET " 
							+ base2Path + inning.getBowling_team().getTeamBadge() + " \0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Minis$Side" + WhichSide + "$Bowling$AllDataGrp$TeamNameGrp$HeaderText$img_Text2*TEXTURE*IMAGE SET " 
							+ text2Path + inning.getBowling_team().getTeamBadge() + " \0", print_writers);
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Minis$Side" + WhichSide + "$Bowling$Header$headerBnd$img_Header"
							+ "*TEXTURE*IMAGE SET IMAGE*/Default/Essentials/Textures/Color02\0", print_writers);
					for(int i=1;i<=14;i++) {
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Minis$Side" + WhichSide + "$Bowling$DataGrp$Row" + i + "$Players_Highlight$"
								+ "img_Highlight*TEXTURE*IMAGE SET IMAGE*/Default/Essentials/Textures/Color01\0", print_writers);
					}
					break;
				}
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Minis$Side" + WhichSide + "$Bowling$AllDataGrp$TeamNameGrp$img_Flag$img_Base02*TEXTURE*IMAGE SET " 
						+ base2Path + inning.getBowling_team().getTeamBadge() + " \0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Minis$Side" + WhichSide + "$Bowling$AllDataGrp$TeamNameGrp$img_Flag$img_Base01*TEXTURE*IMAGE SET " 
						+ base1Path + inning.getBowling_team().getTeamBadge() + " \0", print_writers);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Minis$Side" + WhichSide + "$Bowling$img_Flag*TEXTURE*IMAGE SET " 
						+ logoPath  + inning.getBowling_team().getTeamBadge() + " \0", print_writers);
				
				switch(config.getBroadcaster()) {
				case Constants.TG20:
					CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$Minis$Side" + WhichSide + "$Bowling$AllDataGrp$Header$Name$"
							+ "select_Language*FUNCTION*Omo*vis_con SET ", config, Constants.TG20, print_writers, foreignLanguageOmo);
					foreignLanguageData = CricketFunctions.AssembleMultiLanguageData(CricketUtil.TEAM, CricketUtil.TEAMNAME_4, multilanguagedata, inning.getBowling_team().getTeamName1(), 
							"", null, 0, foreignLanguageDataList);
					CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$Minis$Side" + WhichSide + "$Bowling$AllDataGrp$Header$Name$English$"
							+ "txt_FirstName*GEOM*TEXT SET ", config, Constants.TG20, print_writers, foreignLanguageData);
					break;
				default:
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Minis$Side" + WhichSide + "$Bowling$txt_FirstName*GEOM*TEXT SET " + 
							(config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES)?inning.getBowling_team().getTeamName3():inning.getBowling_team().getTeamName3())  
							+ " \0", print_writers);
					break;
				}
				
				for(int i=1; i<=inning.getBowlingCard().size(); i++) {
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Minis$Side" + WhichSide + "$Bowling$Row"+(i)+"*ACTIVE SET 1 \0", print_writers);
				}
				
				for (BowlingCard boc : inning.getBowlingCard()) {
					if(boc.getRuns() > 0 || ((boc.getOvers()*6)+boc.getBalls()) > 0) {
						bowling_size=bowling_size + 1;
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Minis$Side" + WhichSide + "$Bowling$DataGrp*FUNCTION*Grid*num_row SET " 
								+ bowling_size + " \0", print_writers);
					}
					
					if(inning.getInningStatus().equalsIgnoreCase(CricketUtil.PAUSE)) {
						omo_num = 1;
						cont_name = "$Dehighlight";
					}else {
						switch (boc.getStatus().toUpperCase()) {
						case (CricketUtil.OTHER + CricketUtil.BOWLER):
							omo_num = 1;
							cont_name = "$Dehighlight";
							break;
						case (CricketUtil.LAST + CricketUtil.BOWLER):
							omo_num = 1;
							cont_name = "$Dehighlight";
							break;
						case (CricketUtil.CURRENT + CricketUtil.BOWLER):
							omo_num = 2;
							cont_name = "$select_BallRowType$Highlight";
							break;
						}
					}
					
					rowId = rowId + 1;
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Minis$Side" + WhichSide + "$Bowling$Row"+rowId+"$select_BallRowType"
							+ "*FUNCTION*Omo*vis_con SET " + String.valueOf(omo_num) + " \0", print_writers);
					switch(config.getBroadcaster()) {
					case Constants.TG20:
						CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$Minis$Side" + WhichSide + "$Bowling$DataGrp$Row" + rowId + cont_name + 
								"$select_Language*FUNCTION*Omo*vis_con SET ", config, Constants.TG20, print_writers, foreignLanguageOmo);
						foreignLanguageData = CricketFunctions.AssembleMultiLanguageData(CricketUtil.PLAYER, CricketUtil.TICKERNAME, multilanguagedata, boc.getPlayer().getTicker_name(), 
								"", null, 0, foreignLanguageDataList);
						CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$Minis$Side" + WhichSide + "$Bowling$DataGrp$Row" + rowId + cont_name + 
								"$English$txt_BowlerName*GEOM*TEXT SET ", config, Constants.TG20, print_writers, foreignLanguageData);
						break;
					default:
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Minis$Side" + WhichSide + "$Bowling$Row" + rowId + cont_name 
								+ "$txt_BowlerName*GEOM*TEXT SET " + boc.getPlayer().getTicker_name() + " \0", print_writers);
						break;
					}
					
					
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Minis$Side" + WhichSide 
							+ "$Bowling$Row" + rowId + cont_name + "$txt_Overs*GEOM*TEXT SET " + CricketFunctions.OverBalls(boc.getOvers(),boc.getBalls()) + " \0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Minis$Side" + WhichSide 
							+ "$Bowling$Row" + rowId + cont_name + "$txt_Figures*GEOM*TEXT SET " + boc.getWickets() + "-" + boc.getRuns() + " \0", print_writers);
				}
				break;
				
			case "Alt_F2":
				int row_no = 0, count = 0;
				String MatchName = "";
				rowId = 0;
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Minis$Side" + WhichSide 
						+ "*FUNCTION*Omo*vis_con SET 0 \0", print_writers);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Minis$Side" + WhichSide 
						+ "$Bowling$img_Flag*TEXTURE*IMAGE SET " + logoPath  + team.getTeamBadge() + " \0", print_writers);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Minis$Side" + WhichSide + "$Bowling$AllDataGrp$TeamNameGrp$img_Flag$img_Base02*TEXTURE*IMAGE SET " 
						+ base2Path + team.getTeamBadge() + " \0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Minis$Side" + WhichSide + "$Bowling$AllDataGrp$TeamNameGrp$img_Flag$img_Base01*TEXTURE*IMAGE SET " 
						+ base1Path + team.getTeamBadge() + " \0", print_writers);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Minis$Side" + WhichSide + "$Bowling$AllDataGrp$TeamNameGrp$headerBnd$img_Base2*TEXTURE*IMAGE SET " 
						+ base2Path + team.getTeamBadge() + " \0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Minis$Side" + WhichSide + "$Bowling$AllDataGrp$TeamNameGrp$HeaderText$img_Text2*TEXTURE*IMAGE SET " 
						+ text2Path + team.getTeamBadge() + " \0", print_writers);
				
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Minis$Side" + WhichSide + "$Bowling$Header$headerBnd$img_Header"
						+ "*TEXTURE*IMAGE SET IMAGE*/Default/Essentials/Textures/Color02\0", print_writers);
				for(int i=1;i<=14;i++) {
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Minis$Side" + WhichSide + "$Bowling$DataGrp$Row" + i + "$Players_Highlight$"
							+ "img_Highlight*TEXTURE*IMAGE SET IMAGE*/Default/Essentials/Textures/Color01\0", print_writers);
				}
				
				CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$Minis$Side" + WhichSide + "$Bowling$AllDataGrp$Header$Name$"
						+ "select_Language*FUNCTION*Omo*vis_con SET ", config, Constants.TG20, print_writers, foreignLanguageOmo);
				foreignLanguageData = CricketFunctions.AssembleMultiLanguageData(CricketUtil.PLAYER, CricketUtil.FULLNAME, multilanguagedata, player.getFull_name(), 
						"", null, 0, foreignLanguageDataList);
				CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$Minis$Side" + WhichSide + "$Bowling$AllDataGrp$Header$Name$English$"
						+ "txt_FirstName*GEOM*TEXT SET ", config, Constants.TG20, print_writers, foreignLanguageData);
				
//				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$minis$Side" + WhichSide 
//						+ "$Bowling$txt_FirstName*GEOM*TEXT SET " + player.getFull_name() + " \0", print_writers);
				
				
				for(HeadToHeadPlayer h2h : headToHead) {
					if(h2h.getPlayerId() == player.getPlayerId() && h2h.getTeam().getTeamName4().equalsIgnoreCase(team.getTeamName4())) {
						row_no++;
						MatchName = h2h.getMatchFileName();
						
//						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$minis$Side" + WhichSide 
//								+ "$Bowling$Row" + row_no + "$select_BallRowType$Players_Dehighlight$In_Out*FUNCTION*Omo*vis_con SET 0\0", print_writers);
						
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Minis$Side" + WhichSide 
								+ "$Bowling$DataGrp*FUNCTION*Grid*num_row SET " + row_no + " \0", print_writers);
						
//						print_writer.println("-1 RENDERER*TREE*$Main$Select$MiniBatting_Bwling$Mini$Bowling$AllDataGrp$CardAll$Data$Data$DataGrp$Row"
//										+ row_no + "$RowAnimation$Select_Star*FUNCTION*Omo*vis_con SET 0 \0");
						
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Minis$Side" + WhichSide 
								+ "$Bowling$Row" + row_no + "$select_BallRowType*FUNCTION*Omo*vis_con SET 1\0", print_writers);
						
						CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$Minis$Side" + WhichSide + "$Bowling$DataGrp$Row" + row_no + 
								"$Dehighlight$select_Language*FUNCTION*Omo*vis_con SET ", config, Constants.TG20, print_writers, foreignLanguageOmo);
						foreignLanguageDataList = CricketFunctions.AssembleMultiLanguageData(CricketUtil.DICTIONARY, "", multilanguagedata,"v",
							    "", null, 1,foreignLanguageDataList);
	    					foreignLanguageDataList = CricketFunctions.AssembleMultiLanguageData(CricketUtil.TEAM, CricketUtil.TEAMNAME_3, multilanguagedata,h2h.getOpponentTeam().getTeamName1().trim().toUpperCase(),
								    "", null, 2,foreignLanguageDataList);

						foreignLanguageData.add(CricketFunctions.MergeForeignLanguageDataListToSingleObject(foreignLanguageDataList));
						
						CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$Minis$Side" + WhichSide 
								+ "$Bowling$Row" + row_no + "$Dehighlight$English$txt_BowlerName*GEOM*TEXT SET ",config, Constants.BCCI, print_writers, foreignLanguageData);
						
//						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Minis$Side" + WhichSide 
//								+ "$Bowling$Row" + row_no + "$Dehighlight$txt_Name*GEOM*TEXT SET v " + h2h.getOpponentTeam().getTeamName3() + " \0", print_writers);
						
						if(h2h.getBallsBowled() == 0) {
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Minis$Side" + WhichSide 
									+ "$Bowling$Row" + row_no + "$Dehighlight$txt_Overs*GEOM*TEXT SET \0", print_writers);

							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Minis$Side" + WhichSide 
									+ "$Bowling$Row" + row_no + "$Dehighlight$txt_Figures*GEOM*TEXT SET DNB\0", print_writers);
							
						}else {
							
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Minis$Side" + WhichSide 
									+ "$Bowling$Row" + row_no + "$Dehighlight$txt_Figures*GEOM*TEXT SET " + h2h.getWickets() +"-"+h2h.getRunsConceded() + "\0", print_writers);
							
							if(h2h.getBallsBowled()%6 == 0) {
								CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Minis$Side" + WhichSide 
										+ "$Bowling$Row" + row_no + "$Dehighlight$txt_Overs*GEOM*TEXT SET " + (h2h.getBallsBowled()/6) + "\0", print_writers);
							}else {
								CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Minis$Side" + WhichSide 
										+ "$Bowling$Row" + row_no + "$Dehighlight$txt_Overs*GEOM*TEXT SET " + (h2h.getBallsBowled()/6)+"."+h2h.getBallsBowled()%6 + "\0", print_writers);
							}
						}						
						count = 0;
					}else if(h2h.getTeam().getTeamName4().equalsIgnoreCase(team.getTeamName4())) {
						if(count == 11) {
							row_no++;
							
//							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Minis$Side" + WhichSide 
//									+ "$Bowling$Row" + row_no + "$select_BallRowType$Players_Dehighlight$In_Out*FUNCTION*Omo*vis_con SET 0\0", print_writers);
							
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Minis$Side" + WhichSide 
									+ "$Bowling$DataGrp*FUNCTION*Grid*num_row SET " + row_no + " \0", print_writers);
							
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Minis$Side" + WhichSide 
									+ "$Bowling$Row" + row_no + "$select_BallRowType*FUNCTION*Omo*vis_con SET 1\0", print_writers);
							
							CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$Minis$Side" + WhichSide + "$Bowling$DataGrp$Row" + row_no + 
									"$Dehighlight$select_Language*FUNCTION*Omo*vis_con SET ", config, Constants.TG20, print_writers, foreignLanguageOmo);
							foreignLanguageDataList = CricketFunctions.AssembleMultiLanguageData(CricketUtil.DICTIONARY, "", multilanguagedata,"v",
								    "", null, 1,foreignLanguageDataList);
		    					foreignLanguageDataList = CricketFunctions.AssembleMultiLanguageData(CricketUtil.TEAM, CricketUtil.TEAMNAME_3, multilanguagedata,h2h.getOpponentTeam().getTeamName1().trim().toUpperCase(),
									    "", null, 2,foreignLanguageDataList);

							foreignLanguageData.add(CricketFunctions.MergeForeignLanguageDataListToSingleObject(foreignLanguageDataList));
							
							CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$Minis$Side" + WhichSide 
									+ "$Bowling$Row" + row_no + "$Dehighlight$English$txt_BowlerName*GEOM*TEXT SET ",config, Constants.BCCI, print_writers, foreignLanguageData);
							
//							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Minis$Side" + WhichSide 
//									+ "$Bowling$Row" + row_no + "$Dehighlight$txt_Name*GEOM*TEXT SET v " + h2h.getOpponentTeam().getTeamName3() + " \0", print_writers);
							
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Minis$Side" + WhichSide 
									+ "$Bowling$Row" + row_no + "$Dehighlight$txt_Overs*GEOM*TEXT SET \0", print_writers);

							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Minis$Side" + WhichSide 
									+ "$Bowling$Row" + row_no + "$Dehighlight$txt_Figures*GEOM*TEXT SET DNP\0", print_writers);
							
							count = 0;
						}else if(!MatchName.equalsIgnoreCase(h2h.getMatchFileName()) && count < 11) {
							MatchName = h2h.getMatchFileName();
							count = 1;
						}else {
							if(count==10) {
								count=0;
							}
							count++;
						}
					}
				}
				
				boolean playerIsInBoc = false;
				if(inning.getBowlingCard() != null) {
					for(BowlingCard boc : inning.getBowlingCard()) {
						if(boc.getPlayerId() == player.getPlayerId()) {
							playerIsInBoc = true;
							row_no++;
							
//							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Minis$Side" + WhichSide 
//									+ "$Bowling$Row" + row_no + "$select_BallRowType$Players_Highlight$In_Out*FUNCTION*Omo*vis_con SET 0\0", print_writers);
							
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Minis$Side" + WhichSide 
									+ "$Bowling$DataGrp*FUNCTION*Grid*num_row SET " + row_no + " \0", print_writers);
							
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Minis$Side" + WhichSide 
									+ "$Bowling$Row" + row_no + "$select_BallRowType*FUNCTION*Omo*vis_con SET 2\0", print_writers);
							
							CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$Minis$Side" + WhichSide + "$Bowling$DataGrp$Row" + row_no + 
									"$Highlight$select_Language*FUNCTION*Omo*vis_con SET ", config, Constants.TG20, print_writers, foreignLanguageOmo);
							foreignLanguageDataList = CricketFunctions.AssembleMultiLanguageData(CricketUtil.DICTIONARY, "", multilanguagedata,"v",
								    "", null, 1,foreignLanguageDataList);
		    					foreignLanguageDataList = CricketFunctions.AssembleMultiLanguageData(CricketUtil.TEAM, CricketUtil.TEAMNAME_3, multilanguagedata,inning.getBatting_team().getTeamName1().trim().toUpperCase(),
									    "", null, 2,foreignLanguageDataList);

							foreignLanguageData.add(CricketFunctions.MergeForeignLanguageDataListToSingleObject(foreignLanguageDataList));
							
							CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$Minis$Side" + WhichSide 
									+ "$Bowling$Row" + row_no + "$Highlight$English$txt_BowlerName*GEOM*TEXT SET ",config, Constants.BCCI, print_writers, foreignLanguageData);
							
//							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Minis$Side" + WhichSide 
//									+ "$Bowling$Row" + row_no + "$Highlight$txt_Name*GEOM*TEXT SET v " + inning.getBatting_team().getTeamName3() + " \0", print_writers);
							
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Minis$Side" + WhichSide 
									+ "$Bowling$Row" + row_no + "$Highlight$txt_Overs*GEOM*TEXT SET " + CricketFunctions.OverBalls(boc.getOvers(), boc.getBalls()) + " \0", print_writers);

							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Minis$Side" + WhichSide 
									+ "$Bowling$Row" + row_no + "$Highlight$txt_Figures*GEOM*TEXT SET " + boc.getWickets()+"-"+ boc.getRuns() + "\0", print_writers);
							
							break;
						}else {
							playerIsInBoc = false;
						}
					}
				}
				
				if(!playerIsInBoc) {
					row_no++;
//					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Minis$Side" + WhichSide 
//							+ "$Bowling$Row" + row_no + "$select_BallRowType$Players_Highlight$In_Out*FUNCTION*Omo*vis_con SET 0\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Minis$Side" + WhichSide 
							+ "$Bowling$DataGrp*FUNCTION*Grid*num_row SET " + row_no + " \0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Minis$Side" + WhichSide 
							+ "$Bowling$Row" + row_no + "$select_BallRowType*FUNCTION*Omo*vis_con SET 2\0", print_writers);
					
					CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$Minis$Side" + WhichSide + "$Bowling$DataGrp$Row" + row_no + 
							"$Highlight$select_Language*FUNCTION*Omo*vis_con SET ", config, Constants.TG20, print_writers, foreignLanguageOmo);
					foreignLanguageDataList = CricketFunctions.AssembleMultiLanguageData(CricketUtil.DICTIONARY, "", multilanguagedata,"v",
						    "", null, 1,foreignLanguageDataList);
    					foreignLanguageDataList = CricketFunctions.AssembleMultiLanguageData(CricketUtil.TEAM, CricketUtil.TEAMNAME_3, multilanguagedata,inning.getBatting_team().getTeamName1().trim().toUpperCase(),
							    "", null, 2,foreignLanguageDataList);

					foreignLanguageData.add(CricketFunctions.MergeForeignLanguageDataListToSingleObject(foreignLanguageDataList));
					
					CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$Minis$Side" + WhichSide 
							+ "$Bowling$Row" + row_no + "$Highlight$English$txt_BowlerName*GEOM*TEXT SET ",config, Constants.BCCI, print_writers, foreignLanguageData);
					
//					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Minis$Side" + WhichSide 
//							+ "$Bowling$Row" + row_no + "$Highlight$txt_Name*GEOM*TEXT SET v " + inning.getBatting_team().getTeamName3() + " \0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Minis$Side" + WhichSide 
							+ "$Bowling$Row" + row_no + "$Highlight$txt_Overs*GEOM*TEXT SET \0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Minis$Side" + WhichSide 
							+ "$Bowling$Row" + row_no + "$Highlight$txt_Figures*GEOM*TEXT SET DNP\0", print_writers);
					
					List<Player> plyrs = (matchAllData.getSetup().getHomeTeamId() == team.getTeamId() ? matchAllData.getSetup().getHomeSquad() : 
						matchAllData.getSetup().getAwaySquad());
					for(Player plyr : plyrs) {
						if(plyr.getPlayerId() == player.getPlayerId()) {
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Minis$Side" + WhichSide 
									+ "$Bowling$Row" + row_no + "$Highlight$fig_Runs*GEOM*TEXT SET DNB\0", print_writers);
							break;
						}
					}
				}
				break;	
			case "Alt_F7":
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Minis$Side" + WhichSide + "*FUNCTION*Omo*vis_con SET 2\0", print_writers);
				switch(config.getBroadcaster()) {
				case Constants.TG20:
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Minis$Side" + WhichSide + "$PointsTale$AllDataGrp$DataGrp"
							+ "*FUNCTION*Grid*num_row SET 9\0", print_writers);
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Minis$Side" + WhichSide + "$PointsTale$DataGrp$MiniPartRow1" + 
							"$Select_Row_Type*FUNCTION*Omo*vis_con SET 0\0", print_writers);
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Minis$Side" + WhichSide + "$PointsTale$img_Flag*TEXTURE*IMAGE SET " 
							+ logoPath  + "EVENT \0", print_writers);
					
					CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$Minis$Side" + WhichSide + "$PointsTale$Header$HeaderText$Name$"
							+ "select_Language*FUNCTION*Omo*vis_con SET ", config, Constants.TG20, print_writers, foreignLanguageOmo);
					foreignLanguageData = CricketFunctions.AssembleMultiLanguageData(CricketUtil.DICTIONARY, "", multilanguagedata, "POINTS TABLE", 
							"", null, 0, foreignLanguageDataList);
					CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$Minis$Side" + WhichSide + "$PointsTale$Header$HeaderText$Name$"
							+ "English$txt_FirstName*GEOM*TEXT SET ", config, Constants.TG20, print_writers, foreignLanguageData);
					
					CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$Minis$Side" + WhichSide + "$PointsTale$DataGrp$MiniPartRow1$Title$PointsData$"
							+ "select_Language*FUNCTION*Omo*vis_con SET ", config, Constants.TG20, print_writers, foreignLanguageOmo);
					
					foreignLanguageData = CricketFunctions.AssembleMultiLanguageData(CricketUtil.DICTIONARY, "", multilanguagedata, "P", "", null, 0, foreignLanguageDataList);
					CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$Minis$Side" + WhichSide + "$PointsTale$DataGrp$MiniPartRow1$Title$PointsData$"
							+ "English$fig_Played*GEOM*TEXT SET ", config, Constants.TG20, print_writers, foreignLanguageData);
					foreignLanguageData = CricketFunctions.AssembleMultiLanguageData(CricketUtil.DICTIONARY, "", multilanguagedata, "W", "", null, 0, foreignLanguageDataList);
					CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$Minis$Side" + WhichSide + "$PointsTale$DataGrp$MiniPartRow1$Title$PointsData$"
							+ "English$fig_Won*GEOM*TEXT SET ", config, Constants.TG20, print_writers, foreignLanguageData);
					foreignLanguageData = CricketFunctions.AssembleMultiLanguageData(CricketUtil.DICTIONARY, "", multilanguagedata, "L", "", null, 0, foreignLanguageDataList);
					CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$Minis$Side" + WhichSide + "$PointsTale$DataGrp$MiniPartRow1$Title$PointsData$"
							+ "English$fig_Lost*GEOM*TEXT SET ", config, Constants.TG20, print_writers, foreignLanguageData);
					foreignLanguageData = CricketFunctions.AssembleMultiLanguageData(CricketUtil.DICTIONARY, "", multilanguagedata, "PTS", "", null, 0, foreignLanguageDataList);
					CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$Minis$Side" + WhichSide + "$PointsTale$DataGrp$MiniPartRow1$Title$PointsData$"
							+ "English$fig_Points*GEOM*TEXT SET ", config, Constants.TG20, print_writers, foreignLanguageData);
					foreignLanguageData = CricketFunctions.AssembleMultiLanguageData(CricketUtil.DICTIONARY, "", multilanguagedata, "NRR", "", null, 0, foreignLanguageDataList);
					CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$Minis$Side" + WhichSide + "$PointsTale$DataGrp$MiniPartRow1$Title$PointsData$"
							+ "English$fig_NRR*GEOM*TEXT SET ", config, Constants.TG20, print_writers, foreignLanguageData);
		
					rowId = 1;
					for(int i=0; i<=leagueTable.getLeagueTeams().size()-1;i++) {
						rowId++;
						
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Minis$Side" + WhichSide + "$PointsTale$AllDataGrp$DataGrp"
								+ "*FUNCTION*Grid*num_row SET " + rowId + "\0", print_writers);
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Minis$Side" + WhichSide + "$PointsTale$AllDataGrp$DataGrp$MiniPartRow"
								+ rowId + "*ACTIVE SET 1\0", print_writers);
						
						if(matchAllData.getSetup().getHomeTeam().getTeamName4().equalsIgnoreCase(leagueTable.getLeagueTeams().get(i).getTeamName())  
								|| matchAllData.getSetup().getAwayTeam().getTeamName4().equalsIgnoreCase(leagueTable.getLeagueTeams().get(i).getTeamName())) {
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Minis$Side" + WhichSide + "$PointsTale$DataGrp$MiniPartRow" + rowId + 
									"$Select_Row_Type*FUNCTION*Omo*vis_con SET 2\0", print_writers);
							containerName = "$Highlight";
						}else {
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Minis$Side" + WhichSide + "$PointsTale$DataGrp$MiniPartRow" + rowId + 
									"$Select_Row_Type*FUNCTION*Omo*vis_con SET 1\0", print_writers);
							containerName = "$Dehighlight";
						}
						
						if(leagueTable.getLeagueTeams().get(i).getQualifiedStatus().trim().equalsIgnoreCase("")) {
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Minis$Side" + WhichSide + "$PointsTale$DataGrp$MiniPartRow" + rowId + 
									containerName + "$txt_Rank*GEOM*TEXT SET " + (rowId - 1) + "\0", print_writers);
						}else {
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Minis$Side" + WhichSide + "$PointsTale$DataGrp$MiniPartRow" + rowId + 
									containerName + "$txt_Rank*GEOM*TEXT SET Q\0", print_writers);
						}
						
						for(Team team : Teams) {
							if(team.getTeamName4().equalsIgnoreCase(leagueTable.getLeagueTeams().get(i).getTeamName())) {
								CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$Minis$Side" + WhichSide + "$PointsTale$DataGrp$MiniPartRow" + rowId + 
										containerName + "$Name$select_Language*FUNCTION*Omo*vis_con SET ", config, Constants.TG20, print_writers, foreignLanguageOmo);
								foreignLanguageData = CricketFunctions.AssembleMultiLanguageData(CricketUtil.TEAM, CricketUtil.TEAMNAME_4, multilanguagedata, team.getTeamName1(), "", 
										null, 0, foreignLanguageDataList);
								CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$Minis$Side" + WhichSide + "$PointsTale$DataGrp$MiniPartRow" + rowId + 
										containerName + "$Name$English$txt_Name*GEOM*TEXT SET ", config, Constants.TG20, print_writers, foreignLanguageData);
								break;
							}
						}
						
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Minis$Side" + WhichSide + "$PointsTale$DataGrp$MiniPartRow" + rowId + containerName 
								+ "$fig_Played*GEOM*TEXT SET " + leagueTable.getLeagueTeams().get(i).getPlayed() + "\0", print_writers);
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Minis$Side" + WhichSide + "$PointsTale$DataGrp$MiniPartRow" + rowId + containerName 
								+ "$fig_Won*GEOM*TEXT SET " + leagueTable.getLeagueTeams().get(i).getWon() + "\0", print_writers);
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Minis$Side" + WhichSide + "$PointsTale$DataGrp$MiniPartRow" + rowId + containerName 
								+ "$fig_Lost*GEOM*TEXT SET " + leagueTable.getLeagueTeams().get(i).getLost() + "\0", print_writers);
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Minis$Side" + WhichSide + "$PointsTale$DataGrp$MiniPartRow" + rowId + containerName 
								+ "$fig_Points*GEOM*TEXT SET " + leagueTable.getLeagueTeams().get(i).getPoints() + "\0", print_writers);
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Minis$Side" + WhichSide + "$PointsTale$DataGrp$MiniPartRow" + rowId + containerName 
								+ "$fig_NRR*GEOM*TEXT SET " + String.format("%.2f", leagueTable.getLeagueTeams().get(i).getNetRunRate()) + "\0", print_writers);
					}
					break;
				default:
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Minis$Side" + WhichSide + "$PointsTale$AllDataGrp$DataGrp"
							+ "*FUNCTION*Grid*num_row SET 4\0", print_writers);
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Minis$Side" + WhichSide + "$PointsTale$DataGrp$Row1" + 
							"$Select_Row_Type*FUNCTION*Omo*vis_con SET 0\0", print_writers);
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Minis$Side" + WhichSide + "$PointsTale$img_Flag*TEXTURE*IMAGE SET " + logoPath  + "EVENT \0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Minis$Side" + WhichSide + "$PointsTale$img_Base02*TEXTURE*IMAGE SET " + base2Path  + "EVENT \0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Minis$Side" + WhichSide + "$PointsTale$img_Base01*TEXTURE*IMAGE SET " + base1Path  + "EVENT \0", print_writers);
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Minis$Side" + WhichSide + "$PointsTale$Header$txt_FirstName"
							+ "*GEOM*TEXT SET POINTS TABLE\0", print_writers);
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Minis$Side" + WhichSide + "$PointsTale$DataGrp$Row1$Players_Dehighlight$txt_Name"
							+ "*GEOM*TEXT SET \0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Minis$Side" + WhichSide + "$PointsTale$DataGrp$Row1$Players_Dehighlight$fig_Points"
							+ "*GEOM*TEXT SET PTS\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Minis$Side" + WhichSide + "$PointsTale$DataGrp$Row1$Players_Dehighlight$fig_Lost"
							+ "*GEOM*TEXT SET L\0", print_writers);
									
					rowId = 1;
					for(int i=0; i<=leagueTable.getLeagueTeams().size()-1;i++) {
						rowId++;
						
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Minis$Side" + WhichSide + "$PointsTale$AllDataGrp$DataGrp"
								+ "*FUNCTION*Grid*num_row SET " + rowId + "\0", print_writers);
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Minis$Side" + WhichSide + "$PointsTale$AllDataGrp$DataGrp$Row"
								+ rowId + "*ACTIVE SET 1\0", print_writers);
						
						if(matchAllData.getSetup().getHomeTeam().getTeamBadge().equalsIgnoreCase(leagueTable.getLeagueTeams().get(i).getTeamName())  
								|| matchAllData.getSetup().getAwayTeam().getTeamBadge().equalsIgnoreCase(leagueTable.getLeagueTeams().get(i).getTeamName())) {
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Minis$Side" + WhichSide + "$PointsTale$DataGrp$Row" + rowId + 
									"$Select_Row_Type*FUNCTION*Omo*vis_con SET 1\0", print_writers);
							containerName = "$Players_Highlight";
						}else {
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Minis$Side" + WhichSide + "$PointsTale$DataGrp$Row" + rowId + 
									"$Select_Row_Type*FUNCTION*Omo*vis_con SET 0\0", print_writers);
							containerName = "$Players_Dehighlight";
						}
						
						if(leagueTable.getLeagueTeams().get(i).getQualifiedStatus().trim().equalsIgnoreCase("")) {
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Minis$Side" + WhichSide + "$PointsTale$DataGrp$Row" + rowId + 
									containerName + "$txt_Rank*GEOM*TEXT SET " + (rowId - 1) + "\0", print_writers);
						}else {
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Minis$Side" + WhichSide + "$PointsTale$DataGrp$Row" + rowId + 
									containerName + "$txt_Rank*GEOM*TEXT SET Q\0", print_writers);
						}
						
						for(Team team : Teams) {
							if(team.getTeamName4().equalsIgnoreCase(leagueTable.getLeagueTeams().get(i).getTeamName())) {
								CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Minis$Side" + WhichSide + "$PointsTale$DataGrp$Row" + rowId 
										+ containerName + "$txt_Name*GEOM*TEXT SET " + team.getTeamName1() + "\0", print_writers);
							}
						}
						
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Minis$Side" + WhichSide + "$PointsTale$DataGrp$Row" + rowId + containerName 
								+ "$fig_Played*GEOM*TEXT SET " + leagueTable.getLeagueTeams().get(i).getPlayed() + "\0", print_writers);
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Minis$Side" + WhichSide + "$PointsTale$DataGrp$Row" + rowId + containerName 
								+ "$fig_Won*GEOM*TEXT SET " + leagueTable.getLeagueTeams().get(i).getWon() + "\0", print_writers);
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Minis$Side" + WhichSide + "$PointsTale$DataGrp$Row" + rowId + containerName 
								+ "$fig_Lost*GEOM*TEXT SET " + leagueTable.getLeagueTeams().get(i).getLost() + "\0", print_writers);
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Minis$Side" + WhichSide + "$PointsTale$DataGrp$Row" + rowId + containerName 
								+ "$fig_Points*GEOM*TEXT SET " + leagueTable.getLeagueTeams().get(i).getPoints() + "\0", print_writers);
					}
					break;
				}
				break;
			}
			break;
		}
		
			
		return Constants.OK;
	}	
}