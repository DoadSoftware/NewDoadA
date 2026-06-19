package com.cricket.captions;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import jakarta.xml.bind.JAXBContext;
import com.cricket.broadcaster.ALL_FF;
import com.cricket.broadcaster.FC_FF;
import com.cricket.containers.ImpactData;
import com.cricket.model.BattingCard;
import com.cricket.model.BestStats;
import com.cricket.model.Configuration;
import com.cricket.model.EventFile;
import com.cricket.model.Fixture;
import com.cricket.model.ForeignLanguageData;
import com.cricket.model.Ground;
import com.cricket.model.HeadToHeadPlayer;
import com.cricket.model.Inning;
import com.cricket.model.LeagueTable;
import com.cricket.model.Match;
import com.cricket.model.MatchAllData;
import com.cricket.model.MultiLanguageDatabase;
import com.cricket.model.OverByOverData;
import com.cricket.model.POTT;
import com.cricket.model.Partnership;
import com.cricket.model.Player;
import com.cricket.model.Playoff;
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

public class FullFramesGfx 
{
	public int FirstPlayerId, rowId = 0, rowId1 = 0, plyr_count=0, numberOfRows = 0,omo=0,whichTeam;
	public String WhichScoreCard, WhichProfile, WhichStyle, WhichType, WhichGroup,status = "", containerName = "",
			containerName_2 = "",containerName_3 = "",containerName_4 = "",logo_name = "" , color_name = "",
			logo_name1 = "" , color_name1 = "",Player_photo="", whichGFX = "",whichtype = "", phaseWiseScore = "",
			manhattanOrNot = "",logoCategory = "";
	
	public int pervious_batperformer_id=0, batperformer_id = 0,pervious_ballperformer_id=0, ballperformer_id = 0;
	public static int player_position = 0;
	
	@JsonIgnore
	public List<PrintWriter> print_writers; 
	public Configuration config;
	public List<Statistics> statistics;
	public List<StatsType> statsTypes;
	public List<Tournament> tournaments;
	public List<MatchAllData> tournament_matches;
	public List<Fixture> fixTures;
	public List<Team> Teams;
	public List<Playoff> Playoffs;
	public List<Ground> Grounds;
	public List<VariousText> VariousText;
	public List<Player> Players;
	public List<POTT> Potts;
	public List<String> TeamChanges;
	public List<HeadToHeadPlayer> headToHead;
	public List<Tournament> past_tournament_stats;
	public List<Tournament> stats_past_tournament;
	public List<Integer> PlayerId, PlayerIdIn;
	
	ArrayList<BestStats> batter_data = new ArrayList<BestStats>();
	public List<Tournament> addPastDataToCurr = new ArrayList<Tournament>();
	public List<BestStats> top_batsman_beststats,top_bowler_beststats = new ArrayList<BestStats>();
	public List<BestStats> top_batsman_beststat= new ArrayList<BestStats>();
	public List<Player> PlayingXI,otherSquad;

	public Partnership partnership;
	public BattingCard battingCard;
	public Inning inning;
	public Player player;
	public Statistics stat;
	public StatsType statsType;
	public VariousText variousText;
	public Tournament tournament;
	
	@JsonIgnore
	public CricketService cricketService;
	public MultiLanguageDatabase multilanguagedata;
	public List<ForeignLanguageData> foreignLanguageDataList;
	
	public Fixture fixture;
	public Team team;
	public Ground ground;
	public Playoff playoff;
	public LeagueTable leagueTable;
	public String whichSponsor;
	public FC_FF this_FC_FF = new FC_FF();
	public ALL_FF this_ALL_FF = new ALL_FF();
	
	String varText = "";
	
	public List<BattingCard> battingCardList = new ArrayList<BattingCard>();
	public List<Fixture> FixturesList = new ArrayList<Fixture>();
	public List<OverByOverData> manhattan, manhattan2 = new ArrayList<OverByOverData>();
	public List<Statistics> statisticsList = new ArrayList<Statistics>();
	public List<Tournament> tournament_stats = new ArrayList<Tournament>();
	public List<Tournament> gender_Specific_tournament_stats = new ArrayList<Tournament>();
	public List<BestStats> tape_ball = new ArrayList<BestStats>();
	public List<BestStats> log_fifty = new ArrayList<BestStats>();
	public MatchAllData previous_match = new MatchAllData();
	public List<String> team_change = new ArrayList<String>();
	public List<ImpactData> impactList = new ArrayList<ImpactData>();
	
	public FullFramesGfx() {
		super();
	}	
	
	public String getWhichGFX() {
		return whichGFX;
	}

	public void setWhichGFX(String whichGFX) {
		this.whichGFX = whichGFX;
	}

	public FullFramesGfx(List<PrintWriter> print_writers, Configuration config, List<Statistics> statistics, List<StatsType> statsTypes, 
			List<MatchAllData> tournament_matches,List<Fixture> fixTures, List<Team> Teams, List<Ground> Grounds,List<Tournament> tournaments, 
			List<VariousText> VariousText, List<Player> players, List<POTT> pott,List<Playoff> Playoffs, List<String> teamChanges, List<HeadToHeadPlayer> headToHead, 
			List<Tournament> past_tournament_stats, CricketService cricketService) {
		super();
		this.print_writers = print_writers;
		this.config = config;
		this.statistics = statistics;
		this.statsTypes = statsTypes;
		this.tournament_matches = tournament_matches;
		this.fixTures = fixTures;
		this.Teams = Teams;
		this.Grounds = Grounds;
		this.tournaments = tournaments;
		this.VariousText = VariousText;
		this.Players = players;
		this.Potts = pott;
		this.Playoffs = Playoffs;
		this.TeamChanges = teamChanges;
		this.headToHead = headToHead;
		this.past_tournament_stats = past_tournament_stats;
		this.cricketService = cricketService;
		this_ALL_FF.foreignLanguageData.add(new ForeignLanguageData("", "", "", ""));
		this_ALL_FF.foreignLanguageOmo.add(new ForeignLanguageData("0", "2", "3", "1"));
	}

