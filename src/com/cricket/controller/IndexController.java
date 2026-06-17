package com.cricket.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import com.cricket.captions.Animation;
import com.cricket.captions.BugsAndMiniGfx;
import com.cricket.captions.Caption;
import com.cricket.captions.Constants;
import com.cricket.captions.FullFramesGfx;
import com.cricket.captions.InfobarGfx;
import com.cricket.captions.LowerThirdGfx;
import com.cricket.captions.Scene;
import com.cricket.config.DatabaseContextHolder;
import com.cricket.containers.Infobar;
import com.cricket.containers.Stats;
import com.cricket.model.BestStats;
import com.cricket.model.Bugs;
import com.cricket.model.Commentator;
import com.cricket.model.Configuration;
import com.cricket.model.DuckWorthLewis;
import com.cricket.model.EventFile;
import com.cricket.model.EverestBugs;
import com.cricket.model.FieldersData;
import com.cricket.model.Fixture;
import com.cricket.model.Ground;
import com.cricket.model.HeadToHead;
import com.cricket.model.InfobarStats;
import com.cricket.model.Match;
import com.cricket.model.MatchAllData;
import com.cricket.model.MatchStats;
import com.cricket.model.MultiLanguageDatabase;
import com.cricket.model.NameSuper;
import com.cricket.model.POTT;
import com.cricket.model.PerformanceBug;
import com.cricket.model.Player;
import com.cricket.model.Playoff;
import com.cricket.model.Setup;
import com.cricket.model.Staff;
import com.cricket.model.Statistics;
import com.cricket.model.Team;
import com.cricket.model.Tournament;
import com.cricket.model.VariousText;
import com.cricket.service.CricketService;
import com.cricket.util.CricketFunctions;
import com.cricket.util.CricketUtil;
import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
@SessionAttributes(value = {"session_configuration","expiryDate"})
public class IndexController 
{
	@Autowired
	CricketService cricketService;
	
	public static MatchAllData session_match;
	public static String expiry_date = "2026-12-31";
	public static String current_date;
	public static long last_match_time_stamp = 0;
	public static Scene this_scene;
	public static Caption this_caption;
	public static Animation this_animation;
	public static List<PrintWriter> print_writers;
	public static MatchStats MatchStats;
	public static boolean show_speed = false;
	public static ObjectMapper objectMapper = new ObjectMapper();
	public static List<MatchAllData> cricket_matches = new ArrayList<MatchAllData>();
	public static Map<Integer, List<String>> this_seriesPowerplay =new HashMap<Integer, List<String>>();
	public static List<Tournament> past_tournament_stats = new ArrayList<Tournament>();
	public static HeadToHead headToHead = new HeadToHead ();
	public static List<BestStats> past_tape = new ArrayList<BestStats>();
	public static List<Statistics> session_statistics = new ArrayList<Statistics>();
	public static Statistics session_statistics_past_matches = new Statistics();
	public static List<NameSuper> session_name_super = new ArrayList<NameSuper>();
	public static List<Bugs> session_bugs = new ArrayList<Bugs>();
	public static List<EverestBugs> session_bugs_everest = new ArrayList<EverestBugs>();
	public static List<InfobarStats> session_infoBarStats = new ArrayList<InfobarStats>();
	public static List<Fixture> session_fixture = new ArrayList<Fixture>(); 
	public static List<Team> session_team = new ArrayList<Team>(); 
	public static List<Ground> session_ground = new ArrayList<Ground>();
	public static List<VariousText> session_variousText = new ArrayList<VariousText>();
	public static List<Commentator> session_commentator = new ArrayList<Commentator>();
	public static List<Staff> session_staff = new ArrayList<Staff>();
	public static List<Player> session_players = new ArrayList<Player>();
	public static List<POTT> session_pott = new ArrayList<POTT>();
	public static List<Playoff> session_playoff = new ArrayList<Playoff>();
	public static List<String> session_teamChanges = new ArrayList<String>();
	public static List<PerformanceBug> session_performance_bug = new ArrayList<PerformanceBug>();
	
	File speedFile = new File("C:\\Sports\\Cricket\\Speed\\SPEED.txt");
	
	public static long plotter_match_time_stamp1=0,plotter_match_time_stamp2=0,
			plotter_match_time_stamp3=0,plotter_match_time_stamp4=0,plotter_match_time_stamp=0,speed_match_time_stamp=0,speedfile_match_time_stamp = 0;
	public static String plotterData,speedPath;
	public boolean Plotter_file_change = false;
	
	FieldersData fielderFormation = new FieldersData();
	
	BugsAndMiniGfx this_bugs_mini = new BugsAndMiniGfx();
	
	List<DuckWorthLewis> session_dls = new ArrayList<DuckWorthLewis>();
	
	@RequestMapping(value = {"/","/initialise"}, method={RequestMethod.GET,RequestMethod.POST}) 
	public String initialisePage(ModelMap model, 
		@ModelAttribute("session_configuration") Configuration session_configuration,
		@ModelAttribute("expiryDate") String expiryDate,
		@RequestParam(value = "select_type", required = false, defaultValue = "") String select_type) 
		throws JAXBException, MalformedURLException, IOException, IllegalAccessException, InvocationTargetException, jakarta.xml.bind.JAXBException 
	{
		
		if(current_date == null || current_date.isEmpty()) {
			current_date = CricketFunctions.getOnlineCurrentDate();
		}

		if(select_type.trim().isEmpty()) {
			model.addAttribute("match_files", new File(CricketUtil.CRICKET_SERVER_DIRECTORY + CricketUtil.MATCHES_DIRECTORY).listFiles(new FileFilter() {
				@Override
			    public boolean accept(File pathname) {
			        String name = pathname.getName().toLowerCase();
			        return name.endsWith(".json") && pathname.isFile();
			    }
			}));
		}
		model.addAttribute("configuration_files", new File(CricketUtil.CRICKET_SERVER_DIRECTORY + CricketUtil.CONFIGURATIONS_DIRECTORY).listFiles(new FileFilter() {
			@Override
		    public boolean accept(File pathname) {
		        String name = pathname.getName().toLowerCase();
		        return name.endsWith(".xml") && pathname.isFile();
		    }
		}));
		
		if(select_type.trim().isEmpty()) {
			if(cricket_matches == null || cricket_matches.size()<=0) {
				cricket_matches = CricketFunctions.getTournamentMatches(new File(CricketUtil.CRICKET_SERVER_DIRECTORY + 
						CricketUtil.MATCHES_DIRECTORY).listFiles(new FileFilter() {
					@Override
				    public boolean accept(File pathname) {
				        String name = pathname.getName().toLowerCase();
				        return name.endsWith(".json") && pathname.isFile();
				    }
				}), cricketService);
			}
		}
		
//		headToHead = CricketFunctions.extractHeadToHead(new File(CricketUtil.CRICKET_SERVER_DIRECTORY + 
//				CricketUtil.HEADTOHEAD_DIRECTORY).listFiles(), cricketService);
		DatabaseContextHolder.setDb("LOCAL");
		return "initialise";
	}
	
	@RequestMapping(value = {"/Help"}, method={RequestMethod.GET,RequestMethod.POST}) 
		public String HelpPage()  
		{
			return "Help";
		}
		
