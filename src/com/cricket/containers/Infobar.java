package com.cricket.containers;

import java.util.List;

import com.cricket.model.BattingCard;
import com.cricket.model.BowlingCard;

public class Infobar {
	
	private boolean infobar_on_screen = false;
	private boolean bottom_infobar_on_screen = false;
	private boolean powerplay_on_screen = false;
	private boolean powerplay_end = false;
	private boolean infobar_pushed = false;
	private boolean infobar_shrink = false;
	private boolean thisOvers_Title_Fade = false;
	private boolean challengeRunOnScreen = false;
	private boolean result_on_screen = false;
	private boolean top_stage = false;
	private boolean right_section_play = false;
	private boolean right_bottom_play = false;
	private boolean player_impact = false;
	private boolean target_on_screen = false;
	private boolean target_pushed = false;
	private boolean FieldPlotter_on_screen = false;
	private boolean is_player_outOrnot = false;
	private boolean player_outOrnot = false;
	private boolean freeHit_on_screen = false;
	private boolean HatTrick_on_screen = false;
	private boolean forced_powerplay_out = false;
	
	private int player_id,omo_value,highlight;
	
	private String infobar_status,infobar_photo,infobat_center_data;
	private String BatsmanAndBowlOrSponsor;
	
	private String section1;
	private String Last_section1;
	
	private String section2;
	private String Last_section2;
	
	private String section3;
	private String Last_section3;
	
	private String section5;
	private String Last_section5;
	
	private String section4;
	private String Last_section4;

	private String sectionAnalytics;
	private String Last_sectionAnalytics;
	
	private String sectionLtAnalytics;
	private String Last_sectionLtAnalytics;
	
	private String infobar_ident_section;
	private String Last_which_team;
	private String Which_team;
	private String identContainer;
	
	private List<BattingCard> last_batsmen;
	private BowlingCard last_bowler;
	private String last_this_over;
	
	private String last_speed_value;
	private String last_ball_value;
	private String last_wide_value;
	private String last_noball_value;
	
	private List<String> freeText;
	
