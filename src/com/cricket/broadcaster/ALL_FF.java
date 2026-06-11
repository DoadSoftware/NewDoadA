package com.cricket.broadcaster;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.time.Month;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import com.cricket.captions.Constants;
import com.cricket.containers.FullFrames;
import com.cricket.controller.IndexController;
import com.cricket.model.BattingCard;
import com.cricket.model.BestStats;
import com.cricket.model.BowlingCard;
import com.cricket.model.Configuration;
import com.cricket.model.FallOfWicket;
import com.cricket.model.Fixture;
import com.cricket.model.ForeignLanguageData;
import com.cricket.model.HeadToHeadPlayer;
import com.cricket.model.Inning;
import com.cricket.model.LeagueTable;
import com.cricket.model.MatchAllData;
import com.cricket.model.OverByOverData;
import com.cricket.model.Partnership;
import com.cricket.model.Player;
import com.cricket.model.Statistics;
import com.cricket.model.StatsType;
import com.cricket.model.Team;
import com.cricket.model.Tournament;
import com.cricket.model.VariousText;
import com.cricket.service.CricketService;
import com.cricket.util.CricketFunctions;
import com.cricket.util.CricketUtil;

public class ALL_FF 
{
	public int omo=0,rowId=0,Top_Score=50,whichInning,FirstPlayerId,numberOfRows = 0,rowId1 =0,plyr_count=0,whichTeam;
	public double Mult=0,ScaleFac1=0,ScaleFac2=0;
	public String containerName,how_out_txt="",Left_Batsman="",Right_Batsman="",WhichType,WhichProfile,
			ImageType="",WhichStyle,containerName_2,varText = "",whichtype = "",containerName_1;
	
	public Player player;
	public Team team;
	public Statistics stat;
	public StatsType statsType;
	public Fixture fixture;
	public Partnership partnership;
	public Tournament tournament;
	public List<Player> Players,otherSquad,PlayingXI;
	public List<VariousText> VariousText;
	public List<HeadToHeadPlayer> headToHead;
	public List<Integer> PlayerId, PlayerIdIn;
	public FullFrames fullframes = new FullFrames();
	public List<Statistics> statisticsList = new ArrayList<Statistics>();
	
	public List<OverByOverData> manhattan, manhattan2 = new ArrayList<OverByOverData>();
	public MatchAllData previous_match = new MatchAllData();
	
	public List<ForeignLanguageData> foreignLanguageData,foreignLanguageOmo;
	public ForeignLanguageData howOut;
	public List<Team> Teams;
	
	public List<Tournament> tournament_stats = new ArrayList<Tournament>();
	public List<Tournament> addPastDataToCurr = new ArrayList<Tournament>();
	public List<Tournament> past_tournament_stats;
	public List<Tournament> tournaments;
	public List<BestStats> top_batsman_beststats,top_bowler_beststats = new ArrayList<BestStats>();
	public List<BestStats> top_batsman_beststat= new ArrayList<BestStats>();
	
	public LeagueTable leagueTable;
	public Player homeTeamCaptain,awayTeamCaptain;
	
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
	
	public void logosAndBaseColor(List<PrintWriter> print_writers, int WhichSide, String whatToProcess, MatchAllData matchAllData, Inning inning, Configuration config) throws InterruptedException {
		
		switch (config.getBroadcaster()) {
		case Constants.AFG_SL_SERIES:
			switch(whatToProcess) {
			case "F1": case "Control_Alt_F1": case "Alt_Shift_F1": case "F4": case "Shift_K": case "Shift_F10": case "Control_F10":
			case "Control_F1":
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$LogoGrp$Side" + WhichSide + "$Logo$LogoAll$img_Logos"
						+ "*TEXTURE*IMAGE SET " + Constants.AFG_SL_SERIES_LOGO + inning.getBatting_team().getTeamBadge() + "\0", print_writers);
				break;
			case "F2":
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$LogoGrp$Side" + WhichSide + "$Logo$LogoAll$img_Logos"
						+ "*TEXTURE*IMAGE SET " + Constants.AFG_SL_SERIES_LOGO + inning.getBowling_team().getTeamBadge() + "\0", print_writers);
				break;
			case "Shift_T":
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$LogoGrp$Side" + WhichSide + "$Logo$LogoAll$img_Logos"
						+ "*TEXTURE*IMAGE SET " + Constants.AFG_SL_SERIES_LOGO + team.getTeamBadge() + "\0", print_writers);
				break;
			case "Control_d": case "Control_e": case "Shift_P": case "Shift_Q":
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_Profile$Main$LogoGrp$Side" + WhichSide + "$img_Logos*TEXTURE*IMAGE SET " 
						+ Constants.AFG_SL_SERIES_LOGO + team.getTeamBadge() + "\0", print_writers);
				break;
			}
			break;
		case Constants.ACC:
			switch(whatToProcess) {
			case "Control_d": case "Control_e": case "Shift_P": case "Shift_Q":
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$Profile$TeamBageGrp$Select_BadgeType"
						+ "$img_Badges*TEXTURE*IMAGE SET " + Constants.ACC_FLAG + team.getTeamBadge() + "\0", print_writers);
				break;
				
			case "Control_F10":
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$Manhattan$TeamBageGrp$Select_Badge"
						+ "*FUNCTION*Omo*vis_con SET 0\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$Manhattan$TeamBageGrp$Select_BadgeType"
						+ "$img_Badges*TEXTURE*IMAGE SET " + Constants.ACC_FLAG + inning.getBatting_team().getTeamBadge() + "\0", print_writers);
				break;
			case "Shift_D":
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$Target$Select_Badge"
						+ "*FUNCTION*Omo*vis_con SET 0\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$Target$TeamBageGrp1$Select_BadgeType"
						+ "$img_Badges*TEXTURE*IMAGE SET " + Constants.ACC_FLAG + matchAllData.getSetup().getHomeTeam().getTeamBadge() + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$Target$TeamBageGrp2$Select_BadgeType"
						+ "$img_Badges*TEXTURE*IMAGE SET " + Constants.ACC_FLAG + matchAllData.getSetup().getAwayTeam().getTeamBadge() + "\0", print_writers);
				break;
			case "Shift_T":
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$Playing_XI_Image$TeamBageGrp$Select_Badge"
						+ "*FUNCTION*Omo*vis_con SET 0\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$Playing_XI_Image$TeamBageGrp$Select_BadgeType"
						+ "$img_Badges*TEXTURE*IMAGE SET " + Constants.ACC_FLAG + team.getTeamBadge() + "\0", print_writers);
				break;
			case "Shift_K": 
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$CurrentPArt$CurrentPartnership$TeamBageGrp$"
						+ "Select_Badge*FUNCTION*Omo*vis_con SET 0\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$CurrentPArt$CurrentPartnership$TeamBageGrp$"
						+ "Select_BadgeType$img_Badges*TEXTURE*IMAGE SET " + Constants.ACC_FLAG + inning.getBatting_team().getTeamBadge() + "\0", print_writers);
				break;
			case "Control_F7":
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$Lineup_Both$TeamBageGrp$HomeTeam$Select_BadgeType"
						+ "$img_Badges*TEXTURE*IMAGE SET " + Constants.ACC_FLAG + matchAllData.getSetup().getHomeTeam().getTeamBadge() + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$Lineup_Both$TeamBageGrp$AwayTEam$Select_BadgeType"
						+ "$img_Badges*TEXTURE*IMAGE SET " + Constants.ACC_FLAG + matchAllData.getSetup().getAwayTeam().getTeamBadge() + "\0", print_writers);
				break;
			case "F4": 
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$PartnershipCard$TeamBageGrp$Select_Badge"
						+ "*FUNCTION*Omo*vis_con SET 0\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$PartnershipCard$TeamBageGrp$Select_BadgeType"
						+ "$img_Badges*TEXTURE*IMAGE SET " + Constants.ACC_FLAG + inning.getBatting_team().getTeamBadge() + "\0", print_writers);
				break;
			case "F1": 
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$BattingCard$TeamBageGrp$Select_Badge"
						+ "*FUNCTION*Omo*vis_con SET 0\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$BattingCard$TeamBageGrp$Select_BadgeType"
						+ "$img_Badges*TEXTURE*IMAGE SET " + Constants.ACC_FLAG + inning.getBatting_team().getTeamBadge() + "\0", print_writers);
				break;
			case "F2":
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$BowlingCard$TeamBageGrp$Select_Badge"
						+ "*FUNCTION*Omo*vis_con SET 0\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$BowlingCard$TeamBageGrp$Select_BadgeType"
						+ "$img_Badges*TEXTURE*IMAGE SET " + Constants.ACC_FLAG + inning.getBowling_team().getTeamBadge() + "\0", print_writers);
				break;
			}
			break;
		case Constants.BAN_AFG_SERIES:
			switch(whatToProcess) {
			case "Control_d": case "Control_e": case "Shift_P": case "Shift_Q":
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$Profile$TeamBageGrp$Select_BadgeType"
						+ "$img_Badges*TEXTURE*IMAGE SET " + Constants.BAN_AFG_SERIES_LOGO + team.getTeamBadge() + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$Profile$TeamBageGrp"
						+ "$img_Badges02*TEXTURE*IMAGE SET " + Constants.BAN_AFG_SERIES_LOGO + team.getTeamBadge() + "\0", print_writers);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$Profile$TeamBageGrp"
						+ "$img_Base2*TEXTURE*IMAGE SET " + Constants.BAN_AFG_SERIES_BASE2 + team.getTeamBadge() + "\0", print_writers);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$Profile$BatHeaderGrp"
						+ "$img_Base2*TEXTURE*IMAGE SET " + Constants.BAN_AFG_SERIES_BASE2 + team.getTeamBadge() + "\0", print_writers);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$Profile$ProfileDataAll$Right$LogoBase"
						+ "$img_Base2*TEXTURE*IMAGE SET " + Constants.BAN_AFG_SERIES_BASE2 + team.getTeamBadge() + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$Profile$ProfileDataAll$Right$DataGrp"
						+ "$img_Text*TEXTURE*IMAGE SET " + Constants.BAN_AFG_SERIES_TEXT + team.getTeamBadge() + "\0", print_writers);
				
				for(int i=1;i<=5;i++) {
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$Profile$ProfileDataAll$Left$BatRow" + i
							+ "$ScoreGrp$img_Base2*TEXTURE*IMAGE SET " + Constants.BAN_AFG_SERIES_BASE2 + team.getTeamBadge() + "\0", print_writers);
				}
				break;
			case "Shift_F10":
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$RunWorm$TeamBageGrp"
						+ "$img_Badges*TEXTURE*IMAGE SET " + Constants.BAN_AFG_SERIES_LOGO + "EVENT" + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$RunWorm$TeamBageGrp"
						+ "$img_Badges02*TEXTURE*IMAGE SET " + Constants.BAN_AFG_SERIES_LOGO + "EVENT" + "\0", print_writers);
				
				if(inning.getInningNumber() == 1) {
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$RunWorm$WormDataAll$Worm$Select_Team$1_Teams$Team_1"
							+ "$img_Base2*TEXTURE*IMAGE SET " + Constants.BAN_AFG_SERIES_BASE2 + inning.getBatting_team().getTeamBadge() + "\0", print_writers);
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$RunWorm$WormDataAll$Worm$Graph$Worms$Team_1"
							+ "$img_Text*TEXTURE*IMAGE SET " + Constants.BAN_AFG_SERIES_TEXT + inning.getBatting_team().getTeamBadge() + "\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$RunWorm$Worm$Worms$Team_1$AnimWorm"
							+ "$img_Base_2*TEXTURE*IMAGE SET " + Constants.BAN_AFG_SERIES_BASE2 + inning.getBatting_team().getTeamBadge() + "\0", print_writers);
					
				}else if(inning.getInningNumber() == 2) {
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$RunWorm$WormDataAll$Worm$Select_Team$2_Teams$Team_1"
							+ "$img_Base2*TEXTURE*IMAGE SET " + Constants.BAN_AFG_SERIES_BASE2 + inning.getBowling_team().getTeamBadge() + "\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$RunWorm$WormDataAll$Worm$Select_Team$2_Teams$Team_2"
							+ "$img_Base2*TEXTURE*IMAGE SET " + Constants.BAN_AFG_SERIES_BASE2 + inning.getBatting_team().getTeamBadge() + "\0", print_writers);
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$RunWorm$Worm$Worms$Team_1"
							+ "$img_Text*TEXTURE*IMAGE SET " + Constants.BAN_AFG_SERIES_TEXT + inning.getBowling_team().getTeamBadge() + "\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$RunWorm$Worm$Worms$Team_1$AnimWorm"
							+ "$img_Base_2*TEXTURE*IMAGE SET " + Constants.BAN_AFG_SERIES_BASE2 + inning.getBowling_team().getTeamBadge() + "\0", print_writers);
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$RunWorm$Worm$Worms$Team_2$2"
							+ "$img_Text*TEXTURE*IMAGE SET " + Constants.BAN_AFG_SERIES_TEXT + inning.getBatting_team().getTeamBadge() + "\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$RunWorm$Worm$Worms$Team_2$2"
							+ "$img_Base2*TEXTURE*IMAGE SET " + Constants.BAN_AFG_SERIES_BASE2 + inning.getBatting_team().getTeamBadge() + "\0", print_writers);
				}
				break;
				
			case "Control_F10":
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$Manhattan$TeamBageGrp$Select_BadgeType"
						+ "$img_Badges*TEXTURE*IMAGE SET " + Constants.BAN_AFG_SERIES_LOGO + inning.getBatting_team().getTeamBadge() + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$Manhattan$TeamBageGrp"
						+ "$img_Badges02*TEXTURE*IMAGE SET " + Constants.BAN_AFG_SERIES_LOGO + inning.getBatting_team().getTeamBadge() + "\0", print_writers);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$Manhattan$TeamBageGrp"
						+ "$img_Base2*TEXTURE*IMAGE SET " + Constants.BAN_AFG_SERIES_BASE2 + inning.getBatting_team().getTeamBadge() + "\0", print_writers);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$Manhattan$ManhattanHeaderGrp"
						+ "$img_Base2*TEXTURE*IMAGE SET " + Constants.BAN_AFG_SERIES_BASE2 + inning.getBatting_team().getTeamBadge() + "\0", print_writers);
				
				break;
			case "Shift_D":
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$Target$TeamBageGrp1$Select_BadgeType"
						+ "$img_Badges*TEXTURE*IMAGE SET " + Constants.BAN_AFG_SERIES_LOGO + matchAllData.getSetup().getHomeTeam().getTeamBadge() + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$Target$TeamBageGrp1"
						+ "$img_Badges02*TEXTURE*IMAGE SET " + Constants.BAN_AFG_SERIES_LOGO + matchAllData.getSetup().getHomeTeam().getTeamBadge() + "\0", print_writers);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$Target$TeamBageGrp1"
						+ "$img_Base2*TEXTURE*IMAGE SET " + Constants.BAN_AFG_SERIES_BASE2 + matchAllData.getSetup().getHomeTeam().getTeamBadge() + "\0", print_writers);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$Target$TeamBageGrp2$Select_BadgeType"
						+ "$img_Badges*TEXTURE*IMAGE SET " + Constants.BAN_AFG_SERIES_LOGO + matchAllData.getSetup().getAwayTeam().getTeamBadge() + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$Target$TeamBageGrp2"
						+ "$img_Badges02*TEXTURE*IMAGE SET " + Constants.BAN_AFG_SERIES_LOGO + matchAllData.getSetup().getAwayTeam().getTeamBadge() + "\0", print_writers);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$Target$TeamBageGrp2"
						+ "$img_Base2*TEXTURE*IMAGE SET " + Constants.BAN_AFG_SERIES_BASE2 + matchAllData.getSetup().getAwayTeam().getTeamBadge() + "\0", print_writers);
				
				break;
			case "Shift_T":
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$Playing_XI_Image$TeamBageGrp$Select_BadgeType"
						+ "$img_Badges*TEXTURE*IMAGE SET " + Constants.BAN_AFG_SERIES_LOGO + team.getTeamBadge() + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$Playing_XI_Image$TeamBageGrp"
						+ "$img_Badges02*TEXTURE*IMAGE SET " + Constants.BAN_AFG_SERIES_LOGO + team.getTeamBadge() + "\0", print_writers);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$Playing_XI_Image$TeamBageGrp"
						+ "$img_Base2*TEXTURE*IMAGE SET " + Constants.BAN_AFG_SERIES_BASE2 + team.getTeamBadge() + "\0", print_writers);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$Playing_XI_Image$BatTeamHeaderGrp"
						+ "$img_Base2*TEXTURE*IMAGE SET " + Constants.BAN_AFG_SERIES_BASE2 + team.getTeamBadge() + "\0", print_writers);
				
				for(int i=1;i<=11;i++) {
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$Playing_XI_Image$TeamDataAll$PlayerGrp" + i + 
							"$ImageGrp$LogoBase$img_Base2*TEXTURE*IMAGE SET " + Constants.BAN_AFG_SERIES_BASE2 + team.getTeamBadge() + "\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$Playing_XI_Image$TeamDataAll$PlayerGrp" + i + 
							"$ImageGrp$img_Player*TEXTURE*IMAGE SET " + Constants.BAN_AFG_SERIES_BASE2 + team.getTeamBadge() + "\0", print_writers);

					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$Playing_XI_Image$TeamDataAll$PlayerGrp" + i + 
							"$NameGrp$img_Text*TEXTURE*IMAGE SET " + Constants.BAN_AFG_SERIES_TEXT + team.getTeamBadge() + "\0", print_writers);
				}
				break;
			case "Shift_K":
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$CurrentPArt$TeamBageGrp$Select_BadgeType"
						+ "$img_Badges*TEXTURE*IMAGE SET " + Constants.BAN_AFG_SERIES_LOGO + inning.getBatting_team().getTeamBadge() + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$CurrentPArt$TeamBageGrp"
						+ "$img_Badges02*TEXTURE*IMAGE SET " + Constants.BAN_AFG_SERIES_LOGO + inning.getBatting_team().getTeamBadge() + "\0", print_writers);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$CurrentPArt$TeamBageGrp"
						+ "$img_Base2*TEXTURE*IMAGE SET " + Constants.BAN_AFG_SERIES_BASE2 + inning.getBatting_team().getTeamBadge() + "\0", print_writers);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$CurrentPArt$Header$Bottom_Header"
						+ "$img_Base2*TEXTURE*IMAGE SET " + Constants.BAN_AFG_SERIES_BASE2 + inning.getBatting_team().getTeamBadge() + "\0", print_writers);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$CurrentPArt$CurrentPartnershipDataAll$PartScoreOut"
						+ "$img_Text*TEXTURE*IMAGE SET " + Constants.BAN_AFG_SERIES_TEXT + inning.getBatting_team().getTeamBadge() + "\0", print_writers);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$CurrentPArt$CurrentPartnershipDataAll$ImageGrpOut"
						+ "$ImageGrp1$img_Base2*TEXTURE*IMAGE SET " + Constants.BAN_AFG_SERIES_BASE2 + inning.getBatting_team().getTeamBadge() + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$CurrentPArt$CurrentPartnershipDataAll$ImageGrpOut"
						+ "$ImageGrp2$img_Base2*TEXTURE*IMAGE SET " + Constants.BAN_AFG_SERIES_BASE2 + inning.getBatting_team().getTeamBadge() + "\0", print_writers);
				break;
			case "Control_F7":
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$Lineup_Both$$TeamBageGrp$HomeTeam$Select_BadgeType"
						+ "$img_Badges*TEXTURE*IMAGE SET " + Constants.BAN_AFG_SERIES_LOGO + matchAllData.getSetup().getHomeTeam().getTeamBadge() + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$Lineup_Both$$TeamBageGrp$HomeTeam"
						+ "$img_Badges02*TEXTURE*IMAGE SET " + Constants.BAN_AFG_SERIES_LOGO + matchAllData.getSetup().getHomeTeam().getTeamBadge() + "\0", print_writers);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$Lineup_Both$TeamBageGrp$HomeTeam$LogoBase"
						+ "$img_Base2*TEXTURE*IMAGE SET " + Constants.BAN_AFG_SERIES_BASE2 + matchAllData.getSetup().getHomeTeam().getTeamBadge() + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$Lineup_Both$BatHeaderGrp$Bottom_Header_Home"
						+ "$img_Base2*TEXTURE*IMAGE SET " + Constants.BAN_AFG_SERIES_BASE2 + matchAllData.getSetup().getHomeTeam().getTeamBadge() + "\0", print_writers);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$Lineup_Both$$TeamBageGrp$AwayTEam$Select_BadgeType"
						+ "$img_Badges*TEXTURE*IMAGE SET " + Constants.BAN_AFG_SERIES_LOGO + matchAllData.getSetup().getAwayTeam().getTeamBadge() + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$Lineup_Both$$TeamBageGrp$AwayTEam"
						+ "$img_Badges02*TEXTURE*IMAGE SET " + Constants.BAN_AFG_SERIES_LOGO + matchAllData.getSetup().getAwayTeam().getTeamBadge() + "\0", print_writers);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$Lineup_Both$TeamBageGrp$AwayTEam$LogoBase"
						+ "$img_Base2*TEXTURE*IMAGE SET " + Constants.BAN_AFG_SERIES_BASE2 + matchAllData.getSetup().getAwayTeam().getTeamBadge() + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$Lineup_Both$BatHeaderGrp$Bottom_Header_Away"
						+ "$img_Base2*TEXTURE*IMAGE SET " + Constants.BAN_AFG_SERIES_BASE2 + matchAllData.getSetup().getAwayTeam().getTeamBadge() + "\0", print_writers);
				
				for(int i=1;i<=11;i++) {
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$Lineup_Both$LineupDataALL$HomeTeam$BatRow" + i + "$img_Base2"
							+ "*TEXTURE*IMAGE SET " + Constants.BAN_AFG_SERIES_BASE2 + matchAllData.getSetup().getHomeTeam().getTeamBadge() + "\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$Lineup_Both$LineupDataALL$AwayTeam$BatRow" + i + "$img_Base2"
							+ "*TEXTURE*IMAGE SET " + Constants.BAN_AFG_SERIES_BASE2 + matchAllData.getSetup().getAwayTeam().getTeamBadge() + "\0", print_writers);
				}
				
				break;
			case "Control_F11": case "Shift_F11":
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$Summary$Inning1$Row0$img_Base2*TEXTURE*IMAGE SET " 
						+ Constants.BAN_AFG_SERIES_BASE2 + matchAllData.getMatch().getInning().get(0).getBatting_team().getTeamBadge() + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$Summary$Inning2$Row0$img_Base2*TEXTURE*IMAGE SET " 
						+ Constants.BAN_AFG_SERIES_BASE2 + matchAllData.getMatch().getInning().get(1).getBatting_team().getTeamBadge() + "\0", print_writers);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$Summary$Inning1$CaptainGrp$img_Base2*TEXTURE*IMAGE SET " 
						+ Constants.BAN_AFG_SERIES_BASE2 + matchAllData.getMatch().getInning().get(0).getBatting_team().getTeamBadge() + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$Summary$Inning2$CaptainGrp$img_Base2*TEXTURE*IMAGE SET " 
						+ Constants.BAN_AFG_SERIES_BASE2 + matchAllData.getMatch().getInning().get(1).getBatting_team().getTeamBadge() + "\0", print_writers);
				
				for(int i=1;i<=3;i++) {
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$Summary$Inning1$Row" + i + "$Left$img_Base2*TEXTURE*IMAGE SET " 
							+ Constants.BAN_AFG_SERIES_BASE2 + matchAllData.getMatch().getInning().get(0).getBatting_team().getTeamBadge() + "\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$Summary$Inning1$Row" + i + "$Right$img_Base2*TEXTURE*IMAGE SET " 
							+ Constants.BAN_AFG_SERIES_BASE2 + matchAllData.getMatch().getInning().get(0).getBowling_team().getTeamBadge() + "\0", print_writers);
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$Summary$Inning2$Row" + i + "$Left$img_Base2*TEXTURE*IMAGE SET " 
							+ Constants.BAN_AFG_SERIES_BASE2 + matchAllData.getMatch().getInning().get(1).getBatting_team().getTeamBadge() + "\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$Summary$Inning2$Row" + i + "$Right$img_Base2*TEXTURE*IMAGE SET " 
							+ Constants.BAN_AFG_SERIES_BASE2 + matchAllData.getMatch().getInning().get(1).getBowling_team().getTeamBadge() + "\0", print_writers);
				}
				break;
			case "F4":
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$PartnershipCard$TeamBageGrp$Select_BadgeType"
						+ "$img_Badges*TEXTURE*IMAGE SET " + Constants.BAN_AFG_SERIES_LOGO + inning.getBatting_team().getTeamBadge() + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$PartnershipCard$TeamBageGrp"
						+ "$img_Badges02*TEXTURE*IMAGE SET " + Constants.BAN_AFG_SERIES_LOGO + inning.getBatting_team().getTeamBadge() + "\0", print_writers);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$PartnershipCard$TeamBageGrp"
						+ "$img_Base2*TEXTURE*IMAGE SET " + Constants.BAN_AFG_SERIES_BASE2 + inning.getBatting_team().getTeamBadge() + "\0", print_writers);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$PartnershipCard$PartnershipHeaderGrp"
						+ "$img_Base2*TEXTURE*IMAGE SET " + Constants.BAN_AFG_SERIES_BASE2 + inning.getBatting_team().getTeamBadge() + "\0", print_writers);
				
				for(int i=1;i<=13;i++) {
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$PartnershipCard$PartnershipDataAll$PartRow" + i + 
							"$Title$img_Base2*TEXTURE*IMAGE SET " + Constants.BAN_AFG_SERIES_BASE2 + inning.getBatting_team().getTeamBadge() + "\0", print_writers);
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$PartnershipCard$PartnershipDataAll$PartRow" + i + 
							"$Out$Bars$img_Base2*TEXTURE*IMAGE SET " + Constants.BAN_AFG_SERIES_BASE2 + inning.getBatting_team().getTeamBadge() + "\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$PartnershipCard$PartnershipDataAll$PartRow" + i + 
							"$Out$ScoreGrp$img_Base2*TEXTURE*IMAGE SET " + Constants.BAN_AFG_SERIES_BASE2 + inning.getBatting_team().getTeamBadge() + "\0", print_writers);
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$PartnershipCard$PartnershipDataAll$PartRow" + i + 
							"$NotOut$img_Base2*TEXTURE*IMAGE SET " + Constants.BAN_AFG_SERIES_BASE2 + inning.getBatting_team().getTeamBadge() + "\0", print_writers);
				}
				
				break;
			case "F1":
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$BattingCard$TeamBageGrp$Select_BadgeType"
						+ "$img_Badges*TEXTURE*IMAGE SET " + Constants.BAN_AFG_SERIES_LOGO + inning.getBatting_team().getTeamBadge() + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$BattingCard$TeamBageGrp"
						+ "$img_Badges02*TEXTURE*IMAGE SET " + Constants.BAN_AFG_SERIES_LOGO + inning.getBatting_team().getTeamBadge() + "\0", print_writers);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$BattingCard$TeamBageGrp"
						+ "$img_Base2*TEXTURE*IMAGE SET " + Constants.BAN_AFG_SERIES_BASE2 + inning.getBatting_team().getTeamBadge() + "\0", print_writers);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$BattingCard$BatHeaderGrp"
						+ "$img_Base2*TEXTURE*IMAGE SET " + Constants.BAN_AFG_SERIES_BASE2 + inning.getBatting_team().getTeamBadge() + "\0", print_writers);
				
				for(int i=1;i<=13;i++) {
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$BattingCard$BattingDataAll$Normal$BatRow" + i + 
							"$Out$img_Base2*TEXTURE*IMAGE SET " + Constants.BAN_AFG_SERIES_BASE2 + inning.getBatting_team().getTeamBadge() + "\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$BattingCard$BattingDataAll$Normal$BatRow" + i + 
							"$Out$img_Text1*TEXTURE*IMAGE SET " + Constants.BAN_AFG_SERIES_TEXT1 + inning.getBatting_team().getTeamBadge() + "\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$BattingCard$BattingDataAll$Normal$BatRow" + i + 
							"$Out$img_Text2*TEXTURE*IMAGE SET " + Constants.BAN_AFG_SERIES_TEXT2 + inning.getBatting_team().getTeamBadge() + "\0", print_writers);
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$BattingCard$BattingDataAll$Normal$BatRow" + i + 
							"$NotOut$img_Base2*TEXTURE*IMAGE SET " + Constants.BAN_AFG_SERIES_BASE2 + inning.getBatting_team().getTeamBadge() + "\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$BattingCard$BattingDataAll$Normal$BatRow" + i + 
							"$NotOut$img_Text2*TEXTURE*IMAGE SET " + Constants.BAN_AFG_SERIES_TEXT2 + inning.getBatting_team().getTeamBadge() + "\0", print_writers);
					
					//BAT PERFORMER
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$BattingCard$BattingDataAll$Split$Left$BatRow" + i + 
							"$Out$img_Base2*TEXTURE*IMAGE SET " + Constants.BAN_AFG_SERIES_BASE2 + inning.getBatting_team().getTeamBadge() + "\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$BattingCard$BattingDataAll$Split$Left$BatRow" + i + 
							"$Out$img_Text1*TEXTURE*IMAGE SET " + Constants.BAN_AFG_SERIES_TEXT1 + inning.getBatting_team().getTeamBadge() + "\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$BattingCard$BattingDataAll$Split$Left$BatRow" + i + 
							"$Out$img_Text2*TEXTURE*IMAGE SET " + Constants.BAN_AFG_SERIES_TEXT2 + inning.getBatting_team().getTeamBadge() + "\0", print_writers);
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$BattingCard$BattingDataAll$Split$Left$BatRow" + i + 
							"$Highlight$img_Base3*TEXTURE*IMAGE SET " + Constants.BAN_AFG_SERIES_BASE3 + inning.getBatting_team().getTeamBadge() + "\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$BattingCard$BattingDataAll$Split$Left$BatRow" + i + 
							"$Highlight$img_Text3*TEXTURE*IMAGE SET " + Constants.BAN_AFG_SERIES_TEXT3 + inning.getBatting_team().getTeamBadge() + "\0", print_writers);
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$BattingCard$BattingDataAll$Split$Left$BatRow" + i + 
							"$NotOut$img_Base2*TEXTURE*IMAGE SET " + Constants.BAN_AFG_SERIES_BASE2 + inning.getBatting_team().getTeamBadge() + "\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$BattingCard$BattingDataAll$Split$Left$BatRow" + i + 
							"$NotOut$img_Text2*TEXTURE*IMAGE SET " + Constants.BAN_AFG_SERIES_TEXT2 + inning.getBatting_team().getTeamBadge() + "\0", print_writers);
				}
				
				//BAT PERFORMER
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$BattingCard$BattingDataAll$Split$Right$HighestScore" + 
						"$LogoBase$img_Base2*TEXTURE*IMAGE SET " + Constants.BAN_AFG_SERIES_BASE2 + inning.getBatting_team().getTeamBadge() + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$BattingCard$BattingDataAll$Split$Right$HighestScore" + 
						"$DataGrp$img_Text*TEXTURE*IMAGE SET " + Constants.BAN_AFG_SERIES_TEXT + inning.getBatting_team().getTeamBadge() + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$BattingCard$BattingDataAll$Split$Right$HighestScore" + 
						"$DataGrp$img_Text1*TEXTURE*IMAGE SET " + Constants.BAN_AFG_SERIES_TEXT1 + inning.getBatting_team().getTeamBadge() + "\0", print_writers);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$BattingCard$BattingDataAll$Split$Right$BatPartnership" + 
						"$LogoBase$img_Base2*TEXTURE*IMAGE SET " + Constants.BAN_AFG_SERIES_BASE2 + inning.getBatting_team().getTeamBadge() + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$BattingCard$BattingDataAll$Split$Right$BatPartnership" + 
						"$DataGrp$img_Text*TEXTURE*IMAGE SET " + Constants.BAN_AFG_SERIES_TEXT + inning.getBatting_team().getTeamBadge() + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$BattingCard$BattingDataAll$Split$Right$BatPartnership" + 
						"$DataGrp$img_Text1*TEXTURE*IMAGE SET " + Constants.BAN_AFG_SERIES_TEXT1 + inning.getBatting_team().getTeamBadge() + "\0", print_writers);
				break;
			case "F2":
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$BowlingCard$TeamBageGrp$Select_BadgeType"
						+ "$img_Badges*TEXTURE*IMAGE SET " + Constants.BAN_AFG_SERIES_LOGO + inning.getBowling_team().getTeamBadge() + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$BowlingCard$TeamBageGrp"
						+ "$img_Badges02*TEXTURE*IMAGE SET " + Constants.BAN_AFG_SERIES_LOGO + inning.getBowling_team().getTeamBadge() + "\0", print_writers);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$BowlingCard$TeamBageGrp"
						+ "$img_Base2*TEXTURE*IMAGE SET " + Constants.BAN_AFG_SERIES_BASE2 + inning.getBowling_team().getTeamBadge() + "\0", print_writers);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$BowlingCard$BallHeaderGrp"
						+ "$img_Base2*TEXTURE*IMAGE SET " + Constants.BAN_AFG_SERIES_BASE2 + inning.getBowling_team().getTeamBadge() + "\0", print_writers);
				
				for(int i=1;i<=11;i++) {
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$BowlingCard$BallDataAll$Normal$BallRow" + i + 
							"$Dehighlight$BowlerData$img_Base2*TEXTURE*IMAGE SET " + Constants.BAN_AFG_SERIES_BASE2 + inning.getBowling_team().getTeamBadge() + "\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$BowlingCard$BallDataAll$Normal$BallRow" + i + 
							"$Highlight$BowlerData$img_Base3*TEXTURE*IMAGE SET " + Constants.BAN_AFG_SERIES_BASE3 + inning.getBowling_team().getTeamBadge() + "\0", print_writers);
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$BowlingCard$BallDataAll$Normal$BallRow" + i + 
							"$Dehighlight$BowlerData$img_Text2*TEXTURE*IMAGE SET " + Constants.BAN_AFG_SERIES_TEXT2 + inning.getBowling_team().getTeamBadge() + "\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$BowlingCard$BallDataAll$Normal$BallRow" + i + 
							"$Highlight$BowlerData$img_Text3*TEXTURE*IMAGE SET " + Constants.BAN_AFG_SERIES_TEXT3 + inning.getBowling_team().getTeamBadge() + "\0", print_writers);
					
					//BALL PERFORMER
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$BowlingCard$BallDataAll$Split$Left$BallRow" + i + 
							"$FadeForHighlight$img_Base2*TEXTURE*IMAGE SET " + Constants.BAN_AFG_SERIES_BASE2 + inning.getBowling_team().getTeamBadge() + "\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$BowlingCard$BallDataAll$Split$Left$BallRow" + i + 
							"$FadeForHighlight$img_Text1*TEXTURE*IMAGE SET " + Constants.BAN_AFG_SERIES_TEXT1 + inning.getBowling_team().getTeamBadge() + "\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$BowlingCard$BallDataAll$Split$Left$BallRow" + i + 
							"$FadeForHighlight$img_Text2*TEXTURE*IMAGE SET " + Constants.BAN_AFG_SERIES_TEXT2 + inning.getBowling_team().getTeamBadge() + "\0", print_writers);
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$BowlingCard$BallDataAll$Split$Left$BallRow" + i + 
							"$Highlight$img_Base3*TEXTURE*IMAGE SET " + Constants.BAN_AFG_SERIES_BASE3 + inning.getBowling_team().getTeamBadge() + "\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$BowlingCard$BallDataAll$Split$Left$BallRow" + i + 
							"$Highlight$img_Text3*TEXTURE*IMAGE SET " + Constants.BAN_AFG_SERIES_TEXT3 + inning.getBowling_team().getTeamBadge() + "\0", print_writers);
				}
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$BowlingCard$BallDataAll$Normal$FOW_Grp" + 
						"$FOW_Data$img_Base2*TEXTURE*IMAGE SET " + Constants.BAN_AFG_SERIES_BASE2 + inning.getBowling_team().getTeamBadge() + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$BowlingCard$BallDataAll$Normal$Score" + 
						"$ScoreData$img_Base2*TEXTURE*IMAGE SET " + Constants.BAN_AFG_SERIES_BASE2 + inning.getBowling_team().getTeamBadge() + "\0", print_writers);
				
				//BALL PERFORMER
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$BowlingCard$BallDataAll$Split$Right$HighestScore" + 
						"$LogoBase$img_Base2*TEXTURE*IMAGE SET " + Constants.BAN_AFG_SERIES_BASE2 + inning.getBowling_team().getTeamBadge() + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$BowlingCard$BallDataAll$Split$Right$HighestScore" + 
						"$DataGrp$img_Text*TEXTURE*IMAGE SET " + Constants.BAN_AFG_SERIES_TEXT + inning.getBowling_team().getTeamBadge() + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$BowlingCard$BallDataAll$Split$Right$HighestScore" + 
						"$DataGrp$img_Text1*TEXTURE*IMAGE SET " + Constants.BAN_AFG_SERIES_TEXT1 + inning.getBowling_team().getTeamBadge() + "\0", print_writers);
				
				break;
			}
			break;
		case Constants.TRI_SERIES: case Constants.MT20:
			switch(whatToProcess) {
			case "m":
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_Ident$WipeFinal$In$Team_1$img_Base1*TEXTURE*IMAGE SET " + 
						(config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_BASE1 : Constants.MT20_BASE1) + matchAllData.getSetup().getHomeTeam().getTeamBadge() + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_Ident$WipeFinal$In$Team_1$img_Base2*TEXTURE*IMAGE SET " + 
						(config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_BASE2 : Constants.MT20_BASE2) + matchAllData.getSetup().getHomeTeam().getTeamBadge() + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_Ident$WipeFinal$In$Team_1$img_Logos_BW*TEXTURE*IMAGE SET " + 
						(config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_LOGO_BW : Constants.MT20_LOGO_BW) + matchAllData.getSetup().getHomeTeam().getTeamBadge() + "\0", print_writers);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_Ident$WipeFinal$In$Team_2$img_Base1*TEXTURE*IMAGE SET " + 
						(config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_BASE1 : Constants.MT20_BASE1) + matchAllData.getSetup().getAwayTeam().getTeamBadge() + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_Ident$WipeFinal$In$Team_2$img_Base2*TEXTURE*IMAGE SET " + 
						(config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_BASE2 : Constants.MT20_BASE2) + matchAllData.getSetup().getAwayTeam().getTeamBadge() + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_Ident$WipeFinal$In$Team_2$img_Logos_BW*TEXTURE*IMAGE SET " + 
						(config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_LOGO_BW : Constants.MT20_LOGO_BW) + matchAllData.getSetup().getAwayTeam().getTeamBadge() + "\0", print_writers);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_Ident$WipeFinal$Out$Team_1$img_Base1*TEXTURE*IMAGE SET " + 
						(config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_BASE1 : Constants.MT20_BASE1) + "EVENT" + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_Ident$WipeFinal$Out$Team_1$img_Base2*TEXTURE*IMAGE SET " + 
						(config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_BASE2 : Constants.MT20_BASE2) + "EVENT" + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_Ident$WipeFinal$Out$Team_1$img_Logos_BW*TEXTURE*IMAGE SET " + 
						(config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_LOGO_BW : Constants.MT20_LOGO_BW) + "EVENT" + "\0", print_writers);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_Ident$WipeFinal$Out$Team_2$img_Base1*TEXTURE*IMAGE SET " + 
						(config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_BASE1 : Constants.MT20_BASE1) + "EVENT" + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_Ident$WipeFinal$Out$Team_2$img_Base2*TEXTURE*IMAGE SET " + 
						(config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_BASE2 : Constants.MT20_BASE2) + "EVENT" + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_Ident$WipeFinal$Out$Team_2$img_Logos_BW*TEXTURE*IMAGE SET " + 
						(config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_LOGO_BW : Constants.MT20_LOGO_BW) + "EVENT" + "\0", print_writers);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_Ident$WipeFinal$EventLogoAll$EventLogo*TEXTURE*IMAGE SET " + 
						(config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_LOGO : Constants.MT20_LOGO) + "EVENT" + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_Ident$Main$SideColourBands$Team_1$img_Base1*TEXTURE*IMAGE SET " + 
						(config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_BASE1 : Constants.MT20_BASE1) + matchAllData.getSetup().getHomeTeam().getTeamBadge() + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_Ident$Main$SideColourBands$Team_1$img_Base2*TEXTURE*IMAGE SET " + 
						(config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_BASE2 : Constants.MT20_BASE2) + matchAllData.getSetup().getHomeTeam().getTeamBadge() + "\0", print_writers);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_Ident$Main$SideColourBands$Team_2$img_Base1*TEXTURE*IMAGE SET " + 
						(config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_BASE1 : Constants.MT20_BASE1) + matchAllData.getSetup().getAwayTeam().getTeamBadge() + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_Ident$Main$SideColourBands$Team_2$img_Base2*TEXTURE*IMAGE SET " + 
						(config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_BASE2 : Constants.MT20_BASE2) + matchAllData.getSetup().getAwayTeam().getTeamBadge() + "\0", print_writers);
				break;
			case "Control_m":
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_Ident$WipeFinal$In$Team_1$img_Base1*TEXTURE*IMAGE SET " + 
						(config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_BASE1 : Constants.MT20_BASE1) + fixture.getHome_Team().getTeamBadge() + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_Ident$WipeFinal$In$Team_1$img_Base2*TEXTURE*IMAGE SET " + 
						(config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_BASE2 : Constants.MT20_BASE2) + fixture.getHome_Team().getTeamBadge() + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_Ident$WipeFinal$In$Team_1$img_Logos_BW*TEXTURE*IMAGE SET " + 
						(config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_LOGO_BW : Constants.MT20_LOGO_BW) + fixture.getHome_Team().getTeamBadge() + "\0", print_writers);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_Ident$WipeFinal$In$Team_2$img_Base1*TEXTURE*IMAGE SET " + 
						(config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_BASE1 : Constants.MT20_BASE1) + fixture.getAway_Team().getTeamBadge() + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_Ident$WipeFinal$In$Team_2$img_Base2*TEXTURE*IMAGE SET " + 
						(config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_BASE2 : Constants.MT20_BASE2) + fixture.getAway_Team().getTeamBadge() + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_Ident$WipeFinal$In$Team_2$img_Logos_BW*TEXTURE*IMAGE SET " + 
						(config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_LOGO_BW : Constants.MT20_LOGO_BW) + fixture.getAway_Team().getTeamBadge() + "\0", print_writers);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_Ident$WipeFinal$Out$Team_1$img_Base1*TEXTURE*IMAGE SET " + 
						(config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_BASE1 : Constants.MT20_BASE1) + "EVENT" + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_Ident$WipeFinal$Out$Team_1$img_Base2*TEXTURE*IMAGE SET " + 
						(config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_BASE2 : Constants.MT20_BASE2) + "EVENT" + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_Ident$WipeFinal$Out$Team_1$img_Logos_BW*TEXTURE*IMAGE SET " + 
						(config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_LOGO_BW : Constants.MT20_LOGO_BW) + "EVENT" + "\0", print_writers);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_Ident$WipeFinal$Out$Team_2$img_Base1*TEXTURE*IMAGE SET " + 
						(config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_BASE1 : Constants.MT20_BASE1) + "EVENT" + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_Ident$WipeFinal$Out$Team_2$img_Base2*TEXTURE*IMAGE SET " + 
						(config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_BASE2 : Constants.MT20_BASE2) + "EVENT" + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_Ident$WipeFinal$Out$Team_2$img_Logos_BW*TEXTURE*IMAGE SET " + 
						(config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_LOGO_BW : Constants.MT20_LOGO_BW) + "EVENT" + "\0", print_writers);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_Ident$WipeFinal$EventLogoAll$EventLogo*TEXTURE*IMAGE SET " + 
						(config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_LOGO : Constants.MT20_LOGO) + "EVENT" + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_Ident$Main$SideColourBands$Team_1$img_Base1*TEXTURE*IMAGE SET " + 
						(config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_BASE1 : Constants.MT20_BASE1) + fixture.getHome_Team().getTeamBadge() + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_Ident$Main$SideColourBands$Team_1$img_Base2*TEXTURE*IMAGE SET " + 
						(config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_BASE2 : Constants.MT20_BASE2) + fixture.getHome_Team().getTeamBadge() + "\0", print_writers);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_Ident$Main$SideColourBands$Team_2$img_Base1*TEXTURE*IMAGE SET " + 
						(config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_BASE1 : Constants.MT20_BASE1) + fixture.getAway_Team().getTeamBadge() + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_Ident$Main$SideColourBands$Team_2$img_Base2*TEXTURE*IMAGE SET " + 
						(config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_BASE2 : Constants.MT20_BASE2) + fixture.getAway_Team().getTeamBadge() + "\0", print_writers);
				break;
			case "Control_d": case "Control_e": case "Shift_P": case "Shift_Q":
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_Profile$WipeFinal$In$img_Base1*TEXTURE*IMAGE SET " + 
						(config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_BASE1 : Constants.MT20_BASE1) + team.getTeamBadge() + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_Profile$WipeFinal$In$img_Base2*TEXTURE*IMAGE SET " + 
						(config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_BASE2 : Constants.MT20_BASE2) + team.getTeamBadge() + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_Profile$WipeFinal$In$img_Logos_BW*TEXTURE*IMAGE SET " + 
						(config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_LOGO_BW : Constants.MT20_LOGO_BW) + team.getTeamBadge() + "\0", print_writers);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_Profile$WipeFinal$Out$img_Base1*TEXTURE*IMAGE SET " + 
						(config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_BASE1 : Constants.MT20_BASE1) + "EVENT" + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_Profile$WipeFinal$Out$img_Base2*TEXTURE*IMAGE SET " + 
						(config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_BASE2 : Constants.MT20_BASE2) + "EVENT" + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_Profile$WipeFinal$Out$img_Logos_BW*TEXTURE*IMAGE SET " + 
						(config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_LOGO_BW : Constants.MT20_LOGO_BW) + "EVENT" + "\0", print_writers);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_Profile$Main$SideColourBands$Side" + WhichSide + "$img_Base1"
						+ "*TEXTURE*IMAGE SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_BASE1 : Constants.MT20_BASE1) + team.getTeamBadge() + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_Profile$Main$SideColourBands$Side" + WhichSide + "$img_Base2"
						+ "*TEXTURE*IMAGE SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_BASE2 : Constants.MT20_BASE2) + team.getTeamBadge() + "\0", print_writers);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_Profile$Main$Logo" + (WhichSide==1?"$Change_Out":"$Change_In") 
						+ "$img_Logo*TEXTURE*IMAGE SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_LOGO : Constants.MT20_LOGO) + team.getTeamBadge() + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_Profile$Main$GlassBG$Side" + WhichSide + "$img_Logos_BW*TEXTURE*IMAGE SET " 
						+ (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_LOGO_BW : Constants.MT20_LOGO_BW) + team.getTeamBadge() + "\0", print_writers);
				break;
			case "Control_F7": case "Control_F11": case "Alt_F11": case "Shift_F11": case "Control_p": case "Control_Alt_F1":
			case "z": case "x": case "c": case "v": case "Control_z": case "Control_x": case "Control_Shift_Z": case "Control_Shift_Y":
			case "Alt_Shift_W":
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$SideColourBands$"
						+ "Side" + WhichSide + "$img_Base1*TEXTURE*IMAGE SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_BASE1 : Constants.MT20_BASE1) + "EVENT" + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$SideColourBands$"
						+ "Side" + WhichSide + "$img_Base2*TEXTURE*IMAGE SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_BASE2 : Constants.MT20_BASE2) + "EVENT" + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$LogoGrp$TeamColour$"
						+ "Side" + WhichSide + "$img_Base1*TEXTURE*IMAGE SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_BASE1 : Constants.MT20_BASE1) + "EVENT" + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$LogoGrp$TeamColour$"
						+ "Side" + WhichSide + "$img_Base2*TEXTURE*IMAGE SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_BASE2 : Constants.MT20_BASE2) + "EVENT" + "\0", print_writers);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$WipeFinal$In$img_Base1*TEXTURE*IMAGE SET " + 
						(config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_BASE1 : Constants.MT20_BASE1) + "EVENT" + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$WipeFinal$In$img_Base2*TEXTURE*IMAGE SET " + 
						(config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_BASE2 : Constants.MT20_BASE2) + "EVENT" + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$WipeFinal$In$img_Logos_BW*TEXTURE*IMAGE SET " + 
						(config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_LOGO : Constants.MT20_LOGO) + "EVENT" + "\0", print_writers);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$WipeFinal$Out$img_Base1*TEXTURE*IMAGE SET " + 
						(config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_BASE1 : Constants.MT20_BASE1) + "EVENT" + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$WipeFinal$Out$img_Base2*TEXTURE*IMAGE SET " + 
						(config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_BASE2 : Constants.MT20_BASE2) + "EVENT" + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$WipeFinal$Out$img_Logos_BW*TEXTURE*IMAGE SET " + 
						(config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_LOGO : Constants.MT20_LOGO) + "EVENT" + "\0", print_writers);
				break;
			case "Shift_F10":
				String which_logo_color ="";
				if(inning.getInningNumber() == 1) {
					which_logo_color = inning.getBatting_team().getTeamBadge();
				}else if(inning.getInningNumber() == 2) {
					which_logo_color = "EVENT";
				}
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$SideColourBands$Side" + WhichSide + 
						"$img_Base1*TEXTURE*IMAGE SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_BASE1 : Constants.MT20_BASE1) + which_logo_color + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$SideColourBands$Side" + WhichSide + 
						"$img_Base2*TEXTURE*IMAGE SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_BASE2 : Constants.MT20_BASE2) + which_logo_color + "\0", print_writers);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$LogoGrp$TeamColour$Side" + WhichSide + 
						"$img_Base1*TEXTURE*IMAGE SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_BASE1 : Constants.MT20_BASE1) + which_logo_color + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$LogoGrp$TeamColour$Side" + WhichSide + 
						"$img_Base2*TEXTURE*IMAGE SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_BASE2 : Constants.MT20_BASE2) + which_logo_color + "\0", print_writers);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$WipeFinal$In$img_Base1*TEXTURE*IMAGE SET " + 
						(config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_BASE1 : Constants.MT20_BASE1) + which_logo_color + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$WipeFinal$In$img_Base2*TEXTURE*IMAGE SET " + 
						(config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_BASE2 : Constants.MT20_BASE2) + which_logo_color + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$WipeFinal$In$img_Logos_BW*TEXTURE*IMAGE SET " + 
						(config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_LOGO_BW : Constants.MT20_LOGO_BW) + which_logo_color + "\0", print_writers);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$WipeFinal$Out$img_Base1*TEXTURE*IMAGE SET " + 
						(config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_BASE1 : Constants.MT20_BASE1) + "EVENT" + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$WipeFinal$Out$img_Base2*TEXTURE*IMAGE SET " + 
						(config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_BASE2 : Constants.MT20_BASE2) + "EVENT" + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$WipeFinal$Out$img_Logos_BW*TEXTURE*IMAGE SET " + 
						(config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_LOGO_BW : Constants.MT20_LOGO_BW) + "EVENT" + "\0", print_writers);
				break;
				
			case "F1": case "F4": case "Shift_K": case "Control_F10": case "Alt_F9": case "Control_F1": case "Alt_Shift_F1":
			case "Control_Shift_F4":
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$SideColourBands$"
						+ "Side" + WhichSide + "$img_Base1*TEXTURE*IMAGE SET " + 
						(config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_BASE1 : Constants.MT20_BASE1) + inning.getBatting_team().getTeamBadge() + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$SideColourBands$"
						+ "Side" + WhichSide + "$img_Base2*TEXTURE*IMAGE SET " + 
						(config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_BASE2 : Constants.MT20_BASE2) + inning.getBatting_team().getTeamBadge() + "\0", print_writers);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$LogoGrp$TeamColour$"
						+ "Side" + WhichSide + "$img_Base1*TEXTURE*IMAGE SET " + 
						(config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_BASE1 : Constants.MT20_BASE1) + inning.getBatting_team().getTeamBadge() + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$LogoGrp$TeamColour$"
						+ "Side" + WhichSide + "$img_Base2*TEXTURE*IMAGE SET " + 
						(config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_BASE2 : Constants.MT20_BASE2) + inning.getBatting_team().getTeamBadge() + "\0", print_writers);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$WipeFinal$In$img_Base1*TEXTURE*IMAGE SET " + 
						(config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_BASE1 : Constants.MT20_BASE1) + inning.getBatting_team().getTeamBadge() + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$WipeFinal$In$img_Base2*TEXTURE*IMAGE SET " + 
						(config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_BASE2 : Constants.MT20_BASE2) + inning.getBatting_team().getTeamBadge() + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$WipeFinal$In$img_Logos_BW*TEXTURE*IMAGE SET " + 
						(config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_LOGO_BW : Constants.MT20_LOGO_BW) + inning.getBatting_team().getTeamBadge() + "\0", print_writers);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$WipeFinal$Out$img_Base1*TEXTURE*IMAGE SET " + 
						(config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_BASE1 : Constants.MT20_BASE1) + "EVENT" + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$WipeFinal$Out$img_Base2*TEXTURE*IMAGE SET " + 
						(config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_BASE2 : Constants.MT20_BASE2) + "EVENT" + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$WipeFinal$Out$img_Logos_BW*TEXTURE*IMAGE SET " + 
						(config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_LOGO_BW : Constants.MT20_LOGO_BW) + "EVENT" + "\0", print_writers);
				
				break;
			case "F2": case "Control_Shift_F5":
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$SideColourBands$"
						+ "Side" + WhichSide + "$img_Base1*TEXTURE*IMAGE SET " + 
						(config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_BASE1 : Constants.MT20_BASE1) + inning.getBowling_team().getTeamBadge() + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$SideColourBands$"
						+ "Side" + WhichSide + "$img_Base2*TEXTURE*IMAGE SET " + 
						(config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_BASE2 : Constants.MT20_BASE2) + inning.getBowling_team().getTeamBadge() + "\0", print_writers);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$LogoGrp$TeamColour$"
						+ "Side" + WhichSide + "$img_Base1*TEXTURE*IMAGE SET " + 
						(config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_BASE1 : Constants.MT20_BASE1) + inning.getBowling_team().getTeamBadge() + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$LogoGrp$TeamColour$"
						+ "Side" + WhichSide + "$img_Base2*TEXTURE*IMAGE SET " + 
						(config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_BASE2 : Constants.MT20_BASE2) + inning.getBowling_team().getTeamBadge() + "\0", print_writers);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$WipeFinal$In$img_Base1*TEXTURE*IMAGE SET " + 
						(config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_BASE1 : Constants.MT20_BASE1) + inning.getBowling_team().getTeamBadge() + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$WipeFinal$In$img_Base2*TEXTURE*IMAGE SET " + 
						(config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_BASE2 : Constants.MT20_BASE2) + inning.getBowling_team().getTeamBadge() + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$WipeFinal$In$img_Logos_BW*TEXTURE*IMAGE SET " + 
						(config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_LOGO_BW : Constants.MT20_LOGO_BW) + inning.getBowling_team().getTeamBadge() + "\0", print_writers);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$WipeFinal$Out$img_Base1*TEXTURE*IMAGE SET " + 
						(config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_BASE1 : Constants.MT20_BASE1) + "EVENT" + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$WipeFinal$Out$img_Base2*TEXTURE*IMAGE SET " + 
						(config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_BASE2 : Constants.MT20_BASE2) + "EVENT" + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$WipeFinal$Out$img_Logos_BW*TEXTURE*IMAGE SET " + 
						(config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_LOGO_BW : Constants.MT20_LOGO_BW) + "EVENT" + "\0", print_writers);
				break;
			case "Shift_D":
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_Target$SideColourBands$Team_1$"
						+ "img_Base1*TEXTURE*IMAGE SET " + 
						(config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_BASE1 : Constants.MT20_BASE1) + matchAllData.getSetup().getHomeTeam().getTeamBadge() + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_Target$SideColourBands$Team_1$"
						+ "img_Base2*TEXTURE*IMAGE SET " + 
						(config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_BASE2 : Constants.MT20_BASE2) + matchAllData.getSetup().getHomeTeam().getTeamBadge() + "\0", print_writers);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_Target$SideColourBands$Team_2$"
						+ "img_Base1*TEXTURE*IMAGE SET " + 
						(config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_BASE1 : Constants.MT20_BASE1) + matchAllData.getSetup().getAwayTeam().getTeamBadge() + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_Target$SideColourBands$Team_2$"
						+ "img_Base2*TEXTURE*IMAGE SET " + 
						(config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_BASE2 : Constants.MT20_BASE2) + matchAllData.getSetup().getAwayTeam().getTeamBadge() + "\0", print_writers);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_Target$WipeFinal$In$Team_1$img_Base1*TEXTURE*IMAGE SET " + 
						(config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_BASE1 : Constants.MT20_BASE1) + matchAllData.getSetup().getHomeTeam().getTeamBadge() + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_Target$WipeFinal$In$Team_1$img_Base2*TEXTURE*IMAGE SET " + 
						(config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_BASE2 : Constants.MT20_BASE2) + matchAllData.getSetup().getHomeTeam().getTeamBadge() + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_Target$WipeFinal$In$Team_1$img_Logos_BW*TEXTURE*IMAGE SET " + 
						(config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_LOGO_BW : Constants.MT20_LOGO_BW) + matchAllData.getSetup().getHomeTeam().getTeamBadge() + "\0", print_writers);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_Target$WipeFinal$In$Team_2$img_Base1*TEXTURE*IMAGE SET " + 
						(config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_BASE1 : Constants.MT20_BASE1) + matchAllData.getSetup().getAwayTeam().getTeamBadge() + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_Target$WipeFinal$In$Team_2$img_Base2*TEXTURE*IMAGE SET " + 
						(config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_BASE2 : Constants.MT20_BASE2) + matchAllData.getSetup().getAwayTeam().getTeamBadge() + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_Target$WipeFinal$In$Team_2$img_Logos_BW*TEXTURE*IMAGE SET " + 
						(config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_LOGO_BW : Constants.MT20_LOGO_BW) + matchAllData.getSetup().getAwayTeam().getTeamBadge() + "\0", print_writers);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_Target$WipeFinal$Out$Team_1$img_Base1*TEXTURE*IMAGE SET " + 
						(config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_BASE1 : Constants.MT20_BASE1) + "EVENT" + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_Target$WipeFinal$Out$Team_1$img_Base2*TEXTURE*IMAGE SET " + 
						(config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_BASE2 : Constants.MT20_BASE2) + "EVENT" + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_Target$WipeFinal$Out$Team_1$img_Logos_BW*TEXTURE*IMAGE SET " + 
						(config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_LOGO_BW : Constants.MT20_LOGO_BW) + "EVENT" + "\0", print_writers);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_Target$WipeFinal$Out$Team_2$img_Base1*TEXTURE*IMAGE SET " + 
						(config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_BASE1 : Constants.MT20_BASE1) + "EVENT" + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_Target$WipeFinal$Out$Team_2$img_Base2*TEXTURE*IMAGE SET " + 
						(config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_BASE2 : Constants.MT20_BASE2) + "EVENT" + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_Target$WipeFinal$Out$Team_2$img_Logos_BW*TEXTURE*IMAGE SET " + 
						(config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_LOGO_BW : Constants.MT20_LOGO_BW) + "EVENT" + "\0", print_writers);
				break;
			case "Control_Shift_F7":
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_LineUp_Image$Shift_For_TopTitle$SideColourBands$"
						+ "Side" + WhichSide + "$img_Base1*TEXTURE*IMAGE SET " + 
						(config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_BASE1 : Constants.MT20_BASE1) + team.getTeamBadge() + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_LineUp_Image$Shift_For_TopTitle$SideColourBands$"
						+ "Side" + WhichSide + "$img_Base2*TEXTURE*IMAGE SET " + 
						(config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_BASE2 : Constants.MT20_BASE2) + team.getTeamBadge() + "\0", print_writers);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_LineUp_Image$Shift_For_TopTitle$LogoGrp$TeamColour$"
						+ "Side" + WhichSide + "$img_Base1*TEXTURE*IMAGE SET " + 
						(config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_BASE1 : Constants.MT20_BASE1) + team.getTeamBadge() + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_LineUp_Image$Shift_For_TopTitle$LogoGrp$TeamColour$"
						+ "Side" + WhichSide + "$img_Base2*TEXTURE*IMAGE SET " + 
						(config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_BASE2 : Constants.MT20_BASE2) + team.getTeamBadge() + "\0", print_writers);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_LineUp_Image$WipeFinal$In$img_Base1*TEXTURE*IMAGE SET " + 
						(config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_BASE1 : Constants.MT20_BASE1) + team.getTeamBadge() + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_LineUp_Image$WipeFinal$In$img_Base2*TEXTURE*IMAGE SET " + 
						(config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_BASE2 : Constants.MT20_BASE2) + team.getTeamBadge() + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_LineUp_Image$WipeFinal$In$img_Logos_BW*TEXTURE*IMAGE SET " + 
						(config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_LOGO_BW : Constants.MT20_LOGO_BW) + team.getTeamBadge() + "\0", print_writers);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_LineUp_Image$WipeFinal$Out$img_Base1*TEXTURE*IMAGE SET " + 
						(config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_BASE1 : Constants.MT20_BASE1) + "EVENT" + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_LineUp_Image$WipeFinal$Out$img_Base2*TEXTURE*IMAGE SET " + 
						(config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_BASE2 : Constants.MT20_BASE2) + "EVENT" + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_LineUp_Image$WipeFinal$Out$img_Logos_BW*TEXTURE*IMAGE SET " + 
						(config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_LOGO_BW : Constants.MT20_LOGO_BW) + "EVENT" + "\0", print_writers);
				
				break;
			}
			TimeUnit.MILLISECONDS.sleep(600);
			break;
		}
	}
	
	public void teamColor(List<PrintWriter> print_writers, int WhichSide, String whatToProcess, MatchAllData matchAllData, Inning inning,Configuration config) throws InterruptedException {
		switch (whatToProcess) {
		case "Control_Shift_F4":
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Main$TitleGrp$Side" + WhichSide + "$img_Base1*TEXTURE*IMAGE SET " + 
					(config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_BASE1 : Constants.MT20_BASE1) + inning.getBatting_team().getTeamBadge() + "\0", print_writers);
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Main$Footer$Side" + WhichSide + "$FooterStyle1$Extras$img_Base1*TEXTURE*IMAGE SET " + 
					(config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_BASE1 : Constants.MT20_BASE1) + inning.getBatting_team().getTeamBadge() + "\0", print_writers);
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Main$Footer$Side" + WhichSide + "$FooterStyle1$Overs$img_Base1*TEXTURE*IMAGE SET " + 
					(config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_BASE1 : Constants.MT20_BASE1) + inning.getBatting_team().getTeamBadge() + "\0", print_writers);
			
			for(int i=1;i<=13;i++) {
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Main$AllGraphics$Side" + WhichSide + "$BattingCardExtra$BatterExtraGrp$" + i + "$BattingExtraData$"
						+ "Still_To_Bat$img_Base1*TEXTURE*IMAGE SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_BASE1 : Constants.MT20_BASE1) + inning.getBatting_team().getTeamBadge() + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Main$AllGraphics$Side" + WhichSide + "$BattingCardExtra$BatterExtraGrp$" + i + "$BattingExtraData$"
						+ "Out$img_Base1*TEXTURE*IMAGE SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_BASE1 : Constants.MT20_BASE1) + inning.getBatting_team().getTeamBadge() + "\0", print_writers);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Main$AllGraphics$Side" + WhichSide + "$BattingCardExtra$" + i + "$BattingExtraData$BatterExtraGrp$"
						+ "Still_To_Bat$img_Text1*TEXTURE*IMAGE SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_TEXT1 : Constants.MT20_TEXT1) + inning.getBatting_team().getTeamBadge() + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Main$AllGraphics$Side" + WhichSide + "$BattingCardExtra$" + i + "$BattingExtraData$BatterExtraGrp$"
						+ "Out$img_Text1*TEXTURE*IMAGE SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_TEXT1 : Constants.MT20_TEXT1) + inning.getBatting_team().getTeamBadge() + "\0", print_writers);
				
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Main$AllGraphics$Side" + WhichSide + "$BattingCardExtra$" + i + "$Highlight$"
						+ "img_Base1*TEXTURE*IMAGE SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_BASE1 : Constants.MT20_BASE1) + inning.getBatting_team().getTeamBadge() + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Main$AllGraphics$Side" + WhichSide + "$BattingCardExtra$" + i + "$Highlight$"
						+ "img_Base2*TEXTURE*IMAGE SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_BASE2 : Constants.MT20_BASE2) + inning.getBatting_team().getTeamBadge() + "\0", print_writers);
				
			}
			
			for(int i=1;i<=6;i++) {
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Main$AllGraphics$Side" + WhichSide + "$BattingCardExtra$ExtaData$DetailData$" + i + "$Dehighlight$"
						+ "img_Base1*TEXTURE*IMAGE SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_BASE1 : Constants.MT20_BASE1) + inning.getBatting_team().getTeamBadge() + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Main$AllGraphics$Side" + WhichSide + "$BattingCardExtra$ExtaData$DetailData$" + i + "$Dehighlight$"
						+ "img_Text1*TEXTURE*IMAGE SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_TEXT1 : Constants.MT20_TEXT1) + inning.getBatting_team().getTeamBadge() + "\0", print_writers);
			}
			break;
		case "Control_Shift_F5":
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Main$TitleGrp$Side" + WhichSide + "$img_Base1*TEXTURE*IMAGE SET " + 
					(config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_BASE1 : Constants.MT20_BASE1) + inning.getBowling_team().getTeamBadge() + "\0", print_writers);
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Main$Footer$Side" + WhichSide + "$FooterStyle1$Extras$img_Base1*TEXTURE*IMAGE SET " + 
					(config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_BASE1 : Constants.MT20_BASE1) + inning.getBowling_team().getTeamBadge() + "\0", print_writers);
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Main$Footer$Side" + WhichSide + "$FooterStyle1$Overs$img_Base1*TEXTURE*IMAGE SET " + 
					(config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_BASE1 : Constants.MT20_BASE1) + inning.getBowling_team().getTeamBadge() + "\0", print_writers);
			
			for(int i=1;i<=13;i++) {
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Main$AllGraphics$Side" + WhichSide + "$BowlingCardExtra$BowlerExtraGrp$" + i + "$BowlerExtraData$"
						+ "Dehighlight$img_Base1*TEXTURE*IMAGE SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_BASE1 : Constants.MT20_BASE1) + inning.getBowling_team().getTeamBadge() + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Main$AllGraphics$Side" + WhichSide + "$BowlingCardExtra$BowlerExtraGrp$" + i + "$BowlerExtraData$"
						+ "Dehighlight$img_Text1*TEXTURE*IMAGE SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_TEXT1 : Constants.MT20_TEXT1) + inning.getBowling_team().getTeamBadge() + "\0", print_writers);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Main$AllGraphics$Side" + WhichSide + "$BowlingCardExtra$" + i + "$Highlight$"
						+ "img_Base1*TEXTURE*IMAGE SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_BASE1 : Constants.MT20_BASE1) + inning.getBowling_team().getTeamBadge() + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Main$AllGraphics$Side" + WhichSide + "$BowlingCardExtra$" + i + "$Highlight$"
						+ "img_Base2*TEXTURE*IMAGE SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_BASE2 : Constants.MT20_BASE2) + inning.getBowling_team().getTeamBadge() + "\0", print_writers);
				
			}
			
			for(int i=1;i<=6;i++) {
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Main$AllGraphics$Side" + WhichSide + "$BowlingCardExtra$ExtaData$DetailData$" + i + "$Dehighlight$"
						+ "img_Base1*TEXTURE*IMAGE SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_BASE1 : Constants.MT20_BASE1) + inning.getBowling_team().getTeamBadge() + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Main$AllGraphics$Side" + WhichSide + "$BowlingCardExtra$ExtaData$DetailData$" + i + "$Dehighlight$"
						+ "img_Text1*TEXTURE*IMAGE SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_TEXT1 : Constants.MT20_TEXT1) + inning.getBowling_team().getTeamBadge() + "\0", print_writers);
			}
			break;	
		case "Alt_F11":
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Main$TitleGrp$Side" + WhichSide + "$img_Base1*TEXTURE*IMAGE SET " + 
					(config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_BASE1 : Constants.MT20_BASE1) + "EVENT" + "\0", print_writers);
			break;
		case "z": case "x": case "c": case "v": case "Control_z": case "Control_x": case "Control_Shift_Z": case "Control_Shift_Y":
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Main$TitleGrp$Side" + WhichSide + "$img_Base1*TEXTURE*IMAGE SET " + 
					(config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_BASE1 : Constants.MT20_BASE1) + "EVENT" + "\0", print_writers);
			
			for(int i=1;i<=5;i++) {
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Main$AllGraphics$Side" + WhichSide + "$LeaderBoard$Rows$" + i + "$Dehighlight$"
						+ "img_Base1*TEXTURE*IMAGE SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_BASE1 : Constants.MT20_BASE1) + "EVENT" + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Main$AllGraphics$Side" + WhichSide + "$LeaderBoard$Rows$" + i + "$Dehighlight$"
						+ "img_Text1*TEXTURE*IMAGE SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_TEXT1 : Constants.MT20_TEXT1) + "EVENT" + "\0", print_writers);
			}
			
			
			break;
		case "Shift_F10":
			String which_logo_color ="";
			if(inning.getInningNumber() == 1) {
				which_logo_color = inning.getBatting_team().getTeamBadge();
			}else if(inning.getInningNumber() == 2) {
				which_logo_color = "EVENT";
			}
			
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Main$TitleGrp$Side" + WhichSide + "$img_Base1*TEXTURE*IMAGE SET " + 
					(config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_BASE1 : Constants.MT20_BASE1) + which_logo_color + "\0", print_writers);
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Main$Footer$Side" + WhichSide + "$FooterStyle1$Extras$img_Base1*TEXTURE*IMAGE SET " + 
					(config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_BASE1 : Constants.MT20_BASE1) + which_logo_color + "\0", print_writers);
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Main$Footer$Side" + WhichSide + "$FooterStyle1$Overs$img_Base1*TEXTURE*IMAGE SET " + 
					(config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_BASE1 : Constants.MT20_BASE1) + which_logo_color + "\0", print_writers);
			
			break;
		case "Control_F10":
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Main$TitleGrp$Side" + WhichSide + "$img_Base1*TEXTURE*IMAGE SET " + 
					(config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_BASE1 : Constants.MT20_BASE1) + inning.getBatting_team().getTeamBadge() + "\0", print_writers);
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Main$Footer$Side" + WhichSide + "$FooterStyle1$Extras$img_Base1*TEXTURE*IMAGE SET " + 
					(config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_BASE1 : Constants.MT20_BASE1) + inning.getBatting_team().getTeamBadge() + "\0", print_writers);
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Main$Footer$Side" + WhichSide + "$FooterStyle1$Overs$img_Base1*TEXTURE*IMAGE SET " + 
					(config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_BASE1 : Constants.MT20_BASE1) + inning.getBatting_team().getTeamBadge() + "\0", print_writers);
			break;
		case "Shift_K":
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Main$TitleGrp$Side" + WhichSide + "$img_Base1*TEXTURE*IMAGE SET " + 
					(config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_BASE1 : Constants.MT20_BASE1) + inning.getBatting_team().getTeamBadge() + "\0", print_writers);
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Main$AllGraphics$Side" + WhichSide + "$Partnership$Stats$2$RunsGrp$"
					+ "img_Base1*TEXTURE*IMAGE SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_BASE1 : Constants.MT20_BASE1) + inning.getBatting_team().getTeamBadge() + "\0", print_writers);
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Main$AllGraphics$Side" + WhichSide + "$Partnership$Stats$2$RunsGrp$"
					+ "img_Text1*TEXTURE*IMAGE SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_TEXT1 : Constants.MT20_TEXT1) + inning.getBatting_team().getTeamBadge() + "\0", print_writers);
			
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Main$AllGraphics$Side" + WhichSide + "$Partnership$Stats$3$StikeRate$"
					+ "img_Base1*TEXTURE*IMAGE SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_BASE1 : Constants.MT20_BASE1) + inning.getBatting_team().getTeamBadge() + "\0", print_writers);
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Main$AllGraphics$Side" + WhichSide + "$Partnership$Stats$3$StikeRate$"
					+ "img_Text1*TEXTURE*IMAGE SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_TEXT1 : Constants.MT20_TEXT1) + inning.getBatting_team().getTeamBadge() + "\0", print_writers);
			
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Main$AllGraphics$Side" + WhichSide + "$Partnership$Stats$4$Contribution$Title$"
					+ "img_Base1*TEXTURE*IMAGE SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_BASE1 : Constants.MT20_BASE1) + inning.getBatting_team().getTeamBadge() + "\0", print_writers);
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Main$AllGraphics$Side" + WhichSide + "$Partnership$Stats$4$Contribution$Title$"
					+ "img_Text1*TEXTURE*IMAGE SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_TEXT1 : Constants.MT20_TEXT1) + inning.getBatting_team().getTeamBadge() + "\0", print_writers);
			
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Main$AllGraphics$Side" + WhichSide + "$Partnership$Stats$4$Contribution$1$"
					+ "img_Base1*TEXTURE*IMAGE SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_BASE1 : Constants.MT20_BASE1) + inning.getBatting_team().getTeamBadge() + "\0", print_writers);
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Main$AllGraphics$Side" + WhichSide + "$Partnership$Stats$4$Contribution$1$"
					+ "img_Text1*TEXTURE*IMAGE SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_TEXT1 : Constants.MT20_TEXT1) + inning.getBatting_team().getTeamBadge() + "\0", print_writers);
			
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Main$AllGraphics$Side" + WhichSide + "$Partnership$Stats$4$Contribution$2$"
					+ "img_Base1*TEXTURE*IMAGE SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_BASE1 : Constants.MT20_BASE1) + inning.getBatting_team().getTeamBadge() + "\0", print_writers);
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Main$AllGraphics$Side" + WhichSide + "$Partnership$Stats$4$Contribution$2$"
					+ "img_Text1*TEXTURE*IMAGE SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_TEXT1 : Constants.MT20_TEXT1) + inning.getBatting_team().getTeamBadge() + "\0", print_writers);
			break;
		case "F1": case "F4":
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Main$TitleGrp$Side" + WhichSide + "$img_Base1*TEXTURE*IMAGE SET " + 
					(config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_BASE1 : Constants.MT20_BASE1) + inning.getBatting_team().getTeamBadge() + "\0", print_writers);
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Main$Footer$Side" + WhichSide + "$FooterStyle1$Extras$img_Base1*TEXTURE*IMAGE SET " + 
					(config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_BASE1 : Constants.MT20_BASE1) + inning.getBatting_team().getTeamBadge() + "\0", print_writers);
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Main$Footer$Side" + WhichSide + "$FooterStyle1$Overs$img_Base1*TEXTURE*IMAGE SET " + 
					(config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_BASE1 : Constants.MT20_BASE1) + inning.getBatting_team().getTeamBadge() + "\0", print_writers);
			
			for(int i=1;i<=13;i++) {
				switch (whatToProcess) {
				case "F1":
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Main$AllGraphics$Side" + WhichSide + "$BattingCard$BatterGrp$" + i + "$LeftSplitBatData$"
							+ "Still_To_Bat$img_Base1*TEXTURE*IMAGE SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_BASE1 : Constants.MT20_BASE1) + inning.getBatting_team().getTeamBadge() + "\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Main$AllGraphics$Side" + WhichSide + "$BattingCard$BatterGrp$" + i + "$LeftSplitBatData$"
							+ "Out$img_Base1*TEXTURE*IMAGE SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_BASE1 : Constants.MT20_BASE1) + inning.getBatting_team().getTeamBadge() + "\0", print_writers);
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Main$AllGraphics$Side" + WhichSide + "$BattingCard$BatterGrp$" + i + "$Highlight$"
							+ "img_Base1*TEXTURE*IMAGE SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_BASE1 : Constants.MT20_BASE1) + inning.getBatting_team().getTeamBadge() + "\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Main$AllGraphics$Side" + WhichSide + "$BattingCard$BatterGrp$" + i + "$Highlight$"
							+ "img_Base2*TEXTURE*IMAGE SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_BASE2 : Constants.MT20_BASE2) + inning.getBatting_team().getTeamBadge() + "\0", print_writers);
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Main$AllGraphics$Side" + WhichSide + "$BattingCard$DismissalGrp$" + i + "$DismissalData$"
							+ "Out$img_Base1*TEXTURE*IMAGE SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_BASE1 : Constants.MT20_BASE1) + inning.getBatting_team().getTeamBadge() + "\0", print_writers);
					break;
				case "F4":
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Main$AllGraphics$Side" + WhichSide + "$PartnershipList$" + i + "$PartnershipListData$"
							+ "Title$img_Base1*TEXTURE*IMAGE SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_BASE1 : Constants.MT20_BASE1) + inning.getBatting_team().getTeamBadge() + "\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Main$AllGraphics$Side" + WhichSide + "$PartnershipList$" + i + "$PartnershipListData$"
							+ "Still_To_Bat$img_Base1*TEXTURE*IMAGE SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_BASE1 : Constants.MT20_BASE1) + inning.getBatting_team().getTeamBadge() + "\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Main$AllGraphics$Side" + WhichSide + "$PartnershipList$" + i + "$PartnershipListData$"
							+ "Out$img_Base1*TEXTURE*IMAGE SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_BASE1 : Constants.MT20_BASE1) + inning.getBatting_team().getTeamBadge() + "\0", print_writers);
					break;
				}
			}
			break;
		case "Control_F1":
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Main$TitleGrp$Side" + WhichSide + "$img_Base1*TEXTURE*IMAGE SET " + 
					(config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_BASE1 : Constants.MT20_BASE1) + inning.getBatting_team().getTeamBadge() + "\0", print_writers);
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Main$Footer$Side" + WhichSide + "$FooterStyle1$Extras$img_Base1*TEXTURE*IMAGE SET " + 
					(config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_BASE1 : Constants.MT20_BASE1) + inning.getBatting_team().getTeamBadge() + "\0", print_writers);
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Main$Footer$Side" + WhichSide + "$FooterStyle1$Overs$img_Base1*TEXTURE*IMAGE SET " + 
					(config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_BASE1 : Constants.MT20_BASE1) + inning.getBatting_team().getTeamBadge() + "\0", print_writers);
			
			for(int i=1;i<=6;i++) {
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Main$AllGraphics$Side" + WhichSide + "$ImageBattingCard$Top$" + i + "$TextGrp$Data$Out"
						+ "$img_Base1*TEXTURE*IMAGE SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_BASE1 : Constants.MT20_BASE1) + inning.getBatting_team().getTeamBadge() + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Main$AllGraphics$Side" + WhichSide + "$ImageBattingCard$Top$" + i + "$TextGrp$Data$Out"
						+ "$img_Text1*TEXTURE*IMAGE SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_TEXT1 : Constants.MT20_TEXT1) + inning.getBatting_team().getTeamBadge() + "\0", print_writers);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Main$AllGraphics$Side" + WhichSide + "$ImageBattingCard$Top$" + i + "$TextGrp$Data$Stats"
						+ "$img_Base1*TEXTURE*IMAGE SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_BASE1 : Constants.MT20_BASE1) + inning.getBatting_team().getTeamBadge() + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Main$AllGraphics$Side" + WhichSide + "$ImageBattingCard$Top$" + i + "$TextGrp$Data$Stats"
						+ "$img_Text1*TEXTURE*IMAGE SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_TEXT1 : Constants.MT20_TEXT1) + inning.getBatting_team().getTeamBadge() + "\0", print_writers);
				
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Main$AllGraphics$Side" + WhichSide + "$ImageBattingCard$Bottom$" + i + "$TextGrp$Data$Out"
						+ "$img_Base1*TEXTURE*IMAGE SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_BASE1 : Constants.MT20_BASE1) + inning.getBatting_team().getTeamBadge() + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Main$AllGraphics$Side" + WhichSide + "$ImageBattingCard$Bottom$" + i + "$TextGrp$Data$Out"
						+ "$img_Text1*TEXTURE*IMAGE SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_TEXT1 : Constants.MT20_TEXT1) + inning.getBatting_team().getTeamBadge() + "\0", print_writers);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Main$AllGraphics$Side" + WhichSide + "$ImageBattingCard$Bottom$" + i + "$TextGrp$Data$Stats"
						+ "$img_Base1*TEXTURE*IMAGE SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_BASE1 : Constants.MT20_BASE1) + inning.getBatting_team().getTeamBadge() + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Main$AllGraphics$Side" + WhichSide + "$ImageBattingCard$Bottom$" + i + "$TextGrp$Data$Stats"
						+ "$img_Text1*TEXTURE*IMAGE SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_TEXT1 : Constants.MT20_TEXT1) + inning.getBatting_team().getTeamBadge() + "\0", print_writers);
				
			}
			break;
		case "Alt_F9":
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Main$TitleGrp$Side" + WhichSide + "$img_Base1*TEXTURE*IMAGE SET " + 
					(config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_BASE1 : Constants.MT20_BASE1) + inning.getBatting_team().getTeamBadge() + "\0", print_writers);
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Main$Footer$Side" + WhichSide + "$FooterStyle2$Extras$img_Base1*TEXTURE*IMAGE SET " + 
					(config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_BASE1 : Constants.MT20_BASE1) + inning.getBatting_team().getTeamBadge() + "\0", print_writers);
			
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Main$AllGraphics$Side" + WhichSide + "$TeamSingle$Rows$1$Title$Base"
					+ "$img_Base2*TEXTURE*IMAGE SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_BASE2 : Constants.MT20_BASE2) + inning.getBatting_team().getTeamBadge() + "\0", print_writers);
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Main$AllGraphics$Side" + WhichSide + "$TeamSingle$Rows$1$Title$Data"
					+ "$img_Text2*TEXTURE*IMAGE SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_TEXT2 : Constants.MT20_TEXT2) + inning.getBatting_team().getTeamBadge() 
					+ "\0", print_writers);
			
			
			for(int i=2;i<=12;i++) {
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Main$AllGraphics$Side" + WhichSide + "$TeamSingle$Rows$" + i + "$Name$Base"
						+ "$img_Base1*TEXTURE*IMAGE SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_BASE1 : Constants.MT20_BASE1) + inning.getBatting_team().getTeamBadge() + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Main$AllGraphics$Side" + WhichSide + "$TeamSingle$Rows$" + i + "$Name"
						+ "$img_Text1*TEXTURE*IMAGE SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_TEXT1 : Constants.MT20_TEXT1) + inning.getBatting_team().getTeamBadge() + "\0", print_writers);
				
			}
			break;
		case "Control_Alt_F1":
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Main$TitleGrp$Side" + WhichSide + "$img_Base1*TEXTURE*IMAGE SET " + 
					(config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_BASE1 : Constants.MT20_BASE1) : Constants.MT20_BASE1) + inning.getBatting_team().getTeamBadge() + "\0", print_writers);
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Main$Footer$Side" + WhichSide + "$FooterStyle1$Extras$img_Base1*TEXTURE*IMAGE SET " + 
					(config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_BASE1 : Constants.MT20_BASE1) + inning.getBatting_team().getTeamBadge() + "\0", print_writers);
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Main$Footer$Side" + WhichSide + "$FooterStyle1$Overs$img_Base1*TEXTURE*IMAGE SET " + 
					(config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_BASE1 : Constants.MT20_BASE1) + inning.getBatting_team().getTeamBadge() + "\0", print_writers);
			
			for(int i=1;i<=13;i++) {
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Main$AllGraphics$Side" + WhichSide + "$BattingCard$BatterGrp$" + i + "$LeftSplitBatData$"
						+ "Still_To_Bat$img_Base1*TEXTURE*IMAGE SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_BASE1 : Constants.MT20_BASE1) + inning.getBatting_team().getTeamBadge() + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Main$AllGraphics$Side" + WhichSide + "$BattingCard$BatterGrp$" + i + "$LeftSplitBatData$"
						+ "Out$img_Base1*TEXTURE*IMAGE SET " + Constants.TRI_SERIES_BASE1 + inning.getBatting_team().getTeamBadge() + "\0", print_writers);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Main$AllGraphics$Side" + WhichSide + "$BattingCard$BatterGrp$" + i + "$Highlight$"
						+ "img_Base1*TEXTURE*IMAGE SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_BASE1 : Constants.MT20_BASE1) + inning.getBatting_team().getTeamBadge() + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Main$AllGraphics$Side" + WhichSide + "$BattingCard$BatterGrp$" + i + "$Highlight$"
						+ "img_Base2*TEXTURE*IMAGE SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_BASE2 : Constants.MT20_BASE2) + inning.getBatting_team().getTeamBadge() + "\0", print_writers);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Main$AllGraphics$Side" + WhichSide + "$BattingCard$BowlerGrp$" + i + "$Players$"
						+ "img_Base1*TEXTURE*IMAGE SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_BASE1 : Constants.MT20_BASE1) + inning.getBowling_team().getTeamBadge() + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Main$AllGraphics$Side" + WhichSide + "$BattingCard$BowlerGrp$" + i + "$Players$"
						+ "img_Text1*TEXTURE*IMAGE SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_TEXT1 : Constants.MT20_TEXT1) + inning.getBowling_team().getTeamBadge() + "\0", print_writers);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Main$AllGraphics$Side" + WhichSide + "$BattingCard$BowlerGrp$" + i + "$FOW_Title$"
						+ "img_Base1*TEXTURE*IMAGE SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_BASE1 : Constants.MT20_BASE1) + inning.getBowling_team().getTeamBadge() + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Main$AllGraphics$Side" + WhichSide + "$BattingCard$BowlerGrp$" + i + "$FOW_Title$"
						+ "img_Text1*TEXTURE*IMAGE SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_TEXT1 : Constants.MT20_TEXT1) + inning.getBowling_team().getTeamBadge() + "\0", print_writers);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Main$AllGraphics$Side" + WhichSide + "$BattingCard$BowlerGrp$" + i + "$Wickets$"
						+ "img_Base1*TEXTURE*IMAGE SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_BASE1 : Constants.MT20_BASE1) + inning.getBowling_team().getTeamBadge() + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Main$AllGraphics$Side" + WhichSide + "$BattingCard$BowlerGrp$" + i + "$Wickets$"
						+ "img_Text1*TEXTURE*IMAGE SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_TEXT1 : Constants.MT20_TEXT1) + inning.getBowling_team().getTeamBadge() + "\0", print_writers);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Main$AllGraphics$Side" + WhichSide + "$BattingCard$BowlerGrp$" + i + "$Score$"
						+ "img_Base1*TEXTURE*IMAGE SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_BASE1 : Constants.MT20_BASE1) + inning.getBowling_team().getTeamBadge() + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Main$AllGraphics$Side" + WhichSide + "$BattingCard$BowlerGrp$" + i + "$Score$"
						+ "img_Text1*TEXTURE*IMAGE SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_TEXT1 : Constants.MT20_TEXT1) + inning.getBowling_team().getTeamBadge() + "\0", print_writers);
			}
			
			break;
		case "Alt_Shift_F1":
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Main$TitleGrp$Side" + WhichSide + "$img_Base1*TEXTURE*IMAGE SET " + 
					(config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_BASE1 : Constants.MT20_BASE1) + inning.getBatting_team().getTeamBadge() + "\0", print_writers);
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Main$Footer$Side" + WhichSide + "$FooterStyle1$Extras$img_Base1*TEXTURE*IMAGE SET " + 
					(config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_BASE1 : Constants.MT20_BASE1) + inning.getBatting_team().getTeamBadge() + "\0", print_writers);
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Main$Footer$Side" + WhichSide + "$FooterStyle1$Overs$img_Base1*TEXTURE*IMAGE SET " + 
					(config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_BASE1 : Constants.MT20_BASE1) + inning.getBatting_team().getTeamBadge() + "\0", print_writers);
			
			for(int i=1;i<=13;i++) {
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Main$AllGraphics$Side" + WhichSide + "$BattingCard$BatterGrp$" + i + "$LeftSplitBatData$"
						+ "Still_To_Bat$img_Base1*TEXTURE*IMAGE SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_BASE1 : Constants.MT20_BASE1) + inning.getBatting_team().getTeamBadge() + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Main$AllGraphics$Side" + WhichSide + "$BattingCard$BatterGrp$" + i + "$LeftSplitBatData$"
						+ "Out$img_Base1*TEXTURE*IMAGE SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_BASE1 : Constants.MT20_BASE1) + inning.getBatting_team().getTeamBadge() + "\0", print_writers);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Main$AllGraphics$Side" + WhichSide + "$BattingCard$BatterGrp$" + i + "$Highlight$"
						+ "img_Base1*TEXTURE*IMAGE SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_BASE1 : Constants.MT20_BASE1) + inning.getBatting_team().getTeamBadge() + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Main$AllGraphics$Side" + WhichSide + "$BattingCard$BatterGrp$" + i + "$Highlight$"
						+ "img_Base2*TEXTURE*IMAGE SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_BASE2 : Constants.MT20_BASE2) + inning.getBatting_team().getTeamBadge() + "\0", print_writers);
				
			}
			
			for(int i=1;i<=3;i++) {
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Main$AllGraphics$Side" + WhichSide + "$BattingCard$ContributionGrp$" + i + "$"
						+ "img_Base1*TEXTURE*IMAGE SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_BASE1 : Constants.MT20_BASE1) + inning.getBatting_team().getTeamBadge() + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Main$AllGraphics$Side" + WhichSide + "$BattingCard$ContributionGrp$" + i + "$"
						+ "img_Text1*TEXTURE*IMAGE SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_TEXT1 : Constants.MT20_TEXT1) + inning.getBatting_team().getTeamBadge() + "\0", print_writers);
				
			}
			break;
		case "Control_d": case "Control_e": case "Shift_P": case "Shift_Q":
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_Profile$Main$ShiftForImage$TitleGrp$Side" + WhichSide + "$img_Base1*TEXTURE*IMAGE SET " 
					+ (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_BASE1 : Constants.MT20_BASE1) + team.getTeamBadge() + "\0", print_writers);
			for(int i=3;i<=7;i++) {
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_Profile$Main$ShiftForImage$DataAll$Side" + WhichSide + "$Row" + i + "$Dehighlight"
						+ "$img_Base1*TEXTURE*IMAGE SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_BASE1 : Constants.MT20_BASE1) + team.getTeamBadge() + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_Profile$Main$ShiftForImage$DataAll$Side" + WhichSide + "$Row" + i + "$Dehighlight"
						+ "$img_Text1*TEXTURE*IMAGE SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_TEXT1 : Constants.MT20_TEXT1) + team.getTeamBadge() + "\0", print_writers);
			}
			
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_Profile$Main$ShiftForImage$DataAll$Side" + WhichSide + "$Row8"
					+ "$img_Base1*TEXTURE*IMAGE SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_BASE1 : Constants.MT20_BASE1) + team.getTeamBadge() + "\0", print_writers);
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_Profile$Main$ShiftForImage$DataAll$Side" + WhichSide + "$Row8"
					+ "$img_Text1*TEXTURE*IMAGE SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_TEXT1 : Constants.MT20_TEXT1) + team.getTeamBadge() + "\0", print_writers);
			
			break;
			
		case "Control_F11": case "Shift_F11":
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Main$TitleGrp$Side" + WhichSide + "$img_Base1*TEXTURE*IMAGE SET " + 
					(config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_BASE1 : Constants.MT20_BASE1) + "EVENT" + "\0", print_writers);
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Main$Footer$Side" + WhichSide + "$FooterStyle2$Extras$img_Base1*TEXTURE*IMAGE SET " + 
					(config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_BASE1 : Constants.MT20_BASE1) + "EVENT" + "\0", print_writers);
			
			for(int i=1;i<=4;i++) {
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Main$AllGraphics$Side" + WhichSide + "$Summary$Team1$Row_" + i + "$Batsman"
						+ "$img_Base1*TEXTURE*IMAGE SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_BASE1 : Constants.MT20_BASE1) + matchAllData.getMatch().getInning().get(0).getBatting_team().getTeamBadge() + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Main$AllGraphics$Side" + WhichSide + "$Summary$Team1$Row_" + i + "$Bowler"
						+ "$img_Base1*TEXTURE*IMAGE SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_BASE1 : Constants.MT20_BASE1) + matchAllData.getMatch().getInning().get(0).getBowling_team().getTeamBadge() + "\0", print_writers);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Main$AllGraphics$Side" + WhichSide + "$Summary$Team2$Row_" + i + "$Batsman"
						+ "$img_Base1*TEXTURE*IMAGE SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_BASE1 : Constants.MT20_BASE1) + matchAllData.getMatch().getInning().get(1).getBatting_team().getTeamBadge() + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Main$AllGraphics$Side" + WhichSide + "$Summary$Team2$Row_" + i + "$Bowler"
						+ "$img_Base1*TEXTURE*IMAGE SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_BASE1 : Constants.MT20_BASE1) + matchAllData.getMatch().getInning().get(1).getBowling_team().getTeamBadge() + "\0", print_writers);
			}
			break;
		case "Control_F7":
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Main$TitleGrp$Side" + WhichSide + "$img_Base1*TEXTURE*IMAGE SET " + 
					(config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_BASE1 : Constants.MT20_BASE1) + "EVENT" + "\0", print_writers);
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Main$Footer$Side" + WhichSide + "$FooterStyle2$Extras$img_Base1*TEXTURE*IMAGE SET " + 
					(config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_BASE1 : Constants.MT20_BASE1) + "EVENT" + "\0", print_writers);
			
			for(int i=1;i<=11;i++) {
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Main$AllGraphics$Side" + WhichSide + "$Teams$Team_1$" + i + "$NameGrp"
						+ "$img_Base1*TEXTURE*IMAGE SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_BASE1 : Constants.MT20_BASE1) + matchAllData.getSetup().getHomeTeam().getTeamBadge() + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Main$AllGraphics$Side" + WhichSide + "$Teams$Team_1$" + i + "$NameGrp"
						+ "$img_Text1*TEXTURE*IMAGE SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_TEXT1 : Constants.MT20_TEXT1) + matchAllData.getSetup().getHomeTeam().getTeamBadge() + "\0", print_writers);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Main$AllGraphics$Side" + WhichSide + "$Teams$Team_2$" + i + "$NameGrp"
						+ "$img_Base1*TEXTURE*IMAGE SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_BASE1 : Constants.MT20_BASE1) + matchAllData.getSetup().getAwayTeam().getTeamBadge() + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Main$AllGraphics$Side" + WhichSide + "$Teams$Team_2$" + i + "$NameGrp"
						+ "$img_Text1*TEXTURE*IMAGE SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_TEXT1 : Constants.MT20_TEXT1) + matchAllData.getSetup().getAwayTeam().getTeamBadge() + "\0", print_writers);
				
			}
			break;
		case "Control_Shift_F7":
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_LineUp_Image$Main$TitleGrp$Base$Side" + WhichSide + "$img_Base1*TEXTURE*IMAGE SET " + 
					(config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_BASE1 : Constants.MT20_BASE1) + team.getTeamBadge() + "\0", print_writers);
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_LineUp_Image$Main$TitleGrp$Text$Side" + WhichSide + "$img_Text1*TEXTURE*IMAGE SET " + 
					(config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_TEXT1 : Constants.MT20_TEXT1) + team.getTeamBadge() + "\0", print_writers);
			
			for(int i=1;i<=6;i++) {
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_LineUp_Image$Main$AllGraphics$Side" + WhichSide + "$1$Photo_" + i + "$Role"
						+ "$img_base1*TEXTURE*IMAGE SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_BASE1 : Constants.MT20_BASE1) + team.getTeamBadge() + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_LineUp_Image$Main$AllGraphics$Side" + WhichSide + "$1$Photo_" + i + "$Role"
						+ "$img_Text1*TEXTURE*IMAGE SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_TEXT1 : Constants.MT20_TEXT1) + team.getTeamBadge() + "\0", print_writers);
				
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_LineUp_Image$Main$AllGraphics$Side" + WhichSide + "$2$Photo_" + i + "$Role"
						+ "$img_base1*TEXTURE*IMAGE SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_BASE1 : Constants.MT20_BASE1) + team.getTeamBadge() + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_LineUp_Image$Main$AllGraphics$Side" + WhichSide + "$2$Photo_" + i + "$Role"
						+ "$img_Text1*TEXTURE*IMAGE SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_TEXT1 : Constants.MT20_TEXT1) + team.getTeamBadge() + "\0", print_writers);
			}
			break;	
		case "F2":
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Main$TitleGrp$Side" + WhichSide + "$img_Base1*TEXTURE*IMAGE SET " + 
					(config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_BASE1 : Constants.MT20_BASE1) + inning.getBowling_team().getTeamBadge() + "\0", print_writers);
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Main$Footer$Side" + WhichSide + "$FooterStyle1$Extras$img_Base1*TEXTURE*IMAGE SET " + 
					(config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_BASE1 : Constants.MT20_BASE1) + inning.getBowling_team().getTeamBadge() + "\0", print_writers);
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Main$Footer$Side" + WhichSide + "$FooterStyle1$Overs$img_Base1*TEXTURE*IMAGE SET " + 
					(config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_BASE1 : Constants.MT20_BASE1) + inning.getBowling_team().getTeamBadge() + "\0", print_writers);
			
			for(int i=1;i<=13;i++) {
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Main$AllGraphics$Side" + WhichSide + "$BowlingCard$BowlerGrp$" + i + "$LeftBallSplitData$"
						+ "Dehighlight$img_Base1*TEXTURE*IMAGE SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_BASE1 : Constants.MT20_BASE1) + inning.getBowling_team().getTeamBadge() + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Main$AllGraphics$Side" + WhichSide + "$BowlingCard$BowlerDetailsGrp$" + i + "$BowlerDetailsData$"
						+ "Title$img_Base1*TEXTURE*IMAGE SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_BASE1 : Constants.MT20_BASE1) + inning.getBowling_team().getTeamBadge() + "\0", print_writers);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Main$AllGraphics$Side" + WhichSide + "$BowlingCard$BowlerDetailsGrp$" + i + "$BowlerDetailsData$"
						+ "Values$img_Base1*TEXTURE*IMAGE SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_BASE1 : Constants.MT20_BASE1) + inning.getBowling_team().getTeamBadge() + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Main$AllGraphics$Side" + WhichSide + "$BowlingCard$BowlerDetailsGrp$" + i + "$BowlerDetailsData$"
						+ "FOW_Title$img_Base1*TEXTURE*IMAGE SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_BASE1 : Constants.MT20_BASE1) + inning.getBowling_team().getTeamBadge() + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Main$AllGraphics$Side" + WhichSide + "$BowlingCard$BowlerDetailsGrp$" + i + "$BowlerDetailsData$"
						+ "Wickets$img_Base1*TEXTURE*IMAGE SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_BASE1 : Constants.MT20_BASE1) + inning.getBowling_team().getTeamBadge() + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Main$AllGraphics$Side" + WhichSide + "$BowlingCard$BowlerDetailsGrp$" + i + "$BowlerDetailsData$"
						+ "Score$img_Base1*TEXTURE*IMAGE SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_BASE1 : Constants.MT20_BASE1) + inning.getBowling_team().getTeamBadge() + "\0", print_writers);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Main$AllGraphics$Side" + WhichSide + "$BowlingCard$BowlerGrp$" + i + "$Highlight$"
						+ "img_Base1*TEXTURE*IMAGE SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_BASE1 : Constants.MT20_BASE1) + inning.getBowling_team().getTeamBadge() + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Main$AllGraphics$Side" + WhichSide + "$BowlingCard$BowlerGrp$" + i + "$Highlight$"
						+ "img_Base2*TEXTURE*IMAGE SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_BASE2 : Constants.MT20_BASE2) + inning.getBowling_team().getTeamBadge() + "\0", print_writers);
			}
			break;
		}
		TimeUnit.MILLISECONDS.sleep(600);
	}
	public String populateHeader(List<PrintWriter> print_writers, int WhichSide, String whatToProcess, MatchAllData matchAllData, Inning inning, Configuration config) throws InterruptedException {
		
		switch (config.getBroadcaster()) {
		case Constants.AFG_SL_SERIES:
			break;
		default:
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Sponsor$Side" + WhichSide + "$Select*FUNCTION*Omo*vis_con SET 0\0", print_writers);
			break;
		}
		
		switch (config.getBroadcaster()) {
		case Constants.AFG_SL_SERIES:
			switch(whatToProcess) {
			case "Shift_F10":
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$LogoGrp$Side" + WhichSide + "$select_Logo"
						+ "*FUNCTION*Omo*vis_con SET " + (inning.getInningNumber() == 1 ? "1" : "0") + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$HeaderGrp$Header$Side" + WhichSide + "$select_HeaderStyle"
						+ "*FUNCTION*Omo*vis_con SET " + (inning.getInningNumber() == 1 ? "0" : "1") + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$HeaderGrp$SubHeader$Side" + WhichSide + "$select_SubHeaderStyle"
						+ "*FUNCTION*Omo*vis_con SET " + (inning.getInningNumber() == 1 ? "0" : "1") + "\0", print_writers);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$VerticalText$Side" + WhichSide + "$select_VerticalText"
						+ "*FUNCTION*Omo*vis_con SET 0\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$IconGrp$Side" + WhichSide + "$select_IconVisibility"
						+ "*FUNCTION*Omo*vis_con SET 1\0", print_writers);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$HeaderGrp$Header$Side" + WhichSide + (inning.getInningNumber() == 1 ? "$Style1" : "$Style2") 
						+ "$txt_Info*GEOM*TEXT SET " + matchAllData.getSetup().getMatchIdent() + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$HeaderGrp$SubHeader$Side" + WhichSide + (inning.getInningNumber() == 1 ? "$Style1" : "$Style2") 
						+ "$txt_SubHeader*GEOM*TEXT SET " + matchAllData.getSetup().getTournament() + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$HeaderGrp$Header$Side" + WhichSide + (inning.getInningNumber() == 1 ? "$Style1" : "$Style2") 
						+ "$txt_Header*GEOM*TEXT SET " + (inning.getInningNumber() == 1 ? inning.getBatting_team().getTeamName1() : "COMPARISON") + "\0", print_writers);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$IconGrp$IconAll$Side" + WhichSide + "$select_Icon"
						+ "*FUNCTION*Omo*vis_con SET 0\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$IconGrp$IconAll$Side" + WhichSide + "$select_Icon$Logo$img_Icon"
						+ "*TEXTURE*IMAGE SET " + Constants.AFG_SL_SERIES_LOGO + "TLogo" + "\0", print_writers);
				break;
			case "Control_F10":
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$LogoGrp$Side" + WhichSide + "$select_Logo"
						+ "*FUNCTION*Omo*vis_con SET 1\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$HeaderGrp$Header$Side" + WhichSide + "$select_HeaderStyle"
						+ "*FUNCTION*Omo*vis_con SET 0\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$HeaderGrp$SubHeader$Side" + WhichSide + "$select_SubHeaderStyle"
						+ "*FUNCTION*Omo*vis_con SET 0\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$VerticalText$Side" + WhichSide + "$select_VerticalText"
						+ "*FUNCTION*Omo*vis_con SET 0\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$IconGrp$Side" + WhichSide + "$select_IconVisibility"
						+ "*FUNCTION*Omo*vis_con SET 1\0", print_writers);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$HeaderGrp$Header$Side" + WhichSide + "$Style1$"
						+ "txt_Info*GEOM*TEXT SET " + matchAllData.getSetup().getMatchIdent() + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$HeaderGrp$SubHeader$Side" + WhichSide + "$Style1$"
						+ "txt_SubHeader*GEOM*TEXT SET " + matchAllData.getSetup().getTournament() + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$HeaderGrp$Header$Side" + WhichSide + "$Style1$"
						+ "txt_Header*GEOM*TEXT SET " + inning.getBatting_team().getTeamName1() + "\0", print_writers);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$IconGrp$IconAll$Side" + WhichSide + "$select_Icon"
						+ "*FUNCTION*Omo*vis_con SET 1\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$IconGrp$IconAll$Side" + WhichSide + "$select_Icon$Icon$img_Icon"
						+ "*TEXTURE*IMAGE SET " + Constants.AFG_SL_SERIES_ICONS + "Bat_Icon" + "\0", print_writers);
				break;
			case "Alt_F11":
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$LogoGrp$Side" + WhichSide + "$select_Logo"
						+ "*FUNCTION*Omo*vis_con SET 0\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$HeaderGrp$Header$Side" + WhichSide + "$select_HeaderStyle"
						+ "*FUNCTION*Omo*vis_con SET 1\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$HeaderGrp$SubHeader$Side" + WhichSide + "$select_SubHeaderStyle"
						+ "*FUNCTION*Omo*vis_con SET 1\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$VerticalText$Side" + WhichSide + "$select_VerticalText"
						+ "*FUNCTION*Omo*vis_con SET 0\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$IconGrp$Side" + WhichSide + "$select_IconVisibility"
						+ "*FUNCTION*Omo*vis_con SET 1\0", print_writers);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$HeaderGrp$Header$Side" + WhichSide + "$Style2$"
						+ "txt_Info*GEOM*TEXT SET " + matchAllData.getSetup().getMatchIdent() + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$HeaderGrp$SubHeader$Side" + WhichSide + "$Style2$"
						+ "txt_SubHeader*GEOM*TEXT SET " + matchAllData.getSetup().getTournament() + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$HeaderGrp$Header$Side" + WhichSide + "$Style2$"
						+ "txt_Header*GEOM*TEXT SET " + "COMPARISON" + "\0", print_writers);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$IconGrp$IconAll$Side" + WhichSide + "$select_Icon"
						+ "*FUNCTION*Omo*vis_con SET 0\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$IconGrp$IconAll$Side" + WhichSide + "$select_Icon$Logo$img_Icon"
						+ "*TEXTURE*IMAGE SET " + Constants.AFG_SL_SERIES_LOGO + "TLogo" + "\0", print_writers);
				break;
			case "Control_F11": case "Shift_F11":
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$LogoGrp$Side" + WhichSide + "$select_Logo"
						+ "*FUNCTION*Omo*vis_con SET 0\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$HeaderGrp$Header$Side" + WhichSide + "$select_HeaderStyle"
						+ "*FUNCTION*Omo*vis_con SET 1\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$HeaderGrp$SubHeader$Side" + WhichSide + "$select_SubHeaderStyle"
						+ "*FUNCTION*Omo*vis_con SET 1\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$VerticalText$Side" + WhichSide + "$select_VerticalText"
						+ "*FUNCTION*Omo*vis_con SET 0\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$IconGrp$Side" + WhichSide + "$select_IconVisibility"
						+ "*FUNCTION*Omo*vis_con SET 1\0", print_writers);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$HeaderGrp$Header$Side" + WhichSide + "$Style2$txt_Info"
						+ "*GEOM*TEXT SET " + matchAllData.getSetup().getMatchIdent() + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$HeaderGrp$SubHeader$Side" + WhichSide + "$Style2$txt_SubHeader"
						+ "*GEOM*TEXT SET " + matchAllData.getSetup().getTournament() + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$HeaderGrp$Header$Side" + WhichSide + "$Style2$txt_Header"
						+ "*GEOM*TEXT SET SUMMARY\0", print_writers);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$IconGrp$IconAll$Side" + WhichSide + "$select_Icon"
						+ "*FUNCTION*Omo*vis_con SET 0\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$IconGrp$IconAll$Side" + WhichSide + "$select_Icon$Logo$img_Icon"
						+ "*TEXTURE*IMAGE SET " + Constants.AFG_SL_SERIES_LOGO + "TLogo" + "\0", print_writers);
				break;
			case "Control_F7":
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$LogoGrp$Side" + WhichSide + "$select_Logo"
						+ "*FUNCTION*Omo*vis_con SET 0\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$HeaderGrp$Header$Side" + WhichSide + "$select_HeaderStyle"
						+ "*FUNCTION*Omo*vis_con SET 1\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$HeaderGrp$SubHeader$Side" + WhichSide + "$select_SubHeaderStyle"
						+ "*FUNCTION*Omo*vis_con SET 1\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$VerticalText$Side" + WhichSide + "$select_VerticalText"
						+ "*FUNCTION*Omo*vis_con SET 0\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$IconGrp$Side" + WhichSide + "$select_IconVisibility"
						+ "*FUNCTION*Omo*vis_con SET 1\0", print_writers);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$HeaderGrp$Header$Side" + WhichSide + "$Style2$txt_Info"
						+ "*GEOM*TEXT SET " + matchAllData.getSetup().getMatchIdent() + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$HeaderGrp$SubHeader$Side" + WhichSide + "$Style2$txt_SubHeader"
						+ "*GEOM*TEXT SET " + matchAllData.getSetup().getTournament() + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$HeaderGrp$Header$Side" + WhichSide + "$Style2$txt_Header"
						+ "*GEOM*TEXT SET TEAMS\0", print_writers);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$IconGrp$IconAll$Side" + WhichSide + "$select_Icon"
						+ "*FUNCTION*Omo*vis_con SET 0\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$IconGrp$IconAll$Side" + WhichSide + "$select_Icon$Logo$img_Icon"
						+ "*TEXTURE*IMAGE SET " + Constants.AFG_SL_SERIES_LOGO + "TLogo" + "\0", print_writers);
				break;
			case "Control_d": case "Control_e": case "Shift_P": case "Shift_Q":
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_Profile$Main$Side" + WhichSide + "$SubHeader$txt_Sub_Header*GEOM*TEXT SET " 
						+ team.getTeamName1() + "\0", print_writers);
				
				if(WhichProfile.equalsIgnoreCase("DT20")) {
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_Profile$Main$Side" + WhichSide + "$Info$txt_Age*GEOM*TEXT SET " 
							+ "T20 CAREER" + "\0", print_writers);
				}else if(WhichProfile.equalsIgnoreCase("IT20")){
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_Profile$Main$Side" + WhichSide + "$Info$txt_Age*GEOM*TEXT SET " 
							+ "T20I CAREER" + "\0", print_writers);
				}else if(WhichProfile.equalsIgnoreCase("ODI")) {
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_Profile$Main$Side" + WhichSide + "$Info$txt_Age*GEOM*TEXT SET " 
							+ "ODI CAREER" + "\0", print_writers);
				}else if(WhichProfile.equalsIgnoreCase("Test")){
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_Profile$Main$Side" + WhichSide + "$Info$txt_Age*GEOM*TEXT SET " 
							+ "TEST CAREER" + "\0", print_writers);
				}else if(WhichProfile.equalsIgnoreCase("LIST A")){
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_Profile$Main$Side" + WhichSide + "$Info$txt_Age*GEOM*TEXT SET " 
							+ "LIST A CAREER" + "\0", print_writers);
				}else if(WhichProfile.equalsIgnoreCase("THIS SERIES")) {
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_Profile$Main$Side" + WhichSide + "$Info$txt_Age*GEOM*TEXT SET " 
							+ "THIS SERIES" + "\0", print_writers);
				}
				break;
			
			case "F1": case "F2": case "Shift_T": case "Control_Alt_F1": case "Alt_Shift_F1": case "F4": case "Shift_K": case "Control_F1":
			case "Control_Shift_F1":
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$LogoGrp$Side" + WhichSide + "$select_Logo"
						+ "*FUNCTION*Omo*vis_con SET 1\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$HeaderGrp$Header$Side" + WhichSide + "$select_HeaderStyle"
						+ "*FUNCTION*Omo*vis_con SET 0\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$HeaderGrp$SubHeader$Side" + WhichSide + "$select_SubHeaderStyle"
						+ "*FUNCTION*Omo*vis_con SET 0\0", print_writers);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$HeaderGrp$Header$Side" + WhichSide + "$Style1$txt_Info"
						+ "*GEOM*TEXT SET " + matchAllData.getSetup().getMatchIdent() + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$HeaderGrp$SubHeader$Side" + WhichSide + "$Style1$txt_SubHeader"
						+ "*GEOM*TEXT SET " + matchAllData.getSetup().getTournament() + "\0", print_writers);
				
				switch(whatToProcess) {
				case "F1": case "Control_Alt_F1": case "Alt_Shift_F1": case "F4": case "Shift_K": case "Control_F1": case "Control_Shift_F1":
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$VerticalText$Side" + WhichSide + "$select_VerticalText"
							+ "*FUNCTION*Omo*vis_con SET 1\0", print_writers);
					switch(whatToProcess) {
					case "Shift_K": case "Control_Shift_F1":
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$IconGrp$Side" + WhichSide + "$select_IconVisibility"
								+ "*FUNCTION*Omo*vis_con SET 0\0", print_writers);
						break;
					default:
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$IconGrp$Side" + WhichSide + "$select_IconVisibility"
								+ "*FUNCTION*Omo*vis_con SET 1\0", print_writers);
						break;
					}
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$HeaderGrp$Header$Side" + WhichSide + "$Style1$txt_Header"
							+ "*GEOM*TEXT SET " + inning.getBatting_team().getTeamName1() + "\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$VerticalText$Side" + WhichSide + "$txt_ShortName"
							+ "*GEOM*TEXT SET " + inning.getBatting_team().getTeamName4() + "\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$VerticalText$Side" + WhichSide + "$ShortNameOutline"
							+ "*GEOM*TEXT SET " + inning.getBatting_team().getTeamName4() + "\0", print_writers);
					
					switch(whatToProcess) {
					case "Control_Alt_F1":
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$IconGrp$IconAll$Side" + WhichSide + "$select_Icon"
								+ "*FUNCTION*Omo*vis_con SET 0\0", print_writers);
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$IconGrp$IconAll$Side" + WhichSide + "$select_Icon$Logo$img_Icon"
								+ "*TEXTURE*IMAGE SET " + Constants.AFG_SL_SERIES_LOGO + "TLogo" + "\0", print_writers);
						break;
					default:
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$IconGrp$IconAll$Side" + WhichSide + "$select_Icon"
								+ "*FUNCTION*Omo*vis_con SET 1\0", print_writers);
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$IconGrp$IconAll$Side" + WhichSide + "$select_Icon$Icon$img_Icon"
								+ "*TEXTURE*IMAGE SET " + Constants.AFG_SL_SERIES_ICONS + "Bat_Icon" + "\0", print_writers);
						break;
					}
					break;
				case "F2":
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$VerticalText$Side" + WhichSide + "$select_VerticalText"
							+ "*FUNCTION*Omo*vis_con SET 1\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$IconGrp$Side" + WhichSide + "$select_IconVisibility"
							+ "*FUNCTION*Omo*vis_con SET 1\0", print_writers);
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$HeaderGrp$Header$Side" + WhichSide + "$Style1$txt_Header"
							+ "*GEOM*TEXT SET " + inning.getBowling_team().getTeamName1() + "\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$VerticalText$Side" + WhichSide + "$txt_ShortName"
							+ "*GEOM*TEXT SET " + inning.getBowling_team().getTeamName4() + "\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$VerticalText$Side" + WhichSide + "$ShortNameOutline"
							+ "*GEOM*TEXT SET " + inning.getBowling_team().getTeamName4() + "\0", print_writers);
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$IconGrp$IconAll$Side" + WhichSide + "$select_Icon"
							+ "*FUNCTION*Omo*vis_con SET 1\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$IconGrp$IconAll$Side" + WhichSide + "$select_Icon$Icon$img_Icon"
							+ "*TEXTURE*IMAGE SET " + Constants.AFG_SL_SERIES_ICONS + "Bowler" + "\0", print_writers);
					break;
				case "Shift_T":
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$VerticalText$Side" + WhichSide + "$select_VerticalText"
							+ "*FUNCTION*Omo*vis_con SET 1\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$IconGrp$Side" + WhichSide + "$select_IconVisibility"
							+ "*FUNCTION*Omo*vis_con SET 1\0", print_writers);
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$HeaderGrp$Header$Side" + WhichSide + "$Style1$txt_Header"
							+ "*GEOM*TEXT SET " + team.getTeamName1() + "\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$VerticalText$Side" + WhichSide + "$txt_ShortName"
							+ "*GEOM*TEXT SET " + team.getTeamName4() + "\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$VerticalText$Side" + WhichSide + "$ShortNameOutline"
							+ "*GEOM*TEXT SET " + team.getTeamName4() + "\0", print_writers);
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$IconGrp$IconAll$Side" + WhichSide + "$select_Icon"
							+ "*FUNCTION*Omo*vis_con SET 0\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$IconGrp$IconAll$Side" + WhichSide + "$select_Icon$Logo$img_Icon"
							+ "*TEXTURE*IMAGE SET " + Constants.AFG_SL_SERIES_LOGO + "TLogo" + "\0", print_writers);
					break;
					
				}
				break;
			}
			logosAndBaseColor(print_writers, WhichSide, whatToProcess, matchAllData, inning, config);
			break;
		case Constants.BAN_AFG_SERIES: case Constants.ACC:
			switch(whatToProcess) {
			case "z": case "x": case "c": case "v": case "Control_z": case "Control_x": case "Control_Shift_Z": case "Control_Shift_Y":
			case "Alt_Shift_W":
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$TopScorer"
						+ "$TopHeaderGrp$txt_SubHead2*GEOM*TEXT SET \0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$TopScorer"
						+ "$TopHeaderGrp$txt_TeamName*GEOM*TEXT SET " + matchAllData.getSetup().getTournament() + "\0", print_writers);
				
				switch(whatToProcess) {
				case "z":
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$TopScorer"
							+ "$TopHeaderGrp$txt_SubHead1*GEOM*TEXT SET MOST RUNS\0", print_writers);
					break;
				case "x":
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$TopScorer"
							+ "$TopHeaderGrp$txt_SubHead1*GEOM*TEXT SET MOST WICKETS\0", print_writers);
					break;
				case "c":
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$TopScorer"
							+ "$TopHeaderGrp$txt_SubHead1*GEOM*TEXT SET MOST FOURS\0", print_writers);
					break;
				case "v":
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$TopScorer"
							+ "$TopHeaderGrp$txt_SubHead1*GEOM*TEXT SET MOST SIXES\0", print_writers);
					break;
				case "Control_z":
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$TopScorer"
							+ "$TopHeaderGrp$txt_SubHead1*GEOM*TEXT SET HIGHEST INDIVIDUAL SCORES\0", print_writers);
					break;
				case "Control_x":
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$TopScorer"
							+ "$TopHeaderGrp$txt_SubHead1*GEOM*TEXT SET BEST FIGURES\0", print_writers);
					break;
				case "Control_Shift_Z":
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$TopScorer"
							+ "$TopHeaderGrp$txt_SubHead1*GEOM*TEXT SET HIGHEST STRIKE RATE\0", print_writers);
					break;
				case "Control_Shift_Y":
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$TopScorer"
							+ "$TopHeaderGrp$txt_SubHead1*GEOM*TEXT SET BEST ECONOMY\0", print_writers);
					break;	
				}
				break;	
			case "Control_d": case "Control_e": case "Shift_P": case "Shift_Q":
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$Profile$BatHeaderGrp$Header"
						+ "$txt_SubHead1*GEOM*TEXT SET " + matchAllData.getSetup().getTournament() + "\0", print_writers);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$Profile$BatHeaderGrp$Bottom_Header"
						+ "$txt_TeamName*GEOM*TEXT SET " + team.getTeamName1() + "\0", print_writers);
				
				switch (config.getBroadcaster().toUpperCase()) {
				case Constants.BAN_AFG_SERIES:
					if(WhichProfile.equalsIgnoreCase("DT20")) {
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$Profile$BatHeaderGrp$SubHead"
								+ "$txt_SubHead2*GEOM*TEXT SET " + "T20 CAREER" + "\0", print_writers);
					}else if(WhichProfile.equalsIgnoreCase("IT20")){
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$Profile$BatHeaderGrp$SubHead"
								+ "$txt_SubHead2*GEOM*TEXT SET " + "T20I CAREER" + "\0", print_writers);
					}else if(WhichProfile.equalsIgnoreCase("ODI")) {
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$Profile$BatHeaderGrp$SubHead"
								+ "$txt_SubHead2*GEOM*TEXT SET " + "ODI CAREER" + "\0", print_writers);
					}else if(WhichProfile.equalsIgnoreCase("Test")){
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$Profile$BatHeaderGrp$SubHead"
								+ "$txt_SubHead2*GEOM*TEXT SET " + "TEST CAREER" + "\0", print_writers);
					}else if(WhichProfile.equalsIgnoreCase("LIST A")){
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$Profile$BatHeaderGrp$SubHead"
								+ "$txt_SubHead2*GEOM*TEXT SET " + "LIST A CAREER" + "\0", print_writers);
					}else if(WhichProfile.equalsIgnoreCase("THIS SERIES")) {
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$Profile$BatHeaderGrp$SubHead"
								+ "$txt_SubHead2*GEOM*TEXT SET " + "THIS SERIES" + "\0", print_writers);
					}
					break;

				default:
					if(WhichProfile.equalsIgnoreCase("DT20")) {
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$Profile$BatHeaderGrp$SubHead"
								+ "$txt_SubHead2*GEOM*TEXT SET " + "T20 CAREER | " + team.getTeamName1() + "\0", print_writers);
					}else if(WhichProfile.equalsIgnoreCase("IT20")){
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$Profile$BatHeaderGrp$SubHead"
								+ "$txt_SubHead2*GEOM*TEXT SET " + "T20I CAREER | " + team.getTeamName1() + "\0", print_writers);
					}else if(WhichProfile.equalsIgnoreCase("ODI")) {
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$Profile$BatHeaderGrp$SubHead"
								+ "$txt_SubHead2*GEOM*TEXT SET " + "ODI CAREER | " + team.getTeamName1() + "\0", print_writers);
					}else if(WhichProfile.equalsIgnoreCase("Test")){
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$Profile$BatHeaderGrp$SubHead"
								+ "$txt_SubHead2*GEOM*TEXT SET " + "TEST CAREER | " + team.getTeamName1() + "\0", print_writers);
					}else if(WhichProfile.equalsIgnoreCase("LIST A")){
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$Profile$BatHeaderGrp$SubHead"
								+ "$txt_SubHead2*GEOM*TEXT SET " + "LIST A CAREER | " + team.getTeamName1() + "\0", print_writers);
					}else if(WhichProfile.equalsIgnoreCase("THIS SERIES")) {
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$Profile$BatHeaderGrp$SubHead"
								+ "$txt_SubHead2*GEOM*TEXT SET " + "THIS TOURNAMENT | " + team.getTeamName1() + "\0", print_writers);
					}
					
					break;
				}
				break;
			case "Shift_F10":
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$RunWorm$WormHeaderGrp$SubHead"
						+ "$txt_SubHead2*GEOM*TEXT SET " + matchAllData.getSetup().getMatchIdent() + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$RunWorm$WormHeaderGrp$Header"
						+ "$txt_SubHead1*GEOM*TEXT SET " + matchAllData.getSetup().getTournament() + "\0", print_writers);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$RunWorm$WormHeaderGrp$Bottom_Header"
						+ "$txt_TeamName*GEOM*TEXT SET " + (inning.getInningNumber() == 1 ? "" : "COMPARISON") + "\0", print_writers);
				break;
			case "Control_F10":
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$Manhattan$ManhattanHeaderGrp$SubHead"
						+ "$txt_SubHead2*GEOM*TEXT SET " + matchAllData.getSetup().getMatchIdent() + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$Manhattan$ManhattanHeaderGrp$Header"
						+ "$txt_SubHead1*GEOM*TEXT SET " + matchAllData.getSetup().getTournament() + "\0", print_writers);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$Manhattan$ManhattanHeaderGrp$Bottom_Header"
						+ "$txt_TeamName*GEOM*TEXT SET " + inning.getBatting_team().getTeamName1() + "\0", print_writers);
				break;
			case "Control_p":
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$Standings$BatHeaderGrp$SubHead"
						+ "$txt_SubHead2*GEOM*TEXT SET " + WhichProfile + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$Standings$BatHeaderGrp$Header"
						+ "$txt_SubHead1*GEOM*TEXT SET " + matchAllData.getSetup().getTournament() + "\0", print_writers);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$Standings$BatHeaderGrp$Bottom_Header"
						+ "$txt_TeamName*GEOM*TEXT SET " + "STANDINGS" + "\0", print_writers);
				break;
			case "Shift_D":
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$Target$TargetHeaderGrp$HeaderGrp"
						+ "$txt_SubHead2*GEOM*TEXT SET \0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$Target$TargetHeaderGrp$HeaderGrp"
						+ "$txt_SubHead1*GEOM*TEXT SET " + matchAllData.getSetup().getMatchIdent() + "\0", print_writers);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$Target$TargetHeaderGrp$HeaderGrp"
						+ "$txt_TeamName*GEOM*TEXT SET " + matchAllData.getSetup().getTournament() + "\0", print_writers);
				break;
			case "Shift_T":
				containerName_1 = Players.stream().filter(player -> PlayerId.contains(player.getPlayerId())).map(Player::getTicker_name).collect(Collectors.joining(", "));
				String TossValue = CricketFunctions.generateTossResult(matchAllData, CricketUtil.SHORT, CricketUtil.FIELD, CricketUtil.SHORT, CricketUtil.CHOSE).toUpperCase();
				
				if(!containerName_1.trim().isEmpty()) {
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$Playing_XI_Image$BatTeamHeaderGrp$SubHead"
							+ "$txt_SubHead2*GEOM*TEXT SET " + matchAllData.getSetup().getMatchIdent() + "\nTOSS - " + TossValue.split("WON THE TOSS &")[0] + "CHOSE TO" 
							+ TossValue.split("WON THE TOSS &")[1] + "\0", print_writers);
				}else {
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$Playing_XI_Image$BatTeamHeaderGrp$SubHead"
							+ "$txt_SubHead2*GEOM*TEXT SET " + matchAllData.getSetup().getMatchIdent() + "\0", print_writers);
				}
			
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$Playing_XI_Image$BatTeamHeaderGrp$Header"
						+ "$txt_SubHead1*GEOM*TEXT SET " + matchAllData.getSetup().getTournament() + "\0", print_writers);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$Playing_XI_Image$BatTeamHeaderGrp$Bottom_Header"
						+ "$txt_TeamName*GEOM*TEXT SET " + team.getTeamName1() + "\0", print_writers);
				break;
			case "Shift_K":
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$CurrentPArt$Header$SubHead"
						+ "$txt_SubHead2*GEOM*TEXT SET " + CricketFunctions.ordinal(partnership.getPartnershipNumber()) + " WICKET PARTNERSHIP" + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$CurrentPArt$Header$Header"
						+ "$txt_SubHead1*GEOM*TEXT SET " + matchAllData.getSetup().getTournament() + " | " + matchAllData.getSetup().getMatchIdent() + "\0", print_writers);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$CurrentPArt$Header$Bottom_Header"
						+ "$txt_TeamName*GEOM*TEXT SET " + inning.getBatting_team().getTeamName1() + "\0", print_writers);
				break;
			case "Control_F7":
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$Lineup_Both$BatHeaderGrp$SubHead"
						+ "$txt_SubHead2*GEOM*TEXT SET " + matchAllData.getSetup().getMatchIdent() + "\nTEAMS" + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$Lineup_Both$BatHeaderGrp$Header"
						+ "$txt_SubHead1*GEOM*TEXT SET " + matchAllData.getSetup().getTournament() + "\0", print_writers);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$Lineup_Both$BatHeaderGrp$Bottom_Header_Home"
						+ "$txt_TeamName*GEOM*TEXT SET " + matchAllData.getSetup().getHomeTeam().getTeamName1() + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$Lineup_Both$BatHeaderGrp$Bottom_Header_Away"
						+ "$txt_TeamName*GEOM*TEXT SET " + matchAllData.getSetup().getAwayTeam().getTeamName1() + "\0", print_writers);
				break;

			case "Control_F11": case "Shift_F11":
				switch (whatToProcess) {
				case "Control_F11":
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Sponsor$Side" + WhichSide + "$Select"
							+ "*FUNCTION*Omo*vis_con SET 2\0", print_writers);
					break;
				}
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$Summary$SummaryHeaderGrp$txt_TeamName*GEOM*TEXT SET " 
						+ matchAllData.getSetup().getTournament() + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$Summary$SummaryHeaderGrp$txt_SubHead1*GEOM*TEXT SET " 
						+ matchAllData.getSetup().getMatchIdent() + " | SUMMARY" + "\0", print_writers);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$Summary$SummaryHeaderGrp$txt_SubHead2*GEOM*TEXT SET \0", print_writers);
				break;
			case "F4":
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$PartnershipCard$PartnershipHeaderGrp$SubHead"
						+ "$txt_SubHead2*GEOM*TEXT SET " + matchAllData.getSetup().getMatchIdent() + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$PartnershipCard$PartnershipHeaderGrp$Header"
						+ "$txt_SubHead1*GEOM*TEXT SET " + matchAllData.getSetup().getTournament() + "\0", print_writers);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$PartnershipCard$PartnershipHeaderGrp$Bottom_Header"
						+ "$txt_TeamName*GEOM*TEXT SET " + inning.getBatting_team().getTeamName1() + "\0", print_writers);
				break;
			case "F1":
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Sponsor$Side" + WhichSide + "$Select"
						+ "*FUNCTION*Omo*vis_con SET 1\0", print_writers);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$BattingCard$BatHeaderGrp$SubHead"
						+ "$txt_SubHead2*GEOM*TEXT SET " + matchAllData.getSetup().getMatchIdent() + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$BattingCard$BatHeaderGrp$Header"
						+ "$txt_SubHead1*GEOM*TEXT SET " + matchAllData.getSetup().getTournament() + "\0", print_writers);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$BattingCard$BatHeaderGrp$Bottom_Header"
						+ "$txt_TeamName*GEOM*TEXT SET " + inning.getBatting_team().getTeamName1() + "\0", print_writers);
				break;
			case "F2":
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Sponsor$Side" + WhichSide + "$Select"
						+ "*FUNCTION*Omo*vis_con SET 2\0", print_writers);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$BowlingCard$BallHeaderGrp$SubHead"
						+ "$txt_SubHead2*GEOM*TEXT SET " + matchAllData.getSetup().getMatchIdent() + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$BowlingCard$BallHeaderGrp$Header"
						+ "$txt_SubHead1*GEOM*TEXT SET " + matchAllData.getSetup().getTournament() + "\0", print_writers);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$BowlingCard$BallHeaderGrp$Bottom_Header"
						+ "$txt_TeamName*GEOM*TEXT SET " + inning.getBowling_team().getTeamName1() + "\0", print_writers);
				break;
			}
			logosAndBaseColor(print_writers, WhichSide, whatToProcess, matchAllData, inning, config);
			break;
		case Constants.TRI_SERIES: case Constants.MT20:
			switch(whatToProcess) {
			case "z": case "x": case "c": case "v": case "Control_z": case "Control_x": case "Control_Shift_Z": case "Control_Shift_Y":
			case "Alt_Shift_W":
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$TopTitleGrp$txt_Title"
						+ "*GEOM*TEXT SET " + matchAllData.getSetup().getTournament() + "\0", print_writers);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$TitleGrp"
						+ "$select_TitleType*FUNCTION*Omo*vis_con SET 0\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$TitleGrp$select_TitleType$Text"
						+ "$Side" + WhichSide + "$select_HeaderStyle*FUNCTION*Omo*vis_con SET 1\0", print_writers);
				
				switch(whatToProcess) {
				case "z":
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$TitleGrp$Text"
							+ "$Side" + WhichSide + "$Style2$txt_Title*GEOM*TEXT SET MOST RUNS\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle"
							+ "$Side" + WhichSide + "$LeaderBoard$SubTitle$txt_SubTitle*GEOM*TEXT SET \0", print_writers);
					break;
				case "x":
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$TitleGrp$Text"
							+ "$Side" + WhichSide + "$Style2$txt_Title*GEOM*TEXT SET MOST WICKETS\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle"
							+ "$Side" + WhichSide + "$LeaderBoard$SubTitle$txt_SubTitle*GEOM*TEXT SET \0", print_writers);
					break;
				case "c":
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$TitleGrp$Text"
							+ "$Side" + WhichSide + "$Style2$txt_Title*GEOM*TEXT SET MOST FOURS\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle"
							+ "$Side" + WhichSide + "$LeaderBoard$SubTitle$txt_SubTitle*GEOM*TEXT SET \0", print_writers);
					break;
				case "v":
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$TitleGrp$Text"
							+ "$Side" + WhichSide + "$Style2$txt_Title*GEOM*TEXT SET MOST SIXES\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle"
							+ "$Side" + WhichSide + "$LeaderBoard$SubTitle$txt_SubTitle*GEOM*TEXT SET \0", print_writers);
					break;
				case "Control_z":
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$TitleGrp$Text"
							+ "$Side" + WhichSide + "$Style2$txt_Title*GEOM*TEXT SET HIGHEST INDIVIDUAL SCORES\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle"
							+ "$Side" + WhichSide + "$LeaderBoard$SubTitle$txt_SubTitle*GEOM*TEXT SET \0", print_writers);
					break;
				case "Control_x":
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$TitleGrp$Text"
							+ "$Side" + WhichSide + "$Style2$txt_Title*GEOM*TEXT SET BEST FIGURES\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle"
							+ "$Side" + WhichSide + "$LeaderBoard$SubTitle$txt_SubTitle*GEOM*TEXT SET \0", print_writers);
					break;
				case "Control_Shift_Z":
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$TitleGrp$Text"
							+ "$Side" + WhichSide + "$Style2$txt_Title*GEOM*TEXT SET HIGHEST STRIKE RATE\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle"
							+ "$Side" + WhichSide + "$LeaderBoard$SubTitle$txt_SubTitle*GEOM*TEXT SET MINIMUM 30 BALLS FACED\0", print_writers);
					break;
				case "Control_Shift_Y":
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$TitleGrp$Text"
							+ "$Side" + WhichSide + "$Style2$txt_Title*GEOM*TEXT SET BEST ECONOMY\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle"
							+ "$Side" + WhichSide + "$LeaderBoard$SubTitle$txt_SubTitle*GEOM*TEXT SET MINIMUM 5 OVERS BOWLED\0", print_writers);
					break;	
				}
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Big_Logo$Side" + WhichSide + 
						"$img_Logos_BW*TEXTURE*IMAGE SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_LOGO_BW : Constants.MT20_LOGO_BW) + "EVENT" + "\0", print_writers);
				
				break;
			case "Control_d": case "Control_e": case "Shift_P": case "Shift_Q":
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_Profile$Main$TitleGrp$Title$Side" + WhichSide + 
						"$txt_FirstName*GEOM*TEXT SET " + player.getFirstname() + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_Profile$Main$TitleGrp$Title$Side" + WhichSide + 
						"$txt_lastName*GEOM*TEXT SET " + (player.getSurname() != null ? player.getSurname() : "") + "\0", print_writers);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_Profile$Main$EventLogo$Event_Logo*TEXTURE*IMAGE SET " + 
						(config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_LOGO : Constants.MT20_LOGO) + "EVENT" + "\0", print_writers);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_Profile$TopTitleGrp$txt_Title"
						+ "*GEOM*TEXT SET " + matchAllData.getSetup().getTournament() + "\0", print_writers);
				break;
			case "Control_F7": case "Control_F11": case "Alt_F11": case "Shift_F11": case "Control_p":
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$TopTitleGrp$txt_Title"
						+ "*GEOM*TEXT SET " + matchAllData.getSetup().getTournament() + "\0", print_writers);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$TitleGrp"
						+ "$select_TitleType*FUNCTION*Omo*vis_con SET 0\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$TitleGrp$select_TitleType$Text"
						+ "$Side" + WhichSide + "$select_HeaderStyle*FUNCTION*Omo*vis_con SET 1\0", print_writers);
				
				switch(whatToProcess) {
				case "Control_F7":
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$TitleGrp$Text"
							+ "$Side" + WhichSide + "$Style2$txt_Title*GEOM*TEXT SET TEAMS\0", print_writers);
					break;
				case "Control_F11": case "Shift_F11":
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$TitleGrp$Text"
							+ "$Side" + WhichSide + "$Style2$txt_Title*GEOM*TEXT SET SUMMARY\0", print_writers);
					break;
				case "Alt_F11":
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$TitleGrp$Text"
							+ "$Side" + WhichSide + "$Style2$txt_Title*GEOM*TEXT SET COMPARISON\0", print_writers);
					break;
				case "Control_p":
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$TitleGrp$Text"
							+ "$Side" + WhichSide + "$Style2$txt_Title*GEOM*TEXT SET POINTS TABLE\0", print_writers);
					break;	
				}
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Main$EventLogo$Event_Logo*TEXTURE*IMAGE SET " + 
						(config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_LOGO : Constants.MT20_LOGO) + "EVENT" + "\0", print_writers);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$SubTitleGrp$SubTitle"
						+ "$Side" + WhichSide + "$txt_SubTitle*GEOM*TEXT SET " + matchAllData.getSetup().getMatchIdent() + "\0", print_writers);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$LogoGrp$Logo"
						+ "$Side" + WhichSide + "$img_Logo*TEXTURE*IMAGE SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_LOGO : Constants.MT20_LOGO) + "EVENT" + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Big_Logo$Side" + WhichSide + 
						"$img_Logos_BW*TEXTURE*IMAGE SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_LOGO_BW : Constants.MT20_LOGO_BW) + "EVENT" + "\0", print_writers);
				break;
			case "Shift_F10":
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Main$EventLogo$Event_Logo*TEXTURE*IMAGE SET " + 
						(config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_LOGO : Constants.MT20_LOGO) + "EVENT" + "\0", print_writers);
				
				if(inning.getInningNumber() == 1) {
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$TopTitleGrp$txt_Title"
							+ "*GEOM*TEXT SET " + matchAllData.getSetup().getTournament() + "\0", print_writers);
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$TitleGrp"
							+ "$select_TitleType*FUNCTION*Omo*vis_con SET 0\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$TitleGrp$select_TitleType$Text"
							+ "$Side" + WhichSide + "$select_HeaderStyle*FUNCTION*Omo*vis_con SET 0\0", print_writers);
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$TitleGrp$Text"
							+ "$Side" + WhichSide + "$Style1$txt_Title1*GEOM*TEXT SET " + inning.getBatting_team().getTeamName1() + "\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$TitleGrp$Text"
							+ "$Side" + WhichSide + "$Style1$txt_Title2*GEOM*TEXT SET v " + inning.getBowling_team().getTeamName1() + "\0", print_writers);
			
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$SubTitleGrp$SubTitle"
							+ "$Side" + WhichSide + "$txt_SubTitle*GEOM*TEXT SET " + matchAllData.getSetup().getMatchIdent() + "\0", print_writers);
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$LogoGrp$Logo"
							+ "$Side" + WhichSide + "$img_Logo*TEXTURE*IMAGE SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_LOGO : Constants.MT20_LOGO) + inning.getBatting_team().getTeamBadge() + "\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Big_Logo"
							+ "$Side" + WhichSide + "$img_Logos_BW*TEXTURE*IMAGE SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_LOGO_BW : Constants.MT20_LOGO_BW) + inning.getBatting_team().getTeamBadge() + "\0", print_writers);
				}else if(inning.getInningNumber() == 2) {
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$TopTitleGrp$txt_Title"
							+ "*GEOM*TEXT SET " + matchAllData.getSetup().getTournament() + "\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$TitleGrp"
							+ "$select_TitleType*FUNCTION*Omo*vis_con SET 0\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$TitleGrp$select_TitleType$Text"
							+ "$Side" + WhichSide + "$select_HeaderStyle*FUNCTION*Omo*vis_con SET 1\0", print_writers);
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$TitleGrp$Text"
							+ "$Side" + WhichSide + "$Style2$txt_Title*GEOM*TEXT SET COMPARISON\0", print_writers);
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$SubTitleGrp$SubTitle"
							+ "$Side" + WhichSide + "$txt_SubTitle*GEOM*TEXT SET " + matchAllData.getSetup().getMatchIdent() + "\0", print_writers);
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$LogoGrp$Logo"
							+ "$Side" + WhichSide + "$img_Logo*TEXTURE*IMAGE SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_LOGO : Constants.MT20_LOGO) + "EVENT" + "\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Big_Logo$Side" + WhichSide + 
							"$img_Logos_BW*TEXTURE*IMAGE SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_LOGO_BW : Constants.MT20_LOGO_BW) + "EVENT" + "\0", print_writers);
				}
				break;
				
			case "Control_Alt_F1":
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Main$EventLogo$Event_Logo*TEXTURE*IMAGE SET " + 
						(config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_LOGO : Constants.MT20_LOGO) + "EVENT" + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$TopTitleGrp$txt_Title"
						+ "*GEOM*TEXT SET " + matchAllData.getSetup().getTournament() + "\0", print_writers);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$TitleGrp"
						+ "$select_TitleType*FUNCTION*Omo*vis_con SET 0\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$TitleGrp$select_TitleType$Text"
						+ "$Side" + WhichSide + "$select_HeaderStyle*FUNCTION*Omo*vis_con SET 0\0", print_writers);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$TitleGrp$Text"
						+ "$Side" + WhichSide + "$Style1$txt_Title1*GEOM*TEXT SET " + inning.getBatting_team().getTeamName1() + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$TitleGrp$Text"
						+ "$Side" + WhichSide + "$Style1$txt_Title2*GEOM*TEXT SET v " + inning.getBowling_team().getTeamName1() + "\0", print_writers);
		
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$SubTitleGrp$SubTitle"
						+ "$Side" + WhichSide + "$txt_SubTitle*GEOM*TEXT SET " + matchAllData.getSetup().getMatchIdent() + "\0", print_writers);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$LogoGrp$Logo"
						+ "$Side" + WhichSide + "$img_Logo*TEXTURE*IMAGE SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_LOGO : Constants.MT20_LOGO) + "EVENT" + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Big_Logo"
						+ "$Side" + WhichSide + "$img_Logos_BW*TEXTURE*IMAGE SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_LOGO_BW : Constants.MT20_LOGO_BW) + "EVENT" + "\0", print_writers);
				break;
			
			case "Shift_Control_F1": case "Shift_Control_F2":
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$TitleGrp$Text"
					+ "$Side" + WhichSide + "$Style1$txt_Title1*GEOM*TEXT SET " + inning.getBatting_team().getTeamName1() + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$TitleGrp$Text"
					+ "$Side" + WhichSide + "$Style1$txt_Title2*GEOM*TEXT SET v " + inning.getBowling_team().getTeamName1() + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Main$EventLogo$Event_Logo*TEXTURE*IMAGE SET " + 
					(config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_LOGO : Constants.MT20_LOGO) + "EVENT" + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$TopTitleGrp$txt_Title"
					+ "*GEOM*TEXT SET " + matchAllData.getSetup().getTournament() + "\0", print_writers);
				break;
				
			case "F1": case "F4": case "Shift_K": case "Control_F10": case "Alt_F9": case "Control_F1": case "Alt_Shift_F1":
			case "Control_Shift_F4":
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$TitleGrp$Text"
						+ "$Side" + WhichSide + "$Style1$txt_Title1*GEOM*TEXT SET " + inning.getBatting_team().getTeamName1() + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$TitleGrp$Text"
						+ "$Side" + WhichSide + "$Style1$txt_Title2*GEOM*TEXT SET v " + inning.getBowling_team().getTeamName1() + "\0", print_writers);
		
				TimeUnit.MILLISECONDS.sleep(600);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Main$EventLogo$Event_Logo*TEXTURE*IMAGE SET " + 
						(config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_LOGO : Constants.MT20_LOGO) + "EVENT" + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$TopTitleGrp$txt_Title"
						+ "*GEOM*TEXT SET " + matchAllData.getSetup().getTournament() + "\0", print_writers);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$TitleGrp"
						+ "$select_TitleType*FUNCTION*Omo*vis_con SET 0\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$TitleGrp$select_TitleType$Text"
						+ "$Side" + WhichSide + "$select_HeaderStyle*FUNCTION*Omo*vis_con SET 0\0", print_writers);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$SubTitleGrp$SubTitle"
						+ "$Side" + WhichSide + "$txt_SubTitle*GEOM*TEXT SET " + matchAllData.getSetup().getMatchIdent() + "\0", print_writers);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$LogoGrp$Logo"
						+ "$Side" + WhichSide + "$img_Logo*TEXTURE*IMAGE SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_LOGO : Constants.MT20_LOGO) + inning.getBatting_team().getTeamBadge() + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Big_Logo"
						+ "$Side" + WhichSide + "$img_Logos_BW*TEXTURE*IMAGE SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_LOGO_BW : Constants.MT20_LOGO_BW) + inning.getBatting_team().getTeamBadge() + "\0", print_writers);
				break;
			case "F2": case "Control_Shift_F5":
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$TitleGrp$Text"
						+ "$Side" + WhichSide + "$Style1$txt_Title1*GEOM*TEXT SET " + inning.getBowling_team().getTeamName1() + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$TitleGrp$Text"
						+ "$Side" + WhichSide + "$Style1$txt_Title2*GEOM*TEXT SET v " + inning.getBatting_team().getTeamName1() + "\0", print_writers);
		
				TimeUnit.MILLISECONDS.sleep(600);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Main$EventLogo$Event_Logo*TEXTURE*IMAGE SET " + 
						(config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_LOGO : Constants.MT20_LOGO) + "EVENT" + "\0", print_writers);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$TopTitleGrp$txt_Title"
						+ "*GEOM*TEXT SET " + matchAllData.getSetup().getTournament() + "\0", print_writers);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$TitleGrp"
						+ "$select_TitleType*FUNCTION*Omo*vis_con SET 0\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$TitleGrp$select_TitleType$Text"
						+ "$Side" + WhichSide + "$select_HeaderStyle*FUNCTION*Omo*vis_con SET 0\0", print_writers);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$SubTitleGrp$SubTitle"
						+ "$Side" + WhichSide + "$txt_SubTitle*GEOM*TEXT SET " + matchAllData.getSetup().getMatchIdent() + "\0", print_writers);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$LogoGrp$Logo"
						+ "$Side" + WhichSide + "$img_Logo*TEXTURE*IMAGE SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_LOGO : Constants.MT20_LOGO) + inning.getBowling_team().getTeamBadge() + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Big_Logo"
						+ "$Side" + WhichSide + "$img_Logos_BW*TEXTURE*IMAGE SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_LOGO_BW : Constants.MT20_LOGO_BW) + inning.getBowling_team().getTeamBadge() + "\0", print_writers);
				break;
			case "Control_Shift_F7":
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_LineUp_Image$Main$EventLogo$Event_Logo*TEXTURE*IMAGE SET " + 
						(config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_LOGO : Constants.MT20_LOGO) + "EVENT" + "\0", print_writers);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_LineUp_Image$Shift_For_TopTitle$TopTitleGrp$txt_Title"
						+ "*GEOM*TEXT SET " + matchAllData.getSetup().getTournament() + "\0", print_writers);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$TitleGrp"
						+ "$select_TitleType*FUNCTION*Omo*vis_con SET 0\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$TitleGrp$select_TitleType$Text"
						+ "$Side" + WhichSide + "$select_HeaderStyle*FUNCTION*Omo*vis_con SET 0\0", print_writers);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_LineUp_Image$Shift_For_TopTitle$TitleGrp$Text"
						+ "$Side" + WhichSide + "$Style1$txt_Title1*GEOM*TEXT SET " + team.getTeamName1() + "\0", print_writers);
				
				if(team.getTeamId() == matchAllData.getSetup().getHomeTeamId()) {
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_LineUp_Image$Shift_For_TopTitle$TitleGrp$Text"
							+ "$Side" + WhichSide + "$Style1$txt_Title2*GEOM*TEXT SET v " + matchAllData.getSetup().getAwayTeam().getTeamName1() + "\0", print_writers);
				}else {
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_LineUp_Image$Shift_For_TopTitle$TitleGrp$Text"
							+ "$Side" + WhichSide + "$Style1$txt_Title2*GEOM*TEXT SET v " + matchAllData.getSetup().getHomeTeam().getTeamName1() + "\0", print_writers);
				}
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_LineUp_Image$Shift_For_TopTitle$SubTitleGrp$SubTitle"
						+ "$Side" + WhichSide + "$txt_SubTitle*GEOM*TEXT SET " + matchAllData.getSetup().getMatchIdent() + "\0", print_writers);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_LineUp_Image$Shift_For_TopTitle$LogoGrp$Logo"
						+ "$Side" + WhichSide + "$img_Logo*TEXTURE*IMAGE SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_LOGO : Constants.MT20_LOGO) + team.getTeamBadge() + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_LineUp_Image$Big_Logo"
						+ "$Side" + WhichSide + "$img_Logos_BW*TEXTURE*IMAGE SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_LOGO_BW : Constants.MT20_LOGO_BW) + team.getTeamBadge() + "\0", print_writers);
				break;
			}
			teamColor(print_writers, WhichSide, whatToProcess, matchAllData, inning,config);
			logosAndBaseColor(print_writers, WhichSide, whatToProcess, matchAllData, inning, config);
			break;
		}
		return Constants.OK;
	}
	public String populateTeamLineUpFooter(List<PrintWriter> print_writers, int WhichSide, String whichGraphicsToProcess, 
			Configuration config, String whatToProcess, MatchAllData matchAllData) {
		System.out.println("populateTeamLineUpFooter: WhichSide = " + WhichSide + 
			" : whichGraphicsToProcess = " + whichGraphicsToProcess + " : whatToProcess = " + whatToProcess 
			+ " : config.getBroadcaster() = " + config.getBroadcaster());
		switch (config.getBroadcaster()) {
		case Constants.TRI_SERIES: case Constants.MT20: 
			switch(whichGraphicsToProcess) {
			case "Control_Shift_F7":
			case "ANIMATE-OUT-SECOND_PLAYING":
				switch (whatToProcess) {
				case "SHOW-TOSS":
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_LineUp_Image$Shift_For_TopTitle$Footer$Text$Side" + WhichSide + "$"
						+ "txt_FooterText*GEOM*TEXT SET " + CricketFunctions.generateTossResult(matchAllData, CricketUtil.FULL, CricketUtil.FIELD, 
						CricketUtil.FULL, CricketUtil.CHOSE).toUpperCase() + "\0", print_writers);
					break;
				case "SHOW-SQUAD-NOT-PLAYING-TODAY":
					
					containerName_1 = Players.stream().filter(player -> PlayerId.contains(player.getPlayerId())).map(Player::getTicker_name).collect(Collectors.joining(", "));
					System.out.println("containerName_1 = " + containerName_1);
					if(!containerName_1.isEmpty()) {
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_LineUp_Image$Shift_For_TopTitle$Footer$Text$Side" + WhichSide + "$"
							+ "txt_FooterText*GEOM*TEXT SET OUT: " + containerName_1 + "\0", print_writers);
					} else {
						populateTeamLineUpFooter(print_writers, WhichSide, whichGraphicsToProcess, config, "SHOW-TOSS", matchAllData);
					}
					break;
				}
				break;
			}
			break;
		}
		return Constants.OK;
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

	public String populateFooter(List<PrintWriter> print_writers, int WhichSide, String whatToProcess, MatchAllData matchAllData, Inning inning, Configuration config) {
		switch (config.getBroadcaster()) {
		case Constants.AFG_SL_SERIES:
			switch(whatToProcess) {
			case "Control_F7":
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$Footer$Side" + WhichSide + "$select_FooterType"
						+ "*FUNCTION*Omo*vis_con SET 4\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$Footer$Side" + WhichSide + "$Style4$txt_Footer"
						+ "*GEOM*TEXT SET " + CricketFunctions.generateTossResult(matchAllData, CricketUtil.FULL, CricketUtil.FIELD, CricketUtil.FULL, 
								CricketUtil.CHOSE).toUpperCase() + "\0", print_writers);
				break;
			case "Shift_T":
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$Footer$Side" + WhichSide + "$select_FooterType"
						+ "*FUNCTION*Omo*vis_con SET 3\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$Footer$Side" + WhichSide + "$Style3$txt_Footer"
						+ "*GEOM*TEXT SET " + CricketFunctions.generateTossResult(matchAllData, CricketUtil.FULL, CricketUtil.FIELD, CricketUtil.FULL, 
								CricketUtil.CHOSE).toUpperCase() + "\0", print_writers);
				break;
			case "Shift_F11":
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$Footer$Side" + WhichSide + "$select_FooterType"
						+ "*FUNCTION*Omo*vis_con SET 3\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$Footer$Side" + WhichSide + "$Style3$txt_Footer"
						+ "*GEOM*TEXT SET " + CricketFunctions.GenerateMatchSummaryStatus(2, matchAllData, CricketUtil.FULL, "", 
								Constants.BCCI, false).getTargetOrResult().replace("win", "won").toUpperCase() + "\0", print_writers);
				break;
			case "Control_F11":
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$Footer$Side" + WhichSide + "$select_FooterType"
						+ "*FUNCTION*Omo*vis_con SET 3\0", print_writers);
				for(VariousText vt : VariousText) {
					if(vt.getVariousType().equalsIgnoreCase("MATCHSUMMARYFOOTER") && vt.getUseThis().equalsIgnoreCase(CricketUtil.YES)) {
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$Footer$Side" + WhichSide + "$Style3$txt_Footer"
								+ "*GEOM*TEXT SET " + vt.getVariousText().toUpperCase() + "\0", print_writers);
						break;
					}else if(vt.getVariousType().equalsIgnoreCase("MATCHSUMMARYFOOTER") && vt.getUseThis().equalsIgnoreCase(CricketUtil.NO)) {
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$Footer$Side" + WhichSide + "$Style3$txt_Footer"
								+ "*GEOM*TEXT SET " + CricketFunctions.GenerateMatchSummaryStatus(2, matchAllData, CricketUtil.FULL, "", Constants.BCCI, 
										false).getTargetOrResult().toUpperCase() + "\0", print_writers);
					}
				}
				break;
			case "Shift_K": case "Shift_F10": case "Alt_F11":
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$Footer$Side" + WhichSide + "$select_FooterType"
						+ "*FUNCTION*Omo*vis_con SET 0\0", print_writers);
				break;
			case "Control_Shift_F1":
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$Footer$Side" + WhichSide + "$select_FooterType"
						+ "*FUNCTION*Omo*vis_con SET 2\0", print_writers);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$Footer$Side" + WhichSide + "$Style2$Score$txt_Score"
						+ "*GEOM*TEXT SET " + CricketFunctions.getTeamScore(inning, "-", false) + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$Footer$Side" + WhichSide + "$Style2$Score$Score_Outline"
						+ "*GEOM*TEXT SET " + CricketFunctions.getTeamScore(inning, "-", false) + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$Footer$Side" + WhichSide + "$Style2$Score$DeclareGgrp"
						+ "*ACTIVE SET 0\0", print_writers);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$Footer$Side" + WhichSide + "$Style2$Overs$txt_OversHead"
						+ "*GEOM*TEXT SET " + (inning.getTotalOvers() == 1 && inning.getTotalBalls() == 0 ? "OVER" : "OVERS") + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$Footer$Side" + WhichSide + "$Style2$Overs$txt_OversValue"
						+ "*GEOM*TEXT SET " + CricketFunctions.OverBalls(inning.getTotalOvers(), inning.getTotalBalls()) + "\0", print_writers);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$Footer$Side" + WhichSide + "$Style2$Extras$txt_ExtrasValue"
						+ "*GEOM*TEXT SET " + inning.getTotalExtras() + "\0", print_writers);
				break;
			case "Control_F1":
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$Footer$Side" + WhichSide + "$select_FooterType"
						+ "*FUNCTION*Omo*vis_con SET 5\0", print_writers);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$Footer$Side" + WhichSide + "$Style5$Score$txt_Score"
						+ "*GEOM*TEXT SET " + CricketFunctions.getTeamScore(inning, "-", false) + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$Footer$Side" + WhichSide + "$Style5$Score$Score_Outline"
						+ "*GEOM*TEXT SET " + CricketFunctions.getTeamScore(inning, "-", false) + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$Footer$Side" + WhichSide + "$Style5$Score$DeclareGgrp"
						+ "*ACTIVE SET 0\0", print_writers);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$Footer$Side" + WhichSide + "$Style5$Overs$txt_OversHead"
						+ "*GEOM*TEXT SET " + (inning.getTotalOvers() == 1 && inning.getTotalBalls() == 0 ? "OVER" : "OVERS") + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$Footer$Side" + WhichSide + "$Style5$Overs$txt_OversValue"
						+ "*GEOM*TEXT SET " + CricketFunctions.OverBalls(inning.getTotalOvers(), inning.getTotalBalls()) + "\0", print_writers);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$Footer$Side" + WhichSide + "$Style5$Extras$txt_ExtrasValue"
						+ "*GEOM*TEXT SET " + inning.getTotalExtras() + "\0", print_writers);
				break;
			case "F1": case "F2": case "Control_Alt_F1": case "Alt_Shift_F1": case "F4": case "Control_F10":
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$Footer$Side" + WhichSide + "$select_FooterType"
						+ "*FUNCTION*Omo*vis_con SET 1\0", print_writers);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$Footer$Side" + WhichSide + "$Style1$Score$txt_Score"
						+ "*GEOM*TEXT SET " + CricketFunctions.getTeamScore(inning, "-", false) + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$Footer$Side" + WhichSide + "$Style1$Score$Score_Outline"
						+ "*GEOM*TEXT SET " + CricketFunctions.getTeamScore(inning, "-", false) + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$Footer$Side" + WhichSide + "$Style1$Score$DeclareGgrp"
						+ "*ACTIVE SET 0\0", print_writers);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$Footer$Side" + WhichSide + "$Style1$Overs$txt_OversHead"
						+ "*GEOM*TEXT SET " + (inning.getTotalOvers() == 1 && inning.getTotalBalls() == 0 ? "OVER" : "OVERS") + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$Footer$Side" + WhichSide + "$Style1$Overs$txt_OversValue"
						+ "*GEOM*TEXT SET " + CricketFunctions.OverBalls(inning.getTotalOvers(), inning.getTotalBalls()) + "\0", print_writers);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$Footer$Side" + WhichSide + "$Style1$Extras$txt_ExtrasValue"
						+ "*GEOM*TEXT SET " + inning.getTotalExtras() + "\0", print_writers);
				break;
			}
			break;
		case Constants.BAN_AFG_SERIES: case Constants.ACC:
			
			String OverData = "", filePath = "C:\\Sports\\Cricket\\ReduceOver.txt",line1 = "", line2 = "", line3 = "";
			
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
	        
			switch(whatToProcess) {
			case "F4": case "F1": case "F2": case "Control_F10":
				if(inning.getInningNumber() == 1) {
					if(line1.equalsIgnoreCase("YES")) {
						OverData = "(" + line2 + ")";
					}else if(line1.equalsIgnoreCase("NO")) {
						if(matchAllData.getSetup().getReducedOvers() != null) {
							OverData = (Integer.valueOf(matchAllData.getSetup().getReducedOvers()) > 0 ? " (" + 
									matchAllData.getSetup().getReducedOvers() + ")":"");
						}
					}
				}else if(inning.getInningNumber() == 2) {
					if(line1.equalsIgnoreCase("YES")) {
						OverData = "(" + line3 + ")";
					}else if(line1.equalsIgnoreCase("NO")) {
						OverData = (matchAllData.getSetup().getTargetOvers() != null && !matchAllData.getSetup().getTargetOvers().trim().isEmpty() ? 
								" (" + matchAllData.getSetup().getTargetOvers() + ")":"");
					}
				}
				break;
			}

			switch(whatToProcess) {
			case "Shift_T":
				containerName_1 = Players.stream().filter(player -> PlayerId.contains(player.getPlayerId())).map(Player::getTicker_name).collect(Collectors.joining(", "));
	
				if(!containerName_1.trim().isEmpty()) {
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$Playing_XI_Image$TeamDataAll$BottomInfoAll"
							+ "$txt_Info*GEOM*TEXT SET OUT : " + containerName_1 + "\0", print_writers);
				}else {
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$Playing_XI_Image$TeamDataAll$BottomInfoAll$txt_Info*GEOM*TEXT SET " 
							+ CricketFunctions.generateTossResult(matchAllData, CricketUtil.FULL, CricketUtil.FIELD, CricketUtil.FULL, CricketUtil.CHOSE).toUpperCase() + "\0", print_writers);
				}
				break;
			case "Control_F7":
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$Lineup_Both$LineupDataALL$BottomInfoAll$txt_Score*GEOM*TEXT SET " 
						+ CricketFunctions.generateTossResult(matchAllData, CricketUtil.FULL, CricketUtil.FIELD, CricketUtil.FULL, CricketUtil.CHOSE).toUpperCase() + "\0", print_writers);
				break;
			case "Shift_F10":
				if(inning.getInningNumber() == 1) {
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$RunWorm$WormDataAll$BottomInfoAll$txt_Info*GEOM*TEXT SET " 
							+ "CURRENT RUN RATE : " + inning.getRunRate() + "\0", print_writers);
				}else {
					if(matchAllData.getMatch().getMatchResult() != null) {
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$RunWorm$WormDataAll$BottomInfoAll$txt_Info*GEOM*TEXT SET " 
								+ matchAllData.getMatch().getMatchStatus().toUpperCase() + "\0", print_writers);
					}else {
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$RunWorm$WormDataAll$BottomInfoAll$txt_Info*GEOM*TEXT SET " 
								+ "AT THIS STAGE : " + inning.getBowling_team().getTeamName1() + " WERE " + IndexController.MatchStats.getInningCompare().getTotalRuns() + " - " 
								+ IndexController.MatchStats.getInningCompare().getTotalWickets() + "\0", print_writers);
					}
				}
				break;
			case "Shift_F11":
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$Summary$BottomInfoAll$txt_Info*GEOM*TEXT SET " 
						+ CricketFunctions.GenerateMatchSummaryStatus(2, matchAllData, CricketUtil.FULL, "", Constants.BCCI, false).getTargetOrResult().replace("win", "won").toUpperCase() + "\0", print_writers);
				break;
			case "Control_F11":
				for(VariousText vt : VariousText) {
					if(vt.getVariousType().equalsIgnoreCase("MATCHSUMMARYFOOTER") && vt.getUseThis().equalsIgnoreCase(CricketUtil.YES)) {
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$Summary$BottomInfoAll$txt_Info*GEOM*TEXT SET " 
								+ vt.getVariousText().toUpperCase() + "\0", print_writers);
						break;
					}else if(vt.getVariousType().equalsIgnoreCase("MATCHSUMMARYFOOTER") && vt.getUseThis().equalsIgnoreCase(CricketUtil.NO)) {
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$Summary$BottomInfoAll$txt_Info*GEOM*TEXT SET " 
								+ CricketFunctions.GenerateMatchSummaryStatus(2, matchAllData, CricketUtil.FULL, "", Constants.BCCI, false).getTargetOrResult().toUpperCase() + "\0", print_writers);
					}
				}
				break;
				
			case "Control_p":
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$Standings$BattingDataAll$BottomInfoAll$img_Text$"
						+ "txt_Score*GEOM*TEXT SET " + varText + "\0", print_writers);
				break;
				
			case "Control_F10":
				String whichManh = "";
				switch (matchAllData.getSetup().getMatchType()) {
				case CricketUtil.ODI: case CricketUtil.OD:
					whichManh = "ManhattanDataAll2";
					break;
				default:
					whichManh = "ManhattanDataAll";
					break;
				}
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$Manhattan$" + whichManh + "$"
						+ "BottomInfoAll$txt_Score*GEOM*TEXT SET " + CricketFunctions.getTeamScore(inning, "-", false) + "\0", print_writers);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$Manhattan$" + whichManh + "$"
						+ "BottomInfoAll$txt_OversText*GEOM*TEXT SET " + (inning.getTotalOvers()==1 && inning.getTotalBalls()==0?"OVER":"OVERS") + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$Manhattan$" + whichManh + "$"
						+ "BottomInfoAll$txt_Overs*GEOM*TEXT SET " + CricketFunctions.OverBalls(inning.getTotalOvers(), inning.getTotalBalls()) + OverData + "\0", print_writers);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$Manhattan$" + whichManh + "$"
						+ "BottomInfoAll$txt_RunRateText*GEOM*TEXT SET EXTRAS\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$Manhattan$" + whichManh + "$"
						+ "BottomInfoAll$txt_RunRate*GEOM*TEXT SET " + inning.getTotalExtras() + "\0", print_writers);
				break;
			case "F4":
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$PartnershipCard$PartnershipDataAll$"
						+ "BottomInfoAll$txt_Score*GEOM*TEXT SET " + CricketFunctions.getTeamScore(inning, "-", false) + "\0", print_writers);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$PartnershipCard$PartnershipDataAll$"
						+ "BottomInfoAll$txt_OversText*GEOM*TEXT SET " + (inning.getTotalOvers()==1 && inning.getTotalBalls()==0?"OVER":"OVERS") + "\0", print_writers);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$PartnershipCard$PartnershipDataAll$"
						+ "BottomInfoAll$txt_Overs*GEOM*TEXT SET " + CricketFunctions.OverBalls(inning.getTotalOvers(), inning.getTotalBalls()) + OverData + "\0", print_writers);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$PartnershipCard$PartnershipDataAll$"
						+ "BottomInfoAll$txt_Extras*GEOM*TEXT SET " + inning.getTotalExtras() + "\0", print_writers);
				break;
			case "F1":
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$BattingCard$BattingDataAll$Normal$"
						+ "BottomInfoAll$txt_Score*GEOM*TEXT SET " + CricketFunctions.getTeamScore(inning, "-", false) + "\0", print_writers);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$BattingCard$BattingDataAll$Normal$"
						+ "BottomInfoAll$txt_OversText*GEOM*TEXT SET " + (inning.getTotalOvers()==1 && inning.getTotalBalls()==0?"OVER":"OVERS") + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$BattingCard$BattingDataAll$Normal$"
						+ "BottomInfoAll$txt_Overs*GEOM*TEXT SET " + CricketFunctions.OverBalls(inning.getTotalOvers(), inning.getTotalBalls()) + OverData + "\0", print_writers);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$BattingCard$BattingDataAll$Normal$"
						+ "BottomInfoAll$txt_Extras*GEOM*TEXT SET " + inning.getTotalExtras() + "\0", print_writers);
				
				//BAT PERFORMER
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$BattingCard$BattingDataAll$Split$Left$"
						+ "BottomInfoAll$txt_Score*GEOM*TEXT SET " + CricketFunctions.getTeamScore(inning, "-", false) + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$BattingCard$BattingDataAll$Split$Left$"
						+ "BottomInfoAll$txt_Extras*GEOM*TEXT SET " + CricketFunctions.OverBalls(inning.getTotalOvers(), inning.getTotalBalls()) + OverData + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$BattingCard$BattingDataAll$Split$Left$"
						+ "BottomInfoAll$txt_ExtrasText*GEOM*TEXT SET " + (inning.getTotalOvers()==1 && inning.getTotalBalls()==0?"OVER":"OVERS") + "\0", print_writers);
				break;
			case "F2":
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$BowlingCard$BallDataAll$Normal$"
						+ "BottomInfoAll$txt_Score*GEOM*TEXT SET " + CricketFunctions.getTeamScore(inning, "-", false) + "\0", print_writers);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$BowlingCard$BallDataAll$Normal$"
						+ "BottomInfoAll$txt_OversText*GEOM*TEXT SET " + (inning.getTotalOvers()==1 && inning.getTotalBalls()==0?"OVER":"OVERS") + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$BowlingCard$BallDataAll$Normal$"
						+ "BottomInfoAll$txt_Overs*GEOM*TEXT SET " + CricketFunctions.OverBalls(inning.getTotalOvers(), inning.getTotalBalls()) + OverData + "\0", print_writers);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$BowlingCard$BallDataAll$Normal$"
						+ "BottomInfoAll$txt_Extras*GEOM*TEXT SET " + inning.getTotalExtras() + "\0", print_writers);
				
				//BALL PERFORMER
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$BowlingCard$BallDataAll$Split$Left$"
						+ "BottomInfoAll$txt_Score*GEOM*TEXT SET " + CricketFunctions.getTeamScore(inning, "-", false) + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$BowlingCard$BallDataAll$Split$Left$"
						+ "BottomInfoAll$txt_Extras*GEOM*TEXT SET " + CricketFunctions.OverBalls(inning.getTotalOvers(), inning.getTotalBalls()) + OverData + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$BowlingCard$BallDataAll$Split$Left$"
						+ "BottomInfoAll$txt_ExtrasText*GEOM*TEXT SET " + (inning.getTotalOvers()==1 && inning.getTotalBalls()==0?"OVER":"OVERS") + "\0", print_writers);
				break;
			case "m":
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$MatchID$Text$HeaderGrp$000Header*GEOM*TEXT SET " 
						+ matchAllData.getSetup().getTournament() + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$MatchID$Text$HeaderGrp$VS*GEOM*TEXT SET V\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$MatchID$Text$HeaderGrp$000SubHeader*GEOM*TEXT SET " 
						+ matchAllData.getSetup().getMatchIdent() + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$MatchID$Text$HeaderGrp$BottmeText*GEOM*TEXT SET LIVE FROM " 
						+ matchAllData.getSetup().getVenueName() + "\0", print_writers);
				break;
			case "Control_m":
//				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_Ident$Main$MatchNumberGrp$SubTitle$txt_MatchNumber*GEOM*TEXT SET " 
//						+ (fixture.getMatchnumber()>9 ? fixture.getMatchfilename() : "MATCH " + fixture.getMatchnumber()) + "\0", print_writers);
				
				String Footer_Data="",newDate="",Day_Data="",Time_Data="";
				
				Calendar cal = Calendar.getInstance();
				cal.add(Calendar.DATE, +1);
				if(fixture.getDate().equalsIgnoreCase(new SimpleDateFormat("dd-MM-yyyy").format(cal.getTime()))) {
					Footer_Data = "TOMORROW - " + (fixture.getLocalTime() != null ?fixture.getLocalTime() + " - ":"") + matchAllData.getSetup().getVenueName();
					Day_Data = "TOMORROW";
					Time_Data = (fixture.getLocalTime() != null ?fixture.getLocalTime() + " LOCAL TIME":"");
				}else {
					cal.add(Calendar.DATE, -1);
					if(fixture.getDate().equalsIgnoreCase(new SimpleDateFormat("dd-MM-yyyy").format(cal.getTime()))) {
						Footer_Data = "UP-NEXT";
						Day_Data = "UP-NEXT";
						Time_Data = "";
					}else {
						newDate = fixture.getDate().split("-")[0];
						if(Integer.valueOf(newDate) < 10) {
							newDate = newDate.replaceFirst("0", "");
						}
						Footer_Data = CricketFunctions.ordinal(Integer.valueOf(newDate)) + " " + Month.of(Integer.valueOf(fixture.getDate().split("-")[1])) 
							+ " - " + (fixture.getLocalTime() != null ?fixture.getLocalTime() + " - ":"") + matchAllData.getSetup().getVenueName();
						
						Day_Data = CricketFunctions.ordinal(Integer.valueOf(newDate)) + " " + Month.of(Integer.valueOf(fixture.getDate().split("-")[1]));
						Time_Data = (fixture.getLocalTime() != null ?fixture.getLocalTime() + " LOCAL TIME":"");
					}
				}
				
				switch (config.getBroadcaster()) {
				case Constants.BAN_AFG_SERIES:
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$MatchID$Text$HeaderGrp$000Header*GEOM*TEXT SET " 
							+ matchAllData.getSetup().getTournament() + "\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$MatchID$Text$HeaderGrp$VS*GEOM*TEXT SET V\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$MatchID$Text$HeaderGrp$000SubHeader*GEOM*TEXT SET " 
							+ toOrdinalWords(fixture.getMatchnumber()).toUpperCase() + " T20I" + "\0", print_writers);
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$MatchID$Text$HeaderGrp$BottmeText*GEOM*TEXT SET " + Footer_Data + "\0", print_writers);
					break;
				case Constants.ACC:
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$MatchID$Text$HeaderGrp$000Header*GEOM*TEXT SET " 
							+ matchAllData.getSetup().getTournament() + "\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$MatchID$Text$HeaderGrp$VS*GEOM*TEXT SET V\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$MatchID$Text$HeaderGrp$000SubHeader*GEOM*TEXT SET " 
							+ (fixture.getTeamgroup() != null ? fixture.getTeamgroup() : "") + " | " + Day_Data + "," + Time_Data + "\0", print_writers);
					break;
				}
				
				
				break;
			}
			break;
		case Constants.TRI_SERIES: case Constants.MT20: 
			switch(whatToProcess) {
			case "Control_F7":
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$Footer"
						+ "$Side" + WhichSide + "$select_FooterStyle*FUNCTION*Omo*vis_con SET 1\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$Footer$Side" + WhichSide + "$FooterStyle2$"
						+ "Extras$Extras$txt_FooterText*GEOM*TEXT SET " + CricketFunctions.generateTossResult(matchAllData, CricketUtil.FULL, CricketUtil.FIELD, 
								CricketUtil.FULL, CricketUtil.CHOSE).toUpperCase() + "\0", print_writers);
				break;
//			case "Control_Shift_F7":
//				containerName_1 = "";
//				containerName_2 = "";
//				containerName_1 = PlayingXI.stream().filter(player -> !PlayerIdIn.contains(player.getPlayerId())).map(Player::getTicker_name).collect(Collectors.joining(", "));
//				if(otherSquad != null && otherSquad.size() > 0) {
//					containerName_2 = otherSquad.stream().filter(player -> PlayerId.contains(player.getPlayerId())).map(Player::getTicker_name).collect(Collectors.joining(", "));
//				}
//				
//				if(!containerName_1.isEmpty() && containerName_1 != null && 
//						!containerName_2.isEmpty() && containerName_2 != null) {
//					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_LineUp_Image$Shift_For_TopTitle$Footer$Text$Side" + WhichSide + "$"
//							+ "txt_FooterText*GEOM*TEXT SET OUT: " + containerName_2 + "\0", print_writers);
//					
//				}else {
//					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_LineUp_Image$Shift_For_TopTitle$Footer$Text$Side" + WhichSide + "$"
//							+ "txt_FooterText*GEOM*TEXT SET " + CricketFunctions.generateTossResult(matchAllData, CricketUtil.FULL, CricketUtil.FIELD, 
//									CricketUtil.FULL, CricketUtil.CHOSE).toUpperCase() + "\0", print_writers);
//				}
//				
////				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_LineUp_Image$Shift_For_TopTitle$Footer$Text$Side" + WhichSide + "$"
////						+ "txt_FooterText*GEOM*TEXT SET " + CricketFunctions.generateTossResult(matchAllData, CricketUtil.FULL, CricketUtil.FIELD, 
////								CricketUtil.FULL, CricketUtil.CHOSE).toUpperCase() + "\0", print_writers);
//				
//				break;	
			case "Control_F11":
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$Footer"
						+ "$Side" + WhichSide + "$select_FooterStyle*FUNCTION*Omo*vis_con SET 1\0", print_writers);
				for(VariousText vt : VariousText) {
					if(vt.getVariousType().equalsIgnoreCase("MATCHSUMMARYFOOTER") && vt.getUseThis().equalsIgnoreCase(CricketUtil.YES)) {
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$Footer$Side" + WhichSide + "$FooterStyle2$"
								+ "Extras$Extras$txt_FooterText*GEOM*TEXT SET " + vt.getVariousText().toUpperCase() + "\0", print_writers);
						break;
					}else if(vt.getVariousType().equalsIgnoreCase("MATCHSUMMARYFOOTER") && vt.getUseThis().equalsIgnoreCase(CricketUtil.NO)) {
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$Footer$Side" + WhichSide + "$FooterStyle2$"
								+ "Extras$Extras$txt_FooterText*GEOM*TEXT SET " + CricketFunctions.GenerateMatchSummaryStatus(2, matchAllData, CricketUtil.FULL, "", 
										Constants.BCCI, false).getTargetOrResult().toUpperCase() + "\0", print_writers);
					}
				}
				break;
			case "Shift_F11":
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$Footer"
						+ "$Side" + WhichSide + "$select_FooterStyle*FUNCTION*Omo*vis_con SET 1\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$Footer$Side" + WhichSide + "$FooterStyle2$"
						+ "Extras$Extras$txt_FooterText*GEOM*TEXT SET " + CricketFunctions.GenerateMatchSummaryStatus(2, matchAllData, CricketUtil.FULL, "", 
								Constants.BCCI, false).getTargetOrResult().replace("win", "won").toUpperCase() + "\0", print_writers);
				break;	
			case "Control_p":
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$Footer"
						+ "$Side" + WhichSide + "$select_FooterStyle*FUNCTION*Omo*vis_con SET 1\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$Footer$Side" + WhichSide + "$FooterStyle2$"
						+ "Extras$Extras$txt_FooterText*GEOM*TEXT SET " + varText + "\0", print_writers);
				break;	
			case "Alt_F9":
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$Footer"
						+ "$Side" + WhichSide + "$select_FooterStyle*FUNCTION*Omo*vis_con SET 1\0", print_writers);
				if(WhichStyle.equalsIgnoreCase("Age")) {
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$Footer$Side" + WhichSide + "$FooterStyle2$"
							+ "Extras$Extras$txt_FooterText*GEOM*TEXT SET \0", print_writers);
				}else {
					if(statsType.getStatsShortName().equalsIgnoreCase("IT20")) {
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$Footer$Side" + WhichSide + "$FooterStyle2$"
								+ "Extras$Extras$txt_FooterText*GEOM*TEXT SET " + WhichStyle.toUpperCase() + " IN T20Is"
								+ "\0", print_writers);
					}else {
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$Footer$Side" + WhichSide + "$FooterStyle2$"
								+ "Extras$Extras$txt_FooterText*GEOM*TEXT SET " + statsType.getStatsFullName().toUpperCase() + " - " + WhichStyle.toUpperCase() 
								+ "\0", print_writers);
					}
				}
				break;
			case "Shift_F10":
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$Footer$Side" + WhichSide 
						+ "$select_FooterStyle*FUNCTION*Omo*vis_con SET 1\0", print_writers);
				if(inning.getInningNumber() == 1) {
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$Footer$Side" + WhichSide + "$FooterStyle2$"
							+ "Extras$Extras$txt_FooterText*GEOM*TEXT SET CURRENT RUN RATE : " + inning.getRunRate() + "\0", print_writers);
				}else {
					if(matchAllData.getMatch().getMatchResult() != null) {
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$Footer$Side" + WhichSide + "$FooterStyle2$"
								+ "Extras$Extras$txt_FooterText*GEOM*TEXT SET " + matchAllData.getMatch().getMatchStatus().toUpperCase() + "\0", print_writers);
					}else {
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$Footer$Side" + WhichSide + "$FooterStyle2$"
								+ "Extras$Extras$txt_FooterText*GEOM*TEXT SET AT THIS STAGE: " + inning.getBowling_team().getTeamName1() + " WERE " + 
								IndexController.MatchStats.getInningCompare().getTotalRuns() + " - " + IndexController.MatchStats.getInningCompare().getTotalWickets() 
								+ "\0", print_writers);
					}
				}
				break;
			case "m":
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_Ident$Main$MatchNumberGrp$SubTitle$txt_MatchNumber*GEOM*TEXT SET " 
						+ matchAllData.getSetup().getMatchIdent() + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_Ident$Main$Footer$Text$txt_FooterText*GEOM*TEXT SET " 
						+ "LIVE FROM " + matchAllData.getSetup().getVenueName() + "\0", print_writers);
				break;
			case "Control_m":
//				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_Ident$Main$MatchNumberGrp$SubTitle$txt_MatchNumber*GEOM*TEXT SET " 
//						+ (fixture.getMatchnumber()>9 ? fixture.getMatchfilename() : "MATCH " + fixture.getMatchnumber()) + "\0", print_writers);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_Ident$Main$MatchNumberGrp$SubTitle$txt_MatchNumber*GEOM*TEXT SET " 
						+ toOrdinalWords(fixture.getMatchnumber()).toUpperCase() + " T20I" + "\0", print_writers);
				String Footer_Data="",newDate="";
				
				Calendar cal = Calendar.getInstance();
				cal.add(Calendar.DATE, +1);
				if(fixture.getDate().equalsIgnoreCase(new SimpleDateFormat("dd-MM-yyyy").format(cal.getTime()))) {
					Footer_Data = "TOMORROW - " + (fixture.getLocalTime() != null ?fixture.getLocalTime() + " - ":"") + matchAllData.getSetup().getVenueName();
				}else {
					cal.add(Calendar.DATE, -1);
					if(fixture.getDate().equalsIgnoreCase(new SimpleDateFormat("dd-MM-yyyy").format(cal.getTime()))) {
						Footer_Data = "UP-NEXT";
					}else {
						newDate = fixture.getDate().split("-")[0];
						if(Integer.valueOf(newDate) < 10) {
							newDate = newDate.replaceFirst("0", "");
						}
						Footer_Data = CricketFunctions.ordinal(Integer.valueOf(newDate)) + " " + Month.of(Integer.valueOf(fixture.getDate().split("-")[1])) 
							+ " - " + (fixture.getLocalTime() != null ?fixture.getLocalTime() + " - ":"") + matchAllData.getSetup().getVenueName();
					}
				}
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_Ident$Main$Footer$Text$txt_FooterText*GEOM*TEXT SET " + Footer_Data 
						+ "\0", print_writers);
				
				break;
			case "F1": case "F2": case "F4": case "Control_F10": case "Control_F1": case "Control_Alt_F1": case "Alt_Shift_F1":
			case "Control_Shift_F4": case "Control_Shift_F5":
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$Footer"
						+ "$Side" + WhichSide + "$select_FooterStyle*FUNCTION*Omo*vis_con SET 0\0", print_writers);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$Footer"
						+ "$Side" + WhichSide + "$FooterStyle1$Extras$Extras$txt_StatHead*GEOM*TEXT SET Extras\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$Footer"
						+ "$Side" + WhichSide + "$FooterStyle1$Extras$Extras$txt_StatValue*GEOM*TEXT SET " + inning.getTotalExtras() + "\0", print_writers);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$Footer"
						+ "$Side" + WhichSide + "$FooterStyle1$Extras$Overs$txt_StatHead*GEOM*TEXT SET " + (inning.getTotalOvers() == 1 && 
						inning.getTotalBalls()==0 ?"Over":"Overs") + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$Footer"
						+ "$Side" + WhichSide + "$FooterStyle1$Extras$Overs$txt_StatValue*GEOM*TEXT SET " + 
						CricketFunctions.OverBalls(inning.getTotalOvers(), inning.getTotalBalls()) + "\0", print_writers);
				
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$Footer"
						+ "$Side" + WhichSide + "$FooterStyle1$Overs$Overs$txt_StatHead*GEOM*TEXT SET " + (inning.getTotalOvers() == 1 && 
						inning.getTotalBalls()==0 ?"Over":"Overs") + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$Footer"
						+ "$Side" + WhichSide + "$FooterStyle1$Overs$Overs$txt_StatValue*GEOM*TEXT SET " + 
						CricketFunctions.OverBalls(inning.getTotalOvers(), inning.getTotalBalls()) + "\0", print_writers);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$Footer"
						+ "$Side" + WhichSide + "$FooterStyle1$Total$txt_TotalScore*GEOM*TEXT SET " + 
						CricketFunctions.getTeamScore(inning, "-", false) + "\0", print_writers);
				break;	
			}
			break;
		}
		return Constants.OK;
	}
	
	public String ScorecardBody(List<PrintWriter> print_writers, int WhichSide, Configuration config, MatchAllData matchAllData,Inning inning) throws Exception
	{
		switch (config.getBroadcaster()) {
		case Constants.AFG_SL_SERIES:
			rowId = 0;
			
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$select_GraphicsType"
					+ "*FUNCTION*Omo*vis_con SET 0\0", print_writers);
			
			Collections.sort(inning.getBattingCard());
			for (BattingCard bc : inning.getBattingCard()) {
				rowId++;
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$BattingCard$MainData"
						+ "*FUNCTION*Grid*num_row SET " + rowId + "\0", print_writers);
				if(rowId == 12) {
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$BattingCard$MainData"
							+ "*FUNCTION*Grid*row_offset SET " + "48" + "\0", print_writers);
				}else if(rowId == 13) {
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$BattingCard$MainData"
							+ "*FUNCTION*Grid*row_offset SET " + "52" + "\0", print_writers);
				}else if(rowId == 11) {
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$BattingCard$MainData"
							+ "*FUNCTION*Grid*row_offset SET " + "55" + "\0", print_writers);
				}
				
				switch (bc.getStatus().toUpperCase()) {
				case CricketUtil.STILL_TO_BAT:
					
					if(bc.getHowOut() == null) {
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$BattingCard$MainData$Grp" 
								+ rowId + "$select_DataType*FUNCTION*Omo*vis_con SET 0\0", print_writers);
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$BattingCard$MainData$Grp" 
								+ rowId + "$BattingData$StillToBat$txt_BatterName*GEOM*TEXT SET " + bc.getPlayer().getTicker_name() + "\0", print_writers);

						if(!matchAllData.getMatch().getSourceOfThisMatchData().equalsIgnoreCase("WEBSITE")) {
							if(!CricketFunctions.checkBatAndBallImpactInOutPlayer(matchAllData.getEventFile().getEvents(), bc.getPlayerId()).isEmpty()) {
								switch(CricketFunctions.checkBatAndBallImpactInOutPlayer(matchAllData.getEventFile().getEvents(), bc.getPlayerId())) {
								case "CON_OUT":
									if(bc.getBalls() == 0) {
										rowId = rowId - 1;
										this.numberOfRows = rowId;
									}
									break;
								}
							}
						}
						
					}else {
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$BattingCard$MainData$Grp" 
								+ rowId + "$select_DataType*FUNCTION*Omo*vis_con SET 1\0", print_writers);
						
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$BattingCard$MainData$Grp" 
								+ rowId + "$BattingData$Out$txt_BatterName*GEOM*TEXT SET " + bc.getPlayer().getTicker_name() + "\0", print_writers);
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$BattingCard$MainData$Grp" 
								+ rowId + "$BattingData$Out$txt_Scores*GEOM*TEXT SET " + bc.getRuns() + "\0", print_writers);
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$BattingCard$MainData$Grp" 
								+ rowId + "$BattingData$Out$txt_Balls*GEOM*TEXT SET " + bc.getBalls() + "\0", print_writers);
						
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$BattingCard$MainData$Grp" 
								+ rowId + "$BattingData$Out$txt_HowOut1*GEOM*TEXT SET \0", print_writers);
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$BattingCard$MainData$Grp" 
								+ rowId + "$BattingData$Out$txt_FielderName*GEOM*TEXT SET " + bc.getHowOut().replace("_", " ").toLowerCase() + "\0", print_writers);
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$BattingCard$MainData$Grp" 
								+ rowId + "$BattingData$Out$txt_HowOut2*GEOM*TEXT SET \0", print_writers);
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$BattingCard$MainData$Grp" 
								+ rowId + "$BattingData$Out$txt_BowlerName*GEOM*TEXT SET \0", print_writers);
					}
					break;
				default:
					switch (bc.getStatus().toUpperCase()) {
					case CricketUtil.OUT:
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$BattingCard$MainData$Grp" 
								+ rowId + "$select_DataType*FUNCTION*Omo*vis_con SET 1\0", print_writers);
						
						containerName = "$Out";
						break;
					case CricketUtil.NOT_OUT:
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$BattingCard$MainData$Grp" 
								+ rowId + "$select_DataType*FUNCTION*Omo*vis_con SET 2\0", print_writers);
						
						containerName = "$NotOut";
						break;
					}
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$BattingCard$MainData$Grp" 
							+ rowId + "$BattingData" + containerName + "$txt_BatterName*GEOM*TEXT SET " + bc.getPlayer().getTicker_name() + "\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$BattingCard$MainData$Grp" 
							+ rowId + "$BattingData" + containerName + "$txt_Scores*GEOM*TEXT SET " + bc.getRuns() + "\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$BattingCard$MainData$Grp" 
							+ rowId + "$BattingData" + containerName + "$txt_Balls*GEOM*TEXT SET " + bc.getBalls() + "\0", print_writers);
					
					how_out_txt = CricketFunctions.processHowOutText("FOUR-PART-HOW-OUT", bc);
					
					if(how_out_txt != null && how_out_txt.split("|").length >= 4) {
						//System.out.println("how_out_txt = " + how_out_txt);
						
						if(bc.getHowOut().equalsIgnoreCase("run_out") || bc.getHowOut().equalsIgnoreCase("mankad")) {
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$BattingCard$MainData$Grp" 
									+ rowId + "$BattingData" + containerName + "$txt_HowOut1*GEOM*TEXT SET " + how_out_txt.split("\\|")[0].trim() + "\0", print_writers);
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$BattingCard$MainData$Grp" 
									+ rowId + "$BattingData" + containerName + "$txt_FielderName*GEOM*TEXT SET " + how_out_txt.split("\\|")[1].trim().replace("substitute", "sub") + "\0", 
									print_writers);
						}else {
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$BattingCard$MainData$Grp" 
									+ rowId + "$BattingData" + containerName + "$txt_HowOut1*GEOM*TEXT SET " + how_out_txt.split("\\|")[0].trim() + "\0", print_writers);
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$BattingCard$MainData$Grp" 
									+ rowId + "$BattingData" + containerName + "$txt_FielderName*GEOM*TEXT SET " + how_out_txt.split("\\|")[1].trim().replace("substitute", "sub") + "\0", 
									print_writers);
						}
						
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$BattingCard$MainData$Grp" 
								+ rowId + "$BattingData" + containerName + "$txt_HowOut2*GEOM*TEXT SET " + how_out_txt.split("\\|")[2].trim() + "\0", print_writers);
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$BattingCard$MainData$Grp" 
								+ rowId + "$BattingData" + containerName + "$txt_BowlerName*GEOM*TEXT SET " + how_out_txt.split("\\|")[3].trim() + "\0", print_writers);
						
						if(bc.getHowOut().equalsIgnoreCase("concussed") || bc.getHowOut().equalsIgnoreCase("retired_out")) {
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$BattingCard$MainData$Grp" 
									+ rowId + "$BattingData" + containerName + "$txt_HowOut1*GEOM*TEXT SET \0", print_writers);
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$BattingCard$MainData$Grp" 
									+ rowId + "$BattingData" + containerName + "$txt_FielderName*GEOM*TEXT SET " + how_out_txt.split("\\|")[0].trim() + "\0", print_writers);
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$BattingCard$MainData$Grp" 
									+ rowId + "$BattingData" + containerName + "$txt_HowOut2*GEOM*TEXT SET \0", print_writers);
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$BattingCard$MainData$Grp" 
									+ rowId + "$BattingData" + containerName + "$txt_BowlerName*GEOM*TEXT SET \0", print_writers);
						}
					}else {
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$BattingCard$MainData$Grp" 
								+ rowId + "$BattingData" + containerName + "$txt_NotOut*GEOM*TEXT SET " + "NOT OUT" + "\0", print_writers);
					}
				}
			}
			
			this.numberOfRows = rowId;
			break;
		case Constants.BAN_AFG_SERIES: case Constants.ACC:
			rowId = 0;
			
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$Select*FUNCTION*Omo*vis_con SET 0\0", print_writers);
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$BattingCard$BattingDataAll$Select_Type"
					+ "*FUNCTION*Omo*vis_con SET 0\0", print_writers);
			
			Collections.sort(inning.getBattingCard());
			
			for (BattingCard bc : inning.getBattingCard()) {
				rowId++;
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$BattingDataAll$Normal"
						+ "$BatGrp*FUNCTION*Grid*num_row SET " + rowId + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$BattingDataAll$Split$Left"
						+ "$BatGrp*FUNCTION*Grid*num_row SET " + rowId + "\0", print_writers);
				
				if(rowId == 12) {
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$BattingDataAll$Normal"
							+ "$BatGrp*FUNCTION*Grid*row_offset SET " + "18.2" + "\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$BattingDataAll$Split$Left"
							+ "$BatGrp*FUNCTION*Grid*row_offset SET " + "18.2" + "\0", print_writers);
				}else if(rowId == 13) {
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$BattingDataAll$Normal"
							+ "$BatGrp*FUNCTION*Grid*row_offset SET " + "17" + "\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$BattingDataAll$Split$Left"
							+ "$BatGrp*FUNCTION*Grid*row_offset SET " + "17" + "\0", print_writers);
				}else if(rowId == 11) {
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$BattingDataAll$Normal"
							+ "$BatGrp*FUNCTION*Grid*row_offset SET " + "20" + "\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$BattingDataAll$Split$Left"
							+ "$BatGrp*FUNCTION*Grid*row_offset SET " + "20" + "\0", print_writers);
				}
				
				switch (bc.getStatus().toUpperCase()) {
				case CricketUtil.STILL_TO_BAT:
					
					if(bc.getHowOut() == null) {
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$BattingDataAll$Normal$BatRow" 
								+ rowId + "$BatSelection*FUNCTION*Omo*vis_con SET 0\0", print_writers);
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$BattingDataAll$Normal$BatRow" 
								+ rowId + "$StillToBat$txt_BatterName*GEOM*TEXT SET " + bc.getPlayer().getTicker_name() + "\0", print_writers);
						
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$BattingDataAll$Split$Left$BatRow" 
								+ rowId + "$BatSelection*FUNCTION*Omo*vis_con SET 0\0", print_writers);
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$BattingDataAll$Split$Left$BatRow" 
								+ rowId + "$StillToBat$txt_BatterName*GEOM*TEXT SET " + bc.getPlayer().getTicker_name() + "\0", print_writers);
						
						if(!matchAllData.getMatch().getSourceOfThisMatchData().equalsIgnoreCase("WEBSITE")) {
							if(!CricketFunctions.checkBatAndBallImpactInOutPlayer(matchAllData.getEventFile().getEvents(), bc.getPlayerId()).isEmpty()) {
								switch(CricketFunctions.checkBatAndBallImpactInOutPlayer(matchAllData.getEventFile().getEvents(), bc.getPlayerId())) {
								
								case "CON_OUT":
									if(bc.getBalls() == 0) {
										rowId = rowId - 1;
										this.numberOfRows = rowId;
									}
									break;
								}
							}
						}
						
					}else {
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$BattingDataAll$Normal$BatRow" 
								+ rowId + "$BatSelection*FUNCTION*Omo*vis_con SET 1\0", print_writers);
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$BattingDataAll$Normal$BatRow" 
								+ rowId + "$Out$txt_BatterName*GEOM*TEXT SET " + bc.getPlayer().getTicker_name() + "\0", print_writers);
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$BattingDataAll$Normal$BatRow" 
								+ rowId + "$Out$txt_PlayerRuns*GEOM*TEXT SET " + bc.getRuns() + "\0", print_writers);
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$BattingDataAll$Normal$BatRow" 
								+ rowId + "$Out$txt_PlayerBalls*GEOM*TEXT SET " + bc.getBalls() + "\0", print_writers);
						
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$BattingDataAll$Normal$BatRow" 
								+ rowId + "$Out$txt_FielderName*GEOM*TEXT SET " + bc.getHowOut().replace("_", " ").toLowerCase() + "\0", print_writers);
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$BattingDataAll$Normal$BatRow" 
								+ rowId + "$Out$txt_BowlerName*GEOM*TEXT SET \0", print_writers);
						
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$BattingDataAll$Split$Left$BatRow" 
								+ rowId + "$BatSelection*FUNCTION*Omo*vis_con SET 1\0", print_writers);
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$BattingDataAll$Split$Left$BatRow" 
								+ rowId + "$Out$txt_BatterName*GEOM*TEXT SET " + bc.getPlayer().getTicker_name() + "\0", print_writers);
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$BattingDataAll$Split$Left$BatRow" 
								+ rowId + "$Out$txt_PlayerRuns*GEOM*TEXT SET " + bc.getRuns() + "\0", print_writers);
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$BattingDataAll$Split$Left$BatRow" 
								+ rowId + "$Out$txt_PlayerBalls*GEOM*TEXT SET " + bc.getBalls() + "\0", print_writers);
						
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$BattingDataAll$Split$Left$BatRow" 
								+ rowId + "$Highlight$txt_BatterName*GEOM*TEXT SET " + bc.getPlayer().getTicker_name() + "\0", print_writers);
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$BattingDataAll$Split$Left$BatRow" 
								+ rowId + "$Highlight$txt_PlayerRuns*GEOM*TEXT SET " + bc.getRuns() + "\0", print_writers);
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$BattingDataAll$Split$Left$BatRow" 
								+ rowId + "$Highlight$txt_PlayerBalls*GEOM*TEXT SET " + bc.getBalls() + "\0", print_writers);
					}
					break;
				default:
					switch (bc.getStatus().toUpperCase()) {
					case CricketUtil.OUT:
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$BattingDataAll$Normal$BatRow" 
								+ rowId + "$BatSelection*FUNCTION*Omo*vis_con SET 1\0", print_writers);
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$BattingDataAll$Split$Left$BatRow" 
								+ rowId + "$BatSelection*FUNCTION*Omo*vis_con SET 1\0", print_writers);
						
						containerName = "$Out";
						break;
					case CricketUtil.NOT_OUT:
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$BattingDataAll$Normal$BatRow" 
								+ rowId + "$BatSelection*FUNCTION*Omo*vis_con SET 2\0", print_writers);
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$BattingDataAll$Split$Left$BatRow" 
								+ rowId + "$BatSelection*FUNCTION*Omo*vis_con SET 2\0", print_writers);
						
						containerName = "$NotOut";
						break;
					}
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$BattingDataAll$Normal$BatRow" 
							+ rowId + containerName + "$txt_BatterName*GEOM*TEXT SET " + bc.getPlayer().getTicker_name() + "\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$BattingDataAll$Normal$BatRow" 
							+ rowId + containerName + "$txt_PlayerRuns*GEOM*TEXT SET " + bc.getRuns() + "\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$BattingDataAll$Normal$BatRow" 
							+ rowId + containerName + "$txt_PlayerBalls*GEOM*TEXT SET " + bc.getBalls() + "\0", print_writers);
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$BattingDataAll$Split$Left$BatRow" 
							+ rowId + containerName + "$txt_BatterName*GEOM*TEXT SET " + bc.getPlayer().getTicker_name() + "\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$BattingDataAll$Split$Left$BatRow" 
							+ rowId + containerName + "$txt_PlayerRuns*GEOM*TEXT SET " + bc.getRuns() + "\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$BattingDataAll$Split$Left$BatRow" 
							+ rowId + containerName + "$txt_PlayerBalls*GEOM*TEXT SET " + bc.getBalls() + "\0", print_writers);
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$BattingDataAll$Split$Left$BatRow" 
							+ rowId + "$Highlight$txt_BatterName*GEOM*TEXT SET " + bc.getPlayer().getTicker_name() + "\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$BattingDataAll$Split$Left$BatRow" 
							+ rowId + "$Highlight$txt_PlayerRuns*GEOM*TEXT SET " + bc.getRuns() + "\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$BattingDataAll$Split$Left$BatRow" 
							+ rowId + "$Highlight$txt_PlayerBalls*GEOM*TEXT SET " + bc.getBalls() + "\0", print_writers);
					
					how_out_txt = CricketFunctions.processHowOutText("FOUR-PART-HOW-OUT", bc);
					
					if(how_out_txt != null && how_out_txt.split("|").length >= 4) {
						//System.out.println("how_out_txt = " + how_out_txt);
						
						if(bc.getHowOut().equalsIgnoreCase("run_out") || bc.getHowOut().equalsIgnoreCase("mankad")) {
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$BattingDataAll$Normal$BatRow" 
									+ rowId + containerName + "$txt_FielderName*GEOM*TEXT SET " + how_out_txt.split("\\|")[0].trim() + " " + how_out_txt.split("\\|")[1].trim().replace("substitute", "sub") 
									+ "\0", print_writers);
						}else {
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$BattingDataAll$Normal$BatRow" 
									+ rowId + containerName + "$txt_FielderName*GEOM*TEXT SET " + how_out_txt.split("\\|")[0].trim() + " " + how_out_txt.split("\\|")[1].trim().replace("substitute", "sub") 
									+ "\0", print_writers);
						}
						
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$BattingDataAll$Normal$BatRow" 
								+ rowId + containerName + "$txt_BowlerName*GEOM*TEXT SET " + how_out_txt.split("\\|")[2].trim() + " " + how_out_txt.split("\\|")[3].trim() + "\0", print_writers);
						
						if(bc.getHowOut().equalsIgnoreCase("concussed") || bc.getHowOut().equalsIgnoreCase("retired_out")) {
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$BattingDataAll$Normal$BatRow" 
									+ rowId + containerName + "$txt_FielderName*GEOM*TEXT SET " + how_out_txt.split("\\|")[0].trim() + "\0", print_writers);
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$BattingDataAll$Normal$BatRow" 
									+ rowId + containerName + "$txt_BowlerName*GEOM*TEXT SET \0", print_writers);
						}
					}else {
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$BattingDataAll$Normal$BatRow" 
								+ rowId + containerName + "$txt_FielderName*GEOM*TEXT SET " + "NOT OUT" + "\0", print_writers);
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$BattingDataAll$Normal$BatRow" 
								+ rowId + containerName + "$txt_BowlerName*GEOM*TEXT SET \0", print_writers);
					}
				}
			}
			this.numberOfRows = rowId;
			
			break;
		case Constants.TRI_SERIES: case Constants.MT20:
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide 
					+ "$select_GraphicsType*FUNCTION*Omo*vis_con SET 0\0", print_writers);
			Collections.sort(inning.getBattingCard());
			
			for(int i=1; i<=13; i++) {
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide 
						+ "$BattingCard$BatterGrp$" + i + "*ACTIVE SET 0\0", print_writers);
			}
			rowId = 0;
			
			//battingCardList = updateBattingCards(inning, CricketFunctions.getListOfImpact(matchAllData.getEventFile().getEvents(), WhichInning));
			
			Collections.sort(inning.getBattingCard());
			for (BattingCard bc : inning.getBattingCard()) {
				rowId++;
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide 
					+ "$BattingCard$BatterGrp$" + rowId + "*ACTIVE SET 1\0", print_writers);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide 
					+ "$BattingCard$BatterGrp$Rows*FUNCTION*Grid*num_row SET " + rowId + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide 
					+ "$BattingCard$DismissalGrp$Rows*FUNCTION*Grid*num_row SET " + rowId + "\0", print_writers);
				
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide 
					+ "$BattingCard$BatterGrp$Rows$" + rowId + "*ACTIVE SET 1\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide 
					+ "$BattingCard$DismissalGrp$Rows$" + rowId + "*ACTIVE SET 1\0", print_writers);
				
				switch (bc.getStatus().toUpperCase()) {
				case CricketUtil.STILL_TO_BAT:
					
					if(bc.getHowOut() == null) {
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide 
								+ "$BattingCard$BatterGrp$" + rowId + "$select_RowType*FUNCTION*Omo*vis_con SET 0\0", print_writers);
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide 
								+ "$BattingCard$DismissalGrp$" + rowId + "$select_RowType*FUNCTION*Omo*vis_con SET 0\0", print_writers);
						
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide 
								+ "$BattingCard$BatterGrp$" + rowId + "$Still_To_Bat$txt_BatterName*GEOM*TEXT SET " + bc.getPlayer().getTicker_name() + "\0", print_writers);
						
						if(!CricketFunctions.checkBatAndBallImpactInOutPlayer(matchAllData.getEventFile().getEvents(), bc.getPlayerId()).isEmpty()) {
							switch(CricketFunctions.checkBatAndBallImpactInOutPlayer(matchAllData.getEventFile().getEvents(), bc.getPlayerId())) {
							
							case "CON_OUT":
								if(bc.getBalls() == 0) {
									rowId = rowId - 1;
									this.numberOfRows = rowId;
								}
								break;
							}
						}
					}else if(bc.getHowOut().equalsIgnoreCase(CricketUtil.RETIRED_HURT)) {
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide 
								+ "$BattingCard$BatterGrp$" + rowId + "$select_RowType*FUNCTION*Omo*vis_con SET 1\0", print_writers);
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide 
								+ "$BattingCard$DismissalGrp$" + rowId + "$select_RowType*FUNCTION*Omo*vis_con SET 1\0", print_writers);
						
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide 
								+ "$BattingCard$BatterGrp$" + rowId + "$Out$Out$txt_BatterName*GEOM*TEXT SET " + bc.getPlayer().getTicker_name() + "\0", print_writers);
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide 
								+ "$BattingCard$BatterGrp$" + rowId + "$Out$Out$txt_Runs*GEOM*TEXT SET " + bc.getRuns() + "\0", print_writers);
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide 
								+ "$BattingCard$BatterGrp$" + rowId + "$Out$Out$txt_Balls*GEOM*TEXT SET " + bc.getBalls() + "\0", print_writers);
						
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide 
								+ "$BattingCard$DismissalGrp$" + rowId + "$Out$Out$How_Out_1$txt_OutType*GEOM*TEXT SET " + 
								bc.getHowOut().replace("_", " ").toLowerCase() + "\0", print_writers);
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide 
								+ "$BattingCard$DismissalGrp$" + rowId + "$Out$Out$How_Out_1$txt_FielderName*GEOM*TEXT SET \0", print_writers);
						
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide 
								+ "$BattingCard$DismissalGrp$" + rowId + "$Out$Out$How_Out_2$txt_OutType*GEOM*TEXT SET \0", print_writers);
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide 
								+ "$BattingCard$DismissalGrp$" + rowId + "$Out$Out$How_Out_2$txt_FielderName*GEOM*TEXT SET \0", print_writers);
						
					}else {
						
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide 
								+ "$BattingCard$BatterGrp$" + rowId + "$select_RowType*FUNCTION*Omo*vis_con SET 1\0", print_writers);
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide 
								+ "$BattingCard$DismissalGrp$" + rowId + "$select_RowType*FUNCTION*Omo*vis_con SET 1\0", print_writers);
						
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide 
								+ "$BattingCard$BatterGrp$" + rowId + "$Out$Out$txt_BatterName*GEOM*TEXT SET " + bc.getPlayer().getTicker_name() + "\0", print_writers);
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide 
								+ "$BattingCard$BatterGrp$" + rowId + "$Out$Out$txt_Runs*GEOM*TEXT SET " + bc.getRuns() + "\0", print_writers);
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide 
								+ "$BattingCard$BatterGrp$" + rowId + "$Out$Out$txt_Balls*GEOM*TEXT SET " + bc.getBalls() + "\0", print_writers);
						
						
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide 
								+ "$BattingCard$DismissalGrp$" + rowId + "$Out$Out$How_Out_1$txt_OutType*GEOM*TEXT SET " + 
								bc.getHowOut().replace("_", " ").toLowerCase() + "\0", print_writers);
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide 
								+ "$BattingCard$DismissalGrp$" + rowId + "$Out$Out$How_Out_1$txt_FielderName*GEOM*TEXT SET \0", print_writers);
						
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide 
								+ "$BattingCard$DismissalGrp$" + rowId + "$Out$Out$How_Out_2$txt_OutType*GEOM*TEXT SET \0", print_writers);
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide 
								+ "$BattingCard$DismissalGrp$" + rowId + "$Out$Out$How_Out_2$txt_FielderName*GEOM*TEXT SET \0", print_writers);
					}
					break;
				default:
					switch (bc.getStatus().toUpperCase()) {
					case CricketUtil.OUT:
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide 
								+ "$BattingCard$BatterGrp$" + rowId + "$select_RowType*FUNCTION*Omo*vis_con SET 1\0", print_writers);
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide 
								+ "$BattingCard$DismissalGrp$" + rowId + "$select_RowType*FUNCTION*Omo*vis_con SET 1\0", print_writers);
						containerName = "$Out$Out";
						break;
					case CricketUtil.NOT_OUT:
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide 
								+ "$BattingCard$BatterGrp$" + rowId + "$select_RowType*FUNCTION*Omo*vis_con SET 2\0", print_writers);
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide 
								+ "$BattingCard$DismissalGrp$" + rowId + "$select_RowType*FUNCTION*Omo*vis_con SET 2\0", print_writers);
						
						containerName = "$NotOut";
						break;
					}
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide 
							+ "$BattingCard$BatterGrp$" + rowId + containerName + "$txt_BatterName*GEOM*TEXT SET " + bc.getPlayer().getTicker_name() + "\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide 
							+ "$BattingCard$BatterGrp$" + rowId + containerName + "$txt_Runs*GEOM*TEXT SET " + bc.getRuns() + "\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide 
							+ "$BattingCard$BatterGrp$" + rowId + containerName + "$txt_Balls*GEOM*TEXT SET " + bc.getBalls() + "\0", print_writers);
					
					how_out_txt = CricketFunctions.processHowOutText("FOUR-PART-HOW-OUT", bc);
					
					if(how_out_txt != null && how_out_txt.split("|").length >= 4) {
						//System.out.println("how_out_txt = " + how_out_txt);
						
						if(bc.getHowOut().equalsIgnoreCase("run_out") || bc.getHowOut().equalsIgnoreCase("mankad")) {
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide 
									+ "$BattingCard$DismissalGrp$" + rowId + "$Out$Out$How_Out_1$txt_OutType*GEOM*TEXT SET " + 
									how_out_txt.split("\\|")[0].trim() + "\0", print_writers);
							
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide 
									+ "$BattingCard$DismissalGrp$" + rowId + "$Out$Out$How_Out_1$txt_FielderName*GEOM*TEXT SET "+ how_out_txt.split("\\|")[1].trim().replace("substitute", "sub") +"\0", print_writers);
						}else {
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide 
									+ "$BattingCard$DismissalGrp$" + rowId + "$Out$Out$How_Out_1$txt_OutType*GEOM*TEXT SET " + 
									how_out_txt.split("\\|")[0].trim() + "\0", print_writers);
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide 
									+ "$BattingCard$DismissalGrp$" + rowId + "$Out$Out$How_Out_1$txt_FielderName*GEOM*TEXT SET " + 
									how_out_txt.split("\\|")[1].trim().replace("substitute", "sub") + "\0", print_writers);
						}
						
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide 
								+ "$BattingCard$DismissalGrp$" + rowId + "$Out$Out$How_Out_2$txt_OutType*GEOM*TEXT SET " + 
								how_out_txt.split("\\|")[2].trim() + "\0", print_writers);
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide 
								+ "$BattingCard$DismissalGrp$" + rowId + "$Out$Out$How_Out_2$txt_FielderName*GEOM*TEXT SET " + 
								how_out_txt.split("\\|")[3].trim() + "\0", print_writers);
						
						if(bc.getHowOut().equalsIgnoreCase("concussed") || bc.getHowOut().equalsIgnoreCase("retired_out")) {
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide 
									+ "$BattingCard$DismissalGrp$" + rowId + "$Out$Out$How_Out_1$txt_OutType*GEOM*TEXT SET " + how_out_txt.split("\\|")[0].trim() 
									+ "\0", print_writers);
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide 
									+ "$BattingCard$DismissalGrp$" + rowId + "$Out$Out$How_Out_1$txt_FielderName*GEOM*TEXT SET \0", print_writers);
							
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide 
									+ "$BattingCard$DismissalGrp$" + rowId + "$Out$Out$How_Out_2$txt_OutType*GEOM*TEXT SET \0", print_writers);
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide 
									+ "$BattingCard$DismissalGrp$" + rowId + "$Out$Out$How_Out_2$txt_FielderName*GEOM*TEXT SET \0", print_writers);
						}
					}else {
//						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$AllGraphics$Side" + WhichSide 
//								+"$BattingCard$"+ rowId +containerName+"$HowOut1$txt_FielderName*GEOM*TEXT SET "+"NOT OUT"+"\0", print_writers);
					}
				}
			}
			this.numberOfRows = rowId;
			break;
		}
		return Constants.OK;
	}
	public String ScorecardBatPerformerBody(List<PrintWriter> print_writers, int WhichSide, Configuration config, MatchAllData matchAllData,Inning inning) throws Exception
	{
		switch (config.getBroadcaster()) {
		case Constants.AFG_SL_SERIES:
			rowId = 0;
			
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$select_GraphicsType"
					+ "*FUNCTION*Omo*vis_con SET 2\0", print_writers);
			Collections.sort(inning.getBattingCard());
			for (BattingCard bc : inning.getBattingCard()) {
				rowId++;
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$Split_Card$Left"
						+ "*FUNCTION*Grid*num_row SET " + rowId + "\0", print_writers);
				if(rowId == 12) {
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$Split_Card$Left"
							+ "*FUNCTION*Grid*row_offset SET " + "48" + "\0", print_writers);
				}else if(rowId == 13) {
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$Split_Card$Left"
							+ "*FUNCTION*Grid*row_offset SET " + "52" + "\0", print_writers);
				}else if(rowId == 11) {
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$Split_Card$Left"
							+ "*FUNCTION*Grid*row_offset SET " + "55" + "\0", print_writers);
				}
				
				switch (bc.getStatus().toUpperCase()) {
				case CricketUtil.STILL_TO_BAT:
					
					if(bc.getHowOut() == null) {
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$Split_Card$Left$Grp" 
								+ rowId + "$Dehighlight$select_DataType*FUNCTION*Omo*vis_con SET 0\0", print_writers);
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$Split_Card$Left$Grp" 
								+ rowId + "$Dehighlight$StillToBat$txt_BatterName*GEOM*TEXT SET " + bc.getPlayer().getTicker_name() + "\0", print_writers);

						if(!matchAllData.getMatch().getSourceOfThisMatchData().equalsIgnoreCase("WEBSITE")) {
							if(!CricketFunctions.checkBatAndBallImpactInOutPlayer(matchAllData.getEventFile().getEvents(), bc.getPlayerId()).isEmpty()) {
								switch(CricketFunctions.checkBatAndBallImpactInOutPlayer(matchAllData.getEventFile().getEvents(), bc.getPlayerId())) {
								case "CON_OUT":
									if(bc.getBalls() == 0) {
										rowId = rowId - 1;
										this.numberOfRows = rowId;
									}
									break;
								}
							}
						}
						
					}else {
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$Split_Card$Left$Grp" 
								+ rowId + "$Dehighlight$select_DataType*FUNCTION*Omo*vis_con SET 1\0", print_writers);
						
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$Split_Card$Left$Grp" 
								+ rowId + "$Dehighlight$Out$txt_BatterName*GEOM*TEXT SET " + bc.getPlayer().getTicker_name() + "\0", print_writers);
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$Split_Card$Left$Grp" 
								+ rowId + "$Dehighlight$Out$txt_Scores*GEOM*TEXT SET " + bc.getRuns() + "\0", print_writers);
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$Split_Card$Left$Grp" 
								+ rowId + "$Dehighlight$Out$txt_Balls*GEOM*TEXT SET " + bc.getBalls() + "\0", print_writers);
					}
					break;
				default:
					switch (bc.getStatus().toUpperCase()) {
					case CricketUtil.OUT:
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$Split_Card$Left$Grp" 
								+ rowId + "$Dehighlight$select_DataType*FUNCTION*Omo*vis_con SET 1\0", print_writers);
						
						containerName = "$Dehighlight$Out";
						break;
					case CricketUtil.NOT_OUT:
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$Split_Card$Left$Grp" 
								+ rowId + "$Dehighlight$select_DataType*FUNCTION*Omo*vis_con SET 2\0", print_writers);
						
						containerName = "$Dehighlight$NotOut";
						break;
					}
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$Split_Card$Left$Grp" 
							+ rowId + containerName + "$txt_BatterName*GEOM*TEXT SET " + bc.getPlayer().getTicker_name() + "\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$Split_Card$Left$Grp" 
							+ rowId + containerName + "$txt_Scores*GEOM*TEXT SET " + bc.getRuns() + "\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$Split_Card$Left$Grp" 
							+ rowId + containerName + "$txt_Balls*GEOM*TEXT SET " + bc.getBalls() + "\0", print_writers);
				}
			}
			
			switch (WhichType.toUpperCase()) {
			case "PERFORMER":
				BattingCard bc = inning.getBattingCard().stream().filter(bc1 -> bc1.getPlayerId() == FirstPlayerId).findAny().orElse(null);
				player = Players.stream().filter(plyr -> plyr.getPlayerId() == FirstPlayerId).findAny().orElse(null);
				fullframes.setHighlight(bc.getBatterPosition());
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$Split_Card$Right$select_DataType"
						+ "*FUNCTION*Omo*vis_con SET 2\0", print_writers);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$Split_Card$Left$Grp" 
						+ bc.getBatterPosition() + "$Highlight$txt_BatterName*GEOM*TEXT SET " + bc.getPlayer().getTicker_name() + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$Split_Card$Left$Grp" 
						+ bc.getBatterPosition() + "$Highlight$txt_Scores*GEOM*TEXT SET " + bc.getRuns() + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$Split_Card$Left$Grp" 
						+ bc.getBatterPosition() + "$Highlight$txt_Balls*GEOM*TEXT SET " + bc.getBalls() + "\0", print_writers);
				
				if(player.getSurname() != null) {
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$Split_Card$Right$HighestScorer$Name"
							+ "$txt_FirstName*GEOM*TEXT SET " + player.getFirstname() + "\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$Split_Card$Right$HighestScorer$Name"
							+ "$txt_LastName*GEOM*TEXT SET " + player.getSurname() + "\0", print_writers);
				}else {
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$Split_Card$Right$HighestScorer$Name"
							+ "$txt_FirstName*GEOM*TEXT SET \0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$Split_Card$Right$HighestScorer$Name"
							+ "$txt_LastName*GEOM*TEXT SET " + player.getFirstname() + "\0", print_writers);
				}
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$Split_Card$HighestScorer$Stats$4s$txt_Statvalue"
						+ "*GEOM*TEXT SET " + bc.getFours() + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$Split_Card$HighestScorer$Stats$6s$txt_Statvalue"
						+ "*GEOM*TEXT SET " + bc.getSixes() + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$Split_Card$HighestScorer$Stats$StrikeRate"
						+ "$txt_Statvalue*GEOM*TEXT SET " + CricketFunctions.generateStrikeRate(bc.getRuns(), bc.getBalls(), 0) + "\0", print_writers);
				
				if(config.getPrimaryIpAddress().equalsIgnoreCase(Constants.LOCALHOST)) {
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$Split_Card$HighestScorer$ImageGrp"
							+ "$img_PlayerShadow*TEXTURE*IMAGE SET "+Constants.BAN_AFG_SERIES_LOCAL_PHOTO_PATH +inning.getBatting_team().getTeamName4()+"\\\\" +  Constants.RIGHT 
							+ "\\\\" + player.getPhoto() + CricketUtil.PNG_EXTENSION+"\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$Split_Card$HighestScorer$ImageGrp"
							+ "$img_Player*TEXTURE*IMAGE SET "+Constants.BAN_AFG_SERIES_LOCAL_PHOTO_PATH +inning.getBatting_team().getTeamName4()+"\\\\" +  Constants.RIGHT + "\\\\" 
							+ player.getPhoto()+ CricketUtil.PNG_EXTENSION+"\0", print_writers);
				}else {
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$Split_Card$HighestScorer$ImageGrp"
							+ "$img_PlayerShadow*TEXTURE*IMAGE SET "+ "\\\\" + config.getPrimaryIpAddress()+Constants.BAN_AFG_SERIES_PHOTO_PATH +inning.getBatting_team().getTeamName4() 
							+ "\\\\" +  Constants.RIGHT + "\\\\" + player.getPhoto() + CricketUtil.PNG_EXTENSION+"\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$Split_Card$HighestScorer$ImageGrp"
							+ "$img_PlayerShadow*TEXTURE*IMAGE SET "+ "\\\\" + config.getPrimaryIpAddress()+Constants.BAN_AFG_SERIES_PHOTO_PATH +inning.getBatting_team().getTeamName4() 
							+ "\\\\" +  Constants.RIGHT + "\\\\" + player.getPhoto() + CricketUtil.PNG_EXTENSION+"\0", print_writers);
				}
				
				
				break;

			case "PARTNERSHIP":
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$Split_Card$Right$select_DataType"
						+ "*FUNCTION*Omo*vis_con SET 3\0", print_writers);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$Split_Card$Right$Partnership$Header"
						+ "$txt_Header1*GEOM*TEXT SET " + CricketFunctions.ordinal(inning.getPartnerships().get(inning.getPartnerships().size()-1).getPartnershipNumber()) 
						+ " WICKET" + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$Split_Card$Right$Partnership$Header"
						+ "$txt_Header2*GEOM*TEXT SET " + "PARTNERSHIP" + "\0", print_writers);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$Split_Card$Right$Partnership$Score"
						+ "$txt_Runs*GEOM*TEXT SET " + inning.getPartnerships().get(inning.getPartnerships().size()-1).getTotalRuns() + "*" + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$Split_Card$Right$Partnership$Score"
						+ "$txt_Balls*GEOM*TEXT SET " + "OFF " + inning.getPartnerships().get(inning.getPartnerships().size()-1).getTotalBalls() + "\0", print_writers);
				
				ScaleFac1 = ((double) inning.getPartnerships().get(inning.getPartnerships().size()-1).getFirstBatterRuns()) / 
						inning.getPartnerships().get(inning.getPartnerships().size()-1).getTotalRuns();
				ScaleFac2 = ((double) inning.getPartnerships().get(inning.getPartnerships().size()-1).getSecondBatterRuns()) / 
						inning.getPartnerships().get(inning.getPartnerships().size()-1).getTotalRuns();
				
				ScaleFac1 = ScaleFac1*360;
				ScaleFac2 = ScaleFac2*360;
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$Split_Card$Partnership$Score$PieAll$Player1$"
						+ "obj_RingAngle*GEOM*Angle SET " + ScaleFac1 + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$Split_Card$Partnership$Score$PieAll$Player1$"
						+ "obj_RingAngle*GEOM*Rotation SET " + (200-ScaleFac1) + "\0", print_writers);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$Split_Card$Partnership$Score$PieAll$Player2$"
						+ "obj_RingAngle*GEOM*Angle SET " + ScaleFac2 + "\0", print_writers);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$Split_Card$Partnership$Stats$4s$txt_Statvalue"
						+ "*GEOM*TEXT SET " + inning.getPartnerships().get(inning.getPartnerships().size()-1).getTotalFours() + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$Split_Card$Partnership$Stats$6s$txt_Statvalue"
						+ "*GEOM*TEXT SET " + inning.getPartnerships().get(inning.getPartnerships().size()-1).getTotalSixes() + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$Split_Card$Partnership$Stats$StrikeRate$txt_Statvalue"
						+ "*GEOM*TEXT SET " + CricketFunctions.generateStrikeRate(inning.getPartnerships().get(inning.getPartnerships().size()-1).getTotalRuns(), 
								inning.getPartnerships().get(inning.getPartnerships().size()-1).getTotalBalls(), 0) + "\0", print_writers);
				
				if(config.getPrimaryIpAddress().equalsIgnoreCase(Constants.LOCALHOST)) {
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$Split_Card$Partnership$ImageGrp$Player1"
							+ "$img_PlayerShadow*TEXTURE*IMAGE SET "+Constants.BAN_AFG_SERIES_LOCAL_PHOTO_PATH +inning.getBatting_team().getTeamName4()+"\\\\" +  Constants.RIGHT 
							+ "\\\\" + inning.getPartnerships().get(inning.getPartnerships().size()-1).getFirstPlayer().getPhoto()+ CricketUtil.PNG_EXTENSION+"\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$Split_Card$Partnership$ImageGrp$Player1"
							+ "$img_Player*TEXTURE*IMAGE SET "+Constants.BAN_AFG_SERIES_LOCAL_PHOTO_PATH +inning.getBatting_team().getTeamName4()+"\\\\" +  Constants.RIGHT + "\\\\" 
							+ inning.getPartnerships().get(inning.getPartnerships().size()-1).getFirstPlayer().getPhoto()+ CricketUtil.PNG_EXTENSION+"\0", print_writers);
				}else {
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$Split_Card$Partnership$ImageGrp$Player1"
							+ "$img_PlayerShadow*TEXTURE*IMAGE SET "+ "\\\\" + config.getPrimaryIpAddress()+Constants.BAN_AFG_SERIES_PHOTO_PATH +inning.getBatting_team().getTeamName4() 
							+ "\\\\" +  Constants.RIGHT + "\\\\" + inning.getPartnerships().get(inning.getPartnerships().size()-1).getFirstPlayer().getPhoto() 
							+ CricketUtil.PNG_EXTENSION+"\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$Split_Card$Partnership$ImageGrp$Player1"
							+ "$img_PlayerShadow*TEXTURE*IMAGE SET "+ "\\\\" + config.getPrimaryIpAddress()+Constants.BAN_AFG_SERIES_PHOTO_PATH +inning.getBatting_team().getTeamName4() 
							+ "\\\\" +  Constants.RIGHT + "\\\\" + inning.getPartnerships().get(inning.getPartnerships().size()-1).getFirstPlayer().getPhoto() 
							+ CricketUtil.PNG_EXTENSION+"\0", print_writers);
				}
				
				if(config.getPrimaryIpAddress().equalsIgnoreCase(Constants.LOCALHOST)) {
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$Split_Card$Partnership$ImageGrp$Player2"
							+ "$img_PlayerShadow*TEXTURE*IMAGE SET "+Constants.BAN_AFG_SERIES_LOCAL_PHOTO_PATH +inning.getBatting_team().getTeamName4()+"\\\\" +  Constants.RIGHT + "\\\\" 
							+ inning.getPartnerships().get(inning.getPartnerships().size()-1).getSecondPlayer().getPhoto()+ CricketUtil.PNG_EXTENSION+"\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$Split_Card$Partnership$ImageGrp$Player2"
							+ "$img_Player*TEXTURE*IMAGE SET "+Constants.BAN_AFG_SERIES_LOCAL_PHOTO_PATH +inning.getBatting_team().getTeamName4()+"\\\\" +  Constants.RIGHT + "\\\\" 
							+ inning.getPartnerships().get(inning.getPartnerships().size()-1).getSecondPlayer().getPhoto()+ CricketUtil.PNG_EXTENSION+"\0", print_writers);
				}else {
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$Split_Card$Partnership$ImageGrp$Player2"
							+ "$img_PlayerShadow*TEXTURE*IMAGE SET "+ "\\\\" + config.getPrimaryIpAddress()+Constants.BAN_AFG_SERIES_PHOTO_PATH +inning.getBatting_team().getTeamName4() 
							+ "\\\\" +  Constants.RIGHT + "\\\\" + inning.getPartnerships().get(inning.getPartnerships().size()-1).getSecondPlayer().getPhoto() 
							+ CricketUtil.PNG_EXTENSION+"\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$Split_Card$Partnership$ImageGrp$Player2"
							+ "$img_PlayerShadow*TEXTURE*IMAGE SET "+ "\\\\" + config.getPrimaryIpAddress()+Constants.BAN_AFG_SERIES_PHOTO_PATH +inning.getBatting_team().getTeamName4() 
							+ "\\\\" +  Constants.RIGHT + "\\\\" + inning.getPartnerships().get(inning.getPartnerships().size()-1).getSecondPlayer().getPhoto() 
							+ CricketUtil.PNG_EXTENSION+"\0", print_writers);
				}
				break;
			}
			
			break;
		case Constants.TRI_SERIES: case Constants.MT20:
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide 
					+ "$select_GraphicsType*FUNCTION*Omo*vis_con SET 13\0", print_writers);
			Collections.sort(inning.getBattingCard());
			for(int i=1; i<=13; i++) {
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide + "$BattingCardExtra"
						+ "$BatterExtraGrp$" + i + "*ACTIVE SET 0\0", print_writers);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide + "$BattingCardExtra$BatterExtraGrp$"
						+ i + "$select_Colour$img_Base1*TEXTURE*IMAGE SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_BASE1 : Constants.MT20_BASE1) + inning.getBatting_team().getTeamBadge() + "\0", print_writers);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide + "$BattingCardExtra$BatterExtraGrp$"
						+ i + "$select_Colour$img_Base2*TEXTURE*IMAGE SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_BASE2 : Constants.MT20_BASE2) + inning.getBatting_team().getTeamBadge() + "\0", print_writers);
				
			}
			rowId = 0;
			
			Collections.sort(inning.getBattingCard());
			for (BattingCard bc : inning.getBattingCard()) {
				rowId++;
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide + "$BattingCardExtra"
						+ "$BatterExtraGrp$" + rowId + "*ACTIVE SET 1\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide + "$BattingCardExtra"
						+ "$BatterExtraGrp$Rows*FUNCTION*Grid*num_row SET " + rowId + "\0", print_writers);
				
				switch (bc.getStatus().toUpperCase()) {
				case CricketUtil.STILL_TO_BAT:
					
					if(bc.getHowOut() == null) {
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide + "$BattingCardExtra"
								+ "$BatterExtraGrp$" + rowId + "$select_RowType*FUNCTION*Omo*vis_con SET 0\0", print_writers);
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide + "$BattingCardExtra"
								+ "$BatterExtraGrp$" + rowId + "$Still_To_Bat$txt_BatterName*GEOM*TEXT SET " + bc.getPlayer().getTicker_name() + "\0", print_writers);
						
						if(!CricketFunctions.checkBatAndBallImpactInOutPlayer(matchAllData.getEventFile().getEvents(), bc.getPlayerId()).isEmpty()) {
							switch(CricketFunctions.checkBatAndBallImpactInOutPlayer(matchAllData.getEventFile().getEvents(), bc.getPlayerId())) {
							
							case "CON_OUT":
								if(bc.getBalls() == 0) {
									rowId = rowId - 1;
									this.numberOfRows = rowId;
								}
								break;
							}
						}
					}else if(bc.getHowOut().equalsIgnoreCase(CricketUtil.RETIRED_HURT)) {
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide + "$BattingCardExtra"
								+ "$BatterExtraGrp$" + rowId + "$select_RowType*FUNCTION*Omo*vis_con SET 1\0", print_writers);
						
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide + "$BattingCardExtra"
								+ "$BatterExtraGrp$" + rowId + "$Out$Out$txt_BatterName*GEOM*TEXT SET " + bc.getPlayer().getTicker_name() + "\0", print_writers);
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide + "$BattingCardExtra"
								+ "$BatterExtraGrp$" + rowId + "$Out$Out$txt_Runs*GEOM*TEXT SET " + bc.getRuns() + "\0", print_writers);
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide + "$BattingCardExtra"
								+ "$BatterExtraGrp$" + rowId + "$Out$Out$txt_Balls*GEOM*TEXT SET " + bc.getBalls() + "\0", print_writers);
						
					}else {
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide + "$BattingCardExtra"
								+ "$BatterExtraGrp$" + rowId + "$select_RowType*FUNCTION*Omo*vis_con SET 1\0", print_writers);
						
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide + "$BattingCardExtra"
								+ "$BatterExtraGrp$" + rowId + "$Out$Out$txt_BatterName*GEOM*TEXT SET " + bc.getPlayer().getTicker_name() + "\0", print_writers);
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide + "$BattingCardExtra"
								+ "$BatterExtraGrp$" + rowId + "$Out$Out$txt_Runs*GEOM*TEXT SET " + bc.getRuns() + "\0", print_writers);
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide + "$BattingCardExtra"
								+ "$BatterExtraGrp$" + rowId + "$Out$Out$txt_Balls*GEOM*TEXT SET " + bc.getBalls() + "\0", print_writers);
					}
					break;
				default:
					switch (bc.getStatus().toUpperCase()) {
					case CricketUtil.OUT:
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide + "$BattingCardExtra"
								+ "$BatterExtraGrp$" + rowId + "$select_RowType*FUNCTION*Omo*vis_con SET 1\0", print_writers);
						containerName = "$Out$Out";
						break;
					case CricketUtil.NOT_OUT:
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide + "$BattingCardExtra"
								+ "$BatterExtraGrp$" + rowId + "$select_RowType*FUNCTION*Omo*vis_con SET 2\0", print_writers);
						
						containerName = "$NotOut";
						break;
					}
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide + "$BattingCardExtra"
							+ "$BatterExtraGrp$" + rowId + containerName + "$txt_BatterName*GEOM*TEXT SET " + bc.getPlayer().getTicker_name() + "\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide + "$BattingCardExtra"
							+ "$BatterExtraGrp$" + rowId + containerName + "$txt_Runs*GEOM*TEXT SET " + bc.getRuns() + "\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide + "$BattingCardExtra"
							+ "$BatterExtraGrp$" + rowId + containerName + "$txt_Balls*GEOM*TEXT SET " + bc.getBalls() + "\0", print_writers);
				}
			}
			
			switch (WhichType.toUpperCase()) {
			case "PERFORMER":
				BattingCard bc = inning.getBattingCard().stream().filter(bc1 -> bc1.getPlayerId() == FirstPlayerId).findAny().orElse(null);
				player = Players.stream().filter(plyr -> plyr.getPlayerId() == FirstPlayerId).findAny().orElse(null);
				
				String[] Count = CricketFunctions.getScoreTypeData(CricketUtil.BATSMAN,matchAllData, inning.getInningNumber(), player.getPlayerId(),
						"-", matchAllData.getEventFile().getEvents()).split("-");
				
				fullframes.setHighlight(bc.getBatterPosition());
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide + "$BattingCardExtra$BatterExtraGrp$"
						+ fullframes.getHighlight() + "$select_Colour*FUNCTION*Omo*vis_con SET 2\0", print_writers);
				
				if(config.getPrimaryIpAddress().equalsIgnoreCase(Constants.LOCALHOST)) {
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide + "$BattingCardExtra"
							+ "$BestPerformer$img_PlayerShadow*TEXTURE*IMAGE SET "+(config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_PHOTO_PATH : Constants.MT20_PHOTO_PATH)+ inning.getBatting_team().getTeamName4() + "\\\\" 
							+ Constants.RIGHT + "\\\\" + player.getPhoto()+CricketUtil.PNG_EXTENSION + "\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide + "$BattingCardExtra"
							+ "$BestPerformer$img_Player*TEXTURE*IMAGE SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_PHOTO_PATH : Constants.MT20_PHOTO_PATH) + inning.getBatting_team().getTeamName4()+ "\\\\" + Constants.RIGHT 
							+ "\\\\"  + player.getPhoto()+CricketUtil.PNG_EXTENSION+"\0", print_writers);
				}else {
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide + "$BattingCardExtra"
							+ "$BestPerformer$img_PlayerShadow*TEXTURE*IMAGE SET " + "\\\\" + config.getPrimaryIpAddress() + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_PHOTO_PATH : Constants.MT20_PHOTO_PATH) +
							inning.getBatting_team().getTeamName4() + "\\\\" +  Constants.RIGHT + "\\\\"  + player.getPhoto()+CricketUtil.PNG_EXTENSION + "\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide + "$BattingCardExtra"
							+ "$BestPerformer$img_Player*TEXTURE*IMAGE SET " + "\\\\" + config.getPrimaryIpAddress() + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_PHOTO_PATH : Constants.MT20_PHOTO_PATH) + 
							inning.getBatting_team().getTeamName4()+ "\\\\" +  Constants.RIGHT + "\\\\" + player.getPhoto()+CricketUtil.PNG_EXTENSION + "\0", print_writers);
				}
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide + "$BattingCardExtra"
						+ "$BestPerformer$DetailData*FUNCTION*Grid*num_row SET 6\0", print_writers);
				for(int i =1;i<=6; i++) {
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide + "$BattingCardExtra"
							+ "$BestPerformer$DetailData$" + i + "$select_Highlight*FUNCTION*Omo*vis_con SET 0\0", print_writers);
				}
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide + "$BattingCardExtra"
						+ "$BestPerformer$img_Logo*TEXTURE*IMAGE SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_LOGO : Constants.MT20_LOGO) + inning.getBatting_team().getTeamBadge() + "\0", print_writers);
				
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide + "$BattingCardExtra"
						+ "$BestPerformer$DetailData$1$Dehighlight$txt_StatHead*GEOM*TEXT SET DOTS\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide + "$BattingCardExtra"
						+ "$BestPerformer$DetailData$2$Dehighlight$txt_StatHead*GEOM*TEXT SET ONES\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide + "$BattingCardExtra"
						+ "$BestPerformer$DetailData$3$Dehighlight$txt_StatHead*GEOM*TEXT SET TWOS\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide + "$BattingCardExtra"
						+ "$BestPerformer$DetailData$4$Dehighlight$txt_StatHead*GEOM*TEXT SET THREES\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide + "$BattingCardExtra"
						+ "$BestPerformer$DetailData$5$Dehighlight$txt_StatHead*GEOM*TEXT SET FOURS\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide + "$BattingCardExtra"
						+ "$BestPerformer$DetailData$6$Dehighlight$txt_StatHead*GEOM*TEXT SET SIXES\0", print_writers);
				
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide + "$BattingCardExtra"
						+ "$BestPerformer$DetailData$1$Dehighlight$txt_StatValue*GEOM*TEXT SET " + Count[0] + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide + "$BattingCardExtra"
						+ "$BestPerformer$DetailData$2$Dehighlight$txt_StatValue*GEOM*TEXT SET " + Count[1] + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide + "$BattingCardExtra"
						+ "$BestPerformer$DetailData$3$Dehighlight$txt_StatValue*GEOM*TEXT SET " + Count[2] + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide + "$BattingCardExtra"
						+ "$BestPerformer$DetailData$4$Dehighlight$txt_StatValue*GEOM*TEXT SET " + Count[3] + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide + "$BattingCardExtra"
						+ "$BestPerformer$DetailData$5$Dehighlight$txt_StatValue*GEOM*TEXT SET " + Count[4] + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide + "$BattingCardExtra"
						+ "$BestPerformer$DetailData$6$Dehighlight$txt_StatValue*GEOM*TEXT SET " + Count[6] + "\0", print_writers);
				
				if(player.getSurname() != null) {
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide + "$BattingCardExtra"
							+ "$BestPerformer$txt_FirstName*GEOM*TEXT SET "+player.getFirstname()+"\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide + "$BattingCardExtra"
							+ "$BestPerformer$txt_LastName*GEOM*TEXT SET "+player.getSurname()+"\0", print_writers);
				}else {
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide + "$BattingCardExtra"
							+ "$BestPerformer$txt_FirstName*GEOM*TEXT SET "+player.getFull_name()+"\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide + "$BattingCardExtra"
							+ "$BestPerformer$txt_LastName*GEOM*TEXT SET \0", print_writers);
				}
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide + "$BattingCardExtra"
						+ "$BestPerformer$Data$txt_StatHead*GEOM*TEXT SET STRIKE RATE \0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide + "$BattingCardExtra"
						+ "$BestPerformer$Data$txt_StatValue*GEOM*TEXT SET "+CricketFunctions.generateStrikeRate(bc.getRuns(), bc.getBalls(), 0)+"\0", print_writers);
				break;

			case "PARTNERSHIP":
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$ExtraData$Side" + WhichSide + "$select_Graphics"
						+ "*FUNCTION*Omo*vis_con SET 1\0", print_writers);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$ExtraData$Side" + WhichSide + "$Partnership$Title$txt_Title1"
						+ "*GEOM*TEXT SET "+CricketFunctions.ordinal(inning.getPartnerships().get(inning.getPartnerships().size()-1).getPartnershipNumber())+" WICKET"+"\0", print_writers); 
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$ExtraData$Side" + WhichSide + "$Partnership$Title$txt_Title2"
						+ "*GEOM*TEXT SET PARTNERSHIP\0", print_writers);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$ExtraData$Side" + WhichSide + "$Partnership$Data$txt_Runs"
						+ "*GEOM*TEXT SET "+inning.getPartnerships().get(inning.getPartnerships().size()-1).getTotalRuns()+"\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$ExtraData$Side" + WhichSide + "$Partnership$Data$txt_Balls"
						+ "*GEOM*TEXT SET "+inning.getPartnerships().get(inning.getPartnerships().size()-1).getTotalBalls()+"\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$ExtraData$Side" + WhichSide + "$Partnership$Data$txt_BallsHead"
						+ "*GEOM*TEXT SET BALL" + CricketFunctions.Plural(inning.getPartnerships().get(inning.getPartnerships().size()-1).getTotalBalls()).toUpperCase()+"\0", print_writers);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$ExtraData$Side" + WhichSide + "$Partnership$img_Logo"
						+ "*TEXTURE*IMAGE SET "+(config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_LOGO : Constants.MT20_LOGO) + inning.getBatting_team().getTeamBadge() + "\0", print_writers);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$ExtraData$Side" + WhichSide + "$Partnership$PieAll$img_Base1"
						+ "*TEXTURE*IMAGE SET "+(config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_BASE1 : Constants.MT20_BASE1) + inning.getBatting_team().getTeamBadge() + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$ExtraData$Side" + WhichSide + "$Partnership$PieAll$img_Base2"
						+ "*TEXTURE*IMAGE SET "+(config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_BASE2 : Constants.MT20_BASE2) + inning.getBatting_team().getTeamBadge() + "\0", print_writers);
				
				if(config.getPrimaryIpAddress().equalsIgnoreCase(Constants.LOCALHOST)) {
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$ExtraData$Side" + WhichSide + "$Partnership$Image1$img_PlayerShadow"
							+ "*TEXTURE*IMAGE SET "+(config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_LOCAL_PHOTO_PATH : Constants.MT20_LOCAL_PHOTO_PATH) +inning.getBatting_team().getTeamName4()+"\\\\" +  Constants.RIGHT + "\\\\" +
							inning.getPartnerships().get(inning.getPartnerships().size()-1).getFirstPlayer().getPhoto()+ CricketUtil.PNG_EXTENSION+"\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$ExtraData$Side" + WhichSide + "$Partnership$Image1$img_Player"
							+ "*TEXTURE*IMAGE SET "+(config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_LOCAL_PHOTO_PATH : Constants.MT20_LOCAL_PHOTO_PATH) +inning.getBatting_team().getTeamName4() + "\\\\" +  Constants.RIGHT + "\\\\" +
							inning.getPartnerships().get(inning.getPartnerships().size()-1).getFirstPlayer().getPhoto()+ CricketUtil.PNG_EXTENSION+"\0", print_writers);
				}else {
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$ExtraData$Side" + WhichSide + "$Partnership$Image1$img_PlayerShadow"
							+ "*TEXTURE*IMAGE SET "+ "\\\\" + config.getPrimaryIpAddress()+(config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_PHOTO_PATH : Constants.MT20_PHOTO_PATH) +inning.getBatting_team().getTeamName4() + "\\\\" +  Constants.RIGHT + "\\\\" +
							inning.getPartnerships().get(inning.getPartnerships().size()-1).getFirstPlayer().getPhoto()+ CricketUtil.PNG_EXTENSION+"\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$ExtraData$Side" + WhichSide + "$Partnership$Image1$img_Player"
							+ "*TEXTURE*IMAGE SET "+ "\\\\" + config.getPrimaryIpAddress()+(config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_PHOTO_PATH : Constants.MT20_PHOTO_PATH) +inning.getBatting_team().getTeamName4() + "\\\\" +  Constants.RIGHT + "\\\\" +
							inning.getPartnerships().get(inning.getPartnerships().size()-1).getFirstPlayer().getPhoto()+ CricketUtil.PNG_EXTENSION+"\0", print_writers);
				}
				if(config.getPrimaryIpAddress().equalsIgnoreCase(Constants.LOCALHOST)) {
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$ExtraData$Side" + WhichSide + "$Partnership$Image2$img_PlayerShadow"
							+ "*TEXTURE*IMAGE SET "+(config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_LOCAL_PHOTO_PATH : Constants.MT20_LOCAL_PHOTO_PATH) +inning.getBatting_team().getTeamName4() + "\\\\" +  Constants.RIGHT + "\\\\" +
							inning.getPartnerships().get(inning.getPartnerships().size()-1).getSecondPlayer().getPhoto()+ CricketUtil.PNG_EXTENSION+"\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$ExtraData$Side" + WhichSide + "$Partnership$Image2$img_Player"
							+ "*TEXTURE*IMAGE SET "+(config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_LOCAL_PHOTO_PATH : Constants.MT20_LOCAL_PHOTO_PATH) +inning.getBatting_team().getTeamName4() + "\\\\" +  Constants.RIGHT + "\\\\" +
							inning.getPartnerships().get(inning.getPartnerships().size()-1).getSecondPlayer().getPhoto()+ CricketUtil.PNG_EXTENSION+"\0", print_writers);
				}else {
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$ExtraData$Side" + WhichSide + "$Partnership$Image2$img_PlayerShadow"
							+ "*TEXTURE*IMAGE SET "+ "\\\\" + config.getPrimaryIpAddress()+(config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_LOCAL_PHOTO_PATH : Constants.MT20_LOCAL_PHOTO_PATH) +inning.getBatting_team().getTeamName4() + "\\\\" +  Constants.RIGHT + "\\\\" +
							inning.getPartnerships().get(inning.getPartnerships().size()-1).getSecondPlayer().getPhoto()+ CricketUtil.PNG_EXTENSION+"\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$ExtraData$Side" + WhichSide + "$Partnership$Image2$img_Player"
							+ "*TEXTURE*IMAGE SET "+ "\\\\" + config.getPrimaryIpAddress()+(config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_LOCAL_PHOTO_PATH : Constants.MT20_LOCAL_PHOTO_PATH) +inning.getBatting_team().getTeamName4() + "\\\\" +  Constants.RIGHT + "\\\\" +
							inning.getPartnerships().get(inning.getPartnerships().size()-1).getSecondPlayer().getPhoto()+ CricketUtil.PNG_EXTENSION+"\0", print_writers);
				}
				break;
			}
			
			this.numberOfRows = rowId;
			break;
		}
		return Constants.OK;
	}
	public String BatPerformerBody(List<PrintWriter> print_writers, int WhichSide, Configuration config, MatchAllData matchAllData,Inning inning, CricketService cricketService) throws Exception
	{
		switch (config.getBroadcaster()) {
		case Constants.BAN_AFG_SERIES: case Constants.ACC:
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$BattingCard$BattingDataAll$Select_Type*FUNCTION*Omo*vis_con SET 1\0", print_writers);
			switch (WhichType.toUpperCase()) {
			case "PERFORMER":
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$BattingDataAll$Split$Right$Select_Type*FUNCTION*Omo*vis_con SET 0\0", print_writers);
				
				fullframes.setWhichhighlight("PERFORMER");
				BattingCard bc = inning.getBattingCard().stream().filter(bc1 -> bc1.getPlayerId() == FirstPlayerId).findAny().orElse(null);
				player = Players.stream().filter(plyr -> plyr.getPlayerId() == FirstPlayerId).findAny().orElse(null);
				
				String[] Count = CricketFunctions.getScoreTypeData(CricketUtil.BATSMAN,matchAllData, inning.getInningNumber(), player.getPlayerId(),
						"-", matchAllData.getEventFile().getEvents()).split("-");
				
				fullframes.setHighlight(bc.getBatterPosition());
				
				switch (config.getBroadcaster()) {
				case Constants.BAN_AFG_SERIES:
					if(config.getPrimaryIpAddress().equalsIgnoreCase(Constants.LOCALHOST)) {
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$BattingDataAll$Split$Right$HighestScore$img_Player*TEXTURE*IMAGE SET " 
								+ Constants.BAN_AFG_SERIES_LOCAL_PHOTO_PATH + inning.getBatting_team().getTeamName4() + "\\\\" + Constants.CENTER + "\\\\" + player.getPhoto()+CricketUtil.PNG_EXTENSION 
								+ "\0", print_writers);
					}else {
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$BattingDataAll$Split$Right$HighestScore$img_Player*TEXTURE*IMAGE SET " 
								+ "\\\\" + config.getPrimaryIpAddress() + Constants.BAN_AFG_SERIES_PHOTO_PATH + inning.getBatting_team().getTeamName4() + "\\\\" + Constants.CENTER + "\\\\" + player.getPhoto() 
								+ CricketUtil.PNG_EXTENSION + "\0", print_writers);
					}
					break;
				case Constants.ACC:
					if(config.getPrimaryIpAddress().equalsIgnoreCase(Constants.LOCALHOST)) {
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$BattingDataAll$Split$Right$HighestScore$img_Player*TEXTURE*IMAGE SET " 
								+ Constants.ACC_LOCAL_PHOTO_PATH + inning.getBatting_team().getTeamBadge() + "\\\\" + Constants.CENTER + "\\\\" + player.getPhoto()+CricketUtil.PNG_EXTENSION 
								+ "\0", print_writers);
					}else {
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$BattingDataAll$Split$Right$HighestScore$img_Player*TEXTURE*IMAGE SET " 
								+ "\\\\" + config.getPrimaryIpAddress() + Constants.ACC_PHOTO_PATH + inning.getBatting_team().getTeamBadge() + "\\\\" + Constants.CENTER + "\\\\" + player.getPhoto() 
								+ CricketUtil.PNG_EXTENSION + "\0", print_writers);
					}
					break;	
				}
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$BattingDataAll$Split$Right$HighestScore$txt_PlayerName"
						+ "*GEOM*TEXT SET " + bc.getPlayer().getFull_name() + "\0", print_writers);
				
				if(matchAllData.getSetup().getMatchType().equalsIgnoreCase(CricketUtil.ODI) || 
						matchAllData.getSetup().getMatchType().equalsIgnoreCase(CricketUtil.OD)) {
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$BattingDataAll$Split$Right$HighestScore$txt_StatHead"
							+ "*GEOM*TEXT SET FOURS/SIXES \0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$BattingDataAll$Split$Right$HighestScore$txt_StatValue"
							+ "*GEOM*TEXT SET " + Count[4] + "/" + Count[6] +"\0", print_writers);
				}else {
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$BattingDataAll$Split$Right$HighestScore$txt_StatHead"
							+ "*GEOM*TEXT SET STRIKE RATE \0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$BattingDataAll$Split$Right$HighestScore$txt_StatValue"
							+ "*GEOM*TEXT SET "+CricketFunctions.generateStrikeRate(bc.getRuns(), bc.getBalls(), 0)+"\0", print_writers);
				}
				
				break;
			case "PARTNERSHIP":
				fullframes.setWhichhighlight("PARTNERSHIP");
				
				BattingCard batcard1 = inning.getBattingCard().stream().filter(bc1 -> bc1.getPlayerId() == inning.getPartnerships().get(inning.getPartnerships().size()-1).getFirstPlayer().getPlayerId()).findAny().orElse(null);
				BattingCard batcard2 = inning.getBattingCard().stream().filter(bc1 -> bc1.getPlayerId() == inning.getPartnerships().get(inning.getPartnerships().size()-1).getSecondPlayer().getPlayerId()).findAny().orElse(null);
				
				fullframes.setHighlight(batcard1.getBatterPosition());
				fullframes.setSecondHighlight(batcard2.getBatterPosition());
				
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$BattingDataAll$Split$Right$Select_Type*FUNCTION*Omo*vis_con SET 1\0", print_writers);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$BattingDataAll$Split$Right$BatPartnership$txt_BatPartnershipHead"
						+ "*GEOM*TEXT SET " + CricketFunctions.ordinal(inning.getPartnerships().get(inning.getPartnerships().size()-1).getPartnershipNumber()) + " WICKET PARTNERSHIP" + "\0", print_writers); 
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$BattingDataAll$Split$Right$BatPartnership$txt_BatPartneshipRuns"
						+ "*GEOM*TEXT SET "+inning.getPartnerships().get(inning.getPartnerships().size()-1).getTotalRuns() + "*" +"\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$BattingDataAll$Split$Right$BatPartnership$txt_txt_BatPartneshipBalls"
						+ "*GEOM*TEXT SET "+inning.getPartnerships().get(inning.getPartnerships().size()-1).getTotalBalls()+"\0", print_writers);
				
				switch (config.getBroadcaster()) {
				case Constants.BAN_AFG_SERIES:
					if(config.getPrimaryIpAddress().equalsIgnoreCase(Constants.LOCALHOST)) {
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$BattingDataAll$Split$Right$BatPartnership$img_Player1"
								+ "*TEXTURE*IMAGE SET " + Constants.BAN_AFG_SERIES_LOCAL_PHOTO_PATH + inning.getBatting_team().getTeamName4() + "\\\\" +  Constants.LEFT + "\\\\" 
								+ inning.getPartnerships().get(inning.getPartnerships().size()-1).getFirstPlayer().getPhoto() + CricketUtil.PNG_EXTENSION + "\0", print_writers);
					}else {
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$BattingDataAll$Split$Right$BatPartnership$img_Player1"
								+ "*TEXTURE*IMAGE SET " + "\\\\" + config.getPrimaryIpAddress() + Constants.BAN_AFG_SERIES_PHOTO_PATH + inning.getBatting_team().getTeamName4() + "\\\\" 
								+  Constants.LEFT + "\\\\" + inning.getPartnerships().get(inning.getPartnerships().size()-1).getFirstPlayer().getPhoto() + CricketUtil.PNG_EXTENSION + "\0", print_writers);
					}
					
					if(config.getPrimaryIpAddress().equalsIgnoreCase(Constants.LOCALHOST)) {
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$BattingDataAll$Split$Right$BatPartnership$img_Player2"
								+ "*TEXTURE*IMAGE SET " + Constants.BAN_AFG_SERIES_LOCAL_PHOTO_PATH + inning.getBatting_team().getTeamName4() + "\\\\" +  Constants.RIGHT + "\\\\" 
								+ inning.getPartnerships().get(inning.getPartnerships().size()-1).getSecondPlayer().getPhoto() + CricketUtil.PNG_EXTENSION + "\0", print_writers);
					}else {
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$BattingDataAll$Split$Right$BatPartnership$img_Player2"
								+ "*TEXTURE*IMAGE SET "+ "\\\\" + config.getPrimaryIpAddress()+Constants.BAN_AFG_SERIES_PHOTO_PATH +inning.getBatting_team().getTeamName4() + "\\\\" 
								+  Constants.RIGHT + "\\\\" + inning.getPartnerships().get(inning.getPartnerships().size()-1).getSecondPlayer().getPhoto() + CricketUtil.PNG_EXTENSION + "\0", print_writers);
					}
					break;
				case Constants.ACC:
					if(config.getPrimaryIpAddress().equalsIgnoreCase(Constants.LOCALHOST)) {
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$BattingDataAll$Split$Right$BatPartnership$img_Player1"
								+ "*TEXTURE*IMAGE SET " + Constants.ACC_LOCAL_PHOTO_PATH + inning.getBatting_team().getTeamBadge() + "\\\\" +  Constants.LEFT + "\\\\" 
								+ inning.getPartnerships().get(inning.getPartnerships().size()-1).getFirstPlayer().getPhoto() + CricketUtil.PNG_EXTENSION + "\0", print_writers);
					}else {
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$BattingDataAll$Split$Right$BatPartnership$img_Player1"
								+ "*TEXTURE*IMAGE SET " + "\\\\" + config.getPrimaryIpAddress() + Constants.ACC_PHOTO_PATH + inning.getBatting_team().getTeamBadge() + "\\\\" 
								+  Constants.LEFT + "\\\\" + inning.getPartnerships().get(inning.getPartnerships().size()-1).getFirstPlayer().getPhoto() + CricketUtil.PNG_EXTENSION + "\0", print_writers);
					}
					
					if(config.getPrimaryIpAddress().equalsIgnoreCase(Constants.LOCALHOST)) {
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$BattingDataAll$Split$Right$BatPartnership$img_Player2"
								+ "*TEXTURE*IMAGE SET " + Constants.ACC_LOCAL_PHOTO_PATH + inning.getBatting_team().getTeamBadge() + "\\\\" +  Constants.RIGHT + "\\\\" 
								+ inning.getPartnerships().get(inning.getPartnerships().size()-1).getSecondPlayer().getPhoto() + CricketUtil.PNG_EXTENSION + "\0", print_writers);
					}else {
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$BattingDataAll$Split$Right$BatPartnership$img_Player2"
								+ "*TEXTURE*IMAGE SET "+ "\\\\" + config.getPrimaryIpAddress()+Constants.ACC_PHOTO_PATH +inning.getBatting_team().getTeamBadge() + "\\\\" 
								+  Constants.RIGHT + "\\\\" + inning.getPartnerships().get(inning.getPartnerships().size()-1).getSecondPlayer().getPhoto() + CricketUtil.PNG_EXTENSION + "\0", print_writers);
					}
					break;	
				}
				
				break;
			}
			break;
		case Constants.TRI_SERIES: case Constants.MT20:
			switch (WhichType.toUpperCase()) {
			case "PERFORMER":
				fullframes.setWhichhighlight("PERFORMER");
				BattingCard bc = inning.getBattingCard().stream().filter(bc1 -> bc1.getPlayerId() == FirstPlayerId).findAny().orElse(null);
				player = Players.stream().filter(plyr -> plyr.getPlayerId() == FirstPlayerId).findAny().orElse(null);
				
				String[] Count = CricketFunctions.getScoreTypeData(CricketUtil.BATSMAN,matchAllData, inning.getInningNumber(), player.getPlayerId(),
						"-", matchAllData.getEventFile().getEvents()).split("-");
				
				fullframes.setHighlight(bc.getBatterPosition());
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$ExtraData$Side" + WhichSide + "$select_Graphics"
						+ "*FUNCTION*Omo*vis_con SET 0\0", print_writers);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide + "$BattingCard$BatterGrp$"
						+ fullframes.getHighlight() + "$select_Colour*FUNCTION*Omo*vis_con SET 2\0", print_writers);
				
				for(int i =1; i<= 13; i++) {
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide + "$BattingCard$BatterGrp$"
							+ i + "$select_Colour$img_Base1*TEXTURE*IMAGE SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_BASE1 : Constants.MT20_BASE1) + inning.getBatting_team().getTeamBadge() + "\0", print_writers);
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide + "$BattingCard$BatterGrp$"
							+ i + "$select_Colour$img_Base2*TEXTURE*IMAGE SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_BASE2 : Constants.MT20_BASE2) + inning.getBatting_team().getTeamBadge() + "\0", print_writers);
					
				}
				
				for(int i=1;i<=6;i++) {
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Main$ExtraData$Side" + WhichSide + "$HighestScorer$DetailData$" + i + "$Dehighlight"
							+ "$img_Base1*TEXTURE*IMAGE SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_BASE1 : Constants.MT20_BASE1) + inning.getBatting_team().getTeamBadge() + "\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Main$ExtraData$Side" + WhichSide + "$HighestScorer$DetailData$" + i + "$Dehighlight"
							+ "$img_Text1*TEXTURE*IMAGE SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_TEXT1 : Constants.MT20_TEXT1) + inning.getBatting_team().getTeamBadge() + "\0", print_writers);
				}
				
				if(config.getPrimaryIpAddress().equalsIgnoreCase(Constants.LOCALHOST)) {
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$ExtraData$Side" + WhichSide + "$HighestScorer$img_PlayerShadow"
							+ "*TEXTURE*IMAGE SET "+(config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_PHOTO_PATH : Constants.MT20_PHOTO_PATH)+ inning.getBatting_team().getTeamName4() + "\\\\" + Constants.RIGHT + "\\\\" + player.getPhoto()+CricketUtil.PNG_EXTENSION+"\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$ExtraData$Side" + WhichSide + "$HighestScorer$img_Player"
							+ "*TEXTURE*IMAGE SET "+(config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_PHOTO_PATH : Constants.MT20_PHOTO_PATH)+ inning.getBatting_team().getTeamName4()+ "\\\\" +  Constants.RIGHT + "\\\\"  + player.getPhoto()+CricketUtil.PNG_EXTENSION+"\0", print_writers);
				}else {
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$ExtraData$Side" + WhichSide + "$HighestScorer$img_PlayerShadow"
							+ "*TEXTURE*IMAGE SET "+"\\\\"+config.getPrimaryIpAddress()+(config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_PHOTO_PATH : Constants.MT20_PHOTO_PATH)+ inning.getBatting_team().getTeamName4()+ "\\\\" +  Constants.RIGHT + "\\\\"  + player.getPhoto()+CricketUtil.PNG_EXTENSION+"\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$ExtraData$Side" + WhichSide + "$HighestScorer$img_Player"
							+ "*TEXTURE*IMAGE SET "+"\\\\"+config.getPrimaryIpAddress()+(config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_PHOTO_PATH : Constants.MT20_PHOTO_PATH)+ inning.getBatting_team().getTeamName4()+ "\\\\" +  Constants.RIGHT + "\\\\"  + player.getPhoto()+CricketUtil.PNG_EXTENSION+"\0", print_writers);
				}
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$ExtraData$Side" + WhichSide + "$HighestScorer$DetailData"
						+ "*FUNCTION*Grid*num_row SET 6\0", print_writers);
				
				for(int i =1;i<=6; i++) {
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$ExtraData$Side" + WhichSide + "$HighestScorer$DetailData$" + i + "$select_Highlight"
							+ "*FUNCTION*Omo*vis_con SET 0\0", print_writers);
				}
				
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$ExtraData$Side" + WhichSide + "$HighestScorer$img_Logo"
						+ "*TEXTURE*IMAGE SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_LOGO : Constants.MT20_LOGO) + inning.getBatting_team().getTeamBadge() + "\0", print_writers);
				
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$ExtraData$Side" + WhichSide + "$HighestScorer$DetailData"
						+ "$1$Dehighlight$txt_StatHead*GEOM*TEXT SET DOTS\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$ExtraData$Side" + WhichSide + "$HighestScorer$DetailData"
						+ "$2$Dehighlight$txt_StatHead*GEOM*TEXT SET ONES\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$ExtraData$Side" + WhichSide + "$HighestScorer$DetailData"
						+ "$3$Dehighlight$txt_StatHead*GEOM*TEXT SET TWOS\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$ExtraData$Side" + WhichSide + "$HighestScorer$DetailData"
						+ "$4$Dehighlight$txt_StatHead*GEOM*TEXT SET THREES\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$ExtraData$Side" + WhichSide + "$HighestScorer$DetailData"
						+ "$5$Dehighlight$txt_StatHead*GEOM*TEXT SET FOURS\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$ExtraData$Side" + WhichSide + "$HighestScorer$DetailData"
						+ "$6$Dehighlight$txt_StatHead*GEOM*TEXT SET SIXES\0", print_writers);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$ExtraData$Side" + WhichSide + "$HighestScorer$DetailData"
						+ "$1$Dehighlight$txt_StatValue*GEOM*TEXT SET " + Count[0] + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$ExtraData$Side" + WhichSide + "$HighestScorer$DetailData"
						+ "$2$Dehighlight$txt_StatValue*GEOM*TEXT SET " + Count[1] + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$ExtraData$Side" + WhichSide + "$HighestScorer$DetailData"
						+ "$3$Dehighlight$txt_StatValue*GEOM*TEXT SET " + Count[2] + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$ExtraData$Side" + WhichSide + "$HighestScorer$DetailData"
						+ "$4$Dehighlight$txt_StatValue*GEOM*TEXT SET " + Count[3] + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$ExtraData$Side" + WhichSide + "$HighestScorer$DetailData"
						+ "$5$Dehighlight$txt_StatValue*GEOM*TEXT SET " + Count[4] + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$ExtraData$Side" + WhichSide + "$HighestScorer$DetailData"
						+ "$6$Dehighlight$txt_StatValue*GEOM*TEXT SET " + Count[6] + "\0", print_writers);
				
				if(player.getSurname() != null) {
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$ExtraData$Side" + WhichSide + "$HighestScorer$txt_FirstName"
							+ "*GEOM*TEXT SET "+player.getFirstname()+"\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$ExtraData$Side" + WhichSide + "$HighestScorer$txt_LastName"
							+ "*GEOM*TEXT SET "+player.getSurname()+"\0", print_writers);
				}else {
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$ExtraData$Side" + WhichSide + "$HighestScorer$txt_FirstName"
							+ "*GEOM*TEXT SET "+player.getFull_name()+"\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$ExtraData$Side" + WhichSide + "$HighestScorer$txt_LastName"
							+ "*GEOM*TEXT SET \0", print_writers);
				}
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$ExtraData$Side" + WhichSide + "$HighestScorer$Data$txt_StatHead"
						+ "*GEOM*TEXT SET STRIKE RATE \0", print_writers);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$ExtraData$Side" + WhichSide + "$HighestScorer$Data$txt_StatValue"
						+ "*GEOM*TEXT SET "+CricketFunctions.generateStrikeRate(bc.getRuns(), bc.getBalls(), 0)+"\0", print_writers);
				break;

			case "PARTNERSHIP":
				int Top_Score = 50,total=0;
				double Mult = 360,ScaleFac1 = 0, ScaleFac2 = 0;
				
				fullframes.setWhichhighlight("PARTNERSHIP");
				
				for(int i =1; i<= 13; i++) {
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide + "$BattingCard$BatterGrp$"
							+ i + "$select_Colour$img_Base1*TEXTURE*IMAGE SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_BASE1 : Constants.MT20_BASE1) + inning.getBatting_team().getTeamBadge() + "\0", print_writers);
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide + "$BattingCard$BatterGrp$"
							+ i + "$select_Colour$img_Base2*TEXTURE*IMAGE SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_BASE2 : Constants.MT20_BASE2) + inning.getBatting_team().getTeamBadge() + "\0", print_writers);
					
				}
				
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$ExtraData$Side" + WhichSide + "$select_Graphics"
						+ "*FUNCTION*Omo*vis_con SET 1\0", print_writers);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$ExtraData$Side" + WhichSide + "$Partnership$Title$txt_Title1"
						+ "*GEOM*TEXT SET "+CricketFunctions.ordinal(inning.getPartnerships().get(inning.getPartnerships().size()-1).getPartnershipNumber())+" WICKET"+"\0", print_writers); 
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$ExtraData$Side" + WhichSide + "$Partnership$Title$txt_Title2"
						+ "*GEOM*TEXT SET PARTNERSHIP\0", print_writers);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$ExtraData$Side" + WhichSide + "$Partnership$Data$txt_Runs"
						+ "*GEOM*TEXT SET "+inning.getPartnerships().get(inning.getPartnerships().size()-1).getTotalRuns()+"\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$ExtraData$Side" + WhichSide + "$Partnership$Data$txt_Balls"
						+ "*GEOM*TEXT SET "+inning.getPartnerships().get(inning.getPartnerships().size()-1).getTotalBalls()+"\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$ExtraData$Side" + WhichSide + "$Partnership$Data$txt_BallsHead"
						+ "*GEOM*TEXT SET BALL" + CricketFunctions.Plural(inning.getPartnerships().get(inning.getPartnerships().size()-1).getTotalBalls()).toUpperCase()+"\0", print_writers);
				
				
				for(int a = 1; a <= inning.getPartnerships().size(); a++){
					if(inning.getPartnerships().get(a-1).getFirstBatterRuns() > Top_Score) {
						Top_Score = inning.getPartnerships().get(a-1).getFirstBatterRuns();
					}
					if(inning.getPartnerships().get(a-1).getSecondBatterRuns() > Top_Score) {
						Top_Score = inning.getPartnerships().get(a-1).getSecondBatterRuns();
					}
				}
				total = (inning.getPartnerships().get(inning.getPartnerships().size()-1).getFirstBatterRuns() + 
						inning.getPartnerships().get(inning.getPartnerships().size()-1).getSecondBatterRuns());
				
				System.out.println("total = " + total);
				
				ScaleFac1 = ((double) inning.getPartnerships().get(inning.getPartnerships().size()-1).getFirstBatterRuns()) / total;
				ScaleFac2 = ((double) inning.getPartnerships().get(inning.getPartnerships().size()-1).getSecondBatterRuns()) / total;
				
				System.out.println("ScaleFac1 = " + ScaleFac1);
				System.out.println("ScaleFac2 = " + ScaleFac2);
				
				ScaleFac1 = ScaleFac1*360;
				ScaleFac2 = ScaleFac2*360;
				
				for(BattingCard bcd : inning.getBattingCard()) {
					if(bcd.getPlayerId() == inning.getPartnerships().get(inning.getPartnerships().size()-1).getFirstBatterNo()) {
						fullframes.setHighlight(bcd.getBatterPosition());
					}
					if(bcd.getPlayerId() == inning.getPartnerships().get(inning.getPartnerships().size()-1).getSecondBatterNo()) {
						fullframes.setSecondHighlight(bcd.getBatterPosition());
					}
				}
//				ScaleFac1 = ((inning.getPartnerships().get(inning.getPartnerships().size()-1).getFirstBatterRuns())*(Mult/Top_Score)) ;
//				ScaleFac2 = ((inning.getPartnerships().get(inning.getPartnerships().size()-1).getSecondBatterRuns())*(Mult/Top_Score)) ;
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$ExtraData$Side" + WhichSide + "$Partnership$Data$PieAll"
						+ "$Player1$obj_RingAngle*GEOM*Angle SET " + ScaleFac1 + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$ExtraData$Side" + WhichSide + "$Partnership$Data$PieAll"
						+ "$Player1$obj_RingAngle*GEOM*Rotation SET " + (200-ScaleFac1) + "\0", print_writers);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$ExtraData$Side" + WhichSide + "$Partnership$Data$PieAll"
						+ "$Player2$obj_RingAngle*GEOM*Angle SET " + ScaleFac2 + "\0", print_writers);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$ExtraData$Side" + WhichSide + "$Partnership$Data$PieAll"
						+ "$Player1$img_Base1*TEXTURE*IMAGE SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_BASE1 : Constants.MT20_BASE1) + inning.getBatting_team().getTeamBadge() + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$ExtraData$Side" + WhichSide + "$Partnership$Data$PieAll"
						+ "$Player2$img_Base2*TEXTURE*IMAGE SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_BASE2 : Constants.MT20_BASE2) + inning.getBatting_team().getTeamBadge() + "\0", print_writers);
				
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$ExtraData$Side" + WhichSide + "$Partnership$img_Logo"
						+ "*TEXTURE*IMAGE SET "+(config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_LOGO : Constants.MT20_LOGO) + inning.getBatting_team().getTeamBadge() + "\0", print_writers);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$ExtraData$Side" + WhichSide + "$Partnership$PieAll$img_Base1"
						+ "*TEXTURE*IMAGE SET "+(config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_BASE1 : Constants.MT20_BASE1) + inning.getBatting_team().getTeamBadge() + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$ExtraData$Side" + WhichSide + "$Partnership$PieAll$img_Base2"
						+ "*TEXTURE*IMAGE SET "+(config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_BASE2 : Constants.MT20_BASE2) + inning.getBatting_team().getTeamBadge() + "\0", print_writers);
				
				if(config.getPrimaryIpAddress().equalsIgnoreCase(Constants.LOCALHOST)) {
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$ExtraData$Side" + WhichSide + "$Partnership$Image1$img_PlayerShadow"
							+ "*TEXTURE*IMAGE SET "+(config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_LOCAL_PHOTO_PATH : Constants.MT20_LOCAL_PHOTO_PATH) +inning.getBatting_team().getTeamName4()+"\\\\" +  Constants.RIGHT + "\\\\" +
							inning.getPartnerships().get(inning.getPartnerships().size()-1).getFirstPlayer().getPhoto()+ CricketUtil.PNG_EXTENSION+"\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$ExtraData$Side" + WhichSide + "$Partnership$Image1$img_Player"
							+ "*TEXTURE*IMAGE SET "+(config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_LOCAL_PHOTO_PATH : Constants.MT20_LOCAL_PHOTO_PATH) +inning.getBatting_team().getTeamName4() + "\\\\" +  Constants.RIGHT + "\\\\" +
							inning.getPartnerships().get(inning.getPartnerships().size()-1).getFirstPlayer().getPhoto()+ CricketUtil.PNG_EXTENSION+"\0", print_writers);
				}else {
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$ExtraData$Side" + WhichSide + "$Partnership$Image1$img_PlayerShadow"
							+ "*TEXTURE*IMAGE SET "+ "\\\\" + config.getPrimaryIpAddress()+(config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_PHOTO_PATH : Constants.MT20_PHOTO_PATH) +inning.getBatting_team().getTeamName4() + "\\\\" +  Constants.RIGHT + "\\\\" +
							inning.getPartnerships().get(inning.getPartnerships().size()-1).getFirstPlayer().getPhoto()+ CricketUtil.PNG_EXTENSION+"\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$ExtraData$Side" + WhichSide + "$Partnership$Image1$img_Player"
							+ "*TEXTURE*IMAGE SET "+ "\\\\" + config.getPrimaryIpAddress()+(config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_PHOTO_PATH : Constants.MT20_PHOTO_PATH) +inning.getBatting_team().getTeamName4() + "\\\\" +  Constants.RIGHT + "\\\\" +
							inning.getPartnerships().get(inning.getPartnerships().size()-1).getFirstPlayer().getPhoto()+ CricketUtil.PNG_EXTENSION+"\0", print_writers);
				}
				
				if(config.getPrimaryIpAddress().equalsIgnoreCase(Constants.LOCALHOST)) {
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$ExtraData$Side" + WhichSide + "$Partnership$Image2$img_PlayerShadow"
							+ "*TEXTURE*IMAGE SET "+(config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_LOCAL_PHOTO_PATH : Constants.MT20_LOCAL_PHOTO_PATH) +inning.getBatting_team().getTeamName4() + "\\\\" +  Constants.RIGHT + "\\\\" +
							inning.getPartnerships().get(inning.getPartnerships().size()-1).getSecondPlayer().getPhoto()+ CricketUtil.PNG_EXTENSION+"\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$ExtraData$Side" + WhichSide + "$Partnership$Image2$img_Player"
							+ "*TEXTURE*IMAGE SET "+(config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_LOCAL_PHOTO_PATH : Constants.MT20_LOCAL_PHOTO_PATH) +inning.getBatting_team().getTeamName4() + "\\\\" +  Constants.RIGHT + "\\\\" +
							inning.getPartnerships().get(inning.getPartnerships().size()-1).getSecondPlayer().getPhoto()+ CricketUtil.PNG_EXTENSION+"\0", print_writers);
				}else {
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$ExtraData$Side" + WhichSide + "$Partnership$Image2$img_PlayerShadow"
							+ "*TEXTURE*IMAGE SET "+ "\\\\" + config.getPrimaryIpAddress()+(config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_PHOTO_PATH : Constants.MT20_PHOTO_PATH) +inning.getBatting_team().getTeamName4() + "\\\\" +  Constants.RIGHT + "\\\\" +
							inning.getPartnerships().get(inning.getPartnerships().size()-1).getSecondPlayer().getPhoto()+ CricketUtil.PNG_EXTENSION+"\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$ExtraData$Side" + WhichSide + "$Partnership$Image2$img_Player"
							+ "*TEXTURE*IMAGE SET "+ "\\\\" + config.getPrimaryIpAddress()+(config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_PHOTO_PATH : Constants.MT20_PHOTO_PATH) +inning.getBatting_team().getTeamName4() + "\\\\" +  Constants.RIGHT + "\\\\" +
							inning.getPartnerships().get(inning.getPartnerships().size()-1).getSecondPlayer().getPhoto()+ CricketUtil.PNG_EXTENSION+"\0", print_writers);
				}
				break;
			}
			break;
		}
		return Constants.OK;
	}
	
	public String BowlingCardBallPerformerBody(List<PrintWriter> print_writers, int WhichSide, Configuration config, MatchAllData matchAllData,Inning inning) throws Exception
	{
		switch (config.getBroadcaster()) {
		case Constants.TRI_SERIES: case Constants.MT20:
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide 
					+ "$select_GraphicsType*FUNCTION*Omo*vis_con SET 14\0", print_writers);
			
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide + "$BowlingCardExtra"+
					"$BowlerExtraGrp$1$select_BallRowType*FUNCTION*Omo*vis_con SET 0 \0", print_writers);
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide + "$BowlingCardExtra"+
					"$BowlerExtraGrp$Rows*FUNCTION*Grid*num_row SET 11 \0", print_writers);
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide + "$BowlingCardExtra$" +
					"BowlerExtraGrp$object_ScaleY*TRANSFORMATION*SCALING*Y SET 1.0\0", print_writers);
			for(int j=1;j<=11;j++) {
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide + "$BowlingCardExtra"+
						"$BowlerExtraGrp$" + (j+1) + "$select_BallRowType*FUNCTION*Omo*vis_con SET 0 \0", print_writers);
			}
			for(int i=1; i<=13; i++) {
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide + "$BowlingCardExtra$BowlerExtraGrp$"
						+ i + "$select_Colour$img_Base1*TEXTURE*IMAGE SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_BASE1 : Constants.MT20_BASE1) + inning.getBowling_team().getTeamBadge() + "\0", print_writers);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide + "$BowlingCardExtra$BowlerExtraGrp$"
						+ i + "$select_Colour$img_Base2*TEXTURE*IMAGE SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_BASE2 : Constants.MT20_BASE2) + inning.getBowling_team().getTeamBadge() + "\0", print_writers);
				
			}

			Collections.sort(inning.getBowlingCard());
			if(inning.getBowlingCard() != null && inning.getBowlingCard().size() > 0) {
				for(int iRow = 1; iRow <= inning.getBowlingCard().size(); iRow++) {
					if(inning.getBowlingCard().get(iRow-1).getRuns() > 0 || ((inning.getBowlingCard().get(iRow-1).getOvers()*6)
							+inning.getBowlingCard().get(iRow-1).getBalls()) > 0) {
						if(CricketFunctions.GetTargetData(matchAllData).getRemaningRuns() == 0 || matchAllData.getMatch().getInning().get(1).getTotalWickets() >= 10 || 
								CricketFunctions.GetTargetData(matchAllData).getRemaningBall()  == 0) {
							omo = 1;
							containerName = "$Dehighlight";
						}else {
							if(inning.getInningStatus().equalsIgnoreCase(CricketUtil.PAUSE)) {
								omo = 1;
								containerName = "$Dehighlight";
							}else {
								switch (inning.getBowlingCard().get(iRow-1).getStatus().toUpperCase()) {
								case (CricketUtil.OTHER + CricketUtil.BOWLER):
									omo = 1;
									containerName = "$Dehighlight";
									break;
								case (CricketUtil.LAST + CricketUtil.BOWLER):
									omo = 1;
									containerName = "$Dehighlight";
									break;
								case (CricketUtil.CURRENT + CricketUtil.BOWLER):
									omo = 2;
									containerName = "$Highlight";
									break;
								}
							}
						}
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide + "$BowlingCardExtra"+
								"$BowlerExtraGrp$" + (iRow+1) + "$select_BallRowType*FUNCTION*Omo*vis_con SET " + omo + "\0", print_writers);
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide + "$BowlingCardExtra"+
								"$BowlerExtraGrp$" + (iRow+1) + "$select_BallRowType" + containerName + "$txt_BowlerName*GEOM*TEXT SET " + 
								inning.getBowlingCard().get(iRow-1).getPlayer().getTicker_name() + "\0", print_writers);
						
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide + "$BowlingCardExtra"+
								"$BowlerExtraGrp$" + (iRow+1) + "$select_BallRowType" + containerName + "$txt_Figures*GEOM*TEXT SET " + 
								inning.getBowlingCard().get(iRow-1).getWickets() + "-" + inning.getBowlingCard().get(iRow-1).getRuns() + "\0", print_writers);
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide + "$BowlingCardExtra"+
								"$BowlerExtraGrp$" + (iRow+1) + "$select_BallRowType" + containerName + "$txt_Overs*GEOM*TEXT SET " + CricketFunctions.
								OverBalls(inning.getBowlingCard().get(iRow-1).getOvers(), inning.getBowlingCard().get(iRow-1).getBalls()) + "\0", print_writers);
					}
				}
			}
			
			BowlingCard bc = inning.getBowlingCard().stream().filter(boc -> boc.getPlayerId() == FirstPlayerId).findAny().orElse(null);
			player = Players.stream().filter(plyr -> plyr.getPlayerId() == FirstPlayerId).findAny().orElse(null);
			
			String[] Count = CricketFunctions.getScoreTypeData(CricketUtil.BOWLER,matchAllData, inning.getInningNumber(), player.getPlayerId(),
					"-", matchAllData.getEventFile().getEvents()).split("-");
			fullframes.setHighlight(bc.getBowlingPosition()+1);
			
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide + "$BowlingCardExtra$BowlerExtraGrp$"
					+ fullframes.getHighlight() + "$select_Colour*FUNCTION*Omo*vis_con SET 2\0", print_writers);
			
			if(config.getPrimaryIpAddress().equalsIgnoreCase(Constants.LOCALHOST)) {
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide + "$BowlingCardExtra"
						+ "$BestPerformer$img_PlayerShadow*TEXTURE*IMAGE SET "+(config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_PHOTO_PATH : Constants.MT20_PHOTO_PATH)+ inning.getBatting_team().getTeamName4() + "\\\\" 
						+ Constants.RIGHT + "\\\\" + player.getPhoto()+CricketUtil.PNG_EXTENSION + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide + "$BowlingCardExtra"
						+ "$BestPerformer$img_Player*TEXTURE*IMAGE SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_PHOTO_PATH : Constants.MT20_PHOTO_PATH) + inning.getBatting_team().getTeamName4()+ "\\\\" + Constants.RIGHT 
						+ "\\\\"  + player.getPhoto()+CricketUtil.PNG_EXTENSION+"\0", print_writers);
			}else {
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide + "$BowlingCardExtra"
						+ "$BestPerformer$img_PlayerShadow*TEXTURE*IMAGE SET " + "\\\\" + config.getPrimaryIpAddress() + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_PHOTO_PATH : Constants.MT20_PHOTO_PATH) +
						inning.getBowling_team().getTeamName4() + "\\\\" +  Constants.RIGHT + "\\\\"  + player.getPhoto()+CricketUtil.PNG_EXTENSION + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide + "$BowlingCardExtra"
						+ "$BestPerformer$img_Player*TEXTURE*IMAGE SET " + "\\\\" + config.getPrimaryIpAddress() + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_PHOTO_PATH : Constants.MT20_PHOTO_PATH) + 
						inning.getBowling_team().getTeamName4()+ "\\\\" +  Constants.RIGHT + "\\\\" + player.getPhoto()+CricketUtil.PNG_EXTENSION + "\0", print_writers);
			}
			
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide + "$BowlingCardExtra"
					+ "$BestPerformer$DetailData*FUNCTION*Grid*num_row SET 3\0", print_writers);
			for(int i =1;i<=6; i++) {
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide + "$BowlingCardExtra"
						+ "$BestPerformer$DetailData$" + i + "$select_Highlight*FUNCTION*Omo*vis_con SET 0\0", print_writers);
			}
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide + "$BowlingCardExtra"
					+ "$BestPerformer$img_Logo*TEXTURE*IMAGE SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_LOGO : Constants.MT20_LOGO) + inning.getBowling_team().getTeamBadge() + "\0", print_writers);
			
			
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide + "$BowlingCardExtra"
					+ "$BestPerformer$DetailData$1$Dehighlight$txt_StatHead*GEOM*TEXT SET DOTS\0", print_writers);
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide + "$BowlingCardExtra"
					+ "$BestPerformer$DetailData$2$Dehighlight$txt_StatHead*GEOM*TEXT SET 4s Conc.\0", print_writers);
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide + "$BowlingCardExtra"
					+ "$BestPerformer$DetailData$3$Dehighlight$txt_StatHead*GEOM*TEXT SET 6s Conc.\0", print_writers);
			
			
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide + "$BowlingCardExtra"
					+ "$BestPerformer$DetailData$1$Dehighlight$txt_StatValue*GEOM*TEXT SET " + Count[0] + "\0", print_writers);
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide + "$BowlingCardExtra"
					+ "$BestPerformer$DetailData$2$Dehighlight$txt_StatValue*GEOM*TEXT SET " + Count[4] + "\0", print_writers);
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide + "$BowlingCardExtra"
					+ "$BestPerformer$DetailData$3$Dehighlight$txt_StatValue*GEOM*TEXT SET " + Count[6] + "\0", print_writers);
			
			
			if(player.getSurname() != null) {
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide + "$BowlingCardExtra"
						+ "$BestPerformer$txt_FirstName*GEOM*TEXT SET "+player.getFirstname()+"\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide + "$BowlingCardExtra"
						+ "$BestPerformer$txt_LastName*GEOM*TEXT SET "+player.getSurname()+"\0", print_writers);
			}else {
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide + "$BowlingCardExtra"
						+ "$BestPerformer$txt_FirstName*GEOM*TEXT SET "+player.getFull_name()+"\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide + "$BowlingCardExtra"
						+ "$BestPerformer$txt_LastName*GEOM*TEXT SET \0", print_writers);
			}
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide + "$BowlingCardExtra"
					+ "$BestPerformer$Data$txt_StatHead*GEOM*TEXT SET ECONOMY\0", print_writers);
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide + "$BowlingCardExtra"
					+ "$BestPerformer$Data$txt_StatValue*GEOM*TEXT SET " + bc.getEconomyRate() + "\0", print_writers);
			break;
		}
		return Constants.OK;
	}
	
	public String BallPerformerBody(List<PrintWriter> print_writers, int WhichSide, Configuration config, MatchAllData matchAllData,Inning inning, CricketService cricketService) throws Exception
	{
		BowlingCard bc = inning.getBowlingCard().stream().filter(boc -> boc.getPlayerId() == FirstPlayerId).findAny().orElse(null);
		player = Players.stream().filter(plyr -> plyr.getPlayerId() == FirstPlayerId).findAny().orElse(null);
		
		switch (config.getBroadcaster()) {
		case Constants.BAN_AFG_SERIES: case Constants.ACC:
			fullframes.setHighlight(bc.getBowlingPosition());
			
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$BowlingCard$BallDataAll$Select_Type*FUNCTION*Omo*vis_con SET 1\0", print_writers);
			
			switch (config.getBroadcaster()) {
			case Constants.BAN_AFG_SERIES:
				if(config.getPrimaryIpAddress().equalsIgnoreCase(Constants.LOCALHOST)) {
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$BowlingCard$Split$Right$HighestScore$img_Player*TEXTURE*IMAGE SET " 
							+ Constants.BAN_AFG_SERIES_LOCAL_PHOTO_PATH + inning.getBowling_team().getTeamName4() + "\\\\" + Constants.CENTER + "\\\\" + player.getPhoto()+CricketUtil.PNG_EXTENSION 
							+ "\0", print_writers);
				}else {
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$BowlingCard$Split$Right$HighestScore$img_Player*TEXTURE*IMAGE SET " 
							+ "\\\\" + config.getPrimaryIpAddress() + Constants.BAN_AFG_SERIES_PHOTO_PATH + inning.getBowling_team().getTeamName4() + "\\\\" + Constants.CENTER + "\\\\" + player.getPhoto() 
							+ CricketUtil.PNG_EXTENSION + "\0", print_writers);
				}
				break;
			case Constants.ACC:
				if(config.getPrimaryIpAddress().equalsIgnoreCase(Constants.LOCALHOST)) {
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$BowlingCard$Split$Right$HighestScore$img_Player*TEXTURE*IMAGE SET " 
							+ Constants.ACC_LOCAL_PHOTO_PATH + inning.getBowling_team().getTeamBadge() + "\\\\" + Constants.CENTER + "\\\\" + player.getPhoto()+CricketUtil.PNG_EXTENSION 
							+ "\0", print_writers);
				}else {
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$BowlingCard$Split$Right$HighestScore$img_Player*TEXTURE*IMAGE SET " 
							+ "\\\\" + config.getPrimaryIpAddress() + Constants.ACC_PHOTO_PATH + inning.getBowling_team().getTeamBadge() + "\\\\" + Constants.CENTER + "\\\\" + player.getPhoto() 
							+ CricketUtil.PNG_EXTENSION + "\0", print_writers);
				}
				break;	
			}
			
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$BowlingCard$Split$Right$HighestScore$txt_PlayerName"
					+ "*GEOM*TEXT SET " + bc.getPlayer().getFull_name() + "\0", print_writers);
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$BowlingCard$Split$Right$HighestScore$txt_StatHead"
					+ "*GEOM*TEXT SET ECONOMY\0", print_writers);
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$BowlingCard$Split$Right$HighestScore$txt_StatValue"
					+ "*GEOM*TEXT SET " + bc.getEconomyRate() + "\0", print_writers);
			
			break;
		case Constants.TRI_SERIES: case Constants.MT20:
			
			
			String[] Count = CricketFunctions.getScoreTypeData(CricketUtil.BOWLER,matchAllData, inning.getInningNumber(), player.getPlayerId(),
					"-", matchAllData.getEventFile().getEvents()).split("-");
			fullframes.setHighlight(bc.getBowlingPosition()+1);
			
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide + "$BowlingCard$BowlerGrp$"
					+ fullframes.getHighlight() + "$select_Colour*FUNCTION*Omo*vis_con SET 2\0", print_writers);
			
			for(int i =1; i<= 13; i++) {
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide + "$BowlingCard$BowlerGrp$"
						+ i + "$select_Colour$img_Base1*TEXTURE*IMAGE SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_BASE1 : Constants.MT20_BASE1) + inning.getBowling_team().getTeamBadge() + "\0", print_writers);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide + "$BowlingCard$BowlerGrp$"
						+ i + "$select_Colour$img_Base2*TEXTURE*IMAGE SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_BASE2 : Constants.MT20_BASE2) + inning.getBowling_team().getTeamBadge() + "\0", print_writers);
				
			}
			
			for(int i=1;i<=6;i++) {
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Main$ExtraData$Side" + WhichSide + "$HighestScorer$DetailData$" + i + "$Dehighlight"
						+ "$img_Base1*TEXTURE*IMAGE SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_BASE1 : Constants.MT20_BASE1) + inning.getBowling_team().getTeamBadge() + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Main$ExtraData$Side" + WhichSide + "$HighestScorer$DetailData$" + i + "$Dehighlight"
						+ "$img_Text1*TEXTURE*IMAGE SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_TEXT1 : Constants.MT20_TEXT1) + inning.getBowling_team().getTeamBadge() + "\0", print_writers);
			}
			
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$ExtraData$Side" + WhichSide + "$select_Graphics"
					+ "*FUNCTION*Omo*vis_con SET 0\0", print_writers);
			
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$ExtraData$Side" + WhichSide + "$select_Graphics"
					+ "*FUNCTION*Omo*vis_con SET 0\0", print_writers);
			
			if(config.getPrimaryIpAddress().equalsIgnoreCase(Constants.LOCALHOST)) {
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$ExtraData$Side" + WhichSide + "$HighestScorer$img_PlayerShadow"
						+ "*TEXTURE*IMAGE SET "+(config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_PHOTO_PATH : Constants.MT20_PHOTO_PATH)+ inning.getBowling_team().getTeamName4() + "\\\\" + Constants.RIGHT + "\\\\" + player.getPhoto()+CricketUtil.PNG_EXTENSION+"\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$ExtraData$Side" + WhichSide + "$HighestScorer$img_Player"
						+ "*TEXTURE*IMAGE SET "+(config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_PHOTO_PATH : Constants.MT20_PHOTO_PATH)+ inning.getBowling_team().getTeamName4()+ "\\\\" +  Constants.RIGHT + "\\\\"  + player.getPhoto()+CricketUtil.PNG_EXTENSION+"\0", print_writers);
			}else {
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$ExtraData$Side" + WhichSide + "$HighestScorer$img_PlayerShadow"
						+ "*TEXTURE*IMAGE SET "+"\\\\"+config.getPrimaryIpAddress()+(config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_PHOTO_PATH : Constants.MT20_PHOTO_PATH)+ inning.getBowling_team().getTeamName4()+ "\\\\" +  Constants.RIGHT + "\\\\"  + player.getPhoto()+CricketUtil.PNG_EXTENSION+"\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$ExtraData$Side" + WhichSide + "$HighestScorer$img_Player"
						+ "*TEXTURE*IMAGE SET "+"\\\\"+config.getPrimaryIpAddress()+(config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_PHOTO_PATH : Constants.MT20_PHOTO_PATH)+ inning.getBowling_team().getTeamName4()+ "\\\\" +  Constants.RIGHT + "\\\\"  + player.getPhoto()+CricketUtil.PNG_EXTENSION+"\0", print_writers);
			}
			
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$ExtraData$Side" + WhichSide + "$HighestScorer$DetailData"
					+ "*FUNCTION*Grid*num_row SET 3\0", print_writers);
			
			for(int i =1;i<=6; i++) {
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$ExtraData$Side" + WhichSide + "$HighestScorer$DetailData$" + i + "$select_Highlight"
						+ "*FUNCTION*Omo*vis_con SET 0\0", print_writers);
			}
			
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$ExtraData$Side" + WhichSide + "$HighestScorer$img_Logo"
					+ "*TEXTURE*IMAGE SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_LOGO : Constants.MT20_LOGO) + inning.getBowling_team().getTeamBadge() + "\0", print_writers);
			
			
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$ExtraData$Side" + WhichSide + "$HighestScorer$DetailData"
					+ "$1$Dehighlight$txt_StatHead*GEOM*TEXT SET DOTS\0", print_writers);
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$ExtraData$Side" + WhichSide + "$HighestScorer$DetailData"
					+ "$2$Dehighlight$txt_StatHead*GEOM*TEXT SET 4s Conc.\0", print_writers);
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$ExtraData$Side" + WhichSide + "$HighestScorer$DetailData"
					+ "$3$Dehighlight$txt_StatHead*GEOM*TEXT SET 6s Conc.\0", print_writers);
			
			
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$ExtraData$Side" + WhichSide + "$HighestScorer$DetailData"
					+ "$1$Dehighlight$txt_StatValue*GEOM*TEXT SET " + Count[0] + "\0", print_writers);
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$ExtraData$Side" + WhichSide + "$HighestScorer$DetailData"
					+ "$2$Dehighlight$txt_StatValue*GEOM*TEXT SET " + Count[4] + "\0", print_writers);
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$ExtraData$Side" + WhichSide + "$HighestScorer$DetailData"
					+ "$3$Dehighlight$txt_StatValue*GEOM*TEXT SET " + Count[6] + "\0", print_writers);
			
			
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$ExtraData$Side" + WhichSide + "$HighestScorer$img_Logo"
					+ "*TEXTURE*IMAGE SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_LOGO : Constants.MT20_LOGO) + inning.getBowling_team().getTeamBadge() + "\0", print_writers);
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$ExtraData$Side" + WhichSide + "$HighestScorer$txt_SubTitle"
					+ "*GEOM*TEXT SET BEST PERFORMER\0", print_writers);
			if(player.getSurname() != null) {
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$ExtraData$Side" + WhichSide + "$HighestScorer$txt_FirstName"
						+ "*GEOM*TEXT SET "+player.getFirstname()+"\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$ExtraData$Side" + WhichSide + "$HighestScorer$txt_LastName"
						+ "*GEOM*TEXT SET "+player.getSurname()+"\0", print_writers);
			}else {
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$ExtraData$Side" + WhichSide + "$HighestScorer$txt_FirstName"
						+ "*GEOM*TEXT SET "+player.getFull_name()+"\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$ExtraData$Side" + WhichSide + "$HighestScorer$txt_LastName"
						+ "*GEOM*TEXT SET \0", print_writers);
			}
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$ExtraData$Side" + WhichSide + "$HighestScorer$txt_StatHead"
					+ "*GEOM*TEXT SET ECONOMY \0", print_writers);
			
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$ExtraData$Side" + WhichSide + "$HighestScorer$txt_StatValue"
					+ "*GEOM*TEXT SET " + bc.getEconomyRate() + "\0", print_writers);
			break;
		}
		return Constants.OK;
	}
	public String BowlingCardBody(List<PrintWriter> print_writers, int WhichSide, Configuration config, MatchAllData matchAllData,Inning inning) {
		
		switch (config.getBroadcaster()) {
		case Constants.AFG_SL_SERIES:
			rowId = 1;
			
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$select_GraphicsType*FUNCTION*Omo*vis_con SET 1\0", print_writers);
			
			for(int j=2;j<=13;j++) {
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$BowlingCard$MainData$Grp" + j + "*ACTIVE SET 0\0", print_writers);
			}
			
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$BowlingCard$MainData$Grp1$select_DataType*FUNCTION*Omo*vis_con SET 0\0", print_writers);
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$BowlingCard$MainData$Grp1$Title$txt_Overs*GEOM*TEXT SET OVERS\0", print_writers);
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$BowlingCard$MainData$Grp1$Title$txt_Runs*GEOM*TEXT SET RUNS\0", print_writers);
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$BowlingCard$MainData$Grp1$Title$txt_Wickets*GEOM*TEXT SET WICKETS\0", print_writers);
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$BowlingCard$MainData$Grp1$Title$txt_Economy*GEOM*TEXT SET ECONOMY\0", print_writers);
			
			switch (matchAllData.getSetup().getMatchType()) {
			case CricketUtil.ODI: case CricketUtil.TEST: case CricketUtil.OD:
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$BowlingCard$MainData$Grp1$Title$txt_Maidens*GEOM*TEXT SET MAIDENS\0", print_writers);
				break;
			default:
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$BowlingCard$MainData$Grp1$Title$txt_Maidens*GEOM*TEXT SET DOTS\0", print_writers);
				break;
			}
			Collections.sort(inning.getBowlingCard());
			if(inning.getBowlingCard() != null && inning.getBowlingCard().size() > 0) {
				for(int iRow = 1; iRow <= inning.getBowlingCard().size(); iRow++) {
					if(inning.getBowlingCard().get(iRow-1).getRuns() > 0 || ((inning.getBowlingCard().get(iRow-1).getOvers()*6)
							+inning.getBowlingCard().get(iRow-1).getBalls()) > 0) {
						rowId = rowId + 1;
						if(CricketFunctions.GetTargetData(matchAllData).getRemaningRuns() == 0 || matchAllData.getMatch().getInning().get(1).getTotalWickets() >= 10 || 
								CricketFunctions.GetTargetData(matchAllData).getRemaningBall()  == 0) {
							omo = 1;
							containerName = "$Dehighlight";
						}else {
							if(inning.getInningStatus().equalsIgnoreCase(CricketUtil.PAUSE)) {
								omo = 1;
								containerName = "$Dehighlight";
							}else {
								switch (inning.getBowlingCard().get(iRow-1).getStatus().toUpperCase()) {
								case (CricketUtil.OTHER + CricketUtil.BOWLER):
									omo = 1;
									containerName = "$Dehighlight";
									break;
								case (CricketUtil.LAST + CricketUtil.BOWLER):
									omo = 1;
									containerName = "$Dehighlight";
									break;
								case (CricketUtil.CURRENT + CricketUtil.BOWLER):
									omo = 2;
									containerName = "$Highlight";
									break;
								}
							}
						}
						
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$BowlingCard$MainData$Grp" 
								+ rowId + "$select_DataType*FUNCTION*Omo*vis_con SET " + omo + "\0", print_writers);
						
						
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$BowlingCard$MainData$Grp" + rowId + "*ACTIVE SET 1\0", print_writers);
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$BowlingCard$MainData$Grp" + rowId + containerName 
								+ "$txt_BowlerName*GEOM*TEXT SET " + inning.getBowlingCard().get(iRow-1).getPlayer().getTicker_name() + "\0", print_writers);
						
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$BowlingCard$MainData$Grp" + rowId + containerName 
								+ "$txt_Overs*GEOM*TEXT SET " + CricketFunctions.OverBalls(inning.getBowlingCard().get(iRow-1).getOvers(), inning.getBowlingCard().get(iRow-1).getBalls()) + "\0", print_writers);
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$BowlingCard$MainData$Grp" + rowId + containerName 
								+ "$txt_Runs*GEOM*TEXT SET " + inning.getBowlingCard().get(iRow-1).getRuns() + "\0", print_writers);
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$BowlingCard$MainData$Grp" + rowId + containerName 
								+ "$txt_Wickets*GEOM*TEXT SET " + inning.getBowlingCard().get(iRow-1).getWickets() + "\0", print_writers);
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$BowlingCard$MainData$Grp" + rowId + containerName 
								+ "$txt_Economy*GEOM*TEXT SET " + (inning.getBowlingCard().get(iRow-1).getEconomyRate() != null ? inning.getBowlingCard().get(iRow-1).getEconomyRate() : "-") + "\0", print_writers);
						
						switch (matchAllData.getSetup().getMatchType()) {
						case CricketUtil.ODI: case CricketUtil.TEST: case CricketUtil.OD:
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$BowlingCard$MainData$Grp" + rowId + containerName 
									+ "$txt_Maidens*GEOM*TEXT SET " + inning.getBowlingCard().get(iRow-1).getMaidens() + "\0", print_writers);
							break;
						default:
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$BowlingCard$MainData$Grp" + rowId + containerName 
									+ "$txt_Maidens*GEOM*TEXT SET " + inning.getBowlingCard().get(iRow-1).getDots() + "\0", print_writers);
							break;
						}
					}
				}
			}
			if(inning.getBowlingCard().size() <= 9) {
				if(inning.getFallsOfWickets() != null) {
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$BowlingCard$MainData$Grp11*ACTIVE SET 1\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$BowlingCard$MainData$Grp12*ACTIVE SET 1\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$BowlingCard$MainData$Grp13*ACTIVE SET 1\0", print_writers);
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$BowlingCard$MainData$Grp11$select_DataType"
							+ "*FUNCTION*Omo*vis_con SET 3\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$BowlingCard$MainData$Grp12$select_DataType"
							+ "*FUNCTION*Omo*vis_con SET 4\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$BowlingCard$MainData$Grp13$select_DataType"
							+ "*FUNCTION*Omo*vis_con SET 5\0", print_writers);
					
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$BowlingCard$MainData$Grp11$FOW_Title$txt_BowlerName"
							+ "*GEOM*TEXT SET  FALL OF WICKETS\0", print_writers);
					for(int i =1; i<= 10; i++) {
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$BowlingCard$MainData$Grp12$Wickets$WicketsData$fig_"
								+ i + "*GEOM*TEXT SET " + CricketFunctions.ordinal(i) + "\0", print_writers);
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$BowlingCard$MainData$Grp13$Score$ScoreData$fig_"
								+ i + "*GEOM*TEXT SET \0", print_writers);
					}
					for(FallOfWicket fow : inning.getFallsOfWickets()) {
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$BowlingCard$MainData$Grp13$Score$ScoreData$fig_"
								+ fow.getFowNumber() + "*GEOM*TEXT SET " + fow.getFowRuns() + "\0", print_writers);
					}
				}
			}
			
			break;
		case Constants.BAN_AFG_SERIES: case Constants.ACC:
			rowId = 0;
			
			fullframes.setSecondHighlight(0);
			
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$Select*FUNCTION*Omo*vis_con SET 1\0", print_writers);
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$BowlingCard$BallDataAll$Select_Type*FUNCTION*Omo*vis_con SET 0\0", print_writers);
			
			for(int j=1;j<=11;j++) {
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$BowlingCard$BallDataAll$Normal$BallRow" + j + "*ACTIVE SET 0\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$BowlingCard$BallDataAll$Split$Left$BallRow" + j + "*ACTIVE SET 0\0", print_writers);
			}
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$BowlingCard$BallDataAll$Normal$FOW_Grp*ACTIVE SET 0\0", print_writers);
			
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$BowlingCard$BallDataAll$Normal$BallRow0$txt_Overs*GEOM*TEXT SET OVERS\0", print_writers);
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$BowlingCard$BallDataAll$Normal$BallRow0$txt_Runs*GEOM*TEXT SET RUNS\0", print_writers);
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$BowlingCard$BallDataAll$Normal$BallRow0$txt_Wickets*GEOM*TEXT SET WICKETS\0", print_writers);
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$BowlingCard$BallDataAll$Normal$BallRow0$txt_Economy*GEOM*TEXT SET ECONOMY\0", print_writers);
			
			switch (matchAllData.getSetup().getMatchType()) {
			case CricketUtil.ODI: case CricketUtil.TEST: case CricketUtil.OD:
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$BowlingCard$BallDataAll$Normal$BallRow0$txt_Dots*GEOM*TEXT SET MAIDENS\0", print_writers);
				break;
			default:
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$BowlingCard$BallDataAll$Normal$BallRow0$txt_Dots*GEOM*TEXT SET DOTS\0", print_writers);
				break;
			}
			
			Collections.sort(inning.getBowlingCard());
			if(inning.getBowlingCard() != null && inning.getBowlingCard().size() > 0) {
				for(int iRow = 1; iRow <= inning.getBowlingCard().size(); iRow++) {
					if(inning.getBowlingCard().get(iRow-1).getRuns() > 0 || ((inning.getBowlingCard().get(iRow-1).getOvers()*6)
							+inning.getBowlingCard().get(iRow-1).getBalls()) > 0) {
						if(CricketFunctions.GetTargetData(matchAllData).getRemaningRuns() == 0 || matchAllData.getMatch().getInning().get(1).getTotalWickets() >= 10 || 
								CricketFunctions.GetTargetData(matchAllData).getRemaningBall()  == 0) {
							omo = 0;
							containerName = "$Dehighlight";
						}else {
							if(inning.getInningStatus().equalsIgnoreCase(CricketUtil.PAUSE)) {
								omo = 0;
								containerName = "$Dehighlight";
							}else {
								switch (inning.getBowlingCard().get(iRow-1).getStatus().toUpperCase()) {
								case (CricketUtil.OTHER + CricketUtil.BOWLER):
									omo = 0;
									containerName = "$Dehighlight";
									break;
								case (CricketUtil.LAST + CricketUtil.BOWLER):
									omo = 0;
									containerName = "$Dehighlight";
									break;
								case (CricketUtil.CURRENT + CricketUtil.BOWLER):
									omo = 1;
									containerName = "$Highlight";
									
									fullframes.setSecondHighlight(inning.getBowlingCard().get(iRow-1).getBowlingPosition());
									
									CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$BowlingCard$BallDataAll$Split$Left$BallRow" + iRow + 
											"$Highlight$img_Base3*TEXTURE*IMAGE SET " + Constants.BAN_AFG_SERIES_BASE2 + inning.getBowling_team().getTeamBadge() + "\0", print_writers);
									CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$BowlingCard$BallDataAll$Split$Left$BallRow" + iRow + 
											"$Highlight$img_Text3*TEXTURE*IMAGE SET " + Constants.BAN_AFG_SERIES_TEXT2 + inning.getBowling_team().getTeamBadge() + "\0", print_writers);
									break;
								}
							}
						}
						
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$BowlingCard"
								+ "$BallDataAll$Normal$BallRow" + iRow + "$Select*FUNCTION*Omo*vis_con SET " + omo + "\0", print_writers);
						
						
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$BowlingCard$BallDataAll$Normal$BallRow" + iRow + "*ACTIVE SET 1\0", print_writers);
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$BowlingCard$BallDataAll$Normal$BallRow" + iRow + 
								"$txt_BowlerName*GEOM*TEXT SET " + inning.getBowlingCard().get(iRow-1).getPlayer().getTicker_name() + "\0", print_writers);
						
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$BowlingCard$BallDataAll$Normal$BallRow" + iRow + containerName +
								"$txt_Overs*GEOM*TEXT SET " + CricketFunctions.OverBalls(inning.getBowlingCard().get(iRow-1).getOvers(), inning.getBowlingCard().get(iRow-1).getBalls()) + "\0", print_writers);						
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$BowlingCard$BallDataAll$Normal$BallRow" + iRow + containerName + 
								"$txt_Runs*GEOM*TEXT SET " + inning.getBowlingCard().get(iRow-1).getRuns() + "\0", print_writers);
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$BowlingCard$BallDataAll$Normal$BallRow" + iRow + containerName + 
								"$txt_Wickets*GEOM*TEXT SET " + inning.getBowlingCard().get(iRow-1).getWickets() + "\0", print_writers);
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$BowlingCard$BallDataAll$Normal$BallRow" + iRow + containerName + 
								"$txt_Economy*GEOM*TEXT SET " + (inning.getBowlingCard().get(iRow-1).getEconomyRate() != null ? inning.getBowlingCard().get(iRow-1).getEconomyRate() : "-") + "\0", print_writers);
						
						switch (matchAllData.getSetup().getMatchType()) {
						case CricketUtil.ODI: case CricketUtil.TEST: case CricketUtil.OD:
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$BowlingCard$BallDataAll$Normal$BallRow" + iRow + containerName + 
									"$txt_Dots*GEOM*TEXT SET " + inning.getBowlingCard().get(iRow-1).getMaidens() + "\0", print_writers);
							break;
						default:
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$BowlingCard$BallDataAll$Normal$BallRow" + iRow + containerName + 
									"$txt_Dots*GEOM*TEXT SET " + inning.getBowlingCard().get(iRow-1).getDots() + "\0", print_writers);
							break;
						}
						
						//BALL PERFORMER
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$BowlingCard$BallDataAll$Split$Left$BallRow" + iRow + "*ACTIVE SET 1\0", print_writers);
						
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$BowlingCard$BallDataAll$Split$Left$BallRow" + iRow + 
								"$FadeForHighlight$txt_BowlerName*GEOM*TEXT SET " + inning.getBowlingCard().get(iRow-1).getPlayer().getTicker_name() + "\0", print_writers);
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$BowlingCard$BallDataAll$Split$Left$BallRow" + iRow + 
								"$Highlight$txt_BowlerName*GEOM*TEXT SET " + inning.getBowlingCard().get(iRow-1).getPlayer().getTicker_name() + "\0", print_writers);
						
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$BowlingCard$BallDataAll$Split$Left$BallRow" + iRow + 
								"$FadeForHighlight$txt_Fig*GEOM*TEXT SET " + inning.getBowlingCard().get(iRow-1).getWickets() + "-" + inning.getBowlingCard().get(iRow-1).getRuns() + "\0", print_writers);
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$BowlingCard$BallDataAll$Split$Left$BallRow" + iRow + 
								"$FadeForHighlight$txt_Over*GEOM*TEXT SET " + CricketFunctions.OverBalls(inning.getBowlingCard().get(iRow-1).getOvers(), inning.getBowlingCard().get(iRow-1).getBalls()) + "\0", print_writers);
						
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$BowlingCard$BallDataAll$Split$Left$BallRow" + iRow + 
								"$Highlight$txt_Fig*GEOM*TEXT SET " + inning.getBowlingCard().get(iRow-1).getWickets() + "-" + inning.getBowlingCard().get(iRow-1).getRuns() + "\0", print_writers);
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$BowlingCard$BallDataAll$Split$Left$BallRow" + iRow + 
								"$Highlight$txt_Over*GEOM*TEXT SET " + CricketFunctions.OverBalls(inning.getBowlingCard().get(iRow-1).getOvers(), inning.getBowlingCard().get(iRow-1).getBalls()) + "\0", print_writers);
					}
				}
			}
			
			if(inning.getBowlingCard().size() <= 7) {
				if(inning.getFallsOfWickets() != null) {
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$BowlingCard$BallDataAll$Normal$FOW_Grp*ACTIVE SET 1\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$BowlingCard$BallDataAll$Normal$FOW_Grp$"
							+ "FOW$txt_Title*GEOM*TEXT SET  FALL OF WICKETS\0", print_writers);
					for(int i =1; i<= 10; i++) {
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$BowlingCard$BallDataAll$Normal$FOW_Grp$Wickets$" 
								+ i + "$txt_Wicket*GEOM*TEXT SET " + CricketFunctions.ordinal(i) + "\0", print_writers);
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$BowlingCard$BallDataAll$Normal$FOW_Grp$Score$" 
								+ i + "$txt_Score*GEOM*TEXT SET \0", print_writers);
					}
					for(FallOfWicket fow : inning.getFallsOfWickets()) {
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$BowlingCard$BallDataAll$Normal$FOW_Grp$Score$" 
								+ fow.getFowNumber() + "$txt_Score*GEOM*TEXT SET " + fow.getFowRuns() + "\0", print_writers);
					}
				}
			}
			
			break;
		case Constants.TRI_SERIES: case Constants.MT20:
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide 
					+ "$select_GraphicsType*FUNCTION*Omo*vis_con SET 1\0", print_writers);
			
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide + "$BowlingCard"+
					"$BowlerGrp$1$select_BallRowType*FUNCTION*Omo*vis_con SET 0 \0", print_writers);
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide + "$BowlingCard"+
					"$BowlerDetailsGrp$1$select_RowType*FUNCTION*Omo*vis_con SET 1 \0", print_writers);
			
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide + "$BowlingCard"+
					"$BowlerGrp$Rows*FUNCTION*Grid*num_row SET 11 \0", print_writers);
			
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide + "$BowlingCard$" +
					"BowlerGrp$object_ScaleY*TRANSFORMATION*SCALING*Y SET 1.0\0", print_writers);
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide + "$BowlingCard$" +
					"BowlerDetailsGrp$object_ScaleY*TRANSFORMATION*SCALING*Y SET 1.0\0", print_writers);
			
			for(int j=1;j<=11;j++) {
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide + "$BowlingCard"+
						"$BowlerGrp$" + (j+1) + "$select_BallRowType*FUNCTION*Omo*vis_con SET 0 \0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide + "$BowlingCard"+
						"$BowlerDetailsGrp$" + (j+1) + "$select_RowType*FUNCTION*Omo*vis_con SET 0 \0", print_writers);
			}
			
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide + "$BowlingCard"+
					"$BowlerDetailsGrp$1$select_RowType$Title$1$txt_Title*GEOM*TEXT SET DOTS\0", print_writers);
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide + "$BowlingCard"+
					"$BowlerDetailsGrp$1$select_RowType$Title$2$txt_Title*GEOM*TEXT SET ECONOMY\0", print_writers);
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide + "$BowlingCard"+
					"$BowlerDetailsGrp$1$select_RowType$Title$3$txt_Title*GEOM*TEXT SET 4s CONC.\0", print_writers);
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide + "$BowlingCard"+
					"$BowlerDetailsGrp$1$select_RowType$Title$4$txt_Title*GEOM*TEXT SET 6s CONC.\0", print_writers);
			
			Collections.sort(inning.getBowlingCard());
			if(inning.getBowlingCard() != null && inning.getBowlingCard().size() > 0) {
				for(int iRow = 1; iRow <= inning.getBowlingCard().size(); iRow++) {
					if(inning.getBowlingCard().get(iRow-1).getRuns() > 0 || ((inning.getBowlingCard().get(iRow-1).getOvers()*6)
							+inning.getBowlingCard().get(iRow-1).getBalls()) > 0) {
						if(CricketFunctions.GetTargetData(matchAllData).getRemaningRuns() == 0 || matchAllData.getMatch().getInning().get(1).getTotalWickets() >= 10 || 
								CricketFunctions.GetTargetData(matchAllData).getRemaningBall()  == 0) {
							omo = 1;
							containerName = "$Dehighlight";
						}else {
							if(inning.getInningStatus().equalsIgnoreCase(CricketUtil.PAUSE)) {
								omo = 1;
								containerName = "$Dehighlight";
							}else {
								switch (inning.getBowlingCard().get(iRow-1).getStatus().toUpperCase()) {
								case (CricketUtil.OTHER + CricketUtil.BOWLER):
									omo = 1;
									containerName = "$Dehighlight";
									break;
								case (CricketUtil.LAST + CricketUtil.BOWLER):
									omo = 1;
									containerName = "$Dehighlight";
									break;
								case (CricketUtil.CURRENT + CricketUtil.BOWLER):
									omo = 2;
									containerName = "$Highlight";
									break;
								}
							}
						}
						
						String[] Count = CricketFunctions.getScoreTypeData(CricketUtil.BOWLER,matchAllData, inning.getInningNumber(), inning.getBowlingCard().get(iRow-1).getPlayer().getPlayerId(),
								"-", matchAllData.getEventFile().getEvents()).split("-");
						
//						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide + "$BowlingCard"+
//								"$BowlerDetailsGrp$" + (iRow+1) + "$BowlerDetailsGrp$Values$ValuesData*FUNCTION*Grid*num_col SET 3\0", print_writers);
						
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide + "$BowlingCard"+
								"$BowlerGrp$" + (iRow+1) + "$select_BallRowType*FUNCTION*Omo*vis_con SET " + omo + "\0", print_writers);
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide + "$BowlingCard"+
								"$BowlerDetailsGrp$" + (iRow+1) + "$select_RowType*FUNCTION*Omo*vis_con SET 2\0", print_writers);
						
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide + "$BowlingCard"+
								"$BowlerGrp$" + (iRow+1) + "$select_BallRowType" + containerName + "$txt_BowlerName*GEOM*TEXT SET " + 
								inning.getBowlingCard().get(iRow-1).getPlayer().getTicker_name() + "\0", print_writers);
						
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide + "$BowlingCard"+
								"$BowlerGrp$" + (iRow+1) + "$select_BallRowType" + containerName + "$txt_Figures*GEOM*TEXT SET " + 
								inning.getBowlingCard().get(iRow-1).getWickets() + "-" + inning.getBowlingCard().get(iRow-1).getRuns() + "\0", print_writers);
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide + "$BowlingCard"+
								"$BowlerGrp$" + (iRow+1) + "$select_BallRowType" + containerName + "$txt_Overs*GEOM*TEXT SET " + CricketFunctions.
								OverBalls(inning.getBowlingCard().get(iRow-1).getOvers(), inning.getBowlingCard().get(iRow-1).getBalls()) + "\0", print_writers);
						
						
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide + "$BowlingCard"+
								"$BowlerDetailsGrp$" + (iRow+1) + "$select_RowType$Values$1$txt_Value*GEOM*TEXT SET " + 
								inning.getBowlingCard().get(iRow-1).getDots() + "\0", print_writers);
						
						if(inning.getBowlingCard().get(iRow-1).getEconomyRate() != null) {
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide + "$BowlingCard"+
									"$BowlerDetailsGrp$" + (iRow+1) + "$select_RowType$Values$2$txt_Value*GEOM*TEXT SET " + 
									inning.getBowlingCard().get(iRow-1).getEconomyRate() + "\0", print_writers);
						}else {
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide + "$BowlingCard"+
									"$BowlerDetailsGrp$" + (iRow+1) + "$select_RowType$Values$2$txt_Value*GEOM*TEXT SET -\0", print_writers);
						}
						
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide + "$BowlingCard"+
								"$BowlerDetailsGrp$" + (iRow+1) + "$select_RowType$Values$3$txt_Value*GEOM*TEXT SET " + 
								Count[4] + "\0", print_writers);
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide + "$BowlingCard"+
								"$BowlerDetailsGrp$" + (iRow+1) + "$select_RowType$Values$4$txt_Value*GEOM*TEXT SET " + 
								Count[6] + "\0", print_writers);
					}
				}
			}
			
			if(inning.getBowlingCard().size() <= 7) {
				
				if(inning.getFallsOfWickets() != null) {
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide + "$BowlingCard"+
							"$BowlerDetailsGrp$9$select_RowType*FUNCTION*Omo*vis_con SET 3\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide + "$BowlingCard"+
							"$BowlerDetailsGrp$10$select_RowType*FUNCTION*Omo*vis_con SET 4\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide + "$BowlingCard"+
							"$BowlerDetailsGrp$11$select_RowType*FUNCTION*Omo*vis_con SET 5\0", print_writers);
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide + "$BowlingCard"+
							"$BowlerDetailsGrp$9$select_RowType$FOW_Title$txt_FOW*GEOM*TEXT SET  FALL OF WICKETS\0", print_writers);
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide + "$BowlingCard"+
							"$BowlerDetailsGrp$10$select_RowType$Wickets$Wickets*FUNCTION*Grid*num_col SET " + 10 + "\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide + "$BowlingCard"+
							"$BowlerDetailsGrp$11$select_RowType$Score$Runs*FUNCTION*Grid*num_col SET " + inning.getFallsOfWickets().size() + "\0", print_writers);
					
					for(int i =1; i<= 10; i++) {
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide + "$BowlingCard"+
								"$BowlerDetailsGrp$10$select_RowType$Wickets$Wickets$fig_" + i + "*GEOM*TEXT SET " + i + "\0", print_writers);
						
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide + "$BowlingCard"+
								"$BowlerDetailsGrp$11$select_RowType$Score$Runs$fig_" + i + "*GEOM*TEXT SET \0", print_writers);
					}
					
					for(FallOfWicket fow : inning.getFallsOfWickets()) {
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide + "$BowlingCard"+
								"$BowlerDetailsGrp$10$select_RowType$Wickets$Wickets$fig_" + fow.getFowNumber() + "*GEOM*TEXT SET " + fow.getFowNumber() + "\0", print_writers);
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide + "$BowlingCard"+
								"$BowlerDetailsGrp$11$select_RowType$Score$Runs$fig_" + fow.getFowNumber() + "*GEOM*TEXT SET " + fow.getFowRuns() + "\0", print_writers);
					}
				}
			}
			break;
		}
		return Constants.OK;
	}
	public String PartnershipListBody(List<PrintWriter> print_writers, int WhichSide, Configuration config, MatchAllData matchAllData,Inning inning) {
		
		int omo_num=0,Top_Score = 50;
		double Mult = 0,ScaleFac1 = 0, ScaleFac2 = 0;
		
		switch (config.getBroadcaster()) {
		case Constants.AFG_SL_SERIES:
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$select_GraphicsType"
					+ "*FUNCTION*Omo*vis_con SET 6\0", print_writers);
			
			for(int a = 1; a <= inning.getPartnerships().size(); a++){
				if(inning.getPartnerships().get(a-1).getFirstBatterRuns() > Top_Score) {
					Top_Score = inning.getPartnerships().get(a-1).getFirstBatterRuns();
				}
				if(inning.getPartnerships().get(a-1).getSecondBatterRuns() > Top_Score) {
					Top_Score = inning.getPartnerships().get(a-1).getSecondBatterRuns();
				}
			}
			
			rowId = 0;
			Mult = 190;
			
			for (Partnership ps : inning.getPartnerships()) {
				rowId = rowId + 1;	
				if(inning.getPartnerships().size() >= 10 && inning.getTotalWickets()>=10) {
					if(ps.getPartnershipNumber()<=inning.getPartnerships().size()) {
						omo_num = 2;
						containerName = "$Out";
					}
				}
				else {
					if(ps.getPartnershipNumber() < inning.getPartnerships().size()) {
						omo_num = 2;
						containerName = "$Out";
					}
					else if(ps.getPartnershipNumber() >= inning.getPartnerships().size()) {
						omo_num = 3;
						containerName = "$NotOut";
					}
				}
				
				ScaleFac1 = ((ps.getFirstBatterRuns())*(Mult/Top_Score)) ;
				ScaleFac2 = ((ps.getSecondBatterRuns())*(Mult/Top_Score)) ;
				
				if(inning.getTotalWickets() >= 9) {
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$PartnershipList$MainData"
							+ "*FUNCTION*Grid*num_row SET " + inning.getPartnerships().size() + "\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$PartnershipList$MainData"
							+ "*FUNCTION*Grid*row_offset SET " + "55" + "\0", print_writers);
				}else {
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$PartnershipList$MainData"
							+ "*FUNCTION*Grid*num_row SET " + inning.getBattingCard().size() + "\0", print_writers);
					
					if(inning.getBattingCard().size() == 12) {
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$PartnershipList$MainData"
								+ "*FUNCTION*Grid*row_offset SET " + "52" + "\0", print_writers);
					}else if(inning.getBattingCard().size() == 13) {
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$PartnershipList$MainData"
								+ "*FUNCTION*Grid*row_offset SET " + "48" + "\0", print_writers);
					}else if(inning.getBattingCard().size() == 11) {
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$PartnershipList$MainData"
								+ "*FUNCTION*Grid*row_offset SET " + "55" + "\0", print_writers);
					}
				}
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$PartnershipList$Grp" + rowId 
						+ "$select_DataType*FUNCTION*Omo*vis_con SET " + omo_num + "\0", print_writers);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$PartnershipList$Grp" + rowId 
						+ containerName + "$txt_Name_1*GEOM*TEXT SET " + ps.getFirstPlayer().getTicker_name() + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$PartnershipList$Grp" + rowId 
						+ containerName + "$txt_Name_2*GEOM*TEXT SET " + ps.getSecondPlayer().getTicker_name() + "\0", print_writers);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$PartnershipList$Grp" + rowId 
						+ containerName + "$txt_Scores*GEOM*TEXT SET " + ps.getTotalRuns() + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$PartnershipList$Grp" + rowId 
						+ containerName + "$txt_Balls*GEOM*TEXT SET " + ps.getTotalBalls() + "\0", print_writers);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$PartnershipList$Grp" + rowId 
						+ containerName + "$Bars$obj_Widht1*GEOM*width SET " + ScaleFac1 + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$PartnershipList$Grp" + rowId 
						+ containerName + "$Bars$obj_Widht2*GEOM*width SET " + ScaleFac2 + "\0", print_writers);
			}
			
			if(inning.getPartnerships().size() >= 10) {
				rowId = rowId + 1;
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$PartnershipList$Grp" + rowId 
						+ "$select_DataType*FUNCTION*Omo*vis_con SET 0\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$PartnershipList$Grp" + rowId 
						+ "$Title$txt_Title*GEOM*TEXT SET \0", print_writers);
			}else {
				for (BattingCard bc : inning.getBattingCard()) {
					if(rowId < inning.getBattingCard().size()) {
						if(rowId == inning.getPartnerships().size()) {
							rowId = rowId + 1;
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$PartnershipList$Grp" + rowId 
									+ "$select_DataType*FUNCTION*Omo*vis_con SET 0\0", print_writers);
							
							if(inning.getTotalOvers() == matchAllData.getSetup().getMaxOvers() || inning.getTotalWickets() >= 10 ) {
								CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$PartnershipList$Grp" + rowId 
										+ "$Title$txt_Title*GEOM*TEXT SET DID NOT BAT\0", print_writers);
							}
							else if(matchAllData.getSetup().getTargetOvers() != null && !matchAllData.getSetup().getTargetOvers().trim().isEmpty()) {
								
								if(inning.getInningStatus().equalsIgnoreCase(CricketUtil.PAUSE)) {
									CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$PartnershipList$Grp" + rowId 
											+ "$Title$txt_Title*GEOM*TEXT SET DID NOT BAT\0", print_writers);
								}else {
									
									if(matchAllData.getSetup().getTargetOvers().contains(".")) {
										int TotalBallsBowled = (inning.getTotalOvers()*6) + inning.getTotalBalls();
										int TotalBalls = (Integer.valueOf(matchAllData.getSetup().getTargetOvers().split("\\.")[0])*6) + 
												Integer.valueOf(matchAllData.getSetup().getTargetOvers().split("\\.")[1]);
										
										if(TotalBallsBowled == TotalBalls || inning.getTotalWickets() >= 10) {
											CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$PartnershipList$Grp" + rowId 
													+ "$Title$txt_Title*GEOM*TEXT SET DID NOT BAT\0", print_writers);
										}else if(CricketFunctions.GetTargetData(matchAllData).getRemaningRuns() <= 0 || CricketFunctions.GetTargetData(matchAllData).getRemaningBall() <= 0 
												|| CricketFunctions.getWicketsLeft(matchAllData, inning.getInningNumber()) <= 0) {
											CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$PartnershipList$Grp" + rowId 
													+ "$Title$txt_Title*GEOM*TEXT SET DID NOT BAT\0", print_writers);
										}else {
											CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$PartnershipList$Grp" + rowId 
													+ "$Title$txt_Title*GEOM*TEXT SET STILL TO BAT\0", print_writers);
										}
									}else {
										if(inning.getTotalOvers() == Integer.valueOf(matchAllData.getSetup().getTargetOvers()) || inning.getTotalWickets() >= 10) {
											CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$PartnershipList$Grp" + rowId 
													+ "$Title$txt_Title*GEOM*TEXT SET DID NOT BAT\0", print_writers);
										}else if(CricketFunctions.GetTargetData(matchAllData).getRemaningRuns() <= 0 || CricketFunctions.GetTargetData(matchAllData).getRemaningBall() <= 0 
												|| CricketFunctions.getWicketsLeft(matchAllData, inning.getInningNumber()) <= 0) {
											CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$PartnershipList$Grp" + rowId 
													+ "$Title$txt_Title*GEOM*TEXT SET DID NOT BAT\0", print_writers);
										}else {
											CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$PartnershipList$Grp" + rowId 
													+ "$Title$txt_Title*GEOM*TEXT SET STILL TO BAT\0", print_writers);
										}
									}
								}
							}
							else if(CricketFunctions.GetTargetData(matchAllData).getRemaningRuns() <= 0 || CricketFunctions.GetTargetData(matchAllData).getRemaningBall() <= 0 ||
									CricketFunctions.getWicketsLeft(matchAllData, inning.getInningNumber()) <= 0) {
								CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$PartnershipList$Grp" + rowId 
										+ "$Title$txt_Title*GEOM*TEXT SET DID NOT BAT\0", print_writers);
							}
							else if(inning.getInningStatus().equalsIgnoreCase(CricketUtil.PAUSE)) {
								CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$PartnershipList$Grp" + rowId 
										+ "$Title$txt_Title*GEOM*TEXT SET DID NOT BAT\0", print_writers);
							}
							else {
								CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$PartnershipList$Grp" + rowId 
										+ "$Title$txt_Title*GEOM*TEXT SET STILL TO BAT\0", print_writers);
							}
						}
						else if(bc.getStatus().toUpperCase().equalsIgnoreCase(CricketUtil.STILL_TO_BAT)) {
							rowId = rowId + 1;
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$PartnershipList$Grp" + rowId 
									+ "$select_DataType*FUNCTION*Omo*vis_con SET 1\0", print_writers);
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$PartnershipList$Grp" + rowId 
									+ "$StillToBat$txt_BatterName*GEOM*TEXT SET " + bc.getPlayer().getTicker_name() + "\0", print_writers);
						}	
					}
					else {
						break;
					}
				}
			}
			
			break;
		case Constants.BAN_AFG_SERIES: case Constants.ACC:
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$Select*FUNCTION*Omo*vis_con SET 3\0", print_writers);
			
			for(int a = 1; a <= inning.getPartnerships().size(); a++){
				if(inning.getPartnerships().get(a-1).getFirstBatterRuns() > Top_Score) {
					Top_Score = inning.getPartnerships().get(a-1).getFirstBatterRuns();
				}
				if(inning.getPartnerships().get(a-1).getSecondBatterRuns() > Top_Score) {
					Top_Score = inning.getPartnerships().get(a-1).getSecondBatterRuns();
				}
			}
			
			rowId = 0;
			Mult = 60;
			
			for (Partnership ps : inning.getPartnerships()) {
				rowId = rowId + 1;	
				if(inning.getPartnerships().size() >= 10 && inning.getTotalWickets()>=10) {
					if(ps.getPartnershipNumber()<=inning.getPartnerships().size()) {
						omo_num = 2;
						containerName = "$Out";
					}
				}
				else {
					if(ps.getPartnershipNumber() < inning.getPartnerships().size()) {
						omo_num = 2;
						containerName = "$Out";
					}
					else if(ps.getPartnershipNumber() >= inning.getPartnerships().size()) {
						omo_num = 3;
						containerName = "$NotOut";
					}
				}
				
				ScaleFac1 = ((ps.getFirstBatterRuns())*(Mult/Top_Score)) ;
				ScaleFac2 = ((ps.getSecondBatterRuns())*(Mult/Top_Score)) ;
				
				if(inning.getTotalWickets() >= 9) {
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$PartnershipCard$PartGrp"
							+ "*FUNCTION*Grid*num_row SET " + inning.getPartnerships().size() + "\0", print_writers);
				}else {
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$PartnershipCard$PartGrp"
							+ "*FUNCTION*Grid*num_row SET " + inning.getBattingCard().size() + "\0", print_writers);
					
					if(inning.getBattingCard().size() == 12) {
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$PartnershipCard$PartGrp"
								+ "*FUNCTION*Grid*row_offset SET " + "18.2" + "\0", print_writers);
					}else if(inning.getBattingCard().size() == 13) {
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$PartnershipCard$PartGrp"
								+ "*FUNCTION*Grid*row_offset SET " + "17" + "\0", print_writers);
					}else if(inning.getBattingCard().size() == 11) {
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$PartnershipCard$PartGrp"
								+ "*FUNCTION*Grid*row_offset SET " + "20" + "\0", print_writers);
					}
				}
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$PartnershipCard$PartRow" + rowId 
						+ "$PartSelection*FUNCTION*Omo*vis_con SET " + omo_num + "\0", print_writers);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$PartnershipCard$PartGrp$PartRow" + rowId 
						+ containerName + "$txt_BatterName1*GEOM*TEXT SET " + ps.getFirstPlayer().getTicker_name() + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$PartnershipCard$PartGrp$PartRow" + rowId 
						+ containerName + "$txt_BatterName2*GEOM*TEXT SET " + ps.getSecondPlayer().getTicker_name() + "\0", print_writers);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$PartnershipCard$PartGrp$PartRow" + rowId 
						+ containerName + "$txt_PartRuns*GEOM*TEXT SET " + ps.getTotalRuns() + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$PartnershipCard$PartGrp$PartRow" + rowId 
						+ containerName + "$txt_PartBalls*GEOM*TEXT SET " + ps.getTotalBalls() + "\0", print_writers);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$PartnershipCard$PartGrp$PartRow" + rowId 
						+ containerName + "$Bars$Bar_1*GEOM*width SET " + ScaleFac1 + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$PartnershipCard$PartGrp$PartRow" + rowId 
						+ containerName + "$Bars$Bar_2*GEOM*width SET " + ScaleFac2 + "\0", print_writers);
			}
			
			if(inning.getPartnerships().size() >= 10) {
				rowId = rowId + 1;
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$PartnershipCard$PartGrp$PartRow" + rowId 
						+ "$PartSelection*FUNCTION*Omo*vis_con SET 0\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$PartnershipCard$PartGrp$PartRow" + rowId 
						+ "$Title$txt_Title*GEOM*TEXT SET \0", print_writers);
			}else {
				for (BattingCard bc : inning.getBattingCard()) {
					if(rowId < inning.getBattingCard().size()) {
						if(rowId == inning.getPartnerships().size()) {
							rowId = rowId + 1;
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$PartnershipCard$PartGrp$PartRow" + rowId 
									+ "$PartSelection*FUNCTION*Omo*vis_con SET 0\0", print_writers);
							
							if(inning.getTotalOvers() == matchAllData.getSetup().getMaxOvers() || inning.getTotalWickets() >= 10 ) {
								CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$PartnershipCard$PartGrp$PartRow" + rowId 
										+ "$Title$txt_Title*GEOM*TEXT SET DID NOT BAT\0", print_writers);
							}
							else if(matchAllData.getSetup().getTargetOvers() != null && !matchAllData.getSetup().getTargetOvers().trim().isEmpty()) {
								
								if(inning.getInningStatus().equalsIgnoreCase(CricketUtil.PAUSE)) {
									CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$PartnershipCard$PartGrp$PartRow" + rowId 
											+ "$Title$txt_Title*GEOM*TEXT SET DID NOT BAT\0", print_writers);
								}else {
									
									if(matchAllData.getSetup().getTargetOvers().contains(".")) {
										int TotalBallsBowled = (inning.getTotalOvers()*6) + inning.getTotalBalls();
										int TotalBalls = (Integer.valueOf(matchAllData.getSetup().getTargetOvers().split("\\.")[0])*6) + 
												Integer.valueOf(matchAllData.getSetup().getTargetOvers().split("\\.")[1]);
										
										if(TotalBallsBowled == TotalBalls || inning.getTotalWickets() >= 10) {
											CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$PartnershipCard$PartGrp$PartRow" + rowId 
													+ "$Title$txt_Title*GEOM*TEXT SET DID NOT BAT\0", print_writers);
										}else if(CricketFunctions.GetTargetData(matchAllData).getRemaningRuns() <= 0 || CricketFunctions.GetTargetData(matchAllData).getRemaningBall() <= 0 
												|| CricketFunctions.getWicketsLeft(matchAllData, inning.getInningNumber()) <= 0) {
											CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$PartnershipCard$PartGrp$PartRow" + rowId 
													+ "$Title$txt_Title*GEOM*TEXT SET DID NOT BAT\0", print_writers);
										}else {
											CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$PartnershipCard$PartGrp$PartRow" + rowId 
													+ "$Title$txt_Title*GEOM*TEXT SET STILL TO BAT\0", print_writers);
										}
									}else {
										if(inning.getTotalOvers() == Integer.valueOf(matchAllData.getSetup().getTargetOvers()) || inning.getTotalWickets() >= 10) {
											CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$PartnershipCard$PartGrp$PartRow" + rowId 
													+ "$Title$txt_Title*GEOM*TEXT SET DID NOT BAT\0", print_writers);
										}else if(CricketFunctions.GetTargetData(matchAllData).getRemaningRuns() <= 0 || CricketFunctions.GetTargetData(matchAllData).getRemaningBall() <= 0 
												|| CricketFunctions.getWicketsLeft(matchAllData, inning.getInningNumber()) <= 0) {
											CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$PartnershipCard$PartGrp$PartRow" + rowId 
													+ "$Title$txt_Title*GEOM*TEXT SET DID NOT BAT\0", print_writers);
										}else {
											CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$PartnershipCard$PartGrp$PartRow" + rowId 
													+ "$Title$txt_Title*GEOM*TEXT SET STILL TO BAT\0", print_writers);
										}
									}
								}
							}
							else if(CricketFunctions.GetTargetData(matchAllData).getRemaningRuns() <= 0 || CricketFunctions.GetTargetData(matchAllData).getRemaningBall() <= 0 ||
									CricketFunctions.getWicketsLeft(matchAllData, inning.getInningNumber()) <= 0) {
								CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$PartnershipCard$PartGrp$PartRow" + rowId 
										+ "$Title$txt_Title*GEOM*TEXT SET DID NOT BAT\0", print_writers);
							}
							else if(inning.getInningStatus().equalsIgnoreCase(CricketUtil.PAUSE)) {
								CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$PartnershipCard$PartGrp$PartRow" + rowId 
										+ "$Title$txt_Title*GEOM*TEXT SET DID NOT BAT\0", print_writers);
							}
							else {
								CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$PartnershipCard$PartGrp$PartRow" + rowId 
										+ "$Title$txt_Title*GEOM*TEXT SET STILL TO BAT\0", print_writers);
							}
						}
						else if(bc.getStatus().toUpperCase().equalsIgnoreCase(CricketUtil.STILL_TO_BAT)) {
							rowId = rowId + 1;
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$PartnershipCard$PartGrp$PartRow" + rowId 
									+ "$PartSelection*FUNCTION*Omo*vis_con SET 1\0", print_writers);
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$PartnershipCard$PartGrp$PartRow" + rowId 
									+ "$StillToBat$txt_BatterName*GEOM*TEXT SET " + bc.getPlayer().getTicker_name() + "\0", print_writers);
						}	
					}
					else {
						break;
					}
				}
			}
			
			break;
		case Constants.TRI_SERIES: case Constants.MT20:
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide 
					+ "$select_GraphicsType*FUNCTION*Omo*vis_con SET 5\0", print_writers);
			
			
			for(int a = 1; a <= inning.getPartnerships().size(); a++){
				if(inning.getPartnerships().get(a-1).getFirstBatterRuns() > Top_Score) {
					Top_Score = inning.getPartnerships().get(a-1).getFirstBatterRuns();
				}
				if(inning.getPartnerships().get(a-1).getSecondBatterRuns() > Top_Score) {
					Top_Score = inning.getPartnerships().get(a-1).getSecondBatterRuns();
				}
			}
			
			rowId = 0;
			Mult = 81;
			for (Partnership ps : inning.getPartnerships()) {
				rowId = rowId + 1;	
				if(inning.getPartnerships().size() >= 10 && inning.getTotalWickets()>=10) {
					if(ps.getPartnershipNumber()<=inning.getPartnerships().size()) {
						omo_num = 2;
						containerName = "$Out";
					}
				}
				else {
					if(ps.getPartnershipNumber() < inning.getPartnerships().size()) {
						omo_num = 2;
						containerName = "$Out";
					}
					else if(ps.getPartnershipNumber() >= inning.getPartnerships().size()) {
						omo_num = 3;
						containerName = "$NotOut";
					}
				}
				
				ScaleFac1 = ((ps.getFirstBatterRuns())*(Mult/Top_Score)) ;
				ScaleFac2 = ((ps.getSecondBatterRuns())*(Mult/Top_Score)) ;
				
				if(inning.getTotalWickets() >= 9) {
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$AllGraphics$Side" + WhichSide + "$PartnershipList$Rows"
							+ "*FUNCTION*Grid*num_row SET " + inning.getPartnerships().size() + "\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$AllGraphics$Side" + WhichSide + "$PartnershipList$object_ScaleY"
							+ "*TRANSFORMATION*SCALING*Y SET 1.11\0", print_writers);
				}else {
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$AllGraphics$Side" + WhichSide + "$PartnershipList$Rows"
							+ "*FUNCTION*Grid*num_row SET " + inning.getBattingCard().size() + "\0", print_writers);
					if(inning.getBattingCard().size() == 11) {
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$AllGraphics$Side" + WhichSide + "$PartnershipList$object_ScaleY"
								+ "*TRANSFORMATION*SCALING*Y SET 1.000\0", print_writers);
					}else if(inning.getBattingCard().size() == 12) {
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$AllGraphics$Side" + WhichSide + "$PartnershipList$object_ScaleY"
								+ "*TRANSFORMATION*SCALING*Y SET 0.915\0", print_writers);
					}else if(inning.getBattingCard().size() == 13) {
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$AllGraphics$Side" + WhichSide + "$PartnershipList$object_ScaleY"
								+ "*TRANSFORMATION*SCALING*Y SET 0.845\0", print_writers);
					}
				}
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$AllGraphics$Side" + WhichSide + "$PartnershipList$Rows$" + rowId 
						+ "$select_RowType*FUNCTION*Omo*vis_con SET " + omo_num + "\0", print_writers);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$AllGraphics$Side" + WhichSide + "$PartnershipList$Rows$" + rowId 
						+ containerName + "$Data$txt_Name_1*GEOM*TEXT SET " + ps.getFirstPlayer().getTicker_name() + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$AllGraphics$Side" + WhichSide + "$PartnershipList$Rows$" + rowId 
						+ containerName + "$Data$txt_Name_2*GEOM*TEXT SET " + ps.getSecondPlayer().getTicker_name() + "\0", print_writers);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$AllGraphics$Side" + WhichSide + "$PartnershipList$Rows$" + rowId 
						+ containerName + "$Data$ScoreAll$txt_Runs*GEOM*TEXT SET " + ps.getTotalRuns() + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$AllGraphics$Side" + WhichSide + "$PartnershipList$Rows$" + rowId 
						+ containerName + "$Data$ScoreAll$txt_Balls*GEOM*TEXT SET " + ps.getTotalBalls() + "\0", print_writers);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$AllGraphics$Side" + WhichSide + "$PartnershipList$Rows$" + rowId 
						+ containerName + "$Data$Geom_Bars$Geom_Bar_1*GEOM*width SET " + ScaleFac1 + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$AllGraphics$Side" + WhichSide + "$PartnershipList$Rows$" + rowId 
						+ containerName + "$Data$Geom_Bars$Geom_Bar_2*GEOM*width SET " + ScaleFac2 + "\0", print_writers);
			}
			
			if(inning.getPartnerships().size() >= 10) {
				rowId = rowId + 1;
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$AllGraphics$Side" + WhichSide + "$PartnershipList$Rows$" + rowId 
						+ "$select_RowType*FUNCTION*Omo*vis_con SET 0\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$AllGraphics$Side" + WhichSide + "$PartnershipList$Rows$" + rowId 
						+ "$Title$txt_Title*GEOM*TEXT SET \0", print_writers);
			}else {
				for (BattingCard bc : inning.getBattingCard()) {
					if(rowId < inning.getBattingCard().size()) {
						if(rowId == inning.getPartnerships().size()) {
							rowId = rowId + 1;
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$AllGraphics$Side" + WhichSide + "$PartnershipList$Rows$" 
									+ rowId + "$select_RowType*FUNCTION*Omo*vis_con SET 0\0", print_writers);
							
							if(inning.getTotalOvers() == matchAllData.getSetup().getMaxOvers() || inning.getTotalWickets() >= 10 ) {
								CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$AllGraphics$Side" + WhichSide + "$PartnershipList$Rows$" 
										+ rowId + "$Title$txt_Title*GEOM*TEXT SET DID NOT BAT\0", print_writers);
							}
							else if(matchAllData.getSetup().getTargetOvers() != null && !matchAllData.getSetup().getTargetOvers().trim().isEmpty()) {
								
								if(inning.getInningStatus().equalsIgnoreCase(CricketUtil.PAUSE)) {
									CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$AllGraphics$Side" + WhichSide + "$PartnershipList$Rows$" 
											+ rowId + "$Title$txt_Title*GEOM*TEXT SET DID NOT BAT\0", print_writers);
								}else {
									if(inning.getTotalOvers() == Integer.valueOf(matchAllData.getSetup().getTargetOvers()) || inning.getTotalWickets() >= 10) {
										CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$AllGraphics$Side" + WhichSide + "$PartnershipList$Rows$" 
												+ rowId + "$Title$txt_Title*GEOM*TEXT SET DID NOT BAT\0", print_writers);
									}else if(CricketFunctions.GetTargetData(matchAllData).getRemaningRuns() <= 0 || CricketFunctions.GetTargetData(matchAllData).getRemaningBall() <= 0 
											|| CricketFunctions.getWicketsLeft(matchAllData, inning.getInningNumber()) <= 0) {
										CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$AllGraphics$Side" + WhichSide + "$PartnershipList$Rows$" 
												+ rowId + "$Title$txt_Title*GEOM*TEXT SET DID NOT BAT\0", print_writers);
									}else {
										CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$AllGraphics$Side" + WhichSide + "$PartnershipList$Rows$" 
												+ rowId + "$Title$txt_Title*GEOM*TEXT SET STILL TO BAT\0", print_writers);
									}
								}
							}
							else if(CricketFunctions.GetTargetData(matchAllData).getRemaningRuns() <= 0 || CricketFunctions.GetTargetData(matchAllData).getRemaningBall() <= 0 ||
									CricketFunctions.getWicketsLeft(matchAllData, inning.getInningNumber()) <= 0) {
								CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$AllGraphics$Side" + WhichSide + "$PartnershipList$Rows$" 
										+ rowId + "$Title$txt_Title*GEOM*TEXT SET DID NOT BAT\0", print_writers);
							}
							else if(inning.getInningStatus().equalsIgnoreCase(CricketUtil.PAUSE)) {
								CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$AllGraphics$Side" + WhichSide + "$PartnershipList$Rows$" 
										+ rowId + "$Title$txt_Title*GEOM*TEXT SET DID NOT BAT\0", print_writers);
							}
							else {
								CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$AllGraphics$Side" + WhichSide + "$PartnershipList$Rows$" 
										+ rowId + "$Title$txt_Title*GEOM*TEXT SET STILL TO BAT\0", print_writers);
							}
						}
						else if(bc.getStatus().toUpperCase().equalsIgnoreCase(CricketUtil.STILL_TO_BAT)) {
							rowId = rowId + 1;
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$AllGraphics$Side" + WhichSide + "$PartnershipList$Rows$" 
									+ rowId + "$select_RowType*FUNCTION*Omo*vis_con SET 1\0", print_writers);
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$AllGraphics$Side" + WhichSide + "$PartnershipList$Rows$" 
									+ rowId + "$Still_To_Bat$txt_Title*GEOM*TEXT SET " + bc.getPlayer().getTicker_name() + "\0", print_writers);
						}	
					}
					else {
						break;
					}
				}
			}
			break;
		}
		return Constants.OK;
	}
	public String SummaryBody(List<PrintWriter> print_writers, int WhichSide, Configuration config, MatchAllData matchAllData,Inning inning, int WhichInning) {
		
		String filePath = "C:\\Sports\\Cricket\\ReduceOver.txt",line1 = "", line2 = "", line3 = "";
		
		File file = new File(filePath);

        if (!file.exists()) {
        	line1 = "no";
        }else {
        	try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
	            line1 = br.readLine();
	            if (line1 != null && line1.equalsIgnoreCase("yes")) {
	                line2 = br.readLine();
	            }else if (line1 != null && line1.equalsIgnoreCase("no")) {
                    line3 = br.readLine();
                }
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
        }
        
		switch (config.getBroadcaster()) {
		case Constants.AFG_SL_SERIES:
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$select_GraphicsType"
					+ "*FUNCTION*Omo*vis_con SET 5\0", print_writers);
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$Summary$select_InningsNumber"
					+ "*FUNCTION*Omo*vis_con SET " + WhichInning + "\0", print_writers);
			
			if(WhichInning == 1) {
				containerName = "InningsGrp1$Innings";
			}else if(WhichInning == 2) {
				containerName = "InningsGrp2$Innings";
			}
			numberOfRows = 4;
			for(int i = 1; i <= WhichInning ; i++) {
				if(i == 1) {
					rowId=0;
				}else {
					rowId=0;
				}
				
				if(matchAllData.getMatch().getInning().get(i-1).getBattingTeamId() == matchAllData.getSetup().getTossWinningTeam()) {
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$Summary$" + containerName 
							+ i + "$Title$select_Toss*FUNCTION*Omo*vis_con SET 1\0", print_writers);
				}else {
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$Summary$" + containerName 
							+ i + "$Title$select_Toss*FUNCTION*Omo*vis_con SET 0\0", print_writers);				
				}
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$Summary$" + containerName 
						+ i + "$Title$txt_TeamName*GEOM*TEXT SET " + matchAllData.getMatch().getInning().get(i-1).getBatting_team().getTeamName1() + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$Summary$" + containerName 
						+ i + "$Title$txt_Scores*GEOM*TEXT SET " + CricketFunctions.getTeamScore(matchAllData.getMatch().getInning().get(i-1),"-", false) 
						+ "\0", print_writers);
				
				String OverData = "";
				if(i==1) {
					if(line1.equalsIgnoreCase("YES")) {
						OverData = "(" + line2 + ")";
					}else if(line1.equalsIgnoreCase("NO")) {
						if(matchAllData.getSetup().getReducedOvers() != null) {
							OverData = (Integer.valueOf(matchAllData.getSetup().getReducedOvers()) > 0 ? " (" + 
									matchAllData.getSetup().getReducedOvers() + ")":"");
						}
					}
				}else if(i==2) {
					if(line1.equalsIgnoreCase("YES")) {
						OverData = "(" + line3 + ")";
					}else if(line1.equalsIgnoreCase("NO")) {
						OverData = (matchAllData.getSetup().getTargetOvers() != null && !matchAllData.getSetup().getTargetOvers().trim().isEmpty() ? " (" + matchAllData.getSetup().getTargetOvers() + ")":"");
					}
				}
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$Summary$" + containerName 
						+ i + "$Title$txt_OversHead*GEOM*TEXT SET " + (matchAllData.getMatch().getInning().get(i-1).getTotalOvers() == 1 && 
						matchAllData.getMatch().getInning().get(i-1). getTotalBalls() == 0 ? "OVER " : "OVERS ") + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$Summary$" + containerName 
						+ i + "$Title$txt_OversValue*GEOM*TEXT SET " + CricketFunctions.OverBalls(matchAllData.getMatch().getInning().get(i-1).getTotalOvers(),
								matchAllData.getMatch().getInning().get(i-1).getTotalBalls()) + OverData + "\0", print_writers);
				
				if(matchAllData.getMatch().getInning().get(i-1).getBattingCard() != null) {
					Collections.sort(matchAllData.getMatch().getInning().get(i-1).getBattingCard(),new CricketFunctions.BatsmenScoreComparator());
					for(BattingCard bc : matchAllData.getMatch().getInning().get(i-1).getBattingCard()) {
						if(bc.getRuns() > 0) {
							rowId++;
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$Summary$" + containerName 
									+ i + "$Grp" + rowId + "$Batsman*ACTIVE SET 1\0", print_writers);
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$Summary$" + containerName 
									+ i + "$Grp" + rowId + "$Batsman$txt_BatterName*GEOM*TEXT SET " + bc.getPlayer().getTicker_name() + "\0", print_writers);
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$Summary$" + containerName 
									+ i + "$Grp" + rowId + "$Batsman$txt_Scores*GEOM*TEXT SET " + bc.getRuns() + "\0", print_writers);
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$Summary$" + containerName 
									+ i + "$Grp" + rowId + "$Batsman$txt_NotOutStar*GEOM*TEXT SET " + (bc.getStatus().toUpperCase().equalsIgnoreCase(CricketUtil.NOT_OUT) 
											? "*" : "") + "\0", print_writers);
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$Summary$" + containerName 
									+ i + "$Grp" + rowId + "$Batsman$txt_Balls*GEOM*TEXT SET " + String.valueOf(bc.getBalls()) + "\0", print_writers);
							
							if(i == 1 && rowId >= numberOfRows) {
								break;
							}else if(i == 2 && rowId >= numberOfRows) {
								break;
							}
						}
					}
				}
				for(int j = rowId+1; j <= numberOfRows; j++) {
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$Summary$" + containerName 
							+ i + "$Grp" + j + "$Batsman*ACTIVE SET 0\0", print_writers);
				}
				
				if(i == 1) {
					rowId = 0;
				}
				else {
					rowId = 0;
				}
				
				if(matchAllData.getMatch().getInning().get(i-1).getBowlingCard() != null) {
					Collections.sort(matchAllData.getMatch().getInning().get(i-1).getBowlingCard(),new CricketFunctions.BowlerFiguresComparator());
					for(BowlingCard boc : matchAllData.getMatch().getInning().get(i-1).getBowlingCard()) {
						
						if(boc.getWickets() > 0) {
							rowId++;
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$Summary$" + containerName 
									+ i + "$Grp" + rowId + "$Bowler*ACTIVE SET 1\0", print_writers);
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$Summary$" + containerName 
									+ i + "$Grp" + rowId + "$Bowler$txt_BatterName*GEOM*TEXT SET " + boc.getPlayer().getTicker_name() + "\0", print_writers);
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$Summary$" + containerName 
									+ i + "$Grp" + rowId + "$Bowler$txt_Figures*GEOM*TEXT SET " + boc.getWickets() + "-" + boc.getRuns() + "\0", print_writers);
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$Summary$" + containerName 
									+ i + "$Grp" + rowId + "$Bowler$txt_Overs*GEOM*TEXT SET " + CricketFunctions.OverBalls(boc.getOvers(),boc.getBalls()) + "\0", print_writers);
							
							if(i == 1 && rowId >= numberOfRows) {
								break;
							}
							else if(i == 2 && rowId >= numberOfRows) {
								break;
							}
						}
					}
				}
				for(int j = rowId+1; j <= numberOfRows; j++) {
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$Summary$" + containerName 
							+ i + "$Grp" + j + "$Bowler*ACTIVE SET 0\0", print_writers);
				}
			}
			break;
		case Constants.BAN_AFG_SERIES: case Constants.ACC:
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$Select*FUNCTION*Omo*vis_con SET 2\0", print_writers);
			containerName = "Inning";
			numberOfRows = 3;
			for(int i = 1; i <= WhichInning ; i++) {
				if(i == 1) {
					rowId=0;
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$Summary$" + containerName + "2*ACTIVE SET 0\0", print_writers);
				}else {
					rowId=0;
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$Summary$" + containerName + "2*ACTIVE SET 1\0", print_writers);
				}
				
				if(matchAllData.getMatch().getInning().get(i-1).getBattingTeamId() == matchAllData.getSetup().getTossWinningTeam()) {
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$Summary$" + containerName + i + "$Row0$img_Coin*ACTIVE SET 1\0", print_writers);
				}else {
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$Summary$" + containerName + i + "$Row0$img_Coin*ACTIVE SET 0\0", print_writers);				
				}
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$Summary$" + containerName + i + "$Row0$txt_TeamName*GEOM*TEXT SET " 
						+ matchAllData.getMatch().getInning().get(i-1).getBatting_team().getTeamName1() + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$Summary$" + containerName + i + "$Row0$txt_Runs*GEOM*TEXT SET " 
						+ CricketFunctions.getTeamScore(matchAllData.getMatch().getInning().get(i-1),"-", false) + "\0", print_writers);
				
				String OverData = "";
				if(i==1) {
					if(line1.equalsIgnoreCase("YES")) {
						OverData = "(" + line2 + ")";
					}else if(line1.equalsIgnoreCase("NO")) {
						if(matchAllData.getSetup().getReducedOvers() != null) {
							OverData = (Integer.valueOf(matchAllData.getSetup().getReducedOvers()) > 0 ? " (" + 
									matchAllData.getSetup().getReducedOvers() + ")":"");
						}
					}
				}else if(i==2) {
					if(line1.equalsIgnoreCase("YES")) {
						OverData = "(" + line3 + ")";
					}else if(line1.equalsIgnoreCase("NO")) {
						OverData = (matchAllData.getSetup().getTargetOvers() != null && !matchAllData.getSetup().getTargetOvers().trim().isEmpty() ? " (" + matchAllData.getSetup().getTargetOvers() + ")":"");
					}
				}
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$Summary$" + containerName + i + "$Row0$txt_Overs"
						+ "*GEOM*TEXT SET " + (matchAllData.getMatch().getInning().get(i-1).getTotalOvers() == 1 && matchAllData.getMatch().getInning().get(i-1).
						getTotalBalls() == 0 ? "OVER " : "OVERS ") + CricketFunctions.OverBalls(matchAllData.getMatch().getInning().get(i-1).getTotalOvers(),
								matchAllData.getMatch().getInning().get(i-1).getTotalBalls()) + OverData + "\0", print_writers);
				
				Player player = null;
				if(matchAllData.getMatch().getInning().get(i-1).getBattingTeamId() == matchAllData.getSetup().getHomeTeamId()) {
					player = matchAllData.getSetup().getHomeSquad().stream().filter(plyr -> plyr.getCaptainWicketKeeper() != null && 
							(plyr.getCaptainWicketKeeper().equalsIgnoreCase(CricketUtil.CAPTAIN) || 
							plyr.getCaptainWicketKeeper().equalsIgnoreCase("CAPTAIN_WICKET_KEEPER"))).findAny().orElse(null);
				}else {
					player = matchAllData.getSetup().getAwaySquad().stream().filter(plyr -> plyr.getCaptainWicketKeeper() != null && 
							(plyr.getCaptainWicketKeeper().equalsIgnoreCase(CricketUtil.CAPTAIN) || 
							plyr.getCaptainWicketKeeper().equalsIgnoreCase("CAPTAIN_WICKET_KEEPER"))).findAny().orElse(null);
				}
				
				switch (config.getBroadcaster()) {
				case Constants.BAN_AFG_SERIES:
					if(!matchAllData.getMatch().getSourceOfThisMatchData().equalsIgnoreCase("WEBSITE")) {
						if(config.getPrimaryIpAddress().equalsIgnoreCase(Constants.LOCALHOST)) {
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$Summary$" + containerName + i + "$CaptainGrp$img_Player*TEXTURE*IMAGE SET " 
									+ Constants.BAN_AFG_SERIES_LOCAL_PHOTO_PATH + matchAllData.getMatch().getInning().get(i-1).getBatting_team().getTeamName4()+ "\\\\" + Constants.CENTER + "\\\\"  
									+ player.getPhoto() + CricketUtil.PNG_EXTENSION +"\0", print_writers);
						}else {
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$Summary$" + containerName + i + "$CaptainGrp$img_Player*TEXTURE*IMAGE SET " 
									+ "\\\\" + config.getPrimaryIpAddress() + Constants.BAN_AFG_SERIES_PHOTO_PATH + matchAllData.getMatch().getInning().get(i-1).getBatting_team().getTeamName4()+ "\\\\" 
									+ Constants.CENTER + "\\\\"  + player.getPhoto() + CricketUtil.PNG_EXTENSION +"\0", print_writers);
						}
						
					}
					break;
				default:
					System.out.println("WhichStyle - " + WhichStyle);
					
					if(WhichStyle.equalsIgnoreCase("WITHOUT_IMAGE")) {
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$Summary$" + containerName + i + "$CaptainGrp$"
								+ "Select*FUNCTION*Omo*vis_con SET 1\0", print_writers);
					}else if(WhichStyle.equalsIgnoreCase("WITH_IMAGE")) {
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$Summary$" + containerName + i + "$CaptainGrp$"
								+ "Select*FUNCTION*Omo*vis_con SET 0\0", print_writers);
					}
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$Summary$" + containerName + i + "$CaptainGrp$"
							+ "img_Badges*TEXTURE*IMAGE SET " + Constants.ACC_FLAG + matchAllData.getMatch().getInning().get(i-1).getBatting_team().getTeamBadge()
							+"\0", print_writers);
					
					if(!matchAllData.getMatch().getSourceOfThisMatchData().equalsIgnoreCase("WEBSITE")) {
						if(config.getPrimaryIpAddress().equalsIgnoreCase(Constants.LOCALHOST)) {
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$Summary$" + containerName + i + "$CaptainGrp"
									+ "$img_Player*TEXTURE*IMAGE SET " + Constants.ACC_LOCAL_PHOTO_PATH + matchAllData.getMatch().getInning().get(i-1).getBatting_team().getTeamBadge()
									+ "\\\\" + Constants.CENTER + "\\\\" + player.getPhoto() + CricketUtil.PNG_EXTENSION +"\0", print_writers);
						}else {
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$Summary$" + containerName + i + "$CaptainGrp$img_Player*TEXTURE*IMAGE SET " 
									+ "\\\\" + config.getPrimaryIpAddress() + Constants.ACC_PHOTO_PATH + matchAllData.getMatch().getInning().get(i-1).getBatting_team().getTeamBadge()+ "\\\\" 
									+ Constants.CENTER + "\\\\"  + player.getPhoto() + CricketUtil.PNG_EXTENSION +"\0", print_writers);
						}
					}
					break;
				}
				

				if(matchAllData.getMatch().getInning().get(i-1).getBattingCard() != null) {
					Collections.sort(matchAllData.getMatch().getInning().get(i-1).getBattingCard(),new CricketFunctions.BatsmenScoreComparator());
					for(BattingCard bc : matchAllData.getMatch().getInning().get(i-1).getBattingCard()) {
						if(bc.getRuns() > 0) {
							rowId++;
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$Summary$" + containerName + i + "$Row" 
									+ rowId + "$Left*ACTIVE SET 1\0", print_writers);
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$Summary$" + containerName + i + "$Row" 
									+ rowId + "$Left$txt_Name*GEOM*TEXT SET " + bc.getPlayer().getTicker_name() + "\0", print_writers);
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$Summary$" + containerName + i + "$Row" 
									+ rowId + "$Left$txt_Runs*GEOM*TEXT SET " + bc.getRuns() + "\0", print_writers);
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$Summary$" + containerName + i + "$Row" 
									+ rowId + "$Left$txt_NotOutStar*GEOM*TEXT SET " + (bc.getStatus().toUpperCase().equalsIgnoreCase(CricketUtil.NOT_OUT) ? "*" : "") + "\0", print_writers);
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$Summary$" + containerName + i + "$Row" 
									+ rowId + "$Left$txt_Balls*GEOM*TEXT SET " + String.valueOf(bc.getBalls()) + "\0", print_writers);
							
							if(i == 1 && rowId >= numberOfRows) {
								break;
							}else if(i == 2 && rowId >= numberOfRows) {
								break;
							}
						}
					}
				}
				for(int j = rowId+1; j <= numberOfRows; j++) {
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$Summary$" + containerName + i + "$Row" + j + "$Left*ACTIVE SET 0\0", print_writers);
				}
				
				if(i == 1) {
					rowId = 0;
				}
				else {
					rowId = 0;
				}
				
				if(matchAllData.getMatch().getInning().get(i-1).getBowlingCard() != null) {
					Collections.sort(matchAllData.getMatch().getInning().get(i-1).getBowlingCard(),new CricketFunctions.BowlerFiguresComparator());
					for(BowlingCard boc : matchAllData.getMatch().getInning().get(i-1).getBowlingCard()) {
						
						if(boc.getWickets() > 0) {
							rowId++;
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$Summary$" + containerName + i + "$Row" 
									+ rowId + "$Right*ACTIVE SET 1\0", print_writers);
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$Summary$" + containerName + i + "$Row" 
									+ rowId + "$Right$txt_Name*GEOM*TEXT SET " + boc.getPlayer().getTicker_name() + "\0", print_writers);
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$Summary$" + containerName + i + "$Row" 
									+ rowId + "$Right$txt_Figures*GEOM*TEXT SET " + boc.getWickets() + "-" + boc.getRuns() + "\0", print_writers);
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$Summary$" + containerName + i + "$Row" 
									+ rowId + "$Right$txt_Overs*GEOM*TEXT SET " + CricketFunctions.OverBalls(boc.getOvers(),boc.getBalls()) + "\0", print_writers);
							
							if(i == 1 && rowId >= numberOfRows) {
								break;
							}
							else if(i == 2 && rowId >= numberOfRows) {
								break;
							}
						}
					}
				}
				for(int j = rowId+1; j <= numberOfRows; j++) {
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$Summary$" + containerName + i + "$Row" + j + "$Right*ACTIVE SET 0\0", print_writers);
				}
				
			}
			break;
		case Constants.TRI_SERIES: case Constants.MT20:
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide 
					+ "$select_GraphicsType*FUNCTION*Omo*vis_con SET 4\0", print_writers);
			
			containerName = "Team";
			numberOfRows = 4;
			
			for(int i = 1; i <= WhichInning ; i++) {
				if(i == 1) {
					rowId=0;
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$AllGraphics$Side" + WhichSide + "$Summary$" + containerName + 
							"2*ACTIVE SET 0\0", print_writers);
				}else {
					rowId=0;
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$AllGraphics$Side" + WhichSide + "$Summary$" + containerName + 
							"2*ACTIVE SET 1\0", print_writers);
				}
				
				if(matchAllData.getMatch().getInning().get(i-1).getBattingTeamId() == matchAllData.getSetup().getTossWinningTeam()) {
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$AllGraphics$Side" + WhichSide + "$Summary$" + containerName + i + 
							"$Title$Select_Toss*FUNCTION*Omo*vis_con SET 1\0", print_writers);
				}else {
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$AllGraphics$Side" + WhichSide + "$Summary$" + containerName + i + 
							"$Title$Select_Toss*FUNCTION*Omo*vis_con SET 0\0", print_writers);						
				}
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$AllGraphics$Side" + WhichSide + "$Summary$" + containerName + i + 
						"$Title$txt_Team*GEOM*TEXT SET " + matchAllData.getMatch().getInning().get(i-1).getBatting_team().getTeamName1() + "\0", print_writers);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$AllGraphics$Side" + WhichSide + "$Summary$" + containerName + i + 
						"$Title$FiguresAll$txt_Score*GEOM*TEXT SET " + CricketFunctions.getTeamScore(matchAllData.getMatch().getInning().get(i-1),"-", false) + "\0", print_writers);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$AllGraphics$Side" + WhichSide + "$Summary$" + containerName + i + 
						"$Title$Overs$txt_Overs*GEOM*TEXT SET OVERS\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$AllGraphics$Side" + WhichSide + "$Summary$" + containerName + i + 
						"$Title$Overs$txt_Overs_Value*GEOM*TEXT SET " + CricketFunctions.OverBalls(matchAllData.getMatch().getInning().get(i-1).getTotalOvers(),
								matchAllData.getMatch().getInning().get(i-1).getTotalBalls()) + "\0", print_writers);
				if(i==1) {
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$AllGraphics$Side" + WhichSide + "$Summary$" + containerName + i + 
							"$Title$Overs$txt_DLS_Value*GEOM*TEXT SET " + (matchAllData.getSetup().getReducedOvers() != null ?(Integer.valueOf(matchAllData.getSetup().getReducedOvers()) > 0 ? "(" + matchAllData.getSetup().getReducedOvers() 
									+ ")":"") : "") + "\0", print_writers);
				}else if(i==2) {
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$AllGraphics$Side" + WhichSide + "$Summary$" + containerName + i + 
							"$Title$Overs$txt_DLS_Value*GEOM*TEXT SET " + (matchAllData.getSetup().getTargetOvers() != null && !matchAllData.getSetup().getTargetOvers().trim()
							.isEmpty() ? "(" + matchAllData.getSetup().getTargetOvers() + ")":"") + "\0", print_writers);
				}
				
				if(matchAllData.getMatch().getInning().get(i-1).getBattingCard() != null) {
					Collections.sort(matchAllData.getMatch().getInning().get(i-1).getBattingCard(),new CricketFunctions.BatsmenScoreComparator());
					for(BattingCard bc : matchAllData.getMatch().getInning().get(i-1).getBattingCard()) {
						if(bc.getRuns() > 0) {
							rowId++;
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$AllGraphics$Side" + WhichSide + "$Summary$" + containerName + i + 
									"$Row_" + rowId + "$Batsman*ACTIVE SET 1\0", print_writers);
							
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$AllGraphics$Side" + WhichSide + "$Summary$" + containerName + i + 
									"$Row_" + rowId + "$Batsman$txt_Name*GEOM*TEXT SET " + bc.getPlayer().getTicker_name() + "\0", print_writers);
							
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$AllGraphics$Side" + WhichSide + "$Summary$" + containerName + i + 
									"$Row_" + rowId + "$Batsman$ScoresAll$txt_Runs*GEOM*TEXT SET " + bc.getRuns() + "\0", print_writers);
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$AllGraphics$Side" + WhichSide + "$Summary$" + containerName + i + 
									"$Row_" + rowId + "$Batsman$ScoresAll$txt_Not-Out*GEOM*TEXT SET " + (bc.getStatus().toUpperCase().equalsIgnoreCase(CricketUtil.NOT_OUT)
											? "*" : "") + "\0", print_writers);
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$AllGraphics$Side" + WhichSide + "$Summary$" + containerName + i + 
									"$Row_" + rowId + "$Batsman$ScoresAll$txt_Balls*GEOM*TEXT SET " + String.valueOf(bc.getBalls()) + "\0", print_writers);
							
							if(i == 1 && rowId >= numberOfRows) {
								break;
							}else if(i == 2 && rowId >= numberOfRows) {
								break;
							}
						}
					}
				}
				for(int j = rowId+1; j <= numberOfRows; j++) {
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$AllGraphics$Side" + WhichSide + "$Summary$" + containerName + i + 
							"$Row_" + j + "$Batsman*ACTIVE SET 0\0", print_writers);
				}
				
				if(i == 1) {
					rowId = 0;
				}
				else {
					rowId = 0;
				}
				
				if(matchAllData.getMatch().getInning().get(i-1).getBowlingCard() != null) {
					Collections.sort(matchAllData.getMatch().getInning().get(i-1).getBowlingCard(),new CricketFunctions.BowlerFiguresComparator());
					for(BowlingCard boc : matchAllData.getMatch().getInning().get(i-1).getBowlingCard()) {
						
						if(boc.getWickets() > 0) {
							rowId++;
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$AllGraphics$Side" + WhichSide + "$Summary$" + containerName + i + 
									"$Row_" + rowId + "$Bowler*ACTIVE SET 1\0", print_writers);
							
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$AllGraphics$Side" + WhichSide + "$Summary$" + containerName + i + 
									"$Row_" + rowId + "$Bowler$txt_Name*GEOM*TEXT SET " + boc.getPlayer().getTicker_name() + "\0", print_writers);
							
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$AllGraphics$Side" + WhichSide + "$Summary$" + containerName + i + 
									"$Row_" + rowId + "$Bowler$FiguresAll$txt_Figures*GEOM*TEXT SET " + boc.getWickets() + "-" + boc.getRuns() + "\0", print_writers);
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$AllGraphics$Side" + WhichSide + "$Summary$" + containerName + i + 
									"$Row_" + rowId + "$Bowler$FiguresAll$txt_Overs*GEOM*TEXT SET " + CricketFunctions.OverBalls(boc.getOvers(),boc.getBalls()) + "\0", print_writers);
							
							if(i == 1 && rowId >= numberOfRows) {
								break;
							}
							else if(i == 2 && rowId >= numberOfRows) {
								break;
							}
						}
					}
				}
				for(int j = rowId+1; j <= numberOfRows; j++) {
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$AllGraphics$Side" + WhichSide + "$Summary$" + containerName + i + 
							"$Row_" + j + "$Bowler*ACTIVE SET 0\0", print_writers);
				}
			}
			break;
		}
		return Constants.OK;
	}
	public String CurrentPartnerShipBody(List<PrintWriter> print_writers, int WhichSide, Configuration config, MatchAllData matchAllData,Inning inning) {
		
		int total=0;
		double ScaleFac1 = 0, ScaleFac2 = 0;
		
		switch (config.getBroadcaster()) {
		case Constants.AFG_SL_SERIES:
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$select_GraphicsType"
					+ "*FUNCTION*Omo*vis_con SET 7\0", print_writers);
			
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$Partnership$Title$txt_Header1"
					+ "*GEOM*TEXT SET " + CricketFunctions.ordinal(partnership.getPartnershipNumber()) + " WICKET" + "\0", print_writers);
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$Partnership$Title$txt_Header2"
					+ "*GEOM*TEXT SET " + "PARTNERSHIP" + "\0", print_writers);
			
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$Partnership$Score$txt_Runs"
					+ "*GEOM*TEXT SET " + partnership.getTotalRuns() + "*" + "\0", print_writers);
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$Partnership$Score$txt_Balls"
					+ "*GEOM*TEXT SET " + "OFF " + partnership.getTotalBalls() + "\0", print_writers);
			
			total = (inning.getPartnerships().get(inning.getPartnerships().size()-1).getFirstBatterRuns() + 
					inning.getPartnerships().get(inning.getPartnerships().size()-1).getSecondBatterRuns());
			
			ScaleFac1 = ((double) inning.getPartnerships().get(inning.getPartnerships().size()-1).getFirstBatterRuns()) / total;
			ScaleFac2 = ((double) inning.getPartnerships().get(inning.getPartnerships().size()-1).getSecondBatterRuns()) / total;
			
			ScaleFac1 = ScaleFac1*360;
			ScaleFac2 = ScaleFac2*360;
			
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$Partnership$Score$PieAll$Player1$"
					+ "obj_RingAngle*GEOM*Angle SET " + ScaleFac1 + "\0", print_writers);
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$Partnership$Score$PieAll$Player1$"
					+ "obj_RingAngle*GEOM*Rotation SET " + (200-ScaleFac1) + "\0", print_writers);
			
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$Partnership$Score$PieAll$Player2$"
					+ "obj_RingAngle*GEOM*Angle SET " + ScaleFac2 + "\0", print_writers);
			
			for (BattingCard bc : inning.getBattingCard()) {
				if (bc.getPlayerId() == partnership.getFirstBatterNo()) {
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$Partnership$Stats$Grp2$RunsGrp$txt_Scores"
							+ "*GEOM*TEXT SET " + bc.getRuns() + "\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$Partnership$Stats$Grp2$RunsGrp$txt_Balls"
							+ "*GEOM*TEXT SET " + bc.getBalls() + "\0", print_writers);
				}else if(bc.getPlayerId() == partnership.getSecondBatterNo()) {
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$Partnership$Stats$Grp3$RunsGrp$txt_Scores"
							+ "*GEOM*TEXT SET " + bc.getRuns() + "\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$Partnership$Stats$Grp3$RunsGrp$txt_Balls"
							+ "*GEOM*TEXT SET " + bc.getBalls() + "\0", print_writers);
				}
			}
			
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$Partnership$Stats$Grp2$txt_Name"
					+ "*GEOM*TEXT SET " + partnership.getFirstPlayer().getTicker_name() + "\0", print_writers);
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$Partnership$Stats$Grp2$ContributionGrp$txt_Scores"
					+ "*GEOM*TEXT SET " + partnership.getFirstBatterRuns() + "\0", print_writers);
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$Partnership$Stats$Grp2$ContributionGrp$txt_Balls"
					+ "*GEOM*TEXT SET " + partnership.getFirstBatterBalls() + "\0", print_writers);
			
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$Partnership$Stats$Grp3$txt_Name"
					+ "*GEOM*TEXT SET " + partnership.getSecondPlayer().getTicker_name() + "\0", print_writers);
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$Partnership$Stats$Grp3$ContributionGrp$txt_Scores"
					+ "*GEOM*TEXT SET " + partnership.getSecondBatterRuns() + "\0", print_writers);
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$Partnership$Stats$Grp3$ContributionGrp$txt_Balls"
					+ "*GEOM*TEXT SET " + partnership.getSecondBatterBalls() + "\0", print_writers);
			
			break;
		case Constants.BAN_AFG_SERIES: case Constants.ACC:
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$Select*FUNCTION*Omo*vis_con SET 9\0", print_writers);
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$CurrentPArt$CurrentPartnership*ACTIVE SET 1\0", print_writers);
			
//			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrame$AllGraphics$Side" + WhichSide + "$Partnership$Stats$1$PartnershipNumber$"
//					+ "txt_Partnership*GEOM*TEXT SET " + CricketFunctions.ordinal(partnership.getPartnershipNumber()) + " WICKET PARTNERSHIP" + "\0", print_writers);
			
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$CurrentPArt$CurrentPartnershipDataAll$PartScoreOut$"
					+ "txt_PartnershipRuns*GEOM*TEXT SET " + partnership.getTotalRuns() + "*" + "\0", print_writers);
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$CurrentPArt$CurrentPartnershipDataAll$PartScoreOut$"
					+ "txt_PartnershipBalls*GEOM*TEXT SET OFF " + partnership.getTotalBalls() + "\0", print_writers);
			
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$CurrentPArt$CurrentPartnershipDataAll$ImageGrpOut"
					+ "$ImageGrp1$NameGrp$txt_Name*GEOM*TEXT SET " + partnership.getFirstPlayer().getTicker_name() + "\0", print_writers);
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$CurrentPArt$CurrentPartnershipDataAll$ImageGrpOut"
					+ "$ImageGrp2$NameGrp$txt_Name*GEOM*TEXT SET " + partnership.getSecondPlayer().getTicker_name() + "\0", print_writers);
			
			for (BattingCard bc : inning.getBattingCard()) {
				if (bc.getPlayerId() == partnership.getFirstBatterNo()) {
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$CurrentPArt$CurrentPartnershipDataAll$ImageGrpOut"
							+ "$ImageGrp1$NameGrp$txt_Runs*GEOM*TEXT SET " + bc.getRuns() + "\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$CurrentPArt$CurrentPartnershipDataAll$ImageGrpOut"
							+ "$ImageGrp1$NameGrp$txt_Balls*GEOM*TEXT SET " + bc.getBalls() + "\0", print_writers);
				}else if(bc.getPlayerId() == partnership.getSecondBatterNo()) {
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$CurrentPArt$CurrentPartnershipDataAll$ImageGrpOut"
							+ "$ImageGrp2$NameGrp$txt_Runs*GEOM*TEXT SET " + bc.getRuns() + "\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$CurrentPArt$CurrentPartnershipDataAll$ImageGrpOut"
							+ "$ImageGrp2$NameGrp$txt_Balls*GEOM*TEXT SET " + bc.getBalls() + "\0", print_writers);
				}
			}
			
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$CurrentPArt$CurrentPartnershipDataAll$ContributionGrp$"
					+ "ContributionValueGrp1$txt_ContributionRuns*GEOM*TEXT SET " + partnership.getFirstBatterRuns() + "\0", print_writers);
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$CurrentPArt$CurrentPartnershipDataAll$ContributionGrp$"
					+ "ContributionValueGrp1$txt_ContributionBalls*GEOM*TEXT SET " + partnership.getFirstBatterBalls() + "\0", print_writers);
			
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$CurrentPArt$CurrentPartnershipDataAll$ContributionGrp$"
					+ "ContributionValueGrp2$txt_ContributionRuns*GEOM*TEXT SET " + partnership.getSecondBatterRuns() + "\0", print_writers);
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$CurrentPArt$CurrentPartnershipDataAll$ContributionGrp$"
					+ "ContributionValueGrp2$txt_ContributionBalls*GEOM*TEXT SET " + partnership.getSecondBatterBalls() + "\0", print_writers);
			
			switch (config.getBroadcaster()) {
			case Constants.BAN_AFG_SERIES:
				if(config.getPrimaryIpAddress().equalsIgnoreCase(Constants.LOCALHOST)) {
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$CurrentPArt$CurrentPartnershipDataAll$ImageGrpOut"
							+ "$ImageGrp1$img_Player*TEXTURE*IMAGE SET " + Constants.BAN_AFG_SERIES_LOCAL_PHOTO_PATH + "\\\\" + inning.getBatting_team().getTeamName4() + "\\\\" 
							+ Constants.CENTER + "\\\\" + partnership.getFirstPlayer().getPhoto() + CricketUtil.PNG_EXTENSION + "\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$CurrentPArt$CurrentPartnershipDataAll$ImageGrpOut"
							+ "$ImageGrp1$img_PlayerGlow*TEXTURE*IMAGE SET " + Constants.BAN_AFG_SERIES_LOCAL_PHOTO_PATH + "\\\\" + inning.getBatting_team().getTeamName4() + "\\\\" 
							+ Constants.CENTER + "\\\\" + partnership.getFirstPlayer().getPhoto() + CricketUtil.PNG_EXTENSION + "\0", print_writers);
				}else {
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$CurrentPArt$CurrentPartnershipDataAll$ImageGrpOut"
							+ "$ImageGrp1$img_Player*TEXTURE*IMAGE SET " + "\\\\" + config.getPrimaryIpAddress() + "\\\\" + Constants.BAN_AFG_SERIES_PHOTO_PATH + "\\\\" + inning.getBatting_team().getTeamName4() 
							+ "\\\\" + Constants.CENTER + "\\\\" + partnership.getFirstPlayer().getPhoto() + CricketUtil.PNG_EXTENSION + "\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$CurrentPArt$CurrentPartnershipDataAll$ImageGrpOut"
							+ "$ImageGrp1$img_PlayerGlow*TEXTURE*IMAGE SET " + "\\\\" + config.getPrimaryIpAddress() + "\\\\" + Constants.BAN_AFG_SERIES_PHOTO_PATH + "\\\\" + inning.getBatting_team().getTeamName4() 
							+ "\\\\" + Constants.CENTER + "\\\\" + partnership.getFirstPlayer().getPhoto() + CricketUtil.PNG_EXTENSION + "\0", print_writers);
				}
				
				if(config.getPrimaryIpAddress().equalsIgnoreCase(Constants.LOCALHOST)) {
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$CurrentPArt$CurrentPartnershipDataAll$ImageGrpOut"
							+ "$ImageGrp2$img_Player*TEXTURE*IMAGE SET " + Constants.BAN_AFG_SERIES_LOCAL_PHOTO_PATH + "\\\\" + inning.getBatting_team().getTeamName4() + "\\\\" 
							+ Constants.CENTER + "\\\\" + partnership.getSecondPlayer().getPhoto() + CricketUtil.PNG_EXTENSION + "\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$CurrentPArt$CurrentPartnershipDataAll$ImageGrpOut"
							+ "$ImageGrp2$img_PlayerGlow*TEXTURE*IMAGE SET " + Constants.BAN_AFG_SERIES_LOCAL_PHOTO_PATH + "\\\\" + inning.getBatting_team().getTeamName4() + "\\\\" 
							+ Constants.CENTER + "\\\\" + partnership.getSecondPlayer().getPhoto() + CricketUtil.PNG_EXTENSION + "\0", print_writers);
				}else {
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$CurrentPArt$CurrentPartnershipDataAll$ImageGrpOut"
							+ "$ImageGrp2$img_Player*TEXTURE*IMAGE SET " + "\\\\" + config.getPrimaryIpAddress() + "\\\\" + Constants.BAN_AFG_SERIES_PHOTO_PATH + "\\\\" + inning.getBatting_team().getTeamName4() 
							+ "\\\\" + Constants.CENTER + "\\\\" + partnership.getSecondPlayer().getPhoto() + CricketUtil.PNG_EXTENSION + "\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$CurrentPArt$CurrentPartnershipDataAll$ImageGrpOut"
							+ "$ImageGrp2$img_PlayerGlow*TEXTURE*IMAGE SET " + "\\\\" + config.getPrimaryIpAddress() + "\\\\" + Constants.BAN_AFG_SERIES_PHOTO_PATH + "\\\\" + inning.getBatting_team().getTeamName4() 
							+ "\\\\" + Constants.CENTER + "\\\\" + partnership.getSecondPlayer().getPhoto() + CricketUtil.PNG_EXTENSION + "\0", print_writers);
				}
				break;
			default:
				if(config.getPrimaryIpAddress().equalsIgnoreCase(Constants.LOCALHOST)) {
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$CurrentPArt$CurrentPartnershipDataAll$ImageGrpOut"
							+ "$ImageGrp1$img_Player*TEXTURE*IMAGE SET " + Constants.ACC_LOCAL_PHOTO_PATH + "\\\\" + inning.getBatting_team().getTeamBadge() + "\\\\" 
							+ Constants.LEFT + "\\\\" + partnership.getFirstPlayer().getPhoto() + CricketUtil.PNG_EXTENSION + "\0", print_writers);
				}else {
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$CurrentPArt$CurrentPartnershipDataAll$ImageGrpOut"
							+ "$ImageGrp1$img_Player*TEXTURE*IMAGE SET " + "\\\\" + config.getPrimaryIpAddress() + "\\\\" + Constants.ACC_PHOTO_PATH + "\\\\" + inning.getBatting_team().getTeamBadge() 
							+ "\\\\" + Constants.LEFT + "\\\\" + partnership.getFirstPlayer().getPhoto() + CricketUtil.PNG_EXTENSION + "\0", print_writers);
				}
				
				if(config.getPrimaryIpAddress().equalsIgnoreCase(Constants.LOCALHOST)) {
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$CurrentPArt$CurrentPartnershipDataAll$ImageGrpOut"
							+ "$ImageGrp2$img_Player*TEXTURE*IMAGE SET " + Constants.ACC_LOCAL_PHOTO_PATH + "\\\\" + inning.getBatting_team().getTeamBadge() + "\\\\" 
							+ Constants.RIGHT + "\\\\" + partnership.getSecondPlayer().getPhoto() + CricketUtil.PNG_EXTENSION + "\0", print_writers);
				}else {
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$CurrentPArt$CurrentPartnershipDataAll$ImageGrpOut"
							+ "$ImageGrp2$img_Player*TEXTURE*IMAGE SET " + "\\\\" + config.getPrimaryIpAddress() + "\\\\" + Constants.ACC_PHOTO_PATH + "\\\\" + inning.getBatting_team().getTeamBadge() 
							+ "\\\\" + Constants.RIGHT + "\\\\" + partnership.getSecondPlayer().getPhoto() + CricketUtil.PNG_EXTENSION + "\0", print_writers);
				}
				break;
			}
			
			break;
		case Constants.TRI_SERIES: case Constants.MT20:
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide 
					+ "$select_GraphicsType*FUNCTION*Omo*vis_con SET 6\0", print_writers);
			
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$AllGraphics$Side" + WhichSide + "$Partnership$Stats$1$PartnershipNumber$"
					+ "txt_Partnership*GEOM*TEXT SET " + CricketFunctions.ordinal(partnership.getPartnershipNumber()) + " WICKET PARTNERSHIP" + "\0", print_writers);
			
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$AllGraphics$Side" + WhichSide + "$Partnership$Stats$2$RunsGrp$"
					+ "fig_Runs*GEOM*TEXT SET " + partnership.getTotalRuns() + "*" + "\0", print_writers);
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$AllGraphics$Side" + WhichSide + "$Partnership$Stats$2$RunsGrp$"
					+ "fig_Balls*GEOM*TEXT SET " + partnership.getTotalBalls() + "\0", print_writers);
			
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$AllGraphics$Side" + WhichSide + "$Partnership$Stats$3$StikeRate$"
					+ "txt_StrikeRate*GEOM*TEXT SET STRIKE RATE - " +  CricketFunctions.generateStrikeRate(partnership.getTotalRuns(), partnership.getTotalBalls(), 
							0) + "\0", print_writers);
			
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$AllGraphics$Side" + WhichSide + "$Partnership$Stats$4$$select_Contribution"
					+ "*FUNCTION*Omo*vis_con SET 1\0", print_writers);
			
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$AllGraphics$Side" + WhichSide + "$Partnership$Stats$4$Contribution$1$"
					+ "txt_Name_1*GEOM*TEXT SET " + partnership.getFirstPlayer().getTicker_name() + "\0", print_writers);
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$AllGraphics$Side" + WhichSide + "$Partnership$Stats$4$Contribution$2$"
					+ "txt_Name_1*GEOM*TEXT SET " + partnership.getSecondPlayer().getTicker_name() + "\0", print_writers);
			
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$AllGraphics$Side" + WhichSide + "$Partnership$Stats$4$Contribution$1$"
					+ "txt_Runs*GEOM*TEXT SET " + partnership.getFirstBatterRuns() + "\0", print_writers);
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$AllGraphics$Side" + WhichSide + "$Partnership$Stats$4$Contribution$1$"
					+ "txt_Balls*GEOM*TEXT SET " + partnership.getFirstBatterBalls() + "\0", print_writers);
			
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$AllGraphics$Side" + WhichSide + "$Partnership$Stats$4$Contribution$2$"
					+ "txt_Runs*GEOM*TEXT SET " + partnership.getSecondBatterRuns() + "\0", print_writers);
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$AllGraphics$Side" + WhichSide + "$Partnership$Stats$4$Contribution$2$"
					+ "txt_Balls*GEOM*TEXT SET " + partnership.getSecondBatterBalls() + "\0", print_writers);
			
			if(config.getPrimaryIpAddress().equalsIgnoreCase(Constants.LOCALHOST)) {
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$AllGraphics$Side" + WhichSide + "$Partnership$ImageGrp$Image1"
						+ "$img_Player*TEXTURE*IMAGE SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_LOCAL_PHOTO_PATH : Constants.MT20_LOCAL_PHOTO_PATH) + "\\\\" + inning.getBatting_team().getTeamName4() + "\\\\" + Constants.LEFT + "\\\\" + 
						partnership.getFirstPlayer().getPhoto() + CricketUtil.PNG_EXTENSION + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$AllGraphics$Side" + WhichSide + "$Partnership$ImageGrp$Image1"
						+ "$img_PlayerShadow*TEXTURE*IMAGE SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_LOCAL_PHOTO_PATH : Constants.MT20_LOCAL_PHOTO_PATH) + "\\\\" + inning.getBatting_team().getTeamName4() + "\\\\" + Constants.LEFT + "\\\\" + 
						partnership.getFirstPlayer().getPhoto() + CricketUtil.PNG_EXTENSION + "\0", print_writers);
			}else {
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$AllGraphics$Side" + WhichSide + "$Partnership$ImageGrp$Image1"
						+ "$img_Player*TEXTURE*IMAGE SET " + "\\\\" + config.getPrimaryIpAddress() + "\\\\" + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_PHOTO_PATH : Constants.MT20_PHOTO_PATH) + "\\\\" + 
						inning.getBatting_team().getTeamName4() + "\\\\" + Constants.LEFT + "\\\\" + partnership.getFirstPlayer().getPhoto() + CricketUtil.PNG_EXTENSION + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$AllGraphics$Side" + WhichSide + "$Partnership$ImageGrp$Image1"
						+ "$img_PlayerShadow*TEXTURE*IMAGE SET " + "\\\\" + config.getPrimaryIpAddress() + "\\\\" + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_PHOTO_PATH : Constants.MT20_PHOTO_PATH) + "\\\\" + 
						inning.getBatting_team().getTeamName4() + "\\\\" + Constants.LEFT + "\\\\" + partnership.getFirstPlayer().getPhoto() + CricketUtil.PNG_EXTENSION + "\0", print_writers);
			}
			
			if(config.getPrimaryIpAddress().equalsIgnoreCase(Constants.LOCALHOST)) {
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$AllGraphics$Side" + WhichSide + "$Partnership$ImageGrp$Image2"
						+ "$img_Player*TEXTURE*IMAGE SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_LOCAL_PHOTO_PATH : Constants.MT20_LOCAL_PHOTO_PATH) + "\\\\" + inning.getBatting_team().getTeamName4() + "\\\\" + Constants.RIGHT + "\\\\" + 
						partnership.getSecondPlayer().getPhoto() + CricketUtil.PNG_EXTENSION + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$AllGraphics$Side" + WhichSide + "$Partnership$ImageGrp$Image2"
						+ "$img_PlayerShadow*TEXTURE*IMAGE SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_LOCAL_PHOTO_PATH : Constants.MT20_LOCAL_PHOTO_PATH) + "\\\\" + inning.getBatting_team().getTeamName4() + "\\\\" + Constants.RIGHT + "\\\\" + 
						partnership.getSecondPlayer().getPhoto() + CricketUtil.PNG_EXTENSION + "\0", print_writers);
			}else {
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$AllGraphics$Side" + WhichSide + "$Partnership$ImageGrp$Image2"
						+ "$img_Player*TEXTURE*IMAGE SET " + "\\\\" + config.getPrimaryIpAddress() + "\\\\" + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_PHOTO_PATH : Constants.MT20_PHOTO_PATH) + "\\\\" + 
						inning.getBatting_team().getTeamName4() + "\\\\" + Constants.RIGHT + "\\\\" + partnership.getSecondPlayer().getPhoto() + CricketUtil.PNG_EXTENSION + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$AllGraphics$Side" + WhichSide + "$Partnership$ImageGrp$Image2"
						+ "$img_PlayerShadow*TEXTURE*IMAGE SET " + "\\\\" + config.getPrimaryIpAddress() + "\\\\" + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_PHOTO_PATH : Constants.MT20_PHOTO_PATH) + "\\\\" + 
						inning.getBatting_team().getTeamName4() + "\\\\" + Constants.RIGHT + "\\\\" + partnership.getSecondPlayer().getPhoto() + CricketUtil.PNG_EXTENSION + "\0", print_writers);
			}
			break;
		}
		return Constants.OK;
	}
	public String ManhattanBody(List<PrintWriter> print_writers, int WhichSide, Configuration config, MatchAllData matchAllData,Inning inning) {
		
		int maxRuns = 0,runsIncr = 0,powerplay_omo=0,powerPlayValue = 0,whichpowerplay = 0;
		double lngth = 0;
		String powerPlay = "",value="",whichManhattan = "";
		
		switch(config.getBroadcaster()) {
		case Constants.AFG_SL_SERIES:
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$select_GraphicsType"
					+ "*FUNCTION*Omo*vis_con SET 9\0", print_writers);
			
			switch (matchAllData.getSetup().getMatchType()) {
			case CricketUtil.ODI: case CricketUtil.OD:
				whichManhattan = "50_Overs";
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$Manhattan$Bar$select_BarNumbers"
						+ "*FUNCTION*Omo*vis_con SET 2\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$Manhattan$Overs_Axis$Data$"
						+ "select_OverNumbers*FUNCTION*Omo*vis_con SET 2\0", print_writers);
				break;
			case CricketUtil.DT20: case CricketUtil.IT20:
				whichManhattan = "20_Overs";
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$Manhattan$Bar$select_BarNumbers"
						+ "*FUNCTION*Omo*vis_con SET 1\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$Manhattan$Overs_Axis$Data$"
						+ "select_OverNumbers*FUNCTION*Omo*vis_con SET 1\0", print_writers);
				break;
			default:
				whichManhattan = "10_Overs";
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$Manhattan$Bar$select_BarNumbers"
						+ "*FUNCTION*Omo*vis_con SET 0\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$Manhattan$Overs_Axis$Data$"
						+ "select_OverNumbers*FUNCTION*Omo*vis_con SET 0\0", print_writers);
				break;
			}
			
			for(int l = 1; l <= 10; l++) {
				switch (matchAllData.getSetup().getMatchType()) {
				case CricketUtil.ODI: case CricketUtil.OD:
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$Manhattan$Overs_Axis$Overs_Data$"
							+ whichManhattan + "$txt_" + (l+1) + "*GEOM*TEXT SET " + (5*l) + "\0", print_writers);
					break;
				case CricketUtil.DT20: case CricketUtil.IT20:
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$Manhattan$Overs_Axis$Overs_Data$"
							+ whichManhattan + "$txt_" + (l+1) + "*GEOM*TEXT SET " + (2*l) + "\0", print_writers);
					break;
				default:
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$Manhattan$Overs_Axis$Overs_Data$"
							+ whichManhattan + "$txt_" + l + "*GEOM*TEXT SET " + l + "\0", print_writers);
					break;
				}
			}
			
			for (int j = 1; j < manhattan.size(); j++) {
				if(manhattan.get(j).getInningNumber() == inning.getInningNumber()) {
					if(Integer.valueOf(manhattan.get(j).getOverTotalRuns()) > maxRuns){
						maxRuns = Integer.valueOf(manhattan.get(j).getOverTotalRuns()); // 33 runs came off 34th over
					}
					while (maxRuns % 5 != 0) {     // 5 label in y-axis
				 		maxRuns = maxRuns + 1;    // keep incrementing till max runs is divisible by 5. maxRuns = 35
					}
				}
			}
			runsIncr = maxRuns / 5; // e.g., 35 / 5 = 7
			for (int i = 1; i <= 5; i++) {
			    CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$Manhattan$Runs_Axis$"
			    		+ "Runs_Data$txt_" + i + "*GEOM*TEXT SET " + (runsIncr * i) + "\0", print_writers);
			}
			if(!CricketFunctions.getBallCountStartAndEndRange(matchAllData, inning).isEmpty()) {
				powerPlayValue = CricketFunctions.getBallCountStartAndEndRange(matchAllData, inning).get(1);
			}
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$Manhattan$Bar$" + whichManhattan 
					+ "$Position*FUNCTION*Grid*num_row SET 1\0", print_writers);
			
			for(int j = 1; j <= matchAllData.getSetup().getMaxOvers(); j++) {
				
				switch (matchAllData.getSetup().getMatchType()) {
				case CricketUtil.ODI: case CricketUtil.OD:
					if((j*6) <= CricketFunctions.getBallCountStartAndEndRange(matchAllData, inning).get(1)) {
						powerplay_omo = 0;
						powerPlay = "$PowerPlay1";
					}
					else if((j*6) >= CricketFunctions.getBallCountStartAndEndRange(matchAllData, inning).get(2) && 
							(j*6) <= CricketFunctions.getBallCountStartAndEndRange(matchAllData, inning).get(3)) {
						powerplay_omo = 1;
						powerPlay = "$PowerPlay2";
					}
					else if((j*6) >= CricketFunctions.getBallCountStartAndEndRange(matchAllData, inning).get(4) && 
							(j*6) <= CricketFunctions.getBallCountStartAndEndRange(matchAllData, inning).get(5)) {
						powerplay_omo = 2;
						powerPlay = "$PowerPlay3";
					}
					break;
				default:
					if((j*6) <= powerPlayValue) {
						powerplay_omo = 0;
						powerPlay = "$PowerPlay1";
					}else {
						powerplay_omo = 3;
						powerPlay = "$Normal";
					}
					break;
				}
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$Manhattan$Bar$" + whichManhattan 
						+ "$Position$" + j + "$obj_ScaleY$Select_PowerPlay*FUNCTION*Omo*vis_con SET " + powerplay_omo + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$Manhattan$Bar$" + whichManhattan 
						+ "$Position$" + j + "$Wkt$Select_PowerPlay*FUNCTION*Omo*vis_con SET " + powerplay_omo + "\0", print_writers);
				
				if(j < manhattan.size()) {
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$Manhattan$Bar$" + whichManhattan 
							+ "$Position*FUNCTION*Grid*num_col SET " + j + "\0", print_writers);
					
					lngth = ((1.0 * Integer.valueOf(manhattan.get(j).getOverTotalRuns())) / maxRuns);
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$Manhattan$Bar$" + whichManhattan 
							+ "$Position$" + j + "$obj_ScaleY*TRANSFORMATION*SCALING*Y SET " + lngth + "\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$Manhattan$Bar$" + whichManhattan 
							+ "$Position$" + j + "$Wkt" + powerPlay + "$Select_Wickets*FUNCTION*Omo*vis_con SET " + manhattan.get(j).getOverTotalWickets() + "\0", print_writers);
				}
			}
			
			break;
		case Constants.BAN_AFG_SERIES: case Constants.ACC:
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$Select*FUNCTION*Omo*vis_con SET 6\0", print_writers);
			
			switch (matchAllData.getSetup().getMatchType()) {
			case CricketUtil.ODI: case CricketUtil.OD:
				whichManhattan = "ManhattanDataAll2";
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$Select"
						+ "$Manhattan$Select*FUNCTION*Omo*vis_con SET 1\0", print_writers);
				
//				for(int i=1;i<=50;i++) {
//					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$Manhattan$ManhattanDataAll2$Bars$" + i + 
//							"$PowerPlay$Img_Manhattan1*TEXTURE*IMAGE SET " + Constants.BAN_AFG_SERIES_BASE1 + inning.getBatting_team().getTeamBadge() + "\0", print_writers);
//					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$Manhattan$ManhattanDataAll2$Bars$" + i + 
//							"$NonPowerPlay$Img_Manhattan2*TEXTURE*IMAGE SET " + Constants.BAN_AFG_SERIES_BASE2 + inning.getBatting_team().getTeamBadge() + "\0", print_writers);
//				}
				
				break;
			default:
				whichManhattan = "ManhattanDataAll";
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$Select"
						+ "$Manhattan$Select*FUNCTION*Omo*vis_con SET 0\0", print_writers);
				
//				for(int i=1;i<=20;i++) {
//					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$Manhattan$ManhattanDataAll$Bars$" + i + 
//							"$PowerPlay$Img_Manhattan1*TEXTURE*IMAGE SET " + Constants.BAN_AFG_SERIES_BASE1 + inning.getBatting_team().getTeamBadge() + "\0", print_writers);
//					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$Manhattan$ManhattanDataAll$Bars$" + i + 
//							"$NonPowerPlay$Img_Manhattan2*TEXTURE*IMAGE SET " + Constants.BAN_AFG_SERIES_BASE2 + inning.getBatting_team().getTeamBadge() + "\0", print_writers);
//				}
				break;
			}
			
			for (int j = 1; j < manhattan.size(); j++) {
				if(manhattan.get(j).getInningNumber() == inning.getInningNumber()) {
					if(Integer.valueOf(manhattan.get(j).getOverTotalRuns()) > maxRuns){
						maxRuns = Integer.valueOf(manhattan.get(j).getOverTotalRuns()); // 33 runs came off 34th over
					}
					while (maxRuns % 4 != 0) {     // 5 label in y-axis
				 		maxRuns = maxRuns + 1;    // keep incrementing till max runs is divisible by 5. maxRuns = 35
					}
				}
			}
			
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$" + whichManhattan + "$Graph$Runs_Axis$RunsTexts$"
					+ "txt_5*GEOM*TEXT SET 0\0", print_writers);
			
			runsIncr = maxRuns / 4; // e.g., 35 / 5 = 7
			for (int i = 1; i <= 4; i++) {
			    int txtIndex = 5 - i; // remap: i=1 -> 4, i=2 -> 3, ... i=5 -> 0
			    CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$" + whichManhattan + "$Graph$Runs_Axis$"
			    		+ "RunsTexts$txt_" + txtIndex + "*GEOM*TEXT SET " + (runsIncr * i) + "\0", print_writers);
			}
			
			if(!CricketFunctions.getBallCountStartAndEndRange(matchAllData, inning).isEmpty()) {
				powerPlayValue = CricketFunctions.getBallCountStartAndEndRange(matchAllData, inning).get(1);
			}
			for(int j = 1; j <= matchAllData.getSetup().getMaxOvers(); j++) {
				
				switch (matchAllData.getSetup().getMatchType()) {
				case CricketUtil.ODI: case CricketUtil.OD:
					if((j*6) <= CricketFunctions.getBallCountStartAndEndRange(matchAllData, inning).get(1)) {
						whichpowerplay = 1;
						powerplay_omo = 1;
						powerPlay = "$NonPowerPlay";
						value = "$Img_Manhattan2";
					}
					else if((j*6) >= CricketFunctions.getBallCountStartAndEndRange(matchAllData, inning).get(2) && 
							(j*6) <= CricketFunctions.getBallCountStartAndEndRange(matchAllData, inning).get(3)) {
						whichpowerplay = 2;
						powerplay_omo = 0;
						powerPlay = "$PowerPlay";
						value = "$Img_Manhattan1";
					}
					else if((j*6) >= CricketFunctions.getBallCountStartAndEndRange(matchAllData, inning).get(4) && 
							(j*6) <= CricketFunctions.getBallCountStartAndEndRange(matchAllData, inning).get(5)) {
						whichpowerplay = 3;
						powerplay_omo = 1;
						powerPlay = "$NonPowerPlay";
						value = "$Img_Manhattan2";
					}
					break;
				default:
					if((j*6) <= powerPlayValue) {
						whichpowerplay = 1;
						powerplay_omo = 0;
						powerPlay = "$PowerPlay";
						value = "$Img_Manhattan1";
					}else {
						whichpowerplay = 2;
						powerplay_omo = 1;
						powerPlay = "$NonPowerPlay";
						value = "$Img_Manhattan2";
					}
					break;
				}
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$" + whichManhattan + "$Bars$" + j + 
						"$obj_ScaleY$Select_Style*FUNCTION*Omo*vis_con SET " + powerplay_omo + "\0", print_writers);
				
				if(j < manhattan.size()) {
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$" + whichManhattan + "$Bars$Position*FUNCTION*Grid*num_col SET " + j + "\0", print_writers);
					
					lngth = ((195 * Integer.valueOf(manhattan.get(j).getOverTotalRuns())) / maxRuns);
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$" + whichManhattan + "$Bars$" + j + 
							"$obj_ScaleY" + powerPlay + value + "*GEOM*height SET " + lngth + "\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$" + whichManhattan + "$Bars$" + j + 
							"$Wickets$WicketColour*FUNCTION*Omo*vis_con SET " + manhattan.get(j).getOverTotalWickets() + "\0", print_writers);
					
					if(whichpowerplay == 3) {
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$" + whichManhattan + "$Bars$" + j + 
								powerPlay + value + "*TEXTURE*IMAGE SET "  + Constants.BAN_AFG_SERIES_MANHATTAN1 + "EVENT" + "\0", print_writers);
					}else {
						if(value.equalsIgnoreCase("$Img_Manhattan2")) {
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$" + whichManhattan + "$Bars$" + j + 
									powerPlay + value + "*TEXTURE*IMAGE SET "  + Constants.BAN_AFG_SERIES_MANHATTAN2 + inning.getBatting_team().getTeamBadge() + "\0", print_writers);
							
						}else {
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$" + whichManhattan + "$Bars$" + j + 
									powerPlay + value + "*TEXTURE*IMAGE SET "  + Constants.BAN_AFG_SERIES_MANHATTAN1 + inning.getBatting_team().getTeamBadge() + "\0", print_writers);
							
						}
					}
				}
			}
			break;
		case Constants.TRI_SERIES: case Constants.MT20:
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide 
					+ "$select_GraphicsType*FUNCTION*Omo*vis_con SET 8\0", print_writers);
			
			for (int j = 1; j < manhattan.size(); j++) {
				if(manhattan.get(j).getInningNumber() == inning.getInningNumber()) {
					if(Integer.valueOf(manhattan.get(j).getOverTotalRuns()) > maxRuns){
						maxRuns = Integer.valueOf(manhattan.get(j).getOverTotalRuns()); // 33 runs came off 34th over
					}
					while (maxRuns % 5 != 0) {     // 5 label in y-axis
				 		maxRuns = maxRuns + 1;    // keep incrementing till max runs is divisible by 5. maxRuns = 35
					}
				}
			}
			
			for(int i = 0; i < 5;i++) {
				runsIncr = maxRuns / 5; // 35/5=7 -> Y axis will be plot like 6,12,18,24,30 & 36
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$AllGraphics$Side" + WhichSide + "$Manhattan$Runs_Axis$Runs_Data$"
						+ "txt_" + (i+1) + "*GEOM*TEXT SET " + runsIncr*(i+1) + "\0", print_writers);
			}
			
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$AllGraphics$Side" + WhichSide + "$Manhattan$Bar$Position"
					+ "*FUNCTION*Grid*num_row SET 1\0", print_writers);
			
			
			if(!CricketFunctions.getBallCountStartAndEndRange(matchAllData, inning).isEmpty()) {
				powerPlayValue = CricketFunctions.getBallCountStartAndEndRange(matchAllData, inning).get(1);
			}
			for(int j = 1; j <= matchAllData.getSetup().getMaxOvers(); j++) {
				if((j*6) <= powerPlayValue) {
					powerplay_omo = 0;
					powerPlay = "$PowerPlay";
					value= "$img_Base2---";
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$AllGraphics$Side" + WhichSide + "$Manhattan$Bar$" + j + "$obj_ScaleY" 
							+ powerPlay + "$Bar*TEXTURE*IMAGE SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_BASE2 : Constants.MT20_BASE2) + inning.getBatting_team().getTeamBadge() + "\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$AllGraphics$Side" + WhichSide + "$Manhattan$Bar$" + j + "$Wkt" 
							+ powerPlay + "$img_Base1*TEXTURE*IMAGE SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_BASE1 : Constants.MT20_BASE1) + inning.getBatting_team().getTeamBadge() + "\0", print_writers);
				}else {
					powerplay_omo = 1;
					powerPlay = "$Normal";
					value= "$img_Base1-";
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$AllGraphics$Side" + WhichSide + "$Manhattan$Bar$" + j + "$obj_ScaleY" 
							+ powerPlay + "$Bar*TEXTURE*IMAGE SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_BASE1 : Constants.MT20_BASE1) + inning.getBatting_team().getTeamBadge() + "\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$AllGraphics$Side" + WhichSide + "$Manhattan$Bar$" + j + "$Wkt" 
							+ powerPlay + "$img_Base2*TEXTURE*IMAGE SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_BASE2 : Constants.MT20_BASE2) + inning.getBatting_team().getTeamBadge() + "\0", print_writers);
				}
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$AllGraphics$Side" + WhichSide + "$Manhattan$Bar$" + j + 
						"$obj_ScaleY$Select_PowerPlay*FUNCTION*Omo*vis_con SET " + powerplay_omo + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$AllGraphics$Side" + WhichSide + "$Manhattan$Bar$" + j + 
						"$Wkt$Select_PowerPlay*FUNCTION*Omo*vis_con SET " + powerplay_omo + "\0", print_writers);
				
				if(j < manhattan.size()) {
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$AllGraphics$Side" + WhichSide + "$Manhattan$Bar$"
							+ "Position*FUNCTION*Grid*num_col SET " + j + "\0", print_writers);
					lngth = ((199 * Integer.valueOf(manhattan.get(j).getOverTotalRuns())) / maxRuns);
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$AllGraphics$Side" + WhichSide + "$Manhattan$Bar$" + j + 
							"$obj_ScaleY" + powerPlay + "$Bar*GEOM*height SET " + lngth + "\0", print_writers);
					if(lngth == 0) {
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$AllGraphics$Side" + WhichSide + "$Manhattan$Bar$" + j + 
								"$obj_ScaleY" + powerPlay + value + "*ACTIVE SET 0\0", print_writers);
					}else {
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$AllGraphics$Side" + WhichSide + "$Manhattan$Bar$" + j + 
								"$obj_ScaleY" + powerPlay + value + "*ACTIVE SET 1\0", print_writers);
					}
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$AllGraphics$Side" + WhichSide + "$Manhattan$Bar$" + j + 
							"$Wkt" + powerPlay + "$Select_Wickets*FUNCTION*Omo*vis_con SET " + manhattan.get(j).getOverTotalWickets() + "\0", print_writers);
				}
			}
			break;
		}
		return Constants.OK;
	}
	public String SingleTeamBody(List<PrintWriter> print_writers, int WhichSide, Configuration config, MatchAllData matchAllData, Inning whichInning) 
	{
		CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide 
				+ "$select_GraphicsType*FUNCTION*Omo*vis_con SET 2\0", print_writers);
		
		int iRow = 1;
		CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$AllGraphics$Side" + WhichSide + "$TeamSingle$Rows$" 
				+ iRow + "$select_TeamRowType*FUNCTION*Omo*vis_con SET 0\0", print_writers);
		
		if(WhichStyle.equalsIgnoreCase("Age")) {
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$AllGraphics$Side" + WhichSide + "$TeamSingle$Rows$" 
					+ iRow + "$Title$txt_Tittle_1*GEOM*TEXT SET AGE\0", print_writers);
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$AllGraphics$Side" + WhichSide + "$TeamSingle$Rows$" 
					+ iRow + "$Title$txt_Tittle_2*GEOM*TEXT SET ROLE\0", print_writers);
		}
		else {
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$AllGraphics$Side" + WhichSide + "$TeamSingle$Rows$" 
					+ iRow + "$Title$txt_Tittle_1*GEOM*TEXT SET MATCHES\0", print_writers);
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$AllGraphics$Side" + WhichSide + "$TeamSingle$Rows$" 
					+ iRow + "$Title$txt_Tittle_2*GEOM*TEXT SET " + WhichType.toUpperCase() + "\0", print_writers);
		}
		
		//Assuming user is selecting batting team Id of either inning 1 or inning 2
		for(int i=1;i<=PlayingXI.size();i++) {
			iRow ++;
			rowId = i-1;
			stat = statisticsList.stream().filter(stat -> stat.getPlayer_id() == PlayingXI.get(rowId).getPlayerId()).findAny().orElse(null);
			if(stat == null) {
				return "populatePlayerProfile: No stats found for player id [" + PlayingXI.get(rowId).getPlayerId() + "] from database is returning NULL";
			}
			
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$AllGraphics$Side" + WhichSide + "$TeamSingle$Rows$" 
					+ iRow + "$select_TeamRowType*FUNCTION*Omo*vis_con SET 1\0", print_writers);
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$AllGraphics$Side" + WhichSide + "$TeamSingle$Rows$" 
					+ iRow + "$Name$txt_FirstName*GEOM*TEXT SET " + PlayingXI.get(i-1).getFirstname() + " \0", print_writers);
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$AllGraphics$Side" + WhichSide + "$TeamSingle$Rows$" 
					+ iRow + "$Name$txt_LastName*GEOM*TEXT SET " + (PlayingXI.get(i-1).getSurname() != null ? PlayingXI.get(rowId).getSurname()
					: "") + " \0", print_writers);
			
			if(WhichStyle.equalsIgnoreCase("Age")) {
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$AllGraphics$Side" + WhichSide + "$TeamSingle$Rows$" 
						+ iRow + "$Name$txt_Description*GEOM*TEXT SET \0", print_writers);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$AllGraphics$Side" + WhichSide + "$TeamSingle$Rows$" 
						+ iRow + "$Name$fig_1*GEOM*TEXT SET " + PlayingXI.get(i-1).getAge() + " \0", print_writers);
				
				if(PlayingXI.get(i-1).getRole().equalsIgnoreCase(CricketUtil.BATSMAN) || PlayingXI.get(i-1).getRole().equalsIgnoreCase("BAT/KEEPER")) {
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$AllGraphics$Side" + WhichSide + "$TeamSingle$Rows$" 
							+ iRow + "$Name$fig_2*GEOM*TEXT SET " + "BATTER" + " \0", print_writers);
				}
				else if(PlayingXI.get(i-1).getRole().equalsIgnoreCase(CricketUtil.BOWLER)) {
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$AllGraphics$Side" + WhichSide + "$TeamSingle$Rows$" 
							+ iRow + "$Name$fig_2*GEOM*TEXT SET " + "BOWLER" + " \0", print_writers);
				}
				else if(PlayingXI.get(i-1).getRole().equalsIgnoreCase("ALL-ROUNDER")) {
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$AllGraphics$Side" + WhichSide + "$TeamSingle$Rows$" 
							+ iRow + "$Name$fig_2*GEOM*TEXT SET " + "ALL-ROUNDER" + " \0", print_writers);
				}
				
				if(PlayingXI.get(i-1).getCaptainWicketKeeper() != null) {
					if(PlayingXI.get(i-1).getCaptainWicketKeeper().equalsIgnoreCase(CricketUtil.CAPTAIN)){
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$AllGraphics$Side" + WhichSide + "$TeamSingle$Rows$" 
								+ iRow + "$Name$txt_Captain*GEOM*TEXT SET (C) \0", print_writers);
					}
					else if(PlayingXI.get(i-1).getCaptainWicketKeeper().equalsIgnoreCase(CricketUtil.WICKET_KEEPER)) {
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$AllGraphics$Side" + WhichSide + "$TeamSingle$Rows$" 
								+ iRow + "$Name$txt_Captain*GEOM*TEXT SET \0", print_writers);
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$AllGraphics$Side" + WhichSide + "$TeamSingle$Rows$" 
								+ iRow + "$Name$fig_2*GEOM*TEXT SET " + "KEEPER" + " \0", print_writers);
					}
					else if(PlayingXI.get(i-1).getCaptainWicketKeeper().equalsIgnoreCase("CAPTAIN_WICKET_KEEPER")) {
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$AllGraphics$Side" + WhichSide + "$TeamSingle$Rows$" 
								+ iRow + "$Name$txt_Captain*GEOM*TEXT SET (C) \0", print_writers);
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$AllGraphics$Side" + WhichSide + "$TeamSingle$Rows$" 
								+ iRow + "$Name$fig_2*GEOM*TEXT SET " + "KEEPER" + " \0", print_writers);
					}
				}else {
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$AllGraphics$Side" + WhichSide + "$TeamSingle$Rows$" 
							+ iRow + "$Name$txt_Captain*GEOM*TEXT SET \0", print_writers);
				}
				
			}else {
				
				if(PlayingXI.get(i-1).getRole().equalsIgnoreCase(CricketUtil.BATSMAN) || PlayingXI.get(i-1).getRole().equalsIgnoreCase("BAT/KEEPER")) {
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$AllGraphics$Side" + WhichSide + "$TeamSingle$Rows$" 
							+ iRow + "$Name$txt_Description*GEOM*TEXT SET " + "BATTER" + " \0", print_writers);
				}
				else if(PlayingXI.get(i-1).getRole().equalsIgnoreCase(CricketUtil.BOWLER)) {
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$AllGraphics$Side" + WhichSide + "$TeamSingle$Rows$" 
							+ iRow + "$Name$txt_Description*GEOM*TEXT SET " + "BOWLER" + " \0", print_writers);
				}
				else if(PlayingXI.get(i-1).getRole().equalsIgnoreCase("ALL-ROUNDER")) {
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$AllGraphics$Side" + WhichSide + "$TeamSingle$Rows$" 
							+ iRow + "$Name$txt_Description*GEOM*TEXT SET " + "ALL-ROUNDER" + " \0", print_writers);
				}
				
				if(PlayingXI.get(i-1).getCaptainWicketKeeper() != null) {
					if(PlayingXI.get(i-1).getCaptainWicketKeeper().equalsIgnoreCase(CricketUtil.CAPTAIN)){
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$AllGraphics$Side" + WhichSide + "$TeamSingle$Rows$" 
								+ iRow + "$Name$txt_Captain*GEOM*TEXT SET (C) \0", print_writers);
					}
					else if(PlayingXI.get(i-1).getCaptainWicketKeeper().equalsIgnoreCase(CricketUtil.WICKET_KEEPER)) {
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$AllGraphics$Side" + WhichSide + "$TeamSingle$Rows$" 
								+ iRow + "$Name$txt_Captain*GEOM*TEXT SET \0", print_writers);
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$AllGraphics$Side" + WhichSide + "$TeamSingle$Rows$" 
								+ iRow + "$Name$txt_Description*GEOM*TEXT SET " + "KEEPER" + " \0", print_writers);
					}
					else if(PlayingXI.get(i-1).getCaptainWicketKeeper().equalsIgnoreCase("CAPTAIN_WICKET_KEEPER")) {
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$AllGraphics$Side" + WhichSide + "$TeamSingle$Rows$" 
								+ iRow + "$Name$txt_Captain*GEOM*TEXT SET (C) \0", print_writers);
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$AllGraphics$Side" + WhichSide + "$TeamSingle$Rows$" 
								+ iRow + "$Name$txt_Description*GEOM*TEXT SET " + "KEEPER" + " \0", print_writers);
					}
				}else {
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$AllGraphics$Side" + WhichSide + "$TeamSingle$Rows$" 
							+ iRow + "$Name$txt_Captain*GEOM*TEXT SET \0", print_writers);
				}
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$AllGraphics$Side" + WhichSide + "$TeamSingle$Rows$" 
						+ iRow + "$Name$fig_1*GEOM*TEXT SET " + stat.getMatches() + " \0", print_writers);
				
				switch(WhichType.toUpperCase()) {
				case "RUNS":
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$AllGraphics$Side" + WhichSide + "$TeamSingle$Rows$" 
							+ iRow + "$Name$fig_2*GEOM*TEXT SET " + stat.getRuns() + " \0", print_writers);
					break;
				case "S/R":
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$AllGraphics$Side" + WhichSide + "$TeamSingle$Rows$" 
							+ iRow + "$Name$fig_2*GEOM*TEXT SET " + CricketFunctions.generateStrikeRate(stat.getRuns(), stat.getBalls_faced(), 1) 
							+ " \0", print_writers);
					break;
				case "WICKETS":
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$AllGraphics$Side" + WhichSide + "$TeamSingle$Rows$" 
							+ iRow + "$Name$fig_2*GEOM*TEXT SET " + stat.getWickets() + " \0", print_writers);
					break;
				case "ECONOMY":
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$AllGraphics$Side" + WhichSide + "$TeamSingle$Rows$" 
							+ iRow + "$Name$fig_2*GEOM*TEXT SET " + CricketFunctions.getEconomy(stat.getRuns_conceded(), stat.getBalls_bowled(), 2, "-") 
							+ " \0", print_writers);
					break;
				}
			}
		}
		
		return Constants.OK;
	}
	public String DoubleManhattanBody(List<PrintWriter> print_writers, int WhichSide, Configuration config, MatchAllData matchAllData, Inning inning) {
		
		int maxRuns = 0,runsIncr = 0,powerplay_omo=0,powerPlayValue = 0;
		double lngth = 0;
		String powerPlay = "",value="",whichManhattan="";
		List<OverByOverData> manhattanData = new ArrayList<OverByOverData>();
		
		switch(config.getBroadcaster()) {
		case Constants.AFG_SL_SERIES:
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$select_GraphicsType"
					+ "*FUNCTION*Omo*vis_con SET 10\0", print_writers);
			
			for(int iTeam=1; iTeam<=2; iTeam++) {
				if(iTeam == 1) {
					maxRuns = runsIncr = powerplay_omo = 0;
					lngth = 0;
					powerPlay = "";
					manhattanData = manhattan;
				}else {
					maxRuns = runsIncr = powerplay_omo = 0;
					lngth = 0;
					powerPlay = "";
					manhattanData = manhattan2;
				}
				
				switch (matchAllData.getSetup().getMatchType()) {
				case CricketUtil.ODI: case CricketUtil.OD:
					whichManhattan = "$50_Overs";
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$ManhattanComparison$Team" + iTeam + 
							"$Bar$select_BarNumbers*FUNCTION*Omo*vis_con SET 2\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$ManhattanComparison$Team" + iTeam + 
							"$Overs_Axis$Data$select_OverNumbers*FUNCTION*Omo*vis_con SET 2\0", print_writers);
					break;
				case CricketUtil.DT20: case CricketUtil.IT20:
					whichManhattan = "$20_Overs";
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$ManhattanComparison$Team" + iTeam + 
							"$Bar$select_BarNumbers*FUNCTION*Omo*vis_con SET 1\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$ManhattanComparison$Team" + iTeam + 
							"$Overs_Axis$Data$select_OverNumbers*FUNCTION*Omo*vis_con SET 1\0", print_writers);
					break;
				default:
					whichManhattan = "$10_Overs";
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$ManhattanComparison$Team" + iTeam + 
							"$Bar$select_BarNumbers*FUNCTION*Omo*vis_con SET 0\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$ManhattanComparison$Team" + iTeam + 
							"$Overs_Axis$Data$select_OverNumbers*FUNCTION*Omo*vis_con SET 0\0", print_writers);
					break;
				}
				
				for(int l = 1; l <= 10; l++) {
					switch (matchAllData.getSetup().getMatchType()) {
					case CricketUtil.ODI: case CricketUtil.OD:
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$ManhattanComparison$Team" + iTeam + 
								"$Overs_Axis$Overs_Data$" + whichManhattan + "$txt_" + (l+1) + "*GEOM*TEXT SET " + (5*l) + "\0", print_writers);
						break;
					case CricketUtil.DT20: case CricketUtil.IT20:
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$ManhattanComparison$Team" + iTeam + 
								"$Overs_Axis$Overs_Data$" + whichManhattan + "$txt_" + (l+1) + "*GEOM*TEXT SET " + (2*l) + "\0", print_writers);
						break;
					default:
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$ManhattanComparison$Team" + iTeam + 
								"$Overs_Axis$Overs_Data$" + l + "$txt_" + l + "*GEOM*TEXT SET " + (2*l) + "\0", print_writers);
						break;
					}
				}
				
				for (int j = 1; j < manhattanData.size(); j++) {
					if(manhattanData.get(j).getInningNumber() == iTeam) {
						if(Integer.valueOf(manhattanData.get(j).getOverTotalRuns()) > maxRuns){
							maxRuns = Integer.valueOf(manhattanData.get(j).getOverTotalRuns()); // 33 runs came off 34th over
						}
						while (maxRuns % 3 != 0) {     // 3 label in y-axis
					 		maxRuns = maxRuns + 1;    // keep incrementing till max runs is divisible by 3. maxRuns = 35
						}
					}
				}
				
				runsIncr = maxRuns / 3; // 35/5=7 -> Y axis will be plot like 6,12,18,24,30 & 36
				for(int i = 1; i<=3;i++) {
					 CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$ManhattanComparison$Team" + iTeam + 
							 "$Runs_Axis$Runs_Data$txt_" + i + "*GEOM*TEXT SET " + (runsIncr * i) + "\0", print_writers);
				}
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$ManhattanComparison$Team" + iTeam + 
						"$Bar" + whichManhattan + "$Position*FUNCTION*Grid*num_row SET 1\0", print_writers);
				
				if(!CricketFunctions.getBallCountStartAndEndRange(matchAllData, inning).isEmpty()) {
					powerPlayValue = CricketFunctions.getBallCountStartAndEndRange(matchAllData, inning).get(1);
				}
				
				for(int j = 1; j <= matchAllData.getSetup().getMaxOvers(); j++) {
					
					switch (matchAllData.getSetup().getMatchType()) {
					case CricketUtil.ODI: case CricketUtil.OD:
						if((j*6) <= CricketFunctions.getBallCountStartAndEndRange(matchAllData, inning).get(1)) {
							powerplay_omo = 0;
							powerPlay = "$PowerPlay1";
						}
						else if((j*6) >= CricketFunctions.getBallCountStartAndEndRange(matchAllData, inning).get(2) && 
								(j*6) <= CricketFunctions.getBallCountStartAndEndRange(matchAllData, inning).get(3)) {
							powerplay_omo = 1;
							powerPlay = "$PowerPlay2";
						}
						else if((j*6) >= CricketFunctions.getBallCountStartAndEndRange(matchAllData, inning).get(4) && 
								(j*6) <= CricketFunctions.getBallCountStartAndEndRange(matchAllData, inning).get(5)) {
							powerplay_omo = 2;
							powerPlay = "$PowerPlay3";
						}
						break;
					default:
						if((j*6) <= powerPlayValue) {
							powerplay_omo = 0;
							powerPlay = "$PowerPlay1";
						}else {
							powerplay_omo = 3;
							powerPlay = "$Normal";
						}
						break;
					}
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$ManhattanComparison$Team" + iTeam + "$Bar" 
							+ whichManhattan + "$Position$" + j + "$obj_ScaleY$Select_PowerPlay*FUNCTION*Omo*vis_con SET " + powerplay_omo + "\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$ManhattanComparison$Team" + iTeam + "$Bar" 
							+ whichManhattan + "$Position$" + j + "$Wkt$Select_PowerPlay*FUNCTION*Omo*vis_con SET " + powerplay_omo + "\0", print_writers);
					
					if(j < manhattanData.size()) {
						
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$ManhattanComparison$Team" + iTeam + "$Bar" 
								+ whichManhattan + "$Position*FUNCTION*Grid*num_col SET " + j + "\0", print_writers);
						
						lngth = ((1.0 * Integer.valueOf(manhattanData.get(j).getOverTotalRuns())) / maxRuns);
						
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$ManhattanComparison$Team" + iTeam + "$Bar" 
								+ whichManhattan + "$Position$" + j + "$obj_ScaleY*TRANSFORMATION*SCALING*Y SET " + lngth + "\0", print_writers);
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$ManhattanComparison$Team" + iTeam + "$Bar" 
								+ whichManhattan + "$Position$" + j + "$Wkt" + powerPlay + "$Select_Wickets*FUNCTION*Omo*vis_con SET " + manhattanData.get(j).getOverTotalWickets() 
								+ "\0", print_writers);
					}
				}
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$ManhattanComparison$Team" + iTeam + "$Title$select_Toss"
						+ "*FUNCTION*Omo*vis_con SET 0\0", print_writers);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$ManhattanComparison$Team" + iTeam + "$Title$txt_TeamName"
						+ "*GEOM*TEXT SET " + matchAllData.getMatch().getInning().get(iTeam-1).getBatting_team().getTeamName1().toUpperCase() + " \0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$ManhattanComparison$Team" + iTeam + "$Title$txt_OversHead"
						+ "*GEOM*TEXT SET OVERS\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$ManhattanComparison$Team" + iTeam + "$Title$txt_OversValue"
						+ "*GEOM*TEXT SET " + CricketFunctions.OverBalls(matchAllData.getMatch().getInning().get(iTeam-1).getTotalOvers(), matchAllData.getMatch().getInning().get(iTeam-1).getTotalBalls()) 
						+ " \0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$ManhattanComparison$Team" + iTeam + "$Title$txt_Scores*GEOM*TEXT SET " 
						+ CricketFunctions.getTeamScore(matchAllData.getMatch().getInning().get(iTeam-1), "-", false) + " \0", print_writers);
			}
			break;
		case Constants.TRI_SERIES: case Constants.MT20:
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide 
					+ "$select_GraphicsType*FUNCTION*Omo*vis_con SET 9\0", print_writers);
			
			for(int iTeam=1; iTeam<=2; iTeam++) {
				if(iTeam == 1) {
					maxRuns = runsIncr = powerplay_omo = 0;
					lngth = 0;
					powerPlay = "";
					manhattanData = manhattan;
				}else {
					maxRuns = runsIncr = powerplay_omo = 0;
					lngth = 0;
					powerPlay = "";
					
					manhattanData = manhattan2;
				}
				
				for (int j = 1; j < manhattanData.size(); j++) {
					if(manhattanData.get(j).getInningNumber() == iTeam) {
						if(Integer.valueOf(manhattanData.get(j).getOverTotalRuns()) > maxRuns){
							maxRuns = Integer.valueOf(manhattanData.get(j).getOverTotalRuns()); // 33 runs came off 34th over
						}
						while (maxRuns % 3 != 0) {     // 3 label in y-axis
					 		maxRuns = maxRuns + 1;    // keep incrementing till max runs is divisible by 3. maxRuns = 35
						}
					}
				}
				
				runsIncr = maxRuns / 3; // 35/5=7 -> Y axis will be plot like 6,12,18,24,30 & 36
				for(int i = 0; i < 3;i++) {
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$AllGraphics$Side" + WhichSide + "$ManhattanComparison$"
							+ "Team_" + iTeam + "$Runs_Axis$Runs_Data$txt_" + (i+1) + "*GEOM*TEXT SET " + runsIncr*(i+1) + "\0", print_writers);
				}
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$AllGraphics$Side" + WhichSide + "$ManhattanComparison$"
						+ "Team_" + iTeam + "$Bar$Position*FUNCTION*Grid*num_row SET 1\0", print_writers);
				
				if(!CricketFunctions.getBallCountStartAndEndRange(matchAllData, inning).isEmpty()) {
					powerPlayValue = CricketFunctions.getBallCountStartAndEndRange(matchAllData, inning).get(1);
				}
				
				for(int j = 1; j <= matchAllData.getSetup().getMaxOvers(); j++) {
					if(j < manhattanData.size()) {
						if((j*6) <= powerPlayValue) {
							powerplay_omo = 0;
							powerPlay = "$PowerPlay";
							value="img_Base2-";
							
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$AllGraphics$Side" + WhichSide + "$ManhattanComparison$Team_" 
									+ iTeam + "$Bar$" + j + "$obj_ScaleY" + powerPlay + "$Bar*TEXTURE*IMAGE SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_BASE2 : Constants.MT20_BASE2) + 
									matchAllData.getMatch().getInning().get(iTeam-1).getBatting_team().getTeamBadge() + "\0", print_writers);
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$AllGraphics$Side" + WhichSide + "$ManhattanComparison$Team_" 
									+ iTeam + "$Bar$" + j + "$Wkt" + powerPlay + "$img_Base1*TEXTURE*IMAGE SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_BASE1 : Constants.MT20_BASE1) + 
									matchAllData.getMatch().getInning().get(iTeam-1).getBatting_team().getTeamBadge() + "\0", print_writers);
						}else {
							powerplay_omo = 1;
							powerPlay = "$Normal";
							value="img_Base1-";
							
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$AllGraphics$Side" + WhichSide + "$ManhattanComparison$Team_" 
									+ iTeam + "$Bar$" + j + "$obj_ScaleY" + powerPlay + "$Bar*TEXTURE*IMAGE SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_BASE1 : Constants.MT20_BASE1) + 
									matchAllData.getMatch().getInning().get(iTeam-1).getBatting_team().getTeamBadge() + "\0", print_writers);
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$AllGraphics$Side" + WhichSide + "$ManhattanComparison$Team_" 
									+ iTeam + "$Bar$" + j + "$Wkt" + powerPlay + "$img_Base2*TEXTURE*IMAGE SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_BASE2 : Constants.MT20_BASE2) + 
									matchAllData.getMatch().getInning().get(iTeam-1).getBatting_team().getTeamBadge() + "\0", print_writers);
						}
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$AllGraphics$Side" + WhichSide + "$ManhattanComparison$Team_" 
								+ iTeam + "$Bar$" + j + "$obj_ScaleY$Select_PowerPlay*FUNCTION*Omo*vis_con SET " + powerplay_omo + "\0", print_writers);
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$AllGraphics$Side" + WhichSide + "$ManhattanComparison$Team_" 
								+ iTeam + "$Bar$" + j + "$Wkt$Select_PowerPlay*FUNCTION*Omo*vis_con SET " + powerplay_omo + "\0", print_writers);
						
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$AllGraphics$Side" + WhichSide + "$ManhattanComparison$Team_" 
								+ iTeam + "$Bar$Position*FUNCTION*Grid*num_col SET " + j + "\0", print_writers);
						
						lngth = ((71 * Integer.valueOf(manhattanData.get(j).getOverTotalRuns())) / maxRuns);
						
						if(lngth == 0) {
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$AllGraphics$Side" + WhichSide + "$ManhattanComparison$Team_" 
									+ iTeam + "$Bar$" + j + "$obj_ScaleY" + powerPlay + value + "*ACTIVE SET 0\0", print_writers);
						}else {
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$AllGraphics$Side" + WhichSide + "$ManhattanComparison$Team_" 
									+ iTeam + "$Bar$" + j + "$obj_ScaleY" + powerPlay + value + "*ACTIVE SET 1\0", print_writers);
						}
						
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$AllGraphics$Side" + WhichSide + "$ManhattanComparison$Team_" 
								+ iTeam + "$Bar$" + j + "$obj_ScaleY" + powerPlay + "$Bar*GEOM*height SET " + lngth + "\0", print_writers);
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$AllGraphics$Side" + WhichSide + "$ManhattanComparison$Team_" 
								+ iTeam + "$Bar$" + j + "$Wkt" + powerPlay + "$Select_Wickets*FUNCTION*Omo*vis_con SET " + manhattanData.get(j).getOverTotalWickets() 
								+ "\0", print_writers);
					}
				}
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$AllGraphics$Side" + WhichSide + "$ManhattanComparison$Team_" 
						+ iTeam + "$Title$txt_Team*GEOM*TEXT SET " + matchAllData.getMatch().getInning().get(iTeam-1).getBatting_team().getTeamName1().toUpperCase() 
						+ " \0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$AllGraphics$Side" + WhichSide + "$ManhattanComparison$Team_" 
						+ iTeam + "$Title$txt_Overs*GEOM*TEXT SET OVERS\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$AllGraphics$Side" + WhichSide + "$ManhattanComparison$Team_" 
						+ iTeam + "$Title$txt_Overs_Value*GEOM*TEXT SET " + CricketFunctions.OverBalls(matchAllData.getMatch().getInning().get(iTeam-1).getTotalOvers(),
						matchAllData.getMatch().getInning().get(iTeam-1).getTotalBalls()) + " \0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$AllGraphics$Side" + WhichSide + "$ManhattanComparison$Team_" 
						+ iTeam + "$Title$txt_DLS_Value*GEOM*TEXT SET \0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$AllGraphics$Side" + WhichSide + "$ManhattanComparison$Team_" 
						+ iTeam + "$Title$txt_Score*GEOM*TEXT SET " + CricketFunctions.getTeamScore(matchAllData.getMatch().getInning().get(iTeam-1), "-", false) 
						+ " \0", print_writers);
			}
			break;
		}
		return Constants.OK;
	}	
	public String WormBody(List<PrintWriter> print_writers, int WhichSide, Configuration config, MatchAllData matchAllData,Inning inning) {
		
		int maxRuns = 0,runsIncr = 0,wkt_count=0,j=0,txtIndex1 =0;
		
		switch(config.getBroadcaster()) {
		case Constants.AFG_SL_SERIES:
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$select_GraphicsType"
					+ "*FUNCTION*Omo*vis_con SET 8\0", print_writers);
			
			if(inning.getInningNumber() == 1) {
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$Worms$Title$select_Teams*FUNCTION*Omo*vis_con SET 1\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$Worms$WormData$Graph$select_Teams*FUNCTION*Omo*vis_con SET 1\0", print_writers);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$Worms$Title$Team1$txt_Tittle*GEOM*TEXT SET " 
						+ inning.getBatting_team().getTeamName1() + " \0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$Worms$Title$Team1$txt_Scores*GEOM*TEXT SET " 
						+ CricketFunctions.getTeamScore(inning, "-", false) + " \0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$Worms$Title$Team1$txt_Overs*GEOM*TEXT SET " 
						+ CricketFunctions.OverBalls(inning.getTotalOvers(), inning.getTotalBalls()) + " \0", print_writers);
				
				maxRuns = inning.getTotalRuns();
			}
			else if(inning.getInningNumber() == 2) {
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$Worms$Title$select_Teams*FUNCTION*Omo*vis_con SET 2\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$Worms$WormData$Graph$select_Teams*FUNCTION*Omo*vis_con SET 2\0", print_writers);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$Worms$Title$Team1$txt_Tittle*GEOM*TEXT SET " 
						+ matchAllData.getMatch().getInning().get(0).getBatting_team().getTeamName1() + " \0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$Worms$Title$Team1$txt_Scores*GEOM*TEXT SET " 
						+ CricketFunctions.getTeamScore(matchAllData.getMatch().getInning().get(0), "-", false) + " \0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$Worms$Title$Team1$txt_Overs*GEOM*TEXT SET " 
						+ CricketFunctions.OverBalls(matchAllData.getMatch().getInning().get(0).getTotalOvers(), matchAllData.getMatch().getInning().get(0).getTotalBalls()) + " \0", print_writers);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$Worms$Title$Team2$txt_Tittle*GEOM*TEXT SET " 
						+ matchAllData.getMatch().getInning().get(1).getBatting_team().getTeamName1() + " \0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$Worms$Title$Team2$txt_Scores*GEOM*TEXT SET " 
						+ CricketFunctions.getTeamScore(matchAllData.getMatch().getInning().get(1), "-", false) + " \0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$Worms$Title$Team2$txt_Overs*GEOM*TEXT SET " 
						+ CricketFunctions.OverBalls(matchAllData.getMatch().getInning().get(1).getTotalOvers(), matchAllData.getMatch().getInning().get(1).getTotalBalls()) + " \0", print_writers);
				
				if(matchAllData.getMatch().getInning().get(0).getTotalRuns() > matchAllData.getMatch().getInning().get(1).getTotalRuns()) {
					maxRuns = matchAllData.getMatch().getInning().get(0).getTotalRuns();
				}
				else {
					maxRuns = matchAllData.getMatch().getInning().get(1).getTotalRuns();
				}
			}
			
			for(int l = 1; l <= 10; l++) {           // For Y-Axis Value
				switch (matchAllData.getSetup().getMatchType()) {
				case CricketUtil.ODI: case CricketUtil.OD:
					txtIndex1 = 5*l;
					break;
				default:
					txtIndex1 = 2*l;
					break;
				}
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$Worms$WormData$Overs_Axis$Arrange$"
						+ "txt_" + (l+1) + "*GEOM*TEXT SET " + txtIndex1 + "\0", print_writers);
			}
			
			for(int inn_count = 1; inn_count <= inning.getInningNumber(); inn_count++) {
				List<String> overByOverRuns = new ArrayList<String>();
				List<String> overByOverwicket = new ArrayList<String>();
				
				overByOverRuns.clear();
				overByOverwicket.clear();
				//wicket_which_over = "";
				for(OverByOverData Over : CricketFunctions.getOverByOverData(matchAllData,inn_count ,"WORM" ,matchAllData.getEventFile().getEvents())) {
					overByOverRuns.add(String.valueOf(Over.getOverTotalRuns()));
				}
				String cumm_runs = String.valueOf(0) + "," + String.join(",", overByOverRuns); // Store Per Overs Runs
				
				if(inn_count == 2) {
					wkt_count=0;
				}
				for(OverByOverData Wicket : CricketFunctions.getOverByOverData(matchAllData,inn_count ,"WORM" ,matchAllData.getEventFile().getEvents())) {
					wkt_count = wkt_count + 1;
					
					if(Wicket.getOverTotalWickets() > 0) {
						for(int w=1; w <= Wicket.getOverTotalWickets(); w++) {
							overByOverwicket.add(String.valueOf(wkt_count-1));
						}
					}
				}
				String cumm_wkts = String.join(",", overByOverwicket); // Store Per Overs Wickets
				
				if(matchAllData.getMatch().getInning().get(0).getTotalRuns() > matchAllData.getMatch().getInning().get(1).getTotalRuns()) {
					maxRuns = matchAllData.getMatch().getInning().get(0).getTotalRuns();
				}
				else {
					maxRuns = matchAllData.getMatch().getInning().get(1).getTotalRuns();
				}
				if(maxRuns % 5 == 0) {
					maxRuns = maxRuns + 1;
				}
				while (maxRuns % 5 != 0) {     // 5 label in y-axis
					maxRuns = maxRuns + 1;    // keep incrementing till max runs is divisible by 5. maxRuns = 35
				}
				
				for(int k = 1; k <= 5; k++) {           // For Y-Axis Value 
					runsIncr = maxRuns / 5; // 35/5=7 -> Y axis will be plot like 6,12,18,24,30 & 36
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$Worms$WormData$Runs_Axis$Runs_Data$"
							+ "txt_" + k + "*GEOM*TEXT SET " + (runsIncr*k) + "\0", print_writers);
				}
				
				if(inn_count == 1) {
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$Worms$WormData$Graph$" + inn_count + 
							"$AnimWorm*SCRIPT*INSTANCE*strDataY SET "+ cumm_runs.replaceFirst("0,", "") +"\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$Worms$WormData$Graph$" + inn_count + 
							"$AnimWorm*SCRIPT*INSTANCE*iGraphHeightInRuns SET " + maxRuns +"\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$Worms$WormData$Graph$" + inn_count + 
							"$AnimWorm*SCRIPT*INSTANCE*iGraphWidthInOvers SET " + matchAllData.getSetup().getMaxOvers() +"\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$Worms$WormData$Graph$" + inn_count + 
							"$AnimWorm*SCRIPT*INSTANCE*iNumberOfOversForRandomData SET " + matchAllData.getSetup().getMaxOvers() +"\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$Worms$WormData$Graph$" + inn_count + 
							"$AnimWorm*SCRIPT*INSTANCE*iSetWorm INVOKE \0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$Worms$WormData$Graph$" + inn_count + 
							"$AnimWorm*SCRIPT*INSTANCE*strWicketsData SET " + cumm_wkts +"\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$Worms$WormData$Graph$" + inn_count + 
							"$AnimWorm*SCRIPT*INSTANCE*iSetWorm INVOKE \0", print_writers);
				}else {
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$Worms$WormData$Graph$" + inn_count + 
							"$AnimWorm*SCRIPT*INSTANCE*strDataY SET "+ cumm_runs.replaceFirst("0,", "") +"\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$Worms$WormData$Graph$" + inn_count + 
							"$AnimWorm*SCRIPT*INSTANCE*iGraphHeightInRuns SET " + maxRuns +"\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$Worms$WormData$Graph$" + inn_count + 
							"$AnimWorm*SCRIPT*INSTANCE*iGraphWidthInOvers SET " + matchAllData.getSetup().getMaxOvers() +"\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$Worms$WormData$Graph$" + inn_count + 
							"$AnimWorm*SCRIPT*INSTANCE*iNumberOfOversForRandomData SET " + matchAllData.getSetup().getMaxOvers() +"\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$Worms$WormData$Graph$" + inn_count + 
							"$AnimWorm*SCRIPT*INSTANCE*iSetWorm INVOKE \0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$Worms$WormData$Graph$" + inn_count + 
							"$AnimWorm*SCRIPT*INSTANCE*strWicketsData SET " + cumm_wkts +"\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$Worms$WormData$Graph$" + inn_count + 
							"$AnimWorm*SCRIPT*INSTANCE*iSetWorm INVOKE \0", print_writers);						
				}
			}
			
			
			break;
		case Constants.BAN_AFG_SERIES: case Constants.ACC:
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$Select*FUNCTION*Omo*vis_con SET 5\0", print_writers);
			
			if(inning.getInningNumber() == 1) {
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$RunWorm$WormDataAll$Select_Team*FUNCTION*Omo*vis_con SET 0 \0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$RunWorm$WormDataAll$Graph$Worms$Team_2*ACTIVE SET 0\0", print_writers);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$RunWorm$WormDataAll$1_Teams$Team_1$Data$txt_Tittle*GEOM*TEXT SET " 
						+ inning.getBatting_team().getTeamName1() + " \0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$RunWorm$WormDataAll$1_Teams$Team_1$Data$txt_Runs*GEOM*TEXT SET " 
						+ CricketFunctions.getTeamScore(inning, "-", false) + " \0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$RunWorm$WormDataAll$1_Teams$Team_1$Data$txt_Balls*GEOM*TEXT SET " 
						+ CricketFunctions.OverBalls(inning.getTotalOvers(), inning.getTotalBalls()) + " \0", print_writers);
				
				maxRuns = inning.getTotalRuns();
			}
			else if(inning.getInningNumber() == 2) {
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$RunWorm$WormDataAll$Select_Team*FUNCTION*Omo*vis_con SET 1 \0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$RunWorm$WormDataAll$Graph$Worms$Team_2*ACTIVE SET 1\0", print_writers);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$RunWorm$WormDataAll$2_Teams$Team_1$Data$txt_Tittle*GEOM*TEXT SET " 
						+ matchAllData.getMatch().getInning().get(0).getBatting_team().getTeamName1() + " \0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$RunWorm$WormDataAll$2_Teams$Team_1$Data$txt_Runs*GEOM*TEXT SET " 
						+ CricketFunctions.getTeamScore(matchAllData.getMatch().getInning().get(0), "-", false) + " \0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$RunWorm$WormDataAll$2_Teams$Team_1$Data$txt_Balls*GEOM*TEXT SET " 
						+ CricketFunctions.OverBalls(matchAllData.getMatch().getInning().get(0).getTotalOvers(), matchAllData.getMatch().getInning().get(0).getTotalBalls()) + " \0", print_writers);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$RunWorm$WormDataAll$2_Teams$Team_2$Data$txt_Tittle*GEOM*TEXT SET " 
						+ matchAllData.getMatch().getInning().get(1).getBatting_team().getTeamName1() + " \0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$RunWorm$WormDataAll$2_Teams$Team_2$Data$txt_Runs*GEOM*TEXT SET " 
						+ CricketFunctions.getTeamScore(matchAllData.getMatch().getInning().get(1), "-", false) + " \0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$RunWorm$WormDataAll$2_Teams$Team_2$Data$txt_Balls*GEOM*TEXT SET " 
						+ CricketFunctions.OverBalls(matchAllData.getMatch().getInning().get(1).getTotalOvers(), matchAllData.getMatch().getInning().get(1).getTotalBalls()) + " \0", print_writers);
				
				if(matchAllData.getMatch().getInning().get(0).getTotalRuns() > matchAllData.getMatch().getInning().get(1).getTotalRuns()) {
					maxRuns = matchAllData.getMatch().getInning().get(0).getTotalRuns();
				}
				else {
					maxRuns = matchAllData.getMatch().getInning().get(1).getTotalRuns();
				}
			}
			
			for (int i = 0; i <= 20; i++) {
	            if (i % 2 == 0) {
	            	j=j+1;
	            	CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$RunWorm$WormDataAll$Graph$Overs_Axis$Arrange$txt_" + j + 
	            			"*GEOM*TEXT SET " + i + "\0", print_writers);
	            }
	        }
			
			for(int inn_count = 1; inn_count <= inning.getInningNumber(); inn_count++) {
				List<String> overByOverRuns = new ArrayList<String>();
				List<String> overByOverwicket = new ArrayList<String>();
				
				overByOverRuns.clear();
				overByOverwicket.clear();
				//wicket_which_over = "";
				for(OverByOverData Over : CricketFunctions.getOverByOverData(matchAllData,inn_count ,"WORM" ,matchAllData.getEventFile().getEvents())) {
					overByOverRuns.add(String.valueOf(Over.getOverTotalRuns()));
				}
				String cumm_runs = String.valueOf(0) + "," + String.join(",", overByOverRuns); // Store Per Overs Runs
				
				if(inn_count == 2) {
					wkt_count=0;
				}
				for(OverByOverData Wicket : CricketFunctions.getOverByOverData(matchAllData,inn_count ,"WORM" ,matchAllData.getEventFile().getEvents())) {
					wkt_count = wkt_count + 1;
					
					if(Wicket.getOverTotalWickets() > 0) {
						for(int w=1; w <= Wicket.getOverTotalWickets(); w++) {
							overByOverwicket.add(String.valueOf(wkt_count-1));
						}
					}
				}
				String cumm_wkts = String.join(",", overByOverwicket); // Store Per Overs Wickets
				
				if(matchAllData.getMatch().getInning().get(0).getTotalRuns() > matchAllData.getMatch().getInning().get(1).getTotalRuns()) {
					maxRuns = matchAllData.getMatch().getInning().get(0).getTotalRuns();
				}
				else {
					maxRuns = matchAllData.getMatch().getInning().get(1).getTotalRuns();
				}
				if(maxRuns % 4 == 0) {
					maxRuns = maxRuns + 1;
				}
				while (maxRuns % 4 != 0) {     // 5 label in y-axis
					maxRuns = maxRuns + 1;    // keep incrementing till max runs is divisible by 5. maxRuns = 35
				}
				
				for(int k = 1; k <= 4; k++) {           // For Y-Axis Value 
					runsIncr = maxRuns / 4; // 35/5=7 -> Y axis will be plot like 6,12,18,24,30 & 36
					int txtIndex = 5 - k;
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$RunWorm$WormDataAll$Graph$Runs_Axis$RunsTexts$txt_" 
							+ txtIndex + "*GEOM*TEXT SET " + (runsIncr*k) + "\0", print_writers);
				}
				
				for(int l = 1; l <= 10; l++) {           // For Y-Axis Value
					
					switch (matchAllData.getSetup().getMatchType()) {
					case CricketUtil.ODI: case CricketUtil.OD:
						txtIndex1 = 5*l;
						break;
					default:
						txtIndex1 = 2*l;
						break;
					}
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$RunWorm$WormDataAll$Graph$Overs_Axis$Arrange$txt_" 
							+ (l+1) + "*GEOM*TEXT SET " + txtIndex1 + "\0", print_writers);
				}
				
				
				if(inn_count == 1) {
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$RunWorm$Worm$Worms$Team_" + inn_count
							+ "$" + inn_count + "$AnimWorm*SCRIPT*INSTANCE*strDataY SET "+ cumm_runs.replaceFirst("0,", "") +"\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$RunWorm$Worm$Worms$Team_" + inn_count
							+ "$" + inn_count + "$AnimWorm*SCRIPT*INSTANCE*iGraphHeightInRuns SET " + (maxRuns+10) +"\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$RunWorm$Worm$Worms$Team_" + inn_count
							+ "$" + inn_count + "$AnimWorm*SCRIPT*INSTANCE*iGraphWidthInOvers SET " + matchAllData.getSetup().getMaxOvers() +"\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$RunWorm$Worm$Worms$Team_" + inn_count
							+ "$" + inn_count + "$AnimWorm*SCRIPT*INSTANCE*iNumberOfOversForRandomData SET " + matchAllData.getSetup().getMaxOvers() +"\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$RunWorm$Worm$Worms$Team_" + inn_count
							+ "$" + inn_count + "$AnimWorm*SCRIPT*INSTANCE*iSetWorm INVOKE \0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$RunWorm$Worm$Worms$Team_" + inn_count
							+ "$" + inn_count + "$AnimWorm*SCRIPT*INSTANCE*strWicketsData SET " + cumm_wkts +"\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$RunWorm$Worm$Worms$Team_" + inn_count
							+ "$" + inn_count + "$AnimWorm*SCRIPT*INSTANCE*iSetWorm INVOKE \0", print_writers);
				}else {
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$RunWorm$Worm$Worms$Team_" + inn_count 
							+ "$Select_Colour*FUNCTION*Omo*vis_con SET 1\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$RunWorm$Worm$Worms$Team_" + inn_count
							+ "$" + inn_count + "$AnimWorm*SCRIPT*INSTANCE*strDataY SET "+ cumm_runs.replaceFirst("0,", "") +"\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$RunWorm$Worm$Worms$Team_" + inn_count
							+ "$" + inn_count + "$AnimWorm*SCRIPT*INSTANCE*iGraphHeightInRuns SET " + (maxRuns+10) +"\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$RunWorm$Worm$Worms$Team_" + inn_count 
							+ "$" + inn_count + "$AnimWorm*SCRIPT*INSTANCE*iGraphWidthInOvers SET " + matchAllData.getSetup().getMaxOvers() +"\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$RunWorm$Worm$Worms$Team_" + inn_count
							+ "$" + inn_count + "$AnimWorm*SCRIPT*INSTANCE*iNumberOfOversForRandomData SET " + matchAllData.getSetup().getMaxOvers() +"\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$RunWorm$Worm$Worms$Team_" + inn_count
							+ "$" + inn_count + "$AnimWorm*SCRIPT*INSTANCE*iSetWorm INVOKE \0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$RunWorm$Worm$Worms$Team_" + inn_count
							+ "$" + inn_count + "$AnimWorm*SCRIPT*INSTANCE*strWicketsData SET " + cumm_wkts +"\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$RunWorm$Worm$Worms$Team_" + inn_count
							+ "$" + inn_count + "$AnimWorm*SCRIPT*INSTANCE*iSetWorm INVOKE \0", print_writers);								
				}
			}
			
			break;
		case Constants.TRI_SERIES: case Constants.MT20:
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide 
					+ "$select_GraphicsType*FUNCTION*Omo*vis_con SET 7\0", print_writers);
			
			if(inning.getInningNumber() == 1) {
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$AllGraphics$Side" + WhichSide + "$Worms$Select_Team"
						+ "*FUNCTION*Omo*vis_con SET 0 \0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$AllGraphics$Side" + WhichSide + "$Worms$Graph$Worms$"
						+ "Team_2*ACTIVE SET 0\0", print_writers);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$AllGraphics$Side" + WhichSide + "$1_Teams$txt_Tittle"
						+ "*GEOM*TEXT SET " + inning.getBatting_team().getTeamName1() + " \0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$AllGraphics$Side" + WhichSide + "$1_Teams$txt_Score"
						+ "*GEOM*TEXT SET " + CricketFunctions.getTeamScore(inning, "-", false) + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$AllGraphics$Side" + WhichSide + "$1_Teams$txt_Overs"
						+ "*GEOM*TEXT SET " + CricketFunctions.OverBalls(inning.getTotalOvers(), inning.getTotalBalls()) + "\0", print_writers);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$AllGraphics$Side" + WhichSide + "$1_Teams$Base$img_Base1"
						+ "*TEXTURE*IMAGE SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_BASE2 : Constants.MT20_BASE2) + inning.getBatting_team().getTeamBadge() + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$AllGraphics$Side" + WhichSide + "$1_Teams$img_Text1"
						+ "*TEXTURE*IMAGE SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_TEXT2 : Constants.MT20_TEXT2)
						+ inning.getBatting_team().getTeamBadge() + "\0", print_writers);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$AllGraphics$Side" + WhichSide + "$Worms$Graph$Worms$"
						+ "Team_1$1$AnimWorm$img_Base1*TEXTURE*IMAGE SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_BASE2 : Constants.MT20_BASE2) + inning.getBatting_team().getTeamBadge() + "\0", print_writers);
				
				maxRuns = inning.getTotalRuns();
			}
			else if(inning.getInningNumber() == 2) {
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$AllGraphics$Side" + WhichSide + "$Worms$Select_Team"
						+ "*FUNCTION*Omo*vis_con SET 1 \0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$AllGraphics$Side" + WhichSide + "$Worms$Graph$Worms$"
						+ "Team_2*ACTIVE SET 1\0", print_writers);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$AllGraphics$Side" + WhichSide + "$2_Teams$Team1$txt_Tittle"
						+ "*GEOM*TEXT SET " + matchAllData.getMatch().getInning().get(0).getBatting_team().getTeamName1() + "\0", print_writers);	
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$AllGraphics$Side" + WhichSide + "$2_Teams$Team1$txt_Score"
						+ "*GEOM*TEXT SET " + CricketFunctions.getTeamScore(matchAllData.getMatch().getInning().get(0), "-", false) + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$AllGraphics$Side" + WhichSide + "$2_Teams$Team1$txt_Overs"
						+ "*GEOM*TEXT SET " + CricketFunctions.OverBalls(matchAllData.getMatch().getInning().get(0).getTotalOvers(), 
								matchAllData.getMatch().getInning().get(0).getTotalBalls()) + "\0", print_writers);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$AllGraphics$Side" + WhichSide + "$2_Teams$Team2$txt_Tittle"
						+ "*GEOM*TEXT SET " + inning.getBatting_team().getTeamName1()+"\0", print_writers);	
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$AllGraphics$Side" + WhichSide + "$2_Teams$Team2$txt_Score"
						+ "*GEOM*TEXT SET "+CricketFunctions.getTeamScore(inning, "-", false)+"\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$AllGraphics$Side" + WhichSide + "$2_Teams$Team2$txt_Overs"
						+ "*GEOM*TEXT SET "+CricketFunctions.OverBalls(inning.getTotalOvers(), inning.getTotalBalls())+"\0", print_writers);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$AllGraphics$Side" + WhichSide + "$2_Teams$Team1$Base$img_Base1"
						+ "*TEXTURE*IMAGE SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_BASE2 : Constants.MT20_BASE2) + matchAllData.getMatch().getInning().get(0).getBatting_team().getTeamBadge() + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$AllGraphics$Side" + WhichSide + "$2_Teams$Team1$img_Text1"
						+ "*TEXTURE*IMAGE SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_TEXT2 : Constants.MT20_TEXT2) + matchAllData.getMatch().getInning().get(0).getBatting_team().getTeamBadge() + "\0", print_writers);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$AllGraphics$Side" + WhichSide + "$2_Teams$Team2$Base$img_Base1"
						+ "*TEXTURE*IMAGE SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_BASE2 : Constants.MT20_BASE2) + matchAllData.getMatch().getInning().get(1).getBatting_team().getTeamBadge() + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$AllGraphics$Side" + WhichSide + "$2_Teams$Team2$img_Text1"
						+ "*TEXTURE*IMAGE SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_TEXT2 : Constants.MT20_TEXT2) + matchAllData.getMatch().getInning().get(1).getBatting_team().getTeamBadge() + "\0", print_writers);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$AllGraphics$Side" + WhichSide + "$Worms$Graph$Worms$Team_1$1$AnimWorm$"
						+ "img_Base1*TEXTURE*IMAGE SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_BASE2 : Constants.MT20_BASE2) + matchAllData.getMatch().getInning().get(0).getBatting_team().getTeamBadge() + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$AllGraphics$Side" + WhichSide + "$Worms$Graph$Worms$Team_2$2$AnimWorm$"
						+ "img_Base1*TEXTURE*IMAGE SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_BASE2 : Constants.MT20_BASE2) + matchAllData.getMatch().getInning().get(1).getBatting_team().getTeamBadge() + "\0", print_writers);
				
				if(matchAllData.getMatch().getInning().get(0).getTotalRuns() > matchAllData.getMatch().getInning().get(1).getTotalRuns()) {
					maxRuns = matchAllData.getMatch().getInning().get(0).getTotalRuns();
				}
				else {
					maxRuns = matchAllData.getMatch().getInning().get(1).getTotalRuns();
				}
			}
			
			for (int i = 0; i <= 20; i++) {
	            if (i % 2 == 0) {
	            	j=j+1;
	            	CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$AllGraphics$Side" + WhichSide + "$Worms$Overs_Axis"
							+ "$Overs_Data$txt_" + j + "*GEOM*TEXT SET " + i + "\0", print_writers);
	            }
	        }
			
			for(int inn_count = 1; inn_count <= inning.getInningNumber(); inn_count++) {
				List<String> overByOverRuns = new ArrayList<String>();
				List<String> overByOverwicket = new ArrayList<String>();
				
				overByOverRuns.clear();
				overByOverwicket.clear();
				//wicket_which_over = "";
				for(OverByOverData Over : CricketFunctions.getOverByOverData(matchAllData,inn_count ,"WORM" ,matchAllData.getEventFile().getEvents())) {
					overByOverRuns.add(String.valueOf(Over.getOverTotalRuns()));
				}
				String cumm_runs = String.valueOf(0) + "," + String.join(",", overByOverRuns); // Store Per Overs Runs
				
				if(inn_count == 2) {
					wkt_count=0;
				}
				for(OverByOverData Wicket : CricketFunctions.getOverByOverData(matchAllData,inn_count ,"WORM" ,matchAllData.getEventFile().getEvents())) {
					wkt_count = wkt_count + 1;
					
					if(Wicket.getOverTotalWickets() > 0) {
						for(int w=1; w <= Wicket.getOverTotalWickets(); w++) {
							overByOverwicket.add(String.valueOf(wkt_count-1));
						}
					}
				}
				String cumm_wkts = String.join(",", overByOverwicket); // Store Per Overs Wickets
				
				if(matchAllData.getMatch().getInning().get(0).getTotalRuns() > matchAllData.getMatch().getInning().get(1).getTotalRuns()) {
					maxRuns = matchAllData.getMatch().getInning().get(0).getTotalRuns();
				}
				else {
					maxRuns = matchAllData.getMatch().getInning().get(1).getTotalRuns();
				}
				if(maxRuns % 5 == 0) {
					maxRuns = maxRuns + 1;
				}
				while (maxRuns % 5 != 0) {     // 5 label in y-axis
					maxRuns = maxRuns + 1;    // keep incrementing till max runs is divisible by 5. maxRuns = 35
				}
				
				for(int k = 1; k <= 5; k++) {           // For Y-Axis Value 
					runsIncr = maxRuns / 5; // 35/5=7 -> Y axis will be plot like 6,12,18,24,30 & 36
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$AllGraphics$Side" + WhichSide + "$Worms$Runs_Axis"
							+ "$Runs_Data$txt_" + k + "*GEOM*TEXT SET " + (runsIncr*k) + "\0", print_writers);
				}
				
				if(inn_count == 1) {
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$AllGraphics$Side" + WhichSide + "$Worms$Worms$Team_" + inn_count
							+ "$" + inn_count + "$AnimWorm*SCRIPT*INSTANCE*strDataY SET "+ cumm_runs.replaceFirst("0,", "") +"\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$AllGraphics$Side" + WhichSide + "$Worms$Worms$Team_" + inn_count
							+ "$" + inn_count + "$AnimWorm*SCRIPT*INSTANCE*iGraphHeightInRuns SET " + (maxRuns+10) +"\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$AllGraphics$Side" + WhichSide + "$Worms$Worms$Team_" + inn_count
							+ "$" + inn_count + "$AnimWorm*SCRIPT*INSTANCE*iGraphWidthInOvers SET " + matchAllData.getSetup().getMaxOvers() +"\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$AllGraphics$Side" + WhichSide + "$Worms$Worms$Team_" + inn_count
							+ "$" + inn_count + "$AnimWorm*SCRIPT*INSTANCE*iNumberOfOversForRandomData SET " + matchAllData.getSetup().getMaxOvers() +"\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$AllGraphics$Side" + WhichSide + "$Worms$Worms$Team_" + inn_count
							+ "$" + inn_count + "$AnimWorm*SCRIPT*INSTANCE*iSetWorm INVOKE \0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$AllGraphics$Side" + WhichSide + "$Worms$Worms$Team_" + inn_count
							+ "$" + inn_count + "$AnimWorm*SCRIPT*INSTANCE*strWicketsData SET " + cumm_wkts +"\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$AllGraphics$Side" + WhichSide + "$Worms$Worms$Team_" + inn_count
							+ "$" + inn_count + "$AnimWorm*SCRIPT*INSTANCE*iSetWorm INVOKE \0", print_writers);
				}else {
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$AllGraphics$Side" + WhichSide + "$Worms$Worms$Team_" + inn_count 
							+ "$Select_Colour*FUNCTION*Omo*vis_con SET 1\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$AllGraphics$Side" + WhichSide + "$Worms$Worms$Team_" + inn_count
							+ "$" + inn_count + "$AnimWorm*SCRIPT*INSTANCE*strDataY SET "+ cumm_runs.replaceFirst("0,", "") +"\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$AllGraphics$Side" + WhichSide + "$Worms$Worms$Team_" + inn_count
							+ "$" + inn_count + "$AnimWorm*SCRIPT*INSTANCE*iGraphHeightInRuns SET " + (maxRuns+10) +"\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$AllGraphics$Side" + WhichSide + "$Worms$Worms$Team_" + inn_count 
							+ "$" + inn_count + "$AnimWorm*SCRIPT*INSTANCE*iGraphWidthInOvers SET " + matchAllData.getSetup().getMaxOvers() +"\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$AllGraphics$Side" + WhichSide + "$Worms$Worms$Team_" + inn_count
							+ "$" + inn_count + "$AnimWorm*SCRIPT*INSTANCE*iNumberOfOversForRandomData SET " + matchAllData.getSetup().getMaxOvers() +"\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$AllGraphics$Side" + WhichSide + "$Worms$Worms$Team_" + inn_count
							+ "$" + inn_count + "$AnimWorm*SCRIPT*INSTANCE*iSetWorm INVOKE \0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$AllGraphics$Side" + WhichSide + "$Worms$Worms$Team_" + inn_count
							+ "$" + inn_count + "$AnimWorm*SCRIPT*INSTANCE*strWicketsData SET " + cumm_wkts +"\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$AllGraphics$Side" + WhichSide + "$Worms$Worms$Team_" + inn_count
							+ "$" + inn_count + "$AnimWorm*SCRIPT*INSTANCE*iSetWorm INVOKE \0", print_writers);								
				}
			}
			break;
		}
		return Constants.OK;
	}
	public String PhotoBattingCardBody(List<PrintWriter> print_writers, int WhichSide, String whatToProcess, Configuration config,MatchAllData matchAllData, Inning inning) {
		
		rowId = 0;
		plyr_count = 0;
		Collections.sort(inning.getBattingCard());
		String which_role = "";
		
		switch(config.getBroadcaster()) {
		case Constants.AFG_SL_SERIES:
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$select_GraphicsType"
					+ "*FUNCTION*Omo*vis_con SET 11\0", print_writers);
			
			for(int iRow = 1; iRow <= inning.getBattingCard().size(); iRow++) {
				switch(iRow) {
				case 1: case 2: case 3: case 4: case 5: case 6:
					rowId = iRow;
					containerName = "$Top$";
					break;
				case 7: case 8: case 9: case 10: case 11:
					rowId = iRow - 6;
					containerName = "$Bottom$";
					break;
				}
				
				plyr_count = plyr_count + 1;
				player = PlayingXI.stream().filter(plyr -> plyr.getPlayerId() == inning.getBattingCard().get(plyr_count-1).getPlayerId()).findAny().orElse(null);
				
				if(player.getRole().equalsIgnoreCase("BATSMAN") || player.getRole().equalsIgnoreCase("BATTER") || player.getRole().equalsIgnoreCase("BAT/KEEPER")) {
					if(player.getBattingStyle().equalsIgnoreCase("LHB")) {
						which_role = "Batsman_Lefthand";
					}else {
						which_role = "Bat_Icon";
					}
				}else if(player.getRole().equalsIgnoreCase("BOWLER")) {
					if(player.getBowlingStyle() == null) {
						which_role = "Bowler";
					}else {
						switch(player.getBowlingStyle()) {
						case "RF": case "RFM": case "RMF": case "RM": case "RSM": case "LF": case "LFM": case "LMF": case "LM":
							which_role = "FastBowler";
							break;
						case "LSL": case "WSL": case "LCH": case "RLG": case "WSR": case "LSO":
							which_role = "SpinBowler";
							break;
						case "ROB":
							which_role = "Off_Spin";
							break;
						case "RLB":
							which_role = "Leg_Spin";
							break;	
						}
					}
				}else if(player.getRole().equalsIgnoreCase("ALL-ROUNDER")) {
					if(player.getBowlingStyle() == null) {
						if(player.getBattingStyle().equalsIgnoreCase("LHB")) {
							which_role = "Off_Spin_Allrounder_Left";
						}else {
							which_role = "FastBowlerAllrounder";
						}
					}else {
						switch(player.getBowlingStyle()) {
						case "RF": case "RFM": case "RMF": case "RM": case "RSM": case "LF": case "LFM": case "LMF": case "LM":
							if(player.getBattingStyle().equalsIgnoreCase("LHB")) {
								which_role = "Pace_BowlerAllrounerLeftHand";
							}else {
								which_role = "FastBowlerAllrounder";
							}
							break;
						case "ROB": case "RLB": case "LSL": case "WSL": case "LCH": case "RLG": case "WSR": case "LSO":
							if(player.getBattingStyle().equalsIgnoreCase("LHB")) {
								which_role = "Off_Spin_Allrounder_Left";
							}else {
								which_role = "Off_Spin_Bat";
							}
							break;
						}
					}
				}
				
				if(player.getCaptainWicketKeeper() != null && !player.getCaptainWicketKeeper().isEmpty()) {
					if(player.getCaptainWicketKeeper().equalsIgnoreCase(CricketUtil.CAPTAIN)) {
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$ImageBattingCard" + containerName 
								+ rowId + "$Icons$Captain$select_Captain*FUNCTION*Omo*vis_con SET 1\0", print_writers);
					}else if(player.getCaptainWicketKeeper().equalsIgnoreCase("CAPTAIN_WICKET_KEEPER")) {
						which_role = "Keeper";
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$ImageBattingCard" + containerName 
								+ rowId + "$Icons$Captain$select_Captain*FUNCTION*Omo*vis_con SET 1\0", print_writers);
						
					}else if(player.getCaptainWicketKeeper().equalsIgnoreCase(CricketUtil.WICKET_KEEPER)) {
						which_role = "Keeper";
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$ImageBattingCard" + containerName 
								+ rowId + "$Icons$Captain$select_Captain*FUNCTION*Omo*vis_con SET 0\0", print_writers);
					}else {
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$ImageBattingCard" + containerName 
								+ rowId + "$Icons$Captain$select_Captain*FUNCTION*Omo*vis_con SET 0\0", print_writers);
					}
				}else {
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$ImageBattingCard" + containerName 
							+ rowId + "$Icons$Captain$select_Captain*FUNCTION*Omo*vis_con SET 0\0", print_writers);
				}
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$ImageBattingCard" + containerName 
						+ rowId + "$Icons$Role$img_Role*TEXTURE*IMAGE SET " + Constants.AFG_SL_SERIES_ICONS + which_role + "\0", print_writers);
				
				if(config.getPrimaryIpAddress().equalsIgnoreCase(Constants.LOCALHOST)) {
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$ImageBattingCard" + containerName 
							+ rowId + "$img_Player*TEXTURE*IMAGE SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_LOCAL_PHOTO_PATH : Constants.MT20_LOCAL_PHOTO_PATH) + inning.getBatting_team().getTeamName4() + "\\\\" 
							+ Constants.CENTER + "\\\\" + inning.getBattingCard().get(iRow-1).getPlayer().getPhoto() + CricketUtil.PNG_EXTENSION + "\0", print_writers);
				}else {
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$ImageBattingCard" + containerName 
							+ rowId + "$img_Player*TEXTURE*IMAGE SET \\\\" + config.getPrimaryIpAddress() + "\\" + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_PHOTO_PATH : Constants.MT20_PHOTO_PATH) 
							+ inning.getBatting_team().getTeamName4() + "\\\\" + Constants.CENTER + "\\\\" + inning.getBattingCard().get(iRow-1).getPlayer().getPhoto() 
							+ CricketUtil.PNG_EXTENSION + "\0", print_writers);
				}
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$ImageBattingCard" + containerName
						+ rowId + "$Arrow$select_Arrow*FUNCTION*Omo*vis_con SET 0\0", print_writers);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$ImageBattingCard" + containerName 
						+ rowId + "$NameGrp$txt_Name*GEOM*TEXT SET " + inning.getBattingCard().get(iRow-1).getPlayer().getTicker_name() + "\0", print_writers);
				
				switch (inning.getBattingCard().get(iRow-1).getStatus().toUpperCase()) {
				case CricketUtil.STILL_TO_BAT:
					if(inning.getBattingCard().get(iRow-1).getHowOut() == null) {
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$ImageBattingCard" + containerName 
								+ rowId + "$Data$select_DataStyle*FUNCTION*Omo*vis_con SET 0\0", print_writers);
						
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$ImageBattingCard" + containerName 
								+ rowId + "$Stats$txt_StatHead*GEOM*TEXT SET " + "IN AT " + "\0", print_writers);
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$ImageBattingCard" + containerName 
								+ rowId + "$Stats$txt_StatValue*GEOM*TEXT SET " + iRow + "\0", print_writers);
					} else {
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$ImageBattingCard" + containerName 
								+ rowId + "$Data$select_DataStyle*FUNCTION*Omo*vis_con SET 1\0", print_writers);
					
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$ImageBattingCard" + containerName 
								+ rowId + "$Out$Out$txt_Runs*GEOM*TEXT SET " + inning.getBattingCard().get(iRow-1).getRuns() + "\0", print_writers);
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$ImageBattingCard" + containerName 
								+ rowId + "$Out$Out$txt_Balls*GEOM*TEXT SET " + inning.getBattingCard().get(iRow-1).getBalls() + "\0", print_writers);
					}
					
					break;
				default:
					switch (inning.getBattingCard().get(iRow-1).getStatus().toUpperCase()) {
					case CricketUtil.OUT:
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$ImageBattingCard" + containerName 
								+ rowId + "$Data$select_DataStyle*FUNCTION*Omo*vis_con SET 1\0", print_writers);
						containerName_2 = "$Out$Out";
						break;
					case CricketUtil.NOT_OUT:
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$ImageBattingCard" + containerName 
								+ rowId + "$Data$select_DataStyle*FUNCTION*Omo*vis_con SET 2\0", print_writers);
						containerName_2 = "$NotOut";
						break;
					}
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$ImageBattingCard" + containerName 
							+ rowId + containerName_2 + "$txt_Runs*GEOM*TEXT SET " + inning.getBattingCard().get(iRow-1).getRuns() + "\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$ImageBattingCard" + containerName 
							+ rowId + containerName_2 + "$txt_Balls*GEOM*TEXT SET " + inning.getBattingCard().get(iRow-1).getBalls() + "\0", print_writers);
					break;
				}
			}
			break;
		case Constants.TRI_SERIES: case Constants.MT20:
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide 
					+ "$select_GraphicsType*FUNCTION*Omo*vis_con SET 11\0", print_writers);
			
			for(int iRow = 1; iRow <= inning.getBattingCard().size(); iRow++) {
				switch(iRow) {
				case 1: case 2: case 3: case 4: case 5: case 6:
					rowId = iRow;
					containerName = "$Top$";
					break;
				case 7: case 8: case 9: case 10: case 11:
					rowId = iRow - 6;
					containerName = "$Bottom$";
					break;
				}
				
				plyr_count = plyr_count + 1;
				player = PlayingXI.stream().filter(plyr -> plyr.getPlayerId() == inning.getBattingCard().get(plyr_count-1).getPlayerId()).findAny().orElse(null);
				
				if(player != null) {
					if(player.getCaptainWicketKeeper() != null && !player.getCaptainWicketKeeper().isEmpty()){
						if(player.getCaptainWicketKeeper().equalsIgnoreCase(CricketUtil.CAPTAIN) || player.getCaptainWicketKeeper().equalsIgnoreCase("CAPTAIN_WICKET_KEEPER")){
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$AllGraphics$Side" + WhichSide + "$ImageBatting" + containerName 
									 + rowId + "$Name$txt_Captain*GEOM*TEXT SET (C)\0", print_writers);
						}else if(player.getCaptainWicketKeeper().equalsIgnoreCase(CricketUtil.WICKET_KEEPER)) {
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$AllGraphics$Side" + WhichSide + "$ImageBatting" + containerName 
									 + rowId + "$Name$txt_Captain*GEOM*TEXT SET (WK)\0", print_writers);
						}else {
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$AllGraphics$Side" + WhichSide + "$ImageBatting" + containerName 
									 + rowId + "$Name$txt_Captain*GEOM*TEXT SET \0", print_writers);
						}
					}else {
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$AllGraphics$Side" + WhichSide + "$ImageBatting" + containerName 
								 + rowId + "$Name$txt_Captain*GEOM*TEXT SET \0", print_writers);
					}
				}else {
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$AllGraphics$Side" + WhichSide + "$ImageBatting" + containerName 
							 + rowId + "$Name$txt_Captain*GEOM*TEXT SET \0", print_writers);
				}
				
				
				if(config.getPrimaryIpAddress().equalsIgnoreCase(Constants.LOCALHOST)) {
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$AllGraphics$Side" + WhichSide + "$ImageBatting" + containerName 
							+ rowId + "$img_Play3r*TEXTURE*IMAGE SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_LOCAL_PHOTO_PATH : Constants.MT20_LOCAL_PHOTO_PATH) + inning.getBatting_team().getTeamName4() + "\\\\" 
							+ Constants.CENTER + "\\\\" + inning.getBattingCard().get(iRow-1).getPlayer().getPhoto() + CricketUtil.PNG_EXTENSION + "\0", print_writers);
				}else {
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$AllGraphics$Side" + WhichSide + "$ImageBatting" + containerName 
							+ rowId + "$img_Play3r*TEXTURE*IMAGE SET \\\\" + config.getPrimaryIpAddress() + "\\" + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_PHOTO_PATH : Constants.MT20_PHOTO_PATH) 
							+ inning.getBatting_team().getTeamName4() + "\\\\" + Constants.CENTER + "\\\\" + inning.getBattingCard().get(iRow-1).getPlayer().getPhoto() 
							+ CricketUtil.PNG_EXTENSION + "\0", print_writers);
				}
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$AllGraphics$Side" + WhichSide + "$ImageBatting" + containerName 
						+ rowId + "$Name$txt_PlayerName*GEOM*TEXT SET " + inning.getBattingCard().get(iRow-1).getPlayer().getTicker_name() + "\0", print_writers);
				
				switch (inning.getBattingCard().get(iRow-1).getStatus().toUpperCase()) {
				case CricketUtil.STILL_TO_BAT:
					if(inning.getBattingCard().get(iRow-1).getHowOut() == null) {
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$AllGraphics$Side" + WhichSide + "$ImageBatting" + containerName 
								+ rowId + "$Data$select_DataStyle*FUNCTION*Omo*vis_con SET 0\0", print_writers);
						
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$AllGraphics$Side" + WhichSide + "$ImageBatting" + containerName 
								+ rowId + "$Stats$txt_StatHead*GEOM*TEXT SET " + "IN AT " + "\0", print_writers);
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$AllGraphics$Side" + WhichSide + "$ImageBatting" + containerName 
								+ rowId + "$Stats$txt_StatValue*GEOM*TEXT SET " + iRow + "\0", print_writers);
					} else {
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$AllGraphics$Side" + WhichSide + "$ImageBatting" + containerName 
								+ rowId + "$Data$select_DataStyle*FUNCTION*Omo*vis_con SET 1\0", print_writers);
					
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$AllGraphics$Side" + WhichSide + "$ImageBatting" + containerName 
								+ rowId + "$Out$Out$txt_Runs*GEOM*TEXT SET " + inning.getBattingCard().get(iRow-1).getRuns() + "\0", print_writers);
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$AllGraphics$Side" + WhichSide + "$ImageBatting" + containerName 
								+ rowId + "$Out$Out$txt_Balls*GEOM*TEXT SET " + inning.getBattingCard().get(iRow-1).getBalls() + "\0", print_writers);
					}
					
					break;
				default:
					switch (inning.getBattingCard().get(iRow-1).getStatus().toUpperCase()) {
					case CricketUtil.OUT:
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$AllGraphics$Side" + WhichSide + "$ImageBatting" + containerName 
								+ rowId + "$Data$select_DataStyle*FUNCTION*Omo*vis_con SET 1\0", print_writers);
						containerName_2 = "$Out$Out";
						break;
					case CricketUtil.NOT_OUT:
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$AllGraphics$Side" + WhichSide + "$ImageBatting" + containerName 
								+ rowId + "$Data$select_DataStyle*FUNCTION*Omo*vis_con SET 2\0", print_writers);
						containerName_2 = "$NotOut";
						break;
					}
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$AllGraphics$Side" + WhichSide + "$ImageBatting" + containerName 
							+ rowId + containerName_2 + "$txt_Runs*GEOM*TEXT SET " + inning.getBattingCard().get(iRow-1).getRuns() + 
							(inning.getBattingCard().get(iRow-1).getStatus().equalsIgnoreCase(CricketUtil.NOT_OUT) ? "*" : "") + "\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$AllGraphics$Side" + WhichSide + "$ImageBatting" + containerName 
							+ rowId + containerName_2 + "$txt_Balls*GEOM*TEXT SET " + inning.getBattingCard().get(iRow-1).getBalls() + "\0", print_writers);
					break;
				}
			}
			break;
		}
		return Constants.OK;
	}
	public String ScoreAndBowlingCardBody(List<PrintWriter> print_writers, int WhichSide, Configuration config, MatchAllData matchAllData,Inning inning) throws Exception
	{
		switch (config.getBroadcaster()) {
		case Constants.AFG_SL_SERIES:
			rowId = 0;
			
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$select_GraphicsType"
					+ "*FUNCTION*Omo*vis_con SET 2\0", print_writers);
			Collections.sort(inning.getBattingCard());
			for (BattingCard bc : inning.getBattingCard()) {
				rowId++;
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$Split_Card$Left"
						+ "*FUNCTION*Grid*num_row SET " + rowId + "\0", print_writers);
				if(rowId == 12) {
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$Split_Card$Left"
							+ "*FUNCTION*Grid*row_offset SET " + "48" + "\0", print_writers);
				}else if(rowId == 13) {
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$Split_Card$Left"
							+ "*FUNCTION*Grid*row_offset SET " + "52" + "\0", print_writers);
				}else if(rowId == 11) {
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$Split_Card$Left"
							+ "*FUNCTION*Grid*row_offset SET " + "55" + "\0", print_writers);
				}
				
				switch (bc.getStatus().toUpperCase()) {
				case CricketUtil.STILL_TO_BAT:
					
					if(bc.getHowOut() == null) {
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$Split_Card$Left$Grp" 
								+ rowId + "$Dehighlight$select_DataType*FUNCTION*Omo*vis_con SET 0\0", print_writers);
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$Split_Card$Left$Grp" 
								+ rowId + "$Dehighlight$StillToBat$txt_BatterName*GEOM*TEXT SET " + bc.getPlayer().getTicker_name() + "\0", print_writers);

						if(!matchAllData.getMatch().getSourceOfThisMatchData().equalsIgnoreCase("WEBSITE")) {
							if(!CricketFunctions.checkBatAndBallImpactInOutPlayer(matchAllData.getEventFile().getEvents(), bc.getPlayerId()).isEmpty()) {
								switch(CricketFunctions.checkBatAndBallImpactInOutPlayer(matchAllData.getEventFile().getEvents(), bc.getPlayerId())) {
								case "CON_OUT":
									if(bc.getBalls() == 0) {
										rowId = rowId - 1;
										this.numberOfRows = rowId;
									}
									break;
								}
							}
						}
						
					}else {
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$Split_Card$Left$Grp" 
								+ rowId + "$Dehighlight$select_DataType*FUNCTION*Omo*vis_con SET 1\0", print_writers);
						
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$Split_Card$Left$Grp" 
								+ rowId + "$Dehighlight$Out$txt_BatterName*GEOM*TEXT SET " + bc.getPlayer().getTicker_name() + "\0", print_writers);
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$Split_Card$Left$Grp" 
								+ rowId + "$Dehighlight$Out$txt_Scores*GEOM*TEXT SET " + bc.getRuns() + "\0", print_writers);
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$Split_Card$Left$Grp" 
								+ rowId + "$Dehighlight$Out$txt_Balls*GEOM*TEXT SET " + bc.getBalls() + "\0", print_writers);
					}
					break;
				default:
					switch (bc.getStatus().toUpperCase()) {
					case CricketUtil.OUT:
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$Split_Card$Left$Grp" 
								+ rowId + "$Dehighlight$select_DataType*FUNCTION*Omo*vis_con SET 1\0", print_writers);
						
						containerName = "$Dehighlight$Out";
						break;
					case CricketUtil.NOT_OUT:
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$Split_Card$Left$Grp" 
								+ rowId + "$Dehighlight$select_DataType*FUNCTION*Omo*vis_con SET 2\0", print_writers);
						
						containerName = "$Dehighlight$NotOut";
						break;
					}
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$Split_Card$Left$Grp" 
							+ rowId + containerName + "$txt_BatterName*GEOM*TEXT SET " + bc.getPlayer().getTicker_name() + "\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$Split_Card$Left$Grp" 
							+ rowId + containerName + "$txt_Scores*GEOM*TEXT SET " + bc.getRuns() + "\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$Split_Card$Left$Grp" 
							+ rowId + containerName + "$txt_Balls*GEOM*TEXT SET " + bc.getBalls() + "\0", print_writers);
				}
			}
			
			rowId1 = 0;
			
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$Split_Card$Right$select_DataType"
					+ "*FUNCTION*Omo*vis_con SET 1\0", print_writers);
			
			for(int j=1;j<=13;j++) {
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$Split_Card$Right$SplitBowling$Grp" 
						+ j + "$select_DataType*FUNCTION*Omo*vis_con SET 0\0", print_writers);
			}
			
			Collections.sort(inning.getBowlingCard());
			if(inning.getBowlingCard() != null && inning.getBowlingCard().size() > 0) {
				for(int iRow = 1; iRow <= inning.getBowlingCard().size(); iRow++) {
					if(inning.getBowlingCard().get(iRow-1).getRuns() > 0 || ((inning.getBowlingCard().get(iRow-1).getOvers()*6)
							+inning.getBowlingCard().get(iRow-1).getBalls()) > 0) {
						rowId1 = rowId1 + 1;
						if(CricketFunctions.GetTargetData(matchAllData).getRemaningRuns() == 0 || matchAllData.getMatch().getInning().get(1).getTotalWickets() >= 10 || 
								CricketFunctions.GetTargetData(matchAllData).getRemaningBall()  == 0) {
							omo = 1;
							containerName = "$Dehighlight";
						}else {
							if(inning.getInningStatus().equalsIgnoreCase(CricketUtil.PAUSE)) {
								omo = 1;
								containerName = "$Dehighlight";
							}else {
								switch (inning.getBowlingCard().get(iRow-1).getStatus().toUpperCase()) {
								case (CricketUtil.OTHER + CricketUtil.BOWLER):
									omo = 1;
									containerName = "$Dehighlight";
									break;
								case (CricketUtil.LAST + CricketUtil.BOWLER):
									omo = 1;
									containerName = "$Dehighlight";
									break;
								case (CricketUtil.CURRENT + CricketUtil.BOWLER):
									omo = 2;
									containerName = "$Highlight";
									break;
								}
							}
						}
						
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$Split_Card$Right$SplitBowling$Grp" 
								+ rowId1 + "$select_DataType*FUNCTION*Omo*vis_con SET " + omo + "\0", print_writers);
						
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$Split_Card$Right$SplitBowling$Grp" + rowId1 + containerName 
								+ "$txt_BowlerName*GEOM*TEXT SET " + inning.getBowlingCard().get(iRow-1).getPlayer().getTicker_name() + "\0", print_writers);
						
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$Split_Card$Right$SplitBowling$Grp" + rowId1 + containerName 
								+ "$txt_Overs*GEOM*TEXT SET " + CricketFunctions.OverBalls(inning.getBowlingCard().get(iRow-1).getOvers(), inning.getBowlingCard().get(iRow-1).getBalls()) + "\0", print_writers);
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$Split_Card$Right$SplitBowling$Grp" + rowId1 + containerName 
								+ "$txt_Runs*GEOM*TEXT SET " + inning.getBowlingCard().get(iRow-1).getRuns() + "\0", print_writers);
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$Split_Card$Right$SplitBowling$Grp" + rowId1 + containerName 
								+ "$txt_Wickets*GEOM*TEXT SET " + inning.getBowlingCard().get(iRow-1).getWickets() + "\0", print_writers);
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$Split_Card$Right$SplitBowling$Grp" + rowId1 + containerName 
								+ "$txt_Economy*GEOM*TEXT SET " + (inning.getBowlingCard().get(iRow-1).getEconomyRate() != null ? inning.getBowlingCard().get(iRow-1).getEconomyRate() : "-") + "\0", print_writers);
						
						switch (matchAllData.getSetup().getMatchType()) {
						case CricketUtil.ODI: case CricketUtil.TEST: case CricketUtil.OD:
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$Split_Card$Right$SplitBowling$Grp" + rowId1 + containerName 
									+ "$txt_Maidens*GEOM*TEXT SET " + inning.getBowlingCard().get(iRow-1).getMaidens() + "\0", print_writers);
							break;
						default:
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$Split_Card$Right$SplitBowling$Grp" + rowId1 + containerName 
									+ "$txt_Maidens*GEOM*TEXT SET " + inning.getBowlingCard().get(iRow-1).getDots() + "\0", print_writers);
							break;
						}
					}
				}
			}
			if(inning.getBowlingCard().size() <= 9) {
				if(inning.getFallsOfWickets() != null) {
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$Split_Card$Right$SplitBowling$Grp11$select_DataType"
							+ "*FUNCTION*Omo*vis_con SET 3\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$Split_Card$Right$SplitBowling$Grp12$select_DataType"
							+ "*FUNCTION*Omo*vis_con SET 4\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$Split_Card$Right$SplitBowling$Grp13$select_DataType"
							+ "*FUNCTION*Omo*vis_con SET 5\0", print_writers);
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$Split_Card$Right$SplitBowling$Grp11$txt_FOW"
							+ "*GEOM*TEXT SET  FALL OF WICKETS\0", print_writers);
					for(int i =1; i<= 10; i++) {
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$Split_Card$Right$SplitBowling$Grp12$Wickets$WicketsData$fig_"
								+ i + "*GEOM*TEXT SET " + CricketFunctions.ordinal(i) + "\0", print_writers);
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$Split_Card$Right$SplitBowling$Grp13$Score$ScoreData$fig_"
								+ i + "*GEOM*TEXT SET \0", print_writers);
					}
					for(FallOfWicket fow : inning.getFallsOfWickets()) {
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$Split_Card$Right$SplitBowling$Grp13$Score$ScoreData$fig_"
								+ fow.getFowNumber() + "*GEOM*TEXT SET " + fow.getFowRuns() + "\0", print_writers);
					}
				}
			}
			
			break;
		case Constants.TRI_SERIES: case Constants.MT20:
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide 
					+ "$select_GraphicsType*FUNCTION*Omo*vis_con SET 0\0", print_writers);
			Collections.sort(inning.getBattingCard());
			for(int i=1; i<=13; i++) {
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide + "$BattingCard"+
						"$BatterGrp$" + i + "*ACTIVE SET 0\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide + "$BattingCard"+
						"$BowlerGrp$" + i + "$select_RowType*FUNCTION*Omo*vis_con SET 0 \0", print_writers);
			}
			rowId = 0;
			
			Collections.sort(inning.getBattingCard());
			for (BattingCard bc : inning.getBattingCard()) {
				rowId++;
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide 
						+ "$BattingCard$BatterGrp$" + rowId + "*ACTIVE SET 1\0", print_writers);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide 
						+ "$BattingCard$BatterGrp$Rows*FUNCTION*Grid*num_row SET " + rowId + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide 
						+ "$BattingCard$DismissalGrp$Rows*FUNCTION*Grid*num_row SET " + rowId + "\0", print_writers);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide 
						+ "$BattingCard$BatterGrp$Rows$" + rowId + "*ACTIVE SET 1\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide 
						+ "$BattingCard$DismissalGrp$Rows$" + rowId + "*ACTIVE SET 1\0", print_writers);
				
				switch (bc.getStatus().toUpperCase()) {
				case CricketUtil.STILL_TO_BAT:
					
					if(bc.getHowOut() == null) {
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide 
								+ "$BattingCard$BatterGrp$" + rowId + "$select_RowType*FUNCTION*Omo*vis_con SET 0\0", print_writers);
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide 
								+ "$BattingCard$DismissalGrp$" + rowId + "$select_RowType*FUNCTION*Omo*vis_con SET 0\0", print_writers);
						
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide 
								+ "$BattingCard$BatterGrp$" + rowId + "$Still_To_Bat$txt_BatterName*GEOM*TEXT SET " + bc.getPlayer().getTicker_name() + "\0", print_writers);
						
						if(!CricketFunctions.checkBatAndBallImpactInOutPlayer(matchAllData.getEventFile().getEvents(), bc.getPlayerId()).isEmpty()) {
							switch(CricketFunctions.checkBatAndBallImpactInOutPlayer(matchAllData.getEventFile().getEvents(), bc.getPlayerId())) {
							
							case "CON_OUT":
								if(bc.getBalls() == 0) {
									rowId = rowId - 1;
									this.numberOfRows = rowId;
								}
								break;
							}
						}
					}else if(bc.getHowOut().equalsIgnoreCase(CricketUtil.RETIRED_HURT)) {
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide 
								+ "$BattingCard$BatterGrp$" + rowId + "$select_RowType*FUNCTION*Omo*vis_con SET 1\0", print_writers);
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide 
								+ "$BattingCard$DismissalGrp$" + rowId + "$select_RowType*FUNCTION*Omo*vis_con SET 1\0", print_writers);
						
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide 
								+ "$BattingCard$BatterGrp$" + rowId + "$Out$Out$txt_BatterName*GEOM*TEXT SET " + bc.getPlayer().getTicker_name() + "\0", print_writers);
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide 
								+ "$BattingCard$BatterGrp$" + rowId + "$Out$Out$txt_Runs*GEOM*TEXT SET " + bc.getRuns() + "\0", print_writers);
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide 
								+ "$BattingCard$BatterGrp$" + rowId + "$Out$Out$txt_Balls*GEOM*TEXT SET " + bc.getBalls() + "\0", print_writers);
						
					}else {
						
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide 
								+ "$BattingCard$BatterGrp$" + rowId + "$select_RowType*FUNCTION*Omo*vis_con SET 1\0", print_writers);
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide 
								+ "$BattingCard$DismissalGrp$" + rowId + "$select_RowType*FUNCTION*Omo*vis_con SET 1\0", print_writers);
						
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide 
								+ "$BattingCard$BatterGrp$" + rowId + "$Out$Out$txt_BatterName*GEOM*TEXT SET " + bc.getPlayer().getTicker_name() + "\0", print_writers);
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide 
								+ "$BattingCard$BatterGrp$" + rowId + "$Out$Out$txt_Runs*GEOM*TEXT SET " + bc.getRuns() + "\0", print_writers);
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide 
								+ "$BattingCard$BatterGrp$" + rowId + "$Out$Out$txt_Balls*GEOM*TEXT SET " + bc.getBalls() + "\0", print_writers);
					}
					break;
				default:
					switch (bc.getStatus().toUpperCase()) {
					case CricketUtil.OUT:
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide 
								+ "$BattingCard$BatterGrp$" + rowId + "$select_RowType*FUNCTION*Omo*vis_con SET 1\0", print_writers);
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide 
								+ "$BattingCard$DismissalGrp$" + rowId + "$select_RowType*FUNCTION*Omo*vis_con SET 1\0", print_writers);
						containerName = "$Out$Out";
						break;
					case CricketUtil.NOT_OUT:
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide 
								+ "$BattingCard$BatterGrp$" + rowId + "$select_RowType*FUNCTION*Omo*vis_con SET 2\0", print_writers);
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide 
								+ "$BattingCard$DismissalGrp$" + rowId + "$select_RowType*FUNCTION*Omo*vis_con SET 2\0", print_writers);
						
						containerName = "$NotOut";
						break;
					}
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide 
							+ "$BattingCard$BatterGrp$" + rowId + containerName + "$txt_BatterName*GEOM*TEXT SET " + bc.getPlayer().getTicker_name() + "\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide 
							+ "$BattingCard$BatterGrp$" + rowId + containerName + "$txt_Runs*GEOM*TEXT SET " + bc.getRuns() + "\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide 
							+ "$BattingCard$BatterGrp$" + rowId + containerName + "$txt_Balls*GEOM*TEXT SET " + bc.getBalls() + "\0", print_writers);
				}
			}
			
			rowId1 = 0;
			Collections.sort(inning.getBowlingCard());
			if(inning.getBowlingCard() != null && inning.getBowlingCard().size() > 0) {
				for(int iRow = 1; iRow <= inning.getBowlingCard().size(); iRow++) {
					if(inning.getBowlingCard().get(iRow-1).getRuns() > 0 || ((inning.getBowlingCard().get(iRow-1).getOvers()*6)
							+inning.getBowlingCard().get(iRow-1).getBalls()) > 0) {
						
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide + "$BattingCard"+
								"$BowlerGrp$" + iRow + "$select_RowType*FUNCTION*Omo*vis_con SET 1\0", print_writers);
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide + "$BattingCard"+
								"$BowlerGrp$" + iRow + "$Players$txt_BowlerName*GEOM*TEXT SET " + inning.getBowlingCard().get(iRow-1).getPlayer().getTicker_name() 
								+ "\0", print_writers);
						
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide + "$BattingCard"+
								"$BowlerGrp$" + iRow + "$Players$txt_Figures*GEOM*TEXT SET " + inning.getBowlingCard().get(iRow-1).getWickets() + "-" + 
								inning.getBowlingCard().get(iRow-1).getRuns() + "\0", print_writers);
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide + "$BattingCard"+
								"$BowlerGrp$" + iRow + "$Players$txt_Overs*GEOM*TEXT SET " + CricketFunctions.OverBalls(inning.getBowlingCard().get(iRow-1).getOvers(), 
										inning.getBowlingCard().get(iRow-1).getBalls()) + "\0", print_writers);
					}
				}
			}
		
			if(inning.getBowlingCard().size() <= 9) {
				
				if(inning.getFallsOfWickets() != null) {
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide + "$BattingCard"+
							"$BowlerGrp$10$select_RowType*FUNCTION*Omo*vis_con SET 2\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide + "$BattingCard"+
							"$BowlerGrp$11$select_RowType*FUNCTION*Omo*vis_con SET 3\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide + "$BattingCard"+
							"$BowlerGrp$12$select_RowType*FUNCTION*Omo*vis_con SET 4\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide + "$BattingCard"+
							"$BowlerGrp$10$FOW_Title$txt_FOW*GEOM*TEXT SET  FALL OF WICKETS\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide + "$BattingCard"+
							"$BowlerGrp$12$Score$Runs*FUNCTION*Grid*num_col SET " + inning.getFallsOfWickets().size() + "\0", print_writers);
					
					for(int i=1;i<=10;i++) {
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide + "$BattingCard"+
								"$BowlerGrp$11$Wickets$Wickets$fig_" + i + "*GEOM*TEXT SET " + i + "\0", print_writers);
					}
					
					for(FallOfWicket fow : inning.getFallsOfWickets()) {
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide + "$BattingCard"+
								"$BowlerGrp$12$Score$Runs$fig_" + fow.getFowNumber() + "*GEOM*TEXT SET " + fow.getFowRuns() + "\0", print_writers);
					}
				}
			}
			this.numberOfRows = rowId;
			break;
		}
		return Constants.OK;
	}
	public String ScoreCardContributionBody(List<PrintWriter> print_writers, int WhichSide, Configuration config, MatchAllData matchAllData,Inning inning) throws Exception
	{
		double lngth=0,percent=0;
		List<Integer> OrderRuns = new ArrayList<>();
		
		List<Double> scaledPercent = new ArrayList<>();
		List<Integer> roundedPercent = new ArrayList<>();
		
		switch (config.getBroadcaster()) {
		case Constants.AFG_SL_SERIES:
			rowId = 0;
			
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$select_GraphicsType"
					+ "*FUNCTION*Omo*vis_con SET 2\0", print_writers);
			Collections.sort(inning.getBattingCard());
			for (BattingCard bc : inning.getBattingCard()) {
				rowId++;
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$Split_Card$Left"
						+ "*FUNCTION*Grid*num_row SET " + rowId + "\0", print_writers);
				if(rowId == 12) {
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$Split_Card$Left"
							+ "*FUNCTION*Grid*row_offset SET " + "48" + "\0", print_writers);
				}else if(rowId == 13) {
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$Split_Card$Left"
							+ "*FUNCTION*Grid*row_offset SET " + "52" + "\0", print_writers);
				}else if(rowId == 11) {
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$Split_Card$Left"
							+ "*FUNCTION*Grid*row_offset SET " + "55" + "\0", print_writers);
				}
				
				switch (bc.getStatus().toUpperCase()) {
				case CricketUtil.STILL_TO_BAT:
					
					if(bc.getHowOut() == null) {
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$Split_Card$Left$Grp" 
								+ rowId + "$Dehighlight$select_DataType*FUNCTION*Omo*vis_con SET 0\0", print_writers);
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$Split_Card$Left$Grp" 
								+ rowId + "$Dehighlight$StillToBat$txt_BatterName*GEOM*TEXT SET " + bc.getPlayer().getTicker_name() + "\0", print_writers);

						if(!matchAllData.getMatch().getSourceOfThisMatchData().equalsIgnoreCase("WEBSITE")) {
							if(!CricketFunctions.checkBatAndBallImpactInOutPlayer(matchAllData.getEventFile().getEvents(), bc.getPlayerId()).isEmpty()) {
								switch(CricketFunctions.checkBatAndBallImpactInOutPlayer(matchAllData.getEventFile().getEvents(), bc.getPlayerId())) {
								case "CON_OUT":
									if(bc.getBalls() == 0) {
										rowId = rowId - 1;
										this.numberOfRows = rowId;
									}
									break;
								}
							}
						}
						
					}else {
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$Split_Card$Left$Grp" 
								+ rowId + "$Dehighlight$select_DataType*FUNCTION*Omo*vis_con SET 1\0", print_writers);
						
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$Split_Card$Left$Grp" 
								+ rowId + "$Dehighlight$Out$txt_BatterName*GEOM*TEXT SET " + bc.getPlayer().getTicker_name() + "\0", print_writers);
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$Split_Card$Left$Grp" 
								+ rowId + "$Dehighlight$Out$txt_Scores*GEOM*TEXT SET " + bc.getRuns() + "\0", print_writers);
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$Split_Card$Left$Grp" 
								+ rowId + "$Dehighlight$Out$txt_Balls*GEOM*TEXT SET " + bc.getBalls() + "\0", print_writers);
					}
					break;
				default:
					switch (bc.getStatus().toUpperCase()) {
					case CricketUtil.OUT:
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$Split_Card$Left$Grp" 
								+ rowId + "$Dehighlight$select_DataType*FUNCTION*Omo*vis_con SET 1\0", print_writers);
						
						containerName = "$Dehighlight$Out";
						break;
					case CricketUtil.NOT_OUT:
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$Split_Card$Left$Grp" 
								+ rowId + "$Dehighlight$select_DataType*FUNCTION*Omo*vis_con SET 2\0", print_writers);
						
						containerName = "$Dehighlight$NotOut";
						break;
					}
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$Split_Card$Left$Grp" 
							+ rowId + containerName + "$txt_BatterName*GEOM*TEXT SET " + bc.getPlayer().getTicker_name() + "\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$Split_Card$Left$Grp" 
							+ rowId + containerName + "$txt_Scores*GEOM*TEXT SET " + bc.getRuns() + "\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$Split_Card$Left$Grp" 
							+ rowId + containerName + "$txt_Balls*GEOM*TEXT SET " + bc.getBalls() + "\0", print_writers);
				}
			}
			
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$Split_Card$Right$select_DataType"
					+ "*FUNCTION*Omo*vis_con SET 6\0", print_writers);
			
			lngth=0;percent=0;
			OrderRuns = CricketFunctions.calculateOrderRuns(inning);
			
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$Split_Card$Right"
					+ "$ContributionGrp$BarAll$1$txt_StatHead*GEOM*TEXT SET TOP\0", print_writers);
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$Split_Card$Right"
					+ "$ContributionGrp$BarAll$2$txt_StatHead*GEOM*TEXT SET MIDDLE\0", print_writers);
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$Split_Card$Right"
					+ "$ContributionGrp$BarAll$3$txt_StatHead*GEOM*TEXT SET LOWER\0", print_writers);
			
	        for (int i = 0; i < OrderRuns.size(); i++) {
	        	percent = (100.0 * OrderRuns.get(i) / inning.getTotalRuns());
	        	percent = Math.round(percent * 100.0) / 100.0; // round to 2 decimals
	        	scaledPercent.add(percent);
	        	
	        	int rounded = (int) Math.round(percent);
		        roundedPercent.add(rounded);
	        }
	        
			for(int i=0;i<=2;i++) {
				lngth = (455 * ((double)OrderRuns.get(i) / inning.getTotalRuns()));
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$Split_Card$Right"
						+ "$ContributionGrp$BarAll$" + (i+1) + "$BarGrp$obj_Width*GEOM*width SET " + lngth + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$Split_Card$Right"
						+ "$ContributionGrp$BarAll$" + (i+1) + "$BarGrp$StatGrp$txt_StatValue*GEOM*TEXT SET " + roundedPercent.get(i) + "\0", print_writers);
			}
			break;
		case Constants.TRI_SERIES: case Constants.MT20:
			
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide 
					+ "$select_GraphicsType*FUNCTION*Omo*vis_con SET 0\0", print_writers);
			Collections.sort(inning.getBattingCard());
			for(int i=1; i<=13; i++) {
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide + "$BattingCard"+
						"$BatterGrp$" + i + "*ACTIVE SET 0\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide + "$BattingCard"+
						"$BowlerGrp$" + i + "$select_RowType*FUNCTION*Omo*vis_con SET 0 \0", print_writers);
			}
			rowId = 0;
			
			Collections.sort(inning.getBattingCard());
			for (BattingCard bc : inning.getBattingCard()) {
				rowId++;
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide 
						+ "$BattingCard$BatterGrp$" + rowId + "*ACTIVE SET 1\0", print_writers);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide 
						+ "$BattingCard$BatterGrp$Rows*FUNCTION*Grid*num_row SET " + rowId + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide 
						+ "$BattingCard$DismissalGrp$Rows*FUNCTION*Grid*num_row SET " + rowId + "\0", print_writers);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide 
						+ "$BattingCard$BatterGrp$Rows$" + rowId + "*ACTIVE SET 1\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide 
						+ "$BattingCard$DismissalGrp$Rows$" + rowId + "*ACTIVE SET 1\0", print_writers);
				
				switch (bc.getStatus().toUpperCase()) {
				case CricketUtil.STILL_TO_BAT:
					
					if(bc.getHowOut() == null) {
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide 
								+ "$BattingCard$BatterGrp$" + rowId + "$select_RowType*FUNCTION*Omo*vis_con SET 0\0", print_writers);
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide 
								+ "$BattingCard$DismissalGrp$" + rowId + "$select_RowType*FUNCTION*Omo*vis_con SET 0\0", print_writers);
						
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide 
								+ "$BattingCard$BatterGrp$" + rowId + "$Still_To_Bat$txt_BatterName*GEOM*TEXT SET " + bc.getPlayer().getTicker_name() + "\0", print_writers);
						
						if(!CricketFunctions.checkBatAndBallImpactInOutPlayer(matchAllData.getEventFile().getEvents(), bc.getPlayerId()).isEmpty()) {
							switch(CricketFunctions.checkBatAndBallImpactInOutPlayer(matchAllData.getEventFile().getEvents(), bc.getPlayerId())) {
							
							case "CON_OUT":
								if(bc.getBalls() == 0) {
									rowId = rowId - 1;
									this.numberOfRows = rowId;
								}
								break;
							}
						}
					}else if(bc.getHowOut().equalsIgnoreCase(CricketUtil.RETIRED_HURT)) {
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide 
								+ "$BattingCard$BatterGrp$" + rowId + "$select_RowType*FUNCTION*Omo*vis_con SET 1\0", print_writers);
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide 
								+ "$BattingCard$DismissalGrp$" + rowId + "$select_RowType*FUNCTION*Omo*vis_con SET 1\0", print_writers);
						
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide 
								+ "$BattingCard$BatterGrp$" + rowId + "$Out$Out$txt_BatterName*GEOM*TEXT SET " + bc.getPlayer().getTicker_name() + "\0", print_writers);
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide 
								+ "$BattingCard$BatterGrp$" + rowId + "$Out$Out$txt_Runs*GEOM*TEXT SET " + bc.getRuns() + "\0", print_writers);
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide 
								+ "$BattingCard$BatterGrp$" + rowId + "$Out$Out$txt_Balls*GEOM*TEXT SET " + bc.getBalls() + "\0", print_writers);
						
					}else {
						
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide 
								+ "$BattingCard$BatterGrp$" + rowId + "$select_RowType*FUNCTION*Omo*vis_con SET 1\0", print_writers);
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide 
								+ "$BattingCard$DismissalGrp$" + rowId + "$select_RowType*FUNCTION*Omo*vis_con SET 1\0", print_writers);
						
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide 
								+ "$BattingCard$BatterGrp$" + rowId + "$Out$Out$txt_BatterName*GEOM*TEXT SET " + bc.getPlayer().getTicker_name() + "\0", print_writers);
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide 
								+ "$BattingCard$BatterGrp$" + rowId + "$Out$Out$txt_Runs*GEOM*TEXT SET " + bc.getRuns() + "\0", print_writers);
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide 
								+ "$BattingCard$BatterGrp$" + rowId + "$Out$Out$txt_Balls*GEOM*TEXT SET " + bc.getBalls() + "\0", print_writers);
					}
					break;
				default:
					switch (bc.getStatus().toUpperCase()) {
					case CricketUtil.OUT:
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide 
								+ "$BattingCard$BatterGrp$" + rowId + "$select_RowType*FUNCTION*Omo*vis_con SET 1\0", print_writers);
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide 
								+ "$BattingCard$DismissalGrp$" + rowId + "$select_RowType*FUNCTION*Omo*vis_con SET 1\0", print_writers);
						containerName = "$Out$Out";
						break;
					case CricketUtil.NOT_OUT:
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide 
								+ "$BattingCard$BatterGrp$" + rowId + "$select_RowType*FUNCTION*Omo*vis_con SET 2\0", print_writers);
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide 
								+ "$BattingCard$DismissalGrp$" + rowId + "$select_RowType*FUNCTION*Omo*vis_con SET 2\0", print_writers);
						
						containerName = "$NotOut";
						break;
					}
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide 
							+ "$BattingCard$BatterGrp$" + rowId + containerName + "$txt_BatterName*GEOM*TEXT SET " + bc.getPlayer().getTicker_name() + "\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide 
							+ "$BattingCard$BatterGrp$" + rowId + containerName + "$txt_Runs*GEOM*TEXT SET " + bc.getRuns() + "\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide 
							+ "$BattingCard$BatterGrp$" + rowId + containerName + "$txt_Balls*GEOM*TEXT SET " + bc.getBalls() + "\0", print_writers);
				}
			}
			
			lngth=0;percent=0;
			OrderRuns = CricketFunctions.calculateOrderRuns(inning);
			
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide + "$BattingCard"
					+ "$ContributionGrp$StatHeadGrp$1$txt_StatHead*GEOM*TEXT SET TOP\0", print_writers);
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide + "$BattingCard"
					+ "$ContributionGrp$StatHeadGrp$2$txt_StatHead*GEOM*TEXT SET MIDDLE\0", print_writers);
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide + "$BattingCard"
					+ "$ContributionGrp$StatHeadGrp$3$txt_StatHead*GEOM*TEXT SET LOWER\0", print_writers);
			
	        for (int i = 0; i < OrderRuns.size(); i++) {
	        	percent = (100.0 * OrderRuns.get(i) / inning.getTotalRuns());
	        	percent = Math.round(percent * 100.0) / 100.0; // round to 2 decimals
	        	scaledPercent.add(percent);
	        	
	        	int rounded = (int) Math.round(percent);
		        roundedPercent.add(rounded);
	        }
	        
	        CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide + "$BattingCard"
					+ "$ContributionGrp*ACTIVE SET 1\0", print_writers);
			
			for(int i=0;i<=2;i++) {
				lngth = (150 * ((double)OrderRuns.get(i) / inning.getTotalRuns()));
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide + "$BattingCard"
						+ "$ContributionGrp$BarGrp$" + (i+1) + "$obj_ScaleX*GEOM*width SET " + lngth + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide + "$BattingCard"
						+ "$ContributionGrp$BarGrp$" + (i+1) + "$StatGrp$txt_StatValue*GEOM*TEXT SET " + roundedPercent.get(i) + "\0", print_writers);
			}
			
			this.numberOfRows = rowId;
			break;
		}
		return Constants.OK;
	}
	
	public String MatchIdentAndPromoBody(List<PrintWriter> print_writers, String whatToProcess,int WhichSide, MatchAllData matchAllData, Configuration config) {
		
		switch (config.getBroadcaster()) {
		case Constants.AFG_SL_SERIES:
			Team HomeTeam = null,AwayTeam=null;
			String Header="",Footer="",newDate="";
			switch(whatToProcess) {
			case "m":
				HomeTeam = matchAllData.getSetup().getHomeTeam();
				AwayTeam = matchAllData.getSetup().getAwayTeam();
				Header = matchAllData.getSetup().getMatchIdent();
				Footer = "LIVE FROM " + matchAllData.getSetup().getVenueName();
				break;
			case "Control_m":
				HomeTeam = fixture.getHome_Team();
				AwayTeam = fixture.getAway_Team();
				Header = toOrdinalWords(fixture.getMatchnumber()).toUpperCase() + " T20I";

				Calendar cal = Calendar.getInstance();
				cal.add(Calendar.DATE, +1);
				if(fixture.getDate().equalsIgnoreCase(new SimpleDateFormat("dd-MM-yyyy").format(cal.getTime()))) {
					Footer = "TOMORROW - " + (fixture.getLocalTime() != null ?fixture.getLocalTime() + " - ":"") + matchAllData.getSetup().getVenueName();
				}else {
					cal.add(Calendar.DATE, -1);
					if(fixture.getDate().equalsIgnoreCase(new SimpleDateFormat("dd-MM-yyyy").format(cal.getTime()))) {
						Footer = "UP-NEXT";
					}else {
						newDate = fixture.getDate().split("-")[0];
						if(Integer.valueOf(newDate) < 10) {
							newDate = newDate.replaceFirst("0", "");
						}
						Footer = CricketFunctions.ordinal(Integer.valueOf(newDate)) + " " + Month.of(Integer.valueOf(fixture.getDate().split("-")[1])) 
							+ " - " + (fixture.getLocalTime() != null ?fixture.getLocalTime() + " - ":"") + matchAllData.getSetup().getVenueName();
					}
				}
				break;
			}
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_Ident$LogoGrp$Team1$LogoAll$img_Logos*TEXTURE*IMAGE SET " + 
					Constants.AFG_SL_SERIES_LOGO + HomeTeam.getTeamBadge() + "\0", print_writers);
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_Ident$LogoGrp$Team1$LogoAll$LogoShadow*TEXTURE*IMAGE SET " + 
					Constants.AFG_SL_SERIES_LOGO_BW + HomeTeam.getTeamBadge() + "\0", print_writers);
			
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_Ident$TeamNameGrp$Team1$txt_Name*GEOM*TEXT SET " + 
					HomeTeam.getTeamName4() + "\0", print_writers);
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_Ident$TeamNameGrp$Team1$Name*GEOM*TEXT SET " + 
					HomeTeam.getTeamName4() + "\0", print_writers);
			
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_Ident$LogoGrp$Team2$LogoAll$img_Logos*TEXTURE*IMAGE SET " + 
					Constants.AFG_SL_SERIES_LOGO + AwayTeam.getTeamBadge() + "\0", print_writers);
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_Ident$LogoGrp$Team2$LogoAll$LogoShadow*TEXTURE*IMAGE SET " + 
					Constants.AFG_SL_SERIES_LOGO_BW + AwayTeam.getTeamBadge() + "\0", print_writers);
			
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_Ident$TeamNameGrp$Team2$txt_Name*GEOM*TEXT SET " + 
					AwayTeam.getTeamName4() + "\0", print_writers);
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_Ident$TeamNameGrp$Team2$Name*GEOM*TEXT SET " + 
					AwayTeam.getTeamName4() + "\0", print_writers);
			
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_Ident$SubHeader$txt_Sub_Header*GEOM*TEXT SET " + Header + "\0", print_writers);
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_Ident$Footer$txt_Footer*GEOM*TEXT SET " + Footer + "\0", print_writers);
			break;
		case Constants.BAN_AFG_SERIES: case Constants.ACC:
			switch(whatToProcess) {
			case "m":
				switch (config.getBroadcaster()) {
				case Constants.BAN_AFG_SERIES:
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$MatchID$BattingCard$TeamName$Home$img_Base2*TEXTURE*IMAGE SET " + 
							Constants.BAN_AFG_SERIES_BASE2 + matchAllData.getSetup().getHomeTeam().getTeamBadge() + "\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$MatchID$BattingCard$TeamBageGrp$img_Badges*TEXTURE*IMAGE SET " + 
							Constants.BAN_AFG_SERIES_LOGO + matchAllData.getSetup().getHomeTeam().getTeamBadge() + "\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$MatchID$BattingCard$TeamBageGrp$img_Badges02*TEXTURE*IMAGE SET " + 
							Constants.BAN_AFG_SERIES_LOGO + matchAllData.getSetup().getHomeTeam().getTeamBadge() + "\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$MatchID$BattingCard$TeamBageGrp$LogoBase$img_Base2*TEXTURE*IMAGE SET " + 
							Constants.BAN_AFG_SERIES_BASE2 + matchAllData.getSetup().getHomeTeam().getTeamBadge() + "\0", print_writers);
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$MatchID$BattingCard$TeamName$Away$img_Base2*TEXTURE*IMAGE SET " + 
							Constants.BAN_AFG_SERIES_BASE2 + matchAllData.getSetup().getAwayTeam().getTeamBadge() + "\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$MatchID$BattingCard$TeamBageGrp2$img_Badges*TEXTURE*IMAGE SET " + 
							Constants.BAN_AFG_SERIES_LOGO + matchAllData.getSetup().getAwayTeam().getTeamBadge() + "\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$MatchID$BattingCard$TeamBageGrp2$img_Badges02*TEXTURE*IMAGE SET " + 
							Constants.BAN_AFG_SERIES_LOGO + matchAllData.getSetup().getAwayTeam().getTeamBadge() + "\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$MatchID$BattingCard$TeamBageGrp2$LogoBase$img_Base2*TEXTURE*IMAGE SET " + 
							Constants.BAN_AFG_SERIES_BASE2 + matchAllData.getSetup().getAwayTeam().getTeamBadge() + "\0", print_writers);
					
					break;
				default:
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$MatchID$BattingCard$Home$Select*FUNCTION*Omo*vis_con SET 1\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$MatchID$BattingCard$Away$Select*FUNCTION*Omo*vis_con SET 1\0", print_writers);
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$MatchID$BattingCard$TeamName$Select*FUNCTION*Omo*vis_con SET 1\0", print_writers);
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$MatchID$BattingCard$Home$Flag$Base*ACTIVE SET 0\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$MatchID$BattingCard$Away$Flag$Base*ACTIVE SET 0\0", print_writers);
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$MatchID$BattingCard$Home$Flag$img_Badges*TEXTURE*IMAGE SET " + 
							Constants.ACC_FLAG + matchAllData.getSetup().getHomeTeam().getTeamBadge() + "\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$MatchID$BattingCard$Away$Flag$img_Badges*TEXTURE*IMAGE SET " + 
							Constants.ACC_FLAG + matchAllData.getSetup().getAwayTeam().getTeamBadge() + "\0", print_writers);
					
					for(Player plyr : matchAllData.getSetup().getHomeSquad()) {
						if(plyr.getCaptainWicketKeeper() != null && (plyr.getCaptainWicketKeeper().equalsIgnoreCase(CricketUtil.CAPTAIN) || 
								plyr.getCaptainWicketKeeper().equalsIgnoreCase("CAPTAIN_WICKET_KEEPER"))) {
							
							switch (config.getBroadcaster()) {
							case Constants.ACC:
								if(config.getPrimaryIpAddress().equalsIgnoreCase(Constants.LOCALHOST)) {
									CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$Target$PlayerImageOut$PlayerImageGrp1$img_Player*TEXTURE*IMAGE SET " + 
											Constants.ACC_LOCAL_PHOTO_PATH + matchAllData.getSetup().getHomeTeam().getTeamBadge() + "\\\\" + Constants.CENTER + "\\\\" + plyr.getPhoto() + 
											CricketUtil.PNG_EXTENSION + "\0", print_writers);
									break;
								}else {
									CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$Target$PlayerImageOut$PlayerImageGrp1$img_Player*TEXTURE*IMAGE SET " + "\\\\" 
											+ config.getPrimaryIpAddress() + Constants.ACC_PHOTO_PATH + matchAllData.getSetup().getHomeTeam().getTeamBadge() + "\\\\" + Constants.CENTER + "\\\\" + plyr.getPhoto() 
											+ CricketUtil.PNG_EXTENSION + "\0", print_writers);
									break;
								}
							}
							
						}
					}
					
					for(Player plyr : matchAllData.getSetup().getAwaySquad()) {
						if(plyr.getCaptainWicketKeeper() != null && (plyr.getCaptainWicketKeeper().equalsIgnoreCase(CricketUtil.CAPTAIN) || 
								plyr.getCaptainWicketKeeper().equalsIgnoreCase("CAPTAIN_WICKET_KEEPER"))) {
							
							switch (config.getBroadcaster()) {
							case Constants.ACC:
								if(config.getPrimaryIpAddress().equalsIgnoreCase(Constants.LOCALHOST)) {
									CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$Target$PlayerImageOut$PlayerImageGrp2$img_Player*TEXTURE*IMAGE SET " + 
											Constants.ACC_LOCAL_PHOTO_PATH + matchAllData.getSetup().getAwayTeam().getTeamBadge() + "\\\\" + Constants.CENTER + "\\\\" + plyr.getPhoto() + 
											CricketUtil.PNG_EXTENSION + "\0", print_writers);
									break;
								}else {
									CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$Target$PlayerImageOut$PlayerImageGrp2$img_Player*TEXTURE*IMAGE SET " + "\\\\" 
											+ config.getPrimaryIpAddress() + Constants.ACC_PHOTO_PATH + matchAllData.getSetup().getAwayTeam().getTeamBadge() + "\\\\" + Constants.CENTER + "\\\\" + plyr.getPhoto() 
											+ CricketUtil.PNG_EXTENSION + "\0", print_writers);
									break;
								}
							}
						}
					}
					break;
				}
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$MatchID$BattingCard$TeamName$Home$000Header*GEOM*TEXT SET " + 
						matchAllData.getSetup().getHomeTeam().getTeamName1() + "\0", print_writers);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$MatchID$BattingCard$TeamName$Away$000Header*GEOM*TEXT SET " + 
						matchAllData.getSetup().getAwayTeam().getTeamName1() + "\0", print_writers);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$MatchID$BattingCard$TeamName$Flag$Home$000Header*GEOM*TEXT SET " + 
						matchAllData.getSetup().getHomeTeam().getTeamName1() + "\0", print_writers);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$MatchID$BattingCard$TeamName$Flag$Away$000Header*GEOM*TEXT SET " + 
						matchAllData.getSetup().getAwayTeam().getTeamName1() + "\0", print_writers);
				
				break;
			case "Control_m":
				
				switch (config.getBroadcaster()) {
				case Constants.BAN_AFG_SERIES:
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$MatchID$BattingCard$TeamName$Home$img_Base2*TEXTURE*IMAGE SET " + 
							Constants.BAN_AFG_SERIES_BASE2 + fixture.getHome_Team().getTeamBadge() + "\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$MatchID$BattingCard$TeamBageGrp$img_Badges*TEXTURE*IMAGE SET " + 
							Constants.BAN_AFG_SERIES_LOGO + fixture.getHome_Team().getTeamBadge() + "\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$MatchID$BattingCard$TeamBageGrp$img_Badges02*TEXTURE*IMAGE SET " + 
							Constants.BAN_AFG_SERIES_LOGO + fixture.getHome_Team().getTeamBadge() + "\0", print_writers);
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$MatchID$BattingCard$TeamBageGrp$LogoBase$img_Base2*TEXTURE*IMAGE SET " + 
							Constants.BAN_AFG_SERIES_BASE2 + fixture.getHome_Team().getTeamBadge() + "\0", print_writers);
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$MatchID$BattingCard$TeamName$Away$img_Base2*TEXTURE*IMAGE SET " + 
							Constants.BAN_AFG_SERIES_BASE2 + fixture.getAway_Team().getTeamBadge() + "\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$MatchID$BattingCard$TeamBageGrp2$img_Badges*TEXTURE*IMAGE SET " + 
							Constants.BAN_AFG_SERIES_LOGO + fixture.getAway_Team().getTeamBadge() + "\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$MatchID$BattingCard$TeamBageGrp2$img_Badges02*TEXTURE*IMAGE SET " + 
							Constants.BAN_AFG_SERIES_LOGO + fixture.getAway_Team().getTeamBadge() + "\0", print_writers);
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$MatchID$BattingCard$TeamBageGrp2$LogoBase$img_Base2*TEXTURE*IMAGE SET " + 
							Constants.BAN_AFG_SERIES_BASE2 + fixture.getAway_Team().getTeamBadge() + "\0", print_writers);
					break;
				default:
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$MatchID$BattingCard$Home$Select*FUNCTION*Omo*vis_con SET 1\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$MatchID$BattingCard$Away$Select*FUNCTION*Omo*vis_con SET 1\0", print_writers);
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$MatchID$BattingCard$TeamName$Select*FUNCTION*Omo*vis_con SET 1\0", print_writers);
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$MatchID$BattingCard$Home$Flag$Base*ACTIVE SET 0\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$MatchID$BattingCard$Away$Flag$Base*ACTIVE SET 0\0", print_writers);
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$MatchID$BattingCard$Home$Flag$img_Badges*TEXTURE*IMAGE SET " + 
							Constants.ACC_FLAG + fixture.getHome_Team().getTeamBadge() + "\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$MatchID$BattingCard$Away$Flag$img_Badges*TEXTURE*IMAGE SET " + 
							Constants.ACC_FLAG + fixture.getAway_Team().getTeamBadge() + "\0", print_writers);
					
					for(Player plyr : matchAllData.getSetup().getHomeSquad()) {
						if(plyr.getCaptainWicketKeeper() != null && (plyr.getCaptainWicketKeeper().equalsIgnoreCase(CricketUtil.CAPTAIN) || 
								plyr.getCaptainWicketKeeper().equalsIgnoreCase("CAPTAIN_WICKET_KEEPER"))) {
							
							switch (config.getBroadcaster()) {
							case Constants.ACC:
								if(config.getPrimaryIpAddress().equalsIgnoreCase(Constants.LOCALHOST)) {
									CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$Target$PlayerImageOut$PlayerImageGrp1$img_Player*TEXTURE*IMAGE SET " + 
											Constants.ACC_LOCAL_PHOTO_PATH + fixture.getHome_Team().getTeamBadge() + "\\\\" + Constants.CENTER + "\\\\" + plyr.getPhoto() + 
											CricketUtil.PNG_EXTENSION + "\0", print_writers);
									break;
								}else {
									CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$Target$PlayerImageOut$PlayerImageGrp1$img_Player*TEXTURE*IMAGE SET " + "\\\\" 
											+ config.getPrimaryIpAddress() + Constants.ACC_PHOTO_PATH + fixture.getHome_Team().getTeamBadge() + "\\\\" + Constants.CENTER + "\\\\" + plyr.getPhoto() 
											+ CricketUtil.PNG_EXTENSION + "\0", print_writers);
									break;
								}
							}
						}
					}
					
					for(Player plyr : matchAllData.getSetup().getAwaySquad()) {
						if(plyr.getCaptainWicketKeeper() != null && (plyr.getCaptainWicketKeeper().equalsIgnoreCase(CricketUtil.CAPTAIN) || 
								plyr.getCaptainWicketKeeper().equalsIgnoreCase("CAPTAIN_WICKET_KEEPER"))) {
							
							switch (config.getBroadcaster()) {
							case Constants.ACC:
								if(config.getPrimaryIpAddress().equalsIgnoreCase(Constants.LOCALHOST)) {
									CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$Target$PlayerImageOut$PlayerImageGrp2$img_Player*TEXTURE*IMAGE SET " + 
											Constants.ACC_LOCAL_PHOTO_PATH + fixture.getAway_Team().getTeamBadge() + "\\\\" + Constants.CENTER + "\\\\" + plyr.getPhoto() + 
											CricketUtil.PNG_EXTENSION + "\0", print_writers);
									break;
								}else {
									CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$Target$PlayerImageOut$PlayerImageGrp2$img_Player*TEXTURE*IMAGE SET " + "\\\\" 
											+ config.getPrimaryIpAddress() + Constants.ACC_PHOTO_PATH + fixture.getAway_Team().getTeamBadge() + "\\\\" + Constants.CENTER + "\\\\" + plyr.getPhoto() 
											+ CricketUtil.PNG_EXTENSION + "\0", print_writers);
									break;
								}
							}
						}
					}
					break;
				}
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$MatchID$BattingCard$TeamName$Home$000Header*GEOM*TEXT SET " + 
						fixture.getHome_Team().getTeamName1() + "\0", print_writers);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$MatchID$BattingCard$TeamName$Away$000Header*GEOM*TEXT SET " + 
						fixture.getAway_Team().getTeamName1() + "\0", print_writers);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$MatchID$BattingCard$TeamName$Flag$Home$000Header*GEOM*TEXT SET " + 
						fixture.getHome_Team().getTeamName1() + "\0", print_writers);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$MatchID$BattingCard$TeamName$Flag$Away$000Header*GEOM*TEXT SET " + 
						fixture.getAway_Team().getTeamName1() + "\0", print_writers);
				break;
			}
			break;
		case Constants.TRI_SERIES: case Constants.MT20:
			switch(whatToProcess) {
			case "m":
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_Ident$Main$Big_Logo$Team1$img_Logos_BW*TEXTURE*IMAGE SET " + 
						(config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_LOGO_BW : Constants.MT20_LOGO_BW) + matchAllData.getSetup().getHomeTeam().getTeamBadge() + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_Ident$Main$TeamLogo$Team_1$img_Logo*TEXTURE*IMAGE SET " + 
						(config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_LOGO : Constants.MT20_LOGO) + matchAllData.getSetup().getHomeTeam().getTeamBadge() + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_Ident$Main$TeamLogo$Team_1$img_LogoShadow*TEXTURE*IMAGE SET " + 
						(config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_LOGO : Constants.MT20_LOGO) + matchAllData.getSetup().getHomeTeam().getTeamBadge() + "\0", print_writers);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_Ident$Main$TeamLogo$Team_1$TeamName$img_Base1*TEXTURE*IMAGE SET " + 
						(config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_BASE1 : Constants.MT20_BASE1) + matchAllData.getSetup().getHomeTeam().getTeamBadge() + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_Ident$Main$TeamLogo$Team_1$TeamName$img_Text1*TEXTURE*IMAGE SET " + 
						(config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_TEXT1 : Constants.MT20_TEXT1) + matchAllData.getSetup().getHomeTeam().getTeamBadge() + "\0", print_writers);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_Ident$MatchNumberGrp$txt_MatchNumber"
						+ "*GEOM*TEXT SET " + matchAllData.getSetup().getMatchIdent() + "\0", print_writers);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_Ident$TopTitleGrp$txt_Title"
						+ "*GEOM*TEXT SET " + matchAllData.getSetup().getTournament() + "\0", print_writers);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_Ident$Main$TeamLogo$Team_1$TeamName$txt_TeamName"
						+ "*GEOM*TEXT SET " + matchAllData.getSetup().getHomeTeam().getTeamName1() + "\0", print_writers);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_Ident$Main$EventLogo$Event_Logo*TEXTURE*IMAGE SET " + 
						(config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_LOGO : Constants.MT20_LOGO) + "EVENT" + "\0", print_writers);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_Ident$Main$Big_Logo$Team_2$img_Logos_BW*TEXTURE*IMAGE SET " + 
						(config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_LOGO_BW : Constants.MT20_LOGO_BW) + matchAllData.getSetup().getAwayTeam().getTeamBadge() + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_Ident$Main$TeamLogo$Team_2$img_Logo*TEXTURE*IMAGE SET " + 
						(config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_LOGO : Constants.MT20_LOGO) + matchAllData.getSetup().getAwayTeam().getTeamBadge() + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_Ident$Main$TeamLogo$Team_2$img_LogoShadow*TEXTURE*IMAGE SET " + 
						(config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_LOGO : Constants.MT20_LOGO) + matchAllData.getSetup().getAwayTeam().getTeamBadge() + "\0", print_writers);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_Ident$Main$TeamLogo$Team_2$TeamName$img_Base1*TEXTURE*IMAGE SET " + 
						(config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_BASE1 : Constants.MT20_BASE1) + matchAllData.getSetup().getAwayTeam().getTeamBadge() + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_Ident$Main$TeamLogo$Team_2$TeamName$img_Text1*TEXTURE*IMAGE SET " + 
						(config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_TEXT1 : Constants.MT20_TEXT1) + matchAllData.getSetup().getAwayTeam().getTeamBadge() + "\0", print_writers);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_Ident$Main$TeamLogo$Team_2$TeamName$txt_TeamName"
						+ "*GEOM*TEXT SET " + matchAllData.getSetup().getAwayTeam().getTeamName1() + "\0", print_writers);
				break;
			case "Control_m":
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_Ident$Main$Big_Logo$Team1$img_Logos_BW*TEXTURE*IMAGE SET " + 
						(config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_LOGO_BW : Constants.MT20_LOGO_BW) + fixture.getHome_Team().getTeamBadge() + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_Ident$Main$TeamLogo$Team_1$img_Logo*TEXTURE*IMAGE SET " + 
						(config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_LOGO : Constants.MT20_LOGO) + fixture.getHome_Team().getTeamBadge() + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_Ident$Main$TeamLogo$Team_1$img_LogoShadow*TEXTURE*IMAGE SET " + 
						(config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_LOGO : Constants.MT20_LOGO) + fixture.getHome_Team().getTeamBadge() + "\0", print_writers);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_Ident$Main$TeamLogo$Team_1$TeamName$img_Base1*TEXTURE*IMAGE SET " + 
						(config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_BASE1 : Constants.MT20_BASE1) + fixture.getHome_Team().getTeamBadge() + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_Ident$Main$TeamLogo$Team_1$TeamName$img_Text1*TEXTURE*IMAGE SET " + 
						(config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_TEXT1 : Constants.MT20_TEXT1) + fixture.getHome_Team().getTeamBadge() + "\0", print_writers);
				
				if(fixture.getMatchnumber() <= 9) {
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_Ident$MatchNumberGrp$txt_MatchNumber"
							+ "*GEOM*TEXT SET MATCH " + fixture.getMatchnumber() + "\0", print_writers);
				}else {
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_Ident$MatchNumberGrp$txt_MatchNumber"
							+ "*GEOM*TEXT SET " + fixture.getMatchfilename() + "\0", print_writers);
				}
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_Ident$TopTitleGrp$txt_Title"
						+ "*GEOM*TEXT SET " + matchAllData.getSetup().getTournament() + "\0", print_writers);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_Ident$Main$TeamLogo$Team_1$TeamName$txt_TeamName"
						+ "*GEOM*TEXT SET " + fixture.getHome_Team().getTeamName1() + "\0", print_writers);
				
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_Ident$Main$EventLogo$Event_Logo*TEXTURE*IMAGE SET " + 
						(config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_LOGO : Constants.MT20_LOGO) + "EVENT" + "\0", print_writers);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_Ident$Main$Big_Logo$Team_2$img_Logos_BW*TEXTURE*IMAGE SET " + 
						(config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_LOGO_BW : Constants.MT20_LOGO_BW) + fixture.getAway_Team().getTeamBadge() + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_Ident$Main$TeamLogo$Team_2$img_Logo*TEXTURE*IMAGE SET " + 
						(config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_LOGO : Constants.MT20_LOGO) + fixture.getAway_Team().getTeamBadge() + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_Ident$Main$TeamLogo$Team_2$img_LogoShadow*TEXTURE*IMAGE SET " + 
						(config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_LOGO : Constants.MT20_LOGO) + fixture.getAway_Team().getTeamBadge() + "\0", print_writers);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_Ident$Main$TeamLogo$Team_2$TeamName$img_Base1*TEXTURE*IMAGE SET " + 
						(config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_BASE1 : Constants.MT20_BASE1) + fixture.getAway_Team().getTeamBadge() + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_Ident$Main$TeamLogo$Team_2$TeamName$img_Text1*TEXTURE*IMAGE SET " + 
						(config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_TEXT1 : Constants.MT20_TEXT1) + fixture.getAway_Team().getTeamBadge() + "\0", print_writers);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_Ident$Main$TeamLogo$Team_2$TeamName$txt_TeamName"
						+ "*GEOM*TEXT SET " + fixture.getAway_Team().getTeamName1() + "\0", print_writers);
				break;
			}
			break;
		}
		return Constants.OK;
	}
	public String PlayerProfileBody(List<PrintWriter> print_writers, String whatToProcess, int WhichSide, MatchAllData matchAllData, Configuration config) throws InterruptedException {
		
		String[] TitleData = null;
		String[] StatData = null;
		String which_role = "";
		
		switch (config.getBroadcaster()) {
		case Constants.AFG_SL_SERIES:
			if(player.getSurname() != null) {
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_Profile$Main$Side" + WhichSide + "$Name$txt_Firstname*GEOM*TEXT SET " + player.getFirstname() + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_Profile$Main$Side" + WhichSide + "$Name$txt_LastName*GEOM*TEXT SET " + player.getSurname() + "\0", print_writers);
			}else {
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_Profile$Main$Side" + WhichSide + "$Name$txt_Firstname*GEOM*TEXT SET \0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_Profile$Main$Side" + WhichSide + "$Name$txt_LastName*GEOM*TEXT SET " + player.getFirstname() + "\0", print_writers);
			}
			
			if(config.getPrimaryIpAddress().equalsIgnoreCase(Constants.LOCALHOST)) {
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_Profile$Main$Side" + WhichSide + "$ImageGrp$img_Player*TEXTURE*IMAGE SET " + Constants.AFG_SL_SERIES_LOCAL_PHOTO_PATH 
						+ "\\\\" + team.getTeamBadge() + "\\\\" + Constants.RIGHT + "\\\\" + player.getPhoto() + CricketUtil.PNG_EXTENSION + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_Profile$Main$Side" + WhichSide + "$ImageGrp$PlayerShadow*TEXTURE*IMAGE SET " + Constants.AFG_SL_SERIES_LOCAL_PHOTO_PATH 
						+ "\\\\" + team.getTeamBadge() + "\\\\" + Constants.RIGHT + "\\\\" + player.getPhoto() + CricketUtil.PNG_EXTENSION + "\0", print_writers);
			}else {
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_Profile$Main$Side" + WhichSide + "$ImageGrp$img_Player*TEXTURE*IMAGE SET " + "\\\\" + config.getPrimaryIpAddress() 
						+ "\\\\" + Constants.AFG_SL_SERIES_PHOTO_PATH + "\\\\" + team.getTeamBadge() + "\\\\" + Constants.RIGHT + "\\\\" + player.getPhoto() + CricketUtil.PNG_EXTENSION + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_Profile$Main$Side" + WhichSide + "$ImageGrp$PlayerShadow*TEXTURE*IMAGE SET " + "\\\\" + config.getPrimaryIpAddress() 
				+ "\\\\" + Constants.AFG_SL_SERIES_PHOTO_PATH + "\\\\" + team.getTeamBadge() + "\\\\" + Constants.RIGHT + "\\\\" + player.getPhoto() + CricketUtil.PNG_EXTENSION + "\0", print_writers);
			}
			
			switch(whatToProcess) {
			case "Control_d":
				if(player.getBattingStyle().equalsIgnoreCase("LHB")) {
					which_role = "Batsman_Lefthand";
				}else {
					which_role = "Bat_Icon";
				}
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_Profile$Main$Side" + WhichSide + "$Info$txt_Style*GEOM*TEXT SET " + player.getBattingStyle().toUpperCase() + "\0", print_writers);
				
				switch (WhichProfile) {
				case "ODI": case "Test": case "LIST A":
					TitleData = new String[] {"MATCHES", "RUNS", "50s / 100s", "AVERAGE", "BEST"};
					StatData = new String[] {String.valueOf(stat.getMatches()),String.valueOf(stat.getRuns()),stat.getFifties() + " / " + stat.getHundreds(),
							CricketFunctions.getAverage(stat.getInnings(), stat.getNot_out(), stat.getRuns(), 1, "-"),(stat.getBest_score().equalsIgnoreCase("0") ? "-" : stat.getBest_score())};
					break;
				default:
					TitleData = new String[] {"MATCHES", "RUNS", "STRIKE RATE", "50s / 100s", "BEST"};
					StatData = new String[] {String.valueOf(stat.getMatches()),String.valueOf(stat.getRuns()),CricketFunctions.generateStrikeRate(stat.getRuns(), stat.getBalls_faced(), 0),
								stat.getFifties() + " / " + stat.getHundreds(),(stat.getBest_score().equalsIgnoreCase("0") ? "-" : stat.getBest_score())};
					break;
				}
				break;
			case "Control_e":
				if(player.getBowlingStyle() == null) {
					which_role = "Bowler";
				}else {
					switch(player.getBowlingStyle()) {
					case "RF": case "RFM": case "RMF": case "RM": case "RSM": case "LF": case "LFM": case "LMF": case "LM":
						which_role = "FastBowler";
						break;
					case "LSL": case "WSL": case "LCH": case "RLG": case "WSR": case "LSO":
						which_role = "SpinBowler";
						break;
					case "ROB":
						which_role = "Off_Spin";
						break;
					case "RLB":
						which_role = "Leg_Spin";
						break;	
					}
				}
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_Profile$Main$Side" + WhichSide + "$Info$txt_Style*GEOM*TEXT SET " + player.getBowlingStyle().toUpperCase() + "\0", print_writers);
				
				TitleData = new String[] {"MATCHES", "WICKETS", "ECONOMY", "3WI / 5WI", "BEST"};
				StatData = new String[] {String.valueOf(stat.getMatches()),String.valueOf(stat.getWickets()),CricketFunctions.getEconomy(stat.getRuns_conceded(), stat.getBalls_bowled(), 2, "-"),
						stat.getPlus_3() + " / " + stat.getPlus_5(),(stat.getBest_figures().contains("-") ? stat.getBest_figures() : "-")};
				break;
			}
			
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_Profile$Main$Side" + WhichSide + "$Info$ing_icon*TEXTURE*IMAGE SET " 
					+ Constants.AFG_SL_SERIES_ICONS + which_role + "\0", print_writers);
			
			for (int i = 0; i < TitleData.length; i++) {
			    CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_Profile$Main$Side" + WhichSide + "$Data$Grp" + (i+1) + "$Highlight$txt_StatHead"
			    		+ "*GEOM*TEXT SET " + TitleData[i] + "\0", print_writers);
			    CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_Profile$Main$Side" + WhichSide + "$Data$Grp" + (i+1) + "$Highlight$txt_StatValue"
			    		+ "*GEOM*TEXT SET " + StatData[i] + "\0", print_writers);
			}
			break;
		case Constants.BAN_AFG_SERIES: case Constants.ACC:
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$Select*FUNCTION*Omo*vis_con SET 7\0", print_writers);
			
			switch (config.getBroadcaster()) {
			case Constants.BAN_AFG_SERIES:
				if(config.getPrimaryIpAddress().equalsIgnoreCase(Constants.LOCALHOST)) {
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$Profile$Right$img_Player*TEXTURE*IMAGE SET " 
							+ Constants.BAN_AFG_SERIES_LOCAL_PHOTO_PATH + "\\\\" + team.getTeamName4() + "\\\\" + Constants.CENTER + "\\\\" + player.getPhoto()
							+ CricketUtil.PNG_EXTENSION + "\0", print_writers);
				}else {
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$Profile$Right$img_Player*TEXTURE*IMAGE SET " + "\\\\" 
							+ config.getPrimaryIpAddress() + "\\\\" + Constants.BAN_AFG_SERIES_PHOTO_PATH + "\\\\" + team.getTeamName4() + "\\\\" + Constants.CENTER + "\\\\" 
							+ player.getPhoto() + CricketUtil.PNG_EXTENSION + "\0", print_writers);
				}
				break;
			default:
				if(config.getPrimaryIpAddress().equalsIgnoreCase(Constants.LOCALHOST)) {
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$Profile$Right$img_Player*TEXTURE*IMAGE SET " 
							+ Constants.ACC_LOCAL_PHOTO_PATH + "\\\\" + team.getTeamBadge() + "\\\\" + Constants.RIGHT + "\\\\" + player.getPhoto()
							+ CricketUtil.PNG_EXTENSION + "\0", print_writers);
				}else {
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$Profile$Right$img_Player*TEXTURE*IMAGE SET " + "\\\\" 
							+ config.getPrimaryIpAddress() + "\\\\" + Constants.ACC_PHOTO_PATH + "\\\\" + team.getTeamBadge() + "\\\\" + Constants.RIGHT + "\\\\" 
							+ player.getPhoto() + CricketUtil.PNG_EXTENSION + "\0", print_writers);
				}
				break;
			}
			
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$Profile$Right$img_Text1$txt_StatValue"
					+ "*GEOM*TEXT SET \0", print_writers);
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$Profile$Right$img_Text$txt_PlayerName"
					+ "*GEOM*TEXT SET " + player.getFull_name() + "\0", print_writers);
			
			switch (config.getBroadcaster().toUpperCase()) {
			case Constants.ACC:
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$Profile$BatHeaderGrp$Bottom_Header"
						+ "$txt_TeamName*GEOM*TEXT SET " + player.getFull_name() + "\0", print_writers);
				break;
			}
			
			switch(whatToProcess) {
			case "Control_d":
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$Profile$Right$img_Text1$txt_StatHead"
						+ "*GEOM*TEXT SET " + CricketFunctions.getbattingstyle(player.getBattingStyle(),CricketUtil.FULL, true, false).toUpperCase() + "\0", print_writers);
				
				switch (WhichProfile) {
				case "ODI": case "Test": case "LIST A":
					TitleData = new String[] {"MATCHES", "RUNS", "50s / 100s", "AVERAGE", "BEST"};
					StatData = new String[] {String.valueOf(stat.getMatches()),String.valueOf(stat.getRuns()),stat.getFifties() + " / " + stat.getHundreds(),
							CricketFunctions.getAverage(stat.getInnings(), stat.getNot_out(), stat.getRuns(), 1, "-"),(stat.getBest_score().equalsIgnoreCase("0") ? "-" : stat.getBest_score())};
					break;
				default:
					TitleData = new String[] {"MATCHES", "RUNS", "STRIKE RATE", "50s / 100s", "BEST"};
					StatData = new String[] {String.valueOf(stat.getMatches()),String.valueOf(stat.getRuns()),CricketFunctions.generateStrikeRate(stat.getRuns(), stat.getBalls_faced(), 0),
								stat.getFifties() + " / " + stat.getHundreds(),(stat.getBest_score().equalsIgnoreCase("0") ? "-" : stat.getBest_score())};
					break;
				}
				break;
			case "Control_e":
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$Profile$Right$img_Text1$txt_StatHead"
						+ "*GEOM*TEXT SET " + CricketFunctions.getbowlingstyle(player.getBowlingStyle()).toUpperCase() + "\0", print_writers);
				
				TitleData = new String[] {"MATCHES", "WICKETS", "ECONOMY", "3WI / 5WI", "BEST"};
				StatData = new String[] {String.valueOf(stat.getMatches()),String.valueOf(stat.getWickets()),CricketFunctions.getEconomy(stat.getRuns_conceded(), stat.getBalls_bowled(), 2, "-"),
						stat.getPlus_3() + " / " + stat.getPlus_5(),(stat.getBest_figures().contains("-") ? stat.getBest_figures() : "-")};
				break;
			}
			
			for (int i = 0; i < TitleData.length; i++) {
			    CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$Profile$Left$BatRow" + (i+1) + 
			    		"$img_Text1$txt_SubHead*GEOM*TEXT SET " + TitleData[i] + "\0", print_writers);
			    CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$Profile$Left$BatRow" + (i+1) + 
			    		"$ScoreGrp$txt_SubHead*GEOM*TEXT SET " + StatData[i] + "\0", print_writers);
			}
			break;
		case Constants.TRI_SERIES: case Constants.MT20:
			if(WhichProfile.equalsIgnoreCase("DT20")) {
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_Profile$Main$DataAll$Data$Side" + WhichSide + "$Row2$txt_Career"
						+ "*GEOM*TEXT SET " + "T20 CAREER" + "\0", print_writers);
			}else if(WhichProfile.equalsIgnoreCase("IT20")){
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_Profile$Main$DataAll$Data$Side" + WhichSide + "$Row2$txt_Career"
						+ "*GEOM*TEXT SET " + "T20I CAREER" + "\0", print_writers);
			}else if(WhichProfile.equalsIgnoreCase("ODI")) {
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_Profile$Main$DataAll$Data$Side" + WhichSide + "$Row2$txt_Career"
						+ "*GEOM*TEXT SET " + "ODI CAREER" + "\0", print_writers);
			}else if(WhichProfile.equalsIgnoreCase("Test")){
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_Profile$Main$DataAll$Data$Side" + WhichSide + "$Row2$txt_Career"
						+ "*GEOM*TEXT SET " + "TEST CAREER" + "\0", print_writers);
			}
			
			if(ImageType.equalsIgnoreCase("WITH")) {
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_Profile$Main$ImageAll$select_Image*FUNCTION*Omo*vis_con SET 1\0", print_writers);
				
				if(config.getPrimaryIpAddress().equalsIgnoreCase(Constants.LOCALHOST)) {
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_Profile$Main$ImageAll$Image$Side" + WhichSide + "$img_Player*TEXTURE*IMAGE SET "
							+ (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_LOCAL_PHOTO_PATH : Constants.MT20_LOCAL_PHOTO_PATH) + "\\\\" + team.getTeamName4() + "\\\\" + Constants.RIGHT + "\\\\" + player.getPhoto()+ CricketUtil.PNG_EXTENSION + "\0", print_writers);
				}else {
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_Profile$Main$ImageAll$Image$Side" + WhichSide + "$img_Player*TEXTURE*IMAGE SET "
							+ "\\\\" + config.getPrimaryIpAddress() + "\\\\" + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_PHOTO_PATH : Constants.MT20_PHOTO_PATH)
							+ "\\\\" + team.getTeamName4() + "\\\\" + Constants.RIGHT + "\\\\" + player.getPhoto()
							+ CricketUtil.PNG_EXTENSION + "\0", print_writers);
				}
			}else {
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_Profile$Main$ImageAll$select_Image*FUNCTION*Omo*vis_con SET 0\0", print_writers);
			}
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_Profile$Main$DataAll$Data$Side" + WhichSide + "$Row1$txt_Age"
					+ "*GEOM*TEXT SET " + "" + "\0", print_writers);
			
			switch(whatToProcess) {
			case "Control_d":
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_Profile$Main$DataAll$Data$Side" + WhichSide + "$Row1$txt_Hand"
						+ "*GEOM*TEXT SET " + CricketFunctions.getbattingstyle(player.getBattingStyle(),CricketUtil.FULL, true, false).toUpperCase() + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_Profile$Main$DataAll$Data$Side" + WhichSide + "$Row8$txt_CountryName"
						+ "*GEOM*TEXT SET " + (stat.getBest_score_against().contains("v ")?stat.getBest_score_against():"v "+stat.getBest_score_against())+"\0", print_writers);
				
				TitleData = new String[] {"MATCHES", "RUNS", "50s / 100s", "STRIKE RATE", "BEST"};
				StatData = new String[] {String.valueOf(stat.getMatches()),String.valueOf(stat.getRuns()),stat.getFifties() + " / " + stat.getHundreds(),
							CricketFunctions.generateStrikeRate(stat.getRuns(), stat.getBalls_faced(), 0),(stat.getBest_score().equalsIgnoreCase("0") ? "-" : stat.getBest_score())};
				break;
			case "Control_e":
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_Profile$Main$DataAll$Data$Side" + WhichSide + "$Row1$txt_Hand"
						+ "*GEOM*TEXT SET " + CricketFunctions.getbowlingstyle(player.getBowlingStyle()).toUpperCase() + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_Profile$Main$DataAll$Data$Side" + WhichSide + "$Row8$txt_CountryName"
						+ "*GEOM*TEXT SET " + (stat.getBest_figures_against().contains("v ")?stat.getBest_figures_against():"v "+stat.getBest_figures_against())+"\0", print_writers);
				
				TitleData = new String[] {"MATCHES", "WICKETS", "3WI / 5WI", "ECONOMY", "BEST"};
				StatData = new String[] {String.valueOf(stat.getMatches()),String.valueOf(stat.getWickets()),stat.getPlus_3() + " / " + stat.getPlus_5(),
				        CricketFunctions.getEconomy(stat.getRuns_conceded(), stat.getBalls_bowled(), 2, "-"),(stat.getBest_figures().contains("-") ? stat.getBest_figures() : "-")};
				break;
			}
			
			for (int i = 0; i < TitleData.length; i++) {
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_Profile$Main$DataAll$Data$Side" + WhichSide + "$Row" + (i+3) + 
			    		"$select_Highlight*FUNCTION*Omo*vis_con SET 0\0", print_writers);
			    CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_Profile$Main$DataAll$Data$Side" + WhichSide + "$Row" + (i+3) + 
			    		"$Dehighlight$txt_StatHead*GEOM*TEXT SET " + TitleData[i] + "\0", print_writers);
			    CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_Profile$Main$DataAll$Data$Side" + WhichSide + "$Row" + (i+3) + 
			    		"$Dehighlight$txt_StatValue*GEOM*TEXT SET " + StatData[i] + "\0", print_writers);
			}
			
			break;
		}
		return Constants.OK;
	}
	public String DoubleTeamsBody(List<PrintWriter> print_writers, String whatToProcess, int WhichSide, MatchAllData matchAllData, Configuration config) throws InterruptedException {
		
		String which_role = "";
		switch (config.getBroadcaster()) {
		case Constants.AFG_SL_SERIES:
			which_role = "";
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$select_GraphicsType"
					+ "*FUNCTION*Omo*vis_con SET 4\0", print_writers);
			
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$Teams$Team1$Grp1$TeamsData$select_DataType"
					+ "*FUNCTION*Omo*vis_con SET 0\0", print_writers);
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$Teams$Team2$Grp1$TeamsData$select_DataType"
					+ "*FUNCTION*Omo*vis_con SET 0\0", print_writers);
			
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$Teams$Team1$Grp1$TeamsData$Title$txt_TeamName"
					+ "*GEOM*TEXT SET " + matchAllData.getSetup().getHomeTeam().getTeamName1() + "\0", print_writers);
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$Teams$Team2$Grp1$TeamsData$Title$txt_TeamName"
					+ "*GEOM*TEXT SET " + matchAllData.getSetup().getAwayTeam().getTeamName1() + "\0", print_writers);
			
			
			for(int i=1;i<=2;i++) {
				rowId = 1;
				List<Player> players = (i==1?matchAllData.getSetup().getHomeSquad():matchAllData.getSetup().getAwaySquad());
				containerName = (i==1 ? "$Team1" : "$Team2");
				
				for(Player plyr : players) {
					rowId ++;
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$Teams" + containerName + "$Grp" + rowId + "$TeamsData$select_DataType"
							+ "*FUNCTION*Omo*vis_con SET 2\0", print_writers);
					if(plyr.getSurname() != null) {
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$Teams" + containerName + "$Grp" + rowId + "$TeamsData$Highlight$Name_Role$"
								+ "select_FirstName*FUNCTION*Omo*vis_con SET 1\0", print_writers);
						
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$Teams" + containerName + "$Grp" + rowId + "$TeamsData$Highlight$"
								+ "txt_FirstName*GEOM*TEXT SET " + plyr.getFirstname() + "\0", print_writers);
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$Teams" + containerName + "$Grp" + rowId + "$TeamsData$Highlight$"
								+ "txt_LastName*GEOM*TEXT SET " + plyr.getSurname() + "\0", print_writers);
					}else {
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$Teams" + containerName + "$Grp" + rowId + "$TeamsData$Highlight$Name_Role$"
								+ "select_FirstName*FUNCTION*Omo*vis_con SET 0\0", print_writers);
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$Teams" + containerName + "$Grp" + rowId + "$TeamsData$Highlight$"
								+ "txt_LastName*GEOM*TEXT SET " + plyr.getFirstname() + "\0", print_writers);
					}
					
					if(plyr.getRole().equalsIgnoreCase("BATSMAN") || plyr.getRole().equalsIgnoreCase("BATTER") || plyr.getRole().equalsIgnoreCase("BAT/KEEPER")) {
						if(plyr.getBattingStyle().equalsIgnoreCase("LHB")) {
							which_role = "Batsman_Lefthand";
						}else {
							which_role = "Bat_Icon";
						}
					}else if(plyr.getRole().equalsIgnoreCase("BOWLER")) {
						if(plyr.getBowlingStyle() == null) {
							which_role = "Bowler";
						}else {
							switch(plyr.getBowlingStyle()) {
							case "RF": case "RFM": case "RMF": case "RM": case "RSM": case "LF": case "LFM": case "LMF": case "LM":
								which_role = "FastBowler";
								break;
							case "LSL": case "WSL": case "LCH": case "RLG": case "WSR": case "LSO":
								which_role = "SpinBowler";
								break;
							case "ROB":
								which_role = "Off_Spin";
								break;
							case "RLB":
								which_role = "Leg_Spin";
								break;	
							}
						}
					}else if(plyr.getRole().equalsIgnoreCase("ALL-ROUNDER")) {
						if(plyr.getBowlingStyle() == null) {
							if(plyr.getBattingStyle().equalsIgnoreCase("LHB")) {
								which_role = "Off_Spin_Allrounder_Left";
							}else {
								which_role = "FastBowlerAllrounder";
							}
						}else {
							switch(plyr.getBowlingStyle()) {
							case "RF": case "RFM": case "RMF": case "RM": case "RSM": case "LF": case "LFM": case "LMF": case "LM":
								if(plyr.getBattingStyle().equalsIgnoreCase("LHB")) {
									which_role = "Pace_BowlerAllrounerLeftHand";
								}else {
									which_role = "FastBowlerAllrounder";
								}
								break;
							case "ROB": case "RLB": case "LSL": case "WSL": case "LCH": case "RLG": case "WSR": case "LSO":
								if(plyr.getBattingStyle().equalsIgnoreCase("LHB")) {
									which_role = "Off_Spin_Allrounder_Left";
								}else {
									which_role = "Off_Spin_Bat";
								}
								break;
							}
						}
					}
					
					if(plyr.getCaptainWicketKeeper() != null && !plyr.getCaptainWicketKeeper().isEmpty()) {
						if(plyr.getCaptainWicketKeeper().equalsIgnoreCase(CricketUtil.CAPTAIN)) {
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$Teams" + containerName + "$Grp" + rowId + 
									"$TeamsData$Highlight$Captain*ACTIVE SET 1\0", print_writers);
						}else if(plyr.getCaptainWicketKeeper().equalsIgnoreCase("CAPTAIN_WICKET_KEEPER")) {
							which_role = "Keeper";
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$Teams" + containerName + "$Grp" + rowId + 
									"$TeamsData$Highlight$Captain*ACTIVE SET 1\0", print_writers);
							
						}else if(plyr.getCaptainWicketKeeper().equalsIgnoreCase(CricketUtil.WICKET_KEEPER)) {
							which_role = "Keeper";
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$Teams" + containerName + "$Grp" + rowId + 
									"$TeamsData$Highlight$Captain*ACTIVE SET 0\0", print_writers);
						}else {
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$Teams" + containerName + "$Grp" + rowId + 
									"$TeamsData$Highlight$Captain*ACTIVE SET 0\0", print_writers);
						}
					}else {
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$Teams" + containerName + "$Grp" + rowId + 
								"$TeamsData$Highlight$Captain*ACTIVE SET 0\0", print_writers);
					}
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$Teams" + containerName + "$Grp" + rowId + 
							"$TeamsData$Highlight$RoleIcon$img_Icons*TEXTURE*IMAGE SET " + Constants.AFG_SL_SERIES_ICONS + which_role + "\0", print_writers);
				}
			}
			break;
		case Constants.BAN_AFG_SERIES: case Constants.ACC:
			which_role = "";
			
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$Select*FUNCTION*Omo*vis_con SET 8\0", print_writers);
			
			for(int i=1;i<=2;i++) {
				rowId = 0;
				List<Player> players = (i==1?matchAllData.getSetup().getHomeSquad():matchAllData.getSetup().getAwaySquad());
				containerName = (i==1 ? "$HomeTeam" : "$AwayTeam");
				
				for(Player plyr : players) {
					rowId++;
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$Lineup_Both$LineupDataALL" + containerName + "$BatRow" + rowId + 
							"$Select*FUNCTION*Omo*vis_con SET " + (!PlayerIdIn.contains(plyr.getPlayerId())?"1":"0") + "\0", print_writers);
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$Lineup_Both$LineupDataALL" + containerName + "$BatRow" + rowId + 
							"$PLAYER$txt_BatterName*GEOM*TEXT SET " + plyr.getFull_name() + "\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$Lineup_Both$LineupDataALL" + containerName + "$BatRow" + rowId + 
							"$IN$txt_BatterName*GEOM*TEXT SET " + plyr.getFull_name() + "\0", print_writers);
					
					if(plyr.getRole().equalsIgnoreCase("BATSMAN") || plyr.getRole().equalsIgnoreCase("BATTER") || plyr.getRole().equalsIgnoreCase("BAT/KEEPER")) {
						if(plyr.getBattingStyle().equalsIgnoreCase("LHB")) {
							which_role = "Batsman_Lefthand";
						}else {
							which_role = "Bat_Icon";
						}
					}else if(plyr.getRole().equalsIgnoreCase("BOWLER")) {
						if(plyr.getBowlingStyle() == null) {
							which_role = "Bowler";
						}else {
							switch(plyr.getBowlingStyle()) {
							case "RF": case "RFM": case "RMF": case "RM": case "RSM": case "LF": case "LFM": case "LMF": case "LM":
								which_role = "FastBowler";
								break;
							case "LSL": case "WSL": case "LCH": case "RLG": case "WSR": case "LSO":
								which_role = "SpinBowler";
								break;
							case "ROB":
								which_role = "Off_Spin";
								break;
							case "RLB":
								which_role = "Leg_Spin";
								break;	
							}
						}
					}else if(plyr.getRole().equalsIgnoreCase("ALL-ROUNDER")) {
						if(plyr.getBowlingStyle() == null) {
							if(plyr.getBattingStyle().equalsIgnoreCase("LHB")) {
								which_role = "Off_Spin_Allrounder_Left";
							}else {
								which_role = "FastBowlerAllrounder";
							}
						}else {
							switch(plyr.getBowlingStyle()) {
							case "RF": case "RFM": case "RMF": case "RM": case "RSM": case "LF": case "LFM": case "LMF": case "LM":
								if(plyr.getBattingStyle().equalsIgnoreCase("LHB")) {
									which_role = "Pace_BowlerAllrounerLeftHand";
								}else {
									which_role = "FastBowlerAllrounder";
								}
								break;
							case "ROB": case "RLB": case "LSL": case "WSL": case "LCH": case "RLG": case "WSR": case "LSO":
								if(plyr.getBattingStyle().equalsIgnoreCase("LHB")) {
									which_role = "Off_Spin_Allrounder_Left";
								}else {
									which_role = "Off_Spin_Bat";
								}
								break;
							}
						}
					}
					
					if(plyr.getCaptainWicketKeeper() != null && !plyr.getCaptainWicketKeeper().isEmpty()) {
						if(plyr.getCaptainWicketKeeper().equalsIgnoreCase(CricketUtil.CAPTAIN)) {
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$Lineup_Both$LineupDataALL" + containerName + "$BatRow" + rowId + 
									"$PLAYER$Captain*FUNCTION*Omo*vis_con SET 1\0", print_writers);
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$Lineup_Both$LineupDataALL" + containerName + "$BatRow" + rowId + 
									"$IN$Captain*FUNCTION*Omo*vis_con SET 1\0", print_writers);
						}else if(plyr.getCaptainWicketKeeper().equalsIgnoreCase("CAPTAIN_WICKET_KEEPER")) {
							which_role = "Keeper";
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$Lineup_Both$LineupDataALL" + containerName + "$BatRow" + rowId + 
									"$PLAYER$Captain*FUNCTION*Omo*vis_con SET 1\0", print_writers);
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$Lineup_Both$LineupDataALL" + containerName + "$BatRow" + rowId + 
									"$IN$Captain*FUNCTION*Omo*vis_con SET 1\0", print_writers);
							
						}else if(plyr.getCaptainWicketKeeper().equalsIgnoreCase(CricketUtil.WICKET_KEEPER)) {
							which_role = "Keeper";
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$Lineup_Both$LineupDataALL" + containerName + "$BatRow" + rowId + 
									"$PLAYER$Captain*FUNCTION*Omo*vis_con SET 0\0", print_writers);
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$Lineup_Both$LineupDataALL" + containerName + "$BatRow" + rowId + 
									"$IN$Captain*FUNCTION*Omo*vis_con SET 0\0", print_writers);
						}else {
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$Lineup_Both$LineupDataALL" + containerName + "$BatRow" + rowId + 
									"$PLAYER$Captain*FUNCTION*Omo*vis_con SET 0\0", print_writers);
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$Lineup_Both$LineupDataALL" + containerName + "$BatRow" + rowId + 
									"$IN$Captain*FUNCTION*Omo*vis_con SET 0\0", print_writers);
						}
					}else {
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$Lineup_Both$LineupDataALL" + containerName + "$BatRow" + rowId + 
								"$PLAYER$Captain*FUNCTION*Omo*vis_con SET 0\0", print_writers);
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$Lineup_Both$LineupDataALL" + containerName + "$BatRow" + rowId + 
								"$IN$Captain*FUNCTION*Omo*vis_con SET 0\0", print_writers);
					}
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$Lineup_Both$LineupDataALL" + containerName + "$BatRow" + rowId + 
							"$PLAYER$Role*TEXTURE*IMAGE SET " + Constants.BAN_AFG_SERIES_ICONS + which_role + "\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$Lineup_Both$LineupDataALL" + containerName + "$BatRow" + rowId + 
							"$IN$Role*TEXTURE*IMAGE SET " + Constants.BAN_AFG_SERIES_ICONS + which_role + "\0", print_writers);
				}
			}
			break;
			
		case Constants.TRI_SERIES: case Constants.MT20:
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide 
					+ "$select_GraphicsType*FUNCTION*Omo*vis_con SET 3\0", print_writers);
			
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide 
					+ "$Teams$Team_1$Rows$Title$txt_Tittle*GEOM*TEXT SET " + matchAllData.getSetup().getHomeTeam().getTeamName1() + "\0", print_writers);
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide 
					+ "$Teams$Team_2$Rows$Title$txt_Tittle*GEOM*TEXT SET " + matchAllData.getSetup().getAwayTeam().getTeamName1() + "\0", print_writers);
			
			rowId = 0;
			for(Player hs : matchAllData.getSetup().getHomeSquad()) {
				rowId++;
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide 
						+ "$Teams$Team_1$Rows$" + rowId + "$NameGrp$select_Status*FUNCTION*Omo*vis_con SET " + (!PlayerIdIn.contains(hs.getPlayerId())?"1":"0") 
						+ "\0", print_writers);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide 
						+ "$Teams$Team_1$Rows$" + rowId + "$NameGrp$txt_FirstName*GEOM*TEXT SET " + hs.getFirstname() + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide 
						+ "$Teams$Team_1$Rows$" + rowId + "$NameGrp$txt_LastName*GEOM*TEXT SET " + (hs.getSurname() != null ?hs.getSurname():"") + "\0", print_writers);
				
				if(hs.getRole().equalsIgnoreCase("Batsman") || hs.getRole().equalsIgnoreCase("Bat/Keeper")) {
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide 
							+ "$Teams$Team_1$Rows$" + rowId + "$NameGrp$txt_Description*GEOM*TEXT SET BATTER\0", print_writers);
				}else if(hs.getRole().equalsIgnoreCase("Bowler")) {
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide 
							+ "$Teams$Team_1$Rows$" + rowId + "$NameGrp$txt_Description*GEOM*TEXT SET BOWLER\0", print_writers);
				}else if(hs.getRole().equalsIgnoreCase("All-rounder")) {
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide 
							+ "$Teams$Team_1$Rows$" + rowId + "$NameGrp$txt_Description*GEOM*TEXT SET ALL-ROUNDER\0", print_writers);
				}
				
				if(hs.getRole().equalsIgnoreCase("BATSMAN") || hs.getRole().equalsIgnoreCase("BATTER") || hs.getRole().equalsIgnoreCase("BAT/KEEPER")) {
					if(hs.getBattingStyle().equalsIgnoreCase("LHB")) {
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide 
								+ "$Teams$Team_1$Rows$" + rowId + "$NameGrp$img_Icon*TEXTURE*IMAGE SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_ICONS : Constants.MT20_ICONS) 
								+ "Batsman_Lefthand" + "\0", print_writers);
						
					}else {
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide 
								+ "$Teams$Team_1$Rows$" + rowId + "$NameGrp$img_Icon*TEXTURE*IMAGE SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_ICONS : Constants.MT20_ICONS) + "Bat_Icon" + "\0", print_writers);
						
					}
				}else if(hs.getRole().equalsIgnoreCase("BOWLER")) {
					if(hs.getBowlingStyle() == null) {
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide 
								+ "$Teams$Team_1$Rows$" + rowId + "$NameGrp$img_Icon*TEXTURE*IMAGE SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_ICONS : Constants.MT20_ICONS) + "Bowler" + "\0", print_writers);
						
					}else {
						switch(hs.getBowlingStyle()) {
						case "RF": case "RFM": case "RMF": case "RM": case "RSM": case "LF": case "LFM": case "LMF": case "LM":
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide 
									+ "$Teams$Team_1$Rows$" + rowId + "$NameGrp$img_Icon*TEXTURE*IMAGE SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_ICONS : Constants.MT20_ICONS) + "FastBowler" + "\0", print_writers);
							
							break;
						case "LSL": case "WSL": case "LCH": case "RLG": case "WSR": case "LSO":
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide 
									+ "$Teams$Team_1$Rows$" + rowId + "$NameGrp$img_Icon*TEXTURE*IMAGE SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_ICONS : Constants.MT20_ICONS) + "SpinBowler" + "\0", print_writers);
							
							break;
						case "ROB":
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide 
									+ "$Teams$Team_1$Rows$" + rowId + "$NameGrp$img_Icon*TEXTURE*IMAGE SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_ICONS : Constants.MT20_ICONS) + "Off_Spin" + "\0", print_writers);
							
							break;
						case "RLB":
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide 
									+ "$Teams$Team_1$Rows$" + rowId + "$NameGrp$img_Icon*TEXTURE*IMAGE SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_ICONS : Constants.MT20_ICONS) + "Leg_Spin" + "\0", print_writers);
							
							break;	
						}
					}
				}else if(hs.getRole().equalsIgnoreCase("ALL-ROUNDER")) {
					if(hs.getBowlingStyle() == null) {
						if(hs.getBattingStyle().equalsIgnoreCase("LHB")) {
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide 
									+ "$Teams$Team_1$Rows$" + rowId + "$NameGrp$img_Icon*TEXTURE*IMAGE SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_ICONS : Constants.MT20_ICONS) + "Off_Spin_Allrounder_Left" + "\0", print_writers);
							
						}else {
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide 
									+ "$Teams$Team_1$Rows$" + rowId + "$NameGrp$img_Icon*TEXTURE*IMAGE SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_ICONS : Constants.MT20_ICONS) + "FastBowlerAllrounder" + "\0", print_writers);
							
						}
					}else {
						switch(hs.getBowlingStyle()) {
						case "RF": case "RFM": case "RMF": case "RM": case "RSM": case "LF": case "LFM": case "LMF": case "LM":
							if(hs.getBattingStyle().equalsIgnoreCase("LHB")) {
								CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide 
										+ "$Teams$Team_1$Rows$" + rowId + "$NameGrp$img_Icon*TEXTURE*IMAGE SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_ICONS : Constants.MT20_ICONS) + "Pace_BowlerAllrounerLeftHand" + "\0", print_writers);
								
							}else {
								CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide 
										+ "$Teams$Team_1$Rows$" + rowId + "$NameGrp$img_Icon*TEXTURE*IMAGE SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_ICONS : Constants.MT20_ICONS) + "FastBowlerAllrounder" + "\0", print_writers);
								
							}
							break;
						case "ROB": case "RLB": case "LSL": case "WSL": case "LCH": case "RLG": case "WSR": case "LSO":
							if(hs.getBattingStyle().equalsIgnoreCase("LHB")) {
								CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide 
										+ "$Teams$Team_1$Rows$" + rowId + "$NameGrp$img_Icon*TEXTURE*IMAGE SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_ICONS : Constants.MT20_ICONS) + "Off_Spin_Allrounder_Left" + "\0", print_writers);
								
							}else {
								CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide 
										+ "$Teams$Team_1$Rows$" + rowId + "$NameGrp$img_Icon*TEXTURE*IMAGE SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_ICONS : Constants.MT20_ICONS) + "Off_Spin_Bat" + "\0", print_writers);
								
							}
							break;
						}
					}
				}
				
				if(hs.getCaptainWicketKeeper() != null && !hs.getCaptainWicketKeeper().isEmpty()) {
					if(hs.getCaptainWicketKeeper().equalsIgnoreCase(CricketUtil.CAPTAIN)) {
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide 
								+ "$Teams$Team_1$Rows$" + rowId + "$NameGrp$txt_Captain*GEOM*TEXT SET (C)\0", print_writers);
					}else if(hs.getCaptainWicketKeeper().equalsIgnoreCase("CAPTAIN_WICKET_KEEPER")) {
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide 
								+ "$Teams$Team_1$Rows$" + rowId + "$NameGrp$txt_Captain*GEOM*TEXT SET (C)\0", print_writers);
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide 
								+ "$Teams$Team_1$Rows$" + rowId + "$NameGrp$txt_Description*GEOM*TEXT SET KEEPER\0", print_writers);
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide 
								+ "$Teams$Team_1$Rows$" + rowId + "$NameGrp$img_Icon*TEXTURE*IMAGE SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_ICONS : Constants.MT20_ICONS) + "Keeper" + "\0", print_writers);
						
					}else if(hs.getCaptainWicketKeeper().equalsIgnoreCase(CricketUtil.WICKET_KEEPER)) {
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide 
								+ "$Teams$Team_1$Rows$" + rowId + "$NameGrp$txt_Captain*GEOM*TEXT SET \0", print_writers);
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide 
								+ "$Teams$Team_1$Rows$" + rowId + "$NameGrp$txt_Description*GEOM*TEXT SET KEEPER\0", print_writers);
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide 
								+ "$Teams$Team_1$Rows$" + rowId + "$NameGrp$img_Icon*TEXTURE*IMAGE SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_ICONS : Constants.MT20_ICONS) + "Keeper" + "\0", print_writers);
						
					}else {
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide 
								+ "$Teams$Team_1$Rows$" + rowId + "$NameGrp$txt_Captain*GEOM*TEXT SET \0", print_writers);
					}
				}else {
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide 
							+ "$Teams$Team_1$Rows$" + rowId + "$NameGrp$txt_Captain*GEOM*TEXT SET \0", print_writers);
				}
			}
			
			rowId = 0;
			for(Player as : matchAllData.getSetup().getAwaySquad()) {
				rowId++;
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide 
						+ "$Teams$Team_2$Rows$" + rowId + "$NameGrp$select_Status*FUNCTION*Omo*vis_con SET " + (!PlayerIdIn.contains(as.getPlayerId())?"1":"0") 
						+ "\0", print_writers);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide 
						+ "$Teams$Team_2$Rows$" + rowId + "$NameGrp$txt_FirstName*GEOM*TEXT SET " + as.getFirstname() + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide 
						+ "$Teams$Team_2$Rows$" + rowId + "$NameGrp$txt_LastName*GEOM*TEXT SET " + (as.getSurname() != null ?as.getSurname():"") + "\0", print_writers);
				
				if(as.getRole().equalsIgnoreCase("Batsman") || as.getRole().equalsIgnoreCase("Bat/Keeper")) {
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide 
							+ "$Teams$Team_2$Rows$" + rowId + "$NameGrp$txt_Description*GEOM*TEXT SET BATTER\0", print_writers);
				}else if(as.getRole().equalsIgnoreCase("Bowler")) {
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide 
							+ "$Teams$Team_2$Rows$" + rowId + "$NameGrp$txt_Description*GEOM*TEXT SET BOWLER\0", print_writers);
				}else if(as.getRole().equalsIgnoreCase("All-rounder")) {
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide 
							+ "$Teams$Team_2$Rows$" + rowId + "$NameGrp$txt_Description*GEOM*TEXT SET ALL-ROUNDER\0", print_writers);
				}
				
				if(as.getRole().equalsIgnoreCase("BATSMAN") || as.getRole().equalsIgnoreCase("BATTER") || as.getRole().equalsIgnoreCase("BAT/KEEPER")) {
					if(as.getBattingStyle().equalsIgnoreCase("LHB")) {
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide 
								+ "$Teams$Team_2$Rows$" + rowId + "$NameGrp$img_Icon*TEXTURE*IMAGE SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_ICONS : Constants.MT20_ICONS) + "Batsman_Lefthand" + "\0", print_writers);
						
					}else {
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide 
								+ "$Teams$Team_2$Rows$" + rowId + "$NameGrp$img_Icon*TEXTURE*IMAGE SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_ICONS : Constants.MT20_ICONS) + "Bat_Icon" + "\0", print_writers);
						
					}
				}else if(as.getRole().equalsIgnoreCase("BOWLER")) {
					if(as.getBowlingStyle() == null) {
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide 
								+ "$Teams$Team_2$Rows$" + rowId + "$NameGrp$img_Icon*TEXTURE*IMAGE SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_ICONS : Constants.MT20_ICONS) + "Bowler" + "\0", print_writers);
						
					}else {
						switch(as.getBowlingStyle()) {
						case "RF": case "RFM": case "RMF": case "RM": case "RSM": case "LF": case "LFM": case "LMF": case "LM":
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide 
									+ "$Teams$Team_2$Rows$" + rowId + "$NameGrp$img_Icon*TEXTURE*IMAGE SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_ICONS : Constants.MT20_ICONS) + "FastBowler" + "\0", print_writers);
							
							break;
						case "LSL": case "WSL": case "LCH": case "RLG": case "WSR": case "LSO":
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide 
									+ "$Teams$Team_2$Rows$" + rowId + "$NameGrp$img_Icon*TEXTURE*IMAGE SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_ICONS : Constants.MT20_ICONS) + "SpinBowler" + "\0", print_writers);
							
							break;
						case "ROB":
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide 
									+ "$Teams$Team_2$Rows$" + rowId + "$NameGrp$img_Icon*TEXTURE*IMAGE SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_ICONS : Constants.MT20_ICONS) + "Off_Spin" + "\0", print_writers);
							
							break;
						case "RLB":
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide 
									+ "$Teams$Team_2$Rows$" + rowId + "$NameGrp$img_Icon*TEXTURE*IMAGE SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_ICONS : Constants.MT20_ICONS) + "Leg_Spin" + "\0", print_writers);
							
							break;	
						}
					}
				}else if(as.getRole().equalsIgnoreCase("ALL-ROUNDER")) {
					if(as.getBowlingStyle() == null) {
						if(as.getBattingStyle().equalsIgnoreCase("LHB")) {
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide 
									+ "$Teams$Team_2$Rows$" + rowId + "$NameGrp$img_Icon*TEXTURE*IMAGE SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_ICONS : Constants.MT20_ICONS) + "Off_Spin_Allrounder_Left" + "\0", print_writers);
							
						}else {
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide 
									+ "$Teams$Team_2$Rows$" + rowId + "$NameGrp$img_Icon*TEXTURE*IMAGE SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_ICONS : Constants.MT20_ICONS) + "FastBowlerAllrounder" + "\0", print_writers);
							
						}
					}else {
						switch(as.getBowlingStyle()) {
						case "RF": case "RFM": case "RMF": case "RM": case "RSM": case "LF": case "LFM": case "LMF": case "LM":
							if(as.getBattingStyle().equalsIgnoreCase("LHB")) {
								CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide 
										+ "$Teams$Team_2$Rows$" + rowId + "$NameGrp$img_Icon*TEXTURE*IMAGE SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_ICONS : Constants.MT20_ICONS) + "Pace_BowlerAllrounerLeftHand" + "\0", print_writers);
								
							}else {
								CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide 
										+ "$Teams$Team_2$Rows$" + rowId + "$NameGrp$img_Icon*TEXTURE*IMAGE SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_ICONS : Constants.MT20_ICONS) + "FastBowlerAllrounder" + "\0", print_writers);
								
							}
							break;
						case "ROB": case "RLB": case "LSL": case "WSL": case "LCH": case "RLG": case "WSR": case "LSO":
							if(as.getBattingStyle().equalsIgnoreCase("LHB")) {
								CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide 
										+ "$Teams$Team_2$Rows$" + rowId + "$NameGrp$img_Icon*TEXTURE*IMAGE SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_ICONS : Constants.MT20_ICONS) + "Off_Spin_Allrounder_Left" + "\0", print_writers);
								
							}else {
								CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide 
										+ "$Teams$Team_2$Rows$" + rowId + "$NameGrp$img_Icon*TEXTURE*IMAGE SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_ICONS : Constants.MT20_ICONS) + "Off_Spin_Bat" + "\0", print_writers);
								
							}
							break;
						}
					}
				}
				
				if(as.getCaptainWicketKeeper() != null && !as.getCaptainWicketKeeper().isEmpty()) {
					if(as.getCaptainWicketKeeper().equalsIgnoreCase(CricketUtil.CAPTAIN)) {
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide 
								+ "$Teams$Team_2$Rows$" + rowId + "$NameGrp$txt_Captain*GEOM*TEXT SET (C)\0", print_writers);
					}else if(as.getCaptainWicketKeeper().equalsIgnoreCase("CAPTAIN_WICKET_KEEPER")) {
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide 
								+ "$Teams$Team_2$Rows$" + rowId + "$NameGrp$txt_Captain*GEOM*TEXT SET (C)\0", print_writers);
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide 
								+ "$Teams$Team_2$Rows$" + rowId + "$NameGrp$txt_Description*GEOM*TEXT SET KEEPER\0", print_writers);
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide 
								+ "$Teams$Team_2$Rows$" + rowId + "$NameGrp$img_Icon*TEXTURE*IMAGE SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_ICONS : Constants.MT20_ICONS) + "Keeper" + "\0", print_writers);
						
					}else if(as.getCaptainWicketKeeper().equalsIgnoreCase(CricketUtil.WICKET_KEEPER)) {
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide 
								+ "$Teams$Team_2$Rows$" + rowId + "$NameGrp$txt_Captain*GEOM*TEXT SET \0", print_writers);
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide 
								+ "$Teams$Team_2$Rows$" + rowId + "$NameGrp$txt_Description*GEOM*TEXT SET KEEPER\0", print_writers);
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide 
								+ "$Teams$Team_2$Rows$" + rowId + "$NameGrp$img_Icon*TEXTURE*IMAGE SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_ICONS : Constants.MT20_ICONS) + "Keeper" + "\0", print_writers);
						
					}else {
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide 
								+ "$Teams$Team_2$Rows$" + rowId + "$NameGrp$txt_Captain*GEOM*TEXT SET \0", print_writers);
					}
				}else {
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide 
							+ "$Teams$Team_2$Rows$" + rowId + "$NameGrp$txt_Captain*GEOM*TEXT SET \0", print_writers);
				}
			}
			
			
			break;
		}
		return Constants.OK;
	}
	public String TargetBody(List<PrintWriter> print_writers,int WhichSide,Configuration config, MatchAllData matchAllData,Inning inning) {
		
		switch (config.getBroadcaster()) {
		case Constants.AFG_SL_SERIES:
			homeTeamCaptain = matchAllData.getSetup().getHomeSquad().stream().filter(plyr -> plyr.getCaptainWicketKeeper() != null && 
						(plyr.getCaptainWicketKeeper().equalsIgnoreCase(CricketUtil.CAPTAIN) || 
								plyr.getCaptainWicketKeeper().equalsIgnoreCase("CAPTAIN_WICKET_KEEPER"))).findAny().orElse(null);
			awayTeamCaptain = matchAllData.getSetup().getAwaySquad().stream().filter(plyr -> plyr.getCaptainWicketKeeper() != null && 
					(plyr.getCaptainWicketKeeper().equalsIgnoreCase(CricketUtil.CAPTAIN) || 
							plyr.getCaptainWicketKeeper().equalsIgnoreCase("CAPTAIN_WICKET_KEEPER"))).findAny().orElse(null);
			
			if(config.getPrimaryIpAddress().equalsIgnoreCase(Constants.LOCALHOST)) {
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_Target$ImageGrp$Team1$img_Player*TEXTURE*IMAGE SET " + 
						Constants.AFG_SL_SERIES_LOCAL_PHOTO_PATH + matchAllData.getSetup().getHomeTeam().getTeamName4() + "\\\\" + 
						Constants.LEFT + "\\\\" + homeTeamCaptain.getPhoto() + CricketUtil.PNG_EXTENSION + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_Target$ImageGrp$Team1$img_PlayerShadow*TEXTURE*IMAGE SET " + 
						Constants.AFG_SL_SERIES_LOCAL_PHOTO_PATH + matchAllData.getSetup().getHomeTeam().getTeamName4() + "\\\\" + 
						Constants.LEFT + "\\\\" + homeTeamCaptain.getPhoto() + CricketUtil.PNG_EXTENSION + "\0", print_writers);
			}else {
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_Target$ImageGrp$Team1$img_Player*TEXTURE*IMAGE SET " + "\\\\" 
						+ config.getPrimaryIpAddress() + Constants.AFG_SL_SERIES_PHOTO_PATH + matchAllData.getSetup().getHomeTeam().getTeamName4() 
						+ "\\\\" + Constants.LEFT + "\\\\" + homeTeamCaptain.getPhoto() + CricketUtil.PNG_EXTENSION + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_Target$ImageGrp$Team1$img_PlayerShadow*TEXTURE*IMAGE SET " + "\\\\" 
						+ config.getPrimaryIpAddress() + Constants.AFG_SL_SERIES_PHOTO_PATH + matchAllData.getSetup().getHomeTeam().getTeamName4() 
						+ "\\\\" + Constants.LEFT + "\\\\" + homeTeamCaptain.getPhoto() + CricketUtil.PNG_EXTENSION + "\0", print_writers);
			}
			
			if(config.getPrimaryIpAddress().equalsIgnoreCase(Constants.LOCALHOST)) {
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_Target$ImageGrp$Team2$img_Player*TEXTURE*IMAGE SET " + 
						Constants.AFG_SL_SERIES_LOCAL_PHOTO_PATH + matchAllData.getSetup().getAwayTeam().getTeamName4() + "\\\\" + 
						Constants.RIGHT + "\\\\" + awayTeamCaptain.getPhoto() + CricketUtil.PNG_EXTENSION + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_Target$ImageGrp$Team2$img_PlayerShadow*TEXTURE*IMAGE SET " + 
						Constants.AFG_SL_SERIES_LOCAL_PHOTO_PATH + matchAllData.getSetup().getAwayTeam().getTeamName4() + "\\\\" + 
						Constants.RIGHT + "\\\\" + awayTeamCaptain.getPhoto() + CricketUtil.PNG_EXTENSION + "\0", print_writers);
			}else {
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_Target$ImageGrp$Team2$img_Player*TEXTURE*IMAGE SET " + "\\\\" 
						+ config.getPrimaryIpAddress() + Constants.AFG_SL_SERIES_PHOTO_PATH + matchAllData.getSetup().getAwayTeam().getTeamName4() 
						+ "\\\\" + Constants.RIGHT + "\\\\" + awayTeamCaptain.getPhoto() + CricketUtil.PNG_EXTENSION + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_Target$ImageGrp$Team2$img_PlayerShadow*TEXTURE*IMAGE SET " + "\\\\" 
						+ config.getPrimaryIpAddress() + Constants.AFG_SL_SERIES_PHOTO_PATH + matchAllData.getSetup().getAwayTeam().getTeamName4() 
						+ "\\\\" + Constants.RIGHT + "\\\\" + awayTeamCaptain.getPhoto() + CricketUtil.PNG_EXTENSION + "\0", print_writers);
			}
			
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_Target$TargetData$Data$txt_TeamName*GEOM*TEXT SET " 
					+ inning.getBatting_team().getTeamName1() + "\0", print_writers);
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_Target$TargetData$Data$txt_Runs*GEOM*TEXT SET " 
					+ CricketFunctions.GetTargetData(matchAllData).getTargetRuns() + "\0", print_writers);
			
			if(matchAllData.getSetup().getMatchType().equalsIgnoreCase(CricketUtil.SUPER_OVER)) {
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_Target$TargetData$info1$txt_Info1*GEOM*TEXT SET " 
						+ "FROM " + (matchAllData.getSetup().getMaxOvers()*6) + " BALLS" + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_Target$TargetData$Info2$txt_Info1*GEOM*TEXT SET \0", print_writers);
			}else {
				if(matchAllData.getSetup().getTargetOvers() != null && matchAllData.getSetup().getTargetRuns() != 0) {
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_Target$TargetData$info1$txt_Info1*GEOM*TEXT SET " 
							+ "FROM " + CricketFunctions.GetTargetData(matchAllData).getTargetOvers() + " OVERS" + "\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_Target$TargetData$Info2$txt_Info1*GEOM*TEXT SET " + "@ " 
							+ CricketFunctions.generateRunRate(CricketFunctions.GetTargetData(matchAllData).getTargetRuns(), 
								Integer.valueOf(CricketFunctions.GetTargetData(matchAllData).getTargetOvers().split("\\.")[0]),
								(Integer.valueOf(CricketFunctions.GetTargetData(matchAllData).getTargetOvers().split("\\.").length >1 ? 
								Integer.valueOf(CricketFunctions.GetTargetData(matchAllData).getTargetOvers().split("\\.")[1]):0)), 2, matchAllData) 
							+ " RPO" + (matchAllData.getSetup().getTargetType() != null && !matchAllData.getSetup().getTargetType().isEmpty()
							? "("+matchAllData.getSetup().getTargetType().toUpperCase()+")": "") + "\0", print_writers);
				}else {
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_Target$TargetData$info1$txt_Info1*GEOM*TEXT SET " 
							+ "FROM " + CricketFunctions.GetTargetData(matchAllData).getTargetOvers() + " OVERS" + "\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_Target$TargetData$Info2$txt_Info1*GEOM*TEXT SET " + "@ " 
							+ CricketFunctions.generateRunRate(CricketFunctions.GetTargetData(matchAllData).getTargetRuns(),
							Integer.valueOf(CricketFunctions.GetTargetData(matchAllData).getTargetOvers()), 0, 2, matchAllData) + " RPO" + "\0", print_writers);
				}
			}
			
			break;
		case Constants.BAN_AFG_SERIES: case Constants.ACC:
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$Select*FUNCTION*Omo*vis_con SET 11\0", print_writers);
			
			for(Player plyr : matchAllData.getSetup().getHomeSquad()) {
				if(plyr.getCaptainWicketKeeper() != null && (plyr.getCaptainWicketKeeper().equalsIgnoreCase(CricketUtil.CAPTAIN) || 
						plyr.getCaptainWicketKeeper().equalsIgnoreCase("CAPTAIN_WICKET_KEEPER"))) {
					
					switch (config.getBroadcaster()) {
					case Constants.BAN_AFG_SERIES:
						if(config.getPrimaryIpAddress().equalsIgnoreCase(Constants.LOCALHOST)) {
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$Target$PlayerImageOut$PlayerImageGrp1$img_Player*TEXTURE*IMAGE SET " + 
									Constants.BAN_AFG_SERIES_LOCAL_PHOTO_PATH + matchAllData.getSetup().getHomeTeam().getTeamName4() + "\\\\" + Constants.CENTER + "\\\\" + plyr.getPhoto() + 
									CricketUtil.PNG_EXTENSION + "\0", print_writers);
							break;
						}else {
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$Target$PlayerImageOut$PlayerImageGrp1$img_Player*TEXTURE*IMAGE SET " + "\\\\" 
									+ config.getPrimaryIpAddress() + Constants.BAN_AFG_SERIES_PHOTO_PATH + matchAllData.getSetup().getHomeTeam().getTeamName4() + "\\\\" + Constants.CENTER + "\\\\" + plyr.getPhoto() 
									+ CricketUtil.PNG_EXTENSION + "\0", print_writers);
							break;
						}
					default:
						if(config.getPrimaryIpAddress().equalsIgnoreCase(Constants.LOCALHOST)) {
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$Target$PlayerImageOut$PlayerImageGrp1$img_Player*TEXTURE*IMAGE SET " + 
									Constants.ACC_LOCAL_PHOTO_PATH + matchAllData.getSetup().getHomeTeam().getTeamBadge() + "\\\\" + Constants.CENTER + "\\\\" + plyr.getPhoto() + 
									CricketUtil.PNG_EXTENSION + "\0", print_writers);
							break;
						}else {
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$Target$PlayerImageOut$PlayerImageGrp1$img_Player*TEXTURE*IMAGE SET " + "\\\\" 
									+ config.getPrimaryIpAddress() + Constants.ACC_PHOTO_PATH + matchAllData.getSetup().getHomeTeam().getTeamBadge() + "\\\\" + Constants.CENTER + "\\\\" + plyr.getPhoto() 
									+ CricketUtil.PNG_EXTENSION + "\0", print_writers);
							break;
						}
					}
					
				}
			}
			
			for(Player plyr : matchAllData.getSetup().getAwaySquad()) {
				if(plyr.getCaptainWicketKeeper() != null && (plyr.getCaptainWicketKeeper().equalsIgnoreCase(CricketUtil.CAPTAIN) || 
						plyr.getCaptainWicketKeeper().equalsIgnoreCase("CAPTAIN_WICKET_KEEPER"))) {
					
					switch (config.getBroadcaster()) {
					case Constants.BAN_AFG_SERIES:
						if(config.getPrimaryIpAddress().equalsIgnoreCase(Constants.LOCALHOST)) {
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$Target$PlayerImageOut$PlayerImageGrp2$img_Player*TEXTURE*IMAGE SET " + 
									Constants.BAN_AFG_SERIES_LOCAL_PHOTO_PATH + matchAllData.getSetup().getAwayTeam().getTeamName4() + "\\\\" + Constants.CENTER + "\\\\" + plyr.getPhoto() + 
									CricketUtil.PNG_EXTENSION + "\0", print_writers);
							break;
						}else {
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$Target$PlayerImageOut$PlayerImageGrp2$img_Player*TEXTURE*IMAGE SET " + "\\\\" 
									+ config.getPrimaryIpAddress() + Constants.BAN_AFG_SERIES_PHOTO_PATH + matchAllData.getSetup().getAwayTeam().getTeamName4() + "\\\\" + Constants.CENTER + "\\\\" + plyr.getPhoto() 
									+ CricketUtil.PNG_EXTENSION + "\0", print_writers);
							break;
						}
					default:
						if(config.getPrimaryIpAddress().equalsIgnoreCase(Constants.LOCALHOST)) {
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$Target$PlayerImageOut$PlayerImageGrp2$img_Player*TEXTURE*IMAGE SET " + 
									Constants.ACC_LOCAL_PHOTO_PATH + matchAllData.getSetup().getAwayTeam().getTeamBadge() + "\\\\" + Constants.CENTER + "\\\\" + plyr.getPhoto() + 
									CricketUtil.PNG_EXTENSION + "\0", print_writers);
							break;
						}else {
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$Target$PlayerImageOut$PlayerImageGrp2$img_Player*TEXTURE*IMAGE SET " + "\\\\" 
									+ config.getPrimaryIpAddress() + Constants.ACC_PHOTO_PATH + matchAllData.getSetup().getAwayTeam().getTeamBadge() + "\\\\" + Constants.CENTER + "\\\\" + plyr.getPhoto() 
									+ CricketUtil.PNG_EXTENSION + "\0", print_writers);
							break;
						}
					}
				}
			}
			
			if(inning.getInningNumber() == 1) {
				if(inning.getBowling_team().getTeamName1().equalsIgnoreCase("PAKISTAN SHAHEENS")) {
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$Target$SubHeadGrp$txt_TeamName*GEOM*TEXT SET " 
							+ "SHAHEENS" + "\0", print_writers);
				}else {
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$Target$SubHeadGrp$txt_TeamName*GEOM*TEXT SET " 
							+ inning.getBowling_team().getTeamName1() + "\0", print_writers);
				}
			}else if(inning.getInningNumber() == 2) {
				if(inning.getBatting_team().getTeamName1().equalsIgnoreCase("PAKISTAN SHAHEENS")) {
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$Target$SubHeadGrp$txt_TeamName*GEOM*TEXT SET "
							+ "SHAHEENS" + "\0", print_writers);
				}else {
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$Target$SubHeadGrp$txt_TeamName*GEOM*TEXT SET "
							+ inning.getBatting_team().getTeamName1() + "\0", print_writers);
				}
			}
			
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$Target$TargetHeadGrp$txt_Target*GEOM*TEXT SET TARGET\0", print_writers);
			
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$Target$SubHeadGrp$txt_Need*GEOM*TEXT SET NEED\0", print_writers);
			
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$Target$ScoreGrp$txt_Runs*GEOM*TEXT SET " + 
					CricketFunctions.GetTargetData(matchAllData).getTargetRuns() + "\0", print_writers);
			
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$Target$ScoreGrp$txt_RunsText*GEOM*TEXT SET RUNS\0", print_writers);
			
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$Target$OversGrp$txt_From*GEOM*TEXT SET FROM\0", print_writers);
			
			if(matchAllData.getSetup().getMatchType().equalsIgnoreCase(CricketUtil.SUPER_OVER) && matchAllData.getSetup().getMaxOvers() == 1) {
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$Target$OversGrp$txt_Overs*GEOM*TEXT SET " + 
			(matchAllData.getSetup().getMaxOvers()*6) + " BALLS" + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$Target$RunRateGrp$txt_Overs*GEOM*TEXT SET \0", print_writers);
			}else {
				if(matchAllData.getSetup().getTargetOvers() != null && matchAllData.getSetup().getTargetRuns() != 0) {
					if(matchAllData.getSetup().getTargetType() != null && !matchAllData.getSetup().getTargetType().isEmpty()) {
						
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$Target$OversGrp$txt_Overs*GEOM*TEXT SET " + 
								CricketFunctions.GetTargetData(matchAllData).getTargetOvers() + " OVERS (" + matchAllData.getSetup().getTargetType().toUpperCase() + ")" + "\0", print_writers);
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$Target$RunRateGrp$txt_Overs*GEOM*TEXT SET " + 
								"@ " + CricketFunctions.generateRunRate(CricketFunctions.GetTargetData(matchAllData).getTargetRuns(), 
								Integer.valueOf(CricketFunctions.GetTargetData(matchAllData).getTargetOvers().split("\\.")[0]),
								(Integer.valueOf(CricketFunctions.GetTargetData(matchAllData).getTargetOvers().split("\\.").length >1 ? 
								Integer.valueOf(CricketFunctions.GetTargetData(matchAllData).getTargetOvers().split("\\.")[1]):0)), 2, matchAllData) + " RPO" + "\0", print_writers);
					}else {
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$Target$OversGrp$txt_Overs*GEOM*TEXT SET " + 
								CricketFunctions.GetTargetData(matchAllData).getTargetOvers() + " OVERS" + "\0", print_writers);
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$Target$RunRateGrp$txt_Overs*GEOM*TEXT SET " + 
								"@ " + CricketFunctions.generateRunRate(CricketFunctions.GetTargetData(matchAllData).getTargetRuns(), 
										Integer.valueOf(CricketFunctions.GetTargetData(matchAllData).getTargetOvers().split("\\.")[0]),
								(Integer.valueOf(CricketFunctions.GetTargetData(matchAllData).getTargetOvers().split("\\.").length >1 ? 
								Integer.valueOf(CricketFunctions.GetTargetData(matchAllData).getTargetOvers().split("\\.")[1]):0)), 2, matchAllData) + " RPO" + "\0", print_writers);
					}
				}else {
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$Target$OversGrp$txt_Overs*GEOM*TEXT SET " + 
							CricketFunctions.GetTargetData(matchAllData).getTargetOvers() + " OVERS" + "\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$Target$RunRateGrp$txt_Overs*GEOM*TEXT SET " + 
							"@ " + CricketFunctions.generateRunRate(CricketFunctions.GetTargetData(matchAllData).getTargetRuns(),
							Integer.valueOf(CricketFunctions.GetTargetData(matchAllData).getTargetOvers()), 0, 2, matchAllData) + " RPO" + "\0", print_writers);
				}
			}
			break;
		case Constants.TRI_SERIES: case Constants.MT20:
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_Target$TopTitleGrp$txt_Title"
					+ "*GEOM*TEXT SET " + matchAllData.getSetup().getTournament() + "\0", print_writers);
			
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_Target$TeamLogo$Team_1$img_LogoShadow*TEXTURE*IMAGE SET "
					+ (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_LOGO : Constants.MT20_LOGO) + matchAllData.getSetup().getHomeTeam().getTeamBadge() + "\0", print_writers);
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_Target$TeamLogo$Team_1$img_Logo*TEXTURE*IMAGE SET "
					+ (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_LOGO : Constants.MT20_LOGO) + matchAllData.getSetup().getHomeTeam().getTeamBadge() + "\0", print_writers);
			
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_Target$TeamLogo$Team_2$img_LogoShadow*TEXTURE*IMAGE SET "
					+ (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_LOGO : Constants.MT20_LOGO) + matchAllData.getSetup().getAwayTeam().getTeamBadge() + "\0", print_writers);
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_Target$TeamLogo$Team_2$img_Logo*TEXTURE*IMAGE SET "
					+ (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_LOGO : Constants.MT20_LOGO) + matchAllData.getSetup().getAwayTeam().getTeamBadge() + "\0", print_writers);
			
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_Target$Big_Logo$Team1$img_Logos_BW*TEXTURE*IMAGE SET "
					+ (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_LOGO_BW : Constants.MT20_LOGO_BW) + matchAllData.getSetup().getHomeTeam().getTeamBadge() + "\0", print_writers);
			
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_Target$Big_Logo$Team_2$img_Logos_BW*TEXTURE*IMAGE SET "
					+ (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_LOGO_BW : Constants.MT20_LOGO_BW) + matchAllData.getSetup().getAwayTeam().getTeamBadge() + "\0", print_writers);
			
			
			for(Player plyr : matchAllData.getSetup().getHomeSquad()) {
				if(plyr.getCaptainWicketKeeper() != null) {
					if(plyr.getCaptainWicketKeeper().equalsIgnoreCase(CricketUtil.CAPTAIN) || plyr.getCaptainWicketKeeper().equalsIgnoreCase("CAPTAIN_WICKET_KEEPER")) {
						
						if(config.getPrimaryIpAddress().equalsIgnoreCase(Constants.LOCALHOST)) {
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_Target$PlayerImage$Team1$img_PlayerShadow*TEXTURE*IMAGE SET "
									+ (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_LOCAL_PHOTO_PATH : Constants.MT20_LOCAL_PHOTO_PATH)
									+ matchAllData.getSetup().getHomeTeam().getTeamName4() + "\\\\" + Constants.LEFT + "\\\\" + plyr.getPhoto()+CricketUtil.PNG_EXTENSION + "\0", print_writers);
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_Target$PlayerImage$Team1$img_Player*TEXTURE*IMAGE SET "
									+ (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_LOCAL_PHOTO_PATH : Constants.MT20_LOCAL_PHOTO_PATH) + matchAllData.getSetup().getHomeTeam().getTeamName4() + "\\\\" + Constants.LEFT + "\\\\" + plyr.getPhoto()+CricketUtil.PNG_EXTENSION + "\0", print_writers);
							break;
						}else {
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_Target$PlayerImage$Team1$img_PlayerShadow*TEXTURE*IMAGE SET "
									+ "\\\\" + config.getPrimaryIpAddress() + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_PHOTO_PATH : Constants.MT20_PHOTO_PATH) + matchAllData.getSetup().getHomeTeam().getTeamName4() + 
									"\\\\" + Constants.LEFT + "\\\\" + plyr.getPhoto() + CricketUtil.PNG_EXTENSION+ "\0", print_writers);
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_Target$PlayerImage$Team1$img_Player*TEXTURE*IMAGE SET "
									+ "\\\\" + config.getPrimaryIpAddress() + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_PHOTO_PATH : Constants.MT20_PHOTO_PATH) + matchAllData.getSetup().getHomeTeam().getTeamName4() + 
									"\\\\" + Constants.LEFT + "\\\\" + plyr.getPhoto() + CricketUtil.PNG_EXTENSION + "\0", print_writers);
							break;
						}
					}
				}
			}
			
			for(Player plyr : matchAllData.getSetup().getAwaySquad()) {
				if(plyr.getCaptainWicketKeeper() != null) {
					if(plyr.getCaptainWicketKeeper().equalsIgnoreCase(CricketUtil.CAPTAIN) || plyr.getCaptainWicketKeeper().equalsIgnoreCase("CAPTAIN_WICKET_KEEPER")) {
						
						if(config.getPrimaryIpAddress().equalsIgnoreCase(Constants.LOCALHOST)) {
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_Target$PlayerImage$Team2$img_PlayerShadow*TEXTURE*IMAGE SET "
									+ (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_LOCAL_PHOTO_PATH : Constants.MT20_LOCAL_PHOTO_PATH) + matchAllData.getSetup().getAwayTeam().getTeamName4() + "\\\\" + Constants.RIGHT + "\\\\" + plyr.getPhoto()+CricketUtil.PNG_EXTENSION + "\0", print_writers);
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_Target$PlayerImage$Team2$img_Player*TEXTURE*IMAGE SET "
									+ (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_LOCAL_PHOTO_PATH : Constants.MT20_LOCAL_PHOTO_PATH) + matchAllData.getSetup().getAwayTeam().getTeamName4() + "\\\\" + Constants.RIGHT + "\\\\" + plyr.getPhoto()+CricketUtil.PNG_EXTENSION + "\0", print_writers);
							break;
						}else {
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_Target$PlayerImage$Team2$img_PlayerShadow*TEXTURE*IMAGE SET "
									+ "\\\\" + config.getPrimaryIpAddress() + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_PHOTO_PATH : Constants.MT20_PHOTO_PATH) + matchAllData.getSetup().getAwayTeam().getTeamName4() + 
									"\\\\" + Constants.RIGHT + "\\\\" + plyr.getPhoto() + CricketUtil.PNG_EXTENSION+ "\0", print_writers);
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_Target$PlayerImage$Team2$img_Player*TEXTURE*IMAGE SET "
									+ "\\\\" + config.getPrimaryIpAddress() + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_PHOTO_PATH : Constants.MT20_PHOTO_PATH) + matchAllData.getSetup().getAwayTeam().getTeamName4() + 
									"\\\\" + Constants.RIGHT + "\\\\" + plyr.getPhoto() + CricketUtil.PNG_EXTENSION + "\0", print_writers);
							break;
						}
					}
				}
			}
			
			if(inning.getInningNumber() == 1) {
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_Target$Data$txt_TeamName*GEOM*TEXT SET "+ inning.getBowling_team().getTeamName1() + "\0", print_writers);
			}else if(inning.getInningNumber() == 2) {
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_Target$Data$txt_TeamName*GEOM*TEXT SET "+ inning.getBatting_team().getTeamName1() + "\0", print_writers);
			}
			
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_Target$Data$txt_text1*GEOM*TEXT SET NEED\0", print_writers);
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_Target$Data$txt_Runs*GEOM*TEXT SET "+CricketFunctions.GetTargetData(matchAllData).getTargetRuns()+"\0", print_writers);
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_Target$Data$txt_text2*GEOM*TEXT SET TO WIN\0", print_writers);
			
			if(matchAllData.getSetup().getMatchType().equalsIgnoreCase(CricketUtil.SUPER_OVER) && matchAllData.getSetup().getMaxOvers() == 1) {
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_Target$info1$InfoAll1$txt_Info1*GEOM*TEXT SET " + "FROM " + 
						(matchAllData.getSetup().getMaxOvers()*6) + " BALLS" + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_Target$Info2$txt_Info1*GEOM*TEXT SET \0", print_writers);
			}else {
				if(matchAllData.getSetup().getTargetOvers() != null && matchAllData.getSetup().getTargetRuns() != 0) {
					if(matchAllData.getSetup().getTargetType().toUpperCase().equalsIgnoreCase(CricketUtil.VJD) || 
							matchAllData.getSetup().getTargetType().toUpperCase().equalsIgnoreCase(CricketUtil.DLS)) {
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_Target$info1$InfoAll1$txt_Info1*GEOM*TEXT SET " + "FROM " + 
								CricketFunctions.GetTargetData(matchAllData).getTargetOvers() + " OVERS" + "\0", print_writers);
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_Target$Info2$txt_Info1*GEOM*TEXT SET " + "AT "+
								CricketFunctions.generateRunRate(CricketFunctions.GetTargetData(matchAllData).getTargetRuns(), 
										Integer.valueOf(CricketFunctions.GetTargetData(matchAllData).getTargetOvers().split("\\.")[0]),
										(Integer.valueOf(CricketFunctions.GetTargetData(matchAllData).getTargetOvers().split("\\.").length >1 ? 
												Integer.valueOf(CricketFunctions.GetTargetData(matchAllData).getTargetOvers().split("\\.")[1]):0)), 2, matchAllData) + 
								" RUNS PER OVER (" + matchAllData.getSetup().getTargetType().toUpperCase() + ")\0", print_writers);
						
					}
					else {
						
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_Target$info1$InfoAll1$txt_Info1*GEOM*TEXT SET " + "FROM " + 
								CricketFunctions.GetTargetData(matchAllData).getTargetOvers() + " OVERS" + "\0", print_writers);
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_Target$Info2$txt_Info1*GEOM*TEXT SET " + "AT "+
								CricketFunctions.generateRunRate(CricketFunctions.GetTargetData(matchAllData).getTargetRuns(), 
										Integer.valueOf(CricketFunctions.GetTargetData(matchAllData).getTargetOvers().split("\\.")[0]),
										(Integer.valueOf(CricketFunctions.GetTargetData(matchAllData).getTargetOvers().split("\\.").length >1 ? 
												Integer.valueOf(CricketFunctions.GetTargetData(matchAllData).getTargetOvers().split("\\.")[1]):0)), 2, matchAllData) + " RUNS PER OVER" + "\0", print_writers);
						
					}
				}else {
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_Target$info1$InfoAll1$txt_Info1*GEOM*TEXT SET " + "FROM " + 
							CricketFunctions.GetTargetData(matchAllData).getTargetOvers() + " OVERS" + "\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_Target$Info2$txt_Info1*GEOM*TEXT SET " + "AT "+
							CricketFunctions.generateRunRate(CricketFunctions.GetTargetData(matchAllData).getTargetRuns(),
									Integer.valueOf(CricketFunctions.GetTargetData(matchAllData).getTargetOvers()), 
									0, 2, matchAllData) + " RUNS PER OVER" + "\0", print_writers);
				}
			}
			break;
		}
		return Constants.OK;
	}
	public String PlayingXIBody(List<PrintWriter> print_writers,String WhatToProcess, int WhichSide,Configuration config, MatchAllData matchAllData,Inning inning) {
		
		rowId=0; rowId1 =0;
		for(int i=1;i<=PlayingXI.size();i++) {
			switch(i) {
			case 1: case 2: case 3: case 4: case 5:
				rowId = i;
				containerName = "$1";
				break;
			case 6: case 7: case 8: case 9: case 10: case 11:
				rowId = i - 5;
				containerName = "$2";
				break;
			}
			
			if(!PlayerIdIn.contains(PlayingXI.get(i-1).getPlayerId())) {
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_LineUp_Image$LineUpBig$Side" + WhichSide + containerName + "$Photo_" + rowId + 
						"$Name$SelectArrow*FUNCTION*Omo*vis_con SET 2\0", print_writers);
			}else {
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_LineUp_Image$LineUpBig$Side" + WhichSide + containerName + "$Photo_" + rowId + 
						"$Name$SelectArrow*FUNCTION*Omo*vis_con SET 0\0", print_writers);
			}
			
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_LineUp_Image$LineUpBig$Side" + WhichSide + containerName + "$Photo_" + rowId + 
					"$Name$txt_PlayerName*GEOM*TEXT SET " + PlayingXI.get(i-1).getTicker_name() + "\0", print_writers);
			
			if(config.getPrimaryIpAddress().equalsIgnoreCase(Constants.LOCALHOST)) {
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_LineUp_Image$LineUpBig$Side" + WhichSide  + containerName + "$Photo_" + rowId + "$img_PhotoShadow*TEXTURE*IMAGE SET " 
						+ (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_LOCAL_PHOTO_PATH : Constants.MT20_LOCAL_PHOTO_PATH) + team.getTeamName4() + "\\\\" + Constants.CENTER + "\\\\" + PlayingXI.get(i-1).getPhoto() + CricketUtil.PNG_EXTENSION + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_LineUp_Image$LineUpBig$Side" + WhichSide  + containerName + "$Photo_" + rowId + "$img_Photo*TEXTURE*IMAGE SET " 
						+ (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_LOCAL_PHOTO_PATH : Constants.MT20_LOCAL_PHOTO_PATH) + team.getTeamName4() + "\\\\" + Constants.CENTER + "\\\\" + PlayingXI.get(i-1).getPhoto() + CricketUtil.PNG_EXTENSION + "\0", print_writers);
			}else {
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_LineUp_Image$LineUpBig$Side" + WhichSide  + containerName + "$Photo_" + rowId + "$img_Photo*TEXTURE*IMAGE SET " 
					+ "\\\\" + config.getPrimaryIpAddress() + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_PHOTO_PATH : Constants.MT20_PHOTO_PATH) + team.getTeamName4() + "\\" + Constants.CENTER + "\\" + PlayingXI.get(i-1).getPhoto() + CricketUtil.PNG_EXTENSION + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_LineUp_Image$LineUpBig$Side" + WhichSide  + containerName + "$Photo_" + rowId + "$img_PhotoShadow*TEXTURE*IMAGE SET " 
						+ "\\\\" + config.getPrimaryIpAddress() + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_PHOTO_PATH : Constants.MT20_PHOTO_PATH) + team.getTeamName4() + "\\" + Constants.CENTER + "\\" + PlayingXI.get(i-1).getPhoto() + CricketUtil.PNG_EXTENSION + "\0", print_writers);
			}
			
			if(PlayingXI.get(i-1).getRole().equalsIgnoreCase(CricketUtil.BATSMAN) || PlayingXI.get(i-1).getRole().equalsIgnoreCase("BAT/KEEPER")) {
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_LineUp_Image$LineUpBig$Side" + WhichSide  + containerName + "$Photo_" + rowId + 
						"$Role$txt_Description*GEOM*TEXT SET " + "BATTER" + "\0", print_writers);
			}
			else if(PlayingXI.get(i-1).getRole().equalsIgnoreCase(CricketUtil.BOWLER)) {
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_LineUp_Image$LineUpBig$Side" + WhichSide  + containerName + "$Photo_" + rowId + 
					"$Role$txt_Description*GEOM*TEXT SET " + CricketFunctions.getBowlerType(PlayingXI.get(i-1).getBowlingStyle()).replace("PACE", "SEAM") + "\0", print_writers);
			}
			else if(PlayingXI.get(i-1).getRole().equalsIgnoreCase("ALL-ROUNDER")) {
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_LineUp_Image$LineUpBig$Side" + WhichSide  + containerName + "$Photo_" + rowId + 
						"$Role$txt_Description*GEOM*TEXT SET " + "BAT/" +CricketFunctions.getBowlerType(PlayingXI.get(i-1).getBowlingStyle()).replace("PACE", "SEAM") + "\0", print_writers);
			}else if(PlayingXI.get(i-1).getRole().equalsIgnoreCase("Wicket-keeper")) {
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_LineUp_Image$LineUpBig$Side" + WhichSide  + containerName + "$Photo_" + rowId + 
						"$Role$txt_Description*GEOM*TEXT SET " + "KEEPER" + "\0", print_writers);
			}
			
			if(PlayingXI.get(i-1).getCaptainWicketKeeper() != null){
				if(PlayingXI.get(i-1).getCaptainWicketKeeper().equalsIgnoreCase(CricketUtil.CAPTAIN)){
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_LineUp_Image$LineUpBig$Side" + WhichSide  + containerName
							+ "$Photo_" + rowId + "$Data$txt_Captain*GEOM*TEXT SET (C) \0", print_writers);
				}
				else if(PlayingXI.get(i-1).getCaptainWicketKeeper().equalsIgnoreCase(CricketUtil.WICKET_KEEPER)) {
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_LineUp_Image$LineUpBig$Side" + WhichSide  + containerName
							+ "$Photo_" + rowId + "$Data$txt_Captain*GEOM*TEXT SET \0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_LineUp_Image$LineUpBig$Side" + WhichSide  + containerName
							+ "$Photo_" + rowId + "$Role$txt_Description*GEOM*TEXT SET " + "KEEPER" + "\0", print_writers);
				}
				else if(PlayingXI.get(i-1).getCaptainWicketKeeper().equalsIgnoreCase("CAPTAIN_WICKET_KEEPER")) {
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_LineUp_Image$LineUpBig$Side" + WhichSide  + containerName
							+ "$Photo_" + rowId + "$Data$txt_Captain*GEOM*TEXT SET (C) \0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_LineUp_Image$LineUpBig$Side" + WhichSide  + containerName
							+ "$Photo_" + rowId + "$Role$txt_Description*GEOM*TEXT SET " + "KEEPER" + "\0", print_writers);
				}else {
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_LineUp_Image$LineUpBig$Side"  + WhichSide + containerName
							+ "$Photo_" + rowId + "$Data$txt_Captain*GEOM*TEXT SET \0", print_writers);
				}
			}else {
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_LineUp_Image$LineUpBig$Side" + WhichSide  + containerName
						+ "$Photo_" + rowId + "$Data$txt_Captain*GEOM*TEXT SET \0", print_writers);
			}
		}
		return Constants.OK;
	}
	public String PlayingXISimpleBody(List<PrintWriter> print_writers,String WhatToProcess, int WhichSide,Configuration config, MatchAllData matchAllData,Inning inning) {
		
		switch (config.getBroadcaster()) {
		case Constants.AFG_SL_SERIES:
			rowId=1; rowId1 =1;
			String which_role = "";
			
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$select_GraphicsType"
					+ "*FUNCTION*Omo*vis_con SET 3\0", print_writers);
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$Team$Grp1$select_DataType"
					+ "*FUNCTION*Omo*vis_con SET 0\0", print_writers);
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$Team$Grp1$TeamData$Title$"
					+ "DetailsData*ACTIVE SET 1\0", print_writers);
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$Team$Grp1$TeamData$Title$"
					+ "txt_Title1*GEOM*TEXT SET " + "MATCHES" + "\0", print_writers);
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$Team$Grp1$TeamData$Title$"
					+ "txt_Title2*GEOM*TEXT SET " + "RUNS" + "\0", print_writers);
			
			for(int i=1;i<=PlayingXI.size();i++) {
				rowId ++ ;
				if(PlayingXI.get(i-1).getSurname() != null) {
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$Team$Grp" + rowId + "$TeamData$Highlight$Name_Role$"
							+ "select_FirstName*FUNCTION*Omo*vis_con SET 2\0", print_writers);
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$Team$Grp" + rowId + "$TeamData$Highlight$"
							+ "txt_FirstName*GEOM*TEXT SET " + PlayingXI.get(i-1).getFirstname() + "\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$Team$Grp" + rowId + "$TeamData$Highlight$"
							+ "txt_LastName*GEOM*TEXT SET " + PlayingXI.get(i-1).getSurname() + "\0", print_writers);
				}else {
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$Team$Grp" + rowId + "$TeamData$Highlight$Name_Role$"
							+ "select_FirstName*FUNCTION*Omo*vis_con SET 0\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$Team$Grp" + rowId + "$TeamData$Highlight$"
							+ "txt_LastName*GEOM*TEXT SET " + PlayingXI.get(i-1).getFirstname() + "\0", print_writers);
				}
				
				if(PlayingXI.get(i-1).getRole().equalsIgnoreCase("BATSMAN") || PlayingXI.get(i-1).getRole().equalsIgnoreCase("BATTER") || PlayingXI.get(i-1).getRole().equalsIgnoreCase("BAT/KEEPER")) {
					if(PlayingXI.get(i-1).getBattingStyle().equalsIgnoreCase("LHB")) {
						which_role = "Batsman_Lefthand";
					}else {
						which_role = "Bat_Icon";
					}
				}else if(PlayingXI.get(i-1).getRole().equalsIgnoreCase("BOWLER")) {
					if(PlayingXI.get(i-1).getBowlingStyle() == null) {
						which_role = "Bowler";
					}else {
						switch(PlayingXI.get(i-1).getBowlingStyle()) {
						case "RF": case "RFM": case "RMF": case "RM": case "RSM": case "LF": case "LFM": case "LMF": case "LM":
							which_role = "FastBowler";
							break;
						case "LSL": case "WSL": case "LCH": case "RLG": case "WSR": case "LSO":
							which_role = "SpinBowler";
							break;
						case "ROB":
							which_role = "Off_Spin";
							break;
						case "RLB":
							which_role = "Leg_Spin";
							break;	
						}
					}
				}else if(PlayingXI.get(i-1).getRole().equalsIgnoreCase("ALL-ROUNDER")) {
					if(PlayingXI.get(i-1).getBowlingStyle() == null) {
						if(PlayingXI.get(i-1).getBattingStyle().equalsIgnoreCase("LHB")) {
							which_role = "Off_Spin_Allrounder_Left";
						}else {
							which_role = "FastBowlerAllrounder";
						}
					}else {
						switch(PlayingXI.get(i-1).getBowlingStyle()) {
						case "RF": case "RFM": case "RMF": case "RM": case "RSM": case "LF": case "LFM": case "LMF": case "LM":
							if(PlayingXI.get(i-1).getBattingStyle().equalsIgnoreCase("LHB")) {
								which_role = "Pace_BowlerAllrounerLeftHand";
							}else {
								which_role = "FastBowlerAllrounder";
							}
							break;
						case "ROB": case "RLB": case "LSL": case "WSL": case "LCH": case "RLG": case "WSR": case "LSO":
							if(PlayingXI.get(i-1).getBattingStyle().equalsIgnoreCase("LHB")) {
								which_role = "Off_Spin_Allrounder_Left";
							}else {
								which_role = "Off_Spin_Bat";
							}
							break;
						}
					}
				}
				
				if(PlayingXI.get(i-1).getCaptainWicketKeeper() != null && !PlayingXI.get(i-1).getCaptainWicketKeeper().isEmpty()) {
					if(PlayingXI.get(i-1).getCaptainWicketKeeper().equalsIgnoreCase(CricketUtil.CAPTAIN)) {
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$Team$Grp" + rowId + "$TeamData$Highlight"
								+ "$Captain*ACTIVE SET 1\0", print_writers);
					}else if(PlayingXI.get(i-1).getCaptainWicketKeeper().equalsIgnoreCase("CAPTAIN_WICKET_KEEPER")) {
						which_role = "Keeper";
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$Team$Grp" + rowId + "$TeamData$Highlight"
								+ "$Captain*ACTIVE SET 1\0", print_writers);
						
					}else if(PlayingXI.get(i-1).getCaptainWicketKeeper().equalsIgnoreCase(CricketUtil.WICKET_KEEPER)) {
						which_role = "Keeper";
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$Team$Grp" + rowId + "$TeamData$Highlight"
								+ "$Captain*ACTIVE SET 0\0", print_writers);
					}else {
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$Team$Grp" + rowId + "$TeamData$Highlight"
								+ "$Captain*ACTIVE SET 0\0", print_writers);
					}
				}else {
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$Team$Grp" + rowId + "$TeamData$Highlight"
							+ "$Captain*ACTIVE SET 0\0", print_writers);
				}
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$Team$Grp" + rowId + "$TeamData$Highlight$RoleIcon"
						+ "$img_Icons*TEXTURE*IMAGE SET " + Constants.AFG_SL_SERIES_ICONS + which_role + "\0", print_writers);
				
				int playerId = PlayingXI.get(i-1).getPlayerId();
				stat = statisticsList.stream().filter(stat -> stat.getPlayer_id() == playerId).findAny().orElse(null);
				
				if(stat != null) {
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$Team$Grp" + rowId + "$TeamData$Highlight$DetailsData$"
							+ "txt_Value1*GEOM*TEXT SET " + stat.getMatches() + "\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$Team$Grp" + rowId + "$TeamData$Highlight$DetailsData$"
							+ "txt_Value2*GEOM*TEXT SET " + stat.getRuns() + "\0", print_writers);
				}else {
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$Team$Grp" + rowId + "$TeamData$Highlight$DetailsData$"
							+ "txt_Value1*GEOM*TEXT SET -\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_FullFrames$Main$All_Graphics$Side" + WhichSide + "$Team$Grp" + rowId + "$TeamData$Highlight$DetailsData$"
							+ "txt_Value2*GEOM*TEXT SET -\0", print_writers);
				}
			}
			
			break;
		case Constants.BAN_AFG_SERIES: case Constants.ACC:
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$Select*FUNCTION*Omo*vis_con SET 4\0", print_writers);
			
			rowId=0; rowId1 =0;
			for(int i=1;i<=PlayingXI.size();i++) {
				rowId = rowId + 1;
				if(!PlayerIdIn.contains(PlayingXI.get(i-1).getPlayerId())) {
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide  + "$Playing_XI_Image$TeamDataAll$PlayerGrp" + rowId + 
							"$iN*FUNCTION*Omo*vis_con SET 1\0", print_writers);
				}else {
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide  + "$Playing_XI_Image$TeamDataAll$PlayerGrp" + rowId + 
							"$iN*FUNCTION*Omo*vis_con SET 0\0", print_writers);
				}
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$Playing_XI_Image$TeamDataAll$PlayerGrp" + rowId + 
						"$NameGrp$txt_PlayerName*GEOM*TEXT SET " + PlayingXI.get(i-1).getTicker_name() + "\0", print_writers);
				
				switch (config.getBroadcaster()) {
				case Constants.BAN_AFG_SERIES:
					if(config.getPrimaryIpAddress().equalsIgnoreCase(Constants.LOCALHOST)) {
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$Playing_XI_Image$TeamDataAll$PlayerGrp" + rowId + "$ImageGrp$img_Player*TEXTURE*IMAGE SET " 
								+ Constants.BAN_AFG_SERIES_LOCAL_PHOTO_PATH + team.getTeamName4() + "\\\\" + Constants.CENTER + "\\\\" + PlayingXI.get(i-1).getPhoto() + CricketUtil.PNG_EXTENSION + "\0", print_writers);
					}else {
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$Playing_XI_Image$TeamDataAll$PlayerGrp" + rowId + "$ImageGrp$img_Player*TEXTURE*IMAGE SET " 
								+ "\\\\" + config.getPrimaryIpAddress() + Constants.BAN_AFG_SERIES_PHOTO_PATH + team.getTeamName4() + "\\\\" + Constants.CENTER + "\\\\" + PlayingXI.get(i-1).getPhoto() + CricketUtil.PNG_EXTENSION 
								+ "\0", print_writers);
					}
					break;
				default:
					if(config.getPrimaryIpAddress().equalsIgnoreCase(Constants.LOCALHOST)) {
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$Playing_XI_Image$TeamDataAll$PlayerGrp" + rowId + "$ImageGrp$img_Player*TEXTURE*IMAGE SET " 
								+ Constants.ACC_LOCAL_PHOTO_PATH + team.getTeamBadge() + "\\\\" + Constants.CENTER + "\\\\" + PlayingXI.get(i-1).getPhoto() + CricketUtil.PNG_EXTENSION + "\0", print_writers);
					}else {
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$Playing_XI_Image$TeamDataAll$PlayerGrp" + rowId + "$ImageGrp$img_Player*TEXTURE*IMAGE SET " 
								+ "\\\\" + config.getPrimaryIpAddress() + Constants.ACC_PHOTO_PATH + team.getTeamBadge() + "\\\\" + Constants.CENTER + "\\\\" + PlayingXI.get(i-1).getPhoto() + CricketUtil.PNG_EXTENSION 
								+ "\0", print_writers);
					}
					break;
				}
				
				if(PlayingXI.get(i-1).getRole().equalsIgnoreCase(CricketUtil.BATSMAN) || PlayingXI.get(i-1).getRole().equalsIgnoreCase("BAT/KEEPER")) {
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide  + "$Playing_XI_Image$TeamDataAll$PlayerGrp" + rowId + 
							"$NameGrp$txt_Role*GEOM*TEXT SET " + "BATTER" + "\0", print_writers);
				}
				else if(PlayingXI.get(i-1).getRole().equalsIgnoreCase(CricketUtil.BOWLER)) {
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide  + "$Playing_XI_Image$TeamDataAll$PlayerGrp" + rowId + 
							"$NameGrp$txt_Role*GEOM*TEXT SET " + CricketFunctions.getBowlerType(PlayingXI.get(i-1).getBowlingStyle()).replace("PACE", "SEAM") + "\0", print_writers);
				}
				else if(PlayingXI.get(i-1).getRole().equalsIgnoreCase("ALL-ROUNDER")) {
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide  + "$Playing_XI_Image$TeamDataAll$PlayerGrp" + rowId + 
							"$NameGrp$txt_Role*GEOM*TEXT SET " + "BAT/" +CricketFunctions.getBowlerType(PlayingXI.get(i-1).getBowlingStyle()).replace("PACE", "SEAM") + "\0", print_writers);
				}
				
				if(PlayingXI.get(i-1).getCaptainWicketKeeper() != null){
					if(PlayingXI.get(i-1).getCaptainWicketKeeper().equalsIgnoreCase(CricketUtil.CAPTAIN)){
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide  + "$Playing_XI_Image$TeamDataAll$PlayerGrp" + rowId + 
								"$Captain*FUNCTION*Omo*vis_con SET 1\0", print_writers);
					}else if(PlayingXI.get(i-1).getCaptainWicketKeeper().equalsIgnoreCase(CricketUtil.WICKET_KEEPER)) {
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide  + "$Playing_XI_Image$TeamDataAll$PlayerGrp" + rowId + 
								"$NameGrp$txt_Role*GEOM*TEXT SET " + "KEEPER" + "\0", print_writers);
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide  + "$Playing_XI_Image$TeamDataAll$PlayerGrp" + rowId + 
								"$Captain*FUNCTION*Omo*vis_con SET 0\0", print_writers);
					}else if(PlayingXI.get(i-1).getCaptainWicketKeeper().equalsIgnoreCase("CAPTAIN_WICKET_KEEPER")) {
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide  + "$Playing_XI_Image$TeamDataAll$PlayerGrp" + rowId + 
								"$NameGrp$txt_Role*GEOM*TEXT SET " + "KEEPER" + "\0", print_writers);
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide  + "$Playing_XI_Image$TeamDataAll$PlayerGrp" + rowId + 
								"$Captain*FUNCTION*Omo*vis_con SET 1\0", print_writers);
					}
				}else {
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide  + "$Playing_XI_Image$TeamDataAll$PlayerGrp" + rowId + 
							"$Captain*FUNCTION*Omo*vis_con SET 0\0", print_writers);
				}
			}
			break;
		}
		return Constants.OK;
	}
	public String PointsTableBody(List<PrintWriter> print_writers, String whatToProcess, int WhichSide, MatchAllData matchAllData, Configuration config) throws InterruptedException {
		
		switch (config.getBroadcaster()) {
		case Constants.ACC:
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$Select*FUNCTION*Omo*vis_con SET 12\0", print_writers);
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$Standings$BatRow1$txt_4*GEOM*TEXT SET NR\0", print_writers);
			rowId = 1;
			for(int i=0; i<=leagueTable.getLeagueTeams().size()-1;i++) {
				rowId ++;
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$Standings$BatRow" + rowId + "$txt_Pos*GEOM*TEXT SET " + (rowId-1) + "\0", print_writers);
				
				if(leagueTable.getLeagueTeams().get(i).getQualifiedStatus().trim().equalsIgnoreCase("")) {
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$Standings$BatRow" + rowId + "$txt_Qualify*GEOM*TEXT SET \0", print_writers);
				}else {
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$Standings$BatRow" + rowId + "$txt_Qualify*GEOM*TEXT SET Q\0", print_writers);
				}
				
				for(Team team : Teams) {
					if(team.getTeamBadge().equalsIgnoreCase(leagueTable.getLeagueTeams().get(i).getTeamName())) {
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$Standings$BatRow" + rowId + "$txt_Name*GEOM*TEXT SET " 
								+ team.getTeamName1() + "\0", print_writers);
						break;
					}
				}
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$Standings$BatRow" + rowId + "$txt_1*GEOM*TEXT SET " 
						+ leagueTable.getLeagueTeams().get(i).getPlayed() + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$Standings$BatRow" + rowId + "$txt_2*GEOM*TEXT SET " 
						+ leagueTable.getLeagueTeams().get(i).getWon() + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$Standings$BatRow" + rowId + "$txt_3*GEOM*TEXT SET " 
						+ leagueTable.getLeagueTeams().get(i).getLost() + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$Standings$BatRow" + rowId + "$txt_4*GEOM*TEXT SET " 
						+ leagueTable.getLeagueTeams().get(i).getNoResult() + "\0", print_writers);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$Standings$BatRow" + rowId + "$txt_5*GEOM*TEXT SET " 
						+ leagueTable.getLeagueTeams().get(i).getPoints() + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$Standings$BatRow" + rowId + "$txt_6*GEOM*TEXT SET " 
						+ String.format("%.2f", leagueTable.getLeagueTeams().get(i).getNetRunRate()) + "\0", print_writers);
			}
			break;
		default:
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide 
					+ "$select_GraphicsType*FUNCTION*Omo*vis_con SET 10\0", print_writers);
			
			rowId = 0;
			for(int i=0; i<=leagueTable.getLeagueTeams().size()-1;i++) {
				rowId++;
				if(matchAllData.getSetup().getHomeTeam().getTeamBadge().equalsIgnoreCase(leagueTable.getLeagueTeams().get(i).getTeamName())  
						|| matchAllData.getSetup().getAwayTeam().getTeamBadge().equalsIgnoreCase(leagueTable.getLeagueTeams().get(i).getTeamName())) {
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide + 
							"$Standings$Row_" + rowId + "$select_Highlight*FUNCTION*Omo*vis_con SET 1\0", print_writers);
					
					containerName = "$Highlight";
					
				}else {
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide + 
							"$Standings$Row_" + rowId + "$select_Highlight*FUNCTION*Omo*vis_con SET 0\0", print_writers);
					
					containerName = "$Dehighlight";
					
				}
				
				if(leagueTable.getLeagueTeams().get(i).getQualifiedStatus().trim().equalsIgnoreCase("")) {
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide + 
							"$Standings$Row_" + rowId + containerName + "$txt_Position*GEOM*TEXT SET " + rowId + "\0", print_writers);
				}else {
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide + 
							"$Standings$Row_" + rowId + containerName + "$txt_Position*GEOM*TEXT SET Q\0", print_writers);
				}
				for(Team team : Teams) {
					if(team.getTeamBadge().equalsIgnoreCase(leagueTable.getLeagueTeams().get(i).getTeamName())) {
						
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide + 
								"$Standings$Row_" + rowId + containerName + "$txt_Team*GEOM*TEXT SET " + team.getTeamName1() + "\0", print_writers);
					}
				}
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide + 
						"$Standings$Row_" + rowId + containerName + "$txt_Played*GEOM*TEXT SET " + leagueTable.getLeagueTeams().get(i).getPlayed() + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide + 
						"$Standings$Row_" + rowId + containerName + "$txt_Won*GEOM*TEXT SET " + leagueTable.getLeagueTeams().get(i).getWon() + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide + 
						"$Standings$Row_" + rowId + containerName + "$txt_Lost*GEOM*TEXT SET " + leagueTable.getLeagueTeams().get(i).getLost() + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide + 
						"$Standings$Row_" + rowId + containerName + "$txt_NoResult*GEOM*TEXT SET " + leagueTable.getLeagueTeams().get(i).getNoResult() + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide + 
						"$Standings$Row_" + rowId + containerName + "$txt_Points*GEOM*TEXT SET " + leagueTable.getLeagueTeams().get(i).getPoints() + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide + 
						"$Standings$Row_" + rowId + containerName + "$txt_NRR*GEOM*TEXT SET " + 
						String.format("%.2f", leagueTable.getLeagueTeams().get(i).getNetRunRate()) + "\0", print_writers);
			}
			break;
		}
		return Constants.OK;
	}
	public String LeaderBoardBody(List<PrintWriter> print_writers,int WhichSide,String whatToProcess ,Configuration config, MatchAllData matchAllData,Inning inning) throws InterruptedException {
		
		switch (config.getBroadcaster()) {
		case Constants.BAN_AFG_SERIES: case Constants.ACC:
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide 
					+ "$Select*FUNCTION*Omo*vis_con SET 10\0", print_writers);
			
			if(whatToProcess.equalsIgnoreCase("Control_z")) {
				rowId = 0;
				System.out.println("Size = " + top_batsman_beststat.size());
				for(int i = 0; i <= top_batsman_beststat.size() - 1 ; i++) {
					rowId = rowId + 1;
					if(rowId <= 6) {
						
						if(FirstPlayerId == top_batsman_beststat.get(i).getPlayer().getPlayerId()) {
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide +"$TopScorer$Rows$Row" + rowId 
									+ "$Omo*FUNCTION*Omo*vis_con SET 1\0", print_writers);
							
							switch (config.getBroadcaster()) {
							case Constants.BAN_AFG_SERIES:
								CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$TopScorer$ImageGrp$LogoBase$"
										+ "img_Base2*TEXTURE*IMAGE SET " + Constants.BAN_AFG_SERIES_BASE2 + Teams.get(top_batsman_beststat.get(i).getPlayer().getTeamId() - 1).getTeamBadge() + "\0", print_writers);
								
								if(config.getPrimaryIpAddress().equalsIgnoreCase(Constants.LOCALHOST)) {
									CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide +"$TopScorer$ImageGrp$img_Player"
											+ "*TEXTURE*IMAGE SET "+ Constants.BAN_AFG_SERIES_LOCAL_PHOTO_PATH + Teams.get(top_batsman_beststat.get(i).getPlayer().getTeamId() - 1).getTeamName4() 
											+ "\\\\" + Constants.CENTER + "\\\\" + top_batsman_beststat.get(i).getPlayer().getPhoto() + CricketUtil.PNG_EXTENSION +"\0", print_writers);
								}else {
									CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide +"$TopScorer$ImageGrp$img_Player"
											+ "*TEXTURE*IMAGE SET " + "\\\\" + config.getPrimaryIpAddress() + "\\\\" + Constants.BAN_AFG_SERIES_PHOTO_PATH 
											+ Teams.get(top_batsman_beststat.get(i).getPlayer().getTeamId() - 1).getTeamName4() + "\\\\" + Constants.CENTER + "\\\\" 
											+ top_batsman_beststat.get(i).getPlayer().getPhoto() + CricketUtil.PNG_EXTENSION +"\0", print_writers);
								}
								break;
							case Constants.ACC:
								if(config.getPrimaryIpAddress().equalsIgnoreCase(Constants.LOCALHOST)) {
									CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$TopScorer$ImageGrp$img_Player"
											+ "*TEXTURE*IMAGE SET "+ Constants.ACC_LOCAL_PHOTO_PATH + Teams.get(top_batsman_beststat.get(i).getPlayer().getTeamId() - 1).getTeamBadge() 
											+ "\\\\" + Constants.CENTER + "\\\\" + top_batsman_beststat.get(i).getPlayer().getPhoto() + CricketUtil.PNG_EXTENSION +"\0", print_writers);
								}else {
									CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$TopScorer$ImageGrp$img_Player"
											+ "*TEXTURE*IMAGE SET " + "\\\\" + config.getPrimaryIpAddress() + "\\\\" + Constants.ACC_PHOTO_PATH 
											+ Teams.get(top_batsman_beststat.get(i).getPlayer().getTeamId() - 1).getTeamBadge() + "\\\\" + Constants.CENTER + "\\\\" 
											+ top_batsman_beststat.get(i).getPlayer().getPhoto() + CricketUtil.PNG_EXTENSION +"\0", print_writers);
								}
								break;
							}
							
							
						}else {
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide 
									+"$TopScorer$Rows$Row" + rowId + "$Omo*FUNCTION*Omo*vis_con SET 0\0", print_writers);
						}
						
						for(int j=0; j<2; j++) {
							
							if(j==0) {
								containerName ="$DeHighlight";
								CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$TopScorer$Data$Rows"
										+ "$Row" + rowId + containerName + "$img_Text1*TEXTURE*IMAGE SET " + Constants.BAN_AFG_SERIES_TEXT3 + Teams.get(top_batsman_beststat.get(i).getPlayer().getTeamId() - 1).getTeamBadge() + "\0", print_writers);
								
							}else {
								containerName ="$Highlight";
								switch (config.getBroadcaster()) {
								case Constants.BAN_AFG_SERIES:
									CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$TopScorer$Data$Rows"
											+ "$Row" + rowId + containerName + "$img_Base2*TEXTURE*IMAGE SET " + Constants.BAN_AFG_SERIES_BASE2 + 
											Teams.get(top_batsman_beststat.get(i).getPlayer().getTeamId() - 1).getTeamBadge() + "\0", print_writers);
									break;
								}
								CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$TopScorer$Data$Rows"
										+ "$Row" + rowId + containerName + "$img_Text2*TEXTURE*IMAGE SET " + Constants.BAN_AFG_SERIES_TEXT2 + 
										Teams.get(top_batsman_beststat.get(i).getPlayer().getTeamId() - 1).getTeamBadge() + "\0", print_writers);
							}
							
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide 
									+"$TopScorer$Rows$Row" + rowId + containerName + "$txt_PlayertName*GEOM*TEXT SET " + 
									top_batsman_beststat.get(i).getPlayer().getFull_name() + "\0", print_writers);
							
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide 
									+"$TopScorer$Rows$Row" + rowId + containerName + "$SelectDataType*FUNCTION*Omo*vis_con SET 1\0", print_writers);
							
//							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide 
//									+"$TopScorer$Rows$Row" + rowId + containerName + "$txt_TeamName*GEOM*TEXT SET v "+
//									top_batsman_beststat.get(i).getOpponentTeam().getTeamName1() + "  |  " + 
//									getOrdinalMatch(top_batsman_beststat.get(i).getMatchNumber()).toUpperCase() + " T20I" +"\0", print_writers);
							
//							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide 
//									+"$TopScorer$Rows$Row" + rowId + containerName + "$txt_TeamName*GEOM*TEXT SET "+
//									Teams.get(top_batsman_beststat.get(i).getPlayer().getTeamId() - 1).getTeamName1() 
//									+ " | v " + top_batsman_beststat.get(i).getOpponentTeam().getTeamName1() +"\0", print_writers);
							
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide 
									+"$TopScorer$Rows$Row" + rowId + containerName + "$txt_TeamName*GEOM*TEXT SET "+
									Teams.get(top_batsman_beststat.get(i).getPlayer().getTeamId() - 1).getTeamName1() 
									+ ", " + getOrdinalMatch(top_batsman_beststat.get(i).getMatchNumber()).toUpperCase() + " T20I" +"\0", print_writers);
							
							
							if(top_batsman_beststat.get(i).getBestEquation() % 2 == 0) {
								CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide 
										+"$TopScorer$Rows$Row" + rowId + containerName + "$txt_Stat1*GEOM*TEXT SET "+top_batsman_beststat.get(i).getBestEquation() / 2+"\0", print_writers);
								
							}else {
								CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide 
										+"$TopScorer$Rows$Row" + rowId + containerName + "$txt_Stat1*GEOM*TEXT SET "+top_batsman_beststat.get(i).getBestEquation() / 2 + "*"+"\0", print_writers);
								
							}
						}
					}
				}
			}else if(whatToProcess.equalsIgnoreCase("Control_x")) {
				rowId = 0;
				for(int i = 0; i <= top_bowler_beststats.size() - 1 ; i++) {
					rowId = rowId + 1;
					if(rowId <= 6) {
						
						if(FirstPlayerId == top_bowler_beststats.get(i).getPlayer().getPlayerId()) {
							
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide 
									+"$TopScorer$Rows$Row" + rowId + "$Omo*FUNCTION*Omo*vis_con SET 1\0", print_writers);
							
							switch (config.getBroadcaster()) {
							case Constants.BAN_AFG_SERIES:
								CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$TopScorer$ImageGrp$LogoBase$"
										+ "img_Base2*TEXTURE*IMAGE SET " + Constants.BAN_AFG_SERIES_BASE2 + Teams.get(top_bowler_beststats.get(i).getPlayer().getTeamId() - 1).getTeamBadge() + "\0", print_writers);
								
								if(config.getPrimaryIpAddress().equalsIgnoreCase(Constants.LOCALHOST)) {
									CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$TopScorer$ImageGrp$img_Player"
											+ "*TEXTURE*IMAGE SET "+ Constants.BAN_AFG_SERIES_LOCAL_PHOTO_PATH + Teams.get(top_bowler_beststats.get(i).getPlayer().getTeamId() - 1).getTeamName4() 
											+ "\\\\" + Constants.CENTER + "\\\\" + top_bowler_beststats.get(i).getPlayer().getPhoto() + CricketUtil.PNG_EXTENSION +"\0", print_writers);
								}else {
									CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$TopScorer$ImageGrp$img_Player"
											+ "*TEXTURE*IMAGE SET " + "\\\\" + config.getPrimaryIpAddress() + "\\\\" + Constants.BAN_AFG_SERIES_PHOTO_PATH 
											+ Teams.get(top_bowler_beststats.get(i).getPlayer().getTeamId() - 1).getTeamName4() + "\\\\" + Constants.CENTER + "\\\\" 
											+ top_bowler_beststats.get(i).getPlayer().getPhoto() + CricketUtil.PNG_EXTENSION +"\0", print_writers);
								}
								break;
							case Constants.ACC:
								if(config.getPrimaryIpAddress().equalsIgnoreCase(Constants.LOCALHOST)) {
									CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$TopScorer$ImageGrp$img_Player"
											+ "*TEXTURE*IMAGE SET "+ Constants.ACC_LOCAL_PHOTO_PATH + Teams.get(top_bowler_beststats.get(i).getPlayer().getTeamId() - 1).getTeamBadge() 
											+ "\\\\" + Constants.CENTER + "\\\\" + top_bowler_beststats.get(i).getPlayer().getPhoto() + CricketUtil.PNG_EXTENSION +"\0", print_writers);
								}else {
									CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$TopScorer$ImageGrp$img_Player"
											+ "*TEXTURE*IMAGE SET " + "\\\\" + config.getPrimaryIpAddress() + "\\\\" + Constants.ACC_PHOTO_PATH 
											+ Teams.get(top_bowler_beststats.get(i).getPlayer().getTeamId() - 1).getTeamBadge() + "\\\\" + Constants.CENTER + "\\\\" 
											+ top_bowler_beststats.get(i).getPlayer().getPhoto() + CricketUtil.PNG_EXTENSION +"\0", print_writers);
								}
								break;
							}
							
							
						}else {
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide 
									+"$TopScorer$Rows$Row" + rowId + "$Omo*FUNCTION*Omo*vis_con SET 0\0", print_writers);
						}
						
						for(int j=0; j<2; j++) {
							
							if(j==0) {
								containerName ="$DeHighlight";
								CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$TopScorer$Data$Rows"
										+ "$Row" + rowId + containerName + "$img_Text1*TEXTURE*IMAGE SET " + Constants.BAN_AFG_SERIES_TEXT3 + Teams.get(top_bowler_beststats.get(i).getPlayer().getTeamId() - 1).getTeamBadge() + "\0", print_writers);
								
							}else {
								containerName ="$Highlight";
								switch (config.getBroadcaster()) {
								case Constants.BAN_AFG_SERIES:
									CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$TopScorer$Data$Rows"
											+ "$Row" + rowId + containerName + "$img_Base2*TEXTURE*IMAGE SET " + Constants.BAN_AFG_SERIES_BASE2 + 
											Teams.get(top_bowler_beststats.get(i).getPlayer().getTeamId() - 1).getTeamBadge() + "\0", print_writers);
									break;
								}
								CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$TopScorer$Data$Rows"
										+ "$Row" + rowId + containerName + "$img_Base2*TEXTURE*IMAGE SET " + Constants.BAN_AFG_SERIES_BASE2 + Teams.get(top_bowler_beststats.get(i).getPlayer().getTeamId() - 1).getTeamBadge() + "\0", print_writers);
								CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$TopScorer$Data$Rows"
										+ "$Row" + rowId + containerName + "$img_Text2*TEXTURE*IMAGE SET " + Constants.BAN_AFG_SERIES_TEXT2 + Teams.get(top_bowler_beststats.get(i).getPlayer().getTeamId() - 1).getTeamBadge() + "\0", print_writers);
								
							}
							
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide 
									+"$TopScorer$Rows$Row" + rowId + containerName + "$txt_PlayertName*GEOM*TEXT SET " + 
									top_bowler_beststats.get(i).getPlayer().getFull_name() + "\0", print_writers);
							
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide 
									+"$TopScorer$Rows$Row" + rowId + containerName + "$SelectDataType*FUNCTION*Omo*vis_con SET 1\0", print_writers);
							
//							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide 
//									+"$TopScorer$Rows$Row" + rowId + containerName + "$txt_TeamName*GEOM*TEXT SET v "+
//									top_bowler_beststats.get(i).getOpponentTeam().getTeamName1() + "  |  " + 
//									getOrdinalMatch(top_bowler_beststats.get(i).getMatchNumber()).toUpperCase() + " T20I" +"\0", print_writers);
							
//							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide 
//									+"$TopScorer$Rows$Row" + rowId + containerName + "$txt_TeamName*GEOM*TEXT SET "+
//									Teams.get(top_bowler_beststats.get(i).getPlayer().getTeamId() - 1).getTeamName1() 
//									+ " | v " + top_bowler_beststats.get(i).getOpponentTeam().getTeamName1() +"\0", print_writers);
							
							
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide 
									+"$TopScorer$Rows$Row" + rowId + containerName + "$txt_TeamName*GEOM*TEXT SET "+
									Teams.get(top_bowler_beststats.get(i).getPlayer().getTeamId() - 1).getTeamName1()
									+ ", " + getOrdinalMatch(top_bowler_beststats.get(i).getMatchNumber()).toUpperCase() + " T20I" +"\0", print_writers);
							
							
							if(top_bowler_beststats.get(i).getBestEquation() % 1000 > 0) {
								
								CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide 
										+"$TopScorer$Rows$Row" + rowId + containerName + "$txt_Stat1*GEOM*TEXT SET "+((top_bowler_beststats.get(i).getBestEquation() / 1000) +1) 
										+ "-" + (1000 - (top_bowler_beststats.get(i).getBestEquation() % 1000))+"\0", print_writers);
							}
							else if(top_bowler_beststats.get(i).getBestEquation() % 1000 < 0) {
								CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide 
										+"$TopScorer$Rows$Row" + rowId + containerName + "$txt_Stat1*GEOM*TEXT SET "+(top_bowler_beststats.get(i).getBestEquation() / 1000) 
										+ "-" + Math.abs(top_bowler_beststats.get(i).getBestEquation())+"\0", print_writers);
								
							}else {
								CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide 
										+"$TopScorer$Rows$Row" + rowId + containerName + "$txt_Stat1*GEOM*TEXT SET "+
										(top_bowler_beststats.get(j).getBestEquation() / 1000) + "-" + "0"+"\0", print_writers);
							}
						}
					}
				}
			}else if(whatToProcess.equalsIgnoreCase("Control_Shift_Z")){
				rowId = 0;
				for(int i = 0; i <= tournaments.size() - 1 ; i++) {
					if(tournaments.get(i).getBallsFaced() >= 30) {
						rowId++;
						if(rowId>=6) break;
						
						if(FirstPlayerId == tournaments.get(i).getPlayer().getPlayerId()) {
							
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide 
									+"$TopScorer$Rows$Row" + rowId + "$Omo*FUNCTION*Omo*vis_con SET 1\0", print_writers);
							
							switch (config.getBroadcaster()) {
							case Constants.BAN_AFG_SERIES:
								CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$TopScorer$ImageGrp$LogoBase$"
										+ "img_Base2*TEXTURE*IMAGE SET " + Constants.BAN_AFG_SERIES_BASE2 + Teams.get(tournaments.get(i).getPlayer().getTeamId() - 1).getTeamBadge() + "\0", print_writers);
								
								if(config.getPrimaryIpAddress().equalsIgnoreCase(Constants.LOCALHOST)) {
									CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$TopScorer$ImageGrp$img_Player"
											+ "*TEXTURE*IMAGE SET "+ Constants.BAN_AFG_SERIES_LOCAL_PHOTO_PATH + Teams.get(tournaments.get(i).getPlayer().getTeamId() - 1).getTeamName4() 
											+ "\\\\" + Constants.CENTER + "\\\\" + tournaments.get(i).getPlayer().getPhoto() + CricketUtil.PNG_EXTENSION +"\0", print_writers);
								}else {
									CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$TopScorer$ImageGrp$img_Player"
											+ "*TEXTURE*IMAGE SET " + "\\\\" + config.getPrimaryIpAddress() + "\\\\" + Constants.BAN_AFG_SERIES_PHOTO_PATH 
											+ Teams.get(tournaments.get(i).getPlayer().getTeamId() - 1).getTeamName4() + "\\\\" + Constants.CENTER + "\\\\" 
											+ tournaments.get(i).getPlayer().getPhoto() + CricketUtil.PNG_EXTENSION +"\0", print_writers);
								}
								break;
							case Constants.ACC:
								if(config.getPrimaryIpAddress().equalsIgnoreCase(Constants.LOCALHOST)) {
									CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$TopScorer$ImageGrp$img_Player"
											+ "*TEXTURE*IMAGE SET "+ Constants.ACC_LOCAL_PHOTO_PATH + Teams.get(tournaments.get(i).getPlayer().getTeamId() - 1).getTeamBadge() 
											+ "\\\\" + Constants.CENTER + "\\\\" + tournaments.get(i).getPlayer().getPhoto() + CricketUtil.PNG_EXTENSION +"\0", print_writers);
								}else {
									CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$TopScorer$ImageGrp$img_Player"
											+ "*TEXTURE*IMAGE SET " + "\\\\" + config.getPrimaryIpAddress() + "\\\\" + Constants.ACC_PHOTO_PATH 
											+ Teams.get(tournaments.get(i).getPlayer().getTeamId() - 1).getTeamBadge() + "\\\\" + Constants.CENTER + "\\\\" 
											+ tournaments.get(i).getPlayer().getPhoto() + CricketUtil.PNG_EXTENSION +"\0", print_writers);
								}
								break;
							}
						}else {
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide 
									+"$TopScorer$Rows$Row" + rowId + "$Omo*FUNCTION*Omo*vis_con SET 0\0", print_writers);
						}

						for(int j=0; j<2; j++) {
							
							if(j==0) {
								containerName ="$DeHighlight";
								CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$TopScorer$Data$Rows"
										+ "$Row" + rowId + containerName + "$img_Text1*TEXTURE*IMAGE SET " + Constants.BAN_AFG_SERIES_TEXT3 + Teams.get(tournaments.get(i).getPlayer().getTeamId() - 1).getTeamBadge() + "\0", print_writers);
								
							}else {
								containerName ="$Highlight";
								switch (config.getBroadcaster()) {
								case Constants.BAN_AFG_SERIES:
									CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$TopScorer$Data$Rows"
											+ "$Row" + rowId + containerName + "$img_Base2*TEXTURE*IMAGE SET " + Constants.BAN_AFG_SERIES_BASE2 + 
											Teams.get(tournaments.get(i).getPlayer().getTeamId() - 1).getTeamBadge() + "\0", print_writers);
									break;
								}
								CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$TopScorer$Data$Rows"
										+ "$Row" + rowId + containerName + "$img_Base2*TEXTURE*IMAGE SET " + Constants.BAN_AFG_SERIES_BASE2 + Teams.get(tournaments.get(i).getPlayer().getTeamId() - 1).getTeamBadge() + "\0", print_writers);
								CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$TopScorer$Data$Rows"
										+ "$Row" + rowId + containerName + "$img_Text2*TEXTURE*IMAGE SET " + Constants.BAN_AFG_SERIES_TEXT2 + Teams.get(tournaments.get(i).getPlayer().getTeamId() - 1).getTeamBadge() + "\0", print_writers);
								
							}
							
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide 
									+"$TopScorer$Rows$Row" + rowId + containerName + "$txt_PlayertName*GEOM*TEXT SET " + 
									tournaments.get(i).getPlayer().getFull_name() + "\0", print_writers);
							
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide 
									+"$TopScorer$Rows$Row" + rowId + containerName + "$SelectDataType*FUNCTION*Omo*vis_con SET 1\0", print_writers);
							
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide 
									+"$TopScorer$Rows$Row" + rowId + containerName + "$txt_Stat1*GEOM*TEXT SET "+
									CricketFunctions.generateStrikeRate(tournaments.get(i).getRuns(), tournaments.get(i).getBallsFaced(), 1) + "\0", print_writers);
							
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide 
									+"$TopScorer$Rows$Row" + rowId + containerName + "$txt_TeamName*GEOM*TEXT SET "+
									Teams.get(tournaments.get(i).getPlayer().
											getTeamId() - 1).getTeamName1() +"\0", print_writers);
							
						}
					}
				}
			}else if(whatToProcess.equalsIgnoreCase("Control_Shift_Y")){
				rowId = 0;
				for(int i = 0; i <= tournaments.size() - 1 ; i++) {
					if(tournaments.get(i).getBallsBowled() >= 30) {
						rowId++;
						if(rowId>=6) break;
						
						if(FirstPlayerId == tournaments.get(i).getPlayer().getPlayerId()) {
							
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide 
									+"$TopScorer$Rows$Row" + rowId + "$Omo*FUNCTION*Omo*vis_con SET 1\0", print_writers);
							
							switch (config.getBroadcaster()) {
							case Constants.BAN_AFG_SERIES:
								CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$TopScorer$ImageGrp$LogoBase$"
										+ "img_Base2*TEXTURE*IMAGE SET " + Constants.BAN_AFG_SERIES_BASE2 + Teams.get(tournaments.get(i).getPlayer().getTeamId() - 1).getTeamBadge() + "\0", print_writers);
								
								if(config.getPrimaryIpAddress().equalsIgnoreCase(Constants.LOCALHOST)) {
									CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$TopScorer$ImageGrp$img_Player"
											+ "*TEXTURE*IMAGE SET "+ Constants.BAN_AFG_SERIES_LOCAL_PHOTO_PATH + Teams.get(tournaments.get(i).getPlayer().getTeamId() - 1).getTeamName4() 
											+ "\\\\" + Constants.CENTER + "\\\\" + tournaments.get(i).getPlayer().getPhoto() + CricketUtil.PNG_EXTENSION +"\0", print_writers);
								}else {
									CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$TopScorer$ImageGrp$img_Player"
											+ "*TEXTURE*IMAGE SET " + "\\\\" + config.getPrimaryIpAddress() + "\\\\" + Constants.BAN_AFG_SERIES_PHOTO_PATH 
											+ Teams.get(tournaments.get(i).getPlayer().getTeamId() - 1).getTeamName4() + "\\\\" + Constants.CENTER + "\\\\" 
											+ tournaments.get(i).getPlayer().getPhoto() + CricketUtil.PNG_EXTENSION +"\0", print_writers);
								}
								break;
							case Constants.ACC:
								if(config.getPrimaryIpAddress().equalsIgnoreCase(Constants.LOCALHOST)) {
									CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$TopScorer$ImageGrp$img_Player"
											+ "*TEXTURE*IMAGE SET "+ Constants.ACC_LOCAL_PHOTO_PATH + Teams.get(tournaments.get(i).getPlayer().getTeamId() - 1).getTeamBadge() 
											+ "\\\\" + Constants.CENTER + "\\\\" + tournaments.get(i).getPlayer().getPhoto() + CricketUtil.PNG_EXTENSION +"\0", print_writers);
								}else {
									CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$TopScorer$ImageGrp$img_Player"
											+ "*TEXTURE*IMAGE SET " + "\\\\" + config.getPrimaryIpAddress() + "\\\\" + Constants.ACC_PHOTO_PATH 
											+ Teams.get(tournaments.get(i).getPlayer().getTeamId() - 1).getTeamBadge() + "\\\\" + Constants.CENTER + "\\\\" 
											+ tournaments.get(i).getPlayer().getPhoto() + CricketUtil.PNG_EXTENSION +"\0", print_writers);
								}
								break;
							}
						}else {
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide 
									+"$TopScorer$Rows$Row" + rowId + "$Omo*FUNCTION*Omo*vis_con SET 0\0", print_writers);
						}

						for(int j=0; j<2; j++) {
							
							if(j==0) {
								containerName ="$DeHighlight";
								CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$TopScorer$Data$Rows"
										+ "$Row" + rowId + containerName + "$img_Text1*TEXTURE*IMAGE SET " + Constants.BAN_AFG_SERIES_TEXT3 + Teams.get(tournaments.get(i).getPlayer().getTeamId() - 1).getTeamBadge() + "\0", print_writers);
								
							}else {
								containerName ="$Highlight";
								switch (config.getBroadcaster()) {
								case Constants.BAN_AFG_SERIES:
									CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$TopScorer$Data$Rows"
											+ "$Row" + rowId + containerName + "$img_Base2*TEXTURE*IMAGE SET " + Constants.BAN_AFG_SERIES_BASE2 + 
											Teams.get(tournaments.get(i).getPlayer().getTeamId() - 1).getTeamBadge() + "\0", print_writers);
									break;
								}
								CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$TopScorer$Data$Rows"
										+ "$Row" + rowId + containerName + "$img_Text2*TEXTURE*IMAGE SET " + Constants.BAN_AFG_SERIES_TEXT2 + Teams.get(tournaments.get(i).getPlayer().getTeamId() - 1).getTeamBadge() + "\0", print_writers);
								
							}
							
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide 
									+"$TopScorer$Rows$Row" + rowId + containerName + "$txt_PlayertName*GEOM*TEXT SET " + 
									tournaments.get(i).getPlayer().getFull_name() + "\0", print_writers);
							
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide 
									+"$TopScorer$Rows$Row" + rowId + containerName + "$SelectDataType*FUNCTION*Omo*vis_con SET 1\0", print_writers);
							
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide 
									+"$TopScorer$Rows$Row" + rowId + containerName + "$txt_Stat1*GEOM*TEXT SET "+
									CricketFunctions.getEconomy(tournaments.get(i).getRunsConceded(), tournaments.get(i).getBallsBowled(), 1, "-") + "\0", print_writers);
							
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide 
									+"$TopScorer$Rows$Row" + rowId + containerName + "$txt_TeamName*GEOM*TEXT SET "+
									Teams.get(tournaments.get(i).getPlayer().
											getTeamId() - 1).getTeamName1() +"\0", print_writers);
								
						}
					}
				}
			}else {
				rowId = 0;
				for(int i = 0; i <= tournaments.size() - 1 ; i++) {
					rowId++;
					if(rowId>=6) break;
					
					if(FirstPlayerId == tournaments.get(i).getPlayer().getPlayerId()) {
						
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide 
								+"$TopScorer$Rows$Row" + rowId + "$Omo*FUNCTION*Omo*vis_con SET 1\0", print_writers);
						
						switch (config.getBroadcaster()) {
						case Constants.BAN_AFG_SERIES:
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$TopScorer$ImageGrp$LogoBase$"
									+ "img_Base2*TEXTURE*IMAGE SET " + Constants.BAN_AFG_SERIES_BASE2 + Teams.get(tournaments.get(i).getPlayer().getTeamId() - 1).getTeamBadge() + "\0", print_writers);
							
							if(config.getPrimaryIpAddress().equalsIgnoreCase(Constants.LOCALHOST)) {
								CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide 
										+"$TopScorer$ImageGrp$img_Player*TEXTURE*IMAGE SET "+ Constants.BAN_AFG_SERIES_LOCAL_PHOTO_PATH + Teams.get(tournaments.get(i).getPlayer().
												getTeamId() - 1).getTeamName4() + "\\\\" + Constants.RIGHT + "\\\\" + tournaments.get(i).getPlayer().getPhoto() 
										+ CricketUtil.PNG_EXTENSION +"\0", print_writers);
							}else {
								CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide 
										+"$TopScorer$ImageGrp$img_Player*TEXTURE*IMAGE SET "+ "\\\\"+config.getPrimaryIpAddress()+
											"\\\\" + Constants.BAN_AFG_SERIES_PHOTO_PATH + Teams.get(tournaments.get(i).getPlayer().getTeamId() - 1).getTeamName4() 
											+ "\\\\" + Constants.RIGHT + "\\\\" + tournaments.get(i).getPlayer().getPhoto() 
											+ CricketUtil.PNG_EXTENSION +"\0", print_writers);
							}
							break;
						case Constants.ACC:
							if(config.getPrimaryIpAddress().equalsIgnoreCase(Constants.LOCALHOST)) {
								CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$TopScorer$ImageGrp$img_Player"
										+ "*TEXTURE*IMAGE SET "+ Constants.ACC_LOCAL_PHOTO_PATH + Teams.get(tournaments.get(i).getPlayer().getTeamId() - 1).getTeamBadge() 
										+ "\\\\" + Constants.CENTER + "\\\\" + tournaments.get(i).getPlayer().getPhoto() + CricketUtil.PNG_EXTENSION +"\0", print_writers);
							}else {
								CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$TopScorer$ImageGrp$img_Player"
										+ "*TEXTURE*IMAGE SET " + "\\\\" + config.getPrimaryIpAddress() + "\\\\" + Constants.ACC_PHOTO_PATH 
										+ Teams.get(tournaments.get(i).getPlayer().getTeamId() - 1).getTeamBadge() + "\\\\" + Constants.CENTER + "\\\\" 
										+ tournaments.get(i).getPlayer().getPhoto() + CricketUtil.PNG_EXTENSION +"\0", print_writers);
							}
							break;
						}
						
					}else {
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide 
								+"$TopScorer$Rows$Row" + rowId + "$Omo*FUNCTION*Omo*vis_con SET 0\0", print_writers);
					}
					
					
					for(int j=0; j<2; j++) {
						
						if(j==0) {
							containerName ="$DeHighlight";
							switch (config.getBroadcaster()) {
							case Constants.BAN_AFG_SERIES:
								CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$TopScorer$Data$Rows$Row" 
										+ rowId + containerName + "$img_Text1*TEXTURE*IMAGE SET " + Constants.BAN_AFG_SERIES_TEXT3 + Teams.get(tournaments.get(i).
												getPlayer().getTeamId() - 1).getTeamBadge() + "\0", print_writers);
								break;
							}
						}else {
							containerName ="$Highlight";
							switch (config.getBroadcaster()) {
							case Constants.BAN_AFG_SERIES:
								CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$TopScorer$Data$Rows$Row" 
										+ rowId + containerName + "$img_Base2*TEXTURE*IMAGE SET " + Constants.BAN_AFG_SERIES_BASE2 + Teams.get(tournaments.get(i).
												getPlayer().getTeamId() - 1).getTeamBadge() + "\0", print_writers);
								CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$TopScorer$Data$Rows$Row" 
										+ rowId + containerName + "$img_Text2*TEXTURE*IMAGE SET " + Constants.BAN_AFG_SERIES_TEXT2 + Teams.get(tournaments.get(i).
												getPlayer().getTeamId() - 1).getTeamBadge() + "\0", print_writers);
								break;
							}
						}
						
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide 
								+"$TopScorer$Rows$Row" + rowId + containerName + "$txt_PlayertName*GEOM*TEXT SET "+tournaments.get(i).getPlayer().getFull_name()+"\0", print_writers);
						
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide 
								+"$TopScorer$Rows$Row" + rowId + containerName + "$SelectDataType*FUNCTION*Omo*vis_con SET 1\0", print_writers);
						
						switch (whatToProcess) {
						case "z":
							
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide 
									+"$TopScorer$Rows$Row" + rowId + containerName + "$txt_Stat1*GEOM*TEXT SET "+tournaments.get(i).getRuns()+"\0", print_writers);
							
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide 
									+"$TopScorer$Rows$Row" + rowId + containerName + "$txt_TeamName*GEOM*TEXT SET "+
									Teams.get(tournaments.get(i).getPlayer().getTeamId() - 1).getTeamName3() +"\0", print_writers);
							
							break;
						case "x":
							
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide 
									+"$TopScorer$Rows$Row" + rowId + containerName + "$txt_Stat1*GEOM*TEXT SET "+tournaments.get(i).getWickets()+"\0", print_writers);
							
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide 
									+"$TopScorer$Rows$Row" + rowId + containerName + "$txt_TeamName*GEOM*TEXT SET "+
									Teams.get(tournaments.get(i).getPlayer().getTeamId() - 1).getTeamName3() +"\0", print_writers);
							
							break;
						case "c":
							
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide 
									+"$TopScorer$Rows$Row" + rowId + containerName + "$txt_Stat1*GEOM*TEXT SET "+tournaments.get(i).getFours()+"\0", print_writers);
							
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide 
									+"$TopScorer$Rows$Row" + rowId + containerName + "$txt_TeamName*GEOM*TEXT SET "+
									Teams.get(tournaments.get(i).getPlayer().getTeamId() - 1).getTeamName3() +"\0", print_writers);
							
							break;
						case "v":
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide 
									+"$TopScorer$Rows$Row" + rowId + containerName + "$txt_Stat1*GEOM*TEXT SET "+tournaments.get(i).getSixes()+"\0", print_writers);
							
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide 
									+"$TopScorer$Rows$Row" + rowId + containerName + "$txt_TeamName*GEOM*TEXT SET "+
									Teams.get(tournaments.get(i).getPlayer().getTeamId() - 1).getTeamName3() +"\0", print_writers);
							
							break;
						}
					}
				}
			}
			break;
		case Constants.TRI_SERIES: case Constants.MT20:
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide 
					+ "$select_GraphicsType*FUNCTION*Omo*vis_con SET 12\0", print_writers);
			
			if(whatToProcess.equalsIgnoreCase("Control_z")) {
				rowId = 0;
				for(int i = 0; i <= top_batsman_beststat.size() - 1 ; i++) {
					rowId = rowId + 1;
					if(rowId <= 6) {
						
						if(FirstPlayerId == top_batsman_beststat.get(i).getPlayer().getPlayerId()) {
							
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Main$AllGraphics$Side" + WhichSide + "$LeaderBoard$Rows$" + rowId + "$Highlight$"
									+ "img_Base1*TEXTURE*IMAGE SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_BASE1 : Constants.MT20_BASE1) + Teams.get(top_batsman_beststat.get(i).getPlayer().getTeamId() - 1).getTeamBadge() + "\0", print_writers);
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Main$AllGraphics$Side" + WhichSide + "$LeaderBoard$Rows$" + rowId + "$Highlight$"
									+ "img_Text1*TEXTURE*IMAGE SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_TEXT1 : Constants.MT20_TEXT1) + Teams.get(top_batsman_beststat.get(i).getPlayer().getTeamId() - 1).getTeamBadge() + "\0", print_writers);
							
							
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide 
									+"$LeaderBoard$Rows$"+rowId+"$select_Highlight*FUNCTION*Omo*vis_con SET 1\0", print_writers);
							
							if(config.getPrimaryIpAddress().equalsIgnoreCase(Constants.LOCALHOST)) {
								CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide 
										+"$LeaderBoard$Image$img_Player*TEXTURE*IMAGE SET "+ (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_LOCAL_PHOTO_PATH : Constants.MT20_LOCAL_PHOTO_PATH) + Teams.get(top_batsman_beststat.get(i).getPlayer().
												getTeamId() - 1).getTeamName4() + "\\\\" + Constants.RIGHT + "\\\\" + top_batsman_beststat.get(i).getPlayer().getPhoto() 
										+ CricketUtil.PNG_EXTENSION +"\0", print_writers);
							}else {
								CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide 
										+"$LeaderBoard$Image$img_Player*TEXTURE*IMAGE SET "+ "\\\\"+config.getPrimaryIpAddress()+
											"\\\\" + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_PHOTO_PATH : Constants.MT20_PHOTO_PATH) + Teams.get(top_batsman_beststat.get(i).getPlayer().getTeamId() - 1).getTeamName4() 
											+ "\\\\" + Constants.RIGHT + "\\\\" + top_batsman_beststat.get(i).getPlayer().getPhoto() 
											+ CricketUtil.PNG_EXTENSION +"\0", print_writers);
							}
						}else {
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide 
									+"$LeaderBoard$Rows$"+rowId+"$select_Highlight*FUNCTION*Omo*vis_con SET 0\0", print_writers);
						}
						
						for(int j=0; j<2; j++) {
							
							if(j==0) {
								containerName ="$Dehighlight";
							}else {
								containerName ="$Highlight";
							}
							
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide 
									+"$LeaderBoard$Rows$"+rowId+containerName+"$txt_Name*GEOM*TEXT SET "+top_batsman_beststat.get(i).getPlayer().getFull_name()+"\0", print_writers);
							
							
							
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide 
									+"$LeaderBoard$Rows$"+rowId+containerName+"$txt_Country*GEOM*TEXT SET v "+
									top_batsman_beststat.get(i).getOpponentTeam().getTeamName1() + "  |  " + getOrdinalMatch(top_batsman_beststat.get(i).getMatchNumber()).toUpperCase() + " T20I" +"\0", print_writers);
							
							
							if(top_batsman_beststat.get(i).getBestEquation() % 2 == 0) {
								CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide 
										+"$LeaderBoard$Rows$"+rowId+containerName+"$txt_Fig*GEOM*TEXT SET "+top_batsman_beststat.get(i).getBestEquation() / 2+"\0", print_writers);
								
							}else {
								CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide 
										+"$LeaderBoard$Rows$"+rowId+containerName+"$txt_Fig*GEOM*TEXT SET "+top_batsman_beststat.get(i).getBestEquation() / 2 + "*" +"\0", print_writers);
							}
						}
					}
				}
			}else if(whatToProcess.equalsIgnoreCase("Control_x")) {
				rowId = 0;
				for(int i = 0; i <= top_bowler_beststats.size() - 1 ; i++) {
					rowId = rowId + 1;
					if(rowId <= 6) {
						
						if(FirstPlayerId == top_bowler_beststats.get(i).getPlayer().getPlayerId()) {
							
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Main$AllGraphics$Side" + WhichSide + "$LeaderBoard$Rows$" + rowId + "$Highlight$"
									+ "img_Base1*TEXTURE*IMAGE SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_BASE1 : Constants.MT20_BASE1) + Teams.get(top_bowler_beststats.get(i).getPlayer().getTeamId() - 1).getTeamBadge() + "\0", print_writers);
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Main$AllGraphics$Side" + WhichSide + "$LeaderBoard$Rows$" + rowId + "$Highlight$"
									+ "img_Text1*TEXTURE*IMAGE SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_TEXT1 : Constants.MT20_TEXT1) + Teams.get(top_bowler_beststats.get(i).getPlayer().getTeamId() - 1).getTeamBadge() + "\0", print_writers);
							
							
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide 
									+"$LeaderBoard$Rows$"+rowId+"$select_Highlight*FUNCTION*Omo*vis_con SET 1\0", print_writers);
							
							if(config.getPrimaryIpAddress().equalsIgnoreCase(Constants.LOCALHOST)) {
								CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide 
										+"$LeaderBoard$Image$img_Player*TEXTURE*IMAGE SET "+ (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_LOCAL_PHOTO_PATH : Constants.MT20_LOCAL_PHOTO_PATH) + Teams.get(top_bowler_beststats.get(i).getPlayer().
												getTeamId() - 1).getTeamName4() + "\\\\" + Constants.RIGHT + "\\\\" + top_bowler_beststats.get(i).getPlayer().getPhoto() 
										+ CricketUtil.PNG_EXTENSION +"\0", print_writers);
							}else {
								CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide 
										+"$LeaderBoard$Image$img_Player*TEXTURE*IMAGE SET "+ "\\\\"+config.getPrimaryIpAddress()+
											"\\\\" + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_PHOTO_PATH : Constants.MT20_PHOTO_PATH) + Teams.get(top_bowler_beststats.get(i).getPlayer().getTeamId() - 1).getTeamName4() 
											+ "\\\\" + Constants.RIGHT + "\\\\" + top_bowler_beststats.get(i).getPlayer().getPhoto() 
											+ CricketUtil.PNG_EXTENSION +"\0", print_writers);
							}
						}else {
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide 
									+"$LeaderBoard$Rows$"+rowId+"$select_Highlight*FUNCTION*Omo*vis_con SET 0\0", print_writers);
						}
						
						for(int j=0; j<2; j++) {
							
							if(j==0) {
								containerName ="$Dehighlight";
							}else {
								containerName ="$Highlight";
							}
							
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide 
									+"$LeaderBoard$Rows$"+rowId+containerName+"$txt_Name*GEOM*TEXT SET "+top_bowler_beststats.get(i).getPlayer().getFull_name()+"\0", print_writers);
							
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide 
									+"$LeaderBoard$Rows$"+rowId+containerName+"$txt_Country*GEOM*TEXT SET v "+
									top_bowler_beststats.get(i).getOpponentTeam().getTeamName1() + "  |  " + getOrdinalMatch(top_bowler_beststats.get(i).getMatchNumber()).toUpperCase() + " T20I" +"\0", print_writers);
							
							
							if(top_bowler_beststats.get(i).getBestEquation() % 1000 > 0) {
								
								CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide 
										+"$LeaderBoard$Rows$"+rowId+containerName+"$txt_Fig*GEOM*TEXT SET "+
										((top_bowler_beststats.get(i).getBestEquation() / 1000) +1) 
										+ "-" + (1000 - (top_bowler_beststats.get(i).getBestEquation() % 1000))+"\0", print_writers);
							}
							else if(top_bowler_beststats.get(i).getBestEquation() % 1000 < 0) {
								CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide 
										+"$LeaderBoard$Rows$"+rowId+containerName+"$txt_Fig*GEOM*TEXT SET "+
										(top_bowler_beststats.get(i).getBestEquation() / 1000) 
										+ "-" + Math.abs(top_bowler_beststats.get(i).getBestEquation())+"\0", print_writers);
							}else {
								CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide 
										+"$LeaderBoard$Rows$"+rowId+containerName+"$txt_Fig*GEOM*TEXT SET "+
										(top_bowler_beststats.get(j).getBestEquation() / 1000) + "-" + "0"+"\0", print_writers);
							}
						}
					}
				}
			}else if(whatToProcess.equalsIgnoreCase("Control_Shift_Z")){
				rowId = 0;
				for(int i = 0; i <= tournaments.size() - 1 ; i++) {
					if(tournaments.get(i).getBallsFaced() >= 30) {
						rowId++;
						if(rowId>=6) break;
						
						if(FirstPlayerId == tournaments.get(i).getPlayer().getPlayerId()) {
							
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Main$AllGraphics$Side" + WhichSide + "$LeaderBoard$Rows$" + rowId + "$Highlight$"
									+ "img_Base1*TEXTURE*IMAGE SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_BASE1 : Constants.MT20_BASE1) + Teams.get(tournaments.get(i).getPlayer().getTeamId() - 1).getTeamBadge() + "\0", print_writers);
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Main$AllGraphics$Side" + WhichSide + "$LeaderBoard$Rows$" + rowId + "$Highlight$"
									+ "img_Text1*TEXTURE*IMAGE SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_TEXT1 : Constants.MT20_TEXT1) + Teams.get(tournaments.get(i).getPlayer().getTeamId() - 1).getTeamBadge() + "\0", print_writers);
							
							
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide 
									+"$LeaderBoard$Rows$"+rowId+"$select_Highlight*FUNCTION*Omo*vis_con SET 1\0", print_writers);
							
							if(config.getPrimaryIpAddress().equalsIgnoreCase(Constants.LOCALHOST)) {
								CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide 
										+"$LeaderBoard$Image$img_Player*TEXTURE*IMAGE SET "+ (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_LOCAL_PHOTO_PATH : Constants.MT20_LOCAL_PHOTO_PATH) + Teams.get(tournaments.get(i).getPlayer().
												getTeamId() - 1).getTeamName4() + "\\\\" + Constants.RIGHT + "\\\\" + tournaments.get(i).getPlayer().getPhoto() 
										+ CricketUtil.PNG_EXTENSION +"\0", print_writers);
							}else {
								CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide 
										+"$LeaderBoard$Image$img_Player*TEXTURE*IMAGE SET "+ "\\\\"+config.getPrimaryIpAddress()+
											"\\\\" + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_PHOTO_PATH : Constants.MT20_PHOTO_PATH) + Teams.get(tournaments.get(i).getPlayer().getTeamId() - 1).getTeamName4() 
											+ "\\\\" + Constants.RIGHT + "\\\\" + tournaments.get(i).getPlayer().getPhoto() 
											+ CricketUtil.PNG_EXTENSION +"\0", print_writers);
							}
						}else {
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide 
									+"$LeaderBoard$Rows$"+rowId+"$select_Highlight*FUNCTION*Omo*vis_con SET 0\0", print_writers);
						}

						for(int j=0; j<2; j++) {
							
							if(j==0) {
								containerName ="$Dehighlight";
							}else {
								containerName ="$Highlight";
							}
							
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide 
									+"$LeaderBoard$Rows$"+rowId+containerName+"$txt_Name*GEOM*TEXT SET "+tournaments.get(i).getPlayer().getFull_name()+"\0", print_writers);
							
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide 
									+"$LeaderBoard$Rows$"+rowId+containerName+"$txt_Fig*GEOM*TEXT SET "+CricketFunctions.generateStrikeRate(tournaments.get(i).getRuns(), tournaments.get(i).getBallsFaced(), 1)+"\0", print_writers);
							
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide 
									+"$LeaderBoard$Rows$"+rowId+containerName+"$txt_Country*GEOM*TEXT SET "+
									Teams.get(tournaments.get(i).getPlayer().
											getTeamId() - 1).getTeamName1() +"\0", print_writers);
							
						}
					}
				}
			}else if(whatToProcess.equalsIgnoreCase("Control_Shift_Y")){
				rowId = 0;
				for(int i = 0; i <= tournaments.size() - 1 ; i++) {
					if(tournaments.get(i).getBallsBowled() >= 30) {
						rowId++;
						if(rowId>=6) break;
						
						if(FirstPlayerId == tournaments.get(i).getPlayer().getPlayerId()) {
							
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Main$AllGraphics$Side" + WhichSide + "$LeaderBoard$Rows$" + rowId + "$Highlight$"
									+ "img_Base1*TEXTURE*IMAGE SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_BASE1 : Constants.MT20_BASE1) + Teams.get(tournaments.get(i).getPlayer().getTeamId() - 1).getTeamBadge() + "\0", print_writers);
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Main$AllGraphics$Side" + WhichSide + "$LeaderBoard$Rows$" + rowId + "$Highlight$"
									+ "img_Text1*TEXTURE*IMAGE SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_TEXT1 : Constants.MT20_TEXT1) + Teams.get(tournaments.get(i).getPlayer().getTeamId() - 1).getTeamBadge() + "\0", print_writers);
							
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide 
									+"$LeaderBoard$Rows$"+rowId+"$select_Highlight*FUNCTION*Omo*vis_con SET 1\0", print_writers);
							
							if(config.getPrimaryIpAddress().equalsIgnoreCase(Constants.LOCALHOST)) {
								CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide 
										+"$LeaderBoard$Image$img_Player*TEXTURE*IMAGE SET "+ (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_LOCAL_PHOTO_PATH : Constants.MT20_LOCAL_PHOTO_PATH) + Teams.get(tournaments.get(i).getPlayer().
												getTeamId() - 1).getTeamName4() + "\\\\" + Constants.RIGHT + "\\\\" + tournaments.get(i).getPlayer().getPhoto() 
										+ CricketUtil.PNG_EXTENSION +"\0", print_writers);
							}else {
								CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide 
										+"$LeaderBoard$Image$img_Player*TEXTURE*IMAGE SET "+ "\\\\"+config.getPrimaryIpAddress()+
											"\\\\" + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_PHOTO_PATH : Constants.MT20_PHOTO_PATH) + Teams.get(tournaments.get(i).getPlayer().getTeamId() - 1).getTeamName4() 
											+ "\\\\" + Constants.RIGHT + "\\\\" + tournaments.get(i).getPlayer().getPhoto() 
											+ CricketUtil.PNG_EXTENSION +"\0", print_writers);
							}
						}else {
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide 
									+"$LeaderBoard$Rows$"+rowId+"$select_Highlight*FUNCTION*Omo*vis_con SET 0\0", print_writers);
						}

						for(int j=0; j<2; j++) {
							
							if(j==0) {
								containerName ="$Dehighlight";
							}else {
								containerName ="$Highlight";
							}
							
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide 
									+"$LeaderBoard$Rows$"+rowId+containerName+"$txt_Name*GEOM*TEXT SET "+tournaments.get(i).getPlayer().getFull_name()+"\0", print_writers);
							
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide 
									+"$LeaderBoard$Rows$"+rowId+containerName+"$txt_Fig*GEOM*TEXT SET "+CricketFunctions.getEconomy(tournaments.get(i).getRunsConceded(), tournaments.get(i).getBallsBowled(), 1, "-")+"\0", print_writers);
							
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide 
									+"$LeaderBoard$Rows$"+rowId+containerName+"$txt_Country*GEOM*TEXT SET "+
									Teams.get(tournaments.get(i).getPlayer().
											getTeamId() - 1).getTeamName1() +"\0", print_writers);
								
						}
					}
				}
			}else {
				rowId = 0;
				for(int i = 0; i <= tournaments.size() - 1 ; i++) {
					rowId++;
					if(rowId>=6) break;
					
					if(FirstPlayerId == tournaments.get(i).getPlayer().getPlayerId()) {
						
						
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Main$AllGraphics$Side" + WhichSide + "$LeaderBoard$Rows$" + rowId + "$Highlight$"
								+ "img_Base1*TEXTURE*IMAGE SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_BASE1 : Constants.MT20_BASE1) + Teams.get(tournaments.get(i).getPlayer().getTeamId() - 1).getTeamBadge() + "\0", print_writers);
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Main$AllGraphics$Side" + WhichSide + "$LeaderBoard$Rows$" + rowId + "$Highlight$"
								+ "img_Text1*TEXTURE*IMAGE SET " + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_TEXT1 : Constants.MT20_TEXT1) + Teams.get(tournaments.get(i).getPlayer().getTeamId() - 1).getTeamBadge() + "\0", print_writers);
						
						
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide 
								+"$LeaderBoard$Rows$"+rowId+"$select_Highlight*FUNCTION*Omo*vis_con SET 1\0", print_writers);
						
						if(config.getPrimaryIpAddress().equalsIgnoreCase(Constants.LOCALHOST)) {
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide 
									+"$LeaderBoard$Image$img_Player*TEXTURE*IMAGE SET "+ (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_LOCAL_PHOTO_PATH : Constants.MT20_LOCAL_PHOTO_PATH) + Teams.get(tournaments.get(i).getPlayer().
											getTeamId() - 1).getTeamName4() + "\\\\" + Constants.RIGHT + "\\\\" + tournaments.get(i).getPlayer().getPhoto() 
									+ CricketUtil.PNG_EXTENSION +"\0", print_writers);
						}else {
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide 
									+"$LeaderBoard$Image$img_Player*TEXTURE*IMAGE SET "+ "\\\\"+config.getPrimaryIpAddress()+
										"\\\\" + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_PHOTO_PATH : Constants.MT20_PHOTO_PATH) + Teams.get(tournaments.get(i).getPlayer().getTeamId() - 1).getTeamName4() 
										+ "\\\\" + Constants.RIGHT + "\\\\" + tournaments.get(i).getPlayer().getPhoto() 
										+ CricketUtil.PNG_EXTENSION +"\0", print_writers);
						}
					}else {
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide 
								+"$LeaderBoard$Rows$"+rowId+"$select_Highlight*FUNCTION*Omo*vis_con SET 0\0", print_writers);
					}
					
					
					for(int j=0; j<2; j++) {
						if(j==0) {
							containerName ="$Dehighlight";
						}else {
							containerName ="$Highlight";
						}
						
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide 
								+"$LeaderBoard$Rows$"+rowId+containerName+"$txt_Name*GEOM*TEXT SET "+tournaments.get(i).getPlayer().getFull_name()+"\0", print_writers);
						
						switch (whatToProcess) {
						case "z":
							
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide 
									+"$LeaderBoard$Rows$"+rowId+containerName+"$txt_Fig*GEOM*TEXT SET "+tournaments.get(i).getRuns()+"\0", print_writers);
							
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide 
									+"$LeaderBoard$Rows$"+rowId+containerName+"$txt_Country*GEOM*TEXT SET "+
									Teams.get(tournaments.get(i).getPlayer().getTeamId() - 1).getTeamName3() +"\0", print_writers);
							
							break;
						case "x":
							
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide 
									+"$LeaderBoard$Rows$"+rowId+containerName+"$txt_Fig*GEOM*TEXT SET "+tournaments.get(i).getWickets()+"\0", print_writers);
							
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide 
									+"$LeaderBoard$Rows$"+rowId+containerName+"$txt_Country*GEOM*TEXT SET "+
									Teams.get(tournaments.get(i).getPlayer().getTeamId() - 1).getTeamName3() +"\0", print_writers);
							
							break;
						case "c":
							
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide 
									+"$LeaderBoard$Rows$"+rowId+containerName+"$txt_Fig*GEOM*TEXT SET "+tournaments.get(i).getFours()+"\0", print_writers);
							
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide 
									+"$LeaderBoard$Rows$"+rowId+containerName+"$txt_Country*GEOM*TEXT SET "+
									Teams.get(tournaments.get(i).getPlayer().getTeamId() - 1).getTeamName3() +"\0", print_writers);
							
							break;
						case "v":
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide 
									+"$LeaderBoard$Rows$"+rowId+containerName+"$txt_Fig*GEOM*TEXT SET "+tournaments.get(i).getSixes()+"\0", print_writers);
							
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side" + WhichSide 
									+"$LeaderBoard$Rows$"+rowId+containerName+"$txt_Country*GEOM*TEXT SET "+
									Teams.get(tournaments.get(i).getPlayer().getTeamId() - 1).getTeamName3() +"\0", print_writers);
							
							break;
						}
					}
				}
			}
			break;
		}
		return Constants.OK;
	}
	public String ThisSeriesBody(List<PrintWriter> print_writers,int WhichSide,String whatToProcess ,Configuration config, MatchAllData matchAllData,Inning inning) throws InterruptedException {
		
		String[] TitleData = null;
		String[] StatData = null;
		
		String best = "",data = "",which_role = "";
		
		switch(config.getBroadcaster()) {
		case Constants.AFG_SL_SERIES:
			if(player.getSurname() != null) {
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_Profile$Main$Side" + WhichSide + "$Name$txt_Firstname*GEOM*TEXT SET " + player.getFirstname() + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_Profile$Main$Side" + WhichSide + "$Name$txt_LastName*GEOM*TEXT SET " + player.getSurname() + "\0", print_writers);
			}else {
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_Profile$Main$Side" + WhichSide + "$Name$txt_Firstname*GEOM*TEXT SET \0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_Profile$Main$Side" + WhichSide + "$Name$txt_LastName*GEOM*TEXT SET " + player.getFirstname() + "\0", print_writers);
			}
			
			if(config.getPrimaryIpAddress().equalsIgnoreCase(Constants.LOCALHOST)) {
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_Profile$Main$Side" + WhichSide + "$ImageGrp$img_Player*TEXTURE*IMAGE SET " + Constants.AFG_SL_SERIES_LOCAL_PHOTO_PATH 
						+ "\\\\" + team.getTeamBadge() + "\\\\" + Constants.RIGHT + "\\\\" + player.getPhoto() + CricketUtil.PNG_EXTENSION + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_Profile$Main$Side" + WhichSide + "$ImageGrp$PlayerShadow*TEXTURE*IMAGE SET " + Constants.AFG_SL_SERIES_LOCAL_PHOTO_PATH 
						+ "\\\\" + team.getTeamBadge() + "\\\\" + Constants.RIGHT + "\\\\" + player.getPhoto() + CricketUtil.PNG_EXTENSION + "\0", print_writers);
			}else {
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_Profile$Main$Side" + WhichSide + "$ImageGrp$img_Player*TEXTURE*IMAGE SET " + "\\\\" + config.getPrimaryIpAddress() 
						+ "\\\\" + Constants.AFG_SL_SERIES_PHOTO_PATH + "\\\\" + team.getTeamBadge() + "\\\\" + Constants.RIGHT + "\\\\" + player.getPhoto() + CricketUtil.PNG_EXTENSION + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_Profile$Main$Side" + WhichSide + "$ImageGrp$PlayerShadow*TEXTURE*IMAGE SET " + "\\\\" + config.getPrimaryIpAddress() 
				+ "\\\\" + Constants.AFG_SL_SERIES_PHOTO_PATH + "\\\\" + team.getTeamBadge() + "\\\\" + Constants.RIGHT + "\\\\" + player.getPhoto() + CricketUtil.PNG_EXTENSION + "\0", print_writers);
			}
			
			switch(whatToProcess) {
			case "Shift_P":
				if(player.getBattingStyle().equalsIgnoreCase("LHB")) {
					which_role = "Batsman_Lefthand";
				}else {
					which_role = "Bat_Icon";
				}
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_Profile$Main$Side" + WhichSide + "$Info$txt_Style*GEOM*TEXT SET " + player.getBattingStyle().toUpperCase() + "\0", print_writers);
				
				for(int j=0;j<= top_batsman_beststats.size()-1;j++) {
					if(top_batsman_beststats.get(j).getPlayerId() == player.getPlayerId()) {
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
					}else {
						best = "-";
					}
				}
				
				switch (matchAllData.getSetup().getMatchType()) {
				case CricketUtil.ODI: case CricketUtil.OD:
					data = (tournament.getFifty() == 0 && tournament.getHundreds() == 0 ? "-" : (tournament.getFifty() != 0 ? tournament.getFifty() : "-") + "/" 
							+ (tournament.getHundreds() != 0 ? tournament.getHundreds() : "-"));
					
					TitleData = new String[] {"MATCHES", "RUNS", "50s / 100s", "AVERAGE", "BEST"};
					StatData = new String[] {(tournament.getMatches() != 0 ? String.valueOf(tournament.getMatches()) : "-"), (tournament.getRuns() != 0 ? String.valueOf(tournament.getRuns()) : "-"),
											data, CricketFunctions.getAverage(tournament.getInnings(), tournament.getNot_out(), tournament.getRuns(), 1, "-"),best};
					break;

				default:
					if(tournament.getHundreds() > 0) {
						data = (tournament.getFifty() == 0 && tournament.getHundreds() == 0 ? "-" : (tournament.getFifty() != 0 ? tournament.getFifty() : "-") + "/" 
								+ (tournament.getHundreds() != 0 ? tournament.getHundreds() : "-"));
						
						TitleData = new String[] {"MATCHES", "RUNS", "STRIKE RATE", "50s / 100s", "BEST"};
						StatData = new String[] {(tournament.getMatches() != 0 ? String.valueOf(tournament.getMatches()) : "-"), (tournament.getRuns() != 0 ? String.valueOf(tournament.getRuns()) : "-"),
								CricketFunctions.generateStrikeRate(tournament.getRuns(), tournament.getBallsFaced(), 0),data ,best};
					}else {
						data = (tournament.getThirty() == 0 && tournament.getFifty() == 0 ? "-" : (tournament.getThirty() != 0 ? tournament.getThirty() : "-") + "/" 
								+ (tournament.getFifty() != 0 ? tournament.getFifty() : "-"));
						
						TitleData = new String[] {"MATCHES", "RUNS", "STRIKE RATE", "30s / 50s", "BEST"};
						StatData = new String[] {(tournament.getMatches() != 0 ? String.valueOf(tournament.getMatches()) : "-"), (tournament.getRuns() != 0 ? String.valueOf(tournament.getRuns()) : "-"),
								CricketFunctions.generateStrikeRate(tournament.getRuns(), tournament.getBallsFaced(), 0),data ,best};
					}
					break;
				}
				break;
			case "Shift_Q":
				if(player.getBowlingStyle() == null) {
					which_role = "Bowler";
				}else {
					switch(player.getBowlingStyle()) {
					case "RF": case "RFM": case "RMF": case "RM": case "RSM": case "LF": case "LFM": case "LMF": case "LM":
						which_role = "FastBowler";
						break;
					case "LSL": case "WSL": case "LCH": case "RLG": case "WSR": case "LSO":
						which_role = "SpinBowler";
						break;
					case "ROB":
						which_role = "Off_Spin";
						break;
					case "RLB":
						which_role = "Leg_Spin";
						break;	
					}
				}
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_Profile$Main$Side" + WhichSide + "$Info$txt_Style*GEOM*TEXT SET " + player.getBowlingStyle().toUpperCase() + "\0", print_writers);
				
				for(int j=0;j<= top_bowler_beststats.size()-1;j++) {
					if(top_bowler_beststats.get(j).getPlayerId() == player.getPlayerId()) {
						if(top_bowler_beststats.get(j).getBestEquation() % 1000 > 0) {
							best = ((top_bowler_beststats.get(j).getBestEquation() / 1000) +1) + "-" + (1000 - (top_bowler_beststats.get(j).getBestEquation() % 1000));
						}
						else if(top_bowler_beststats.get(j).getBestEquation() % 1000 < 0) {
							best = (top_bowler_beststats.get(j).getBestEquation() / 1000) + "-" + Math.abs(top_bowler_beststats.get(j).getBestEquation());
						}
						break;
					}else if(top_bowler_beststats.get(j).getPlayerId() != player.getPlayerId()) {
						best = "-";
					}
				}
//				System.out.println("tournament = " + tournament.getWickets());
				TitleData = new String[] {"MATCHES", "WICKETS", "ECONOMY", "3WI", "BEST"};
				StatData = new String[] {(tournament.getMatches() != 0 ? String.valueOf(tournament.getMatches()) : "-"), (tournament.getWickets() != 0 ? String.valueOf(tournament.getWickets()) : "-"), 
						CricketFunctions.getEconomy(tournament.getRunsConceded(), tournament.getBallsBowled(), 2, "-"), (tournament.getThreeWicketHaul() != 0 ? String.valueOf(tournament.getThreeWicketHaul()) : "-"), best};
				break;
			}
			
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_Profile$Main$Side" + WhichSide + "$Info$ing_icon*TEXTURE*IMAGE SET " 
					+ Constants.AFG_SL_SERIES_ICONS + which_role + "\0", print_writers);
			
			for (int i = 0; i < TitleData.length; i++) {
			    CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_Profile$Main$Side" + WhichSide + "$Data$Grp" + (i+1) + "$Highlight$txt_StatHead"
			    		+ "*GEOM*TEXT SET " + TitleData[i] + "\0", print_writers);
			    CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$gfx_Profile$Main$Side" + WhichSide + "$Data$Grp" + (i+1) + "$Highlight$txt_StatValue"
			    		+ "*GEOM*TEXT SET " + StatData[i] + "\0", print_writers);
			}
			break;
		case Constants.BAN_AFG_SERIES: case Constants.ACC:
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$Select*FUNCTION*Omo*vis_con SET 7\0", print_writers);
			
			switch(config.getBroadcaster()) {
			case Constants.BAN_AFG_SERIES:
				if(config.getPrimaryIpAddress().equalsIgnoreCase(Constants.LOCALHOST)) {
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$Profile$Right$img_Player*TEXTURE*IMAGE SET " 
							+ Constants.BAN_AFG_SERIES_LOCAL_PHOTO_PATH + "\\\\" + team.getTeamName4() + "\\\\" + Constants.CENTER + "\\\\" + player.getPhoto()
							+ CricketUtil.PNG_EXTENSION + "\0", print_writers);
				}else {
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$Profile$Right$img_Player*TEXTURE*IMAGE SET " + "\\\\" 
							+ config.getPrimaryIpAddress() + "\\\\" + Constants.BAN_AFG_SERIES_PHOTO_PATH + "\\\\" + team.getTeamName4() + "\\\\" + Constants.CENTER + "\\\\" 
							+ player.getPhoto() + CricketUtil.PNG_EXTENSION + "\0", print_writers);
				}
				break;
			default:
				if(config.getPrimaryIpAddress().equalsIgnoreCase(Constants.LOCALHOST)) {
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$Profile$Right$img_Player*TEXTURE*IMAGE SET " 
							+ Constants.ACC_LOCAL_PHOTO_PATH + "\\\\" + team.getTeamBadge() + "\\\\" + Constants.RIGHT + "\\\\" + player.getPhoto()
							+ CricketUtil.PNG_EXTENSION + "\0", print_writers);
				}else {
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$Profile$Right$img_Player*TEXTURE*IMAGE SET " + "\\\\" 
							+ config.getPrimaryIpAddress() + "\\\\" + Constants.ACC_PHOTO_PATH + "\\\\" + team.getTeamBadge() + "\\\\" + Constants.RIGHT + "\\\\" 
							+ player.getPhoto() + CricketUtil.PNG_EXTENSION + "\0", print_writers);
				}
				break;
			}
			
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$Profile$Right$img_Text1$txt_StatValue*GEOM*TEXT SET \0", print_writers);
//			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$Profile$Bottom_Header$txt_TeamName*GEOM*TEXT SET " 
//					+ player.getFull_name() + "\0", print_writers);
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$Profile$Right$img_Text$txt_PlayerName"
					+ "*GEOM*TEXT SET " + player.getFull_name() + "\0", print_writers);
			
			switch(whatToProcess) {
			case "Shift_P":
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$Profile$Right$img_Text1$txt_StatHead"
						+ "*GEOM*TEXT SET " + CricketFunctions.getbattingstyle(player.getBattingStyle(),CricketUtil.FULL, true, false).toUpperCase() + "\0", print_writers);
				
				for(int j=0;j<= top_batsman_beststats.size()-1;j++) {
					if(top_batsman_beststats.get(j).getPlayerId() == player.getPlayerId()) {
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
					}else {
						best = "-";
					}
				}
				
				switch (matchAllData.getSetup().getMatchType()) {
				case CricketUtil.ODI: case CricketUtil.OD:
					data = (tournament.getFifty() == 0 && tournament.getHundreds() == 0 ? "-" : (tournament.getFifty() != 0 ? tournament.getFifty() : "-") + "/" 
							+ (tournament.getHundreds() != 0 ? tournament.getHundreds() : "-"));
					
					TitleData = new String[] {"MATCHES", "RUNS", "50s / 100s", "AVERAGE", "BEST"};
					StatData = new String[] {(tournament.getMatches() != 0 ? String.valueOf(tournament.getMatches()) : "-"), (tournament.getRuns() != 0 ? String.valueOf(tournament.getRuns()) : "-"),
											data, CricketFunctions.getAverage(tournament.getInnings(), tournament.getNot_out(), tournament.getRuns(), 1, "-"),best};
					break;

				default:
					data = (tournament.getThirty() == 0 && tournament.getFifty() == 0 ? "-" : (tournament.getThirty() != 0 ? tournament.getThirty() : "-") + "/" 
							+ (tournament.getFifty() != 0 ? tournament.getFifty() : "-"));
					
					TitleData = new String[] {"MATCHES", "RUNS", "STRIKE RATE", "30s / 50s", "BEST"};
					StatData = new String[] {(tournament.getMatches() != 0 ? String.valueOf(tournament.getMatches()) : "-"), (tournament.getRuns() != 0 ? String.valueOf(tournament.getRuns()) : "-"),
							CricketFunctions.generateStrikeRate(tournament.getRuns(), tournament.getBallsFaced(), 0),data ,best};
					break;
				}
				
				
				
				break;
			case "Shift_Q":
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$Profile$Right$img_Text1$txt_StatHead"
						+ "*GEOM*TEXT SET " + CricketFunctions.getbowlingstyle(player.getBowlingStyle()).toUpperCase() + "\0", print_writers);
				
				for(int j=0;j<= top_bowler_beststats.size()-1;j++) {
					if(top_bowler_beststats.get(j).getPlayerId() == player.getPlayerId()) {
						if(top_bowler_beststats.get(j).getBestEquation() % 1000 > 0) {
							best = ((top_bowler_beststats.get(j).getBestEquation() / 1000) +1) + "-" + (1000 - (top_bowler_beststats.get(j).getBestEquation() % 1000));
						}
						else if(top_bowler_beststats.get(j).getBestEquation() % 1000 < 0) {
							best = (top_bowler_beststats.get(j).getBestEquation() / 1000) + "-" + Math.abs(top_bowler_beststats.get(j).getBestEquation());
						}
						break;
					}else if(top_bowler_beststats.get(j).getPlayerId() != player.getPlayerId()) {
						best = "-";
					}
				}
//				System.out.println("tournament = " + tournament.getWickets());
				TitleData = new String[] {"MATCHES", "WICKETS", "ECONOMY", "3WI", "BEST"};
				StatData = new String[] {(tournament.getMatches() != 0 ? String.valueOf(tournament.getMatches()) : "-"), (tournament.getWickets() != 0 ? String.valueOf(tournament.getWickets()) : "-"), 
						CricketFunctions.getEconomy(tournament.getRunsConceded(), tournament.getBallsBowled(), 2, "-"), (tournament.getThreeWicketHaul() != 0 ? String.valueOf(tournament.getThreeWicketHaul()) : "-"), best};
				break;
			}
			
			for (int i = 0; i < TitleData.length; i++) {
			    CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$Profile$Left$BatRow" + (i+1) + 
			    		"$img_Text1$txt_SubHead*GEOM*TEXT SET " + TitleData[i] + "\0", print_writers);
			    CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*TREE*$FullFrame_All$Main_GFX$Side" + WhichSide + "$Profile$Left$BatRow" + (i+1) + 
			    		"$ScoreGrp$txt_SubHead*GEOM*TEXT SET " + StatData[i] + "\0", print_writers);
			}
			break;
		case Constants.TRI_SERIES: case Constants.MT20:
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_Profile$Main$DataAll$Data$Side" + WhichSide + "$Row2$txt_Career"
					+ "*GEOM*TEXT SET " + "THIS SERIES" + "\0", print_writers);
			
			if(ImageType.equalsIgnoreCase("WITH")) {
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_Profile$Main$ImageAll$select_Image*FUNCTION*Omo*vis_con SET 1\0", print_writers);
				
				if(config.getPrimaryIpAddress().equalsIgnoreCase(Constants.LOCALHOST)) {
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_Profile$Main$ImageAll$Image$Side" + WhichSide + "$img_Player*TEXTURE*IMAGE SET "
							+ (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_LOCAL_PHOTO_PATH : Constants.MT20_LOCAL_PHOTO_PATH) + "\\\\" + team.getTeamName4() + "\\\\" + Constants.RIGHT + "\\\\" + player.getPhoto()+ CricketUtil.PNG_EXTENSION + "\0", print_writers);
				}else {
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_Profile$Main$ImageAll$Image$Side" + WhichSide + "$img_Player*TEXTURE*IMAGE SET "
							+ "\\\\" + config.getPrimaryIpAddress() + "\\\\" + (config.getBroadcaster().equalsIgnoreCase(Constants.TRI_SERIES) ? Constants.TRI_SERIES_PHOTO_PATH : Constants.MT20_PHOTO_PATH) + "\\\\" + team.getTeamName4() + "\\\\" + Constants.RIGHT + "\\\\" + player.getPhoto()
							+ CricketUtil.PNG_EXTENSION + "\0", print_writers);
				}
			}else {
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_Profile$Main$ImageAll$select_Image*FUNCTION*Omo*vis_con SET 0\0", print_writers);
			}
			
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_Profile$Main$DataAll$Data$Side" + WhichSide + "$Row1$txt_Age"
					+ "*GEOM*TEXT SET " + "" + "\0", print_writers);
			
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_Profile$Main$DataAll$Data$Side" + WhichSide + "$Row3" + 
		    		"$select_Highlight*FUNCTION*Omo*vis_con SET 0\0", print_writers);
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_Profile$Main$DataAll$Data$Side" + WhichSide + "$Row4" + 
		    		"$select_Highlight*FUNCTION*Omo*vis_con SET 0\0", print_writers);
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_Profile$Main$DataAll$Data$Side" + WhichSide + "$Row5" + 
		    		"$select_Highlight*FUNCTION*Omo*vis_con SET 0\0", print_writers);
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_Profile$Main$DataAll$Data$Side" + WhichSide + "$Row6" + 
		    		"$select_Highlight*FUNCTION*Omo*vis_con SET 0\0", print_writers);
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_Profile$Main$DataAll$Data$Side" + WhichSide + "$Row7" + 
		    		"$select_Highlight*FUNCTION*Omo*vis_con SET 0\0", print_writers);
			switch (whatToProcess) {
			case "Shift_P":
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_Profile$Main$DataAll$Data$Side" + WhichSide + "$Row1$txt_Hand"
						+ "*GEOM*TEXT SET " + CricketFunctions.getbattingstyle(player.getBattingStyle(),CricketUtil.FULL, true, false).toUpperCase() + "\0", print_writers);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_Profile$Main$DataAll$Data$Side" + WhichSide + "$Row3" + 
			    		"$Dehighlight$txt_StatHead*GEOM*TEXT SET MATCHES\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_Profile$Main$DataAll$Data$Side" + WhichSide + "$Row4" + 
			    		"$Dehighlight$txt_StatHead*GEOM*TEXT SET RUNS\0", print_writers);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_Profile$Main$DataAll$Data$Side" + WhichSide + "$Row6" + 
			    		"$Dehighlight$txt_StatHead*GEOM*TEXT SET STRIKE RATE\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_Profile$Main$DataAll$Data$Side" + WhichSide + "$Row7" + 
			    		"$Dehighlight$txt_StatHead*GEOM*TEXT SET BEST\0", print_writers);
//				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_Profile$Main$DataAll$Data$Side" + WhichSide + "$Row8" + 
//			    		"$Dehighlight$txt_StatHead*GEOM*TEXT SET MATCHES\0", print_writers);
				
				if(tournament.getHundreds() > 0) {
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_Profile$Main$DataAll$Data$Side" + WhichSide + "$Row5" + 
				    		"$Dehighlight$txt_StatHead*GEOM*TEXT SET 50s/100s\0", print_writers);
				}else {
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_Profile$Main$DataAll$Data$Side" + WhichSide + "$Row5" + 
				    		"$Dehighlight$txt_StatHead*GEOM*TEXT SET 30s/50s\0", print_writers);
				}

				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_Profile$Main$DataAll$Data$Side" + WhichSide + "$Row3" + 
			    		"$Dehighlight$txt_StatValue*GEOM*TEXT SET " + (tournament.getMatches() != 0 ? tournament.getMatches() : "-") + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_Profile$Main$DataAll$Data$Side" + WhichSide + "$Row4" + 
			    		"$Dehighlight$txt_StatValue*GEOM*TEXT SET " + (tournament.getRuns() != 0 ? tournament.getRuns() : "-") + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_Profile$Main$DataAll$Data$Side" + WhichSide + "$Row6" + 
			    		"$Dehighlight$txt_StatValue*GEOM*TEXT SET " + CricketFunctions.generateStrikeRate(tournament.getRuns(), tournament.getBallsFaced(), 0) + "\0", print_writers);
				
				if(tournament.getHundreds() > 0) {
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_Profile$Main$DataAll$Data$Side" + WhichSide + "$Row5" + 
				    		"$Dehighlight$txt_StatValue*GEOM*TEXT SET " + (tournament.getFifty() == 0 && tournament.getHundreds() == 0 ? "-" : (tournament.getFifty() != 0 ? 
									tournament.getFifty() : "-") + "/" + (tournament.getHundreds() != 0 ? tournament.getHundreds() : "-")) + "\0", print_writers);
					
				}else {
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_Profile$Main$DataAll$Data$Side" + WhichSide + "$Row5" + 
				    		"$Dehighlight$txt_StatValue*GEOM*TEXT SET " + (tournament.getThirty() == 0 && tournament.getFifty() == 0 ? "-" : (tournament.getThirty() != 0 ? 
									tournament.getThirty() : "-") + "/" + (tournament.getFifty() != 0 ? tournament.getFifty() : "-")) + "\0", print_writers);
					
				}
				
				for(int j=0;j<= top_batsman_beststats.size()-1;j++) {
					if(top_batsman_beststats.get(j).getPlayerId() == player.getPlayerId()) {
						if(top_batsman_beststats.get(j).getBestEquation() % 2 == 0) {
							if(top_batsman_beststats.get(j).getBestEquation()/2 == 0) {
								
								CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_Profile$Main$DataAll$Data$Side" + WhichSide + "$Row7" + 
							    		"$Dehighlight$txt_StatValue*GEOM*TEXT SET -\0", print_writers);
								
								CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_Profile$Main$DataAll$Data$Side" + WhichSide + "$Row8$txt_CountryName"
										+ "*GEOM*TEXT SET \0", print_writers);
								
							}else {
								CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_Profile$Main$DataAll$Data$Side" + WhichSide + "$Row7" + 
							    		"$Dehighlight$txt_StatValue*GEOM*TEXT SET " +  String.valueOf(top_batsman_beststats.get(j).getBestEquation()/2) + "\0", print_writers);
								CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_Profile$Main$DataAll$Data$Side" + WhichSide + "$Row8$txt_CountryName"
										+ "*GEOM*TEXT SET v " + top_batsman_beststats.get(j).getOpponentTeam().getTeamName3().toUpperCase() + "\0", print_writers);
							}
						}else {
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_Profile$Main$DataAll$Data$Side" + WhichSide + "$Row7" + 
						    		"$Dehighlight$txt_StatValue*GEOM*TEXT SET " +  (top_batsman_beststats.get(j).getBestEquation()-1) / 2 + "*" + "\0", print_writers);
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_Profile$Main$DataAll$Data$Side" + WhichSide + "$Row8$txt_CountryName"
									+ "*GEOM*TEXT SET v " + top_batsman_beststats.get(j).getOpponentTeam().getTeamName3().toUpperCase() + "\0", print_writers);
						}
						break;
					}else {
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_Profile$Main$DataAll$Data$Side" + WhichSide + "$Row7" + 
					    		"$Dehighlight$txt_StatValue*GEOM*TEXT SET -\0", print_writers);
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_Profile$Main$DataAll$Data$Side" + WhichSide + "$Row8$txt_CountryName"
								+ "*GEOM*TEXT SET \0", print_writers);
					}
				}
				
				break;

			case "Shift_Q":
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_Profile$Main$DataAll$Data$Side" + WhichSide + "$Row1$txt_Hand"
						+ "*GEOM*TEXT SET " + CricketFunctions.getbowlingstyle(player.getBowlingStyle()).toUpperCase() + "\0", print_writers);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_Profile$Main$DataAll$Data$Side" + WhichSide + "$Row3" + 
			    		"$Dehighlight$txt_StatHead*GEOM*TEXT SET MATCHES\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_Profile$Main$DataAll$Data$Side" + WhichSide + "$Row4" + 
			    		"$Dehighlight$txt_StatHead*GEOM*TEXT SET WICKETS\0", print_writers);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_Profile$Main$DataAll$Data$Side" + WhichSide + "$Row6" + 
			    		"$Dehighlight$txt_StatHead*GEOM*TEXT SET ECONOMY\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_Profile$Main$DataAll$Data$Side" + WhichSide + "$Row7" + 
			    		"$Dehighlight$txt_StatHead*GEOM*TEXT SET BEST\0", print_writers);
//				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_Profile$Main$DataAll$Data$Side" + WhichSide + "$Row8" + 
//			    		"$Dehighlight$txt_StatHead*GEOM*TEXT SET MATCHES\0", print_writers);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_Profile$Main$DataAll$Data$Side" + WhichSide + "$Row5" + 
			    		"$Dehighlight$txt_StatHead*GEOM*TEXT SET 3WI\0", print_writers);

				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_Profile$Main$DataAll$Data$Side" + WhichSide + "$Row3" + 
			    		"$Dehighlight$txt_StatValue*GEOM*TEXT SET " + (tournament.getMatches() != 0 ? tournament.getMatches() : "-") + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_Profile$Main$DataAll$Data$Side" + WhichSide + "$Row4" + 
			    		"$Dehighlight$txt_StatValue*GEOM*TEXT SET " + (tournament.getRuns() != 0 ? tournament.getWickets() : "-") + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_Profile$Main$DataAll$Data$Side" + WhichSide + "$Row6" + 
			    		"$Dehighlight$txt_StatValue*GEOM*TEXT SET " + CricketFunctions.getEconomy(tournament.getRunsConceded(), tournament.getBallsBowled(), 2, "-") + "\0", print_writers);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_Profile$Main$DataAll$Data$Side" + WhichSide + "$Row5" + 
			    		"$Dehighlight$txt_StatValue*GEOM*TEXT SET " + (tournament.getThreeWicketHaul() != 0 ? tournament.getThreeWicketHaul() : "-") + "\0", print_writers);
				
				
				for(int j=0;j<= top_bowler_beststats.size()-1;j++) {
					if(top_bowler_beststats.get(j).getPlayerId() == player.getPlayerId()) {
						if(top_bowler_beststats.get(j).getBestEquation() % 1000 > 0) {
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_Profile$Main$DataAll$Data$Side" + WhichSide + "$Row7" + 
						    		"$Dehighlight$txt_StatValue*GEOM*TEXT SET " +  ((top_bowler_beststats.get(j).getBestEquation() / 1000) +1) + "-" + 
									(1000 - (top_bowler_beststats.get(j).getBestEquation() % 1000)) + "\0", print_writers);
						}
						else if(top_bowler_beststats.get(j).getBestEquation() % 1000 < 0) {
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_Profile$Main$DataAll$Data$Side" + WhichSide + "$Row7" + 
						    		"$Dehighlight$txt_StatValue*GEOM*TEXT SET " +  (top_bowler_beststats.get(j).getBestEquation() / 1000) + "-" + 
									Math.abs(top_bowler_beststats.get(j).getBestEquation()) + "\0", print_writers);
						}
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_Profile$Main$DataAll$Data$Side" + WhichSide + "$Row8$txt_CountryName"
								+ "*GEOM*TEXT SET v " + top_bowler_beststats.get(j).getOpponentTeam().getTeamName3().toUpperCase() + "\0", print_writers);
						
						break;
					}else if(top_bowler_beststats.get(j).getPlayerId() != player.getPlayerId()) {
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_Profile$Main$DataAll$Data$Side" + WhichSide + "$Row7" + 
					    		"$Dehighlight$txt_StatValue*GEOM*TEXT SET -\0", print_writers);
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_Profile$Main$DataAll$Data$Side" + WhichSide + "$Row8$txt_CountryName"
								+ "*GEOM*TEXT SET \0", print_writers);
						
					}
				}
				break;
			}
			break;
		}
		return Constants.OK;
	}
}
	