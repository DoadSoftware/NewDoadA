package com.cricket.broadcaster;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import com.cricket.captions.Constants;
import com.cricket.model.BattingCard;
import com.cricket.model.BowlingCard;
import com.cricket.model.Configuration;
import com.cricket.model.Dictionary;
import com.cricket.model.FallOfWicket;
import com.cricket.model.ForeignLanguageData;
import com.cricket.model.Inning;
import com.cricket.model.MatchAllData;
import com.cricket.model.MultiLanguageDatabase;
import com.cricket.model.Partnership;
import com.cricket.model.Tournament;
import com.cricket.service.CricketService;
import com.cricket.util.CricketFunctions;
import com.cricket.util.CricketUtil;

public class FC_FF 
{
	public int omo=0,rowId=0,Top_Score=50,whichInning,FirstPlayerId;
	public double Mult=0,ScaleFac1=0,ScaleFac2=0;
	public String containerName,how_out_txt="",Left_Batsman="",Right_Batsman="",WhichType;
	
	public List<ForeignLanguageData> foreignLanguageData,foreignLanguageOmo;
	public ForeignLanguageData howOut;
	
	public void logosAndBaseColor(List<PrintWriter> print_writers, int WhichSide, String whatToProcess, MatchAllData matchAllData, Inning inning) {
		switch(whatToProcess) {
		case "m":
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_Ident$MainGfx$Band$Bottom$img_BadgeBW_A*TEXTURE*IMAGE SET " 
					+ Constants.BCCI_BADGE_BW + matchAllData.getSetup().getHomeTeam().getTeamBadge()+"\0", print_writers);
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_Ident$MainGfx$Band$Bottom$img_BadgeBW_B*TEXTURE*IMAGE SET " 
					+ Constants.BCCI_BADGE_BW + matchAllData.getSetup().getAwayTeam().getTeamBadge()+"\0", print_writers);
			
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_Ident$MainGfx$Band$CenterPart$Team1$img_Base1$img_BadgeLeft*TEXTURE*IMAGE SET " 
					+ Constants.BCCI_BADGE_LEFT + matchAllData.getSetup().getHomeTeam().getTeamBadge()+"\0", print_writers);
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_Ident$MainGfx$Band$CenterPart$Team2$img_Base1$img_BadgeRight*TEXTURE*IMAGE SET " 
					+ Constants.BCCI_BADGE_RIGHT + matchAllData.getSetup().getAwayTeam().getTeamBadge()+"\0", print_writers);
			
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_Ident$MainGfx$Band$CenterPart$Team1$img_Base1*TEXTURE*IMAGE SET " + 
					Constants.BCCI_BASE1 + matchAllData.getSetup().getHomeTeam().getTeamBadge() + "\0", print_writers);
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_Ident$MainGfx$Band$CenterPart$Team2$img_Base1*TEXTURE*IMAGE SET " + 
					Constants.BCCI_BASE1 + matchAllData.getSetup().getAwayTeam().getTeamBadge() + "\0", print_writers);
			
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_Ident$MainGfx$Team1$img_BadgeBW_Shadow*TEXTURE*IMAGE SET " 
					+ Constants.BCCI_BADGE_BW + matchAllData.getSetup().getHomeTeam().getTeamBadge()+"\0", print_writers);
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_Ident$MainGfx$Team1$img_BadgeBW_1*TEXTURE*IMAGE SET " 
					+ Constants.BCCI_BADGE_BW + matchAllData.getSetup().getHomeTeam().getTeamBadge()+"\0", print_writers);
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_Ident$MainGfx$Team1$img_BadgeBW_2*TEXTURE*IMAGE SET " 
					+ Constants.BCCI_BADGE_BW + matchAllData.getSetup().getHomeTeam().getTeamBadge()+"\0", print_writers);
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_Ident$MainGfx$Team1$img_BadgeBW_3*TEXTURE*IMAGE SET " 
					+ Constants.BCCI_BADGE_BW + matchAllData.getSetup().getHomeTeam().getTeamBadge()+"\0", print_writers);
			
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_Ident$MainGfx$Team2$img_BadgeBW_Shadow*TEXTURE*IMAGE SET " 
					+ Constants.BCCI_BADGE_BW + matchAllData.getSetup().getAwayTeam().getTeamBadge()+"\0", print_writers);
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_Ident$MainGfx$Team2$img_BadgeBW_1*TEXTURE*IMAGE SET " 
					+ Constants.BCCI_BADGE_BW + matchAllData.getSetup().getAwayTeam().getTeamBadge()+"\0", print_writers);
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_Ident$MainGfx$Team2$img_BadgeBW_2*TEXTURE*IMAGE SET " 
					+ Constants.BCCI_BADGE_BW + matchAllData.getSetup().getAwayTeam().getTeamBadge()+"\0", print_writers);
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_Ident$MainGfx$Team2$img_BadgeBW_3*TEXTURE*IMAGE SET " 
					+ Constants.BCCI_BADGE_BW + matchAllData.getSetup().getAwayTeam().getTeamBadge()+"\0", print_writers);
			
			break;
		case "Control_F11":
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrames$TeamBadges$Select_LogoType*FUNCTION*Omo*vis_con SET 1\0", print_writers);
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrames$TeamBadges$Side" + WhichSide + "$Select_Logo"
					+ "*FUNCTION*Omo*vis_con SET 2\0", print_writers);
			
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrames$OutWipes$Select_Logo*FUNCTION*Omo*vis_con SET 2\0", print_writers);
			
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrames$OutWipes$DoubleLogo$Team1$img_BadgeBW*TEXTURE*IMAGE SET " 
					+ Constants.BCCI_BADGE_BW + matchAllData.getSetup().getHomeTeam().getTeamBadge()+"\0", print_writers);
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrames$OutWipes$DoubleLogo$Team1$TeamLogoGloss$img_BadgeBW"
					+ "*TEXTURE*IMAGE SET " + Constants.BCCI_BADGE_BW + matchAllData.getSetup().getHomeTeam().getTeamBadge()+"\0", print_writers);
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrames$OutWipes$DoubleLogo$Team2$img_BadgeBW*TEXTURE*IMAGE SET " 
					+ Constants.BCCI_BADGE_BW + matchAllData.getSetup().getAwayTeam().getTeamBadge()+"\0", print_writers);
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrames$OutWipes$DoubleLogo$Team2$TeamLogoGloss$img_BadgeBW"
					+ "*TEXTURE*IMAGE SET " + Constants.BCCI_BADGE_BW + matchAllData.getSetup().getAwayTeam().getTeamBadge()+"\0", print_writers);
			
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrames$TeamBadges$Side" + WhichSide + "$DoubleLogo$Team1$TopLogoBand$ColourBaseOut1"
					+ "$img_Base1_A*TEXTURE*IMAGE SET " + Constants.BCCI_BASE1 + matchAllData.getSetup().getHomeTeam().getTeamBadge() + "\0", print_writers);
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrames$TeamBadges$Side" + WhichSide + "$DoubleLogo$Team1$LogoGrp$img_BadgeBW_Shadow"
					+ "*TEXTURE*IMAGE SET " + Constants.BCCI_BADGE_BW + matchAllData.getSetup().getHomeTeam().getTeamBadge()+"\0", print_writers);
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrames$TeamBadges$Side" + WhichSide + "$DoubleLogo$Team1$LogoGrp$img_BadgeBW"
					+ "*TEXTURE*IMAGE SET " + Constants.BCCI_BADGE_BW + matchAllData.getSetup().getHomeTeam().getTeamBadge()+"\0", print_writers);
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrames$TeamBadges$Side" + WhichSide + "$DoubleLogo$Team1$LogoGrp$TeamLogoGloss$"
					+ "img_BadgeBW*TEXTURE*IMAGE SET " + Constants.BCCI_BADGE_BW + matchAllData.getSetup().getHomeTeam().getTeamBadge()+"\0", print_writers);
			
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrames$TeamBadges$Side" + WhichSide + "$DoubleLogo$Team2$TopLogoBand$ColourBaseOut1"
					+ "$img_Base1_A*TEXTURE*IMAGE SET " + Constants.BCCI_BASE1 + matchAllData.getSetup().getAwayTeam().getTeamBadge() + "\0", print_writers);
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrames$TeamBadges$Side" + WhichSide + "$DoubleLogo$Team2$LogoGrp$img_BadgeBW_Shadow"
					+ "*TEXTURE*IMAGE SET " + Constants.BCCI_BADGE_BW + matchAllData.getSetup().getAwayTeam().getTeamBadge()+"\0", print_writers);
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrames$TeamBadges$Side" + WhichSide + "$DoubleLogo$Team2$LogoGrp$img_BadgeBW"
					+ "*TEXTURE*IMAGE SET " + Constants.BCCI_BADGE_BW + matchAllData.getSetup().getAwayTeam().getTeamBadge()+"\0", print_writers);
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrames$TeamBadges$Side" + WhichSide + "$DoubleLogo$Team2$LogoGrp$TeamLogoGloss$"
					+ "img_BadgeBW*TEXTURE*IMAGE SET " + Constants.BCCI_BADGE_BW + matchAllData.getSetup().getAwayTeam().getTeamBadge()+"\0", print_writers);
			
			break;
		case "F1": case "F2": case "F4":
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrames$TeamBadges$Select_LogoType*FUNCTION*Omo*vis_con SET 1\0", print_writers);
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrames$TeamBadges$Side" + WhichSide + "$Select_Logo*FUNCTION*Omo*vis_con SET 1\0", 
					print_writers);
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrames$OutWipes$Select_Logo*FUNCTION*Omo*vis_con SET 0\0", print_writers);
			
			switch(whatToProcess) {
			case "F1": case "F4":
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrames$TeamBadges$Side" + WhichSide + "$SingleLogo$TopLogoBand$ColourBaseOut1"
						+ "$img_Base1*TEXTURE*IMAGE SET " + Constants.BCCI_BASE1 + inning.getBatting_team().getTeamBadge() + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrames$TeamBadges$Side" + WhichSide + "$SingleLogo$LogoGrp$img_BadgeBW_Shadow"
						+ "*TEXTURE*IMAGE SET " + Constants.BCCI_BADGE_BW + inning.getBatting_team().getTeamBadge()+"\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrames$TeamBadges$Side" + WhichSide + "$SingleLogo$LogoGrp$img_BadgeBW"
						+ "*TEXTURE*IMAGE SET " + Constants.BCCI_BADGE_BW + inning.getBatting_team().getTeamBadge()+"\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrames$TeamBadges$Side" + WhichSide + "$SingleLogo$LogoGrp$TeamLogoGloss$"
						+ "img_BadgeBW*TEXTURE*IMAGE SET " + Constants.BCCI_BADGE_BW + inning.getBatting_team().getTeamBadge()+"\0", print_writers);
				
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrames$OutWipes$SingleLogo$img_BadgeBW*TEXTURE*IMAGE SET " 
						+ Constants.BCCI_BADGE_BW + inning.getBatting_team().getTeamBadge()+"\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrames$OutWipes$SingleLogo$TeamLogoGloss$img_BadgeBW"
						+ "*TEXTURE*IMAGE SET " + Constants.BCCI_BADGE_BW + inning.getBatting_team().getTeamBadge()+"\0", print_writers);
				break;
			case "F2":
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrames$TeamBadges$Side" + WhichSide + "$SingleLogo$TopLogoBand	$ColourBaseOut1"
						+ "$img_Base1*TEXTURE*IMAGE SET " + Constants.BCCI_BASE1 + inning.getBowling_team().getTeamBadge() + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrames$TeamBadges$Side" + WhichSide + "$SingleLogo$LogoGrp$img_BadgeBW_Shadow"
						+ "*TEXTURE*IMAGE SET " + Constants.BCCI_BADGE_BW + inning.getBowling_team().getTeamBadge()+"\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrames$TeamBadges$Side" + WhichSide + "$SingleLogo$LogoGrp$img_BadgeBW"
						+ "*TEXTURE*IMAGE SET " + Constants.BCCI_BADGE_BW + inning.getBowling_team().getTeamBadge()+"\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrames$TeamBadges$Side" + WhichSide + "$SingleLogo$LogoGrp$TeamLogoGloss$"
						+ "img_BadgeBW*TEXTURE*IMAGE SET " + Constants.BCCI_BADGE_BW + inning.getBowling_team().getTeamBadge()+"\0", print_writers);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrames$OutWipes$SingleLogo$img_BadgeBW*TEXTURE*IMAGE SET " 
						+ Constants.BCCI_BADGE_BW + inning.getBowling_team().getTeamBadge()+"\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrames$OutWipes$SingleLogo$TeamLogoGloss$img_BadgeBW"
						+ "*TEXTURE*IMAGE SET " + Constants.BCCI_BADGE_BW + inning.getBowling_team().getTeamBadge()+"\0", print_writers);
				break;
			}
			break;
		}
	}
	
	public String populateHeader(List<PrintWriter> print_writers, int WhichSide, String whatToProcess, MatchAllData matchAllData, Inning inning, Configuration config,
			MultiLanguageDatabase multilanguagedata, List<ForeignLanguageData> foreignLanguageDataList, List<Dictionary> dict) {
		
		switch (config.getBroadcaster()) {
		case Constants.BCCI:
			foreignLanguageData = new ArrayList<ForeignLanguageData>();
			foreignLanguageOmo = new ArrayList<ForeignLanguageData>();
			foreignLanguageData.add(new ForeignLanguageData("", "", "", ""));
			foreignLanguageOmo.add(new ForeignLanguageData("1", "2", "3", "4"));
			
			switch(whatToProcess) {
			case "F1": case "F2": case "F4": case "Control_F11":
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrames$ExtraData$Side" + WhichSide + "$select_GraphicsType"
						+ "*FUNCTION*Omo*vis_con SET 0\0", print_writers);
				break;
			}
			
			switch(whatToProcess) {
			case "m":
				logosAndBaseColor(print_writers, WhichSide, whatToProcess, matchAllData, inning);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_Ident$MainGfx$Band$Glass$TopTextGrp$txt_TopTextBigText1*GEOM*TEXT SET " + 
						matchAllData.getSetup().getHomeTeam().getTeamName4() + " v " + matchAllData.getSetup().getAwayTeam().getTeamName4() + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_Ident$MainGfx$Band$Glass$TopTextGrp$txt_TopTextBigText2*GEOM*TEXT SET " + 
						matchAllData.getSetup().getHomeTeam().getTeamName4() + " v " + matchAllData.getSetup().getAwayTeam().getTeamName4() + "\0", print_writers);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_Ident$MainGfx$Band$txt_SmallText*GEOM*TEXT SET " + 
						matchAllData.getSetup().getHomeTeam().getTeamName4() + " v " + matchAllData.getSetup().getAwayTeam().getTeamName4() + "\0", print_writers);
				
				break;
			
			case "Control_F11":
				logosAndBaseColor(print_writers, WhichSide, whatToProcess, matchAllData, inning);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrames$RightVeilBadge_Text$Side" + WhichSide + "$txt_SmallText*GEOM*TEXT SET " 
						+ matchAllData.getSetup().getHomeTeam().getTeamName4() + " v " + matchAllData.getSetup().getAwayTeam().getTeamName4() + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrames$Header_Subheader$HeaderAll$Side" + WhichSide + "$SmallText$txt_SmallText"
						+ "*GEOM*TEXT SET " + matchAllData.getSetup().getHomeTeam().getTeamName4() + " v " + matchAllData.getSetup().getAwayTeam().getTeamName4() + "\0", print_writers);
				
				//Sub-Header
				CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*BACK_LAYER*TREE*$gfx_FullFrames$Header_Subheader$SubheaderAll$Side" + WhichSide + "$SubHeaderText$"
						+ "Select_Language*FUNCTION*Omo*vis_con SET ", config, Constants.BCCI, print_writers, foreignLanguageData);
				foreignLanguageData = CricketFunctions.AssembleMultiLanguageData(CricketUtil.DICTIONARY, "", multilanguagedata, matchAllData.getSetup().getTournament() + 
						", " + matchAllData.getSetup().getMatchIdent(), "", null, 0, foreignLanguageDataList);
				CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*BACK_LAYER*TREE*$gfx_FullFrames$Header_Subheader$SubheaderAll$Side" + WhichSide + 
						"$SubHeaderText$English$txt_Subheader*GEOM*TEXT SET ", config, Constants.BCCI, print_writers, foreignLanguageData);
				
				//LeftTeamName
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrames$LeftTeamName$Side" + WhichSide + "$Side1$"
						+ "txt_TeamName1*GEOM*TEXT SET \0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrames$LeftTeamName$Side" + WhichSide + "$Side1$"
						+ "txt_TeamName2*GEOM*TEXT SET \0", print_writers);
				
				//RightVeilBadge_Text
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrames$RightVeilBadge_Text$Side" + WhichSide + "$Select_LogoType"
						+ "*FUNCTION*Omo*vis_con SET 2\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrames$RightVeilBadge_Text$Side" + WhichSide + "$Double_Logo$Team1$img_BadgeBW"
						+ "*TEXTURE*IMAGE SET " + Constants.BCCI_BADGE_BW + matchAllData.getSetup().getHomeTeam().getTeamBadge()+"\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrames$RightVeilBadge_Text$Side" + WhichSide + "$Double_Logo$Team2$img_BadgeBW"
						+ "*TEXTURE*IMAGE SET " + Constants.BCCI_BADGE_BW + matchAllData.getSetup().getAwayTeam().getTeamBadge()+"\0", print_writers);
				
				//Header
				CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*BACK_LAYER*TREE*$gfx_FullFrames$Header_Subheader$HeaderAll$Side" + WhichSide + "$Header_Out$"
						+ "HeaderText1$Select_Language*FUNCTION*Omo*vis_con SET ", config, Constants.BCCI, print_writers, foreignLanguageOmo);
				foreignLanguageData = CricketFunctions.AssembleMultiLanguageData(CricketUtil.DICTIONARY, "", multilanguagedata, "MATCH SUMMARY", "", null, 0, foreignLanguageDataList);
				CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*BACK_LAYER*TREE*$gfx_FullFrames$Header_Subheader$HeaderAll$Side" + WhichSide + "$Header_Out$"
						+ "HeaderText1$English$txt_Header1*GEOM*TEXT SET ", config, Constants.BCCI, print_writers, foreignLanguageData);
				
				CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*BACK_LAYER*TREE*$gfx_FullFrames$Header_Subheader$HeaderAll$Side" + WhichSide + "$Header_Out$"
						+ "HeaderText2$Select_Language*FUNCTION*Omo*vis_con SET ", config, Constants.BCCI, print_writers, foreignLanguageOmo);
				foreignLanguageData = CricketFunctions.AssembleMultiLanguageData(CricketUtil.DICTIONARY, "", multilanguagedata, "", "", null, 0, foreignLanguageDataList);
				CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*BACK_LAYER*TREE*$gfx_FullFrames$Header_Subheader$HeaderAll$Side" + WhichSide + "$Header_Out$"
						+ "HeaderText2$English$txt_Header2*GEOM*TEXT SET ", config, Constants.BCCI, print_writers, foreignLanguageData);
				break;
			
			case "F1": case "F2": case "F4":
				//Logos & Color
				logosAndBaseColor(print_writers, WhichSide, whatToProcess, matchAllData, inning);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrames$RightVeilBadge_Text$Side" + WhichSide + "$txt_SmallText*GEOM*TEXT SET " 
						+ matchAllData.getSetup().getHomeTeam().getTeamName4() + " v " + matchAllData.getSetup().getAwayTeam().getTeamName4() + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrames$Header_Subheader$HeaderAll$Side" + WhichSide + "$SmallText$txt_SmallText"
						+ "*GEOM*TEXT SET " + matchAllData.getSetup().getHomeTeam().getTeamName4() + " v " + matchAllData.getSetup().getAwayTeam().getTeamName4() + "\0", print_writers);
				
				switch(whatToProcess) {
				case "F1": case "F4":
					//LeftTeamName
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrames$LeftTeamName$Side" + WhichSide + "$Side1$"
							+ "txt_TeamName1*GEOM*TEXT SET " + inning.getBatting_team().getTeamName4() + "\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrames$LeftTeamName$Side" + WhichSide + "$Side1$"
							+ "txt_TeamName2*GEOM*TEXT SET " + inning.getBatting_team().getTeamName4() + "\0", print_writers);
					
					//RightVeilBadge_Text
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrames$RightVeilBadge_Text$Side" + WhichSide + "$Select_LogoType"
							+ "*FUNCTION*Omo*vis_con SET 1\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrames$RightVeilBadge_Text$Side" + WhichSide + "$Single_Logo$img_BadgeBW"
							+ "*TEXTURE*IMAGE SET " + Constants.BCCI_BADGE_BW + inning.getBatting_team().getTeamBadge() + "\0", print_writers);
					
					//Header
					CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*BACK_LAYER*TREE*$gfx_FullFrames$Header_Subheader$HeaderAll$Side" + WhichSide + "$Header_Out$"
							+ "HeaderText1$Select_Language*FUNCTION*Omo*vis_con SET ", config, Constants.BCCI, print_writers, foreignLanguageOmo);
					foreignLanguageData = CricketFunctions.AssembleMultiLanguageData(CricketUtil.TEAM, "", multilanguagedata, inning.getBatting_team().getTeamName1(), "", null, 
							0, foreignLanguageDataList);
					CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*BACK_LAYER*TREE*$gfx_FullFrames$Header_Subheader$HeaderAll$Side" + WhichSide + "$Header_Out$"
							+ "HeaderText1$English$txt_Header1*GEOM*TEXT SET ", config, Constants.BCCI, print_writers, foreignLanguageData);
					
					CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*BACK_LAYER*TREE*$gfx_FullFrames$Header_Subheader$HeaderAll$Side" + WhichSide + "$Header_Out$"
							+ "HeaderText2$Select_Language*FUNCTION*Omo*vis_con SET ", config, Constants.BCCI, print_writers, foreignLanguageOmo);
					foreignLanguageData = CricketFunctions.AssembleMultiLanguageData(CricketUtil.TEAM, "", multilanguagedata, inning.getBowling_team().getTeamName1(), "", null, 
							0, foreignLanguageDataList);
					CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*BACK_LAYER*TREE*$gfx_FullFrames$Header_Subheader$HeaderAll$Side" + WhichSide + "$Header_Out$"
							+ "HeaderText2$English$txt_Header2*GEOM*TEXT SET ", config, Constants.BCCI, print_writers, foreignLanguageData);
					break;
				case "F2":
					//LeftTeamName
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrames$LeftTeamName$Side" + WhichSide + "$Side1$"
							+ "txt_TeamName1*GEOM*TEXT SET " + inning.getBowling_team().getTeamName4() + "\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrames$LeftTeamName$Side" + WhichSide + "$Side1$"
							+ "txt_TeamName2*GEOM*TEXT SET " + inning.getBowling_team().getTeamName4() + "\0", print_writers);
					
					//RightVeilBadge_Text
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrames$RightVeilBadge_Text$Side" + WhichSide + "$Select_LogoType"
							+ "*FUNCTION*Omo*vis_con SET 1\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrames$RightVeilBadge_Text$Side" + WhichSide + "$Single_Logo$img_BadgeBW"
							+ "*TEXTURE*IMAGE SET " + Constants.BCCI_BADGE_BW + inning.getBowling_team().getTeamBadge()+"\0", print_writers);
					
					
					//Header
					CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*BACK_LAYER*TREE*$gfx_FullFrames$Header_Subheader$HeaderAll$Side" + WhichSide + "$Header_Out$"
							+ "HeaderText1$Select_Language*FUNCTION*Omo*vis_con SET ", config, Constants.BCCI, print_writers, foreignLanguageOmo);
					foreignLanguageData = CricketFunctions.AssembleMultiLanguageData(CricketUtil.TEAM, "", multilanguagedata, inning.getBowling_team().getTeamName1(), "", null, 
							0, foreignLanguageDataList);
					CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*BACK_LAYER*TREE*$gfx_FullFrames$Header_Subheader$HeaderAll$Side" + WhichSide + "$Header_Out$"
							+ "HeaderText1$English$txt_Header1*GEOM*TEXT SET ", config, Constants.BCCI, print_writers, foreignLanguageData);
					
					CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*BACK_LAYER*TREE*$gfx_FullFrames$Header_Subheader$HeaderAll$Side" + WhichSide + "$Header_Out$"
							+ "HeaderText2$Select_Language*FUNCTION*Omo*vis_con SET ", config, Constants.BCCI, print_writers, foreignLanguageOmo);
					foreignLanguageData = CricketFunctions.AssembleMultiLanguageData(CricketUtil.TEAM, "", multilanguagedata, inning.getBatting_team().getTeamName1(), "", null, 
							0, foreignLanguageDataList);
					CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*BACK_LAYER*TREE*$gfx_FullFrames$Header_Subheader$HeaderAll$Side" + WhichSide + "$Header_Out$"
							+ "HeaderText2$English$txt_Header2*GEOM*TEXT SET ", config, Constants.BCCI, print_writers, foreignLanguageData);
					break;
				}
				
				//Sub-Header
				CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*BACK_LAYER*TREE*$gfx_FullFrames$Header_Subheader$SubheaderAll$Side" + WhichSide + "$SubHeaderText$"
						+ "Select_Language*FUNCTION*Omo*vis_con SET ", config, Constants.BCCI, print_writers, foreignLanguageData);
				foreignLanguageData = CricketFunctions.AssembleMultiLanguageData(CricketUtil.DICTIONARY, "", multilanguagedata, matchAllData.getSetup().getTournament() + ", " + 
						matchAllData.getSetup().getMatchIdent() + " - " + (inning.getInningNumber() == 1 || inning.getInningNumber() == 2 ? "1st" : "2nd") + " INNINGS", "", null, 
						0, foreignLanguageDataList);
				CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*BACK_LAYER*TREE*$gfx_FullFrames$Header_Subheader$SubheaderAll$Side" + WhichSide + 
						"$SubHeaderText$English$txt_Subheader*GEOM*TEXT SET ", config, Constants.BCCI, print_writers, foreignLanguageData);	
				break;
			}
			break;

		case Constants.TRI_SERIES:
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$TopTitleGrp$txt_Title"
					+ "*GEOM*TEXT SET " + matchAllData.getSetup().getTournament() + "\0", print_writers);
			
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$TitleGrp$Text"
					+ "$Side" + WhichSide + "$select_HeaderStyle*FUNCTION*Omo*vis_con SET 0\0", print_writers);
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$TitleGrp$Text"
					+ "$Side" + WhichSide + "$Style1$txt_Title1*GEOM*TEXT SET " + inning.getBatting_team().getTeamName1() + "\0", print_writers);
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$TitleGrp$Text"
					+ "$Side" + WhichSide + "$Style1$txt_Title2*GEOM*TEXT SET v " + inning.getBowling_team().getTeamName1() + "\0", print_writers);
	
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$SubTitleGrp$SubTitle"
					+ "$Side" + WhichSide + "$txt_SubTitle*GEOM*TEXT SET " + matchAllData.getSetup().getMatchIdent() + "\0", print_writers);
			
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$LogoGrp$Logo"
					+ "$Side" + WhichSide + "$img_Logo*TEXTURE*IMAGE SET " + Constants.TRI_SERIES_LOGO + inning.getBatting_team().getTeamBadge() + "\0", print_writers);
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Big_Logo"
					+ "$Side" + WhichSide + "$img_Logos_BW*TEXTURE*IMAGE SET " + Constants.TRI_SERIES_LOGO_BW + inning.getBatting_team().getTeamBadge() + "\0", print_writers);
			break;
		}
		
		
		return Constants.OK;
	}
	public String populateFooter(List<PrintWriter> print_writers, int WhichSide, String whatToProcess, MatchAllData matchAllData, Inning inning, Configuration config,
			MultiLanguageDatabase multilanguagedata, List<ForeignLanguageData> foreignLanguageDataList, List<Dictionary> dict) {
		
		switch (config.getBroadcaster()) {
		case Constants.BCCI:
			foreignLanguageData = new ArrayList<ForeignLanguageData>();
			foreignLanguageOmo = new ArrayList<ForeignLanguageData>();
			foreignLanguageData.add(new ForeignLanguageData("", "", "", ""));
			foreignLanguageOmo.add(new ForeignLanguageData("1", "2", "3", "4"));
			
			switch(whatToProcess) {
			case "m":
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_Ident$MainGfx$SponsorGrp$select_Sponsor*FUNCTION*Omo*vis_con SET 0\0", print_writers);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_Ident$All_Venue$BottomInfoIn$select_Language*FUNCTION*Omo*vis_con SET 1\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_Ident$All_Venue$BottomInfoIn$English$txt_Venue*GEOM*TEXT SET " 
						+ matchAllData.getSetup().getVenueName() + "\0", print_writers);
				break;
			
			case "Control_F11":
				//Sponsor
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrames$SponsorGrp$Side" + WhichSide + "$Select_SponsorTye"
						+ "*FUNCTION*Omo*vis_con SET 0\0", print_writers);
				
				//Footer
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrames$Footer$Side" + WhichSide + "$Select_FooterType"
						+ "*FUNCTION*Omo*vis_con SET 3\0", print_writers);
				
				CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*BACK_LAYER*TREE*$gfx_FullFrames$Footer$Side" + WhichSide + "$Style3$BottomInfo$"
						+ "select_Language*FUNCTION*Omo*vis_con SET ", config, Constants.BCCI, print_writers, foreignLanguageOmo);