	public String populateSingleTeamsCareer(int WhichSide, String whatToProcess, MatchAllData matchAllData, int WhichInning) throws Exception
	{	
		this_ALL_FF.WhichProfile = "IT20";
		this_ALL_FF.WhichStyle = whatToProcess.split(",")[3];
		this_ALL_FF.WhichType = whatToProcess.split(",")[4];
		
		if(Integer.valueOf(whatToProcess.split(",")[2]) == matchAllData.getSetup().getHomeTeamId()) {
			this_ALL_FF.PlayingXI = matchAllData.getSetup().getHomeSquad();
		}else {
			this_ALL_FF.PlayingXI = matchAllData.getSetup().getAwaySquad();
		}
		
		inning = matchAllData.getMatch().getInning().stream().filter(inn -> inn.getBattingTeamId() == Integer.valueOf(whatToProcess.split(",")[2]))
				.findAny().orElse(null);
		if(inning == null) {
			return "populateSingleTeams current inning is NULL";
		}
		
		this_ALL_FF.statsType = statsTypes.stream().filter(st -> st.getStatsShortName().equalsIgnoreCase(this_ALL_FF.WhichProfile)).findAny().orElse(null);
		if(this_ALL_FF.statsType == null) {
			return "populateSingleTeams: Stats Type not found for profile [" + this_ALL_FF.WhichProfile + "]";
		}
		System.out.println(this_ALL_FF.statsType.getStatsFullName());
		
		this_ALL_FF.statisticsList = new ArrayList<Statistics>();
		for(Statistics stat : statistics) {
			if(stat.getStatsTypeId() == this_ALL_FF.statsType.getStatsId()) {
				stat.setStats_type(this_ALL_FF.statsType);
				stat = CricketFunctions.updateTournamentWithH2h(stat, headToHead, matchAllData, CricketUtil.FULL);
				stat = CricketFunctions.updateStatisticsWithMatchData(stat, matchAllData, CricketUtil.FULL);
				this_ALL_FF.statisticsList.add(stat);
			}
		}
		
		if(this_ALL_FF.statisticsList == null && this_ALL_FF.statisticsList.isEmpty()) {
			return "populateSingleTeams: Stats List is null";
		}
		
		status = PopulateFfHeader(WhichSide, whatToProcess.split(",")[0], matchAllData, 0);
		if(status == Constants.OK) {
			setBasePosition(print_writers, WhichSide, whatToProcess, config);
			status = PopulateFfBody(WhichSide, whatToProcess.split(",")[0], matchAllData, 0);
			if(status == Constants.OK) {
				return PopulateFfFooter(WhichSide, whatToProcess.split(",")[0], matchAllData, 0);
			} else {
				return status;
			}
		} else {
			return status;
		}
	}
	public String PopulateScoreBowlingCardFF(int WhichSide, String whatToProcess, MatchAllData matchAllData, int WhichInning) throws Exception
	{
		inning = matchAllData.getMatch().getInning().stream().filter(inn -> inn.getInningNumber() == WhichInning)
			.findAny().orElse(null);
		if(inning == null) {
			return "PopulateScoreBowlingCardFF: current inning is NULL";
		}
		
		status = PopulateFfHeader(WhichSide, whatToProcess, matchAllData, WhichInning);
		if(status == Constants.OK) {
			setBasePosition(print_writers, WhichSide, whatToProcess, config);
			status = PopulateFfBody(WhichSide, whatToProcess, matchAllData, WhichInning);
			if(status == Constants.OK) {
				setFullFrameFooterPosition(WhichSide, 1, whatToProcess);
				return PopulateFfFooter(WhichSide, whatToProcess, matchAllData, WhichInning);
			} else {
				return status;
			}
		} else {
			return status;
		}
	}
	public String PopulateScoreCardContributionFF(int WhichSide, String whatToProcess, MatchAllData matchAllData, int WhichInning) throws Exception
	{
		inning = matchAllData.getMatch().getInning().stream().filter(inn -> inn.getInningNumber() == WhichInning)
			.findAny().orElse(null);
		if(inning == null) {
			return "PopulateScoreCardContributionFF: current inning is NULL";
		}
		
		status = PopulateFfHeader(WhichSide, whatToProcess, matchAllData, WhichInning);
		if(status == Constants.OK) {
			setBasePosition(print_writers, WhichSide, whatToProcess, config);
			status = PopulateFfBody(WhichSide, whatToProcess, matchAllData, WhichInning);
			if(status == Constants.OK) {
				setFullFrameFooterPosition(WhichSide, 1, whatToProcess);
				return PopulateFfFooter(WhichSide, whatToProcess, matchAllData, WhichInning);
			} else {
				return status;
			}
		} else {
			return status;
		}
	}
	public String PopulatePhotoScorecardFF(int WhichSide, String whatToProcess, MatchAllData matchAllData, int WhichInning) throws Exception
	{
		inning = matchAllData.getMatch().getInning().stream().filter(inn -> inn.getInningNumber() == WhichInning).findAny().orElse(null);
		if(inning == null) {
			return "PopulatePhotoScorecardFF: current inning is NULL";
		}
		
		if(matchAllData.getMatch().getInning().get(WhichInning-1).getBattingTeamId() == matchAllData.getSetup().getHomeTeamId()) {
			this_ALL_FF.PlayingXI = matchAllData.getSetup().getHomeSquad();
		}else {
			this_ALL_FF.PlayingXI = matchAllData.getSetup().getAwaySquad();
		}
		
		status = PopulateFfHeader(WhichSide, whatToProcess, matchAllData, WhichInning);
		if(status == Constants.OK) {
			setBasePosition(print_writers, WhichSide, whatToProcess, config);
			status = PopulateFfBody(WhichSide, whatToProcess, matchAllData, WhichInning);
			if(status == Constants.OK) {
				return PopulateFfFooter(WhichSide, whatToProcess, matchAllData, WhichInning);
			} else {
				return status;
			}
		} else {
			return status;
		}
	}
	public String PopulateScorecardFF(int WhichSide, String whatToProcess, MatchAllData matchAllData, int WhichInning) throws Exception
	{
		inning = matchAllData.getMatch().getInning().stream().filter(inn -> inn.getInningNumber() == WhichInning)
			.findAny().orElse(null);
		if(inning == null) {
			return "PopulateScorecardFF: current inning is NULL";
		}
		
		status = PopulateFfHeader(WhichSide, whatToProcess, matchAllData, WhichInning);
		if(status == Constants.OK) {
			setBasePosition(print_writers, WhichSide, whatToProcess, config);
			status = PopulateFfBody(WhichSide, whatToProcess, matchAllData, WhichInning);
			if(status == Constants.OK) {
				setFullFrameFooterPosition(WhichSide, 1, whatToProcess);
				return PopulateFfFooter(WhichSide, whatToProcess, matchAllData, WhichInning);
			} else {
				return status;
			}
		} else {
			return status;
		}
	}
	public String PopulateScorecardBatPerformerFF(int WhichSide, String whatToProcess, MatchAllData matchAllData, int WhichInning) throws Exception
	{
		inning = matchAllData.getMatch().getInning().stream().filter(inn -> inn.getInningNumber() == WhichInning)
			.findAny().orElse(null);
		if(inning == null) {
			return "PopulateScorecardFF: current inning is NULL";
		}
		
		status = PopulateFfHeader(WhichSide, whatToProcess, matchAllData, WhichInning);
		if(status == Constants.OK) {
			setBasePosition(print_writers, WhichSide, whatToProcess, config);
			status = PopulateFfBody(WhichSide, whatToProcess, matchAllData, WhichInning);
			if(status == Constants.OK) {
				setFullFrameFooterPosition(WhichSide, 1, whatToProcess);
				return PopulateFfFooter(WhichSide, whatToProcess, matchAllData, WhichInning);
			} else {
				return status;
			}
		} else {
			return status;
		}
	}
	public String PopulateBowlingCardBallPerformerFF(int WhichSide, String whatToProcess, MatchAllData matchAllData, int WhichInning) throws Exception
	{
		inning = matchAllData.getMatch().getInning().stream().filter(inn -> inn.getInningNumber() == WhichInning)
			.findAny().orElse(null);
		if(inning == null) {
			return "PopulateScorecardFF: current inning is NULL";
		}
		
		status = PopulateFfHeader(WhichSide, whatToProcess, matchAllData, WhichInning);
		if(status == Constants.OK) {
			setBasePosition(print_writers, WhichSide, whatToProcess, config);
			status = PopulateFfBody(WhichSide, whatToProcess, matchAllData, WhichInning);
			if(status == Constants.OK) {
				setFullFrameFooterPosition(WhichSide, 1, whatToProcess);
				return PopulateFfFooter(WhichSide, whatToProcess, matchAllData, WhichInning);
			} else {
				return status;
			}
		} else {
			return status;
		}
	}
	public String PopulateBatPerformerFF(int WhichSide, String whatToProcess, MatchAllData matchAllData, int WhichInning) throws Exception
	{
		inning = matchAllData.getMatch().getInning().stream().filter(inn -> inn.getInningNumber() == WhichInning).findAny().orElse(null);
		
		if(inning == null) {
			return "PopulateBatPerformerFF: current inning is NULL";
		}
		setBasePosition(print_writers, WhichSide, whatToProcess, config);
		status = PopulateFfBody(WhichSide, whatToProcess, matchAllData, WhichInning);
		return status;
	}
	
