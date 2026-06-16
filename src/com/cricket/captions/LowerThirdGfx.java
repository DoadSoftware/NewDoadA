package com.cricket.captions;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.Month;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import com.cricket.containers.Infobar;
import com.cricket.containers.L3Griff;
import com.cricket.containers.LowerThird;
import com.cricket.model.BatBallGriff;
import com.cricket.model.BattingCard;
import com.cricket.model.BestStats;
import com.cricket.model.BowlingCard;
import com.cricket.model.Configuration;
import com.cricket.model.DuckWorthLewis;
import com.cricket.model.Event;
import com.cricket.model.Fixture;
import com.cricket.model.Ground;
import com.cricket.model.HeadToHeadPlayer;
import com.cricket.model.Inning;
import com.cricket.model.Match;
import com.cricket.model.MatchAllData;
import com.cricket.model.MatchStats.VariousStats;
import com.cricket.model.NameSuper;
import com.cricket.model.OverByOverData;
import com.cricket.model.POTT;
import com.cricket.model.Partnership;
import com.cricket.model.Player;
import com.cricket.model.PowerPlays;
import com.cricket.model.Setup;
import com.cricket.model.Speed;
import com.cricket.model.Staff;
import com.cricket.model.Statistics;
import com.cricket.model.StatsType;
import com.cricket.model.Team;
import com.cricket.model.Tournament;
import com.cricket.model.VariousText;
import com.cricket.model.Weather;
import com.cricket.service.CricketService;
import com.cricket.util.CricketFunctions;
import com.cricket.util.CricketUtil;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class LowerThirdGfx 
{
	public int FirstPlayerId;
	public int SecondPlayerId;
	public String WhichProfile, status = "",logo_data = "",matchname = "";
	public String match_name="",newDate = "",date_data = "",text = "",matchday = "";
	
	public Animation this_animation = new Animation();
	
	@JsonIgnore
	public List<PrintWriter> print_writers; 
	public Configuration config;
	public List<Statistics> statistics;
	public List<StatsType> statsTypes;
	public List<Tournament> tournaments;
	public List<BestStats> tapeballs;
	public List<MatchAllData> tournament_matches;
	public List<NameSuper> nameSupers;
	public List<Team> Teams;
	public List<Partnership> partnershipList;
	public List<Ground> Grounds;
	public List<Staff> Staff;
	public List<Player> Players;
	public List<POTT> Potts;
	public List<VariousText> VariousText;
	public List<HeadToHeadPlayer> headToHead;
	public List<Tournament> past_tournament_stats;
	public List<Fixture> fixTures;
	public List<OverByOverData> overByOverData;
	public List<OverByOverData> manhattan = new ArrayList<OverByOverData>();
	public Weather weatherData;
	public List<Integer> PlayerId, PlayerIdIn;
	
	public BattingCard battingCard;
	public Partnership partnership;
	public BowlingCard bowlingCard;
	public MatchAllData match;
	public Inning inning;
	public Inning inning2;
	public Player player;
	public Statistics stat;
	public StatsType statsType;
	public Tournament tournament;
	public BestStats tapeball;
	public LowerThird lowerThird;
	public Staff staff;
	public NameSuper namesuper;
	public Fixture fixture;
	public Ground ground;
	public Team team;
	public L3Griff l3griff;
	public VariousText variousText;
	
	@JsonIgnore
	public CricketService cricketService;
	public static List<VariousStats> PlayerList;
	public VariousStats VariousStats;
	
	public Statistics statODI;
	public Statistics statDT20;
	public Statistics statTEST;
	public StatsType statsTypeDT20;
	public StatsType statsTypeODI;
	public StatsType statsTypeTEST;
	
	public List<DuckWorthLewis> dls;
	public List<BattingCard> battingCardList = new ArrayList<BattingCard>();
	public List<BowlingCard> bowlingCardList = new ArrayList<BowlingCard>();
	public List<BestStats> top_tape = new ArrayList<BestStats>();
	public List<BestStats> top_batsman_beststats = new ArrayList<BestStats>();
	public List<BestStats> top_bowler_beststats = new ArrayList<BestStats>();
	public List<String> this_data_str = new ArrayList<String>();
	public List<BatBallGriff> griff = new ArrayList<BatBallGriff>();
	public List<Fixture> FixturesList = new ArrayList<Fixture>();
	public List<Player> PlayersList = new ArrayList<Player>();
	
	public List<PowerPlays> powerplayData = new ArrayList<PowerPlays>();
	public List<BestStats> pastdata = new ArrayList<BestStats>();
	public List<Tournament> addPastDataToCurr = new ArrayList<Tournament>();
	public List<Speed> Speed = new ArrayList<Speed>();
	List<Double> numbers = new ArrayList<>();
	public Infobar infobar = new Infobar();
	
	public int impactOmo=0;
	public boolean impact = false;
	public boolean photo = false;
	public boolean prev_impact = false;
	
	String containerName = "",ltWhichContainer = "",surName = "", teamName = "", variousData = "",logo_name = "" , 
			color_name = "",age = "", teamNameAsCity = "", phaseWiseScore = "",typeData = "",logoCategory = "";
	int subline = 0;
	
	public boolean isImpact() {
		return impact;
	}

	public void setImpact(boolean impact) {
		this.impact = impact;
	}

	public boolean isPrev_impact() {
		return prev_impact;
	}

	public void setPrev_impact(boolean prev_impact) {
		this.prev_impact = prev_impact;
	}

	public LowerThirdGfx() {
		super();
	}
	
	public LowerThirdGfx(List<PrintWriter> print_writers, Configuration config, List<Statistics> statistics, List<StatsType> statsTypes, 
			List<MatchAllData> tournament_matches, List<NameSuper> nameSupers, List<Team> Teams, List<Ground> Grounds, 
			List<Tournament> tournaments,List<BestStats> tapeballs,List<DuckWorthLewis> dls, List<Staff> staff, List<Player> players, List<POTT> pott,
			List<VariousText> VariousText, List<HeadToHeadPlayer> headToHead, List<Tournament> past_tournament_stats, CricketService cricketService,List<Fixture> fixTures) {
		super();
		this.print_writers = print_writers;
		this.config = config;
		this.statistics = statistics;
		this.statsTypes = statsTypes;
		this.tournament_matches = tournament_matches;
		this.nameSupers = nameSupers;
		this.Teams = Teams;
		this.Grounds = Grounds;
		this.tournaments = tournaments;
		this.tapeballs = tapeballs;
		this.dls = dls;
		this.Staff = staff;
		this.Players = players;
		this.Potts = pott;
		this.VariousText = VariousText;
		this.headToHead = headToHead;
		this.past_tournament_stats = past_tournament_stats;
		this.cricketService = cricketService;
		this.fixTures = fixTures;
	}
	
	public String toOrdinalWords(int number) {
		
		String[] WORDS = {"", "First", "Second", "Third", "Fourth", "Fifth","Sixth", "Seventh", "Eighth", "Ninth", "Tenth","Eleventh", 
				"Twelfth", "Thirteenth", "Fourteenth", "Fifteenth","Sixteenth", "Seventeenth", "Eighteenth", "Nineteenth", "Twentieth"};
		String[] TENS = {"", "", "Twenty", "Thirty", "Forty", "Fifty","Sixty", "Seventy", "Eighty", "Ninety"};
		
        if (number < WORDS.length) {
            return WORDS[number];
        } else if (number < 100) {
            int tens = number / 10;
            int ones = number % 10;
            String tensWord = TENS[tens];
            String onesWord = (ones > 0) ? WORDS[ones] : "";
            return tensWord + (onesWord.isEmpty() ? "ieth" : " " + onesWord);
        } else {
            return number + "th"; // fallback for bigger numbers
        }
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
	
	public String PopulateBatBallGriff(String whatToProcess,int WhichSide,MatchAllData matchAllData) throws InterruptedException, StreamReadException, DatabindException, FileNotFoundException, IOException
	{
		if (matchAllData == null || matchAllData.getMatch() == null || matchAllData.getMatch().getInning() == null) {
			return status;
		} else {
			
			inning = matchAllData.getMatch().getInning().stream().filter(inn -> inn.getInningNumber() == Integer.valueOf(whatToProcess.split(",")[1])).findAny().orElse(null);
			if(inning == null) {
				return "populateBatThisMatch: Current Inning NOT found in this match";
			}
			player = CricketFunctions.getPlayerFromMatchData(Integer.valueOf(whatToProcess.split(",")[2]), matchAllData);
			if(whatToProcess.split(",")[0].equalsIgnoreCase("Alt_F1")) {
				team = inning.getBatting_team();
				griff = CricketFunctions.getBatBallGriff(player ,CricketUtil.BATSMAN,player.getPlayerId(), team, headToHead, matchAllData);
				
				if(griff == null) {
					return "griff is Null";
				}
				
				if(griff.size() > 7) {
					return "griff size is greater than 7 we cannot display data";
				}
				
				this_data_str = new ArrayList<String>();
				
				for(int i=0;i<=griff.size()-1;i++) {
					if(griff.get(i).getMatchNumber().contains("MATCH")) {
						this_data_str.add(toOrdinalWords(Integer.valueOf(griff.get(i).getMatchNumber().replace("MATCH", "").trim())));
					}else {
						this_data_str.add(griff.get(i).getMatchNumber());
					}
				}
				
				setGriff(griff.size(), whatToProcess, WhichSide, config);
				
			}else if(whatToProcess.split(",")[0].equalsIgnoreCase("Alt_F2")) {
				team = inning.getBowling_team();
				griff = CricketFunctions.getBatBallGriff(player , CricketUtil.BOWLER,player.getPlayerId(), team, headToHead, matchAllData);
				
				if(griff == null) {
					return "griff is Null";
				}
			
				if(griff.size() > 7) {
					return "griff size is greater than 7 we cannot display data";
				}
				
				this_data_str = new ArrayList<String>();
				
				for(int i=0;i<=griff.size()-1;i++) {
					if(griff.get(i).getMatchNumber().contains("MATCH")) {
						this_data_str.add(toOrdinalWords(Integer.valueOf(griff.get(i).getMatchNumber().replace("MATCH", "").trim())));
					}else {
						this_data_str.add(griff.get(i).getMatchNumber());
					}
				}
				
				setGriff(griff.size(), whatToProcess, WhichSide, config);
			}
		}
		
		typeData = matchAllData.getSetup().getTournament();
		
		status = PopulateL3rdHeader(whatToProcess.split(",")[0],WhichSide);
		if(status == Constants.OK) {
			PopulateL3rdBaseColor(whatToProcess.split(",")[0], WhichSide);
			HideAndShowGriffSubStrapContainers(WhichSide);
			setPositionOfLT(whatToProcess,WhichSide,config,l3griff.getNumberOfSubLines());
			return PopulateL3rdBody(WhichSide, whatToProcess.split(",")[0]);
		} else {
			return status;
		}
	}
	public String populateBowlingStyle(String whatToProcess,int WhichSide,MatchAllData matchAllData) throws InterruptedException
	{
		
		player = CricketFunctions.getPlayerFromMatchData(Integer.valueOf(whatToProcess.split(",")[2]), matchAllData);
		
		inning = matchAllData.getMatch().getInning().stream().filter(inn -> inn.getInningNumber() == Integer.valueOf(whatToProcess.split(",")[1])).findAny().orElse(null);
		if(inning == null) {
			return "populateBowlingStyle: Current Inning NOT found in this match";
		}
		
		if(matchAllData.getSetup().getHomeTeamId() == player.getTeamId()) {
			team = matchAllData.getSetup().getHomeTeam();
		} else if(matchAllData.getSetup().getAwayTeamId() == player.getTeamId()) {
			team = matchAllData.getSetup().getAwayTeam();
		}
		
		if(player.getSurname() == null) {
			surName = "";
		}else {
			surName = player.getSurname();
		}
		
		if(whatToProcess.contains("with_photo")) {
			photo = true;
		}else {
			photo = false;
		}
		
		switch (config.getBroadcaster().toUpperCase()) {
		case Constants.TRI_SERIES:  case Constants.MT20: case Constants.BAN_AFG_SERIES: case Constants.ACC:
			lowerThird = new LowerThird(team.getTeamName3(), player.getFirstname(), surName,"", null, null,
					1,"",team.getTeamBadge(),null,null,new String[]{CricketFunctions.getbowlingstyle(player.getBowlingStyle()).toUpperCase()},
					new String[]{whatToProcess.split(",")[3]},null);
			break;
		}
		
		if(CricketFunctions.isImpactPlayer(matchAllData.getEventFile().getEvents(), inning.getInningNumber(), 
				Integer.valueOf(whatToProcess.split(",")[2])).equalsIgnoreCase(CricketUtil.YES)) {
			impactOmo = 1;
			setImpact(true);
			setPrev_impact(true);
		} else {
			impactOmo =0;
			setImpact(false);
		}
		
		status = PopulateL3rdHeader(whatToProcess.split(",")[0],WhichSide);
		if(status == Constants.OK) {
			switch (config.getBroadcaster().toUpperCase()) {
			case Constants.TRI_SERIES:  case Constants.MT20:
				PopulateL3rdBaseColor(whatToProcess.split(",")[0], WhichSide);
				HideAndShowL3rdSubStrapContainers(WhichSide);
				setPositionOfLT(whatToProcess,WhichSide,config,lowerThird.getNumberOfSubLines());
				break;
			}	
			return PopulateL3rdBody(WhichSide,whatToProcess.split(",")[0]);
		} else {
			return status;
		}
		
	}
	
	public String populateBattingStyle(String whatToProcess,int WhichSide,MatchAllData matchAllData) throws InterruptedException
	{
		if (matchAllData == null || matchAllData.getMatch() == null || matchAllData.getMatch().getInning() == null) {
			return status;
		} else {
			inning = matchAllData.getMatch().getInning().stream().filter(inn -> inn.getInningNumber() == Integer.valueOf(whatToProcess.split(",")[1])).findAny().orElse(null);
			if(inning == null) {
				return "populateBattingStyle: Current Inning NOT found in this match";
			}
			player = Players.stream().filter(bc -> bc.getPlayerId() == Integer.valueOf(whatToProcess.split(",")[2])).findAny().orElse(null);
			if(player == null) {
				return status;
			}
		}
		if(whatToProcess.contains("with_photo")) {
			photo = true;
		}else {
			photo = false;
		}
		if(config.getCategory().equalsIgnoreCase("MEN")) {
			logoCategory = "M";
		}else if(config.getCategory().equalsIgnoreCase("WOMEN")) {
			logoCategory = "W";
		}else {
			logoCategory = "";
		}
		if(CricketFunctions.isImpactPlayer(matchAllData.getEventFile().getEvents(), inning.getInningNumber(), 
				Integer.valueOf(whatToProcess.split(",")[2])).equalsIgnoreCase(CricketUtil.YES)) {
			impactOmo = 1;
			setImpact(true);
			setPrev_impact(true);
		} else {
			impactOmo =0;
			setImpact(false);
		}
		
		if(player.getSurname() == null) {
			surName = "";
		}else {
			surName = player.getSurname();
		}
		
		switch(config.getBroadcaster().toUpperCase()) {
		case Constants.TRI_SERIES:  case Constants.MT20: case Constants.BAN_AFG_SERIES: case Constants.ACC:
			lowerThird = new LowerThird(inning.getBatting_team().getTeamName3(), player.getFirstname(), surName,"", null, null, 1,"",
					inning.getBatting_team().getTeamBadge(),null,null,new String[]{CricketFunctions.getbattingstyle(player.getBattingStyle(),
						CricketUtil.FULL, true, false).toUpperCase()},null,null);
			break;
		}
		
		status = PopulateL3rdHeader(whatToProcess.split(",")[0],WhichSide);
		if(status == Constants.OK) {
			switch(config.getBroadcaster().toUpperCase()) {
			case Constants.TRI_SERIES:  case Constants.MT20:
				PopulateL3rdBaseColor(whatToProcess.split(",")[0], WhichSide);
				HideAndShowL3rdSubStrapContainers(WhichSide);
				setPositionOfLT(whatToProcess,WhichSide,config,lowerThird.getNumberOfSubLines());
				break;
			}

			return PopulateL3rdBody(WhichSide,whatToProcess.split(",")[0]);
		} else {
			return status;
		}
		
	}
	
	public String populateQuickHowOut(String whatToProcess,int WhichSide,MatchAllData matchAllData) throws InterruptedException
	{
		String striktRate = "",howOut = "",duration ="";
		
		if(whatToProcess.contains("with_photo")) {
			photo = true;
		}else {
			photo = false;
		}
		if (matchAllData == null || matchAllData.getMatch() == null || matchAllData.getMatch().getInning() == null) {
			return status;
		} else {
			
			inning = matchAllData.getMatch().getInning().stream().filter(inn -> 
			inn.getIsCurrentInning().equalsIgnoreCase(CricketUtil.YES)).findAny().orElse(null);

			if(inning == null) {
				return "populateVizInfobarMiddleSection: Inning returned is NULL";
			}
			
			if(inning.getFallsOfWickets() == null && inning.getFallsOfWickets().isEmpty()) {
				return "populateVizInfobarMiddleSection: FoW returned is EMPTY";
			}
			
			battingCardList.add(inning.getBattingCard().stream().filter(bc -> bc.getPlayerId() == 
				inning.getFallsOfWickets().get(inning.getFallsOfWickets().size() - 1).getFowPlayerID()).findAny().orElse(null));
	
			if(battingCardList.get(battingCardList.size()-1) == null) {
				return "populateVizInfobarLeftBottom: Last wicket returned is invalid";
			}
		}
		
		if(battingCardList.get(battingCardList.size()-1).getStrikeRate() != null) {
			if(battingCardList.get(battingCardList.size()-1).getStrikeRate().equalsIgnoreCase("0.0")) {
				striktRate = "-";
			}else {
				striktRate = String.valueOf(CricketFunctions.generateStrikeRate(battingCardList.get(battingCardList.size()-1).getRuns(), battingCardList.get(battingCardList.size()-1).getBalls(), 0));
			}
		}else {
			striktRate = "-";
		}
		
		
		if(battingCardList.get(battingCardList.size()-1).getPlayer().getSurname() == null) {
			surName = "";
		}else {
			surName = battingCardList.get(battingCardList.size()-1).getPlayer().getSurname();
		}
		
		if(battingCardList.get(battingCardList.size()-1).getHowOut().equalsIgnoreCase(CricketUtil.RUN_OUT)) {
			if(battingCardList.get(battingCardList.size()-1).getWasHowOutFielderSubstitute() != null && 
					battingCardList.get(battingCardList.size()-1).getWasHowOutFielderSubstitute().equalsIgnoreCase(CricketUtil.YES)) {
				howOut = "run out " + "(sub - " + battingCardList.get(battingCardList.size()-1).getHowOutFielder().getTicker_name() + ")";
			} else if(battingCardList.get(battingCardList.size()-1).getHowOutFielder() == null){
				howOut = "run out (sub)";
			}else {
				howOut = "run out (" + battingCardList.get(battingCardList.size()-1).getHowOutFielder().getTicker_name() + ")";
			}
		}else if(battingCardList.get(battingCardList.size()-1).getHowOut().equalsIgnoreCase(CricketUtil.STUMPED)) {
			if(battingCardList.get(battingCardList.size()-1).getWasHowOutFielderSubstitute() != null && 
					battingCardList.get(battingCardList.size()-1).getWasHowOutFielderSubstitute().equalsIgnoreCase(CricketUtil.YES)) {
				howOut = "st " +  "(sub -  " + battingCardList.get(battingCardList.size()-1).getHowOutFielder().getTicker_name() + ")  b " + 
						battingCardList.get(battingCardList.size()-1).getHowOutBowler().getTicker_name();
			} else if(battingCardList.get(battingCardList.size()-1).getHowOutFielder() == null){
				howOut = "st (sub)";
			}else {
				howOut = "st " + battingCardList.get(battingCardList.size()-1).getHowOutFielder().getTicker_name() + "  b " + 
						battingCardList.get(battingCardList.size()-1).getHowOutBowler().getTicker_name();
			}
		}
		else if(battingCardList.get(battingCardList.size()-1).getHowOut().equalsIgnoreCase(CricketUtil.CAUGHT)) {
			if(battingCardList.get(battingCardList.size()-1).getWasHowOutFielderSubstitute() != null && 
					battingCardList.get(battingCardList.size()-1).getWasHowOutFielderSubstitute().equalsIgnoreCase(CricketUtil.YES)) {
				howOut = "c " +  "(sub -  " + battingCardList.get(battingCardList.size()-1).getHowOutFielder().getTicker_name() + ")  b " + 
						battingCardList.get(battingCardList.size()-1).getHowOutBowler().getTicker_name();
			} else if(battingCardList.get(battingCardList.size()-1).getHowOutFielder() == null){
				howOut = "c (sub)";
			}else {
				howOut = "c " + battingCardList.get(battingCardList.size()-1).getHowOutFielder().getTicker_name() + "  b " + 
						battingCardList.get(battingCardList.size()-1).getHowOutBowler().getTicker_name();
			}
		}else {
			if(!battingCardList.get(battingCardList.size()-1).getHowOutPartOne().isEmpty()) {
				howOut = battingCardList.get(battingCardList.size()-1).getHowOutPartOne();
			}
			
			if(!battingCardList.get(battingCardList.size()-1).getHowOutPartTwo().isEmpty()) {
				if(!howOut.trim().isEmpty()) {
					howOut = howOut + "  " + battingCardList.get(battingCardList.size()-1).getHowOutPartTwo();
				}else {
					howOut = battingCardList.get(battingCardList.size()-1).getHowOutPartTwo();
				}
			}
		}
		
		if(CricketFunctions.isImpactPlayer(matchAllData.getEventFile().getEvents(), inning.getInningNumber(), 
				inning.getFallsOfWickets().get(inning.getFallsOfWickets().size() - 1).getFowPlayerID()).equalsIgnoreCase(CricketUtil.YES)) {
			impactOmo = 1;
			setImpact(true);
		} else {
			impactOmo =0;
			setImpact(false);
		}
		
		String[] Count = CricketFunctions.getScoreTypeData(CricketUtil.BATSMAN,matchAllData, inning.getInningNumber(), 
				battingCardList.get(battingCardList.size()-1).getPlayerId(),"-", matchAllData.getEventFile().getEvents()).split("-");
		
		switch (config.getBroadcaster().toUpperCase()) {
		case Constants.TRI_SERIES:  case Constants.MT20: case Constants.BAN_AFG_SERIES: case Constants.ACC:
			lowerThird = new LowerThird("", battingCardList.get(battingCardList.size()-1).getPlayer().getFirstname(), surName,"", 
					String.valueOf(battingCardList.get(battingCardList.size()-1).getRuns()), 
					String.valueOf(battingCardList.get(battingCardList.size()-1).getBalls()),2,"",inning.getBatting_team().getTeamBadge(),
					null,null,new String[]{howOut,String.valueOf(battingCardList.get(battingCardList.size()-1).getFours()),
					String.valueOf(battingCardList.get(battingCardList.size()-1).getSixes()),Count[0],striktRate},
					new String[]{String.valueOf((battingCardList.get(battingCardList.size()-1).getDuration()/60))},null);
			break;
		}
		
		status = PopulateL3rdHeader(whatToProcess.split(",")[0],WhichSide);
		if(status == Constants.OK) {
			switch (config.getBroadcaster().toUpperCase()) {
			case Constants.TRI_SERIES:  case Constants.MT20:
				PopulateL3rdBaseColor(whatToProcess.split(",")[0], WhichSide);
				HideAndShowL3rdSubStrapContainers(WhichSide);
				setPositionOfLT(whatToProcess,WhichSide,config,lowerThird.getNumberOfSubLines());
				break;
			}

			return PopulateL3rdBody(WhichSide,whatToProcess.split(",")[0]);
		} else {
			return status;
		}
	}
	
	public String populateHowOutWithOutFielder(String whatToProcess,int WhichSide,MatchAllData matchAllData) throws InterruptedException
	{
		String striktRate = "";
		
		if (matchAllData == null || matchAllData.getMatch() == null || matchAllData.getMatch().getInning() == null) {
			return status;
		} else {
			inning = matchAllData.getMatch().getInning().stream().filter(inn -> inn.getInningNumber() == Integer.valueOf(whatToProcess.split(",")[1])).findAny().orElse(null);
			
			if(inning == null) {
				return "populateHowOutWithOutFielder: Inning is Not Found";
			}
			battingCard = inning.getBattingCard().stream().filter(bc -> bc.getPlayerId() == Integer.valueOf(whatToProcess.split(",")[2])).findAny().orElse(null);
			if(battingCard == null) {
				return status;
			}
		}
		if(whatToProcess.contains("with_photo")) {
			photo = true;
		}else {
			photo = false;
		}
		if(battingCard.getStrikeRate() != null) {
			if(battingCard.getStrikeRate().equalsIgnoreCase("0.0")) {
				striktRate = "-";
			}else {
				striktRate = CricketFunctions.generateStrikeRate(battingCard.getRuns(), (battingCard.getBalls()+1), 0);
			}
		}else {
			striktRate = "-";
		}
		
		
		if(battingCard.getPlayer().getSurname() == null) {
			surName = "";
		}else {
			surName = battingCard.getPlayer().getSurname();
		}
		
		if(CricketFunctions.isImpactPlayer(matchAllData.getEventFile().getEvents(), inning.getInningNumber(), 
				Integer.valueOf(whatToProcess.split(",")[2])).equalsIgnoreCase(CricketUtil.YES)) {
			impactOmo = 1;
			setImpact(true);
		} else {
			impactOmo =0;
			setImpact(false);
		}
		
		String[] Count = CricketFunctions.getScoreTypeData(CricketUtil.BATSMAN,matchAllData, inning.getInningNumber(), Integer.valueOf(whatToProcess.split(",")[2]),
				"-", matchAllData.getEventFile().getEvents()).split("-");
		
		switch (config.getBroadcaster().toUpperCase()) {
		case Constants.TRI_SERIES:  case Constants.MT20: case Constants.BAN_AFG_SERIES: case Constants.ACC:
			lowerThird = new LowerThird("", battingCard.getPlayer().getFirstname(), surName,"", 
					String.valueOf(battingCard.getRuns()), String.valueOf(battingCard.getBalls() + 1),1,"",inning.getBatting_team().getTeamBadge(),
					null,null,new String[]{String.valueOf(battingCard.getFours()),String.valueOf(battingCard.getSixes()),Count[0],striktRate},
					new String[]{striktRate},null);
			break;
		}
		
		status = PopulateL3rdHeader(whatToProcess.split(",")[0],WhichSide);
		if(status == Constants.OK) {
			switch (config.getBroadcaster().toUpperCase()) {
			case Constants.TRI_SERIES:  case Constants.MT20:
				PopulateL3rdBaseColor(whatToProcess.split(",")[0], WhichSide);
				HideAndShowL3rdSubStrapContainers(WhichSide);
				setPositionOfLT(whatToProcess,WhichSide,config,lowerThird.getNumberOfSubLines());
				break;
			}
			return PopulateL3rdBody(WhichSide,whatToProcess.split(",")[0]);
		} else {
			return status;
		}
	}
	
	public String populateHowOut(String whatToProcess,int WhichSide,MatchAllData matchAllData) throws InterruptedException
	{
		String striktRate = "",howOut = "";
		
		if (matchAllData == null || matchAllData.getMatch() == null || matchAllData.getMatch().getInning() == null) {
			return status;
		} else {
			inning = matchAllData.getMatch().getInning().stream().filter(inn -> inn.getInningNumber() == Integer.valueOf(whatToProcess.split(",")[1])).findAny().orElse(null);
			
			if(inning == null) {
				return "populateHowOut: Inning is Not Found";
			}
			battingCard = inning.getBattingCard().stream().filter(bc -> bc.getPlayerId() == Integer.valueOf(whatToProcess.split(",")[2])).findAny().orElse(null);
			if(battingCard == null) {
				return status;
			}
		}
		
		String[] Count = CricketFunctions.getScoreTypeData(CricketUtil.BATSMAN,matchAllData, inning.getInningNumber(), Integer.valueOf(whatToProcess.split(",")[2]),
				"-", matchAllData.getEventFile().getEvents()).split("-");
		
		if(battingCard.getStrikeRate() != null) {
			if(battingCard.getStrikeRate().equalsIgnoreCase("0.0")) {
				striktRate = "-";
			}else if(battingCard.getStrikeRate().isEmpty()){
				striktRate = "-";
			}else {
				striktRate = String.valueOf(CricketFunctions.generateStrikeRate(battingCard.getRuns(), battingCard.getBalls(), 0));
				
			}
		}else {
			striktRate = "-";
		}
		
		
		if(battingCard.getPlayer().getSurname() == null) {
			surName = "";
		}else {
			surName = battingCard.getPlayer().getSurname();
		}
		if(whatToProcess.contains("with_photo")) {
			photo = true;
		}else {
			photo = false;
		}
		if(battingCard.getHowOut().equalsIgnoreCase(CricketUtil.RUN_OUT)) {
			if(battingCard.getWasHowOutFielderSubstitute() != null && 
					battingCard.getWasHowOutFielderSubstitute().equalsIgnoreCase(CricketUtil.YES)) {
				howOut = "run out " + "(sub - " + battingCard.getHowOutFielder().getTicker_name() + ")";
			}else if(battingCard.getHowOutFielder() == null){
				howOut = "run out (sub)";
			}else {
				howOut = "run out (" + battingCard.getHowOutFielder().getTicker_name() + ")";
			}
		}else if(battingCard.getHowOut().equalsIgnoreCase(CricketUtil.STUMPED)) {
			if(battingCard.getWasHowOutFielderSubstitute() != null && 
					battingCard.getWasHowOutFielderSubstitute().equalsIgnoreCase(CricketUtil.YES)) {
				howOut = "st " + "(sub - " + battingCard.getHowOutFielder().getTicker_name() + ") b " + 
					battingCard.getHowOutBowler().getTicker_name();
			}else {
				howOut = "st " + battingCard.getHowOutFielder().getTicker_name() + "  b " + 
						battingCard.getHowOutBowler().getTicker_name();
			}
		}
		else if(battingCard.getHowOut().equalsIgnoreCase(CricketUtil.CAUGHT)) {
			if(battingCard.getWasHowOutFielderSubstitute() != null && 
					battingCard.getWasHowOutFielderSubstitute().equalsIgnoreCase(CricketUtil.YES)) {
				howOut = "c " +  "(sub - " + battingCard.getHowOutFielder().getTicker_name() + ")  b " + 
						battingCard.getHowOutBowler().getTicker_name();
			} else if(battingCard.getHowOutFielder() == null){
				howOut = "c (sub)";
			}else {
				howOut = "c " + battingCard.getHowOutFielder().getTicker_name() + "  b " + 
						battingCard.getHowOutBowler().getTicker_name();
			}
		}else if(battingCard.getHowOut().equalsIgnoreCase(CricketUtil.RETIRED_HURT)) {
			howOut = "retired hurt";
		}else {
			if(!battingCard.getHowOutPartOne().isEmpty()) {
				howOut = battingCard.getHowOutPartOne();
			}
			
			if(!battingCard.getHowOutPartTwo().isEmpty()) {
				if(!howOut.trim().isEmpty()) {
					howOut = howOut + "  " + battingCard.getHowOutPartTwo();
				}else {
					howOut = battingCard.getHowOutPartTwo();
				}
			}
		}
		
		if(CricketFunctions.isImpactPlayer(matchAllData.getEventFile().getEvents(), inning.getInningNumber(), 
				Integer.valueOf(whatToProcess.split(",")[2])).equalsIgnoreCase(CricketUtil.YES)) {
			impactOmo = 1;
			setImpact(true);
		} else {
			impactOmo =0;
			setImpact(false);
		}
		
		switch (config.getBroadcaster().toUpperCase()) {
		case Constants.TRI_SERIES:  case Constants.MT20: case Constants.BAN_AFG_SERIES: case Constants.ACC:
			lowerThird = new LowerThird("", battingCard.getPlayer().getFirstname(), surName,"", 
					String.valueOf(battingCard.getRuns()), String.valueOf(battingCard.getBalls()),2,"",inning.getBatting_team().getTeamBadge(),
					null,null,new String[]{howOut,String.valueOf(battingCard.getFours()),String.valueOf(battingCard.getSixes()),Count[0],striktRate},
					null,null);
			break;
		}
		
		status = PopulateL3rdHeader(whatToProcess.split(",")[0],WhichSide);
		if(status == Constants.OK) {
			switch (config.getBroadcaster().toUpperCase()) {
			case Constants.TRI_SERIES:  case Constants.MT20:
				PopulateL3rdBaseColor(whatToProcess.split(",")[0], WhichSide);
				HideAndShowL3rdSubStrapContainers(WhichSide);
				setPositionOfLT(whatToProcess,WhichSide,config,lowerThird.getNumberOfSubLines());
				break;
			}
			
			return PopulateL3rdBody(WhichSide,whatToProcess.split(",")[0]);
		} else {
			return status;
		}
	}
	
	public String populateLTNameSuper(String whatToProcess,int WhichSide,MatchAllData matchAllData) throws InterruptedException
	{
		if(config.getCategory().equalsIgnoreCase("MEN")) {
			logoCategory = "M";
		}else if(config.getCategory().equalsIgnoreCase("WOMEN")) {
			logoCategory = "W";
		}else {
			logoCategory = "";
		}
		
		namesuper = this.nameSupers.stream().filter(ns -> ns.getNamesuperId() == Integer.valueOf(whatToProcess.split(",")[2]))
			.findAny().orElse(null);
		
		if(namesuper.getSurname() == null) {
			surName = "";
		}else {
			surName = namesuper.getSurname();
		}
		
		switch (config.getBroadcaster().toUpperCase()) {
		case Constants.TRI_SERIES:  case Constants.MT20: case Constants.BAN_AFG_SERIES: case Constants.ACC:
			if(namesuper.getSponsor()!= null && namesuper.getFlag()!= null && namesuper.getSubLine() != null) {
				lowerThird = new LowerThird("", namesuper.getFirstname(), surName,"", "", "", 1, namesuper.getSponsor() ,namesuper.getFlag(),
						null,null,new String[]{namesuper.getSubLine()},null,null);
			}else if(namesuper.getSponsor()!= null && namesuper.getFlag()== null && namesuper.getSubLine() != null) {
				lowerThird = new LowerThird("", namesuper.getFirstname(), surName,"", "", "", 1, namesuper.getSponsor() ,"",
						null,null,new String[]{namesuper.getSubLine()},null,null);
			}else if(namesuper.getSponsor()== null && namesuper.getFlag()!= null && namesuper.getSubLine() != null) {
				lowerThird = new LowerThird("", namesuper.getFirstname(), surName,"", "", "", 1, "" ,namesuper.getFlag(),
						null,null,new String[]{namesuper.getSubLine()},null,null);
			}else if(namesuper.getSponsor()== null && namesuper.getFlag()== null && namesuper.getSubLine() != null) {
				lowerThird = new LowerThird("", namesuper.getFirstname(), surName,"", "", "", 1, "" ,config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES)?
						"EVENT":"EVENT",null,null,new String[]{namesuper.getSubLine()},null,null);
			}else if(namesuper.getSponsor()!= null && namesuper.getFlag()!= null && namesuper.getSubLine() == null) {
				lowerThird = new LowerThird("", namesuper.getFirstname(), surName,"", "", "", 0, namesuper.getSponsor() ,namesuper.getFlag(),
						null,null,new String[]{""},null,null);
			}else if(namesuper.getSponsor()!= null && namesuper.getFlag()== null && namesuper.getSubLine() == null) {
				lowerThird = new LowerThird("", namesuper.getFirstname(), surName,"", "", "", 0, namesuper.getSponsor() ,"",
						null,null,new String[]{""},null,null);
			}else if(namesuper.getSponsor()== null && namesuper.getFlag()!= null && namesuper.getSubLine() == null) {
				lowerThird = new LowerThird("", namesuper.getFirstname(), surName,"", "", "", 0, "" ,namesuper.getFlag(),
						null,null,new String[]{""},null,null);
			}else if(namesuper.getSponsor()== null && namesuper.getFlag()== null && namesuper.getSubLine() == null) {
				lowerThird = new LowerThird("", namesuper.getFirstname(), surName,"", "", "", 0, "" ,"",
						null,null,new String[]{""},null,null);
			}else {
				lowerThird = new LowerThird("", namesuper.getFirstname(), surName,"", "", "", 1, "" ,"",
						null,null,new String[]{namesuper.getSubLine()},null,null);
			}
			break;
		}
		
		status = PopulateL3rdHeader(whatToProcess.split(",")[0],WhichSide);
		if(status == Constants.OK) {
			switch (config.getBroadcaster().toUpperCase()) {
			case Constants.TRI_SERIES:  case Constants.MT20:
				PopulateL3rdBaseColor(whatToProcess.split(",")[0], WhichSide);
//				setPositionOfLT(whatToProcess,WhichSide,config,lowerThird.getNumberOfSubLines());
				break;	
			}
			return PopulateL3rdBody(WhichSide,whatToProcess.split(",")[0]);
		} else {
			return status;
		}
	}

	public String populateL3rdProjected(String whatToProcess,int WhichSide,MatchAllData matchAllData) throws InterruptedException
	{
		if (matchAllData == null || matchAllData.getMatch() == null || matchAllData.getMatch().getInning() == null) {
			return status;
		} else {
			inning = matchAllData.getMatch().getInning().stream().filter(inn -> inn.getInningNumber() == 1 &&
					inn.getIsCurrentInning().equalsIgnoreCase(CricketUtil.YES)).findAny().orElse(null);
			
			if(inning == null) {
				return "Current Inning is Not 1";
			}
			//this_data_str = CricketFunctions.projectedScore(matchAllData);
			this_data_str = CricketFunctions.GetProjectedScore(matchAllData);
			if(this_data_str.size() <= 0) {
				return status;
			}
		}
		switch (config.getBroadcaster().toUpperCase()) {
		case Constants.TRI_SERIES:  case Constants.MT20:
			lowerThird = new LowerThird("", inning.getBatting_team().getTeamName1(), "","PROJECTED SCORES", CricketFunctions.getTeamScore(inning, "-", false), 
					CricketFunctions.OverBalls(inning.getTotalOvers(), inning.getTotalBalls()),2,"",inning.getBatting_team().getTeamBadge(),
					new String[]{"CURRENT (" + this_data_str.get(0) + ")",this_data_str.get(2) + "/OVER",this_data_str.get(4) + "/OVER",this_data_str.get(6) + "/OVER"},
					new String[]{this_data_str.get(1),this_data_str.get(3),this_data_str.get(5),this_data_str.get(7)},new String[]{"RATE","SCORE"},null,
					new String[] {"-99","-6","80","167"});
			break;
		case Constants.BAN_AFG_SERIES: case Constants.ACC:
			lowerThird = new LowerThird("", inning.getBatting_team().getTeamName1(), "","PROJECTED SCORES", CricketFunctions.getTeamScore(inning, "-", false), 
					CricketFunctions.OverBalls(inning.getTotalOvers(), inning.getTotalBalls()),2,"",inning.getBatting_team().getTeamBadge(),
					new String[]{"@CRR (" + this_data_str.get(0) + ")","@"+this_data_str.get(2) + " RPO","@"+this_data_str.get(4) + " RPO","@"+this_data_str.get(6) + " RPO"},
					new String[]{this_data_str.get(1),this_data_str.get(3),this_data_str.get(5),this_data_str.get(7)},new String[]{"RATE","SCORE"},null,
					new String[] {"-99","-6","80","167"});
			break;	
		}
		
		status = PopulateL3rdHeader(whatToProcess.split(",")[0],WhichSide);
		if(status == Constants.OK) {
			switch (config.getBroadcaster().toUpperCase()) {
			 case Constants.TRI_SERIES:  case Constants.MT20:
				PopulateL3rdBaseColor(whatToProcess.split(",")[0], WhichSide);
				HideAndShowL3rdSubStrapContainers(WhichSide);
				//setStatsPositionOfLT(4, 2, WhichSide,whatToProcess.split(",")[0], print_writers, config);
				break;
			}
			
			setPositionOfLT(whatToProcess,WhichSide,config,lowerThird.getNumberOfSubLines());

			return PopulateL3rdBody(WhichSide, whatToProcess.split(",")[0]);
		} else {
			return status;
		}
		
	}
	
	public String PopulateCurrentAndCareer(String whatToProcess,int WhichSide,MatchAllData matchAllData) throws InterruptedException, IOException
	{ 
		FirstPlayerId = Integer.valueOf(whatToProcess.split(",")[2]);
		String outOrNot = "",striktRate = "";
		stat = null;
		if (matchAllData == null || matchAllData.getMatch() == null || matchAllData.getMatch().getInning() == null) {
			return status;
		} else {
			inning = matchAllData.getMatch().getInning().stream().filter(inn -> inn.getInningNumber() == Integer.valueOf(whatToProcess.split(",")[1])).findAny().orElse(null);
			if(inning == null) {
				return "populateBatThisMatch: Current Inning NOT found in this match";
			}
			battingCard = inning.getBattingCard().stream().filter(bc -> bc.getPlayerId() == Integer.valueOf(whatToProcess.split(",")[2])).findAny().orElse(null);
			if(battingCard == null) {
				return status;
			}
			if(battingCard.getStatus().equalsIgnoreCase(CricketUtil.NOT_OUT)) {
				outOrNot = "NOT OUT";
			}else {
				outOrNot = "";
			}
		}
		
		if(battingCard.getStrikeRate() !=null) {
			if(battingCard.getStrikeRate().trim().isEmpty()) {
				striktRate = "-";
			}else {
				striktRate = String.valueOf(CricketFunctions.generateStrikeRate(battingCard.getRuns(), battingCard.getBalls(), 0));
			}
		}else {
			striktRate = "-";
		}
		
		String[] Count = CricketFunctions.getScoreTypeData(CricketUtil.BATSMAN,matchAllData, inning.getInningNumber(), Integer.valueOf(whatToProcess.split(",")[2]),
				"-", matchAllData.getEventFile().getEvents()).split("-");
		
		if(battingCard.getPlayer().getSurname() == null) {
			surName = "";
		}else {
			surName = battingCard.getPlayer().getSurname();
		}
		
		switch (config.getBroadcaster().toUpperCase()) {
		case Constants.TRI_SERIES:  case Constants.MT20:
			if(!whatToProcess.split(",")[3].equalsIgnoreCase("NOSTATS")) {
				if(whatToProcess.split(",")[3].equalsIgnoreCase("THISSERIES")) {
					addPastDataToCurr = CricketFunctions.extractTournamentData("CURRENT_MATCH_DATA", false, headToHead,cricketService, matchAllData, past_tournament_stats);
					if(addPastDataToCurr == null) {
						return "addPastDataToCurr is Null";
					}
					
					for(Tournament tourn : addPastDataToCurr) {
						for(BestStats bs : tourn.getBatsman_best_Stats()) {
							top_batsman_beststats.add(bs);
						}
						for(BestStats bfig : tourn.getBowler_best_Stats()) {
							top_bowler_beststats.add(bfig);
						}
					}
					
					Collections.sort(top_batsman_beststats, new CricketFunctions.PlayerBestStatsComparator());
					Collections.sort(top_bowler_beststats, new CricketFunctions.PlayerBestStatsComparator());
					
					tournament = addPastDataToCurr.stream().filter(tourn -> tourn.getPlayerId() == Integer.valueOf(whatToProcess.split(",")[2])).findAny().orElse(null);
				}else {
					switch (config.getBroadcaster().toUpperCase()) {
					case Constants.TRI_SERIES:  case Constants.MT20:
						
						switch (whatToProcess.split(",")[3].toUpperCase()) {
						case "DT20": case "IT20":
							
							switch (whatToProcess.split(",")[3].toUpperCase()) {
							case "DT20":
								matchAllData.getSetup().setMatchType("DT20");
								
								statsType = statsTypes.stream().filter(st -> st.getStatsShortName().equalsIgnoreCase("DT20")).findAny().orElse(null);
								
								break;
							case "IT20":
								matchAllData.getSetup().setMatchType("IT20");
								
								statsType = statsTypes.stream().filter(st -> st.getStatsShortName().equalsIgnoreCase("IT20")).findAny().orElse(null);
								
								break;	
							}
							if(statsType == null) {
								return "PopulateL3rdPlayerProfile: Stats Type not found for profile [" + WhichProfile + "]";
							}
							
							System.out.println(statsType.getStatsFullName());
							System.out.println(statsType.getStatsId());
							
							stat = statistics.stream().filter(st -> st.getPlayerID() == FirstPlayerId && statsType.getStatsId() == st.getStatsTypeId()).findAny().orElse(null);
							if(stat == null) {
								return "PopulateL3rdPlayerProfile: Stats not found for Player Id [" + FirstPlayerId + "]";
							}
							
							stat.setStats_type(statsType);
							stat = CricketFunctions.updateTournamentWithH2h(stat, headToHead, matchAllData, CricketUtil.FULL);
							stat = CricketFunctions.updateStatisticsWithMatchData(stat, matchAllData, CricketUtil.FULL);
							break;
						case "TEST": case "ODI":
							
							switch (whatToProcess.split(",")[3].toUpperCase()) {
							case "TEST":
								matchAllData.getSetup().setMatchType("TEST");
								
								statsType = statsTypes.stream().filter(st -> st.getStatsShortName().equalsIgnoreCase("TEST")).findAny().orElse(null);
								
								break;
							case "ODI":
								matchAllData.getSetup().setMatchType("ODI");
								
								statsType = statsTypes.stream().filter(st -> st.getStatsShortName().equalsIgnoreCase("ODI")).findAny().orElse(null);
								
								break;	
							}
							
							if(statsType == null) {
								return "PopulateL3rdPlayerProfile: Stats Type not found for profile [" + WhichProfile + "]";
							}
							
							stat = statistics.stream().filter(st -> st.getPlayerID() == FirstPlayerId && statsType.getStatsId() == st.getStatsTypeId()).findAny().orElse(null);
							if(stat == null) {
								return "PopulateL3rdPlayerProfile: Stats not found for Player Id [" + FirstPlayerId + "]";
							}
							
							stat.setStats_type(statsType);
							stat = CricketFunctions.updateTournamentWithH2h(stat, headToHead, matchAllData, CricketUtil.FULL);
//							stat = CricketFunctions.updateStatisticsWithMatchData(stat, matchAllData, CricketUtil.FULL);
							break;	
						}
						
						break;
					}
				}
			}
			break;
		}
		
			
		switch (config.getBroadcaster().toUpperCase()) {
		case Constants.TRI_SERIES:  case Constants.MT20:
			String sponsorOrNot = whatToProcess.split(",")[3];
			if(stat!=null) {
				lowerThird = new LowerThird("DT20", battingCard.getPlayer().getFirstname(), surName,outOrNot, String.valueOf(battingCard.getRuns()),
						String.valueOf(battingCard.getBalls()), 3, sponsorOrNot,inning.getBatting_team().getTeamName4(),new String[] {"DOTS","FOURS","SIXES","STRIKE RATE"},new String[] {Count[0],
								String.valueOf(battingCard.getFours()),String.valueOf(battingCard.getSixes()),striktRate},
						new String[] {stat.getStats_type().getStatsShortName(),String.valueOf(stat.getMatches()),String.valueOf(stat.getRuns()),
								String.valueOf(CricketFunctions.generateStrikeRate(stat.getRuns(), stat.getBallsFaced(), 1))},null,
						new String[] {"-178","-73","41","160"});
			}
			break;
		}
		System.out.println("getHeaderText =" + lowerThird.getHeaderText());
		System.out.println("getFirstName = " + lowerThird.getFirstName());
		System.out.println("getSurName = "+lowerThird.getSurName());
		System.out.println("getSubTitle = "+lowerThird.getSubTitle());
		System.out.println("getScoreText = "+lowerThird.getScoreText());
		System.out.println("getBallsFacedText = "+lowerThird.getBallsFacedText());
		System.out.println("getNumberOfSubLines = "+lowerThird.getNumberOfSubLines());
		System.out.println("getWhichSponsor = "+lowerThird.getWhichSponsor());
		System.out.println("getWhichTeamFlag = "+lowerThird.getWhichTeamFlag());
		System.out.println("getTitlesText = "+lowerThird.getTitlesText());
		System.out.println("getStatsText = "+lowerThird.getStatsText());
		System.out.println("getLeftText = "+lowerThird.getLeftText());
		System.out.println("getRightText = "+lowerThird.getRightText());
		
		status = PopulateL3rdHeader(whatToProcess.split(",")[0],WhichSide);
		if(status == Constants.OK) {
			switch (config.getBroadcaster()) {
			case Constants.TRI_SERIES:  case Constants.MT20:
				HideAndShowL3rdSubStrapContainers(WhichSide);
//				setStatsPositionOfLT(4, 2, WhichSide,whatToProcess.split(",")[0], print_writers, config);
				setPositionOfLT(whatToProcess,WhichSide,config,lowerThird.getNumberOfSubLines());
//				setPositionOfLT(lowerThird.getNumberOfSubLines(), WhichSide, 4,print_writers, config);
				break;
			}

			return PopulateL3rdBody(WhichSide, whatToProcess.split(",")[0]);
		} else {
			return status;
		}
	}
	public String populateBowlThisMatchAndCareer(String whatToProcess,int WhichSide,MatchAllData matchAllData) throws InterruptedException, IOException
	{ 
		FirstPlayerId = Integer.valueOf(whatToProcess.split(",")[2]);
		String economy = "",plural = "";
		stat = null;
		if (matchAllData == null || matchAllData.getMatch() == null || matchAllData.getMatch().getInning() == null) {
			return status;
		} else {
			inning = matchAllData.getMatch().getInning().stream().filter(inn -> inn.getInningNumber() == Integer.valueOf(whatToProcess.split(",")[1])).findAny().orElse(null);
			if(inning == null) {
				return "populateBallThisMatch: Current Inning NOT found in this match";
			}
			bowlingCard = inning.getBowlingCard().stream().filter(boc -> boc.getPlayerId() == Integer.valueOf(whatToProcess.split(",")[2])).findAny().orElse(null);
			if(bowlingCard == null) {
				return status;
			}
		}
		
		if(bowlingCard.getEconomyRate()==null || bowlingCard.getEconomyRate().equalsIgnoreCase("0.00")) {
			economy = "-";
		}else {
			economy = String.valueOf(bowlingCard.getEconomyRate());
		}
		
		if(bowlingCard.getPlayer().getSurname() == null) {
			surName = "";
		}else {
			surName = bowlingCard.getPlayer().getSurname();
		}
		
		
		switch (config.getBroadcaster().toUpperCase()) {
		case Constants.TRI_SERIES:  case Constants.MT20:
			if(!whatToProcess.split(",")[3].equalsIgnoreCase("NOSTATS")) {
				if(whatToProcess.split(",")[3].equalsIgnoreCase("THISSERIES")) {
					addPastDataToCurr = CricketFunctions.extractTournamentData("CURRENT_MATCH_DATA", false, headToHead,cricketService, matchAllData, past_tournament_stats);
					if(addPastDataToCurr == null) {
						return "addPastDataToCurr is Null";
					}
					
					for(Tournament tourn : addPastDataToCurr) {
						for(BestStats bs : tourn.getBatsman_best_Stats()) {
							top_batsman_beststats.add(bs);
						}
						for(BestStats bfig : tourn.getBowler_best_Stats()) {
							top_bowler_beststats.add(bfig);
						}
					}
					
					Collections.sort(top_batsman_beststats, new CricketFunctions.PlayerBestStatsComparator());
					Collections.sort(top_bowler_beststats, new CricketFunctions.PlayerBestStatsComparator());
					
					tournament = addPastDataToCurr.stream().filter(tourn -> tourn.getPlayerId() == Integer.valueOf(whatToProcess.split(",")[2])).findAny().orElse(null);
					System.out.println("WICKETS : "+tournament.getWickets());
					
				}else {
					switch (config.getBroadcaster().toUpperCase()) {
					case Constants.TRI_SERIES:  case Constants.MT20:
						
						switch (whatToProcess.split(",")[3].toUpperCase()) {
						case "DT20": case "IT20":
							
							switch (whatToProcess.split(",")[3].toUpperCase()) {
							case "DT20":
								matchAllData.getSetup().setMatchType("DT20");
								
								statsType = statsTypes.stream().filter(st -> st.getStatsShortName().equalsIgnoreCase("DT20")).findAny().orElse(null);
								
								break;
							case "IT20":
								matchAllData.getSetup().setMatchType("IT20");
								
								statsType = statsTypes.stream().filter(st -> st.getStatsShortName().equalsIgnoreCase("IT20")).findAny().orElse(null);
								
								break;	
							}
							if(statsType == null) {
								return "PopulateL3rdPlayerProfile: Stats Type not found for profile [" + WhichProfile + "]";
							}
							
							System.out.println(statsType.getStatsFullName());
							System.out.println(statsType.getStatsId());
							
							stat = statistics.stream().filter(st -> st.getPlayerID() == FirstPlayerId && statsType.getStatsId() == st.getStatsTypeId()).findAny().orElse(null);
							if(stat == null) {
								return "PopulateL3rdPlayerProfile: Stats not found for Player Id [" + FirstPlayerId + "]";
							}
							
							stat.setStats_type(statsType);
							stat = CricketFunctions.updateTournamentWithH2h(stat, headToHead, matchAllData, CricketUtil.FULL);
							stat = CricketFunctions.updateStatisticsWithMatchData(stat, matchAllData, CricketUtil.FULL);
							break;
						case "TEST": case "ODI":
							
							switch (whatToProcess.split(",")[3].toUpperCase()) {
							case "TEST":
								matchAllData.getSetup().setMatchType("TEST");
								
								statsType = statsTypes.stream().filter(st -> st.getStatsShortName().equalsIgnoreCase("TEST")).findAny().orElse(null);
								
								break;
							case "ODI":
								matchAllData.getSetup().setMatchType("ODI");
								
								statsType = statsTypes.stream().filter(st -> st.getStatsShortName().equalsIgnoreCase("ODI")).findAny().orElse(null);
								
								break;	
							}
							
							if(statsType == null) {
								return "PopulateL3rdPlayerProfile: Stats Type not found for profile [" + WhichProfile + "]";
							}
							
							stat = statistics.stream().filter(st -> st.getPlayerID() == FirstPlayerId && statsType.getStatsId() == st.getStatsTypeId()).findAny().orElse(null);
							if(stat == null) {
								return "PopulateL3rdPlayerProfile: Stats not found for Player Id [" + FirstPlayerId + "]";
							}
							
							stat.setStats_type(statsType);
							stat = CricketFunctions.updateTournamentWithH2h(stat, headToHead, matchAllData, CricketUtil.FULL);
//							stat = CricketFunctions.updateStatisticsWithMatchData(stat, matchAllData, CricketUtil.FULL);
							break;	
						}
						
						break;
					}
				}
			}
			break;
		}
		
			
		switch (config.getBroadcaster().toUpperCase()) {
		case Constants.TRI_SERIES:  case Constants.MT20:
			String sponsorOrNot = whatToProcess.split(",")[3];
			if(stat!=null) {
				lowerThird = new LowerThird("DT20", bowlingCard.getPlayer().getFirstname(), surName,"", "", "", 3, sponsorOrNot, inning.getBowling_team().getTeamName4(),
						new String[] {"OVERS","DOTS", "RUNS", "WICKETS", "ECONOMY"},new String[]{CricketFunctions.OverBalls(bowlingCard.getOvers(), bowlingCard.getBalls()), 
						String.valueOf(bowlingCard.getDots()),String.valueOf(bowlingCard.getRuns()),String.valueOf(bowlingCard.getWickets()), economy}
						,new String[] {stat.getStats_type().getStatsShortName(), String.valueOf(stat.getMatches()), String.valueOf(stat.getWickets()), String.valueOf(CricketFunctions.getEconomy(stat.getRunsConceded(),
								stat.getBallsBowled(), 2, "-"))},null,new String[] {"-174","-100","-20","64","165"});
			}
			break;
		}
		System.out.println("getHeaderText =" + lowerThird.getHeaderText());
		System.out.println("getFirstName = " + lowerThird.getFirstName());
		System.out.println("getSurName = "+lowerThird.getSurName());
		System.out.println("getSubTitle = "+lowerThird.getSubTitle());
		System.out.println("getScoreText = "+lowerThird.getScoreText());
		System.out.println("getBallsFacedText = "+lowerThird.getBallsFacedText());
		System.out.println("getNumberOfSubLines = "+lowerThird.getNumberOfSubLines());
		System.out.println("getWhichSponsor = "+lowerThird.getWhichSponsor());
		System.out.println("getWhichTeamFlag = "+lowerThird.getWhichTeamFlag());
		System.out.println("getTitlesText = "+lowerThird.getTitlesText());
		System.out.println("getStatsText = "+lowerThird.getStatsText());
		System.out.println("getLeftText = "+lowerThird.getLeftText());
		System.out.println("getRightText = "+lowerThird.getRightText());
		
		status = PopulateL3rdHeader(whatToProcess.split(",")[0],WhichSide);
		if(status == Constants.OK) {
			switch (config.getBroadcaster()) {
			case Constants.TRI_SERIES:  case Constants.MT20:
				HideAndShowL3rdSubStrapContainers(WhichSide);
//				setStatsPositionOfLT(4, 2, WhichSide,whatToProcess.split(",")[0], print_writers, config);
				setPositionOfLT(whatToProcess,WhichSide,config,lowerThird.getNumberOfSubLines());
//				setPositionOfLT(lowerThird.getNumberOfSubLines(), WhichSide, 4,print_writers, config);
				break;
			}

			return PopulateL3rdBody(WhichSide, whatToProcess.split(",")[0]);
		} else {
			return status;
		}
	}
	public String populateMultiCareer(String whatToProcess, int WhichSide, MatchAllData matchAllData) 
			throws JsonMappingException, JsonProcessingException, InterruptedException{
		System.out.println(whatToProcess);
		if(!whatToProcess.contains(",") && whatToProcess.split(",").length >= 4) {
			return "PopulateL3rdPlayerProfile: error occured in what to process";
		}
		FirstPlayerId = Integer.valueOf(whatToProcess.split(",")[2]);
		player = CricketFunctions.getPlayerFromMatchData(FirstPlayerId, matchAllData); 
		if(FirstPlayerId <= 0 ) {
			return "PopulateL3rdPlayerProfile: Player Id NOT found [" + FirstPlayerId + "]";
		}
		
		player = CricketFunctions.getPlayerFromMatchData(FirstPlayerId, matchAllData); 
		
		if(player == null) {
			return "PopulateL3rdPlayerProfile: Player Id not found [" + FirstPlayerId + "]";
		}
		
		if(matchAllData.getSetup().getHomeTeamId() == player.getTeamId()) {
			team = matchAllData.getSetup().getHomeTeam();
		} else if(matchAllData.getSetup().getAwayTeamId() == player.getTeamId()) {
			team = matchAllData.getSetup().getAwayTeam();
		} 
		
		if(team == null) {
			return "PopulateL3rdPlayerProfile: Team Id not found [" + player.getTeamId() + "]";
		}
		switch (config.getBroadcaster().toUpperCase()) {
		case Constants.TRI_SERIES:  case Constants.MT20:
			statsType = statsTypes.stream().filter(st -> st.getStatsShortName().equalsIgnoreCase("IT20")).findAny().orElse(null);
			statsTypeDT20 = statsTypes.stream().filter(st -> st.getStatsShortName().equalsIgnoreCase("DT20")).findAny().orElse(null);
			statsTypeODI = statsTypes.stream().filter(st -> st.getStatsShortName().equalsIgnoreCase("ODI")).findAny().orElse(null);
			statsTypeTEST = statsTypes.stream().filter(st -> st.getStatsShortName().equalsIgnoreCase("TEST")).findAny().orElse(null);
			
			if(statsType == null) {
				return "PopulateL3rdPlayerProfile: Stats Type not found for profile [" + WhichProfile + "]";
			}
			stat = statistics.stream().filter(st -> st.getPlayerID() == FirstPlayerId && statsType.getStatsId() == st.getStatsTypeId()).findAny().orElse(null);
			if(statsType != null) {
				stat.setStats_type(statsType);	
			}
			stat = CricketFunctions.updateTournamentWithH2h(stat, headToHead, matchAllData, CricketUtil.FULL);
			//Domestic
			statDT20 = statistics.stream().filter(st -> st.getPlayerID() == FirstPlayerId && statsTypeDT20.getStatsId() == st.getStatsTypeId()).findAny().orElse(null);
			statDT20.setStats_type(statsTypeDT20);
			statDT20 = CricketFunctions.updateTournamentWithH2h(stat, headToHead, matchAllData, CricketUtil.FULL);
			//ODI
			statODI = statistics.stream().filter(st -> st.getPlayerID() == FirstPlayerId && statsTypeODI.getStatsId() == st.getStatsTypeId()).findAny().orElse(null);
			statODI.setStats_type(statsTypeODI);
			//Dt20
			statTEST = statistics.stream().filter(st -> st.getPlayerID() == FirstPlayerId && statsTypeTEST.getStatsId() == st.getStatsTypeId()).findAny().orElse(null);
			statTEST.setStats_type(statsTypeTEST);
			
			break;
		}
		String fiftyDT20 = "", hundredDT20 = "", strikeRateDT20 = "", runsDT20 = "" ,BestDT20 = "" , short_nameDT20 = "";
		String fiftyIT20 = "", hundredIT20 = "", strikeRateIT20 = "" , RunsIT20 = "" , BestIT20 = "" , short_nameIT20 = ""; 
		String fiftyODI = "", hundredODI = "",strikeRateODI = "" ,RunsODI = "" , BestODI = "" , short_nameODI = "";
		String fiftyTEST = "", hundredTEST = "", strikeRateTEST = "",RunsTEST = "" , BestTEST = "" , short_nameTEST = "";
		
		
		
		if(whatToProcess.split(",")[0].equalsIgnoreCase("Control_c")) {
			//IT20
			fiftyIT20   = (stat.getFifties() == 0) ? "-" : String.valueOf(stat.getFifties());
			hundredIT20 = (stat.getHundreds() == 0) ? "-" : String.valueOf(stat.getHundreds());
			if (fiftyIT20.equals("-") && !hundredIT20.equals("-"))  fiftyIT20 = "0";
			if (hundredIT20.equals("-") && !fiftyIT20.equals("-")) hundredIT20 = "0";

			strikeRateIT20 = (CricketFunctions.generateStrikeRate(stat.getRuns(), stat.getBallsFaced(), 1).trim().isEmpty()) 
			        ? "-" 
			        : String.valueOf(CricketFunctions.generateStrikeRate(stat.getRuns(), stat.getBallsFaced(), 0));

			RunsIT20 = (stat.getRuns() == 0) 
			        ? "-" 
			        : String.format("%,d\n", stat.getRuns());

			surName = (player.getSurname() == null) ? "" : player.getSurname();

			BestIT20 = (stat.getBestScore().equals("0")) ? "-" : stat.getBestScore();

			
			//dt20
			fiftyDT20   = (statDT20.getFifties() == 0) ? "-" : String.valueOf(statDT20.getFifties());
			hundredDT20 = (statDT20.getHundreds() == 0) ? "-" : String.valueOf(statDT20.getHundreds());
			if (fiftyDT20.equals("-") && !hundredDT20.equals("-"))  fiftyDT20 = "0";
			if (hundredDT20.equals("-") && !fiftyDT20.equals("-")) hundredDT20 = "0";

			strikeRateDT20 = (CricketFunctions.generateStrikeRate(statDT20.getRuns(), statDT20.getBallsFaced(), 1).trim().isEmpty()) 
			        ? "-" 
			        : String.valueOf(CricketFunctions.generateStrikeRate(statDT20.getRuns(), statDT20.getBallsFaced(), 0));

			runsDT20 = (statDT20.getRuns() == 0) 
			        ? "-" 
			        : String.format("%,d\n", statDT20.getRuns());

			short_nameDT20 = (player.getSurname() == null) ? "" : player.getSurname();

			BestDT20 = (statDT20.getBestScore().equals("0")) ? "-" : statDT20.getBestScore();
			
			//odi
			fiftyODI   = (statODI.getFifties() == 0) ? "-" : String.valueOf(statODI.getFifties());
			hundredODI = (statODI.getHundreds() == 0) ? "-" : String.valueOf(statODI.getHundreds());

			if (fiftyODI.equals("-") && !hundredODI.equals("-"))  fiftyODI = "0";
			if (hundredODI.equals("-") && !fiftyODI.equals("-")) hundredODI = "0";

			strikeRateODI = (CricketFunctions.generateStrikeRate(statODI.getRuns(), statODI.getBallsFaced(), 1).trim().isEmpty()) 
			        ? "-" 
			        : String.valueOf(CricketFunctions.generateStrikeRate(statODI.getRuns(), statODI.getBallsFaced(), 0));

			RunsODI = (statODI.getRuns() == 0) 
			        ? "-" 
			        : String.format("%,d\n", statODI.getRuns());

			short_nameODI = (player.getSurname() == null) ? "" : player.getSurname();

			BestODI = (statODI.getBestScore().equals("0")) ? "-" : statODI.getBestScore();
			
			//test
			fiftyTEST   = (statTEST.getFifties() == 0) ? "-" : String.valueOf(statTEST.getFifties());
			hundredTEST = (statTEST.getHundreds() == 0) ? "-" : String.valueOf(statTEST.getHundreds());
			if (fiftyTEST.equals("-") && !hundredTEST.equals("-"))  fiftyTEST = "0";
			if (hundredTEST.equals("-") && !fiftyTEST.equals("-")) hundredTEST = "0";

			strikeRateTEST = (CricketFunctions.generateStrikeRate(statTEST.getRuns(), statTEST.getBallsFaced(), 1).trim().isEmpty()) 
			        ? "-" 
			        : String.valueOf(CricketFunctions.generateStrikeRate(statTEST.getRuns(), statTEST.getBallsFaced(), 0));

			RunsTEST = String.format("%,d", statTEST.getRuns());  // will show 0 if runs=

			short_nameTEST = (player.getSurname() == null) ? "" : player.getSurname();

			BestTEST = (statTEST.getBestScore() == null || statTEST.getBestScore().trim().equals("0"))
			        ? "-" : statTEST.getBestScore().trim();

            //for name
			short_nameODI =  "ODI CAREER";
			short_nameTEST =  "TEST CAREER";
			short_nameIT20 =  "T20I CAREER";
			short_nameDT20 =  "T20 CAREER";
	



			
			switch (config.getBroadcaster().toUpperCase()) {
			case Constants.TRI_SERIES:  case Constants.MT20:
				switch (config.getBroadcaster().toUpperCase()) {
				
				case Constants.TRI_SERIES:  case Constants.MT20:
					lowerThird = new LowerThird(CricketFunctions.getbattingstyle(player.getBattingStyle(),
							CricketUtil.FULL, true, false).toUpperCase(), player.getFirstname(), surName,short_nameIT20, "", "", 4,"",team.getTeamBadge(),
							new String[]{"MATCHES", "RUNS", "50s/100s", "BEST", "STRIKE RATE"},
							new String[]{String.valueOf(stat.getMatches()) + "-" + String.valueOf(statODI.getMatches())+ "-" + String.valueOf(statTEST.getMatches()) + "-" + String.valueOf(statDT20.getMatches()) 
							,RunsIT20 + "-" + RunsODI  + "-" + RunsTEST + "-" + runsDT20, fiftyIT20 + "/" + hundredIT20 + "-" + fiftyODI + "/" + hundredODI + "-" + fiftyTEST + "/" + hundredTEST+ "-" + fiftyDT20 + "/" + hundredDT20
							, BestIT20+ "-" +BestODI + "-" + BestTEST + "-" + BestDT20,strikeRateIT20 + "-" + strikeRateODI + "-" + strikeRateTEST + "-" + strikeRateDT20 },null,new String[] {"WITHOUT"},
							new String[] {"-165","-90","-8","72","157"});
					System.out.println("+=====================");
					     System.out.println(lowerThird.getStatsText()[1].split("-")[2]);
					     System.out.println(lowerThird.getStatsText()[1].split("-")[0]);
					     System.out.println(lowerThird.getStatsText()[1].split("-")[1]);
					break;
				
				}
				break;
			}
			
			
		}	
		System.out.println("Coming insdie");
		status = PopulateL3rdHeader(whatToProcess.split(",")[0],WhichSide);
		if(status == Constants.OK) {
			PopulateL3rdBaseColor(whatToProcess.split(",")[0], WhichSide);
			HideAndShowL3rdSubStrapContainers(WhichSide);
			setPositionOfLT(whatToProcess,WhichSide,config,lowerThird.getNumberOfSubLines());

			return PopulateL3rdBody(WhichSide,whatToProcess.split(",")[0]);
		} else {
			return status;
		}
	}
	public String populateLTNameSuperPlayer(String whatToProcess, int whichSide, MatchAllData matchAllData) throws InterruptedException {
		player =  CricketFunctions.getPlayerFromMatchData(Integer.valueOf(whatToProcess.split(",")[2]), matchAllData);
		team = Teams.stream().filter(tm -> tm.getTeamId() == player.getTeamId()).findAny().orElse(null);
		
		if(whatToProcess.contains("with_photo")) {
			photo = true;
		}else {
			photo = false;
		}
		if(team == null) {
			return "populateNameSuper: Team From Database returning Null";
		}
		
		if(player.getSurname() == null) {
			surName = "";
		}else {
			surName = player.getSurname();
		}
		
		WhichProfile = whatToProcess.split(",")[3].toUpperCase();
		
		lowerThird = new LowerThird("", player.getFirstname(), surName, team.getTeamName1(),
				team.getTeamName4(), "", 1, "",team.getTeamBadge(),
				null,null,new String[]{WhichProfile},null,null);
		
		status = PopulateL3rdHeader(whatToProcess.split(",")[0],whichSide);
		if(status == Constants.OK) {
			switch (config.getBroadcaster().toUpperCase()) {
			case Constants.TRI_SERIES:  case Constants.MT20: case Constants.BAN_AFG_SERIES: case Constants.ACC:
				PopulateL3rdBaseColor(whatToProcess.split(",")[0], whichSide);
//				setPositionOfLT(whatToProcess,whichSide,config,lowerThird.getNumberOfSubLines());
				break;	
			}
			return PopulateL3rdBody(whichSide, whatToProcess.split(",")[0]);
		} else {
			return status;
		}
	}
	
	public String PopulateL3rdPlayerProfile(String whatToProcess, int WhichSide, MatchAllData matchAllData) 
			throws JsonMappingException, JsonProcessingException, InterruptedException
		{
			if(!whatToProcess.contains(",") && whatToProcess.split(",").length >= 4) {
				return "PopulateL3rdPlayerProfile: error occured in what to process";
			}

			FirstPlayerId = Integer.valueOf(whatToProcess.split(",")[2]);
			WhichProfile = whatToProcess.split(",")[3];
			
			if(FirstPlayerId <= 0 || WhichProfile == null) {
				return "PopulateL3rdPlayerProfile: Player Id NOT found [" + FirstPlayerId + "]";
			}
			
			player = CricketFunctions.getPlayerFromMatchData(FirstPlayerId, matchAllData); 
			
			if(player == null) {
				return "PopulateL3rdPlayerProfile: Player Id not found [" + FirstPlayerId + "]";
			}
			
			if(matchAllData.getSetup().getHomeTeamId() == player.getTeamId()) {
				team = matchAllData.getSetup().getHomeTeam();
			} else if(matchAllData.getSetup().getAwayTeamId() == player.getTeamId()) {
				team = matchAllData.getSetup().getAwayTeam();
			} 
			
			if(team == null) {
				return "PopulateL3rdPlayerProfile: Team Id not found [" + player.getTeamId() + "]";
			}
			
			switch (config.getBroadcaster().toUpperCase()) {
			
			case Constants.TRI_SERIES:  case Constants.MT20: case Constants.BAN_AFG_SERIES: case Constants.ACC:
				switch (WhichProfile.toUpperCase()) {
				case "MAHARAJA_CAREER":
			         statsType = statsTypes.stream()
			            .filter(st -> st.getStatsShortName().equalsIgnoreCase("MAHARAJA_CAREER"))
			            .findAny().orElse(null);
			         
			         for(StatsType stt : statsTypes) {
			        	 	System.out.println("stt = " + stt);
			         }
			         
			        if (statsType == null) {
			            return "InfoBarPlayerProfile: Stats Type not found for profile [" + WhichProfile + "]";
			        }
			        
			        stat = statistics.stream()
			            .filter(st -> st.getPlayerID() == FirstPlayerId && statsType.getStatsId() == st.getStatsTypeId())
			            .findAny().orElse(null);
			        if (stat == null) {
			            return "InfoBarPlayerProfile: Stats not found for Player Id [" + FirstPlayerId + "]";
			        }
			        
			        statsType = statsTypes.stream().filter(st -> st.getStatsShortName().equalsIgnoreCase("DT20")).findAny().orElse(null);
					stat.setStats_type(statsType);
			        stat = CricketFunctions.updateTournamentWithH2h(stat, headToHead, matchAllData, CricketUtil.FULL);
			        stat = CricketFunctions.updateStatisticsWithMatchData(stat, matchAllData, CricketUtil.FULL);
			        break;
				case "DT20": case "IT20":
					
					switch (WhichProfile.toUpperCase()) {
					case "DT20":
//						matchAllData.getSetup().setMatchType("DT20");
						
						statsType = statsTypes.stream().filter(st -> st.getStatsShortName().equalsIgnoreCase("DT20")).findAny().orElse(null);
						
						break;
					case "IT20":
//						matchAllData.getSetup().setMatchType("IT20");
						
						statsType = statsTypes.stream().filter(st -> st.getStatsShortName().equalsIgnoreCase("IT20")).findAny().orElse(null);
						
						break;	
					}
					if(statsType == null) {
						return "PopulateL3rdPlayerProfile: Stats Type not found for profile [" + WhichProfile + "]";
					}
					
					stat = statistics.stream().filter(st -> st.getPlayerID() == FirstPlayerId && statsType.getStatsId() == st.getStatsTypeId()).findAny().orElse(null);
					if(stat == null) {
						return "PopulateL3rdPlayerProfile: Stats not found for Player Id [" + FirstPlayerId + "]";
					}
					
					stat.setStats_type(statsType);
					stat = CricketFunctions.updateTournamentWithH2h(stat, headToHead, matchAllData, CricketUtil.FULL);
					
//					if(matchAllData.getSetup().getMatchType().equalsIgnoreCase(CricketUtil.IT20)) {
//						switch (WhichProfile.toUpperCase()) {
//						case "DT20":
//							matchAllData.getSetup().setMatchType(CricketUtil.DT20);
//							break;
//						}
//						stat = CricketFunctions.updateStatisticsWithMatchData(stat, matchAllData, CricketUtil.FULL);
//						if(WhichProfile.equalsIgnoreCase(CricketUtil.DT20)) {
//							matchAllData.getSetup().setMatchType(CricketUtil.IT20);
//						}
//					}else if(matchAllData.getSetup().getMatchType().equalsIgnoreCase(CricketUtil.DT20)) {
//						stat = CricketFunctions.updateStatisticsWithMatchData(stat, matchAllData, CricketUtil.FULL);
//					}
					
					
					break;
				case "TEST": case "ODI": case "LIST A":
					System.out.println("WhichProfile = " + WhichProfile);
					switch (WhichProfile.toUpperCase()) {
					case "TEST":
						matchAllData.getSetup().setMatchType("TEST");
						
						statsType = statsTypes.stream().filter(st -> st.getStatsShortName().equalsIgnoreCase("TEST")).findAny().orElse(null);
						
						break;
					case "ODI":
//						matchAllData.getSetup().setMatchType("ODI");
						
						statsType = statsTypes.stream().filter(st -> st.getStatsShortName().equalsIgnoreCase("ODI")).findAny().orElse(null);
						
						break;
					case "LIST A":
//						matchAllData.getSetup().setMatchType("ODI");
						
						statsType = statsTypes.stream().filter(st -> st.getStatsShortName().equalsIgnoreCase(WhichProfile.toUpperCase())).findAny().orElse(null);
						
						break;	
					}
					
					if(statsType == null) {
						return "PopulateL3rdPlayerProfile: Stats Type not found for profile [" + WhichProfile + "]";
					}
					
					stat = statistics.stream().filter(st -> st.getPlayerID() == FirstPlayerId && statsType.getStatsId() == st.getStatsTypeId()).findAny().orElse(null);
					if(stat == null) {
						return "PopulateL3rdPlayerProfile: Stats not found for Player Id [" + FirstPlayerId + "]";
					}
					//Comment this when we do not add current match data
					statsType = statsTypes.stream().filter(st -> st.getStatsShortName().equalsIgnoreCase("ODI")).findAny().orElse(null);
					stat.setStats_type(statsType);
					stat = CricketFunctions.updateTournamentWithH2h(stat, headToHead, matchAllData, CricketUtil.FULL);
					
					if(matchAllData.getSetup().getMatchType().equalsIgnoreCase(CricketUtil.ODI) || 
							matchAllData.getSetup().getMatchType().equalsIgnoreCase(CricketUtil.OD)) {
						stat = CricketFunctions.updateStatisticsWithMatchData(stat, matchAllData, CricketUtil.FULL);
					}
					break;	
				}
				break;
			}
			
			
			double average = 0;
			String Data = "",hundred = "",fifty = "",strikeRate = "", thirty = "",
				batAverage = "",economy = "",best = "",runs = "",short_name = "";;
			
			if(stat.getRunsConceded() == 0 || stat.getWickets() == 0) {
				Data = "-";
			}else {
				average = stat.getRunsConceded()/stat.getWickets();
				DecimalFormat df_bo = new DecimalFormat("0.00");
				Data = df_bo.format(average);
			}
			
			if(whatToProcess.split(",")[0].equalsIgnoreCase("F7")) {
				if(stat.getFifties() == 0) {
					fifty = "-";
				}else {
					fifty = String.valueOf(stat.getFifties());
				}
				
				if(stat.getThirties() == 0) {
					thirty = "-";
				}else {
					thirty = String.valueOf(stat.getThirties());
				}
				
				if(stat.getHundreds() == 0) {
					hundred = "-";
				}else {
					hundred = String.valueOf(stat.getHundreds());
				}
				
				if(fifty.equalsIgnoreCase("-") && !hundred.equalsIgnoreCase("-")) {
					fifty = "0";
				}
				
				if(hundred.equalsIgnoreCase("-") && !fifty.equalsIgnoreCase("-")) {
					hundred = "0";
				}
				
				if(CricketFunctions.generateStrikeRate(stat.getRuns(), 
						stat.getBallsFaced(), 1).trim().isEmpty()) {
					strikeRate = "-";
				}else {
					strikeRate = String.valueOf(CricketFunctions.generateStrikeRate(stat.getRuns(), stat.getBallsFaced(), 0));
				}
				
				if(stat.getRuns() == 0) {
					runs = "-";
				}else {
					runs = String.format("%,d\n", stat.getRuns());
				}
				
				if(CricketFunctions.getAverage(stat.getInnings(), stat.getNotOut(), stat.getRuns(), 2, "-").equalsIgnoreCase("0.00")) {
					batAverage = "-";
				}else {
					batAverage = CricketFunctions.getAverage(stat.getInnings(), stat.getNotOut(), stat.getRuns(), 2, "-");
				}
				
				if(player.getSurname() == null) {
					surName = "";
				}else {
					surName = player.getSurname();
				}
				
				if(stat.getBestScore().equalsIgnoreCase("0")) {
					best = "-";
				}else {
					best = stat.getBestScore();
				}
				
				switch (config.getBroadcaster().toUpperCase()) {
				case Constants.TRI_SERIES:  case Constants.MT20: case Constants.BAN_AFG_SERIES: case Constants.ACC:
					if(WhichProfile.equalsIgnoreCase("DT20")) {
						short_name =  "T20 CAREER";
					}else if(WhichProfile.equalsIgnoreCase("IT20")) {
						short_name =  "T20I CAREER";
					}else if(WhichProfile.equalsIgnoreCase("ODI")) {
						short_name =  "ODI CAREER";
					}else if(WhichProfile.equalsIgnoreCase("TEST")) {
						short_name =  "TEST CAREER";
					}else if(WhichProfile.equalsIgnoreCase("LIST A")) {
						short_name =  "LIST A CAREER";
					}else if(WhichProfile.equalsIgnoreCase("MAHARAJA_CAREER")) {
						short_name =  "MAHARAJA T20 CAREER";
					}else {
						short_name = "T20 CAREER";
					}
					switch (config.getBroadcaster().toUpperCase()) {
					
					case Constants.TRI_SERIES:  case Constants.MT20: case Constants.BAN_AFG_SERIES: case Constants.ACC:
						
						if(WhichProfile.equalsIgnoreCase("DT20") || WhichProfile.equalsIgnoreCase("IT20") || 
								WhichProfile.equalsIgnoreCase("MAHARAJA_CAREER")) {
							lowerThird = new LowerThird(CricketFunctions.getbattingstyle(player.getBattingStyle(),
									CricketUtil.FULL, true, false).toUpperCase(), player.getFirstname(), surName,short_name, "", "", 2,"",team.getTeamBadge(),
									new String[]{"MATCHES", "RUNS", "50s / 100s", "BEST", "STRIKE RATE"},
									new String[]{String.valueOf(stat.getMatches()), runs , fifty + " / " + hundred, best,strikeRate},null,new String[] {"WITHOUT"},
									new String[] {"-165","-90","-8","72","157"});
						}else if(WhichProfile.equalsIgnoreCase("ODI") || WhichProfile.equalsIgnoreCase("LIST A")) {
							lowerThird = new LowerThird(CricketFunctions.getbattingstyle(player.getBattingStyle(),
									CricketUtil.FULL, true, false).toUpperCase(), player.getFirstname(), surName,short_name, "", "", 2,"",team.getTeamBadge(),
									new String[]{"MATCHES", "RUNS", "50s / 100s", "BEST", "AVERAGE"},
									new String[]{String.valueOf(stat.getMatches()), runs , fifty + " / " + hundred, best,batAverage},null,new String[] {"WITHOUT"},
									new String[] {"-165","-90","-8","72","157"});
						}
						break;
					
					}
					break;
				}
				
				
			}	
			else if(whatToProcess.split(",")[0].equalsIgnoreCase("F11")) {
				if(CricketFunctions.getEconomy(stat.getRunsConceded(), stat.getBallsBowled(),2,"-").equalsIgnoreCase("0.00")) {
					economy = "-";
				}else {
					economy = CricketFunctions.getEconomy(stat.getRunsConceded(), stat.getBallsBowled(),2,"-");
				}
				
				if(player.getSurname() == null) {
					surName = "";
				}else {
					surName = player.getSurname();
				}
				
				switch (config.getBroadcaster().toUpperCase()) {
				
				case Constants.TRI_SERIES:  case Constants.MT20: case Constants.BAN_AFG_SERIES: case Constants.ACC:
					if(WhichProfile.equalsIgnoreCase("DT20")) {
						short_name =  "T20 CAREER";
					}else if(WhichProfile.equalsIgnoreCase("IT20")) {
						short_name =  "T20I CAREER";
					}else if(WhichProfile.equalsIgnoreCase("ODI")) {
						short_name =  "ODI CAREER";
					}else if(WhichProfile.equalsIgnoreCase("TEST")) {
						short_name =  "TEST CAREER";
					}else if(WhichProfile.equalsIgnoreCase("LIST A")) {
						short_name =  "LIST A CAREER";
					}else if(WhichProfile.equalsIgnoreCase("MAHARAJA_CAREER")) {
						short_name =  "MAHARAJA T20 CAREER";
					}else {
						short_name = "T20I CAREER";
					}
					
					lowerThird = new LowerThird(CricketFunctions.getbattingstyle(player.getBattingStyle(),CricketUtil.FULL, true, false).toUpperCase(), 
							player.getFirstname(), surName,short_name, "", "", 2,"",team.getTeamBadge(),new String[]{"MATCHES", "WICKETS", "3WI / 5WI", "BEST", "ECONOMY"},
							new String[]{String.valueOf(stat.getMatches()), String.valueOf(stat.getWickets()),String.valueOf(stat.getPlus3()) +" / "+ String.valueOf(stat.getPlus5()), 
							stat.getBestFigures(),economy},null,null,new String[] {"-165","-77","11","83","163"});
					
					
					break;
				
				}
			}
			
			status = PopulateL3rdHeader(whatToProcess.split(",")[0],WhichSide);
			if(status == Constants.OK) {
				PopulateL3rdBaseColor(whatToProcess.split(",")[0], WhichSide);
				HideAndShowL3rdSubStrapContainers(WhichSide);
				setPositionOfLT(whatToProcess,WhichSide,config,lowerThird.getNumberOfSubLines());

				return PopulateL3rdBody(WhichSide,whatToProcess.split(",")[0]);
			} else {
				return status;
			}
		}
	public String populateL3rdThisSeries(String whatToProcess,int WhichSide,MatchAllData matchAllData) throws InterruptedException, IOException
	{
		String teamName = "",best = "-",economy = "",batAverage = "",
				bat_sr = "",hundred = "", fifties = "",Data = "",thirty = "";
		int k =0;
		double average = 0,economy_rate=0;
		
		if(whatToProcess.split(",")[3].equalsIgnoreCase("WITHOUT_CURRENT")) {
			typeData = "WITHOUT_CURRENT";
			addPastDataToCurr = past_tournament_stats;
		}else if(whatToProcess.split(",")[3].equalsIgnoreCase("WITH_CURRENT")) {
			typeData = "WITH_CURRENT";
			addPastDataToCurr = CricketFunctions.extractTournamentData("CURRENT_MATCH_DATA", false, headToHead,cricketService, matchAllData, past_tournament_stats);
		}
		
		if(addPastDataToCurr == null) {
			return "addPastDataToCurr is Null";
		}
		
		for(Tournament tourn : addPastDataToCurr) {
			for(BestStats bs : tourn.getBatsman_best_Stats()) {
				top_batsman_beststats.add(bs);
			}
			for(BestStats bfig : tourn.getBowler_best_Stats()) {
				top_bowler_beststats.add(bfig);
			}
		}
		
		Collections.sort(top_batsman_beststats, new CricketFunctions.PlayerBestStatsComparator());
		Collections.sort(top_bowler_beststats, new CricketFunctions.PlayerBestStatsComparator());
		
		tournament = addPastDataToCurr.stream().filter(tourn -> tourn.getPlayerId() == Integer.valueOf(whatToProcess.split(",")[2])).findAny().orElse(null);
		player = Players.stream().filter(plyr -> plyr.getPlayerId() == Integer.valueOf(whatToProcess.split(",")[2])).findAny().orElse(null);
		team = Teams.stream().filter(tm -> tm.getTeamId() == tournament.getPlayer().getTeamId()).findAny().orElse(null);
		teamName = team.getTeamBadge();
		
		if(tournament.getPlayer().getSurname() == null) {
			surName = "";
		}else {
			surName = tournament.getPlayer().getSurname();
		}
		
		if(whatToProcess.split(",")[0].equalsIgnoreCase("Control_s")) {
			
			for(int j=0;j<= top_batsman_beststats.size()-1;j++) {
				if(top_batsman_beststats.get(j).getPlayerId() == Integer.valueOf(whatToProcess.split(",")[2])) {
					if(k == 0) {
						k += 1;
						if(top_batsman_beststats.get(j).getBestEquation() % 2 == 0) {
							if(top_batsman_beststats.get(j).getBestEquation()/2 == 0) {
								best = "-";
							}else {
								best = String.valueOf(top_batsman_beststats.get(j).getBestEquation()/2);
							}
						}else {
							best = (top_batsman_beststats.get(j).getBestEquation()-1) / 2 + "*";
						}
						break;
					}
				}else {
					best = "-";
				}
			}
			
			if(CricketFunctions.generateStrikeRate(tournament.getRuns(), 
					tournament.getBallsFaced(), 1).trim().isEmpty()) {
				bat_sr = "-"; 
			}else {
				bat_sr = CricketFunctions.generateStrikeRate(tournament.getRuns(), tournament.getBallsFaced(), 0);
			}
			
			if(CricketFunctions.getAverage(tournament.getInnings(), tournament.getNot_out(), tournament.getRuns(), 2, "-").equalsIgnoreCase("0.00")) {
				batAverage = "-";
			}else {
				batAverage = CricketFunctions.getAverage(tournament.getInnings(), tournament.getNot_out(), tournament.getRuns(), 2, "-");
			}
			
			if(tournament.getThirty() == 0) {
				thirty = "-";
			}else {
				thirty = String.valueOf(tournament.getThirty());
			}
			
			if(tournament.getFifty() == 0) {
				fifties = "-";
			}else {
				fifties = String.valueOf(tournament.getFifty());
			}
			
			if(tournament.getHundreds() == 0) {
				hundred = "-";
			}else {
				hundred = String.valueOf(tournament.getHundreds());
			}
			
			switch (config.getBroadcaster().toUpperCase()) {
			case Constants.TRI_SERIES:  case Constants.MT20: case Constants.BAN_AFG_SERIES: case Constants.ACC:
				
				if(matchAllData.getSetup().getMatchType().equalsIgnoreCase(CricketUtil.ODI) || 
						matchAllData.getSetup().getMatchType().equalsIgnoreCase(CricketUtil.OD)) {
					lowerThird = new LowerThird(CricketFunctions.getbattingstyle(player.getBattingStyle(),
							CricketUtil.FULL, true, false).toUpperCase(), tournament.getPlayer().getFirstname(), surName, "THIS SERIES", "", "", 2, "",
							teamName, new String[]{"MATCHES", "RUNS", "50s / 100s", "AVERAGE", "BEST"},new String[]{String.valueOf(tournament.getMatches()), 
							String.format("%,d\n", tournament.getRuns()), fifties + " / " + hundred,batAverage,best},null,null,
							new String[] {"-165","-80","7","92","176"});
				}else {
					if(hundred != "-") {
						lowerThird = new LowerThird(CricketFunctions.getbattingstyle(player.getBattingStyle(),
								CricketUtil.FULL, true, false).toUpperCase(), tournament.getPlayer().getFirstname(), surName,"THIS SERIES", "", "", 2,"",teamName,
								new String[]{"MATCHES", "RUNS", "50s / 100s", "STRIKE RATE", "BEST"},new String[]{String.valueOf(tournament.getMatches()), 
								String.format("%,d\n", tournament.getRuns()), fifties + " / " + hundred,bat_sr,best},null,null,
								new String[] {"-165","-80","7","92","176"});
					}else {
						lowerThird = new LowerThird(CricketFunctions.getbattingstyle(player.getBattingStyle(),
								CricketUtil.FULL, true, false).toUpperCase(), tournament.getPlayer().getFirstname(), surName,"THIS SERIES", "", "", 2,"",teamName,
								new String[]{"MATCHES", "RUNS", "30s/50s", "STRIKE RATE", "BEST"},new String[]{String.valueOf(tournament.getMatches()), 
								String.format("%,d\n", tournament.getRuns()), thirty + " / " + fifties,bat_sr,best},null,null,
								new String[] {"-165","-80","7","92","176"});
					}
				}
				break;
			}
		}else if(whatToProcess.split(",")[0].equalsIgnoreCase("Control_f")) {
			
			for(int j=0;j<= top_bowler_beststats.size()-1;j++) {
				if(top_bowler_beststats.get(j).getPlayerId() == Integer.valueOf(whatToProcess.split(",")[2])) {
					if(k == 1) {
						break;
					}
					if(k == 0) {
						k += 1;
						if(top_bowler_beststats.get(j).getBestEquation() % 1000 > 0) {
							best = ((top_bowler_beststats.get(j).getBestEquation() / 1000) +1) + "-" + (1000 - (top_bowler_beststats.get(j).getBestEquation() % 1000));
						}
						else if(top_bowler_beststats.get(j).getBestEquation() % 1000 < 0) {
							best = (top_bowler_beststats.get(j).getBestEquation() / 1000) + "-" + Math.abs(top_bowler_beststats.get(j).getBestEquation());
						}
						else if(top_bowler_beststats.get(j).getBestEquation() != 0) {
							if(top_bowler_beststats.get(j).getBestEquation() % 1000 == 0) {
								best = (top_bowler_beststats.get(j).getBestEquation() / 1000) + "-" + "0";
							}
						}
						break;
					}
				}else if(top_bowler_beststats.get(j).getPlayerId() != Integer.valueOf(whatToProcess.split(",")[2])) {
					best = "-";
				}
			}
			
			if(tournament.getRunsConceded() == 0 && tournament.getBallsBowled() == 0) {
				economy = "-";
			}else {
				economy_rate = (Double.valueOf(tournament.getRunsConceded())) / tournament.getBallsBowled();
				economy_rate = economy_rate * 6;
				DecimalFormat df_b = new DecimalFormat("0.00");
				economy = df_b.format(economy_rate);
			}
			
			if(tournament.getRunsConceded() == 0 || tournament.getWickets() == 0) {
				Data = "-";
			}else {
				average = tournament.getRunsConceded()/tournament.getWickets();
				DecimalFormat df_bo = new DecimalFormat("0.00");
				Data = df_bo.format(average);
			}
			
			switch (config.getBroadcaster().toUpperCase()) {
			
			case Constants.TRI_SERIES:  case Constants.MT20: case Constants.BAN_AFG_SERIES: case Constants.ACC:
				lowerThird = new LowerThird(CricketFunctions.getbowlingstyle(player.getBowlingStyle()).toUpperCase(), tournament.getPlayer().getFirstname(), 
						surName,"THIS SERIES", "", "", 2,"",teamName,new String[]{"MATCHES", "WICKETS", "AVERAGE", "ECONOMY", "BEST"},
						new String[]{String.valueOf(tournament.getMatches()),String.valueOf(tournament.getWickets()),Data,economy,best},null,null,
						new String[] {"-165","-80","9","97","176"});
				break;
			}
		}
		
		status = PopulateL3rdHeader(whatToProcess.split(",")[0],WhichSide);
		if(status == Constants.OK) {
			PopulateL3rdBaseColor(whatToProcess.split(",")[0], WhichSide);
			HideAndShowL3rdSubStrapContainers(WhichSide);
			setPositionOfLT(whatToProcess,WhichSide,config,lowerThird.getNumberOfSubLines());

			return PopulateL3rdBody(WhichSide,whatToProcess.split(",")[0]);
		} else {
			return status;
		}
	}
	
	public String populateBatThisMatch(String whatToProcess,int WhichSide,MatchAllData matchAllData) throws InterruptedException, IOException
	{
		
		String outOrNot = "",striktRate = "";
		stat = null;
		if (matchAllData == null || matchAllData.getMatch() == null || matchAllData.getMatch().getInning() == null) {
			return status;
		} else {
			inning = matchAllData.getMatch().getInning().stream().filter(inn -> inn.getInningNumber() == Integer.valueOf(whatToProcess.split(",")[1])).findAny().orElse(null);
			if(inning == null) {
				return "populateBatThisMatch: Current Inning NOT found in this match";
			}
			battingCard = inning.getBattingCard().stream().filter(bc -> bc.getPlayerId() == Integer.valueOf(whatToProcess.split(",")[2])).findAny().orElse(null);
			if(battingCard == null) {
				return status;
			}
			if(battingCard.getStatus().equalsIgnoreCase(CricketUtil.NOT_OUT)) {
				outOrNot = "NOT OUT";
			}else {
				outOrNot = "";
			}
		}
		
		if(battingCard.getStrikeRate() !=null) {
			if(battingCard.getStrikeRate().trim().isEmpty()) {
				striktRate = "-";
			}else {
				striktRate = String.valueOf(CricketFunctions.generateStrikeRate(battingCard.getRuns(), battingCard.getBalls(), 0));
			}
		}else {
			striktRate = "-";
		}
		
		String[] Count = CricketFunctions.getScoreTypeData(CricketUtil.BATSMAN,matchAllData, inning.getInningNumber(), Integer.valueOf(whatToProcess.split(",")[2]),
				"-", matchAllData.getEventFile().getEvents()).split("-");
		
		if(battingCard.getPlayer().getSurname() == null) {
			surName = "";
		}else {
			surName = battingCard.getPlayer().getSurname();
		}
		
		if(CricketFunctions.isImpactPlayer(matchAllData.getEventFile().getEvents(), inning.getInningNumber(), 
				Integer.valueOf(whatToProcess.split(",")[2])).equalsIgnoreCase(CricketUtil.YES)) {
			impactOmo = 1;
			setImpact(true);
		} else {
			impactOmo =0;
			setImpact(false);
		}
		
		if(whatToProcess.contains("with_photo")) {
			photo = true;
		}else {
			photo = false;
		}
			
		switch (config.getBroadcaster().toUpperCase()) {
		
		case Constants.TRI_SERIES:  case Constants.MT20: case Constants.BAN_AFG_SERIES: case Constants.ACC:
			lowerThird = new LowerThird("", battingCard.getPlayer().getFirstname(), surName,outOrNot, String.valueOf(battingCard.getRuns()),
					String.valueOf(battingCard.getBalls()), 2, "",inning.getBatting_team().getTeamBadge(),new String[] {"DOTS","FOURS","SIXES","STRIKE RATE"},new String[] {
					Count[0],String.valueOf(battingCard.getFours()),String.valueOf(battingCard.getSixes()),striktRate},null,new String[] {"WITHOUT"},new String[] {"-178","-73","41","160"});
			break;
		}
		status = PopulateL3rdHeader(whatToProcess.split(",")[0],WhichSide);
		if(status == Constants.OK) {
			PopulateL3rdBaseColor(whatToProcess.split(",")[0], WhichSide);
			HideAndShowL3rdSubStrapContainers(WhichSide);
			setPositionOfLT(whatToProcess,WhichSide,config,lowerThird.getNumberOfSubLines());
			
			return PopulateL3rdBody(WhichSide, whatToProcess.split(",")[0]);
		} else {
			return status;
		}
	}
	public String populateBowlThisMatch(String whatToProcess,int WhichSide,MatchAllData matchAllData) throws InterruptedException, JsonMappingException, JsonProcessingException
	{
		String economy = "";
		stat = null;
		if (matchAllData == null || matchAllData.getMatch() == null || matchAllData.getMatch().getInning() == null) {
			return status;
		} else {
			inning = matchAllData.getMatch().getInning().stream().filter(inn -> inn.getInningNumber() == Integer.valueOf(whatToProcess.split(",")[1])).findAny().orElse(null);
			if(inning == null) {
				return "populateBallThisMatch: Current Inning NOT found in this match";
			}
			bowlingCard = inning.getBowlingCard().stream().filter(boc -> boc.getPlayerId() == Integer.valueOf(whatToProcess.split(",")[2])).findAny().orElse(null);
			if(bowlingCard == null) {
				return status;
			}
		}
		if(whatToProcess.contains("with_photo")) {
			photo = true;
		}else {
			photo = false;
		}
		if(bowlingCard.getEconomyRate()==null || bowlingCard.getEconomyRate().equalsIgnoreCase("0.00")) {
			economy = "-";
		}else {
			economy = String.valueOf(bowlingCard.getEconomyRate());
		}
		
		if(bowlingCard.getPlayer().getSurname() == null) {
			surName = "";
		}else {
			surName = bowlingCard.getPlayer().getSurname();
		}
		
		if(CricketFunctions.isImpactPlayer(matchAllData.getEventFile().getEvents(), inning.getInningNumber(), 
				Integer.valueOf(whatToProcess.split(",")[2])).equalsIgnoreCase(CricketUtil.YES)) {
			impactOmo = 1;
			setImpact(true);
		} else {
			impactOmo =0;
			setImpact(false);
		}
		
		switch (config.getBroadcaster().toUpperCase()) {
		case Constants.TRI_SERIES:  case Constants.MT20: case Constants.BAN_AFG_SERIES: case Constants.ACC:
			String[] TitleData = null;
			String[] StatData = null;
			
			switch (matchAllData.getSetup().getMatchType()) {
			case CricketUtil.ODI: case CricketUtil.TEST:  case CricketUtil.OD:
				TitleData = new String[] {"OVERS", "MAIDENS", "RUNS", "WICKETS", "ECONOMY"};
				StatData = new String[]{CricketFunctions.OverBalls(bowlingCard.getOvers(), bowlingCard.getBalls()), 
						String.valueOf(bowlingCard.getMaidens()),String.valueOf(bowlingCard.getRuns()),String.valueOf(bowlingCard.getWickets()), economy};
				break;
			default:
				TitleData = new String[] {"OVERS", "DOTS", "RUNS", "WICKETS", "ECONOMY"};
				StatData = new String[]{CricketFunctions.OverBalls(bowlingCard.getOvers(), bowlingCard.getBalls()), 
						String.valueOf(bowlingCard.getDots()),String.valueOf(bowlingCard.getRuns()),String.valueOf(bowlingCard.getWickets()), economy};
				break;
			}
			
			player = bowlingCard.getPlayer();
			lowerThird = new LowerThird("THIS MATCH", bowlingCard.getPlayer().getFirstname(), surName,"", "","", 2, "",inning.getBowling_team().getTeamBadge(),
					TitleData,StatData,null,null,new String[] {"-174","-100","-20","64","165"});
			break;
		}
		
		status = PopulateL3rdHeader(whatToProcess.split(",")[0],WhichSide);
		if(status == Constants.OK) {
			PopulateL3rdBaseColor(whatToProcess.split(",")[0], WhichSide);
			HideAndShowL3rdSubStrapContainers(WhichSide);
			setPositionOfLT(whatToProcess,WhichSide,config,lowerThird.getNumberOfSubLines());
			
			return PopulateL3rdBody(WhichSide, whatToProcess.split(",")[0]);
		} else {
			return status;
		}
	}
	public String populateFOW(String whatToProcess,int WhichSide,MatchAllData matchAllData) throws InterruptedException
	{
		inning = matchAllData.getMatch().getInning().stream().filter(inn -> inn.getInningNumber() == Integer.valueOf(whatToProcess.split(",")[1]))
				.findAny().orElse(null);
		if(inning == null) {
			return "populateFOW: Current Inning NOT found in this match";
		}else {
			String[] fowData = new String[inning.getTotalWickets()];
			String[] fowNumber = new String[inning.getTotalWickets()];
			
			if(inning.getFallsOfWickets() == null || inning.getFallsOfWickets().size() <= 0) {
				lowerThird = new LowerThird("FALL OF WICKETS", inning.getBatting_team().getTeamName3(), "",CricketFunctions.OverBalls(inning.getTotalOvers(), inning.getTotalBalls()), 
						String.valueOf(inning.getTotalRuns() + "-" + inning.getTotalWickets()), "",
					2,"",inning.getBatting_team().getTeamBadge(),new String[]{" "},new String[]{" "},new String[]{"WICKETS","SCORE"},null,
					new String[] {"-136","-105","-69","-32","4","42","78","115","149","184"});
			}
			else if(inning.getFallsOfWickets() != null || inning.getFallsOfWickets().size() > 0) {
				for(int fow_id=0;fow_id<inning.getFallsOfWickets().size();fow_id++) {
					fowData[fow_id] = String.valueOf(inning.getFallsOfWickets().get(fow_id).getFowRuns());
					fowNumber[fow_id] = String.valueOf(fow_id+1);
				}
			}
			
			switch (config.getBroadcaster().toUpperCase()) {
			
			case Constants.TRI_SERIES:  case Constants.MT20: case Constants.BAN_AFG_SERIES: case Constants.ACC:
				lowerThird = new LowerThird("FALL OF WICKETS", inning.getBatting_team().getTeamName1(), "",CricketFunctions.OverBalls(inning.getTotalOvers(), inning.getTotalBalls()), 
						String.valueOf(inning.getTotalRuns()), String.valueOf(inning.getTotalWickets()),
						2,"",inning.getBatting_team().getTeamBadge(),fowNumber,fowData,new String[]{"WICKETS","SCORE"},null,
						new String[] {"-136","-105","-69","-32","4","42","78","115","149","184"});
				break;
			}
			
			status = PopulateL3rdHeader(whatToProcess.split(",")[0],WhichSide);
			if(status == Constants.OK) {
				PopulateL3rdBaseColor(whatToProcess.split(",")[0], WhichSide);
				HideAndShowL3rdSubStrapContainers(WhichSide);
				setPositionOfLT(whatToProcess,WhichSide,config,lowerThird.getNumberOfSubLines());
				return PopulateL3rdBody(WhichSide, whatToProcess.split(",")[0]);
			} else {
				return status;
			}
		}
	}
	public String populate30_50Split(String whatToProcess,int WhichSide,MatchAllData matchAllData) throws InterruptedException
	{
		
		PlayerProgression(34, matchAllData, matchAllData.getEventFile().getEvents());
		String whichSplit = "";
		System.out.println(whatToProcess);
		String[] splitData = new String[CricketFunctions.getSplit(Integer.valueOf(whatToProcess.split(",")[1]), Integer.valueOf(whatToProcess.split(",")[2]),
			matchAllData,matchAllData.getEventFile().getEvents()).size()];
		String[] splitNumber = new String[CricketFunctions.getSplit(Integer.valueOf(whatToProcess.split(",")[1]), Integer.valueOf(whatToProcess.split(",")[2]),
			matchAllData,matchAllData.getEventFile().getEvents()).size()];
		
		inning = matchAllData.getMatch().getInning().stream().filter(inn -> inn.getInningNumber() == Integer.valueOf(whatToProcess.split(",")[1]))
			.findAny().orElse(null);
		
		if(inning == null) {
			return "populate50-100: Current Inning NOT found in this match";
		}
		
		if (inning.getBowlingTeamId() == matchAllData.getSetup().getHomeTeamId() && Integer.valueOf(whatToProcess.split(",")[2]) == 30 || 
				inning.getBowlingTeamId() == matchAllData.getSetup().getHomeTeamId() && Integer.valueOf(whatToProcess.split(",")[2]) == 50 ||
				inning.getBowlingTeamId() == matchAllData.getSetup().getHomeTeamId() && Integer.valueOf(whatToProcess.split(",")[2]) == 100) {
			
			for(int split_id=0;split_id<CricketFunctions.getSplit(Integer.valueOf(whatToProcess.split(",")[1]), Integer.valueOf(whatToProcess.split(",")[2]),
					matchAllData,matchAllData.getEventFile().getEvents()).size();split_id++) {
				splitData[split_id] = CricketFunctions.getSplit(Integer.valueOf(whatToProcess.split(",")[1]), Integer.valueOf(whatToProcess.split(",")[2])
						,matchAllData,matchAllData.getEventFile().getEvents()).get(split_id);
				splitNumber[split_id] = String.valueOf(split_id+1);
				System.out.println(splitData[split_id]);
	    	}
			
			if(Integer.valueOf(whatToProcess.split(",")[2]) == 30) {
				whichSplit = "BALLS PER THIRTY";
				switch (config.getBroadcaster().toUpperCase()) {
				
				case Constants.TRI_SERIES:  case Constants.MT20:
					lowerThird = new LowerThird("", matchAllData.getSetup().getAwayTeam().getTeamName1(), "",whichSplit, String.valueOf(inning.getTotalRuns() + "-" + inning.getTotalWickets()), 
							String.valueOf(CricketFunctions.OverBalls(inning.getTotalOvers(), inning.getTotalBalls())),
							2,"",matchAllData.getSetup().getAwayTeam().getTeamName4(),splitNumber,splitData,new String[]{"THIRTIES","BALLS"},null,
							new String[] {"-129","-90","-52","-12","26","66","103","140","170","191"});
					break;
				case Constants.BAN_AFG_SERIES: case Constants.ACC:
					lowerThird = new LowerThird("", matchAllData.getSetup().getAwayTeam().getTeamName1(), "",whichSplit, String.valueOf(inning.getTotalRuns() + "-" + inning.getTotalWickets()), 
							String.valueOf(CricketFunctions.OverBalls(inning.getTotalOvers(), inning.getTotalBalls())),
							2,"",matchAllData.getSetup().getAwayTeam().getTeamBadge(),splitNumber,splitData,new String[]{"THIRTY","BALLS"},null,
							new String[] {"-129","-90","-52","-12","26","66","103","140","170","191"});
					break;	
				}
			}else if(Integer.valueOf(whatToProcess.split(",")[2]) == 50) {
				whichSplit = "BALLS PER FIFTY";
				switch (config.getBroadcaster().toUpperCase()) {
				case Constants.TRI_SERIES:  case Constants.MT20:
					lowerThird = new LowerThird("", matchAllData.getSetup().getAwayTeam().getTeamName1(), "",whichSplit, String.valueOf(inning.getTotalRuns() + "-" + inning.getTotalWickets()), 
							String.valueOf(CricketFunctions.OverBalls(inning.getTotalOvers(), inning.getTotalBalls())),
							2,"",matchAllData.getSetup().getAwayTeam().getTeamName4(),splitNumber,splitData,new String[]{"FIFTIES","BALLS"},null,
							new String[] {"-117","-72","-27","19","64","111","158","190","450","450"});
					break;
				case Constants.BAN_AFG_SERIES: case Constants.ACC:
					lowerThird = new LowerThird("", matchAllData.getSetup().getAwayTeam().getTeamName1(), "",whichSplit, String.valueOf(inning.getTotalRuns() + "-" + inning.getTotalWickets()), 
							String.valueOf(CricketFunctions.OverBalls(inning.getTotalOvers(), inning.getTotalBalls())),
							2,"",matchAllData.getSetup().getAwayTeam().getTeamBadge(),splitNumber,splitData,new String[]{"50s","BALLS"},null,
							new String[] {"-117","-72","-27","19","64","111","158","190","450","450"});
					break;	
				}
			}else {
				whichSplit = "BALLS PER HUNDRED";
				switch (config.getBroadcaster().toUpperCase()) {
				case Constants.TRI_SERIES:  case Constants.MT20:
					lowerThird = new LowerThird("", matchAllData.getSetup().getAwayTeam().getTeamName1(), "",whichSplit, String.valueOf(inning.getTotalRuns() + "-" + inning.getTotalWickets()), 
							String.valueOf(CricketFunctions.OverBalls(inning.getTotalOvers(), inning.getTotalBalls())),
							2,"",matchAllData.getSetup().getAwayTeam().getTeamName4(),splitNumber,splitData,new String[]{"HUNDREDS","BALLS"},null,
							new String[] {"-117","-72","-27","19","64","111","158","190","450","450"});
					break;
				case Constants.BAN_AFG_SERIES: case Constants.ACC:
					lowerThird = new LowerThird("", matchAllData.getSetup().getAwayTeam().getTeamName1(), "",whichSplit, String.valueOf(inning.getTotalRuns() + "-" 
							+ inning.getTotalWickets()), String.valueOf(CricketFunctions.OverBalls(inning.getTotalOvers(), inning.getTotalBalls())),
							2,"",matchAllData.getSetup().getAwayTeam().getTeamBadge(),splitNumber,splitData,new String[]{"100s","BALLS"},null,
							new String[] {"-117","-72","-27","19","64","111","158","190","450","450"});
					break;	
				}
			}
		}else {
			
			for(int split_id=0;split_id<CricketFunctions.getSplit(Integer.valueOf(whatToProcess.split(",")[1]), Integer.valueOf(whatToProcess.split(",")[2]),
					matchAllData,matchAllData.getEventFile().getEvents()).size();split_id++) {
				splitData[split_id] = CricketFunctions.getSplit(Integer.valueOf(whatToProcess.split(",")[1]), Integer.valueOf(whatToProcess.split(",")[2])
						,matchAllData,matchAllData.getEventFile().getEvents()).get(split_id);
				splitNumber[split_id] = String.valueOf(split_id+1);
			}
			
			if(Integer.valueOf(whatToProcess.split(",")[2]) == 30) {
				whichSplit = "BALLS PER THIRTY";
				switch (config.getBroadcaster().toUpperCase()) {
				
				case Constants.TRI_SERIES:  case Constants.MT20:
					lowerThird = new LowerThird("", matchAllData.getSetup().getHomeTeam().getTeamName1(), "",whichSplit, String.valueOf(inning.getTotalRuns() + "-" + inning.getTotalWickets()), 
							String.valueOf(CricketFunctions.OverBalls(inning.getTotalOvers(), inning.getTotalBalls())),
							2,"",matchAllData.getSetup().getHomeTeam().getTeamName4(),splitNumber,splitData,new String[]{"THIRTIES","BALLS"},null,
							new String[] {"-129","-90","-52","-12","26","66","103","140","170","191"});
					break;
				case Constants.BAN_AFG_SERIES: case Constants.ACC:
					lowerThird = new LowerThird("", matchAllData.getSetup().getHomeTeam().getTeamName1(), "",whichSplit, String.valueOf(inning.getTotalRuns() + "-" + inning.getTotalWickets()), 
							String.valueOf(CricketFunctions.OverBalls(inning.getTotalOvers(), inning.getTotalBalls())),
							2,"",matchAllData.getSetup().getHomeTeam().getTeamBadge(),splitNumber,splitData,new String[]{"THIRTY","BALLS"},null,
							new String[] {"-129","-90","-52","-12","26","66","103","140","170","191"});
					break;	
				}
			}else if(Integer.valueOf(whatToProcess.split(",")[2]) == 50) {
				whichSplit = "BALLS PER FIFTY";
				switch (config.getBroadcaster().toUpperCase()) {
				case Constants.TRI_SERIES:  case Constants.MT20:
					lowerThird = new LowerThird("", matchAllData.getSetup().getHomeTeam().getTeamName1(), "",whichSplit, String.valueOf(inning.getTotalRuns() + "-" + inning.getTotalWickets()), 
							String.valueOf(CricketFunctions.OverBalls(inning.getTotalOvers(), inning.getTotalBalls())),
							2,"",matchAllData.getSetup().getHomeTeam().getTeamName4(),splitNumber,splitData,new String[]{"FIFTIES","BALLS"},null,
							new String[] {"-117","-72","-27","19","64","111","158","190","450","450"});
					break;
				case Constants.BAN_AFG_SERIES: case Constants.ACC:
					lowerThird = new LowerThird("", matchAllData.getSetup().getHomeTeam().getTeamName1(), "",whichSplit, String.valueOf(inning.getTotalRuns() + "-" + inning.getTotalWickets()), 
							String.valueOf(CricketFunctions.OverBalls(inning.getTotalOvers(), inning.getTotalBalls())),
							2,"",matchAllData.getSetup().getHomeTeam().getTeamBadge(),splitNumber,splitData,new String[]{"50s","BALLS"},null,
							new String[] {"-117","-72","-27","19","64","111","158","190","450","450"});
					break;	
				}
			}else {
				whichSplit = "BALLS PER HUNDRED";
				switch (config.getBroadcaster().toUpperCase()) {
				case Constants.TRI_SERIES:  case Constants.MT20:
					lowerThird = new LowerThird("", matchAllData.getSetup().getHomeTeam().getTeamName1(), "",whichSplit, String.valueOf(inning.getTotalRuns() + "-" + inning.getTotalWickets()), 
							String.valueOf(CricketFunctions.OverBalls(inning.getTotalOvers(), inning.getTotalBalls())),
							2,"",matchAllData.getSetup().getHomeTeam().getTeamName4(),splitNumber,splitData,new String[]{"HUNDREDS","BALLS"},null,
							new String[] {"-117","-72","-27","19","64","111","158","190","450","450"});
					break;
				case Constants.BAN_AFG_SERIES: case Constants.ACC:
					lowerThird = new LowerThird("", matchAllData.getSetup().getHomeTeam().getTeamName1(), "",whichSplit, String.valueOf(inning.getTotalRuns() + "-" 
							+ inning.getTotalWickets()), String.valueOf(CricketFunctions.OverBalls(inning.getTotalOvers(), inning.getTotalBalls())),
							2,"",matchAllData.getSetup().getHomeTeam().getTeamBadge(),splitNumber,splitData,new String[]{"100s","BALLS"},null,
							new String[] {"-117","-72","-27","19","64","111","158","190","450","450"});
					break;	
				}
			}
		}
		
		status = PopulateL3rdHeader(whatToProcess.split(",")[0],WhichSide);
		if(status == Constants.OK) {
			PopulateL3rdBaseColor(whatToProcess.split(",")[0], WhichSide);
			HideAndShowL3rdSubStrapContainers(WhichSide);
			setPositionOfLT(whatToProcess,WhichSide,config,lowerThird.getNumberOfSubLines());

			return PopulateL3rdBody(WhichSide, whatToProcess.split(",")[0]);
		} else {
			return status;
		}
	}
	public String populateL3PlayerProgression(String whatToProcess,int WhichSide,MatchAllData matchAllData) throws InterruptedException
	{
		String[] playerData = new String[5];
		String[] splitNumber = new String[5];
		
		inning = matchAllData.getMatch().getInning().stream().filter(inn -> inn.getInningNumber() == Integer.valueOf(whatToProcess.split(",")[1]))
			.findAny().orElse(null);
		
		if(inning == null) {
			return "populateProgression: Current Inning NOT found in this match";
		}
		
		battingCard = inning.getBattingCard().stream().filter(bc -> bc.getPlayerId() == Integer.valueOf(whatToProcess.split(",")[2])).findAny().orElse(null);
		if(battingCard == null) {
			return status;
		}
		
		for(int split_id=0;split_id<5;split_id++) {
			playerData[split_id] = PlayerProgression(Integer.valueOf(whatToProcess.split(",")[2]), matchAllData, 
					matchAllData.getEventFile().getEvents()).get(split_id);
			splitNumber[split_id] =PlayerProgression(Integer.valueOf(whatToProcess.split(",")[2]), matchAllData, 
					matchAllData.getEventFile().getEvents()).get(split_id+5);
    	}
		for(int i=0;i<5;i++) {
			System.out.println(splitNumber[i]);
		}
		switch (config.getBroadcaster().toUpperCase()) {
		
		case Constants.BAN_AFG_SERIES: case Constants.ACC:
			lowerThird = new LowerThird("", battingCard.getPlayer().getFull_name(), "","INNINGS PROGRESSION", String.valueOf(battingCard.getRuns()), 
					String.valueOf(battingCard.getBalls()),
					2,"",inning.getBatting_team().getTeamBadge(),splitNumber,playerData,new String[]{"RUNS","BALLS"},null,
					new String[] {"-129","-90","-52","-12","26","66","103","140","170","191"});
			break;	
		}
		
		status = PopulateL3rdHeader(whatToProcess.split(",")[0],WhichSide);
		if(status == Constants.OK) {
			PopulateL3rdBaseColor(whatToProcess.split(",")[0], WhichSide);
			HideAndShowL3rdSubStrapContainers(WhichSide);
			setPositionOfLT(whatToProcess,WhichSide,config,lowerThird.getNumberOfSubLines());

			return PopulateL3rdBody(WhichSide, whatToProcess.split(",")[0]);
		} else {
			return status;
		}
	}
	public String populateL3rdAllRounderStats(String whatToProcess, int WhichSide, MatchAllData matchAllData) throws InterruptedException, IOException {
		if (matchAllData == null || matchAllData.getMatch() == null || matchAllData.getMatch().getInning() == null) {
			return status;
		} else {
			if( matchAllData.getMatch().getInning() == null) {
				return status;
			}
			
			String economy = "",bat_sr = "";
			double economy_rate=0;
			
			FirstPlayerId = Integer.valueOf(whatToProcess.split(",")[2]);
			WhichProfile = matchAllData.getSetup().getMatchType();
			
			this_data_str = new ArrayList<String>();
			battingCard = null;
			bowlingCard = null;
			
			if(whatToProcess.split(",")[3].equalsIgnoreCase("Tournament")) {
				
				
				addPastDataToCurr = CricketFunctions.extractTournamentData("CURRENT_MATCH_DATA", false, 
						headToHead,cricketService, matchAllData, past_tournament_stats);
				
				if(addPastDataToCurr == null) {
					return "addPastDataToCurr is Null";
				}
				
				for(Tournament tourn : addPastDataToCurr) {
					for(BestStats bs : tourn.getBatsman_best_Stats()) {
						top_batsman_beststats.add(bs);
					}
					for(BestStats bfig : tourn.getBowler_best_Stats()) {
						top_bowler_beststats.add(bfig);
					}
				}
				
				Collections.sort(top_batsman_beststats, new CricketFunctions.PlayerBestStatsComparator());
				Collections.sort(top_bowler_beststats, new CricketFunctions.PlayerBestStatsComparator());
				
				tournament = addPastDataToCurr.stream().filter(tourn -> tourn.getPlayerId() == Integer.valueOf(whatToProcess.split(",")[2])).findAny().orElse(null);
				player = Players.stream().filter(plyr -> plyr.getPlayerId() == Integer.valueOf(whatToProcess.split(",")[2])).findAny().orElse(null);
				team = Teams.stream().filter(tm -> tm.getTeamId() == tournament.getPlayer().getTeamId()).findAny().orElse(null);
				teamName = team.getTeamBadge();
				
				if(CricketFunctions.generateStrikeRate(tournament.getRuns(), 
						tournament.getBallsFaced(), 1).trim().isEmpty()) {
					bat_sr = "-"; 
				}else {
					bat_sr = CricketFunctions.generateStrikeRate(tournament.getRuns(), tournament.getBallsFaced(), 0);
				}
				
				if(tournament.getRunsConceded() == 0 && tournament.getBallsBowled() == 0) {
					economy = "-";
				}else {
					economy_rate = (Double.valueOf(tournament.getRunsConceded())) / tournament.getBallsBowled();
					economy_rate = economy_rate * 6;
					DecimalFormat df_b = new DecimalFormat("0.00");
					economy = df_b.format(economy_rate);
				}
				
				lowerThird = new LowerThird(player.getFull_name(),team.getTeamName1(), "","THIS TOURNAMENT", "","", 
						2, "" ,team.getTeamBadge(),new String[]{"MATCHES","RUNS","S/R","WICKETS","ECONOMY"},
						new String[]{String.valueOf(tournament.getMatches()), String.format("%,d\n", tournament.getRuns()),
								bat_sr,String.valueOf(tournament.getWickets()),economy},null,
						null,new String[] {"-503.0","-262.0","-9.0","250.0","530.0"});
			}else {
				
				for(Inning inn : matchAllData.getMatch().getInning()) {
					if(battingCard == null) {
						battingCard = inn.getBattingCard().stream().filter(bc->bc.getPlayerId() == 
								FirstPlayerId).findAny().orElse(null);
					}
					
					if(inn.getBowlingCard() != null && inn.getBowlingCard().size() > 0) {
						if(bowlingCard == null) {
							bowlingCard = inn.getBowlingCard().stream().filter(boc->boc.getPlayerId() == 
									FirstPlayerId).findAny().orElse(null);
						}
					}
				}
				
				team = Teams.stream().filter(team->team.getTeamId() == battingCard.getPlayer().getTeamId())
						.findAny().orElse(null);
				
				if(battingCard.getRuns() <= 0) {
					this_data_str.add("-");
				}else {
					this_data_str.add(CricketFunctions.generateStrikeRate(battingCard.getRuns(), 
							battingCard.getBalls(), 1));
				}
				
				if(bowlingCard == null) {
					this_data_str.add("-");
					this_data_str.add("-");
					this_data_str.add("-");
				}else {
					this_data_str.add(CricketFunctions.OverBalls(bowlingCard.getOvers(), bowlingCard.getBalls()));
					this_data_str.add(String.valueOf(bowlingCard.getWickets()));
					
					if(bowlingCard.getEconomyRate() == null) {
						this_data_str.add("-");
					}else {
						this_data_str.add(bowlingCard.getEconomyRate());
					}
				}
				player = battingCard.getPlayer();
				lowerThird = new LowerThird(battingCard.getPlayer().getFull_name(),team.getTeamName1(), "","THIS MATCH", "","", 2, "",
						team.getTeamBadge(), new String[]{"RUNS","BALLS","S/R","OVERS","WICKETS","ECONOMY"},
						new String[]{String.valueOf(battingCard.getRuns()), String.valueOf(battingCard.getBalls()), 
							this_data_str.get(0),this_data_str.get(1),this_data_str.get(2),this_data_str.get(3)},null,
						null,new String[] {"-530.0","-345.0","-122.0","102.0","310.0","530.0"});
					
			}
			status = PopulateL3rdHeader(whatToProcess.split(",")[0],WhichSide);
			if(status == Constants.OK) {
				HideAndShowL3rdSubStrapContainers(WhichSide);
				setPositionOfLT(whatToProcess,WhichSide,config,lowerThird.getNumberOfSubLines());
				return PopulateL3rdBody(WhichSide, whatToProcess.split(",")[0]);
			} else {
				return status;
			}
		}
	}
	public String populateL3rdExtras(String whatToProcess,int WhichSide,MatchAllData matchAllData) throws InterruptedException
	{
		switch (config.getBroadcaster().toUpperCase()) {
		case Constants.TRI_SERIES:  case Constants.MT20:
			if(whatToProcess.split(",")[2].equalsIgnoreCase("1")) {
				inning = matchAllData.getMatch().getInning().stream().filter(inn -> inn.getInningNumber() == Integer.valueOf(whatToProcess.split(",")[2]))
						.findAny().orElse(null);
					
				if(inning == null) {
					return "populateExtras: Current Inning is Null";
				}
				
				if(matchAllData.getMatch().getInning().get(0).getTotalPenalties() > 0) {
					lowerThird = new LowerThird("INNINGS EXTRAS", "", "",whatToProcess.split(",")[2], "", "",2,"",inning.getBowling_team().getTeamBadge(),new String[]{"WIDES","NO BALLS","BYES","LEG BYES","PENALTIES"},
							new String[]{String.valueOf(inning.getTotalWides()),String.valueOf(inning.getTotalNoBalls()),String.valueOf(inning.getTotalByes()),
							String.valueOf(inning.getTotalLegByes()),String.valueOf(inning.getTotalPenalties())},null,null,
							new String[] {"-175","-97","-11","73","166"});
				}else {
					lowerThird = new LowerThird("INNINGS EXTRAS", "", "",whatToProcess.split(",")[2], "", "",2,"",inning.getBowling_team().getTeamBadge(),new String[]{"WIDES","NO BALLS","BYES","LEG BYES"},
							new String[]{String.valueOf(inning.getTotalWides()),String.valueOf(inning.getTotalNoBalls()),String.valueOf(inning.getTotalByes()),
									String.valueOf(inning.getTotalLegByes())},null,null,
							new String[] {"-175","-57","60","167"});
				}
			}else if(whatToProcess.split(",")[2].equalsIgnoreCase("2")) {
				inning = matchAllData.getMatch().getInning().stream().filter(inn -> inn.getInningNumber() == Integer.valueOf(whatToProcess.split(",")[2]))
						.findAny().orElse(null);
					
				if(inning == null) {
					return "populateExtras: Current Inning is Null";
				}
				
				if(matchAllData.getMatch().getInning().get(1).getTotalPenalties() > 0) {
					lowerThird = new LowerThird("INNINGS EXTRAS", "", "",whatToProcess.split(",")[2], "", "",2,"",inning.getBowling_team().getTeamBadge(),new String[]{"WIDES","NO BALLS","BYES","LEG BYES","PENALTIES"},
							new String[]{String.valueOf(inning.getTotalWides()),String.valueOf(inning.getTotalNoBalls()),String.valueOf(inning.getTotalByes()),
							String.valueOf(inning.getTotalLegByes()),String.valueOf(inning.getTotalPenalties())},null,null,
							new String[] {"-175","-97","-11","73","166"});
				}else {
					lowerThird = new LowerThird("INNINGS EXTRAS", "", "",whatToProcess.split(",")[2], "", "",2,"",inning.getBowling_team().getTeamBadge(),new String[]{"WIDES","NO BALLS","BYES","LEG BYES"},
							new String[]{String.valueOf(inning.getTotalWides()),String.valueOf(inning.getTotalNoBalls()),String.valueOf(inning.getTotalByes()),
									String.valueOf(inning.getTotalLegByes())},null,null,
							new String[] {"-175","-57","60","167"});
				}
			}else {
				if(matchAllData.getMatch().getInning().get(0).getTotalPenalties() > 0 || matchAllData.getMatch().getInning().get(1).getTotalPenalties() > 0) {
					lowerThird = new LowerThird("MATCH EXTRAS", matchAllData.getSetup().getHomeTeam().getTeamName4(), matchAllData.getSetup().getAwayTeam().getTeamName4(),
							whatToProcess.split(",")[2], "", "",4,"FLAG","",new String[]{"WIDES","NO BALLS","BYES","LEG BYES","PENALTIES"},
							new String[]{String.valueOf(matchAllData.getMatch().getInning().get(0).getTotalWides() + "," + matchAllData.getMatch().getInning().get(1).getTotalWides() + "," + 
							(matchAllData.getMatch().getInning().get(0).getTotalWides() + matchAllData.getMatch().getInning().get(1).getTotalWides())),
							String.valueOf(matchAllData.getMatch().getInning().get(0).getTotalNoBalls() + "," + matchAllData.getMatch().getInning().get(1).getTotalNoBalls() + "," + 
							(matchAllData.getMatch().getInning().get(0).getTotalNoBalls() + matchAllData.getMatch().getInning().get(1).getTotalNoBalls())),
							String.valueOf(matchAllData.getMatch().getInning().get(0).getTotalByes() + "," + matchAllData.getMatch().getInning().get(1).getTotalByes() + "," + 
							(matchAllData.getMatch().getInning().get(0).getTotalByes() + matchAllData.getMatch().getInning().get(1).getTotalByes())),
							String.valueOf(matchAllData.getMatch().getInning().get(0).getTotalLegByes() + "," + matchAllData.getMatch().getInning().get(1).getTotalLegByes() + "," + 
							(matchAllData.getMatch().getInning().get(0).getTotalLegByes() + matchAllData.getMatch().getInning().get(1).getTotalLegByes())),
							String.valueOf(matchAllData.getMatch().getInning().get(0).getTotalPenalties() + "," + matchAllData.getMatch().getInning().get(1).getTotalPenalties() + "," + 
							(matchAllData.getMatch().getInning().get(0).getTotalPenalties() + matchAllData.getMatch().getInning().get(1).getTotalPenalties()))},
							new String[]{matchAllData.getMatch().getInning().get(0).getBatting_team().getTeamName1(),
							matchAllData.getMatch().getInning().get(1).getBatting_team().getTeamName1()},null,
							new String[] {"-175","-97","-11","73","166"});
				}else {
					lowerThird = new LowerThird("MATCH EXTRAS", matchAllData.getSetup().getHomeTeam().getTeamName4(), matchAllData.getSetup().getAwayTeam().getTeamName4(),
							whatToProcess.split(",")[2], "", "",4,"FLAG","",new String[]{"WIDES","NO BALLS","BYES","LEG BYES"},
							new String[]{String.valueOf(matchAllData.getMatch().getInning().get(0).getTotalWides() + "," + matchAllData.getMatch().getInning().get(1).getTotalWides() + "," + 
									(matchAllData.getMatch().getInning().get(0).getTotalWides() + matchAllData.getMatch().getInning().get(1).getTotalWides())),
									String.valueOf(matchAllData.getMatch().getInning().get(0).getTotalNoBalls() + "," + matchAllData.getMatch().getInning().get(1).getTotalNoBalls() + "," + 
									(matchAllData.getMatch().getInning().get(0).getTotalNoBalls() + matchAllData.getMatch().getInning().get(1).getTotalNoBalls())),
									String.valueOf(matchAllData.getMatch().getInning().get(0).getTotalByes() + "," + matchAllData.getMatch().getInning().get(1).getTotalByes() + "," + 
									(matchAllData.getMatch().getInning().get(0).getTotalByes() + matchAllData.getMatch().getInning().get(1).getTotalByes())),
									String.valueOf(matchAllData.getMatch().getInning().get(0).getTotalLegByes() + "," + matchAllData.getMatch().getInning().get(1).getTotalLegByes() + "," + 
									(matchAllData.getMatch().getInning().get(0).getTotalLegByes() + matchAllData.getMatch().getInning().get(1).getTotalLegByes()))},
							new String[]{matchAllData.getMatch().getInning().get(0).getBatting_team().getTeamName1(),
							matchAllData.getMatch().getInning().get(1).getBatting_team().getTeamName1()},null,
							new String[] {"-175","-57","60","167"});
				}
			}
			break;
		}
		
		status = PopulateL3rdHeader(whatToProcess.split(",")[0],WhichSide);
		if(status == Constants.OK) {
			PopulateL3rdBaseColor(whatToProcess.split(",")[0], WhichSide);
			HideAndShowL3rdSubStrapContainers(WhichSide);
			setPositionOfLT(whatToProcess,WhichSide,config,lowerThird.getNumberOfSubLines());
			return PopulateL3rdBody(WhichSide, whatToProcess.split(",")[0]);
		} else {
			return status;
		}
	}
	public String populateBatSummary(String whatToProcess,int WhichSide,MatchAllData matchAllData) throws InterruptedException
	{
		String outOrNot = "";
		
		if (matchAllData == null || matchAllData.getMatch() == null || matchAllData.getMatch().getInning() == null) {
			return status;
		} else {
			inning = matchAllData.getMatch().getInning().stream().filter(inn -> inn.getInningNumber() == Integer.valueOf(whatToProcess.split(",")[1])).findAny().orElse(null);
			
			if(inning == null) {
				return "populateBatSummary: Inning is Not Found";
			}
			
			battingCard = inning.getBattingCard().stream().filter(bc -> bc.getPlayerId() == Integer.valueOf(whatToProcess.split(",")[2])).findAny().orElse(null);
			if(battingCard == null) {
				return status;
			}
			
			if(battingCard.getStatus().equalsIgnoreCase(CricketUtil.NOT_OUT)) {
				outOrNot = "*";
			}else {
				outOrNot = "";
			}
			
		}
		
		String[] Count = CricketFunctions.getScoreTypeData(CricketUtil.BATSMAN,matchAllData, inning.getInningNumber(), Integer.valueOf(whatToProcess.split(",")[2]),
				"-", matchAllData.getEventFile().getEvents()).split("-");
		
		if(battingCard.getPlayer().getSurname() == null) {
			surName = "";
		}else {
			surName = battingCard.getPlayer().getSurname();
		}
		
		if(CricketFunctions.isImpactPlayer(matchAllData.getEventFile().getEvents(), inning.getInningNumber(), 
				Integer.valueOf(whatToProcess.split(",")[2])).equalsIgnoreCase(CricketUtil.YES)) {
			impactOmo = 1;
			setImpact(true);
		} else {
			impactOmo =0;
			setImpact(false);
		}
		
		switch (config.getBroadcaster().toUpperCase()) {
		case Constants.TRI_SERIES:  case Constants.MT20:
			player = battingCard.getPlayer();
			containerName =(config.getBroadcaster().toUpperCase().equalsIgnoreCase(Constants.TRI_SERIES)?"THIS INNINGS":"");
			lowerThird = new LowerThird(containerName, battingCard.getPlayer().getFirstname(), surName,outOrNot, String.valueOf(battingCard.getRuns()), 
					String.valueOf(battingCard.getBalls()), 2, "", inning.getBatting_team().getTeamBadge(),new String[] {"DOTS", "ONES", "TWOS", "THREES", "FOURS", "SIXES"},
					new String[]{Count[0],Count[1],Count[2],Count[3],Count[4],Count[6]},null,null,new String[] {"-178","-110","-38","37","113","178"});
			break;
		case Constants.BAN_AFG_SERIES: case Constants.ACC:
			player = battingCard.getPlayer();
			containerName =(config.getBroadcaster().toUpperCase().equalsIgnoreCase(Constants.TRI_SERIES)?"THIS INNINGS":"");
			lowerThird = new LowerThird(containerName, battingCard.getPlayer().getFull_name(), surName,outOrNot, String.valueOf(battingCard.getRuns()), 
					String.valueOf(battingCard.getBalls()), 2, "", inning.getBatting_team().getTeamBadge(),new String[] {"DOTS", "ONES", "TWOS", "THREES", "FOURS", "SIXES"},
					new String[]{Count[0],Count[1],Count[2],Count[3],Count[4],Count[6]},null,null,new String[] {"-178","-110","-38","37","113","178"});
			break;	
		}
		
		status = PopulateL3rdHeader(whatToProcess.split(",")[0],WhichSide);
		if(status == Constants.OK) {
			PopulateL3rdBaseColor(whatToProcess.split(",")[0], WhichSide);
			HideAndShowL3rdSubStrapContainers(WhichSide);
			setPositionOfLT(whatToProcess,WhichSide,config,lowerThird.getNumberOfSubLines());
			return PopulateL3rdBody(WhichSide, whatToProcess.split(",")[0]);
		} else {
			return status;
		}
	}
	
	public String populateBallSummary(String whatToProcess,int WhichSide,MatchAllData matchAllData) throws InterruptedException
	{
		String over_text = "",plural = "";
		
		if (matchAllData == null || matchAllData.getMatch() == null || matchAllData.getMatch().getInning() == null) {
			return status;
		} else {
			inning = matchAllData.getMatch().getInning().stream().filter(inn -> inn.getInningNumber() == Integer.valueOf(whatToProcess.split(",")[1])).findAny().orElse(null);
			
			if(inning == null) {
				return "populateBallSummary: Inning is Not Found";
			}
			
			bowlingCard = inning.getBowlingCard().stream().filter(boc -> boc.getPlayerId() == Integer.valueOf(whatToProcess.split(",")[2])).findAny().orElse(null);
			if(bowlingCard == null) {
				return status;
			}
		}
		if(CricketFunctions.isImpactPlayer(matchAllData.getEventFile().getEvents(), inning.getInningNumber(), 
				Integer.valueOf(whatToProcess.split(",")[2])).equalsIgnoreCase(CricketUtil.YES)) {
			impactOmo = 1;
			setImpact(true);
		} else {
			impactOmo =0;
			setImpact(false);
		}
		
		String[] Count = CricketFunctions.getScoreTypeData(CricketUtil.BOWLER,matchAllData, inning.getInningNumber(), Integer.valueOf(whatToProcess.split(",")[2]),
				"-", matchAllData.getEventFile().getEvents()).split("-");
		
		if(bowlingCard.getPlayer().getSurname() == null) {
			surName = "";
		}else {
			surName = bowlingCard.getPlayer().getSurname();
		}
		
		switch (config.getBroadcaster().toUpperCase()) {
		/*case Constants.LEGENDS:*/case "":
			if(bowlingCard.getOvers() == 0 && bowlingCard.getBalls() >= 0) {
				over_text = "BALLS";
			}else if(bowlingCard.getOvers() == 1 && bowlingCard.getBalls() == 0) {
				over_text = "BALL";
			}else if(bowlingCard.getOvers() == 1 && bowlingCard.getBalls() > 0) {
				over_text = "BALLS";
			}else {
				over_text = "BALLS";
			}
			break;
		default:
			if(bowlingCard.getOvers() == 0 && bowlingCard.getBalls() >= 0) {
				over_text = "OVERS";
			}else if(bowlingCard.getOvers() == 1 && bowlingCard.getBalls() == 0) {
				over_text = "OVER";
			}else if(bowlingCard.getOvers() == 1 && bowlingCard.getBalls() > 0) {
				over_text = "OVERS";
			}else {
				over_text = "OVERS";
			}
			break;
		}
		
		switch (config.getBroadcaster().toUpperCase()) {
		case Constants.TRI_SERIES:  case Constants.MT20:
			player = bowlingCard.getPlayer();
			containerName =(config.getBroadcaster().toUpperCase().equalsIgnoreCase(Constants.TRI_SERIES)?"THIS INNINGS":"");
			lowerThird = new LowerThird(containerName, bowlingCard.getPlayer().getFirstname(), surName,over_text, String.valueOf(bowlingCard.getWickets()) + "-" + String.valueOf(bowlingCard.getRuns()), 
					String.valueOf(CricketFunctions.OverBalls(bowlingCard.getOvers(), bowlingCard.getBalls())), 2, "", inning.getBowling_team().getTeamBadge(),new String[] {"DOTS", "ONES", "TWOS", "THREES", "FOURS", "SIXES"},
					new String[]{Count[0],Count[1],Count[2],Count[3],Count[4],Count[6]},null,null,new String[] {"-178","-110","-38","37","113","178"});
			break;
		case Constants.BAN_AFG_SERIES: case Constants.ACC:
			player = bowlingCard.getPlayer();
			containerName =(config.getBroadcaster().toUpperCase().equalsIgnoreCase(Constants.TRI_SERIES)?"THIS INNINGS":"");
			lowerThird = new LowerThird(containerName, bowlingCard.getPlayer().getFull_name(), surName,over_text, String.valueOf(bowlingCard.getWickets()) + "-" + String.valueOf(bowlingCard.getRuns()), 
					String.valueOf(CricketFunctions.OverBalls(bowlingCard.getOvers(), bowlingCard.getBalls())), 2, "", inning.getBowling_team().getTeamBadge(),new String[] {"DOTS", "ONES", "TWOS", "THREES", "FOURS", "SIXES"},
					new String[]{Count[0],Count[1],Count[2],Count[3],Count[4],Count[6]},null,null,new String[] {"-178","-110","-38","37","113","178"});
			break;	
		}
		
		status = PopulateL3rdHeader(whatToProcess.split(",")[0],WhichSide);
		if(status == Constants.OK) {
			PopulateL3rdBaseColor(whatToProcess.split(",")[0], WhichSide);
			HideAndShowL3rdSubStrapContainers(WhichSide);
			setPositionOfLT(whatToProcess,WhichSide,config,lowerThird.getNumberOfSubLines());
			return PopulateL3rdBody(WhichSide, whatToProcess.split(",")[0]);
		} else {
			return status;
		}
	}
	
	public String populateTeamSummary(String whatToProcess,int WhichSide,MatchAllData matchAllData) throws InterruptedException
	{
		if (matchAllData == null || matchAllData.getMatch() == null || matchAllData.getMatch().getInning() == null) {
			return status;
		} else {
			
			inning = matchAllData.getMatch().getInning().stream().filter(inn -> inn.getBattingTeamId() == Integer.valueOf(whatToProcess.split(",")[2])).findAny().orElse(null);
			
			if(inning == null) {
				return "populateTeamSummary: Inning is Not Found";
			}
		}
		
		String[] Count = CricketFunctions.getScoreTypeData(CricketUtil.TEAM,matchAllData, inning.getInningNumber(), 0,
				"-", matchAllData.getEventFile().getEvents()).split("-");
		
		switch (config.getBroadcaster().toUpperCase()) {
		case Constants.TRI_SERIES:  case Constants.MT20: case Constants.BAN_AFG_SERIES: case Constants.ACC:
			lowerThird = new LowerThird(inning.getBatting_team().getTeamName1(), "", "","", String.valueOf(inning.getTotalRuns()) + "-" + String.valueOf(inning.getTotalWickets()), 
					CricketFunctions.OverBalls(inning.getTotalOvers(), inning.getTotalBalls()), 2, "", inning.getBatting_team().getTeamBadge(),
					new String[] {"DOTS", "ONES", "TWOS", "THREES", "FOURS", "SIXES"},new String[]{Count[0],Count[1],Count[2],Count[3],String.valueOf(inning.getTotalFours()),
					String.valueOf(inning.getTotalSixes())},null,null,new String[] {"-178","-110","-38","37","113","178"});
			break;
		}
		
		status = PopulateL3rdHeader(whatToProcess.split(",")[0],WhichSide);
		if(status == Constants.OK) {
			PopulateL3rdBaseColor(whatToProcess.split(",")[0], WhichSide);
			HideAndShowL3rdSubStrapContainers(WhichSide);
			setPositionOfLT(whatToProcess,WhichSide,config,lowerThird.getNumberOfSubLines());
			return PopulateL3rdBody(WhichSide, whatToProcess.split(",")[0]);
		} else {
			return status;
		}
	}
	public String populateL3rdTarget(String whatToProcess,int WhichSide,MatchAllData matchAllData) throws InterruptedException
	{
		inning = matchAllData.getMatch().getInning().stream().filter(inn -> inn.getInningNumber() == Integer.valueOf(whatToProcess.split(",")[1]))
			.findAny().orElse(null);
		
		if(inning == null) {
			return "populateTarget: Current Inning NOT found in this match";
		}
		
		if(inning.getInningNumber() == 1) {
			return "populateTarget: Current Inning is 1";
		}
		
		String runRate = "",summary = "";
		
		teamNameAsCity = inning.getBatting_team().getTeamName1();
		
		if(matchAllData.getSetup().getMatchType().equalsIgnoreCase(CricketUtil.SUPER_OVER) && matchAllData.getSetup().getMaxOvers() == 1) {
			summary = teamNameAsCity + " NEED " + CricketFunctions.GetTargetData(matchAllData).getTargetRuns() + " RUNS" + " TO WIN";
			
			lowerThird = new LowerThird(summary, inning.getBatting_team().getTeamName2(), inning.getBatting_team().getTeamName3(),matchAllData.getSetup().getHomeTeam().getTeamName1(), 
					matchAllData.getSetup().getAwayTeam().getTeamName1(), matchAllData.getSetup().getHomeTeam().getTeamBadge(),1,matchAllData.getSetup().getAwayTeam().getTeamBadge(),
					inning.getBatting_team().getTeamBadge(),null,null,new String[]{"THE SUPER OVER",""},null,null);
		}else {
			
			if(matchAllData.getSetup().getTargetOvers() != null && matchAllData.getSetup().getTargetRuns() != 0) {
				summary = teamNameAsCity + " NEED " + CricketFunctions.GetTargetData(matchAllData).getTargetRuns() + " RUNS" + " TO WIN";
				runRate = "@ " + CricketFunctions.generateRunRate(CricketFunctions.GetTargetData(matchAllData).getTargetRuns(), 
						Integer.valueOf(CricketFunctions.GetTargetData(matchAllData).getTargetOvers().split("\\.")[0]),
						(Integer.valueOf(CricketFunctions.GetTargetData(matchAllData).getTargetOvers().split("\\.").length >1 ? 
								Integer.valueOf(CricketFunctions.GetTargetData(matchAllData).getTargetOvers().split("\\.")[1]):0)), 2,matchAllData) + " RPO";
				
				if(matchAllData.getSetup().getTargetOvers() != null && !matchAllData.getSetup().getTargetOvers().isEmpty()) {
					
					lowerThird = new LowerThird(summary, inning.getBatting_team().getTeamName2(), inning.getBatting_team().getTeamName3(),matchAllData.getSetup().getHomeTeam().getTeamName1(),
							matchAllData.getSetup().getAwayTeam().getTeamName1(), matchAllData.getSetup().getHomeTeam().getTeamBadge(),1,matchAllData.getSetup().getAwayTeam().getTeamBadge(),
							inning.getBatting_team().getTeamBadge(),null,null,new String[]{String.valueOf("FROM " + 
									CricketFunctions.GetTargetData(matchAllData).getTargetOvers())+ " OVERS",""},null,null);
				}
				if(matchAllData.getSetup().getTargetType().toUpperCase().equalsIgnoreCase("VJD")) {
					
					lowerThird = new LowerThird(summary, inning.getBatting_team().getTeamName2(), inning.getBatting_team().getTeamName3(),matchAllData.getSetup().getHomeTeam().getTeamName1(), 
							matchAllData.getSetup().getAwayTeam().getTeamName1(), matchAllData.getSetup().getHomeTeam().getTeamBadge(),1,matchAllData.getSetup().getAwayTeam().getTeamBadge(),inning.getBatting_team().getTeamBadge(),null,null,
							new String[]{String.valueOf("FROM " + CricketFunctions.GetTargetData(matchAllData).getTargetOvers())+ " OVERS"," (VJD)"},null,null);
					
				}else if(matchAllData.getSetup().getTargetType().toUpperCase().equalsIgnoreCase("DLS")) {
					
					lowerThird = new LowerThird(summary, inning.getBatting_team().getTeamName2(), inning.getBatting_team().getTeamName3(),matchAllData.getSetup().getHomeTeam().getTeamName1(), matchAllData.getSetup().getAwayTeam().getTeamName1(), 
							matchAllData.getSetup().getHomeTeam().getTeamBadge(),1,matchAllData.getSetup().getAwayTeam().getTeamBadge(),inning.getBatting_team().getTeamBadge(),null,null,
							new String[]{String.valueOf("FROM " + CricketFunctions.GetTargetData(matchAllData).getTargetOvers())+ " OVERS"," (DLS)"},null,null);
				}
			}else {
				summary = teamNameAsCity + " NEED " + CricketFunctions.GetTargetData(matchAllData).getTargetRuns() + " RUNS" + " TO WIN";
				runRate = "@ " + CricketFunctions.generateRunRate(CricketFunctions.GetTargetData(matchAllData).getTargetRuns(), 
						Integer.valueOf(CricketFunctions.GetTargetData(matchAllData).getTargetOvers().split("\\.")[0]),
						(Integer.valueOf(CricketFunctions.GetTargetData(matchAllData).getTargetOvers().split("\\.").length >1 ? 
								Integer.valueOf(CricketFunctions.GetTargetData(matchAllData).getTargetOvers().split("\\.")[1]):0)), 2, matchAllData) + " RPO";
				
				lowerThird = new LowerThird(summary, inning.getBatting_team().getTeamName2(), inning.getBatting_team().getTeamName3(),matchAllData.getSetup().getHomeTeam().getTeamName1(), 
						matchAllData.getSetup().getAwayTeam().getTeamName1(), matchAllData.getSetup().getHomeTeam().getTeamBadge(),1,matchAllData.getSetup().getAwayTeam().getTeamBadge(),
						inning.getBatting_team().getTeamBadge(),null,null,new String[]{String.valueOf("FROM " + CricketFunctions.GetTargetData(matchAllData).getTargetOvers())+ 
						" OVERS",""},null,null);

			}
		}
		
		status = PopulateL3rdHeader(whatToProcess.split(",")[0],WhichSide);
		if(status == Constants.OK) {
			PopulateL3rdBaseColor(whatToProcess.split(",")[0], WhichSide);
			HideAndShowL3rdSubStrapContainers(WhichSide);
			setPositionOfLT(whatToProcess,WhichSide,config,lowerThird.getNumberOfSubLines());
			return PopulateL3rdBody(WhichSide, whatToProcess.split(",")[0]);
		} else {
			return status;
		}
	}

	public String populateL3rdEquation(String whatToProcess,int WhichSide,MatchAllData matchAllData) throws InterruptedException
	{
		inning = matchAllData.getMatch().getInning().stream().filter(inn -> inn.getInningNumber() == 2 && inn.getIsCurrentInning().equalsIgnoreCase(CricketUtil.YES))
				.findAny().orElse(null);
		
		if(inning == null) {
			return "populateEquation: current inning is 1";
		}
		
		String line_1 = "",summary="",line_2 = "";
		
		teamNameAsCity = inning.getBatting_team().getTeamName1();
		
		if(CricketFunctions.GetTargetData(matchAllData).getRemaningBall() < 100) {
			line_1 = "FROM " + CricketFunctions.GetTargetData(matchAllData).getRemaningBall() + " BALL" + CricketFunctions.Plural(CricketFunctions.GetTargetData(matchAllData).getRemaningBall()).toUpperCase();
		}else {
			if(CricketFunctions.GetTargetData(matchAllData).getRemaningBall()%6 == 0) {
				 line_1 = "FROM " + CricketFunctions.GetTargetData(matchAllData).getRemaningBall()/6 + " OVERS";
			}else {
				line_1 = "FROM " + CricketFunctions.OverBalls(CricketFunctions.GetTargetData(matchAllData).getRemaningBall()/6, CricketFunctions.GetTargetData(matchAllData).getRemaningBall()%6) + " OVERS";
			}
		}
		
		summary = teamNameAsCity + " NEED " + CricketFunctions.GetTargetData(matchAllData).getRemaningRuns();
		
		switch (config.getBroadcaster().toUpperCase()) {
		case Constants.TRI_SERIES:  case Constants.MT20: case Constants.BAN_AFG_SERIES: case Constants.ACC:
			
			if(CricketFunctions.GetTargetData(matchAllData).getRemaningRuns() < 2) {
				summary = summary + " RUN" + CricketFunctions.Plural(CricketFunctions.GetTargetData(matchAllData).getRemaningRuns()).toUpperCase() + " TO WIN";
				
			}else {
				summary = summary + " MORE RUN" + CricketFunctions.Plural(CricketFunctions.GetTargetData(matchAllData).getRemaningRuns()).toUpperCase() + " TO WIN";
				
			}
			
			line_2 = "";
			break;
		}
		
		if(matchAllData.getSetup().getTargetType()!= null) {
			if(matchAllData.getSetup().getTargetType().toUpperCase().equalsIgnoreCase("VJD")) {
				line_1 = line_1 + " (" + CricketUtil.VJD + ")";
			}else if(matchAllData.getSetup().getTargetType().toUpperCase().equalsIgnoreCase("DLS")) {
				line_1 = line_1 + " (" + CricketUtil.DLS + ")";
			}
		}
		
		switch (config.getBroadcaster().toUpperCase()) {
		case Constants.TRI_SERIES:  case Constants.MT20:
			lowerThird = new LowerThird(summary, inning.getBatting_team().getTeamName2(), inning.getBatting_team().getTeamName3(),"", "", "",1,"",
					inning.getBatting_team().getTeamBadge(),null,null,new String[]{line_1,line_2},null,null);
			break;
		}
		
		status = PopulateL3rdHeader(whatToProcess.split(",")[0],WhichSide);
		if(status == Constants.OK) {
			PopulateL3rdBaseColor(whatToProcess.split(",")[0], WhichSide);
			HideAndShowL3rdSubStrapContainers(WhichSide);
			setPositionOfLT(whatToProcess,WhichSide,config,lowerThird.getNumberOfSubLines());
			return PopulateL3rdBody(WhichSide, whatToProcess.split(",")[0]);
		} else {
			return status;
		}
	}
	public String populateL3PhaseWise(String whatToProcess, int whichSide, MatchAllData matchAllData) throws InterruptedException {
		inning = matchAllData.getMatch().getInning().stream().filter(inn -> inn.getInningNumber() == Integer.valueOf(whatToProcess.split(",")[1])).findAny().orElse(null);
		if(inning == null) {
			return "populateL3PhaseWise Inning is null";
		}
		
		String titl = "20";
		
		if(inning.getInningNumber() == 1) {
			if(matchAllData.getSetup().getReducedOvers() != null) {
				if(Integer.valueOf(matchAllData.getSetup().getReducedOvers()) > 0) {
					titl = matchAllData.getSetup().getReducedOvers();
				}	
			}
		}else if(inning.getInningNumber() == 2) {
			if(matchAllData.getSetup().getReducedOvers() != null) {
				if(Integer.valueOf(matchAllData.getSetup().getReducedOvers()) > 0) {
					titl = matchAllData.getSetup().getReducedOvers();
				}
			}
			
			if(matchAllData.getSetup().getTargetOvers() != null && !matchAllData.getSetup().getTargetOvers().isEmpty()) {
				titl = matchAllData.getSetup().getTargetOvers();
			}
		}
		
		if(matchAllData.getSetup().getMatchType().equalsIgnoreCase(CricketUtil.ODI) || 
				matchAllData.getSetup().getMatchType().equalsIgnoreCase(CricketUtil.OD)) {
			int oneToTenRuns = 0, elevneToTwentyRuns = 0, twentyOneToThirtyRuns = 0,thirtyOneToFourtyRuns = 0,fourtyOneToFiftyRuns = 0,
					oneToTenWkt = 0, elevneToTwentyWkt = 0, twentyOneToThirtyWkt = 0,thirtyOneToFourtyWkt = 0,fourtyOneToFiftyWkt = 0;
			
			List<OverByOverData> overByOverData = CricketFunctions.getOverByOverData(matchAllData, Integer.valueOf(whatToProcess.split(",")[1]), 
					"MANHATTAN", matchAllData.getEventFile().getEvents());
			for(int i=0; i<2; i++) {
				if(matchAllData.getMatch().getInning().get(i).getInningNumber() == Integer.valueOf(whatToProcess.split(",")[1])) {
					
					for(int j=1; j<=overByOverData.size()-1; j++) {
						if(j>0 && j<=10) {
							oneToTenRuns+= overByOverData.get(j).getOverTotalRuns();
							oneToTenWkt+=overByOverData.get(j).getOverTotalWickets();
						}
						
						if(j>10 && j<=40) {
							thirtyOneToFourtyRuns+= overByOverData.get(j).getOverTotalRuns();
							thirtyOneToFourtyWkt+=overByOverData.get(j).getOverTotalWickets();
						}
						
//						if(j>10 && j<=20) {
//							elevneToTwentyRuns+= overByOverData.get(j).getOverTotalRuns();
//							elevneToTwentyWkt+=overByOverData.get(j).getOverTotalWickets();
//						}
//						if(j>20 && j<=30) {
//							twentyOneToThirtyRuns+= overByOverData.get(j).getOverTotalRuns();
//							twentyOneToThirtyWkt+=overByOverData.get(j).getOverTotalWickets();
//						}
//						if(j>30 && j<=40) {
//							thirtyOneToFourtyRuns+= overByOverData.get(j).getOverTotalRuns();
//							thirtyOneToFourtyWkt+=overByOverData.get(j).getOverTotalWickets();
//						}
						if(j>40 && j<=50) {
							fourtyOneToFiftyRuns+= overByOverData.get(j).getOverTotalRuns();
							fourtyOneToFiftyWkt+=overByOverData.get(j).getOverTotalWickets();
						}
					}
				}
			}
			
			lowerThird = new LowerThird("", inning.getBatting_team().getTeamName2(), inning.getBatting_team().getTeamName3(),"",CricketFunctions.getTeamScore(inning, "-", false), 
					String.valueOf(CricketFunctions.OverBalls(inning.getTotalOvers(), inning.getTotalBalls())),2,matchAllData.getSetup().getMatchType(),
					inning.getBatting_team().getTeamBadge(),
					new String[]{"1 - 10", "11 - 40", "41 - "+ titl},new String[]{oneToTenRuns + "-" + oneToTenWkt
							,thirtyOneToFourtyRuns + "-" + thirtyOneToFourtyWkt,fourtyOneToFiftyRuns + "-" + fourtyOneToFiftyWkt},
					new String[] {"OVERS", "SCORE"},null,new String[]{"-97","31","171"});
		}else {
			int oneToSixRuns = 0, sevenToFifteenRuns = 0, sixteenToTweentyRuns = 0,oneToSixfWkt = 0, sevenToFifteenWkt = 0, sixteenToTweentyWkt = 0;
			
			List<OverByOverData> overByOverData = CricketFunctions.getOverByOverData(matchAllData, Integer.valueOf(whatToProcess.split(",")[1]), "MANHATTAN", matchAllData.getEventFile().getEvents());
			for(int i=0; i<2; i++) {
				if(matchAllData.getMatch().getInning().get(i).getInningNumber() == Integer.valueOf(whatToProcess.split(",")[1])) {
					
					for(int j=1; j<=overByOverData.size()-1; j++) {
						if(j>0 && j<=6) {
							oneToSixRuns+= overByOverData.get(j).getOverTotalRuns();
							oneToSixfWkt+=overByOverData.get(j).getOverTotalWickets();
						}
						if(j>6 && j<=15) {
							sevenToFifteenRuns+= overByOverData.get(j).getOverTotalRuns();
							sevenToFifteenWkt+=overByOverData.get(j).getOverTotalWickets();
						}
						if(j>15 && j<=20) {
							sixteenToTweentyRuns+= overByOverData.get(j).getOverTotalRuns();
							sixteenToTweentyWkt+=overByOverData.get(j).getOverTotalWickets();
						}
					}
				}
			}
			
			lowerThird = new LowerThird("", inning.getBatting_team().getTeamName2(), inning.getBatting_team().getTeamName3(),"",CricketFunctions.getTeamScore(inning, "-", false), 
					String.valueOf(CricketFunctions.OverBalls(inning.getTotalOvers(), inning.getTotalBalls())),2,matchAllData.getSetup().getMatchType(),
					inning.getBatting_team().getTeamBadge(),
					new String[]{"1 - 6", "7 - 15", "16 - "+ titl},new String[]{oneToSixRuns + "-" + oneToSixfWkt,
					sevenToFifteenRuns + "-" + sevenToFifteenWkt,sixteenToTweentyRuns + "-" + sixteenToTweentyWkt},
					new String[] {"OVERS", "SCORE"},null,new String[]{"-97","31","171"});
		}
		
		status = PopulateL3rdHeader(whatToProcess.split(",")[0],whichSide);
		if(status == Constants.OK) {
			PopulateL3rdBaseColor(whatToProcess.split(",")[0], whichSide);
			HideAndShowL3rdSubStrapContainers(whichSide);
			setPositionOfLT(whatToProcess,whichSide,config,lowerThird.getNumberOfSubLines());
			return PopulateL3rdBody(whichSide, whatToProcess.split(",")[0]);
		}else {
			return status;
		}
	}
	public String populatePhaseComp(String whatToProcess, int whichSide, MatchAllData matchAllData) throws InterruptedException {
		
		inning = matchAllData.getMatch().getInning().stream().filter(inn -> inn.getInningNumber() == 1).findAny().orElse(null);
		inning2 = matchAllData.getMatch().getInning().stream().filter(inn -> inn.getInningNumber() == 2).findAny().orElse(null);
		
		String titl_1 = "20",titl_2 = "20";
		
		if(inning.getInningNumber() == 1) {
			if(matchAllData.getSetup().getReducedOvers() != null) {
				if(Integer.valueOf(matchAllData.getSetup().getReducedOvers()) > 0) {
					titl_1 = matchAllData.getSetup().getReducedOvers();
				}
			}
		}
		
		
		if(inning2.getInningNumber() == 2) {
			if(matchAllData.getSetup().getReducedOvers() != null) {
				if(Integer.valueOf(matchAllData.getSetup().getReducedOvers()) > 0) {
					titl_2 = matchAllData.getSetup().getReducedOvers();
				}
			}
			
			if(matchAllData.getSetup().getTargetOvers() != null && !matchAllData.getSetup().getTargetOvers().isEmpty()) {
				titl_2 = matchAllData.getSetup().getTargetOvers();
			}
		}
		
		int oneToSixRuns1 = 0, sevenToFifteenRuns1 = 0, sixteenToTweentyRuns1 = 0,oneToSixfWkt1 = 0, sevenToFifteenWkt1 = 0, sixteenToTweentyWkt1 = 0;
		int oneToSixRuns2 = 0, sevenToFifteenRuns2 = 0, sixteenToTweentyRuns2 = 0,oneToSixfWkt2 = 0, sevenToFifteenWkt2 = 0, sixteenToTweentyWkt2 = 0;
		
		List<OverByOverData> overByOverData1 = CricketFunctions.getOverByOverData(matchAllData, 1, "MANHATTAN", matchAllData.getEventFile().getEvents());
		List<OverByOverData> overByOverData2 = CricketFunctions.getOverByOverData(matchAllData, 2, "MANHATTAN", matchAllData.getEventFile().getEvents());
		
		for(int i=0; i<2; i++) {
			
			if(matchAllData.getSetup().getMatchType().equalsIgnoreCase(CricketUtil.ODI) ||
					matchAllData.getSetup().getMatchType().equalsIgnoreCase(CricketUtil.OD)) {
				if(matchAllData.getMatch().getInning().get(i).getInningNumber() == 1) {
					
					for(int j=1; j<=overByOverData1.size()-1; j++) {
						if(j>0 && j<=10) {
							oneToSixRuns1+= overByOverData1.get(j).getOverTotalRuns();
							oneToSixfWkt1+=overByOverData1.get(j).getOverTotalWickets();
						}
						if(j>10 && j<=40) {
							sevenToFifteenRuns1+= overByOverData1.get(j).getOverTotalRuns();
							sevenToFifteenWkt1+=overByOverData1.get(j).getOverTotalWickets();
						}
						if(j>40 && j<=50) {
							sixteenToTweentyRuns1+= overByOverData1.get(j).getOverTotalRuns();
							sixteenToTweentyWkt1+=overByOverData1.get(j).getOverTotalWickets();
						}
					}
				}else if(matchAllData.getMatch().getInning().get(i).getInningNumber() == 2) {
					
					for(int j=1; j<=overByOverData2.size()-1; j++) {
						if(j>0 && j<=10) {
							oneToSixRuns2+= overByOverData2.get(j).getOverTotalRuns();
							oneToSixfWkt2+=overByOverData2.get(j).getOverTotalWickets();
						}
						if(j>10 && j<=40) {
							sevenToFifteenRuns2+= overByOverData2.get(j).getOverTotalRuns();
							sevenToFifteenWkt2+=overByOverData2.get(j).getOverTotalWickets();
						}
						if(j>40 && j<=50) {
							sixteenToTweentyRuns2+= overByOverData2.get(j).getOverTotalRuns();
							sixteenToTweentyWkt2+=overByOverData2.get(j).getOverTotalWickets();
						}
					}
				}
			}else {
				if(matchAllData.getMatch().getInning().get(i).getInningNumber() == 1) {
					
					for(int j=1; j<=overByOverData1.size()-1; j++) {
						if(j>0 && j<=6) {
							oneToSixRuns1+= overByOverData1.get(j).getOverTotalRuns();
							oneToSixfWkt1+=overByOverData1.get(j).getOverTotalWickets();
						}
						if(j>6 && j<=15) {
							sevenToFifteenRuns1+= overByOverData1.get(j).getOverTotalRuns();
							sevenToFifteenWkt1+=overByOverData1.get(j).getOverTotalWickets();
						}
						if(j>15 && j<=20) {
							sixteenToTweentyRuns1+= overByOverData1.get(j).getOverTotalRuns();
							sixteenToTweentyWkt1+=overByOverData1.get(j).getOverTotalWickets();
						}
					}
				}else if(matchAllData.getMatch().getInning().get(i).getInningNumber() == 2) {
					
					for(int j=1; j<=overByOverData2.size()-1; j++) {
						if(j>0 && j<=6) {
							oneToSixRuns2+= overByOverData2.get(j).getOverTotalRuns();
							oneToSixfWkt2+=overByOverData2.get(j).getOverTotalWickets();
						}
						if(j>6 && j<=15) {
							sevenToFifteenRuns2+= overByOverData2.get(j).getOverTotalRuns();
							sevenToFifteenWkt2+=overByOverData2.get(j).getOverTotalWickets();
						}
						if(j>15 && j<=20) {
							sixteenToTweentyRuns2+= overByOverData2.get(j).getOverTotalRuns();
							sixteenToTweentyWkt2+=overByOverData2.get(j).getOverTotalWickets();
						}
					}
				}
			}
		}
		
		if(matchAllData.getSetup().getMatchType().equalsIgnoreCase(CricketUtil.ODI) ||
				matchAllData.getSetup().getMatchType().equalsIgnoreCase(CricketUtil.OD)) {
			lowerThird = new LowerThird("", "COMPARISON", "","","", "",3,"","TLogo_White",
					new String[]{"OVERS 1 TO 10", "OVERS 11 TO 40", "OVERS 41 TO "+ titl_2},new String[]{oneToSixRuns1 + "," + oneToSixRuns2 + "-" + oneToSixfWkt1 + "," + oneToSixfWkt2,
					sevenToFifteenRuns1 + "," + sevenToFifteenRuns2 + "-" + sevenToFifteenWkt1 + "," + sevenToFifteenWkt2,sixteenToTweentyRuns1 + "," + 
					sixteenToTweentyRuns2 + "-" + sixteenToTweentyWkt1 + "," + sixteenToTweentyWkt2},
					new String[] {"OVERS", "SCORE"},null,new String[]{"-97","31","171"});
		}else {
			lowerThird = new LowerThird("", "COMPARISON", "","","", "",3,"","TLogo",
					new String[]{"OVERS 1 TO 6", "OVERS 7 TO 15", "OVERS 16 TO 20"},new String[]{oneToSixRuns1 + "," + oneToSixRuns2 + "-" + oneToSixfWkt1 + "," + oneToSixfWkt2,
					sevenToFifteenRuns1 + "," + sevenToFifteenRuns2 + "-" + sevenToFifteenWkt1 + "," + sevenToFifteenWkt2,sixteenToTweentyRuns1 + "," + 
					sixteenToTweentyRuns2 + "-" + sixteenToTweentyWkt1 + "," + sixteenToTweentyWkt2},
					new String[] {"OVERS", "SCORE"},null,new String[]{"-97","31","171"});
		}
		
		status = PopulateL3rdHeader(whatToProcess.split(",")[0],whichSide);
		if(status == Constants.OK) {
			PopulateL3rdBaseColor(whatToProcess.split(",")[0], whichSide);
			HideAndShowL3rdSubStrapContainers(whichSide);
			setPositionOfLT(whatToProcess,whichSide,config,lowerThird.getNumberOfSubLines());
			return PopulateL3rdBody(whichSide, whatToProcess.split(",")[0]);
		}else {
			return status;
		}
	}
	public String populateL3rdComparison (String whatToProcess,int WhichSide,MatchAllData matchAllData) throws InterruptedException
	{
		if (matchAllData == null || matchAllData.getMatch() == null || matchAllData.getMatch().getInning() == null) {
			return status;
		} else {
			
			inning = matchAllData.getMatch().getInning().stream().filter(inn -> inn.getIsCurrentInning().equalsIgnoreCase(CricketUtil.YES)
					&& inn.getInningNumber() == 2).findAny().orElse(null);
			
			if(inning == null) {
				return "populateL3rdComparison: Inning is Not Found";
			}
			
			String in_data= CricketFunctions.compareInning_Data(matchAllData, ",", 1, matchAllData.getEventFile().getEvents());
			System.out.println("in_data = " + in_data);
			switch (config.getBroadcaster().toUpperCase()) {
			case Constants.TRI_SERIES:  case Constants.MT20: case Constants.BAN_AFG_SERIES: case Constants.ACC:
				lowerThird = new LowerThird("AFTER",  inning.getBowling_team().getTeamBadge(), 
						inning.getBatting_team().getTeamBadge(),"", "",CricketFunctions.OverBalls(inning.getTotalOvers(), inning.getTotalBalls()), 
						2, "FLAG" ,"",new String[]{"FOURS" + "," + String.valueOf(in_data.split(",")[3]), String.valueOf(inning.getTotalFours()),
						"SIXES" + "," + String.valueOf(in_data.split(",")[2]) , String.valueOf(inning.getTotalSixes())},null,new String[]{inning.getBowling_team().getTeamName1(), 
						" WERE ",  String.valueOf(in_data.split(",")[0] + "-" + in_data.split(",")[1]),inning.getBatting_team().getTeamName1(), 
						"ARE", String.valueOf(inning.getTotalRuns() + "-" + inning.getTotalWickets())},null,new String[] {"328.0","406.0","515.0","585.0"});
				break;
			
			
			}
			
			status = PopulateL3rdHeader(whatToProcess.split(",")[0],WhichSide);
			if(status == Constants.OK) {
				PopulateL3rdBaseColor(whatToProcess.split(",")[0], WhichSide);
				return PopulateL3rdBody(WhichSide, whatToProcess.split(",")[0]);
			}else {
				return status;
			}
		}				
	}
	public String populateLTMatchId(String whatToProcess,int WhichSide,MatchAllData matchAllData) throws InterruptedException
	{
		
		String Footer = "";
		
		for(VariousText vt : VariousText) {
			if(vt.getVariousType().equalsIgnoreCase("LT_MATCH_ID") && vt.getUseThis().equalsIgnoreCase(CricketUtil.YES)) {
				Footer = vt.getVariousText();
				break;
			}else if(vt.getVariousType().equalsIgnoreCase("LT_MATCH_ID") && vt.getUseThis().equalsIgnoreCase(CricketUtil.NO)) {
				Footer = "FROM " + matchAllData.getSetup().getGround().getFullname();
			}
		}
		
		matchname = matchAllData.getSetup().getMatchIdent();
		lowerThird = new LowerThird(matchAllData.getSetup().getHomeTeam().getTeamBadge(), matchAllData.getSetup().getAwayTeam().getTeamBadge(), 
				Footer,matchAllData.getSetup().getHomeTeam().getTeamName2(), matchAllData.getSetup().getHomeTeam().getTeamName3(),
				matchAllData.getSetup().getAwayTeam().getTeamName2(),1,matchAllData.getSetup().getAwayTeam().getTeamName3(),"",null,null,null,null,null);
		
		
		status = PopulateL3rdHeader(whatToProcess.split(",")[0],WhichSide);
		if(status == Constants.OK) {
			PopulateL3rdBaseColor(whatToProcess.split(",")[0], WhichSide);
			return PopulateL3rdBody(WhichSide, whatToProcess.split(",")[0]);
		} else {
			return status;
		}
	}
	
	public String InfobarManhattan(List<PrintWriter> print_writers, MatchAllData matchAllData,int WhichInning) {
		
		int maxRuns = 0,runsIncr = 0,powerplay_omo=0;
		double lngth = 0;
		String powerPlay = "",value = "";
		
		List<Integer> ballCount = null;
		manhattan = new ArrayList<OverByOverData>();
		manhattan = CricketFunctions.getOverByOverData(matchAllData, WhichInning,"MANHATTAN" ,matchAllData.getEventFile().getEvents());
		if(manhattan == null) {
			return "populateManhattan is null";
		}
		
		inning = matchAllData.getMatch().getInning().stream().filter(inn -> inn.getInningNumber() == WhichInning)
				.findAny().orElse(null);
		if(inning == null) {
			return "PopulateManhattan: current inning is NULL";
		}
		
		CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT_Manhattan$TopLine$LogoAll$img_Logos*TEXTURE*IMAGE SET " 
				+ (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? 
						Constants.TRI_SERIES_LOGO : Constants.MT20_LOGO) + inning.getBatting_team().getTeamBadge() + "\0", print_writers);
		
		for (int j = 1; j < manhattan.size(); j++) {
			if(manhattan.get(j).getInningNumber() == WhichInning) {
				if(Integer.valueOf(manhattan.get(j).getOverTotalRuns()) > maxRuns){
					maxRuns = Integer.valueOf(manhattan.get(j).getOverTotalRuns()); // 33 runs came off 34th over
				}
				while (maxRuns % 3 != 0) {     // 5 label in y-axis
			 		maxRuns = maxRuns + 1;    // keep incrementing till max runs is divisible by 5. maxRuns = 35
				}
			}
		}
		
		for(int i = 1; i <= 3;i++) {
			runsIncr = maxRuns / 3; // 35/5=7 -> Y axis will be plot like 6,12,18,24,30 & 36
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT_Manhattan$BarAll$Runs_Axis$Runs_Data$txt_" + i + 
					"*GEOM*TEXT SET " + runsIncr*(i) + "\0", print_writers);
		}
		CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT_Manhattan$BarAll$Bar$Position*FUNCTION*Grid*num_row SET 1\0", print_writers);
		
		ballCount = CricketFunctions.getBallCountStartAndEndRange(matchAllData, matchAllData.getMatch().getInning().get(WhichInning - 1));
		for(int j = 1; j <= matchAllData.getSetup().getMaxOvers(); j++) {
			
			if(j < manhattan.size()) {
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT_Manhattan$BarAll$Bar$Position*FUNCTION*Grid*num_col SET " + j + "\0", print_writers);
				
				if((j*6) <= ballCount.get(1)) {
					powerplay_omo = 0;
					powerPlay = "$PowerPlay";
					value = "$img_Base2---";
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT_Manhattan$BarAll$Bar$" + j + "$obj_ScaleY" + powerPlay + "$img_Base2"
							+ "*TEXTURE*IMAGE SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? 
									Constants.TRI_SERIES_BASE2 : Constants.MT20_BASE2) +  inning.getBatting_team().getTeamBadge() + "\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT_Manhattan$BarAll$Bar$" + j + "$Wkt" + powerPlay + "$img_Base1"
							+ "*TEXTURE*IMAGE SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? 
									Constants.TRI_SERIES_BASE1 : Constants.MT20_BASE1) +  inning.getBatting_team().getTeamBadge() + "\0", print_writers);
				}else {
					powerplay_omo = 1;
					powerPlay = "$Normal";
					value = "$img_Base1--";
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT_Manhattan$BarAll$Bar$" + j + "$obj_ScaleY" + powerPlay + "$img_Base1"
							+ "*TEXTURE*IMAGE SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? 
									Constants.TRI_SERIES_BASE1 : Constants.MT20_BASE1) +  inning.getBatting_team().getTeamBadge() + "\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT_Manhattan$BarAll$Bar$" + j + "$Wkt" + powerPlay + "$img_Base2"
							+ "*TEXTURE*IMAGE SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? 
									Constants.TRI_SERIES_BASE2 : Constants.MT20_BASE2) +  inning.getBatting_team().getTeamBadge() + "\0", print_writers);
				}
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT_Manhattan$BarAll$Bar$" + j + "$obj_ScaleY$Select_PowerPlay"
						+ "*FUNCTION*Omo*vis_con SET " + powerplay_omo + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT_Manhattan$BarAll$Bar$" + j + "$Wkt$Select_PowerPlay"
						+ "*FUNCTION*Omo*vis_con SET " + powerplay_omo + "\0", print_writers);
				
				lngth = ((40.4 * Integer.valueOf(manhattan.get(j).getOverTotalRuns())) / maxRuns);
				
				if(lngth == 0) {
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT_Manhattan$BarAll$Bar$" + j + "$obj_ScaleY" + powerPlay + value
							+ "*ACTIVE SET 0\0", print_writers);
				}else {
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT_Manhattan$BarAll$Bar$" + j + "$obj_ScaleY" + powerPlay + value
							+ "*ACTIVE SET 1\0", print_writers);
				}
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT_Manhattan$BarAll$Bar$" + j + "$obj_ScaleY" + powerPlay + "$Bar"
						+ "*GEOM*height SET " + lngth + "\0", print_writers);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT_Manhattan$BarAll$Bar$" + j + "$Wkt" + powerPlay + "$Select_Wickets"
						+ "*FUNCTION*Omo*vis_con SET " + manhattan.get(j).getOverTotalWickets() + "\0", print_writers);
			}
		}
		return Constants.OK;
		
	}
	public String populateWeather(String whatToProcess) {
		weatherData = cricketService.getWeather().get(0);
		if(weatherData == null) {
			return "populateWeather: weather is NULL";
		}
		
		CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT_Weather$Overall_Position_Y_In_Out$TopLine$LogoAll$Side1$img_Logos*TEXTURE*IMAGE SET " 
				+ (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? 
						Constants.TRI_SERIES_LOGO : Constants.MT20_LOGO) + "EVENT" + " \0", print_writers);
	
		CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT_Weather$TopLine$TextAll$select_DataType*FUNCTION*Omo*vis_con SET 0\0", print_writers);
		for(VariousText vt : VariousText) {
			if(vt.getVariousType().equalsIgnoreCase("WEATHER") && vt.getUseThis().equalsIgnoreCase(CricketUtil.YES)) {
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT_Weather$TopLine$TextAll$select_DataType$Name$txt_Title1*GEOM*TEXT SET " 
						+ vt.getVariousText() + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT_Weather$TopLine$TextAll$select_DataType$Name$txt_Title2*GEOM*TEXT SET " 
						+ "" + "\0", print_writers);
				break;
			}else if(vt.getVariousType().equalsIgnoreCase("WEATHER") && vt.getUseThis().equalsIgnoreCase(CricketUtil.NO)) {
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT_Weather$TopLine$TextAll$select_DataType$Name$txt_Title1*GEOM*TEXT SET WEATHER\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT_Weather$TopLine$TextAll$select_DataType$Name$txt_Title2*GEOM*TEXT SET " 
						+ "" + "\0", print_writers);
			}
		}
		
		CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT_Weather$SublineGrp$Rows$Select$Icon_GRP$Icon*TEXTURE*IMAGE SET " + Constants.TRI_SERIES_WEATHERICON + 
				"Icon_" + weatherData.getIconType()+ "\0", print_writers);
		CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT_Weather$SublineGrp$Rows$Select$Icon_GRP$Icon_Shadow*TEXTURE*IMAGE SET " + Constants.TRI_SERIES_WEATHERICON + 
				"Icon_" + weatherData.getIconType()+ "\0", print_writers);
		
		
		CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT_Weather$SublineGrp$Rows$Select$1$txt_Info*GEOM*TEXT SET " + "TEMPERATURE" + "\0", print_writers);
		CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT_Weather$SublineGrp$Rows$Select$2$txt_Info*GEOM*TEXT SET " + "HUMIDITY" + "\0", print_writers);
		CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT_Weather$SublineGrp$Rows$Select$3$txt_Info*GEOM*TEXT SET " + "WIND SPEED" + "\0", print_writers);
		
		CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT_Weather$SublineGrp$Rows$Select$1$txt_Temparature*GEOM*TEXT SET " 
				+ weatherData.getCurrentTemp() + "\0", print_writers);
		CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT_Weather$SublineGrp$Rows$Select$2$txt_Humidity*GEOM*TEXT SET " 
				+ weatherData.getHumidity() + "\0", print_writers);
		CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT_Weather$SublineGrp$Rows$Select$3$txt_Speed*GEOM*TEXT SET " 
				+ weatherData.getWindSpeed() + "\0", print_writers);
		
		return Constants.OK;
	}
	public String populateL3rdLineUp(String whatToProcess,int WhichSide,MatchAllData matchAllData) throws InterruptedException, StreamReadException, DatabindException, IOException
	{
		if (matchAllData == null || matchAllData.getMatch() == null || matchAllData.getMatch().getInning() == null) {
			return status;
		} 
		
		inning = matchAllData.getMatch().getInning().stream().filter(inn -> inn.getBattingTeamId() == Integer.valueOf(whatToProcess.split(",")[2])).findAny().orElse(null);
		if(inning == null) {
			return status;
		}
		
		WhichProfile = whatToProcess.split(",")[3].trim().toUpperCase();
		if(WhichProfile.equalsIgnoreCase("ROLES")) {
			PlayersList = (matchAllData.getSetup().getHomeTeamId() == Integer.valueOf(whatToProcess.split(",")[2])? matchAllData.getSetup().getHomeSquad()
					:matchAllData.getSetup().getAwaySquad());
			team = (matchAllData.getSetup().getHomeTeamId() == Integer.valueOf(whatToProcess.split(",")[2])? matchAllData.getSetup().getHomeTeam()
					:matchAllData.getSetup().getAwayTeam());
		}else {
			battingCardList = inning.getBattingCard();
			team = inning.getBatting_team();
		}
		
		String MatchFileName = null;
		PlayerId = new ArrayList<Integer>();
		PlayerIdIn = new ArrayList<Integer>();
		
		if(headToHead.size() > 1) {
			for (int i = headToHead.size() - 1; i >= 0; i--) {
			    if (headToHead.get(i).getTeam().getTeamId() == team.getTeamId()) {

			    	if (MatchFileName == null) {
			    		MatchFileName = headToHead.get(i).getMatchFileName(); 
			        }
			        if (!headToHead.get(i).getMatchFileName().equalsIgnoreCase(MatchFileName)) {
			            break;
			        }
			    }
			}
		}else {
			MatchFileName = matchAllData.getMatch().getMatchFileName();
		}
		
		if(MatchFileName != null) {
			Setup setup = new ObjectMapper().readValue(new File(CricketUtil.CRICKET_DIRECTORY + CricketUtil.SETUP_DIRECTORY + 
					MatchFileName), Setup.class);
			
			for(Player headToHead : (setup.getHomeTeamId()==team.getTeamId() ? setup.getHomeSquad():setup.getAwaySquad())) {
				boolean playerFound = false;
				for (Player ply : PlayersList) {
		    	    if(ply.getPlayerId() == headToHead.getPlayerId()) {
		    	    	playerFound = true;
		    	    	break;
		    	    }
		    	}
		        if (!playerFound) {
		        	PlayerId.add(headToHead.getPlayerId());
		        }else {
		        	PlayerIdIn.add(headToHead.getPlayerId());
		        }
			}
		}
		System.out.println("PlayerId - " + PlayerId);
		System.out.println("PlayerIdIn - " + PlayerIdIn);
		
		match = matchAllData;
		lowerThird = new LowerThird(whatToProcess.split(",")[3], "", "","", "","", 2, "",inning.getBatting_team().getTeamBadge(),
				null,null,null,null,null);
		
		status = PopulateL3rdHeader(whatToProcess.split(",")[0],WhichSide);
		if(status == Constants.OK) {
			HideAndShowL3rdSubStrapContainers(WhichSide);
			setPositionOfLT(whatToProcess,WhichSide,config,lowerThird.getNumberOfSubLines());

			return PopulateL3rdBody(WhichSide, whatToProcess.split(",")[0]);
		} else {
			return status;
		}
	}
	public String populateLTMatchPromo(String whatToProcess, int WhichSide, MatchAllData matchAllData) throws InterruptedException {
		
		fixture = fixTures.stream().filter(fix -> fix.getMatchnumber() == 
				Integer.valueOf(whatToProcess.split(",")[2])).findAny().orElse(null);
		
		if(fixture == null) {
			return "Fixture id [" + Integer.valueOf(whatToProcess.split(",")[2]) + "] from database is returning NULL";
		}
		
		String[] dateSuffix = {
				"th", "st", "nd", "rd", "th", "th", "th", "th", "th", "th",
				
				"th", "th", "th", "th", "th", "th", "th", "th", "th", "th",
				
				"th", "st", "nd", "rd", "th", "th", "th", "th", "th","th",
				
				"th", "st"
		};
		
		switch (config.getBroadcaster().toUpperCase()) {
		case Constants.ACC:
			match_name = fixture.getTeamgroup();
			break;

		default:
			if(fixture.getMatchnumber() > 9) {
				match_name = String.valueOf(getOrdinalMatch(fixture.getMatchfilename().replace("MATCH", "")));
			}else {
				match_name = String.valueOf(getOrdinalMatch(fixture.getMatchfilename().replace("MATCH", "")));
			}
			break;
		}
		
		
		Calendar cal_bengal = Calendar.getInstance();
		cal_bengal.add(Calendar.DATE, +1);
		if(fixture.getDate().equalsIgnoreCase(new SimpleDateFormat("dd-MM-yyyy").format(cal_bengal.getTime()))) {
			
			text = "TOMORROW - " + fixture.getLocalTime() + " - " + fixture.getVenue();
			matchday = "TOMORROW - " + fixture.getLocalTime() + " - " + fixture.getVenue();
		}else {
			cal_bengal.add(Calendar.DATE, -1);
			if(fixture.getDate().equalsIgnoreCase(new SimpleDateFormat("dd-MM-yyyy").format(cal_bengal.getTime()))) {
				text = "UP NEXT - " + match_name + " - LIVE FROM " + fixture.getVenue();
				matchday = "UP NEXT " + " - LIVE FROM " + fixture.getVenue();
			}else {
				newDate = fixture.getDate().split("-")[0];
				if(Integer.valueOf(newDate) < 10) {
					newDate = newDate.replaceFirst("0", "");
				}
				date_data = newDate + dateSuffix[Integer.valueOf(newDate)] + " " + 
						Month.of(Integer.valueOf(fixture.getDate().split("-")[1]));
				
				text = date_data + " - " + fixture.getLocalTime() + " - " + fixture.getVenue();
				matchday = date_data + " - " + fixture.getLocalTime() + " - " + fixture.getVenue();
			}
		}
		
		for(VariousText vt : VariousText) {
			if(vt.getVariousType().equalsIgnoreCase("LT_MATCH_PROMO") && vt.getUseThis().equalsIgnoreCase(CricketUtil.YES)) {
				matchday = vt.getVariousText();
				break;
			}
		}
		
		lowerThird = new LowerThird(fixture.getHome_Team().getTeamName2() + "-" + fixture.getHome_Team().getTeamName3(), 
				fixture.getAway_Team().getTeamName2() + "-" + fixture.getAway_Team().getTeamName3(),
				"",fixture.getHome_Team().getTeamBadge(), fixture.getAway_Team().getTeamBadge(), text,2,"Emirates","",null,null,
				null,null,null);
		
		status = PopulateL3rdHeader(whatToProcess.split(",")[0],WhichSide);
		if(status == Constants.OK) {
			
			return PopulateL3rdBody(WhichSide, whatToProcess.split(",")[0]);
		} else {
			return status;
		}
	}
	public String populateNextToBat(String whatToProcess, int whichSide, MatchAllData matchAllData) throws InterruptedException {
		inning = matchAllData.getMatch().getInning().stream().filter(inn -> inn.getIsCurrentInning().equalsIgnoreCase(CricketUtil.YES)).findAny().orElse(null);
		if(inning == null) {
			return "Select the current inning";
		}
		battingCardList = inning.getBattingCard().stream().filter(bc ->bc.getStatus().equalsIgnoreCase(CricketUtil.STILL_TO_BAT)).collect(Collectors.toList());
		team = inning.getBatting_team();
		
		lowerThird = new LowerThird("", "", "","", "", "",0,"",inning.getBatting_team().getTeamBadge(), null,null,null,null,null);
		
		status = PopulateL3rdHeader(whatToProcess.split(",")[0],whichSide);
		if(status == Constants.OK) {
			return PopulateL3rdBody(whichSide, whatToProcess.split(",")[0]);
		} else {
			return status;
		}
	}
	public String populateDlsTarget(String whatToProcess,int WhichSide,MatchAllData matchAllData) throws InterruptedException
	{
		int balls = 0, overs = 0;
		if (matchAllData == null || matchAllData.getMatch() == null || matchAllData.getMatch().getInning() == null) {
			return status;
		} else {
			
			if(config.getCategory().equalsIgnoreCase("MEN")) {
				logoCategory = "M";
			}else if(config.getCategory().equalsIgnoreCase("WOMEN")) {
				logoCategory = "W";
			}else {
				logoCategory = "";
			}
			
			inning = matchAllData.getMatch().getInning().stream().filter(inn -> inn.getIsCurrentInning().equalsIgnoreCase(CricketUtil.YES)).findAny().orElse(null);
			
			if(inning == null) {
				return "populateTeamSummary: Inning is Not Found";
			}
		}
		
		this_data_str = new ArrayList<String>();
		if(dls == null) {
			return "populateTeamSummary: dls is Null";
		}
		
		if(whatToProcess.split(",")[2].equalsIgnoreCase("currentOver")) {
			overs = inning.getTotalOvers();
			balls = inning.getTotalBalls();
		}else if(whatToProcess.split(",")[2].equalsIgnoreCase("nextBall")) {
			overs = inning.getTotalOvers();
			balls = inning.getTotalBalls() + 1;
		}else if(whatToProcess.split(",")[2].equalsIgnoreCase("nextOver")) {
			overs = inning.getTotalOvers() + 1;
			balls = 0;
		}
		
		
		for(int i = 0; i<= dls.size() -1;i++) {
			if(dls.get(i).getOver_left().split("\\.")[0].equalsIgnoreCase(String.valueOf(overs))) {
				for(int j=0;j<6;j++) {
					if(balls == j) {
						this_data_str.add(CricketFunctions.populateDuckWorthLewis(matchAllData, CricketUtil.CRICKET_DIRECTORY).get(i+j).getWkts_down());
						break;
					}
				}
				break;
			}
		}
		
		if(CricketFunctions.populateDls(matchAllData, CricketUtil.FULL, Integer.valueOf(this_data_str.get(0))).trim().isEmpty()) {
			return "error";
		}
		
		this_data_str.add(CricketFunctions.populateDls(matchAllData, CricketUtil.FULL, Integer.valueOf(this_data_str.get(0))));
		
		if(this_data_str == null) {
			return "error";
		}
		
		lowerThird = new LowerThird("DUCKWORTH LEWIS STERN", matchAllData.getSetup().getHomeTeam().getTeamName4(), 
				matchAllData.getSetup().getAwayTeam().getTeamName4(),"", String.valueOf(CricketFunctions.OverBalls(overs, balls)), 
				"",2,"TLogo","", null,null,new String[]{this_data_str.get(0),this_data_str.get(1)},null,null);
		
		status = PopulateL3rdHeader(whatToProcess.split(",")[0],WhichSide);
		if(status == Constants.OK) {
			HideAndShowL3rdSubStrapContainers(WhichSide);
			setPositionOfLT(whatToProcess,WhichSide,config,lowerThird.getNumberOfSubLines());

			return PopulateL3rdBody(WhichSide, whatToProcess.split(",")[0]);
		} else {
			return status;
		}
	}
	public String populateGeneric(String whatToProcess,int WhichSide,MatchAllData matchAllData) throws InterruptedException
	{
		String data = "";
		
		if (matchAllData == null || matchAllData.getMatch() == null || matchAllData.getMatch().getInning() == null) {
			return status;
		}
		
		if(whatToProcess.split(",")[2].equalsIgnoreCase("CRR")){
			inning = matchAllData.getMatch().getInning().stream().filter(inn -> 
			inn.getInningNumber() == Integer.valueOf(whatToProcess.split(",")[1])).findAny().orElse(null);
			
			if(inning == null) {
				return "populateGeneric: Inning is Not Found";
			}
			data = "CURRENT RUN RATE : " + CricketFunctions.generateRunRate(inning.getTotalRuns(),inning.getTotalOvers(), inning.getTotalBalls(), 2,matchAllData);
		}else if(whatToProcess.split(",")[2].equalsIgnoreCase("RRR")){
			inning = matchAllData.getMatch().getInning().stream().filter(inn -> 
			inn.getInningNumber() == Integer.valueOf(whatToProcess.split(",")[1])).findAny().orElse(null);
			
			if(inning == null) {
				return "populateGeneric: Inning is Not Found";
			}
			data = "REQUIRED RUN RATE : " + CricketFunctions.generateRunRate(CricketFunctions.GetTargetData(matchAllData).getRemaningRuns(),0,CricketFunctions.GetTargetData(matchAllData).getRemaningBall(),2,matchAllData);
		}else if(whatToProcess.split(",")[2].equalsIgnoreCase("LAST_WICKET")) {
			inning = matchAllData.getMatch().getInning().stream().filter(inn -> 
				inn.getIsCurrentInning().equalsIgnoreCase(CricketUtil.YES)).findAny().orElse(null);
			
			String how_out_txt = "";
			
			if(inning == null) {
				return "populateGeneric: Inning returned is NULL";
			}
			
			if(inning.getFallsOfWickets() == null && inning.getFallsOfWickets().isEmpty()) {
				return "populateGeneric: FoW returned is EMPTY";
			}
			
			battingCardList.add(inning.getBattingCard().stream().filter(bc -> bc.getPlayerId() == 
				inning.getFallsOfWickets().get(inning.getFallsOfWickets().size() - 1).getFowPlayerID()).findAny().orElse(null));
	
			if(battingCardList.get(battingCardList.size()-1) == null) {
				return "populateGeneric: Last wicket returned is invalid";
			}
			
			how_out_txt = battingCardList.get(battingCardList.size()-1).getPlayer().getTicker_name();
			
			if(battingCardList.get(battingCardList.size()-1).getHowOut().equalsIgnoreCase(CricketUtil.RUN_OUT)) {
				if(battingCardList.get(battingCardList.size()-1).getWasHowOutFielderSubstitute() != null && 
						battingCardList.get(battingCardList.size()-1).getWasHowOutFielderSubstitute().equalsIgnoreCase(CricketUtil.YES)) {
					how_out_txt = how_out_txt + " run out " + " (sub - " + battingCardList.get(battingCardList.size()-1).getHowOutFielder().getTicker_name() + ")";
				} else {
					how_out_txt = how_out_txt + " run out (" + battingCardList.get(battingCardList.size()-1).getHowOutFielder().getTicker_name() + ")";
				}
			}else if(battingCardList.get(battingCardList.size()-1).getHowOut().equalsIgnoreCase(CricketUtil.STUMPED)) {
				if(battingCardList.get(battingCardList.size()-1).getWasHowOutFielderSubstitute() != null && 
						battingCardList.get(battingCardList.size()-1).getWasHowOutFielderSubstitute().equalsIgnoreCase(CricketUtil.YES)) {
					how_out_txt = how_out_txt + " st" +  " (sub - " + battingCardList.get(battingCardList.size()-1).getHowOutFielder().getTicker_name() + ")  b " + 
							battingCardList.get(battingCardList.size()-1).getHowOutBowler().getTicker_name();
				} else {
					how_out_txt = how_out_txt + " st " + battingCardList.get(battingCardList.size()-1).getHowOutFielder().getTicker_name() + "  b " + 
							battingCardList.get(battingCardList.size()-1).getHowOutBowler().getTicker_name();
				}
			}
			else if(battingCardList.get(battingCardList.size()-1).getHowOut().equalsIgnoreCase(CricketUtil.CAUGHT)) {
				if(battingCardList.get(battingCardList.size()-1).getWasHowOutFielderSubstitute() != null && 
						battingCardList.get(battingCardList.size()-1).getWasHowOutFielderSubstitute().equalsIgnoreCase(CricketUtil.YES)) {
					how_out_txt = how_out_txt + " c" +  " (sub - " + battingCardList.get(battingCardList.size()-1).getHowOutFielder().getTicker_name() + ")  b " + 
							battingCardList.get(battingCardList.size()-1).getHowOutBowler().getTicker_name();
				} else {
					how_out_txt = how_out_txt + " c " + battingCardList.get(battingCardList.size()-1).getHowOutFielder().getTicker_name() + "  b " + 
							battingCardList.get(battingCardList.size()-1).getHowOutBowler().getTicker_name();
				}
			}else {
				if(!battingCardList.get(battingCardList.size()-1).getHowOutPartOne().isEmpty()) {
					how_out_txt = how_out_txt + " " + battingCardList.get(battingCardList.size()-1).getHowOutPartOne();
				}
				
				if(!battingCardList.get(battingCardList.size()-1).getHowOutPartTwo().isEmpty()) {
					if(!how_out_txt.trim().isEmpty()) {
						how_out_txt = how_out_txt + "  " + battingCardList.get(battingCardList.size()-1).getHowOutPartTwo();
					}else {
						how_out_txt = how_out_txt + " " + battingCardList.get(battingCardList.size()-1).getHowOutPartTwo();
					}
				}
			}
			
			data = "LAST WICKET : " + how_out_txt + " " + battingCardList.get(battingCardList.size()-1).getRuns() + " (" + 
					battingCardList.get(battingCardList.size()-1).getBalls() + ")";
			
		}else if(whatToProcess.split(",")[2].equalsIgnoreCase("CURR_PART")) {
			inning = matchAllData.getMatch().getInning().stream().filter(inn -> 
				inn.getIsCurrentInning().equalsIgnoreCase(CricketUtil.YES)).findAny().orElse(null);
		
			if(inning == null) {
				return "populateGeneric: Inning returned is NULL";
			}
			
			if(inning.getPartnerships() != null && inning.getPartnerships().size() <= 0) {
				return "populateGeneric: Partnership size is NULL/Zero";
			}
			
			data = CricketFunctions.ordinal(inning.getPartnerships().get(inning.getPartnerships().size()-1).getPartnershipNumber()) + " WICKET PARTNERSHIP";
			
		}else if(whatToProcess.split(",")[2].equalsIgnoreCase("BOUNDARIES")) {
			inning = matchAllData.getMatch().getInning().stream().filter(inn -> 
			inn.getIsCurrentInning().equalsIgnoreCase(CricketUtil.YES)).findAny().orElse(null);

			if(inning == null) {
				return "populateGeneric: Inning return is NULL";
			}
			
			data = "INNINGS BOUNDARIES : FOURS : " + inning.getTotalFours() + "  SIXES : " + inning.getTotalSixes();
			
		}else if(whatToProcess.split(",")[2].equalsIgnoreCase("CRR_RRR")) {
			inning = matchAllData.getMatch().getInning().stream().filter(inn -> 
			inn.getInningNumber() == Integer.valueOf(whatToProcess.split(",")[1])).findAny().orElse(null);
			
			if(inning == null) {
				return "populateGeneric: Inning is Not Found";
			}
			data = "CURRENT RUN RATE : " + inning.getRunRate() + "           REQUIRED RUN RATE : " + 
					CricketFunctions.generateRunRate(CricketFunctions.GetTargetData(matchAllData).getRemaningRuns(),0,CricketFunctions.GetTargetData(matchAllData).getRemaningBall(),2,matchAllData);
			
		}else if(whatToProcess.split(",")[2].equalsIgnoreCase("COMPARISON")) {
			
			inning = matchAllData.getMatch().getInning().stream().filter(inn -> 
			inn.getIsCurrentInning().equalsIgnoreCase(CricketUtil.YES)).findAny().orElse(null);

			if(inning == null) {
				return "populateGeneric: Inning return is NULL";
			}
			if(CricketFunctions.compareInningData(matchAllData,"-", 1 , matchAllData.getEventFile().getEvents()).split("-")[1].equalsIgnoreCase("10")) {
				data = "AT THIS STAGE : " + matchAllData.getMatch().getInning().get(0).getBatting_team().getTeamName1().toUpperCase() + 
						 " WERE " + CricketFunctions.compareInningData(matchAllData,"-", 1 , matchAllData.getEventFile().getEvents()).split("-")[0];
			}else {
				data = "AT THIS STAGE : " + matchAllData.getMatch().getInning().get(0).getBatting_team().getTeamName1().toUpperCase() + 
						 " WERE " + CricketFunctions.compareInningData(matchAllData,"-", 1 , matchAllData.getEventFile().getEvents());
			}
			
		}
		
		lowerThird = new LowerThird(whatToProcess.split(",")[2], inning.getBatting_team().getTeamName2(), inning.getBatting_team().getTeamName3(),"", String.valueOf(inning.getTotalRuns()) + "-" + String.valueOf(inning.getTotalWickets()), 
				CricketFunctions.OverBalls(inning.getTotalOvers(), inning.getTotalBalls()),2,"",inning.getBatting_team().getTeamBadge(),
				null,null,new String[]{data},null,null);
		
		status = PopulateL3rdHeader(whatToProcess.split(",")[0],WhichSide);
		if(status == Constants.OK) {
			HideAndShowL3rdSubStrapContainers(WhichSide);
			setPositionOfLT(whatToProcess,WhichSide,config,lowerThird.getNumberOfSubLines());
			
			return PopulateL3rdBody(WhichSide,whatToProcess.split(",")[0]);
		} else {
			return status;
		}
	}
	
	public String PopulateL3rdHeader(String whatToProcess, int WhichSide) throws InterruptedException {
	  
		if (WhichSide == 1) {
	        containerName = "$Change_Out";
	    } else if (WhichSide == 2) {
	        containerName = "$Change_In";
	    }

	    switch (config.getBroadcaster().toUpperCase()) {
	    	case Constants.BAN_AFG_SERIES: case Constants.ACC:
	    		switch (whatToProcess) {
	    		case "Alt_d":
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$ALL_LT_LOGOGRP$Side" + WhichSide + 
							"$Select*FUNCTION*Omo*vis_con SET 0 \0",print_writers);
	    			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$All$BaseAll$Side" + WhichSide + 
	    					"$Select*FUNCTION*Omo*vis_con SET 0 \0",print_writers);
	    			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$All$DataAll$Side" + WhichSide + 
	    					"$Select*FUNCTION*Omo*vis_con SET 9 \0",print_writers);
	    			
	    			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$ALL_LT_LOGOGRP$Side" + WhichSide
	    					+ "$Select$OneTeam$LTLogoGRP$LogoIn$LLC_LogoGrp$img_Badges*TEXTURE*IMAGE SET " + Constants.ACC_FLAG
	    					+ "TLogo\0",print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$ALL_LT_LOGOGRP$Side" + WhichSide
	    					+ "$Select$OneTeam$LTLogoGRP$LogoIn$LLC_LogoGrp$img_Badges02*TEXTURE*IMAGE SET " + Constants.ACC_FLAG
	    					+ lowerThird.getWhichTeamFlag() + "\0",print_writers);
					break;
	    		case "l":
	    			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$ALL_LT_LOGOGRP$Side" + WhichSide + "$Select*FUNCTION*Omo*vis_con SET 0 \0",print_writers);
	    			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$All$BaseAll$Side" + WhichSide + "$Select*FUNCTION*Omo*vis_con SET 0 \0",print_writers);
	    			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$All$DataAll$Side" + WhichSide + "$Select*FUNCTION*Omo*vis_con SET 11 \0",print_writers);
					
					
	    			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$All$DataAll$Side" + WhichSide
    						+ "$Select$PlayerProfile$TopGrp$HeaderGrp$txt_Header1*GEOM*TEXT SET "
    						+ lowerThird.getHeaderText() + "\0",print_writers);
    				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$All$DataAll$Side" + WhichSide
    						+ "$Select$PlayerProfile$TopGrp$HeaderGrp$txt_Header2*GEOM*TEXT SET \0",print_writers);
    				
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$All$DataAll$Side" + WhichSide+ "$Select$PlayerProfile$BottomGrp$RestDataGrp$" 
							+ "txt_Data1*GEOM*TEXT SET " + lowerThird.getSubTitle() + "\0",print_writers);
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$ALL_LT_LOGOGRP$Side" + WhichSide
	    					+ "$Select$OneTeam$LTLogoGRP$LogoIn$LLC_LogoGrp$img_Badges*TEXTURE*IMAGE SET " + Constants.ACC_FLAG
	    					+ lowerThird.getWhichTeamFlag() + "\0",print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$ALL_LT_LOGOGRP$Side" + WhichSide
	    					+ "$Select$OneTeam$LTLogoGRP$LogoIn$LLC_LogoGrp$img_Badges02*TEXTURE*IMAGE SET " + Constants.ACC_FLAG
	    					+ lowerThird.getWhichTeamFlag() + "\0",print_writers);
					
	    			break;
	    		case "Alt_Shift_F3":
	    			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$ALL_LT_LOGOGRP$Side" + WhichSide + "$Select*FUNCTION*Omo*vis_con SET 0 \0",print_writers);
	    			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$All$BaseAll$Side" + WhichSide + "$Select*FUNCTION*Omo*vis_con SET 0 \0",print_writers);
	    			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$All$DataAll$Side" + WhichSide + "$Select*FUNCTION*Omo*vis_con SET 18 \0",print_writers);
	    			
	    			if(lowerThird.getWhichTeamFlag() != null) {
	    				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$ALL_LT_LOGOGRP$Side" + WhichSide + "$Select$OneTeam$LTLogoGRP$LogoIn$"
	    	    				+ "LLC_LogoGrp$img_Badges*TEXTURE*IMAGE SET " + Constants.ACC_FLAG + lowerThird.getWhichTeamFlag() + "\0",print_writers);
	    			}
					
	    			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$All$DataAll$Side" + WhichSide+ "$Select$PhaseWise$TopGrp$HeaderGrp$" 
							+ "txt_Header1*GEOM*TEXT SET " + lowerThird.getFirstName() + " " + lowerThird.getSubTitle() + "\0",print_writers);
	    			
	    			break;
	    		case "Control_Shift_B":
	    			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$ALL_LT_LOGOGRP$Side" + WhichSide + "$Select*FUNCTION*Omo*vis_con SET 0 \0",print_writers);
	    			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$All$BaseAll$Side" + WhichSide + "$Select*FUNCTION*Omo*vis_con SET 2 \0",print_writers);
	    			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$All$DataAll$Side" + WhichSide + "$Select*FUNCTION*Omo*vis_con SET 2 \0",print_writers);
	    			
	    			switch (config.getBroadcaster().toUpperCase()) {
	    			case Constants.ACC:
	    				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$ALL_LT_LOGOGRP$Side" + WhichSide + "$Select$OneTeam$LTLogoGRP$LogoIn$"
	    	    				+ "LLC_LogoGrp$img_Badges*TEXTURE*IMAGE SET " + Constants.ACC_FLAG + lowerThird.getWhichTeamFlag() + "\0",print_writers);
	    				break;
	    			}
	    			
	    			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$All$DataAll$Side" + WhichSide+ "$Select$NextToBat$TopGrp$HeaderGrp$" 
							+ "txt_Header1*GEOM*TEXT SET " + "NEXT TO BAT" + "\0",print_writers);
	    		
	    			break;
	    		
	    		case "Control_Shift_O":
	    			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$ALL_LT_LOGOGRP$Side" + WhichSide + "$Select*FUNCTION*Omo*vis_con SET 2 \0",print_writers);
	    			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$All$BaseAll$Side" + WhichSide + "$Select*FUNCTION*Omo*vis_con SET 4 \0",print_writers);
	    			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$All$DataAll$Side" + WhichSide + "$Select*FUNCTION*Omo*vis_con SET 4 \0",print_writers);

	    			switch (config.getBroadcaster().toUpperCase()) {
	    	    	case Constants.BAN_AFG_SERIES:
	    	    		CricketFunctions.DoadWriteCommandToAllViz(
		    					"-1 RENDERER*FRONT_LAYER*TREE*$LT$ALL_LT_LOGOGRP$Side" + WhichSide + "$Select$LineUp$LTLogoGRP$LogoIn$LLC_LogoGrp$img_Badges*TEXTURE*IMAGE SET "
		    							+ Constants.BAN_AFG_SERIES_LOGO + lowerThird.getWhichTeamFlag() + "\0",print_writers);
		    			CricketFunctions.DoadWriteCommandToAllViz(
		    					"-1 RENDERER*FRONT_LAYER*TREE*$LT$ALL_LT_LOGOGRP$Side" + WhichSide + "$Select$LineUp$LTLogoGRP$LogoIn$LLC_LogoGrp$img_Badges02*TEXTURE*IMAGE SET "
		    							+ Constants.BAN_AFG_SERIES_LOGO + lowerThird.getWhichTeamFlag() + "\0",print_writers);

		    			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$ALL_LT_LOGOGRP$Side" + WhichSide + "$Select$LineUp$LogoIn$LLC_LogoGrp$img_Base2*TEXTURE*IMAGE SET "
								+ Constants.BAN_AFG_SERIES_BASE2 + lowerThird.getWhichTeamFlag() + " \0",print_writers);
		    			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$ALL_LT_LOGOGRP$Side" + WhichSide + "$Select$LineUp$LogoIn$Outline$img_Base2*TEXTURE*IMAGE SET "
								+ Constants.BAN_AFG_SERIES_BASE2 + lowerThird.getWhichTeamFlag() + " \0",print_writers);
		    			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$ALL_LT_LOGOGRP$Side" + WhichSide + "$Select$LineUp$LogoIn$Outline$img_Base1*TEXTURE*IMAGE SET "
								+ Constants.BAN_AFG_SERIES_BASE1 + lowerThird.getWhichTeamFlag() + " \0",print_writers);
		    			
		    			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$All$BaseAll$Side" + WhichSide + "$Select$LineUp$LT$Base1$img_Base1*TEXTURE*IMAGE SET "
		    							+ Constants.BAN_AFG_SERIES_BASE1 + lowerThird.getWhichTeamFlag() + " \0",print_writers);
		    			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$All$BaseAll$Side" + WhichSide + "$Select$LineUp$LT$Base2$img_Base2*TEXTURE*IMAGE SET "
		    							+ Constants.BAN_AFG_SERIES_BASE2 + lowerThird.getWhichTeamFlag() + " \0",print_writers);
		    			CricketFunctions.DoadWriteCommandToAllViz(
		    					"-1 RENDERER*FRONT_LAYER*TREE*$LT$ALL_LT_LOGOGRP$Side" + WhichSide + "$Select$LineUp$LTLogoGRP$img_Base2*TEXTURE*IMAGE SET "
		    							+ Constants.BAN_AFG_SERIES_BASE2 + lowerThird.getWhichTeamFlag() + "\0",print_writers);

	    	    		break;
	    	    	default:
	    	    		CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$ALL_LT_LOGOGRP$Side" + WhichSide + "$Select$LineUp$LTLogoGRP$LogoIn$"
	    	    				+ "LLC_LogoGrp$img_Badges*TEXTURE*IMAGE SET " + Constants.ACC_FLAG + lowerThird.getWhichTeamFlag() + "\0",print_writers);
		    			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$ALL_LT_LOGOGRP$Side" + WhichSide + "$Select$LineUp$LTLogoGRP$LogoIn$"
		    					+ "LLC_LogoGrp$img_Badges02*TEXTURE*IMAGE SET "+ Constants.ACC_FLAG + lowerThird.getWhichTeamFlag() + "\0",print_writers);
	    	    		break;
	    			}
	    			
	    			break;
	    		case "Control_F3":
	    			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$ALL_LT_LOGOGRP$Side" + WhichSide + "$Select*FUNCTION*Omo*vis_con SET 1 \0",print_writers);
	    			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$All$BaseAll$Side" + WhichSide + "$Select*FUNCTION*Omo*vis_con SET 1 \0",print_writers);
	    			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$All$DataAll$Side" + WhichSide + "$Select*FUNCTION*Omo*vis_con SET 16 \0",print_writers);
	    			
	    			switch (config.getBroadcaster().toUpperCase()) {
	    	    	case Constants.BAN_AFG_SERIES:
	    	    		CricketFunctions.DoadWriteCommandToAllViz(
								"-1 RENDERER*FRONT_LAYER*TREE*$LT$ALL_LT_LOGOGRP$Side" + WhichSide + "$Select$BothTeam$LTHomeLogoGRP$LogoIn$LLC_LogoGrp$img_Badges*TEXTURE*IMAGE SET "
										+ Constants.BAN_AFG_SERIES_LOGO + lowerThird.getFirstName() + "\0",print_writers);
		    			CricketFunctions.DoadWriteCommandToAllViz(
								"-1 RENDERER*FRONT_LAYER*TREE*$LT$ALL_LT_LOGOGRP$Side" + WhichSide + "$Select$BothTeam$LTHomeLogoGRP$LogoIn$LLC_LogoGrp$img_Badges02*TEXTURE*IMAGE SET "
										+ Constants.BAN_AFG_SERIES_LOGO + lowerThird.getFirstName() + "\0",print_writers);
		    			
		    			CricketFunctions.DoadWriteCommandToAllViz(
								"-1 RENDERER*FRONT_LAYER*TREE*$LT$ALL_LT_LOGOGRP$Side" + WhichSide + "$Select$BothTeam$LTAwayLogoGRP$LogoIn$LLC_LogoGrp$img_Badges*TEXTURE*IMAGE SET "
										+ Constants.BAN_AFG_SERIES_LOGO + lowerThird.getSurName() + "\0",print_writers);
		    			CricketFunctions.DoadWriteCommandToAllViz(
								"-1 RENDERER*FRONT_LAYER*TREE*$LT$ALL_LT_LOGOGRP$Side" + WhichSide + "$Select$BothTeam$LTAwayLogoGRP$LogoIn$LLC_LogoGrp$img_Badges02*TEXTURE*IMAGE SET "
										+ Constants.BAN_AFG_SERIES_LOGO + lowerThird.getSurName() + "\0",print_writers);
		    			CricketFunctions.DoadWriteCommandToAllViz(
								"-1 RENDERER*FRONT_LAYER*TREE*$LT$All$BaseAll$Side" + WhichSide + "$Select$HomeTopBand$img_Base2*TEXTURE*IMAGE SET "
										+ Constants.BAN_AFG_SERIES_BASE2 + lowerThird.getFirstName() + " \0",print_writers);
						CricketFunctions.DoadWriteCommandToAllViz(
								"-1 RENDERER*FRONT_LAYER*TREE*$LT$All$BaseAll$Side" + WhichSide + "$Select$AwayTopBand$img_Base2*TEXTURE*IMAGE SET "
										+ Constants.BAN_AFG_SERIES_BASE2 + lowerThird.getSurName() + " \0",print_writers);
						
						CricketFunctions.DoadWriteCommandToAllViz(
								"-1 RENDERER*FRONT_LAYER*TREE*$LT$All$DataAll$Side" + WhichSide + "$Select$Comparison$TopGrp$Home$HeaderGrp$img_Text2*TEXTURE*IMAGE SET "
										+ Constants.BAN_AFG_SERIES_TEXT2 + lowerThird.getFirstName() + " \0",print_writers);
						CricketFunctions.DoadWriteCommandToAllViz(
								"-1 RENDERER*FRONT_LAYER*TREE*$LT$All$DataAll$Side" + WhichSide + "$Select$Comparison$TopGrp$Away$HeaderGrp$img_Text2*TEXTURE*IMAGE SET "
										+ Constants.BAN_AFG_SERIES_TEXT2 + lowerThird.getSurName() + " \0",print_writers);

						CricketFunctions.DoadWriteCommandToAllViz(
								"-1 RENDERER*FRONT_LAYER*TREE*$LT$All$DataAll$Side" + WhichSide + "$Select$Comparison$BottomGrp$RestDataGrp$HomeScoreGrp$img_Text2*TEXTURE*IMAGE SET "
										+ Constants.BAN_AFG_SERIES_TEXT2 + lowerThird.getFirstName() + " \0",print_writers);
						CricketFunctions.DoadWriteCommandToAllViz(
								"-1 RENDERER*FRONT_LAYER*TREE*$LT$All$DataAll$Side" + WhichSide + "$Select$Comparison$BottomGrp$RestDataGrp$AwayScoreGrp$img_Text2*TEXTURE*IMAGE SET "
										+ Constants.BAN_AFG_SERIES_TEXT2 + lowerThird.getSurName() + " \0",print_writers);
	    	    		break;
	    	    	default:
	    	    		CricketFunctions.DoadWriteCommandToAllViz(
								"-1 RENDERER*FRONT_LAYER*TREE*$LT$ALL_LT_LOGOGRP$Side" + WhichSide + "$Select$BothTeam$LTHomeLogoGRP$LogoIn$LLC_LogoGrp$img_Badges*TEXTURE*IMAGE SET "
										+ Constants.ACC_FLAG + lowerThird.getFirstName() + "\0",print_writers);
		    			CricketFunctions.DoadWriteCommandToAllViz(
								"-1 RENDERER*FRONT_LAYER*TREE*$LT$ALL_LT_LOGOGRP$Side" + WhichSide + "$Select$BothTeam$LTHomeLogoGRP$LogoIn$LLC_LogoGrp$img_Badges02*TEXTURE*IMAGE SET "
										+ Constants.ACC_FLAG + lowerThird.getFirstName() + "\0",print_writers);
		    			
		    			CricketFunctions.DoadWriteCommandToAllViz(
								"-1 RENDERER*FRONT_LAYER*TREE*$LT$ALL_LT_LOGOGRP$Side" + WhichSide + "$Select$BothTeam$LTAwayLogoGRP$LogoIn$LLC_LogoGrp$img_Badges*TEXTURE*IMAGE SET "
										+ Constants.ACC_FLAG + lowerThird.getSurName() + "\0",print_writers);
		    			CricketFunctions.DoadWriteCommandToAllViz(
								"-1 RENDERER*FRONT_LAYER*TREE*$LT$ALL_LT_LOGOGRP$Side" + WhichSide + "$Select$BothTeam$LTAwayLogoGRP$LogoIn$LLC_LogoGrp$img_Badges02*TEXTURE*IMAGE SET "
										+ Constants.ACC_FLAG + lowerThird.getSurName() + "\0",print_writers);
	    	    		break;
	    			}
	    			
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$ALL_LT_LOGOGRP$Side" + WhichSide + "$Select$BothTeam$LTHomeLogoGRP$LogoIn$LLC_LogoGrp$img_Base2*TEXTURE*IMAGE SET " + Constants.BAN_AFG_SERIES_BASE2 + 
							lowerThird.getFirstName() +" \0",print_writers);

					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$ALL_LT_LOGOGRP$Side" + WhichSide + "$Select$BothTeam$LTAwayLogoGRP$LogoIn$LLC_LogoGrp$img_Base2*TEXTURE*IMAGE SET " + Constants.BAN_AFG_SERIES_BASE2 + 
							lowerThird.getSurName() +" \0",print_writers);
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$ALL_LT_LOGOGRP$Side" + WhichSide + "$Select$BothTeam$LTHomeLogoGRP$LogoIn$Outline$img_Base2*TEXTURE*IMAGE SET " + Constants.BAN_AFG_SERIES_BASE2 + 
							lowerThird.getFirstName() +" \0",print_writers);

					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$ALL_LT_LOGOGRP$Side" + WhichSide + "$Select$BothTeam$LTAwayLogoGRP$LogoIn$Outline$img_Base2*TEXTURE*IMAGE SET " + Constants.BAN_AFG_SERIES_BASE2 + 
							lowerThird.getSurName() +" \0",print_writers);
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$ALL_LT_LOGOGRP$Side" + WhichSide + "$Select$BothTeam$LTHomeLogoGRP$LogoIn$Outline$img_Base1*TEXTURE*IMAGE SET " + Constants.BAN_AFG_SERIES_BASE1 + 
							lowerThird.getFirstName() +" \0",print_writers);

					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$ALL_LT_LOGOGRP$Side" + WhichSide + "$Select$BothTeam$LTAwayLogoGRP$LogoIn$Outline$img_Base1*TEXTURE*IMAGE SET " + Constants.BAN_AFG_SERIES_BASE1 + 
							lowerThird.getSurName() +" \0",print_writers);
					
					CricketFunctions.DoadWriteCommandToAllViz(
							"-1 RENDERER*FRONT_LAYER*TREE*$LT$All$DataAll$Side" + WhichSide + "$Select$Comparison$TopGrp$txt_Header1*GEOM*TEXT SET "
									+ lowerThird.getLeftText()[0] + "\0",print_writers);

					CricketFunctions.DoadWriteCommandToAllViz(
							"-1 RENDERER*FRONT_LAYER*TREE*$LT$All$DataAll$Side" + WhichSide + "$Select$Comparison$TopGrp$txt_Header2*GEOM*TEXT SET "
									+ lowerThird.getLeftText()[3] + "\0",print_writers);
					
	    			break;
	    		case "F7": case "F11": case "Control_s": case "Control_f":
	    			
	    			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$ALL_LT_LOGOGRP$Side" + WhichSide + "$Select*FUNCTION*Omo*vis_con SET 0 \0",print_writers);
	    			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$All$BaseAll$Side" + WhichSide + "$Select*FUNCTION*Omo*vis_con SET 0 \0",print_writers);
	    			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$All$DataAll$Side" + WhichSide + "$Select*FUNCTION*Omo*vis_con SET 11 \0",print_writers);
					
					
	    			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$All$DataAll$Side" + WhichSide
    						+ "$Select$PlayerProfile$TopGrp$HeaderGrp$txt_Header1*GEOM*TEXT SET "
    						+ lowerThird.getFirstName() + "\0",print_writers);
    				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$All$DataAll$Side" + WhichSide
    						+ "$Select$PlayerProfile$TopGrp$HeaderGrp$txt_Header2*GEOM*TEXT SET "
    						+ lowerThird.getSurName() + "\0",print_writers);
    				
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$All$DataAll$Side" + WhichSide+ "$Select$PlayerProfile$BottomGrp$RestDataGrp$" 
							+ "txt_Data1*GEOM*TEXT SET " + lowerThird.getSubTitle() + "\0",print_writers);
					
					switch (config.getBroadcaster().toUpperCase()) {
	    	    	case Constants.BAN_AFG_SERIES:
	    	    		CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$ALL_LT_LOGOGRP$Side" + WhichSide
		    					+ "$Select$OneTeam$LTLogoGRP$LogoIn$LLC_LogoGrp$img_Badges*TEXTURE*IMAGE SET " + Constants.BAN_AFG_SERIES_LOGO
		    					+ lowerThird.getWhichTeamFlag() + "\0",print_writers);
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$ALL_LT_LOGOGRP$Side" + WhichSide
		    					+ "$Select$OneTeam$LTLogoGRP$LogoIn$LLC_LogoGrp$img_Badges02*TEXTURE*IMAGE SET " + Constants.BAN_AFG_SERIES_LOGO
		    					+ lowerThird.getWhichTeamFlag() + "\0",print_writers);

		    			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$ALL_LT_LOGOGRP$Side" + WhichSide
		    					+ "$Select$OneTeam$LTLogoGRP$LogoIn$LLC_LogoGrp$img_Base2*TEXTURE*IMAGE SET "
		    					+ Constants.BAN_AFG_SERIES_BASE2 + lowerThird.getWhichTeamFlag() + "\0",print_writers);
		    			
		    			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$ALL_LT_LOGOGRP$Side" + WhichSide
		    					+ "$Select$OneTeam$LTLogoGRP$LogoIn$Outline$img_Base1*TEXTURE*IMAGE SET "
		    					+ Constants.BAN_AFG_SERIES_BASE1 + lowerThird.getWhichTeamFlag() + "\0",print_writers);
		    			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$ALL_LT_LOGOGRP$Side" + WhichSide
		    					+ "$Select$OneTeam$LTLogoGRP$LogoIn$Outline$img_Base2*TEXTURE*IMAGE SET "
		    					+ Constants.BAN_AFG_SERIES_BASE2 + lowerThird.getWhichTeamFlag() + "\0",print_writers);
		    			
		    			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$BaseAll$Side" + WhichSide
								+ "$Select$OneTeam$img_Base2*TEXTURE*IMAGE SET "
								+ Constants.BAN_AFG_SERIES_BASE2 + lowerThird.getWhichTeamFlag() + "\0",print_writers);
						
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$DataAll$Side" + WhichSide
								+ "$Select$PlayerProfile$TopGrp$HeaderGrp$img_Text1*TEXTURE*IMAGE SET "
								+ Constants.BAN_AFG_SERIES_TEXT1 + lowerThird.getWhichTeamFlag() + "\0",print_writers);
						
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$DataAll$Side" + WhichSide
								+ "$Select$PlayerProfile$BottomGrp$RestDataGrp$RestData$img_Text*TEXTURE*IMAGE SET "
								+ Constants.BAN_AFG_SERIES_BASE2 + lowerThird.getWhichTeamFlag() + "\0",print_writers);
	    	    		break;
	    	    	default:
	    	    		CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$ALL_LT_LOGOGRP$Side" + WhichSide
		    					+ "$Select$OneTeam$LTLogoGRP$LogoIn$LLC_LogoGrp$img_Badges*TEXTURE*IMAGE SET " + Constants.ACC_FLAG
		    					+ lowerThird.getWhichTeamFlag() + "\0",print_writers);
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$ALL_LT_LOGOGRP$Side" + WhichSide
		    					+ "$Select$OneTeam$LTLogoGRP$LogoIn$LLC_LogoGrp$img_Badges02*TEXTURE*IMAGE SET " + Constants.ACC_FLAG
		    					+ lowerThird.getWhichTeamFlag() + "\0",print_writers);
	    	    		break;
	    			}
					
	    			break;
	    		case "Control_Shift_L":
	    			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$ALL_LT_LOGOGRP$Side" + WhichSide + "$Select*FUNCTION*Omo*vis_con SET 1 \0",print_writers);
	    			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$All$BaseAll$Side" + WhichSide + "$Select*FUNCTION*Omo*vis_con SET 1 \0",print_writers);
	    			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$All$DataAll$Side" + WhichSide + "$Select*FUNCTION*Omo*vis_con SET 1 \0",print_writers);
	    			
	    			switch (config.getBroadcaster().toUpperCase()) {
	    	    	case Constants.BAN_AFG_SERIES:
	    	    		CricketFunctions.DoadWriteCommandToAllViz(
								"-1 RENDERER*FRONT_LAYER*TREE*$LT$ALL_LT_LOGOGRP$Side1$Select$BothTeam$LTHomeLogoGRP$LogoIn$LLC_LogoGrp$img_Badges*TEXTURE*IMAGE SET "
										+ Constants.BAN_AFG_SERIES_LOGO + lowerThird.getSubTitle() + "\0",print_writers);
		    			CricketFunctions.DoadWriteCommandToAllViz(
								"-1 RENDERER*FRONT_LAYER*TREE*$LT$ALL_LT_LOGOGRP$Side1$Select$BothTeam$LTHomeLogoGRP$LogoIn$LLC_LogoGrp$img_Badges02*TEXTURE*IMAGE SET "
										+ Constants.BAN_AFG_SERIES_LOGO + lowerThird.getSubTitle() + "\0",print_writers);
		    			
		    			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$ALL_LT_LOGOGRP$Select$BothTeam$LTHomeLogoGRP$LogoIn$LLC_LogoGrp$img_Base2*TEXTURE*IMAGE SET " + Constants.BAN_AFG_SERIES_BASE2 + 
		    							lowerThird.getSubTitle() +" \0",print_writers);
		    			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$ALL_LT_LOGOGRP$Select$BothTeam$LTHomeLogoGRP$LogoIn$Outline$img_Base2*TEXTURE*IMAGE SET " + Constants.BAN_AFG_SERIES_BASE2 + 
								lowerThird.getSubTitle() +" \0",print_writers);
		    			
		    			CricketFunctions.DoadWriteCommandToAllViz(
								"-1 RENDERER*FRONT_LAYER*TREE*$LT$ALL_LT_LOGOGRP$Side1$Select$BothTeam$LTAwayLogoGRP$LogoIn$LLC_LogoGrp$img_Badges*TEXTURE*IMAGE SET "
										+ Constants.BAN_AFG_SERIES_LOGO + lowerThird.getScoreText() + "\0",print_writers);
		    			CricketFunctions.DoadWriteCommandToAllViz(
								"-1 RENDERER*FRONT_LAYER*TREE*$LT$ALL_LT_LOGOGRP$Side1$Select$BothTeam$LTAwayLogoGRP$LogoIn$LLC_LogoGrp$img_Badges02*TEXTURE*IMAGE SET "
										+ Constants.BAN_AFG_SERIES_LOGO + lowerThird.getScoreText() + "\0",print_writers);
		    			
		    			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$ALL_LT_LOGOGRP$Select$BothTeam$LTAwayLogoGRP$LogoIn$LLC_LogoGrp$img_Base2*TEXTURE*IMAGE SET " + Constants.BAN_AFG_SERIES_BASE2 + 
		    							lowerThird.getScoreText() +" \0",print_writers);
		    			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$ALL_LT_LOGOGRP$Select$BothTeam$LTAwayLogoGRP$LogoIn$Outline$img_Base2*TEXTURE*IMAGE SET " + Constants.BAN_AFG_SERIES_BASE2 + 
								lowerThird.getScoreText() +" \0",print_writers);
		    			
		    			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$ALL_LT_LOGOGRP$Side" + WhichSide + "$Select$BothTeam$LTHomeLogoGRP$LogoIn$Outline$img_Base1*TEXTURE*IMAGE SET " + Constants.BAN_AFG_SERIES_BASE1 + 
		    					lowerThird.getSubTitle() +" \0",print_writers);
		    			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$BaseAll$Side" + WhichSide + "$Select$BothTeam$HomeTopBand$img_Base2*TEXTURE*IMAGE SET " + Constants.BAN_AFG_SERIES_BASE2 + 
		    					lowerThird.getSubTitle() +" \0",print_writers);

		    			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$ALL_LT_LOGOGRP$Side" + WhichSide + "$Select$BothTeam$LTAwayLogoGRP$LogoIn$Outline$img_Base1*TEXTURE*IMAGE SET " + Constants.BAN_AFG_SERIES_BASE1 + 
		    					lowerThird.getScoreText() +" \0",print_writers);
		    			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$BaseAll$Side" + WhichSide + "$Select$BothTeam$AwayTopBand$img_Base2*TEXTURE*IMAGE SET " + Constants.BAN_AFG_SERIES_BASE2 + 
		    					lowerThird.getScoreText() +" \0",print_writers);
		    			
	    	    		break;
	    	    	default:
	    	    		CricketFunctions.DoadWriteCommandToAllViz(
								"-1 RENDERER*FRONT_LAYER*TREE*$LT$ALL_LT_LOGOGRP$Side1$Select$BothTeam$LTHomeLogoGRP$LogoIn$LLC_LogoGrp$img_Badges*TEXTURE*IMAGE SET "
										+ Constants.ACC_FLAG + lowerThird.getSubTitle() + "\0",print_writers);
		    			CricketFunctions.DoadWriteCommandToAllViz(
								"-1 RENDERER*FRONT_LAYER*TREE*$LT$ALL_LT_LOGOGRP$Side1$Select$BothTeam$LTHomeLogoGRP$LogoIn$LLC_LogoGrp$img_Badges02*TEXTURE*IMAGE SET "
										+ Constants.ACC_FLAG + lowerThird.getSubTitle() + "\0",print_writers);
		    			
		    			CricketFunctions.DoadWriteCommandToAllViz(
								"-1 RENDERER*FRONT_LAYER*TREE*$LT$ALL_LT_LOGOGRP$Side1$Select$BothTeam$LTAwayLogoGRP$LogoIn$LLC_LogoGrp$img_Badges*TEXTURE*IMAGE SET "
										+ Constants.ACC_FLAG + lowerThird.getScoreText() + "\0",print_writers);
		    			CricketFunctions.DoadWriteCommandToAllViz(
								"-1 RENDERER*FRONT_LAYER*TREE*$LT$ALL_LT_LOGOGRP$Side1$Select$BothTeam$LTAwayLogoGRP$LogoIn$LLC_LogoGrp$img_Badges02*TEXTURE*IMAGE SET "
										+ Constants.ACC_FLAG + lowerThird.getScoreText() + "\0",print_writers);
	    	    		break;
	    			}
	    			
	    			CricketFunctions.DoadWriteCommandToAllViz(
							"-1 RENDERER*FRONT_LAYER*TREE*$LT$All$DataAll$Side" + WhichSide + "$Select$MatchId$TopGrp$HeaderGrp$txt_Header1*GEOM*TEXT SET "
									+ lowerThird.getHeaderText().split("-")[0] + "\0",print_writers);
	    			
	    			CricketFunctions.DoadWriteCommandToAllViz(
							"-1 RENDERER*FRONT_LAYER*TREE*$LT$All$DataAll$Side" + WhichSide + "$Select$MatchId$TopGrp$HeaderGrp$txt_Header2*GEOM*TEXT SET "
									+ lowerThird.getFirstName().split("-")[0] + "\0",print_writers);
	    			
	    			break;
	    		case "Control_Shift_M":
	    			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$ALL_LT_LOGOGRP$Side" + WhichSide + "$Select*FUNCTION*Omo*vis_con SET 1 \0",print_writers);
	    			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$All$BaseAll$Side" + WhichSide + "$Select*FUNCTION*Omo*vis_con SET 1 \0",print_writers);
	    			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$All$DataAll$Side" + WhichSide + "$Select*FUNCTION*Omo*vis_con SET 1 \0",print_writers);

	    			switch (config.getBroadcaster().toUpperCase()) {
	    	    	case Constants.BAN_AFG_SERIES:
	    	    		CricketFunctions.DoadWriteCommandToAllViz(
		    					"-1 RENDERER*FRONT_LAYER*TREE*$LT$ALL_LT_LOGOGRP$Side" + WhichSide + "$Select$BothTeam$LTHomeLogoGRP$LogoIn$LLC_LogoGrp$img_Badges*TEXTURE*IMAGE SET "
		    							+ Constants.BAN_AFG_SERIES_LOGO + lowerThird.getHeaderText() + "\0",print_writers);
		    			CricketFunctions.DoadWriteCommandToAllViz(
		    					"-1 RENDERER*FRONT_LAYER*TREE*$LT$ALL_LT_LOGOGRP$Side" + WhichSide + "$Select$BothTeam$LTHomeLogoGRP$LogoIn$LLC_LogoGrp$img_Badges02*TEXTURE*IMAGE SET "
		    							+ Constants.BAN_AFG_SERIES_LOGO + lowerThird.getHeaderText() + "\0",print_writers);
		    			
		    			CricketFunctions.DoadWriteCommandToAllViz(
		    					"-1 RENDERER*FRONT_LAYER*TREE*$LT$ALL_LT_LOGOGRP$Side" + WhichSide + "$Select$BothTeam$LTAwayLogoGRP$LogoIn$LLC_LogoGrp$img_Badges*TEXTURE*IMAGE SET "
		    							+ Constants.BAN_AFG_SERIES_LOGO + lowerThird.getFirstName() + "\0",print_writers);
		    			CricketFunctions.DoadWriteCommandToAllViz(
		    					"-1 RENDERER*FRONT_LAYER*TREE*$LT$ALL_LT_LOGOGRP$Side" + WhichSide + "$Select$BothTeam$LTAwayLogoGRP$LogoIn$LLC_LogoGrp$img_Badges02*TEXTURE*IMAGE SET "
		    							+ Constants.BAN_AFG_SERIES_LOGO + lowerThird.getFirstName() + "\0",print_writers);
		    			
		    			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$ALL_LT_LOGOGRP$Side" + WhichSide + "$Select$BothTeam$LTHomeLogoGRP$LogoIn$LLC_LogoGrp$img_Base2*TEXTURE*IMAGE SET " + Constants.BAN_AFG_SERIES_BASE2 + 
		    					lowerThird.getHeaderText() +" \0",print_writers);
		    			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$ALL_LT_LOGOGRP$Side" + WhichSide + "$Select$BothTeam$LTHomeLogoGRP$LogoIn$Outline$img_Base2*TEXTURE*IMAGE SET " + Constants.BAN_AFG_SERIES_BASE2 + 
		    					lowerThird.getHeaderText() +" \0",print_writers);
		    			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$ALL_LT_LOGOGRP$Side" + WhichSide + "$Select$BothTeam$LTHomeLogoGRP$LogoIn$Outline$img_Base1*TEXTURE*IMAGE SET " + Constants.BAN_AFG_SERIES_BASE1 + 
		    					lowerThird.getHeaderText() +" \0",print_writers);
		    			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$BaseAll$Side" + WhichSide + "$Select$BothTeam$HomeTopBand$img_Base2*TEXTURE*IMAGE SET " + Constants.BAN_AFG_SERIES_BASE2 + 
		    					lowerThird.getHeaderText() +" \0",print_writers);

		    			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$ALL_LT_LOGOGRP$Side" + WhichSide + "$Select$BothTeam$LTAwayLogoGRP$LogoIn$LLC_LogoGrp$img_Base2*TEXTURE*IMAGE SET " + Constants.BAN_AFG_SERIES_BASE2 + 
		    					lowerThird.getFirstName() +" \0",print_writers);
		    			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$ALL_LT_LOGOGRP$Side" + WhichSide + "$Select$BothTeam$LTAwayLogoGRP$LogoIn$Outline$img_Base2*TEXTURE*IMAGE SET " + Constants.BAN_AFG_SERIES_BASE2 + 
		    					lowerThird.getFirstName() +" \0",print_writers);
		    			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$ALL_LT_LOGOGRP$Side" + WhichSide + "$Select$BothTeam$LTAwayLogoGRP$LogoIn$Outline$img_Base1*TEXTURE*IMAGE SET " + Constants.BAN_AFG_SERIES_BASE1 + 
		    					lowerThird.getFirstName() +" \0",print_writers);
		    			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$BaseAll$Side" + WhichSide + "$Select$BothTeam$AwayTopBand$img_Base2*TEXTURE*IMAGE SET " + Constants.BAN_AFG_SERIES_BASE2 + 
		    					lowerThird.getFirstName() +" \0",print_writers);
	    	    		break;
	    	    	default:
	    	    		CricketFunctions.DoadWriteCommandToAllViz(
		    					"-1 RENDERER*FRONT_LAYER*TREE*$LT$ALL_LT_LOGOGRP$Side" + WhichSide + "$Select$BothTeam$LTHomeLogoGRP$LogoIn$LLC_LogoGrp$img_Badges*TEXTURE*IMAGE SET "
		    							+ Constants.ACC_FLAG + lowerThird.getHeaderText() + "\0",print_writers);
		    			CricketFunctions.DoadWriteCommandToAllViz(
		    					"-1 RENDERER*FRONT_LAYER*TREE*$LT$ALL_LT_LOGOGRP$Side" + WhichSide + "$Select$BothTeam$LTHomeLogoGRP$LogoIn$LLC_LogoGrp$img_Badges02*TEXTURE*IMAGE SET "
		    							+ Constants.ACC_FLAG + lowerThird.getHeaderText() + "\0",print_writers);
		    			
		    			CricketFunctions.DoadWriteCommandToAllViz(
		    					"-1 RENDERER*FRONT_LAYER*TREE*$LT$ALL_LT_LOGOGRP$Side" + WhichSide + "$Select$BothTeam$LTAwayLogoGRP$LogoIn$LLC_LogoGrp$img_Badges*TEXTURE*IMAGE SET "
		    							+ Constants.ACC_FLAG + lowerThird.getFirstName() + "\0",print_writers);
		    			CricketFunctions.DoadWriteCommandToAllViz(
		    					"-1 RENDERER*FRONT_LAYER*TREE*$LT$ALL_LT_LOGOGRP$Side" + WhichSide + "$Select$BothTeam$LTAwayLogoGRP$LogoIn$LLC_LogoGrp$img_Badges02*TEXTURE*IMAGE SET "
		    							+ Constants.ACC_FLAG + lowerThird.getFirstName() + "\0",print_writers);
	    	    		break;
	    			}
	    			
	    			

	    			CricketFunctions.DoadWriteCommandToAllViz(
	    					"-1 RENDERER*FRONT_LAYER*TREE*$LT$All$DataAll$Side" + WhichSide + "$Select$MatchId$TopGrp$$HeaderGrp$txt_Header1*GEOM*TEXT SET "
	    							+ lowerThird.getSubTitle() + "\0",print_writers);
	    			CricketFunctions.DoadWriteCommandToAllViz(
	    					"-1 RENDERER*FRONT_LAYER*TREE*$LT$All$DataAll$Side" + WhichSide + "$Select$MatchId$TopGrp$HeaderGrp$txt_Header2*GEOM*TEXT SET "
	    							+ lowerThird.getBallsFacedText() + "\0",print_writers);
	    			
	    			break;
	    		case "d":
	    			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$ALL_LT_LOGOGRP$Side" + WhichSide + "$Select*FUNCTION*Omo*vis_con SET 1 \0",print_writers);
	    			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$All$BaseAll$Side" + WhichSide + "$Select*FUNCTION*Omo*vis_con SET 1 \0",print_writers);
	    			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$All$DataAll$Side" + WhichSide + "$Select*FUNCTION*Omo*vis_con SET 1 \0",print_writers);

	    			switch (config.getBroadcaster().toUpperCase()) {
	    	    	case Constants.BAN_AFG_SERIES:
	    	    		CricketFunctions.DoadWriteCommandToAllViz(
		    					"-1 RENDERER*FRONT_LAYER*TREE*$LT$ALL_LT_LOGOGRP$Side" + WhichSide + "$Select$BothTeam$LTHomeLogoGRP$LogoIn$LLC_LogoGrp$img_Badges*TEXTURE*IMAGE SET "
		    							+ Constants.BAN_AFG_SERIES_LOGO + lowerThird.getBallsFacedText() + "\0",print_writers);
		    			CricketFunctions.DoadWriteCommandToAllViz(
		    					"-1 RENDERER*FRONT_LAYER*TREE*$LT$ALL_LT_LOGOGRP$Side" + WhichSide + "$Select$BothTeam$LTHomeLogoGRP$LogoIn$LLC_LogoGrp$img_Badges02*TEXTURE*IMAGE SET "
		    							+ Constants.BAN_AFG_SERIES_LOGO + lowerThird.getBallsFacedText() + "\0",print_writers);
		    			
		    			CricketFunctions.DoadWriteCommandToAllViz(
		    					"-1 RENDERER*FRONT_LAYER*TREE*$LT$ALL_LT_LOGOGRP$Side" + WhichSide + "$Select$BothTeam$LTAwayLogoGRP$LogoIn$LLC_LogoGrp$img_Badges*TEXTURE*IMAGE SET "
		    							+ Constants.BAN_AFG_SERIES_LOGO + lowerThird.getWhichSponsor() + "\0",print_writers);
		    			CricketFunctions.DoadWriteCommandToAllViz(
		    					"-1 RENDERER*FRONT_LAYER*TREE*$LT$ALL_LT_LOGOGRP$Side" + WhichSide + "$Select$BothTeam$LTAwayLogoGRP$LogoIn$LLC_LogoGrp$img_Badges02*TEXTURE*IMAGE SET "
		    							+ Constants.BAN_AFG_SERIES_LOGO + lowerThird.getWhichSponsor() + "\0",print_writers);
		    			
		    			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$ALL_LT_LOGOGRP$Side" + WhichSide + "$Select$BothTeam$LTHomeLogoGRP$LogoIn$LLC_LogoGrp$img_Base2*TEXTURE*IMAGE SET " + Constants.BAN_AFG_SERIES_BASE2 + 
		    					lowerThird.getBallsFacedText() +" \0",print_writers);
		    			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$ALL_LT_LOGOGRP$Side" + WhichSide + "$Select$BothTeam$LTHomeLogoGRP$LogoIn$Outline$img_Base2*TEXTURE*IMAGE SET " + Constants.BAN_AFG_SERIES_BASE2 + 
		    					lowerThird.getBallsFacedText() +" \0",print_writers);
		    			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$ALL_LT_LOGOGRP$Side" + WhichSide + "$Select$BothTeam$LTHomeLogoGRP$LogoIn$Outline$img_Base1*TEXTURE*IMAGE SET " + Constants.BAN_AFG_SERIES_BASE1 + 
		    					lowerThird.getBallsFacedText() +" \0",print_writers);
		    			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$BaseAll$Side" + WhichSide + "$Select$BothTeam$HomeTopBand$img_Base2*TEXTURE*IMAGE SET " + Constants.BAN_AFG_SERIES_BASE2 + 
		    					lowerThird.getBallsFacedText() +" \0",print_writers);

		    			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$ALL_LT_LOGOGRP$Side" + WhichSide + "$Select$BothTeam$LTAwayLogoGRP$LogoIn$LLC_LogoGrp$img_Base2*TEXTURE*IMAGE SET " + Constants.BAN_AFG_SERIES_BASE2 + 
		    					lowerThird.getWhichSponsor() +" \0",print_writers);
		    			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$ALL_LT_LOGOGRP$Side" + WhichSide + "$Select$BothTeam$LTAwayLogoGRP$LogoIn$Outline$img_Base2*TEXTURE*IMAGE SET " + Constants.BAN_AFG_SERIES_BASE2 + 
		    					lowerThird.getWhichSponsor() +" \0",print_writers);
		    			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$ALL_LT_LOGOGRP$Side" + WhichSide + "$Select$BothTeam$LTAwayLogoGRP$LogoIn$Outline$img_Base1*TEXTURE*IMAGE SET " + Constants.BAN_AFG_SERIES_BASE1 + 
		    					lowerThird.getWhichSponsor() +" \0",print_writers);
		    			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$BaseAll$Side" + WhichSide + "$Select$BothTeam$AwayTopBand$img_Base2*TEXTURE*IMAGE SET " + Constants.BAN_AFG_SERIES_BASE2 + 
		    					lowerThird.getWhichSponsor() +" \0",print_writers);
	    	    		break;
	    	    	default:
	    	    		CricketFunctions.DoadWriteCommandToAllViz(
		    					"-1 RENDERER*FRONT_LAYER*TREE*$LT$ALL_LT_LOGOGRP$Side" + WhichSide + "$Select$BothTeam$LTHomeLogoGRP$LogoIn$LLC_LogoGrp$img_Badges*TEXTURE*IMAGE SET "
		    							+ Constants.ACC_FLAG + lowerThird.getBallsFacedText() + "\0",print_writers);
		    			CricketFunctions.DoadWriteCommandToAllViz(
		    					"-1 RENDERER*FRONT_LAYER*TREE*$LT$ALL_LT_LOGOGRP$Side" + WhichSide + "$Select$BothTeam$LTHomeLogoGRP$LogoIn$LLC_LogoGrp$img_Badges02*TEXTURE*IMAGE SET "
		    							+ Constants.ACC_FLAG + lowerThird.getBallsFacedText() + "\0",print_writers);
		    			
		    			CricketFunctions.DoadWriteCommandToAllViz(
		    					"-1 RENDERER*FRONT_LAYER*TREE*$LT$ALL_LT_LOGOGRP$Side" + WhichSide + "$Select$BothTeam$LTAwayLogoGRP$LogoIn$LLC_LogoGrp$img_Badges*TEXTURE*IMAGE SET "
		    							+ Constants.ACC_FLAG + lowerThird.getWhichSponsor() + "\0",print_writers);
		    			CricketFunctions.DoadWriteCommandToAllViz(
		    					"-1 RENDERER*FRONT_LAYER*TREE*$LT$ALL_LT_LOGOGRP$Side" + WhichSide + "$Select$BothTeam$LTAwayLogoGRP$LogoIn$LLC_LogoGrp$img_Badges02*TEXTURE*IMAGE SET "
		    							+ Constants.ACC_FLAG + lowerThird.getWhichSponsor() + "\0",print_writers);
	    	    		break;
	    			}
	    			
	    			CricketFunctions.DoadWriteCommandToAllViz(
	    					"-1 RENDERER*FRONT_LAYER*TREE*$LT$All$DataAll$Side" + WhichSide + "$Select$MatchId$TopGrp$$HeaderGrp$txt_Header1*GEOM*TEXT SET "
	    							+ lowerThird.getSubTitle() + "\0",print_writers);
	    			CricketFunctions.DoadWriteCommandToAllViz(
	    					"-1 RENDERER*FRONT_LAYER*TREE*$LT$All$DataAll$Side" + WhichSide + "$Select$MatchId$TopGrp$HeaderGrp$txt_Header2*GEOM*TEXT SET "
	    							+ lowerThird.getScoreText() + "\0",print_writers);
	    			break;
	    		case "Alt_Shift_F4":
	    			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$ALL_LT_LOGOGRP$Side1$Select*FUNCTION*Omo*vis_con SET 0 \0",print_writers);
	    			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$All$BaseAll$Side1$Select*FUNCTION*Omo*vis_con SET 0 \0",print_writers);
	    			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$All$DataAll$Side1$Select*FUNCTION*Omo*vis_con SET 19 \0",print_writers);

	    			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$ALL_LT_LOGOGRP$Side" + WhichSide
							+ "$Select$OneTeam$LTLogoGRP$LogoIn$LLC_LogoGrp$img_Badges*TEXTURE*IMAGE SET " + Constants.ACC_FLAG
							+ lowerThird.getWhichTeamFlag() + "\0",print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$ALL_LT_LOGOGRP$Side" + WhichSide
							+ "$Select$OneTeam$LTLogoGRP$LogoIn$LLC_LogoGrp$img_Badges02*TEXTURE*IMAGE SET " + Constants.ACC_FLAG
							+ lowerThird.getWhichTeamFlag() + "\0",print_writers);
	    			
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$All$DataAll$Side" + WhichSide
							+ "$Select$BatterPhase$TopGrp$HeaderGrp$txt_Header1*GEOM*TEXT SET "
							+ lowerThird.getFirstName() + "\0",print_writers);
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$All$DataAll$Side" + WhichSide
							+ "$Select$BatterPhase$BottomGrp$RestDataGrp$txt_Data1*GEOM*TEXT SET "
							+ lowerThird.getSubTitle() + "\0",print_writers);
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$All$DataAll$Side" + WhichSide
							+ "$Select$BatterPhase$BottomGrp$ScoreGrp$txt_Data1*GEOM*TEXT SET "
							+ lowerThird.getScoreText() + "\0",print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$All$DataAll$Side" + WhichSide
							+ "$Select$BatterPhase$BottomGrp$ScoreGrp$txt_Data2*GEOM*TEXT SET "
							+ lowerThird.getBallsFacedText() + "\0",print_writers);
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$All$DataAll$Side" + WhichSide
							+ "$Select$BatterPhase$BottomGrp$RestDataGrp$Data$1$txt_Data1A*GEOM*TEXT SET "
							+ lowerThird.getLeftText()[0] + "\0",print_writers);
	    			break;
	    		case "u":
	    			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$ALL_LT_LOGOGRP$Side1$Select*FUNCTION*Omo*vis_con SET 0 \0",print_writers);
	    			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$All$BaseAll$Side1$Select*FUNCTION*Omo*vis_con SET 0 \0",print_writers);
	    			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$All$DataAll$Side1$Select*FUNCTION*Omo*vis_con SET 6 \0",print_writers);

	    			switch (config.getBroadcaster().toUpperCase()) {
	    	    	case Constants.BAN_AFG_SERIES:
	    	    		CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$ALL_LT_LOGOGRP$Side" + WhichSide
								+ "$Select$OneTeam$LTLogoGRP$LogoIn$LLC_LogoGrp$img_Badges*TEXTURE*IMAGE SET " + Constants.BAN_AFG_SERIES_LOGO
								+ lowerThird.getWhichTeamFlag() + "\0",print_writers);
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$ALL_LT_LOGOGRP$Side" + WhichSide
								+ "$Select$OneTeam$LTLogoGRP$LogoIn$LLC_LogoGrp$img_Badges02*TEXTURE*IMAGE SET " + Constants.BAN_AFG_SERIES_LOGO
								+ lowerThird.getWhichTeamFlag() + "\0",print_writers);

						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$ALL_LT_LOGOGRP$Side" + WhichSide
								+ "$Select$OneTeam$LTLogoGRP$LogoIn$LLC_LogoGrp$img_Base2*TEXTURE*IMAGE SET "
								+ Constants.BAN_AFG_SERIES_BASE2 + lowerThird.getWhichTeamFlag() + "\0",print_writers);
						
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$ALL_LT_LOGOGRP$Side" + WhichSide
		    					+ "$Select$OneTeam$LTLogoGRP$LogoIn$Outline$img_Base1*TEXTURE*IMAGE SET "
		    					+ Constants.BAN_AFG_SERIES_BASE1 + lowerThird.getWhichTeamFlag() + "\0",print_writers);
		    			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$ALL_LT_LOGOGRP$Side" + WhichSide
		    					+ "$Select$OneTeam$LTLogoGRP$LogoIn$Outline$img_Base2*TEXTURE*IMAGE SET "
		    					+ Constants.BAN_AFG_SERIES_BASE2 + lowerThird.getWhichTeamFlag() + "\0",print_writers);
						
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$BaseAll$Side" + WhichSide
								+ "$Select$OneTeam$img_Base2*TEXTURE*IMAGE SET "
								+ Constants.BAN_AFG_SERIES_BASE2 + lowerThird.getWhichTeamFlag() + "\0",print_writers);
						
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$DataAll$Side" + WhichSide
								+ "$Select$30_50Splits$TopGrp$HeaderGrp$img_Text1*TEXTURE*IMAGE SET "
								+ Constants.BAN_AFG_SERIES_TEXT1 + lowerThird.getWhichTeamFlag() + "\0",print_writers);
						
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$DataAll$Side" + WhichSide
								+ "$Select$30_50Splits$BottomGrp$RestDataGrp$RestData$img_Text*TEXTURE*IMAGE SET "
								+ Constants.BAN_AFG_SERIES_BASE2 + lowerThird.getWhichTeamFlag() + "\0",print_writers);
						
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$DataAll$Side" + WhichSide
								+ "$Select$30_50Splits$BottomGrp$RestDataGrp$RestData$Data$1$img_Text*TEXTURE*IMAGE SET "
								+ Constants.BAN_AFG_SERIES_BASE2 + lowerThird.getWhichTeamFlag() + "\0",print_writers);
	    	    		break;
	    	    	default:
	    	    		CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$ALL_LT_LOGOGRP$Side" + WhichSide
								+ "$Select$OneTeam$LTLogoGRP$LogoIn$LLC_LogoGrp$img_Badges*TEXTURE*IMAGE SET " + Constants.ACC_FLAG
								+ lowerThird.getWhichTeamFlag() + "\0",print_writers);
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$ALL_LT_LOGOGRP$Side" + WhichSide
								+ "$Select$OneTeam$LTLogoGRP$LogoIn$LLC_LogoGrp$img_Badges02*TEXTURE*IMAGE SET " + Constants.ACC_FLAG
								+ lowerThird.getWhichTeamFlag() + "\0",print_writers);
	    	    		break;
	    			}
	    			
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$All$DataAll$Side" + WhichSide
							+ "$Select$30_50Splits$TopGrp$HeaderGrp$txt_Header1*GEOM*TEXT SET "
							+ lowerThird.getFirstName() + "\0",print_writers);
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$All$DataAll$Side" + WhichSide
							+ "$Select$30_50Splits$BottomGrp$RestDataGrp$txt_Data1*GEOM*TEXT SET "
							+ lowerThird.getSubTitle() + "\0",print_writers);
					
					if (Integer.valueOf(lowerThird.getScoreText().split("-")[1])>=10) {
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$All$DataAll$Side" + WhichSide
								+ "$Select$30_50Splits$BottomGrp$ScoreGrp$txt_Data1*GEOM*TEXT SET "
								+ lowerThird.getScoreText().split("-")[0] + "\0",print_writers);
					} else {
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$All$DataAll$Side" + WhichSide
								+ "$Select$30_50Splits$BottomGrp$ScoreGrp$txt_Data1*GEOM*TEXT SET "
								+ lowerThird.getScoreText() + "\0",print_writers);
					}
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$All$DataAll$Side" + WhichSide
							+ "$Select$30_50Splits$BottomGrp$ScoreGrp$txt_Data2*GEOM*TEXT SET "
							+ lowerThird.getBallsFacedText() + "\0",print_writers);
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$All$DataAll$Side" + WhichSide
							+ "$Select$30_50Splits$BottomGrp$RestDataGrp$Data$1$txt_Data1A*GEOM*TEXT SET "
							+ lowerThird.getLeftText()[0] + "\0",print_writers);
	    			break;
	    		case "Control_a":
	    			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$ALL_LT_LOGOGRP$Side" + WhichSide + "$Select*FUNCTION*Omo*vis_con SET 0 \0",print_writers);
    				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$All$BaseAll$Side" + WhichSide + "$Select*FUNCTION*Omo*vis_con SET 0 \0",print_writers);
    				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$All$DataAll$Side" + WhichSide + "$Select*FUNCTION*Omo*vis_con SET 10 \0",print_writers);
    				
    				
	    			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$All$DataAll$Side" + WhichSide
	    					+ "$Select$ProjectedScore$BottomGrp$RestDataGrp$txt_Data1*GEOM*TEXT SET "
	    					+ "PROJECTED SCORES" + " \0",print_writers);

	    			switch (config.getBroadcaster().toUpperCase()) {
	    	    	case Constants.BAN_AFG_SERIES:
	    	    		CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$ALL_LT_LOGOGRP$Side" + WhichSide
								+ "$Select$OneTeam$LTLogoGRP$LogoIn$LLC_LogoGrp$img_Badges*TEXTURE*IMAGE SET " + Constants.BAN_AFG_SERIES_LOGO
								+ lowerThird.getWhichTeamFlag() + "\0",print_writers);
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$ALL_LT_LOGOGRP$Side" + WhichSide
								+ "$Select$OneTeam$LTLogoGRP$LogoIn$LLC_LogoGrp$img_Badges02*TEXTURE*IMAGE SET " + Constants.BAN_AFG_SERIES_LOGO
								+ lowerThird.getWhichTeamFlag() + "\0",print_writers);

						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$ALL_LT_LOGOGRP$Side" + WhichSide
								+ "$Select$OneTeam$LTLogoGRP$LogoIn$LLC_LogoGrp$img_Base2*TEXTURE*IMAGE SET "
								+ Constants.BAN_AFG_SERIES_BASE2 + lowerThird.getWhichTeamFlag() + "\0",print_writers);
						
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$ALL_LT_LOGOGRP$Side" + WhichSide
		    					+ "$Select$OneTeam$LTLogoGRP$LogoIn$Outline$img_Base1*TEXTURE*IMAGE SET "
		    					+ Constants.BAN_AFG_SERIES_BASE1 + lowerThird.getWhichTeamFlag() + "\0",print_writers);
		    			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$ALL_LT_LOGOGRP$Side" + WhichSide
		    					+ "$Select$OneTeam$LTLogoGRP$LogoIn$Outline$img_Base2*TEXTURE*IMAGE SET "
		    					+ Constants.BAN_AFG_SERIES_BASE2 + lowerThird.getWhichTeamFlag() + "\0",print_writers);
						
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$BaseAll$Side" + WhichSide
								+ "$Select$OneTeam$img_Base2*TEXTURE*IMAGE SET "
								+ Constants.BAN_AFG_SERIES_BASE2 + lowerThird.getWhichTeamFlag() + "\0",print_writers);
						
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$DataAll$Side" + WhichSide
								+ "$Select$ProjectedScore$TopGrp$HeaderGrp$img_Text1*TEXTURE*IMAGE SET "
								+ Constants.BAN_AFG_SERIES_TEXT1 + lowerThird.getWhichTeamFlag() + "\0",print_writers);
						
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$DataAll$Side" + WhichSide
								+ "$Select$ProjectedScore$BottomGrp$RestDataGrp$RestData$img_Text*TEXTURE*IMAGE SET "
								+ Constants.BAN_AFG_SERIES_BASE2 + lowerThird.getWhichTeamFlag() + "\0",print_writers);
	    	    		break;
	    	    	default:
	    	    		CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$ALL_LT_LOGOGRP$Side" + WhichSide
								+ "$Select$OneTeam$LTLogoGRP$LogoIn$LLC_LogoGrp$img_Badges*TEXTURE*IMAGE SET " + Constants.ACC_FLAG
								+ lowerThird.getWhichTeamFlag() + "\0",print_writers);
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$ALL_LT_LOGOGRP$Side" + WhichSide
								+ "$Select$OneTeam$LTLogoGRP$LogoIn$LLC_LogoGrp$img_Badges02*TEXTURE*IMAGE SET " + Constants.ACC_FLAG
								+ lowerThird.getWhichTeamFlag() + "\0",print_writers);
	    	    		break;
	    			}
	    			
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$All$DataAll$Side" + WhichSide
							+ "$Select$ProjectedScore$TopGrp$HeaderGrp$txt_Header1*GEOM*TEXT SET "
							+ lowerThird.getFirstName() + "\0",print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$All$DataAll$Side" + WhichSide
							+ "$Select$ProjectedScore$TopGrp$HeaderGrp$txt_Header2*GEOM*TEXT SET "
							+ "" + "\0",print_writers);
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$All$DataAll$Side" + WhichSide
	    					+ "$Select$ProjectedScore$BottomGrp$ScoreGrp$txt_Data1*GEOM*TEXT SET "
	    					+ lowerThird.getScoreText() + " \0",print_writers);
	    			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$All$DataAll$Side" + WhichSide
	    					+ "$Select$ProjectedScore$BottomGrp$ScoreGrp$txt_Data2*GEOM*TEXT SET "
	    					+ lowerThird.getBallsFacedText() + " \0",print_writers);
					
	    			break;
	    		case "Control_h":
	    			
	    			if(lowerThird.getWhichSponsor().equalsIgnoreCase(CricketUtil.ODI)) {
	    				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$ALL_LT_LOGOGRP$Side" + WhichSide + "$Select*FUNCTION*Omo*vis_con SET 0 \0",print_writers);
		    			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$All$BaseAll$Side" + WhichSide + "$Select*FUNCTION*Omo*vis_con SET 0 \0",print_writers);
		    			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$All$DataAll$Side" + WhichSide + "$Select*FUNCTION*Omo*vis_con SET 11 \0",print_writers);
						
						
		    			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$All$DataAll$Side" + WhichSide
	    						+ "$Select$PlayerProfile$TopGrp$HeaderGrp$txt_Header1*GEOM*TEXT SET "
	    						+ lowerThird.getFirstName() + "\0",print_writers);
	    				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$All$DataAll$Side" + WhichSide
	    						+ "$Select$PlayerProfile$TopGrp$HeaderGrp$txt_Header2*GEOM*TEXT SET "
	    						+ "" + "\0",print_writers);
	    				
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$All$DataAll$Side" + WhichSide+ "$Select$PlayerProfile$BottomGrp$RestDataGrp$" + "txt_Data1*GEOM*TEXT SET "
	    						+ "PHASE-WISE SCORES" + "\0",print_writers);
						
						switch (config.getBroadcaster().toUpperCase()) {
		    	    	case Constants.BAN_AFG_SERIES:
		    	    		CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$ALL_LT_LOGOGRP$Side" + WhichSide
			    					+ "$Select$OneTeam$LTLogoGRP$LogoIn$LLC_LogoGrp$img_Badges*TEXTURE*IMAGE SET " + Constants.BAN_AFG_SERIES_LOGO
			    					+ lowerThird.getWhichTeamFlag() + "\0",print_writers);
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$ALL_LT_LOGOGRP$Side" + WhichSide
			    					+ "$Select$OneTeam$LTLogoGRP$LogoIn$LLC_LogoGrp$img_Badges02*TEXTURE*IMAGE SET " + Constants.BAN_AFG_SERIES_LOGO
			    					+ lowerThird.getWhichTeamFlag() + "\0",print_writers);

			    			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$ALL_LT_LOGOGRP$Side" + WhichSide
			    					+ "$Select$OneTeam$LTLogoGRP$LogoIn$LLC_LogoGrp$img_Base2*TEXTURE*IMAGE SET "
			    					+ Constants.BAN_AFG_SERIES_BASE2 + lowerThird.getWhichTeamFlag() + "\0",print_writers);
			    			
			    			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$ALL_LT_LOGOGRP$Side" + WhichSide
			    					+ "$Select$OneTeam$LTLogoGRP$LogoIn$Outline$img_Base1*TEXTURE*IMAGE SET "
			    					+ Constants.BAN_AFG_SERIES_BASE1 + lowerThird.getWhichTeamFlag() + "\0",print_writers);
			    			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$ALL_LT_LOGOGRP$Side" + WhichSide
			    					+ "$Select$OneTeam$LTLogoGRP$LogoIn$Outline$img_Base2*TEXTURE*IMAGE SET "
			    					+ Constants.BAN_AFG_SERIES_BASE2 + lowerThird.getWhichTeamFlag() + "\0",print_writers);
			    			
			    			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$BaseAll$Side" + WhichSide
									+ "$Select$OneTeam$img_Base2*TEXTURE*IMAGE SET "
									+ Constants.BAN_AFG_SERIES_BASE2 + lowerThird.getWhichTeamFlag() + "\0",print_writers);
							
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$DataAll$Side" + WhichSide
									+ "$Select$PlayerProfile$TopGrp$HeaderGrp$img_Text1*TEXTURE*IMAGE SET "
									+ Constants.BAN_AFG_SERIES_TEXT1 + lowerThird.getWhichTeamFlag() + "\0",print_writers);
							
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$DataAll$Side" + WhichSide
									+ "$Select$PlayerProfile$BottomGrp$RestDataGrp$RestData$img_Text*TEXTURE*IMAGE SET "
									+ Constants.BAN_AFG_SERIES_BASE2 + lowerThird.getWhichTeamFlag() + "\0",print_writers);
		    	    		break;
		    	    	default:
		    	    		CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$ALL_LT_LOGOGRP$Side" + WhichSide
			    					+ "$Select$OneTeam$LTLogoGRP$LogoIn$LLC_LogoGrp$img_Badges*TEXTURE*IMAGE SET " + Constants.ACC_FLAG
			    					+ lowerThird.getWhichTeamFlag() + "\0",print_writers);
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$ALL_LT_LOGOGRP$Side" + WhichSide
			    					+ "$Select$OneTeam$LTLogoGRP$LogoIn$LLC_LogoGrp$img_Badges02*TEXTURE*IMAGE SET " + Constants.ACC_FLAG
			    					+ lowerThird.getWhichTeamFlag() + "\0",print_writers);
		    	    		break;
		    			}
	    			}else {
	    				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$ALL_LT_LOGOGRP$Side" + WhichSide + "$Select*FUNCTION*Omo*vis_con SET 0 \0",print_writers);
		    			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$All$BaseAll$Side" + WhichSide + "$Select*FUNCTION*Omo*vis_con SET 0 \0",print_writers);
		    			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$All$DataAll$Side" + WhichSide + "$Select*FUNCTION*Omo*vis_con SET 10 \0",print_writers);
		    			
		    			switch (config.getBroadcaster().toUpperCase()) {
		    	    	case Constants.BAN_AFG_SERIES:
		    	    		CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$ALL_LT_LOGOGRP$Side" + WhichSide
									+ "$Select$OneTeam$LTLogoGRP$LogoIn$LLC_LogoGrp$img_Badges*TEXTURE*IMAGE SET " + Constants.BAN_AFG_SERIES_LOGO
									+ lowerThird.getWhichTeamFlag() + "\0",print_writers);
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$ALL_LT_LOGOGRP$Side" + WhichSide
									+ "$Select$OneTeam$LTLogoGRP$LogoIn$LLC_LogoGrp$img_Badges02*TEXTURE*IMAGE SET " + Constants.BAN_AFG_SERIES_LOGO
									+ lowerThird.getWhichTeamFlag() + "\0",print_writers);
							
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$ALL_LT_LOGOGRP$Side" + WhichSide
									+ "$Select$OneTeam$LTLogoGRP$LogoIn$LLC_LogoGrp$img_Base2*TEXTURE*IMAGE SET "
									+ Constants.BAN_AFG_SERIES_BASE2 + lowerThird.getWhichTeamFlag() + "\0",print_writers);
							
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$ALL_LT_LOGOGRP$Side" + WhichSide
			    					+ "$Select$OneTeam$LTLogoGRP$LogoIn$Outline$img_Base1*TEXTURE*IMAGE SET "
			    					+ Constants.BAN_AFG_SERIES_BASE1 + lowerThird.getWhichTeamFlag() + "\0",print_writers);
			    			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$ALL_LT_LOGOGRP$Side" + WhichSide
			    					+ "$Select$OneTeam$LTLogoGRP$LogoIn$Outline$img_Base2*TEXTURE*IMAGE SET "
			    					+ Constants.BAN_AFG_SERIES_BASE2 + lowerThird.getWhichTeamFlag() + "\0",print_writers);
							
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$BaseAll$Side" + WhichSide
									+ "$Select$OneTeam$img_Base2*TEXTURE*IMAGE SET "
									+ Constants.BAN_AFG_SERIES_BASE2 + lowerThird.getWhichTeamFlag() + "\0",print_writers);
							
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$DataAll$Side" + WhichSide
									+ "$Select$ProjectedScore$TopGrp$HeaderGrp$img_Text1*TEXTURE*IMAGE SET "
									+ Constants.BAN_AFG_SERIES_TEXT1 + lowerThird.getWhichTeamFlag() + "\0",print_writers);
							
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$DataAll$Side" + WhichSide
									+ "$Select$ProjectedScore$BottomGrp$RestDataGrp$RestData$img_Text*TEXTURE*IMAGE SET "
									+ Constants.BAN_AFG_SERIES_BASE2 + lowerThird.getWhichTeamFlag() + "\0",print_writers);
		    	    		break;
		    	    	default:
		    	    		CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$ALL_LT_LOGOGRP$Side" + WhichSide
									+ "$Select$OneTeam$LTLogoGRP$LogoIn$LLC_LogoGrp$img_Badges*TEXTURE*IMAGE SET " + Constants.ACC_FLAG
									+ lowerThird.getWhichTeamFlag() + "\0",print_writers);
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$ALL_LT_LOGOGRP$Side" + WhichSide
									+ "$Select$OneTeam$LTLogoGRP$LogoIn$LLC_LogoGrp$img_Badges02*TEXTURE*IMAGE SET " + Constants.ACC_FLAG
									+ lowerThird.getWhichTeamFlag() + "\0",print_writers);
		    	    		break;
		    			}
		    			
		    			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$All$DataAll$Side" + WhichSide
		    					+ "$Select$ProjectedScore$BottomGrp$RestDataGrp$txt_Data1*GEOM*TEXT SET "
		    					+ "PHASE-WISE SCORES" + " \0",print_writers);
		    			
		    			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$All$DataAll$Side" + WhichSide
								+ "$Select$ProjectedScore$TopGrp$HeaderGrp$txt_Header1*GEOM*TEXT SET "
								+ lowerThird.getFirstName() + "\0",print_writers);
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$All$DataAll$Side" + WhichSide
								+ "$Select$ProjectedScore$TopGrp$HeaderGrp$txt_Header2*GEOM*TEXT SET "
								+ "" + "\0",print_writers);
	    			}
	    			
	    			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$All$DataAll$Side" + WhichSide
	    					+ "$Select$ProjectedScore$BottomGrp$ScoreGrp$txt_Data1*GEOM*TEXT SET "
	    					+ lowerThird.getScoreText() + " \0",print_writers);
	    			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$All$DataAll$Side" + WhichSide
	    					+ "$Select$ProjectedScore$BottomGrp$ScoreGrp$txt_Data2*GEOM*TEXT SET "
	    					+ lowerThird.getBallsFacedText() + " \0",print_writers);
	    			
//	    			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$All$DataAll$Side" + WhichSide + "$Select$PhaseScore$BottomGrp$ScoreGrp$txt_Data1*GEOM*TEXT SET "
//    						+ lowerThird.getScoreText() + "\0",print_writers);
//	    			
//	    			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$All$DataAll$Side" + WhichSide + "$Select$PhaseScore$BottomGrp$ScoreGrp$txt_Data2*GEOM*TEXT SET "
//	    					+ lowerThird.getBallsFacedText() + " OVERS" + "\0",print_writers);
	    			
	    			break;	
	    		case "F6": case "Control_F6": case "Shift_F6":
	    			if (infobar.getLast_which_team() != null && !infobar.getLast_which_team().isEmpty()) {

					} else {
						infobar.setLast_which_team(lowerThird.getWhichTeamFlag());
					}
	    			
	    			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$ALL_LT_LOGOGRP$Side" + WhichSide + "$Select*FUNCTION*Omo*vis_con SET 0\0",print_writers);
    				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$All$BaseAll$Side" + WhichSide + "$Select*FUNCTION*Omo*vis_con SET 0\0",print_writers);
    				
    				switch (whatToProcess) {
    	    		case "Shift_F6":
    	    			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$All$"
    	    					+ "DataAll$Side" + WhichSide + "$Select*FUNCTION*Omo*vis_con SET 0\0",print_writers);
        				
    	    			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$All$DataAll$Side" + WhichSide
    							+ "$Select$BatsmanOut$TopGrp$HeaderGrp$txt_Header1*GEOM*TEXT SET "
    							+ lowerThird.getFirstName() + "\0",print_writers);
    					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$All$DataAll$Side" + WhichSide
    							+ "$Select$BatsmanOut$TopGrp$HeaderGrp$txt_Header2*GEOM*TEXT SET "
    							+ lowerThird.getSurName() + "\0",print_writers);
    	    			break;
    	    		default:
    	    			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$All$"
    	    					+ "DataAll$Side" + WhichSide + "$Select*FUNCTION*Omo*vis_con SET 20\0",print_writers);
        				
    	    			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$All$DataAll$Side" + WhichSide
    							+ "$Select$BatsmanOut02$TopGrp$HeaderGrp$txt_Header1*GEOM*TEXT SET "
    							+ lowerThird.getFirstName() + "\0",print_writers);
    					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$All$DataAll$Side" + WhichSide
    							+ "$Select$BatsmanOut02$TopGrp$HeaderGrp$txt_Header2*GEOM*TEXT SET "
    							+ lowerThird.getSurName() + "\0",print_writers);
    	    			break;	
    				}
    				
    				switch (config.getBroadcaster().toUpperCase()) {
	    	    	case Constants.BAN_AFG_SERIES:
	    	    		CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$ALL_LT_LOGOGRP$Side" + WhichSide
								+ "$Select$OneTeam$LTLogoGRP$LogoIn$LLC_LogoGrp$img_Badges*TEXTURE*IMAGE SET "
								+ Constants.BAN_AFG_SERIES_LOGO + lowerThird.getWhichTeamFlag() + "\0",print_writers);
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$ALL_LT_LOGOGRP$Side" + WhichSide
								+ "$Select$OneTeam$LTLogoGRP$LogoIn$LLC_LogoGrp$img_Badges02*TEXTURE*IMAGE SET "
								+ Constants.BAN_AFG_SERIES_LOGO + lowerThird.getWhichTeamFlag() + "\0",print_writers);
						
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$ALL_LT_LOGOGRP$Side" + WhichSide
		    					+ "$Select$OneTeam$LTLogoGRP$LogoIn$Outline$img_Base1*TEXTURE*IMAGE SET "
		    					+ Constants.BAN_AFG_SERIES_BASE1 + lowerThird.getWhichTeamFlag() + "\0",print_writers);
		    			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$ALL_LT_LOGOGRP$Side" + WhichSide
		    					+ "$Select$OneTeam$LTLogoGRP$LogoIn$Outline$img_Base2*TEXTURE*IMAGE SET "
		    					+ Constants.BAN_AFG_SERIES_BASE2 + lowerThird.getWhichTeamFlag() + "\0",print_writers);

						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$ALL_LT_LOGOGRP$Side" + WhichSide
								+ "$Select$OneTeam$LTLogoGRP$LogoIn$LLC_LogoGrp$img_Base2*TEXTURE*IMAGE SET "
								+ Constants.BAN_AFG_SERIES_BASE2 + lowerThird.getWhichTeamFlag() + "\0",print_writers);
						
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$All$BaseAll$Side" + WhichSide
		    					+ "$Select$OneTeam$img_Base2*TEXTURE*IMAGE SET "
		    					+ Constants.BAN_AFG_SERIES_BASE2 + lowerThird.getWhichTeamFlag() + "\0",print_writers);
	    	    		break;
	    	    	default:
	    	    		CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$ALL_LT_LOGOGRP$Side" + WhichSide
								+ "$Select$OneTeam$LTLogoGRP$LogoIn$LLC_LogoGrp$img_Badges*TEXTURE*IMAGE SET "
								+ Constants.ACC_FLAG + lowerThird.getWhichTeamFlag() + "\0",print_writers);
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$ALL_LT_LOGOGRP$Side" + WhichSide
								+ "$Select$OneTeam$LTLogoGRP$LogoIn$LLC_LogoGrp$img_Badges02*TEXTURE*IMAGE SET "
								+ Constants.ACC_FLAG + lowerThird.getWhichTeamFlag() + "\0",print_writers);
	    	    		break;
	    			}
    				
	    			break;
	    		case "Shift_F3":
	    			
	    			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$ALL_LT_LOGOGRP$Side" + WhichSide + "$Select*FUNCTION*Omo*vis_con SET 0 \0",print_writers);
    				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$All$BaseAll$Side" + WhichSide + "$Select*FUNCTION*Omo*vis_con SET 0 \0",print_writers);
    				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$All$DataAll$Side" + WhichSide + "$Select*FUNCTION*Omo*vis_con SET 5 \0",print_writers);
    				
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$All$DataAll$Side" + WhichSide
							+ "$Select$FOW$TopGrp$HeaderGrp$txt_Header1*GEOM*TEXT SET "
							+ lowerThird.getFirstName() + "\0",print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$All$DataAll$Side" + WhichSide
							+ "$Select$FOW$TopGrp$HeaderGrp$txt_Header2*GEOM*TEXT SET "
							+ "" + "\0",print_writers);
					TimeUnit.MILLISECONDS.sleep(2);

					switch (config.getBroadcaster().toUpperCase()) {
	    	    	case Constants.BAN_AFG_SERIES:
	    	    		CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$ALL_LT_LOGOGRP$Side" + WhichSide
								+ "$Select$OneTeam$LTLogoGRP$LogoIn$LLC_LogoGrp$img_Badges*TEXTURE*IMAGE SET "
								+ Constants.BAN_AFG_SERIES_LOGO + lowerThird.getWhichTeamFlag() + "\0",print_writers);
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$ALL_LT_LOGOGRP$Side" + WhichSide
								+ "$Select$OneTeam$LTLogoGRP$LogoIn$LLC_LogoGrp$img_Badges02*TEXTURE*IMAGE SET "
								+ Constants.BAN_AFG_SERIES_LOGO + lowerThird.getWhichTeamFlag() + "\0",print_writers);
						
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$ALL_LT_LOGOGRP$Side" + WhichSide
		    					+ "$Select$OneTeam$LTLogoGRP$LogoIn$Outline$img_Base1*TEXTURE*IMAGE SET "
		    					+ Constants.BAN_AFG_SERIES_BASE1 + lowerThird.getWhichTeamFlag() + "\0",print_writers);
		    			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$ALL_LT_LOGOGRP$Side" + WhichSide
		    					+ "$Select$OneTeam$LTLogoGRP$LogoIn$Outline$img_Base2*TEXTURE*IMAGE SET "
		    					+ Constants.BAN_AFG_SERIES_BASE2 + lowerThird.getWhichTeamFlag() + "\0",print_writers);

						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$ALL_LT_LOGOGRP$Side" + WhichSide
								+ "$Select$OneTeam$LTLogoGRP$LogoIn$LLC_LogoGrp$img_Base2*TEXTURE*IMAGE SET "
								+ Constants.BAN_AFG_SERIES_BASE2 + lowerThird.getWhichTeamFlag() + "\0",print_writers);
						
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$All$BaseAll$Side" + WhichSide
		    					+ "$Select$OneTeam$img_Base2*TEXTURE*IMAGE SET "
		    					+ Constants.BAN_AFG_SERIES_BASE2 + lowerThird.getWhichTeamFlag() + "\0",print_writers);
						
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$All$DataAll$Side" + WhichSide
		    					+ "$Select$FOW$BottomGrp$RestDataGrp$RestData$img_Text*TEXTURE*IMAGE SET "
		    					+ Constants.BAN_AFG_SERIES_BASE2 + lowerThird.getWhichTeamFlag() + "\0",print_writers);
						
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$All$DataAll$Side" + WhichSide
		    					+ "$Select$FOW$BottomGrp$RestDataGrp$RestData$Data$1$img_Text*TEXTURE*IMAGE SET "
		    					+ Constants.BAN_AFG_SERIES_BASE2 + lowerThird.getWhichTeamFlag() + "\0",print_writers);
	    	    		break;
	    	    	default:
	    	    		CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$ALL_LT_LOGOGRP$Side" + WhichSide
								+ "$Select$OneTeam$LTLogoGRP$LogoIn$LLC_LogoGrp$img_Badges*TEXTURE*IMAGE SET "
								+ Constants.ACC_FLAG + lowerThird.getWhichTeamFlag() + "\0",print_writers);
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$ALL_LT_LOGOGRP$Side" + WhichSide
								+ "$Select$OneTeam$LTLogoGRP$LogoIn$LLC_LogoGrp$img_Badges02*TEXTURE*IMAGE SET "
								+ Constants.ACC_FLAG + lowerThird.getWhichTeamFlag() + "\0",print_writers);
	    	    		break;
	    			}
					
					if(Integer.valueOf(lowerThird.getBallsFacedText()) >= 10) {
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$All$DataAll$Side" + WhichSide
								+ "$Select$FOW$BottomGrp$ScoreGrp$txt_Data1*GEOM*TEXT SET "
								+ lowerThird.getScoreText() + "\0",print_writers);
						TimeUnit.MILLISECONDS.sleep(2);
					} else {
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$All$DataAll$Side" + WhichSide
								+ "$Select$FOW$BottomGrp$ScoreGrp$txt_Data1*GEOM*TEXT SET "
								+ lowerThird.getScoreText() + "-" + lowerThird.getBallsFacedText() + "\0",print_writers);
					}

					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$All$DataAll$Side" + WhichSide
							+ "$Select$FOW$BottomGrp$ScoreGrp$txt_Data2*GEOM*TEXT SET "
							+ lowerThird.getSubTitle() + "\0",print_writers);
					
	    			break;
	    		case "F9":
	    			
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$ALL_LT_LOGOGRP$Side" + WhichSide + "$Select*FUNCTION*Omo*vis_con SET 0 \0",print_writers);
    				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$All$BaseAll$Side" + WhichSide + "$Select*FUNCTION*Omo*vis_con SET 0 \0",print_writers);
    				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$All$DataAll$Side" + WhichSide + "$Select*FUNCTION*Omo*vis_con SET 8 \0",print_writers);
    				
    				switch (config.getBroadcaster().toUpperCase()) {
	    	    	case Constants.BAN_AFG_SERIES:
	    	    		CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$ALL_LT_LOGOGRP$Side" + WhichSide
								+ "$Select$OneTeam$LTLogoGRP$LogoIn$LLC_LogoGrp$img_Badges*TEXTURE*IMAGE SET " + Constants.BAN_AFG_SERIES_LOGO
								+ lowerThird.getWhichTeamFlag() + "\0",print_writers);
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$ALL_LT_LOGOGRP$Side" + WhichSide
								+ "$Select$OneTeam$LTLogoGRP$LogoIn$LLC_LogoGrp$img_Badges02*TEXTURE*IMAGE SET " + Constants.BAN_AFG_SERIES_LOGO
								+ lowerThird.getWhichTeamFlag() + "\0",print_writers);

						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$ALL_LT_LOGOGRP$Side" + WhichSide
								+ "$Select$OneTeam$LTLogoGRP$LogoIn$LLC_LogoGrp$img_Base2*TEXTURE*IMAGE SET "
								+ Constants.BAN_AFG_SERIES_BASE2 + lowerThird.getWhichTeamFlag() + "\0",print_writers);
						
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$ALL_LT_LOGOGRP$Side" + WhichSide
		    					+ "$Select$OneTeam$LTLogoGRP$LogoIn$Outline$img_Base1*TEXTURE*IMAGE SET "
		    					+ Constants.BAN_AFG_SERIES_BASE1 + lowerThird.getWhichTeamFlag() + "\0",print_writers);
		    			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$ALL_LT_LOGOGRP$Side" + WhichSide
		    					+ "$Select$OneTeam$LTLogoGRP$LogoIn$Outline$img_Base2*TEXTURE*IMAGE SET "
		    					+ Constants.BAN_AFG_SERIES_BASE2 + lowerThird.getWhichTeamFlag() + "\0",print_writers);
						
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$All$BaseAll$Side" + WhichSide
		    					+ "$Select$OneTeam$img_Base2*TEXTURE*IMAGE SET "
		    					+ Constants.BAN_AFG_SERIES_BASE2 + lowerThird.getWhichTeamFlag() + "\0",print_writers);

						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$All$DataAll$Side" + WhichSide
		    					+ "$Select$BowlerBowlingDetails$BottomGrp$RestDataGrp$RestData$img_Text*TEXTURE*IMAGE SET "
		    					+ Constants.BAN_AFG_SERIES_BASE2 + lowerThird.getWhichTeamFlag() + "\0",print_writers);
	    	    		break;
	    	    	default:
	    	    		CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$ALL_LT_LOGOGRP$Side" + WhichSide
								+ "$Select$OneTeam$LTLogoGRP$LogoIn$LLC_LogoGrp$img_Badges*TEXTURE*IMAGE SET " + Constants.ACC_FLAG
								+ lowerThird.getWhichTeamFlag() + "\0",print_writers);
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$ALL_LT_LOGOGRP$Side" + WhichSide
								+ "$Select$OneTeam$LTLogoGRP$LogoIn$LLC_LogoGrp$img_Badges02*TEXTURE*IMAGE SET " + Constants.ACC_FLAG
								+ lowerThird.getWhichTeamFlag() + "\0",print_writers);
	    	    		break;
	    			}
    				
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$All$DataAll$Side" + WhichSide
							+ "$Select$BowlerBowlingDetails$TopGrp$HeaderGrp$txt_Header1*GEOM*TEXT SET "
							+ lowerThird.getFirstName() + "\0",print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$All$DataAll$Side" + WhichSide
							+ "$Select$BowlerBowlingDetails$TopGrp$HeaderGrp$txt_Header2*GEOM*TEXT SET "
							+ lowerThird.getSurName() + "\0",print_writers);
					
	    			break;
	    		case "F5":
	    			
	    			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$ALL_LT_LOGOGRP$Side" + WhichSide + "$Select*FUNCTION*Omo*vis_con SET 0 \0",print_writers);
    				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$All$BaseAll$Side" + WhichSide + "$Select*FUNCTION*Omo*vis_con SET 0 \0",print_writers);
    				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$All$DataAll$Side" + WhichSide + "$Select*FUNCTION*Omo*vis_con SET 12 \0",print_writers);
    				
    				switch (config.getBroadcaster().toUpperCase()) {
	    	    	case Constants.BAN_AFG_SERIES:
	    	    		CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$ALL_LT_LOGOGRP$Side" + WhichSide
								+ "$Select$OneTeam$LTLogoGRP$LogoIn$LLC_LogoGrp$img_Badges*TEXTURE*IMAGE SET "
								+ Constants.BAN_AFG_SERIES_LOGO + lowerThird.getWhichTeamFlag() + "\0",print_writers);
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$ALL_LT_LOGOGRP$Side" + WhichSide
								+ "$Select$OneTeam$LTLogoGRP$LogoIn$LLC_LogoGrp$img_Badges02*TEXTURE*IMAGE SET "
								+ Constants.BAN_AFG_SERIES_LOGO + lowerThird.getWhichTeamFlag() + "\0",print_writers);

						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$ALL_LT_LOGOGRP$Side" + WhichSide
								+ "$Select$OneTeam$LTLogoGRP$LogoIn$LLC_LogoGrp$img_Base2*TEXTURE*IMAGE SET "
								+ Constants.BAN_AFG_SERIES_BASE2 + lowerThird.getWhichTeamFlag() + "\0",print_writers);
						
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$ALL_LT_LOGOGRP$Side" + WhichSide
		    					+ "$Select$OneTeam$LTLogoGRP$LogoIn$Outline$img_Base1*TEXTURE*IMAGE SET "
		    					+ Constants.BAN_AFG_SERIES_BASE1 + lowerThird.getWhichTeamFlag() + "\0",print_writers);
		    			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$ALL_LT_LOGOGRP$Side" + WhichSide
		    					+ "$Select$OneTeam$LTLogoGRP$LogoIn$Outline$img_Base2*TEXTURE*IMAGE SET "
		    					+ Constants.BAN_AFG_SERIES_BASE2 + lowerThird.getWhichTeamFlag() + "\0",print_writers);
						
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$All$BaseAll$Side" + WhichSide
		    					+ "$Select$OneTeam$img_Base2*TEXTURE*IMAGE SET "
		    					+ Constants.BAN_AFG_SERIES_BASE2 + lowerThird.getWhichTeamFlag() + "\0",print_writers);

						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$All$DataAll$Side" + WhichSide
		    					+ "$Select$PlayerStats$BottomGrp$RestDataGrp$RestData$img_Text*TEXTURE*IMAGE SET "
		    					+ Constants.BAN_AFG_SERIES_BASE2 + lowerThird.getWhichTeamFlag() + "\0",print_writers);
	    	    		break;
	    	    	default:
	    	    		CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$ALL_LT_LOGOGRP$Side" + WhichSide
								+ "$Select$OneTeam$LTLogoGRP$LogoIn$LLC_LogoGrp$img_Badges*TEXTURE*IMAGE SET "
								+ Constants.ACC_FLAG + lowerThird.getWhichTeamFlag() + "\0",print_writers);
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$ALL_LT_LOGOGRP$Side" + WhichSide
								+ "$Select$OneTeam$LTLogoGRP$LogoIn$LLC_LogoGrp$img_Badges02*TEXTURE*IMAGE SET "
								+ Constants.ACC_FLAG + lowerThird.getWhichTeamFlag() + "\0",print_writers);
	    	    		break;
	    			}
    				
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$All$DataAll$Side" + WhichSide
							+ "$Select$PlayerStats$TopGrp$HeaderGrp$txt_Header1*GEOM*TEXT SET "
							+ lowerThird.getFirstName() + "\0",print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$All$DataAll$Side" + WhichSide
							+ "$Select$PlayerStats$TopGrp$HeaderGrp$txt_Header2*GEOM*TEXT SET "
							+ lowerThird.getSurName() + "\0",print_writers);

					if (lowerThird.getSubTitle().equalsIgnoreCase(CricketUtil.NOT_OUT)) {
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$All$DataAll$Side" + WhichSide
								+ "$Select$PlayerStats$BottomGrp$ScoreGrp$txt_Data1*GEOM*TEXT SET "
								+ lowerThird.getScoreText() + "*" + "\0",print_writers);
					} else {
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$All$DataAll$Side" + WhichSide
								+ "$Select$PlayerStats$BottomGrp$ScoreGrp$txt_Data1*GEOM*TEXT SET "
								+ lowerThird.getScoreText() + "\0",print_writers);
					}

					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$All$DataAll$Side" + WhichSide
							+ "$Select$PlayerStats$BottomGrp$ScoreGrp$txt_Data2*GEOM*TEXT SET OFF "
							+ lowerThird.getBallsFacedText() + "\0",print_writers);

	    			break;
	    		case "Alt_F12":
	    			
	    			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$ALL_LT_LOGOGRP$Side" + WhichSide + "$Select*FUNCTION*Omo*vis_con SET 0 \0",print_writers);
    				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$All$BaseAll$Side" + WhichSide + "$Select*FUNCTION*Omo*vis_con SET 0 \0",print_writers);
    				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$All$DataAll$Side" + WhichSide + "$Select*FUNCTION*Omo*vis_con SET 7 \0",print_writers);
    				
    				switch (config.getBroadcaster().toUpperCase()) {
	    	    	case Constants.BAN_AFG_SERIES:
	    	    		CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$ALL_LT_LOGOGRP$Side" + WhichSide
								+ "$Select$OneTeam$LTLogoGRP$LogoIn$LLC_LogoGrp$img_Badges*TEXTURE*IMAGE SET "
								+ Constants.BAN_AFG_SERIES_LOGO + lowerThird.getWhichTeamFlag() + "\0",print_writers);
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$ALL_LT_LOGOGRP$Side" + WhichSide
								+ "$Select$OneTeam$LTLogoGRP$LogoIn$LLC_LogoGrp$img_Badges02*TEXTURE*IMAGE SET "
								+ Constants.BAN_AFG_SERIES_LOGO + lowerThird.getWhichTeamFlag() + "\0",print_writers);

						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$ALL_LT_LOGOGRP$Side" + WhichSide
								+ "$Select$OneTeam$LTLogoGRP$LogoIn$LLC_LogoGrp$img_Base2*TEXTURE*IMAGE SET "
								+ Constants.BAN_AFG_SERIES_BASE2 + lowerThird.getWhichTeamFlag() + "\0",print_writers);
						
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$ALL_LT_LOGOGRP$Side" + WhichSide
		    					+ "$Select$OneTeam$LTLogoGRP$LogoIn$Outline$img_Base1*TEXTURE*IMAGE SET "
		    					+ Constants.BAN_AFG_SERIES_BASE1 + lowerThird.getWhichTeamFlag() + "\0",print_writers);
		    			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$ALL_LT_LOGOGRP$Side" + WhichSide
		    					+ "$Select$OneTeam$LTLogoGRP$LogoIn$Outline$img_Base2*TEXTURE*IMAGE SET "
		    					+ Constants.BAN_AFG_SERIES_BASE2 + lowerThird.getWhichTeamFlag() + "\0",print_writers);
						
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$All$BaseAll$Side" + WhichSide
		    					+ "$Select$OneTeam$img_Base2*TEXTURE*IMAGE SET "
		    					+ Constants.BAN_AFG_SERIES_BASE2 + lowerThird.getWhichTeamFlag() + "\0",print_writers);

						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$All$DataAll$Side" + WhichSide
		    					+ "$Select$BattingSummary$BottomGrp$RestDataGrp$RestData$img_Text*TEXTURE*IMAGE SET "
		    					+ Constants.BAN_AFG_SERIES_BASE2 + lowerThird.getWhichTeamFlag() + "\0",print_writers);
	    	    		break;
	    	    	default:
	    	    		CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$ALL_LT_LOGOGRP$Side" + WhichSide
								+ "$Select$OneTeam$LTLogoGRP$LogoIn$LLC_LogoGrp$img_Badges*TEXTURE*IMAGE SET "
								+ Constants.ACC_FLAG + lowerThird.getWhichTeamFlag() + "\0",print_writers);
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$ALL_LT_LOGOGRP$Side" + WhichSide
								+ "$Select$OneTeam$LTLogoGRP$LogoIn$LLC_LogoGrp$img_Badges02*TEXTURE*IMAGE SET "
								+ Constants.ACC_FLAG + lowerThird.getWhichTeamFlag() + "\0",print_writers);
	    	    		break;
	    			}
    				
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$All$DataAll$Side" + WhichSide
							+ "$Select$BattingSummary$TopGrp$HeaderGrp$txt_Header1*GEOM*TEXT SET "
							+ lowerThird.getHeaderText() + " \0",print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$All$DataAll$Side" + WhichSide
							+ "$Select$BattingSummary$TopGrp$HeaderGrp$txt_Header2*GEOM*TEXT SET \0",print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$All$DataAll$Side" + WhichSide
							+ "$Select$BattingSummary$BottomGrp$RestDataGrp$txt_Data1*GEOM*TEXT SET "
							+ "THIS MATCH" + " \0",print_writers);
					
					if (Integer.valueOf(lowerThird.getScoreText().split("-")[1])>=10) {
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$All$DataAll$Side" + WhichSide
								+ "$Select$BattingSummary$BottomGrp$ScoreGrp$txt_Data1*GEOM*TEXT SET "
								+ lowerThird.getScoreText().split("-")[0] + " \0",print_writers);
					} else {
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$All$DataAll$Side" + WhichSide
								+ "$Select$BattingSummary$BottomGrp$ScoreGrp$txt_Data1*GEOM*TEXT SET "
								+ lowerThird.getScoreText() + " \0",print_writers);
					}
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$All$DataAll$Side" + WhichSide
							+ "$Select$BattingSummary$BottomGrp$ScoreGrp$txt_Data2*GEOM*TEXT SET "
							+ lowerThird.getBallsFacedText() + " \0",print_writers);
	    			break;
	    		case "Shift_F5": case "Shift_F9":
	    			
	    			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$ALL_LT_LOGOGRP$Side" + WhichSide + "$Select*FUNCTION*Omo*vis_con SET 0 \0",print_writers);
    				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$All$BaseAll$Side" + WhichSide + "$Select*FUNCTION*Omo*vis_con SET 0 \0",print_writers);
    				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$All$DataAll$Side" + WhichSide + "$Select*FUNCTION*Omo*vis_con SET 7 \0",print_writers);
    				
    				switch (config.getBroadcaster().toUpperCase()) {
	    	    	case Constants.BAN_AFG_SERIES:
	    	    		CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$ALL_LT_LOGOGRP$Side" + WhichSide
								+ "$Select$OneTeam$LTLogoGRP$LogoIn$LLC_LogoGrp$img_Badges*TEXTURE*IMAGE SET "
								+ Constants.BAN_AFG_SERIES_LOGO + lowerThird.getWhichTeamFlag() + "\0",print_writers);
		    			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$ALL_LT_LOGOGRP$Side" + WhichSide
								+ "$Select$OneTeam$LTLogoGRP$LogoIn$LLC_LogoGrp$img_Badges02*TEXTURE*IMAGE SET "
								+ Constants.BAN_AFG_SERIES_LOGO + lowerThird.getWhichTeamFlag() + "\0",print_writers);

						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$ALL_LT_LOGOGRP$Side" + WhichSide
								+ "$Select$OneTeam$LTLogoGRP$LogoIn$LLC_LogoGrp$img_Base2*TEXTURE*IMAGE SET "
								+ Constants.BAN_AFG_SERIES_BASE2 + lowerThird.getWhichTeamFlag() + "\0",print_writers);
						
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$ALL_LT_LOGOGRP$Side" + WhichSide
		    					+ "$Select$OneTeam$LTLogoGRP$LogoIn$Outline$img_Base1*TEXTURE*IMAGE SET "
		    					+ Constants.BAN_AFG_SERIES_BASE1 + lowerThird.getWhichTeamFlag() + "\0",print_writers);
		    			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$ALL_LT_LOGOGRP$Side" + WhichSide
		    					+ "$Select$OneTeam$LTLogoGRP$LogoIn$Outline$img_Base2*TEXTURE*IMAGE SET "
		    					+ Constants.BAN_AFG_SERIES_BASE2 + lowerThird.getWhichTeamFlag() + "\0",print_writers);
						
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$All$BaseAll$Side" + WhichSide
		    					+ "$Select$OneTeam$img_Base2*TEXTURE*IMAGE SET "
		    					+ Constants.BAN_AFG_SERIES_BASE2 + lowerThird.getWhichTeamFlag() + "\0",print_writers);
						
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$All$DataAll$Side" + WhichSide
		    					+ "$Select$BattingSummary$BottomGrp$RestDataGrp$RestData$img_Text*TEXTURE*IMAGE SET "
		    					+ Constants.BAN_AFG_SERIES_BASE2 + lowerThird.getWhichTeamFlag() + "\0",print_writers);
	    	    		break;
	    	    	default:
	    	    		CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$ALL_LT_LOGOGRP$Side" + WhichSide
								+ "$Select$OneTeam$LTLogoGRP$LogoIn$LLC_LogoGrp$img_Badges*TEXTURE*IMAGE SET "
								+ Constants.ACC_FLAG + lowerThird.getWhichTeamFlag() + "\0",print_writers);
		    			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$ALL_LT_LOGOGRP$Side" + WhichSide
								+ "$Select$OneTeam$LTLogoGRP$LogoIn$LLC_LogoGrp$img_Badges02*TEXTURE*IMAGE SET "
								+ Constants.ACC_FLAG + lowerThird.getWhichTeamFlag() + "\0",print_writers);
	    	    		break;
	    			}
    				
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$All$DataAll$Side" + WhichSide
							+ "$Select$BattingSummary$TopGrp$HeaderGrp$txt_Header1*GEOM*TEXT SET "
							+ lowerThird.getFirstName() + " \0",print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$All$DataAll$Side" + WhichSide
							+ "$Select$BattingSummary$TopGrp$HeaderGrp$txt_Header2*GEOM*TEXT SET \0",print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$All$DataAll$Side" + WhichSide
							+ "$Select$BattingSummary$BottomGrp$RestDataGrp$txt_Data1*GEOM*TEXT SET "
							+ "THIS MATCH" + " \0",print_writers);
					
					
					switch(whatToProcess) {
    				
    				case "Shift_F5":
    					if (lowerThird.getSubTitle().equalsIgnoreCase("*")) {
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$All$DataAll$Side" + WhichSide
									+ "$Select$BattingSummary$BottomGrp$ScoreGrp$txt_Data1*GEOM*TEXT SET "
									+ lowerThird.getScoreText() + "*" + " \0",print_writers);
						} else {
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$All$DataAll$Side" + WhichSide
									+ "$Select$BattingSummary$BottomGrp$ScoreGrp$txt_Data1*GEOM*TEXT SET "
									+ lowerThird.getScoreText() + " \0",print_writers);
						}
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$All$DataAll$Side" + WhichSide
								+ "$Select$BattingSummary$BottomGrp$ScoreGrp$txt_Data2*GEOM*TEXT SET "
								+ lowerThird.getBallsFacedText() + " \0",print_writers);
						
    					break;
    				case "Shift_F9":
    					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$All$DataAll$Side" + WhichSide
								+ "$Select$BattingSummary$BottomGrp$ScoreGrp$txt_Data1*GEOM*TEXT SET "
								+ lowerThird.getScoreText() + " \0",print_writers);
					
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$All$DataAll$Side" + WhichSide
								+ "$Select$BattingSummary$BottomGrp$ScoreGrp$txt_Data2*GEOM*TEXT SET "
								+ lowerThird.getBallsFacedText() + " \0",print_writers);
    					break;	
    				}
					
	    			break;
	    		case "Control_F9":
	    			
	    			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$ALL_LT_LOGOGRP$Side" + WhichSide + "$Select*FUNCTION*Omo*vis_con SET 3 \0",print_writers);
    				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$All$BaseAll$Side" + WhichSide + "$Select*FUNCTION*Omo*vis_con SET 3 \0",print_writers);
    				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$All$DataAll$Side" + WhichSide + "$Select*FUNCTION*Omo*vis_con SET 3 \0",print_writers);
    				
    				switch (config.getBroadcaster().toUpperCase()) {
	    	    	case Constants.BAN_AFG_SERIES:
	    	    		CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$ALL_LT_LOGOGRP$Side" + WhichSide
	    						+ "$Select$OneTeam_Small$LTLogoGRP$LogoIn$LLC_LogoGrp$img_Badges*TEXTURE*IMAGE SET "
	    						+ Constants.BAN_AFG_SERIES_LOGO + lowerThird.getWhichTeamFlag() + "\0",print_writers);
		    			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$ALL_LT_LOGOGRP$Side" + WhichSide
	    						+ "$Select$OneTeam_Small$LTLogoGRP$LogoIn$LLC_LogoGrp$img_Badges02*TEXTURE*IMAGE SET "
	    						+ Constants.BAN_AFG_SERIES_LOGO + lowerThird.getWhichTeamFlag() + "\0",print_writers);
	    				
	    				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$ALL_LT_LOGOGRP$Side" + WhichSide
	    						+ "$Select$OneTeam_Small$LTLogoGRP$LogoIn$LLC_LogoGrp$img_Base2*TEXTURE*IMAGE SET "
	    						+ Constants.BAN_AFG_SERIES_BASE2 + lowerThird.getWhichTeamFlag() + "\0",print_writers);
	    				
	    				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$ALL_LT_LOGOGRP$Side" + WhichSide
								+ "$Select$OneTeam_Small$LTLogoGRP$LogoIn$Outline$img_Base2*TEXTURE*IMAGE SET "
								+ Constants.BAN_AFG_SERIES_BASE2 + lowerThird.getWhichTeamFlag() + "\0",print_writers);
	    				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$ALL_LT_LOGOGRP$Side" + WhichSide
								+ "$Select$OneTeam_Small$LTLogoGRP$LogoIn$Outline$img_Base1*TEXTURE*IMAGE SET "
								+ Constants.BAN_AFG_SERIES_BASE1 + lowerThird.getWhichTeamFlag() + "\0",print_writers);
	    				
	    				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$All$BaseAll$Side" + WhichSide
		    					+ "$Select$SingleSuper$img_Base2*TEXTURE*IMAGE SET "
		    					+ Constants.BAN_AFG_SERIES_BASE2 + lowerThird.getWhichTeamFlag() + "\0",print_writers);
	    	    		break;
	    	    	default:
	    	    		CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$ALL_LT_LOGOGRP$Side" + WhichSide
	    						+ "$Select$OneTeam_Small$LTLogoGRP$LogoIn$LLC_LogoGrp$img_Badges*TEXTURE*IMAGE SET "
	    						+ Constants.ACC_FLAG + lowerThird.getWhichTeamFlag() + "\0",print_writers);
//		    			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$ALL_LT_LOGOGRP$Side" + WhichSide
//	    						+ "$Select$OneTeam_Small$LTLogoGRP$LogoIn$LLC_LogoGrp$img_Badges02*TEXTURE*IMAGE SET "
//	    						+ Constants.ACC_FLAG + lowerThird.getWhichTeamFlag() + "\0",print_writers);
	    	    		break;
	    			}
    				
	    			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$All$DataAll$Side" + WhichSide
    						+ "$Select$NameSuperSingle$TopGrp$HeaderGrp$txt_Header1*GEOM*TEXT SET "
    						+ lowerThird.getFirstName() + "\0",print_writers);
    				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$All$DataAll$Side" + WhichSide
    						+ "$Select$NameSuperSingle$TopGrp$HeaderGrp$txt_Header2*GEOM*TEXT SET "
    						+ lowerThird.getSurName() + "\0",print_writers);

	    			break;
	    		case "Control_F5":
	    			
	    			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$ALL_LT_LOGOGRP$Side" + WhichSide + "$Select*FUNCTION*Omo*vis_con SET 3 \0",print_writers);
    				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$All$BaseAll$Side" + WhichSide + "$Select*FUNCTION*Omo*vis_con SET 3 \0",print_writers);
    				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$All$DataAll$Side" + WhichSide + "$Select*FUNCTION*Omo*vis_con SET 3 \0",print_writers);
    				
    				switch (config.getBroadcaster().toUpperCase()) {
	    	    	case Constants.BAN_AFG_SERIES:
	    	    		CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$ALL_LT_LOGOGRP$Side" + WhichSide
	    						+ "$Select$OneTeam_Small$LTLogoGRP$LogoIn$LLC_LogoGrp$img_Badges*TEXTURE*IMAGE SET "
	    						+ Constants.BAN_AFG_SERIES_LOGO + lowerThird.getWhichTeamFlag() + "\0",print_writers);
		    			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$ALL_LT_LOGOGRP$Side" + WhichSide
	    						+ "$Select$OneTeam_Small$LTLogoGRP$LogoIn$LLC_LogoGrp$img_Badges02*TEXTURE*IMAGE SET "
	    						+ Constants.BAN_AFG_SERIES_LOGO + lowerThird.getWhichTeamFlag() + "\0",print_writers);
	    				
	    				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$ALL_LT_LOGOGRP$Side" + WhichSide
	    						+ "$Select$OneTeam_Small$LTLogoGRP$LogoIn$LLC_LogoGrp$img_Base2*TEXTURE*IMAGE SET "
	    						+ Constants.BAN_AFG_SERIES_BASE2 + lowerThird.getWhichTeamFlag() + "\0",print_writers);
	    				
	    				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$ALL_LT_LOGOGRP$Side" + WhichSide
								+ "$Select$OneTeam_Small$LTLogoGRP$LogoIn$Outline$img_Base2*TEXTURE*IMAGE SET "
								+ Constants.BAN_AFG_SERIES_BASE2 + lowerThird.getWhichTeamFlag() + "\0",print_writers);
	    				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$ALL_LT_LOGOGRP$Side" + WhichSide
								+ "$Select$OneTeam_Small$LTLogoGRP$LogoIn$Outline$img_Base1*TEXTURE*IMAGE SET "
								+ Constants.BAN_AFG_SERIES_BASE1 + lowerThird.getWhichTeamFlag() + "\0",print_writers);
	    				
	    				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$All$BaseAll$Side" + WhichSide
		    					+ "$Select$SingleSuper$img_Base2*TEXTURE*IMAGE SET "
		    					+ Constants.BAN_AFG_SERIES_BASE2 + lowerThird.getWhichTeamFlag() + "\0",print_writers);
	    	    		break;
	    	    	default:
	    	    		CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$ALL_LT_LOGOGRP$Side" + WhichSide
	    						+ "$Select$OneTeam_Small$LTLogoGRP$LogoIn$LLC_LogoGrp$img_Badges*TEXTURE*IMAGE SET "
	    						+ Constants.ACC_FLAG + lowerThird.getWhichTeamFlag() + "\0",print_writers);
//		    			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$ALL_LT_LOGOGRP$Side" + WhichSide
//	    						+ "$Select$OneTeam_Small$LTLogoGRP$LogoIn$LLC_LogoGrp$img_Badges02*TEXTURE*IMAGE SET "
//	    						+ Constants.ACC_FLAG + lowerThird.getWhichTeamFlag() + "\0",print_writers);
	    	    		break;
	    			}
    				
	    			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$All$DataAll$Side" + WhichSide
    						+ "$Select$NameSuperSingle$TopGrp$HeaderGrp$txt_Header1*GEOM*TEXT SET "
    						+ lowerThird.getFirstName() + "\0",print_writers);
    				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$All$DataAll$Side" + WhichSide
    						+ "$Select$NameSuperSingle$TopGrp$HeaderGrp$txt_Header2*GEOM*TEXT SET "
    						+ lowerThird.getSurName() + "\0",print_writers);

	    			break;
	    		case "F10":
	    			
	    			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$ALL_LT_LOGOGRP$Side" + WhichSide + "$Select*FUNCTION*Omo*vis_con SET 3 \0",print_writers);
    				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$All$BaseAll$Side" + WhichSide + "$Select*FUNCTION*Omo*vis_con SET 3 \0",print_writers);
    				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$All$DataAll$Side" + WhichSide + "$Select*FUNCTION*Omo*vis_con SET 3 \0",print_writers);
    				
	    			if(!lowerThird.getWhichSponsor().isEmpty() && !lowerThird.getWhichTeamFlag().isEmpty()) {
	    				
	    				switch (config.getBroadcaster().toUpperCase()) {
		    	    	case Constants.BAN_AFG_SERIES:
		    	    		CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$ALL_LT_LOGOGRP$Side" + WhichSide
		    						+ "$Select$OneTeam_Small$LTLogoGRP$LogoIn$LLC_LogoGrp$img_Badges*TEXTURE*IMAGE SET "
		    						+ Constants.BAN_AFG_SERIES_LOGO + lowerThird.getWhichTeamFlag() + "\0",print_writers);
			    			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$ALL_LT_LOGOGRP$Side" + WhichSide
		    						+ "$Select$OneTeam_Small$LTLogoGRP$LogoIn$LLC_LogoGrp$img_Badges02*TEXTURE*IMAGE SET "
		    						+ Constants.BAN_AFG_SERIES_LOGO + lowerThird.getWhichTeamFlag() + "\0",print_writers);
		    				
			    			if(lowerThird.getWhichTeamFlag().equalsIgnoreCase("TLogo_BW")) {
			    				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$ALL_LT_LOGOGRP$Side" + WhichSide
			    						+ "$Select$OneTeam_Small$LTLogoGRP$LogoIn$LLC_LogoGrp$img_Base2*TEXTURE*IMAGE SET "
			    						+ Constants.BAN_AFG_SERIES_BASE2 + "EVENT" + "\0",print_writers);
			    				
			    				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$ALL_LT_LOGOGRP$Side" + WhichSide
										+ "$Select$OneTeam_Small$LTLogoGRP$LogoIn$Outline$img_Base2*TEXTURE*IMAGE SET "
										+ Constants.BAN_AFG_SERIES_BASE2 + "EVENT" + "\0",print_writers);
			    				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$ALL_LT_LOGOGRP$Side" + WhichSide
										+ "$Select$OneTeam_Small$LTLogoGRP$LogoIn$Outline$img_Base1*TEXTURE*IMAGE SET "
										+ Constants.BAN_AFG_SERIES_BASE1 + "EVENT" + "\0",print_writers);
			    				
			    				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$All$BaseAll$Side" + WhichSide
				    					+ "$Select$SingleSuper$img_Base2*TEXTURE*IMAGE SET "
				    					+ Constants.BAN_AFG_SERIES_BASE2 + "EVENT" + "\0",print_writers);
			    			}else {
			    				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$ALL_LT_LOGOGRP$Side" + WhichSide
			    						+ "$Select$OneTeam_Small$LTLogoGRP$LogoIn$LLC_LogoGrp$img_Base2*TEXTURE*IMAGE SET "
			    						+ Constants.BAN_AFG_SERIES_BASE2 + lowerThird.getWhichTeamFlag() + "\0",print_writers);
			    				
			    				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$ALL_LT_LOGOGRP$Side" + WhichSide
										+ "$Select$OneTeam_Small$LTLogoGRP$LogoIn$Outline$img_Base2*TEXTURE*IMAGE SET "
										+ Constants.BAN_AFG_SERIES_BASE2 + lowerThird.getWhichTeamFlag() + "\0",print_writers);
			    				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$ALL_LT_LOGOGRP$Side" + WhichSide
										+ "$Select$OneTeam_Small$LTLogoGRP$LogoIn$Outline$img_Base1*TEXTURE*IMAGE SET "
										+ Constants.BAN_AFG_SERIES_BASE1 + lowerThird.getWhichTeamFlag() + "\0",print_writers);
			    				
			    				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$All$BaseAll$Side" + WhichSide
				    					+ "$Select$SingleSuper$img_Base2*TEXTURE*IMAGE SET "
				    					+ Constants.BAN_AFG_SERIES_BASE2 + lowerThird.getWhichTeamFlag() + "\0",print_writers);
			    			}
		    				
		    				
		    				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$All$DataAll$Side" + WhichSide
			    					+ "$Select$NameSuperSingle$TopGrp$HeaderGrp$img_Text2*TEXTURE*IMAGE SET " + Constants.BAN_AFG_SERIES_TEXT2 + lowerThird.getWhichTeamFlag()
			    					+ " \0",print_writers);
			    			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$All$DataAll$Side" + WhichSide
			    					+ "$Select$NameSuperSingle$BottomGrp$RestDataGrp$img_Text2*TEXTURE*IMAGE SET " + Constants.BAN_AFG_SERIES_TEXT2
			    					+ lowerThird.getWhichTeamFlag() + " \0",print_writers);
		    	    		break;
		    	    	default:
		    	    		CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$ALL_LT_LOGOGRP$Side" + WhichSide
		    						+ "$Select$OneTeam_Small$LTLogoGRP$LogoIn$LLC_LogoGrp$img_Badges*TEXTURE*IMAGE SET "
		    						+ Constants.ACC_FLAG + lowerThird.getWhichTeamFlag() + "\0",print_writers);
//			    			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$ALL_LT_LOGOGRP$Side" + WhichSide
//		    						+ "$Select$OneTeam_Small$LTLogoGRP$LogoIn$LLC_LogoGrp$img_Badges02*TEXTURE*IMAGE SET "
//		    						+ Constants.ACC_FLAG + lowerThird.getWhichTeamFlag() + "\0",print_writers);
		    	    		break;
		    			}
					}else if(!lowerThird.getWhichSponsor().isEmpty() && lowerThird.getWhichTeamFlag().isEmpty()) {
						
						switch (config.getBroadcaster().toUpperCase()) {
		    	    	case Constants.BAN_AFG_SERIES:
		    	    		CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$ALL_LT_LOGOGRP$Side" + WhichSide
		    						+ "$Select$OneTeam_Small$LTLogoGRP$LogoIn$LLC_LogoGrp$img_Badges*TEXTURE*IMAGE SET "
		    						+ Constants.BAN_AFG_SERIES_LOGO + "TLogo_BW" + "\0",print_writers);
			    			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$ALL_LT_LOGOGRP$Side" + WhichSide
		    						+ "$Select$OneTeam_Small$LTLogoGRP$LogoIn$LLC_LogoGrp$img_Badges02*TEXTURE*IMAGE SET "
		    						+ Constants.BAN_AFG_SERIES_LOGO + "TLogo_BW" + "\0",print_writers);
		    				
		    				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$ALL_LT_LOGOGRP$Side" + WhichSide
		    						+ "$Select$OneTeam_Small$LTLogoGRP$LogoIn$LLC_LogoGrp$img_Base2*TEXTURE*IMAGE SET "
		    						+ Constants.BAN_AFG_SERIES_BASE2 + "EVENT" + "\0",print_writers);
		    				
		    				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$ALL_LT_LOGOGRP$Side" + WhichSide
									+ "$Select$OneTeam_Small$LTLogoGRP$LogoIn$Outline$img_Base2*TEXTURE*IMAGE SET "
									+ Constants.BAN_AFG_SERIES_BASE2 + "EVENT" + "\0",print_writers);
		    				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$ALL_LT_LOGOGRP$Side" + WhichSide
									+ "$Select$OneTeam_Small$LTLogoGRP$LogoIn$Outline$img_Base1*TEXTURE*IMAGE SET "
									+ Constants.BAN_AFG_SERIES_BASE1 + "EVENT" + "\0",print_writers);
		    				
		    				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$All$BaseAll$Side" + WhichSide
			    					+ "$Select$SingleSuper$img_Base2*TEXTURE*IMAGE SET "
			    					+ Constants.BAN_AFG_SERIES_BASE2 + "EVENT" + "\0",print_writers);
		    				
		    				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$All$DataAll$Side" + WhichSide
			    					+ "$Select$NameSuperSingle$TopGrp$HeaderGrp$img_Text2*TEXTURE*IMAGE SET " + Constants.BAN_AFG_SERIES_TEXT2 + "EVENT"
			    					+ " \0",print_writers);
			    			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$All$DataAll$Side" + WhichSide
			    					+ "$Select$NameSuperSingle$BottomGrp$RestDataGrp$img_Text2*TEXTURE*IMAGE SET " + Constants.BAN_AFG_SERIES_TEXT2
			    					+ "EVENT" + " \0",print_writers);
		    	    		break;
		    	    	default:
		    	    		CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$ALL_LT_LOGOGRP$Side" + WhichSide
		    						+ "$Select$OneTeam_Small$LTLogoGRP$LogoIn$LLC_LogoGrp$img_Badges*TEXTURE*IMAGE SET "
		    						+ Constants.ACC_FLAG + "EVENT" + "\0",print_writers);
//			    			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$ALL_LT_LOGOGRP$Side" + WhichSide
//		    						+ "$Select$OneTeam_Small$LTLogoGRP$LogoIn$LLC_LogoGrp$img_Badges02*TEXTURE*IMAGE SET "
//		    						+ Constants.ACC_FLAG + "EVENT" + "\0",print_writers);
		    	    		break;
		    			}
					}else if(lowerThird.getWhichSponsor().isEmpty() && !lowerThird.getWhichTeamFlag().isEmpty()) {
						switch (config.getBroadcaster().toUpperCase()) {
		    	    	case Constants.BAN_AFG_SERIES:
		    	    		System.out.println("HELLO");
		    	    		CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$ALL_LT_LOGOGRP$Side" + WhichSide
		    						+ "$Select$OneTeam_Small$LTLogoGRP$LogoIn$LLC_LogoGrp$img_Badges*TEXTURE*IMAGE SET "
		    						+ Constants.BAN_AFG_SERIES_LOGO + lowerThird.getWhichTeamFlag() + "\0",print_writers);
			    			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$ALL_LT_LOGOGRP$Side" + WhichSide
		    						+ "$Select$OneTeam_Small$LTLogoGRP$LogoIn$LLC_LogoGrp$img_Badges02*TEXTURE*IMAGE SET "
		    						+ Constants.BAN_AFG_SERIES_LOGO + lowerThird.getWhichTeamFlag() + "\0",print_writers);
		    				
			    			if(lowerThird.getWhichTeamFlag().equalsIgnoreCase("TLogo_BW")) {
			    				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$ALL_LT_LOGOGRP$Side" + WhichSide
			    						+ "$Select$OneTeam_Small$LTLogoGRP$LogoIn$LLC_LogoGrp$img_Base2*TEXTURE*IMAGE SET "
			    						+ Constants.BAN_AFG_SERIES_BASE2 + "EVENT" + "\0",print_writers);
			    				
			    				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$ALL_LT_LOGOGRP$Side" + WhichSide
										+ "$Select$OneTeam_Small$LTLogoGRP$LogoIn$Outline$img_Base2*TEXTURE*IMAGE SET "
										+ Constants.BAN_AFG_SERIES_BASE2 + "EVENT" + "\0",print_writers);
			    				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$ALL_LT_LOGOGRP$Side" + WhichSide
										+ "$Select$OneTeam_Small$LTLogoGRP$LogoIn$Outline$img_Base1*TEXTURE*IMAGE SET "
										+ Constants.BAN_AFG_SERIES_BASE1 + "EVENT" + "\0",print_writers);
			    				
			    				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$All$BaseAll$Side" + WhichSide
				    					+ "$Select$SingleSuper$img_Base2*TEXTURE*IMAGE SET "
				    					+ Constants.BAN_AFG_SERIES_BASE2 + "EVENT" + "\0",print_writers);
			    			}else {
			    				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$ALL_LT_LOGOGRP$Side" + WhichSide
			    						+ "$Select$OneTeam_Small$LTLogoGRP$LogoIn$LLC_LogoGrp$img_Base2*TEXTURE*IMAGE SET "
			    						+ Constants.BAN_AFG_SERIES_BASE2 + lowerThird.getWhichTeamFlag() + "\0",print_writers);
			    				
			    				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$ALL_LT_LOGOGRP$Side" + WhichSide
										+ "$Select$OneTeam_Small$LTLogoGRP$LogoIn$Outline$img_Base2*TEXTURE*IMAGE SET "
										+ Constants.BAN_AFG_SERIES_BASE2 + lowerThird.getWhichTeamFlag() + "\0",print_writers);
			    				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$ALL_LT_LOGOGRP$Side" + WhichSide
										+ "$Select$OneTeam_Small$LTLogoGRP$LogoIn$Outline$img_Base1*TEXTURE*IMAGE SET "
										+ Constants.BAN_AFG_SERIES_BASE1 + lowerThird.getWhichTeamFlag() + "\0",print_writers);
			    				
			    				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$All$BaseAll$Side" + WhichSide
				    					+ "$Select$SingleSuper$img_Base2*TEXTURE*IMAGE SET "
				    					+ Constants.BAN_AFG_SERIES_BASE2 + lowerThird.getWhichTeamFlag() + "\0",print_writers);
			    			}

		    				
		    				
		    				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$All$DataAll$Side" + WhichSide
			    					+ "$Select$NameSuperSingle$TopGrp$HeaderGrp$img_Text2*TEXTURE*IMAGE SET " + Constants.BAN_AFG_SERIES_TEXT2 + lowerThird.getWhichTeamFlag()
			    					+ " \0",print_writers);
			    			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$All$DataAll$Side" + WhichSide
			    					+ "$Select$NameSuperSingle$BottomGrp$RestDataGrp$img_Text2*TEXTURE*IMAGE SET " + Constants.BAN_AFG_SERIES_TEXT2
			    					+ lowerThird.getWhichTeamFlag() + " \0",print_writers);
		    	    		break;
		    	    	default:
		    	    		CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$ALL_LT_LOGOGRP$Side" + WhichSide
		    						+ "$Select$OneTeam_Small$LTLogoGRP$LogoIn$LLC_LogoGrp$img_Badges*TEXTURE*IMAGE SET "
		    						+ Constants.ACC_FLAG + lowerThird.getWhichTeamFlag() + "\0",print_writers);
//			    			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$ALL_LT_LOGOGRP$Side" + WhichSide
//		    						+ "$Select$OneTeam_Small$LTLogoGRP$LogoIn$LLC_LogoGrp$img_Badges02*TEXTURE*IMAGE SET "
//		    						+ Constants.ACC_FLAG + lowerThird.getWhichTeamFlag() + "\0",print_writers);
		    	    		break;
		    			}
					}else {
						switch (config.getBroadcaster().toUpperCase()) {
		    	    	case Constants.BAN_AFG_SERIES:
		    	    		CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$ALL_LT_LOGOGRP$Side" + WhichSide
		    						+ "$Select$OneTeam_Small$LTLogoGRP$LogoIn$LLC_LogoGrp$img_Badges*TEXTURE*IMAGE SET "
		    						+ Constants.BAN_AFG_SERIES_LOGO + "TLogo_BW" + "\0",print_writers);
			    			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$ALL_LT_LOGOGRP$Side" + WhichSide
		    						+ "$Select$OneTeam_Small$LTLogoGRP$LogoIn$LLC_LogoGrp$img_Badges02*TEXTURE*IMAGE SET "
		    						+ Constants.BAN_AFG_SERIES_LOGO + "TLogo_BW" + "\0",print_writers);
		    				
		    				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$ALL_LT_LOGOGRP$Side" + WhichSide
		    						+ "$Select$OneTeam_Small$LTLogoGRP$LogoIn$LLC_LogoGrp$img_Base2*TEXTURE*IMAGE SET "
		    						+ Constants.BAN_AFG_SERIES_BASE2 + "EVENT" + "\0",print_writers);
		    				
		    				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$ALL_LT_LOGOGRP$Side" + WhichSide
									+ "$Select$OneTeam_Small$LTLogoGRP$LogoIn$Outline$img_Base2*TEXTURE*IMAGE SET "
									+ Constants.BAN_AFG_SERIES_BASE2 + "EVENT" + "\0",print_writers);
		    				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$ALL_LT_LOGOGRP$Side" + WhichSide
									+ "$Select$OneTeam_Small$LTLogoGRP$LogoIn$Outline$img_Base1*TEXTURE*IMAGE SET "
									+ Constants.BAN_AFG_SERIES_BASE1 + "EVENT" + "\0",print_writers);
		    				
		    				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$All$BaseAll$Side" + WhichSide
			    					+ "$Select$SingleSuper$img_Base2*TEXTURE*IMAGE SET "
			    					+ Constants.BAN_AFG_SERIES_BASE2 + "EVENT" + "\0",print_writers);
		    				
		    				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$All$DataAll$Side" + WhichSide
			    					+ "$Select$NameSuperSingle$TopGrp$HeaderGrp$img_Text2*TEXTURE*IMAGE SET " + Constants.BAN_AFG_SERIES_TEXT2 + "EVENT"
			    					+ " \0",print_writers);
			    			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$All$DataAll$Side" + WhichSide
			    					+ "$Select$NameSuperSingle$BottomGrp$RestDataGrp$img_Text2*TEXTURE*IMAGE SET " + Constants.BAN_AFG_SERIES_TEXT2
			    					+ "EVENT" + " \0",print_writers);
		    	    		break;
		    	    	default:
		    	    		CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$ALL_LT_LOGOGRP$Side" + WhichSide
		    						+ "$Select$OneTeam_Small$LTLogoGRP$LogoIn$LLC_LogoGrp$img_Badges*TEXTURE*IMAGE SET "
		    						+ Constants.ACC_FLAG + "EVENT" + "\0",print_writers);
//			    			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$ALL_LT_LOGOGRP$Side" + WhichSide
//		    						+ "$Select$OneTeam_Small$LTLogoGRP$LogoIn$LLC_LogoGrp$img_Badges02*TEXTURE*IMAGE SET "
//		    						+ Constants.ACC_FLAG + "EVENT" + "\0",print_writers);
		    	    		break;
		    			}
					}
	    			

	    			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$All$DataAll$Side" + WhichSide
    						+ "$Select$NameSuperSingle$TopGrp$HeaderGrp$txt_Header1*GEOM*TEXT SET "
    						+ lowerThird.getFirstName() + "\0",print_writers);
    				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$All$DataAll$Side" + WhichSide
    						+ "$Select$NameSuperSingle$TopGrp$HeaderGrp$txt_Header2*GEOM*TEXT SET "
    						+ lowerThird.getSurName() + "\0",print_writers);

    				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$All$DataAll$Side" + WhichSide
	    					+ "$Select$NameSuperSingle$BottomGrp$RestDataGrp$txt_Data1*GEOM*TEXT SET "
	    					+ lowerThird.getLeftText()[0] + "\0",print_writers);
	    			break;
	    		case "F8": case "Alt_F8":
	    			
    				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$ALL_LT_LOGOGRP$Side" + WhichSide + "$Select*FUNCTION*Omo*vis_con SET 3 \0",print_writers);
    				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$All$BaseAll$Side" + WhichSide + "$Select*FUNCTION*Omo*vis_con SET 3 \0",print_writers);
    				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$All$DataAll$Side" + WhichSide + "$Select*FUNCTION*Omo*vis_con SET 3 \0",print_writers);
    				
    				switch (config.getBroadcaster().toUpperCase()) {
	    	    	case Constants.BAN_AFG_SERIES:
	    	    		CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$ALL_LT_LOGOGRP$Side" + WhichSide
	    						+ "$Select$OneTeam_Small$LTLogoGRP$LogoIn$LLC_LogoGrp$img_Badges*TEXTURE*IMAGE SET "
	    						+ Constants.BAN_AFG_SERIES_LOGO + lowerThird.getWhichTeamFlag() + "\0",print_writers);
		    			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$ALL_LT_LOGOGRP$Side" + WhichSide
	    						+ "$Select$OneTeam_Small$LTLogoGRP$LogoIn$LLC_LogoGrp$img_Badges02*TEXTURE*IMAGE SET "
	    						+ Constants.BAN_AFG_SERIES_LOGO + lowerThird.getWhichTeamFlag() + "\0",print_writers);
	    				
	    				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$ALL_LT_LOGOGRP$Side" + WhichSide
	    						+ "$Select$OneTeam_Small$LTLogoGRP$LogoIn$LLC_LogoGrp$img_Base2*TEXTURE*IMAGE SET "
	    						+ Constants.BAN_AFG_SERIES_BASE2 + lowerThird.getWhichTeamFlag() + "\0",print_writers);
	    				
	    				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$ALL_LT_LOGOGRP$Side" + WhichSide
								+ "$Select$OneTeam_Small$LTLogoGRP$LogoIn$Outline$img_Base2*TEXTURE*IMAGE SET "
								+ Constants.BAN_AFG_SERIES_BASE2 + lowerThird.getWhichTeamFlag() + "\0",print_writers);
	    				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$ALL_LT_LOGOGRP$Side" + WhichSide
								+ "$Select$OneTeam_Small$LTLogoGRP$LogoIn$Outline$img_Base1*TEXTURE*IMAGE SET "
								+ Constants.BAN_AFG_SERIES_BASE1 + lowerThird.getWhichTeamFlag() + "\0",print_writers);
	    				
	    				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$All$BaseAll$Side" + WhichSide
		    					+ "$Select$SingleSuper$img_Base2*TEXTURE*IMAGE SET "
		    					+ Constants.BAN_AFG_SERIES_BASE2 + lowerThird.getWhichTeamFlag() + "\0",print_writers);
	    	    		break;
	    	    	default:
	    	    		CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$ALL_LT_LOGOGRP$Side" + WhichSide
	    						+ "$Select$OneTeam_Small$LTLogoGRP$LogoIn$LLC_LogoGrp$img_Badges*TEXTURE*IMAGE SET "
	    						+ Constants.ACC_FLAG + lowerThird.getWhichTeamFlag() + "\0",print_writers);
//		    			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$ALL_LT_LOGOGRP$Side" + WhichSide
//	    						+ "$Select$OneTeam_Small$LTLogoGRP$LogoIn$LLC_LogoGrp$img_Badges02*TEXTURE*IMAGE SET "
//	    						+ Constants.ACC_FLAG + lowerThird.getWhichTeamFlag() + "\0",print_writers);
	    	    		break;
	    			}
    				
    				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$All$DataAll$Side" + WhichSide
							+ "$Select$NameSuperSingle$TopGrp$HeaderGrp$txt_Header1*GEOM*TEXT SET "
							+ lowerThird.getFirstName() + "\0",print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$All$DataAll$Side" + WhichSide
							+ "$Select$NameSuperSingle$TopGrp$HeaderGrp$txt_Header2*GEOM*TEXT SET "
							+ lowerThird.getSurName() + "\0",print_writers);
	    			break;
	    		}
	    		break;
	        case Constants.TRI_SERIES:  case Constants.MT20:
	            switch (whatToProcess) {
	            case "Control_c": 
    				switch (config.getBroadcaster().toUpperCase()) {
    				
    				case Constants.TRI_SERIES:  case Constants.MT20:
    					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$TopLine$img_Text1$Side"+ WhichSide + 
    							"$select_DataType*FUNCTION*Omo*vis_con SET 0\0", print_writers);
    					
    					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$TopLine$RightTextBand$Side"+ WhichSide + 
    							"$select_DataType*FUNCTION*Omo*vis_con SET 0\0", print_writers);
    					
    					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$TopLine$img_Text1$Side"+ WhichSide + 
    							"$select_DataType$txt_FirstName*GEOM*TEXT SET " + lowerThird.getFirstName() +"\0", print_writers);
    					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$TopLine$img_Text1$Side"+ WhichSide + 
    							"$select_DataType$txt_LastName*GEOM*TEXT SET " + lowerThird.getSurName() +"\0", print_writers);
    					
    					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$TopLine$RightTextBand$Side"+ WhichSide + 
    							"$txt_Designation*GEOM*TEXT SET " + lowerThird.getSubTitle() +"\0", print_writers);
    					
    					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$TopLine$LogoAll$Side"+ WhichSide + 
    							"$img_Logos*TEXTURE*IMAGE SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? 
    									Constants.TRI_SERIES_LOGO : Constants.MT20_LOGO)  + lowerThird.getWhichTeamFlag() +"\0", print_writers);
    					
    					break;
    				}
    				break;
	            case "Control_Shift_K":
	            	switch (config.getBroadcaster().toUpperCase()) {
    				case Constants.TRI_SERIES:  case Constants.MT20:
    					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$TopLine$img_Text1$Side"+ WhichSide + 
    							"$select_DataType*FUNCTION*Omo*vis_con SET 0\0", print_writers);
    					
    					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$TopLine$RightTextBand$Side"+ WhichSide + 
    							"$select_DataType*FUNCTION*Omo*vis_con SET 0\0", print_writers);
    					
    					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$Position_With_Graphics$TopLine$LogoAll$Side" + WhichSide + 
								"$img_Logos*TEXTURE*IMAGE SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? 
    									Constants.TRI_SERIES_LOGO : Constants.MT20_LOGO) + lowerThird.getWhichTeamFlag() + "\0", print_writers);
					
    					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$TopLine$img_Text1$Side"+ WhichSide + 
    							"$select_DataType$txt_FirstName*GEOM*TEXT SET " + lowerThird.getFirstName() +"\0", print_writers);
    					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$TopLine$img_Text1$Side"+ WhichSide + 
    							"$select_DataType$txt_LastName*GEOM*TEXT SET " + lowerThird.getSurName() +"\0", print_writers);
    					
    					break;
    				}
	            	break;
	            case "Control_Shift_X":
    				switch (config.getBroadcaster().toUpperCase()) {
    				case Constants.TRI_SERIES:  case Constants.MT20:
    					
    					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$TopLine$img_Text1$Side"+ WhichSide + 
    							"$select_DataType*FUNCTION*Omo*vis_con SET 0\0", print_writers);
    					
    					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$TopLine$RightTextBand$Side"+ WhichSide + 
    							"$select_DataType*FUNCTION*Omo*vis_con SET 1\0", print_writers);
    					
    					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$Position_With_Graphics$TopLine$LogoAll$Side" + WhichSide + 
								"$img_Logos*TEXTURE*IMAGE SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? 
    									Constants.TRI_SERIES_LOGO : Constants.MT20_LOGO) + lowerThird.getWhichTeamFlag() + "\0", print_writers);
					
    					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$TopLine$img_Text1$Side"+ WhichSide + 
    							"$select_DataType$txt_FirstName*GEOM*TEXT SET " + lowerThird.getFirstName() +"\0", print_writers);
    					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$TopLine$img_Text1$Side"+ WhichSide + 
    							"$select_DataType$txt_LastName*GEOM*TEXT SET " + lowerThird.getSurName() +"\0", print_writers);
    					
    					if(lowerThird.getSubTitle().equalsIgnoreCase(CricketUtil.NOT_OUT)) {
    						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$TopLine$RightTextBand$Side"+ WhichSide + 
	    							"$Score$txt_Runs*GEOM*TEXT SET " + lowerThird.getScoreText() + "*" +"\0", print_writers);
    					}else {
    						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$TopLine$RightTextBand$Side"+ WhichSide + 
	    							"$Score$txt_Runs*GEOM*TEXT SET " + lowerThird.getScoreText() +"\0", print_writers);
    					}
    					
    					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$TopLine$RightTextBand$Side"+ WhichSide + 
    							"$Score$txt_Balls*GEOM*TEXT SET " + lowerThird.getBallsFacedText() + " BALL" + CricketFunctions.Plural(Integer.valueOf(lowerThird.getBallsFacedText())).toUpperCase() + "\0", print_writers);
    					System.out.println(lowerThird.getBallsFacedText());
    					
    					break;
    				}
    				break;
	            case "Alt_F1": case "Alt_F2":
	            	CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$Position_With_Graphics$TopLine$LogoAll$Side" + WhichSide + 
							"$img_Logos*TEXTURE*IMAGE SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? 
									Constants.TRI_SERIES_LOGO : Constants.MT20_LOGO) + l3griff.getWhichTeamFlag() + "\0", print_writers);

	            	CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$Position_With_Graphics$TopLine$Text$Side"+ WhichSide + 
							"$img_Text1$select_DataType*FUNCTION*Omo*vis_con SET 0\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$TopLine$RightTextBand$TextAll$Side"+ WhichSide + 
							"$select_DataType*FUNCTION*Omo*vis_con SET 0\0", print_writers);
					
	            	CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$Position_With_Graphics$TopLine$Text$Side" + WhichSide 
							+ "$select_DataType$Name$txt_FirstName*GEOM*TEXT SET " + l3griff.getFirstName() + "\0", print_writers);
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$Position_With_Graphics$TopLine$Text$Side" + WhichSide 
							+ "$select_DataType$Name$txt_LastName*GEOM*TEXT SET \0", print_writers);
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$TopLine$RightTextBand$TextAll$Side"+ WhichSide + 
							"$txt_Designation*GEOM*TEXT SET THIS SERIES\0", print_writers);
					
					break;
	            case "Control_Shift_Q":
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$Position_With_Graphics$TopLine$LogoAll$Side" + WhichSide + 
							"$img_Logos*TEXTURE*IMAGE SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? 
									Constants.TRI_SERIES_LOGO : Constants.MT20_LOGO) + lowerThird.getWhichTeamFlag() + "\0", print_writers);

	            	CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$Position_With_Graphics$TopLine$Text$Side"+ WhichSide + 
							"$img_Text1$select_DataType*FUNCTION*Omo*vis_con SET 0\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$TopLine$RightTextBand$TextAll$Side"+ WhichSide + 
							"$select_DataType*FUNCTION*Omo*vis_con SET 1\0", print_writers);

					if(lowerThird.getHeaderText().equalsIgnoreCase("CURR_PART")) {
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$Position_With_Graphics$TopLine$Text$Side" + WhichSide 
								+ "$select_DataType$Name$txt_FirstName*GEOM*TEXT SET " + lowerThird.getLeftText()[0] + "\0", print_writers);
						
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$Position_With_Graphics$TopLine$Text$Side" + WhichSide 
								+ "$select_DataType$Name$txt_LastName*GEOM*TEXT SET \0", print_writers);
						
						if(inning.getPartnerships().size() == inning.getPartnerships().get(inning.getPartnerships().size() - 1).getPartnershipNumber()) {
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$TopLine$RightTextBand$TextAll$Side"+ WhichSide + 
									"$Score$txt_Runs*GEOM*TEXT SET " + inning.getPartnerships().get(inning.getPartnerships().size() - 1).getTotalRuns() + "*" +"\0", print_writers);
						}else {
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$TopLine$RightTextBand$TextAll$Side"+ WhichSide + 
									"$Score$txt_Runs*GEOM*TEXT SET " + inning.getPartnerships().get(inning.getPartnerships().size() - 1).getTotalRuns() +"\0", print_writers);
						}
						
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$TopLine$RightTextBand$TextAll$Side"+ WhichSide + 
								"$Score$txt_Balls*GEOM*TEXT SET " + 
								inning.getPartnerships().get(inning.getPartnerships().size() - 1).getTotalBalls() + " BALLS" + "\0", print_writers);
					}else {
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$Position_With_Graphics$TopLine$Text$Side" + WhichSide 
								+ "$select_DataType$Name$txt_FirstName*GEOM*TEXT SET " + lowerThird.getFirstName() + "\0", print_writers);
						
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$Position_With_Graphics$TopLine$Text$Side" + WhichSide 
								+ "$select_DataType$Name$txt_LastName*GEOM*TEXT SET \0", print_writers);
						
						if(Integer.valueOf(lowerThird.getScoreText().split("-")[1]) >= 10) {
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$TopLine$RightTextBand$TextAll$Side"+ WhichSide + 
									"$Score$txt_Runs*GEOM*TEXT SET " + lowerThird.getScoreText().split("-")[0] +"\0", print_writers);
						}else {
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$TopLine$RightTextBand$TextAll$Side"+ WhichSide + 
									"$Score$txt_Runs*GEOM*TEXT SET " + lowerThird.getScoreText() +"\0", print_writers);
						}
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$TopLine$RightTextBand$TextAll$Side"+ WhichSide + 
								"$Score$txt_Balls*GEOM*TEXT SET " + lowerThird.getBallsFacedText() + " OVERS" + "\0", print_writers);
						
					}
					
					break;
	            case "Control_Shift_B":
	            	switch (config.getBroadcaster().toUpperCase()) {
	    			case Constants.TRI_SERIES:  case Constants.MT20: 
	    				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT_NextToBat$Side"+WhichSide+"$TopLine$LogoAll$Side"+WhichSide+"$img_Logos*TEXTURE*IMAGE SET " 
	    						+ (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? 
    									Constants.TRI_SERIES_LOGO : Constants.MT20_LOGO) + inning.getBatting_team().getTeamBadge() + " \0", print_writers);
	    				
	    				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT_NextToBat$Side" + WhichSide + "$TopLine$Header_Out$Base" + 
								"$img_Base1*TEXTURE*IMAGE SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? 
										Constants.TRI_SERIES_BASE1 : Constants.MT20_BASE1) + lowerThird.getWhichTeamFlag() + "\0", print_writers);
		            	CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT_NextToBat$Side" + WhichSide + "$TopLine$Header_Out$Base" + 
								"$img_Base2*TEXTURE*IMAGE SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? 
										Constants.TRI_SERIES_BASE2 : Constants.MT20_BASE2) + lowerThird.getWhichTeamFlag() + "\0", print_writers);
		            	CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT_NextToBat$Side" + WhichSide + "$TopLine" + 
								"$img_Text1*TEXTURE*IMAGE SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? 
										Constants.TRI_SERIES_TEXT1 : Constants.MT20_TEXT1) + lowerThird.getWhichTeamFlag() + "\0", print_writers);
						
	    				break;
	            	}
	    			break;
	            case "Control_Shift_L":
					
					switch (config.getBroadcaster().toUpperCase()) {
					case Constants.TRI_SERIES:  case Constants.MT20:
						
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Lt_MatchID$Side0" + WhichSide + "$Team1$LogoGrp$TeamColourAll" + 
								"$img_Base1*TEXTURE*IMAGE SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? 
										Constants.TRI_SERIES_BASE1 : Constants.MT20_BASE1) + lowerThird.getSubTitle() + "\0", print_writers);
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Lt_MatchID$Side0" + WhichSide + "$Team1$LogoGrp$TeamColourAll" + 
								"$img_Base2*TEXTURE*IMAGE SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? 
										Constants.TRI_SERIES_BASE2 : Constants.MT20_BASE2) + lowerThird.getSubTitle() + "\0", print_writers);
						
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Lt_MatchID$Side0" + WhichSide + "$Team2$LogoGrp$TeamColourAll" + 
								"$img_Base1*TEXTURE*IMAGE SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? 
										Constants.TRI_SERIES_BASE1 : Constants.MT20_BASE1) + lowerThird.getScoreText() + "\0", print_writers);
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Lt_MatchID$Side0" + WhichSide + "$Team2$LogoGrp$TeamColourAll" + 
								"$img_Base2*TEXTURE*IMAGE SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? 
										Constants.TRI_SERIES_BASE2 : Constants.MT20_BASE2) + lowerThird.getScoreText() + "\0", print_writers);
						
						
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Lt_MatchID$Side0" + WhichSide + "$Team1$LogoGrp$Logo$Loop" + 
								"$img_Logo*TEXTURE*IMAGE SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? 
    									Constants.TRI_SERIES_LOGO : Constants.MT20_LOGO) + lowerThird.getSubTitle() + "\0", print_writers);
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Lt_MatchID$Side0" + WhichSide + "$Team1$TeamNameGrp" + 
								"$TeamName$txt_TeamName*GEOM*TEXT SET " + lowerThird.getHeaderText().split("-")[0] + " \0", print_writers);
						
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Lt_MatchID$Side0" + WhichSide + "$Team2$LogoGrp$Logo$Loop" + 
								"$img_Logo*TEXTURE*IMAGE SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? 
    									Constants.TRI_SERIES_LOGO : Constants.MT20_LOGO) + lowerThird.getScoreText() + "\0", print_writers);
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Lt_MatchID$Side0" + WhichSide + "$Team2$TeamNameGrp" + 
								"$TeamName$txt_TeamName*GEOM*TEXT SET " + lowerThird.getFirstName().split("-")[0] + " \0", print_writers);
						break;
						
					}
					
					break;
	            case "Control_Shift_O":
	            	CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT_PlayingXI$Side" + WhichSide + "$TopLine$Header_Out$Base" + 
							"$img_Base1*TEXTURE*IMAGE SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? 
									Constants.TRI_SERIES_BASE1 : Constants.MT20_BASE1) + lowerThird.getWhichTeamFlag() + "\0", print_writers);
	            	CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT_PlayingXI$Side" + WhichSide + "$TopLine$Header_Out$Base" + 
							"$img_Base2*TEXTURE*IMAGE SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? 
									Constants.TRI_SERIES_BASE2 : Constants.MT20_BASE2) + lowerThird.getWhichTeamFlag() + "\0", print_writers);
	            	CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT_PlayingXI$Side" + WhichSide + "$TopLine" + 
							"$img_Text1*TEXTURE*IMAGE SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? 
									Constants.TRI_SERIES_TEXT1 : Constants.MT20_TEXT1) + lowerThird.getWhichTeamFlag() + "\0", print_writers);
					
	            	CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT_PlayingXI$Side" + WhichSide + 
							"$TopLine$LogoAll$Side" + WhichSide +"$img_Logos*TEXTURE*IMAGE SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? 
									Constants.TRI_SERIES_LOGO : Constants.MT20_LOGO) + lowerThird.getWhichTeamFlag() + "\0", print_writers);
					break;
	            case "Control_a":
	            	CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$Position_With_Graphics$TopLine$LogoAll$Side" + WhichSide + 
							"$img_Logos*TEXTURE*IMAGE SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? 
									Constants.TRI_SERIES_LOGO : Constants.MT20_LOGO) + lowerThird.getWhichTeamFlag() + "\0", print_writers);

	            	CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$Position_With_Graphics$TopLine$Text$Side"+ WhichSide + 
							"$img_Text1$select_DataType*FUNCTION*Omo*vis_con SET 1\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$TopLine$RightTextBand$TextAll$Side"+ WhichSide + 
							"$select_DataType*FUNCTION*Omo*vis_con SET 1\0", print_writers);

					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$Position_With_Graphics$TopLine$Text$Side" + WhichSide 
							+ "$select_DataType$Name$txt_FirstName*GEOM*TEXT SET " + lowerThird.getFirstName() + "\0", print_writers);
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$Position_With_Graphics$TopLine$Text$Side" + WhichSide 
							+ "$select_DataType$Name$txt_LastName*GEOM*TEXT SET \0", print_writers);
					
					if(Integer.valueOf(lowerThird.getScoreText().split("-")[1])>=10) {
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$TopLine$RightTextBand$TextAll$Side"+ WhichSide + 
								"$Score$txt_Runs*GEOM*TEXT SET " + lowerThird.getScoreText().split("-")[0] +"\0", print_writers);
					}else {
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$TopLine$RightTextBand$TextAll$Side"+ WhichSide + 
								"$Score$txt_Runs*GEOM*TEXT SET " + lowerThird.getScoreText() +"\0", print_writers);
					}
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$TopLine$RightTextBand$TextAll$Side"+ WhichSide + 
							"$Score$txt_Balls*GEOM*TEXT SET " + lowerThird.getBallsFacedText() + " OVERS" + "\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$Position_With_Graphics$TopLine$Text$Side" + WhichSide 
							+ "$select_DataType$txt_Designation*GEOM*TEXT SET " +  lowerThird.getSubTitle() + "\0", print_writers);
	            	break;
	            case "Control_Shift_M":
					switch (config.getBroadcaster().toUpperCase()) {
					case Constants.TRI_SERIES:  case Constants.MT20:
						
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Lt_MatchID$Side0" + WhichSide + "$Team1$LogoGrp$TeamColourAll" + 
								"$img_Base1*TEXTURE*IMAGE SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? 
										Constants.TRI_SERIES_BASE1 : Constants.MT20_BASE1) + lowerThird.getHeaderText() + "\0", print_writers);
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Lt_MatchID$Side0" + WhichSide + "$Team1$LogoGrp$TeamColourAll" + 
								"$img_Base2*TEXTURE*IMAGE SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? 
										Constants.TRI_SERIES_BASE2 : Constants.MT20_BASE2) + lowerThird.getHeaderText() + "\0", print_writers);
						
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Lt_MatchID$Side0" + WhichSide + "$Team2$LogoGrp$TeamColourAll" + 
								"$img_Base1*TEXTURE*IMAGE SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? 
										Constants.TRI_SERIES_BASE1 : Constants.MT20_BASE1) + lowerThird.getFirstName() + "\0", print_writers);
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Lt_MatchID$Side0" + WhichSide + "$Team2$LogoGrp$TeamColourAll" + 
								"$img_Base2*TEXTURE*IMAGE SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? 
										Constants.TRI_SERIES_BASE2 : Constants.MT20_BASE2) + lowerThird.getFirstName() + "\0", print_writers);
						
						//home team
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Lt_MatchID$Side0" + WhichSide + "$Team1$LogoGrp$Logo$Loop" + 
								"$img_Logo*TEXTURE*IMAGE SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? 
    									Constants.TRI_SERIES_LOGO : Constants.MT20_LOGO) + lowerThird.getHeaderText() + "\0", print_writers);
						
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Lt_MatchID$Side0" + WhichSide + "$Team1$TeamNameGrp" + 
						"$TeamName$txt_TeamName*GEOM*TEXT SET " + lowerThird.getSubTitle() + " \0", print_writers);
						
						//away team
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Lt_MatchID$Side0" + WhichSide + "$Team2$LogoGrp$Logo$Loop" + 
								"$img_Logo*TEXTURE*IMAGE SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? 
    									Constants.TRI_SERIES_LOGO : Constants.MT20_LOGO) + lowerThird.getFirstName() + "\0", print_writers);
						
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Lt_MatchID$Side0" + WhichSide + "$Team2$TeamNameGrp" + 
								"$TeamName$txt_TeamName*GEOM*TEXT SET " + lowerThird.getBallsFacedText() + " \0", print_writers);
						
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Lt_MatchID$Side0" + WhichSide + "$DataPart$BottomPart" + 
								"$Text$Side" + WhichSide +"$txt_SubTitle*GEOM*TEXT SET " + lowerThird.getSurName() + " \0", print_writers);
						
						
						break;
					}	
					break;
	            case "Control_F5": case "Control_F9":
					switch (config.getBroadcaster().toUpperCase()) {
					
					case Constants.TRI_SERIES:  case Constants.MT20:
						if(lowerThird.getWhichTeamFlag() != null) {
							
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$Position_With_Graphics$TopLine$LogoAll$Side" + WhichSide + 
									"$img_Logos*TEXTURE*IMAGE SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? 
	    									Constants.TRI_SERIES_LOGO : Constants.MT20_LOGO) + lowerThird.getWhichTeamFlag() + "\0", print_writers);
						}
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$Position_With_Graphics$TopLine$Text$Side"+ WhichSide + 
								"$img_Text1$select_DataType*FUNCTION*Omo*vis_con SET 0\0", print_writers);
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$TopLine$RightTextBand$TextAll$Side"+ WhichSide + 
								"$select_DataType*FUNCTION*Omo*vis_con SET 0\0", print_writers);
						
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$Position_With_Graphics$TopLine$Text$Side" + WhichSide 
								+ "$select_DataType$Name$txt_FirstName*GEOM*TEXT SET " + lowerThird.getFirstName() + "\0", print_writers);						

						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$Position_With_Graphics$TopLine$Text$Side" + WhichSide 
								+ "$select_DataType$Name$txt_FirstName*GEOM*TEXT SET " + lowerThird.getFirstName() + "\0", print_writers);
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$Position_With_Graphics$TopLine$Text$Side" + WhichSide 
								+ "$select_DataType$Name$txt_LastName*GEOM*TEXT SET " + lowerThird.getSurName() + "\0", print_writers);
						
						break;
					}
					
					break;
					
	            case "F6": case "Control_F6": case "Shift_F6":
					switch (config.getBroadcaster().toUpperCase()) {
					case Constants.TRI_SERIES:  case Constants.MT20:
						if(lowerThird.getWhichTeamFlag() != null) {
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$Position_With_Graphics$TopLine$LogoAll$Side" + WhichSide + 
									"$img_Logos*TEXTURE*IMAGE SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? 
	    									Constants.TRI_SERIES_LOGO : Constants.MT20_LOGO) + lowerThird.getWhichTeamFlag() + "\0", print_writers);
						}
						

						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$Position_With_Graphics$TopLine$Text$Side"+ WhichSide + 
								"$img_Text1$select_DataType*FUNCTION*Omo*vis_con SET 0\0", print_writers);
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$TopLine$RightTextBand$TextAll$Side"+ WhichSide + 
								"$select_DataType*FUNCTION*Omo*vis_con SET 1\0", print_writers);

						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$Position_With_Graphics$TopLine$Text$Side" + WhichSide 
								+ "$select_DataType$Name$txt_FirstName*GEOM*TEXT SET " + lowerThird.getFirstName() + "\0", print_writers);
						
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$Position_With_Graphics$TopLine$Text$Side" + WhichSide 
								+ "$select_DataType$Name$txt_LastName*GEOM*TEXT SET " +  lowerThird.getSurName() + "\0", print_writers);
						
    					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$TopLine$RightTextBand$TextAll$Side"+ WhichSide + 
    							"$Score$txt_Runs*GEOM*TEXT SET " + lowerThird.getScoreText() +"\0", print_writers);
    					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$TopLine$RightTextBand$TextAll$Side"+ WhichSide + 
    							"$Score$txt_Balls*GEOM*TEXT SET " + lowerThird.getBallsFacedText() + " BALL" + 
    							CricketFunctions.Plural(Integer.valueOf(lowerThird.getBallsFacedText())).toUpperCase() +"\0", print_writers);
    					
						break;
					
					}
					
					break;
	                case "F8": case "Alt_F8":
	                	switch (config.getBroadcaster().toUpperCase()) {
	                	case Constants.TRI_SERIES:  case Constants.MT20:
	    					if(lowerThird.getWhichTeamFlag() != null) {
	    						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_NameSuper$TopLine$LogoAll$Side" + WhichSide + 
	    								"$img_Logos*TEXTURE*IMAGE SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? 
	        									Constants.TRI_SERIES_LOGO : Constants.MT20_LOGO) + lowerThird.getWhichTeamFlag() + "\0", print_writers);
	    						
//	    						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$Position_With_Graphics$TopLine$LogoAll$Side" + WhichSide + 
//										"$img_Logos*TEXTURE*IMAGE SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? 
//								Constants.TRI_SERIES_LOGO : Constants.MT20_LOGO) + lowerThird.getWhichTeamFlag() + "\0", print_writers);
	    					}

	    					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_NameSuper$TopLine$img_Text1$Side" + WhichSide 
	    							+ "$txt_FirstName*GEOM*TEXT SET " + lowerThird.getFirstName() + "\0", print_writers);
	    					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_NameSuper$TopLine$img_Text1$Side" + WhichSide 
	    							+ "$txt_LastName*GEOM*TEXT SET " + lowerThird.getSurName() + "\0", print_writers);
	    					
//	    					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$Position_With_Graphics$TopLine$Text$Side" + WhichSide 
//									+ "$select_DataType$Name$txt_FirstName*GEOM*TEXT SET " + lowerThird.getFirstName() + "\0", print_writers);
//							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$Position_With_Graphics$TopLine$Text$Side" + WhichSide 
//									+ "$select_DataType$Name$txt_LastName*GEOM*TEXT SET " +  lowerThird.getSurName() + "\0", print_writers);
	    					break;
	                	}
	                	break;
	                case "F10":
	    				switch (config.getBroadcaster().toUpperCase()) {	
	    				case Constants.TRI_SERIES:  case Constants.MT20:
//	    					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$Position_With_Graphics$TopLine$Text$Side"+ WhichSide + 
//	    							"$img_Text1$select_DataType*FUNCTION*Omo*vis_con SET 0\0", print_writers);
//	    					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$TopLine$RightTextBand$TextAll$Side"+ WhichSide + 
//	    							"$select_DataType*FUNCTION*Omo*vis_con SET 0\0", print_writers);
	    					
	    					if(!lowerThird.getWhichSponsor().isEmpty() && !lowerThird.getWhichTeamFlag().isEmpty()) {
	    						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_NameSuper$TopLine$LogoAll$Side" + WhichSide + 
	    								"$img_Logos*TEXTURE*IMAGE SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? 
	        									Constants.TRI_SERIES_LOGO : Constants.MT20_LOGO) + lowerThird.getWhichTeamFlag() + "\0", print_writers);
//	    						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$Position_With_Graphics$TopLine$LogoAll$Side" + WhichSide + 
//										"$img_Logos*TEXTURE*IMAGE SET " + Constants.TRI_SERIES_LOGO + lowerThird.getWhichTeamFlag() + "\0", print_writers);
	    					}else if(!lowerThird.getWhichSponsor().isEmpty() && lowerThird.getWhichTeamFlag().isEmpty()) {
	    						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_NameSuper$TopLine$LogoAll$Side" + WhichSide + 
	    								"$img_Logos*TEXTURE*IMAGE SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? 
	        									Constants.TRI_SERIES_LOGO : Constants.MT20_LOGO) + lowerThird.getWhichSponsor() + "\0", print_writers);
//	    						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$Position_With_Graphics$TopLine$LogoAll$Side" + WhichSide + 
//										"$img_Logos*TEXTURE*IMAGE SET " + Constants.TRI_SERIES_LOGO + "EVENT" + "\0", print_writers);
	    					}else if(lowerThird.getWhichSponsor().isEmpty() && !lowerThird.getWhichTeamFlag().isEmpty()) {
	    						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_NameSuper$TopLine$LogoAll$Side" + WhichSide + 
	    								"$img_Logos*TEXTURE*IMAGE SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? 
	        									Constants.TRI_SERIES_LOGO : Constants.MT20_LOGO) + lowerThird.getWhichTeamFlag() + "\0", print_writers);
//	    						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$Position_With_Graphics$TopLine$LogoAll$Side" + WhichSide + 
//										"$img_Logos*TEXTURE*IMAGE SET " + Constants.TRI_SERIES_LOGO + lowerThird.getWhichTeamFlag() + "\0", print_writers);
	    					}else {
	    						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_NameSuper$TopLine$LogoAll$Side" + WhichSide + 
	    								"$img_Logos*TEXTURE*IMAGE SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? 
	        									Constants.TRI_SERIES_LOGO : Constants.MT20_LOGO) + "EVENT" + "\0", print_writers);
//	    						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$Position_With_Graphics$TopLine$LogoAll$Side" + WhichSide + 
//										"$img_Logos*TEXTURE*IMAGE SET " + Constants.TRI_SERIES_LOGO + "EVENT" + "\0", print_writers);
	    					}
	    					
	    					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_NameSuper$TopLine$img_Text1$Side" + WhichSide 
	    							+ "$txt_FirstName*GEOM*TEXT SET " + lowerThird.getFirstName() + "\0", print_writers);
	    					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_NameSuper$TopLine$img_Text1$Side" + WhichSide 
	    							+ "$txt_LastName*GEOM*TEXT SET " + lowerThird.getSurName() + "\0", print_writers);
	    					
//	    					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$Position_With_Graphics$TopLine$Text$Side" + WhichSide 
//									+ "$select_DataType$Name$txt_FirstName*GEOM*TEXT SET " + lowerThird.getFirstName() + "\0", print_writers);
//							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$Position_With_Graphics$TopLine$Text$Side" + WhichSide 
//									+ "$select_DataType$Name$txt_LastName*GEOM*TEXT SET " +  lowerThird.getSurName() + "\0", print_writers);
	    					break;
	    				
	    				}
	    				break;
	                case "F7": case "F11": case "Control_s": case "Control_f":
	    				switch (config.getBroadcaster().toUpperCase()) {
	    				
	    				case Constants.TRI_SERIES:  case Constants.MT20:
	    					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$Position_With_Graphics$TopLine$Text$Side"+ WhichSide + 
	    							"$img_Text1$select_DataType*FUNCTION*Omo*vis_con SET 0\0", print_writers);
	    					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$TopLine$RightTextBand$TextAll$Side"+ WhichSide + 
	    							"$select_DataType*FUNCTION*Omo*vis_con SET 0\0", print_writers);
	    					
	    					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$TopLine$Text$Side"+ WhichSide + 
	    							"$select_DataType$txt_FirstName*GEOM*TEXT SET " + lowerThird.getFirstName() +"\0", print_writers);
	    					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$TopLine$Text$Side"+ WhichSide + 
	    							"$select_DataType$txt_LastName*GEOM*TEXT SET " + lowerThird.getSurName() +"\0", print_writers);
	    					
	    					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$TopLine$RightTextBand$TextAll$Side"+ WhichSide + 
	    							"$txt_Designation*GEOM*TEXT SET " + lowerThird.getSubTitle() +"\0", print_writers);
	    					
	    					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$TopLine$LogoAll$Side"+ WhichSide + 
	    							"$img_Logos*TEXTURE*IMAGE SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? 
	    									Constants.TRI_SERIES_LOGO : Constants.MT20_LOGO) + lowerThird.getWhichTeamFlag() +"\0", print_writers);
	    					
	    					break;
	    				}
	    				break;
	                case "F5":
	    				switch (config.getBroadcaster().toUpperCase()) {
	    				case Constants.TRI_SERIES:  case Constants.MT20:
	    					
	    					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$Position_With_Graphics$TopLine$Text$Side"+ WhichSide + 
	    							"$img_Text1$select_DataType*FUNCTION*Omo*vis_con SET 0\0", print_writers);
	    					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$TopLine$RightTextBand$TextAll$Side"+ WhichSide + 
	    							"$select_DataType*FUNCTION*Omo*vis_con SET 1\0", print_writers);
	    					
	    					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$Position_With_Graphics$TopLine$LogoAll$Side" + WhichSide + 
									"$img_Logos*TEXTURE*IMAGE SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? 
	    									Constants.TRI_SERIES_LOGO : Constants.MT20_LOGO) + lowerThird.getWhichTeamFlag() + "\0", print_writers);
						
	    					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$TopLine$Text$Side"+ WhichSide + 
	    							"$select_DataType$txt_FirstName*GEOM*TEXT SET " + lowerThird.getFirstName() +"\0", print_writers);
	    					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$TopLine$Text$Side"+ WhichSide + 
	    							"$select_DataType$txt_LastName*GEOM*TEXT SET " + lowerThird.getSurName() +"\0", print_writers);
	    					
	    					if(lowerThird.getSubTitle().equalsIgnoreCase(CricketUtil.NOT_OUT)) {
	    						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$TopLine$RightTextBand$TextAll$Side"+ WhichSide + 
		    							"$Score$txt_Runs*GEOM*TEXT SET " + lowerThird.getScoreText() + "*" +"\0", print_writers);
	    					}else {
	    						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$TopLine$RightTextBand$TextAll$Side"+ WhichSide + 
		    							"$Score$txt_Runs*GEOM*TEXT SET " + lowerThird.getScoreText() +"\0", print_writers);
	    					}
	    					
	    					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$TopLine$RightTextBand$TextAll$Side"+ WhichSide + 
	    							"$Score$txt_Balls*GEOM*TEXT SET " + lowerThird.getBallsFacedText() + " BALL" + 
	    							CricketFunctions.Plural(Integer.valueOf(lowerThird.getBallsFacedText())).toUpperCase() + "\0", print_writers);
	    					
	    					break;
	    				}
	    				break;
	    				
	    			case "F9":
	    				switch (config.getBroadcaster().toUpperCase()) {
	    				case Constants.TRI_SERIES:  case Constants.MT20:
	    					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$Position_With_Graphics$TopLine$Text$Side"+ WhichSide + 
	    							"$img_Text1$select_DataType*FUNCTION*Omo*vis_con SET 0\0", print_writers);
	    					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$TopLine$RightTextBand$TextAll$Side"+ WhichSide + 
	    							"$select_DataType*FUNCTION*Omo*vis_con SET 0\0", print_writers);
	    					
	    					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$Position_With_Graphics$TopLine$LogoAll$Side" + WhichSide + 
									"$img_Logos*TEXTURE*IMAGE SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? 
	    									Constants.TRI_SERIES_LOGO : Constants.MT20_LOGO) + lowerThird.getWhichTeamFlag() + "\0", print_writers);
						
	    					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$TopLine$Text$Side"+ WhichSide + 
	    							"$select_DataType$txt_FirstName*GEOM*TEXT SET " + lowerThird.getFirstName() +"\0", print_writers);
	    					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$TopLine$Text$Side"+ WhichSide + 
	    							"$select_DataType$txt_LastName*GEOM*TEXT SET " + lowerThird.getSurName() +"\0", print_writers);
	    					
	    					break;
	    				}
	    				
	    				break;
	    			case "Shift_F3":
	    				switch (config.getBroadcaster().toUpperCase()) {
	    				case Constants.TRI_SERIES:  case Constants.MT20:
	    					
	    					if(lowerThird.getWhichTeamFlag() != null) {
								CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$Position_With_Graphics$TopLine$LogoAll$Side" + WhichSide + 
										"$img_Logos*TEXTURE*IMAGE SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? 
		    									Constants.TRI_SERIES_LOGO : Constants.MT20_LOGO) + lowerThird.getWhichTeamFlag() + "\0", print_writers);
							}
							

	    					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$Position_With_Graphics$TopLine$Text$Side"+ WhichSide + 
	    							"$img_Text1$select_DataType*FUNCTION*Omo*vis_con SET 1\0", print_writers);
	    					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$TopLine$RightTextBand$TextAll$Side"+ WhichSide + 
	    							"$select_DataType*FUNCTION*Omo*vis_con SET 1\0", print_writers);

							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$Position_With_Graphics$TopLine$Text$Side" + WhichSide 
									+ "$select_DataType$Name$txt_FirstName*GEOM*TEXT SET " + lowerThird.getFirstName() + "\0", print_writers);
							
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$Position_With_Graphics$TopLine$Text$Side" + WhichSide 
									+ "$select_DataType$Name$txt_LastName*GEOM*TEXT SET \0", print_writers);
							
							if(Integer.valueOf(lowerThird.getBallsFacedText()) >= 10) {
								CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$TopLine$RightTextBand$TextAll$Side"+ WhichSide + 
		    							"$Score$txt_Runs*GEOM*TEXT SET " + lowerThird.getScoreText() +"\0", print_writers);
	    					}else {
	    						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$TopLine$RightTextBand$TextAll$Side"+ WhichSide + 
		    							"$Score$txt_Runs*GEOM*TEXT SET " + lowerThird.getScoreText() + "-" + lowerThird.getBallsFacedText() +"\0", print_writers);
	    					}
							
	    					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$TopLine$RightTextBand$TextAll$Side"+ WhichSide + 
	    							"$Score$txt_Balls*GEOM*TEXT SET " + lowerThird.getSubTitle() + " OVERS" + "\0", print_writers);
	    					
	    					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$Position_With_Graphics$TopLine$Text$Side" + WhichSide 
									+ "$select_DataType$txt_Designation*GEOM*TEXT SET " +  lowerThird.getHeaderText() + "\0", print_writers);
	    					break;
	    				}
	    				break;
	    			case "u":
	    				switch (config.getBroadcaster().toUpperCase()) {
	    				case Constants.TRI_SERIES:  case Constants.MT20:
	    					
	    					if(lowerThird.getWhichTeamFlag() != null) {
								CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$Position_With_Graphics$TopLine$LogoAll$Side" + WhichSide + 
										"$img_Logos*TEXTURE*IMAGE SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? 
		    									Constants.TRI_SERIES_LOGO : Constants.MT20_LOGO) + lowerThird.getWhichTeamFlag() + "\0", print_writers);
							}
							

	    					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$Position_With_Graphics$TopLine$Text$Side"+ WhichSide + 
	    							"$img_Text1$select_DataType*FUNCTION*Omo*vis_con SET 1\0", print_writers);
	    					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$TopLine$RightTextBand$TextAll$Side"+ WhichSide + 
	    							"$select_DataType*FUNCTION*Omo*vis_con SET 1\0", print_writers);

							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$Position_With_Graphics$TopLine$Text$Side" + WhichSide 
									+ "$select_DataType$Name$txt_FirstName*GEOM*TEXT SET " + lowerThird.getFirstName() + "\0", print_writers);
							
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$Position_With_Graphics$TopLine$Text$Side" + WhichSide 
									+ "$select_DataType$Name$txt_LastName*GEOM*TEXT SET \0", print_writers);
							
							if(Integer.valueOf(lowerThird.getScoreText().split("-")[1])>=10) {
								CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$TopLine$RightTextBand$TextAll$Side"+ WhichSide + 
		    							"$Score$txt_Runs*GEOM*TEXT SET " + lowerThird.getScoreText().split("-")[0] +"\0", print_writers);
	    					}else {
	    						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$TopLine$RightTextBand$TextAll$Side"+ WhichSide + 
		    							"$Score$txt_Runs*GEOM*TEXT SET " + lowerThird.getScoreText() +"\0", print_writers);
	    					}
							
	    					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$TopLine$RightTextBand$TextAll$Side"+ WhichSide + 
	    							"$Score$txt_Balls*GEOM*TEXT SET " + lowerThird.getBallsFacedText() + " OVERS" + "\0", print_writers);
	    					
	    					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$Position_With_Graphics$TopLine$Text$Side" + WhichSide 
									+ "$select_DataType$txt_Designation*GEOM*TEXT SET " +  lowerThird.getSubTitle() + "\0", print_writers);
	    					
	    					break;
	    				}
	    				
	    				break;
	    			case "Shift_F5": case "Shift_F9":
	    				switch (config.getBroadcaster().toUpperCase()) {
	    				case Constants.TRI_SERIES:  case Constants.MT20:
	    					
	    					if(lowerThird.getWhichTeamFlag() != null) {
								CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$Position_With_Graphics$TopLine$LogoAll$Side" + WhichSide + 
										"$img_Logos*TEXTURE*IMAGE SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? 
		    									Constants.TRI_SERIES_LOGO : Constants.MT20_LOGO) + lowerThird.getWhichTeamFlag() + "\0", print_writers);
							}
							

	    					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$Position_With_Graphics$TopLine$Text$Side"+ WhichSide + 
	    							"$img_Text1$select_DataType*FUNCTION*Omo*vis_con SET 0\0", print_writers);
	    					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$TopLine$RightTextBand$TextAll$Side"+ WhichSide + 
	    							"$select_DataType*FUNCTION*Omo*vis_con SET 1\0", print_writers);

							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$Position_With_Graphics$TopLine$Text$Side" + WhichSide 
									+ "$select_DataType$Name$txt_FirstName*GEOM*TEXT SET " + lowerThird.getFirstName() + "\0", print_writers);
							
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$Position_With_Graphics$TopLine$Text$Side" + WhichSide 
									+ "$select_DataType$Name$txt_LastName*GEOM*TEXT SET " + lowerThird.getSurName() + "\0", print_writers);
							
	    					break;
	    				}
	    				
	    				switch(whatToProcess) {
	    				
	    				case "Shift_F5":
	    					switch (config.getBroadcaster().toUpperCase()) {
	    					case Constants.TRI_SERIES:  case Constants.MT20:
	    						
	    						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$Position_With_Graphics$TopLine$LogoAll$Side" + WhichSide + 
										"$img_Logos*TEXTURE*IMAGE SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? 
		    									Constants.TRI_SERIES_LOGO : Constants.MT20_LOGO) + lowerThird.getWhichTeamFlag() + "\0", print_writers);
							
	    						if(lowerThird.getSubTitle().equalsIgnoreCase("*")) {
									CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$TopLine$RightTextBand$TextAll$Side"+ WhichSide + 
			    							"$Score$txt_Runs*GEOM*TEXT SET " + lowerThird.getScoreText()+"*" +"\0", print_writers);
		    					}else {
		    						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$TopLine$RightTextBand$TextAll$Side"+ WhichSide + 
			    							"$Score$txt_Runs*GEOM*TEXT SET " + lowerThird.getScoreText() +"\0", print_writers);
		    					}
	    						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$TopLine$RightTextBand$TextAll$Side"+ WhichSide + 
		    							"$Score$txt_Balls*GEOM*TEXT SET " + lowerThird.getBallsFacedText() + " BALL" + 
	    								CricketFunctions.Plural(Integer.valueOf(lowerThird.getBallsFacedText())).toUpperCase() + "\0", print_writers);
	    						
	    						break;
	    					}
	    					
	    					break;
	    				case "Shift_F9":
	    					switch (config.getBroadcaster().toUpperCase()) {
	    					case Constants.TRI_SERIES:  case Constants.MT20:
	    						
	    						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$Position_With_Graphics$TopLine$LogoAll$Side" + WhichSide + 
										"$img_Logos*TEXTURE*IMAGE SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? 
		    									Constants.TRI_SERIES_LOGO : Constants.MT20_LOGO) + lowerThird.getWhichTeamFlag() + "\0", print_writers);
							
	    						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$TopLine$RightTextBand$TextAll$Side"+ WhichSide + 
		    							"$Score$txt_Runs*GEOM*TEXT SET " + lowerThird.getScoreText() +"\0", print_writers);
	    					
	    						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$TopLine$RightTextBand$TextAll$Side"+ WhichSide + 
		    							"$Score$txt_Balls*GEOM*TEXT SET " + lowerThird.getBallsFacedText() + " " + lowerThird.getSubTitle() + "\0", print_writers);
	    						break;
	    					}
	    					break;	
	    				}
	    				break;
	    			case "Alt_F12":
	    				switch (config.getBroadcaster().toUpperCase()) {
	    				case Constants.TRI_SERIES:  case Constants.MT20:
	    					if(lowerThird.getWhichTeamFlag() != null) {
								CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$Position_With_Graphics$TopLine$LogoAll$Side" + WhichSide + 
										"$img_Logos*TEXTURE*IMAGE SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? 
		    									Constants.TRI_SERIES_LOGO : Constants.MT20_LOGO) + lowerThird.getWhichTeamFlag() + "\0", print_writers);
							}
							
	    					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$Position_With_Graphics$TopLine$Text$Side"+ WhichSide + 
	    							"$img_Text1$select_DataType*FUNCTION*Omo*vis_con SET 0\0", print_writers);
	    					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$TopLine$RightTextBand$TextAll$Side"+ WhichSide + 
	    							"$select_DataType*FUNCTION*Omo*vis_con SET 1\0", print_writers);

							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$Position_With_Graphics$TopLine$Text$Side" + WhichSide 
									+ "$select_DataType$Name$txt_FirstName*GEOM*TEXT SET " + lowerThird.getHeaderText() + "\0", print_writers);
							
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$Position_With_Graphics$TopLine$Text$Side" + WhichSide 
									+ "$select_DataType$Name$txt_LastName*GEOM*TEXT SET \0", print_writers);
							
							
							if(Integer.valueOf(lowerThird.getScoreText().split("-")[1])>=10) {
								CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$TopLine$RightTextBand$TextAll$Side"+ WhichSide + 
		    							"$Score$txt_Runs*GEOM*TEXT SET " + lowerThird.getScoreText().split("-")[0] +"\0", print_writers);
	    					}else {
	    						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$TopLine$RightTextBand$TextAll$Side"+ WhichSide + 
		    							"$Score$txt_Runs*GEOM*TEXT SET " + lowerThird.getScoreText() +"\0", print_writers);
	    					}
    						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$TopLine$RightTextBand$TextAll$Side"+ WhichSide + 
	    							"$Score$txt_Balls*GEOM*TEXT SET " + lowerThird.getBallsFacedText() + " OVERS" + "\0", print_writers);
    						
	    					break;
	    				}
	    				
	    				break;
	    			case "Shift_E":
	    				switch (config.getBroadcaster().toUpperCase()) {
	    				case Constants.TRI_SERIES:  case Constants.MT20:
	    					if(lowerThird.getWhichTeamFlag() != null) {
								CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$Position_With_Graphics$TopLine$LogoAll$Side" + WhichSide + 
										"$img_Logos*TEXTURE*IMAGE SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? 
		    									Constants.TRI_SERIES_LOGO : Constants.MT20_LOGO) + lowerThird.getWhichTeamFlag() + "\0", print_writers);
							}
							

	    					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$Position_With_Graphics$TopLine$Text$Side"+ WhichSide + 
	    							"$img_Text1$select_DataType*FUNCTION*Omo*vis_con SET 0\0", print_writers);
	    					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$TopLine$RightTextBand$TextAll$Side"+ WhichSide + 
	    							"$select_DataType*FUNCTION*Omo*vis_con SET 0\0", print_writers);

							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$Position_With_Graphics$TopLine$Text$Side" + WhichSide 
									+ "$select_DataType$Name$txt_FirstName*GEOM*TEXT SET " + lowerThird.getHeaderText() + "\0", print_writers);
							
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$Position_With_Graphics$TopLine$Text$Side" + WhichSide 
									+ "$select_DataType$Name$txt_LastName*GEOM*TEXT SET \0", print_writers);
	    					break;
	    				}
	    				
	    				break;
	    			case "d":  case "e":
	    				switch (config.getBroadcaster().toUpperCase()) {
	    				case Constants.TRI_SERIES:  case Constants.MT20:
	    					
	    					if(lowerThird.getWhichTeamFlag() != null) {
								CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$Position_With_Graphics$TopLine$LogoAll$Side" + WhichSide + 
										"$img_Logos*TEXTURE*IMAGE SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? 
		    									Constants.TRI_SERIES_LOGO : Constants.MT20_LOGO) + lowerThird.getWhichTeamFlag() + "\0", print_writers);
							}
							

	    					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$Position_With_Graphics$TopLine$Text$Side"+ WhichSide + 
	    							"$img_Text1$select_DataType*FUNCTION*Omo*vis_con SET 0\0", print_writers);
	    					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$TopLine$RightTextBand$TextAll$Side"+ WhichSide + 
	    							"$select_DataType*FUNCTION*Omo*vis_con SET 0\0", print_writers);

							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$Position_With_Graphics$TopLine$Text$Side" + WhichSide 
									+ "$select_DataType$Name$txt_FirstName*GEOM*TEXT SET " + teamNameAsCity + "\0", print_writers);
							
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$Position_With_Graphics$TopLine$Text$Side" + WhichSide 
									+ "$select_DataType$Name$txt_LastName*GEOM*TEXT SET\0", print_writers);
							
	    					break;
	    				}
	    				break;
	    			case "Control_h":
	    				switch (config.getBroadcaster().toUpperCase()) {	
	    				case Constants.TRI_SERIES:  case Constants.MT20:
	    					
	    					if(lowerThird.getWhichTeamFlag() != null) {
								CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$Position_With_Graphics$TopLine$LogoAll$Side" + WhichSide + 
										"$img_Logos*TEXTURE*IMAGE SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? 
		    									Constants.TRI_SERIES_LOGO : Constants.MT20_LOGO) + lowerThird.getWhichTeamFlag() + "\0", print_writers);
							}
							

	    					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$Position_With_Graphics$TopLine$Text$Side"+ WhichSide + 
	    							"$img_Text1$select_DataType*FUNCTION*Omo*vis_con SET 1\0", print_writers);
	    					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$TopLine$RightTextBand$TextAll$Side"+ WhichSide + 
	    							"$select_DataType*FUNCTION*Omo*vis_con SET 1\0", print_writers);

							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$Position_With_Graphics$TopLine$Text$Side" + WhichSide 
									+ "$select_DataType$Name$txt_FirstName*GEOM*TEXT SET " + lowerThird.getFirstName() + "\0", print_writers);
							
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$Position_With_Graphics$TopLine$Text$Side" + WhichSide 
									+ "$select_DataType$Name$txt_LastName*GEOM*TEXT SET \0", print_writers);
							
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$TopLine$RightTextBand$TextAll$Side"+ WhichSide + 
	    							"$Score$txt_Runs*GEOM*TEXT SET " + lowerThird.getScoreText() +"\0", print_writers);
	    					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$TopLine$RightTextBand$TextAll$Side"+ WhichSide + 
	    							"$Score$txt_Balls*GEOM*TEXT SET " + lowerThird.getBallsFacedText() + " OVERS" + "\0", print_writers);
	    					
	    					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$Position_With_Graphics$TopLine$Text$Side" + WhichSide 
									+ "$select_DataType$txt_Designation*GEOM*TEXT SET " +  lowerThird.getSubTitle() + "\0", print_writers);
	    					
	    					break;
	    				}
	    				break;
	    			case "Alt_Shift_F3":
	    				switch (config.getBroadcaster().toUpperCase()) {	
	    				case Constants.TRI_SERIES:  case Constants.MT20:
	    					
	    					if(lowerThird.getWhichTeamFlag() != null) {
								CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$Position_With_Graphics$TopLine$LogoAll$Side" + WhichSide + 
										"$img_Logos*TEXTURE*IMAGE SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? 
		    									Constants.TRI_SERIES_LOGO : Constants.MT20_LOGO) + lowerThird.getWhichTeamFlag() + "\0", print_writers);
							}
							
	    					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$Position_With_Graphics$TopLine$Text$Side"+ WhichSide + 
	    							"$img_Text1$select_DataType*FUNCTION*Omo*vis_con SET 0\0", print_writers);
	    					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$TopLine$RightTextBand$TextAll$Side"+ WhichSide + 
	    							"$select_DataType*FUNCTION*Omo*vis_con SET 0\0", print_writers);

							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$Position_With_Graphics$TopLine$Text$Side" + WhichSide 
									+ "$select_DataType$Name$txt_FirstName*GEOM*TEXT SET " + lowerThird.getFirstName() + "\0", print_writers);
							
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$Position_With_Graphics$TopLine$Text$Side" + WhichSide 
									+ "$select_DataType$Name$txt_LastName*GEOM*TEXT SET \0", print_writers);
							
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$TopLine$RightTextBand$TextAll$Side"+ WhichSide + 
	    							"$txt_Designation*GEOM*TEXT SET " + lowerThird.getSubTitle() +"\0", print_writers);
	    					
	    					break;
	    				}
	    				break;
	    			case "Control_F3"://Comparison
	                    switch (config.getBroadcaster().toUpperCase()) {
	                    case Constants.TRI_SERIES:  case Constants.MT20:
	                    	
	                    	CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Lt_Comparison$Side0" + WhichSide + "$Team1$LogoGrp$TeamColourAll" + 
									"$img_Base1*TEXTURE*IMAGE SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? 
											Constants.TRI_SERIES_BASE1 : Constants.MT20_BASE1) + lowerThird.getFirstName() + "\0", print_writers);
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Lt_Comparison$Side0" + WhichSide + "$Team1$LogoGrp$TeamColourAll" + 
									"$img_Base2*TEXTURE*IMAGE SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? 
											Constants.TRI_SERIES_BASE2 : Constants.MT20_BASE2) + lowerThird.getFirstName() + "\0", print_writers);
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Lt_Comparison$Side0" + WhichSide + "$DataPart$BottomPart$HomeTeam" + 
									"$img_Base1*TEXTURE*IMAGE SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? 
											Constants.TRI_SERIES_BASE1 : Constants.MT20_BASE1) + lowerThird.getFirstName() + "\0", print_writers);
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Lt_Comparison$Side0" + WhichSide + "$DataPart$BottomPart$HomeTeam" + 
									"$img_Base2*TEXTURE*IMAGE SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? 
											Constants.TRI_SERIES_BASE2 : Constants.MT20_BASE2) + lowerThird.getFirstName() + "\0", print_writers);
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Lt_Comparison$Side0" + WhichSide + "$DataPart$BottomPart$HomeTeam" + 
									"$img_Text2*TEXTURE*IMAGE SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? 
	    									Constants.TRI_SERIES_TEXT2 : Constants.MT20_TEXT2) + lowerThird.getFirstName() + "\0", print_writers);
							
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Lt_Comparison$Side0" + WhichSide + "$Team2$LogoGrp$TeamColourAll" + 
									"$img_Base1*TEXTURE*IMAGE SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? 
											Constants.TRI_SERIES_BASE1 : Constants.MT20_BASE1) + lowerThird.getSurName() + "\0", print_writers);
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Lt_Comparison$Side0" + WhichSide + "$Team2$LogoGrp$TeamColourAll" + 
									"$img_Base2*TEXTURE*IMAGE SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? 
											Constants.TRI_SERIES_BASE2 : Constants.MT20_BASE2) + lowerThird.getSurName() + "\0", print_writers);
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Lt_Comparison$Side0" + WhichSide + "$DataPart$BottomPart$AwayTeam" + 
									"$img_Base1*TEXTURE*IMAGE SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? 
											Constants.TRI_SERIES_BASE1 : Constants.MT20_BASE1) + lowerThird.getSurName() + "\0", print_writers);
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Lt_Comparison$Side0" + WhichSide + "$DataPart$BottomPart$AwayTeam" + 
									"$img_Base2*TEXTURE*IMAGE SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? 
											Constants.TRI_SERIES_BASE2 : Constants.MT20_BASE2) + lowerThird.getSurName() + "\0", print_writers);
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Lt_Comparison$Side0" + WhichSide + "$DataPart$BottomPart$AwayTeam" + 
									"$img_Text2*TEXTURE*IMAGE SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? 
	    									Constants.TRI_SERIES_TEXT2 : Constants.MT20_TEXT2) + lowerThird.getSurName() + "\0", print_writers);
							
							
	                 	   // bowling team1flag
	                 	   CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Lt_Comparison$Side0" + WhichSide + "$Team1$LogoGrp$Logo$Loop$img_Logo*TEXTURE*IMAGE SET " + 
	                 			  (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? 
	    									Constants.TRI_SERIES_LOGO : Constants.MT20_LOGO) + lowerThird.getFirstName() + "\0", print_writers);
	                 	   //bowlingteam name
	                 	   CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Lt_Comparison$Side0" + WhichSide + "$Team1$TeamNameGrp$TeamName$txt_TeamName*GEOM*TEXT SET " + 
	                 			   lowerThird.getLeftText()[0] + "\0", print_writers); 
	                 	  //bating team flag
	                 	   CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Lt_Comparison$Side0" + WhichSide + "$Team2$LogoGrp$Logo$Loop$img_Logo*TEXTURE*IMAGE SET " + 
	                 			  (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? 
	    									Constants.TRI_SERIES_LOGO : Constants.MT20_LOGO) + lowerThird.getSurName() + "\0", print_writers); 
	                 	   CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Lt_Comparison$Side0" + WhichSide + "$Team2$TeamNameGrp$TeamName$txt_TeamName*GEOM*TEXT SET " + 
	                 			   lowerThird.getLeftText()[3] + "\0", print_writers);
	                 	   break;
	                    }
	 					break; 	
	    				// new cases
	              }
	            break; 
	    }

	    return Constants.OK;
	}

	private void PopulateL3rdBaseColor(String whatToProcess, int whichSide) {
		switch (config.getBroadcaster().toUpperCase()) {
        case Constants.TRI_SERIES:  case Constants.MT20:
            switch (whatToProcess) {
            case "Alt_Shift_F3":
            	CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$TopLine$Header_Out$Side" + whichSide + 
            			"$img_Base1*TEXTURE*IMAGE SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? 
								Constants.TRI_SERIES_BASE1 : Constants.MT20_BASE1) + "EVENT" +"\0", print_writers);
            		break;
            	case "Alt_F1": case "Alt_F2":
            		CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$TopLine$Header_Out$Side" + whichSide + 
                			"$img_Base1*TEXTURE*IMAGE SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? 
									Constants.TRI_SERIES_BASE1 : Constants.MT20_BASE1) + l3griff.getWhichTeamFlag() +"\0", print_writers);
                	CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$TopLine$Text$Side" + whichSide + "$img_Text1" + 
							"*TEXTURE*IMAGE SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? 
									Constants.TRI_SERIES_TEXT1 : Constants.MT20_TEXT1) + l3griff.getWhichTeamFlag() +"\0", print_writers);
                	
                	CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$TopLine$RightTextBand$Base$Side" + whichSide + 
							"$img_Base2*TEXTURE*IMAGE SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? 
									Constants.TRI_SERIES_BASE2 : Constants.MT20_BASE2) + l3griff.getWhichTeamFlag() +"\0", print_writers);
                	CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$TopLine$RightTextBand$TextAll$Side" + whichSide + 
							"$img_Text2*TEXTURE*IMAGE SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? 
									Constants.TRI_SERIES_TEXT2 : Constants.MT20_TEXT2) + l3griff.getWhichTeamFlag() +"\0", print_writers);
					
                	break;
                	
                case "F8": case "Alt_F8":
                	CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_NameSuper$TopLine$TeamColour$Side" + whichSide + 
							"$img_Base1*TEXTURE*IMAGE SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? 
									Constants.TRI_SERIES_BASE1 : Constants.MT20_BASE1) + lowerThird.getWhichTeamFlag() +"\0", print_writers);
                	CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_NameSuper$TopLine$TeamColour$Side" + whichSide + 
							"$img_Base2*TEXTURE*IMAGE SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? 
									Constants.TRI_SERIES_BASE2 : Constants.MT20_BASE2) + lowerThird.getWhichTeamFlag() +"\0", print_writers);
                	break;
                case "F10":
                	if(!lowerThird.getWhichSponsor().isEmpty() && !lowerThird.getWhichTeamFlag().isEmpty()) {
                		CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_NameSuper$TopLine$TeamColour$Side" + whichSide + 
    							"$img_Base1*TEXTURE*IMAGE SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? 
    									Constants.TRI_SERIES_BASE1 : Constants.MT20_BASE1) + lowerThird.getWhichTeamFlag() +"\0", print_writers);
                    	CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_NameSuper$TopLine$TeamColour$Side" + whichSide + 
    							"$img_Base2*TEXTURE*IMAGE SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? 
    									Constants.TRI_SERIES_BASE2 : Constants.MT20_BASE2) + lowerThird.getWhichTeamFlag() +"\0", print_writers);
					}else if(!lowerThird.getWhichSponsor().isEmpty() && lowerThird.getWhichTeamFlag().isEmpty()) {
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_NameSuper$TopLine$TeamColour$Side" + whichSide + 
								"$img_Base1*TEXTURE*IMAGE SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? 
										Constants.TRI_SERIES_BASE1 : Constants.MT20_BASE1) + lowerThird.getWhichSponsor() +"\0", print_writers);
	                	CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_NameSuper$TopLine$TeamColour$Side" + whichSide + 
								"$img_Base2*TEXTURE*IMAGE SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? 
										Constants.TRI_SERIES_BASE2 : Constants.MT20_BASE2) + lowerThird.getWhichSponsor() +"\0", print_writers);
					}else if(lowerThird.getWhichSponsor().isEmpty() && !lowerThird.getWhichTeamFlag().isEmpty()) {
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_NameSuper$TopLine$TeamColour$Side" + whichSide + 
								"$img_Base1*TEXTURE*IMAGE SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? 
										Constants.TRI_SERIES_BASE1 : Constants.MT20_BASE1) + lowerThird.getWhichTeamFlag() +"\0", print_writers);
	                	CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_NameSuper$TopLine$TeamColour$Side" + whichSide + 
								"$img_Base2*TEXTURE*IMAGE SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? 
										Constants.TRI_SERIES_BASE2 : Constants.MT20_BASE2) + lowerThird.getWhichTeamFlag() +"\0", print_writers);
					}else {
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_NameSuper$TopLine$TeamColour$Side" + whichSide + 
								"$img_Base1*TEXTURE*IMAGE SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? 
										Constants.TRI_SERIES_BASE1 : Constants.MT20_BASE1) + "EVENT" +"\0", print_writers);
	                	CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_NameSuper$TopLine$TeamColour$Side" + whichSide + 
								"$img_Base2*TEXTURE*IMAGE SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? 
										Constants.TRI_SERIES_BASE2 : Constants.MT20_BASE2) + "EVENT" +"\0", print_writers);
					}
                	break;	
                
                default:
                	CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$TopLine$Header_Out$Side" + whichSide + 
                			"$img_Base1*TEXTURE*IMAGE SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? 
									Constants.TRI_SERIES_BASE1 : Constants.MT20_BASE1) + lowerThird.getWhichTeamFlag() +"\0", print_writers);
                	CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$TopLine$Text$Side" + whichSide + "$img_Text1" + 
							"*TEXTURE*IMAGE SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? 
									Constants.TRI_SERIES_TEXT1 : Constants.MT20_TEXT1) + lowerThird.getWhichTeamFlag() +"\0", print_writers);
                	
                	CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$TopLine$RightTextBand$Base$Side" + whichSide + 
							"$img_Base2*TEXTURE*IMAGE SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? 
									Constants.TRI_SERIES_BASE2 : Constants.MT20_BASE2) + lowerThird.getWhichTeamFlag() +"\0", print_writers);
                	CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$TopLine$RightTextBand$TextAll$Side" + whichSide + 
							"$img_Text2*TEXTURE*IMAGE SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? 
									Constants.TRI_SERIES_TEXT2 : Constants.MT20_TEXT2) + lowerThird.getWhichTeamFlag() +"\0", print_writers);
					
                	break;	
            }
            break;
		}
	}
	
	public String PopulateL3rdBody(int whichSide, String whatToProcess) {
		if(whichSide == 1) {
			containerName = "$Change_Out";
		}else if(whichSide == 2) {
			containerName = "$Change_In";
		}
		switch (config.getBroadcaster().toUpperCase()) {
			case Constants.BAN_AFG_SERIES: case Constants.ACC:
				switch (whatToProcess) {
				case "Alt_d":
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$All$DataAll$Side" + whichSide 
							+ "$PlayerNameSuper$TopGrp$txt_Header1*GEOM*TEXT SET " + "DLS PAR SCORE" + "\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$All$DataAll$Side" + whichSide 
							+ "$PlayerNameSuper$TopGrp$txt_Header2*GEOM*TEXT SET \0", print_writers);
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$All$DataAll$Side" + whichSide 
							+ "$PlayerNameSuper$BottomGrp$txt_Data1*GEOM*TEXT SET AFTER " + lowerThird.getScoreText() + " OVERS - "
							+ lowerThird.getLeftText()[0] + "\0", print_writers);
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$All$DataAll$Side" + whichSide 
							+ "$PlayerNameSuper$BottomGrp$txt_Data2*GEOM*TEXT SET " + lowerThird.getLeftText()[1].toUpperCase() + "\0", print_writers);
					break;
				case "l":
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$All$DataAll$Side" + whichSide
    						+ "$Select$PlayerProfile$BottomGrp$RestDataGrp$RestData$Data*FUNCTION*Omo*vis_con SET 4\0",print_writers);
					
					for(int iStat = 0; iStat < lowerThird.getTitlesText().length; iStat++) {
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$All$DataAll$Side" + whichSide
	    						+ "$Select$PlayerProfile$BottomGrp$RestDataGrp$RestData$Data$" + (iStat + 1) + "$txt_Data1A*GEOM*TEXT SET "
	    						+ lowerThird.getTitlesText()[iStat] + "\0",print_writers);
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$All$DataAll$Side" + whichSide
	    						+ "$Select$PlayerProfile$BottomGrp$RestDataGrp$RestData$Data$" + (iStat + 1) + "$txt_Data2A*GEOM*TEXT SET "
	    						+ lowerThird.getStatsText()[iStat] + "\0",print_writers);
					}
					break;
				case "Alt_Shift_F3":
    				
    				switch (config.getBroadcaster().toUpperCase()) {
    				case Constants.ACC:
    					
    					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$All$DataAll$Side"+ whichSide +"$Select$PhaseWise$BottomGrp$RestData$1" 
								 + "$txt_Data1A*GEOM*TEXT SET " + inning.getBatting_team().getTeamName1() + " \0", print_writers);
    					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$All$DataAll$Side"+ whichSide +"$Select$PhaseWise$BottomGrp$RestData$1" 
								 + "$txt_Data2A*GEOM*TEXT SET " + inning2.getBatting_team().getTeamName1() + " \0", print_writers);
    					
    					for(int i=0; i<lowerThird.getTitlesText().length; i++) {
    						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$All$DataAll$Side"+ whichSide +"$Select$PhaseWise$BottomGrp$txt_Data" + (i + 1) + "" 
    								 + "*GEOM*TEXT SET " + lowerThird.getTitlesText()[i] + " \0", print_writers);
    					}
    					
    					//1st Phase
    					if(Integer.valueOf(lowerThird.getStatsText()[0].split("-")[0].split(",")[0]) == 0 && Integer.valueOf(lowerThird.getStatsText()[0].split("-")[1].split(",")[0]) == 0) {
    						if(Float.valueOf(CricketFunctions.OverBalls(inning.getTotalOvers(), inning.getTotalBalls())) > 0.0) {
    							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$All$DataAll$Side"+ whichSide +"$Select$PhaseWise$BottomGrp$RestData$2" 
    									 + "$txt_Data1A*GEOM*TEXT SET " + "0-0" + " \0", print_writers);
    						}else {
    							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$All$DataAll$Side"+ whichSide +"$Select$PhaseWise$BottomGrp$RestData$2" 
   									 + "$txt_Data1A*GEOM*TEXT SET " + "-" + " \0", print_writers);
    						}
    					}else {
    						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$All$DataAll$Side"+ whichSide +"$Select$PhaseWise$BottomGrp$RestData$2" 
									 + "$txt_Data1A*GEOM*TEXT SET " + lowerThird.getStatsText()[0].split("-")[0].split(",")[0] + "-" + 
	    								lowerThird.getStatsText()[0].split("-")[1].split(",")[0] + " \0", print_writers);
    					}
    					
    					//2nd phase
    					if(Integer.valueOf(lowerThird.getStatsText()[1].split("-")[0].split(",")[0]) == 0 && Integer.valueOf(lowerThird.getStatsText()[1].split("-")[1].split(",")[0]) == 0) {
    						if(Float.valueOf(CricketFunctions.OverBalls(inning.getTotalOvers(), inning.getTotalBalls())) > 6.0) {
    							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$All$DataAll$Side"+ whichSide +"$Select$PhaseWise$BottomGrp$RestData$3" 
   									 + "$txt_Data1A*GEOM*TEXT SET " + "0-0" + " \0", print_writers);
    						}else {
    							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$All$DataAll$Side"+ whichSide +"$Select$PhaseWise$BottomGrp$RestData$3" 
      									 + "$txt_Data1A*GEOM*TEXT SET " + "-" + " \0", print_writers);
    						}
    					}else {
    						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$All$DataAll$Side"+ whichSide +"$Select$PhaseWise$BottomGrp$RestData$3" 
  									 + "$txt_Data1A*GEOM*TEXT SET " + lowerThird.getStatsText()[1].split("-")[0].split(",")[0] + "-" + 
     								lowerThird.getStatsText()[1].split("-")[1].split(",")[0] + " \0", print_writers);
    					}
    					
    					//3rd Phase
    					if(Integer.valueOf(lowerThird.getStatsText()[2].split("-")[0].split(",")[0]) == 0 && Integer.valueOf(lowerThird.getStatsText()[2].split("-")[1].split(",")[0]) == 0) {
    						if(Float.valueOf(CricketFunctions.OverBalls(inning.getTotalOvers(), inning.getTotalBalls())) > 15.0) {
    							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$All$DataAll$Side"+ whichSide +"$Select$PhaseWise$BottomGrp$RestData$4" 
      									 + "$txt_Data1A*GEOM*TEXT SET " + "0-0" + " \0", print_writers);
    						}else {
    							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$All$DataAll$Side"+ whichSide +"$Select$PhaseWise$BottomGrp$RestData$4" 
      									 + "$txt_Data1A*GEOM*TEXT SET " + "-" + " \0", print_writers);
    						}
    					}else {
    						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$All$DataAll$Side"+ whichSide +"$Select$PhaseWise$BottomGrp$RestData$4" 
  									 + "$txt_Data1A*GEOM*TEXT SET " + lowerThird.getStatsText()[2].split("-")[0].split(",")[0] + "-" + 
     								lowerThird.getStatsText()[2].split("-")[1].split(",")[0] + " \0", print_writers);
    					}
    					
    					
    					
    					// 2 Team
    					
    					//1st Phase
    					if(Integer.valueOf(lowerThird.getStatsText()[0].split("-")[0].split(",")[1]) == 0 && Integer.valueOf(lowerThird.getStatsText()[0].split("-")[1].split(",")[1]) == 0) {
    						if(Float.valueOf(CricketFunctions.OverBalls(inning2.getTotalOvers(), inning2.getTotalBalls())) > 0.0) {
    							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$All$DataAll$Side"+ whichSide +"$Select$PhaseWise$BottomGrp$RestData$2" 
     									 + "$txt_Data2A*GEOM*TEXT SET " + "0-0" + " \0", print_writers);
    						}else {
    							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$All$DataAll$Side"+ whichSide +"$Select$PhaseWise$BottomGrp$RestData$2" 
    									 + "$txt_Data2A*GEOM*TEXT SET " + "-" + " \0", print_writers);
    						}
    					}else {
    						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$All$DataAll$Side"+ whichSide +"$Select$PhaseWise$BottomGrp$RestData$2" 
									 + "$txt_Data2A*GEOM*TEXT SET " + lowerThird.getStatsText()[0].split("-")[0].split(",")[1] + "-" + 
	    								lowerThird.getStatsText()[0].split("-")[1].split(",")[1] + " \0", print_writers);
    					}
    					
    					//2nd phase
    					if(Integer.valueOf(lowerThird.getStatsText()[1].split("-")[0].split(",")[1]) == 0 && Integer.valueOf(lowerThird.getStatsText()[1].split("-")[1].split(",")[1]) == 0) {
    						if(Float.valueOf(CricketFunctions.OverBalls(inning2.getTotalOvers(), inning2.getTotalBalls())) > 6.0) {
    							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$All$DataAll$Side"+ whichSide +"$Select$PhaseWise$BottomGrp$RestData$3" 
    									 + "$txt_Data2A*GEOM*TEXT SET " + "0-0" + " \0", print_writers);
    						}else {
    							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$All$DataAll$Side"+ whichSide +"$Select$PhaseWise$BottomGrp$RestData$3" 
   									 + "$txt_Data2A*GEOM*TEXT SET " + "-" + " \0", print_writers);
    						}
    					}else {
    						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$All$DataAll$Side"+ whichSide +"$Select$PhaseWise$BottomGrp$RestData$3" 
									 + "$txt_Data2A*GEOM*TEXT SET " + lowerThird.getStatsText()[1].split("-")[0].split(",")[1] + "-" + 
	    								lowerThird.getStatsText()[1].split("-")[1].split(",")[1] + " \0", print_writers);
    					}
    					
    					//3rd Phase
    					if(Integer.valueOf(lowerThird.getStatsText()[2].split("-")[0].split(",")[1]) == 0 && Integer.valueOf(lowerThird.getStatsText()[2].split("-")[1].split(",")[1]) == 0) {
    						if(Float.valueOf(CricketFunctions.OverBalls(inning2.getTotalOvers(), inning2.getTotalBalls())) > 15.0) {
    							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$All$DataAll$Side"+ whichSide +"$Select$PhaseWise$BottomGrp$RestData$4" 
   									 + "$txt_Data2A*GEOM*TEXT SET " + "0-0" + " \0", print_writers);
    						}else {
    							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$All$DataAll$Side"+ whichSide +"$Select$PhaseWise$BottomGrp$RestData$4" 
      									 + "$txt_Data2A*GEOM*TEXT SET " + "-" + " \0", print_writers);
    						}
    					}else {
    						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$All$DataAll$Side"+ whichSide +"$Select$PhaseWise$BottomGrp$RestData$4" 
  									 + "$txt_Data2A*GEOM*TEXT SET " + lowerThird.getStatsText()[2].split("-")[0].split(",")[1] + "-" + 
     								lowerThird.getStatsText()[2].split("-")[1].split(",")[1] + " \0", print_writers);
    					}
    					break;
    				}
    				break;
				case "Control_Shift_B":
					int rowId = 0;
    				int position = 0,inAtPositionCount = inning.getTotalWickets();
    				
    				for(BattingCard bc : inning.getBattingCard()) {
    					switch (bc.getStatus()) {
    					case CricketUtil.NOT_OUT:
    						inAtPositionCount++;
    						break;
    					}
    					
    				}
    				
    				for(BattingCard bc : inning.getBattingCard()) {
    					position++;
    					if(rowId>=6) break;
    					switch (bc.getStatus()) {
    					case CricketUtil.STILL_TO_BAT:
    						if(bc.getHowOut() != null && !bc.getHowOut().equalsIgnoreCase(CricketUtil.RETIRED_HURT)) continue;
    						rowId++;
    						
    						inAtPositionCount++;
    						switch (config.getBroadcaster().toUpperCase()) {
    						case Constants.ACC:
    							if(config.getPrimaryIpAddress().equalsIgnoreCase(Constants.LOCALHOST)) {
  									CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$All$DataAll$Side" + whichSide + "$Select$NextToBat$BottomGrp$"
  											+ "Player" + rowId + "$img_Player*TEXTURE*IMAGE SET " + Constants.ACC_LOCAL_PHOTO_PATH + team.getTeamBadge() + "\\\\" 
  											+ Constants.CENTER + "\\\\" + bc.getPlayer().getPhoto() + CricketUtil.PNG_EXTENSION + " \0",print_writers);
  	  	    					}else {
  	  	    						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$All$DataAll$Side" + whichSide + "$Select$NextToBat$BottomGrp$"
  	  	    								+ "Player" + rowId + "$img_Player*TEXTURE*IMAGE SET " + "\\\\" + config.getPrimaryIpAddress() + "\\\\" + Constants.ACC_PHOTO_PATH 
  	  	    								+ "\\\\" + team.getTeamBadge() + "\\\\" + Constants.CENTER + "\\\\" + bc.getPlayer().getPhoto() + CricketUtil.PNG_EXTENSION + " \0",print_writers);
  	  	    					}
    							break;
    						case Constants.BAN_AFG_SERIES:
    							
    							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$All$BaseAll$Side" + whichSide + "$Select$NextToBaat$"
										+ "img_Base2*TEXTURE*IMAGE SET " + Constants.BAN_AFG_SERIES_BASE2 + team.getTeamBadge() + " \0",print_writers);
							
    							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$All$DataAll$Side" + whichSide + "$Select$NextToBat$BottomGrp$"
											+ "Player" + rowId + "$img_Base2*TEXTURE*IMAGE SET " + Constants.BAN_AFG_SERIES_BASE2 + team.getTeamBadge() + " \0",print_writers);
    							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$All$DataAll$Side" + whichSide + "$Select$NextToBat$BottomGrp$"
										+ "Player" + rowId + "$img_Text*TEXTURE*IMAGE SET " + Constants.BAN_AFG_SERIES_TEXT2 + team.getTeamBadge() + " \0",print_writers);
							
    							
    							if(config.getPrimaryIpAddress().equalsIgnoreCase(Constants.LOCALHOST)) {
    								CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$All$DataAll$Side" + whichSide + "$Select$NextToBat$BottomGrp$"
  											+ "Player" + rowId + "$img_Player*TEXTURE*IMAGE SET " + Constants.BAN_AFG_SERIES_LOCAL_PHOTO_PATH + team.getTeamBadge() + "\\\\" 
  											+ Constants.CENTER + "\\\\" + bc.getPlayer().getPhoto() + CricketUtil.PNG_EXTENSION + " \0",print_writers);
  	  	    					}else {
  	  	    					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$All$DataAll$Side" + whichSide + "$Select$NextToBat$BottomGrp$"
	  	    								+ "Player" + rowId + "$img_Player*TEXTURE*IMAGE SET " + "\\\\" + config.getPrimaryIpAddress() + "\\\\" + Constants.BAN_AFG_SERIES_PHOTO_PATH 
	  	    								+ "\\\\" + team.getTeamBadge() + "\\\\" + Constants.CENTER + "\\\\" + bc.getPlayer().getPhoto() + CricketUtil.PNG_EXTENSION + " \0",print_writers);
  	  	    					}
    							break;
    						}
    						
    						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$All$DataAll$Side"+ whichSide +"$Select$NextToBat$BottomGrp$Player" 
    								+ rowId + "$PlayerData$img_Text$Data1*GEOM*TEXT SET " + bc.getPlayer().getTicker_name() + " \0", print_writers);
    						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$All$DataAll$Side"+ whichSide +"$Select$NextToBat$BottomGrp$Player"
    								+ rowId + "$PlayerData$Data0*GEOM*TEXT SET \0", print_writers);
    						
    						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$All$DataAll$Side"+ whichSide +"$Select$NextToBat$BottomGrp$Player"
    								+ rowId + "$PlayerData$Data2*GEOM*TEXT SET " + "IN AT" + "\0", print_writers);
    						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$All$DataAll$Side"+ whichSide +"$Select$NextToBat$BottomGrp$Player"
    								+ rowId + "$PlayerData$Data3*GEOM*TEXT SET " + inAtPositionCount + "\0", print_writers);
    						break;
    					}
    				}
					break;
				case "Control_Shift_O":					
					int row_id = 0;
  	    			if(lowerThird.getHeaderText().equalsIgnoreCase("ROLES")) {
  	    				
  	    				Collections.sort(inning.getBattingCard());
  	    				for (Player bc : PlayersList) {
  	    					row_id = row_id + 1;
  	    					
  	    					switch (config.getBroadcaster().toUpperCase()) {
  	    					case Constants.BAN_AFG_SERIES:
  	    						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$All$DataAll$Side" + whichSide + "$Select$LineUp$BottomGrp$"
  	    								+ "LineUp_ALL$Player" + row_id + "$Dataall$img_Base2*TEXTURE*IMAGE SET " + Constants.BAN_AFG_SERIES_BASE2 + team.getTeamBadge()
  												+ " \0",print_writers);
  								
  								if(config.getPrimaryIpAddress().equalsIgnoreCase(Constants.LOCALHOST)) {
  									CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$All$DataAll$Side" + whichSide + "$Select$LineUp$BottomGrp$"
  											+ "LineUp_ALL$Player" + row_id + "$Dataall$img_Player*TEXTURE*IMAGE SET " + Constants.BAN_AFG_SERIES_LOCAL_PHOTO_PATH
  											+ team.getTeamName4() + "\\\\" + Constants.CENTER + "\\\\" + bc.getPhoto() + CricketUtil.PNG_EXTENSION + " \0",print_writers);
  	  	    					}else {
  	  	    						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$All$DataAll$Side" + whichSide + "$Select$LineUp$BottomGrp$"
  	  	    								+ "LineUp_ALL$Player" + row_id + "$Dataall$img_Player*TEXTURE*IMAGE SET " + "\\\\" + config.getPrimaryIpAddress() + "\\\\" 
  	  	    								+ Constants.BAN_AFG_SERIES_PHOTO_PATH + "\\\\" + team.getTeamName4() + "\\\\" + Constants.CENTER + "\\\\"
  											+ bc.getPhoto() + CricketUtil.PNG_EXTENSION + " \0",print_writers);
  	  	    					}
  	    						break;
  	    					default:
  	    						if(config.getPrimaryIpAddress().equalsIgnoreCase(Constants.LOCALHOST)) {
  									CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$All$DataAll$Side" + whichSide + "$Select$LineUp$BottomGrp$"
  											+ "LineUp_ALL$Player" + row_id + "$Dataall$img_Player*TEXTURE*IMAGE SET " + Constants.ACC_LOCAL_PHOTO_PATH + team.getTeamBadge() 
  											+ "\\\\" + Constants.CENTER + "\\\\" + bc.getPhoto() + CricketUtil.PNG_EXTENSION + " \0",print_writers);
  	  	    					}else {
  	  	    						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$All$DataAll$Side" + whichSide + "$Select$LineUp$BottomGrp$"
  	  	    								+ "LineUp_ALL$Player" + row_id + "$Dataall$img_Player*TEXTURE*IMAGE SET " + "\\\\" + config.getPrimaryIpAddress() + "\\\\" 
  	  	    								+ Constants.ACC_PHOTO_PATH + "\\\\" + team.getTeamBadge() + "\\\\" + Constants.CENTER + "\\\\"
  											+ bc.getPhoto() + CricketUtil.PNG_EXTENSION + " \0",print_writers);
  	  	    					}
  	    						break;
  	    					}
  	    					
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$All$DataAll$Side" + whichSide + "$Select$LineUp$BottomGrp$LineUp_ALL$"
									+ "Player" + row_id + "$Dataall$Details$txt_Name*GEOM*TEXT SET " + bc.getTicker_name() + " \0",print_writers);
							
							if(bc.getRole().equalsIgnoreCase(CricketUtil.BATSMAN) || bc.getRole().equalsIgnoreCase("BAT/KEEPER")) {
								CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$All$DataAll$Side" + whichSide + "$Select$LineUp$BottomGrp$LineUp_ALL"
										+ "$Player" + row_id + "$Dataall$txt_Role*GEOM*TEXT SET " + "BATTER" + " \0",print_writers);
							}
							else if(bc.getRole().equalsIgnoreCase(CricketUtil.BOWLER)) {
								CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$All$DataAll$Side" + whichSide + "$Select$LineUp$BottomGrp$LineUp_ALL$"
										+ "Player" + row_id + "$Dataall$txt_Role*GEOM*TEXT SET " + CricketFunctions.getBowlerType(bc.getBowlingStyle()).replace("PACE", "SEAM") 
										+ " \0",print_writers);
							}
							else if(bc.getRole().equalsIgnoreCase("ALL-ROUNDER")) {
								CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$All$DataAll$Side" + whichSide + "$Select$LineUp$BottomGrp$LineUp_ALL"
										+ "$Player" + row_id + "$Dataall$txt_Role*GEOM*TEXT SET " + "BAT/" +CricketFunctions.getBowlerType(bc.getBowlingStyle()).replace("PACE", 
												"SEAM") + " \0",print_writers);
							}

  	    					if(bc.getCaptainWicketKeeper() != null) {
  	    						if (bc.getCaptainWicketKeeper().equalsIgnoreCase(CricketUtil.CAPTAIN)) {
  	    							CricketFunctions.DoadWriteCommandToAllViz(
  	  									"-1 RENDERER*FRONT_LAYER*TREE*$LT$All$DataAll$Side" + whichSide + "$Select$LineUp$BottomGrp$LineUp_ALL$Player"
  	  											+ row_id + "$Dataall$Details$SelectCaptain*FUNCTION*Omo*vis_con SET "
  	  											+ "1" + " \0",print_writers);
  	    							
  	    						} else if (bc.getCaptainWicketKeeper().equalsIgnoreCase("CAPTAIN_WICKET_KEEPER")) {
  	    							CricketFunctions.DoadWriteCommandToAllViz(
  	  									"-1 RENDERER*FRONT_LAYER*TREE*$LT$All$DataAll$Side" + whichSide + "$Select$LineUp$BottomGrp$LineUp_ALL$Player"
  	  											+ row_id
  	  											+ "$Dataall$Details$SelectCaptain*FUNCTION*Omo*vis_con SET "
  	  											+ "1" + " \0",print_writers);
  	    							CricketFunctions.DoadWriteCommandToAllViz(
  											"-1 RENDERER*FRONT_LAYER*TREE*$LT$All$DataAll$Side" + whichSide + "$Select$LineUp$BottomGrp$LineUp_ALL$Player"
  													+ row_id + "$Dataall$txt_Role*GEOM*TEXT SET "
  													+ "KEEPER" + " \0",print_writers);
  	    						} else if (bc.getCaptainWicketKeeper().equalsIgnoreCase(CricketUtil.WICKET_KEEPER)) {
  	    							CricketFunctions.DoadWriteCommandToAllViz(
  	  									"-1 RENDERER*FRONT_LAYER*TREE*$LT$All$DataAll$Side" + whichSide + "$Select$LineUp$BottomGrp$LineUp_ALL$Player"
  	  											+ row_id
  	  											+ "$Dataall$Details$SelectCaptain*FUNCTION*Omo*vis_con SET "
  	  											+ "0" + " \0",print_writers);
  	    							CricketFunctions.DoadWriteCommandToAllViz(
  											"-1 RENDERER*FRONT_LAYER*TREE*$LT$All$DataAll$Side" + whichSide + "$Select$LineUp$BottomGrp$LineUp_ALL$Player"
  													+ row_id + "$Dataall$txt_Role*GEOM*TEXT SET "
  													+ "KEEPER" + " \0",print_writers);
  	    						}
  	    					}else {
  	    						CricketFunctions.DoadWriteCommandToAllViz(
  	  									"-1 RENDERER*FRONT_LAYER*TREE*$LT$All$DataAll$Side" + whichSide + "$Select$LineUp$BottomGrp$LineUp_ALL$Player"
  	  											+ row_id
  	  											+ "$Dataall$Details$SelectCaptain*FUNCTION*Omo*vis_con SET "
  	  											+ "0" + " \0",print_writers);
  	    					}
  	    				}
  	    			}else if(lowerThird.getHeaderText().equalsIgnoreCase("BATTING_CARD")) {
  	    				
  	    				int inAtPosition = inning.getTotalWickets();
  	    				
  	    				for(BattingCard bc : battingCardList) {
  	    					switch (bc.getStatus()) {
  	    					case CricketUtil.NOT_OUT:
  	    						inAtPosition++;
  	    						break;
  	    					}
  	    				}
  	    				
  	    				Collections.sort(battingCardList);
  	    				for (BattingCard bc : battingCardList) {
  	    					row_id = row_id + 1;
  	    					
//  	    					if(battingCardList.size() >= 12) {
////  	    						containerNam1 = "$Data_Grp";
////  	    						offset = "133.0";
////  	    						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*FRONT_LAYER*TREE*$LT_PlayingXI$Data_All$Side"+ WhichSide + containerNam1 +
////  	    								"$" + row_id + "$Select*FUNCTION*Omo*vis_con SET 1\0", print_writers);
//  	    					}else {
//  	    						containerNam1 = "$Data_Grp";
//  	    						offset = "146.0";
//  	    						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*FRONT_LAYER*TREE*$LT_PlayingXI$Data_All$Side"+ WhichSide + containerNam1 +
//  	    								"$" + row_id + "$Select*FUNCTION*Omo*vis_con SET 0\0", print_writers);
//  	    					}
  	    					
//  	    					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*FRONT_LAYER*TREE*$LT_PlayingXI$Data_All$Side"+ WhichSide + containerNam1 
//  	    							+ "*FUNCTION*Grid*col_offset SET " + offset + "\0", print_writers);
//  	    					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*FRONT_LAYER*TREE*$LT_PlayingXI$Data_All$Side"+ WhichSide + containerNam1 
//  	    							+ "*FUNCTION*Grid*num_col SET " + row_id + "\0", print_writers);

//  	    					if(!CricketFunctions.checkBatAndBallImpactInOutPlayer(match.getEventFile().getEvents(), bc.getPlayerId()).isEmpty()) {
//  	    						switch(CricketFunctions.checkBatAndBallImpactInOutPlayer(match.getEventFile().getEvents(), bc.getPlayerId())) {
//  	    						case "IMP_IN":
//  	    							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*FRONT_LAYER*TREE*$LT_PlayingXI$Data_All$Side" + 
//  	    									WhichSide + "$" + row_id + "$Impact_Sub*FUNCTION*Omo*vis_con SET 1\0", print_writers);
//  	    							break;
//  	    						case "IMP_OUT":
//  	    							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*FRONT_LAYER*TREE*$LT_PlayingXI$Data_All" +
//  	    									"$Side" + WhichSide + "$" + row_id + "$Impact_Sub*FUNCTION*Omo*vis_con SET 2\0", print_writers);
//  	    							break;
//  	    						case "CON_IN":
//  	    							break;
//  	    						case "CON_OUT":
//  	    							break;
//  	    						}
//  	    					}else {
//  	    						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*FRONT_LAYER*TREE*$LT_PlayingXI$Data_All" +
//  	    								"$Side" + WhichSide + "$" + row_id + "$Impact_Sub*FUNCTION*Omo*vis_con SET 0\0", print_writers);
//  	    					}
  	    					
//  	    					CricketFunctions.DoadWriteCommandToAllViz(
//									"-1 RENDERER*FRONT_LAYER*TREE*$LT$All$DataAll$Side" + whichSide + "$Select$LineUp$BottomGrp$RestDataGrp$LineUp_ALL$Player"
//											+ row_id + "$Dataall$Data$all$img_Text1*TEXTURE*IMAGE SET " + Constants.BAN_AFG_SERIES_TEXT1
//											+ lowerThird.getWhichTeamFlag() + " \0",print_writers);
//							CricketFunctions.DoadWriteCommandToAllViz(
//									"-1 RENDERER*FRONT_LAYER*TREE*$LT$All$DataAll$Side" + whichSide + "$Select$LineUp$BottomGrp$RestDataGrp$LineUp_ALL$Player"
//											+ row_id + "$Dataall$Data$all$img_Base1*TEXTURE*IMAGE SET " + Constants.BAN_AFG_SERIES_BASE1
//											+ lowerThird.getWhichTeamFlag() + " \0",print_writers);
//							CricketFunctions.DoadWriteCommandToAllViz(
//									"-1 RENDERER*FRONT_LAYER*TREE*$LT$All$DataAll$Side" + whichSide + "$Select$LineUp$BottomGrp$RestDataGrp$LineUp_ALL$Player"
//											+ row_id + "$Dataall$img_Base1*TEXTURE*IMAGE SET " + Constants.BAN_AFG_SERIES_BASE1
//											+ lowerThird.getWhichTeamFlag() + " \0",print_writers);
  	    					
  	    					switch (config.getBroadcaster().toUpperCase()) {
  	    					case Constants.BAN_AFG_SERIES:
  	    						CricketFunctions.DoadWriteCommandToAllViz(
  										"-1 RENDERER*FRONT_LAYER*TREE*$LT$All$DataAll$Side" + whichSide + "$Select$LineUp$BottomGrp$LineUp_ALL$Player"
  												+ row_id + "$Dataall$img_Base2*TEXTURE*IMAGE SET "
  												+ Constants.BAN_AFG_SERIES_BASE2 + team.getTeamBadge()
  												+ " \0",print_writers);
  								
  								CricketFunctions.DoadWriteCommandToAllViz(
  										"-1 RENDERER*FRONT_LAYER*TREE*$LT$All$DataAll$Side" + whichSide + "$Select$LineUp$BottomGrp$LineUp_ALL$Player"
  												+ row_id + "$Dataall$Details$txt_Name*GEOM*TEXT SET "
  												+ bc.getPlayer().getTicker_name() + " \0",print_writers);
  								
  								if (config.getPrimaryIpAddress().equalsIgnoreCase("LOCALHOST")) {
  									CricketFunctions.DoadWriteCommandToAllViz(
  											"-1 RENDERER*FRONT_LAYER*TREE*$LT$All$DataAll$Side" + whichSide + "$Select$LineUp$BottomGrp$LineUp_ALL$Player"
  													+ row_id + "$Dataall$img_Player*TEXTURE*IMAGE SET " + Constants.BAN_AFG_SERIES_LOCAL_PHOTO_PATH + "\\\\" + 
  													team.getTeamName4() + "\\\\" + Constants.CENTER + "\\\\" + bc.getPlayer().getPhoto() + 
  													CricketUtil.PNG_EXTENSION + " \0",print_writers);
  								} else {
  									CricketFunctions.DoadWriteCommandToAllViz(
  											"-1 RENDERER*FRONT_LAYER*TREE*$LT$All$DataAll$Side" + whichSide + "$Select$LineUp$BottomGrp$LineUp_ALL$Player"
  													+ row_id + "$Dataall$img_Player*TEXTURE*IMAGE SET " + "\\\\" + config.getPrimaryIpAddress() + "\\\\" + 
  													Constants.BAN_AFG_SERIES_PHOTO_PATH + "\\\\" + team.getTeamName4() + "\\\\" + Constants.CENTER + "\\\\" + 
  													bc.getPlayer().getPhoto() + CricketUtil.PNG_EXTENSION + " \0",print_writers);
  								}
  	    						break;
  	    					default:
  	    						if(config.getPrimaryIpAddress().equalsIgnoreCase(Constants.LOCALHOST)) {
  									CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$All$DataAll$Side" + whichSide + "$Select$LineUp$BottomGrp$"
  											+ "LineUp_ALL$Player" + row_id + "$Dataall$img_Player*TEXTURE*IMAGE SET " + Constants.ACC_LOCAL_PHOTO_PATH + team.getTeamBadge() 
  											+ "\\\\" + Constants.CENTER + "\\\\" + bc.getPlayer().getPhoto() + CricketUtil.PNG_EXTENSION + " \0",print_writers);
  	  	    					}else {
  	  	    						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$All$DataAll$Side" + whichSide + "$Select$LineUp$BottomGrp$"
  	  	    								+ "LineUp_ALL$Player" + row_id + "$Dataall$img_Player*TEXTURE*IMAGE SET " + "\\\\" + config.getPrimaryIpAddress() + "\\\\" 
  	  	    								+ Constants.ACC_PHOTO_PATH + "\\\\" + team.getTeamBadge() + "\\\\" + Constants.CENTER + "\\\\"
  											+ bc.getPlayer().getPhoto() + CricketUtil.PNG_EXTENSION + " \0",print_writers);
  	  	    					}
  	    						break;
  	    					}
  	    					
							CricketFunctions.DoadWriteCommandToAllViz(
									"-1 RENDERER*FRONT_LAYER*TREE*$LT$All$DataAll$Side" + whichSide + "$Select$LineUp$BottomGrp$LineUp_ALL$Player"
											+ row_id + "$Dataall$Details$txt_Name*GEOM*TEXT SET "
											+ bc.getPlayer().getTicker_name() + " \0",print_writers);
							
  	    					if(bc.getStatus().equalsIgnoreCase(CricketUtil.STILL_TO_BAT)) {
  	    						inAtPosition++;
  	    						if(bc.getHowOut() != null) {
  	    							if(bc.getHowOut().equalsIgnoreCase(CricketUtil.RETIRED_HURT)) {
  	    								CricketFunctions.DoadWriteCommandToAllViz(
    											"-1 RENDERER*FRONT_LAYER*TREE*$LT$All$DataAll$Side" + whichSide + "$Select$LineUp$BottomGrp$LineUp_ALL$Player"
    													+ row_id + "$Dataall$txt_Role*GEOM*TEXT SET "
    													+ bc.getRuns() + " (" + bc.getBalls() + ")" + " \0",print_writers);
  	    							}
  	    						}else {
  	    							
  	    							if (inning.getInningStatus().equalsIgnoreCase(CricketUtil.START)) {
  	    								CricketFunctions.DoadWriteCommandToAllViz(
    											"-1 RENDERER*FRONT_LAYER*TREE*$LT$All$DataAll$Side" + whichSide + "$Select$LineUp$BottomGrp$LineUp_ALL$Player"
    													+ row_id + "$Dataall$txt_Role*GEOM*TEXT SET IN AT "
    													+ row_id + " \0",print_writers);
  	    							} else if (inning.getInningStatus().equalsIgnoreCase(CricketUtil.PAUSE)) {
  	    								CricketFunctions.DoadWriteCommandToAllViz(
    											"-1 RENDERER*FRONT_LAYER*TREE*$LT$All$DataAll$Side" + whichSide + "$Select$LineUp$BottomGrp$LineUp_ALL$Player"
    													+ row_id + "$Dataall$txt_Role*GEOM*TEXT SET DNB \0",print_writers);
  	    							}
  	    						}
  	    					}else if(bc.getStatus().equalsIgnoreCase(CricketUtil.NOT_OUT)) {
  	    						CricketFunctions.DoadWriteCommandToAllViz(
										"-1 RENDERER*FRONT_LAYER*TREE*$LT$All$DataAll$Side" + whichSide + "$Select$LineUp$BottomGrp$LineUp_ALL$Player"
												+ row_id + "$Dataall$txt_Role*GEOM*TEXT SET "
												+ bc.getRuns() + "* (" + bc.getBalls() + ")" + " \0",print_writers);
  	    					}else {
  	    						CricketFunctions.DoadWriteCommandToAllViz(
										"-1 RENDERER*FRONT_LAYER*TREE*$LT$All$DataAll$Side" + whichSide + "$Select$LineUp$BottomGrp$LineUp_ALL$Player"
												+ row_id + "$Dataall$txt_Role*GEOM*TEXT SET "
												+ bc.getRuns() + " (" + bc.getBalls() + ")" + " \0",print_writers);
  	    					}
  	    					
  	    					String cap_wkt = "";
  	    					if(inning.getBattingTeamId() == match.getSetup().getHomeTeamId()) {
  	    						
  	    						for (Player plyr : match.getSetup().getHomeSquad()) {
  	    							if(plyr.getPlayerId() == bc.getPlayerId()) {
  	    								cap_wkt = plyr.getCaptainWicketKeeper();
  	    							}
  	    						}
  	    						if(match.getSetup().getHomeSubstitutes() != null) {
  	    							for (Player plyr : match.getSetup().getHomeSubstitutes()) {
  		    							if(plyr.getPlayerId() == bc.getPlayerId()) {
  		    								cap_wkt = plyr.getCaptainWicketKeeper();
  		    							}
  		    						}
  	    						}
  	    						if(match.getSetup().getHomeOtherSquad() != null) {
  	    							for (Player plyr : match.getSetup().getHomeOtherSquad()) {
  		    							if(plyr.getPlayerId() == bc.getPlayerId()) {
  		    								cap_wkt = plyr.getCaptainWicketKeeper();
  		    							}
  		    						}
  	    						}
  	    					}else if(inning.getBattingTeamId() == match.getSetup().getAwayTeamId()) {
  	    						
  	    						if( match.getSetup().getAwaySquad() != null) {
  	    							for (Player plyr : match.getSetup().getAwaySquad()) {
  		    							if(plyr.getPlayerId() == bc.getPlayerId()) {
  		    								cap_wkt = plyr.getCaptainWicketKeeper();
  		    							}
  		    						}
  		    					}
  	    						
  	    						if(match.getSetup().getAwaySubstitutes() != null) {
  	    							for (Player plyr : match.getSetup().getAwaySubstitutes()) {
  		    							if(plyr.getPlayerId() == bc.getPlayerId()) {
  		    								cap_wkt = plyr.getCaptainWicketKeeper();
  		    							}
  		    						}
  	    						}
  	    						
  	    						if(match.getSetup().getAwayOtherSquad() != null) {
  	    							for (Player plyr : match.getSetup().getAwayOtherSquad()) {
  		    							if(plyr.getPlayerId() == bc.getPlayerId()) {
  		    								cap_wkt = plyr.getCaptainWicketKeeper();
  		    							}
  		    						}
  	    						}
  	    						
  	    					}
  	    					
  	    					if(cap_wkt != null) {
  	    						if (cap_wkt.equalsIgnoreCase(CricketUtil.CAPTAIN)) {
  	    							CricketFunctions.DoadWriteCommandToAllViz(
											"-1 RENDERER*FRONT_LAYER*TREE*$LT$All$DataAll$Side" + whichSide + "$Select$LineUp$BottomGrp$LineUp_ALL$Player"
													+ row_id + "$Dataall$Details$SelectCaptain*FUNCTION*Omo*vis_con SET "
													+ "1" + " \0",print_writers);
  	    						} else if (cap_wkt.equalsIgnoreCase("CAPTAIN_WICKET_KEEPER")) {
  	    							CricketFunctions.DoadWriteCommandToAllViz(
											"-1 RENDERER*FRONT_LAYER*TREE*$LT$All$DataAll$Side" + whichSide + "$Select$LineUp$BottomGrp$LineUp_ALL$Player"
													+ row_id + "$Dataall$Details$SelectCaptain*FUNCTION*Omo*vis_con SET "
													+ "1" + " \0",print_writers);
  	    						} else if (cap_wkt.equalsIgnoreCase(CricketUtil.WICKET_KEEPER)) {
  	    							CricketFunctions.DoadWriteCommandToAllViz(
											"-1 RENDERER*FRONT_LAYER*TREE*$LT$All$DataAll$Side" + whichSide + "$Select$LineUp$BottomGrp$LineUp_ALL$Player"
													+ row_id + "$Dataall$Details$SelectCaptain*FUNCTION*Omo*vis_con SET "
													+ "0" + " \0",print_writers);
  	    						}
  	    					}else {
  	    						CricketFunctions.DoadWriteCommandToAllViz(
										"-1 RENDERER*FRONT_LAYER*TREE*$LT$All$DataAll$Side" + whichSide + "$Select$LineUp$BottomGrp$LineUp_ALL$Player"
												+ row_id + "$Dataall$Details$SelectCaptain*FUNCTION*Omo*vis_con SET "
												+ "0" + " \0",print_writers);
  	    					}
  	    				}
  	    			}
					break;
				case "Control_F3":
					CricketFunctions.DoadWriteCommandToAllViz(
							"-1 RENDERER*FRONT_LAYER*TREE*$LT$All$DataAll$Side" + whichSide + "$Select$Comparison$BottomGrp$RestDataGrp$txt_Data1*GEOM*TEXT SET "
									+ "AFTER " + lowerThird.getBallsFacedText() + " OVERS" + "\0",print_writers);
					
					if(lowerThird.getLeftText()[2].split("-")[1].equalsIgnoreCase("10")) {
						CricketFunctions.DoadWriteCommandToAllViz(
								"-1 RENDERER*FRONT_LAYER*TREE*$LT$All$DataAll$Side" + whichSide + "$Select$Comparison$BottomGrp$HomeScoreGrp$txt_Data1*GEOM*TEXT SET "
										+ lowerThird.getLeftText()[2].split("-")[0] + "\0",print_writers);
             	   }else {
             		  CricketFunctions.DoadWriteCommandToAllViz(
								"-1 RENDERER*FRONT_LAYER*TREE*$LT$All$DataAll$Side" + whichSide + "$Select$Comparison$BottomGrp$HomeScoreGrp$txt_Data1*GEOM*TEXT SET "
										+ lowerThird.getLeftText()[2] + "\0",print_writers);
             	   }
             	   
             	   if(lowerThird.getLeftText()[5].split("-")[1].equalsIgnoreCase("10")) {
             		  CricketFunctions.DoadWriteCommandToAllViz(
  							"-1 RENDERER*FRONT_LAYER*TREE*$LT$All$DataAll$Side" + whichSide + "$Select$Comparison$BottomGrp$AwayScoreGrp$txt_Data1*GEOM*TEXT SET "
  									+ lowerThird.getLeftText()[5].split("-")[0] + "\0",print_writers);
             	   }else {
             		  CricketFunctions.DoadWriteCommandToAllViz(
    							"-1 RENDERER*FRONT_LAYER*TREE*$LT$All$DataAll$Side" + whichSide + "$Select$Comparison$BottomGrp$AwayScoreGrp$txt_Data1*GEOM*TEXT SET "
    									+ lowerThird.getLeftText()[5] + "\0",print_writers);
             	   }
					break;
				case "F7": case "F11": case "Control_s": case "Control_f":
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$All$DataAll$Side" + whichSide
    						+ "$Select$PlayerProfile$BottomGrp$RestDataGrp$RestData$Data*FUNCTION*Omo*vis_con SET 4\0",print_writers);
					
					for(int iStat = 0; iStat < lowerThird.getTitlesText().length; iStat++) {
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$All$DataAll$Side" + whichSide
	    						+ "$Select$PlayerProfile$BottomGrp$RestDataGrp$RestData$Data$" + (iStat + 1) + "$txt_Data1A*GEOM*TEXT SET "
	    						+ lowerThird.getTitlesText()[iStat] + "\0",print_writers);
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$All$DataAll$Side" + whichSide
	    						+ "$Select$PlayerProfile$BottomGrp$RestDataGrp$RestData$Data$" + (iStat + 1) + "$txt_Data2A*GEOM*TEXT SET "
	    						+ lowerThird.getStatsText()[iStat] + "\0",print_writers);
					}
					break;
				case "Control_Shift_L":
					
					switch (config.getBroadcaster().toUpperCase()) {
					case Constants.ACC:
						CricketFunctions.DoadWriteCommandToAllViz(
								"-1 RENDERER*FRONT_LAYER*TREE*$LT$All$DataAll$Side" + whichSide + "$Select$MatchId$BottomGrp$RestDataGrp$txt_Data1*GEOM*TEXT SET "
										+ match_name.toUpperCase() + "\0",print_writers);
						break;

					default:
						CricketFunctions.DoadWriteCommandToAllViz(
								"-1 RENDERER*FRONT_LAYER*TREE*$LT$All$DataAll$Side" + whichSide + "$Select$MatchId$BottomGrp$RestDataGrp$txt_Data1*GEOM*TEXT SET "
										+ match_name.toUpperCase() + " T20I" + "\0",print_writers);
						break;
					}
					
	    			CricketFunctions.DoadWriteCommandToAllViz(
							"-1 RENDERER*FRONT_LAYER*TREE*$LT$All$DataAll$Side" + whichSide + "$Select$MatchId$BottomGrp$RestDataGrp$txt_Data2*GEOM*TEXT SET "
									+ matchday + "\0",print_writers);
	    			break;
				case "Control_Shift_M":
					CricketFunctions.DoadWriteCommandToAllViz(
	    					"-1 RENDERER*FRONT_LAYER*TREE*$LT$All$DataAll$Side" + whichSide + "$Select$MatchId$BottomGrp$RestDataGrp$txt_Data1*GEOM*TEXT SET "
	    							+ matchname + "\0",print_writers);
	    			CricketFunctions.DoadWriteCommandToAllViz(
							"-1 RENDERER*FRONT_LAYER*TREE*$LT$All$DataAll$Side" + whichSide + "$Select$MatchId$BottomGrp$RestDataGrp$txt_Data2*GEOM*TEXT SET "
									+ lowerThird.getSurName() + "\0",print_writers);
	    			break;
				case "Alt_Shift_F4":
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$All$DataAll$Side" + whichSide
							+ "$Select$BatterPhase$BottomGrp$RestDataGrp$RestData$Data*FUNCTION*Omo*vis_con SET "
							+ lowerThird.getTitlesText().length + "\0",print_writers);
					
					for(int iStat = 0; iStat < lowerThird.getTitlesText().length; iStat++) {
						
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$All$DataAll$Side" + whichSide
								+ "$Select$BatterPhase$BottomGrp$RestDataGrp$RestData$Data$" + (iStat + 2) + "$txt_Data1A*GEOM*TEXT SET "
								+ lowerThird.getTitlesText()[iStat] + "\0",print_writers);
						
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$All$DataAll$Side" + whichSide
								+ "$Select$BatterPhase$BottomGrp$RestDataGrp$RestData$Data$" + (iStat + 2) + "$txt_Data2A*GEOM*TEXT SET "
								+ lowerThird.getStatsText()[iStat] + "\0",print_writers);
					}
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$All$DataAll$Side" + whichSide
							+ "$Select$BatterPhase$BottomGrp$RestDataGrp$RestData$Data$1$img_Base1$txt_Data1A*GEOM*TEXT SET "
							+ lowerThird.getLeftText()[0] + "\0",print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$All$DataAll$Side" + whichSide
							+ "$Select$BatterPhase$BottomGrp$RestDataGrp$RestData$Data$1$txt_Data2A*GEOM*TEXT SET "
							+ lowerThird.getLeftText()[1] + "\0",print_writers);
					
					break;	
				case "u":
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$All$DataAll$Side" + whichSide
							+ "$Select$30_50Splits$BottomGrp$RestDataGrp$RestData$Data*FUNCTION*Omo*vis_con SET "
							+ lowerThird.getTitlesText().length + "\0",print_writers);
					
					for(int iStat = 0; iStat < lowerThird.getTitlesText().length; iStat++) {
						
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$All$DataAll$Side" + whichSide
								+ "$Select$30_50Splits$BottomGrp$RestDataGrp$RestData$Data$" + (iStat + 2) + "$txt_Data1A*GEOM*TEXT SET "
								+ (iStat + 1) + "\0",print_writers);
						
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$All$DataAll$Side" + whichSide
								+ "$Select$30_50Splits$BottomGrp$RestDataGrp$RestData$Data$" + (iStat + 2) + "$txt_Data2A*GEOM*TEXT SET "
								+ lowerThird.getStatsText()[iStat] + "\0",print_writers);
					}
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$All$DataAll$Side" + whichSide
							+ "$Select$30_50Splits$BottomGrp$RestDataGrp$RestData$Data$1$img_Base1$txt_Data1A*GEOM*TEXT SET "
							+ lowerThird.getLeftText()[0] + "\0",print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$All$DataAll$Side" + whichSide
							+ "$Select$30_50Splits$BottomGrp$RestDataGrp$RestData$Data$1$txt_Data2A*GEOM*TEXT SET "
							+ lowerThird.getLeftText()[1] + "\0",print_writers);
					
					break;
				case "Control_a":
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$All$DataAll$Side" + whichSide
							+ "$Select$ProjectedScore$BottomGrp$RestDataGrp$RestData$Data*FUNCTION*Omo*vis_con SET "
							+ "2" + " \0",print_writers);

					for(int iStat = 0; iStat < lowerThird.getTitlesText().length-1; iStat++) {
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$All$DataAll$Side" + whichSide
								+ "$Select$ProjectedScore$BottomGrp$RestDataGrp$RestData$Data$" + (iStat + 1) + "$txt_Data1A*GEOM*TEXT SET "
								+ lowerThird.getTitlesText()[iStat] + "\0",print_writers);
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$All$DataAll$Side" + whichSide
								+ "$Select$ProjectedScore$BottomGrp$RestDataGrp$RestData$Data$" + (iStat + 1) + "$txt_Data2A*GEOM*TEXT SET "
								+ lowerThird.getStatsText()[iStat] + "\0",print_writers);
					}
					
					break;
				case "Control_h":
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$All$DataAll$Side" + whichSide
    						+ "$Select$PlayerProfile$BottomGrp$RestDataGrp$RestData$Data*FUNCTION*Omo*vis_con SET 4\0",print_writers);
					
					if(lowerThird.getWhichSponsor().equalsIgnoreCase(CricketUtil.ODI) || 
							lowerThird.getWhichSponsor().equalsIgnoreCase(CricketUtil.OD)) {
//						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$All$DataAll$Side" + whichSide
//								+ "$Select$PlayerProfile$BottomGrp$RestDataGrp$RestData$Data*FUNCTION*Omo*vis_con SET "
//								+ "2" + " \0",print_writers);
						
//						for(int i=0; i<lowerThird.getTitlesText().length; i++) {
//							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$All$DataAll$Side" + whichSide
//									+ "$Select$PlayerProfile$BottomGrp$RestDataGrp$RestData$Data$" + (i + 1) + "$txt_Data1A*GEOM*TEXT SET "
//									+ lowerThird.getTitlesText()[i] + "\0",print_writers);
//						}
//						
//						//1st Phase
//						if(Integer.valueOf(lowerThird.getStatsText()[0].split("-")[0]) == 0 && Integer.valueOf(lowerThird.getStatsText()[0].split("-")[1]) == 0) {
//							if(Float.valueOf(CricketFunctions.OverBalls(inning.getTotalOvers(), inning.getTotalBalls())) > 0.0) {
//								CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$All$DataAll$Side" + whichSide
//										+ "$Select$PlayerProfile$BottomGrp$RestDataGrp$RestData$Data$1$txt_Data2A*GEOM*TEXT SET "
//										+ "0-0" + "\0",print_writers);
//							}else {
//								CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$All$DataAll$Side" + whichSide
//										+ "$Select$PlayerProfile$BottomGrp$RestDataGrp$RestData$Data$1$txt_Data2A*GEOM*TEXT SET "
//										+ "-" + "\0",print_writers);
//							}
//						}else {
//							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$All$DataAll$Side" + whichSide
//									+ "$Select$PlayerProfile$BottomGrp$RestDataGrp$RestData$Data$1$txt_Data2A*GEOM*TEXT SET "
//									+ lowerThird.getStatsText()[0].split("-")[0] + "-" + 
//									lowerThird.getStatsText()[0].split("-")[1] + "\0",print_writers);
//						}
//						
//						//2nd phase
//						if(Integer.valueOf(lowerThird.getStatsText()[1].split("-")[0]) == 0 && Integer.valueOf(lowerThird.getStatsText()[1].split("-")[1]) == 0) {
//							if(Float.valueOf(CricketFunctions.OverBalls(inning.getTotalOvers(), inning.getTotalBalls())) > 6.0) {
//								CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$All$DataAll$Side" + whichSide
//										+ "$Select$PlayerProfile$BottomGrp$RestDataGrp$RestData$Data$2$txt_Data2A*GEOM*TEXT SET "
//										+ "0-0" + "\0",print_writers);
//							}else {
//								CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$All$DataAll$Side" + whichSide
//										+ "$Select$PlayerProfile$BottomGrp$RestDataGrp$RestData$Data$2$txt_Data2A*GEOM*TEXT SET "
//										+ "-" + "\0",print_writers);
//							}
//						}else {
//							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$All$DataAll$Side" + whichSide
//									+ "$Select$PlayerProfile$BottomGrp$RestDataGrp$RestData$Data$2$txt_Data2A*GEOM*TEXT SET "
//									+ lowerThird.getStatsText()[1].split("-")[0] + "-" + 
//									lowerThird.getStatsText()[1].split("-")[1] + "\0",print_writers);
//						}
//						
//						//3rd Phase
//						if(Integer.valueOf(lowerThird.getStatsText()[2].split("-")[0]) == 0 && Integer.valueOf(lowerThird.getStatsText()[2].split("-")[1]) == 0) {
//							if(Float.valueOf(CricketFunctions.OverBalls(inning.getTotalOvers(), inning.getTotalBalls())) > 15.0) {
//								CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$All$DataAll$Side" + whichSide
//										+ "$Select$PlayerProfile$BottomGrp$RestDataGrp$RestData$Data$3$txt_Data2A*GEOM*TEXT SET "
//										+ "0-0" + "\0",print_writers);
//							}else {
//								CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$All$DataAll$Side" + whichSide
//										+ "$Select$PlayerProfile$BottomGrp$RestDataGrp$RestData$Data$3$txt_Data2A*GEOM*TEXT SET "
//										+ "-" + "\0",print_writers);
//							}
//						}else {
//							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$All$DataAll$Side" + whichSide
//									+ "$Select$PlayerProfile$BottomGrp$RestDataGrp$RestData$Data$3$txt_Data2A*GEOM*TEXT SET "
//									+ lowerThird.getStatsText()[2].split("-")[0] + "-" + 
//									lowerThird.getStatsText()[2].split("-")[1] + "\0",print_writers);
//						}
//						
//						//4th Phase
//						if(Integer.valueOf(lowerThird.getStatsText()[3].split("-")[0]) == 0 && Integer.valueOf(lowerThird.getStatsText()[3].split("-")[1]) == 0) {
//							if(Float.valueOf(CricketFunctions.OverBalls(inning.getTotalOvers(), inning.getTotalBalls())) > 15.0) {
//								CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$All$DataAll$Side" + whichSide
//										+ "$Select$PlayerProfile$BottomGrp$RestDataGrp$RestData$Data$4$txt_Data2A*GEOM*TEXT SET "
//										+ "0-0" + "\0",print_writers);
//							}else {
//								CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$All$DataAll$Side" + whichSide
//										+ "$Select$PlayerProfile$BottomGrp$RestDataGrp$RestData$Data$4$txt_Data2A*GEOM*TEXT SET "
//										+ "-" + "\0",print_writers);
//							}
//						}else {
//							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$All$DataAll$Side" + whichSide
//									+ "$Select$PlayerProfile$BottomGrp$RestDataGrp$RestData$Data$4$txt_Data2A*GEOM*TEXT SET "
//									+ lowerThird.getStatsText()[3].split("-")[0] + "-" + 
//									lowerThird.getStatsText()[3].split("-")[1] + "\0",print_writers);
//						}
//						
//						//5th Phase
//						if(Integer.valueOf(lowerThird.getStatsText()[4].split("-")[0]) == 0 && Integer.valueOf(lowerThird.getStatsText()[4].split("-")[1]) == 0) {
//							if(Float.valueOf(CricketFunctions.OverBalls(inning.getTotalOvers(), inning.getTotalBalls())) > 15.0) {
//								CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$All$DataAll$Side" + whichSide
//										+ "$Select$PlayerProfile$BottomGrp$RestDataGrp$RestData$Data$5$txt_Data2A*GEOM*TEXT SET "
//										+ "0-0" + "\0",print_writers);
//							}else {
//								CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$All$DataAll$Side" + whichSide
//										+ "$Select$PlayerProfile$BottomGrp$RestDataGrp$RestData$Data$5$txt_Data2A*GEOM*TEXT SET "
//										+ "-" + "\0",print_writers);
//							}
//						}else {
//							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$All$DataAll$Side" + whichSide
//									+ "$Select$PlayerProfile$BottomGrp$RestDataGrp$RestData$Data$5$txt_Data2A*GEOM*TEXT SET "
//									+ lowerThird.getStatsText()[4].split("-")[0] + "-" + 
//									lowerThird.getStatsText()[4].split("-")[1] + "\0",print_writers);
//						}
						
						for(int i=0; i<lowerThird.getTitlesText().length; i++) {
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$All$DataAll$Side" + whichSide
									+ "$Select$ProjectedScore$BottomGrp$RestDataGrp$RestData$Data$" + (i + 1) + "$txt_Data1A*GEOM*TEXT SET OVERS "
									+ lowerThird.getTitlesText()[i] + "\0",print_writers);
						}
						
						//1st Phase
						if(Integer.valueOf(lowerThird.getStatsText()[0].split("-")[0]) == 0 && Integer.valueOf(lowerThird.getStatsText()[0].split("-")[1]) == 0) {
							if(Float.valueOf(CricketFunctions.OverBalls(inning.getTotalOvers(), inning.getTotalBalls())) > 0.0) {
								CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$All$DataAll$Side" + whichSide
										+ "$Select$ProjectedScore$BottomGrp$RestDataGrp$RestData$Data$1$txt_Data2A*GEOM*TEXT SET "
										+ "0-0" + "\0",print_writers);
							}else {
								CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$All$DataAll$Side" + whichSide
										+ "$Select$ProjectedScore$BottomGrp$RestDataGrp$RestData$Data$1$txt_Data2A*GEOM*TEXT SET "
										+ "-" + "\0",print_writers);
							}
						}else {
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$All$DataAll$Side" + whichSide
									+ "$Select$ProjectedScore$BottomGrp$RestDataGrp$RestData$Data$1$txt_Data2A*GEOM*TEXT SET "
									+ lowerThird.getStatsText()[0].split("-")[0] + "-" + 
									lowerThird.getStatsText()[0].split("-")[1] + "\0",print_writers);
						}
						
						//2nd phase
						if(Integer.valueOf(lowerThird.getStatsText()[1].split("-")[0]) == 0 && Integer.valueOf(lowerThird.getStatsText()[1].split("-")[1]) == 0) {
							if(Float.valueOf(CricketFunctions.OverBalls(inning.getTotalOvers(), inning.getTotalBalls())) > 6.0) {
								CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$All$DataAll$Side" + whichSide
										+ "$Select$ProjectedScore$BottomGrp$RestDataGrp$RestData$Data$2$txt_Data2A*GEOM*TEXT SET "
										+ "0-0" + "\0",print_writers);
							}else {
								CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$All$DataAll$Side" + whichSide
										+ "$Select$ProjectedScore$BottomGrp$RestDataGrp$RestData$Data$2$txt_Data2A*GEOM*TEXT SET "
										+ "-" + "\0",print_writers);
							}
						}else {
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$All$DataAll$Side" + whichSide
									+ "$Select$ProjectedScore$BottomGrp$RestDataGrp$RestData$Data$2$txt_Data2A*GEOM*TEXT SET "
									+ lowerThird.getStatsText()[1].split("-")[0] + "-" + 
									lowerThird.getStatsText()[1].split("-")[1] + "\0",print_writers);
						}
						
						//3rd Phase
						if(Integer.valueOf(lowerThird.getStatsText()[2].split("-")[0]) == 0 && Integer.valueOf(lowerThird.getStatsText()[2].split("-")[1]) == 0) {
							if(Float.valueOf(CricketFunctions.OverBalls(inning.getTotalOvers(), inning.getTotalBalls())) > 15.0) {
								CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$All$DataAll$Side" + whichSide
										+ "$Select$ProjectedScore$BottomGrp$RestDataGrp$RestData$Data$3$txt_Data2A*GEOM*TEXT SET "
										+ "0-0" + "\0",print_writers);
							}else {
								CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$All$DataAll$Side" + whichSide
										+ "$Select$ProjectedScore$BottomGrp$RestDataGrp$RestData$Data$3$txt_Data2A*GEOM*TEXT SET "
										+ "-" + "\0",print_writers);
							}
						}else {
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$All$DataAll$Side" + whichSide
									+ "$Select$ProjectedScore$BottomGrp$RestDataGrp$RestData$Data$3$txt_Data2A*GEOM*TEXT SET "
									+ lowerThird.getStatsText()[2].split("-")[0] + "-" + 
									lowerThird.getStatsText()[2].split("-")[1] + "\0",print_writers);
						}
						
					}else {
//						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$All$DataAll$Side" + whichSide
//								+ "$Select$ProjectedScore$BottomGrp$RestDataGrp$RestData$Data*FUNCTION*Omo*vis_con SET "
//								+ "2" + " \0",print_writers);
						
						for(int i=0; i<lowerThird.getTitlesText().length; i++) {
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$All$DataAll$Side" + whichSide
									+ "$Select$ProjectedScore$BottomGrp$RestDataGrp$RestData$Data$" + (i + 1) + "$txt_Data1A*GEOM*TEXT SET OVERS "
									+ lowerThird.getTitlesText()[i] + "\0",print_writers);
						}
						
						//1st Phase
						if(Integer.valueOf(lowerThird.getStatsText()[0].split("-")[0]) == 0 && Integer.valueOf(lowerThird.getStatsText()[0].split("-")[1]) == 0) {
							if(Float.valueOf(CricketFunctions.OverBalls(inning.getTotalOvers(), inning.getTotalBalls())) > 0.0) {
								CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$All$DataAll$Side" + whichSide
										+ "$Select$ProjectedScore$BottomGrp$RestDataGrp$RestData$Data$1$txt_Data2A*GEOM*TEXT SET "
										+ "0-0" + "\0",print_writers);
							}else {
								CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$All$DataAll$Side" + whichSide
										+ "$Select$ProjectedScore$BottomGrp$RestDataGrp$RestData$Data$1$txt_Data2A*GEOM*TEXT SET "
										+ "-" + "\0",print_writers);
							}
						}else {
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$All$DataAll$Side" + whichSide
									+ "$Select$ProjectedScore$BottomGrp$RestDataGrp$RestData$Data$1$txt_Data2A*GEOM*TEXT SET "
									+ lowerThird.getStatsText()[0].split("-")[0] + "-" + 
									lowerThird.getStatsText()[0].split("-")[1] + "\0",print_writers);
						}
						
						//2nd phase
						if(Integer.valueOf(lowerThird.getStatsText()[1].split("-")[0]) == 0 && Integer.valueOf(lowerThird.getStatsText()[1].split("-")[1]) == 0) {
							if(Float.valueOf(CricketFunctions.OverBalls(inning.getTotalOvers(), inning.getTotalBalls())) > 6.0) {
								CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$All$DataAll$Side" + whichSide
										+ "$Select$ProjectedScore$BottomGrp$RestDataGrp$RestData$Data$2$txt_Data2A*GEOM*TEXT SET "
										+ "0-0" + "\0",print_writers);
							}else {
								CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$All$DataAll$Side" + whichSide
										+ "$Select$ProjectedScore$BottomGrp$RestDataGrp$RestData$Data$2$txt_Data2A*GEOM*TEXT SET "
										+ "-" + "\0",print_writers);
							}
						}else {
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$All$DataAll$Side" + whichSide
									+ "$Select$ProjectedScore$BottomGrp$RestDataGrp$RestData$Data$2$txt_Data2A*GEOM*TEXT SET "
									+ lowerThird.getStatsText()[1].split("-")[0] + "-" + 
									lowerThird.getStatsText()[1].split("-")[1] + "\0",print_writers);
						}
						
						//3rd Phase
						if(Integer.valueOf(lowerThird.getStatsText()[2].split("-")[0]) == 0 && Integer.valueOf(lowerThird.getStatsText()[2].split("-")[1]) == 0) {
							if(Float.valueOf(CricketFunctions.OverBalls(inning.getTotalOvers(), inning.getTotalBalls())) > 15.0) {
								CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$All$DataAll$Side" + whichSide
										+ "$Select$ProjectedScore$BottomGrp$RestDataGrp$RestData$Data$3$txt_Data2A*GEOM*TEXT SET "
										+ "0-0" + "\0",print_writers);
							}else {
								CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$All$DataAll$Side" + whichSide
										+ "$Select$ProjectedScore$BottomGrp$RestDataGrp$RestData$Data$3$txt_Data2A*GEOM*TEXT SET "
										+ "-" + "\0",print_writers);
							}
						}else {
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$All$DataAll$Side" + whichSide
									+ "$Select$ProjectedScore$BottomGrp$RestDataGrp$RestData$Data$3$txt_Data2A*GEOM*TEXT SET "
									+ lowerThird.getStatsText()[2].split("-")[0] + "-" + 
									lowerThird.getStatsText()[2].split("-")[1] + "\0",print_writers);
						}
					}
	    			break;
				case "Shift_F6":
//					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$All$DataAll$Side" + whichSide
//							+ "$Select$BatsmanOut02$BottomGrp$RestDataGrp$Visible*FUNCTION*Omo*vis_con SET 1 \0",print_writers);
//					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$All$DataAll$Side" + whichSide
//							+ "$Select$BatsmanBatsmanOut02Out$BottomGrp$RestDataGrp$txt_Data1*GEOM*TEXT SET "
//							+ "FOURS : " + lowerThird.getLeftText()[0] + "\0",print_writers);
//					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$All$DataAll$Side" + whichSide
//							+ "$Select$BatsmanOut02$BottomGrp$RestDataGrp$txt_Data2*GEOM*TEXT SET "
//							+ "SIXES : " + lowerThird.getLeftText()[1] + "\0",print_writers);

					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$All$DataAll$Side" + whichSide
							+ "$Select$BatsmanOut$BottomGrp$RestDataGrp$Visible*FUNCTION*Omo*vis_con SET 1 \0",print_writers);
					
//					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$All$DataAll$Side" + whichSide
//							+ "$Select$BatsmanOut02$BottomGrp$RestDataGrp$txt_Data1*GEOM*TEXT SET \0",print_writers);
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$All$DataAll$Side" + whichSide
							+ "$Select$BatsmanOut$BottomGrp$RestDataGrp$txt_Data1*GEOM*TEXT SET "
							+ "FOURS : " + lowerThird.getLeftText()[0] + "\0",print_writers);
					
//					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$All$DataAll$Side" + whichSide
//							+ "$Select$BatsmanOut02$BottomGrp$RestDataGrp$Detail_Grp$1$txt_SubHead01*GEOM*TEXT SET "
//							+ "FOURS : " + "\0",print_writers);
//					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$All$DataAll$Side" + whichSide
//							+ "$Select$BatsmanOut02$BottomGrp$RestDataGrp$Detail_Grp$1$txt_SubValue01*GEOM*TEXT SET "
//							+ lowerThird.getLeftText()[0] + "\0",print_writers);
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$All$DataAll$Side" + whichSide
							+ "$Select$BatsmanOut$BottomGrp$RestDataGrp$txt_Data2*GEOM*TEXT SET "
							+ "SIXES : " + lowerThird.getLeftText()[1] + "\0",print_writers);
					
//					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$All$DataAll$Side" + whichSide
//							+ "$Select$BatsmanOut02$BottomGrp$RestDataGrp$Detail_Grp$2$txt_SubHead01*GEOM*TEXT SET "
//							+ "SIXES : " + "\0",print_writers);
//					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$All$DataAll$Side" + whichSide
//							+ "$Select$BatsmanOut02$BottomGrp$RestDataGrp$Detail_Grp$2$txt_SubValue01*GEOM*TEXT SET "
//							+ lowerThird.getLeftText()[1] + "\0",print_writers);
					
//					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$All$DataAll$Side" + whichSide
//							+ "$Select$BatsmanOut02$BottomGrp$RestDataGrp$Detail_Grp$3$txt_SubHead01*GEOM*TEXT SET "
//							+ "S/R : " + "\0",print_writers);
//					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$All$DataAll$Side" + whichSide
//							+ "$Select$BatsmanOut02$BottomGrp$RestDataGrp$Detail_Grp$3$txt_SubValue01*GEOM*TEXT SET "
//							+ lowerThird.getLeftText()[3] + "\0",print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$All$DataAll$Side" + whichSide
							+ "$Select$BatsmanOut$BottomGrp$ScoreGrp$txt_Data1*GEOM*TEXT SET "
							+ lowerThird.getScoreText() + "\0",print_writers);
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$All$DataAll$Side" + whichSide
							+ "$Select$BatsmanOut$BottomGrp$ScoreGrp$txt_Data2*GEOM*TEXT SET "
							+ "OFF " + lowerThird.getBallsFacedText() + "\0",print_writers);
					
//					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$All$DataAll$Side" + whichSide
//							+ "$Select$BatsmanOut02$BottomGrp$ScoreGrp$txt_Data1*GEOM*TEXT SET "
//							+ lowerThird.getScoreText() + "\0",print_writers);
//					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$All$DataAll$Side" + whichSide
//							+ "$Select$BatsmaBatsmanOut02nOut$BottomGrp$ScoreGrp$txt_Data2*GEOM*TEXT SET "
//							+ "OFF " + lowerThird.getBallsFacedText() + "\0",print_writers);
					break;
				case "F6": case "Control_F6":
					
//					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$All$DataAll$Side" + whichSide
//							+ "$Select$BatsmanOut$BottomGrp$ScoreGrp$txt_Data1*GEOM*TEXT SET "
//							+ lowerThird.getScoreText() + "\0",print_writers);
//					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$All$DataAll$Side" + whichSide
//							+ "$Select$BatsmanOut$BottomGrp$ScoreGrp$txt_Data2*GEOM*TEXT SET "
//							+ "OFF " + lowerThird.getBallsFacedText() + "\0",print_writers);
					
//					switch (whatToProcess) {
//					case "F6":
//							
//						if(battingCard.getHowOut().equalsIgnoreCase(CricketUtil.CAUGHT_AND_BOWLED)) {
//							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$All$DataAll$Side" + whichSide
//									+ "$Select$BatsmanOut$BottomGrp$RestDataGrp$Visible*FUNCTION*Omo*vis_con SET 0 \0",print_writers);
//							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$All$DataAll$Side" + whichSide
//									+ "$Select$BatsmanOut$BottomGrp$RestDataGrp$txt_Data1*GEOM*TEXT SET "
//									+ battingCard.getHowOutPartOne() + " " + battingCard.getHowOutPartTwo() + "\0",print_writers);
//						}else if(battingCard.getHowOut().equalsIgnoreCase(CricketUtil.RETIRED_OUT)) {
//							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$All$DataAll$Side" + whichSide
//									+ "$Select$BatsmanOut$BottomGrp$RestDataGrp$Visible*FUNCTION*Omo*vis_con SET 1 \0",print_writers);
//							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$All$DataAll$Side" + whichSide
//									+ "$Select$BatsmanOut$BottomGrp$RestDataGrp$txt_Data1*GEOM*TEXT SET "
//									+ battingCard.getHowOutPartOne() + "\0",print_writers);
//							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$All$DataAll$Side" + whichSide
//									+ "$Select$BatsmanOut$BottomGrp$RestDataGrp$txt_Data2*GEOM*TEXT SET "
//									+ battingCard.getHowOutPartTwo() + "\0",print_writers);
//						}else {
//							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$All$DataAll$Side" + whichSide
//									+ "$Select$BatsmanOut$BottomGrp$RestDataGrp$Visible*FUNCTION*Omo*vis_con SET 0 \0",print_writers);
//							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$All$DataAll$Side" + whichSide
//									+ "$Select$BatsmanOut$BottomGrp$RestDataGrp$txt_Data1*GEOM*TEXT SET "
//									+ lowerThird.getLeftText()[0] + "\0",print_writers);
//						}
//						break;
//					case "Control_F6":
//						if(battingCardList.get(battingCardList.size()-1).getHowOut().equalsIgnoreCase(CricketUtil.CAUGHT_AND_BOWLED)) {
//							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$All$DataAll$Side" + whichSide
//									+ "$Select$BatsmanOut$BottomGrp$RestDataGrp$Visible*FUNCTION*Omo*vis_con SET 0 \0",print_writers);
//							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$All$DataAll$Side" + whichSide
//									+ "$Select$BatsmanOut$BottomGrp$RestDataGrp$txt_Data1*GEOM*TEXT SET "
//									+ battingCardList.get(battingCardList.size()-1).getHowOutPartOne() + " " 
//									+ battingCardList.get(battingCardList.size()-1).getHowOutPartTwo() + "\0",print_writers);
//						}else if(battingCardList.get(battingCardList.size()-1).getHowOut().equalsIgnoreCase(CricketUtil.RETIRED_OUT)) {
//							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$All$DataAll$Side" + whichSide
//									+ "$Select$BatsmanOut$BottomGrp$RestDataGrp$Visible*FUNCTION*Omo*vis_con SET 1 \0",print_writers);
//							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$All$DataAll$Side" + whichSide
//									+ "$Select$BatsmanOut$BottomGrp$RestDataGrp$txt_Data1*GEOM*TEXT SET "
//									+ battingCardList.get(battingCardList.size()-1).getHowOutPartOne() + "\0",print_writers);
//							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$All$DataAll$Side" + whichSide
//									+ "$Select$BatsmanOut$BottomGrp$RestDataGrp$txt_Data2*GEOM*TEXT SET "
//									+ battingCardList.get(battingCardList.size()-1).getHowOutPartTwo() + "\0",print_writers);
//						}else {
//							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$All$DataAll$Side" + whichSide
//									+ "$Select$BatsmanOut$BottomGrp$RestDataGrp$Visible*FUNCTION*Omo*vis_con SET 0 \0",print_writers);
//							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$All$DataAll$Side" + whichSide
//									+ "$Select$BatsmanOut$BottomGrp$RestDataGrp$txt_Data1*GEOM*TEXT SET "
//									+ lowerThird.getLeftText()[0] + "\0",print_writers);
//						}
//						break;	
//					}
					
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$All$DataAll$Side" + whichSide
							+ "$Select$BatsmanOut02$BottomGrp$ScoreGrp$txt_Data1*GEOM*TEXT SET "
							+ lowerThird.getScoreText() + "\0",print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$All$DataAll$Side" + whichSide
							+ "$Select$BatsmanOut02$BottomGrp$ScoreGrp$txt_Data2*GEOM*TEXT SET "
							+ "OFF " + lowerThird.getBallsFacedText() + "\0",print_writers);
					
//					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$All$DataAll$Side" + whichSide
//							+ "$Select$BatsmanOut02$BottomGrp$RestDataGrp$Visible*FUNCTION*Omo*vis_con SET 0 \0",print_writers);
//					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$All$DataAll$Side" + whichSide
//							+ "$Select$BatsmanOut02$BottomGrp$RestDataGrp$txt_Data1*GEOM*TEXT SET "
//							+ lowerThird.getLeftText()[0] + "\0",print_writers);
//					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$All$DataAll$Side" + whichSide
							+ "$Select$BatsmanOut02$BottomGrp$RestDataGrp$Details$1st$txt_Head*GEOM*TEXT SET "
							+ "FOURS : " + "\0",print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$All$DataAll$Side" + whichSide
							+ "$Select$BatsmanOut02$BottomGrp$RestDataGrp$Details$1st$txt_Value*GEOM*TEXT SET "
							+ lowerThird.getLeftText()[1] + "\0",print_writers);
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$All$DataAll$Side" + whichSide
							+ "$Select$BatsmanOut02$BottomGrp$RestDataGrp$Details$2nd$txt_Head*GEOM*TEXT SET "
							+ "SIXES : " + "\0",print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$All$DataAll$Side" + whichSide
							+ "$Select$BatsmanOut02$BottomGrp$RestDataGrp$Details$2nd$txt_Value*GEOM*TEXT SET "
							+ lowerThird.getLeftText()[2] + "\0",print_writers);
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$All$DataAll$Side" + whichSide
							+ "$Select$BatsmanOut02$BottomGrp$RestDataGrp$Details$3rd$txt_Head*GEOM*TEXT SET "
							+ "S/R : " + "\0",print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$All$DataAll$Side" + whichSide
							+ "$Select$BatsmanOut02$BottomGrp$RestDataGrp$Details$3rd$txt_Value*GEOM*TEXT SET "
							+ lowerThird.getLeftText()[4] + "\0",print_writers);
					
					switch (whatToProcess) {
					case "F6":
						if(battingCard.getHowOut().equalsIgnoreCase(CricketUtil.CAUGHT_AND_BOWLED)) {
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$All$DataAll$Side" + whichSide
									+ "$Select$BatsmanOut02$BottomGrp$RestDataGrp$Visible*FUNCTION*Omo*vis_con SET 0 \0",print_writers);
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$All$DataAll$Side" + whichSide
									+ "$Select$BatsmanOut02$BottomGrp$RestDataGrp$txt_Data1*GEOM*TEXT SET "
									+ battingCard.getHowOutPartOne() + " " + battingCard.getHowOutPartTwo() + "\0",print_writers);
						}else if(battingCard.getHowOut().equalsIgnoreCase(CricketUtil.RETIRED_OUT)) {
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$All$DataAll$Side" + whichSide
									+ "$Select$BatsmanOut02$BottomGrp$RestDataGrp$Visible*FUNCTION*Omo*vis_con SET 1 \0",print_writers);
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$All$DataAll$Side" + whichSide
									+ "$Select$BatsmanOut02$BottomGrp$RestDataGrp$txt_Data1*GEOM*TEXT SET "
									+ battingCard.getHowOutPartOne() + "\0",print_writers);
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$All$DataAll$Side" + whichSide
									+ "$Select$BatsmanOut02$BottomGrp$RestDataGrp$txt_Data2*GEOM*TEXT SET "
									+ battingCard.getHowOutPartTwo() + "\0",print_writers);
						}else {
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$All$DataAll$Side" + whichSide
									+ "$Select$BatsmanOut02$BottomGrp$RestDataGrp$Visible*FUNCTION*Omo*vis_con SET 0 \0",print_writers);
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$All$DataAll$Side" + whichSide
									+ "$Select$BatsmanOut02$BottomGrp$RestDataGrp$txt_Data1*GEOM*TEXT SET "
									+ lowerThird.getLeftText()[0] + "\0",print_writers);
						}
						break;
					case "Control_F6":
						if(battingCardList.get(battingCardList.size()-1).getHowOut().equalsIgnoreCase(CricketUtil.CAUGHT_AND_BOWLED)) {
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$All$DataAll$Side" + whichSide
									+ "$Select$BatsmanOut02$BottomGrp$RestDataGrp$Visible*FUNCTION*Omo*vis_con SET 0 \0",print_writers);
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$All$DataAll$Side" + whichSide
									+ "$Select$BatsmanOut02$BottomGrp$RestDataGrp$txt_Data1*GEOM*TEXT SET "
									+ battingCardList.get(battingCardList.size()-1).getHowOutPartOne() + " " 
									+ battingCardList.get(battingCardList.size()-1).getHowOutPartTwo() + "\0",print_writers);
						}else if(battingCardList.get(battingCardList.size()-1).getHowOut().equalsIgnoreCase(CricketUtil.RETIRED_OUT)) {
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$All$DataAll$Side" + whichSide
									+ "$Select$BatsmanOut02$BottomGrp$RestDataGrp$Visible*FUNCTION*Omo*vis_con SET 1 \0",print_writers);
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$All$DataAll$Side" + whichSide
									+ "$Select$BatsmanOut02$BottomGrp$RestDataGrp$txt_Data1*GEOM*TEXT SET "
									+ battingCardList.get(battingCardList.size()-1).getHowOutPartOne() + "\0",print_writers);
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$All$DataAll$Side" + whichSide
									+ "$Select$BatsmanOut02$BottomGrp$RestDataGrp$txt_Data2*GEOM*TEXT SET "
									+ battingCardList.get(battingCardList.size()-1).getHowOutPartTwo() + "\0",print_writers);
						}else {
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$All$DataAll$Side" + whichSide
									+ "$Select$BatsmanOut02$BottomGrp$RestDataGrp$Visible*FUNCTION*Omo*vis_con SET 0 \0",print_writers);
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$All$DataAll$Side" + whichSide
									+ "$Select$BatsmanOut02$BottomGrp$RestDataGrp$txt_Data1*GEOM*TEXT SET "
									+ lowerThird.getLeftText()[0] + "\0",print_writers);
						}
						break;	
					}
					break;
				case "Shift_F3":
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$All$DataAll$Side" + whichSide
							+ "$Select$FOW$BottomGrp$RestDataGrp$RestData$Data*FUNCTION*Omo*vis_con SET "
							+ lowerThird.getTitlesText().length + " \0",print_writers);
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$All$DataAll$Side" + whichSide
							+ "$Select$FOW$BottomGrp$RestDataGrp$RestData$Data$1$txt_Data2A*GEOM*TEXT SET RUNS\0",print_writers);
					
					for(int iStat = 0; iStat < lowerThird.getTitlesText().length; iStat++) {
						
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$All$DataAll$Side" + whichSide
								+ "$Select$FOW$BottomGrp$RestDataGrp$RestData$Data$"
								+ (iStat + 2) + "$txt_Data1A*GEOM*TEXT SET "
								+ (iStat+1) + "\0",print_writers);
						
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$All$DataAll$Side" + whichSide
								+ "$Select$FOW$BottomGrp$RestDataGrp$RestData$Data$"
								+ (iStat + 2) + "$txt_Data2A*GEOM*TEXT SET "
								+ lowerThird.getStatsText()[iStat] + "\0",print_writers);
						
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$All$DataAll$Side" + whichSide
		    					+ "$Select$FOW$BottomGrp$RestDataGrp$RestData$Data$" + (iStat + 2) + "$img_Text1*TEXTURE*IMAGE SET "
		    					+ Constants.BAN_AFG_SERIES_TEXT1 + lowerThird.getWhichTeamFlag() + "\0",print_writers);
					}
					break;
				case "F9":
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$All$DataAll$Side" + whichSide
							+ "$Select$BowlerBowlingDetails$BottomGrp$RestDataGrp$txt_Data1*GEOM*TEXT SET \0",print_writers);
					
					for(int iStat = 0; iStat < lowerThird.getTitlesText().length; iStat++) {
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$All$DataAll$Side" + whichSide
								+ "$Select$BowlerBowlingDetails$BottomGrp$RestDataGrp$RestData$Data$" + (iStat + 1) + "$txt_Data1A*GEOM*TEXT SET "
								+ lowerThird.getTitlesText()[iStat] + "\0",print_writers);
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$All$DataAll$Side" + whichSide
								+ "$Select$BowlerBowlingDetails$BottomGrp$RestDataGrp$RestData$Data$" + (iStat + 1) + "$txt_Data2A*GEOM*TEXT SET "
								+ lowerThird.getStatsText()[iStat] + "\0",print_writers);
					}
					break;
				case "F5":
					
					for(int iStat = 0; iStat < lowerThird.getTitlesText().length; iStat++) {
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$All$DataAll$Side" + whichSide
								+ "$Select$PlayerStats$BottomGrp$RestDataGrp$RestData$Data$" + (iStat + 1) + "$txt_Data1A*GEOM*TEXT SET "
								+ lowerThird.getTitlesText()[iStat] + "\0",print_writers);
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$All$DataAll$Side" + whichSide
								+ "$Select$PlayerStats$BottomGrp$RestDataGrp$RestData$Data$" + (iStat + 1)  + "$txt_Data2A*GEOM*TEXT SET "
								+ lowerThird.getStatsText()[iStat]  + "\0",print_writers);
					}
					break;
				case "Shift_F5": case "Shift_F9": case "Alt_F12":
					
					for(int iStat = 0; iStat < lowerThird.getTitlesText().length; iStat++) {
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$All$DataAll$Side" + whichSide
								+ "$Select$BattingSummary$BottomGrp$RestDataGrp$RestData$Data$" + (iStat + 1) + "$txt_Data1A*GEOM*TEXT SET "
								+ lowerThird.getTitlesText()[iStat] + " \0",print_writers);
						
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$All$DataAll$Side" + whichSide
								+ "$Select$BattingSummary$BottomGrp$RestDataGrp$RestData$Data$" + (iStat + 1) + "$txt_Data2A*GEOM*TEXT SET "
								+ lowerThird.getStatsText()[iStat] + " \0",print_writers);
					}
					break;
				case "Control_F9":
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$All$DataAll$Side" + whichSide
    						+ "$Select$NameSuperSingle$BottomGrp$RestDataGrp$txt_Data1*GEOM*TEXT SET "
    						+ lowerThird.getLeftText()[0]
    						+ "\0",print_writers);
					break;
				case "Control_F5":
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$All$DataAll$Side" + whichSide
    						+ "$Select$NameSuperSingle$BottomGrp$RestDataGrp$txt_Data1*GEOM*TEXT SET "
    						+ lowerThird.getLeftText()[0]
    						+ "\0",print_writers);
					break;
	            case "F8": case "Alt_F8":
	            	if(lowerThird.getLeftText()[0].equalsIgnoreCase("Captain") || lowerThird.getLeftText()[0].equalsIgnoreCase("Captain Wicket-Keeper") ||
							lowerThird.getLeftText()[0].equalsIgnoreCase("Wicket-Keeper")) {
	            		CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$All$DataAll$Side" + whichSide
	    						+ "$Select$NameSuperSingle$BottomGrp$RestDataGrp$txt_Data1*GEOM*TEXT SET "
	    						+ lowerThird.getLeftText()[0].toUpperCase() + ", " + lowerThird.getSubTitle() + "\0",print_writers);
	            		
					}else if(lowerThird.getLeftText()[0].equalsIgnoreCase("Team")) {
						
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$All$DataAll$Side" + whichSide
	    						+ "$Select$NameSuperSingle$BottomGrp$RestDataGrp$txt_Data1*GEOM*TEXT SET "
	    						+ lowerThird.getSubTitle() + "\0",print_writers);
						
					}else {
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$All$DataAll$Side" + whichSide
	    						+ "$Select$NameSuperSingle$BottomGrp$RestDataGrp$txt_Data1*GEOM*TEXT SET "
	    						+ lowerThird.getLeftText()[0].toUpperCase() + "\0",print_writers);
					}
	            	break;
	            case "d":
	            	CricketFunctions.DoadWriteCommandToAllViz(
	    					"-1 RENDERER*FRONT_LAYER*TREE*$LT$All$DataAll$Side" + whichSide + "$Select$MatchId$BottomGrp$RestDataGrp$txt_Data1*GEOM*TEXT SET "
	    							+ lowerThird.getHeaderText().split("NEED")[0] + " NEED" + "\0",print_writers);
	    			CricketFunctions.DoadWriteCommandToAllViz(
							"-1 RENDERER*FRONT_LAYER*TREE*$LT$All$DataAll$Side" + whichSide + "$Select$MatchId$BottomGrp$RestDataGrp$txt_Data2*GEOM*TEXT SET "
									+ lowerThird.getHeaderText().split("NEED")[1] + " " + lowerThird.getLeftText()[0] + "\0",print_writers);
	            	break;
				}
				break;
	        case Constants.TRI_SERIES:  case Constants.MT20:
	            switch (whatToProcess) {
	            case "Alt_F1": case "Alt_F2":
	            CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$Position_With_Graphics$SublineGrp$Rows$Side" + whichSide +
						"$select_Subline$2$Title*ACTIVE SET 0 \0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$Position_With_Graphics$SublineGrp$Rows$Side" + whichSide +
						"$select_Subline$3$Title*ACTIVE SET 1 \0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$Position_With_Graphics$SublineGrp$Rows$Side" + whichSide +
						"$select_Subline$3$Stat*ACTIVE SET 0 \0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$Position_With_Graphics$SublineGrp$Rows$Side" + whichSide +
						"$select_Subline$4$Title*ACTIVE SET 0 \0", print_writers);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$Position_With_Graphics$SublineGrp$Rows$Side" + whichSide +
						"$select_Subline$1$Left*ACTIVE SET 0 \0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$Position_With_Graphics$SublineGrp$Rows$Side" + whichSide +
						"$select_Subline$2$Left*ACTIVE SET 0 \0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$Position_With_Graphics$SublineGrp$Rows$Side" + whichSide +
						"$select_Subline$3$Left*ACTIVE SET 0 \0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$Position_With_Graphics$SublineGrp$Rows$Side" + whichSide +
						"$select_Subline$4$Left*ACTIVE SET 0 \0", print_writers);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$Position_With_Graphics$SublineGrp$Rows$Side" + whichSide +
						"$select_Subline$1$Right*ACTIVE SET 0 \0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$Position_With_Graphics$SublineGrp$Rows$Side" + whichSide +
						"$select_Subline$2$Right*ACTIVE SET 0 \0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$Position_With_Graphics$SublineGrp$Rows$Side" + whichSide +
						"$select_Subline$3$Right*ACTIVE SET 0 \0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$Position_With_Graphics$SublineGrp$Rows$Side" + whichSide +
						"$select_Subline$4$Right*ACTIVE SET 0 \0", print_writers);
				
				if(griff.size() <= 3) {
					for(int i=0; i<l3griff.getTopTitlesText().length; i++) {
						
//						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$SublineGrp$Side" + whichSide 
//    							+ "$select_Subline$1$Title$txt_" + (i+1) + "*GEOM*TEXT SET " + "v " + l3griff.getTopTitlesText()[i] + "\0", print_writers);
						
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$SublineGrp$Side" + whichSide 
    							+ "$select_Subline$1$Title$txt_" + (i+1) + "*GEOM*TEXT SET " + l3griff.getTopTitlesText()[i] + "\0", print_writers);
    						
						if(l3griff.getTopStatsText()[i].contains(",")) {
							
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$SublineGrp$Side" + whichSide 
	    							+ "$select_Subline$2$Stat$txt_" + (i+1) + "*GEOM*TEXT SET " + l3griff.getTopStatsText()[i].split(",")[0] + " (" + 
	    							l3griff.getTopStatsText()[i].split(",")[1] + ")" + "\0", print_writers);
						}else {
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$SublineGrp$Side" + whichSide 
	    							+ "$select_Subline$2$Stat$txt_" + (i+1) + "*GEOM*TEXT SET " + l3griff.getTopStatsText()[i] + "\0", print_writers);
						}
					}
				}else {
					for(int i=0; i<l3griff.getTopTitlesText().length; i++) {
//						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$SublineGrp$Side" + whichSide 
//    							+ "$select_Subline$1$Title$txt_" + (i+1) + "*GEOM*TEXT SET " + "v " + l3griff.getTopTitlesText()[i] + "\0", print_writers);
						
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$SublineGrp$Side" + whichSide 
    							+ "$select_Subline$1$Title$txt_" + (i+1) + "*GEOM*TEXT SET " + l3griff.getTopTitlesText()[i] + "\0", print_writers);
    					
						if(l3griff.getTopStatsText()[i].contains(",")) {
							
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$SublineGrp$Side" + whichSide 
	    							+ "$select_Subline$2$Stat$txt_" + (i+1) + "*GEOM*TEXT SET " + l3griff.getTopStatsText()[i].split(",")[0] + " (" + 
	    							l3griff.getTopStatsText()[i].split(",")[1] + ")" + "\0", print_writers);
						}else {
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$SublineGrp$Side" + whichSide 
	    							+ "$select_Subline$2$Stat$txt_" + (i+1) + "*GEOM*TEXT SET " + l3griff.getTopStatsText()[i] + "\0", print_writers);
							
						}
					}
					
					for(int i=0; i<l3griff.getBottomTitlesText().length; i++) {
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$SublineGrp$Side" + whichSide 
    							+ "$select_Subline$3$Title$txt_" + (i+1) + "*GEOM*TEXT SET " + "v " + l3griff.getBottomTitlesText()[i] + "\0", print_writers);
    					
						if(l3griff.getBottomStatsText()[i].contains(",")) {
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$SublineGrp$Side" + whichSide 
	    							+ "$select_Subline$4$Stat$txt_" + (i+1) + "*GEOM*TEXT SET " + l3griff.getBottomStatsText()[i].split(",")[0] + " (" + 
	    							l3griff.getBottomStatsText()[i].split(",")[1] + ")" + "\0", print_writers);
							
						}else {
							
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$SublineGrp$Side" + whichSide 
	    							+ "$select_Subline$4$Stat$txt_" + (i+1) + "*GEOM*TEXT SET " + l3griff.getBottomStatsText()[i] + "\0", print_writers);
							
						}
					}
				}
				break;
	            case "Control_F5": case "Control_F9":
					
					switch (config.getBroadcaster().toUpperCase()) {
					case Constants.TRI_SERIES:  case Constants.MT20:
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$Position_With_Graphics$SublineGrp$Rows$Side" + whichSide +
								"$select_Subline$2$Title*ACTIVE SET 0 \0", print_writers);
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$Position_With_Graphics$SublineGrp$Rows$Side" + whichSide +
								"$select_Subline$3$Title*ACTIVE SET 0 \0", print_writers);
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$Position_With_Graphics$SublineGrp$Rows$Side" + whichSide +
								"$select_Subline$4$Title*ACTIVE SET 0 \0", print_writers);
						
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$Position_With_Graphics$SublineGrp$Rows$Side" + whichSide +
								"$select_Subline$1$Left$txt_1*GEOM*TEXT SET " + lowerThird.getLeftText()[0] + "\0", print_writers);
						
						break;
					}
					
					switch (whatToProcess) {
					case "Control_F9":
						switch (config.getBroadcaster().toUpperCase()) {
							case Constants.TRI_SERIES:  case Constants.MT20:
								CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$Position_With_Graphics$SublineGrp$Side" + whichSide 
										+ "$select_Subline$1$Right*TRANSFORMATION*POSITION*X SET 190\0", print_writers);
								
								if(!lowerThird.getRightText()[0].equalsIgnoreCase("WITHOUTEND")) {
									CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$Position_With_Graphics$SublineGrp$Side" + whichSide 
											+ "$select_Subline$1$Right$txt_1*GEOM*TEXT SET " + lowerThird.getRightText()[0] + " END" + "\0", print_writers);
								}else {
									CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$Position_With_Graphics$SublineGrp$Side" + whichSide 
											+ "$select_Subline$1$Right$txt_1*GEOM*TEXT SET " +  "" + "\0", print_writers);
								}
								break;
			
						}
						break;
					}
					
					break;
	        	case "Shift_F6":
					switch (config.getBroadcaster().toUpperCase()) {
					
					case Constants.TRI_SERIES:  case Constants.MT20:
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$SublineGrp$Side" + whichSide +
    							"$select_Subline$2$Right*ACTIVE SET 0 \0", print_writers);
    					
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$Position_With_Graphics$SublineGrp$Rows$Side" + whichSide +
								"$select_Subline$2$Title*ACTIVE SET 0 \0", print_writers);
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$Position_With_Graphics$SublineGrp$Rows$Side" + whichSide +
								"$select_Subline$3$Title*ACTIVE SET 0 \0", print_writers);
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$Position_With_Graphics$SublineGrp$Rows$Side" + whichSide +
								"$select_Subline$4$Title*ACTIVE SET 0 \0", print_writers);
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$Position_With_Graphics$SublineGrp$Rows$Side" + whichSide +
								"$select_Subline$1$Left$txt_1*GEOM*TEXT SET " + "FOURS " + lowerThird.getLeftText()[0] + "      SIXES " + 
								lowerThird.getLeftText()[1] + "\0", print_writers);
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$Position_With_Graphics$SublineGrp$Rows$Side" + whichSide +
								"$select_Subline$1$Right$txt_1*GEOM*TEXT SET " + "STRIKE RATE " + lowerThird.getRightText()[0] + "\0", print_writers);
						
						break;
					}
				 break;	
					
	            case "F6": case "Control_F6":
					switch (config.getBroadcaster().toUpperCase()) {
					
					case Constants.TRI_SERIES:  case Constants.MT20:
						
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$SublineGrp$Side" + whichSide +
    							"$select_Subline$1$Right*ACTIVE SET 0 \0", print_writers);
    					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$SublineGrp$Side" + whichSide +
    							"$select_Subline$2$Right*ACTIVE SET 0 \0", print_writers);
    					
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$Position_With_Graphics$SublineGrp$Rows$Side" + whichSide +
								"$select_Subline$2$Title*ACTIVE SET 0 \0", print_writers);
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$Position_With_Graphics$SublineGrp$Rows$Side" + whichSide +
								"$select_Subline$3$Title*ACTIVE SET 0 \0", print_writers);
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$Position_With_Graphics$SublineGrp$Rows$Side" + whichSide +
								"$select_Subline$4$Title*ACTIVE SET 0 \0", print_writers);
						
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$Position_With_Graphics$SublineGrp$Rows$Side" + whichSide +
								"$select_Subline$1$Left$txt_1*GEOM*TEXT SET " + lowerThird.getLeftText()[0] + "\0", print_writers);
					    CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$Position_With_Graphics$SublineGrp$Rows$Side" + whichSide +
								"$select_Subline$2$Left$txt_1*GEOM*TEXT SET " + "FOURS  " + lowerThird.getLeftText()[1] + "                           SIXES  " +
								 lowerThird.getLeftText()[2] + "                           DOTS  " + lowerThird.getLeftText()[3] + "                           STRIKE RATE  " + 
								 lowerThird.getLeftText()[4]  + "\0", print_writers);
						
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$Position_With_Graphics$SublineGrp$Rows$Side" + whichSide +
								"$select_Subline$1$Left$txt_1*GEOM*TEXT SET " + lowerThird.getLeftText()[0] + "\0", print_writers);
						
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$Position_With_Graphics$SublineGrp$Rows$Side" + whichSide +
								"$select_Subline$1$Right$txt_1*GEOM*TEXT SET " + "" + "\0", print_writers);
						break;
					}
					break;
					
					case "F10":
//						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$SublineGrp$Side" + whichSide +
//    							"$select_Subline$1$Right*ACTIVE SET 0 \0", print_writers);
//						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$SublineGrp$Side" + whichSide +
//    							"$select_Subline$2$Right*ACTIVE SET 0 \0", print_writers);
//						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$SublineGrp$Side" + whichSide +
//    							"$select_Subline$3$Right*ACTIVE SET 0 \0", print_writers);
//						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$SublineGrp$Side" + whichSide +
//    							"$select_Subline$4$Right*ACTIVE SET 0 \0", print_writers);
//						
//						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$SublineGrp$Side" + whichSide +
//    							"$select_Subline$2$Left*ACTIVE SET 0 \0", print_writers);
//						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$SublineGrp$Side" + whichSide +
//    							"$select_Subline$3$Left*ACTIVE SET 0 \0", print_writers);
//						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$SublineGrp$Side" + whichSide +
//    							"$select_Subline$4$Left*ACTIVE SET 0 \0", print_writers);
//						
//						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$Position_With_Graphics$SublineGrp$Rows$Side" + whichSide +
//								"$select_Subline$1$Stat*ACTIVE SET 0 \0", print_writers);
//						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$Position_With_Graphics$SublineGrp$Rows$Side" + whichSide +
//								"$select_Subline$2$Stat*ACTIVE SET 0 \0", print_writers);
//						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$Position_With_Graphics$SublineGrp$Rows$Side" + whichSide +
//								"$select_Subline$3$Stat*ACTIVE SET 0 \0", print_writers);
//						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$Position_With_Graphics$SublineGrp$Rows$Side" + whichSide +
//								"$select_Subline$4$Stat*ACTIVE SET 0 \0", print_writers);
//						
//						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$Position_With_Graphics$SublineGrp$Rows$Side" + whichSide +
//								"$select_Subline$1$Title*ACTIVE SET 0 \0", print_writers);
//						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$Position_With_Graphics$SublineGrp$Rows$Side" + whichSide +
//								"$select_Subline$2$Title*ACTIVE SET 0 \0", print_writers);
//						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$Position_With_Graphics$SublineGrp$Rows$Side" + whichSide +
//								"$select_Subline$3$Title*ACTIVE SET 0 \0", print_writers);
//						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$Position_With_Graphics$SublineGrp$Rows$Side" + whichSide +
//								"$select_Subline$4$Title*ACTIVE SET 0 \0", print_writers);
//						
//						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$SublineGrp$Side" + whichSide +
//    							"$select_Subline$1$Left*ACTIVE SET 1 \0", print_writers);
						
	            		CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_NameSuper$TopLine$img_Text1$Side" + whichSide 
    							+ "$txt_Designation*GEOM*TEXT SET " + lowerThird.getLeftText()[0] + "\0", print_writers);
	            		
//	            		CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$Position_With_Graphics$SublineGrp$Rows$Side" + whichSide +
//								"$select_Subline$1$Left$txt_1*GEOM*TEXT SET " + lowerThird.getLeftText()[0].toUpperCase() + "\0", print_writers);
						
	            	break;
	                case "Control_a":
	                	CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$SublineGrp$Side" + whichSide +
    							"$select_Subline$1$Right*ACTIVE SET 0 \0", print_writers);
    					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$SublineGrp$Side" + whichSide +
    							"$select_Subline$2$Right*ACTIVE SET 0 \0", print_writers);
    					
    					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$SublineGrp$Side" + whichSide +
    							"$select_Subline$2$Title*ACTIVE SET 0 \0", print_writers);
    					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$SublineGrp$Side" + whichSide +
    							"$select_Subline$3$Title*ACTIVE SET 0 \0", print_writers);
    					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$SublineGrp$Side" + whichSide +
    							"$select_Subline$4$Title*ACTIVE SET 0 \0", print_writers);
    					
    					for(int iStat = 0; iStat < lowerThird.getTitlesText().length; iStat++) {
    						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$SublineGrp$Side" + whichSide 
    							+ "$select_Subline$1$Title$txt_" + (iStat + 1) + "*GEOM*TEXT SET " + lowerThird.getTitlesText()[iStat] + "\0", print_writers);
    						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$SublineGrp$Side" + whichSide 
    							+ "$select_Subline$2$Stat$txt_" + (iStat + 1) + "*GEOM*TEXT SET " + lowerThird.getStatsText()[iStat] + "\0", print_writers);
    					}
    					
    					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$Position_With_Graphics$SublineGrp$Rows$Side" + whichSide +
								"$select_Subline$1$Left$txt_1*GEOM*TEXT SET " + lowerThird.getLeftText()[0] + "\0", print_writers);
    					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$Position_With_Graphics$SublineGrp$Rows$Side" + whichSide +
								"$select_Subline$2$Left$txt_1*GEOM*TEXT SET " + lowerThird.getLeftText()[1] + "\0", print_writers);
						
	                    break; 
	                case "F8": case "Alt_F8":
	    				switch (config.getBroadcaster().toUpperCase()) {
	    					case Constants.TRI_SERIES:  case Constants.MT20:
//	    						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$SublineGrp$Side" + whichSide +
//	        							"$select_Subline$1$Right*ACTIVE SET 0 \0", print_writers);
//	    						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$SublineGrp$Side" + whichSide +
//	        							"$select_Subline$2$Right*ACTIVE SET 0 \0", print_writers);
//	    						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$SublineGrp$Side" + whichSide +
//	        							"$select_Subline$3$Right*ACTIVE SET 0 \0", print_writers);
//	    						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$SublineGrp$Side" + whichSide +
//	        							"$select_Subline$4$Right*ACTIVE SET 0 \0", print_writers);
//	    						
//	    						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$SublineGrp$Side" + whichSide +
//	        							"$select_Subline$2$Left*ACTIVE SET 0 \0", print_writers);
//	    						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$SublineGrp$Side" + whichSide +
//	        							"$select_Subline$3$Left*ACTIVE SET 0 \0", print_writers);
//	    						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$SublineGrp$Side" + whichSide +
//	        							"$select_Subline$4$Left*ACTIVE SET 0 \0", print_writers);
//	    						
//	    						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$Position_With_Graphics$SublineGrp$Rows$Side" + whichSide +
//	    								"$select_Subline$1$Stat*ACTIVE SET 0 \0", print_writers);
//	    						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$Position_With_Graphics$SublineGrp$Rows$Side" + whichSide +
//	    								"$select_Subline$2$Stat*ACTIVE SET 0 \0", print_writers);
//	    						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$Position_With_Graphics$SublineGrp$Rows$Side" + whichSide +
//	    								"$select_Subline$3$Stat*ACTIVE SET 0 \0", print_writers);
//	    						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$Position_With_Graphics$SublineGrp$Rows$Side" + whichSide +
//	    								"$select_Subline$4$Stat*ACTIVE SET 0 \0", print_writers);
//	    						
//	    						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$Position_With_Graphics$SublineGrp$Rows$Side" + whichSide +
//	    								"$select_Subline$1$Title*ACTIVE SET 0 \0", print_writers);
//	    						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$Position_With_Graphics$SublineGrp$Rows$Side" + whichSide +
//	    								"$select_Subline$2$Title*ACTIVE SET 0 \0", print_writers);
//	    						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$Position_With_Graphics$SublineGrp$Rows$Side" + whichSide +
//	    								"$select_Subline$3$Title*ACTIVE SET 0 \0", print_writers);
//	    						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$Position_With_Graphics$SublineGrp$Rows$Side" + whichSide +
//	    								"$select_Subline$4$Title*ACTIVE SET 0 \0", print_writers);
//	    						
//	    						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$SublineGrp$Side" + whichSide +
//	        							"$select_Subline$1$Left*ACTIVE SET 1 \0", print_writers);
	    						
		    					if(lowerThird.getLeftText()[0].equalsIgnoreCase("Captain") || lowerThird.getLeftText()[0].equalsIgnoreCase("Captain Wicket-Keeper") ||
		    							lowerThird.getLeftText()[0].equalsIgnoreCase("Wicket-Keeper")) {
		    						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_NameSuper$TopLine$img_Text1$Side" + whichSide 
			    							+ "$txt_Designation*GEOM*TEXT SET " + lowerThird.getSubTitle() + " " + lowerThird.getLeftText()[0].toUpperCase() + "\0", print_writers);
//		    						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$Position_With_Graphics$SublineGrp$Rows$Side" + whichSide +
//		    								"$select_Subline$1$Left$txt_1*GEOM*TEXT SET " + lowerThird.getSubTitle() + " " + lowerThird.getLeftText()[0].toUpperCase() + "\0", print_writers);
		    						
		    					}else if(lowerThird.getLeftText()[0].equalsIgnoreCase("Team")) {
		    						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_NameSuper$TopLine$img_Text1$Side" + whichSide 
			    							+ "$txt_Designation*GEOM*TEXT SET " + lowerThird.getSubTitle() + "\0", print_writers);
//		    						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$Position_With_Graphics$SublineGrp$Rows$Side" + whichSide +
//		    								"$select_Subline$1$Left$txt_1*GEOM*TEXT SET " + lowerThird.getSubTitle() + "\0", print_writers);
		    						
		    					}else {
		    						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_NameSuper$TopLine$img_Text1$Side" + whichSide 
			    							+ "$txt_Designation*GEOM*TEXT SET " + lowerThird.getLeftText()[0].toUpperCase() + "\0", print_writers);
//		    						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$Position_With_Graphics$SublineGrp$Rows$Side" + whichSide +
//		    								"$select_Subline$1$Left$txt_1*GEOM*TEXT SET " + lowerThird.getLeftText()[0].toUpperCase() + "\0", print_writers);
		    						
		    					}
	    					break;
	    				}
	    			break;
	                case "F7": case "F11": case "Control_s": case "Control_f":
	    				switch (config.getBroadcaster().toUpperCase()) {
	    				case Constants.TRI_SERIES:  case Constants.MT20:
	    					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$SublineGrp$Side" + whichSide +
	    							"$select_Subline$1$Right*ACTIVE SET 0 \0", print_writers);
	    					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$SublineGrp$Side" + whichSide +
	    							"$select_Subline$2$Right*ACTIVE SET 0 \0", print_writers);
	    					
	    					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$SublineGrp$Side" + whichSide +
	    							"$select_Subline$2$Title*ACTIVE SET 0 \0", print_writers);
	    					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$SublineGrp$Side" + whichSide +
	    							"$select_Subline$3$Title*ACTIVE SET 0 \0", print_writers);
	    					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$SublineGrp$Side" + whichSide +
	    							"$select_Subline$4$Title*ACTIVE SET 0 \0", print_writers);
	    					
	    					for(int iStat = 0; iStat < lowerThird.getTitlesText().length; iStat++) {
	    						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$SublineGrp$Side" + whichSide 
	    							+ "$select_Subline$1$Title$txt_" + (iStat + 1) + "*GEOM*TEXT SET " + lowerThird.getTitlesText()[iStat] + "\0", print_writers);
	    						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$SublineGrp$Side" + whichSide 
	    							+ "$select_Subline$2$Stat$txt_" + (iStat + 1) + "*GEOM*TEXT SET " + lowerThird.getStatsText()[iStat] + "\0", print_writers);
	    					}
	    					break;
	    				}
	    				break;
	                case "F5":
	    				switch (config.getBroadcaster().toUpperCase()) {
	    				case Constants.TRI_SERIES:  case Constants.MT20:
	    					
	    					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$SublineGrp$Side" + whichSide +
	    							"$select_Subline$1$Right*ACTIVE SET 0 \0", print_writers);
	    					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$SublineGrp$Side" + whichSide +
	    							"$select_Subline$2$Right*ACTIVE SET 0 \0", print_writers);
	    					
	    					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$SublineGrp$Side" + whichSide +
	    							"$select_Subline$2$Title*ACTIVE SET 0 \0", print_writers);
	    					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$SublineGrp$Side" + whichSide +
	    							"$select_Subline$3$Title*ACTIVE SET 0 \0", print_writers);
	    					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$SublineGrp$Side" + whichSide +
	    							"$select_Subline$4$Title*ACTIVE SET 0 \0", print_writers);
	    					
	    					for(int iStat = 0; iStat < lowerThird.getTitlesText().length; iStat++) {
	    						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$SublineGrp$Side" + whichSide 
	    							+ "$select_Subline$1$Title$txt_" + (iStat + 1) + "*GEOM*TEXT SET " + lowerThird.getTitlesText()[iStat] + "\0", print_writers);
	    						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$SublineGrp$Side" + whichSide 
	    							+ "$select_Subline$2$Stat$txt_" + (iStat + 1) + "*GEOM*TEXT SET " + lowerThird.getStatsText()[iStat] + "\0", print_writers);
	    					}
	    					break;
	    				}
	    				
	    				break;
	    				
	    			case "F9":
	    				switch (config.getBroadcaster().toUpperCase()) {
	    				case Constants.TRI_SERIES:  case Constants.MT20:
	    					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$SublineGrp$Side" + whichSide +
	    							"$select_Subline$1$Right*ACTIVE SET 0 \0", print_writers);
	    					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$SublineGrp$Side" + whichSide +
	    							"$select_Subline$2$Right*ACTIVE SET 0 \0", print_writers);
	    					
	    					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$SublineGrp$Side" + whichSide +
	    							"$select_Subline$2$Title*ACTIVE SET 0 \0", print_writers);
	    					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$SublineGrp$Side" + whichSide +
	    							"$select_Subline$3$Title*ACTIVE SET 0 \0", print_writers);
	    					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$SublineGrp$Side" + whichSide +
	    							"$select_Subline$4$Title*ACTIVE SET 0 \0", print_writers);
	    					
	    					for(int iStat = 0; iStat < lowerThird.getTitlesText().length; iStat++) {
	    						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$SublineGrp$Side" + whichSide 
	    							+ "$select_Subline$1$Title$txt_" + (iStat + 1) + "*GEOM*TEXT SET " + lowerThird.getTitlesText()[iStat] + "\0", print_writers);
	    						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$SublineGrp$Side" + whichSide 
	    							+ "$select_Subline$2$Stat$txt_" + (iStat + 1) + "*GEOM*TEXT SET " + lowerThird.getStatsText()[iStat] + "\0", print_writers);
	    					}
	    					break;
	    				}
	    				
	    				break;
	    				
	    			case "u": case "Shift_F3":
	    				switch (config.getBroadcaster().toUpperCase()) {
	    				case Constants.TRI_SERIES:  case Constants.MT20:
	    					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$SublineGrp$Side" + whichSide +
	    							"$select_Subline$1$Right*ACTIVE SET 0 \0", print_writers);
	    					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$SublineGrp$Side" + whichSide +
	    							"$select_Subline$2$Right*ACTIVE SET 0 \0", print_writers);
	    					
	    					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$SublineGrp$Side" + whichSide +
	    							"$select_Subline$2$Title*ACTIVE SET 0 \0", print_writers);
	    					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$SublineGrp$Side" + whichSide +
	    							"$select_Subline$3$Title*ACTIVE SET 0 \0", print_writers);
	    					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$SublineGrp$Side" + whichSide +
	    							"$select_Subline$4$Title*ACTIVE SET 0 \0", print_writers);
	    					
	    					for(int i=0; i<10; i++) {
	    						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$SublineGrp$Side" + whichSide 
		    							+ "$select_Subline$1$Title$txt_" + (i+1) + "*GEOM*TEXT SET \0", print_writers);
	    					}
	    					
	    					for(int i=0; i<10; i++) {
	    						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$SublineGrp$Side" + whichSide 
		    							+ "$select_Subline$2$Stat$txt_" + (i+1) + "*FUNCTION*Maxsize*DEFAULT_SCALE_X SET 0.23 \0", print_writers);
	    					}
	    					
	    					for(int iStat = 0; iStat < lowerThird.getTitlesText().length; iStat++) {
	    						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$SublineGrp$Side" + whichSide 
	    							+ "$select_Subline$1$Title$txt_" + (iStat + 1) + "*GEOM*TEXT SET " + lowerThird.getTitlesText()[iStat] + "\0", print_writers);
	    						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$SublineGrp$Side" + whichSide 
	    							+ "$select_Subline$2$Stat$txt_" + (iStat + 1) + "*GEOM*TEXT SET " + lowerThird.getStatsText()[iStat] + "\0", print_writers);
	    					}
	    					
	    					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$Position_With_Graphics$SublineGrp$Rows$Side" + whichSide +
									"$select_Subline$1$Left$txt_1*GEOM*TEXT SET " + lowerThird.getLeftText()[0] + "\0", print_writers);
	    					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$Position_With_Graphics$SublineGrp$Rows$Side" + whichSide +
									"$select_Subline$2$Left$txt_1*GEOM*TEXT SET " + lowerThird.getLeftText()[1] + "\0", print_writers);
	    					break;
	    				}
	    				
	    				break;
	    			case "Shift_F5": case "Shift_F9": case "Alt_F12":
	    				switch (config.getBroadcaster().toUpperCase()) {
	    				case Constants.TRI_SERIES:  case Constants.MT20:
	    					
	    					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$SublineGrp$Side" + whichSide +
	    							"$select_Subline$1$Right*ACTIVE SET 0 \0", print_writers);
	    					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$SublineGrp$Side" + whichSide +
	    							"$select_Subline$2$Right*ACTIVE SET 0 \0", print_writers);
	    					
	    					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$SublineGrp$Side" + whichSide +
	    							"$select_Subline$2$Title*ACTIVE SET 0 \0", print_writers);
	    					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$SublineGrp$Side" + whichSide +
	    							"$select_Subline$3$Title*ACTIVE SET 0 \0", print_writers);
	    					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$SublineGrp$Side" + whichSide +
	    							"$select_Subline$4$Title*ACTIVE SET 0 \0", print_writers);
	    					
	    					for(int iStat = 0; iStat < lowerThird.getTitlesText().length; iStat++) {
	    						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$SublineGrp$Side" + whichSide 
	    							+ "$select_Subline$1$Title$txt_" + (iStat + 1) + "*GEOM*TEXT SET " + lowerThird.getTitlesText()[iStat] + "\0", print_writers);
	    						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$SublineGrp$Side" + whichSide 
	    							+ "$select_Subline$2$Stat$txt_" + (iStat + 1) + "*GEOM*TEXT SET " + lowerThird.getStatsText()[iStat] + "\0", print_writers);
	    					}
	    					break;
	    				}
	    				
	    				break;
	    			case "Shift_E":
	    				switch (config.getBroadcaster().toUpperCase()) {
	    				case Constants.TRI_SERIES:  case Constants.MT20:
	    					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$SublineGrp$Side" + whichSide +
	    							"$select_Subline$1$Right*ACTIVE SET 0 \0", print_writers);
	    					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$SublineGrp$Side" + whichSide +
	    							"$select_Subline$2$Right*ACTIVE SET 0 \0", print_writers);
	    					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$SublineGrp$Side" + whichSide +
	    							"$select_Subline$3$Right*ACTIVE SET 0 \0", print_writers);
	    					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$SublineGrp$Side" + whichSide +
	    							"$select_Subline$4$Right*ACTIVE SET 0 \0", print_writers);
	    					
	    					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$SublineGrp$Side" + whichSide +
	    							"$select_Subline$2$Title*ACTIVE SET 0 \0", print_writers);
	    					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$SublineGrp$Side" + whichSide +
	    							"$select_Subline$3$Title*ACTIVE SET 0 \0", print_writers);
	    					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$SublineGrp$Side" + whichSide +
	    							"$select_Subline$4$Title*ACTIVE SET 0 \0", print_writers);
	    					
	    					if(lowerThird.getSubTitle().equalsIgnoreCase("1") || lowerThird.getSubTitle().equalsIgnoreCase("2")) {
	    						for(int iStat = 0; iStat < lowerThird.getTitlesText().length; iStat++) {
		    						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$SublineGrp$Side" + whichSide 
		    							+ "$select_Subline$1$Title$txt_" + (iStat + 1) + "*GEOM*TEXT SET " + lowerThird.getTitlesText()[iStat] + "\0", print_writers);
		    						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$SublineGrp$Side" + whichSide 
		    							+ "$select_Subline$2$Stat$txt_" + (iStat + 1) + "*GEOM*TEXT SET " + lowerThird.getStatsText()[iStat] + "\0", print_writers);
		    					}
	    					}else {
	    						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$SublineGrp$Side" + whichSide +
		    							"$select_Subline$1$Left*ACTIVE SET 0 \0", print_writers);
		    					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$SublineGrp$Side" + whichSide +
		    							"$select_Subline$2$Left*ACTIVE SET 0 \0", print_writers);
		    					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$SublineGrp$Side" + whichSide +
		    							"$select_Subline$3$Left*ACTIVE SET 0 \0", print_writers);
		    					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$SublineGrp$Side" + whichSide +
		    							"$select_Subline$4$Left*ACTIVE SET 0 \0", print_writers);
		    					
	    						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$Position_With_Graphics$SublineGrp$Rows$Side" + whichSide +
										"$select_Subline$2$Left$txt_1*GEOM*TEXT SET " + "TOTAL" + "\0", print_writers);
	    						
	    						for(int iStat = 0; iStat < lowerThird.getTitlesText().length; iStat++) {
		    						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$SublineGrp$Side" + whichSide 
		    							+ "$select_Subline$1$Title$txt_" + (iStat + 1) + "*GEOM*TEXT SET " + lowerThird.getTitlesText()[iStat] + "\0", print_writers);
		    						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$SublineGrp$Side" + whichSide 
		    							+ "$select_Subline$2$Stat$txt_" + (iStat + 1) + "*GEOM*TEXT SET " + lowerThird.getStatsText()[iStat].split(",")[0] + "\0", print_writers);
		    						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$SublineGrp$Side" + whichSide 
		    							+ "$select_Subline$3$Stat$txt_" + (iStat + 1) + "*GEOM*TEXT SET " + lowerThird.getStatsText()[iStat].split(",")[1] + "\0", print_writers);
		    						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$SublineGrp$Side" + whichSide 
		    							+ "$select_Subline$4$Stat$txt_" + (iStat + 1) + "*GEOM*TEXT SET " + lowerThird.getStatsText()[iStat].split(",")[2] + "\0", print_writers);
		    					}
	    						
	    						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$Position_With_Graphics$SublineGrp$Rows$Side" + whichSide +
										"$select_Subline$2$Left$txt_1*GEOM*TEXT SET " + lowerThird.getLeftText()[0] + "\0", print_writers);
		    					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$Position_With_Graphics$SublineGrp$Rows$Side" + whichSide +
										"$select_Subline$3$Left$txt_1*GEOM*TEXT SET " + lowerThird.getLeftText()[1] + "\0", print_writers);
		    					
	    					}
	    					
	    					break;
	    				}
	    				break;
	    			case "e": case "d":
	    				switch (config.getBroadcaster().toUpperCase()) {
	    				case Constants.TRI_SERIES:  case Constants.MT20:
	    					
	    					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$SublineGrp$Side" + whichSide +
	    							"$select_Subline$1$Right*ACTIVE SET 0 \0", print_writers);
	    					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$SublineGrp$Side" + whichSide +
	    							"$select_Subline$1$Title*ACTIVE SET 0 \0", print_writers);
	    					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$SublineGrp$Side" + whichSide +
	    							"$select_Subline$1$Stat*ACTIVE SET 0 \0", print_writers);
	    					
    						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$Position_With_Graphics$SublineGrp$Rows$Side" + whichSide +
									"$select_Subline$1$Left$txt_1*GEOM*TEXT SET NEED " + lowerThird.getHeaderText().split("NEED")[1] + " " + lowerThird.getLeftText()[0] + lowerThird.getLeftText()[1] + "\0", print_writers);
	    						
	    					break;
	    				}
	    				break;
	    			case "Control_h":
	    				
	    				switch (config.getBroadcaster().toUpperCase()) {
	    				case Constants.TRI_SERIES:  case Constants.MT20:
	    					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$SublineGrp$Side" + whichSide +
	    							"$select_Subline$1$Right*ACTIVE SET 0 \0", print_writers);
	    					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$SublineGrp$Side" + whichSide +
	    							"$select_Subline$2$Right*ACTIVE SET 0 \0", print_writers);
	    					
	    					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$SublineGrp$Side" + whichSide +
	    							"$select_Subline$2$Title*ACTIVE SET 0 \0", print_writers);
	    					
	    					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$Position_With_Graphics$SublineGrp$Rows$Side" + whichSide +
									"$select_Subline$1$Left$txt_1*GEOM*TEXT SET " + lowerThird.getLeftText()[0] + "\0", print_writers);
	    					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$Position_With_Graphics$SublineGrp$Rows$Side" + whichSide +
									"$select_Subline$2$Left$txt_1*GEOM*TEXT SET " + lowerThird.getLeftText()[1] + "\0", print_writers);
	    					
	    					
	    					for(int i=0; i<lowerThird.getTitlesText().length; i++) {
	    						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$SublineGrp$Side" + whichSide 
		    							+ "$select_Subline$1$Title$txt_" + (i + 1) + "*GEOM*TEXT SET " + lowerThird.getTitlesText()[i] + "\0", print_writers);
	    					}
	    					
	    					//1st Phase
	    					if(Integer.valueOf(lowerThird.getStatsText()[0].split("-")[0]) == 0 && Integer.valueOf(lowerThird.getStatsText()[0].split("-")[1]) == 0) {
	    						if(Float.valueOf(CricketFunctions.OverBalls(inning.getTotalOvers(), inning.getTotalBalls())) > 0.0) {
	    							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$SublineGrp$Side" + whichSide 
	    	    							+ "$select_Subline$2$Stat$txt_1*GEOM*TEXT SET " + "0-0" + "\0", print_writers);
	    						}else {
	    							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$SublineGrp$Side" + whichSide 
	    	    							+ "$select_Subline$2$Stat$txt_1*GEOM*TEXT SET " + "-" + "\0", print_writers);
	    						}
	    					}else {
	    						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$SublineGrp$Side" + whichSide 
		    							+ "$select_Subline$2$Stat$txt_1*GEOM*TEXT SET " + lowerThird.getStatsText()[0].split("-")[0] + "-" + 
	    								lowerThird.getStatsText()[0].split("-")[1] + "\0", print_writers);
	    					}
	    					
	    					//2nd phase
	    					if(Integer.valueOf(lowerThird.getStatsText()[1].split("-")[0]) == 0 && Integer.valueOf(lowerThird.getStatsText()[1].split("-")[1]) == 0) {
	    						if(Float.valueOf(CricketFunctions.OverBalls(inning.getTotalOvers(), inning.getTotalBalls())) > 6.0) {
	    							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$SublineGrp$Side" + whichSide 
	    	    							+ "$select_Subline$2$Stat$txt_2*GEOM*TEXT SET " + "0-0" + "\0", print_writers);
	    						}else {
	    							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$SublineGrp$Side" + whichSide 
	    	    							+ "$select_Subline$2$Stat$txt_2*GEOM*TEXT SET " + "-" + "\0", print_writers);
	    						}
	    					}else {
	    						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$SublineGrp$Side" + whichSide 
		    							+ "$select_Subline$2$Stat$txt_2*GEOM*TEXT SET " + lowerThird.getStatsText()[1].split("-")[0] + "-" + 
	    								lowerThird.getStatsText()[1].split("-")[1] + "\0", print_writers);
	    					}
	    					
	    					//3rd Phase
	    					if(Integer.valueOf(lowerThird.getStatsText()[2].split("-")[0]) == 0 && Integer.valueOf(lowerThird.getStatsText()[2].split("-")[1]) == 0) {
	    						if(Float.valueOf(CricketFunctions.OverBalls(inning.getTotalOvers(), inning.getTotalBalls())) > 15.0) {
	    							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$SublineGrp$Side" + whichSide 
	    	    							+ "$select_Subline$2$Stat$txt_3*GEOM*TEXT SET " + "0-0" + "\0", print_writers);
	    						}else {
	    							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$SublineGrp$Side" + whichSide 
	    	    							+ "$select_Subline$2$Stat$txt_3*GEOM*TEXT SET " + "-" + "\0", print_writers);
	    						}
	    					}else {
	    						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$SublineGrp$Side" + whichSide 
		    							+ "$select_Subline$2$Stat$txt_3*GEOM*TEXT SET " + lowerThird.getStatsText()[2].split("-")[0] + "-" + 
	    								lowerThird.getStatsText()[2].split("-")[1] + "\0", print_writers);
	    					}
	    					
	    					break;
	    				}
	    				break;
	    			case "Alt_Shift_F3":
	    				
	    				switch (config.getBroadcaster().toUpperCase()) {
	    				case Constants.TRI_SERIES:  case Constants.MT20:
	    					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$SublineGrp$Side" + whichSide +
	    							"$select_Subline$1$Right*ACTIVE SET 0 \0", print_writers);
	    					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$SublineGrp$Side" + whichSide +
	    							"$select_Subline$2$Right*ACTIVE SET 0 \0", print_writers);
	    					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$SublineGrp$Side" + whichSide +
	    							"$select_Subline$3$Right*ACTIVE SET 0 \0", print_writers);
	    					
	    					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$SublineGrp$Side" + whichSide +
	    							"$select_Subline$2$Title*ACTIVE SET 0 \0", print_writers);
	    					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$SublineGrp$Side" + whichSide +
	    							"$select_Subline$3$Title*ACTIVE SET 0 \0", print_writers);
	    					
	    					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$Position_With_Graphics$SublineGrp$Rows$Side" + whichSide +
									"$select_Subline$1$Left$txt_1*GEOM*TEXT SET " + lowerThird.getLeftText()[0] + "\0", print_writers);
	    					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$Position_With_Graphics$SublineGrp$Rows$Side" + whichSide +
									"$select_Subline$2$Left$txt_1*GEOM*TEXT SET " + inning.getBatting_team().getTeamName3() + "\0", print_writers);
	    					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$Position_With_Graphics$SublineGrp$Rows$Side" + whichSide +
									"$select_Subline$3$Left$txt_1*GEOM*TEXT SET " + inning2.getBatting_team().getTeamName3() + "\0", print_writers);
	    					
	    					for(int i = 0; i < lowerThird.getTitlesText().length; i++) {
	    					    String title = lowerThird.getTitlesText()[i];
	    					    if(title != null && title.contains("OVERS")) {
	    					        title = title.replace("OVERS", "").trim();
	    					    }
	    					    CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$SublineGrp$Side" + whichSide 
	    					    		+ "$select_Subline$1$Title$txt_" + (i + 1) + "*GEOM*TEXT SET " + title + "\0", print_writers);
	    					}
	    					
	    					//1st Phase
	    					if(Integer.valueOf(lowerThird.getStatsText()[0].split("-")[0].split(",")[0]) == 0 && Integer.valueOf(lowerThird.getStatsText()[0].split("-")[1].split(",")[0]) == 0) {
	    						if(Float.valueOf(CricketFunctions.OverBalls(inning.getTotalOvers(), inning.getTotalBalls())) > 0.0) {
	    							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$SublineGrp$Side" + whichSide 
	    	    							+ "$select_Subline$2$Stat$txt_1*GEOM*TEXT SET " + "0-0" + "\0", print_writers);
	    						}else {
	    							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$SublineGrp$Side" + whichSide 
	    	    							+ "$select_Subline$2$Stat$txt_1*GEOM*TEXT SET " + "-" + "\0", print_writers);
	    						}
	    					}else {
	    						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$SublineGrp$Side" + whichSide 
		    							+ "$select_Subline$2$Stat$txt_1*GEOM*TEXT SET " + lowerThird.getStatsText()[0].split("-")[0].split(",")[0] + "-" + 
	    								lowerThird.getStatsText()[0].split("-")[1].split(",")[0] + "\0", print_writers);
	    					}
	    					
	    					//2nd phase
	    					if(Integer.valueOf(lowerThird.getStatsText()[1].split("-")[0].split(",")[0]) == 0 && Integer.valueOf(lowerThird.getStatsText()[1].split("-")[1].split(",")[0]) == 0) {
	    						if(Float.valueOf(CricketFunctions.OverBalls(inning.getTotalOvers(), inning.getTotalBalls())) > 6.0) {
	    							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$SublineGrp$Side" + whichSide 
	    	    							+ "$select_Subline$2$Stat$txt_2*GEOM*TEXT SET " + "0-0" + "\0", print_writers);
	    						}else {
	    							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$SublineGrp$Side" + whichSide 
	    	    							+ "$select_Subline$2$Stat$txt_2*GEOM*TEXT SET " + "-" + "\0", print_writers);
	    						}
	    					}else {
	    						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$SublineGrp$Side" + whichSide 
		    							+ "$select_Subline$2$Stat$txt_2*GEOM*TEXT SET " + lowerThird.getStatsText()[1].split("-")[0].split(",")[0] + "-" + 
	    								lowerThird.getStatsText()[1].split("-")[1].split(",")[0] + "\0", print_writers);
	    					}
	    					
	    					//3rd Phase
	    					if(Integer.valueOf(lowerThird.getStatsText()[2].split("-")[0].split(",")[0]) == 0 && Integer.valueOf(lowerThird.getStatsText()[2].split("-")[1].split(",")[0]) == 0) {
	    						if(Float.valueOf(CricketFunctions.OverBalls(inning.getTotalOvers(), inning.getTotalBalls())) > 15.0) {
	    							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$SublineGrp$Side" + whichSide 
	    	    							+ "$select_Subline$2$Stat$txt_3*GEOM*TEXT SET " + "0-0" + "\0", print_writers);
	    						}else {
	    							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$SublineGrp$Side" + whichSide 
	    	    							+ "$select_Subline$2$Stat$txt_3*GEOM*TEXT SET " + "-" + "\0", print_writers);
	    						}
	    					}else {
	    						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$SublineGrp$Side" + whichSide 
		    							+ "$select_Subline$2$Stat$txt_3*GEOM*TEXT SET " + lowerThird.getStatsText()[2].split("-")[0].split(",")[0] + "-" + 
	    								lowerThird.getStatsText()[2].split("-")[1].split(",")[0] + "\0", print_writers);
	    					}
	    					
	    					
	    					
	    					// 2 Team
	    					
	    					//1st Phase
	    					if(Integer.valueOf(lowerThird.getStatsText()[0].split("-")[0].split(",")[1]) == 0 && Integer.valueOf(lowerThird.getStatsText()[0].split("-")[1].split(",")[1]) == 0) {
	    						if(Float.valueOf(CricketFunctions.OverBalls(inning2.getTotalOvers(), inning2.getTotalBalls())) > 0.0) {
	    							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$SublineGrp$Side" + whichSide 
	    	    							+ "$select_Subline$3$Stat$txt_1*GEOM*TEXT SET " + "0-0" + "\0", print_writers);
	    						}else {
	    							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$SublineGrp$Side" + whichSide 
	    	    							+ "$select_Subline$3$Stat$txt_1*GEOM*TEXT SET " + "-" + "\0", print_writers);
	    						}
	    					}else {
	    						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$SublineGrp$Side" + whichSide 
		    							+ "$select_Subline$3$Stat$txt_1*GEOM*TEXT SET " + lowerThird.getStatsText()[0].split("-")[0].split(",")[1] + "-" + 
	    								lowerThird.getStatsText()[0].split("-")[1].split(",")[1] + "\0", print_writers);
	    					}
	    					
	    					//2nd phase
	    					if(Integer.valueOf(lowerThird.getStatsText()[1].split("-")[0].split(",")[1]) == 0 && Integer.valueOf(lowerThird.getStatsText()[1].split("-")[1].split(",")[1]) == 0) {
	    						if(Float.valueOf(CricketFunctions.OverBalls(inning2.getTotalOvers(), inning2.getTotalBalls())) > 6.0) {
	    							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$SublineGrp$Side" + whichSide 
	    	    							+ "$select_Subline$3$Stat$txt_2*GEOM*TEXT SET " + "0-0" + "\0", print_writers);
	    						}else {
	    							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$SublineGrp$Side" + whichSide 
	    	    							+ "$select_Subline$3$Stat$txt_2*GEOM*TEXT SET " + "-" + "\0", print_writers);
	    						}
	    					}else {
	    						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$SublineGrp$Side" + whichSide 
		    							+ "$select_Subline$3$Stat$txt_2*GEOM*TEXT SET " + lowerThird.getStatsText()[1].split("-")[0].split(",")[1] + "-" + 
	    								lowerThird.getStatsText()[1].split("-")[1].split(",")[1] + "\0", print_writers);
	    					}
	    					
	    					//3rd Phase
	    					if(Integer.valueOf(lowerThird.getStatsText()[2].split("-")[0].split(",")[1]) == 0 && Integer.valueOf(lowerThird.getStatsText()[2].split("-")[1].split(",")[1]) == 0) {
	    						if(Float.valueOf(CricketFunctions.OverBalls(inning2.getTotalOvers(), inning2.getTotalBalls())) > 15.0) {
	    							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$SublineGrp$Side" + whichSide 
	    	    							+ "$select_Subline$3$Stat$txt_3*GEOM*TEXT SET " + "0-0" + "\0", print_writers);
	    						}else {
	    							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$SublineGrp$Side" + whichSide 
	    	    							+ "$select_Subline$3$Stat$txt_3*GEOM*TEXT SET " + "-" + "\0", print_writers);
	    						}
	    					}else {
	    						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$SublineGrp$Side" + whichSide 
		    							+ "$select_Subline$3$Stat$txt_3*GEOM*TEXT SET " + lowerThird.getStatsText()[2].split("-")[0].split(",")[1] + "-" + 
	    								lowerThird.getStatsText()[2].split("-")[1].split(",")[1] + "\0", print_writers);
	    					}
	    					break;
	    				}
	    				break;
	    			case "Control_F3"://Comparison
	                   switch (config.getBroadcaster().toUpperCase()) {
	                   case Constants.TRI_SERIES:  case Constants.MT20:
	                	   // bowling teamscore
	                	   if(lowerThird.getLeftText()[2].split("-")[1].equalsIgnoreCase("10")) {
	                		   CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Lt_Comparison$Side0" + whichSide + "$DataPart$"
	   	                	   		+ "BottomPart$Text$Score_Grp$HomeTeam$img_Text2$txt_Score*GEOM*TEXT SET " + lowerThird.getLeftText()[2].split("-")[0] + "\0", print_writers); 
	                	   }else {
	                		   CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Lt_Comparison$Side0" + whichSide + "$DataPart$"
	   	                	   		+ "BottomPart$Text$Score_Grp$HomeTeam$img_Text2$txt_Score*GEOM*TEXT SET " + lowerThird.getLeftText()[2] + "\0", print_writers);
	                	   }
	                	   
	                	   if(lowerThird.getLeftText()[5].split("-")[1].equalsIgnoreCase("10")) {
	                		   CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Lt_Comparison$Side0" + whichSide + "$DataPart$"
	   	                	   		+ "BottomPart$Text$Score_Grp$AwayTeam$img_Text2$txt_Score*GEOM*TEXT SET " +  lowerThird.getLeftText()[5].split("-")[0] + "\0", print_writers);
	                	   }else {
	                		   CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Lt_Comparison$Side0" + whichSide + "$DataPart$"
	   	                	   		+ "BottomPart$Text$Score_Grp$AwayTeam$img_Text2$txt_Score*GEOM*TEXT SET " +  lowerThird.getLeftText()[5] + "\0", print_writers);
	                	   }
	                	   //bating score
	                	   
	                	  //middle section
	                	   CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Lt_Comparison$Side0" + whichSide + "$DataPart$"
	                	   		+ "BottomPart$Text$txt_SubTitle*GEOM*TEXT SET " + "AFTER " + lowerThird.getBallsFacedText() + " 	OVERS" + "\0", print_writers);
	                   }
					break;
	    		case "Control_Shift_O":
	  	    			int row_id = 0;
	  	    			String containerNam1 = "",offset="";
	  	    			
	  	    			if(lowerThird.getHeaderText().equalsIgnoreCase("ROLES")) {
	  	    				
	  	    				Collections.sort(inning.getBattingCard());
	  	    				for (Player bc : PlayersList) {
	  	    					row_id = row_id + 1;
	  	    					
	  	    					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT_PlayingXI$Side"+ whichSide + 
	  	    							"$TopLine$img_Text1$" + row_id + "$Bottom$Select*FUNCTION*Omo*vis_con SET 0\0", print_writers);
	  	    					
	  	    					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT_PlayingXI$Side"+ whichSide + "$TopLine$img_Text1$" + row_id + 
	  	    							"$PlayerStatus$Select_Status*FUNCTION*Omo*vis_con SET " + (!PlayerIdIn.contains(bc.getPlayerId())?"2":"0") + "\0", print_writers);
	  	    					
	  	    					containerNam1 = "$Data_Grp";
	  	    					offset = "146.0";
//	  	    					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT_PlayingXI$Data_All$Side"+ whichSide + 
//	  	    							"$Select*FUNCTION*Omo*vis_con SET 0\0", print_writers);

	  	    					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT_PlayingXI$Side" + whichSide + "$TopLine" + 
		  	  							"$img_Text1$" + row_id + "$Pic$img_Base2*TEXTURE*IMAGE SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? 
		  										Constants.TRI_SERIES_BASE2 : Constants.MT20_BASE2) + lowerThird.getWhichTeamFlag() + "\0", print_writers);
		  	  	            	CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT_PlayingXI$Side" + whichSide + "$TopLine" + 
		  	  							"$img_Text1$" + row_id + "$Name_Grp$img_Text2*TEXTURE*IMAGE SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? 
		    									Constants.TRI_SERIES_TEXT2 : Constants.MT20_TEXT2) + lowerThird.getWhichTeamFlag() + "\0", print_writers);
		  	  	            	CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT_PlayingXI$Side" + whichSide + "$TopLine" + 
		  	  							"$img_Text1$" + row_id + "$Captain$img_Text1*TEXTURE*IMAGE SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? 
		    									Constants.TRI_SERIES_TEXT2 : Constants.MT20_TEXT2) + lowerThird.getWhichTeamFlag() + "\0", print_writers);
	  	  	            	
	  	  	            	
		  	  	            	CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT_PlayingXI$Side"+ whichSide + 
  	    							"$TopLine$img_Text1$" + row_id + "$Captain$Select$img_Text1*TEXTURE*IMAGE SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? 
	    									Constants.TRI_SERIES_TEXT2 : Constants.MT20_TEXT2) + lowerThird.getWhichTeamFlag() + "\0", print_writers);
		  	  	            
	  	    					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT_PlayingXI$Side"+ whichSide + 
	  	    							"$TopLine$img_Text1$" + row_id + "$Name_Grp$txt_LastName*GEOM*TEXT SET " + bc.getTicker_name() + "\0", print_writers);
	  	    					
	  	    					
	  	    					if(config.getPrimaryIpAddress().equalsIgnoreCase(Constants.LOCALHOST)) {
	  	    						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT_PlayingXI$Side" + whichSide + "$TopLine$img_Text1$" + row_id + "$Pic$img*TEXTURE*IMAGE SET "
	  	    								+ (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_LOCAL_PHOTO_PATH : Constants.MT20_LOCAL_PHOTO_PATH) 
	  	    								+ "\\\\" + team.getTeamName4() + "\\\\" + Constants.CENTER + "\\\\" + bc.getPhoto()+ CricketUtil.PNG_EXTENSION + "\0", print_writers);
	  	    					}else {
	  	    						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT_PlayingXI$Side" + whichSide + "$TopLine$img_Text1$" + row_id + "$Pic$img*TEXTURE*IMAGE SET "
	  	    								+ "\\\\" + config.getPrimaryIpAddress() + "\\\\" + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_PHOTO_PATH : Constants.MT20_PHOTO_PATH) 
	  	    								+ "\\\\" + team.getTeamName4() + "\\\\" + Constants.CENTER + "\\\\" + bc.getPhoto()
	  	    								+ CricketUtil.PNG_EXTENSION + "\0", print_writers);
	  	    					}
	  	    					
	  	    					
	  	    					if(bc.getRole().equalsIgnoreCase(CricketUtil.BATSMAN) || bc.getRole().equalsIgnoreCase("BATTER") || 
	  	    							bc.getRole().equalsIgnoreCase("BAT/KEEPER")) {
	  	    						if(bc.getBattingStyle().equalsIgnoreCase("RHB")) {
	  	    							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT_PlayingXI$Side"+ whichSide + 
	  	    	    							"$TopLine$img_Text1$" + row_id + "$Bottom$Select$Batsman_Lefthand*TEXTURE*IMAGE SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? 
	  			    									Constants.TRI_SERIES_ICONS : Constants.MT20_ICONS) + "Bat_Icon" + "\0", print_writers);
	  	    						}else if(bc.getBattingStyle().equalsIgnoreCase("LHB")) {
	  	    							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT_PlayingXI$Side"+ whichSide + 
	  	    	    							"$TopLine$img_Text1$" + row_id + "$Bottom$Select$Batsman_Lefthand*TEXTURE*IMAGE SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? 
	  			    									Constants.TRI_SERIES_ICONS : Constants.MT20_ICONS) + "Batsman_Lefthand" + "\0", print_writers);
	  	    						}
	  	    					}else if(bc.getRole().equalsIgnoreCase(CricketUtil.BOWLER)) {
	  	    						if(bc.getBowlingStyle() == null) {
	  	    							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT_PlayingXI$Side"+ whichSide + 
	  	    	    							"$TopLine$img_Text1$" + row_id + "$Bottom$Select$Batsman_Lefthand*TEXTURE*IMAGE SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? 
	  			    									Constants.TRI_SERIES_ICONS : Constants.MT20_ICONS) + "Bowler" + "\0", print_writers);
	  	    						}else {
	  	    							switch (bc.getBowlingStyle().toUpperCase()) {
	  	    							case "RFM": case "RF": case "RMF": case "RM": case "RSM": case "LF": case "LFM": case "LMF": case "LM":
	  	    								CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT_PlayingXI$Side"+ whichSide + 
	  		    	    							"$TopLine$img_Text1$" + row_id + "$Bottom$Select$Batsman_Lefthand*TEXTURE*IMAGE SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? 
		  			    									Constants.TRI_SERIES_ICONS : Constants.MT20_ICONS) + "FastBowler" + "\0", print_writers);
	  	    								break;

	  	    							case "LSL": case "WSL": case "LCH": case "RLG": case "WSR": case "LSO":
	  	    								CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT_PlayingXI$Side"+ whichSide + 
	  		    	    							"$TopLine$img_Text1$" + row_id + "$Bottom$Select$Batsman_Lefthand*TEXTURE*IMAGE SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? 
		  			    									Constants.TRI_SERIES_ICONS : Constants.MT20_ICONS) + "SpinBowler" + "\0", print_writers);
	  	    								break;
	  	    							case "ROB":
	  	    								CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT_PlayingXI$Side"+ whichSide + 
	  		    	    							"$TopLine$img_Text1$" + row_id + "$Bottom$Select$Batsman_Lefthand*TEXTURE*IMAGE SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? 
		  			    									Constants.TRI_SERIES_ICONS : Constants.MT20_ICONS) + "Off_Spin" + "\0", print_writers);
	  	    								break;
	  	    							case "RLB":
	  	    								CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT_PlayingXI$Side"+ whichSide + 
	  		    	    							"$TopLine$img_Text1$" + row_id + "$Bottom$Select$Batsman_Lefthand*TEXTURE*IMAGE SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? 
		  			    									Constants.TRI_SERIES_ICONS : Constants.MT20_ICONS) + "Leg_Spin" + "\0", print_writers);
	  	    								break;	
	  	    							}
	  	    						}
	  	    					}else if(bc.getRole().equalsIgnoreCase(CricketUtil.ALL_ROUNDER)) {
	  	    						if(bc.getBowlingStyle() == null) {
	  	    							if(bc.getBattingStyle().equalsIgnoreCase("LHB")) {
	  	    								CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT_PlayingXI$Side"+ whichSide + 
	  		    	    							"$TopLine$img_Text1$" + row_id + "$Bottom$Select$Batsman_Lefthand*TEXTURE*IMAGE SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? 
		  			    									Constants.TRI_SERIES_ICONS : Constants.MT20_ICONS) + "Off_Spin_Allrounder_Left" + "\0", print_writers);
	  	    							}else {
	  	    								CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT_PlayingXI$Side"+ whichSide + 
	  		    	    							"$TopLine$img_Text1$" + row_id + "$Bottom$Select$Batsman_Lefthand*TEXTURE*IMAGE SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? 
		  			    									Constants.TRI_SERIES_ICONS : Constants.MT20_ICONS) + "FastBowlerAllrounder" + "\0", print_writers);
	  	    							}
	  	    							
	  	    						}else {
	  	    							switch (bc.getBowlingStyle().toUpperCase()) {
	  	    							case "RFM": case "RF": case "RMF": case "RM": case "RSM": case "LF": case "LFM": case "LMF": case "LM":
	  	    								if(bc.getBattingStyle().equalsIgnoreCase("LHB")) {
	  	    									CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT_PlayingXI$Side"+ whichSide + 
	  	    	    	    							"$TopLine$img_Text1$" + row_id + "$Bottom$Select$Batsman_Lefthand*TEXTURE*IMAGE SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? 
	  		  			    									Constants.TRI_SERIES_ICONS : Constants.MT20_ICONS) + "Pace_BowlerAllrounerLeftHand" + "\0", print_writers);
	  	    								}else {
	  	    									CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT_PlayingXI$Side"+ whichSide + 
	  	    	    	    							"$TopLine$img_Text1$" + row_id + "$Bottom$Select$Batsman_Lefthand*TEXTURE*IMAGE SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? 
	  		  			    									Constants.TRI_SERIES_ICONS : Constants.MT20_ICONS) + "FastBowlerAllrounder" + "\0", print_writers);
	  	    								}
	  	    								break;

	  	    							case "ROB": case "RLB": case "LSL": case "WSL": case "LCH": case "RLG": case "WSR": case "LSO":
	  	    								if(bc.getBattingStyle().equalsIgnoreCase("LHB")) {
	  	    									CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT_PlayingXI$Side"+ whichSide + 
	  	    	    	    							"$TopLine$img_Text1$" + row_id + "$Bottom$Select$Batsman_Lefthand*TEXTURE*IMAGE SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? 
	  		  			    									Constants.TRI_SERIES_ICONS : Constants.MT20_ICONS) + "Off_Spin_Allrounder_Left" + "\0", print_writers);
	  	    								}else {
	  	    									CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT_PlayingXI$Side"+ whichSide + 
	  	    	    	    							"$TopLine$img_Text1$" + row_id + "$Bottom$Select$Batsman_Lefthand*TEXTURE*IMAGE SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? 
	  		  			    									Constants.TRI_SERIES_ICONS : Constants.MT20_ICONS) + "Off_Spin_Bat" + "\0", print_writers);
	  	    								}
	  	    								break;
	  	    							}
	  	    						}
	  	    					}
	  	    					if(bc.getCaptainWicketKeeper() != null) {
	  	    						if (bc.getCaptainWicketKeeper().equalsIgnoreCase(CricketUtil.CAPTAIN)) {
	  	    							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT_PlayingXI$Side"+ whichSide + 
	  	    	    							"$TopLine$img_Text1$" + row_id + "$Captain$Select*FUNCTION*Omo*vis_con SET 1\0", print_writers);
	  	    							
	  	    						} else if (bc.getCaptainWicketKeeper().equalsIgnoreCase("CAPTAIN_WICKET_KEEPER")) {
	  	    							
	  	    							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT_PlayingXI$Side"+ whichSide + 
	  	    	    							"$TopLine$img_Text1$" + row_id + "$Captain$Select*FUNCTION*Omo*vis_con SET 1\0", print_writers);
	  	    							
	  	    							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT_PlayingXI$Side"+ whichSide + 
	  	    	    							"$TopLine$img_Text1$" + row_id + "$Bottom$Select$Batsman_Lefthand*TEXTURE*IMAGE SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? 
	  			    									Constants.TRI_SERIES_ICONS : Constants.MT20_ICONS) + "Keeper" + "\0", print_writers);
	  	    						} else if (bc.getCaptainWicketKeeper().equalsIgnoreCase(CricketUtil.WICKET_KEEPER)) {
	  	    							
	  	    							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT_PlayingXI$Side"+ whichSide + 
	  	    	    							"$TopLine$img_Text1$" + row_id + "$Captain$Select*FUNCTION*Omo*vis_con SET 0\0", print_writers);

	  	    							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT_PlayingXI$Side"+ whichSide + 
	  	    	    							"$TopLine$img_Text1$" + row_id + "$Bottom$Select$Batsman_Lefthand*TEXTURE*IMAGE SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? 
	  			    									Constants.TRI_SERIES_ICONS : Constants.MT20_ICONS) + "Keeper" + "\0", print_writers);
	  	    						} else {
	  	    							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT_PlayingXI$Side"+ whichSide + 
	  	    	    							"$TopLine$img_Text1$" + row_id + "$Captain$Select*FUNCTION*Omo*vis_con SET 0\0", print_writers);
	  	    						}
	  	    					}else {
	  	    						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT_PlayingXI$Side"+ whichSide + 
	      	    							"$TopLine$img_Text1$" + row_id + "$Captain$Select*FUNCTION*Omo*vis_con SET 0\0", print_writers);
	  	    					}
	  	    				}
	  	    			}else if(lowerThird.getHeaderText().equalsIgnoreCase("BATTING_CARD")) {
	  	    				
	  	    				int inAtPosition = inning.getTotalWickets();
	  	    				
	  	    				for(BattingCard bc : battingCardList) {
	  	    					switch (bc.getStatus()) {
	  	    					case CricketUtil.NOT_OUT:
	  	    						inAtPosition++;
	  	    						break;
	  	    					}
	  	    				}
	  	    				
	  	    				Collections.sort(battingCardList);
	  	    				for (BattingCard bc : battingCardList) {
	  	    					row_id = row_id + 1;
	  	    					
//	  	    					if(battingCardList.size() >= 12) {
////	  	    						containerNam1 = "$Data_Grp";
////	  	    						offset = "133.0";
////	  	    						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT_PlayingXI$Data_All$Side"+ WhichSide + containerNam1 +
////	  	    								"$" + row_id + "$Select*FUNCTION*Omo*vis_con SET 1\0", print_writers);
//	  	    					}else {
//	  	    						containerNam1 = "$Data_Grp";
//	  	    						offset = "146.0";
//	  	    						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT_PlayingXI$Data_All$Side"+ WhichSide + containerNam1 +
//	  	    								"$" + row_id + "$Select*FUNCTION*Omo*vis_con SET 0\0", print_writers);
//	  	    					}
	  	    					
//	  	    					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT_PlayingXI$Data_All$Side"+ WhichSide + containerNam1 
//	  	    							+ "*FUNCTION*Grid*col_offset SET " + offset + "\0", print_writers);
//	  	    					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT_PlayingXI$Data_All$Side"+ WhichSide + containerNam1 
//	  	    							+ "*FUNCTION*Grid*num_col SET " + row_id + "\0", print_writers);

//	  	    					if(!CricketFunctions.checkBatAndBallImpactInOutPlayer(match.getEventFile().getEvents(), bc.getPlayerId()).isEmpty()) {
//	  	    						switch(CricketFunctions.checkBatAndBallImpactInOutPlayer(match.getEventFile().getEvents(), bc.getPlayerId())) {
//	  	    						case "IMP_IN":
//	  	    							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT_PlayingXI$Data_All$Side" + 
//	  	    									WhichSide + "$" + row_id + "$Impact_Sub*FUNCTION*Omo*vis_con SET 1\0", print_writers);
//	  	    							break;
//	  	    						case "IMP_OUT":
//	  	    							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT_PlayingXI$Data_All" +
//	  	    									"$Side" + WhichSide + "$" + row_id + "$Impact_Sub*FUNCTION*Omo*vis_con SET 2\0", print_writers);
//	  	    							break;
//	  	    						case "CON_IN":
//	  	    							break;
//	  	    						case "CON_OUT":
//	  	    							break;
//	  	    						}
//	  	    					}else {
//	  	    						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT_PlayingXI$Data_All" +
//	  	    								"$Side" + WhichSide + "$" + row_id + "$Impact_Sub*FUNCTION*Omo*vis_con SET 0\0", print_writers);
//	  	    					}
	  	    					
	  	    					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT_PlayingXI$Side"+ whichSide + "$TopLine$img_Text1$" + row_id + 
	  	    							"$PlayerStatus$Select_Status*FUNCTION*Omo*vis_con SET 0\0", print_writers);

	  	    					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT_PlayingXI$Side" + whichSide + "$TopLine" + 
		  	  							"$img_Text1$" + row_id + "$Pic$img_Base2*TEXTURE*IMAGE SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? 
		  										Constants.TRI_SERIES_BASE2 : Constants.MT20_BASE2) + lowerThird.getWhichTeamFlag() + "\0", print_writers);
		  	  	            	CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT_PlayingXI$Side" + whichSide + "$TopLine" + 
		  	  							"$img_Text1$" + row_id + "$Name_Grp$img_Text2*TEXTURE*IMAGE SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? 
		    									Constants.TRI_SERIES_TEXT2 : Constants.MT20_TEXT2) + lowerThird.getWhichTeamFlag() + "\0", print_writers);
		  	  	            	CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT_PlayingXI$Side" + whichSide + "$TopLine" + 
		  	  							"$img_Text1$" + row_id + "$Captain$img_Text1*TEXTURE*IMAGE SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? 
		    									Constants.TRI_SERIES_TEXT2 : Constants.MT20_TEXT2) + lowerThird.getWhichTeamFlag() + "\0", print_writers);
	  	  	            	
	  	  	            	
		  	  	            	CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT_PlayingXI$Side"+ whichSide + 
  	    							"$TopLine$img_Text1$" + row_id + "$Captain$Select$img_Text1*TEXTURE*IMAGE SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? 
	    									Constants.TRI_SERIES_TEXT2 : Constants.MT20_TEXT2) + lowerThird.getWhichTeamFlag() + "\0", print_writers);
		  	  	            
	  	    					
	  	    					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT_PlayingXI$Side"+ whichSide + 
	  	    							"$TopLine$img_Text1$" + row_id + "$Name_Grp$txt_LastName*GEOM*TEXT SET " + bc.getPlayer().getTicker_name() + "\0", print_writers);
	  	    					
	  	    					if(config.getPrimaryIpAddress().equalsIgnoreCase(Constants.LOCALHOST)) {
	  	    						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT_PlayingXI$Side" + whichSide + "$TopLine$img_Text1$" + row_id + "$Pic$img*TEXTURE*IMAGE SET "
	  	    								+ (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_LOCAL_PHOTO_PATH : Constants.MT20_LOCAL_PHOTO_PATH) 
	  	    								+ "\\\\" + team.getTeamName4() + "\\\\" + Constants.CENTER + "\\\\" + bc.getPlayer().getPhoto() + CricketUtil.PNG_EXTENSION + "\0", print_writers);
	  	    					}else {
	  	    						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT_PlayingXI$Side" + whichSide + "$TopLine$img_Text1$" + row_id + "$Pic$img*TEXTURE*IMAGE SET "
	  	    								+ "\\\\" + config.getPrimaryIpAddress() + "\\\\" + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_PHOTO_PATH : Constants.MT20_PHOTO_PATH) + "\\\\" + team.getTeamName4() + "\\\\" + Constants.CENTER + "\\\\" + bc.getPlayer().getPhoto()
	  	    								+ CricketUtil.PNG_EXTENSION + "\0", print_writers);
	  	    					}
	  	    					
	  	    					if(bc.getStatus().equalsIgnoreCase(CricketUtil.STILL_TO_BAT)) {
	  	    						inAtPosition++;
	  	    						if(bc.getHowOut() != null) {
	  	    							if(bc.getHowOut().equalsIgnoreCase(CricketUtil.RETIRED_HURT)) {
	  	    								CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT_PlayingXI$Side"+ whichSide + 
	  	        	    							"$TopLine$img_Text1$" + row_id + "$Bottom$Select*FUNCTION*Omo*vis_con SET 1\0", print_writers);
	  	    								CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT_PlayingXI$Side"+ whichSide + 
	  	        	    							"$TopLine$img_Text1$" + row_id + "$Bottom$txt1*GEOM*TEXT SET " + bc.getRuns() + "* (" + bc.getBalls() + ")" + "\0", print_writers);
	  	    							}
	  	    						}else {
	  	    							
	  	    							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT_PlayingXI$Side"+ whichSide + 
	          	    							"$TopLine$img_Text1$" + row_id + "$Bottom$Select*FUNCTION*Omo*vis_con SET 1\0", print_writers);
	  	    							
	  	    							if (inning.getInningStatus().equalsIgnoreCase(CricketUtil.START)) {
	  	    								CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT_PlayingXI$Side"+ whichSide + 
	  	        	    							"$TopLine$img_Text1$" + row_id + "$Bottom$txt1*GEOM*TEXT SET IN AT " + row_id + "\0", print_writers);
	  	    							} else if (inning.getInningStatus().equalsIgnoreCase(CricketUtil.PAUSE)) {
	  	    								CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT_PlayingXI$Side"+ whichSide + 
	  	        	    							"$TopLine$img_Text1$" + row_id + "$Bottom$txt1*GEOM*TEXT SET DNB\0", print_writers);
	  	    							}
	  	    						}
	  	    					}else if(bc.getStatus().equalsIgnoreCase(CricketUtil.NOT_OUT)) {
	  	    						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT_PlayingXI$Side"+ whichSide + 
	      	    							"$TopLine$img_Text1$" + row_id + "$Bottom$Select*FUNCTION*Omo*vis_con SET 1\0", print_writers);
	  	    						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT_PlayingXI$Side"+ whichSide + 
	      	    							"$TopLine$img_Text1$" + row_id + "$Bottom$txt1*GEOM*TEXT SET " + bc.getRuns() + "* (" + bc.getBalls() + ")" + "\0", print_writers);
	  	    					}else {
	  	    						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT_PlayingXI$Side"+ whichSide + 
	      	    							"$TopLine$img_Text1$" + row_id + "$Bottom$Select*FUNCTION*Omo*vis_con SET 1\0", print_writers);
	  	    						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT_PlayingXI$Side"+ whichSide + 
	      	    							"$TopLine$img_Text1$" + row_id + "$Bottom$txt1*GEOM*TEXT SET " + bc.getRuns() + " (" + bc.getBalls() + ")" + "\0", print_writers);
	  	    					}
	  	    					
	  	    					String cap_wkt = "";
	  	    					if(inning.getBattingTeamId() == match.getSetup().getHomeTeamId()) {
	  	    						
	  	    						for (Player plyr : match.getSetup().getHomeSquad()) {
	  	    							if(plyr.getPlayerId() == bc.getPlayerId()) {
	  	    								cap_wkt = plyr.getCaptainWicketKeeper();
	  	    							}
	  	    						}
	  	    						if(match.getSetup().getHomeSubstitutes() != null) {
	  	    							for (Player plyr : match.getSetup().getHomeSubstitutes()) {
	  		    							if(plyr.getPlayerId() == bc.getPlayerId()) {
	  		    								cap_wkt = plyr.getCaptainWicketKeeper();
	  		    							}
	  		    						}
	  	    						}
	  	    						if(match.getSetup().getHomeOtherSquad() != null) {
	  	    							for (Player plyr : match.getSetup().getHomeOtherSquad()) {
	  		    							if(plyr.getPlayerId() == bc.getPlayerId()) {
	  		    								cap_wkt = plyr.getCaptainWicketKeeper();
	  		    							}
	  		    						}
	  	    						}
	  	    					}else if(inning.getBattingTeamId() == match.getSetup().getAwayTeamId()) {
	  	    						
	  	    						if( match.getSetup().getAwaySquad() != null) {
	  	    							for (Player plyr : match.getSetup().getAwaySquad()) {
	  		    							if(plyr.getPlayerId() == bc.getPlayerId()) {
	  		    								cap_wkt = plyr.getCaptainWicketKeeper();
	  		    							}
	  		    						}
	  		    					}
	  	    						
	  	    						if(match.getSetup().getAwaySubstitutes() != null) {
	  	    							for (Player plyr : match.getSetup().getAwaySubstitutes()) {
	  		    							if(plyr.getPlayerId() == bc.getPlayerId()) {
	  		    								cap_wkt = plyr.getCaptainWicketKeeper();
	  		    							}
	  		    						}
	  	    						}
	  	    						
	  	    						if(match.getSetup().getAwayOtherSquad() != null) {
	  	    							for (Player plyr : match.getSetup().getAwayOtherSquad()) {
	  		    							if(plyr.getPlayerId() == bc.getPlayerId()) {
	  		    								cap_wkt = plyr.getCaptainWicketKeeper();
	  		    							}
	  		    						}
	  	    						}
	  	    						
	  	    					}
	  	    					
	  	    					if(cap_wkt != null) {
	  	    						if (cap_wkt.equalsIgnoreCase(CricketUtil.CAPTAIN)) {
	  	    							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT_PlayingXI$Side"+ whichSide + 
	  	    	    							"$TopLine$img_Text1$" + row_id + "$Captain$Select*FUNCTION*Omo*vis_con SET 1\0", print_writers);
	  	    						} else if (cap_wkt.equalsIgnoreCase("CAPTAIN_WICKET_KEEPER")) {
	  	    							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT_PlayingXI$Side"+ whichSide + 
	  	    	    							"$TopLine$img_Text1$" + row_id + "$Captain$Select*FUNCTION*Omo*vis_con SET 1\0", print_writers);
	  	    						} else if (cap_wkt.equalsIgnoreCase(CricketUtil.WICKET_KEEPER)) {
	  	    							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT_PlayingXI$Side"+ whichSide + 
	  	    	    							"$TopLine$img_Text1$" + row_id + "$Captain$Select*FUNCTION*Omo*vis_con SET 0\0", print_writers);
	  	    						} else {
	  	    							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT_PlayingXI$Side"+ whichSide + 
	  	    	    							"$TopLine$img_Text1$" + row_id + "$Captain$Select*FUNCTION*Omo*vis_con SET 0\0", print_writers);
	  	    						}
	  	    					}else {
	  	    						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT_PlayingXI$Side"+ whichSide + 
	      	    							"$TopLine$img_Text1$" + row_id + "$Captain$Select*FUNCTION*Omo*vis_con SET 0\0", print_writers);
	  	    					}
	  	    				}
	  	    			}
	  	    			break;
	    			  case "Control_Shift_L":
	  	            	CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Lt_MatchID$Side0" + whichSide + "$DataPart$BottomPart" + 
	  							"$Text$Side" + whichSide +"$txt_SubTitle*GEOM*TEXT SET " + lowerThird.getBallsFacedText() + " \0", print_writers);
	  					break;
	    			  case "Control_Shift_B":
	  	    			switch (config.getBroadcaster().toUpperCase()) {
	  	    			case Constants.TRI_SERIES:  case Constants.MT20: 
	  	    				int rowId = 0;
	  	    				int position = 0,inAtPosition = inning.getTotalWickets();
	  	    				
	  	    				for(BattingCard bc : inning.getBattingCard()) {
	  	    					switch (bc.getStatus()) {
	  	    					case CricketUtil.NOT_OUT:
	  	    						inAtPosition++;
	  	    						break;
	  	    					}
	  	    					
	  	    				}
	  	    				
	  	    				for(BattingCard bc : inning.getBattingCard()) {
	  	    					position++;
	  	    					if(rowId>=6) break;
	  	    					switch (bc.getStatus()) {
	  	    					case CricketUtil.STILL_TO_BAT:
	  	    						if(bc.getHowOut() != null && !bc.getHowOut().equalsIgnoreCase(CricketUtil.RETIRED_HURT)) continue;
	  	    						rowId++;
	  	    						
	  	    						inAtPosition++;
	  	    						switch (config.getBroadcaster().toUpperCase()) {
	  	    						case Constants.TRI_SERIES:  case Constants.MT20:
	  	    							if(config.getPrimaryIpAddress().equalsIgnoreCase(Constants.LOCALHOST)) {
	  	    								CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT_NextToBat$Side"+ whichSide +"$TopLine$img_Text1$"+ rowId + "$Pic$img*TEXTURE*IMAGE SET " 
	  	    										+ (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_LOCAL_PHOTO_PATH : Constants.MT20_LOCAL_PHOTO_PATH) 
	  	    										+ "\\\\" + inning.getBatting_team().getTeamName4() + "\\\\" + Constants.CENTER +"\\\\"+bc.getPlayer().getPhoto()+CricketUtil.PNG_EXTENSION + " \0", print_writers);
	  	    							}else {
	  	    								CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT_NextToBat$Side"+whichSide+"$TopLine$img_Text1$"+ rowId + "$Pic$img*TEXTURE*IMAGE SET " 
	  	    										+ "\\\\" + config.getPrimaryIpAddress() + "\\\\" + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_PHOTO_PATH : Constants.MT20_PHOTO_PATH)
	  	    										+ "\\\\" + inning.getBatting_team().getTeamName4() +"\\\\" + Constants.CENTER +"\\\\"+bc.getPlayer().getPhoto()+CricketUtil.PNG_EXTENSION + " \0", print_writers);
	  	    							}
	  	    							
	  	    							break;
	  	    						}
	  	    						
	  	    						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT_NextToBat$Side" + whichSide + "$TopLine" + 
			  	  							"$img_Text1$" + rowId + "$Pic$img_Base2*TEXTURE*IMAGE SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? 
			  										Constants.TRI_SERIES_BASE2 : Constants.MT20_BASE2) + lowerThird.getWhichTeamFlag() + "\0", print_writers);
			  	  	            	CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT_NextToBat$Side" + whichSide + "$TopLine" + 
			  	  							"$img_Text1$" + rowId + "$Name_Grp$img_Text2*TEXTURE*IMAGE SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? 
			    									Constants.TRI_SERIES_TEXT2 : Constants.MT20_TEXT2) + lowerThird.getWhichTeamFlag() + "\0", print_writers);
			  	  	            	
	  	    						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT_NextToBat$Side"+ whichSide +"$TopLine$img_Text1$"+ rowId + "$Bottom$Select*FUNCTION*Omo*vis_con SET 1\0", print_writers);
	  	    						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT_NextToBat$Side"+ whichSide +"$TopLine$img_Text1$"+ rowId + "$Name_Grp$txt_LastName*GEOM*TEXT SET " 
	  	    								+ bc.getPlayer().getTicker_name() + " \0", print_writers);
	  	    						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT_NextToBat$Side"+ whichSide +"$TopLine$img_Text1$"+ rowId + "$Bottom$Select$txt1*GEOM*TEXT SET " 
	  	    								+ "IN AT " + inAtPosition + " \0", print_writers);
	  	    						break;
	  	    					}
	  	    					
	  	    				}
	  	    				break;
	  	    			}
	  	    			break;
	    			  case "Control_Shift_Q":
	    					switch (config.getBroadcaster().toUpperCase()) {
	    					case Constants.TRI_SERIES:  case Constants.MT20:
	    						String firstBatter = "", secondBatter = "";
	    						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$SublineGrp$Side" + whichSide +
		    							"$select_Subline$1$Left*TRANSFORMATION*POSITION*X SET -190 \0", print_writers);
	    						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$SublineGrp$Side" + whichSide +
		    							"$select_Subline$1$Right*TRANSFORMATION*POSITION*X SET 190 \0", print_writers);
	    						
	    						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$SublineGrp$Side" + whichSide +
		    							"$select_Subline$1$Right*ACTIVE SET 1 \0", print_writers);
		    					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$SublineGrp$Side" + whichSide +
		    							"$select_Subline$1$Title*ACTIVE SET 0 \0", print_writers);
		    					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$SublineGrp$Side" + whichSide +
		    							"$select_Subline$1$Stat*ACTIVE SET 0 \0", print_writers);
		    					
		    					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$SublineGrp$Side" + whichSide +
		    							"$select_Subline$2$Right*ACTIVE SET 0 \0", print_writers);
		    					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$SublineGrp$Side" + whichSide +
		    							"$select_Subline$2$Title*ACTIVE SET 0 \0", print_writers);
		    					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$SublineGrp$Side" + whichSide +
		    							"$select_Subline$2$Stat*ACTIVE SET 0 \0", print_writers);
		    					
	    						for(BattingCard bc : inning.getBattingCard()) {
	    							if(inning.getPartnerships().get(inning.getPartnerships().size() - 1).getFirstBatterNo() == bc.getPlayerId()) {
	    								firstBatter = bc.getPlayer().getTicker_name() + " " + bc.getRuns() + " (" + bc.getBalls() + ")";
	    							}else if(inning.getPartnerships().get(inning.getPartnerships().size() - 1).getSecondBatterNo() == bc.getPlayerId()) {
	    								secondBatter = bc.getPlayer().getTicker_name() + " " + bc.getRuns() + " (" + bc.getBalls() + ")";
	    							}
	    						}
	    						
	    						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$Position_With_Graphics$SublineGrp$Rows$Side" + whichSide +
										"$select_Subline$1$Left$txt_1*GEOM*TEXT SET " + firstBatter + "\0", print_writers);
		    					
								CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$Position_With_Graphics$SublineGrp$Rows$Side" + whichSide +
										"$select_Subline$1$Right$txt_1*GEOM*TEXT SET " + secondBatter + "\0", print_writers);
		    					
	    						if(lowerThird.getHeaderText().equalsIgnoreCase("CURR_PART")) {
	    							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$Position_With_Graphics$SublineGrp$Rows$Side" + whichSide +
											"$select_Subline$2$Left$txt_1*GEOM*TEXT SET " + inning.getPartnerships().get(inning.getPartnerships().size() - 1).getFirstBatterRuns() 
	    									+ " (" + inning.getPartnerships().get(inning.getPartnerships().size() - 1).getFirstBatterBalls() + ")" + "                                               "
											+ "             CONTRIBUTION                                                      " + inning.getPartnerships().get(inning.getPartnerships().size() - 1).getSecondBatterRuns() 
											+ " (" + inning.getPartnerships().get(inning.getPartnerships().size() - 1).getSecondBatterBalls() + ")" + "\0", print_writers);
	    						}else {
	    							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$Position_With_Graphics$SublineGrp$Rows$Side" + whichSide +
											"$select_Subline$2$Left$txt_1*GEOM*TEXT SET " + lowerThird.getLeftText()[0] + "\0", print_writers);
	    						}
	    						
	    						break;
	    					}
	    				break;
	    			  case "Control_Shift_X":
	      				switch (config.getBroadcaster().toUpperCase()) {
	      				case Constants.TRI_SERIES:  case Constants.MT20:
	      					
	      					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$SublineGrp$Side" + whichSide +
	      							"$select_Subline$1$Right*ACTIVE SET 0 \0", print_writers);
	      					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$SublineGrp$Side" + whichSide +
	      							"$select_Subline$2$Right*ACTIVE SET 0 \0", print_writers);
	      					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$SublineGrp$Side" + whichSide +
	      							"$select_Subline$3$Right*ACTIVE SET 0 \0", print_writers);
	      					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$SublineGrp$Side" + whichSide +
	      							"$select_Subline$1$Left*ACTIVE SET 0 \0", print_writers);
	      					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$SublineGrp$Side" + whichSide +
	      							"$select_Subline$2$Left*ACTIVE SET 0 \0", print_writers);
	      					
	      					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$SublineGrp$Side" + whichSide +
	      							"$select_Subline$2$Title*ACTIVE SET 0 \0", print_writers);
	      					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$SublineGrp$Side" + whichSide +
	      							"$select_Subline$3$Title*ACTIVE SET 0 \0", print_writers);
	      					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$SublineGrp$Side" + whichSide +
	      							"$select_Subline$4$Title*ACTIVE SET 0 \0", print_writers);
	      					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$SublineGrp$Side" + whichSide +
	      							"$select_Subline$3$Stat*ACTIVE SET 0 \0", print_writers);
	      					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$SublineGrp$Side" + whichSide +
	      							"$select_Subline$4$Right*ACTIVE SET 0 \0", print_writers);
	      					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$SublineGrp$Side" + whichSide +
	      							"$select_Subline$4$Left*ACTIVE SET 0 \0", print_writers);
	      					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$SublineGrp$Side" + whichSide +
	      							"$select_Subline$4$Stat*ACTIVE SET 0 \0", print_writers);
	      					
	      					for(int iStat = 0; iStat < lowerThird.getTitlesText().length; iStat++) {
	      						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$SublineGrp$Side" + whichSide 
	      							+ "$select_Subline$1$Title$txt_" + (iStat + 1) + "*GEOM*TEXT SET " + lowerThird.getTitlesText()[iStat] + "\0", print_writers);
	      						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$SublineGrp$Side" + whichSide 
	      							+ "$select_Subline$2$Stat$txt_" + (iStat + 1) + "*GEOM*TEXT SET " + lowerThird.getStatsText()[iStat] + "\0", print_writers);
	      					}
	      					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$SublineGrp$Side" + whichSide +
	      							"$select_Subline$3$Left$txt_1*GEOM*TEXT SET " + lowerThird.getLeftText()[0] + " CAREER :  " + "MATCHES: " + lowerThird.getLeftText()[1] + " | RUNS: " + 
	      							lowerThird.getLeftText()[2] + " | STRIKE RATE: " + lowerThird.getLeftText()[3]   + " \0", print_writers);
	      					break;
	      				}
	      				
	      				break;
	    			  case "Control_Shift_K": 
	      				switch (config.getBroadcaster().toUpperCase()) {
	      				case Constants.TRI_SERIES:  case Constants.MT20:
	      					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$SublineGrp$Side" + whichSide +
	      							"$select_Subline$1$Right*ACTIVE SET 0 \0", print_writers);
	      					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$SublineGrp$Side" + whichSide +
	      							"$select_Subline$2$Right*ACTIVE SET 0 \0", print_writers);
	      					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$SublineGrp$Side" + whichSide +
	      							"$select_Subline$3$Right*ACTIVE SET 0 \0", print_writers);
	      					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$SublineGrp$Side" + whichSide +
	      							"$select_Subline$1$Left*ACTIVE SET 0 \0", print_writers);
	      					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$SublineGrp$Side" + whichSide +
	      							"$select_Subline$2$Left*ACTIVE SET 0 \0", print_writers);
	      					
	      					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$SublineGrp$Side" + whichSide +
	      							"$select_Subline$2$Title*ACTIVE SET 0 \0", print_writers);
	      					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$SublineGrp$Side" + whichSide +
	      							"$select_Subline$3$Title*ACTIVE SET 0 \0", print_writers);
	      					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$SublineGrp$Side" + whichSide +
	      							"$select_Subline$4$Title*ACTIVE SET 0 \0", print_writers);
	      					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$SublineGrp$Side" + whichSide +
	      							"$select_Subline$3$Stat*ACTIVE SET 0 \0", print_writers);
	      					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$SublineGrp$Side" + whichSide +
	      							"$select_Subline$4$Right*ACTIVE SET 0 \0", print_writers);
	      					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$SublineGrp$Side" + whichSide +
	      							"$select_Subline$4$Left*ACTIVE SET 0 \0", print_writers);
	      					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$SublineGrp$Side" + whichSide +
	      							"$select_Subline$4$Stat*ACTIVE SET 0 \0", print_writers);
	      					
	      					for(int iStat = 0; iStat < lowerThird.getTitlesText().length; iStat++) {
	      						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$SublineGrp$Side" + whichSide 
	      							+ "$select_Subline$1$Title$txt_" + (iStat + 1) + "*GEOM*TEXT SET " + lowerThird.getTitlesText()[iStat] + "\0", print_writers);
	      						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$SublineGrp$Side" + whichSide 
	      							+ "$select_Subline$2$Stat$txt_" + (iStat + 1) + "*GEOM*TEXT SET " + lowerThird.getStatsText()[iStat] + "\0", print_writers);
	      					}
	      					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$SublineGrp$Side" + whichSide +
	      							"$select_Subline$3$Left$txt_1*GEOM*TEXT SET " + lowerThird.getLeftText()[0] + " CAREER :  " + "MATCHES: " + lowerThird.getLeftText()[1] + " | WICKETS: " + 
	      							lowerThird.getLeftText()[2] + " | ECONOMY: " + lowerThird.getLeftText()[3]   + " \0", print_writers);
	      					break;
	      				}
	      				break;
	    			  case "Control_c": 
	      				switch (config.getBroadcaster().toUpperCase()) {
	      				case Constants.TRI_SERIES:  case Constants.MT20:
	      					//1st
	      					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$SublineGrp$Side" + whichSide +
	      							"$select_Subline$1$Right*ACTIVE SET 0 \0", print_writers);
	      					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$SublineGrp$Side" + whichSide +
	      							"$select_Subline$1$Left*ACTIVE SET 0 \0", print_writers);
	      					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$SublineGrp$Side" + whichSide +
	      							"$select_Subline$1$Stat*ACTIVE SET 0 \0", print_writers);
	      					//2nd
	      					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$SublineGrp$Side" + whichSide +
	      							"$select_Subline$2$Right*ACTIVE SET 0 \0", print_writers);
	      					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$SublineGrp$Side" + whichSide +
	      							"$select_Subline$2$Left*ACTIVE SET 0 \0", print_writers);
	      					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$SublineGrp$Side" + whichSide +
	      							"$select_Subline$2$Title*ACTIVE SET 0 \0", print_writers);
	      					//3rd
	      					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$SublineGrp$Side" + whichSide +
	      							"$select_Subline$3$Title*ACTIVE SET 0 \0", print_writers);
	      					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$SublineGrp$Side" + whichSide +
	      							"$select_Subline$3$Left*ACTIVE SET 0 \0", print_writers);
	      					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$SublineGrp$Side" + whichSide +
	      							"$select_Subline$3$Right*ACTIVE SET 0 \0", print_writers);
	      					//4th
	      					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$SublineGrp$Side" + whichSide +
	      							"$select_Subline$4$Title*ACTIVE SET 0 \0", print_writers);
	      					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$SublineGrp$Side" + whichSide +
	      							"$select_Subline$4$Left*ACTIVE SET 0 \0", print_writers);
	      					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$SublineGrp$Side" + whichSide +
	      							"$select_Subline$4$Right*ACTIVE SET 0 \0", print_writers);
	      					
	      					for(int iStat = 0; iStat < lowerThird.getTitlesText().length; iStat++) {
	      						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$SublineGrp$Side" + whichSide 
	  	    							+ "$select_Subline$1$Title$txt_" + (iStat + 1) + "*GEOM*TEXT SET " + lowerThird.getTitlesText()[iStat] + "\0", print_writers);
	      						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$SublineGrp$Side" + whichSide 
	      							+ "$select_Subline$2$Stat$txt_" + (iStat + 1) + "*GEOM*TEXT SET " + lowerThird.getStatsText()[iStat].split("-")[0] + "\0", print_writers);
	      						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$SublineGrp$Side" + whichSide 
	          							+ "$select_Subline$3$Stat$txt_" + (iStat + 1) + "*GEOM*TEXT SET " + lowerThird.getStatsText()[iStat].split("-")[1] + "\0", print_writers);
	      						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$SublineGrp$Side" + whichSide 
	          							+ "$select_Subline$4$Stat$txt_" + (iStat + 1) + "*GEOM*TEXT SET " + lowerThird.getStatsText()[iStat].split("-")[2] + "\0", print_writers);
	      						
	      					}
	      					
	      					
	      					break;
	      				}
	      				break;	
	    			//new case
	            }
	            break; 
	      }

	    return Constants.OK;
	}
	
	public void setPositionOfLT(String whatToProcess,int WhichSide,Configuration config,int subline)
	{
		String LT_In = "",LT_Out = "",LT_c1 = "",LT_c2 = "",
				LT_SbIn = "",LT_SbOut = "",LT_Sbc1 = "",LT_Sbc2 = "";
		switch (config.getBroadcaster().toUpperCase()) {
		
		case Constants.TRI_SERIES:  case Constants.MT20:
			switch (subline) {
			case 0:
				LT_In = "0.0 0.0 0.0";
				LT_Out = "0.0 0.0 0.0";
				LT_c1 = "0.0 0.0 0.0";
				LT_c2 = "0.0 0.0 0.0";
				
				LT_SbIn = "0.0";
				LT_SbOut = "0.0";
				LT_Sbc1 = "0.0";
				LT_Sbc2 = "0.0";
				break;
			case 1:
				LT_In = "0.0 20.0 0.0";
				LT_Out = "0.0 20.0 0.0";
				LT_c1 = "0.0 20.0 0.0";
				LT_c2 = "0.0 20.0 0.0";
				
				LT_SbIn = "20.0";
				LT_SbOut = "20.0";
				LT_Sbc1 = "20.0";
				LT_Sbc2 = "20.0";
				break;
			case 2:
				LT_In = "0.0 40.0 0.0";
				LT_Out = "0.0 40.0 0.0";
				LT_c1 = "0.0 40.0 0.0";
				LT_c2 = "0.0 40.0 0.0";
				
				LT_SbIn = "40.0";
				LT_SbOut = "40.0";
				LT_Sbc1 = "40.0";
				LT_Sbc2 = "40.0";
				System.out.println("LT_In = " + LT_In);
				break;
			case 3:
				LT_In = "0.0 60.0 0.0";
				LT_Out = "0.0 60.0 0.0";
				LT_c1 = "0.0 60.0 0.0";
				LT_c2 = "0.0 60.0 0.0";
				
				LT_SbIn = "60.0";
				LT_SbOut = "60.0";
				LT_Sbc1 = "60.0";
				LT_Sbc2 = "60.0";
				break;
			case 4:
				LT_In = "0.0 80.0 0.0";
				LT_Out = "0.0 80.0 0.0";
				LT_c1 = "0.0 80.0 0.0";
				LT_c2 = "0.0 80.0 0.0";
				
				LT_SbIn = "80.0";
				LT_SbOut = "80.0";
				LT_Sbc1 = "80.0";
				LT_Sbc2 = "80.0";
				break;	
			}
			
			if(WhichSide == 1) {
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$Overall_Position_Y_In_Out*ANIMATION*KEY*$C1*VALUE SET "
						+ LT_c1 + "\0",print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$Overall_Position_Y_In_Out$SublineGrp$obj_SublineBase*"
						+ "ANIMATION*KEY*$SBC1*VALUE SET " + LT_Sbc1 + "\0",print_writers);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$Overall_Position_Y_In_Out*ANIMATION*KEY*$1N*VALUE SET "
						+ LT_In + "\0",print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$Overall_Position_Y_In_Out*ANIMATION*KEY*$OUT*VALUE SET "
						+ LT_Out + "\0",print_writers);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$Overall_Position_Y_In_Out$SublineGrp$obj_SublineBase*"
						+ "ANIMATION*KEY*$SB_IN*VALUE SET " + LT_SbIn + "\0",print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$Overall_Position_Y_In_Out$SublineGrp$obj_SublineBase*"
						+ "ANIMATION*KEY*$SB_OUT*VALUE SET " + LT_SbOut + "\0",print_writers);
			}else if(WhichSide == 2) {
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$Overall_Position_Y_In_Out*ANIMATION*KEY*$C2*VALUE SET "
						+ LT_c2 + "\0",print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$Overall_Position_Y_In_Out$SublineGrp$obj_SublineBase*"
						+ "ANIMATION*KEY*$SBC2*VALUE SET " + LT_Sbc2 + "\0",print_writers);
			}
			break;
		}
		
	}
	public void HideAndShowL3rdSubStrapContainers(int WhichSide)
	{
		switch (config.getBroadcaster().toUpperCase()) {
		
		case Constants.TRI_SERIES:  case Constants.MT20:
			//Show number of sublines
			
			for(int i=0; i<10; i++) {
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$SublineGrp$Side" + WhichSide 
						+ "$select_Subline$2$Stat$txt_" + (i+1) + "*FUNCTION*Maxsize*DEFAULT_SCALE_X SET 0.33 \0", print_writers);
			}
			
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$SublineGrp$Side" + WhichSide +
					"$select_Subline$1$Left*TRANSFORMATION*POSITION*X SET -190 \0", print_writers);
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$SublineGrp$Side" + WhichSide +
					"$select_Subline$1$Right*TRANSFORMATION*POSITION*X SET 190 \0", print_writers);
			
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$SublineGrp$Side"+ WhichSide + 
					"$select_Subline*FUNCTION*Omo*vis_con SET " + lowerThird.getNumberOfSubLines() + "\0", print_writers);
			
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$Overall_Position_Y_In_Out$SublineGrp$Side" + WhichSide +
					"$select_Subline$1$Stat*ACTIVE SET 0 \0", print_writers);
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$Overall_Position_Y_In_Out$SublineGrp$Side" + WhichSide +
					"$select_Subline$2$Title*ACTIVE SET 0 \0", print_writers);
			
			if(lowerThird.getNumberOfSubLines() == 4) {
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$Overall_Position_Y_In_Out$SublineGrp$Side" + WhichSide +
						"$select_Subline$2$Stat*ACTIVE SET 1 \0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$Overall_Position_Y_In_Out$SublineGrp$Side" + WhichSide +
						"$select_Subline$3$Stat*ACTIVE SET 1 \0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$Overall_Position_Y_In_Out$SublineGrp$Side" + WhichSide +
						"$select_Subline$4$Stat*ACTIVE SET 1 \0", print_writers);
			}else if(lowerThird.getNumberOfSubLines() == 3) {
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$Overall_Position_Y_In_Out$SublineGrp$Side" + WhichSide +
						"$select_Subline$2$Stat*ACTIVE SET 1 \0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$Overall_Position_Y_In_Out$SublineGrp$Side" + WhichSide +
						"$select_Subline$3$Stat*ACTIVE SET 1 \0", print_writers);
			}else if(lowerThird.getNumberOfSubLines() == 2) {
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$Overall_Position_Y_In_Out$SublineGrp$Side" + WhichSide +
						"$select_Subline$2$Stat*ACTIVE SET 1 \0", print_writers);
			}
			
			for(int iSubLine = 1; iSubLine <= lowerThird.getNumberOfSubLines(); iSubLine++) {
				
				//HIDE AND SHOW TITLE
				if(lowerThird.getTitlesText() != null) {
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$Overall_Position_Y_In_Out$SublineGrp$Side" + WhichSide +
						"$select_Subline$"+ iSubLine + "$Title*ACTIVE SET 1 \0", print_writers);
					
					for(int iTitle = 1; iTitle <= lowerThird.getTitlesText().length; iTitle++) {
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$Overall_Position_Y_In_Out$SublineGrp$Side" + WhichSide +
							"$select_Subline$"+ iSubLine + "$Title$txt_" + iTitle + "*ACTIVE SET 1 \0", print_writers);
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$SublineGrp$Side" + WhichSide +
							"$select_Subline$"+ iSubLine + "$Title$txt_" + iTitle + "*TRANSFORMATION*POSITION*X SET " 
							+ lowerThird.getPosition()[iTitle-1] + "\0",print_writers);
					}
					//Hide number of Titles on each strap
					for(int iTitle = lowerThird.getTitlesText().length + 1; iTitle <= 10; iTitle++) {
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$Overall_Position_Y_In_Out$SublineGrp$Side" + WhichSide +
								"$select_Subline$"+ iSubLine + "$Title$txt_" + iTitle + "*ACTIVE SET 0 \0", print_writers);
					}
				}else {
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$Overall_Position_Y_In_Out$SublineGrp$Side" + WhichSide +
							"$select_Subline$"+ iSubLine + "$Title*ACTIVE SET 0 \0", print_writers);
				}
				
				// HIDE AND SHOW STATS
				if(lowerThird.getStatsText() != null) {
					
					for(int iStats = 1; iStats <= lowerThird.getStatsText().length; iStats++) {
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$Overall_Position_Y_In_Out$SublineGrp$Side" + WhichSide +
							"$select_Subline$"+ iSubLine + "$Stat$txt_" + iStats + "*ACTIVE SET 1 \0", print_writers);
						
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$SublineGrp$Side" + WhichSide +
							"$select_Subline$" + iSubLine + "$Stat$txt_" + iStats + "*TRANSFORMATION*POSITION*X SET " + lowerThird.getPosition()[iStats-1] + "\0",print_writers);
					}
					//Hide number of Titles on each strap
					for(int iStats = lowerThird.getStatsText().length + 1; iStats <= 10; iStats++) {
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$Overall_Position_Y_In_Out$SublineGrp$Side" + WhichSide +
								"$select_Subline$"+ iSubLine + "$Stat$txt_" + iStats + "*ACTIVE SET 0 \0", print_writers);
					}
				}else {
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$Overall_Position_Y_In_Out$SublineGrp$Side" + WhichSide +
							"$select_Subline$"+ iSubLine + "$Stat*ACTIVE SET 0 \0", print_writers);
				}
				
				//Show Left on each strap
				if(lowerThird.getLeftText() != null &&  lowerThird.getLeftText().length > 0) {
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$Overall_Position_Y_In_Out$SublineGrp$Side" + WhichSide +
							"$select_Subline$"+ iSubLine + "$Left*ACTIVE SET 1 \0", print_writers);
				} else {
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$Overall_Position_Y_In_Out$SublineGrp$Side" + WhichSide +
							"$select_Subline$"+ iSubLine + "$Left*ACTIVE SET 0 \0", print_writers);
				}
				//Show Right on each strap
				if(lowerThird.getRightText() != null && lowerThird.getRightText().length > 0) {
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$Overall_Position_Y_In_Out$SublineGrp$Side" + WhichSide +
							"$select_Subline$"+ iSubLine + "$Right*ACTIVE SET 1 \0", print_writers);
					if(lowerThird.getPosition() != null && lowerThird.getPosition().length > 0) {
						for(int iTitle = 1; iTitle <= lowerThird.getRightText().length; iTitle++) {
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$Overall_Position_Y_In_Out$SublineGrp$Side" + WhichSide +
								"$select_Subline$" + iTitle + "$Right*ACTIVE SET 1 \0", print_writers);
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$SublineGrp$Side" + WhichSide +
								"$select_Subline$" + iTitle + "$Right*TRANSFORMATION*POSITION*X SET " 
								+ lowerThird.getPosition()[0] + "\0",print_writers);
						}
					}
				} else {
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$Overall_Position_Y_In_Out$SublineGrp$Side" + WhichSide +
							"$select_Subline$"+ iSubLine + "$Right*ACTIVE SET 0 \0", print_writers);
				}
			}
			break;	
		}
	}
	public void setGriff(int griffSize,String whatToProcess,int WhichSide,Configuration config)
	{
		String [] TopTitle = null,TopStats = null,BottomTitle = null,BottomStats = null,Position=null;
		int numberOfSubLines = 0;
		
		if(griffSize <= 3) {
			numberOfSubLines = 2;
		}else {
			numberOfSubLines = 4;
		}
		
		switch (griffSize) {
		case 1:
			TopTitle = new String[1];
			TopStats = new String[1];
			Position = new String[] {"0.0"}; 
			if(whatToProcess.split(",")[0].equalsIgnoreCase("Alt_F1")) {
				for(int i=0;i<=griffSize-1;i++) {
					if(this_data_str.get(i).toUpperCase().equalsIgnoreCase("SEVENTH")) {
						TopTitle[i] = griff.get(i).getOpponentTeam().getTeamName4() + " - " + " FINAL";
					}else {
//						TopTitle[i] = griff.get(i).getOpponentTeam().getTeamName4() + " - " + this_data_str.get(i).toUpperCase() + " T20I";
						TopTitle[i] = this_data_str.get(i).toUpperCase() + " T20I";
					}
//					TopTitle[i] = griff.get(i).getOpponentTeam().getTeamName4() + " - " + this_data_str.get(i).toUpperCase() + " T20I";
					if(griff.get(i).getStatus().equalsIgnoreCase(CricketUtil.STILL_TO_BAT)) {
						if(griff.get(i).getHow_out() != null && !griff.get(i).getHow_out().trim().isEmpty() && 
								griff.get(i).getHow_out().equalsIgnoreCase(CricketUtil.RETIRED_HURT)) {
							TopStats[i] = griff.get(i).getRuns() + "," + griff.get(i).getBallsFaced();
						}else {
							TopStats[i] = "DNB";
						}
					}else if(griff.get(i).getStatus().equalsIgnoreCase(CricketUtil.OUT)) {
						TopStats[i] = griff.get(i).getRuns() + "," + griff.get(i).getBallsFaced();
					}else if(griff.get(i).getStatus().equalsIgnoreCase(CricketUtil.NOT_OUT)) {
						TopStats[i] = griff.get(i).getRuns() + "*," + griff.get(i).getBallsFaced();
					}else if(griff.get(i).getStatus().equalsIgnoreCase("DNB")) {
						TopStats[i] = "DNP";
					}else {
						TopStats[i] = "DNP";
					}
				}
			}else if(whatToProcess.split(",")[0].equalsIgnoreCase("Alt_F2")){
				for(int i=0;i<=griffSize-1;i++) {
					if(this_data_str.get(i).toUpperCase().equalsIgnoreCase("SEVENTH")) {
						TopTitle[i] = griff.get(i).getOpponentTeam().getTeamName4() + " - " + " FINAL";
					}else {
						TopTitle[i] = griff.get(i).getOpponentTeam().getTeamName4() + " - " + this_data_str.get(i).toUpperCase() + " T20I";
					}
//					TopTitle[i] = griff.get(i).getOpponentTeam().getTeamName4() + " - " + this_data_str.get(i).toUpperCase() + " T20I";
					if(griff.get(i).getStatus().equalsIgnoreCase("DNB")) {
						TopStats[i] = "DNB";
					}else if(griff.get(i).getStatus().equalsIgnoreCase("BALL")) {
						TopStats[i] = griff.get(i).getWickets() + "-" + griff.get(i).getRunsConceded() + "," + griff.get(i).getOversBowled();
					}else {
						TopStats[i] = "DNP";
					}
				}
			}
			break;
		case 2:
			TopTitle = new String[2];
			TopStats = new String[2];
			Position = new String[] {"-134","130"}; 
			
			if(whatToProcess.split(",")[0].equalsIgnoreCase("Alt_F1")) {
				for(int i=0;i<=griffSize-1;i++) {
					if(this_data_str.get(i).toUpperCase().equalsIgnoreCase("SEVENTH")) {
						TopTitle[i] = griff.get(i).getOpponentTeam().getTeamName4() + " - " + " FINAL";
					}else {
//						TopTitle[i] = griff.get(i).getOpponentTeam().getTeamName4() + " - " + this_data_str.get(i).toUpperCase() + " T20I";
						TopTitle[i] = this_data_str.get(i).toUpperCase() + " T20I";
					}
//					TopTitle[i] = griff.get(i).getOpponentTeam().getTeamName4() + " - " + this_data_str.get(i).toUpperCase() + " T20I";
					if(griff.get(i).getStatus().equalsIgnoreCase(CricketUtil.STILL_TO_BAT)) {
						if(griff.get(i).getHow_out() != null && !griff.get(i).getHow_out().trim().isEmpty() && griff.get(i).getHow_out().equalsIgnoreCase(CricketUtil.RETIRED_HURT)) {
							TopStats[i] = griff.get(i).getRuns() + "," + griff.get(i).getBallsFaced();
						}else {
							TopStats[i] = "DNB";
						}
					}else if(griff.get(i).getStatus().equalsIgnoreCase("DNB")) {
						TopStats[i] = "DNB";
					}else if(griff.get(i).getStatus().equalsIgnoreCase(CricketUtil.OUT)) {
						TopStats[i] = griff.get(i).getRuns() + "," + griff.get(i).getBallsFaced();
					}else if(griff.get(i).getStatus().equalsIgnoreCase(CricketUtil.NOT_OUT)) {
						TopStats[i] = griff.get(i).getRuns() + "*," + griff.get(i).getBallsFaced();
					}else {
						TopStats[i] = "DNP";
					}
				}
			}else if(whatToProcess.split(",")[0].equalsIgnoreCase("Alt_F2")){
				for(int i=0;i<=griffSize-1;i++) {
					if(this_data_str.get(i).toUpperCase().equalsIgnoreCase("SEVENTH")) {
						TopTitle[i] = griff.get(i).getOpponentTeam().getTeamName4() + " - " + " FINAL";
					}else {
						TopTitle[i] = griff.get(i).getOpponentTeam().getTeamName4() + " - " + this_data_str.get(i).toUpperCase() + " T20I";
					}
//					TopTitle[i] = griff.get(i).getOpponentTeam().getTeamName4() + " - " + this_data_str.get(i).toUpperCase() + " T20I";
					if(griff.get(i).getStatus().equalsIgnoreCase("DNB")) {
						TopStats[i] = "DNB";
					}else if(griff.get(i).getStatus().equalsIgnoreCase("BALL")) {
						TopStats[i] = griff.get(i).getWickets() + "-" + griff.get(i).getRunsConceded() + "," + griff.get(i).getOversBowled();
					}else {
						TopStats[i] = "DNP";
					}
				}
			}
			break;
		case 3:
			TopTitle = new String[3];
			TopStats = new String[3];
			Position = new String[] {"-134","0","130"}; 
			
			if(whatToProcess.split(",")[0].equalsIgnoreCase("Alt_F1")) {
				for(int i=0;i<=griffSize-1;i++) {
					if(this_data_str.get(i).toUpperCase().equalsIgnoreCase("SEVENTH")) {
						TopTitle[i] = griff.get(i).getOpponentTeam().getTeamName4() + " - " + " FINAL";
					}else {
//						TopTitle[i] = griff.get(i).getOpponentTeam().getTeamName4() + " - " + this_data_str.get(i).toUpperCase() + " T20I";
						TopTitle[i] = this_data_str.get(i).toUpperCase() + " T20I";
					}
//					TopTitle[i] = griff.get(i).getOpponentTeam().getTeamName4() + " - " + this_data_str.get(i).toUpperCase() + " T20I";
					if(griff.get(i).getStatus().equalsIgnoreCase(CricketUtil.STILL_TO_BAT)) {
						if(griff.get(i).getHow_out() != null && !griff.get(i).getHow_out().trim().isEmpty() && griff.get(i).getHow_out().equalsIgnoreCase(CricketUtil.RETIRED_HURT)) {
							TopStats[i] = griff.get(i).getRuns() + "," + griff.get(i).getBallsFaced();
						}else {
							TopStats[i] = "DNB";
						}
					}else if(griff.get(i).getStatus().equalsIgnoreCase("DNB")) {
						TopStats[i] = "DNB";
					}else if(griff.get(i).getStatus().equalsIgnoreCase(CricketUtil.OUT)) {
						TopStats[i] = griff.get(i).getRuns() + "," + griff.get(i).getBallsFaced();
					}else if(griff.get(i).getStatus().equalsIgnoreCase(CricketUtil.NOT_OUT)) {
						TopStats[i] = griff.get(i).getRuns() + "*," + griff.get(i).getBallsFaced();
					}else {
						TopStats[i] = "DNP";
					}
				}
			}else if(whatToProcess.split(",")[0].equalsIgnoreCase("Alt_F2")){
				for(int i=0;i<=griffSize-1;i++) {
					if(this_data_str.get(i).toUpperCase().equalsIgnoreCase("SEVENTH")) {
						TopTitle[i] = griff.get(i).getOpponentTeam().getTeamName4() + " - " + " FINAL";
					}else {
						TopTitle[i] = griff.get(i).getOpponentTeam().getTeamName4() + " - " + this_data_str.get(i).toUpperCase() + " T20I";
					}
//					TopTitle[i] = griff.get(i).getOpponentTeam().getTeamName4() + " - " + this_data_str.get(i).toUpperCase() + " T20I";
					if(griff.get(i).getStatus().equalsIgnoreCase("DNB")) {
						TopStats[i] = "DNB";
					}else if(griff.get(i).getStatus().equalsIgnoreCase("BALL")) {
						TopStats[i] = griff.get(i).getWickets() + "-" + griff.get(i).getRunsConceded() + "," + griff.get(i).getOversBowled();
					}else {
						TopStats[i] = "DNP";
					}
				}
			}
			break;
		case 4:
			TopTitle = new String[2];
			TopStats = new String[2];
			BottomTitle = new String[2];
			BottomStats = new String[2];
			Position = new String[] {"-134","130","-134","130"};
			
			if(whatToProcess.split(",")[0].equalsIgnoreCase("Alt_F1")) {
				for(int i=0;i<=griffSize-1;i++) {
					if(i<2) {
						if(this_data_str.get(i).toUpperCase().equalsIgnoreCase("SEVENTH")) {
							TopTitle[i] = griff.get(i).getOpponentTeam().getTeamName4() + " - " + " FINAL";
						}else {
							TopTitle[i] = griff.get(i).getOpponentTeam().getTeamName4() + " - " + this_data_str.get(i).toUpperCase() + " T20I";
						}
//						TopTitle[i] = griff.get(i).getOpponentTeam().getTeamName4() + " - " + this_data_str.get(i).toUpperCase() + " T20I";
						if(griff.get(i).getStatus().equalsIgnoreCase(CricketUtil.STILL_TO_BAT)) {
							if(griff.get(i).getHow_out() != null && !griff.get(i).getHow_out().trim().isEmpty() && griff.get(i).getHow_out().equalsIgnoreCase(CricketUtil.RETIRED_HURT)) {
								TopStats[i] = griff.get(i).getRuns() + "," + griff.get(i).getBallsFaced();
							}else {
								TopStats[i] = "DNB";
							}
						}else if(griff.get(i).getStatus().equalsIgnoreCase("DNB")) {
							TopStats[i] = "DNB";
						}else if(griff.get(i).getStatus().equalsIgnoreCase(CricketUtil.OUT)) {
							TopStats[i] = griff.get(i).getRuns() + "," + griff.get(i).getBallsFaced();
						}else if(griff.get(i).getStatus().equalsIgnoreCase(CricketUtil.NOT_OUT)) {
							TopStats[i] = griff.get(i).getRuns() + "*," + griff.get(i).getBallsFaced();
						}else {
							TopStats[i] = "DNP";
						}
					}else {
						if(this_data_str.get(i).toUpperCase().equalsIgnoreCase("SEVENTH")) {
							BottomTitle[i-2] = griff.get(i).getOpponentTeam().getTeamName4() + " - " + " FINAL";
						}else {
							BottomTitle[i-2] = griff.get(i).getOpponentTeam().getTeamName4() + " - " + this_data_str.get(i).toUpperCase() + " T20I";
						}
//						BottomTitle[i-2] = griff.get(i).getOpponentTeam().getTeamName4() + " - " + this_data_str.get(i).toUpperCase() + " T20I";
						if(griff.get(i).getStatus().equalsIgnoreCase(CricketUtil.STILL_TO_BAT)) {
							if(griff.get(i).getHow_out() != null && !griff.get(i).getHow_out().trim().isEmpty() && griff.get(i).getHow_out().equalsIgnoreCase(CricketUtil.RETIRED_HURT)) {
								BottomStats[i-2] = griff.get(i).getRuns() + "," + griff.get(i).getBallsFaced();
							}else {
								BottomStats[i-2] = "DNB";
							}
						}else if(griff.get(i).getStatus().equalsIgnoreCase("DNB")) {
							BottomStats[i-2] = "DNB";
						}else if(griff.get(i).getStatus().equalsIgnoreCase(CricketUtil.OUT)) {
							BottomStats[i-2] = griff.get(i).getRuns() + "," + griff.get(i).getBallsFaced();
						}else if(griff.get(i).getStatus().equalsIgnoreCase(CricketUtil.NOT_OUT)) {
							BottomStats[i-2] = griff.get(i).getRuns() + "*," + griff.get(i).getBallsFaced();
						}else {
							BottomStats[i-2] = "DNP";
						}
					}
				}
			}else if(whatToProcess.split(",")[0].equalsIgnoreCase("Alt_F2")){
				for(int i=0;i<=griffSize-1;i++) {
					if(i<2) {
						if(this_data_str.get(i).toUpperCase().equalsIgnoreCase("SEVENTH")) {
							TopTitle[i] = griff.get(i).getOpponentTeam().getTeamName4() + " - " + " FINAL";
						}else {
							TopTitle[i] = griff.get(i).getOpponentTeam().getTeamName4() + " - " + this_data_str.get(i).toUpperCase() + " T20I";
						}
//						TopTitle[i] = griff.get(i).getOpponentTeam().getTeamName4() + " - " + this_data_str.get(i).toUpperCase() + " T20I";
						if(griff.get(i).getStatus().equalsIgnoreCase("DNB")) {
							TopStats[i] = "DNB";
						}else if(griff.get(i).getStatus().equalsIgnoreCase("BALL")) {
							TopStats[i] = griff.get(i).getWickets() + "-" + griff.get(i).getRunsConceded() + "," + griff.get(i).getOversBowled();
						}else {
							TopStats[i] = "DNP";
						}
					}else {
						if(this_data_str.get(i).toUpperCase().equalsIgnoreCase("SEVENTH")) {
							BottomTitle[i-2] = griff.get(i).getOpponentTeam().getTeamName4() + " - " + " FINAL";
						}else {
							BottomTitle[i-2] = griff.get(i).getOpponentTeam().getTeamName4() + " - " + this_data_str.get(i).toUpperCase() + " T20I";
						}
//						BottomTitle[i-2] = griff.get(i).getOpponentTeam().getTeamName4() + " - " + this_data_str.get(i).toUpperCase() + " T20I";
						if(griff.get(i).getStatus().equalsIgnoreCase("DNB")) {
							BottomStats[i-2] = "DNB";
						}else if(griff.get(i).getStatus().equalsIgnoreCase("BALL")) {
							BottomStats[i-2] = griff.get(i).getWickets() + "-" + griff.get(i).getRunsConceded() + "," + griff.get(i).getOversBowled();
						}else {
							BottomStats[i-2] = "DNP";
						}
					}
				}
			}
			break;
		case 5:
			TopTitle = new String[3];
			TopStats = new String[3];
			BottomTitle = new String[2];
			BottomStats = new String[2];
			Position = new String[] {"-134","0","130","-66","66"};
			
			if(whatToProcess.split(",")[0].equalsIgnoreCase("Alt_F1")) {
				for(int i=0;i<=griffSize-1;i++) {
					if(i<3) {
						if(this_data_str.get(i).toUpperCase().equalsIgnoreCase("SEVENTH")) {
							TopTitle[i] = griff.get(i).getOpponentTeam().getTeamName4() + " - " + " FINAL";
						}else {
							TopTitle[i] = griff.get(i).getOpponentTeam().getTeamName4() + " - " + this_data_str.get(i).toUpperCase() + " T20I";
						}
//						TopTitle[i] = griff.get(i).getOpponentTeam().getTeamName4() + " - " + this_data_str.get(i).toUpperCase() + " T20I";
						if(griff.get(i).getStatus().equalsIgnoreCase(CricketUtil.STILL_TO_BAT)) {
							if(griff.get(i).getHow_out() != null && !griff.get(i).getHow_out().trim().isEmpty() && griff.get(i).getHow_out().equalsIgnoreCase(CricketUtil.RETIRED_HURT)) {
								TopStats[i] = griff.get(i).getRuns() + "," + griff.get(i).getBallsFaced();
							}else {
								TopStats[i] = "DNB";
							}
						}else if(griff.get(i).getStatus().equalsIgnoreCase("DNB")) {
							TopStats[i] = "DNB";
						}else if(griff.get(i).getStatus().equalsIgnoreCase(CricketUtil.OUT)) {
							TopStats[i] = griff.get(i).getRuns() + "," + griff.get(i).getBallsFaced();
						}else if(griff.get(i).getStatus().equalsIgnoreCase(CricketUtil.NOT_OUT)) {
							TopStats[i] = griff.get(i).getRuns() + "*," + griff.get(i).getBallsFaced();
						}else {
							TopStats[i] = "DNP";
						}
					}else {
						if(this_data_str.get(i).toUpperCase().equalsIgnoreCase("SEVENTH")) {
							BottomTitle[i-3] = griff.get(i).getOpponentTeam().getTeamName4() + " - " + " FINAL";
						}else {
							BottomTitle[i-3] = griff.get(i).getOpponentTeam().getTeamName4() + " - " + this_data_str.get(i).toUpperCase() + " T20I";
						}
//						BottomTitle[i-3] = griff.get(i).getOpponentTeam().getTeamName4() + " - " + this_data_str.get(i).toUpperCase() + " T20I";
						if(griff.get(i).getStatus().equalsIgnoreCase(CricketUtil.STILL_TO_BAT)) {
							if(griff.get(i).getHow_out() != null && !griff.get(i).getHow_out().trim().isEmpty() && griff.get(i).getHow_out().equalsIgnoreCase(CricketUtil.RETIRED_HURT)) {
								BottomStats[i-3] = griff.get(i).getRuns() + "," + griff.get(i).getBallsFaced();
							}else {
								BottomStats[i-3] = "DNB";
							}
						}else if(griff.get(i).getStatus().equalsIgnoreCase("DNB")) {
							BottomStats[i-3] = "DNB";
						}else if(griff.get(i).getStatus().equalsIgnoreCase(CricketUtil.OUT)) {
							BottomStats[i-3] = griff.get(i).getRuns() + "," + griff.get(i).getBallsFaced();
						}else if(griff.get(i).getStatus().equalsIgnoreCase(CricketUtil.NOT_OUT)) {
							BottomStats[i-3] = griff.get(i).getRuns() + "*," + griff.get(i).getBallsFaced();
						}else {
							BottomStats[i-3] = "DNP";
						}
					}
				}
			}else if(whatToProcess.split(",")[0].equalsIgnoreCase("Alt_F2")){
				for(int i=0;i<=griffSize-1;i++) {
					if(i<3) {
						if(this_data_str.get(i).toUpperCase().equalsIgnoreCase("SEVENTH")) {
							TopTitle[i] = griff.get(i).getOpponentTeam().getTeamName4() + " - " + " FINAL";
						}else {
							TopTitle[i] = griff.get(i).getOpponentTeam().getTeamName4() + " - " + this_data_str.get(i).toUpperCase() + " T20I";
						}
//						TopTitle[i] = griff.get(i).getOpponentTeam().getTeamName4() + " - " + this_data_str.get(i).toUpperCase() + " T20I";
						if(griff.get(i).getStatus().equalsIgnoreCase("DNB")) {
							TopStats[i] = "DNB";
						}else if(griff.get(i).getStatus().equalsIgnoreCase("BALL")) {
							TopStats[i] = griff.get(i).getWickets() + "-" + griff.get(i).getRunsConceded() + "," + griff.get(i).getOversBowled();
						}else {
							TopStats[i] = "DNP";
						}
					}else {
						if(this_data_str.get(i).toUpperCase().equalsIgnoreCase("SEVENTH")) {
							BottomTitle[i-3] = griff.get(i).getOpponentTeam().getTeamName4() + " - " + " FINAL";
						}else {
							BottomTitle[i-3] = griff.get(i).getOpponentTeam().getTeamName4() + " - " + this_data_str.get(i).toUpperCase() + " T20I";
						}
//						BottomTitle[i-3] = griff.get(i).getOpponentTeam().getTeamName4() + " - " + this_data_str.get(i).toUpperCase() + " T20I";
						if(griff.get(i).getStatus().equalsIgnoreCase("DNB")) {
							BottomStats[i-3] = "DNB";
						}else if(griff.get(i).getStatus().equalsIgnoreCase("BALL")) {
							BottomStats[i-3] = griff.get(i).getWickets() + "-" + griff.get(i).getRunsConceded() + "," + griff.get(i).getOversBowled();
						}else {
							BottomStats[i-3] = "DNP";
						}
					}
				}
			}
			break;
		case 6:
			TopTitle = new String[3];
			TopStats = new String[3];
			BottomTitle = new String[3];
			BottomStats = new String[3];
			Position = new String[] {"-134","0","130","-134","0","130"};
			
			if(whatToProcess.split(",")[0].equalsIgnoreCase("Alt_F1")) {
				for(int i=0;i<=griffSize-1;i++) {
					if(i<3) {
						if(this_data_str.get(i).toUpperCase().equalsIgnoreCase("SEVENTH")) {
							TopTitle[i] = griff.get(i).getOpponentTeam().getTeamName4() + " - " + " FINAL";
						}else {
							TopTitle[i] = griff.get(i).getOpponentTeam().getTeamName4() + " - " + this_data_str.get(i).toUpperCase() + " T20I";
						}
//						TopTitle[i] = griff.get(i).getOpponentTeam().getTeamName4() + " - " + this_data_str.get(i).toUpperCase() + " T20I";
						if(griff.get(i).getStatus().equalsIgnoreCase(CricketUtil.STILL_TO_BAT)) {
							if(griff.get(i).getHow_out() != null && !griff.get(i).getHow_out().trim().isEmpty() && griff.get(i).getHow_out().equalsIgnoreCase(CricketUtil.RETIRED_HURT)) {
								TopStats[i] = griff.get(i).getRuns() + "," + griff.get(i).getBallsFaced();
							}else {
								TopStats[i] = "DNB";
							}
						}else if(griff.get(i).getStatus().equalsIgnoreCase("DNB")) {
							TopStats[i] = "DNB";
						}else if(griff.get(i).getStatus().equalsIgnoreCase(CricketUtil.OUT)) {
							TopStats[i] = griff.get(i).getRuns() + "," + griff.get(i).getBallsFaced();
						}else if(griff.get(i).getStatus().equalsIgnoreCase(CricketUtil.NOT_OUT)) {
							TopStats[i] = griff.get(i).getRuns() + "*," + griff.get(i).getBallsFaced();
						}else {
							TopStats[i] = "DNP";
						}
					}else {
						if(this_data_str.get(i).toUpperCase().equalsIgnoreCase("SEVENTH")) {
							BottomTitle[i-3] = griff.get(i).getOpponentTeam().getTeamName4() + " - " + " FINAL";
						}else {
							BottomTitle[i-3] = griff.get(i).getOpponentTeam().getTeamName4() + " - " + this_data_str.get(i).toUpperCase() + " T20I";
						}
//						BottomTitle[i-3] = griff.get(i).getOpponentTeam().getTeamName4() + " - " + this_data_str.get(i).toUpperCase() + " T20I";
						if(griff.get(i).getStatus().equalsIgnoreCase(CricketUtil.STILL_TO_BAT)) {
							if(griff.get(i).getHow_out() != null && !griff.get(i).getHow_out().trim().isEmpty() && griff.get(i).getHow_out().equalsIgnoreCase(CricketUtil.RETIRED_HURT)) {
								BottomStats[i-3] = griff.get(i).getRuns() + "," + griff.get(i).getBallsFaced();
							}else {
								BottomStats[i-3] = "DNB";
							}
						}else if(griff.get(i).getStatus().equalsIgnoreCase("DNB")) {
							BottomStats[i-3] = "DNB";
						}else if(griff.get(i).getStatus().equalsIgnoreCase(CricketUtil.OUT)) {
							BottomStats[i-3] = griff.get(i).getRuns() + "," + griff.get(i).getBallsFaced();
						}else if(griff.get(i).getStatus().equalsIgnoreCase(CricketUtil.NOT_OUT)) {
							BottomStats[i-3] = griff.get(i).getRuns() + "*," + griff.get(i).getBallsFaced();
						}else {
							BottomStats[i-3] = "DNP";
						}
					}
				}
			}else if(whatToProcess.split(",")[0].equalsIgnoreCase("Alt_F2")){
				for(int i=0;i<=griffSize-1;i++) {
					if(i<3) {
						if(this_data_str.get(i).toUpperCase().equalsIgnoreCase("SEVENTH")) {
							TopTitle[i] = griff.get(i).getOpponentTeam().getTeamName4() + " - " + " FINAL";
						}else {
							TopTitle[i] = griff.get(i).getOpponentTeam().getTeamName4() + " - " + this_data_str.get(i).toUpperCase() + " T20I";
						}
//						TopTitle[i] = griff.get(i).getOpponentTeam().getTeamName4() + " - " + this_data_str.get(i).toUpperCase() + " T20I";
						if(griff.get(i).getStatus().equalsIgnoreCase("DNB")) {
							TopStats[i] = "DNB";
						}else if(griff.get(i).getStatus().equalsIgnoreCase("BALL")) {
							TopStats[i] = griff.get(i).getWickets() + "-" + griff.get(i).getRunsConceded() + "," + griff.get(i).getOversBowled();
						}else {
							TopStats[i] = "DNP";
						}
					}else {
						if(this_data_str.get(i).toUpperCase().equalsIgnoreCase("SEVENTH")) {
							BottomTitle[i-3] = griff.get(i).getOpponentTeam().getTeamName4() + " - " + " FINAL";
						}else {
							BottomTitle[i-3] = griff.get(i).getOpponentTeam().getTeamName4() + " - " + this_data_str.get(i).toUpperCase() + " T20I";
						}
//						BottomTitle[i-3] = griff.get(i).getOpponentTeam().getTeamName4() + " - " + this_data_str.get(i).toUpperCase() + " T20I";
						if(griff.get(i).getStatus().equalsIgnoreCase("DNB")) {
							BottomStats[i-3] = "DNB";
						}else if(griff.get(i).getStatus().equalsIgnoreCase("BALL")) {
							BottomStats[i-3] = griff.get(i).getWickets() + "-" + griff.get(i).getRunsConceded() + "," + griff.get(i).getOversBowled();
						}else {
							BottomStats[i-3] = "DNP";
						}
					}
				}
			}
			break;
		case 7:
			TopTitle = new String[4];
			TopStats = new String[4];
			BottomTitle = new String[3];
			BottomStats = new String[3];
			Position = new String[] {"-428","-130","167.0","460.0","-283.0","22.0","323.0"};
			
			if(whatToProcess.split(",")[0].equalsIgnoreCase("Alt_F1")) {
				for(int i=0;i<=griffSize-1;i++) {
					if(i<4) {
						if(this_data_str.get(i).toUpperCase().equalsIgnoreCase("SEVENTH")) {
							TopTitle[i] = griff.get(i).getOpponentTeam().getTeamName4() + " - " + " FINAL";
						}else {
							TopTitle[i] = griff.get(i).getOpponentTeam().getTeamName4() + " - " + this_data_str.get(i).toUpperCase() + " T20I";
						}
//						TopTitle[i] = griff.get(i).getOpponentTeam().getTeamName4() + " - " + this_data_str.get(i).toUpperCase() + " T20I";
						if(griff.get(i).getStatus().equalsIgnoreCase(CricketUtil.STILL_TO_BAT)) {
							if(griff.get(i).getHow_out() != null && !griff.get(i).getHow_out().trim().isEmpty() && griff.get(i).getHow_out().equalsIgnoreCase(CricketUtil.RETIRED_HURT)) {
								TopStats[i] = griff.get(i).getRuns() + "," + griff.get(i).getBallsFaced();
							}else {
								TopStats[i] = "DNB";
							}
						}else if(griff.get(i).getStatus().equalsIgnoreCase("DNB")) {
							TopStats[i] = "DNB";
						}else if(griff.get(i).getStatus().equalsIgnoreCase(CricketUtil.OUT)) {
							TopStats[i] = griff.get(i).getRuns() + "," + griff.get(i).getBallsFaced();
						}else if(griff.get(i).getStatus().equalsIgnoreCase(CricketUtil.NOT_OUT)) {
							TopStats[i] = griff.get(i).getRuns() + "*," + griff.get(i).getBallsFaced();
						}else {
							TopStats[i] = "DNP";
						}
					}else {
						if(this_data_str.get(i).toUpperCase().equalsIgnoreCase("SEVENTH")) {
							BottomTitle[i-4] = griff.get(i).getOpponentTeam().getTeamName4() + " - " + " FINAL";
						}else {
							BottomTitle[i-4] = griff.get(i).getOpponentTeam().getTeamName4() + " - " + this_data_str.get(i).toUpperCase() + " T20I";
						}
//						BottomTitle[i-4] = griff.get(i).getOpponentTeam().getTeamName4() + " - " + this_data_str.get(i).toUpperCase() + " T20I";
						if(griff.get(i).getStatus().equalsIgnoreCase(CricketUtil.STILL_TO_BAT)) {
							if(griff.get(i).getHow_out() != null && !griff.get(i).getHow_out().trim().isEmpty() && griff.get(i).getHow_out().equalsIgnoreCase(CricketUtil.RETIRED_HURT)) {
								BottomStats[i-4] = griff.get(i).getRuns() + "," + griff.get(i).getBallsFaced();
							}else {
								BottomStats[i-4] = "DNB";
							}
						}else if(griff.get(i).getStatus().equalsIgnoreCase("DNB")) {
							BottomStats[i-4] = "DNB";
						}else if(griff.get(i).getStatus().equalsIgnoreCase(CricketUtil.OUT)) {
							BottomStats[i-4] = griff.get(i).getRuns() + "," + griff.get(i).getBallsFaced();
						}else if(griff.get(i).getStatus().equalsIgnoreCase(CricketUtil.NOT_OUT)) {
							BottomStats[i-4] = griff.get(i).getRuns() + "*," + griff.get(i).getBallsFaced();
						}else {
							BottomStats[i-4] = "DNP";
						}
					}
				}
			}else if(whatToProcess.split(",")[0].equalsIgnoreCase("Alt_F2")){
				for(int i=0;i<=griffSize-1;i++) {
					if(i<4) {
						if(this_data_str.get(i).toUpperCase().equalsIgnoreCase("SEVENTH")) {
							TopTitle[i] = griff.get(i).getOpponentTeam().getTeamName4() + " - " + " FINAL";
						}else {
							TopTitle[i] = griff.get(i).getOpponentTeam().getTeamName4() + " - " + this_data_str.get(i).toUpperCase() + " T20I";
						}
//						TopTitle[i] = griff.get(i).getOpponentTeam().getTeamName4() + " - " + this_data_str.get(i).toUpperCase() + " T20I";
						if(griff.get(i).getStatus().equalsIgnoreCase("DNB")) {
							TopStats[i] = "DNB";
						}else if(griff.get(i).getStatus().equalsIgnoreCase("BALL")) {
							TopStats[i] = griff.get(i).getWickets() + "-" + griff.get(i).getRunsConceded() + "," + griff.get(i).getOversBowled();
						}else {
							TopStats[i] = "DNP";
						}
					}else {
						if(this_data_str.get(i).toUpperCase().equalsIgnoreCase("SEVENTH")) {
							BottomTitle[i-4] = griff.get(i).getOpponentTeam().getTeamName4() + " - " + " FINAL";
						}else {
							BottomTitle[i-4] = griff.get(i).getOpponentTeam().getTeamName4() + " - " + this_data_str.get(i).toUpperCase() + " T20I";
						}
//						BottomTitle[i-4] = griff.get(i).getOpponentTeam().getTeamName4() + " - " + this_data_str.get(i).toUpperCase() + " T20I";
						if(griff.get(i).getStatus().equalsIgnoreCase("DNB")) {
							BottomStats[i-4] = "DNB";
						}else if(griff.get(i).getStatus().equalsIgnoreCase("BALL")) {
							BottomStats[i-4] = griff.get(i).getWickets() + "-" + griff.get(i).getRunsConceded() + "," + griff.get(i).getOversBowled();
						}else {
							BottomStats[i-4] = "DNP";
						}
					}
				}
			}
			break;
		}
		
		if(whatToProcess.split(",")[0].equalsIgnoreCase("Alt_F1")){
			l3griff = new L3Griff("", player.getFull_name(), "", "", "", inning.getBatting_team().getTeamBadge(), numberOfSubLines, 
					TopTitle, TopStats, BottomTitle, BottomStats, Position);
		}else {
			l3griff = new L3Griff("", player.getFull_name(), "", "", "", inning.getBowling_team().getTeamBadge(), numberOfSubLines, 
					TopTitle, TopStats, BottomTitle, BottomStats, Position);
		}
	}
	public void resetLts() {
		CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*STAGE*DIRECTOR*"
				+ "LT$Logo$In_Out$In SHOW 0.0\0", print_writers);
		CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*STAGE*DIRECTOR*"
				+ "LT$Base$In_Out$In SHOW 0.0\0", print_writers);
		CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*STAGE*DIRECTOR*"
				+ "LT$Data$In_Out$In SHOW 0.0\0", print_writers);
		
	}
	public void HideAndShowGriffSubStrapContainers(int WhichSide)
	{
		switch (config.getBroadcaster().toUpperCase()) {
		case Constants.TRI_SERIES:  case Constants.MT20:
			//Show number of sublines
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$All_LowerThirds$Sublines$Side_"+ WhichSide + 
					"$Select_Subline$1$Select_Base*FUNCTION*Omo*vis_con SET 0 \0", print_writers);
			
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$SublineGrp$Side"+ WhichSide + 
					"$select_Subline*FUNCTION*Omo*vis_con SET " + l3griff.getNumberOfSubLines() + "\0", print_writers);
			
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$Overall_Position_Y_In_Out$SublineGrp$Side" + WhichSide +
					"$select_Subline$1$Stat*ACTIVE SET 0 \0", print_writers);
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$Overall_Position_Y_In_Out$SublineGrp$Side" + WhichSide +
					"$select_Subline$2$Title*ACTIVE SET 0 \0", print_writers);
			
			if(l3griff.getNumberOfSubLines() == 4) {
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$Overall_Position_Y_In_Out$SublineGrp$Side" + WhichSide +
						"$select_Subline$2$Stat*ACTIVE SET 1 \0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$Overall_Position_Y_In_Out$SublineGrp$Side" + WhichSide +
						"$select_Subline$3$Stat*ACTIVE SET 1 \0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$Overall_Position_Y_In_Out$SublineGrp$Side" + WhichSide +
						"$select_Subline$4$Stat*ACTIVE SET 1 \0", print_writers);
				
			}else if(l3griff.getNumberOfSubLines() == 3) {
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$Overall_Position_Y_In_Out$SublineGrp$Side" + WhichSide +
						"$select_Subline$2$Stat*ACTIVE SET 1 \0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$Overall_Position_Y_In_Out$SublineGrp$Side" + WhichSide +
						"$select_Subline$3$Stat*ACTIVE SET 1 \0", print_writers);
			}else if(l3griff.getNumberOfSubLines() == 2) {
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$Overall_Position_Y_In_Out$SublineGrp$Side" + WhichSide +
						"$select_Subline$2$Stat*ACTIVE SET 1 \0", print_writers);
			}
			
			//HIDE AND SHOW TITLE
			if(l3griff.getTopTitlesText() != null) {
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$Overall_Position_Y_In_Out$SublineGrp$Side" + WhichSide +
						"$select_Subline$1$Title*ACTIVE SET 1 \0", print_writers);
				
				
				for(int iTitle = 1; iTitle <= l3griff.getTopTitlesText().length; iTitle++) {
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$Overall_Position_Y_In_Out$SublineGrp$Side" + WhichSide +
							"$select_Subline$1$Title$txt_" + iTitle + "*ACTIVE SET 1 \0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$Overall_Position_Y_In_Out$SublineGrp$Side" + WhichSide +
							"$select_Subline$1$Title$txt_" + iTitle + "*TRANSFORMATION*POSITION*X SET "+ l3griff.getPosition()[iTitle-1] + "\0", print_writers);
					
				}
				//Hide number of Titles on each strap
				for(int iTitle = l3griff.getTopTitlesText().length + 1; iTitle <= 10; iTitle++) {
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$Overall_Position_Y_In_Out$SublineGrp$Side" + WhichSide +
							"$select_Subline$1$Title$txt_" + iTitle + "*ACTIVE SET 0 \0", print_writers);
				}
			}else {
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$Overall_Position_Y_In_Out$SublineGrp$Side" + WhichSide +
						"$select_Subline$1$Title*ACTIVE SET 0 \0", print_writers);
			}
			
			// HIDE AND SHOW STATS
			if(l3griff.getTopStatsText() != null) {
				
				for(int iStats = 1; iStats <= l3griff.getTopStatsText().length; iStats++) {
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$Overall_Position_Y_In_Out$SublineGrp$Side" + WhichSide +
							"$select_Subline$2$Stat$txt_" + iStats + "*ACTIVE SET 1 \0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$Overall_Position_Y_In_Out$SublineGrp$Side" + WhichSide +
							"$select_Subline$2$Stat$txt_" + iStats + "*TRANSFORMATION*POSITION*X SET "+ 
							l3griff.getPosition()[iStats-1] + " \0", print_writers);
				}
				//Hide number of Titles on each strap
				for(int iStats = l3griff.getTopStatsText().length + 1; iStats <= 10; iStats++) {
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$Overall_Position_Y_In_Out$SublineGrp$Side" + WhichSide +
							"$select_Subline$2$Stat$txt_" + iStats + "*ACTIVE SET 0 \0", print_writers);
				}
			}else {
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$Overall_Position_Y_In_Out$SublineGrp$Side" + WhichSide +
						"$select_Subline$2$Stat*ACTIVE SET 0 \0", print_writers);
			}
			
			//HIDE AND SHOW TITLE
			if(l3griff.getBottomTitlesText() != null) {
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$Overall_Position_Y_In_Out$SublineGrp$Side" + WhichSide +
						"$select_Subline$3$Title*ACTIVE SET 1 \0", print_writers);
				
				for(int iTitle = 1; iTitle <= l3griff.getBottomTitlesText().length; iTitle++) {
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$Overall_Position_Y_In_Out$SublineGrp$Side" + WhichSide +
							"$select_Subline$3$Title$txt_" + iTitle + "*ACTIVE SET 1 \0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$Overall_Position_Y_In_Out$SublineGrp$Side" + WhichSide +
							"$select_Subline$3$Title$txt_" + iTitle + "*TRANSFORMATION*POSITION*X SET " + l3griff.getPosition()[l3griff.getTopTitlesText().length + (iTitle-1)] + " \0", print_writers);
					
				}
				//Hide number of Titles on each strap
				for(int iTitle = l3griff.getBottomTitlesText().length + 1; iTitle <= 10; iTitle++) {
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$Overall_Position_Y_In_Out$SublineGrp$Side" + WhichSide +
							"$select_Subline$3$Title$txt_" + iTitle + "*ACTIVE SET 0 \0", print_writers);
				}
			}else {
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$Overall_Position_Y_In_Out$SublineGrp$Side" + WhichSide +
						"$select_Subline$3$Title*ACTIVE SET 0 \0", print_writers);
			}
			
			// HIDE AND SHOW STATS
			if(l3griff.getBottomStatsText() != null) {
				
				for(int iStats = 1; iStats <= l3griff.getBottomStatsText().length; iStats++) {
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$Overall_Position_Y_In_Out$SublineGrp$Side" + WhichSide +
							"$select_Subline$4$Stat$txt_" + iStats + "*ACTIVE SET 1 \0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$Overall_Position_Y_In_Out$SublineGrp$Side" + WhichSide +
							"$select_Subline$4$Stat$txt_" + iStats + "*TRANSFORMATION*POSITION*X SET " + 
							l3griff.getPosition()[l3griff.getTopStatsText().length + (iStats-1)] + " \0", print_writers);
				}
				//Hide number of Titles on each strap
				for(int iStats = l3griff.getBottomStatsText().length + 1; iStats <= 10; iStats++) {
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$Overall_Position_Y_In_Out$SublineGrp$Side" + WhichSide +
							"$select_Subline$4$Stat$txt_" + iStats + "*ACTIVE SET 0 \0", print_writers);
				}
			}else {
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$Overall_Position_Y_In_Out$SublineGrp$Side" + WhichSide +
						"$select_Subline$4$Stat*ACTIVE SET 0 \0", print_writers);
			}
			break;
		}
	}
	public static List<String> PlayerProgression(int PlayerId,MatchAllData match,List<Event> events) {
		int PlyrRuns = 0,PlyrBalls = 0,Runs = 0,diff=0,total_balls=0,total_runs =0,val=0;
		boolean go_ahead = false;
		String out_not = "";
		
		for(Inning inn : match.getMatch().getInning()) {
			for(BattingCard bc : inn.getBattingCard()) {
				if(bc.getPlayerId() == PlayerId) {
					Runs = bc.getRuns();
					PlyrRuns = bc.getRuns();
					PlyrBalls = bc.getBalls();
					if(bc.getStatus().equalsIgnoreCase(CricketUtil.OUT)) {
						out_not = "OUT";
//						System.out.println(out_not);
					}else if(bc.getStatus().equalsIgnoreCase(CricketUtil.NOT_OUT)) {
						out_not = "NOT OUT";
					}
					break;
				}
			}
		}
		
		if(Runs >= 50) {
			while (Runs % 10 != 0) {
				Runs = Runs + 1;
			}
		}
		
		diff = Runs/5;
		val = diff;
		List<String> Balls = new ArrayList<String>();
		List<String> Phase = new ArrayList<String>();
		List<String> result = new ArrayList<>();
		
		for(int i = 1; i<=5; i++) {
			if(i==1) {
				Phase.add("1-"+val);
			}else if(i==2) {
				Phase.add((val+1)+"-"+(val+diff));
				val = val+diff;
			}else if(i==3) {
				Phase.add((val+1)+"-"+(val+diff));
				val = val+diff;
			}else if(i==4) {
				Phase.add((val+1)+"-"+(val+diff));
				val = val+diff;
			}else if(i==5) {
				Phase.add((val+1)+"-"+PlyrRuns);
			}
		}
		
		
		if((events != null) && (events.size() > 0)) {
			for (int i = 0; i <= events.size() - 1; i++) {
				if(events.get(i).getEventBatterNo() == PlayerId) {
					switch (events.get(i).getEventType()) {
					case CricketUtil.DOT: case CricketUtil.ONE: case CricketUtil.TWO: case CricketUtil.THREE: case CricketUtil.FOUR:  
					case CricketUtil.FIVE: case CricketUtil.SIX: case CricketUtil.NINE: case CricketUtil.LEG_BYE: case CricketUtil.BYE: 
						total_balls = total_balls + 1;
						total_runs = total_runs + events.get(i).getEventRuns();
						break;
					
					case CricketUtil.LOG_WICKET:
						if(events.get(i).getEventHowOut().equalsIgnoreCase(CricketUtil.CAUGHT) || events.get(i).getEventHowOut().equalsIgnoreCase(CricketUtil.CAUGHT_AND_BOWLED)
                        		|| events.get(i).getEventHowOut().equalsIgnoreCase(CricketUtil.BOWLED) || events.get(i).getEventHowOut().equalsIgnoreCase(CricketUtil.STUMPED)
                        		|| events.get(i).getEventHowOut().equalsIgnoreCase(CricketUtil.LBW) || events.get(i).getEventHowOut().equalsIgnoreCase(CricketUtil.HIT_WICKET)
                        		|| events.get(i).getEventHowOut().equalsIgnoreCase(CricketUtil.HIT_BALL_TWICE)) {
							
							total_balls = total_balls + 1;
							total_runs = total_runs + events.get(i).getEventRuns();
						}
						break;
						
					case CricketUtil.LOG_ANY_BALL:
						if (events.get(i).getEventExtra().equalsIgnoreCase(CricketUtil.NO_BALL)) {
                            if (events.get(i).getEventHowOut().equalsIgnoreCase(CricketUtil.RUN_OUT)) {
                            	total_balls = total_balls + 1 ;
    							total_runs = total_runs + events.get(i).getEventRuns();
                            }
                            if ((events.get(i).getEventRuns() == Integer.valueOf(CricketUtil.FOUR)) && (events.get(i).getEventWasABoundary() != null) &&
                                    (events.get(i).getEventWasABoundary().equalsIgnoreCase(CricketUtil.YES))) {
                            	total_balls = total_balls + 1 ;
    							total_runs = total_runs + events.get(i).getEventRuns();
                            }
                            if ((events.get(i).getEventRuns() == Integer.valueOf(CricketUtil.SIX)) && (events.get(i).getEventWasABoundary() != null) &&
                                    (events.get(i).getEventWasABoundary().equalsIgnoreCase(CricketUtil.YES))) {
                            	total_balls = total_balls + 1 ;
    							total_runs = total_runs + events.get(i).getEventRuns();
                            }
                        }
			          break;
					}
					
					
					if(total_runs >= diff) {
						
						Balls.add(String.valueOf(total_balls));
						total_runs = total_runs - diff;
						total_balls = 0;
						
						continue;
					}
					
					if(Balls.size() == 4 && !go_ahead) {
						diff = (PlyrRuns-(4*diff));
						if(out_not.equalsIgnoreCase("OUT")) {
							//total_balls = total_balls + 1;
//							System.out.println("YES");
						}
						go_ahead = true;
					}
				}
			}
		}
		
		if (Balls.size() == 5) {
		    int ballsSum = 0;

		    for (String b : Balls) {
		        ballsSum += Integer.parseInt(b);
		    }

		    if (ballsSum != PlyrBalls) {
		        int remainingBalls = PlyrBalls - ballsSum;

		        if (remainingBalls > 0) {
		            int lastPhaseBalls = Integer.parseInt(Balls.get(4));
		            Balls.set(4, String.valueOf(lastPhaseBalls + remainingBalls));
		        }
		    }
		}
		
		result.addAll(Balls);
		result.addAll(Phase);
//		System.out.println(result);
		return result;
	}
}