//				howOut = CricketFunctions.generateMatchResultForeignLanguage(matchAllData, CricketUtil.FULL, multilanguagedata);
//				foreignLanguageData = CricketFunctions.AssembleMultiLanguageData(CricketUtil.DICTIONARY, "", multilanguagedata, CricketFunctions.GenerateMatchSummaryStatus(inning.getInningNumber(), matchAllData, CricketUtil.FULL, "", 
//						Constants.BCCI, false).getTargetOrResult().toUpperCase(), 
//						"", null, 0, foreignLanguageDataList);
				
//				foreignLanguageData.add(new ForeignLanguageData(howOut.getEnglishText(), howOut.getHindiText(), howOut.getTamilText(), howOut.getTeluguText()));
//				how_out_txt = CricketFunctions.GetVariousLanguageTextToEachViz(config, Constants.BCCI, print_writers, foreignLanguageData);
//				
//				foreignLanguageData = CricketFunctions.AssembleMultiLanguageData("", "", multilanguagedata, how_out_txt, "", null, 
//						0, foreignLanguageDataList);
//				CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*BACK_LAYER*TREE*$gfx_FullFrames$Footer$Side" + WhichSide + "$Style3$BottomInfo$English"
//						+ "$txt_Info*GEOM*TEXT SET ", config, Constants.BCCI, print_writers, foreignLanguageData);
				