	@RequestMapping(value = {"/output"}, method={RequestMethod.GET,RequestMethod.POST},
		consumes = {MediaType.MULTIPART_FORM_DATA_VALUE}) 
	public String outputPage(ModelMap model,
			@ModelAttribute("session_configuration") Configuration session_configuration,
			@ModelAttribute("expiryDate") String expiryDate,
			@RequestParam(value = "configuration_file_name", required = false, defaultValue = "") String configuration_file_name,
			@RequestParam(value = "select_cricket_matches", required = false, defaultValue = "") String selectedMatch,
			@RequestParam(value = "select_type", required = false, defaultValue = "") String select_type,
			@RequestParam(value = "select_broadcaster", required = false, defaultValue = "") String select_broadcaster,
			@RequestParam(value = "select_second_broadcaster", required = false, defaultValue = "") String select_second_broadcaster,
			@RequestParam(value = "generateInteractiveFile", required = false, defaultValue = "") String generateInteractiveFile,
			@RequestParam(value = "qtIPAddress", required = false, defaultValue = "") String qtIPAddress,
			@RequestParam(value = "qtPortNumber", required = false, defaultValue = "") int qtPortNumber,
			@RequestParam(value = "vizIPAddress", required = false, defaultValue = "") String vizIPAddress,
			@RequestParam(value = "vizPortNumber", required = false, defaultValue = "") int vizPortNumber,
			@RequestParam(value = "vizSceneName", required = false, defaultValue = "") String vizScene,
			@RequestParam(value = "vizLanguage", required = false, defaultValue = "") String vizLanguage,
			@RequestParam(value = "primaryVariousOptions", required = false, defaultValue = "") String primaryVariousOptions,
			@RequestParam(value = "vizSecondaryIPAddress", required = false, defaultValue = "") String vizSecondaryIPAddress,
			@RequestParam(value = "vizSecondaryPortNumber", required = false, defaultValue = "") int vizSecondaryPortNumber,
			@RequestParam(value = "vizSecondaryScene", required = false, defaultValue = "") String vizSecondaryScene,
			@RequestParam(value = "vizSecondaryLanguage", required = false, defaultValue = "") String vizSecondaryLanguage,
			@RequestParam(value = "vizTertiaryIPAddress", required = false, defaultValue = "") String vizTertiaryIPAddress,
			@RequestParam(value = "vizTertiaryPortNumber", required = false, defaultValue = "") int vizTertiaryPortNumber,
			@RequestParam(value = "vizTertiaryScene", required = false, defaultValue = "") String vizTertiaryScene,
			@RequestParam(value = "vizTertiaryLanguage", required = false, defaultValue = "") String vizTertiaryLanguage,
			@RequestParam(value = "previewOnOrOff", required = false, defaultValue = "") String previewOnOrOff,
			@RequestParam(value = "selectInfobar", required = false, defaultValue = "") String selectInfobar,
			@RequestParam(value = "Category", required = false, defaultValue = "") String Category)
				throws StreamWriteException, DatabindException, IllegalAccessException, InvocationTargetException, 
				JAXBException, IOException, URISyntaxException, ParseException, InterruptedException, CloneNotSupportedException, jakarta.xml.bind.JAXBException 
	{
		if(current_date == null || current_date.isEmpty()) {
			
			model.addAttribute("error_message","You must be connected to the internet online");
			return "error";
		
		} else if(new SimpleDateFormat("yyyy-MM-dd").parse(expiry_date).before(new SimpleDateFormat("yyyy-MM-dd").parse(current_date))) {
			
			model.addAttribute("error_message","This software has expired");
			return "error";
			
		}else {

			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			
			LocalDate date1 = LocalDate.parse(current_date, dtf);
			LocalDate date2 = LocalDate.parse(expiry_date, dtf);
			
			long daysBetween = ChronoUnit.DAYS.between(date1, date2);
			
			expiryDate = String.valueOf(daysBetween);
			
			if (select_type == null || select_type.trim().isEmpty() || 
					select_type.equals(",")) {
				last_match_time_stamp = new File(CricketUtil.CRICKET_SERVER_DIRECTORY + CricketUtil.MATCHES_DIRECTORY 
						+ selectedMatch).lastModified();
		    } else {
		    	last_match_time_stamp = new File(CricketUtil.CRICKET_ARCHIVE_DIRECTORY + CricketUtil.ARCHIVE_MATCHES_DIRECTORY + select_type.split(",", -1)[0] + "/" +
		                CricketUtil.MATCHES_DIRECTORY + selectedMatch).lastModified();
		    }
			
			session_configuration = new Configuration(selectedMatch, select_broadcaster, select_second_broadcaster,
				vizIPAddress, vizPortNumber, vizLanguage, qtIPAddress, qtPortNumber, primaryVariousOptions, vizSecondaryIPAddress,
				vizSecondaryPortNumber, vizSecondaryLanguage, previewOnOrOff,selectInfobar, generateInteractiveFile, Category,select_type.split(",",-1)[0]);
			//session_configuration.setCategory(Category); // Category parameter added to constructor
			
			JAXBContext.newInstance(Configuration.class).createMarshaller().marshal(session_configuration, 
					new File(CricketUtil.CRICKET_SERVER_DIRECTORY + CricketUtil.CONFIGURATIONS_DIRECTORY + configuration_file_name));
		
			print_writers = CricketFunctions.processPrintWriter(session_configuration);
			
			session_match = new MatchAllData();
			this_scene = new Scene();
			this_animation = new Animation(new Infobar());
			GetVariousDBData("NEW", session_configuration);
						
			if (select_type == null || select_type.trim().isEmpty() || 
					select_type.equals(",")) {
				if(new File(CricketUtil.CRICKET_DIRECTORY + CricketUtil.SETUP_DIRECTORY + selectedMatch).exists()) {
					session_match.setSetup(objectMapper.readValue(new File(CricketUtil.CRICKET_DIRECTORY + CricketUtil.SETUP_DIRECTORY + 
						selectedMatch), Setup.class));
				}
				if(new File(CricketUtil.CRICKET_DIRECTORY + CricketUtil.MATCHES_DIRECTORY + selectedMatch).exists()) {
					session_match.setMatch(objectMapper.readValue(new File(CricketUtil.CRICKET_DIRECTORY + CricketUtil.MATCHES_DIRECTORY + 
						selectedMatch), Match.class));
				}
				
				if(new File(CricketUtil.CRICKET_DIRECTORY + CricketUtil.EVENT_DIRECTORY + selectedMatch).exists()) {
					session_match.setEventFile(objectMapper.readValue(new File(CricketUtil.CRICKET_DIRECTORY + CricketUtil.EVENT_DIRECTORY + 
						selectedMatch), EventFile.class));
				}
		    } else {
		    	if(new File(CricketUtil.CRICKET_ARCHIVE_DIRECTORY + CricketUtil.ARCHIVE_MATCHES_DIRECTORY + select_type.split(",", -1)[0] + "/" + CricketUtil.SETUP_DIRECTORY + selectedMatch).exists()) {
					session_match.setSetup(objectMapper.readValue(new File(CricketUtil.CRICKET_ARCHIVE_DIRECTORY + CricketUtil.ARCHIVE_MATCHES_DIRECTORY + select_type.split(",", -1)[0] + "/" + CricketUtil.SETUP_DIRECTORY + 
						selectedMatch), Setup.class));
				}
				if(new File(CricketUtil.CRICKET_ARCHIVE_DIRECTORY + CricketUtil.ARCHIVE_MATCHES_DIRECTORY + select_type.split(",", -1)[0] + "/" + CricketUtil.MATCHES_DIRECTORY + selectedMatch).exists()) {
					session_match.setMatch(objectMapper.readValue(new File(CricketUtil.CRICKET_ARCHIVE_DIRECTORY + CricketUtil.ARCHIVE_MATCHES_DIRECTORY + select_type.split(",", -1)[0] + "/" + CricketUtil.MATCHES_DIRECTORY + 
						selectedMatch), Match.class));
				}
				
				if(new File(CricketUtil.CRICKET_ARCHIVE_DIRECTORY + CricketUtil.ARCHIVE_MATCHES_DIRECTORY + select_type.split(",", -1)[0] + "/" + CricketUtil.EVENT_DIRECTORY + selectedMatch).exists()) {
					session_match.setEventFile(objectMapper.readValue(new File(CricketUtil.CRICKET_ARCHIVE_DIRECTORY + CricketUtil.ARCHIVE_MATCHES_DIRECTORY + select_type.split(",", -1)[0] + "/" + CricketUtil.EVENT_DIRECTORY + 
						selectedMatch), EventFile.class));
				}
		    }
			
			session_match.getMatch().setMatchFileName(selectedMatch);
			session_match = CricketFunctions.populateMatchVariables(CricketFunctions.readOrSaveMatchFile(CricketUtil.READ, 
				CricketUtil.SETUP + "," + CricketUtil.MATCH + "," + CricketUtil.EVENT, session_match,session_configuration, CricketUtil.CRICKET_DIRECTORY), 
				session_players, session_team, session_ground);
			
			if(!session_match.getMatch().getSourceOfThisMatchData().equalsIgnoreCase("WEBSITE")) {
				MatchStats = CricketFunctions.getAllEvents(session_match,session_configuration.getBroadcaster(), 
						session_match.getEventFile().getEvents());
			}
			
			session_match.getSetup().setMatchFileTimeStamp(new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(new Date()));
			session_match.getSetup().setGenerateInteractiveFile(session_configuration.getGenerateInteractiveFile());
			CricketFunctions.getInteractive(session_match, "FULL_WRITE", CricketUtil.CRICKET_DIRECTORY);

			headToHead = CricketFunctions.extractHeadToHead(session_match, cricketService, CricketUtil.CRICKET_DIRECTORY);
			//this_seriesPowerplay = CricketFunctions.PowerPlayTeamThisSeries(session_match, cricket_matches);
				
			//past_tournament_stats = CricketFunctions.extractTournamentData("PAST_MATCHES_DATA", false, headToHead, cricketService, session_match, null);
			session_match.getMatch().setMatchStats(MatchStats);
			GetVariousDBData("NEW", session_configuration);
			
			switch (select_broadcaster) {
			case Constants.BCCI: case Constants.TRI_SERIES: case Constants.BAN_AFG_SERIES: case Constants.ACC: case Constants.AFG_SL_SERIES:
			case Constants.MT20: case Constants.TG20:
				if(session_configuration.getPrimaryVariousOptions().contains(Constants.FULL_FRAMER)) {
					this_scene.LoadScene("FULL-FRAMERS", print_writers, session_configuration);
				}
				this_scene.LoadScene("OVERLAYS", print_writers, session_configuration);
				this_animation.ResetAnimation("CLEAR-ALL", print_writers, session_configuration);
				break;
			}
			
			if(session_match.getMatch().getInning() != null) {
				model.addAttribute("which_inning", session_match.getMatch().getInning().stream().filter(inn -> inn.getIsCurrentInning()
					.equalsIgnoreCase(CricketUtil.YES)).findAny().orElse(null).getInningNumber());
			} else {
				model.addAttribute("which_inning", "1");
			}
			
			model.addAttribute("session_match", session_match);
			model.addAttribute("expiryDate", expiryDate);
			model.addAttribute("session_configuration", session_configuration);
			model.addAttribute("select_type", select_type);
			model.addAttribute("select_second_broadcaster", select_second_broadcaster);
			model.addAttribute("select_broadcaster", select_broadcaster);			
			return "output";
		}
	}