	public String PopulateBallPerformerFF(int WhichSide, String whatToProcess, MatchAllData matchAllData, int WhichInning) throws Exception
	{
		inning = matchAllData.getMatch().getInning().stream().filter(inn -> inn.getInningNumber() == WhichInning).findAny().orElse(null);
		if(inning == null) {
			return "PopulateBatPerformerFF: current inning is NULL";
		}
		
		setBasePosition(print_writers, WhichSide, whatToProcess, config);
		status = PopulateFfBody(WhichSide, whatToProcess, matchAllData, WhichInning);
		return status;
	}
	public String PopulateBowlingCardFF(int WhichSide,String whatToProcess, MatchAllData matchAllData, int WhichInning) throws Exception
	{
		inning = matchAllData.getMatch().getInning().stream().filter(inn -> inn.getInningNumber() == WhichInning)
				.findAny().orElse(null);
		if(inning == null) {
			return "populateMatchSummary: current inning is NULL";
		}
		status = PopulateFfHeader(WhichSide, whatToProcess, matchAllData, WhichInning);
		if(status == Constants.OK) {
			setBasePosition(print_writers, WhichSide, whatToProcess, config);
			status = PopulateFfBody(WhichSide, whatToProcess, matchAllData, WhichInning);
			if(status == Constants.OK) {
				return PopulateFfFooter(WhichSide, whatToProcess, matchAllData, WhichInning);
			} else {
				return status;
			}
		} else {
			return status;
		}
	}
	public String populatePartnership(int WhichSide, String whatToProcess, MatchAllData matchAllData, int WhichInning) throws Exception 
	{
		inning = matchAllData.getMatch().getInning().stream().filter(inn -> inn.getInningNumber() == WhichInning).findAny().orElse(null);
		if(inning == null) {
			return "populateMatchSummary: current inning is NULL";
		}
		status = PopulateFfHeader(WhichSide, whatToProcess.split(",")[0], matchAllData, WhichInning);
		if(status == Constants.OK) {
			setBasePosition(print_writers, WhichSide, whatToProcess, config);
			status = PopulateFfBody(WhichSide, whatToProcess.split(",")[0], matchAllData, WhichInning);
			if(status == Constants.OK) {
				return PopulateFfFooter(WhichSide, whatToProcess.split(",")[0], matchAllData, WhichInning);
			} else {
				return status;
			}
		} else {
			return status;
		}
	}
	public String populateMatchSummary(int WhichSide, String whatToProcess, MatchAllData matchAllData, int WhichInning) throws Exception
	{
		if(!matchAllData.getMatch().getSourceOfThisMatchData().equalsIgnoreCase("WEBSITE")) {
			ground = Grounds.stream().filter(grnd -> grnd.getFullname().contains(matchAllData.getSetup().getVenueName())).findAny().orElse(null);
			if(ground == null) {
				return "populateMatchSummary: Ground Name [" + matchAllData.getSetup().getVenueName() + "] from database is returning NULL";
			}
		}
		
		inning = matchAllData.getMatch().getInning().stream().filter(inn -> inn.getInningNumber() == WhichInning).findAny().orElse(null);
		if(inning == null) {
			return "populateMatchSummary: current inning is NULL";
		}
		
		this_ALL_FF.VariousText = VariousText;
		
		status = PopulateFfHeader(WhichSide, whatToProcess, matchAllData, WhichInning);
		if(status == Constants.OK) {
			setBasePosition(print_writers, WhichSide, whatToProcess, config);
			status = PopulateFfBody(WhichSide, whatToProcess, matchAllData, WhichInning);
			if(status == Constants.OK) {
				return PopulateFfFooter(WhichSide, whatToProcess, matchAllData, WhichInning);
			} else {
				return status;
			}
		} else {
			return status;
		}
	}
	public String populateCurrPartnership(int WhichSide, String whatToProcess, MatchAllData matchAllData, int WhichInning) throws Exception
	{
		inning = matchAllData.getMatch().getInning().stream().filter(inn -> inn.getIsCurrentInning().equalsIgnoreCase(CricketUtil.YES))
			.findAny().orElse(null);

		if(inning == null) {
			return "populateCurrPartnership: Current Inning NOT found in this match";
		}
		
		if(inning.getPartnerships() != null && inning.getPartnerships().size() <= 0) {
			return "populateCurrPartnership: Partnership NOT found in this match";
		}
		
		List<Partnership > part = CricketFunctions.ConcussedPartnership(matchAllData.getMatch(), inning.getInningNumber());
		this_ALL_FF.partnership = part.get(part.size()-1);
		
		for(BattingCard bc : inning.getBattingCard()) {
			if(bc.getPlayerId() == this_ALL_FF.partnership.getFirstBatterNo()) {
				this_ALL_FF.partnership.setFirstPlayer(bc.getPlayer());
			}
			if(bc.getPlayerId() == this_ALL_FF.partnership.getSecondBatterNo()) {
				this_ALL_FF.partnership.setSecondPlayer(bc.getPlayer());
			}
		}
		if(this_ALL_FF.partnership == null) {
			return "populateCurrPartnership Partnership is null";
		}
		status = PopulateFfHeader(WhichSide, whatToProcess, matchAllData, WhichInning);
		if(status == Constants.OK) {
			setBasePosition(print_writers, WhichSide, whatToProcess, config);
			status = PopulateFfBody(WhichSide, whatToProcess, matchAllData, WhichInning);
			if(status == Constants.OK) {
				return PopulateFfFooter(WhichSide, whatToProcess, matchAllData, WhichInning);
			} else {
				return status;
			}
		} else {
			return status;
		}
	}
	public String populateManhattan(int WhichSide, String whatToProcess, MatchAllData matchAllData, int WhichInning) throws Exception
	{
		this_ALL_FF.manhattan = new ArrayList<OverByOverData>();
		this_ALL_FF.manhattan = CricketFunctions.getOverByOverData(matchAllData, WhichInning,"MANHATTAN" ,matchAllData.getEventFile().getEvents());
		if(this_ALL_FF.manhattan == null) {
			return "populateManhattan is null";
		}
		
		inning = matchAllData.getMatch().getInning().stream().filter(inn -> inn.getInningNumber() == WhichInning).findAny().orElse(null);
		if(inning == null) {
			return "populateManhattan: current inning is NULL";
		}
		
		status = PopulateFfHeader(WhichSide, whatToProcess, matchAllData, WhichInning);
		if(status == Constants.OK) {
			setBasePosition(print_writers, WhichSide, whatToProcess, config);
			status = PopulateFfBody(WhichSide, whatToProcess, matchAllData, WhichInning);
			if(status == Constants.OK) {
				return PopulateFfFooter(WhichSide, whatToProcess, matchAllData, WhichInning);
			} else {
				return status;
			}
		} else {
			return status;
		}
	}
	public String populateDoubleManhattan(int WhichSide, String whatToProcess, MatchAllData matchAllData, int WhichInning) throws Exception
	{
		this_ALL_FF.manhattan = new ArrayList<OverByOverData>();
		this_ALL_FF.manhattan2 = new ArrayList<OverByOverData>();
		this_ALL_FF.manhattan = CricketFunctions.getOverByOverData(matchAllData, 1,"MANHATTAN" ,matchAllData.getEventFile().getEvents());
		this_ALL_FF.manhattan2 = CricketFunctions.getOverByOverData(matchAllData, 2,"MANHATTAN" ,matchAllData.getEventFile().getEvents());
		if(this_ALL_FF.manhattan == null || this_ALL_FF.manhattan2 == null) {
			return "One of the Manhattans are null";
		}
		
		inning = matchAllData.getMatch().getInning().stream().filter(inn -> inn.getInningNumber() == WhichInning).findAny().orElse(null);
		if(inning == null) {
			return "populateManhattan: current inning is NULL";
		}
		
		status = PopulateFfHeader(WhichSide, whatToProcess, matchAllData, WhichInning);
		if(status == Constants.OK) {
			setBasePosition(print_writers, WhichSide, whatToProcess, config);
			status = PopulateFfBody(WhichSide, whatToProcess, matchAllData, WhichInning);
			if(status == Constants.OK) {
				return PopulateFfFooter(WhichSide, whatToProcess, matchAllData, WhichInning);
			} else {
				return status;
			}
		} else {
			return status;
		}
	}	
	public String populateWorms(int WhichSide, String whatToProcess, MatchAllData matchAllData,int WhichInning) throws Exception
	{
		ground = Grounds.stream().filter(grnd -> grnd.getFullname().contains(matchAllData.getSetup().getVenueName())).findAny().orElse(null);
		if(ground == null) {
			return "populateWorms: Ground Name [" + matchAllData.getSetup().getVenueName() + "] from database is returning NULL";
		}
		inning = matchAllData.getMatch().getInning().stream().filter(inn -> inn.getInningNumber() == WhichInning).findAny().orElse(null);
		if(inning == null) {
			return "populateWorms: current inning is NULL";
		}
		status = PopulateFfHeader(WhichSide, whatToProcess, matchAllData, WhichInning);
		if(status == Constants.OK) {
			setBasePosition(print_writers, WhichSide, whatToProcess, config);
			status = PopulateFfBody(WhichSide, whatToProcess, matchAllData, WhichInning);
			if(status == Constants.OK) {
				return PopulateFfFooter(WhichSide, whatToProcess, matchAllData, WhichInning);
			} else {
				return status;
			}
		} else {
			return status;
		}
	}
	public String populateTarget(int WhichSide, String whatToProcess, MatchAllData matchAllData, int WhichInning) throws Exception
	{
		inning = matchAllData.getMatch().getInning().stream().filter(inn -> inn.getInningNumber() == WhichInning)
			.findAny().orElse(null);
		if(inning == null ) {
			return "populateTarget: inning is null";
		}
		status = PopulateFfHeader(WhichSide, whatToProcess, matchAllData, 0);
		if(status == Constants.OK) {
			return PopulateFfBody(WhichSide, whatToProcess.split(",")[0], matchAllData, WhichInning);
		} else {
			return status;
		}
	}
	public String populatePreviousMatchSummary(int WhichSide, String whatToProcess, MatchAllData matchAllData, int WhichInning) throws Exception
	{
		previous_match = new MatchAllData();
		fixture = fixTures.stream().filter(fix -> fix.getMatchnumber() == Integer.valueOf(whatToProcess.split(",")[2])).findAny().orElse(null);
		
		if(fixture == null) {
			return "Fixture id [" + Integer.valueOf(whatToProcess.split(",")[2]) + "] from database is returning NULL";
		}
		
		if(new File(CricketUtil.CRICKET_DIRECTORY + CricketUtil.SETUP_DIRECTORY + fixture.getMatchfilename() + ".json").exists()) {
			previous_match.setSetup(new ObjectMapper().readValue(new File(CricketUtil.CRICKET_DIRECTORY + CricketUtil.SETUP_DIRECTORY + 
					fixture.getMatchfilename() + ".json"), Setup.class));
			previous_match.setMatch(new ObjectMapper().readValue(new File(CricketUtil.CRICKET_DIRECTORY + CricketUtil.MATCHES_DIRECTORY + 
					fixture.getMatchfilename() + ".json"), Match.class));
		}
		if(new File(CricketUtil.CRICKET_DIRECTORY + CricketUtil.EVENT_DIRECTORY + fixture.getMatchfilename() + ".json").exists()) {
			previous_match.setEventFile(new ObjectMapper().readValue(new File(CricketUtil.CRICKET_DIRECTORY + CricketUtil.EVENT_DIRECTORY + 
					fixture.getMatchfilename() + ".json"), EventFile.class));
		}
//		previous_match = CricketFunctions.populateMatchVariables(cricketService, CricketFunctions.readOrSaveMatchFile(CricketUtil.READ, 
//				CricketUtil.SETUP + "," + CricketUtil.MATCH, previous_match,config));	
		
		inning = previous_match.getMatch().getInning().stream().filter(inn -> inn.getInningNumber() == 2).findAny().orElse(null);
		if(inning == null) {
			return "populatePreviousMatchSummary: current inning is NULL";
		}
		
		status = PopulateFfHeader(WhichSide, whatToProcess.split(",")[0], previous_match, 2);
		if(status == Constants.OK) {
			setBasePosition(print_writers, WhichSide, whatToProcess, config);
			status = PopulateFfBody(WhichSide, whatToProcess.split(",")[0], previous_match, 2);
			if(status == Constants.OK) {
				return PopulateFfFooter(WhichSide, whatToProcess.split(",")[0], previous_match, 2);
			} else {
				return status;
			}
		} else {
			return status;
		}
	}
	