	public String getInfobar_ident_section() {
		return infobar_ident_section;
	}
	public void setInfobar_ident_section(String infobar_ident_section) {
		this.infobar_ident_section = infobar_ident_section;
	}
	public boolean isChallengeRunOnScreen() {
		return challengeRunOnScreen;
	}
	public void setChallengeRunOnScreen(boolean challengeRunOnScreen) {
		this.challengeRunOnScreen = challengeRunOnScreen;
	}
	public List<String> getFreeText() {
		return freeText;
	}
	public void setFreeText(List<String> freeText) {
		this.freeText = freeText;
	}
	public boolean isInfobar_pushed() {
		return infobar_pushed;
	}
	public void setInfobar_pushed(boolean infobar_pushed) {
		this.infobar_pushed = infobar_pushed;
	}
	public String getInfobar_status() {
		return infobar_status;
	}
	public void setInfobar_status(String infobar_status) {
		this.infobar_status = infobar_status;
	}
	public String getLast_this_over() {
		return last_this_over;
	}
	public void setLast_this_over(String last_this_over) {
		this.last_this_over = last_this_over;
	}
	public boolean isInfobar_on_screen() {
		return infobar_on_screen;
	}
	public void setInfobar_on_screen(boolean infobar_on_screen) {
		this.infobar_on_screen = infobar_on_screen;
	}
	public boolean isPowerplay_on_screen() {
		return powerplay_on_screen;
	}
	public void setPowerplay_on_screen(boolean powerplay_on_screen) {
		this.powerplay_on_screen = powerplay_on_screen;
	}
	public List<BattingCard> getLast_batsmen() {
		return last_batsmen;
	}
	public void setLast_batsmen(List<BattingCard> last_batsmen) {
		this.last_batsmen = last_batsmen;
	}
	public BowlingCard getLast_bowler() {
		return last_bowler;
	}
	public void setLast_bowler(BowlingCard last_bowler) {
		this.last_bowler = last_bowler;
	}
	public int getPlayer_id() {
		return player_id;
	}
	public void setPlayer_id(int player_id) {
		this.player_id = player_id;
	}
	public String getBatsmanAndBowlOrSponsor() {
		return BatsmanAndBowlOrSponsor;
	}
	public void setBatsmanAndBowlOrSponsor(String batsmanAndBowlOrSponsor) {
		BatsmanAndBowlOrSponsor = batsmanAndBowlOrSponsor;
	}
	public boolean isThisOvers_Title_Fade() {
		return thisOvers_Title_Fade;
	}
	public void setThisOvers_Title_Fade(boolean thisOvers_Title_Fade) {
		this.thisOvers_Title_Fade = thisOvers_Title_Fade;
	}
	public boolean isPowerplay_end() {
		return powerplay_end;
	}
	public void setPowerplay_end(boolean powerplay_end) {
		this.powerplay_end = powerplay_end;
	}
	public boolean isResult_on_screen() {
		return result_on_screen;
	}
	public void setResult_on_screen(boolean result_on_screen) {
		this.result_on_screen = result_on_screen;
	}
	public boolean isTop_stage() {
		return top_stage;
	}
	public void setTop_stage(boolean top_stage) {
		this.top_stage = top_stage;
	}
	public boolean isRight_section_play() {
		return right_section_play;
	}
	public void setRight_section_play(boolean right_section_play) {
		this.right_section_play = right_section_play;
	}
	public boolean isPlayer_impact() {
		return player_impact;
	}
	public void setPlayer_impact(boolean player_impact) {
		this.player_impact = player_impact;
	}
	public boolean isTarget_on_screen() {
		return target_on_screen;
	}
	public void setTarget_on_screen(boolean target_on_screen) {
		this.target_on_screen = target_on_screen;
	}
	public boolean isFieldPlotter_on_screen() {
		return FieldPlotter_on_screen;
	}
	public void setFieldPlotter_on_screen(boolean fieldPlotter_on_screen) {
		FieldPlotter_on_screen = fieldPlotter_on_screen;
	}
	public boolean isIs_player_outOrnot() {
		return is_player_outOrnot;
	}
	public void setIs_player_outOrnot(boolean is_player_outOrnot) {
		this.is_player_outOrnot = is_player_outOrnot;
	}
	public boolean isPlayer_outOrnot() {
		return player_outOrnot;
	}
	public void setPlayer_outOrnot(boolean player_outOrnot) {
		this.player_outOrnot = player_outOrnot;
	}
	public String getLast_speed_value() {
		return last_speed_value;
	}
	public void setLast_speed_value(String last_speed_value) {
		this.last_speed_value = last_speed_value;
	}
	public String getLast_ball_value() {
		return last_ball_value;
	}
	public void setLast_ball_value(String last_ball_value) {
		this.last_ball_value = last_ball_value;
	}
	public String getLast_wide_value() {
		return last_wide_value;
	}
	public void setLast_wide_value(String last_wide_value) {
		this.last_wide_value = last_wide_value;
	}
	public String getLast_noball_value() {
		return last_noball_value;
	}
	public void setLast_noball_value(String last_noball_value) {
		this.last_noball_value = last_noball_value;
	}
	public boolean isTarget_pushed() {
		return target_pushed;
	}
	public void setTarget_pushed(boolean target_pushed) {
		this.target_pushed = target_pushed;
	}
	public boolean isBottom_infobar_on_screen() {
		return bottom_infobar_on_screen;
	}
	public void setBottom_infobar_on_screen(boolean bottom_infobar_on_screen) {
		this.bottom_infobar_on_screen = bottom_infobar_on_screen;
	}
	public boolean isFreeHit_on_screen() {
		return freeHit_on_screen;
	}
	public void setFreeHit_on_screen(boolean freeHit_on_screen) {
		this.freeHit_on_screen = freeHit_on_screen;
	}
	public boolean isRight_bottom_play() {
		return right_bottom_play;
	}
	public void setRight_bottom_play(boolean right_bottom_play) {
		this.right_bottom_play = right_bottom_play;
	}
	public boolean isInfobar_shrink() {
		return infobar_shrink;
	}
	public void setInfobar_shrink(boolean infobar_shrink) {
		this.infobar_shrink = infobar_shrink;
	}
	public String getInfobar_photo() {
		return infobar_photo;
	}
	public void setInfobar_photo(String infobar_photo) {
		this.infobar_photo = infobar_photo;
	}
	public int getOmo_value() {
		return omo_value;
	}
	public void setOmo_value(int omo_value) {
		this.omo_value = omo_value;
	}
	public String getInfobat_center_data() {
		return infobat_center_data;
	}
	public void setInfobat_center_data(String infobat_center_data) {
		this.infobat_center_data = infobat_center_data;
	}
	public String getSection1() {
		return section1;
	}
	public void setSection1(String section1) {
		this.section1 = section1;
	}
	public String getLast_section1() {
		return Last_section1;
	}
	public void setLast_section1(String last_section1) {
		Last_section1 = last_section1;
	}
	public String getSection2() {
		return section2;
	}
	public void setSection2(String section2) {
		this.section2 = section2;
	}
	public String getLast_section2() {
		return Last_section2;
	}
	public void setLast_section2(String last_section2) {
		Last_section2 = last_section2;
	}
	public String getSection3() {
		return section3;
	}
	public void setSection3(String section3) {
		this.section3 = section3;
	}
	public String getLast_section3() {
		return Last_section3;
	}
	public void setLast_section3(String last_section3) {
		Last_section3 = last_section3;
	}
	public String getSection5() {
		return section5;
	}
	public void setSection5(String section5) {
		this.section5 = section5;
	}
	public String getLast_section5() {
		return Last_section5;
	}
	public void setLast_section5(String last_section5) {
		Last_section5 = last_section5;
	}
	public String getSection4() {
		return section4;
	}
	public void setSection4(String section4) {
		this.section4 = section4;
	}
	public String getLast_section4() {
		return Last_section4;
	}
	public void setLast_section4(String last_section4) {
		Last_section4 = last_section4;
	}
	public String getSectionAnalytics() {
		return sectionAnalytics;
	}
	public void setSectionAnalytics(String sectionAnalytics) {
		this.sectionAnalytics = sectionAnalytics;
	}
	public String getLast_sectionAnalytics() {
		return Last_sectionAnalytics;
	}
	public void setLast_sectionAnalytics(String last_sectionAnalytics) {
		Last_sectionAnalytics = last_sectionAnalytics;
	}
	public String getSectionLtAnalytics() {
		return sectionLtAnalytics;
	}
	public void setSectionLtAnalytics(String sectionLtAnalytics) {
		this.sectionLtAnalytics = sectionLtAnalytics;
	}
	public String getLast_sectionLtAnalytics() {
		return Last_sectionLtAnalytics;
	}
	public void setLast_sectionLtAnalytics(String last_sectionLtAnalytics) {
		Last_sectionLtAnalytics = last_sectionLtAnalytics;
	}
	public String getLast_which_team() {
		return Last_which_team;
	}
	public void setLast_which_team(String last_which_team) {
		Last_which_team = last_which_team;
	}
	public String getWhich_team() {
		return Which_team;
	}
	public void setWhich_team(String which_team) {
		Which_team = which_team;
	}
	public boolean isForced_powerplay_out() {
		return forced_powerplay_out;
	}
	public void setForced_powerplay_out(boolean forced_powerplay_out) {
		this.forced_powerplay_out = forced_powerplay_out;
	}
	public boolean isHatTrick_on_screen() {
		return HatTrick_on_screen;
	}
	public void setHatTrick_on_screen(boolean hatTrick_on_screen) {
		HatTrick_on_screen = hatTrick_on_screen;
	}
	public String getIdentContainer() {
		return identContainer;
	}
	public void setIdentContainer(String identContainer) {
		this.identContainer = identContainer;
	}
	
}