	@RequestMapping(value = {"/processCricketProcedures.html"}, method={RequestMethod.GET,RequestMethod.POST})    
	public @ResponseBody String processCricketProcedures(
		@ModelAttribute("session_configuration") Configuration session_configuration,
		@RequestParam(value = "whatToProcess", required = false, defaultValue = "") String whatToProcess,
		@RequestParam(value = "valueToProcess", required = false, defaultValue = "") String valueToProcess) 
			throws Exception 
	{
		switch (whatToProcess.toUpperCase()) {
		
		case "GET_ARCHIVE_MATCH_FILES":
			File[] files;
		    
			if (valueToProcess.isEmpty()) {
		    	DatabaseContextHolder.setDb("LOCAL");
		    	for (Player plyr : cricketService.getAllPlayer()) {
					System.out.println("plyr full name = " + plyr.getFull_name());
				}
		        files = new File(CricketUtil.CRICKET_SERVER_DIRECTORY + CricketUtil.MATCHES_DIRECTORY)
		                .listFiles(pathname -> pathname.isFile() && pathname.getName().toLowerCase().endsWith(".json"));
		    } else {
		    	DatabaseContextHolder.setDb("ARCHIVE");
		    	for (Player plyr : cricketService.getAllPlayer()) {
					System.out.println("plyr full name = " + plyr.getFull_name());
				}
		    	files = new File(CricketUtil.CRICKET_ARCHIVE_DIRECTORY + CricketUtil.ARCHIVE_MATCHES_DIRECTORY + valueToProcess + "/" +
		                CricketUtil.MATCHES_DIRECTORY)
		                .listFiles(pathname -> pathname.isFile() && pathname.getName().toLowerCase().endsWith(".json"));
		    }
		    
		    List<String> matchNames = new ArrayList<>();
		    if (files != null) {
		        for (File f : files) {
		            matchNames.add(f.getName());
		        }
		    }
		    return objectMapper.writeValueAsString(matchNames).toString();
		    
		case "HEAD_TO_HEAD_FILE":
			CricketFunctions.exportMatchData(session_match, CricketUtil.CRICKET_DIRECTORY);

			return objectMapper.writeValueAsString(session_match).toString();
		case "GET-CONFIG-DATA":
			System.out.println("valueToProcess - " + valueToProcess);
			session_configuration = (Configuration)JAXBContext.newInstance(Configuration.class).createUnmarshaller().unmarshal(
				new File(CricketUtil.CRICKET_DIRECTORY + CricketUtil.CONFIGURATIONS_DIRECTORY + valueToProcess));
			
			return objectMapper.writeValueAsString(session_configuration).toString();
			
		case "DB_DATA_READ":
			GetVariousDBData("ONLY_DB", session_configuration);

			return objectMapper.writeValueAsString(session_match).toString();
			
		case "RE_READ_DATA":
			headToHead = CricketFunctions.extractHeadToHead(session_match, cricketService, CricketUtil.CRICKET_DIRECTORY);
			//this_seriesPowerplay = CricketFunctions.PowerPlayTeamThisSeries(session_match, cricket_matches);
			GetVariousDBData("UPDATE", session_configuration);
			return objectMapper.writeValueAsString(session_match).toString();
		
		case "TURN_ON_OR_OFF_SPEED":
			System.out.println("valueToProcess - " + valueToProcess);
			if(valueToProcess.equalsIgnoreCase("TRUE")) {
				show_speed = true;
			}else {
				show_speed = false;
			}
			return String.valueOf(show_speed);
			
		case "TURN_ON_OR_OFF_AUDIO":
			
			if(valueToProcess.equalsIgnoreCase("TRUE")) {
				this_animation.audioenabled = "TRUE";
			}else {
				this_animation.audioenabled = "FALSE";
			}
			return null;	
			
		case "READ-MATCH-AND-POPULATE":
			
			if(session_match == null) {
				return objectMapper.writeValueAsString(null).toString();
			}
			
			if(last_match_time_stamp != new File(CricketUtil.CRICKET_DIRECTORY + CricketUtil.MATCHES_DIRECTORY 
				+ session_match.getMatch().getMatchFileName()).lastModified()) {
				session_match = CricketFunctions.populateMatchVariables(CricketFunctions.readOrSaveMatchFile(CricketUtil.READ,
					CricketUtil.MATCH + "," + CricketUtil.EVENT, session_match,session_configuration, CricketUtil.CRICKET_DIRECTORY), session_players, session_team, session_ground);
				session_match.getSetup().setGenerateInteractiveFile(session_configuration.getGenerateInteractiveFile());
				last_match_time_stamp = new File(CricketUtil.CRICKET_DIRECTORY + CricketUtil.MATCHES_DIRECTORY 
						+ session_match.getMatch().getMatchFileName()).lastModified();
				MatchStats = CricketFunctions.getAllEvents(session_match,session_configuration.getBroadcaster(), session_match.getEventFile().getEvents());
				CricketFunctions.getInteractive(session_match, "FULL_WRITE", CricketUtil.CRICKET_DIRECTORY);
				
				session_match.getMatch().setMatchStats(MatchStats);
				
				this_caption.this_infobarGfx.updateInfobar(print_writers, session_match);
			}
			
			switch (session_configuration.getBroadcaster()) {
			case Constants.BCCI: case Constants.BAN_AFG_SERIES: case Constants.ACC: case Constants.AFG_SL_SERIES: case Constants.TG20:
				if(show_speed == true) {
					if (speedFile.exists()) {
						long currentTimestamp = speedFile.lastModified();
					    // Check if speed_match_time_stamp is initialized
					    if (speed_match_time_stamp == 0) {
					        speed_match_time_stamp = currentTimestamp; // Set the initial value if uninitialized
					    }

					    // Use a tolerance for comparison
					    if (Math.abs(speed_match_time_stamp - currentTimestamp) > 100) {
					        this_caption.this_infobarGfx.speed(CricketFunctions.processPrintWriter(session_configuration).get(0), session_match);
					        speed_match_time_stamp = currentTimestamp; // Update to the new timestamp
					    }
					} else {
					    //System.out.println("File does not exist.");
					}
				}else if(show_speed == false) {
					if (speedFile.exists()) {
						long currentTimestamp = speedFile.lastModified();
					    if (speed_match_time_stamp == 0) {
					        speed_match_time_stamp = currentTimestamp; // Set the initial value if uninitialized
					    }

					    if (Math.abs(speed_match_time_stamp - currentTimestamp) > 100) {
					        speed_match_time_stamp = currentTimestamp; // Update to the new timestamp
					    } else {
					        //System.out.println("No modification detected.");
					    }
					}
				}
				break;
			}
			if(new File("C:\\Sports\\Cricket\\Fielder\\Fielder_Text\\" + 
		            "FieldPlotter.txt").exists()) {
				
				String data = new String(Files.readAllBytes(Paths.get("C:\\Sports\\Cricket\\Fielder\\Fielder_Text\\" + 
			            "FieldPlotter.txt")));
		        // Split the content by lines and print each line separately
		        String[] lines = data.split("\n");
		        
		        plotterData = lines[0].trim();
		        
		        if(lines.length > 0) {
					if(lines[1].trim().equalsIgnoreCase("true")) {
						fielderFormation = CricketFunctions.getFielderFormation(CricketUtil.CRICKET_DIRECTORY + "Fielder/" + lines[0].trim());
						if(fielderFormation.isCheckbox() == true) {
							if(lines[0].trim().equalsIgnoreCase("FielderFormation.JSON")) {
								if(plotter_match_time_stamp != new File(CricketUtil.CRICKET_DIRECTORY + "Fielder/" + lines[0].trim()).lastModified()) {
									plotter_match_time_stamp = new File(CricketUtil.CRICKET_DIRECTORY + "Fielder/" + lines[0].trim()).lastModified();
									//System.out.println("AfterCheckBox = " + fielderFormation.isCheckbox());
									Plotter_file_change = true;
								}
							}else if(lines[0].trim().equalsIgnoreCase("FielderFormation_1.JSON")) {
								if(plotter_match_time_stamp1 != new File(CricketUtil.CRICKET_DIRECTORY + "Fielder/" + lines[0].trim()).lastModified()) {
									plotter_match_time_stamp1 = new File(CricketUtil.CRICKET_DIRECTORY + "Fielder/" + lines[0].trim()).lastModified();
									//System.out.println("AfterCheckBox = " + fielderFormation.isCheckbox());
									Plotter_file_change = true;
								}
							}else if(lines[0].trim().equalsIgnoreCase("FielderFormation_2.JSON")) {
								if(plotter_match_time_stamp2 != new File(CricketUtil.CRICKET_DIRECTORY + "Fielder/" + lines[0].trim()).lastModified()) {
									plotter_match_time_stamp2 = new File(CricketUtil.CRICKET_DIRECTORY + "Fielder/" + lines[0].trim()).lastModified();
									//System.out.println("AfterCheckBox = " + fielderFormation.isCheckbox());
									Plotter_file_change = true;
								}
							}else if(lines[0].trim().equalsIgnoreCase("FielderFormation_3.JSON")) {
								if(plotter_match_time_stamp3 != new File(CricketUtil.CRICKET_DIRECTORY + "Fielder/" + lines[0].trim()).lastModified()) {
									plotter_match_time_stamp3 = new File(CricketUtil.CRICKET_DIRECTORY + "Fielder/" + lines[0].trim()).lastModified();
									//System.out.println("AfterCheckBox = " + fielderFormation.isCheckbox());
									Plotter_file_change = true;
								}
							}else if(lines[0].trim().equalsIgnoreCase("FielderFormation_4.JSON")) {
								if(plotter_match_time_stamp4 != new File(CricketUtil.CRICKET_DIRECTORY + "Fielder/" + lines[0].trim()).lastModified()) {
									plotter_match_time_stamp4 = new File(CricketUtil.CRICKET_DIRECTORY + "Fielder/" + lines[0].trim()).lastModified();
									//System.out.println("AfterCheckBox = " + fielderFormation.isCheckbox());
									Plotter_file_change = true;
								}
							}
						}
					}else if(lines[1].trim().equalsIgnoreCase("false")) {
						
					}
				}
			}
			
			if(Plotter_file_change == true) {
				if(this_caption.this_infobarGfx!=null) {
					this_caption.this_infobarGfx.updateFieldPlotter(print_writers, session_match);
				}
				Plotter_file_change = false;
			}
			return objectMapper.writeValueAsString(session_match).toString();
		default:
			if(whatToProcess.toUpperCase().equalsIgnoreCase("IMPACT-CHANGE-ON")) {
				this_animation.AnimateIn("Shift_I", print_writers, session_configuration);
			}
			if(whatToProcess.toUpperCase().equalsIgnoreCase("PLAYING-XI-CHANGE-ON")) {
				this_animation.AnimateIn("Shift_T", print_writers, session_configuration);
			}
			if(whatToProcess.contains("GRAPHICS-OPTIONS")||whatToProcess.contains("GRAPHICS-OPTIONS_DATA")) {
				System.out.println("vtp:  "+valueToProcess);
				return objectMapper.writeValueAsString(GetGraphicOption(valueToProcess,session_configuration)).toString();
			}else if(whatToProcess.contains("POPULATE-GRAPHICS")) {
				this_animation.matchAllData = session_match;
				switch(this_animation.getTypeOfGraphicsOnScreen(session_configuration,valueToProcess)){
				case Constants.INFO_BAR:
					if(valueToProcess.split(",")[0].equalsIgnoreCase("Control_F12") || valueToProcess.split(",")[0].equalsIgnoreCase("Shift_F12")) {
						if(this_animation.infobar.isInfobar_on_screen()) {
							this_caption.whichSide = 2;
						} else {
							this_caption.whichSide = 1;
						}
						
						this_caption.PopulateGraphics(valueToProcess, session_match);
						this_animation.caption = this_caption;
//						this_animation.processInfoBarPreview(valueToProcess, print_writers, this_caption.whichSide, session_configuration, 
//								this_animation.whichGraphicOnScreen);
//						if(this_caption.status.equalsIgnoreCase(Constants.OK)) {
//							processAnimations("ANIMATE-IN-GRAPHICS", session_configuration, valueToProcess, print_writers);
//							if(this_animation.infobar.isInfobar_on_screen()) {
//								TimeUnit.MILLISECONDS.sleep(1000);
//								this_caption.this_infobarGfx.logosAndBaseColor(print_writers, "Control_F12", session_match, 1);
//								this_animation.processAnimation(Constants.FRONT, print_writers, "anim_Change$Logo_Colour", "SHOW 0.0");	
//							}
//							
//							return objectMapper.writeValueAsString(this_caption).toString();
//						} else {
//							return objectMapper.writeValueAsString(this_caption).toString();
//						}
					}else {
						if(this_animation.infobar.isInfobar_on_screen()) {
							this_caption.whichSide = 2;
						} else {
							this_caption.whichSide = 1;
						}
						
						this_caption.PopulateGraphics(valueToProcess, session_match);
						this_animation.caption = this_caption;
						
						if(this_caption.status.equalsIgnoreCase(Constants.OK)) {
							processAnimations("ANIMATE-IN-GRAPHICS", session_configuration, valueToProcess, print_writers);
							this_caption.status = CricketUtil.YES;
							return objectMapper.writeValueAsString(this_caption).toString();
						} else {
							return objectMapper.writeValueAsString(this_caption).toString();
						}
					}
					break;
				default:
					switch (session_configuration.getBroadcaster()) {
					case Constants.BCCI: case Constants.TRI_SERIES: case Constants.BAN_AFG_SERIES: case Constants.ACC: case Constants.AFG_SL_SERIES:
					case Constants.MT20: case Constants.TG20:
						if(!session_configuration.getPrimaryVariousOptions().contains(Constants.FULL_FRAMER)
							&& this_animation.getTypeOfGraphicsOnScreen(session_configuration, valueToProcess).contains(Constants.FULL_FRAMER)) {
							this_caption.setStatus("Error: Full framers captions NOT selected on start-up");
							return objectMapper.writeValueAsString(this_caption).toString();
						}
						break;
					}
					if(this_animation.whichGraphicOnScreen.isEmpty()) {
						if(!this_animation.specialBugOnScreen.equalsIgnoreCase(CricketUtil.TOSS)) {
							if(this_animation.infobar.isInfobar_on_screen() == false) {
								this_animation.ResetAnimation("CLEAR-ALL", print_writers, session_configuration);
							}else {
								this_animation.ResetAnimation("", print_writers, session_configuration);
							}
						}
						
						if(this_animation.getTypeOfGraphicsOnScreen(session_configuration, valueToProcess).contains(Constants.LOWER_THIRD)||
								this_animation.getTypeOfGraphicsOnScreen(session_configuration, valueToProcess).contains(Constants.NAME_SUPERS + Constants.LOWER_THIRD)) {
							if((this_caption.this_infobarGfx.infobar.getLast_sectionAnalytics() != null && 
									!this_caption.this_infobarGfx.infobar.getLast_sectionAnalytics().isEmpty()) || 
									(this_caption.this_infobarGfx.infobar.getLast_sectionLtAnalytics() != null && 
									!this_caption.this_infobarGfx.infobar.getLast_sectionLtAnalytics().isEmpty())) {
								
								this_caption.setStatus("Alt+8, Alt+3, Alt+4 is on screen.");	
								return objectMapper.writeValueAsString(this_caption).toString();
							}
						}
						this_caption.whichSide = 1;
					} else {
						//Don't allow L3rds change-on while FFs are on screen
						switch (this_animation.getTypeOfGraphicsOnScreen(session_configuration, this_animation.whichGraphicOnScreen)) {
						case Constants.FULL_FRAMER: case Constants.LOWER_THIRD: 
						case Constants.NAME_SUPERS + Constants.LOWER_THIRD:
						case Constants.BOUNDARIES + Constants.LOWER_THIRD:
						case Constants.BUGS:	
							
							if(this_animation.getTypeOfGraphicsOnScreen(session_configuration,valueToProcess) 
								!= this_animation.getTypeOfGraphicsOnScreen(session_configuration,this_animation.whichGraphicOnScreen)) {

								//Make a preview of lowerThird when FullFrames is on Screen and vice-verca
								switch (this_animation.getTypeOfGraphicsOnScreen(session_configuration, this_animation.whichGraphicOnScreen)) {
								case Constants.FULL_FRAMER: 
									switch (this_animation.getTypeOfGraphicsOnScreen(session_configuration, valueToProcess)) {
									case Constants.LOWER_THIRD: case Constants.NAME_SUPERS + Constants.LOWER_THIRD: 
									case Constants.BOUNDARIES + Constants.LOWER_THIRD:
										this_caption.whichSide = 1;
										this_caption.PopulateGraphics(valueToProcess, session_match);
										this_animation.processL3Preview(valueToProcess, print_writers, this_caption.whichSide, session_configuration,session_match);
										break;
									case Constants.BUGS: 
										this_caption.whichSide = 1;
										this_caption.PopulateGraphics(valueToProcess, session_match);
										this_animation.processBugsPreview(valueToProcess, print_writers, this_caption.whichSide, session_configuration,this_animation.whichGraphicOnScreen);
										break;	
									}
									break;
								case Constants.BUGS: 
									switch (this_animation.getTypeOfGraphicsOnScreen(session_configuration, valueToProcess)) {
									case Constants.FULL_FRAMER:
										this_caption.whichSide = 1;
										this_caption.PopulateGraphics(valueToProcess, session_match);
										this_animation.processFullFramesPreview(valueToProcess, print_writers, this_caption.whichSide, 
												session_configuration, this_animation.whichGraphicOnScreen);
										break;
									case Constants.LOWER_THIRD: case Constants.NAME_SUPERS + Constants.LOWER_THIRD: 
									case Constants.BOUNDARIES + Constants.LOWER_THIRD:
										this_caption.whichSide = 1;
										this_caption.PopulateGraphics(valueToProcess, session_match);
										this_animation.processL3Preview(valueToProcess, print_writers, this_caption.whichSide, session_configuration,session_match);
										break;
									}
									break;	
								case Constants.LOWER_THIRD: case Constants.NAME_SUPERS + Constants.LOWER_THIRD: 
								case Constants.BOUNDARIES + Constants.LOWER_THIRD:
									
									if(this_caption.this_infobarGfx.infobar.getLast_sectionAnalytics() != null && 
										!this_caption.this_infobarGfx.infobar.getLast_sectionAnalytics().isEmpty()) {
										
										this_caption.setStatus("Alt_8 is on screen");
										return objectMapper.writeValueAsString(this_caption).toString();
										
									}else if(this_caption.this_infobarGfx.infobar.getLast_sectionLtAnalytics() != null && 
											!this_caption.this_infobarGfx.infobar.getLast_sectionLtAnalytics().isEmpty()) {
										
										this_caption.setStatus("Alt_3 or Alt_4 is on screen");
										return objectMapper.writeValueAsString(this_caption).toString();
									}
								
									switch (this_animation.getTypeOfGraphicsOnScreen(session_configuration, valueToProcess)) {
									case Constants.FULL_FRAMER:
										this_caption.whichSide = 1;
										this_caption.PopulateGraphics(valueToProcess, session_match);
										this_animation.processFullFramesPreview(valueToProcess, print_writers, this_caption.whichSide, 
												session_configuration, this_animation.whichGraphicOnScreen);	
										break;
									case Constants.BUGS: 
										this_caption.whichSide = 1;
										this_caption.PopulateGraphics(valueToProcess, session_match);
										this_animation.processBugsPreview(valueToProcess, print_writers, this_caption.whichSide, session_configuration,this_animation.whichGraphicOnScreen);
										break;	
									}
									break;
								}
								
								this_caption.setStatus(this_animation.getTypeOfGraphicsOnScreen(session_configuration,
									this_animation.whichGraphicOnScreen).replace("_", " ") + " is on screen. "
									+ this_animation.getTypeOfGraphicsOnScreen(session_configuration,valueToProcess).replace(
									"_", " ") + " not allowed" );
								
								return objectMapper.writeValueAsString(this_caption).toString();

							}
							break;
						}
						this_caption.whichSide = 2;
					}
					
					this_caption.PopulateGraphics(valueToProcess, session_match);
					this_animation.caption = this_caption;
					//Previews
					switch (this_animation.getTypeOfGraphicsOnScreen(session_configuration, valueToProcess)) {
					case Constants.FULL_FRAMER:
						this_animation.processFullFramesPreview(valueToProcess, print_writers, this_caption.whichSide, 
								session_configuration, this_animation.whichGraphicOnScreen);
						break;
					case Constants.LOWER_THIRD: 
					case Constants.NAME_SUPERS + Constants.LOWER_THIRD:
					case Constants.BOUNDARIES + Constants.LOWER_THIRD:
						this_animation.processL3Preview(valueToProcess, print_writers, this_caption.whichSide, session_configuration,session_match);
						break;
					case Constants.BUGS:
						this_animation.processBugsPreview(valueToProcess, print_writers, this_caption.whichSide, 
							session_configuration, this_animation.whichGraphicOnScreen);
						break;
					case Constants.MINIS:
						this_animation.processMiniPreview(valueToProcess, print_writers, this_caption.whichSide, 
							session_configuration, this_animation.whichGraphicOnScreen);
						break;
					}
					break;
				}
				return objectMapper.writeValueAsString(this_caption.status);
			}
			else if(whatToProcess.contains("ANIMATE-IN-GRAPHICS") || whatToProcess.contains("ANIMATE-OUT-GRAPHICS")
				|| whatToProcess.contains("ANIMATE-OUT-INFOBAR") || whatToProcess.contains("QUIDICH-COMMANDS") || 
				whatToProcess.contains("ANIMATE-OUT-TAPE") || whatToProcess.contains("ANIMATE-OUT-IDENT") || 
				whatToProcess.contains("ANIMATE-OUT-TARGET") || whatToProcess.contains("ANIMATE-OUT-BOTTOM")) {

				if(whatToProcess.contains("ANIMATE-OUT-GRAPHICS")) {
					switch (valueToProcess.split(",")[0]) {
					case "Alt_p":
						if(!this_animation.whichGraphicOnScreen.isEmpty()) {
							this_animation.status = "Cannot animate out bugs while " + 
								this_animation.whichGraphicOnScreen + " is on screen";
							return objectMapper.writeValueAsString(this_animation).toString();
						}
						break;
					case "Alt_g":
						return objectMapper.writeValueAsString(this_animation).toString();
					}
				}
				
				processAnimations(whatToProcess, session_configuration, valueToProcess, print_writers);
			}else if(whatToProcess.contains("ANIMATE-OUT-SECOND_PLAYING")) {
				switch (session_configuration.getBroadcaster()) {
				case Constants.TRI_SERIES: case Constants.MT20: case Constants.TG20:
					if(this_animation.whichGraphicOnScreen.contains("Control_Shift_F7")) {
						this_animation.lineUpCount++;
//						if(this_animation.lineUpCount == 3) {
//							this_animation.lineUpCount = 0;
//							//this_animation.whichGraphicOnScreen = "";
//						}
						if(this_animation.lineUpCount > 2) {
							this_animation.processAnimation(Constants.BACK, print_writers, "Change_LineUp_Image$Main$Change_In", "CONTINUE");
						} else {
							this_animation.processAnimation(Constants.BACK, print_writers, "Anim_LineUp_Image$In_Out", "CONTINUE");
						}
						if(this_animation.lineUpCount == 1 || this_animation.lineUpCount == 3) {
							TimeUnit.MILLISECONDS.sleep(500);
							if(!this_caption.this_fullFramesGfx.this_ALL_FF.PlayerId.isEmpty()) {
								this_caption.this_fullFramesGfx.this_ALL_FF.populateTeamLineUpFooter(print_writers, 2, 
										whatToProcess.split(",")[0], session_configuration, "SHOW-SQUAD-NOT-PLAYING-TODAY", session_match);
									this_animation.processAnimation(Constants.BACK, print_writers, "Change_LineUp_Image$Footer", "START");
									TimeUnit.MILLISECONDS.sleep(600);
									this_caption.this_fullFramesGfx.this_ALL_FF.populateTeamLineUpFooter(print_writers, 1, 
										whatToProcess.split(",")[0], session_configuration, "SHOW-SQUAD-NOT-PLAYING-TODAY", session_match);
									this_animation.processAnimation(Constants.BACK, print_writers, "Change_LineUp_Image$Footer", "SHOW 0.0");
							}
						}
						
//						if(this_animation.lineUpCount == 0) {
//							TimeUnit.MILLISECONDS.sleep(2500);
//							if(this_animation.watermarkOnScreen.equalsIgnoreCase("WATERMARK")) {
//								this_animation.processAnimation(Constants.FRONT, print_writers, "Watermark", "START");
//							}
//						}
					}
					break;
				}
			}
			else if(whatToProcess.contains("ANIMATE-OUT-ALL_INFOBAR_PART")) {
				infobarAnimateOutAllSection(session_configuration, session_match, print_writers);
			}else if(whatToProcess.contains("GRAPHICS_PREVIEW-OPTIONS")) {
				return objectMapper.writeValueAsString(this_caption.this_infobarGfx.GetPreviewData(valueToProcess,session_configuration,session_match)).toString();
			}else if(whatToProcess.contains("CLEAR-ALL") || whatToProcess.contains("CLEAR-ALL-WITH-INFOBAR")) {
				this_animation.ResetAnimation(whatToProcess, print_writers, session_configuration);
				this_animation.ResetAnimation("CLEAR-INFOBAR_DATA", print_writers, session_configuration);
			}else if(whatToProcess.contains("CANCLE-GRAPHICS")) {
				this_caption.whichSide = 1;
			}
			return objectMapper.writeValueAsString(this_animation);
		}
	}
	public void infobarAnimateOutAllSection(Configuration session_configuration, MatchAllData session_match, List<PrintWriter> print_writers) throws Exception {
		switch(session_configuration.getBroadcaster()) {
		case Constants.BAN_AFG_SERIES:
			if(this_caption.this_infobarGfx.infobar.getSection5() != null && !this_caption.this_infobarGfx.infobar.getSection5().isEmpty()) {
				this_caption.PopulateGraphics("Alt_5,,BLANK", session_match);
				this_animation.caption = this_caption;
				processAnimations("ANIMATE-IN-GRAPHICS", session_configuration, "Alt_5,,BLANK", print_writers);
			}
			if(this_caption.this_infobarGfx.infobar.getSectionAnalytics() != null && !this_caption.this_infobarGfx.infobar.getSectionAnalytics().isEmpty()) {
				this_caption.PopulateGraphics("Alt_8,,BLANK", session_match);
				this_animation.caption = this_caption;
				processAnimations("ANIMATE-IN-GRAPHICS", session_configuration, "Alt_8,,BLANK", print_writers);
			}
			break;
		case Constants.ACC:
			
			if(this_caption.this_infobarGfx.infobar.getSection5() != null && !this_caption.this_infobarGfx.infobar.getSection5().isEmpty()) {
				this_caption.PopulateGraphics("Alt_5,,BLANK", session_match);
				this_animation.caption = this_caption;
				processAnimations("ANIMATE-IN-GRAPHICS", session_configuration, "Alt_5,,BLANK", print_writers);
			}
			
			if(this_caption.this_infobarGfx.infobar.getSectionAnalytics() != null && !this_caption.this_infobarGfx.infobar.getSectionAnalytics().isEmpty()) {
				this_caption.PopulateGraphics("Alt_8,,BLANK", session_match);
				this_animation.caption = this_caption;
				processAnimations("ANIMATE-IN-GRAPHICS", session_configuration, "Alt_8,,BLANK", print_writers);
			}
			break;	
		case Constants.TRI_SERIES: case Constants.MT20: case Constants.TG20:
			if(this_caption.this_infobarGfx.infobar.getSection2() != null && !this_caption.this_infobarGfx.infobar.getSection2().isEmpty()) {
				this_caption.PopulateGraphics("Alt_2,,BLANK", session_match);
				this_animation.caption = this_caption;
				processAnimations("ANIMATE-IN-GRAPHICS", session_configuration, "Alt_2,,BLANK", print_writers);
			}
			
			if(this_caption.this_infobarGfx.infobar.getSection4() != null && !this_caption.this_infobarGfx.infobar.getSection4().isEmpty()) {
				this_caption.PopulateGraphics("Alt_6,,BLANK", session_match);
				this_animation.caption = this_caption;
				processAnimations("ANIMATE-IN-GRAPHICS", session_configuration, "Alt_6,,BLANK", print_writers);
			}
			if(this_caption.this_infobarGfx.infobar.getSection5() != null && !this_caption.this_infobarGfx.infobar.getSection5().isEmpty()) {
				this_caption.PopulateGraphics("Alt_5,,BLANK", session_match);
				this_animation.caption = this_caption;
				processAnimations("ANIMATE-IN-GRAPHICS", session_configuration, "Alt_5,,BLANK", print_writers);
			}
			if(this_caption.this_infobarGfx.infobar.getSectionAnalytics() != null && !this_caption.this_infobarGfx.infobar.getSectionAnalytics().isEmpty()) {
				this_caption.PopulateGraphics("Alt_8,,BLANK", session_match);
				this_animation.caption = this_caption;
				processAnimations("ANIMATE-IN-GRAPHICS", session_configuration, "Alt_8,,BLANK", print_writers);
			}
			break;
		case Constants.AFG_SL_SERIES:
			if(this_caption.this_infobarGfx.infobar.getSection2() != null && !this_caption.this_infobarGfx.infobar.getSection2().isEmpty()) {
				this_caption.PopulateGraphics("Alt_2,,BLANK", session_match);
				this_animation.caption = this_caption;
				processAnimations("ANIMATE-IN-GRAPHICS", session_configuration, "Alt_2,,BLANK", print_writers);
			}
			break;
		}
	}
	