	public String populateFFMatchId(int WhichSide, String whatToProcess, MatchAllData matchAllData) throws Exception
	{
		status = PopulateFfHeader(WhichSide, whatToProcess, matchAllData, 0);
		if(status == Constants.OK) {
			status = PopulateFfBody(WhichSide, whatToProcess, matchAllData, 0);
			if(status == Constants.OK) {
				this.numberOfRows = 11;
				return PopulateFfFooter(WhichSide, whatToProcess, matchAllData, 0);
			} else {
				return status;
			}
		} else {
			return status;
		}
	}
	public String populatePlayerProfile(int WhichSide, String whatToProcess, MatchAllData matchAllData, int WhichInning) throws Exception 
	{
		this_ALL_FF.FirstPlayerId = Integer.valueOf(whatToProcess.split(",")[2]);
		this_ALL_FF.WhichProfile = whatToProcess.split(",")[3];
		System.out.println("this_ALL_FF.WhichProfile = " + this_ALL_FF.WhichProfile);
		System.out.println("this_ALL_FF.FirstPlayerId = " + this_ALL_FF.FirstPlayerId);
		switch(config.getBroadcaster()) {
		case Constants.TRI_SERIES: case Constants.MT20: case Constants.TG20:
			this_ALL_FF.ImageType = whatToProcess.split(",")[4];
			break;
		}
		
		if(this_ALL_FF.FirstPlayerId <= 0 || this_ALL_FF.WhichProfile == null) {
			return "populatePlayerProfile: Player Id NOT found [" + FirstPlayerId + "]";
		}
		
		this_ALL_FF.player = CricketFunctions.getPlayerFromMatchData(this_ALL_FF.FirstPlayerId, matchAllData);
		if(this_ALL_FF.player == null) {
			return "populatePlayerProfile: Player id [" + whatToProcess.split(",")[2] + "] from database is returning NULL";
		}
		
		this_ALL_FF.team = Teams.stream().filter(tm -> tm.getTeamId() == this_ALL_FF.player.getTeamId()).findAny().orElse(null);
		if(this_ALL_FF.team == null) {
			return "populatePlayerProfile: Team id [" + this_ALL_FF.player.getTeamId() + "] from database is returning NULL";
		}
		
		if(this_ALL_FF.WhichProfile.equalsIgnoreCase("DT20") || this_ALL_FF.WhichProfile.equalsIgnoreCase("IT20")) {
			this_ALL_FF.statsType = statsTypes.stream().filter(st -> st.getStatsShortName().equalsIgnoreCase(this_ALL_FF.WhichProfile)).findAny().orElse(null);
			if(this_ALL_FF.statsType == null) {
				return "populatePlayerProfile: Stats Type not found for profile [" + this_ALL_FF.WhichProfile + "]";
			}
			this_ALL_FF.stat = statistics.stream().filter(st -> st.getPlayerID() == this_ALL_FF.FirstPlayerId && 
					this_ALL_FF.statsType.getStatsId() == st.getStatsTypeId()).findAny().orElse(null);
			if(this_ALL_FF.stat == null) {
				return "populatePlayerProfile: Stats not found for Player Id [" + this_ALL_FF.FirstPlayerId + "]";
			}
			
			//this_ALL_FF.statsType = statsTypes.stream().filter(st -> st.getStatsShortName().equalsIgnoreCase("DT20")).findAny().orElse(null);
			this_ALL_FF.stat.setStats_type(this_ALL_FF.statsType);
			
			this_ALL_FF.stat = CricketFunctions.updateTournamentWithH2h(this_ALL_FF.stat, headToHead, matchAllData, CricketUtil.FULL);
			
			if(this_ALL_FF.stat.getPlayerID() == this_ALL_FF.FirstPlayerId) {
				System.out.println(this_ALL_FF.stat.getMatches());
			}
			
//			if(matchAllData.getSetup().getMatchType().equalsIgnoreCase(CricketUtil.IT20)) {
//				switch (this_ALL_FF.WhichProfile.toUpperCase()) {
//				case "DT20":
//					matchAllData.getSetup().setMatchType(CricketUtil.DT20);
//					break;
//				}
//				this_ALL_FF.stat = CricketFunctions.updateStatisticsWithMatchData(this_ALL_FF.stat, matchAllData, CricketUtil.FULL);
//				
//				if(this_ALL_FF.WhichProfile.equalsIgnoreCase(CricketUtil.DT20)) {
//					matchAllData.getSetup().setMatchType(CricketUtil.IT20);
//				}
//			}else if(matchAllData.getSetup().getMatchType().equalsIgnoreCase(CricketUtil.DT20)) {
//				this_ALL_FF.stat = CricketFunctions.updateStatisticsWithMatchData(this_ALL_FF.stat, matchAllData, CricketUtil.FULL);
//				
//			}
		}else if(this_ALL_FF.WhichProfile.equalsIgnoreCase("MAHARAJA_CAREER")) {
			this_ALL_FF.statsType = statsTypes.stream()
		            .filter(st -> st.getStatsShortName().equalsIgnoreCase("MAHARAJA_CAREER"))
		            .findAny().orElse(null);
		        if (this_ALL_FF.statsType == null) {
		            return "InfoBarPlayerProfile: Stats Type not found for profile [" + this_ALL_FF.WhichProfile + "]";
		        }
		        
		        this_ALL_FF.stat = statistics.stream()
		            .filter(st -> st.getPlayerID() == this_ALL_FF.FirstPlayerId && this_ALL_FF.statsType.getStatsId() == st.getStatsTypeId())
		            .findAny().orElse(null);
		        if (this_ALL_FF.stat == null) {
		            return "InfoBarPlayerProfile: Stats not found for Player Id [" + this_ALL_FF.FirstPlayerId + "]";
		        }
		        
		        this_ALL_FF.statsType = statsTypes.stream().filter(st -> st.getStatsShortName().equalsIgnoreCase("DT20")).findAny().orElse(null);
		        this_ALL_FF.stat.setStats_type(this_ALL_FF.statsType);
		        this_ALL_FF.stat = CricketFunctions.updateTournamentWithH2h(this_ALL_FF.stat, headToHead, matchAllData, CricketUtil.FULL);
		        this_ALL_FF.stat = CricketFunctions.updateStatisticsWithMatchData(this_ALL_FF.stat, matchAllData, CricketUtil.FULL);
		        
		}else if(this_ALL_FF.WhichProfile.equalsIgnoreCase("ODI") || this_ALL_FF.WhichProfile.equalsIgnoreCase("TEST") ||
				this_ALL_FF.WhichProfile.equalsIgnoreCase("LIST A")) {
			this_ALL_FF.statsType = statsTypes.stream().filter(st -> st.getStatsShortName().equalsIgnoreCase(this_ALL_FF.WhichProfile)).findAny().orElse(null);
			if(this_ALL_FF.statsType == null) {
				return "populatePlayerProfile: Stats Type not found for profile [" + this_ALL_FF.WhichProfile + "]";
			}
			this_ALL_FF.stat = statistics.stream().filter(st -> st.getPlayerID() == this_ALL_FF.FirstPlayerId && 
					this_ALL_FF.statsType.getStatsId() == st.getStatsTypeId()).findAny().orElse(null);
			if(this_ALL_FF.stat == null) {
				return "populatePlayerProfile: Stats not found for Player Id [" + this_ALL_FF.FirstPlayerId + "]";
			}
			
			this_ALL_FF.statsType = statsTypes.stream().filter(st -> st.getStatsShortName().equalsIgnoreCase("ODI")).findAny().orElse(null);
			this_ALL_FF.stat.setStats_type(this_ALL_FF.statsType);
			
			this_ALL_FF.stat = CricketFunctions.updateTournamentWithH2h(this_ALL_FF.stat, headToHead, matchAllData, CricketUtil.FULL);
			if(matchAllData.getSetup().getMatchType().equalsIgnoreCase(CricketUtil.ODI) || matchAllData.getSetup().getMatchType().equalsIgnoreCase(CricketUtil.OD) ||
					matchAllData.getSetup().getMatchType().equalsIgnoreCase(CricketUtil.TEST)) {
				this_ALL_FF.stat = CricketFunctions.updateStatisticsWithMatchData(this_ALL_FF.stat, matchAllData, CricketUtil.FULL);
			}
		}else {
			this_ALL_FF.statsType = statsTypes.stream().filter(st -> st.getStatsShortName().equalsIgnoreCase(this_ALL_FF.WhichProfile)).findAny().orElse(null);
			if(this_ALL_FF.statsType == null) {
				return "InfoBarPlayerProfile: Stats Type not found for profile [" + this_ALL_FF.WhichProfile + "]";
			}
			this_ALL_FF.stat = statistics.stream().filter(st -> st.getPlayerID() == this_ALL_FF.FirstPlayerId && 
					this_ALL_FF.statsType.getStatsId() == st.getStatsTypeId()).findAny().orElse(null);
			if(this_ALL_FF.stat == null) {
				return "InfoBarPlayerProfile: Stats not found for Player Id [" + this_ALL_FF.FirstPlayerId + "]";
			}
		}

		status = PopulateFfHeader(WhichSide, whatToProcess.split(",")[0], matchAllData, WhichInning);
		if(status == Constants.OK) {
			status = PopulateFfBody(WhichSide, whatToProcess.split(",")[0], matchAllData, WhichInning);
			if(status == Constants.OK) {
				this.numberOfRows = 11;
				return PopulateFfFooter(WhichSide, whatToProcess.split(",")[0], matchAllData, WhichInning);
			} else {
				return status;
			}
		} else {
			return status;
		}
	}
	public String populateFFMatchPromo(int WhichSide, String whatToProcess, MatchAllData matchAllData) throws Exception
	{
		this_ALL_FF.fixture = fixTures.stream().filter(fix -> fix.getMatchnumber() == Integer.valueOf(whatToProcess.split(",")[2])).findAny().orElse(null);
		if(this_ALL_FF.fixture == null) {
			return "Fixture id [" + Integer.valueOf(whatToProcess.split(",")[2]) + "] from database is returning NULL";
		}
		
		status = PopulateFfHeader(WhichSide, whatToProcess.split(",")[0], matchAllData, 0);
		if(status == Constants.OK) {
			status = PopulateFfBody(WhichSide, whatToProcess.split(",")[0], matchAllData, 0);
			if(status == Constants.OK) {
				this.numberOfRows = 11;
				return PopulateFfFooter(WhichSide, whatToProcess.split(",")[0], matchAllData, 0);
			} else {
				return status;
			}
		} else {
			return status;
		}
	}

