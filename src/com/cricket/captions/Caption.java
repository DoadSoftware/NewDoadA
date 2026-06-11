package com.cricket.captions;

import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;


import com.cricket.containers.LowerThird;
import com.cricket.model.BattingCard;
import com.cricket.model.BestStats;
import com.cricket.model.Bugs;
import com.cricket.model.Commentator;
import com.cricket.model.Configuration;
import com.cricket.model.DuckWorthLewis;
import com.cricket.model.EverestBugs;
import com.cricket.model.Fixture;
import com.cricket.model.Ground;
import com.cricket.model.HeadToHeadPlayer;
import com.cricket.model.InfobarStats;
import com.cricket.model.Inning;
import com.cricket.model.MatchAllData;
import com.cricket.model.NameSuper;
import com.cricket.model.POTT;
import com.cricket.model.PerformanceBug;
import com.cricket.model.Player;
import com.cricket.model.Playoff;
import com.cricket.model.Statistics;
import com.cricket.model.StatsType;
import com.cricket.model.Team;
import com.cricket.model.Tournament;
import com.cricket.model.VariousText;
import com.cricket.service.CricketService;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.cricket.model.Staff;

public class Caption 
{
	public InfobarGfx this_infobarGfx;
	public BugsAndMiniGfx this_bugsAndMiniGfx;
	public LowerThirdGfx this_lowerThirdGfx;
	public FullFramesGfx this_fullFramesGfx;
	public Animation this_anim = new Animation();
	public static Scene this_scene;
	
	@JsonIgnore
	public List<PrintWriter> print_writers; 
	public Configuration config;
	public List<Statistics> statistics;
	public List<StatsType> statsTypes;
	public List<MatchAllData> tournament_matches;
	public List<Tournament> tournament;
	public List<BestStats> tapeball;
	public List<NameSuper> nameSupers;
	public List<Fixture> fixTures;
	public List<Team> Teams;
	public List<Playoff> Playoffs;
	public List<Ground> Grounds;
	public List<Bugs> bugs;
	public List<EverestBugs> everestBugs;
	public List<InfobarStats> infobarStats;
	public List<VariousText> VariousText;
	public List<DuckWorthLewis> dls;
	public List<Commentator> Commentators;
	public List<Staff> Staff;
	public List<Player> Players;
	public List<POTT> Pott;
	public List<String> TeamChanges;
	public List<HeadToHeadPlayer> headToHead;
	public List<Tournament> past_tournament_stats;
	public List<PerformanceBug> performanceBugs;
	@JsonIgnore
	public CricketService cricketService;
	
	public BattingCard battingCard;
	public Inning inning;
	public Player player;
	public Statistics stat;
	public StatsType statsType;
	public LowerThird lowerThird;
	public NameSuper namesuper;
	public Fixture fixture;
	public Team team;
	public Playoff playoff;

	public int FirstPlayerId, SecondPlayerId, whichSide;
	public String WhichProfile, status, captionWhichGfx = "";
	
	public Caption() {
		super();
	}
	
	public Caption(List<PrintWriter> print_writers, Configuration config, List<Statistics> statistics, List<StatsType> statsTypes, 
		List<MatchAllData> tournament_matches, List<NameSuper> nameSupers,List<Bugs> bugs, List<InfobarStats> infobarStats, List<Fixture> fixTures,
		List<Team> Teams, List<Ground> Grounds, List<VariousText> varioustText, List<Commentator> commentators, List<Staff> staff, List<Player> players, 
		List<POTT> pott,List<Playoff> Playoffs, List<String> teamChanges, List<PerformanceBug> performanceBugs, FullFramesGfx this_fullFramesGfx,
		LowerThirdGfx this_lowerThirdGfx, InfobarGfx this_infobarGfx,BugsAndMiniGfx this_bugsAndMiniGfx, int whichSide, String whichGraphhicsOnScreen, 
		String slashOrDash, List<Tournament> tournament,List<BestStats> tapeball,List<DuckWorthLewis> dls, List<HeadToHeadPlayer> headToHead, 
		List<Tournament> past_tournament_stats, CricketService cricketService,List<EverestBugs> everestBugs) {
	
		super();
		this.print_writers = print_writers;
		this.config = config;
		this.statistics = statistics;
		this.statsTypes = statsTypes;
		this.tournament_matches = tournament_matches;
		this.nameSupers = nameSupers;
		this.bugs = bugs;
		this.infobarStats = infobarStats;
		this.fixTures = fixTures;
		this.Teams = Teams;
		this.Grounds = Grounds;
		this.tournament = tournament;
		this.tapeball = tapeball;
		this.VariousText = varioustText;
		this.Commentators = commentators;
		this.Staff = staff;
		this.Players = players;
		this.Pott = pott;
		this.Playoffs = Playoffs;
		this.TeamChanges = teamChanges;
		this.headToHead = headToHead;
		this.past_tournament_stats = past_tournament_stats;
		this.cricketService = cricketService;
		this.performanceBugs = performanceBugs;
		this.everestBugs = everestBugs;
		this.dls = dls;
		this.this_fullFramesGfx = new FullFramesGfx(print_writers, config, statistics, statsTypes, tournament_matches, 
				fixTures, Teams, Grounds,tournament, VariousText, players, pott,Playoffs, teamChanges,headToHead, past_tournament_stats, cricketService);
		this.this_lowerThirdGfx = new LowerThirdGfx(print_writers, config, statistics, statsTypes, tournament_matches, 
				nameSupers, Teams, Grounds, tournament,tapeball, dls, staff, players, pott, varioustText, headToHead, past_tournament_stats, cricketService,fixTures);
		this.whichSide = whichSide;
		this.this_infobarGfx = new InfobarGfx(config, slashOrDash, print_writers, statistics, statsTypes, infobarStats, Grounds, Commentators, 
				tournament_matches, dls, players, headToHead, past_tournament_stats, cricketService);
		this.this_bugsAndMiniGfx = new BugsAndMiniGfx(print_writers, config, bugs, performanceBugs, Teams, VariousText, cricketService, headToHead, tournament_matches,
				statistics, statsTypes, past_tournament_stats,everestBugs);
		this.status = "";
	}

	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

