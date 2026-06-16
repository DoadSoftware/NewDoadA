package com.cricket.captions;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import com.cricket.containers.Infobar;
import com.cricket.controller.IndexController;
import com.cricket.model.BattingCard;
import com.cricket.model.BestStats;
import com.cricket.model.BowlingCard;
import com.cricket.model.Commentator;
import com.cricket.model.Configuration;
import com.cricket.model.DaySession;
import com.cricket.model.DuckWorthLewis;
import com.cricket.model.FieldersData;
import com.cricket.model.ForeignLanguageData;
import com.cricket.model.Ground;
import com.cricket.model.HeadToHeadPlayer;
import com.cricket.model.InfobarStats;
import com.cricket.model.Inning;
import com.cricket.model.MatchAllData;
import com.cricket.model.MultiLanguageDatabase;
import com.cricket.model.OverByOverData;
import com.cricket.model.Partnership;
import com.cricket.model.Player;
import com.cricket.model.Review;
import com.cricket.model.Speed;
import com.cricket.model.Sponsor;
import com.cricket.model.Statistics;
import com.cricket.model.StatsType;
import com.cricket.model.TargetData;
import com.cricket.model.Team;
import com.cricket.model.Tournament;
import com.cricket.model.Weather;
import com.cricket.service.CricketService;
import com.cricket.util.CricketFunctions;
import com.cricket.util.CricketUtil;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

public class InfobarGfx 
{
	public Configuration config;
	public String slashOrDash = "-", WhichProfile = "", containerName = "", status = "", previous_sixes = "", 
			stats_text = "", par_Overs="", Comms_Name,color = "", color2 = "",freeText = "",today_sixes="",
			logoCategory = "",phaseWiseScore = "",matchType="",today_fours="",previous_fours = "",LastOverRuns="",
			category = "",teamName="",photoCategory,data_Type;
	public String how_out_txts="";
	public ForeignLanguageData howOut;
	
	boolean isThisOverLimitExceed = false, isbatsmannotout = false,isimpactBowlIn = false,isimpactBatIn = false,
			bowlerTickerName=false,BatterTickerName=false,bowlerOnScreen=false, batterOnScreen=false;
	String Ident_Line1 = "",Ident_Line2 = "",currentOnStrike="",previousOnStrike="";
	private boolean isNullOrEmpty(String s) {
	    return s == null || s.isEmpty();
	}
	public int batter1PreviousRuns=0, batter2PreviousRuns=0, batter1PreviousBall=0,batter2PreviousBall=0;
	public static int lastBatsmanOnStrike = 0;
	
	public int previous_runs = 0, previous_wickets = 0, previous_overs=0, previous_balls=0, next_ball = 0,
		bowlerPreviousRuns=0,bowlerPreviousWickets=0,bowlerPreviousOvers=0,bowlerPreviousBalls=0,bowlerNextBall=0;
	
	public int FirstPlayerId=0,lastXballs,sponsor_omo,infobarStatsId=0,rowId=0,challengedRuns,BallsBowledInInnings,omo=0,PP_Id=0,sponsor_id=0;

	public Inning inning = new Inning();
	public Team team = new Team();
	public Infobar infobar = new Infobar();
	public Animation this_animation = new Animation();
	public TargetData this_targetData = new TargetData();
	Map<Integer, String> previousOnStrikeMap = new HashMap<>();
	
	public List<Statistics> statistics;
	public List<StatsType> statsTypes;
	public List<MatchAllData> tournament_matches;
	public List<InfobarStats> infobarStats;
	public List<Ground> Grounds;
	public List<DuckWorthLewis> dls;
	public List<Commentator> Commentators;
	public List<Player> Players;
	public List<Team> teams;
	public List<HeadToHeadPlayer> headToHead;
	public List<Tournament> past_tournament_stats;
	
	@JsonIgnore
	public CricketService cricketService;
	
	public MultiLanguageDatabase multilanguagedata;
	public List<ForeignLanguageData> foreignLanguageDataList;

	@JsonIgnore
	public List<PrintWriter> print_writers; 
	public List<BattingCard> battingCardList = new ArrayList<BattingCard>();
	public BowlingCard bowlingCard,lastBowler = new BowlingCard();
	public List<String> this_data_str = new ArrayList<String>();
	public List<OverByOverData> manhattan = new ArrayList<OverByOverData>();
	public List<Tournament> this_series = new ArrayList<Tournament>();
	public List<BestStats> top_batsman_beststats = new ArrayList<BestStats>();
	public List<BestStats> top_bowler_beststats = new ArrayList<BestStats>();
	
	//public List<ForeignLanguageData> foreignLanguageData,foreignLanguageOmo;
	
	public Player player;
	public Statistics stat;
	public StatsType statsType;
	public InfobarStats infoBarStats;
	public Ground ground;
	public Tournament tournament;
	public Partnership partnership;
	public BattingCard battingCard;
	
	public List<ForeignLanguageData> foreignLanguageData = new ArrayList<ForeignLanguageData>();
	public List<ForeignLanguageData> foreignLanguageOmo = new ArrayList<ForeignLanguageData>();
	
	public InfobarGfx() {
		super();
	}

	public InfobarGfx(Configuration config, String slashOrDash, List<PrintWriter> print_writers, List<Statistics> statistics, List<StatsType> statsTypes, 
			List<InfobarStats> infobarStats, List<Ground> Grounds, List<Commentator> commentators,List<MatchAllData> tournament_matches, List<DuckWorthLewis> dls, 
			List<Player> players, List<HeadToHeadPlayer> headToHead, List<Tournament> past_tournament_stats, CricketService cricketService, List<Team> teams) {
		super();
		this.config = config;
		this.slashOrDash = slashOrDash;
		this.print_writers = print_writers;
		this.statistics = statistics;
		this.statsTypes = statsTypes;
		this.infobarStats = infobarStats;
		this.Grounds = Grounds;
		this.Commentators = commentators;
		this.tournament_matches = tournament_matches;
		this.dls = dls;
		this.Players = players;
		this.headToHead = headToHead;
		this.past_tournament_stats = past_tournament_stats;
		this.cricketService = cricketService;
		this.teams = teams;
		foreignLanguageData.add(new ForeignLanguageData("", "", "", ""));
		foreignLanguageOmo.add(new ForeignLanguageData("0", "2", "3", "1"));		
	}

	public String updateInfobar(List<PrintWriter> print_writers,MatchAllData matchAllData) throws Exception {
		
		inning = matchAllData.getMatch().getInning().stream().filter(inn -> inn.getIsCurrentInning().equalsIgnoreCase(CricketUtil.YES)).findAny().orElse(null);	
		if(inning == null) {
			return "updateInfobar: Inning return is NULL";
		}
		
		switch (config.getBroadcaster()) {
		case Constants.AFG_SL_SERIES:
			if(infobar.isResult_on_screen() == false) {

				populateInfobarTeamNameScore(true,print_writers,matchAllData,1);
				populateCurrentBatsmen(print_writers, matchAllData, 1, 2);
				populateInfobarBowler(1, 1, print_writers, matchAllData);
				populateSection1(print_writers, matchAllData, 1);
				
				if(infobar.getLast_section2() != null && !infobar.getLast_section2().isEmpty()) {
					populateSection2(print_writers, matchAllData, 1);
				}
				
				if(infobar.isInfobar_on_screen()) {
					this_targetData = CricketFunctions.GenerateMatchSummaryStatus(inning.getInningNumber(), matchAllData, 
						CricketUtil.FULL, "|", config.getBroadcaster(), false);
					if(this_targetData.isMatchFinished() == true) {
						
						this.infobar.setResult_on_screen(true);
						this.infobar.setFreeText(Arrays.asList(this_targetData.getTargetOrResult().split("\\|")));
						populateInfoBarResult(print_writers, matchAllData);
						
						if(infobar.getLast_sectionAnalytics() != null && !infobar.getLast_sectionAnalytics().isEmpty()) {
							this_animation.processAnimation(Constants.FRONT, print_writers, "anim_Infobar$Analytics$InOut", "CONTINUE");
							this_animation.processAnimation(Constants.FRONT, print_writers, "Fade_For_Analytics", "CONTINUE");
							TimeUnit.MILLISECONDS.sleep(400);
							this_animation.processAnimation(Constants.FRONT, print_writers, "Infobar_To_Result", "START");
							TimeUnit.MILLISECONDS.sleep(2000);
							this_animation.processAnimation(Constants.FRONT, print_writers, "Normal", "SHOW 0.0");
						}else {
							this_animation.processAnimation(Constants.FRONT, print_writers, "Infobar_To_Result", "START");
							TimeUnit.MILLISECONDS.sleep(2000);
							this_animation.processAnimation(Constants.FRONT, print_writers, "Normal", "SHOW 0.0");
						}
					}
				} 
			}
			break;
		case Constants.ACC:
			if(infobar.isInfobar_on_screen()) {
				if(infobar.getInfobar_status().equalsIgnoreCase("IDENT")) {
					infoIdentSection(print_writers, "", matchAllData, 1, 1);
				}else if(infobar.getInfobar_status().equalsIgnoreCase("INFOBAR")) {
					if(infobar.isResult_on_screen() == false) {
//						String resultTxt = CricketFunctions.GenerateMatchSummaryStatus(inning.getInningNumber(), matchAllData, 
//								CricketUtil.FULL, "|", config.getBroadcaster(), false).toUpperCase();
//						if(resultTxt.contains(CricketUtil.TIED) || resultTxt.contains(" WIN BY ") || resultTxt.contains(" WIN ")) {
						if(CricketFunctions.GenerateMatchSummaryStatus(inning.getInningNumber(), matchAllData, 
							CricketUtil.FULL, "|", config.getBroadcaster(), false).isMatchFinished() == true) {

							populateInfoBarResult(print_writers, matchAllData);
							
							this_animation.processAnimation(Constants.FRONT, print_writers, "Anim_Infobar$Out", "START");
							TimeUnit.MILLISECONDS.sleep(550);
							this_animation.processAnimation(Constants.FRONT, print_writers, "Anim_Infobar$In", "SHOW 0.0");
							this_animation.processAnimation(Constants.FRONT, print_writers, "Anim_Infobar$Section4$In_Out", "SHOW 0.0");
							
							this_animation.processAnimation(Constants.FRONT, print_writers, "Anim_Infobar$IdentOut", "SHOW 0.0");
							this_animation.processAnimation(Constants.FRONT, print_writers, "Anim_Infobar$IdentIn", "START");
							//Animate in result
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Sponsor_BallSpeed$Sponsor$Sponsor" 
									+ "*FUNCTION*Omo*vis_con SET 0\0", print_writers);
							this.infobar.setResult_on_screen(true);
						}else {
							populateInfobarTeamNameScore(false,print_writers,matchAllData,1);
							populatePowerPlay(print_writers, matchAllData);
							populateSection2(print_writers, matchAllData, 1);
							populateCurrentBatsmen(print_writers, matchAllData, 1, 1);
							populateInfobarBowler(1, 1, print_writers, matchAllData);
							//populateSection3(print_writers, matchAllData, 1);
							
							if(infobar.getLast_section5() != null && !infobar.getLast_section5().isEmpty()) {
								populateSection5(print_writers, matchAllData, 1);
							}
							
							if(infobar.getLast_sectionAnalytics() != null && !infobar.getLast_sectionAnalytics().isEmpty()) {
								populateSectionAnalytics(print_writers, matchAllData, 1);
							}
						}
					}
				}
			}
			break;
		case Constants.BAN_AFG_SERIES:
			if(infobar.isInfobar_on_screen()) {
				if(infobar.isResult_on_screen() == false) {
//					String resultTxt = CricketFunctions.GenerateMatchSummaryStatus(inning.getInningNumber(), matchAllData, 
//							CricketUtil.FULL, "|", config.getBroadcaster(), false).toUpperCase();
//					if(resultTxt.contains(CricketUtil.TIED) || resultTxt.contains(" WIN BY ") || resultTxt.contains(" WIN ")) {
					if(CricketFunctions.GenerateMatchSummaryStatus(inning.getInningNumber(), matchAllData, 
						CricketUtil.FULL, "|", config.getBroadcaster(), false).isMatchFinished() == true) {

						populateInfoBarResult(print_writers, matchAllData);
						
						this_animation.processAnimation(Constants.FRONT, print_writers, "Anim_Infobar$Out", "START");
						TimeUnit.MILLISECONDS.sleep(550);
						this_animation.processAnimation(Constants.FRONT, print_writers, "Anim_Infobar$In", "SHOW 0.0");
						this_animation.processAnimation(Constants.FRONT, print_writers, "Anim_Infobar$Section4$In_Out", "SHOW 0.0");
						
						this_animation.processAnimation(Constants.FRONT, print_writers, "Anim_Infobar$IdentIn", "START");
						//Animate in result
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Sponsor_BallSpeed$Sponsor$Sponsor" 
								+ "*FUNCTION*Omo*vis_con SET 0\0", print_writers);
						this.infobar.setResult_on_screen(true);
					}else {
						populateInfobarTeamNameScore(false,print_writers,matchAllData,1);
						populatePowerPlay(print_writers, matchAllData);
						populateSection2(print_writers, matchAllData, 1);
						populateCurrentBatsmen(print_writers, matchAllData, 1, 1);
						populateInfobarBowler(1, 1, print_writers, matchAllData);
						
						if(infobar.getLast_section5() != null && !infobar.getLast_section5().isEmpty()) {
							populateSection5(print_writers, matchAllData, 1);
						}
						if(infobar.getLast_sectionAnalytics() != null && !infobar.getLast_sectionAnalytics().isEmpty()) {
							populateSectionAnalytics(print_writers, matchAllData, 1);
						}
					}
				}
			}
			break;
		case Constants.TRI_SERIES:  case Constants.MT20: case Constants.TG20:
			if(infobar.isResult_on_screen() == false) {

				populateInfobarTeamNameScore(true,print_writers,matchAllData,1);
				populatePowerPlay(print_writers, matchAllData);
				populateCurrentBatsmen(print_writers, matchAllData, 1, 2);
				populateInfobarBowler(1, 1, print_writers, matchAllData);
				populateSection1(print_writers, matchAllData, 1);
				
				if(infobar.getLast_section2() != null && !infobar.getLast_section2().isEmpty()) {
					populateSection2(print_writers, matchAllData, 1);
				}
				if(infobar.getLast_section4() != null && !infobar.getLast_section4().isEmpty()) {
					populateSection4(print_writers, matchAllData, 1);
				}
				if(infobar.getLast_section5() != null && !infobar.getLast_section5().isEmpty()) {
					populateSection5(print_writers, matchAllData, 1);
				}
				if(infobar.getLast_sectionAnalytics() != null && !infobar.getLast_sectionAnalytics().isEmpty()) {
					populateSectionAnalytics(print_writers, matchAllData, 1);
				}
				
				if(infobar.isInfobar_on_screen()) {
//					stats_text = CricketFunctions.GenerateMatchSummaryStatus(inning.getInningNumber(), matchAllData, 
//							CricketUtil.FULL, "|", config.getBroadcaster(), false).toUpperCase();
//					if(stats_text.contains(" " + CricketUtil.BEAT + " ") || stats_text.contains(CricketUtil.TIED) || 
//							stats_text.contains(" WIN BY ") || stats_text.contains(" WIN THE ")) {
					this_targetData = CricketFunctions.GenerateMatchSummaryStatus(inning.getInningNumber(), matchAllData, 
						CricketUtil.FULL, "|", config.getBroadcaster(), false);
					if(this_targetData.isMatchFinished() == true) {
						
						this.infobar.setResult_on_screen(true);
						this.infobar.setFreeText(Arrays.asList(this_targetData.getTargetOrResult().split("\\|")));
						populateInfoBarResult(print_writers, matchAllData);
						
						if(infobar.getLast_sectionAnalytics() != null && !infobar.getLast_sectionAnalytics().isEmpty()) {
							this_animation.processAnimation(Constants.FRONT, print_writers, "anim_Infobar$Analytics$InOut", "CONTINUE");
							this_animation.processAnimation(Constants.FRONT, print_writers, "Fade_For_Analytics", "CONTINUE");
							TimeUnit.MILLISECONDS.sleep(400);
							this_animation.processAnimation(Constants.FRONT, print_writers, "Infobar_To_Result", "START");
							TimeUnit.MILLISECONDS.sleep(2000);
							this_animation.processAnimation(Constants.FRONT, print_writers, "Normal", "SHOW 0.0");
						}else {
							this_animation.processAnimation(Constants.FRONT, print_writers, "Infobar_To_Result", "START");
							TimeUnit.MILLISECONDS.sleep(2000);
							this_animation.processAnimation(Constants.FRONT, print_writers, "Normal", "SHOW 0.0");
						}
					}
				} 
			}
			break;
			
		case Constants.BCCI:
			
			populateInfobarTeamNameScore(true,print_writers,matchAllData,2);
			populateCurrentBatsmen(print_writers, matchAllData, 1, 1);

			populateInfobarBowler(1, 1, print_writers, matchAllData);
			populateVizInfobarRightBottom(print_writers, matchAllData, 1, 1);
			populateVizInfobarLeftBottom(print_writers, matchAllData, 1);
			
			if(infobar.getLast_section3() != null && !infobar.getLast_section3().isEmpty()) {
				populateVizInfobarMiddleSection(print_writers, matchAllData, 1);
			}
			if(infobar.getLast_sectionAnalytics() != null && !infobar.getLast_sectionAnalytics().isEmpty()) {
				populateFullSection(print_writers, matchAllData, 1);
			}
			break;
		}
		return Constants.OK;
	}
	
	@SuppressWarnings("resource")
	public void speed(PrintWriter print_writer, MatchAllData match) throws InterruptedException, IOException {
		
		String text_to_return = "";
		int lineIndex1 = 1;
		boolean found1 = false;
		TimeUnit.MILLISECONDS.sleep(500);
		BufferedReader br = new BufferedReader(new FileReader(CricketUtil.CRICKET_DIRECTORY + "Speed/SPEED.txt"));

		while ((text_to_return = br.readLine()) != null) {
			if (lineIndex1 == 1) {
				addSpeedData(print_writer, text_to_return, "km/h",match);
				TimeUnit.MILLISECONDS.sleep(300);
				found1 = true;
				break;
			}
			lineIndex1++;
		}
		if (!found1) {
			// System.out.println("Line Not There");
		}
		
		switch (config.getBroadcaster()) {
		case Constants.BAN_AFG_SERIES:
			this_animation.processAnimation(Constants.FRONT, print_writers, "BallSpeed", "START");
			break;
		default:
			this_animation.processAnimation(Constants.FRONT, print_writers, "Speed", "START");
			break;
		}
		
		infobar.setLast_speed_value(match.getMatch().getCurrent_speed());
		for (Inning inn : match.getMatch().getInning()) {
			if (inn.getIsCurrentInning().equalsIgnoreCase(CricketUtil.YES)) {
				infobar.setLast_ball_value(String.valueOf(inn.getTotalBalls()));
				infobar.setLast_wide_value(String.valueOf(inn.getTotalWides()));
				infobar.setLast_noball_value(String.valueOf(inn.getTotalNoBalls()));
			}
		}
	}
	public void addSpeedData(PrintWriter print_writer, String speedValue, String speedType,MatchAllData match) {
		switch (config.getBroadcaster()) {
		case Constants.BCCI:
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$NormalAll$OutForSection5$BowlerNameGrp$Speed$SpeedBarAll$Scale"
					+ "$geom_ScaleX*GEOM*width SET " + speedValue + "\0", print_writers);
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$NormalAll$OutForSection5$BowlerNameGrp$Speed$SpeedBarAll$SpeedValue"
					+ "$SpeedValue*GEOM*TEXT SET " + speedValue + "\0", print_writers);
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$NormalAll$OutForSection5$BowlerNameGrp$Speed$SpeedBarAll$SpeedValue"
					+ "$SpeedKph*GEOM*TEXT SET " + speedType + "\0", print_writers);
			break;
		case Constants.BAN_AFG_SERIES:
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Ball_SpeedGrp$Side1$Ball_speed$txt_Data2*GEOM*TEXT SET " 
					+ speedValue + " " + speedType + "\0", print_writers);
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Ball_SpeedGrp$Side2$Ball_speed$txt_Data2*GEOM*TEXT SET " 
					+ speedValue + " " + speedType + "\0", print_writers);
			inning = match.getMatch().getInning().stream().filter(inn -> inn.getIsCurrentInning().equalsIgnoreCase(CricketUtil.YES)).findAny().orElse(null);	
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Ball_SpeedGrp$Side1$Ball_speed$img_Base02*TEXTURE*IMAGE SET "  
					+ Constants.BAN_AFG_SERIES_BASE2 +inning.getBowling_team().getTeamBadge() +"\0", print_writers);
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Ball_SpeedGrp$Side2$Ball_speed$img_Base02*TEXTURE*IMAGE SET "  
					+ Constants.BAN_AFG_SERIES_BASE2 +inning.getBowling_team().getTeamBadge() +"\0", 
					print_writers);
			break;
		case Constants.TG20:
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$RightSection$BowlerSection$BowlerGrp$Speed$txt_SpeedValue"
					+ "*GEOM*TEXT SET " + speedValue + "\0", print_writers);
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$RightSection$BowlerSection$BowlerGrp$Speed$txt_SpeedUnit"
					+ "*GEOM*TEXT SET " + speedType + "\0", print_writers);
			break;
		}
	}
	
	public Infobar updateFieldPlotter(List<PrintWriter> print_writer, MatchAllData match)
			throws InterruptedException, IOException {
		if (infobar.isFieldPlotter_on_screen() == true) {
			String data = new String(Files.readAllBytes(Paths.get("C:\\Sports\\Cricket\\Fielder\\Fielder_Text\\" + 
		            "FieldPlotter.txt")));
	        // Split the content by lines and print each line separately
	        String[] lines = data.split("\n");
	        
	        String plotterData = lines[0].trim();
	        
			FieldersData fielderFormation = new FieldersData();
			fielderFormation = CricketFunctions.getFielderFormation(CricketUtil.CRICKET_DIRECTORY + "Fielder/" + plotterData);

			if (fielderFormation.isCheckbox() == true) {
				populateFieldPlotter(print_writer, match);
			}
		}
		return infobar;
	}
	public String populateFieldPlotter(List<PrintWriter> print_writers, MatchAllData matchAllData) throws InterruptedException, IOException {
		
		String data = new String(Files.readAllBytes(Paths.get("C:\\Sports\\Cricket\\Fielder\\Fielder_Text\\" + 
	            "FieldPlotter.txt")));
        // Split the content by lines and print each line separately
        String[] lines = data.split("\n");
        
        String plotterData = lines[0].trim();
        
		FieldersData fielderFormation = new FieldersData();
		fielderFormation = CricketFunctions
				.getFielderFormation(CricketUtil.CRICKET_DIRECTORY + "Fielder/" + plotterData);
		if (fielderFormation.getStyle().equalsIgnoreCase("RHB")) {
			
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$Main$All$Geom_GroundAll$RotationGrp$SideGrp1$Geom_Side$SelectSide"
					+ "*FUNCTION*Omo*vis_con SET 0 \0", print_writers);
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$Main$All$Geom_GroundAll$RotationGrp$SideGrp1$Geom_Side$SelectSide"
					+ "$Off*GEOM*TEXT SET " + "OFF" + "\0", print_writers);
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$Main$All$Geom_GroundAll$RotationGrp$SideGrp2$Geom_Side$SelectSide"
					+ "*FUNCTION*Omo*vis_con SET 1 \0", print_writers);
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$Main$All$Geom_GroundAll$RotationGrp$SideGrp2$Geom_Side$SelectSide"
					+ "$Leg*GEOM*TEXT SET " + "LEG" + "\0", print_writers);
		} else {
			
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$Main$All$Geom_GroundAll$RotationGrp$SideGrp1$Geom_Side$SelectSide"
					+ "*FUNCTION*Omo*vis_con SET 1 \0", print_writers);
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$Main$All$Geom_GroundAll$RotationGrp$SideGrp1$Geom_Side$SelectSide"
					+ "$Off*GEOM*TEXT SET " + "LEG" + "\0", print_writers);
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$Main$All$Geom_GroundAll$RotationGrp$SideGrp2$Geom_Side$SelectSide"
					+ "*FUNCTION*Omo*vis_con SET 0 \0", print_writers);
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$Main$All$Geom_GroundAll$RotationGrp$SideGrp2$Geom_Side$SelectSide"
					+ "$Leg*GEOM*TEXT SET " + "OFF" + "\0", print_writers);
		}
		for (int i = 0; i <= fielderFormation.getFielders().size() - 1; i++) {
			double ScaleX = 0, ScaleY = 0;
			ScaleX = ((-186) + (341 * ((fielderFormation.getFielders().get(i).getLeftLocation() - 10) / 457.0)));
			ScaleY = ((-186) + (341 * ((fielderFormation.getFielders().get(i).getTopLocation() - 50) / 427.0)))+10;

			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$Main$All$Geom_GroundAll$RotationGrp$Players$PlayerAll" + (i + 1) + 
					"*TRANSFORMATION" + "*POSITION*X SET " + ScaleX + "\0", print_writers);
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$Main$All$Geom_GroundAll$RotationGrp$Players$PlayerAll" + (i + 1) + 
					"*TRANSFORMATION" + "*POSITION*Z SET " + ScaleY + "\0", print_writers);
			
			if (fielderFormation.getFielders().get(i).getFielderhighlight().equalsIgnoreCase("YES")) {
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$Main$All$Geom_GroundAll$RotationGrp$Players$PlayerAll" + (i + 1) + 
						"$PositionY$PositionX$SelectPlayer*FUNCTION*Omo*vis_con SET 1 \0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*STAGE*DIRECTOR*Loop START \0", print_writers);
			} else {
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$Main$All$Geom_GroundAll$RotationGrp$Players$PlayerAll" + (i + 1) + 
						"$PositionY$PositionX$SelectPlayer*FUNCTION*Omo*vis_con SET 0 \0", print_writers);
			}
		}
//		print_writer.println("-1 RENDERER PREVIEW SCENE*" + viz_scene + " C:/Temp/Preview.png Plotter 1.000 \0");
		TimeUnit.MILLISECONDS.sleep(100);
		return Constants.OK;
	}
	
	public String powerplay(List<PrintWriter> print_writers, MatchAllData matchAllData) {
		switch (config.getBroadcaster()) {
		case Constants.BAN_AFG_SERIES: case Constants.ACC:
			if(infobar.isPowerplay_on_screen() == false) {
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*STAGE*DIRECTOR*PowerPlayIn START\0", print_writers);
				infobar.setPowerplay_on_screen(true);
				infobar.setForced_powerplay_out(false);
			}else {
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*STAGE*DIRECTOR*PowerPlayIn CONTINUE REVERSE\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$SB_Small$ScoreGrp$BowlTeam_Over_PPGrp$txt_PowerPlay"
						+ "*GEOM*TEXT SET \0", print_writers);
				infobar.setPowerplay_on_screen(false);
				infobar.setForced_powerplay_out(true);
			}
			break;
		case Constants.TRI_SERIES:  case Constants.MT20: case Constants.TG20:
			if(infobar.isPowerplay_on_screen() == false) {
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*STAGE*DIRECTOR*PowerPlay START\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$ScoreSection$OversAll$PowerPlay$txt_PowerPlay"
						+ "*GEOM*TEXT SET POWERPLAY\0", print_writers);
				infobar.setPowerplay_on_screen(true);
				infobar.setForced_powerplay_out(false);
			}else {
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*STAGE*DIRECTOR*PowerPlay CONTINUE REVERSE\0", print_writers);
				infobar.setPowerplay_on_screen(false);
				infobar.setForced_powerplay_out(true);
			}
			break;
		}
		return Constants.OK;
	}
	
	public void logosAndBaseColor(List<PrintWriter> print_writers, String whatToProcess, MatchAllData matchAllData, int WhichSide) {
		
		String logoPath, base1Path, base2Path,text1Path;
		switch (whatToProcess.split(",")[0]) {
		case "Control_F12":
			switch (config.getBroadcaster()) {
			case Constants.ACC:
				//Logo
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Ident$IdentBadges$HomeBadge$img_Badges*TEXTURE*IMAGE SET " 
						+ Constants.ACC_FLAG + matchAllData.getSetup().getHomeTeam().getTeamBadge() + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Ident$IdentBadges$AwayBadge$img_Badges*TEXTURE*IMAGE SET " 
						+ Constants.ACC_FLAG + matchAllData.getSetup().getAwayTeam().getTeamBadge() + "\0", print_writers);
				break;
			case Constants.BAN_AFG_SERIES:
				//Logo
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Ident$IdentBadges$HomeBadge$img_Badges*TEXTURE*IMAGE SET " 
						+ Constants.BAN_AFG_SERIES_LOGO + matchAllData.getSetup().getHomeTeam().getTeamBadge() + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Ident$IdentBadges$AwayBadge$img_Badges*TEXTURE*IMAGE SET " 
						+ Constants.BAN_AFG_SERIES_LOGO + matchAllData.getSetup().getAwayTeam().getTeamBadge() + "\0", print_writers);
				
				//Color
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Ident$IdentTeamGrp1$img_Base02*TEXTURE*IMAGE SET " 
						+ Constants.BAN_AFG_SERIES_BASE2 + matchAllData.getSetup().getHomeTeam().getTeamBadge() + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Ident$IdentTeamGrp2$img_Base02*TEXTURE*IMAGE SET " 
						+ Constants.BAN_AFG_SERIES_BASE2 + matchAllData.getSetup().getAwayTeam().getTeamBadge() + "\0", print_writers);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Ident$IdentBadges$HomeBadge$LLC_LogoGrp$img_Base2*TEXTURE*IMAGE SET " 
						+ Constants.BAN_AFG_SERIES_BASE2 + matchAllData.getSetup().getHomeTeam().getTeamBadge() + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Ident$IdentBadges$AwayBadge$LLC_LogoGrp$img_Base2*TEXTURE*IMAGE SET " 
						+ Constants.BAN_AFG_SERIES_BASE2 + matchAllData.getSetup().getAwayTeam().getTeamBadge() + "\0", print_writers);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Ident$IdentBadges$HomeBadge$img_Base1*TEXTURE*IMAGE SET " 
						+ Constants.BAN_AFG_SERIES_BASE1 + matchAllData.getSetup().getHomeTeam().getTeamBadge() + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Ident$IdentBadges$HomeBadge$img_Base2*TEXTURE*IMAGE SET " 
						+ Constants.BAN_AFG_SERIES_BASE2 + matchAllData.getSetup().getHomeTeam().getTeamBadge() + "\0", print_writers);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Ident$IdentBadges$AwayBadge$img_Base1*TEXTURE*IMAGE SET " 
						+ Constants.BAN_AFG_SERIES_BASE1 + matchAllData.getSetup().getAwayTeam().getTeamBadge() + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Ident$IdentBadges$AwayBadge$img_Base2*TEXTURE*IMAGE SET " 
						+ Constants.BAN_AFG_SERIES_BASE2 + matchAllData.getSetup().getAwayTeam().getTeamBadge() + "\0", print_writers);
				break;
			
			case Constants.TRI_SERIES:  case Constants.MT20: case Constants.TG20:
				
				if(whatToProcess.split(",")[2].equalsIgnoreCase("SHORT")) {
					omo = 0;
					containerName = "$Ident_ShortName";
				}else if(whatToProcess.split(",")[2].equalsIgnoreCase("FULL")) {
					omo = 1;
					containerName = "$Ident_FullName";
				}
				
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
				
				//Logo
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Ident$Side" + WhichSide + containerName + "$Team1$LogoGrp$Logo$"
						+ "img_Logo*TEXTURE*IMAGE SET " + logoPath + matchAllData.getSetup().getHomeTeam().getTeamBadge() + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Ident$Side" + WhichSide + containerName + "$Team2$LogoGrp$Logo$"
						+ "img_Logo*TEXTURE*IMAGE SET " + logoPath + matchAllData.getSetup().getAwayTeam().getTeamBadge() + "\0", print_writers);
				
				//TeamColor
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Ident$Side" + WhichSide + containerName + "$Team1$LogoGrp$TeamColourAll$"
						+ "img_Base1*TEXTURE*IMAGE SET " + base1Path + matchAllData.getSetup().getHomeTeam().getTeamBadge() + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Ident$Side" + WhichSide + containerName + "$Team1$LogoGrp$TeamColourAll$"
						+ "img_Base2*TEXTURE*IMAGE SET " + base2Path + matchAllData.getSetup().getHomeTeam().getTeamBadge() + "\0", print_writers);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Ident$Side" + WhichSide + containerName + "$Team2$LogoGrp$TeamColourAll$"
						+ "img_Base1*TEXTURE*IMAGE SET " + base1Path + matchAllData.getSetup().getAwayTeam().getTeamBadge() + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Ident$Side" + WhichSide + containerName + "$Team2$LogoGrp$TeamColourAll$"
						+ "img_Base2*TEXTURE*IMAGE SET " + base2Path + matchAllData.getSetup().getAwayTeam().getTeamBadge() + "\0", print_writers);
				
				switch (config.getBroadcaster()) {
				case Constants.TRI_SERIES:  case Constants.MT20:
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Ident$Side" + WhichSide + containerName + "$Team1$TeamNameGrp$BaseAll$"
							+ "img_Base1*TEXTURE*IMAGE SET " + base1Path + matchAllData.getSetup().getHomeTeam().getTeamBadge() + "\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Ident$Side" + WhichSide + containerName + "$Team1$TeamNameGrp$TeamName$"
							+ "img_Text1*TEXTURE*IMAGE SET " + text1Path + matchAllData.getSetup().getHomeTeam().getTeamBadge() + "\0", print_writers);
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Ident$Side" + WhichSide + containerName + "$Team2$TeamNameGrp$BaseAll$"
							+ "img_Base1*TEXTURE*IMAGE SET " + base1Path + matchAllData.getSetup().getAwayTeam().getTeamBadge() + "\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Ident$Side" + WhichSide + containerName + "$Team2$TeamNameGrp$TeamName$"
							+ "img_Text1*TEXTURE*IMAGE SET " + text1Path + matchAllData.getSetup().getAwayTeam().getTeamBadge() + "\0", print_writers);
					break;
				}
				break;

			case Constants.BCCI:
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$TeamLogo$Team1$TeamLogoGrp1$Side" + WhichSide + "$Select_LogoType"
						+ "*FUNCTION*Omo*vis_con SET 0\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$TeamLogo$Team2$TeamLogoGrp$Side" + WhichSide + "$Select_LogoType"
						+ "*FUNCTION*Omo*vis_con SET 0\0", print_writers);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$TeamLogo$Team1$TeamLogoGrp1$Side" + WhichSide + "$TeamLogoStart$Blue$"
						+ "img_BadgeBW1*TEXTURE*IMAGE SET " + Constants.BCCI_BADGE_BW + matchAllData.getSetup().getHomeTeam().getTeamBadge() + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$TeamLogo$Team1$TeamLogoGrp1$Side" + WhichSide + "$TeamLogoStart$Blue$LogoGlossGrp"
						+ "$img_BadgeBW2*TEXTURE*IMAGE SET " + Constants.BCCI_BADGE_BW + matchAllData.getSetup().getHomeTeam().getTeamBadge() + "\0", print_writers);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$TeamLogo$Team1$TeamLogoGrp1$Side" + WhichSide + "$TeamLogoStart$White"
						+ "$img_BadgeBW3*TEXTURE*IMAGE SET " + Constants.BCCI_BADGE_BW + matchAllData.getSetup().getHomeTeam().getTeamBadge() + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$TeamLogo$Team1$TeamLogoGrp1$Side" + WhichSide + "$TeamLogoStart$White"
						+ "$img_BadgeBW1*TEXTURE*IMAGE SET " + Constants.BCCI_BADGE_BW + matchAllData.getSetup().getHomeTeam().getTeamBadge() + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$TeamLogo$Team1$TeamLogoGrp1$Side" + WhichSide + "$TeamLogoStart$White"
						+ "$LogoGlossGrp$img_BadgeBW2*TEXTURE*IMAGE SET " + Constants.BCCI_BADGE_BW + matchAllData.getSetup().getHomeTeam().getTeamBadge() + "\0", print_writers);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$TeamLogo$Team1$TeamColorOut$Side" + WhichSide + "$img_Base1"
						+ "*TEXTURE*IMAGE SET " + Constants.BCCI_BASE1 + matchAllData.getSetup().getHomeTeam().getTeamBadge() + "\0", print_writers);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$TeamLogo$Team2$TeamLogoGrp$Side" + WhichSide + "$TeamLogoStart$Blue$"
						+ "img_BadgeBW1*TEXTURE*IMAGE SET " + Constants.BCCI_BADGE_BW + matchAllData.getSetup().getAwayTeam().getTeamBadge() + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$TeamLogo$Team2$TeamLogoGrp$Side" + WhichSide + "$TeamLogoStart$Blue$LogoGlossGrp"
						+ "$img_BadgeBW2*TEXTURE*IMAGE SET " + Constants.BCCI_BADGE_BW + matchAllData.getSetup().getAwayTeam().getTeamBadge() + "\0", print_writers);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$TeamLogo$Team2$TeamLogoGrp$Side" + WhichSide + "$TeamLogoStart$White"
						+ "$img_BadgeBW3*TEXTURE*IMAGE SET " + Constants.BCCI_BADGE_BW + matchAllData.getSetup().getAwayTeam().getTeamBadge() + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$TeamLogo$Team2$TeamLogoGrp$Side" + WhichSide + "$TeamLogoStart$White"
						+ "$img_BadgeBW1*TEXTURE*IMAGE SET " + Constants.BCCI_BADGE_BW + matchAllData.getSetup().getAwayTeam().getTeamBadge() + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$TeamLogo$Team2$TeamLogoGrp$Side" + WhichSide + "$TeamLogoStart$White"
						+ "$LogoGlossGrp$img_BadgeBW2*TEXTURE*IMAGE SET " + Constants.BCCI_BADGE_BW + matchAllData.getSetup().getAwayTeam().getTeamBadge() + "\0", print_writers);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$TeamLogo$Team2$TeamColorOut$Side" + WhichSide + "$img_Base1"
						+ "*TEXTURE*IMAGE SET " + Constants.BCCI_BASE1 + matchAllData.getSetup().getAwayTeam().getTeamBadge() + "\0", print_writers);
				break;
			}
			break;
		case "F12":
			switch (config.getBroadcaster()) {
			case Constants.ACC:
				//Logo
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$BattingBadge$img_Badges*TEXTURE*IMAGE SET " 
						+ Constants.ACC_FLAG + inning.getBatting_team().getTeamBadge() + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$BowlingBadge$img_Badges*TEXTURE*IMAGE SET " 
						+ Constants.ACC_FLAG + inning.getBowling_team().getTeamBadge() + "\0", print_writers);
				break;
			case Constants.BAN_AFG_SERIES:
				//Logo
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$BattingBadge$img_Badges*TEXTURE*IMAGE SET " 
						+ Constants.BAN_AFG_SERIES_LOGO + inning.getBatting_team().getTeamBadge() + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$BowlingBadge$img_Badges*TEXTURE*IMAGE SET " 
						+ Constants.BAN_AFG_SERIES_LOGO + inning.getBowling_team().getTeamBadge() + "\0", print_writers);
				
				//Color
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$BattingBadge$LLC_LogoGrp$img_Base2*TEXTURE*IMAGE SET " 
						+ Constants.BAN_AFG_SERIES_BASE2 + inning.getBatting_team().getTeamBadge() + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$ScoreGrp$BattingTeamGrp$txt_BattingTeamName*TEXTURE*IMAGE SET " 
						+ Constants.BAN_AFG_SERIES_TEXT + inning.getBatting_team().getTeamBadge() + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$SB_Small$ScoreGrp$BattingTeamGrp$txt_BattingTeamName*TEXTURE*IMAGE SET " 
						+ Constants.BAN_AFG_SERIES_TEXT + inning.getBatting_team().getTeamBadge() + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$ScoreGrp$BowlTeam_Over_PPGrp$img_Text*TEXTURE*IMAGE SET " 
						+ Constants.BAN_AFG_SERIES_TEXT + inning.getBatting_team().getTeamBadge() + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$BatsmanGrp$OnStrikeGrp$img_Base02*TEXTURE*IMAGE SET " 
						+ Constants.BAN_AFG_SERIES_BASE2 + inning.getBatting_team().getTeamBadge() + "\0", print_writers);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$BatsmanGrp$img_Text*TEXTURE*IMAGE SET " 
						+ Constants.BAN_AFG_SERIES_TEXT + inning.getBatting_team().getTeamBadge() + "\0", print_writers);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$BowlingBadge$LLC_LogoGrp$img_Base2*TEXTURE*IMAGE SET " 
						+ Constants.BAN_AFG_SERIES_BASE2 + inning.getBowling_team().getTeamBadge() + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$BowlerGrp$Side1$img_Base02*TEXTURE*IMAGE SET " 
						+ Constants.BAN_AFG_SERIES_BASE2 + inning.getBowling_team().getTeamBadge() + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$BowlerGrp$Side2$img_Base02*TEXTURE*IMAGE SET " 
						+ Constants.BAN_AFG_SERIES_BASE2 + inning.getBowling_team().getTeamBadge() + "\0", print_writers);
				break;
			
			case Constants.TRI_SERIES:  case Constants.MT20: case Constants.TG20:
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
				
				
				//Logo
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$LeftSection$LogoGrp$Logo$img_Logo*TEXTURE*IMAGE SET " 
						+ logoPath + inning.getBatting_team().getTeamBadge() + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$RightSection$LogoGrp$Logo$img_Logo*TEXTURE*IMAGE SET " 
						+ logoPath + inning.getBowling_team().getTeamBadge() + "\0", print_writers);
				
				//TeamColor
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$LeftSection$LogoGrp$TeamColourAll$img_Base1*TEXTURE*IMAGE SET " 
						+ base1Path + inning.getBatting_team().getTeamBadge() + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$LeftSection$LogoGrp$TeamColourAll$img_Base2*TEXTURE*IMAGE SET " 
						+ base2Path + inning.getBatting_team().getTeamBadge() + "\0", print_writers);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$RightSection$LogoGrp$TeamColourAll$img_Base1*TEXTURE*IMAGE SET " 
						+ base1Path + inning.getBowling_team().getTeamBadge() + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$RightSection$LogoGrp$TeamColourAll$img_Base2*TEXTURE*IMAGE SET " 
						+ base2Path + inning.getBowling_team().getTeamBadge() + "\0", print_writers);
				
				switch (config.getBroadcaster()) {
				case Constants.TRI_SERIES:  case Constants.MT20:
					//---------------------------------------
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$RightSection$OutForSection5$BowlerGrp$img_Base1*TEXTURE*IMAGE SET " 
							+ base1Path + inning.getBowling_team().getTeamBadge() + "\0", print_writers);

					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$RightSection$OutForSection5$BowlerGrp$Bowler$Side1$img_Text1*TEXTURE*IMAGE SET " 
							+ text1Path + inning.getBowling_team().getTeamBadge() + "\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$RightSection$OutForSection5$BowlerGrp$Bowler$Side2$img_Text1*TEXTURE*IMAGE SET " 
							+ text1Path + inning.getBowling_team().getTeamBadge() + "\0", print_writers);
					break;
				}
				break;

			case Constants.BCCI:
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$TeamLogo$Team1$TeamLogoGrp1$Side" + WhichSide + "$Select_LogoType"
						+ "*FUNCTION*Omo*vis_con SET 1\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$TeamLogo$Team2$TeamLogoGrp$Side" + WhichSide + "$Select_LogoType"
						+ "*FUNCTION*Omo*vis_con SET 1\0", print_writers);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$TeamLogo$Team1$TeamLogoGrp1$Side" + WhichSide + "$TeamLogoMain$Orange$"
						+ "img_BadgeBW1*TEXTURE*IMAGE SET " + Constants.BCCI_BADGE_BW + inning.getBatting_team().getTeamBadge() + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$TeamLogo$Team1$TeamLogoGrp1$Side" + WhichSide + "$TeamLogoMain$Orange$"
						+ "LogoGlossGrp$img_BadgeBW2*TEXTURE*IMAGE SET " + Constants.BCCI_BADGE_BW + inning.getBatting_team().getTeamBadge() + "\0", print_writers);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$TeamLogo$Team1$TeamLogoGrp1$Side" + WhichSide + "$TeamLogoMain$White"
						+ "$img_BadgeBW3*TEXTURE*IMAGE SET " + Constants.BCCI_BADGE_BW + inning.getBatting_team().getTeamBadge() + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$TeamLogo$Team1$TeamLogoGrp1$Side" + WhichSide + "$TeamLogoMain$White"
						+ "$img_BadgeBW1*TEXTURE*IMAGE SET " + Constants.BCCI_BADGE_BW + inning.getBatting_team().getTeamBadge() + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$TeamLogo$Team1$TeamLogoGrp1$Side" + WhichSide + "$TeamLogoMain$White"
						+ "$LogoGlossGrp$img_BadgeBW2*TEXTURE*IMAGE SET " + Constants.BCCI_BADGE_BW + inning.getBatting_team().getTeamBadge() + "\0", print_writers);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$TeamLogo$Team1$TeamColorOut$Side" + WhichSide + "$img_Base1"
						+ "*TEXTURE*IMAGE SET " + Constants.BCCI_BASE1 + inning.getBatting_team().getTeamBadge() + "\0", print_writers);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$TeamLogo$Team2$TeamLogoGrp$Side" + WhichSide + "$TeamLogoMain$Orange$"
						+ "img_BadgeBW1*TEXTURE*IMAGE SET " + Constants.BCCI_BADGE_BW + inning.getBowling_team().getTeamBadge() + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$TeamLogo$Team2$TeamLogoGrp$Side" + WhichSide + "$TeamLogoMain$Orange$"
						+ "LogoGlossGrp$img_BadgeBW2*TEXTURE*IMAGE SET " + Constants.BCCI_BADGE_BW + inning.getBowling_team().getTeamBadge() + "\0", print_writers);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$TeamLogo$Team2$TeamLogoGrp$Side" + WhichSide + "$TeamLogoMain$White"
						+ "$img_BadgeBW3*TEXTURE*IMAGE SET " + Constants.BCCI_BADGE_BW + inning.getBowling_team().getTeamBadge() + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$TeamLogo$Team2$TeamLogoGrp$Side" + WhichSide + "$TeamLogoMain$White"
						+ "$img_BadgeBW1*TEXTURE*IMAGE SET " + Constants.BCCI_BADGE_BW + inning.getBowling_team().getTeamBadge() + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$TeamLogo$Team2$TeamLogoGrp$Side" + WhichSide + "$TeamLogoMain$White"
						+ "$LogoGlossGrp$img_BadgeBW2*TEXTURE*IMAGE SET " + Constants.BCCI_BADGE_BW + inning.getBowling_team().getTeamBadge() + "\0", print_writers);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$TeamLogo$Team2$TeamColorOut$Side" + WhichSide + "$img_Base1"
						+ "*TEXTURE*IMAGE SET " + Constants.BCCI_BASE1 + inning.getBowling_team().getTeamBadge() + "\0", print_writers);
				break;
			}
			break;
		}
	}
	
	public String populateInfobarIdent(List<PrintWriter> print_writers,String whatToProcess, MatchAllData matchAllData,int WhichSide) {
		
		logosAndBaseColor(print_writers, whatToProcess, matchAllData, WhichSide);
		
		switch (config.getBroadcaster()) {
		case Constants.AFG_SL_SERIES:
			if(whatToProcess.split(",")[2].equalsIgnoreCase("SHORT")) {
				omo = 1;
				infobar.setIdentContainer("$ShortName");
			}else if(whatToProcess.split(",")[2].equalsIgnoreCase("FULL")) {
				omo = 0;
				infobar.setIdentContainer("$FullName");
			}
			
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Ident$Side" + WhichSide + infobar.getIdentContainer() + "$Team1$LogoGrp$img_Logo"
					+ "*TEXTURE*IMAGE SET " + Constants.AFG_SL_SERIES_LOGO + matchAllData.getSetup().getHomeTeam().getTeamBadge() + "\0", print_writers);
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Ident$Side" + WhichSide + infobar.getIdentContainer() + "$Team2$LogoGrp$img_Logo"
					+ "*TEXTURE*IMAGE SET " + Constants.AFG_SL_SERIES_LOGO + matchAllData.getSetup().getAwayTeam().getTeamBadge() + "\0", print_writers);
			
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Ident$Side" + WhichSide + "$select_IdentType*FUNCTION*Omo*vis_con SET " 
					+ omo + "\0", print_writers);
			
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Ident$Side" + WhichSide + infobar.getIdentContainer() + "$Team1$Name$txt_TeamName"
					+ "*GEOM*TEXT SET " + (whatToProcess.split(",")[2].equalsIgnoreCase("SHORT") ? matchAllData.getSetup().getHomeTeam().getTeamName4() : 
						matchAllData.getSetup().getHomeTeam().getTeamName1()) + "\0", print_writers);
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Ident$Side" + WhichSide + infobar.getIdentContainer() + "$Team2$Name$txt_TeamName"
					+ "*GEOM*TEXT SET " + (whatToProcess.split(",")[2].equalsIgnoreCase("SHORT") ? matchAllData.getSetup().getAwayTeam().getTeamName4() : 
						matchAllData.getSetup().getAwayTeam().getTeamName1()) + "\0", print_writers);
			
			infoIdentSection(print_writers, whatToProcess, matchAllData, 1, WhichSide);
			break;
		case Constants.BAN_AFG_SERIES: case Constants.ACC:
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Ident$IdentTeamGrp1$txt_TeamName*GEOM*TEXT SET " 
					+ matchAllData.getSetup().getHomeTeam().getTeamName1() + "\0", print_writers);
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Ident$IdentTeamGrp2$txt_TeamName*GEOM*TEXT SET " 
					+ matchAllData.getSetup().getAwayTeam().getTeamName1() + "\0", print_writers);
			
			infoIdentSection(print_writers, whatToProcess, matchAllData, WhichSide, 1);
			break;
		case Constants.TRI_SERIES:  case Constants.MT20:
			if(whatToProcess.split(",")[2].equalsIgnoreCase("SHORT")) {
				omo = 0;
				containerName = "$Ident_ShortName";
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Ident$Side" + WhichSide + "$Ident_ShortName$DataPart$TopPartPart$"
						+ "select_TextType*FUNCTION*Omo*vis_con SET 0\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Ident$Side" + WhichSide + "$Ident_ShortName$DataPart$TopPartPart$"
						+ "1_Line$txt_Title1*GEOM*TEXT SET " + matchAllData.getSetup().getTournament() + "\0", print_writers);
				
			}else if(whatToProcess.split(",")[2].equalsIgnoreCase("FULL")) {
				omo = 1;
				containerName = "$Ident_FullName";
			}
			
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Ident$Side" + WhichSide + "$select_IdentType*FUNCTION*Omo*vis_con SET " 
					+ omo + "\0", print_writers);
			
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Ident$Side" + WhichSide + containerName + "$Team1$TeamNameGrp$txt_TeamName"
					+ "*GEOM*TEXT SET " + (whatToProcess.split(",")[2].equalsIgnoreCase("SHORT") ? matchAllData.getSetup().getHomeTeam().getTeamName4() : 
						matchAllData.getSetup().getHomeTeam().getTeamName1()) + "\0", print_writers);
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Ident$Side" + WhichSide + containerName + "$Team2$TeamNameGrp$txt_TeamName"
					+ "*GEOM*TEXT SET " + (whatToProcess.split(",")[2].equalsIgnoreCase("SHORT") ? matchAllData.getSetup().getAwayTeam().getTeamName4() : 
						matchAllData.getSetup().getAwayTeam().getTeamName1()) + "\0", print_writers);
			
			if(whatToProcess.split(",")[2].equalsIgnoreCase("FULL")) {
				infoIdentSection(print_writers, whatToProcess, matchAllData, WhichSide, 1);
			}
			break;
		case Constants.TG20:
			if(whatToProcess.split(",")[2].equalsIgnoreCase("SHORT")) {
				omo = 0;
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Ident$Side" + WhichSide + "$Ident_ShortName$Team1$TeamNameGrp$txt_TeamName"
						+ "*GEOM*TEXT SET " + matchAllData.getSetup().getHomeTeam().getTeamName4() + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Ident$Side" + WhichSide + "$Ident_ShortName$Team2$TeamNameGrp$txt_TeamName"
						+ "*GEOM*TEXT SET " + matchAllData.getSetup().getAwayTeam().getTeamName4() + "\0", print_writers);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Ident$Side" + WhichSide + "$Ident_ShortName$DataPart$TopPartPart$"
						+ "select_TextType*FUNCTION*Omo*vis_con SET 0\0", print_writers);
				
				CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Ident$Side" + WhichSide + "$Ident_ShortName$DataPart$TopPartPart$"
						+ "1_Line$select_Language*FUNCTION*Omo*vis_con SET ", config, Constants.TG20, print_writers, foreignLanguageOmo);
				foreignLanguageData = CricketFunctions.AssembleMultiLanguageData(CricketUtil.DICTIONARY, "", multilanguagedata, matchAllData.getSetup().getTournament(), 
						"", null, 0, foreignLanguageDataList);
				CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Ident$Side" + WhichSide + "$Ident_ShortName$DataPart$TopPartPart$"
						+ "1_Line$English$txt_Title1*GEOM*TEXT SET ", config, Constants.TG20, print_writers, foreignLanguageData);
//				
//				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Ident$Side" + WhichSide + "$Ident_ShortName$DataPart$TopPartPart$"
//						+ "1_Line$txt_Title1*GEOM*TEXT SET " + matchAllData.getSetup().getTournament() + "\0", print_writers);
				
			}else if(whatToProcess.split(",")[2].equalsIgnoreCase("FULL")) {
				omo = 1;
				
				
				CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Ident$Side" + WhichSide + "$Ident_FullName$Team1$TeamNameGrp$TeamName$"
						+ "slect_Language*FUNCTION*Omo*vis_con SET ", config, Constants.TG20, print_writers, foreignLanguageOmo);
				foreignLanguageData = CricketFunctions.AssembleMultiLanguageData(CricketUtil.TEAM, "", multilanguagedata, matchAllData.getSetup().getHomeTeam().getTeamName1(), 
						"", null, 0, foreignLanguageDataList);
				CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Ident$Side" + WhichSide + "$Ident_FullName$Team1$TeamNameGrp$English$"
						+ "txt_TeamName*GEOM*TEXT SET ", config, Constants.TG20, print_writers, foreignLanguageData);
				
				CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Ident$Side" + WhichSide + "$Ident_FullName$Team2$TeamNameGrp$TeamName$"
						+ "slect_Language*FUNCTION*Omo*vis_con SET ", config, Constants.TG20, print_writers, foreignLanguageOmo);
				foreignLanguageData = CricketFunctions.AssembleMultiLanguageData(CricketUtil.TEAM, "", multilanguagedata, matchAllData.getSetup().getAwayTeam().getTeamName1(), 
						"", null, 0, foreignLanguageDataList);
				CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Ident$Side" + WhichSide + "$Ident_FullName$Team2$TeamNameGrp$English$"
						+ "txt_TeamName*GEOM*TEXT SET ", config, Constants.TG20, print_writers, foreignLanguageData);
				
//				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Ident$Side" + WhichSide + "$Ident_FullName$Team1$TeamNameGrp$txt_TeamName"
//						+ "*GEOM*TEXT SET " + matchAllData.getSetup().getHomeTeam().getTeamName1() + "\0", print_writers);
//				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Ident$Side" + WhichSide + "$Ident_FullName$Team2$TeamNameGrp$txt_TeamName"
//						+ "*GEOM*TEXT SET " + matchAllData.getSetup().getAwayTeam().getTeamName1() + "\0", print_writers);
			}
			
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Ident$Side" + WhichSide + "$select_IdentType*FUNCTION*Omo*vis_con SET " 
					+ omo + "\0", print_writers);
			
			if(whatToProcess.split(",")[2].equalsIgnoreCase("FULL")) {
				infoIdentSection(print_writers, whatToProcess, matchAllData, WhichSide, 1);
			}
			break;

		case Constants.BCCI:
			foreignLanguageData = new ArrayList<ForeignLanguageData>();
			foreignLanguageOmo = new ArrayList<ForeignLanguageData>();
			foreignLanguageData.add(new ForeignLanguageData("", "", "", ""));
			foreignLanguageOmo.add(new ForeignLanguageData("1", "2", "3", "4"));
			
			CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$IdentAll$TeamName$Team1$select_Language*FUNCTION*Omo*vis_con SET ", 
					config, Constants.BCCI, print_writers, foreignLanguageOmo);
			foreignLanguageData = CricketFunctions.AssembleMultiLanguageData(CricketUtil.TEAM, "", multilanguagedata, matchAllData.getSetup().getHomeTeam().getTeamName1(), 
					"", null, 0, foreignLanguageDataList);
			CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$IdentAll$TeamName$Team1$English$txt_TeamName*GEOM*TEXT SET ", 
					config, Constants.BCCI, print_writers, foreignLanguageData);
			CricketFunctions.DoadWriteSameCommandToEachViz("RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$IdentAll$TeamName$Team1$English$txt_TeamName*ACTIVE SET 1", print_writers, config);
			
			
			CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$IdentAll$TeamName$Team2$select_Language*FUNCTION*Omo*vis_con SET ", 
					config, Constants.BCCI, print_writers, foreignLanguageOmo);
			foreignLanguageData = CricketFunctions.AssembleMultiLanguageData(CricketUtil.TEAM, "", multilanguagedata, matchAllData.getSetup().getAwayTeam().getTeamName1(), 
					"", null, 0, foreignLanguageDataList);
			CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$IdentAll$TeamName$Team2$English$txt_TeamName*GEOM*TEXT SET ", 
					config, Constants.BCCI, print_writers, foreignLanguageData);
			CricketFunctions.DoadWriteSameCommandToEachViz("RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$IdentAll$TeamName$Team2$English$txt_TeamName*ACTIVE SET 1", print_writers, config);
			
			CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$IdentAll$RighInfo$Info1$select_Language*FUNCTION*Omo*vis_con SET ", 
					config, Constants.BCCI, print_writers, foreignLanguageOmo);
			foreignLanguageData = CricketFunctions.AssembleMultiLanguageData(CricketUtil.DICTIONARY, "", multilanguagedata, matchAllData.getSetup().getTournament(), 
					"", null, 0, foreignLanguageDataList);
			CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$IdentAll$RighInfo$Info1$English$txt_Info1*GEOM*TEXT SET ", 
					config, Constants.BCCI, print_writers, foreignLanguageData);
			
			CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$IdentAll$RighInfo$Info2$select_Language*FUNCTION*Omo*vis_con SET ", 
					config, Constants.BCCI, print_writers, foreignLanguageOmo);
			if(!matchAllData.getSetup().getMatchType().equalsIgnoreCase(CricketUtil.TEST) && !matchAllData.getSetup().getMatchType().equalsIgnoreCase(CricketUtil.FC)) {
				foreignLanguageData = CricketFunctions.AssembleMultiLanguageData(CricketUtil.DICTIONARY, "", multilanguagedata, matchAllData.getSetup().getMatchIdent(), 
						"", null, 0, foreignLanguageDataList);
			}else {
				foreignLanguageData = CricketFunctions.AssembleMultiLanguageData(CricketUtil.DICTIONARY, "", multilanguagedata, "DAY " + 
						matchAllData.getMatch().getDaysSessions().get(matchAllData.getMatch().getDaysSessions().size()-1).getDayNumber() + ", SESSION " 
						+ matchAllData.getMatch().getDaysSessions().get(matchAllData.getMatch().getDaysSessions().size()-1).getSessionNumber(), 
						"", null, 0, foreignLanguageDataList);
			}
			CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$IdentAll$RighInfo$Info2$English$txt_Info2*GEOM*TEXT SET ", 
					config, Constants.BCCI, print_writers, foreignLanguageData);
			
			infoIdentSection(print_writers, whatToProcess, matchAllData, WhichSide, 1);
			break;
		}
		return Constants.OK;
	}
	public String infoIdentSection(List<PrintWriter> print_writers,String whatToProcess, MatchAllData matchAllData,int WhichSide, int WhichSubSide) {
		
		switch (config.getBroadcaster()) {
		case Constants.AFG_SL_SERIES:
			switch (infobar.getInfobar_ident_section()) {
			case "SUPEROVER":
				Ident_Line1 = matchAllData.getSetup().getMatchIdent();
				Ident_Line2 = "SUPER OVER";
				break;
			case "TOSS":
				Ident_Line1 = CricketFunctions.generateTossResult(matchAllData, CricketUtil.FULL, CricketUtil.FIELD, CricketUtil.FULL,
						CricketUtil.CHOSE).toUpperCase().split("&")[0];
				Ident_Line2 = CricketFunctions.generateTossResult(matchAllData, CricketUtil.FULL, CricketUtil.FIELD, CricketUtil.FULL,
						CricketUtil.CHOSE).toUpperCase().split("&")[1];
				break;
			case "VENUE":
				Ident_Line1 = "LIVE FROM";
				Ident_Line2 = matchAllData.getSetup().getVenueName();
				break;
			case "TARGET":
				inning = matchAllData.getMatch().getInning().stream().filter(inn -> inn.getIsCurrentInning().equalsIgnoreCase(CricketUtil.YES)).findAny().orElse(null);
				
				Ident_Line1 = "TO WIN - " + inning.getBatting_team().getTeamName1() + " NEED";
				
				if(matchAllData.getSetup().getMatchType().equalsIgnoreCase(CricketUtil.SUPER_OVER) && matchAllData.getSetup().getMaxOvers() == 1) {
					Ident_Line2 = CricketFunctions.GetTargetData(matchAllData).getTargetRuns() + " RUNS OFF " + (matchAllData.getSetup().getMaxOvers()*6) + " BALLS";
				}else {
					Ident_Line2 = CricketFunctions.GetTargetData(matchAllData).getTargetRuns() + " RUNS OFF " + CricketFunctions.GetTargetData(matchAllData).getTargetOvers() 
							+ " OVERS" + (matchAllData.getSetup().getTargetType() != null && !matchAllData.getSetup().getTargetType().isEmpty()?
									" (" + matchAllData.getSetup().getTargetType().toUpperCase() + ")":"");
				}
				break;
			case "EQUATION":
				inning = matchAllData.getMatch().getInning().stream().filter(inn -> inn.getIsCurrentInning().equalsIgnoreCase(CricketUtil.YES)).findAny().orElse(null);
				
				Ident_Line1 = "TO WIN - " + inning.getBatting_team().getTeamName1() + " NEED";
				Ident_Line2 = CricketFunctions.GetTargetData(matchAllData).getRemaningRuns() + " RUN" + CricketFunctions.Plural(CricketFunctions.GetTargetData(matchAllData).
						getRemaningRuns()).toUpperCase() + " OFF " + CricketFunctions.GetTargetData(matchAllData).getRemaningBall() + " BALL" + CricketFunctions.
						Plural(CricketFunctions.GetTargetData(matchAllData).getRemaningBall()).toUpperCase() + (matchAllData.getSetup().getTargetType() != null && 
						!matchAllData.getSetup().getTargetType().isEmpty() ? " (" + matchAllData.getSetup().getTargetType().toUpperCase() + ")" : "");
				
				break;
			case "RESULT":
				Ident_Line1 = CricketFunctions.GenerateMatchSummaryStatus(2, matchAllData, CricketUtil.FULL, "|", Constants.BCCI, false).getTargetOrResult().toUpperCase();
				Ident_Line2 = "";
				break;
			}
			
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Ident$Side" + WhichSide + infobar.getIdentContainer() + "$CenterPart$TopText$"
					+ "Side" + WhichSubSide + "$txt_Info*GEOM*TEXT SET " + Ident_Line1 + "\0", print_writers);
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Ident$Side" + WhichSide + infobar.getIdentContainer() + "$CenterPart$BottomTextText$"
					+ "Side" + WhichSubSide + "$txt_Info*GEOM*TEXT SET " + Ident_Line2 + "\0", print_writers);
			break;
		case Constants.BAN_AFG_SERIES: case Constants.ACC:
			switch (infobar.getInfobar_ident_section()) {
			case "SUPEROVER":
				Ident_Line1 = "SUPER OVER";
				Ident_Line2 = "";
				break;
			case "TOSS":
				Ident_Line1 = CricketFunctions.generateTossResult(matchAllData, CricketUtil.FULL, CricketUtil.FIELD, CricketUtil.FULL,
						CricketUtil.CHOSE).toUpperCase();
				Ident_Line2 = "";
				break;
			case "VENUE":
				Ident_Line1 = "";
				Ident_Line2 = matchAllData.getSetup().getVenueName();
				break;
			case "TARGET":
				inning = matchAllData.getMatch().getInning().stream().filter(inn -> inn.getIsCurrentInning().equalsIgnoreCase(CricketUtil.YES)).findAny().orElse(null);
				
				Ident_Line1 = "TO WIN - " + inning.getBatting_team().getTeamName1() + " NEED ";
				
				if(matchAllData.getSetup().getMatchType().equalsIgnoreCase(CricketUtil.SUPER_OVER) && matchAllData.getSetup().getMaxOvers() == 1) {
					Ident_Line2 = CricketFunctions.GetTargetData(matchAllData).getTargetRuns() + " RUNS OFF " + (matchAllData.getSetup().getMaxOvers()*6) + " BALLS";
				}else {
					Ident_Line2 = CricketFunctions.GetTargetData(matchAllData).getTargetRuns() + " RUNS OFF " + CricketFunctions.GetTargetData(matchAllData).getTargetOvers() 
							+ " OVERS" + (matchAllData.getSetup().getTargetType() != null && !matchAllData.getSetup().getTargetType().isEmpty()?
									" (" + matchAllData.getSetup().getTargetType().toUpperCase() + ")" : "");
				}
				break;
			case "EQUATION":
				inning = matchAllData.getMatch().getInning().stream().filter(inn -> inn.getIsCurrentInning().equalsIgnoreCase(CricketUtil.YES)).findAny().orElse(null);
				
				Ident_Line1 = "TO WIN - " + inning.getBatting_team().getTeamName1() + " NEED ";
				Ident_Line2 = CricketFunctions.GetTargetData(matchAllData).getRemaningRuns() + " RUN" + CricketFunctions.Plural(CricketFunctions.GetTargetData(matchAllData).
						getRemaningRuns()).toUpperCase() + " OFF " + CricketFunctions.GetTargetData(matchAllData).getRemaningBall() + " BALL" + CricketFunctions.
						Plural(CricketFunctions.GetTargetData(matchAllData).getRemaningBall()).toUpperCase() + (matchAllData.getSetup().getTargetType() != null && 
						!matchAllData.getSetup().getTargetType().isEmpty() ? " (" + matchAllData.getSetup().getTargetType().toUpperCase() + ")" : "");
				
				break;
			case "RESULT":
				Ident_Line1 = CricketFunctions.GenerateMatchSummaryStatus(2, matchAllData, CricketUtil.FULL, "|", Constants.BCCI, false).getTargetOrResult().toUpperCase();
				Ident_Line2 = "";
				break;
			}
			
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Ident$IdentInfoGrp$Side" + WhichSide + "$txt_IdentInfo*GEOM*TEXT SET " 
					+ Ident_Line1 + Ident_Line2 + "\0", print_writers);
			break;
		case Constants.TRI_SERIES:  case Constants.MT20:
			switch (infobar.getInfobar_ident_section()) {
			case "SUPEROVER":
				Ident_Line1 = matchAllData.getSetup().getMatchIdent();
				Ident_Line2 = "SUPER OVER";
				break;
			case "TOSS":
				Ident_Line1 = CricketFunctions.generateTossResult(matchAllData, CricketUtil.FULL, CricketUtil.FIELD, CricketUtil.FULL,
						CricketUtil.CHOSE).toUpperCase().split("&")[0];
				Ident_Line2 = CricketFunctions.generateTossResult(matchAllData, CricketUtil.FULL, CricketUtil.FIELD, CricketUtil.FULL,
						CricketUtil.CHOSE).toUpperCase().split("&")[1];
				break;
			case "VENUE":
				Ident_Line1 = "LIVE FROM";
				Ident_Line2 = matchAllData.getSetup().getVenueName();
				break;
			case "TARGET":
				inning = matchAllData.getMatch().getInning().stream().filter(inn -> inn.getIsCurrentInning().equalsIgnoreCase(CricketUtil.YES)).findAny().orElse(null);
				
				Ident_Line1 = "TO WIN - " + inning.getBatting_team().getTeamName1() + " NEED";
				
				if(matchAllData.getSetup().getMatchType().equalsIgnoreCase(CricketUtil.SUPER_OVER) && matchAllData.getSetup().getMaxOvers() == 1) {
					Ident_Line2 = CricketFunctions.GetTargetData(matchAllData).getTargetRuns() + " RUNS OFF " + (matchAllData.getSetup().getMaxOvers()*6) + " BALLS";
				}else {
					Ident_Line2 = CricketFunctions.GetTargetData(matchAllData).getTargetRuns() + " RUNS OFF " + CricketFunctions.GetTargetData(matchAllData).getTargetOvers() 
							+ " OVERS" + (matchAllData.getSetup().getTargetType() != null && !matchAllData.getSetup().getTargetType().isEmpty()?
									" " + matchAllData.getSetup().getTargetType().toUpperCase():"");
				}
				break;
			case "EQUATION":
				inning = matchAllData.getMatch().getInning().stream().filter(inn -> inn.getIsCurrentInning().equalsIgnoreCase(CricketUtil.YES)).findAny().orElse(null);
				
				Ident_Line1 = "TO WIN - " + inning.getBatting_team().getTeamName1() + " NEED";
				Ident_Line2 = CricketFunctions.GetTargetData(matchAllData).getRemaningRuns() + " RUN" + CricketFunctions.Plural(CricketFunctions.GetTargetData(matchAllData).
						getRemaningRuns()).toUpperCase() + " OFF " + CricketFunctions.GetTargetData(matchAllData).getRemaningBall() + " BALL" + CricketFunctions.
						Plural(CricketFunctions.GetTargetData(matchAllData).getRemaningBall()).toUpperCase() + (matchAllData.getSetup().getTargetType() != null && 
						!matchAllData.getSetup().getTargetType().isEmpty() ? " " + matchAllData.getSetup().getTargetType().toUpperCase() : "");
				
				break;
			case "RESULT":
				Ident_Line1 = CricketFunctions.GenerateMatchSummaryStatus(2, matchAllData, CricketUtil.FULL, "|", Constants.BCCI, false).getTargetOrResult().toUpperCase();
				Ident_Line2 = "";
				break;
			}
			
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Ident$Side" + WhichSide + "$Ident_FullName$DataPart$TopPartPart$Side" 
					+ WhichSubSide + "$txt_Title*GEOM*TEXT SET " + Ident_Line1 + "\0", print_writers);
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Ident$Side" + WhichSide + "$Ident_FullName$DataPart$BottomPart$Side" 
					+ WhichSubSide + "$txt_SubTitle*GEOM*TEXT SET " + Ident_Line2 + "\0", print_writers);
			break;
		case Constants.TG20:
			switch (infobar.getInfobar_ident_section()) {
			case "SUPEROVER":
				Ident_Line1 = matchAllData.getSetup().getMatchIdent();
				Ident_Line2 = "SUPER OVER";
				
				CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Ident$Side" + WhichSide + "$Ident_FullName$DataPart$TopPartPart$Side" 
						+ WhichSubSide + "$select_Language*FUNCTION*Omo*vis_con SET ", config, Constants.TG20, print_writers, foreignLanguageOmo);

				foreignLanguageDataList = CricketFunctions.AssembleMultiLanguageData(CricketUtil.DICTIONARY, "", multilanguagedata,Ident_Line1.split(" ")[0].trim(), 
				    "", null, 1,foreignLanguageDataList);
				foreignLanguageDataList = CricketFunctions.AssembleMultiLanguageData(CricketUtil.DICTIONARY, "", multilanguagedata,Ident_Line1.split(" ")[1].trim(), 
				    "", null, 2, foreignLanguageDataList);
				foreignLanguageData.add(CricketFunctions.MergeForeignLanguageDataListToSingleObject(foreignLanguageDataList));
				
				CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Ident$Side" + WhichSide + "$Ident_FullName$DataPart$TopPartPart$Side" 
						+ WhichSubSide + "$English$txt_Title*GEOM*TEXT SET ",config, Constants.TG20, print_writers, foreignLanguageData);
				
				CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Ident$Side" + WhichSide + "$Ident_FullName$DataPart$BottomPart$Side" 
						+ WhichSubSide + "$select_Language*FUNCTION*Omo*vis_con SET ",config, Constants.TG20, print_writers, foreignLanguageOmo);
				foreignLanguageData = CricketFunctions.AssembleMultiLanguageData(CricketUtil.DICTIONARY, "", multilanguagedata,Ident_Line2.trim(),
				    "", null, 0, foreignLanguageDataList);
				CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Ident$Side" + WhichSide + "$Ident_FullName$DataPart$BottomPart$Side" 
						+ WhichSubSide + "$English$txt_SubTitle*GEOM*TEXT SET ", config, Constants.TG20, print_writers, foreignLanguageData);
				break;
			case "TOSS":
				Ident_Line1 = CricketFunctions.generateTossResult(matchAllData, CricketUtil.FULL, CricketUtil.FIELD, CricketUtil.FULL,
						CricketUtil.CHOSE).toUpperCase().split("&")[0];
				Ident_Line2 = CricketFunctions.generateTossResult(matchAllData, CricketUtil.FULL, CricketUtil.FIELD, CricketUtil.FULL,
						CricketUtil.CHOSE).toUpperCase().split("&")[1];
				
				// === IDENT LINE 1 (Top Part) - Merged: "INDIA" + "WON THE TOSS" ===
				CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Ident$Side" + WhichSide + "$Ident_FullName$DataPart$TopPartPart$Side" 
						+ WhichSubSide + "$select_Language*FUNCTION*Omo*vis_con SET ", config, Constants.TG20, print_writers, foreignLanguageOmo);

				// Step 1: Start a new list with index=1 (first item, resets the list)
				foreignLanguageDataList = CricketFunctions.AssembleMultiLanguageData(CricketUtil.TEAM, "", multilanguagedata,Ident_Line1.split("WON")[0].trim(),   // e.g. "INDIA"
				    "", null, 1, // index=1 → resets list, adds at position 0
				    foreignLanguageDataList);

				// Step 2: Add second part at index=2 (appends to list at position 1)
				foreignLanguageDataList = CricketFunctions.AssembleMultiLanguageData(CricketUtil.DICTIONARY, "", multilanguagedata,
				    "WON " + Ident_Line1.split("WON")[1].trim(),  // e.g. "WON THE TOSS"
				    "", null, 2,  // index=2 → adds at position 1
				    foreignLanguageDataList);

				// Step 3: Merge both into a single ForeignLanguageData object
				foreignLanguageData.add(CricketFunctions.MergeForeignLanguageDataListToSingleObject(foreignLanguageDataList));

				// Step 4: Write merged Line1 to viz
				CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Ident$Side" + WhichSide + "$Ident_FullName$DataPart$TopPartPart$Side" 
						+ WhichSubSide + "$English$txt_Title*GEOM*TEXT SET ",config, Constants.TG20, print_writers, foreignLanguageData);

				// === IDENT LINE 2 (Bottom Part) - Single lookup, no merge needed ===

				CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Ident$Side" + WhichSide + "$Ident_FullName$DataPart$BottomPart$Side" 
						+ WhichSubSide + "$select_Language*FUNCTION*Omo*vis_con SET ",config, Constants.TG20, print_writers, foreignLanguageOmo);

				foreignLanguageData = CricketFunctions.AssembleMultiLanguageData(CricketUtil.DICTIONARY, "", multilanguagedata,Ident_Line2.trim(),
				    "", null,0,  // index=0 → single item, no merge needed
				    foreignLanguageDataList);

				CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Ident$Side" + WhichSide + "$Ident_FullName$DataPart$BottomPart$Side" 
						+ WhichSubSide + "$English$txt_SubTitle*GEOM*TEXT SET ", config, Constants.TG20, print_writers, foreignLanguageData);
				break;
			case "VENUE":
				Ident_Line1 = "LIVE FROM";
				Ident_Line2 = matchAllData.getSetup().getVenueName();
				
				CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Ident$Side" + WhichSide + "$Ident_FullName$DataPart$TopPartPart$"
						+ "Side" + WhichSubSide + "$select_Language*FUNCTION*Omo*vis_con SET ", config, Constants.TG20, print_writers, foreignLanguageOmo);
				foreignLanguageData = CricketFunctions.AssembleMultiLanguageData(CricketUtil.DICTIONARY, "", multilanguagedata, Ident_Line1, "", null, 0, foreignLanguageDataList);
				CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Ident$Side" + WhichSide + "$Ident_FullName$DataPart$TopPartPart$"
						+ "Side" + WhichSubSide + "$English$txt_Title*GEOM*TEXT SET ", config, Constants.TG20, print_writers, foreignLanguageData);
				
				CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Ident$Side" + WhichSide + "$Ident_FullName$DataPart$BottomPart$"
						+ "Side" + WhichSubSide + "$select_Language*FUNCTION*Omo*vis_con SET ", config, Constants.TG20, print_writers, foreignLanguageOmo);
				foreignLanguageData = CricketFunctions.AssembleMultiLanguageData(CricketUtil.DICTIONARY, "", multilanguagedata, Ident_Line2, "", null, 0, foreignLanguageDataList);
				CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Ident$Side" + WhichSide + "$Ident_FullName$DataPart$BottomPart$"
						+ "Side" + WhichSubSide + "$English$txt_SubTitle*GEOM*TEXT SET ", config, Constants.TG20, print_writers, foreignLanguageData);
				break;
			case "TARGET":
				inning = matchAllData.getMatch().getInning().stream().filter(inn -> inn.getIsCurrentInning().equalsIgnoreCase(CricketUtil.YES)).findAny().orElse(null);
				
				CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Ident$Side" + WhichSide + "$Ident_FullName$DataPart$TopPartPart$"
						+ "Side" + WhichSubSide + "$select_Language*FUNCTION*Omo*vis_con SET ", config, Constants.TG20, print_writers, foreignLanguageOmo);
				foreignLanguageDataList = CricketFunctions.AssembleMultiLanguageData(CricketUtil.DICTIONARY, "", multilanguagedata,"TO WIN", "", null, 1,foreignLanguageDataList);
				foreignLanguageDataList = CricketFunctions.AssembleMultiLanguageData(CricketUtil.DICTIONARY, "", multilanguagedata," - ", "", null, 2,foreignLanguageDataList);
				foreignLanguageDataList = CricketFunctions.AssembleMultiLanguageData(CricketUtil.TEAM, "", multilanguagedata, inning.getBatting_team().getTeamName1(), "", null, 
						3,foreignLanguageDataList);
				foreignLanguageDataList = CricketFunctions.AssembleMultiLanguageData(CricketUtil.DICTIONARY, "", multilanguagedata,"NEED", "", null, 4,foreignLanguageDataList);
				
				foreignLanguageData.add(CricketFunctions.MergeForeignLanguageDataListToSingleObject(foreignLanguageDataList));
				CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Ident$Side" + WhichSide + "$Ident_FullName$DataPart$TopPartPart$"
						+ "Side" + WhichSubSide + "$English$txt_Title*GEOM*TEXT SET ", config, Constants.TG20, print_writers, foreignLanguageData);
				
				CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Ident$Side" + WhichSide + "$Ident_FullName$DataPart$BottomPart$"
						+ "Side" + WhichSubSide + "$select_Language*FUNCTION*Omo*vis_con SET ", config, Constants.TG20, print_writers, foreignLanguageOmo);
				
				if(matchAllData.getSetup().getMatchType().equalsIgnoreCase(CricketUtil.SUPER_OVER) && matchAllData.getSetup().getMaxOvers() == 1) {
					foreignLanguageDataList = CricketFunctions.AssembleMultiLanguageData(CricketUtil.DICTIONARY, "", multilanguagedata, String.valueOf(CricketFunctions.GetTargetData(matchAllData).
							getTargetRuns()), "", null, 1,foreignLanguageDataList);
					foreignLanguageDataList = CricketFunctions.AssembleMultiLanguageData(CricketUtil.DICTIONARY, "", multilanguagedata, "RUNS", "", null, 2,foreignLanguageDataList);
					foreignLanguageDataList = CricketFunctions.AssembleMultiLanguageData(CricketUtil.DICTIONARY, "", multilanguagedata,"OFF", "", null, 3,foreignLanguageDataList);
					foreignLanguageDataList = CricketFunctions.AssembleMultiLanguageData(CricketUtil.DICTIONARY, "", multilanguagedata, String.valueOf((matchAllData.getSetup().getMaxOvers()*6)), 
							"", null, 4,foreignLanguageDataList);
					foreignLanguageDataList = CricketFunctions.AssembleMultiLanguageData(CricketUtil.DICTIONARY, "", multilanguagedata,"BALLS", "", null, 5,foreignLanguageDataList);
				}else {
					foreignLanguageDataList = CricketFunctions.AssembleMultiLanguageData(CricketUtil.DICTIONARY, "", multilanguagedata, String.valueOf(CricketFunctions.GetTargetData(matchAllData).
							getTargetRuns()), "", null, 1,foreignLanguageDataList);
					foreignLanguageDataList = CricketFunctions.AssembleMultiLanguageData(CricketUtil.DICTIONARY, "", multilanguagedata, "RUNS", "", null, 2,foreignLanguageDataList);
					foreignLanguageDataList = CricketFunctions.AssembleMultiLanguageData(CricketUtil.DICTIONARY, "", multilanguagedata,"OFF", "", null, 3,foreignLanguageDataList);
					foreignLanguageDataList = CricketFunctions.AssembleMultiLanguageData(CricketUtil.DICTIONARY, "", multilanguagedata, String.valueOf(CricketFunctions.GetTargetData(matchAllData).
							getTargetOvers()), "", null, 4,foreignLanguageDataList);
					foreignLanguageDataList = CricketFunctions.AssembleMultiLanguageData(CricketUtil.DICTIONARY, "", multilanguagedata,"OVERS", "", null, 5,foreignLanguageDataList);
					foreignLanguageDataList = CricketFunctions.AssembleMultiLanguageData(CricketUtil.DICTIONARY, "", multilanguagedata, (matchAllData.getSetup().getTargetType() != null && 
							!matchAllData.getSetup().getTargetType().isEmpty() ? matchAllData.getSetup().getTargetType().toUpperCase():""), "", null, 6,foreignLanguageDataList);
				}
				
				foreignLanguageData.add(CricketFunctions.MergeForeignLanguageDataListToSingleObject(foreignLanguageDataList));
				CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Ident$Side" + WhichSide + "$Ident_FullName$DataPart$BottomPart$"
						+ "Side" + WhichSubSide + "$English$txt_SubTitle*GEOM*TEXT SET ", config, Constants.TG20, print_writers, foreignLanguageData);
				break;
			case "EQUATION":
				inning = matchAllData.getMatch().getInning().stream().filter(inn -> inn.getIsCurrentInning().equalsIgnoreCase(CricketUtil.YES)).findAny().orElse(null);
				
				Ident_Line1 = "TO WIN - " + inning.getBatting_team().getTeamName1() + " NEED";
				Ident_Line2 = CricketFunctions.GetTargetData(matchAllData).getRemaningRuns() + " RUN" + CricketFunctions.Plural(CricketFunctions.GetTargetData(matchAllData).
						getRemaningRuns()).toUpperCase() + " OFF " + CricketFunctions.GetTargetData(matchAllData).getRemaningBall() + " BALL" + CricketFunctions.
						Plural(CricketFunctions.GetTargetData(matchAllData).getRemaningBall()).toUpperCase() + (matchAllData.getSetup().getTargetType() != null && 
						!matchAllData.getSetup().getTargetType().isEmpty() ? " " + matchAllData.getSetup().getTargetType().toUpperCase() : "");
				
				break;
			case "RESULT":
				Ident_Line1 = CricketFunctions.GenerateMatchSummaryStatus(2, matchAllData, CricketUtil.FULL, "|", Constants.BCCI, false).getTargetOrResult().toUpperCase();
				Ident_Line2 = "";
				break;
			}
			break;
		case Constants.BCCI:
			CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$IdentAll$BottomInfo$Side" + WhichSide + "$select_Language"
					+ "*FUNCTION*Omo*vis_con SET ", config, Constants.BCCI, print_writers, foreignLanguageOmo);
			
			switch (infobar.getInfobar_ident_section()) {
			case "SUPEROVER":
				foreignLanguageData = CricketFunctions.AssembleMultiLanguageData(CricketUtil.DICTIONARY, "", multilanguagedata, "SUPER OVER", "", null, 0, foreignLanguageDataList);
				break;
			case "TOSS":
				System.out.println(CricketFunctions.generateTossResult(matchAllData, CricketUtil.FULL, CricketUtil.FIELD, CricketUtil.FULL, CricketUtil.CHOSE).toUpperCase());
				foreignLanguageData = CricketFunctions.AssembleMultiLanguageData(CricketUtil.DICTIONARY, "", multilanguagedata, CricketFunctions.generateTossResult(matchAllData, 
						CricketUtil.FULL, CricketUtil.FIELD, CricketUtil.FULL, CricketUtil.CHOSE).toUpperCase(), "", null, 0, foreignLanguageDataList);
				break;
			case "VENUE":
				foreignLanguageData = CricketFunctions.AssembleMultiLanguageData(CricketUtil.DICTIONARY, "", multilanguagedata, "LIVE FROM " + 
						matchAllData.getSetup().getVenueName(), "", null, 0, foreignLanguageDataList);
				break;
			case "RESULT":
				foreignLanguageData = CricketFunctions.AssembleMultiLanguageData(CricketUtil.DICTIONARY, "", multilanguagedata, CricketFunctions.GenerateMatchSummaryStatus(
						inning.getInningNumber(), matchAllData, CricketUtil.FULL, "", Constants.BCCI, false).getTargetOrResult().toUpperCase(), "", null, 0, foreignLanguageDataList);
				break;
			}
			
			CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$IdentAll$BottomInfo$Side" + WhichSide + "$English$txt_Info"
					+ "*GEOM*TEXT SET ", config, Constants.BCCI, print_writers, foreignLanguageData);
			break;
		}
		return Constants.OK;
	}
	
	public String populateInfoBarResult(List<PrintWriter> print_writers, MatchAllData matchAllData) {
		switch (config.getBroadcaster()) {
		case Constants.AFG_SL_SERIES:
			//Logo
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Result$Team1$LogoGrp$Logo$img_Logo*TEXTURE*IMAGE SET " 
					+ (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_LOGO : Constants.MT20_LOGO)+ matchAllData.getSetup().getHomeTeam().getTeamBadge() + "\0", print_writers);
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Result$Team2$LogoGrp$Logo$img_Logo*TEXTURE*IMAGE SET " 
					+ (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_LOGO : Constants.MT20_LOGO)+ matchAllData.getSetup().getAwayTeam().getTeamBadge() + "\0", print_writers);
			
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Result$CenterPart$TopText$txt_Info*GEOM*TEXT SET " 
					+ matchAllData.getSetup().getMatchIdent() + "\0", print_writers);
			
			if(!CricketFunctions.GenerateMatchSummaryStatus(2, matchAllData, CricketUtil.FULL, "|", config.getBroadcaster(), true).getTargetOrResult().toUpperCase().split("\\|")[0].contains(" TIED")) {
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Result$CenterPart$BottomTextText$txt_Info*GEOM*TEXT SET " 
						+ CricketFunctions.GenerateMatchSummaryStatus(2, matchAllData, CricketUtil.FULL, "|", config.getBroadcaster(), false).getTargetOrResult().toUpperCase() + "\0", print_writers);
			}else {
				if(matchAllData.getSetup().getMatchType().equalsIgnoreCase(CricketUtil.SUPER_OVER)) {
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Result$CenterPart$BottomTextText$txt_Info*GEOM*TEXT SET " 
						+ "SUPER OVER TIED - ANOTHER SUPER OVER TO FOLLOW" + "\0", print_writers);
				}else {
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Result$CenterPart$BottomTextText$txt_Info*GEOM*TEXT SET " 
						+ "MATCH TIED - WINNER WILL BE DECIDED BY SUPER OVER" + "\0", print_writers);
				}
			}
			break;
		case Constants.ACC:
			//NAME
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Ident$IdentTeamGrp1$txt_TeamName*GEOM*TEXT SET " 
					+ matchAllData.getSetup().getHomeTeam().getTeamName1() + "\0", print_writers);
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Ident$IdentTeamGrp2$txt_TeamName*GEOM*TEXT SET " 
					+ matchAllData.getSetup().getAwayTeam().getTeamName1() + "\0", print_writers);
			
			//Logo
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Ident$IdentBadges$HomeBadge$img_Badges*TEXTURE*IMAGE SET " 
					+ Constants.ACC_FLAG + matchAllData.getSetup().getHomeTeam().getTeamBadge() + "\0", print_writers);
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Ident$IdentBadges$AwayBadge$img_Badges*TEXTURE*IMAGE SET " 
					+ Constants.ACC_FLAG + matchAllData.getSetup().getAwayTeam().getTeamBadge() + "\0", print_writers);
			
			if(!CricketFunctions.GenerateMatchSummaryStatus(2, matchAllData, CricketUtil.FULL, "|", config.getBroadcaster(), true).getTargetOrResult().toUpperCase().split("\\|")[0].contains(" TIED")) {
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Ident$IdentInfoGrp$txt_IdentInfo*GEOM*TEXT SET " 
						+ CricketFunctions.GenerateMatchSummaryStatus(2, matchAllData, CricketUtil.FULL, "|", config.getBroadcaster(), false).getTargetOrResult().toUpperCase() + "\0", print_writers);
			}else {
				if(matchAllData.getSetup().getMatchType().equalsIgnoreCase(CricketUtil.SUPER_OVER)) {
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Ident$IdentInfoGrp$txt_IdentInfo*GEOM*TEXT SET " 
							+ "SUPER OVER TIED - ANOTHER SUPER OVER TO FOLLOW" + "\0", print_writers);
				}else {
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Ident$IdentInfoGrp$txt_IdentInfo*GEOM*TEXT SET " 
							+ "MATCH TIED - WINNER WILL BE DECIDED BY SUPER OVER" + "\0", print_writers);
				}
			}
			break;
		case Constants.BAN_AFG_SERIES:
			//NAME
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Ident$IdentTeamGrp1$txt_TeamName*GEOM*TEXT SET " 
					+ matchAllData.getSetup().getHomeTeam().getTeamName1() + "\0", print_writers);
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Ident$IdentTeamGrp2$txt_TeamName*GEOM*TEXT SET " 
					+ matchAllData.getSetup().getAwayTeam().getTeamName1() + "\0", print_writers);
			
			//Logo
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Ident$IdentBadges$HomeBadge$img_Badges*TEXTURE*IMAGE SET " 
					+ Constants.BAN_AFG_SERIES_LOGO + matchAllData.getSetup().getHomeTeam().getTeamBadge() + "\0", print_writers);
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Ident$IdentBadges$AwayBadge$img_Badges*TEXTURE*IMAGE SET " 
					+ Constants.BAN_AFG_SERIES_LOGO + matchAllData.getSetup().getAwayTeam().getTeamBadge() + "\0", print_writers);
			
			//Color
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Ident$IdentTeamGrp1$img_Base02*TEXTURE*IMAGE SET " 
					+ Constants.BAN_AFG_SERIES_BASE2 + matchAllData.getSetup().getHomeTeam().getTeamBadge() + "\0", print_writers);
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Ident$IdentTeamGrp2$img_Base02*TEXTURE*IMAGE SET " 
					+ Constants.BAN_AFG_SERIES_BASE2 + matchAllData.getSetup().getAwayTeam().getTeamBadge() + "\0", print_writers);
			
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Ident$IdentBadges$HomeBadge$LLC_LogoGrp$img_Base2*TEXTURE*IMAGE SET " 
					+ Constants.BAN_AFG_SERIES_BASE2 + matchAllData.getSetup().getHomeTeam().getTeamBadge() + "\0", print_writers);
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Ident$IdentBadges$AwayBadge$LLC_LogoGrp$img_Base2*TEXTURE*IMAGE SET " 
					+ Constants.BAN_AFG_SERIES_BASE2 + matchAllData.getSetup().getAwayTeam().getTeamBadge() + "\0", print_writers);
			
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Ident$IdentBadges$HomeBadge$img_Base1*TEXTURE*IMAGE SET " 
					+ Constants.BAN_AFG_SERIES_BASE1 + matchAllData.getSetup().getHomeTeam().getTeamBadge() + "\0", print_writers);
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Ident$IdentBadges$HomeBadge$img_Base2*TEXTURE*IMAGE SET " 
					+ Constants.BAN_AFG_SERIES_BASE2 + matchAllData.getSetup().getHomeTeam().getTeamBadge() + "\0", print_writers);
			
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Ident$IdentBadges$AwayBadge$img_Base1*TEXTURE*IMAGE SET " 
					+ Constants.BAN_AFG_SERIES_BASE1 + matchAllData.getSetup().getAwayTeam().getTeamBadge() + "\0", print_writers);
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Ident$IdentBadges$AwayBadge$img_Base2*TEXTURE*IMAGE SET " 
					+ Constants.BAN_AFG_SERIES_BASE2 + matchAllData.getSetup().getAwayTeam().getTeamBadge() + "\0", print_writers);
			
			if(!CricketFunctions.GenerateMatchSummaryStatus(2, matchAllData, CricketUtil.FULL, "|", config.getBroadcaster(), true).getTargetOrResult().toUpperCase().split("\\|")[0].contains(" TIED")) {
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Ident$IdentInfoGrp$txt_IdentInfo*GEOM*TEXT SET " 
						+ CricketFunctions.GenerateMatchSummaryStatus(2, matchAllData, CricketUtil.FULL, "|", config.getBroadcaster(), false).getTargetOrResult().toUpperCase() + "\0", print_writers);
			}else {
				if(matchAllData.getSetup().getMatchType().equalsIgnoreCase(CricketUtil.SUPER_OVER)) {
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Ident$IdentInfoGrp$txt_IdentInfo*GEOM*TEXT SET " 
							+ "SUPER OVER TIED - ANOTHER SUPER OVER TO FOLLOW" + "\0", print_writers);
				}else {
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Ident$IdentInfoGrp$txt_IdentInfo*GEOM*TEXT SET " 
							+ "MATCH TIED - WINNER WILL BE DECIDED BY SUPER OVER" + "\0", print_writers);
				}
			}
			break;
		
		case Constants.TRI_SERIES:  case Constants.MT20:
			//Logo
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Result$Team1$LogoGrp$Logo$img_Logo*TEXTURE*IMAGE SET " 
					+ (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_LOGO : Constants.MT20_LOGO)+ matchAllData.getSetup().getHomeTeam().getTeamBadge() + "\0", print_writers);
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Result$Team2$LogoGrp$Logo$img_Logo*TEXTURE*IMAGE SET " 
					+ (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_LOGO : Constants.MT20_LOGO)+ matchAllData.getSetup().getAwayTeam().getTeamBadge() + "\0", print_writers);
			
			//TeamColor
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Result$Team1$LogoGrp$TeamColourAll$img_Base1*TEXTURE*IMAGE SET " 
					+ (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_BASE1 : Constants.MT20_BASE1) + matchAllData.getSetup().getHomeTeam().getTeamBadge() + "\0", print_writers);
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Result$Team1$LogoGrp$TeamColourAll$img_Base2*TEXTURE*IMAGE SET " 
					+ (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_BASE2 : Constants.MT20_BASE2) + matchAllData.getSetup().getHomeTeam().getTeamBadge() + "\0", print_writers);
			
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Result$Team2$LogoGrp$TeamColourAll$img_Base1*TEXTURE*IMAGE SET " 
					+ (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_BASE1 : Constants.MT20_BASE1) + matchAllData.getSetup().getAwayTeam().getTeamBadge() + "\0", print_writers);
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Result$Team2$LogoGrp$TeamColourAll$img_Base2*TEXTURE*IMAGE SET " 
					+ (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_BASE2 : Constants.MT20_BASE2) + matchAllData.getSetup().getAwayTeam().getTeamBadge() + "\0", print_writers);
			
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Result$DataPart$TopPartPart$txt_Title*GEOM*TEXT SET " 
					+ matchAllData.getSetup().getMatchIdent() + "\0", print_writers);
			
			if(!CricketFunctions.GenerateMatchSummaryStatus(2, matchAllData, CricketUtil.FULL, "|", config.getBroadcaster(), true).getTargetOrResult().toUpperCase().split("\\|")[0].contains(" TIED")) {
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Result$DataPart$BottomPart$txt_SubTitle*GEOM*TEXT SET " 
						+ CricketFunctions.GenerateMatchSummaryStatus(2, matchAllData, CricketUtil.FULL, "|", config.getBroadcaster(), false).getTargetOrResult().toUpperCase() + "\0", print_writers);
			}else {
				if(matchAllData.getSetup().getMatchType().equalsIgnoreCase(CricketUtil.SUPER_OVER)) {
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Result$DataPart$BottomPart$txt_SubTitle*GEOM*TEXT SET " 
						+ "SUPER OVER TIED - ANOTHER SUPER OVER TO FOLLOW" + "\0", print_writers);
				}else {
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Result$DataPart$BottomPart$txt_SubTitle*GEOM*TEXT SET " 
						+ "MATCH TIED - WINNER WILL BE DECIDED BY SUPER OVER" + "\0", print_writers);
				}
			}
			break;
		case Constants.TG20:
			//Logo
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Result$Team1$LogoGrp$Logo$img_Logo*TEXTURE*IMAGE SET " 
					+ Constants.TG20_LOGO + matchAllData.getSetup().getHomeTeam().getTeamBadge() + "\0", print_writers);
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Result$Team2$LogoGrp$Logo$img_Logo*TEXTURE*IMAGE SET " 
					+ Constants.TG20_LOGO + matchAllData.getSetup().getAwayTeam().getTeamBadge() + "\0", print_writers);
			
			//TeamColor
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Result$Team1$LogoGrp$TeamColourAll$img_Base1*TEXTURE*IMAGE SET " 
					+ Constants.TG20_BASE1 + matchAllData.getSetup().getHomeTeam().getTeamBadge() + "\0", print_writers);
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Result$Team1$LogoGrp$TeamColourAll$img_Base2*TEXTURE*IMAGE SET " 
					+ Constants.TG20_BASE2 + matchAllData.getSetup().getHomeTeam().getTeamBadge() + "\0", print_writers);
			
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Result$Team2$LogoGrp$TeamColourAll$img_Base1*TEXTURE*IMAGE SET " 
					+ Constants.TG20_BASE1 + matchAllData.getSetup().getAwayTeam().getTeamBadge() + "\0", print_writers);
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Result$Team2$LogoGrp$TeamColourAll$img_Base2*TEXTURE*IMAGE SET " 
					+ Constants.TG20_BASE2 + matchAllData.getSetup().getAwayTeam().getTeamBadge() + "\0", print_writers);
			
			CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Result$DataPart$TopPartPart$Text$Title$select_Language"
					+ "*FUNCTION*Omo*vis_con SET ", config, Constants.TG20, print_writers, foreignLanguageOmo);

			foreignLanguageDataList = CricketFunctions.AssembleMultiLanguageData(CricketUtil.DICTIONARY, "", multilanguagedata,matchAllData.getSetup().getMatchIdent().split(" ")[0].trim(), 
			    "", null, 1,foreignLanguageDataList);
			foreignLanguageDataList = CricketFunctions.AssembleMultiLanguageData(CricketUtil.DICTIONARY, "", multilanguagedata,matchAllData.getSetup().getMatchIdent().split(" ")[1].trim(), 
			    "", null, 2, foreignLanguageDataList);
			foreignLanguageData.add(CricketFunctions.MergeForeignLanguageDataListToSingleObject(foreignLanguageDataList));

			CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Result$DataPart$TopPartPart$Text$Title$English$txt_Title"
					+ "*GEOM*TEXT SET ",config, Constants.TG20, print_writers, foreignLanguageData);
			
			if(!CricketFunctions.GenerateMatchSummaryStatus(2, matchAllData, CricketUtil.FULL, "|", config.getBroadcaster(), true).getTargetOrResult().toUpperCase().split("\\|")[0].contains(" TIED")) {
				CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Result$DataPart$BottomPart$Text$SubTitle$select_Language"
						+ "*FUNCTION*Omo*vis_con SET ", config, Constants.TG20, print_writers, foreignLanguageOmo);
				foreignLanguageData = CricketFunctions.AssembleMultiLanguageData(CricketUtil.DICTIONARY, "", multilanguagedata, CricketFunctions.GenerateMatchSummaryStatus(2, matchAllData, 
						CricketUtil.FULL, "|", config.getBroadcaster(), false).getTargetOrResult().toUpperCase(), "", null, 0, foreignLanguageDataList);
				CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Result$DataPart$BottomPart$Text$SubTitle$English$txt_SubTitle"
						+ "*GEOM*TEXT SET ",config, Constants.TG20, print_writers, foreignLanguageData);
			}else {
				if(matchAllData.getSetup().getMatchType().equalsIgnoreCase(CricketUtil.SUPER_OVER)) {
					CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Result$DataPart$BottomPart$Text$SubTitle$select_Language"
							+ "*FUNCTION*Omo*vis_con SET ", config, Constants.TG20, print_writers, foreignLanguageOmo);
					foreignLanguageData = CricketFunctions.AssembleMultiLanguageData(CricketUtil.DICTIONARY, "", multilanguagedata, "SUPER OVER TIED - ANOTHER SUPER OVER TO FOLLOW", 
							"", null, 0, foreignLanguageDataList);
					CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Result$DataPart$BottomPart$Text$SubTitle$English$txt_SubTitle"
							+ "*GEOM*TEXT SET ",config, Constants.TG20, print_writers, foreignLanguageData);
				}else {
					CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Result$DataPart$BottomPart$Text$SubTitle$select_Language"
							+ "*FUNCTION*Omo*vis_con SET ", config, Constants.TG20, print_writers, foreignLanguageOmo);
					foreignLanguageData = CricketFunctions.AssembleMultiLanguageData(CricketUtil.DICTIONARY, "", multilanguagedata, "MATCH TIED - WINNER WILL BE DECIDED BY SUPER OVER", 
							"", null, 0, foreignLanguageDataList);
					CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Result$DataPart$BottomPart$Text$SubTitle$English$txt_SubTitle"
							+ "*GEOM*TEXT SET ",config, Constants.TG20, print_writers, foreignLanguageData);
				}
			}
		}
		return Constants.OK;
	}
	
	public String populateInfobar(List<PrintWriter> print_writers,String whatToProcess, MatchAllData matchAllData) throws InterruptedException, IOException {
		
		inning = matchAllData.getMatch().getInning().stream().filter(inn -> inn.getIsCurrentInning().equalsIgnoreCase(CricketUtil.YES)).findAny().orElse(null);
		if(inning == null) {
			return "populateInfobar: Inning return is NULL";
		}
		
		switch (config.getBroadcaster()) {
		case Constants.AFG_SL_SERIES:
			status = populateInfobarTeamNameScore(false,print_writers,matchAllData,1);
			if(status == Constants.OK) {
				this.infobar.setSection1(whatToProcess.split(",")[2]);
				this.infobar.setSection3(whatToProcess.split(",")[3]);
				
				populateCurrentBatsmen(print_writers, matchAllData, 1, 1);
				populateInfobarBowler(1, 1, print_writers, matchAllData);
				
				populateSection1(print_writers, matchAllData, 1);
			}
			break;
		case Constants.BAN_AFG_SERIES:
			
			infobar.setLast_batsmen(new ArrayList<BattingCard>());
			lastBatsmanOnStrike = 0;

			logosAndBaseColor(print_writers, "F12", matchAllData, 1);
			status = populateInfobarTeamNameScore(false,print_writers,matchAllData,1);
			if(status == Constants.OK) {
				this.infobar.setSection2(whatToProcess.split(",")[2]);
				
				populatePowerPlay(print_writers, matchAllData);
				populateSection2(print_writers, matchAllData, 1);
				populateCurrentBatsmen(print_writers, matchAllData, 1, 1);
				populateInfobarBowler(1, 1, print_writers, matchAllData);
			}
			this.infobar.setResult_on_screen(false);
//			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Sponsor_BallSpeed$Sponsor$Sponsor" 
//					+ "*FUNCTION*Omo*vis_con SET 1\0", print_writers);
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Sponsor_BallSpeed$Sponsor$Sponsor" 
					+ "*FUNCTION*Omo*vis_con SET 0\0", print_writers);
			break;
		case Constants.ACC:
			
			infobar.setLast_batsmen(new ArrayList<BattingCard>());
			lastBatsmanOnStrike = 0;

			logosAndBaseColor(print_writers, "F12", matchAllData, 1);
			status = populateInfobarTeamNameScore(false,print_writers,matchAllData,1);
			if(status == Constants.OK) {
				this.infobar.setSection2(whatToProcess.split(",")[2]);
				this.infobar.setSection3(whatToProcess.split(",")[3]);
				
				populatePowerPlay(print_writers, matchAllData);
				populateSection2(print_writers, matchAllData, 1);
				populateCurrentBatsmen(print_writers, matchAllData, 1, 1);
				populateInfobarBowler(1, 1, print_writers, matchAllData);
			}
			this.infobar.setResult_on_screen(false);
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Sponsor_BallSpeed$Sponsor$Sponsor" 
					+ "*FUNCTION*Omo*vis_con SET 1\0", print_writers);
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$SB_Small$ScoreGrp$BowlTeam_Over_PPGrp$txt_PowerPlay"
					+ "*GEOM*TEXT SET \0", print_writers);
			break;	
		case Constants.TRI_SERIES:  case Constants.MT20: case Constants.TG20:
			
			switch (config.getBroadcaster()) {
			case Constants.TRI_SERIES:
				setDataPathInScript(print_writers, CricketUtil.TEAM);
				setDataPathInScript(print_writers, CricketUtil.BATSMAN);
				setDataPathInScript(print_writers, CricketUtil.BOWLER);
				break;
			}
			
			previous_runs = inning.getTotalRuns();
			previous_wickets = inning.getTotalWickets();
			previous_overs = inning.getTotalOvers();
			previous_balls = next_ball;
			
			status = populateInfobarTeamNameScore(false,print_writers,matchAllData,1);
			if(status == Constants.OK) {
				this.infobar.setSection1(whatToProcess.split(",")[2]);
				this.infobar.setSection3(whatToProcess.split(",")[3]);
				
				populatePowerPlay(print_writers, matchAllData);
				
				bowlerOnScreen = true;
				batterOnScreen = true;
				bowlingCard = inning.getBowlingCard().stream().filter(boc -> boc.getStatus().toUpperCase().equalsIgnoreCase(CricketUtil.CURRENT+CricketUtil.BOWLER)
					|| boc.getStatus().toUpperCase().equalsIgnoreCase(CricketUtil.LAST+CricketUtil.BOWLER)).findAny().orElse(null);
				if(bowlingCard != null) {
					bowlerPreviousRuns = bowlingCard.getRuns();
				    bowlerPreviousWickets = bowlingCard.getWickets();
				    bowlerPreviousOvers = bowlingCard.getOvers();
				    bowlerPreviousBalls   = bowlingCard.getBalls();
					populateBowlersData(print_writers, 1, 1, "");
					populateSection3(print_writers, matchAllData, 1);
				}
				populatePowerPlay(print_writers, matchAllData);
				populateCurrentBatsmen(print_writers, matchAllData, 1, 1);
				populateSection1(print_writers, matchAllData, 1);
			} else {
				return status;
			}
			break;
			
		case Constants.BCCI:
			
			foreignLanguageData = new ArrayList<ForeignLanguageData>();
			foreignLanguageOmo = new ArrayList<ForeignLanguageData>();
			foreignLanguageData.add(new ForeignLanguageData("", "", "", ""));
			foreignLanguageOmo.add(new ForeignLanguageData("1", "2", "3", "4"));
			
			status = populateInfobarTeamNameScore(false,print_writers,matchAllData,1);
			if(status == Constants.OK) {
				if(status == Constants.OK) {
					this.infobar.setSection1(whatToProcess.split(",")[2]);
					this.infobar.setSection4("OVER");
					
					populateCurrentBatsmen(print_writers, matchAllData, 1, 1);
					
					populateInfobarBowler(1,1,print_writers, matchAllData);
					populateVizInfobarLeftBottom(print_writers, matchAllData, 1);
					
				} else {
					return status;
				}
			}else {
				return status;
			}
			break;
		}
		return Constants.OK;
	}
		
	public void setDataPathInScript(List<PrintWriter> print_writers,String whichData) throws InterruptedException {
		switch (whichData) {
		case CricketUtil.TEAM:
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$ScoreSection$ScoreGrpAll*SCRIPT*INSTANCE*unitsSide1Path SET " 
					+ "Units$Side1$txt_Figure" + "\0", print_writers);
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$ScoreSection$ScoreGrpAll*SCRIPT*INSTANCE*unitsSide2Path SET " 
					+ "Units$Side2$txt_Figure" + "\0", print_writers);
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$ScoreSection$ScoreGrpAll*SCRIPT*INSTANCE*tensSide1Path SET " 
					+ "Tenths$Side1$txt_Figure" + "\0", print_writers);
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$ScoreSection$ScoreGrpAll*SCRIPT*INSTANCE*tensSide2Path SET " 
					+ "Tenths$Side2$txt_Figure" + "\0", print_writers);
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$ScoreSection$ScoreGrpAll*SCRIPT*INSTANCE*hundredsSide1Path SET " 
					+ "Hundreths$Side1$txt_Figure" + "\0", print_writers);
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$ScoreSection$ScoreGrpAll*SCRIPT*INSTANCE*hundredsSide2Path SET " 
					+ "Hundreths$Side2$txt_Figure" + "\0", print_writers);
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$ScoreSection$ScoreGrpAll*SCRIPT*INSTANCE*unitsDirectorPath SET " 
					+ "Score_Counter$Units" + "\0", print_writers);
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$ScoreSection$ScoreGrpAll*SCRIPT*INSTANCE*tensDirectorPath SET " 
					+ "Score_Counter$Tenths" + "\0", print_writers);
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$ScoreSection$ScoreGrpAll*SCRIPT*INSTANCE*hundredsDirectorPath SET " 
					+ "Score_Counter$Hundredths" + "\0", print_writers);
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$ScoreSection$ScoreGrpAll*SCRIPT*INSTANCE*wicketsSide1Path SET " 
					+ "Wicket$Side1$txt_Figure" + "\0", print_writers);
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$ScoreSection$ScoreGrpAll*SCRIPT*INSTANCE*wicketsSide2Path SET " 
					+ "Wicket$Side2$txt_Figure" + "\0", print_writers);
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$ScoreSection$ScoreGrpAll*SCRIPT*INSTANCE*wicketsDirectorPath SET " 
					+ "Score_Counter$Wickets" + "\0", print_writers);
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$ScoreSection$ScoreGrpAll*SCRIPT*INSTANCE*hyphenDirectorPath SET " 
					+ "Score_Counter$Hyphen" + "\0", print_writers);
			
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$ScoreSection$OversAll*SCRIPT*INSTANCE*unitsSide1Path SET " 
					+ "OversAll$OversValue$Units$Side1$txt_Overs" + "\0", print_writers);
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$ScoreSection$OversAll*SCRIPT*INSTANCE*unitsSide2Path SET " 
					+ "OversAll$OversValue$Units$Side2$txt_Overs" + "\0", print_writers);
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$ScoreSection$OversAll*SCRIPT*INSTANCE*tensSide1Path SET " 
					+ "OversAll$OversValue$Tenths$Side1$txt_Overs" + "\0", print_writers);
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$ScoreSection$OversAll*SCRIPT*INSTANCE*tensSide2Path SET " 
					+ "OversAll$OversValue$Tenths$Side2$txt_Overs" + "\0", print_writers);
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$ScoreSection$OversAll*SCRIPT*INSTANCE*hundredsSide1Path SET " 
					+ "OversAll$OversValue$Hundreths$Side1$txt_Overs" + "\0", print_writers);
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$ScoreSection$OversAll*SCRIPT*INSTANCE*hundredsSide2Path SET " 
					+ "OversAll$OversValue$Hundreths$Side2$txt_Overs" + "\0", print_writers);
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$ScoreSection$OversAll*SCRIPT*INSTANCE*unitsDirectorPath SET " 
					+ "Overs_Counter$Units" + "\0", print_writers);
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$ScoreSection$OversAll*SCRIPT*INSTANCE*tensDirectorPath SET " 
					+ "Overs_Counter$Tenths" + "\0", print_writers);
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$ScoreSection$OversAll*SCRIPT*INSTANCE*hundredsDirectorPath SET " 
					+ "Overs_Counter$Hundredths" + "\0", print_writers);
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$ScoreSection$OversAll*SCRIPT*INSTANCE*ballsSide1Path SET " 
					+ "OversAll$OversValue$Decimal$Side1$txt_Overs" + "\0", print_writers);
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$ScoreSection$OversAll*SCRIPT*INSTANCE*ballsSide2Path SET " 
					+ "OversAll$OversValue$Decimal$Side2$txt_Overs" + "\0", print_writers);
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$ScoreSection$OversAll*SCRIPT*INSTANCE*ballsDirectorPath SET " 
					+ "Overs_Counter$Decimal" + "\0", print_writers);
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$ScoreSection$OversAll*SCRIPT*INSTANCE*dotPath SET " 
					+ "OversAll$OversValue$Dot" + "\0", print_writers);
			break;
		case CricketUtil.BATSMAN:
			for (int WhichBatsman = 1; WhichBatsman <= 2; WhichBatsman++) {
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$LeftSection$BatsmanGrp_" + WhichBatsman + "$Side1" 
						+ "$ScoreGrp$Runs*SCRIPT*INSTANCE*UnitsSide1Path SET BatsmanGrp_" + WhichBatsman + "$Runs$Units$Side1$txt_Runs" + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$LeftSection$BatsmanGrp_" + WhichBatsman + "$Side1" 
						+ "$ScoreGrp$Runs*SCRIPT*INSTANCE*UnitsSide2Path SET BatsmanGrp_" + WhichBatsman + "$Runs$Units$Side2$txt_Runs" + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$LeftSection$BatsmanGrp_" + WhichBatsman + "$Side1"
						+ "$ScoreGrp$Runs*SCRIPT*INSTANCE*TensSide1Path SET BatsmanGrp_" + WhichBatsman + "$Runs$Tenths$Side1$txt_Runs" + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$LeftSection$BatsmanGrp_" + WhichBatsman + "$Side1" 
						+ "$ScoreGrp$Runs*SCRIPT*INSTANCE*TensSide2Path SET BatsmanGrp_" + WhichBatsman + "$Runs$Tenths$Side2$txt_Runs" + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$LeftSection$BatsmanGrp_" + WhichBatsman + "$Side1"
						+ "$ScoreGrp$Runs*SCRIPT*INSTANCE*HundredsSide1Path SET BatsmanGrp_" + WhichBatsman + "$Runs$Hundreths$Side1$txt_Runs" + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$LeftSection$BatsmanGrp_" + WhichBatsman + "$Side1" 
						+ "$ScoreGrp$Runs*SCRIPT*INSTANCE*HundredsSide2Path SET BatsmanGrp_" + WhichBatsman + "$Runs$Hundreths$Side2$txt_Runs" + "\0", print_writers);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$LeftSection$BatsmanGrp_" + WhichBatsman + "$Side1" 
						+ "$ScoreGrp$Runs*SCRIPT*INSTANCE*UnitsDirectorPath SET BatsmanCounter$" + WhichBatsman + "$Runs$Units" + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$LeftSection$BatsmanGrp_" + WhichBatsman + "$Side1"
						+ "$ScoreGrp$Runs*SCRIPT*INSTANCE*TensDirectorPath SET BatsmanCounter$" + WhichBatsman + "$Runs$Tenths" + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$LeftSection$BatsmanGrp_" + WhichBatsman + "$Side1" 
						+ "$ScoreGrp$Runs*SCRIPT*INSTANCE*HundredsDirectorPath SET BatsmanCounter$" + WhichBatsman + "$Runs$Hundreths" + "\0", print_writers);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$LeftSection$BatsmanGrp_" + WhichBatsman + "$Side1" 
						+ "$ScoreGrp$Balls*SCRIPT*INSTANCE*UnitsSide1Path SET BatsmanGrp_" + WhichBatsman + "$Balls$Units$Side1$txt_Balls" + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$LeftSection$BatsmanGrp_" + WhichBatsman + "$Side1"
						+ "$ScoreGrp$Balls*SCRIPT*INSTANCE*UnitsSide2Path SET BatsmanGrp_" + WhichBatsman + "$Balls$Units$Side2$txt_Balls" + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$LeftSection$BatsmanGrp_" + WhichBatsman + "$Side1" 
						+ "$ScoreGrp$Balls*SCRIPT*INSTANCE*TensSide1Path SET BatsmanGrp_" + WhichBatsman + "$Balls$Tenths$Side1$txt_Balls" + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$LeftSection$BatsmanGrp_" + WhichBatsman + "$Side1"
						+ "$ScoreGrp$Balls*SCRIPT*INSTANCE*TensSide2Path SET BatsmanGrp_" + WhichBatsman + "$Balls$Tenths$Side2$txt_Balls" + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$LeftSection$BatsmanGrp_" + WhichBatsman + "$Side1"
						+ "$ScoreGrp$Balls*SCRIPT*INSTANCE*HundredsSide1Path SET BatsmanGrp_" + WhichBatsman + "$Balls$Hundreths$Side1$txt_Balls" + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$LeftSection$BatsmanGrp_" + WhichBatsman + "$Side1"
						+ "$ScoreGrp$Balls*SCRIPT*INSTANCE*HundredsSide2Path SET BatsmanGrp_" + WhichBatsman + "$Balls$Hundreths$Side2$txt_Balls" + "\0", print_writers);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$LeftSection$BatsmanGrp_" + WhichBatsman + "$Side1"
						+ "$ScoreGrp$Balls*SCRIPT*INSTANCE*UnitsDirectorPath SET BatsmanCounter$" + WhichBatsman + "$Balls$Units" + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$LeftSection$BatsmanGrp_" + WhichBatsman + "$Side1"
						+ "$ScoreGrp$Balls*SCRIPT*INSTANCE*TensDirectorPath SET BatsmanCounter$" + WhichBatsman + "$Balls$Tenths" + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$LeftSection$BatsmanGrp_" + WhichBatsman + "$Side1" 
						+ "$ScoreGrp$Balls*SCRIPT*INSTANCE*HundredsDirectorPath SET BatsmanCounter$" + WhichBatsman + "$Balls$Hundreths" + "\0", print_writers);
			}
			break;
		case CricketUtil.BOWLER:
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$RightSection$BowlerGrp$Side1$ScoreGrp$Figures"
					+ "*SCRIPT*INSTANCE*unitsSide1Path SET Figures$Runs$Units$Side1$txt_Runs\0", print_writers);
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$RightSection$BowlerGrp$Side1$ScoreGrp$Figures"
					+ "*SCRIPT*INSTANCE*unitsSide2Path SET Figures$Runs$Units$Side2$txt_Runs\0", print_writers);
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$RightSection$BowlerGrp$Side1$ScoreGrp$Figures"
					+ "*SCRIPT*INSTANCE*tensSide1Path SET Figures$Runs$Tenths$Side1$txt_Runs\0", print_writers);
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$RightSection$BowlerGrp$Side1$ScoreGrp$Figures"
					+ "*SCRIPT*INSTANCE*tensSide2Path SET Figures$Runs$Tenths$Side2$txt_Runs\0", print_writers);
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$RightSection$BowlerGrp$Side1$ScoreGrp$Figures"
					+ "*SCRIPT*INSTANCE*hundredsSide1Path SET Figures$Runs$Hundreths$Side1$txt_Runs\0", print_writers);
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$RightSection$BowlerGrp$Side1$ScoreGrp$Figures"
					+ "*SCRIPT*INSTANCE*hundredsSide2Path SET Figures$Runs$Hundreths$Side2$txt_Runs\0", print_writers);
			
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$RightSection$BowlerGrp$Side1$ScoreGrp$Figures"
					+ "*SCRIPT*INSTANCE*unitsDirectorPath SET BowlerCounter$Runs$Units\0", print_writers);
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$RightSection$BowlerGrp$Side1$ScoreGrp$Figures"
					+ "*SCRIPT*INSTANCE*tensDirectorPath SET BowlerCounter$Runs$Tenths\0", print_writers);
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$RightSection$BowlerGrp$Side1$ScoreGrp$Figures"
					+ "*SCRIPT*INSTANCE*hundredsDirectorPath SET BowlerCounter$Runs$Hundreths\0", print_writers);
			
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$RightSection$BowlerGrp$Side1$ScoreGrp$Figures"
					+ "*SCRIPT*INSTANCE*wicketsUnitsSide1Path SET Figures$Wickets$Units$Side1$txt_Wickets\0", print_writers);
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$RightSection$BowlerGrp$Side1$ScoreGrp$Figures"
					+ "*SCRIPT*INSTANCE*wicketsUnitsSide2Path SET Figures$Wickets$Units$Side2$txt_Wickets\0", print_writers);
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$RightSection$BowlerGrp$Side1$ScoreGrp$Figures"
					+ "*SCRIPT*INSTANCE*wicketsTensSide1Path SET Figures$Wickets$Tenths$Side1$txt_Wickets\0", print_writers);
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$RightSection$BowlerGrp$Side1$ScoreGrp$Figures"
					+ "*SCRIPT*INSTANCE*wicketsTensSide2Path SET Figures$Wickets$Tenths$Side2$txt_Wickets\0", print_writers);
			
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$RightSection$BowlerGrp$Side1$ScoreGrp$Figures"
					+ "*SCRIPT*INSTANCE*wicketsUnitsDirectorPath SET BowlerCounter$Wickets$Units\0", print_writers);
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$RightSection$BowlerGrp$Side1$ScoreGrp$Figures"
					+ "*SCRIPT*INSTANCE*wicketsTensDirectorPath SET BowlerCounter$Wickets$Tenths\0", print_writers);
			
			
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$RightSection$BowlerGrp$Side1$ScoreGrp$Overs"
					+ "*SCRIPT*INSTANCE*unitsSide1Path SET Bowler$Overs$Units$Side1$txt_Overs\0", print_writers);
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$RightSection$BowlerGrp$Side1$ScoreGrp$Overs"
					+ "*SCRIPT*INSTANCE*unitsSide2Path SET Bowler$Overs$Units$Side2$txt_Overs\0", print_writers);
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$RightSection$BowlerGrp$Side1$ScoreGrp$Overs"
					+ "*SCRIPT*INSTANCE*tensSide1Path SET Bowler$Overs$Tenths$Side1$txt_Overs\0", print_writers);
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$RightSection$BowlerGrp$Side1$ScoreGrp$Overs"
					+ "*SCRIPT*INSTANCE*tensSide2Path SET Bowler$Overs$Tenths$Side2$txt_Overs\0", print_writers);
			
//			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$RightSection$BowlerGrp$Side1$ScoreGrp$Overs"
//					+ "*SCRIPT*INSTANCE*hundredsSide1Path SET \0", print_writers);
//			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$RightSection$BowlerGrp$Side1$ScoreGrp$Overs"
//					+ "*SCRIPT*INSTANCE*hundredsSide2Path SET \0", print_writers);
			
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$RightSection$BowlerGrp$Side1$ScoreGrp$Overs"
					+ "*SCRIPT*INSTANCE*unitsDirectorPath SET BowlerCounter$Overs$Units\0", print_writers);
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$RightSection$BowlerGrp$Side1$ScoreGrp$Overs"
					+ "*SCRIPT*INSTANCE*tensDirectorPath SET BowlerCounter$Overs$Tenths\0", print_writers);
//			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$RightSection$BowlerGrp$Side1$ScoreGrp$Overs"
//					+ "*SCRIPT*INSTANCE*hundredsDirectorPath SET \0", print_writers);
			
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$RightSection$BowlerGrp$Side1$ScoreGrp$Overs"
					+ "*SCRIPT*INSTANCE*ballsSide1Path SET Bowler$Overs$Decimal$Side1$txt_Overs\0", print_writers);
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$RightSection$BowlerGrp$Side1$ScoreGrp$Overs"
					+ "*SCRIPT*INSTANCE*ballsSide2Path SET Bowler$Overs$Decimal$Side2$txt_Overs\0", print_writers);
			
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$RightSection$BowlerGrp$Side1$ScoreGrp$Overs"
					+ "*SCRIPT*INSTANCE*ballsDirectorPath SET BowlerCounter$Overs$Decimal\0", print_writers);
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$RightSection$BowlerGrp$Side1$ScoreGrp$Overs"
					+ "*SCRIPT*INSTANCE*dotPath SET Bowler$Overs$Dot\0", print_writers);
			break;
		}
	}
	public void setPosition(List<PrintWriter> print_writers, int whichSide, String whatToProcess) 
	{
		String xPositionTxt = "";
		
		switch(config.getBroadcaster()) {
		case Constants.TRI_SERIES:  case Constants.MT20:
			
			switch(whatToProcess) {
			case CricketUtil.TEAM:
				
				if(whichSide == 1) {
					
					if(previous_wickets >= 10) {
						if(previous_runs > 99) {
							xPositionTxt = "12.0 0.0 0.0";
						}else if(previous_runs > 9) {
							xPositionTxt = "6.0 0.0 0.0";
						}else {
							xPositionTxt = "0.0 0.0 0.0";
						}
					}else {
						if(previous_runs > 99) {
							xPositionTxt = "0.0 0.0 0.0";
						}else if(previous_runs > 9) {
							xPositionTxt = "-6.0 0.0 0.0";
						}else {
							xPositionTxt = "-12.0 0.0 0.0";
						}
					}
					
				} else {

					if(inning.getTotalWickets() >= 10) {
						if(inning.getTotalRuns() > 99) {
							xPositionTxt = "12.0 0.0 0.0";
						}else if(inning.getTotalRuns() > 9) {
							xPositionTxt = "6.0 0.0 0.0";
						}else {
							xPositionTxt = "0.0 0.0 0.0";
						}
					}else {
						if(inning.getTotalRuns() > 99) {
							xPositionTxt = "0.0 0.0 0.0";
						}else if(inning.getTotalRuns() > 9) {
							xPositionTxt = "-6.0 0.0 0.0";
						}else {
							xPositionTxt = "-12.0 0.0 0.0";
						}
					}
				}
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$ScoreSection$MoveForDigits"
					+ "*ANIMATION*KEY*$MD" + whichSide + "*VALUE SET " + xPositionTxt + "\0",print_writers);
				
				break;
				
			case CricketUtil.OVER:

				if(whichSide == 1) {
					
					if(previous_balls > 0) {
						if(previous_overs > 99) {
							xPositionTxt = "0.0 0.0 0.0";
						}else if(previous_overs > 9) {
							xPositionTxt = "-3.0 0.0 0.0";
						}else {
							xPositionTxt = "-6.0 0.0 0.0";
						}
					}else {
						if(previous_overs > 99) {
							xPositionTxt = "12.0 0.0 0.0";
						}else if(previous_overs > 9) {
							xPositionTxt = "6.0 0.0 0.0";
						}else {
							xPositionTxt = "0.0 0.0 0.0";
						}
					}					
					
				} else {

					if(inning.getTotalBalls() > 0) {
						if(inning.getTotalOvers() > 99) {
							xPositionTxt = "0.0 0.0 0.0";
						}else if(inning.getTotalOvers() > 9) {
							xPositionTxt = "-3.0 0.0 0.0";
						}else {
							xPositionTxt = "-6.0 0.0 0.0";
						}
					}else {
						if(inning.getTotalOvers() > 99) {
							xPositionTxt = "12.0 0.0 0.0";
						}else if(inning.getTotalOvers() > 9) {
							xPositionTxt = "6.0 0.0 0.0";
						}else {
							xPositionTxt = "0.0 0.0 0.0";
						}
					}					
				}
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$ScoreSection$OversAll$MoveForDigits"
					+ "*ANIMATION*KEY*$OMD" + whichSide + "*VALUE SET " + xPositionTxt + "\0",print_writers);
				
				break;
			}
			break;
		}	
	}
	
	public String populatePowerPlay(List<PrintWriter> print_writers, MatchAllData matchAllData) 
	{
		switch(config.getBroadcaster()) {
		case Constants.MT20: case Constants.TRI_SERIES: case Constants.TG20:
			
			if(matchAllData.getSetup().getMatchType().equalsIgnoreCase(CricketUtil.SUPER_OVER)) {
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$ScoreSection$OversAll$PowerPlay*ACTIVE SET 0\0", print_writers);
				if (infobar.isPowerplay_on_screen() == true) {
				} else {
					if (infobar.isPowerplay_on_screen() == false) {
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*STAGE*DIRECTOR*PowerPlay START\0", print_writers);
						infobar.setPowerplay_on_screen(true);
					}
				}
			}else {
				if (!CricketFunctions.processPowerPlay(CricketUtil.MINI, matchAllData).isEmpty()) {
					if (infobar.isPowerplay_on_screen() == true) {
					} else {
						if (infobar.isPowerplay_on_screen() == false) {
							switch (matchAllData.getSetup().getMatchType()) {
							case CricketUtil.ODI: case CricketUtil.OD:
								CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$ScoreSection$OversAll$PowerPlay$txt_PowerPlay"
										+ "*GEOM*TEXT SET " + CricketFunctions.processPowerPlay(CricketUtil.MINI, matchAllData) + "\0", print_writers);
								break;
							default:
								CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$ScoreSection$OversAll$PowerPlay$txt_PowerPlay"
										+ "*GEOM*TEXT SET POWERPLAY\0", print_writers);
								break;
							}
							
							if(infobar.isForced_powerplay_out() == false) {
								if (infobar.isPowerplay_on_screen() == true) {
								} else {
									if (infobar.isPowerplay_on_screen() == false) {
										CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*STAGE*DIRECTOR*PowerPlay START\0", print_writers);
										infobar.setPowerplay_on_screen(true);
									}
								}
							}
//							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*STAGE*DIRECTOR*PowerPlayIn START\0", print_writers);
//							infobar.setPowerplay_on_screen(true);
						}
					}
				}else {
					if (infobar.isPowerplay_on_screen() == true) {
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*STAGE*DIRECTOR*PowerPlay CONTINUE REVERSE\0", print_writers);
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$ScoreSection$OversAll$PowerPlay$txt_PowerPlay"
								+ "*GEOM*TEXT SET POWERPLAY\0", print_writers);
						infobar.setPowerplay_on_screen(false);
					}else {
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$ScoreSection$OversAll$PowerPlay$txt_PowerPlay"
								+ "*GEOM*TEXT SET \0", print_writers);
					}
				}
			}
			break;
		default:
			if(matchAllData.getSetup().getMatchType().equalsIgnoreCase(CricketUtil.SUPER_OVER)) {
//				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*STAGE*DIRECTOR*PowerPlay SHOW 0.0\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$ScoreGrp$BowlTeam_Over_PPGrp$txt_PowerPlay"
						+ "*GEOM*TEXT SET S\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$SB_Small$ScoreGrp$BowlTeam_Over_PPGrp$txt_PowerPlay"
						+ "*GEOM*TEXT SET \0", print_writers);
				
				if (infobar.isPowerplay_on_screen() == true) {
				} else {
					if (infobar.isPowerplay_on_screen() == false) {
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*STAGE*DIRECTOR*Anim_Infobar$PowerPlayIn START\0", print_writers);
						infobar.setPowerplay_on_screen(true);
					}
				}
			}else {
				if (!CricketFunctions.processPowerPlay(CricketUtil.MINI, matchAllData).isEmpty()) {
					if (infobar.isPowerplay_on_screen() == true) {
					} else {
						if (infobar.isPowerplay_on_screen() == false) {
							switch (matchAllData.getSetup().getMatchType()) {
							case CricketUtil.ODI: case CricketUtil.OD:
								CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$ScoreGrp$BowlTeam_Over_PPGrp$txt_PowerPlay"
										+ "*GEOM*TEXT SET " + CricketFunctions.processPowerPlay(CricketUtil.MINI, matchAllData) + "\0", print_writers);
								CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$SB_Small$ScoreGrp$BowlTeam_Over_PPGrp$txt_PowerPlay"
										+ "*GEOM*TEXT SET " + "" + "\0", print_writers);
								break;
							default:
								CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$ScoreGrp$BowlTeam_Over_PPGrp$txt_PowerPlay"
										+ "*GEOM*TEXT SET P\0", print_writers);
								CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$SB_Small$ScoreGrp$BowlTeam_Over_PPGrp$txt_PowerPlay"
										+ "*GEOM*TEXT SET \0", print_writers);
								break;
							}
							
							if(infobar.isForced_powerplay_out() == false) {
								if (infobar.isPowerplay_on_screen() == true) {
								} else {
									if (infobar.isPowerplay_on_screen() == false) {
										CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*STAGE*DIRECTOR*Anim_Infobar$PowerPlayIn START\0", print_writers);
										infobar.setPowerplay_on_screen(true);
									}
								}
							}
//							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*STAGE*DIRECTOR*PowerPlayIn START\0", print_writers);
//							infobar.setPowerplay_on_screen(true);
						}
					}
				}else {
					if (infobar.isPowerplay_on_screen() == true) {
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*STAGE*DIRECTOR*Anim_Infobar$PowerPlayIn CONTINUE REVERSE\0", print_writers);
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$SB_Small$ScoreGrp$BowlTeam_Over_PPGrp$txt_PowerPlay"
								+ "*GEOM*TEXT SET \0", print_writers);
						infobar.setPowerplay_on_screen(false);
					}else {
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$SB_Small$ScoreGrp$BowlTeam_Over_PPGrp$txt_PowerPlay"
								+ "*GEOM*TEXT SET \0", print_writers);
					}
				}
			}
			break;
		}
		
		return Constants.OK;
	}
	
	public String populateInfobarTeamNameScore(boolean is_this_updating,List<PrintWriter> print_writers,MatchAllData matchAllData, int whichSide) throws InterruptedException {
		
		String filePath = "ReduceOver.txt",line1 = "", line2 = "", line3 = "";
		inning = matchAllData.getMatch().getInning().stream().filter(inn -> inn.getIsCurrentInning().equalsIgnoreCase(CricketUtil.YES)).findAny().orElse(null);
		if(inning == null) {
			return "populateInfobarTeamNameScore: Inning return is NULL";
		}
		
		switch(config.getBroadcaster()) {
		case Constants.AFG_SL_SERIES:
			if(is_this_updating == false) {
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$CenterPart$TopText$txt_TeamName1*GEOM*TEXT SET " 
						+ inning.getBatting_team().getTeamName4() + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$CenterPart$TopText$txt_Team2*GEOM*TEXT SET " 
						+ inning.getBowling_team().getTeamName4() + "\0", print_writers);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$BatttingGrp$LogoGrp$img_Logo"
						+ "*TEXTURE*IMAGE SET " + Constants.AFG_SL_SERIES_LOGO + inning.getBatting_team().getTeamBadge() + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$BowlingGrp$LogoGrp$img_Logo"
						+ "*TEXTURE*IMAGE SET " + Constants.AFG_SL_SERIES_LOGO + inning.getBowling_team().getTeamBadge() + "\0", print_writers);
			}
			
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$CenterPart$TopText$Score$txt_Score*GEOM*TEXT SET " 
					+ CricketFunctions.getTeamScore(inning, "-", false) + "\0", print_writers);
			
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$CenterPart$TopText$OversGrp$txt_Overs*GEOM*TEXT SET " 
					+ CricketFunctions.OverBalls(inning.getTotalOvers(), inning.getTotalBalls()) + "\0", print_writers);
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$CenterPart$TopText$OversGrp$txt_OversHead*GEOM*TEXT SET " 
					+ (inning.getTotalOvers() == 1 && inning.getTotalBalls() == 0 ? "OVER" : "OVERS") + "\0", print_writers);
			break;
		case Constants.ACC:
			if(is_this_updating == false) {
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$ScoreGrp$BattingTeamGrp$txt_BattingTeamName*GEOM*TEXT SET " 
						+ inning.getBatting_team().getTeamName4() + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$SB_Small$ScoreGrp$BattingTeamGrp$txt_BattingTeamName*GEOM*TEXT SET " 
						+ inning.getBatting_team().getTeamName4() + "\0", print_writers);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$ScoreGrp$BowlTeam_Over_PPGrp$txt_BowlingTeamName*ACTIVE SET 0 \0", print_writers);

				File file = new File(filePath);

		        if (!file.exists()) {
		        	line1 = "NO";
		        }else {
		        	try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
			            line1 = br.readLine();
			            if (line1 != null && line1.equalsIgnoreCase("YES")) {
			                line2 = br.readLine();
			            }else if (line1 != null && line1.equalsIgnoreCase("NO")) {
		                    line3 = br.readLine();
		                }
			        } catch (IOException e) {
			            e.printStackTrace();
			        }
		        }
		        
				if(inning.getInningNumber() == 1) {
					if(line1.equalsIgnoreCase("YES")) {
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$ScoreGrp$BowlTeam_Over_PPGrp$txt_DLS*GEOM*TEXT SET " 
								+ "" + line2 + "" + "\0", print_writers);
					}else if(line1.equalsIgnoreCase("NO")) {
						if(matchAllData.getSetup().getReducedOvers() != null && Integer.valueOf(matchAllData.getSetup().getReducedOvers()) > 0) {
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$ScoreGrp$BowlTeam_Over_PPGrp$txt_DLS*GEOM*TEXT SET " 
									+ "(" + matchAllData.getSetup().getReducedOvers() + ")" + "\0", print_writers);
						}else {
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$ScoreGrp$BowlTeam_Over_PPGrp$txt_DLS*GEOM*TEXT SET \0", print_writers);
						}
					}
				}else {
					
					if(line1.equalsIgnoreCase("YES")) {
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$ScoreGrp$BowlTeam_Over_PPGrp$txt_DLS*GEOM*TEXT SET " 
								+ "(" + line3 + ") " + ((matchAllData.getSetup().getTargetType() != null && 
								!matchAllData.getSetup().getTargetType().isEmpty())?matchAllData.getSetup().getTargetType().toUpperCase():"") + "\0", print_writers);
					}else if(line1.equalsIgnoreCase("NO")) {
						if(matchAllData.getSetup().getTargetOvers() != null && !matchAllData.getSetup().getTargetOvers().isEmpty()) {
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$ScoreGrp$BowlTeam_Over_PPGrp$txt_DLS*GEOM*TEXT SET " 
									+ "(" + matchAllData.getSetup().getTargetOvers() + ") " + ((matchAllData.getSetup().getTargetType() != null && 
									!matchAllData.getSetup().getTargetType().isEmpty())?matchAllData.getSetup().getTargetType().toUpperCase():"") + "\0", print_writers);
						}else {
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$ScoreGrp$BowlTeam_Over_PPGrp$txt_DLS*GEOM*TEXT SET \0", print_writers);
						}
					}
				}
			}
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$ScoreGrp$BattingTeamGrp$txt_Score*GEOM*TEXT SET " + 
					CricketFunctions.getTeamScore(inning, "-", false) + "\0", print_writers);
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$ScoreGrp$BowlTeam_Over_PPGrp$txt_Overs*GEOM*TEXT SET " + 
					CricketFunctions.OverBalls(inning.getTotalOvers(), inning.getTotalBalls()) + "\0", print_writers);
//			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$ScoreGrp$BowlTeam_Over_PPGrp$txt_Overs*GEOM*TEXT SET " + 
//					CricketFunctions.OverBalls(inning.getTotalOvers(), inning.getTotalBalls())+(inning.getTotalOvers()==1 && 
//					inning.getTotalBalls()==0?" over":" overs") + "\0", print_writers);
			
			
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$SB_Small$ScoreGrp$BattingTeamGrp$txt_Score*GEOM*TEXT SET " + 
					CricketFunctions.getTeamScore(inning, "-", false) + "\0", print_writers);
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$SB_Small$ScoreGrp$BowlTeam_Over_PPGrp$txt_Overs*GEOM*TEXT SET " + 
					CricketFunctions.OverBalls(inning.getTotalOvers(), inning.getTotalBalls()) + "\0", print_writers);
	
			break;
		case Constants.BAN_AFG_SERIES:
			if(is_this_updating == false) {
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$ScoreGrp$BattingTeamGrp$txt_BattingTeamName*GEOM*TEXT SET " 
						+ inning.getBatting_team().getTeamName1() + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$SB_Small$ScoreGrp$BattingTeamGrp$txt_BattingTeamName*GEOM*TEXT SET " 
						+ inning.getBatting_team().getTeamName4() + "\0", print_writers);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$ScoreGrp$BowlTeam_Over_PPGrp$txt_BowlingTeamName*ACTIVE SET 0 \0", print_writers);
				
				String OverData = "";
				if(inning.getInningNumber() == 1) {
					
					if(line1.equalsIgnoreCase("YES")) {
						OverData = "(" + line2 + ")";
					}else if(line1.equalsIgnoreCase("NO")) {
						OverData = "(" + (Integer.valueOf(matchAllData.getSetup().getReducedOvers()) > 0 ? " (" + matchAllData.getSetup().getReducedOvers() + ")":"") + ")";
					}
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$ScoreGrp$BowlTeam_Over_PPGrp$txt_DLS*GEOM*TEXT SET " 
							+ OverData + "\0", print_writers);
//					if(Integer.valueOf(matchAllData.getSetup().getReducedOvers()) > 0) {
//						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$ScoreGrp$BowlTeam_Over_PPGrp$txt_DLS*GEOM*TEXT SET " 
//								+ "(" + OverData + ")" + "\0", print_writers);
//					}else {
//						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$ScoreGrp$BowlTeam_Over_PPGrp$txt_DLS*GEOM*TEXT SET \0", print_writers);
//					}
				}else {
					if(line1.equalsIgnoreCase("YES")) {
						OverData = "(" + line3 + ")";
					}else if(line1.equalsIgnoreCase("NO")) {
						OverData = (matchAllData.getSetup().getTargetOvers() != null && !matchAllData.getSetup().getTargetOvers().trim().isEmpty() ? 
								" (" + matchAllData.getSetup().getTargetOvers() + ")":"");
					}
					
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$ScoreGrp$BowlTeam_Over_PPGrp$txt_DLS*GEOM*TEXT SET " 
							+ OverData + "\0", print_writers);
					
//					if(matchAllData.getSetup().getTargetOvers() != null && !matchAllData.getSetup().getTargetOvers().isEmpty()) {
//						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$ScoreGrp$BowlTeam_Over_PPGrp$txt_DLS*GEOM*TEXT SET " 
//								+ "(" + matchAllData.getSetup().getTargetOvers() + ") " + ((matchAllData.getSetup().getTargetType() != null && 
//								!matchAllData.getSetup().getTargetType().isEmpty())?matchAllData.getSetup().getTargetType().toUpperCase():"") + "\0", print_writers);
//					}else {
//						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$ScoreGrp$BowlTeam_Over_PPGrp$txt_DLS*GEOM*TEXT SET \0", print_writers);
//					}
				}
			}
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$ScoreGrp$BattingTeamGrp$txt_Score*GEOM*TEXT SET " + 
					CricketFunctions.getTeamScore(inning, "-", false) + "\0", print_writers);
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$ScoreGrp$BowlTeam_Over_PPGrp$txt_Overs*GEOM*TEXT SET " + 
					CricketFunctions.OverBalls(inning.getTotalOvers(), inning.getTotalBalls()) + "\0", print_writers);
//			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$ScoreGrp$BowlTeam_Over_PPGrp$txt_Overs*GEOM*TEXT SET " + 
//					CricketFunctions.OverBalls(inning.getTotalOvers(), inning.getTotalBalls())+(inning.getTotalOvers()==1 && 
//					inning.getTotalBalls()==0?" over":" overs") + "\0", print_writers);
			
			
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$SB_Small$ScoreGrp$BattingTeamGrp$txt_Score*GEOM*TEXT SET " + 
					CricketFunctions.getTeamScore(inning, "-", false) + "\0", print_writers);
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$SB_Small$ScoreGrp$BowlTeam_Over_PPGrp$txt_Overs*GEOM*TEXT SET " + 
					CricketFunctions.OverBalls(inning.getTotalOvers(), inning.getTotalBalls()) + "\0", print_writers);
	
			break;
		case Constants.TRI_SERIES:  case Constants.MT20: case Constants.TG20:
			if(is_this_updating == false) {
				logosAndBaseColor(print_writers, "F12", matchAllData, 1);
				for(int i=1;i<=9;i++) {
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$RightSection$Section3$Side1$ThisOver$select_BallNumber"
						+ "$Ball_" + i + "$Select_DataType*FUNCTION*Omo*vis_con SET 0\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$RightSection$Section3$Side2$ThisOver$select_BallNumber"
						+ "$Ball_" + i + "$Select_DataType*FUNCTION*Omo*vis_con SET 0\0", print_writers);
				}
			}

			boolean useScriptContainer = is_this_updating;
			if(config.getSecondaryBroadcaster().equalsIgnoreCase("without_script")) {
				useScriptContainer = false;
			}
			
			if(useScriptContainer == true) {

				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$ScoreSection$ScoreGrpAll*SCRIPT*INSTANCE*startValue SET " 
					+ previous_runs + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$ScoreSection$ScoreGrpAll*SCRIPT*INSTANCE*endValue SET " 
					+ inning.getTotalRuns() + "\0", print_writers);

				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$ScoreSection$ScoreGrpAll*SCRIPT*INSTANCE*wicketStartValue SET " 
					+ previous_wickets + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$ScoreSection$ScoreGrpAll*SCRIPT*INSTANCE*wicketEndValue SET " 
					+ inning.getTotalWickets() + "\0", print_writers);					

				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$ScoreSection$OversAll*SCRIPT*INSTANCE*startValue SET " 
					+ previous_overs + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$ScoreSection$OversAll*SCRIPT*INSTANCE*endValue SET " 
					+ inning.getTotalOvers() + "\0", print_writers);
				
				next_ball = inning.getTotalBalls();
				
				if (previous_balls == 6 && next_ball <= 0) {next_ball = 6;}
				else if (previous_balls == 6 && next_ball == 1) {previous_balls = 0;}
				else if (previous_balls == 5 && next_ball <= 0) {next_ball = 6;}
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$ScoreSection$OversAll*SCRIPT*INSTANCE*ballsStartValue SET " 
					+ previous_balls + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$ScoreSection$OversAll*SCRIPT*INSTANCE*ballsEndValue SET " 
					+ next_ball + "\0", print_writers);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$ScoreSection$ScoreGrpAll*SCRIPT*INSTANCE*StartLoopBtn "
					+ "INVOKE\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$ScoreSection$OversAll*SCRIPT*INSTANCE*StartLoopBtn "
					+ "INVOKE\0", print_writers);
				
				if(previous_runs != inning.getTotalRuns() || previous_wickets != inning.getTotalWickets()) {
					setPosition(print_writers, 1, CricketUtil.TEAM);
					setPosition(print_writers, 2, CricketUtil.TEAM);
				    this_animation.processAnimation(Constants.FRONT, print_writers, "MoveForDigitsScore", "START");
				}
				if(previous_overs != inning.getTotalOvers() || previous_balls != next_ball) {
					setPosition(print_writers, 1, CricketUtil.OVER);
					setPosition(print_writers, 2, CricketUtil.OVER);
				    this_animation.processAnimation(Constants.FRONT, print_writers, "MoveForDigitsOver", "START");
				}
				if(previous_runs != inning.getTotalRuns() || previous_wickets != inning.getTotalWickets()
					|| previous_overs != inning.getTotalOvers() || previous_balls != next_ball) {
				    TimeUnit.MILLISECONDS.sleep(300);
				}
				
			} else {

				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$ScoreSection$ScoreGrpAll$Hundreths$Side" 
					+ whichSide + "$txt_Figure*GEOM*TEXT SET " + CricketFunctions.hundredsTensUnitsTeamScore(
					String.valueOf(inning.getTotalRuns())).split(",")[0] + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$ScoreSection$ScoreGrpAll$Tenths$Side" 
					+ whichSide + "$txt_Figure*GEOM*TEXT SET " + CricketFunctions.hundredsTensUnitsTeamScore(
					String.valueOf(inning.getTotalRuns())).split(",")[1] + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$ScoreSection$ScoreGrpAll$Units$Side" 
					+ whichSide + "$txt_Figure*GEOM*TEXT SET " + CricketFunctions.hundredsTensUnitsTeamScore(
					String.valueOf(inning.getTotalRuns())).split(",")[2] + "\0", print_writers);

				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$ScoreSection$ScoreGrpAll$Wicket$Side" 
					+ whichSide + "$txt_Figure*GEOM*TEXT SET " + (inning.getTotalWickets() >= 10 ? "" : inning.getTotalWickets()) + "\0", print_writers);
				
				if(inning.getTotalWickets() >= 10) {
				    this_animation.processAnimation(Constants.FRONT, print_writers, "Score_Counter$Hyphen", "SHOW 0.2");
				} else {
				    this_animation.processAnimation(Constants.FRONT, print_writers, "Score_Counter$Hyphen", "SHOW 0.0");
				}

				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$ScoreSection$OversAll$OversValue$Hundreths$Side" 
					+ whichSide + "$txt_Overs*GEOM*TEXT SET " + CricketFunctions.hundredsTensUnitsTeamScore(
					String.valueOf(inning.getTotalOvers())).split(",")[0] + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$ScoreSection$OversAll$OversValue$Tenths$Side" 
					+ whichSide + "$txt_Overs*GEOM*TEXT SET " + CricketFunctions.hundredsTensUnitsTeamScore(
					String.valueOf(inning.getTotalOvers())).split(",")[1] + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$ScoreSection$OversAll$OversValue$Units$Side" 
					+ whichSide + "$txt_Overs*GEOM*TEXT SET " + CricketFunctions.hundredsTensUnitsTeamScore(
					String.valueOf(inning.getTotalOvers())).split(",")[2] + "\0", print_writers);
				if(inning.getTotalBalls() >= 1 && inning.getTotalBalls() <= 5) {
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$ScoreSection$OversAll$OversValue$Decimal$Side" 
						+ whichSide + "$txt_Overs*GEOM*TEXT SET " + inning.getTotalBalls() + "\0", print_writers);
					System.out.println("inning.getTotalBalls() = " + inning.getTotalBalls());
				} else {
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$ScoreSection$OversAll$OversValue$Decimal$Side" 
						+ whichSide + "$txt_Overs*GEOM*TEXT SET " + "" + "\0", print_writers);
					System.out.println("inning.getTotalBalls() = ZERO: " + inning.getTotalBalls());
				}
				next_ball = inning.getTotalBalls();
			}

			if(inning.getTotalBalls() >= 1 && inning.getTotalBalls() <= 5) {
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$ScoreSection$OversAll$OversValue$$Dot"
					+ "*ACTIVE SET 1\0", print_writers);
			} else {
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$ScoreSection$OversAll$OversValue$$Dot"
					+ "*ACTIVE SET 0\0", print_writers);
			}
			
			previous_runs = inning.getTotalRuns();
			previous_wickets = inning.getTotalWickets();
			previous_overs = inning.getTotalOvers();
			previous_balls = next_ball;

			setPosition(print_writers, 1, CricketUtil.TEAM);
		    this_animation.processAnimation(Constants.FRONT, print_writers, "MoveForDigitsScore", "SHOW 0.0");
			setPosition(print_writers, 1, CricketUtil.OVER);
		    this_animation.processAnimation(Constants.FRONT, print_writers, "MoveForDigitsOver", "SHOW 0.0");
			
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$ScoreSection$OversAll$select_Overs"
				+ "*FUNCTION*Omo*vis_con SET 1\0",print_writers);
			switch(config.getBroadcaster()) {
			case Constants.TG20:
				CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$ScoreSection$OversAll$select_Langiage"
						+ "*FUNCTION*Omo*vis_con SET ", config, Constants.BCCI, print_writers, foreignLanguageOmo);
				foreignLanguageData = CricketFunctions.AssembleMultiLanguageData(CricketUtil.DICTIONARY, "", multilanguagedata, 
						(inning.getTotalOvers() == 1 && inning.getTotalBalls() == 0 ? "OVER" : "OVERS"), "", null, 0, foreignLanguageDataList);
				CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$ScoreSection$OversAll$English$txt_Overs"
						+ "*GEOM*TEXT SET ", config, Constants.BCCI, print_writers, foreignLanguageData);
				break;
			default:
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$ScoreSection$OversAll$txt_Overs*GEOM*TEXT SET " 
						+ (inning.getTotalOvers() == 1 && inning.getTotalBalls() == 0 ? "OVER" : "OVERS") + "\0", print_writers);
				break;
			}
			
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$ScoreSection$OversAll$select_DLS"
				+ "*FUNCTION*Omo*vis_con SET 0\0",print_writers);
			
			if(inning.getInningNumber() == 1) {
				if(matchAllData.getSetup().getReducedOvers() != null) {
					if(Integer.valueOf(matchAllData.getSetup().getReducedOvers()) > 0) {
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$ScoreSection$OversAll$select_Overs"
								+ "*FUNCTION*Omo*vis_con SET 0\0",print_writers);
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$ScoreSection$OversAll$select_DLS"
								+ "*FUNCTION*Omo*vis_con SET 1\0",print_writers);
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$ScoreSection$OversAll$txt_DLS*GEOM*TEXT SET " 
								+ "(" + matchAllData.getSetup().getReducedOvers() + ")" + "\0", print_writers);
					}
				}
			}else {
				if(matchAllData.getSetup().getTargetOvers() != null && !matchAllData.getSetup().getTargetOvers().isEmpty()) {
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$ScoreSection$OversAll$select_Overs"
							+ "*FUNCTION*Omo*vis_con SET 0\0",print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$ScoreSection$OversAll$select_DLS"
							+ "*FUNCTION*Omo*vis_con SET 1\0",print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$ScoreSection$OversAll$txt_DLS*GEOM*TEXT SET " 
							+ "(" + matchAllData.getSetup().getTargetOvers() + ") " + ((matchAllData.getSetup().getTargetType() != null && 
							!matchAllData.getSetup().getTargetType().isEmpty()) ? matchAllData.getSetup().getTargetType().toUpperCase() : "") + "\0", print_writers);
				}
			}
//			if(matchAllData.getSetup().getMatchType().equalsIgnoreCase(CricketUtil.SUPER_OVER)) {
//				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$ScoreSection$OversAll$PowerPlay*ACTIVE SET 0\0", print_writers);
//			}else {
//				if (!CricketFunctions.processPowerPlay(CricketUtil.MINI, matchAllData).isEmpty()) {
//					
//					System.out.println("matchAllData.getSetup().getNumberOfPowerplays() = " + matchAllData.getSetup().getNumberOfPowerplays());
//					if(matchAllData.getSetup().getMatchType().equalsIgnoreCase(CricketUtil.DT20)){
//						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$ScoreSection$OversAll$PowerPlay$txt_PowerPlay"
//								+ "*GEOM*TEXT SET POWERPLAY\0", print_writers);
//					}else {
//						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$ScoreSection$OversAll$PowerPlay$txt_PowerPlay"
//								+ "*GEOM*TEXT SET " + (matchAllData.getSetup().getNumberOfPowerplays() == 1 ? "POWERPLAY" : 
//									CricketFunctions.processPowerPlay(CricketUtil.MINI, matchAllData)) + "\0", print_writers);
//					}
////					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$ScoreSection$OversAll$PowerPlay$txt_PowerPlay"
////							+ "*GEOM*TEXT SET " + (matchAllData.getSetup().getNumberOfPowerplays() == 1 ? "POWERPLAY" : 
////								CricketFunctions.processPowerPlay(CricketUtil.MINI, matchAllData)) + "\0", print_writers);
//					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$ScoreSection$OversAll$PowerPlay$txt_PowerPlay"
//							+ "*GEOM*TEXT SET " + (matchAllData.getSetup().getNumberOfPowerplays() == 1 ? "POWERPLAY" : 
//								CricketFunctions.processPowerPlay(CricketUtil.MINI, matchAllData)) + "\0", print_writers);
//					
//					if(infobar.isForced_powerplay_out() == false) {
//						if (infobar.isPowerplay_on_screen() == true) {
//						} else {
//							if (infobar.isPowerplay_on_screen() == false) {
//								CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*STAGE*DIRECTOR*PowerPlay START\0", print_writers);
//								infobar.setPowerplay_on_screen(true);
//							}
//						}
//					}
//				}else {
//					if (infobar.isPowerplay_on_screen() == true) {
//						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*STAGE*DIRECTOR*PowerPlay CONTINUE REVERSE\0", print_writers);
//						infobar.setPowerplay_on_screen(false);
//					}
//				}
//			}
			break;
			
		case Constants.BCCI:
			if(is_this_updating == false) {
				if(this.infobar.isInfobar_on_screen()) {
					logosAndBaseColor(print_writers, "F12", matchAllData, 2);
				}else {
					logosAndBaseColor(print_writers, "F12", matchAllData, 1);
				}
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$NormalAll$MainScoreGrp$MainScoreGrp$TeamName$txt_TeamName*GEOM*TEXT SET " 
						+ inning.getBatting_team().getTeamName4() + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$NormalAll$OutForSection5$Section-1-2$BowlingTeamName$txt_TeamName"
						+ "*GEOM*TEXT SET v " + inning.getBowling_team().getTeamName4() + "\0", print_writers);
			}
			
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$NormalAll$MainScoreGrp$MainScoreGrp$Score$txt_Score*GEOM*TEXT SET " 
					+ CricketFunctions.getTeamScore(inning, "-", false) + "\0", print_writers);
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$NormalAll$MainScoreGrp$MainScoreGrp$Overs$txt_Overs*GEOM*TEXT SET " 
					+ CricketFunctions.OverBalls(inning.getTotalOvers(), inning.getTotalBalls()) + "\0", print_writers);
			
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$NormalAll$MainScoreGrp$MainScoreGrp$Overs$txt_DLOvers*GEOM*TEXT SET "
					+ "OVR" + "\0", print_writers);
			
			if(matchAllData.getSetup().getMatchType().equalsIgnoreCase(CricketUtil.TEST) || matchAllData.getSetup().getMatchType().equalsIgnoreCase(CricketUtil.FC)
					|| matchAllData.getSetup().getMatchType().equalsIgnoreCase(CricketUtil.SUPER_OVER)) {
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*STAGE*DIRECTOR*PowerPlay SHOW 0.500\0", print_writers);
			}else {
				if (!CricketFunctions.processPowerPlay(CricketUtil.MINI, matchAllData).isEmpty()) {
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$NormalAll$MainScoreGrp$MainScoreGrp$Overs$PowerPlayGrp$txt_PowerPlay"
							+ "*GEOM*TEXT SET " + (matchAllData.getSetup().getNumberOfPowerplays()==1?"P":CricketFunctions.processPowerPlay(CricketUtil.MINI, matchAllData)) + "\0", print_writers);
					if (infobar.isPowerplay_on_screen() == true) {
					} else {
						if (infobar.isPowerplay_on_screen() == false) {
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*STAGE*DIRECTOR*PowerPlay CONTINUE REVERSE\0", print_writers);
							infobar.setPowerplay_on_screen(true);
						}
					}
				}else {
					if (infobar.isPowerplay_on_screen() == true) {
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*STAGE*DIRECTOR*PowerPlay START\0", print_writers);
						infobar.setPowerplay_on_screen(false);
					}
				}
			}
			break;
		}
		return Constants.OK;
	}
	
	public String populateCurrentBatsmen(List<PrintWriter> print_writers, MatchAllData matchAllData, int WhichSide, int SubSide) throws InterruptedException {
		
		if(inning.getPartnerships() != null && inning.getPartnerships().size() <= 0) {
			return "populateCurrentBatsmen: Partnership array size is zero [" + inning.getPartnerships().size() + "]";
		}
		battingCardList = new ArrayList<BattingCard>();
		for(int iBat = 1; iBat <= 2; iBat++) {
			if(iBat == 1) {
				battingCardList.add(inning.getBattingCard().stream().filter(bc -> bc.getPlayerId() == 
					inning.getPartnerships().get(inning.getPartnerships().size() - 1).getFirstBatterNo()).findAny().orElse(null));
			} else {
				battingCardList.add(inning.getBattingCard().stream().filter(bc -> bc.getPlayerId() == 
					inning.getPartnerships().get(inning.getPartnerships().size() - 1).getSecondBatterNo()).findAny().orElse(null));
			}
			if(battingCardList.get(battingCardList.size()-1) == null) {
				return "populateCurrentBatsmen: One or more batsmen return are NULL";
			}
		}
		switch(config.getBroadcaster()) {
		case Constants.AFG_SL_SERIES:
			if(infobar.getLast_batsmen() != null && infobar.getLast_batsmen().size() >= 2) {
				if(infobar.getLast_batsmen().get(0).getPlayerId() != battingCardList.get(0).getPlayerId()) {
					populateTwoBatsmenSingleBatsman(print_writers, matchAllData, WhichSide, 2, 1, battingCardList, 1);
					this_animation.processAnimation(Constants.FRONT, print_writers, "Batter_Change$1", "START");
					TimeUnit.MILLISECONDS.sleep(800);
					populateTwoBatsmenSingleBatsman(print_writers, matchAllData, WhichSide, 1, 1, battingCardList, 1);
					this_animation.processAnimation(Constants.FRONT, print_writers, "Batter_Change$1", "SHOW 0.0");
				} else {
					populateTwoBatsmenSingleBatsman(print_writers, matchAllData, WhichSide, 1, 1, battingCardList, 1);
				}
				
				if(infobar.getLast_batsmen().get(1).getPlayerId() != battingCardList.get(1).getPlayerId()) {
					populateTwoBatsmenSingleBatsman(print_writers, matchAllData, WhichSide, 2, 2, battingCardList, 1);
					this_animation.processAnimation(Constants.FRONT, print_writers, "Batter_Change$2", "START");
					TimeUnit.MILLISECONDS.sleep(800);
					populateTwoBatsmenSingleBatsman(print_writers, matchAllData, WhichSide, 1, 2, battingCardList, 1);
					this_animation.processAnimation(Constants.FRONT, print_writers, "Batter_Change$2", "SHOW 0.0");
				} else {
					populateTwoBatsmenSingleBatsman(print_writers, matchAllData, WhichSide, 1, 2, battingCardList, 1);
				}
			} else {
				populateTwoBatsmenSingleBatsman(print_writers, matchAllData, 1, 1, 1, battingCardList, 1);
				populateTwoBatsmenSingleBatsman(print_writers, matchAllData, 1, 1, 2, battingCardList, 1);
			}
			infobar.setLast_batsmen(battingCardList);
			break;
		case Constants.ACC:
			if(infobar.getLast_batsmen() != null && infobar.getLast_batsmen().size() >= 2) {
				if(infobar.getLast_batsmen().get(0).getPlayerId() != battingCardList.get(0).getPlayerId()) {
					populateTwoBatsmenSingleBatsman(print_writers, matchAllData, WhichSide, 2, 1, battingCardList, 1);
					this_animation.processAnimation(Constants.FRONT, print_writers, "Anim_Infobar$Bat1DelhighlightIn", "START");
					TimeUnit.MILLISECONDS.sleep(800);
					populateTwoBatsmenSingleBatsman(print_writers, matchAllData, WhichSide, 1, 1, battingCardList, 1);
					this_animation.processAnimation(Constants.FRONT, print_writers, "Anim_Infobar$Bat1DelhighlightIn", "SHOW 0.0");
				} else {
					populateTwoBatsmenSingleBatsman(print_writers, matchAllData, WhichSide, 1, 1, battingCardList, 1);
				}
				
				if(infobar.getLast_batsmen().get(1).getPlayerId() != battingCardList.get(1).getPlayerId()) {
					populateTwoBatsmenSingleBatsman(print_writers, matchAllData, WhichSide, 2, 2, battingCardList, 1);
					this_animation.processAnimation(Constants.FRONT, print_writers, "Anim_Infobar$Bat2DelhighlightIn", "START");
					TimeUnit.MILLISECONDS.sleep(800);
					populateTwoBatsmenSingleBatsman(print_writers, matchAllData, WhichSide, 1, 2, battingCardList, 1);
					this_animation.processAnimation(Constants.FRONT, print_writers, "Anim_Infobar$Bat2DelhighlightIn", "SHOW 0.0");
				} else {
					populateTwoBatsmenSingleBatsman(print_writers, matchAllData, WhichSide, 1, 2, battingCardList, 1);
				}
			} else {
				populateTwoBatsmenSingleBatsman(print_writers, matchAllData, 1, 1, 1, battingCardList, 1);
				populateTwoBatsmenSingleBatsman(print_writers, matchAllData, 1, 1, 2, battingCardList, 1);
			}
			infobar.setLast_batsmen(battingCardList);
			break;
		case Constants.BAN_AFG_SERIES:
			if(infobar.getLast_batsmen() != null && infobar.getLast_batsmen().size() >= 2) {
				if(infobar.getLast_batsmen().get(0).getPlayerId() != battingCardList.get(0).getPlayerId()) {
					populateInfobarBatsman(false, print_writers, battingCardList.get(0), 1, 2);
					this_animation.processAnimation(Constants.FRONT, print_writers, "Anim_Infobar$Batsman1$Change", "START");
					TimeUnit.MILLISECONDS.sleep(800);
					populateInfobarBatsman(false, print_writers, battingCardList.get(0), 1, 1);
					this_animation.processAnimation(Constants.FRONT, print_writers, "Anim_Infobar$Batsman1$Change", "SHOW 0.0");
				} else {
					populateInfobarBatsman(true, print_writers, battingCardList.get(0), 1, 1);
				}
				
				if(infobar.getLast_batsmen().get(1).getPlayerId() != battingCardList.get(1).getPlayerId()) {
					populateInfobarBatsman(false, print_writers, battingCardList.get(1), 2, 2);
					this_animation.processAnimation(Constants.FRONT, print_writers, "Anim_Infobar$Batsman2$Change", "START");
					TimeUnit.MILLISECONDS.sleep(800);
					populateInfobarBatsman(false, print_writers, battingCardList.get(1), 2, 1);
					this_animation.processAnimation(Constants.FRONT, print_writers, "Anim_Infobar$Batsman2$Change", "SHOW 0.0");
				} else {
					populateInfobarBatsman(true, print_writers, battingCardList.get(1), 2, 1);
				}
			} else {
				populateInfobarBatsman(false, print_writers, battingCardList.get(0), 1, 1);
				populateInfobarBatsman(false, print_writers, battingCardList.get(1), 2, 1);
			}
			infobar.setLast_batsmen(battingCardList);
			break;
		case Constants.TRI_SERIES:  case Constants.MT20: case Constants.TG20:
			if(infobar.getLast_batsmen() != null && infobar.getLast_batsmen().size() >= 2) {
				if(infobar.getLast_batsmen().get(0).getPlayerId() != battingCardList.get(0).getPlayerId()) {
					populateTwoBatsmenSingleBatsman(print_writers, matchAllData, 2, 1, 1, battingCardList, SubSide);
					this_animation.processAnimation(Constants.FRONT, print_writers, "Batsmen_Change$1", "START");
					TimeUnit.MILLISECONDS.sleep(800);
					populateTwoBatsmenSingleBatsman(print_writers, matchAllData, 1, 1, 1, battingCardList, 1);
					this_animation.processAnimation(Constants.FRONT, print_writers, "Batsmen_Change$1", "SHOW 0.0");
					this_animation.processAnimation(Constants.FRONT, print_writers, "BatsmanCounter$1", "SHOW 0.0");
				} else {
					populateTwoBatsmenSingleBatsman(print_writers, matchAllData, 1, 1, 1, battingCardList, SubSide);
				}
				
				if(infobar.getLast_batsmen().get(1).getPlayerId() != battingCardList.get(1).getPlayerId()) {
					populateTwoBatsmenSingleBatsman(print_writers, matchAllData, 2, 1, 2, battingCardList, SubSide);
					this_animation.processAnimation(Constants.FRONT, print_writers, "Batsmen_Change$2", "START");
					TimeUnit.MILLISECONDS.sleep(800);
					populateTwoBatsmenSingleBatsman(print_writers, matchAllData, 1, 1, 2, battingCardList, 1);
					this_animation.processAnimation(Constants.FRONT, print_writers, "Batsmen_Change$2", "SHOW 0.0");
					this_animation.processAnimation(Constants.FRONT, print_writers, "BatsmanCounter$2", "SHOW 0.0");
				} else {
					populateTwoBatsmenSingleBatsman(print_writers, matchAllData, 1, 1, 2, battingCardList, SubSide);
				}
			} else {
				populateTwoBatsmenSingleBatsman(print_writers, matchAllData, 1, 1, 1, battingCardList, SubSide);
				populateTwoBatsmenSingleBatsman(print_writers, matchAllData, 1, 1, 2, battingCardList, SubSide);
			}
			infobar.setLast_batsmen(battingCardList);
			break;
		case Constants.BCCI:
			if(infobar.getLast_batsmen() != null && infobar.getLast_batsmen().size() >= 2) {
				if(infobar.getLast_batsmen().get(0).getPlayerId() != battingCardList.get(0).getPlayerId()) {
					populateTwoBatsmenSingleBatsman(print_writers, matchAllData, WhichSide, 2, 1, battingCardList, 1);
					this_animation.processAnimation(Constants.FRONT, print_writers, "Batter_Lowlight$1", "START");
					TimeUnit.MILLISECONDS.sleep(800);
					populateTwoBatsmenSingleBatsman(print_writers, matchAllData, WhichSide, 1, 1, battingCardList, 1);
					this_animation.processAnimation(Constants.FRONT, print_writers, "Batter_Lowlight$1", "SHOW 0.0");
				} else {
					populateTwoBatsmenSingleBatsman(print_writers, matchAllData, WhichSide, 1, 1, battingCardList, 1);
				}
				
				if(infobar.getLast_batsmen().get(1).getPlayerId() != battingCardList.get(1).getPlayerId()) {
					populateTwoBatsmenSingleBatsman(print_writers, matchAllData, WhichSide, 2, 2, battingCardList, 1);
					this_animation.processAnimation(Constants.FRONT, print_writers, "Batter_Lowlight$2", "START");
					TimeUnit.MILLISECONDS.sleep(800);
					populateTwoBatsmenSingleBatsman(print_writers, matchAllData, WhichSide, 1, 2, battingCardList, 1);
					this_animation.processAnimation(Constants.FRONT, print_writers, "Batter_Lowlight$2", "SHOW 0.0");
				} else {
					populateTwoBatsmenSingleBatsman(print_writers, matchAllData, WhichSide, 1, 2, battingCardList, 1);
				}
			} else {
				populateTwoBatsmenSingleBatsman(print_writers, matchAllData, 1, 1, 1, battingCardList, 1);
				populateTwoBatsmenSingleBatsman(print_writers, matchAllData, 1, 1, 2, battingCardList, 1);
			}
			infobar.setLast_batsmen(battingCardList);
			break;
		}
		return Constants.OK;
	}
	public String populateInfobarBatsman(boolean isItUpdating, List<PrintWriter> print_writers, BattingCard battingCard, int whichBatsman, int whichSide) 
	{
		if(isItUpdating == false) {
			if(config.getPrimaryIpAddress().equalsIgnoreCase(Constants.LOCALHOST)) {
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$BatsmanGrp$Batsman" + whichBatsman + "_Grp$Side" + whichSide + 
						"$img_Player*TEXTURE*IMAGE SET " + Constants.BAN_AFG_SERIES_LOCAL_PHOTO_PATH + inning.getBatting_team().getTeamName4() + "\\\\" + 
						Constants.RIGHT + "\\\\" + battingCard.getPlayer().getPhoto()+CricketUtil.PNG_EXTENSION + "\0", print_writers);
			}else {
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$BatsmanGrp$Batsman" + whichBatsman + "_Grp$Side" + whichSide + 
						"$img_Player*TEXTURE*IMAGE SET " + "\\\\" + config.getPrimaryIpAddress() + Constants.BAN_AFG_SERIES_PHOTO_PATH + inning.getBatting_team().getTeamName4() + 
						"\\\\" + Constants.RIGHT + "\\\\" + battingCard.getPlayer().getPhoto()+CricketUtil.PNG_EXTENSION + "\0", print_writers);
			}
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$BatsmanGrp$Batsman" + whichBatsman + "_Grp$Side" + whichSide + 
					"$txt_Batsman*GEOM*TEXT SET " + battingCard.getPlayer().getTicker_name() + "\0", print_writers);
		}
		
		CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$BatsmanGrp$Batsman" + whichBatsman + "_Grp$Side" + whichSide + 
				"$txt_Runs*GEOM*TEXT SET " + battingCard.getRuns() + "\0", print_writers);
		CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$BatsmanGrp$Batsman" + whichBatsman + "_Grp$Side" + whichSide + 
				"$txt_Balls*GEOM*TEXT SET " + battingCard.getBalls() + "\0", print_writers);
		
		if((whichBatsman == 1 && battingCard.getStatus().equalsIgnoreCase(CricketUtil.NOT_OUT)) || 
				(whichBatsman == 2 && battingCard.getStatus().equalsIgnoreCase(CricketUtil.NOT_OUT))) {
			this_animation.processAnimation(Constants.FRONT, print_writers, "Bat" + whichBatsman + "DelhighlightIn", "SHOW 0.0");
		} else {
			this_animation.processAnimation(Constants.FRONT, print_writers, "Bat" + whichBatsman + "DelhighlightIn", "SHOW 0.220");	
		}
		
//-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------		
		
//		if((whichBatsman == 1 && battingCard.getOnStrike() == null) || (whichBatsman == 2 && battingCard.getOnStrike() == null)) {
//			this_animation.processAnimation(Constants.FRONT, print_writers, "OnStrike$In_Out", "SHOW 0.0");
//			
//			if((whichBatsman == 1 && battingCardList.get(1).getOnStrike().equalsIgnoreCase(CricketUtil.YES)) || 
//					whichBatsman == 2 && battingCardList.get(0).getOnStrike().equalsIgnoreCase(CricketUtil.YES)) {
//				this_animation.processAnimation(Constants.FRONT, print_writers, "OnStrike$In_Out", "SHOW 0.440");
//			}
//		}else {
//			if((whichBatsman == 1 && battingCard.getOnStrike().equalsIgnoreCase(CricketUtil.YES)) || 
//					(whichBatsman == 2 && battingCard.getOnStrike().equalsIgnoreCase(CricketUtil.YES))) {
//				this_animation.processAnimation(Constants.FRONT, print_writers, "OnStrike$In_Out", "SHOW 0.440");
//			}
//		}
//		
//		if(whichBatsman == 1) { //Left hand side batter
//			if(battingCard.getOnStrike() != null && battingCard.getOnStrike().equalsIgnoreCase(CricketUtil.YES)) {
//				if(lastBatsmanOnStrike != battingCard.getPlayerId()) {
//					this_animation.processAnimation(Constants.FRONT, print_writers, "OnStrike$Strike_Change", "CONTINUE REVERSE");
//				}
//				lastBatsmanOnStrike = battingCard.getPlayerId();
//			}
//		} else if(whichBatsman == 2) { //Right hand side batter
//			if(battingCard.getOnStrike() != null && battingCard.getOnStrike().equalsIgnoreCase(CricketUtil.YES)) {
//				if(lastBatsmanOnStrike != battingCard.getPlayerId()) {
//					this_animation.processAnimation(Constants.FRONT, print_writers, "OnStrike$Strike_Change", "START");
//				}
//				lastBatsmanOnStrike = battingCard.getPlayerId();
//			}
//		}
		
//--------------------------------------------------------------------------------------------------------------------------------------		
		
		System.out.println("whichBatsman = " + whichBatsman);
		System.out.println("lastBatsmanOnStrike = " + lastBatsmanOnStrike);
		System.out.println("battingCard.getPlayerId() = " + battingCard.getPlayerId());
		System.out.println("battingCard.getOnStrike() = " + battingCard.getOnStrike());
		if(infobar.getLast_batsmen() != null && infobar.getLast_batsmen().size() >= 2) {
			System.out.println("infobar.getLast_batsmen().get(0).getPlayerId() = " + infobar.getLast_batsmen().get(0));
			System.out.println("infobar.getLast_batsmen().get(1).getPlayerId() = " + infobar.getLast_batsmen().get(1));
		}
		
		if(isItUpdating == false && whichSide == 1) {
			
			if(whichBatsman == 1) {
				if(battingCard.getOnStrike() != null && battingCard.getOnStrike().equalsIgnoreCase(CricketUtil.YES)) {
					this_animation.processAnimation(Constants.FRONT, print_writers, "OnStrike$Strike_Change", "SHOW 0.0");					
					this_animation.processAnimation(Constants.FRONT, print_writers, "OnStrike$In_Out", "START");					
					lastBatsmanOnStrike = battingCard.getPlayerId();
				}
			}else if(whichBatsman == 2) {
				if(battingCard.getOnStrike() != null && battingCard.getOnStrike().equalsIgnoreCase(CricketUtil.YES)) {
					this_animation.processAnimation(Constants.FRONT, print_writers, "OnStrike$Strike_Change", "SHOW 0.360");					
					this_animation.processAnimation(Constants.FRONT, print_writers, "OnStrike$In_Out", "START");					
					lastBatsmanOnStrike = battingCard.getPlayerId();
				}
			}			
		} else if(isItUpdating == false && whichSide == 2) { // Do nothing when batsman is changing on
		} else {
			if(whichBatsman == 1) {
				if(battingCard.getOnStrike() != null && battingCard.getOnStrike().equalsIgnoreCase(CricketUtil.YES)) {
					if(lastBatsmanOnStrike <= 0) {
						this_animation.processAnimation(Constants.FRONT, print_writers, "OnStrike$In_Out", "START");
						lastBatsmanOnStrike = battingCard.getPlayerId();
					}else if(lastBatsmanOnStrike == battingCard.getPlayerId()) {
						this_animation.processAnimation(Constants.FRONT, print_writers, "OnStrike$In_Out", "SHOW 0.440");
						lastBatsmanOnStrike = battingCard.getPlayerId();
					}else if(lastBatsmanOnStrike != battingCard.getPlayerId()) {
						if(infobar.getLast_batsmen() != null && infobar.getLast_batsmen().size() >= 2) {
							if(lastBatsmanOnStrike == infobar.getLast_batsmen().get(1).getPlayerId()) {
								this_animation.processAnimation(Constants.FRONT, print_writers, "OnStrike$Strike_Change", "CONTINUE REVERSE");					
								lastBatsmanOnStrike = battingCard.getPlayerId();
							}
						} else {
							this_animation.processAnimation(Constants.FRONT, print_writers, "OnStrike$In_Out", "SHOW 0.440");					
							lastBatsmanOnStrike = battingCard.getPlayerId();
						}
					}
				} else {
					if(lastBatsmanOnStrike == battingCard.getPlayerId()) {
						this_animation.processAnimation(Constants.FRONT, print_writers, "OnStrike$In_Out", "CONTINUE");					
						lastBatsmanOnStrike = 0;
					}
				}
			}else if(whichBatsman == 2) {
				if(battingCard.getOnStrike() != null && battingCard.getOnStrike().equalsIgnoreCase(CricketUtil.YES)) {
					if(lastBatsmanOnStrike <= 0) {
						this_animation.processAnimation(Constants.FRONT, print_writers, "OnStrike$Strike_Change", "SHOW 0.360");
						this_animation.processAnimation(Constants.FRONT, print_writers, "OnStrike$In_Out", "START");					
						lastBatsmanOnStrike = battingCard.getPlayerId();
					}else if(lastBatsmanOnStrike == battingCard.getPlayerId()) {
						this_animation.processAnimation(Constants.FRONT, print_writers, "OnStrike$In_Out", "SHOW 0.440");
						this_animation.processAnimation(Constants.FRONT, print_writers, "OnStrike$Strike_Change", "SHOW 0.360");
						lastBatsmanOnStrike = battingCard.getPlayerId();
					}else if(lastBatsmanOnStrike != battingCard.getPlayerId()) {
						if(infobar.getLast_batsmen() != null && infobar.getLast_batsmen().size() >= 2) {
							if(lastBatsmanOnStrike == infobar.getLast_batsmen().get(0).getPlayerId()) {
								this_animation.processAnimation(Constants.FRONT, print_writers, "OnStrike$In_Out", "SHOW 0.440");
								this_animation.processAnimation(Constants.FRONT, print_writers, "OnStrike$Strike_Change", "START");					
								lastBatsmanOnStrike = battingCard.getPlayerId();
							}
						} else {
							this_animation.processAnimation(Constants.FRONT, print_writers, "OnStrike$In_Out", "SHOW 0.440");
							this_animation.processAnimation(Constants.FRONT, print_writers, "OnStrike$Strike_Change", "SHOW 0.360");
							lastBatsmanOnStrike = battingCard.getPlayerId();
						}
					}
				} else {
					if(lastBatsmanOnStrike == battingCard.getPlayerId()) {
						this_animation.processAnimation(Constants.FRONT, print_writers, "OnStrike$Strike_Change", "SHOW 0.360");
						this_animation.processAnimation(Constants.FRONT, print_writers, "OnStrike$In_Out", "CONTINUE");					
						lastBatsmanOnStrike = 0;
					}
				}
			}		
			
		}

		return Constants.OK;
	}
	
	public void populateTwoBatsmenSingleBatsman(List<PrintWriter> print_writers, MatchAllData matchAllData, int WhichSide, int WhichSubSide, int WhichBatsman, 
		List<BattingCard> battingCardList, int SubSide) throws InterruptedException {
		
		switch(config.getBroadcaster()) {
		case Constants.AFG_SL_SERIES:
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$BatttingGrp$Batter" + WhichBatsman + "$Side" + WhichSide 
					+ "$txt_BatterName*GEOM*TEXT SET " + battingCardList.get(WhichBatsman-1).getPlayer().getTicker_name() + "\0", print_writers);
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$BatttingGrp$Batter" + WhichBatsman + "$Side" + WhichSide 
					+ "$txt_Runs*GEOM*TEXT SET " + battingCardList.get(WhichBatsman-1).getRuns() + "\0", print_writers);
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$BatttingGrp$Batter" + WhichBatsman + "$Side" + WhichSide 
					+ "$txt_Balls*GEOM*TEXT SET " + battingCardList.get(WhichBatsman-1).getBalls() + "\0", print_writers);
			
			if(battingCardList.get(WhichBatsman-1).getOnStrike() != null) {
				if(battingCardList.get(WhichBatsman-1).getOnStrike().equalsIgnoreCase(CricketUtil.YES)) {
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$BatttingGrp$Batter" + WhichBatsman + "$Side" + WhichSide 
							+ "$StrikeIcon$select_Strike*FUNCTION*Omo*vis_con SET 1\0", print_writers);
				}else {
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$BatttingGrp$Batter" + WhichBatsman + "$Side" + WhichSide 
							+ "$StrikeIcon$select_Strike*FUNCTION*Omo*vis_con SET 0\0", print_writers);
				}
			}else {
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$BatttingGrp$Batter" + WhichBatsman + "$Side" + WhichSide 
						+ "$StrikeIcon$select_Strike*FUNCTION*Omo*vis_con SET 0\0", print_writers);
			}
			
			if((WhichBatsman == 1 && battingCardList.get(0).getStatus().equalsIgnoreCase(CricketUtil.NOT_OUT)) || 
					(WhichBatsman == 2 && battingCardList.get(1).getStatus().equalsIgnoreCase(CricketUtil.NOT_OUT))) {
				this_animation.processAnimation(Constants.FRONT, print_writers, "Batsmen_Lowlight$" + WhichBatsman, "SHOW 0.0");
			} else {
				this_animation.processAnimation(Constants.FRONT, print_writers, "Batsmen_Lowlight$" + WhichBatsman, "SHOW 0.400");
			}
			break;
		case Constants.ACC:
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$BatsmanGrp$batsman$Row" + WhichBatsman
					+ "$Batsman1*GEOM*TEXT SET " + battingCardList.get(WhichBatsman-1).getPlayer().getTicker_name() + "\0", print_writers);
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$BatsmanGrp$batsman$Row" + WhichBatsman
					+ "$Batsman1ScoreGrp$Runs*GEOM*TEXT SET " + battingCardList.get(WhichBatsman-1).getRuns() + "\0", print_writers);
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$BatsmanGrp$batsman$Row" + WhichBatsman
					+ "$Batsman1ScoreGrp$Balls*GEOM*TEXT SET " + battingCardList.get(WhichBatsman-1).getBalls() + "\0", print_writers);
			
			if(WhichBatsman == 1 && battingCardList.get(0).getOnStrike() != null) {
				if(battingCardList.get(0).getOnStrike().equalsIgnoreCase(CricketUtil.YES)) {
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$BatsmanGrp$batsman$OnStrikeGrp$img_Text"
							+ "*FUNCTION*Omo*vis_con SET 0\0", print_writers);
				}else {
//					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Right$Side_" + WhichSide 
//							+ infobar.getBatsmanAndBowlOrSponsor() + "$Bat_" + WhichBatsman + "$Side" + WhichSubSide + "$OnStrike*ACTIVE SET 0 \0",print_writers);
				}
				
			}else if(WhichBatsman == 2 && battingCardList.get(1).getOnStrike() != null) {
				
				if(battingCardList.get(1).getOnStrike().equalsIgnoreCase(CricketUtil.YES)) {
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$BatsmanGrp$batsman$OnStrikeGrp$img_Text"
							+ "*FUNCTION*Omo*vis_con SET 1\0", print_writers);
				}else {
//					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Right$Side_" + WhichSide 
//							+ infobar.getBatsmanAndBowlOrSponsor() + "$Bat_" + WhichBatsman + "$Side" + WhichSubSide + "$OnStrike*ACTIVE SET 0 \0",print_writers);
				}
				
			}else {
//				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Right$Side_" + WhichSide 
//						+ infobar.getBatsmanAndBowlOrSponsor() + "$Bat_" + WhichBatsman + "$Side" + WhichSubSide + "$OnStrike*ACTIVE SET 0 \0",print_writers);
			}
			
			if((WhichBatsman == 1 && battingCardList.get(0).getStatus().equalsIgnoreCase(CricketUtil.NOT_OUT)) || 
					(WhichBatsman == 2 && battingCardList.get(1).getStatus().equalsIgnoreCase(CricketUtil.NOT_OUT))) {
				this_animation.processAnimation(Constants.FRONT, print_writers, "Anim_Infobar$Bat" + WhichBatsman + "DelhighlightIn", "SHOW 0.0");
			} else {
				this_animation.processAnimation(Constants.FRONT, print_writers, "Anim_Infobar$Bat" + WhichBatsman + "DelhighlightIn", "SHOW 0.400");
			}
			
			break;
			
		case Constants.TRI_SERIES:  case Constants.MT20: case Constants.TG20:
			switch(config.getBroadcaster()) {
			case Constants.TRI_SERIES:  case Constants.MT20:
				if(BatterTickerName) {
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$LeftSection$BatsmanGrp_" + WhichBatsman + "$Side" + WhichSide 
						+ "$Name$Side" + WhichSubSide + "$select_NameType*FUNCTION*Omo*vis_con SET 0\0", print_writers);	
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$LeftSection$BatsmanGrp_" + WhichBatsman + "$Side" + WhichSide 
						+ "$Name$Side" + WhichSubSide + "$TickerName$txt_Name*GEOM*TEXT SET " + battingCardList.get(WhichBatsman-1).getPlayer().getTicker_name() + "\0", print_writers);
				}else {
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$LeftSection$BatsmanGrp_" + WhichBatsman + "$Side" + WhichSide 
						+ "$Name$Side" + WhichSubSide + "$select_NameType*FUNCTION*Omo*vis_con SET 1\0", print_writers);	
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$LeftSection$BatsmanGrp_" + WhichBatsman + "$Side" + WhichSide 
						+ "$Name$Side" + WhichSubSide + "$FullName$txt_FirstName*GEOM*TEXT SET \0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$LeftSection$BatsmanGrp_" + WhichBatsman + "$Side" + WhichSide 
						+ "$Name$Side" + WhichSubSide + "$FullName$txt_LastName*GEOM*TEXT SET " + (battingCardList.get(WhichBatsman-1).getPlayer().getTicker_name() != null ?
						battingCardList.get(WhichBatsman-1).getPlayer().getTicker_name():"") + "\0", print_writers);
				}
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$LeftSection$BatsmanGrp_" + WhichBatsman + "$Base$ScaleForSection2" 
						+ "$img_Base1*TEXTURE*IMAGE SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_BASE1 : Constants.MT20_BASE1) 
						+ inning.getBatting_team().getTeamBadge() + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$LeftSection$BatsmanGrp_" + WhichBatsman + "$Side" + WhichSide 
						+ "$Name$img_Text1*TEXTURE*IMAGE SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_TEXT1 : Constants.MT20_TEXT1) 
						+ inning.getBatting_team().getTeamBadge() + "\0", print_writers);
				break;
			case Constants.TG20:
				if(BatterTickerName) {
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$LeftSection$BatsmanGrp_" + WhichBatsman + "$Side" + WhichSide 
						+ "$Name$Side" + WhichSubSide + "$select_NameType*FUNCTION*Omo*vis_con SET 0\0", print_writers);
					
					CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$LeftSection$BatsmanGrp_" + WhichBatsman + "$Side" + WhichSide 
							+ "$Name$Side" + WhichSubSide + "$TickerName$select_Language*FUNCTION*Omo*vis_con SET ", config, Constants.TG20, print_writers, foreignLanguageOmo);
					foreignLanguageData = CricketFunctions.AssembleMultiLanguageData(CricketUtil.PLAYER, CricketUtil.TICKERNAME, multilanguagedata, 
							battingCardList.get(WhichBatsman-1).getPlayer().getTicker_name().trim(), "", null, 0, foreignLanguageDataList);
					CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$LeftSection$BatsmanGrp_" + WhichBatsman + "$Side" + WhichSide 
							+ "$Name$Side" + WhichSubSide + "$TickerName$English$txt_Name*GEOM*TEXT SET ", config, Constants.TG20, print_writers, foreignLanguageData);

//					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$LeftSection$BatsmanGrp_" + WhichBatsman + "$Side" + WhichSide 
//						+ "$Name$Side" + WhichSubSide + "$TickerName$txt_Name*GEOM*TEXT SET " + battingCardList.get(WhichBatsman-1).getPlayer().getTicker_name() + "\0", print_writers);
				}else {
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$LeftSection$BatsmanGrp_" + WhichBatsman + "$Side" + WhichSide 
						+ "$Name$Side" + WhichSubSide + "$select_NameType*FUNCTION*Omo*vis_con SET 1\0", print_writers);
					
					CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$LeftSection$BatsmanGrp_" + WhichBatsman + "$Side" + WhichSide 
							+ "$Name$Side" + WhichSubSide + "$FullName$select_Language*FUNCTION*Omo*vis_con SET ", config, Constants.TG20, print_writers, foreignLanguageOmo);
					
					//FirstName
					foreignLanguageData = CricketFunctions.AssembleMultiLanguageData(CricketUtil.PLAYER, CricketUtil.TICKERNAME, multilanguagedata, "", "", null, 0, foreignLanguageDataList);
					CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$LeftSection$BatsmanGrp_" + WhichBatsman + "$Side" + WhichSide 
							+ "$Name$Side" + WhichSubSide + "$FullName$English$txt_FirstName*GEOM*TEXT SET ", config, Constants.TG20, print_writers, foreignLanguageData);
					//LastNAme
					foreignLanguageData = CricketFunctions.AssembleMultiLanguageData(CricketUtil.PLAYER, CricketUtil.TICKERNAME, multilanguagedata, 
							battingCardList.get(WhichBatsman-1).getPlayer().getTicker_name().trim(), "", null, 0, foreignLanguageDataList);
					CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$LeftSection$BatsmanGrp_" + WhichBatsman + "$Side" + WhichSide 
							+ "$Name$Side" + WhichSubSide + "$FullName$English$txt_LastName*GEOM*TEXT SET ", config, Constants.TG20, print_writers, foreignLanguageData);
					
//					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$LeftSection$BatsmanGrp_" + WhichBatsman + "$Side" + WhichSide 
//						+ "$Name$Side" + WhichSubSide + "$FullName$txt_FirstName*GEOM*TEXT SET \0", print_writers);
//					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$LeftSection$BatsmanGrp_" + WhichBatsman + "$Side" + WhichSide 
//						+ "$Name$Side" + WhichSubSide + "$FullName$txt_LastName*GEOM*TEXT SET " + (battingCardList.get(WhichBatsman-1).getPlayer().getTicker_name() != null ?
//						battingCardList.get(WhichBatsman-1).getPlayer().getTicker_name():"") + "\0", print_writers);
				}
				break;
			}
			
			if(WhichSide == 2) {
				BatsmanGeomData(print_writers, matchAllData, WhichSide, WhichBatsman);
			}else {
				if(config.getSecondaryBroadcaster().equalsIgnoreCase("without_script")) {
					BatsmanGeomData(print_writers, matchAllData, WhichSide, WhichBatsman);
				} else {
					BatsmanRunsAndBallsAnim(print_writers, matchAllData, WhichSide, WhichBatsman, battingCardList, SubSide);
				}
			}
			
			// Inside your method:
			currentOnStrike = battingCardList.get(WhichBatsman - 1).getOnStrike();
			boolean isOnStrike = CricketUtil.YES.equalsIgnoreCase(currentOnStrike);

			int strikeValue = isOnStrike ? 1 : 0;
			String strikeShow = isOnStrike ? "START" : "CONTINUE REVERSE";

			// Always update Viz value
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$LeftSection$BatsmanGrp_" + WhichBatsman + "$Side" + WhichSubSide
			    + "$select_Strike*FUNCTION*Omo*vis_con SET " + strikeValue + "\0",print_writers);

			// Check previous state before playing animation
			previousOnStrike = previousOnStrikeMap.getOrDefault(WhichBatsman, null);
			if (previousOnStrike == null || !previousOnStrike.equalsIgnoreCase(currentOnStrike)) {
			    // Only play animation if changed
			    this_animation.processAnimation(Constants.FRONT, print_writers, "BatsmanStrike$" + WhichBatsman, strikeShow);
			}

			// Update previous state
			previousOnStrikeMap.put(WhichBatsman, currentOnStrike);

			if((WhichBatsman == 1 && battingCardList.get(0).getStatus().equalsIgnoreCase(CricketUtil.NOT_OUT)) || 
					(WhichBatsman == 2 && battingCardList.get(1).getStatus().equalsIgnoreCase(CricketUtil.NOT_OUT))) {
				this_animation.processAnimation(Constants.FRONT, print_writers, "Batsmen_Lowlight$" + WhichBatsman, "SHOW 0.0");
			} else {
				this_animation.processAnimation(Constants.FRONT, print_writers, "Batsmen_Lowlight$" + WhichBatsman, "SHOW 0.600");
			}
			
			break;
			
		case Constants.BCCI:
			CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$NormalAll$BatterGrp$Batter" + WhichBatsman + "$Side" 
					+ WhichSubSide + "$Name$select_Language*FUNCTION*Omo*vis_con SET ", config, Constants.BCCI, print_writers, foreignLanguageOmo);
			foreignLanguageData = CricketFunctions.AssembleMultiLanguageData(CricketUtil.PLAYER, CricketUtil.TICKERNAME, multilanguagedata, 
					battingCardList.get(WhichBatsman-1).getPlayer().getTicker_name(), "", null, 0, foreignLanguageDataList);
			CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$NormalAll$BatterGrp$Batter" + WhichBatsman + "$Side" 
					+ WhichSubSide + "$Name$English$txt_BatterName*GEOM*TEXT SET ", config, Constants.BCCI, print_writers, foreignLanguageData);
			
//			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$NormalAll$BatterGrp$Batter" + WhichBatsman + "$Side" + WhichSubSide + 
//					"$Name$select_Language*FUNCTION*Omo*vis_con SET 1\0", print_writers);
//			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$NormalAll$BatterGrp$Batter" + WhichBatsman + "$Side" + WhichSubSide + 
//					"$Name$English$txt_BatterName*GEOM*TEXT SET " + battingCardList.get(WhichBatsman-1).getPlayer().getTicker_name() + "\0", print_writers);
			
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$NormalAll$BatterGrp$Batter" + WhichBatsman + "$Side" + WhichSubSide + 
					"$Score$txt_Runs*GEOM*TEXT SET " + battingCardList.get(WhichBatsman-1).getRuns() + "\0", print_writers);
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$NormalAll$BatterGrp$Batter" + WhichBatsman + "$Side" + WhichSubSide + 
					"$Score$txt_Balls*GEOM*TEXT SET " + battingCardList.get(WhichBatsman-1).getBalls() + "\0", print_writers);
			
			if((WhichBatsman == 1 && battingCardList.get(0).getOnStrike().equalsIgnoreCase(CricketUtil.YES))
					||(WhichBatsman == 2 && battingCardList.get(1).getOnStrike().equalsIgnoreCase(CricketUtil.YES))) {
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$NormalAll$BatterGrp$Batter" + WhichBatsman + "$Side" + WhichSubSide + 
						"$select_Strike*FUNCTION*Omo*vis_con SET 1\0", print_writers);
			} else {
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$NormalAll$BatterGrp$Batter" + WhichBatsman + "$Side" + WhichSubSide + 
						"$select_Strike*FUNCTION*Omo*vis_con SET 0\0", print_writers);
			}
			
			if((WhichBatsman == 1 && battingCardList.get(0).getStatus().equalsIgnoreCase(CricketUtil.NOT_OUT)) || 
					(WhichBatsman == 2 && battingCardList.get(1).getStatus().equalsIgnoreCase(CricketUtil.NOT_OUT))) {
				this_animation.processAnimation(Constants.FRONT, print_writers, "Batter_Lowlight$" + WhichBatsman, "SHOW 0.0");
			} else {
				this_animation.processAnimation(Constants.FRONT, print_writers, "Batter_Lowlight$" + WhichBatsman, "SHOW 0.500");
			}
			
			break;
		}
	}
	public void BatsmanRunsAndBallsAnim(List<PrintWriter> print_writers, MatchAllData matchAllData, int WhichSide, int WhichBatsman, 
			List<BattingCard> battingCardList, int SubSide) throws InterruptedException {
		switch(config.getBroadcaster()) {
		case Constants.TRI_SERIES:  case Constants.MT20:
			if(SubSide == 2) {
				boolean runsChanged1 = batter1PreviousRuns != battingCardList.get(0).getRuns();
				boolean ballsChanged1 = batter1PreviousBall != battingCardList.get(0).getBalls();
				boolean runsChanged2 = batter2PreviousRuns != battingCardList.get(1).getRuns();
				boolean ballsChanged2 = batter2PreviousBall != battingCardList.get(1).getBalls();

				if (runsChanged1 || ballsChanged1) {
				    if(runsChanged1) {
				    	CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$LeftSection$BatsmanGrp_1$Side" + WhichSide 
								+ "$ScoreGrp$Runs*SCRIPT*INSTANCE*StartValue SET " + batter1PreviousRuns + "\0", print_writers);
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$LeftSection$BatsmanGrp_1$Side" + WhichSide 
								+ "$ScoreGrp$Runs*SCRIPT*INSTANCE*EndValue SET " + battingCardList.get(0).getRuns() + "\0", print_writers);
				    }else {
				    	CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$LeftSection$BatsmanGrp_1$Side" + WhichSide 
								+ "$ScoreGrp$Runs*SCRIPT*INSTANCE*StartValue SET " + batter1PreviousRuns + "\0", print_writers);
				    }
				    if(ballsChanged1) {
				    	CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$LeftSection$BatsmanGrp_1$Side" + WhichSide 
								+ "$ScoreGrp$Balls*SCRIPT*INSTANCE*StartValue SET " + batter1PreviousBall + "\0", print_writers);
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$LeftSection$BatsmanGrp_1$Side" + WhichSide 
								+ "$ScoreGrp$Balls*SCRIPT*INSTANCE*EndValue SET " + battingCardList.get(0).getBalls() + "\0", print_writers);
				    }else {
				    	CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$LeftSection$BatsmanGrp_1$Side" + WhichSide 
								+ "$ScoreGrp$Balls*SCRIPT*INSTANCE*StartValue SET " + batter1PreviousBall + "\0", print_writers);
				    }
				    
				    CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$LeftSection$BatsmanGrp_1$Side" + WhichSide 
							+ "$ScoreGrp$Runs*SCRIPT*INSTANCE*StartLoopBtn INVOKE\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$LeftSection$BatsmanGrp_1$Side" + WhichSide 
							+ "$ScoreGrp$Balls*SCRIPT*INSTANCE*StartLoopBtn INVOKE\0", print_writers);
				}
				
				if (runsChanged2 || ballsChanged2) {
				    if(runsChanged2) {
				    	CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$LeftSection$BatsmanGrp_2$Side" + WhichSide 
								+ "$ScoreGrp$Runs*SCRIPT*INSTANCE*StartValue SET " + batter2PreviousRuns + "\0", print_writers);
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$LeftSection$BatsmanGrp_2$Side" + WhichSide 
								+ "$ScoreGrp$Runs*SCRIPT*INSTANCE*EndValue SET " + battingCardList.get(1).getRuns() + "\0", print_writers);
				    }else {
				    	CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$LeftSection$BatsmanGrp_2$Side" + WhichSide 
								+ "$ScoreGrp$Runs*SCRIPT*INSTANCE*StartValue SET " + batter2PreviousRuns + "\0", print_writers);
				    }
				    if(ballsChanged2) {
				    	CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$LeftSection$BatsmanGrp_2$Side" + WhichSide 
								+ "$ScoreGrp$Balls*SCRIPT*INSTANCE*StartValue SET " + batter2PreviousBall + "\0", print_writers);
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$LeftSection$BatsmanGrp_2$Side" + WhichSide 
								+ "$ScoreGrp$Balls*SCRIPT*INSTANCE*EndValue SET " + battingCardList.get(1).getBalls() + "\0", print_writers);
				    }else {
				    	CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$LeftSection$BatsmanGrp_2$Side" + WhichSide 
								+ "$ScoreGrp$Balls*SCRIPT*INSTANCE*StartValue SET " + batter2PreviousBall + "\0", print_writers);
				    }
				    
				    CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$LeftSection$BatsmanGrp_2$Side" + WhichSide 
							+ "$ScoreGrp$Runs*SCRIPT*INSTANCE*StartLoopBtn INVOKE\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$LeftSection$BatsmanGrp_2$Side" + WhichSide 
							+ "$ScoreGrp$Balls*SCRIPT*INSTANCE*StartLoopBtn INVOKE\0", print_writers);
				}
				
			    // update your previous trackers
				if(WhichBatsman == 1) {
					batter1PreviousRuns = battingCardList.get(0).getRuns();
					batter1PreviousBall = battingCardList.get(0).getBalls();
				}else if(WhichBatsman == 2) {
					batter2PreviousRuns = battingCardList.get(1).getRuns();
					batter2PreviousBall = battingCardList.get(1).getBalls();
				}
				
			}else if(SubSide == 1) {
				
				if ((WhichBatsman == 1 && battingCardList.get(0).getOnStrike() != null && battingCardList.get(0).getOnStrike().equalsIgnoreCase(CricketUtil.YES)) ||
					    (WhichBatsman == 2 && battingCardList.get(1).getOnStrike() != null && battingCardList.get(1).getOnStrike().equalsIgnoreCase(CricketUtil.YES))) {
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$LeftSection$BatsmanGrp_" + WhichBatsman + "$Side" 
							+ WhichSide + "$select_Strike*FUNCTION*Omo*vis_con SET 1\0", print_writers);
					this_animation.processAnimation(Constants.FRONT, print_writers, "BatsmanStrike$" + WhichBatsman, "SHOW 0.300");
				}else {
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$LeftSection$BatsmanGrp_" + WhichBatsman + "$Side" 
							+ WhichSide + "$select_Strike*FUNCTION*Omo*vis_con SET 0\0", print_writers);
					this_animation.processAnimation(Constants.FRONT, print_writers, "BatsmanStrike$" + WhichBatsman, "SHOW 0.0");
				}

				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$LeftSection$BatsmanGrp_1$Side" + WhichSide 
						+ "$ScoreGrp$Runs*SCRIPT*INSTANCE*StartValue SET " + battingCardList.get(0).getRuns() + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$LeftSection$BatsmanGrp_1$Side" + WhichSide 
						+ "$ScoreGrp$Runs*SCRIPT*INSTANCE*EndValue SET " + battingCardList.get(0).getRuns() + "\0", print_writers);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$LeftSection$BatsmanGrp_1$Side" + WhichSide 
						+ "$ScoreGrp$Balls*SCRIPT*INSTANCE*StartValue SET " + battingCardList.get(0).getBalls() + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$LeftSection$BatsmanGrp_1$Side" + WhichSide 
						+ "$ScoreGrp$Balls*SCRIPT*INSTANCE*EndValue SET " + battingCardList.get(0).getBalls() + "\0", print_writers);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$LeftSection$BatsmanGrp_2$Side" + WhichSide 
						+ "$ScoreGrp$Runs*SCRIPT*INSTANCE*StartValue SET " + battingCardList.get(1).getRuns() + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$LeftSection$BatsmanGrp_2$Side" + WhichSide 
						+ "$ScoreGrp$Runs*SCRIPT*INSTANCE*EndValue SET " + battingCardList.get(1).getRuns() + "\0", print_writers);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$LeftSection$BatsmanGrp_2$Side" + WhichSide 
						+ "$ScoreGrp$Balls*SCRIPT*INSTANCE*StartValue SET " + battingCardList.get(1).getBalls() + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$LeftSection$BatsmanGrp_2$Side" + WhichSide 
						+ "$ScoreGrp$Balls*SCRIPT*INSTANCE*EndValue SET " + battingCardList.get(1).getBalls() + "\0", print_writers);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$LeftSection$BatsmanGrp_" + WhichBatsman + "$Side" + WhichSide 
						+ "$ScoreGrp$Runs*SCRIPT*INSTANCE*StartLoopBtn INVOKE\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$LeftSection$BatsmanGrp_" + WhichBatsman + "$Side" + WhichSide 
						+ "$ScoreGrp$Balls*SCRIPT*INSTANCE*StartLoopBtn INVOKE\0", print_writers);
				
				if(WhichBatsman == 1) {
					batter1PreviousRuns = battingCardList.get(0).getRuns();
					batter1PreviousBall = battingCardList.get(0).getBalls();
				}else if(WhichBatsman == 2) {
					batter2PreviousRuns = battingCardList.get(1).getRuns();
					batter2PreviousBall = battingCardList.get(1).getBalls();
				}
			}
			break;
		}
	}
	public void BatsmanGeomData(List<PrintWriter> print_writers, MatchAllData matchAllData, int WhichSide, int WhichBatsman) throws InterruptedException {
		
		switch(config.getBroadcaster()) {
		case Constants.TRI_SERIES:  case Constants.MT20: case Constants.TG20:
			
			this_data_str = new ArrayList<String>();
			this_data_str.add(CricketFunctions.hundredsTensUnitsTeamScore(String.valueOf(battingCardList.get(WhichBatsman - 1).getRuns())));
			this_data_str.add(CricketFunctions.hundredsTensUnitsTeamScore(String.valueOf(battingCardList.get(WhichBatsman - 1).getBalls())));
			
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$LeftSection$BatsmanGrp_" + WhichBatsman + "$Side" + WhichSide 
					+ "$ScoreGrp$Runs$Hundreths$Side1$txt_Runs*GEOM*TEXT SET " + this_data_str.get(0).split(",")[0] + "\0", print_writers);
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$LeftSection$BatsmanGrp_" + WhichBatsman + "$Side" + WhichSide 
					+ "$ScoreGrp$Runs$Tenths$Side1$txt_Runs*GEOM*TEXT SET " + this_data_str.get(0).split(",")[1] + "\0", print_writers);
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$LeftSection$BatsmanGrp_" + WhichBatsman + "$Side" + WhichSide 
					+ "$ScoreGrp$Runs$Units$Side1$txt_Runs*GEOM*TEXT SET " + this_data_str.get(0).split(",")[2] + "\0", print_writers);
			
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$LeftSection$BatsmanGrp_" + WhichBatsman + "$Side" + WhichSide 
					+ "$ScoreGrp$Balls$Hundreths$Side1$txt_Balls*GEOM*TEXT SET " + this_data_str.get(1).split(",")[0] + "\0", print_writers);
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$LeftSection$BatsmanGrp_" + WhichBatsman + "$Side" + WhichSide 
					+ "$ScoreGrp$Balls$Tenths$Side1$txt_Balls*GEOM*TEXT SET " + this_data_str.get(1).split(",")[1] + "\0", print_writers);
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$LeftSection$BatsmanGrp_" + WhichBatsman + "$Side" + WhichSide 
					+ "$ScoreGrp$Balls$Units$Side1$txt_Balls*GEOM*TEXT SET " + this_data_str.get(1).split(",")[2] + "\0", print_writers);
			
			if(WhichBatsman == 1) {
				batter1PreviousRuns = battingCardList.get(0).getRuns();
				batter1PreviousBall = battingCardList.get(0).getBalls();
			}else if(WhichBatsman == 2) {
				batter2PreviousRuns = battingCardList.get(1).getRuns();
				batter2PreviousBall = battingCardList.get(1).getBalls();
			}
			break;
		}
	}

	public String populateInfobarBowler(int whichSide, int whichSubSide, List<PrintWriter> print_writers, MatchAllData matchAllData) throws InterruptedException, IOException {

		bowlingCard = inning.getBowlingCard().stream().filter(boc -> boc.getStatus().toUpperCase().equalsIgnoreCase(CricketUtil.CURRENT+CricketUtil.BOWLER)
			|| boc.getStatus().toUpperCase().equalsIgnoreCase(CricketUtil.LAST+CricketUtil.BOWLER)).findAny().orElse(null);
		
		if(bowlingCard == null) {
			return "populateInfobarBowler: no current bowler found";
		}
		switch(config.getBroadcaster()) {
		case Constants.AFG_SL_SERIES:
			if(infobar.getLast_bowler() != null && infobar.getLast_bowler().getPlayerId() != bowlingCard.getPlayerId()) {
				populateBowlersData(false, print_writers, 2);
				populateSection3(print_writers, matchAllData, 2);
				
				if(infobar.getInfobar_status().equalsIgnoreCase("INFOBAR")) {
					this_animation.processAnimation(Constants.FRONT, print_writers, "Bowler_Change", "START");
					this_animation.processAnimation(Constants.FRONT, print_writers, "Section3_Change", "START");
					TimeUnit.MILLISECONDS.sleep(500);
				}
				
				populateBowlersData(false, print_writers, 1);
				populateSection3(print_writers, matchAllData, 1);
				
				if(infobar.getInfobar_status().equalsIgnoreCase("INFOBAR")) {
					TimeUnit.MILLISECONDS.sleep(1000);
					this_animation.processAnimation(Constants.FRONT, print_writers, "Bowler_Change", "SHOW 0.0");
					this_animation.processAnimation(Constants.FRONT, print_writers, "Section3_Change", "SHOW 0.0");
				}
				
				isimpactBowlIn = false;
			} else {
				populateBowlersData(false, print_writers, 1);
				populateSection3(print_writers, matchAllData, 1);
			}
			infobar.setLast_bowler(bowlingCard);
			break;
		case Constants.ACC:
			if(infobar.getLast_bowler() != null && infobar.getLast_bowler().getPlayerId() != bowlingCard.getPlayerId()) {
				populateBowlersData(false, print_writers, 2);
				populateSection3(print_writers, matchAllData, 2);
				
				if(infobar.getInfobar_status().equalsIgnoreCase("INFOBAR")) {
					this_animation.processAnimation(Constants.FRONT, print_writers, "Anim_Infobar$Bowler$Change", "START");
					this_animation.processAnimation(Constants.FRONT, print_writers, "Anim_Infobar$Section3$Change", "START");
					TimeUnit.MILLISECONDS.sleep(500);
				}
				
				populateBowlersData(false, print_writers, 1);
				populateSection3(print_writers, matchAllData, 1);
				
				if(infobar.getInfobar_status().equalsIgnoreCase("INFOBAR")) {
					TimeUnit.MILLISECONDS.sleep(1000);
					this_animation.processAnimation(Constants.FRONT, print_writers, "Anim_Infobar$Bowler$Change", "SHOW 0.0");
					this_animation.processAnimation(Constants.FRONT, print_writers, "Anim_Infobar$Section3$Change", "SHOW 0.0");
				}
				
				isimpactBowlIn = false;
			} else {
				populateBowlersData(false, print_writers, 1);
				populateSection3(print_writers, matchAllData, 1);
			}
			infobar.setLast_bowler(bowlingCard);
			break;
		case Constants.BAN_AFG_SERIES:
			if(infobar.getLast_bowler() != null && infobar.getLast_bowler().getPlayerId() != bowlingCard.getPlayerId()) {
				populateBowlersData(false, print_writers, 2);
				this_animation.processAnimation(Constants.FRONT, print_writers, "Anim_Infobar$Bowler$Change", "START");
				TimeUnit.MILLISECONDS.sleep(800);
				populateBowlersData(false, print_writers, 1);
				this_animation.processAnimation(Constants.FRONT, print_writers, "Anim_Infobar$Bowler$Change", "SHOW 0.0");
			} else {
				populateBowlersData(false, print_writers, 1);
			}
			infobar.setLast_bowler(bowlingCard);
			break;
		case Constants.TRI_SERIES:  case Constants.MT20: case Constants.TG20:
			if(infobar.getLast_bowler() != null && infobar.getLast_bowler().getPlayerId() != bowlingCard.getPlayerId()) {
				bowlerPreviousRuns = bowlingCard.getRuns();
			    bowlerPreviousWickets = bowlingCard.getWickets();
			    bowlerPreviousOvers = bowlingCard.getOvers();
			    bowlerPreviousBalls   = bowlingCard.getBalls();
				populateBowlersData(print_writers, 2, 1, "");
				populateSection3(print_writers, matchAllData, 2);
				//populateRightTopBowler(print_writers, matchAllData, 2, 1, 1);
				//populateVizInfobarRightBottom(print_writers, matchAllData, 2, 1);
				this_animation.processAnimation(Constants.FRONT, print_writers, "Bowler_Change", "START");
				this_animation.processAnimation(Constants.FRONT, print_writers, "Section_3_Change", "START");
				TimeUnit.MILLISECONDS.sleep(500);
				populateBowlersData(print_writers, 1, 1, "");
				populateSection3(print_writers, matchAllData, 1);
				//populateRightTopBowler(print_writers, matchAllData, 1, 1, 1);
				//populateVizInfobarRightBottom(print_writers, matchAllData, 1, 1);
				TimeUnit.MILLISECONDS.sleep(1000);
				this_animation.processAnimation(Constants.FRONT, print_writers, "Bowler_Change", "SHOW 0.0");
				this_animation.processAnimation(Constants.FRONT, print_writers, "Section_3_Change", "SHOW 0.0");
				isimpactBowlIn = false;
			} else {
				populateBowlersData(print_writers, 1, 1, Constants.SCRIPT);
				populateSection3(print_writers, matchAllData, 1);
				//populateRightTopBowler(print_writers, matchAllData, 1, 1, 1);
				//populateVizInfobarRightBottom(print_writers, matchAllData, 1, 1);
			}
			infobar.setLast_bowler(bowlingCard);
			break;
		case Constants.BCCI:
			if(infobar.getLast_bowler() != null) {
				if(infobar.getLast_bowler().getPlayerId() != bowlingCard.getPlayerId()) {
					populateRightTopBowler(print_writers, matchAllData, 2, 1, 1);
					populateVizInfobarRightBottom(print_writers, matchAllData, 2, 1);
					this_animation.processAnimation(Constants.FRONT, print_writers, "anim_Change$Bowler", "START");
					this_animation.processAnimation(Constants.FRONT, print_writers, "anim_Change$Section4", "START");
					TimeUnit.MILLISECONDS.sleep(500);
					populateRightTopBowler(print_writers, matchAllData, 1, 1, 1);
					populateVizInfobarRightBottom(print_writers, matchAllData, 1, 1);
					TimeUnit.MILLISECONDS.sleep(1000);
					this_animation.processAnimation(Constants.FRONT, print_writers, "anim_Change$Bowler", "SHOW 0.0");
					this_animation.processAnimation(Constants.FRONT, print_writers, "anim_Change$Section4", "SHOW 0.0");
					
					isimpactBowlIn = false;
				} else {
					populateRightTopBowler(print_writers, matchAllData, 1, 1, 1);
					populateRightTopBowler(print_writers, matchAllData, 2, 1, 1);
					populateVizInfobarRightBottom(print_writers, matchAllData, 1, 1);
					populateVizInfobarRightBottom(print_writers, matchAllData, 2, 1);
				}
			} else {
				populateRightTopBowler(print_writers, matchAllData, 1, 1, 1);
				populateRightTopBowler(print_writers, matchAllData, 2, 1, 1);
				populateVizInfobarRightBottom(print_writers, matchAllData, 1, 1);
				populateVizInfobarRightBottom(print_writers, matchAllData, 2, 1);
			}
			
			infobar.setLast_bowler(bowlingCard);
			break;
		}
		return Constants.OK;
	}
	
	public void populateRightTopBowler(List<PrintWriter> print_writers, MatchAllData matchAllData,int WhichSide, int WhichSubSide, int SubSide) throws InterruptedException {
		switch(config.getBroadcaster()) {
		case Constants.BCCI:
			CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$NormalAll$OutForSection5$BowlerNameGrp$Side" + WhichSide + 
					"$Name$select_Language*FUNCTION*Omo*vis_con SET ", config, Constants.BCCI, print_writers, foreignLanguageOmo);
			foreignLanguageData = CricketFunctions.AssembleMultiLanguageData(CricketUtil.PLAYER, CricketUtil.TICKERNAME, multilanguagedata, 
					bowlingCard.getPlayer().getTicker_name(), "", null, 0, foreignLanguageDataList);
			CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$NormalAll$OutForSection5$BowlerNameGrp$Side" + WhichSide + 
					"$Name$English$txt_BowlerName*GEOM*TEXT SET ", config, Constants.BCCI, print_writers, foreignLanguageData);
			
//			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$NormalAll$OutForSection5$BowlerNameGrp$Side" + WhichSide + 
//					"$Name$select_Language*FUNCTION*Omo*vis_con SET 1\0", print_writers);
//			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$NormalAll$OutForSection5$BowlerNameGrp$Side" + WhichSide + 
//					"$Name$English$txt_BowlerName*GEOM*TEXT SET " + bowlingCard.getPlayer().getTicker_name() + "\0", print_writers);
			
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$NormalAll$OutForSection5$BowlerNameGrp$Side" + WhichSide + 
					"$ScoreGrp$txt_Figures*GEOM*TEXT SET " + bowlingCard.getWickets() + slashOrDash + bowlingCard.getRuns() + "\0", print_writers);
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$NormalAll$OutForSection5$BowlerNameGrp$Side" + WhichSide + 
					"$ScoreGrp$txt_Overs*GEOM*TEXT SET " + CricketFunctions.OverBalls(bowlingCard.getOvers(), bowlingCard.getBalls()) + "\0", print_writers);
			
			if(bowlingCard.getStatus().equalsIgnoreCase(CricketUtil.CURRENT + CricketUtil.BOWLER)) {
				this_animation.processAnimation(Constants.FRONT, print_writers, "Bowler_Lowlight", "SHOW 0.0");
			} else {
				this_animation.processAnimation(Constants.FRONT, print_writers, "Bowler_Lowlight", "SHOW 0.500");
			}
			break;
		}
	}
	public void populateBowlersData(boolean isItUpdating, List<PrintWriter> print_writers, int whichSide) throws InterruptedException{
		
		switch(config.getBroadcaster()) {
		case Constants.AFG_SL_SERIES:
			if(isItUpdating == false) {
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$BowlingGrp$BowlerSection$Bowler$Side" + whichSide 
						+ "$Name*GEOM*TEXT SET " + bowlingCard.getPlayer().getTicker_name() + "\0", print_writers);
			}
			
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$BowlingGrp$BowlerSection$Bowler$Side" + whichSide 
					+ "$Runs*GEOM*TEXT SET " + bowlingCard.getWickets() + "-" + bowlingCard.getRuns() + "\0", print_writers);
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$BowlingGrp$BowlerSection$Bowler$Side" + whichSide 
					+ "$Balls*GEOM*TEXT SET " + CricketFunctions.OverBalls(bowlingCard.getOvers(), bowlingCard.getBalls()) + "\0", print_writers);
			
			if(bowlingCard.getStatus().equalsIgnoreCase(CricketUtil.CURRENT + CricketUtil.BOWLER)) {
				this_animation.processAnimation(Constants.FRONT, print_writers, "Bowler_Lowlight", "SHOW 0.0");
			} else {
				this_animation.processAnimation(Constants.FRONT, print_writers, "Bowler_Lowlight", "SHOW 0.340");
			}
			break;
		case Constants.ACC:
			if(isItUpdating == false) {
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$BowlerGrp$Side" + whichSide + 
						"$Bowler$Bowler*GEOM*TEXT SET " + bowlingCard.getPlayer().getTicker_name() + "\0", print_writers);
			}
			
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$BowlerGrp$Side" + whichSide + "$BowlFigure*GEOM*TEXT SET " 
					+ bowlingCard.getWickets() + "-" + bowlingCard.getRuns() + "\0", print_writers);
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$BowlerGrp$Side" + whichSide + "$Overs*GEOM*TEXT SET " 
					+ CricketFunctions.OverBalls(bowlingCard.getOvers(), bowlingCard.getBalls()) + "\0", print_writers);
			
			if(bowlingCard.getStatus().equalsIgnoreCase(CricketUtil.CURRENT + CricketUtil.BOWLER)) {
				this_animation.processAnimation(Constants.FRONT, print_writers, "Anim_Infobar$BowlDelhighlightIn", "SHOW 0.0");
			} else {
				this_animation.processAnimation(Constants.FRONT, print_writers, "Anim_Infobar$BowlDelhighlightIn", "SHOW 0.340");
			}
			break;
		default:
			if(isItUpdating == false) {
				if(config.getPrimaryIpAddress().equalsIgnoreCase(Constants.LOCALHOST)) {
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$BowlerGrp$Side" + whichSide + "$img_Player*TEXTURE*IMAGE SET " 
							+ Constants.BAN_AFG_SERIES_LOCAL_PHOTO_PATH + inning.getBowling_team().getTeamName4() + "\\\\" + Constants.RIGHT + "\\\\" + 
							bowlingCard.getPlayer().getPhoto()+CricketUtil.PNG_EXTENSION + "\0", print_writers);
				}else {
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$BowlerGrp$Side" + whichSide + "$img_Player*TEXTURE*IMAGE SET " 
							+ "\\\\" + config.getPrimaryIpAddress() + Constants.BAN_AFG_SERIES_PHOTO_PATH + inning.getBowling_team().getTeamName4() + "\\\\" 
							+ Constants.RIGHT + "\\\\" + bowlingCard.getPlayer().getPhoto()+CricketUtil.PNG_EXTENSION + "\0", print_writers);
				}
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$BowlerGrp$Side" + whichSide + 
						"$txt_Bowler*GEOM*TEXT SET " + bowlingCard.getPlayer().getTicker_name() + "\0", print_writers);
			}
			
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$BowlerGrp$Side" + whichSide + "$txt_Figures*GEOM*TEXT SET " 
					+ bowlingCard.getWickets() + "-" + bowlingCard.getRuns() + "\0", print_writers);
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$BowlerGrp$Side" + whichSide + "$txt_Overs*GEOM*TEXT SET " 
					+ CricketFunctions.OverBalls(bowlingCard.getOvers(), bowlingCard.getBalls()) + "\0", print_writers);
			
			if(bowlingCard.getStatus().equalsIgnoreCase(CricketUtil.CURRENT + CricketUtil.BOWLER)) {
				this_animation.processAnimation(Constants.FRONT, print_writers, "Anim_Infobar$BowlDelhighlightIn", "SHOW 0.0");
			} else {
				this_animation.processAnimation(Constants.FRONT, print_writers, "Anim_Infobar$BowlDelhighlightIn", "SHOW 0.340");
			}
			break;
		}
	}
	public void populateBowlersData(List<PrintWriter> print_writers, int WhichSide, int WhichSubSide, String additionalFeature) throws InterruptedException {
		switch(config.getBroadcaster()) {
		case Constants.TRI_SERIES:  case Constants.MT20: case Constants.TG20:
			switch(config.getBroadcaster()) {
			case Constants.TG20:
				if(infobar.getSection4() != null && !infobar.getSection4().isEmpty()) {
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$RightSection$BowlerGrp$Side" + WhichSide + "$Name$Side" 
						+ WhichSubSide + "$select_NameType*FUNCTION*Omo*vis_con SET 0\0", print_writers);
					
					CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$RightSection$BowlerGrp$Side" + WhichSide + "$Name$Side" 
							+ WhichSubSide + "$TickerName$select_Language*FUNCTION*Omo*vis_con SET ", config, Constants.TG20, print_writers, foreignLanguageOmo);
					//LastNAme
					foreignLanguageData = CricketFunctions.AssembleMultiLanguageData(CricketUtil.PLAYER, CricketUtil.TICKERNAME, multilanguagedata, bowlingCard.getPlayer().getTicker_name().trim(), 
							"", null, 0, foreignLanguageDataList);
					CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$RightSection$BowlerGrp$Side" + WhichSide + "$Name$Side" 
							+ WhichSubSide + "$TickerName$English$txt_Name*GEOM*TEXT SET ", config, Constants.TG20, print_writers, foreignLanguageData);
					
//					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$RightSection$BowlerGrp$Side" + WhichSide + "$Name$Side" 
//						+ WhichSubSide + "$TickerName$txt_Name*GEOM*TEXT SET " + bowlingCard.getPlayer().getTicker_name() + "\0", print_writers);
				}else {
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$RightSection$BowlerGrp$Side" + WhichSide + "$Name$Side" 
						+ WhichSubSide + "$select_NameType*FUNCTION*Omo*vis_con SET 1\0", print_writers);
					
					CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$RightSection$BowlerGrp$Side" + WhichSide + "$Name$Side" 
							+ WhichSubSide + "$FullName$select_Language*FUNCTION*Omo*vis_con SET ", config, Constants.TG20, print_writers, foreignLanguageOmo);
					
					//FirstName
					foreignLanguageData = CricketFunctions.AssembleMultiLanguageData(CricketUtil.PLAYER, CricketUtil.TICKERNAME, multilanguagedata, "", "", null, 0, foreignLanguageDataList);
					CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$RightSection$BowlerGrp$Side" + WhichSide + "$Name$Side" 
							+ WhichSubSide + "$FullName$English$txt_FirstName*GEOM*TEXT SET ", config, Constants.TG20, print_writers, foreignLanguageData);
					//LastNAme
					foreignLanguageData = CricketFunctions.AssembleMultiLanguageData(CricketUtil.PLAYER, CricketUtil.TICKERNAME, multilanguagedata, bowlingCard.getPlayer().getTicker_name().trim(), 
							"", null, 0, foreignLanguageDataList);
					CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$RightSection$BowlerGrp$Side" + WhichSide + "$Name$Side" 
							+ WhichSubSide + "$FullName$English$txt_LastName*GEOM*TEXT SET ", config, Constants.TG20, print_writers, foreignLanguageData);
					
//					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$RightSection$BowlerGrp$Side" + WhichSide + "$Name$Side" 
//						+ WhichSubSide + "$FullName$txt_FirstName*GEOM*TEXT SET \0", print_writers);
//					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$RightSection$BowlerGrp$Side" + WhichSide + "$Name$Side" + WhichSubSide 
//						+ "$FullName$txt_LastName*GEOM*TEXT SET " + (bowlingCard.getPlayer().getTicker_name() != null ? bowlingCard.getPlayer().getTicker_name():"") + "\0", print_writers);
				}
				break;
			default:
				if(infobar.getSection4() != null && !infobar.getSection4().isEmpty()) {
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$RightSection$BowlerGrp$Side" + WhichSide + "$Name$Side" 
						+ WhichSubSide + "$select_NameType*FUNCTION*Omo*vis_con SET 0\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$RightSection$BowlerGrp$Side" + WhichSide + "$Name$Side" 
						+ WhichSubSide + "$TickerName$txt_Name*GEOM*TEXT SET " + bowlingCard.getPlayer().getTicker_name() + "\0", print_writers);
				}else {
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$RightSection$BowlerGrp$Side" + WhichSide + "$Name$Side" 
						+ WhichSubSide + "$select_NameType*FUNCTION*Omo*vis_con SET 1\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$RightSection$BowlerGrp$Side" + WhichSide + "$Name$Side" 
						+ WhichSubSide + "$FullName$txt_FirstName*GEOM*TEXT SET \0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$RightSection$BowlerGrp$Side" + WhichSide + "$Name$Side" + WhichSubSide 
						+ "$FullName$txt_LastName*GEOM*TEXT SET " + (bowlingCard.getPlayer().getTicker_name() != null ? bowlingCard.getPlayer().getTicker_name():"") + "\0", print_writers);
				}
				break;
			}
			
			boolean useScriptContainer = false;
			if (additionalFeature.equalsIgnoreCase(Constants.SCRIPT)) {
				useScriptContainer = true;
			}
			if(config.getSecondaryBroadcaster().equalsIgnoreCase("without_script")) {
				useScriptContainer = false;
			}
			
			if (useScriptContainer == true) {
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$RightSection$BowlerGrp$Side" + WhichSide 
					+ "$ScoreGrp$Figures*SCRIPT*INSTANCE*startValue SET " + bowlerPreviousRuns + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$RightSection$BowlerGrp$Side" + WhichSide 
					+ "$ScoreGrp$Figures*SCRIPT*INSTANCE*endValue SET " + bowlingCard.getRuns() + "\0", print_writers);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$RightSection$BowlerGrp$Side" + WhichSide 
					+ "$ScoreGrp$Figures*SCRIPT*INSTANCE*wicketStartValue SET " + bowlerPreviousWickets + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$RightSection$BowlerGrp$Side" + WhichSide 
					+ "$ScoreGrp$Figures*SCRIPT*INSTANCE*wicketEndValue SET " + bowlingCard.getWickets() + "\0", print_writers);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$RightSection$BowlerGrp$Side" + WhichSide 
					+ "$ScoreGrp$Overs*SCRIPT*INSTANCE*startValue SET " + bowlerPreviousOvers + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$RightSection$BowlerGrp$Side" + WhichSide 
					+ "$ScoreGrp$Overs*SCRIPT*INSTANCE*endValue SET " + bowlingCard.getOvers() + "\0", print_writers);
				
		    	bowlerNextBall = bowlingCard.getBalls();
		    	
		    	if (bowlerPreviousBalls == 6 && bowlerNextBall <= 0) {bowlerNextBall = 6;}
				else if (bowlerPreviousBalls == 6 && bowlerNextBall == 1) {bowlerPreviousBalls = 0;}
				else if (bowlerPreviousBalls == 5 && bowlerNextBall <= 0) {bowlerNextBall = 6;}
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$RightSection$BowlerGrp$Side" + WhichSide 
					+ "$ScoreGrp$Overs*SCRIPT*INSTANCE*ballsStartValue SET " + bowlerPreviousBalls + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$RightSection$BowlerGrp$Side" + WhichSide 
					+ "$ScoreGrp$Overs*SCRIPT*INSTANCE*ballsEndValue SET " + bowlerNextBall + "\0", print_writers);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$RightSection$BowlerGrp$Side" + WhichSide 
					+ "$ScoreGrp$Figures*SCRIPT*INSTANCE*StartLoopBtn INVOKE\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$RightSection$BowlerGrp$Side" + WhichSide 
					+ "$ScoreGrp$Overs*SCRIPT*INSTANCE*StartLoopBtn INVOKE\0", print_writers);				
			
			} else {
				
				bowlerNextBall = bowlingCard.getBalls();
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$RightSection$BowlerGrp$Side" + WhichSide 
					+ "$ScoreGrp$Runs$Hundreths$Side" + WhichSubSide + "$txt_Runs*GEOM*TEXT SET " + CricketFunctions.hundredsTensUnitsTeamScore(
					String.valueOf(bowlingCard.getRuns())).split(",")[0] + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$RightSection$BowlerGrp$Side" + WhichSide 
					+ "$ScoreGrp$Runs$Tenths$Side" + WhichSubSide + "$txt_Runs*GEOM*TEXT SET " + CricketFunctions.hundredsTensUnitsTeamScore(
					String.valueOf(bowlingCard.getRuns())).split(",")[1] + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$RightSection$BowlerGrp$Side" + WhichSide 
					+ "$ScoreGrp$Runs$Units$Side" + WhichSubSide + "$txt_Runs*GEOM*TEXT SET " + CricketFunctions.hundredsTensUnitsTeamScore(
					String.valueOf(bowlingCard.getRuns())).split(",")[2] + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$RightSection$BowlerGrp$Side" + WhichSide 
						+ "$ScoreGrp$Runs$Units*ACTIVE SET 1\0", print_writers);

				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$RightSection$BowlerGrp$Side" + WhichSide 
					+ "$ScoreGrp$Wickets$Tenths$Side" + WhichSubSide + "$txt_Wickets*GEOM*TEXT SET " + CricketFunctions.hundredsTensUnitsTeamScore(
					String.valueOf(bowlingCard.getWickets())).split(",")[1] + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$RightSection$BowlerGrp$Side" + WhichSide 
					+ "$ScoreGrp$Wickets$Units$Side" + WhichSubSide + "$txt_Wickets*GEOM*TEXT SET " + CricketFunctions.hundredsTensUnitsTeamScore(
					String.valueOf(bowlingCard.getWickets())).split(",")[2] + "\0", print_writers);

				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$RightSection$BowlerGrp$Side" + WhichSide 
					+ "$ScoreGrp$Overs$Tenths$Side" + WhichSubSide + "$txt_Overs*GEOM*TEXT SET " + CricketFunctions.hundredsTensUnitsTeamScore(
					String.valueOf(bowlingCard.getOvers())).split(",")[1] + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$RightSection$BowlerGrp$Side" + WhichSide 
					+ "$ScoreGrp$Overs$Units$Side" + WhichSubSide + "$txt_Overs*GEOM*TEXT SET " + CricketFunctions.hundredsTensUnitsTeamScore(
					String.valueOf(bowlingCard.getOvers())).split(",")[2] + "\0", print_writers);

				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$RightSection$BowlerGrp$Side" + WhichSide 
					+ "$ScoreGrp$Overs$Dot*ACTIVE SET " + (bowlerNextBall == 0?0:1) + "\0", print_writers);

				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$RightSection$BowlerGrp$Side" + WhichSide 
					+ "$ScoreGrp$Overs$Decimal$Side" + WhichSubSide + "$txt_Overs*GEOM*TEXT SET " + (bowlerNextBall == 0?"":bowlerNextBall) + "\0", print_writers);		

				this_animation.processAnimation(Constants.FRONT, print_writers, "BowlerCounter$Wickets", "SHOW 0.0");
				this_animation.processAnimation(Constants.FRONT, print_writers, "BowlerCounter$Runs", "SHOW 0.0");
				this_animation.processAnimation(Constants.FRONT, print_writers, "BowlerCounter$Overs", "SHOW 0.0");
				
			}
			
			if(WhichSide == 1) {
				if(bowlingCard.getStatus().equalsIgnoreCase(CricketUtil.CURRENT + CricketUtil.BOWLER)) {
					this_animation.processAnimation(Constants.FRONT, print_writers, "Bowler_Lowlight", "SHOW 0.0");
				} else {
					this_animation.processAnimation(Constants.FRONT, print_writers, "Bowler_Lowlight", "SHOW 0.600");
				}
			}
			
	    	String startKf = "", endKf = "";
	    	if (useScriptContainer == true) {

			    if (String.valueOf(bowlerPreviousRuns).length() != String.valueOf(bowlingCard.getRuns()).length()) {
			    	
			    	if (bowlerPreviousRuns < bowlingCard.getRuns()) {
			    		
						if(bowlerPreviousRuns > 99) {
							startKf = "0.0 0.0 0.0";
						}else if(bowlerPreviousRuns > 9) {
							startKf = "9.0 0.0 0.0";
						}else {
							startKf = "18.0 0.0 0.0";
						}
						if(bowlingCard.getRuns() > 99) {
							endKf = "0.0 0.0 0.0";
						}else if(bowlingCard.getRuns() > 9) {
							endKf = "9.0 0.0 0.0";
						}else {
							endKf = "18.0 0.0 0.0";
						}

			    	} 
					else if (bowlerPreviousRuns > bowlingCard.getRuns()) {
					
						if(bowlingCard.getRuns() > 99) {
							startKf = "0.0 0.0 0.0";
						}else if(bowlingCard.getRuns() > 9) {
							startKf = "9.0 0.0 0.0";
						}else {
							startKf = "18.0 0.0 0.0";
						}
						if(bowlerPreviousRuns > 99) {
							endKf = "0.0 0.0 0.0";
						}else if(bowlerPreviousRuns > 9) {
							endKf = "9.0 0.0 0.0";
						}else {
							endKf = "18.0 0.0 0.0";
						}

					}
			    	
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$RightSection$BowlerSection$Side" 
						+ WhichSide + "$Figures$MoveForDigits*ANIMATION*KEY*$FMD1*VALUE SET " + startKf + "\0",print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$RightSection$BowlerSection$Side" 
						+ WhichSide + "$Figures$MoveForDigits*ANIMATION*KEY*$FMD2*VALUE SET " + endKf + "\0",print_writers);

			        this_animation.processAnimation(Constants.FRONT, print_writers, "anim_Infobar$BowlerCounter$MoveForDigits", "START");
			        TimeUnit.MILLISECONDS.sleep(600);

					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$RightSection$BowlerSection$Side" 
						+ WhichSide + "$Figures$MoveForDigits*ANIMATION*KEY*$FMD1*VALUE SET " + endKf + "\0",print_writers);

			        this_animation.processAnimation(Constants.FRONT, print_writers, "anim_Infobar$BowlerCounter$MoveForDigits", "SHOW 0.0");
			        
			    }
			    
	    	} else {

				if(bowlingCard.getRuns() > 99) {
					endKf = "0.0 0.0 0.0";
				}else if(bowlingCard.getRuns() > 9) {
					endKf = "9.0 0.0 0.0";
				}else {
					endKf = "18.0 0.0 0.0";
				}
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$RightSection$BowlerSection$Side" 
					+ WhichSide + "$Figures$MoveForDigits*ANIMATION*KEY*$FMD1*VALUE SET " + endKf + "\0",print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$RightSection$BowlerSection$Side" 
					+ WhichSide + "$Figures$MoveForDigits*ANIMATION*KEY*$FMD1*VALUE SET " + endKf + "\0",print_writers);
		        this_animation.processAnimation(Constants.FRONT, print_writers, "anim_Infobar$BowlerCounter$MoveForDigits", "SHOW 0.0");
		        break;
	    	}
			
			bowlerPreviousRuns = bowlingCard.getRuns();
		    bowlerPreviousWickets = bowlingCard.getWickets();
		    bowlerPreviousOvers = bowlingCard.getOvers();
		    bowlerPreviousBalls   = bowlerNextBall;
			
			break;
		}
	}
	public String populateVizInfobarRightBottom(List<PrintWriter> print_writers, MatchAllData matchAllData,int WhichSide,int WhichSubSide) throws InterruptedException, IOException {
		
		inning = matchAllData.getMatch().getInning().stream().filter(inn -> inn.getIsCurrentInning().equalsIgnoreCase(CricketUtil.YES)).findAny().orElse(null);

		if(inning == null) {
			return "populateVizInfobarRightBottom: Inning return is NULL";
		}
		
		switch(config.getBroadcaster()) {
		case Constants.TRI_SERIES:  case Constants.MT20:
			populateSection3(print_writers, matchAllData, WhichSide);
			break;
		case Constants.BCCI:
			if(infobar.getSection4() != null && !infobar.getSection4().isEmpty()) {
				switch(infobar.getSection4().toUpperCase()) {
				case "BOWLER_REPLACE":
					isThisOverLimitExceed = true;
					int Replaced_Player_id = CricketFunctions.SecondLastBowlerId(matchAllData,bowlingCard.getPlayerId());
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$NormalAll$OutForSection5$BowlerNameGrp$Section4$Side" 
							+ WhichSide + "$select_GraphicsType*FUNCTION*Omo*vis_con SET 2\0", print_writers);
					
					if(Replaced_Player_id > 0) {
						player = Players.stream().filter(plyr -> plyr.getPlayerId() == Replaced_Player_id).findAny().orElse(null);
						
						CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$NormalAll$OutForSection5$BowlerNameGrp$Section4$Side" 
								+ WhichSide + "$GenericText$Text$select_Language*FUNCTION*Omo*vis_con SET ", config, Constants.BCCI, print_writers, foreignLanguageOmo);
						foreignLanguageData = CricketFunctions.AssembleMultiLanguageData(CricketUtil.PLAYER, CricketUtil.TICKERNAME, multilanguagedata, 
								"REPLACES " + player.getTicker_name(), "", null, 0, foreignLanguageDataList);
						CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$NormalAll$OutForSection5$BowlerNameGrp$Section4$Side" 
								+ WhichSide + "$GenericText$Text$English$txt_Text*GEOM*TEXT SET ", config, Constants.BCCI, print_writers, foreignLanguageData);
					}
					break;
				case "BOWLING_END":
					isThisOverLimitExceed = true;
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$NormalAll$OutForSection5$BowlerNameGrp$Section4$Side" 
							+ WhichSide + "$select_GraphicsType*FUNCTION*Omo*vis_con SET 2\0", print_writers);
					
					CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$NormalAll$OutForSection5$BowlerNameGrp$Section4$Side" 
							+ WhichSide + "$GenericText$Text$select_Language*FUNCTION*Omo*vis_con SET ", config, Constants.BCCI, print_writers, foreignLanguageOmo);
					foreignLanguageData = CricketFunctions.AssembleMultiLanguageData(CricketUtil.DICTIONARY, "", multilanguagedata, "BOWLING FROM " + 
							(bowlingCard.getBowling_end() == 1 ? matchAllData.getSetup().getGround().getFirst_bowling_end() : 
								matchAllData.getSetup().getGround().getSecond_bowling_end()) + " END", "", null, 0, foreignLanguageDataList);
					CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$NormalAll$OutForSection5$BowlerNameGrp$Section4$Side" 
							+ WhichSide + "$GenericText$Text$English$txt_Text*GEOM*TEXT SET ", config, Constants.BCCI, print_writers, foreignLanguageData);
					break;
				case CricketUtil.OVER:
					this_data_str = new ArrayList<String>();
					this_data_str.add(String.join(",", 
						    new ArrayList<>(Arrays.asList(IndexController.MatchStats.getOverData().getThisOverTxt().split(",")))
					        .stream()
					        .map(s -> s.replace("WIDE", "WD").replace("NO_BALL", "NB").replace("LEG_BYE", "LB").replace("BYE", "B")
					                   .replace("PENALTY", "PN").replace("LOG_WICKET", "W").replace("WICKET", "W"))
					        .collect(Collectors.collectingAndThen(Collectors.toList(), list -> {Collections.reverse(list); return list;}))
					        .toArray(new String[0])));
					
					int totalOverSize = 6;
					for(int i=1;i<=totalOverSize;i++) {
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$NormalAll$OutForSection5$BowlerNameGrp$Section4$Side" 
								+ WhichSide + "$ThisOver$ThisOverGrp" + i + "$select_DataType*FUNCTION*Omo*vis_con SET 1\0", print_writers);
					}
					
					if(this_data_str.get(this_data_str.size()-1) == null) {
						return "populateVizInfobarRightBottom: This over data returned invalid";
					}
					if(this_data_str.get(this_data_str.size()-1).split(",").length <= 10) {
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$NormalAll$OutForSection5$BowlerNameGrp$Section4$Side" 
								+ WhichSide + "$select_GraphicsType*FUNCTION*Omo*vis_con SET 1\0", print_writers);
						
						for(int iBall = 0; iBall < this_data_str.get(this_data_str.size()-1).split(",").length; iBall++) {
							switch (this_data_str.get(this_data_str.size()-1).split(",")[iBall].toUpperCase()) {
							case CricketUtil.DOT: case CricketUtil.ONE: case CricketUtil.TWO: case CricketUtil.THREE: case CricketUtil.FIVE: 
							case CricketUtil.FOUR:case CricketUtil.SIX: case "W":
								switch (this_data_str.get(this_data_str.size()-1).split(",")[iBall].toUpperCase()) {
								case CricketUtil.DOT:
									CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$NormalAll$OutForSection5$BowlerNameGrp$Section4$Side" 
											+ WhichSide + "$ThisOver$ThisOverGrp" + (iBall+1) + "$select_DataType*FUNCTION*Omo*vis_con SET 2\0", print_writers);
									break;
								case "W":
									CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$NormalAll$OutForSection5$BowlerNameGrp$Section4$Side" 
											+ WhichSide + "$ThisOver$ThisOverGrp" + (iBall+1) + "$select_DataType*FUNCTION*Omo*vis_con SET 6\0", print_writers);
									break;
								default:
									CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$NormalAll$OutForSection5$BowlerNameGrp$Section4$Side" 
											+ WhichSide + "$ThisOver$ThisOverGrp" + (iBall+1) + "$select_DataType*FUNCTION*Omo*vis_con SET 3\0", print_writers);
									CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$NormalAll$OutForSection5$BowlerNameGrp$Section4$Side" 
											+ WhichSide + "$ThisOver$ThisOverGrp" + (iBall+1) + "$Runs$txt_Runs*GEOM*TEXT SET " + this_data_str.get(this_data_str.size()-1).
											split(",")[iBall] + "\0", print_writers);
									break;
								}
								break;
							default:
								if(this_data_str.get(this_data_str.size()-1).split(",")[iBall].toUpperCase().contains("BOUNDARY")) {
									if(this_data_str.get(this_data_str.size()-1).split(",")[iBall].toUpperCase().contains("4")) {
										CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$NormalAll$OutForSection5$BowlerNameGrp$Section4$Side" 
												+ WhichSide + "$ThisOver$ThisOverGrp" + (iBall+1) + "$select_DataType*FUNCTION*Omo*vis_con SET 4\0", print_writers);
									}else {
										CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$NormalAll$OutForSection5$BowlerNameGrp$Section4$Side" 
												+ WhichSide + "$ThisOver$ThisOverGrp" + (iBall+1) + "$select_DataType*FUNCTION*Omo*vis_con SET 5\0", print_writers);
									}
								}else if(!this_data_str.get(this_data_str.size()-1).isEmpty()) {
									CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$NormalAll$OutForSection5$BowlerNameGrp$Section4$Side" 
											+ WhichSide + "$ThisOver$ThisOverGrp" + (iBall+1) + "$select_DataType*FUNCTION*Omo*vis_con SET 7\0", print_writers);
									
									if(this_data_str.get(this_data_str.size()-1).split(",")[iBall].toUpperCase().contains("W B")) {
										CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$NormalAll$OutForSection5$BowlerNameGrp$Section4$Side" 
												+ WhichSide + "$ThisOver$ThisOverGrp" + (iBall+1) + "$2Line$txt_Text1*GEOM*TEXT SET " + this_data_str.get(this_data_str.size()-1).
												split(",")[iBall].toUpperCase() + "\0", print_writers);
									}else {
										CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$NormalAll$OutForSection5$BowlerNameGrp$Section4$Side" 
												+ WhichSide + "$ThisOver$ThisOverGrp" + (iBall+1) + "$2Line$txt_Text1*GEOM*TEXT SET " + this_data_str.get(this_data_str.size()-1).
												split(",")[iBall].toUpperCase() + "\0", print_writers);
									}
									CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$NormalAll$OutForSection5$BowlerNameGrp$Section4$Side" 
											+ WhichSide + "$ThisOver$ThisOverGrp" + (iBall+1) + "$2Line$txt_Text2*GEOM*TEXT SET \0", print_writers);
									switch (this_data_str.get(this_data_str.size()-1).split(",")[iBall].toUpperCase()) {
									case "1B": case "2B": case "3B": case "4B": case "5B": case "6B":
									case "1LB": case "2LB": case "3LB": case "4LB": case "5LB": case "6LB":
										
										break;

									default:
										if(this_data_str.get(this_data_str.size()-1).split(",")[iBall].contains("NB") || 
												this_data_str.get(this_data_str.size()-1).split(",")[iBall].contains("WD")) {
											totalOverSize++;
										}
										break;
									}
								}
								break;
							}
						}
					}else {
						if(this_data_str.get(this_data_str.size()-1).split(",").length > 10 || isThisOverLimitExceed) {
							isThisOverLimitExceed = true;
							
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$NormalAll$OutForSection5$BowlerNameGrp$Section4$Side" 
									+ WhichSide + "$select_GraphicsType*FUNCTION*Omo*vis_con SET 2\0", print_writers);	
							
							for (BowlingCard boc : inning.getBowlingCard()) {
								switch (boc.getStatus().toUpperCase()) {
								case CricketUtil.CURRENT + CricketUtil.BOWLER: case CricketUtil.LAST + CricketUtil.BOWLER:
									if (boc.getStatus().equalsIgnoreCase(CricketUtil.CURRENT + CricketUtil.BOWLER)) {
										if(!CricketFunctions.processThisOverRunsCount(boc.getPlayerId(),matchAllData.getEventFile().getEvents()).split("-")[0].equalsIgnoreCase("0")) {
											CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$NormalAll$OutForSection5$BowlerNameGrp$Section4$Side" + WhichSide + 
													"$GenericText$Text$select_Language*FUNCTION*Omo*vis_con SET 1\0", print_writers);
											
											CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$NormalAll$OutForSection5$BowlerNameGrp$Section4$Side" + WhichSide + 
													"$GenericText$Text$English$txt_Text*GEOM*TEXT SET " + "THIS OVER : " + CricketFunctions.processThisOverRunsCount(boc.getPlayerId(), 
													matchAllData.getEventFile().getEvents()).split("-")[0] + " RUN" + CricketFunctions.Plural(Integer.valueOf(CricketFunctions.processThisOverRunsCount(
													boc.getPlayerId(),matchAllData.getEventFile().getEvents()).split("-")[0])).toUpperCase() + "\0", print_writers);
										}
									} else if (boc.getStatus().equalsIgnoreCase(CricketUtil.LAST + CricketUtil.BOWLER)) {
										CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$NormalAll$OutForSection5$BowlerNameGrp$Section4$Side" + WhichSide + 
												"$GenericText$Text$select_Language*FUNCTION*Omo*vis_con SET 1\0", print_writers);
										
										CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$NormalAll$OutForSection5$BowlerNameGrp$Section4$Side" + WhichSide + 
												"$GenericText$Text$English$txt_Text*GEOM*TEXT SET " + "LAST OVER : " + CricketFunctions.processThisOverRunsCount(boc.getPlayerId(), 
												matchAllData.getEventFile().getEvents()).split("-")[0] + " RUN" + CricketFunctions.Plural(Integer.valueOf(CricketFunctions.processThisOverRunsCount(
												boc.getPlayerId(),matchAllData.getEventFile().getEvents()).split("-")[0])).toUpperCase() + "\0", print_writers);
										
										isThisOverLimitExceed = false;
										break;
									}
									break;
								}
							}
						}
					}
					for(int i=totalOverSize+1;i<=10;i++) {
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$NormalAll$OutForSection5$BowlerNameGrp$Section4$Side" 
								+ WhichSide + "$ThisOver$ThisOverGrp" + i + "$select_DataType*FUNCTION*Omo*vis_con SET 0\0", print_writers);
					}
					infobar.setLast_this_over(this_data_str.get(this_data_str.size()-1));
					break;
				}
			}
			break;
		}
		return Constants.OK;
	}
	public String populateVizInfobarLeftBottom(List<PrintWriter> print_writers, MatchAllData matchAllData,int WhichSide) {
		switch(config.getBroadcaster()) {
		case Constants.BCCI:
			switch(infobar.getSection1().toUpperCase()) {
			case "CURRENT_SESSION":
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$NormalAll$OutForSection5$Section-1-2$Section1$Section1$Side" 
						+ WhichSide + "$select_GraphicsType*FUNCTION*Omo*vis_con SET 3\0", print_writers);
				
				CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$NormalAll$OutForSection5$Section-1-2$Section1$Section1$Side" 
						+ WhichSide + "$RunRate$Text$select_Language*FUNCTION*Omo*vis_con SET ", config, Constants.BCCI, print_writers, foreignLanguageOmo);
				foreignLanguageData = CricketFunctions.AssembleMultiLanguageData(CricketUtil.DICTIONARY, "", multilanguagedata, "SESSION RR", "", null, 0, foreignLanguageDataList);
				CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$NormalAll$OutForSection5$Section-1-2$Section1$Section1$Side" 
						+ WhichSide + "$RunRate$Text$English$txt_Title*GEOM*TEXT SET ", config, Constants.BCCI, print_writers, foreignLanguageData);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$NormalAll$OutForSection5$Section-1-2$Section1$Section1$Side" 
						+ WhichSide + "$RunRate$ValueGrp$txt_Value*GEOM*TEXT SET " + CricketFunctions.generateRunRate(matchAllData.getMatch().getDaysSessions().
								get(matchAllData.getMatch().getDaysSessions().size()-1).getTotalRuns(), 0, matchAllData.getMatch().getDaysSessions().
									get(matchAllData.getMatch().getDaysSessions().size()-1).getTotalBalls(), 2, matchAllData) + "\0", print_writers);
				break;
			case "RRR":				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$NormalAll$OutForSection5$Section-1-2$Section1$Section1$Side" 
						+ WhichSide + "$select_GraphicsType*FUNCTION*Omo*vis_con SET 3\0", print_writers);
				
				CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$NormalAll$OutForSection5$Section-1-2$Section1$Section1$Side" 
						+ WhichSide + "$RunRate$Text$select_Language*FUNCTION*Omo*vis_con SET ", config, Constants.BCCI, print_writers, foreignLanguageOmo);
				foreignLanguageData = CricketFunctions.AssembleMultiLanguageData(CricketUtil.DICTIONARY, "", multilanguagedata, "REQUIRED RUN RATE", "", null, 0, foreignLanguageDataList);
				CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$NormalAll$OutForSection5$Section-1-2$Section1$Section1$Side" 
						+ WhichSide + "$RunRate$Text$English$txt_Title*GEOM*TEXT SET ", config, Constants.BCCI, print_writers, foreignLanguageData);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$NormalAll$OutForSection5$Section-1-2$Section1$Section1$Side" 
						+ WhichSide + "$RunRate$ValueGrp$txt_Value*GEOM*TEXT SET " + CricketFunctions.generateRunRate(CricketFunctions.GetTargetData(matchAllData).
								getRemaningRuns(),0,CricketFunctions.GetTargetData(matchAllData).getRemaningBall(),2,matchAllData) + "\0", print_writers);
				break;
			case "TARGET":
				if(!matchAllData.getMatch().getInning().get(1).getIsCurrentInning().equalsIgnoreCase(CricketUtil.YES)&&
						!matchAllData.getMatch().getInning().get(3).getIsCurrentInning().equalsIgnoreCase(CricketUtil.YES)) {
					return "populateVizInfobarLeftBottom: Target available in 2nd inning only";
				}
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$NormalAll$OutForSection5$Section-1-2$Section1$Section1$Side" 
						+ WhichSide + "$select_GraphicsType*FUNCTION*Omo*vis_con SET 2\0", print_writers);
				
				CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$NormalAll$OutForSection5$Section-1-2$Section1$Section1$Side" 
						+ WhichSide + "$Target$TeamName$select_Language*FUNCTION*Omo*vis_con SET ", config, Constants.BCCI, print_writers, foreignLanguageOmo);
				foreignLanguageData = CricketFunctions.AssembleMultiLanguageData(CricketUtil.DICTIONARY, "", multilanguagedata, "TARGET", "", null, 0, foreignLanguageDataList);
				CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$NormalAll$OutForSection5$Section-1-2$Section1$Section1$Side" 
						+ WhichSide + "$Target$TeamName$English$txt_TeamName*GEOM*TEXT SET ", config, Constants.BCCI, print_writers, foreignLanguageData);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$NormalAll$OutForSection5$Section-1-2$Section1$Section1$Side" 
						+ WhichSide + "$Target$ValueGrp$txt_Value*GEOM*TEXT SET " + CricketFunctions.GetTargetData(matchAllData).getTargetRuns() + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$NormalAll$OutForSection5$Section-1-2$Section1$Section1$Side" 
						+ WhichSide + "$Target$ValueGrp$txt_TargetDls*GEOM*TEXT SET " + (matchAllData.getSetup().getTargetType() != null && 
							!matchAllData.getSetup().getTargetType().isEmpty() ? "(" + matchAllData.getSetup().getTargetType().toUpperCase() + ")" : "") + "\0", print_writers);
				break;
			case "DLSTARGET":
				if(!matchAllData.getMatch().getInning().get(1).getIsCurrentInning().equalsIgnoreCase(CricketUtil.YES)) {
					return "populateVizInfobarLeftBottom: Target available in 2nd inning only";
				}
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$NormalAll$OutForSection5$Section-1-2$Section1$Section1$Side" 
						+ WhichSide + "$select_GraphicsType*FUNCTION*Omo*vis_con SET 2\0", print_writers);
				
				CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$NormalAll$OutForSection5$Section-1-2$Section1$Section1$Side" 
						+ WhichSide + "$Target$TeamName$select_Language*FUNCTION*Omo*vis_con SET ", config, Constants.BCCI, print_writers, foreignLanguageOmo);
				foreignLanguageData = CricketFunctions.AssembleMultiLanguageData(CricketUtil.DICTIONARY, "", multilanguagedata, "D/L TARGET", "", null, 0, foreignLanguageDataList);
				CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$NormalAll$OutForSection5$Section-1-2$Section1$Section1$Side" 
						+ WhichSide + "$Target$TeamName$English$txt_TeamName*GEOM*TEXT SET ", config, Constants.BCCI, print_writers, foreignLanguageData);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$NormalAll$OutForSection5$Section-1-2$Section1$Section1$Side" 
						+ WhichSide + "$Target$ValueGrp$txt_Value*GEOM*TEXT SET " + CricketFunctions.GetTargetData(matchAllData).getTargetRuns() + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$NormalAll$OutForSection5$Section-1-2$Section1$Section1$Side" 
						+ WhichSide + "$Target$ValueGrp$txt_TargetDls*GEOM*TEXT SET \0", print_writers);
				break;
			case "EXTRAS":
				inning = matchAllData.getMatch().getInning().stream().filter(inn -> inn.getIsCurrentInning().equalsIgnoreCase(CricketUtil.YES)).findAny().orElse(null);
				
				if(inning == null) {
					return "populateVizInfobarLeftBottom: Inning return is NULL";
				}
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$NormalAll$OutForSection5$Section-1-2$Section1$Section1$Side" 
						+ WhichSide + "$select_GraphicsType*FUNCTION*Omo*vis_con SET 4\0", print_writers);
				
				CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$NormalAll$OutForSection5$Section-1-2$Section1$Section1$Side" 
						+ WhichSide + "$Extras$Text$select_Language*FUNCTION*Omo*vis_con SET ", config, Constants.BCCI, print_writers, foreignLanguageOmo);
				foreignLanguageData = CricketFunctions.AssembleMultiLanguageData(CricketUtil.DICTIONARY, "", multilanguagedata, "EXTRAS", "", null, 0, foreignLanguageDataList);
				CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$NormalAll$OutForSection5$Section-1-2$Section1$Section1$Side" 
						+ WhichSide + "$Extras$Text$English$txt_Title*GEOM*TEXT SET ", config, Constants.BCCI, print_writers, foreignLanguageData);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$NormalAll$OutForSection5$Section-1-2$Section1$Section1$Side" 
						+ WhichSide + "$Extras$ValueGrp$txt_Value*GEOM*TEXT SET " + inning.getTotalExtras() + "\0", print_writers);
				break;
			case CricketUtil.TOSS:
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$NormalAll$OutForSection5$Section-1-2$Section1$Section1$Side" 
						+ WhichSide + "$select_GraphicsType*FUNCTION*Omo*vis_con SET 7\0", print_writers);
				
				CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$NormalAll$OutForSection5$Section-1-2$Section1$Section1$Side" 
						+ WhichSide + "$GenericText$Text1$select_Language*FUNCTION*Omo*vis_con SET ", config, Constants.BCCI, print_writers, foreignLanguageOmo);
				foreignLanguageData = CricketFunctions.AssembleMultiLanguageData(CricketUtil.DICTIONARY, "", multilanguagedata, "TOSS", "", null, 0, foreignLanguageDataList);
				CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$NormalAll$OutForSection5$Section-1-2$Section1$Section1$Side" 
						+ WhichSide + "$GenericText$Text1$English$txt_Text1*GEOM*TEXT SET ", config, Constants.BCCI, print_writers, foreignLanguageData);
				
				CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$NormalAll$OutForSection5$Section-1-2$Section1$Section1$Side" 
						+ WhichSide + "$GenericText$Text2$select_Language*FUNCTION*Omo*vis_con SET ", config, Constants.BCCI, print_writers, foreignLanguageOmo);
				foreignLanguageData = CricketFunctions.AssembleMultiLanguageData(CricketUtil.TEAM, "", multilanguagedata, (matchAllData.getSetup().getTossWinningTeam() 
						== matchAllData.getSetup().getHomeTeamId() ? matchAllData.getSetup().getHomeTeam().getTeamName4() : matchAllData.getSetup().getAwayTeam().getTeamName4()), 
						"", null, 0, foreignLanguageDataList);
				CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$NormalAll$OutForSection5$Section-1-2$Section1$Section1$Side" 
						+ WhichSide + "$GenericText$Text2$English$txt_Text2*GEOM*TEXT SET ", config, Constants.BCCI, print_writers, foreignLanguageData);
			
				break;
			case "SUPER_OVER":
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$NormalAll$OutForSection5$Section-1-2$Section1$Section1$Side" 
						+ WhichSide + "$select_GraphicsType*FUNCTION*Omo*vis_con SET 7\0", print_writers);
				
				CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$NormalAll$OutForSection5$Section-1-2$Section1$Section1$Side" 
						+ WhichSide + "$GenericText$Text1$select_Language*FUNCTION*Omo*vis_con SET ", config, Constants.BCCI, print_writers, foreignLanguageOmo);
				foreignLanguageData = CricketFunctions.AssembleMultiLanguageData(CricketUtil.DICTIONARY, "", multilanguagedata, "SUPER", "", null, 0, foreignLanguageDataList);
				CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$NormalAll$OutForSection5$Section-1-2$Section1$Section1$Side" 
						+ WhichSide + "$GenericText$Text1$English$txt_Text1*GEOM*TEXT SET ", config, Constants.BCCI, print_writers, foreignLanguageData);
				
				CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$NormalAll$OutForSection5$Section-1-2$Section1$Section1$Side" 
						+ WhichSide + "$GenericText$Text2$select_Language*FUNCTION*Omo*vis_con SET ", config, Constants.BCCI, print_writers, foreignLanguageOmo);
				foreignLanguageData = CricketFunctions.AssembleMultiLanguageData(CricketUtil.DICTIONARY, "", multilanguagedata, "OVER", "", null, 0, foreignLanguageDataList);
				CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$NormalAll$OutForSection5$Section-1-2$Section1$Section1$Side" 
						+ WhichSide + "$GenericText$Text2$English$txt_Text2*GEOM*TEXT SET ", config, Constants.BCCI, print_writers, foreignLanguageData);
			
				break;	
			case "CRR":
				inning = matchAllData.getMatch().getInning().stream().filter(inn -> inn.getIsCurrentInning().equalsIgnoreCase(CricketUtil.YES)).findAny().orElse(null);
				
				if(inning == null) {
					return "populateVizInfobarLeftBottom: Inning return is NULL";
				}
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$NormalAll$OutForSection5$Section-1-2$Section1$Section1$Side" 
						+ WhichSide + "$select_GraphicsType*FUNCTION*Omo*vis_con SET 3\0", print_writers);
				
				CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$NormalAll$OutForSection5$Section-1-2$Section1$Section1$Side" 
						+ WhichSide + "$RunRate$Text$select_Language*FUNCTION*Omo*vis_con SET ", config, Constants.BCCI, print_writers, foreignLanguageOmo);
				foreignLanguageData = CricketFunctions.AssembleMultiLanguageData(CricketUtil.DICTIONARY, "", multilanguagedata, "CURRENT RUN RATE", "", null, 0, foreignLanguageDataList);
				CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$NormalAll$OutForSection5$Section-1-2$Section1$Section1$Side" 
						+ WhichSide + "$RunRate$Text$English$txt_Title*GEOM*TEXT SET ", config, Constants.BCCI, print_writers, foreignLanguageData);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$NormalAll$OutForSection5$Section-1-2$Section1$Section1$Side" 
						+ WhichSide + "$RunRate$ValueGrp$txt_Value*GEOM*TEXT SET " + CricketFunctions.generateRunRate(inning.getTotalRuns(),inning.getTotalOvers(), 
								inning.getTotalBalls(), 2,matchAllData) + "\0", print_writers);
				break;
			case "DAY_SESSION":
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$NormalAll$OutForSection5$Section-1-2$Section1$Section1$Side" 
						+ WhichSide + "$select_GraphicsType*FUNCTION*Omo*vis_con SET 7\0", print_writers);
				
				CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$NormalAll$OutForSection5$Section-1-2$Section1$Section1$Side" 
						+ WhichSide + "$GenericText$Text1$select_Language*FUNCTION*Omo*vis_con SET ", config, Constants.BCCI, print_writers, foreignLanguageOmo);
				CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$NormalAll$OutForSection5$Section-1-2$Section1$Section1$Side" 
						+ WhichSide + "$GenericText$Text2$select_Language*FUNCTION*Omo*vis_con SET ", config, Constants.BCCI, print_writers, foreignLanguageOmo);
				
				if(matchAllData.getMatch().getDaysSessions().get(matchAllData.getMatch().getDaysSessions().size()-1).getIsCurrentSession().equalsIgnoreCase(CricketUtil.YES)) {
					foreignLanguageData = CricketFunctions.AssembleMultiLanguageData(CricketUtil.DICTIONARY, "", multilanguagedata, "DAY " + matchAllData.getMatch().
							getDaysSessions().get(matchAllData.getMatch().getDaysSessions().size()-1).getDayNumber(), "", null, 0, foreignLanguageDataList);
					CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$NormalAll$OutForSection5$Section-1-2$Section1$Section1$Side" 
							+ WhichSide + "$GenericText$Text1$English$txt_Text1*GEOM*TEXT SET ", config, Constants.BCCI, print_writers, foreignLanguageData);
					
					foreignLanguageData = CricketFunctions.AssembleMultiLanguageData(CricketUtil.DICTIONARY, "", multilanguagedata, "SESSION " + matchAllData.getMatch().
							getDaysSessions().get(matchAllData.getMatch().getDaysSessions().size()-1).getSessionNumber(), "", null, 0, foreignLanguageDataList);
					CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$NormalAll$OutForSection5$Section-1-2$Section1$Section1$Side" 
							+ WhichSide + "$GenericText$Text2$English$txt_Text2*GEOM*TEXT SET ", config, Constants.BCCI, print_writers, foreignLanguageData);
				}
				break;
			case "LUNCH_TEXT": case "TEA_TEXT":
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$NormalAll$OutForSection5$Section-1-2$Section1$Section1$Side" 
						+ WhichSide + "$select_GraphicsType*FUNCTION*Omo*vis_con SET 7\0", print_writers);
				
				CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$NormalAll$OutForSection5$Section-1-2$Section1$Section1$Side" 
						+ WhichSide + "$GenericText$Text1$select_Language*FUNCTION*Omo*vis_con SET ", config, Constants.BCCI, print_writers, foreignLanguageOmo);
				CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$NormalAll$OutForSection5$Section-1-2$Section1$Section1$Side" 
						+ WhichSide + "$GenericText$Text2$select_Language*FUNCTION*Omo*vis_con SET ", config, Constants.BCCI, print_writers, foreignLanguageOmo);
				
				foreignLanguageData = CricketFunctions.AssembleMultiLanguageData(CricketUtil.DICTIONARY, "", multilanguagedata, (infobar.getSection1().
						equalsIgnoreCase("LUNCH_TEXT")?"LUNCH":"TEA"), "", null, 0, foreignLanguageDataList);
				CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$NormalAll$OutForSection5$Section-1-2$Section1$Section1$Side" 
						+ WhichSide + "$GenericText$Text1$English$txt_Text1*GEOM*TEXT SET ", config, Constants.BCCI, print_writers, foreignLanguageData);
				
				if(matchAllData.getMatch().getDaysSessions().get(matchAllData.getMatch().getDaysSessions().size()-1).getIsCurrentSession().equalsIgnoreCase(CricketUtil.YES)) {
					foreignLanguageData = CricketFunctions.AssembleMultiLanguageData(CricketUtil.DICTIONARY, "", multilanguagedata, "DAY " + matchAllData.getMatch().
							getDaysSessions().get(matchAllData.getMatch().getDaysSessions().size()-1).getDayNumber(), "", null, 0, foreignLanguageDataList);
					CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$NormalAll$OutForSection5$Section-1-2$Section1$Section1$Side" 
							+ WhichSide + "$GenericText$Text2$English$txt_Text2*GEOM*TEXT SET ", config, Constants.BCCI, print_writers, foreignLanguageData);
				}
				break;
			case "LOCAL-TIME":
				Date dt = new Date();
		        SimpleDateFormat dateFormat = new SimpleDateFormat("hh:mm a");
			      
		        CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$NormalAll$OutForSection5$Section-1-2$Section1$Section1$Side" 
						+ WhichSide + "$select_GraphicsType*FUNCTION*Omo*vis_con SET 5\0", print_writers);
		        
		        CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$NormalAll$OutForSection5$Section-1-2$Section1$Section1$Side" 
						+ WhichSide + "$LocalTime$Text1$select_Language*FUNCTION*Omo*vis_con SET ", config, Constants.BCCI, print_writers, foreignLanguageOmo);
				foreignLanguageData = CricketFunctions.AssembleMultiLanguageData(CricketUtil.DICTIONARY, "", multilanguagedata, "LOCAL TIME", "", null, 0, foreignLanguageDataList);
				CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$NormalAll$OutForSection5$Section-1-2$Section1$Section1$Side" 
						+ WhichSide + "$LocalTime$Text$English$txt_Title*GEOM*TEXT SET ", config, Constants.BCCI, print_writers, foreignLanguageData);
		        
		        CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$NormalAll$OutForSection5$Section-1-2$Section1$Section1$Side" 
						+ WhichSide + "$LocalTime$ValueGrp$txt_Value*GEOM*TEXT SET " + dateFormat.format(dt) + "\0", print_writers);	
				break;
			}
			break;
		}
		return Constants.OK;
	}
	public String populateVizInfobarMiddleSection(List<PrintWriter> print_writers, MatchAllData matchAllData, int WhichSide) throws Exception {
		switch(config.getBroadcaster()) {
		case Constants.BCCI:
			if(infobar.getSection3() != null && !infobar.getSection3().isEmpty()) {
				switch(infobar.getSection3().toUpperCase()) {
				case "BLANK":
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$NormalAll$OutForSection5$Section3$Data$Side" + WhichSide + 
							"$select_GraphicsType*FUNCTION*Omo*vis_con SET 0\0", print_writers);
					break;
				case "EQUATION":
					if(!matchAllData.getMatch().getInning().get(1).getIsCurrentInning().equalsIgnoreCase(CricketUtil.YES)&&
							!matchAllData.getMatch().getInning().get(3).getIsCurrentInning().equalsIgnoreCase(CricketUtil.YES)) {
						return "populateVizInfobarMiddleSection: Equation available in 2nd inning only";
					}

					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$NormalAll$OutForSection5$Section3$Data$Side" + WhichSide + 
							"$select_GraphicsType*FUNCTION*Omo*vis_con SET 4\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$NormalAll$OutForSection5$Section3$Data$Side" + WhichSide + 
							"$ToWin$select_DataType*FUNCTION*Omo*vis_con SET 0\0", print_writers);
					
					CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$NormalAll$OutForSection5$Section3$Data$Side" + WhichSide + 
							"$ToWin$Split$Grp1$Stat$select_Language*FUNCTION*Omo*vis_con SET ", config, Constants.BCCI, print_writers, foreignLanguageOmo);
					foreignLanguageData = CricketFunctions.AssembleMultiLanguageData(CricketUtil.DICTIONARY, "", multilanguagedata, "TO WIN", "", null, 0, foreignLanguageDataList);
					CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$NormalAll$OutForSection5$Section3$Data$Side" + WhichSide + 
							"$ToWin$Split$Grp1$Stat$English$txt_Stat*GEOM*TEXT SET ", config, Constants.BCCI, print_writers, foreignLanguageData);
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$NormalAll$OutForSection5$Section3$Data$Side" + WhichSide + 
							"$ToWin$Split$Grp1$txt_Value*GEOM*TEXT SET " + CricketFunctions.GetTargetData(matchAllData).getRemaningRuns() + "\0", print_writers);
					
					CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$NormalAll$OutForSection5$Section3$Data$Side" + WhichSide + 
							"$ToWin$Split$Grp2$Stat$select_Language*FUNCTION*Omo*vis_con SET ", config, Constants.BCCI, print_writers, foreignLanguageOmo);
					foreignLanguageData = CricketFunctions.AssembleMultiLanguageData(CricketUtil.DICTIONARY, "", multilanguagedata, (CricketFunctions.GetTargetData(matchAllData).
							getRemaningBall() < 100 ? "BALL" + CricketFunctions.Plural(CricketFunctions.GetTargetData(matchAllData).getRemaningBall()).toUpperCase() : "OVERS"), 
							"", null, 0, foreignLanguageDataList);
					CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$NormalAll$OutForSection5$Section3$Data$Side" + WhichSide + 
							"$ToWin$Split$Grp2$Stat$English$txt_Stat*GEOM*TEXT SET ", config, Constants.BCCI, print_writers, foreignLanguageData);
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$NormalAll$OutForSection5$Section3$Data$Side" + WhichSide + 
							"$ToWin$Split$Grp2$txt_Value*GEOM*TEXT SET " + (CricketFunctions.GetTargetData(matchAllData).getRemaningBall()<100 ? 
									CricketFunctions.GetTargetData(matchAllData).getRemaningBall() : CricketFunctions.OverBalls(0, CricketFunctions.GetTargetData(matchAllData)
											.getRemaningBall())) + "\0", print_writers);
					break;
				case "NEW_BALL_DUE":
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$NormalAll$OutForSection5$Section3$Data$Side" + WhichSide + 
							"$select_GraphicsType*FUNCTION*Omo*vis_con SET 9\0", print_writers);
					
//					if(CricketFunctions.getnewBallOver(matchAllData, inning.getInningNumber()) != null) {
//						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$NormalAll$OutForSection5$Section3$Data$Side" + WhichSide + 
//								"$GenericText$select_Line*FUNCTION*Omo*vis_con SET 2\0", print_writers);
//						
//						CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$NormalAll$OutForSection5$Section3$Data$Side" + WhichSide + 
//								"$GenericText$Text1$select_Language*FUNCTION*Omo*vis_con SET ", config, Constants.BCCI, print_writers, foreignLanguageOmo);
//						foreignLanguageData = CricketFunctions.AssembleMultiLanguageData(CricketUtil.DICTIONARY, "", multilanguagedata, "NEW BALL DUE IN", "", null, 0, foreignLanguageDataList);
//						CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$NormalAll$OutForSection5$Section3$Data$Side" + WhichSide + 
//								"$GenericText$Text1$English$txt_Text1*GEOM*TEXT SET ", config, Constants.BCCI, print_writers, foreignLanguageData);
//						
//						CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$NormalAll$OutForSection5$Section3$Data$Side" + WhichSide + 
//								"$GenericText$Text2$select_Language*FUNCTION*Omo*vis_con SET ", config, Constants.BCCI, print_writers, foreignLanguageOmo);
//						foreignLanguageData = CricketFunctions.AssembleMultiLanguageData(CricketUtil.DICTIONARY, "", multilanguagedata, 
//								CricketFunctions.getnewBallOver(matchAllData, inning.getInningNumber()) + " OVERS", "", null, 0, foreignLanguageDataList);
//						CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$NormalAll$OutForSection5$Section3$Data$Side" + WhichSide + 
//								"$GenericText$Text2$English$txt_Text2*GEOM*TEXT SET ", config, Constants.BCCI, print_writers, foreignLanguageData);
//					}else {
//						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$NormalAll$OutForSection5$Section3$Data$Side" + WhichSide + 
//								"$GenericText$select_Line*FUNCTION*Omo*vis_con SET 1\0", print_writers);
//						
//						CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$NormalAll$OutForSection5$Section3$Data$Side" + WhichSide + 
//								"$GenericText$Text1$select_Language*FUNCTION*Omo*vis_con SET ", config, Constants.BCCI, print_writers, foreignLanguageOmo);
//						foreignLanguageData = CricketFunctions.AssembleMultiLanguageData(CricketUtil.DICTIONARY, "", multilanguagedata, "NEW BALL AVAILABE", "", 
//								null, 0, foreignLanguageDataList);
//						CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$NormalAll$OutForSection5$Section3$Data$Side" + WhichSide + 
//								"$GenericText$Text1$English$txt_Text1*GEOM*TEXT SET ", config, Constants.BCCI, print_writers, foreignLanguageData);
//					}
					break;
					
				case "LEAD_TRAIL_EQUATION":
					String leadTrail="";
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$NormalAll$OutForSection5$Section3$Data$Side" + WhichSide + 
							"$select_GraphicsType*FUNCTION*Omo*vis_con SET 5\0", print_writers);
					if(matchAllData.getMatch().getInning().get(1).getIsCurrentInning().equalsIgnoreCase(CricketUtil.YES)) {
						if(CricketFunctions.GetTeamRunsAhead(2,matchAllData) > 0) {
							leadTrail = matchAllData.getMatch().getInning().get(1).getBatting_team().getTeamName4() + " LEAD BY " + CricketFunctions.GetTeamRunsAhead(2,matchAllData) 
										+ " RUN" + CricketFunctions.Plural(CricketFunctions.GetTeamRunsAhead(2,matchAllData)).toUpperCase();
						} else if(CricketFunctions.GetTeamRunsAhead(2,matchAllData) == 0) {
							leadTrail = "SCORES ARE LEVEL";
						} else {
							leadTrail = matchAllData.getMatch().getInning().get(1).getBatting_team().getTeamName4() + " TRAIL BY " + (-1 * CricketFunctions.GetTeamRunsAhead(2, 
									matchAllData)) + " RUN" + CricketFunctions.Plural(-1 * CricketFunctions.GetTeamRunsAhead(2,matchAllData)).toUpperCase();
						}
					}else if(matchAllData.getMatch().getInning().get(2).getIsCurrentInning().equalsIgnoreCase(CricketUtil.YES)) {
						if(CricketFunctions.GetTeamRunsAhead(3,matchAllData) > 0) {
							leadTrail = matchAllData.getMatch().getInning().get(2).getBatting_team().getTeamName4() + " LEAD BY " + CricketFunctions.GetTeamRunsAhead(3,matchAllData) 
										+ " RUN" + CricketFunctions.Plural(CricketFunctions.GetTeamRunsAhead(3,matchAllData)).toUpperCase();
						} else if(CricketFunctions.GetTeamRunsAhead(3,matchAllData) == 0) {
							leadTrail = "SCORES ARE LEVEL";
						} else {
							leadTrail = matchAllData.getMatch().getInning().get(2).getBatting_team().getTeamName4() + " TRAIL BY " + (-1 * CricketFunctions.GetTeamRunsAhead(3, 
									matchAllData)) + " RUN" + CricketFunctions.Plural(-1 * CricketFunctions.GetTeamRunsAhead(3,matchAllData)).toUpperCase();
						}
					}
					
					CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$NormalAll$OutForSection5$Section3$Data$Side" + WhichSide + 
							"$LeadTrail$Text$select_Language*FUNCTION*Omo*vis_con SET ", config, Constants.BCCI, print_writers, foreignLanguageOmo);
					foreignLanguageData = CricketFunctions.AssembleMultiLanguageData(CricketUtil.DICTIONARY, "", multilanguagedata, leadTrail, "", null, 0, foreignLanguageDataList);
					CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$NormalAll$OutForSection5$Section3$Data$Side" + WhichSide + 
							"$LeadTrail$Text$English$txt_Text*GEOM*TEXT SET ", config, Constants.BCCI, print_writers, foreignLanguageData);
					break;
				case "BOTH_TEAMS_SCORE":
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$NormalAll$OutForSection5$Section3$Data$Side" + WhichSide + 
							"$select_GraphicsType*FUNCTION*Omo*vis_con SET 3\0", print_writers);
					inning = matchAllData.getMatch().getInning().stream().filter(inn->inn.getIsCurrentInning().equalsIgnoreCase(CricketUtil.YES)).findAny().orElse(null);
					
					String team1="", team2="",teamNameInInning3="",teamNameInInning1="",teamNameInInning1ball="";
					
					if(inning.getInningNumber()>2) {
						teamNameInInning3 = matchAllData.getMatch().getInning().get(2).getBatting_team().getTeamName4();
						teamNameInInning1 = matchAllData.getMatch().getInning().get(0).getBatting_team().getTeamName4();
						teamNameInInning1ball = matchAllData.getMatch().getInning().get(0).getBowling_team().getTeamName4();
						
						if (teamNameInInning3.equalsIgnoreCase(teamNameInInning1)) {
						    team1 = CricketFunctions.getTeamScore(matchAllData.getMatch().getInning().get(0), "-", false)
						    		+ CricketFunctions.isDeclared(matchAllData.getMatch().getInning().get(0));
						    
						    team2 = CricketFunctions.getTeamScore(matchAllData.getMatch().getInning().get(1), "-", false)
						    		+ CricketFunctions.isDeclared(matchAllData.getMatch().getInning().get(1));
						} else {
						    team2 = CricketFunctions.getTeamScore(matchAllData.getMatch().getInning().get(0), "-", false)
					        		+ CricketFunctions.isDeclared(matchAllData.getMatch().getInning().get(0));
						   
							team1 = CricketFunctions.getTeamScore(matchAllData.getMatch().getInning().get(1), "-", false)
							        + CricketFunctions.isDeclared(matchAllData.getMatch().getInning().get(1));
						}
						if(inning.getInningNumber()==3) {
							team1 += " & "+ CricketFunctions.getTeamScore(inning, "-", false)+ CricketFunctions.isDeclared(inning);
						}else {
							team1+= " & "+ CricketFunctions.getTeamScore(matchAllData.getMatch().getInning().get(2), "-", false)
									+ CricketFunctions.isDeclared(matchAllData.getMatch().getInning().get(2));

							team2+= " & "+ CricketFunctions.getTeamScore(inning, "-", false) + CricketFunctions.isDeclared(inning);
						}
					}else {
						team1 = CricketFunctions.getTeamScore(matchAllData.getMatch().getInning().get(0), "-", false) + 
								CricketFunctions.isDeclared(matchAllData.getMatch().getInning().get(0));
						
						team2 = CricketFunctions.getTeamScore(matchAllData.getMatch().getInning().get(1), "-", false) + 
								CricketFunctions.isDeclared(matchAllData.getMatch().getInning().get(1));
					}
					
					CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$NormalAll$OutForSection5$Section3$Data$Side" + WhichSide + 
							"$RunsRates$Grp1$Stat$select_Language*FUNCTION*Omo*vis_con SET ", config, Constants.BCCI, print_writers, foreignLanguageOmo);
					foreignLanguageData = CricketFunctions.AssembleMultiLanguageData(CricketUtil.DICTIONARY, "", multilanguagedata, teamNameInInning1, "", null, 0, foreignLanguageDataList);
					CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$NormalAll$OutForSection5$Section3$Data$Side" + WhichSide + 
							"$RunsRates$Grp1$Stat$English$txt_Stat*GEOM*TEXT SET ", config, Constants.BCCI, print_writers, foreignLanguageData);
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$NormalAll$OutForSection5$Section3$Data$Side" + WhichSide + 
							"$RunsRates$Grp1$txt_Value*GEOM*TEXT SET " + team1 + "\0", print_writers);
					
					CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$NormalAll$OutForSection5$Section3$Data$Side" + WhichSide + 
							"$RunsRates$Grp2$Stat$select_Language*FUNCTION*Omo*vis_con SET ", config, Constants.BCCI, print_writers, foreignLanguageOmo);
					foreignLanguageData = CricketFunctions.AssembleMultiLanguageData(CricketUtil.DICTIONARY, "", multilanguagedata, teamNameInInning1ball, "", null, 0, foreignLanguageDataList);
					CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$NormalAll$OutForSection5$Section3$Data$Side" + WhichSide + 
							"$RunsRates$Grp2$Stat$English$txt_Stat*GEOM*TEXT SET ", config, Constants.BCCI, print_writers, foreignLanguageData);
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$NormalAll$OutForSection5$Section3$Data$Side" + WhichSide + 
							"$RunsRates$Grp2$txt_Value*GEOM*TEXT SET " + team2 + "\0", print_writers);
					
					break;
				case "REVIEWS_REMAINING":
					Review reviewRemaining = CricketFunctions.getReviewRemaining(matchAllData);
					String[] parts = reviewRemaining.getReviewStatus().split(",");
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$NormalAll$OutForSection5$Section3$Data$Side" + WhichSide + 
							"$select_GraphicsType*FUNCTION*Omo*vis_con SET 1\0", print_writers);
					
					CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$NormalAll$OutForSection5$Section3$Data$Side" + WhichSide + 
							"$ReviewRemaining$Header$HeadText$select_Language*FUNCTION*Omo*vis_con SET ", config, Constants.BCCI, print_writers, foreignLanguageOmo);
					foreignLanguageData = CricketFunctions.AssembleMultiLanguageData(CricketUtil.DICTIONARY, "", multilanguagedata, "REVIEWS REMAINING", "", 
							null, 0, foreignLanguageDataList);
					CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$NormalAll$OutForSection5$Section3$Data$Side" + WhichSide + 
							"$ReviewRemaining$Header$HeadText$English$txt_Header*GEOM*TEXT SET ", config, Constants.BCCI, print_writers, foreignLanguageData);
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$NormalAll$OutForSection5$Section3$Data$Side" + WhichSide + 
							"$ReviewRemaining$Grp1$StatOut$txt_Stat*GEOM*TEXT SET " + matchAllData.getSetup().getHomeTeam().getTeamName4() + "\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$NormalAll$OutForSection5$Section3$Data$Side" + WhichSide + 
							"$ReviewRemaining$Grp2$StatOut$txt_Stat*GEOM*TEXT SET " + matchAllData.getSetup().getAwayTeam().getTeamName4() + "\0", print_writers);
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$NormalAll$OutForSection5$Section3$Data$Side" + WhichSide + 
							"$ReviewRemaining$Grp1$img_Text1$txt_Value*GEOM*TEXT SET " + Integer.parseInt(parts[0]) + "\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$NormalAll$OutForSection5$Section3$Data$Side" + WhichSide + 
							"$ReviewRemaining$Grp2$img_Text1$txt_Value*GEOM*TEXT SET " + Integer.parseInt(parts[1]) + "\0", print_writers);
					break;
				case "CURR_PARTNERSHIP": case "EXTRAS": case CricketUtil.BOUNDARY: case "CRR":
					inning = matchAllData.getMatch().getInning().stream().filter(inn -> inn.getIsCurrentInning().equalsIgnoreCase(CricketUtil.YES)).findAny().orElse(null);
					if(inning == null) {
						return "populateVizInfobarMiddleSection: Inning returned is NULL";
					}
					
					switch(infobar.getSection3().toUpperCase()) {
					case "CRR":
						this_data_str = new ArrayList<String>();
						this_data_str.add(CricketFunctions.getlastthirtyballsdata(matchAllData, slashOrDash, matchAllData.getEventFile().getEvents(), lastXballs));
						if(this_data_str.get(this_data_str.size()-1) == null || this_data_str.get(this_data_str.size()-1).split(slashOrDash).length > 4) {
							return "populateVizInfobarMiddleSection: Last " + lastXballs + " Balls data returned invalid";
						}
						
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$NormalAll$OutForSection5$Section3$Data$Side" + WhichSide + 
								"$select_GraphicsType*FUNCTION*Omo*vis_con SET 3\0", print_writers);
						
						CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$NormalAll$OutForSection5$Section3$Data$Side" + WhichSide + 
								"$ReviewRemaining$Header$HeadText$select_Language*FUNCTION*Omo*vis_con SET ", config, Constants.BCCI, print_writers, foreignLanguageOmo);
						foreignLanguageData = CricketFunctions.AssembleMultiLanguageData(CricketUtil.DICTIONARY, "", multilanguagedata, "CRR", "", null, 0, foreignLanguageDataList);
						CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$NormalAll$OutForSection5$Section3$Data$Side" + WhichSide + 
								"$RunsRates$Grp1$Stat$English$txt_Stat*GEOM*TEXT SET ", config, Constants.BCCI, print_writers, foreignLanguageData);
						
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$NormalAll$OutForSection5$Section3$Data$Side" + WhichSide + 
								"$RunsRates$Grp1$txt_Value*GEOM*TEXT SET " + CricketFunctions.generateRunRate(inning.getTotalRuns(),inning.getTotalOvers(), 
										inning.getTotalBalls(), 2,matchAllData) + "\0", print_writers);
						
						CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$NormalAll$OutForSection5$Section3$Data$Side" + WhichSide + 
								"$RunsRates$Grp2$Stat$select_Language*FUNCTION*Omo*vis_con SET ", config, Constants.BCCI, print_writers, foreignLanguageOmo);
						foreignLanguageData = CricketFunctions.AssembleMultiLanguageData(CricketUtil.DICTIONARY, "", multilanguagedata, "LAST " + lastXballs + " BALLS", 
								"", null, 0, foreignLanguageDataList);
						CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$NormalAll$OutForSection5$Section3$Data$Side" + WhichSide + 
								"$RunsRates$Grp1$Stat$English$txt_Stat*GEOM*TEXT SET ", config, Constants.BCCI, print_writers, foreignLanguageData);
						
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$NormalAll$OutForSection5$Section3$Data$Side" + WhichSide + 
								"$RunsRates$Grp2$txt_Value*GEOM*TEXT SET " + CricketFunctions.generateRunRate(Integer.valueOf(this_data_str.get(this_data_str.size()-1).
										split(slashOrDash)[0]), 0, lastXballs, 2,matchAllData) + "\0", print_writers);
						break;
					case CricketUtil.BOUNDARY:
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$NormalAll$OutForSection5$Section3$Data$Side" + WhichSide + 
								"$select_GraphicsType*FUNCTION*Omo*vis_con SET 6\0", print_writers);
						
						CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$NormalAll$OutForSection5$Section3$Data$Side" + WhichSide + 
								"$Boundaries$Grp1$Stat$select_Language*FUNCTION*Omo*vis_con SET ", config, Constants.BCCI, print_writers, foreignLanguageOmo);
						foreignLanguageData = CricketFunctions.AssembleMultiLanguageData(CricketUtil.DICTIONARY, "", multilanguagedata, "FOUR" + 
								CricketFunctions.Plural(inning.getTotalFours()).toUpperCase(), "", null, 0, foreignLanguageDataList);
						CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$NormalAll$OutForSection5$Section3$Data$Side" + WhichSide + 
								"$Boundaries$Grp1$Stat$English$txt_Stat*GEOM*TEXT SET ", config, Constants.BCCI, print_writers, foreignLanguageData);
						
						CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$NormalAll$OutForSection5$Section3$Data$Side" + WhichSide + 
								"$Boundaries$Grp2$Stat$select_Language*FUNCTION*Omo*vis_con SET ", config, Constants.BCCI, print_writers, foreignLanguageOmo);
						foreignLanguageData = CricketFunctions.AssembleMultiLanguageData(CricketUtil.DICTIONARY, "", multilanguagedata, (inning.getTotalSixes() == 1 ? "SIX" : "SIXES"),
								"", null, 0, foreignLanguageDataList);
						CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$NormalAll$OutForSection5$Section3$Data$Side" + WhichSide + 
								"$Boundaries$Grp2$Stat$English$txt_Stat*GEOM*TEXT SET ", config, Constants.BCCI, print_writers, foreignLanguageData);
					
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$NormalAll$OutForSection5$Section3$Data$Side" + WhichSide + 
								"$Boundaries$Grp1$txt_Value*GEOM*TEXT SET " + inning.getTotalFours() + "\0", print_writers);
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$NormalAll$OutForSection5$Section3$Data$Side" + WhichSide + 
								"$Boundaries$Grp2$txt_Value*GEOM*TEXT SET " + inning.getTotalSixes() + "\0", print_writers);
						break;
					
					case "CURR_PARTNERSHIP":
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$NormalAll$OutForSection5$Section3$Data$Side" + WhichSide + 
								"$select_GraphicsType*FUNCTION*Omo*vis_con SET 2\0", print_writers);
						
						CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$NormalAll$OutForSection5$Section3$Data$Side" + WhichSide + 
								"$PartnershipGrp$Grp1$Text$select_Language*FUNCTION*Omo*vis_con SET ", config, Constants.BCCI, print_writers, foreignLanguageOmo);
						foreignLanguageData = CricketFunctions.AssembleMultiLanguageData(CricketUtil.DICTIONARY, "", multilanguagedata, "CURRENT PARTNERSHIP",
								"", null, 0, foreignLanguageDataList);
						CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$NormalAll$OutForSection5$Section3$Data$Side" + WhichSide + 
								"$PartnershipGrp$Grp1$Text$English$txt_Header*GEOM*TEXT SET ", config, Constants.BCCI, print_writers, foreignLanguageData);
						
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$NormalAll$OutForSection5$Section3$Data$Side" + WhichSide + 
								"$PartnershipGrp$Grp2$ValueGrp$txt_Runs*GEOM*TEXT SET " + inning.getPartnerships().get(inning.getPartnerships().size()-1).getTotalRuns() + "\0", print_writers);
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$NormalAll$OutForSection5$Section3$Data$Side" + WhichSide + 
								"$PartnershipGrp$Grp2$ValueGrp$txt_Balls*GEOM*TEXT SET " + inning.getPartnerships().get(inning.getPartnerships().size()-1).getTotalBalls() + "\0", print_writers);
						break;
					case "EXTRAS":
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$NormalAll$OutForSection5$Section3$Data$Side" + WhichSide + 
								"$select_GraphicsType*FUNCTION*Omo*vis_con SET 7\0", print_writers);
						
						CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$NormalAll$OutForSection5$Section3$Data$Side" + WhichSide + 
								"$Extras$Text$select_Language*FUNCTION*Omo*vis_con SET ", config, Constants.BCCI, print_writers, foreignLanguageOmo);
						foreignLanguageData = CricketFunctions.AssembleMultiLanguageData(CricketUtil.DICTIONARY, "", multilanguagedata, "EXTRAS","", null, 0, foreignLanguageDataList);
						CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$NormalAll$OutForSection5$Section3$Data$Side" + WhichSide + 
								"$Extras$Text$English$txt_Header*GEOM*TEXT SET ", config, Constants.BCCI, print_writers, foreignLanguageData);
						
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$NormalAll$OutForSection5$Section3$Data$Side" + WhichSide + 
								"$Extras$ValueGrp$txt_Value1*GEOM*TEXT SET " + inning.getTotalExtras() + "\0", print_writers);
						
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$NormalAll$OutForSection5$Section3$Data$Side" + WhichSide + 
								"$Extras$ValueGrp$txt_Value2*GEOM*TEXT SET (" + inning.getTotalWides() + " WD, " + inning.getTotalNoBalls() + " NB, " + inning.getTotalLegByes()
								+ " B, " + inning.getTotalLegByes() + (inning.getTotalPenalties() == 0 ? " LB" : " LB, " + inning.getTotalPenalties() + " P") + ")\0", print_writers);
						break;
					}
					break;
				}
			}
			break;
		}
		return Constants.OK;
	}
	public String populateFullSection(List<PrintWriter> print_writers, MatchAllData matchAllData, int WhichSide) throws JsonMappingException, JsonProcessingException, InterruptedException {
		switch(config.getBroadcaster()) {
		case Constants.TRI_SERIES:  case Constants.MT20: case Constants.BAN_AFG_SERIES: case Constants.ACC: case Constants.TG20:
			populateSectionAnalytics(print_writers, matchAllData, WhichSide);
			break;
		case Constants.BCCI:
			switch(infobar.getSectionAnalytics().toUpperCase()) {
			case "BLANK":
//				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$NormalAll$Analytics$AnalyticsDataAll$Side" + WhichSide + 
//						"$select_GraphicsType*FUNCTION*Omo*vis_con SET 0\0", print_writers);
				break;
			case "NEXTTOBAT":
				int inAtPosition = inning.getTotalWickets();
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$NormalAll$Analytics$AnalyticsDataAll$Side" + WhichSide + 
						"$select_GraphicsType*FUNCTION*Omo*vis_con SET 2\0", print_writers);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$NormalAll$Analytics$AnalyticsDataAll$Side" + WhichSide + 
						"$NextToBat$Header$Header$HeadText$select_Language*FUNCTION*Omo*vis_con SET 1\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$NormalAll$Analytics$AnalyticsDataAll$Side" + WhichSide + 
						"$NextToBat$Header$Header$HeadText$English$txt_Header*GEOM*TEXT SET NEXT TO BAT\0", print_writers);
				
				for(BattingCard bc : inning.getBattingCard()) {
					if(rowId >= 3) break;
					switch (bc.getStatus()) {
					case CricketUtil.STILL_TO_BAT:
						if(bc.getHowOut() != null && !bc.getHowOut().equalsIgnoreCase(CricketUtil.RETIRED_HURT)) continue;
						rowId++;
						inAtPosition++;
						
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$NormalAll$Analytics$AnalyticsDataAll$Side" + WhichSide + 
								"$NextToBat$NextData$Grp" + rowId + "$NameGrp$Text1$select_Language*FUNCTION*Omo*vis_con SET 1\0", print_writers);
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$NormalAll$Analytics$AnalyticsDataAll$Side" + WhichSide + 
								"$NextToBat$NextData$Grp" + rowId + "$NameGrp$Text1$English$txt_FirstName*GEOM*TEXT SET " + "IN AT " + inAtPosition + "\0", print_writers);
						
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$NormalAll$Analytics$AnalyticsDataAll$Side" + WhichSide + 
								"$NextToBat$NextData$Grp" + rowId + "$NameGrp$Text2$select_Language*FUNCTION*Omo*vis_con SET 1\0", print_writers);
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$NormalAll$Analytics$AnalyticsDataAll$Side" + WhichSide + 
								"$NextToBat$NextData$Grp" + rowId + "$NameGrp$Text2$English$txt_LastName*GEOM*TEXT SET " + bc.getPlayer().getTicker_name() + "\0", print_writers);
						break;
					}
				}
				break;
			case "LASTXBALLS":
				this_data_str = new ArrayList<String>();
				this_data_str.add(CricketFunctions.getlastthirtyballsdata(matchAllData, slashOrDash, matchAllData.getEventFile().getEvents(), lastXballs));
				if(this_data_str.get(this_data_str.size()-1) == null || this_data_str.get(this_data_str.size()-1).split(slashOrDash).length > 4) {
					return "populateVizInfobarMiddleSection: Last " + lastXballs + " Balls data returned invalid";
				}

				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$NormalAll$Analytics$AnalyticsDataAll$Side" + WhichSide + 
						"$select_GraphicsType*FUNCTION*Omo*vis_con SET 5\0", print_writers);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$NormalAll$Analytics$AnalyticsDataAll$Side" + WhichSide + 
						"$RunsInLastX$HeaderGrp$Header$HeaderText$select_Language*FUNCTION*Omo*vis_con SET 1\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$NormalAll$Analytics$AnalyticsDataAll$Side" + WhichSide + 
						"$RunsInLastX$HeaderGrp$Header$HeaderText$English$txt_Header*GEOM*TEXT SET " + "LAST " + lastXballs + " BALLS" + "\0", print_writers);
				
//				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$NormalAll$Analytics$AnalyticsDataAll$Side" + WhichSide + 
//						"$RunsInLastX$DataGrps$Grp1$Stat*FUNCTION*Omo*vis_con SET 1\0", print_writers);
//				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$NormalAll$Analytics$AnalyticsDataAll$Side" + WhichSide + 
//						"$RunsInLastX$DataGrps$Grp1$Stat*GEOM*TEXT SET " + "RUN" + CricketFunctions.Plural(Integer.valueOf(this_data_str.get(this_data_str.size()-1).
//								split(slashOrDash)[0])) + "\0", print_writers);
//				
//				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$NormalAll$Analytics$AnalyticsDataAll$Side" + WhichSide + 
//						"$RunsInLastX$DataGrps$Grp2$Stat*FUNCTION*Omo*vis_con SET 1\0", print_writers);
//				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$NormalAll$Analytics$AnalyticsDataAll$Side" + WhichSide + 
//						"$RunsInLastX$DataGrps$Grp2$Stat*GEOM*TEXT SET " + "WICKET" + CricketFunctions.Plural(Integer.valueOf(this_data_str.get(this_data_str.size()-1).
//								split(slashOrDash)[1])) + "\0", print_writers);
//				
//				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$NormalAll$Analytics$AnalyticsDataAll$Side" + WhichSide + 
//						"$RunsInLastX$DataGrps$Grp3$Stat*FUNCTION*Omo*vis_con SET 1\0", print_writers);
//				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$NormalAll$Analytics$AnalyticsDataAll$Side" + WhichSide + 
//						"$RunsInLastX$DataGrps$Grp3$Stat*GEOM*TEXT SET " + "FOUR" + CricketFunctions.Plural(Integer.valueOf(this_data_str.get(this_data_str.size()-1).
//								split(slashOrDash)[2])) + "\0", print_writers);
//				
//				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$NormalAll$Analytics$AnalyticsDataAll$Side" + WhichSide + 
//						"$RunsInLastX$DataGrps$Grp4$Stat*FUNCTION*Omo*vis_con SET 1\0", print_writers);
//				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$NormalAll$Analytics$AnalyticsDataAll$Side" + WhichSide + 
//						"$RunsInLastX$DataGrps$Grp4$Stat*GEOM*TEXT SET " + (Integer.valueOf(this_data_str.get(this_data_str.size()-1).split(slashOrDash)[3]) == 1
//						? "SIX" : "SIXES") + "\0", print_writers);
//				
//				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$NormalAll$Analytics$AnalyticsDataAll$Side" + WhichSide + 
//						"$RunsInLastX$DataGrps$Grp5$Stat*FUNCTION*Omo*vis_con SET 1\0", print_writers);
//				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$NormalAll$Analytics$AnalyticsDataAll$Side" + WhichSide + 
//						"$RunsInLastX$DataGrps$Grp5$Stat*GEOM*TEXT SET RUN RATE\0", print_writers);
				
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$NormalAll$Analytics$AnalyticsDataAll$Side" + WhichSide + 
						"$RunsInLastX$DataGrps$Grp1$Value*GEOM*TEXT SET " + this_data_str.get(this_data_str.size()-1).split(slashOrDash)[0] + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$NormalAll$Analytics$AnalyticsDataAll$Side" + WhichSide + 
						"$RunsInLastX$DataGrps$Grp2$Value*GEOM*TEXT SET " + this_data_str.get(this_data_str.size()-1).split(slashOrDash)[1] + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$NormalAll$Analytics$AnalyticsDataAll$Side" + WhichSide + 
						"$RunsInLastX$DataGrps$Grp3$Value*GEOM*TEXT SET " + this_data_str.get(this_data_str.size()-1).split(slashOrDash)[2] + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$NormalAll$Analytics$AnalyticsDataAll$Side" + WhichSide + 
						"$RunsInLastX$DataGrps$Grp4$Value*GEOM*TEXT SET " + this_data_str.get(this_data_str.size()-1).split(slashOrDash)[3] + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$NormalAll$Analytics$AnalyticsDataAll$Side" + WhichSide + 
						"$RunsInLastX$DataGrps$Grp5$Value*GEOM*TEXT SET " + CricketFunctions.generateRunRate(Integer.valueOf(this_data_str.get(this_data_str.size()-1).
								split(slashOrDash)[0]), 0, lastXballs, 2,matchAllData) + "\0", print_writers);
				
				break;
			case "SESSION_SUMMARY":
				if (matchAllData != null && matchAllData.getMatch() != null && matchAllData.getMatch().getDaysSessions() != null &&
			    !matchAllData.getMatch().getDaysSessions().isEmpty()) {
					DaySession last = matchAllData.getMatch().getDaysSessions().get(matchAllData.getMatch().getDaysSessions().size() - 1);
					 int balls =(last.getTotalBalls()>0?last.getTotalBalls():0);
					 int Runs =(last.getTotalRuns()>0?last.getTotalRuns():0);
					 int Wicket =(last.getTotalWickets()>0?last.getTotalWickets():0);
					 
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$NormalAll$Analytics$AnalyticsDataAll$Side" + WhichSide + 
							"$select_GraphicsType*FUNCTION*Omo*vis_con SET 10\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$NormalAll$Analytics$AnalyticsDataAll$Side" + WhichSide + 
							"$SessionSummary$HeaderGrp$Header1$select_Language*FUNCTION*Omo*vis_con SET 1\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$NormalAll$Analytics$AnalyticsDataAll$Side" + WhichSide + 
							"$SessionSummary$HeaderGrp$Header1$English$txt_Header1*GEOM*TEXT SET " + "THIS" + "\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$NormalAll$Analytics$AnalyticsDataAll$Side" + WhichSide + 
							"$SessionSummary$HeaderGrp$Header2$select_Language*FUNCTION*Omo*vis_con SET 1\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$NormalAll$Analytics$AnalyticsDataAll$Side" + WhichSide + 
							"$SessionSummary$HeaderGrp$Header2$English$txt_Header2*GEOM*TEXT SET " + "SESSION" + "\0", print_writers);
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$NormalAll$Analytics$AnalyticsDataAll$Side" + WhichSide + 
							"$SessionSummary$DataGrps$Grp1$Stat*GEOM*TEXT SET " + "SCORE" + "\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$NormalAll$Analytics$AnalyticsDataAll$Side" + WhichSide + 
							"$SessionSummary$DataGrps$Grp1$txt_Value*GEOM*TEXT SET " + Runs + "-" + Wicket + "\0", print_writers);
		
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$NormalAll$Analytics$AnalyticsDataAll$Side" + WhichSide + 
							"$SessionSummary$DataGrps$Grp2$Stat*GEOM*TEXT SET " + "OVER"+ "\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$NormalAll$Analytics$AnalyticsDataAll$Side" + WhichSide + 
							"$SessionSummary$DataGrps$Grp2$txt_Value*GEOM*TEXT SET " +CricketFunctions.OverBalls(0, balls) + " OVER" + CricketFunctions.Plural(balls/6).toUpperCase()  + "\0", print_writers);
		
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$NormalAll$Analytics$AnalyticsDataAll$Side" + WhichSide + 
							"$SessionSummary$DataGrps$Grp3$Stat*GEOM*TEXT SET " + "OVER RATE" + "\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$NormalAll$Analytics$AnalyticsDataAll$Side" + WhichSide + 
							"$SessionSummary$DataGrps$Grp3$txt_Value*GEOM*TEXT SET " + CricketFunctions.BetterOverRate(0, balls, last.getTotalSeconds(), "", false)+ "\0", print_writers);
		
				}
				
				break;
			case "TIMELINE":
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$NormalAll$Analytics$AnalyticsDataAll$Side" + WhichSide + 
						"$select_GraphicsType*FUNCTION*Omo*vis_con SET 6\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$NormalAll$Analytics$AnalyticsDataAll$Side" + WhichSide + 
						"$TimeLine$HeaderGrp$select_Language*FUNCTION*Omo*vis_con SET 1\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$NormalAll$Analytics$AnalyticsDataAll$Side" + WhichSide + 
						"$TimeLine$HeaderGrp$English$txt_Header*GEOM*TEXT SET " + "TIMELINE" + "\0", print_writers);
				String data = CricketFunctions.getlastthirtyballsdata(matchAllData, slashOrDash, matchAllData.getEventFile().getEvents(), 20);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$NormalAll$Analytics$AnalyticsDataAll$Side" + WhichSide + 
						"$TimeLine$TimelineData$DataGrps$select_Language*FUNCTION*Omo*vis_con SET 1\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$NormalAll$Analytics$AnalyticsDataAll$Side" + WhichSide + 
						"$TimeLine$TimelineData$DataGrps$English$txt_Stat*GEOM*TEXT SET " + "LAST 20 BALLS:" + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$NormalAll$Analytics$AnalyticsDataAll$Side" + WhichSide + 
						"$TimeLine$TimelineData$DataGrps$txt_Value*GEOM*TEXT SET " +data.substring(0, data.indexOf("-", data.indexOf("-") + 1)) + "\0", print_writers);
				
				this_data_str = new ArrayList<String>();
				this_data_str.add(String.join(",",  new ArrayList<>(Arrays.asList(IndexController.MatchStats.getTimeLine().split(",")))
				        .stream().map(s -> s.replace("WIDE", "WD")
				                   .replace("NO_BALL", "NB")
				                   .replace("LEG_BYE", "LB")
				                   .replace("BYE", "B")
				                   .replace("PENALTY", "PN")
				                   .replace("LOG_WICKET", "W")
				                   .replace("WICKET", "W"))
				        .collect(Collectors.collectingAndThen(Collectors.toList(), list -> {Collections.reverse(list); return list;}))
				        .toArray(new String[0])));
				String[] elements = this_data_str.get(this_data_str.size() - 1).split(",");
				
				if(this_data_str.get(this_data_str.size()-1) == null || this_data_str.get(this_data_str.size()-1).split(",").length < 21) {
					return "populateVizInfobarRightBottom: TIMELINE data returned invalid";
				}
				if (elements.length > 26) {
				    elements = Arrays.copyOfRange(elements, elements.length - 26, elements.length);
				}
				for(int i=1;i<=26;i++) {
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$NormalAll$Analytics$AnalyticsDataAll$Side" + WhichSide 
						+ "$TimeLine$TimelineData$TimeLineGrp$TimeLineGrp"+ i +"*ACTIVE SET 0\0", print_writers);
				}
				//Collections.reverse(Arrays.asList(elements));
				for(int iBall = 0; iBall < elements.length; iBall++) {
					if(iBall < 26) {
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$NormalAll$Analytics$AnalyticsDataAll$Side" + WhichSide 
								+ "$TimeLine$TimelineData$TimeLineGrp$TimeLineGrp"+ (iBall+1) +"*ACTIVE SET 1\0", print_writers);
						switch (elements[iBall].toUpperCase()) {
						case "|":
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$NormalAll$Analytics$AnalyticsDataAll$Side" + WhichSide 
									+ "$TimeLine$TimelineData$TimeLineGrp$TimeLineGrp"+ (iBall+1) +"$select_DataType*FUNCTION*Omo*vis_con SET 0\0", print_writers);
							break;
						case CricketUtil.DOT:
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$NormalAll$Analytics$AnalyticsDataAll$Side" + WhichSide 
									+ "$TimeLine$TimelineData$TimeLineGrp$TimeLineGrp"+ (iBall+1) +"$select_DataType*FUNCTION*Omo*vis_con SET 1\0", print_writers);
							break;
						case CricketUtil.ONE: case CricketUtil.TWO: case CricketUtil.THREE: case CricketUtil.FIVE: case CricketUtil.FOUR: case CricketUtil.SIX:
						case "W":
							switch(elements[iBall].toUpperCase()) {
							case "W":
								CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$NormalAll$Analytics$AnalyticsDataAll$Side" + WhichSide 
										+ "$TimeLine$TimelineData$TimeLineGrp$TimeLineGrp"+ (iBall+1) +"$select_DataType*FUNCTION*Omo*vis_con SET 5\0", print_writers);
								break;
							default:
								CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$NormalAll$Analytics$AnalyticsDataAll$Side" + WhichSide 
										+ "$TimeLine$TimelineData$TimeLineGrp$TimeLineGrp"+ (iBall+1) +"$select_DataType*FUNCTION*Omo*vis_con SET 2\0", print_writers);
								CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$NormalAll$Analytics$AnalyticsDataAll$Side" + WhichSide 
										+ "$TimeLine$TimelineData$TimeLineGrp$TimeLineGrp"+ (iBall+1) +"$Runs$txt_Runs*GEOM*TEXT SET "+
										elements[iBall] + "\0", print_writers);
								break;
							}
							break;
						default:
							if(elements[iBall].toUpperCase().contains("BOUNDARY")) {
								if(elements[iBall].toUpperCase().equalsIgnoreCase("6BOUNDARY")) {
									CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$NormalAll$Analytics$AnalyticsDataAll$Side" + WhichSide 
											+ "$TimeLine$TimelineData$TimeLineGrp$TimeLineGrp"+ (iBall+1) +"$select_DataType*FUNCTION*Omo*vis_con SET 4\0", print_writers);
									CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$NormalAll$Analytics$AnalyticsDataAll$Side" + WhichSide 
											+ "$TimeLine$TimelineData$TimeLineGrp$TimeLineGrp"+ (iBall+1) +"$Six$txt_Six*GEOM*TEXT SET 6\0", print_writers);
								}else if(elements[iBall].toUpperCase().equalsIgnoreCase("4BOUNDARY")) {									
									CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$NormalAll$Analytics$AnalyticsDataAll$Side" + WhichSide 
											+ "$TimeLine$TimelineData$TimeLineGrp$TimeLineGrp"+ (iBall+1) +"$select_DataType*FUNCTION*Omo*vis_con SET 3\0", print_writers);
									CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$NormalAll$Analytics$AnalyticsDataAll$Side" + WhichSide 
											+ "$TimeLine$TimelineData$TimeLineGrp$TimeLineGrp"+ (iBall+1) +"$Fours$txt_Fours*GEOM*TEXT SET 4\0", print_writers);
								}
							}else {
								CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$NormalAll$Analytics$AnalyticsDataAll$Side" + WhichSide 
										+ "$TimeLine$TimelineData$TimeLineGrp$TimeLineGrp"+ (iBall+1) +"$select_DataType*FUNCTION*Omo*vis_con SET 6\0", print_writers);
								CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$NormalAll$Analytics$AnalyticsDataAll$Side" + WhichSide 
										+ "$TimeLine$TimelineData$TimeLineGrp$TimeLineGrp"+ (iBall+1) +"$2Line$txt_Text1*GEOM*TEXT SET "+
										elements[iBall] + "\0", print_writers);
								CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$NormalAll$Analytics$AnalyticsDataAll$Side" + WhichSide 
										+ "$TimeLine$TimelineData$TimeLineGrp$TimeLineGrp"+ (iBall+1) +"$2Line$txt_Text2*ACTIVE SET 0\0", print_writers);
							}
							break;
						}
					}
				}
				break;
			case "THIS_SPEED":
				inning = matchAllData.getMatch().getInning().stream().filter(inn -> inn.getInningNumber() == 1 &&
					inn.getIsCurrentInning().equalsIgnoreCase(CricketUtil.YES)).findAny().orElse(null);
				
				bowlingCard = inning.getBowlingCard().stream().filter(bc -> bc.getStatus().equalsIgnoreCase(CricketUtil.LAST + CricketUtil.BOWLER) ||
						bc.getStatus().equalsIgnoreCase(CricketUtil.CURRENT + CricketUtil.BOWLER)).findAny().orElse(null);
				
				if(bowlingCard.getSpeeds()== null) {
					return "Speed is null";
				}
				List<Speed> Speed = CricketFunctions.getThisOverSpeeds(bowlingCard,inning);
				
				if(Speed == null) {
					return "This Over Speed is null";
				}
				if(Speed.size() > 10) {
					return "This Over Speed count is more than 10.";
				}
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$NormalAll$Analytics$AnalyticsDataAll$Side" + WhichSide + 
						"$select_GraphicsType*FUNCTION*Omo*vis_con SET 11\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$NormalAll$Analytics$AnalyticsDataAll$Side" + WhichSide + 
						"$ThisOverSpeed$Data$HeaderGrp$Header$select_Language*FUNCTION*Omo*vis_con SET 1\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$NormalAll$Analytics$AnalyticsDataAll$Side" + WhichSide + 
						"$ThisOverSpeed$Data$HeaderGrp$Header$English$txt_Header*GEOM*TEXT SET " + "THIS OVER SPEED" + "\0", print_writers);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$NormalAll$Analytics$AnalyticsDataAll$Side" + WhichSide + 
						"$ThisOverSpeed$select_Image*FUNCTION*Omo*vis_con SET " + (containerName.equalsIgnoreCase("WithoutPhoto")? 0:1) + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$NormalAll$Analytics$AnalyticsDataAll$Side" + WhichSide + 
						"$ThisOverSpeed$MoveForImage*FUNCTION*Omo*vis_con SET " + (containerName.equalsIgnoreCase("WithoutPhoto")? 0:1) + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$NormalAll$Analytics$AnalyticsDataAll$Side" + WhichSide + 
						"$ThisOverSpeed$NameGrp$HeadText1$Shadow-Medium-Font*GEOM*TEXT SET " + bowlingCard.getPlayer().getFirstname() + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$NormalAll$Analytics$AnalyticsDataAll$Side" + WhichSide + 
						"$ThisOverSpeed$NameGrp$HeadText2$Shadow-Black-Font*GEOM*TEXT SET " + bowlingCard.getPlayer().getSurname() + "\0", print_writers);
				
				if (containerName.equalsIgnoreCase("WithPhoto")) {
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$NormalAll$Analytics$AnalyticsDataAll$Side" + WhichSide +
						"$ThisOverSpeed$ImageAll$img_Player*TEXTURE*IMAGE SET " +  bowlingCard.getPlayer().getPhoto() + "\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$NormalAll$Analytics$AnalyticsDataAll$Side" + WhichSide + 
						"$ThisOverSpeed$ImageAll$img_PlayerShadow*TEXTURE*IMAGE SET " + bowlingCard.getPlayer().getPhoto() + "\0", print_writers);
					
				}
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$NormalAll$Analytics$AnalyticsDataAll$Side" + WhichSide + 
						"$ThisOverSpeed$Data$BallsAll$select_BallNumbers*FUNCTION*Omo*vis_con SET " + Speed.size() + "\0", print_writers);
				
				for(int i=0; i < Speed.size(); i++) {
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$NormalAll$Analytics$AnalyticsDataAll$Side" + WhichSide + 
							"$ThisOverSpeed$Data$BallsAll$Grp" +(i+1) + "$txt_Stat*GEOM*TEXT SET " + (i+1) + "\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$NormalAll$Analytics$AnalyticsDataAll$Side" + WhichSide + 
							"$ThisOverSpeed$Data$BallsAll$Grp" +(i+1) + "$txt_Value*GEOM*TEXT SET " + Speed.get(i).getSpeedValue() + "\0", print_writers);
					
				}
				break;
			case "BATSMANTIMELINE":
				inning = matchAllData.getMatch().getInning().stream().filter(inn -> inn.getInningNumber() == 1 &&
					inn.getIsCurrentInning().equalsIgnoreCase(CricketUtil.YES)).findAny().orElse(null);
				BattingCard batter = inning.getBattingCard().stream().filter(bat->bat.getPlayer().getPlayerId()== FirstPlayerId).findAny().orElse(null);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$NormalAll$Analytics$AnalyticsDataAll$Side" + WhichSide + 
						"$select_GraphicsType*FUNCTION*Omo*vis_con SET 7\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$NormalAll$Analytics$AnalyticsDataAll$Side" + WhichSide + 
						"$BatsmanTimeLine$Data$Header$select_Language*FUNCTION*Omo*vis_con SET 1\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$NormalAll$Analytics$AnalyticsDataAll$Side" + WhichSide + 
						"$BatsmanTimeLine$Data$Header$English$txt_Header*GEOM*TEXT SET " + "BATTER TIMELINE" + "\0", print_writers);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$NormalAll$Analytics$AnalyticsDataAll$Side" + WhichSide + 
						"$BatsmanTimeLine$select_Image*FUNCTION*Omo*vis_con SET " + (containerName.equalsIgnoreCase("WithoutPhoto")? 0:1) + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$NormalAll$Analytics$AnalyticsDataAll$Side" + WhichSide + 
						"$BatsmanTimeLine$MoveForImage*FUNCTION*Omo*vis_con SET " + (containerName.equalsIgnoreCase("WithoutPhoto")? 0:1) + "\0", print_writers);
				
				if (containerName.equalsIgnoreCase("WithPhoto")) {
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$NormalAll$Analytics$AnalyticsDataAll$Side" + WhichSide +
						"$BatsmanTimeLine$ImageAll$img_Player*TEXTURE*IMAGE SET " +  batter.getPlayer().getPhoto() + "\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$NormalAll$Analytics$AnalyticsDataAll$Side" + WhichSide + 
						"$BatsmanTimeLine$ImageAll$img_PlayerShadow*TEXTURE*IMAGE SET " + batter.getPlayer().getPhoto() + "\0", print_writers);
					
				}
				
				this_data_str = new ArrayList<String>();
				this_data_str.add(String.join(",", 
					    new ArrayList<>(Arrays.asList(IndexController.MatchStats.getPlayerStats().stream().filter(ply->ply.getStatsType().equalsIgnoreCase(CricketUtil.BAT)
								&& FirstPlayerId == ply.getId()).findAny().orElse(null).getThisOverTxt().split(","))).stream()
				        .map(s -> s.replace("WIDE", "WD").replace("NO_BALL", "NB").replace("LEG_BYE", "LB").replace("BYE", "B")
				                   .replace("PENALTY", "PN").replace("LOG_WICKET", "W").replace("WICKET", "W").replace("BOUNDARY", ""))
				        .collect(Collectors.collectingAndThen(Collectors.toList(), list -> {Collections.reverse(list); return list;}))
				        .toArray(new String[0])));
				
				if(this_data_str.get(this_data_str.size()-1) == null) {
					return "populateVizInfobarRightBottom: This over data returned invalid";
				}
				for(int i=1;i<=17;i++) {
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$NormalAll$Analytics$AnalyticsDataAll$Side" + WhichSide 
						+ "$BatsmanTimeLine$TimelineData$Stage7Batsman6TimeLineGrps$Batsman1TimeLineGrp"+ i +"*ACTIVE SET 0\0", print_writers);
				}
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$NormalAll$Analytics$AnalyticsDataAll$Side" + WhichSide + 
						"$BatsmanTimeLine$TimelineData$ScoreGrp$Runs*GEOM*TEXT SET " + batter.getRuns()+
						(batter.getStatus().equalsIgnoreCase(CricketUtil.NOT_OUT)? "*" : "") + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$NormalAll$Analytics$AnalyticsDataAll$Side" + WhichSide + 
						"$BatsmanTimeLine$TimelineData$ScoreGrp$Balls*GEOM*TEXT SET " + batter.getBalls() + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$NormalAll$Analytics$AnalyticsDataAll$Side" + WhichSide + 
						"$BatsmanTimeLine$NameGrp$HeadText1$Shadow-Medium-Font*GEOM*TEXT SET " + batter.getPlayer().getFirstname() + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$NormalAll$Analytics$AnalyticsDataAll$Side" + WhichSide + 
						"$BatsmanTimeLine$NameGrp$HeadText2$Shadow-Black-Font*GEOM*TEXT SET " + batter.getPlayer().getSurname() + "\0", print_writers);
				
				if(this_data_str.get(this_data_str.size()-1).split(",").length < 17) {
					for(int iBall = 0; iBall < this_data_str.get(this_data_str.size()-1).split(",").length; iBall++) {
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$NormalAll$Analytics$AnalyticsDataAll$Side" + WhichSide 
							+ "$BatsmanTimeLine$TimelineData$Stage7Batsman6TimeLineGrps$Batsman1TimeLineGrp"+ (iBall+1) +"*ACTIVE SET 1\0", print_writers);
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$NormalAll$Analytics$AnalyticsDataAll$Side" + WhichSide 
							+ "$BatsmanTimeLine$TimelineData$Stage7Batsman6TimeLineGrps$Batsman1TimeLineGrp"+ (iBall+1) +"*GEOM*TEXT SET " +
							this_data_str.get(this_data_str.size()-1).split(",")[iBall] + "\0", print_writers);
					}
				}
				break;
			case "ALLSESSION_SUMMARY":
				if (matchAllData != null && matchAllData.getMatch() != null && matchAllData.getMatch().getDaysSessions() != null &&
			    !matchAllData.getMatch().getDaysSessions().isEmpty()) {
					 int day =matchAllData.getMatch().getDaysSessions().get(matchAllData.getMatch().getDaysSessions().size() - 1).getDayNumber();
					 DaySession last = matchAllData.getMatch().getDaysSessions().stream().filter(ds->ds.getDayNumber()==day && ds.getSessionNumber()==FirstPlayerId).findAny().orElse(null);
					 int balls =(last.getTotalBalls()>0?last.getTotalBalls():0);
					 int Runs =(last.getTotalRuns()>0?last.getTotalRuns():0);
					 int Wicket =(last.getTotalWickets()>0?last.getTotalWickets():0);
					 
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$NormalAll$Analytics$AnalyticsDataAll$Side" + WhichSide + 
							"$select_GraphicsType*FUNCTION*Omo*vis_con SET 10\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$NormalAll$Analytics$AnalyticsDataAll$Side" + WhichSide + 
							"$SessionSummary$HeaderGrp$Header1$select_Language*FUNCTION*Omo*vis_con SET 1\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$NormalAll$Analytics$AnalyticsDataAll$Side" + WhichSide + 
							"$SessionSummary$HeaderGrp$Header1$English$txt_Header1*GEOM*TEXT SET " + "THIS" + "\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$NormalAll$Analytics$AnalyticsDataAll$Side" + WhichSide + 
							"$SessionSummary$HeaderGrp$Header2$select_Language*FUNCTION*Omo*vis_con SET 1\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$NormalAll$Analytics$AnalyticsDataAll$Side" + WhichSide + 
							"$SessionSummary$HeaderGrp$Header2$English$txt_Header2*GEOM*TEXT SET " + "SESSION" + "\0", print_writers);
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$NormalAll$Analytics$AnalyticsDataAll$Side" + WhichSide + 
							"$SessionSummary$DataGrps$Grp1$Stat*GEOM*TEXT SET " + "SCORE" + "\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$NormalAll$Analytics$AnalyticsDataAll$Side" + WhichSide + 
							"$SessionSummary$DataGrps$Grp1$txt_Value*GEOM*TEXT SET " + Runs + "-" + Wicket + "\0", print_writers);
		
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$NormalAll$Analytics$AnalyticsDataAll$Side" + WhichSide + 
							"$SessionSummary$DataGrps$Grp2$Stat*GEOM*TEXT SET " + "OVER"+ "\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$NormalAll$Analytics$AnalyticsDataAll$Side" + WhichSide + 
							"$SessionSummary$DataGrps$Grp2$txt_Value*GEOM*TEXT SET " +CricketFunctions.OverBalls(0, balls) + " OVER" + CricketFunctions.Plural(balls/6).toUpperCase()  + "\0", print_writers);
		
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$NormalAll$Analytics$AnalyticsDataAll$Side" + WhichSide + 
							"$SessionSummary$DataGrps$Grp3$Stat*GEOM*TEXT SET " + "OVER RATE" + "\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$NormalAll$Analytics$AnalyticsDataAll$Side" + WhichSide + 
							"$SessionSummary$DataGrps$Grp3$txt_Value*GEOM*TEXT SET " + CricketFunctions.BetterOverRate(0, balls, last.getTotalSeconds(), "", false)+ "\0", print_writers);
				}
				break;
			case "COMMENTATORS":
				//Commentators
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$NormalAll$Analytics$AnalyticsDataAll$Side" + WhichSide + 
						"$select_GraphicsType*FUNCTION*Omo*vis_con SET 1\0", print_writers);

				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$NormalAll$Analytics$AnalyticsDataAll$Side" + WhichSide + 
						"$Commentators$Header$select_icon*FUNCTION*Omo*vis_con SET 1\0", print_writers);

				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$NormalAll$Analytics$AnalyticsDataAll$Side" + WhichSide + 
						"$Commentators$Header$select_Language*FUNCTION*Omo*vis_con SET 1\0", print_writers);
				
				if(Integer.valueOf(Comms_Name.split(",")[2]) > 0 && Integer.valueOf(Comms_Name.split(",")[1]) > 0 
						&& Integer.valueOf(Comms_Name.split(",")[0]) > 0) {
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$NormalAll$Analytics$AnalyticsDataAll$Side" + WhichSide + 
							"$Commentators$Header$English$txt_Header*GEOM*TEXT SET " + "COMMENTATORS" + "\0", print_writers);
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$NormalAll$Analytics$AnalyticsDataAll$Side" + WhichSide + 
							"$Commentators$select_Commentators*FUNCTION*Omo*vis_con SET 3\0", print_writers);
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$NormalAll$Analytics$AnalyticsDataAll$Side" + WhichSide + 
							"$Commentators$select_Commentators$Grp1$NameGrp$select_Language$Text1*Omo*vis_con SET 1\0", print_writers);
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$NormalAll$Analytics$AnalyticsDataAll$Side" + WhichSide + 
							"$Commentators$select_Commentators$Grp1$NameGrp$select_Language$Text2*Omo*vis_con SET 1\0", print_writers);
					for(int i=0;i<3;i++) {
//						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$NormalAll$Analytics$AnalyticsDataAll$Side" + WhichSide + "$Commentators$select_Commentators$Grp"
//								+(i+1) + "$ImageGroup$img_Commentators*TEXTURE*IMAGE SET " +  Commentators.get(Integer.valueOf(Comms_Name.split(",")[i])-1).getFirstName() + "\0", print_writers);
//						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$NormalAll$Analytics$AnalyticsDataAll$Side" + WhichSide + "$Commentators$select_Commentators$Grp"
//								+(i+1) + "$ImageGroup$img_CommentatorsShadow*TEXTURE*IMAGE SET " +  Commentators.get(Integer.valueOf(Comms_Name.split(",")[i])-1).getFirstName() + "\0", print_writers);
						
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$NormalAll$Analytics$AnalyticsDataAll$Side" + WhichSide + "$Commentators$select_Commentators$Grp"
								+(i+1) +"$ImageGroup*ACTIVE SET 0\0", print_writers);
						
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$NormalAll$Analytics$AnalyticsDataAll$Side" + WhichSide + 
								"$Commentators$select_Commentators$Grp" + (i+1) + "$NameGrp$Text1$select_Language*FUNCTION*Omo*vis_con SET 1\0", print_writers);
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$NormalAll$Analytics$AnalyticsDataAll$Side" + WhichSide + 
								"$Commentators$select_Commentators$Grp" + (i+1) + "$NameGrp$Text2$select_Language*FUNCTION*Omo*vis_con SET 1\0", print_writers);
						
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$NormalAll$Analytics$AnalyticsDataAll$Side" + WhichSide + 
								"$Commentators$select_Commentators$Grp" + (i+1) + "$NameGrp$select_Language$Text1$English$txt_FirstName*GEOM*TEXT SET " +
								Commentators.get(Integer.valueOf(Comms_Name.split(",")[i])-1).getFirstName() + "\0", print_writers);
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$NormalAll$Analytics$AnalyticsDataAll$Side" + WhichSide + 
								"$Commentators$select_Commentators$Grp" + (i+1) + "$NameGrp$select_Language$Text2$English$txt_LastName*GEOM*TEXT SET " + 
								Commentators.get(Integer.valueOf(Comms_Name.split(",")[i])-1).getLastName()+ "\0", print_writers);
					}
					
				}else if(Integer.valueOf(Comms_Name.split(",")[2]) == 0 && Integer.valueOf(Comms_Name.split(",")[1]) > 0 
						&& Integer.valueOf(Comms_Name.split(",")[0]) > 0) {
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$NormalAll$Analytics$AnalyticsDataAll$Side" + WhichSide + 
							"$Commentators$Header$English$txt_Header*GEOM*TEXT SET " + "COMMENTATORS" + "\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$NormalAll$Analytics$AnalyticsDataAll$Side" + WhichSide + 
							"$Commentators$select_Commentators*FUNCTION*Omo*vis_con SET 2\0", print_writers);
					for(int i=0;i<2;i++) {
//						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$NormalAll$Analytics$AnalyticsDataAll$Side" + WhichSide + "$Commentators$select_Commentators$Grp"
//								+(i+1) + "$ImageGroup$img_Commentators*TEXTURE*IMAGE SET " +  Commentators.get(Integer.valueOf(Comms_Name.split(",")[i])-1).getFirstName() + "\0", print_writers);
//						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$NormalAll$Analytics$AnalyticsDataAll$Side" + WhichSide + "$Commentators$select_Commentators$Grp"
//								+(i+1) + "$ImageGroup$img_CommentatorsShadow*TEXTURE*IMAGE SET " +  Commentators.get(Integer.valueOf(Comms_Name.split(",")[i])-1).getFirstName() + "\0", print_writers);
						
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$NormalAll$Analytics$AnalyticsDataAll$Side" + WhichSide + "$Commentators$select_Commentators$Grp"
								+(i+1) +"$ImageGroup*ACTIVE SET 0\0", print_writers);
						
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$NormalAll$Analytics$AnalyticsDataAll$Side" + WhichSide + 
								"$Commentators$select_Commentators$Grp" + (i+1) + "$NameGrp$Text1$select_Language*FUNCTION*Omo*vis_con SET 1\0", print_writers);
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$NormalAll$Analytics$AnalyticsDataAll$Side" + WhichSide + 
								"$Commentators$select_Commentators$Grp" + (i+1) + "$NameGrp$Text2$select_Language*FUNCTION*Omo*vis_con SET 1\0", print_writers);
						
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$NormalAll$Analytics$AnalyticsDataAll$Side" + WhichSide + 
								"$Commentators$select_Commentators$Grp" + (i+1) + "$NameGrp$Text1$English$txt_FirstName*GEOM*TEXT SET " +
								Commentators.get(Integer.valueOf(Comms_Name.split(",")[i])-1).getFirstName() + "\0", print_writers);
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$NormalAll$Analytics$AnalyticsDataAll$Side" + WhichSide + 
								"$Commentators$select_Commentators$Grp" + (i+1) + "$NameGrp$Text2$English$txt_LastName*GEOM*TEXT SET " + 
								Commentators.get(Integer.valueOf(Comms_Name.split(",")[i])-1).getLastName()+ "\0", print_writers);
					}
				
				}else if(Integer.valueOf(Comms_Name.split(",")[2]) == 0 && Integer.valueOf(Comms_Name.split(",")[1]) == 0 
						&& Integer.valueOf(Comms_Name.split(",")[0]) > 0) {
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$NormalAll$Analytics$AnalyticsDataAll$Side" + WhichSide + 
							"$Commentators$Header$English$txt_Header*GEOM*TEXT SET " + "COMMENTATOR" + "\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$NormalAll$Analytics$AnalyticsDataAll$Side" + WhichSide + 
							"$Commentators$select_Commentators*FUNCTION*Omo*vis_con SET 1\0", print_writers);
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$NormalAll$Analytics$AnalyticsDataAll$Side" + WhichSide + 
							"$Commentators$select_Commentators*FUNCTION*Omo*vis_con SET 1\0", print_writers);
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$NormalAll$Analytics$AnalyticsDataAll$Side" + WhichSide + "$Commentators$select_Commentators$Grp"
							+"1$ImageGroup*ACTIVE SET 0\0", print_writers);
//					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$NormalAll$Analytics$AnalyticsDataAll$Side" + WhichSide + "$Commentators$select_Commentators$Grp"
//							+"1$ImageGroup$img_Commentators*TEXTURE*IMAGE SET " +  Commentators.get(Integer.valueOf(Comms_Name.split(",")[0])-1).getFirstName() + "\0", print_writers);
//					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$NormalAll$Analytics$AnalyticsDataAll$Side" + WhichSide + "$Commentators$select_Commentators$Grp"
//							+ "1$ImageGroup$img_CommentatorsShadow*TEXTURE*IMAGE SET " +  Commentators.get(Integer.valueOf(Comms_Name.split(",")[0])-1).getFirstName() + "\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$NormalAll$Analytics$AnalyticsDataAll$Side" + WhichSide + 
							"$Commentators$select_Commentators$Grp1$NameGrp$Text1$select_Language*FUNCTION*Omo*vis_con SET 1\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$NormalAll$Analytics$AnalyticsDataAll$Side" + WhichSide + 
							"$Commentators$select_Commentators$Grp1$NameGrp$Text2$select_Language*FUNCTION*Omo*vis_con SET 1\0", print_writers);
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$NormalAll$Analytics$AnalyticsDataAll$Side" + WhichSide + 
							"$Commentators$select_Commentators$Grp1$NameGrp$Text1$English$txt_FirstName*GEOM*TEXT SET " +Commentators.get(Integer.valueOf(Comms_Name.split(",")[0])-1).getFirstName() + "\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$NormalAll$Analytics$AnalyticsDataAll$Side" + WhichSide + 
							"$Commentators$select_Commentators$Grp1$NameGrp$Text2$English$txt_LastName*GEOM*TEXT SET " + Commentators.get(Integer.valueOf(Comms_Name.split(",")[0])-1).getLastName()+ "\0", print_writers);
				
				}else {
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$NormalAll$Analytics$AnalyticsDataAll$Side" + WhichSide + 
							"$Commentators$select_Commentators*FUNCTION*Omo*vis_con SET 0\0", print_writers);
				}
				break;
			case "PROJECTED_SCORE":
				inning = matchAllData.getMatch().getInning().stream().filter(inn -> inn.getInningNumber() == 1 &&
				inn.getIsCurrentInning().equalsIgnoreCase(CricketUtil.YES)).findAny().orElse(null);
		
				if(inning == null) {
					return "populateVizInfobarRightSection: 1st Inning returned is NULL";
				}
				this_data_str = CricketFunctions.projectedScore(matchAllData);
				
				if(this_data_str.size() <= 0) {
					return "populateVizInfobarRightSection: Projected score invalid";
				}
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$NormalAll$Analytics$AnalyticsDataAll$Side" + WhichSide + 
						"$select_GraphicsType*FUNCTION*Omo*vis_con SET 4\0", print_writers);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$NormalAll$Analytics$AnalyticsDataAll$Side" + WhichSide + 
						"$ProjectedScore$HeaderGrp$Header1$select_Language*FUNCTION*Omo*vis_con SET 1\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$NormalAll$Analytics$AnalyticsDataAll$Side" + WhichSide + 
						"$ProjectedScore$HeaderGrp$Header1$English$txt_Header1*GEOM*TEXT SET " + "PROJECTED" + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$NormalAll$Analytics$AnalyticsDataAll$Side" + WhichSide + 
						"$ProjectedScore$HeaderGrp$Header2$select_Language*FUNCTION*Omo*vis_con SET 1\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$NormalAll$Analytics$AnalyticsDataAll$Side" + WhichSide + 
						"$ProjectedScore$HeaderGrp$Header2$English$txt_Header2*GEOM*TEXT SET " + "SCORE" + "\0", print_writers);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$NormalAll$Analytics$AnalyticsDataAll$Side" + WhichSide + 
						"$ProjectedScore$DataGrps$Grp1$Stat*GEOM*TEXT SET " + "@" +this_data_str.get(0) +" (CRR)"+ "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$NormalAll$Analytics$AnalyticsDataAll$Side" + WhichSide + 
						"$ProjectedScore$DataGrps$Grp1$txt_Value*GEOM*TEXT SET " + this_data_str.get(1)+ "\0", print_writers);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$NormalAll$Analytics$AnalyticsDataAll$Side" + WhichSide + 
						"$ProjectedScore$DataGrps$Grp2$Stat*GEOM*TEXT SET " + "@" + this_data_str.get(1) +" RPO"  + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$NormalAll$Analytics$AnalyticsDataAll$Side" + WhichSide + 
						"$ProjectedScore$DataGrps$Grp2$txt_Value*GEOM*TEXT SET " +  this_data_str.get(2) + "\0", print_writers);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$NormalAll$Analytics$AnalyticsDataAll$Side" + WhichSide + 
						"$ProjectedScore$DataGrps$Grp3$Stat*GEOM*TEXT SET " + "@" + this_data_str.get(3) +" RPO" + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$NormalAll$Analytics$AnalyticsDataAll$Side" + WhichSide + 
						"$ProjectedScore$DataGrps$Grp3$txt_Value*GEOM*TEXT SET " + this_data_str.get(4) + "\0", print_writers);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$NormalAll$Analytics$AnalyticsDataAll$Side" + WhichSide + 
						"$ProjectedScore$DataGrps$Grp4$Stat*GEOM*TEXT SET " + "@" + this_data_str.get(5) +" RPO"  + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$NormalAll$Analytics$AnalyticsDataAll$Side" + WhichSide + 
						"$ProjectedScore$DataGrps$Grp4$txt_Value*GEOM*TEXT SET " + this_data_str.get(6)+ "\0", print_writers);
				break;
			}
			break;
		}
		return Constants.OK;
	}
	public String populateVizInfobarSection5(List<PrintWriter> print_writers, MatchAllData matchAllData,int WhichSide,int WhichSubSide) {
		inning = matchAllData.getMatch().getInning().stream().filter(inn -> inn.getIsCurrentInning().equalsIgnoreCase(CricketUtil.YES)).findAny().orElse(null);

		if(inning == null) {
			return "populateVizInfobarSection5: Inning return is NULL";
		}
		switch(config.getBroadcaster()) {
		case Constants.BCCI:
			if(infobar.getSection5() != null && !infobar.getSection5().isEmpty()) {
				switch(infobar.getSection5().toUpperCase()) {
					case "LAST_WICKET":
					   CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$NormalAll$Section5$Side" 
							+ WhichSide + "$select_GraphicsType*FUNCTION*Omo*vis_con SET 2\0", print_writers);
					  
					   CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$NormalAll$Section5$Side" + WhichSide + 
							"$LastWicket$select_Image*FUNCTION*Omo*vis_con SET " + (containerName.equalsIgnoreCase("WithoutPhoto")? 0:1) + "\0", print_writers);
					   CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$NormalAll$Section5$Side" + WhichSide + 
							"$LastWicket$MoveForImage*FUNCTION*Omo*vis_con SET " + (containerName.equalsIgnoreCase("WithoutPhoto")? 0:1) + "\0", print_writers);
//					   CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$NormalAll$Section5$Side" + WhichSide + 
//							"$LastWicket$Data$Header$select_Language*FUNCTION*Omo*vis_con SET 1\0", print_writers);
//					   CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$NormalAll$Section5$Side" + WhichSide + 
//							"$LastWicket$Data$Header$English$txt_Header*GEOM*TEXT SET LAST WICKET\0", print_writers);
						
					   CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$NormalAll$Section5$Side" + WhichSide + 
								"$LastWicket$Data$Header$Header$select_Language*FUNCTION*Omo*vis_con SET ", config, Constants.BCCI, print_writers, foreignLanguageOmo);
						foreignLanguageData = CricketFunctions.AssembleMultiLanguageData(CricketUtil.DICTIONARY, "", multilanguagedata, "LAST WICKET", "", 
								null, 0, foreignLanguageDataList);
						CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$NormalAll$Section5$Side" + WhichSide + 
								"$LastWicket$Data$Header$Header$English$txt_Header*GEOM*TEXT SET ", config, Constants.BCCI, print_writers, foreignLanguageData);
						
					   inning = matchAllData.getMatch().getInning().stream().filter(inn ->inn.getIsCurrentInning().equalsIgnoreCase(CricketUtil.YES)).findAny().orElse(null);
					
						
						if(inning == null) {
							return "populateVizInfobarSection5: Inning returned is NULL";
						}
						
						if(inning.getFallsOfWickets() == null && inning.getFallsOfWickets().isEmpty()) {
							return "populateVizInfobarSection5: FoW returned is EMPTY";
						}
						battingCardList.add(inning.getBattingCard().stream().filter(bc -> bc.getPlayerId() == 
								inning.getFallsOfWickets().get(inning.getFallsOfWickets().size() - 1).getFowPlayerID()).findAny().orElse(null));

						if(battingCardList.get(battingCardList.size()-1) == null) {
							return "populateVizInfobarLeftBottom: Last wicket returned is invalid";
						}
						String how_out_txt = "";
						if(battingCardList.get(battingCardList.size()-1).getHowOut().equalsIgnoreCase(CricketUtil.RUN_OUT)) {
							if(battingCardList.get(battingCardList.size()-1).getWasHowOutFielderSubstitute() != null && 
									battingCardList.get(battingCardList.size()-1).getWasHowOutFielderSubstitute().equalsIgnoreCase(CricketUtil.YES)) {
								how_out_txt = "run out " + "sub (" + battingCardList.get(battingCardList.size()-1).getHowOutFielder().getTicker_name() + ")";
							} else {
								how_out_txt = "run out (" + battingCardList.get(battingCardList.size()-1).getHowOutFielder().getTicker_name() + ")";
							}
						}
						else if(battingCardList.get(battingCardList.size()-1).getHowOut().equalsIgnoreCase(CricketUtil.CAUGHT)) {
							if(battingCardList.get(battingCardList.size()-1).getWasHowOutFielderSubstitute() != null && 
									battingCardList.get(battingCardList.size()-1).getWasHowOutFielderSubstitute().equalsIgnoreCase(CricketUtil.YES)) {
								how_out_txt = "c" +  " sub (" + battingCardList.get(battingCardList.size()-1).getHowOutFielder().getTicker_name() + ")  b " + 
										battingCardList.get(battingCardList.size()-1).getHowOutBowler().getTicker_name();
							} else {
								how_out_txt = "c " + battingCardList.get(battingCardList.size()-1).getHowOutFielder().getTicker_name() + "  b " + 
										battingCardList.get(battingCardList.size()-1).getHowOutBowler().getTicker_name();
							}
						}else {
							if(!battingCardList.get(battingCardList.size()-1).getHowOutPartOne().isEmpty()) {
								how_out_txt = battingCardList.get(battingCardList.size()-1).getHowOutPartOne();
							}
							
							if(!battingCardList.get(battingCardList.size()-1).getHowOutPartTwo().isEmpty()) {
								if(!how_out_txt.trim().isEmpty()) {
									how_out_txt = how_out_txt + "  " + battingCardList.get(battingCardList.size()-1).getHowOutPartTwo();
								}else {
									how_out_txt = battingCardList.get(battingCardList.size()-1).getHowOutPartTwo();
								}
							}
						}
						if (containerName.equalsIgnoreCase("WithPhoto")) {
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$NormalAll$Section5$Side" + WhichSide +
								"$LastWicket$ImageAll$img_Player*TEXTURE*IMAGE SET " +  battingCardList.get(battingCardList.size()-1).getPlayer().getPhoto() + "\0", print_writers);
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$NormalAll$Section5$Side" + WhichSide + 
								"$LastWicket$ImageAll$img_PlayerShadow*TEXTURE*IMAGE SET " + battingCardList.get(battingCardList.size()-1).getPlayer().getPhoto() + "\0", print_writers);
							
						}
						
						CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$NormalAll$Section5$Side" + WhichSide + 
								"$LastWicket$Data$NameGrp$FirstName$select_Language*FUNCTION*Omo*vis_con SET ", config, Constants.BCCI, print_writers, foreignLanguageOmo);
						CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$NormalAll$Section5$Side" + WhichSide + 
								"$LastWicket$Data$NameGrp$LastName$select_Language*FUNCTION*Omo*vis_con SET ", config, Constants.BCCI, print_writers, foreignLanguageOmo);
						
						foreignLanguageData = CricketFunctions.AssembleMultiLanguageData(CricketUtil.PLAYER, CricketUtil.TICKERNAME, multilanguagedata, battingCardList.get(battingCardList.size()-1).getPlayer().getFirstname(), "", 
								null, 0, foreignLanguageDataList);
						CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$NormalAll$Section5$Side" + WhichSide + 
								"$LastWicket$Data$NameGrp$FirstName$English$txt_FirstName*GEOM*TEXT SET ", config, Constants.BCCI, print_writers, foreignLanguageData);
						
						foreignLanguageData = CricketFunctions.AssembleMultiLanguageData(CricketUtil.PLAYER, CricketUtil.TICKERNAME, multilanguagedata, battingCardList.get(battingCardList.size()-1).getPlayer().getSurname(), "", 
								null, 0, foreignLanguageDataList);
						CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$NormalAll$Section5$Side" + WhichSide + 
								"$LastWicket$Data$NameGrp$LastName$English$txt_LastName*GEOM*TEXT SET ", config, Constants.BCCI, print_writers, foreignLanguageData);
						
//						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$NormalAll$Section5$Side" + WhichSide + 
//								"$LastWicket$Data$NameGrp$FirstName$English$txt_FirstName*GEOM*TEXT SET " + battingCardList.get(battingCardList.size()-1).getPlayer().getFirstname() + "\0", print_writers);
//						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$NormalAll$Section5$Side" + WhichSide + 
//								"$LastWicket$Data$NameGrp$LastName$English$txt_LastName*GEOM*TEXT SET " + battingCardList.get(battingCardList.size()-1).getPlayer().getSurname() + "\0", print_writers);
						
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$NormalAll$Section5$Side" + WhichSide + 
								"$LastWicket$Data$DataGrps$ScoreGrp$txt_Runs*GEOM*TEXT SET " + battingCardList.get(battingCardList.size()-1).getRuns() + "\0", print_writers);
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$NormalAll$Section5$Side" + WhichSide + 
								"$LastWicket$Data$DataGrps$ScoreGrp$txt_Balls*GEOM*TEXT SET " + battingCardList.get(battingCardList.size()-1).getBalls() + "\0", print_writers);
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$NormalAll$Section5$Side" + WhichSide + 
								"$LastWicket$Data$DataGrps$Stat$select_Language*FUNCTION*Omo*vis_con SET 1\0", print_writers);
						
						how_out_txts = null;
//						if(battingCardList.get(battingCardList.size()-1).getHowOut() != null && !battingCardList.get(battingCardList.size()-1).getStatus().equalsIgnoreCase(CricketUtil.NOT_OUT)) {
//							howOut = CricketFunctions.generateBattingCardForeignLanguage(battingCardList.get(battingCardList.size()-1).getHowOut(), battingCardList.get(battingCardList.size()-1).getHowOutPartOne(), battingCardList.get(battingCardList.size()-1).getHowOutPartTwo(), 
//									battingCardList.get(battingCardList.size()-1).getHowOutText(), multilanguagedata);
//							foreignLanguageData.add(new ForeignLanguageData(howOut.getEnglishText(), howOut.getHindiText(), "", ""));
//							//how_out_txt = CricketFunctions.GetVariousLanguageTextToEachViz(config, Constants.BCCI, print_writers, foreignLanguageData);
//						}
						
						if(how_out_txts != null && how_out_txts.split("|").length >= 4) {
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrames$AllGraphics$Side" + WhichSide + "$Normal_BattingCard$FullBatRow" + 
									rowId + containerName + "$How_Out_1$txt_OutType*GEOM*TEXT SET " + how_out_txts.split("\\|")[0] + "\0", print_writers);
							
							CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*BACK_LAYER*TREE*$gfx_FullFrames$AllGraphics$Side" + WhichSide + "$Normal_BattingCard$FullBatRow" + 
									rowId + containerName + "$How_Out_1$FielderName$select_Language*FUNCTION*Omo*vis_con SET ", config, Constants.BCCI, print_writers, foreignLanguageOmo);
							foreignLanguageData = CricketFunctions.AssembleMultiLanguageData("", "", multilanguagedata, how_out_txts.split("\\|")[1], "", null, 
									0, foreignLanguageDataList);
							CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*BACK_LAYER*TREE*$gfx_FullFrames$AllGraphics$Side" + WhichSide + "$Normal_BattingCard$FullBatRow" + 
									rowId + containerName + "$How_Out_1$FielderName$English$txt_FielderName*GEOM*TEXT SET ", config, Constants.BCCI, print_writers, foreignLanguageData);
							
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrames$AllGraphics$Side" + WhichSide + "$Normal_BattingCard$FullBatRow" + 
									rowId + containerName + "$How_Out_2$BowlerName$txt_Bold*GEOM*TEXT SET " + how_out_txts.split("\\|")[2] + "\0", print_writers);
							
							CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*BACK_LAYER*TREE*$gfx_FullFrames$AllGraphics$Side" + WhichSide + "$Normal_BattingCard$FullBatRow" + 
									rowId + containerName + "$How_Out_2$BowlerName$BowlerName$select_Language*FUNCTION*Omo*vis_con SET ", config, Constants.BCCI, print_writers, foreignLanguageOmo);
							foreignLanguageData = CricketFunctions.AssembleMultiLanguageData("", "", multilanguagedata, how_out_txts.split("\\|")[3], "", null, 
									0, foreignLanguageDataList);
							CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*BACK_LAYER*TREE*$gfx_FullFrames$AllGraphics$Side" + WhichSide + "$Normal_BattingCard$FullBatRow" + 
									rowId + containerName + "$How_Out_2$BowlerName$BowlerName$English$txt_BowlerName*GEOM*TEXT SET ", config, Constants.BCCI, print_writers, foreignLanguageData);
							
						}
						
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$NormalAll$Section5$Side" + WhichSide + 
								"$LastWicket$Data$DataGrps$Stat$English$txt_HowOut*GEOM*TEXT SET " + how_out_txt + "\0", print_writers);
						
						
					break;
				}
			}
			break;
		}
		return Constants.OK;
	}
	
	public void populateBowlerName(List<PrintWriter> print_writers, MatchAllData matchAllData,int WhichSide, int WhichSubSide) throws InterruptedException {
		switch(config.getBroadcaster()) {
		case Constants.TRI_SERIES:  case Constants.MT20:
			if(infobar.getSection4() != null && !infobar.getSection4().isEmpty()) {
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$RightSection$BowlerGrp$Side" + WhichSide + "$Name$Side" 
						+ WhichSubSide + "$select_NameType*FUNCTION*Omo*vis_con SET 0\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$RightSection$BowlerGrp$Side" + WhichSide + "$Name$Side" 
						+ WhichSubSide + "$TickerName$txt_Name*GEOM*TEXT SET " + bowlingCard.getPlayer().getTicker_name() + "\0", print_writers);
				//bowlerTickerName = true;
			}else {
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$RightSection$BowlerGrp$Side" + WhichSide + "$Name$Side" 
						+ WhichSubSide + "$select_NameType*FUNCTION*Omo*vis_con SET 1\0", print_writers);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$RightSection$BowlerGrp$Side" + WhichSide + "$Name$Side" 
						+ WhichSubSide + "$FullName$txt_FirstName*GEOM*TEXT SET \0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$RightSection$BowlerGrp$Side" + WhichSide + "$Name$Side" 
						+ WhichSubSide + "$FullName$txt_LastName*GEOM*TEXT SET " + (bowlingCard.getPlayer().getTicker_name() != null ? 
								bowlingCard.getPlayer().getTicker_name():"") + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$RightSection$Section3$Side" + WhichSide 
						+ "$ThisOver$This_OverHead$select_ThisOverHead*FUNCTION*Omo*vis_con SET 1\0", print_writers);
				//bowlerTickerName = false;
			}
			//populateSection3(print_writers, matchAllData, WhichSubSide);
			break;
		case Constants.TG20:
			if(infobar.getSection4() != null && !infobar.getSection4().isEmpty()) {
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$RightSection$BowlerGrp$Side" + WhichSide + "$Name$Side" 
						+ WhichSubSide + "$select_NameType*FUNCTION*Omo*vis_con SET 0\0", print_writers);
				
				CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$RightSection$BowlerGrp$Side" + WhichSide + "$Name$Side" + WhichSubSide 
						+ "$TickerName$select_Language*FUNCTION*Omo*vis_con SET ", config, Constants.TG20, print_writers, foreignLanguageOmo);
				foreignLanguageData = CricketFunctions.AssembleMultiLanguageData(CricketUtil.PLAYER, CricketUtil.TICKERNAME, multilanguagedata, 
						bowlingCard.getPlayer().getTicker_name(), "", null, 0, foreignLanguageDataList);
				CricketFunctions.DoadWriteVariousLanguageTextToEachViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$RightSection$BowlerGrp$Side" + WhichSide + "$Name$Side" + WhichSubSide 
						+ "$TickerName$English$txt_Name*GEOM*TEXT SET ", config, Constants.TG20, print_writers, foreignLanguageData);
				
			}else {
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$RightSection$BowlerGrp$Side" + WhichSide + "$Name$Side" 
						+ WhichSubSide + "$select_NameType*FUNCTION*Omo*vis_con SET 1\0", print_writers);
				
				CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$RightSection$BowlerGrp$Side" + WhichSide + "$Name$Side" 
						+ WhichSubSide + "$FullName$select_Language*FUNCTION*Omo*vis_con SET ", config, Constants.TG20, print_writers, foreignLanguageOmo);
				
				//FirstName
				foreignLanguageData = CricketFunctions.AssembleMultiLanguageData(CricketUtil.PLAYER, CricketUtil.TICKERNAME, multilanguagedata, "", "", null, 0, foreignLanguageDataList);
				CricketFunctions.DoadWriteVariousLanguageTextToEachViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$RightSection$BowlerGrp$Side" + WhichSide + "$Name$Side" 
						+ WhichSubSide + "$FullName$English$txt_FirstName*GEOM*TEXT SET ", config, Constants.TG20, print_writers, foreignLanguageData);
				//LastNAme
				foreignLanguageData = CricketFunctions.AssembleMultiLanguageData(CricketUtil.PLAYER, CricketUtil.TICKERNAME, multilanguagedata, 
						bowlingCard.getPlayer().getTicker_name(), "", null, 0, foreignLanguageDataList);
				CricketFunctions.DoadWriteVariousLanguageTextToEachViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$RightSection$BowlerGrp$Side" + WhichSide + "$Name$Side" 
						+ WhichSubSide + "$FullName$English$txt_LastName*GEOM*TEXT SET ", config, Constants.TG20, print_writers, foreignLanguageData);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$RightSection$Section3$Side" + WhichSide 
						+ "$ThisOver$This_OverHead$select_ThisOverHead*FUNCTION*Omo*vis_con SET 1\0", print_writers);
			}
			break;
		}
	}
	public void populateBatterName(List<PrintWriter> print_writers, MatchAllData matchAllData,int WhichSide, int WhichSubSide) {
		switch(config.getBroadcaster()) {
		case Constants.TRI_SERIES:  case Constants.MT20:
			for(int i=1;i<=battingCardList.size();i++) {
				if(infobar.getSection2() != null && !infobar.getSection2().isEmpty()) {
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$LeftSection$BatsmanGrp_" + i + "$Side" + WhichSide 
							+ "$Name$Side" + WhichSubSide + "$select_NameType*FUNCTION*Omo*vis_con SET 0\0", print_writers);	
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$LeftSection$BatsmanGrp_" + i + "$Side" + WhichSide 
							+ "$Name$Side" + WhichSubSide + "$TickerName$txt_Name*GEOM*TEXT SET " + battingCardList.get(i-1).getPlayer().getTicker_name() + "\0", print_writers);
					
					BatterTickerName = true;
				}else {
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$LeftSection$BatsmanGrp_" + i + "$Side" + WhichSide 
							+ "$Name$Side" + WhichSubSide + "$select_NameType*FUNCTION*Omo*vis_con SET 1\0", print_writers);	
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$LeftSection$BatsmanGrp_" + i + "$Side" + WhichSide 
							+ "$Name$Side" + WhichSubSide + "$FullName$txt_FirstName*GEOM*TEXT SET \0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$LeftSection$BatsmanGrp_" + i + "$Side" + WhichSide 
							+ "$Name$Side" + WhichSubSide + "$FullName$txt_LastName*GEOM*TEXT SET " + (battingCardList.get(i-1).getPlayer().getTicker_name() != null ?
									battingCardList.get(i-1).getPlayer().getTicker_name():"") + "\0", print_writers);
					BatterTickerName = false;
				}
			}
			break;
		case Constants.TG20:
			for(int i=1;i<=battingCardList.size();i++) {
				if(infobar.getSection2() != null && !infobar.getSection2().isEmpty()) {
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$LeftSection$BatsmanGrp_" + i + "$Side" + WhichSide 
							+ "$Name$Side" + WhichSubSide + "$select_NameType*FUNCTION*Omo*vis_con SET 0\0", print_writers);
						
					CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$LeftSection$BatsmanGrp_" + i + "$Side" + WhichSide 
							+ "$Name$Side" + WhichSubSide + "$TickerName$select_Language*FUNCTION*Omo*vis_con SET ", config, Constants.TG20, print_writers, foreignLanguageOmo);
					foreignLanguageData = CricketFunctions.AssembleMultiLanguageData(CricketUtil.PLAYER, CricketUtil.TICKERNAME, multilanguagedata, 
							battingCardList.get(i-1).getPlayer().getTicker_name().trim(), "", null, 0, foreignLanguageDataList);
					CricketFunctions.DoadWriteVariousLanguageTextToEachViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$LeftSection$BatsmanGrp_" + i + "$Side" + WhichSide 
							+ "$Name$Side" + WhichSubSide + "$TickerName$English$txt_Name*GEOM*TEXT SET ", config, Constants.TG20, print_writers, foreignLanguageData);
					
					BatterTickerName = true;
				}else {
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$LeftSection$BatsmanGrp_" + i + "$Side" + WhichSide 
						+ "$Name$Side" + WhichSubSide + "$select_NameType*FUNCTION*Omo*vis_con SET 1\0", print_writers);
					
					CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$LeftSection$BatsmanGrp_" + i + "$Side" + WhichSide 
							+ "$Name$Side" + WhichSubSide + "$FullName$select_Language*FUNCTION*Omo*vis_con SET ", config, Constants.TG20, print_writers, foreignLanguageOmo);
					
					//FirstName
					foreignLanguageData = CricketFunctions.AssembleMultiLanguageData(CricketUtil.PLAYER, CricketUtil.TICKERNAME, multilanguagedata, "", "", null, 0, foreignLanguageDataList);
					CricketFunctions.DoadWriteVariousLanguageTextToEachViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$LeftSection$BatsmanGrp_" + i + "$Side" + WhichSide 
							+ "$Name$Side" + WhichSubSide + "$FullName$English$txt_FirstName*GEOM*TEXT SET ", config, Constants.TG20, print_writers, foreignLanguageData);
					//LastNAme
					foreignLanguageData = CricketFunctions.AssembleMultiLanguageData(CricketUtil.PLAYER, CricketUtil.TICKERNAME, multilanguagedata, 
							battingCardList.get(i-1).getPlayer().getTicker_name().trim(), "", null, 0, foreignLanguageDataList);
					CricketFunctions.DoadWriteVariousLanguageTextToEachViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$LeftSection$BatsmanGrp_" + i + "$Side" + WhichSide 
							+ "$Name$Side" + WhichSubSide + "$FullName$English$txt_LastName*GEOM*TEXT SET ", config, Constants.TG20, print_writers, foreignLanguageData);
					BatterTickerName = false;
				}
			}
			break;
		}
	}
	public void setThisOverAndLastOverData(List<PrintWriter> print_writers, MatchAllData matchAllData,int WhichSide) {
		switch(config.getBroadcaster()) {
		case Constants.AFG_SL_SERIES:
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$BowlingGrp$Section3$Side" + WhichSide 
					+ "$ThisOver$ThisOverDataAll$select_DataType*FUNCTION*Omo*vis_con SET 1\0", print_writers);
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$BowlingGrp$Section3$Side" + WhichSide 
					+ "$ThisOver$This_OverHead$select_ThisOverHead*FUNCTION*Omo*vis_con SET 1\0", print_writers);
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$BowlingGrp$Section3$Side" + WhichSide 
					+ "$select_DataType*FUNCTION*Omo*vis_con SET 0\0", print_writers);
			
			for (BowlingCard boc : inning.getBowlingCard()) {
			    String status = boc.getStatus().toUpperCase();
			    if (!(status.equals(CricketUtil.CURRENT + CricketUtil.BOWLER) || status.equals(CricketUtil.LAST + CricketUtil.BOWLER))) 
			        continue;

			    String this_over = CricketFunctions.processThisOverRunsCount(boc.getPlayerId(), matchAllData.getEventFile().getEvents());

			    String[] overParts = this_over.split("-");
			    int runs = Integer.parseInt(overParts[0]);
			    int wickets = Integer.parseInt(overParts[2]);

			    String overText = "";
			    if (runs > 0) {
			    	overText = runs + " RUN" + CricketFunctions.Plural(runs).toUpperCase();
			    }
			    if (wickets > 0) {
			        if (overText.contains("RUN")) {
			        	overText = overText + " & " + wickets + " WICKET" + CricketFunctions.Plural(wickets).toUpperCase();
			        }else {
			        	overText = wickets + " WICKET" + CricketFunctions.Plural(wickets).toUpperCase();
			        }
			    }
			    String header = status.equals(CricketUtil.CURRENT + CricketUtil.BOWLER) ? "THIS OVER" : "LAST OVER";

			    CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$BowlingGrp$Section3$Side" + WhichSide +
			        "$ThisOver$This_OverHead$txt_ThisOverHead*GEOM*TEXT SET " + header + " \0", print_writers);
			    CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$BowlingGrp$Section3$Side" + WhichSide +
			        "$ThisOver$ThisOverDataAll$TotalScore$txt_ThisOverFigure*GEOM*TEXT SET " + overText + "\0", print_writers);

			    if (!status.equals(CricketUtil.CURRENT + CricketUtil.BOWLER)) {
			        isThisOverLimitExceed = false;
			        break;
			    }
			}
			break;
		case Constants.ACC:
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section3$Side" + WhichSide + "$Select"
					+ "*FUNCTION*Omo*vis_con SET 10\0", print_writers);
			for (BowlingCard boc : inning.getBowlingCard()) {
			    String status = boc.getStatus().toUpperCase();
			    if (!(status.equals(CricketUtil.CURRENT + CricketUtil.BOWLER) || status.equals(CricketUtil.LAST + CricketUtil.BOWLER))) 
			        continue;

			    String this_over = CricketFunctions.processThisOverRunsCount(boc.getPlayerId(), matchAllData.getEventFile().getEvents());

			    String[] overParts = this_over.split("-");
			    int runs = Integer.parseInt(overParts[0]);
			    int wickets = Integer.parseInt(overParts[2]);

			    String overText = "";
			    if (runs > 0) {
			    	overText = runs + " RUN" + CricketFunctions.Plural(runs).toUpperCase();
			    }
			    if (wickets > 0) {
			        if (overText.contains("RUN")) {
			        	overText = overText + " & " + wickets + " WICKET" + CricketFunctions.Plural(wickets).toUpperCase();
			        }else {
			        	overText = wickets + " WICKET" + CricketFunctions.Plural(wickets).toUpperCase();
			        }
			    }
			    String header = status.equals(CricketUtil.CURRENT + CricketUtil.BOWLER) ? "THIS OVER" : "LAST OVER";
			    
			    CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section3$Side" + WhichSide +
				        "$FreeText$txt_Head*GEOM*TEXT SET " + header + " - " + overText + " \0", print_writers);

			    if (!status.equals(CricketUtil.CURRENT + CricketUtil.BOWLER)) {
			        isThisOverLimitExceed = false;
			        break;
			    }
			}
			break;
		case Constants.BAN_AFG_SERIES:
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section3$Side" + WhichSide + "$Select"
					+ "*FUNCTION*Omo*vis_con SET 10\0", print_writers);
			for (BowlingCard boc : inning.getBowlingCard()) {
			    String status = boc.getStatus().toUpperCase();
			    if (!(status.equals(CricketUtil.CURRENT + CricketUtil.BOWLER) || status.equals(CricketUtil.LAST + CricketUtil.BOWLER))) 
			        continue;

			    String this_over = CricketFunctions.processThisOverRunsCount(boc.getPlayerId(), matchAllData.getEventFile().getEvents());

			    String[] overParts = this_over.split("-");
			    int runs = Integer.parseInt(overParts[0]);
			    int wickets = Integer.parseInt(overParts[2]);

			    String overText = "";
			    if (runs > 0) {
			    	overText = runs + " RUN" + CricketFunctions.Plural(runs).toUpperCase();
			    }
			    if (wickets > 0) {
			        if (overText.contains("RUN")) {
			        	overText = overText + " & " + wickets + " WICKET" + CricketFunctions.Plural(wickets).toUpperCase();
			        }else {
			        	overText = wickets + " WICKET" + CricketFunctions.Plural(wickets).toUpperCase();
			        }
			    }
			    String header = status.equals(CricketUtil.CURRENT + CricketUtil.BOWLER) ? "THIS OVER" : "LAST OVER";
			    
			    CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section3$Side" + WhichSide +
				        "$FreeText$txt_Head*GEOM*TEXT SET " + header + " - " + overText + " \0", print_writers);

			    if (!status.equals(CricketUtil.CURRENT + CricketUtil.BOWLER)) {
			        isThisOverLimitExceed = false;
			        break;
			    }
			}
			break;
		case Constants.TRI_SERIES:  case Constants.MT20:
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$RightSection$Section3$Side" + WhichSide 
					+ "$ThisOver$ThisOverDataAll$select_DataType*FUNCTION*Omo*vis_con SET 1\0", print_writers);
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$RightSection$Section3$Side" + WhichSide 
					+ "$ThisOver$This_OverHead$select_ThisOverHead*FUNCTION*Omo*vis_con SET 1\0", print_writers);
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$RightSection$Section3$Side" + WhichSide 
					+ "$select_DataType*FUNCTION*Omo*vis_con SET 0\0", print_writers);
			
			for (BowlingCard boc : inning.getBowlingCard()) {
			    String status = boc.getStatus().toUpperCase();
			    if (!(status.equals(CricketUtil.CURRENT + CricketUtil.BOWLER) || status.equals(CricketUtil.LAST + CricketUtil.BOWLER))) 
			        continue;

			    String this_over = CricketFunctions.processThisOverRunsCount(boc.getPlayerId(), matchAllData.getEventFile().getEvents());

			    String[] overParts = this_over.split("-");
			    int runs = Integer.parseInt(overParts[0]);
			    int wickets = Integer.parseInt(overParts[2]);

			    String overText = "";
			    if (runs > 0) {
			    	overText = runs + " RUN" + CricketFunctions.Plural(runs).toUpperCase();
			    }
			    if (wickets > 0) {
			        if (overText.contains("RUN")) {
			        	overText = overText + " & " + wickets + " WICKET" + CricketFunctions.Plural(wickets).toUpperCase();
			        }else {
			        	overText = wickets + " WICKET" + CricketFunctions.Plural(wickets).toUpperCase();
			        }
			    }
			    String header = status.equals(CricketUtil.CURRENT + CricketUtil.BOWLER) ? "THIS OVER" : "LAST OVER";

			    CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$RightSection$Section3$Side" + WhichSide +
			        "$ThisOver$This_OverHead$txt_ThisOverHead*GEOM*TEXT SET " + header + " \0", print_writers);
			    CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$RightSection$Section3$Side" + WhichSide +
			        "$ThisOver$ThisOverDataAll$TotalScore$txt_ThisOverFigure*GEOM*TEXT SET " + overText + "\0", print_writers);

			    if (!status.equals(CricketUtil.CURRENT + CricketUtil.BOWLER)) {
			        isThisOverLimitExceed = false;
			        break;
			    }
			}
			break;
		}
	}
	public void teamColorAndPositon(List<PrintWriter> print_writers,  int WhichSide) {
		switch(config.getBroadcaster()) {
		case Constants.TRI_SERIES:  case Constants.MT20:
			switch(infobar.getSectionLtAnalytics().toUpperCase()) {
			case "BAT_PP": case "BALL_PP":
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$Lt_Analytics$HeaderGrp$ScaleY_Out$Side" + WhichSide + 
						"$img_Base1*TEXTURE*IMAGE SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_BASE1 : Constants.MT20_BASE1) + team.getTeamBadge() + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$Lt_Analytics$HeaderGrp$Text$Side" + WhichSide + 
						"$img_Text1*TEXTURE*IMAGE SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_TEXT1 : Constants.MT20_TEXT1) + team.getTeamBadge() + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$Lt_Analytics$HeaderGrp$RightTextBand$Base$Side" + WhichSide + 
						"$img_Base2*TEXTURE*IMAGE SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_BASE2 : Constants.MT20_BASE2) + team.getTeamBadge() + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$Lt_Analytics$HeaderGrp$RightTextBand$TextAll$Side" + WhichSide + 
						"$img_Text2*TEXTURE*IMAGE SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_TEXT2 : Constants.MT20_TEXT2) + team.getTeamBadge() + "\0", print_writers);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$Lt_Analytics$HeaderGrp$LogoAll$Side" + WhichSide + 
						"$img_Logos*TEXTURE*IMAGE SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_LOGO : Constants.MT20_LOGO)+ team.getTeamBadge() + "\0", print_writers);
				
				for (int i = 1; i <= 2; i++) {
				    String sidePath = "-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$Lt_Analytics$SublineGrp$Rows$Side" + WhichSide + "$" + i;
				    
				    // Always disable Left & Right
				    CricketFunctions.DoadWriteCommandToAllViz(sidePath + "$Left*ACTIVE SET 0\0", print_writers);
				    CricketFunctions.DoadWriteCommandToAllViz(sidePath + "$Right*ACTIVE SET 0\0", print_writers);

				    // Decide which section to enable (Title for i=1, Stat for i=2)
				    String enableSection = (i == 1) ? "Title" : "Stat";
				    String disableSection = (i == 1) ? "Stat" : "Title";

				    // Toggle main sections
				    CricketFunctions.DoadWriteCommandToAllViz(sidePath + "$" + enableSection + "*ACTIVE SET 1\0", print_writers);
				    CricketFunctions.DoadWriteCommandToAllViz(sidePath + "$" + disableSection + "*ACTIVE SET 0\0", print_writers);
				    
				 // Position mapping for j=1..5
				    int[] positions = {-165, -90, -10, 90, 170};
				    
				    // First 5 txt on, last 5 txt off
				    for (int j = 1; j <= 10; j++) {
				        int active = (j <= 5) ? 1 : 0;
				        CricketFunctions.DoadWriteCommandToAllViz(sidePath + "$" + enableSection + "$txt_" + j + "*ACTIVE SET " + active + "\0",print_writers);
				        if (j <= 5) {
				            CricketFunctions.DoadWriteCommandToAllViz(sidePath + "$" + enableSection + "$txt_" + j + "*TRANSFORMATION*POSITION*X SET " 
				            		+ positions[j - 1] + "\0",print_writers);
				        }
				    }
				}
				break;
			}
			break;
		}
	}
	
	public String populateSection1(List<PrintWriter> print_writers, MatchAllData matchAllData,int WhichSide) {
		switch(config.getBroadcaster()) {
		case Constants.AFG_SL_SERIES:
			if(infobar.getSection1() != null && !infobar.getSection1().isEmpty()) {
				switch(infobar.getSection1().toUpperCase()) {
				case "TEAMNAME":
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$CenterPart$Section1$Side" + WhichSide + "$select_DataType"
							+ "*FUNCTION*Omo*vis_con SET 1\0",print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$CenterPart$Section1$Side" + WhichSide + "$FreeText1"
							+ "$txt_Info1*GEOM*TEXT SET " + inning.getBatting_team().getTeamName4() + "v" + inning.getBowling_team().getTeamName4() + "\0", print_writers);
					break;
				case "VENUE":
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$CenterPart$Section1$Side" + WhichSide + "$select_DataType"
							+ "*FUNCTION*Omo*vis_con SET 1\0",print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$CenterPart$Section1$Side" + WhichSide + "$FreeText1"
							+ "$txt_Info1*GEOM*TEXT SET " + matchAllData.getSetup().getVenueName() + "\0", print_writers);
					break;
				case "IDENT":
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$CenterPart$Section1$Side" + WhichSide + "$select_DataType"
							+ "*FUNCTION*Omo*vis_con SET 1\0",print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$CenterPart$Section1$Side" + WhichSide + "$FreeText1"
							+ "$txt_Info1*GEOM*TEXT SET " + matchAllData.getSetup().getMatchIdent() + "\0", print_writers);
					break;
				case "SUPER_OVER":
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$CenterPart$Section1$Side" + WhichSide + "$select_DataType"
							+ "*FUNCTION*Omo*vis_con SET 1\0",print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$CenterPart$Section1$Side" + WhichSide + "$FreeText1"
							+ "$txt_Info1*GEOM*TEXT SET " + "SUPER OVER" + "\0", print_writers);
					break;
				case "EQUATION":
					if(!matchAllData.getMatch().getInning().get(1).getIsCurrentInning().equalsIgnoreCase(CricketUtil.YES)&&
							!matchAllData.getMatch().getInning().get(3).getIsCurrentInning().equalsIgnoreCase(CricketUtil.YES)) {
						return "populateSection1: Equation available in 2nd inning only";
					}
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$CenterPart$Section1$Side" + WhichSide + "$select_DataType"
							+ "*FUNCTION*Omo*vis_con SET 1\0",print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$CenterPart$Section1$Side" + WhichSide + "$FreeText1"
							+ "$txt_Info1*GEOM*TEXT SET " + "TO WIN " + CricketFunctions.GetTargetData(matchAllData).getRemaningRuns() + " OFF " + 
							CricketFunctions.GetTargetData(matchAllData).getRemaningBall() + "\0", print_writers);
					break;
				case "RRR":
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$CenterPart$Section1$Side" + WhichSide + "$select_DataType"
							+ "*FUNCTION*Omo*vis_con SET 1\0",print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$CenterPart$Section1$Side" + WhichSide + "$FreeText1"
							+ "$txt_Info1*GEOM*TEXT SET " + "REQUIRED RUN RATE : " + CricketFunctions.generateRunRate(CricketFunctions.GetTargetData(matchAllData).
									getRemaningRuns(),0,CricketFunctions.GetTargetData(matchAllData).getRemaningBall(),2,matchAllData) + "\0", print_writers);
					break;
				case "FIRST_INNING_SCORE":
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$CenterPart$Section1$Side" + WhichSide + "$select_DataType"
							+ "*FUNCTION*Omo*vis_con SET 2\0",print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$CenterPart$Section1$Side" + WhichSide + "$FreeText2"
							+ "$txt_Info1*GEOM*TEXT SET " + matchAllData.getMatch().getInning().get(0).getBatting_team().getTeamName4() + "\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$CenterPart$Section1$Side" + WhichSide + "$FreeText2"
							+ "$txt_Info2*GEOM*TEXT SET " + CricketFunctions.getTeamScore(matchAllData.getMatch().getInning().get(0), slashOrDash, false) + "\0", print_writers);
					break;
				case "TARGET":
					if(!matchAllData.getMatch().getInning().get(1).getIsCurrentInning().equalsIgnoreCase(CricketUtil.YES)&&
							!matchAllData.getMatch().getInning().get(3).getIsCurrentInning().equalsIgnoreCase(CricketUtil.YES)) {
						return "populateVizInfobarLeftBottom: Target available in 2nd inning only";
					}
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$CenterPart$Section1$Side" + WhichSide + "$select_DataType"
							+ "*FUNCTION*Omo*vis_con SET 1\0",print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$CenterPart$Section1$Side" + WhichSide + "$FreeText1"
							+ "$txt_Info1*GEOM*TEXT SET " + "TARGET : " + CricketFunctions.GetTargetData(matchAllData).getTargetRuns() + (matchAllData.getSetup().getTargetType() 
									!= null && !matchAllData.getSetup().getTargetType().isEmpty() ? " (" + matchAllData.getSetup().getTargetType().toUpperCase() + ")" 
											: "") + "\0", print_writers);
					break;
				case CricketUtil.TOSS:
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$CenterPart$Section1$Side" + WhichSide + "$Select_DataType"
							+ "*FUNCTION*Omo*vis_con SET 2\0",print_writers);
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$CenterPart$Section1$Side" + WhichSide + "$select_DataType"
							+ "*FUNCTION*Omo*vis_con SET 1\0",print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$CenterPart$Section1$Side" + WhichSide + "$FreeText1"
							+ "$txt_Info1*GEOM*TEXT SET " + CricketFunctions.generateTossResult(matchAllData, CricketUtil.FULL, CricketUtil.FIELD, CricketUtil.SHORT, 
									CricketUtil.CHOSE).replace(" won the toss & ", " ").toUpperCase() + "\0", print_writers);
					break;
				case "CRR":
					inning = matchAllData.getMatch().getInning().stream().filter(inn -> inn.getIsCurrentInning().equalsIgnoreCase(CricketUtil.YES)).findAny().orElse(null);
					
					if(inning == null) {
						return "populateVizInfobarLeftBottom: Inning return is NULL";
					}
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$CenterPart$Section1$Side" + WhichSide + "$select_DataType"
							+ "*FUNCTION*Omo*vis_con SET 1\0",print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$CenterPart$Section1$Side" + WhichSide + "$FreeText1"
							+ "$txt_Info1*GEOM*TEXT SET " + "CURRENT RUN RATE : " + CricketFunctions.generateRunRate(inning.getTotalRuns(),inning.getTotalOvers(), 
									inning.getTotalBalls(), 2,matchAllData) + "\0", print_writers);
					break;
				}
			}
			break;
		case Constants.TRI_SERIES:  case Constants.MT20:
			if(infobar.getSection1() != null && !infobar.getSection1().isEmpty()) {
				switch(infobar.getSection1().toUpperCase()) {
				case "TEAMNAME":
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$ScoreSection$Section1$Side" + WhichSide + "$Select_DataType"
							+ "*FUNCTION*Omo*vis_con SET 0\0",print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$ScoreSection$Section1$Side" + WhichSide + "$TeamNames"
							+ "$txt_BattingTeamName*GEOM*TEXT SET " + inning.getBatting_team().getTeamName3() + "\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$ScoreSection$Section1$Side" + WhichSide + "$TeamNames"
							+ "$txt_BowlingTeamName*GEOM*TEXT SET v " + inning.getBowling_team().getTeamName3() + "\0", print_writers);
					break;
				case "VENUE":
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$ScoreSection$Section1$Side" + WhichSide + "$Select_DataType"
							+ "*FUNCTION*Omo*vis_con SET 2\0",print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$ScoreSection$Section1$Side" + WhichSide + "$FreeText"
							+ "$txt_Text*GEOM*TEXT SET " + matchAllData.getSetup().getVenueName() + "\0", print_writers);
					break;
				case "IDENT":
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$ScoreSection$Section1$Side" + WhichSide + "$Select_DataType"
							+ "*FUNCTION*Omo*vis_con SET 2\0",print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$ScoreSection$Section1$Side" + WhichSide + "$FreeText"
							+ "$txt_Text*GEOM*TEXT SET " + matchAllData.getSetup().getMatchIdent() + "\0", print_writers);
					break;
				case "SUPER_OVER":
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$ScoreSection$Section1$Side" + WhichSide + "$Select_DataType"
							+ "*FUNCTION*Omo*vis_con SET 2\0",print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$ScoreSection$Section1$Side" + WhichSide + "$FreeText"
							+ "$txt_Text*GEOM*TEXT SET SUPER OVER\0", print_writers);
					break;
				case "EQUATION":
					if(!matchAllData.getMatch().getInning().get(1).getIsCurrentInning().equalsIgnoreCase(CricketUtil.YES)&&
							!matchAllData.getMatch().getInning().get(3).getIsCurrentInning().equalsIgnoreCase(CricketUtil.YES)) {
						return "populateSection1: Equation available in 2nd inning only";
					}
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$ScoreSection$Section1$Side" + WhichSide + "$Select_DataType"
							+ "*FUNCTION*Omo*vis_con SET 2\0",print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$ScoreSection$Section1$Side" + WhichSide + "$FreeText"
							+ "$txt_Text*GEOM*TEXT SET " + "TO WIN " + CricketFunctions.GetTargetData(matchAllData).getRemaningRuns() + " OFF " + 
							CricketFunctions.GetTargetData(matchAllData).getRemaningBall() + "\0", print_writers);
					break;
				case "RRR":				
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$ScoreSection$Section1$Side" + WhichSide + "$Select_DataType"
							+ "*FUNCTION*Omo*vis_con SET 1\0",print_writers);
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$ScoreSection$Section1$Side" + WhichSide + "$Stats"
							+ "$txt_StatHead*GEOM*TEXT SET REQUIRED RUN RATE\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$ScoreSection$Section1$Side" + WhichSide + "$Stats"
							+ "$txt_StatValue*GEOM*TEXT SET " + CricketFunctions.generateRunRate(CricketFunctions.GetTargetData(matchAllData).
									getRemaningRuns(),0,CricketFunctions.GetTargetData(matchAllData).getRemaningBall(),2,matchAllData) + "\0", print_writers);
					break;
				case "FIRST_INNING_SCORE":
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$ScoreSection$Section1$Side" + WhichSide + "$Select_DataType"
							+ "*FUNCTION*Omo*vis_con SET 1\0",print_writers);
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$ScoreSection$Section1$Side" + WhichSide + "$Stats"
							+ "$txt_StatHead*GEOM*TEXT SET " + matchAllData.getMatch().getInning().get(0).getBatting_team().getTeamName1() + "\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$ScoreSection$Section1$Side" + WhichSide + "$Stats"
							+ "$txt_StatValue*GEOM*TEXT SET " + CricketFunctions.getTeamScore(matchAllData.getMatch().getInning().get(0), slashOrDash, false) 
							+ "\0", print_writers);
					break;
				case "TARGET":
					if(!matchAllData.getMatch().getInning().get(1).getIsCurrentInning().equalsIgnoreCase(CricketUtil.YES)&&
							!matchAllData.getMatch().getInning().get(3).getIsCurrentInning().equalsIgnoreCase(CricketUtil.YES)) {
						return "populateVizInfobarLeftBottom: Target available in 2nd inning only";
					}
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$ScoreSection$Section1$Side" + WhichSide + "$Select_DataType"
							+ "*FUNCTION*Omo*vis_con SET 1\0",print_writers);
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$ScoreSection$Section1$Side" + WhichSide + "$Stats"
							+ "$txt_StatHead*GEOM*TEXT SET TARGET\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$ScoreSection$Section1$Side" + WhichSide + "$Stats"
							+ "$txt_StatValue*GEOM*TEXT SET " + CricketFunctions.GetTargetData(matchAllData).getTargetRuns() + (matchAllData.getSetup().getTargetType() 
									!= null && !matchAllData.getSetup().getTargetType().isEmpty() ? " (" + matchAllData.getSetup().getTargetType().toUpperCase() + ")" 
											: "") + "\0", print_writers);
					break;
				case CricketUtil.TOSS:
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$ScoreSection$Section1$Side" + WhichSide + "$Select_DataType"
							+ "*FUNCTION*Omo*vis_con SET 2\0",print_writers);
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$ScoreSection$Section1$Side" + WhichSide + "$FreeText"
							+ "$txt_Text*GEOM*TEXT SET " + CricketFunctions.generateTossResult(matchAllData, CricketUtil.FULL, CricketUtil.FIELD, CricketUtil.SHORT, 
									CricketUtil.CHOSE).replace(" won the toss & ", " ").toUpperCase() + "\0", print_writers);
					break;
				case "CRR":
					inning = matchAllData.getMatch().getInning().stream().filter(inn -> inn.getIsCurrentInning().equalsIgnoreCase(CricketUtil.YES)).findAny().orElse(null);
					
					if(inning == null) {
						return "populateVizInfobarLeftBottom: Inning return is NULL";
					}
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$ScoreSection$Section1$Side" + WhichSide + "$Select_DataType"
							+ "*FUNCTION*Omo*vis_con SET 1\0",print_writers);
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$ScoreSection$Section1$Side" + WhichSide + "$Stats"
							+ "$txt_StatHead*GEOM*TEXT SET CURRENT RUN RATE\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$ScoreSection$Section1$Side" + WhichSide + "$Stats"
							+ "$txt_StatValue*GEOM*TEXT SET " + CricketFunctions.generateRunRate(inning.getTotalRuns(),inning.getTotalOvers(), 
									inning.getTotalBalls(), 2,matchAllData) + "\0", print_writers);
					break;
				}
			}
			break;
		case Constants.TG20:
			if(infobar.getSection1() != null && !infobar.getSection1().isEmpty()) {
				switch(infobar.getSection1().toUpperCase()) {
				case "TEAMNAME":
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$ScoreSection$Section1$Side" + WhichSide + "$Select_DataType"
							+ "*FUNCTION*Omo*vis_con SET 0\0",print_writers);
					
					CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$ScoreSection$Section1$Side" + WhichSide + "$TeamNames$select_Language"
							+ "*FUNCTION*Omo*vis_con SET ", config, Constants.TG20, print_writers, foreignLanguageOmo);
					foreignLanguageData = CricketFunctions.AssembleMultiLanguageData(CricketUtil.TEAM, CricketUtil.TEAMNAME_4, multilanguagedata, inning.getBatting_team().getTeamName1(), "", 
							null, 0, foreignLanguageDataList);
					CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$ScoreSection$Section1$Side" + WhichSide + "$TeamNames$English"
							+ "$txt_BattingTeamName*GEOM*TEXT SET ", config, Constants.TG20, print_writers, foreignLanguageData);
					
					CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$ScoreSection$Section1$Side" + WhichSide + "$TeamNames$select_Language"
							+ "*FUNCTION*Omo*vis_con SET ", config, Constants.TG20, print_writers, foreignLanguageOmo);
					foreignLanguageDataList = CricketFunctions.AssembleMultiLanguageData(CricketUtil.DICTIONARY, "", multilanguagedata, "v ", "", null, 1,foreignLanguageDataList);
					foreignLanguageDataList = CricketFunctions.AssembleMultiLanguageData(CricketUtil.TEAM, CricketUtil.TEAMNAME_4, multilanguagedata, inning.getBowling_team().getTeamName1(), 
					    "", null, 2, foreignLanguageDataList);
					foreignLanguageData.add(CricketFunctions.MergeForeignLanguageDataListToSingleObject(foreignLanguageDataList));
					CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$ScoreSection$Section1$Side" + WhichSide + "$TeamNames$English"
							+ "$txt_BowlingTeamName*GEOM*TEXT SET ", config, Constants.TG20, print_writers, foreignLanguageData);
					break;
				case "VENUE":
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$ScoreSection$Section1$Side" + WhichSide + "$Select_DataType"
							+ "*FUNCTION*Omo*vis_con SET 2\0",print_writers);
					
					CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$ScoreSection$Section1$Side" + WhichSide + "$FreeText$select_Language"
							+ "*FUNCTION*Omo*vis_con SET ", config, Constants.TG20, print_writers, foreignLanguageOmo);
					foreignLanguageData = CricketFunctions.AssembleMultiLanguageData(CricketUtil.DICTIONARY, "", multilanguagedata, matchAllData.getSetup().getVenueName(), "", null, 0, foreignLanguageDataList);
					CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$ScoreSection$Section1$Side" + WhichSide + "$FreeText$English"
							+ "$txt_Text*GEOM*TEXT SET ", config, Constants.TG20, print_writers, foreignLanguageData);
					break;
				case "IDENT":
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$ScoreSection$Section1$Side" + WhichSide + "$Select_DataType"
							+ "*FUNCTION*Omo*vis_con SET 2\0",print_writers);
					CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$ScoreSection$Section1$Side" + WhichSide + "$FreeText$select_Language"
							+ "*FUNCTION*Omo*vis_con SET ", config, Constants.TG20, print_writers, foreignLanguageOmo);
					foreignLanguageDataList = CricketFunctions.AssembleMultiLanguageData(CricketUtil.DICTIONARY, "", multilanguagedata, matchAllData.getSetup().getMatchIdent().split(" ")[0].trim(), 
						    "", null, 1,foreignLanguageDataList);
					foreignLanguageDataList = CricketFunctions.AssembleMultiLanguageData(CricketUtil.DICTIONARY, "", multilanguagedata, matchAllData.getSetup().getMatchIdent().split(" ")[1].trim(), 
					    "", null, 2, foreignLanguageDataList);
					foreignLanguageData.add(CricketFunctions.MergeForeignLanguageDataListToSingleObject(foreignLanguageDataList));
					
					CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$ScoreSection$Section1$Side" + WhichSide + "$FreeText$English"
							+ "$txt_Text*GEOM*TEXT SET ", config, Constants.TG20, print_writers, foreignLanguageData);
					break;
				case "SUPER_OVER":
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$ScoreSection$Section1$Side" + WhichSide + "$Select_DataType"
							+ "*FUNCTION*Omo*vis_con SET 2\0",print_writers);
					
					CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$ScoreSection$Section1$Side" + WhichSide + "$FreeText$select_Language"
							+ "*FUNCTION*Omo*vis_con SET ", config, Constants.TG20, print_writers, foreignLanguageOmo);
					foreignLanguageData = CricketFunctions.AssembleMultiLanguageData(CricketUtil.DICTIONARY, "", multilanguagedata, "SUPER OVER", "", null, 0, foreignLanguageDataList);
					CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$ScoreSection$Section1$Side" + WhichSide + "$FreeText$English"
							+ "$txt_Text*GEOM*TEXT SET ", config, Constants.TG20, print_writers, foreignLanguageData);
					break;
				case "EQUATION":
					if(!matchAllData.getMatch().getInning().get(1).getIsCurrentInning().equalsIgnoreCase(CricketUtil.YES)&&
							!matchAllData.getMatch().getInning().get(3).getIsCurrentInning().equalsIgnoreCase(CricketUtil.YES)) {
						return "populateSection1: Equation available in 2nd inning only";
					}
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$ScoreSection$Section1$Side" + WhichSide + "$Select_DataType"
							+ "*FUNCTION*Omo*vis_con SET 2\0",print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$ScoreSection$Section1$Side" + WhichSide + "$FreeText"
							+ "$txt_Text*GEOM*TEXT SET " + "TO WIN " + CricketFunctions.GetTargetData(matchAllData).getRemaningRuns() + " OFF " + 
							CricketFunctions.GetTargetData(matchAllData).getRemaningBall() + "\0", print_writers);
					break;
				case "RRR":				
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$ScoreSection$Section1$Side" + WhichSide + "$Select_DataType"
							+ "*FUNCTION*Omo*vis_con SET 1\0",print_writers);
					
					CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$ScoreSection$Section1$Side" + WhichSide + "$Stats$select_Language"
							+ "*FUNCTION*Omo*vis_con SET ", config, Constants.TG20, print_writers, foreignLanguageOmo);
					foreignLanguageData = CricketFunctions.AssembleMultiLanguageData(CricketUtil.DICTIONARY, "", multilanguagedata, "REQUIRED RUN RATE", "", null, 0, foreignLanguageDataList);
					CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$ScoreSection$Section1$Side" + WhichSide + "$Stats$English"
							+ "$txt_StatHead*GEOM*TEXT SET ", config, Constants.TG20, print_writers, foreignLanguageData);
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$ScoreSection$Section1$Side" + WhichSide + "$Stats"
							+ "$txt_StatValue*GEOM*TEXT SET " + CricketFunctions.generateRunRate(CricketFunctions.GetTargetData(matchAllData).
									getRemaningRuns(),0,CricketFunctions.GetTargetData(matchAllData).getRemaningBall(),2,matchAllData) + "\0", print_writers);
					break;
				case "FIRST_INNING_SCORE":
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$ScoreSection$Section1$Side" + WhichSide + "$Select_DataType"
							+ "*FUNCTION*Omo*vis_con SET 1\0",print_writers);
					
					CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$ScoreSection$Section1$Side" + WhichSide + "$FreeText$select_Language"
							+ "*FUNCTION*Omo*vis_con SET ", config, Constants.TG20, print_writers, foreignLanguageOmo);
					foreignLanguageData = CricketFunctions.AssembleMultiLanguageData(CricketUtil.TEAM, "", multilanguagedata, matchAllData.getMatch().getInning().get(0).getBatting_team().getTeamName1(), 
							"", null, 0, foreignLanguageDataList);
					CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$ScoreSection$Section1$Side" + WhichSide + "$Stats$English"
							+ "$txt_StatHead*GEOM*TEXT SET ", config, Constants.TG20, print_writers, foreignLanguageData);

					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$ScoreSection$Section1$Side" + WhichSide + "$Stats"
							+ "$txt_StatValue*GEOM*TEXT SET " + CricketFunctions.getTeamScore(matchAllData.getMatch().getInning().get(0), slashOrDash, false) 
							+ "\0", print_writers);
					break;
				case "TARGET":
					if(!matchAllData.getMatch().getInning().get(1).getIsCurrentInning().equalsIgnoreCase(CricketUtil.YES)&&
							!matchAllData.getMatch().getInning().get(3).getIsCurrentInning().equalsIgnoreCase(CricketUtil.YES)) {
						return "populateVizInfobarLeftBottom: Target available in 2nd inning only";
					}
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$ScoreSection$Section1$Side" + WhichSide + "$Select_DataType"
							+ "*FUNCTION*Omo*vis_con SET 1\0",print_writers);
					
					CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$ScoreSection$Section1$Side" + WhichSide + "$FreeText$select_Language"
							+ "*FUNCTION*Omo*vis_con SET ", config, Constants.TG20, print_writers, foreignLanguageOmo);
					foreignLanguageData = CricketFunctions.AssembleMultiLanguageData(CricketUtil.DICTIONARY, "", multilanguagedata, "TARGET", "", null, 0, foreignLanguageDataList);
					CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$ScoreSection$Section1$Side" + WhichSide + "$Stats$English"
							+ "$txt_StatHead*GEOM*TEXT SET ", config, Constants.TG20, print_writers, foreignLanguageData);

					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$ScoreSection$Section1$Side" + WhichSide + "$Stats"
							+ "$txt_StatValue*GEOM*TEXT SET " + CricketFunctions.GetTargetData(matchAllData).getTargetRuns() + (matchAllData.getSetup().getTargetType() 
									!= null && !matchAllData.getSetup().getTargetType().isEmpty() ? " (" + matchAllData.getSetup().getTargetType().toUpperCase() + ")" 
											: "") + "\0", print_writers);
					break;
				case CricketUtil.TOSS:
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$ScoreSection$Section1$Side" + WhichSide + "$Select_DataType"
							+ "*FUNCTION*Omo*vis_con SET 2\0",print_writers);
					
					String toss_data = CricketFunctions.generateTossResult(matchAllData, CricketUtil.FULL, CricketUtil.FIELD, CricketUtil.FULL, CricketUtil.CHOSE).toUpperCase();
					
					CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$ScoreSection$Section1$Side" + WhichSide + "$FreeText$select_Language"
							+ "*FUNCTION*Omo*vis_con SET ", config, Constants.TG20, print_writers, foreignLanguageOmo);
					
					System.out.println(toss_data.split(" WON THE TOSS & ")[0].trim());
					System.out.println(toss_data.split(" WON THE TOSS & ")[1].trim());
					
					foreignLanguageDataList = CricketFunctions.AssembleMultiLanguageData(CricketUtil.TEAM, CricketUtil.TEAMNAME_4, multilanguagedata, toss_data.split(" WON THE TOSS & ")[0].trim(), 
						    "", null, 1,foreignLanguageDataList);
					foreignLanguageDataList = CricketFunctions.AssembleMultiLanguageData(CricketUtil.DICTIONARY, "", multilanguagedata, toss_data.split(" WON THE TOSS & ")[1].trim(), 
					    "", null, 2, foreignLanguageDataList);
					foreignLanguageData.add(CricketFunctions.MergeForeignLanguageDataListToSingleObject(foreignLanguageDataList));
					
					CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$ScoreSection$Section1$Side" + WhichSide + "$FreeText$English"
							+ "$txt_Text*GEOM*TEXT SET ", config, Constants.TG20, print_writers, foreignLanguageData);
					break;
				case "CRR":
					inning = matchAllData.getMatch().getInning().stream().filter(inn -> inn.getIsCurrentInning().equalsIgnoreCase(CricketUtil.YES)).findAny().orElse(null);
					
					if(inning == null) {
						return "populateVizInfobarLeftBottom: Inning return is NULL";
					}
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$ScoreSection$Section1$Side" + WhichSide + "$Select_DataType"
							+ "*FUNCTION*Omo*vis_con SET 1\0",print_writers);
					
					CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$ScoreSection$Section1$Side" + WhichSide + "$Stats$select_Language"
							+ "*FUNCTION*Omo*vis_con SET ", config, Constants.TG20, print_writers, foreignLanguageOmo);
					foreignLanguageData = CricketFunctions.AssembleMultiLanguageData(CricketUtil.DICTIONARY, "", multilanguagedata, "CURRENT RUN RATE", "", null, 0, foreignLanguageDataList);
					CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$ScoreSection$Section1$Side" + WhichSide + "$Stats$English"
							+ "$txt_StatHead*GEOM*TEXT SET ", config, Constants.TG20, print_writers, foreignLanguageData);
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$ScoreSection$Section1$Side" + WhichSide + "$Stats"
							+ "$txt_StatValue*GEOM*TEXT SET " + CricketFunctions.generateRunRate(inning.getTotalRuns(),inning.getTotalOvers(), 
									inning.getTotalBalls(), 2,matchAllData) + "\0", print_writers);
					break;
				}
			}
			break;
		}
		return Constants.OK;
	}
	public String populateSection2(List<PrintWriter> print_writers, MatchAllData matchAllData,int WhichSide) {
		switch(config.getBroadcaster()) {
		case Constants.AFG_SL_SERIES:
			if(infobar.getSection2() != null && !infobar.getSection2().isEmpty()) {
				switch(infobar.getSection2().toUpperCase()) {
				case "RRR":
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$BatttingGrp$Section2$Side" + WhichSide + "$select_DataType"
							+ "*FUNCTION*Omo*vis_con SET 2\0",print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$BatttingGrp$Section2$Side" + WhichSide + "$FreeText"
							+ "$select_LineNumber*FUNCTION*Omo*vis_con SET 1\0",print_writers);
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$BatttingGrp$Section2$Side" + WhichSide + "$FreeText"
							+ "$txt_Info1*GEOM*TEXT SET REQUIRED RUN RATE: " + CricketFunctions.generateRunRate(CricketFunctions.GetTargetData(matchAllData).
									getRemaningRuns(),0,CricketFunctions.GetTargetData(matchAllData).getRemaningBall(),2,matchAllData) + "\0", print_writers);
					break;
				case "PROJECTED_SCORE":
					inning = matchAllData.getMatch().getInning().stream().filter(inn -> inn.getInningNumber() == 1 &&
					inn.getIsCurrentInning().equalsIgnoreCase(CricketUtil.YES)).findAny().orElse(null);
			
					if(inning == null) {
						return "populateVizInfobarRightSection: 1st Inning returned is NULL";
					}
					this_data_str = CricketFunctions.GetProjectedScore(matchAllData);
					
					if(this_data_str.size() <= 0) {
						return "populateVizInfobarRightSection: Projected score invalid";
					}
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$BatttingGrp$Section2$Side" + WhichSide + "$select_DataType"
							+ "*FUNCTION*Omo*vis_con SET 2\0",print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$BatttingGrp$Section2$Side" + WhichSide + "$FreeText"
							+ "$select_LineNumber*FUNCTION*Omo*vis_con SET 2\0",print_writers);
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$BatttingGrp$Section2$Side" + WhichSide + "$FreeText"
							+ "$txt_Info1*GEOM*TEXT SET PROJECTED SCORE\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$BatttingGrp$Section2$Side" + WhichSide + "$FreeText"
							+ "$txt_Info2*GEOM*TEXT SET @ CRR (" + this_data_str.get(0) + ") - " + this_data_str.get(1) + "\0", print_writers);
					break;
				
				case "CURR_PARTNERSHIP": case "CRR":
					inning = matchAllData.getMatch().getInning().stream().filter(inn -> inn.getIsCurrentInning().equalsIgnoreCase(CricketUtil.YES)).findAny().orElse(null);
					if(inning == null) {
						return "populateVizInfobarMiddleSection: Inning returned is NULL";
					}
					
					switch(infobar.getSection2().toUpperCase()) {
					case "CRR":
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$BatttingGrp$Section2$Side" + WhichSide + "$select_DataType"
								+ "*FUNCTION*Omo*vis_con SET 2\0",print_writers);
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$BatttingGrp$Section2$Side" + WhichSide + "$FreeText"
								+ "$select_LineNumber*FUNCTION*Omo*vis_con SET 1\0",print_writers);
						
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$BatttingGrp$Section2$Side" + WhichSide + "$FreeText"
								+ "$txt_Info1*GEOM*TEXT SET CURRENT RUN RATE: " + CricketFunctions.generateRunRate(inning.getTotalRuns(),inning.getTotalOvers(), 
										inning.getTotalBalls(), 2,matchAllData) + "\0", print_writers);
						break;	
					case "CURR_PARTNERSHIP":
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$BatttingGrp$Section2$Side" + WhichSide + "$select_DataType"
								+ "*FUNCTION*Omo*vis_con SET 0\0",print_writers);
						
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$BatttingGrp$Section2$Side" + WhichSide + "$Partnership"
								+ "$txt_Title*GEOM*TEXT SET CURR P'SHIP\0", print_writers);
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$BatttingGrp$Section2$Side" + WhichSide + "$Partnership"
								+ "$txt_Runs*GEOM*TEXT SET " + inning.getPartnerships().get(inning.getPartnerships().size()-1).getTotalRuns() + "*" + "\0", print_writers);
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$BatttingGrp$Section2$Side" + WhichSide + "$Partnership"
								+ "$txt_Balls*GEOM*TEXT SET " + inning.getPartnerships().get(inning.getPartnerships().size()-1).getTotalBalls() + "\0", print_writers);
						break;
					}
					break;
				}
			}
			break;
		case Constants.ACC:
			if(infobar.getSection2() != null && !infobar.getSection2().isEmpty()) {
				switch(infobar.getSection2().toUpperCase()) {
				case "TOURNAMENT":
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section2$Side" + WhichSide + "$Select"
							+ "*FUNCTION*Omo*vis_con SET 0\0",print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section2$Side" + WhichSide + 
							 "$Rectangle*TEXTURE*IMAGE SET " + Constants.ACC_FLAG + "TLogo" + "\0", print_writers);
					break;
				case "LIVE_FROM":
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section2$Side" + WhichSide + "$Select"
							+ "*FUNCTION*Omo*vis_con SET 1\0",print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section2$Side" + WhichSide + "$Toss$txt_Header"
							+ "*GEOM*TEXT SET FROM\0", print_writers);
					
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section2$Side" + WhichSide + "$Toss$txt_Data1"
							+ "*GEOM*TEXT SET " + (matchAllData.getSetup().getGroundId() == 1 ? matchAllData.getSetup().getGround().
									getCity() : matchAllData.getSetup().getGround().getCity()) + "\0", print_writers);
					break;	
				case "SUPER_OVER":
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section2$Side" + WhichSide + "$Select"
							+ "*FUNCTION*Omo*vis_con SET 1\0",print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section2$Side" + WhichSide + "$Toss$txt_Header"
							+ "*GEOM*TEXT SET SUPER\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section2$Side" + WhichSide + "$Toss$txt_Data1"
							+ "*GEOM*TEXT SET OVER\0", print_writers);
					break;
				case "MATCH_IDENT":
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section2$Side" + WhichSide + "$Select"
							+ "*FUNCTION*Omo*vis_con SET 1\0",print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section2$Side" + WhichSide + "$Toss$txt_Header"
							+ "*GEOM*TEXT SET " + matchAllData.getSetup().getMatchIdent() + "\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section2$Side" + WhichSide + "$Toss$txt_Data1"
							+ "*GEOM*TEXT SET " + matchAllData.getSetup().getHomeTeam().getTeamName4() + " v " + 
							matchAllData.getSetup().getAwayTeam().getTeamName4() + "\0", print_writers);
					break;	
				case "TOSS":
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section2$Side" + WhichSide + "$Select"
							+ "*FUNCTION*Omo*vis_con SET 1\0",print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section2$Side" + WhichSide + "$Toss$txt_Header"
							+ "*GEOM*TEXT SET TOSS\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section2$Side" + WhichSide + "$Toss$txt_Data1"
							+ "*GEOM*TEXT SET " + (matchAllData.getSetup().getTossWinningTeam() == matchAllData.getSetup().getHomeTeamId() ? 
							matchAllData.getSetup().getHomeTeam().getTeamName4() : matchAllData.getSetup().getAwayTeam().getTeamName4()) + "\0", print_writers);
					break;
				case "BOUNDARY":
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section2$Side" + WhichSide + "$Select"
							+ "*FUNCTION*Omo*vis_con SET 1\0",print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section2$Side" + WhichSide + "$Toss$txt_Header"
							+ "*GEOM*TEXT SET INNS BOUNDARIES\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section2$Side" + WhichSide + "$Toss$txt_Data1"
							+ "*GEOM*TEXT SET " + "4s : " + inning.getTotalFours() + "  6s : " + inning.getTotalSixes() + "\0", print_writers);
					break;
				case "EQUATION":
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section2$Side" + WhichSide + "$Select"
							+ "*FUNCTION*Omo*vis_con SET 7\0",print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section2$Side" + WhichSide + "$Equation$txt_Header"
							+ "*GEOM*TEXT SET NEED " + CricketFunctions.GetTargetData(matchAllData).getRemaningRuns() + "\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section2$Side" + WhichSide + "$Equation$txt_Data1"
							+ "*GEOM*TEXT SET " + "OFF " + CricketFunctions.GetTargetData(matchAllData).getRemaningBall() + " BALL" + 
							CricketFunctions.Plural(CricketFunctions.GetTargetData(matchAllData).getRemaningBall()).toUpperCase() + "\0", print_writers);
					break;	
				case "COMPARE":
					inning = matchAllData.getMatch().getInning().stream().filter(inn -> inn.getIsCurrentInning().equalsIgnoreCase(CricketUtil.YES)
							&& inn.getInningNumber() == 2).findAny().orElse(null);
						
					if(inning == null) {
						return "populateVizInfobarRightSection: 2nd Inning returned is NULL";
					}
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section2$Side" + WhichSide + "$Select"
							+ "*FUNCTION*Omo*vis_con SET 1\0",print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section2$Side" + WhichSide + "$Toss$txt_Header"
							+ "*GEOM*TEXT SET " + inning.getBowling_team().getTeamName4() + " WERE" + "\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section2$Side" + WhichSide + "$Toss$txt_Data1"
							+ "*GEOM*TEXT SET " + CricketFunctions.compareInningData(matchAllData, "-", 
									1, matchAllData.getEventFile().getEvents()) + "\0", print_writers);
					break;	
				case "EXTRAS":
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section2$Side" + WhichSide + "$Select"
							+ "*FUNCTION*Omo*vis_con SET 1\0",print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section2$Side" + WhichSide + "$Toss$txt_Header"
							+ "*GEOM*TEXT SET TOTAL EXTRAS\0", print_writers);
					
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section2$Side" + WhichSide + "$Toss$txt_Data1"
							+ "*GEOM*TEXT SET " + inning.getTotalExtras() + "\0", print_writers);
					break;
				case "LAST_WICKET":
					if(inning.getFallsOfWickets() == null && inning.getFallsOfWickets().isEmpty()) {
						return "populateVizInfobarSection5: FoW returned is EMPTY";
					}
					battingCard = inning.getBattingCard().stream().filter(bc -> bc.getPlayerId() == 
							inning.getFallsOfWickets().get(inning.getFallsOfWickets().size() - 1).getFowPlayerID()).findAny().orElse(null);
					
					if(battingCard == null) {
						return "populateVizInfobarSection5: Last wicket returned is invalid";
					}
					
					String how_out_txt = "";
					if(battingCard.getHowOut().equalsIgnoreCase(CricketUtil.RUN_OUT)) {
						if(battingCard.getWasHowOutFielderSubstitute() != null && battingCard.getWasHowOutFielderSubstitute().equalsIgnoreCase(CricketUtil.YES)) {
							how_out_txt = "run out " + "(sub " + battingCard.getHowOutFielder().getTicker_name() + ")";
						} else {
							how_out_txt = "run out (" + battingCard.getHowOutFielder().getTicker_name() + ")";
						}
					}
					else if(battingCard.getHowOut().equalsIgnoreCase(CricketUtil.CAUGHT)) {
						if(battingCard.getWasHowOutFielderSubstitute() != null && battingCard.getWasHowOutFielderSubstitute().equalsIgnoreCase(CricketUtil.YES)) {
							how_out_txt = "c " +  "(sub - " + battingCard.getHowOutFielder().getTicker_name() + ")  b " + battingCard.getHowOutBowler().getTicker_name();
						} else {
							how_out_txt = "c " + battingCard.getHowOutFielder().getTicker_name() + "  b " + battingCard.getHowOutBowler().getTicker_name();
						}
					}
					else if(battingCard.getHowOut().equalsIgnoreCase(CricketUtil.STUMPED)) {
						if(battingCard.getWasHowOutFielderSubstitute() != null && battingCard.getWasHowOutFielderSubstitute().equalsIgnoreCase(CricketUtil.YES)) {
							how_out_txt = "st " +  "(sub - " + battingCard.getHowOutFielder().getTicker_name() + ")  b " + battingCard.getHowOutBowler().getTicker_name();
						} else {
							how_out_txt = "st " + battingCard.getHowOutFielder().getTicker_name() + "  b " + battingCard.getHowOutBowler().getTicker_name();
						}
					}
					else {
						if(!battingCard.getHowOutPartOne().isEmpty()) {
							how_out_txt = battingCard.getHowOutPartOne();
						}
						if(!battingCard.getHowOutPartTwo().isEmpty()) {
							if(!how_out_txt.trim().isEmpty()) {
								how_out_txt = how_out_txt + "  " + battingCard.getHowOutPartTwo();
							}else {
								how_out_txt = battingCard.getHowOutPartTwo();
							}
						}
					}
					
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section2$Side" + WhichSide + "$Select"
							+ "*FUNCTION*Omo*vis_con SET 1\0",print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section2$Side" + WhichSide + "$Toss$txt_Header"
							+ "*GEOM*TEXT SET LAST WICKET\0", print_writers);
					
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section2$Side" + WhichSide + "$Toss$txt_Data1"
							+ "*GEOM*TEXT SET " + battingCard.getPlayer().getTicker_name() + " " + battingCard.getRuns() + " (" + battingCard.getBalls() + ")" + "\0", print_writers);
					break;
				case "PROJECTED":
					inning = matchAllData.getMatch().getInning().stream().filter(inn -> inn.getInningNumber() == 1 &&
						inn.getIsCurrentInning().equalsIgnoreCase(CricketUtil.YES)).findAny().orElse(null);
			
					if(inning == null) {
						return "populateVizInfobarRightSection: 1st Inning returned is NULL";
					}
					
					System.out.println(CricketFunctions.projectedScore(matchAllData));
					this_data_str = CricketFunctions.projectedScore(matchAllData);
					
					if(this_data_str.size() <= 0) {
						return "populateVizInfobarRightSection: Projected score invalid";
					}
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section2$Side" + WhichSide + "$Select"
							+ "*FUNCTION*Omo*vis_con SET 1\0",print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section2$Side" + WhichSide + "$Toss$txt_Header"
							+ "*GEOM*TEXT SET PROJECTED\0", print_writers);
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section2$Side" + WhichSide + "$Toss$txt_Data1"
							+ "*GEOM*TEXT SET " + this_data_str.get(1) + " @CRR(" + this_data_str.get(0) + ")" + "\0", print_writers);
					break;	
				case "TARGET":
					if(!matchAllData.getMatch().getInning().get(1).getIsCurrentInning().equalsIgnoreCase(CricketUtil.YES)&&
							!matchAllData.getMatch().getInning().get(3).getIsCurrentInning().equalsIgnoreCase(CricketUtil.YES)) {
						return "populateVizInfobarLeftBottom: Target available in 2nd inning only";
					}
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section2$Side" + WhichSide + "$Select"
							+ "*FUNCTION*Omo*vis_con SET 2\0",print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section2$Side" + WhichSide + "$Target"
							+ "$txt_Header*GEOM*TEXT SET TARGET\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section2$Side" + WhichSide + "$Target"
							+ "$txt_Data1*GEOM*TEXT SET " + CricketFunctions.GetTargetData(matchAllData).getTargetRuns() + "\0", print_writers);
					break;
				case "RRR":
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section2$Side" + WhichSide + "$Select"
							+ "*FUNCTION*Omo*vis_con SET 6\0",print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section2$Side" + WhichSide + "$ReqRunRate$txt_Header"
							+ "*GEOM*TEXT SET REQ. RR\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section2$Side" + WhichSide + "$ReqRunRate$txt_Data1"
							+ "*GEOM*TEXT SET " + CricketFunctions.generateRunRate(CricketFunctions.GetTargetData(matchAllData).getRemaningRuns(), 
									0, CricketFunctions.GetTargetData(matchAllData).getRemaningBall(), 2, matchAllData) + "\0", print_writers);				
					break;
				case "CURR_PARTNERSHIP": case "CRR":
					inning = matchAllData.getMatch().getInning().stream().filter(inn -> inn.getIsCurrentInning().equalsIgnoreCase(CricketUtil.YES)).findAny().orElse(null);
					if(inning == null) {
						return "populateVizInfobarMiddleSection: Inning returned is NULL";
					}
					
					switch(infobar.getSection2().toUpperCase()) {
					case "CRR":
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section2$Side" + WhichSide + "$Select"
								+ "*FUNCTION*Omo*vis_con SET 3\0",print_writers);
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section2$Side" + WhichSide + "$RunRate$txt_Header"
								+ "*GEOM*TEXT SET CRR\0", print_writers);
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section2$Side" + WhichSide + "$RunRate$txt_Data1"
								+ "*GEOM*TEXT SET " + CricketFunctions.generateRunRate(inning.getTotalRuns(),inning.getTotalOvers(), 
										inning.getTotalBalls(), 2,matchAllData) + "\0", print_writers);
						break;	
					case "CURR_PARTNERSHIP":
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section2$Side" + WhichSide + "$Select"
								+ "*FUNCTION*Omo*vis_con SET 4\0",print_writers);
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section2$Side" + WhichSide + "$Partnership$txt_Header"
								+ "*GEOM*TEXT SET PARTNERSHIP\0", print_writers);
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section2$Side" + WhichSide + "$Partnership$txt_Data1"
								+ "*GEOM*TEXT SET " + inning.getPartnerships().get(inning.getPartnerships().size()-1).getTotalRuns() + "*" + "\0", print_writers);
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section2$Side" + WhichSide + "$Partnership$txt_Data2"
								+ "*GEOM*TEXT SET " + inning.getPartnerships().get(inning.getPartnerships().size()-1).getTotalBalls() + "\0", print_writers);
						break;
					}
					break;
				}
			}
			break;
		case Constants.BAN_AFG_SERIES:
			if(infobar.getSection2() != null && !infobar.getSection2().isEmpty()) {
				switch(infobar.getSection2().toUpperCase()) {
				
				case "MATCH_IDENT":
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section2$Side" + WhichSide + "$Select"
							+ "*FUNCTION*Omo*vis_con SET 0\0",print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section2$Side" + WhichSide + "$Toss$txt_Header"
							+ "*GEOM*TEXT SET "+ replaceOrdinalInText(matchAllData.getSetup().getMatchIdent()) + "\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section2$Side" + WhichSide + "$Toss$txt_Data1"
							+ "*GEOM*TEXT SET "+matchAllData.getSetup().getHomeTeam().getTeamName4() + " v " + 
							matchAllData.getSetup().getAwayTeam().getTeamName4() + "\0", print_writers);
					break;
				case "LIVE_FROM":
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section2$Side" + WhichSide + "$Select"
							+ "*FUNCTION*Omo*vis_con SET 0\0",print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section2$Side" + WhichSide + "$Toss$txt_Header"
							+ "*GEOM*TEXT SET FROM\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section2$Side" + WhichSide + "$Toss$txt_Data1"
							+ "*GEOM*TEXT SET DUBAI\0", print_writers);
					break;
				case "SUPER_OVER":
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section2$Side" + WhichSide + "$Select"
							+ "*FUNCTION*Omo*vis_con SET 0\0",print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section2$Side" + WhichSide + "$Toss$txt_Header"
							+ "*GEOM*TEXT SET SUPER\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section2$Side" + WhichSide + "$Toss$txt_Data1"
							+ "*GEOM*TEXT SET OVER\0", print_writers);
					break;
				case "TOSS":
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section2$Side" + WhichSide + "$Select"
							+ "*FUNCTION*Omo*vis_con SET 0\0",print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section2$Side" + WhichSide + "$Toss$txt_Header"
							+ "*GEOM*TEXT SET TOSS\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section2$Side" + WhichSide + "$Toss$txt_Data1"
							+ "*GEOM*TEXT SET " + (matchAllData.getSetup().getTossWinningTeam() == matchAllData.getSetup().getHomeTeamId() ? 
							matchAllData.getSetup().getHomeTeam().getTeamName4() : matchAllData.getSetup().getAwayTeam().getTeamName4()) + "\0", print_writers);
					break;
				case "TARGET":
					if(!matchAllData.getMatch().getInning().get(1).getIsCurrentInning().equalsIgnoreCase(CricketUtil.YES)&&
							!matchAllData.getMatch().getInning().get(3).getIsCurrentInning().equalsIgnoreCase(CricketUtil.YES)) {
						return "populateVizInfobarLeftBottom: Target available in 2nd inning only";
					}
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section2$Side" + WhichSide + "$Select"
							+ "*FUNCTION*Omo*vis_con SET 1\0",print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section2$Side" + WhichSide + "$Target"
							+ "$txt_Header*GEOM*TEXT SET TARGET\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section2$Side" + WhichSide + "$Target"
							+ "$txt_Data1*GEOM*TEXT SET " + CricketFunctions.GetTargetData(matchAllData).getTargetRuns() + "\0", print_writers);
					break;
				case "RRR":
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section2$Side" + WhichSide + "$Select"
							+ "*FUNCTION*Omo*vis_con SET 5\0",print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section2$Side" + WhichSide + "$ReqRunRate$txt_Header"
							+ "*GEOM*TEXT SET REQ. RR\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section2$Side" + WhichSide + "$ReqRunRate$txt_Data1"
							+ "*GEOM*TEXT SET " + CricketFunctions.generateRunRate(CricketFunctions.GetTargetData(matchAllData).getRemaningRuns(), 
									0, CricketFunctions.GetTargetData(matchAllData).getRemaningBall(), 2, matchAllData) + "\0", print_writers);				
					break;
				case "CURR_PARTNERSHIP": case "CRR":
					inning = matchAllData.getMatch().getInning().stream().filter(inn -> inn.getIsCurrentInning().equalsIgnoreCase(CricketUtil.YES)).findAny().orElse(null);
					if(inning == null) {
						return "populateVizInfobarMiddleSection: Inning returned is NULL";
					}
					
					switch(infobar.getSection2().toUpperCase()) {
					case "CRR":
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section2$Side" + WhichSide + "$Select"
								+ "*FUNCTION*Omo*vis_con SET 2\0",print_writers);
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section2$Side" + WhichSide + "$RunRate$txt_Header"
								+ "*GEOM*TEXT SET CRR\0", print_writers);
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section2$Side" + WhichSide + "$RunRate$txt_Data1"
								+ "*GEOM*TEXT SET " + CricketFunctions.generateRunRate(inning.getTotalRuns(),inning.getTotalOvers(), 
										inning.getTotalBalls(), 2,matchAllData) + "\0", print_writers);
						break;	
					case "CURR_PARTNERSHIP":
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section2$Side" + WhichSide + "$Select"
								+ "*FUNCTION*Omo*vis_con SET 3\0",print_writers);
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section2$Side" + WhichSide + "$Partnership$txt_Header"
								+ "*GEOM*TEXT SET PARTNERSHIP\0", print_writers);
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section2$Side" + WhichSide + "$Partnership$txt_Data1"
								+ "*GEOM*TEXT SET " + inning.getPartnerships().get(inning.getPartnerships().size()-1).getTotalRuns() + "*" + "\0", print_writers);
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section2$Side" + WhichSide + "$Partnership$txt_Data2"
								+ "*GEOM*TEXT SET " + inning.getPartnerships().get(inning.getPartnerships().size()-1).getTotalBalls() + "\0", print_writers);
						break;
					}
					break;
				}
			}
			break;
		case Constants.TRI_SERIES:  case Constants.MT20: case Constants.TG20:
			if ((!isNullOrEmpty(infobar.getLast_section2()) && !isNullOrEmpty(infobar.getSection2())) ||
			    (isNullOrEmpty(infobar.getLast_section2()) && isNullOrEmpty(infobar.getSection2()))) {
				populateBatterName(print_writers, matchAllData, 1, 1);
			} else {
				populateBatterName(print_writers, matchAllData, 1, 2);
			}
			
			switch(config.getBroadcaster()) {
			case Constants.TG20:
				if(infobar.getSection2() != null && !infobar.getSection2().isEmpty()) {
					switch(infobar.getSection2().toUpperCase()) {
					case "RRR":
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$LeftSection$Section2$Side" + WhichSide + "$select_DataType"
								+ "*FUNCTION*Omo*vis_con SET 0\0",print_writers);
						
						CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$LeftSection$Section2$Side" + WhichSide + "$Stats$select_Language"
								+ "*FUNCTION*Omo*vis_con SET ", config, Constants.TG20, print_writers, foreignLanguageOmo);
						foreignLanguageData = CricketFunctions.AssembleMultiLanguageData(CricketUtil.DICTIONARY, "", multilanguagedata, "RRR", "", null, 0, foreignLanguageDataList);
						CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$LeftSection$Section2$Side" + WhichSide + "$Stats$English"
								+ "$txt_StatHead*GEOM*TEXT SET ", config, Constants.TG20, print_writers, foreignLanguageData);
						
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$LeftSection$Section2$Side" + WhichSide + "$Stats"
								+ "$txt_StatValue*GEOM*TEXT SET " + CricketFunctions.generateRunRate(CricketFunctions.GetTargetData(matchAllData).
										getRemaningRuns(),0,CricketFunctions.GetTargetData(matchAllData).getRemaningBall(),2,matchAllData) + "\0", print_writers);
						break;
					case "PROJECTED_SCORE":
						inning = matchAllData.getMatch().getInning().stream().filter(inn -> inn.getInningNumber() == 1 &&
						inn.getIsCurrentInning().equalsIgnoreCase(CricketUtil.YES)).findAny().orElse(null);
				
						if(inning == null) {
							return "populateVizInfobarRightSection: 1st Inning returned is NULL";
						}
						this_data_str = CricketFunctions.projectedScore(matchAllData);
						
						if(this_data_str.size() <= 0) {
							return "populateVizInfobarRightSection: Projected score invalid";
						}
						
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$LeftSection$Section2$Side" + WhichSide + "$select_DataType"
								+ "*FUNCTION*Omo*vis_con SET 0\0",print_writers);
						
						CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$LeftSection$ExtraPopUp$Side" + WhichSide + "$select_Language"
								+ "*FUNCTION*Omo*vis_con SET ", config, Constants.TG20, print_writers, foreignLanguageOmo);
						foreignLanguageData = CricketFunctions.AssembleMultiLanguageData(CricketUtil.DICTIONARY, "", multilanguagedata, "PROJECTED", "", null, 0, 
								foreignLanguageDataList);
						CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$LeftSection$ExtraPopUp$Side" + WhichSide + "$English"
								+ "$txt_Info*GEOM*TEXT SET ", config, Constants.TG20, print_writers, foreignLanguageData);
						
						CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$LeftSection$Section2$Side" + WhichSide + "$Stats$select_Language"
								+ "*FUNCTION*Omo*vis_con SET ", config, Constants.TG20, print_writers, foreignLanguageOmo);
						foreignLanguageData = CricketFunctions.AssembleMultiLanguageData(CricketUtil.DICTIONARY, "", multilanguagedata, " @ CRR (" + this_data_str.get(0) + ")", "", 
								null, 0, foreignLanguageDataList);
						CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$LeftSection$Section2$Side" + WhichSide + "$Stats$English"
								+ "$txt_StatHead*GEOM*TEXT SET ", config, Constants.TG20, print_writers, foreignLanguageData);
						
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$LeftSection$Section2$Side" + WhichSide + "$Stats"
								+ "$txt_StatValue*GEOM*TEXT SET " + this_data_str.get(1) + "\0", print_writers);
						break;
					
					case "CURR_PARTNERSHIP": case "CRR":
						inning = matchAllData.getMatch().getInning().stream().filter(inn -> inn.getIsCurrentInning().equalsIgnoreCase(CricketUtil.YES)).findAny().orElse(null);
						if(inning == null) {
							return "populateVizInfobarMiddleSection: Inning returned is NULL";
						}
						
						switch(infobar.getSection2().toUpperCase()) {
						case "CRR":
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$LeftSection$Section2$Side" + WhichSide + "$select_DataType"
									+ "*FUNCTION*Omo*vis_con SET 0\0",print_writers);
							
							CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$LeftSection$Section2$Side" + WhichSide + "$Stats$select_Language"
									+ "*FUNCTION*Omo*vis_con SET ", config, Constants.TG20, print_writers, foreignLanguageOmo);
							foreignLanguageData = CricketFunctions.AssembleMultiLanguageData(CricketUtil.DICTIONARY, "", multilanguagedata, "CRR", "", null, 0, foreignLanguageDataList);
							CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$LeftSection$Section2$Side" + WhichSide + "$Stats$English"
									+ "$txt_StatHead*GEOM*TEXT SET ", config, Constants.TG20, print_writers, foreignLanguageData);
							
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$LeftSection$Section2$Side" + WhichSide + "$Stats"
									+ "$txt_StatValue*GEOM*TEXT SET " + CricketFunctions.generateRunRate(inning.getTotalRuns(),inning.getTotalOvers(), 
											inning.getTotalBalls(), 2,matchAllData) + "\0", print_writers);
							break;	
						case "CURR_PARTNERSHIP":
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$LeftSection$Section2$Side" + WhichSide + "$select_DataType"
									+ "*FUNCTION*Omo*vis_con SET 1\0",print_writers);
							
							CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$LeftSection$Section2$Side" + WhichSide + "$Partnership$select_Language"
									+ "*FUNCTION*Omo*vis_con SET ", config, Constants.TG20, print_writers, foreignLanguageOmo);
							foreignLanguageData = CricketFunctions.AssembleMultiLanguageData(CricketUtil.DICTIONARY, "", multilanguagedata, "CURR P'SHIP", "", null, 0, foreignLanguageDataList);
							CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$LeftSection$Section2$Side" + WhichSide + "$Partnership$English"
									+ "$txt_PartnershipHead*GEOM*TEXT SET ", config, Constants.TG20, print_writers, foreignLanguageData);
							
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$LeftSection$Section2$Side" + WhichSide + "$Partnership"
									+ "$txt_PartnershipRuns*GEOM*TEXT SET " + inning.getPartnerships().get(inning.getPartnerships().size()-1).getTotalRuns() + "*" + "\0", print_writers);
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$LeftSection$Section2$Side" + WhichSide + "$Partnership"
									+ "$txt_PartnershipBalls*GEOM*TEXT SET " + inning.getPartnerships().get(inning.getPartnerships().size()-1).getTotalBalls() + "\0", print_writers);
							break;
						}
						break;
					}
				}
				break;
			default:
				if(infobar.getSection2() != null && !infobar.getSection2().isEmpty()) {
					switch(infobar.getSection2().toUpperCase()) {
					case "RRR":
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$LeftSection$Section2$Side" + WhichSide + "$select_DataType"
								+ "*FUNCTION*Omo*vis_con SET 0\0",print_writers);
						
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$LeftSection$Section2$Side" + WhichSide + "$Stats"
								+ "$txt_StatHead*GEOM*TEXT SET RRR\0", print_writers);
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$LeftSection$Section2$Side" + WhichSide + "$Stats"
								+ "$txt_StatValue*GEOM*TEXT SET " + CricketFunctions.generateRunRate(CricketFunctions.GetTargetData(matchAllData).
										getRemaningRuns(),0,CricketFunctions.GetTargetData(matchAllData).getRemaningBall(),2,matchAllData) + "\0", print_writers);
						break;
					case "PROJECTED_SCORE":
						inning = matchAllData.getMatch().getInning().stream().filter(inn -> inn.getInningNumber() == 1 &&
						inn.getIsCurrentInning().equalsIgnoreCase(CricketUtil.YES)).findAny().orElse(null);
				
						if(inning == null) {
							return "populateVizInfobarRightSection: 1st Inning returned is NULL";
						}
						this_data_str = CricketFunctions.projectedScore(matchAllData);
						
						if(this_data_str.size() <= 0) {
							return "populateVizInfobarRightSection: Projected score invalid";
						}
						
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$LeftSection$Section2$Side" + WhichSide + "$select_DataType"
								+ "*FUNCTION*Omo*vis_con SET 0\0",print_writers);
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$LeftSection$ExtraPopUp$Side" + WhichSide + "$txt_Info"
								+ "*GEOM*TEXT SET PROJECTED\0", print_writers);
						
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$LeftSection$Section2$Side" + WhichSide + "$Stats"
								+ "$txt_StatHead*GEOM*TEXT SET @ CRR (" + this_data_str.get(0) + ")\0", print_writers);
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$LeftSection$Section2$Side" + WhichSide + "$Stats"
								+ "$txt_StatValue*GEOM*TEXT SET " + this_data_str.get(1) + "\0", print_writers);
						break;
					
					case "CURR_PARTNERSHIP": case "CRR":
						inning = matchAllData.getMatch().getInning().stream().filter(inn -> inn.getIsCurrentInning().equalsIgnoreCase(CricketUtil.YES)).findAny().orElse(null);
						if(inning == null) {
							return "populateVizInfobarMiddleSection: Inning returned is NULL";
						}
						
						switch(infobar.getSection2().toUpperCase()) {
						case "CRR":
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$LeftSection$Section2$Side" + WhichSide + "$select_DataType"
									+ "*FUNCTION*Omo*vis_con SET 0\0",print_writers);
							
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$LeftSection$Section2$Side" + WhichSide + "$Stats"
									+ "$txt_StatHead*GEOM*TEXT SET CRR\0", print_writers);
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$LeftSection$Section2$Side" + WhichSide + "$Stats"
									+ "$txt_StatValue*GEOM*TEXT SET " + CricketFunctions.generateRunRate(inning.getTotalRuns(),inning.getTotalOvers(), 
											inning.getTotalBalls(), 2,matchAllData) + "\0", print_writers);
							break;	
						case "CURR_PARTNERSHIP":
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$LeftSection$Section2$Side" + WhichSide + "$select_DataType"
									+ "*FUNCTION*Omo*vis_con SET 1\0",print_writers);
							
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$LeftSection$Section2$Side" + WhichSide + "$Partnership"
									+ "$txt_PartnershipHead*GEOM*TEXT SET CURR P'SHIP\0", print_writers);
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$LeftSection$Section2$Side" + WhichSide + "$Partnership"
									+ "$txt_PartnershipRuns*GEOM*TEXT SET " + inning.getPartnerships().get(inning.getPartnerships().size()-1).getTotalRuns() + "*" + "\0", print_writers);
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$LeftSection$Section2$Side" + WhichSide + "$Partnership"
									+ "$txt_PartnershipBalls*GEOM*TEXT SET " + inning.getPartnerships().get(inning.getPartnerships().size()-1).getTotalBalls() + "\0", print_writers);
							break;
						}
						break;
					}
				}
				break;
			}
			break;
		}
		return Constants.OK;
	}
	public String populateSection3(List<PrintWriter> print_writers, MatchAllData matchAllData,int WhichSide) throws InterruptedException, IOException {
		
		switch(config.getBroadcaster()) {
		case Constants.AFG_SL_SERIES:
			if(infobar.getSection3() != null && !infobar.getSection3().isEmpty()) {
				switch(infobar.getSection3().toUpperCase()) {
				case "BOWLER_REPLACE":
					isThisOverLimitExceed = true;
					int Replaced_Player_id = CricketFunctions.SecondLastBowlerId(matchAllData,bowlingCard.getPlayerId());
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$BowlingGrp$Section3$Side" + WhichSide 
							+ "$select_DataType*FUNCTION*Omo*vis_con SET 1\0", print_writers);

					if(Replaced_Player_id > 0) {
						player = Players.stream().filter(plyr -> plyr.getPlayerId() == Replaced_Player_id).findAny().orElse(null);
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$BowlingGrp$Section3$Side" + WhichSide 
								+ "$FreeText1$txt_Info*GEOM*TEXT SET REPLACES " + player.getFull_name() + "\0", print_writers);
					}
					break;
				case "BOWLING_END":
					isThisOverLimitExceed = true;
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$BowlingGrp$Section3$Side" + WhichSide 
							+ "$select_DataType*FUNCTION*Omo*vis_con SET 1\0", print_writers);
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$BowlingGrp$Section3$Side" + WhichSide 
							+ "$FreeText1$txt_Info*GEOM*TEXT SET BOWLING FROM " + (bowlingCard.getBowling_end() == 1 ? matchAllData.getSetup().getGround().
							getFirst_bowling_end() : matchAllData.getSetup().getGround().getSecond_bowling_end()) + " END" + "\0", print_writers);
					break;
				case "EXTRAS":
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$BowlingGrp$Section3$Side" + WhichSide 
							+ "$select_DataType*FUNCTION*Omo*vis_con SET 2\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$BowlingGrp$Section3$Side" + WhichSide 
							+ "$FreeText2$txt_StatHead*GEOM*TEXT SET EXTRAS\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$BowlingGrp$Section3$Side" + WhichSide 
							+ "$FreeText2$txt_StatValue*GEOM*TEXT SET " + inning.getTotalExtras() + "\0", print_writers);
					break;
				case "BOWLER_ECONOMY":
					isThisOverLimitExceed = true;
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$BowlingGrp$Section3$Side" + WhichSide 
							+ "$select_DataType*FUNCTION*Omo*vis_con SET 2\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$BowlingGrp$Section3$Side" + WhichSide 
							+ "$FreeText1$txt_Info*GEOM*TEXT SET ECONOMY : " + CricketFunctions.getEconomy(bowlingCard.getRuns(), 
									((bowlingCard.getOvers()*6)+bowlingCard.getBalls()), 2, "-") + "\0", print_writers);
					break;
				case CricketUtil.OVER:
					this_data_str = new ArrayList<String>();
					this_data_str.add(String.join(",", 
						    new ArrayList<>(Arrays.asList(IndexController.MatchStats.getOverData().getThisOverTxt().split(","))).stream()
					        .map(s -> s.replace("WIDE", "WD").replace("NO_BALL", "NB").replace("LEG_BYE", "LB").replace("BYE", "B")
					                   .replace("PENALTY", "PN").replace("LOG_WICKET", "W").replace("WICKET", "W"))
					        .collect(Collectors.collectingAndThen(Collectors.toList(), list -> {Collections.reverse(list); return list;}))
					        .toArray(new String[0])));
					
					int totalOverSize = 6;
					for(int i=1;i<=totalOverSize;i++) {
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$BowlingGrp$Section3$Side" + WhichSide + 
								"$ThisOver$select_BallNumber$Ball_" + i + "$Select_DataType*FUNCTION*Omo*vis_con SET 0\0", print_writers);
					}
					
					if(this_data_str.get(this_data_str.size()-1) == null) {
						return "populateSection3: This over data returned invalid";
					}
					if(this_data_str.get(this_data_str.size()-1).split(",").length <= 9) {
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$BowlingGrp$Section3$Side" + WhichSide 
								+ "$select_DataType*FUNCTION*Omo*vis_con SET 0\0", print_writers);
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$BowlingGrp$Section3$Side" + WhichSide 
								+ "$ThisOver$ThisOverDataAll$select_DataType*FUNCTION*Omo*vis_con SET 0\0", print_writers);
						
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$BowlingGrp$Section3$Side" + WhichSide 
								+ "$ThisOver$This_OverHead$select_ThisOverHead*FUNCTION*Omo*vis_con SET 1\0", print_writers);
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$BowlingGrp$Section3$Side" + WhichSide + 
				    			"$ThisOver$This_OverHead$txt_ThisOverHead*GEOM*TEXT SET THIS OVER\0", print_writers);
						
//						if(infobar.getSection4() != null && !infobar.getSection4().isEmpty()) {
//							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$BowlingGrp$Section3$Side" + WhichSide 
//									+ "$ThisOver$This_OverHead$select_ThisOverHead*FUNCTION*Omo*vis_con SET 0\0", print_writers);
//							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$BowlingGrp$Section3$Side" + WhichSide + 
//					    			"$ThisOver$This_OverHead$txt_ThisOverHead*GEOM*TEXT SET THIS OVER\0", print_writers);
//						}else {
//							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$BowlingGrp$Section3$Side" + WhichSide 
//									+ "$ThisOver$This_OverHead$select_ThisOverHead*FUNCTION*Omo*vis_con SET 1\0", print_writers);
//							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$BowlingGrp$Section3$Side" + WhichSide + 
//					    			"$ThisOver$This_OverHead$txt_ThisOverHead*GEOM*TEXT SET THIS OVER\0", print_writers);
//						}
						
						for(int iBall = 0; iBall < this_data_str.get(this_data_str.size()-1).split(",").length; iBall++) {
							//System.out.println(this_data_str.get(this_data_str.size()-1).split(",")[iBall].toUpperCase());
							switch (this_data_str.get(this_data_str.size()-1).split(",")[iBall].toUpperCase()) {
							case CricketUtil.DOT: case CricketUtil.ONE: case CricketUtil.TWO: case CricketUtil.THREE: case CricketUtil.FIVE: 
							case CricketUtil.FOUR:case CricketUtil.SIX: case "W":
								switch (this_data_str.get(this_data_str.size()-1).split(",")[iBall].toUpperCase()) {
								case "W":
									CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$BowlingGrp$Section3$Side" + WhichSide + 
											"$ThisOver$Ball_" + (iBall+1) + "$Select_DataType*FUNCTION*Omo*vis_con SET 3\0", print_writers);
									
									CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$BowlingGrp$Section3$Side" + WhichSide + 
											"$ThisOver$Ball_" + (iBall+1) + "$Wicket$select_LineNumber*FUNCTION*Omo*vis_con SET 1\0", print_writers);
									CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$BowlingGrp$Section3$Side" + WhichSide + 
											"$ThisOver$Ball_" + (iBall+1) + "$Wicket$1_Line$txt_Figures1*GEOM*TEXT SET W\0", print_writers);
									break;
								default:
									CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$BowlingGrp$Section3$Side" + WhichSide + 
											"$ThisOver$Ball_" + (iBall+1) + "$Select_DataType*FUNCTION*Omo*vis_con SET 1\0", print_writers);
									CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$BowlingGrp$Section3$Side" + WhichSide + 
											"$ThisOver$Ball_" + (iBall+1) + "$Runs$select_LineNumber*FUNCTION*Omo*vis_con SET 1\0", print_writers);
									if(this_data_str.get(this_data_str.size()-1).split(",")[iBall].contains("PN")) {
										CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$BowlingGrp$Section3$Side" + WhichSide + 
												"$ThisOver$Ball_" + (iBall+1) + "$Runs$1_Line$txt_Figures1*GEOM*TEXT SET 5PN\0", print_writers);
									}else {
										CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$BowlingGrp$Section3$Side" + WhichSide + 
												"$ThisOver$Ball_" + (iBall+1) + "$Runs$1_Line$txt_Figures1*GEOM*TEXT SET " + this_data_str.get(this_data_str.size()-1).
												split(",")[iBall] + "\0", print_writers);
									}
									break;
								}
								break;
							default:
								if(this_data_str.get(this_data_str.size()-1).split(",")[iBall].toUpperCase().contains("BOUNDARY")) {
									CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$BowlingGrp$Section3$Side" + WhichSide + 
											"$ThisOver$Ball_" + (iBall+1) + "$Select_DataType*FUNCTION*Omo*vis_con SET 2\0", print_writers);
									
									CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$BowlingGrp$Section3$Side" + WhichSide + 
											"$ThisOver$Ball_" + (iBall+1) + "$Boundaries$select_LineNumber*FUNCTION*Omo*vis_con SET 1\0", print_writers);
									CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$BowlingGrp$Section3$Side" + WhichSide + 
											"$ThisOver$Ball_" + (iBall+1) + "$Boundaries$1_Line$txt_Figures1*GEOM*TEXT SET " + this_data_str.get(this_data_str.size()-1).
											split(",")[iBall].replace("BOUNDARY", "")	 + "\0", print_writers);
									
								}else if(!this_data_str.get(this_data_str.size()-1).isEmpty()) {
									CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$BowlingGrp$Section3$Side" + WhichSide + 
											"$ThisOver$Ball_" + (iBall+1) + "$Select_DataType*FUNCTION*Omo*vis_con SET 1\0", print_writers);
									
									if(this_data_str.get(this_data_str.size()-1).split(",")[iBall].contains("+")) {
										String line1 = "",line2 = "";
										String[] parts = this_data_str.get(this_data_str.size()-1).split(",")[iBall].split("\\+");
										if (parts.length == 2) {
									        line1 = parts[0];
									        line2 = parts[1];
									    } else if (parts.length == 3) {
									        line1 = parts[0];
									        line2 = parts[1] + "+" + parts[2];
									    }
										
										CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$BowlingGrp$Section3$Side" + WhichSide + 
												"$ThisOver$Ball_" + (iBall+1) + "$Runs$select_LineNumber*FUNCTION*Omo*vis_con SET 2\0", print_writers);
										CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$BowlingGrp$Section3$Side" + WhichSide + 
												"$ThisOver$Ball_" + (iBall+1) + "$Runs$2_Line$txt_Figures1*GEOM*TEXT SET " + line1.toUpperCase() + "\0", print_writers);
										CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$BowlingGrp$Section3$Side" + WhichSide + 
												"$ThisOver$Ball_" + (iBall+1) + "$Runs$2_Line$txt_Figures2*GEOM*TEXT SET " + line2.toUpperCase() + "\0", print_writers);
									}else {
										CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$BowlingGrp$Section3$Side" + WhichSide + 
												"$ThisOver$Ball_" + (iBall+1) + "$Runs$select_LineNumber*FUNCTION*Omo*vis_con SET 1\0", print_writers);
										if(this_data_str.get(this_data_str.size()-1).split(",")[iBall].contains("PN")) {
											CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$BowlingGrp$Section3$Side" + WhichSide + 
													"$ThisOver$Ball_" + (iBall+1) + "$Runs$1_Line$txt_Figures1*GEOM*TEXT SET 5PN\0", print_writers);
										}else {
											CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$BowlingGrp$Section3$Side" + WhichSide + 
													"$ThisOver$Ball_" + (iBall+1) + "$Runs$1_Line$txt_Figures1*GEOM*TEXT SET " + this_data_str.get(this_data_str.size()-1).
													split(",")[iBall].toUpperCase() + "\0", print_writers);
										}
									}
									
									switch (this_data_str.get(this_data_str.size()-1).split(",")[iBall].toUpperCase()) {
									case "1B": case "2B": case "3B": case "4B": case "5B": case "6B":
									case "1LB": case "2LB": case "3LB": case "4LB": case "5LB": case "6LB":
										
										break;

									default:
										if(this_data_str.get(this_data_str.size()-1).split(",")[iBall].contains("NB") || 
												this_data_str.get(this_data_str.size()-1).split(",")[iBall].contains("WD")||
												this_data_str.get(this_data_str.size()-1).split(",")[iBall].contains("PN_Y")) {
											totalOverSize++;
										}
										break;
									}
								}
								break;
							}
						}
					}else {
						if(this_data_str.get(this_data_str.size()-1).split(",").length > 9 || isThisOverLimitExceed) {
							isThisOverLimitExceed = true;
							if(this_data_str.get(this_data_str.size()-1).split(",").length == 10) {
//								setThisOverAndLastOverData(print_writers, matchAllData, 2);
//								this_animation.processAnimation(Constants.FRONT, print_writers, "anim_Infobar$Section_3_Change", "START");
//								TimeUnit.MILLISECONDS.sleep(1000);
								
								setThisOverAndLastOverData(print_writers, matchAllData, 1);
//								TimeUnit.MILLISECONDS.sleep(500);
//								this_animation.processAnimation(Constants.FRONT, print_writers, "anim_Infobar$Section_3_Change", "SHOW 0.0");
							}else {
								setThisOverAndLastOverData(print_writers, matchAllData, 1);
							}
						}
					}
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$BowlingGrp$Section3$Side" + WhichSide + 
							"$ThisOver$select_BallNumber*FUNCTION*Omo*vis_con SET " + totalOverSize + "\0", print_writers);
					if(totalOverSize >= 6) {
						for(int i = totalOverSize;i<=9;i++) {
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$BowlingGrp$Section3$Side" + WhichSide + 
									"$ThisOver$select_BallNumber$Ball_" + i + "$Select_DataType*FUNCTION*Omo*vis_con SET 0\0", print_writers);
						}
					}
					infobar.setLast_this_over(this_data_str.get(this_data_str.size()-1));
					break;
				}
			}
			break;
		case Constants.ACC:
			if(infobar.getSection3() != null && !infobar.getSection3().isEmpty()) {
				switch(infobar.getSection3().toUpperCase()) {
				case CricketUtil.PROJECTED:
					inning = matchAllData.getMatch().getInning().stream().filter(inn -> inn.getInningNumber() == 1 &&
					inn.getIsCurrentInning().equalsIgnoreCase(CricketUtil.YES)).findAny().orElse(null);

					if(inning == null) {
						return "populateSectionAnalytics: 1st Inning returned is NULL";
					}
					this_data_str = CricketFunctions.projectedScore(matchAllData);
					
					if(this_data_str.size() <= 0) {
						return "populateSectionAnalytics: Projected score invalid";
					}
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section3$Side" + WhichSide + "$Select"
							+ "*FUNCTION*Omo*vis_con SET 3\0", print_writers);
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section3$Side" + WhichSide + "$ProjectedScore$"
							+ "txt_Head*GEOM*TEXT SET " + "PROJECTED SCORES" + "\0", print_writers);
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section3$Side" + WhichSide + "$ProjectedScore$Data1Grp$"
							+ "txt_Head2*GEOM*TEXT SET " + "@CRR (" + this_data_str.get(0) + ")" + "\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section3$Side" + WhichSide + "$ProjectedScore$Data2Grp$"
							+ "txt_Head3*GEOM*TEXT SET " + "@"+String.format("%.2f", Double.parseDouble(this_data_str.get(2))) + "\0", print_writers);
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section3$Side" + WhichSide + "$ProjectedScore$Data1Grp$"
							+ "txt_Data1*GEOM*TEXT SET " + this_data_str.get(1) + "\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section3$Side" + WhichSide + "$ProjectedScore$Data2Grp$"
							+ "txt_Data2*GEOM*TEXT SET " + this_data_str.get(3) + "\0", print_writers);
					break;
				case "COMPARE": case "EQUATION": case "PAR_SCORE":
					inning = matchAllData.getMatch().getInning().stream().filter(inn -> inn.getIsCurrentInning().equalsIgnoreCase(CricketUtil.YES)
							&& inn.getInningNumber() == 2).findAny().orElse(null);
						
					if(inning == null) {
						return "populateVizInfobarRightSection: 2nd Inning returned is NULL";
					}
					
					switch(infobar.getSection3().toUpperCase()) {
					case "PAR_SCORE":
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section3$Side" + WhichSide + "$Select"
								+ "*FUNCTION*Omo*vis_con SET 4\0", print_writers);
						
						this_data_str = new ArrayList<String>();
						for(int i = 0; i<= dls.size() -1;i++) {
							if(dls.get(i).getOver_left().split("\\.")[0].equalsIgnoreCase(String.valueOf(inning.getTotalOvers()))) {
								for(int j=0;j<6;j++) {
									if(inning.getTotalBalls() == j) {
										this_data_str.add(dls.get(i+j).getWkts_down());
										break;
									}
								}
								break;
							}
						}
						
						this_data_str.add(CricketFunctions.populateDls(matchAllData, CricketUtil.SHORT, Integer.valueOf(this_data_str.get(0))));
						
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section3$Side" + WhichSide + "$AtThisStage$txt_Head*GEOM*TEXT SET " 
								+ "DLS PAR SCORE" + "\0", print_writers);
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section3$Side" + WhichSide + "$AtThisStage$Data1Grp$txt_Head2*GEOM*TEXT SET " + "AFTER " 
								+ CricketFunctions.OverBalls(inning.getTotalOvers(), inning.getTotalBalls()) + (inning.getTotalOvers()==1 && inning.getTotalBalls()==0?" OVER":" OVERS") + "\0", print_writers);
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section3$Side" + WhichSide + "$AtThisStage$Data1Grp$Data*ACTIVE SET 1\0", print_writers);
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section3$Side" + WhichSide + "$AtThisStage$Data1Grp$"
								+ "img_Text2$txt_Data1*GEOM*TEXT SET " + this_data_str.get(0) + "\0", print_writers);
						break;
					case "COMPARE":
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section3$Side" + WhichSide + "$Select"
								+ "*FUNCTION*Omo*vis_con SET 4\0", print_writers);
						
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section3$Side" + WhichSide + "$AtThisStage$txt_Head*GEOM*TEXT SET " 
								+ "AFTER " + CricketFunctions.OverBalls(inning.getTotalOvers(), inning.getTotalBalls()) + " OVERS" + "\0", print_writers);
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section3$Side" + WhichSide + "$AtThisStage$Data1Grp$"
								+ "txt_Head2*GEOM*TEXT SET " + inning.getBowling_team().getTeamName4() + " WERE" + "\0", print_writers);
						
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section3$Side" + WhichSide + "$AtThisStage$Data1Grp$Data*ACTIVE SET 1\0", print_writers);
						
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section3$Side" + WhichSide + "$AtThisStage$Data1Grp$"
								+ "img_Text2$txt_Data1*GEOM*TEXT SET " + CricketFunctions.compareInningData(matchAllData, "-", 
										1, matchAllData.getEventFile().getEvents()) + "\0", print_writers);
						break;
					case "EQUATION":
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section3$Side" + WhichSide + "$Select"
								+ "*FUNCTION*Omo*vis_con SET 2\0", print_writers);
						
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section3$Side" + WhichSide + "$Equation$Top$"
								+ "txt_Head*GEOM*TEXT SET " + "NEED" + "\0", print_writers);
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section3$Side" + WhichSide + "$Equation$Top$Data1Grp$"
								+ "txt_Data1*GEOM*TEXT SET " + CricketFunctions.GetTargetData(matchAllData).getRemaningRuns() + "\0", print_writers);
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section3$Side" + WhichSide + "$Equation$Top$Data1Grp$txt_Head2"
								+ "*GEOM*TEXT SET " + "RUN" + CricketFunctions.Plural(CricketFunctions.GetTargetData(matchAllData).getRemaningRuns()).toUpperCase() + " \0", print_writers);
						
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section3$Side" + WhichSide + "$Equation$Bottom$"
								+ "txt_Head*GEOM*TEXT SET " + " FROM" + "\0", print_writers);
						
						switch (matchAllData.getSetup().getMatchType()) {
						case CricketUtil.ODI:
							
							if(CricketFunctions.GetTargetData(matchAllData).getRemaningBall() >= 100) {
								CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section3$Side" + WhichSide + "$Equation$Bottom$Data1Grp$"
										+ "txt_Data1*GEOM*TEXT SET " + CricketFunctions.OverBalls(0, CricketFunctions.GetTargetData(matchAllData).getRemaningBall()) + "\0", print_writers);
								CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section3$Side" + WhichSide + "$Equation$Bottom$Data1Grp$txt_Head2"
										+ "*GEOM*TEXT SET " + "OVERS" + "\0", print_writers);
								
							}else {
								CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section3$Side" + WhichSide + "$Equation$Bottom$Data1Grp$"
										+ "txt_Data1*GEOM*TEXT SET " + CricketFunctions.GetTargetData(matchAllData).getRemaningBall() + "\0", print_writers);
								CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section3$Side" + WhichSide + "$Equation$Bottom$Data1Grp$txt_Head2"
										+ "*GEOM*TEXT SET " + "BALL" + CricketFunctions.Plural(CricketFunctions.GetTargetData(matchAllData).getRemaningBall()).toUpperCase() + "\0", print_writers);
							}
							break;

						default:
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section3$Side" + WhichSide + "$Equation$Bottom$Data1Grp$"
									+ "txt_Data1*GEOM*TEXT SET " + CricketFunctions.GetTargetData(matchAllData).getRemaningBall() + "\0", print_writers);
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section3$Side" + WhichSide + "$Equation$Bottom$Data1Grp$txt_Head2"
									+ "*GEOM*TEXT SET " + "BALL" + CricketFunctions.Plural(CricketFunctions.GetTargetData(matchAllData).getRemaningBall()).toUpperCase() + "\0", print_writers);
							break;
						}
						break;
					}
					break;
				case "REVIEWS_REMAINING":
//					Review reviewRemaining = CricketFunctions.getReviewRemaining(matchAllData);
//					String[] parts = reviewRemaining.getReviewStatus().split(",");
					
					String text_to_return = "";
					int lineIndex1 = 1;
				    boolean found1 = false;
					BufferedReader br = new BufferedReader(new FileReader(CricketUtil.CRICKET_DIRECTORY + "ICC_Reviews.txt"));
				
				    while( (text_to_return = br.readLine()) != null) {
				        if(lineIndex1 == 1) {
				        	CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section3$Side" + WhichSide + "$InningBoundaries$Data1Grp$"
									+ "txt_Data1*GEOM*TEXT SET " + text_to_return.split(" ")[0] + "\0", print_writers);
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section3$Side" + WhichSide + "$InningBoundaries$Data2Grp$"
									+ "txt_Data2*GEOM*TEXT SET " + text_to_return.split(" ")[1]  + "\0", print_writers);
				            found1 = true;
				            break;
				        }
				        lineIndex1++;
				    }
				    if(!found1) {
				    	//System.out.println("Line Not There");
				    }
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section3$Side" + WhichSide + "$Select*FUNCTION*Omo*vis_con SET 0\0", print_writers);
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section3$Side" + WhichSide + "$InningBoundaries$"
							+ "txt_Head*GEOM*TEXT SET " + "REVIEWS REMAINING" + "\0", print_writers);
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section3$Side" + WhichSide + "$InningBoundaries$Data1Grp$"
							+ "txt_Head2*GEOM*TEXT SET " + matchAllData.getSetup().getHomeTeam().getTeamName4() + "\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section3$Side" + WhichSide + "$InningBoundaries$Data2Grp$"
							+ "txt_Head3*GEOM*TEXT SET " + matchAllData.getSetup().getAwayTeam().getTeamName4() + "\0", print_writers);
					
					break;
				case CricketUtil.DOT:
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section3$Side" + WhichSide + "$Select"
							+ "*FUNCTION*Omo*vis_con SET 8\0", print_writers);
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section3$Side" + WhichSide + "$DotBalls$"
							+ "txt_Head*GEOM*TEXT SET " + "DOTS THIS INNINGS" + "\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section3$Side" + WhichSide + "$DotBalls$Data1Grp$txt_Data1*GEOM*TEXT SET " 
							+ (inning.getInningNumber() == 1 ? IndexController.MatchStats.getHomeTeamScoreData().getTotalDots() : 
								IndexController.MatchStats.getAwayTeamScoreData().getTotalDots())  + "\0", print_writers);
					break;
				case CricketUtil.FOUR:
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section3$Side" + WhichSide + "$Select"
							+ "*FUNCTION*Omo*vis_con SET 7\0", print_writers);
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section3$Side" + WhichSide + "$TournamentFours$Data1Grp$"
							+ "img_Base2*ACTIVE SET 1\0", print_writers);
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section3$Side" + WhichSide + "$TournamentFours$"
							+ "txt_Head*GEOM*TEXT SET " + "FOURS THIS INNINGS" + "\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section3$Side" + WhichSide + "$TournamentFours$Data1Grp"
							+ "$txt_Data1*GEOM*TEXT SET " + inning.getTotalFours()  + "\0", print_writers);
					break;
				case CricketUtil.SIX:
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section3$Side" + WhichSide + "$Select"
							+ "*FUNCTION*Omo*vis_con SET 6\0", print_writers);
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section3$Side" + WhichSide + "$TournamentSixes$Data1Grp$"
							+ "img_Base2*ACTIVE SET 1\0", print_writers);
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section3$Side" + WhichSide + "$TournamentSixes$"
							+ "txt_Head*GEOM*TEXT SET " + "SIXES THIS INNINGS" + "\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section3$Side" + WhichSide + "$TournamentSixes$Data1Grp"
							+ "$txt_Data1*GEOM*TEXT SET " + inning.getTotalSixes()  + "\0", print_writers);
					break;
				case CricketUtil.BOUNDARY:
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section3$Side" + WhichSide + "$Select"
							+ "*FUNCTION*Omo*vis_con SET 0\0", print_writers);
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section3$Side" + WhichSide + "$InningBoundaries$"
							+ "txt_Head*GEOM*TEXT SET " + "INNINGS BOUNDARIES" + "\0", print_writers);
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section3$Side" + WhichSide + "$InningBoundaries$Data1Grp$"
							+ "txt_Head2*GEOM*TEXT SET " + "FOUR" + CricketFunctions.Plural(inning.getTotalFours()).toUpperCase() + "\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section3$Side" + WhichSide + "$InningBoundaries$Data2Grp$"
							+ "txt_Head3*GEOM*TEXT SET " + (inning.getTotalSixes() == 1 ? "SIX" : "SIXES")+ "\0", print_writers);
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section3$Side" + WhichSide + "$InningBoundaries$Data1Grp$"
							+ "txt_Data1*GEOM*TEXT SET " + inning.getTotalFours() + "\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section3$Side" + WhichSide + "$InningBoundaries$Data2Grp$"
							+ "txt_Data2*GEOM*TEXT SET " + inning.getTotalSixes() + "\0", print_writers);
					break;
				case "LASTXBALLS":
					this_data_str = new ArrayList<String>();
					this_data_str.add(CricketFunctions.getlastthirtyballsdata(matchAllData, slashOrDash, matchAllData.getEventFile().getEvents(), lastXballs));
					if(this_data_str.get(this_data_str.size()-1) == null || this_data_str.get(this_data_str.size()-1).split(slashOrDash).length > 4) {
						return "populateVizInfobarMiddleSection: Last " + lastXballs + " Balls data returned invalid";
					}
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section3$Side" + WhichSide + "$Select"
							+ "*FUNCTION*Omo*vis_con SET 1\0", print_writers);
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section3$Side" + WhichSide + "$LastXBalls$"
							+ "txt_Head*GEOM*TEXT SET " + "LAST " + lastXballs + " BALLS" + "\0", print_writers);
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section3$Side" + WhichSide + "$LastXBalls$Data1Grp$"
							+ "txt_Head2*GEOM*TEXT SET " + "RUN" + CricketFunctions.Plural(Integer.valueOf(this_data_str.
									get(this_data_str.size()-1).split(slashOrDash)[0])).toUpperCase() + "\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section3$Side" + WhichSide + "$LastXBalls$Data2Grp$"
							+ "txt_Head3*GEOM*TEXT SET " + "WICKET" + CricketFunctions.Plural(Integer.valueOf(this_data_str.
									get(this_data_str.size()-1).split(slashOrDash)[1])).toUpperCase()+ "\0", print_writers);
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section3$Side" + WhichSide + "$LastXBalls$Data1Grp$"
							+ "txt_Data1*GEOM*TEXT SET " + this_data_str.get(this_data_str.size()-1).split(slashOrDash)[0] + "\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section3$Side" + WhichSide + "$LastXBalls$Data2Grp$"
							+ "txt_Data2*GEOM*TEXT SET " + this_data_str.get(this_data_str.size()-1).split(slashOrDash)[1] + "\0", print_writers);
										
					break;
				case "EXTRAS":
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section3$Side" + WhichSide + "$Select"
							+ "*FUNCTION*Omo*vis_con SET 13\0", print_writers);
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section3$Side" + WhichSide + "$Extras$txt_Head"
							+ "*GEOM*TEXT SET " + "EXTRAS - " + inning.getTotalExtras() + "\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section3$Side" + WhichSide + "$Extras$DataAll$txt_Data3"
							+ "*GEOM*TEXT SET " + "WD " + inning.getTotalWides() + " | NB " + inning.getTotalNoBalls() + " | LB " + inning.getTotalLegByes() 
							+ " | B " + inning.getTotalByes() + (inning.getTotalPenalties() > 0 ? " | PN " + inning.getTotalPenalties() : "") + "\0", print_writers);
					break;
				case "LAST_WICKET":
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section3$Side" + WhichSide + "$Select"
							+ "*FUNCTION*Omo*vis_con SET 5\0", print_writers);
					
					if(inning.getFallsOfWickets() == null && inning.getFallsOfWickets().isEmpty()) {
						return "populateVizInfobarSection5: FoW returned is EMPTY";
					}
					battingCard = inning.getBattingCard().stream().filter(bc -> bc.getPlayerId() == 
							inning.getFallsOfWickets().get(inning.getFallsOfWickets().size() - 1).getFowPlayerID()).findAny().orElse(null);
					
					if(battingCard == null) {
						return "populateVizInfobarSection5: Last wicket returned is invalid";
					}
					
					String how_out_txt = "";
					if(battingCard.getHowOut().equalsIgnoreCase(CricketUtil.RUN_OUT)) {
						if(battingCard.getWasHowOutFielderSubstitute() != null && battingCard.getWasHowOutFielderSubstitute().equalsIgnoreCase(CricketUtil.YES)) {
							how_out_txt = "run out " + "(sub " + battingCard.getHowOutFielder().getTicker_name() + ")";
						} else {
							how_out_txt = "run out (" + battingCard.getHowOutFielder().getTicker_name() + ")";
						}
					}
					else if(battingCard.getHowOut().equalsIgnoreCase(CricketUtil.CAUGHT)) {
						if(battingCard.getWasHowOutFielderSubstitute() != null && battingCard.getWasHowOutFielderSubstitute().equalsIgnoreCase(CricketUtil.YES)) {
							how_out_txt = "c " +  "(sub - " + battingCard.getHowOutFielder().getTicker_name() + ")  b " + battingCard.getHowOutBowler().getTicker_name();
						} else {
							how_out_txt = "c " + battingCard.getHowOutFielder().getTicker_name() + "  b " + battingCard.getHowOutBowler().getTicker_name();
						}
					}
					else if(battingCard.getHowOut().equalsIgnoreCase(CricketUtil.STUMPED)) {
						if(battingCard.getWasHowOutFielderSubstitute() != null && battingCard.getWasHowOutFielderSubstitute().equalsIgnoreCase(CricketUtil.YES)) {
							how_out_txt = "st " +  "(sub - " + battingCard.getHowOutFielder().getTicker_name() + ")  b " + battingCard.getHowOutBowler().getTicker_name();
						} else {
							how_out_txt = "st " + battingCard.getHowOutFielder().getTicker_name() + "  b " + battingCard.getHowOutBowler().getTicker_name();
						}
					}
					else {
						if(!battingCard.getHowOutPartOne().isEmpty()) {
							how_out_txt = battingCard.getHowOutPartOne();
						}
						if(!battingCard.getHowOutPartTwo().isEmpty()) {
							if(!how_out_txt.trim().isEmpty()) {
								how_out_txt = how_out_txt + "  " + battingCard.getHowOutPartTwo();
							}else {
								how_out_txt = battingCard.getHowOutPartTwo();
							}
						}
					}
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section3$Side" + WhichSide + "$LastWicket$"
							+ "txt_Head*GEOM*TEXT SET " + "LAST WICKET" + "\0", print_writers);
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section3$Side" + WhichSide + "$LastWicket$Data1Grp$"
							+ "txt_Head2*GEOM*TEXT SET " + battingCard.getPlayer().getTicker_name() + "\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section3$Side" + WhichSide + "$LastWicket$Data1Grp$"
							+ "Data$img_Text2$txt_Data1*GEOM*TEXT SET " + battingCard.getRuns() + "\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section3$Side" + WhichSide + "$LastWicket$Data1Grp$"
							+ "Data$img_Text2$txt_Data2*GEOM*TEXT SET " + battingCard.getBalls() + "\0", print_writers);
					break;
					
				case "BALLS_SINCE_LAST_BOUNDARY":
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section3$Side" + WhichSide + "$Select"
							+ "*FUNCTION*Omo*vis_con SET 9\0", print_writers);
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section3$Side" + WhichSide + "$BallsSince$"
							+ "txt_Head*GEOM*TEXT SET " + "BALLS SINCE LAST BOUNDARY" + "\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section3$Side" + WhichSide + "$BallsSince$Data1Grp$"
							+ "Data$img_Text2$txt_Data1*GEOM*TEXT SET " + IndexController.MatchStats.getBallsSinceLastBoundary() + "\0", print_writers);
					break;	
					
				case "BOWLER_REPLACE":
					isThisOverLimitExceed = true;
					int Replaced_Player_id = CricketFunctions.SecondLastBowlerId(matchAllData,bowlingCard.getPlayerId());
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section3$Side" + WhichSide 
							+ "$Select*FUNCTION*Omo*vis_con SET 10\0", print_writers);

					if(Replaced_Player_id > 0) {
						player = Players.stream().filter(plyr -> plyr.getPlayerId() == Replaced_Player_id).findAny().orElse(null);
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section3$Side" + WhichSide 
								+ "$FreeText$txt_Head*GEOM*TEXT SET REPLACES " + player.getFull_name() + "\0", print_writers);
					}
					break;
				case "BOWLING_END":
					isThisOverLimitExceed = true;
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section3$Side" + WhichSide 
							+ "$Select*FUNCTION*Omo*vis_con SET 12\0", print_writers);
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section3$Side" + WhichSide 
							+ "$BowlingEnd$txt_Head*GEOM*TEXT SET BOWLING FROM " + (bowlingCard.getBowling_end() == 1 ? matchAllData.getSetup().getGround().
							getFirst_bowling_end() : matchAllData.getSetup().getGround().getSecond_bowling_end()) + " END" + "\0", print_writers);
					
					break;
				case "BOWLER_ECONOMY":
					isThisOverLimitExceed = true;
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section3$Side" + WhichSide 
							+ "$Select*FUNCTION*Omo*vis_con SET 12\0", print_writers);
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section3$Side" + WhichSide 
							+ "$BowlingEnd$txt_Head*GEOM*TEXT SET ECONOMY : " + CricketFunctions.getEconomy(bowlingCard.getRuns(), 
									((bowlingCard.getOvers()*6)+bowlingCard.getBalls()), 2, "-") + "\0", print_writers);
					break;
				case CricketUtil.OVER:
					this_data_str = new ArrayList<String>();
					this_data_str.add(String.join(",", 
						    new ArrayList<>(Arrays.asList(IndexController.MatchStats.getOverData().getThisOverTxt().split(","))).stream()
					        .map(s -> s.replace("WIDE", "WD").replace("NO_BALL", "NB").replace("LEG_BYE", "LB").replace("BYE", "B")
					                   .replace("PENALTY", "PN").replace("LOG_WICKET", "W").replace("WICKET", "W"))
					        .collect(Collectors.collectingAndThen(Collectors.toList(), list -> {Collections.reverse(list); return list;}))
					        .toArray(new String[0])));
					
					int totalOverSize = 6;
					for(int i=1;i<=10;i++) {
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section3$Side" + WhichSide + "$This_Over$ThisOver$BallGrp" 
								+ i + "$SelectType*FUNCTION*Omo*vis_con SET 0\0", print_writers);
					}
					
					if(this_data_str.get(this_data_str.size()-1) == null) {
						return "populateSection5: This over data returned invalid";
					}
					if(this_data_str.get(this_data_str.size()-1).split(",").length <= 6) {
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section3$Side" + WhichSide + "$Select"
								+ "*FUNCTION*Omo*vis_con SET 11\0", print_writers);
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section3$Side" + WhichSide + "$This_Over$txt_Head*GEOM*TEXT SET " 
								+ "THIS OVER" + "\0", print_writers);
						
						for(int iBall = 0; iBall < this_data_str.get(this_data_str.size()-1).split(",").length; iBall++) {
							//System.out.println(this_data_str.get(this_data_str.size()-1).split(",")[iBall].toUpperCase());
							switch (this_data_str.get(this_data_str.size()-1).split(",")[iBall].toUpperCase()) {
							case CricketUtil.DOT: case CricketUtil.ONE: case CricketUtil.TWO: case CricketUtil.THREE: case CricketUtil.FIVE: 
							case CricketUtil.FOUR:case CricketUtil.SIX: case "W":
								switch (this_data_str.get(this_data_str.size()-1).split(",")[iBall].toUpperCase()) {
								case CricketUtil.DOT:
									CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section3$Side" + WhichSide + "$This_Over$ThisOver$BallGrp" 
											+ (iBall+1) + "$SelectType*FUNCTION*Omo*vis_con SET 1\0", print_writers);
									break;
								case "W":
									CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section3$Side" + WhichSide + "$This_Over$ThisOver$BallGrp" 
											+ (iBall+1) + "$SelectType*FUNCTION*Omo*vis_con SET 3\0", print_writers);
									CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section3$Side" + WhichSide + "$This_Over$ThisOver$BallGrp" 
											+ (iBall+1) + "$Four_Six_WKT$txt_Ball*GEOM*TEXT SET W\0", print_writers);
									break;
								default:
									CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section3$Side" + WhichSide + "$This_Over$ThisOver$BallGrp" 
											+ (iBall+1) + "$SelectType*FUNCTION*Omo*vis_con SET 2\0", print_writers);
									
									if(this_data_str.get(this_data_str.size()-1).split(",")[iBall].contains("PN")) {
										CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section3$Side" + WhichSide + "$This_Over$ThisOver$BallGrp" 
												+ (iBall+1) + "$Rest$txt_Ball*GEOM*TEXT SET 5PN\0", print_writers);
									}else {
										CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section3$Side" + WhichSide + "$This_Over$ThisOver$BallGrp" 
												+ (iBall+1) + "$Rest$txt_Ball*GEOM*TEXT SET " + this_data_str.get(this_data_str.size()-1).split(",")[iBall] + "\0", print_writers);
									}
									break;
								}
								break;
							default:
								if(this_data_str.get(this_data_str.size()-1).split(",")[iBall].toUpperCase().contains("BOUNDARY")) {
									CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section3$Side" + WhichSide + "$This_Over$ThisOver$BallGrp" 
											+ (iBall+1) + "$SelectType*FUNCTION*Omo*vis_con SET 3\0", print_writers);
									CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section3$Side" + WhichSide + "$This_Over$ThisOver$BallGrp" 
											+ (iBall+1) + "$Four_Six_WKT$txt_Ball*GEOM*TEXT SET " + this_data_str.get(this_data_str.size()-1).split(",")[iBall].replace("BOUNDARY", "") + "\0", print_writers);
									
								}else if(!this_data_str.get(this_data_str.size()-1).isEmpty()) {
									CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section3$Side" + WhichSide + "$This_Over$ThisOver$BallGrp" 
											+ (iBall+1) + "$SelectType*FUNCTION*Omo*vis_con SET 2\0", print_writers);
									if(this_data_str.get(this_data_str.size()-1).split(",")[iBall].contains("+")) {
										String line1 = "",line2 = "";
										String[] parts = this_data_str.get(this_data_str.size()-1).split(",")[iBall].split("\\+");
										if (parts.length == 2) {
									        line1 = parts[0];
									        line2 = parts[1];
									    } else if (parts.length == 3) {
									        line1 = parts[0];
									        line2 = parts[1] + "+" + parts[2];
									    }
										
										CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section3$Side" + WhichSide + "$This_Over$ThisOver$BallGrp" 
												+ (iBall+1) + "$Rest$txt_Ball*GEOM*TEXT SET " + line1.toUpperCase() + "+" + line2.toUpperCase() + "\0", print_writers);
									}else {
										if(this_data_str.get(this_data_str.size()-1).split(",")[iBall].contains("PN")) {
											CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section3$Side" + WhichSide + "$This_Over$ThisOver$BallGrp" 
													+ (iBall+1) + "$Rest$txt_Ball*GEOM*TEXT SET 5PN\0", print_writers);
										}else {
											CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section3$Side" + WhichSide + "$This_Over$ThisOver$BallGrp" 
													+ (iBall+1) + "$Rest$txt_Ball*GEOM*TEXT SET " + this_data_str.get(this_data_str.size()-1).split(",")[iBall] + "\0", print_writers);
										}
									}
									
									switch (this_data_str.get(this_data_str.size()-1).split(",")[iBall].toUpperCase()) {
									case "1B": case "2B": case "3B": case "4B": case "5B": case "6B":
									case "1LB": case "2LB": case "3LB": case "4LB": case "5LB": case "6LB":
										
										break;

									default:
										if(this_data_str.get(this_data_str.size()-1).split(",")[iBall].contains("NB") || 
												this_data_str.get(this_data_str.size()-1).split(",")[iBall].contains("WD")||
												this_data_str.get(this_data_str.size()-1).split(",")[iBall].contains("PN_Y")) {
											totalOverSize++;
										}
										break;
									}
								}
								break;
							}
						}
					}else {
						if(this_data_str.get(this_data_str.size()-1).split(",").length > 6 || isThisOverLimitExceed) {
							isThisOverLimitExceed = true;
							if(this_data_str.get(this_data_str.size()-1).split(",").length == 7) {
//								setThisOverAndLastOverData(print_writers, matchAllData, 2);
//								this_animation.processAnimation(Constants.FRONT, print_writers, "anim_Infobar$Section_3_Change", "START");
//								TimeUnit.MILLISECONDS.sleep(1000);
								
								setThisOverAndLastOverData(print_writers, matchAllData, 1);
//								TimeUnit.MILLISECONDS.sleep(500);
//								this_animation.processAnimation(Constants.FRONT, print_writers, "anim_Infobar$Section_3_Change", "SHOW 0.0");
							}else {
								setThisOverAndLastOverData(print_writers, matchAllData, 1);
							}
						}
					}
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section3$Side" + WhichSide + "$This_Over$ThisOverAll*FUNCTION*Omo*vis_con SET " 
							+ totalOverSize + "\0", print_writers);
					if(totalOverSize >= 6) {
						for(int i = totalOverSize+1;i<=10;i++) {
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section3$Side" + WhichSide + "$This_Over$ThisOver$BallGrp" 
									+ i + "$SelectType*FUNCTION*Omo*vis_con SET 0\0", print_writers);
						}
					}
					infobar.setLast_this_over(this_data_str.get(this_data_str.size()-1));
					break;
				}
			}
			break;
		case Constants.TRI_SERIES:  case Constants.MT20: case Constants.TG20:
			if(infobar.getSection3() != null && !infobar.getSection3().isEmpty()) {
				switch(infobar.getSection3().toUpperCase()) {
				case "BOWLER_REPLACE":
					isThisOverLimitExceed = true;
					int Replaced_Player_id = CricketFunctions.SecondLastBowlerId(matchAllData,bowlingCard.getPlayerId());
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$RightSection$Section3$Side" + WhichSide 
							+ "$select_DataType*FUNCTION*Omo*vis_con SET 2\0", print_writers);

					if(Replaced_Player_id > 0) {
						player = Players.stream().filter(plyr -> plyr.getPlayerId() == Replaced_Player_id).findAny().orElse(null);
						CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$RightSection$Section3$Side" + WhichSide + "$FreeText"
								+ "$select_Language*FUNCTION*Omo*vis_con SET ", config, Constants.TG20, print_writers, foreignLanguageOmo);
						foreignLanguageDataList = CricketFunctions.AssembleMultiLanguageData(CricketUtil.DICTIONARY, "", multilanguagedata, "REPLACES", "", null, 1,foreignLanguageDataList);
						foreignLanguageDataList = CricketFunctions.AssembleMultiLanguageData(CricketUtil.PLAYER, CricketUtil.FULLNAME, multilanguagedata, player.getFull_name(), "", null, 
								2, foreignLanguageDataList);
						foreignLanguageData.add(CricketFunctions.MergeForeignLanguageDataListToSingleObject(foreignLanguageDataList));
						CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$RightSection$Section3$Side" + WhichSide + "$FreeText$English"
								+ "$txt_Text*GEOM*TEXT SET ", config, Constants.TG20, print_writers, foreignLanguageData);
					}
					break;
				case "BOWLING_END":
					isThisOverLimitExceed = true;
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$RightSection$Section3$Side" + WhichSide 
							+ "$select_DataType*FUNCTION*Omo*vis_con SET 2\0", print_writers);
					CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$RightSection$Section3$Side" + WhichSide + "$FreeText"
							+ "$select_Language*FUNCTION*Omo*vis_con SET ", config, Constants.TG20, print_writers, foreignLanguageOmo);
					
					foreignLanguageDataList = CricketFunctions.AssembleMultiLanguageData(CricketUtil.DICTIONARY, "", multilanguagedata, "BOWLING FROM", "", null, 1,foreignLanguageDataList);
					foreignLanguageDataList = CricketFunctions.AssembleMultiLanguageData(CricketUtil.DICTIONARY, "", multilanguagedata,(bowlingCard.getBowling_end() == 1 ? 
							matchAllData.getSetup().getGround().getFirst_bowling_end() : matchAllData.getSetup().getGround().getSecond_bowling_end()), "", null, 2, foreignLanguageDataList);

					foreignLanguageData.add(CricketFunctions.MergeForeignLanguageDataListToSingleObject(foreignLanguageDataList));
					CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$RightSection$Section3$Side" + WhichSide + "$FreeText$English"
							+ "$txt_Text*GEOM*TEXT SET ", config, Constants.TG20, print_writers, foreignLanguageData);
					
					break;
				case "EXTRAS":
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$RightSection$Section3$Side" + WhichSide 
							+ "$select_DataType*FUNCTION*Omo*vis_con SET 1\0", print_writers);
					
					CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$RightSection$Section3$Side" + WhichSide + "$Extras"
							+ "$select_Language*FUNCTION*Omo*vis_con SET ", config, Constants.TG20, print_writers, foreignLanguageOmo);
					foreignLanguageData = CricketFunctions.AssembleMultiLanguageData(CricketUtil.DICTIONARY, "", multilanguagedata, "EXTRAS", "", null, 0, foreignLanguageDataList);
					CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$RightSection$Section3$Side" + WhichSide + "$Extras$English"
							+ "$txt_Head*GEOM*TEXT SET ", config, Constants.TG20, print_writers, foreignLanguageData);
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$RightSection$Section3$Side" + WhichSide 
							+ "$Extras$txt_Value*GEOM*TEXT SET " + inning.getTotalExtras() + "\0", print_writers);
					break;
				case "BOWLER_ECONOMY":
					isThisOverLimitExceed = true;
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$RightSection$Section3$Side" + WhichSide 
							+ "$select_DataType*FUNCTION*Omo*vis_con SET 2\0", print_writers);
					
					CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$RightSection$Section3$Side" + WhichSide + "$FreeText"
							+ "$select_Language*FUNCTION*Omo*vis_con SET ", config, Constants.TG20, print_writers, foreignLanguageOmo);
					foreignLanguageDataList = CricketFunctions.AssembleMultiLanguageData(CricketUtil.DICTIONARY, "", multilanguagedata, "ECONOMY", "", null, 1,foreignLanguageDataList);
					foreignLanguageDataList = CricketFunctions.AssembleMultiLanguageData(CricketUtil.DICTIONARY, "", multilanguagedata, ": ", "", null, 2,foreignLanguageDataList);
					foreignLanguageDataList = CricketFunctions.AssembleMultiLanguageData(CricketUtil.DICTIONARY, "", multilanguagedata, CricketFunctions.getEconomy(bowlingCard.getRuns(), 
							((bowlingCard.getOvers()*6)+bowlingCard.getBalls()), 2, "-"), "", null, 3, foreignLanguageDataList);
					foreignLanguageData.add(CricketFunctions.MergeForeignLanguageDataListToSingleObject(foreignLanguageDataList));
					CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$RightSection$Section3$Side" + WhichSide + "$FreeText$English"
							+ "$txt_Text*GEOM*TEXT SET ", config, Constants.TG20, print_writers, foreignLanguageData);
					break;
				case CricketUtil.OVER:
					this_data_str = new ArrayList<String>();
					this_data_str.add(String.join(",", 
						    new ArrayList<>(Arrays.asList(IndexController.MatchStats.getOverData().getThisOverTxt().split(","))).stream()
					        .map(s -> s.replace("WIDE", "WD").replace("NO_BALL", "NB").replace("LEG_BYE", "LB").replace("BYE", "B")
					                   .replace("PENALTY", "PN").replace("LOG_WICKET", "W").replace("WICKET", "W"))
					        .collect(Collectors.collectingAndThen(Collectors.toList(), list -> {Collections.reverse(list); return list;}))
					        .toArray(new String[0])));
					
					int totalOverSize = 6;
					for(int i=1;i<=totalOverSize;i++) {
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$RightSection$Section3$Side" + WhichSide + 
								"$ThisOver$select_BallNumber$Ball_" + i + "$Select_DataType*FUNCTION*Omo*vis_con SET 0\0", print_writers);
					}
					
					if(this_data_str.get(this_data_str.size()-1) == null) {
						return "populateSection3: This over data returned invalid";
					}
					if(this_data_str.get(this_data_str.size()-1).split(",").length <= 9) {
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$RightSection$Section3$Side" + WhichSide 
								+ "$select_DataType*FUNCTION*Omo*vis_con SET 0\0", print_writers);
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$RightSection$Section3$Side" + WhichSide 
								+ "$ThisOver$ThisOverDataAll$select_DataType*FUNCTION*Omo*vis_con SET 0\0", print_writers);
						
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$RightSection$Section3$Side" + WhichSide 
								+ "$ThisOver$This_OverHead$select_ThisOverHead*FUNCTION*Omo*vis_con SET 1\0", print_writers);
						switch(config.getBroadcaster()) {
						case Constants.TG20:
//							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$RightSection$Section3$Side" + WhichSide + 
//					    			"$ThisOver$This_OverHead$txt_ThisOverHead*GEOM*TEXT SET THIS OVER\0", print_writers);
							
							CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$RightSection$Section3$Side" + WhichSide + "$ThisOver$This_OverHead"
									+ "$select_Language*FUNCTION*Omo*vis_con SET ", config, Constants.TG20, print_writers, foreignLanguageOmo);
							foreignLanguageData = CricketFunctions.AssembleMultiLanguageData(CricketUtil.DICTIONARY, "", multilanguagedata, "THIS OVER", "", null, 0, foreignLanguageDataList);
							CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$RightSection$Section3$Side" + WhichSide + "$ThisOver$This_OverHead$English"
									+ "$txt_ThisOverHead*GEOM*TEXT SET ", config, Constants.TG20, print_writers, foreignLanguageData);
							
							break;
						default:
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$RightSection$Section3$Side" + WhichSide + 
					    			"$ThisOver$This_OverHead$txt_ThisOverHead*GEOM*TEXT SET THIS OVER\0", print_writers);
							break;
						}
						
						
						if(infobar.getSection4() != null && !infobar.getSection4().isEmpty()) {
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$RightSection$Section3$Side" + WhichSide 
									+ "$ThisOver$This_OverHead$select_ThisOverHead*FUNCTION*Omo*vis_con SET 0\0", print_writers);
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$RightSection$Section3$Side" + WhichSide + 
					    			"$ThisOver$This_OverHead$txt_ThisOverHead*GEOM*TEXT SET THIS OVER\0", print_writers);
						}else {
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$RightSection$Section3$Side" + WhichSide 
									+ "$ThisOver$This_OverHead$select_ThisOverHead*FUNCTION*Omo*vis_con SET 1\0", print_writers);
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$RightSection$Section3$Side" + WhichSide + 
					    			"$ThisOver$This_OverHead$txt_ThisOverHead*GEOM*TEXT SET THIS OVER\0", print_writers);
						}
						
						for(int iBall = 0; iBall < this_data_str.get(this_data_str.size()-1).split(",").length; iBall++) {
							//System.out.println(this_data_str.get(this_data_str.size()-1).split(",")[iBall].toUpperCase());
							switch (this_data_str.get(this_data_str.size()-1).split(",")[iBall].toUpperCase()) {
							case CricketUtil.DOT: case CricketUtil.ONE: case CricketUtil.TWO: case CricketUtil.THREE: case CricketUtil.FIVE: 
							case CricketUtil.FOUR:case CricketUtil.SIX: case "W":
								switch (this_data_str.get(this_data_str.size()-1).split(",")[iBall].toUpperCase()) {
								case "W":
									CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$RightSection$Section3$Side" + WhichSide + 
											"$ThisOver$Ball_" + (iBall+1) + "$Select_DataType*FUNCTION*Omo*vis_con SET 3\0", print_writers);
									CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$RightSection$Section3$Side" + WhichSide + 
											"$ThisOver$Ball_" + (iBall + 1) + "$Wicket$img_Base2*TEXTURE*IMAGE SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_BASE2 : Constants.MT20_BASE2) + 
											inning.getBowling_team().getTeamBadge() + "\0", print_writers);
									CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$RightSection$Section3$Side" + WhichSide + 
											"$ThisOver$Ball_" + (iBall + 1) + "$Wicket$img_Text2*TEXTURE*IMAGE SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_TEXT2 : Constants.MT20_TEXT2) + 
											inning.getBowling_team().getTeamBadge() + "\0", print_writers);
									
									CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$RightSection$Section3$Side" + WhichSide + 
											"$ThisOver$Ball_" + (iBall+1) + "$Wicket$select_LineNumber*FUNCTION*Omo*vis_con SET 1\0", print_writers);
									CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$RightSection$Section3$Side" + WhichSide + 
											"$ThisOver$Ball_" + (iBall+1) + "$Wicket$1_Line$txt_Figures1*GEOM*TEXT SET W\0", print_writers);
									break;
								default:
									CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$RightSection$Section3$Side" + WhichSide + 
											"$ThisOver$Ball_" + (iBall+1) + "$Select_DataType*FUNCTION*Omo*vis_con SET 1\0", print_writers);
									CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$RightSection$Section3$Side" + WhichSide + 
											"$ThisOver$Ball_" + (iBall+1) + "$Runs$select_LineNumber*FUNCTION*Omo*vis_con SET 1\0", print_writers);
									if(this_data_str.get(this_data_str.size()-1).split(",")[iBall].contains("PN")) {
										CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$RightSection$Section3$Side" + WhichSide + 
												"$ThisOver$Ball_" + (iBall+1) + "$Runs$1_Line$txt_Figures1*GEOM*TEXT SET 5PN\0", print_writers);
									}else {
										CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$RightSection$Section3$Side" + WhichSide + 
												"$ThisOver$Ball_" + (iBall+1) + "$Runs$1_Line$txt_Figures1*GEOM*TEXT SET " + this_data_str.get(this_data_str.size()-1).
												split(",")[iBall] + "\0", print_writers);
									}
									break;
								}
								break;
							default:
								if(this_data_str.get(this_data_str.size()-1).split(",")[iBall].toUpperCase().contains("BOUNDARY")) {
									CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$RightSection$Section3$Side" + WhichSide + 
											"$ThisOver$Ball_" + (iBall+1) + "$Select_DataType*FUNCTION*Omo*vis_con SET 2\0", print_writers);
									
									CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$RightSection$Section3$Side" + WhichSide + 
											"$ThisOver$Ball_" + (iBall + 1) + "$Boundaries$img_Base2*TEXTURE*IMAGE SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_BASE2 : Constants.MT20_BASE2) + 
											inning.getBatting_team().getTeamBadge() + "\0", print_writers);
									CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$RightSection$Section3$Side" + WhichSide + 
											"$ThisOver$Ball_" + (iBall + 1) + "$Boundaries$img_Text2*TEXTURE*IMAGE SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_TEXT2 : Constants.MT20_TEXT2) + 
											inning.getBatting_team().getTeamBadge() + "\0", print_writers);
									
									CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$RightSection$Section3$Side" + WhichSide + 
											"$ThisOver$Ball_" + (iBall+1) + "$Boundaries$select_LineNumber*FUNCTION*Omo*vis_con SET 1\0", print_writers);
									CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$RightSection$Section3$Side" + WhichSide + 
											"$ThisOver$Ball_" + (iBall+1) + "$Boundaries$1_Line$txt_Figures1*GEOM*TEXT SET " + this_data_str.get(this_data_str.size()-1).
											split(",")[iBall].replace("BOUNDARY", "")	 + "\0", print_writers);
									
								}else if(!this_data_str.get(this_data_str.size()-1).isEmpty()) {
									CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$RightSection$Section3$Side" + WhichSide + 
											"$ThisOver$Ball_" + (iBall+1) + "$Select_DataType*FUNCTION*Omo*vis_con SET 1\0", print_writers);
									
									if(this_data_str.get(this_data_str.size()-1).split(",")[iBall].contains("+")) {
										String line1 = "",line2 = "";
										String[] parts = this_data_str.get(this_data_str.size()-1).split(",")[iBall].split("\\+");
										if (parts.length == 2) {
									        line1 = parts[0];
									        line2 = parts[1];
									    } else if (parts.length == 3) {
									        line1 = parts[0];
									        line2 = parts[1] + "+" + parts[2];
									    }
										
										CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$RightSection$Section3$Side" + WhichSide + 
												"$ThisOver$Ball_" + (iBall+1) + "$Runs$select_LineNumber*FUNCTION*Omo*vis_con SET 2\0", print_writers);
										CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$RightSection$Section3$Side" + WhichSide + 
												"$ThisOver$Ball_" + (iBall+1) + "$Runs$2_Line$txt_Figures1*GEOM*TEXT SET " + line1.toUpperCase() + "\0", print_writers);
										CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$RightSection$Section3$Side" + WhichSide + 
												"$ThisOver$Ball_" + (iBall+1) + "$Runs$2_Line$txt_Figures2*GEOM*TEXT SET " + line2.toUpperCase() + "\0", print_writers);
									}else {
										CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$RightSection$Section3$Side" + WhichSide + 
												"$ThisOver$Ball_" + (iBall+1) + "$Runs$select_LineNumber*FUNCTION*Omo*vis_con SET 1\0", print_writers);
										if(this_data_str.get(this_data_str.size()-1).split(",")[iBall].contains("PN")) {
											CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$RightSection$Section3$Side" + WhichSide + 
													"$ThisOver$Ball_" + (iBall+1) + "$Runs$1_Line$txt_Figures1*GEOM*TEXT SET 5PN\0", print_writers);
										}else {
											CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$RightSection$Section3$Side" + WhichSide + 
													"$ThisOver$Ball_" + (iBall+1) + "$Runs$1_Line$txt_Figures1*GEOM*TEXT SET " + this_data_str.get(this_data_str.size()-1).
													split(",")[iBall].toUpperCase() + "\0", print_writers);
										}
									}
									
									switch (this_data_str.get(this_data_str.size()-1).split(",")[iBall].toUpperCase()) {
									case "1B": case "2B": case "3B": case "4B": case "5B": case "6B":
									case "1LB": case "2LB": case "3LB": case "4LB": case "5LB": case "6LB":
										
										break;

									default:
										if(this_data_str.get(this_data_str.size()-1).split(",")[iBall].contains("NB") || 
												this_data_str.get(this_data_str.size()-1).split(",")[iBall].contains("WD")||
												this_data_str.get(this_data_str.size()-1).split(",")[iBall].contains("PN_Y")) {
											totalOverSize++;
										}
										break;
									}
								}
								break;
							}
						}
					}else {
						if(this_data_str.get(this_data_str.size()-1).split(",").length > 9 || isThisOverLimitExceed) {
							isThisOverLimitExceed = true;
							if(this_data_str.get(this_data_str.size()-1).split(",").length == 10) {
//								setThisOverAndLastOverData(print_writers, matchAllData, 2);
//								this_animation.processAnimation(Constants.FRONT, print_writers, "anim_Infobar$Section_3_Change", "START");
//								TimeUnit.MILLISECONDS.sleep(1000);
								
								setThisOverAndLastOverData(print_writers, matchAllData, 1);
//								TimeUnit.MILLISECONDS.sleep(500);
//								this_animation.processAnimation(Constants.FRONT, print_writers, "anim_Infobar$Section_3_Change", "SHOW 0.0");
							}else {
								setThisOverAndLastOverData(print_writers, matchAllData, 1);
							}
						}
					}
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$RightSection$Section3$Side" + WhichSide + 
							"$ThisOver$select_BallNumber*FUNCTION*Omo*vis_con SET " + totalOverSize + "\0", print_writers);
					if(totalOverSize >= 6) {
						for(int i = totalOverSize+1;i<=9;i++) {
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$RightSection$Section3$Side" + WhichSide + 
									"$ThisOver$select_BallNumber$Ball_" + i + "$Select_DataType*FUNCTION*Omo*vis_con SET 0\0", print_writers);
						}
					}
					infobar.setLast_this_over(this_data_str.get(this_data_str.size()-1));
					break;
				}
			}
			break;
		}
		return Constants.OK;
	}
	public String populateSection4(List<PrintWriter> print_writers, MatchAllData matchAllData,int WhichSide) throws InterruptedException {
		switch(config.getBroadcaster()) {
		case Constants.TRI_SERIES:  case Constants.MT20: case Constants.TG20:
			if ((!isNullOrEmpty(infobar.getLast_section4()) && !isNullOrEmpty(infobar.getSection4())) ||
			    (isNullOrEmpty(infobar.getLast_section4()) && isNullOrEmpty(infobar.getSection4()))) {
			    populateBowlerName(print_writers, matchAllData, 1, 1);
			} else {
			    populateBowlerName(print_writers, matchAllData, 1, 2);
			}
			
			switch(config.getBroadcaster()) {
			case Constants.TG20:
				if(infobar.getSection4() != null && !infobar.getSection4().isEmpty()) {
					switch(infobar.getSection4().toUpperCase()) {
					case "WEATHER":
						Weather weatherData = cricketService.getWeather().get(0);
						
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$RightSection$Section4$Side" + WhichSide 
								+ "$select_DataType*FUNCTION*Omo*vis_con SET 1\0", print_writers);
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$RightSection$Section4$Side" + WhichSide 
								+ "$Weather$txt_Temperature*GEOM*TEXT SET " + weatherData.getCurrentTemp() + "\0", print_writers);
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$RightSection$Section4$Side" + WhichSide 
								+ "$Weather$img_Icon*TEXTURE*IMAGE SET " + Constants.TG20_WEATHERICON + "Icon_" + weatherData.getIconType() + "\0", print_writers);
						break;
					case "BOWLER_ECONOMY":
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$RightSection$Section4$Side" + WhichSide 
								+ "$select_DataType*FUNCTION*Omo*vis_con SET 0\0", print_writers);
						
						CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$RightSection$Section4$Side" + WhichSide + "$Stats$"
								+ "select_Language*FUNCTION*Omo*vis_con SET ", config, Constants.TG20, print_writers, foreignLanguageOmo);
						foreignLanguageData = CricketFunctions.AssembleMultiLanguageData(CricketUtil.DICTIONARY, "", multilanguagedata, "ECONOMY", "", null, 0, foreignLanguageDataList);
						CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$RightSection$Section4$Side" + WhichSide + "$Stats$English"
								+ "$txt_StatHead*GEOM*TEXT SET ", config, Constants.TG20, print_writers, foreignLanguageData);
						
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$RightSection$Section4$Side" + WhichSide 
								+ "$Stats$txt_StatValue*GEOM*TEXT SET " + CricketFunctions.getEconomy(bowlingCard.getRuns(), 
										((bowlingCard.getOvers()*6)+bowlingCard.getBalls()), 2, "-") + "\0", print_writers);
						break;
					case "TARGET":
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$RightSection$Section4$Side" + WhichSide 
								+ "$select_DataType*FUNCTION*Omo*vis_con SET 0\0", print_writers);
						
						CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$RightSection$Section4$Side" + WhichSide + "$Stats$"
								+ "select_Language*FUNCTION*Omo*vis_con SET ", config, Constants.TG20, print_writers, foreignLanguageOmo);
						foreignLanguageData = CricketFunctions.AssembleMultiLanguageData(CricketUtil.DICTIONARY, "", multilanguagedata, "TARGET", "", null, 0, foreignLanguageDataList);
						CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$RightSection$Section4$Side" + WhichSide + "$Stats$English"
								+ "$txt_StatHead*GEOM*TEXT SET ", config, Constants.TG20, print_writers, foreignLanguageData);
						
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$RightSection$Section4$Side" + WhichSide 
								+ "$Stats$txt_StatValue*GEOM*TEXT SET " + CricketFunctions.GetTargetData(matchAllData).getTargetRuns() + "\0", print_writers);
						break;
					}
				}
				break;
			default:
				if(infobar.getSection4() != null && !infobar.getSection4().isEmpty()) {
					switch(infobar.getSection4().toUpperCase()) {
					case "WEATHER":
						Weather weatherData = cricketService.getWeather().get(0);
						
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$RightSection$Section4$Side" + WhichSide 
								+ "$select_DataType*FUNCTION*Omo*vis_con SET 1\0", print_writers);
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$RightSection$Section4$Side" + WhichSide 
								+ "$Weather$txt_Temperature*GEOM*TEXT SET " + weatherData.getCurrentTemp() + "\0", print_writers);
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$RightSection$Section4$Side" + WhichSide 
								+ "$Weather$img_Icon*TEXTURE*IMAGE SET " + Constants.TRI_SERIES_WEATHERICON + "Icon_" + weatherData.getIconType() + "\0", print_writers);
						break;
					case "BOWLER_ECONOMY":
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$RightSection$Section4$Side" + WhichSide 
								+ "$select_DataType*FUNCTION*Omo*vis_con SET 0\0", print_writers);
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$RightSection$Section4$Side" + WhichSide 
								+ "$Stats$txt_StatHead*GEOM*TEXT SET ECONOMY\0", print_writers);
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$RightSection$Section4$Side" + WhichSide 
								+ "$Stats$txt_StatValue*GEOM*TEXT SET " + CricketFunctions.getEconomy(bowlingCard.getRuns(), 
										((bowlingCard.getOvers()*6)+bowlingCard.getBalls()), 2, "-") + "\0", print_writers);
						break;
					case "TARGET":
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$RightSection$Section4$Side" + WhichSide 
								+ "$select_DataType*FUNCTION*Omo*vis_con SET 0\0", print_writers);
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$RightSection$Section4$Side" + WhichSide 
								+ "$Stats$txt_StatHead*GEOM*TEXT SET TARGET\0", print_writers);
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$RightSection$Section4$Side" + WhichSide 
								+ "$Stats$txt_StatValue*GEOM*TEXT SET " + CricketFunctions.GetTargetData(matchAllData).getTargetRuns() + "\0", print_writers);
						break;
					case "EQUATION":
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$RightSection$Section4$Side" + WhichSide 
								+ "$select_DataType*FUNCTION*Omo*vis_con SET 2\0", print_writers);
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$RightSection$Section4$Side" + WhichSide 
								+ "$FreeText$txt_Text1*GEOM*TEXT SET RUN" + CricketFunctions.Plural(CricketFunctions.GetTargetData(matchAllData).getRemaningRuns()).toUpperCase() 
								+ " " + CricketFunctions.GetTargetData(matchAllData).getRemaningRuns() + "\0", print_writers);
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$RightSection$Section4$Side" + WhichSide 
								+ "$FreeText$txt_Text2*GEOM*TEXT SET BALL" + CricketFunctions.Plural(CricketFunctions.GetTargetData(matchAllData).getRemaningBall()).toUpperCase() 
								+ " " + CricketFunctions.GetTargetData(matchAllData).getRemaningBall() + "\0", print_writers);
						break;
					}
				}
				break;
			}
			
			break;
		}
		return Constants.OK;
	}
	public String populateSection5(List<PrintWriter> print_writers, MatchAllData matchAllData, int WhichSide) throws CloneNotSupportedException, IOException, NumberFormatException, InterruptedException {
		switch(config.getBroadcaster()) {
		case Constants.ACC:
			if(infobar.getSection5() != null && !infobar.getSection5().isEmpty()) {
				switch(infobar.getSection5().toUpperCase()) {
				case "THIS_MATCH_FOURS":
					int foursThisMatch = 0;
					for (Inning inn : matchAllData.getMatch().getInning()) {
						foursThisMatch+=inn.getTotalFours();
					}
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section5$Side" + WhichSide + "$Select"
							+ "*FUNCTION*Omo*vis_con SET 4\0", print_writers);
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section5$Side" + WhichSide + "$TournamentFours$"
							+ "txt_Head*GEOM*TEXT SET " + "FOURS THIS MATCH" + "\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section5$Side" + WhichSide + "$TournamentFours$Data1Grp"
							+ "$txt_Data1*GEOM*TEXT SET " + foursThisMatch  + "\0", print_writers);
					break;
				case "THIS_MATCH_SIXES":
					int sixesThisMatch = 0;
					for (Inning inn : matchAllData.getMatch().getInning()) {
						sixesThisMatch+=inn.getTotalSixes();
					}
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section5$Side" + WhichSide + "$Select"
							+ "*FUNCTION*Omo*vis_con SET 3\0", print_writers);
				
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section5$Side" + WhichSide + "$TournamentSixes$"
							+ "txt_Head*GEOM*TEXT SET " + "SIXES THIS MATCH" + "\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section5$Side" + WhichSide + "$TournamentSixes$Data1Grp"
							+ "$txt_Data1*GEOM*TEXT SET " + sixesThisMatch  + "\0", print_writers);
					break;
				case "TOURNAMENT_FOURS":
					int fours = 0;
					today_fours = String.valueOf(CricketFunctions.extracttournamentFoursAndSixesData("CURRENT_MATCH_DATA", headToHead, matchAllData, null).getTournament_fours());
					fours = Integer.valueOf(previous_fours) + Integer.valueOf(today_fours);
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section5$Side" + WhichSide + "$Select"
							+ "*FUNCTION*Omo*vis_con SET 4\0", print_writers);
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section5$Side" + WhichSide + "$TournamentFours$"
							+ "txt_Head*GEOM*TEXT SET " + "FOURS THIS SERIES" + "\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section5$Side" + WhichSide + "$TournamentFours$Data1Grp"
							+ "$txt_Data1*GEOM*TEXT SET " + fours + "\0", print_writers);
					break;
				case "TOURNAMENT_SIXES":
					int sixes = 0;
					today_sixes = String.valueOf(CricketFunctions.extracttournamentFoursAndSixesData("CURRENT_MATCH_DATA", headToHead, matchAllData, null).getTournament_sixes());
					sixes = Integer.valueOf(previous_sixes) + Integer.valueOf(today_sixes);
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section5$Side" + WhichSide + "$Select"
							+ "*FUNCTION*Omo*vis_con SET 3\0", print_writers);
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section5$Side" + WhichSide + "$TournamentSixes$"
							+ "txt_Head*GEOM*TEXT SET " + "SIXES THIS SERIES" + "\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section5$Side" + WhichSide + "$TournamentSixes$Data1Grp"
							+ "$txt_Data1*GEOM*TEXT SET " + sixes + "\0", print_writers);
					break;
				case "REVIEWS_REMAINING":
//					Review reviewRemaining = CricketFunctions.getReviewRemaining(matchAllData);
//					String[] parts = reviewRemaining.getReviewStatus().split(",");
					
					String text_to_return = "";
					int lineIndex1 = 1;
				    boolean found1 = false;
					BufferedReader br = new BufferedReader(new FileReader(CricketUtil.CRICKET_DIRECTORY + "ICC_Reviews.txt"));
				
				    while( (text_to_return = br.readLine()) != null) {
				        if(lineIndex1 == 1) {
				        	CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section5$Side" + WhichSide + "$InningBoundaries$Data1Grp$"
									+ "txt_Data1*GEOM*TEXT SET " + text_to_return.split(" ")[0] + "\0", print_writers);
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section5$Side" + WhichSide + "$InningBoundaries$Data2Grp$"
									+ "txt_Data2*GEOM*TEXT SET " + text_to_return.split(" ")[1]  + "\0", print_writers);
				            found1 = true;
				            break;
				        }
				        lineIndex1++;
				    }
				    if(!found1) {
				    	//System.out.println("Line Not There");
				    }
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section5$Side" + WhichSide + "$Select*FUNCTION*Omo*vis_con SET 0\0", print_writers);
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section5$Side" + WhichSide + "$InningBoundaries$"
							+ "txt_Head*GEOM*TEXT SET " + "REVIEWS REMAINING" + "\0", print_writers);
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section5$Side" + WhichSide + "$InningBoundaries$Data1Grp$"
							+ "txt_Head2*GEOM*TEXT SET " + matchAllData.getSetup().getHomeTeam().getTeamName4() + "\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section5$Side" + WhichSide + "$InningBoundaries$Data2Grp$"
							+ "txt_Head3*GEOM*TEXT SET " + matchAllData.getSetup().getAwayTeam().getTeamName4() + "\0", print_writers);
					
					break;
					
				case CricketUtil.DOT: case CricketUtil.FOUR: case CricketUtil.SIX: 
				case CricketUtil.BOUNDARY: case "LASTXBALLS": case "EXTRAS": case "LAST_WICKET": case "BALLS_SINCE_LAST_BOUNDARY":
					inning = matchAllData.getMatch().getInning().stream().filter(inn -> inn.getIsCurrentInning().equalsIgnoreCase(CricketUtil.YES)).findAny().orElse(null);
					if(inning == null) {
						return "populateVizInfobarMiddleSection: Inning returned is NULL";
					}
					
					switch(infobar.getSection5().toUpperCase()) {
					
					case CricketUtil.DOT:
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section5$Side" + WhichSide + "$Select"
								+ "*FUNCTION*Omo*vis_con SET 5\0", print_writers);
						
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section5$Side" + WhichSide + "$DotBalls$"
								+ "txt_Head*GEOM*TEXT SET " + "DOTS THIS INNINGS" + "\0", print_writers);
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section5$Side" + WhichSide + "$DotBalls$Data1Grp$txt_Data1*GEOM*TEXT SET " 
								+ (inning.getInningNumber() == 1 ? IndexController.MatchStats.getHomeTeamScoreData().getTotalDots() : 
									IndexController.MatchStats.getAwayTeamScoreData().getTotalDots())  + "\0", print_writers);
						break;
					case CricketUtil.FOUR:
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section5$Side" + WhichSide + "$Select"
								+ "*FUNCTION*Omo*vis_con SET 4\0", print_writers);
						
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section5$Side" + WhichSide + "$TournamentFours$"
								+ "txt_Head*GEOM*TEXT SET " + "FOURS THIS INNINGS" + "\0", print_writers);
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section5$Side" + WhichSide + "$TournamentFours$Data1Grp"
								+ "$txt_Data1*GEOM*TEXT SET " + inning.getTotalFours()  + "\0", print_writers);
						break;
					case CricketUtil.SIX:
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section5$Side" + WhichSide + "$Select"
								+ "*FUNCTION*Omo*vis_con SET 3\0", print_writers);
						
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section5$Side" + WhichSide + "$TournamentSixes$"
								+ "txt_Head*GEOM*TEXT SET " + "SIXES THIS INNINGS" + "\0", print_writers);
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section5$Side" + WhichSide + "$TournamentSixes$Data1Grp"
								+ "$txt_Data1*GEOM*TEXT SET " + inning.getTotalSixes()  + "\0", print_writers);
						break;
					case CricketUtil.BOUNDARY:
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section5$Side" + WhichSide + "$Select"
								+ "*FUNCTION*Omo*vis_con SET 0\0", print_writers);
						
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section5$Side" + WhichSide + "$InningBoundaries$"
								+ "txt_Head*GEOM*TEXT SET " + "INNINGS BOUNDARIES" + "\0", print_writers);
						
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section5$Side" + WhichSide + "$InningBoundaries$Data1Grp$"
								+ "txt_Head2*GEOM*TEXT SET " + "FOUR" + CricketFunctions.Plural(inning.getTotalFours()).toUpperCase() + "\0", print_writers);
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section5$Side" + WhichSide + "$InningBoundaries$Data2Grp$"
								+ "txt_Head3*GEOM*TEXT SET " + (inning.getTotalSixes() == 1 ? "SIX" : "SIXES")+ "\0", print_writers);
						
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section5$Side" + WhichSide + "$InningBoundaries$Data1Grp$"
								+ "txt_Data1*GEOM*TEXT SET " + inning.getTotalFours() + "\0", print_writers);
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section5$Side" + WhichSide + "$InningBoundaries$Data2Grp$"
								+ "txt_Data2*GEOM*TEXT SET " + inning.getTotalSixes() + "\0", print_writers);
						break;
					case "LASTXBALLS":
						this_data_str = new ArrayList<String>();
						this_data_str.add(CricketFunctions.getlastthirtyballsdata(matchAllData, slashOrDash, matchAllData.getEventFile().getEvents(), lastXballs));
						if(this_data_str.get(this_data_str.size()-1) == null || this_data_str.get(this_data_str.size()-1).split(slashOrDash).length > 4) {
							return "populateVizInfobarMiddleSection: Last " + lastXballs + " Balls data returned invalid";
						}
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section5$Side" + WhichSide + "$Select"
								+ "*FUNCTION*Omo*vis_con SET 1\0", print_writers);
						
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section5$Side" + WhichSide + "$LastXBalls$"
								+ "txt_Head*GEOM*TEXT SET " + "LAST " + lastXballs + " BALLS" + "\0", print_writers);
						
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section5$Side" + WhichSide + "$LastXBalls$Data1Grp$"
								+ "txt_Head2*GEOM*TEXT SET " + "RUN" + CricketFunctions.Plural(Integer.valueOf(this_data_str.
										get(this_data_str.size()-1).split(slashOrDash)[0])).toUpperCase() + "\0", print_writers);
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section5$Side" + WhichSide + "$LastXBalls$Data2Grp$"
								+ "txt_Head3*GEOM*TEXT SET " + "WICKET" + CricketFunctions.Plural(Integer.valueOf(this_data_str.
										get(this_data_str.size()-1).split(slashOrDash)[1])).toUpperCase()+ "\0", print_writers);
						
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section5$Side" + WhichSide + "$LastXBalls$Data1Grp$"
								+ "txt_Data1*GEOM*TEXT SET " + this_data_str.get(this_data_str.size()-1).split(slashOrDash)[0] + "\0", print_writers);
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section5$Side" + WhichSide + "$LastXBalls$Data2Grp$"
								+ "txt_Data2*GEOM*TEXT SET " + this_data_str.get(this_data_str.size()-1).split(slashOrDash)[1] + "\0", print_writers);
											
						break;
					case "EXTRAS":
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section5$Side" + WhichSide + "$Select"
								+ "*FUNCTION*Omo*vis_con SET 7\0", print_writers);
						
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section5$Side" + WhichSide + "$Extras$txt_Head"
								+ "*GEOM*TEXT SET " + "EXTRAS - " + inning.getTotalExtras() + "\0", print_writers);
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section5$Side" + WhichSide + "$Extras$DataAll$txt_Data3"
								+ "*GEOM*TEXT SET " + "WD " + inning.getTotalWides() + " | NB " + inning.getTotalNoBalls() + " | LB " + inning.getTotalLegByes() 
								+ " | B " + inning.getTotalByes() + (inning.getTotalPenalties() > 0 ? " | PN " + inning.getTotalPenalties() : "") + "\0", print_writers);
						break;
					case "LAST_WICKET":
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section5$Side" + WhichSide + "$Select"
								+ "*FUNCTION*Omo*vis_con SET 2\0", print_writers);
						
						if(inning.getFallsOfWickets() == null && inning.getFallsOfWickets().isEmpty()) {
							return "populateVizInfobarSection5: FoW returned is EMPTY";
						}
						battingCard = inning.getBattingCard().stream().filter(bc -> bc.getPlayerId() == 
								inning.getFallsOfWickets().get(inning.getFallsOfWickets().size() - 1).getFowPlayerID()).findAny().orElse(null);
						
						if(battingCard == null) {
							return "populateVizInfobarSection5: Last wicket returned is invalid";
						}
						
						String how_out_txt = "";
						if(battingCard.getHowOut().equalsIgnoreCase(CricketUtil.RUN_OUT)) {
							if(battingCard.getWasHowOutFielderSubstitute() != null && battingCard.getWasHowOutFielderSubstitute().equalsIgnoreCase(CricketUtil.YES)) {
								how_out_txt = "run out " + "(sub " + battingCard.getHowOutFielder().getTicker_name() + ")";
							} else {
								how_out_txt = "run out (" + battingCard.getHowOutFielder().getTicker_name() + ")";
							}
						}
						else if(battingCard.getHowOut().equalsIgnoreCase(CricketUtil.CAUGHT)) {
							if(battingCard.getWasHowOutFielderSubstitute() != null && battingCard.getWasHowOutFielderSubstitute().equalsIgnoreCase(CricketUtil.YES)) {
								how_out_txt = "c " +  "(sub - " + battingCard.getHowOutFielder().getTicker_name() + ")  b " + battingCard.getHowOutBowler().getTicker_name();
							} else {
								how_out_txt = "c " + battingCard.getHowOutFielder().getTicker_name() + "  b " + battingCard.getHowOutBowler().getTicker_name();
							}
						}
						else if(battingCard.getHowOut().equalsIgnoreCase(CricketUtil.STUMPED)) {
							if(battingCard.getWasHowOutFielderSubstitute() != null && battingCard.getWasHowOutFielderSubstitute().equalsIgnoreCase(CricketUtil.YES)) {
								how_out_txt = "st " +  "(sub - " + battingCard.getHowOutFielder().getTicker_name() + ")  b " + battingCard.getHowOutBowler().getTicker_name();
							} else {
								how_out_txt = "st " + battingCard.getHowOutFielder().getTicker_name() + "  b " + battingCard.getHowOutBowler().getTicker_name();
							}
						}
						else {
							if(!battingCard.getHowOutPartOne().isEmpty()) {
								how_out_txt = battingCard.getHowOutPartOne();
							}
							if(!battingCard.getHowOutPartTwo().isEmpty()) {
								if(!how_out_txt.trim().isEmpty()) {
									how_out_txt = how_out_txt + "  " + battingCard.getHowOutPartTwo();
								}else {
									how_out_txt = battingCard.getHowOutPartTwo();
								}
							}
						}
						
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section5$Side" + WhichSide + "$LastWicket$"
								+ "txt_Head*GEOM*TEXT SET " + "LAST WICKET" + "\0", print_writers);
						
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section5$Side" + WhichSide + "$LastWicket$Data1Grp$"
								+ "txt_Head2*GEOM*TEXT SET " + battingCard.getPlayer().getTicker_name() + "\0", print_writers);
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section5$Side" + WhichSide + "$LastWicket$Data2Grp$"
								+ "txt_Head2*GEOM*TEXT SET " + battingCard.getHowOutText() + "\0", print_writers);
						
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section5$Side" + WhichSide + "$LastWicket$Data2Grp$"
								+ "Data$txt_Data1*GEOM*TEXT SET " + battingCard.getRuns() + "\0", print_writers);
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section5$Side" + WhichSide + "$LastWicket$Data2Grp$"
								+ "Data$txt_Data2*GEOM*TEXT SET " + "(" + battingCard.getBalls() + ")" + "\0", print_writers);
						break;
						
					case "BALLS_SINCE_LAST_BOUNDARY":
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section5$Side" + WhichSide + "$Select"
								+ "*FUNCTION*Omo*vis_con SET 6\0", print_writers);
						
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section5$Side" + WhichSide + "$BallsSince$"
								+ "txt_Head*GEOM*TEXT SET " + "BALLS SINCE LAST BOUNDARY" + "\0", print_writers);
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section5$Side" + WhichSide + "$BallsSince$Data1Grp$"
								+ "Data$img_Text2$txt_Data1*GEOM*TEXT SET " + IndexController.MatchStats.getBallsSinceLastBoundary() + "\0", print_writers);
						break;
					}
					break;
				}
			}
			break;
		case Constants.BAN_AFG_SERIES:
			if(infobar.getSection5() != null && !infobar.getSection5().isEmpty()) {
				switch(infobar.getSection5().toUpperCase()) {
				case "THIS_MATCH_FOURS":
					int foursThisMatch = 0;
					for (Inning inn : matchAllData.getMatch().getInning()) {
						foursThisMatch+=inn.getTotalFours();
					}
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section3$Side" + WhichSide + "$Select"
							+ "*FUNCTION*Omo*vis_con SET 7\0", print_writers);
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section3$Side" + WhichSide + "$TournamentFours$Data1Grp$"
							+ "img_Base2*ACTIVE SET 1\0", print_writers);
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section3$Side" + WhichSide + "$TournamentFours$Data1Grp$"
							+ "img_Base2*TEXTURE*IMAGE SET " + Constants.BAN_AFG_SERIES_BASE2 + inning.getBatting_team().getTeamBadge() + "\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section3$Side" + WhichSide + "$TournamentFours$Data1Grp$"
							+ "img_Text2*TEXTURE*IMAGE SET " + Constants.BAN_AFG_SERIES_TEXT2 + inning.getBatting_team().getTeamBadge() + "\0", print_writers);
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section3$Side" + WhichSide + "$TournamentFours$"
							+ "txt_Head*GEOM*TEXT SET " + "FOURS THIS MATCH" + "\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section3$Side" + WhichSide + "$TournamentFours$Data1Grp"
							+ "$txt_Data1*GEOM*TEXT SET " + foursThisMatch  + "\0", print_writers);
					break;
				case "THIS_MATCH_SIXES":
					int sixesThisMatch = 0;
					for (Inning inn : matchAllData.getMatch().getInning()) {
						sixesThisMatch+=inn.getTotalSixes();
					}
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section3$Side" + WhichSide + "$Select"
							+ "*FUNCTION*Omo*vis_con SET 6\0", print_writers);
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section3$Side" + WhichSide + "$TournamentSixes$Data1Grp$"
							+ "img_Base2*ACTIVE SET 1\0", print_writers);
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section3$Side" + WhichSide + "$TournamentSixes$Data1Grp$"
							+ "img_Base2*TEXTURE*IMAGE SET " + Constants.BAN_AFG_SERIES_BASE2 + inning.getBatting_team().getTeamBadge() + "\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section3$Side" + WhichSide + "$TournamentSixes$Data1Grp$"
							+ "img_Text2*TEXTURE*IMAGE SET " + Constants.BAN_AFG_SERIES_TEXT2 + inning.getBatting_team().getTeamBadge() + "\0", print_writers);
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section3$Side" + WhichSide + "$TournamentSixes$"
							+ "txt_Head*GEOM*TEXT SET " + "SIXES THIS MATCH" + "\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section3$Side" + WhichSide + "$TournamentSixes$Data1Grp"
							+ "$txt_Data1*GEOM*TEXT SET " + sixesThisMatch  + "\0", print_writers);
					break;
				case "TOURNAMENT_FOURS":
					int fours = 0;
					today_fours = String.valueOf(CricketFunctions.extracttournamentFoursAndSixesData("CURRENT_MATCH_DATA", headToHead, matchAllData, null).getTournament_fours());
					fours = Integer.valueOf(previous_fours) + Integer.valueOf(today_fours);
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section3$Side" + WhichSide + "$Select"
							+ "*FUNCTION*Omo*vis_con SET 7\0", print_writers);
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section3$Side" + WhichSide + "$TournamentFours$Data1Grp$"
							+ "img_Base2*ACTIVE SET 1\0", print_writers);
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section3$Side" + WhichSide + "$TournamentFours$Data1Grp$"
							+ "img_Base2*TEXTURE*IMAGE SET " + Constants.BAN_AFG_SERIES_BASE2 + "EVENT" + "\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section3$Side" + WhichSide + "$TournamentFours$Data1Grp$"
							+ "img_Text2*TEXTURE*IMAGE SET " + Constants.BAN_AFG_SERIES_TEXT2 + "EVENT" + "\0", print_writers);
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section3$Side" + WhichSide + "$TournamentFours$"
							+ "txt_Head*GEOM*TEXT SET " + "FOURS THIS SERIES" + "\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section3$Side" + WhichSide + "$TournamentFours$Data1Grp"
							+ "$txt_Data1*GEOM*TEXT SET " + fours + "\0", print_writers);
					break;
				case "TOURNAMENT_SIXES":
					int sixes = 0;
					today_sixes = String.valueOf(CricketFunctions.extracttournamentFoursAndSixesData("CURRENT_MATCH_DATA", headToHead, matchAllData, null).getTournament_sixes());
					sixes = Integer.valueOf(previous_sixes) + Integer.valueOf(today_sixes);
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section3$Side" + WhichSide + "$Select"
							+ "*FUNCTION*Omo*vis_con SET 6\0", print_writers);
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section3$Side" + WhichSide + "$TournamentSixes$Data1Grp$"
							+ "img_Base2*ACTIVE SET 1\0", print_writers);
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section3$Side" + WhichSide + "$TournamentSixes$Data1Grp$"
							+ "img_Base2*TEXTURE*IMAGE SET " + Constants.BAN_AFG_SERIES_BASE2 + "EVENT" + "\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section3$Side" + WhichSide + "$TournamentSixes$Data1Grp$"
							+ "img_Text2*TEXTURE*IMAGE SET " + Constants.BAN_AFG_SERIES_TEXT2 + "EVENT" + "\0", print_writers);
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section3$Side" + WhichSide + "$TournamentSixes$"
							+ "txt_Head*GEOM*TEXT SET " + "SIXES THIS SERIES" + "\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section3$Side" + WhichSide + "$TournamentSixes$Data1Grp"
							+ "$txt_Data1*GEOM*TEXT SET " + sixes + "\0", print_writers);
					break;
				case CricketUtil.PROJECTED:
					inning = matchAllData.getMatch().getInning().stream().filter(inn -> inn.getInningNumber() == 1 &&
					inn.getIsCurrentInning().equalsIgnoreCase(CricketUtil.YES)).findAny().orElse(null);

					if(inning == null) {
						return "populateSectionAnalytics: 1st Inning returned is NULL";
					}
					this_data_str = CricketFunctions.projectedScore(matchAllData);
					
					if(this_data_str.size() <= 0) {
						return "populateSectionAnalytics: Projected score invalid";
					}
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section3$Side" + WhichSide + "$Select"
							+ "*FUNCTION*Omo*vis_con SET 3\0", print_writers);
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section3$Side" + WhichSide + "$ProjectedScore$"
							+ "img_Base02*TEXTURE*IMAGE SET " + Constants.BAN_AFG_SERIES_BASE2 + inning.getBatting_team().getTeamBadge() + "\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section3$Side" + WhichSide + "$ProjectedScore$Data1Grp$"
							+ "img_Base2*TEXTURE*IMAGE SET " + Constants.BAN_AFG_SERIES_BASE2 + inning.getBatting_team().getTeamBadge() + "\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section3$Side" + WhichSide + "$ProjectedScore$Data2Grp$"
							+ "img_Base2*TEXTURE*IMAGE SET " + Constants.BAN_AFG_SERIES_BASE2 + inning.getBatting_team().getTeamBadge() + "\0", print_writers);
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section3$Side" + WhichSide + "$ProjectedScore$Data1Grp$"
							+ "img_Text2*TEXTURE*IMAGE SET " + Constants.BAN_AFG_SERIES_TEXT2 + inning.getBatting_team().getTeamBadge() + "\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section3$Side" + WhichSide + "$ProjectedScore$Data2Grp$"
							+ "img_Text2*TEXTURE*IMAGE SET " + Constants.BAN_AFG_SERIES_TEXT2 + inning.getBatting_team().getTeamBadge() + "\0", print_writers);
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section3$Side" + WhichSide + "$ProjectedScore$"
							+ "txt_Head*GEOM*TEXT SET " + "PROJECTED SCORES" + "\0", print_writers);
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section3$Side" + WhichSide + "$ProjectedScore$Data1Grp$"
							+ "txt_Head2*GEOM*TEXT SET " + "@CRR (" + this_data_str.get(0) + ")" + "\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section3$Side" + WhichSide + "$ProjectedScore$Data2Grp$"
							+ "txt_Head3*GEOM*TEXT SET " + String.format("%.2f", Double.parseDouble(this_data_str.get(2))) + "\0", print_writers);
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section3$Side" + WhichSide + "$ProjectedScore$Data1Grp$"
							+ "txt_Data1*GEOM*TEXT SET " + this_data_str.get(1) + "\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section3$Side" + WhichSide + "$ProjectedScore$Data2Grp$"
							+ "txt_Data2*GEOM*TEXT SET " + this_data_str.get(3) + "\0", print_writers);
					break;
				case "COMPARE": case "EQUATION": case "PAR_SCORE":
					inning = matchAllData.getMatch().getInning().stream().filter(inn -> inn.getIsCurrentInning().equalsIgnoreCase(CricketUtil.YES)
							&& inn.getInningNumber() == 2).findAny().orElse(null);
						
					if(inning == null) {
						return "populateVizInfobarRightSection: 2nd Inning returned is NULL";
					}
					
					switch(infobar.getSection5().toUpperCase()) {
					case "PAR_SCORE":
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section3$Side" + WhichSide + "$Select"
								+ "*FUNCTION*Omo*vis_con SET 4\0", print_writers);
						
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section3$Side" + WhichSide + "$AtThisStage$"
								+ "img_Base02*TEXTURE*IMAGE SET " + Constants.BAN_AFG_SERIES_BASE2 + inning.getBatting_team().getTeamBadge() + "\0", print_writers);
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section3$Side" + WhichSide + "$AtThisStage$Data1Grp$"
								+ "img_Base2*TEXTURE*IMAGE SET " + Constants.BAN_AFG_SERIES_BASE2 + inning.getBatting_team().getTeamBadge() + "\0", print_writers);
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section3$Side" + WhichSide + "$AtThisStage$Data1Grp$"
								+ "img_Text2*TEXTURE*IMAGE SET " + Constants.BAN_AFG_SERIES_TEXT2 + inning.getBatting_team().getTeamBadge() + "\0", print_writers);
						
						this_data_str = new ArrayList<String>();
						for(int i = 0; i<= dls.size() -1;i++) {
							if(dls.get(i).getOver_left().split("\\.")[0].equalsIgnoreCase(String.valueOf(inning.getTotalOvers()))) {
								for(int j=0;j<6;j++) {
									if(inning.getTotalBalls() == j) {
										this_data_str.add(dls.get(i+j).getWkts_down());
										break;
									}
								}
								break;
							}
						}
						
						this_data_str.add(CricketFunctions.populateDls(matchAllData, CricketUtil.SHORT, Integer.valueOf(this_data_str.get(0))));
						
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section3$Side" + WhichSide + "$AtThisStage$txt_Head*GEOM*TEXT SET " 
								+ "DLS PAR SCORE" + "\0", print_writers);
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section3$Side" + WhichSide + "$AtThisStage$Data1Grp$txt_Head2*GEOM*TEXT SET " + "AFTER " 
								+ CricketFunctions.OverBalls(inning.getTotalOvers(), inning.getTotalBalls()) + (inning.getTotalOvers()==1 && inning.getTotalBalls()==0?" OVER":" OVERS") + "\0", print_writers);
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section3$Side" + WhichSide + "$AtThisStage$Data1Grp$Data*ACTIVE SET 1\0", print_writers);
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section3$Side" + WhichSide + "$AtThisStage$Data1Grp$"
								+ "img_Text2$txt_Data1*GEOM*TEXT SET " + this_data_str.get(0) + "\0", print_writers);
						break;
					case "COMPARE":
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section3$Side" + WhichSide + "$Select"
								+ "*FUNCTION*Omo*vis_con SET 4\0", print_writers);
						
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section3$Side" + WhichSide + "$AtThisStage$"
								+ "img_Base02*TEXTURE*IMAGE SET " + Constants.BAN_AFG_SERIES_BASE2 + inning.getBowling_team().getTeamBadge() + "\0", print_writers);
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section3$Side" + WhichSide + "$AtThisStage$Data1Grp$"
								+ "img_Base2*TEXTURE*IMAGE SET " + Constants.BAN_AFG_SERIES_BASE2 + inning.getBowling_team().getTeamBadge() + "\0", print_writers);
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section3$Side" + WhichSide + "$AtThisStage$Data1Grp$"
								+ "img_Text2*TEXTURE*IMAGE SET " + Constants.BAN_AFG_SERIES_TEXT2 + inning.getBowling_team().getTeamBadge() + "\0", print_writers);
						
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section3$Side" + WhichSide + "$AtThisStage$txt_Head*GEOM*TEXT SET " 
								+ "AFTER " + CricketFunctions.OverBalls(inning.getTotalOvers(), inning.getTotalBalls()) + " OVERS" + "\0", print_writers);
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section3$Side" + WhichSide + "$AtThisStage$Data1Grp$"
								+ "txt_Head2*GEOM*TEXT SET " + inning.getBowling_team().getTeamName4() + " WERE" + "\0", print_writers);
						
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section3$Side" + WhichSide + "$AtThisStage$Data1Grp$Data*ACTIVE SET 1\0", print_writers);
						
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section3$Side" + WhichSide + "$AtThisStage$Data1Grp$"
								+ "img_Text2$txt_Data1*GEOM*TEXT SET " + CricketFunctions.compareInningData(matchAllData, "-", 
										1, matchAllData.getEventFile().getEvents()) + "\0", print_writers);
						break;
					case "EQUATION":
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section3$Side" + WhichSide + "$Select"
								+ "*FUNCTION*Omo*vis_con SET 2\0", print_writers);
						
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section3$Side" + WhichSide + "$Equation$"
								+ "img_Base02*TEXTURE*IMAGE SET " + Constants.BAN_AFG_SERIES_BASE2 + inning.getBatting_team().getTeamBadge() + "\0", print_writers);
						
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section3$Side" + WhichSide + "$Equation$Top$Data1Grp$"
								+ "img_Base2*TEXTURE*IMAGE SET " + Constants.BAN_AFG_SERIES_BASE2 + inning.getBatting_team().getTeamBadge() + "\0", print_writers);
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section3$Side" + WhichSide + "$Equation$Top$Data1Grp$"
								+ "img_Text2*TEXTURE*IMAGE SET " + Constants.BAN_AFG_SERIES_TEXT2 + inning.getBatting_team().getTeamBadge() + "\0", print_writers);
						
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section3$Side" + WhichSide + "$Equation$Bottom$Data1Grp$"
								+ "img_Base2*TEXTURE*IMAGE SET " + Constants.BAN_AFG_SERIES_BASE2 + inning.getBatting_team().getTeamBadge() + "\0", print_writers);
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section3$Side" + WhichSide + "$Equation$Bottom$Data1Grp$"
								+ "img_Text2*TEXTURE*IMAGE SET " + Constants.BAN_AFG_SERIES_TEXT2 + inning.getBatting_team().getTeamBadge() + "\0", print_writers);
						
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section3$Side" + WhichSide + "$Equation$Top$"
								+ "txt_Head*GEOM*TEXT SET " + "NEED" + "\0", print_writers);
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section3$Side" + WhichSide + "$Equation$Top$Data1Grp$"
								+ "txt_Data1*GEOM*TEXT SET " + CricketFunctions.GetTargetData(matchAllData).getRemaningRuns() + "\0", print_writers);
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section3$Side" + WhichSide + "$Equation$Top$Data1Grp$txt_Head2"
								+ "*GEOM*TEXT SET " + "RUN" + CricketFunctions.Plural(CricketFunctions.GetTargetData(matchAllData).getRemaningRuns()).toUpperCase() + "\0", print_writers);
						
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section3$Side" + WhichSide + "$Equation$Bottom$"
								+ "txt_Head*GEOM*TEXT SET " + "FROM" + "\0", print_writers);
						
						switch (matchAllData.getSetup().getMatchType()) {
						case CricketUtil.ODI:
							
							if(CricketFunctions.GetTargetData(matchAllData).getRemaningBall() >= 100) {
								CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section3$Side" + WhichSide + "$Equation$Bottom$Data1Grp$"
										+ "txt_Data1*GEOM*TEXT SET " + CricketFunctions.OverBalls(0, CricketFunctions.GetTargetData(matchAllData).getRemaningBall()) + "\0", print_writers);
								CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section3$Side" + WhichSide + "$Equation$Bottom$Data1Grp$txt_Head2"
										+ "*GEOM*TEXT SET " + "OVERS" + "\0", print_writers);
								
							}else {
								CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section3$Side" + WhichSide + "$Equation$Bottom$Data1Grp$"
										+ "txt_Data1*GEOM*TEXT SET " + CricketFunctions.GetTargetData(matchAllData).getRemaningBall() + "\0", print_writers);
								CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section3$Side" + WhichSide + "$Equation$Bottom$Data1Grp$txt_Head2"
										+ "*GEOM*TEXT SET " + "BALL" + CricketFunctions.Plural(CricketFunctions.GetTargetData(matchAllData).getRemaningBall()).toUpperCase() + "\0", print_writers);
							}
							break;

						default:
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section3$Side" + WhichSide + "$Equation$Bottom$Data1Grp$"
									+ "txt_Data1*GEOM*TEXT SET " + CricketFunctions.GetTargetData(matchAllData).getRemaningBall() + "\0", print_writers);
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section3$Side" + WhichSide + "$Equation$Bottom$Data1Grp$txt_Head2"
									+ "*GEOM*TEXT SET " + "BALL" + CricketFunctions.Plural(CricketFunctions.GetTargetData(matchAllData).getRemaningBall()).toUpperCase() + "\0", print_writers);
							break;
						}
						break;
					}
					break;
				case "REVIEWS_REMAINING":
//					Review reviewRemaining = CricketFunctions.getReviewRemaining(matchAllData);
//					String[] parts = reviewRemaining.getReviewStatus().split(",");
					
					String text_to_return = "";
					int lineIndex1 = 1;
				    boolean found1 = false;
					BufferedReader br = new BufferedReader(new FileReader(CricketUtil.CRICKET_DIRECTORY + "ICC_Reviews.txt"));
				
				    while( (text_to_return = br.readLine()) != null) {
				        if(lineIndex1 == 1) {
				        	CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section3$Side" + WhichSide + "$InningBoundaries$Data1Grp$"
									+ "txt_Data1*GEOM*TEXT SET " + text_to_return.split(" ")[0] + "\0", print_writers);
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section3$Side" + WhichSide + "$InningBoundaries$Data2Grp$"
									+ "txt_Data2*GEOM*TEXT SET " + text_to_return.split(" ")[1]  + "\0", print_writers);
				            found1 = true;
				            break;
				        }
				        lineIndex1++;
				    }
				    if(!found1) {
				    	//System.out.println("Line Not There");
				    }
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section3$Side" + WhichSide + "$Select*FUNCTION*Omo*vis_con SET 0\0", print_writers);
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section3$Side" + WhichSide + "$InningBoundaries$"
							+ "img_Base02*TEXTURE*IMAGE SET " + Constants.BAN_AFG_SERIES_BASE2 + matchAllData.getSetup().getHomeTeam().getTeamBadge() + "\0", print_writers);
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section3$Side" + WhichSide + "$InningBoundaries$Data1Grp$"
							+ "img_Base2*TEXTURE*IMAGE SET " + Constants.BAN_AFG_SERIES_BASE2 + matchAllData.getSetup().getHomeTeam().getTeamBadge() + "\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section3$Side" + WhichSide + "$InningBoundaries$Data2Grp$"
							+ "img_Base2*TEXTURE*IMAGE SET " + Constants.BAN_AFG_SERIES_BASE2 + matchAllData.getSetup().getAwayTeam().getTeamBadge() + "\0", print_writers);
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section3$Side" + WhichSide + "$InningBoundaries$Data1Grp$"
							+ "img_Text2*TEXTURE*IMAGE SET " + Constants.BAN_AFG_SERIES_TEXT2 + matchAllData.getSetup().getHomeTeam().getTeamBadge() + "\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section3$Side" + WhichSide + "$InningBoundaries$Data2Grp$"
							+ "img_Text2*TEXTURE*IMAGE SET " + Constants.BAN_AFG_SERIES_TEXT2 + matchAllData.getSetup().getAwayTeam().getTeamBadge() + "\0", print_writers);
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section3$Side" + WhichSide + "$InningBoundaries$"
							+ "txt_Head*GEOM*TEXT SET " + "REVIEWS REMAINING" + "\0", print_writers);
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section3$Side" + WhichSide + "$InningBoundaries$Data1Grp$"
							+ "txt_Head2*GEOM*TEXT SET " + matchAllData.getSetup().getHomeTeam().getTeamName4() + "\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section3$Side" + WhichSide + "$InningBoundaries$Data2Grp$"
							+ "txt_Head3*GEOM*TEXT SET " + matchAllData.getSetup().getAwayTeam().getTeamName4() + "\0", print_writers);
					
					break;
					
				case CricketUtil.OVER: case "ECONOMY": case "BOWLINGEND": case CricketUtil.DOT: case CricketUtil.FOUR: case CricketUtil.SIX: 
				case CricketUtil.BOUNDARY: case "LASTXBALLS": case "EXTRAS": case "LAST_WICKET": case "BALLS_SINCE_LAST_BOUNDARY":
					inning = matchAllData.getMatch().getInning().stream().filter(inn -> inn.getIsCurrentInning().equalsIgnoreCase(CricketUtil.YES)).findAny().orElse(null);
					if(inning == null) {
						return "populateVizInfobarMiddleSection: Inning returned is NULL";
					}
					
					switch(infobar.getSection5().toUpperCase()) {
					case CricketUtil.OVER:
						this_data_str = new ArrayList<String>();
						this_data_str.add(String.join(",", 
							    new ArrayList<>(Arrays.asList(IndexController.MatchStats.getOverData().getThisOverTxt().split(","))).stream()
						        .map(s -> s.replace("WIDE", "WD").replace("NO_BALL", "NB").replace("LEG_BYE", "LB").replace("BYE", "B")
						                   .replace("PENALTY", "PN").replace("LOG_WICKET", "W").replace("WICKET", "W"))
						        .collect(Collectors.collectingAndThen(Collectors.toList(), list -> {Collections.reverse(list); return list;}))
						        .toArray(new String[0])));
						
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section3$Side" + WhichSide + "$This_Over$img_Base02*TEXTURE*IMAGE SET " 
								+ Constants.BAN_AFG_SERIES_BASE2 + inning.getBatting_team().getTeamBadge() + "\0", print_writers);
						
						int totalOverSize = 6;
						for(int i=1;i<=totalOverSize;i++) {
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section3$Side" + WhichSide + "$This_Over$ThisOver$BallGrp" 
									+ i + "$SelectType*FUNCTION*Omo*vis_con SET 0\0", print_writers);
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section3$Side" + WhichSide + "$This_Over$ThisOver$BallGrp"
									+ i + "$group$img_Base2*TEXTURE*IMAGE SET " + Constants.BAN_AFG_SERIES_BASE2 + inning.getBatting_team().getTeamBadge() + "\0", print_writers);
						}
						
						if(this_data_str.get(this_data_str.size()-1) == null) {
							return "populateSection5: This over data returned invalid";
						}
						if(this_data_str.get(this_data_str.size()-1).split(",").length <= 9) {
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section3$Side" + WhichSide + "$Select"
									+ "*FUNCTION*Omo*vis_con SET 11\0", print_writers);
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section3$Side" + WhichSide + "$This_Over$txt_Head*GEOM*TEXT SET " 
									+ "THIS OVER" + "\0", print_writers);
							
							for(int iBall = 0; iBall < this_data_str.get(this_data_str.size()-1).split(",").length; iBall++) {
								//System.out.println(this_data_str.get(this_data_str.size()-1).split(",")[iBall].toUpperCase());
								switch (this_data_str.get(this_data_str.size()-1).split(",")[iBall].toUpperCase()) {
								case CricketUtil.DOT: case CricketUtil.ONE: case CricketUtil.TWO: case CricketUtil.THREE: case CricketUtil.FIVE: 
								case CricketUtil.FOUR:case CricketUtil.SIX: case "W":
									switch (this_data_str.get(this_data_str.size()-1).split(",")[iBall].toUpperCase()) {
									case CricketUtil.DOT:
										CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section3$Side" + WhichSide + "$This_Over$ThisOver$BallGrp" 
												+ (iBall+1) + "$SelectType*FUNCTION*Omo*vis_con SET 1\0", print_writers);
										CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section3$Side" + WhichSide + "$This_Over$ThisOver$BallGrp"
												+ (iBall+1) + "$Dot$img_Base2*TEXTURE*IMAGE SET " + Constants.BAN_AFG_SERIES_BASE2 + inning.getBatting_team().getTeamBadge() + "\0", print_writers);
										CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section3$Side" + WhichSide + "$This_Over$ThisOver$BallGrp"
												+ (iBall+1) + "$Dot$img_Text2*TEXTURE*IMAGE SET " + Constants.BAN_AFG_SERIES_TEXT2 + inning.getBatting_team().getTeamBadge() + "\0", print_writers);
										break;
									case "W":
										CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section3$Side" + WhichSide + "$This_Over$ThisOver$BallGrp" 
												+ (iBall+1) + "$SelectType*FUNCTION*Omo*vis_con SET 3\0", print_writers);
										CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section3$Side" + WhichSide + "$This_Over$ThisOver$BallGrp"
												+ (iBall+1) + "$Four_Six_WKT$img_Text2_No$txt_Ball*TEXTURE*IMAGE SET " + Constants.BAN_AFG_SERIES_BASE2 + inning.getBowling_team().getTeamBadge() + "\0", print_writers);
										
										CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section3$Side" + WhichSide + "$This_Over$ThisOver$BallGrp" 
												+ (iBall+1) + "$Four_Six_WKT$txt_Ball*GEOM*TEXT SET W\0", print_writers);
										break;
									default:
										CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section3$Side" + WhichSide + "$This_Over$ThisOver$BallGrp" 
												+ (iBall+1) + "$SelectType*FUNCTION*Omo*vis_con SET 2\0", print_writers);
										CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section3$Side" + WhichSide + "$This_Over$ThisOver$BallGrp"
												+ (iBall+1) + "$Rest$img_Base2*TEXTURE*IMAGE SET " + Constants.BAN_AFG_SERIES_BASE2 + inning.getBatting_team().getTeamBadge() + "\0", print_writers);
										CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section3$Side" + WhichSide + "$This_Over$ThisOver$BallGrp"
												+ (iBall+1) + "$Rest$img_Text2*TEXTURE*IMAGE SET " + Constants.BAN_AFG_SERIES_TEXT2 + inning.getBatting_team().getTeamBadge() + "\0", print_writers);
										
										if(this_data_str.get(this_data_str.size()-1).split(",")[iBall].contains("PN")) {
											CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section3$Side" + WhichSide + "$This_Over$ThisOver$BallGrp" 
													+ (iBall+1) + "$Rest$txt_Ball*GEOM*TEXT SET 5PN\0", print_writers);
										}else {
											CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section3$Side" + WhichSide + "$This_Over$ThisOver$BallGrp" 
													+ (iBall+1) + "$Rest$txt_Ball*GEOM*TEXT SET " + this_data_str.get(this_data_str.size()-1).split(",")[iBall] + "\0", print_writers);
										}
										break;
									}
									break;
								default:
									if(this_data_str.get(this_data_str.size()-1).split(",")[iBall].toUpperCase().contains("BOUNDARY")) {
										CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section3$Side" + WhichSide + "$This_Over$ThisOver$BallGrp" 
												+ (iBall+1) + "$SelectType*FUNCTION*Omo*vis_con SET 3\0", print_writers);
										CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section3$Side" + WhichSide + "$This_Over$ThisOver$BallGrp"
												+ (iBall+1) + "$Four_Six_WKT$img_Text2_No$txt_Ball*TEXTURE*IMAGE SET " + Constants.BAN_AFG_SERIES_BASE2 + inning.getBatting_team().getTeamBadge() + "\0", print_writers);
										
										CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section3$Side" + WhichSide + "$This_Over$ThisOver$BallGrp" 
												+ (iBall+1) + "$Four_Six_WKT$txt_Ball*GEOM*TEXT SET " + this_data_str.get(this_data_str.size()-1).split(",")[iBall].replace("BOUNDARY", "") + "\0", print_writers);
										
									}else if(!this_data_str.get(this_data_str.size()-1).isEmpty()) {
										CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section3$Side" + WhichSide + "$This_Over$ThisOver$BallGrp" 
												+ (iBall+1) + "$SelectType*FUNCTION*Omo*vis_con SET 2\0", print_writers);
										CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section3$Side" + WhichSide + "$This_Over$ThisOver$BallGrp"
												+ (iBall+1) + "$Rest$img_Base2*TEXTURE*IMAGE SET " + Constants.BAN_AFG_SERIES_BASE2 + inning.getBatting_team().getTeamBadge() + "\0", print_writers);
										CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section3$Side" + WhichSide + "$This_Over$ThisOver$BallGrp"
												+ (iBall+1) + "$Rest$img_Text2*TEXTURE*IMAGE SET " + Constants.BAN_AFG_SERIES_TEXT2 + inning.getBatting_team().getTeamBadge() + "\0", print_writers);
										
										if(this_data_str.get(this_data_str.size()-1).split(",")[iBall].contains("+")) {
											String line1 = "",line2 = "";
											String[] parts = this_data_str.get(this_data_str.size()-1).split(",")[iBall].split("\\+");
											if (parts.length == 2) {
										        line1 = parts[0];
										        line2 = parts[1];
										    } else if (parts.length == 3) {
										        line1 = parts[0];
										        line2 = parts[1] + "+" + parts[2];
										    }
											
											CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section3$Side" + WhichSide + "$This_Over$ThisOver$BallGrp" 
													+ (iBall+1) + "$Rest$txt_Ball*GEOM*TEXT SET " + line1.toUpperCase() + "+" + line2.toUpperCase() + "\0", print_writers);
										}else {
											if(this_data_str.get(this_data_str.size()-1).split(",")[iBall].contains("PN")) {
												CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section3$Side" + WhichSide + "$This_Over$ThisOver$BallGrp" 
														+ (iBall+1) + "$Rest$txt_Ball*GEOM*TEXT SET 5PN\0", print_writers);
											}else {
												CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section3$Side" + WhichSide + "$This_Over$ThisOver$BallGrp" 
														+ (iBall+1) + "$Rest$txt_Ball*GEOM*TEXT SET " + this_data_str.get(this_data_str.size()-1).split(",")[iBall] + "\0", print_writers);
											}
										}
										
										switch (this_data_str.get(this_data_str.size()-1).split(",")[iBall].toUpperCase()) {
										case "1B": case "2B": case "3B": case "4B": case "5B": case "6B":
										case "1LB": case "2LB": case "3LB": case "4LB": case "5LB": case "6LB":
											
											break;

										default:
											if(this_data_str.get(this_data_str.size()-1).split(",")[iBall].contains("NB") || 
													this_data_str.get(this_data_str.size()-1).split(",")[iBall].contains("WD")||
													this_data_str.get(this_data_str.size()-1).split(",")[iBall].contains("PN_Y")) {
												totalOverSize++;
											}
											break;
										}
									}
									break;
								}
							}
						}else {
							if(this_data_str.get(this_data_str.size()-1).split(",").length > 9 || isThisOverLimitExceed) {
								isThisOverLimitExceed = true;
								if(this_data_str.get(this_data_str.size()-1).split(",").length == 10) {
//									setThisOverAndLastOverData(print_writers, matchAllData, 2);
//									this_animation.processAnimation(Constants.FRONT, print_writers, "anim_Infobar$Section_3_Change", "START");
//									TimeUnit.MILLISECONDS.sleep(1000);
									
									setThisOverAndLastOverData(print_writers, matchAllData, 1);
//									TimeUnit.MILLISECONDS.sleep(500);
//									this_animation.processAnimation(Constants.FRONT, print_writers, "anim_Infobar$Section_3_Change", "SHOW 0.0");
								}else {
									setThisOverAndLastOverData(print_writers, matchAllData, 1);
								}
							}
						}
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section3$Side" + WhichSide + "$This_Over$ThisOverAll*FUNCTION*Omo*vis_con SET " 
								+ totalOverSize + "\0", print_writers);
						if(totalOverSize >= 6) {
							for(int i = totalOverSize+1;i<=10;i++) {
								CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section3$Side" + WhichSide + "$This_Over$ThisOver$BallGrp" 
										+ i + "$SelectType*FUNCTION*Omo*vis_con SET 0\0", print_writers);
								CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section3$Side" + WhichSide + "$This_Over$ThisOver$BallGrp"
										+ i + "$group$img_Base2*TEXTURE*IMAGE SET " + Constants.BAN_AFG_SERIES_BASE2 + inning.getBatting_team().getTeamBadge() + "\0", print_writers);
							}
						}
						infobar.setLast_this_over(this_data_str.get(this_data_str.size()-1));
						break;
					case "ECONOMY":
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section3$Side" + WhichSide + "$Select"
								+ "*FUNCTION*Omo*vis_con SET 12\0", print_writers);
						
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section3$Side" + WhichSide + "$BowlingEnd$"
								+ "img_Base02*TEXTURE*IMAGE SET " + Constants.BAN_AFG_SERIES_BASE2 + inning.getBowling_team().getTeamBadge() + "\0", print_writers);
						
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section3$Side" + WhichSide + "$BowlingEnd$txt_Head*GEOM*TEXT SET " 
								+ "ECONOMY" + "\0", print_writers);
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section3$Side" + WhichSide + "$BowlingEnd$Data1Grp$txt_Head2*GEOM*TEXT SET " 
								+ CricketFunctions.getEconomy(bowlingCard.getRuns(), (bowlingCard.getOvers()*6)+(bowlingCard.getBalls()), 2, "-") + "\0", print_writers);
						break;
					case "BOWLINGEND":
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section3$Side" + WhichSide + "$Select"
								+ "*FUNCTION*Omo*vis_con SET 12\0", print_writers);
						
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section3$Side" + WhichSide + "$BowlingEnd$"
								+ "img_Base02*TEXTURE*IMAGE SET " + Constants.BAN_AFG_SERIES_BASE2 + inning.getBowling_team().getTeamBadge() + "\0", print_writers);
						
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section3$Side" + WhichSide + "$BowlingEnd$txt_Head*GEOM*TEXT SET " 
								+ "BOWLING FROM" + "\0", print_writers);
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section3$Side" + WhichSide + "$BowlingEnd$Data1Grp$txt_Head2*GEOM*TEXT SET " 
								+ (bowlingCard.getBowling_end() == 1 ? matchAllData.getSetup().getGround().getFirst_bowling_end() : matchAllData.getSetup().getGround().getSecond_bowling_end().toUpperCase()) 
								+ " END" + "\0", print_writers);
						break;
					case CricketUtil.DOT:
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section3$Side" + WhichSide + "$Select"
								+ "*FUNCTION*Omo*vis_con SET 8\0", print_writers);
						
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section3$Side" + WhichSide + "$DotBalls$Data1Grp$"
								+ "img_Base2*TEXTURE*IMAGE SET " + Constants.BAN_AFG_SERIES_BASE2 + inning.getBowling_team().getTeamBadge() + "\0", print_writers);
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section3$Side" + WhichSide + "$DotBalls$Data1Grp$"
								+ "img_Text2*TEXTURE*IMAGE SET " + Constants.BAN_AFG_SERIES_TEXT2 + inning.getBowling_team().getTeamBadge() + "\0", print_writers);
						
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section3$Side" + WhichSide + "$DotBalls$"
								+ "txt_Head*GEOM*TEXT SET " + "DOTS THIS INNINGS" + "\0", print_writers);
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section3$Side" + WhichSide + "$DotBalls$Data1Grp$txt_Data1*GEOM*TEXT SET " 
								+ (inning.getInningNumber() == 1 ? IndexController.MatchStats.getHomeTeamScoreData().getTotalDots() : 
									IndexController.MatchStats.getAwayTeamScoreData().getTotalDots())  + "\0", print_writers);
						break;
					case CricketUtil.FOUR:
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section3$Side" + WhichSide + "$Select"
								+ "*FUNCTION*Omo*vis_con SET 7\0", print_writers);
						
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section3$Side" + WhichSide + "$TournamentFours$Data1Grp$"
								+ "img_Base2*ACTIVE SET 1\0", print_writers);
						
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section3$Side" + WhichSide + "$TournamentFours$Data1Grp$"
								+ "img_Base2*TEXTURE*IMAGE SET " + Constants.BAN_AFG_SERIES_BASE2 + inning.getBatting_team().getTeamBadge() + "\0", print_writers);
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section3$Side" + WhichSide + "$TournamentFours$Data1Grp$"
								+ "img_Text2*TEXTURE*IMAGE SET " + Constants.BAN_AFG_SERIES_TEXT2 + inning.getBatting_team().getTeamBadge() + "\0", print_writers);
						
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section3$Side" + WhichSide + "$TournamentFours$"
								+ "txt_Head*GEOM*TEXT SET " + "FOURS THIS INNINGS" + "\0", print_writers);
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section3$Side" + WhichSide + "$TournamentFours$Data1Grp"
								+ "$txt_Data1*GEOM*TEXT SET " + inning.getTotalFours()  + "\0", print_writers);
						break;
					case CricketUtil.SIX:
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section3$Side" + WhichSide + "$Select"
								+ "*FUNCTION*Omo*vis_con SET 6\0", print_writers);
						
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section3$Side" + WhichSide + "$TournamentSixes$Data1Grp$"
								+ "img_Base2*ACTIVE SET 1\0", print_writers);
						
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section3$Side" + WhichSide + "$TournamentSixes$Data1Grp$"
								+ "img_Base2*TEXTURE*IMAGE SET " + Constants.BAN_AFG_SERIES_BASE2 + inning.getBatting_team().getTeamBadge() + "\0", print_writers);
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section3$Side" + WhichSide + "$TournamentSixes$Data1Grp$"
								+ "img_Text2*TEXTURE*IMAGE SET " + Constants.BAN_AFG_SERIES_TEXT2 + inning.getBatting_team().getTeamBadge() + "\0", print_writers);
						
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section3$Side" + WhichSide + "$TournamentSixes$"
								+ "txt_Head*GEOM*TEXT SET " + "SIXES THIS INNINGS" + "\0", print_writers);
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section3$Side" + WhichSide + "$TournamentSixes$Data1Grp"
								+ "$txt_Data1*GEOM*TEXT SET " + inning.getTotalSixes()  + "\0", print_writers);
						break;
					case CricketUtil.BOUNDARY:
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section3$Side" + WhichSide + "$Select"
								+ "*FUNCTION*Omo*vis_con SET 0\0", print_writers);
						
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section3$Side" + WhichSide + "$InningBoundaries$"
								+ "img_Base02*TEXTURE*IMAGE SET " + Constants.BAN_AFG_SERIES_BASE2 + inning.getBatting_team().getTeamBadge() + "\0", print_writers);
						
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section3$Side" + WhichSide + "$InningBoundaries$Data1Grp$"
								+ "img_Base2*TEXTURE*IMAGE SET " + Constants.BAN_AFG_SERIES_BASE2 + inning.getBatting_team().getTeamBadge() + "\0", print_writers);
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section3$Side" + WhichSide + "$InningBoundaries$Data2Grp$"
								+ "img_Base2*TEXTURE*IMAGE SET " + Constants.BAN_AFG_SERIES_BASE2 + inning.getBatting_team().getTeamBadge() + "\0", print_writers);
						
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section3$Side" + WhichSide + "$InningBoundaries$Data1Grp$"
								+ "img_Text2*TEXTURE*IMAGE SET " + Constants.BAN_AFG_SERIES_TEXT2 + inning.getBatting_team().getTeamBadge() + "\0", print_writers);
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section3$Side" + WhichSide + "$InningBoundaries$Data2Grp$"
								+ "img_Text2*TEXTURE*IMAGE SET " + Constants.BAN_AFG_SERIES_TEXT2 + inning.getBatting_team().getTeamBadge() + "\0", print_writers);
						
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section3$Side" + WhichSide + "$InningBoundaries$"
								+ "txt_Head*GEOM*TEXT SET " + "INNINGS BOUNDARIES" + "\0", print_writers);
						
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section3$Side" + WhichSide + "$InningBoundaries$Data1Grp$"
								+ "txt_Head2*GEOM*TEXT SET " + "FOUR" + CricketFunctions.Plural(inning.getTotalFours()).toUpperCase() + "\0", print_writers);
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section3$Side" + WhichSide + "$InningBoundaries$Data2Grp$"
								+ "txt_Head3*GEOM*TEXT SET " + (inning.getTotalSixes() == 1 ? "SIX" : "SIXES")+ "\0", print_writers);
						
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section3$Side" + WhichSide + "$InningBoundaries$Data1Grp$"
								+ "txt_Data1*GEOM*TEXT SET " + inning.getTotalFours() + "\0", print_writers);
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section3$Side" + WhichSide + "$InningBoundaries$Data2Grp$"
								+ "txt_Data2*GEOM*TEXT SET " + inning.getTotalSixes() + "\0", print_writers);
						break;
					case "LASTXBALLS":
						this_data_str = new ArrayList<String>();
						this_data_str.add(CricketFunctions.getlastthirtyballsdata(matchAllData, slashOrDash, matchAllData.getEventFile().getEvents(), lastXballs));
						if(this_data_str.get(this_data_str.size()-1) == null || this_data_str.get(this_data_str.size()-1).split(slashOrDash).length > 4) {
							return "populateVizInfobarMiddleSection: Last " + lastXballs + " Balls data returned invalid";
						}
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section3$Side" + WhichSide + "$Select"
								+ "*FUNCTION*Omo*vis_con SET 1\0", print_writers);
						
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section3$Side" + WhichSide + "$LastXBalls$"
								+ "img_Base02*TEXTURE*IMAGE SET " + Constants.BAN_AFG_SERIES_BASE2 + inning.getBatting_team().getTeamBadge() + "\0", print_writers);
						
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section3$Side" + WhichSide + "$LastXBalls$Data1Grp$"
								+ "img_Base2*TEXTURE*IMAGE SET " + Constants.BAN_AFG_SERIES_BASE2 + inning.getBatting_team().getTeamBadge() + "\0", print_writers);
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section3$Side" + WhichSide + "$LastXBalls$Data2Grp$"
								+ "img_Base2*TEXTURE*IMAGE SET " + Constants.BAN_AFG_SERIES_BASE2 + inning.getBatting_team().getTeamBadge() + "\0", print_writers);
						
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section3$Side" + WhichSide + "$LastXBalls$Data1Grp$"
								+ "img_Text2*TEXTURE*IMAGE SET " + Constants.BAN_AFG_SERIES_TEXT2 + inning.getBatting_team().getTeamBadge() + "\0", print_writers);
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section3$Side" + WhichSide + "$LastXBalls$Data2Grp$"
								+ "img_Text2*TEXTURE*IMAGE SET " + Constants.BAN_AFG_SERIES_TEXT2 + inning.getBatting_team().getTeamBadge() + "\0", print_writers);
						
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section3$Side" + WhichSide + "$LastXBalls$"
								+ "txt_Head*GEOM*TEXT SET " + "LAST " + lastXballs + " BALLS" + "\0", print_writers);
						
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section3$Side" + WhichSide + "$LastXBalls$Data1Grp$"
								+ "txt_Head2*GEOM*TEXT SET " + "RUN" + CricketFunctions.Plural(Integer.valueOf(this_data_str.
										get(this_data_str.size()-1).split(slashOrDash)[0])).toUpperCase() + "\0", print_writers);
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section3$Side" + WhichSide + "$LastXBalls$Data2Grp$"
								+ "txt_Head3*GEOM*TEXT SET " + "WICKET" + CricketFunctions.Plural(Integer.valueOf(this_data_str.
										get(this_data_str.size()-1).split(slashOrDash)[1])).toUpperCase()+ "\0", print_writers);
						
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section3$Side" + WhichSide + "$LastXBalls$Data1Grp$"
								+ "txt_Data1*GEOM*TEXT SET " + this_data_str.get(this_data_str.size()-1).split(slashOrDash)[0] + "\0", print_writers);
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section3$Side" + WhichSide + "$LastXBalls$Data2Grp$"
								+ "txt_Data2*GEOM*TEXT SET " + this_data_str.get(this_data_str.size()-1).split(slashOrDash)[1] + "\0", print_writers);
											
						break;
					case "EXTRAS":
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section3$Side" + WhichSide + "$Select"
								+ "*FUNCTION*Omo*vis_con SET 13\0", print_writers);
						
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section3$Side" + WhichSide + "$Extras$img_Base02"
								+ "*TEXTURE*IMAGE SET " + Constants.BAN_AFG_SERIES_BASE2 + inning.getBatting_team().getTeamBadge() + "\0", print_writers);
						
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section3$Side" + WhichSide + "$Extras$txt_Head"
								+ "*GEOM*TEXT SET " + "EXTRAS - " + inning.getTotalExtras() + "\0", print_writers);
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section3$Side" + WhichSide + "$Extras$DataAll$txt_Data3"
								+ "*GEOM*TEXT SET " + "WD " + inning.getTotalWides() + " | NB " + inning.getTotalNoBalls() + " | LB " + inning.getTotalLegByes() 
								+ " | B " + inning.getTotalByes() + (inning.getTotalPenalties() > 0 ? " | PN " + inning.getTotalPenalties() : "") + "\0", print_writers);
						break;
					case "LAST_WICKET":
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section3$Side" + WhichSide + "$Select"
								+ "*FUNCTION*Omo*vis_con SET 5\0", print_writers);
						
						if(inning.getFallsOfWickets() == null && inning.getFallsOfWickets().isEmpty()) {
							return "populateVizInfobarSection5: FoW returned is EMPTY";
						}
						battingCard = inning.getBattingCard().stream().filter(bc -> bc.getPlayerId() == 
								inning.getFallsOfWickets().get(inning.getFallsOfWickets().size() - 1).getFowPlayerID()).findAny().orElse(null);
						
						if(battingCard == null) {
							return "populateVizInfobarSection5: Last wicket returned is invalid";
						}
						
						String how_out_txt = "";
						if(battingCard.getHowOut().equalsIgnoreCase(CricketUtil.RUN_OUT)) {
							if(battingCard.getWasHowOutFielderSubstitute() != null && battingCard.getWasHowOutFielderSubstitute().equalsIgnoreCase(CricketUtil.YES)) {
								how_out_txt = "run out " + "(sub " + battingCard.getHowOutFielder().getTicker_name() + ")";
							} else {
								how_out_txt = "run out (" + battingCard.getHowOutFielder().getTicker_name() + ")";
							}
						}
						else if(battingCard.getHowOut().equalsIgnoreCase(CricketUtil.CAUGHT)) {
							if(battingCard.getWasHowOutFielderSubstitute() != null && battingCard.getWasHowOutFielderSubstitute().equalsIgnoreCase(CricketUtil.YES)) {
								how_out_txt = "c " +  "(sub - " + battingCard.getHowOutFielder().getTicker_name() + ")  b " + battingCard.getHowOutBowler().getTicker_name();
							} else {
								how_out_txt = "c " + battingCard.getHowOutFielder().getTicker_name() + "  b " + battingCard.getHowOutBowler().getTicker_name();
							}
						}
						else if(battingCard.getHowOut().equalsIgnoreCase(CricketUtil.STUMPED)) {
							if(battingCard.getWasHowOutFielderSubstitute() != null && battingCard.getWasHowOutFielderSubstitute().equalsIgnoreCase(CricketUtil.YES)) {
								how_out_txt = "st " +  "(sub - " + battingCard.getHowOutFielder().getTicker_name() + ")  b " + battingCard.getHowOutBowler().getTicker_name();
							} else {
								how_out_txt = "st " + battingCard.getHowOutFielder().getTicker_name() + "  b " + battingCard.getHowOutBowler().getTicker_name();
							}
						}
						else {
							if(!battingCard.getHowOutPartOne().isEmpty()) {
								how_out_txt = battingCard.getHowOutPartOne();
							}
							if(!battingCard.getHowOutPartTwo().isEmpty()) {
								if(!how_out_txt.trim().isEmpty()) {
									how_out_txt = how_out_txt + "  " + battingCard.getHowOutPartTwo();
								}else {
									how_out_txt = battingCard.getHowOutPartTwo();
								}
							}
						}
						
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section3$Side" + WhichSide + "$LastWicket$"
								+ "txt_Head*GEOM*TEXT SET " + "LAST WICKET" + "\0", print_writers);
						
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section3$Side" + WhichSide + "$LastWicket$img_Base02"
								+ "*TEXTURE*IMAGE SET " + Constants.BAN_AFG_SERIES_BASE2 + inning.getBatting_team().getTeamBadge() + "\0", print_writers);
						
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section3$Side" + WhichSide + "$LastWicket$Data1Grp$"
								+ "img_Base2*TEXTURE*IMAGE SET " + Constants.BAN_AFG_SERIES_BASE2 + inning.getBatting_team().getTeamBadge() + "\0", print_writers);
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section3$Side" + WhichSide + "$LastWicket$Data1Grp$"
								+ "img_Text2*TEXTURE*IMAGE SET " + Constants.BAN_AFG_SERIES_TEXT2 + inning.getBatting_team().getTeamBadge() + "\0", print_writers);
						
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section3$Side" + WhichSide + "$LastWicket$Data1Grp$"
								+ "txt_Head2*GEOM*TEXT SET " + battingCard.getPlayer().getTicker_name() + "\0", print_writers);
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section3$Side" + WhichSide + "$LastWicket$Data1Grp$"
								+ "Data$img_Text2$txt_Data1*GEOM*TEXT SET " + battingCard.getRuns() + "\0", print_writers);
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section3$Side" + WhichSide + "$LastWicket$Data1Grp$"
								+ "Data$img_Text2$txt_Data2*GEOM*TEXT SET " + battingCard.getBalls() + "\0", print_writers);
						break;
						
					case "BALLS_SINCE_LAST_BOUNDARY":
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section3$Side" + WhichSide + "$Select"
								+ "*FUNCTION*Omo*vis_con SET 9\0", print_writers);
						
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section3$Side" + WhichSide + "$BallsSince$Data1Grp$"
								+ "img_Base2*TEXTURE*IMAGE SET " + Constants.BAN_AFG_SERIES_BASE2 + inning.getBatting_team().getTeamBadge() + "\0", print_writers);
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section3$Side" + WhichSide + "$BallsSince$Data1Grp$"
								+ "img_Text2*TEXTURE*IMAGE SET " + Constants.BAN_AFG_SERIES_TEXT2 + inning.getBatting_team().getTeamBadge() + "\0", print_writers);
						
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section3$Side" + WhichSide + "$BallsSince$"
								+ "txt_Head*GEOM*TEXT SET " + "BALLS SINCE LAST BOUNDARY" + "\0", print_writers);
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section3$Side" + WhichSide + "$BallsSince$Data1Grp$"
								+ "Data$img_Text2$txt_Data1*GEOM*TEXT SET " + IndexController.MatchStats.getBallsSinceLastBoundary() + "\0", print_writers);
						break;
					}
					break;
				}
			}
			break;
		case Constants.TRI_SERIES:  case Constants.MT20: case Constants.TG20:
			if(infobar.getSection5() != null && !infobar.getSection5().isEmpty()) {
				switch(infobar.getSection5().toUpperCase()) {
				case "LASTXBALLS":
					this_data_str = new ArrayList<String>();
					this_data_str.add(CricketFunctions.getlastthirtyballsdata(matchAllData, slashOrDash, matchAllData.getEventFile().getEvents(), lastXballs));
					if(this_data_str.get(this_data_str.size()-1) == null || this_data_str.get(this_data_str.size()-1).split(slashOrDash).length > 4) {
						return "populateVizInfobarMiddleSection: Last " + lastXballs + " Balls data returned invalid";
					}

					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$RightSection$Section5$Side" + WhichSide 
							+ "$Select_DataType*FUNCTION*Omo*vis_con SET 5\0", print_writers);
//					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$RightSection$Section5$Side" + WhichSide 
//							+ "$Last_X_Overs$TopText$txt_Head*GEOM*TEXT SET " + "LAST " + lastXballs + " BALLS" + "\0", print_writers);
					
					switch(config.getBroadcaster()) {
					case Constants.TG20:
						CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$RightSection$Section5$Side" + WhichSide + "$Last_X_Overs$TopText$"
								+ "select_Language*FUNCTION*Omo*vis_con SET ", config, Constants.TG20, print_writers, foreignLanguageOmo);
						foreignLanguageDataList = CricketFunctions.AssembleMultiLanguageData(CricketUtil.DICTIONARY, "", multilanguagedata,"LAST", "", null, 1,foreignLanguageDataList);
						foreignLanguageDataList = CricketFunctions.AssembleMultiLanguageData(CricketUtil.DICTIONARY, "", multilanguagedata, String.valueOf(lastXballs), "", 
								null, 2,foreignLanguageDataList);
						foreignLanguageDataList = CricketFunctions.AssembleMultiLanguageData(CricketUtil.DICTIONARY, "", multilanguagedata,"BALLS", "", null, 3,foreignLanguageDataList);
							
						foreignLanguageData.add(CricketFunctions.MergeForeignLanguageDataListToSingleObject(foreignLanguageDataList));
						CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$RightSection$Section5$Side" + WhichSide + "$Last_X_Overs$TopText$"
								+ "English$txt_Head*GEOM*TEXT SET ", config, Constants.TG20, print_writers, foreignLanguageData);
						
						CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$RightSection$Section5$Side" + WhichSide + "$Last_X_Overs$Runs_Grp$"
								+ "select_Language*FUNCTION*Omo*vis_con SET ", config, Constants.TG20, print_writers, foreignLanguageOmo);
						foreignLanguageData = CricketFunctions.AssembleMultiLanguageData(CricketUtil.DICTIONARY, "", multilanguagedata, "RUN" + CricketFunctions.Plural(Integer.valueOf(this_data_str.
								get(this_data_str.size()-1).split(slashOrDash)[0])).toUpperCase(), "", null, 0, foreignLanguageDataList);
						CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$RightSection$Section5$Side" + WhichSide + "$Last_X_Overs$Runs_Grp$"
								+ "English$txt_Header*GEOM*TEXT SET ", config, Constants.TG20, print_writers, foreignLanguageData);
						
						CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$RightSection$Section5$Side" + WhichSide + "$Last_X_Overs$Wickets_Grp$"
								+ "select_Language*FUNCTION*Omo*vis_con SET ", config, Constants.TG20, print_writers, foreignLanguageOmo);
						foreignLanguageData = CricketFunctions.AssembleMultiLanguageData(CricketUtil.DICTIONARY, "", multilanguagedata, "WICKET" + CricketFunctions.Plural(Integer.valueOf(this_data_str.
								get(this_data_str.size()-1).split(slashOrDash)[1])).toUpperCase(), "", null, 0, foreignLanguageDataList);
						CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$RightSection$Section5$Side" + WhichSide + "$Last_X_Overs$Wickets_Grp$"
								+ "English$txt_Header*GEOM*TEXT SET ", config, Constants.TG20, print_writers, foreignLanguageData);
						break;
					default:
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$RightSection$Section5$Side" + WhichSide 
								+ "$Last_X_Overs$Runs_Grp$txt_Header*GEOM*TEXT SET " + "RUN" + CricketFunctions.Plural(Integer.valueOf(this_data_str.
										get(this_data_str.size()-1).split(slashOrDash)[0])).toUpperCase() + "\0", print_writers);
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$RightSection$Section5$Side" + WhichSide 
								+ "$Last_X_Overs$Wickets_Grp$txt_Header*GEOM*TEXT SET " + "WICKET" + CricketFunctions.Plural(Integer.valueOf(this_data_str.
										get(this_data_str.size()-1).split(slashOrDash)[1])).toUpperCase() + "\0", print_writers);
						break;
					}

					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$RightSection$Section5$Side" + WhichSide 
							+ "$Last_X_Overs$Runs_Grp$txt_Figures*GEOM*TEXT SET " + this_data_str.get(this_data_str.size()-1).split(slashOrDash)[0] + "\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$RightSection$Section5$Side" + WhichSide 
							+ "$Last_X_Overs$Wickets_Grp$txt_Figures*GEOM*TEXT SET " + this_data_str.get(this_data_str.size()-1).split(slashOrDash)[1] + "\0", print_writers);
					break;
				case "COMPARE": case "RUN_RATES": case "EQUATION":
					inning = matchAllData.getMatch().getInning().stream().filter(inn -> inn.getIsCurrentInning().equalsIgnoreCase(CricketUtil.YES)
							&& inn.getInningNumber() == 2).findAny().orElse(null);
						
					if(inning == null) {
						return "populateVizInfobarRightSection: 2nd Inning returned is NULL";
					}
					
					switch(infobar.getSection5().toUpperCase()) {
					case "COMPARE":
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$RightSection$Section5$Side" + WhichSide 
								+ "$Select_DataType*FUNCTION*Omo*vis_con SET 4\0", print_writers);
						
						switch(config.getBroadcaster()) {
						case Constants.TG20:
							CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$RightSection$Section5$Side" + WhichSide + "$At_This_Stage$TopText$"
									+ "select_Language*FUNCTION*Omo*vis_con SET ", config, Constants.TG20, print_writers, foreignLanguageOmo);
							foreignLanguageData = CricketFunctions.AssembleMultiLanguageData(CricketUtil.DICTIONARY, "", multilanguagedata, "AT THIS STAGE", "", null, 0, foreignLanguageDataList);
							CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$RightSection$Section5$Side" + WhichSide + "$At_This_Stage$TopText$"
									+ "English$txt_Head*GEOM*TEXT SET ", config, Constants.TG20, print_writers, foreignLanguageData);
							
							CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$RightSection$Section5$Side" + WhichSide + "$At_This_Stage$BottomText$"
									+ "select_Language*FUNCTION*Omo*vis_con SET ", config, Constants.TG20, print_writers, foreignLanguageOmo);
							foreignLanguageDataList = CricketFunctions.AssembleMultiLanguageData(CricketUtil.TEAM, CricketUtil.TEAMNAME_4, multilanguagedata, inning.getBowling_team().getTeamName1(), 
									"", null, 1, foreignLanguageDataList);
							foreignLanguageDataList = CricketFunctions.AssembleMultiLanguageData(CricketUtil.DICTIONARY, "", multilanguagedata, "WERE", "", null, 2,foreignLanguageDataList);
							
							foreignLanguageData.add(CricketFunctions.MergeForeignLanguageDataListToSingleObject(foreignLanguageDataList));
							CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$RightSection$Section5$Side" + WhichSide + "$At_This_Stage$"
									+ "BottomText$English$txt_TeamName*GEOM*TEXT SET ", config, Constants.TG20, print_writers, foreignLanguageData);
							
							break;
						default:
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$RightSection$Section5$Side" + WhichSide 
									+ "$At_This_Stage$TopText$txt_Head*GEOM*TEXT SET " + "AT THIS STAGE" + "\0", print_writers);
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$RightSection$Section5$Side" + WhichSide 
									+ "$At_This_Stage$BottomText$txt_TeamName*GEOM*TEXT SET " + inning.getBowling_team().getTeamName1() + " WERE" + "\0", print_writers);
							break;
						}
						
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$RightSection$Section5$Side" + WhichSide 
								+ "$At_This_Stage$Figures_Grp$txt_Figures*GEOM*TEXT SET " + CricketFunctions.compareInningData(matchAllData, "-", 
										1, matchAllData.getEventFile().getEvents()) + "\0", print_writers);
						break;
					case "RUN_RATES":
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$RightSection$Section5$Side" + WhichSide 
								+ "$Select_DataType*FUNCTION*Omo*vis_con SET 2\0", print_writers);
						
						switch(config.getBroadcaster()) {
						case Constants.TG20:
							CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$RightSection$Section5$Side" + WhichSide + "$Run_Rates$Head$"
									+ "select_Language*FUNCTION*Omo*vis_con SET ", config, Constants.TG20, print_writers, foreignLanguageOmo);
							foreignLanguageData = CricketFunctions.AssembleMultiLanguageData(CricketUtil.DICTIONARY, "", multilanguagedata, "RUN RATES", "", null, 0, foreignLanguageDataList);
							CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$RightSection$Section5$Side" + WhichSide + "$Run_Rates$Head$"
									+ "English$txt_Head*GEOM*TEXT SET ", config, Constants.TG20, print_writers, foreignLanguageData);
							
							CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$RightSection$Section5$Side" + WhichSide + "$Run_Rates$Current_Grp$"
									+ "select_Language*FUNCTION*Omo*vis_con SET ", config, Constants.TG20, print_writers, foreignLanguageOmo);
							foreignLanguageData = CricketFunctions.AssembleMultiLanguageData(CricketUtil.DICTIONARY, "", multilanguagedata, "CURRENT", "", null, 0, foreignLanguageDataList);
							CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$RightSection$Section5$Side" + WhichSide + "$Run_Rates$Current_Grp$"
									+ "English$txt_Header*GEOM*TEXT SET ", config, Constants.TG20, print_writers, foreignLanguageData);
							
							CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$RightSection$Section5$Side" + WhichSide + "$Run_Rates$Required_Grp$"
									+ "select_Language*FUNCTION*Omo*vis_con SET ", config, Constants.TG20, print_writers, foreignLanguageOmo);
							foreignLanguageData = CricketFunctions.AssembleMultiLanguageData(CricketUtil.DICTIONARY, "", multilanguagedata, "REQUIRED", "", null, 0, foreignLanguageDataList);
							CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$RightSection$Section5$Side" + WhichSide + "$Run_Rates$Required_Grp$"
									+ "English$txt_Header*GEOM*TEXT SET ", config, Constants.TG20, print_writers, foreignLanguageData);
							break;
						}
						
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$RightSection$Section5$Side" + WhichSide 
								+ "$Run_Rates$Current_Grp$txt_Figures*GEOM*TEXT SET " + CricketFunctions.generateRunRate(inning.getTotalRuns(),inning.getTotalOvers(), 
										inning.getTotalBalls(), 2,matchAllData) + "\0", print_writers);
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$RightSection$Section5$Side" + WhichSide 
								+ "$Run_Rates$Required_Grp$txt_Figures*GEOM*TEXT SET " + CricketFunctions.generateRunRate(CricketFunctions.
										GetTargetData(matchAllData).getRemaningRuns(),0,CricketFunctions.GetTargetData(matchAllData).getRemaningBall(),
										2,matchAllData) + "\0", print_writers);
						break;
					case "EQUATION":
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$RightSection$Section5$Side" + WhichSide 
								+ "$Select_DataType*FUNCTION*Omo*vis_con SET 8\0", print_writers);
						
						String summary = CricketFunctions.GenerateMatchSummaryStatus(2, matchAllData, CricketUtil.FULL, "", Constants.BCCI, false).getTargetOrResult().toUpperCase();
						System.out.println(summary);

						String teamName = summary.split("NEED")[0].trim();
						String afterNeed = summary.split("NEED")[1].trim();
						String runs = afterNeed.split("RUNS")[0].trim();
						String balls = summary.split("FROM")[1].trim().split("BALLS")[0].trim();
						
						foreignLanguageDataList = CricketFunctions.AssembleMultiLanguageData(CricketUtil.TEAM, "", multilanguagedata,teamName, "", null, 1, foreignLanguageDataList);

						List<String> inserts = Arrays.asList(balls, runs);  // [0]=balls → before బాల్స్‌లో, [1]=runs → before పరుగులు
						foreignLanguageDataList = CricketFunctions.AssembleMultiLanguageData(CricketUtil.DICTIONARY, "", multilanguagedata, "NEED RUNS TO WIN FROM BALLS",
						        "", inserts, 2, foreignLanguageDataList);
						// Step 5: Merge and store
						ForeignLanguageData merged = CricketFunctions.MergeForeignLanguageDataListToSingleObject(foreignLanguageDataList);
						foreignLanguageData.add(merged);

						System.out.println("EQUATION English : " + merged.getEnglishText());
						System.out.println("EQUATION Telugu  : " + merged.getTeluguText());

						
//						CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$RightSection$Section5$Side" + WhichSide + "$Free_Text$TopText$"
//								+ "select_Language*FUNCTION*Omo*vis_con SET ", config, Constants.TG20, print_writers, foreignLanguageOmo);
//						foreignLanguageDataList = CricketFunctions.AssembleMultiLanguageData(CricketUtil.DICTIONARY, "", multilanguagedata,"TO WIN", "", null, 1,foreignLanguageDataList);
//						foreignLanguageDataList = CricketFunctions.AssembleMultiLanguageData(CricketUtil.DICTIONARY, "", multilanguagedata," - ", "", null, 2,foreignLanguageDataList);
//						foreignLanguageDataList = CricketFunctions.AssembleMultiLanguageData(CricketUtil.TEAM, "", multilanguagedata, inning.getBatting_team().getTeamName1(), "", null, 
//								3,foreignLanguageDataList);
//						foreignLanguageDataList = CricketFunctions.AssembleMultiLanguageData(CricketUtil.DICTIONARY, "", multilanguagedata,"NEED", "", null, 4,foreignLanguageDataList);
//						
//						foreignLanguageData.add(CricketFunctions.MergeForeignLanguageDataListToSingleObject(foreignLanguageDataList));
//						CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$RightSection$Section5$Side" + WhichSide + "$Free_Text$TopText$"
//								+ "English$txt_Head*GEOM*TEXT SET ", config, Constants.TG20, print_writers, foreignLanguageData);
//						
//						CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$RightSection$Section5$Side" + WhichSide + "$Free_Text$BottomText$"
//								+ "select_Language*FUNCTION*Omo*vis_con SET ", config, Constants.TG20, print_writers, foreignLanguageOmo);
//						
//						foreignLanguageDataList = CricketFunctions.AssembleMultiLanguageData(CricketUtil.DICTIONARY, "", multilanguagedata, String.valueOf(CricketFunctions.
//								GetTargetData(matchAllData).getRemaningRuns()), "", null, 1,foreignLanguageDataList);
//						foreignLanguageDataList = CricketFunctions.AssembleMultiLanguageData(CricketUtil.DICTIONARY, "", multilanguagedata, "RUN" 
//								+ CricketFunctions.Plural(CricketFunctions.GetTargetData(matchAllData).getRemaningRuns()).toUpperCase(), "", null, 2,foreignLanguageDataList);
//						foreignLanguageDataList = CricketFunctions.AssembleMultiLanguageData(CricketUtil.DICTIONARY, "", multilanguagedata,"FROM", "", null, 3,foreignLanguageDataList);
//						foreignLanguageDataList = CricketFunctions.AssembleMultiLanguageData(CricketUtil.DICTIONARY, "", multilanguagedata, String.valueOf(CricketFunctions.
//								GetTargetData(matchAllData).getRemaningBall()), "", null, 4,foreignLanguageDataList);
//						foreignLanguageDataList = CricketFunctions.AssembleMultiLanguageData(CricketUtil.DICTIONARY, "", multilanguagedata,"BALL" + 
//								CricketFunctions.Plural(CricketFunctions.GetTargetData(matchAllData).getRemaningBall()).toUpperCase(), "", null, 5,foreignLanguageDataList);
//						
//						foreignLanguageData.add(CricketFunctions.MergeForeignLanguageDataListToSingleObject(foreignLanguageDataList));
//						CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$RightSection$Section5$Side" + WhichSide + "$Free_Text$BottomText$"
//								+ "English$txt_Head*GEOM*TEXT SET ", config, Constants.TG20, print_writers, foreignLanguageData);
						break;
					}
					break;
					
				case CricketUtil.BOUNDARY: case "EXTRAS": case "LAST_WICKET": case "BALLS_SINCE_LAST_BOUNDARY": case "CURR_PARTNERSHIP":
					inning = matchAllData.getMatch().getInning().stream().filter(inn -> inn.getIsCurrentInning().equalsIgnoreCase(CricketUtil.YES)).findAny().orElse(null);
					if(inning == null) {
						return "populateVizInfobarMiddleSection: Inning returned is NULL";
					}
					switch(infobar.getSection5().toUpperCase()) {
					case CricketUtil.BOUNDARY:
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$RightSection$Section5$Side" + WhichSide 
								+ "$Select_DataType*FUNCTION*Omo*vis_con SET 0\0", print_writers);
						
						switch(config.getBroadcaster()) {
						case Constants.TG20:
							CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$RightSection$Section5$Side" + WhichSide + "$Boundaries$"
									+ "select_Language*FUNCTION*Omo*vis_con SET ", config, Constants.TG20, print_writers, foreignLanguageOmo);
							foreignLanguageData = CricketFunctions.AssembleMultiLanguageData(CricketUtil.DICTIONARY, "", multilanguagedata, "BOUNDARIES", "", null, 0, foreignLanguageDataList);
							CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$RightSection$Section5$Side" + WhichSide + "$Boundaries$"
									+ "English$txt_Head*GEOM*TEXT SET ", config, Constants.TG20, print_writers, foreignLanguageData);
							
							CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$RightSection$Section5$Side" + WhichSide + "$Boundaries$Fours_Grp$"
									+ "select_Language*FUNCTION*Omo*vis_con SET ", config, Constants.TG20, print_writers, foreignLanguageOmo);
							foreignLanguageData = CricketFunctions.AssembleMultiLanguageData(CricketUtil.DICTIONARY, "", multilanguagedata, "FOUR" + CricketFunctions.Plural(inning.getTotalFours()).toUpperCase(), 
									"", null, 0, foreignLanguageDataList);
							CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$RightSection$Section5$Side" + WhichSide + "$Boundaries$Fours_Grp$"
									+ "English$txt_Head*GEOM*TEXT SET ", config, Constants.TG20, print_writers, foreignLanguageData);
							
							CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$RightSection$Section5$Side" + WhichSide + "$Boundaries$Six_Grp$"
									+ "select_Language*FUNCTION*Omo*vis_con SET ", config, Constants.TG20, print_writers, foreignLanguageOmo);
							foreignLanguageData = CricketFunctions.AssembleMultiLanguageData(CricketUtil.DICTIONARY, "", multilanguagedata, (inning.getTotalSixes() == 1 ? "SIX" : "SIXES"), "", 
									null, 0, foreignLanguageDataList);
							CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$RightSection$Section5$Side" + WhichSide + "$Boundaries$Six_Grp$"
									+ "English$txt_Head*GEOM*TEXT SET ", config, Constants.TG20, print_writers, foreignLanguageData);
							break;
						default:
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$RightSection$Section5$Side" + WhichSide 
									+ "$Boundaries$Fours_Grp$txt_Head*GEOM*TEXT SET " + "FOUR" + CricketFunctions.Plural(inning.getTotalFours()).toUpperCase() + "\0", print_writers);
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$RightSection$Section5$Side" + WhichSide 
									+ "$Boundaries$Six_Grp$txt_Head*GEOM*TEXT SET " + (inning.getTotalSixes() == 1 ? "SIX" : "SIXES")+ "\0", print_writers);
							break;
						}
						
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$RightSection$Section5$Side" + WhichSide 
								+ "$Boundaries$Fours_Grp$txt_Figures*GEOM*TEXT SET " + inning.getTotalFours() + "\0", print_writers);
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$RightSection$Section5$Side" + WhichSide 
								+ "$Boundaries$Six_Grp$txt_Figures*GEOM*TEXT SET " + inning.getTotalSixes() + "\0", print_writers);
						break;
					case "EXTRAS":
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$RightSection$Section5$Side" + WhichSide 
								+ "$Select_DataType*FUNCTION*Omo*vis_con SET 1\0", print_writers);
						
						switch(config.getBroadcaster()) {
						case Constants.TG20:
							CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$RightSection$Section5$Side" + WhichSide + "$Extras$"
									+ "select_Language*FUNCTION*Omo*vis_con SET ", config, Constants.TG20, print_writers, foreignLanguageOmo);
							foreignLanguageData = CricketFunctions.AssembleMultiLanguageData(CricketUtil.DICTIONARY, "", multilanguagedata, "EXTRAS", "", null, 0, foreignLanguageDataList);
							CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$RightSection$Section5$Side" + WhichSide + "$Extras$"
									+ "English$txt_Head*GEOM*TEXT SET ", config, Constants.TG20, print_writers, foreignLanguageData);
							break;
						default:
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$RightSection$Section5$Side" + WhichSide 
									+ "$Extras$txt_Head*GEOM*TEXT SET " + "BALLS SINCE LAST BOUNDARY" + "\0", print_writers);
							break;
						}
						
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$RightSection$Section5$Side" + WhichSide 
								+ "$Extras$Figures_Grp$txt_Figures*GEOM*TEXT SET " + inning.getTotalExtras() + "\0", print_writers);
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$RightSection$Section5$Side" + WhichSide 
								+ "$Extras$Details_Grp$txt_Details*GEOM*TEXT SET WD " + inning.getTotalWides() + " | NB " + inning.getTotalNoBalls() + " | LB " 
								+ inning.getTotalLegByes() + " | B " + inning.getTotalByes() + (inning.getTotalPenalties() > 0 ? " | PN " + inning.getTotalPenalties() 
								: "") + "\0", print_writers);
						break;
					case "LAST_WICKET":
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$RightSection$Section5$Side" + WhichSide 
								+ "$Select_DataType*FUNCTION*Omo*vis_con SET 3\0", print_writers);
						
						if(inning.getFallsOfWickets() == null && inning.getFallsOfWickets().isEmpty()) {
							return "populateVizInfobarSection5: FoW returned is EMPTY";
						}
						battingCard = inning.getBattingCard().stream().filter(bc -> bc.getPlayerId() == 
								inning.getFallsOfWickets().get(inning.getFallsOfWickets().size() - 1).getFowPlayerID()).findAny().orElse(null);
						
						if(battingCard == null) {
							return "populateVizInfobarSection5: Last wicket returned is invalid";
						}
						
						switch(config.getBroadcaster()) {
						case Constants.TG20:
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
							
							CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$RightSection$Section5$Side" + WhichSide + "$Last_Wicket$"
									+ "TopText$select_Language*FUNCTION*Omo*vis_con SET ", config, Constants.TG20, print_writers, foreignLanguageOmo);
							foreignLanguageData = CricketFunctions.AssembleMultiLanguageData(CricketUtil.DICTIONARY, "", multilanguagedata, "LAST WICKET", "", null, 0, foreignLanguageDataList);
							CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$RightSection$Section5$Side" + WhichSide + "$Last_Wicket$"
									+ "TopText$English$txt_Head*GEOM*TEXT SET ", config, Constants.TG20, print_writers, foreignLanguageData);
							
							CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$RightSection$Section5$Side" + WhichSide + "$Last_Wicket$"
									+ "BottomText$select_Language*FUNCTION*Omo*vis_con SET ", config, Constants.TG20, print_writers, foreignLanguageOmo);
							foreignLanguageData = CricketFunctions.AssembleMultiLanguageData(CricketUtil.PLAYER, CricketUtil.TICKERNAME, multilanguagedata, battingCard.getPlayer().getTicker_name(), 
									"", null, 0, foreignLanguageDataList);
							CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$RightSection$Section5$Side" + WhichSide + "$Last_Wicket$"
									+ "BottomText$English$txt_Name*GEOM*TEXT SET ", config, Constants.TG20, print_writers, foreignLanguageData);
							
							foreignLanguageData.add(new ForeignLanguageData(howOut.getEnglishText().replace("|", ""), howOut.getHindiText().replace("|", ""), howOut.getTamilText().replace("|", ""), 
									howOut.getTeluguText().replace("|", "")));
							CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$RightSection$Section5$Side" + WhichSide + "$Last_Wicket$"
									+ "BottomText$English$txt_HowOut*GEOM*TEXT SET ", config, Constants.TG20, print_writers, foreignLanguageData);
							break;
						default:
							String how_out_txt = "";
							if(battingCard.getHowOut().equalsIgnoreCase(CricketUtil.RUN_OUT)) {
								if(battingCard.getWasHowOutFielderSubstitute() != null && battingCard.getWasHowOutFielderSubstitute().equalsIgnoreCase(CricketUtil.YES)) {
									how_out_txt = "run out " + "(sub " + battingCard.getHowOutFielder().getTicker_name() + ")";
								} else {
									how_out_txt = "run out (" + battingCard.getHowOutFielder().getTicker_name() + ")";
								}
							}
							else if(battingCard.getHowOut().equalsIgnoreCase(CricketUtil.CAUGHT)) {
								if(battingCard.getWasHowOutFielderSubstitute() != null && battingCard.getWasHowOutFielderSubstitute().equalsIgnoreCase(CricketUtil.YES)) {
									how_out_txt = "c " +  "(sub - " + battingCard.getHowOutFielder().getTicker_name() + ")  b " + battingCard.getHowOutBowler().getTicker_name();
								} else {
									how_out_txt = "c " + battingCard.getHowOutFielder().getTicker_name() + "  b " + battingCard.getHowOutBowler().getTicker_name();
								}
							}
							else if(battingCard.getHowOut().equalsIgnoreCase(CricketUtil.STUMPED)) {
								if(battingCard.getWasHowOutFielderSubstitute() != null && battingCard.getWasHowOutFielderSubstitute().equalsIgnoreCase(CricketUtil.YES)) {
									how_out_txt = "st " +  "(sub - " + battingCard.getHowOutFielder().getTicker_name() + ")  b " + battingCard.getHowOutBowler().getTicker_name();
								} else {
									how_out_txt = "st " + battingCard.getHowOutFielder().getTicker_name() + "  b " + battingCard.getHowOutBowler().getTicker_name();
								}
							}
							else {
								if(!battingCard.getHowOutPartOne().isEmpty()) {
									how_out_txt = battingCard.getHowOutPartOne();
								}
								if(!battingCard.getHowOutPartTwo().isEmpty()) {
									if(!how_out_txt.trim().isEmpty()) {
										how_out_txt = how_out_txt + "  " + battingCard.getHowOutPartTwo();
									}else {
										how_out_txt = battingCard.getHowOutPartTwo();
									}
								}
							}
							
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$RightSection$Section5$Side" + WhichSide 
									+ "$Last_Wicket$TopText$txt_Head*GEOM*TEXT SET " + "LAST WICKET" + "\0", print_writers);
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$RightSection$Section5$Side" + WhichSide 
									+ "$Last_Wicket$BottomText$txt_Name*GEOM*TEXT SET " + battingCard.getPlayer().getTicker_name() + "\0", print_writers);
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$RightSection$Section5$Side" + WhichSide 
									+ "$Last_Wicket$BottomText$txt_HowOut*GEOM*TEXT SET " + how_out_txt + "\0", print_writers);
							break;
						}
						
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$RightSection$Section5$Side" + WhichSide 
								+ "$Last_Wicket$ScoreGrp$txt_Runs*GEOM*TEXT SET " + battingCard.getRuns() + "\0", print_writers);
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$RightSection$Section5$Side" + WhichSide 
								+ "$Last_Wicket$ScoreGrp$txt_Balls*GEOM*TEXT SET " + battingCard.getBalls() + " BALL" + CricketFunctions.Plural(battingCard.
										getBalls()).toUpperCase() + "\0", print_writers);
						break;
					case "BALLS_SINCE_LAST_BOUNDARY":
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$RightSection$Section5$Side" + WhichSide 
								+ "$Select_DataType*FUNCTION*Omo*vis_con SET 6\0", print_writers);
						
						switch(config.getBroadcaster()) {
						case Constants.TG20:
							CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$RightSection$Section5$Side" + WhichSide + "$Balls_Since$"
									+ "select_Language*FUNCTION*Omo*vis_con SET ", config, Constants.TG20, print_writers, foreignLanguageOmo);
							foreignLanguageData = CricketFunctions.AssembleMultiLanguageData(CricketUtil.DICTIONARY, "", multilanguagedata, "BALLS SINCE LAST BOUNDARY", "", null, 0, foreignLanguageDataList);
							CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$RightSection$Section5$Side" + WhichSide + "$Balls_Since$"
									+ "English$txt_Head*GEOM*TEXT SET ", config, Constants.TG20, print_writers, foreignLanguageData);
							break;
						default:
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$RightSection$Section5$Side" + WhichSide 
									+ "$Balls_Since$txt_Head*GEOM*TEXT SET " + "BALLS SINCE LAST BOUNDARY" + "\0", print_writers);
							break;
						}
						
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$RightSection$Section5$Side" + WhichSide 
								+ "$Balls_Since$Figures_Grp$txt_Figures*GEOM*TEXT SET " + CricketFunctions.lastFewOversData(CricketUtil.BOUNDARY, 
										matchAllData.getEventFile().getEvents(), inning.getInningNumber()) + "\0", print_writers);
						break;
					case "CURR_PARTNERSHIP":
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$RightSection$Section5$Side" + WhichSide 
								+ "$Select_DataType*FUNCTION*Omo*vis_con SET 7\0", print_writers);
						
						switch(config.getBroadcaster()) {
						case Constants.TG20:
							CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$RightSection$Section5$Side" + WhichSide + "$Partnership$"
									+ "select_Language*FUNCTION*Omo*vis_con SET ", config, Constants.TG20, print_writers, foreignLanguageOmo);
							foreignLanguageData = CricketFunctions.AssembleMultiLanguageData(CricketUtil.DICTIONARY, "", multilanguagedata, "CURRENT PARTNERSHIP", "", null, 0, foreignLanguageDataList);
							CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$RightSection$Section5$Side" + WhichSide + "$Partnership$"
									+ "English$txt_Head*GEOM*TEXT SET ", config, Constants.TG20, print_writers, foreignLanguageData);
							break;
						default:
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$RightSection$Section5$Side" + WhichSide 
									+ "$Partnership$txt_Head*GEOM*TEXT SET " + "CURRENT PARTNERSHIP" + "\0", print_writers);
							break;
						}
						
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$RightSection$Section5$Side" + WhichSide 
								+ "$Partnership$Figures_Grp$txt_Runs*GEOM*TEXT SET " + inning.getPartnerships().get(inning.getPartnerships().size()-1).getTotalRuns() 
								+ "*" + "\0", print_writers);
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$RightSection$Section5$Side" + WhichSide 
								+ "$Partnership$Figures_Grp$txt_Balls*GEOM*TEXT SET " + inning.getPartnerships().get(inning.getPartnerships().size()-1).getTotalBalls() 
								+ " BALL" + CricketFunctions.Plural(inning.getPartnerships().get(inning.getPartnerships().size()-1).getTotalBalls()).toUpperCase() 
								+ "\0", print_writers);
						
						if(photoCategory.equalsIgnoreCase("WithoutPhoto")) {
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$RightSection$Section5$Side" + WhichSide 
									+ "$Partnership$Image_Grp*ACTIVE SET 0\0", print_writers);
						}else {
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$RightSection$Section5$Side" + WhichSide 
									+ "$Partnership$Image_Grp*ACTIVE SET 1\0", print_writers);
							
							String basePath = "";
							
							switch(config.getBroadcaster()) {
							case Constants.TG20:
								basePath = (config.getPrimaryIpAddress().equalsIgnoreCase(Constants.LOCALHOST) ? Constants.TG20_LOCAL_PHOTO_PATH : "\\\\" + config.getPrimaryIpAddress() 
									+ "\\\\" + Constants.TG20_PHOTO_PATH);
								break;
							default:
								basePath = (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? (config.getPrimaryIpAddress().equalsIgnoreCase(Constants.LOCALHOST) ? 
										Constants.TRI_SERIES_LOCAL_PHOTO_PATH : "\\\\" + config.getPrimaryIpAddress() + "\\\\" + Constants.TRI_SERIES_PHOTO_PATH) : 
										(config.getPrimaryIpAddress().equalsIgnoreCase(Constants.LOCALHOST) ? Constants.MT20_LOCAL_PHOTO_PATH : "\\\\" + config.getPrimaryIpAddress() 
										+ "\\\\" + Constants.MT20_PHOTO_PATH));
								break;
							}
							
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$RightSection$Section5$Side" + WhichSide + 
									"$Partnership$Image_Grp$Image1$img_Player*TEXTURE*IMAGE SET " + basePath + Constants.BLANK + CricketUtil.PNG_EXTENSION + "\0", print_writers);
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$RightSection$Section5$Side" + WhichSide + 
									"$Partnership$Image_Grp$Image1$img_PlayerShadow*TEXTURE*IMAGE SET " + basePath + Constants.BLANK + CricketUtil.PNG_EXTENSION + "\0", print_writers);
							
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$RightSection$Section5$Side" + WhichSide + 
									"$Partnership$Image_Grp$Image2$img_Player*TEXTURE*IMAGE SET " + basePath + Constants.BLANK + CricketUtil.PNG_EXTENSION + "\0", print_writers);
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$RightSection$Section5$Side" + WhichSide + 
									"$Partnership$Image_Grp$Image2$img_PlayerShadow*TEXTURE*IMAGE SET " + basePath + Constants.BLANK + CricketUtil.PNG_EXTENSION + "\0", print_writers);
						    
							String team = inning.getBatting_team().getTeamName4();
							Partnership lastPartnership = inning.getPartnerships().get(inning.getPartnerships().size() - 1);

							String[] photos = {lastPartnership.getFirstPlayer().getPhoto(),lastPartnership.getSecondPlayer().getPhoto()};
							String[] imageGroups = { "Image1", "Image2" };

							for (int i = 0; i < photos.length; i++) {
							    String fullPath = basePath + "\\\\" + team + "\\\\" + Constants.RIGHT + "\\\\" + photos[i] + CricketUtil.PNG_EXTENSION;
							    String vizPath = "-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$RightSection$Section5$Side"
							            + WhichSide + "$Partnership$Image_Grp$" + imageGroups[i];

							    CricketFunctions.DoadWriteCommandToAllViz(vizPath + "$img_Player*TEXTURE*IMAGE SET " + fullPath + "\0", print_writers);
							    CricketFunctions.DoadWriteCommandToAllViz(vizPath + "$img_PlayerShadow*TEXTURE*IMAGE SET " + fullPath + "\0", print_writers);
							}
						}
						break;
					}
					break;
				}
			}
			break;
		}
		return Constants.OK;
	}
	public String populateSectionAnalytics(List<PrintWriter> print_writers, MatchAllData matchAllData, int WhichSide) throws JsonMappingException, JsonProcessingException, InterruptedException {
		switch(config.getBroadcaster()) {
		case Constants.ACC:
			if(infobar.getSectionAnalytics() != null && !infobar.getSectionAnalytics().isEmpty()) {
				switch(infobar.getSectionAnalytics().toUpperCase()) {
				case "FREETEXT":
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section4$Side" + WhichSide + "$Select*FUNCTION*Omo*vis_con SET 1\0", print_writers);
					
					if(freeText.split(",").length == 5) {
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section4$Side" + WhichSide + 
								"$Freetext$txt_Head*GEOM*TEXT SET " + freeText.split(",")[3] + " " + freeText.split(",")[4] + "\0", print_writers);
					}else if(freeText.split(",").length == 4) {
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section4$Side" + WhichSide + 
								"$Freetext$txt_Head*GEOM*TEXT SET " + freeText.split(",")[3] + "\0", print_writers);
					}
					
					break;
				case "FREETEXTDB":
					InfobarStats infoStat = infobarStats.get(infobarStatsId-1);
					if(infoStat == null) {
						return "populateSectionAnalytics: infoStat null";
					}
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section4$Side" + WhichSide + "$Select*FUNCTION*Omo*vis_con SET 1\0", print_writers);
					
					if(infoStat.getText2() != null) {
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section4$Side" + WhichSide + 
								"$Freetext$txt_Head*GEOM*TEXT SET " + infoStat.getText1() + " " + infoStat.getText2() + "\0", print_writers);
					}else if(freeText.split(",").length == 4) {
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section4$Side" + WhichSide + 
								"$Freetext$txt_Head*GEOM*TEXT SET " + infoStat.getText1() + "\0", print_writers);
					}
					break;
				case "COMMENTATORS":
					String Data = "";
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section4$Side" + WhichSide + "$Select*FUNCTION*Omo*vis_con SET 1\0", print_writers);
					
					String[] parts = Comms_Name.split(",");
					List<String> names = new ArrayList<>();

					for (int i = 0; i < parts.length; i++) {
					    if (Integer.parseInt(parts[i]) > 0) {
					        names.add(Commentators.get(Integer.parseInt(parts[i]) - 1).getCommentatorName());
					    }
					}

					if (names.size() > 1) {
					    Data = String.join(" | ", names);
					} else {
					    Data = String.join("", names);
					}
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section4$Side" + WhichSide + 
							"$Freetext$txt_Head*GEOM*TEXT SET " + "COMMENTATORS - " + Data + "\0", print_writers);
					break;
				case "DLS":
					this_data_str = new ArrayList<String>();
					
					if(dls == null) {
						return "populateVizInfobarMiddleSection: DLS is NULL";
					}
					
					for(int i = 0; i<= dls.size() -1;i++) {
						if(dls.get(i).getOver_left().split("\\.")[0].equalsIgnoreCase(String.valueOf(inning.getTotalOvers()))) {
							for(int j=0;j<6;j++) {
								if(inning.getTotalBalls() == j) {
									this_data_str.add(CricketFunctions.populateDuckWorthLewis(matchAllData, CricketUtil.CRICKET_DIRECTORY).get(i+j).getWkts_down());
									break;
								}
							}
							break;
						}
					}
					
					if(CricketFunctions.populateDls(matchAllData, CricketUtil.FULL, Integer.valueOf(this_data_str.get(0))).trim().isEmpty()) {
						return "populateVizInfobarFullSection: populateDls Function is Empty";
					}
					
					this_data_str.add(CricketFunctions.populateDls(matchAllData, CricketUtil.SHORT, Integer.valueOf(this_data_str.get(0))));
					
					if(this_data_str == null) {
						return "populateVizInfobarMiddleSection this_data_str is null";
					}
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section4$Side" + WhichSide + "$Select*FUNCTION*Omo*vis_con SET 1\0", print_writers);
//					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section4$Side" + WhichSide + 
//							"$Freetext$txt_Head*GEOM*TEXT SET " + "DLS PAR SCORE AFTER " + CricketFunctions.OverBalls(inning.getTotalOvers(), inning.getTotalBalls()) + " OVERS - " 
//							+ this_data_str.get(0) + "\0", print_writers);
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section4$Side" + WhichSide + 
							"$Freetext$txt_Head*GEOM*TEXT SET " + "DLS PAR SCORE - " + this_data_str.get(0) + " | " + this_data_str.get(1).toUpperCase() + "\0", print_writers);
					
					break;	
				case "SPONSOR":
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section4$Side" + WhichSide + "$"
							+ "Select*FUNCTION*Omo*vis_con SET 2\0", print_writers);
					
					for(Sponsor sp : cricketService.getSponsor()) {
						if(sp.getSponsorId() == sponsor_id) {
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section4$Side" + WhichSide + "$Freetext_Image$Sponsor*TEXTURE*IMAGE SET " 
									+ (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_SPONSOR : Constants.MT20_SPONSOR) + sp.getImagename() 
									+ "\0", print_writers);
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section4$Side" + WhichSide 
									+ "$Freetext_Image$txt_Head*GEOM*TEXT SET " + sp.getText() + "\0", print_writers);
						}
					}
					break;
				case CricketUtil.PROJECTED:
					inning = matchAllData.getMatch().getInning().stream().filter(inn -> inn.getInningNumber() == 1 &&
					inn.getIsCurrentInning().equalsIgnoreCase(CricketUtil.YES)).findAny().orElse(null);

					if(inning == null) {
						return "populateSectionAnalytics: 1st Inning returned is NULL";
					}
					this_data_str = CricketFunctions.projectedScore(matchAllData);
					
					if(this_data_str.size() <= 0) {
						return "populateSectionAnalytics: Projected score invalid";
					}
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section4$Side" + WhichSide + "$Select"
							+ "*FUNCTION*Omo*vis_con SET 5\0", print_writers);
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section4$Side" + WhichSide + "$ProjectedScore$"
							+ "txt_Head*GEOM*TEXT SET " + "PROJECTED SCORES" + "\0", print_writers);
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section4$Side" + WhichSide + "$ProjectedScore$Data1Grp$"
							+ "txt_Head2*GEOM*TEXT SET " + "@CRR (" + this_data_str.get(0) + ")" + "\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section4$Side" + WhichSide + "$ProjectedScore$Data2Grp$"
							+ "txt_Head3*GEOM*TEXT SET " + "@"+String.format("%.2f", Double.parseDouble(this_data_str.get(2))) + "\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section4$Side" + WhichSide + "$ProjectedScore$Data3Grp$"
							+ "txt_Head3*GEOM*TEXT SET " + "@"+String.format("%.2f", Double.parseDouble(this_data_str.get(4))) + "\0", print_writers);
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section4$Side" + WhichSide + "$ProjectedScore$Data1Grp$"
							+ "txt_Data1*GEOM*TEXT SET " + this_data_str.get(1) + "\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section4$Side" + WhichSide + "$ProjectedScore$Data2Grp$"
							+ "txt_Data2*GEOM*TEXT SET " + this_data_str.get(3) + "\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section4$Side" + WhichSide + "$ProjectedScore$Data3Grp$"
							+ "txt_Data2*GEOM*TEXT SET " + this_data_str.get(5) + "\0", print_writers);
					break;
				case "PHASE_WISE_SCORE":
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section4$Side" + WhichSide + "$Select"
							+ "*FUNCTION*Omo*vis_con SET 6\0", print_writers);
					
					int oneToTenRuns = 0,thirtyOneToFourtyRuns = 0,fourtyOneToFiftyRuns = 0,oneToTenWkt = 0,thirtyOneToFourtyWkt = 0,fourtyOneToFiftyWkt = 0;
					
					if(inning.getInningNumber()==1) {
						List<OverByOverData> overByOverData = CricketFunctions.getOverByOverData(matchAllData, 1, "MANHATTAN", matchAllData.getEventFile().getEvents());
						for(int j=1; j<=overByOverData.size()-1; j++) {
							if(j>0 && j<=10) {
								oneToTenRuns+= overByOverData.get(j).getOverTotalRuns();
								oneToTenWkt+=overByOverData.get(j).getOverTotalWickets();
							}
							if(j>10 && j<=40) {
								thirtyOneToFourtyRuns+= overByOverData.get(j).getOverTotalRuns();
								thirtyOneToFourtyWkt+=overByOverData.get(j).getOverTotalWickets();
							}
							if(j>40 && j<=50) {
								fourtyOneToFiftyRuns+= overByOverData.get(j).getOverTotalRuns();
								fourtyOneToFiftyWkt+=overByOverData.get(j).getOverTotalWickets();
							}
						}
						phaseWiseScore = oneToTenRuns+","+oneToTenWkt+"_" + thirtyOneToFourtyRuns+","+thirtyOneToFourtyWkt
								+"_" + fourtyOneToFiftyRuns+","+fourtyOneToFiftyWkt;
						
						
//						phaseWiseScore = IndexController.MatchStats.getHomeFirstPowerPlay().getTotalRuns()+","+IndexController.MatchStats.getHomeFirstPowerPlay().getTotalWickets()+"_"+
//										 IndexController.MatchStats.getHomeSecondPowerPlay().getTotalRuns()+","+IndexController.MatchStats.getHomeSecondPowerPlay().getTotalWickets()+"_"
//										 +IndexController.MatchStats.getHomeThirdPowerPlay().getTotalRuns()+","+IndexController.MatchStats.getHomeThirdPowerPlay().getTotalWickets();
					}else if(inning.getInningNumber()==2) {
						List<OverByOverData> overByOverData = CricketFunctions.getOverByOverData(matchAllData, 1, "MANHATTAN", matchAllData.getEventFile().getEvents());
						for(int j=1; j<=overByOverData.size()-1; j++) {
							if(j>0 && j<=10) {
								oneToTenRuns+= overByOverData.get(j).getOverTotalRuns();
								oneToTenWkt+=overByOverData.get(j).getOverTotalWickets();
							}
							if(j>10 && j<=40) {
								thirtyOneToFourtyRuns+= overByOverData.get(j).getOverTotalRuns();
								thirtyOneToFourtyWkt+=overByOverData.get(j).getOverTotalWickets();
							}
							if(j>40 && j<=50) {
								fourtyOneToFiftyRuns+= overByOverData.get(j).getOverTotalRuns();
								fourtyOneToFiftyWkt+=overByOverData.get(j).getOverTotalWickets();
							}
						}
						phaseWiseScore = oneToTenRuns+","+oneToTenWkt+"_" + thirtyOneToFourtyRuns+","+thirtyOneToFourtyWkt
								+"_" + fourtyOneToFiftyRuns+","+fourtyOneToFiftyWkt;
						
//						phaseWiseScore = IndexController.MatchStats.getAwayFirstPowerPlay().getTotalRuns()+","+IndexController.MatchStats.getAwayFirstPowerPlay().getTotalWickets()+"_"+
//								 IndexController.MatchStats.getAwaySecondPowerPlay().getTotalRuns()+","+IndexController.MatchStats.getAwaySecondPowerPlay().getTotalWickets()+"_"
//								 +IndexController.MatchStats.getAwayThirdPowerPlay().getTotalRuns()+","+IndexController.MatchStats.getAwayThirdPowerPlay().getTotalWickets();
					}
					
					String PP1 ="-",PP2="-",PP3="-";
					
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section4$Side" + WhichSide + "$PhaseWiseScore$"
							+ "txt_Head*GEOM*TEXT SET " + "PHASE-WISE SCORES" + "\0", print_writers);
					
					if(matchAllData.getSetup().getMatchType().equalsIgnoreCase(CricketUtil.OD) || 
							matchAllData.getSetup().getMatchType().equalsIgnoreCase(CricketUtil.ODI)) {
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section4$Side" + WhichSide + "$PhaseWiseScore$Data1Grp$"
								+ "txt_Head2*GEOM*TEXT SET " + "OVERS 1-10" + "\0", print_writers);
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section4$Side" + WhichSide + "$PhaseWiseScore$Data2Grp$"
								+ "txt_Head3*GEOM*TEXT SET " + "OVERS 11-40" + "\0", print_writers);
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section4$Side" + WhichSide + "$PhaseWiseScore$Data3Grp$"
								+ "txt_Head3*GEOM*TEXT SET " + "OVERS 41-50" + "\0", print_writers);
						
						if(Integer.valueOf(phaseWiseScore.split("_")[0].split(",")[0]) == 0 && Integer.valueOf(phaseWiseScore.split("_")[0].split(",")[1]) == 0) {
							if(Float.valueOf(CricketFunctions.OverBalls(inning.getTotalOvers(), inning.getTotalBalls())) > 0.0) {
								PP1 = "0-0";
							}
						}else {
							PP1 = phaseWiseScore.split("_")[0].split(",")[0]+"-"+phaseWiseScore.split("_")[0].split(",")[1];
						}
						
						if(Integer.valueOf(phaseWiseScore.split("_")[1].split(",")[0]) == 0 && Integer.valueOf(phaseWiseScore.split("_")[1].split(",")[1]) == 0) {
							if(Float.valueOf(CricketFunctions.OverBalls(inning.getTotalOvers(), inning.getTotalBalls())) > 10.0) {
								PP2 = "0-0";
							}
						}else {
							PP2 = phaseWiseScore.split("_")[1].split(",")[0]+"-"+phaseWiseScore.split("_")[1].split(",")[1];
						}
						
						if(Integer.valueOf(phaseWiseScore.split("_")[2].split(",")[0]) == 0 && Integer.valueOf(phaseWiseScore.split("_")[2].split(",")[1]) == 0) {
							if(Float.valueOf(CricketFunctions.OverBalls(inning.getTotalOvers(), inning.getTotalBalls())) > 40.0) {
								PP3 = "0-0";
							}
						}else {
							PP3 = phaseWiseScore.split("_")[2].split(",")[0]+"-"+phaseWiseScore.split("_")[2].split(",")[1];
						}
						
					}else {
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section4$Side" + WhichSide + "$PhaseWiseScore$Data1Grp$"
								+ "txt_Head2*GEOM*TEXT SET " + "OVERS 1-6" + "\0", print_writers);
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section4$Side" + WhichSide + "$PhaseWiseScore$Data2Grp$"
								+ "txt_Head3*GEOM*TEXT SET " + "OVERS 7-15" + "\0", print_writers);
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section4$Side" + WhichSide + "$PhaseWiseScore$Data3Grp$"
								+ "txt_Head3*GEOM*TEXT SET " + "OVERS 16-20" + "\0", print_writers);
						
						
						if(Integer.valueOf(phaseWiseScore.split("_")[0].split(",")[0]) == 0 && Integer.valueOf(phaseWiseScore.split("_")[0].split(",")[1]) == 0) {
							if(Float.valueOf(CricketFunctions.OverBalls(inning.getTotalOvers(), inning.getTotalBalls())) > 0.0) {
								PP1 = "0-0";
							}
						}else {
							PP1 = phaseWiseScore.split("_")[0].split(",")[0]+"-"+phaseWiseScore.split("_")[0].split(",")[1];
						}
						if(Integer.valueOf(phaseWiseScore.split("_")[1].split(",")[0]) == 0 && Integer.valueOf(phaseWiseScore.split("_")[1].split(",")[1]) == 0) {
							if(Float.valueOf(CricketFunctions.OverBalls(inning.getTotalOvers(), inning.getTotalBalls())) > 6.0) {
								PP2 = "0-0";
							}
						}else {
							PP2 = phaseWiseScore.split("_")[1].split(",")[0]+"-"+phaseWiseScore.split("_")[1].split(",")[1];
						}
						if(Integer.valueOf(phaseWiseScore.split("_")[2].split(",")[0]) == 0 && Integer.valueOf(phaseWiseScore.split("_")[2].split(",")[1]) == 0) {
							if(Float.valueOf(CricketFunctions.OverBalls(inning.getTotalOvers(), inning.getTotalBalls())) > 15.0) {
								PP3 = "0-0";
							}
						}else {
							PP3 = phaseWiseScore.split("_")[2].split(",")[0]+"-"+phaseWiseScore.split("_")[2].split(",")[1];
						}
					}
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section4$Side" + WhichSide + "$PhaseWiseScore$Data1Grp$"
							+ "txt_Data1*GEOM*TEXT SET " + PP1 + "\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section4$Side" + WhichSide + "$PhaseWiseScore$Data2Grp$"
							+ "txt_Data2*GEOM*TEXT SET " + PP2 + "\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section4$Side" + WhichSide + "$PhaseWiseScore$Data3Grp$"
							+ "txt_Data2*GEOM*TEXT SET " + PP3 + "\0", print_writers);
					break;	
				case "BATMILESTONE": case "BALLMILESTONE":
					if(FirstPlayerId <= 0) {
						return "InfoBarPlayerProfile: Player Id NOT found [" + FirstPlayerId + "]";
					}
					
					BattingCard battingCard = null;
					BowlingCard bowlingCard = null;
					
					switch(infobar.getSectionAnalytics().toUpperCase()) {
				    case "BATMILESTONE":
				    	battingCard = inning.getBattingCard().stream().filter(bc -> bc.getPlayerId() == FirstPlayerId).findAny().orElse(null);
						
						if(battingCard == null) {
							return "InfoBarPlayerProfile: Player Id not found [" + FirstPlayerId + "]";
						}
				    	break;
				    case "BALLMILESTONE":
				    	bowlingCard = inning.getBowlingCard().stream().filter(boc -> boc.getPlayerId() == FirstPlayerId).findAny().orElse(null);
						
						if(bowlingCard == null) {
							return "InfoBarPlayerProfile: Player Id not found [" + FirstPlayerId + "]";
						}
				    	break;
				    }
					
					switch (matchAllData.getSetup().getMatchType()) {
					case CricketUtil.ODI:
						statsType = statsTypes.stream().filter(st -> st.getStatsShortName().equalsIgnoreCase("ODI")).findAny().orElse(null);
						break;
					case CricketUtil.OD:
						statsType = statsTypes.stream().filter(st -> st.getStatsShortName().equalsIgnoreCase("LIST A")).findAny().orElse(null);
						break;	
					case CricketUtil.IT20:
						statsType = statsTypes.stream().filter(st -> st.getStatsShortName().equalsIgnoreCase("IT20")).findAny().orElse(null);
						break;
					case CricketUtil.DT20:
						statsType = statsTypes.stream().filter(st -> st.getStatsShortName().equalsIgnoreCase("DT20")).findAny().orElse(null);
						break;
					}
					
					if(statsType == null) {
						return "InfoBarPlayerProfile: Stats Type not found for profile [" + WhichProfile + "]";
					}
					stat = statistics.stream().filter(st -> st.getPlayerID() == FirstPlayerId && statsType.getStatsId() == st.getStatsTypeId()).findAny().orElse(null);
					if(stat == null) {
						return "InfoBarPlayerProfile: Stats not found for Player Id [" + FirstPlayerId + "]";
					}
					
					switch (matchAllData.getSetup().getMatchType()) {
					case CricketUtil.ODI:
						statsType = statsTypes.stream().filter(st -> st.getStatsShortName().equalsIgnoreCase("ODI")).findAny().orElse(null);
						break;
					case CricketUtil.OD:
						statsType = statsTypes.stream().filter(st -> st.getStatsShortName().equalsIgnoreCase("LIST A")).findAny().orElse(null);
						break;	
					case CricketUtil.IT20:
						statsType = statsTypes.stream().filter(st -> st.getStatsShortName().equalsIgnoreCase("IT20")).findAny().orElse(null);
						break;
					case CricketUtil.DT20:
						statsType = statsTypes.stream().filter(st -> st.getStatsShortName().equalsIgnoreCase("DT20")).findAny().orElse(null);
						break;
					}
					
					stat.setStats_type(statsType);
					
					stat = CricketFunctions.updateTournamentWithH2h(stat, headToHead, matchAllData, CricketUtil.FULL);
					stat = CricketFunctions.updateStatisticsWithMatchData(stat, matchAllData, CricketUtil.FULL);
					
//					switch (matchAllData.getSetup().getMatchType()) {
//					case CricketUtil.ODI:
//						
//						break;
//					case CricketUtil.IT20:
//						statsType = statsTypes.stream().filter(st -> st.getStatsShortName().equalsIgnoreCase("IT20")).findAny().orElse(null);
//						break;
//					case CricketUtil.DT20:
//						statsType = statsTypes.stream().filter(st -> st.getStatsShortName().equalsIgnoreCase("T20")).findAny().orElse(null);
//						break;
//					}
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section4$Side" + WhichSide + "$"
							+ "Select*FUNCTION*Omo*vis_con SET 4\0", print_writers);
					
				    switch(infobar.getSectionAnalytics().toUpperCase()) {
				    case "BATMILESTONE":
				    	
				    	CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section4$Side" + WhichSide + 
								"$Miletsone$txt_Head*GEOM*TEXT SET " + battingCard.getPlayer().getTicker_name() + "\0", print_writers);
				    	break;
				    case "BALLMILESTONE":
				    	
				    	CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section4$Side" + WhichSide + 
								"$Miletsone$txt_Head*GEOM*TEXT SET " + bowlingCard.getPlayer().getTicker_name() + "\0", print_writers);
				    	break;
				    }

					switch (data_Type.toUpperCase()) {
					case "RUNS":
						
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section4$Side" + WhichSide + 
								"$Miletsone$Data1Grp$txt_Data1*GEOM*TEXT SET " + stat.getRuns() + "\0", print_writers);
						
						if(matchAllData.getSetup().getMatchType().equalsIgnoreCase(CricketUtil.ODI)) {
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section4$Side" + WhichSide + 
									"$Miletsone$txt_Head2*GEOM*TEXT SET " + " RUNS IN " + stat.getMatches() + " ODI MATCHES" + "\0", print_writers);
						}else if(matchAllData.getSetup().getMatchType().equalsIgnoreCase(CricketUtil.OD)) {
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section4$Side" + WhichSide + 
									"$Miletsone$txt_Head2*GEOM*TEXT SET " + " RUNS IN " + stat.getMatches() + " LIST A MATCHES" + "\0", print_writers);
						}else if(matchAllData.getSetup().getMatchType().equalsIgnoreCase(CricketUtil.IT20)) {
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section4$Side" + WhichSide + 
									"$Miletsone$txt_Head2*GEOM*TEXT SET " + " RUNS IN " + stat.getMatches() + " T20I MATCHES" + "\0", print_writers);
						}else if(matchAllData.getSetup().getMatchType().equalsIgnoreCase(CricketUtil.DT20)) {
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section4$Side" + WhichSide + 
									"$Miletsone$txt_Head2*GEOM*TEXT SET " + " T20 RUNS IN " + stat.getMatches() + " T20 MATCHES" + "\0", print_writers);
						}
						break;
					case "50":
						if(stat.getFifties() == 1) {
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section4$Side" + WhichSide + 
									"$Miletsone$Data1Grp$txt_Data1*GEOM*TEXT SET MAIDEN\0", print_writers);
							
							if(matchAllData.getSetup().getMatchType().equalsIgnoreCase(CricketUtil.ODI)) {
								CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section4$Side" + WhichSide + 
										"$Miletsone$txt_Head2*GEOM*TEXT SET " + " ODI FIFTY" + "\0", print_writers);
							}else if(matchAllData.getSetup().getMatchType().equalsIgnoreCase(CricketUtil.OD)) {
								CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section4$Side" + WhichSide + 
										"$Miletsone$txt_Head2*GEOM*TEXT SET " + " LIST A FIFTY" + "\0", print_writers);
							}else if(matchAllData.getSetup().getMatchType().equalsIgnoreCase(CricketUtil.IT20)) {
								CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section4$Side" + WhichSide + 
										"$Miletsone$txt_Head2*GEOM*TEXT SET " + " T20I FIFTY" + "\0", print_writers);
							}else if(matchAllData.getSetup().getMatchType().equalsIgnoreCase(CricketUtil.DT20)) {
								CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section4$Side" + WhichSide + 
										"$Miletsone$txt_Head2*GEOM*TEXT SET " + " T20 FIFTY" + "\0", print_writers);
							}
						}else {
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section4$Side" + WhichSide + 
									"$Miletsone$Data1Grp$txt_Data1*GEOM*TEXT SET " + CricketFunctions.ordinal(stat.getFifties()) + "\0", print_writers);
							
							if(matchAllData.getSetup().getMatchType().equalsIgnoreCase(CricketUtil.ODI)) {
								CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section4$Side" + WhichSide + 
										"$Miletsone$txt_Head2*GEOM*TEXT SET " + " ODI FIFTY" + "\0", print_writers);
							}else if(matchAllData.getSetup().getMatchType().equalsIgnoreCase(CricketUtil.OD)) {
								CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section4$Side" + WhichSide + 
										"$Miletsone$txt_Head2*GEOM*TEXT SET " + " LIST A FIFTY" + "\0", print_writers);
							}else if(matchAllData.getSetup().getMatchType().equalsIgnoreCase(CricketUtil.IT20)) {
								CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section4$Side" + WhichSide + 
										"$Miletsone$txt_Head2*GEOM*TEXT SET " + " T20I FIFTY" + "\0", print_writers);
							}else if(matchAllData.getSetup().getMatchType().equalsIgnoreCase(CricketUtil.DT20)) {
								CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section4$Side" + WhichSide + 
										"$Miletsone$txt_Head2*GEOM*TEXT SET " + " T20 FIFTY" + "\0", print_writers);
							}
						}
						break;
					case "100":
						if(stat.getHundreds() == 1) {
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section4$Side" + WhichSide + 
									"$Miletsone$Data1Grp$txt_Data1*GEOM*TEXT SET MAIDEN\0", print_writers);
							
							if(matchAllData.getSetup().getMatchType().equalsIgnoreCase(CricketUtil.ODI)) {
								CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section4$Side" + WhichSide + 
										"$Miletsone$txt_Head2*GEOM*TEXT SET " + " ODI HUNDRED" + "\0", print_writers);
							}else if(matchAllData.getSetup().getMatchType().equalsIgnoreCase(CricketUtil.OD)) {
								CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section4$Side" + WhichSide + 
										"$Miletsone$txt_Head2*GEOM*TEXT SET " + " LIST A HUNDRED" + "\0", print_writers);
							}else if(matchAllData.getSetup().getMatchType().equalsIgnoreCase(CricketUtil.IT20)) {
								CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section4$Side" + WhichSide + 
										"$Miletsone$txt_Head2*GEOM*TEXT SET " + " T20I HUNDRED" + "\0", print_writers);
							}else if(matchAllData.getSetup().getMatchType().equalsIgnoreCase(CricketUtil.DT20)) {
								CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section4$Side" + WhichSide + 
										"$Miletsone$txt_Head2*GEOM*TEXT SET " + " T20 HUNDRED" + "\0", print_writers);
							}
						}else {
							
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section4$Side" + WhichSide + 
									"$Miletsone$Data1Grp$txt_Data1*GEOM*TEXT SET " + CricketFunctions.ordinal(stat.getHundreds()) + "\0", print_writers);
							
							if(matchAllData.getSetup().getMatchType().equalsIgnoreCase(CricketUtil.ODI)) {
								CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section4$Side" + WhichSide + 
										"$Miletsone$txt_Head2*GEOM*TEXT SET " + " ODI HUNDRED" + "\0", print_writers);
							}else if(matchAllData.getSetup().getMatchType().equalsIgnoreCase(CricketUtil.OD)) {
								CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section4$Side" + WhichSide + 
										"$Miletsone$txt_Head2*GEOM*TEXT SET " + " LIST A HUNDRED" + "\0", print_writers);
							}else if(matchAllData.getSetup().getMatchType().equalsIgnoreCase(CricketUtil.IT20)) {
								CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section4$Side" + WhichSide + 
										"$Miletsone$txt_Head2*GEOM*TEXT SET " + " T20I HUNDRED" + "\0", print_writers);
							}else if(matchAllData.getSetup().getMatchType().equalsIgnoreCase(CricketUtil.DT20)) {
								CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section4$Side" + WhichSide + 
										"$Miletsone$txt_Head2*GEOM*TEXT SET " + " T20 HUNDRED" + "\0", print_writers);
							}
						}
						break;
					case "WICKETS":
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section4$Side" + WhichSide + 
								"$Miletsone$Data1Grp$txt_Data1*GEOM*TEXT SET " + stat.getWickets() + "\0", print_writers);
						
						if(matchAllData.getSetup().getMatchType().equalsIgnoreCase(CricketUtil.ODI)) {
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section4$Side" + WhichSide + 
									"$Miletsone$txt_Head2*GEOM*TEXT SET " + " WICKETS IN " + stat.getMatches() + " ODI MATCHES" + "\0", print_writers);
						}else if(matchAllData.getSetup().getMatchType().equalsIgnoreCase(CricketUtil.OD)) {
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section4$Side" + WhichSide + 
									"$Miletsone$txt_Head2*GEOM*TEXT SET " + " WICKETS IN " + stat.getMatches() + " LIST A MATCHES" + "\0", print_writers);
						}else if(matchAllData.getSetup().getMatchType().equalsIgnoreCase(CricketUtil.IT20)) {
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section4$Side" + WhichSide + 
									"$Miletsone$txt_Head2*GEOM*TEXT SET " + " WICKETS IN " + stat.getMatches() + " T20I MATCHES" + "\0", print_writers);
						}else if(matchAllData.getSetup().getMatchType().equalsIgnoreCase(CricketUtil.DT20)) {
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section4$Side" + WhichSide + 
									"$Miletsone$txt_Head2*GEOM*TEXT SET " + " WICKETS IN " + stat.getMatches() + " T20 MATCHES"+ "\0", print_writers);
						}
						break;
					case "3WI":
						if(stat.getPlus3() == 1) {
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section4$Side" + WhichSide + 
									"$Miletsone$Data1Grp$txt_Data1*GEOM*TEXT SET MAIDEN\0", print_writers);
							
							if(matchAllData.getSetup().getMatchType().equalsIgnoreCase(CricketUtil.ODI)) {
								CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section4$Side" + WhichSide + 
										"$Miletsone$txt_Head2*GEOM*TEXT SET " + " 3-WICKET HAUL IN ODIs" + "\0", print_writers);
							}else if(matchAllData.getSetup().getMatchType().equalsIgnoreCase(CricketUtil.OD)) {
								CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section4$Side" + WhichSide + 
										"$Miletsone$txt_Head2*GEOM*TEXT SET " + " 3-WICKET HAUL IN LIST A" + "\0", print_writers);
							}else if(matchAllData.getSetup().getMatchType().equalsIgnoreCase(CricketUtil.IT20)) {
								CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section4$Side" + WhichSide + 
										"$Miletsone$txt_Head2*GEOM*TEXT SET " + " 3-WICKET HAUL IN T20Is" + "\0", print_writers);
							}else if(matchAllData.getSetup().getMatchType().equalsIgnoreCase(CricketUtil.DT20)) {
								CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section4$Side" + WhichSide + 
										"$Miletsone$txt_Head2*GEOM*TEXT SET " + " 3-WICKET HAUL IN T20s" + "\0", print_writers);
							}
						}else {
							
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section4$Side" + WhichSide + 
									"$Miletsone$Data1Grp$txt_Data1*GEOM*TEXT SET " + CricketFunctions.ordinal(stat.getPlus3()) + "\0", print_writers);
							
							if(matchAllData.getSetup().getMatchType().equalsIgnoreCase(CricketUtil.ODI)) {
								CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section4$Side" + WhichSide + 
										"$Miletsone$txt_Head2*GEOM*TEXT SET " + " 3-WICKET HAUL IN ODIs" + "\0", print_writers);
							}else if(matchAllData.getSetup().getMatchType().equalsIgnoreCase(CricketUtil.OD)) {
								CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section4$Side" + WhichSide + 
										"$Miletsone$txt_Head2*GEOM*TEXT SET " + " 3-WICKET HAUL IN LIST A" + "\0", print_writers);
							}else if(matchAllData.getSetup().getMatchType().equalsIgnoreCase(CricketUtil.IT20)) {
								CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section4$Side" + WhichSide + 
										"$Miletsone$txt_Head2*GEOM*TEXT SET " + " 3-WICKET HAUL IN T20Is" + "\0", print_writers);
							}else if(matchAllData.getSetup().getMatchType().equalsIgnoreCase(CricketUtil.DT20)) {
								CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section4$Side" + WhichSide + 
										"$Miletsone$txt_Head2*GEOM*TEXT SET " + " 3-WICKET HAUL IN T20s" + "\0", print_writers);
							}
						}
						break;
					case "5WI":
						if(stat.getPlus5() == 1) {
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section4$Side" + WhichSide + 
									"$Miletsone$Data1Grp$txt_Data1*GEOM*TEXT SET MAIDEN\0", print_writers);
							
							if(matchAllData.getSetup().getMatchType().equalsIgnoreCase(CricketUtil.ODI)) {
								CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section4$Side" + WhichSide + 
										"$Miletsone$txt_Head2*GEOM*TEXT SET " + " 5-WICKET HAUL IN ODIs" + "\0", print_writers);
							}else if(matchAllData.getSetup().getMatchType().equalsIgnoreCase(CricketUtil.OD)) {
								CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section4$Side" + WhichSide + 
										"$Miletsone$txt_Head2*GEOM*TEXT SET " + " 5-WICKET HAUL IN LIST A" + "\0", print_writers);
							}else if(matchAllData.getSetup().getMatchType().equalsIgnoreCase(CricketUtil.IT20)) {
								CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section4$Side" + WhichSide + 
										"$Miletsone$txt_Head2*GEOM*TEXT SET " + " 5-WICKET HAUL IN T20Is" + "\0", print_writers);
							}else if(matchAllData.getSetup().getMatchType().equalsIgnoreCase(CricketUtil.DT20)) {
								CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section4$Side" + WhichSide + 
										"$Miletsone$txt_Head2*GEOM*TEXT SET " + " 5-WICKET HAUL IN T20s" + "\0", print_writers);
							}
						}else {
							
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section4$Side" + WhichSide + 
									"$Miletsone$Data1Grp$txt_Data1*GEOM*TEXT SET " + CricketFunctions.ordinal(stat.getPlus5()) + "\0", print_writers);
							
							if(matchAllData.getSetup().getMatchType().equalsIgnoreCase(CricketUtil.ODI)) {
								CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section4$Side" + WhichSide + 
										"$Miletsone$txt_Head2*GEOM*TEXT SET " + " 5-WICKET HAUL IN ODIs" + "\0", print_writers);
							}else if(matchAllData.getSetup().getMatchType().equalsIgnoreCase(CricketUtil.OD)) {
								CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section4$Side" + WhichSide + 
										"$Miletsone$txt_Head2*GEOM*TEXT SET " + " 5-WICKET HAUL IN LIST A" + "\0", print_writers);
							}else if(matchAllData.getSetup().getMatchType().equalsIgnoreCase(CricketUtil.IT20)) {
								CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section4$Side" + WhichSide + 
										"$Miletsone$txt_Head2*GEOM*TEXT SET " + " 5-WICKET HAUL IN T20Is" + "\0", print_writers);
							}else if(matchAllData.getSetup().getMatchType().equalsIgnoreCase(CricketUtil.DT20)) {
								CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section4$Side" + WhichSide + 
										"$Miletsone$txt_Head2*GEOM*TEXT SET " + " 5-WICKET HAUL IN T20s" + "\0", print_writers);
							}
						}
						break;
					}
					break;
				case "BIG_EQUATION":
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section4$Side" + WhichSide + "$"
							+ "Select*FUNCTION*Omo*vis_con SET 3\0", print_writers);
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section4$Side" + WhichSide + "$Equation$Top$"
							+ "txt_Head*GEOM*TEXT SET " + inning.getBatting_team().getTeamName4() + " NEED" + "\0", print_writers);
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section4$Side" + WhichSide + "$Equation$Top$"
							+ "txt_Data1*GEOM*TEXT SET " + CricketFunctions.GetTargetData(matchAllData).getRemaningRuns() + "\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section4$Side" + WhichSide + "$Equation$Top$"
							+ "txt_Head2*GEOM*TEXT SET " + "RUN" + CricketFunctions.Plural(CricketFunctions.GetTargetData(matchAllData).getRemaningRuns()).toUpperCase() 
							+ "\0", print_writers);
					
					switch (matchAllData.getSetup().getMatchType()) {
					case CricketUtil.ODI: case CricketUtil.OD:
						
						if(CricketFunctions.GetTargetData(matchAllData).getRemaningBall() >= 100) {
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section4$Side" + WhichSide + "$Equation$Bottom$"
									+ "txt_Data1*GEOM*TEXT SET " + CricketFunctions.OverBalls(0, CricketFunctions.GetTargetData(matchAllData).getRemaningBall()) + "\0", print_writers);
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section4$Side" + WhichSide + "$Equation$Bottom$"
									+ "txt_Head2*GEOM*TEXT SET " + "OVERS"
									+ (matchAllData.getSetup().getTargetType() != null && !matchAllData.getSetup().getTargetType().isEmpty()
											? matchAllData.getSetup().getTargetType().toUpperCase():"") + "\0", print_writers);
						}else {
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section4$Side" + WhichSide + "$Equation$Bottom$"
									+ "txt_Data1*GEOM*TEXT SET " + CricketFunctions.GetTargetData(matchAllData).getRemaningBall() + "\0", print_writers);
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section4$Side" + WhichSide + "$Equation$Bottom$"
									+ "txt_Head2*GEOM*TEXT SET " + "BALL" + CricketFunctions.Plural(CricketFunctions.GetTargetData(matchAllData).getRemaningBall()).toUpperCase() 
									+ (matchAllData.getSetup().getTargetType() != null && !matchAllData.getSetup().getTargetType().isEmpty()
											? " (" + matchAllData.getSetup().getTargetType().toUpperCase() + ")" :"") + "\0", print_writers);
						}
						
						
						break;

					default:
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section4$Side" + WhichSide + "$Equation$Bottom$"
								+ "txt_Data1*GEOM*TEXT SET " + CricketFunctions.GetTargetData(matchAllData).getRemaningBall() + "\0", print_writers);
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section4$Side" + WhichSide + "$Equation$Bottom$"
								+ "txt_Head2*GEOM*TEXT SET " + "BALL" + CricketFunctions.Plural(CricketFunctions.GetTargetData(matchAllData).getRemaningBall()).toUpperCase() 
								+ (matchAllData.getSetup().getTargetType() != null && !matchAllData.getSetup().getTargetType().isEmpty()
										? " (" + matchAllData.getSetup().getTargetType().toUpperCase() + ")" : "") + "\0", print_writers);
						break;
					}
					
					
					break;	
				case "TIMELINE":
					int i = 0;
					this_data_str = new ArrayList<String>();
					
					if(matchAllData.getSetup().getMatchType().equalsIgnoreCase(CricketUtil.OD) || 
							matchAllData.getSetup().getMatchType().equalsIgnoreCase(CricketUtil.ODI)) {
						this_data_str.add(CricketFunctions.getlastthirtyballsdata(matchAllData, slashOrDash, matchAllData.getEventFile().getEvents(), 30));
						if(this_data_str.get(this_data_str.size()-1) == null || this_data_str.get(this_data_str.size()-1).split(slashOrDash).length > 4) {
							return "populateVizInfobarMiddleSection: Last " + 30 + " Balls data returned invalid";
						}
					}else {
						this_data_str.add(CricketFunctions.getlastthirtyballsdata(matchAllData, slashOrDash, matchAllData.getEventFile().getEvents(), 12));
						if(this_data_str.get(this_data_str.size()-1) == null || this_data_str.get(this_data_str.size()-1).split(slashOrDash).length > 4) {
							return "populateVizInfobarMiddleSection: Last " + 12 + " Balls data returned invalid";
						}
					}
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section4$Side" + WhichSide + "$Select*FUNCTION*Omo*vis_con SET 0\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section4$Side" + WhichSide + "$TimeLine$txt_Head*GEOM*TEXT SET TIMELINE\0", print_writers);
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section4$Side" + WhichSide + "$TimeLine"
							+ "$RunRate$txt_Header*GEOM*TEXT SET CURRENT RUN RATE - \0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section4$Side" + WhichSide + "$TimeLine"
							+ "$RunRate$txt_Data1*GEOM*TEXT SET " + inning.getRunRate() + " RPO" + "\0", print_writers);
					
					if(matchAllData.getSetup().getMatchType().equalsIgnoreCase(CricketUtil.OD) || 
							matchAllData.getSetup().getMatchType().equalsIgnoreCase(CricketUtil.ODI)) {
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section4$Side" + WhichSide + "$TimeLine"
								+ "$LastXBalls$txt_Head*GEOM*TEXT SET LAST 30 BALLS \0", print_writers);
					}else {
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section4$Side" + WhichSide + "$TimeLine"
								+ "$LastXBalls$txt_Head*GEOM*TEXT SET LAST 12 BALLS \0", print_writers);
					}
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section4$Side" + WhichSide + "$TimeLine"
							+ "$LastXBalls$Data1Grp$txt_Head2*GEOM*TEXT SET " + "RUN" + CricketFunctions.Plural(Integer.valueOf(this_data_str.
									get(this_data_str.size()-1).split(slashOrDash)[0])).toUpperCase() + " \0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section4$Side" + WhichSide + "$TimeLine"
							+ "$LastXBalls$Data2Grp$txt_Head3*GEOM*TEXT SET " + "WKT" + CricketFunctions.Plural(Integer.valueOf(this_data_str.
									get(this_data_str.size()-1).split(slashOrDash)[1])).toUpperCase() + " \0", print_writers);
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section4$Side" + WhichSide + "$TimeLine"
							+ "$LastXBalls$Data1Grp$txt_Data1*GEOM*TEXT SET " + this_data_str.get(this_data_str.size()-1).split(slashOrDash)[0] + "\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section4$Side" + WhichSide + "$TimeLine"
							+ "$LastXBalls$Data2Grp$txt_Data2*GEOM*TEXT SET " + this_data_str.get(this_data_str.size()-1).split(slashOrDash)[1] + "\0", print_writers);
					
					this_data_str = new ArrayList<String>();
					this_data_str.add(String.join(",",  new ArrayList<>(Arrays.asList(IndexController.MatchStats.getTimeLine().split(",")))
					        .stream().map(s -> s.replace("WIDE", "WD").replace("NO_BALL", "NB").replace("LEG_BYE", "LB").replace("BYE", "B")
					        .replace("PENALTY", "PN").replace("LOG_WICKET", "W").replace("WICKET", "W"))
					        .collect(Collectors.collectingAndThen(Collectors.toList(), list -> {Collections.reverse(list); return list;}))
					        .toArray(new String[0])));
					
					if(this_data_str.get(this_data_str.size()-1) == null || this_data_str.get(this_data_str.size()-1).split(",").length < 20) {
						return "populateSectionAnalytics: TIMELINE data returned invalid";
					}
					
					String[] elements = Arrays.stream(this_data_str.get(this_data_str.size() - 1).split(","))
					    .map(String::trim).filter(s -> !s.isEmpty()).toArray(String[]::new);
					
					if (elements.length > 28) {
					    elements = Arrays.copyOfRange(elements, elements.length - 28, elements.length);
					}
					Collections.reverse(Arrays.asList(elements));
					
//					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section4$Side" + WhichSide + "$TimeLine$ThisOver$ThisOverAll"
//							+ "*FUNCTION*Omo*vis_con SET " + Math.min(elements.length, 25) + "\0", print_writers);
					
					for(int iBall = 0; iBall < elements.length; iBall++) {
						if(i == 22) {
							i = i+1;
						}
						if(iBall < 28) {
							switch (elements[iBall].toUpperCase()) {
							case "|":
								CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section4$Side" + WhichSide + "$TimeLine$ThisOver$ThisOverAll$BallGrp" 
										+ (i + 1) + "$SelectType*FUNCTION*Omo*vis_con SET 0\0", print_writers);
								break;
							case CricketUtil.DOT:
								CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section4$Side" + WhichSide + "$TimeLine$ThisOver$ThisOverAll$BallGrp" 
										+ (i + 1) + "$SelectType*FUNCTION*Omo*vis_con SET 1\0", print_writers);
								break;
							case CricketUtil.ONE: case CricketUtil.TWO: case CricketUtil.THREE: case CricketUtil.FIVE: case CricketUtil.FOUR: case CricketUtil.SIX:
								
								CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section4$Side" + WhichSide + "$TimeLine$ThisOver$ThisOverAll$BallGrp" 
										+ (i + 1) + "$SelectType*FUNCTION*Omo*vis_con SET 2\0", print_writers);
								CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section4$Side" + WhichSide + "$TimeLine$ThisOver$ThisOverAll$BallGrp" 
										+ (i+1) + "$Rest$txt_Ball*GEOM*TEXT SET " + elements[iBall] + "\0", print_writers);
								break;	
							case "W":
								CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section4$Side" + WhichSide + "$TimeLine$ThisOver$ThisOverAll$BallGrp" 
										+ (i + 1) + "$SelectType*FUNCTION*Omo*vis_con SET 3\0", print_writers);
								CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section4$Side" + WhichSide + "$TimeLine$ThisOver$ThisOverAll$BallGrp" 
										+ (i+1) + "$Four_Six_WKT$txt_Ball*GEOM*TEXT SET W\0", print_writers);
								break;

							default:
								if(elements[iBall].toUpperCase().contains("BOUNDARY")) {
									if(elements[iBall].toUpperCase().equalsIgnoreCase("6BOUNDARY")||elements[iBall].toUpperCase().equalsIgnoreCase("4BOUNDARY")) {
										CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section4$Side" + WhichSide + "$TimeLine$ThisOver$ThisOverAll$BallGrp" 
												+ (i + 1) + "$SelectType*FUNCTION*Omo*vis_con SET 3\0", print_writers);
										CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section4$Side" + WhichSide + "$TimeLine$ThisOver$ThisOverAll$BallGrp" 
												+ (i+1) + "$Four_Six_WKT$txt_Ball*GEOM*TEXT SET " + elements[iBall].toUpperCase().replace("BOUNDARY", "") + "\0", print_writers);
									}
								}else {
									CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section4$Side" + WhichSide + "$TimeLine$ThisOver$ThisOverAll$BallGrp" 
											+ (i + 1) + "$SelectType*FUNCTION*Omo*vis_con SET 2\0", print_writers);
									
									if(elements[iBall].contains("+")) {
										String line1 = "",line2 = "";
										String[] Timeparts = elements[iBall].split("\\+");
										if (Timeparts.length == 2) {
									        line1 = Timeparts[0];
									        line2 = Timeparts[1];
									    } else if (Timeparts.length == 3) {
									        line1 = Timeparts[0];
									        line2 = Timeparts[1] + "+" + Timeparts[2];
									    }
										
										CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section4$Side" + WhichSide + "$TimeLine$ThisOver$ThisOverAll$BallGrp" 
												+ (i+1) + "$Rest$txt_Ball*GEOM*TEXT SET " + line1.toUpperCase() + "+" + line2.toUpperCase() + "\0", print_writers);
									}else {
										if(elements[iBall].contains("PN")) {
											CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section4$Side" + WhichSide + "$TimeLine$ThisOver$ThisOverAll$BallGrp" 
													+ (i+1) + "$Rest$txt_Ball*GEOM*TEXT SET 5PN\0", print_writers);
										}else {
											CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section4$Side" + WhichSide + "$TimeLine$ThisOver$ThisOverAll$BallGrp" 
													+ (i+1) + "$Rest$txt_Ball*GEOM*TEXT SET " + elements[iBall] + "\0", print_writers);
										}
									}
								}
								break;
							}
						}
						i = i + 1;
					}
					break;
				}
			}
			break;
		case Constants.BAN_AFG_SERIES:
			if(infobar.getSectionAnalytics() != null && !infobar.getSectionAnalytics().isEmpty()) {
				switch(infobar.getSectionAnalytics().toUpperCase()) {
				case "FREETEXT":
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section4$Side" + WhichSide + "$Select*FUNCTION*Omo*vis_con SET 1\0", print_writers);
					
					if(freeText.split(",").length == 5) {
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section4$Side" + WhichSide + 
								"$Freetext$txt_Head*GEOM*TEXT SET " + freeText.split(",")[3] + " " + freeText.split(",")[4] + "\0", print_writers);
					}else if(freeText.split(",").length == 4) {
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section4$Side" + WhichSide + 
								"$Freetext$txt_Head*GEOM*TEXT SET " + freeText.split(",")[3] + "\0", print_writers);
					}
					
					break;
				case "IDENT":
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section4$Side" + WhichSide + "$Select*FUNCTION*Omo*vis_con SET 1\0", print_writers);
					
//					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section4$Side" + WhichSide + 
//							"$Freetext$txt_Head*GEOM*TEXT SET " + matchAllData.getSetup().getMatchIdent() + " | LIVE FROM SHARJAH v " + inning.getBowling_team().getTeamName1() + "\0", print_writers);
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section4$Side" + WhichSide + 
							"$Freetext$txt_Head*GEOM*TEXT SET " + matchAllData.getSetup().getMatchIdent() + " | LIVE FROM DUBAI\0", print_writers);
					break;
				case "FREETEXTDB":
					InfobarStats infoStat = infobarStats.get(infobarStatsId-1);
					if(infoStat == null) {
						return "populateSectionAnalytics: infoStat null";
					}
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section4$Side" + WhichSide + "$Select*FUNCTION*Omo*vis_con SET 1\0", print_writers);
					
					if(infoStat.getText2() != null) {
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section4$Side" + WhichSide + 
								"$Freetext$txt_Head*GEOM*TEXT SET " + infoStat.getText1() + " " + infoStat.getText2() + "\0", print_writers);
					}else if(freeText.split(",").length == 4) {
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section4$Side" + WhichSide + 
								"$Freetext$txt_Head*GEOM*TEXT SET " + infoStat.getText1() + "\0", print_writers);
					}
					break;
				case "COMMENTATORS":
					String Data = "";
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section4$Side" + WhichSide + "$Select*FUNCTION*Omo*vis_con SET 1\0", print_writers);
					
					String[] parts = Comms_Name.split(",");
					List<String> names = new ArrayList<>();

					for (int i = 0; i < parts.length; i++) {
					    if (Integer.parseInt(parts[i]) > 0) {
					        names.add(Commentators.get(Integer.parseInt(parts[i]) - 1).getCommentatorName());
					    }
					}

					if (names.size() > 1) {
					    Data = String.join(" | ", names);
					} else {
					    Data = String.join("", names);
					}
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section4$Side" + WhichSide + 
							"$Freetext$txt_Head*GEOM*TEXT SET " + "COMMENTATORS - " + Data + "\0", print_writers);
					break;
				case "SPONSOR":
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section4$Side" + WhichSide + "$"
							+ "Select*FUNCTION*Omo*vis_con SET 2\0", print_writers);
					
					for(Sponsor sp : cricketService.getSponsor()) {
						if(sp.getSponsorId() == sponsor_id) {
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section4$Side" + WhichSide 
						    		+ "$Freetext_Image$Sponsor*TEXTURE*IMAGE SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_SPONSOR : Constants.MT20_SPONSOR) + sp.getImagename() + "\0", print_writers);
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section4$Side" + WhichSide 
									+ "$Freetext_Image$txt_Head*GEOM*TEXT SET " + sp.getText() + "\0", print_writers);
						}
					}
					break;
				case "BATMILESTONE": case "BALLMILESTONE":
					if(FirstPlayerId <= 0) {
						return "InfoBarPlayerProfile: Player Id NOT found [" + FirstPlayerId + "]";
					}
					
					BattingCard battingCard = null;
					BowlingCard bowlingCard = null;
					
					switch(infobar.getSectionAnalytics().toUpperCase()) {
				    case "BATMILESTONE":
				    	battingCard = inning.getBattingCard().stream().filter(bc -> bc.getPlayerId() == FirstPlayerId).findAny().orElse(null);
						
						if(battingCard == null) {
							return "InfoBarPlayerProfile: Player Id not found [" + FirstPlayerId + "]";
						}
				    	break;
				    case "BALLMILESTONE":
				    	bowlingCard = inning.getBowlingCard().stream().filter(boc -> boc.getPlayerId() == FirstPlayerId).findAny().orElse(null);
						
						if(bowlingCard == null) {
							return "InfoBarPlayerProfile: Player Id not found [" + FirstPlayerId + "]";
						}
				    	break;
				    }
					
					switch (matchAllData.getSetup().getMatchType()) {
					case CricketUtil.ODI:
						statsType = statsTypes.stream().filter(st -> st.getStatsShortName().equalsIgnoreCase("ODI")).findAny().orElse(null);
						break;
					case CricketUtil.IT20:
						statsType = statsTypes.stream().filter(st -> st.getStatsShortName().equalsIgnoreCase("IT20")).findAny().orElse(null);
						break;
					case CricketUtil.DT20:
						statsType = statsTypes.stream().filter(st -> st.getStatsShortName().equalsIgnoreCase("T20")).findAny().orElse(null);
						break;
					}
					
					if(statsType == null) {
						return "InfoBarPlayerProfile: Stats Type not found for profile [" + WhichProfile + "]";
					}
					stat = statistics.stream().filter(st -> st.getPlayerID() == FirstPlayerId && statsType.getStatsId() == st.getStatsTypeId()).findAny().orElse(null);
					if(stat == null) {
						return "InfoBarPlayerProfile: Stats not found for Player Id [" + FirstPlayerId + "]";
					}
					
					switch (matchAllData.getSetup().getMatchType()) {
					case CricketUtil.ODI:
						statsType = statsTypes.stream().filter(st -> st.getStatsShortName().equalsIgnoreCase("ODI")).findAny().orElse(null);
						break;
					case CricketUtil.IT20:
						statsType = statsTypes.stream().filter(st -> st.getStatsShortName().equalsIgnoreCase("IT20")).findAny().orElse(null);
						break;
					case CricketUtil.DT20:
						statsType = statsTypes.stream().filter(st -> st.getStatsShortName().equalsIgnoreCase("T20")).findAny().orElse(null);
						break;
					}
					
					stat.setStats_type(statsType);
					
					stat = CricketFunctions.updateTournamentWithH2h(stat, headToHead, matchAllData, CricketUtil.FULL);
					stat = CricketFunctions.updateStatisticsWithMatchData(stat, matchAllData, CricketUtil.FULL);
					
//					switch (matchAllData.getSetup().getMatchType()) {
//					case CricketUtil.ODI:
//						
//						break;
//					case CricketUtil.IT20:
//						statsType = statsTypes.stream().filter(st -> st.getStatsShortName().equalsIgnoreCase("IT20")).findAny().orElse(null);
//						break;
//					case CricketUtil.DT20:
//						statsType = statsTypes.stream().filter(st -> st.getStatsShortName().equalsIgnoreCase("T20")).findAny().orElse(null);
//						break;
//					}
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section4$Side" + WhichSide + "$"
							+ "Select*FUNCTION*Omo*vis_con SET 4\0", print_writers);
					
				    switch(infobar.getSectionAnalytics().toUpperCase()) {
				    case "BATMILESTONE":
				    	CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section4$Side" + WhichSide + 
								"$Miletsone$Data$img_Base2*TEXTURE*IMAGE SET " + Constants.BAN_AFG_SERIES_BASE2 + inning.getBatting_team().getTeamBadge() + "\0", print_writers);
					    CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section4$Side" + WhichSide + 
								"$Miletsone$Data$img_Text2*TEXTURE*IMAGE SET " + Constants.BAN_AFG_SERIES_TEXT2 + inning.getBatting_team().getTeamBadge() + "\0", print_writers);
					    
				    	CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section4$Side" + WhichSide + 
								"$Miletsone$txt_Head*GEOM*TEXT SET " + battingCard.getPlayer().getTicker_name() + "\0", print_writers);
				    	break;
				    case "BALLMILESTONE":
				    	CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section4$Side" + WhichSide + 
								"$Miletsone$Data$img_Base2*TEXTURE*IMAGE SET " + Constants.BAN_AFG_SERIES_BASE2 + inning.getBowling_team().getTeamBadge() + "\0", print_writers);
					    CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section4$Side" + WhichSide + 
								"$Miletsone$Data$img_Text2*TEXTURE*IMAGE SET " + Constants.BAN_AFG_SERIES_TEXT2 + inning.getBowling_team().getTeamBadge() + "\0", print_writers);
					    
					    
				    	CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section4$Side" + WhichSide + 
								"$Miletsone$txt_Head*GEOM*TEXT SET " + bowlingCard.getPlayer().getTicker_name() + "\0", print_writers);
				    	break;
				    }

					switch (data_Type.toUpperCase()) {
					case "RUNS":
						
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section4$Side" + WhichSide + 
								"$Miletsone$Data1Grp$txt_Data1*GEOM*TEXT SET " + stat.getRuns() + "\0", print_writers);
						
						if(matchAllData.getSetup().getMatchType().equalsIgnoreCase(CricketUtil.ODI)) {
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section4$Side" + WhichSide + 
									"$Miletsone$txt_Head2*GEOM*TEXT SET " + " RUNS IN " + stat.getMatches() + " ODI MATCHES" + "\0", print_writers);
						}else if(matchAllData.getSetup().getMatchType().equalsIgnoreCase(CricketUtil.IT20)) {
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section4$Side" + WhichSide + 
									"$Miletsone$txt_Head2*GEOM*TEXT SET " + " RUNS IN " + stat.getMatches() + " T20I MATCHES" + "\0", print_writers);
						}else if(matchAllData.getSetup().getMatchType().equalsIgnoreCase(CricketUtil.DT20)) {
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section4$Side" + WhichSide + 
									"$Miletsone$txt_Head2*GEOM*TEXT SET " + " T20 RUNS IN " + stat.getMatches() + " T20 MATCHES" + "\0", print_writers);
						}
						break;
					case "50":
						if(stat.getFifties() == 1) {
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section4$Side" + WhichSide + 
									"$Miletsone$Data1Grp$txt_Data1*GEOM*TEXT SET MAIDEN\0", print_writers);
							
							if(matchAllData.getSetup().getMatchType().equalsIgnoreCase(CricketUtil.ODI)) {
								CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section4$Side" + WhichSide + 
										"$Miletsone$txt_Head2*GEOM*TEXT SET " + " ODI FIFTY" + "\0", print_writers);
							}else if(matchAllData.getSetup().getMatchType().equalsIgnoreCase(CricketUtil.IT20)) {
								CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section4$Side" + WhichSide + 
										"$Miletsone$txt_Head2*GEOM*TEXT SET " + " T20I FIFTY" + "\0", print_writers);
							}else if(matchAllData.getSetup().getMatchType().equalsIgnoreCase(CricketUtil.DT20)) {
								CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section4$Side" + WhichSide + 
										"$Miletsone$txt_Head2*GEOM*TEXT SET " + " T20 FIFTY" + "\0", print_writers);
							}
						}else {
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section4$Side" + WhichSide + 
									"$Miletsone$Data1Grp$txt_Data1*GEOM*TEXT SET " + CricketFunctions.ordinal(stat.getFifties()) + "\0", print_writers);
							
							if(matchAllData.getSetup().getMatchType().equalsIgnoreCase(CricketUtil.ODI)) {
								CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section4$Side" + WhichSide + 
										"$Miletsone$txt_Head2*GEOM*TEXT SET " + " ODI FIFTY" + "\0", print_writers);
							}else if(matchAllData.getSetup().getMatchType().equalsIgnoreCase(CricketUtil.IT20)) {
								CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section4$Side" + WhichSide + 
										"$Miletsone$txt_Head2*GEOM*TEXT SET " + " T20I FIFTY" + "\0", print_writers);
							}else if(matchAllData.getSetup().getMatchType().equalsIgnoreCase(CricketUtil.DT20)) {
								CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section4$Side" + WhichSide + 
										"$Miletsone$txt_Head2*GEOM*TEXT SET " + " T20 FIFTY" + "\0", print_writers);
							}
						}
						break;
					case "100":
						if(stat.getHundreds() == 1) {
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section4$Side" + WhichSide + 
									"$Miletsone$Data1Grp$txt_Data1*GEOM*TEXT SET MAIDEN\0", print_writers);
							
							if(matchAllData.getSetup().getMatchType().equalsIgnoreCase(CricketUtil.ODI)) {
								CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section4$Side" + WhichSide + 
										"$Miletsone$txt_Head2*GEOM*TEXT SET " + " ODI HUNDRED" + "\0", print_writers);
							}else if(matchAllData.getSetup().getMatchType().equalsIgnoreCase(CricketUtil.IT20)) {
								CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section4$Side" + WhichSide + 
										"$Miletsone$txt_Head2*GEOM*TEXT SET " + " T20I HUNDRED" + "\0", print_writers);
							}else if(matchAllData.getSetup().getMatchType().equalsIgnoreCase(CricketUtil.DT20)) {
								CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section4$Side" + WhichSide + 
										"$Miletsone$txt_Head2*GEOM*TEXT SET " + " T20 HUNDRED" + "\0", print_writers);
							}
						}else {
							
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section4$Side" + WhichSide + 
									"$Miletsone$Data1Grp$txt_Data1*GEOM*TEXT SET " + CricketFunctions.ordinal(stat.getHundreds()) + "\0", print_writers);
							
							if(matchAllData.getSetup().getMatchType().equalsIgnoreCase(CricketUtil.ODI)) {
								CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section4$Side" + WhichSide + 
										"$Miletsone$txt_Head2*GEOM*TEXT SET " + " ODI HUNDRED" + "\0", print_writers);
							}else if(matchAllData.getSetup().getMatchType().equalsIgnoreCase(CricketUtil.IT20)) {
								CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section4$Side" + WhichSide + 
										"$Miletsone$txt_Head2*GEOM*TEXT SET " + " T20I HUNDRED" + "\0", print_writers);
							}else if(matchAllData.getSetup().getMatchType().equalsIgnoreCase(CricketUtil.DT20)) {
								CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section4$Side" + WhichSide + 
										"$Miletsone$txt_Head2*GEOM*TEXT SET " + " T20 HUNDRED" + "\0", print_writers);
							}
						}
						break;
					case "WICKETS":
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section4$Side" + WhichSide + 
								"$Miletsone$Data1Grp$txt_Data1*GEOM*TEXT SET " + stat.getWickets() + "\0", print_writers);
						
						if(matchAllData.getSetup().getMatchType().equalsIgnoreCase(CricketUtil.ODI)) {
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section4$Side" + WhichSide + 
									"$Miletsone$txt_Head2*GEOM*TEXT SET " + " WICKETS IN " + stat.getMatches() + " ODI MATCHES" + "\0", print_writers);
						}else if(matchAllData.getSetup().getMatchType().equalsIgnoreCase(CricketUtil.IT20)) {
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section4$Side" + WhichSide + 
									"$Miletsone$txt_Head2*GEOM*TEXT SET " + " WICKETS IN " + stat.getMatches() + " T20I MATCHES" + "\0", print_writers);
						}else if(matchAllData.getSetup().getMatchType().equalsIgnoreCase(CricketUtil.DT20)) {
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section4$Side" + WhichSide + 
									"$Miletsone$txt_Head2*GEOM*TEXT SET " + " WICKETS IN " + stat.getMatches() + " T20 MATCHES"+ "\0", print_writers);
						}
						break;
					case "3WI":
						if(stat.getPlus3() == 1) {
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section4$Side" + WhichSide + 
									"$Miletsone$Data1Grp$txt_Data1*GEOM*TEXT SET MAIDEN\0", print_writers);
							
							if(matchAllData.getSetup().getMatchType().equalsIgnoreCase(CricketUtil.ODI)) {
								CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section4$Side" + WhichSide + 
										"$Miletsone$txt_Head2*GEOM*TEXT SET " + " 3 WICKET HAUL IN ODIs" + "\0", print_writers);
							}else if(matchAllData.getSetup().getMatchType().equalsIgnoreCase(CricketUtil.IT20)) {
								CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section4$Side" + WhichSide + 
										"$Miletsone$txt_Head2*GEOM*TEXT SET " + " 3 WICKET HAUL IN T20Is" + "\0", print_writers);
							}else if(matchAllData.getSetup().getMatchType().equalsIgnoreCase(CricketUtil.DT20)) {
								CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section4$Side" + WhichSide + 
										"$Miletsone$txt_Head2*GEOM*TEXT SET " + " 3 WICKET HAUL IN T20s" + "\0", print_writers);
							}
						}else {
							
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section4$Side" + WhichSide + 
									"$Miletsone$Data1Grp$txt_Data1*GEOM*TEXT SET " + CricketFunctions.ordinal(stat.getPlus3()) + "\0", print_writers);
							
							if(matchAllData.getSetup().getMatchType().equalsIgnoreCase(CricketUtil.ODI)) {
								CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section4$Side" + WhichSide + 
										"$Miletsone$txt_Head2*GEOM*TEXT SET " + " 3 WICKET HAUL IN ODIs" + "\0", print_writers);
							}else if(matchAllData.getSetup().getMatchType().equalsIgnoreCase(CricketUtil.IT20)) {
								CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section4$Side" + WhichSide + 
										"$Miletsone$txt_Head2*GEOM*TEXT SET " + " 3 WICKET HAUL IN T20Is" + "\0", print_writers);
							}else if(matchAllData.getSetup().getMatchType().equalsIgnoreCase(CricketUtil.DT20)) {
								CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section4$Side" + WhichSide + 
										"$Miletsone$txt_Head2*GEOM*TEXT SET " + " 3 WICKET HAUL IN T20s" + "\0", print_writers);
							}
						}
						break;
					case "5WI":
						if(stat.getPlus5() == 1) {
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section4$Side" + WhichSide + 
									"$Miletsone$Data1Grp$txt_Data1*GEOM*TEXT SET MAIDEN\0", print_writers);
							
							if(matchAllData.getSetup().getMatchType().equalsIgnoreCase(CricketUtil.ODI)) {
								CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section4$Side" + WhichSide + 
										"$Miletsone$txt_Head2*GEOM*TEXT SET " + " 5 WICKET HAUL IN ODIs" + "\0", print_writers);
							}else if(matchAllData.getSetup().getMatchType().equalsIgnoreCase(CricketUtil.IT20)) {
								CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section4$Side" + WhichSide + 
										"$Miletsone$txt_Head2*GEOM*TEXT SET " + " 5 WICKET HAUL IN T20Is" + "\0", print_writers);
							}else if(matchAllData.getSetup().getMatchType().equalsIgnoreCase(CricketUtil.DT20)) {
								CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section4$Side" + WhichSide + 
										"$Miletsone$txt_Head2*GEOM*TEXT SET " + " 5 WICKET HAUL IN T20s" + "\0", print_writers);
							}
						}else {
							
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section4$Side" + WhichSide + 
									"$Miletsone$Data1Grp$txt_Data1*GEOM*TEXT SET " + CricketFunctions.ordinal(stat.getPlus5()) + "\0", print_writers);
							
							if(matchAllData.getSetup().getMatchType().equalsIgnoreCase(CricketUtil.ODI)) {
								CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section4$Side" + WhichSide + 
										"$Miletsone$txt_Head2*GEOM*TEXT SET " + " 5 WICKET HAUL IN ODIs" + "\0", print_writers);
							}else if(matchAllData.getSetup().getMatchType().equalsIgnoreCase(CricketUtil.IT20)) {
								CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section4$Side" + WhichSide + 
										"$Miletsone$txt_Head2*GEOM*TEXT SET " + " 5 WICKET HAUL IN T20Is" + "\0", print_writers);
							}else if(matchAllData.getSetup().getMatchType().equalsIgnoreCase(CricketUtil.DT20)) {
								CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section4$Side" + WhichSide + 
										"$Miletsone$txt_Head2*GEOM*TEXT SET " + " 5 WICKET HAUL IN T20s" + "\0", print_writers);
							}
						}
						break;
					}
					break;
				case "BIG_EQUATION":
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section4$Side" + WhichSide + "$"
							+ "Select*FUNCTION*Omo*vis_con SET 3\0", print_writers);
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section4$Side" + WhichSide + "$Equation$Top$"
							+ "Data1Grp$img_Base2*TEXTURE*IMAGE SET " + Constants.BAN_AFG_SERIES_BASE2 + inning.getBatting_team().getTeamBadge() + "\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section4$Side" + WhichSide + "$Equation$Top$"
							+ "Data1Grp$img_Text2*TEXTURE*IMAGE SET " + Constants.BAN_AFG_SERIES_TEXT2 + inning.getBatting_team().getTeamBadge() + "\0", print_writers);
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section4$Side" + WhichSide + "$Equation$Bottom$"
							+ "Data1Grp$img_Base2*TEXTURE*IMAGE SET " + Constants.BAN_AFG_SERIES_BASE2 + inning.getBatting_team().getTeamBadge() + "\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section4$Side" + WhichSide + "$Equation$Bottom$"
							+ "Data1Grp$img_Text2*TEXTURE*IMAGE SET " + Constants.BAN_AFG_SERIES_TEXT2 + inning.getBatting_team().getTeamBadge() + "\0", print_writers);
					
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section4$Side" + WhichSide + "$Equation$Top$"
							+ "txt_Head*GEOM*TEXT SET " + inning.getBatting_team().getTeamName4() + " NEED" + "\0", print_writers);
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section4$Side" + WhichSide + "$Equation$Top$"
							+ "txt_Data1*GEOM*TEXT SET " + CricketFunctions.GetTargetData(matchAllData).getRemaningRuns() + "\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section4$Side" + WhichSide + "$Equation$Top$"
							+ "txt_Head2*GEOM*TEXT SET " + "RUN" + CricketFunctions.Plural(CricketFunctions.GetTargetData(matchAllData).getRemaningRuns()).toUpperCase() 
							+ "\0", print_writers);
					
					switch (matchAllData.getSetup().getMatchType()) {
					case CricketUtil.ODI:
						
						if(CricketFunctions.GetTargetData(matchAllData).getRemaningBall() >= 100) {
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section4$Side" + WhichSide + "$Equation$Bottom$"
									+ "txt_Data1*GEOM*TEXT SET " + CricketFunctions.OverBalls(0, CricketFunctions.GetTargetData(matchAllData).getRemaningBall()) + "\0", print_writers);
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section4$Side" + WhichSide + "$Equation$Bottom$"
									+ "txt_Head2*GEOM*TEXT SET " + "OVERS"
									+ (matchAllData.getSetup().getTargetType() != null && !matchAllData.getSetup().getTargetType().isEmpty()
											? matchAllData.getSetup().getTargetType().toUpperCase():"") + "\0", print_writers);
						}else {
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section4$Side" + WhichSide + "$Equation$Bottom$"
									+ "txt_Data1*GEOM*TEXT SET " + CricketFunctions.GetTargetData(matchAllData).getRemaningBall() + "\0", print_writers);
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section4$Side" + WhichSide + "$Equation$Bottom$"
									+ "txt_Head2*GEOM*TEXT SET " + "BALL" + CricketFunctions.Plural(CricketFunctions.GetTargetData(matchAllData).getRemaningBall()).toUpperCase() 
									+ (matchAllData.getSetup().getTargetType() != null && !matchAllData.getSetup().getTargetType().isEmpty()
											? matchAllData.getSetup().getTargetType().toUpperCase():"") + "\0", print_writers);
						}
						
						
						break;

					default:
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section4$Side" + WhichSide + "$Equation$Bottom$"
								+ "txt_Data1*GEOM*TEXT SET " + CricketFunctions.GetTargetData(matchAllData).getRemaningBall() + "\0", print_writers);
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section4$Side" + WhichSide + "$Equation$Bottom$"
								+ "txt_Head2*GEOM*TEXT SET " + "BALL" + CricketFunctions.Plural(CricketFunctions.GetTargetData(matchAllData).getRemaningBall()).toUpperCase() 
								+ (matchAllData.getSetup().getTargetType() != null && !matchAllData.getSetup().getTargetType().isEmpty()
										? matchAllData.getSetup().getTargetType().toUpperCase():"") + "\0", print_writers);
						break;
					}
					
					
					break;	
				case "TIMELINE":
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section4$Side" + WhichSide + "$Select*FUNCTION*Omo*vis_con SET 0\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section4$Side" + WhichSide + "$TimeLine$txt_Head*GEOM*TEXT SET TIMELINE\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section4$Side" + WhichSide + "$TimeLine$img_Base02*TEXTURE*IMAGE SET " 
							+ Constants.BAN_AFG_SERIES_BASE2 + inning.getBatting_team().getTeamBadge() + "\0", print_writers);
					
					this_data_str = new ArrayList<String>();
					this_data_str.add(String.join(",",  new ArrayList<>(Arrays.asList(IndexController.MatchStats.getTimeLine().split(",")))
					        .stream().map(s -> s.replace("WIDE", "WD").replace("NO_BALL", "NB").replace("LEG_BYE", "LB").replace("BYE", "B")
					        .replace("PENALTY", "PN").replace("LOG_WICKET", "W").replace("WICKET", "W"))
					        .collect(Collectors.collectingAndThen(Collectors.toList(), list -> {Collections.reverse(list); return list;}))
					        .toArray(new String[0])));
					
					if(this_data_str.get(this_data_str.size()-1) == null || this_data_str.get(this_data_str.size()-1).split(",").length < 20) {
						return "populateSectionAnalytics: TIMELINE data returned invalid";
					}
					
					String[] elements = Arrays.stream(this_data_str.get(this_data_str.size() - 1).split(","))
					    .map(String::trim).filter(s -> !s.isEmpty()).toArray(String[]::new);
					
					if (elements.length > 25) {
					    elements = Arrays.copyOfRange(elements, elements.length - 25, elements.length);
					}
					Collections.reverse(Arrays.asList(elements));
					
//					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section4$Side" + WhichSide + "$TimeLine$ThisOver$ThisOverAll"
//							+ "*FUNCTION*Omo*vis_con SET " + Math.min(elements.length, 25) + "\0", print_writers);
					
					for(int iBall = 0; iBall < elements.length; iBall++) {
						if(iBall < 25) {
							switch (elements[iBall].toUpperCase()) {
							case "|":
								CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section4$Side" + WhichSide + "$TimeLine$ThisOver$ThisOverAll$BallGrp" 
										+ (iBall + 1) + "$SelectType*FUNCTION*Omo*vis_con SET 0\0", print_writers);
								CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section4$Side" + WhichSide + "$TimeLine$ThisOver$ThisOverAll$BallGrp"
										+ (iBall+1) + "$group$img_Base2*TEXTURE*IMAGE SET " + Constants.BAN_AFG_SERIES_BASE2 + inning.getBatting_team().getTeamBadge() + "\0", print_writers);
								break;
							case CricketUtil.DOT:
								CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section4$Side" + WhichSide + "$TimeLine$ThisOver$ThisOverAll$BallGrp" 
										+ (iBall + 1) + "$SelectType*FUNCTION*Omo*vis_con SET 1\0", print_writers);
								CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section4$Side" + WhichSide + "$TimeLine$ThisOver$ThisOverAll$BallGrp"
										+ (iBall+1) + "$Dot$img_Base2*TEXTURE*IMAGE SET " + Constants.BAN_AFG_SERIES_BASE2 + inning.getBatting_team().getTeamBadge() + "\0", print_writers);
								CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section4$Side" + WhichSide + "$TimeLine$ThisOver$ThisOverAll$BallGrp"
										+ (iBall+1) + "$Dot$img_Text2*TEXTURE*IMAGE SET " + Constants.BAN_AFG_SERIES_TEXT2 + inning.getBatting_team().getTeamBadge() + "\0", print_writers);
								break;
							case CricketUtil.ONE: case CricketUtil.TWO: case CricketUtil.THREE: case CricketUtil.FIVE: case CricketUtil.FOUR: case CricketUtil.SIX:
								
								CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section4$Side" + WhichSide + "$TimeLine$ThisOver$ThisOverAll$BallGrp" 
										+ (iBall + 1) + "$SelectType*FUNCTION*Omo*vis_con SET 2\0", print_writers);
								CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section4$Side" + WhichSide + "$TimeLine$ThisOver$ThisOverAll$BallGrp"
										+ (iBall+1) + "$Rest$img_Base2*TEXTURE*IMAGE SET " + Constants.BAN_AFG_SERIES_BASE2 + inning.getBatting_team().getTeamBadge() + "\0", print_writers);
								CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section4$Side" + WhichSide + "$TimeLine$ThisOver$ThisOverAll$BallGrp"
										+ (iBall+1) + "$Rest$img_Text2*TEXTURE*IMAGE SET " + Constants.BAN_AFG_SERIES_TEXT2 + inning.getBatting_team().getTeamBadge() + "\0", print_writers);
								CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section4$Side" + WhichSide + "$TimeLine$ThisOver$ThisOverAll$BallGrp" 
										+ (iBall+1) + "$Rest$txt_Ball*GEOM*TEXT SET " + elements[iBall] + "\0", print_writers);
								break;	
							case "W":
								CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section4$Side" + WhichSide + "$TimeLine$ThisOver$ThisOverAll$BallGrp" 
										+ (iBall + 1) + "$SelectType*FUNCTION*Omo*vis_con SET 3\0", print_writers);
								CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section4$Side" + WhichSide + "$TimeLine$ThisOver$ThisOverAll$BallGrp"
										+ (iBall+1) + "$Four_Six_WKT$txt_Ball*TEXTURE*IMAGE SET " + Constants.BAN_AFG_SERIES_BASE2 + inning.getBatting_team().getTeamBadge() + "\0", print_writers);
								CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section4$Side" + WhichSide + "$TimeLine$ThisOver$ThisOverAll$BallGrp" 
										+ (iBall+1) + "$Four_Six_WKT$txt_Ball*GEOM*TEXT SET W\0", print_writers);
								break;

							default:
								if(elements[iBall].toUpperCase().contains("BOUNDARY")) {
									if(elements[iBall].toUpperCase().equalsIgnoreCase("6BOUNDARY")||elements[iBall].toUpperCase().equalsIgnoreCase("4BOUNDARY")) {
										CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section4$Side" + WhichSide + "$TimeLine$ThisOver$ThisOverAll$BallGrp" 
												+ (iBall + 1) + "$SelectType*FUNCTION*Omo*vis_con SET 3\0", print_writers);
										CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section4$Side" + WhichSide + "$TimeLine$ThisOver$ThisOverAll$BallGrp"
												+ (iBall+1) + "$Four_Six_WKT$txt_Ball*TEXTURE*IMAGE SET " + Constants.BAN_AFG_SERIES_BASE2 + inning.getBatting_team().getTeamBadge() + "\0", print_writers);
										CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section4$Side" + WhichSide + "$TimeLine$ThisOver$ThisOverAll$BallGrp" 
												+ (iBall+1) + "$Four_Six_WKT$txt_Ball*GEOM*TEXT SET " + elements[iBall].toUpperCase().replace("BOUNDARY", "") + "\0", print_writers);
									}
								}else {
									CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section4$Side" + WhichSide + "$TimeLine$ThisOver$ThisOverAll$BallGrp" 
											+ (iBall + 1) + "$SelectType*FUNCTION*Omo*vis_con SET 2\0", print_writers);
									CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section4$Side" + WhichSide + "$TimeLine$ThisOver$ThisOverAll$BallGrp"
											+ (iBall+1) + "$Rest$img_Base2*TEXTURE*IMAGE SET " + Constants.BAN_AFG_SERIES_BASE2 + inning.getBatting_team().getTeamBadge() + "\0", print_writers);
									CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section4$Side" + WhichSide + "$TimeLine$ThisOver$ThisOverAll$BallGrp"
											+ (iBall+1) + "$Rest$img_Text2*TEXTURE*IMAGE SET " + Constants.BAN_AFG_SERIES_TEXT2 + inning.getBatting_team().getTeamBadge() + "\0", print_writers);
									
									if(elements[iBall].contains("+")) {
										String line1 = "",line2 = "";
										String[] Timeparts = elements[iBall].split("\\+");
										if (Timeparts.length == 2) {
									        line1 = Timeparts[0];
									        line2 = Timeparts[1];
									    } else if (Timeparts.length == 3) {
									        line1 = Timeparts[0];
									        line2 = Timeparts[1] + "+" + Timeparts[2];
									    }
										
										CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section4$Side" + WhichSide + "$TimeLine$ThisOver$ThisOverAll$BallGrp" 
												+ (iBall+1) + "$Rest$txt_Ball*GEOM*TEXT SET " + line1.toUpperCase() + "+" + line2.toUpperCase() + "\0", print_writers);
									}else {
										if(elements[iBall].contains("PN")) {
											CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section4$Side" + WhichSide + "$TimeLine$ThisOver$ThisOverAll$BallGrp" 
													+ (iBall+1) + "$Rest$txt_Ball*GEOM*TEXT SET 5PN\0", print_writers);
										}else {
											CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section4$Side" + WhichSide + "$TimeLine$ThisOver$ThisOverAll$BallGrp" 
													+ (iBall+1) + "$Rest$txt_Ball*GEOM*TEXT SET " + elements[iBall] + "\0", print_writers);
										}
									}
								}
								break;
							}
						}
					}
					break;
				}
			}
			break;
		case Constants.TRI_SERIES:  case Constants.MT20: case Constants.TG20:
			if(infobar.getSectionAnalytics() != null && !infobar.getSectionAnalytics().isEmpty()) {
				switch(infobar.getSectionAnalytics().toUpperCase()) {
				case "FREETEXT":
					if(freeText.split(",").length == 5) {
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$Analytics$Side" + WhichSide 
								+ "$Select_DataType*FUNCTION*Omo*vis_con SET 1\0", print_writers);
						
						switch(config.getBroadcaster()) {
						case Constants.TG20:
							CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$Analytics$Side" + WhichSide + "$2_Line_Text$Text$Maxsize_Title"
									+ "$select_Language*FUNCTION*Omo*vis_con SET ", config, Constants.TG20, print_writers, foreignLanguageOmo);
							foreignLanguageData = CricketFunctions.AssembleMultiLanguageData(CricketUtil.DICTIONARY, "", multilanguagedata, freeText.split(",")[3],"", null, 0, 
									foreignLanguageDataList);
							CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$Analytics$Side" + WhichSide + "$2_Line_Text$Text$Maxsize_Title"
									+ "$English$txt_Title*GEOM*TEXT SET ",config, Constants.TG20, print_writers, foreignLanguageData);
							
							CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$Analytics$Side" + WhichSide + "$2_Line_Text$Text$Maxsize_Subtitle"
									+ "$select_Language*FUNCTION*Omo*vis_con SET ", config, Constants.TG20, print_writers, foreignLanguageOmo);
							foreignLanguageData = CricketFunctions.AssembleMultiLanguageData(CricketUtil.DICTIONARY, "", multilanguagedata, freeText.split(",")[4],"", null, 0, 
									foreignLanguageDataList);
							CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$Analytics$Side" + WhichSide + "$2_Line_Text$Text$Maxsize_Subtitle"
									+ "$English$txt_Subtitle*GEOM*TEXT SET ",config, Constants.TG20, print_writers, foreignLanguageData);
							break;
						default:
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$Analytics$Side" + WhichSide + 
									"$2_Line_Text$txt_Title*GEOM*TEXT SET " + freeText.split(",")[3] + "\0", print_writers);
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$Analytics$Side" + WhichSide + 
									"$2_Line_Text$txt_Subtitle*GEOM*TEXT SET " + freeText.split(",")[4] + "\0", print_writers);
							break;
						}
					}else if(freeText.split(",").length == 4) {
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$Analytics$Side" + WhichSide 
								+ "$Select_DataType*FUNCTION*Omo*vis_con SET 0\0", print_writers);
						
						switch(config.getBroadcaster()) {
						case Constants.TG20:
							CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$Analytics$Side" + WhichSide + "$1_Line_Text$Maxsize_Title"
									+ "$select_Language*FUNCTION*Omo*vis_con SET ", config, Constants.TG20, print_writers, foreignLanguageOmo);
							foreignLanguageData = CricketFunctions.AssembleMultiLanguageData(CricketUtil.DICTIONARY, "", multilanguagedata, freeText.split(",")[3],"", null, 0, 
									foreignLanguageDataList);
							CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$Analytics$Side" + WhichSide + "$1_Line_Text$Maxsize_Title"
									+ "$English$txt_Title*GEOM*TEXT SET ",config, Constants.TG20, print_writers, foreignLanguageData);
							break;
						default:
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$Analytics$Side" + WhichSide + 
									"$1_Line_Text$txt_Title*GEOM*TEXT SET " + freeText.split(",")[3] + "\0", print_writers);
							break;
						}
					}
					
					break;
				case "FREETEXTDB":
					InfobarStats infoStat = infobarStats.get(infobarStatsId-1);
					if(infoStat == null) {
						return "populateSectionAnalytics: infoStat null";
					}
					
					if(infoStat.getText2() != null) {
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$Analytics$Side" + WhichSide 
								+ "$Select_DataType*FUNCTION*Omo*vis_con SET 1\0", print_writers);
						
						switch(config.getBroadcaster()) {
						case Constants.TG20:
							CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$Analytics$Side" + WhichSide + "$2_Line_Text$Text$Maxsize_Title"
									+ "$select_Language*FUNCTION*Omo*vis_con SET ", config, Constants.TG20, print_writers, foreignLanguageOmo);
							foreignLanguageData = CricketFunctions.AssembleMultiLanguageData(CricketUtil.DICTIONARY, "", multilanguagedata, infoStat.getText1(),"", null, 0, 
									foreignLanguageDataList);
							CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$Analytics$Side" + WhichSide + "$2_Line_Text$Text$Maxsize_Title"
									+ "$English$txt_Title*GEOM*TEXT SET ",config, Constants.TG20, print_writers, foreignLanguageData);
							
							CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$Analytics$Side" + WhichSide + "$2_Line_Text$Text$Maxsize_Subtitle"
									+ "$select_Language*FUNCTION*Omo*vis_con SET ", config, Constants.TG20, print_writers, foreignLanguageOmo);
							foreignLanguageData = CricketFunctions.AssembleMultiLanguageData(CricketUtil.DICTIONARY, "", multilanguagedata, infoStat.getText2(),"", null, 0, 
									foreignLanguageDataList);
							CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$Analytics$Side" + WhichSide + "$2_Line_Text$Text$Maxsize_Subtitle"
									+ "$English$txt_Subtitle*GEOM*TEXT SET ",config, Constants.TG20, print_writers, foreignLanguageData);
							break;
						default:
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$Analytics$Side" + WhichSide + 
									"$2_Line_Text$txt_Title*GEOM*TEXT SET " + infoStat.getText1() + "\0", print_writers);
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$Analytics$Side" + WhichSide + 
									"$2_Line_Text$txt_Subtitle*GEOM*TEXT SET " + infoStat.getText2() + "\0", print_writers);
							break;
						}
					}else {
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$Analytics$Side" + WhichSide 
								+ "$Select_DataType*FUNCTION*Omo*vis_con SET 0\0", print_writers);
						
						switch(config.getBroadcaster()) {
						case Constants.TG20:
							CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$Analytics$Side" + WhichSide + "$1_Line_Text$Maxsize_Title"
									+ "$select_Language*FUNCTION*Omo*vis_con SET ", config, Constants.TG20, print_writers, foreignLanguageOmo);
							foreignLanguageData = CricketFunctions.AssembleMultiLanguageData(CricketUtil.DICTIONARY, "", multilanguagedata, infoStat.getText1(),"", null, 0, 
									foreignLanguageDataList);
							CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$Analytics$Side" + WhichSide + "$1_Line_Text$Maxsize_Title"
									+ "$English$txt_Title*GEOM*TEXT SET ",config, Constants.TG20, print_writers, foreignLanguageData);
							break;
						default:
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$Analytics$Side" + WhichSide + 
									"$1_Line_Text$txt_Title*GEOM*TEXT SET " + infoStat.getText1() + "\0", print_writers);
							break;
						}
					}
					break;
				case "SPONSOR":
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$Analytics$Side" + WhichSide 
							+ "$Select_DataType*FUNCTION*Omo*vis_con SET 9\0", print_writers);
					
					for(Sponsor sp : cricketService.getSponsor()) {
						if(sp.getSponsorId() == sponsor_id) {
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$Analytics$Side" + WhichSide 
						    		+ "$Production$TPT03*TEXTURE*IMAGE SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? 
						    				Constants.TRI_SERIES_SPONSOR : Constants.MT20_SPONSOR) + sp.getImagename() + "\0", print_writers);
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$Analytics$Side" + WhichSide 
									+ "$Production$txt_Title*GEOM*TEXT SET " + sp.getText() + "\0", print_writers);
						}
					}
					break;
				case "IDENT":
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$Analytics$Side" + WhichSide 
							+ "$Select_DataType*FUNCTION*Omo*vis_con SET 1\0", print_writers);
					
					switch(config.getBroadcaster()) {
					case Constants.TG20:
						CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$Analytics$Side" + WhichSide + "$2_Line_Text$Text$Maxsize_Title"
								+ "$select_Language*FUNCTION*Omo*vis_con SET ", config, Constants.TG20, print_writers, foreignLanguageOmo);
						foreignLanguageData = CricketFunctions.AssembleMultiLanguageData(CricketUtil.DICTIONARY, "", multilanguagedata,matchAllData.getSetup().getTournament().trim(),
							    "", null, 0, foreignLanguageDataList);
						CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$Analytics$Side" + WhichSide + "$2_Line_Text$Text$Maxsize_Title"
								+ "$English$txt_Title*GEOM*TEXT SET ",config, Constants.TG20, print_writers, foreignLanguageData);
						
						CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$Analytics$Side" + WhichSide + "$2_Line_Text$Text$Maxsize_Subtitle"
								+ "$select_Language*FUNCTION*Omo*vis_con SET ", config, Constants.TG20, print_writers, foreignLanguageOmo);
						foreignLanguageDataList = CricketFunctions.AssembleMultiLanguageData(CricketUtil.DICTIONARY, "", multilanguagedata,matchAllData.getSetup().getMatchIdent().split(" ")[0].trim(), 
							    "", null, 1,foreignLanguageDataList);
						foreignLanguageDataList = CricketFunctions.AssembleMultiLanguageData(CricketUtil.DICTIONARY, "", multilanguagedata,matchAllData.getSetup().getMatchIdent().split(" ")[1].trim(), 
						    "", null, 2, foreignLanguageDataList);
						foreignLanguageData.add(CricketFunctions.MergeForeignLanguageDataListToSingleObject(foreignLanguageDataList));
						
						CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$Analytics$Side" + WhichSide + "$2_Line_Text$Text$Maxsize_Subtitle"
								+ "$English$txt_Subtitle*GEOM*TEXT SET ",config, Constants.TG20, print_writers, foreignLanguageData);
						break;
					default:
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$Analytics$Side" + WhichSide + 
								"$2_Line_Text$txt_Title*GEOM*TEXT SET " + matchAllData.getSetup().getTournament() + "\0", print_writers);
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$Analytics$Side" + WhichSide + 
								"$2_Line_Text$txt_Subtitle*GEOM*TEXT SET " + matchAllData.getSetup().getMatchIdent() + "\0", print_writers);
						break;
					}
					break;
				case "COMMENTATORS":
					String Data = "";
					String[] parts = Comms_Name.split(",");
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$Analytics$Side" + WhichSide 
							+ "$Select_DataType*FUNCTION*Omo*vis_con SET 1\0", print_writers);
					switch(config.getBroadcaster()) {
					case Constants.TG20:
						CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$Analytics$Side" + WhichSide + "$2_Line_Text$Text$Maxsize_Subtitle"
								+ "$select_Language*FUNCTION*Omo*vis_con SET ", config, Constants.TG20, print_writers, foreignLanguageOmo);
						foreignLanguageData = CricketFunctions.AssembleMultiLanguageData(CricketUtil.DICTIONARY, "", multilanguagedata, "COMMENTATORS", "", null, 0, foreignLanguageDataList);
						CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$Analytics$Side" + WhichSide + "$2_Line_Text$Text$Maxsize_Subtitle"
								+ "$English$txt_Subtitle*GEOM*TEXT SET ",config, Constants.TG20, print_writers, foreignLanguageData);
						
						List<String> namesEnglish = new ArrayList<>();
						List<String> namesTelugu  = new ArrayList<>();
						
						for (int i = 0; i < parts.length; i++) {
						    int index = Integer.parseInt(parts[i].trim());
						    if (index > 0) {
						        String commName = Commentators.get(index - 1).getCommentatorName();

						        // Look up multilanguage data for this commentator by full name
						        foreignLanguageDataList = CricketFunctions.AssembleMultiLanguageData(CricketUtil.DICTIONARY, "", multilanguagedata, commName, 
						        		"", null, 0, foreignLanguageDataList);

						        ForeignLanguageData commFld = foreignLanguageDataList.get(0);

						        namesEnglish.add(commFld.getEnglishText() != null && !commFld.getEnglishText().isEmpty() ? commFld.getEnglishText() : commName);
						        namesTelugu.add (commFld.getTeluguText()  != null && !commFld.getTeluguText().isEmpty()  ? commFld.getTeluguText()  : commName);
						    }
						}
						
						// Join with separator based on count
						String separator = " | ";
						ForeignLanguageData commMerged = new ForeignLanguageData();
						commMerged.setEnglishText(String.join(namesEnglish.size() > 1 ? separator : "", namesEnglish));
						commMerged.setTeluguText (String.join(namesTelugu.size()  > 1 ? separator : "", namesTelugu));
						
						foreignLanguageData.add(commMerged);
						// Write all languages to viz
						CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$Analytics$Side" + WhichSide + "$2_Line_Text$Text$Maxsize_Title"
								+ "$select_Language*FUNCTION*Omo*vis_con SET ", config, Constants.TG20, print_writers, foreignLanguageOmo);
						CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$Analytics$Side" + WhichSide + "$2_Line_Text$Text$Maxsize_Title"
								+ "$English$txt_Title*GEOM*TEXT SET ",config, Constants.TG20, print_writers, foreignLanguageData);
						break;
					default:
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$Analytics$Side" + WhichSide + 
								"$2_Line_Text$txt_Subtitle*GEOM*TEXT SET " + "COMMENTATORS" + "\0", print_writers);
						List<String> names = new ArrayList<>();
						for (int i = 0; i < parts.length; i++) {
						    if (Integer.parseInt(parts[i]) > 0) {
						        names.add(Commentators.get(Integer.parseInt(parts[i]) - 1).getCommentatorName());
						    }
						}

						if (names.size() > 1) {
						    Data = String.join(" | ", names);
						} else {
						    Data = String.join("", names);
						}
						
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$Analytics$Side" + WhichSide + 
								"$2_Line_Text$txt_Title*GEOM*TEXT SET " + Data + "\0", print_writers);
						break;
					}
					break;
				case "BATMILESTONE": case "BALLMILESTONE":
					if(FirstPlayerId <= 0) {
						return "InfoBarPlayerProfile: Player Id NOT found [" + FirstPlayerId + "]";
					}
					
					BattingCard battingCard = null;
					BowlingCard bowlingCard = null;
					
					switch(infobar.getSectionAnalytics().toUpperCase()) {
				    case "BATMILESTONE":
				    	battingCard = inning.getBattingCard().stream().filter(bc -> bc.getPlayerId() == FirstPlayerId).findAny().orElse(null);
						
						if(battingCard == null) {
							return "InfoBarPlayerProfile: Player Id not found [" + FirstPlayerId + "]";
						}
				    	break;
				    case "BALLMILESTONE":
				    	bowlingCard = inning.getBowlingCard().stream().filter(boc -> boc.getPlayerId() == FirstPlayerId).findAny().orElse(null);
						
						if(bowlingCard == null) {
							return "InfoBarPlayerProfile: Player Id not found [" + FirstPlayerId + "]";
						}
				    	break;
				    }
					
					statsType = statsTypes.stream().filter(st -> st.getStatsShortName().equalsIgnoreCase("IT20")).findAny().orElse(null);
					if(statsType == null) {
						return "InfoBarPlayerProfile: Stats Type not found for profile [" + WhichProfile + "]";
					}
					stat = statistics.stream().filter(st -> st.getPlayerID() == FirstPlayerId && statsType.getStatsId() == st.getStatsTypeId()).findAny().orElse(null);
					if(stat == null) {
						return "InfoBarPlayerProfile: Stats not found for Player Id [" + FirstPlayerId + "]";
					}
					statsType = statsTypes.stream().filter(st -> st.getStatsShortName().equalsIgnoreCase("IT20")).findAny().orElse(null);
					stat.setStats_type(statsType);
					
					stat = CricketFunctions.updateTournamentWithH2h(stat, headToHead, matchAllData, CricketUtil.FULL);
					stat = CricketFunctions.updateStatisticsWithMatchData(stat, matchAllData, CricketUtil.FULL);
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$Analytics$Side" + WhichSide 
							+ "$Select_DataType*FUNCTION*Omo*vis_con SET 6\0", print_writers);
					
					String basePath = "",fullPath="",vizPath="";
					
					switch(config.getBroadcaster()) {
					case Constants.TRI_SERIES:
						basePath = (config.getPrimaryIpAddress().equalsIgnoreCase(Constants.LOCALHOST) ? Constants.TRI_SERIES_LOCAL_PHOTO_PATH
						        : "\\\\" + config.getPrimaryIpAddress() + "\\\\" + Constants.TRI_SERIES_PHOTO_PATH);
						break;
					case Constants.MT20:
						basePath = (config.getPrimaryIpAddress().equalsIgnoreCase(Constants.LOCALHOST) ? Constants.MT20_LOCAL_PHOTO_PATH
						        : "\\\\" + config.getPrimaryIpAddress() + "\\\\" + Constants.MT20_PHOTO_PATH);
						break;
					case Constants.TG20:
						basePath = (config.getPrimaryIpAddress().equalsIgnoreCase(Constants.LOCALHOST) ? Constants.TG20_LOCAL_PHOTO_PATH
						        : "\\\\" + config.getPrimaryIpAddress() + "\\\\" + Constants.TG20_PHOTO_PATH);
						break;
					}
					
				    switch(infobar.getSectionAnalytics().toUpperCase()) {
				    case "BATMILESTONE":
				    	CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$Analytics$Side" + WhichSide + 
								"$Milestone$Name$txt_First_Name*GEOM*TEXT SET " + battingCard.getPlayer().getFirstname() + "\0", print_writers);
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$Analytics$Side" + WhichSide + "$Milestone$Name$"
								+ "txt_Last_Name*GEOM*TEXT SET " + (battingCard.getPlayer().getSurname() != null ? battingCard.getPlayer().getSurname():"") 
								+ "\0", print_writers);
						
						//Photo
						fullPath = basePath + inning.getBatting_team().getTeamName4() + "\\\\" + Constants.RIGHT + "\\\\" + 
								battingCard.getPlayer().getPhoto() + CricketUtil.PNG_EXTENSION;
					    vizPath = "-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$Analytics$Side" + WhichSide + "$Milestone$Image";
					    
					    CricketFunctions.DoadWriteCommandToAllViz(vizPath + "$img_Player*TEXTURE*IMAGE SET " + basePath + Constants.BLANK + CricketUtil.PNG_EXTENSION + "\0", print_writers);
					    CricketFunctions.DoadWriteCommandToAllViz(vizPath + "$img_Player*TEXTURE*IMAGE SET " + fullPath + "\0", print_writers);
				    	
				    	CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$Analytics$Side" + WhichSide + "$Milestone$Stat_1"
								+ "$txt_Runs*GEOM*TEXT SET " + battingCard.getRuns() + "*" + "\0", print_writers);
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$Analytics$Side" + WhichSide + "$Milestone$Stat_1"
								+ "$txt_Balls*GEOM*TEXT SET " + battingCard.getBalls() + " BALL" + CricketFunctions.Plural(battingCard.getBalls()).toUpperCase() 
								+ "\0", print_writers);
				    	break;
				    case "BALLMILESTONE":
				    	CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$Analytics$Side" + WhichSide + 
								"$Milestone$Name$txt_First_Name*GEOM*TEXT SET " + bowlingCard.getPlayer().getFirstname() + "\0", print_writers);
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$Analytics$Side" + WhichSide + "$Milestone$Name$"
								+ "txt_Last_Name*GEOM*TEXT SET " + (bowlingCard.getPlayer().getSurname() != null ? bowlingCard.getPlayer().getSurname():"") 
								+ "\0", print_writers);
						
						//Photo
						
						fullPath = basePath + inning.getBowling_team().getTeamName4() + "\\\\" + Constants.RIGHT + "\\\\" + 
								bowlingCard.getPlayer().getPhoto() + CricketUtil.PNG_EXTENSION;
					    vizPath = "-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$Analytics$Side" + WhichSide + "$Milestone$Image";
					    
					    
					    CricketFunctions.DoadWriteCommandToAllViz(vizPath + "$img_Player*TEXTURE*IMAGE SET " + basePath + Constants.BLANK 
					    		+ CricketUtil.PNG_EXTENSION + "\0", print_writers);
					    CricketFunctions.DoadWriteCommandToAllViz(vizPath + "$img_Player*TEXTURE*IMAGE SET " + fullPath + "\0", print_writers);
				    	
				    	CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$Analytics$Side" + WhichSide + "$Milestone$Stat_1"
								+ "$txt_Runs*GEOM*TEXT SET " + bowlingCard.getWickets() + "-" + bowlingCard.getRuns() + "\0", print_writers);
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$Analytics$Side" + WhichSide + "$Milestone$Stat_1"
								+ "$txt_Balls*GEOM*TEXT SET " + CricketFunctions.OverBalls(bowlingCard.getOvers(), bowlingCard.getBalls()) + 
								(bowlingCard.getOvers() == 1 && bowlingCard.getBalls() == 0 ? " OVER" : " OVERS") + "\0", print_writers);
				    	break;
				    }

					switch (data_Type.toUpperCase()) {
					case "RUNS":
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$Analytics$Side" + WhichSide + "$Milestone$Info"
								+ "$txt_Text*GEOM*TEXT SET " + stat.getRuns() + " T20I RUNS IN " + stat.getMatches() + " MATCHES" + "\0", print_writers);
						break;
					case "50":
						if(stat.getFifties() == 1) {
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$Analytics$Side" + WhichSide + "$Milestone$Info"
									+ "$txt_Text*GEOM*TEXT SET " + "MAIDEN T20I FIFTY IN " + stat.getMatches() + " MATCHES"  + "\0", print_writers);
						}else {
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$Analytics$Side" + WhichSide + "$Milestone$Info"
									+ "$txt_Text*GEOM*TEXT SET " + stat.getFifties() + " T20I FIFTIES IN " + stat.getMatches() + " MATCHES" + "\0", print_writers);
						}
						break;
					case "100":
						if(stat.getHundreds() == 1) {
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$Analytics$Side" + WhichSide + "$Milestone$Info"
									+ "$txt_Text*GEOM*TEXT SET " + "MAIDEN T20I HUNDRED IN " + stat.getMatches() + " MATCHES" + "\0", print_writers);
						}else {
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$Analytics$Side" + WhichSide + "$Milestone$Info"
									+ "$txt_Text*GEOM*TEXT SET " + stat.getHundreds() + " T20I HUNDREDS IN "+ stat.getMatches() + " MATCHES" + "\0", print_writers);
						}
						break;
					case "WICKETS":
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$Analytics$Side" + WhichSide + "$Milestone$Info"
								+ "$txt_Text*GEOM*TEXT SET " + stat.getWickets() + " T20I WICKETS IN " + stat.getMatches() + " MATCHES" + "\0", print_writers);
						break;
					case "3WI":
						if(stat.getPlus3() == 1) {
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$Analytics$Side" + WhichSide + "$Milestone$Info"
									+ "$txt_Text*GEOM*TEXT SET " + "MAIDEN T20I 3 WICKET HAUL IN " + stat.getMatches() + " MATCHES" + "\0", print_writers);
						}else {
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$Analytics$Side" + WhichSide + "$Milestone$Info"
									+ "$txt_Text*GEOM*TEXT SET " + stat.getPlus3() + " T20I 3 WICKET HAUL IN " + stat.getMatches() + " MATCHES" + "\0", print_writers);
						}
						break;
					case "5WI":
						if(stat.getPlus5() == 1) {
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$Analytics$Side" + WhichSide + "$Milestone$Info"
									+ "$txt_Text*GEOM*TEXT SET " + "MAIDEN T20I 5 WICKET HAUL IN " + stat.getMatches() + " MATCHES" + "\0", print_writers);
						}else {
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$Analytics$Side" + WhichSide + "$Milestone$Info"
									+ "$txt_Text*GEOM*TEXT SET " + stat.getPlus5() + " T20I 5 WICKET HAUL IN " + stat.getMatches() + " MATCHES" + "\0", print_writers);
						}
						break;
					}
					break;
				
				case "PROJECTED_SCORE":
					inning = matchAllData.getMatch().getInning().stream().filter(inn -> inn.getInningNumber() == 1 &&
					inn.getIsCurrentInning().equalsIgnoreCase(CricketUtil.YES)).findAny().orElse(null);
			
					if(inning == null) {
						return "populateSectionAnalytics: 1st Inning returned is NULL";
					}
					this_data_str = CricketFunctions.projectedScore(matchAllData);
					
					if(this_data_str.size() <= 0) {
						return "populateSectionAnalytics: Projected score invalid";
					}
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$Analytics$Side" + WhichSide 
							+ "$Select_DataType*FUNCTION*Omo*vis_con SET 2\0", print_writers);
					
					switch(config.getBroadcaster()) {
					case Constants.TG20:
						CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$Analytics$Side" + WhichSide + "$Projected$RPO$Maxsize_Title"
								+ "$select_Language*FUNCTION*Omo*vis_con SET ", config, Constants.TG20, print_writers, foreignLanguageOmo);
						foreignLanguageData = CricketFunctions.AssembleMultiLanguageData(CricketUtil.DICTIONARY, "", multilanguagedata, "RUNS PER OVER", "", null, 0, foreignLanguageDataList);
						CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$Analytics$Side" + WhichSide + "$Projected$RPO$Maxsize_Title"
								+ "$English$txt_Title*GEOM*TEXT SET ",config, Constants.TG20, print_writers, foreignLanguageData);
						
						CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$Analytics$Side" + WhichSide + "$Projected$Score$Maxsize_Title"
								+ "$select_Language*FUNCTION*Omo*vis_con SET ", config, Constants.TG20, print_writers, foreignLanguageOmo);
						foreignLanguageData = CricketFunctions.AssembleMultiLanguageData(CricketUtil.DICTIONARY, "", multilanguagedata, "PROJECTED SCORE", "", null, 0, foreignLanguageDataList);
						CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$Analytics$Side" + WhichSide + "$Projected$Score$Maxsize_Title"
								+ "$English$txt_Title*GEOM*TEXT SET ",config, Constants.TG20, print_writers, foreignLanguageData);
						break;
					default:
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$Analytics$Side" + WhichSide + 
								"$Projected$RPO$txt_Title*GEOM*TEXT SET RUNS PER OVER\0", print_writers);
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$Analytics$Side" + WhichSide + 
								"$Projected$Score$txt_Title*GEOM*TEXT SET PROJECTED SCORES\0", print_writers);
						break;
					}

					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$Analytics$Side" + WhichSide + 
							"$Projected$RPO$txt_Stat_1*GEOM*TEXT SET @CRR (" + this_data_str.get(0) + ")\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$Analytics$Side" + WhichSide + 
							"$Projected$RPO$txt_Stat_2*GEOM*TEXT SET " + String.format("%.2f", Double.parseDouble(this_data_str.get(2))) + "\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$Analytics$Side" + WhichSide + 
							"$Projected$RPO$txt_Stat_3*GEOM*TEXT SET " + String.format("%.2f", Double.parseDouble(this_data_str.get(4))) + "\0", print_writers);
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$Analytics$Side" + WhichSide + 
							"$Projected$Score$txt_Stat_1*GEOM*TEXT SET " + this_data_str.get(1) + "\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$Analytics$Side" + WhichSide + 
							"$Projected$Score$txt_Stat_2*GEOM*TEXT SET " + this_data_str.get(3) + "\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$Analytics$Side" + WhichSide + 
							"$Projected$Score$txt_Stat_3*GEOM*TEXT SET " + this_data_str.get(5) + "\0", print_writers);
					break;
				case "PHASE_WISE_SCORE":
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$Analytics$Side" + WhichSide 
							+ "$Select_DataType*FUNCTION*Omo*vis_con SET 2\0", print_writers);
					
					if(inning.getInningNumber()==1) {
						phaseWiseScore = IndexController.MatchStats.getHomeFirstPowerPlay().getTotalRuns()+","+IndexController.MatchStats.getHomeFirstPowerPlay().getTotalWickets()+"_"+
										 IndexController.MatchStats.getHomeSecondPowerPlay().getTotalRuns()+","+IndexController.MatchStats.getHomeSecondPowerPlay().getTotalWickets()+"_"
										 +IndexController.MatchStats.getHomeThirdPowerPlay().getTotalRuns()+","+IndexController.MatchStats.getHomeThirdPowerPlay().getTotalWickets();
					}else if(inning.getInningNumber()==2) {
						phaseWiseScore = IndexController.MatchStats.getAwayFirstPowerPlay().getTotalRuns()+","+IndexController.MatchStats.getAwayFirstPowerPlay().getTotalWickets()+"_"+
								 IndexController.MatchStats.getAwaySecondPowerPlay().getTotalRuns()+","+IndexController.MatchStats.getAwaySecondPowerPlay().getTotalWickets()+"_"
								 +IndexController.MatchStats.getAwayThirdPowerPlay().getTotalRuns()+","+IndexController.MatchStats.getAwayThirdPowerPlay().getTotalWickets();
					}
					
					String PP1 ="-",PP2="-",PP3="-";
					if(Integer.valueOf(phaseWiseScore.split("_")[0].split(",")[0]) == 0 && Integer.valueOf(phaseWiseScore.split("_")[0].split(",")[1]) == 0) {
						if(Float.valueOf(CricketFunctions.OverBalls(inning.getTotalOvers(), inning.getTotalBalls())) > 0.0) {
							PP1 = "0-0";
						}
					}else {
						PP1 = phaseWiseScore.split("_")[0].split(",")[0]+"-"+phaseWiseScore.split("_")[0].split(",")[1];
					}
					if(Integer.valueOf(phaseWiseScore.split("_")[1].split(",")[0]) == 0 && Integer.valueOf(phaseWiseScore.split("_")[1].split(",")[1]) == 0) {
						if(Float.valueOf(CricketFunctions.OverBalls(inning.getTotalOvers(), inning.getTotalBalls())) > 6.0) {
							PP2 = "0-0";
						}
					}else {
						PP2 = phaseWiseScore.split("_")[1].split(",")[0]+"-"+phaseWiseScore.split("_")[1].split(",")[1];
					}
					if(Integer.valueOf(phaseWiseScore.split("_")[2].split(",")[0]) == 0 && Integer.valueOf(phaseWiseScore.split("_")[2].split(",")[1]) == 0) {
						if(Float.valueOf(CricketFunctions.OverBalls(inning.getTotalOvers(), inning.getTotalBalls())) > 15.0) {
							PP3 = "0-0";
						}
					}else {
						PP3 = phaseWiseScore.split("_")[2].split(",")[0]+"-"+phaseWiseScore.split("_")[2].split(",")[1];
					}
					
					switch(config.getBroadcaster()) {
					case Constants.TG20:
						CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$Analytics$Side" + WhichSide + "$Projected$RPO$Maxsize_Title"
								+ "$select_Language*FUNCTION*Omo*vis_con SET ", config, Constants.TG20, print_writers, foreignLanguageOmo);
						foreignLanguageData = CricketFunctions.AssembleMultiLanguageData(CricketUtil.DICTIONARY, "", multilanguagedata, "OVERS", "", null, 0, foreignLanguageDataList);
						CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$Analytics$Side" + WhichSide + "$Projected$RPO$Maxsize_Title"
								+ "$English$txt_Title*GEOM*TEXT SET ",config, Constants.TG20, print_writers, foreignLanguageData);
						
						CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$Analytics$Side" + WhichSide + "$Projected$Score$Maxsize_Title"
								+ "$select_Language*FUNCTION*Omo*vis_con SET ", config, Constants.TG20, print_writers, foreignLanguageOmo);
						foreignLanguageData = CricketFunctions.AssembleMultiLanguageData(CricketUtil.DICTIONARY, "", multilanguagedata, "SCORE", "", null, 0, foreignLanguageDataList);
						CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$Analytics$Side" + WhichSide + "$Projected$Score$Maxsize_Title"
								+ "$English$txt_Title*GEOM*TEXT SET ",config, Constants.TG20, print_writers, foreignLanguageData);
						break;
					default:
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$Analytics$Side" + WhichSide + 
								"$Projected$RPO$txt_Title*GEOM*TEXT SET OVERS\0", print_writers);
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$Analytics$Side" + WhichSide + 
								"$Projected$Score$txt_Title*GEOM*TEXT SET SCORES\0", print_writers);
						break;
					}
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$Analytics$Side" + WhichSide + 
							"$Projected$RPO$txt_Stat_1*GEOM*TEXT SET 1-6\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$Analytics$Side" + WhichSide + 
							"$Projected$RPO$txt_Stat_2*GEOM*TEXT SET 7-15\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$Analytics$Side" + WhichSide + 
							"$Projected$RPO$txt_Stat_3*GEOM*TEXT SET 16-20\0", print_writers);
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$Analytics$Side" + WhichSide + 
							"$Projected$Score$txt_Stat_1*GEOM*TEXT SET " + PP1 + "\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$Analytics$Side" + WhichSide + 
							"$Projected$Score$txt_Stat_2*GEOM*TEXT SET " + PP2 + "\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$Analytics$Side" + WhichSide + 
							"$Projected$Score$txt_Stat_3*GEOM*TEXT SET " + PP3 + "\0", print_writers);
					break;
				case "EXTRAS":
					inning = matchAllData.getMatch().getInning().stream().filter(inn -> inn.getIsCurrentInning().equalsIgnoreCase(CricketUtil.YES)).findAny().orElse(null);
					if(inning == null) {
						return "populateVizInfobarMiddleSection: Inning returned is NULL";
					}
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$Analytics$Side" + WhichSide 
							+ "$Select_DataType*FUNCTION*Omo*vis_con SET 4\0", print_writers);
					
					switch(config.getBroadcaster()) {
					case Constants.TG20:
						CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$Analytics$Side" + WhichSide + "$Extras$Top_Line"
								+ "$select_Langiage*FUNCTION*Omo*vis_con SET ", config, Constants.TG20, print_writers, foreignLanguageOmo);
						foreignLanguageData = CricketFunctions.AssembleMultiLanguageData(CricketUtil.DICTIONARY, "", multilanguagedata, "EXTRAS", "", null, 0, foreignLanguageDataList);
						CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$Analytics$Side" + WhichSide + "$Extras$Top_Line"
								+ "$English$txt_Title*GEOM*TEXT SET ",config, Constants.TG20, print_writers, foreignLanguageData);
						break;
					}
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$Analytics$Side" + WhichSide 
							+ "$Extras$Stat_1$txt_Runs*GEOM*TEXT SET " + inning.getTotalExtras() + "\0", print_writers);
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$Analytics$Side" + WhichSide 
							+ "$Extras$ExtraData$select_Data*FUNCTION*Omo*vis_con SET " + (inning.getTotalPenalties() > 0 ? 5: 4) + "\0", print_writers);
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$Analytics$Side" + WhichSide 
							+ "$Extras$ExtraData$1$txt_StatHead*GEOM*TEXT SET WIDE" + CricketFunctions.Plural(inning.getTotalWides()).toUpperCase() + "\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$Analytics$Side" + WhichSide 
							+ "$Extras$ExtraData$1$txt_StatValue*GEOM*TEXT SET " + inning.getTotalWides() + "\0", print_writers);
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$Analytics$Side" + WhichSide 
							+ "$Extras$ExtraData$2$txt_StatHead*GEOM*TEXT SET NO BALL" + CricketFunctions.Plural(inning.getTotalNoBalls()).toUpperCase() + "\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$Analytics$Side" + WhichSide 
							+ "$Extras$ExtraData$2$txt_StatValue*GEOM*TEXT SET " + inning.getTotalNoBalls() + "\0", print_writers);
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$Analytics$Side" + WhichSide 
							+ "$Extras$ExtraData$3$txt_StatHead*GEOM*TEXT SET LEG BYE" + CricketFunctions.Plural(inning.getTotalLegByes()).toUpperCase() + "\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$Analytics$Side" + WhichSide 
							+ "$Extras$ExtraData$3$txt_StatValue*GEOM*TEXT SET " + inning.getTotalLegByes() + "\0", print_writers);
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$Analytics$Side" + WhichSide 
							+ "$Extras$ExtraData$4$txt_StatHead*GEOM*TEXT SET BYE" + CricketFunctions.Plural(inning.getTotalByes()).toUpperCase() + "\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$Analytics$Side" + WhichSide 
							+ "$Extras$ExtraData$4$txt_StatValue*GEOM*TEXT SET " + inning.getTotalByes() + "\0", print_writers);
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$Analytics$Side" + WhichSide 
							+ "$Extras$ExtraData$5$txt_StatHead*GEOM*TEXT SET PENALTIES\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$Analytics$Side" + WhichSide 
							+ "$Extras$ExtraData$5$txt_StatValue*GEOM*TEXT SET " + inning.getTotalPenalties() + "\0", print_writers);
					break;
				case "EQUATION":
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$Analytics$Side" + WhichSide 
							+ "$Select_DataType*FUNCTION*Omo*vis_con SET 3\0", print_writers);

					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$Analytics$Side" + WhichSide + "$Equation$Top_Line$"
							+ "Stat_1$txt_Runs*GEOM*TEXT SET " + CricketFunctions.GetTargetData(matchAllData).getRemaningRuns() + "\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$Analytics$Side" + WhichSide + "$Equation$Top_Line$"
							+ "txt_Title*GEOM*TEXT SET " + "RUN" + CricketFunctions.Plural(CricketFunctions.GetTargetData(matchAllData).getRemaningRuns()).toUpperCase() 
							+ "\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$Analytics$Side" + WhichSide + "$Equation$Top_Line$"
							+ "Stat_2$txt_Balls*GEOM*TEXT SET " + CricketFunctions.GetTargetData(matchAllData).getRemaningBall() + "\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$Analytics$Side" + WhichSide + "$Equation$Top_Line$"
							+ "txt_Title2*GEOM*TEXT SET " + "BALL" + CricketFunctions.Plural(CricketFunctions.GetTargetData(matchAllData).getRemaningBall()).toUpperCase() 
							+ "\0", print_writers);
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$Analytics$Side" + WhichSide + "$Equation$Top_Line$"
							+ "txt_DLS*GEOM*TEXT SET " + (matchAllData.getSetup().getTargetType() != null && !matchAllData.getSetup().getTargetType().isEmpty()
							? matchAllData.getSetup().getTargetType().toUpperCase():"") + "\0", print_writers);
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$Analytics$Side" + WhichSide + "$Equation$Bottom_Line$"
							+ "txt_Title*GEOM*TEXT SET REQUIRED RUN RATE : " + CricketFunctions.generateRunRate(CricketFunctions.GetTargetData(matchAllData).getRemaningRuns(),
									0,CricketFunctions.GetTargetData(matchAllData).getRemaningBall(),2,matchAllData) + "\0", print_writers);
					break;
				case "BIG_EQUATION":
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$Analytics$Side" + WhichSide 
							+ "$Select_DataType*FUNCTION*Omo*vis_con SET 5\0", print_writers);
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$Analytics$Side" + WhichSide + "$EquationBig$Top_Line$"
							+ "Stat_1$txt_Runs*GEOM*TEXT SET " + CricketFunctions.GetTargetData(matchAllData).getRemaningRuns() + "\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$Analytics$Side" + WhichSide + "$EquationBig$Top_Line$"
							+ "txt_Title*GEOM*TEXT SET " + "RUN" + CricketFunctions.Plural(CricketFunctions.GetTargetData(matchAllData).getRemaningRuns()).toUpperCase() 
							+ "\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$Analytics$Side" + WhichSide + "$EquationBig$Top_Line$"
							+ "Stat_2$txt_Balls*GEOM*TEXT SET " + CricketFunctions.GetTargetData(matchAllData).getRemaningBall() + "\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$Analytics$Side" + WhichSide + "$EquationBig$Top_Line$"
							+ "txt_Title2*GEOM*TEXT SET " + "BALL" + CricketFunctions.Plural(CricketFunctions.GetTargetData(matchAllData).getRemaningBall()).toUpperCase() 
							+ "\0", print_writers);
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$Analytics$Side" + WhichSide + "$EquationBig$Top_Line$"
							+ "txt_DLS*GEOM*TEXT SET " + (matchAllData.getSetup().getTargetType() != null && !matchAllData.getSetup().getTargetType().isEmpty()
							? matchAllData.getSetup().getTargetType().toUpperCase():"") + "\0", print_writers);
					
					break;
				case "BATSMANTIMELINE":
					inning = matchAllData.getMatch().getInning().stream().filter(inn ->inn.getIsCurrentInning().equalsIgnoreCase(CricketUtil.YES)).findAny().orElse(null);
					BattingCard batter = inning.getBattingCard().stream().filter(bat->bat.getPlayer().getPlayerId()== FirstPlayerId).findAny().orElse(null);
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$Analytics$Side" + WhichSide 
							+ "$Select_DataType*FUNCTION*Omo*vis_con SET 8\0", print_writers);
					
					switch(config.getBroadcaster()) {
					case Constants.TG20:
						CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$Analytics$Side" + WhichSide + "$PlayerTimeLine$TopText"
								+ "$select_Language*FUNCTION*Omo*vis_con SET ", config, Constants.TG20, print_writers, foreignLanguageOmo);
						foreignLanguageData = CricketFunctions.AssembleMultiLanguageData(CricketUtil.PLAYER, CricketUtil.TICKERNAME, multilanguagedata, batter.getPlayer().getTicker_name(), 
								"", null, 0, foreignLanguageDataList);
						CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$Analytics$Side" + WhichSide + "$PlayerTimeLine$TopText"
								+ "$English$txt_Name*GEOM*TEXT SET ",config, Constants.TG20, print_writers, foreignLanguageData);
						
						CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$Analytics$Side" + WhichSide + "$PlayerTimeLine$TopText"
								+ "$select_Language*FUNCTION*Omo*vis_con SET ", config, Constants.TG20, print_writers, foreignLanguageOmo);
						foreignLanguageData = CricketFunctions.AssembleMultiLanguageData(CricketUtil.DICTIONARY, "", multilanguagedata, "LAST 12 BALLS", "", null, 0, foreignLanguageDataList);
						CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$Analytics$Side" + WhichSide + "$PlayerTimeLine$TopText"
								+ "$English$txt_Text*GEOM*TEXT SET ",config, Constants.TG20, print_writers, foreignLanguageData);
						break;
					default:
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$Analytics$Side" + WhichSide + "$PlayerTimeLine$TopText"
								+ "$txt_Name*GEOM*TEXT SET " + batter.getPlayer().getTicker_name() + "\0", print_writers);
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$Analytics$Side" + WhichSide + "$PlayerTimeLine$TopText"
								+ "$txt_Text*GEOM*TEXT SET LAST 10 BALLS\0", print_writers);
						break;
					}
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$Analytics$Side" + WhichSide + "$PlayerTimeLine$ScoreAll"
							+ "$txt_Runs*GEOM*TEXT SET " + batter.getRuns() + (batter.getStatus().equalsIgnoreCase(CricketUtil.NOT_OUT)?"*":"") + "\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$Analytics$Side" + WhichSide + "$PlayerTimeLine$ScoreAll"
							+ "$txt_Balls*GEOM*TEXT SET " + batter.getBalls() + "\0", print_writers);
					
					this_data_str = new ArrayList<String>();
					this_data_str.add(String.join(",",Arrays.stream(IndexController.MatchStats.getPlayerStats().stream()
						     .filter(ply -> ply.getStatsType().equalsIgnoreCase(CricketUtil.BAT)&& FirstPlayerId == ply.getId())
						     .findAny().orElse(null).getThisOverTxt().split(","))
						     .filter(s -> !s.contains("BYE") && !s.contains("LEG_BYE") && !s.contains("NO_BALL"))
						     .map(s -> s.replace("WIDE", "WD").replace("LEG_BYE", "LB").replace("BYE", "B")
						     .replace("NO_BALL", "NB").replace("PENALTY", "PN").replace("LOG_WICKET", "W")
						     .replace("WICKET", "W")).collect(Collectors.toList())));

					if(this_data_str.get(this_data_str.size()-1) == null) {
						return "populateSectionAnalytics: This over data returned invalid";
					}
					
					String[] plyrelements = Arrays.stream(this_data_str.get(this_data_str.size() - 1).split(","))
						    .map(String::trim).filter(s -> !s.isEmpty()).toArray(String[]::new);
					
					if (plyrelements.length > 12) {
						plyrelements = Arrays.copyOfRange(plyrelements, plyrelements.length - 12, plyrelements.length);
					}
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$Analytics$Side" + WhichSide + "$PlayerTimeLine$AllBalls"
							+ "$select_BallNumber*FUNCTION*Omo*vis_con SET " + Math.min(plyrelements.length, 12) + "\0", print_writers);
					
					for(int iBall = 0; iBall < plyrelements.length; iBall++) {
						if(iBall < 12) {
							switch (plyrelements[iBall].toUpperCase()) {
							case CricketUtil.DOT:
								CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$Analytics$Side" + WhichSide + 
										"$PlayerTimeLine$AllBalls$Ball_" + (iBall + 1) + "$Select_DataType*FUNCTION*Omo*vis_con SET 1\0", print_writers);
								CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$Analytics$Side" + WhichSide + 
										"$PlayerTimeLine$AllBalls$Ball_" + (iBall + 1) + "$Runs$select_LineNumber*FUNCTION*Omo*vis_con SET 1\0", print_writers);
								CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$Analytics$Side" + WhichSide + 
										"$PlayerTimeLine$AllBalls$Ball_" + (iBall + 1) + "$Runs$1_Line$txt_Figures1*GEOM*TEXT SET 0\0", print_writers);
								break;
							case CricketUtil.ONE: case CricketUtil.TWO: case CricketUtil.THREE: case CricketUtil.FIVE: case CricketUtil.FOUR: case CricketUtil.SIX:
								CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$Analytics$Side" + WhichSide + 
										"$PlayerTimeLine$AllBalls$Ball_" + (iBall + 1) + "$Select_DataType*FUNCTION*Omo*vis_con SET 1\0", print_writers);
								CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$Analytics$Side" + WhichSide + 
										"$PlayerTimeLine$AllBalls$Ball_" + (iBall + 1) + "$Runs$select_LineNumber*FUNCTION*Omo*vis_con SET 1\0", print_writers);
								CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$Analytics$Side" + WhichSide + 
										"$PlayerTimeLine$AllBalls$Ball_" + (iBall + 1) + "$Runs$1_Line$txt_Figures1*GEOM*TEXT SET " + plyrelements[iBall] + "\0", print_writers);
								break;
							default:
								if(plyrelements[iBall].toUpperCase().contains("BOUNDARY")) {
									if(plyrelements[iBall].toUpperCase().equalsIgnoreCase("6BOUNDARY")||plyrelements[iBall].toUpperCase().equalsIgnoreCase("4BOUNDARY")) {
										CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$Analytics$Side" + WhichSide + 
												"$PlayerTimeLine$AllBalls$Ball_" + (iBall + 1) + "$Select_DataType*FUNCTION*Omo*vis_con SET 2\0", print_writers);
										CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$Analytics$Side" + WhichSide + 
												"$PlayerTimeLine$AllBalls$Ball_" + (iBall + 1) + "$Boundaries$select_LineNumber*FUNCTION*Omo*vis_con SET 1\0", print_writers);
										CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$Analytics$Side" + WhichSide + 
												"$PlayerTimeLine$AllBalls$Ball_" + (iBall + 1) + "$Boundaries$1_Line$txt_Figures1*GEOM*TEXT SET " + 
												plyrelements[iBall].toUpperCase().replace("BOUNDARY", "") + "\0", print_writers);
										
										CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$Analytics$Side" + WhichSide + "$PlayerTimeLine$AllBalls$"
												+ "Ball_" + (iBall + 1) + "$Boundaries$img_Base2*TEXTURE*IMAGE SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? 
												Constants.TRI_SERIES_BASE2 : config.getBroadcaster().equalsIgnoreCase(Constants.MT20) ? Constants.MT20_BASE2 :Constants.TG20_BASE2) 
												+ inning.getBatting_team().getTeamBadge() + "\0", print_writers);
										CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$Analytics$Side" + WhichSide + "$PlayerTimeLine$AllBalls"
												+ "$Ball_" + (iBall + 1) + "$Boundaries$img_Text2*TEXTURE*IMAGE SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? 
												Constants.TRI_SERIES_TEXT2 : config.getBroadcaster().equalsIgnoreCase(Constants.MT20) ? Constants.MT20_TEXT2 :Constants.TG20_TEXT2) + 
												inning.getBatting_team().getTeamBadge() + "\0", print_writers);
									}
								}else {
									CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$Analytics$Side" + WhichSide + 
											"$TimeLine$AllBalls$Ball_" + (iBall + 1) + "$Select_DataType*FUNCTION*Omo*vis_con SET 1\0", print_writers);
									
									if(plyrelements[iBall].contains("+")) {
										String line1 = "",line2 = "";
										String[] Timeparts = plyrelements[iBall].split("\\+");
										if (Timeparts.length == 2) {
									        line1 = Timeparts[0];
									        line2 = Timeparts[1];
									    } else if (Timeparts.length == 3) {
									        line1 = Timeparts[0];
									        line2 = Timeparts[1] + "+" + Timeparts[2];
									    }
										
										CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$Analytics$Side" + WhichSide + 
												"$PlayerTimeLine$AllBalls$Ball_" + (iBall + 1) + "$Runs$select_LineNumber*FUNCTION*Omo*vis_con SET 2\0", print_writers);
										
										CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$Analytics$Side" + WhichSide + 
												"$PlayerTimeLine$AllBalls$Ball_" + (iBall + 1) + "$Runs$2_Line$txt_Figures1*GEOM*TEXT SET " + line1.toUpperCase() + "\0", print_writers);
										CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$Analytics$Side" + WhichSide + 
												"$PlayerTimeLine$AllBalls$Ball_" + (iBall + 1) + "$Runs$2_Line$txt_Figures2*GEOM*TEXT SET " + line2.toUpperCase() + "\0", print_writers);
									}else {
										CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$Analytics$Side" + WhichSide + 
												"$PlayerTimeLine$AllBalls$Ball_" + (iBall + 1) + "$Runs$select_LineNumber*FUNCTION*Omo*vis_con SET 1\0", print_writers);
										CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$Analytics$Side" + WhichSide + 
												"$PlayerTimeLine$AllBalls$Ball_" + (iBall + 1) + "$Runs$1_Line$txt_Figures1*GEOM*TEXT SET " + plyrelements[iBall] + "\0", print_writers);
									}
								}
								break;
							}
						}
					}
					break;
				case "TIMELINE":
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$Analytics$Side" + WhichSide 
							+ "$Select_DataType*FUNCTION*Omo*vis_con SET 7\0", print_writers);
					
					switch(config.getBroadcaster()) {
					case Constants.TG20:
						CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$Analytics$Side" + WhichSide + "$TimeLine$TopText"
								+ "$select_Language*FUNCTION*Omo*vis_con SET ", config, Constants.TG20, print_writers, foreignLanguageOmo);
						foreignLanguageData = CricketFunctions.AssembleMultiLanguageData(CricketUtil.DICTIONARY, "", multilanguagedata, "TIMELINE", "", null, 0, foreignLanguageDataList);
						CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$Analytics$Side" + WhichSide + "$TimeLine$TopText"
								+ "$English$txt_Name*GEOM*TEXT SET ",config, Constants.TG20, print_writers, foreignLanguageData);
						
						CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$Analytics$Side" + WhichSide + "$TimeLine$TopText"
								+ "$select_Language*FUNCTION*Omo*vis_con SET ", config, Constants.TG20, print_writers, foreignLanguageOmo);
						foreignLanguageData = CricketFunctions.AssembleMultiLanguageData(CricketUtil.DICTIONARY, "", multilanguagedata, " ", "", null, 0, foreignLanguageDataList);
						CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$Analytics$Side" + WhichSide + "$TimeLine$TopText"
								+ "$English$txt_Text*GEOM*TEXT SET ",config, Constants.TG20, print_writers, foreignLanguageData);
						break;
					default:
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$Analytics$Side" + WhichSide + "$TimeLine$"
								+ "TopText$txt_Name*GEOM*TEXT SET TIMELINE\0", print_writers);
						break;
					}
					
					
					this_data_str = new ArrayList<String>();
					this_data_str.add(String.join(",",  new ArrayList<>(Arrays.asList(IndexController.MatchStats.getTimeLine().split(",")))
					        .stream().map(s -> s.replace("WIDE", "WD").replace("NO_BALL", "NB").replace("LEG_BYE", "LB").replace("BYE", "B")
					        .replace("PENALTY", "PN").replace("LOG_WICKET", "W").replace("WICKET", "W"))
					        .collect(Collectors.collectingAndThen(Collectors.toList(), list -> {Collections.reverse(list); return list;}))
					        .toArray(new String[0])));
					
					if(this_data_str.get(this_data_str.size()-1) == null || this_data_str.get(this_data_str.size()-1).split(",").length < 6) {
						return "populateSectionAnalytics: TIMELINE data returned invalid";
					}
					//String[] elements = this_data_str.get(this_data_str.size() - 1).split(",");
					
					String[] elements = Arrays.stream(this_data_str.get(this_data_str.size() - 1).split(","))
					    .map(String::trim).filter(s -> !s.isEmpty()).toArray(String[]::new);
					
					if (elements.length > 18) {
					    elements = Arrays.copyOfRange(elements, elements.length - 18, elements.length);
					}
					Collections.reverse(Arrays.asList(elements));
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$Analytics$Side" + WhichSide + "$TimeLine$AllBalls"
							+ "$select_BallNumber*FUNCTION*Omo*vis_con SET " + Math.min(elements.length, 18) + "\0", print_writers);
					
					for(int iBall = 0; iBall < elements.length; iBall++) {
						if(iBall < elements.length) {
							switch (elements[iBall].toUpperCase()) {
							case "|":
								CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$Analytics$Side" + WhichSide + 
										"$TimeLine$AllBalls$Ball_" + (iBall + 1) + "$Select_DataType*FUNCTION*Omo*vis_con SET 0\0", print_writers);		
								break;
							case CricketUtil.DOT:
								CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$Analytics$Side" + WhichSide + 
										"$TimeLine$AllBalls$Ball_" + (iBall + 1) + "$Select_DataType*FUNCTION*Omo*vis_con SET 1\0", print_writers);
								CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$Analytics$Side" + WhichSide + 
										"$TimeLine$AllBalls$Ball_" + (iBall + 1) + "$Runs$select_LineNumber*FUNCTION*Omo*vis_con SET 1\0", print_writers);
								CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$Analytics$Side" + WhichSide + 
										"$TimeLine$AllBalls$Ball_" + (iBall + 1) + "$Runs$1_Line$txt_Figures1*GEOM*TEXT SET 0\0", print_writers);
								break;
							case CricketUtil.ONE: case CricketUtil.TWO: case CricketUtil.THREE: case CricketUtil.FIVE:
							case CricketUtil.FOUR: case CricketUtil.SIX:
								CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$Analytics$Side" + WhichSide + 
										"$TimeLine$AllBalls$Ball_" + (iBall + 1) + "$Select_DataType*FUNCTION*Omo*vis_con SET 1\0", print_writers);
								CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$Analytics$Side" + WhichSide + 
										"$TimeLine$AllBalls$Ball_" + (iBall + 1) + "$Runs$select_LineNumber*FUNCTION*Omo*vis_con SET 1\0", print_writers);
								CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$Analytics$Side" + WhichSide + 
										"$TimeLine$AllBalls$Ball_" + (iBall + 1) + "$Runs$1_Line$txt_Figures1*GEOM*TEXT SET " + elements[iBall] + "\0", print_writers);
								break;	
							case "W":
								CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$Analytics$Side" + WhichSide + 
										"$TimeLine$AllBalls$Ball_" + (iBall + 1) + "$Select_DataType*FUNCTION*Omo*vis_con SET 3\0", print_writers);
								CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$Analytics$Side" + WhichSide + 
										"$TimeLine$AllBalls$Ball_" + (iBall + 1) + "$Wicket$select_LineNumber*FUNCTION*Omo*vis_con SET 1\0", print_writers);
								CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$Analytics$Side" + WhichSide + 
										"$TimeLine$AllBalls$Ball_" + (iBall + 1) + "$Wicket$1_Line$txt_Figures1*GEOM*TEXT SET W\0", print_writers);
								
								CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$Analytics$Side" + WhichSide + 
										"$TimeLine$AllBalls$Ball_" + (iBall + 1) + "$Wicket$img_Base2*TEXTURE*IMAGE SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? 
												Constants.TRI_SERIES_BASE2 : config.getBroadcaster().equalsIgnoreCase(Constants.MT20) ? Constants.MT20_BASE2 :Constants.TG20_BASE2) + 
										inning.getBowling_team().getTeamBadge() + "\0", print_writers);
								CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$Analytics$Side" + WhichSide + 
										"$TimeLine$AllBalls$Ball_" + (iBall + 1) + "$Wicket$img_Text2*TEXTURE*IMAGE SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? 
												Constants.TRI_SERIES_TEXT2 : config.getBroadcaster().equalsIgnoreCase(Constants.MT20) ? Constants.MT20_TEXT2 :Constants.TG20_TEXT2) + 
										inning.getBowling_team().getTeamBadge() + "\0", print_writers);
								break;

							default:
								if(elements[iBall].toUpperCase().contains("BOUNDARY")) {
									if(elements[iBall].toUpperCase().equalsIgnoreCase("6BOUNDARY")||elements[iBall].toUpperCase().equalsIgnoreCase("4BOUNDARY")) {
										CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$Analytics$Side" + WhichSide + 
												"$TimeLine$AllBalls$Ball_" + (iBall + 1) + "$Select_DataType*FUNCTION*Omo*vis_con SET 2\0", print_writers);
										CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$Analytics$Side" + WhichSide + 
												"$TimeLine$AllBalls$Ball_" + (iBall + 1) + "$Boundaries$select_LineNumber*FUNCTION*Omo*vis_con SET 1\0", print_writers);
										CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$Analytics$Side" + WhichSide + 
												"$TimeLine$AllBalls$Ball_" + (iBall + 1) + "$Boundaries$1_Line$txt_Figures1*GEOM*TEXT SET " + 
												elements[iBall].toUpperCase().replace("BOUNDARY", "") + "\0", print_writers);
										
										CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$Analytics$Side" + WhichSide + "$TimeLine$AllBalls"
												+ "$Ball_" + (iBall + 1) + "$Boundaries$img_Base2*TEXTURE*IMAGE SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? 
													Constants.TRI_SERIES_BASE2 : config.getBroadcaster().equalsIgnoreCase(Constants.MT20) ? Constants.MT20_BASE2 :Constants.TG20_BASE2) + 
												inning.getBatting_team().getTeamBadge() + "\0", print_writers);
										CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$Analytics$Side" + WhichSide + "$TimeLine$AllBalls"
												+ "$Ball_" + (iBall + 1) + "$Boundaries$img_Text2*TEXTURE*IMAGE SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? 
													Constants.TRI_SERIES_TEXT2 : config.getBroadcaster().equalsIgnoreCase(Constants.MT20) ? Constants.MT20_TEXT2 :Constants.TG20_TEXT2) + 
												inning.getBatting_team().getTeamBadge() + "\0", print_writers);
									}
								}else {
									CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$Analytics$Side" + WhichSide + 
											"$TimeLine$AllBalls$Ball_" + (iBall + 1) + "$Select_DataType*FUNCTION*Omo*vis_con SET 1\0", print_writers);
									
									if(elements[iBall].contains("+")) {
										String line1 = "",line2 = "";
										String[] Timeparts = elements[iBall].split("\\+");
										if (Timeparts.length == 2) {
									        line1 = Timeparts[0];
									        line2 = Timeparts[1];
									    } else if (Timeparts.length == 3) {
									        line1 = Timeparts[0];
									        line2 = Timeparts[1] + "+" + Timeparts[2];
									    }
										
										CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$Analytics$Side" + WhichSide + 
												"$TimeLine$AllBalls$Ball_" + (iBall + 1) + "$Runs$select_LineNumber*FUNCTION*Omo*vis_con SET 2\0", print_writers);
										
										CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$Analytics$Side" + WhichSide + 
												"$TimeLine$AllBalls$Ball_" + (iBall + 1) + "$Runs$2_Line$txt_Figures1*GEOM*TEXT SET " + line1.toUpperCase() + "\0", print_writers);
										CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$Analytics$Side" + WhichSide + 
												"$TimeLine$AllBalls$Ball_" + (iBall + 1) + "$Runs$2_Line$txt_Figures2*GEOM*TEXT SET " + line2.toUpperCase() + "\0", print_writers);
									}else {
										CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$Analytics$Side" + WhichSide + 
												"$TimeLine$AllBalls$Ball_" + (iBall + 1) + "$Runs$select_LineNumber*FUNCTION*Omo*vis_con SET 1\0", print_writers);
										
										if(elements[iBall].contains("PN")) {
											CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$Analytics$Side" + WhichSide + 
													"$TimeLine$AllBalls$Ball_" + (iBall + 1) + "$Runs$1_Line$txt_Figures1*GEOM*TEXT SET 5PN\0", print_writers);
										}else {
											CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$Analytics$Side" + WhichSide + 
													"$TimeLine$AllBalls$Ball_" + (iBall + 1) + "$Runs$1_Line$txt_Figures1*GEOM*TEXT SET " + elements[iBall] + "\0", print_writers);
										}
									}
								}
								break;
							}
						}
					}
					break;
				}
			}
			break;
		}
		return Constants.OK;
	}
	public String populateSectionLtAnalytics(List<PrintWriter> print_writers, MatchAllData matchAllData, int WhichSide) throws JsonMappingException, JsonProcessingException, InterruptedException {
		switch(config.getBroadcaster()) {
		case Constants.TRI_SERIES:  case Constants.MT20: case Constants.TG20:
			if(infobar.getSectionLtAnalytics() != null && !infobar.getSectionLtAnalytics().isEmpty()) {
				switch(infobar.getSectionLtAnalytics().toUpperCase()) {
				case "BAT_PP": case "BALL_PP":
					int k =0;
					String best = "-";
					if(PP_Id <= 0 || WhichProfile == null) {
						return "InfoBarPlayerProfile: Player Id NOT found [" + PP_Id + "]";
					}
					player = CricketFunctions.getPlayerFromMatchData(PP_Id, matchAllData); 
					if(player == null) {
						return "InfoBarPlayerProfile: Player Id not found [" + PP_Id + "]";
					}
					
					team = teams.stream().filter(tm -> tm.getTeamId() == player.getTeamId()).findAny().orElse(null);
					if(team == null) {
						return "InfoBarPlayerProfile: team Id not found [" + PP_Id + "]";
					}
					
					if(WhichProfile.equalsIgnoreCase("DT20")) {
						statsType = statsTypes.stream().filter(st -> st.getStatsShortName().equalsIgnoreCase(WhichProfile)).findAny().orElse(null);
						if(statsType == null) {
							return "InfoBarPlayerProfile: Stats Type not found for profile [" + WhichProfile + "]";
						}

						stat = statistics.stream().filter(st -> st.getPlayerID() == PP_Id && statsType.getStatsId() == st.getStatsTypeId()).findAny().orElse(null);
						if(stat == null) {
							return "InfoBarPlayerProfile: Stats not found for Player Id [" + PP_Id + "]";
						}
//						statsType = statsTypes.stream().filter(st -> st.getStatsShortName().equalsIgnoreCase("IT20")).findAny().orElse(null);
//						stat.setStats_type(statsType);
//						
//						stat = CricketFunctions.updateTournamentWithH2h(stat, headToHead, matchAllData, CricketUtil.FULL);
//						stat = CricketFunctions.updateStatisticsWithMatchData(stat, matchAllData, CricketUtil.FULL);
					}else if(WhichProfile.equalsIgnoreCase("IT20")) {
						statsType = statsTypes.stream().filter(st -> st.getStatsShortName().equalsIgnoreCase(WhichProfile)).findAny().orElse(null);
						if(statsType == null) {
							return "InfoBarPlayerProfile: Stats Type not found for profile [" + WhichProfile + "]";
						}
						stat = statistics.stream().filter(st -> st.getPlayerID() == PP_Id && statsType.getStatsId() == st.getStatsTypeId()).findAny().orElse(null);
						if(stat == null) {
							return "InfoBarPlayerProfile: Stats not found for Player Id [" + PP_Id + "]";
						}
//						statsType = statsTypes.stream().filter(st -> st.getStatsShortName().equalsIgnoreCase(WhichProfile)).findAny().orElse(null);
//						stat.setStats_type(statsType);
//						
//						stat = CricketFunctions.updateTournamentWithH2h(stat, headToHead, matchAllData, CricketUtil.FULL);
//						stat = CricketFunctions.updateStatisticsWithMatchData(stat, matchAllData, CricketUtil.FULL);
					}else if(WhichProfile.equalsIgnoreCase("THIS_SERIES")) {
						this_series = CricketFunctions.extractTournamentData("CURRENT_MATCH_DATA", false, headToHead, cricketService, matchAllData, past_tournament_stats);
						tournament = this_series.stream().filter(st -> st.getPlayerId() == PP_Id).findAny().orElse(null);
						
						for(Tournament tourn : this_series) {
							for(BestStats bs : tourn.getBatsman_best_Stats()) {
								top_batsman_beststats.add(bs);
							}
							for(BestStats bfig : tourn.getBowler_best_Stats()) {
								top_bowler_beststats.add(bfig);
							}
						}
						
						Collections.sort(top_batsman_beststats, new CricketFunctions.PlayerBestStatsComparator());
						Collections.sort(top_bowler_beststats, new CricketFunctions.PlayerBestStatsComparator());
						
						switch(infobar.getSectionLtAnalytics().toUpperCase()) {
						case "BAT_PP":
							for(int j=0;j<= top_batsman_beststats.size()-1;j++) {
								if(top_batsman_beststats.get(j).getPlayerId() == PP_Id) {
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
							break;
						case "BALL_PP":
							for(int j=0;j<= top_bowler_beststats.size()-1;j++) {
								if(top_bowler_beststats.get(j).getPlayerId() == PP_Id) {
									if(k == 1) {
										break;
									}
									if(k == 0) {
										k += 1;
										if(top_bowler_beststats.get(j).getBestEquation() % 1000 > 0) {
											best = ((top_bowler_beststats.get(j).getBestEquation() / 1000) +1) + "-" + (1000 - (top_bowler_beststats.get(j).getBestEquation() % 1000));
											break;
										}
										else if(top_bowler_beststats.get(j).getBestEquation() % 1000 < 0) {
											best = (top_bowler_beststats.get(j).getBestEquation() / 1000) + "-" + Math.abs(top_bowler_beststats.get(j).getBestEquation());
											break;
										}
										else if(top_bowler_beststats.get(j).getBestEquation() != 0) {
											if(top_bowler_beststats.get(j).getBestEquation() % 1000 == 0) {
												best = (top_bowler_beststats.get(j).getBestEquation() / 1000) + "-" + "0";
												break;
											}
										}
										break;
									}
								}else if(top_bowler_beststats.get(j).getPlayerId() != PP_Id) {
									best = "-";
								}
							}
							break;
						}
						
					}else {
						statsType = statsTypes.stream().filter(st -> st.getStatsShortName().equalsIgnoreCase(WhichProfile)).findAny().orElse(null);
						if(statsType == null) {
							return "InfoBarPlayerProfile: Stats Type not found for profile [" + WhichProfile + "]";
						}
						stat = statistics.stream().filter(st -> st.getPlayerID() == PP_Id && statsType.getStatsId() == st.getStatsTypeId()).findAny().orElse(null);
						if(stat == null) {
							return "InfoBarPlayerProfile: Stats not found for Player Id [" + PP_Id + "]";
						}
					}
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$Lt_Analytics$HeaderGrp$TextAll$Side" 
							+ WhichSide + "$img_Text2$select_DataType*FUNCTION*Omo*vis_con SET 0\0",print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$Lt_Analytics$HeaderGrp$LogoAll$Side" 
							+ WhichSide + "$img_Logos*TEXTURE*IMAGE SET " + Constants.TG20_LOGO + team.getTeamBadge() + "\0", print_writers);
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$Lt_Analytics$SublineGrp$Rows$Side" 
							+ WhichSide + "$1$Left*ACTIVE SET 0\0",print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$Lt_Analytics$SublineGrp$Rows$Side" 
							+ WhichSide + "$1$Right*ACTIVE SET 0\0",print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$Lt_Analytics$SublineGrp$Rows$Side" 
							+ WhichSide + "$1$Title*ACTIVE SET 1\0",print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$Lt_Analytics$SublineGrp$Rows$Side" 
							+ WhichSide + "$1$Stat*ACTIVE SET 0\0",print_writers);
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$Lt_Analytics$SublineGrp$Rows$Side" 
							+ WhichSide + "$2$Left*ACTIVE SET 0\0",print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$Lt_Analytics$SublineGrp$Rows$Side" 
							+ WhichSide + "$2$Right*ACTIVE SET 0\0",print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$Lt_Analytics$SublineGrp$Rows$Side" 
							+ WhichSide + "$2$Title*ACTIVE SET 0\0",print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$Lt_Analytics$SublineGrp$Rows$Side" 
							+ WhichSide + "$2$Stat*ACTIVE SET 1\0",print_writers);
					
					for(int j=1;j<=10;j++) {
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$Lt_Analytics$SublineGrp$Rows$Side" 
								+ WhichSide + "$1$Title$" + j + "*ACTIVE SET " + (j<=5 ? "1" : "0") + "\0",print_writers);
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$Lt_Analytics$SublineGrp$Rows$Side" 
								+ WhichSide + "$2$Stat$txt_" + j + "*ACTIVE SET " + (j<=5 ? "1" : "0") + "\0",print_writers);
					}
					
					if(WhichProfile.equalsIgnoreCase("DT20")) {
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$Lt_Analytics$HeaderGrp$TextAll$Side" 
								+ WhichSide + "$img_Text2$txt_Designation*GEOM*TEXT SET " + "T20 CAREER" + "\0", print_writers);
					}else if(WhichProfile.equalsIgnoreCase("IT20")){
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$Lt_Analytics$HeaderGrp$TextAll$Side" 
								+ WhichSide + "$img_Text2$txt_Designation*GEOM*TEXT SET " + "T20I CAREER" + "\0", print_writers);
					}else if(WhichProfile.equalsIgnoreCase("ODI")) {
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$Lt_Analytics$HeaderGrp$TextAll$Side" 
								+ WhichSide + "$img_Text2$txt_Designation*GEOM*TEXT SET " + "ODI CAREER" + "\0", print_writers);
					}else if(WhichProfile.equalsIgnoreCase("Test")){
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$Lt_Analytics$HeaderGrp$TextAll$Side" 
								+ WhichSide + "$img_Text2$txt_Designation*GEOM*TEXT SET " + "TEST CAREER" + "\0", print_writers);
					}else if(WhichProfile.equalsIgnoreCase("THIS_SERIES")){
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$Lt_Analytics$HeaderGrp$TextAll$Side" 
								+ WhichSide + "$img_Text2$txt_Designation*GEOM*TEXT SET " + "THIS SERIES" + "\0", print_writers);
					}
					
					teamColorAndPositon(print_writers, WhichSide);
					
					switch(config.getBroadcaster()) {
					case Constants.TG20:
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$Lt_Analytics$HeaderGrp$Text$Side" + WhichSide + 
								"$English$select_DataType*FUNCTION*Omo*vis_con SET 0\0",print_writers);
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$Lt_Analytics$HeaderGrp$Text$Side" + WhichSide + 
								"$Telugu$select_DataType*FUNCTION*Omo*vis_con SET 0\0",print_writers);
						
						CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$Lt_Analytics$HeaderGrp$Text$Side" + WhichSide + 
								"$select_Language*FUNCTION*Omo*vis_con SET ",config, Constants.TG20, print_writers, foreignLanguageOmo);
						foreignLanguageData = CricketFunctions.AssembleMultiLanguageData(CricketUtil.PLAYER, CricketUtil.FIRSTNAME, multilanguagedata, player.getFull_name(),
									"", null, 0, foreignLanguageDataList);
						CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$Lt_Analytics$HeaderGrp$Text$Side" + WhichSide + 
								"$English$Name$txt_FirstName*GEOM*TEXT SET ", config, Constants.TG20, print_writers, foreignLanguageData);
						
						CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$Lt_Analytics$HeaderGrp$Text$Side" + WhichSide + 
								"$select_Language*FUNCTION*Omo*vis_con SET ",config, Constants.TG20, print_writers, foreignLanguageOmo);
						foreignLanguageData = CricketFunctions.AssembleMultiLanguageData(CricketUtil.PLAYER, CricketUtil.SURNAME, multilanguagedata, player.getFull_name(), 
								"", null, 0, foreignLanguageDataList);
						CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$Lt_Analytics$HeaderGrp$Text$Side" + WhichSide + 
								"$English$Name$txt_LastName*GEOM*TEXT SET ", config, Constants.TG20, print_writers, foreignLanguageData);
						break;
					default:
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$Lt_Analytics$HeaderGrp$Text$Side" + WhichSide + 
								"$img_Text1$select_DataType*FUNCTION*Omo*vis_con SET 0\0",print_writers);
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$Lt_Analytics$HeaderGrp$Text$Side" + WhichSide + 
								"$img_Text1$Name$txt_FirstName*GEOM*TEXT SET " + player.getFirstname() + "\0", print_writers);
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$Lt_Analytics$HeaderGrp$Text$Side" + WhichSide + 
								"$img_Text1$Name$txt_LastName*GEOM*TEXT SET " + (player.getSurname() != null ? player.getSurname() : "") + "\0", print_writers);
						break;
					}
					
				
					if(WhichProfile.equalsIgnoreCase("THIS_SERIES")) {
						String[] TitleData = infobar.getSectionLtAnalytics().equalsIgnoreCase("BAT_PP")
							    ? new String[] {"MATCHES", "RUNS", "50s / 100s", "STRIKE RATE", "BEST"}
							    : new String[] {"MATCHES", "WICKETS", "3WI / 5WI", "ECONOMY", "BEST"};

						String[] StatData = infobar.getSectionLtAnalytics().equalsIgnoreCase("BAT_PP")
						    ? new String[] {String.valueOf(tournament.getMatches()),String.valueOf(tournament.getRuns()),
						    tournament.getFifty() + " / " + tournament.getHundreds(),CricketFunctions.generateStrikeRate(tournament.getRuns(), 
						    	tournament.getBallsFaced(), 0),best}: new String[] {String.valueOf(tournament.getMatches()),
						        String.valueOf(tournament.getWickets()),tournament.getThreeWicketHaul() + " / " + tournament.getFiveWicketHaul(),
						        CricketFunctions.getEconomy(tournament.getRunsConceded(), tournament.getBallsBowled(), 2, slashOrDash), // Example
						        best};

							// Now loop properly through both arrays
							for (int i = 0; i < TitleData.length; i++) {
								switch(config.getBroadcaster()) {
								case Constants.TG20:
									CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$Lt_Analytics$SublineGrp$Rows$Side" + WhichSide + 
											"$Title$" + (i+1) + "$select_Language*FUNCTION*Omo*vis_con SET ",config, Constants.TG20, print_writers, foreignLanguageOmo);
									foreignLanguageData = CricketFunctions.AssembleMultiLanguageData(CricketUtil.DICTIONARY, "", multilanguagedata, TitleData[i],
												"", null, 0, foreignLanguageDataList);
									CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$Lt_Analytics$SublineGrp$Rows$Side" + WhichSide + 
											"$Title$" + (i+1) + "$English$txt_1*GEOM*TEXT SET ", config, Constants.TG20, print_writers, foreignLanguageData);
									break;
								default:
									CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$Lt_Analytics$SublineGrp$Rows$Side"
									        + WhichSide + "$1$Title$txt_" + (i + 1) + "*GEOM*TEXT SET " + TitleData[i] + "\0",print_writers);
									break;
								}
															    
							    CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$Lt_Analytics$SublineGrp$Rows$Side"
							        + WhichSide + "$2$Stat$txt_" + (i + 1) + "*GEOM*TEXT SET " + StatData[i] + "\0",print_writers);
							}
					}else {
						String[] TitleData = infobar.getSectionLtAnalytics().equalsIgnoreCase("BAT_PP")
							    ? new String[] {"MATCHES", "RUNS", "50s / 100s", "STRIKE RATE", "BEST"}
							    : new String[] {"MATCHES", "WICKETS", "3WI / 5WI", "ECONOMY", "BEST"};

						String[] StatData = infobar.getSectionLtAnalytics().equalsIgnoreCase("BAT_PP")
						    ? new String[] {String.valueOf(stat.getMatches()),String.valueOf(stat.getRuns()),
						        stat.getFifties() + " / " + stat.getHundreds(),CricketFunctions.generateStrikeRate(stat.getRuns(), stat.getBallsFaced(), 0),
						        (stat.getBestScore().equalsIgnoreCase("0") ? "-" : stat.getBestScore())}: new String[] {String.valueOf(stat.getMatches()),
						        String.valueOf(stat.getWickets()),stat.getPlus3() + " / " + stat.getPlus5(),
						        CricketFunctions.getEconomy(stat.getRunsConceded(), stat.getBallsBowled(), 2, slashOrDash), // Example
						        (stat.getBestFigures().contains("-") ? stat.getBestFigures() : "-")};

						// Now loop properly through both arrays
						for (int i = 0; i < TitleData.length; i++) {
							switch(config.getBroadcaster()) {
							case Constants.TG20:
								CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$Lt_Analytics$SublineGrp$Rows$Side" + WhichSide + 
										"$Title$" + (i+1) + "$select_Language*FUNCTION*Omo*vis_con SET ",config, Constants.TG20, print_writers, foreignLanguageOmo);
								foreignLanguageData = CricketFunctions.AssembleMultiLanguageData(CricketUtil.DICTIONARY, "", multilanguagedata, TitleData[i],
											"", null, 0, foreignLanguageDataList);
								CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$Lt_Analytics$SublineGrp$Rows$Side" + WhichSide + 
										"$Title$" + (i+1) + "$English$txt_1*GEOM*TEXT SET ", config, Constants.TG20, print_writers, foreignLanguageData);
								break;
							default:
								CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$Lt_Analytics$SublineGrp$Rows$Side"
								        + WhichSide + "$1$Title$txt_" + (i + 1) + "*GEOM*TEXT SET " + TitleData[i] + "\0",print_writers);
								break;
							}
						    CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$Lt_Analytics$SublineGrp$Rows$Side"
						        + WhichSide + "$2$Stat$txt_" + (i + 1) + "*GEOM*TEXT SET " + StatData[i] + "\0",print_writers);
						}
					}
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$Lt_Analytics$SublineGrp$Rows$Side" 
							+ WhichSide + "$1$Title$1*TRANSFORMATION*POSITION*X SET 0.0\0",print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$Lt_Analytics$SublineGrp$Rows$Side" 
							+ WhichSide + "$2$Stat$txt_1*TRANSFORMATION*POSITION*X SET -160.0\0",print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$Lt_Analytics$SublineGrp$Rows$Side" 
							+ WhichSide + "$1$Title$2*TRANSFORMATION*POSITION*X SET 70.0\0",print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$Lt_Analytics$SublineGrp$Rows$Side" 
							+ WhichSide + "$2$Stat$txt_2*TRANSFORMATION*POSITION*X SET -90.0\0",print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$Lt_Analytics$SublineGrp$Rows$Side" 
							+ WhichSide + "$1$Title$3*TRANSFORMATION*POSITION*X SET 150.0\0",print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$Lt_Analytics$SublineGrp$Rows$Side" 
							+ WhichSide + "$2$Stat$txt_3*TRANSFORMATION*POSITION*X SET -10.0\0",print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$Lt_Analytics$SublineGrp$Rows$Side" 
							+ WhichSide + "$1$Title$4*TRANSFORMATION*POSITION*X SET 250.0\0",print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$Lt_Analytics$SublineGrp$Rows$Side" 
							+ WhichSide + "$2$Stat$txt_4*TRANSFORMATION*POSITION*X SET 90.0\0",print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$Lt_Analytics$SublineGrp$Rows$Side" 
							+ WhichSide + "$1$Title$5*TRANSFORMATION*POSITION*X SET 340.0\0",print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$Lt_Analytics$SublineGrp$Rows$Side" 
							+ WhichSide + "$2$Stat$txt_5*TRANSFORMATION*POSITION*X SET 178.0\0",print_writers);
					break;
				}
			}
			break;
		}
		return Constants.OK;
	}
	public String populateLofDimension(List<PrintWriter> print_writers, MatchAllData matchAllData) throws InterruptedException, IOException {
		
		ground = Grounds.stream().filter(grnd -> grnd.getFullname().contains(matchAllData.getSetup().getVenueName())).findAny().orElse(null);
		if(ground == null) {
			return "populateVizInfobarLeftBottom: Ground Name [" + matchAllData.getSetup().getVenueName() + "] from database is returning NULL";
		}
		
		CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$Main$All$Geom_GroundAll$RotationGrp$dime_final$Grp$5" + 
				"*ACTIVE SET 0\0", print_writers);
		CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$Main$All$Geom_GroundAll$RotationGrp$dime_final$Data$5" + 
				"*ACTIVE SET 0\0", print_writers);
		
		CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$Main$All$Geom_GroundAll$RotationGrp$dime_final$1$Text$txt_Data" + 
				"*GEOM*TEXT SET " + ground.getDimension_six_o_clock() +containerName+ "\0", print_writers);
		CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$Main$All$Geom_GroundAll$RotationGrp$dime_final$2$Text$txt_Data" + 
				"*GEOM*TEXT SET " + ground.getDimension_five_o_clock() +containerName+ "\0", print_writers);
		CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$Main$All$Geom_GroundAll$RotationGrp$dime_final$3$Text$txt_Data" + 
				"*GEOM*TEXT SET " + ground.getDimension_three_o_clock() +containerName+ "\0", print_writers);
		CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$Main$All$Geom_GroundAll$RotationGrp$dime_final$4$Text$txt_Data" + 
				"*GEOM*TEXT SET " + ground.getDimension_two_o_clock() +containerName+ "\0", print_writers);
		CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$Main$All$Geom_GroundAll$RotationGrp$dime_final$5$Text$txt_Data" + 
				"*GEOM*TEXT SET " + ground.getDimension_twelve_o_clock() +containerName+ "\0", print_writers);
		CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$Main$All$Geom_GroundAll$RotationGrp$dime_final$6$Text$txt_Data" + 
				"*GEOM*TEXT SET " + ground.getDimension_ten_o_clock() +containerName+ "\0", print_writers);
		CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$Main$All$Geom_GroundAll$RotationGrp$dime_final$7$Text$txt_Data" + 
				"*GEOM*TEXT SET " + ground.getDimension_nine_o_clock() +containerName+ "\0", print_writers);
		CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$Main$All$Geom_GroundAll$RotationGrp$dime_final$8$Text$txt_Data" + 
				"*GEOM*TEXT SET " + ground.getDimension_seven_o_clock() +containerName+ "\0", print_writers);
		
		TimeUnit.MILLISECONDS.sleep(100);
		return Constants.OK;
	}
	@SuppressWarnings("unchecked")
	public <T> List<T> GetPreviewData(String whatToProcess,Configuration session_configuration, MatchAllData matchAllData) throws JsonMappingException, 
		JsonProcessingException, InterruptedException {
		
		int k =0;
		String best = "-";
		List<Tournament> ts = new ArrayList<Tournament>();
		Tournament tourment = new Tournament();
		
		List<String> statsData = new ArrayList<String>();
		
		switch ((whatToProcess.contains(",") ? whatToProcess.split(",")[0] : whatToProcess)) {
		case "Alt_3": case "Alt_4":
			
			player = CricketFunctions.getPlayerFromMatchData(Integer.valueOf(whatToProcess.split(",")[2]), matchAllData);
			if(player == null) {
				statsData.add("InfoBarPlayerProfile: Player Id not found [" + whatToProcess.split(",")[2] + "]");
				return (List<T>) statsData;
			}
			
			if(whatToProcess.split(",")[3].equalsIgnoreCase("DT20")) {
				statsType = statsTypes.stream().filter(st -> st.getStatsShortName().equalsIgnoreCase(whatToProcess.split(",")[3])).findAny().orElse(null);
				if(statsType == null) {
					statsData.add("InfoBarPlayerProfile: Stats Type not found for profile [" + whatToProcess.split(",")[3] + "]");
					return (List<T>) statsData;
				}
				stat = statistics.stream().filter(st -> st.getPlayerID() == player.getPlayerId() && statsType.getStatsId() == st.getStatsTypeId()).findAny().orElse(null);
				if(stat == null) {
					statsData.add("InfoBarPlayerProfile: Player Id not found [" + whatToProcess.split(",")[2] + "]");
					return (List<T>) statsData;
				}
				
//				statsType = statsTypes.stream().filter(st -> st.getStatsShortName().equalsIgnoreCase("IT20")).findAny().orElse(null);
//				stat.setStats_type(statsType);
//				
//				stat = CricketFunctions.updateTournamentWithH2h(stat, headToHead, matchAllData, CricketUtil.FULL);
//				stat = CricketFunctions.updateStatisticsWithMatchData(stat, matchAllData, CricketUtil.FULL);
			}else if(whatToProcess.split(",")[3].equalsIgnoreCase("IT20")) {
				statsType = statsTypes.stream().filter(st -> st.getStatsShortName().equalsIgnoreCase(whatToProcess.split(",")[3])).findAny().orElse(null);
				if(statsType == null) {
					statsData.add("InfoBarPlayerProfile: Stats Type not found for profile [" + whatToProcess.split(",")[3] + "]");
					return (List<T>) statsData;
				}
				stat = statistics.stream().filter(st -> st.getPlayerID() == player.getPlayerId() && statsType.getStatsId() == st.getStatsTypeId()).findAny().orElse(null);
				if(stat == null) {
					statsData.add("InfoBarPlayerProfile: Player Id not found [" + whatToProcess.split(",")[2] + "]");
					return (List<T>) statsData;
				}
//				statsType = statsTypes.stream().filter(st -> st.getStatsShortName().equalsIgnoreCase(whatToProcess.split(",")[3])).findAny().orElse(null);
//				stat.setStats_type(statsType);
//				
//				stat = CricketFunctions.updateTournamentWithH2h(stat, headToHead, matchAllData, CricketUtil.FULL);
//				stat = CricketFunctions.updateStatisticsWithMatchData(stat, matchAllData, CricketUtil.FULL);
			}else if(whatToProcess.split(",")[3].equalsIgnoreCase("THIS_SERIES")) {
				ts = CricketFunctions.extractTournamentData("CURRENT_MATCH_DATA", false, headToHead, cricketService, matchAllData, 
						past_tournament_stats);
				tourment = ts.stream().filter(st -> st.getPlayerId() == player.getPlayerId()).findAny().orElse(null);
				
				for(Tournament tourn : ts) {
					for(BestStats bs : tourn.getBatsman_best_Stats()) {
						top_batsman_beststats.add(bs);
					}
					for(BestStats bfig : tourn.getBowler_best_Stats()) {
						top_bowler_beststats.add(bfig);
					}
				}
				
				Collections.sort(top_batsman_beststats, new CricketFunctions.PlayerBestStatsComparator());
				Collections.sort(top_bowler_beststats, new CricketFunctions.PlayerBestStatsComparator());
				
				switch((whatToProcess.contains(",") ? whatToProcess.split(",")[0] : whatToProcess)) {
				case "Alt_3":
					for(int j=0;j<= top_batsman_beststats.size()-1;j++) {
						if(top_batsman_beststats.get(j).getPlayerId() == player.getPlayerId()) {
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
					break;
				case "Alt_4":
					for(int j=0;j<= top_bowler_beststats.size()-1;j++) {
						if(top_bowler_beststats.get(j).getPlayerId() == player.getPlayerId()) {
							if(k == 1) {
								break;
							}
							if(k == 0) {
								k += 1;
								if(top_bowler_beststats.get(j).getBestEquation() % 1000 > 0) {
									best = ((top_bowler_beststats.get(j).getBestEquation() / 1000) +1) + "-" + (1000 - (top_bowler_beststats.get(j).getBestEquation() % 1000));
									break;
								}
								else if(top_bowler_beststats.get(j).getBestEquation() % 1000 < 0) {
									best = (top_bowler_beststats.get(j).getBestEquation() / 1000) + "-" + Math.abs(top_bowler_beststats.get(j).getBestEquation());
									break;
								}
								else if(top_bowler_beststats.get(j).getBestEquation() != 0) {
									if(top_bowler_beststats.get(j).getBestEquation() % 1000 == 0) {
										best = (top_bowler_beststats.get(j).getBestEquation() / 1000) + "-" + "0";
										break;
									}
								}
								break;
							}
						}else if(top_bowler_beststats.get(j).getPlayerId() != player.getPlayerId()) {
							best = "-";
						}
					}
					break;
				}
				
			}else {
				statsType = statsTypes.stream().filter(st -> st.getStatsShortName().equalsIgnoreCase(whatToProcess.split(",")[3])).findAny().orElse(null);
				if(statsType == null) {
					statsData.add("InfoBarPlayerProfile: Stats Type not found for profile [" + whatToProcess.split(",")[3] + "]");
					return (List<T>) statsData;
				}
				stat = statistics.stream().filter(st -> st.getPlayerID() == player.getPlayerId() && statsType.getStatsId() == st.getStatsTypeId()).findAny().orElse(null);
				if(stat == null) {
					statsData.add("InfoBarPlayerProfile: Player Id not found [" + whatToProcess.split(",")[2] + "]");
					return (List<T>) statsData;
				}
			}
			
			statsData.add(player.getFull_name());
			if(whatToProcess.split(",")[3].equalsIgnoreCase("THIS_SERIES")) {
				statsData.add("MATCHES," + tourment.getMatches());
				switch ((whatToProcess.contains(",") ? whatToProcess.split(",")[0] : whatToProcess)) {
				case "Alt_3":
					statsData.add("RUNS," + tourment.getRuns());
					statsData.add("50s / 100s,"+tourment.getFifty() + " / " + tourment.getHundreds());
					statsData.add("STRIKE RATE," + CricketFunctions.generateStrikeRate(tourment.getRuns(), tourment.getBallsFaced(), 0));
					statsData.add("BEST," + best);
					break;
				case "Alt_4":
					statsData.add("WICKETS," + tourment.getWickets());
					statsData.add("ECONOMY," + CricketFunctions.getEconomy(tourment.getRunsConceded(), tourment.getBallsBowled(), 2, slashOrDash));
					statsData.add("3WI / 5WI," + tourment.getThreeWicketHaul() + " / " + tourment.getFiveWicketHaul());
					statsData.add("BEST," + best);
					break;
				}
			}else {
				statsData.add("MATCHES," + stat.getMatches());
				switch ((whatToProcess.contains(",") ? whatToProcess.split(",")[0] : whatToProcess)) {
				case "Alt_3":
					statsData.add("RUNS," + stat.getRuns());
					statsData.add("50s / 100s,"+stat.getFifties() + " / " + stat.getHundreds());
					statsData.add("STRIKE RATE," + CricketFunctions.generateStrikeRate(stat.getRuns(), stat.getBallsFaced(), 0));
					statsData.add("BEST," + (stat.getBestScore().equalsIgnoreCase("0") ? "-" : stat.getBestScore()));
					break;
				case "Alt_4":
					statsData.add("WICKETS," + stat.getWickets());
					statsData.add("ECONOMY," + CricketFunctions.getEconomy(stat.getRunsConceded(), stat.getBallsBowled(), 2, slashOrDash));
					statsData.add("3WI / 5WI," + stat.getPlus3() + " / " + stat.getPlus5());
					statsData.add("BEST," + (stat.getBestFigures().contains("-") ? stat.getBestFigures() : "-"));
					break;
				}
			}
			
			
			
			return (List<T>) statsData;
		}
		return null;
		
	}
	
	public String toOrdinalShort(String input) {

	    if (input == null || input.trim().isEmpty())
	        return input;

	    input = input.trim().toLowerCase();

	    String[] WORDS = {
	        "", "first", "second", "third", "fourth", "fifth", "sixth", "seventh",
	        "eighth", "ninth", "tenth", "eleventh", "twelfth", "thirteenth",
	        "fourteenth", "fifteenth", "sixteenth", "seventeenth", "eighteenth",
	        "nineteenth", "twentieth"
	    };

	    String[] TENS = {
	        "", "", "twenty", "thirty", "forty", "fifty",
	        "sixty", "seventy", "eighty", "ninety"
	    };

	    // 1 to 20
	    for (int i = 1; i < WORDS.length; i++) {
	        if (input.equals(WORDS[i])) {
	            return toOrdinalNumber(i);
	        }
	    }

	    // 21 to 99 like "twenty first"
	    String[] parts = input.split("\\s+");
	    if (parts.length == 2) {
	        int tens = -1;
	        int ones = -1;

	        for (int i = 2; i < TENS.length; i++) {
	            if (parts[0].equals(TENS[i])) {
	                tens = i * 10;
	                break;
	            }
	        }

	        for (int i = 1; i < WORDS.length; i++) {
	            if (parts[1].equals(WORDS[i])) {
	                ones = i;
	                break;
	            }
	        }

	        if (tens != -1 && ones != -1) {
	            return toOrdinalNumber(tens + ones);
	        }
	    }

	    return input; // fallback
	}

	private String toOrdinalNumber(int number) {
	    if (number % 100 >= 11 && number % 100 <= 13)
	        return number + "th";

	    switch (number % 10) {
	        case 1: return number + "st";
	        case 2: return number + "nd";
	        case 3: return number + "rd";
	        default: return number + "th";
	    }
	}
	
	public String replaceOrdinalInText(String input) {

	    if (input == null || input.trim().isEmpty())
	        return input;

	    String[] words = input.split("\\s+");

	    if (words.length == 0)
	        return input;

	    // Try first 2 words: "Twenty First"
	    if (words.length >= 2) {
	        String firstTwo = words[0] + " " + words[1];
	        String converted = toOrdinalShort(firstTwo);
	        if (!converted.equalsIgnoreCase(firstTwo)) {
	            return converted + " " + joinFrom(words, 2);
	        }
	    }

	    // Try first word: "First"
	    String first = words[0];
	    String converted = toOrdinalShort(first);
	    if (!converted.equalsIgnoreCase(first)) {
	        return converted + " " + joinFrom(words, 1);
	    }

	    return input; // no change
	}

	private String joinFrom(String[] arr, int start) {
	    StringBuilder sb = new StringBuilder();
	    for (int i = start; i < arr.length; i++) {
	        if (i > start) sb.append(" ");
	        sb.append(arr[i]);
	    }
	    return sb.toString();
	}
}