	public void processAnimations(String whatToProcess, Configuration session_configuration, String valueToProcess, 
		List<PrintWriter> print_writers) throws Exception
	{
		if(whatToProcess.contains("ANIMATE-IN-GRAPHICS")) {
			switch(this_animation.getTypeOfGraphicsOnScreen(session_configuration,valueToProcess)){
			case Constants.INFO_BAR:
				if(valueToProcess.split(",")[0].equalsIgnoreCase("Control_F12")) {
					switch (session_configuration.getBroadcaster()) {
					case Constants.TRI_SERIES: case Constants.AFG_SL_SERIES: case Constants.MT20: case Constants.TG20:
						if(this_animation.infobar.isInfobar_on_screen()) {
							this_animation.ChangeOn(valueToProcess, print_writers, session_configuration);
							TimeUnit.MILLISECONDS.sleep(2000);
							this_caption.whichSide = 1;
							this_caption.PopulateGraphics(valueToProcess, session_match);
							this_animation.CutBack(valueToProcess, print_writers, session_configuration);
						} else {
							this_animation.AnimateIn(valueToProcess, print_writers, session_configuration);
						}
						break;

					default:
						this_animation.AnimateIn(valueToProcess, print_writers, session_configuration);
						break;
					}
					
					if(session_configuration.getBroadcaster().equalsIgnoreCase(Constants.BCCI)) {
						if(this_animation.infobar.isInfobar_on_screen()) {
							TimeUnit.MILLISECONDS.sleep(1000);
							this_caption.this_infobarGfx.logosAndBaseColor(print_writers, "Control_F12", session_match, 1);
							this_animation.processAnimation(Constants.FRONT, print_writers, "anim_Change$Logo_Colour", "SHOW 0.0");	
						}
					}
				}else if(valueToProcess.split(",")[0].equalsIgnoreCase("Shift_F12")){
					this_animation.ChangeOn(valueToProcess, print_writers, session_configuration);
					TimeUnit.MILLISECONDS.sleep(2000);
					this_caption.whichSide = 1;
					this_caption.PopulateGraphics(valueToProcess, session_match);
					this_animation.CutBack(valueToProcess, print_writers, session_configuration);
				}
				else if(valueToProcess.split(",")[0].equalsIgnoreCase("Alt_y")){
					this_animation.AnimateIn(valueToProcess, print_writers, session_configuration);
				}else if(valueToProcess.split(",")[0].equalsIgnoreCase("Alt_e")){
					GetGraphicOption(valueToProcess,session_configuration);
				}else if(valueToProcess.split(",")[0].equalsIgnoreCase("Alt_2")) {
					this_animation.ChangeOn(valueToProcess, print_writers, session_configuration);
					switch (session_configuration.getBroadcaster()) {
					case Constants.TRI_SERIES: case Constants.MT20: case Constants.TG20:
						TimeUnit.MILLISECONDS.sleep(300);
						break;
					case Constants.BAN_AFG_SERIES:  case Constants.ACC: case Constants.AFG_SL_SERIES:
						TimeUnit.MILLISECONDS.sleep(700);
						break;
					}
					
					this_caption.whichSide = 1;
					this_caption.PopulateGraphics(valueToProcess, session_match);
					TimeUnit.MILLISECONDS.sleep(100);
					this_animation.CutBack(valueToProcess, print_writers, session_configuration);
				}else if(valueToProcess.split(",")[0].equalsIgnoreCase("Alt_5")) {
					this_animation.ChangeOn(valueToProcess, print_writers, session_configuration);
					switch (session_configuration.getBroadcaster()) {
					case Constants.TRI_SERIES:  case Constants.MT20: case Constants.TG20:
						TimeUnit.MILLISECONDS.sleep(300);
						break;
					case Constants.BAN_AFG_SERIES:  case Constants.ACC:
						TimeUnit.MILLISECONDS.sleep(700);
						break;
					}
					
					this_caption.whichSide = 1;
					this_caption.PopulateGraphics(valueToProcess, session_match);
					TimeUnit.MILLISECONDS.sleep(100);
					this_animation.CutBack(valueToProcess, print_writers, session_configuration);
				}else if(valueToProcess.split(",")[0].equalsIgnoreCase("Alt_6")) {
					this_animation.ChangeOn(valueToProcess, print_writers, session_configuration);
					TimeUnit.MILLISECONDS.sleep(300);

					this_caption.whichSide = 1;
					this_caption.PopulateGraphics(valueToProcess, session_match);
					TimeUnit.MILLISECONDS.sleep(100);
					this_animation.CutBack(valueToProcess, print_writers, session_configuration);
				}else if(valueToProcess.split(",")[0].equalsIgnoreCase("Alt_8")) {
					this_animation.ChangeOn(valueToProcess, print_writers, session_configuration);
					if(valueToProcess.contains("Sponsor")) {
						TimeUnit.MILLISECONDS.sleep(500);
					}else {
						TimeUnit.MILLISECONDS.sleep(600);
					}
					this_caption.whichSide = 1;
					this_caption.PopulateGraphics(valueToProcess, session_match);
					//TimeUnit.MILLISECONDS.sleep(100);
					this_animation.CutBack(valueToProcess, print_writers, session_configuration);
				}else {
					this_animation.ChangeOn(valueToProcess, print_writers, session_configuration);
					TimeUnit.MILLISECONDS.sleep(2000);
					this_caption.whichSide = 1;
					this_caption.PopulateGraphics(valueToProcess, session_match);
					this_animation.CutBack(valueToProcess, print_writers, session_configuration);
				}
				break;
			case "WATERMARK":
				switch (valueToProcess.split(",")[0]) {
				case "Alt_g":
					this_animation.AnimateIn(valueToProcess, print_writers, session_configuration);
					break;
				}
				break;
			default:
				if(this_animation.whichGraphicOnScreen.isEmpty()) {
					this_animation.AnimateIn(valueToProcess, print_writers, session_configuration);
					
					if(valueToProcess.split(",")[0].equalsIgnoreCase("F12") && 
							session_configuration.getBroadcaster().equalsIgnoreCase(Constants.BCCI)) {
						if(this_animation.infobar.isInfobar_on_screen()) {
							TimeUnit.MILLISECONDS.sleep(1000);
							this_caption.this_infobarGfx.logosAndBaseColor(print_writers, "F12", session_match, 1);
							this_animation.processAnimation(Constants.FRONT, print_writers, "anim_Change$Logo_Colour", "SHOW 0.0");	
						}
					}
				} else { // Change on
					switch (valueToProcess.split(",")[0]) {
					case "Control_Shift_U_change_on": case "Control_Shift_V_change_on":
						this_animation.ChangeOn(valueToProcess, print_writers, session_configuration);
						TimeUnit.MILLISECONDS.sleep(700);
						break;
					default:
						this_animation.ChangeOn(valueToProcess, print_writers, session_configuration);
						switch (session_configuration.getBroadcaster()) {
						case Constants.TRI_SERIES: case Constants.MT20: case Constants.TG20:
							TimeUnit.MILLISECONDS.sleep(400);
							break;
						case Constants.BAN_AFG_SERIES:  case Constants.ACC: case Constants.AFG_SL_SERIES:
							TimeUnit.MILLISECONDS.sleep(1500);
							break;
						}
						break;
					}
					
					this_caption.whichSide = 1;
					this_caption.PopulateGraphics(valueToProcess, session_match);
					TimeUnit.MILLISECONDS.sleep(2000);
					this_animation.CutBack(valueToProcess, print_writers, session_configuration);
				
				}
				break;
			}
		} else if(whatToProcess.contains("ANIMATE-OUT-GRAPHICS")) {
			switch (valueToProcess.split(",")[0]) {
			case "Alt_p":
				this_animation.AnimateOut(valueToProcess, print_writers, session_configuration);
				break;
			case "Alt_g":
				this_animation.AnimateOut(valueToProcess, print_writers, session_configuration);
				break;	
			case "o":
				this_animation.AnimateOut("o", print_writers, session_configuration);
				break;
			default:
				
				this_animation.AnimateOut(this_animation.whichGraphicOnScreen, print_writers, session_configuration);		
				break;
			}
		}else if(whatToProcess.contains("ANIMATE-OUT-INFOBAR")) {
			this_animation.AnimateOut("F12,", print_writers, session_configuration);
		}else if(whatToProcess.contains("ANIMATE-OUT-IDENT")) {
			this_animation.AnimateOut("Control_F12,", print_writers, session_configuration);
		}else if(whatToProcess.contains("QUIDICH-COMMANDS")) {
			this_animation.processQuidichCommands(valueToProcess, print_writers, session_configuration);
		}else if(whatToProcess.contains("ANIMATE-OUT-TAPE")) {
			this_animation.AnimateOut("Control_F8,", print_writers, session_configuration);
		}else if(whatToProcess.contains("ANIMATE-OUT-TARGET")) {
			this_animation.AnimateOut("Alt_y,", print_writers, session_configuration);
		}
	}
	@ModelAttribute("session_configuration")
	public Configuration session_configuration(){
		return new Configuration();
	}
	@ModelAttribute("expiryDate")
	public String expiryDate(){
		return new String();
	}
	