	public void PopulateGraphics(String whatToProcess, MatchAllData matchAllData) throws Exception
	{
		if(whatToProcess.contains(",")) {
			switch (whatToProcess.split(",")[0]) {
			case "F1": //ScoreCard
				status = this_fullFramesGfx.PopulateScorecardFF(whichSide, whatToProcess.split(",")[0], matchAllData, Integer.valueOf(whatToProcess.split(",")[1]));
				break;
			case "Control_Shift_F4":
				this_fullFramesGfx.this_ALL_FF.FirstPlayerId = Integer.valueOf(whatToProcess.split(",")[2]);
				this_fullFramesGfx.this_ALL_FF.WhichType = whatToProcess.split(",")[3];
				this_fullFramesGfx.this_ALL_FF.Players = Players;
			    status = this_fullFramesGfx.PopulateScorecardBatPerformerFF(whichSide, whatToProcess.split(",")[0], matchAllData, Integer.valueOf(whatToProcess.split(",")[1]));
				break;
			case "Control_Shift_F1":
				switch (config.getBroadcaster().toUpperCase()) {
				case Constants.AFG_SL_SERIES:
					this_fullFramesGfx.this_ALL_FF.FirstPlayerId = Integer.valueOf(whatToProcess.split(",")[2]);
					this_fullFramesGfx.this_ALL_FF.WhichType = whatToProcess.split(",")[3];
					this_fullFramesGfx.this_ALL_FF.Players = Players;
				    status = this_fullFramesGfx.PopulateScorecardBatPerformerFF(whichSide, whatToProcess.split(",")[0], matchAllData, Integer.valueOf(whatToProcess.split(",")[1]));
					break;
				default:
					if(this_anim.whichGraphicOnScreen.isEmpty() || this_anim.whichGraphicOnScreen.equalsIgnoreCase("F1")) {
						this_fullFramesGfx.this_FC_FF.FirstPlayerId = Integer.valueOf(whatToProcess.split(",")[2]);
						this_fullFramesGfx.this_FC_FF.WhichType = whatToProcess.split(",")[3];
						this_fullFramesGfx.this_ALL_FF.FirstPlayerId = Integer.valueOf(whatToProcess.split(",")[2]);
						this_fullFramesGfx.this_ALL_FF.WhichType = whatToProcess.split(",")[3];
						this_fullFramesGfx.this_ALL_FF.Players = Players;
						if (!this_anim.whichGraphicOnScreen.equalsIgnoreCase("F1")) {
						    status = this_fullFramesGfx.PopulateScorecardFF(whichSide, "F1", matchAllData, Integer.valueOf(whatToProcess.split(",")[1]));
						}
						status = this_fullFramesGfx.PopulateBatPerformerFF(whichSide, whatToProcess.split(",")[0], matchAllData, 
							Integer.valueOf(whatToProcess.split(",")[1]));
					} else {
						status = "Bat performer is available on blank screen or when FF scorecard is on screen";
					}
					break;
				}
				
				break;
			case "Control_Alt_F1":
				status = this_fullFramesGfx.PopulateScoreBowlingCardFF(whichSide, whatToProcess.split(",")[0], matchAllData, 
						Integer.valueOf(whatToProcess.split(",")[1]));
				break;
			case "Alt_Shift_F1":
				status = this_fullFramesGfx.PopulateScoreCardContributionFF(whichSide, whatToProcess.split(",")[0], matchAllData, 
						Integer.valueOf(whatToProcess.split(",")[1]));
				break;
				
			case "F2": // Bowling FF
				status = this_fullFramesGfx.PopulateBowlingCardFF(whichSide, whatToProcess.split(",")[0], matchAllData, Integer.valueOf(whatToProcess.split(",")[1]));
				break;
			case "Control_Shift_F5":
				this_fullFramesGfx.this_ALL_FF.FirstPlayerId = Integer.valueOf(whatToProcess.split(",")[2]);
				this_fullFramesGfx.this_ALL_FF.WhichType = whatToProcess.split(",")[3];
				this_fullFramesGfx.this_ALL_FF.Players = Players;
			    status = this_fullFramesGfx.PopulateBowlingCardBallPerformerFF(whichSide, whatToProcess.split(",")[0], matchAllData, 
			    		Integer.valueOf(whatToProcess.split(",")[1]));
				break;
			case "Control_Shift_F2":
				if(this_anim.whichGraphicOnScreen.isEmpty() || this_anim.whichGraphicOnScreen.equalsIgnoreCase("F2")) {
					this_fullFramesGfx.this_ALL_FF.FirstPlayerId = Integer.valueOf(whatToProcess.split(",")[2]);
					this_fullFramesGfx.this_ALL_FF.WhichType = whatToProcess.split(",")[3];
					this_fullFramesGfx.this_ALL_FF.Players = Players;
					if(!this_anim.whichGraphicOnScreen.equalsIgnoreCase("F2")) {
						status = this_fullFramesGfx.PopulateBowlingCardFF(whichSide, "F2", matchAllData, Integer.valueOf(whatToProcess.split(",")[1]));
					}
					status = this_fullFramesGfx.PopulateBallPerformerFF(whichSide, whatToProcess.split(",")[0], matchAllData, 
						Integer.valueOf(whatToProcess.split(",")[1]));
				} else {
					status = "Ball performer is available on blank screen or when FF bowling card is on screen";
				}
				break;	
			case "F4": //All Partnership
				status = this_fullFramesGfx.populatePartnership(whichSide, whatToProcess.split(",")[0], matchAllData, Integer.valueOf(whatToProcess.split(",")[1]));
				break;
			case "Control_F11": //MATCH SUMMARY
				switch (config.getBroadcaster().toUpperCase()) {
				case Constants.ACC:
					this_fullFramesGfx.this_ALL_FF.WhichStyle = whatToProcess.split(",")[2];
					break;
				}
				
				status = this_fullFramesGfx.populateMatchSummary(whichSide, whatToProcess.split(",")[0], matchAllData, Integer.valueOf(whatToProcess.split(",")[1]));
				break;
			case "m":
				status = this_fullFramesGfx.populateFFMatchId(whichSide,whatToProcess.split(",")[0], matchAllData);
				break;
			case "Control_d": case "Control_e":
				status = this_fullFramesGfx.populatePlayerProfile(whichSide, whatToProcess, matchAllData, 0);
				break;
			case "Control_m": //MATCH PROMO
				status = this_fullFramesGfx.populateFFMatchPromo(whichSide, whatToProcess,matchAllData);
				break;
			case "Control_F7": // Double Teams
				status = this_fullFramesGfx.PopulateDoubleTeams(whichSide, whatToProcess.split(",")[0], matchAllData);
				break;
			case "Shift_K"://FF curr part
				status = this_fullFramesGfx.populateCurrPartnership(whichSide, whatToProcess.split(",")[0], matchAllData, whichSide);
				break;
			case "Control_F10":
				status = this_fullFramesGfx.populateManhattan(whichSide, whatToProcess.split(",")[0],matchAllData,Integer.valueOf(whatToProcess.split(",")[1]));
				break;
			case "Shift_F10": //WORM
				status = this_fullFramesGfx.populateWorms(whichSide, whatToProcess.split(",")[0], matchAllData, 
					Integer.valueOf(whatToProcess.split(",")[1]));
				break;
			case "Shift_D":
				status = this_fullFramesGfx.populateTarget(whichSide, whatToProcess.split(",")[0], matchAllData, Integer.valueOf(whatToProcess.split(",")[1]));
				break;
			case "Shift_T":
				status = this_fullFramesGfx.populatePlayingXI(whichSide, whatToProcess.split(",")[0],
						Integer.valueOf(whatToProcess.split(",")[2]), matchAllData, 0);
					break;
			case "Control_Shift_F7":
				this_fullFramesGfx.this_ALL_FF.Players = Players;
				status = this_fullFramesGfx.populateSecondPlayingXI(whichSide, whatToProcess.split(",")[0],
					Integer.valueOf(whatToProcess.split(",")[2]), matchAllData, 0);
				break;
			case "Alt_F9": // Single Teams Career
				status = this_fullFramesGfx.populateSingleTeamsCareer(whichSide, whatToProcess, matchAllData, 0);
				break;
			case "Alt_F11":
				status = this_fullFramesGfx.populateDoubleManhattan(whichSide, whatToProcess.split(",")[0],matchAllData,Integer.valueOf(whatToProcess.split(",")[1]));
				break;
			case "Control_F1":// Photo ScoreCard
				status = this_fullFramesGfx.PopulatePhotoScorecardFF(whichSide, whatToProcess.split(",")[0], matchAllData, Integer.valueOf(whatToProcess.split(",")[1]));
				break;
			case "Shift_F11":
				switch (config.getBroadcaster().toUpperCase()) {
				case Constants.ACC:
					this_fullFramesGfx.this_ALL_FF.WhichStyle = whatToProcess.split(",")[3];
					break;
				}
				
//				this_fullFramesGfx.this_ALL_FF.WhichStyle = whatToProcess.split(",")[3];
				status = this_fullFramesGfx.populatePreviousMatchSummary(whichSide, whatToProcess, matchAllData, Integer.valueOf(whatToProcess.split(",")[1]));
				break;
			case "Control_p":// Points Table
				System.out.println(whatToProcess);
				if(config.getBroadcaster().equalsIgnoreCase(Constants.ACC)) {
					this_fullFramesGfx.WhichGroup = whatToProcess.split(",")[2];
				}else {
					this_fullFramesGfx.WhichGroup = "LeagueTable";
				}
				status = this_fullFramesGfx.populateFFPointsTable(whichSide,whatToProcess.split(",")[0], matchAllData, 0);
				break;
			case "z": case "x": case "c": case "v": case "Control_z": case "Control_x": case "Control_Shift_Z": case "Control_Shift_Y":
			case "Alt_Shift_W":
				if(!whatToProcess.split(",")[0].equalsIgnoreCase("Alt_Shift_W")) {
					this_fullFramesGfx.FirstPlayerId = Integer.valueOf((whatToProcess.split(",")[2]).split("_")[1]);
					this_fullFramesGfx.this_ALL_FF.FirstPlayerId = Integer.valueOf((whatToProcess.split(",")[2]).split("_")[1]);
				}
				
				if(whatToProcess.split(",")[0].equalsIgnoreCase("Alt_Shift_W")){
					this_fullFramesGfx.whichtype = whatToProcess.split(",")[3];
					this_fullFramesGfx.whichTeam = Integer.valueOf(whatToProcess.split(",")[2]);
					
					this_fullFramesGfx.this_ALL_FF.whichtype = whatToProcess.split(",")[3];
					this_fullFramesGfx.this_ALL_FF.whichTeam = Integer.valueOf(whatToProcess.split(",")[2]);
				}
				
				status = this_fullFramesGfx.populateLeaderBoard(whichSide, whatToProcess, matchAllData, 0);
				break;
			case "Shift_P": case "Shift_Q":
				status = this_fullFramesGfx.populateThisSeries(whichSide, whatToProcess, matchAllData, 0);
				break;	
				
				
			case "Alt_F7":// Points Table
				if(config.getBroadcaster().equalsIgnoreCase(Constants.ACC)) {
					this_bugsAndMiniGfx.WhichGroup = whatToProcess.split(",")[2];
				}else {
					this_bugsAndMiniGfx.WhichGroup = "LeagueTable";
				}
				status = this_bugsAndMiniGfx.populatePointsTable(whatToProcess.split(",")[0], matchAllData,whichSide);
				break;
				
			case "Alt_e":
				status = this_infobarGfx.powerplay(print_writers, matchAllData);
				break;
				
			case "Control_F12":
				switch (config.getBroadcaster().toUpperCase()) {
				case Constants.TRI_SERIES:  case Constants.MT20: case Constants.AFG_SL_SERIES:
					this_infobarGfx.infobar.setInfobar_ident_section(whatToProcess.split(",")[3]);
					break;
				case Constants.BAN_AFG_SERIES: case Constants.ACC: 
					this_infobarGfx.infobar.setInfobar_ident_section(whatToProcess.split(",")[2]);
					break;
				}
				status = this_infobarGfx.populateInfobarIdent(print_writers,whatToProcess,matchAllData,whichSide);
				break;
			case "Shift_F12":
				this_infobarGfx.infobar.setInfobar_ident_section(whatToProcess.split(",")[2]);
				switch (config.getBroadcaster().toUpperCase()) {
				case Constants.TRI_SERIES:  case Constants.MT20: case Constants.AFG_SL_SERIES:
					status = this_infobarGfx.infoIdentSection(print_writers, whatToProcess, matchAllData, 1,whichSide);
					break;
				case Constants.BAN_AFG_SERIES: case Constants.ACC: 
					status = this_infobarGfx.infoIdentSection(print_writers, whatToProcess, matchAllData, whichSide,1);
					break;
				}
				break;
			case "F12":// InfoBar
				this_infobarGfx.infobar.setInfobar_on_screen(false);
				this_infobarGfx.infobar.setPowerplay_on_screen(false);
				
				this_infobarGfx.BatterTickerName = false;
				this_infobarGfx.bowlerTickerName = false;
				
				this_infobarGfx.infobar.setSection1("");
				this_infobarGfx.infobar.setSection2("");
				this_infobarGfx.infobar.setSection3("");
				this_infobarGfx.infobar.setSection4("");
				this_infobarGfx.infobar.setSection5("");
				this_infobarGfx.infobar.setSectionAnalytics("");
				
				this_infobarGfx.infobar.setLast_sectionAnalytics("");
				this_infobarGfx.infobar.setLast_section1("");
				this_infobarGfx.infobar.setLast_section2("");
				this_infobarGfx.infobar.setLast_section3("");
				this_infobarGfx.infobar.setLast_section4("");
				this_infobarGfx.infobar.setLast_section5("");
				
				status = this_infobarGfx.populateInfobar(print_writers, whatToProcess, matchAllData);	
				break;
				
				
			case "Alt_F1": case "Alt_F2":// BatGriff
				switch (config.getBroadcaster().toUpperCase()) {
				case Constants.TRI_SERIES:  case Constants.MT20: 
					status = this_lowerThirdGfx.PopulateBatBallGriff(whatToProcess,whichSide, matchAllData);
					break;
				case Constants.BAN_AFG_SERIES: case Constants.ACC: 
					status = this_bugsAndMiniGfx.populateGriff(whatToProcess,whichSide, matchAllData);
					break;	
				}
				break;	
			
			case "l"://All-rounderStats
				status = this_lowerThirdGfx.populateL3rdAllRounderStats(whatToProcess,whichSide,matchAllData);
				break;	
			case "Control_Shift_X":
				status = this_lowerThirdGfx.PopulateCurrentAndCareer(whatToProcess,whichSide, matchAllData);
				break;
			case "Control_Shift_K": //BOWL THIS MATCH
				status = this_lowerThirdGfx.populateBowlThisMatchAndCareer(whatToProcess, whichSide, matchAllData);
				break;
			case "Control_c":
				status = this_lowerThirdGfx.populateMultiCareer(whatToProcess, whichSide, matchAllData);
				break;	
			case "Control_a"://Projected
				status = this_lowerThirdGfx.populateL3rdProjected(whatToProcess,whichSide,matchAllData);
				break;
			case "F8": case "Alt_F8": //HOME NAMESUPER PLAYER
				status = this_lowerThirdGfx.populateLTNameSuperPlayer(whatToProcess,whichSide,matchAllData);
				break;	
			case "F10": //NameSuper DB
				status = this_lowerThirdGfx.populateLTNameSuper(whatToProcess,whichSide, matchAllData);
				break;
			case "F7": case "F11": // L3rd BAT and BALL Profile
				status = this_lowerThirdGfx.PopulateL3rdPlayerProfile(whatToProcess,whichSide, matchAllData);
				break;
			case "F6"://HowOut
				status = this_lowerThirdGfx.populateHowOut(whatToProcess,whichSide,matchAllData);
				break;
			case "Control_s": case "Control_f":
				status = this_lowerThirdGfx.populateL3rdThisSeries(whatToProcess,whichSide,matchAllData);
				break;
			case "Shift_F6":
				status = this_lowerThirdGfx.populateHowOutWithOutFielder(whatToProcess,whichSide,matchAllData);
				break;
			case "Control_F6":
				status = this_lowerThirdGfx.populateQuickHowOut(whatToProcess,whichSide,matchAllData);
				break;
			case "Control_F5"://Batsman Style
				status = this_lowerThirdGfx.populateBattingStyle(whatToProcess,whichSide,matchAllData);
				break;
			case "Control_F9"://Bowler Style
				status = this_lowerThirdGfx.populateBowlingStyle(whatToProcess,whichSide,matchAllData);
				break;
			case "F5": //BAT THIS MATCH
				status = this_lowerThirdGfx.populateBatThisMatch(whatToProcess, whichSide, matchAllData);
				break;
			case "F9": //BOWL THIS MATCH
				status = this_lowerThirdGfx.populateBowlThisMatch(whatToProcess, whichSide, matchAllData);
				break;
			case "Shift_F3": //Fall of Wicket
				status = this_lowerThirdGfx.populateFOW(whatToProcess, whichSide, matchAllData);
				break;
			case "d": //Target
				status = this_lowerThirdGfx.populateL3rdTarget(whatToProcess, whichSide, matchAllData);
				break;
			case "e": //Equation
				status = this_lowerThirdGfx.populateL3rdEquation(whatToProcess, whichSide, matchAllData);	
				break;
			case "Shift_E": //Extras
				status = this_lowerThirdGfx.populateL3rdExtras(whatToProcess, whichSide, matchAllData);
				break;
			case "u": //30-50
				status = this_lowerThirdGfx.populate30_50Split(whatToProcess, whichSide, matchAllData);
				break;
			case "Alt_Shift_F4":
				status = this_lowerThirdGfx.populateL3PlayerProgression(whatToProcess,whichSide,matchAllData);
				break;
			case "Control_h"://phase wise
				status = this_lowerThirdGfx.populateL3PhaseWise(whatToProcess,whichSide,matchAllData);
				break;
			case "Alt_Shift_F3":
				status = this_lowerThirdGfx.populatePhaseComp(whatToProcess,whichSide, matchAllData);
				break;
			case "Control_F3"://Comparison
				status = this_lowerThirdGfx.populateL3rdComparison(whatToProcess,whichSide,matchAllData);
				break;
			case "Control_Shift_M": //LT Match id
			     status = this_lowerThirdGfx.populateLTMatchId(whatToProcess, whichSide, matchAllData);
				break;	
			case "Shift_F5"://Bat 012
				status = this_lowerThirdGfx.populateBatSummary(whatToProcess,whichSide,matchAllData);
				break;
			case "Shift_F9"://Ball 012
				status = this_lowerThirdGfx.populateBallSummary(whatToProcess,whichSide,matchAllData);
				break;
			case "Alt_F12"://Teams 012
				status = this_lowerThirdGfx.populateTeamSummary(whatToProcess,whichSide,matchAllData);
				break;
			case "Control_Shift_F10":
				status = this_lowerThirdGfx.InfobarManhattan(print_writers,matchAllData,Integer.valueOf(whatToProcess.split(",")[1]));
				break;
			case "Control_Shift_O":
				status = this_lowerThirdGfx.populateL3rdLineUp(whatToProcess,whichSide,matchAllData);
				break;
			case "Control_Shift_L": // Lt promo
				status = this_lowerThirdGfx.populateLTMatchPromo(whatToProcess,whichSide,matchAllData);
				break;	
			case "Control_6": 
				status = this_lowerThirdGfx.populateWeather(whatToProcess);
				break;
			case "Control_Shift_B":
				status = this_lowerThirdGfx.populateNextToBat(whatToProcess,whichSide, matchAllData);
				break;
			case "Control_Shift_Q":
				status = this_lowerThirdGfx.populateGeneric(whatToProcess,whichSide,matchAllData);
				break;
			case "Alt_d":// DLS Target
				status = this_lowerThirdGfx.populateDlsTarget(whatToProcess,whichSide,matchAllData);
				break;	
			case "Shift_F": //wicket sequencing
				status = this_bugsAndMiniGfx.populateWicketSequencing(whatToProcess, matchAllData, whichSide);
				break;	
			case "y": // Bug Batsman Score
				status = this_bugsAndMiniGfx.populateBatScore(whatToProcess, matchAllData, whichSide);
				break;
			case "g": //Bug Bowler fig
				status = this_bugsAndMiniGfx.populateBowlScore(whatToProcess, matchAllData, whichSide);
				break;
			case "k"://DataBase
				status = this_bugsAndMiniGfx.bugsDB(whatToProcess,whichSide,matchAllData);
				break;
			case "h":
				status = this_bugsAndMiniGfx.populateBugHighlight(whatToProcess,matchAllData,whichSide, Integer.valueOf(whatToProcess.split(",")[1]));
				break;
			case "Control_y":
				status = this_bugsAndMiniGfx.populatebugPowerplay(whatToProcess,whichSide ,matchAllData);
				break;
			case "Control_k": //Curr Partnership
				status = this_bugsAndMiniGfx.bugsCurrPartnership(whatToProcess,matchAllData,whichSide);
				break;
			case "Shift_F4": 
				status = this_bugsAndMiniGfx.bugMultiPartnership(whatToProcess,matchAllData,whichSide);
				break;
			case "Shift_O":
				status = this_bugsAndMiniGfx.bugsDismissal(whatToProcess,matchAllData,whichSide);
				break;
			case "Alt_p":
				status = this_bugsAndMiniGfx.bugsToss(whatToProcess,matchAllData,whichSide);
				break;
			case "Control_Shift_U_change_on": case "Control_Shift_V_change_on":
				status = this_bugsAndMiniGfx.populatePopupChangeOn(whatToProcess, whichSide, matchAllData);
				break;
			case "Control_Shift_U": case "Control_Shift_V":
				status = this_bugsAndMiniGfx.populatePopup(whatToProcess, whichSide, matchAllData);
				break;
			case "Control_4": case "Control_5":
				status = this_bugsAndMiniGfx.populateFourCounter(whatToProcess, whichSide, matchAllData);
				break;	
			case "6": case "Control_7":
				status = this_bugsAndMiniGfx.populateCounter(whatToProcess, whichSide, matchAllData);
				break;
			case "5":
				status = this_bugsAndMiniGfx.populateThisMatchFourCounter(whatToProcess, whichSide, matchAllData);
				break;	
			case ";":
				status = this_bugsAndMiniGfx.populateThisMatchSixCounter(whatToProcess, whichSide, matchAllData);
				break;
			case "Control_Shift_J":
				status = this_bugsAndMiniGfx.populatePerformanceBug(whatToProcess, whichSide, matchAllData);
				break;
			case "Control_Shift_F3": 
				status = this_bugsAndMiniGfx.populateBugTarget(whatToProcess,matchAllData, whichSide);
				break;
			case "Control_Shift_R":
				status = this_bugsAndMiniGfx.populateBugResult(whatToProcess,matchAllData,whichSide);
				break;	
			case "Shift_F1":
				status = this_bugsAndMiniGfx.populateMiniScorecard(whichSide, whatToProcess,matchAllData);
				break;
			case "Shift_F2":
				status = this_bugsAndMiniGfx.populateMiniBowlingcard(whichSide, whatToProcess,matchAllData);
				break;
			case "Control_Shift_*":
				status = this_bugsAndMiniGfx.populateSponsor(whichSide, whatToProcess,matchAllData,everestBugs);
				break;
			case "Shift_C":
				status = this_bugsAndMiniGfx.populateBugSixDistance(whatToProcess,matchAllData,whichSide);
				break;
			case "Alt_Shift_Q":
				this_scene = new Scene();
				this_scene.LoadScene("LOF_PLOTTER", print_writers, config);
				this_infobarGfx.containerName = "m";
//				if(config.getBroadcaster().equalsIgnoreCase(Constants.MPL)||
//						config.getBroadcaster().equalsIgnoreCase(Constants.NPL)||
//						config.getBroadcaster().equalsIgnoreCase(Constants.LEGENDS) ||
//						config.getBroadcaster().equalsIgnoreCase(Constants.BENGAL_T20)) {
//					this_infobarGfx.containerName = " " + whatToProcess.split(",")[2];	
//				}
				status = this_infobarGfx.populateLofDimension(print_writers, matchAllData);
				break;	
			case "Alt_1":
				this_infobarGfx.infobar.setSection1(whatToProcess.split(",")[2]);
				switch (config.getBroadcaster().toUpperCase()) {
				case Constants.TRI_SERIES:  case Constants.MT20: case Constants.AFG_SL_SERIES:
					status = this_infobarGfx.populateSection1(print_writers, matchAllData, whichSide);
					break;
				case Constants.BCCI:
					status = this_infobarGfx.populateVizInfobarLeftBottom(print_writers, matchAllData, whichSide);
					break;
				}
				break;
			case "Alt_2":
				switch(config.getBroadcaster()) {
				case Constants.BAN_AFG_SERIES: case Constants.ACC: 
					whichSide = (this_infobarGfx.infobar.getSection2() != null && !this_infobarGfx.infobar.getSection2().isEmpty() 
		            	&& !this_infobarGfx.infobar.getSection2().equalsIgnoreCase(whatToProcess.split(",")[2])) ? 2 : 1;
					
					this_infobarGfx.infobar.setSection2(whatToProcess.split(",")[2]);
					
					status = this_infobarGfx.populateSection2(print_writers, matchAllData, whichSide);
					break;
				case Constants.TRI_SERIES:  case Constants.MT20: case Constants.AFG_SL_SERIES:
					whichSide = (this_infobarGfx.infobar.getSection2() != null && !this_infobarGfx.infobar.getSection2().isEmpty() 
		            && !this_infobarGfx.infobar.getSection2().equalsIgnoreCase(whatToProcess.split(",")[2])) ? 2 : 1;
					
					if(!whatToProcess.split(",")[2].equalsIgnoreCase("BLANK")) {
						this_infobarGfx.infobar.setSection2(whatToProcess.split(",")[2]);
					}else {
						this_infobarGfx.infobar.setSection2("");
					}

					status = this_infobarGfx.populateSection2(print_writers, matchAllData, whichSide);
					break;
				case Constants.BCCI:
					whichSide = (this_infobarGfx.infobar.getSection3() != null && !this_infobarGfx.infobar.getSection3().isEmpty() 
		            && !this_infobarGfx.infobar.getSection3().equalsIgnoreCase(whatToProcess.split(",")[2])) ? 2 : 1;
					
					if(!whatToProcess.split(",")[2].equalsIgnoreCase("BLANK")) {
						this_infobarGfx.infobar.setSection3(whatToProcess.split(",")[2]);
						if(whatToProcess.split(",")[2].equalsIgnoreCase("CRR")) {
							this_infobarGfx.lastXballs = Integer.valueOf(whatToProcess.split(",")[3]);
						}
					}else {
						this_infobarGfx.infobar.setSection3("");
					}

					status = this_infobarGfx.populateVizInfobarMiddleSection(print_writers,matchAllData, whichSide);
					break;
				}
				break;
			case "Alt_3": case "Alt_4":
				if(this_infobarGfx.infobar.getSectionLtAnalytics() != null && !this_infobarGfx.infobar.getSectionLtAnalytics().isEmpty()) {
					switch(whatToProcess.split(",")[0]) {
					case "Alt_3":
						if (!this_infobarGfx.infobar.getSectionLtAnalytics().equalsIgnoreCase("BAT_PP") ||
						    this_infobarGfx.PP_Id != Integer.valueOf(whatToProcess.split(",")[2]) ||
						    !this_infobarGfx.WhichProfile.equalsIgnoreCase(whatToProcess.split(",")[3])) {
						    
						    whichSide = 2;
						} else {
						    whichSide = 1;
						}
						this_infobarGfx.infobar.setSectionLtAnalytics("BAT_PP");
						break;
					case "Alt_4":
						if (!this_infobarGfx.infobar.getSectionLtAnalytics().equalsIgnoreCase("BALL_PP") ||
						    this_infobarGfx.PP_Id != Integer.valueOf(whatToProcess.split(",")[2]) ||
						    !this_infobarGfx.WhichProfile.equalsIgnoreCase(whatToProcess.split(",")[3])) {
						    
						    whichSide = 2;
						} else {
						    whichSide = 1;
						}
						
						this_infobarGfx.infobar.setSectionLtAnalytics("BALL_PP");
						break;
					}
					
					this_infobarGfx.PP_Id = Integer.valueOf(whatToProcess.split(",")[2]);
					this_infobarGfx.WhichProfile = whatToProcess.split(",")[3];
					
				}else {
					whichSide = 1;
					
					switch(whatToProcess.split(",")[0]) {
					case "Alt_3":
						this_infobarGfx.infobar.setSectionLtAnalytics("BAT_PP");
						break;
					case "Alt_4":
						this_infobarGfx.infobar.setSectionLtAnalytics("BALL_PP");
						break;
					}
					
					this_infobarGfx.PP_Id = Integer.valueOf(whatToProcess.split(",")[2]);
					this_infobarGfx.WhichProfile = whatToProcess.split(",")[3];
				}
				
				status = this_infobarGfx.populateSectionLtAnalytics(print_writers, matchAllData, whichSide);
				break;
			case "Alt_5":
				switch (config.getBroadcaster().toUpperCase()) {
				case Constants.TRI_SERIES:  case Constants.MT20: case Constants.BAN_AFG_SERIES: case Constants.ACC:
					whichSide = (this_infobarGfx.infobar.getSection5() != null && !this_infobarGfx.infobar.getSection5().isEmpty() 
		            && !this_infobarGfx.infobar.getSection5().equalsIgnoreCase(whatToProcess.split(",")[2])) ? 2 : 1;
					
					if(!whatToProcess.split(",")[2].equalsIgnoreCase("BLANK")) {
						this_infobarGfx.bowlerOnScreen = false;
						
						this_infobarGfx.infobar.setSection5(whatToProcess.split(",")[2]);
						if(whatToProcess.split(",")[2].equalsIgnoreCase("LastXBalls")) {
							this_infobarGfx.lastXballs = Integer.valueOf(whatToProcess.split(",")[3]);
						}else if(whatToProcess.split(",")[2].equalsIgnoreCase("CURR_PARTNERSHIP")) {
							this_infobarGfx.photoCategory = whatToProcess.split(",")[3];
						}
					}else {
						this_infobarGfx.bowlerOnScreen = true;
						this_infobarGfx.infobar.setSection5("");
					}
					
					status = this_infobarGfx.populateSection5(print_writers, matchAllData, whichSide);
					break;
				case Constants.BCCI:
					this_infobarGfx.infobar.setSection5(whatToProcess.split(",")[2]);
					status = this_infobarGfx.populateVizInfobarSection5(print_writers, matchAllData, whichSide, 1);
					break;
				}
				break;
			case "Alt_6":
				switch(config.getBroadcaster()) {
				case Constants.TRI_SERIES:  case Constants.MT20:
					whichSide = (this_infobarGfx.infobar.getSection4() != null && !this_infobarGfx.infobar.getSection4().isEmpty() 
		            && !this_infobarGfx.infobar.getSection4().equalsIgnoreCase(whatToProcess.split(",")[2])) ? 2 : 1;
					
					if(!whatToProcess.split(",")[2].equalsIgnoreCase("BLANK")) {
						this_infobarGfx.infobar.setSection4(whatToProcess.split(",")[2]);
					}else {
						this_infobarGfx.infobar.setSection4("");
					}
					status = this_infobarGfx.populateSection4(print_writers, matchAllData, whichSide);
					break;
				}
				break;
			case "Alt_7":
				switch (config.getBroadcaster().toUpperCase()) {
				case Constants.TRI_SERIES:  case Constants.MT20: case Constants.ACC: case Constants.AFG_SL_SERIES:
					this_infobarGfx.infobar.setSection3(whatToProcess.split(",")[2]);
					
					if(whatToProcess.split(",")[2].equalsIgnoreCase("LastXBalls")) {
						this_infobarGfx.lastXballs = Integer.valueOf(whatToProcess.split(",")[3]);
					}
					
					status = this_infobarGfx.populateSection3(print_writers, matchAllData, whichSide);
					break;
				case Constants.BCCI:
					this_infobarGfx.infobar.setSection4(whatToProcess.split(",")[2]);
					status = this_infobarGfx.populateVizInfobarRightBottom(print_writers, matchAllData, whichSide, 1);
					break;
				}
				break;
			case "Alt_8":
				switch(config.getBroadcaster()) {
				case Constants.TRI_SERIES:  case Constants.MT20: case Constants.BAN_AFG_SERIES: case Constants.ACC:
					whichSide = (this_infobarGfx.infobar.getSectionAnalytics() != null && !this_infobarGfx.infobar.getSectionAnalytics().isEmpty() 
		            && !this_infobarGfx.infobar.getSectionAnalytics().equalsIgnoreCase(whatToProcess.split(",")[2])) ? 2 : 1;
					
					if(!whatToProcess.split(",")[2].equalsIgnoreCase("BLANK")) {
						this_infobarGfx.bowlerOnScreen = false;
						this_infobarGfx.batterOnScreen = false;
						
						if(whatToProcess.split(",")[2].equalsIgnoreCase("Commentators")) {
							if(this_infobarGfx.infobar.getSectionAnalytics() != null && !this_infobarGfx.infobar.getSectionAnalytics().isEmpty() 
									&& this_infobarGfx.infobar.getSectionAnalytics().equalsIgnoreCase("Commentators")) {
								whichSide = (!this_infobarGfx.Comms_Name.isEmpty() && !this_infobarGfx.Comms_Name.equalsIgnoreCase(String.join(",", 
										Arrays.asList(whatToProcess.split(",")).subList(whatToProcess.split(",").length - 3, whatToProcess.split(",").length)))?2:1);
							}
							this_infobarGfx.Comms_Name = String.join(",", Arrays.asList(whatToProcess.split(","))
									.subList(whatToProcess.split(",").length - 3, whatToProcess.split(",").length));
						}
						else if(whatToProcess.split(",")[2].equalsIgnoreCase("FreeTextDb")) {
							if(this_infobarGfx.infobar.getSectionAnalytics() != null && !this_infobarGfx.infobar.getSectionAnalytics().isEmpty() 
									&& this_infobarGfx.infobar.getSectionAnalytics().equalsIgnoreCase("FreeTextDb")) {
								whichSide = (this_infobarGfx.infobarStatsId > 0 && this_infobarGfx.infobarStatsId != Integer.valueOf(whatToProcess.split(",")[3])) ? 2 : 1;
							}
							this_infobarGfx.infobarStatsId = Integer.valueOf(whatToProcess.split(",")[3]);	
						}else if(whatToProcess.split(",")[2].equalsIgnoreCase("FreeText")) {
//							if(this_infobarGfx.infobar.getSectionAnalytics() != null && !this_infobarGfx.infobar.getSectionAnalytics().isEmpty() 
//									&& this_infobarGfx.infobar.getSectionAnalytics().equalsIgnoreCase("FreeText")) {
//								whichSide = 2;
//							}
							this_infobarGfx.freeText = whatToProcess;
						}
						else if(whatToProcess.split(",")[2].equalsIgnoreCase("Sponsor")) {
							if(this_infobarGfx.infobar.getSectionAnalytics() != null && !this_infobarGfx.infobar.getSectionAnalytics().isEmpty() 
									&& this_infobarGfx.infobar.getSectionAnalytics().equalsIgnoreCase("Sponsor")) {
								whichSide = (this_infobarGfx.sponsor_id > 0 && this_infobarGfx.sponsor_id != Integer.valueOf(whatToProcess.split(",")[3])) ? 2 : 1;
							}
							this_infobarGfx.sponsor_id = Integer.valueOf(whatToProcess.split(",")[3]);	
						}
						else if(whatToProcess.split(",")[2].equalsIgnoreCase("BatsmanTimeLine")) {
							if(this_infobarGfx.infobar.getSectionAnalytics() != null && !this_infobarGfx.infobar.getSectionAnalytics().isEmpty() 
									&& this_infobarGfx.infobar.getSectionAnalytics().equalsIgnoreCase("BatsmanTimeLine")) {
								whichSide = (this_infobarGfx.FirstPlayerId > 0 && this_infobarGfx.FirstPlayerId != Integer.valueOf(whatToProcess.split(",")[3])) ? 2 : 1;
							}
							this_infobarGfx.FirstPlayerId = Integer.valueOf(whatToProcess.split(",")[3]);
						}else if(whatToProcess.split(",")[2].equalsIgnoreCase("BatMileStone")) {
							if(this_infobarGfx.infobar.getSectionAnalytics() != null && !this_infobarGfx.infobar.getSectionAnalytics().isEmpty() 
									&& this_infobarGfx.infobar.getSectionAnalytics().equalsIgnoreCase("BatMileStone")) {
								whichSide = (this_infobarGfx.FirstPlayerId > 0 && this_infobarGfx.FirstPlayerId != Integer.valueOf(whatToProcess.split(",")[3])) ? 2 : 1;
							}
							this_infobarGfx.FirstPlayerId = Integer.valueOf(whatToProcess.split(",")[3]);
							this_infobarGfx.data_Type = whatToProcess.split(",")[4];
						}
						else if(whatToProcess.split(",")[2].equalsIgnoreCase("BallMileStone")) {
							if(this_infobarGfx.infobar.getSectionAnalytics() != null && !this_infobarGfx.infobar.getSectionAnalytics().isEmpty() 
									&& this_infobarGfx.infobar.getSectionAnalytics().equalsIgnoreCase("BallMileStone")) {
								whichSide = (this_infobarGfx.FirstPlayerId > 0 && this_infobarGfx.FirstPlayerId != Integer.valueOf(whatToProcess.split(",")[3])) ? 2 : 1;
							}
							this_infobarGfx.FirstPlayerId = Integer.valueOf(whatToProcess.split(",")[3]);
							this_infobarGfx.data_Type = whatToProcess.split(",")[4];
						}
						this_infobarGfx.infobar.setSectionAnalytics(whatToProcess.split(",")[2]);
					}else {
						this_infobarGfx.bowlerOnScreen = true;
						this_infobarGfx.batterOnScreen = true;
						
						this_infobarGfx.Comms_Name = "";
						this_infobarGfx.infobarStatsId = 0;
						this_infobarGfx.FirstPlayerId = 0;
						this_infobarGfx.infobar.setSectionAnalytics("");
					}
					status = this_infobarGfx.populateFullSection(print_writers, matchAllData, whichSide);
					break;
				case Constants.BCCI:
					whichSide = (this_infobarGfx.infobar.getSectionAnalytics() != null && !this_infobarGfx.infobar.getSectionAnalytics().isEmpty() 
		            && !this_infobarGfx.infobar.getSectionAnalytics().equalsIgnoreCase(whatToProcess.split(",")[2])) ? 2 : 1;
					
					if(!whatToProcess.split(",")[2].equalsIgnoreCase("BLANK")) {
						this_infobarGfx.infobar.setSectionAnalytics(whatToProcess.split(",")[2]);
						if(whatToProcess.split(",")[2].equalsIgnoreCase("LastXBalls")) {
							this_infobarGfx.lastXballs = Integer.valueOf(whatToProcess.split(",")[3]);
						}else if(whatToProcess.split(",")[2].equalsIgnoreCase("Commentators")) {
							this_infobarGfx.Comms_Name = String.join(",", Arrays.asList(whatToProcess.split(","))
									.subList(whatToProcess.split(",").length - 3, whatToProcess.split(",").length));
						}else if(whatToProcess.split(",")[2].equalsIgnoreCase("AllSession_Summary")) {
							this_infobarGfx.FirstPlayerId = Integer.valueOf(whatToProcess.split(",")[3]);
						}else if(whatToProcess.split(",")[2].equalsIgnoreCase("BatsmanTimeLine")) {
							this_infobarGfx.FirstPlayerId = Integer.valueOf(whatToProcess.split(",")[3]);
							this_infobarGfx.containerName = whatToProcess.split(",")[4];
						}else if(whatToProcess.split(",")[2].equalsIgnoreCase("This_speed")) {
							this_infobarGfx.containerName = whatToProcess.split(",")[3];
						}
					}else {
						this_infobarGfx.infobar.setSectionAnalytics("");
					}
					status = this_infobarGfx.populateFullSection(print_writers, matchAllData, whichSide);
					break;
				}
				break;
			}
		}
	}
}