	public String PopulateDoubleTeams(int WhichSide, String whatToProcess,MatchAllData matchAllData) throws Exception
	{
		String MatchFileNameHome = null, MatchFileNameAway = null;
		this_ALL_FF.PlayerId = new ArrayList<Integer>();
		this_ALL_FF.PlayerIdIn = new ArrayList<Integer>();
		
		if (headToHead.size() > 1) {
		    int homeId = matchAllData.getSetup().getHomeTeamId();
		    int awayId = matchAllData.getSetup().getAwayTeamId();

		    for (int i = headToHead.size() - 1; i >= 0; i--) {
		    	HeadToHeadPlayer m = headToHead.get(i);
		        int teamId = m.getTeam().getTeamId();
		        String file = m.getMatchFileName();

		        if (teamId == homeId) {
		            if (MatchFileNameHome == null) MatchFileNameHome = file;
		            if (!file.equalsIgnoreCase(MatchFileNameHome)) break;
		        } else if (teamId == awayId) {
		            if (MatchFileNameAway == null) MatchFileNameAway = file;
		            if (!file.equalsIgnoreCase(MatchFileNameAway)) break;
		        }
		    }
		    
		    if (MatchFileNameHome == null) {
		    	MatchFileNameHome = matchAllData.getMatch().getMatchFileName();
	        }
		    if (MatchFileNameAway == null) {
		    	MatchFileNameAway = matchAllData.getMatch().getMatchFileName();
	        }
		}else {
			MatchFileNameHome = matchAllData.getMatch().getMatchFileName(); 
			MatchFileNameAway = matchAllData.getMatch().getMatchFileName(); 
		}

		processDoubleTeamsInAndOutPlayer(MatchFileNameHome, matchAllData.getSetup().getHomeSquad(), this_ALL_FF);
		processDoubleTeamsInAndOutPlayer(MatchFileNameAway, matchAllData.getSetup().getAwaySquad(), this_ALL_FF);
		
		status = PopulateFfHeader(WhichSide, whatToProcess, matchAllData, 0);
		if(status == Constants.OK) {
			setBasePosition(print_writers, WhichSide, whatToProcess, config);
			status = PopulateFfBody(WhichSide, whatToProcess, matchAllData, 0);
			if(status == Constants.OK) {
				return PopulateFfFooter(WhichSide, whatToProcess, matchAllData, 0);
			} else {
				return status;
			}
		} else {
			return status;
		}
	}
	public String populatePlayingXI(int WhichSide, String whatToProcess,int teamId,MatchAllData matchAllData, int WhichInning) throws Exception
	{
		if(teamId == matchAllData.getSetup().getHomeTeamId()) {
			this_ALL_FF.PlayingXI = matchAllData.getSetup().getHomeSquad();
			this_ALL_FF.otherSquad = matchAllData.getSetup().getHomeSubstitutes();
		}else {
			this_ALL_FF.PlayingXI = matchAllData.getSetup().getAwaySquad();
			this_ALL_FF.otherSquad = matchAllData.getSetup().getAwaySubstitutes();
		}
		
		this_ALL_FF.Players = cricketService.getAllPlayer();
		
		ground = Grounds.stream().filter(grnd -> grnd.getFullname().contains(matchAllData.getSetup().getVenueName())).findAny().orElse(null);
		if(ground == null) {
			return "populatePlayingXI: Ground Name [" + matchAllData.getSetup().getVenueName() + "] from database is returning NULL";
		}
		
		if(matchAllData.getMatch().getInning().get(0).getBattingTeamId() == teamId) {
			WhichType = "BAT";
		}else if(matchAllData.getMatch().getInning().get(0).getBowlingTeamId() == teamId) {
			WhichType = "BALL";
		}
		
		this_ALL_FF.team = Teams.stream().filter(tm -> tm.getTeamId() == teamId).findAny().orElse(null);
		if(this_ALL_FF.team == null) {
			return "populatePlayingXI: Team id [" + teamId + "] from database is returning NULL";
		}
		
		//--------------------
		
		String MatchFileName = null;
		this_ALL_FF.PlayerId = new ArrayList<Integer>();
		this_ALL_FF.PlayerIdIn = new ArrayList<Integer>();
		System.out.println(headToHead.size());
		if(headToHead.size() > 1) {
			for (int i = headToHead.size() - 1; i >= 0; i--) {
			    if (headToHead.get(i).getTeam().getTeamId() == this_ALL_FF.team.getTeamId()) {
			    	if (MatchFileName == null) {
			    		MatchFileName = headToHead.get(i).getMatchFileName(); 
			        }
			        if (!headToHead.get(i).getMatchFileName().equalsIgnoreCase(MatchFileName)) {
			            break;
			        }
			    }
			}
			if (MatchFileName == null) {
				MatchFileName = matchAllData.getMatch().getMatchFileName();
	        }
		}else {
			MatchFileName = matchAllData.getMatch().getMatchFileName();
		}
		
		if(MatchFileName != null) {
			Setup setup = new ObjectMapper().readValue(new File(CricketUtil.CRICKET_DIRECTORY + CricketUtil.SETUP_DIRECTORY + 
					MatchFileName), Setup.class);
			
			for(Player headToHead : (setup.getHomeTeamId()==teamId ? setup.getHomeSquad():setup.getAwaySquad())) {
				boolean playerFound = false;
				for (Player ply : this_ALL_FF.PlayingXI) {
		    	    if(ply.getPlayerId() == headToHead.getPlayerId()) {
		    	    	playerFound = true;
		    	    	break;
		    	    }
		    	}
		        if (!playerFound) {  
//		        	PlayerId.add(headToHead.getPlayerId());
		        	this_ALL_FF.PlayerId.add(headToHead.getPlayerId());
		        }else {
//		        	PlayerIdIn.add(headToHead.getPlayerId());
		        	this_ALL_FF.PlayerIdIn.add(headToHead.getPlayerId());
		        }
			}
		}
		System.out.println("IN - " + this_ALL_FF.PlayerIdIn.toString());
		System.out.println("OUT - " + this_ALL_FF.PlayerId.toString());
		//-----------------------
		
		this_ALL_FF.statsType = statsTypes.stream().filter(st -> st.getStatsShortName().equalsIgnoreCase(matchAllData.getSetup().getMatchType())).findAny().orElse(null);
		if(this_ALL_FF.statsType == null) {
			return "populateSingleTeams: Stats Type not found for profile [" + matchAllData.getSetup().getMatchType() + "]";
		}
		
		this_ALL_FF.statisticsList = new ArrayList<Statistics>();
		for(Statistics stat : statistics) {
			if(stat.getStatsTypeId() == this_ALL_FF.statsType.getStatsId()) {
				stat.setStats_type(this_ALL_FF.statsType);
				stat = CricketFunctions.updateTournamentWithH2h(stat, headToHead, matchAllData, CricketUtil.FULL);
				stat = CricketFunctions.updateStatisticsWithMatchData(stat, matchAllData, CricketUtil.FULL);
				this_ALL_FF.statisticsList.add(stat);
			}
		}
		
		if(this_ALL_FF.statisticsList == null && this_ALL_FF.statisticsList.isEmpty()) {
			return "populateSingleTeams: Stats List is null";
		}
		
		status = PopulateFfHeader(WhichSide, whatToProcess, matchAllData, WhichInning);
		if(status == Constants.OK) {
			status = PopulateFfBody(WhichSide, whatToProcess, matchAllData, WhichInning);
			if(status == Constants.OK) {
				return PopulateFfFooter(WhichSide, whatToProcess, matchAllData, WhichInning);
			} else {
				return status;
			}
		} else {
			return status;
		}
	}
	public String populateSecondPlayingXI(int WhichSide, String whatToProcess,int teamId,MatchAllData matchAllData, int WhichInning) throws Exception
	{
		if(teamId == matchAllData.getSetup().getHomeTeamId()) {
			this_ALL_FF.PlayingXI = matchAllData.getSetup().getHomeSquad();
			this_ALL_FF.otherSquad = matchAllData.getSetup().getHomeSubstitutes();
		}else {
			this_ALL_FF.PlayingXI = matchAllData.getSetup().getAwaySquad();
			this_ALL_FF.otherSquad = matchAllData.getSetup().getAwaySubstitutes();
		}
		
		ground = Grounds.stream().filter(grnd -> grnd.getFullname().contains(matchAllData.getSetup().getVenueName())).findAny().orElse(null);
		if(ground == null) {
			return "populatePlayingXI: Ground Name [" + matchAllData.getSetup().getVenueName() + "] from database is returning NULL";
		}
		
		if(matchAllData.getMatch().getInning().get(0).getBattingTeamId() == teamId) {
			WhichType = "BAT";
		}else if(matchAllData.getMatch().getInning().get(0).getBowlingTeamId() == teamId) {
			WhichType = "BALL";
		}
		
		this_ALL_FF.team = Teams.stream().filter(tm -> tm.getTeamId() == teamId).findAny().orElse(null);
		if(this_ALL_FF.team == null) {
			return "populatePlayingXI: Team id [" + teamId + "] from database is returning NULL";
		}
		
		//--------------------
		
		String MatchFileName = null;
		this_ALL_FF.PlayerId = new ArrayList<Integer>();
		this_ALL_FF.PlayerIdIn = new ArrayList<Integer>();
		System.out.println(headToHead.size());
		if(headToHead.size() > 1) {
			for (int i = headToHead.size() - 1; i >= 0; i--) {
			    if (headToHead.get(i).getTeam().getTeamId() == this_ALL_FF.team.getTeamId()) {

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
			
			for(Player headToHead : (setup.getHomeTeamId()==teamId ? setup.getHomeSquad():setup.getAwaySquad())) {
				boolean playerFound = false;
				for (Player ply : this_ALL_FF.PlayingXI) {
		    	    if(ply.getPlayerId() == headToHead.getPlayerId()) {
		    	    	playerFound = true;
		    	    	break;
		    	    }
		    	}
		        if (!playerFound) {  
//		        	PlayerId.add(headToHead.getPlayerId());
		        	this_ALL_FF.PlayerId.add(headToHead.getPlayerId());
		        }else {
//		        	PlayerIdIn.add(headToHead.getPlayerId());
		        	this_ALL_FF.PlayerIdIn.add(headToHead.getPlayerId());
		        }
			}
		}
		System.out.println("IN - " + this_ALL_FF.PlayerIdIn.toString());
		System.out.println("OUT - " + this_ALL_FF.PlayerId.toString());
		//-----------------------
		status = PopulateFfHeader(WhichSide, whatToProcess, matchAllData, WhichInning);
		if(status == Constants.OK) {
			status = PopulateFfBody(WhichSide, whatToProcess, matchAllData, WhichInning);
			if(status == Constants.OK) {
				return PopulateFfFooter(WhichSide, whatToProcess, matchAllData, WhichInning);
			} else {
				return status;
			}
		} else {
			return status;
		}
	}
	public String populateFFPointsTable(int WhichSide, String whatToProcess, MatchAllData matchAllData, int WhichInning) throws Exception
	{
		this_ALL_FF.Teams = Teams;
		
		this_ALL_FF.WhichProfile = (WhichGroup.equalsIgnoreCase("GroupA") ? "GROUP A" : "GROUP B");
		
		if(new File(CricketUtil.CRICKET_DIRECTORY + CricketUtil.LEAGUE_TABLE_DIRECTORY + WhichGroup + CricketUtil.XML_EXTENSION).exists()) {
			this_ALL_FF.leagueTable = (LeagueTable)JAXBContext.newInstance(LeagueTable.class).createUnmarshaller().unmarshal(
					new File(CricketUtil.CRICKET_DIRECTORY + CricketUtil.LEAGUE_TABLE_DIRECTORY + WhichGroup + CricketUtil.XML_EXTENSION));
		}
		if(this_ALL_FF.leagueTable == null) {
			return "populateFFPointsTable : League Table is null";
		}
		
		for(VariousText vt : VariousText) {
			if(vt.getVariousType().equalsIgnoreCase("POINTSTABLEFOOTER") && vt.getUseThis().toUpperCase().equalsIgnoreCase(CricketUtil.YES)) {
				this_ALL_FF.varText = vt.getVariousText();
				break;
			}else {
				this_ALL_FF.varText = "TOP TWO TEAMS QUALIFY FOR SEMI-FINALS";
			}
		}
		
		status = PopulateFfHeader(WhichSide, whatToProcess, matchAllData, WhichInning);
		if(status == Constants.OK) {
			setBasePosition(print_writers, WhichSide, whatToProcess, config);
			status = PopulateFfBody(WhichSide, whatToProcess, matchAllData, WhichInning);
			return PopulateFfFooter(WhichSide, whatToProcess, matchAllData, WhichInning);
		} else {
			return status;
		}
	}
	public String populateLeaderBoard(int WhichSide, String whatToProcess, MatchAllData matchAllData, int WhichInning) throws Exception
	{
		this_ALL_FF.tournaments = new ArrayList<Tournament>();
		this_ALL_FF.top_batsman_beststat.clear();
		this_ALL_FF.Teams = Teams;
				
		switch (config.getBroadcaster()) {
		case Constants.TRI_SERIES:  case Constants.MT20: case Constants.BAN_AFG_SERIES: case Constants.ACC:
			if(whatToProcess.split(",")[3].equalsIgnoreCase("WITHOUT_CURRENT")) {
				this_ALL_FF.tournaments = past_tournament_stats;
			}else if(whatToProcess.split(",")[3].equalsIgnoreCase("WITH_CURRENT")) {
				this_ALL_FF.tournaments = CricketFunctions.extractTournamentData("CURRENT_MATCH_DATA", false, this_ALL_FF.headToHead, cricketService, 
						matchAllData, past_tournament_stats);
			}
			break;

		default:
			this_ALL_FF.tournaments = CricketFunctions.extractTournamentData("CURRENT_MATCH_DATA", false, null, null, matchAllData, tournaments);
			break;
		}
		if(this_ALL_FF.tournaments == null) {
			return "populateLeaderBoard : Tournament Stats is Null";
		}
		
		switch (whatToProcess.split(",")[0]) {
		case "z": 
			Collections.sort(this_ALL_FF.tournaments,new CricketFunctions.BatsmenMostRunComparator());
			break;
		case "x": 
			Collections.sort(this_ALL_FF.tournaments,new CricketFunctions.BowlerWicketsComparator());
			break;
		case "c": 
			Collections.sort(this_ALL_FF.tournaments,new CricketFunctions.BatsmanFoursComparator());
			break;
		case "v":
			Collections.sort(this_ALL_FF.tournaments,new CricketFunctions.BatsmanSixesComparator());
			break;
		case "Control_Shift_Z":
			Collections.sort(this_ALL_FF.tournaments,new CricketFunctions.BestBatsmanStrikeRateComparator());
			break;
		case "Control_Shift_Y":
			Collections.sort(this_ALL_FF.tournaments,new CricketFunctions.BestBowlerEconomyComparator());
			break;
		case "Control_z":
			this_ALL_FF.top_batsman_beststat = new ArrayList<BestStats>();
			for(Tournament tourn : this_ALL_FF.tournaments) {
				for(BestStats bs : tourn.getBatsman_best_Stats()) {
					this_ALL_FF.top_batsman_beststat.add(CricketFunctions.getProcessedBatsmanBestStats(bs));
				}
			}
			System.out.println("Size1 = " + this_ALL_FF.top_batsman_beststat.size());
			Collections.sort(this_ALL_FF.top_batsman_beststat,new CricketFunctions.BatsmanBestStatsComparator());
			break;
		 case "Control_x":
			 this_ALL_FF.top_bowler_beststats = new ArrayList<BestStats>();
			for(Tournament tourn : this_ALL_FF.tournaments) {
				for(BestStats bs : tourn.getBowler_best_Stats()) {
					this_ALL_FF.top_bowler_beststats.add(CricketFunctions.getProcessedBowlerBestStats(bs));
				}
			}
			Collections.sort(this_ALL_FF.top_bowler_beststats,new CricketFunctions.BowlerBestStatsComparator());
			break;	
		}
		
		status = PopulateFfHeader(WhichSide, whatToProcess.split(",")[0], matchAllData, WhichInning);
		if(status == Constants.OK) {
			setBasePosition(print_writers, WhichSide, whatToProcess, config);
			status = PopulateFfBody(WhichSide, whatToProcess.split(",")[0], matchAllData, WhichInning);
			if(status == Constants.OK) {
				return PopulateFfFooter(WhichSide, whatToProcess.split(",")[0], matchAllData, WhichInning);
			} else {
				return status;
			}
		} else {
			return status;
		}
	}
	public String populateThisSeries(int WhichSide, String whatToProcess, MatchAllData matchAllData, int WhichInning) throws Exception 
	{
		this_ALL_FF.FirstPlayerId = Integer.valueOf(whatToProcess.split(",")[2]);
		this_ALL_FF.ImageType = whatToProcess.split(",")[4];
		this_ALL_FF.WhichProfile = "THIS SERIES";
		
		this_ALL_FF.player = CricketFunctions.getPlayerFromMatchData(this_ALL_FF.FirstPlayerId, matchAllData);
		if(this_ALL_FF.player == null) {
			return "populatePlayerProfile: Player id [" + whatToProcess.split(",")[2] + "] from database is returning NULL";
		}
		
		this_ALL_FF.team = Teams.stream().filter(tm -> tm.getTeamId() == this_ALL_FF.player.getTeamId()).findAny().orElse(null);
		if(this_ALL_FF.team == null) {
			return "populatePlayerProfile: Team id [" + this_ALL_FF.player.getTeamId() + "] from database is returning NULL";
		}
		
		if(whatToProcess.split(",")[3].equalsIgnoreCase("WITHOUT_CURRENT")) {
			this_ALL_FF.addPastDataToCurr = past_tournament_stats;

		}else if(whatToProcess.split(",")[3].equalsIgnoreCase("WITH_CURRENT")) {
			this_ALL_FF.addPastDataToCurr = CricketFunctions.extractTournamentData("CURRENT_MATCH_DATA", false, this_ALL_FF.headToHead,cricketService, 
					matchAllData, past_tournament_stats);

		}
		
		if(this_ALL_FF.addPastDataToCurr == null) {
			return "addPastDataToCurr is Null";
		}
		
		this_ALL_FF.top_batsman_beststats = new ArrayList<BestStats>();
		this_ALL_FF.top_bowler_beststats = new ArrayList<BestStats>();
		for(Tournament tourn : this_ALL_FF.addPastDataToCurr) {
			for(BestStats bs : tourn.getBatsman_best_Stats()) {
				this_ALL_FF.top_batsman_beststats.add(bs);
			}
			for(BestStats bfig : tourn.getBowler_best_Stats()) {
				this_ALL_FF.top_bowler_beststats.add(bfig);
			}
		}
		
		Collections.sort(this_ALL_FF.top_batsman_beststats, new CricketFunctions.PlayerBestStatsComparator());
		Collections.sort(this_ALL_FF.top_bowler_beststats, new CricketFunctions.PlayerBestStatsComparator());
		
		this_ALL_FF.tournament = this_ALL_FF.addPastDataToCurr.stream().filter(tourn -> tourn.getPlayerId() == this_ALL_FF.player.getPlayerId()).findAny().orElse(null);
		if(this_ALL_FF.tournament == null) {
			return "ThisSeries : Tournament Stats is Null";
		}
		
		this_ALL_FF.team = Teams.stream().filter(tm -> tm.getTeamId() == this_ALL_FF.player.getTeamId()).findAny().orElse(null);
		if(this_ALL_FF.team == null) {
			return "populatePlayerProfile: Team id [" + this_ALL_FF.player.getTeamId() + "] from database is returning NULL";
		}

		status = PopulateFfHeader(WhichSide, whatToProcess.split(",")[0], matchAllData, WhichInning);
		if(status == Constants.OK) {
			status = PopulateFfBody(WhichSide, whatToProcess.split(",")[0], matchAllData, WhichInning);
			if(status == Constants.OK) {
				return PopulateFfFooter(WhichSide, whatToProcess.split(",")[0], matchAllData, WhichInning);
			} else {
				return status;
			}
		} else {
			return status;
		}
	}
	public String PopulateFfBody(int WhichSide, String whatToProcess, MatchAllData matchAllData, int WhichInning) throws Exception {
		switch(whatToProcess) {
		case "F1":
			switch (config.getBroadcaster().toUpperCase()) {
			case Constants.BCCI:
				return this_FC_FF.ScorecardBody(print_writers, WhichSide, config, matchAllData, inning, multilanguagedata, 
						foreignLanguageDataList, cricketService.getDictionary());
			default:	
				return this_ALL_FF.ScorecardBody(print_writers, WhichSide, config, matchAllData, inning);	
			}
		case "Control_Shift_F1":
			switch (config.getBroadcaster().toUpperCase()) {
			case Constants.BCCI:
				return this_FC_FF.BatPerformerBody(print_writers, WhichSide, config, matchAllData, inning, cricketService, multilanguagedata, 
						foreignLanguageDataList, cricketService.getDictionary());
			case Constants.AFG_SL_SERIES:
				return this_ALL_FF.ScorecardBatPerformerBody(print_writers, WhichSide, config, matchAllData, inning);
			default:	
				return this_ALL_FF.BatPerformerBody(print_writers, WhichSide, config, matchAllData, inning, cricketService);	
			}
			
		case "Control_Shift_F4":
			return this_ALL_FF.ScorecardBatPerformerBody(print_writers, WhichSide, config, matchAllData, inning);
		
		case "Control_Shift_F5":
			return this_ALL_FF.BowlingCardBallPerformerBody(print_writers, WhichSide, config, matchAllData, inning);
		
		
		case "Control_Shift_F2":
			switch (config.getBroadcaster().toUpperCase()) {
			case Constants.BCCI:
				return this_FC_FF.BatPerformerBody(print_writers, WhichSide, config, matchAllData, inning, cricketService, multilanguagedata, 
						foreignLanguageDataList, cricketService.getDictionary());
			default:	
				return this_ALL_FF.BallPerformerBody(print_writers, WhichSide, config, matchAllData, inning, cricketService);	
			}	
		case "F2":
			switch (config.getBroadcaster().toUpperCase()) {
			case Constants.BCCI:
				return this_FC_FF.BowlingCardBody(print_writers, WhichSide, config, matchAllData, inning, multilanguagedata, 
						foreignLanguageDataList, cricketService.getDictionary());
			default:
				return this_ALL_FF.BowlingCardBody(print_writers, WhichSide, config, matchAllData, inning);
			}
		case "F4":
			switch (config.getBroadcaster().toUpperCase()) {
			case Constants.BCCI:
				return this_FC_FF.PartnershipListBody(print_writers, WhichSide, config, matchAllData, inning, multilanguagedata, 
						foreignLanguageDataList, cricketService.getDictionary());
			default:	
				return this_ALL_FF.PartnershipListBody(print_writers, WhichSide, config, matchAllData, inning);	
			}
		case "Control_F11": case "Shift_F11":
			switch (config.getBroadcaster().toUpperCase()) {
			case Constants.BCCI:
				return this_FC_FF.SummaryBody(print_writers, WhichSide, config, matchAllData, inning, multilanguagedata, 
						foreignLanguageDataList, cricketService.getDictionary());
			default:
				return this_ALL_FF.SummaryBody(print_writers, WhichSide, config, matchAllData, inning, WhichInning);
			}
		case "Control_p":
			return this_ALL_FF.PointsTableBody(print_writers, whatToProcess, WhichSide, matchAllData, config);	
		
		case "m": case "Control_m":
			switch (config.getBroadcaster().toUpperCase()) {
			case Constants.BCCI:
				return this_FC_FF.MatchIdentBody(print_writers, WhichSide, config, matchAllData, inning, multilanguagedata, 
						foreignLanguageDataList, cricketService.getDictionary());
			default:
				return this_ALL_FF.MatchIdentAndPromoBody(print_writers, whatToProcess,WhichSide, matchAllData, config); 
			}
		case "Control_d": case "Control_e":
			return this_ALL_FF.PlayerProfileBody(print_writers, whatToProcess, WhichSide, matchAllData, config);
		case "Control_F7":
			return this_ALL_FF.DoubleTeamsBody(print_writers, whatToProcess, WhichSide, matchAllData, config);
		case "Shift_K":
			return this_ALL_FF.CurrentPartnerShipBody(print_writers, WhichSide, config, matchAllData, inning);
		case "Control_F10":
			return this_ALL_FF.ManhattanBody(print_writers, WhichSide, config, matchAllData, inning);
		case "Alt_F11":
			return this_ALL_FF.DoubleManhattanBody(print_writers, WhichSide, config, matchAllData, inning);
		case "Alt_F9":
			return this_ALL_FF.SingleTeamBody(print_writers, WhichSide, config, matchAllData, inning);
		case "Shift_F10":
			return this_ALL_FF.WormBody(print_writers, WhichSide, config, matchAllData, inning);
		case "Shift_D":
			return this_ALL_FF.TargetBody(print_writers, WhichSide, config, matchAllData, inning);
		case "Shift_T":
			return this_ALL_FF.PlayingXISimpleBody(print_writers, whatToProcess, WhichSide, config, matchAllData, inning);
		case "Control_Shift_F7":
			return this_ALL_FF.PlayingXIBody(print_writers,whatToProcess, WhichSide, config, matchAllData, inning);
		case "Control_F1":
			return this_ALL_FF.PhotoBattingCardBody(print_writers, WhichSide, whatToProcess, config, matchAllData, inning);
		case "Control_Alt_F1":
			return this_ALL_FF.ScoreAndBowlingCardBody(print_writers, WhichSide, config, matchAllData, inning);
		case "Alt_Shift_F1":
			return this_ALL_FF.ScoreCardContributionBody(print_writers, WhichSide, config, matchAllData, inning);
		case "z": case "x":	case "c": case "v": case "Control_z": case "Control_x": case "Control_Shift_Z": case "Control_Shift_Y":
			return this_ALL_FF.LeaderBoardBody(print_writers, WhichSide,whatToProcess, config, matchAllData, inning);
		case "Shift_P": case "Shift_Q":
			return this_ALL_FF.ThisSeriesBody(print_writers, WhichSide,whatToProcess, config, matchAllData, inning);	
		}
		return Constants.OK;
	}
	
	public String PopulateFfHeader(int WhichSide, String whatToProcess, MatchAllData matchAllData, int WhichInning) throws InterruptedException {
		switch(whatToProcess) {
		case "F1": case "F2": case "F4": case "Control_F11": case "m": case "Control_d": case "Control_e": case "Control_m": case "Control_F7":
		case "Shift_K": case "Control_F10": case "Shift_F10": case "Shift_D": case "Control_Shift_F7": case "Alt_F9": case "Alt_F11":
		case "Control_F1": case "Shift_F11": case "Control_p": case "Control_Alt_F1": case "Alt_Shift_F1": case "Shift_Control_F1": case "Shift_Control_F2":
		case "Shift_P": case "Shift_Q":	case "z": case "x": case "c": case "v": case "Control_z": case "Control_x": case "Control_Shift_Z": case "Control_Shift_Y":
		case "Alt_Shift_W": case "Control_Shift_F4": case "Control_Shift_F5": case "Shift_T": case "Control_Shift_F1":
			switch (config.getBroadcaster().toUpperCase()) {
			case Constants.BCCI:
				return this_FC_FF.populateHeader(print_writers, WhichSide, whatToProcess, matchAllData, inning, config, multilanguagedata, 
						foreignLanguageDataList, cricketService.getDictionary());
			default:
				return this_ALL_FF.populateHeader(print_writers, WhichSide, whatToProcess, matchAllData, inning, config);
			}
		}
		return Constants.OK;
	}
	public String PopulateFfFooter(int WhichSide, String whatToProcess, MatchAllData matchAllData, int WhichInning) {
		switch(whatToProcess) {
		case "F1": case "F2": case "F4": case "Control_F11": case "m": case "Control_d": case "Control_e": case "Control_m": case "Control_F7":
		case "Shift_K": case "Control_F10": case "Shift_F10": case "Control_Shift_F7": case "Alt_F9": case "Alt_F11": case "Control_F1":
		case "Shift_F11": case "Control_Alt_F1": case "Alt_Shift_F1": case "Control_p": case "Control_Shift_F4": case "Control_Shift_F5":
		case "Shift_T": case "Control_Shift_F1":
			switch (config.getBroadcaster().toUpperCase()) {
			case Constants.BCCI:
				return this_FC_FF.populateFooter(print_writers, WhichSide, whatToProcess, matchAllData, inning, config, multilanguagedata, 
						foreignLanguageDataList, cricketService.getDictionary());
			default:
				return this_ALL_FF.populateFooter(print_writers, WhichSide, whatToProcess, matchAllData, inning, config);
			}
		}
		return Constants.OK;
	}
	public void setFullFrameFooterPosition(int whichside,int footer_omo, String whatToProcess) 
	{
		String offset = "";
		switch (config.getBroadcaster()) {
		case Constants.TRI_SERIES:  case Constants.MT20:
			offset = "";
			switch(this_ALL_FF.numberOfRows) {
			case 11:
				offset = "1.0";
				break;
			case 12:
				offset = "0.915";
				break;
			case 13:
				offset = "0.845";
				break;
			}
			switch(whatToProcess) {
			case "F1":  case "Control_Alt_F1": case "Control_Alt_F2": case "Alt_Shift_F1":
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + whichside + "$BattingCard$" +
						"BatterGrp$object_ScaleY*TRANSFORMATION*SCALING*Y SET "+offset+"\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + whichside + "$BattingCard$" +
						"DismissalGrp$object_ScaleY*TRANSFORMATION*SCALING*Y SET "+offset+"\0", print_writers);
				break;	
			}
			break;
		case Constants.BAN_AFG_SERIES:
			offset = "";
			switch(this_ALL_FF.numberOfRows) {
			case 11:
				offset = "20.0";
				break;
			case 12:
				offset = "18.0";
				break;
			case 13:
				offset = "17.0";
				break;
			}
			switch(whatToProcess) {
			case "F1": case "Control_Shift_F1":
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + whichside + "$BattingCard$" +
						"BattingDataAll$Normal$BatGrp*FUNCTION*Grid*row_offset SET "+offset+"\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + whichside + "$BattingCard$" +
						"BattingDataAll$Split$Left$BatGrp*FUNCTION*Grid*row_offset SET "+offset+"\0", print_writers);
				break;
			case "F4":
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + whichside + "$PartnershipCard$" +
						"PartnershipDataAll$PartGrp*FUNCTION*Grid*row_offset SET "+offset+"\0", print_writers);
				break;	
			}
			break;	
		}
	}
	public void setBasePosition(List<PrintWriter> print_writers, int whichside, String whatToProcess, Configuration config) 
	{
		String Position_footer = "",Position_Base_X_IN ="",Position_Base_X_Out="";
		switch (config.getBroadcaster()) {
		case Constants.TRI_SERIES:  case Constants.MT20: case Constants.TG20:
			switch(whatToProcess.split(",")[0]) {
			case "Shift_K": case "Alt_F11":
				Position_footer = "1.103";
				break;
			default:
				Position_footer = "1.00";
				break;
			}
			
			switch(whatToProcess.split(",")[0]) {
			case "F1": case "F2": case "Control_F7": case "Control_Alt_F7": case "Alt_Shift_F1": case "Control_Alt_F1":
				Position_Base_X_IN = "1.00";
				Position_Base_X_Out = "1.00";
				break;
			case "Control_Shift_F4": case "Control_Shift_F5": case "Control_Shift_F1": case "Control_Shift_F2":
			case "Shift_K": 
				Position_Base_X_IN = "1.00";
				Position_Base_X_Out = "0.00";
				break;
			case "Alt_F9": case "Control_F11": case "Shift_F11": case "F4": case "Shift_F10": case "Control_F10":
			case "Alt_F11": case "Control_p": case "Control_F1": case "z": case "x": case "c": case "v": case "Control_z": 
			case "Control_x": case "Control_Shift_Z": case "Control_Shift_Y": case "Alt_Shift_W":
				Position_Base_X_IN = "2.017";
				Position_Base_X_Out = "0.0";
				break;
			}
			
			switch(whatToProcess.split(",")[0]) {
//			case "Alt_F11":
//				if(whichside == 1) {
//					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Main$Shift_For_TopTitle$Base$obj_ScaleY_For_Footer"
//							+ "*ANIMATION*KEY*$Y_IN*VALUE SET 1.0 " + Position_footer + " 1.0\0", print_writers);
//					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Main$Shift_For_TopTitle$Base$obj_ScaleY_For_Footer"
//							+ "*ANIMATION*KEY*$Y_OUT*VALUE SET 1.0 " + Position_footer + " 1.0\0", print_writers);
//					
//					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Main$Shift_For_TopTitle$Base$obj_ScaleY_For_Footer"
//							+ "*ANIMATION*KEY*$Y_1*VALUE SET 1.0 " + Position_footer + " 1.0 \0", print_writers);
//					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Main$Shift_For_TopTitle$Base$obj_ScaleY_For_Footer"
//							+ "*ANIMATION*KEY*$Y_2*VALUE SET 1.0 " + Position_footer + " 1.0 \0", print_writers);
//				}
//				else if(whichside == 2) {
//					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Main$Shift_For_TopTitle$Base$obj_ScaleY_For_Footer"
//							+ "*ANIMATION*KEY*$Y_2*VALUE SET 1.0 " + Position_footer + " 1.0\0", print_writers);
//				}
//				break;
			default:
				if(whichside == 1) {
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Main$Shift_For_TopTitle$Base$obj_ScaleY_For_Footer"
							+ "*ANIMATION*KEY*$Y_IN*VALUE SET 1.0 " + Position_footer + " 1.0\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Main$Shift_For_TopTitle$Base$obj_ScaleY_For_Footer"
							+ "*ANIMATION*KEY*$Y_OUT*VALUE SET 1.0 " + Position_footer + " 1.0\0", print_writers);
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Main$Shift_For_TopTitle$Base$obj_ScaleX_In"
							+ "*ANIMATION*KEY*$X_IN_1*VALUE SET " + Position_Base_X_IN + " 1.0 1.0 \0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Main$Shift_For_TopTitle$Base$obj_ScaleX_In"
							+ "*ANIMATION*KEY*$X_IN_2*VALUE SET " + Position_Base_X_IN + " 1.0 1.0 \0", print_writers);
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Main$Shift_For_TopTitle$Base$obj_ScaleX_Out"
							+ "*ANIMATION*KEY*$X_OUT_1*VALUE SET " + Position_Base_X_Out + " 1.0 1.0 \0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Main$Shift_For_TopTitle$Base$obj_ScaleX_Out"
							+ "*ANIMATION*KEY*$X_OUT_2*VALUE SET " + Position_Base_X_Out + " 1.0 1.0 \0", print_writers);
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Main$Shift_For_TopTitle$Base$obj_ScaleY_For_Footer"
							+ "*ANIMATION*KEY*$Y_1*VALUE SET 1.0 " + Position_footer + " 1.0 \0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Main$Shift_For_TopTitle$Base$obj_ScaleY_For_Footer"
							+ "*ANIMATION*KEY*$Y_2*VALUE SET 1.0 " + Position_footer + " 1.0 \0", print_writers);
	//-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------				
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Main$Shift_For_TopTitle$Base$obj_ScaleX_In"
							+ "*ANIMATION*KEY*$X_D_IN_1*VALUE SET " + Position_Base_X_IN + " 1.0 1.0 \0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Main$Shift_For_TopTitle$Base$obj_ScaleX_In"
							+ "*ANIMATION*KEY*$X_D_IN_2*VALUE SET " + Position_Base_X_IN + " 1.0 1.0 \0", print_writers);
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Main$Shift_For_TopTitle$Base$obj_ScaleX_Out"
							+ "*ANIMATION*KEY*$X_D_OUT_1*VALUE SET " + Position_Base_X_Out + " 1.0 1.0 \0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Main$Shift_For_TopTitle$Base$obj_ScaleX_Out"
							+ "*ANIMATION*KEY*$X_D_OUT__2*VALUE SET " + Position_Base_X_Out + " 1.0 1.0 \0", print_writers);
				}
				else if(whichside == 2) {
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Main$Shift_For_TopTitle$Base$obj_ScaleY_For_Footer"
							+ "*ANIMATION*KEY*$Y_2*VALUE SET 1.0 " + Position_footer + " 1.0\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Main$Shift_For_TopTitle$Base$obj_ScaleX_In"
							+ "*ANIMATION*KEY*$X_D_IN_2*VALUE SET " + Position_Base_X_IN + " 1.0 1.0 \0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Main$Shift_For_TopTitle$Base$obj_ScaleX_Out"
							+ "*ANIMATION*KEY*$X_D_OUT__2*VALUE SET " + Position_Base_X_Out + " 1.0 1.0 \0", print_writers);
				}
				break;
			}
			break;
		}
	}
	private void processDoubleTeamsInAndOutPlayer(String matchFileName, List<Player> currentSquad, ALL_FF this_ALL_FF) throws StreamReadException, DatabindException, IOException {
	    if (matchFileName == null) return;

	    Setup setup = new ObjectMapper().readValue(
	        new File(CricketUtil.CRICKET_DIRECTORY + CricketUtil.SETUP_DIRECTORY + matchFileName),
	        Setup.class
	    );

	    List<Player> allSquadsH2h = new ArrayList<>();
	    allSquadsH2h.addAll(setup.getHomeSquad());
	    allSquadsH2h.addAll(setup.getAwaySquad());

	    for (Player headToHead : allSquadsH2h) {
	        boolean playerFound = false;
	        for (Player ply : currentSquad) {
	            if (ply.getPlayerId() == headToHead.getPlayerId()) {
	                playerFound = true;
	                break;
	            }
	        }
	        if (playerFound) {
	            this_ALL_FF.PlayerIdIn.add(headToHead.getPlayerId());
	        } else {
	            this_ALL_FF.PlayerId.add(headToHead.getPlayerId());
	        }
	    }
	}
}
	