	public static List<Stats> getPlayerFromMatchData(String whatToProcess, MatchAllData match)
	{
		List<Stats> stats = new ArrayList<Stats>();
		
		switch(whatToProcess) {
		case "Shift_!":
			for(Player plyr : match.getSetup().getHomeSquad()) {
				stats.add(new Stats(plyr.getPlayerId(), match.getSetup().getHomeTeam(), plyr, new ArrayList<Statistics>()));
			}
			for(Player plyr : match.getSetup().getHomeSubstitutes()) {
				stats.add(new Stats(plyr.getPlayerId(), match.getSetup().getHomeTeam(), plyr, new ArrayList<Statistics>()));
			}
			for(Player plyr : match.getSetup().getAwaySquad()) {
				stats.add(new Stats(plyr.getPlayerId(), match.getSetup().getAwayTeam(), plyr, new ArrayList<Statistics>()));
			}
			for(Player plyr : match.getSetup().getAwaySubstitutes()) {
				stats.add(new Stats(plyr.getPlayerId(), match.getSetup().getAwayTeam(), plyr, new ArrayList<Statistics>()));
			}
			break;
		case "Shift_~":
			for(Player plyr : match.getSetup().getHomeSquad()) {
				stats.add(new Stats(plyr.getPlayerId(), match.getSetup().getHomeTeam(), plyr, null, null));
			}
			for(Player plyr : match.getSetup().getHomeSubstitutes()) {
				stats.add(new Stats(plyr.getPlayerId(), match.getSetup().getHomeTeam(), plyr, null, null));
			}
			for(Player plyr : match.getSetup().getAwaySquad()) {
				stats.add(new Stats(plyr.getPlayerId(), match.getSetup().getAwayTeam(), plyr, null, null));
			}
			for(Player plyr : match.getSetup().getAwaySubstitutes()) {
				stats.add(new Stats(plyr.getPlayerId(), match.getSetup().getAwayTeam(), plyr, null, null));
			}
			break;
		}
		return stats;
	}
	