//				String matchStatus1 = CricketFunctions .GenerateMatchSummaryStatus(inning.getInningNumber(),matchAllData,CricketUtil.FULL, "",Constants.BCCI,false)
//				        .getTargetOrResult().toUpperCase();
//				
//				String[] words = matchStatus1.split(" ");
//
//				StringBuilder finalTamil = new StringBuilder();
//
//				List<ForeignLanguageData> tempList;
//
//				for (int i = 0; i < words.length; i++) {
//
//				    String word = words[i];
//
//				    // ✅ number → skip
//				    if (word.matches("\\d+(\\.\\d+)?")) {
//				        finalTamil.append(word).append(" ");
//				        continue;
//				    }
//
//				    ForeignLanguageData data = null;
//
//				    // =========================
//				    // ✅ 1. TRY 2-WORD COMBINATION
//				    // =========================
//				    if (i < words.length - 1) {
//
//				        String twoWord = word + " " + words[i + 1];
//
//				        tempList = CricketFunctions.AssembleMultiLanguageData(
//				                CricketUtil.TEAM,
//				                "",
//				                multilanguagedata,
//				                twoWord,
//				                "",
//				                null,
//				                1,
//				                new ArrayList<>()
//				        );
//
//				        data = tempList.get(0);
//
//				        if (data.getEnglishText() != null && !data.getEnglishText().isEmpty()) {
//				            finalTamil.append(data.getTamilText()).append(" ");
//				            i++; // 🔥 skip next word
//				            continue;
//				        }
//				    }
//
//				    // =========================
//				    // ✅ 2. TEAM (single word)
//				    // =========================
//				    tempList = CricketFunctions.AssembleMultiLanguageData(
//				            CricketUtil.TEAM,
//				            "",
//				            multilanguagedata,
//				            word,
//				            "",
//				            null,
//				            1,
//				            new ArrayList<>()
//				    );
//
//				    data = tempList.get(0);
//
//				    if (data.getEnglishText() == null || data.getEnglishText().isEmpty()) {
//
//				        tempList = CricketFunctions.AssembleMultiLanguageData(
//				                CricketUtil.PLAYER,
//				                CricketUtil.FULLNAME,
//				                multilanguagedata,
//				                word,
//				                "",
//				                null,
//				                1,
//				                new ArrayList<>()
//				        );
//
//				        data = tempList.get(0);
//				    }
//				    if (data.getEnglishText() == null || data.getEnglishText().isEmpty()) {
//
//				        tempList = CricketFunctions.AssembleMultiLanguageData(
//				                CricketUtil.DICTIONARY,
//				                "",
//				                multilanguagedata,
//				                word,
//				                "",
//				                null,
//				                1,
//				                new ArrayList<>()
//				        );
//
//				        data = tempList.get(0);
//				    }
//				    if (data.getTamilText() == null || data.getTamilText().isEmpty()) {
//				        finalTamil.append(word).append(" ");
//				    } else {
//				        finalTamil.append(data.getTamilText()).append(" ");
//				    }
//				}
//				String finalTamilSentence = finalTamil.toString().trim();
//				System.out.println(finalTamilSentence);
				String matchStatus1 = CricketFunctions .GenerateMatchSummaryStatus(inning.getInningNumber(),matchAllData,CricketUtil.FULL, "",Constants.BCCI,false)
		        .getTargetOrResult().toUpperCase();
				
//				ForeignLanguageData data = CricketFunctions.universalMatchParser( matchStatus1,CricketUtil.FULLNAME, multilanguagedata);
//				List<ForeignLanguageData> list = new ArrayList<>();
//				list.add(data);
//				CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*BACK_LAYER*TREE*$gfx_FullFrames$Footer$Side" + WhichSide + "$Style3$BottomInfo$English"
//						+ "$txt_Info*GEOM*TEXT SET ", config, Constants.BCCI, print_writers, list);
				
