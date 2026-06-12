package com.cricket.captions;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.concurrent.TimeUnit;

import com.cricket.containers.Infobar;
import com.cricket.containers.LowerThird;
import com.cricket.model.Configuration;
import com.cricket.model.MatchAllData;
import com.cricket.util.CricketFunctions;
import com.cricket.util.CricketUtil;

public class Animation 
{
	public String whichGraphicOnScreen = "", specialBugOnScreen = "", status = "", whichPlayer = "", isComp = "",
		prevWhichPlayer = "",targetOnScreen = "",tapeballOnScreen = "", HighlightDirector = "0",prevHighlightDirector = "0", prevLeaderHighlight = "0", 
		swhichScorecard = "",audioenabled = "",bugs_pre = "",TeamName = "",Data="",containerName="",containerName2="",containerName3="",watermarkOnScreen = "";
	public Infobar infobar;
	public Caption caption;
	public int lastNumberOfRows=0,footercount=0,lineUpCount=0;
	public InfobarGfx this_infobarGfx;
	public FullFramesGfx this_fullFramesGfx;
	public MatchAllData matchAllData;
	public boolean sponsorOnScreen = false;
	public boolean ExtraInfoOnScreen = false;
	public boolean MiddleSectionInfoOnScreen = false;
	public boolean bigScoreBug_On_Screen = false;
	public boolean LineUpBigImage_On_Screen = false;
	
	LowerThird LT = new LowerThird();
	
	public BugsAndMiniGfx this_bugs;
	public LowerThirdGfx this_lowerGfx;
	
	
	public Animation(Infobar infobar) {
		super();
		this.infobar = infobar;
	}

	public Animation() {
		super();
	}
	public String getTypeOfGraphicsOnScreen(Configuration config,String whatToProcess)
	{
		switch (config.getBroadcaster().toUpperCase()) {
		case Constants.BCCI:
			switch (whatToProcess.split(",")[0]) {
			case "Control_F12": case "Shift_F12":
			case "Alt_1": case "Alt_2": case "Alt_3": case "Alt_5": case "Alt_7": case "Alt_8":
				return Constants.INFO_BAR;
			case "F1": case "F2": case "F4": case "Control_F11": case "Control_Shift_F1":
				return Constants.FULL_FRAMER;
			}
			break;
		case Constants.AFG_SL_SERIES:
			switch (whatToProcess.split(",")[0]) {
			case "Control_F12": case "Shift_F12":
			case "Alt_1": case "Alt_2": case "Alt_7":
				return Constants.INFO_BAR;
			case "m": case "Control_m": case "F1": case "F2": case "Control_F7": case "Shift_T": case "Control_d": case "Control_e": case "Shift_P": 
			case "Shift_Q": case "Control_Alt_F1": case "F4": case "Control_F11": case "Shift_F11": case "Shift_K": case "Shift_F10": case "Control_F10": 
			case "Alt_F11": case "Control_F1": case "Shift_D": case "Control_Shift_F1":
				return Constants.FULL_FRAMER;
			}
			break;
		case Constants.BAN_AFG_SERIES:
			switch (whatToProcess.split(",")[0]) {
			case "Control_F12": case "Shift_F12": case "Alt_2": case "Alt_5": case "Alt_8": case "Alt_e":
				return Constants.INFO_BAR;
			case "Shift_O": case "Control_k": case "k": case "g": case "y": case "Control_Shift_R": case "Control_Shift_U": case "Control_Shift_V":
			case "h": case "Shift_F4": case "Shift_F":case "Alt_b": case "Alt_p": case "Control_Shift_F3":  case "Shift_C": case "Control_Shift_J": case "6":
			case "Control_y": case "Control_4": case "Alt_Shift_J": case "Control_Shift_U_change_on": case "Control_Shift_V_change_on": case "5": case ";":
			case "Control_Shift_*":
				return Constants.BUGS;
			case "F5": case "F6": case "F8": case "F9": case "F10": case "Alt_F8":
			case "Control_F6": case "Control_F5": case "Control_F9":
			case "Shift_F6": case "Shift_F3": case "Shift_F5": case "Shift_F9": case "Alt_F12":
			case "Control_Shift_L": case "Control_Shift_M": case "u": case "Control_a":
			case "F7": case "F11": case "Control_s": case "Control_f": case "Control_Shift_B":
			case "Control_Shift_O": case "Control_h": case "Control_F3": case "d":	
				return Constants.LOWER_THIRD;
			case "Shift_F1": case "Shift_F2": case "Alt_F1": case "Alt_F2": case "Alt_F7":
				return Constants.MINIS;	
			case "m": case "Control_m": case "F1": case "Control_Shift_F1": case "F2": case "Control_Shift_F2": case "Control_F11": case "Shift_F11":
			case "Control_F7": case "F4": case "Shift_K": case "Shift_T": case "Shift_D": case "Control_F10": case "Control_d": case "Control_e":
			case "Shift_P": case "Shift_Q": case "Shift_F10": case "z": case "x": case "c": case "v": case "Control_z": case "Control_x": 
			case "Control_Shift_Z": case "Control_Shift_Y":
				return Constants.FULL_FRAMER;

			}
			break;
		case Constants.ACC:
			switch (whatToProcess.split(",")[0]) {
			case "Control_F12": case "Shift_F12": case "Alt_2": case "Alt_7": case "Alt_8": case "Alt_5": case "Alt_e":
				return Constants.INFO_BAR;
			case "Shift_O": case "Control_k": case "k": case "g": case "y": case "Control_Shift_R": case "Control_Shift_U": case "Control_Shift_V":
			case "h": case "Shift_F4": case "Shift_F":case "Alt_b": case "Alt_p": case "Control_Shift_F3":  case "Shift_C": case "Control_Shift_J": case "6":
			case "Control_y": case "Control_4": case "Alt_Shift_J": case "Control_Shift_U_change_on": case "Control_Shift_V_change_on": case "5": case ";":
			case "Control_Shift_*": case "Control_5": case "Control_7":
				return Constants.BUGS;
			case "Shift_F1": case "Shift_F2": case "Alt_F1": case "Alt_F2": case "Alt_F7":
				return Constants.MINIS;	
			case "Alt_g":
				return "WATERMARK";	
			case "F5": case "F6": case "F8": case "F9": case "F10": case "Alt_F8": case "Control_F6": case "Control_F5": case "Control_F9":
			case "Shift_F6": case "Shift_F3": case "Shift_F5": case "Shift_F9": case "Alt_F12": case "Control_Shift_L": case "Control_Shift_M": 
			case "u": case "Control_a": case "F7": case "F11": case "Control_s": case "Control_f": case "Control_Shift_O": case "Control_h": 
			case "Control_F3": case "d": case "Control_Shift_B": case "Alt_Shift_F3": case "l": case "Alt_Shift_Q": case "Alt_Shift_F4":
			case "Alt_d":	
				return Constants.LOWER_THIRD;
			case "m": case "Control_m": case "F1": case "Control_Shift_F1": case "F2": case "Control_Shift_F2": case "Control_F11": case "Shift_F11":
			case "Control_F7": case "F4": case "Shift_K": case "Shift_T": case "Shift_D": case "Control_F10": case "Control_d": case "Control_e":
			case "Shift_P": case "Shift_Q": case "Shift_F10": case "z": case "x": case "c": case "v": case "Control_z": case "Control_x": 
			case "Control_Shift_Z": case "Control_Shift_Y": case "Control_p":
				return Constants.FULL_FRAMER;
			}
			break;	
		case Constants.TRI_SERIES:  case Constants.MT20:
			switch (whatToProcess.split(",")[0]) {
			case "Control_F12": case "Shift_F12":
			case "Alt_1": case "Alt_2": case "Alt_3": case "Alt_4": case "Alt_5": case "Alt_6": 
			case "Alt_7": case "Alt_8": case "Alt_e":
				return Constants.INFO_BAR;
			case "Shift_O": case "Control_k": case "k": case "g": case "y": case "Control_Shift_R": case "Control_Shift_U": case "Control_Shift_V":
			case "h": case "Shift_F4": case "Shift_F":case "Alt_b": case "Alt_p": case "Control_Shift_F3":  case "Shift_C": case "Control_Shift_J": case "6":
			case "Control_y": case "Control_4": case "Alt_Shift_J": case "Control_Shift_U_change_on": case "Control_Shift_V_change_on": case "5": case ";":
			case "Control_Shift_*":	
				return Constants.BUGS;
			case "Shift_F1": case "Shift_F2": case "Alt_F7":
				return Constants.MINIS;
			case "Alt_g":
				return "WATERMARK";	
			case "F1": case "F2": case "m": case "Control_d": case "Control_e": case "Control_m": case "Control_F7": case "Control_F11":
			case "Control_Shift_F1": case "Control_Shift_F2": case "Shift_D": case "Control_Shift_F7": case "F4": case "Alt_F9": case "Alt_F11":
			case "Control_F10": case "Shift_F10": case "Shift_K": case "Control_F1": case "Shift_F11": case "Control_p": case "Control_Alt_F1":
			case "Alt_Shift_F1": case "z": case "x": case "c": case "v": case "Control_z": case "Control_x": case "Control_Shift_Z": case "Control_Shift_Y": 
			case "Control_Shift_F4": case "Control_Shift_F5": case "Shift_P": case "Shift_Q":
				return Constants.FULL_FRAMER;	
			case "F5": case "F6": case "F7": case "F8": case "F9": case "F10": case "F11": case "Alt_F8":
			case "Control_s": case "Control_f": case "Control_F6": case "Control_F5": case "Control_F9":
			case "Shift_F6": case "u": case "Shift_F3": case "Shift_F5": case "Shift_F9": case "Alt_F12":
			case "Shift_E":	case "d": case "e": case "Control_h": case "Alt_Shift_F3": case "Control_F3":
			case "Control_Shift_M":	case "Control_a": case "Control_Shift_F10": case "Control_Shift_B":
			case "Control_Shift_L":	case "Control_6": case "Control_Shift_O": case "Control_Shift_Q": case "Alt_F1":
			case "Alt_F2": case "Control_Shift_X": case "Control_Shift_K": case "Control_c":
				switch (whatToProcess.split(",")[0]) {
				case "Alt_F8": case "F8": case "F10": case "j": case "Alt_a": case "Alt_s": // Name super L3rd
					return Constants.NAME_SUPERS + Constants.LOWER_THIRD;
				default:
					return Constants.LOWER_THIRD;
				}	
			}
			break;
		}
		return "";
	}
	
	public String AnimateIn(String whatToProcess, List<PrintWriter> print_writers, Configuration config) throws InterruptedException, IOException 
	{
		switch (config.getBroadcaster().toUpperCase()) {
		case Constants.AFG_SL_SERIES:
			switch (whatToProcess.split(",")[0]) {
			case "Control_F12":
				if(this.infobar.isInfobar_on_screen()) {
					
				}else {
					processAnimation(Constants.FRONT, print_writers, "anim_Infobar$InOut$Essencials", "START");
					processAnimation(Constants.FRONT, print_writers, "anim_Infobar$InOut$Ident", "START");
				}
				
				processAnimation(Constants.FRONT, print_writers, "Loop", "START");
				this.infobar.setInfobar_on_screen(true);
				caption.this_infobarGfx.infobar.setInfobar_on_screen(true);
				break;
			case "F12":
				if(this.infobar.isInfobar_on_screen()) {
					processAnimation(Constants.FRONT, print_writers, "Ident_To_Normal", "START");
					TimeUnit.MILLISECONDS.sleep(1500);
					processAnimation(Constants.FRONT, print_writers, "anim_Infobar$InOut$Ident", "SHOW 0.0");
					processAnimation(Constants.FRONT, print_writers, "anim_Infobar$InOut$Normal", "SHOW 1.800");
				}else {
					processAnimation(Constants.FRONT, print_writers, "anim_Infobar$InOut$Essencials", "START");
					processAnimation(Constants.FRONT, print_writers, "anim_Infobar$InOut$Normal", "START");
				}
				processAnimation(Constants.FRONT, print_writers, "BatsmanStrike", "START");
				
				processAnimation(Constants.FRONT, print_writers, "Loop", "START");

				this.infobar.setInfobar_on_screen(true);
				caption.this_infobarGfx.infobar.setInfobar_on_screen(true);
				this.infobar.setInfobar_pushed(false);
				this.infobar.setInfobar_status(Constants.TWO_LINER_INFOBAR);
				break;
			
			case "m": case "Control_m":
				AnimateIn("ArrowDown,", print_writers, config); // Push infobar
				TimeUnit.MILLISECONDS.sleep(500);
				processAnimation("", print_writers, "Loop", "START");
				if(audioenabled.equalsIgnoreCase("TRUE")) {
					processAnimation("", print_writers, "Audio", "START");
				}
				
				processAnimation("", print_writers, "anim_Ident$In_Out", "START");
				this.whichGraphicOnScreen = whatToProcess;
				break;
			case "Shift_D":
				AnimateIn("ArrowDown,", print_writers, config); // Push infobar
				TimeUnit.MILLISECONDS.sleep(500);
				processAnimation("", print_writers, "Loop", "START");
				if(audioenabled.equalsIgnoreCase("TRUE")) {
					processAnimation("", print_writers, "Audio", "START");
				}
				
				processAnimation("", print_writers, "anim_Target$In_Out", "START");
				this.whichGraphicOnScreen = whatToProcess;
				break;
			case "Control_d": case "Control_e": case "Shift_P": case "Shift_Q":
				AnimateIn("ArrowDown,", print_writers, config); // Push infobar
				TimeUnit.MILLISECONDS.sleep(500);
				processAnimation("", print_writers, "Loop", "START");
				if(audioenabled.equalsIgnoreCase("TRUE")) {
					processAnimation("", print_writers, "Audio", "START");
				}
				
				processAnimation("", print_writers, "anim_Profile$In_Out", "START");
				this.whichGraphicOnScreen = whatToProcess;
				break;
			case "F1": case "F2": case "Control_F7": case "Shift_T": case "Control_Alt_F1": case "Alt_Shift_F1": case "F4": 
			case "Control_F11": case "Shift_F11": case "Shift_K": case "Shift_F10": case "Control_F10": case "Alt_F11":
			case "Control_F1": case "Control_Shift_F1":
				AnimateIn("ArrowDown,", print_writers, config); // Push infobar
				TimeUnit.MILLISECONDS.sleep(500);
				
				processAnimation("", print_writers, "Loop", "START");
				processAnimation("", print_writers, "anim_FullFrames$In_Out$Essentials", "START");
				processAnimation("", print_writers, "anim_FullFrames$In_Out$Header", "START");
				processAnimation("", print_writers, "anim_FullFrames$In_Out$SubHeader", "START");
				processAnimation("", print_writers, "anim_FullFrames$In_Out$Logo", "START");
				processAnimation("", print_writers, "anim_FullFrames$In_Out$Icon", "START");
				processAnimation("", print_writers, "anim_FullFrames$In_Out$VerticalText", "START");
				processAnimation("", print_writers, "anim_FullFrames$In_Out$Footer", "START");

				if(audioenabled.equalsIgnoreCase("TRUE")) {
					processAnimation("", print_writers, "Audio", "START");
				}
				
				switch (whatToProcess.split(",")[0]) {
				case "F1":
					processAnimation("", print_writers, "anim_FullFrames$In_Out$Main$BattingCard", "START");
					break;
				case "F4":
					processAnimation("", print_writers, "anim_FullFrames$In_Out$Main$PartnershipList", "START");
					break;
				case "Shift_K":
					processAnimation("", print_writers, "anim_FullFrames$In_Out$Main$Partnership", "START");
					break;
				case "Shift_F10":
					processAnimation("", print_writers, "anim_FullFrames$In_Out$Main$Worm", "START");
					break;
				case "Control_F10":
					processAnimation("", print_writers, "anim_FullFrames$In_Out$Main$Manhattan", "START");
					break;
				case "Alt_F11":
					processAnimation("", print_writers, "anim_FullFrames$In_Out$Main$ManhattanComparison", "START");
					break;
				case "Control_F1":
					processAnimation("", print_writers, "anim_FullFrames$In_Out$Main$ImageBattingCard", "START");
					break;
				case "Control_Alt_F1": case "Alt_Shift_F1": case "Control_Shift_F1":
					processAnimation("", print_writers, "anim_FullFrames$In_Out$Main$SplitCard$Left", "START");
					switch (whatToProcess.split(",")[0]) {
					case "Control_Alt_F1":
						processAnimation("", print_writers, "anim_FullFrames$In_Out$Main$SplitCard$Right$SplitBowling", "START");
						break;
					case "Alt_Shift_F1":
						processAnimation("", print_writers, "anim_FullFrames$In_Out$Main$SplitCard$Right$Contribution", "START");
						break;
					case "Control_Shift_F1":
						switch(whatToProcess.split(",")[3].toUpperCase()) {
						case "PERFORMER":
							processAnimation("", print_writers, "anim_FullFrames$In_Out$Main$SplitCard$Right$HighestScorer", "START");
							processAnimation("", print_writers, "Split_CardHighlight$Side1$" + caption.this_fullFramesGfx.this_ALL_FF.fullframes.getHighlight() + "", "START");
							break;
						case "PARTNERSHIP":
							processAnimation("", print_writers, "anim_FullFrames$In_Out$Main$SplitCard$Right$Partnership", "START");
							break;
						}
						break;
					}
					break;
				case "F2":
					processAnimation("", print_writers, "anim_FullFrames$In_Out$Main$BowlingCard", "START");
					break;
				case "Control_F11": case "Shift_F11":
					processAnimation("", print_writers, "anim_FullFrames$In_Out$Main$Summary", "START");
					break;
				case "Control_F7":
					processAnimation("", print_writers, "anim_FullFrames$In_Out$Main$Teams", "START");
					break;
				case "Shift_T":
					processAnimation("", print_writers, "anim_FullFrames$In_Out$Main$Team", "START");
					break;
				}
				this.whichGraphicOnScreen = whatToProcess;
				break;
			}
			break;
		case Constants.ACC:
			switch (whatToProcess.split(",")[0]) {
			case "ArrowUp":
				if(this.infobar.isInfobar_on_screen() == true && this.infobar.isInfobar_pushed()) {
					processAnimation(Constants.FRONT, print_writers, "Anim_Infobar$FFIn", "CONTINUE REVERSE");
					this.infobar.setInfobar_pushed(false);
				}
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Sponsor_BallSpeed$Sponsor$Sponsor" 
						+ "*FUNCTION*Omo*vis_con SET 0\0", print_writers);
				
				if (caption.this_infobarGfx.infobar.getSection5() == null || caption.this_infobarGfx.infobar.getSection5().isEmpty()) {
				    CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Sponsor_BallSpeed$Sponsor$Sponsor" 
							+ "*FUNCTION*Omo*vis_con SET 1\0", print_writers);
				} 
				
				if (caption.this_infobarGfx.infobar.getSectionAnalytics() == null || caption.this_infobarGfx.infobar.getSectionAnalytics().isEmpty()) {
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Sponsor_BallSpeed$Sponsor$Sponsor" 
							+ "*FUNCTION*Omo*vis_con SET 1\0", print_writers);
				}
				break;
			case "ArrowDown":
				if(this.infobar.isInfobar_on_screen() == true && !this.infobar.isInfobar_pushed()) {
					processAnimation(Constants.FRONT, print_writers, "Anim_Infobar$FFIn", "START");
					this.infobar.setInfobar_pushed(true);
					TimeUnit.MILLISECONDS.sleep(600);
				}
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Sponsor_BallSpeed$Sponsor$Sponsor" 
						+ "*FUNCTION*Omo*vis_con SET 0\0", print_writers);
				break;
			case "ArrowLeft":
				if(this.infobar.isInfobar_on_screen() == true) {
					if(this.infobar.getInfobar_status().equalsIgnoreCase(Constants.TWO_LINER_INFOBAR)) {
						if(!this.infobar.isInfobar_pushed()) {
							processAnimation(Constants.FRONT, print_writers, "Anim_Infobar$LTIn", "START");
						}
						processAnimation(Constants.FRONT, print_writers, "Anim_Infobar$SB_Small$In_Out", "START");
						this.infobar.setInfobar_status(Constants.FORCED + Constants.SHRUNK_INFOBAR);
					}
				}
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Sponsor_BallSpeed$Sponsor$Sponsor" 
						+ "*FUNCTION*Omo*vis_con SET 0\0", print_writers);
				break;
			case "ArrowRight":
				if(this.infobar.isInfobar_on_screen() == true) {
					processAnimation(Constants.FRONT, print_writers, "Anim_Infobar$SB_Small$In_Out", "CONTINUE");
					if(this.infobar.isInfobar_pushed()) {
						processAnimation(Constants.FRONT, print_writers, "Anim_Infobar$FFIn", "CONTINUE REVERSE");
					}else {
						processAnimation(Constants.FRONT, print_writers, "Anim_Infobar$LTIn", "CONTINUE REVERSE");
					}
					this.infobar.setInfobar_status(Constants.TWO_LINER_INFOBAR);
					this.infobar.setInfobar_pushed(false);
				}
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Sponsor_BallSpeed$Sponsor$Sponsor" 
						+ "*FUNCTION*Omo*vis_con SET 0\0", print_writers);
				
				if (caption.this_infobarGfx.infobar.getSection5() == null || caption.this_infobarGfx.infobar.getSection5().isEmpty()) {
				    CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Sponsor_BallSpeed$Sponsor$Sponsor" 
							+ "*FUNCTION*Omo*vis_con SET 1\0", print_writers);
				} 
				
				if (caption.this_infobarGfx.infobar.getSectionAnalytics() == null || caption.this_infobarGfx.infobar.getSectionAnalytics().isEmpty()) {
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Sponsor_BallSpeed$Sponsor$Sponsor" 
							+ "*FUNCTION*Omo*vis_con SET 1\0", print_writers);
				}
				
				break;
				
			case Constants.SHRUNK_INFOBAR:
				if(this.infobar.isInfobar_on_screen() == true) {
					if(this.infobar.getInfobar_status().equalsIgnoreCase(Constants.TWO_LINER_INFOBAR)) {
						processAnimation(Constants.FRONT, print_writers, "Anim_Infobar$LTIn", "START");
						this.infobar.setInfobar_status(Constants.SHRUNK_INFOBAR);
					} else if(this.infobar.getInfobar_status().equalsIgnoreCase(Constants.SHRUNK_INFOBAR)) {
						processAnimation(Constants.FRONT, print_writers, "Anim_Infobar$LTIn", "CONTINUE REVERSE");
						this.infobar.setInfobar_status(Constants.TWO_LINER_INFOBAR);
					}
				}
				break;
				
				//---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
			case "i":
				if(this.infobar.isInfobar_on_screen() == true && !this.infobar.isInfobar_pushed()) {
					if(infobar.isFreeHit_on_screen() == false) {
						processAnimation(Constants.FRONT, print_writers, "Anim_Infobar$Section2$FreeHitIn", "START");
						infobar.setFreeHit_on_screen(true);
					}else if(infobar.isFreeHit_on_screen() == true) {
						processAnimation(Constants.FRONT, print_writers, "Anim_Infobar$Section2$FreeHitOut", "START");
						infobar.setFreeHit_on_screen(false);
					}
				}
				break;
			case "8":
				TeamName = caption.this_infobarGfx.inning.getBowling_team().getTeamBadge();
				Data = "ON HAT-TRICK";
				
				if(this.infobar.isInfobar_on_screen() == true && !this.infobar.isInfobar_pushed()) {
					if(infobar.isHatTrick_on_screen() == false) {
						processAnimation(Constants.FRONT, print_writers, "Anim_Infobar$Section2$OnHattrickIn", "START");
						infobar.setHatTrick_on_screen(true);
					}else if(infobar.isHatTrick_on_screen() == true) {
						processAnimation(Constants.FRONT, print_writers, "Anim_Infobar$Section2$OnHattrickOut", "START");
						TimeUnit.MILLISECONDS.sleep(500);
						processAnimation(Constants.FRONT, print_writers, "Anim_Infobar$Section2$OnHattrickIn", "SHOW 0.0");
						processAnimation(Constants.FRONT, print_writers, "Anim_Infobar$Section2$OnHattrickOut", "SHOW 0.0");
						infobar.setHatTrick_on_screen(false);
					}
				}
				
				break;
			case "s": case "f": case "w": case "0": case "9": case ".":
				switch(whatToProcess.split(",")[0]) {
				case "s": 
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section2_Wipes$Select*FUNCTION*Omo*vis_con SET 1\0", print_writers);
					break;
				case "f":
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section2_Wipes$Select*FUNCTION*Omo*vis_con SET 0\0", print_writers);
					break;
				case "w":
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section2_Wipes$Select*FUNCTION*Omo*vis_con SET 2\0", print_writers);
					break;
				case "0":
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section2_Wipes$Select*FUNCTION*Omo*vis_con SET 5\0", print_writers);
					break;
				case "9":
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section2_Wipes$Select*FUNCTION*Omo*vis_con SET 4\0", print_writers);
					break;
				case ".":
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Infobar$Section2_Wipes$Select*FUNCTION*Omo*vis_con SET 3\0", print_writers);
					break;	
				}
				
				processAnimation(Constants.FRONT, print_writers, "Anim_Infobar$Section2_Wipes", "START");
				break;
			case "Alt_g":
				
				if(this.watermarkOnScreen.equalsIgnoreCase("WATERMARK")) {
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$WaterMark$Select*FUNCTION*Omo*vis_con SET 0\0", print_writers);
					
					processAnimation(Constants.FRONT, print_writers, "Watermark", "CONTINUE");
					this.watermarkOnScreen = "";
				}else {
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$WaterMark$Select*FUNCTION*Omo*vis_con SET 1\0", print_writers);
					
					processAnimation(Constants.FRONT, print_writers, "Watermark", "START");
					this.watermarkOnScreen = "WATERMARK";
				}
				break;		
				
//----------------------------------------------------------------------------------------------------------------------------				
			
			case "Control_F12":
				if(this.infobar.isInfobar_on_screen()) {
					processAnimation(Constants.FRONT, print_writers, "Anim_Infobar$Out", "START");
					TimeUnit.MILLISECONDS.sleep(550);
					processAnimation(Constants.FRONT, print_writers, "Anim_Infobar$In", "SHOW 0.0");
				}
				
				processAnimation(Constants.FRONT, print_writers, "Anim_Infobar$IdentIn", "START");
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Sponsor_BallSpeed$Sponsor$Sponsor" 
						+ "*FUNCTION*Omo*vis_con SET 0\0", print_writers);
				this.infobar.setInfobar_on_screen(true);
				caption.this_infobarGfx.infobar.setInfobar_on_screen(true);
				caption.this_infobarGfx.infobar.setInfobar_status("IDENT");
				break;
			case "F12":	
				if(this.infobar.isInfobar_on_screen()) {
					processAnimation(Constants.FRONT, print_writers, "Anim_Infobar$IdentOut", "START");
				}else {
					processAnimation(Constants.FRONT, print_writers, "Anim_Infobar$In$BaseIn", "START");
				}
				
				processAnimation(Constants.FRONT, print_writers, "Anim_Infobar$In$Logos", "START");
				processAnimation(Constants.FRONT, print_writers, "Anim_Infobar$In$MainIn", "START");
				processAnimation(Constants.FRONT, print_writers, "Anim_Infobar$Section2$In_Out", "START");
				processAnimation(Constants.FRONT, print_writers, "Anim_Infobar$Batsman1$In_Out", "START");
				processAnimation(Constants.FRONT, print_writers, "Anim_Infobar$Batsman2$In_Out", "START");
				processAnimation(Constants.FRONT, print_writers, "Anim_Infobar$OnStrike$In_Out", "START");
				processAnimation(Constants.FRONT, print_writers, "Anim_Infobar$Bowler$In_Out$BowlerIn", "START");
				
				this.infobar.setInfobar_on_screen(true);
				caption.this_infobarGfx.infobar.setInfobar_on_screen(true);
				caption.this_infobarGfx.infobar.setInfobar_status("INFOBAR");
				this.infobar.setInfobar_pushed(false);
				this.infobar.setInfobar_status(Constants.TWO_LINER_INFOBAR);
				break;
				
			case "Shift_O": case "Control_k": case "k": case "g": case "y": case "Control_y": case "h": case "Shift_F4": case "Shift_F":case "Alt_b":
			case "Control_Shift_R": case "Control_Shift_F3": case "Control_Shift_J": case "Alt_p":
				AnimateIn("ArrowDown,", print_writers, config); // Push infobar
				TimeUnit.MILLISECONDS.sleep(300);
				processAnimation(Constants.FRONT, print_writers, "Bugs$In_Out", "START");
				this.whichGraphicOnScreen = whatToProcess;
				break;
			case "Shift_C":
				AnimateIn("ArrowDown,", print_writers, config); // Push infobar
				TimeUnit.MILLISECONDS.sleep(500);
				processAnimation(Constants.FRONT, print_writers, "SixDistance", "START");
				this.whichGraphicOnScreen = whatToProcess;
				break;	
			case "Control_Shift_U": case "Control_Shift_V":
				TimeUnit.MILLISECONDS.sleep(500);
				processAnimation(Constants.FRONT, print_writers, "PopUp$In_Out", "START");
				this.whichGraphicOnScreen = whatToProcess;
				break;
			case "Shift_F1": case "Shift_F2": case "Alt_F7": case "Alt_F1": case "Alt_F2":
				TimeUnit.MILLISECONDS.sleep(500);
				processAnimation(Constants.FRONT, print_writers, "Mini$In_Out", "START");
				this.whichGraphicOnScreen = whatToProcess;
				break;
				
			case "F5": case "F6": case "F9": case "F8": case "F10": case "Alt_F8": case "Control_F6": case "Control_F5": case "Control_F9": case "Shift_F6": 
			case "Shift_F3": case "Shift_F5": case "Shift_F9": case "Alt_F12": case "Control_Shift_L": case "Control_Shift_M": case "u": case "Control_a":	
			case "F7": case "F11": case "Control_s": case "Control_f": case "Control_Shift_O": case "Control_h": case "Control_F3": case "d": case "Control_Shift_B":
			case "Alt_Shift_F3": case "l": case "Alt_Shift_F4": case "Alt_d":
				
				if(this.infobar.isInfobar_on_screen() == true) {
					switch (whatToProcess.split(",")[0]) {
					case "F8": case "F10": case "Alt_F8": case "Control_F5": case "Control_F9":
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$LT_Position*"
								+ "TRANSFORMATION*POSITION*Y SET 0.0 \0",print_writers);
						break;
					default:
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$LT_Position*"
								+ "TRANSFORMATION*POSITION*Y SET 5.0 \0",print_writers);
						break;
					}
				}else {
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$LT_Position*"
							+ "TRANSFORMATION*POSITION*Y SET 0.0 \0",print_writers);
				}
				
				if(infobar.getInfobar_status() != null) {
					if(!infobar.getInfobar_status().equalsIgnoreCase(Constants.FORCED+Constants.SHRUNK_INFOBAR)) {
						AnimateIn("ArrowLeft" + ",", print_writers, config); // Shrink infobar
						TimeUnit.MILLISECONDS.sleep(1000);
						infobar.setInfobar_status(Constants.SHRUNK_INFOBAR);
					}
				}
				
				TimeUnit.MILLISECONDS.sleep(200);
				
				processAnimation(Constants.FRONT, print_writers, "LT$Logo$In_Out$In", "START");
				processAnimation(Constants.FRONT, print_writers, "LT$Base$In_Out$In", "START");
				processAnimation(Constants.FRONT, print_writers, "LT$Data$In_Out$In", "START");

				this.whichGraphicOnScreen = whatToProcess;
				break;	
				
			case "m": case "Control_m":
				AnimateIn("ArrowDown,", print_writers, config); // Push infobar
				TimeUnit.MILLISECONDS.sleep(500);
				processAnimation("", print_writers, "Wipe_BG_In_Out$In_Out", "START");
				processAnimation("", print_writers, "Wipe_BG_In_Out$Loop", "START");
				
				if(audioenabled.equalsIgnoreCase("TRUE")) {
					processAnimation("", print_writers, "Audio", "START");
				}
				
				processAnimation("", print_writers, "MatchID$Start_End", "START");
				processAnimation("", print_writers, "MatchID$ALL", "START");
				
				this.whichGraphicOnScreen = whatToProcess;
				break;
			case "z": case "x": case "c": case "v": case "Control_z": case "Control_x": case "Control_Shift_Z": case "Control_Shift_Y":
				
				AnimateIn("ArrowDown,", print_writers, config); // Push infobar
				processAnimation("", print_writers, "Wipe_BG_In_Out$In_Out", "START");
				processAnimation("", print_writers, "Wipe_BG_In_Out$Loop", "START");
				if(audioenabled.equalsIgnoreCase("TRUE")) {
					processAnimation("", print_writers, "Audio", "START");
				}
				
				processAnimation("", print_writers, "Full_Frames$Essentials$In_Out", "START");
				processAnimation("", print_writers, "Full_Frames$Main_Graphics$In_Out$Top5$In_Out", "START");
				this.whichGraphicOnScreen = whatToProcess;
				break;
			case "Alt_Shift_Q":
				processAnimation(Constants.BACK, print_writers, "Plotter", "START");
				this.whichGraphicOnScreen = whatToProcess;
				
				caption.this_infobarGfx.infobar.setFieldPlotter_on_screen(true);
				
				break;	
			case "6": case "Control_4": case "5": case ";": case "Control_5": case "Control_7":
				processAnimation(Constants.FRONT, print_writers, "Counter$InOut", "START");
				TimeUnit.MILLISECONDS.sleep(1700);
				this.whichGraphicOnScreen = whatToProcess;
				if(!caption.this_bugsAndMiniGfx.this_data_str.get(caption.this_bugsAndMiniGfx.this_data_str.size()-2).split(",")[0].
						equalsIgnoreCase(caption.this_bugsAndMiniGfx.this_data_str.get(caption.this_bugsAndMiniGfx.this_data_str.size()-1).split(",")[0])) {
					processAnimation(Constants.FRONT, print_writers, "Counter$Change_Sixes$Hundreds", "START");
					processAnimation(Constants.FRONT, print_writers, "Counter$Change_Sixes$Tens", "START");
					processAnimation(Constants.FRONT, print_writers, "Counter$Change_Sixes$Units", "START");
				}
				else if(!caption.this_bugsAndMiniGfx.this_data_str.get(caption.this_bugsAndMiniGfx.this_data_str.size()-2).split(",")[1].
						equalsIgnoreCase(caption.this_bugsAndMiniGfx.this_data_str.get(caption.this_bugsAndMiniGfx.this_data_str.size()-1).split(",")[1])) {
					processAnimation(Constants.FRONT, print_writers, "Counter$Change_Sixes$Tens", "START");
					processAnimation(Constants.FRONT, print_writers, "Counter$Change_Sixes$Units", "START");
				}
				else if(!caption.this_bugsAndMiniGfx.this_data_str.get(caption.this_bugsAndMiniGfx.this_data_str.size()-2).split(",")[2].
						equalsIgnoreCase(caption.this_bugsAndMiniGfx.this_data_str.get(caption.this_bugsAndMiniGfx.this_data_str.size()-1).split(",")[2])) {
					processAnimation(Constants.FRONT, print_writers, "Counter$Change_Sixes$Units", "START");
				}
				break;	
			case "F1": case "Control_Shift_F1": case "F2": case "Control_Shift_F2": case "Control_F11": case "F4": case "Shift_F11": case "Control_F7":
			case "Shift_K": case "Shift_T": case "Shift_D": case "Control_F10": case "Control_d": case "Control_e": case "Shift_P": case "Shift_Q":
			case "Shift_F10": case "Control_p":
				AnimateIn("ArrowDown,", print_writers, config); // Push infobar
				TimeUnit.MILLISECONDS.sleep(500);
				
				processAnimation("", print_writers, "Wipe_BG_In_Out$In_Out", "START");
				processAnimation("", print_writers, "Wipe_BG_In_Out$Loop", "START");
				processAnimation("", print_writers, "Full_Frames$Essentials$In_Out", "START");
				
				if(audioenabled.equalsIgnoreCase("TRUE")) {
					processAnimation("", print_writers, "Audio", "START");
				}
				
				switch (whatToProcess.split(",")[0]) {
				case "Control_F11": case "Shift_F11":
					processAnimation("", print_writers, "Full_Frames$Main_Graphics$In_Out$Summary$In_Out", "START");
					break;
				case "Control_p":
					processAnimation("", print_writers, "Full_Frames$Main_Graphics$In_Out$Standings$In_Out", "START");
					break;
				case "Control_F7":
					processAnimation("", print_writers, "Full_Frames$Main_Graphics$In_Out$LineUp_Both$In_Out", "START");
					break;
				case "Shift_K":
					processAnimation("", print_writers, "Full_Frames$Main_Graphics$In_Out$CurrPart$In_Out", "START");
					break;
				case "Shift_T":
					processAnimation("", print_writers, "Full_Frames$Main_Graphics$In_Out$PlayingXI_Image$In_Out", "START");
					break;
				case "Shift_D":
					processAnimation("", print_writers, "Full_Frames$Main_Graphics$In_Out$Target$In_Out", "START");
					break;
				case "Control_F10":
					processAnimation("", print_writers, "Full_Frames$Main_Graphics$In_Out$Manhattan$In_Out", "START");
					break;
				case "Shift_F10":
					processAnimation("", print_writers, "Full_Frames$Main_Graphics$In_Out$Worm$In_Out", "START");
					break;
				case "Control_d": case "Control_e": case "Shift_P": case "Shift_Q":
					processAnimation("", print_writers, "Full_Frames$Main_Graphics$In_Out$Profile$In_Out", "START");
					break;
				case "F4":
					processAnimation("", print_writers, "Full_Frames$Main_Graphics$In_Out$Partnership_Table$In_Out", "START");
					break;
				case "F1": case "Control_Shift_F1":
					processAnimation("", print_writers, "Full_Frames$Main_Graphics$In_Out$BatCard$In_Out", "START");
					
					switch (whatToProcess.split(",")[0]) {
					case "Control_Shift_F1":
						if(caption.this_fullFramesGfx.this_ALL_FF.fullframes.getWhichhighlight().equalsIgnoreCase("PERFORMER")) {
							processAnimation("", print_writers, "Full_Frames$Main_Graphics$In_Out$BatCard$BatHighlight$Left$" + 
									caption.this_fullFramesGfx.this_ALL_FF.fullframes.getHighlight() + "", "START");
						}else if(caption.this_fullFramesGfx.this_ALL_FF.fullframes.getWhichhighlight().equalsIgnoreCase("PARTNERSHIP")) {
							processAnimation("", print_writers, "Full_Frames$Main_Graphics$In_Out$BatCard$BatHighlight$Left$" + 
									caption.this_fullFramesGfx.this_ALL_FF.fullframes.getHighlight() + "", "START");
							processAnimation("", print_writers, "Full_Frames$Main_Graphics$In_Out$BatCard$BatHighlight$Left$" + 
									caption.this_fullFramesGfx.this_ALL_FF.fullframes.getSecondHighlight() + "", "START");
						}
						break;
					}
					break;
				case "F2": case "Control_Shift_F2":
					processAnimation("", print_writers, "Full_Frames$Main_Graphics$In_Out$BallCard$In_Out", "START");
					
					switch (whatToProcess.split(",")[0]) {
					case "Control_Shift_F2":
						processAnimation("", print_writers, "Full_Frames$Main_Graphics$In_Out$BallCard$BallHighlight$Left$" + 
								caption.this_fullFramesGfx.this_ALL_FF.fullframes.getHighlight() + "", "START");
						
						if(caption.this_fullFramesGfx.this_ALL_FF.fullframes.getSecondHighlight() != 0) {
							processAnimation("", print_writers, "Full_Frames$Main_Graphics$In_Out$BallCard$BallHighlight$Left$" + 
									caption.this_fullFramesGfx.this_ALL_FF.fullframes.getSecondHighlight() + "", "START");
						}
						
						break;
					}
					break;
				}
				this.whichGraphicOnScreen = whatToProcess;
				break;
			}
			break;
		case Constants.BAN_AFG_SERIES:
			switch (whatToProcess.split(",")[0]) {
			case "ArrowUp":
				if(this.infobar.isInfobar_on_screen() == true && this.infobar.isInfobar_pushed()) {
					processAnimation(Constants.FRONT, print_writers, "Anim_Infobar$FFIn", "CONTINUE REVERSE");
					this.infobar.setInfobar_pushed(false);
				}
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Sponsor_BallSpeed$Sponsor$Sponsor" 
						+ "*FUNCTION*Omo*vis_con SET 0\0", print_writers);
				
				if (caption.this_infobarGfx.infobar.getSection5() == null || caption.this_infobarGfx.infobar.getSection5().isEmpty()) {
//				    CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Sponsor_BallSpeed$Sponsor$Sponsor" 
//							+ "*FUNCTION*Omo*vis_con SET 1\0", print_writers);
				    CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Sponsor_BallSpeed$Sponsor$Sponsor" 
							+ "*FUNCTION*Omo*vis_con SET 0\0", print_writers);
				} 
				
				if (caption.this_infobarGfx.infobar.getSectionAnalytics() == null || caption.this_infobarGfx.infobar.getSectionAnalytics().isEmpty()) {
//					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Sponsor_BallSpeed$Sponsor$Sponsor" 
//							+ "*FUNCTION*Omo*vis_con SET 1\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Sponsor_BallSpeed$Sponsor$Sponsor" 
							+ "*FUNCTION*Omo*vis_con SET 0\0", print_writers);
				}
				break;
			case "ArrowDown":
				if(this.infobar.isInfobar_on_screen() == true && !this.infobar.isInfobar_pushed()) {
					processAnimation(Constants.FRONT, print_writers, "Anim_Infobar$FFIn", "START");
					this.infobar.setInfobar_pushed(true);
					TimeUnit.MILLISECONDS.sleep(600);
				}
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Sponsor_BallSpeed$Sponsor$Sponsor" 
						+ "*FUNCTION*Omo*vis_con SET 0\0", print_writers);
				break;
			case "ArrowLeft":
				if(this.infobar.isInfobar_on_screen() == true) {
					if(this.infobar.getInfobar_status().equalsIgnoreCase(Constants.TWO_LINER_INFOBAR)) {
						if(!this.infobar.isInfobar_pushed()) {
							processAnimation(Constants.FRONT, print_writers, "Anim_Infobar$LTIn", "START");
						}
						processAnimation(Constants.FRONT, print_writers, "Anim_Infobar$SB_Small$In_Out", "START");
						this.infobar.setInfobar_status(Constants.FORCED + Constants.SHRUNK_INFOBAR);
					}
				}
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Sponsor_BallSpeed$Sponsor$Sponsor" 
						+ "*FUNCTION*Omo*vis_con SET 0\0", print_writers);
				break;
			case "ArrowRight":
				if(this.infobar.isInfobar_on_screen() == true) {
					processAnimation(Constants.FRONT, print_writers, "Anim_Infobar$SB_Small$In_Out", "CONTINUE");
					if(this.infobar.isInfobar_pushed()) {
						processAnimation(Constants.FRONT, print_writers, "Anim_Infobar$FFIn", "CONTINUE REVERSE");
					}else {
						processAnimation(Constants.FRONT, print_writers, "Anim_Infobar$LTIn", "CONTINUE REVERSE");
					}
					this.infobar.setInfobar_status(Constants.TWO_LINER_INFOBAR);
					this.infobar.setInfobar_pushed(false);
				}
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Sponsor_BallSpeed$Sponsor$Sponsor" 
						+ "*FUNCTION*Omo*vis_con SET 0\0", print_writers);
				
				if (caption.this_infobarGfx.infobar.getSection5() == null || caption.this_infobarGfx.infobar.getSection5().isEmpty()) {
//				    CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Sponsor_BallSpeed$Sponsor$Sponsor" 
//							+ "*FUNCTION*Omo*vis_con SET 1\0", print_writers);
				    CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Sponsor_BallSpeed$Sponsor$Sponsor" 
							+ "*FUNCTION*Omo*vis_con SET 0\0", print_writers);
				} 
				
				if (caption.this_infobarGfx.infobar.getSectionAnalytics() == null || caption.this_infobarGfx.infobar.getSectionAnalytics().isEmpty()) {
//					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Sponsor_BallSpeed$Sponsor$Sponsor" 
//							+ "*FUNCTION*Omo*vis_con SET 1\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Sponsor_BallSpeed$Sponsor$Sponsor" 
							+ "*FUNCTION*Omo*vis_con SET 0\0", print_writers);
				}
				
				break;
				
			case Constants.SHRUNK_INFOBAR:
				if(this.infobar.isInfobar_on_screen() == true) {
					if(this.infobar.getInfobar_status().equalsIgnoreCase(Constants.TWO_LINER_INFOBAR)) {
						processAnimation(Constants.FRONT, print_writers, "Anim_Infobar$LTIn", "START");
						this.infobar.setInfobar_status(Constants.SHRUNK_INFOBAR);
					} else if(this.infobar.getInfobar_status().equalsIgnoreCase(Constants.SHRUNK_INFOBAR)) {
						processAnimation(Constants.FRONT, print_writers, "Anim_Infobar$LTIn", "CONTINUE REVERSE");
						this.infobar.setInfobar_status(Constants.TWO_LINER_INFOBAR);
					}
				}
				break;
				
				//---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
			case "i":
				if(this.infobar.isInfobar_on_screen() == true && !this.infobar.isInfobar_pushed()) {
					if(infobar.isFreeHit_on_screen() == false) {
						processAnimation(Constants.FRONT, print_writers, "Anim_Infobar$Section2$FreeHitIn", "START");
						infobar.setFreeHit_on_screen(true);
					}else if(infobar.isFreeHit_on_screen() == true) {
						processAnimation(Constants.FRONT, print_writers, "Anim_Infobar$Section2$FreeHitOut", "START");
						infobar.setFreeHit_on_screen(false);
					}
				}
				break;
			case "8":
				TeamName = caption.this_infobarGfx.inning.getBowling_team().getTeamBadge();
				Data = "ON HAT-TRICK";
				break;
			case "s": case "f": case "w": case "0": case "9": case ".":
				switch(whatToProcess.split(",")[0]) {
				case "s": 
					processAnimation(Constants.FRONT, print_writers, "Anim_Infobar$Section2$SixIn", "START");
					break;
				case "f":
					processAnimation(Constants.FRONT, print_writers, "Anim_Infobar$Section2$FourIn", "START");
					break;
				case "w":
					processAnimation(Constants.FRONT, print_writers, "Anim_Infobar$Section2$WicketIn", "START");
					break;
				case "0":
					processAnimation(Constants.FRONT, print_writers, "Anim_Infobar$Section2$Hat_Trick", "START");
					break;
				case "9":
					processAnimation(Constants.FRONT, print_writers, "Anim_Infobar$Section2$NoBall", "START");
					break;
				case ".":
					processAnimation(Constants.FRONT, print_writers, "Anim_Infobar$Section2$WideIn", "START");
					break;	
				}
				break;
				
//----------------------------------------------------------------------------------------------------------------------------				
			
			case "Control_F12":
				if(this.infobar.isInfobar_on_screen()) {
					processAnimation(Constants.FRONT, print_writers, "Anim_Infobar$Out", "START");
					TimeUnit.MILLISECONDS.sleep(550);
					processAnimation(Constants.FRONT, print_writers, "Anim_Infobar$In", "SHOW 0.0");
				}
				
				processAnimation(Constants.FRONT, print_writers, "Anim_Infobar$IdentIn", "START");
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Sponsor_BallSpeed$Sponsor$Sponsor" 
						+ "*FUNCTION*Omo*vis_con SET 0\0", print_writers);
				this.infobar.setInfobar_on_screen(true);
				caption.this_infobarGfx.infobar.setInfobar_on_screen(true);
				break;
			case "F12":	
				if(this.infobar.isInfobar_on_screen()) {
					processAnimation(Constants.FRONT, print_writers, "Anim_Infobar$IdentOut", "START");
				}else {
					processAnimation(Constants.FRONT, print_writers, "Anim_Infobar$In$BaseIn", "START");
				}
				processAnimation(Constants.FRONT, print_writers, "Anim_Infobar$In$MainIn", "START");
				processAnimation(Constants.FRONT, print_writers, "Anim_Infobar$Section2$In_Out", "START");
				processAnimation(Constants.FRONT, print_writers, "Anim_Infobar$Batsman1$In_Out", "START");
				processAnimation(Constants.FRONT, print_writers, "Anim_Infobar$Batsman2$In_Out", "START");
				processAnimation(Constants.FRONT, print_writers, "Anim_Infobar$OnStrike$In_Out", "START");
				processAnimation(Constants.FRONT, print_writers, "Anim_Infobar$Bowler$In_Out$BowlerIn", "START");
				
				this.infobar.setInfobar_on_screen(true);
				caption.this_infobarGfx.infobar.setInfobar_on_screen(true);
				this.infobar.setInfobar_pushed(false);
				this.infobar.setInfobar_status(Constants.TWO_LINER_INFOBAR);
				break;
				
			case "Shift_O": case "Control_k": case "k": case "g": case "y": case "Control_y": case "h": case "Shift_F4": case "Shift_F":case "Alt_b":
			case "Control_Shift_R": case "Control_Shift_F3": case "Control_Shift_J": case "Alt_p":
				AnimateIn("ArrowDown,", print_writers, config); // Push infobar
				TimeUnit.MILLISECONDS.sleep(300);
				processAnimation(Constants.FRONT, print_writers, "Bugs$In_Out", "START");
				this.whichGraphicOnScreen = whatToProcess;
				break;
			case "Shift_C":
				AnimateIn("ArrowDown,", print_writers, config); // Push infobar
				TimeUnit.MILLISECONDS.sleep(500);
				processAnimation(Constants.FRONT, print_writers, "SixDistance", "START");
				this.whichGraphicOnScreen = whatToProcess;
				break;	
			case "Control_Shift_U": case "Control_Shift_V":
				TimeUnit.MILLISECONDS.sleep(500);
				processAnimation(Constants.FRONT, print_writers, "PopUp$In_Out", "START");
				this.whichGraphicOnScreen = whatToProcess;
				break;
			case "Shift_F1": case "Shift_F2": case "Alt_F7": case "Alt_F1": case "Alt_F2":
				TimeUnit.MILLISECONDS.sleep(500);
				processAnimation(Constants.FRONT, print_writers, "Mini$In_Out", "START");
				this.whichGraphicOnScreen = whatToProcess;
				break;
			case "F5": case "F6": case "F9": case "F8": case "F10": case "Alt_F8":
			case "Control_F6": case "Control_F5": case "Control_F9": case "Shift_F6": 
			case "Shift_F3": case "Shift_F5": case "Shift_F9": case "Alt_F12":
			case "Control_Shift_L": case "Control_Shift_M": case "u": case "Control_a":	
			case "F7": case "F11": case "Control_s": case "Control_f": case "Control_Shift_B":
			case "Control_Shift_O": case "Control_h": case "Control_F3": case "d":
				
				if(this.infobar.isInfobar_on_screen() == true) {
					switch (whatToProcess.split(",")[0]) {
					case "F8": case "F10": case "Alt_F8": case "Control_F5": case "Control_F9":
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$LT_Position*"
								+ "TRANSFORMATION*POSITION*Y SET 0.0 \0",print_writers);
						break;
					default:
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$LT_Position*"
								+ "TRANSFORMATION*POSITION*Y SET 5.0 \0",print_writers);
						break;
					}
				}else {
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT$LT_Position*"
							+ "TRANSFORMATION*POSITION*Y SET 0.0 \0",print_writers);
				}
				
				if(infobar.getInfobar_status() != null) {
					if(!infobar.getInfobar_status().equalsIgnoreCase(Constants.FORCED+Constants.SHRUNK_INFOBAR)) {
						AnimateIn("ArrowLeft" + ",", print_writers, config); // Shrink infobar
						TimeUnit.MILLISECONDS.sleep(1000);
						infobar.setInfobar_status(Constants.SHRUNK_INFOBAR);
					}
				}
				
				TimeUnit.MILLISECONDS.sleep(200);
				
				processAnimation(Constants.FRONT, print_writers, "LT$Logo$In_Out$In", "START");
				processAnimation(Constants.FRONT, print_writers, "LT$Base$In_Out$In", "START");
				processAnimation(Constants.FRONT, print_writers, "LT$Data$In_Out$In", "START");

				this.whichGraphicOnScreen = whatToProcess;
				break;	
				
			case "m": case "Control_m":
				AnimateIn("ArrowDown,", print_writers, config); // Push infobar
				TimeUnit.MILLISECONDS.sleep(500);
				processAnimation("", print_writers, "MatchID$Start_End", "START");
				processAnimation("", print_writers, "MatchID$ALL", "START");
				this.whichGraphicOnScreen = whatToProcess;
				break;
			case "z": case "x": case "c": case "v": case "Control_z": case "Control_x": case "Control_Shift_Z": case "Control_Shift_Y":
				
				AnimateIn("ArrowDown,", print_writers, config); // Push infobar
				
				if(audioenabled.equalsIgnoreCase("TRUE")) {
					processAnimation(Constants.BACK, print_writers, "Audio", "START");
				}
				
				processAnimation("", print_writers, "Full_Frames$Essentials$In_Out", "START");
				processAnimation("", print_writers, "Full_Frames$Sponsor$In_Out", "START");
				processAnimation("", print_writers, "Full_Frames$Main_Graphics$In_Out$Top5$In_Out", "START");
				this.whichGraphicOnScreen = whatToProcess;
				break;
			case "6": case "Control_4": case "5": case ";":
				processAnimation(Constants.FRONT, print_writers, "Counter$InOut", "START");
				TimeUnit.MILLISECONDS.sleep(1700);
				this.whichGraphicOnScreen = whatToProcess;
				if(!caption.this_bugsAndMiniGfx.this_data_str.get(caption.this_bugsAndMiniGfx.this_data_str.size()-2).split(",")[0].
						equalsIgnoreCase(caption.this_bugsAndMiniGfx.this_data_str.get(caption.this_bugsAndMiniGfx.this_data_str.size()-1).split(",")[0])) {
					processAnimation(Constants.FRONT, print_writers, "Counter$Change_Sixes$Hundreds", "START");
					processAnimation(Constants.FRONT, print_writers, "Counter$Change_Sixes$Tens", "START");
					processAnimation(Constants.FRONT, print_writers, "Counter$Change_Sixes$Units", "START");
				}
				else if(!caption.this_bugsAndMiniGfx.this_data_str.get(caption.this_bugsAndMiniGfx.this_data_str.size()-2).split(",")[1].
						equalsIgnoreCase(caption.this_bugsAndMiniGfx.this_data_str.get(caption.this_bugsAndMiniGfx.this_data_str.size()-1).split(",")[1])) {
					processAnimation(Constants.FRONT, print_writers, "Counter$Change_Sixes$Tens", "START");
					processAnimation(Constants.FRONT, print_writers, "Counter$Change_Sixes$Units", "START");
				}
				else if(!caption.this_bugsAndMiniGfx.this_data_str.get(caption.this_bugsAndMiniGfx.this_data_str.size()-2).split(",")[2].
						equalsIgnoreCase(caption.this_bugsAndMiniGfx.this_data_str.get(caption.this_bugsAndMiniGfx.this_data_str.size()-1).split(",")[2])) {
					processAnimation(Constants.FRONT, print_writers, "Counter$Change_Sixes$Units", "START");
				}
				break;	
			case "F1": case "Control_Shift_F1": case "F2": case "Control_Shift_F2": case "Control_F11": case "F4": case "Shift_F11": case "Control_F7":
			case "Shift_K": case "Shift_T": case "Shift_D": case "Control_F10": case "Control_d": case "Control_e": case "Shift_P": case "Shift_Q":
			case "Shift_F10":
				AnimateIn("ArrowDown,", print_writers, config); // Push infobar
				TimeUnit.MILLISECONDS.sleep(500);
				
				processAnimation("", print_writers, "Full_Frames$Essentials$In_Out", "START");
				processAnimation("", print_writers, "Full_Frames$Sponsor$In_Out", "START");
				
				switch (whatToProcess.split(",")[0]) {
				case "Control_F11": case "Shift_F11":
					processAnimation("", print_writers, "Full_Frames$Main_Graphics$In_Out$Summary$In_Out", "START");
					break;
				case "Control_F7":
					processAnimation("", print_writers, "Full_Frames$Main_Graphics$In_Out$LineUp_Both$In_Out", "START");
					break;
				case "Shift_K":
					processAnimation("", print_writers, "Full_Frames$Main_Graphics$In_Out$CurrPart$In_Out", "START");
					break;
				case "Shift_T":
					processAnimation("", print_writers, "Full_Frames$Main_Graphics$In_Out$PlayingXI_Image$In_Out", "START");
					break;
				case "Shift_D":
					processAnimation("", print_writers, "Full_Frames$Main_Graphics$In_Out$Target$In_Out", "START");
					break;
				case "Control_F10":
					processAnimation("", print_writers, "Full_Frames$Main_Graphics$In_Out$Manhattan$In_Out", "START");
					break;
				case "Shift_F10":
					processAnimation("", print_writers, "Full_Frames$Main_Graphics$In_Out$Worm$In_Out", "START");
					break;
				case "Control_d": case "Control_e": case "Shift_P": case "Shift_Q":
					processAnimation("", print_writers, "Full_Frames$Main_Graphics$In_Out$Profile$In_Out", "START");
					break;
				case "F4":
					processAnimation("", print_writers, "Full_Frames$Main_Graphics$In_Out$Partnership_Table$In_Out", "START");
					break;
				case "F1": case "Control_Shift_F1":
					processAnimation("", print_writers, "Full_Frames$Main_Graphics$In_Out$BatCard$In_Out", "START");
					
					switch (whatToProcess.split(",")[0]) {
					case "Control_Shift_F1":
						if(caption.this_fullFramesGfx.this_ALL_FF.fullframes.getWhichhighlight().equalsIgnoreCase("PERFORMER")) {
							processAnimation("", print_writers, "Full_Frames$Main_Graphics$In_Out$BatCard$BatHighlight$Left$" + 
									caption.this_fullFramesGfx.this_ALL_FF.fullframes.getHighlight() + "", "START");
						}else if(caption.this_fullFramesGfx.this_ALL_FF.fullframes.getWhichhighlight().equalsIgnoreCase("PARTNERSHIP")) {
							processAnimation("", print_writers, "Full_Frames$Main_Graphics$In_Out$BatCard$BatHighlight$Left$" + 
									caption.this_fullFramesGfx.this_ALL_FF.fullframes.getHighlight() + "", "START");
							processAnimation("", print_writers, "Full_Frames$Main_Graphics$In_Out$BatCard$BatHighlight$Left$" + 
									caption.this_fullFramesGfx.this_ALL_FF.fullframes.getSecondHighlight() + "", "START");
						}
						break;
					}
					break;
				case "F2": case "Control_Shift_F2":
					processAnimation("", print_writers, "Full_Frames$Main_Graphics$In_Out$BallCard$In_Out", "START");
					
					switch (whatToProcess.split(",")[0]) {
					case "Control_Shift_F2":
						processAnimation("", print_writers, "Full_Frames$Main_Graphics$In_Out$BallCard$BallHighlight$Left$" + 
								caption.this_fullFramesGfx.this_ALL_FF.fullframes.getHighlight() + "", "START");
						
						if(caption.this_fullFramesGfx.this_ALL_FF.fullframes.getSecondHighlight() != 0) {
							processAnimation("", print_writers, "Full_Frames$Main_Graphics$In_Out$BallCard$BallHighlight$Left$" + 
									caption.this_fullFramesGfx.this_ALL_FF.fullframes.getSecondHighlight() + "", "START");
						}
						
						break;
					}
					break;
				}
				this.whichGraphicOnScreen = whatToProcess;
				break;
			}
			break;
		case Constants.TRI_SERIES:  case Constants.MT20:
			switch (whatToProcess.split(",")[0]) {
			case "ArrowUp":
				if(this.infobar.isInfobar_on_screen() == true && this.infobar.isInfobar_pushed()) {
					processAnimation(Constants.FRONT, print_writers, "Push", "CONTINUE REVERSE");
					this.infobar.setInfobar_pushed(false);
				}
				break;
			case "ArrowDown":
				if(this.infobar.isInfobar_on_screen() == true && !this.infobar.isInfobar_pushed()) {
					processAnimation(Constants.FRONT, print_writers, "Push", "START");
					this.infobar.setInfobar_pushed(true);
					TimeUnit.MILLISECONDS.sleep(600);
				}
				break;
			case "ArrowLeft":
				if(this.infobar.isInfobar_on_screen() == true) {
					if(this.infobar.getInfobar_status().equalsIgnoreCase(Constants.TWO_LINER_INFOBAR)) {
						processAnimation(Constants.FRONT, print_writers, "Shrink$InOut", "START");
						this.infobar.setInfobar_status(Constants.FORCED + Constants.SHRUNK_INFOBAR);
					}
				}
				break;
			case "ArrowRight":
				if(this.infobar.isInfobar_on_screen() == true) {
					processAnimation(Constants.FRONT, print_writers, "Shrink$InOut", "CONTINUE");
					this.infobar.setInfobar_status(Constants.TWO_LINER_INFOBAR);
				}
				break;
				
			case Constants.SHRUNK_INFOBAR:
				if(this.infobar.isInfobar_on_screen() == true) {
					if(this.infobar.getInfobar_status().equalsIgnoreCase(Constants.TWO_LINER_INFOBAR)) {
						processAnimation(Constants.FRONT, print_writers, "Shrink$InOut", "START");
						this.infobar.setInfobar_status(Constants.SHRUNK_INFOBAR);
					} else if(this.infobar.getInfobar_status().equalsIgnoreCase(Constants.SHRUNK_INFOBAR)) {
						processAnimation(Constants.FRONT, print_writers, "Shrink$InOut", "CONTINUE");
						this.infobar.setInfobar_status(Constants.TWO_LINER_INFOBAR);
					}
				}
				break;
				
//---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
			case "i":
				if(this.infobar.isInfobar_on_screen() == true && !this.infobar.isInfobar_pushed()) {
					if(infobar.isFreeHit_on_screen() == false) {
						processAnimation(Constants.FRONT, print_writers, "FreeHit_Animation", "START");
						infobar.setFreeHit_on_screen(true);
					}else if(infobar.isFreeHit_on_screen() == true) {
						processAnimation(Constants.FRONT, print_writers, "FreeHit_Animation", "CONTINUE");
						infobar.setFreeHit_on_screen(false);
					}
				}
				break;
			case "8":
				TeamName = caption.this_infobarGfx.inning.getBowling_team().getTeamBadge();
				Data = "ON HAT-TRICK";
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$EventAnimations$BaseAll$img_Base2"
						+ "*TEXTURE*IMAGE SET " + Constants.TRI_SERIES_BASE2 + TeamName + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$EventAnimations$BaseAll$img_Base1"
						+ "*TEXTURE*IMAGE SET " + Constants.TRI_SERIES_BASE1 + TeamName + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$EventAnimations$DisruptiveData$img_Bae1"
						+ "*TEXTURE*IMAGE SET " + Constants.TRI_SERIES_BASE1 + TeamName + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$EventAnimations$DisruptiveData$img_Text1"
						+ "*TEXTURE*IMAGE SET " + Constants.TRI_SERIES_TEXT1 + TeamName + "\0", print_writers);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$EventAnimations$DisruptiveData$img_Bae1"
						+ "$txt_Text*GEOM*TEXT SET " + Data + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$EventAnimations$DisruptiveData$img_Text1"
						+ "$txt_Text*GEOM*TEXT SET " + Data + "\0", print_writers);
				
				if(this.infobar.isInfobar_on_screen() == true && !this.infobar.isInfobar_pushed()) {
					processAnimation(Constants.FRONT, print_writers, "Event_Animation", "START");
				}
				break;
			case "s": case "f": case "w": case "0": case "9": case ".":
				switch(whatToProcess.split(",")[0]) {
				case "s": case "f":
					TeamName = caption.this_infobarGfx.inning.getBatting_team().getTeamBadge();
					Data = (whatToProcess.split(",")[0].equalsIgnoreCase("s") ? "SIX" : "FOUR");
					break;
				case "w": case "0":
					TeamName = caption.this_infobarGfx.inning.getBowling_team().getTeamBadge();
					Data = (whatToProcess.split(",")[0].equalsIgnoreCase("w") ? "WICKET" : "HAT TRICK");
					break;
				case "9": case ".":
					TeamName = "EVENT";
					Data = (whatToProcess.split(",")[0].equalsIgnoreCase("9") ? "NO BALL" : "WIDE");
					break;
				}
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$BigEvents$AllMask*TEXTURE*IMAGE SET " 
						+ Constants.TRI_SERIES_BASE1 + TeamName + "\0", print_writers);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$BigEvents$BaseAll$img_Base1*TEXTURE*IMAGE SET " 
						+ Constants.TRI_SERIES_BASE1 + TeamName + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$BigEvents$BaseAll$img_Base2*TEXTURE*IMAGE SET " 
						+ Constants.TRI_SERIES_BASE2 + TeamName + "\0", print_writers);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$BigEvents$TextAll$MainTextAll$img_Base2"
						+ "*TEXTURE*IMAGE SET " + Constants.TRI_SERIES_BASE2 + TeamName + "\0", print_writers);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$BigEvents$TextAll$MainTextAll$img_Base2"
						+ "$txt_Title*GEOM*TEXT SET " + Data + "\0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$BigEvents$TextAll$MainTextAll$img_Base2"
						+ "$txt_TitleOutline*GEOM*TEXT SET " + Data + "\0", print_writers);
				
				for(int j=1;j<=2;j++) {
					containerName= (j==1 ? "Top" : "Bottom");
					for (int i = 1; i <= 40; i++) {
						containerName2 = (i % 2 == 1 ? "$img_Base1" : "$img_Text2");
						containerName3 = (i % 2 == 1 ? Constants.TRI_SERIES_BASE1 : Constants.TRI_SERIES_TEXT2);
						
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$BigEvents$TextAll$SmallTexts$" + containerName 
								+ "$" + i + containerName2 + "*TEXTURE*IMAGE SET " + containerName3 + TeamName + "\0", print_writers);
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$Normal$BigEvents$TextAll$SmallTexts$" + containerName
								+ "$" + i + containerName2 + "$txt_Title*GEOM*TEXT SET " + Data + "\0", print_writers);
					}
				}
				
				if(this.infobar.isInfobar_on_screen() == true && !this.infobar.isInfobar_pushed()) {
					processAnimation(Constants.FRONT, print_writers, "BigEvents", "START");
				}
				break;
//----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------			
			case "Control_F12":
				if(this.infobar.isInfobar_on_screen()) {
					
				}else {
					processAnimation(Constants.FRONT, print_writers, "anim_Infobar$InOut$Essencials", "START");
					processAnimation(Constants.FRONT, print_writers, "anim_Infobar$InOut$Ident", "START");
				}
				
				processAnimation(Constants.FRONT, print_writers, "Loop", "START");
				processAnimation(Constants.FRONT, print_writers, "EventLoop", "START");
				
				this.infobar.setInfobar_on_screen(true);
				caption.this_infobarGfx.infobar.setInfobar_on_screen(true);
				break;
			case "F12":
				processAnimation(Constants.FRONT, print_writers, "Score_Counter$Hundreths", "SHOW 0.0");
				processAnimation(Constants.FRONT, print_writers, "Score_Counter$Tenths", "SHOW 0.0");
				processAnimation(Constants.FRONT, print_writers, "Score_Counter$Units", "SHOW 0.0");
				processAnimation(Constants.FRONT, print_writers, "Score_Counter$Wickets", "SHOW 0.0");
				
				processAnimation(Constants.FRONT, print_writers, "Overs_Counter", "SHOW 0.0");
				processAnimation(Constants.FRONT, print_writers, "BatsmanCounter", "SHOW 0.0");
				processAnimation(Constants.FRONT, print_writers, "BowlerCounter", "SHOW 0.0");
				
				if(this.infobar.isInfobar_on_screen()) {
					processAnimation(Constants.FRONT, print_writers, "Ident_To_Normal", "START");
					TimeUnit.MILLISECONDS.sleep(1500);
					processAnimation(Constants.FRONT, print_writers, "anim_Infobar$InOut$Ident", "SHOW 0.0");
					processAnimation(Constants.FRONT, print_writers, "anim_Infobar$InOut$Normal", "SHOW 1.800");
				}else {
					processAnimation(Constants.FRONT, print_writers, "anim_Infobar$InOut$Essencials", "START");
					processAnimation(Constants.FRONT, print_writers, "anim_Infobar$InOut$Normal", "START");
				}
				processAnimation(Constants.FRONT, print_writers, "BatsmanStrike", "START");
				
				processAnimation(Constants.FRONT, print_writers, "Loop", "START");
				processAnimation(Constants.FRONT, print_writers, "EventLoop", "START");

				this.infobar.setInfobar_on_screen(true);
				caption.this_infobarGfx.infobar.setInfobar_on_screen(true);
				this.infobar.setInfobar_pushed(false);
				this.infobar.setInfobar_status(Constants.TWO_LINER_INFOBAR);
				break;
			
			case "z": case "x": case "c": case "v": case "Control_z": case "Control_x": case "Control_Shift_Z": case "Control_Shift_Y":
				if(this.watermarkOnScreen.equalsIgnoreCase("WATERMARK")) {
					processAnimation(Constants.FRONT, print_writers, "Watermark", "CONTINUE");
				}
				AnimateIn("ArrowDown,", print_writers, config); // Push infobar
				TimeUnit.MILLISECONDS.sleep(500);
				processAnimation(Constants.BACK, print_writers, "Loop", "START");
				
				if(audioenabled.equalsIgnoreCase("TRUE")) {
					processAnimation(Constants.BACK, print_writers, "Audio", "START");
				}
				
				processAnimation(Constants.BACK, print_writers, "Anim_FullFrames$In_Out$Essentials", "START");
				processAnimation(Constants.BACK, print_writers, "Anim_FullFrames$In_Out$Big_Logo", "START");
				processAnimation(Constants.BACK, print_writers, "Anim_FullFrames$In_Out$TopTitle", "START");
				processAnimation(Constants.BACK, print_writers, "Anim_FullFrames$In_Out$SideTeamColour", "START");
				processAnimation(Constants.BACK, print_writers, "Anim_FullFrames$In_Out$Title", "START");
				processAnimation(Constants.BACK, print_writers, "Anim_FullFrames$In_Out$Base", "START");
				processAnimation(Constants.BACK, print_writers, "Anim_FullFrames$In_Out$Main$LeaderBoard", "START");
				this.whichGraphicOnScreen = whatToProcess;
				break;	
				
			case "m": case "Control_m":
				if(this.watermarkOnScreen.equalsIgnoreCase("WATERMARK")) {
					processAnimation(Constants.FRONT, print_writers, "Watermark", "CONTINUE");
				}
				AnimateIn("ArrowDown,", print_writers, config); // Push infobar
				TimeUnit.MILLISECONDS.sleep(500);
				
				if(audioenabled.equalsIgnoreCase("TRUE")) {
					processAnimation(Constants.BACK, print_writers, "Audio", "START");
				}
				
				processAnimation(Constants.BACK, print_writers, "anim_Ident", "START");
				processAnimation(Constants.BACK, print_writers, "Loop", "START");
				
				this.whichGraphicOnScreen = whatToProcess;
				break;
			case "Control_d": case "Control_e": case "Shift_P": case "Shift_Q":
				if(this.watermarkOnScreen.equalsIgnoreCase("WATERMARK")) {
					processAnimation(Constants.FRONT, print_writers, "Watermark", "CONTINUE");
				}
				AnimateIn("ArrowDown,", print_writers, config); // Push infobar
				TimeUnit.MILLISECONDS.sleep(500);
				
				if(audioenabled.equalsIgnoreCase("TRUE")) {
					processAnimation(Constants.BACK, print_writers, "Audio", "START");
				}
				
				processAnimation(Constants.BACK, print_writers, "anim_Playerprofile", "START");
				processAnimation(Constants.BACK, print_writers, "Loop", "START");
				
				
				this.whichGraphicOnScreen = whatToProcess;
				break;
			case "Control_Shift_*":
				if(this.infobar.isInfobar_on_screen() == true) {
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Sponsor$Overall_Position_Y*"
						+ "TRANSFORMATION*POSITION*Y SET 0.0 \0",print_writers);
				}else {
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Sponsor$Overall_Position_Y*"
							+ "TRANSFORMATION*POSITION*Y SET -44.0 \0",print_writers);
				}
				processAnimation(Constants.FRONT, print_writers, "anim_Sponsor", "START");
				
				this.whichGraphicOnScreen = whatToProcess;
				break;	
			case "Control_Shift_F7":
				caption.this_fullFramesGfx.this_ALL_FF.populateTeamLineUpFooter(print_writers, 1, 
					whatToProcess.split(",")[0], config, "SHOW-TOSS", matchAllData);
				if(this.watermarkOnScreen.equalsIgnoreCase("WATERMARK")) {
					processAnimation(Constants.FRONT, print_writers, "Watermark", "CONTINUE");
				}
				AnimateIn("ArrowDown,", print_writers, config); // Push infobar
				TimeUnit.MILLISECONDS.sleep(500);
				processAnimation(Constants.BACK, print_writers, "Anim_LineUp_Image$In_Out", "START");
				lineUpCount = 0;
				this.whichGraphicOnScreen = whatToProcess;
				break;
			case "Control_Alt_F1":
				if(this.watermarkOnScreen.equalsIgnoreCase("WATERMARK")) {
					processAnimation(Constants.FRONT, print_writers, "Watermark", "CONTINUE");
				}
				AnimateIn("ArrowDown,", print_writers, config); // Push infobar
				TimeUnit.MILLISECONDS.sleep(500);
				
				processAnimation(Constants.BACK, print_writers, "Loop", "START");
				
				if(audioenabled.equalsIgnoreCase("TRUE")) {
					processAnimation(Constants.BACK, print_writers, "Audio", "START");
				}
				
				processAnimation(Constants.BACK, print_writers, "Anim_FullFrames$In_Out$Essentials", "START");
				processAnimation(Constants.BACK, print_writers, "Anim_FullFrames$In_Out$Big_Logo", "START");
				processAnimation(Constants.BACK, print_writers, "Anim_FullFrames$In_Out$TopTitle", "START");
				processAnimation(Constants.BACK, print_writers, "Anim_FullFrames$In_Out$SideTeamColour", "START");
				processAnimation(Constants.BACK, print_writers, "Anim_FullFrames$In_Out$Title", "START");
				processAnimation(Constants.BACK, print_writers, "Anim_FullFrames$In_Out$SubTitle", "START");
				processAnimation(Constants.BACK, print_writers, "Anim_FullFrames$In_Out$Base", "START");
				processAnimation(Constants.BACK, print_writers, "Anim_FullFrames$In_Out$Footer", "START");
				
				processAnimation(Constants.BACK, print_writers, "Anim_FullFrames$In_Out$Main$Batting_Card$BatterBase", "START");
				processAnimation(Constants.BACK, print_writers, "Anim_FullFrames$In_Out$Main$Batting_Card$Batter", "START");
				processAnimation(Constants.BACK, print_writers, "Anim_FullFrames$In_Out$Main$Batting_Card$Bowler", "START");
				
				this.whichGraphicOnScreen = whatToProcess;
				
				break;
			case "Alt_Shift_F1":
				if(this.watermarkOnScreen.equalsIgnoreCase("WATERMARK")) {
					processAnimation(Constants.FRONT, print_writers, "Watermark", "CONTINUE");
				}
				AnimateIn("ArrowDown,", print_writers, config); // Push infobar
				TimeUnit.MILLISECONDS.sleep(500);
				
				processAnimation(Constants.BACK, print_writers, "Loop", "START");
				
				if(audioenabled.equalsIgnoreCase("TRUE")) {
					processAnimation(Constants.BACK, print_writers, "Audio", "START");
				}
				
				processAnimation(Constants.BACK, print_writers, "Anim_FullFrames$In_Out$Essentials", "START");
				processAnimation(Constants.BACK, print_writers, "Anim_FullFrames$In_Out$Big_Logo", "START");
				processAnimation(Constants.BACK, print_writers, "Anim_FullFrames$In_Out$TopTitle", "START");
				processAnimation(Constants.BACK, print_writers, "Anim_FullFrames$In_Out$SideTeamColour", "START");
				processAnimation(Constants.BACK, print_writers, "Anim_FullFrames$In_Out$Title", "START");
				processAnimation(Constants.BACK, print_writers, "Anim_FullFrames$In_Out$SubTitle", "START");
				processAnimation(Constants.BACK, print_writers, "Anim_FullFrames$In_Out$Logo", "START");
				processAnimation(Constants.BACK, print_writers, "Anim_FullFrames$In_Out$Base", "START");
				processAnimation(Constants.BACK, print_writers, "Anim_FullFrames$In_Out$Footer", "START");
				
				processAnimation(Constants.BACK, print_writers, "Anim_FullFrames$In_Out$Main$Batting_Card$BatterBase", "START");
				processAnimation(Constants.BACK, print_writers, "Anim_FullFrames$In_Out$Main$Batting_Card$Batter", "START");
				processAnimation(Constants.BACK, print_writers, "Anim_FullFrames$In_Out$Main$Batting_Card$Contribution", "START");
				
				this.whichGraphicOnScreen = whatToProcess;
				
				break;
				
			case "F1": case "F2": case "Control_F7": case "Control_F11": case "F4": case "Shift_K": case "Control_F10": case "Shift_F10":
			case "Control_Shift_F1": case "Control_Shift_F2": case "Alt_F9": case "Alt_F11": case "Control_F1": case "Shift_F11":
			case "Control_p": case "Control_Shift_F4": case "Control_Shift_F5":
				
				if(this.watermarkOnScreen.equalsIgnoreCase("WATERMARK")) {
					processAnimation(Constants.FRONT, print_writers, "Watermark", "CONTINUE");
				}
				
				AnimateIn("ArrowDown,", print_writers, config); // Push infobar
				TimeUnit.MILLISECONDS.sleep(500);
				
				processAnimation(Constants.BACK, print_writers, "Loop", "START");
				
				if(audioenabled.equalsIgnoreCase("TRUE")) {
					processAnimation(Constants.BACK, print_writers, "Audio", "START");
				}
				
				processAnimation(Constants.BACK, print_writers, "Anim_FullFrames$In_Out$Essentials", "START");
				processAnimation(Constants.BACK, print_writers, "Anim_FullFrames$In_Out$Big_Logo", "START");
				processAnimation(Constants.BACK, print_writers, "Anim_FullFrames$In_Out$TopTitle", "START");
				processAnimation(Constants.BACK, print_writers, "Anim_FullFrames$In_Out$SideTeamColour", "START");
				processAnimation(Constants.BACK, print_writers, "Anim_FullFrames$In_Out$Title", "START");
				processAnimation(Constants.BACK, print_writers, "Anim_FullFrames$In_Out$Base", "START");
				
				if(!whatToProcess.split(",")[0].equalsIgnoreCase("Shift_K") && !whatToProcess.split(",")[0].equalsIgnoreCase("Alt_F11")) {
					processAnimation(Constants.BACK, print_writers, "Anim_FullFrames$In_Out$Footer", "START");
				}
				
				if(!whatToProcess.split(",")[0].equalsIgnoreCase("Control_Shift_F1") && !whatToProcess.split(",")[0].equalsIgnoreCase("Control_Shift_F2") && 
						!whatToProcess.split(",")[0].equalsIgnoreCase("Control_p") && !whatToProcess.split(",")[0].equalsIgnoreCase("Control_Shift_F4") && 
						!whatToProcess.split(",")[0].equalsIgnoreCase("Control_Shift_F5")) {
					
					processAnimation(Constants.BACK, print_writers, "Anim_FullFrames$In_Out$SubTitle", "START");
				}
				
				if(!whatToProcess.split(",")[0].equalsIgnoreCase("Control_Shift_F1") && !whatToProcess.split(",")[0].equalsIgnoreCase("Control_Shift_F2") && 
						!whatToProcess.split(",")[0].equalsIgnoreCase("Control_F7") && !whatToProcess.split(",")[0].equalsIgnoreCase("Alt_F11") && 
						!whatToProcess.split(",")[0].equalsIgnoreCase("Control_F11") && !whatToProcess.split(",")[0].equalsIgnoreCase("Shift_F11") && 
						!whatToProcess.split(",")[0].equalsIgnoreCase("Control_p") && !whatToProcess.split(",")[0].equalsIgnoreCase("Shift_F10") &&
						!whatToProcess.split(",")[0].equalsIgnoreCase("Control_Shift_F4") && !whatToProcess.split(",")[0].equalsIgnoreCase("Control_Shift_F5")) {
					processAnimation(Constants.BACK, print_writers, "Anim_FullFrames$In_Out$Logo", "START");
				}
				
				if(whatToProcess.split(",")[0].equalsIgnoreCase("Control_Shift_F1") || whatToProcess.split(",")[0].equalsIgnoreCase("Control_Shift_F2")) {
					processAnimation(Constants.BACK, print_writers, "MoveForExtra", "START");
				}
				
				switch (whatToProcess.split(",")[0]) {
				case "F1":
//					processAnimation(Constants.BACK, print_writers, "Anim_FullFrames$In_Out$Main$Batting_Card$BatterBase", "START");
//					processAnimation(Constants.BACK, print_writers, "Anim_FullFrames$In_Out$Main$Batting_Card$DismissalBase", "START");
					processAnimation(Constants.BACK, print_writers, "Anim_FullFrames$In_Out$Main$Batting_Card$Batter", "START");
					processAnimation(Constants.BACK, print_writers, "Anim_FullFrames$In_Out$Main$Batting_Card$Dismissal", "START");
					break;
				case "Control_Shift_F4":
					processAnimation(Constants.BACK, print_writers, "MoveForExtra", "START");
					processAnimation(Constants.BACK, print_writers, "Anim_FullFrames$In_Out$Main$Batting_CardExtra", "START");
					processAnimation(Constants.BACK, print_writers, "PlayerHighlight$BattingCardExtra$Side1$" + 
							caption.this_fullFramesGfx.this_ALL_FF.fullframes.getHighlight() + "", "START");
					
					caption.this_fullFramesGfx.this_ALL_FF.fullframes.setPreHighlight(caption.this_fullFramesGfx.this_ALL_FF.fullframes.getHighlight());
					break;
				case "Control_Shift_F5":
					processAnimation(Constants.BACK, print_writers, "MoveForExtra", "START");
					processAnimation(Constants.BACK, print_writers, "Anim_FullFrames$In_Out$Main$Bowling_CardExtra", "START");
					processAnimation(Constants.BACK, print_writers, "PlayerHighlight$BowlingCardExtra$Side1$" + 
							caption.this_fullFramesGfx.this_ALL_FF.fullframes.getHighlight() + "", "START");
					
					caption.this_fullFramesGfx.this_ALL_FF.fullframes.setPreHighlight(caption.this_fullFramesGfx.this_ALL_FF.fullframes.getHighlight());
					break;
				case "Control_Shift_F1":
					processAnimation(Constants.BACK, print_writers, "Anim_FullFrames$In_Out$ExtraData$InOut", "SHOW 1.8");
					processAnimation(Constants.BACK, print_writers, "Anim_FullFrames$In_Out$Main$Batting_Card$BatterBase", "START");
					processAnimation(Constants.BACK, print_writers, "Anim_FullFrames$In_Out$Main$Batting_Card$Batter", "START");
					
					processAnimation(Constants.BACK, print_writers, "Anim_FullFrames$In_Out$ExtraData$InOut", "CONTINUE");
					
					if(caption.this_fullFramesGfx.this_ALL_FF.fullframes.getWhichhighlight().equalsIgnoreCase("PERFORMER")) {
						processAnimation("", print_writers, "PlayerHighlight$BattingCard$Side1$" + 
								caption.this_fullFramesGfx.this_ALL_FF.fullframes.getHighlight() + "", "START");
					}else if(caption.this_fullFramesGfx.this_ALL_FF.fullframes.getWhichhighlight().equalsIgnoreCase("PARTNERSHIP")) {
						
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side1$BattingCard$BatterGrp$"
								+ caption.this_fullFramesGfx.this_ALL_FF.fullframes.getHighlight() + "$select_Colour*FUNCTION*Omo*vis_con SET 1\0", print_writers);
						
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side1$BattingCard$BatterGrp$"
								+ caption.this_fullFramesGfx.this_ALL_FF.fullframes.getSecondHighlight() + "$select_Colour*FUNCTION*Omo*vis_con SET 2\0", print_writers);
						
						
						processAnimation("", print_writers, "PlayerHighlight$BattingCard$Side1$" + 
								caption.this_fullFramesGfx.this_ALL_FF.fullframes.getHighlight() + "", "START");
						processAnimation("", print_writers, "PlayerHighlight$BattingCard$Side1$" + 
								caption.this_fullFramesGfx.this_ALL_FF.fullframes.getSecondHighlight() + "", "START");
					}
					
					
					caption.this_fullFramesGfx.this_ALL_FF.fullframes.setPreHighlight(caption.this_fullFramesGfx.this_ALL_FF.fullframes.getHighlight());
					break;	
				case "F2":
					processAnimation(Constants.BACK, print_writers, "Anim_FullFrames$In_Out$Main$Bowling_Card$BowlerBase", "START");
					processAnimation(Constants.BACK, print_writers, "Anim_FullFrames$In_Out$Main$Bowling_Card$DataBase", "START");
					processAnimation(Constants.BACK, print_writers, "Anim_FullFrames$In_Out$Main$Bowling_Card$Bowler", "START");
					processAnimation(Constants.BACK, print_writers, "Anim_FullFrames$In_Out$Main$Bowling_Card$BowlerDetails", "START");
					break;
				case "Control_Shift_F2":
					processAnimation(Constants.BACK, print_writers, "Anim_FullFrames$In_Out$ExtraData$InOut", "SHOW 1.8");
					
					processAnimation(Constants.BACK, print_writers, "Anim_FullFrames$In_Out$Main$Bowling_Card$BowlerBase", "START");
					processAnimation(Constants.BACK, print_writers, "Anim_FullFrames$In_Out$Main$Bowling_Card$Bowler", "START");
					
					processAnimation(Constants.BACK, print_writers, "Anim_FullFrames$In_Out$ExtraData$InOut", "CONTINUE");
					System.out.println(caption.this_fullFramesGfx.this_ALL_FF.fullframes.getHighlight());
					processAnimation(Constants.BACK, print_writers, "PlayerHighlight$BowlingCard$Side1$" + 
							caption.this_fullFramesGfx.this_ALL_FF.fullframes.getHighlight() + "", "START");
					
					caption.this_fullFramesGfx.this_ALL_FF.fullframes.setPreHighlight(caption.this_fullFramesGfx.this_ALL_FF.fullframes.getHighlight());
					break;	
				case "Control_F7":
					processAnimation(Constants.BACK, print_writers, "Anim_FullFrames$In_Out$Main$Teams", "START");
					break;
				case "Control_F11": case "Shift_F11":
					processAnimation(Constants.BACK, print_writers, "Anim_FullFrames$In_Out$Main$Summary", "START");
					break;
				case "Control_p":
					processAnimation(Constants.BACK, print_writers, "Anim_FullFrames$In_Out$Main$Standings", "START");
					break;
				case "F4":
					processAnimation(Constants.BACK, print_writers, "Anim_FullFrames$In_Out$Main$PartnershipList", "START");
					break;
				case "Shift_K":
					processAnimation(Constants.BACK, print_writers, "Anim_FullFrames$In_Out$Main$Partnership", "START");
					break;
				case "Control_F10":
					processAnimation(Constants.BACK, print_writers, "Anim_FullFrames$In_Out$Main$Manhattan", "START");
					break;
				case "Shift_F10":
					processAnimation(Constants.BACK, print_writers, "Anim_FullFrames$In_Out$Main$Worm", "START");
					break;
				case "Alt_F9":
					processAnimation(Constants.BACK, print_writers, "Anim_FullFrames$In_Out$Main$TeamSingle", "START");
					break;
				case "Alt_F11":
					processAnimation(Constants.BACK, print_writers, "Anim_FullFrames$In_Out$Main$ManhattanComparison", "START");
					break;
				case "Control_F1":
					processAnimation(Constants.BACK, print_writers, "Anim_FullFrames$In_Out$Main$ImageBattingCard", "START");
					break;
				}
				processAnimation(Constants.BACK, print_writers, "Loop", "START");
				
				this.whichGraphicOnScreen = whatToProcess;
				caption.captionWhichGfx = whatToProcess.split(",")[0];
				caption.this_fullFramesGfx.whichGFX = whatToProcess.split(",")[0];
				break;
			case "Shift_D":
				if(this.watermarkOnScreen.equalsIgnoreCase("WATERMARK")) {
					processAnimation(Constants.FRONT, print_writers, "Watermark", "CONTINUE");
				}
				AnimateIn("ArrowDown,", print_writers, config); // Push infobar
				TimeUnit.MILLISECONDS.sleep(500);
				
				processAnimation(Constants.BACK, print_writers, "Loop", "START");
				
				if(audioenabled.equalsIgnoreCase("TRUE")) {
					processAnimation(Constants.BACK, print_writers, "Audio", "START");
				}
				processAnimation(Constants.BACK, print_writers, "anim_Target$In_Out", "START");
				
				this.whichGraphicOnScreen = whatToProcess;
				break;
			case "Shift_F1": case "Shift_F2": case "Alt_F7":
				processAnimation(Constants.FRONT, print_writers, "Anim_Mini$In_Out", "START");
				this.whichGraphicOnScreen = whatToProcess;
				break;	
			case "Control_Shift_F10":
				if(this.infobar.isInfobar_on_screen() == true) {
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT_Manhattan$Overall_Position_Y*"
						+ "TRANSFORMATION*POSITION*Y SET 15.0 \0",print_writers);
				}else {
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT_Manhattan$Overall_Position_Y*"
							+ "TRANSFORMATION*POSITION*Y SET 0.0 \0",print_writers);
				}
				
				if(infobar.getInfobar_status() != null) {
					if(!infobar.getInfobar_status().equalsIgnoreCase(Constants.FORCED+Constants.SHRUNK_INFOBAR)) {
						AnimateIn("ArrowLeft" + ",", print_writers, config); // Shrink infobar
						TimeUnit.MILLISECONDS.sleep(1000);
						infobar.setInfobar_status(Constants.SHRUNK_INFOBAR);
					}
				}
				processAnimation(Constants.FRONT, print_writers, "LT_Manhattan$InOut", "START");
				this.whichGraphicOnScreen = whatToProcess;
				break;
			
			case "Alt_F8": case "F8": case "F10":
				if(this.infobar.isInfobar_on_screen() == true || this.specialBugOnScreen.equalsIgnoreCase(CricketUtil.TOSS)) {
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_NameSuper$Overall_Position_Y*"
						+ "TRANSFORMATION*POSITION*Y SET -22.0\0",print_writers);
				}else {
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_NameSuper$Overall_Position_Y*"
							+ "TRANSFORMATION*POSITION*Y SET -44.0\0",print_writers);
				}
				
				if(infobar.getInfobar_status() != null) {
					if(!infobar.getInfobar_status().equalsIgnoreCase(Constants.FORCED+Constants.SHRUNK_INFOBAR)) {
						AnimateIn("ArrowLeft" + ",", print_writers, config); // Shrink infobar
						TimeUnit.MILLISECONDS.sleep(1000);
						infobar.setInfobar_status(Constants.SHRUNK_INFOBAR);
					}
				}
				processAnimation(Constants.FRONT, print_writers, "anim_Namesuper$InOut", "START");
				this.whichGraphicOnScreen = whatToProcess;
				break;
				
			case "Control_F5": case "Control_F9": case "F9": case "Shift_E": case "d": case "e": 
			
				if(this.infobar.isInfobar_on_screen() == true) {
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$Overall_Position_Y*"
						+ "TRANSFORMATION*POSITION*Y SET 15.0 \0",print_writers);
				}else {
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$Overall_Position_Y*"
							+ "TRANSFORMATION*POSITION*Y SET 0.0 \0",print_writers);
				}
				if(infobar.getInfobar_status() != null) {
					if(!infobar.getInfobar_status().equalsIgnoreCase(Constants.FORCED+Constants.SHRUNK_INFOBAR)) {
						AnimateIn("ArrowLeft" + ",", print_writers, config); // Shrink infobar
						TimeUnit.MILLISECONDS.sleep(1000);
						infobar.setInfobar_status(Constants.SHRUNK_INFOBAR);
					}
				}
				processAnimation(Constants.FRONT, print_writers, "anim_Lower_Third$InOut$Essentials", "START");
				processAnimation(Constants.FRONT, print_writers, "anim_Lower_Third$InOut$Logo", "START");
				processAnimation(Constants.FRONT, print_writers, "anim_LtChange$HeaderDynamic", "SHOW 1");
				this.whichGraphicOnScreen = whatToProcess;
				break;
				
			case "F7": case "F11": case "Control_s": case "Control_f": case "F5": case "F6": case "Control_F6": case "Shift_F6":
			case "u": case "Shift_F3": case "Shift_F5": case "Shift_F9": case "Alt_F12": case "Control_h": case "Control_a":
			case "Alt_Shift_F3": case "Control_Shift_Q": case "Alt_F1": case "Alt_F2": case "Control_Shift_X": case "Control_Shift_K": 
			case "Control_c":
				
				if(this.infobar.isInfobar_on_screen() == true) {
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$Overall_Position_Y*"
						+ "TRANSFORMATION*POSITION*Y SET 15.0 \0",print_writers);
				}else {
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$Overall_Position_Y*"
							+ "TRANSFORMATION*POSITION*Y SET 0.0 \0",print_writers);
				}
				
				if(infobar.getInfobar_status() != null) {
					if(!infobar.getInfobar_status().equalsIgnoreCase(Constants.FORCED+Constants.SHRUNK_INFOBAR)) {
						AnimateIn("ArrowLeft" + ",", print_writers, config); // Shrink infobar
						TimeUnit.MILLISECONDS.sleep(1000);
						infobar.setInfobar_status(Constants.SHRUNK_INFOBAR);
					}
				}
				
				processAnimation(Constants.FRONT, print_writers, "anim_Lower_Third", "START");
				processAnimation(Constants.FRONT, print_writers, "anim_LtChange$HeaderDynamic", "CONTINUE REVERSE");
				this.whichGraphicOnScreen = whatToProcess;
				break;
			case "Alt_p":
				processAnimation(Constants.FRONT, print_writers, "anim__Bug", "START");
//				processAnimation(Constants.FRONT, print_writers, "anim__TossBug", "START");
				this.specialBugOnScreen = CricketUtil.TOSS;
				break;
			case "Alt_g":
				if(this.watermarkOnScreen.equalsIgnoreCase("WATERMARK")) {
					processAnimation(Constants.FRONT, print_writers, "Watermark", "CONTINUE");
					this.watermarkOnScreen = "";
				}else {
					processAnimation(Constants.FRONT, print_writers, "Watermark", "START");
					this.watermarkOnScreen = "WATERMARK";
				}
				break;	
			case "Shift_O": case "Control_k": case "k": case "g": case "y": case "Control_y": case "h": case "Shift_F4": case "Shift_F":case "Alt_b":
			case "Control_Shift_R": case "Control_Shift_F3":  case "Shift_C": case "Control_Shift_J":
				AnimateIn("ArrowDown,", print_writers, config); // Push infobar
				TimeUnit.MILLISECONDS.sleep(500);
				processAnimation(Constants.FRONT, print_writers, "anim__Bug", "START");
				this.whichGraphicOnScreen = whatToProcess;
				break;
			case "Control_Shift_U": case "Control_Shift_V":
				processAnimation(Constants.FRONT, print_writers, "PopUps$InOut", "START");
				this.whichGraphicOnScreen = whatToProcess;
				break;
			case "Control_F3":
				if(this.infobar.isInfobar_on_screen() == true) {
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Lt_Comparison$Overall_Position_Y*"
						+ "TRANSFORMATION*POSITION*Y SET 15.0 \0",print_writers);
				}else {
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Lt_Comparison$Overall_Position_Y*"
							+ "TRANSFORMATION*POSITION*Y SET 0.0 \0",print_writers);
				}
				
				if(infobar.getInfobar_status() != null) {
					if(!infobar.getInfobar_status().equalsIgnoreCase(Constants.FORCED+Constants.SHRUNK_INFOBAR)) {
						AnimateIn("ArrowLeft" + ",", print_writers, config); // Shrink infobar
						TimeUnit.MILLISECONDS.sleep(1000);
						infobar.setInfobar_status(Constants.SHRUNK_INFOBAR);
					}
				}
				processAnimation(Constants.FRONT, print_writers, "LT_Comparison$InOut", "START");
				this.whichGraphicOnScreen = whatToProcess;
				break;
			case "Control_Shift_B":
				if(this.infobar.isInfobar_on_screen() == true) {
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT_NextToBat$Overall_Position_Y*"
						+ "TRANSFORMATION*POSITION*Y SET 15.0 \0",print_writers);
				}else {
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT_NextToBat$Overall_Position_Y*"
							+ "TRANSFORMATION*POSITION*Y SET 0.0 \0",print_writers);
				}
				
				if(infobar.getInfobar_status() != null) {
					if(!infobar.getInfobar_status().equalsIgnoreCase(Constants.FORCED+Constants.SHRUNK_INFOBAR)) {
						AnimateIn("ArrowLeft" + ",", print_writers, config); // Shrink infobar
						TimeUnit.MILLISECONDS.sleep(1000);
						infobar.setInfobar_status(Constants.SHRUNK_INFOBAR);
					}
				}
				processAnimation(Constants.FRONT, print_writers, "LT_NextToBat$InOut", "START");
				this.whichGraphicOnScreen = whatToProcess;
				break;
			case "Control_6":
				if(this.infobar.isInfobar_on_screen() == true) {
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT_Weather$Overall_Position_Y*"
						+ "TRANSFORMATION*POSITION*Y SET 15.0 \0",print_writers);
				}else {
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT_Weather$Overall_Position_Y*"
							+ "TRANSFORMATION*POSITION*Y SET 0.0 \0",print_writers);
				}
				
				if(infobar.getInfobar_status() != null) {
					if(!infobar.getInfobar_status().equalsIgnoreCase(Constants.FORCED+Constants.SHRUNK_INFOBAR)) {
						AnimateIn("ArrowLeft" + ",", print_writers, config); // Shrink infobar
						TimeUnit.MILLISECONDS.sleep(1000);
						infobar.setInfobar_status(Constants.SHRUNK_INFOBAR);
					}
				}
				processAnimation(Constants.FRONT, print_writers, "LT_Weather$InOut", "START");
				this.whichGraphicOnScreen = whatToProcess;
				break;
			case "Control_Shift_O":
				if(this.infobar.isInfobar_on_screen() == true) {
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT_PlayingXI$Overall_Position_Y*"
						+ "TRANSFORMATION*POSITION*Y SET 15.0 \0",print_writers);
				}else {
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$LT_PlayingXI$Overall_Position_Y*"
							+ "TRANSFORMATION*POSITION*Y SET 0.0 \0",print_writers);
				}
				
				if(infobar.getInfobar_status() != null) {
					if(!infobar.getInfobar_status().equalsIgnoreCase(Constants.FORCED+Constants.SHRUNK_INFOBAR)) {
						AnimateIn("ArrowLeft" + ",", print_writers, config); // Shrink infobar
						TimeUnit.MILLISECONDS.sleep(1000);
						infobar.setInfobar_status(Constants.SHRUNK_INFOBAR);
					}
				}
				processAnimation(Constants.FRONT, print_writers, "LT_PlayingXI$InOut", "START");
				this.whichGraphicOnScreen = whatToProcess;
				break;	
			case "Control_Shift_M": case "Control_Shift_L":
				if(this.infobar.isInfobar_on_screen() == true) {
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Lt_MatchID$Overall_Position_Y*"
						+ "TRANSFORMATION*POSITION*Y SET 15.0 \0",print_writers);
				}else {
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Lt_MatchID$Overall_Position_Y*"
							+ "TRANSFORMATION*POSITION*Y SET 0.0 \0",print_writers);
				}
				
				if(infobar.getInfobar_status() != null) {
					if(!infobar.getInfobar_status().equalsIgnoreCase(Constants.FORCED+Constants.SHRUNK_INFOBAR)) {
						AnimateIn("ArrowLeft" + ",", print_writers, config); // Shrink infobar
						TimeUnit.MILLISECONDS.sleep(1000);
						infobar.setInfobar_status(Constants.SHRUNK_INFOBAR);
					}
				}
				processAnimation(Constants.FRONT, print_writers, "LT_MatchID$InOut", "START");
				this.whichGraphicOnScreen = whatToProcess;
				break;
			case "6": case "Control_4": case "5": case ";":
				processAnimation(Constants.FRONT, print_writers, "PopUps$InOut", "START");
				TimeUnit.MILLISECONDS.sleep(1700);
				this.whichGraphicOnScreen = whatToProcess;
				if(!caption.this_bugsAndMiniGfx.this_data_str.get(caption.this_bugsAndMiniGfx.this_data_str.size()-2).split(",")[0].
						equalsIgnoreCase(caption.this_bugsAndMiniGfx.this_data_str.get(caption.this_bugsAndMiniGfx.this_data_str.size()-1).split(",")[0])) {
					processAnimation(Constants.FRONT, print_writers, "PopUps$Change_Sixes$Hundreds", "START");
					processAnimation(Constants.FRONT, print_writers, "PopUps$Change_Sixes$Tens", "START");
					processAnimation(Constants.FRONT, print_writers, "PopUps$Change_Sixes$Units", "START");
				}
				else if(!caption.this_bugsAndMiniGfx.this_data_str.get(caption.this_bugsAndMiniGfx.this_data_str.size()-2).split(",")[1].
						equalsIgnoreCase(caption.this_bugsAndMiniGfx.this_data_str.get(caption.this_bugsAndMiniGfx.this_data_str.size()-1).split(",")[1])) {
					processAnimation(Constants.FRONT, print_writers, "PopUps$Change_Sixes$Tens", "START");
					processAnimation(Constants.FRONT, print_writers, "PopUps$Change_Sixes$Units", "START");
				}
				else if(!caption.this_bugsAndMiniGfx.this_data_str.get(caption.this_bugsAndMiniGfx.this_data_str.size()-2).split(",")[2].
						equalsIgnoreCase(caption.this_bugsAndMiniGfx.this_data_str.get(caption.this_bugsAndMiniGfx.this_data_str.size()-1).split(",")[2])) {
					processAnimation(Constants.FRONT, print_writers, "PopUps$Change_Sixes$Units", "START");
				}
				break;	
			}
			break;
			
		case Constants.BCCI:
			switch (whatToProcess.split(",")[0]) {
			case "ArrowUp":
				if(this.infobar.isInfobar_on_screen() == true && this.infobar.isInfobar_pushed()) {
					processAnimation(Constants.FRONT, print_writers, "Shrink$ShrinkForFF", "CONTINUE REVERSE");
					this.infobar.setInfobar_pushed(false);
				}
				break;
			case "ArrowDown":
				if(this.infobar.isInfobar_on_screen() == true && !this.infobar.isInfobar_pushed()) {
					processAnimation(Constants.FRONT, print_writers, "Shrink$ShrinkForFF", "START");
					this.infobar.setInfobar_pushed(true);
					TimeUnit.MILLISECONDS.sleep(600);
				}
				break;
			case "ArrowLeft":
				if(this.infobar.isInfobar_on_screen() == true) {
					if(this.infobar.getInfobar_status().equalsIgnoreCase(Constants.TWO_LINER_INFOBAR)) {
						processAnimation(Constants.FRONT, print_writers, "Shrink$Shrink", "START");
						this.infobar.setInfobar_status(Constants.FORCED + Constants.SHRUNK_INFOBAR);
					}
				}
				break;
			case "ArrowRight":
				if(this.infobar.isInfobar_on_screen() == true) {
					processAnimation(Constants.FRONT, print_writers, "Shrink$Shrink", "CONTINUE REVERSE");
					this.infobar.setInfobar_status(Constants.TWO_LINER_INFOBAR);
				}
				break;
				
			case Constants.SHRUNK_INFOBAR:
				if(this.infobar.isInfobar_on_screen() == true) {
					if(this.infobar.getInfobar_status().equalsIgnoreCase(Constants.TWO_LINER_INFOBAR)) {
						processAnimation(Constants.FRONT, print_writers, "Shrink$Shrink", "START");
						this.infobar.setInfobar_status(Constants.SHRUNK_INFOBAR);
					} else if(this.infobar.getInfobar_status().equalsIgnoreCase(Constants.SHRUNK_INFOBAR)) {
						processAnimation(Constants.FRONT, print_writers, "Shrink$Shrink", "CONTINUE REVERSE");
						this.infobar.setInfobar_status(Constants.TWO_LINER_INFOBAR);
					}
				}
				break;
				
			case "w": case "f": case "s": case "i": case "0": case "8":
				if(whatToProcess.split(",")[0].equalsIgnoreCase("w")) {
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$DisruptiveAnimation$select_Disruptive*FUNCTION*Omo*vis_con SET 3\0", print_writers);
				}else if(whatToProcess.split(",")[0].equalsIgnoreCase("f")) {
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$DisruptiveAnimation$select_Disruptive*FUNCTION*Omo*vis_con SET 1\0", print_writers);
				}else if(whatToProcess.split(",")[0].equalsIgnoreCase("s")) {
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$DisruptiveAnimation$select_Disruptive*FUNCTION*Omo*vis_con SET 2\0", print_writers);
				}else if(whatToProcess.split(",")[0].equalsIgnoreCase("i")) {
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Infobar$DisruptiveAnimation$select_Disruptive*FUNCTION*Omo*vis_con SET 0\0", print_writers);
				}
				
				if(this.infobar.isInfobar_on_screen() == true && !this.infobar.isInfobar_pushed()) {
					processAnimation(Constants.FRONT, print_writers, "Disruptive", "START");
				}
				break;
			
			case "Control_F12":
				if(this.infobar.isInfobar_on_screen()) {
					processAnimation(Constants.FRONT, print_writers, "anim_Infobar$In_Out$Ident", "SHOW 1.720");
					
					processAnimation(Constants.FRONT, print_writers, "anim_Infobar$In_Out$Normal", "CONTINUE");
					processAnimation(Constants.FRONT, print_writers, "anim_Infobar$In_Out$Ident", "CONTINUE");
					processAnimation(Constants.FRONT, print_writers, "anim_Change$Logo_Colour", "START");
					
					TimeUnit.MILLISECONDS.sleep(500);
					processAnimation(Constants.FRONT, print_writers, "anim_Infobar$In_Out$Normal", "SHOW 0.0");
				}else {
					processAnimation(Constants.FRONT, print_writers, "anim_Infobar$In_Out$Essentials", "START");
					processAnimation(Constants.FRONT, print_writers, "anim_Infobar$In_Out$Logo_Colour", "START");
					processAnimation(Constants.FRONT, print_writers, "anim_Infobar$In_Out$Ident", "START");
				}
				
				this.infobar.setInfobar_on_screen(true);
				caption.this_infobarGfx.infobar.setInfobar_on_screen(true);
				break;
			case "F12":
				if(this.infobar.isInfobar_on_screen()) {
					processAnimation(Constants.FRONT, print_writers, "anim_Infobar$In_Out$Normal", "SHOW 1.720");
					
					processAnimation(Constants.FRONT, print_writers, "anim_Infobar$In_Out$Ident", "CONTINUE");
					processAnimation(Constants.FRONT, print_writers, "anim_Infobar$In_Out$Normal", "CONTINUE");
					processAnimation(Constants.FRONT, print_writers, "anim_Change$Logo_Colour", "START");
					
					TimeUnit.MILLISECONDS.sleep(500);
					processAnimation(Constants.FRONT, print_writers, "anim_Infobar$In_Out$Ident", "SHOW 0.0");
					
				}else {
					processAnimation(Constants.FRONT, print_writers, "anim_Infobar$In_Out$Essentials", "START");
					processAnimation(Constants.FRONT, print_writers, "anim_Infobar$In_Out$Logo_Colour", "START");
					processAnimation(Constants.FRONT, print_writers, "anim_Infobar$In_Out$Normal", "START");
				}

				this.infobar.setInfobar_on_screen(true);
				caption.this_infobarGfx.infobar.setInfobar_on_screen(true);
				this.infobar.setInfobar_pushed(false);
				this.infobar.setInfobar_status(Constants.TWO_LINER_INFOBAR);
				break;
			
			case "m":
				AnimateIn("ArrowDown,", print_writers, config);
				processAnimation(Constants.BACK, print_writers, "anim_Ident", "START");
				break;
				
			case "F1": case "F2": case "F4": case "Control_F11": case "Control_Shift_F1": //Full framers
				AnimateIn("ArrowDown,", print_writers, config);
				processAnimation(Constants.BACK, print_writers, "anim_Fullframe$In_Out$Essentials", "START");
				processAnimation(Constants.BACK, print_writers, "anim_Fullframe$In_Out$RightVeil", "START");
				processAnimation(Constants.BACK, print_writers, "anim_Fullframe$In_Out$TeamLogo", "START");
				processAnimation(Constants.BACK, print_writers, "anim_Fullframe$In_Out$TeamName", "START");
				processAnimation(Constants.BACK, print_writers, "anim_Fullframe$In_Out$Header", "START");
				processAnimation(Constants.BACK, print_writers, "anim_Fullframe$In_Out$SubHeader", "START");
				processAnimation(Constants.BACK, print_writers, "anim_Fullframe$In_Out$Footer", "START");
				processAnimation(Constants.BACK, print_writers, "anim_Fullframe$In_Out$Sponsor", "START");
				switch (whatToProcess.split(",")[0]) {
				case "F1": case "Control_Shift_F1":
					processAnimation(Constants.BACK, print_writers, "anim_Fullframe$In_Out$Main$Normal_BattingCard", "START");
					if(whatToProcess.split(",")[0].equalsIgnoreCase("Control_Shift_F1")) {
						processAnimation(Constants.BACK, print_writers, "Bg_Shift", "START");
						TimeUnit.MILLISECONDS.sleep(1500);
						switch (whatToProcess.split(",")[3]) {
						case "performer":
							processAnimation(Constants.BACK, print_writers, "anim_ExtraData$In_Out$PlayerStat", "START");
							break;
						case "partnership":
							processAnimation(Constants.BACK, print_writers, "anim_ExtraData$In_Out$Partnership", "START");
							break;
						}
					}
					break;
				case "F2":
					processAnimation(Constants.BACK, print_writers, "anim_Fullframe$In_Out$Main$BowlingCard", "START");
					break;
				case "F4":
					processAnimation(Constants.BACK, print_writers, "anim_Fullframe$In_Out$Main$PartnershipList", "START");
					break;
				case "Control_F11":
					processAnimation(Constants.BACK, print_writers, "anim_Fullframe$In_Out$Main$Summary", "START");
					processAnimation(Constants.BACK, print_writers, "MoveForLogo", "SHOW 1.000");
					break;
				}
				
				processAnimation(Constants.BACK, print_writers, "Loop", "START");
				this.whichGraphicOnScreen = whatToProcess;
				break;
			}
		}
		CricketFunctions.deletePreview();
		return CricketUtil.YES;
	}	
	public String AnimateOut(String whatToProcess, List<PrintWriter> print_writers, Configuration config) throws InterruptedException, IOException
	{
		switch (config.getBroadcaster().toUpperCase()) {
		case Constants.AFG_SL_SERIES:
			switch (whatToProcess.split(",")[0]) {
			case "Control_F12": case "F12":
				processAnimation(Constants.FRONT, print_writers, "anim_Infobar$InOut$Essencials", "CONTINUE");
				
				switch (whatToProcess.split(",")[0]) {
				case "Control_F12":
					processAnimation(Constants.FRONT, print_writers, "anim_Infobar$InOut$Ident", "CONTINUE");
					break;
				case "F12":
					processAnimation(Constants.FRONT, print_writers, "anim_Infobar$InOut$Normal", "CONTINUE");
					break;
				}
				
				TimeUnit.MILLISECONDS.sleep(1800);
				processAnimation(Constants.FRONT, print_writers, "anim_Infobar", "SHOW 0.0");
				
				this.infobar.setInfobar_on_screen(false);
				caption.this_infobarGfx.infobar.setInfobar_on_screen(false);
				caption.this_infobarGfx.infobar.setPowerplay_on_screen(false);
				
				caption.this_infobarGfx.BatterTickerName = false;
				caption.this_infobarGfx.bowlerTickerName = false;
				
				caption.this_infobarGfx.currentOnStrike = "";
				caption.this_infobarGfx.previousOnStrike = "";
				
				caption.this_infobarGfx.infobar.setSection1("");
				caption.this_infobarGfx.infobar.setSection2("");
				caption.this_infobarGfx.infobar.setSection3("");
				caption.this_infobarGfx.infobar.setSection4("");
				caption.this_infobarGfx.infobar.setSection5("");
				caption.this_infobarGfx.infobar.setSectionAnalytics("");
				
				caption.this_infobarGfx.infobar.setLast_sectionAnalytics("");
				caption.this_infobarGfx.infobar.setLast_section1("");
				caption.this_infobarGfx.infobar.setLast_section2("");
				caption.this_infobarGfx.infobar.setLast_section3("");
				caption.this_infobarGfx.infobar.setLast_section4("");
				caption.this_infobarGfx.infobar.setLast_section5("");
				break;
			
			case "m": case "Control_m":
				processAnimation("", print_writers, "anim_Ident$In_Out", "CONTINUE");
				
				TimeUnit.MILLISECONDS.sleep(800);
				AnimateIn("ArrowUp,", print_writers, config); // Push infobar
				this.whichGraphicOnScreen = "";
				break;
			case "Shift_D":
				processAnimation("", print_writers, "anim_Target$In_Out", "CONTINUE");
				
				TimeUnit.MILLISECONDS.sleep(800);
				AnimateIn("ArrowUp,", print_writers, config); // Push infobar
				this.whichGraphicOnScreen = "";
				break;
			case "Control_d": case "Control_e": case "Shift_P": case "Shift_Q":
				processAnimation("", print_writers, "anim_Profile$In_Out", "CONTINUE");
				
				TimeUnit.MILLISECONDS.sleep(800);
				AnimateIn("ArrowUp,", print_writers, config); // Push infobar
				this.whichGraphicOnScreen = "";
				break;
			case "F1": case "F2": case "Control_F7": case "Shift_T": case "Control_Alt_F1": case "Alt_Shift_F1": case "F4": 
			case "Control_F11": case "Shift_F11": case "Shift_K": case "Shift_F10": case "Control_F10": case "Alt_F11":
			case "Control_F1": case "Control_Shift_F1":
				switch (whatToProcess.split(",")[0]) {
				case "F1":
					processAnimation("", print_writers, "anim_FullFrames$In_Out$Main$BattingCard", "CONTINUE");
					break;
				case "F4":
					processAnimation("", print_writers, "anim_FullFrames$In_Out$Main$PartnershipList", "CONTINUE");
					break;
				case "Shift_K":
					processAnimation("", print_writers, "anim_FullFrames$In_Out$Main$Partnership", "CONTINUE");
					break;
				case "Shift_F10":
					processAnimation("", print_writers, "anim_FullFrames$In_Out$Main$Worm", "CONTINUE");
					break;
				case "Control_F10":
					processAnimation("", print_writers, "anim_FullFrames$In_Out$Main$Manhattan", "CONTINUE");
					break;
				case "Alt_F11":
					processAnimation("", print_writers, "anim_FullFrames$In_Out$Main$ManhattanComparison", "CONTINUE");
					break;
				case "Control_F1":
					processAnimation("", print_writers, "anim_FullFrames$In_Out$Main$ImageBattingCard", "CONTINUE");
					break;
				case "Control_Alt_F1": case "Alt_Shift_F1": case "Control_Shift_F1":
					processAnimation("", print_writers, "anim_FullFrames$In_Out$Main$SplitCard", "CONTINUE");
					break;
				case "F2":
					processAnimation("", print_writers, "anim_FullFrames$In_Out$Main$BowlingCard", "CONTINUE");
					break;
				case "Control_F11": case "Shift_F11":
					processAnimation("", print_writers, "anim_FullFrames$In_Out$Main$Summary", "CONTINUE");
					break;
				case "Control_F7":
					processAnimation("", print_writers, "anim_FullFrames$In_Out$Main$Teams", "CONTINUE");
					break;
				case "Shift_T":
					processAnimation("", print_writers, "anim_FullFrames$In_Out$Main$Team", "CONTINUE");
					break;
				}
				
				processAnimation("", print_writers, "anim_FullFrames$In_Out$Essentials", "CONTINUE");
				processAnimation("", print_writers, "anim_FullFrames$In_Out$Header", "CONTINUE");
				processAnimation("", print_writers, "anim_FullFrames$In_Out$SubHeader", "CONTINUE");
				processAnimation("", print_writers, "anim_FullFrames$In_Out$Logo", "CONTINUE");
				processAnimation("", print_writers, "anim_FullFrames$In_Out$Icon", "CONTINUE");
				processAnimation("", print_writers, "anim_FullFrames$In_Out$VerticalText", "CONTINUE");
				processAnimation("", print_writers, "anim_FullFrames$In_Out$Footer", "CONTINUE");
				
				TimeUnit.MILLISECONDS.sleep(800);
				AnimateIn("ArrowUp,", print_writers, config); // Push infobar
				this.whichGraphicOnScreen = "";
				
				processAnimation("", print_writers, "anim_FullFrames", "SHOW 0.0");
				processAnimation("", print_writers, "Change_Fullframes", "SHOW 0.0");
				processAnimation("", print_writers, "Split_CardHighlight", "SHOW 0.0");
				break;
			}
			break;
		case Constants.BAN_AFG_SERIES: case Constants.ACC:
			switch (whatToProcess.split(",")[0]) {
			case "Control_F12": case "F12":
				switch (whatToProcess.split(",")[0]) {
				case "Control_F12":
					processAnimation(Constants.FRONT, print_writers, "Anim_Infobar$IdentOut", "START");
					processAnimation(Constants.FRONT, print_writers, "Anim_Infobar$Out", "START");
					break;
				case "F12":
					processAnimation(Constants.FRONT, print_writers, "Anim_Infobar$Out", "START");
					TimeUnit.MILLISECONDS.sleep(500);
					processAnimation(Constants.FRONT, print_writers, "Anim_Infobar$Section3$In_Out", "SHOW 0.0");
					processAnimation(Constants.FRONT, print_writers, "Anim_Infobar$Section4$In_Out", "SHOW 0.0");
					processAnimation(Constants.FRONT, print_writers, "Anim_Infobar$Section5$In_Out", "SHOW 0.0");
					break;
				}
				
				TimeUnit.MILLISECONDS.sleep(1800);
				processAnimation(Constants.FRONT, print_writers, "Reset", "START");
				
				this.infobar.setInfobar_on_screen(false);
				caption.this_infobarGfx.infobar.setInfobar_status("");
				caption.this_infobarGfx.infobar.setInfobar_on_screen(false);
				caption.this_infobarGfx.infobar.setPowerplay_on_screen(false);
				
				caption.this_infobarGfx.infobar.setSection1("");
				caption.this_infobarGfx.infobar.setSection2("");
				caption.this_infobarGfx.infobar.setSection3("");
				caption.this_infobarGfx.infobar.setSection4("");
				caption.this_infobarGfx.infobar.setSection5("");
				caption.this_infobarGfx.infobar.setSectionAnalytics("");
				
				caption.this_infobarGfx.infobar.setLast_sectionAnalytics("");
				caption.this_infobarGfx.infobar.setLast_section1("");
				caption.this_infobarGfx.infobar.setLast_section2("");
				caption.this_infobarGfx.infobar.setLast_section3("");
				caption.this_infobarGfx.infobar.setLast_section4("");
				caption.this_infobarGfx.infobar.setLast_section5("");
				break;
			case "Alt_Shift_Q":
				processAnimation(Constants.BACK, print_writers, "Plotter", "CONTINUE");
				this.whichGraphicOnScreen = "";
				caption.this_infobarGfx.infobar.setFieldPlotter_on_screen(false);
				break;	
			case "6": case "Control_4": case "5": case ";": case "Control_5": case "Control_7":
				processAnimation(Constants.FRONT, print_writers, "Counter$InOut", "CONTINUE");
				this.whichGraphicOnScreen = "";
				TimeUnit.MILLISECONDS.sleep(1000);
				processAnimation(Constants.FRONT, print_writers, "Counter$Change_Sixes", "SHOW 0.0");
				processAnimation(Constants.FRONT, print_writers, "Counter", "SHOW 0.0");
				break;	
			case "F5": case "F6": case "F8": case "F9": case "F10": case "Alt_F8": case "Control_F6": case "Control_F5": case "Control_F9": case "Shift_F6": 
			case "Shift_F3": case "Shift_F5": case "Shift_F9": case "Alt_F12": case "Control_Shift_L": case "Control_Shift_M": case "u": case "Control_a":	
			case "F7": case "F11": case "Control_s": case "Control_f": case "Control_Shift_O": case "Control_h": case "Control_F3": case "d": case "Control_Shift_B":
			case "Alt_Shift_F3": case "l": case "Alt_Shift_F4": case "Alt_d":
				
				processAnimation(Constants.FRONT, print_writers, "LT$Logo$In_Out$Out", "START");
				processAnimation(Constants.FRONT, print_writers, "LT$Base$In_Out$Out", "START");
				processAnimation(Constants.FRONT, print_writers, "LT$Data$In_Out$Out", "START");
				
				if(infobar.getInfobar_status() != null) {
					if(!infobar.getInfobar_status().equalsIgnoreCase(Constants.FORCED+Constants.SHRUNK_INFOBAR)) {
						TimeUnit.MILLISECONDS.sleep(1000);
						AnimateIn("ArrowRight" + ",", print_writers, config); // Restore infobar
						infobar.setInfobar_status(Constants.TWO_LINER_INFOBAR);
					}
				}
				
				TimeUnit.MILLISECONDS.sleep(800);
				
				processAnimation(Constants.FRONT, print_writers, "LT$Logo$In_Out$In", "SHOW 0.0");
				processAnimation(Constants.FRONT, print_writers, "LT$Base$In_Out$In", "SHOW 0.0");
				processAnimation(Constants.FRONT, print_writers, "LT$Data$In_Out$In", "SHOW 0.0");
				this.whichGraphicOnScreen = "";
				break;

			case "m": case "Control_m":
				processAnimation("", print_writers, "Wipe_BG_In_Out$In_Out", "CONTINUE");
				
				if(audioenabled.equalsIgnoreCase("TRUE")) {
					processAnimation("", print_writers, "Audio", "START");
				}
				
				processAnimation("", print_writers, "MatchID$Start_End", "CONTINUE");
				processAnimation("", print_writers, "MatchID$ALL", "CONTINUE");
				
				TimeUnit.MILLISECONDS.sleep(800);
				AnimateIn("ArrowUp,", print_writers, config); // Push infobar
				this.whichGraphicOnScreen = "";
				break;
			case "z": case "x": case "c": case "v": case "Control_z": case "Control_x": case "Control_Shift_Z": case "Control_Shift_Y":
				
				processAnimation("", print_writers, "Wipe_BG_In_Out$In_Out", "CONTINUE");
				
				if(audioenabled.equalsIgnoreCase("TRUE")) {
					processAnimation("", print_writers, "Audio", "START");
				}
				
				processAnimation("", print_writers, "Full_Frames$Essentials$In_Out", "CONTINUE");
				processAnimation("", print_writers, "Full_Frames$Sponsor$In_Out", "CONTINUE");
				processAnimation("", print_writers, "Full_Frames$Main_Graphics$In_Out$Top5$In_Out", "CONTINUE");
				TimeUnit.MILLISECONDS.sleep(800);
				AnimateIn("ArrowUp,", print_writers, config); // Push infobar
				
				TimeUnit.MILLISECONDS.sleep(800);
				processAnimation("", print_writers, "Full_Frames", "SHOW 0.0");
				
				this.whichGraphicOnScreen = "";
				break;	
			case "F1": case "Control_Shift_F1": case "F2": case "Control_Shift_F2": case "Control_F11": case "F4": case "Shift_F11": case "Control_F7":
			case "Shift_K": case "Shift_T": case "Shift_D": case "Control_F10": case "Control_d": case "Control_e": case "Shift_P": case "Shift_Q":
			case "Shift_F10": case "Control_p":
				 
				switch (whatToProcess.split(",")[0]) {
				case "Control_F11": case "Shift_F11":
					processAnimation("", print_writers, "Full_Frames$Main_Graphics$In_Out$Summary$In_Out", "CONTINUE");
					break;
				case "Control_p":
					processAnimation("", print_writers, "Full_Frames$Main_Graphics$In_Out$Standings$In_Out", "CONTINUE");
					break;
				case "Control_F7":
					processAnimation("", print_writers, "Full_Frames$Main_Graphics$In_Out$LineUp_Both$In_Out", "CONTINUE");
					break;
				case "Shift_K":
					processAnimation("", print_writers, "Full_Frames$Main_Graphics$In_Out$CurrPart$In_Out", "CONTINUE");
					break;
				case "Shift_T":
					processAnimation("", print_writers, "Full_Frames$Main_Graphics$In_Out$PlayingXI_Image$In_Out", "CONTINUE");
					break;
				case "Shift_D":
					processAnimation("", print_writers, "Full_Frames$Main_Graphics$In_Out$Target$In_Out", "CONTINUE");
					break;
				case "Shift_F10":
					processAnimation("", print_writers, "Full_Frames$Main_Graphics$In_Out$Worm$In_Out", "CONTINUE");
					break;
				case "Control_F10":
					processAnimation("", print_writers, "Full_Frames$Main_Graphics$In_Out$Manhattan$In_Out", "CONTINUE");
					break;
				case "Control_d": case "Control_e": case "Shift_P": case "Shift_Q":
					processAnimation("", print_writers, "Full_Frames$Main_Graphics$In_Out$Profile$In_Out", "CONTINUE");
					break;
				case "F4":
					processAnimation("", print_writers, "Full_Frames$Main_Graphics$In_Out$Partnership_Table$In_Out", "CONTINUE");
					break;
				case "F1": case "Control_Shift_F1":
					processAnimation("", print_writers, "Full_Frames$Main_Graphics$In_Out$BatCard$In_Out", "CONTINUE");
					break;
				case "F2": case "Control_Shift_F2":
					processAnimation("", print_writers, "Full_Frames$Main_Graphics$In_Out$BallCard$In_Out", "CONTINUE");
					break;
				}
				
				processAnimation("", print_writers, "Wipe_BG_In_Out$In_Out", "CONTINUE");
				
				if(audioenabled.equalsIgnoreCase("TRUE")) {
					processAnimation("", print_writers, "Audio", "START");
				}
				
				processAnimation("", print_writers, "Full_Frames$Essentials$In_Out", "CONTINUE");
				processAnimation("", print_writers, "Full_Frames$Sponsor$In_Out", "CONTINUE");
				
				TimeUnit.MILLISECONDS.sleep(800);
				AnimateIn("ArrowUp,", print_writers, config); // Push infobar
				
				TimeUnit.MILLISECONDS.sleep(800);
				processAnimation("", print_writers, "Full_Frames", "SHOW 0.0");
				
				this.whichGraphicOnScreen = "";
				
				caption.this_fullFramesGfx.this_ALL_FF.fullframes.setHighlight(0);
				caption.this_fullFramesGfx.this_ALL_FF.fullframes.setSecondHighlight(0);
				
				break;
			case "Control_Shift_U": case "Control_Shift_V":
				processAnimation(Constants.FRONT, print_writers, "PopUp$In_Out", "CONTINUE");
				TimeUnit.MILLISECONDS.sleep(700);
				processAnimation(Constants.FRONT, print_writers, "PopUp$In_Out", "SHOW 0.0");
				this.whichGraphicOnScreen = "";
				break;
			case "Shift_O": case "Control_k": case "k": case "g": case "y": case "Control_y": case "h": case "Shift_F4": case "Shift_F":case "Alt_b":
			case "Control_Shift_R": case "Control_Shift_F3": case "Control_Shift_J": case "Alt_p":
				processAnimation(Constants.FRONT, print_writers, "Bugs$In_Out", "CONTINUE");
				TimeUnit.MILLISECONDS.sleep(700);
				processAnimation(Constants.FRONT, print_writers, "Bugs$In_Out", "SHOW 0.0");
				this.whichGraphicOnScreen = "";
				break;
			case "Shift_C":
				processAnimation(Constants.FRONT, print_writers, "SixDistance", "CONTINUE");
				TimeUnit.MILLISECONDS.sleep(700);
				processAnimation(Constants.FRONT, print_writers, "SixDistance", "SHOW 0.0");
				this.whichGraphicOnScreen = "";
				break;
			case "Shift_F1": case "Shift_F2": case "Alt_F7": case "Alt_F1": case "Alt_F2":
				processAnimation(Constants.FRONT, print_writers, "Mini$In_Out", "CONTINUE");
				TimeUnit.MILLISECONDS.sleep(700);
				processAnimation(Constants.FRONT, print_writers, "Mini$In_Out", "SHOW 0.0");
				this.whichGraphicOnScreen = "";
				break;	
			}
			break;
		case Constants.TRI_SERIES:  case Constants.MT20:
			switch (whatToProcess.split(",")[0]) {
			case "Control_F12": case "F12":
				processAnimation(Constants.FRONT, print_writers, "anim_Infobar$InOut$Essencials", "CONTINUE");
				
				switch (whatToProcess.split(",")[0]) {
				case "Control_F12":
					processAnimation(Constants.FRONT, print_writers, "anim_Infobar$InOut$Ident", "CONTINUE");
					break;
				case "F12":
					processAnimation(Constants.FRONT, print_writers, "anim_Infobar$InOut$Normal", "CONTINUE");
					break;
				}
				
				TimeUnit.MILLISECONDS.sleep(1800);
				processAnimation(Constants.FRONT, print_writers, "anim_Infobar", "SHOW 0.0");
				
				this.infobar.setInfobar_on_screen(false);
				caption.this_infobarGfx.infobar.setInfobar_on_screen(false);
				caption.this_infobarGfx.infobar.setPowerplay_on_screen(false);
				
				caption.this_infobarGfx.BatterTickerName = false;
				caption.this_infobarGfx.bowlerTickerName = false;
				
				caption.this_infobarGfx.currentOnStrike = "";
				caption.this_infobarGfx.previousOnStrike = "";
				
				caption.this_infobarGfx.infobar.setSection1("");
				caption.this_infobarGfx.infobar.setSection2("");
				caption.this_infobarGfx.infobar.setSection3("");
				caption.this_infobarGfx.infobar.setSection4("");
				caption.this_infobarGfx.infobar.setSection5("");
				caption.this_infobarGfx.infobar.setSectionAnalytics("");
				
				caption.this_infobarGfx.infobar.setLast_sectionAnalytics("");
				caption.this_infobarGfx.infobar.setLast_section1("");
				caption.this_infobarGfx.infobar.setLast_section2("");
				caption.this_infobarGfx.infobar.setLast_section3("");
				caption.this_infobarGfx.infobar.setLast_section4("");
				caption.this_infobarGfx.infobar.setLast_section5("");
				break;
			case "o":
				if(caption.this_infobarGfx.infobar.getLast_sectionAnalytics() != null && !caption.this_infobarGfx.infobar.getLast_sectionAnalytics().isEmpty()) {
					processAnimation(Constants.FRONT, print_writers, "anim_Infobar$Lt_Analytics$InOut", "CONTINUE");
					processAnimation(Constants.FRONT, print_writers, "anim_Infobar$Analytics$InOut", "CONTINUE");
					
					TimeUnit.MILLISECONDS.sleep(1800);
					processAnimation(Constants.FRONT, print_writers, "anim_Infobar$Lt_Analytics$InOut", "SHOW 0.0");
				}else {
					processAnimation(Constants.FRONT, print_writers, "anim_Infobar$Lt_Analytics$InOut", "CONTINUE");
					TimeUnit.MILLISECONDS.sleep(500);
					processAnimation(Constants.FRONT, print_writers, "Fade_For_Analytics", "CONTINUE");
					
					TimeUnit.MILLISECONDS.sleep(1800);
					processAnimation(Constants.FRONT, print_writers, "anim_Infobar$Lt_Analytics$InOut", "SHOW 0.0");
					processAnimation(Constants.FRONT, print_writers, "Fade_For_Analytics", "SHOW 0.0");
				}
				
				caption.this_infobarGfx.infobar.setSectionLtAnalytics("");
				caption.this_infobarGfx.infobar.setLast_sectionLtAnalytics("");
				break;
				
			case "m": case "Control_m":
				
				if(audioenabled.equalsIgnoreCase("TRUE")) {
					processAnimation(Constants.BACK, print_writers, "Audio", "START");
				}
				processAnimation(Constants.BACK, print_writers, "anim_Ident", "CONTINUE");
				
				TimeUnit.MILLISECONDS.sleep(1600);
				
				AnimateIn("ArrowUp,", print_writers, config); // Push infobar
				processAnimation(Constants.BACK, print_writers, "anim_Ident", "SHOW 0.0");
				if(this.watermarkOnScreen.equalsIgnoreCase("WATERMARK")) {
					processAnimation(Constants.FRONT, print_writers, "Watermark", "START");
				}
				this.whichGraphicOnScreen = "";
				break;
			case "Control_d": case "Control_e": case "Shift_P": case "Shift_Q":
				
				if(audioenabled.equalsIgnoreCase("TRUE")) {
					processAnimation(Constants.BACK, print_writers, "Audio", "START");
				}
				processAnimation(Constants.BACK, print_writers, "anim_Playerprofile", "CONTINUE");
				
				TimeUnit.MILLISECONDS.sleep(1600);
				
				AnimateIn("ArrowUp,", print_writers, config); // Push infobar
				processAnimation(Constants.BACK, print_writers, "anim_Playerprofile", "SHOW 0.0");
				if(this.watermarkOnScreen.equalsIgnoreCase("WATERMARK")) {
					processAnimation(Constants.FRONT, print_writers, "Watermark", "START");
				}
				this.whichGraphicOnScreen = "";
				break;
			case "z": case "x": case "c": case "v": case "Control_z": case "Control_x": case "Control_Shift_Z": case "Control_Shift_Y":
				if(audioenabled.equalsIgnoreCase("TRUE")) {
					processAnimation(Constants.BACK, print_writers, "Audio", "START");
				}
				
				processAnimation(Constants.BACK, print_writers, "Anim_FullFrames$In_Out$Essentials", "CONTINUE");
				processAnimation(Constants.BACK, print_writers, "Anim_FullFrames$In_Out$Big_Logo", "CONTINUE");
				processAnimation(Constants.BACK, print_writers, "Anim_FullFrames$In_Out$TopTitle", "CONTINUE");
				processAnimation(Constants.BACK, print_writers, "Anim_FullFrames$In_Out$SideTeamColour", "CONTINUE");
				processAnimation(Constants.BACK, print_writers, "Anim_FullFrames$In_Out$Title", "CONTINUE");
				processAnimation(Constants.BACK, print_writers, "Anim_FullFrames$In_Out$Main$LeaderBoard", "CONTINUE");
				
				TimeUnit.MILLISECONDS.sleep(1600);
				
				AnimateIn("ArrowUp,", print_writers, config); // Push infobar
				if(this.watermarkOnScreen.equalsIgnoreCase("WATERMARK")) {
					processAnimation(Constants.FRONT, print_writers, "Watermark", "START");
				}
				this.whichGraphicOnScreen = "";
				break;	
			case "Control_Shift_*":
				
				processAnimation(Constants.FRONT, print_writers, "anim_Sponsor", "CONTINUE");
				
				this.whichGraphicOnScreen = "";
				break;	
			case "Control_Shift_F7":
//				processAnimation(Constants.BACK, print_writers, "Anim_LineUp_Image$In_Out", "SHOW 5");
				System.out.println("lineUpCount - " + lineUpCount);
				if(lineUpCount > 2) {
					processAnimation(Constants.BACK, print_writers, "Anim_LineUp_Image$In_Out$Essentials", "CONTINUE");
					processAnimation(Constants.BACK, print_writers, "Anim_LineUp_Image$In_Out$Big_Logo", "CONTINUE");
					processAnimation(Constants.BACK, print_writers, "Anim_LineUp_Image$In_Out$TopTitle", "CONTINUE");
					processAnimation(Constants.BACK, print_writers, "Anim_LineUp_Image$In_Out$SideTeamColour", "CONTINUE");
					processAnimation(Constants.BACK, print_writers, "Anim_LineUp_Image$In_Out$Title", "CONTINUE");
					processAnimation(Constants.BACK, print_writers, "Anim_LineUp_Image$In_Out$SubTitle", "CONTINUE");
					processAnimation(Constants.BACK, print_writers, "Anim_LineUp_Image$In_Out$Main$Out2", "CONTINUE");
				} else {
					processAnimation(Constants.BACK, print_writers, "Anim_LineUp_Image$In_Out", "CONTINUE");
				}
				TimeUnit.MILLISECONDS.sleep(1000);
				AnimateIn("ArrowUp,", print_writers, config); // Restore infobar
				TimeUnit.MILLISECONDS.sleep(1000);
				if(this.watermarkOnScreen.equalsIgnoreCase("WATERMARK")) {
					processAnimation(Constants.FRONT, print_writers, "Watermark", "START");
				}
				this.whichGraphicOnScreen = "";
				break;
				
			case "Control_Alt_F1":
				processAnimation(Constants.BACK, print_writers, "Anim_FullFrames$In_Out$Essentials", "CONTINUE");
				processAnimation(Constants.BACK, print_writers, "Anim_FullFrames$In_Out$Big_Logo", "CONTINUE");
				processAnimation(Constants.BACK, print_writers, "Anim_FullFrames$In_Out$TopTitle", "CONTINUE");
				processAnimation(Constants.BACK, print_writers, "Anim_FullFrames$In_Out$SideTeamColour", "CONTINUE");
				processAnimation(Constants.BACK, print_writers, "Anim_FullFrames$In_Out$Title", "CONTINUE");
				processAnimation(Constants.BACK, print_writers, "Anim_FullFrames$In_Out$SubTitle", "CONTINUE");
				processAnimation(Constants.BACK, print_writers, "Anim_FullFrames$In_Out$Base", "CONTINUE");
				processAnimation(Constants.BACK, print_writers, "Anim_FullFrames$In_Out$Footer", "CONTINUE");
				
				processAnimation(Constants.BACK, print_writers, "Anim_FullFrames$In_Out$Main$Batting_Card$BatterBase", "CONTINUE");
				processAnimation(Constants.BACK, print_writers, "Anim_FullFrames$In_Out$Main$Batting_Card$Batter", "CONTINUE");
				processAnimation(Constants.BACK, print_writers, "Anim_FullFrames$In_Out$Main$Batting_Card$Bowler", "CONTINUE");
				
				TimeUnit.MILLISECONDS.sleep(1000);
				AnimateIn("ArrowUp,", print_writers, config); // Restore infobar
				TimeUnit.MILLISECONDS.sleep(1000);
				if(this.watermarkOnScreen.equalsIgnoreCase("WATERMARK")) {
					processAnimation(Constants.FRONT, print_writers, "Watermark", "START");
				}
				this.whichGraphicOnScreen = "";
				break;
			case "Alt_Shift_F1":
				processAnimation(Constants.BACK, print_writers, "Anim_FullFrames$In_Out$Essentials", "CONTINUE");
				processAnimation(Constants.BACK, print_writers, "Anim_FullFrames$In_Out$Big_Logo", "CONTINUE");
				processAnimation(Constants.BACK, print_writers, "Anim_FullFrames$In_Out$TopTitle", "CONTINUE");
				processAnimation(Constants.BACK, print_writers, "Anim_FullFrames$In_Out$SideTeamColour", "CONTINUE");
				processAnimation(Constants.BACK, print_writers, "Anim_FullFrames$In_Out$Title", "CONTINUE");
				processAnimation(Constants.BACK, print_writers, "Anim_FullFrames$In_Out$SubTitle", "CONTINUE");
				processAnimation(Constants.BACK, print_writers, "Anim_FullFrames$In_Out$Logo", "CONTINUE");
				processAnimation(Constants.BACK, print_writers, "Anim_FullFrames$In_Out$Base", "CONTINUE");
				processAnimation(Constants.BACK, print_writers, "Anim_FullFrames$In_Out$Footer", "CONTINUE");
				
				processAnimation(Constants.BACK, print_writers, "Anim_FullFrames$In_Out$Main$Batting_Card$BatterBase", "CONTINUE");
				processAnimation(Constants.BACK, print_writers, "Anim_FullFrames$In_Out$Main$Batting_Card$Batter", "CONTINUE");
				processAnimation(Constants.BACK, print_writers, "Anim_FullFrames$In_Out$Main$Batting_Card$Contribution", "CONTINUE");
				
				TimeUnit.MILLISECONDS.sleep(1000);
				AnimateIn("ArrowUp,", print_writers, config); // Restore infobar
				TimeUnit.MILLISECONDS.sleep(1000);
				if(this.watermarkOnScreen.equalsIgnoreCase("WATERMARK")) {
					processAnimation(Constants.FRONT, print_writers, "Watermark", "START");
				}
				this.whichGraphicOnScreen = "";
				break;
		
				
			case "F1": case "F2": case "Control_F7": case "Control_F11": case "F4": case "Shift_K": case "Control_F10": case "Shift_F10":
			case "Control_Shift_F1": case "Control_Shift_F2": case "Alt_F9": case "Alt_F11": case "Control_F1": case "Shift_F11":
			case "Control_p": case "Control_Shift_F4": case "Control_Shift_F5": 
				
				if(audioenabled.equalsIgnoreCase("TRUE")) {
					processAnimation(Constants.BACK, print_writers, "Audio", "START");
				}
				
				processAnimation(Constants.BACK, print_writers, "Anim_FullFrames$In_Out$Essentials", "CONTINUE");
				processAnimation(Constants.BACK, print_writers, "Anim_FullFrames$In_Out$Big_Logo", "CONTINUE");
				processAnimation(Constants.BACK, print_writers, "Anim_FullFrames$In_Out$TopTitle", "CONTINUE");
				processAnimation(Constants.BACK, print_writers, "Anim_FullFrames$In_Out$SideTeamColour", "CONTINUE");
				processAnimation(Constants.BACK, print_writers, "Anim_FullFrames$In_Out$Title", "CONTINUE");
				processAnimation(Constants.BACK, print_writers, "Anim_FullFrames$In_Out$Base", "CONTINUE");
				
				if(!whatToProcess.split(",")[0].equalsIgnoreCase("Shift_K") && !whatToProcess.split(",")[0].equalsIgnoreCase("Alt_F11")) {
					processAnimation(Constants.BACK, print_writers, "Anim_FullFrames$In_Out$Footer", "CONTINUE");
				}
				
				if(!whatToProcess.split(",")[0].equalsIgnoreCase("Control_Shift_F1") && !whatToProcess.split(",")[0].equalsIgnoreCase("Control_Shift_F2") && 
						!whatToProcess.split(",")[0].equalsIgnoreCase("Control_p") && !whatToProcess.split(",")[0].equalsIgnoreCase("Control_Shift_F4") && 
						!whatToProcess.split(",")[0].equalsIgnoreCase("Control_Shift_F5")) {
					processAnimation(Constants.BACK, print_writers, "Anim_FullFrames$In_Out$SubTitle", "CONTINUE");
					processAnimation(Constants.BACK, print_writers, "Anim_FullFrames$In_Out$Logo", "CONTINUE");
				}
				
//				if(whatToProcess.split(",")[0].equalsIgnoreCase("Control_Shift_F1") || whatToProcess.split(",")[0].equalsIgnoreCase("Control_Shift_F2")) {
//					processAnimation(Constants.BACK, print_writers, "MoveForExtra", "SHOW 0.0");
//				}
				
				if(!whatToProcess.split(",")[0].equalsIgnoreCase("Control_F7") && !whatToProcess.split(",")[0].equalsIgnoreCase("Control_F11") && 
						!whatToProcess.split(",")[0].equalsIgnoreCase("Alt_F11")) {
					processAnimation(Constants.BACK, print_writers, "Anim_FullFrames$In_Out$Logo", "CONTINUE");
				}
				
				switch (whatToProcess.split(",")[0]) {
				case "F1":
//					processAnimation(Constants.BACK, print_writers, "Anim_FullFrames$In_Out$Main$Batting_Card$BatterBase", "CONTINUE");
//					processAnimation(Constants.BACK, print_writers, "Anim_FullFrames$In_Out$Main$Batting_Card$DismissalBase", "CONTINUE");
					processAnimation(Constants.BACK, print_writers, "Anim_FullFrames$In_Out$Main$Batting_Card$Batter", "CONTINUE");
					processAnimation(Constants.BACK, print_writers, "Anim_FullFrames$In_Out$Main$Batting_Card$Dismissal", "CONTINUE");
					break;
				case "Control_Shift_F4":
					processAnimation(Constants.BACK, print_writers, "Anim_FullFrames$In_Out$Main$Batting_CardExtra", "CONTINUE");
					TimeUnit.MILLISECONDS.sleep(1000);
					processAnimation(Constants.BACK, print_writers, "PlayerHighlight$BattingCardExtra$Side1$" + 
							caption.this_fullFramesGfx.this_ALL_FF.fullframes.getPreHighlight() + "", "SHOW 0.0");
					break;
				case "Control_Shift_F5":
					processAnimation(Constants.BACK, print_writers, "Anim_FullFrames$In_Out$Main$Bowling_CardExtra", "CONTINUE");
					TimeUnit.MILLISECONDS.sleep(1000);
					processAnimation(Constants.BACK, print_writers, "PlayerHighlight$BowlingCardExtra$Side1$" + 
							caption.this_fullFramesGfx.this_ALL_FF.fullframes.getPreHighlight() + "", "SHOW 0.0");
					break;
				case "Control_Shift_F1":
					processAnimation(Constants.BACK, print_writers, "Anim_FullFrames$In_Out$Main$Batting_Card$BatterBase", "CONTINUE");
					processAnimation(Constants.BACK, print_writers, "Anim_FullFrames$In_Out$Main$Batting_Card$Batter", "CONTINUE");
					
					processAnimation(Constants.BACK, print_writers, "Anim_FullFrames$In_Out$ExtraData$InOut", "CONTINUE");
					processAnimation(Constants.BACK, print_writers, "PlayerHighlight$BattingCard$Side1$" + prevHighlightDirector + "", "SHOW 0.0");
					
					caption.this_fullFramesGfx.this_ALL_FF.fullframes.setHighlight(0);
					caption.this_fullFramesGfx.this_ALL_FF.fullframes.setPreHighlight(0);
					break;	
				case "F2":
					processAnimation(Constants.BACK, print_writers, "Anim_FullFrames$In_Out$Main$Bowling_Card$BowlerBase", "CONTINUE");
					processAnimation(Constants.BACK, print_writers, "Anim_FullFrames$In_Out$Main$Bowling_Card$DataBase", "CONTINUE");
					processAnimation(Constants.BACK, print_writers, "Anim_FullFrames$In_Out$Main$Bowling_Card$Bowler", "CONTINUE");
					processAnimation(Constants.BACK, print_writers, "Anim_FullFrames$In_Out$Main$Bowling_Card$BowlerDetails", "CONTINUE");
					break;
				case "Control_Shift_F2":
					processAnimation(Constants.BACK, print_writers, "Anim_FullFrames$In_Out$Main$Bowling_Card$BowlerBase", "CONTINUE");
					processAnimation(Constants.BACK, print_writers, "Anim_FullFrames$In_Out$Main$Bowling_Card$Bowler", "CONTINUE");
					
					processAnimation(Constants.BACK, print_writers, "Anim_FullFrames$In_Out$ExtraData$InOut", "CONTINUE");
					processAnimation(Constants.BACK, print_writers, "PlayerHighlight$BowlingCard$Side1$" + prevHighlightDirector + "", "SHOW 0.0");
					
					caption.this_fullFramesGfx.this_ALL_FF.fullframes.setHighlight(0);
					caption.this_fullFramesGfx.this_ALL_FF.fullframes.setPreHighlight(0);
					break;	
				case "Control_F7":
					processAnimation(Constants.BACK, print_writers, "Anim_FullFrames$In_Out$Main$Teams", "CONTINUE");
					break;
				case "Control_F11": case "Shift_F11":
					processAnimation(Constants.BACK, print_writers, "Anim_FullFrames$In_Out$Main$Summary", "CONTINUE");
					break;
				case "Control_p":
					processAnimation(Constants.BACK, print_writers, "Anim_FullFrames$In_Out$Main$Standings", "CONTINUE");
					break;	
				case "F4":
					processAnimation(Constants.BACK, print_writers, "Anim_FullFrames$In_Out$Main$PartnershipList", "CONTINUE");
					break;
				case "Shift_K":
					processAnimation(Constants.BACK, print_writers, "Anim_FullFrames$In_Out$Main$Partnership", "CONTINUE");
					break;
				case "Control_F10":
					processAnimation(Constants.BACK, print_writers, "Anim_FullFrames$In_Out$Main$Manhattan", "CONTINUE");
					break;
				case "Shift_F10":
					processAnimation(Constants.BACK, print_writers, "Anim_FullFrames$In_Out$Main$Worm", "CONTINUE");
					break;
				case "Alt_F9":
					processAnimation(Constants.BACK, print_writers, "Anim_FullFrames$In_Out$Main$TeamSingle", "CONTINUE");
					break;
				case "Alt_F11":
					processAnimation(Constants.BACK, print_writers, "Anim_FullFrames$In_Out$Main$ManhattanComparison", "CONTINUE");
					break;
				case "Control_F1":
					processAnimation(Constants.BACK, print_writers, "Anim_FullFrames$In_Out$Main$ImageBattingCard", "CONTINUE");
					break;
				}
				
				TimeUnit.MILLISECONDS.sleep(1600);
				AnimateIn("ArrowUp,", print_writers, config); // Push infobar
				if(this.watermarkOnScreen.equalsIgnoreCase("WATERMARK")) {
					processAnimation(Constants.FRONT, print_writers, "Watermark", "START");
				}
				
				TimeUnit.MILLISECONDS.sleep(1000);
				processAnimation(Constants.BACK, print_writers, "Anim_FullFrames", "SHOW 0.0");
				this.whichGraphicOnScreen = "";
				break;
			case "Shift_D":
				
				processAnimation(Constants.BACK, print_writers, "anim_Target$In_Out", "CONTINUE");
				AnimateIn("ArrowUp,", print_writers, config); // Push infobar
				TimeUnit.MILLISECONDS.sleep(2500);
				if(this.watermarkOnScreen.equalsIgnoreCase("WATERMARK")) {
					processAnimation(Constants.FRONT, print_writers, "Watermark", "START");
				}
				processAnimation(Constants.BACK, print_writers, "anim_Target$In_Out", "SHOW 0.0");
				this.whichGraphicOnScreen = "";
				break;	
			case "Shift_F1": case "Shift_F2": case "Alt_F7":
				processAnimation(Constants.FRONT, print_writers, "Anim_Mini$In_Out", "CONTINUE");
				TimeUnit.MILLISECONDS.sleep(1000);
				processAnimation(Constants.FRONT, print_writers, "Anim_Mini$In_Out", "SHOW 0.0");
				this.whichGraphicOnScreen = "";
				break;	
			case "Control_Shift_F10":
				processAnimation(Constants.FRONT, print_writers, "LT_Manhattan$InOut", "CONTINUE");
				TimeUnit.MILLISECONDS.sleep(1000);
				processAnimation(Constants.FRONT, print_writers, "LT_Manhattan$InOut", "SHOW 0.0");
				if(infobar.getInfobar_status() != null) {
					if(!infobar.getInfobar_status().equalsIgnoreCase(Constants.FORCED+Constants.SHRUNK_INFOBAR)) {
						TimeUnit.MILLISECONDS.sleep(1000);
						AnimateIn("ArrowRight" + ",", print_writers, config); // Restore infobar
						infobar.setInfobar_status(Constants.TWO_LINER_INFOBAR);
					}
				}
				this.whichGraphicOnScreen = "";
				break;
				
			case "Alt_F8": case "F8": case "F10":
				processAnimation(Constants.FRONT, print_writers, "anim_Namesuper$InOut", "CONTINUE");
				
				if(infobar.getInfobar_status() != null) {
					if(!infobar.getInfobar_status().equalsIgnoreCase(Constants.FORCED+Constants.SHRUNK_INFOBAR)) {
						TimeUnit.MILLISECONDS.sleep(1000);
						AnimateIn("ArrowRight" + ",", print_writers, config); // Restore infobar
						infobar.setInfobar_status(Constants.TWO_LINER_INFOBAR);
					}
				}
				
				TimeUnit.MILLISECONDS.sleep(1000);
				processAnimation(Constants.FRONT, print_writers, "anim_Namesuper", "SHOW 0.0");
				this.whichGraphicOnScreen = "";
				break;
			
			case "Control_F5": case "Control_F9": case "F9": case "Shift_E": case "d": case "e": 
			
				processAnimation(Constants.FRONT, print_writers, "anim_Lower_Third$InOut$Essentials", "CONTINUE");
				processAnimation(Constants.FRONT, print_writers, "anim_Lower_Third$InOut$Logo", "CONTINUE");
				
				if(infobar.getInfobar_status() != null) {
					if(!infobar.getInfobar_status().equalsIgnoreCase(Constants.FORCED+Constants.SHRUNK_INFOBAR)) {
						TimeUnit.MILLISECONDS.sleep(1000);
						AnimateIn("ArrowRight" + ",", print_writers, config); // Restore infobar
						infobar.setInfobar_status(Constants.TWO_LINER_INFOBAR);
					}
				}
				TimeUnit.MILLISECONDS.sleep(1000);
				processAnimation(Constants.FRONT, print_writers, "anim_Lower_Third", "SHOW 0.0");
				this.whichGraphicOnScreen = "";
				break;
			case "F7": case "F11": case "Control_s": case "Control_f": case "F5": case "F6": case "Control_F6": case "Shift_F6":
			case "u": case "Shift_F3":	case "Shift_F5": case "Shift_F9": case "Alt_F12": case "Control_h": case "Control_a":
			case "Alt_Shift_F3": case "Control_Shift_Q": case "Alt_F1": case "Alt_F2": case "Control_Shift_X": case "Control_Shift_K": 
			case "Control_c":
				processAnimation(Constants.FRONT, print_writers, "anim_Lower_Third", "CONTINUE");
				
				if(infobar.getInfobar_status() != null) {
					if(!infobar.getInfobar_status().equalsIgnoreCase(Constants.FORCED+Constants.SHRUNK_INFOBAR)) {
						TimeUnit.MILLISECONDS.sleep(1000);
						AnimateIn("ArrowRight" + ",", print_writers, config); // Restore infobar
						infobar.setInfobar_status(Constants.TWO_LINER_INFOBAR);
					}
				}
				TimeUnit.MILLISECONDS.sleep(1000);
				processAnimation(Constants.FRONT, print_writers, "anim_Lower_Third", "SHOW 0.0");
				this.whichGraphicOnScreen = "";
				break;
			case "Alt_p":
				if(this.specialBugOnScreen.equalsIgnoreCase(CricketUtil.TOSS)) {
					processAnimation(Constants.FRONT, print_writers, "anim__Bug$In_Out", "CONTINUE");
//					processAnimation(Constants.FRONT, print_writers, "anim__TossBug$In_Out", "CONTINUE");
					TimeUnit.MILLISECONDS.sleep(700);
					processAnimation(Constants.FRONT, print_writers, "anim__Bug", "SHOW 0.0");
//					processAnimation(Constants.FRONT, print_writers, "anim__TossBug", "SHOW 0.0");
					this.specialBugOnScreen = "";
				}
				break;
			case "Shift_O": case "Control_k": case "k": case "g": case "y": case "Control_y": case "h": case "Shift_F4": case "Shift_F":case "Alt_b":
			case "Control_Shift_R": case "Control_Shift_F3":  case "Shift_C": case "Control_Shift_J":
				processAnimation(Constants.FRONT, print_writers, "anim__Bug", "CONTINUE");
				TimeUnit.MILLISECONDS.sleep(700);
				processAnimation(Constants.FRONT, print_writers, "anim__Bug", "SHOW 0.0");
				AnimateIn("ArrowUp,", print_writers, config);
				this.whichGraphicOnScreen = "";
				break;
			case "Control_Shift_U": case "Control_Shift_V":
				processAnimation(Constants.FRONT, print_writers, "PopUps$InOut", "CONTINUE");
				TimeUnit.MILLISECONDS.sleep(1000);
				processAnimation(Constants.FRONT, print_writers, "PopUps", "SHOW 0.0");
				processAnimation(Constants.FRONT, print_writers, "PopUps$Out", "SHOW 2.800");
				this.whichGraphicOnScreen = "";
				break;
			case "Control_F3":
				processAnimation(Constants.FRONT, print_writers, "LT_Comparison$InOut", "CONTINUE");
				TimeUnit.MILLISECONDS.sleep(1000);
				processAnimation(Constants.FRONT, print_writers, "LT_Comparison$InOut", "SHOW 0.0");
				
				if(infobar.getInfobar_status() != null) {
					if(!infobar.getInfobar_status().equalsIgnoreCase(Constants.FORCED+Constants.SHRUNK_INFOBAR)) {
						TimeUnit.MILLISECONDS.sleep(1000);
						AnimateIn("ArrowRight" + ",", print_writers, config); // Restore infobar
						infobar.setInfobar_status(Constants.TWO_LINER_INFOBAR);
					}
				}
				
				this.whichGraphicOnScreen = "";
				break;
			case "Control_Shift_B":
				processAnimation(Constants.FRONT, print_writers, "LT_NextToBat$InOut", "CONTINUE");
				TimeUnit.MILLISECONDS.sleep(1000);
				processAnimation(Constants.FRONT, print_writers, "LT_NextToBat$InOut", "SHOW 0.0");
				if(infobar.getInfobar_status() != null) {
					if(!infobar.getInfobar_status().equalsIgnoreCase(Constants.FORCED+Constants.SHRUNK_INFOBAR)) {
						TimeUnit.MILLISECONDS.sleep(1000);
						AnimateIn("ArrowRight" + ",", print_writers, config); // Restore infobar
						infobar.setInfobar_status(Constants.TWO_LINER_INFOBAR);
					}
				}
				this.whichGraphicOnScreen = "";
				break;
			case "Control_6":
				processAnimation(Constants.FRONT, print_writers, "LT_Weather$InOut", "CONTINUE");
				TimeUnit.MILLISECONDS.sleep(1000);
				processAnimation(Constants.FRONT, print_writers, "LT_Weather$InOut", "SHOW 0.0");
				if(infobar.getInfobar_status() != null) {
					if(!infobar.getInfobar_status().equalsIgnoreCase(Constants.FORCED+Constants.SHRUNK_INFOBAR)) {
						TimeUnit.MILLISECONDS.sleep(1000);
						AnimateIn("ArrowRight" + ",", print_writers, config); // Restore infobar
						infobar.setInfobar_status(Constants.TWO_LINER_INFOBAR);
					}
				}
				this.whichGraphicOnScreen = "";
				break;
			case "Control_Shift_O":
				processAnimation(Constants.FRONT, print_writers, "LT_PlayingXI$InOut", "CONTINUE");
				TimeUnit.MILLISECONDS.sleep(1000);
				processAnimation(Constants.FRONT, print_writers, "LT_PlayingXI$InOut", "SHOW 0.0");
				if(infobar.getInfobar_status() != null) {
					if(!infobar.getInfobar_status().equalsIgnoreCase(Constants.FORCED+Constants.SHRUNK_INFOBAR)) {
						TimeUnit.MILLISECONDS.sleep(1000);
						AnimateIn("ArrowRight" + ",", print_writers, config); // Restore infobar
						infobar.setInfobar_status(Constants.TWO_LINER_INFOBAR);
					}
				}
				this.whichGraphicOnScreen = "";
				break;	
			case "Control_Shift_M": case "Control_Shift_L":
				processAnimation(Constants.FRONT, print_writers, "LT_MatchID$InOut", "CONTINUE");
				TimeUnit.MILLISECONDS.sleep(1000);
				processAnimation(Constants.FRONT, print_writers, "LT_MatchID$InOut", "SHOW 0.0");
				
				if(infobar.getInfobar_status() != null) {
					if(!infobar.getInfobar_status().equalsIgnoreCase(Constants.FORCED+Constants.SHRUNK_INFOBAR)) {
						TimeUnit.MILLISECONDS.sleep(1000);
						AnimateIn("ArrowRight" + ",", print_writers, config); // Restore infobar
						infobar.setInfobar_status(Constants.TWO_LINER_INFOBAR);
					}
				}
				this.whichGraphicOnScreen = "";
				break;
			case "6": case "Control_4": case "5": case ";":
				processAnimation(Constants.FRONT, print_writers, "PopUps$InOut", "CONTINUE");
				this.whichGraphicOnScreen = "";
				TimeUnit.MILLISECONDS.sleep(1000);
				processAnimation(Constants.FRONT, print_writers, "PopUps$Change", "SHOW 0.0");
				processAnimation(Constants.FRONT, print_writers, "PopUps", "SHOW 0.0");
				break;	
			}
			break;
		case Constants.BCCI:
			switch (whatToProcess.split(",")[0]) {
			case "Control_F12": case "F12":
				processAnimation(Constants.FRONT, print_writers, "anim_Infobar$In_Out$Essentials", "CONTINUE");
				processAnimation(Constants.FRONT, print_writers, "anim_Infobar$In_Out$Logo_Colour", "CONTINUE");
				
				switch (whatToProcess.split(",")[0]) {
				case "Control_F12":
					processAnimation(Constants.FRONT, print_writers, "anim_Infobar$In_Out$Ident", "CONTINUE");
					break;
				case "F12":
					processAnimation(Constants.FRONT, print_writers, "anim_Infobar$In_Out$Normal", "CONTINUE");
					break;
				}
				
				TimeUnit.MILLISECONDS.sleep(1800);
				processAnimation(Constants.FRONT, print_writers, "anim_Infobar", "SHOW 0.0");
				
				this.infobar.setInfobar_on_screen(false);
				caption.this_infobarGfx.infobar.setInfobar_on_screen(false);
				break;
				
			case "m":
				processAnimation(Constants.BACK, print_writers, "anim_Ident", "CONTINUE");
				TimeUnit.MILLISECONDS.sleep(1500);
				AnimateIn("ArrowDown,", print_writers, config);
				break;
			
			case "F1": case "F2": case "F4": case "Control_F11": //Full framers
				switch (whatToProcess.split(",")[0]) {
				case "F1":
					if(whatToProcess.split(",")[0].equalsIgnoreCase("Control_Shift_F1")) {
						processAnimation(Constants.BACK, print_writers, "Bg_Shift", "CONTINUE REVERSE");
						switch (whatToProcess.split(",")[3]) {
						case "performer":
							processAnimation(Constants.BACK, print_writers, "anim_ExtraData$In_Out$PlayerStat", "CONTINUE");
							break;
						case "partnership":
							processAnimation(Constants.BACK, print_writers, "anim_ExtraData$In_Out$Partnership", "CONTINUE");
							break;
						}
					}
					processAnimation(Constants.BACK, print_writers, "anim_Fullframe$In_Out$Main$Normal_BattingCard", "CONTINUE");
					break;
				case "F2":
					processAnimation(Constants.BACK, print_writers, "anim_Fullframe$In_Out$Main$BowlingCard", "CONTINUE");
					break;
				case "F4":
					processAnimation(Constants.BACK, print_writers, "anim_Fullframe$In_Out$Main$PartnershipList", "CONTINUE");
					break;
				case "Control_F11":
					processAnimation(Constants.BACK, print_writers, "anim_Fullframe$In_Out$Main$Summary", "CONTINUE");
					break;
				}
				
				processAnimation(Constants.BACK, print_writers, "anim_Fullframe$In_Out$Sponsor", "CONTINUE");
				processAnimation(Constants.BACK, print_writers, "anim_Fullframe$In_Out$Footer", "CONTINUE");
				processAnimation(Constants.BACK, print_writers, "anim_Fullframe$In_Out$SubHeader", "CONTINUE");
				processAnimation(Constants.BACK, print_writers, "anim_Fullframe$In_Out$Header", "CONTINUE");
				processAnimation(Constants.BACK, print_writers, "anim_Fullframe$In_Out$TeamName", "CONTINUE");
				processAnimation(Constants.BACK, print_writers, "anim_Fullframe$In_Out$TeamLogo", "CONTINUE");
				processAnimation(Constants.BACK, print_writers, "anim_Fullframe$In_Out$RightVeil", "CONTINUE");
				TimeUnit.MILLISECONDS.sleep(500);
				processAnimation(Constants.BACK, print_writers, "anim_Fullframe$In_Out$Essentials", "CONTINUE");
				processAnimation(Constants.BACK, print_writers, "MoveForLogo", "SHOW 0.0");
				
				TimeUnit.MILLISECONDS.sleep(1500);
				AnimateIn("ArrowUp,", print_writers, config);
				
				this.whichGraphicOnScreen = "";
				break;
			}
		}
		return CricketUtil.YES;
	}
	
	public String ChangeOn(String whatToProcess,List<PrintWriter> print_writers,Configuration config) throws InterruptedException, IOException
	{
		switch (config.getBroadcaster().toUpperCase()) {
		case Constants.AFG_SL_SERIES:
			switch (whatToProcess.split(",")[0]) {
			case "Control_F12":
				processAnimation(Constants.FRONT, print_writers, "anim_Infobar$IdentType_Change", "START");
				break;
			case "Shift_F12":
				processAnimation(Constants.FRONT, print_writers, "anim_Infobar$Ident_Change", "START");
				break;
			case "Alt_1":
				processAnimation(Constants.FRONT, print_writers, "anim_Infobar$Section1_Change", "START");
				caption.this_infobarGfx.infobar.setLast_section1(caption.this_infobarGfx.infobar.getSection1());
				break;
			case "Alt_2":
				if (caption.this_infobarGfx.infobar.getSection2() == null || caption.this_infobarGfx.infobar.getSection2().isEmpty()) {
				    processAnimation(Constants.FRONT, print_writers, "anim_Infobar$Section2$In_Out", "CONTINUE");
				} else {
					if(caption.this_infobarGfx.infobar.getLast_section2() != null && !caption.this_infobarGfx.infobar.getLast_section2().isEmpty()) {
						processAnimation(Constants.FRONT, print_writers, "anim_Infobar$Section2$Change", "START");
					}else {
						processAnimation(Constants.FRONT, print_writers, "anim_Infobar$Section2$In_Out", "START");
					}
				}
				caption.this_infobarGfx.infobar.setLast_section2(caption.this_infobarGfx.infobar.getSection2());
				break;
			case "Alt_7":
				processAnimation(Constants.FRONT, print_writers, "anim_Infobar$Section3_Change", "START");
				caption.this_infobarGfx.infobar.setSection3(caption.this_infobarGfx.infobar.getSection3());
				break;
			
			case "Control_d": case "Control_e": case "Shift_P": case "Shift_Q":
				processAnimation("", print_writers, "Loop", "START");
				if(audioenabled.equalsIgnoreCase("TRUE")) {
					processAnimation("", print_writers, "Audio", "START");
				}
				processAnimation("", print_writers, "Change_Profile", "START");
				break;
			
			case "F1": case "F2": case "Control_F7": case "Shift_T": case "Control_Alt_F1": case "Alt_Shift_F1": case "F4": 
			case "Control_F11": case "Shift_K": case "Shift_F10": case "Control_F10": case "Alt_F11": case "Control_F1":
				if(audioenabled.equalsIgnoreCase("TRUE")) {
					processAnimation("", print_writers, "Audio", "START");
				}
				
				processAnimation("", print_writers, "Change_Fullframes$SubHeader", "START");
				processAnimation("", print_writers, "Change_Fullframes$Header", "START");
				processAnimation("", print_writers, "Change_Fullframes$Logo", "START");
				processAnimation("", print_writers, "Change_Fullframes$Icon", "START");
				processAnimation("", print_writers, "Change_Fullframes$VerticalText", "START");
				processAnimation("", print_writers, "Change_Fullframes$Footer", "START");
				
				switch(whichGraphicOnScreen.split(",")[0]) {
				case "F1":
					processAnimation("", print_writers, "Change_Fullframes$BattingCard", "START");
					break;
				case "F2":
					processAnimation("", print_writers, "Change_Fullframes$BowlingCard", "START");
					break;
				case "Control_F7": 
					processAnimation("", print_writers, "Change_Fullframes$Teams", "START");
					break;
				case "Shift_T": 
					processAnimation("", print_writers, "Change_Fullframes$Team", "START");
					break;
				case "Control_Alt_F1": case "Alt_Shift_F1":
					processAnimation("", print_writers, "Change_Fullframes$SplitCard$Left", "START");
					switch (whichGraphicOnScreen.split(",")[0]) {
					case "Control_Alt_F1":
						processAnimation("", print_writers, "Change_Fullframes$SplitCard$Right$SplitBowling", "START");
						break;
					case "Alt_Shift_F1":
						processAnimation("", print_writers, "Change_Fullframes$SplitCard$Right$Contribution", "START");
						break;
					}
					break;
				case "F4": 
					processAnimation("", print_writers, "Change_Fullframes$PartnershipList", "START");
					break;
				case "Control_F11":
					processAnimation("", print_writers, "Change_Fullframes$Summary", "START");
					break;
				case "Shift_K": 
					processAnimation("", print_writers, "Change_Fullframes$Partnership", "START");
					break;
				case "Shift_F10": 
					processAnimation("", print_writers, "Change_Fullframes$Worms", "START");
					break;
				case "Control_F10": 
					processAnimation("", print_writers, "Change_Fullframes$Manhattan", "START");
					break;
				case "Alt_F11":
					processAnimation("", print_writers, "Change_Fullframes$ManhattanComparison", "START");
					break;
				case "Control_F1":
					processAnimation("", print_writers, "Change_Fullframes$ImageBattingCard", "START");
					break;
				}
				
				if(!whichGraphicOnScreen.split(",")[0].equalsIgnoreCase(whatToProcess.split(",")[0])) {
					switch(whatToProcess.split(",")[0]) {
					case "F1":
						processAnimation("", print_writers, "Change_Fullframes$BattingCard", "START");
						break;
					case "F2":
						processAnimation("", print_writers, "Change_Fullframes$BowlingCard", "START");
						break;
					case "Control_F7": 
						processAnimation("", print_writers, "Change_Fullframes$Teams", "START");
						break;
					case "Shift_T": 
						processAnimation("", print_writers, "Change_Fullframes$Team", "START");
						break;
					case "Control_Alt_F1": case "Alt_Shift_F1":
						processAnimation("", print_writers, "Change_Fullframes$SplitCard$Left", "START");
						switch (whatToProcess.split(",")[0]) {
						case "Control_Alt_F1":
							processAnimation("", print_writers, "Change_Fullframes$SplitCard$Right$SplitBowling", "START");
							break;
						case "Alt_Shift_F1":
							processAnimation("", print_writers, "Change_Fullframes$SplitCard$Right$Contribution", "START");
							break;
						}
						break;
					case "F4": 
						processAnimation("", print_writers, "Change_Fullframes$PartnershipList", "START");
						break;
					case "Control_F11":
						processAnimation("", print_writers, "Change_Fullframes$Summary", "START");
						break;
					case "Shift_K": 
						processAnimation("", print_writers, "Change_Fullframes$Partnership", "START");
						break;
					case "Shift_F10": 
						processAnimation("", print_writers, "Change_Fullframes$Worms", "START");
						break;
					case "Control_F10": 
						processAnimation("", print_writers, "Change_Fullframes$Manhattan", "START");
						break;
					case "Alt_F11":
						processAnimation("", print_writers, "Change_Fullframes$ManhattanComparison", "START");
						break;
					case "Control_F1":
						processAnimation("", print_writers, "Change_Fullframes$ImageBattingCard", "START");
						break;
					}
				}
				break;
			}
			break;
		case Constants.ACC:
			switch (whatToProcess.split(",")[0]) {
			case "Shift_F12":
				processAnimation(Constants.FRONT, print_writers, "Anim_Infobar$IdentInfo_Change", "START");
				break;
			case "Alt_2":
				processAnimation(Constants.FRONT, print_writers, "Anim_Infobar$Section2$Change", "START");
				caption.this_infobarGfx.infobar.setSection2(caption.this_infobarGfx.infobar.getSection2());
				break;
			case "Alt_5":
				if (caption.this_infobarGfx.infobar.getSection5() == null || caption.this_infobarGfx.infobar.getSection5().isEmpty()) {
				    processAnimation(Constants.FRONT, print_writers, "Anim_Infobar$Section5$In_Out", "CONTINUE");
				    CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Sponsor_BallSpeed$Sponsor$Sponsor" 
							+ "*FUNCTION*Omo*vis_con SET 1\0", print_writers);
				} else {
				    processAnimation(Constants.FRONT, print_writers, (caption.this_infobarGfx.infobar.getLast_section5() != null && 
				    		!caption.this_infobarGfx.infobar.getLast_section5().isEmpty())?"Anim_Infobar$Section5$Change":"Anim_Infobar$Section5$In_Out", "START");
				    CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Sponsor_BallSpeed$Sponsor$Sponsor" 
							+ "*FUNCTION*Omo*vis_con SET 0\0", print_writers);
				}
				caption.this_infobarGfx.infobar.setLast_section5(caption.this_infobarGfx.infobar.getSection5());
				break;	
			case "Alt_7":
				processAnimation(Constants.FRONT, print_writers, "Anim_Infobar$Section3$Change", "START");
				caption.this_infobarGfx.infobar.setSection3(caption.this_infobarGfx.infobar.getSection3());
				break;	
			case "Alt_8":
				if(caption.this_infobarGfx.infobar.getSection5() != null && !caption.this_infobarGfx.infobar.getSection5().isEmpty()) {
					processAnimation(Constants.FRONT, print_writers, "Anim_Infobar$Section5$In_Out", "CONTINUE");
					caption.this_infobarGfx.infobar.setLast_section5("");
					caption.this_infobarGfx.infobar.setSection5("");
					TimeUnit.MILLISECONDS.sleep(300);
				}
				
				if (caption.this_infobarGfx.infobar.getSectionAnalytics() == null || caption.this_infobarGfx.infobar.getSectionAnalytics().isEmpty()) {
					processAnimation(Constants.FRONT, print_writers, "Anim_Infobar$Section4$In_Out", "CONTINUE");
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Sponsor_BallSpeed$Sponsor$Sponsor" 
							+ "*FUNCTION*Omo*vis_con SET 1\0", print_writers);
				} else {
					if(caption.this_infobarGfx.infobar.getLast_sectionAnalytics() != null && !caption.this_infobarGfx.infobar.getLast_sectionAnalytics().isEmpty()) {
						processAnimation(Constants.FRONT, print_writers, "Anim_Infobar$Section4$Change", "START");
					}else {
						processAnimation(Constants.FRONT, print_writers, "Anim_Infobar$Section4$In_Out", "START");
					}
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Sponsor_BallSpeed$Sponsor$Sponsor" 
							+ "*FUNCTION*Omo*vis_con SET 0\0", print_writers);
				}
				caption.this_infobarGfx.infobar.setLast_sectionAnalytics(caption.this_infobarGfx.infobar.getSectionAnalytics());
				break;
				
			case "Shift_T":
				processAnimation("", print_writers, "Wipe_BG_In_Out$Change", "START");
				
				if(audioenabled.equalsIgnoreCase("TRUE")) {
					processAnimation("", print_writers, "Audio", "START");
				}
				
				processAnimation("", print_writers, "Full_Frames$Main_Graphics$Change$Side2_In", "START");
				processAnimation("", print_writers, "Full_Frames$Main_Graphics$Change$PlayingXI_Image", "START");
				break;
				
			case "Control_d": case "Control_e": case "Shift_P": case "Shift_Q":
				processAnimation("", print_writers, "Wipe_BG_In_Out$Change", "START");
				
				if(audioenabled.equalsIgnoreCase("TRUE")) {
					processAnimation("", print_writers, "Audio", "START");
				}
				
				processAnimation("", print_writers, "Full_Frames$Main_Graphics$Change$Side2_In", "START");
				processAnimation("", print_writers, "Full_Frames$Main_Graphics$Change$Profile", "START");
				break;
			case "z": case "x": case "c": case "v": case "Control_z": case "Control_x": case "Control_Shift_Z": case "Control_Shift_Y":
				processAnimation("", print_writers, "Wipe_BG_In_Out$Change", "START");
				
				if(audioenabled.equalsIgnoreCase("TRUE")) {
					processAnimation("", print_writers, "Audio", "START");
				}
				
				processAnimation("", print_writers, "Full_Frames$Main_Graphics$Change$Side2_In", "START");
				processAnimation("", print_writers, "Full_Frames$Main_Graphics$Change$Top5", "START");
				break;
			case "F1": case "Control_Shift_F1": case "F2": case "Control_Shift_F2": case "Control_F11": case "F4":
			case "Control_p":
				processAnimation("", print_writers, "Wipe_BG_In_Out$Change", "START");
				
				if(audioenabled.equalsIgnoreCase("TRUE")) {
					processAnimation("", print_writers, "Audio", "START");
				}
				
				processAnimation("", print_writers, "Full_Frames$Main_Graphics$Change$Side2_In", "START");
				switch(whichGraphicOnScreen.split(",")[0]) {
				case "F1": case "Control_Shift_F1":
					processAnimation("", print_writers, "Full_Frames$Main_Graphics$Change$BatCard$Change_Out", "START");
					processAnimation("", print_writers, "Full_Frames$Main_Graphics$Change$BatCard$Change_In", "START");
					break;
				case "F2": case "Control_Shift_F2":
					processAnimation("", print_writers, "Full_Frames$Main_Graphics$Change$BallCard$Change_Out", "START");
					processAnimation("", print_writers, "Full_Frames$Main_Graphics$Change$BallCard$Change_In", "START");
					break;
				case "Control_F11":
					processAnimation("", print_writers, "Full_Frames$Main_Graphics$Change$Summary", "START");
					break;
				case "F4":
					processAnimation("", print_writers, "Full_Frames$Main_Graphics$Change$Partnership_Table", "START");
					break;
				case "Control_p":
					processAnimation("", print_writers, "Full_Frames$Main_Graphics$Change$Standings", "START");
					break;
				}
				
				if(!whichGraphicOnScreen.equalsIgnoreCase(whatToProcess.split(",")[0])) {
					switch(whatToProcess.split(",")[0]) {
					case "Control_F11":
						processAnimation("", print_writers, "Full_Frames$Main_Graphics$Change$Summary", "START");
						break;
					case "Control_p":
						processAnimation("", print_writers, "Full_Frames$Main_Graphics$Change$Standings", "START");
						break;
					case "F4":
						processAnimation("", print_writers, "Full_Frames$Main_Graphics$Change$Partnership_Table", "START");
						break;
					case "F1": case "Control_Shift_F1":
						processAnimation("", print_writers, "Full_Frames$Main_Graphics$Change$BatCard$Change_Out", "START");
						processAnimation("", print_writers, "Full_Frames$Main_Graphics$Change$BatCard$Change_In", "START");
						
						switch (whatToProcess.split(",")[0]) {
						case "Control_Shift_F1":
							if(caption.this_fullFramesGfx.this_ALL_FF.fullframes.getWhichhighlight().equalsIgnoreCase("PERFORMER")) {
								processAnimation("", print_writers, "Full_Frames$Main_Graphics$Change$BatCard$BatHighlight$Left$" + 
										caption.this_fullFramesGfx.this_ALL_FF.fullframes.getHighlight() + "", "START");
							}else if(caption.this_fullFramesGfx.this_ALL_FF.fullframes.getWhichhighlight().equalsIgnoreCase("PARTNERSHIP")) {
								processAnimation("", print_writers, "Full_Frames$Main_Graphics$Change$BatCard$BatHighlight$Left$" + 
										caption.this_fullFramesGfx.this_ALL_FF.fullframes.getHighlight() + "", "START");
								processAnimation("", print_writers, "Full_Frames$Main_Graphics$Change$BatCard$BatHighlight$Left$" + 
										caption.this_fullFramesGfx.this_ALL_FF.fullframes.getSecondHighlight() + "", "START");
							}
							break;
						}
						break;
					case "F2": case "Control_Shift_F2":
						processAnimation("", print_writers, "Full_Frames$Main_Graphics$Change$BallCard$Change_Out", "START");
						processAnimation("", print_writers, "Full_Frames$Main_Graphics$Change$BallCard$Change_In", "START");
						
						switch (whatToProcess.split(",")[0]) {
						case "Control_Shift_F2":
							processAnimation("", print_writers, "Full_Frames$Main_Graphics$Change$BallCard$BallHighlight$Left$" + 
									caption.this_fullFramesGfx.this_ALL_FF.fullframes.getHighlight() + "", "START");
							
							if(caption.this_fullFramesGfx.this_ALL_FF.fullframes.getSecondHighlight() != 0) {
								processAnimation("", print_writers, "Full_Frames$Main_Graphics$Change$BallCard$BallHighlight$Left$" + 
										caption.this_fullFramesGfx.this_ALL_FF.fullframes.getSecondHighlight() + "", "START");
							}
							break;
						}
						break;
					}
				}
				break;
			case "F5": case "F6": case "F8": case "F9": case "F10": case "Alt_F8": case "Control_F6": case "Control_F5": case "Control_F9": case "Shift_F6": 
			case "Shift_F3": case "Shift_F5": case "Shift_F9": case "Alt_F12": case "Control_Shift_L": case "Control_Shift_M": case "u": case "Control_a":	
			case "F7": case "F11": case "Control_s": case "Control_f": case "Control_Shift_O": case "Control_h": case "Control_F3": case "d": case "Control_Shift_B":
			case "Alt_Shift_F3": case "l": case "Alt_Shift_F4": case "Alt_d":
				
				processAnimation(Constants.FRONT, print_writers, "LT$Logo$Change", "START");
				processAnimation(Constants.FRONT, print_writers, "LT$Base$Change", "START");
				processAnimation(Constants.FRONT, print_writers, "LT$Data$Change", "START");
				
				break;
			case "Control_Shift_U": case "Control_Shift_V":
				processAnimation(Constants.FRONT, print_writers, "PopUp$Change", "START");
				break;
			 case "Control_Shift_U_change_on": case "Control_Shift_V_change_on":
				processAnimation(Constants.FRONT, print_writers, "PopUp$Change", "START");
				break;
			 case "Shift_O": case "Control_k": case "k": case "g": case "y": case "Control_y": case "h": case "Shift_F4": case "Shift_F":case "Alt_b":
			 case "Control_Shift_R": case "Control_Shift_F3": case "Control_Shift_J":
				processAnimation(Constants.FRONT, print_writers, "Bugs$Change", "START");
				break;
			 case "Shift_F1": case "Shift_F2": case "Alt_F7":
				processAnimation(Constants.FRONT, print_writers, "Mini$Change", "START");
				break;	
			}
			break;
		case Constants.BAN_AFG_SERIES:
			switch (whatToProcess.split(",")[0]) {
			case "Shift_F12":
				processAnimation(Constants.FRONT, print_writers, "Anim_Infobar$IdentInfo_Change", "START");
				break;
			case "Alt_2":
				processAnimation(Constants.FRONT, print_writers, "Anim_Infobar$Section2$Change", "START");
				caption.this_infobarGfx.infobar.setSection2(caption.this_infobarGfx.infobar.getSection2());
				break;
			case "Alt_5":
				if (caption.this_infobarGfx.infobar.getSection5() == null || caption.this_infobarGfx.infobar.getSection5().isEmpty()) {
				    processAnimation(Constants.FRONT, print_writers, "Anim_Infobar$Section3$In_Out", "CONTINUE");
//				    CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Sponsor_BallSpeed$Sponsor$Sponsor" 
//							+ "*FUNCTION*Omo*vis_con SET 1\0", print_writers);
				    CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Sponsor_BallSpeed$Sponsor$Sponsor" 
							+ "*FUNCTION*Omo*vis_con SET 0\0", print_writers);
				} else {
				    processAnimation(Constants.FRONT, print_writers, (caption.this_infobarGfx.infobar.getLast_section5() != null && 
				    		!caption.this_infobarGfx.infobar.getLast_section5().isEmpty())?"Anim_Infobar$Section3$Change":"Anim_Infobar$Section3$In_Out", "START");
				    CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Sponsor_BallSpeed$Sponsor$Sponsor" 
							+ "*FUNCTION*Omo*vis_con SET 0\0", print_writers);
				}
				caption.this_infobarGfx.infobar.setLast_section5(caption.this_infobarGfx.infobar.getSection5());
				break;
			case "Alt_8":
				if(caption.this_infobarGfx.infobar.getSection5() != null && !caption.this_infobarGfx.infobar.getSection5().isEmpty()) {
					processAnimation(Constants.FRONT, print_writers, "Anim_Infobar$Section3$In_Out", "CONTINUE");
					caption.this_infobarGfx.infobar.setLast_section5("");
					caption.this_infobarGfx.infobar.setSection5("");
					TimeUnit.MILLISECONDS.sleep(300);
				}
				
				if (caption.this_infobarGfx.infobar.getSectionAnalytics() == null || caption.this_infobarGfx.infobar.getSectionAnalytics().isEmpty()) {
					processAnimation(Constants.FRONT, print_writers, "Anim_Infobar$Section4$In_Out", "CONTINUE");
//					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Sponsor_BallSpeed$Sponsor$Sponsor" 
//							+ "*FUNCTION*Omo*vis_con SET 1\0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Sponsor_BallSpeed$Sponsor$Sponsor" 
							+ "*FUNCTION*Omo*vis_con SET 0\0", print_writers);
				} else {
					if(caption.this_infobarGfx.infobar.getLast_sectionAnalytics() != null && !caption.this_infobarGfx.infobar.getLast_sectionAnalytics().isEmpty()) {
						processAnimation(Constants.FRONT, print_writers, "Anim_Infobar$Section4$Change", "START");
					}else {
						processAnimation(Constants.FRONT, print_writers, "Anim_Infobar$Section4$In_Out", "START");
					}
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$Sponsor_BallSpeed$Sponsor$Sponsor" 
							+ "*FUNCTION*Omo*vis_con SET 0\0", print_writers);
				}
				caption.this_infobarGfx.infobar.setLast_sectionAnalytics(caption.this_infobarGfx.infobar.getSectionAnalytics());
				break;
				
			case "Shift_T":
				processAnimation("", print_writers, "Full_Frames$Main_Graphics$Change$Side2_In", "START");
				processAnimation("", print_writers, "Full_Frames$Main_Graphics$Change$PlayingXI_Image", "START");
				processAnimation("", print_writers, "Full_Frames$Sponsor$Change", "START");
				break;
				
			case "Control_d": case "Control_e": case "Shift_P": case "Shift_Q":
				processAnimation("", print_writers, "Full_Frames$Main_Graphics$Change$Side2_In", "START");
				processAnimation("", print_writers, "Full_Frames$Main_Graphics$Change$Profile", "START");
				processAnimation("", print_writers, "Full_Frames$Sponsor$Change", "START");
				break;
			case "z": case "x": case "c": case "v": case "Control_z": case "Control_x": case "Control_Shift_Z": case "Control_Shift_Y":
				processAnimation("", print_writers, "Full_Frames$Main_Graphics$Change$Side2_In", "START");
				processAnimation("", print_writers, "Full_Frames$Main_Graphics$Change$Top5", "START");
				
				processAnimation("", print_writers, "Full_Frames$Sponsor$Change", "START");
				break;
			case "F1": case "Control_Shift_F1": case "F2": case "Control_Shift_F2": case "Control_F11": case "F4":
				processAnimation("", print_writers, "Full_Frames$Sponsor$Change", "START");
				processAnimation("", print_writers, "Full_Frames$Main_Graphics$Change$Side2_In", "START");
				switch(whichGraphicOnScreen.split(",")[0]) {
				case "F1": case "Control_Shift_F1":
					processAnimation("", print_writers, "Full_Frames$Main_Graphics$Change$BatCard$Change_Out", "START");
					processAnimation("", print_writers, "Full_Frames$Main_Graphics$Change$BatCard$Change_In", "START");
					break;
				case "F2": case "Control_Shift_F2":
					processAnimation("", print_writers, "Full_Frames$Main_Graphics$Change$BallCard$Change_Out", "START");
					processAnimation("", print_writers, "Full_Frames$Main_Graphics$Change$BallCard$Change_In", "START");
					break;
				case "Control_F11":
					processAnimation("", print_writers, "Full_Frames$Main_Graphics$Change$Summary", "START");
					break;
				case "F4":
					processAnimation("", print_writers, "Full_Frames$Main_Graphics$Change$Partnership_Table", "START");
					break;
				}
				
				if(!whichGraphicOnScreen.equalsIgnoreCase(whatToProcess.split(",")[0])) {
					switch(whatToProcess.split(",")[0]) {
					case "Control_F11":
						processAnimation("", print_writers, "Full_Frames$Main_Graphics$Change$Summary", "START");
						break;
					case "F4":
						processAnimation("", print_writers, "Full_Frames$Main_Graphics$Change$Partnership_Table", "START");
						break;
					case "F1": case "Control_Shift_F1":
						processAnimation("", print_writers, "Full_Frames$Main_Graphics$Change$BatCard$Change_Out", "START");
						processAnimation("", print_writers, "Full_Frames$Main_Graphics$Change$BatCard$Change_In", "START");
						
						switch (whatToProcess.split(",")[0]) {
						case "Control_Shift_F1":
							if(caption.this_fullFramesGfx.this_ALL_FF.fullframes.getWhichhighlight().equalsIgnoreCase("PERFORMER")) {
								processAnimation("", print_writers, "Full_Frames$Main_Graphics$Change$BatCard$BatHighlight$Left$" + 
										caption.this_fullFramesGfx.this_ALL_FF.fullframes.getHighlight() + "", "START");
							}else if(caption.this_fullFramesGfx.this_ALL_FF.fullframes.getWhichhighlight().equalsIgnoreCase("PARTNERSHIP")) {
								processAnimation("", print_writers, "Full_Frames$Main_Graphics$Change$BatCard$BatHighlight$Left$" + 
										caption.this_fullFramesGfx.this_ALL_FF.fullframes.getHighlight() + "", "START");
								processAnimation("", print_writers, "Full_Frames$Main_Graphics$Change$BatCard$BatHighlight$Left$" + 
										caption.this_fullFramesGfx.this_ALL_FF.fullframes.getSecondHighlight() + "", "START");
							}
							break;
						}
						break;
					case "F2": case "Control_Shift_F2":
						processAnimation("", print_writers, "Full_Frames$Main_Graphics$Change$BallCard$Change_Out", "START");
						processAnimation("", print_writers, "Full_Frames$Main_Graphics$Change$BallCard$Change_In", "START");
						
						switch (whatToProcess.split(",")[0]) {
						case "Control_Shift_F2":
							processAnimation("", print_writers, "Full_Frames$Main_Graphics$Change$BallCard$BallHighlight$Left$" + 
									caption.this_fullFramesGfx.this_ALL_FF.fullframes.getHighlight() + "", "START");
							
							if(caption.this_fullFramesGfx.this_ALL_FF.fullframes.getSecondHighlight() != 0) {
								processAnimation("", print_writers, "Full_Frames$Main_Graphics$Change$BallCard$BallHighlight$Left$" + 
										caption.this_fullFramesGfx.this_ALL_FF.fullframes.getSecondHighlight() + "", "START");
							}
							break;
						}
						break;
					}
				}
				break;
			case "F5": case "F6": case "F8": case "F9": case "F10": case "Alt_F8":
			case "Control_F6": case "Control_F5": case "Control_F9":
			case "Shift_F6": case "Shift_F3": case "Shift_F5": case "Shift_F9": case "Alt_F12":
			case "Control_Shift_L": case "Control_Shift_M": case "u": case "Control_a":	
			case "F7": case "F11": case "Control_s": case "Control_f": case "Control_Shift_B":
			case "Control_Shift_O": case "Control_h": case "Control_F3": case "d":	
				
				processAnimation(Constants.FRONT, print_writers, "LT$Logo$Change", "START");
				processAnimation(Constants.FRONT, print_writers, "LT$Base$Change", "START");
				processAnimation(Constants.FRONT, print_writers, "LT$Data$Change", "START");
				
				break;
			case "Control_Shift_U": case "Control_Shift_V":
				processAnimation(Constants.FRONT, print_writers, "PopUp$Change", "START");
				break;
			 case "Control_Shift_U_change_on": case "Control_Shift_V_change_on":
				processAnimation(Constants.FRONT, print_writers, "PopUp$Change", "START");
				break;
			 case "Shift_O": case "Control_k": case "k": case "g": case "y": case "Control_y": case "h": case "Shift_F4": case "Shift_F":case "Alt_b":
			 case "Control_Shift_R": case "Control_Shift_F3": case "Control_Shift_J":
				processAnimation(Constants.FRONT, print_writers, "Bugs$Change", "START");
				break;
			 case "Shift_F1": case "Shift_F2": case "Alt_F7":
				processAnimation(Constants.FRONT, print_writers, "Mini$Change", "START");
				break;	
			}
			break;
		case Constants.TRI_SERIES:  case Constants.MT20:
			switch (whatToProcess.split(",")[0]) {
			case "Control_F12":
				processAnimation(Constants.FRONT, print_writers, "anim_Infobar$IdentType_Change", "START");
				break;
			case "Shift_F12":
				processAnimation(Constants.FRONT, print_writers, "anim_Infobar$Ident_Change", "START");
				break;
			case "Alt_1":
				processAnimation(Constants.FRONT, print_writers, "anim_Infobar$Section1_Change", "START");
				caption.this_infobarGfx.infobar.setLast_section1(caption.this_infobarGfx.infobar.getSection1());
				break;
			case "Alt_2":
				if (caption.this_infobarGfx.infobar.getSection2() == null || caption.this_infobarGfx.infobar.getSection2().isEmpty()) {
				    processAnimation(Constants.FRONT, print_writers, "anim_Infobar$Section_2$InOut", "CONTINUE");
				    TimeUnit.MILLISECONDS.sleep(400);
				    processAnimation(Constants.FRONT, print_writers, "anim_Infobar$Batsman_Name_Change", "START");
				    TimeUnit.MILLISECONDS.sleep(400);
				    if(caption.this_infobarGfx.infobar.getLast_section2().equalsIgnoreCase("PROJECTED_SCORE")) {
						processAnimation(Constants.FRONT, print_writers, "Extra_Popup2$InOut", "CONTINUE");
					}
				} else {
					if(caption.this_infobarGfx.infobar.getLast_section2() != null && !caption.this_infobarGfx.infobar.getLast_section2().isEmpty()) {
						processAnimation(Constants.FRONT, print_writers, "anim_Infobar$Section_2$Change_Section_2", "START");
						TimeUnit.MILLISECONDS.sleep(400);
						if(caption.this_infobarGfx.infobar.getLast_section2().equalsIgnoreCase("PROJECTED_SCORE")) {
							processAnimation(Constants.FRONT, print_writers, "Extra_Popup2$InOut", "CONTINUE");
						}else if(caption.this_infobarGfx.infobar.getSection2().equalsIgnoreCase("PROJECTED_SCORE")) {
							processAnimation(Constants.FRONT, print_writers, "Extra_Popup2$InOut", "START");
						}
						
					}else {
						processAnimation(Constants.FRONT, print_writers, "anim_Infobar$Section_2$InOut", "START");
						TimeUnit.MILLISECONDS.sleep(400);
						processAnimation(Constants.FRONT, print_writers, "anim_Infobar$Batsman_Name_Change", "START");
						TimeUnit.MILLISECONDS.sleep(400);
						if(caption.this_infobarGfx.infobar.getSection2().equalsIgnoreCase("PROJECTED_SCORE")) {
							processAnimation(Constants.FRONT, print_writers, "Extra_Popup2$InOut", "START");
						}
					}
				}
				caption.this_infobarGfx.infobar.setLast_section2(caption.this_infobarGfx.infobar.getSection2());
				break;
			case "Alt_3": case "Alt_4":
				if (caption.this_infobarGfx.infobar.getSectionLtAnalytics() != null || !caption.this_infobarGfx.infobar.getSectionLtAnalytics().isEmpty()) {
					if(caption.this_infobarGfx.infobar.getLast_sectionLtAnalytics() != null && !caption.this_infobarGfx.infobar.getLast_sectionLtAnalytics().isEmpty()) {
						processAnimation(Constants.FRONT, print_writers, "anim_Infobar$Lt_Analytics$Change", "START");
					}else {
						if(caption.this_infobarGfx.infobar.getLast_sectionAnalytics() != null && !caption.this_infobarGfx.infobar.getLast_sectionAnalytics().isEmpty()) {
							processAnimation(Constants.FRONT, print_writers, "anim_Infobar$Analytics$InOut", "CONTINUE");
							processAnimation(Constants.FRONT, print_writers, "anim_Infobar$Lt_Analytics$InOut", "CONTINUE");
							TimeUnit.MILLISECONDS.sleep(400);
							processAnimation(Constants.FRONT, print_writers, "anim_Infobar$Analytics$InOut", "SHOW 0.0");
						}else {
							processAnimation(Constants.FRONT, print_writers, "anim_Infobar$Lt_Analytics$InOut", "START");
							processAnimation(Constants.FRONT, print_writers, "Fade_For_Analytics", "START");
						}
					}
				}
				caption.this_infobarGfx.infobar.setLast_sectionLtAnalytics(caption.this_infobarGfx.infobar.getSectionLtAnalytics());
				break;
			case "Alt_5":
				if (caption.this_infobarGfx.infobar.getSection5() == null || caption.this_infobarGfx.infobar.getSection5().isEmpty()) {
				    processAnimation(Constants.FRONT, print_writers, "anim_Infobar$Section_5$InOut", "CONTINUE");
				} else {
				    processAnimation(Constants.FRONT, print_writers, (caption.this_infobarGfx.infobar.getLast_section5() != null && 
				    		!caption.this_infobarGfx.infobar.getLast_section5().isEmpty())?"anim_Infobar$Section_5$Change":"anim_Infobar$Section_5$InOut", "START");
				    TimeUnit.MILLISECONDS.sleep(400);
				}
				caption.this_infobarGfx.infobar.setLast_section5(caption.this_infobarGfx.infobar.getSection5());
				break;
			case "Alt_6":
				if (caption.this_infobarGfx.infobar.getSection4() == null || caption.this_infobarGfx.infobar.getSection4().isEmpty()) {
				    processAnimation(Constants.FRONT, print_writers, "anim_Infobar$Section_4$InOut", "CONTINUE");
				    TimeUnit.MILLISECONDS.sleep(400);
				    processAnimation(Constants.FRONT, print_writers, "anim_Infobar$Bowler_Name_Change", "START");
//				    processAnimation(Constants.FRONT, print_writers, "anim_Infobar$Section_3_Change", "START");
				} else {
					if(caption.this_infobarGfx.infobar.getLast_section4() != null && !caption.this_infobarGfx.infobar.getLast_section4().isEmpty()) {
						processAnimation(Constants.FRONT, print_writers, "anim_Infobar$Section_4$Change_Section_4", "START");
					}else {
						processAnimation(Constants.FRONT, print_writers, "anim_Infobar$Section_4$InOut", "START");
						TimeUnit.MILLISECONDS.sleep(400);
						processAnimation(Constants.FRONT, print_writers, "anim_Infobar$Bowler_Name_Change", "START");
//						processAnimation(Constants.FRONT, print_writers, "anim_Infobar$Section_3_Change", "START");
					}
				}
				caption.this_infobarGfx.infobar.setLast_section4(caption.this_infobarGfx.infobar.getSection4());
				break;
			case "Alt_7":
				processAnimation(Constants.FRONT, print_writers, "anim_Infobar$Section_3_Change", "START");
				caption.this_infobarGfx.infobar.setSection3(caption.this_infobarGfx.infobar.getSection3());
				break;
			case "Alt_8":
				if (caption.this_infobarGfx.infobar.getSectionAnalytics() == null || caption.this_infobarGfx.infobar.getSectionAnalytics().isEmpty()) {
				    processAnimation(Constants.FRONT, print_writers, "anim_Infobar$Analytics$InOut", "CONTINUE");
				    processAnimation(Constants.FRONT, print_writers, "Fade_For_Analytics", "CONTINUE");
				} else {
					if(caption.this_infobarGfx.infobar.getLast_sectionAnalytics() != null && !caption.this_infobarGfx.infobar.getLast_sectionAnalytics().isEmpty()) {
						processAnimation(Constants.FRONT, print_writers, "anim_Infobar$Analytics$Change", "START");
						TimeUnit.MILLISECONDS.sleep(500);
					}else {
						processAnimation(Constants.FRONT, print_writers, "anim_Infobar$Analytics$InOut", "START");
						processAnimation(Constants.FRONT, print_writers, "Fade_For_Analytics", "START");
					}
				}
				caption.this_infobarGfx.infobar.setLast_sectionAnalytics(caption.this_infobarGfx.infobar.getSectionAnalytics());
				break;
			
//			case "F1": case "F2": case "F4": case "Control_F11": case "Control_Shift_F1": case "Control_Shift_F2":
//				switch(whichGraphicOnScreen.split(",")[0]) {
//				case "F1":
//					if(!whatToProcess.split(",")[0].equalsIgnoreCase("Control_Shift_F1")) {
//						
//					}
//					break;
//				case "Control_Shift_F1":
//					processAnimation(Constants.BACK, print_writers, "Change$Big_Logo", "START");
//					processAnimation(Constants.BACK, print_writers, "Change$SideTeamColour", "START");
//					processAnimation(Constants.BACK, print_writers, "Change$SubTitle", "START");
//					processAnimation(Constants.BACK, print_writers, "Change$BattingCard$BatterBase", "START");
//					processAnimation(Constants.BACK, print_writers, "Change$BattingCard$Batter", "START");
//					
//					processAnimation(Constants.BACK, print_writers, "Anim_FullFrames$In_Out$SubTitle", "START");
//					processAnimation(Constants.BACK, print_writers, "Anim_FullFrames$In_Out$Logo", "START");
//					processAnimation(Constants.BACK, print_writers, "MoveForExtra", "CONTINUE REVERSE");
//					processAnimation(Constants.BACK, print_writers, "Anim_FullFrames$ExtraData$InOut", "CONTINUE");
//					break;
//				}
//				
//				if(!whichGraphicOnScreen.split(",")[0].equalsIgnoreCase(whatToProcess.split(",")[0])) {
//					switch(whatToProcess.split(",")[0]) {
//					case "Control_Shift_F1":
//						processAnimation(Constants.BACK, print_writers, "Anim_FullFrames$In_Out$SubTitle", "CONTINUE");
//						processAnimation(Constants.BACK, print_writers, "Anim_FullFrames$In_Out$Logo", "CONTINUE");
//						processAnimation(Constants.BACK, print_writers, "MoveForExtra", "START");
//						processAnimation(Constants.BACK, print_writers, "Anim_FullFrames$In_Out$Main$Batting_Card$DismissalBase", "CONTINUE");
//						processAnimation(Constants.BACK, print_writers, "Anim_FullFrames$In_Out$Main$Batting_Card$Dismissal", "CONTINUE");
//						processAnimation(Constants.BACK, print_writers, "Change$ExtraData", "CONTINUE");
//						break;
//						
//					case "F2":
//						processAnimation(Constants.BACK, print_writers, "Change$Bowling_Card", "START");
//						break;
//					}
//				}
//				break;
			case "Control_Shift_F7":
				caption.this_fullFramesGfx.this_ALL_FF.populateTeamLineUpFooter(print_writers, 2, 
					whatToProcess.split(",")[0], config, "SHOW-TOSS", matchAllData);
				processAnimation(Constants.BACK, print_writers, "Change_LineUp_Image", "CONTINUE");
				TimeUnit.MILLISECONDS.sleep(600);
				caption.this_fullFramesGfx.this_ALL_FF.populateTeamLineUpFooter(print_writers, 1, 
					whatToProcess.split(",")[0], config, "SHOW-TOSS", matchAllData);
				processAnimation(Constants.BACK, print_writers, "Change_LineUp_Image$Footer", "SHOW 0.0");
				break;

			case "Control_Shift_F1":
				switch(whichGraphicOnScreen.split(",")[0]) {
				case "F1":
					processAnimation(Constants.BACK, print_writers, "Anim_FullFrames$In_Out$Main$Batting_Card$Dismissal", "CONTINUE");
					processAnimation(Constants.BACK, print_writers, "Anim_FullFrames$In_Out$Main$Batting_Card$DismissalBase", "CONTINUE");
					TimeUnit.MILLISECONDS.sleep(1000);
					processAnimation(Constants.BACK, print_writers, "Anim_FullFrames$In_Out$ExtraData$InOut", "START");
					break;
				}
				break;
			case "Control_Shift_F2":
				switch(whichGraphicOnScreen.split(",")[0]) {
				case "F2":
					processAnimation(Constants.BACK, print_writers, "Anim_FullFrames$In_Out$Main$Bowling_Card$DataBase", "CONTINUE");
					processAnimation(Constants.BACK, print_writers, "Anim_FullFrames$In_Out$Main$Bowling_Card$BowlerDetails", "CONTINUE");
					TimeUnit.MILLISECONDS.sleep(1000);
					processAnimation(Constants.BACK, print_writers, "Anim_FullFrames$In_Out$ExtraData$InOut", "START");
					break;
				}
				break;
				
			case "F1": case "F2": case "Control_F11": case "F4": case "Control_p": case "Control_Shift_F4": case "Control_Shift_F5":
			
				switch(whatToProcess.split(",")[0]) {
				case "F1": case "F2": case "Control_F11": case "F4": case "Control_p": case "Control_Shift_F4": case "Control_Shift_F5":
					
					processAnimation(Constants.BACK, print_writers, "BaseDynamic", "START");
					processAnimation(Constants.BACK, print_writers, "BaseScaleForFooter", "START");
					
					processAnimation(Constants.BACK, print_writers, "Change$Big_Logo", "START");
					processAnimation(Constants.BACK, print_writers, "Change$SideTeamColour", "START");
					processAnimation(Constants.BACK, print_writers, "Change$Title", "START");
					processAnimation(Constants.BACK, print_writers, "Change$TitleBase", "START");
					processAnimation(Constants.BACK, print_writers, "Change$Footer", "START");
					
					switch(whatToProcess.split(",")[0]) {
					case "F1":
						processAnimation(Constants.BACK, print_writers, "Change$BattingCard$Batter", "START");
						processAnimation(Constants.BACK, print_writers, "Change$BattingCard$Dismissal", "START");
						break;
					case "F2":
						processAnimation(Constants.BACK, print_writers, "Change$Bowling_Card", "START");
						break;
					case "Control_F11":
						processAnimation(Constants.BACK, print_writers, "Change$Summary", "START");
						break;
					case "Control_p":
						processAnimation(Constants.BACK, print_writers, "Change$Standings", "START");
						break;	
					case "F4":
						processAnimation(Constants.BACK, print_writers, "Change$PartnershipList", "START");
						break;
					case "Control_Shift_F4": 
						processAnimation(Constants.BACK, print_writers, "Change$Batting_CardExtra", "START");
						break;
					case "Control_Shift_F5":
						processAnimation(Constants.BACK, print_writers, "Change$Bowling_CardExtra", "START");
						break;
					}
					break;
				}
				
				switch(whichGraphicOnScreen.split(",")[0]) {
				case "F1": case "F2": case "Control_F11":  case "F4": case "Control_Shift_F1": case "Control_p": 
				case "Control_Shift_F4": case "Control_Shift_F5":
					
					switch(whichGraphicOnScreen.split(",")[0]) {
					case "F1":
						if(whatToProcess.split(",")[0].equalsIgnoreCase("Control_Shift_F1")) {
							processAnimation(Constants.BACK, print_writers, "Change$ExtraData", "START");
						}else {
							processAnimation(Constants.BACK, print_writers, "Change$BattingCard$Batter", "START");
							processAnimation(Constants.BACK, print_writers, "Change$BattingCard$Dismissal", "START");
						}
						break;
					case "Control_Shift_F1":
						processAnimation(Constants.BACK, print_writers, "Anim_FullFrames$In_Out$ExtraData$InOut", "CONTINUE");
						processAnimation(Constants.BACK, print_writers, "MoveForExtra", "CONTINUE REVERSE");
						processAnimation(Constants.BACK, print_writers, "Change$BattingCard$BatterBase", "START");
						processAnimation(Constants.BACK, print_writers, "Change$BattingCard$Batter", "START");
						break;
					case "F2":
						if(whatToProcess.split(",")[0].equalsIgnoreCase("Control_Shift_F2")) {
							processAnimation(Constants.BACK, print_writers, "Change$ExtraData", "START");
						}else {
							processAnimation(Constants.BACK, print_writers, "Change$Bowling_Card", "START");
						}
						break;
					 case "F4":
						 if(whatToProcess.split(",")[0].equalsIgnoreCase("Control_Shift_F4")) {
							processAnimation(Constants.BACK, print_writers, "Change$ExtraData", "START");
						}else {
							processAnimation(Constants.BACK, print_writers, "Change$PartnershipList", "START");
						} 
						 break;
					 case "Control_F11":
						 processAnimation(Constants.BACK, print_writers, "Change$Summary", "START"); 
						 break;
					 case "Control_p":
						 processAnimation(Constants.BACK, print_writers, "Change$Standings", "START"); 
						 break;
					case "Control_Shift_F4": 
						processAnimation(Constants.BACK, print_writers, "Change$Batting_CardExtra", "START");
						break;
					case "Control_Shift_F5":
						processAnimation(Constants.BACK, print_writers, "Change$Bowling_CardExtra", "START");
						break;
					}
					
					if(whichGraphicOnScreen.split(",")[0].equalsIgnoreCase("Control_Shift_F1") || whichGraphicOnScreen.split(",")[0].equalsIgnoreCase("Control_Shift_F2")
							|| whichGraphicOnScreen.split(",")[0].equalsIgnoreCase("Control_Shift_F4") || whichGraphicOnScreen.split(",")[0].equalsIgnoreCase("Control_Shift_F5")) 
					{
						switch (whatToProcess.split(",")[0]) {
						case "Control_Shift_F4": case "Control_Shift_F5":
							if(whatToProcess.split(",")[0].equalsIgnoreCase("Control_Shift_F4")) {
								processAnimation(Constants.BACK, print_writers, "PlayerHighlight$BattingCardExtra$Side2$" + 
										caption.this_fullFramesGfx.this_ALL_FF.fullframes.getHighlight() + "", "START");
							}else if(whatToProcess.split(",")[0].equalsIgnoreCase("Control_Shift_F5")) {
								processAnimation(Constants.BACK, print_writers, "PlayerHighlight$BowlingCardExtra$Side2$" + 
										caption.this_fullFramesGfx.this_ALL_FF.fullframes.getHighlight() + "", "START");
							}
							break;
						case "F1": case "F2": case "F4": case "Control_F11":
							processAnimation(Constants.BACK, print_writers, "Anim_FullFrames$In_Out$SubTitle", "SHOW 3.000");
							processAnimation(Constants.BACK, print_writers, "Change$SubTitle", "START");
							processAnimation(Constants.BACK, print_writers, "MoveForExtra", "CONTINUE REVERSE");
							
							switch (whatToProcess.split(",")[0]) {
							case "F2": case "F4":
								processAnimation(Constants.BACK, print_writers, "Anim_FullFrames$In_Out$Logo", "SHOW 3.000");			
								processAnimation(Constants.BACK, print_writers, "Change$Logo", "START");
								break;
							}
							break;
						}
					}else if(whichGraphicOnScreen.split(",")[0].equalsIgnoreCase("F1") || whichGraphicOnScreen.split(",")[0].equalsIgnoreCase("F2")
							|| whichGraphicOnScreen.split(",")[0].equalsIgnoreCase("F4") || whichGraphicOnScreen.split(",")[0].equalsIgnoreCase("Control_p")) 
					{
						if(whatToProcess.split(",")[0].equalsIgnoreCase("Control_F11")) {
							processAnimation(Constants.BACK, print_writers, "Anim_FullFrames$In_Out$Logo", "CONTINUE");
						}
						else if(whatToProcess.split(",")[0].equalsIgnoreCase("Control_p")) {
							processAnimation(Constants.BACK, print_writers, "Anim_FullFrames$In_Out$Logo", "CONTINUE");
							processAnimation(Constants.BACK, print_writers, "Anim_FullFrames$In_Out$SubTitle", "CONTINUE");
						}
						else if(whatToProcess.split(",")[0].equalsIgnoreCase("Control_Shift_F4") || whatToProcess.split(",")[0].equalsIgnoreCase("Control_Shift_F5")) {
							processAnimation(Constants.BACK, print_writers, "Anim_FullFrames$In_Out$Logo", "CONTINUE");
							processAnimation(Constants.BACK, print_writers, "Anim_FullFrames$In_Out$SubTitle", "CONTINUE");
							processAnimation(Constants.BACK, print_writers, "MoveForExtra", "START");
							if(whatToProcess.split(",")[0].equalsIgnoreCase("Control_Shift_F4")) {
								processAnimation(Constants.BACK, print_writers, "PlayerHighlight$BattingCardExtra$Side2$" + 
										caption.this_fullFramesGfx.this_ALL_FF.fullframes.getHighlight() + "", "START");
							}else if(whatToProcess.split(",")[0].equalsIgnoreCase("Control_Shift_F5")) {
								processAnimation(Constants.BACK, print_writers, "PlayerHighlight$BowlingCardExtra$Side2$" + 
										caption.this_fullFramesGfx.this_ALL_FF.fullframes.getHighlight() + "", "START");
							}
						}
						else {
							processAnimation(Constants.BACK, print_writers, "Change$Logo", "START");
							processAnimation(Constants.BACK, print_writers, "Change$Big_Logo", "START");
						}
					}else if(whichGraphicOnScreen.split(",")[0].equalsIgnoreCase("Control_F11")) {
						switch (whatToProcess.split(",")[0]) {
						case "F1": case "F2": case "F4":
							processAnimation(Constants.BACK, print_writers, "Anim_FullFrames$In_Out$SubTitle", "SHOW 3.000");
							processAnimation(Constants.BACK, print_writers, "Change$SubTitle", "START");
							processAnimation(Constants.BACK, print_writers, "Anim_FullFrames$In_Out$Logo", "SHOW 3.000");			
							processAnimation(Constants.BACK, print_writers, "Change$Logo", "START");
							break;
						case "Control_p": case "Control_Shift_F4": case "Control_Shift_F5":
							processAnimation(Constants.BACK, print_writers, "Anim_FullFrames$In_Out$SubTitle", "CONTINUE");
							switch (whatToProcess.split(",")[0]) {
							case "Control_Shift_F4": case "Control_Shift_F5":
								processAnimation(Constants.BACK, print_writers, "MoveForExtra", "START");
								
								if(whatToProcess.split(",")[0].equalsIgnoreCase("Control_Shift_F4")) {
									processAnimation(Constants.BACK, print_writers, "PlayerHighlight$BattingCardExtra$Side2$" + 
											caption.this_fullFramesGfx.this_ALL_FF.fullframes.getHighlight() + "", "START");
								}else if(whatToProcess.split(",")[0].equalsIgnoreCase("Control_Shift_F5")) {
									processAnimation(Constants.BACK, print_writers, "PlayerHighlight$BowlingCardExtra$Side2$" + 
											caption.this_fullFramesGfx.this_ALL_FF.fullframes.getHighlight() + "", "START");
								}
								break;
							}
							break;
						default:
							processAnimation(Constants.BACK, print_writers, "Change$Logo", "START");
							processAnimation(Constants.BACK, print_writers, "Change$Big_Logo", "START");
							break;
						}
					}
					break;
				}
				break;
				
			case "F5": case "F6": case "Control_F6": case "Shift_F6": case "F7": case "F11": 
			case "Control_s": case "Control_f": case "u": case "Shift_F3": case "Shift_F5": 
			case "Shift_F9": case "Alt_F12": case "Control_h": case "Control_a": case "Alt_Shift_F3":
			case "Control_Shift_Q": case "Alt_F1": case "Alt_F2": case "Control_Shift_X": 
			case "Control_Shift_K": case "Control_c":
				processAnimation(Constants.FRONT, print_writers, "anim_LtChange$Headerband", "START");
				processAnimation(Constants.FRONT, print_writers, "anim_LtChange$Header", "START");
				processAnimation(Constants.FRONT, print_writers, "anim_LtChange$Score_Band", "START");
				processAnimation(Constants.FRONT, print_writers, "anim_LtChange$Score", "START");
				processAnimation(Constants.FRONT, print_writers, "anim_LtChange$Logo", "START");
				processAnimation(Constants.FRONT, print_writers, "anim_LtChange$Subline", "START");
				processAnimation(Constants.FRONT, print_writers, "anim_LtChange$Lt_Position", "START");
				processAnimation(Constants.FRONT, print_writers, "anim_LtChange$HeaderDynamic", "CONTINUE REVERSE");
				break;
				
			case "Alt_F8": case "F8": case "F10":
				processAnimation(Constants.FRONT, print_writers, "anim_NamesuperChange", "START");
				break;
				
			case "Control_F5": case "Control_F9": case "F9": case "Shift_E": case "d": case "e":  
			
				processAnimation(Constants.FRONT, print_writers, "anim_LtChange$Headerband", "START");
				processAnimation(Constants.FRONT, print_writers, "anim_LtChange$Header", "START");
//				processAnimation(Constants.FRONT, print_writers, "anim_LtChange$Score_Band", "START");
				processAnimation(Constants.FRONT, print_writers, "anim_LtChange$Score", "START");
				processAnimation(Constants.FRONT, print_writers, "anim_LtChange$Logo", "START");
				processAnimation(Constants.FRONT, print_writers, "anim_LtChange$Subline", "START");
				processAnimation(Constants.FRONT, print_writers, "anim_LtChange$Lt_Position", "START");
				
				switch (whichGraphicOnScreen.split(",")[0]) {
				case "Control_F5": case "Control_F9": case "F9": case "Shift_E": case "d": case "e":
					break;
				default:
					processAnimation(Constants.FRONT, print_writers, "anim_LtChange$HeaderDynamic", "START");
					break;
				}
				break;
			 case "Control_Shift_U": case "Control_Shift_V":
				processAnimation(Constants.FRONT, print_writers, "PopUps$Change", "START");
				break;
			 case "Control_Shift_U_change_on": case "Control_Shift_V_change_on":
				processAnimation(Constants.FRONT, print_writers, "PopUps$Change", "START");
				break;
			 case "Shift_F":
				processAnimation(Constants.FRONT, print_writers, "Bug_Change", "START");
				break;	
			}
			break;
		case Constants.BCCI:
			switch (whatToProcess.split(",")[0]) {
			case "Shift_F12":
				processAnimation(Constants.FRONT, print_writers, "anim_Change$Ident", "START");
				break;
			case "Alt_1":
				processAnimation(Constants.FRONT, print_writers, "anim_Change$Section1", "START");
				caption.this_infobarGfx.infobar.setLast_section1(caption.this_infobarGfx.infobar.getSection1());
				break;
			case "Alt_2":
				if(caption.this_infobarGfx.infobar.getSection3() == null || caption.this_infobarGfx.infobar.getSection3().isEmpty()) {
					processAnimation(Constants.FRONT, print_writers, "MoveForSection3", "CONTINUE REVERSE");
					TimeUnit.MILLISECONDS.sleep(1000);
					processAnimation(Constants.FRONT, print_writers, "anim_Infobar$Section3", "SHOW 0.0");
				}else {
					if(caption.this_infobarGfx.infobar.getLast_section3() != null && !caption.this_infobarGfx.infobar.getLast_section3().isEmpty()) {
						processAnimation(Constants.FRONT, print_writers, "anim_Change$Section3", "START");
					}else {
						processAnimation(Constants.FRONT, print_writers, "anim_Infobar$Section3", "START");
						processAnimation(Constants.FRONT, print_writers, "MoveForSection3", "START");
					}
				}
				caption.this_infobarGfx.infobar.setLast_section3(caption.this_infobarGfx.infobar.getSection3());
				break;
			case "Alt_5":
				if(caption.this_infobarGfx.infobar.getSection5() == null || caption.this_infobarGfx.infobar.getSection5().isEmpty()) {
					processAnimation(Constants.FRONT, print_writers, "MoveForSection5", "CONTINUE REVERSE");
				}else {
					if(caption.this_infobarGfx.infobar.getLast_section5() != null && !caption.this_infobarGfx.infobar.getLast_section5().isEmpty()) {
						processAnimation(Constants.FRONT, print_writers, "anim_Change$Section5", "START");
					}else {
						processAnimation(Constants.FRONT, print_writers, "anim_Infobar$Section5", "START");
						processAnimation(Constants.FRONT, print_writers, "MoveForSection5", "START");
					}
				}
				caption.this_infobarGfx.infobar.setLast_section5(caption.this_infobarGfx.infobar.getSection5());
				break;
			case "Alt_7":
				processAnimation(Constants.FRONT, print_writers, "anim_Change$Section4", "START");
				caption.this_infobarGfx.infobar.setSection4(caption.this_infobarGfx.infobar.getSection4());
				break;
			case "Alt_8":
				if(caption.this_infobarGfx.infobar.getSectionAnalytics() == null || caption.this_infobarGfx.infobar.getSectionAnalytics().isEmpty()) {
					processAnimation(Constants.FRONT, print_writers, "MoveForAnalytics", "CONTINUE REVERSE");
				}else {
					if(caption.this_infobarGfx.infobar.getLast_sectionAnalytics() != null && !caption.this_infobarGfx.infobar.getLast_sectionAnalytics().isEmpty()) {
						processAnimation(Constants.FRONT, print_writers, "anim_Change$Analytics", "START");
					}else {
						processAnimation(Constants.FRONT, print_writers, "anim_Infobar$Analytics", "START");
						processAnimation(Constants.FRONT, print_writers, "MoveForAnalytics", "START");
					}
				}
				caption.this_infobarGfx.infobar.setLast_sectionAnalytics(caption.this_infobarGfx.infobar.getSectionAnalytics());
				break;
				
			//Full framers
			case "F1": case "F2": case "F4": case "Control_F11":
				processAnimation(Constants.BACK, print_writers, "change_Fullframe$RightVeil", "START");
				processAnimation(Constants.BACK, print_writers, "change_Fullframe$TeamLogo", "START");
				processAnimation(Constants.BACK, print_writers, "change_Fullframe$TeamName", "START");
				processAnimation(Constants.BACK, print_writers, "change_Fullframe$Header", "START");
				processAnimation(Constants.BACK, print_writers, "change_Fullframe$SubHeader", "START");
				processAnimation(Constants.BACK, print_writers, "change_Fullframe$Footer", "START");
				processAnimation(Constants.BACK, print_writers, "change_Fullframe$Sponsor", "START");
				
				if(whichGraphicOnScreen.contains(",")) {
					switch (whichGraphicOnScreen.split(",")[0]) {
					case "F1": 
						processAnimation(Constants.BACK, print_writers, "change_Fullframe$Normal_BattingCard", "START");
						break;
					case "F2": 
						processAnimation(Constants.BACK, print_writers, "change_Fullframe$BowlingCard", "START");
						break;
					case "F4": 
						processAnimation(Constants.BACK, print_writers, "change_Fullframe$PartnershipList", "START");
						break;
					case "Control_F11":
						processAnimation(Constants.BACK, print_writers, "change_Fullframe$Summary", "START");
						processAnimation(Constants.BACK, print_writers, "MoveForLogo", "SHOW 0.0");
						break;
					}
				}
				TimeUnit.MILLISECONDS.sleep(500);
				if(!whichGraphicOnScreen.split(",")[0].equalsIgnoreCase(whatToProcess.split(",")[0])) {
					switch (whatToProcess.split(",")[0]) {
					case "F1": 
						processAnimation(Constants.BACK, print_writers, "change_Fullframe$Normal_BattingCard", "START");
						break;
					case "F2": 
						processAnimation(Constants.BACK, print_writers, "change_Fullframe$BowlingCard", "START");
						break;
					case "F4": 
						processAnimation(Constants.BACK, print_writers, "change_Fullframe$PartnershipList", "START");
						break;
					case "Control_F11":
						processAnimation(Constants.BACK, print_writers, "change_Fullframe$Summary", "START");
						processAnimation(Constants.BACK, print_writers, "MoveForLogo", "SHOW 1.000");
						break;
					}
				}
				break;
			}
		}
		return CricketUtil.YES;
	}
	public String CutBack(String whatToProcess,List<PrintWriter> print_writers, Configuration config) throws InterruptedException, IOException
	{
		switch (config.getBroadcaster().toUpperCase()) {
		case Constants.AFG_SL_SERIES:
			switch (whatToProcess.split(",")[0]) {
			case "Control_F12":
				processAnimation(Constants.FRONT, print_writers, "anim_Infobar$IdentType_Change", "SHOW 0.0");
				break;
			case "Shift_F12":
				processAnimation(Constants.FRONT, print_writers, "anim_Infobar$Ident_Change", "SHOW 0.0");
				break;
				
			case "Alt_1": case "Alt_2": case "Alt_5": case "Alt_6": case "Alt_7": case "Alt_8":
				switch (whatToProcess.split(",")[0]) {
				case "Alt_1":
					processAnimation(Constants.FRONT, print_writers, "anim_Infobar$Section1_Change", "SHOW 0.0");
					break;
				case "Alt_2":
					if(caption.this_infobarGfx.infobar.getLast_section2() != null && !caption.this_infobarGfx.infobar.getLast_section2().isEmpty()) {
						processAnimation(Constants.FRONT, print_writers, "anim_Infobar$Section2$Change", "SHOW 0.0");
					}
					break;
				case "Alt_5":
//					if(caption.this_infobarGfx.infobar.getSection5() != null && !caption.this_infobarGfx.infobar.getSection5().isEmpty()) {
//						processAnimation(Constants.FRONT, print_writers, "anim_Infobar$Section_5$Change", "SHOW 0.0");
//					}
					break;
				case "Alt_6":
//					TimeUnit.MILLISECONDS.sleep(4000);
//					processAnimation(Constants.FRONT, print_writers, "anim_Infobar$Bowler_Name_Change", "SHOW 0.0");
//					if(caption.this_infobarGfx.infobar.getLast_section4() != null && !caption.this_infobarGfx.infobar.getLast_section4().isEmpty()) {
//						processAnimation(Constants.FRONT, print_writers, "anim_Infobar$Section_4$Change_Section_4", "SHOW 0.0");	
//					}
//					TimeUnit.MILLISECONDS.sleep(1000);
//					processAnimation(Constants.FRONT, print_writers, "Section_3_Change	", "SHOW 0.0");
					break;
				case "Alt_7":
					processAnimation(Constants.FRONT, print_writers, "anim_Infobar$Section3_Change", "SHOW 0.0");
					break;
				case "Alt_8":
//					if(caption.this_infobarGfx.infobar.getSectionAnalytics() != null && !caption.this_infobarGfx.infobar.getSectionAnalytics().isEmpty()) {
//						processAnimation(Constants.FRONT, print_writers, "anim_Infobar$Analytics$Change", "SHOW 0.0");
//					}
					break;
				}
				break;
			
			case "Control_d": case "Control_e": case "Shift_P": case "Shift_Q":
				processAnimation("", print_writers, "Change_Profile", "SHOW 0.0");
				this.whichGraphicOnScreen = whatToProcess;
				break;
			
			case "F1": case "F2": case "Control_F7": case "Shift_T": case "Control_Alt_F1": case "Alt_Shift_F1": case "F4": 
			case "Control_F11": case "Shift_K": case "Shift_F10": case "Control_F10": case "Alt_F11": case "Control_F1":
				
				processAnimation("", print_writers, "Change_Fullframes$SubHeader", "SHOW 0.0");
				processAnimation("", print_writers, "Change_Fullframes$Header", "SHOW 0.0");
				processAnimation("", print_writers, "Change_Fullframes$Logo", "SHOW 0.0");
				processAnimation("", print_writers, "Change_Fullframes$Icon", "SHOW 0.0");
				processAnimation("", print_writers, "Change_Fullframes$VerticalText", "SHOW 0.0");
				processAnimation("", print_writers, "Change_Fullframes$Footer", "SHOW 0.0");
				
				switch(whichGraphicOnScreen.split(",")[0]) {
				case "F1":
					processAnimation("", print_writers, "Change_Fullframes$BattingCard", "SHOW 0.0");
					break;
				case "F2":
					processAnimation("", print_writers, "Change_Fullframes$BowlingCard", "SHOW 0.0");
					break;
				case "Control_F7": 
					processAnimation("", print_writers, "Change_Fullframes$Teams", "SHOW 0.0");
					break;
				case "Shift_T": 
					processAnimation("", print_writers, "Change_Fullframes$Team", "SHOW 0.0");
					break;
				case "Control_Alt_F1": case "Alt_Shift_F1":
					processAnimation("", print_writers, "Change_Fullframes$SplitCard$Left", "SHOW 0.0");
					switch (whichGraphicOnScreen.split(",")[0]) {
					case "Control_Alt_F1":
						processAnimation("", print_writers, "Change_Fullframes$SplitCard$Right$SplitBowling", "SHOW 0.0");
						break;
					case "Alt_Shift_F1":
						processAnimation("", print_writers, "Change_Fullframes$SplitCard$Right$Contribution", "SHOW 0.0");
						break;
					}
					break;
				case "F4": 
					processAnimation("", print_writers, "Change_Fullframes$PartnershipList", "SHOW 0.0");
					break;
				case "Control_F11":
					processAnimation("", print_writers, "Change_Fullframes$Summary", "SHOW 0.0");
					break;
				case "Shift_K": 
					processAnimation("", print_writers, "Change_Fullframes$Partnership", "SHOW 0.0");
					break;
				case "Shift_F10": 
					processAnimation("", print_writers, "Change_Fullframes$Worms", "SHOW 0.0");
					break;
				case "Control_F10": 
					processAnimation("", print_writers, "Change_Fullframes$Manhattan", "SHOW 0.0");
					break;
				case "Alt_F11":
					processAnimation("", print_writers, "Change_Fullframes$ManhattanComparison", "SHOW 0.0");
					break;
				case "Control_F1":
					processAnimation("", print_writers, "Change_Fullframes$ImageBattingCard", "SHOW 0.0");
					break;
				}
				
				if(!whichGraphicOnScreen.split(",")[0].equalsIgnoreCase(whatToProcess.split(",")[0])) {
					switch(whatToProcess.split(",")[0]) {
					case "F1":
						processAnimation("", print_writers, "anim_FullFrames$In_Out$Main$BattingCard", "SHOW 3.020");
						processAnimation("", print_writers, "Change_Fullframes$BattingCard", "SHOW 0.0");
						break;
					case "F2":
						processAnimation("", print_writers, "anim_FullFrames$In_Out$Main$BowlingCard", "SHOW 3.020");
						processAnimation("", print_writers, "Change_Fullframes$BowlingCard", "SHOW 0.0");
						break;
					case "Control_F7":
						processAnimation("", print_writers, "anim_FullFrames$In_Out$Main$Teams", "SHOW 3.020");
						processAnimation("", print_writers, "Change_Fullframes$Teams", "SHOW 0.0");
						break;
					case "Shift_T": 
						processAnimation("", print_writers, "anim_FullFrames$In_Out$Main$Team", "SHOW 3.020");
						processAnimation("", print_writers, "Change_Fullframes$Team", "SHOW 0.0");
						break;
					case "Control_Alt_F1": case "Alt_Shift_F1":
						processAnimation("", print_writers, "anim_FullFrames$In_Out$Main$SplitCard$Left", "SHOW 3.020");
						processAnimation("", print_writers, "Change_Fullframes$SplitCard$Left", "SHOW 0.0");
						switch (whatToProcess.split(",")[0]) {
						case "Control_Alt_F1":
							processAnimation("", print_writers, "anim_FullFrames$In_Out$Main$SplitCard$Right$SplitBowling", "SHOW 3.020");
							processAnimation("", print_writers, "Change_Fullframes$SplitCard$Right$SplitBowling", "SHOW 0.0");
							break;
						case "Alt_Shift_F1":
							processAnimation("", print_writers, "anim_FullFrames$In_Out$Main$SplitCard$Right$Contribution", "SHOW 3.020");
							processAnimation("", print_writers, "Change_Fullframes$SplitCard$Right$Contribution", "SHOW 0.0");
							break;
						}
						break;
					case "F4": 
						processAnimation("", print_writers, "anim_FullFrames$In_Out$Main$PartnershipList", "SHOW 3.020");
						processAnimation("", print_writers, "Change_Fullframes$PartnershipList", "SHOW 0.0");
						break;
					case "Control_F11":
						processAnimation("", print_writers, "anim_FullFrames$In_Out$Main$Summary", "SHOW 3.020");
						processAnimation("", print_writers, "Change_Fullframes$Summary", "SHOW 0.0");
						break;
					case "Shift_K": 
						processAnimation("", print_writers, "anim_FullFrames$In_Out$Main$Partnership", "SHOW 3.020");
						processAnimation("", print_writers, "Change_Fullframes$Partnership", "SHOW 0.0");
						break;
					case "Shift_F10": 
						processAnimation("", print_writers, "anim_FullFrames$In_Out$Main$Worm", "SHOW 3.020");
						processAnimation("", print_writers, "Change_Fullframes$Worms", "SHOW 0.0");
						break;
					case "Control_F10": 
						processAnimation("", print_writers, "anim_FullFrames$In_Out$Main$Manhattan", "SHOW 3.020");
						processAnimation("", print_writers, "Change_Fullframes$Manhattan", "SHOW 0.0");
						break;
					case "Alt_F11":
						processAnimation("", print_writers, "anim_FullFrames$In_Out$Main$ManhattanComparison", "SHOW 3.020");
						processAnimation("", print_writers, "Change_Fullframes$ManhattanComparison", "SHOW 0.0");
						break;
					case "Control_F1":
						processAnimation("", print_writers, "anim_FullFrames$In_Out$Main$ImageBattingCard", "SHOW 3.020");
						processAnimation("", print_writers, "Change_Fullframes$ImageBattingCard", "SHOW 0.0");
						break;
					}
				}
				this.whichGraphicOnScreen = whatToProcess;
				break;
			}
			break;
		case Constants.ACC:
			switch (whatToProcess.split(",")[0]) {
			case "Shift_F12":
				processAnimation(Constants.FRONT, print_writers, "Anim_Infobar$IdentInfo_Change", "SHOW 0.0");
				break;
			case "Alt_2":
				processAnimation(Constants.FRONT, print_writers, "Anim_Infobar$Section2$Change", "SHOW 0.0");
				break;
			case "Alt_5":
				if(caption.this_infobarGfx.infobar.getSection5() != null && !caption.this_infobarGfx.infobar.getSection5().isEmpty()) {
					processAnimation(Constants.FRONT, print_writers, "Anim_Infobar$Section5$Change", "SHOW 0.0");
				}
				break;	
			case "Alt_7":
				TimeUnit.MILLISECONDS.sleep(400);
				processAnimation(Constants.FRONT, print_writers, "Anim_Infobar$Section3$Change", "SHOW 0.0");
				break;	
			case "Alt_8":
				if(caption.this_infobarGfx.infobar.getSectionAnalytics() != null && !caption.this_infobarGfx.infobar.getSectionAnalytics().isEmpty()) {
					processAnimation(Constants.FRONT, print_writers, "Anim_Infobar$Section4$Change", "SHOW 0.0");
				}
				break;
				
			case "Shift_T":
				processAnimation("", print_writers, "Full_Frames$Main_Graphics$Change$PlayingXI_Image", "SHOW 0.0");
				this.whichGraphicOnScreen = whatToProcess;
				break;
				
			case "Control_d": case "Control_e": case "Shift_P": case "Shift_Q":
				processAnimation("", print_writers, "Full_Frames$Main_Graphics$Change$Profile", "SHOW 0.0");
				this.whichGraphicOnScreen = whatToProcess;
				break;
			case "z": case "x": case "c": case "v": case "Control_z": case "Control_x": case "Control_Shift_Z": case "Control_Shift_Y":
				processAnimation("", print_writers, "Full_Frames$Main_Graphics$Change$Top5", "SHOW 0.0");
				this.whichGraphicOnScreen = whatToProcess;
				break;	
			case "F1": case "Control_Shift_F1": case "F2": case "Control_Shift_F2": case "Control_F11": case "F4":
			case "Control_p":
				switch(whichGraphicOnScreen.split(",")[0]) {
				case "F1": case "Control_Shift_F1":
					processAnimation("", print_writers, "Full_Frames$Main_Graphics$Change$BatCard$Change_Out", "SHOW 0.0");
					processAnimation("", print_writers, "Full_Frames$Main_Graphics$Change$BatCard$Change_In", "SHOW 0.0");
					break;
				case "F2": case "Control_Shift_F2":
					processAnimation("", print_writers, "Full_Frames$Main_Graphics$Change$BallCard$Change_Out", "SHOW 0.0");
					processAnimation("", print_writers, "Full_Frames$Main_Graphics$Change$BallCard$Change_In", "SHOW 0.0");
					break;
				case "Control_F11":
					processAnimation("", print_writers, "Full_Frames$Main_Graphics$Change$Summary", "SHOW 0.0");
					break;
				case "F4":
					processAnimation("", print_writers, "Full_Frames$Main_Graphics$Change$Partnership_Table", "SHOW 0.0");
					break;
				case "Control_p":
					processAnimation("", print_writers, "Full_Frames$Main_Graphics$Change$Standings", "SHOW 0.0");
					break;
				}
				
				if(!whichGraphicOnScreen.equalsIgnoreCase(whatToProcess.split(",")[0])) {
					switch(whatToProcess.split(",")[0]) {
					case "Control_F11":
						processAnimation("", print_writers, "Full_Frames$Main_Graphics$In_Out$Summary$In_Out", "SHOW 2.900");
						processAnimation("", print_writers, "Full_Frames$Main_Graphics$Change$Summary", "SHOW 0.0");
						break;
					case "Control_p":
						processAnimation("", print_writers, "Full_Frames$Main_Graphics$In_Out$Standings$In_Out", "SHOW 3.500");
						processAnimation("", print_writers, "Full_Frames$Main_Graphics$Change$Standings", "SHOW 0.0");
						break;
					case "F4":
						processAnimation("", print_writers, "Full_Frames$Main_Graphics$In_Out$Partnership_Table$In_Out", "SHOW 3.640");
						processAnimation("", print_writers, "Full_Frames$Main_Graphics$Change$Partnership_Table", "SHOW 0.0");
						break;
						
					case "F1": case "Control_Shift_F1":
						processAnimation("", print_writers, "Full_Frames$Main_Graphics$In_Out$BatCard$In_Out", "SHOW 3.900");
						processAnimation("", print_writers, "Full_Frames$Main_Graphics$Change$BatCard$Change_Out", "SHOW 0.0");
						processAnimation("", print_writers, "Full_Frames$Main_Graphics$Change$BatCard$Change_In", "SHOW 0.0");
						
						switch (whatToProcess.split(",")[0]) {
						case "Control_Shift_F1":
							if(caption.this_fullFramesGfx.this_ALL_FF.fullframes.getWhichhighlight().equalsIgnoreCase("PERFORMER")) {
								if(caption.this_fullFramesGfx.this_ALL_FF.fullframes.getWhichhighlight().equalsIgnoreCase("PERFORMER")) {
									processAnimation("", print_writers, "Full_Frames$Main_Graphics$In_Out$BatCard$BatHighlight$Left$" + 
											caption.this_fullFramesGfx.this_ALL_FF.fullframes.getHighlight() + "", "SHOW 0.500");
								}
								processAnimation("", print_writers, "Full_Frames$Main_Graphics$Change$BatCard$BatHighlight", "SHOW 0.0");
							}else if(caption.this_fullFramesGfx.this_ALL_FF.fullframes.getWhichhighlight().equalsIgnoreCase("PARTNERSHIP")) {
								processAnimation("", print_writers, "Full_Frames$Main_Graphics$In_Out$BatCard$BatHighlight$Left$" + 
										caption.this_fullFramesGfx.this_ALL_FF.fullframes.getHighlight() + "", "SHOW 0.500");
								processAnimation("", print_writers, "Full_Frames$Main_Graphics$In_Out$BatCard$BatHighlight$Left$" + 
										caption.this_fullFramesGfx.this_ALL_FF.fullframes.getSecondHighlight() + "", "SHOW 0.500");
								
								processAnimation("", print_writers, "Full_Frames$Main_Graphics$Change$BatCard$BatHighlight$Left$" + 
										caption.this_fullFramesGfx.this_ALL_FF.fullframes.getHighlight() + "", "SHOW 0.0");
								processAnimation("", print_writers, "Full_Frames$Main_Graphics$Change$BatCard$BatHighlight$Left$" + 
										caption.this_fullFramesGfx.this_ALL_FF.fullframes.getSecondHighlight() + "", "SHOW 0.0");
							}
							break;
						}
						break;
					case "F2": case "Control_Shift_F2":
						processAnimation("", print_writers, "Full_Frames$Main_Graphics$In_Out$BallCard$In_Out", "SHOW 3.900");
						processAnimation("", print_writers, "Full_Frames$Main_Graphics$Change$BallCard$Change_Out", "SHOW 0.0");
						processAnimation("", print_writers, "Full_Frames$Main_Graphics$Change$BallCard$Change_In", "SHOW 0.0");
						
						switch (whatToProcess.split(",")[0]) {
						case "Control_Shift_F2":
							processAnimation("", print_writers, "Full_Frames$Main_Graphics$In_Out$BallCard$BallHighlight$Left$" + 
									caption.this_fullFramesGfx.this_ALL_FF.fullframes.getHighlight() + "", "SHOW 0.500");
							processAnimation("", print_writers, "Full_Frames$Main_Graphics$Change$BallCard$BallHighlight$Left$" + 
									caption.this_fullFramesGfx.this_ALL_FF.fullframes.getHighlight() + "", "SHOW 0.0");
							
							if(caption.this_fullFramesGfx.this_ALL_FF.fullframes.getSecondHighlight() != 0) {
								processAnimation("", print_writers, "Full_Frames$Main_Graphics$In_Out$BallCard$BallHighlight$Left$" + 
										caption.this_fullFramesGfx.this_ALL_FF.fullframes.getSecondHighlight() + "", "SHOW 0.500");
								processAnimation("", print_writers, "Full_Frames$Main_Graphics$Change$BallCard$BallHighlight$Left$" + 
										caption.this_fullFramesGfx.this_ALL_FF.fullframes.getSecondHighlight() + "", "SHOW 0.0");
							}
							
							
							break;
						}
						break;
					}
				}
				caption.captionWhichGfx = whatToProcess.split(",")[0];
				caption.this_fullFramesGfx.whichGFX = whatToProcess.split(",")[0];
				this.whichGraphicOnScreen = whatToProcess;
				break;
			case "F5": case "F6": case "F8": case "F9": case "F10": case "Alt_F8": case "Control_F6": case "Control_F5": case "Control_F9": case "Shift_F6": 
			case "Shift_F3": case "Shift_F5": case "Shift_F9": case "Alt_F12": case "Control_Shift_L": case "Control_Shift_M": case "u": case "Control_a":	
			case "F7": case "F11": case "Control_s": case "Control_f": case "Control_Shift_O": case "Control_h": case "Control_F3": case "d": case "Control_Shift_B":
			case "Alt_Shift_F3": case "l": case "Alt_Shift_F4": case "Alt_d":
//				processAnimation(Constants.FRONT, print_writers, "LT$Logo$In_Out", "SHOW 1.7");
//				processAnimation(Constants.FRONT, print_writers, "LT$Base$In_Out", "SHOW 0.72");
//				processAnimation(Constants.FRONT, print_writers, "LT$Data$In_Out", "SHOW 0.72");
				
				
				processAnimation(Constants.FRONT, print_writers, "LT$Logo$Change", "SHOW 0.0");
				processAnimation(Constants.FRONT, print_writers, "LT$Base$Change", "SHOW 0.0");
				processAnimation(Constants.FRONT, print_writers, "LT$Data$Change", "SHOW 0.0");
				this.whichGraphicOnScreen = whatToProcess;
				break;
			case "Control_Shift_U": case "Control_Shift_V":
				processAnimation(Constants.FRONT, print_writers, "PopUp$Change", "SHOW 0.0");
				this.whichGraphicOnScreen = whatToProcess;
				break;
			 case "Control_Shift_U_change_on":
				 processAnimation(Constants.FRONT, print_writers, "PopUp$Change", "SHOW 0.0");
				this.whichGraphicOnScreen = "Control_Shift_U";
				break;
			 case "Control_Shift_V_change_on":
				 processAnimation(Constants.FRONT, print_writers, "PopUp$Change", "SHOW 0.0");
				this.whichGraphicOnScreen = "Control_Shift_V";
				break;
			 case "Shift_O": case "Control_k": case "k": case "g": case "y": case "Control_y": case "h": case "Shift_F4": case "Shift_F":case "Alt_b":
			 case "Control_Shift_R": case "Control_Shift_F3": case "Control_Shift_J":
				processAnimation(Constants.FRONT, print_writers, "Bugs$Change", "SHOW 0.0");
				this.whichGraphicOnScreen = whatToProcess;
				break;
			 case "Shift_F1": case "Shift_F2": case "Alt_F7":
				processAnimation(Constants.FRONT, print_writers, "Mini$Change", "SHOW 0.0");
				this.whichGraphicOnScreen = whatToProcess;
				break;	
			}
			break;
		case Constants.BAN_AFG_SERIES:
			switch (whatToProcess.split(",")[0]) {
			case "Shift_F12":
				processAnimation(Constants.FRONT, print_writers, "Anim_Infobar$IdentInfo_Change", "SHOW 0.0");
				break;
			case "Alt_2":
				processAnimation(Constants.FRONT, print_writers, "Anim_Infobar$Section2$Change", "SHOW 0.0");
				break;
			case "Alt_5":
				if(caption.this_infobarGfx.infobar.getSection5() != null && !caption.this_infobarGfx.infobar.getSection5().isEmpty()) {
					processAnimation(Constants.FRONT, print_writers, "Anim_Infobar$Section3$Change", "SHOW 0.0");
				}
				break;
			case "Alt_8":
				if(caption.this_infobarGfx.infobar.getSectionAnalytics() != null && !caption.this_infobarGfx.infobar.getSectionAnalytics().isEmpty()) {
					processAnimation(Constants.FRONT, print_writers, "Anim_Infobar$Section4$Change", "SHOW 0.0");
				}
				break;
				
			case "Shift_T":
				processAnimation("", print_writers, "Full_Frames$Main_Graphics$Change$PlayingXI_Image", "SHOW 0.0");
				processAnimation("", print_writers, "Full_Frames$Sponsor$Change", "SHOW 0.0");
				this.whichGraphicOnScreen = whatToProcess;
				break;
				
			case "Control_d": case "Control_e": case "Shift_P": case "Shift_Q":
				processAnimation("", print_writers, "Full_Frames$Main_Graphics$Change$Profile", "SHOW 0.0");
				processAnimation("", print_writers, "Full_Frames$Sponsor$Change", "SHOW 0.0");
				this.whichGraphicOnScreen = whatToProcess;
				break;
			case "z": case "x": case "c": case "v": case "Control_z": case "Control_x": case "Control_Shift_Z": case "Control_Shift_Y":
				processAnimation("", print_writers, "Full_Frames$Main_Graphics$Change$Top5", "SHOW 0.0");
				processAnimation("", print_writers, "Full_Frames$Sponsor$Change", "SHOW 0.0");
				this.whichGraphicOnScreen = whatToProcess;
				break;	
			case "F1": case "Control_Shift_F1": case "F2": case "Control_Shift_F2": case "Control_F11": case "F4":
				processAnimation("", print_writers, "Full_Frames$Sponsor$Change", "SHOW 0.0");
				switch(whichGraphicOnScreen.split(",")[0]) {
				case "F1": case "Control_Shift_F1":
					processAnimation("", print_writers, "Full_Frames$Main_Graphics$Change$BatCard$Change_Out", "SHOW 0.0");
					processAnimation("", print_writers, "Full_Frames$Main_Graphics$Change$BatCard$Change_In", "SHOW 0.0");
					break;
				case "F2": case "Control_Shift_F2":
					processAnimation("", print_writers, "Full_Frames$Main_Graphics$Change$BallCard$Change_Out", "SHOW 0.0");
					processAnimation("", print_writers, "Full_Frames$Main_Graphics$Change$BallCard$Change_In", "SHOW 0.0");
					break;
				case "Control_F11":
					processAnimation("", print_writers, "Full_Frames$Main_Graphics$Change$Summary", "SHOW 0.0");
					break;
				case "F4":
					processAnimation("", print_writers, "Full_Frames$Main_Graphics$Change$Partnership_Table", "SHOW 0.0");
					break;
				}
				
				processAnimation("", print_writers, "Full_Frames$Sponsor$In_Out", "SHOW 3.600");
				if(!whichGraphicOnScreen.equalsIgnoreCase(whatToProcess.split(",")[0])) {
					switch(whatToProcess.split(",")[0]) {
					case "Control_F11":
						processAnimation("", print_writers, "Full_Frames$Main_Graphics$In_Out$Summary$In_Out", "SHOW 2.900");
						processAnimation("", print_writers, "Full_Frames$Main_Graphics$Change$Summary", "SHOW 0.0");
						break;
					case "F4":
						processAnimation("", print_writers, "Full_Frames$Main_Graphics$In_Out$Partnership_Table$In_Out", "SHOW 3.640");
						processAnimation("", print_writers, "Full_Frames$Main_Graphics$Change$Partnership_Table", "SHOW 0.0");
						break;
						
					case "F1": case "Control_Shift_F1":
						processAnimation("", print_writers, "Full_Frames$Main_Graphics$In_Out$BatCard$In_Out", "SHOW 3.900");
						processAnimation("", print_writers, "Full_Frames$Main_Graphics$Change$BatCard$Change_Out", "SHOW 0.0");
						processAnimation("", print_writers, "Full_Frames$Main_Graphics$Change$BatCard$Change_In", "SHOW 0.0");
						
						switch (whatToProcess.split(",")[0]) {
						case "Control_Shift_F1":
							if(caption.this_fullFramesGfx.this_ALL_FF.fullframes.getWhichhighlight().equalsIgnoreCase("PERFORMER")) {
								if(caption.this_fullFramesGfx.this_ALL_FF.fullframes.getWhichhighlight().equalsIgnoreCase("PERFORMER")) {
									processAnimation("", print_writers, "Full_Frames$Main_Graphics$In_Out$BatCard$BatHighlight$Left$" + 
											caption.this_fullFramesGfx.this_ALL_FF.fullframes.getHighlight() + "", "SHOW 0.500");
								}
								processAnimation("", print_writers, "Full_Frames$Main_Graphics$Change$BatCard$BatHighlight", "SHOW 0.0");
							}else if(caption.this_fullFramesGfx.this_ALL_FF.fullframes.getWhichhighlight().equalsIgnoreCase("PARTNERSHIP")) {
								processAnimation("", print_writers, "Full_Frames$Main_Graphics$In_Out$BatCard$BatHighlight$Left$" + 
										caption.this_fullFramesGfx.this_ALL_FF.fullframes.getHighlight() + "", "SHOW 0.500");
								processAnimation("", print_writers, "Full_Frames$Main_Graphics$In_Out$BatCard$BatHighlight$Left$" + 
										caption.this_fullFramesGfx.this_ALL_FF.fullframes.getSecondHighlight() + "", "SHOW 0.500");
								
								processAnimation("", print_writers, "Full_Frames$Main_Graphics$Change$BatCard$BatHighlight$Left$" + 
										caption.this_fullFramesGfx.this_ALL_FF.fullframes.getHighlight() + "", "SHOW 0.0");
								processAnimation("", print_writers, "Full_Frames$Main_Graphics$Change$BatCard$BatHighlight$Left$" + 
										caption.this_fullFramesGfx.this_ALL_FF.fullframes.getSecondHighlight() + "", "SHOW 0.0");
							}
							break;
						}
						break;
					case "F2": case "Control_Shift_F2":
						processAnimation("", print_writers, "Full_Frames$Main_Graphics$In_Out$BallCard$In_Out", "SHOW 3.900");
						processAnimation("", print_writers, "Full_Frames$Main_Graphics$Change$BallCard$Change_Out", "SHOW 0.0");
						processAnimation("", print_writers, "Full_Frames$Main_Graphics$Change$BallCard$Change_In", "SHOW 0.0");
						
						switch (whatToProcess.split(",")[0]) {
						case "Control_Shift_F2":
							processAnimation("", print_writers, "Full_Frames$Main_Graphics$In_Out$BallCard$BallHighlight$Left$" + 
									caption.this_fullFramesGfx.this_ALL_FF.fullframes.getHighlight() + "", "SHOW 0.500");
							processAnimation("", print_writers, "Full_Frames$Main_Graphics$Change$BallCard$BallHighlight$Left$" + 
									caption.this_fullFramesGfx.this_ALL_FF.fullframes.getHighlight() + "", "SHOW 0.0");
							
							if(caption.this_fullFramesGfx.this_ALL_FF.fullframes.getSecondHighlight() != 0) {
								processAnimation("", print_writers, "Full_Frames$Main_Graphics$In_Out$BallCard$BallHighlight$Left$" + 
										caption.this_fullFramesGfx.this_ALL_FF.fullframes.getSecondHighlight() + "", "SHOW 0.500");
								processAnimation("", print_writers, "Full_Frames$Main_Graphics$Change$BallCard$BallHighlight$Left$" + 
										caption.this_fullFramesGfx.this_ALL_FF.fullframes.getSecondHighlight() + "", "SHOW 0.0");
							}
							
							
							break;
						}
						break;
					}
				}
				caption.captionWhichGfx = whatToProcess.split(",")[0];
				caption.this_fullFramesGfx.whichGFX = whatToProcess.split(",")[0];
				this.whichGraphicOnScreen = whatToProcess;
				break;
			case "F5": case "F6": case "F8": case "F9": case "F10": case "Alt_F8":
			case "Control_F6": case "Control_F5": case "Control_F9":
			case "Shift_F6": case "Shift_F3": case "Shift_F5": case "Shift_F9": case "Alt_F12":
			case "Control_Shift_L": case "Control_Shift_M": case "u": case "Control_a":	
			case "F7": case "F11": case "Control_s": case "Control_f": case "Control_Shift_B":
			case "Control_Shift_O": case "Control_h": case "Control_F3": case "d":	
//				processAnimation(Constants.FRONT, print_writers, "LT$Logo$In_Out", "SHOW 1.7");
//				processAnimation(Constants.FRONT, print_writers, "LT$Base$In_Out", "SHOW 0.72");
//				processAnimation(Constants.FRONT, print_writers, "LT$Data$In_Out", "SHOW 0.72");
				
				
				processAnimation(Constants.FRONT, print_writers, "LT$Logo$Change", "SHOW 0.0");
				processAnimation(Constants.FRONT, print_writers, "LT$Base$Change", "SHOW 0.0");
				processAnimation(Constants.FRONT, print_writers, "LT$Data$Change", "SHOW 0.0");
				this.whichGraphicOnScreen = whatToProcess;
				break;
			case "Control_Shift_U": case "Control_Shift_V":
				processAnimation(Constants.FRONT, print_writers, "PopUp$Change", "SHOW 0.0");
				this.whichGraphicOnScreen = whatToProcess;
				break;
			 case "Control_Shift_U_change_on":
				 processAnimation(Constants.FRONT, print_writers, "PopUp$Change", "SHOW 0.0");
				this.whichGraphicOnScreen = "Control_Shift_U";
				break;
			 case "Control_Shift_V_change_on":
				 processAnimation(Constants.FRONT, print_writers, "PopUp$Change", "SHOW 0.0");
				this.whichGraphicOnScreen = "Control_Shift_V";
				break;
			 case "Shift_O": case "Control_k": case "k": case "g": case "y": case "Control_y": case "h": case "Shift_F4": case "Shift_F":case "Alt_b":
			 case "Control_Shift_R": case "Control_Shift_F3": case "Control_Shift_J":
				processAnimation(Constants.FRONT, print_writers, "Bugs$Change", "SHOW 0.0");
				this.whichGraphicOnScreen = whatToProcess;
				break;
			 case "Shift_F1": case "Shift_F2": case "Alt_F7":
				processAnimation(Constants.FRONT, print_writers, "Mini$Change", "SHOW 0.0");
				this.whichGraphicOnScreen = whatToProcess;
				break;	
			}
			break;
		case Constants.TRI_SERIES:  case Constants.MT20:
			switch (whatToProcess.split(",")[0]) {
			case "Control_F12":
				processAnimation(Constants.FRONT, print_writers, "anim_Infobar$IdentType_Change", "SHOW 0.0");
				break;
			case "Shift_F12":
				processAnimation(Constants.FRONT, print_writers, "anim_Infobar$Ident_Change", "SHOW 0.0");
				break;
			case "Alt_3": case "Alt_4":
				if (caption.this_infobarGfx.infobar.getSectionLtAnalytics() != null || !caption.this_infobarGfx.infobar.getSectionLtAnalytics().isEmpty()) {
					if(caption.this_infobarGfx.infobar.getLast_sectionLtAnalytics() != null && !caption.this_infobarGfx.infobar.getLast_sectionLtAnalytics().isEmpty()) {
						processAnimation(Constants.FRONT, print_writers, "anim_Infobar$Lt_Analytics$Change", "SHOW 0.0");
					}
				}
				break;
			case "Alt_1": case "Alt_2": case "Alt_5": case "Alt_6": case "Alt_7": case "Alt_8":
				switch (whatToProcess.split(",")[0]) {
				case "Alt_1":
					processAnimation(Constants.FRONT, print_writers, "Section1_Change", "SHOW 0.0");
					break;
				case "Alt_2":
					if(caption.this_infobarGfx.infobar.getLast_section2() != null && !caption.this_infobarGfx.infobar.getLast_section2().isEmpty()) {
						processAnimation(Constants.FRONT, print_writers, "anim_Infobar$Section_2$Change_Section_2", "SHOW 0.0");
					}
					processAnimation(Constants.FRONT, print_writers, "anim_Infobar$Batsman_Name_Change", "SHOW 0.0");
					break;
				case "Alt_5":
					if(caption.this_infobarGfx.infobar.getSection5() != null && !caption.this_infobarGfx.infobar.getSection5().isEmpty()) {
						processAnimation(Constants.FRONT, print_writers, "anim_Infobar$Section_5$Change", "SHOW 0.0");
					}
					break;
				case "Alt_6":
					TimeUnit.MILLISECONDS.sleep(4000);
					processAnimation(Constants.FRONT, print_writers, "anim_Infobar$Bowler_Name_Change", "SHOW 0.0");
					if(caption.this_infobarGfx.infobar.getLast_section4() != null && !caption.this_infobarGfx.infobar.getLast_section4().isEmpty()) {
						processAnimation(Constants.FRONT, print_writers, "anim_Infobar$Section_4$Change_Section_4", "SHOW 0.0");	
					}
//					TimeUnit.MILLISECONDS.sleep(1000);
//					processAnimation(Constants.FRONT, print_writers, "Section_3_Change	", "SHOW 0.0");
					break;
				case "Alt_7":
					processAnimation(Constants.FRONT, print_writers, "Section_3_Change	", "SHOW 0.0");
					break;
				case "Alt_8":
					if(caption.this_infobarGfx.infobar.getSectionAnalytics() != null && !caption.this_infobarGfx.infobar.getSectionAnalytics().isEmpty()) {
						TimeUnit.MILLISECONDS.sleep(1000);
						processAnimation(Constants.FRONT, print_writers, "anim_Infobar$Analytics$Change", "SHOW 0.0");
					}
					break;
				}
				break;
			case "Control_Shift_F7":
//				processAnimation(Constants.BACK, print_writers, "Anim_LineUp_Image$In_Out", "SHOW 3");
//				TimeUnit.MILLISECONDS.sleep(1000);
//				processAnimation(Constants.BACK, print_writers, "Change_LineUp_Image", "SHOW 0.0");
//				this.whichGraphicOnScreen = whatToProcess;
				break;
				
//			case "F1": case "F2": case "F4": case "Control_F11": case "Control_Shift_F1": case "Control_Shift_F2":
//				switch(whichGraphicOnScreen.split(",")[0]) {
//				case "F1":
//					if(!whatToProcess.split(",")[0].equalsIgnoreCase("Control_Shift_F1")) {
//						
//					}
//					break;
//				case "Control_Shift_F1":
//					processAnimation(Constants.BACK, print_writers, "Change$Big_Logo", "SHOW 0.0");
//					processAnimation(Constants.BACK, print_writers, "Change$SideTeamColour", "SHOW 0.0");
//					processAnimation(Constants.BACK, print_writers, "Change$SubTitle", "SHOW 0.0");
//					processAnimation(Constants.BACK, print_writers, "Change$BattingCard$BatterBase", "SHOW 0.0");
//					processAnimation(Constants.BACK, print_writers, "Change$BattingCard$Batter", "SHOW 0.0");
//					break;
//				}
//				
//				if(!whichGraphicOnScreen.split(",")[0].equalsIgnoreCase(whatToProcess.split(",")[0])) {
//					switch(whatToProcess.split(",")[0]) {
//					case "Control_Shift_F1":
//						processAnimation(Constants.BACK, print_writers, "Anim_FullFrames$ExtraData$InOut", "SHOW 1.200");
//						processAnimation(Constants.BACK, print_writers, "Change$ExtraData", "SHOW 0.0");
//						break;
//					case "F2":
//						processAnimation(Constants.BACK, print_writers, "Anim_FullFrames$Main$Bowling_Card", "SHOW 3.000");
//						processAnimation(Constants.BACK, print_writers, "Change$Bowling_Card", "SHOW 0.0");
//						break;
//					}
//				}
//				
//				caption.captionWhichGfx = whatToProcess.split(",")[0];
//				caption.this_fullFramesGfx.whichGFX = whatToProcess.split(",")[0];
//				this.whichGraphicOnScreen = whatToProcess;
//				break;
					
			case "F1": case "F2": case "Control_F11": case "F4": case "Control_p": case "Control_Shift_F4": case "Control_Shift_F5":
				
				processAnimation(Constants.BACK, print_writers, "BaseDynamic", "SHOW 0.0");
				processAnimation(Constants.BACK, print_writers, "BaseScaleForFooter", "SHOW 0.0");
				
				switch(whichGraphicOnScreen.split(",")[0]) {
				case "F1":
//					processAnimation(Constants.BACK, print_writers, "Change$BattingCard$BatterBase", "SHOW 0.0");
//					processAnimation(Constants.BACK, print_writers, "Change$BattingCard$DismissalBase", "SHOW 0.0");
					processAnimation(Constants.BACK, print_writers, "Change$BattingCard$Batter", "SHOW 0.0");
					processAnimation(Constants.BACK, print_writers, "Change$BattingCard$Dismissal", "SHOW 0.0");
					break;
				case "F2":
					processAnimation(Constants.BACK, print_writers, "Change$Bowling_Card", "SHOW 0.0");
					break;
				case "Control_F11":
					processAnimation(Constants.BACK, print_writers, "Change$Summary", "SHOW 0.0");
					break;
				case "Control_p":
					processAnimation(Constants.BACK, print_writers, "Change$Standings", "SHOW 0.0");
					break;	
				case "F4":
					processAnimation(Constants.BACK, print_writers, "Change$PartnershipList", "SHOW 0.0");
					break;
				case "Control_Shift_F4": 
					processAnimation(Constants.BACK, print_writers, "Change$Batting_CardExtra", "SHOW 0.0");
					break;
				case "Control_Shift_F5":
					processAnimation(Constants.BACK, print_writers, "Change$Bowling_CardExtra", "SHOW 0.0");
					break;
				}
				
				switch(whatToProcess.split(",")[0]) {
				case "F1":
//					processAnimation(Constants.BACK, print_writers, "Anim_FullFrames$In_Out$Main$Batting_Card$BatterBase", "SHOW 3.0");
//					processAnimation(Constants.BACK, print_writers, "Anim_FullFrames$In_Out$Main$Batting_Card$DismissalBase", "SHOW 3.0");
					processAnimation(Constants.BACK, print_writers, "Anim_FullFrames$In_Out$Main$Batting_Card$Batter", "SHOW 3.0");
					processAnimation(Constants.BACK, print_writers, "Anim_FullFrames$In_Out$Main$Batting_Card$Dismissal", "SHOW 3.0");
					processAnimation(Constants.BACK, print_writers, "Change$BattingCard$Dismissal", "SHOW 0.0");
					processAnimation(Constants.BACK, print_writers, "Anim_FullFrames$In_Out$Logo", "SHOW 3.000");
					processAnimation(Constants.BACK, print_writers, "Anim_FullFrames$In_Out$Big_Logo", "SHOW 3.000");
					processAnimation(Constants.BACK, print_writers, "Anim_FullFrames$In_Out$Title", "SHOW 3.000");
					processAnimation(Constants.BACK, print_writers, "Anim_FullFrames$In_Out$SubTitle", "SHOW 3.000");
					TimeUnit.MILLISECONDS.sleep(300);
					
//					processAnimation(Constants.BACK, print_writers, "Change$BattingCard$BatterBase", "SHOW 0.0");
//					processAnimation(Constants.BACK, print_writers, "Change$BattingCard$DismissalBase", "SHOW 0.0");
					processAnimation(Constants.BACK, print_writers, "Change$BattingCard$Batter", "SHOW 0.0");
					processAnimation(Constants.BACK, print_writers, "Change$BattingCard$Dismissal", "SHOW 0.0");
					
					if(whatToProcess.split(",")[0].equalsIgnoreCase("Control_Shift_F1")) {
						processAnimation(Constants.BACK, print_writers, "Change$ExtraData", "SHOW 0.0");
					}
					break;
				case "F2": case "Control_Shift_F2":
					processAnimation(Constants.BACK, print_writers, "Anim_FullFrames$In_Out$Main$Bowling_Card", "SHOW 3.000");
					processAnimation(Constants.BACK, print_writers, "Change$Bowling_Card", "SHOW 0.0");
					break;
				case "Control_F11":
					processAnimation(Constants.BACK, print_writers, "Anim_FullFrames$In_Out$Main$Summary", "SHOW 3.0");
					processAnimation(Constants.BACK, print_writers, "Anim_FullFrames$In_Out$Logo", "SHOW 0.000");
					processAnimation(Constants.BACK, print_writers, "Anim_FullFrames$In_Out$Big_Logo", "SHOW 3.000");
					processAnimation(Constants.BACK, print_writers, "Anim_FullFrames$In_Out$Title", "SHOW 3.000");
					processAnimation(Constants.BACK, print_writers, "Anim_FullFrames$In_Out$SubTitle", "SHOW 3.000");
					TimeUnit.MILLISECONDS.sleep(300);
					processAnimation(Constants.BACK, print_writers, "Change$Summary", "SHOW 0.0");
					break;
				case "Control_p":
					processAnimation(Constants.BACK, print_writers, "Anim_FullFrames$In_Out$Main$Standings", "SHOW 3.0");
					processAnimation(Constants.BACK, print_writers, "Anim_FullFrames$In_Out$Big_Logo", "SHOW 3.000");
					processAnimation(Constants.BACK, print_writers, "Anim_FullFrames$In_Out$Title", "SHOW 3.000");
					TimeUnit.MILLISECONDS.sleep(300);
					processAnimation(Constants.BACK, print_writers, "Change$Standings", "SHOW 0.0");
					break;	
				case "F4":
					processAnimation(Constants.BACK, print_writers, "Anim_FullFrames$In_Out$Main$PartnershipList", "SHOW 3.0");
					processAnimation(Constants.BACK, print_writers, "Anim_FullFrames$In_Out$Logo", "SHOW 3.000");
					processAnimation(Constants.BACK, print_writers, "Anim_FullFrames$In_Out$Big_Logo", "SHOW 3.000");
					processAnimation(Constants.BACK, print_writers, "Anim_FullFrames$In_Out$Title", "SHOW 3.000");
					processAnimation(Constants.BACK, print_writers, "Anim_FullFrames$In_Out$SubTitle", "SHOW 3.000");
					TimeUnit.MILLISECONDS.sleep(300);
					processAnimation(Constants.BACK, print_writers, "Change$PartnershipList", "SHOW 0.0");
					break;
				case "Control_Shift_F4":
					processAnimation(Constants.BACK, print_writers, "Anim_FullFrames$In_Out$Main$Batting_CardExtra", "SHOW 3.0");
					processAnimation(Constants.BACK, print_writers, "Anim_FullFrames$In_Out$Big_Logo", "SHOW 3.000");
					processAnimation(Constants.BACK, print_writers, "Anim_FullFrames$In_Out$Title", "SHOW 3.000");
					processAnimation(Constants.BACK, print_writers, "Change$Batting_CardExtra", "SHOW 0.0");
					processAnimation(Constants.BACK, print_writers, "PlayerHighlight$BattingCardExtra$Side1$" + 
							caption.this_fullFramesGfx.this_ALL_FF.fullframes.getHighlight() + "", "SHOW 0.500");
					processAnimation(Constants.BACK, print_writers, "PlayerHighlight$BattingCardExtra$Side2", "SHOW 0.0");
					caption.this_fullFramesGfx.this_ALL_FF.fullframes.setPreHighlight(caption.this_fullFramesGfx.this_ALL_FF.fullframes.getHighlight());
					break;
				case "Control_Shift_F5":
					processAnimation(Constants.BACK, print_writers, "Anim_FullFrames$In_Out$Main$Bowling_CardExtra", "SHOW 3.0");
					processAnimation(Constants.BACK, print_writers, "Anim_FullFrames$In_Out$Big_Logo", "SHOW 3.000");
					processAnimation(Constants.BACK, print_writers, "Anim_FullFrames$In_Out$Title", "SHOW 3.000");
					processAnimation(Constants.BACK, print_writers, "Change$Bowling_CardExtra", "SHOW 0.0");
					processAnimation(Constants.BACK, print_writers, "PlayerHighlight$BowlingCardExtra$Side1$" + 
							caption.this_fullFramesGfx.this_ALL_FF.fullframes.getHighlight() + "", "SHOW 0.500");
					processAnimation(Constants.BACK, print_writers, "PlayerHighlight$BowlingCardExtra$Side2", "SHOW 0.0");
					caption.this_fullFramesGfx.this_ALL_FF.fullframes.setPreHighlight(caption.this_fullFramesGfx.this_ALL_FF.fullframes.getHighlight());
					break;
				}
				
				processAnimation(Constants.BACK, print_writers, "Change$Logo", "SHOW 0.0");
				processAnimation(Constants.BACK, print_writers, "Change$Big_Logo", "SHOW 0.0");
				processAnimation(Constants.BACK, print_writers, "Change$SideTeamColour", "SHOW 0.0");
				
				processAnimation(Constants.BACK, print_writers, "Change$SubTitle", "SHOW 0.0");
				processAnimation(Constants.BACK, print_writers, "Change$Footer", "SHOW 0.0");
				
				processAnimation(Constants.BACK, print_writers, "Change$Title", "SHOW 0.0");
				processAnimation(Constants.BACK, print_writers, "Change$TitleBase", "SHOW 0.0");
				caption.captionWhichGfx = whatToProcess.split(",")[0];
				caption.this_fullFramesGfx.whichGFX = whatToProcess.split(",")[0];
				this.whichGraphicOnScreen = whatToProcess;
				break;
				
			case "F5": case "F6": case "Control_F6": case "Shift_F6": case "F7": case "F11": 
			case "Control_s": case "Control_f": case "u": case "Shift_F3": case "Shift_F5": 
			case "Shift_F9": case "Alt_F12": case "Control_h": case "Control_a": case "Alt_Shift_F3":
			case "Control_Shift_Q":	 case "Alt_F1": case "Alt_F2": case "Control_Shift_X": 
			case "Control_Shift_K": case "Control_c":
				processAnimation(Constants.FRONT, print_writers, "anim_LtChange$Headerband", "SHOW 0.0");
				processAnimation(Constants.FRONT, print_writers, "anim_LtChange$Header", "SHOW 0.0");
				processAnimation(Constants.FRONT, print_writers, "anim_LtChange$Score_Band", "SHOW 0.0");
				processAnimation(Constants.FRONT, print_writers, "anim_LtChange$Score", "SHOW 0.0");
				processAnimation(Constants.FRONT, print_writers, "anim_LtChange$Logo", "SHOW 0.0");
				processAnimation(Constants.FRONT, print_writers, "anim_LtChange$Subline", "SHOW 0.0");
				processAnimation(Constants.FRONT, print_writers, "anim_LtChange$Lt_Position", "SHOW 0.0");
				this.whichGraphicOnScreen = whatToProcess;
				break;
				
			case "Alt_F8": case "F8": case "F10":
				processAnimation(Constants.FRONT, print_writers, "anim_NamesuperCenterChange", "SHOW 0.0");
				break;
				
			case "Control_F5": case "Control_F9": case "F9": case "Shift_E": case "d": case "e": 
			
				processAnimation(Constants.FRONT, print_writers, "anim_LtChange$Headerband", "SHOW 0.0");
				processAnimation(Constants.FRONT, print_writers, "anim_LtChange$Header", "SHOW 0.0");
				processAnimation(Constants.FRONT, print_writers, "anim_LtChange$Score", "SHOW 0.0");
				processAnimation(Constants.FRONT, print_writers, "anim_LtChange$Logo", "SHOW 0.0");
				processAnimation(Constants.FRONT, print_writers, "anim_LtChange$Subline", "SHOW 0.0");
				processAnimation(Constants.FRONT, print_writers, "anim_LtChange$Lt_Position", "SHOW 0.0");
//				processAnimation(Constants.FRONT, print_writers, "anim_LtChange$HeaderDynamic", "SHOW 0.0");
				this.whichGraphicOnScreen = whatToProcess;
				break;
				
			 case "Control_Shift_U": case "Control_Shift_V":
				processAnimation(Constants.FRONT, print_writers, "PopUps$Change", "SHOW 0.0");
				this.whichGraphicOnScreen = whatToProcess;
				break;
			 case "Control_Shift_U_change_on":
				 processAnimation(Constants.FRONT, print_writers, "PopUps$Change", "SHOW 0.0");
				this.whichGraphicOnScreen = "Control_Shift_U";
				break;
			 case "Control_Shift_V_change_on":
				 processAnimation(Constants.FRONT, print_writers, "PopUps$Change", "SHOW 0.0");
				this.whichGraphicOnScreen = "Control_Shift_V";
				break;	
			 case "Shift_F":
				processAnimation(Constants.FRONT, print_writers, "Bug_Change", "SHOW 0.0");
				processAnimation(Constants.FRONT, print_writers, "Bug_Change$BugChange_Out", "SHOW 0.0");
				processAnimation(Constants.FRONT, print_writers, "Bug_Change$BugChange_In", "SHOW 0.0");
				this.whichGraphicOnScreen = whatToProcess;
				break;	
			}
			break;
		case Constants.BCCI:
			switch (whatToProcess.split(",")[0]) {
			case "Shift_F12":
				processAnimation(Constants.FRONT, print_writers, "anim_Change$Ident", "SHOW 0.0");
				break;
			case "Alt_1": case "Alt_2": case "Alt_7":
				TimeUnit.MILLISECONDS.sleep(1000);
				switch (whatToProcess.split(",")[0]) {
				case "Alt_1":
					processAnimation(Constants.FRONT, print_writers, "anim_Change$Section1", "SHOW 0.0");
					break;
				case "Alt_2":
					processAnimation(Constants.FRONT, print_writers, "anim_Change$Section3", "SHOW 0.0");
					break;
				case "Alt_7":
					processAnimation(Constants.FRONT, print_writers, "anim_Change$Section4", "SHOW 0.0");
					break;
				}
				break;
				
			//Full framers
			case "F1": case "F2": case "F4": case "Control_F11":
				processAnimation(Constants.BACK, print_writers, "change_Fullframe$RightVeil", "SHOW 0.0");
				processAnimation(Constants.BACK, print_writers, "change_Fullframe$TeamLogo", "SHOW 0.0");
				processAnimation(Constants.BACK, print_writers, "change_Fullframe$TeamName", "SHOW 0.0");
				processAnimation(Constants.BACK, print_writers, "change_Fullframe$Header", "SHOW 0.0");
				processAnimation(Constants.BACK, print_writers, "change_Fullframe$SubHeader", "SHOW 0.0");
				processAnimation(Constants.BACK, print_writers, "change_Fullframe$Footer", "SHOW 0.0");
				processAnimation(Constants.BACK, print_writers, "change_Fullframe$Sponsor", "SHOW 0.0");
				
				switch (whichGraphicOnScreen.split(",")[0]) {
				case "F1": 
					processAnimation(Constants.BACK, print_writers, "change_Fullframe$Normal_BattingCard", "SHOW 0.0");
					break;
				case "F2": 
					processAnimation(Constants.BACK, print_writers, "change_Fullframe$BowlingCard", "SHOW 0.0");
					break;
				case "F4": 
					processAnimation(Constants.BACK, print_writers, "change_Fullframe$PartnershipList", "SHOW 0.0");
					break;
				case "Control_F11":
					processAnimation(Constants.BACK, print_writers, "change_Fullframe$Summary", "SHOW 0.0");
					break;
				}
				
				switch (whatToProcess.split(",")[0]) {
				case "F1":
					processAnimation(Constants.BACK, print_writers, "anim_Fullframe$In_Out$Main$Normal_BattingCard", "SHOW 4.000");
					processAnimation(Constants.BACK, print_writers, "change_Fullframe$Normal_BattingCard", "SHOW 0.0");
					break;
				case "F2":
					processAnimation(Constants.BACK, print_writers, "anim_Fullframe$In_Out$Main$BowlingCard", "SHOW 4.000");
					processAnimation(Constants.BACK, print_writers, "change_Fullframe$BowlingCard", "SHOW 0.0");
					break;
				case "F4":
					processAnimation(Constants.BACK, print_writers, "anim_Fullframe$In_Out$Main$PartnershipList", "SHOW 4.000");
					processAnimation(Constants.BACK, print_writers, "change_Fullframe$PartnershipList", "SHOW 0.0");
					break;
				case "Control_F11":
					processAnimation(Constants.BACK, print_writers, "anim_Fullframe$In_Out$Main$Summary", "SHOW 4.000");
					processAnimation(Constants.BACK, print_writers, "change_Fullframe$Summary", "SHOW 0.0");
					break;
				}
				this.whichGraphicOnScreen = whatToProcess;
				break;
			}
		}
		CricketFunctions.deletePreview();
		return CricketUtil.YES;
	}
	
	public String ResetAnimation(String whatToProcess, List<PrintWriter> print_writers, Configuration config) throws InterruptedException
	{
		switch (config.getBroadcaster().toUpperCase()) {
		case Constants.AFG_SL_SERIES:
			processAnimation("", print_writers, "Loop", "SHOW 0.0");
			processAnimation("", print_writers, "AUDIO", "SHOW 0.0");
			
			processAnimation("", print_writers, "anim_Ident", "SHOW 0.0");
			
			processAnimation("", print_writers, "anim_Profile", "SHOW 0.0");
			processAnimation("", print_writers, "Change_Profile", "SHOW 0.0");
			
			processAnimation("", print_writers, "anim_FullFrames", "SHOW 0.0");
			processAnimation("", print_writers, "Change_Fullframes", "SHOW 0.0");
			processAnimation("", print_writers, "Split_CardHighlight", "SHOW 0.0");
			
			processAnimation("", print_writers, "anim_Target", "SHOW 0.0");
			
			if(whatToProcess.contains("CLEAR-ALL")) {
				processAnimation(Constants.FRONT, print_writers, "sfx_In", "SHOW 0.0");
				processAnimation(Constants.FRONT, print_writers, "sfx_Out", "SHOW 0.0");
				processAnimation(Constants.FRONT, print_writers, "sfx_Change", "SHOW 0.0");
				
				processAnimation(Constants.FRONT, print_writers, "Loop", "SHOW 0.0");
				
				processAnimation(Constants.FRONT, print_writers, "anim_Infobar", "SHOW 0.0");
				
				this.infobar.setInfobar_on_screen(false);
				this.infobar.setFreeHit_on_screen(false);
				this.infobar.setInfobar_pushed(false);
			}else if(whatToProcess.contains("CLEAR-INFOBAR_DATA")) {
				caption.this_infobarGfx.infobar.setInfobar_on_screen(false);
				caption.this_infobarGfx.infobar.setPowerplay_on_screen(false);
				
				caption.this_infobarGfx.BatterTickerName = false;
				caption.this_infobarGfx.bowlerTickerName = false;
				
				caption.this_infobarGfx.currentOnStrike = "";
				caption.this_infobarGfx.previousOnStrike = "";
				
				caption.this_infobarGfx.infobar.setSection1("");
				caption.this_infobarGfx.infobar.setSection2("");
				caption.this_infobarGfx.infobar.setSection3("");
				caption.this_infobarGfx.infobar.setSection4("");
				caption.this_infobarGfx.infobar.setSection5("");
				caption.this_infobarGfx.infobar.setSectionAnalytics("");
				
				caption.this_infobarGfx.infobar.setLast_sectionAnalytics("");
				caption.this_infobarGfx.infobar.setLast_section1("");
				caption.this_infobarGfx.infobar.setLast_section2("");
				caption.this_infobarGfx.infobar.setLast_section3("");
				caption.this_infobarGfx.infobar.setLast_section4("");
				caption.this_infobarGfx.infobar.setLast_section5("");
				
			}
			this.whichGraphicOnScreen = "";
			break;
		case Constants.BAN_AFG_SERIES: case Constants.ACC:
			processAnimation("", print_writers, "MatchID", "SHOW 0.0");
			processAnimation(Constants.BACK, print_writers, "Plotter", "SHOW 0.0");
			processAnimation("", print_writers, "Full_Frames", "SHOW 0.0");
			processAnimation("", print_writers, "Full_Frames$Main_Graphics$In_Out$Top5$In_Out$Out", "SHOW 3.800");
			
			switch (config.getBroadcaster().toUpperCase()) {
			case Constants.ACC:
				processAnimation("", print_writers, "Wipe_BG_In_Out", "SHOW 0.0");
				break;
			}
			
			if(whatToProcess.contains("CLEAR-ALL")) {
				processAnimation(Constants.FRONT, print_writers, "Anim_Infobar$Reset", "START");
				processAnimation(Constants.FRONT, print_writers, "Anim_Infobar", "SHOW 0.0");
				processAnimation(Constants.FRONT, print_writers, "Anim_Infobar$IdentOut", "SHOW 0.500");
				
				processAnimation(Constants.FRONT, print_writers, "Anim_Infobar$Section3", "SHOW 0.0");
				processAnimation(Constants.FRONT, print_writers, "Anim_Infobar$Section4$In_Out", "SHOW 0.0");
				processAnimation(Constants.FRONT, print_writers, "Anim_Infobar$Section5$In_Out", "SHOW 0.0");
				
//				processAnimation(Constants.FRONT, print_writers, "Anim_Infobar$IdentOut", "SHOW 0.0");
//				processAnimation(Constants.FRONT, print_writers, "Anim_Infobar$In$BaseIn", "SHOW 0.0");
				
				processAnimation(Constants.FRONT, print_writers, "LT$Logo$In_Out$In", "SHOW 0.0");
				processAnimation(Constants.FRONT, print_writers, "LT$Base$In_Out$In", "SHOW 0.0");
				processAnimation(Constants.FRONT, print_writers, "LT$Data$In_Out$In", "SHOW 0.0");
				
				processAnimation(Constants.FRONT, print_writers, "PopUp$Change", "SHOW 0.0");
				processAnimation(Constants.FRONT, print_writers, "PopUp$In_Out$In", "SHOW 0.0");

				processAnimation(Constants.FRONT, print_writers, "Mini", "SHOW 0.0");
				processAnimation(Constants.FRONT, print_writers, "Bugs", "SHOW 0.0");
				processAnimation(Constants.FRONT, print_writers, "SixDistance", "SHOW 0.0");
				
//				processAnimation(Constants.FRONT, print_writers, "StarLoop", "SHOW 0.0");
				this.infobar.setInfobar_on_screen(false);
				this.infobar.setFreeHit_on_screen(false);
				this.infobar.setInfobar_pushed(false);
			}else if(whatToProcess.contains("CLEAR-INFOBAR_DATA")) {
				caption.this_infobarGfx.infobar.setInfobar_status("");
			}
			this.whichGraphicOnScreen = "";
			break;
		case Constants.TRI_SERIES:  case Constants.MT20:
			processAnimation(Constants.BACK, print_writers, "anim_Ident", "SHOW 0.0");
			
			processAnimation(Constants.BACK, print_writers, "anim_Playerprofile", "SHOW 0.0");
			processAnimation(Constants.BACK, print_writers, "Change_PlayerProfile", "SHOW 0.0");
			
			processAnimation(Constants.BACK, print_writers, "Anim_FullFrames", "SHOW 0.0");
			processAnimation(Constants.BACK, print_writers, "Anim_LineUp_Image", "SHOW 0.0");
			processAnimation(Constants.BACK, print_writers, "Change_LineUp_Image", "SHOW 0.0");
			processAnimation(Constants.BACK, print_writers, "Change", "SHOW 0.0");
			processAnimation(Constants.BACK, print_writers, "MoveForExtra", "SHOW 0.0");
			processAnimation(Constants.BACK, print_writers, "PlayerHighlight", "SHOW 0.0");
			processAnimation(Constants.BACK, print_writers, "Sponsor", "SHOW 0.0");
			
			if(whatToProcess.contains("CLEAR-ALL")) {
				processAnimation(Constants.FRONT, print_writers, "anim_Infobar", "SHOW 0.0");
				
				processAnimation(Constants.FRONT, print_writers, "anim_Lower_Third", "SHOW 0.0");
				processAnimation(Constants.FRONT, print_writers, "anim_LtChange", "SHOW 0.0");
				
				processAnimation(Constants.FRONT, print_writers, "anim_Sponsor", "SHOW 0.0");
				
				processAnimation(Constants.FRONT, print_writers, "anim_Namesuper", "SHOW 0.0");
				processAnimation(Constants.FRONT, print_writers, "anim_NamesuperChange", "SHOW 0.0");
				
				processAnimation(Constants.FRONT, print_writers, "anim_NamesuperCenter", "SHOW 0.0");
				processAnimation(Constants.FRONT, print_writers, "anim_NamesuperCenterChange", "SHOW 0.0");
				
				processAnimation(Constants.FRONT, print_writers, "Loop", "SHOW 0.0");
				processAnimation(Constants.FRONT, print_writers, "EventLoop", "SHOW 0.0");
				
				processAnimation(Constants.FRONT, print_writers, "LT_MatchID", "SHOW 0.0");
				processAnimation(Constants.FRONT, print_writers, "LT_PlayingXI", "SHOW 0.0");
				processAnimation(Constants.FRONT, print_writers, "LT_Comparison", "SHOW 0.0");
				processAnimation(Constants.FRONT, print_writers, "LT_Manhattan", "SHOW 0.0");
				processAnimation(Constants.FRONT, print_writers, "LT_Weather", "SHOW 0.0");
				processAnimation(Constants.FRONT, print_writers, "LT_NextToBat", "SHOW 0.0");
				
				processAnimation(Constants.FRONT, print_writers, "Anim_Mini", "SHOW 0.0");
				processAnimation(Constants.FRONT, print_writers, "PopUps", "SHOW 0.0");
				processAnimation(Constants.FRONT, print_writers, "anim__Bug", "SHOW 0.0");
				processAnimation(Constants.FRONT, print_writers, "anim__TossBug", "SHOW 0.0");
				processAnimation(Constants.FRONT, print_writers, "Bug_Change", "SHOW 0.0");
//				processAnimation(Constants.FRONT, print_writers, "Watermark", "SHOW 0.0");
				
				this.infobar.setInfobar_on_screen(false);
				this.infobar.setFreeHit_on_screen(false);
				this.infobar.setInfobar_pushed(false);
			}else if(whatToProcess.contains("CLEAR-INFOBAR_DATA")) {
				caption.this_infobarGfx.infobar.setInfobar_on_screen(false);
				caption.this_infobarGfx.infobar.setPowerplay_on_screen(false);
				
				caption.this_infobarGfx.BatterTickerName = false;
				caption.this_infobarGfx.bowlerTickerName = false;
				
				caption.this_infobarGfx.currentOnStrike = "";
				caption.this_infobarGfx.previousOnStrike = "";
				
				caption.this_infobarGfx.infobar.setSection1("");
				caption.this_infobarGfx.infobar.setSection2("");
				caption.this_infobarGfx.infobar.setSection3("");
				caption.this_infobarGfx.infobar.setSection4("");
				caption.this_infobarGfx.infobar.setSection5("");
				caption.this_infobarGfx.infobar.setSectionAnalytics("");
				
				caption.this_infobarGfx.infobar.setLast_sectionAnalytics("");
				caption.this_infobarGfx.infobar.setLast_section1("");
				caption.this_infobarGfx.infobar.setLast_section2("");
				caption.this_infobarGfx.infobar.setLast_section3("");
				caption.this_infobarGfx.infobar.setLast_section4("");
				caption.this_infobarGfx.infobar.setLast_section5("");
				
			}
			this.whichGraphicOnScreen = "";
			break;
		case Constants.BCCI: 
			processAnimation(Constants.BACK, print_writers, "anim_Fullframe", "SHOW 0.0");
			processAnimation(Constants.BACK, print_writers, "change_Fullframe", "SHOW 0.0");
			processAnimation(Constants.BACK, print_writers, "anim_ExtraData", "SHOW 0.0");
			
			if(whatToProcess.contains("CLEAR-ALL")) {
				processAnimation(Constants.FRONT, print_writers, "anim_Infobar", "SHOW 0.0");
				
				this.infobar.setInfobar_on_screen(false);
			}
			this.whichGraphicOnScreen = "";
			break;
		}
		return CricketUtil.YES;
	}
	
	public void processAnimation(String whichLayer, List<PrintWriter> print_writers,
		String animationDirectorName, String animationCommand)
	{
		if(!whichLayer.isEmpty()) {
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*" + whichLayer + "_LAYER*STAGE*DIRECTOR*"
				+ animationDirectorName + " " + animationCommand +"\0", print_writers);
		} else {
			CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*STAGE*DIRECTOR*"
				+ animationDirectorName + " " + animationCommand +"\0", print_writers);
		}
	}
	
	public void processQuidichCommands(String whatToProcess, List<PrintWriter> print_writers, Configuration config) throws InterruptedException
	{
		switch (config.getBroadcaster().toUpperCase()) {
		case Constants.BCCI:
			switch(whatToProcess) {
			case "5": // Reset
				print_writers.get(print_writers.size()-1).printf("%s","F4");
				break;
			case "6": // Stand-By
				print_writers.get(print_writers.size()-1).printf("%s","F6");
				break;
			case "7": //Animate-In
				print_writers.get(print_writers.size()-1).printf("%s","F6");
				TimeUnit.MILLISECONDS.sleep(100);
				print_writers.get(print_writers.size()-1).printf("%s","F7");
				break;
			case "8": //Animate-Out
				print_writers.get(print_writers.size()-1).printf("%s","F9");
				TimeUnit.MILLISECONDS.sleep(1000);
				print_writers.get(print_writers.size()-1).printf("%s","F4");
				break;
			case "9": //Load	
				print_writers.get(print_writers.size()-1).printf("%s","LOAD");
				break;
			}
			break;
		}
	}
	
	public void setVariousAnimationsKeys(String whatToProcess, List<PrintWriter> print_writers, Configuration config) 
	{
		switch (config.getBroadcaster()) {
		case Constants.BCCI: 
			float MoveForExtraData, BasePositionY = 0f, obj_BiggerBase = 0f, obj__Mask_6_ = 0f, PositionY = 0f, Sponsor = 0f;
			
			switch(caption.this_fullFramesGfx.numberOfRows) {
			case 10:
				MoveForExtraData = -25f;
				BasePositionY = 25f;
				obj_BiggerBase = 690f;
				obj__Mask_6_ = 690f;
				PositionY = 50f;
				Sponsor = -330f;
				break;
			case 12:
				MoveForExtraData = 25f;
				BasePositionY = -25f;
				obj_BiggerBase = 790f;
				obj__Mask_6_ = 790f;
				PositionY = -50f;
				Sponsor = -430f;
				break;
			case 13:
				MoveForExtraData = 50f;
				BasePositionY = -50f;
				obj_BiggerBase = 840f;
				obj__Mask_6_ = 840f;
				PositionY = -100f;
				Sponsor = -480f;
				break;
			default: // 11 straps
				MoveForExtraData = 0f;
				BasePositionY = 0f;
				obj_BiggerBase = 740f;
				obj__Mask_6_ = 740f;
				PositionY = 0f;
				Sponsor = -380f;
			}
//			System.out.println("setVariousAnimationsKeys -> whatToProcess = " + whatToProcess + ": Sponsor = " + Sponsor 
//				+ ": numberOfRows = " + caption.this_fullFramesGfx.numberOfRows);
//			System.out.println("setVariousAnimationsKeys: whatToProcess = " + whatToProcess);
			switch (whatToProcess) {
			case "ANIMATE-IN":
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_Full_Frame$MoveForExtraData"
					+ "*ANIMATION*KEY*$ED_In_1*VALUE SET 0.0 " + MoveForExtraData + " 0.0 \0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_Full_Frame$MoveForExtraData"
					+ "*ANIMATION*KEY*$ED_Out_1*VALUE SET 0.0 " + MoveForExtraData + " 0.0 \0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_Full_Frame$AllGraphics$BasePositionY"
					+ "*ANIMATION*KEY*$E_In_1*VALUE SET 0.0 " + BasePositionY + " 0.0 \0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_Full_Frame$AllGraphics$BasePositionY"
					+ "*ANIMATION*KEY*$E_Out_1*VALUE SET 0.0 " + BasePositionY + " 0.0 \0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_Full_Frame$AllGraphics$obj_BiggerBase"
					+ "*ANIMATION*KEY*$BB_In_1*VALUE SET " + obj_BiggerBase + " \0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_Full_Frame$AllGraphics$obj_BiggerBase"
					+ "*ANIMATION*KEY*$BB_Out_1*VALUE SET " + obj_BiggerBase + " \0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_Full_Frame$AllGraphics$obj__Mask_6_"
					+ "*ANIMATION*KEY*$MA_In_1*VALUE SET " + obj__Mask_6_ + " \0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_Full_Frame$AllGraphics$obj__Mask_6_"
					+ "*ANIMATION*KEY*$MA_Out_1*VALUE SET " + obj__Mask_6_ + " \0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_Full_Frame$FooterAll$Footer$PositionY"
					+ "*ANIMATION*KEY*$F_In_1*VALUE SET 0.0 " + PositionY + " 0.0 \0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_Full_Frame$FooterAll$Footer$PositionY"
					+ "*ANIMATION*KEY*$F_Out_1*VALUE SET 0.0 " + PositionY + " 0.0 \0", print_writers);

				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_Full_Frame$Sponsor"
					+ "*ANIMATION*KEY*$S_In_1*VALUE SET 0.0 " + Sponsor + " 0.0 \0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_Full_Frame$Sponsor"
					+ "*ANIMATION*KEY*$In_1*VALUE SET 0.0 " + Sponsor + " 0.0 \0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_Full_Frame$Sponsor"
					+ "*ANIMATION*KEY*$In_2*VALUE SET 0.0 " + Sponsor + " 0.0 \0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_Full_Frame$Sponsor"
					+ "*ANIMATION*KEY*$Out_1*VALUE SET 0.0 " + Sponsor + " 0.0 \0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_Full_Frame$Sponsor"
					+ "*ANIMATION*KEY*$Out_2*VALUE SET 0.0 " + Sponsor + " 0.0 \0", print_writers);
				
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_Full_Frame$MoveForExtraData"
					+ "*ANIMATION*KEY*$In_1*VALUE SET 0.0 " + MoveForExtraData + " 0.0 \0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_Full_Frame$AllGraphics$BasePositionY"
					+ "*ANIMATION*KEY*$In_1*VALUE SET 0.0 " + BasePositionY + " 0.0 \0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_Full_Frame$AllGraphics$obj_BiggerBase"
					+ "*ANIMATION*KEY*$In_1*VALUE SET " + obj_BiggerBase + " \0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_Full_Frame$AllGraphics$obj__Mask_6_"
					+ "*ANIMATION*KEY*$In_1*VALUE SET " + obj__Mask_6_ + " \0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_Full_Frame$FooterAll$Footer$PositionY"
					+ "*ANIMATION*KEY*$In_1*VALUE SET 0.0 " + PositionY + " 0.0 \0", print_writers);
				
//				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_Full_Frame$Sponsor"
//					+ "*ANIMATION*KEY*$In_1*VALUE SET 0.0 " + Sponsor + " 0.0 \0", print_writers);
//				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_Full_Frame$Sponsor"
//					+ "*ANIMATION*KEY*$Out_1*VALUE SET 0.0 " + Sponsor + " 0.0 \0", print_writers);
//				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_Full_Frame$Sponsor"
//					+ "*ANIMATION*KEY*$In_2*VALUE SET 0.0 " + Sponsor + " 0.0 \0", print_writers);
			
				break;
			case "CHANGE-ON":
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_Full_Frame$MoveForExtraData"
					+ "*ANIMATION*KEY*$In_2*VALUE SET 0.0 " + MoveForExtraData + " 0.0 \0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_Full_Frame$AllGraphics$BasePositionY"
					+ "*ANIMATION*KEY*$In_2*VALUE SET 0.0 " + BasePositionY + " 0.0 \0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_Full_Frame$AllGraphics$obj_BiggerBase"
					+ "*ANIMATION*KEY*$In_2*VALUE SET " + obj_BiggerBase + " \0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_Full_Frame$AllGraphics$obj__Mask_6_"
					+ "*ANIMATION*KEY*$In_2*VALUE SET " + obj__Mask_6_ + " \0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_Full_Frame$FooterAll$Footer$PositionY"
					+ "*ANIMATION*KEY*$In_2*VALUE SET 0.0 " + PositionY + " 0.0 \0", print_writers);
				if(caption.this_fullFramesGfx.numberOfRows != lastNumberOfRows) {
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_Full_Frame$Sponsor"
							+ "*ANIMATION*KEY*$S_In_1*VALUE SET 0.0 " + Sponsor + " 0.0 \0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_Full_Frame$Sponsor"
						+ "*ANIMATION*KEY*$Out_1*VALUE SET 0.0 " + Sponsor + " 0.0 \0", print_writers);
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_Full_Frame$Sponsor"
						+ "*ANIMATION*KEY*$In_2*VALUE SET 0.0 " + Sponsor + " 0.0 \0", print_writers);
				}
				break;
			case "CUT-BACK":
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_Full_Frame$MoveForExtraData"
					+ "*ANIMATION*KEY*$In_1*VALUE SET 0.0 " + MoveForExtraData + " 0.0 \0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_Full_Frame$AllGraphics$BasePositionY"
					+ "*ANIMATION*KEY*$In_1*VALUE SET 0.0 " + BasePositionY + " 0.0 \0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_Full_Frame$AllGraphics$obj_BiggerBase"
					+ "*ANIMATION*KEY*$In_1*VALUE SET " + obj_BiggerBase + " \0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_Full_Frame$AllGraphics$obj__Mask_6_"
					+ "*ANIMATION*KEY*$In_1*VALUE SET " + obj__Mask_6_ + " \0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_Full_Frame$FooterAll$Footer$PositionY"
					+ "*ANIMATION*KEY*$In_1*VALUE SET 0.0 " + PositionY + " 0.0 \0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_Full_Frame$Sponsor"
					+ "*ANIMATION*KEY*$S_In_1*VALUE SET 0.0 " + Sponsor + " 0.0 \0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_Full_Frame$Sponsor"
					+ "*ANIMATION*KEY*$In_1*VALUE SET 0.0 " + Sponsor + " 0.0 \0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_Full_Frame$Sponsor"
					+ "*ANIMATION*KEY*$In_2*VALUE SET 0.0 " + Sponsor + " 0.0 \0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_Full_Frame$Sponsor"
					+ "*ANIMATION*KEY*$Out_1*VALUE SET 0.0 " + Sponsor + " 0.0 \0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_Full_Frame$Sponsor"
					+ "*ANIMATION*KEY*$Out_2*VALUE SET 0.0 " + Sponsor + " 0.0 \0", print_writers);
				break;
			}
			break;
		}
	}

	public void processFullFramesPreview(String whatToProcess, List<PrintWriter> print_writer, int whichside, Configuration config,String whichGraphicOnScreen) throws InterruptedException {
		if(config.getPreview().equalsIgnoreCase("WITH_PREVIEW")) {
			String previewCommand = "";
			if(whichside == 1) {
				switch (config.getBroadcaster().toUpperCase()) {
				case Constants.AFG_SL_SERIES:
					switch(whatToProcess.split(",")[0]) {
					case "m": case "Control_m":
						previewCommand = "anim_Ident$In_Out$In 2.300";
						break;
					case "Shift_D":
						previewCommand = "anim_Target$In_Out$In 2.400";
						break;
					case "Control_d": case "Control_e": case "Shift_P": case "Shift_Q":
						previewCommand = "anim_Profile$In_Out$Essentials$In 2.260 anim_Profile$In_Out$Essentials$In$Base 2.260 anim_Profile$In_Out$Essentials$In$Wipe 2.260 "
								+ "anim_Profile$In_Out$Logo$In 2.333 anim_Profile$In_Out$Profile$In 2.880";
						break;
					case "F1": case "F2": case "Control_F7": case "Control_Alt_F1": case "Alt_Shift_F1": case "F4": case "Control_F11": case "Shift_T": case "Shift_F11": 
					case "Shift_K": case "Shift_F10": case "Control_F10": case "Alt_F11": case "Control_F1":
						
						previewCommand = "anim_FullFrames$In_Out$Essentials$In 2.260 anim_FullFrames$In_Out$Essentials$In$Wipe 2.000 anim_FullFrames$In_Out$Essentials$In$Base 2.260 "
								+ "anim_FullFrames$In_Out$Header$In 2.266 anim_FullFrames$In_Out$SubHeader$In 2.066 anim_FullFrames$In_Out$Logo$In 2.333 "
								+ "anim_FullFrames$In_Out$Icon$In 2.533 anim_FullFrames$In_Out$VerticalText$In 2.066 anim_FullFrames$In_Out$Footer$In 2.966 ";
						
						switch (whatToProcess.split(",")[0]) {
						case "F1":
							previewCommand = previewCommand + "anim_FullFrames$In_Out$Main$BattingCard$In 3.006";
							break;
						case "F2":
							previewCommand = previewCommand + "anim_FullFrames$In_Out$Main$BowlingCard$In 3.006";
							break;
						case "F4":
							previewCommand = previewCommand + "anim_FullFrames$In_Out$Main$PartnershipList$In 3.006";
							break;
						case "Control_F7":
							previewCommand = previewCommand + "anim_FullFrames$In_Out$Main$Teams$In 2.946";
							break;
						case "Shift_T":
							previewCommand = previewCommand + "anim_FullFrames$In_Out$Main$Team$In 3.006";
							break;
						case "Control_F11": case "Shift_F11":
							previewCommand = previewCommand + "anim_FullFrames$In_Out$Main$Summary$In 2.826";
							break;
						case "Control_Alt_F1": case "Alt_Shift_F1":
							previewCommand = previewCommand + "anim_FullFrames$In_Out$Main$SplitCard$Left$In 3.006 ";
							switch (whatToProcess.split(",")[0]) {
							case "Control_Alt_F1":
								previewCommand = previewCommand + "anim_FullFrames$In_Out$Main$SplitCard$Right$SplitBowling$In 3.006";
								break;
							case "Alt_Shift_F1":
								previewCommand = previewCommand + "anim_FullFrames$In_Out$Main$SplitCard$Right$Contribution$In 2.520";
								break;
							}
							break;
						case "Shift_K":
							previewCommand = previewCommand + "anim_FullFrames$In_Out$Main$Partnership$In 3.000";
							break;
						case "Shift_F10":
							previewCommand = previewCommand + "anim_FullFrames$In_Out$Main$Worm$In 3.000";
							break;
						case "Control_F10":
							previewCommand = previewCommand + "anim_FullFrames$In_Out$Main$Manhattan$In 3.000";
							break;
						case "Alt_F11":
							previewCommand = previewCommand + "anim_FullFrames$In_Out$Main$ManhattanComparison$In 3.020";
							break;
						case "Control_F1":
							previewCommand = previewCommand + "anim_FullFrames$In_Out$Main$ImageBattingCard$In 2.886";
							break;
						}
						break;
					}
					break;
				case Constants.BAN_AFG_SERIES: case Constants.ACC:
					switch(whatToProcess.split(",")[0]) {
					case "m": case "Control_m":
						previewCommand = "MatchID$Start_End$In 3.600 MatchID$ALL$In 2.660";
						break;
					case "F1": case "Control_Shift_F1": case "F2": case "Control_Shift_F2": case "Control_F11": case "F4": case "Shift_F11": case "Control_F7":
					case "Shift_K": case "Shift_T": case "Shift_D": case "Control_F10": case "Control_d": case "Control_e": case "Shift_P": case "Shift_Q":
					case "Shift_F10": case "z": case "x": case "c": case "v": case "Control_z": case "Control_x": 
					case "Control_Shift_Z": case "Control_Shift_Y": case "Control_p":
						
						previewCommand = "Anim_Infobar$FFIn 0.520 Full_Frames$Essentials$In_Out$In 1.900 ";
						
						switch (whatToProcess.split(",")[0]) {
						case "z": case "x": case "c": case "v": case "Control_z": case "Control_x": 
						case "Control_Shift_Z": case "Control_Shift_Y": 
							previewCommand = previewCommand + "Full_Frames$Main_Graphics$In_Out$Top5$In_Out$In$Image 2.660 "
									+ "Full_Frames$Main_Graphics$In_Out$Top5$In_Out$In$Header 2.200 Full_Frames$Main_Graphics$In_Out$Top5$In_Out$In 2.800 "
									+ "Full_Frames$Main_Graphics$In_Out$Top5$In_Out$Out 3.100";
							break;
						case "Control_p":
							previewCommand = previewCommand + "Full_Frames$Main_Graphics$In_Out$Standings$In_Out$In 2.900";
							break;
						case "Control_F11": case "Shift_F11":
							previewCommand = previewCommand + "Full_Frames$Main_Graphics$In_Out$Summary$In_Out$In 2.900";
							break;
						case "Control_F7":
							previewCommand = previewCommand + "Full_Frames$Main_Graphics$In_Out$LineUp_Both$In_Out$In 3.600";
							break;
						case "Shift_K":
							previewCommand = previewCommand + "Full_Frames$Main_Graphics$In_Out$CurrPart$In_Out$In 2.940";
							break;
						case "Shift_T":
							previewCommand = previewCommand + "Full_Frames$Main_Graphics$In_Out$PlayingXI_Image$In_Out$In 4.200 "
									+ "Full_Frames$Main_Graphics$In_Out$PlayingXI_Image$In_Out$In$TeamEssentials_In 3.304 "
									+ "Full_Frames$Main_Graphics$In_Out$PlayingXI_Image$In_Out$In$Team 4.200";
							break;
						case "Shift_D":
							previewCommand = previewCommand + "Full_Frames$Main_Graphics$In_Out$Target$In_Out$In 2.660";
							break;
						case "Shift_F10":
							previewCommand = previewCommand + "Full_Frames$Main_Graphics$In_Out$Worm$In_Out$In 3.520";
							break;
						case "Control_F10":
							previewCommand = previewCommand + "Full_Frames$Main_Graphics$In_Out$Manhattan$In_Out$In 4.000";
							break;
						case "Control_d": case "Control_e": case "Shift_P": case "Shift_Q":
							previewCommand = previewCommand + "Full_Frames$Main_Graphics$In_Out$Profile$In_Out$In 3.880";
							break;
						case "F4":
							previewCommand = previewCommand + "Full_Frames$Main_Graphics$In_Out$Partnership_Table$In_Out$In 3.600";
							break;
						case "F1": case "Control_Shift_F1":
							previewCommand = previewCommand + "Full_Frames$Main_Graphics$In_Out$BatCard$In_Out$In 3.880";
							
							switch (whatToProcess.split(",")[0]) {
							case "Control_Shift_F1":
								if(caption.this_fullFramesGfx.this_ALL_FF.fullframes.getWhichhighlight().equalsIgnoreCase("PERFORMER")) {
									
									if(caption.this_fullFramesGfx.this_ALL_FF.fullframes.getHighlight() == 1) {
										previewCommand = previewCommand + " Full_Frames$Main_Graphics$In_Out$BatCard$BatHighlight$Left$1$" + 
												caption.this_fullFramesGfx.this_ALL_FF.fullframes.getHighlight() + " 0.500";
									}else {
										previewCommand = previewCommand + " Full_Frames$Main_Graphics$In_Out$BatCard$BatHighlight$Left$" + 
												caption.this_fullFramesGfx.this_ALL_FF.fullframes.getHighlight() + " 0.500";
									}
									
									
								}else if(caption.this_fullFramesGfx.this_ALL_FF.fullframes.getWhichhighlight().equalsIgnoreCase("PARTNERSHIP")) {
									
									if(caption.this_fullFramesGfx.this_ALL_FF.fullframes.getHighlight() == 1) {
										previewCommand = previewCommand + " Full_Frames$Main_Graphics$In_Out$BatCard$BatHighlight$Left$1$" + caption.this_fullFramesGfx.this_ALL_FF.fullframes.getHighlight() + " 0.500 "
												+ "Full_Frames$Main_Graphics$In_Out$BatCard$BatHighlight$Left$" + caption.this_fullFramesGfx.this_ALL_FF.fullframes.getSecondHighlight() + " 0.500";
									}else if(caption.this_fullFramesGfx.this_ALL_FF.fullframes.getSecondHighlight() == 1) {
										previewCommand = previewCommand + " Full_Frames$Main_Graphics$In_Out$BatCard$BatHighlight$Left$" + caption.this_fullFramesGfx.this_ALL_FF.fullframes.getHighlight() + " 0.500 "
												+ "Full_Frames$Main_Graphics$In_Out$BatCard$BatHighlight$Left$1$" + caption.this_fullFramesGfx.this_ALL_FF.fullframes.getSecondHighlight() + " 0.500";
									}else {
										previewCommand = previewCommand + " Full_Frames$Main_Graphics$In_Out$BatCard$BatHighlight$Left$" + caption.this_fullFramesGfx.this_ALL_FF.fullframes.getHighlight() + " 0.500 "
												+ "Full_Frames$Main_Graphics$In_Out$BatCard$BatHighlight$Left$" + caption.this_fullFramesGfx.this_ALL_FF.fullframes.getSecondHighlight() + " 0.500";
									}
								}
								break;
							}
							break;
						case "F2": case "Control_Shift_F2":
							previewCommand = previewCommand + "Full_Frames$Main_Graphics$In_Out$BallCard$In_Out$In 3.880";
							
							switch (whatToProcess.split(",")[0]) {
							case "Control_Shift_F2":
								
								if(caption.this_fullFramesGfx.this_ALL_FF.fullframes.getSecondHighlight() != 0) {
									previewCommand = previewCommand + " Full_Frames$Main_Graphics$In_Out$BallCard$BallHighlight$Left$" + 
											caption.this_fullFramesGfx.this_ALL_FF.fullframes.getHighlight() + " 0.500 "
											+ "Full_Frames$Main_Graphics$In_Out$BallCard$BallHighlight$Left$" + 
											caption.this_fullFramesGfx.this_ALL_FF.fullframes.getSecondHighlight() + " 0.500";
								}else {
									previewCommand = previewCommand + " Full_Frames$Main_Graphics$In_Out$BallCard$BallHighlight$Left$" + 
											caption.this_fullFramesGfx.this_ALL_FF.fullframes.getHighlight() + " 0.500";
								}
								
								break;
							}
							break;
						}
						break;
					}
					
					switch (config.getBroadcaster().toUpperCase()) {
					case Constants.ACC:
						previewCommand = previewCommand + " Wipe_BG_In_Out$In_Out 1.800 Wipe_BG_In_Out$In_Out$In 1.600";
						break;
					}
					break;
				
				case Constants.TRI_SERIES:  case Constants.MT20:
					switch(whatToProcess.split(",")[0]) {
					case "Control_F10": case "F4": case "Shift_K": case "F1": case "F2": case "Alt_F9":
					case "Control_F1": case "Control_Alt_F1": case "Alt_Shift_F1":
						previewCommand = "Anim_FullFrames 3 Anim_FullFrames$In_Out 3 Anim_FullFrames$In_Out$Essentials 3 Anim_FullFrames$In_Out$Essentials$In 1.2 "
								+ "Anim_FullFrames$In_Out$Big_Logo 3 Anim_FullFrames$In_Out$Big_Logo$In 1.5 Anim_FullFrames$In_Out$TopTitle 3 "
								+ "Anim_FullFrames$In_Out$TopTitle$In 2.1 Anim_FullFrames$In_Out$SideTeamColour 3 Anim_FullFrames$In_Out$SideTeamColour$In 1.4 "
								+ "Anim_FullFrames$In_Out$Title 3 Anim_FullFrames$In_Out$Title$In 1.9 Anim_FullFrames$In_Out$Base 3 Anim_FullFrames$In_Out$Base$In_Out 3 "
								+ "Anim_FullFrames$In_Out$Base$In_Out$In 1.6 "
								+ "Anim_FullFrames$In_Out$SubTitle 3 Anim_FullFrames$In_Out$SubTitle$In 2.4 Anim_FullFrames$In_Out$Logo 3 "
								+ "Anim_FullFrames$In_Out$Logo$In 2.88 Anim_FullFrames$In_Out$Main 3";
						break;
					case "Alt_F11": case "Control_F7": case "Shift_F11": case "Control_F11": case "Shift_F10":
						previewCommand = "Anim_FullFrames 3 Anim_FullFrames$In_Out 3 Anim_FullFrames$In_Out$Essentials 3 Anim_FullFrames$In_Out$Essentials$In 1.2 "
								+ "Anim_FullFrames$In_Out$Big_Logo 3 Anim_FullFrames$In_Out$Big_Logo$In 1.5 Anim_FullFrames$In_Out$TopTitle 3 "
								+ "Anim_FullFrames$In_Out$TopTitle$In 2.1 Anim_FullFrames$In_Out$SideTeamColour 3 Anim_FullFrames$In_Out$SideTeamColour$In 1.4 "
								+ "Anim_FullFrames$In_Out$Title 3 Anim_FullFrames$In_Out$Title$In 1.9 Anim_FullFrames$In_Out$Base 3 Anim_FullFrames$In_Out$Base$In_Out 3 "
								+ "Anim_FullFrames$In_Out$Base$In_Out$In 1.6 "
								+ "Anim_FullFrames$In_Out$SubTitle 3 Anim_FullFrames$In_Out$SubTitle$In 2.4 "
								+ "Anim_FullFrames$In_Out$Main 3";
						break;	
					case "Control_Shift_F1": case "Control_Shift_F2": case "Control_p": case "z": case "x": case "c": case "v": 
					case "Control_Shift_F4": case "Control_Shift_F5": case "Control_z": case "Control_x": case "Control_Shift_Z": case "Control_Shift_Y":
						previewCommand = "Anim_FullFrames 3 Anim_FullFrames$In_Out 3 Anim_FullFrames$In_Out$Essentials 3 Anim_FullFrames$In_Out$Essentials$In 1.2 "
								+ "Anim_FullFrames$In_Out$Big_Logo 3 Anim_FullFrames$In_Out$Big_Logo$In 1.5 Anim_FullFrames$In_Out$TopTitle 3 "
								+ "Anim_FullFrames$In_Out$TopTitle$In 2.1 Anim_FullFrames$In_Out$SideTeamColour 3 Anim_FullFrames$In_Out$SideTeamColour$In 1.4 "
								+ "Anim_FullFrames$In_Out$Title 3 Anim_FullFrames$In_Out$Title$In 1.9 Anim_FullFrames$In_Out$Base 3 Anim_FullFrames$In_Out$Base$In_Out 3 "
								+ "Anim_FullFrames$In_Out$Base$In_Out$In 1.6 "
								+ "Anim_FullFrames$In_Out$Main 3";
						break;	
					}
					
					switch(whatToProcess.split(",")[0]) {
					case "Control_Alt_F1":
						previewCommand = previewCommand + " Anim_FullFrames$In_Out$Main$Batting_Card 3 "
								+ "Anim_FullFrames$In_Out$Main$Batting_Card$BatterBase 3 Anim_FullFrames$In_Out$Main$Batting_Card$BatterBase$In 1.2 "
								+ "Anim_FullFrames$In_Out$Main$Batting_Card$Batter 3 Anim_FullFrames$In_Out$Main$Batting_Card$Batter$In 1.98 "
								+ "Anim_FullFrames$In_Out$Main$Batting_Card$Bowler 3 Anim_FullFrames$In_Out$Main$Batting_Card$Bowler$In 2.420 "
								+ "Anim_FullFrames$In_Out$Footer 3 Anim_FullFrames$In_Out$Footer$In 2.080";
						break;
					case "Control_Shift_F4":
						previewCommand = previewCommand + " Anim_FullFrames$In_Out$Main$Batting_CardExtra 3 Anim_FullFrames$In_Out$Main$Batting_CardExtra$In 2.020 "
								+ "PlayerHighlight$BattingCardExtra$Side1$" + caption.this_fullFramesGfx.this_ALL_FF.fullframes.getHighlight() + " 0.5 "
								+ "Anim_FullFrames$In_Out$Footer 3 Anim_FullFrames$In_Out$Footer$In 2.080";
						break;	
					case "Control_Shift_F5":
						previewCommand = previewCommand + " Anim_FullFrames$In_Out$Main$Bowling_CardExtra 3 Anim_FullFrames$In_Out$Main$Bowling_CardExtra$In 2.020 "
								+ "PlayerHighlight$BowlingCardExtra$Side1$" + caption.this_fullFramesGfx.this_ALL_FF.fullframes.getHighlight() + " 0.5 "
								+ "Anim_FullFrames$In_Out$Footer 3 Anim_FullFrames$In_Out$Footer$In 2.080";
						break;
					case "Alt_Shift_F1":
						previewCommand = previewCommand + " Anim_FullFrames$In_Out$Main$Batting_Card 3 Anim_FullFrames$In_Out$Logo 3 Anim_FullFrames$In_Out$Logo$In 2.880 "
								+ "Anim_FullFrames$In_Out$Main$Batting_Card$BatterBase 3 Anim_FullFrames$In_Out$Main$Batting_Card$BatterBase$In 1.2 "
								+ "Anim_FullFrames$In_Out$Main$Batting_Card$Batter 3 Anim_FullFrames$In_Out$Main$Batting_Card$Batter$In 1.98 "
								+ "Anim_FullFrames$In_Out$Main$Batting_Card$Contribution 3 Anim_FullFrames$In_Out$Main$Batting_Card$Contribution$In 2.400 "
								+ "Anim_FullFrames$In_Out$Footer 3 Anim_FullFrames$In_Out$Footer$In 2.080";
						break;
					case "Alt_F9":
						previewCommand = previewCommand + " Anim_FullFrames$In_Out$Main$TeamSingle 3 "
								+ "Anim_FullFrames$In_Out$Main$TeamSingle$In 1.920 Anim_FullFrames$In_Out$Footer 3 Anim_FullFrames$In_Out$Footer$In 2.080";
						break;
					case "z": case "x": case "c": case "v": case "Control_z": case "Control_x": case "Control_Shift_Z": case "Control_Shift_Y":
						previewCommand = previewCommand + " Anim_FullFrames$In_Out$Main$LeaderBoard 3 "
								+ "Anim_FullFrames$In_Out$Main$LeaderBoard$In 1.94";
						break;	
					case "Alt_F11":
						previewCommand = previewCommand + " Anim_FullFrames$In_Out$Logo 0 Anim_FullFrames$In_Out$Logo$In 0 Anim_FullFrames$In_Out$Main$ManhattanComparison 3 Anim_FullFrames$In_Out$Main$ManhattanComparison$In 3";
						break;
					case "Control_F1":
						previewCommand = previewCommand + " Anim_FullFrames$In_Out$Main$ImageBattingCard 3 Anim_FullFrames$In_Out$Main$ImageBattingCard$In 2.460 "
								+ " Anim_FullFrames$In_Out$Footer 3 Anim_FullFrames$In_Out$Footer$In 2.080";
						break;
					case "Control_F10":
						previewCommand = previewCommand + " Anim_FullFrames$In_Out$Main$Manhattan 3 "
								+ "Anim_FullFrames$In_Out$Main$Manhattan$In 2.9 "
								+ "Anim_FullFrames$In_Out$Footer 3 Anim_FullFrames$In_Out$Footer$In 2.080";
						
						break;
					case "Shift_F10":
						previewCommand = previewCommand + " Anim_FullFrames$In_Out$Main$Worm 3 "
								+ "Anim_FullFrames$In_Out$Main$Worm$In 2.180 Anim_FullFrames$In_Out$Main$Worm$In$Runs 2.180 "
								+ "Anim_FullFrames$In_Out$Footer 3 Anim_FullFrames$In_Out$Footer$In 2.080";
						
						break;	
					case "F4":
						previewCommand = previewCommand + " Anim_FullFrames$In_Out$Main$PartnershipList 3 "
								+ "Anim_FullFrames$In_Out$Main$PartnershipList$In 1.980 "
								+ "Anim_FullFrames$In_Out$Footer 3 Anim_FullFrames$In_Out$Footer$In 2.080";
						
						break;
					case "Shift_K":
						previewCommand = previewCommand + " Anim_FullFrames$In_Out$Main$Partnership 3 "
								+ "Anim_FullFrames$In_Out$Main$Partnership$In 1.5";
						
						break;	
					case "Control_F7":
						previewCommand = previewCommand + " Anim_FullFrames$In_Out$Logo 0 Anim_FullFrames$In_Out$Logo$In 0 "
								+ "Anim_FullFrames$In_Out$Main$Teams 3 "
								+ "Anim_FullFrames$In_Out$Main$Teams$In 2.360 "
								+ "Anim_FullFrames$In_Out$Footer 3 Anim_FullFrames$In_Out$Footer$In 2.080";
						
						break;
					case "F1":
						previewCommand = previewCommand + " Anim_FullFrames$In_Out$Main$Batting_Card 3 "
								+ "Anim_FullFrames$In_Out$Main$Batting_Card$BatterBase 3 Anim_FullFrames$In_Out$Main$Batting_Card$BatterBase$In 1.2 "
								+ "Anim_FullFrames$In_Out$Main$Batting_Card$DismissalBase 3 Anim_FullFrames$In_Out$Main$Batting_Card$DismissalBase$In 1.7 "
								+ "Anim_FullFrames$In_Out$Main$Batting_Card$Batter 3 Anim_FullFrames$In_Out$Main$Batting_Card$Batter$In 1.98 "
								+ "Anim_FullFrames$In_Out$Main$Batting_Card$Dismissal 3 Anim_FullFrames$In_Out$Main$Batting_Card$Dismissal$In 2.48 "
								+ "Anim_FullFrames$In_Out$Footer 3 Anim_FullFrames$In_Out$Footer$In 2.080";
						
						break;
					case "Control_Shift_F1":
						
						if(caption.this_fullFramesGfx.this_ALL_FF.fullframes.getWhichhighlight().equalsIgnoreCase("PERFORMER")) {
							previewCommand = previewCommand + " Anim_FullFrames$In_Out$Main$Batting_Card 3 "
									+ "Anim_FullFrames$In_Out$Main$Batting_Card$BatterBase 3 Anim_FullFrames$In_Out$Main$Batting_Card$BatterBase$In 1.2 "
									+ "Anim_FullFrames$In_Out$Main$Batting_Card$DismissalBase 0 Anim_FullFrames$In_Out$Main$Batting_Card$DismissalBase$In 0 "
									+ "Anim_FullFrames$In_Out$Main$Batting_Card$Batter 3 Anim_FullFrames$In_Out$Main$Batting_Card$Batter$In 1.98 "
									+ "Anim_FullFrames$In_Out$Main$Batting_Card$Dismissal 0 Anim_FullFrames$In_Out$Main$Batting_Card$Dismissal$In 0 "
									+ "Anim_FullFrames$In_Out$ExtraData 3 Anim_FullFrames$In_Out$ExtraData$InOut 3 PlayerHighlight$BattingCard$Side1$" + 
										caption.this_fullFramesGfx.this_ALL_FF.fullframes.getHighlight() + " 0.5 "
									+ "Anim_FullFrames$In_Out$ExtraData$InOut$In 3 Anim_FullFrames$In_Out$Footer 3 Anim_FullFrames$In_Out$Footer$In 2.080 MoveForExtra 1";
							
						}else if(caption.this_fullFramesGfx.this_ALL_FF.fullframes.getWhichhighlight().equalsIgnoreCase("PARTNERSHIP")) {
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side1$BattingCard$BatterGrp$"
									+ caption.this_fullFramesGfx.this_ALL_FF.fullframes.getHighlight() + "$select_Colour*FUNCTION*Omo*vis_con SET 1\0", print_writer);
							
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*TREE*$gfx_FullFrame$Shift_For_TopTitle$AllGraphics$Side1$BattingCard$BatterGrp$"
									+ caption.this_fullFramesGfx.this_ALL_FF.fullframes.getSecondHighlight() + "$select_Colour*FUNCTION*Omo*vis_con SET 2\0", print_writer);
							
							previewCommand = previewCommand + " Anim_FullFrames$In_Out$Main$Batting_Card 3 "
									+ "Anim_FullFrames$In_Out$Main$Batting_Card$BatterBase 3 Anim_FullFrames$In_Out$Main$Batting_Card$BatterBase$In 1.2 "
									+ "Anim_FullFrames$In_Out$Main$Batting_Card$DismissalBase 0 Anim_FullFrames$In_Out$Main$Batting_Card$DismissalBase$In 0 "
									+ "Anim_FullFrames$In_Out$Main$Batting_Card$Batter 3 Anim_FullFrames$In_Out$Main$Batting_Card$Batter$In 1.98 "
									+ "Anim_FullFrames$In_Out$Main$Batting_Card$Dismissal 0 Anim_FullFrames$In_Out$Main$Batting_Card$Dismissal$In 0 "
									+ "Anim_FullFrames$In_Out$ExtraData 3 Anim_FullFrames$In_Out$ExtraData$InOut 3 PlayerHighlight$BattingCard$Side1$" + 
										caption.this_fullFramesGfx.this_ALL_FF.fullframes.getHighlight() + " 0.5 PlayerHighlight$BattingCard$Side1$" + 
										caption.this_fullFramesGfx.this_ALL_FF.fullframes.getSecondHighlight() + " 0.5 "
									+ "Anim_FullFrames$In_Out$ExtraData$InOut$In 3 Anim_FullFrames$In_Out$Footer 3 Anim_FullFrames$In_Out$Footer$In 2.080 "
									+ "MoveForExtra 1";
							
						}
						
						break;	
					case "F2":
						previewCommand = previewCommand + " Anim_FullFrames$In_Out$Main$Bowling_Card 3 "
								+ "Anim_FullFrames$In_Out$Main$Bowling_Card$BowlerBase 3 Anim_FullFrames$In_Out$Main$Bowling_Card$BowlerBase$In 1.2 "
								+ "Anim_FullFrames$In_Out$Main$Bowling_Card$DataBase 3 Anim_FullFrames$In_Out$Main$Bowling_Card$DataBase$In 1.7 "
								+ "Anim_FullFrames$In_Out$Main$Bowling_Card$Bowler 3 Anim_FullFrames$In_Out$Main$Bowling_Card$Bowler$In 1.98 "
								+ "Anim_FullFrames$In_Out$Main$Bowling_Card$BowlerDetails 3 Anim_FullFrames$In_Out$Main$Bowling_Card$BowlerDetails$In 2.42 "
								+ "Anim_FullFrames$In_Out$Footer 3 Anim_FullFrames$In_Out$Footer$In 2.080";
						
						break;
					case "Control_Shift_F2":
						previewCommand = previewCommand + " Anim_FullFrames$In_Out$Main$Bowling_Card 3 "
								+ "Anim_FullFrames$In_Out$Main$Bowling_Card$BowlerBase 3 Anim_FullFrames$In_Out$Main$Bowling_Card$BowlerBase$In 1.2 "
								+ "Anim_FullFrames$In_Out$Main$Bowling_Card$DataBase 0 Anim_FullFrames$In_Out$Main$Bowling_Card$DataBase$In 0 "
								+ "Anim_FullFrames$In_Out$Main$Bowling_Card$Bowler 3 Anim_FullFrames$In_Out$Main$Bowling_Card$Bowler$In 1.98 "
								+ "Anim_FullFrames$In_Out$Main$Bowling_Card$BowlerDetails 0 Anim_FullFrames$In_Out$Main$Bowling_Card$BowlerDetails$In 0 "
								+ "Anim_FullFrames$In_Out$ExtraData 3 Anim_FullFrames$In_Out$ExtraData$InOut 3 PlayerHighlight$BowlingCard$Side1$" + 
									caption.this_fullFramesGfx.this_ALL_FF.fullframes.getHighlight() + " 0.5 "
								+ "Anim_FullFrames$In_Out$ExtraData$InOut$In 3 Anim_FullFrames$In_Out$Footer 3 Anim_FullFrames$In_Out$Footer$In 2.080";
						
						break;	
					case "Control_F11": case "Shift_F11":
						previewCommand = previewCommand + " Anim_FullFrames$In_Out$Logo 0 Anim_FullFrames$In_Out$Logo$In 0 Anim_FullFrames$In_Out$Main$Summary 3 "
								+ "Anim_FullFrames$In_Out$Main$Summary$In 1.74 "
								+ "Anim_FullFrames$In_Out$Footer 3 Anim_FullFrames$In_Out$Footer$In 2.080";
						
						break;
					case "Control_p":
						previewCommand = previewCommand + " Anim_FullFrames$In_Out$Logo 0 Anim_FullFrames$In_Out$Logo$In 0 Anim_FullFrames$In_Out$Main$Standings 3 "
								+ "Anim_FullFrames$In_Out$Main$Standings$In 1.44 "
								+ "Anim_FullFrames$In_Out$Footer 3 Anim_FullFrames$In_Out$Footer$In 2.080";
						
						break;	
					case "Control_Shift_F7":
						caption.this_fullFramesGfx.this_ALL_FF.populateTeamLineUpFooter(print_writer, 1, 
								whatToProcess.split(",")[0], config, "SHOW-TOSS", matchAllData);
						
						previewCommand = "Anim_LineUp_Image$In_Out 3 Anim_LineUp_Image$In_Out$Essentials 3 Anim_LineUp_Image$In_Out$Essentials$In 2.080 "
								+ "Anim_LineUp_Image$In_Out$Big_Logo 3 Anim_LineUp_Image$In_Out$Big_Logo$In 1.50 Anim_LineUp_Image$In_Out$TopTitle 3 "
								+ "Anim_LineUp_Image$In_Out$TopTitle$In 2.1 Anim_LineUp_Image$In_Out$SideTeamColour 2.480 Anim_LineUp_Image$In_Out$SideTeamColour$In 1.4 "
								+ "Anim_LineUp_Image$In_Out$Title 3 Anim_LineUp_Image$In_Out$Title$In 1.9 Anim_LineUp_Image$In_Out$SubTitle 3 "
								+ "Anim_LineUp_Image$In_Out$SubTitle$In 2.4 Anim_LineUp_Image$In_Out$Logo 3 Anim_LineUp_Image$In_Out$Logo$In 2.88 "
								+ "Anim_LineUp_Image$In_Out$Main 3 Anim_LineUp_Image$In_Out$Main$In 2.480";
						break;
					case "Shift_D":
						previewCommand = "anim_Target$In_Out 2 anim_Target$In_Out$In 2";
						break;	
					case "m": case "Control_m":
						previewCommand = "anim_Ident$In_Out 2.000 anim_Ident$In_Out$In 2.000";
						break;
					case "Control_d": case "Control_e": case "Shift_P": case "Shift_Q":
						previewCommand = "anim_Playerprofile$In_Out 2.400 anim_Playerprofile$In_Out$Essential$In 1.500 anim_Playerprofile$In_Out$Logo$In 1.500 "
								+ "anim_Playerprofile$In_Out$Title$In 1.500 anim_Playerprofile$In_Out$Image 2.400 anim_Playerprofile$In_Out$Image$In 1.200 "
								+ "anim_Playerprofile$In_Out$Data$In 2.380";
						break;
					}
					break;
				case Constants.BCCI:
					switch(whatToProcess.split(",")[0]) {
					case "F1": case "F2": case "Control_Shift_F1":
						previewCommand = "anim_Fullframe 5.275 anim_Fullframe$In_Out 4.000 anim_Fullframe$In_Out$Essentials 4.000 anim_Fullframe$In_Out$Essentials$In 3.080 "
								+ "anim_Fullframe$In_Out$RightVeil 4.000 anim_Fullframe$In_Out$RightVeil$In 2.940 anim_Fullframe$In_Out$TeamLogo 4.000 "
								+ "anim_Fullframe$In_Out$TeamLogo$In 2.600 anim_Fullframe$In_Out$TeamName 4.000 anim_Fullframe$In_Out$TeamName$In 2.000 "
								+ "anim_Fullframe$In_Out$Header 4.000 anim_Fullframe$In_Out$Header$In 2.500 anim_Fullframe$In_Out$SubHeader 4.000 "
								+ "anim_Fullframe$In_Out$SubHeader$In 2.200 anim_Fullframe$In_Out$Footer 4.000 anim_Fullframe$In_Out$Footer$In 3.360 "
								+ "anim_Fullframe$In_Out$Sponsor 4.000 anim_Fullframe$In_Out$Sponsor$In 3.580 ";
						
						switch(whatToProcess.split(",")[0]) {
						case "F1":
							previewCommand = previewCommand + "anim_Fullframe$In_Out$Main$Normal_BattingCard 4.000 anim_Fullframe$In_Out$Main$Normal_BattingCard$In 4.000";
							break;
						case "F2":
							previewCommand = previewCommand + "anim_Fullframe$In_Out$Main$BowlingCard 4.000 anim_Fullframe$In_Out$Main$BowlingCard$In 4.000";
							break;
						}
						break;
					}
					break;
				}
			}else if(whichside == 2) {
				
				switch(config.getBroadcaster()) {
				case Constants.AFG_SL_SERIES:
					switch(whatToProcess.split(",")[0]) {
					case "Control_d": case "Control_e": case "Shift_P": case "Shift_Q":
						previewCommand = "Change_Profile 2.280 Change_Profile$Logo 1.000 Change_Profile$Profile 2.280 Change_Profile$Profile$Change_Out 1.480 "
								+ "Change_Profile$Profile$Change_In 2.280";
						break;
					case "F1": case "F2": case "Control_F7": case "Shift_T": case "Control_Alt_F1": case "Alt_Shift_F1": case "F4": 
					case "Control_F11": case "Shift_K": case "Shift_F10": case "Control_F10": case "Alt_F11": case "Control_F1":
						
						previewCommand = "Change_Fullframes 1.920 Change_Fullframes$SubHeader 1.000 Change_Fullframes$Header 1.000 Change_Fullframes$Logo 1.233 "
								+ "Change_Fullframes$Logo$Change_Out 0.700 Change_Fullframes$Logo$Change_In 1.233 Change_Fullframes$Icon 1.233 "
								+ "Change_Fullframes$Icon$Change_Out 0.700 Change_Fullframes$Icon$Change_In 1.233 Change_Fullframes$VerticalText 1.000 "
								+ "Change_Fullframes$Footer 1.000";
						
						switch(whichGraphicOnScreen.split(",")[0]) {
						case "F1":
							previewCommand = previewCommand + " Change_Fullframes$BattingCard 1.886 Change_Fullframes$BattingCard$Change_Out 1.220 "
									+ "Change_Fullframes$BattingCard$Change_In 1.886";
							break;
						case "F2":
							previewCommand = previewCommand + " Change_Fullframes$BowlingCard 1.886 Change_Fullframes$BowlingCard$Change_Out 1.220 "
									+ "Change_Fullframes$BowlingCard$Change_In 1.886";
							break;
						case "Control_F7": 
							previewCommand = previewCommand + " Change_Fullframes$Teams 1.886 Change_Fullframes$Teams$Change_Out 1.160 "
									+ "Change_Fullframes$Teams$Change_In 1.886";
							break;
						case "Shift_T": 
							previewCommand = previewCommand + " Change_Fullframes$Team 1.886 Change_Fullframes$Team$Change_Out 1.160 "
									+ "Change_Fullframes$Team$Change_In 1.886";
							break;
						case "Control_Alt_F1": case "Alt_Shift_F1":
							previewCommand = previewCommand + " Change_Fullframes$SplitCard$Left 1.886 Change_Fullframes$SplitCard$Left$Change_Out 1.220 "
									+ "Change_Fullframes$SplitCard$Left$Change_In 1.886";
							switch (whichGraphicOnScreen.split(",")[0]) {
							case "Control_Alt_F1":
								previewCommand = previewCommand + " Change_Fullframes$SplitCard$Right$SplitBowling 1.886 "
										+ "Change_Fullframes$SplitCard$Right$SplitBowling$Change_Out 1.220 "
										+ "Change_Fullframes$SplitCard$Right$SplitBowling$Change_In 1.886";
								break;
							case "Alt_Shift_F1":
								previewCommand = previewCommand + " Change_Fullframes$SplitCard$Right$Contribution 1.400 "
										+ "Change_Fullframes$SplitCard$Right$Contribution$Change_Out 0.700 "
										+ "Change_Fullframes$SplitCard$Right$Contribution$Change_In 1.400";
								break;
							}
							break;
						case "F4": 
							previewCommand = previewCommand + " Change_Fullframes$PartnershipList 1.886 Change_Fullframes$PartnershipList$Change_Out 1.220 "
									+ "Change_Fullframes$PartnershipList$Change_In 1.886";
							break;
						case "Control_F11":
							previewCommand = previewCommand + " Change_Fullframes$Summary 1.706 Change_Fullframes$Summary$Change_Out 0.740 "
									+ "Change_Fullframes$Summary$Change_In 1.706";
							break;
						case "Shift_K": 
							previewCommand = previewCommand + " Change_Fullframes$Partnership 1.880 Change_Fullframes$Partnership$Change_Out 0.900 "
									+ "Change_Fullframes$Partnership$Change_In 1.880";
							break;
						case "Shift_F10": 
							previewCommand = previewCommand + " Change_Fullframes$Worms 1.860 Change_Fullframes$Worms$Change_Out 0.760 "
									+ "Change_Fullframes$Worms$Change_In 1.860 Change_Fullframes$Worms$Change_In$Runs 1.860";
							break;
						case "Control_F10": 
							previewCommand = previewCommand + " Change_Fullframes$Manhattan 1.880 Change_Fullframes$Manhattan$Change_Out 0.600 "
									+ "Change_Fullframes$Manhattan$Change_In 1.880 Change_Fullframes$Manhattan$Change_In$Runs 1.880";
							break;
						case "Alt_F11":
							previewCommand = previewCommand + " Change_Fullframes$ManhattanComparison 1.920 Change_Fullframes$ManhattanComparison$Change_Out 0.880 "
									+ "Change_Fullframes$ManhattanComparison$Change_In 1.920 Change_Fullframes$ManhattanComparison$Change_In$Runs 1.920";
							break;
						case "Control_F1":
							previewCommand = previewCommand + " Change_Fullframes$ImageBattingCard 1.766 Change_Fullframes$ImageBattingCard$Change_Out 0.860 "
									+ "Change_Fullframes$ImageBattingCard$Change_In 1.766";
							break;
						}
						
						if(!whichGraphicOnScreen.split(",")[0].equalsIgnoreCase(whatToProcess.split(",")[0])) {
							switch(whatToProcess.split(",")[0]) {
							case "F1":
								previewCommand = previewCommand + " Change_Fullframes$BattingCard 1.886 Change_Fullframes$BattingCard$Change_Out 1.220 "
										+ "Change_Fullframes$BattingCard$Change_In 1.886";
								break;
							case "F2":
								previewCommand = previewCommand + " Change_Fullframes$BowlingCard 1.886 Change_Fullframes$BowlingCard$Change_Out 1.220 "
										+ "Change_Fullframes$BowlingCard$Change_In 1.886";
								break;
							case "Control_F7": 
								previewCommand = previewCommand + " Change_Fullframes$Teams 1.886 Change_Fullframes$Teams$Change_Out 1.160 "
										+ "Change_Fullframes$Teams$Change_In 1.886";
								break;
							case "Shift_T":
								previewCommand = previewCommand + " Change_Fullframes$Team 1.886 Change_Fullframes$Team$Change_Out 1.160 "
										+ "Change_Fullframes$Team$Change_In 1.886";
								break;
							case "Control_Alt_F1": case "Alt_Shift_F1":
								previewCommand = previewCommand + " Change_Fullframes$SplitCard$Left 1.886 Change_Fullframes$SplitCard$Left$Change_Out 1.220 "
										+ "Change_Fullframes$SplitCard$Left$Change_In 1.886 ";
								switch (whichGraphicOnScreen.split(",")[0]) {
								case "Control_Alt_F1":
									previewCommand = previewCommand + " Change_Fullframes$SplitCard$Right$SplitBowling 1.886 "
											+ "Change_Fullframes$SplitCard$Right$SplitBowling$Change_Out 1.220 "
											+ "Change_Fullframes$SplitCard$Right$SplitBowling$Change_In 1.886";
									break;
								case "Alt_Shift_F1":
									previewCommand = previewCommand + " Change_Fullframes$SplitCard$Right$Contribution 1.400 "
											+ "Change_Fullframes$SplitCard$Right$Contribution$Change_Out 0.700 "
											+ "Change_Fullframes$SplitCard$Right$Contribution$Change_In 1.400";
									break;
								}
								break;
							case "F4": 
								previewCommand = previewCommand + " Change_Fullframes$PartnershipList 1.886 Change_Fullframes$PartnershipList$Change_Out 1.220 "
										+ "Change_Fullframes$PartnershipList$Change_In 1.886";
								break;
							case "Control_F11":
								previewCommand = previewCommand + " Change_Fullframes$Summary 1.706 Change_Fullframes$Summary$Change_Out 0.740 "
										+ "Change_Fullframes$Summary$Change_In 1.706";
								break;
							case "Shift_K": 
								previewCommand = previewCommand + " Change_Fullframes$Partnership 1.880 Change_Fullframes$Partnership$Change_Out 0.900 "
										+ "Change_Fullframes$Partnership$Change_In 1.880";
								break;
							case "Shift_F10": 
								previewCommand = previewCommand + " Change_Fullframes$Worms 1.860 Change_Fullframes$Worms$Change_Out 0.760 "
										+ "Change_Fullframes$Worms$Change_In 1.860 Change_Fullframes$Worms$Change_In$Runs 1.860";
								break;
							case "Control_F10": 
								previewCommand = previewCommand + " Change_Fullframes$Manhattan 1.880 Change_Fullframes$Manhattan$Change_Out 0.600 "
										+ "Change_Fullframes$Manhattan$Change_In 1.880 Change_Fullframes$Manhattan$Change_In$Runs 1.880";
								break;
							case "Alt_F11":
								previewCommand = previewCommand + " Change_Fullframes$ManhattanComparison 1.920 Change_Fullframes$ManhattanComparison$Change_Out 0.880 "
										+ "Change_Fullframes$ManhattanComparison$Change_In 1.920 Change_Fullframes$ManhattanComparison$Change_In$Runs 1.920";
								break;
							case "Control_F1":
								previewCommand = previewCommand + " Change_Fullframes$ImageBattingCard 1.766 Change_Fullframes$ImageBattingCard$Change_Out 0.860 "
										+ "Change_Fullframes$ImageBattingCard$Change_In 1.766";
								break;
							}
						}
						break;
					}
					break;
				case Constants.BAN_AFG_SERIES: case Constants.ACC:
					switch(whatToProcess.split(",")[0]) {
					case "z": case "x": case "c": case "v": case "Control_z": case "Control_x": case "Control_Shift_Z": case "Control_Shift_Y":
						previewCommand = previewCommand + "Full_Frames$Main_Graphics$Change$Side2_In 1.100 Full_Frames$Main_Graphics$Change$Top5 2.900 "
								+ "Full_Frames$Main_Graphics$Change$Top5$Change_Out 1.000  Full_Frames$Main_Graphics$Change$Top5$Change_In 2.900";
						break;
					case "Shift_T":
						previewCommand = "Full_Frames$Main_Graphics$Change$Side2_In 1.100 Full_Frames$Main_Graphics$Change$PlayingXI_Image 4.180 Full_Frames$Main_Graphics$Change$PlayingXI_Image$Change_Out 1.040 "
								+ "Full_Frames$Main_Graphics$Change$PlayingXI_Image$Change_In 4.180 Full_Frames$Main_Graphics$Change$PlayingXI_Image$Change_In$TeamEssentials_In 2.745 "
								+ "Full_Frames$Main_Graphics$Change$PlayingXI_Image$Change_In$Team 4.180";
						break;
					case "Control_d": case "Control_e": case "Shift_P": case "Shift_Q":
						previewCommand = "Full_Frames$Main_Graphics$Change$Side2_In 1.100 Full_Frames$Main_Graphics$Change$Profile 3.340 Full_Frames$Main_Graphics$Change$Profile$Change_Out 1.020 "
								+ "Full_Frames$Main_Graphics$Change$Profile$Change_In 3.340";
						break;
					case "F1": case "Control_Shift_F1": case "F2": case "Control_Shift_F2": case "Control_F11": case "F4": case "Control_p":
						switch(whichGraphicOnScreen.split(",")[0]) {
						case "F1": case "Control_Shift_F1":
							previewCommand = "Full_Frames$Main_Graphics$Change$Side2_In 1.100 Full_Frames$Main_Graphics$Change$BatCard 3.540 Full_Frames$Main_Graphics$Change$BatCard$Change_Out 1.020 "
									+ "Full_Frames$Main_Graphics$Change$BatCard$Change_In 3.540";
							break;
						case "F2": case "Control_Shift_F2":
							previewCommand = "Full_Frames$Main_Graphics$Change$Side2_In 1.100 Full_Frames$Main_Graphics$Change$BallCard 3.640 Full_Frames$Main_Graphics$Change$BallCard$Change_Out 1.020 "
									+ "Full_Frames$Main_Graphics$Change$BallCard$Change_In 3.640";
							break;
						case "Control_F11":
							previewCommand = "Full_Frames$Main_Graphics$Change$Side2_In 1.100 Full_Frames$Main_Graphics$Change$Summary 2.660 Full_Frames$Main_Graphics$Change$Summary$Change_Out 1.100 "
									+ "Full_Frames$Main_Graphics$Change$Summary$Change_In 2.660";
							break;
						case "F4":
							previewCommand = "Full_Frames$Main_Graphics$Change$Side2_In 1.100 Full_Frames$Main_Graphics$Change$Partnership_Table 3.360 Full_Frames$Main_Graphics$Change$Partnership_Table$Change_Out 1.020 "
									+ "Full_Frames$Main_Graphics$Change$Partnership_Table$Change_In 3.360";
							break;
						case "Control_p":
							previewCommand = "Full_Frames$Main_Graphics$Change$Side2_In 1.100 Full_Frames$Main_Graphics$Change$Standings 2.900 Full_Frames$Main_Graphics$Change$Standings$Change_Out 0.720 "
									+ "Full_Frames$Main_Graphics$Change$Standings$Change_In 2.900";
							break;
						}
						
						if(!whichGraphicOnScreen.split(",")[0].equalsIgnoreCase(whatToProcess.split(",")[0])) {
							switch(whatToProcess.split(",")[0]) {
							case "Control_F11":
								previewCommand = previewCommand + " Full_Frames$Main_Graphics$Change$Summary 2.660 Full_Frames$Main_Graphics$Change$Summary$Change_Out 1.100 "
										+ "Full_Frames$Main_Graphics$Change$Summary$Change_In 2.660";
								break;
							case "Control_p":
								previewCommand = previewCommand + " Full_Frames$Main_Graphics$Change$Standings 2.900 Full_Frames$Main_Graphics$Change$Standings$Change_Out 0.720 "
										+ "Full_Frames$Main_Graphics$Change$Standings$Change_In 2.900";
								break;
							case "F4":
								previewCommand = previewCommand + " Full_Frames$Main_Graphics$Change$Partnership_Table 3.360 Full_Frames$Main_Graphics$Change$Partnership_Table$Change_Out 1.020 "
										+ "Full_Frames$Main_Graphics$Change$Partnership_Table$Change_In 3.360";
								break;
							case "F1": case "Control_Shift_F1":
								if(!whichGraphicOnScreen.split(",")[0].equalsIgnoreCase("F1") && !whichGraphicOnScreen.split(",")[0].equalsIgnoreCase("Control_Shift_F1")) {
									previewCommand = previewCommand + " Full_Frames$Main_Graphics$Change$BatCard 3.540 Full_Frames$Main_Graphics$Change$BatCard$Change_Out 1.020 "
											+ "Full_Frames$Main_Graphics$Change$BatCard$Change_In 3.540";
								}
								switch (whatToProcess.split(",")[0]) {
								case "Control_Shift_F1":
									if(caption.this_fullFramesGfx.this_ALL_FF.fullframes.getWhichhighlight().equalsIgnoreCase("PERFORMER")) {
										
										if(caption.this_fullFramesGfx.this_ALL_FF.fullframes.getHighlight() == 1) {
											previewCommand = previewCommand + " Full_Frames$Main_Graphics$Change$BatCard$BatHighlight$Left$1$" + 
													caption.this_fullFramesGfx.this_ALL_FF.fullframes.getHighlight() + " 0.500";
										}else {
											previewCommand = previewCommand + " Full_Frames$Main_Graphics$Change$BatCard$BatHighlight$Left$" + 
													caption.this_fullFramesGfx.this_ALL_FF.fullframes.getHighlight() + " 0.500";
										}
										
									}else if(caption.this_fullFramesGfx.this_ALL_FF.fullframes.getWhichhighlight().equalsIgnoreCase("PARTNERSHIP")) {
										
										if(caption.this_fullFramesGfx.this_ALL_FF.fullframes.getHighlight() == 1) {
											previewCommand = previewCommand + " Full_Frames$Main_Graphics$Change$BatCard$BatHighlight$Left$1$" + caption.this_fullFramesGfx.this_ALL_FF.fullframes.getHighlight() + " 0.500 "
													+ "Full_Frames$Main_Graphics$Change$BatCard$BatHighlight$Left$" + caption.this_fullFramesGfx.this_ALL_FF.fullframes.getSecondHighlight() + " 0.500";
										}else if(caption.this_fullFramesGfx.this_ALL_FF.fullframes.getSecondHighlight() == 1) {
											previewCommand = previewCommand + " Full_Frames$Main_Graphics$Change$BatCard$BatHighlight$Left$" + caption.this_fullFramesGfx.this_ALL_FF.fullframes.getHighlight() + " 0.500 "
													+ "Full_Frames$Main_Graphics$Change$BatCard$BatHighlight$Left$1$" + caption.this_fullFramesGfx.this_ALL_FF.fullframes.getSecondHighlight() + " 0.500";
										}else {
											previewCommand = previewCommand + " Full_Frames$Main_Graphics$Change$BatCard$BatHighlight$Left$" + caption.this_fullFramesGfx.this_ALL_FF.fullframes.getHighlight() + " 0.500 "
													+ "Full_Frames$Main_Graphics$Change$BatCard$BatHighlight$Left$" + caption.this_fullFramesGfx.this_ALL_FF.fullframes.getSecondHighlight() + " 0.500";
										}
										
									}
									break;
								}
								break;
							case "F2": case "Control_Shift_F2":
								if(!whichGraphicOnScreen.split(",")[0].equalsIgnoreCase("F2") && !whichGraphicOnScreen.split(",")[0].equalsIgnoreCase("Control_Shift_F2")) {
									previewCommand = previewCommand + " Full_Frames$Main_Graphics$Change$BallCard 3.640 Full_Frames$Main_Graphics$Change$BallCard$Change_Out 1.020 "
											+ "Full_Frames$Main_Graphics$Change$BallCard$Change_In 3.640";
								}
								switch (whatToProcess.split(",")[0]) {
								case "Control_Shift_F2":
									
									if(caption.this_fullFramesGfx.this_ALL_FF.fullframes.getSecondHighlight() != 0) {
										previewCommand = previewCommand + " Full_Frames$Main_Graphics$Change$BallCard$BallHighlight$Left$" + 
												caption.this_fullFramesGfx.this_ALL_FF.fullframes.getHighlight() + " 0.500 "
												+ "Full_Frames$Main_Graphics$Change$BallCard$BallHighlight$Left$" + 
												caption.this_fullFramesGfx.this_ALL_FF.fullframes.getSecondHighlight() + " 0.500";
									}else {
										previewCommand = previewCommand + " Full_Frames$Main_Graphics$Change$BallCard$BallHighlight$Left$" + 
												caption.this_fullFramesGfx.this_ALL_FF.fullframes.getHighlight() + " 0.500";
									}
									
									break;
								}
								break;
							}
						}
						break;
					}
					
					switch (config.getBroadcaster().toUpperCase()) {
					case Constants.ACC:
						previewCommand = previewCommand + " Wipe_BG_In_Out$Change 1.000";
						break;
					}
					break;
				case Constants.TRI_SERIES:  case Constants.MT20:
					switch(whatToProcess.split(",")[0]) {
					case "Control_Shift_F7":
						
						caption.this_fullFramesGfx.this_ALL_FF.populateTeamLineUpFooter(print_writer, 2, 
								whatToProcess.split(",")[0], config, "SHOW-TOSS", matchAllData);
						
						previewCommand = "Change_LineUp_Image$Big_Logo 1.5 Change_LineUp_Image$SideTeamColour 0.5 Change_LineUp_Image$Title 0.8 Change_LineUp_Image$SubTitle 0.8 Change_LineUp_Image$Logo 0.8 "
								+ "Change_LineUp_Image$Main$Change_Out 0.38 Change_LineUp_Image$Main$Change_In 0.74 Change_LineUp_Image$Footer 0.5";
						break;
					case "F1": case "F2": case "F4": case "Control_F11":
						previewCommand = "Change$Big_Logo 1.5 Change$SideTeamColour 0.5 Change$Title 0.8 Change$TitleBase 0.8 Change$SubTitle 0.8 Change$Logo 0.8 "
								+ "Change$Footer 0.98 Change$Footer$Change_Out 0.5 Change$Footer$Change_In 0.98 ";
						previewCommand = previewCommand + " BaseDynamic 1.4 BaseScaleForFooter 1.0 ";
						break;
					case "Control_Shift_F4": case "Control_Shift_F5":
						previewCommand = "Change$Big_Logo 1.5 Change$SideTeamColour 0.5 Change$Title 0.8 Change$TitleBase 0.8 Change$Footer 0.98 Change$Footer$Change_Out 0.5 "
								+ "Change$Footer$Change_In 0.98 ";
						previewCommand = previewCommand + " BaseDynamic 1.4 BaseScaleForFooter 1.0 ";
						break;
					case "Control_p":
						previewCommand = "Change$Big_Logo 1.5 Change$SideTeamColour 0.5 Change$Title 0.8 Change$TitleBase 0.8 "
								+ "Change$Footer 0.98 Change$Footer$Change_Out 0.5 Change$Footer$Change_In 0.98 ";
						break;	
					}
					
					switch(whichGraphicOnScreen.split(",")[0]) {
					case "Control_Shift_F4": 
						previewCommand = previewCommand + "Change$Batting_CardExtra 2.060 Change$Batting_CardExtra$Change_Out 0.94 "
								+ "Change$Batting_CardExtra$Change_In 2.060 MoveForExtra 1.0 ";
						break;
					case "Control_Shift_F5":
						previewCommand = previewCommand + "Change$Bowling_CardExtra 2.080 Change$Bowling_CardExtra$Change_Out 0.96 "
								+ "Change$Bowling_CardExtra$Change_In 2.080 MoveForExtra 1.0 ";
						break;
					case "F1":
						previewCommand = previewCommand + "Change$BattingCard$Batter 1.92 Change$BattingCard$Batter$Change_Out 0.94 "
								+ "Change$BattingCard$Batter$Change_In 1.92 Change$BattingCard$Dismissal 1.92 Change$BattingCard$Dismissal$Change_Out 0.94 "
								+ "Change$BattingCard$Dismissal$Change_In 1.92 ";
						break;
					case "F2":
						previewCommand = previewCommand + "Change$Bowling_Card 1.92 Change$Bowling_Card$BowlerBase 1.2 Change$Bowling_Card$BowlerBase$Change_Out 1.92 "
								+ "Change$Bowling_Card$BowlerBase$Change_In 1.2 Change$Bowling_Card$DataBase 1.2 Change$Bowling_Card$DataBase$Change_Out 0.7 "
								+ "Change$Bowling_Card$DataBase$Change_In 1.2 Change$Bowling_Card$Bowler 1.92 Change$Bowling_Card$Bowler$Change_Out 0.94 "
								+ "Change$Bowling_Card$Bowler$Change_In 1.92 Change$Bowling_Card$BowlerDetails 1.92 Change$Bowling_Card$BowlerDetails$Change_Out 0.94 "
								+ "Change$Bowling_Card$BowlerDetails$Change_In 1.92 ";
						break;
					case "Control_F11":
						previewCommand = previewCommand + "Change$Summary 1.74 Change$Summary$Change_Out 0.88 Change$Summary$Change_In 1.74 ";
						break;
					case "Control_p":
						previewCommand = previewCommand + "Change$Standings 1.44 Change$Standings$Change_Out 0.76 Change$Standings$IChange_n 1.44 ";
						break;	
					case "F4":
						previewCommand = previewCommand + "Change$PartnershipList 1.98 Change$PartnershipList$Change_Out 0.94 Change$PartnershipList$Change_In 1.98 ";
						break;	
					}
					
					switch(whatToProcess.split(",")[0]) {
					case "F1":
						previewCommand = previewCommand + "Change$BattingCard$Batter 1.92 Change$BattingCard$Batter$Change_Out 0.94 "
								+ "Change$BattingCard$Batter$Change_In 1.92 Change$BattingCard$Dismissal 1.92 Change$BattingCard$Dismissal$Change_Out 0.94 "
								+ "Change$BattingCard$Dismissal$Change_In 1.92 ";
						break;
					case "F2":
						previewCommand = previewCommand + "Change$Bowling_Card 1.92 Change$Bowling_Card$BowlerBase 1.2 Change$Bowling_Card$BowlerBase$Change_Out 1.92 "
								+ "Change$Bowling_Card$BowlerBase$Change_In 1.2 Change$Bowling_Card$DataBase 1.2 Change$Bowling_Card$DataBase$Change_Out 0.7 "
								+ "Change$Bowling_Card$DataBase$Change_In 1.2 Change$Bowling_Card$Bowler 1.92 Change$Bowling_Card$Bowler$Change_Out 0.94 "
								+ "Change$Bowling_Card$Bowler$Change_In 1.92 Change$Bowling_Card$BowlerDetails 1.92 Change$Bowling_Card$BowlerDetails$Change_Out 0.94 "
								+ "Change$Bowling_Card$BowlerDetails$Change_In 1.92";
						break;
					case "Control_Shift_F4": 
						previewCommand = previewCommand + "Change$Batting_CardExtra 2.060 Change$Batting_CardExtra$Change_Out 0.94 "
								+ "PlayerHighlight$BattingCard$Side2$" + 
								caption.this_fullFramesGfx.this_ALL_FF.fullframes.getHighlight() + " 0.5 "
								+ "Change$Batting_CardExtra$Change_In 2.060 MoveForExtra 1.0 ";
						break;
					case "Control_Shift_F5":
						previewCommand = previewCommand + "Change$Bowling_CardExtra 2.080 Change$Bowling_CardExtra$Change_Out 0.96 "
								+ "PlayerHighlight$BowlingCard$Side2$" + 
								caption.this_fullFramesGfx.this_ALL_FF.fullframes.getHighlight() + " 0.5 "
								+ "Change$Bowling_CardExtra$Change_In 2.080 MoveForExtra 1.0 ";
						break;
					case "Control_F11":
						previewCommand = previewCommand + "Change$Summary 1.74 Change$Summary$Change_Out 0.88 Change$Summary$Change_In 1.74";
						break;
					case "Control_p":
						previewCommand = previewCommand + "Change$Standings 1.44 Change$Standings$Change_Out 0.76 Change$Standings$IChange_n 1.44";
						break;	
					case "F4":
						previewCommand = previewCommand + "Change$PartnershipList 1.98 Change$PartnershipList$Change_Out 0.94 Change$PartnershipList$Change_In 1.98";
						break;	
					}
					break;
				}
			}
			switch (config.getBroadcaster().toUpperCase()) {
			case Constants.TRI_SERIES:  case Constants.MT20: case Constants.AFG_SL_SERIES:
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER PREVIEW SCENE*/Default/gfx_FullFrames " + "C:/Temp/Preview.tga " + previewCommand + "\0", print_writer);
				break;
			case Constants.BAN_AFG_SERIES: case Constants.ACC:
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER PREVIEW SCENE*/Default/FullFrames " + "C:/Temp/Preview.tga " + previewCommand + "\0", print_writer);
				break;
			default:
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER PREVIEW SCENE*/Default/gfx_Fullframes " + "C:/Temp/Preview.tga " + previewCommand + "\0", print_writer);
				break;
			}
		}
	}

	public void processL3Preview(String whatToProcess, List<PrintWriter> print_writer, int whichside, Configuration config,MatchAllData match) throws InterruptedException
	{
		if(config.getPreview().equalsIgnoreCase("WITH_PREVIEW")) {
			String previewCommand = "";
			if(whichside == 1) {
				switch (config.getBroadcaster().toUpperCase()) {
				case Constants.TRI_SERIES:  case Constants.MT20:
					if(this.infobar.isInfobar_on_screen() == true) {
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$Overall_Position_Y*"
							+ "TRANSFORMATION*POSITION*Y SET 15.0 \0",print_writer);
					}else {
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_LowerThird$Overall_Position_Y*"
								+ "TRANSFORMATION*POSITION*Y SET 0.0 \0",print_writer);
					}
					
					if(this.infobar.isInfobar_on_screen() == true) {
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Sponsor$Overall_Position_Y*"
							+ "TRANSFORMATION*POSITION*Y SET 0.0 \0",print_writer);
					}else {
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*TREE*$gfx_Sponsor$Overall_Position_Y*"
								+ "TRANSFORMATION*POSITION*Y SET -44.0 \0",print_writer);
					}
					break;	
				}
				
				switch (config.getBroadcaster().toUpperCase()) {
				case Constants.BAN_AFG_SERIES: case Constants.ACC:
					switch(whatToProcess.split(",")[0]) {
					case "Alt_Shift_Q":
						previewCommand = "Plotter 1.000 Plotter$In 1.000 Plotter$Out 1.000";
						break;
					case "F5": case "F6": case "F8": case "F9": case "F10": case "Alt_F8": case "Control_F6": case "Control_F5": case "Control_F9": case "Shift_F6": 
					case "Shift_F3": case "Shift_F5": case "Shift_F9": case "Alt_F12": case "Control_Shift_L": case "Control_Shift_M": case "u": case "Control_a":
					case "F7": case "F11": case "Control_s": case "Control_f": case "Control_Shift_O": case "Control_h": case "Control_F3": case "d": case "Control_Shift_B":
					case "Alt_Shift_F3": case "l": case "Alt_Shift_F4": case "Alt_d":
//						previewCommand = "LT$Logo$In_Out 1.7 LT$Logo$In_Out$In 1.7 LT$Base$In_Out 0.72 LT$Base$In_Out$In 0.72 "
//								+ "LT$Data$In_Out 0.72 LT$Data$In_Out$In 0.72";
						if(infobar.getInfobar_status() != null && !infobar.getInfobar_status().isEmpty()) {
							if(!infobar.getInfobar_status().equalsIgnoreCase(Constants.FORCED+Constants.SHRUNK_INFOBAR) && 
									!infobar.getInfobar_status().equalsIgnoreCase(Constants.SHRUNK_INFOBAR)) {
								previewCommand = "Anim_Infobar$FFIn 0.520 LT$Logo$In_Out$In 1.7 LT$Base$In_Out$In 0.72 "
										+ "LT$Data$In_Out$In 0.72";
							}else {
								previewCommand = "LT$Logo$In_Out$In 1.7 LT$Base$In_Out$In 0.72 "
										+ "LT$Data$In_Out$In 0.72";
							}
						}else {
							previewCommand = "LT$Logo$In_Out$In 1.7 LT$Base$In_Out$In 0.72 "
									+ "LT$Data$In_Out$In 0.72";
						}
						break;
					}
					break;
				case Constants.TRI_SERIES:  case Constants.MT20:
					switch(whatToProcess.split(",")[0]) {
					
					case "Control_Shift_B":
						previewCommand = "Shrink$InOut 0.400 LT_NextToBat$InOut 1.5 LT_NextToBat$InOut$Essentials 1.5 "
								+ "LT_NextToBat$InOut$Essentials$In 1.3 LT_NextToBat$InOut$Logo 1.5 LT_NextToBat$InOut$Logo$In 1.5 "
								+ "LT_NextToBat$InOut$Data 1.12 LT_NextToBat$InOut$Data$In 1.12";
						break;
					case "Control_Shift_O":
						previewCommand = "Shrink$InOut 0.400 LT_PlayingXI$InOut 1.5 LT_PlayingXI$InOut$Essentials 1.5 "
								+ "LT_PlayingXI$InOut$Essentials$In 1.3 LT_PlayingXI$InOut$Logo 1.5 LT_PlayingXI$InOut$Logo$In 1.5 "
								+ "LT_PlayingXI$InOut$Data 1.44 LT_PlayingXI$InOut$Data$In 1.44";
						break;
					case "Control_6":
						previewCommand = "Shrink$InOut 0.400 LT_Weather$InOut 1.5 LT_Weather$InOut$Essentials 1.5 "
								+ "LT_Weather$InOut$Essentials$In 1.3 LT_Weather$InOut$Logo 1.5 LT_Weather$InOut$Logo$In 1.5";
						break;	
					case "Control_Shift_F10":
						previewCommand = "Shrink$InOut 0.400 LT_Manhattan 3 LT_Manhattan$InOut 3 LT_Manhattan$InOut$Essentials 3 "
								+ "LT_Manhattan$InOut$Essentials$In 1.3 LT_Manhattan$InOut$Logo 3 LT_Manhattan$InOut$Logo$In 1.5 "
								+ "LT_Manhattan$InOut$Bars 3 LT_Manhattan$InOut$Bars$In 2.9";
						break;
					case "Control_F3":
						previewCommand = "Shrink$InOut 0.400 LT_Comparison$InOut 1.500 LT_Comparison$InOut$Comparison$In 1.200";
						break;
					case "Control_Shift_M": case "Control_Shift_L":
						previewCommand = "Shrink$InOut 0.400 LT_MatchID$InOut 1.500 LT_MatchID$InOut$Ident$In 1.200";
						break;
						
					case "Alt_F8": case "F8": case "F10":
						previewCommand = "Shrink$InOut 0.400 anim_NamesuperCenter$InOut 1.200 anim_NamesuperCenter$InOut$Essentials$In 0.900 "
								+ "anim_NamesuperCenter$InOut$Logo$In 1.000 anim_NamesuperCenter$InOut$Colour$In 1.200";
						break;
					
					case "Control_F5": case "Control_F9": case "F9": case "Shift_E": case "d": case "e": 
						previewCommand = "Shrink$InOut 0.400 anim_Lower_Third$InOut 1.500 anim_Lower_Third$InOut$Essentials$In 1.300 "
								+ "anim_Lower_Third$InOut$Logo$In 1.500 anim_LtChange$HeaderDynamic 1.000";
						break;
					case "Alt_Shift_F3": case "F7": case "F11": case "Control_s": case "Control_f": case "F5": case "F6": case "Control_F6": 
					case "Shift_F6": case "u": case "Shift_F3": case "Shift_F5": case "Shift_F9": case "Alt_F12": case "Control_h": 
					case "Control_a": case "Control_Shift_Q": case "Alt_F1": case "Alt_F2": case "Control_Shift_X": case "Control_Shift_K": 
					case "Control_c":
					
						previewCommand = "Shrink$InOut 0.400 anim_Lower_Third$InOut 1.500 anim_Lower_Third$InOut$Essentials$In 1.300 "
								+ "anim_Lower_Third$InOut$Logo$In 1.500 anim_LtChange$HeaderDynamic 0.000";
						break;
					}
					break;
				}
			}else if(whichside == 2) {
				switch (config.getBroadcaster().toUpperCase()) {
				case Constants.BAN_AFG_SERIES: case Constants.ACC:
					switch(whatToProcess.split(",")[0]) {
					case "F5": case "F6": case "F8": case "F9": case "F10": case "Alt_F8": case "Control_F6": case "Control_F5": case "Control_F9": case "Shift_F6": 
					case "Shift_F3": case "Shift_F5": case "Shift_F9": case "Alt_F12": case "Control_Shift_L": case "Control_Shift_M": case "u": case "Control_a":
					case "F7": case "F11": case "Control_s": case "Control_f": case "Control_Shift_O": case "Control_h": case "Control_F3": case "d": case "Control_Shift_B":
					case "Alt_Shift_F3": case "l": case "Alt_Shift_F4": case "Alt_d":
//						previewCommand = "LT$Logo$Change 2.120 LT$Logo$Change$Change_Out 0.420 LT$Logo$Change$Change_In 2.120 LT$Base$Change 1.200 LT$Base$Change$Change_Out 0.460 LT$Base$Change$Change_In 1.200 "
//								+ "LT$Data$Change 1.180 LT$Data$Change$Change_Out 0.460 LT$Data$Change$Change_In 1.180";
						previewCommand = "LT$Data$In_Out$In 0.0 LT$Logo$Change 2.120 LT$Logo$Change$Change_Out 0.420 LT$Logo$Change$Change_In 2.120 LT$Base$Change 1.200 LT$Base$Change$Change_Out 0.460 LT$Base$Change$Change_In 1.200 "
								+ "LT$Data$Change 1.180 LT$Data$Change$Change_Out 0.460 LT$Data$Change$Change_In 1.180";
						break;
					}
					break;
				case Constants.TRI_SERIES:  case Constants.MT20:
					switch(whatToProcess.split(",")[0]) {
					case "F5": case "F6": case "Control_F6": case "Shift_F6": case "F7": case "F11": case "Control_s": case "Control_f": case "u": case "Shift_F3": 
					case "Shift_F5": case "Shift_F9": case "Alt_F12": case "Control_h": case "Control_a": case "Alt_Shift_F3": case "Control_Shift_Q": case "Alt_F1": case "Alt_F2":
					case "Control_Shift_X": case "Control_Shift_K": case "Control_c":
						previewCommand = "anim_LtChange 1.000 anim_LtChange$Headerband 1.000 anim_LtChange$Header 1.000 anim_LtChange$Score_Band 1.000 anim_LtChange$Score 1.000 anim_LtChange$Logo 1.000 anim_LtChange$Subline 1.000 "
								+ "anim_LtChange$Lt_Position 1.000 anim_LtChange$HeaderDynamic 0.000";
						break;
					case "Alt_F8": case "F8": case "F10":
						previewCommand = "anim_NamesuperCenterChange 1.000 anim_NamesuperCenterChange$Text 1.000 anim_NamesuperCenterChange$Logo 1.000 "
								+ "anim_NamesuperCenterChange$Colour 0.400 ";
						break;
					case "Control_F5": case "Control_F9": case "F9": case "Shift_E": case "d": case "e": case "j": case "Alt_a": case "Alt_s":
						previewCommand = "anim_LtChange 1.000 anim_LtChange$Headerband 1.000 anim_LtChange$Header 1.000 anim_LtChange$Logo 1.000 anim_LtChange$Subline 1.000 "
								+ "anim_LtChange$Lt_Position 1.000 anim_LtChange$HeaderDynamic 1.000";
						break;
					}
					break;
				}
			}
			switch (config.getBroadcaster().toUpperCase()) {
			case Constants.TRI_SERIES:  case Constants.MT20:
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER PREVIEW SCENE*/Default/gfx_Overlays " + "C:/Temp/Preview.tga " + previewCommand + "\0", print_writer);
				break;
			default:
				switch(whatToProcess.split(",")[0]) {
				case "Alt_Shift_Q":
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER PREVIEW SCENE*/Default/FieldDimesnsion " + "C:/Temp/Preview.tga " + previewCommand + "\0", print_writer);
					break;
				default:
					CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER PREVIEW SCENE*/Default/Overlays " + "C:/Temp/Preview.tga " + previewCommand + "\0", print_writer);
					break;
				}
			}
		}
	}

	public void processBugsPreview(String whatToProcess, List<PrintWriter> print_writer, int whichside, 
		Configuration config,String whichGraphicOnScreen) throws InterruptedException {
		if(config.getPreview().equalsIgnoreCase("WITH_PREVIEW")) {
			if(whichside == 1) {
				switch(config.getBroadcaster().toUpperCase()){
				case Constants.BAN_AFG_SERIES: case Constants.ACC:
					switch(whatToProcess.split(",")[0]) {
					case "Shift_O": case "Control_k": case "k": case "g": case "y": case "Control_Shift_R":
					case "h": case "Shift_F4": case "Shift_F":case "Alt_b": case "Alt_p": case "Control_Shift_F3":  
					case "Control_Shift_J": case "Control_y": case "Alt_Shift_J": case "Control_Shift_*":
						
						if(infobar.getInfobar_status() != null && !infobar.getInfobar_status().isEmpty()) {
							if(!infobar.getInfobar_status().equalsIgnoreCase(Constants.FORCED+Constants.SHRUNK_INFOBAR) && 
									!infobar.getInfobar_status().equalsIgnoreCase(Constants.SHRUNK_INFOBAR)) {
								CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER PREVIEW SCENE*/Default/Overlays C:/Temp/Preview.tga "
										+ "Anim_Infobar$FFIn 0.520  Bugs$In_Out 0.720 Bugs$In_Out$In 0.714\0", print_writer);
							}else {
								CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER PREVIEW SCENE*/Default/Overlays C:/Temp/Preview.tga "
										+ "Bugs$In_Out 0.720 Bugs$In_Out$In 0.714\0", print_writer);
							}
						}else {
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER PREVIEW SCENE*/Default/Overlays C:/Temp/Preview.tga "
									+ "Bugs$In_Out 0.720 Bugs$In_Out$In 0.714\0", print_writer);
						}
						break;
					case "Control_Shift_U": case "Control_Shift_V": case "Control_Shift_U_change_on": case "Control_Shift_V_change_on":
						
						if(infobar.getInfobar_status() != null && !infobar.getInfobar_status().isEmpty()) {
							if(!infobar.getInfobar_status().equalsIgnoreCase(Constants.FORCED+Constants.SHRUNK_INFOBAR) && 
									!infobar.getInfobar_status().equalsIgnoreCase(Constants.SHRUNK_INFOBAR)) {
								CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER PREVIEW SCENE*/Default/Overlays C:/Temp/Preview.tga "
										+ "Anim_Infobar$FFIn 0.520 PopUp$In_Out 1.360 PopUp$In_Out$In 1.320\0", print_writer);
							}else {
								CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER PREVIEW SCENE*/Default/Overlays C:/Temp/Preview.tga "
										+ "PopUp$In_Out 1.360 PopUp$In_Out$In 1.320\0", print_writer);
							}
						}else {
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER PREVIEW SCENE*/Default/Overlays C:/Temp/Preview.tga "
									+ "PopUp$In_Out 1.360 PopUp$In_Out$In 1.320\0", print_writer);
						}
						break;
					case "6": case "Control_4": case "5": case ";": case "Control_5": case "Control_7":
						 CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER PREVIEW SCENE*" + "/Default/Overlays C:/Temp/Preview.tga "
									+ "Counter$InOut 1.320 Counter$InOut$In 0.920\0", print_writer);
						 break;	
					case "Shift_C":
						if(infobar.getInfobar_status() != null && !infobar.getInfobar_status().isEmpty()) {
							if(!infobar.getInfobar_status().equalsIgnoreCase(Constants.FORCED+Constants.SHRUNK_INFOBAR) && 
									!infobar.getInfobar_status().equalsIgnoreCase(Constants.SHRUNK_INFOBAR)) {
								CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER PREVIEW SCENE*/Default/Overlays C:/Temp/Preview.tga "
										+ "Anim_Infobar$FFIn 0.520 SixDistance 1.300 SixDistance$In 0.800\0", print_writer);
							}else {
								CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER PREVIEW SCENE*/Default/Overlays C:/Temp/Preview.tga "
										+ "SixDistance 1.300 SixDistance$In 0.800\0", print_writer);
							}
						}else {
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER PREVIEW SCENE*/Default/Overlays C:/Temp/Preview.tga "
									+ "SixDistance 1.300 SixDistance$In 0.800\0", print_writer);
						}
						break;	
					}
					break;
				case Constants.TRI_SERIES:  case Constants.MT20:
					switch(whatToProcess.split(",")[0]) {
					case "Shift_O": case "Control_k": case "k": case "g": case "y": case "Control_y": case "h": case "Shift_F4": case "Shift_F":case "Alt_b":
					case ".": case "/": case "Shift_C": case "Control_Shift_R": case "Control_Shift_F3": case "Control_Shift_J": case "Alt_p": case "o": case "t":
						
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER PREVIEW SCENE*/Default/gfx_Overlays C:/Temp/Preview.tga "
								+ "Shrink$InOut 0.400 anim__Bug 0.720 anim__Bug$In_Out 0.720 anim__Bug$In_Out$Bug_In 0.720\0", print_writer);
						
						break;
					 case "Control_Shift_U": case "Control_Shift_V":
						 CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER PREVIEW SCENE*/Default/gfx_Overlays C:/Temp/Preview.tga "
									+ "PopUps$InOut 1.700 PopUps$InOut$In 1.700\0", print_writer);
						 break; 
					 case "6": case "Control_4": case "5": case ";":
						 CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER PREVIEW SCENE*" + "/Default/gfx_Overlays C:/Temp/Preview.tga "
									+ "PopUps$InOut 1.700 PopUps$InOut$In 1.700\0", print_writer);
						 break;
					 case "Control_Shift_*":
						 CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER PREVIEW SCENE*" + "/Default/gfx_Overlays C:/Temp/Preview.tga "
									+ "anim_Sponsor$InOut 1.2 anim_Sponsor$InOut$In 0.9\0", print_writer);
						 break;
					}
					break;
				}
			}else {
				switch(config.getBroadcaster().toUpperCase()){
				case Constants.BAN_AFG_SERIES: case Constants.ACC: 
					switch(whatToProcess.split(",")[0]) {
					case "Shift_O": case "Control_k": case "k": case "g": case "y": case "Control_Shift_R":
					case "h": case "Shift_F4": case "Shift_F":case "Alt_b": case "Alt_p": case "Control_Shift_F3":  
					case "Shift_C": case "Control_Shift_J": case "6":
					case "Control_y": case "Control_4": case "Alt_Shift_J": case "5": case ";":
					case "Control_Shift_*":
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER PREVIEW SCENE*/Default/Overlays C:/Temp/Preview.tga "
								+ "Bugs$Change 1.354 Bugs$Change$Change_Out 0.740 Bugs$Change$Change_In 1.354\0", print_writer);
						break;
					case "Control_Shift_U": case "Control_Shift_V": case "Control_Shift_U_change_on": case "Control_Shift_V_change_on":
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER PREVIEW SCENE*/Default/Overlays C:/Temp/Preview.tga "
								+ "PopUp$Change 1.200 PopUp$Change$Change_Out 0.421 PopUp$Change$Change_In 1.200\0", print_writer);
						break;
					}
					break;
				case Constants.TRI_SERIES:  case Constants.MT20:
					switch(whatToProcess.split(",")[0]) {
					case "Shift_O": case "Control_k": case "k": case "g": case "y": case "Control_y": case "h": case "Shift_F4": case "Shift_F":case "Alt_b":
					case ".": case "/":	case "Control_Shift_F3": case "Control_Shift_J":
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER PREVIEW SCENE*"
								+ "/Default/gfx_Overlays C:/Temp/Preview.tga Bug_Change 1.114 Bug_Change$BugChange_Out 0.62 Bug_Change$BugChange_In 1.114\0", print_writer);
						break;
					case "Control_Shift_U": case "Control_Shift_V":
						 CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER PREVIEW SCENE*" + "/Default/gfx_Overlays C:/Temp/Preview.tga "
									+ "PopUps$Change 1.000\0", print_writer);
						 break;	
					}
					break;	
				}
			}
		}
	}

	public void processMiniPreview(String whatToProcess, List<PrintWriter> print_writer, int whichside, Configuration config, String whichGraphicOnScreen) throws InterruptedException {
		if(config.getPreview().equalsIgnoreCase("WITH_PREVIEW")) {
			switch (config.getBroadcaster().toUpperCase()) {
			case Constants.BAN_AFG_SERIES: case Constants.ACC:
				if(whichside == 1) {
					switch(whatToProcess.split(",")[0]) {
					case "Shift_F1": case "Shift_F2": case "Alt_F1": case "Alt_F2":case "Alt_Shift_F8": case "Alt_F7":
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER PREVIEW SCENE*" 
								+ "/Default/Overlays C:/Temp/Preview.tga Mini$In_Out 1.180 Mini$In_Out$In 1.180 Mini$In_Out$In$BatDataIn 1.180\0", print_writer);
						
						TimeUnit.MILLISECONDS.sleep(500);
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER PREVIEW SCENE*" 
								+ "/Default/Overlays C:/Temp/Preview.tga Mini$In_Out 1.180 Mini$In_Out$In 1.180 Mini$In_Out$In$BatDataIn 1.180\0", print_writer);
						
						break;
					}
				}else {
					switch(whatToProcess.split(",")[0]) {
					case "Shift_F1": case "Shift_F2": case "Alt_F1": case "Alt_F2":case "Alt_Shift_F8": case "Alt_F7":
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER PREVIEW SCENE*" 
								+ "/Default/Overlays C:/Temp/Preview.tga Mini$Change 2.040 Mini$Change$Change_Out 0.760 "
								+ "Mini$Change$Change_In 2.040\0", print_writer);
						
						break;
					}
				}
				break;
			case Constants.TRI_SERIES:  case Constants.MT20:
				if(whatToProcess.contains(",")) {
					switch(whatToProcess.split(",")[0]) {
					case "Shift_F1": case "Shift_F2": case "Alt_F1": case "Alt_F2":case "Alt_Shift_F8": case "Alt_F7":
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER PREVIEW SCENE*" 
								+ "/Default/gfx_Overlays C:/Temp/Preview.tga Anim_Mini$In_Out 1.200 Anim_Mini$In_Out$In 1.260 "
								+ "Anim_Mini$In_Out$Out2 1.260\0", print_writer);
						
						TimeUnit.MILLISECONDS.sleep(500);
						CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER PREVIEW SCENE*" 
								+ "/Default/gfx_Overlays C:/Temp/Preview.tga Anim_Mini$In_Out 1.200 Anim_Mini$In_Out$In 1.260 "
								+ "Anim_Mini$In_Out$Out2 1.260\0", print_writer);
						
						break;
					}
					if(whichside == 2) {
						switch(whatToProcess.split(",")[0]) {
						case "Shift_F1": case "Shift_F2": case "Alt_F1": case "Alt_F2": case "Alt_F7":case "Alt_Shift_F8":
							CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER PREVIEW SCENE*" 
									+ "/Default/gfx_Overlays C:/Temp/Preview.tga Anim_MiniChange 1.860 Anim_MiniChange$Change_In 1.860 \0", print_writer);
							break;	
						}
					}
				}
				break;	
			}
		}
	}
	
	public void processInfoBarPreview(String whatToProcess, List<PrintWriter> print_writer, int whichside, Configuration config, String whichGraphicOnScreen) throws InterruptedException {
		
		if(config.getPreview().equalsIgnoreCase("WITH_PREVIEW")) {
			
		}
	}
	
	public String getWhichGraphicOnScreen() {
		return whichGraphicOnScreen;
	}

	public void setWhichGraphicOnScreen(String whichGraphicOnScreen) {
		this.whichGraphicOnScreen = whichGraphicOnScreen;
	}

	public String getSpecialBugOnScreen() {
		return specialBugOnScreen;
	}

	public void setSpecialBugOnScreen(String specialBugOnScreen) {
		this.specialBugOnScreen = specialBugOnScreen;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Infobar getInfobar() {
		return infobar;
	}

	public void setInfobar(Infobar infobar) {
		this.infobar = infobar;
	}

	@Override
	public String toString() {
		return "Animation [whichGraphicOnScreen=" + whichGraphicOnScreen + ", specialBugOnScreen=" + specialBugOnScreen
				+ ", status=" + status + ", caption=" + caption + ", lastNumberOfRows="
				+ lastNumberOfRows + "]";
	}

	
}