	@SuppressWarnings("unchecked")
	public <T> List<T> GetGraphicOption(String whatToProcess,Configuration session_configuration) throws Exception {
		switch ((whatToProcess.contains(",")?whatToProcess.split(",")[0]:whatToProcess)) {
		case "Alt_e":
			this_caption.whichSide = 1;
			this_caption.PopulateGraphics("Alt_e,", session_match);
			break;
		case "k": case "Shift_Y":
			return (List<T>) session_bugs;
		case "F10": 
		    return (List<T>) session_name_super;
		case "Control_Shift_*":
			return (List<T>) session_bugs_everest;
		case "Control_Shift_J":
			return (List<T>) session_performance_bug;    
		case "Control_m": case "Control_Shift_L": case "Shift_F11":
			return (List<T>) CricketFunctions.processAllFixtures(cricketService);
		case "Alt_8": case "Alt_0":
			 if(whatToProcess.contains(",")) {
	  			switch (whatToProcess.split(",")[1]) {
	  			case "Commentators":
	  				return (List<T>) session_commentator;
	  			case "FreeTextDb":
	  				return (List<T>) cricketService.getInfobarStats();
	  			case "Sponsor":
	  				return (List<T>) cricketService.getSponsor();
	  			}
			 }
		  break;
//	  	case "Alt_3":
//	  		ObjectMapper mapper = new ObjectMapper();
//	  		Gfx gfxStat = mapper.readValue(new File("C:\\Sports\\Cricket\\GFXstat_1445832_209.json"), Gfx.class);
//	  		if(whatToProcess.contains(",")) {
//	  			switch (whatToProcess.split(",")[1]) {
//	  			case "SEIRES":
//	  				return (List<T>)gfxStat.getBatting().get(0).getSeries().get(0).getStats();
//	  			case "FORMAT":
//	  				return (List<T>)gfxStat.getBatting().get(0).getFormat().get(0).getStats();	
//	  			case "INNINGS":
//	  				return (List<T>)gfxStat.getBatting().get(0).getInnings().get(0).getStats();
//	  			case "VENUE":
//	  				return (List<T>)gfxStat.getBatting().get(0).getVenue().get(0).getStats();
//	  			case "CAPTAIN":
//	  				return (List<T>)gfxStat.getBatting().get(0).getCaptain().get(0).getStats();
//	  			case "WICKETKEEPER":
//	  				return (List<T>)gfxStat.getBatting().get(0).getWicketKeeper().get(0).getStats();
//	  			case "OPPONENT":
//	  				return (List<T>)gfxStat.getBatting().get(0).getOpponent().get(0).getStats();
//	  			case "TROPHY":
//	  				return (List<T>)gfxStat.getBatting().get(0).getTrophy().get(0).getStats();
//	  			case "POSITION":
//	  				return (List<T>)gfxStat.getBatting().get(0).getPosition().get(0).getStats();
//	  			}	
//	  		}else {
//	  	  		 return (List<T>)gfxStat.getBatting();
//	  		}
  		
	  	case "Shift_!":
				List<Stats> database_statistics = new ArrayList<Stats>();
				database_statistics = getPlayerFromMatchData(whatToProcess ,session_match);
				
				for(Statistics stats : session_statistics) {
					for(int i=0;i<=database_statistics.size()-1;i++) {
						if(database_statistics.get(i).getPlayerId() == stats.getPlayerID()) {
							stats.setStats_type(cricketService.getStatsType(stats.getStatsTypeId()));
							database_statistics.get(i).getStatsList().add(stats);
						}
					}
				}
			
			return (List<T>) database_statistics;
		case "Shift_~":
			List<Stats> statistics = new ArrayList<Stats>();
			statistics = getPlayerFromMatchData(whatToProcess ,session_match);
			
			for(Statistics stats : session_statistics) {
				for(int i=0;i<=statistics.size()-1;i++) {
					if(statistics.get(i).getPlayerId() == stats.getPlayerID()) {
						stats.setStats_type(cricketService.getStatsType(stats.getStatsTypeId()));
						stats = CricketFunctions.updateTournamentWithH2h(stats, headToHead.getH2hPlayer(), session_match, CricketUtil.FULL);
						stats = CricketFunctions.updateStatisticsWithMatchData(stats, session_match, CricketUtil.FULL);
						statistics.get(i).setStats(stats);
					}
				}
			}
			for(Tournament tour : CricketFunctions.extractTournamentData("CURRENT_MATCH_DATA", false, headToHead.getH2hPlayer(),cricketService, session_match, past_tournament_stats)) {
				for(int i=0;i<=statistics.size()-1;i++) {
					if(tour.getPlayerId() == statistics.get(i).getPlayerId()) {
						statistics.get(i).setTournament(tour);
					}
				}
			}
			
			return (List<T>) statistics;
		case "z": case "x": case "c": case "v": case "Control_Shift_Z": case "Control_Shift_Y":
			List<Tournament> tournament_stats = new ArrayList<Tournament>();
			
			tournament_stats = CricketFunctions.extractTournamentData("CURRENT_MATCH_DATA", false, headToHead.getH2hPlayer(), cricketService, 
					session_match, past_tournament_stats);
			
			switch (whatToProcess) {
			case "z":
				Collections.sort(tournament_stats,new CricketFunctions.BatsmenMostRunComparator());
				break;
			case "x":
				Collections.sort(tournament_stats,new CricketFunctions.BowlerWicketsComparator());
				break;
			case "c":
				Collections.sort(tournament_stats,new CricketFunctions.BatsmanFoursComparator());
				break;
			case "v":
				Collections.sort(tournament_stats,new CricketFunctions.BatsmanSixesComparator());
				break;
			case "Control_Shift_Z":
				Collections.sort(tournament_stats,new CricketFunctions.BestBatsmanStrikeRateComparator());
				break;
			case "Control_Shift_Y":
				Collections.sort(tournament_stats,new CricketFunctions.BestBowlerEconomyComparator());
				break;	
			}
			return (List<T>) tournament_stats;
		case "Control_z": case "Control_x":
			
			List<Tournament> tournaments = new ArrayList<Tournament>();
			
			tournaments = CricketFunctions.extractTournamentData("CURRENT_MATCH_DATA", false, headToHead.getH2hPlayer(), cricketService, 
	                session_match, past_tournament_stats);
			 
			List<BestStats> top_ten_beststat = new ArrayList<BestStats>();
	        for(Tournament tourn : tournaments) {
	        	
				switch (whatToProcess) {
				case "Control_z":
					//top_ten_beststat.clear();
		            for(BestStats bs : tourn.getBatsman_best_Stats()) {
//		            	System.out.println("bs = " + bs.getPlayer().getFull_name() + "  runs = " + bs.getBestEquation());
		            	top_ten_beststat.add(CricketFunctions.getProcessedBatsmanBestStats(bs));
		            	//break;
		            }
					Collections.sort(top_ten_beststat,new CricketFunctions.BatsmanBestStatsComparator());
					break;
				case "Control_x":
		            for(BestStats bs : tourn.getBowler_best_Stats()) {
		            	top_ten_beststat.add(CricketFunctions.getProcessedBowlerBestStats(bs));
		            }
					Collections.sort(top_ten_beststat,new CricketFunctions.BowlerBestStatsComparator());
					break;
				}
	        }
	        
			return (List<T>) top_ten_beststat;	
		}
		return null;
	}
	