//				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrames$Footer$Side" + WhichSide + "$Style3$BottomInfo$English"
//						+ "$txt_Info*GEOM*TEXT SET " + CricketFunctions.universalMatchParser(matchStatus1, CricketUtil.FULLNAME, multilanguagedata).getTamilText() + "\0", print_writers);
				break;
			case "F1": case "F2": case "F4":
				//Sponsor
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrames$SponsorGrp$Side" + WhichSide + "$Select_SponsorTye"
						+ "*FUNCTION*Omo*vis_con SET 0\0", print_writers);
				
				//Footer
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrames$Footer$Side" + WhichSide + "$Select_FooterType"
						+ "*FUNCTION*Omo*vis_con SET 1\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrames$Footer$Side" + WhichSide + "$Style1$TeamScoreGrp$"
						+ "txt_TeamScore*GEOM*TEXT SET " + CricketFunctions.getTeamScore(inning, "-", false) + "\0", print_writers);
				
				CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*BACK_LAYER*TREE*$gfx_FullFrames$Footer$Side" + WhichSide + "$Style1$OversGrp$Stat$"
						+ "select_Language*FUNCTION*Omo*vis_con SET ", config, Constants.BCCI, print_writers, foreignLanguageOmo);
				foreignLanguageData = CricketFunctions.AssembleMultiLanguageData(CricketUtil.DICTIONARY, "", multilanguagedata, "OVERS", "", null, 0, foreignLanguageDataList);
				CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*BACK_LAYER*TREE*$gfx_FullFrames$Footer$Side" + WhichSide + "$Style1$OversGrp$Stat$English"
						+ "$txt_OversHead*GEOM*TEXT SET ", config, Constants.BCCI, print_writers, foreignLanguageData);
				
				CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*BACK_LAYER*TREE*$gfx_FullFrames$Footer$Side" + WhichSide + "$Style1$ExtrasGrp$Stat$"
						+ "select_Language*FUNCTION*Omo*vis_con SET ", config, Constants.BCCI, print_writers, foreignLanguageOmo);
				foreignLanguageData = CricketFunctions.AssembleMultiLanguageData(CricketUtil.DICTIONARY, "", multilanguagedata, "EXTRAS", "", null, 0, foreignLanguageDataList);
				CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*BACK_LAYER*TREE*$gfx_FullFrames$Footer$Side" + WhichSide + "$Style1$ExtrasGrp$Stat$English"
						+ "$txt_ExtrasHead*GEOM*TEXT SET ", config, Constants.BCCI, print_writers, foreignLanguageData);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrames$Footer$Side" + WhichSide + "$Style1$OversGrp$txt_Overs"
						+ "*GEOM*TEXT SET " + CricketFunctions.OverBalls(inning.getTotalOvers(), inning.getTotalBalls()) + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrames$Footer$Side" + WhichSide + "$Style1$ExtrasGrp$txt_Extras"
						+ "*GEOM*TEXT SET " + inning.getTotalExtras() + "\0", print_writers);
				
				break;
			}
			break;

		case Constants.TRI_SERIES:
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$Footer"
					+ "$Side" + WhichSide + "$select_FooterStyle*FUNCTION*Omo*vis_con SET 0\0", print_writers);
			
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$Footer"
					+ "$Side" + WhichSide + "$FooterStyle1$Extras$Extras$txt_StatHead*GEOM*TEXT SET Extras\0", print_writers);
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$Footer"
					+ "$Side" + WhichSide + "$FooterStyle1$Extras$Extras$txt_StatValue*GEOM*TEXT SET " + inning.getTotalExtras() + "\0", print_writers);
			
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$Footer"
					+ "$Side" + WhichSide + "$FooterStyle1$Overs$Overs$txt_StatHead*GEOM*TEXT SET Overs\0", print_writers);
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$Footer"
					+ "$Side" + WhichSide + "$FooterStyle1$Overs$Overs$txt_StatValue*GEOM*TEXT SET " + 
					CricketFunctions.OverBalls(inning.getTotalOvers(), inning.getTotalBalls()) + "\0", print_writers);
			
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$Footer"
					+ "$Side" + WhichSide + "$FooterStyle1$Total$txt_TotalScore*GEOM*TEXT SET " + 
					CricketFunctions.getTeamScore(inning, "-", false) + "\0", print_writers);
			
			break;
		}
		return Constants.OK;
	}
	
	public String ScorecardBody(List<PrintWriter> print_writers, int WhichSide, Configuration config, MatchAllData matchAllData,Inning inning,
			MultiLanguageDatabase multilanguagedata, List<ForeignLanguageData> foreignLanguageDataList, List<Dictionary> dict) throws Exception
	{
		foreignLanguageData = new ArrayList<ForeignLanguageData>();
		foreignLanguageOmo = new ArrayList<ForeignLanguageData>();
		foreignLanguageData.add(new ForeignLanguageData("", "", "", ""));
		foreignLanguageOmo.add(new ForeignLanguageData("1", "2", "3", "4"));
		
		Collections.sort(inning.getBattingCard());
		rowId = 0;
		
		CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrames$AllGraphics$Side" + WhichSide + "$Select_GfxType"
				+ "*FUNCTION*Omo*vis_con SET 0\0", print_writers);
		CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrames$AllGraphics$Side" + WhichSide + "$Normal_BattingCard$BatDataGrid"
				+ "*FUNCTION*Grid*num_row SET " + inning.getBattingCard().size() + "\0", print_writers);
		
		for (BattingCard bc : inning.getBattingCard()) {
			rowId++;
			switch (bc.getStatus().toUpperCase()) {
			case CricketUtil.STILL_TO_BAT:
				if(bc.getHowOut() == null) {
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrames$AllGraphics$Side" + WhichSide + "$Normal_BattingCard$BatDataGrid" 
							+ "$FullBatRow" + rowId + "$Select_Row_Type*FUNCTION*Omo*vis_con SET 0\0", print_writers);
					
					CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*BACK_LAYER*TREE*$gfx_FullFrames$AllGraphics$Side" + WhichSide + "$Normal_BattingCard$"
							+ "FullBatRow" + rowId + "$Still_To_Bat$PlayerName$select_Language*FUNCTION*Omo*vis_con SET ", config, Constants.BCCI, print_writers, foreignLanguageOmo);
					foreignLanguageData = CricketFunctions.AssembleMultiLanguageData(CricketUtil.PLAYER, CricketUtil.TICKERNAME, multilanguagedata, bc.getPlayer().getTicker_name(), 
							"", null, 0, foreignLanguageDataList);
					CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*BACK_LAYER*TREE*$gfx_FullFrames$AllGraphics$Side" + WhichSide + "$Normal_BattingCard$FullBatRow" + 
							rowId + "$Still_To_Bat$PlayerName$English$txt_BatterName*GEOM*TEXT SET ", config, Constants.BCCI, print_writers, foreignLanguageData);
					
					if(!CricketFunctions.checkBatAndBallImpactInOutPlayer(matchAllData.getEventFile().getEvents(), bc.getPlayerId()).isEmpty()) {
						switch(CricketFunctions.checkBatAndBallImpactInOutPlayer(matchAllData.getEventFile().getEvents(), bc.getPlayerId())) {
						case "IN":
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrames$AllGraphics$Side" + WhichSide + "$Normal_BattingCard$FullBatRow" 
									+ rowId + "$Still_To_Bat$select_Concuss*FUNCTION*Omo*vis_con SET 3\0", print_writers);
							break;
						case "OUT":
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrames$AllGraphics$Side" + WhichSide + "$Normal_BattingCard$FullBatRow" 
									+ rowId + "$Still_To_Bat$select_Concuss*FUNCTION*Omo*vis_con SET 4\0", print_writers);
							break;
						}
					}else {
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrames$AllGraphics$Side" + WhichSide + "$Normal_BattingCard$FullBatRow" + 
								rowId + "$Still_To_Bat$select_Concuss*FUNCTION*Omo*vis_con SET 0\0", print_writers);
					}

					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrames$AllGraphics$Side" + WhichSide + "$Normal_BattingCard$FullBatRow" + 
							rowId + "$Still_To_Bat$StatAll$Stat$select_Language*FUNCTION*Omo*vis_con SET 1\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrames$AllGraphics$Side" + WhichSide + "$Normal_BattingCard$FullBatRow" + 
							rowId + "$Still_To_Bat$StatAll$Stat$English$txt_StatHead*GEOM*TEXT SET \0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrames$AllGraphics$Side" + WhichSide + "$Normal_BattingCard$FullBatRow" + 
							rowId + "$Still_To_Bat$StatAll$txt_StatValue*GEOM*TEXT SET \0", print_writers);
				}
				else if(bc.getHowOut().equalsIgnoreCase(CricketUtil.RETIRED_HURT)) {
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrames$AllGraphics$Side" + WhichSide + "$Normal_BattingCard$BatDataGrid" 
							+ "$FullBatRow" + rowId + "$Select_Row_Type*FUNCTION*Omo*vis_con SET 1\0", print_writers);
					
					CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*BACK_LAYER*TREE*$gfx_FullFrames$AllGraphics$Side" + WhichSide + "$Normal_BattingCard$"
							+ "FullBatRow" + rowId + "$Out$PlayerName$select_Language*FUNCTION*Omo*vis_con SET ", config, Constants.BCCI, print_writers, foreignLanguageOmo);
					foreignLanguageData = CricketFunctions.AssembleMultiLanguageData(CricketUtil.PLAYER, CricketUtil.TICKERNAME, multilanguagedata, bc.getPlayer().getFull_name(), 
							"", null, 0, foreignLanguageDataList);
					CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*BACK_LAYER*TREE*$gfx_FullFrames$AllGraphics$Side" + WhichSide + "$Normal_BattingCard$FullBatRow" + 
							rowId + "$Out$PlayerName$English$txt_BatterName*GEOM*TEXT SET ", config, Constants.BCCI, print_writers, foreignLanguageData);
					
					if(!CricketFunctions.checkBatAndBallImpactInOutPlayer(matchAllData.getEventFile().getEvents(), bc.getPlayerId()).isEmpty()) {
						switch(CricketFunctions.checkBatAndBallImpactInOutPlayer(matchAllData.getEventFile().getEvents(), bc.getPlayerId())) {
						case "IN":
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrames$AllGraphics$Side" + WhichSide + "$Normal_BattingCard$"
									+ "FullBatRow" + rowId + "$Out$select_Concuss*FUNCTION*Omo*vis_con SET 3\0", print_writers);
							break;
						case "OUT":
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrames$AllGraphics$Side" + WhichSide + "$Normal_BattingCard$"
									+ "FullBatRow" + rowId + "$Out$select_Concuss*FUNCTION*Omo*vis_con SET 4\0", print_writers);
							break;
						}
					}else {
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrames$AllGraphics$Side" + WhichSide + "$Normal_BattingCard$"
								+ "FullBatRow" + rowId + "$Out$select_Concuss*FUNCTION*Omo*vis_con SET 0\0", print_writers);
					}
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrames$AllGraphics$Side" + WhichSide + "$Normal_BattingCard$FullBatRow" + 
							rowId + "$Out$BatScoreGrp$txt_Runs*GEOM*TEXT SET " + bc.getRuns() + "\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrames$AllGraphics$Side" + WhichSide + "$Normal_BattingCard$FullBatRow" + 
							rowId + "$Out$BatScoreGrp$txt_Balls*GEOM*TEXT SET " + bc.getBalls() + "\0", print_writers);
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrames$AllGraphics$Side" + WhichSide + "$Normal_BattingCard$FullBatRow" + 
							rowId  + "$Out$How_Out_1$txt_OutType*GEOM*TEXT SET " + bc.getHowOut().replace("_", " ").toLowerCase() + "\0", print_writers);
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrames$AllGraphics$Side" + WhichSide + "$Normal_BattingCard$FullBatRow" + 
							rowId  + "$Out$How_Out_1$FielderName$select_Language*FUNCTION*Omo*vis_con SET 1\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrames$AllGraphics$Side" + WhichSide + "$Normal_BattingCard$FullBatRow" + 
							rowId + "$Out$How_Out_1$FielderName$English$txt_FielderName*GEOM*TEXT SET \0", print_writers);
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrames$AllGraphics$Side" + WhichSide + "$Normal_BattingCard$FullBatRow" + 
							rowId + "$Out$How_Out_2$BowlerName$txt_Bold*GEOM*TEXT SET \0", print_writers);
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrames$AllGraphics$Side" + WhichSide + "$Normal_BattingCard$FullBatRow" + 
							rowId + "$Out$How_Out_2$BowlerName$BowlerName$select_Language*FUNCTION*Omo*vis_con SET 1\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrames$AllGraphics$Side" + WhichSide + "$Normal_BattingCard$FullBatRow" + 
							rowId + "$Out$How_Out_2$BowlerName$BowlerName$English$txt_BowlerName*GEOM*TEXT SET \0", print_writers);
					
				}else {
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrames$AllGraphics$Side" + WhichSide + "$Normal_BattingCard$BatDataGrid" 
							+ "$FullBatRow" + rowId + "$Select_Row_Type*FUNCTION*Omo*vis_con SET 1\0", print_writers);
					
					CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*BACK_LAYER*TREE*$gfx_FullFrames$AllGraphics$Side" + WhichSide + "$Normal_BattingCard$"
							+ "FullBatRow" + rowId + "$Out$PlayerName$select_Language*FUNCTION*Omo*vis_con SET ", config, Constants.BCCI, print_writers, foreignLanguageOmo);
					foreignLanguageData = CricketFunctions.AssembleMultiLanguageData(CricketUtil.PLAYER, CricketUtil.TICKERNAME, multilanguagedata, bc.getPlayer().getTicker_name(), 
							"", null, 0, foreignLanguageDataList);
					CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*BACK_LAYER*TREE*$gfx_FullFrames$AllGraphics$Side" + WhichSide + "$Normal_BattingCard$"
							+ "FullBatRow" + rowId + "$Out$PlayerName$English$txt_BatterName*GEOM*TEXT SET ", config, Constants.BCCI, print_writers, foreignLanguageData);
					
					if(!CricketFunctions.checkBatAndBallImpactInOutPlayer(matchAllData.getEventFile().getEvents(), bc.getPlayerId()).isEmpty()) {
						switch(CricketFunctions.checkBatAndBallImpactInOutPlayer(matchAllData.getEventFile().getEvents(), bc.getPlayerId())) {
						case "IN":
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrames$AllGraphics$Side" + WhichSide + "$Normal_BattingCard$FullBatRow" + 
									rowId + "$Out$select_Concuss*FUNCTION*Omo*vis_con SET 3\0", print_writers);
							break;
						case "OUT":
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrames$AllGraphics$Side" + WhichSide + "$Normal_BattingCard$FullBatRow" + 
									rowId + "$Out$select_Concuss*FUNCTION*Omo*vis_con SET 4\0", print_writers);
							break;
						}
					}else {
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrames$AllGraphics$Side" + WhichSide + "$Normal_BattingCard$FullBatRow" + 
								rowId + "$Out$select_Concuss*FUNCTION*Omo*vis_con SET 0\0", print_writers);
					}
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrames$AllGraphics$Side" + WhichSide + "$Normal_BattingCard$FullBatRow" + 
							rowId + "$Out$BatScoreGrp$txt_Runs*GEOM*TEXT SET " + bc.getRuns() + "\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrames$AllGraphics$Side" + WhichSide + "$Normal_BattingCard$FullBatRow" + 
							rowId + "$Out$BatScoreGrp$txt_Balls*GEOM*TEXT SET " + bc.getBalls() + "\0", print_writers);
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrames$AllGraphics$Side" + WhichSide + "$Normal_BattingCard$FullBatRow" + 
							rowId + "$Out$How_Out_1$txt_OutType*GEOM*TEXT SET " + bc.getHowOut().replace("_", " ").toLowerCase() + "\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrames$AllGraphics$Side" + WhichSide + "$Normal_BattingCard$FullBatRow" + 
							rowId + "$Out$How_Out_1$FielderName$select_Language*FUNCTION*Omo*vis_con SET 1\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrames$AllGraphics$Side" + WhichSide + "$Normal_BattingCard$FullBatRow" + 
							rowId + "$Out$How_Out_1$FielderName$English$txt_FielderName*GEOM*TEXT SET \0", print_writers);
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrames$AllGraphics$Side" + WhichSide + "$Normal_BattingCard$FullBatRow" + 
							rowId + "$Out$How_Out_2$BowlerName$txt_Bold*GEOM*TEXT SET \0", print_writers);
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrames$AllGraphics$Side" + WhichSide + "$Normal_BattingCard$FullBatRow" + 
							rowId + "$Out$How_Out_2$BowlerName$BowlerName$select_Language*FUNCTION*Omo*vis_con SET 1\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrames$AllGraphics$Side" + WhichSide + "$Normal_BattingCard$FullBatRow" + 
							rowId + "$Out$How_Out_2$BowlerName$BowlerName$English$txt_BowlerName*GEOM*TEXT SET \0", print_writers);
				}
				break;
			default:
				switch (bc.getStatus().toUpperCase()) {
				case CricketUtil.OUT:
					omo = 1;
					containerName = "$Out";
					break;
				case CricketUtil.NOT_OUT:
					omo = 2;
					containerName = "$NotOut";
					break;
				}
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrames$AllGraphics$Side" + WhichSide + "$Normal_BattingCard$BatDataGrid" 
						+ "$FullBatRow" + rowId + "$Select_Row_Type*FUNCTION*Omo*vis_con SET " + omo + "\0", print_writers);
				
				CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*BACK_LAYER*TREE*$gfx_FullFrames$AllGraphics$Side" + WhichSide + "$Normal_BattingCard$FullBatRow" + 
						rowId + containerName + "$PlayerName$select_Language*FUNCTION*Omo*vis_con SET ", config, Constants.BCCI, print_writers, foreignLanguageOmo);
				foreignLanguageData = CricketFunctions.AssembleMultiLanguageData(CricketUtil.PLAYER, CricketUtil.TICKERNAME, multilanguagedata, bc.getPlayer().getTicker_name(), 
						"", null, 0, foreignLanguageDataList);
				CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*BACK_LAYER*TREE*$gfx_FullFrames$AllGraphics$Side" + WhichSide + "$Normal_BattingCard$FullBatRow" + 
						rowId + containerName + "$PlayerName$English$txt_BatterName*GEOM*TEXT SET ", config, Constants.BCCI, print_writers, foreignLanguageData);
				
				if(!CricketFunctions.checkBatAndBallImpactInOutPlayer(matchAllData.getEventFile().getEvents(), bc.getPlayerId()).isEmpty()) {
					switch(CricketFunctions.checkBatAndBallImpactInOutPlayer(matchAllData.getEventFile().getEvents(), bc.getPlayerId())) {
					case "IN":
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrames$AllGraphics$Side" + WhichSide + "$Normal_BattingCard$"
								+ "FullBatRow" + rowId + containerName + "$select_Concuss*FUNCTION*Omo*vis_con SET 3\0", print_writers);
						break;
					case "OUT":
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrames$AllGraphics$Side" + WhichSide + "$Normal_BattingCard$"
								+ "FullBatRow" + rowId + containerName + "$select_Concuss*FUNCTION*Omo*vis_con SET 4\0", print_writers);
						break;
					}
				}else {
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrames$AllGraphics$Side" + WhichSide + "$Normal_BattingCard$"
							+ "FullBatRow" + rowId + containerName + "$select_Concuss*FUNCTION*Omo*vis_con SET 0\0", print_writers);
				}
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrames$AllGraphics$Side" + WhichSide + "$Normal_BattingCard$FullBatRow" + 
						rowId + containerName + "$BatScoreGrp$txt_Runs*GEOM*TEXT SET " + bc.getRuns() + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrames$AllGraphics$Side" + WhichSide + "$Normal_BattingCard$FullBatRow" + 
						rowId + containerName + "$BatScoreGrp$txt_Balls*GEOM*TEXT SET " + bc.getBalls() + "\0", print_writers);
				
				//how_out_txt = CricketFunctions.processHowOutText("FOUR-PART-HOW-OUT", bc);
				how_out_txt = null;
				if(bc.getHowOut() != null && !bc.getStatus().equalsIgnoreCase(CricketUtil.NOT_OUT)) {
					if(bc.getWasHowOutFielderSubstitute() != null) {
						if(bc.getWasHowOutFielderSubstitute().equalsIgnoreCase("NO")) {
							howOut = CricketFunctions.generateBattingCardForeignLanguage(bc.getHowOut(), bc.getHowOutPartOne(), bc.getHowOutPartTwo(), 
									"", multilanguagedata);
						}else {
							howOut = CricketFunctions.generateBattingCardForeignLanguage(bc.getHowOut(), bc.getHowOutPartOne(), bc.getHowOutPartTwo(), 
									bc.getHowOutText(), multilanguagedata);
						}
					}else {
						howOut = CricketFunctions.generateBattingCardForeignLanguage(bc.getHowOut(), bc.getHowOutPartOne(), bc.getHowOutPartTwo(), 
								"", multilanguagedata);
					}
					
					System.out.println("English = " + howOut.getEnglishText());
					foreignLanguageData.add(new ForeignLanguageData(howOut.getEnglishText(), howOut.getHindiText(), howOut.getTamilText(), howOut.getTeluguText()));
					how_out_txt = CricketFunctions.GetVariousLanguageTextToEachViz(config, Constants.BCCI, print_writers, foreignLanguageData);
				}
				
				System.out.println("how_out_txt = " + how_out_txt);
				
				if(how_out_txt != null) {
					String[] parts = how_out_txt.split("\\|");
					
					if(how_out_txt != null && parts.length >= 4) {
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrames$AllGraphics$Side" + WhichSide + "$Normal_BattingCard$FullBatRow" + 
								rowId + containerName + "$How_Out_1$txt_OutType*GEOM*TEXT SET " + how_out_txt.split("\\|")[0] + "\0", print_writers);
						
						CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*BACK_LAYER*TREE*$gfx_FullFrames$AllGraphics$Side" + WhichSide + "$Normal_BattingCard$FullBatRow" + 
								rowId + containerName + "$How_Out_1$select_Language*FUNCTION*Omo*vis_con SET ", config, Constants.BCCI, print_writers, foreignLanguageOmo);
						foreignLanguageData = CricketFunctions.AssembleMultiLanguageData("", "", multilanguagedata, how_out_txt.split("\\|")[1], "", null, 
								0, foreignLanguageDataList);
						CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*BACK_LAYER*TREE*$gfx_FullFrames$AllGraphics$Side" + WhichSide + "$Normal_BattingCard$FullBatRow" + 
								rowId + containerName + "$How_Out_1$English$txt_FielderName*GEOM*TEXT SET ", config, Constants.BCCI, print_writers, foreignLanguageData);
						
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrames$AllGraphics$Side" + WhichSide + "$Normal_BattingCard$FullBatRow" + 
								rowId + containerName + "$How_Out_2$BowlerName$txt_Bold*GEOM*TEXT SET " + how_out_txt.split("\\|")[2] + "\0", print_writers);
						
						CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*BACK_LAYER*TREE*$gfx_FullFrames$AllGraphics$Side" + WhichSide + "$Normal_BattingCard$FullBatRow" + 
								rowId + containerName + "$How_Out_2$BowlerName$BowlerName$select_Language*FUNCTION*Omo*vis_con SET ", config, Constants.BCCI, print_writers, foreignLanguageOmo);
						foreignLanguageData = CricketFunctions.AssembleMultiLanguageData("", "", multilanguagedata, how_out_txt.split("\\|")[3], "", null, 
								0, foreignLanguageDataList);
						CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*BACK_LAYER*TREE*$gfx_FullFrames$AllGraphics$Side" + WhichSide + "$Normal_BattingCard$FullBatRow" + 
								rowId + containerName + "$How_Out_2$BowlerName$BowlerName$English$txt_BowlerName*GEOM*TEXT SET ", config, Constants.BCCI, print_writers, foreignLanguageData);
						
					}else if(how_out_txt != null) {
						
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrames$AllGraphics$Side" + WhichSide + "$Normal_BattingCard$FullBatRow" + 
								rowId + containerName + "$How_Out_1$txt_OutType*GEOM*TEXT SET \0", print_writers);
						CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*BACK_LAYER*TREE*$gfx_FullFrames$AllGraphics$Side" + WhichSide + "$Normal_BattingCard$FullBatRow" + 
								rowId + containerName + "$How_Out_1$select_Language*FUNCTION*Omo*vis_con SET ", config, Constants.BCCI, print_writers, foreignLanguageOmo);
						foreignLanguageData = CricketFunctions.AssembleMultiLanguageData("", "", multilanguagedata, how_out_txt, "", null, 
								0, foreignLanguageDataList);
						CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*BACK_LAYER*TREE*$gfx_FullFrames$AllGraphics$Side" + WhichSide + "$Normal_BattingCard$FullBatRow" + 
								rowId + containerName + "$How_Out_1$English$txt_FielderName*GEOM*TEXT SET ", config, Constants.BCCI, print_writers, foreignLanguageData);
						
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrames$AllGraphics$Side" + WhichSide + "$Normal_BattingCard$FullBatRow" + 
								rowId + containerName + "$How_Out_2$txt_Bold*GEOM*TEXT SET \0", print_writers);
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrames$AllGraphics$Side" + WhichSide + "$Normal_BattingCard$FullBatRow" + 
								rowId + containerName + "$How_Out_2$English$txt_BowlerName*GEOM*TEXT SET \0", print_writers);
					}
				}else {
					CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*BACK_LAYER*TREE*$gfx_FullFrames$AllGraphics$Side" + WhichSide + "$Normal_BattingCard$FullBatRow" + 
							rowId + containerName + "$How_Out_1$select_Language*FUNCTION*Omo*vis_con SET ", config, Constants.BCCI, print_writers, foreignLanguageOmo);
					foreignLanguageData = CricketFunctions.AssembleMultiLanguageData(CricketUtil.DICTIONARY, "", multilanguagedata, "NOT OUT", "", null, 0, foreignLanguageDataList);
					CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*BACK_LAYER*TREE*$gfx_FullFrames$AllGraphics$Side" + WhichSide + "$Normal_BattingCard$FullBatRow" + 
							rowId + containerName + "$How_Out_1$English$txt_FielderName*GEOM*TEXT SET ", config, Constants.BCCI, print_writers, foreignLanguageData);
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrames$AllGraphics$Side" + WhichSide + "$Normal_BattingCard$FullBatRow" + 
							rowId + containerName + "$How_Out_1$txt_OutType*GEOM*TEXT SET \0", print_writers);
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrames$AllGraphics$Side" + WhichSide + "$Normal_BattingCard$FullBatRow" + 
							rowId + containerName + "$How_Out_2$txt_Bold*GEOM*TEXT SET \0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrames$AllGraphics$Side" + WhichSide + "$Normal_BattingCard$FullBatRow" + 
							rowId + containerName + "$How_Out_2$English$txt_BowlerName*GEOM*TEXT SET \0", print_writers);
				}
				
				break;
			}
		}
		return Constants.OK;
	}
	public String BatPerformerBody(List<PrintWriter> print_writers, int WhichSide, Configuration config, MatchAllData matchAllData,Inning inning, CricketService cricketService,
			MultiLanguageDatabase multilanguagedata, List<ForeignLanguageData> foreignLanguageDataList, List<Dictionary> dict) throws Exception
	{
		foreignLanguageData = new ArrayList<ForeignLanguageData>();
		foreignLanguageOmo = new ArrayList<ForeignLanguageData>();
		foreignLanguageData.add(new ForeignLanguageData("", "", "", ""));
		foreignLanguageOmo.add(new ForeignLanguageData("1", "2", "3", "4"));
		
		System.out.println("Wh" + WhichType);
		switch(WhichType.toLowerCase()) {
		case "performer":
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrames$ExtraData$Side" + WhichSide + "$select_GraphicsType"
					+ "*FUNCTION*Omo*vis_con SET 1\0", print_writers);
			
			BattingCard bc = inning.getBattingCard().stream().filter(bc1 -> bc1.getPlayerId() == FirstPlayerId).findAny().orElse(null);
			
			CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*BACK_LAYER*TREE*$gfx_FullFrames$ExtraData$Side" + WhichSide + "$PlayerStat$RightData$Data$" + 
					"TopStatHead$HeaderInfo$select_Language*FUNCTION*Omo*vis_con SET ", config, Constants.BCCI, print_writers, foreignLanguageOmo);
			foreignLanguageData = CricketFunctions.AssembleMultiLanguageData(CricketUtil.DICTIONARY, "", multilanguagedata, "STRIKE RATE", "", null, 0, foreignLanguageDataList);
			CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*BACK_LAYER*TREE*$gfx_FullFrames$ExtraData$Side" + WhichSide + "$PlayerStat$RightData$Data$" + 
					"TopStatHead$HeaderInfo$English$txt_StatHead*GEOM*TEXT SET ", config, Constants.BCCI, print_writers, foreignLanguageData);
			CricketFunctions.DoadWriteSameCommandToEachViz("RENDERER*BACK_LAYER*TREE*$gfx_FullFrames$ExtraData$Side" + WhichSide + "$PlayerStat$RightData$Data$TopStatHead$"
					+ "HeaderInfo$English$txt_StatHead*ACTIVE SET 1", print_writers, config);
			
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrames$ExtraData$Side" + WhichSide + "$PlayerStat$RightData$Data$HeadValue" + 
					"$txt_Statvalue*GEOM*TEXT SET " + CricketFunctions.generateStrikeRate(bc.getRuns(), bc.getBalls(), 0) + "\0", print_writers);
			
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrames$ExtraData$Side" + WhichSide + "$PlayerStat$RightData$Data$InfoGrps$InfoGrp1" + 
					"$InfoDataGrp$txt_StatValue*GEOM*TEXT SET " + bc.getFours() + "\0", print_writers);
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrames$ExtraData$Side" + WhichSide + "$PlayerStat$RightData$Data$InfoGrps$InfoGrp2" + 
					"$InfoDataGrp$txt_StatValue*GEOM*TEXT SET " + bc.getSixes() + "\0", print_writers);
			
			ArrayList<Tournament> tournament = new ArrayList<Tournament>();
			tournament.add(new Tournament());
			CricketFunctions.getBatsmanSRAgainstPaceAndSpin(FirstPlayerId, 0, cricketService, tournament, matchAllData);
			
			CricketFunctions.DoadWriteSameCommandToEachViz("RENDERER*BACK_LAYER*TREE*$gfx_FullFrames$ExtraData$Side" + WhichSide + "$PlayerStat$RightData$Bar$Legends$"
					+ "Text1$English$txt_Legends*GEOM*TEXT SET ", print_writers, config);
			
			CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*BACK_LAYER*TREE*$gfx_FullFrames$ExtraData$Side" + WhichSide + "$PlayerStat$RightData$Bar$Legends$" + 
					"StatHead1$select_Language*FUNCTION*Omo*vis_con SET ", config, Constants.BCCI, print_writers, foreignLanguageOmo);
			foreignLanguageData = CricketFunctions.AssembleMultiLanguageData(CricketUtil.DICTIONARY, "", multilanguagedata, "SEAM", "", null, 0, foreignLanguageDataList);
			CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*BACK_LAYER*TREE*$gfx_FullFrames$ExtraData$Side" + WhichSide + "$PlayerStat$RightData$Bar$Legends$" + 
					"StatHead1$English$txt_StatHead*GEOM*TEXT SET ", config, Constants.BCCI, print_writers, foreignLanguageData);
			
			CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*BACK_LAYER*TREE*$gfx_FullFrames$ExtraData$Side" + WhichSide + "$PlayerStat$RightData$Bar$Legends$" + 
					"StatHead2$select_Language*FUNCTION*Omo*vis_con SET ", config, Constants.BCCI, print_writers, foreignLanguageOmo);
			foreignLanguageData = CricketFunctions.AssembleMultiLanguageData(CricketUtil.DICTIONARY, "", multilanguagedata, "SPIN", "", null, 0, foreignLanguageDataList);
			CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*BACK_LAYER*TREE*$gfx_FullFrames$ExtraData$Side" + WhichSide + "$PlayerStat$RightData$Bar$Legends$" + 
					"StatHead2$English$txt_StatHead*GEOM*TEXT SET ", config, Constants.BCCI, print_writers, foreignLanguageData);
			
			CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*BACK_LAYER*TREE*$gfx_FullFrames$ExtraData$Side" + WhichSide + "$PlayerStat$RightData$Bar$" + 
					"StatHeadIn$select_Language*FUNCTION*Omo*vis_con SET ", config, Constants.BCCI, print_writers, foreignLanguageOmo);
			foreignLanguageData = CricketFunctions.AssembleMultiLanguageData(CricketUtil.DICTIONARY, "", multilanguagedata, "STRIKE RATE", "", null, 0, foreignLanguageDataList);
			CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*BACK_LAYER*TREE*$gfx_FullFrames$ExtraData$Side" + WhichSide + "$PlayerStat$RightData$Bar$" + 
					"StatHeadIn$English$txt_*GEOM*TEXT SET ", config, Constants.BCCI, print_writers, foreignLanguageData);
			break;
		case "partnership":
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrames$ExtraData$Side" + WhichSide + "$select_GraphicsType"
					+ "*FUNCTION*Omo*vis_con SET 2\0", print_writers);
			
			String value = CricketFunctions.ordinal(inning.getPartnerships().get(inning.getPartnerships().size()-1).getPartnershipNumber()) + " WICKET PARTNERSHIP";
			
			CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*BACK_LAYER*TREE*$gfx_FullFrames$ExtraData$Side" + WhichSide + "$Partnership$RightData$Data$" + 
					"HeaderIn$select_Language*FUNCTION*Omo*vis_con SET ", config, Constants.BCCI, print_writers, foreignLanguageOmo);
			foreignLanguageData = CricketFunctions.AssembleMultiLanguageData(CricketUtil.DICTIONARY, "", multilanguagedata, value, "", null, 0, foreignLanguageDataList);
			CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*BACK_LAYER*TREE*$gfx_FullFrames$ExtraData$Side" + WhichSide + "$Partnership$RightData$Data$" + 
					"HeaderIn$English$txt_PartnershipHead*GEOM*TEXT SET ", config, Constants.BCCI, print_writers, foreignLanguageData);
			
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrames$ExtraData$Side" + WhichSide + "$Partnership$AllStat$TotalScore" + 
					"$txt_TotalRuns*GEOM*TEXT SET " + inning.getPartnerships().get(inning.getPartnerships().size()-1).getTotalRuns() + "\0", print_writers);
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrames$ExtraData$Side" + WhichSide + "$Partnership$AllStat$TotalScore" + 
					"$txt_TotalBalls*GEOM*TEXT SET " + inning.getPartnerships().get(inning.getPartnerships().size()-1).getTotalBalls() + "\0", print_writers);
			
			CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*BACK_LAYER*TREE*$gfx_FullFrames$ExtraData$Side" + WhichSide + "$Partnership$AllStat$ContributionGrp1" 
					+ "$Name$select_Language*FUNCTION*Omo*vis_con SET ", config, Constants.BCCI, print_writers, foreignLanguageOmo);
			foreignLanguageData = CricketFunctions.AssembleMultiLanguageData(CricketUtil.PLAYER, CricketUtil.TICKERNAME, multilanguagedata, 
					inning.getPartnerships().get(inning.getPartnerships().size()-1).getFirstPlayer().getTicker_name(), "", null, 0, foreignLanguageDataList);
			CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*BACK_LAYER*TREE*$gfx_FullFrames$ExtraData$Side" + WhichSide + "$Partnership$AllStat$ContributionGrp1" 
					+ "$Name$English$txt_PlayerName1*GEOM*TEXT SET ", config, Constants.BCCI, print_writers, foreignLanguageData);
			
			CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*BACK_LAYER*TREE*$gfx_FullFrames$ExtraData$Side" + WhichSide + "$Partnership$AllStat$ContributionGrp2" 
					+ "$Name$select_Language*FUNCTION*Omo*vis_con SET ", config, Constants.BCCI, print_writers, foreignLanguageOmo);
			foreignLanguageData = CricketFunctions.AssembleMultiLanguageData(CricketUtil.PLAYER, CricketUtil.TICKERNAME, multilanguagedata, 
					inning.getPartnerships().get(inning.getPartnerships().size()-1).getSecondPlayer().getTicker_name(), "", null, 0, foreignLanguageDataList);
			CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*BACK_LAYER*TREE*$gfx_FullFrames$ExtraData$Side" + WhichSide + "$Partnership$AllStat$ContributionGrp2" 
					+ "$Name$English$txt_PlayerName1*GEOM*TEXT SET ", config, Constants.BCCI, print_writers, foreignLanguageData);
			
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrames$ExtraData$Side" + WhichSide + "$Partnership$AllStat$ContributionGrp1"
					+ "$StatGrp$txt_Runs*GEOM*TEXT SET " + inning.getPartnerships().get(inning.getPartnerships().size()-1).getFirstBatterRuns() + "\0", print_writers);
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrames$ExtraData$Side" + WhichSide + "$Partnership$AllStat$ContributionGrp1"
					+ "$StatGrp$txt_Balls*GEOM*TEXT SET " + inning.getPartnerships().get(inning.getPartnerships().size()-1).getFirstBatterBalls() + "\0", print_writers);
			
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrames$ExtraData$Side" + WhichSide + "$Partnership$AllStat$ContributionGrp2"
					+ "$StatGrp$txt_Runs*GEOM*TEXT SET " + inning.getPartnerships().get(inning.getPartnerships().size()-1).getSecondBatterRuns() + "\0", print_writers);
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrames$ExtraData$Side" + WhichSide + "$Partnership$AllStat$ContributionGrp2"
					+ "$StatGrp$txt_Balls*GEOM*TEXT SET " + inning.getPartnerships().get(inning.getPartnerships().size()-1).getSecondBatterBalls() + "\0", print_writers);
			
			break;
		}
		return Constants.OK;
	}
	
	public String BowlingCardBody(List<PrintWriter> print_writers, int WhichSide, Configuration config, MatchAllData matchAllData,Inning inning,
			MultiLanguageDatabase multilanguagedata, List<ForeignLanguageData> foreignLanguageDataList, List<Dictionary> dict) {
		
		foreignLanguageData = new ArrayList<ForeignLanguageData>();
		foreignLanguageOmo = new ArrayList<ForeignLanguageData>();
		foreignLanguageData.add(new ForeignLanguageData("", "", "", ""));
		foreignLanguageOmo.add(new ForeignLanguageData("1", "2", "3", "4"));
		
		Collections.sort(inning.getBowlingCard());
		rowId = 0;
		
		CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrames$AllGraphics$Side" + WhichSide + "$Select_GfxType"
				+ "*FUNCTION*Omo*vis_con SET 3\0", print_writers);
		for(int j=0;j<=11;j++) {
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrames$AllGraphics$Side" + WhichSide + "$BowlingCard$FullBallRow" + j + 
					"$Select_Row_Type*FUNCTION*Omo*vis_con SET 0\0", print_writers);
		}
		CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrames$AllGraphics$Side" + WhichSide + "$BowlingCard$FullBallRow0"
				+ "$Select_Row_Type*FUNCTION*Omo*vis_con SET 1\0", print_writers);
		
		CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*BACK_LAYER*TREE*$gfx_FullFrames$AllGraphics$Side" + WhichSide + "$BowlingCard$FullBallRow0"
				+ "$Tittle$OversHead$select_Language*FUNCTION*Omo*vis_con SET ", config, Constants.BCCI, print_writers, foreignLanguageOmo);
		foreignLanguageData = CricketFunctions.AssembleMultiLanguageData(CricketUtil.DICTIONARY, "", multilanguagedata, "OVERS", "", null, 0, foreignLanguageDataList);
		CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*BACK_LAYER*TREE*$gfx_FullFrames$AllGraphics$Side" + WhichSide + "$BowlingCard$FullBallRow0"
				+ "$Tittle$OversHead$English$txt_Overs*GEOM*TEXT SET ", config, Constants.BCCI, print_writers, foreignLanguageData);
		
		CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*BACK_LAYER*TREE*$gfx_FullFrames$AllGraphics$Side" + WhichSide + "$BowlingCard$FullBallRow0"
				+ "$Tittle$MaidensHead$select_Language*FUNCTION*Omo*vis_con SET ", config, Constants.BCCI, print_writers, foreignLanguageOmo);
		foreignLanguageData = CricketFunctions.AssembleMultiLanguageData(CricketUtil.DICTIONARY, "", multilanguagedata, "MAIDENS", "", null, 0, foreignLanguageDataList);
		CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*BACK_LAYER*TREE*$gfx_FullFrames$AllGraphics$Side" + WhichSide + "$BowlingCard$FullBallRow0"
				+ "$Tittle$MaidensHead$English$txt_Maidens*GEOM*TEXT SET ", config, Constants.BCCI, print_writers, foreignLanguageData);
		
		CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*BACK_LAYER*TREE*$gfx_FullFrames$AllGraphics$Side" + WhichSide + "$BowlingCard$FullBallRow0"
				+ "$Tittle$RunsHead$select_Language*FUNCTION*Omo*vis_con SET ", config, Constants.BCCI, print_writers, foreignLanguageOmo);
		foreignLanguageData = CricketFunctions.AssembleMultiLanguageData(CricketUtil.DICTIONARY, "", multilanguagedata, "RUNS", "", null, 0, foreignLanguageDataList);
		CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*BACK_LAYER*TREE*$gfx_FullFrames$AllGraphics$Side" + WhichSide + "$BowlingCard$FullBallRow0"
				+ "$Tittle$RunsHead$English$txt_Runs*GEOM*TEXT SET ", config, Constants.BCCI, print_writers, foreignLanguageData);
		
		CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*BACK_LAYER*TREE*$gfx_FullFrames$AllGraphics$Side" + WhichSide + "$BowlingCard$FullBallRow0"
				+ "$Tittle$WicketsHead$select_Language*FUNCTION*Omo*vis_con SET ", config, Constants.BCCI, print_writers, foreignLanguageOmo);
		foreignLanguageData = CricketFunctions.AssembleMultiLanguageData(CricketUtil.DICTIONARY, "", multilanguagedata, "WICKETS", "", null, 0, foreignLanguageDataList);
		CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*BACK_LAYER*TREE*$gfx_FullFrames$AllGraphics$Side" + WhichSide + "$BowlingCard$FullBallRow0"
				+ "$Tittle$WicketsHead$English$txt_Wickets*GEOM*TEXT SET ", config, Constants.BCCI, print_writers, foreignLanguageData);
		
		CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*BACK_LAYER*TREE*$gfx_FullFrames$AllGraphics$Side" + WhichSide + "$BowlingCard$FullBallRow0"
				+ "$Tittle$EconomyHead$select_Language*FUNCTION*Omo*vis_con SET ", config, Constants.BCCI, print_writers, foreignLanguageOmo);
		foreignLanguageData = CricketFunctions.AssembleMultiLanguageData(CricketUtil.DICTIONARY, "", multilanguagedata, "ECONOMY", "", null, 0, foreignLanguageDataList);
		CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*BACK_LAYER*TREE*$gfx_FullFrames$AllGraphics$Side" + WhichSide + "$BowlingCard$FullBallRow0"
				+ "$Tittle$EconomyHead$English$txt_Economy*GEOM*TEXT SET ", config, Constants.BCCI, print_writers, foreignLanguageData);
		
		if(inning.getBowlingCard() != null && inning.getBowlingCard().size() > 0) {
			for(int iRow = 1; iRow <= inning.getBowlingCard().size(); iRow++) {
				if(inning.getBowlingCard().get(iRow-1).getRuns() > 0 || ((inning.getBowlingCard().get(iRow-1).getOvers()*6)
						+inning.getBowlingCard().get(iRow-1).getBalls()) > 0) {
					if(CricketFunctions.GetTargetData(matchAllData).getRemaningRuns() == 0 || matchAllData.getMatch().getInning().get(1).getTotalWickets() >= 10 || 
							CricketFunctions.GetTargetData(matchAllData).getRemaningBall() == 0) {
						omo = 2;
						containerName = "$PlayersDehighlight";
					}else {
						if(inning.getInningStatus().equalsIgnoreCase(CricketUtil.PAUSE)) {
							omo = 2;
							containerName = "$PlayersDehighlight";
						}else {
							switch (inning.getBowlingCard().get(iRow-1).getStatus().toUpperCase()) {
							case (CricketUtil.OTHER + CricketUtil.BOWLER): case (CricketUtil.LAST + CricketUtil.BOWLER):
								omo = 2;
								containerName = "$PlayersDehighlight";
								break;
							case (CricketUtil.CURRENT + CricketUtil.BOWLER):
								omo = 3;
								containerName = "$PlayersHighlight";
								break;
							}
						}
					}
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrames$AllGraphics$Side" + WhichSide + "$BowlingCard$FullBallRow" + 
							iRow + "$Select_Row_Type*FUNCTION*Omo*vis_con SET " + omo + "\0", print_writers);
					
					CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*BACK_LAYER*TREE*$gfx_FullFrames$AllGraphics$Side" + WhichSide + "$BowlingCard$FullBallRow" 
							+ iRow + containerName + "$TextAll$PlayerName$select_Language*FUNCTION*Omo*vis_con SET ", config, Constants.BCCI, print_writers, foreignLanguageOmo);
					foreignLanguageData = CricketFunctions.AssembleMultiLanguageData(CricketUtil.PLAYER, CricketUtil.TICKERNAME, multilanguagedata, 
							inning.getBowlingCard().get(iRow-1).getPlayer().getTicker_name(), "", null, 0, foreignLanguageDataList);
					CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*BACK_LAYER*TREE*$gfx_FullFrames$AllGraphics$Side" + WhichSide + "$BowlingCard$FullBallRow" 
							+ iRow + containerName + "$TextAll$PlayerName$English$txt_BowlerName*GEOM*TEXT SET ", config, Constants.BCCI, print_writers, foreignLanguageData);
					
					if(!CricketFunctions.checkBatAndBallImpactInOutPlayer(matchAllData.getEventFile().getEvents(), inning.getBowlingCard().get(iRow-1).getPlayerId()).isEmpty()) {
						switch(CricketFunctions.checkBatAndBallImpactInOutPlayer(matchAllData.getEventFile().getEvents(), inning.getBowlingCard().get(iRow-1).getPlayerId())) {
						case "IN":
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrames$AllGraphics$Side" + WhichSide + "$BowlingCard$FullBallRow" 
									+ iRow + containerName + "$TextAll$select_Concuss*FUNCTION*Omo*vis_con SET 3\0", print_writers);
							break;
						case "OUT":
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrames$AllGraphics$Side" + WhichSide + "$BowlingCard$FullBallRow" 
									+ iRow + containerName + "$TextAll$select_Concuss*FUNCTION*Omo*vis_con SET 4\0", print_writers);
							break;
						}
					}else {
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrames$AllGraphics$Side" + WhichSide + "$BowlingCard$FullBallRow" + 
								iRow + containerName + "$TextAll$select_Concuss*FUNCTION*Omo*vis_con SET 0\0", print_writers);
					}
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrames$AllGraphics$Side" + WhichSide + "$BowlingCard$FullBallRow" + 
							iRow + containerName + "$TextAll$PlayerFigures$fig_Overs*GEOM*TEXT SET " + CricketFunctions.OverBalls(inning.getBowlingCard().
									get(iRow-1).getOvers(), inning.getBowlingCard().get(iRow-1).getBalls()) + "\0", print_writers);
					
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrames$AllGraphics$Side" + WhichSide + "$BowlingCard$FullBallRow" + 
							iRow + containerName + "$TextAll$PlayerFigures$fig_Maidens*GEOM*TEXT SET " + inning.getBowlingCard().get(iRow-1).getMaidens() + "\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrames$AllGraphics$Side" + WhichSide + "$BowlingCard$FullBallRow" + 
							iRow + containerName + "$TextAll$PlayerFigures$fig_Runs*GEOM*TEXT SET " + inning.getBowlingCard().get(iRow-1).getRuns() + "\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrames$AllGraphics$Side" + WhichSide + "$BowlingCard$FullBallRow" + 
							iRow + containerName + "$TextAll$PlayerFigures$fig_Wickets*GEOM*TEXT SET " + inning.getBowlingCard().get(iRow-1).getWickets() + "\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrames$AllGraphics$Side" + WhichSide + "$BowlingCard$FullBallRow" + 
							iRow + containerName + "$TextAll$PlayerFigures$fig_Economy*GEOM*TEXT SET " + (inning.getBowlingCard().get(iRow-1).getEconomyRate() != null ?
									inning.getBowlingCard().get(iRow-1).getEconomyRate():"-") + "\0", print_writers);
					
				}
			}
		}
		if(inning.getFallsOfWickets() != null) {
			if(inning.getBowlingCard().size() <= 8) {
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrames$AllGraphics$Side" + WhichSide + "$BowlingCard$FullBallRow9$"
						+ "Select_Row_Type*FUNCTION*Omo*vis_con SET 4\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrames$AllGraphics$Side" + WhichSide + "$BowlingCard$FullBallRow10$"
						+ "Select_Row_Type*FUNCTION*Omo*vis_con SET 5\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrames$AllGraphics$Side" + WhichSide + "$BowlingCard$FullBallRow11$"
						+ "Select_Row_Type*FUNCTION*Omo*vis_con SET 6\0", print_writers);
				
				CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*BACK_LAYER*TREE*$gfx_FullFrames$AllGraphics$Side" + WhichSide + "$BowlingCard$FullBallRow9"
						+ "$FOW$FowText$select_Language*FUNCTION*Omo*vis_con SET ", config, Constants.BCCI, print_writers, foreignLanguageOmo);
				foreignLanguageData = CricketFunctions.AssembleMultiLanguageData(CricketUtil.DICTIONARY, "", multilanguagedata, "FALL OF WICKETS", "", null, 0, 
						foreignLanguageDataList);
				CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*BACK_LAYER*TREE*$gfx_FullFrames$AllGraphics$Side" + WhichSide + "$BowlingCard$FullBallRow9"
						+ "$FOW$FowText$English$txt_Tittle*GEOM*TEXT SET ", config, Constants.BCCI, print_writers, foreignLanguageData);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrames$AllGraphics$Side" + WhichSide+"$BowlingCard$FullBallRow11"
						+ "$FOW_Score$WicketValues*FUNCTION*Grid*num_col SET " + inning.getFallsOfWickets().size() + "\0", print_writers);
				
				for(FallOfWicket fow : inning.getFallsOfWickets()) {
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrames$AllGraphics$Side" + WhichSide + "$BowlingCard$FullBallRow11"
						+ "$FOW_Score$Fig_" + fow.getFowNumber() + "*GEOM*TEXT SET " + fow.getFowRuns() + "\0", print_writers);
				}
			}
		}
		return Constants.OK;
	}
	public String PartnershipListBody(List<PrintWriter> print_writers, int WhichSide, Configuration config, MatchAllData matchAllData,Inning inning, 
			MultiLanguageDatabase multilanguagedata, List<ForeignLanguageData> foreignLanguageDataList, List<Dictionary> dict) {
		
		rowId = 0;
		omo = 0;
		Mult = 48;
		
		foreignLanguageData = new ArrayList<ForeignLanguageData>();
		foreignLanguageOmo = new ArrayList<ForeignLanguageData>();
		foreignLanguageData.add(new ForeignLanguageData("", "", "", ""));
		foreignLanguageOmo.add(new ForeignLanguageData("1", "2", "3", "4"));
		
		CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrames$AllGraphics$Side" + WhichSide + "$Select_GfxType"
				+ "*FUNCTION*Omo*vis_con SET 5\0", print_writers);
		
		if(inning.getPartnerships().size()>=10) {
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrames$AllGraphics$Side" + WhichSide + "$PartnershipList$PartDataGrid"
					+ "*FUNCTION*Grid*num_row SET " + inning.getPartnerships().size() + " \0", print_writers);
		}else {
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrames$AllGraphics$Side" + WhichSide + "$PartnershipList$PartDataGrid"
					+ "*FUNCTION*Grid*num_row SET " + inning.getBattingCard().size() + "\0", print_writers);
		}
		
		for(int a = 1; a <= inning.getPartnerships().size(); a++){
			if(inning.getPartnerships().get(a-1).getFirstBatterRuns() > Top_Score) {
				Top_Score = inning.getPartnerships().get(a-1).getFirstBatterRuns();
			}
			if(inning.getPartnerships().get(a-1).getSecondBatterRuns() > Top_Score) {
				Top_Score = inning.getPartnerships().get(a-1).getSecondBatterRuns();
			}
		}
		
		for (Partnership ps : inning.getPartnerships()) {
			rowId = rowId + 1;
			Left_Batsman ="" ; Right_Batsman="";
			
			Left_Batsman = ps.getFirstPlayer().getTicker_name();
			Right_Batsman = ps.getSecondPlayer().getTicker_name();
			
			if(inning.getPartnerships().size() >= 10 && inning.getTotalWickets()>=10) {
				if(ps.getPartnershipNumber()<=inning.getPartnerships().size()) {
					omo = 2;
					containerName = "$Out";
				}
			}
			else {
				if(ps.getPartnershipNumber() < inning.getPartnerships().size()) {
					omo = 2;
					containerName = "$Out";
				}
				else if(ps.getPartnershipNumber() >= inning.getPartnerships().size()) {
					omo = 3;
					containerName = "$Not_Out";
				}
			}
			
			ScaleFac1 = ((ps.getFirstBatterRuns())*(Mult/Top_Score)) ;
			ScaleFac2 = ((ps.getSecondBatterRuns())*(Mult/Top_Score)) ;
			
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrames$AllGraphics$Side" + WhichSide + "$PartnershipList$FullPartRow" 
				+ rowId +  "$Select_Row_Type*FUNCTION*Omo*vis_con SET " + omo + "\0", print_writers);
			
			CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*BACK_LAYER*TREE*$gfx_FullFrames$AllGraphics$Side" + WhichSide + "$PartnershipList$FullPartRow" 
					+ rowId + containerName + "$PlayerLeft$select_Language*FUNCTION*Omo*vis_con SET ", config, Constants.BCCI, print_writers, foreignLanguageOmo);
			foreignLanguageData = CricketFunctions.AssembleMultiLanguageData(CricketUtil.PLAYER, CricketUtil.TICKERNAME, multilanguagedata, Left_Batsman, "", null, 0, foreignLanguageDataList);
			CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*BACK_LAYER*TREE*$gfx_FullFrames$AllGraphics$Side" + WhichSide + "$PartnershipList$FullPartRow"
					+ rowId + containerName + "$PlayerLeft$English$txt_Name_1*GEOM*TEXT SET ", config, Constants.BCCI, print_writers, foreignLanguageData);
			
			CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*BACK_LAYER*TREE*$gfx_FullFrames$AllGraphics$Side" + WhichSide + "$PartnershipList$FullPartRow" 
					+ rowId + containerName + "$PlayerRight$select_Language*FUNCTION*Omo*vis_con SET ", config, Constants.BCCI, print_writers, foreignLanguageOmo);
			foreignLanguageData = CricketFunctions.AssembleMultiLanguageData(CricketUtil.PLAYER, CricketUtil.TICKERNAME, multilanguagedata, Right_Batsman, "", null, 0, foreignLanguageDataList);
			CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*BACK_LAYER*TREE*$gfx_FullFrames$AllGraphics$Side" + WhichSide + "$PartnershipList$FullPartRow"
					+ rowId + containerName + "$PlayerRight$English$txt_*GEOM*TEXT SET ", config, Constants.BCCI, print_writers, foreignLanguageData);
			
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrames$AllGraphics$Side" + WhichSide + "$PartnershipList$FullPartRow"
					+ rowId + containerName + "$PartScoreGrp$Runs*GEOM*TEXT SET " + ps.getTotalRuns() + "\0", print_writers);
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrames$AllGraphics$Side" + WhichSide + "$PartnershipList$FullPartRow"
					+ rowId + containerName + "$PartScoreGrp$Balls*GEOM*TEXT SET " + ps.getTotalBalls() + "\0", print_writers);
			
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrames$AllGraphics$Side" + WhichSide + "$PartnershipList$FullPartRow"
					+ rowId + containerName + "$Geom_Bars$Geom_Bar_1*GEOM*width SET " + ScaleFac1 + "\0", print_writers);
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrames$AllGraphics$Side" + WhichSide + "$PartnershipList$FullPartRow"
					+ rowId + containerName + "$Geom_Bars$Geom_Bar_2*GEOM*width SET " + ScaleFac2 + "\0", print_writers);
		}
		if(inning.getPartnerships().size() >= 10) {
			rowId = rowId + 1;
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrames$AllGraphics$Side" + WhichSide + "$PartnershipList$FullPartRow" 
					+ rowId +  "$Select_Row_Type*FUNCTION*Omo*vis_con SET 0\0", print_writers);
		}else {
			for (BattingCard bc : inning.getBattingCard()) {
				if(rowId < inning.getBattingCard().size()) {
					if(rowId == inning.getPartnerships().size()) {
						rowId = rowId + 1;
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrames$AllGraphics$Side" + WhichSide + "$PartnershipList$FullPartRow" 
								+ rowId +  "$Select_Row_Type*FUNCTION*Omo*vis_con SET 0\0", print_writers);
						
						CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*BACK_LAYER*TREE*$gfx_FullFrames$AllGraphics$Side" + WhichSide + "$PartnershipList$"
								+ "FullPartRow" + rowId + "$Title$PlayerLeft$select_Language*FUNCTION*Omo*vis_con SET ", config, Constants.BCCI, print_writers, foreignLanguageOmo);
						
						if(inning.getTotalOvers() == matchAllData.getSetup().getMaxOvers() || inning.getTotalWickets() >= 10 ) {
							foreignLanguageData = CricketFunctions.AssembleMultiLanguageData(CricketUtil.DICTIONARY, "", multilanguagedata, "DID NOT BAT", "", null, 0, foreignLanguageDataList);
							CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*BACK_LAYER*TREE*$gfx_FullFrames$AllGraphics$Side" + WhichSide + "$PartnershipList$"
									+ "FullPartRow" + rowId + "$Title$PlayerLeft$English$txt_Title*GEOM*TEXT SET ", config, Constants.BCCI, print_writers, foreignLanguageData);
						}
						else if(matchAllData.getSetup().getTargetOvers() != null && !matchAllData.getSetup().getTargetOvers().trim().isEmpty()) {
							
							if(inning.getInningStatus().equalsIgnoreCase(CricketUtil.PAUSE)) {
								foreignLanguageData = CricketFunctions.AssembleMultiLanguageData(CricketUtil.DICTIONARY, "", multilanguagedata, "DID NOT BAT", "", null, 0, foreignLanguageDataList);
								CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*BACK_LAYER*TREE*$gfx_FullFrames$AllGraphics$Side" + WhichSide + "$PartnershipList$"
										+ "FullPartRow" + rowId + "$Title$PlayerLeft$English$txt_Title*GEOM*TEXT SET ", config, Constants.BCCI, print_writers, foreignLanguageData);
							}else {
								if(String.valueOf(inning.getTotalOvers()) + "." + String.valueOf(inning.getTotalBalls()) == matchAllData.getSetup().getTargetOvers() 
										|| inning.getTotalWickets() >= 10) {
									foreignLanguageData = CricketFunctions.AssembleMultiLanguageData(CricketUtil.DICTIONARY, "", multilanguagedata, "DID NOT BAT", "", null, 0, foreignLanguageDataList);
									CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*BACK_LAYER*TREE*$gfx_FullFrames$AllGraphics$Side" + WhichSide + "$PartnershipList$"
											+ "FullPartRow" + rowId + "$Title$PlayerLeft$English$txt_Title*GEOM*TEXT SET ", config, Constants.BCCI, print_writers, foreignLanguageData);
								}else if(CricketFunctions.GetTargetData(matchAllData).getRemaningRuns() <= 0 || CricketFunctions.GetTargetData(matchAllData).getRemaningBall() <= 0 
										|| CricketFunctions.getWicketsLeft(matchAllData, inning.getInningNumber()) <= 0) {
									foreignLanguageData = CricketFunctions.AssembleMultiLanguageData(CricketUtil.DICTIONARY, "", multilanguagedata, "DID NOT BAT", "", null, 0, foreignLanguageDataList);
									CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*BACK_LAYER*TREE*$gfx_FullFrames$AllGraphics$Side" + WhichSide + "$PartnershipList$"
											+ "FullPartRow" + rowId + "$Title$PlayerLeft$English$txt_Title*GEOM*TEXT SET ", config, Constants.BCCI, print_writers, foreignLanguageData);
								}else {
									foreignLanguageData = CricketFunctions.AssembleMultiLanguageData(CricketUtil.DICTIONARY, "", multilanguagedata, "STILL NOT BAT", "", null, 0, foreignLanguageDataList);
									CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*BACK_LAYER*TREE*$gfx_FullFrames$AllGraphics$Side" + WhichSide + "$PartnershipList$"
											+ "FullPartRow" + rowId + "$Title$PlayerLeft$English$txt_Title*GEOM*TEXT SET ", config, Constants.BCCI, print_writers, foreignLanguageData);
								}
							}
						}
						else if(CricketFunctions.GetTargetData(matchAllData).getRemaningRuns() <= 0 || CricketFunctions.GetTargetData(matchAllData).getRemaningBall()	 <= 0 ||
								CricketFunctions.getWicketsLeft(matchAllData, inning.getInningNumber()) <= 0) {
							foreignLanguageData = CricketFunctions.AssembleMultiLanguageData(CricketUtil.DICTIONARY, "", multilanguagedata, "DID NOT BAT", "", null, 0, foreignLanguageDataList);
							CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*BACK_LAYER*TREE*$gfx_FullFrames$AllGraphics$Side" + WhichSide + "$PartnershipList$"
									+ "FullPartRow" + rowId + "$Title$PlayerLeft$English$txt_Title*GEOM*TEXT SET ", config, Constants.BCCI, print_writers, foreignLanguageData);
						}
						else if(inning.getInningStatus().equalsIgnoreCase(CricketUtil.PAUSE)) {
							foreignLanguageData = CricketFunctions.AssembleMultiLanguageData(CricketUtil.DICTIONARY, "", multilanguagedata, "DID NOT BAT", "", null, 0, foreignLanguageDataList);
							CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*BACK_LAYER*TREE*$gfx_FullFrames$AllGraphics$Side" + WhichSide + "$PartnershipList$"
									+ "FullPartRow" + rowId + "$Title$PlayerLeft$English$txt_Title*GEOM*TEXT SET ", config, Constants.BCCI, print_writers, foreignLanguageData);
						}
						else {
							foreignLanguageData = CricketFunctions.AssembleMultiLanguageData(CricketUtil.DICTIONARY, "", multilanguagedata, "STILL NOT BAT", "", null, 0, foreignLanguageDataList);
							CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*BACK_LAYER*TREE*$gfx_FullFrames$AllGraphics$Side" + WhichSide + "$PartnershipList$"
									+ "FullPartRow" + rowId + "$Title$PlayerLeft$English$txt_Title*GEOM*TEXT SET ", config, Constants.BCCI, print_writers, foreignLanguageData);
						}
					}
					else if(bc.getStatus().toUpperCase().equalsIgnoreCase(CricketUtil.STILL_TO_BAT)) {
						rowId = rowId + 1;
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrames$AllGraphics$Side" + WhichSide + "$PartnershipList$FullPartRow" 
								+ rowId +  "$Select_Row_Type*FUNCTION*Omo*vis_con SET 1\0", print_writers);
						
						CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*BACK_LAYER*TREE*$gfx_FullFrames$AllGraphics$Side" + WhichSide + "$PartnershipList$FullPartRow" 
								+ rowId + "$Still_To_Bat$PlayerLeft$select_Language*FUNCTION*Omo*vis_con SET ", config, Constants.BCCI, print_writers, foreignLanguageOmo);
						foreignLanguageData = CricketFunctions.AssembleMultiLanguageData(CricketUtil.PLAYER, CricketUtil.TICKERNAME, multilanguagedata, 
								bc.getPlayer().getTicker_name(), "", null, 0, foreignLanguageDataList);
						CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*BACK_LAYER*TREE*$gfx_FullFrames$AllGraphics$Side" + WhichSide + "$PartnershipList$FullPartRow" 
								+ rowId + "$Still_To_Bat$PlayerLeft$English$txt_Title*GEOM*TEXT SET ", config, Constants.BCCI, print_writers, foreignLanguageData);
					}	
				}
				else {
					break;
				}
			}
		}
		return Constants.OK;
	}
	public String SummaryBody(List<PrintWriter> print_writers, int WhichSide, Configuration config, MatchAllData matchAllData,Inning inning, 
			MultiLanguageDatabase multilanguagedata, List<ForeignLanguageData> foreignLanguageDataList, List<Dictionary> dict) {
		
		int row_id = 0, inn_no = 0, max_Strap = 0;
		String teamname = "";
		
		foreignLanguageData = new ArrayList<ForeignLanguageData>();
		foreignLanguageOmo = new ArrayList<ForeignLanguageData>();
		foreignLanguageData.add(new ForeignLanguageData("", "", "", ""));
		foreignLanguageOmo.add(new ForeignLanguageData("1", "2", "3", "4"));
		
		CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrames$AllGraphics$Side" + WhichSide + "$Select_GfxType"
				+ "*FUNCTION*Omo*vis_con SET 4\0", print_writers);
		
		whichInning = inning.getInningNumber();

		CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrames$AllGraphics$Side" + WhichSide + "$MatchSummary$Select_SummaryType"
				+ "*FUNCTION*Omo*vis_con SET " + whichInning + "\0", print_writers);
		
		for(int i = 1; i <= whichInning ; i++) {
			if(whichInning == 2) {
				if(i == 1) {
					inn_no = 1;
				}else {
					inn_no = 2;
				}
				row_id = 0;
				max_Strap = 4;
				
			}else if(whichInning == 3) {
				if(i == 1) {
					inn_no = 1;
				} else if(i==2) {
					inn_no = 2;
				}else if(i==3) {
					inn_no = 3;
				}
				row_id = 0;
				max_Strap = 3;
				
			}else if (whichInning == 4) {
				if(i == 1) {
					inn_no = 1;
				} else if(i==2) {
					inn_no = 2;
				}else if(i==3) {
					inn_no = 3;
				}else if(i==4) {
					inn_no = 4;
				}
				row_id = 0;
				max_Strap = 2;
			}
			
			if(matchAllData.getMatch().getInning().get(i-1).getBattingTeamId() == matchAllData.getSetup().getHomeTeamId()) {
				teamname = matchAllData.getSetup().getHomeTeam().getTeamName1().toUpperCase();	
			} else {
				teamname = matchAllData.getSetup().getAwayTeam().getTeamName1().toUpperCase();
			}
			
			CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*BACK_LAYER*TREE*$gfx_FullFrames$AllGraphics$Side" + WhichSide + "$MatchSummary$SummaryGrp" + 
					whichInning + "$Inn" + inn_no + "$Title$Team$select_Language*FUNCTION*Omo*vis_con SET ", config, Constants.BCCI, print_writers, foreignLanguageOmo);
			foreignLanguageData = CricketFunctions.AssembleMultiLanguageData(CricketUtil.TEAM, "", multilanguagedata, teamname, "", null, 0, foreignLanguageDataList);
			CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*BACK_LAYER*TREE*$gfx_FullFrames$AllGraphics$Side" + WhichSide + "$MatchSummary$SummaryGrp" + 
					whichInning + "$Inn" + inn_no + "$Title$Team$English$txt_Team*GEOM*TEXT SET ", config, Constants.BCCI, print_writers, foreignLanguageData);
			
			CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*BACK_LAYER*TREE*$gfx_FullFrames$AllGraphics$Side" + WhichSide + "$MatchSummary$SummaryGrp" + 
					whichInning + "$Inn" + inn_no + "$Title$Innings$select_Language*FUNCTION*Omo*vis_con SET ", config, Constants.BCCI, print_writers, foreignLanguageOmo);
			foreignLanguageData = CricketFunctions.AssembleMultiLanguageData(CricketUtil.DICTIONARY, "", multilanguagedata, "OVERS", "", null, 0, foreignLanguageDataList);
			CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*BACK_LAYER*TREE*$gfx_FullFrames$AllGraphics$Side" + WhichSide + "$MatchSummary$SummaryGrp" + 
					whichInning + "$Inn" + inn_no + "$Title$Innings$English$txt_Innings*GEOM*TEXT SET ", config, Constants.BCCI, print_writers, foreignLanguageData);
			CricketFunctions.DoadWriteSameCommandToEachViz("RENDERER*BACK_LAYER*TREE*$gfx_FullFrames$AllGraphics$Side" + WhichSide + "$MatchSummary$SummaryGrp" + whichInning 
					+ "$Inn" + inn_no + "$Title$Innings$English$txt_Innings*ACTIVE SET 1", print_writers, config);
			
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrames$AllGraphics$Side" + WhichSide + "$MatchSummary$SummaryGrp" + whichInning 
					+ "$Inn" + inn_no + "$Title$txt_Score*GEOM*TEXT SET " + CricketFunctions.getTeamScore(matchAllData.getMatch().getInning().get(i-1), "-", false) 
					+ "\0", print_writers);
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrames$AllGraphics$Side" + WhichSide + "$MatchSummary$SummaryGrp" + whichInning 
					+ "$Inn" + inn_no + "$Title$txt_Overs_Value*GEOM*TEXT SET " + CricketFunctions.OverBalls(matchAllData.getMatch().getInning().get(i-1).getTotalOvers(), 
							matchAllData.getMatch().getInning().get(i-1).getTotalBalls()) + "\0", print_writers);
			
			if(matchAllData.getMatch().getInning().get(i-1).getBattingCard() != null) {
				Collections.sort(matchAllData.getMatch().getInning().get(i-1).getBattingCard(),new CricketFunctions.BatsmenScoreComparator());
				for(BattingCard bc : matchAllData.getMatch().getInning().get(i-1).getBattingCard()) {
					if(!bc.getStatus().toUpperCase().equalsIgnoreCase(CricketUtil.STILL_TO_BAT)) {
						row_id = row_id + 1;
						
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrames$AllGraphics$Side" + WhichSide + "$MatchSummary$SummaryGrp" + 
								whichInning + "$Inn" + inn_no + "$SummaryInfoGrps$Row_" + row_id + "$BatOut*ACTIVE SET 1\0", print_writers);
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrames$AllGraphics$Side" + WhichSide + "$MatchSummary$SummaryGrp" + 
								whichInning + "$Inn" + inn_no + "$SummaryInfoGrps$Row_" + row_id + "$BatOut$InfoGrp1$SummaryHighlightOmo*FUNCTION*Omo*vis_con SET "
										+ "0\0", print_writers);
						
						CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*BACK_LAYER*TREE*$gfx_FullFrames$AllGraphics$Side" + WhichSide + "$MatchSummary$"
								+ "SummaryGrp" + whichInning + "$Inn" + inn_no + "$SummaryInfoGrps$Row_" + row_id + "$BatOut$InfoGrp1$TextAll$PlayerName$select_Language"
								+ "*FUNCTION*Omo*vis_con SET ", config, Constants.BCCI, print_writers, foreignLanguageOmo);
						foreignLanguageData = CricketFunctions.AssembleMultiLanguageData(CricketUtil.PLAYER, CricketUtil.TICKERNAME, multilanguagedata, 
								bc.getPlayer().getTicker_name(), "", null, 0, foreignLanguageDataList);
						CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*BACK_LAYER*TREE*$gfx_FullFrames$AllGraphics$Side" + WhichSide + "$MatchSummary$"
								+ "SummaryGrp" + whichInning + "$Inn" + inn_no + "$SummaryInfoGrps$Row_" + row_id + "$BatOut$InfoGrp1$TextAll$PlayerName$English$txt_Name"
										+ "*GEOM*TEXT SET ", config, Constants.BCCI, print_writers, foreignLanguageData);
						
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrames$AllGraphics$Side" + WhichSide + "$MatchSummary$SummaryGrp" + 
								whichInning + "$Inn" + inn_no + "$SummaryInfoGrps$Row_" + row_id + "$BatOut$InfoGrp1$TextAll$ScoreGrp$txt_Runs*GEOM*TEXT SET " + 
								bc.getRuns() + "\0", print_writers);
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrames$AllGraphics$Side" + WhichSide + "$MatchSummary$SummaryGrp" + 
								whichInning + "$Inn" + inn_no + "$SummaryInfoGrps$Row_" + row_id + "$BatOut$InfoGrp1$TextAll$ScoreGrp$txt_Balls*GEOM*TEXT SET " + 
								bc.getBalls() + "\0", print_writers);
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrames$AllGraphics$Side" + WhichSide + "$MatchSummary$SummaryGrp" + 
								whichInning + "$Inn" + inn_no + "$SummaryInfoGrps$Row_" + row_id + "$BatOut$InfoGrp1$TextAll$ScoreGrp$txt_Not-Out*GEOM*TEXT SET " + 
								(bc.getStatus().equalsIgnoreCase(CricketUtil.NOT_OUT)?"*":"") + "\0", print_writers);
					}
					
					if(whichInning == 2) {
						if(i == 1 && row_id >= 4) {
							break;
						}else if(i == 2 && row_id >= 4) {
							break;
						}
					}else if(whichInning == 3) {
						if(i == 1 && row_id >= 3) {
							break;
						}else if(i == 2 && row_id >= 3) {
							break;
						}
						else if(i == 3 && row_id >= 3) {
							break;
						}
					}else if(whichInning == 4) {
						if(i == 1 && row_id >= 2) {
							break;
						}else if(i == 2 && row_id >= 2) {
							break;
						}
						else if(i == 3 && row_id >= 2) {
							break;
						}else if(i == 4 && row_id >= 2) {
							break;
						}
					}
				}
			}
			for(int j = row_id + 1; j <= max_Strap; j++) {
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrames$AllGraphics$Side" + WhichSide + "$MatchSummary$SummaryGrp" + 
						whichInning + "$Inn" + inn_no + "$SummaryInfoGrps$Row_" + j + "$BatOut*ACTIVE SET 0\0", print_writers);
			}
			
			if(whichInning == 2 || whichInning == 3 || whichInning == 4) {
				row_id = 0;
			}
			
			if(matchAllData.getMatch().getInning().get(i-1).getBowlingCard() != null) {
				Collections.sort(matchAllData.getMatch().getInning().get(i-1).getBowlingCard(),new CricketFunctions.BowlerFiguresComparator());
				for(BowlingCard boc : matchAllData.getMatch().getInning().get(i-1).getBowlingCard()) {
					if(boc.getWickets() > 0 ) {
						row_id = row_id + 1;
						
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrames$AllGraphics$Side" + WhichSide + "$MatchSummary$SummaryGrp" + 
								whichInning + "$Inn" + inn_no + "$SummaryInfoGrps$Row_" + row_id + "$BallOut*ACTIVE SET 1\0", print_writers);
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrames$AllGraphics$Side" + WhichSide + "$MatchSummary$SummaryGrp" + 
								whichInning + "$Inn" + inn_no + "$SummaryInfoGrps$Row_" + row_id + "$BallOut$InfoGrp1$SummaryHighlightOmo*FUNCTION*Omo*vis_con SET "
										+ "0\0", print_writers);
						
						CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*BACK_LAYER*TREE*$gfx_FullFrames$AllGraphics$Side" + WhichSide + "$MatchSummary$"
								+ "SummaryGrp" + whichInning + "$Inn" + inn_no + "$SummaryInfoGrps$Row_" + row_id + "$BallOut$InfoGrp1$TextAll$PlayerName$select_Language"
								+ "*FUNCTION*Omo*vis_con SET ", config, Constants.BCCI, print_writers, foreignLanguageOmo);
						foreignLanguageData = CricketFunctions.AssembleMultiLanguageData(CricketUtil.PLAYER, CricketUtil.TICKERNAME, multilanguagedata, 
								boc.getPlayer().getTicker_name(), "", null, 0, foreignLanguageDataList);
						CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*BACK_LAYER*TREE*$gfx_FullFrames$AllGraphics$Side" + WhichSide + "$MatchSummary$"
								+ "SummaryGrp" + whichInning + "$Inn" + inn_no + "$SummaryInfoGrps$Row_" + row_id + "$BallOut$InfoGrp1$TextAll$PlayerName$English$txt_Name"
										+ "*GEOM*TEXT SET ", config, Constants.BCCI, print_writers, foreignLanguageData);
						
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrames$AllGraphics$Side" + WhichSide + "$MatchSummary$SummaryGrp" + 
								whichInning + "$Inn" + inn_no + "$SummaryInfoGrps$Row_" + row_id + "$BallOut$InfoGrp1$TextAll$ScoreGrp$txt_Figs*GEOM*TEXT SET " + 
								 boc.getWickets() + "-" + boc.getRuns() + "\0", print_writers);
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrames$AllGraphics$Side" + WhichSide + "$MatchSummary$SummaryGrp" + 
								whichInning + "$Inn" + inn_no + "$SummaryInfoGrps$Row_" + row_id + "$BallOut$InfoGrp1$TextAll$ScoreGrp$txt_Overs*GEOM*TEXT SET " + 
								CricketFunctions.OverBalls(boc.getOvers(), boc.getBalls()) + "\0", print_writers);
					}
					
					if(whichInning == 2) {
						if(i == 1 && row_id >= 4) {
							break;
						}else if(i == 2 && row_id >= 4) {
							break;
						}
					}else if(whichInning == 3) {
						if(i == 1 && row_id >= 3) {
							break;
						}else if(i == 2 && row_id >= 3) {
							break;
						}
						else if(i == 3 && row_id >= 3) {
							break;
						}
					}else if(whichInning == 4) {
						System.out.println("i : " + i + " - row_id : " + row_id);
						if(i == 1 && row_id >= 2) {
							break;
						}else if(i == 2 && row_id >= 2) {
							break;
						}
						else if(i == 3 && row_id >= 2) {
							break;
						}else if(i == 4 && row_id >= 2) {
							break;
						}
					}
				}
			}
			for(int j = row_id + 1; j <= max_Strap; j++) {
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrames$AllGraphics$Side" + WhichSide + "$MatchSummary$SummaryGrp" + 
						whichInning + "$Inn" + inn_no + "$SummaryInfoGrps$Row_" + j + "$BallOut*ACTIVE SET 0\0", print_writers);
			}
		}
		return Constants.OK;
	}
	
	public String MatchIdentBody(List<PrintWriter> print_writers, int WhichSide, Configuration config, MatchAllData matchAllData,Inning inning, 
			MultiLanguageDatabase multilanguagedata, List<ForeignLanguageData> foreignLanguageDataList, List<Dictionary> dict) {
		
		foreignLanguageData = new ArrayList<ForeignLanguageData>();
		foreignLanguageOmo = new ArrayList<ForeignLanguageData>();
		foreignLanguageData.add(new ForeignLanguageData("", "", "", ""));
		foreignLanguageOmo.add(new ForeignLanguageData("1", "2", "3", "4"));
		
		CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_Ident$MainGfx$All_MatchNum$select_Language*FUNCTION*Omo*vis_con SET 1\0", print_writers);
		CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_Ident$MainGfx$All_MatchNum$English$txt_MatchNumber*GEOM*TEXT SET " 
				+ matchAllData.getSetup().getTournament() + ", " + matchAllData.getSetup().getMatchIdent() + "\0", print_writers);
		
		CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_Ident$MainGfx$SubHeaderGrp$select_Language*FUNCTION*Omo*vis_con SET 1\0", print_writers);
		CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_Ident$MainGfx$SubHeaderGrp$English$txt_SubHead*GEOM*TEXT SET \0", print_writers);
		
		CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*BACK_LAYER*TREE*$gfx_Ident$MainGfx$All_Team_Name_Grp$Team1$select_Language*FUNCTION*Omo*vis_con SET ", 
				config, Constants.BCCI, print_writers, foreignLanguageOmo);
		foreignLanguageData = CricketFunctions.AssembleMultiLanguageData(CricketUtil.TEAM, "", multilanguagedata, matchAllData.getSetup().getHomeTeam().getTeamName1(), 
				"", null, 0, foreignLanguageDataList);
		CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*BACK_LAYER*TREE*$gfx_Ident$MainGfx$All_Team_Name_Grp$Team1$English$txt_TeamName*GEOM*TEXT SET ", 
				config, Constants.BCCI, print_writers, foreignLanguageData);
		
		CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*BACK_LAYER*TREE*$gfx_Ident$MainGfx$All_Team_Name_Grp$Team2$select_Language*FUNCTION*Omo*vis_con SET ", 
				config, Constants.BCCI, print_writers, foreignLanguageOmo);
		foreignLanguageData = CricketFunctions.AssembleMultiLanguageData(CricketUtil.TEAM, "", multilanguagedata, matchAllData.getSetup().getAwayTeam().getTeamName1(), 
				"", null, 0, foreignLanguageDataList);
		CricketFunctions.DoadWriteVariousLanguageTextToEachViz("RENDERER*BACK_LAYER*TREE*$gfx_Ident$MainGfx$All_Team_Name_Grp$Team2$English$txt_TeamName*GEOM*TEXT SET ", 
				config, Constants.BCCI, print_writers, foreignLanguageData);
		return Constants.OK;
	}
}
	