	public void GetVariousDBData(String typeOfUpdate, Configuration config) throws StreamReadException, DatabindException, 
		IllegalAccessException, InvocationTargetException, JAXBException, IOException, CloneNotSupportedException, 
		InterruptedException, URISyntaxException, jakarta.xml.bind.JAXBException
	{
		switch (config.getBroadcaster()) {
		case Constants.BCCI: case Constants.TRI_SERIES: case Constants.BAN_AFG_SERIES: case Constants.ACC: case Constants.AFG_SL_SERIES:
		case Constants.MT20: case Constants.TG20:
			switch (typeOfUpdate) {
			case "ONLY_DB":
				session_performance_bug = cricketService.getPerformanceBugs();
				session_name_super =  cricketService.getNameSupers();
				session_team =  cricketService.getTeams();
				session_ground =  cricketService.getGrounds();
				session_bugs = cricketService.getBugs();
				session_bugs_everest = cricketService.getEverestBugs();
				session_infoBarStats = cricketService.getInfobarStats();
				session_variousText = cricketService.getVariousTexts();
				session_commentator = cricketService.getCommentator();
				session_staff = cricketService.getStaff();
				session_fixture =  CricketFunctions.processAllFixtures(cricketService);
				session_players = cricketService.getAllPlayer();
				session_pott = cricketService.getPott();
				session_playoff = cricketService.getPlayOff();
				session_statistics = cricketService.getAllStats();
				
				//Bug and Mini
				this_caption.this_bugsAndMiniGfx.bugs = session_bugs;
				this_caption.this_bugsAndMiniGfx.everestBugs = session_bugs_everest;
				this_caption.this_bugsAndMiniGfx.Teams = session_team;
				this_caption.this_bugsAndMiniGfx.VariousText = session_variousText;
				this_caption.this_bugsAndMiniGfx.performanceBugs = session_performance_bug;
				this_caption.this_bugsAndMiniGfx.statistics = session_statistics;
				this_caption.this_bugsAndMiniGfx.statsTypes = cricketService.getAllStatsType();
				this_caption.this_bugsAndMiniGfx.Players = session_players;
				
				this_caption.this_infobarGfx.statistics = session_statistics;
				this_caption.this_infobarGfx.statsTypes = cricketService.getAllStatsType();
				this_caption.this_infobarGfx.infobarStats  = session_infoBarStats;
				this_caption.this_infobarGfx.Grounds = session_ground;
				this_caption.this_infobarGfx.tournament_matches = cricket_matches;
				this_caption.this_infobarGfx.dls  = session_dls;
				this_caption.this_infobarGfx.Commentators = session_commentator;
				this_caption.this_infobarGfx.Players = session_players;
				this_caption.this_infobarGfx.teams = session_team;
				
				//LowerThird
				this_caption.this_lowerThirdGfx.statistics = session_statistics;
				this_caption.this_lowerThirdGfx.statsTypes = cricketService.getAllStatsType();
				this_caption.this_lowerThirdGfx.tournament_matches = cricket_matches;
				this_caption.this_lowerThirdGfx.nameSupers = session_name_super;
				this_caption.this_lowerThirdGfx.Teams = session_team;
				this_caption.this_lowerThirdGfx.Grounds = session_ground;
				this_caption.this_lowerThirdGfx.tournaments = past_tournament_stats;
				this_caption.this_lowerThirdGfx.tapeballs = past_tape;
				this_caption.this_lowerThirdGfx.dls = session_dls;
				this_caption.this_lowerThirdGfx.Staff = session_staff;
				this_caption.this_lowerThirdGfx.VariousText = session_variousText;
				this_caption.this_lowerThirdGfx.Potts = session_pott;
				this_caption.this_lowerThirdGfx.fixTures = session_fixture;
				
				
				//FullFrames
				this_caption.this_fullFramesGfx.statistics = session_statistics;
				this_caption.this_fullFramesGfx.statsTypes = cricketService.getAllStatsType();
				this_caption.this_fullFramesGfx.tournament_matches = cricket_matches;
				this_caption.this_fullFramesGfx.fixTures = session_fixture;
				this_caption.this_fullFramesGfx.Teams = session_team;
				this_caption.this_fullFramesGfx.Grounds = session_ground;
				this_caption.this_fullFramesGfx.tournaments = past_tournament_stats;
				this_caption.this_fullFramesGfx.this_ALL_FF.tournaments = past_tournament_stats;
				this_caption.this_fullFramesGfx.this_ALL_FF.past_tournament_stats = past_tournament_stats;
				this_caption.this_fullFramesGfx.VariousText = session_variousText;
				this_caption.this_fullFramesGfx.Potts = session_pott;
				this_caption.this_fullFramesGfx.Playoffs = session_playoff;
				
				this_caption.this_fullFramesGfx.multilanguagedata.players = session_players;
				this_caption.this_fullFramesGfx.multilanguagedata.team = session_team;
				this_caption.this_fullFramesGfx.multilanguagedata.dictionary = cricketService.getDictionary();
				this_caption.this_fullFramesGfx.multilanguagedata.venue = cricketService.getVenues();
				
				this_caption.this_lowerThirdGfx.multilanguagedata.players = session_players;
				this_caption.this_lowerThirdGfx.multilanguagedata.team = session_team;
				this_caption.this_lowerThirdGfx.multilanguagedata.dictionary = cricketService.getDictionary();
				this_caption.this_lowerThirdGfx.multilanguagedata.venue = cricketService.getVenues();
				
				this_caption.this_infobarGfx.multilanguagedata.players = session_players;
				this_caption.this_infobarGfx.multilanguagedata.team = session_team;
				this_caption.this_infobarGfx.multilanguagedata.dictionary = cricketService.getDictionary();
				this_caption.this_infobarGfx.multilanguagedata.venue = cricketService.getVenues();
				
				this_caption.this_bugsAndMiniGfx.multilanguagedata.players = session_players;
				this_caption.this_bugsAndMiniGfx.multilanguagedata.team = session_team;
				this_caption.this_bugsAndMiniGfx.multilanguagedata.dictionary = cricketService.getDictionary();
				this_caption.this_bugsAndMiniGfx.multilanguagedata.venue = cricketService.getVenues();
				break;
			default:
				session_statistics = cricketService.getAllStats();
				//past_tournament_stats = CricketFunctions.extractTournamentData("PAST_MATCHES_DATA", false, headToHead.getH2hPlayer(), cricketService, session_match, null);
				
				session_performance_bug = cricketService.getPerformanceBugs();
				session_bugs_everest = cricketService.getEverestBugs();
				session_name_super =  cricketService.getNameSupers();
				session_team =  cricketService.getTeams();
				session_ground =  cricketService.getGrounds();
				session_bugs = cricketService.getBugs();
				session_infoBarStats = cricketService.getInfobarStats();
				session_variousText = cricketService.getVariousTexts();
				session_commentator = cricketService.getCommentator();
				session_staff = cricketService.getStaff();
				session_fixture =  CricketFunctions.processAllFixtures(cricketService);
				session_players = cricketService.getAllPlayer();
				session_pott = cricketService.getPott();
				session_playoff = cricketService.getPlayOff();
				
				if(new File(CricketUtil.CRICKET_DIRECTORY + "ParScores BB.html").exists()) {
					session_dls = CricketFunctions.populateDuckWorthLewis(session_match, CricketUtil.CRICKET_DIRECTORY);
				}
				
				switch (typeOfUpdate) {
				case "NEW":
					this_caption = new Caption(print_writers, config, session_statistics,cricketService.getAllStatsType(), cricket_matches, session_name_super,
						session_bugs,session_infoBarStats,session_fixture, session_team, session_ground,session_variousText, session_commentator, session_staff, 
						session_players, session_pott,session_playoff, session_teamChanges, session_performance_bug, new FullFramesGfx(),new LowerThirdGfx(), 
						new InfobarGfx(), new BugsAndMiniGfx(), 1, "", "-", past_tournament_stats,past_tape,session_dls, headToHead.getH2hPlayer(), 
						past_tournament_stats, cricketService,session_bugs_everest);
					
//					this_caption.this_infobarGfx.previous_sixes = String.valueOf(CricketFunctions.extracttournamentFoursAndSixesData("PAST_MATCHES_DATA", 
//							headToHead.getH2hPlayer(), session_match, null).getTournament_sixes());
//					
//					this_caption.this_infobarGfx.previous_fours = String.valueOf(CricketFunctions.extracttournamentFoursAndSixesData("PAST_MATCHES_DATA", 
//							headToHead.getH2hPlayer(), session_match, null).getTournament_fours());
//					
//					this_caption.this_bugsAndMiniGfx.previous_sixes =  String.valueOf(CricketFunctions.extracttournamentFoursAndSixesData("PAST_MATCHES_DATA", 
//							headToHead.getH2hPlayer(), session_match, null).getTournament_sixes());
//					
//					this_caption.this_bugsAndMiniGfx.previous_fours =  String.valueOf(CricketFunctions.extracttournamentFoursAndSixesData("PAST_MATCHES_DATA", 
//							headToHead.getH2hPlayer(), session_match, null).getTournament_fours());
					
					this_caption.this_fullFramesGfx.multilanguagedata = new MultiLanguageDatabase();
					this_caption.this_fullFramesGfx.multilanguagedata.players = session_players;
					this_caption.this_fullFramesGfx.multilanguagedata.team = session_team;
					this_caption.this_fullFramesGfx.multilanguagedata.dictionary = cricketService.getDictionary();
					this_caption.this_fullFramesGfx.multilanguagedata.venue = cricketService.getVenues();
					
					this_caption.this_lowerThirdGfx.multilanguagedata = new MultiLanguageDatabase();
					this_caption.this_lowerThirdGfx.multilanguagedata.players = session_players;
					this_caption.this_lowerThirdGfx.multilanguagedata.team = session_team;
					this_caption.this_lowerThirdGfx.multilanguagedata.dictionary = cricketService.getDictionary();
					this_caption.this_lowerThirdGfx.multilanguagedata.venue = cricketService.getVenues();
					
					this_caption.this_infobarGfx.multilanguagedata = new MultiLanguageDatabase();
					this_caption.this_infobarGfx.multilanguagedata.players = session_players;
					this_caption.this_infobarGfx.multilanguagedata.team = session_team;
					this_caption.this_infobarGfx.multilanguagedata.dictionary = cricketService.getDictionary();
					this_caption.this_infobarGfx.multilanguagedata.venue = cricketService.getVenues();
					
					this_caption.this_bugsAndMiniGfx.multilanguagedata = new MultiLanguageDatabase();
					this_caption.this_bugsAndMiniGfx.multilanguagedata.players = session_players;
					this_caption.this_bugsAndMiniGfx.multilanguagedata.team = session_team;
					this_caption.this_bugsAndMiniGfx.multilanguagedata.dictionary = cricketService.getDictionary();
					this_caption.this_bugsAndMiniGfx.multilanguagedata.venue = cricketService.getVenues();
					break;
					
				case "UPDATE":				
					session_match = CricketFunctions.populateMatchVariables(CricketFunctions.readOrSaveMatchFile(CricketUtil.READ, 
						CricketUtil.SETUP + "," + CricketUtil.MATCH + "," + CricketUtil.EVENT, session_match,config, CricketUtil.CRICKET_DIRECTORY), 
						session_players, session_team, session_ground);
					session_match.getSetup().setGenerateInteractiveFile(config.getGenerateInteractiveFile());
					MatchStats = CricketFunctions.getAllEvents(session_match, config.getBroadcaster(), session_match.getEventFile().getEvents());
					CricketFunctions.getInteractive(session_match, "FULL_WRITE", CricketUtil.CRICKET_DIRECTORY);
					session_match.getMatch().setMatchStats(MatchStats);
					
					this_caption.this_fullFramesGfx.multilanguagedata.players = session_players;
					this_caption.this_fullFramesGfx.multilanguagedata.team = session_team;
					this_caption.this_fullFramesGfx.multilanguagedata.dictionary = cricketService.getDictionary();
					this_caption.this_fullFramesGfx.multilanguagedata.venue = cricketService.getVenues();
					
					this_caption.this_lowerThirdGfx.multilanguagedata.players = session_players;
					this_caption.this_lowerThirdGfx.multilanguagedata.team = session_team;
					this_caption.this_lowerThirdGfx.multilanguagedata.dictionary = cricketService.getDictionary();
					this_caption.this_lowerThirdGfx.multilanguagedata.venue = cricketService.getVenues();
					
					this_caption.this_infobarGfx.multilanguagedata.players = session_players;
					this_caption.this_infobarGfx.multilanguagedata.team = session_team;
					this_caption.this_infobarGfx.multilanguagedata.dictionary = cricketService.getDictionary();
					this_caption.this_infobarGfx.multilanguagedata.venue = cricketService.getVenues();
					
					this_caption.this_bugsAndMiniGfx.multilanguagedata.players = session_players;
					this_caption.this_bugsAndMiniGfx.multilanguagedata.team = session_team;
					this_caption.this_bugsAndMiniGfx.multilanguagedata.dictionary = cricketService.getDictionary();
					this_caption.this_bugsAndMiniGfx.multilanguagedata.venue = cricketService.getVenues();
					
					this_caption.this_infobarGfx.previous_sixes = String.valueOf(CricketFunctions.extracttournamentFoursAndSixesData("PAST_MATCHES_DATA", 
							headToHead.getH2hPlayer(), session_match, null).getTournament_sixes());
					this_caption.this_infobarGfx.previous_fours = String.valueOf(CricketFunctions.extracttournamentFoursAndSixesData("PAST_MATCHES_DATA", 
							headToHead.getH2hPlayer(), session_match, null).getTournament_fours());
						
					this_caption.this_bugsAndMiniGfx.previous_sixes =  String.valueOf(CricketFunctions.extracttournamentFoursAndSixesData("PAST_MATCHES_DATA", 
							headToHead.getH2hPlayer(), session_match, null).getTournament_sixes());
					this_caption.this_bugsAndMiniGfx.previous_fours =  String.valueOf(CricketFunctions.extracttournamentFoursAndSixesData("PAST_MATCHES_DATA", 
							headToHead.getH2hPlayer(), session_match, null).getTournament_fours());
					
					//Bug and Mini
					this_caption.this_bugsAndMiniGfx.bugs = session_bugs;
					this_caption.this_bugsAndMiniGfx.Teams = session_team;
					this_caption.this_bugsAndMiniGfx.VariousText = session_variousText;
					this_caption.this_bugsAndMiniGfx.performanceBugs = session_performance_bug;
					this_caption.this_bugsAndMiniGfx.statistics = session_statistics;
					this_caption.this_bugsAndMiniGfx.everestBugs = session_bugs_everest;
					this_caption.this_bugsAndMiniGfx.statsTypes = cricketService.getAllStatsType();
					this_caption.this_bugsAndMiniGfx.Players = session_players;
					
					//InfoBar
					this_caption.this_infobarGfx.statistics = session_statistics;
					this_caption.this_infobarGfx.statsTypes = cricketService.getAllStatsType();
					this_caption.this_infobarGfx.infobarStats  = session_infoBarStats;
					this_caption.this_infobarGfx.Grounds = session_ground;
					this_caption.this_infobarGfx.tournament_matches = cricket_matches;
					this_caption.this_infobarGfx.dls  = session_dls;
					this_caption.this_infobarGfx.Commentators = session_commentator;
					this_caption.this_infobarGfx.Players = session_players;
					this_caption.this_infobarGfx.teams = session_team;
					
					//LowerThird
					this_caption.this_lowerThirdGfx.statistics = session_statistics;
					this_caption.this_lowerThirdGfx.statsTypes = cricketService.getAllStatsType();
					this_caption.this_lowerThirdGfx.tournament_matches = cricket_matches;
					this_caption.this_lowerThirdGfx.nameSupers = session_name_super;
					this_caption.this_lowerThirdGfx.Teams = session_team;
					this_caption.this_lowerThirdGfx.Grounds = session_ground;
					this_caption.this_lowerThirdGfx.tournaments = past_tournament_stats;
					this_caption.this_lowerThirdGfx.tapeballs = past_tape;
					this_caption.this_lowerThirdGfx.dls = session_dls;
					this_caption.this_lowerThirdGfx.Staff = session_staff;
					this_caption.this_lowerThirdGfx.VariousText = session_variousText;
					this_caption.this_lowerThirdGfx.Potts = session_pott;
					this_caption.this_lowerThirdGfx.fixTures = session_fixture;
					
					
					//FullFrames
					this_caption.this_fullFramesGfx.statistics = session_statistics;
					this_caption.this_fullFramesGfx.statsTypes = cricketService.getAllStatsType();
					this_caption.this_fullFramesGfx.tournament_matches = cricket_matches;
					this_caption.this_fullFramesGfx.fixTures = session_fixture;
					this_caption.this_fullFramesGfx.Teams = session_team;
					this_caption.this_fullFramesGfx.this_ALL_FF.Teams = session_team;
					this_caption.this_fullFramesGfx.Grounds = session_ground;
					this_caption.this_fullFramesGfx.tournaments = past_tournament_stats;
					this_caption.this_fullFramesGfx.this_ALL_FF.tournaments = past_tournament_stats;
					this_caption.this_fullFramesGfx.this_ALL_FF.past_tournament_stats = past_tournament_stats;
					this_caption.this_fullFramesGfx.VariousText = session_variousText;
					this_caption.this_fullFramesGfx.Potts = session_pott;
					this_caption.this_fullFramesGfx.Playoffs = session_playoff;
					if(new File(CricketUtil.CRICKET_DIRECTORY + "TeamChanges.txt").exists()) {
						String text_to_return = "";
						this_caption.this_fullFramesGfx.TeamChanges.clear();
						try (BufferedReader br = new BufferedReader(new FileReader(CricketUtil.CRICKET_DIRECTORY + "TeamChanges.txt"))) {
							while((text_to_return = br.readLine()) != null) {
								if(text_to_return.contains("|")) {
									
								}else {
									if(text_to_return.contains("H") || text_to_return.contains("A")) {
										this_caption.this_fullFramesGfx.TeamChanges.add(text_to_return);
									}
								}
							}
						}
					}
					
					break;
				}
				break;
			}
			break;
		}
	}
}