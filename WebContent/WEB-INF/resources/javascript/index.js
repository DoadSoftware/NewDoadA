var session_match, session_caption, session_animation,isSplitScorecard = false;
var selected_options = [];
let TeamScore = "";
function processWaitingButtonSpinner(whatToProcess) 
{
	switch (whatToProcess) {
	case 'START_WAIT_TIMER': 
		$('.spinner-border').show();
		$(':button').prop('disabled', true);
		break;
	case 'END_WAIT_TIMER': 
		$('.spinner-border').hide();
		$(':button').prop('disabled', false);
		break;
	}
}
function onPageLoadEvent(whichPage){
	switch(whichPage){
	case 'OUTPUT':
		$("#select_graphic_options_div").empty();
		document.getElementById('selected_inning').innerHTML = 'Selected Innings: ' + document.getElementById('which_inning').value;
		initialiseSelectedOptionsList();
		
		document.getElementById('inning1_teamScore_lbl').setAttribute('style', 'font-family: "Bungee-Regular", sans-serif !important;');
		document.getElementById('inning2_teamScore_lbl').setAttribute('style', 'font-family: "Bungee-Regular", sans-serif !important;');
		
		if(document.getElementById('which_inning').value == 1){
			
			document.getElementById('inning1_teamName').style.backgroundColor ='#990000';
			document.getElementById('inning1_teamName').style.color ='white';
			
			document.getElementById('inning2_teamScore_lbl').style.backgroundColor ='';
			document.getElementById('inning2_teamScore_lbl').style.color ='';
			
			document.getElementById('inning1_teamScore_lbl').style.backgroundColor ='#990000';
			document.getElementById('inning1_teamScore_lbl').style.color ='white';
			
			document.getElementById('inning2_teamName').style.backgroundColor ='';
			document.getElementById('inning2_teamName').style.color ='';
				
		}else if(document.getElementById('which_inning').value == 2){
			document.getElementById('inning2_teamName').style.backgroundColor ='#990000';
			document.getElementById('inning2_teamName').style.color ='white';
			
			document.getElementById('inning1_teamScore_lbl').style.backgroundColor ='';
			document.getElementById('inning1_teamScore_lbl').style.color ='';
			
			document.getElementById('inning2_teamScore_lbl').style.backgroundColor ='#990000';
			document.getElementById('inning2_teamScore_lbl').style.color ='white';
			
			document.getElementById('inning1_teamName').style.color = '';
			document.getElementById('inning1_teamName').style.backgroundColor = '';

		}
		//addItemsToList('HELP-FILE',session_match);
		break;
	}
}
function initialiseSelectedOptionsList()
{
	selected_options = [];
	for(var i = 1; i <= 5; i++) {
	    selected_options.push('');
	}
}
function initialiseForm(whatToProcess,dataToProcess)
{
	switch (whatToProcess) {
	case 'UPDATE-MATCHES':
		const dropdown = document.getElementById('select_cricket_matches');
		dropdown.innerHTML = '';
		
		const defaultOption = document.createElement('option');
            defaultOption.value = '';
            defaultOption.textContent = '-- Select Match --';
            dropdown.appendChild(defaultOption);

            dataToProcess.forEach(matchName => {
                const option = document.createElement('option');
                option.value = matchName;
                option.textContent = matchName;
                dropdown.appendChild(option);
            });
		break;
		
	case 'UPDATE-CONFIG':
		
		document.getElementById('configuration_file_name').value = $('#select_configuration_file option:selected').val();
		document.getElementById('select_cricket_matches').value = dataToProcess.filename;
		document.getElementById('select_broadcaster').value = dataToProcess.broadcaster;
		document.getElementById('select_second_broadcaster').value = dataToProcess.secondaryBroadcaster;
		document.getElementById('generateInteractiveFile').value = dataToProcess.generateInteractiveFile;
		document.getElementById('qtIPAddress').value = dataToProcess.qtIpAddress;
		document.getElementById('qtPortNumber').value = dataToProcess.qtPortNumber;
		document.getElementById('vizIPAddress').value = dataToProcess.primaryIpAddress;
		document.getElementById('vizPortNumber').value = dataToProcess.primaryPortNumber;
		document.getElementById('vizLanguage').value = dataToProcess.primaryLanguage;
		document.getElementById('vizSecondaryLanguage').value = dataToProcess.secondaryLanguage;
		document.getElementById('primaryVariousOptions').value = dataToProcess.primaryVariousOptions;
		document.getElementById('vizSecondaryIPAddress').value = dataToProcess.secondaryIpAddress;
		document.getElementById('vizSecondaryPortNumber').value = dataToProcess.secondaryPortNumber;
		document.getElementById('vizTertiaryIPAddress').value = dataToProcess.tertiaryIpAddress;
		document.getElementById('vizTertiaryPortNumber').value = dataToProcess.tertiaryPortNumber;
		document.getElementById('selectInfobar').value = dataToProcess.whichInfobar;
		document.getElementById('Category').value = dataToProcess.category;
		processUserSelection($('#select_second_broadcaster'));
		break;
		
	case 'UPDATE-MATCH-ON-OUTPUT-FORM':
		if($('#selected_broadcaster').val().toUpperCase()==='ISPL'){
			TeamScore = ChallengeScore(dataToProcess);
		}
		
		let thisOverArr = dataToProcess.match.matchStats.overData.thisOverTxt.split(",");
		
		dataToProcess.match.inning.forEach(function(inn){
			if(inn.inningNumber<=2){
				//ChallengeScore
				if ($('#selected_broadcaster').val().toUpperCase() === 'ISPL') {
				    document.getElementById('inning1_teamName').innerHTML = dataToProcess.match.inning[0].batting_team.teamName1;
				    document.getElementById('inning1_teamScore_lbl').innerText = 
				        TeamScore.split(',')[0] + 
				        (parseInt(dataToProcess.match.inning[0].totalWickets) < 10 
				            ? "-" + dataToProcess.match.inning[0].totalWickets 
				            : "") + 
				        ' [' + parseInt(dataToProcess.match.inning[0].totalOvers) + '.' + parseInt(dataToProcess.match.inning[0].totalBalls) + ']';
				
				    document.getElementById('inning2_teamName').innerHTML = dataToProcess.match.inning[1].batting_team.teamName1;
				    document.getElementById('inning2_teamScore_lbl').innerText = 
				        TeamScore.split(',')[1] + 
				        (parseInt(dataToProcess.match.inning[1].totalWickets) < 10 
				            ? "-" + dataToProcess.match.inning[1].totalWickets 
				            : "") + 
				        ' [' + parseInt(dataToProcess.match.inning[1].totalOvers) + '.' + parseInt(dataToProcess.match.inning[1].totalBalls) + ']';
				} else {
				    document.getElementById('inning1_teamName').innerHTML = dataToProcess.match.inning[0].batting_team.teamName1;
				    document.getElementById('inning1_teamScore_lbl').innerText = 
				        parseInt(dataToProcess.match.inning[0].totalRuns) + 
				        (parseInt(dataToProcess.match.inning[0].totalWickets) < 10 
				            ? "-" + dataToProcess.match.inning[0].totalWickets 
				            : "") + 
				        ' [' + parseInt(dataToProcess.match.inning[0].totalOvers) + '.' + parseInt(dataToProcess.match.inning[0].totalBalls) + ']';
				
				    document.getElementById('inning2_teamName').innerHTML = dataToProcess.match.inning[1].batting_team.teamName1;
				    document.getElementById('inning2_teamScore_lbl').innerText = 
				        parseInt(dataToProcess.match.inning[1].totalRuns) + 
				        (parseInt(dataToProcess.match.inning[1].totalWickets) < 10 
				            ? "-" + dataToProcess.match.inning[1].totalWickets 
				            : "") + 
				        ' [' + parseInt(dataToProcess.match.inning[1].totalOvers) + '.' + parseInt(dataToProcess.match.inning[1].totalBalls) + ']';
				}
			 }else if (inn.inningNumber > 2 && inn.inningNumber <= 4 && inn.isCurrentInning=='YES') {
				    document.getElementById('inning1_teamName').innerHTML = dataToProcess.match.inning[2].batting_team.teamName1;
				    var team1 ; var team2 ;
				   if(dataToProcess.match.inning[2].batting_team.teamName1==dataToProcess.match.inning[0].batting_team.teamName1){
					  team1 = parseInt(dataToProcess.match.inning[0].totalRuns) + 
				    	 (parseInt(dataToProcess.match.inning[0].totalWickets) < 10 
				            ? "-" + dataToProcess.match.inning[0].totalWickets 
				            : "") + " & ";
				      team2 = parseInt(dataToProcess.match.inning[1].totalRuns) + 
				    	 (parseInt(dataToProcess.match.inning[1].totalWickets) < 10 
				            ? "-" + dataToProcess.match.inning[1].totalWickets 
				            : "") + " & ";
				   }else{
					  team2 = parseInt(dataToProcess.match.inning[0].totalRuns) + 
				    	 (parseInt(dataToProcess.match.inning[0].totalWickets) < 10 
				            ? "-" + dataToProcess.match.inning[0].totalWickets 
				            : "") + " & ";
				      team1 = parseInt(dataToProcess.match.inning[1].totalRuns) + 
				    	 (parseInt(dataToProcess.match.inning[1].totalWickets) < 10 
				            ? "-" + dataToProcess.match.inning[1].totalWickets 
				            : "") + " & ";
				   }
				    document.getElementById('inning1_teamScore_lbl').innerText = 
				        team1 + parseInt(dataToProcess.match.inning[2].totalRuns) + 
				        (parseInt(dataToProcess.match.inning[2].totalWickets) < 10 
				            ? "-" + dataToProcess.match.inning[2].totalWickets 
				            : "") + 
				        ' [' + parseInt(dataToProcess.match.inning[2].totalOvers) + '.' + parseInt(dataToProcess.match.inning[2].totalBalls) + ']';
				
				    document.getElementById('inning2_teamName').innerHTML = dataToProcess.match.inning[3].batting_team.teamName1;
				    document.getElementById('inning2_teamScore_lbl').innerText = 
				         team2 +  parseInt(dataToProcess.match.inning[3].totalRuns) + 
				        (parseInt(dataToProcess.match.inning[3].totalWickets) < 10 
				            ? "-" + dataToProcess.match.inning[3].totalWickets 
				            : "") + 
				        ' [' + parseInt(dataToProcess.match.inning[3].totalOvers) + '.' + parseInt(dataToProcess.match.inning[3].totalBalls) + ']';
				}

			if(inn.isCurrentInning == 'YES'){
				inn.battingCard.forEach(function(bc){
					if(inn.partnerships != null && inn.partnerships.length > 0) {
						//inn.partnerships.forEach(function(par){
							const lastPar = inn.partnerships[inn.partnerships.length - 1];
							if(bc.playerId == lastPar.firstBatterNo) {
								if(bc.status == 'OUT'){
									document.getElementById('batter1_text').innerHTML = bc.player.full_name + ' ' + bc.runs + '(' + bc.balls + ')' ;
									document.getElementById('batter1_text').style.color = 'red';
								}else{
									if(bc.onStrike == 'YES'){
										document.getElementById('batter1_text').innerHTML = bc.player.full_name +
										'&nbsp;<i class="fas fa-caret-right arrow"></i> ' +' ' + bc.runs + '(' + bc.balls + ')';
									}else{
										document.getElementById('batter1_text').innerHTML = bc.player.full_name + ' ' + bc.runs + '(' + bc.balls + ')' ;
									}
									document.getElementById('batter1_text').style.color = 'green';
								}
							}
							//alert(lastPar.secondBatterNo)
							if(bc.playerId == lastPar.secondBatterNo) {
								if(bc.status == 'OUT'){
									document.getElementById('batter2_text').innerHTML = bc.player.full_name + ' ' + bc.runs + '(' + bc.balls + ')' ;
									document.getElementById('batter2_text').style.color = 'red';
								}else{
									if(bc.onStrike == 'YES'){
										document.getElementById('batter2_text').innerHTML = bc.player.full_name + ' '+
										'&nbsp;<i class="fas fa-caret-right arrow"></i> '  + bc.runs + '(' + bc.balls + ')' ;
									}else{
										document.getElementById('batter2_text').innerHTML = bc.player.full_name + ' ' + bc.runs + '(' + bc.balls + ')' ;
									}
									document.getElementById('batter2_text').style.color = 'green';
								}
							}
						//});
					}
				});
				
				if(inn.bowlingCard != null){
					inn.bowlingCard.forEach(function(boc){
						if(boc.status == 'CURRENTBOWLER' || boc.status == 'LASTBOWLER'){
							document.getElementById('bowler_text').innerHTML = boc.player.full_name + ' ' + boc.wickets 
										+ '-' + boc.runs + ' [' + boc.overs + '.' + boc.balls + ']'+'&emsp;&ensp;';
							document.getElementById('thisover_text').innerHTML = 'THIS OVER : ' + thisOverArr.map(s => s.replace("WIDE", "WD")
									.replace("NO_BALL", "NB").replace("LEG_BYE", "LB").replace("BYE", "B").replace("PENALTY", "PN")
									.replace("LOG_WICKET", "W").replace("WICKET", "W").replace("BOUNDARY", "")).reverse().join(" , ");
						}
					});	
				}
			}
		});
		break;
	}
}
function processUserSelection(whichInput)
{
  switch ($(whichInput).attr('name')) {
	case 'load_scene_btn':
      	document.initialise_form.method = 'post';
      	document.initialise_form.action = 'output';
      	document.initialise_form.submit();
		break;
	case 'cancel_graphics_btn':
		processCricketProcedures("CANCLE-GRAPHICS");
		$("#select_graphic_options_div").empty();
		document.getElementById('select_graphic_options_div').style.display = 'none';
		$("#captions_div").show();
		//document.getElementById("stats-container").innerHTML = "";
		break;
	case 'checkPlayerData':
		if($(key_press_hidden_input)) {
			processCricketProcedures("GRAPHICS_PREVIEW-OPTIONS", $('#key_press_hidden_input').val() + ',' + selected_options.toString());
		} else {
			processCricketProcedures("GRAPHICS_PREVIEW-OPTIONS", $('#which_keypress').val() + ',' + selected_options.toString());
		}
		break;
	case 'populate_btn': 
		if($(key_press_hidden_input)) {
			processCricketProcedures("POPULATE-GRAPHICS", $('#key_press_hidden_input').val() + ',' + selected_options.toString());
		} else {
			processCricketProcedures("POPULATE-GRAPHICS", $('#which_keypress').val() + ',' + selected_options.toString());
		}
		break;
	case 'change_on':
		if($('#which_keypress').val() == 'Shift_T' && $('#selected_broadcaster').val().toUpperCase() == 'T20_MUMBAI'){
			processCricketProcedures('PLAYING-XI-CHANGE-ON');
		}else if($('#which_keypress').val() == 'Shift_I'){
			processUserSelectionData('IMPACT-CHANGE-ON', 'Shift_I');	
		}
		break;
	case 'pop_up_change_on':
		dataToProcess = $('#which_keypress').val() + '_change_on' + ',' + selected_options.toString();
		processCricketProcedures("POPULATE-GRAPHICS", dataToProcess);
		break;
	case 'change_on_to_stats':
		dataToProcess = $('#which_keypress').val().replace("Control_F5", "F7").replace("Control_F9", "F11") + ',' + selected_options.toString();
		let parts = dataToProcess.split(',');
		if (parts.length > 2) {
		  parts.splice(3, 0, session_match.setup.matchType);
		}
		dataToProcess = parts.join(',');
		
		processCricketProcedures("POPULATE-GRAPHICS", dataToProcess);
		break;	
	case 'speedOnOrOff':
		processCricketProcedures('TURN_ON_OR_OFF_SPEED');
		break;
	case 'audioOnOrOff':
		processCricketProcedures('TURN_ON_OR_OFF_AUDIO');
		break;
	case 'headToHead_file':
		processCricketProcedures('HEAD_TO_HEAD_FILE');
		break;			
	}	
}

function processUserSelectionData(whatToProcess,dataToProcess)
{
	switch (whatToProcess) {
	case 'IMPACT-CHANGE-ON':
		processCricketProcedures('IMPACT-CHANGE-ON');
		break;
	case 'LOGGER_FORM_KEYPRESS':
		 document.getElementById('which_keypress').value = dataToProcess;
		//alert('dataToProcess = ' + dataToProcess);
		switch(dataToProcess) {
		/*case '7':
			processCricketProcedures('HEAD_TO_HEAD_FILE');
			break;*/
		case 'Escape':
			$("#select_graphic_options_div").empty();
			document.getElementById('select_graphic_options_div').style.display = 'none';
			$("#captions_div").show();
			break;
		case 'Alt_r':
			processCricketProcedures('RE_READ_DATA');
			break;
		case 'Alt_v':
			processCricketProcedures('DB_DATA_READ');
			break;	
		case 'Control_ ':
			processCricketProcedures('CLEAR-ALL-WITH-INFOBAR');
			break;
		case ' ':
			processCricketProcedures('CLEAR-ALL');
			break;
		case '`':
			processCricketProcedures('ANIMATE-OUT-ALL_INFOBAR_PART');
			break;
		case '=':
			processCricketProcedures('ANIMATE-OUT-SECOND_PLAYING');
			break;
		case '1': case '2': case '3': case '4':
			if(session_match.setup.maxOvers > 0 && session_match.setup.matchType !='TEST' ){
				switch (dataToProcess) {
				case '3': case '4': // Key 1 to 4
					alert("3rd and 4th inning NOT available in a limited over match");
					return false;
				}				
			}
			document.getElementById('which_inning').value = dataToProcess;
			document.getElementById('selected_inning').innerHTML = 'Selected Innings: ' + dataToProcess;
			
			document.getElementById('inning1_teamScore_lbl').setAttribute('style', 'font-family: "Bungee-Regular", sans-serif !important;');
			document.getElementById('inning2_teamScore_lbl').setAttribute('style', 'font-family: "Bungee-Regular", sans-serif !important;');
				
			if(dataToProcess == 1 ||dataToProcess == 3){
				document.getElementById('inning1_teamName').style.backgroundColor ='#990000';
				document.getElementById('inning1_teamName').style.color ='white';
				
				document.getElementById('inning2_teamName').style.backgroundColor ='';
				document.getElementById('inning2_teamName').style.color ='';
				
				document.getElementById('inning2_teamScore_lbl').style.backgroundColor ='';
				document.getElementById('inning2_teamScore_lbl').style.color ='';
				
				document.getElementById('inning1_teamScore_lbl').style.backgroundColor ='#990000';
				document.getElementById('inning1_teamScore_lbl').style.color ='white';
				
				
			}else if(dataToProcess == 2 ||dataToProcess == 4){
				document.getElementById('inning2_teamName').style.backgroundColor = '#990000';
				document.getElementById('inning2_teamName').style.color = 'white';
								
				document.getElementById('inning1_teamName').style.backgroundColor ='';
				document.getElementById('inning1_teamName').style.color ='';
				
				document.getElementById('inning1_teamScore_lbl').style.backgroundColor ='';
				document.getElementById('inning1_teamScore_lbl').style.color ='';
				
				document.getElementById('inning2_teamScore_lbl').style.backgroundColor ='#990000';
				document.getElementById('inning2_teamScore_lbl').style.color ='white';
			}
			break;
			
		case '-':
			if(confirm('It will Also Delete Your Preview from Directory...\r\n \r\nAre You Sure To Animate Out? ') == true){
				processCricketProcedures('ANIMATE-OUT-GRAPHICS');
			}
			break;
			
		case 'Shift_+':
			processCricketProcedures('ANIMATE-OUT-BOTTOM');
			break;
		case 'Alt_u':
			processCricketProcedures('ANIMATE-OUT-TARGET');
			break;
			
		case 'Alt_-':
			if(confirm('Animate Out Infobar? ') == true){
				processCricketProcedures('ANIMATE-OUT-INFOBAR');
			}
			break;
		case 'Alt_=':
			if(confirm('Animate Out Ident? ') == true){
				processCricketProcedures('ANIMATE-OUT-IDENT');
			}
			break;
		case 'o':
			processCricketProcedures("ANIMATE-OUT-GRAPHICS", dataToProcess);
			break;
		default:
			
			switch($('#which_inning').val()) {
			case 1: case 2: case 3: case 4:
				alert('Selected inning must be between 1 to 4 [found ' 
					+ document.getElementById('which_inning').value + ']');
				return false;
			}
			switch(dataToProcess) {
			case 'F1':
				switch($('#selected_broadcaster').val().toUpperCase()){
				case 'BCCI': case 'TRI_SERIES': case 'BAN_AFG_SERIES': case 'ACC': case 'AFG_SL_SERIES': case 'MT20': case 'TG20':
					dataToProcess = dataToProcess + ',' + document.getElementById('which_inning').value;
					processCricketProcedures("POPULATE-GRAPHICS", dataToProcess);
					break;
				}
				break;
			case 'F4': case 'Shift_K':
				switch($('#selected_broadcaster').val().toUpperCase()){
				case 'BCCI': case 'TRI_SERIES': case 'BAN_AFG_SERIES': case 'ACC': case 'AFG_SL_SERIES': case 'MT20': case 'TG20':
					dataToProcess = dataToProcess + ',' + document.getElementById('which_inning').value;
					processCricketProcedures("POPULATE-GRAPHICS", dataToProcess);
					break;
				}
				break;
			case 'Control_F11':
				switch($('#selected_broadcaster').val().toUpperCase()){
				case 'BCCI': case 'TRI_SERIES': case 'BAN_AFG_SERIES': case 'AFG_SL_SERIES': case 'MT20': case 'TG20':
					dataToProcess = dataToProcess + ',' + document.getElementById('which_inning').value;
					processCricketProcedures("POPULATE-GRAPHICS", dataToProcess);
					break;
				case 'ACC':
					addItemsToList(dataToProcess,null);
					break;	
				}
				break;
			case 'h':
				switch($('#selected_broadcaster').val().toUpperCase()){
				case 'ACC':
					dataToProcess = dataToProcess + ',' + document.getElementById('which_inning').value;
					processCricketProcedures("POPULATE-GRAPHICS", dataToProcess);
					break;
				case 'BCCI': case 'TRI_SERIES': case 'BAN_AFG_SERIES': case 'MT20': case 'TG20':
					addItemsToList(dataToProcess,null);
					break;	
				}
				break;
			case 'Control_p':
				switch($('#selected_broadcaster').val().toUpperCase()){
				case 'ACC':
					addItemsToList(dataToProcess,null);
					break;
				default:
					dataToProcess = dataToProcess + ',' + document.getElementById('which_inning').value;
					processCricketProcedures("POPULATE-GRAPHICS", dataToProcess);
					break;	
				}
				break;
					
			case 'F12': case 'Alt_1': case 'Alt_2': case 'Alt_6': case "Alt_5": case 'Alt_7': case 'Alt_8': case 'Control_F12': case 'Shift_F12': 
			case 'F7': case 'F11': case 'Control_s': case 'Control_f': case 'Control_Shift_F1': case 'F8': case 'Alt_F8': case 'F6': case 'Shift_F6': 
			case 'Control_F5': case 'Control_F9': case 'u': case 'F5': case 'F9': case 'Shift_F9': case 'Shift_F5': case 'Alt_F12': case 'Shift_E': 
			case 'g': case 'y': case 'Shift_O': case 'Shift_F4': case 'Control_Shift_U': case 'Control_Shift_V': case 'Shift_F': case 'Control_Shift_O': 
			case 'Control_Shift_Q': case 'Control_Shift_F7': case 'Control_Shift_F2': case 'Alt_F9': case 'Shift_Control_F1': case 'Shift_Control_F2':
			case 'Shift_P': case 'Shift_Q': case 'Alt_F1': case 'Alt_F2': case 'Control_c': case 'Control_Shift_X': case 'Control_Shift_K': case 'Shift_T': 
			case 'Shift_C': case 'l': case 'Alt_Shift_F4': case 'Alt_d': case 'r': case 'Control_Shift_D':
				addItemsToList(dataToProcess,null); 
				break;	

			case "F1": case "F2": case "F4": case "m": case 'Control_F6': case 'Shift_F3': case 'd': case 'e': case 'Control_h': case 'Alt_Shift_F3': 
			case 'Control_k': case 'Control_Shift_M': case 'Control_F3': case 'Control_4': case '6': case 'Control_a': case '5': case ';': case 'Control_F7': 
			case 'Control_Shift_F10': case 'Shift_F1': case 'Shift_F2': case 'Control_6': case 'Control_Shift_B': case 'Control_Shift_R': case 'Control_Shift_F3': 
			case 'Control_F10':  case 'Shift_F10': case 'Alt_F11': case 'Shift_D': case 'Control_F1': case 'Control_Alt_F1': case 'Alt_Shift_F1': case 'Alt_Shift_Q':
			case 'Control_5': case 'Control_7': case '7': case 'Alt_Shift_Q': case 'Alt_Shift_F12': 
				dataToProcess = dataToProcess + ',' + document.getElementById('which_inning').value;
				processCricketProcedures("POPULATE-GRAPHICS", dataToProcess);
				break;
			case 'Control_y':
				switch($('#selected_broadcaster').val().toUpperCase()){
				case 'BAN_AFG_SERIES':
					dataToProcess = dataToProcess + ',' + document.getElementById('which_inning').value;
					processCricketProcedures("POPULATE-GRAPHICS", dataToProcess);
					break;
				default:
					addItemsToList(dataToProcess,null);
					break;	
				}
				break;
			case 'Alt_F7':
				switch($('#selected_broadcaster').val().toUpperCase()){
				case 'ACC':
					addItemsToList(dataToProcess,null);
					break;
				default:
					dataToProcess = dataToProcess + ',' + document.getElementById('which_inning').value;
					processCricketProcedures("POPULATE-GRAPHICS", dataToProcess);
					break;	
				}
				break;	
				
			case 'Alt_p':
				if(session_animation != null && session_animation.specialBugOnScreen == 'TOSS') {
					processCricketProcedures("ANIMATE-OUT-GRAPHICS", dataToProcess);
				} else {
					addItemsToList(dataToProcess,null);
					//processCricketProcedures("POPULATE-GRAPHICS", dataToProcess);
				}
				break;
			case 'Alt_g':
				processCricketProcedures("ANIMATE-IN-GRAPHICS", dataToProcess);
				break;		
			case 'ArrowDown': case 'ArrowUp': case 'w': case 'i': case 'f': case 's': case '0':	case '8':
			case 'ArrowLeft': case 'ArrowRight': case '9': case '.': case 'Alt_e':
				dataToProcess = dataToProcess + ',' + document.getElementById('which_inning').value;
				processCricketProcedures("ANIMATE-IN-GRAPHICS", dataToProcess);
				break;
			default:
				processCricketProcedures("GRAPHICS-OPTIONS", dataToProcess);
				break;
			}
		}
		break;
	}
}
function processCricketProcedures(whatToProcess,dataToProcess)
{
	var valueToProcess = dataToProcess;
	switch(whatToProcess) {
	
	case 'TURN_ON_OR_OFF_SPEED':
		valueToProcess = $('#speedOnOrOff').is(":checked");
		break;	
	case 'TURN_ON_OR_OFF_AUDIO':
		valueToProcess = $('#audioOnOrOff').is(":checked");
		break;	
	case 'QUIDICH-COMMANDS':
		valueToProcess = dataToProcess;
		break;
	case 'GET-CONFIG-DATA':
		valueToProcess = $('#select_configuration_file option:selected').val();
		break;
	case 'GET_ARCHIVE_MATCH_FILES':
		valueToProcess = $('#select_type option:selected').val();
		break;	
	case 'READ-MATCH-AND-POPULATE':
		valueToProcess = $('#matchFileTimeStamp').val();
		break;
	}		
	$.ajax({    
        type : 'Get',     
        url : 'processCricketProcedures.html',     
        data : 'whatToProcess=' + whatToProcess + '&valueToProcess=' + valueToProcess, 
        dataType : 'json',
        success : function(data) {
			switch(whatToProcess) {
			case 'HEAD_TO_HEAD_FILE':
				alert(data.match.matchFileName + ' H2H FILE IS CREATED');
				break;
			case 'GET-CONFIG-DATA':
				initialiseForm('UPDATE-CONFIG',data);
				break;
			case 'GET_ARCHIVE_MATCH_FILES':
				initialiseForm('UPDATE-MATCHES',data);
				break;	
			case 'READ-MATCH-AND-POPULATE': case "RE_READ_DATA": case 'DB_DATA_READ':
				
				if(data){
					session_match = data;
					initialiseForm('UPDATE-MATCH-ON-OUTPUT-FORM',data);
				}
				if(whatToProcess == "RE_READ_DATA" || whatToProcess == 'DB_DATA_READ'){
					alert("Data is Loaded");
				}
				break;
			case 'SHOW_SPEED':
				if(data == true){
					document.getElementById('speed_lbl').innerHTML = 'Show Speed: ' + 'YES';
				}else if(data == false){
					document.getElementById('speed_lbl').innerHTML = 'Show Speed: ' + 'NO';
				}
				break;
			case 'GRAPHICS_PREVIEW-OPTIONS':
				displayStats(data);
				break;
			default:
				switch(whatToProcess) {
				case 'POPULATE-GRAPHICS':
					if(data == 'OK') {
						session_caption = data;
						if(confirm('Animate In?') == true) {
							$('.my_waiting_modal').modal('hide');
							//setTimeout(function(){$('.my_waiting_modal').modal('hide');},3000);
							processCricketProcedures(whatToProcess.replace('POPULATE-', 'ANIMATE-IN-'),dataToProcess);
							if(dataToProcess.split(',')[0] == 'Shift_F') {
									if($('#selectWicketSequence option:last').val()){
										
									}
									$('#selectWicketSequence option:selected').next().prop('selected', true);
									document.getElementById('selectWicketSequence').setAttribute('onchange','setDropdownOptionForWicketSequence(0)');
									setDropdownOptionForWicketSequence(0);
							}else if(dataToProcess.split(',')[0] == 'Alt_b') {
								if($('#selectWicketplayer option:last').val()){
									
								}
								document.getElementById('selectWicketSequence').setAttribute('onchange','setDropdownOptionForWicketBowlerSequence(0)');
								setDropdownOptionForWicketBowlerSequence(0);
								$('#selectWicketplayer option:selected').next().prop('selected', true);
								document.getElementById('selectWicketplayer').setAttribute('onchange','setDropdownOptionForWicketBowlerSequence(1)');
								setDropdownOptionForWicketBowlerSequence(1);
							}
						}else {
							processUserSelection($('#cancel_graphics_btn').attr('value','cancel_graphics_btn'));
						}
					} else {
						if(data != 'YES' && typeof data !== 'object'){
						      
						        alert(data);
						    }	
						/*$("#select_graphic_options_div").empty();
						document.getElementById('select_graphic_options_div').style.display = 'none';
						$("#captions_div").show();*/
					}
					document.activeElement.blur();
					break;
				case 'GRAPHICS-OPTIONS':
					addItemsToList(dataToProcess,data);
					break;
				case "GRAPHICS-OPTIONS_DATA":
					//alert(JSON.stringify(data));
					if(valueToProcess.includes("Alt_3")){
						setPlayerDropdown(data)
					}else if(valueToProcess.includes("Alt_8") || valueToProcess.includes("Alt_0")){
						if(valueToProcess.includes("Commentators")){
							setCommentators("Commentators",data);
						}else if(valueToProcess.includes("FreeTextDb")){
							setInfoBarStatsDropdown("FreeTextDb",data);
						}else if(valueToProcess.includes("Sponsor")){
							setSponsorDropdown("Sponsor",data);
						}
					}else{
						setPlayerInDropdown(data);
					}
				
					break;	
				default:
					if(whatToProcess.includes("ANIMATE-IN-") || whatToProcess.includes("ANIMATE-OUT-")) {
						session_animation = data;
					}
					document.activeElement.blur();
					break;
				}
				break;
			}
		}
	});
}

function displayStats(data) {
    let container = document.getElementById("stats-container");

    if (!container) {
        container = document.createElement("div");
        container.id = "stats-container";
        document.body.appendChild(container);
    }

    container.innerHTML = ""; // Clear old data

    if (!data || !Array.isArray(data) || data.length === 0) {
        container.innerHTML = "<div>No stats available</div>";
        return;
    }

    // Player name
    const playerName = data[0] || "Unknown Player";
    const nameDiv = document.createElement("div");
    nameDiv.id = "player-name";
    nameDiv.textContent = playerName;
    container.appendChild(nameDiv);

    // Stats line
    const statsLine = document.createElement("div");
    statsLine.id = "stats-line";

    const statsText = data.slice(1)
        .filter(item => item && !item.toLowerCase().includes("undefined"))
        .map(item => {
            const [label, value] = item.split(",");
            return `<strong>${label}</strong> - ${value}`;
        })
        .join(" &nbsp; ");

    statsLine.innerHTML = statsText;
    container.appendChild(statsLine);
}
function removeSelectDuplicates(select_id)
{
	var this_list = {};
	$("select[id='" + select_id + "'] > option").each(function () {
	    if(this_list[this.text]) {
	        $(this).remove();
	    } else {
	        this_list[this.text] = this.value;
	    }
	});
}
function setCommentators(type, data) {
  if (type === "Commentators" && Array.isArray(data)) {
    const commentatorCells = [
      document.getElementById('Player1'),
      document.getElementById('Player2'),
      document.getElementById('Player3')
    ];

    // Clear each cell and add a commentator dropdown
    commentatorCells.forEach((cell, index) => {
      if (!cell) {
        console.warn(`Cell Player${index+1} not found`);
        return;
      }

      cell.innerHTML = '';

      const commSelect = document.createElement('select');
      commSelect.id = `commentatorDropdown${index + 1}`;

      // Add a default empty option
      const defaultOption = document.createElement('option');
      defaultOption.value = '0';
      defaultOption.text = '';
      commSelect.appendChild(defaultOption);

      // Use the passed data array here
      data.forEach(comm => {
        if (comm.useThis === 'Yes') {
          const option = document.createElement('option');
          option.value = comm.commentatorId;
          option.text = comm.commentatorName;
          commSelect.appendChild(option);
        }
      });
      commSelect.selectedIndex = 0;

      $(commSelect).on('change', function() {
        setDropdownOptionToSelectOptionArray($(this), index + 1);
      });

      cell.appendChild(commSelect);

      // Initialize selection
      setDropdownOptionToSelectOptionArray($(commSelect), index + 1);
      $(commSelect).trigger('change');
    });
  }
}

function setInfoBarStatsDropdown(type, data) {
  if (type === "FreeTextDb" && Array.isArray(data)) {
	
	const freeTextCells = [
	      document.getElementById('FreeText')
	    ];

    // Clear each cell and add a commentator dropdown
    freeTextCells.forEach((cell, index) => {
      if (!cell) {
        console.warn(`Cell Player${index+1} not found`);
        return;
      }

      cell.innerHTML = '';

      const freeTextSelect = document.createElement('select');
      freeTextSelect.id = `commentatorDropdown${index + 1}`;

      // Add a default empty option
      const defaultOption = document.createElement('option');
      defaultOption.value = '0';
      defaultOption.text = '';
      freeTextSelect.appendChild(defaultOption);

      // Use the passed data array here
      data.forEach(pro1 => {
        const option = document.createElement('option');
          option.value = pro1.order;  
          option.text = pro1.prompt;
          freeTextSelect.appendChild(option);
      });
      freeTextSelect.selectedIndex = 0;

      $(freeTextSelect).on('change', function() {
        setDropdownOptionToSelectOptionArray($(this), index + 1);
      });

      cell.appendChild(freeTextSelect);

      // Initialize selection
      setDropdownOptionToSelectOptionArray($(freeTextSelect), index + 1);
      $(freeTextSelect).trigger('change');
    });
  }
}
function setSponsorDropdown(type, data) {
  if (type === "Sponsor" && Array.isArray(data)) {
	
	const sponsorCells = [
	      document.getElementById('SponsorValue')
	    ];

    // Clear each cell and add a commentator dropdown
    sponsorCells.forEach((cell, index) => {
      if (!cell) {
        console.warn(`Cell Player${index+1} not found`);
        return;
      }

      cell.innerHTML = '';

      const sponsorCells = document.createElement('select');
      sponsorCells.id = `commentatorDropdown${index + 1}`;

      // Add a default empty option
      const defaultOption = document.createElement('option');
      defaultOption.value = '0';
      defaultOption.text = '';
      sponsorCells.appendChild(defaultOption);

      // Use the passed data array here
      data.forEach(pro1 => {
        const option = document.createElement('option');
          option.value = pro1.sponsorId;  
          option.text = pro1.prompt;
          sponsorCells.appendChild(option);
      });
      sponsorCells.selectedIndex = 0;

      $(sponsorCells).on('change', function() {
        setDropdownOptionToSelectOptionArray($(this), index + 1);
      });

      cell.appendChild(sponsorCells);

      // Initialize selection
      setDropdownOptionToSelectOptionArray($(sponsorCells), index + 1);
      $(sponsorCells).trigger('change');
    });
  }
}

function setPlayerInDropdown(dataToProcess) {
 	const playerCell = document.getElementById('Player');
 	playerCell.innerHTML = ''; 	
    const playerSelect = document.createElement('select');
    playerSelect.id = 'playerDropdown';
    
    let numb = 0;
	dataToProcess.forEach(player => {
		numb++;
		const option = document.createElement('option');
        option.value = numb + '_' + player.playerId;  
        option.text = player.player.full_name; 
        playerSelect.appendChild(option);
  	});
    
	playerSelect.selectedIndex = 0;
    $(playerSelect).on('change', function() {
		setDropdownOptionToSelectOptionArray($(this), 0);
    });        
    playerCell.appendChild(playerSelect);
	   setDropdownOptionToSelectOptionArray($(this), 0);
	$(playerSelect).trigger('change');
}
function setDropdownOptionToSelectOptionArray(whichInput, whichIndex)
{
	isSplitScorecard = false;
	if($('#' + $(whichInput).attr('id') + ' option:selected').val() == 'SPLIT'){
		isSplitScorecard = true;
		addItemsToList('F1')
		selected_options[selected_options.length] = 'SPLIT';
	}
	selected_options[0] = document.getElementById('which_inning').value;
	selected_options[whichIndex+1] = $('#' + $(whichInput).attr('id') + ' option:selected').val();
}

function setTextBoxOptionToSelectOptionArray(whichIndex)
{
	selected_options[0] = document.getElementById('which_inning').value;
	selected_options[whichIndex+1] = $('#selectFreeText').val();
}
function setTextBoxOptionToSelectOptionArray1(whichIndex)
{
	selected_options[0] = document.getElementById('which_inning').value;
	selected_options[whichIndex+1] = $('#selectFreeText1').val();
}

function setTextBoxOptionForSixDistanceToSelectOptionArray(whichIndex)
{
	selected_options[0] = document.getElementById('which_inning').value+','+$('#selectFreeText').val();
	selected_options[whichIndex+1] = $('#sixOrNine option:selected').val();
}
function setDropdownOptionForWicketSequence(whichIndex)
{
	selected_options[0] = document.getElementById('which_inning').value;
	selected_options[whichIndex+1] = $('#selectWicketSequence option:selected').val();
}
function setDropdownOptionForWicketBowlerSequence(whichIndex)
{
	selected_options[0] = document.getElementById('which_inning').value+ ","+
		$('#selectWicketSequence option:selected').val();
	selected_options[whichIndex+1] = $('#selectWicketplayer option:selected').val();
}
function getStrikeRate(totalRunsScored, totalBallsFaced, numberOfDecimals, defaultValue) {
    if (totalBallsFaced <= 0) {
        return defaultValue;
    } else {
        if (numberOfDecimals > 0) {
            return ((totalRunsScored*100) / totalBallsFaced).toFixed(numberOfDecimals);
        } else {
            return defaultValue;
        }
    }
}
function getEconomy(totalRunsConceded, totalBallsBowled, numberOfDecimals, defaultValue) {
    if (totalBallsBowled <= 0) {
        return defaultValue;
    } else {	
        if (numberOfDecimals > 0) {
            return ((totalRunsConceded / totalBallsBowled) * 6).toFixed(numberOfDecimals);
        } else {
            return defaultValue;
		}
 	}
 }
function addItemsToList(whatToProcess,dataToProcess)
{
	var select,option,header_text,div,table,table_data,tbody,row;
	var cellCount = 0,row_count=0;
	
	switch(whatToProcess) {
	case 'F12': case 'Alt_1': case 'Alt_2': case "Alt_3": case 'Alt_4': case "Alt_5": case 'Alt_6': case 'Alt_7': case 'Alt_0': case 'Alt_8': case 'Shift_~': case 'Shift_!': case 'Control_F9': 
	case 'Control_F12': case 'Shift_F12': case 'Control_Shift_F1': case 'Alt_F8': case 'F8': case 'F10': case 'F6': case 'F7': case 'F11': case 'Shift_F6':
	case 'Control_F5': case 'Control_s': case 'Control_f': case 'u': case 'F5': case 'F9': case 'Shift_F9': case 'Shift_F5': case 'Alt_F12': case 'Shift_E':
	case 'y': case 'k': case 'g': case 'h': case 'Control_y': case 'Shift_O': case 'Shift_F4': case 'Alt_p': case 'Control_Shift_U': case 'Control_Shift_V':
	case 'Control_d': case 'Control_e': case 'Control_m': case 'Shift_F': case 'Control_Shift_J': case 'Control_Shift_L': case 'Control_Shift_O': case 'Control_Shift_Q':
	case 'Control_Shift_F7': case 'Control_Shift_F2': case 'Control_Shift_*': case 'Alt_F9': case 'Shift_F11': case 'z': case 'x': case 'c': case 'v': case 'Control_z': case 'Control_x':
	case 'Control_Shift_F4': case 'Control_Shift_F5': case 'Shift_P': case 'Shift_Q': case 'Alt_F1': case 'Alt_F2': case 'Control_Shift_Y': case 'Control_Shift_Z':
	case 'Control_c': case 'Control_Shift_X': case 'Control_Shift_K': case 'Shift_T': case 'Shift_C': case 'Control_F11': case 'Control_p': case 'Alt_F7': case 'l': case 'Alt_Shift_F4':
	case 'Alt_d': case 'r': case 'Control_Shift_D': case 'Alt_z':
		$("#captions_div").hide();
		$('#select_graphic_options_div').empty();
   		initialiseSelectedOptionsList();
		header_text = document.createElement('h6');
		header_text.innerHTML = 'Select Graphic Options';
		document.getElementById('select_graphic_options_div').appendChild(header_text);
		
		table = document.createElement('table');
		table.setAttribute('class', 'table table-bordered');
				
		tbody = document.createElement('tbody');

		table.appendChild(tbody);
		document.getElementById('select_graphic_options_div').appendChild(table);
		
		row = tbody.insertRow(tbody.rows.length);
		
		switch(whatToProcess) {
		case 'Alt_z':
			header_text.innerHTML = 'SQUAD';
			
			select = document.createElement('select');
			select.id = 'selectTeams';
			select.name = select.id;
			
			dataToProcess.forEach(function(teams){
				option = document.createElement('option');
				option.value = teams.teamId;
				option.text = teams.teamName1;
				select.appendChild(option);
			});
			
			select.setAttribute('onchange',"setDropdownOptionToSelectOptionArray(this, 0)");
			row.insertCell(cellCount).appendChild(select);
			setDropdownOptionToSelectOptionArray($(select),0);
			cellCount = cellCount + 1;
			break;
		case 'Shift_!':
			header_text.innerHTML = 'PLAYER CAREER STATS';
			
			thead = document.createElement('thead');
			tr = document.createElement('tr');
			for (var j = 0; j <= 4; j++) {
			    th = document.createElement('th'); // Column
				th.scope = 'col';
			    switch (j) {
				case 0:
				    th.innerHTML = 'TEAM/PLAYER';
					break;
				case 1:
					th.innerHTML = 'BAT T20I STATS (M|R|50/100|SR)';
					break;
				case 2:
					th.innerHTML = 'BALL T20I STATS (M|W|R|Econ.)';
					break;
				case 3:
					th.innerHTML = 'BAT ODI STATS (M|R|50/100|SR)';
					break;
				case 4:
					th.innerHTML = 'BALL ODI STATS (M|W|R|Econ.)';
					break;
				}	
				tr.appendChild(th);
			}
			thead.appendChild(tr);
			table.appendChild(thead);
			alert(dataToProcess.length);
			for(var i = 0; i <= dataToProcess.length - 1; i++){
				row = tbody.insertRow(tbody.rows.length);
				for(var j = 0; j <= 4; j++){
					text = document.createElement('text');
					switch(j){
					case 0:
						text.innerHTML = dataToProcess[i].playerId + ' - ' + dataToProcess[i].player.full_name + ' (' + dataToProcess[i].team.teamName4 + ')';
						break;
					case 1: case 2: case 3: case 4:
						text.innerHTML = 'NO DATA IN DB' ;
						break;
					}
					row.insertCell(j).appendChild(text);
				}
			}
			break;
		case 'Shift_~':
			header_text.innerHTML = 'PLAYER STATS';
			
			thead = document.createElement('thead');
			tr = document.createElement('tr');
			for (var j = 0; j <= 4; j++) {
			    th = document.createElement('th'); // Column
				th.scope = 'col';
			    switch (j) {
				case 0:
				    th.innerHTML = 'TEAM/PLAYER';
					break;
				case 1:
					th.innerHTML = 'BAT CAREER STATS (M|R|50/100|SR)';
					break;
				case 2:
					th.innerHTML = 'BALL CAREER STATS (M|W|R|Econ.)';
					break;
				case 3:
					th.innerHTML = 'BAT THIS SERIES (M|R|50/100|SR)';
					break;
				case 4:
					th.innerHTML = 'BALL THIS SERIES (M|W|R|Econ.)';
					break;
				}	
				tr.appendChild(th);
			}
			thead.appendChild(tr);
			table.appendChild(thead);
			
			for(var i = 0; i <= dataToProcess.length - 1; i++){
				row = tbody.insertRow(tbody.rows.length);
				for(var j = 0; j <= 4; j++){
					text = document.createElement('text');
					switch(j){
					case 0:
						text.innerHTML = dataToProcess[i].playerId + ' - ' + dataToProcess[i].player.full_name + ' (' + dataToProcess[i].team.teamName4 + ')';
						break;
					case 1:
						if(dataToProcess[i].stats != null) {
							text.innerHTML = dataToProcess[i].stats.matches + ' | ' + dataToProcess[i].stats.runs + ' | ' + dataToProcess[i].stats.fifties + '/' +
								dataToProcess[i].stats.hundreds + ' | ' + getStrikeRate(dataToProcess[i].stats.runs,dataToProcess[i].stats.balls_faced,1,'-') ;
						}else {
							text.innerHTML = 'NO DATA IN DB' ;
						}
						break;
					case 2:
						if(dataToProcess[i].stats != null) {
							text.innerHTML = dataToProcess[i].stats.matches + ' | ' + dataToProcess[i].stats.wickets + ' | ' + dataToProcess[i].stats.runs_conceded + 
								' | ' + getEconomy(dataToProcess[i].stats.runs_conceded,dataToProcess[i].stats.balls_bowled,2,'-');
						}else {
							text.innerHTML = 'NO DATA IN DB' ;
						}
						break;
					case 3:
						if(dataToProcess[i].tournament != null) {
							text.innerHTML = dataToProcess[i].tournament.matches + ' | ' + dataToProcess[i].tournament.runs + ' | ' + dataToProcess[i].tournament.fifty + '/' 
								+ dataToProcess[i].tournament.hundreds + ' | ' + getStrikeRate(dataToProcess[i].tournament.runs,dataToProcess[i].tournament.ballsFaced,1,'-');
						}else {
							text.innerHTML = 'NO DATA' ;
						}
						break;
					case 4:
						if(dataToProcess[i].tournament != null) {
							text.innerHTML = dataToProcess[i].tournament.matches + ' | ' + dataToProcess[i].tournament.wickets + ' | ' + dataToProcess[i].tournament.runsConceded + 
								' | ' + getEconomy(dataToProcess[i].tournament.runsConceded,dataToProcess[i].tournament.ballsBowled,2,'-');
						}else {
							text.innerHTML = 'NO DATA' ;
						}
						break;
					}
					row.insertCell(j).appendChild(text);
				}
			}
			break;
			case 'Shift_F12':
				header_text.innerHTML =  'INFOBAR IDENT CHANGE ON';
			
				select = document.createElement('select');
				select.id = select.name = 'selectIdentInfo';
			
				session_match.match.inning.filter(inn => inn.isCurrentInning === 'YES').forEach(inn => {
					const opts = (inn.inningNumber == 1)
						? [{ value: 'TOSS', text: 'Toss' }, { value: 'VENUE', text: 'Venue' }, { value: 'SUPEROVER', text: 'Super Over'}]
						: [{ value: 'VENUE', text: 'Venue' }, { value: 'TARGET', text: 'Target'},
						{ value: 'EQUATION', text: 'Equation'},{ value: 'SUPEROVER', text: 'Super Over'},
						{ value: 'RESULT', text: 'Result'}];
					
					opts.forEach(({ value, text }) => {
						const option = new Option(text, value);
						select.appendChild(option);
					});
				});
			
				select.setAttribute('onchange', "setDropdownOptionToSelectOptionArray(this, 0)");
				row.insertCell(cellCount).appendChild(select);
				setDropdownOptionToSelectOptionArray($(select), 0);
				cellCount++;
				break;
			case 'Control_F12':
				header_text.innerHTML =  'INFOBAR IDENT';
				
				switch($('#selected_broadcaster').val().toUpperCase()) {
				case 'BAN_AFG_SERIES': case 'ACC':
					select = document.createElement('select');
					select.id = select.name = 'selectIdentInfo';
				
					session_match.match.inning.filter(inn => inn.isCurrentInning === 'YES').forEach(inn => {
						const opts = (inn.inningNumber == 1)
							? [{ value: 'TOSS', text: 'Toss' }, { value: 'VENUE', text: 'Venue' }, { value: 'SUPEROVER', text: 'Super Over'}]
							: [{ value: 'TARGET', text: 'Target'},{ value: 'VENUE', text: 'Venue' },
							{ value: 'EQUATION', text: 'Equation'},{ value: 'SUPEROVER', text: 'Super Over'},
							{ value: 'RESULT', text: 'Result'}];
						
						opts.forEach(({ value, text }) => {
							const option = new Option(text, value);
							select.appendChild(option);
						});
					});
				
					select.setAttribute('onchange', "setDropdownOptionToSelectOptionArray(this, 0)");
					row.insertCell(cellCount).appendChild(select);
					setDropdownOptionToSelectOptionArray($(select), 0);
					cellCount++;
					break;
				case 'BCCI': case 'TRI_SERIES': case 'AFG_SL_SERIES': case 'MT20': case 'TG20':
			        select = document.createElement('select');
					select.id = select.name = 'selectWhichIdent';
					[{ value: 'FULL', text: 'Full Name' },
						{ value: 'SHORT', text: 'Short Name' }
					].forEach(({ value, text }) => {
						const option = new Option(text, value);
						select.appendChild(option);
					});
				
					select.setAttribute('onchange', "setDropdownOptionToSelectOptionArray(this, 0)");
					row.insertCell(cellCount).appendChild(select);
					setDropdownOptionToSelectOptionArray($(select), 0);
					cellCount++;
					
					select = document.createElement('select');
					select.id = select.name = 'selectIdentInfo';
				
					session_match.match.inning.filter(inn => inn.isCurrentInning === 'YES').forEach(inn => {
						const opts = (inn.inningNumber == 1)
							? [{ value: 'TOSS', text: 'Toss' }, { value: 'VENUE', text: 'Venue' }, { value: 'SUPEROVER', text: 'Super Over'}]
							: [{ value: 'TARGET', text: 'Target'},{ value: 'VENUE', text: 'Venue' },
							{ value: 'EQUATION', text: 'Equation'},{ value: 'SUPEROVER', text: 'Super Over'},
							{ value: 'RESULT', text: 'Result'}];
						
						opts.forEach(({ value, text }) => {
							const option = new Option(text, value);
							select.appendChild(option);
						});
					});
				
					select.setAttribute('onchange', "setDropdownOptionToSelectOptionArray(this, 1)");
					row.insertCell(cellCount).appendChild(select);
					setDropdownOptionToSelectOptionArray($(select), 1);
					cellCount++;
					break;
					}
				break;
			case "Alt_3": case 'Control_d':
			switch(whatToProcess){
				case "Alt_3":
					header_text.innerHTML = 'INFOBAR - BATTER CAREER';
					break;
				case 'Control_d':
					header_text.innerHTML = 'FF - BATTER CAREER';
					break;
			}
			switch($('#selected_broadcaster').val().toUpperCase()){
				case 'TRI_SERIES': case 'BAN_AFG_SERIES':  case 'ACC': case 'AFG_SL_SERIES': case 'MT20': case 'TG20':
				select = document.createElement('select');
				select.id = 'selectPlayerName';
				select.name = select.id;
				
				session_match.match.inning.forEach(function(inn){
					if(inn.isCurrentInning == 'YES'){
						inn.battingCard.forEach(function(bc){
							if(bc.status == 'NOT OUT'){
								if(bc.onStrike == 'YES'){
									option = document.createElement('option');
									option.value = bc.player.playerId;
									option.text = bc.player.full_name;
									select.appendChild(option);
								}else{
									option = document.createElement('option');
									option.value = bc.player.playerId;
									option.text = bc.player.full_name;
									select.appendChild(option);
								}
							}
						});
						
						if(inn.battingTeamId == session_match.setup.homeTeamId){
							session_match.setup.homeSquad.forEach(function(hs){
								option = document.createElement('option');
								option.value = hs.playerId;
								option.text = hs.full_name;
								select.appendChild(option);
							});
							session_match.setup.homeOtherSquad.forEach(function(hos){
								option = document.createElement('option');
								option.value = hos.playerId;
								option.text = hos.full_name  + ' (OTHER)';
								select.appendChild(option);
							});
						}else {
							session_match.setup.awaySquad.forEach(function(as){
								option = document.createElement('option');
								option.value = as.playerId;
								option.text = as.full_name;
								select.appendChild(option);
							});
							session_match.setup.awayOtherSquad.forEach(function(aos){
								option = document.createElement('option');
								option.value = aos.playerId;
								option.text = aos.full_name  + ' (OTHER)';
								select.appendChild(option);
							});
						}
					}
				});
	
				select.setAttribute('onchange',"setDropdownOptionToSelectOptionArray(this, 0)");
				row.insertCell(cellCount).appendChild(select);
				removeSelectDuplicates(select.id);
				setDropdownOptionToSelectOptionArray($(select),0);
				cellCount = cellCount + 1;
				
				select = document.createElement('select');
				select.id = 'selectProfile';
				select.name = select.id;
				
				switch($('#selected_broadcaster').val().toUpperCase()){
				case 'TG20':
					option = document.createElement('option');
					option.value = 'DT20';
					option.text = 'T20';
					select.appendChild(option);
					switch(whatToProcess){
					case "Alt_3":
						option = document.createElement('option');
						option.value = 'THIS_SERIES';
						option.text = 'THIS SERIES';
						select.appendChild(option);
						break;
					}
					break;
					
				case 'TRI_SERIES': case 'BAN_AFG_SERIES': case 'ACC': case 'AFG_SL_SERIES': case 'MT20':
				    
					option = document.createElement('option');
					option.value = 'DT20';
					option.text = 'T20';
					select.appendChild(option);
					
					option = document.createElement('option');
					option.value = 'MAHARAJA_CAREER';
					option.text = 'MAHARAJA';
					select.appendChild(option);
					
					option = document.createElement('option');
					option.value = 'IT20';
					option.text = 'T20-I';
					select.appendChild(option);
					
					option = document.createElement('option');
					option.value = 'LIST A';
					option.text = 'LIST A';
					select.appendChild(option);
					
					option = document.createElement('option');
					option.value = 'ODI';
					option.text = 'ODI';
					select.appendChild(option);
					
					option = document.createElement('option');
					option.value = 'TEST';
					option.text = 'TEST MATCHES';
					select.appendChild(option);
					
					switch(whatToProcess){
						case "Alt_3":
							option = document.createElement('option');
							option.value = 'THIS_SERIES';
							option.text = 'THIS SERIES';
							select.appendChild(option);
							break;
					}
					
					break;
				}
				
				select.setAttribute('onchange',"setDropdownOptionToSelectOptionArray(this, 1)");
				row.insertCell(cellCount).appendChild(select);
				setDropdownOptionToSelectOptionArray($(select),1);
				cellCount = cellCount + 1
				
				switch(whatToProcess){
				case 'Control_d':
					switch($('#selected_broadcaster').val().toUpperCase()){
					case 'TRI_SERIES': case 'MT20': case 'TG20':
						select = document.createElement('select');
						select.id = 'selectImage';
						select.name = select.id;
						
						option = document.createElement('option');
						option.value = 'WITHOUT';
						option.text = 'WithOut Image';
						select.appendChild(option);
						
						option = document.createElement('option');
						option.value = 'WITH';
						option.text = 'With Image';
						select.appendChild(option);
						
						select.setAttribute('onchange',"setDropdownOptionToSelectOptionArray(this, 2)");
						row.insertCell(cellCount).appendChild(select);
						setDropdownOptionToSelectOptionArray($(select),2);
						cellCount = cellCount + 1
						break;
					}
					break;
				}
				break;	
			}
			/*select = document.createElement('select');
			select.id = select.name = 'selectIdentInfo';
		
			[
				{ value: 'SEIRES', text: 'Series' }, 
				{ value: 'FORMAT', text: 'Format' }, 
				{ value: 'INNINGS', text: 'Innings' },
				{ value: 'VENUE', text: 'Venue' },
				{ value: 'CAPTAIN', text: 'Captain' },
				{ value: 'WICKETKEEPER', text: 'WicketKeeper' },
				{ value: 'OPPONENT', text: 'Opponent' },
				{ value: 'TROPHY', text: 'Trophy' },
				{ value: 'POSITION', text: 'Position' }
			].forEach(({ value, text }) => {
				const option = new Option(text, value);
				select.appendChild(option);
			});
		
			select.setAttribute('onchange', "setDropdownOptionToSelectOptionArray(this, 0)");
			row.insertCell(cellCount).appendChild(select);
			setDropdownOptionToSelectOptionArray($(select), 0);
			cellCount++;
		 	row.insertCell(cellCount).id = 'Player';
			 cellCount++;
			select.addEventListener('change', function () {
				header_text.innerHTML = "INFOBAR BATTING STATS " + this.value;
			   processCricketProcedures("GRAPHICS-OPTIONS_DATA", whatToProcess + "," +
	    			(this.value || $(this).find('option').first().val()));
			});
		
			select.dispatchEvent(new Event('change'));*/	
			break;
		case 'Alt_4': case 'Control_e':
			header_text.innerHTML = 'INFOBAR - BOWLER CAREER';
			switch($('#selected_broadcaster').val().toUpperCase()){
			case 'TRI_SERIES': case 'BAN_AFG_SERIES': case 'ACC': case 'AFG_SL_SERIES': case 'MT20': case 'TG20':
				select = document.createElement('select');
				select.id = 'selectPlayerName';
				select.name = select.id;
				
				session_match.match.inning.forEach(function(inn){
					if(inn.isCurrentInning == 'YES'){
						if(inn.bowlingCard != null){
							inn.bowlingCard.forEach(function(boc){
								if(boc.status != null){
									if(boc.status == 'CURRENTBOWLER'){
										option = document.createElement('option');
										option.value = boc.player.playerId;
										option.text = boc.player.full_name;
										select.appendChild(option);
									}
								}
							});
						}
						
						if(inn.bowlingTeamId == session_match.setup.homeTeamId){
							session_match.setup.homeSquad.forEach(function(hs){
								option = document.createElement('option');
								option.value = hs.playerId;
								option.text = hs.full_name;
								select.appendChild(option);
							});
							session_match.setup.homeOtherSquad.forEach(function(hos){
								option = document.createElement('option');
								option.value = hos.playerId;
								option.text = hos.full_name  + ' (OTHER)';
								select.appendChild(option);
							});
						}else {
							session_match.setup.awaySquad.forEach(function(as){
								option = document.createElement('option');
								option.value = as.playerId;
								option.text = as.full_name;
								select.appendChild(option);
							});
							session_match.setup.awayOtherSquad.forEach(function(aos){
								option = document.createElement('option');
								option.value = aos.playerId;
								option.text = aos.full_name  + ' (OTHER)';
								select.appendChild(option);
							});
						}
					}
				});
	
				select.setAttribute('onchange',"setDropdownOptionToSelectOptionArray(this, 0)");
				row.insertCell(cellCount).appendChild(select);
				removeSelectDuplicates(select.id);
				setDropdownOptionToSelectOptionArray($(select),0);
				cellCount = cellCount + 1;
				
				select = document.createElement('select');
				select.id = 'selectProfile';
				select.name = select.id;
				
				switch($('#selected_broadcaster').val().toUpperCase()){
				case 'TG20':	
					option = document.createElement('option');
					option.value = 'DT20';
					option.text = 'T20';
					select.appendChild(option);
				switch(whatToProcess){
					case "Alt_4":
						option = document.createElement('option');
						option.value = 'THIS_SERIES';
						option.text = 'THIS SERIES';
						select.appendChild(option);
						break;
					}
					break;
				case 'TRI_SERIES': case 'BAN_AFG_SERIES': case 'ACC': case 'AFG_SL_SERIES': case 'MT20':
					
					option = document.createElement('option');
					option.value = 'DT20';
					option.text = 'T20';
					select.appendChild(option);
					
					option = document.createElement('option');
					option.value = 'MAHARAJA_CAREER';
					option.text = 'MAHARAJA';
					select.appendChild(option);
					
					option = document.createElement('option');
					option.value = 'IT20';
					option.text = 'T20-I';
					select.appendChild(option);
					
					option = document.createElement('option');
					option.value = 'LIST A';
					option.text = 'LIST A';
					select.appendChild(option);
					
					option = document.createElement('option');
					option.value = 'ODI';
					option.text = 'ODI';
					select.appendChild(option);
					
					option = document.createElement('option');
					option.value = 'TEST';
					option.text = 'TEST MATCHES';
					select.appendChild(option);
					
					switch(whatToProcess){
					case "Control_e":
						option = document.createElement('option');
						option.value = 'DT20';
						option.text = 'T20';
						select.appendChild(option);
						break;
					case "Alt_4":
						option = document.createElement('option');
						option.value = 'THIS_SERIES';
						option.text = 'THIS SERIES';
						select.appendChild(option);
						break;
					}
					break;
				}
				
				select.setAttribute('onchange',"setDropdownOptionToSelectOptionArray(this, 1)");
				row.insertCell(cellCount).appendChild(select);
				setDropdownOptionToSelectOptionArray($(select),1);
				cellCount = cellCount + 1
				
				switch(whatToProcess){
				case 'Control_e':
					switch($('#selected_broadcaster').val().toUpperCase()){
					case 'TRI_SERIES': case 'MT20': case 'TG20':
						select = document.createElement('select');
						select.id = 'selectImage';
						select.name = select.id;
						
						option = document.createElement('option');
						option.value = 'WITHOUT';
						option.text = 'WithOut Image';
						select.appendChild(option);
												
						option = document.createElement('option');
						option.value = 'WITH';
						option.text = 'With Image';
						select.appendChild(option);
						
						select.setAttribute('onchange',"setDropdownOptionToSelectOptionArray(this, 2)");
						row.insertCell(cellCount).appendChild(select);
						setDropdownOptionToSelectOptionArray($(select),2);
						cellCount = cellCount + 1
						break;
					}
					break;
				}
				break;
			}			
			break;
			
		case 'Control_p': case 'Alt_F7':
			header_text.innerHTML = 'POINTS TABLE';
			
			select = document.createElement('select');
			select.id = 'selectGroup';
			select.name = select.id;
			
			option = document.createElement('option');
			option.value = 'GroupA';
			option.text = 'Group A';
			select.appendChild(option);
			
			option = document.createElement('option');
			option.value = 'GroupB';
			option.text = 'Group B';
			select.appendChild(option);
			
			select.setAttribute('onchange',"setDropdownOptionToSelectOptionArray(this, 0)");
			row.insertCell(cellCount).appendChild(select);
			setDropdownOptionToSelectOptionArray($(select),0);
			cellCount = cellCount + 1;
			break;
			
		case 'Control_F11':
			switch(whatToProcess) {
			case 'Control_F11':
				header_text.innerHTML = 'FF MATCH SUMMARY';
				break;
			}
			select = document.createElement('select');
			select.id = 'selectMatchPhoto';
			select.name = select.id;
			
			option = document.createElement('option');
			option.value = 'WITHOUT_IMAGE';
			option.text = 'Without Image';
			select.appendChild(option);
			
			option = document.createElement('option');
			option.value = 'WITH_IMAGE';
			option.text = 'With Image';
			select.appendChild(option);
			
			select.setAttribute('onchange',"setDropdownOptionToSelectOptionArray(this, 0)");
			row.insertCell(cellCount).appendChild(select);
			setDropdownOptionToSelectOptionArray($(select),0);
			cellCount = cellCount + 1;
			break;	
		case 'Control_m': case 'Shift_F11':
			switch(whatToProcess) {
			case 'Control_m':
				header_text.innerHTML = 'FF MATCH PROMO';
				break;
			case 'Shift_F11':
				header_text.innerHTML = 'FF PREVIOUS MATCH SUMMARY';
				break;
			}
			select = document.createElement('select');
			select.id = 'selectMatchPromo';
			select.name = select.id;
			console.log(Array.isArray(dataToProcess), dataToProcess.length);

			dataToProcess.forEach(function(oop){	
				option = document.createElement('option');
	            option.value = oop.matchnumber;
	            option.text = oop.matchnumber + ' - ' +oop.home_Team.teamName1 + ' Vs ' + oop.away_Team.teamName1 ;
	            select.appendChild(option);
	        });
	        
			select.setAttribute('onchange',"setDropdownOptionToSelectOptionArray(this, 0)");
			row.insertCell(cellCount).appendChild(select);
			setDropdownOptionToSelectOptionArray($(select),0);
			
			cellCount = cellCount + 1;
			
			switch(whatToProcess){
			case 'Shift_F11':
				switch($('#selected_broadcaster').val().toUpperCase()){
				case 'ACC':
					select = document.createElement('select');
					select.id = 'selectImageType';
					select.name = select.id;
					
					option = document.createElement('option');
					option.value = 'WITHOUT_IMAGE';
					option.text = 'Without Image';
					select.appendChild(option);
					
					option = document.createElement('option');
					option.value = 'WITH_IMAGE';
					option.text = 'With Image';
					select.appendChild(option);
					
					select.setAttribute('onchange',"setDropdownOptionToSelectOptionArray(this, 1)");
					row.insertCell(cellCount).appendChild(select);
					setDropdownOptionToSelectOptionArray($(select),1);
					cellCount = cellCount + 1;
					break;	
				}
				break;
			}
			break;
		
		case 'F12':
			switch($('#selected_broadcaster').val().toUpperCase()) {
			case 'BAN_AFG_SERIES': case 'ACC':
				select = document.createElement('select');
				select.id = 'selectMiddle';
				select.name = select.id;
				
				switch($('#selected_broadcaster').val().toUpperCase()) {
				case 'ACC':
					option = document.createElement('option');
					option.value = 'TOURNAMENT';
					option.text = 'TLogo';
					select.appendChild(option);
					break;
				}
				session_match.match.inning.forEach(function(inn){
					if(inn.isCurrentInning == 'YES'){
						if(inn.inningNumber == 1){
							option = document.createElement('option');
							option.value = 'TOSS';
							option.text = 'Toss';
							select.appendChild(option);
						}else{
							option = document.createElement('option');
							option.value = 'TARGET';
							option.text = 'Target';
							select.appendChild(option);
							
							option = document.createElement('option');
							option.value = 'RRR';
							option.text = 'Req. Run Rate';
							select.appendChild(option);
						}
					}
				});
				
				option = document.createElement('option');
				option.value = 'CRR';
				option.text = 'Run Rate';
				select.appendChild(option);
				
				option = document.createElement('option');
				option.value = 'SUPER_OVER';
				option.text = 'SUPER OVER';
				select.appendChild(option);
				
				select.setAttribute('onchange',"setDropdownOptionToSelectOptionArray(this, 0)");
				row.insertCell(cellCount).appendChild(select);
				setDropdownOptionToSelectOptionArray($(select),0);
				cellCount = cellCount + 1;
				
				switch($('#selected_broadcaster').val().toUpperCase()){	
				case 'ACC':
					select = document.createElement('select');
					select.id = 'selectRightBottom';
					select.name = select.id;
					
					option = document.createElement('option');
					option.value = 'BOWLING_END';
					option.text = 'Bowling End';
					select.appendChild(option);
					
					option = document.createElement('option');
					option.value = 'OVER';
					option.text = 'This Over';
					select.appendChild(option);
					
					option = document.createElement('option');
					option.value = 'BOWLER_REPLACE';
					option.text = 'Bowler Replace';
					select.appendChild(option);
					
					option = document.createElement('option');
					option.value = 'EXTRAS';
					option.text = 'Extras';
					select.appendChild(option);
					
					option = document.createElement('option');
					option.value = 'BOWLER_ECONOMY';
					option.text = 'Bowler Economy';
					select.appendChild(option);
					
					select.setAttribute('onchange',"setDropdownOptionToSelectOptionArray(this, 1)");
					row.insertCell(cellCount).appendChild(select);
					setDropdownOptionToSelectOptionArray($(select),1);
					cellCount = cellCount + 1;
					break;
				}
				
				break;
			case 'BCCI': case 'TRI_SERIES': case 'AFG_SL_SERIES': case 'MT20': case 'TG20':
		        select = document.createElement('select');
		        select.id = 'selectLeftBottom';
		        select.name = select.id;
		
		        // helper to add options
		        const addOption = (value, text) => {
		            let option = document.createElement('option');
		            option.value = value;
		            option.text = text;
		            select.appendChild(option);
		        };
		
		        // helper to handle innings-based options
		        const addInningOptions = (inn) => {
		            if (inn.isCurrentInning === 'YES') {
		                if (inn.inningNumber === 1) {
		                    addOption('TOSS', 'Toss');
		                    addOption('CRR', 'Run Rate');
		                } else {
		                    addOption('TARGET', 'Target');
		                    addOption('RRR', 'Required Rate');
		                }
		            }
		        };
		
		        if (session_match.setup.matchType === 'TEST' || session_match.setup.matchType === 'FC') {
		            // Test/FC extras
		            addOption('DAY_SESSION', 'Day Session');
		            addOption('LOCAL-TIME', 'Local Time');
		            addOption('CRR', 'Run Rate');
		            addOption('LUNCH_TEXT', 'Lunch Text');
		            addOption('TEA_TEXT', 'Tea Text');
		
		            session_match.match.inning.forEach(addInningOptions);
		        } else {
		            // Limited overs extras
		            if($('#selected_broadcaster').val().toUpperCase() == 'BCCI'){
			            addOption('SUPER_OVER', 'Super Over');
			            addOption('EXTRAS', 'Extras');
					}else{
						addOption('TEAMNAME', 'TeamName');
			            addOption('VENUE', 'Venue');
			            addOption('SUPER_OVER', 'Super Over');
			            addOption('FIRST_INNING_SCORE', '1st Inning Score');
					}
		            
		            
		            session_match.match.inning.forEach(addInningOptions);
		        }
		
		        select.setAttribute('onchange', "setDropdownOptionToSelectOptionArray(this, 0)");
		        row.insertCell(cellCount).appendChild(select);
		        setDropdownOptionToSelectOptionArray($(select), 0);
		        cellCount++;
		        
		        switch($('#selected_broadcaster').val().toUpperCase()){	
				case 'TRI_SERIES': case 'AFG_SL_SERIES': case 'MT20': case 'TG20':
					select = document.createElement('select');
					select.id = 'selectRightBottom';
					select.name = select.id;
					
					option = document.createElement('option');
					option.value = 'OVER';
					option.text = 'This Over';
					select.appendChild(option);
					
					option = document.createElement('option');
					option.value = 'BOWLING_END';
					option.text = 'Bowling End';
					select.appendChild(option);
					
					option = document.createElement('option');
					option.value = 'BOWLER_REPLACE';
					option.text = 'Bowler Replace';
					select.appendChild(option);
					
					option = document.createElement('option');
					option.value = 'EXTRAS';
					option.text = 'Extras';
					select.appendChild(option);
					
					option = document.createElement('option');
					option.value = 'BOWLER_ECONOMY';
					option.text = 'Bowler Economy';
					select.appendChild(option);
					
					select.setAttribute('onchange',"setDropdownOptionToSelectOptionArray(this, 1)");
					row.insertCell(cellCount).appendChild(select);
					setDropdownOptionToSelectOptionArray($(select),1);
					cellCount = cellCount + 1
					break;
				}
		        break;
			}

			header_text.innerHTML = 'MAIN INFOBAR';
			break;
		
		case 'Alt_1':
			switch($('#selected_broadcaster').val().toUpperCase()){
		    case 'BCCI': case 'TRI_SERIES': case 'AFG_SL_SERIES': case 'MT20': case 'TG20':
		    	header_text.innerHTML = ($('#selected_broadcaster').val().toUpperCase() === 'BCCI') ? 
		    		'LEFT SECTION INFOBAR' : 'INFOBAR - MIDDLE BOTTOM';
		    
		        select = document.createElement('select');
		        select.id = 'selectLeftBottom';
		        select.name = select.id;
		
		        const addOption = (value, text) => {
		            let option = document.createElement('option');
		            option.value = value;
		            option.text = text;
		            select.appendChild(option);
		        };
		
		        if(session_match.setup.matchType === 'TEST' || session_match.setup.matchType === 'FC'){
		            addOption('DAY_SESSION', 'Day Session');
		            addOption('LOCAL-TIME', 'Local Time');
		            addOption('CRR', 'Run Rate');
		            addOption('LUNCH_TEXT', 'Lunch Text');
		            addOption('TEA_TEXT', 'Tea Text');
		
		            session_match.match.inning.forEach(function(inn){
		                if(inn.isCurrentInning === 'YES'){
		                    if(inn.inningNumber === 1){
		                        addOption('TOSS', 'Toss');
		                    } else if(inn.inningNumber === 2){
		                        addOption('FIRST_INNING_SCORE', '1st Inning Score');
		                    } else if(inn.inningNumber === 4){
		                        addOption('TARGET', 'Target');
		                        addOption('RRR', 'Required Rate');
		                    }
		                }
		            });
		
		        } else {
		            addOption('TEAMNAME', 'TeamName');
		            addOption('VENUE', 'Venue');
		            addOption('Ident', 'Ident');
					addOption('CRR', 'Run Rate');
		            
		            session_match.match.inning.forEach(function(inn){
		                if(inn.isCurrentInning === 'YES'){
		                    if(inn.inningNumber === 1){
		                        addOption('TOSS', 'Toss');
		                    } else {
		                        //addOption('FIRST_INNING_SCORE', '1st Inning Score');
		                        //addOption('EQUATION', 'Equation');
		                        addOption('TARGET', 'Target');
		                        addOption('RRR', 'Required Rate');
								addOption('DLS_PAR_SCORE', 'DLS Par Score');
		                    }
		                }
		            });
		            
		            addOption('SUPER_OVER', 'Super Over');
		        }
		
		        select.setAttribute('onchange',"setDropdownOptionToSelectOptionArray(this, 0)");
		        row.insertCell(cellCount).appendChild(select);
		        setDropdownOptionToSelectOptionArray($(select),0);
		        cellCount++;
		        break;
			}
			break;
			
		case 'Alt_2':
			switch($('#selected_broadcaster').val().toUpperCase()){
			case 'BAN_AFG_SERIES': case 'ACC':
				header_text.innerHTML = 'INFOBAR - MIDDLE';
				
				select = document.createElement('select');
				select.id = 'selectMiddleStat';
				select.name = select.id;
				
				option = document.createElement('option');
				option.value = 'LIVE_FROM';
				option.text = 'LIVE FROM';
				select.appendChild(option);
				
				option = document.createElement('option');
				option.value = 'MATCH_IDENT';
				option.text = 'Match Ident';
				select.appendChild(option); 
					
				switch($('#selected_broadcaster').val().toUpperCase()){
				case 'ACC':
					option = document.createElement('option');
					option.value = 'TOURNAMENT';
					option.text = 'TLOGO';
					select.appendChild(option);
					
					option = document.createElement('option');
					option.value = 'LAST_WICKET';
					option.text = 'Last Wicket';
					select.appendChild(option);
					
					option = document.createElement('option');
					option.value = 'BOUNDARY';
					option.text = 'Boundary';
					select.appendChild(option);
					
					option = document.createElement('option');
					option.value = 'EXTRAS';
					option.text = 'Extras';
					select.appendChild(option);
					
					session_match.match.inning.forEach(function(inn){
					if(inn.isCurrentInning == 'YES'){
						if(inn.inningNumber == 1){
							
							option = document.createElement('option');
							option.value = 'PROJECTED';
							option.text = 'Projected';
							select.appendChild(option);
						}else{
							
							option = document.createElement('option');
							option.value = 'EQUATION';
							option.text = 'Equation';
							select.appendChild(option);
							
							option = document.createElement('option');
							option.value = 'COMPARE';
							option.text = 'Comparison';
							select.appendChild(option);
						}
					}
				});
					break;
				}
				
				option = document.createElement('option');
				option.value = 'CRR';
				option.text = 'Run Rate';
				select.appendChild(option);
				
				option = document.createElement('option');
				option.value = 'CURR_PARTNERSHIP';
				option.text = 'Current Partnership';
				select.appendChild(option);
				
				session_match.match.inning.forEach(function(inn){
					if(inn.isCurrentInning == 'YES'){
						if(inn.inningNumber == 1){
							option = document.createElement('option');
							option.value = 'TOSS';
							option.text = 'Toss';
							select.appendChild(option);
							
							/*option = document.createElement('option');
							option.value = 'PROJECTED';
							option.text = 'Projected';
							select.appendChild(option);*/
						}else{
							option = document.createElement('option');
							option.value = 'TARGET';
							option.text = 'Target';
							select.appendChild(option);
							
							option = document.createElement('option');
							option.value = 'RRR';
							option.text = 'Req. Run Rate';
							select.appendChild(option);
							
						}
					}
				});
				
				option = document.createElement('option');
				option.value = 'SUPER_OVER';
				option.text = 'SUPER OVER';
				select.appendChild(option);
							
				break;
			case 'TRI_SERIES': case 'AFG_SL_SERIES': case 'MT20': case 'TG20':
				header_text.innerHTML = 'INFOBAR - LEFT BATTER EXTRA';
				
				select = document.createElement('select');
				select.id = 'selectMiddleStat';
				select.name = select.id;
				
				option = document.createElement('option');
				option.value = 'BLANK';
				option.text = 'Blank';
				select.appendChild(option);
	
				option = document.createElement('option');
				option.value = 'CURR_PARTNERSHIP';
				option.text = 'Current Partnership';
				select.appendChild(option);
				
				option = document.createElement('option');
				option.value = 'CRR';
				option.text = 'Run Rate';
				select.appendChild(option);
				
				session_match.match.inning.forEach(function(inn){
					if(inn.isCurrentInning == 'YES'){
						if(inn.inningNumber == 1){
							option = document.createElement('option');
							option.value = 'PROJECTED_SCORE';
							option.text = 'Projected Score';
							select.appendChild(option);
						}else{
							option = document.createElement('option');
							option.value = 'RRR';
							option.text = 'Req. Run Rate';
							select.appendChild(option);
						}
					}
				});
				break;
			case 'BCCI':
				header_text.innerHTML = 'MIDDLE INFOBAR SECTION';
			
				select = document.createElement('select');
				select.id = 'selectMiddleStat';
				select.name = select.id;
				
				option = document.createElement('option');
				option.value = 'BLANK';
				option.text = 'Blank';
				select.appendChild(option);
	
				option = document.createElement('option');
				option.value = 'CURR_PARTNERSHIP';
				option.text = 'Current Partnership';
				select.appendChild(option);
				
				option = document.createElement('option');
				option.value = 'EXTRAS';
				option.text = 'Extras';
				select.appendChild(option);
				
				option = document.createElement('option');
				option.value = 'BOUNDARY';
				option.text = 'Innings Boundaries';
				select.appendChild(option);
				
				option = document.createElement('option');
				option.value = 'REVIEWS_REMAINING';
				option.text = 'Review';
				select.appendChild(option);
				
				option = document.createElement('option');
				option.value = 'CRR';
				option.text = 'Run Rate';
				select.appendChild(option);
				
				if(session_match.setup.matchType == 'TEST' || session_match.setup.matchType == 'FC'){
					session_match.match.inning.forEach(function(inn){
						if(inn.isCurrentInning == 'YES'){
							if(inn.inningNumber == 1){
							
							}else if(inn.inningNumber == 4){
								option = document.createElement('option');
								option.value = 'EQUATION';
								option.text = 'Equation';
								select.appendChild(option);
							}else{
								option = document.createElement('option');
								option.value = 'BOTH_TEAMS_SCORE';
								option.text = 'Both Teams Score';
								select.appendChild(option);
									
								option = document.createElement('option');
								option.value = 'LEAD_TRAIL_EQUATION';
								option.text = 'Lead Trail Equation';
								select.appendChild(option);
							}
						}
					});
				}
				break;	
			}
			row.insertCell(cellCount).appendChild(select);
			select.setAttribute('onchange',"setDropdownOptionToSelectOptionArray(this, 0)");
			setDropdownOptionToSelectOptionArray($(select),0);
			cellCount = cellCount + 1;
			
			switch($('#selected_broadcaster').val().toUpperCase()){
			case 'BCCI':
				select.addEventListener('change', function () {
					if (document.getElementById('selectFreeText')){
							document.getElementById('selectFreeText').parentElement.remove();
					} 
					//it will show text value (UI VALUE):this.options[this.selectedIndex].text.toUpperCase()
					if(this.value == 'CRR'){
						let label = document.createElement('label');
					    label.setAttribute('for', 'selectFreeText'); 
					    label.innerHTML = 'BALLS : ';	
					    					    	    			
						let xballselect = document.createElement('input');
						xballselect.type = "text";
						xballselect.id = 'selectFreeText';
						xballselect.value = '20';
						
						xballselect.setAttribute('onchange',"setTextBoxOptionToSelectOptionArray(1)");
						let cell = row.insertCell(1);
						cell.style.cssText = "display: flex; align-items: center; text-align: center; vertical-align: middle; gap: 5px;";
					    cell.appendChild(label); 
					    cell.appendChild(document.createTextNode(' '));
					    cell.appendChild(xballselect); 	
					    setTextBoxOptionToSelectOptionArray(1);
				   		cellCount = 2;
					}
				});
				select.dispatchEvent(new Event('change'));
				break;
			}
			break;
		case 'Alt_6':
			switch($('#selected_broadcaster').val().toUpperCase()){	
			case 'TRI_SERIES': case 'MT20': case 'TG20':
				header_text.innerHTML = 'INFOBAR - RIGHT BOWLER EXTRA';
				
				select = document.createElement('select');
				select.id = 'selectMostRight';
				select.name = select.id;
				
				option = document.createElement('option');
				option.value = 'BLANK';
				option.text = 'Blank';
				select.appendChild(option);
				
				/*option = document.createElement('option');
				option.value = 'WEATHER';
				option.text = 'Weather';
				select.appendChild(option);*/
				
				session_match.match.inning.forEach(function(inn){
					if(inn.isCurrentInning == 'YES'){
						if(inn.inningNumber == 1){
							option = document.createElement('option');
							option.value = 'BOWLER_ECONOMY';
							option.text = 'Bowler Economy';
							select.appendChild(option);
						}else{
							option = document.createElement('option');
							option.value = 'TARGET';
							option.text = 'Target';
							select.appendChild(option);
							
							option = document.createElement('option');
							option.value = 'BOWLER_ECONOMY';
							option.text = 'Bowler Economy';
							select.appendChild(option);
						}
					}
				});
				break;	
			}
				
			select.setAttribute('onchange',"setDropdownOptionToSelectOptionArray(this, 0)");
			row.insertCell(cellCount).appendChild(select);
			setDropdownOptionToSelectOptionArray($(select),0);
			cellCount = cellCount + 1
			break;
		case 'Alt_7':
			header_text.innerHTML = 'INFOBAR - RIGHT BOTTOM';
			
			switch($('#selected_broadcaster').val().toUpperCase()){	
			case 'BCCI': case 'TRI_SERIES': case 'ACC': case 'AFG_SL_SERIES': case 'MT20': case 'TG20':
				select = document.createElement('select');
				select.id = 'selectRightBottom';
				select.name = select.id;
				
				option = document.createElement('option');
				option.value = 'OVER';
				option.text = 'This Over';
				select.appendChild(option);
				
				option = document.createElement('option');
				option.value = 'BOWLING_END';
				option.text = 'Bowling End';
				select.appendChild(option);
				
				option = document.createElement('option');
				option.value = 'BOWLER_REPLACE';
				option.text = 'Bowler Replace';
				select.appendChild(option);
				
				switch($('#selected_broadcaster').val().toUpperCase()){
				case 'TRI_SERIES': case 'AFG_SL_SERIES': case 'MT20': case 'TG20':
					option = document.createElement('option');
					option.value = 'EXTRAS';
					option.text = 'Extras';
					select.appendChild(option);
					
					option = document.createElement('option');
					option.value = 'BOWLER_ECONOMY';
					option.text = 'Bowler Economy';
					select.appendChild(option);
					break;
				case 'ACC':
					/*option = document.createElement('option');
					option.value = 'EXTRAS';
					option.text = 'Extras';
					select.appendChild(option);*/
					
					option = document.createElement('option');
					option.value = 'BOWLER_ECONOMY';
					option.text = 'Bowler Economy';
					select.appendChild(option);
					
					/*option = document.createElement('option');
					option.value = 'BOUNDARY';
					option.text = 'Boundary';
					select.appendChild(option);
					
					option = document.createElement('option');
					option.value = '0';
					option.text = 'Dot This Inning';
					select.appendChild(option);
					
					option = document.createElement('option');
					option.value = '4';
					option.text = 'Fours This Inning';
					select.appendChild(option);
					
					option = document.createElement('option');
					option.value = '6';
					option.text = 'Sixes This Inning';
					select.appendChild(option);
					
					option = document.createElement('option');
					option.value = 'LastXBalls';
					option.text = 'LastXBalls';
					select.appendChild(option);
					
					option = document.createElement('option');
					option.value = 'LAST_WICKET';
					option.text = 'Last Wicket';
					select.appendChild(option);
					
					option = document.createElement('option');
					option.value = 'REVIEWS_REMAINING';
					option.text = 'Review Rem';
					select.appendChild(option);
					
					option = document.createElement('option');
					option.value = 'BALLS_SINCE_LAST_BOUNDARY';
					option.text = 'Balls Since Last Boundary';
					select.appendChild(option);*/
					
					session_match.match.inning.forEach(function(inn){
						if(inn.isCurrentInning == 'YES' && inn.inningNumber == 2){
							option = document.createElement('option');
							option.value = 'COMPARE';
							option.text = 'At This Stage';
							select.appendChild(option);
							
							option = document.createElement('option');
							option.value = 'EQUATION';
							option.text = 'Equation';
							select.appendChild(option);
							
							option = document.createElement('option');
							option.value = 'PAR_SCORE';
							option.text = 'Par Score';
							select.appendChild(option);
							
						}
					});
					break;	
				}
				break;	
			}
				
			select.setAttribute('onchange',"setDropdownOptionToSelectOptionArray(this, 0)");
			row.insertCell(cellCount).appendChild(select);
			setDropdownOptionToSelectOptionArray($(select),0);
			cellCount = cellCount + 1
			
			select.addEventListener('change', function () {
				if(this.value == 'LastXBalls'){
					let label = document.createElement('label');
				    label.setAttribute('for', 'selectFreeText'); 
				    label.innerHTML = 'BALLS : ';	
				    					    	    			
					let xballselect = document.createElement('input');
					xballselect.type = "text";
					xballselect.id = 'selectFreeText';
					xballselect.value = '10';
					
					xballselect.setAttribute('onchange',"setTextBoxOptionToSelectOptionArray(1)");
					let cell = row.insertCell(1);
					cell.style.cssText = "display: flex; align-items: center; text-align: center; vertical-align: middle; gap: 5px;";
				    cell.appendChild(label); 
				    cell.appendChild(document.createTextNode(' '));
				    cell.appendChild(xballselect); 	
				    setTextBoxOptionToSelectOptionArray(1);
			   		cellCount = 2;
				}
			});
			select.dispatchEvent(new Event('change'));
			break;
		case "Alt_5":
			header_text.innerHTML = 'INFOBAR - RIGHT FULL';
			select = document.createElement('select');
			select.id = 'selectRightSection';
			select.name = select.id;
			switch($('#selected_broadcaster').val().toUpperCase()){
			case 'BCCI': case 'TRI_SERIES': case 'BAN_AFG_SERIES': case 'ACC': case 'MT20': case 'TG20':
				switch($('#selected_broadcaster').val().toUpperCase()){
					case 'BAN_AFG_SERIES': case 'ACC':
						const dropdown = [
						  { value: 'BLANK', text: 'Blank' },
						  { value: 'BOUNDARY', text: 'Boundaries' },
						  { value: 'LastXBalls', text: 'Last X Balls' },
						  { value: 'EXTRAS', text: 'Extras' },
						  { value: 'LAST_WICKET', text: 'Last Wicket' },
						  { value: 'REVIEWS_REMAINING', text: 'Review'},
						  { value: 'BALLS_SINCE_LAST_BOUNDARY', text: 'Balls Since Last Boundaries'}
						];
						
						switch($('#selected_broadcaster').val().toUpperCase()){
						case 'BCCI': case 'TRI_SERIES': case 'BAN_AFG_SERIES': case 'MT20': case 'TG20':
							dropdown.push(
							  { value: 'OVER', text: 'This Over' },
							  { value: 'ECONOMY', text: 'Economy' },
							  { value: 'BOWLINGEND', text: 'Bowling End' }
							);
							
							session_match.match.inning.forEach(function(inn){
								if(inn.isCurrentInning == 'YES' && inn.inningNumber == 2){
									dropdown.push(
									    { value: 'COMPARE', text: 'At This Stage' },
									    { value: 'EQUATION', text: 'Equation' },
									    { value: 'PAR_SCORE', text: 'DLS PAR SCORE' }
									);
								}else if(inn.isCurrentInning == 'YES' && inn.inningNumber == 1){
									dropdown.push(
									    { value: 'PROJECTED', text: 'Projected Score' }
									);
								}
							});
							break;
						}
						
						dropdown.push(
						  { value: '0', text: 'Dot Balls' },
						  { value: '4', text: 'Fours This Inning' },
						  { value: '6', text: 'Sixes This Inning' },
						  { value: 'THIS_MATCH_FOURS', text: 'Fours This Match' },
						  { value: 'THIS_MATCH_SIXES', text: 'Sixes This Match' },
						  { value: 'TOURNAMENT_FOURS', text: 'Tournament Fours' },
						  { value: 'TOURNAMENT_SIXES', text: 'Tournament Sixes' }
						);
					
						dropdown.forEach(({ value, text }) => {
						  const option = document.createElement('option');
						  option.value = value;
						  option.text = text;
						  select.appendChild(option);
						});
						break;
					case 'TRI_SERIES': case 'MT20': case 'TG20':
						const options = [
						  { value: 'BLANK', text: 'Blank' },
						  { value: 'CURR_PARTNERSHIP', text: 'Current Partnership' },
						  { value: 'LastXBalls', text: 'Last X Balls' },
						  { value: 'BOUNDARY', text: 'Boundaries' },
						  { value: 'EXTRAS', text: 'Extras' },
						  { value: 'LAST_WICKET', text: 'Last Wicket' },
						  { value: 'REVIEWS_REMAINING', text: 'Review'},
						  { value: 'TEAMS_STANDINGS', text: 'Standing'},
						  { value: 'BALLS_SINCE_LAST_BOUNDARY', text: 'Balls Since Last Boundaries' }
						];
						
						session_match.match.inning.forEach(function(inn){
							if(inn.isCurrentInning == 'YES' && inn.inningNumber == 2){
								options.push(
								    { value: 'RUN_RATES', text: 'CRR & RRR' },
								    { value: 'COMPARE', text: 'At This Stage' },
								    { value: 'EQUATION', text: 'Equation' }
								);
							}
						});
					
						options.forEach(({ value, text }) => {
						  const option = document.createElement('option');
						  option.value = value;
						  option.text = text;
						  select.appendChild(option);
						});
						break;
					case 'BCCI':
						[{ value: 'BLANK', text: 'Blank' },
						 { value: 'LAST_WICKET', text: 'LAST WICKET' }
						].forEach(({ value, text }) => {
							  option = document.createElement('option');
							  option.value = value;
							  option.text = text;
							  select.appendChild(option);
						});	
						break;
				}
			
				select.setAttribute('onchange',"setDropdownOptionToSelectOptionArray(this, 0)");
				row.insertCell(cellCount).appendChild(select);
				setDropdownOptionToSelectOptionArray($(select),0);
				cellCount = cellCount + 1
				
				select.addEventListener('change', function () {
				['selectFreeText', 'selectPhoto'].forEach(id => {
					    const el = document.getElementById(id);
					    if (el) {
					        id === 'selectFreeText' ? el.parentElement.remove() : el.remove();
					    }
					});
					
				if(this.value == 'LAST_WICKET' || this.value == 'CURR_PARTNERSHIP'){
					let ballselect  = document.createElement('select');
					ballselect.id = 'selectPhoto';
					ballselect.name = ballselect.id;
					[{ value: 'WithPhoto', text: 'With Photo' },
					{ value: 'WithoutPhoto', text: 'Without Photo' }
						].forEach(({ value, text }) => {
							  option = document.createElement('option');
							  option.value = value;
							  option.text = text;
							  ballselect.appendChild(option);
						});
					ballselect.setAttribute('onchange',"setDropdownOptionToSelectOptionArray(this, 1)");
					row.insertCell(1).appendChild(ballselect);
					setDropdownOptionToSelectOptionArray($(ballselect),1);
					cellCount = 2;
				}else if(this.value == 'LastXBalls'){
					let label = document.createElement('label');
				    label.setAttribute('for', 'selectFreeText'); 
				    label.innerHTML = 'BALLS : ';	
				    					    	    			
					let xballselect = document.createElement('input');
					xballselect.type = "text";
					xballselect.id = 'selectFreeText';
					xballselect.value = '10';
					
					xballselect.setAttribute('onchange',"setTextBoxOptionToSelectOptionArray(1)");
					let cell = row.insertCell(1);
					cell.style.cssText = "display: flex; align-items: center; text-align: center; vertical-align: middle; gap: 5px;";
				    cell.appendChild(label); 
				    cell.appendChild(document.createTextNode(' '));
				    cell.appendChild(xballselect); 	
				    setTextBoxOptionToSelectOptionArray(1);
			   		cellCount = 2;
				}
			});
			select.dispatchEvent(new Event('change'));	
			break;
		  }
		  break;
		case 'Alt_8':
			header_text.innerHTML = 'INFOBAR - FULL';
			select = document.createElement('select');
			select.id = 'selectFullSection';
			select.name = select.id;
			
			switch($('#selected_broadcaster').val().toUpperCase()){	
			case 'BCCI': case 'TRI_SERIES': case 'BAN_AFG_SERIES': case 'ACC': case 'MT20': case 'TG20':
				switch($('#selected_broadcaster').val().toUpperCase()){
				case 'BAN_AFG_SERIES': 
					const dropdown = [
					  { value: 'BLANK', text: 'Blank' },
					  { value: 'IDENT', text: 'Ident' },
					  { value: 'Commentators', text: 'Commentators' },
  					  { value: 'FreeTextDb', text: 'FreeText DB' },
  					  { value: 'TIMELINE', text: 'TimeLine' },
					  { value: 'BatMileStone', text: 'Batter MileStone' },
					  { value: 'BallMileStone', text: 'Bowler MileStone' },
					  //{ value: 'FreeText', text: 'FreeText' },
					  //{ value: 'Sponsor', text: 'Sponsor' },
					];
					
					session_match.match.inning.forEach(function(inn){
						if(inn.isCurrentInning == 'YES' && inn.inningNumber == 2){
							dropdown.push(
							    { value: 'BIG_EQUATION', text: 'Big Equation'}
							);
						}
					});
					
					dropdown.forEach(({ value, text }) => {
					  const option = document.createElement('option');
					  option.value = value;
					  option.text = text;
					  select.appendChild(option);
					});
					break;
				 case 'ACC':
					const dropdown1 = [
					  { value: 'BLANK', text: 'Blank' },
					  { value: 'FreeText', text: 'FreeText' },
					  { value: 'Commentators', text: 'Commentators' },
					  { value: 'FreeTextDb', text: 'FreeText DB' },
					  { value: 'TIMELINE', text: 'TimeLine' },
					  { value: 'Sponsor', text: 'Sponsor' },
					  { value: 'BatMileStone', text: 'Batter MileStone' },
					  { value: 'BallMileStone', text: 'Bowler MileStone' },
					  { value: 'PHASE_WISE_SCORE', text: 'PhaseWise Score' },
					  { value: 'DLS', text: 'DLS' },
					  //{ value: 'IDENT', text: 'Ident' },
					];
					
					session_match.match.inning.forEach(function(inn){
						if(inn.isCurrentInning == 'YES' && inn.inningNumber == 2){
							dropdown1.push(
							    { value: 'BIG_EQUATION', text: 'Big Equation'}
							);
						}if(inn.isCurrentInning == 'YES' && inn.inningNumber == 1){
							dropdown1.push(
							    { value: 'PROJECTED', text: 'projected'}
							);
						}
					});
					
					dropdown1.forEach(({ value, text }) => {
					  const option = document.createElement('option');
					  option.value = value;
					  option.text = text;
					  select.appendChild(option);
					});
					break;
				case 'TRI_SERIES': case 'MT20': case 'TG20':
					const options = [
					  { value: 'BLANK', text: 'Blank' },
					  //{ value: 'FreeText', text: 'FreeText' },
					  { value: 'IDENT', text: 'Ident' },
					  { value: 'EXTRAS', text: 'Extras' },
					  { value: 'Commentators', text: 'Commentators' },
					  { value: 'FreeTextDb', text: 'FreeText DB' },
					  //{ value: 'Sponsor', text: 'Sponsor' },
					  { value: 'TIMELINE', text: 'TimeLine' },
					  { value: 'PHASE_WISE_SCORE', text: 'PhaseWise Score' },
					  { value: 'PHASE_WISE_RUNRATE', text: 'PhaseWise Run-Rates' },
					  { value: 'BatsmanTimeLine', text: 'Batter TimeLine' },
					  { value: 'BatMileStone', text: 'Batter MileStone' },
					  { value: 'BallMileStone', text: 'Bowler MileStone' },
					];
					
					session_match.match.inning.forEach(function(inn){
						if(inn.isCurrentInning == 'YES' && inn.inningNumber == 2){
							options.push(
							    { value: 'EQUATION', text: 'Equation' },
							    { value: 'BIG_EQUATION', text: 'Big Equation'}
							);
						}else if(inn.isCurrentInning == 'YES' && inn.inningNumber == 1){
							 options.push(
							    { value: 'PROJECTED_SCORE', text: 'Projected Score' }
							);
						}
					});
				
					options.forEach(({ value, text }) => {
					  const option = document.createElement('option');
					  option.value = value;
					  option.text = text;
					  select.appendChild(option);
					});
					break;
				case 'BCCI':
					[{ value: 'BLANK', text: 'Blank' },
					 { value: 'NEXTTOBAT', text: 'Next To Bat' },
					 { value: 'LastXBalls', text: 'Last X Balls'},
					 //{ value: 'Projected_Score', text: 'Projected Score'},
					 { value: 'This_speed', text: 'This Over Speed'},
					 { value: 'Timeline', text: 'Timeline'},
					 { value: 'Session_Summary', text: 'Session Summary'},
					 { value: 'AllSession_Summary', text: 'Day All Session Summary'},
					 { value: 'BatsmanTimeLine', text: 'Batsman TimeLine'},
					 { value: 'Commentators', text: 'Commentators'}
					].forEach(({ value, text }) => {
						  option = document.createElement('option');
						  option.value = value;
						  option.text = text;
						  select.appendChild(option);
					});
					break;
				}
								
				select.setAttribute('onchange',"setDropdownOptionToSelectOptionArray(this, 0)");
				row.insertCell(cellCount).appendChild(select);
				setDropdownOptionToSelectOptionArray($(select),0);
				cellCount = cellCount + 1
				
				select.addEventListener('change', function () {
					['selectFreeText', 'selectFreeText1', 'Player1', 'Player2', 'Player3', 'selectPhoto', 'FreeText', 'SponsorValue'].forEach(id => {
					    const el = document.getElementById(id);
					    if (el) {
					        id === 'selectFreeText' ? el.parentElement.remove() : el.remove();
					    }
					});
 
					//it will show text value (UI VALUE):this.options[this.selectedIndex].text.toUpperCase()
					if(this.value == 'LastXBalls'){
						let label = document.createElement('label');
					    label.setAttribute('for', 'selectFreeText'); 
					    label.innerHTML = 'BALLS : ';	
					    					    	    			
						let xballselect = document.createElement('input');
						xballselect.type = "text";
						xballselect.id = 'selectFreeText';
						xballselect.value = '10';
						
						xballselect.setAttribute('onchange',"setTextBoxOptionToSelectOptionArray(1)");
						let cell = row.insertCell(1);
						cell.style.cssText = "display: flex; align-items: center; text-align: center; vertical-align: middle; gap: 5px;";
					    cell.appendChild(label); 
					    cell.appendChild(document.createTextNode(' '));
					    cell.appendChild(xballselect); 	
					    setTextBoxOptionToSelectOptionArray(1);
				   		cellCount = 2;
					}else if(this.value == 'FreeText'){
						let label1 = document.createElement('label');
					    label1.setAttribute('for', 'selectFreeText'); 
					    label1.innerHTML = 'Line 1';	
					    					    	    			
						let ftheader1 = document.createElement('input');
						ftheader1.type = "text";
						ftheader1.id = 'selectFreeText';
						ftheader1.value = '';
						
						ftheader1.setAttribute('onchange',"setTextBoxOptionToSelectOptionArray(1)");
						row.insertCell(1).appendChild(label1).appendChild(ftheader1);
					    setTextBoxOptionToSelectOptionArray(1);
					    
					    let label2 = document.createElement('label');
					    label2.setAttribute('for', 'selectFreeText1'); 
					    label2.innerHTML = 'Line 2';	
					    					    	    			
						let ftheader2 = document.createElement('input');
						ftheader2.type = "text";
						ftheader2.id = 'selectFreeText1';
						ftheader2.value = '';
						
						ftheader2.setAttribute('onchange',"setTextBoxOptionToSelectOptionArray1(2)");
						row.insertCell(2).appendChild(label2).appendChild(ftheader2);
					    setTextBoxOptionToSelectOptionArray1(2);
				   		cellCount = 3;
					}
					else if(this.value == 'Commentators'){
						row.insertCell(1).id = 'Player1';
			 			row.insertCell(2).id = 'Player2';
			 			row.insertCell(3).id = 'Player3';
			 			cellCount = 4;
						processCricketProcedures("GRAPHICS-OPTIONS_DATA", whatToProcess + "," +
	    				(this.value || $(this).find('option').first().val()));
					}else if(this.value == 'FreeTextDb'){
						row.insertCell(1).id = 'FreeText';
			 			cellCount = 2;
						processCricketProcedures("GRAPHICS-OPTIONS_DATA", whatToProcess + "," +
	    				(this.value || $(this).find('option').first().val()));
					}else if(this.value == 'Sponsor'){
						row.insertCell(1).id = 'SponsorValue';
			 			cellCount = 2;
						processCricketProcedures("GRAPHICS-OPTIONS_DATA", whatToProcess + "," +
	    				(this.value || $(this).find('option').first().val()));
					}else if(this.value == 'AllSession_Summary'){
						if (session_match.match.daysSessions && session_match.match.daysSessions.length > 0) {
							const lastDaySession = session_match.match.daysSessions[session_match.match.daysSessions.length - 1].dayNumber;
					
							let xballselect  = document.createElement('select');
							xballselect.id = 'selectFreeText';
							xballselect.name = xballselect.id;
					
							session_match.match.daysSessions.forEach(function(comm) {
								if(comm.dayNumber == lastDaySession){
									option = document.createElement('option');
									option.value = comm.sessionNumber;
									option.text = "SESSION " + comm.sessionNumber;
									xballselect.appendChild(option);
								}
							});
					
							xballselect.setAttribute('onchange',"setDropdownOptionToSelectOptionArray(this, 1)");
							row.insertCell(1).appendChild(xballselect);
							setDropdownOptionToSelectOptionArray($(xballselect),1);
							cellCount = 2;
						}
					} else if(this.value == 'BatMileStone' || this.value == 'BallMileStone'){
						let xballselect  = document.createElement('select');
						xballselect.id = 'selectFreeText';
						xballselect.name = xballselect.id;
						
						let selectedValue = this.value; 
						
						session_match.match.inning.forEach(function(inn){
						if(inn.isCurrentInning == 'YES'){
								if(selectedValue == 'BatMileStone'){
									inn.battingCard.forEach(function(bc){
										if(bc.status == 'NOT OUT'){
											option = document.createElement('option');
											option.value = bc.playerId;
											option.text = bc.player.full_name + " - " + bc.status;	
											xballselect.appendChild(option);	
										}
									});
								}else{
									inn.bowlingCard.forEach(function(boc){
										option = document.createElement('option');
										option.value = boc.playerId;
										option.text = boc.player.full_name;	
										xballselect.appendChild(option);
									});
								}
							}
						});
						xballselect.setAttribute('onchange',"setDropdownOptionToSelectOptionArray(this, 1)");
						row.insertCell(1).appendChild(xballselect);
						setDropdownOptionToSelectOptionArray($(xballselect),1);

						let ballselect  = document.createElement('select');
						ballselect.id = 'selectPhoto';
						ballselect.name = ballselect.id;
						if(this.value == 'BatMileStone'){
							[{ value: 'Runs', text: 'Runs' },{ value: '50', text: '50s' },
							{ value: '100', text: '100s' }].forEach(({ value, text }) => {
								  option = document.createElement('option');
								  option.value = value;
								  option.text = text;
								  ballselect.appendChild(option);
							});
						}else{
							[{ value: 'Wickets', text: 'Wickets' },{ value: '3WI', text: '3WI' },
							{ value: '5WI', text: '5WI' }].forEach(({ value, text }) => {
								  option = document.createElement('option');
								  option.value = value;
								  option.text = text;
								  ballselect.appendChild(option);
							});
						}
						ballselect.setAttribute('onchange',"setDropdownOptionToSelectOptionArray(this, 2)");
						row.insertCell(2).appendChild(ballselect);
						setDropdownOptionToSelectOptionArray($(ballselect),2);
						cellCount = 3;
					}else if(this.value == 'BatsmanTimeLine'){
						let xballselect  = document.createElement('select');
						xballselect.id = 'selectFreeText';
						xballselect.name = xballselect.id;
						session_match.match.inning.forEach(function(inn){
						if(inn.isCurrentInning == 'YES'){
								inn.battingCard.forEach(function(bc){
									if(bc.status == 'NOT OUT'){
										option = document.createElement('option');
										option.value = bc.playerId;
										option.text = bc.player.full_name + " - " + bc.status;	
										xballselect.appendChild(option);	
									}
								});
							}
						});
						xballselect.setAttribute('onchange',"setDropdownOptionToSelectOptionArray(this, 1)");
						row.insertCell(1).appendChild(xballselect);
						setDropdownOptionToSelectOptionArray($(xballselect),1);

						let ballselect  = document.createElement('select');
						ballselect.id = 'selectPhoto';
						ballselect.name = ballselect.id;
						[{ value: 'WithoutPhoto', text: 'Without Photo' },
						 { value: 'WithPhoto', text: 'With Photo' }
							].forEach(({ value, text }) => {
								  option = document.createElement('option');
								  option.value = value;
								  option.text = text;
								  ballselect.appendChild(option);
							});
						ballselect.setAttribute('onchange',"setDropdownOptionToSelectOptionArray(this, 2)");
						row.insertCell(2).appendChild(ballselect);
						setDropdownOptionToSelectOptionArray($(ballselect),2);
						cellCount = 3;
					}else if(this.value == 'This_speed'){
						let ballselect  = document.createElement('select');
						ballselect.id = 'selectPhoto';
						ballselect.name = ballselect.id;
						[{ value: 'WithoutPhoto', text: 'Without Photo' },
						 { value: 'WithPhoto', text: 'With Photo' }
							].forEach(({ value, text }) => {
								  option = document.createElement('option');
								  option.value = value;
								  option.text = text;
								  ballselect.appendChild(option);
							});
						ballselect.setAttribute('onchange',"setDropdownOptionToSelectOptionArray(this, 1)");
						row.insertCell(1).appendChild(ballselect);
						setDropdownOptionToSelectOptionArray($(ballselect),1);
						cellCount = 2;
					}
				});
				select.dispatchEvent(new Event('change'));	
				break;
			}
			break;
			case 'Control_F9'://BowlerStyle
				header_text.innerHTML = 'BALL STYLE';
				select = document.createElement('select');
				select.id = 'selectPlayerName';
				select.name = select.id;
				
				session_match.match.inning.forEach(function(inn){
					if(inn.inningNumber == document.getElementById('which_inning').value){
						if(inn.bowlingCard != null){
							inn.bowlingCard.forEach(function(boc){
								if(boc.status != null){
									if(boc.status == 'CURRENTBOWLER'){
										option = document.createElement('option');
										option.value = boc.player.playerId;
										option.text = boc.player.full_name;
										select.appendChild(option);
									}
								}
							});
						}
						if(inn.bowlingTeamId == session_match.setup.homeTeamId){
							session_match.setup.homeSquad.forEach(function(hs){
								option = document.createElement('option');
								option.value = hs.playerId;
								option.text = hs.full_name;
								select.appendChild(option);
							});
							session_match.setup.homeOtherSquad.forEach(function(hos){
								option = document.createElement('option');
								option.value = hos.playerId;
								option.text = hos.full_name  + ' (OTHER)';
								select.appendChild(option);
							});
						}else {
							session_match.setup.awaySquad.forEach(function(as){
								option = document.createElement('option');
								option.value = as.playerId;
								option.text = as.full_name;
								select.appendChild(option);
							});
							session_match.setup.awayOtherSquad.forEach(function(aos){
								option = document.createElement('option');
								option.value = aos.playerId;
								option.text = aos.full_name  + ' (OTHER)';
								select.appendChild(option);
							});
						}
					}
				});
	
				select.setAttribute('onchange',"setDropdownOptionToSelectOptionArray(this, 0)");
				row.insertCell(cellCount).appendChild(select);
				removeSelectDuplicates(select.id);
				setDropdownOptionToSelectOptionArray($(select),0);
				cellCount = cellCount + 1;
				
				switch(whatToProcess) {
				case 'Control_F9': case 'Control_Shift_F9':
					select = document.createElement('select');
					select.id = 'selectBowlingEnd';
					select.name = select.id;
					
					option = document.createElement('option');
					option.value = 'WITHOUTEND';
					option.text = 'WITHOUT END';
					select.appendChild(option);
					
					option = document.createElement('option');
					option.value = session_match.setup.ground.first_bowling_end;
					option.text = session_match.setup.ground.first_bowling_end;
					select.appendChild(option);
					
					option = document.createElement('option');
					option.value = session_match.setup.ground.second_bowling_end;
					option.text = session_match.setup.ground.second_bowling_end;
					select.appendChild(option);
					
					select.setAttribute('onchange',"setDropdownOptionToSelectOptionArray(this, 1)");
					row.insertCell(cellCount).appendChild(select);
					setDropdownOptionToSelectOptionArray($(select),1);
					cellCount = cellCount + 1
					break;			
				}
				
				break;
			case 'Control_F5': 
				header_text.innerHTML = 'BAT STYLE';
				select = document.createElement('select');
				select.id = 'selectPlayerName';
				select.name = select.id;
				
				session_match.match.inning.forEach(function(inn){
					if(inn.inningNumber == document.getElementById('which_inning').value){
						inn.battingCard.forEach(function(bc){
							if(bc.status != null){
								if(bc.status == 'NOT OUT'){
									if(bc.onStrike == 'YES'){
										option = document.createElement('option');
										option.value = bc.player.playerId;
										option.text = bc.player.full_name;
										select.appendChild(option);
									}else{
										option = document.createElement('option');
										option.value = bc.player.playerId;
										option.text = bc.player.full_name;
										select.appendChild(option);
									}
								}
							}
						});
						
						if(inn.battingTeamId == session_match.setup.homeTeamId){
							session_match.setup.homeSquad.forEach(function(hs){
								option = document.createElement('option');
								option.value = hs.playerId;
								option.text = hs.full_name;
								select.appendChild(option);
							});
							session_match.setup.homeOtherSquad.forEach(function(hos){
								option = document.createElement('option');
								option.value = hos.playerId;
								option.text = hos.full_name  + ' (OTHER)';
								select.appendChild(option);
							});
						}else {
							session_match.setup.awaySquad.forEach(function(as){
								option = document.createElement('option');
								option.value = as.playerId;
								option.text = as.full_name;
								select.appendChild(option);
							});
							session_match.setup.awayOtherSquad.forEach(function(aos){
								option = document.createElement('option');
								option.value = aos.playerId;
								option.text = aos.full_name  + ' (OTHER)';
								select.appendChild(option);
							});
						}
					}
				});
		
				select.setAttribute('onchange',"setDropdownOptionToSelectOptionArray(this, 0)");
				row.insertCell(cellCount).appendChild(select);
				removeSelectDuplicates(select.id);
				setDropdownOptionToSelectOptionArray($(select),0);
				cellCount = cellCount + 1;
			
			break;
		case 'Alt_0': 
			header_text.innerHTML = 'FULL INFOBAR SECTION';
			break;
			
		case 'Control_Shift_D':
			header_text.innerHTML = 'DOUBLE MATCH IDENT/PROMO';
			
			select = document.createElement('select');
			select.id = 'selectTieID';
			select.name = select.id;

			option = document.createElement('option');
			option.value = 'today';
			option.text = 'Today' ;
			select.appendChild(option);
			
			option = document.createElement('option');
			option.value = 'tomorrow';
			option.text = 'Tomorrow' ;
			select.appendChild(option);
			
			option = document.createElement('option');
			option.value = 'day_after_tomorrow';
			option.text = 'Day After Tomorrow' ;
			select.appendChild(option);
			
			row.insertCell(cellCount).appendChild(select);
			cellCount = cellCount + 1;
			
			select.setAttribute('onchange',"setDropdownOptionToSelectOptionArray(this, 0)");
			row.insertCell(cellCount).appendChild(select);
			setDropdownOptionToSelectOptionArray($(select),0);
			removeSelectDuplicates(select.id);
			cellCount = cellCount + 1;
			break;
			
		case 'r':
			header_text.innerHTML = 'Bug Review';
			
			select = document.createElement('select');
			select.id = 'selectTeams';
			select.name = select.id;
			session_match.match.inning.forEach(function(inn){
				if(inn.isCurrentInning == 'YES'){
					option = document.createElement('option');
					option.value = inn.battingTeamId;
					option.text = inn.batting_team.teamName1;
					select.appendChild(option);
					
					option = document.createElement('option');
					option.value = inn.bowlingTeamId;
					option.text = inn.bowling_team.teamName1;
					select.appendChild(option);
				}
			});
			select.setAttribute('onchange',"setDropdownOptionToSelectOptionArray(this, 0)");
			row.insertCell(cellCount).appendChild(select);
			setDropdownOptionToSelectOptionArray($(select),0);
			cellCount = cellCount + 1;
		
			select = document.createElement('select');
			select.id = 'selectType';
			select.name = select.id;
			
			['Original Decision Out','Original Decision Not Out', 'Wide Not Given', 'No-Ball Not Given', 'Wide Given', 
			 'No-Ball Given', 'Decision Overturned', 'Decision Upheld', 'Review lost', 'Review Retained'].forEach(stat => {
		        const option = document.createElement('option');
		        option.value = stat;
		        option.text = stat
		        select.appendChild(option);
		    });
			
			select.setAttribute('onchange',"setDropdownOptionToSelectOptionArray(this, 1)");
			row.insertCell(cellCount).appendChild(select);
			setDropdownOptionToSelectOptionArray($(select),1);
			cellCount = cellCount + 1
			break;	
		case 'Alt_d':
			header_text.innerHTML = 'LT - DLS PAR SCORE';
		
			select = document.createElement('select');
			select.id = 'selectdl';
			select.name = select.id;
			
			option = document.createElement('option');
			option.value = 'currentOver';
			option.text = 'Current Over';
			select.appendChild(option);
			
			option = document.createElement('option');
			option.value = 'nextBall';
			option.text = 'Next Ball';
			select.appendChild(option);
			
			option = document.createElement('option');
			option.value = 'nextOver';
			option.text = 'Next Over';
			select.appendChild(option);
			
			select.setAttribute('onchange',"setDropdownOptionToSelectOptionArray(this, 0)");
			row.insertCell(cellCount).appendChild(select);
			setDropdownOptionToSelectOptionArray($(select),0);
			cellCount = cellCount + 1
			break;	
		case 'Shift_C':
			header_text.innerHTML = 'SIX DISTANCE';
		
			select = document.createElement('input');
			select.type = "text";
			select.id = 'selectFreeText';
			
			select.setAttribute('onchange',"setTextBoxOptionForSixDistanceToSelectOptionArray(0)");
			row.insertCell(cellCount).appendChild(select);
			setTextBoxOptionToSelectOptionArray(0);
			cellCount = cellCount + 1;
			break;	
		case 'F6': case 'Shift_F6': case 'Alt_F6'://HowOut //how out w/o fielder // how out both
			switch(whatToProcess) {
			case 'F6':
				header_text.innerHTML = 'HOW OUT';
				break;
			case 'Shift_F6':
				header_text.innerHTML = 'HOW OUT WITHOUT FIELDER';
				break;	
			case 'Alt_F6':
				header_text.innerHTML = 'HOW OUT BOTH';
				break;				
			}
			select = document.createElement('select');
			select.id = 'selectHowoutPlayers';
			select.name = select.id;
			
			session_match.match.inning.forEach(function(inn){
				if(inn.inningNumber == document.getElementById('which_inning').value){
					inn.battingCard.forEach(function(bc,bc_index,bc_arr){
						if(inn.fallsOfWickets != null){
							if(inn.fallsOfWickets.length > 0){
								if(bc.playerId == inn.fallsOfWickets[inn.fallsOfWickets.length-1].fowPlayerID){
									option = document.createElement('option');
									option.value = bc.playerId;
									option.text = bc.player.full_name + " - " + bc.status;	
									select.appendChild(option);
								}
							}
						}
					});
					
					inn.battingCard.forEach(function(bc,bc_index,bc_arr){
						option = document.createElement('option');
						option.value = bc.playerId;
						option.text = bc.player.full_name + " - " + bc.status;	
						select.appendChild(option);
					});
				}
			});
			
			select.setAttribute('onchange','setDropdownOptionToSelectOptionArray(this, 0)');
			row.insertCell(cellCount).appendChild(select);
			//removeSelectDuplicates(select.id);
			setDropdownOptionToSelectOptionArray($(select),0);
			removeSelectDuplicates(select.id);
			cellCount = cellCount + 1;
			break;
		case 'F8': case 'Alt_F8'://NameSuper Player
			switch(whatToProcess){
			case 'F8':
				header_text.innerHTML = 'HOME TEAM NAMESUPER PLAYER';
				
				select = document.createElement('select');
				select.style = 'width:100px';
				select.id = 'selectPlayer';
				select.name = select.id;
				
				session_match.setup.homeSquad.forEach(function(hs){
					option = document.createElement('option');
					option.value = hs.playerId;
					option.text = hs.full_name + ' - ' + session_match.setup.homeTeam.teamName4;
					select.appendChild(option);
				});
				session_match.setup.homeOtherSquad.forEach(function(hos){
					option = document.createElement('option');
					option.value = hos.playerId;
					option.text = hos.full_name + ' - ' + session_match.setup.homeTeam.teamName4 + ' (OTHER)';
					select.appendChild(option);
				});
				
				select.setAttribute('onchange',"setDropdownOptionToSelectOptionArray(this, 0)");
				row.insertCell(cellCount).appendChild(select);
				setDropdownOptionToSelectOptionArray($(select),0);
				cellCount = cellCount + 1;
				break;
			case 'Alt_F8':
				header_text.innerHTML = 'AWAY TEAM NAMESUPER PLAYER';
				
				select = document.createElement('select');
				select.style = 'width:100px';
				select.id = 'selectPlayer';
				select.name = select.id;
				
				session_match.setup.awaySquad.forEach(function(as){
					option = document.createElement('option');
					option.value = as.playerId;
					option.text = as.full_name + ' - ' + session_match.setup.awayTeam.teamName4;
					select.appendChild(option);
				});
				session_match.setup.awayOtherSquad.forEach(function(aos){
					option = document.createElement('option');
					option.value = aos.playerId;
					option.text = aos.full_name + ' - ' + session_match.setup.awayTeam.teamName4 + ' (OTHER)';
					select.appendChild(option);
				});
				
				select.setAttribute('onchange',"setDropdownOptionToSelectOptionArray(this, 0)");
				row.insertCell(cellCount).appendChild(select);
				setDropdownOptionToSelectOptionArray($(select),0);
				cellCount = cellCount + 1;
				break;
				
			}
			
			select = document.createElement('select');
			select.style = 'width:100px';
			select.id = 'selectCaptainWicketKeeper';
			select.name = select.id;
			
			option = document.createElement('option');
			option.value = 'Captain';
			option.text = 'Captain';
			select.appendChild(option);
			
			option = document.createElement('option');
			option.value = 'Captain Wicket-Keeper';
			option.text = 'Captain-WicketKeeper';
			select.appendChild(option);
			
			option = document.createElement('option');
			option.value = 'Team';
			option.text = 'Team';
			select.appendChild(option);
			
			option = document.createElement('option');
			option.value = 'Man Of The Match';
			option.text = 'Man Of The Match';
			select.appendChild(option);
			
			option = document.createElement('option');
			option.value = 'Player Of The Match';
			option.text = 'Player Of The Match';
			select.appendChild(option);
			
			option = document.createElement('option');
			option.value = 'Wicket-Keeper';
			option.text = 'WicketKeeper';
			select.appendChild(option);
			
			option = document.createElement('option');
			option.value = 'CATCH OF THE MATCH';
			option.text = 'CATCH OF THE MATCH';
			select.appendChild(option);
			
			option = document.createElement('option');
			option.value = 'POWERFUL STRIKER OF THE MATCH';
			option.text = 'POWERFUL STRIKER OF THE MATCH';
			select.appendChild(option);
			
			option = document.createElement('option');
			option.value = 'BEST ECONOMY';
			option.text = 'BEST ECONOMY';
			select.appendChild(option);
			
			option = document.createElement('option');
			option.value = 'Player Of The Tournament';
			option.text = 'Player Of The Tournament';
			select.appendChild(option);
			
			select.setAttribute('onchange',"setDropdownOptionToSelectOptionArray(this, 1)");
			row.insertCell(cellCount).appendChild(select);
			setDropdownOptionToSelectOptionArray($(select),1);
			cellCount = cellCount + 1;
			break;
		case 'Alt_Shift_F4':
			header_text.innerHTML = 'PLAYER PROGRESSION';
			
			select = document.createElement('select');
			select.id = 'selectPlayerProName';
			select.name = select.id;
			
			session_match.match.inning.forEach(function(inn){
				if(inn.inningNumber == document.getElementById('which_inning').value){
					inn.battingCard.forEach(function(bc){
						if(bc.status == 'NOT OUT'){
							if(bc.onStrike == 'YES'){
								option = document.createElement('option');
								option.value = bc.player.playerId;
								option.text = bc.player.full_name;
								select.appendChild(option);
							}else{
								option = document.createElement('option');
								option.value = bc.player.playerId;
								option.text = bc.player.full_name;
								select.appendChild(option);
							}
						}
					});
					
					if(inn.battingTeamId == session_match.setup.homeTeamId){
						session_match.setup.homeSquad.forEach(function(hs){
							option = document.createElement('option');
							option.value = hs.playerId;
							option.text = hs.full_name;
							select.appendChild(option);
						});
						session_match.setup.homeOtherSquad.forEach(function(hos){
							option = document.createElement('option');
							option.value = hos.playerId;
							option.text = hos.full_name  + ' (OTHER)';
							select.appendChild(option);
						});
					}else {
						session_match.setup.awaySquad.forEach(function(as){
							option = document.createElement('option');
							option.value = as.playerId;
							option.text = as.full_name;
							select.appendChild(option);
						});
						session_match.setup.awayOtherSquad.forEach(function(aos){
							option = document.createElement('option');
							option.value = aos.playerId;
							option.text = aos.full_name  + ' (OTHER)';
							select.appendChild(option);
						});
					}
				}
			});

			select.setAttribute('onchange',"setDropdownOptionToSelectOptionArray(this, 0)");
			row.insertCell(cellCount).appendChild(select);
			//removeSelectDuplicates(select.id);
			setDropdownOptionToSelectOptionArray($(select),0);
			removeSelectDuplicates(select.id);
			cellCount = cellCount + 1;
			
			break;
		case 'F7'://Lt Bat Profile
			header_text.innerHTML = 'BAT PLAYER PROFILE';
			
			select = document.createElement('select');
			select.id = 'selectPlayerName';
			select.name = select.id;
			
			session_match.match.inning.forEach(function(inn){
				if(inn.inningNumber == document.getElementById('which_inning').value){
					inn.battingCard.forEach(function(bc){
						if(bc.status == 'NOT OUT'){
							if(bc.onStrike == 'YES'){
								option = document.createElement('option');
								option.value = bc.player.playerId;
								option.text = bc.player.full_name;
								select.appendChild(option);
							}else{
								option = document.createElement('option');
								option.value = bc.player.playerId;
								option.text = bc.player.full_name;
								select.appendChild(option);
							}
						}
					});
					
					if(inn.battingTeamId == session_match.setup.homeTeamId){
						session_match.setup.homeSquad.forEach(function(hs){
							option = document.createElement('option');
							option.value = hs.playerId;
							option.text = hs.full_name;
							select.appendChild(option);
						});
						session_match.setup.homeOtherSquad.forEach(function(hos){
							option = document.createElement('option');
							option.value = hos.playerId;
							option.text = hos.full_name  + ' (OTHER)';
							select.appendChild(option);
						});
					}else {
						session_match.setup.awaySquad.forEach(function(as){
							option = document.createElement('option');
							option.value = as.playerId;
							option.text = as.full_name;
							select.appendChild(option);
						});
						session_match.setup.awayOtherSquad.forEach(function(aos){
							option = document.createElement('option');
							option.value = aos.playerId;
							option.text = aos.full_name  + ' (OTHER)';
							select.appendChild(option);
						});
					}
				}
			});

			select.setAttribute('onchange',"setDropdownOptionToSelectOptionArray(this, 0)");
			row.insertCell(cellCount).appendChild(select);
			//removeSelectDuplicates(select.id);
			setDropdownOptionToSelectOptionArray($(select),0);
			removeSelectDuplicates(select.id);
			cellCount = cellCount + 1;
			
			select = document.createElement('select');
			select.id = 'selectProfile';
			select.name = select.id;
			
			switch($('#selected_broadcaster').val().toUpperCase()){
				case 'TRI_SERIES': case 'BAN_AFG_SERIES': case 'ACC': case 'MT20':
				
					option = document.createElement('option');
					option.value = 'DT20';
					option.text = 'T20';
					select.appendChild(option);
					
					option = document.createElement('option');
					option.value = 'MAHARAJA_CAREER';
					option.text = 'MAHARAJA';
					select.appendChild(option);
					
					option = document.createElement('option');
					option.value = 'IT20';
					option.text = 'T20-I';
					select.appendChild(option);
					
					option = document.createElement('option');
					option.value = 'LIST A';
					option.text = 'LIST A';
					select.appendChild(option);
					
					option = document.createElement('option');
					option.value = 'ODI';
					option.text = 'ODI';
					select.appendChild(option);
					
					option = document.createElement('option');
					option.value = 'TEST';
					option.text = 'TEST MATCHES';
					select.appendChild(option);
					break;
				case 'TG20':	
					option = document.createElement('option');
					option.value = 'DT20';
					option.text = 'T20';
					select.appendChild(option);
					break;		
			}
			select.setAttribute('onchange',"setDropdownOptionToSelectOptionArray(this, 1)");
			row.insertCell(cellCount).appendChild(select);
			setDropdownOptionToSelectOptionArray($(select),1);
			cellCount = cellCount + 1
			break;
				
			case 'F10' ://NameSuperDB
			header_text.innerHTML = 'NAMESUPER DATABASE';
			select = document.createElement('select');
			select.style = 'width:130px';
			select.id = 'selectNameSuper';
			select.name = select.id;
			
			dataToProcess.forEach(function(ns){
				option = document.createElement('option');
				option.value = ns.namesuperId;
				option.text = ns.prompt ;
				select.appendChild(option);
			});
			
			select.setAttribute('onchange',"setDropdownOptionToSelectOptionArray(this, 0)");
			row.insertCell(cellCount).appendChild(select);
			setDropdownOptionToSelectOptionArray($(select),0);
			
			cellCount = cellCount + 1;
			break;
		case 'l':
			header_text.innerHTML = 'All Rounder Stats';
		
			select = document.createElement('select');
			select.id = 'selectPlayerName';
			select.name = select.id;
			
			session_match.match.inning.forEach(function(inn){
				if(inn.inningNumber == document.getElementById('which_inning').value){
					inn.battingCard.forEach(function(bc){
						if(bc.status == 'NOT OUT'){
							if(bc.onStrike == 'YES'){
								option = document.createElement('option');
								option.value = bc.player.playerId;
								option.text = bc.player.full_name;
								select.appendChild(option);
							}else{
								option = document.createElement('option');
								option.value = bc.player.playerId;
								option.text = bc.player.full_name;
								select.appendChild(option);
							}
						}
					});
					
					if(inn.battingTeamId == session_match.setup.homeTeamId){
						session_match.setup.homeSquad.forEach(function(hs){
							option = document.createElement('option');
							option.value = hs.playerId;
							option.text = hs.full_name;
							select.appendChild(option);
						});
						session_match.setup.homeOtherSquad.forEach(function(hos){
							option = document.createElement('option');
							option.value = hos.playerId;
							option.text = hos.full_name  + ' (OTHER)';
							select.appendChild(option);
						});
					}else {
						session_match.setup.awaySquad.forEach(function(as){
							option = document.createElement('option');
							option.value = as.playerId;
							option.text = as.full_name;
							select.appendChild(option);
						});
						session_match.setup.awayOtherSquad.forEach(function(aos){
							option = document.createElement('option');
							option.value = aos.playerId;
							option.text = aos.full_name  + ' (OTHER)';
							select.appendChild(option);
						});
					}
				}
			});

			select.setAttribute('onchange',"setDropdownOptionToSelectOptionArray(this, 0)");
			row.insertCell(cellCount).appendChild(select);
			//removeSelectDuplicates(select.id);
			setDropdownOptionToSelectOptionArray($(select),0);
			removeSelectDuplicates(select.id);
			cellCount = cellCount + 1;
			
			select = document.createElement('select');
			select.id = 'selectProfile';
			select.name = select.id;
			
			option = document.createElement('option');
			option.value = 'THIS_MATCH';
			option.text = 'THIS MATCH';
			select.appendChild(option);
			
			option = document.createElement('option');
			option.value = 'TOURNAMENT';
			option.text = 'THIS TOURNAMENT';
			select.appendChild(option);
			
			select.setAttribute('onchange',"setDropdownOptionToSelectOptionArray(this, 1)");
			row.insertCell(cellCount).appendChild(select);
			setDropdownOptionToSelectOptionArray($(select),1);
			cellCount = cellCount + 1
			
			break;	
		case 'F11': case "Alt_Shift_M"://Lt Ball Profile
			switch(whatToProcess){
			case 'F11': case "Alt_Shift_M":
				header_text.innerHTML = 'BALL PLAYER PROFILE';
				break;
			}
		
			select = document.createElement('select');
			select.id = 'selectPlayerName';
			select.name = select.id;
			
			session_match.match.inning.forEach(function(inn){
				if(inn.inningNumber == document.getElementById('which_inning').value){
					if(inn.bowlingCard != null){
						inn.bowlingCard.forEach(function(boc){
							if(boc.status != null){
								if(boc.status == 'CURRENTBOWLER'){
									option = document.createElement('option');
									option.value = boc.player.playerId;
									option.text = boc.player.full_name;
									select.appendChild(option);
								}
							}
						});
					}
					
					if(inn.bowlingTeamId == session_match.setup.homeTeamId){
						session_match.setup.homeSquad.forEach(function(hs){
							option = document.createElement('option');
							option.value = hs.playerId;
							option.text = hs.full_name;
							select.appendChild(option);
						});
						session_match.setup.homeOtherSquad.forEach(function(hos){
							option = document.createElement('option');
							option.value = hos.playerId;
							option.text = hos.full_name  + ' (OTHER)';
							select.appendChild(option);
						});
					}else {
						session_match.setup.awaySquad.forEach(function(as){
							option = document.createElement('option');
							option.value = as.playerId;
							option.text = as.full_name;
							select.appendChild(option);
						});
						session_match.setup.awayOtherSquad.forEach(function(aos){
							option = document.createElement('option');
							option.value = aos.playerId;
							option.text = aos.full_name  + ' (OTHER)';
							select.appendChild(option);
						});
					}
				}
			});

			select.setAttribute('onchange',"setDropdownOptionToSelectOptionArray(this, 0)");
			row.insertCell(cellCount).appendChild(select);
			//removeSelectDuplicates(select.id);
			setDropdownOptionToSelectOptionArray($(select),0);
			removeSelectDuplicates(select.id);
			cellCount = cellCount + 1;
			
			select = document.createElement('select');
			select.id = 'selectProfile';
			select.name = select.id;
			
			switch($('#selected_broadcaster').val().toUpperCase()){
				case 'TRI_SERIES': case 'BAN_AFG_SERIES': case 'ACC': case 'MT20':
					
					option = document.createElement('option');
					option.value = 'DT20';
					option.text = 'T20';
					select.appendChild(option);
					
					option = document.createElement('option');
					option.value = 'MAHARAJA_CAREER';
					option.text = 'MAHARAJA';
					select.appendChild(option);
					
					option = document.createElement('option');
					option.value = 'IT20';
					option.text = 'T20-I';
					select.appendChild(option);
					
					option = document.createElement('option');
					option.value = 'LIST A';
					option.text = 'LIST A';
					select.appendChild(option);
					
					option = document.createElement('option');
					option.value = 'ODI';
					option.text = 'ODI';
					select.appendChild(option);
					
					option = document.createElement('option');
					option.value = 'TEST';
					option.text = 'TEST MATCHES';
					select.appendChild(option);
					break;
				case 'TG20':			
					option = document.createElement('option');
					option.value = 'DT20';
					option.text = 'T20';
					select.appendChild(option);
					break;	
			}
			
			select.setAttribute('onchange',"setDropdownOptionToSelectOptionArray(this, 1)");
			row.insertCell(cellCount).appendChild(select);
			setDropdownOptionToSelectOptionArray($(select),1);
			cellCount = cellCount + 1
			
			break;
		case 'Control_s':
			header_text.innerHTML = 'LT THIS SERIES BAT';
			select = document.createElement('select');
			select.id = 'selectPlayerName';
			select.name = select.id;
			
			session_match.match.inning.forEach(function(inn){
				if(inn.inningNumber == document.getElementById('which_inning').value){
					inn.battingCard.forEach(function(bc){
						if(bc.status == 'NOT OUT'){
							if(bc.onStrike == 'YES'){
								option = document.createElement('option');
								option.value = bc.player.playerId;
								option.text = bc.player.full_name;
								select.appendChild(option);
							}else{
								option = document.createElement('option');
								option.value = bc.player.playerId;
								option.text = bc.player.full_name;
								select.appendChild(option);
							}
						}
					});
					
					if(inn.battingTeamId == session_match.setup.homeTeamId){
						session_match.setup.homeSquad.forEach(function(hs){
							option = document.createElement('option');
							option.value = hs.playerId;
							option.text = hs.full_name;
							select.appendChild(option);
						});
						session_match.setup.homeOtherSquad.forEach(function(hos){
							option = document.createElement('option');
							option.value = hos.playerId;
							option.text = hos.full_name  + ' (OTHER)';
							select.appendChild(option);
						});
					}else {
						session_match.setup.awaySquad.forEach(function(as){
							option = document.createElement('option');
							option.value = as.playerId;
							option.text = as.full_name;
							select.appendChild(option);
						});
						session_match.setup.awayOtherSquad.forEach(function(aos){
							option = document.createElement('option');
							option.value = aos.playerId;
							option.text = aos.full_name  + ' (OTHER)';
							select.appendChild(option);
						});
					}
				}
			});

			select.setAttribute('onchange',"setDropdownOptionToSelectOptionArray(this, 0)");
			row.insertCell(cellCount).appendChild(select);
			removeSelectDuplicates(select.id);
			setDropdownOptionToSelectOptionArray($(select),0);
			cellCount = cellCount + 1;
			
			select = document.createElement('select');
			select.id = 'selectType';
			select.name = select.id;
			
			option = document.createElement('option');
			option.value = 'WITH_CURRENT';
			option.text = 'With Current Match';
			select.appendChild(option);
			
			option = document.createElement('option');
			option.value = 'WITHOUT_CURRENT';
			option.text = 'Without Current Match';
			select.appendChild(option);
			
			select.setAttribute('onchange',"setDropdownOptionToSelectOptionArray(this, 1)");
			row.insertCell(cellCount).appendChild(select);
			setDropdownOptionToSelectOptionArray($(select),1);
			cellCount = cellCount + 1;
			break;
		case 'Control_f':
			header_text.innerHTML = 'LT BALL THIS SERIES';
			select = document.createElement('select');
			select.id = 'selectPlayerName';
			select.name = select.id;
			
			session_match.match.inning.forEach(function(inn){
				if(inn.inningNumber == document.getElementById('which_inning').value){
					if(inn.bowlingCard != null){
						inn.bowlingCard.forEach(function(boc){
							if(boc.status != null){
								if(boc.status == 'CURRENTBOWLER'){
									option = document.createElement('option');
									option.value = boc.player.playerId;
									option.text = boc.player.full_name;
									select.appendChild(option);
								}
							}
						});
					}
					
					if(inn.bowlingTeamId == session_match.setup.homeTeamId){
						session_match.setup.homeSquad.forEach(function(hs){
							option = document.createElement('option');
							option.value = hs.playerId;
							option.text = hs.full_name;
							select.appendChild(option);
						});
						session_match.setup.homeOtherSquad.forEach(function(hos){
							option = document.createElement('option');
							option.value = hos.playerId;
							option.text = hos.full_name  + ' (OTHER)';
							select.appendChild(option);
						});
					}else {
						session_match.setup.awaySquad.forEach(function(as){
							option = document.createElement('option');
							option.value = as.playerId;
							option.text = as.full_name;
							select.appendChild(option);
						});
						session_match.setup.awayOtherSquad.forEach(function(aos){
							option = document.createElement('option');
							option.value = aos.playerId;
							option.text = aos.full_name  + ' (OTHER)';
							select.appendChild(option);
						});
					}
				}
			});

			select.setAttribute('onchange',"setDropdownOptionToSelectOptionArray(this, 0)");
			row.insertCell(cellCount).appendChild(select);
			removeSelectDuplicates(select.id);
			setDropdownOptionToSelectOptionArray($(select),0);
			cellCount = cellCount + 1;
			
			select = document.createElement('select');
			select.id = 'selectType';
			select.name = select.id;
			
			option = document.createElement('option');
			option.value = 'WITH_CURRENT';
			option.text = 'With Current Match';
			select.appendChild(option);
			
			option = document.createElement('option');
			option.value = 'WITHOUT_CURRENT';
			option.text = 'Without Current Match';
			select.appendChild(option);
			
			select.setAttribute('onchange',"setDropdownOptionToSelectOptionArray(this, 1)");
			row.insertCell(cellCount).appendChild(select);
			setDropdownOptionToSelectOptionArray($(select),1);
			cellCount = cellCount + 1;
			break;
		case 'u':
			header_text.innerHTML = '30-50 SPLIT';
			
			select = document.createElement('select');
			select.id = 'selectSplit';
			select.name = select.id;
			
			if (session_match.setup.matchType === 'ODI' || session_match.setup.matchType === 'OD'){
				option = document.createElement('option');
				option.value = '50';
				option.text = '50-Split';
				select.appendChild(option);
				
				option = document.createElement('option');
				option.value = '100';
				option.text = '100-Split';
				select.appendChild(option);
			}else{
				option = document.createElement('option');
				option.value = '30';
				option.text = '30-Split';
				select.appendChild(option);
				
				option = document.createElement('option');
				option.value = '50';
				option.text = '50-Split';
				select.appendChild(option);
			}
			
			select.setAttribute('onchange',"setDropdownOptionToSelectOptionArray(this, 0)");
			row.insertCell(cellCount).appendChild(select);
			setDropdownOptionToSelectOptionArray($(select),0);
			cellCount = cellCount + 1
			break;
		case 'F5': case 'Control_Shift_X':
			header_text.innerHTML = 'BAT THIS MATCH';
			select = document.createElement('select');
			select.id = 'selectBatsmanThisMatch';
			select.name = select.id;
			
			session_match.match.inning.forEach(function(inn){
				if(inn.inningNumber == document.getElementById('which_inning').value){
					inn.battingCard.forEach(function(bc){
						if(bc.status == 'NOT OUT'){
							if(bc.onStrike == 'YES'){
								option = document.createElement('option');
								option.value = bc.playerId;
								option.text = bc.player.full_name + " - " + bc.status;
								select.appendChild(option);
							}else{
								option = document.createElement('option');
								option.value = bc.playerId;
								option.text = bc.player.full_name + " - " + bc.status;
								select.appendChild(option);
							}
						}
					});
					
					inn.battingCard.forEach(function(bc,bc_index,bc_arr){
						option = document.createElement('option');
						option.value = bc.playerId;
						option.text = bc.player.full_name + " - " + bc.status;	
						select.appendChild(option);
					});
				}
			});
			
			select.setAttribute('onchange',"setDropdownOptionToSelectOptionArray(this, 0)");
			row.insertCell(cellCount).appendChild(select);
			//removeSelectDuplicates(select.id);
			setDropdownOptionToSelectOptionArray($(select),0);
			removeSelectDuplicates(select.id);
			cellCount = cellCount + 1;
			
			switch(whatToProcess) {	
			case 'Control_Shift_X':
				select = document.createElement('select');
				select.id = 'selectProfile';
				select.name = select.id;
				
				switch($('#selected_broadcaster').val().toUpperCase()){
					case 'TRI_SERIES': case 'MT20': case 'TG20':
						option = document.createElement('option');
						option.value = 'IT20';
						option.text = 'T20-I';
						select.appendChild(option);
						
						option = document.createElement('option');
						option.value = 'DT20';
						option.text = 'T20';
						select.appendChild(option);
						
						option = document.createElement('option');
						option.value = 'ODI';
						option.text = 'ODI';
						select.appendChild(option);
						
						option = document.createElement('option');
						option.value = 'TEST';
						option.text = 'TEST MATCHES';
						select.appendChild(option);
						break;	
				}
				select.setAttribute('onchange',"setDropdownOptionToSelectOptionArray(this, 1)");
				row.insertCell(cellCount).appendChild(select);
				setDropdownOptionToSelectOptionArray($(select),1);
				cellCount = cellCount + 1
				break;		
			}
			
			break;
		case 'F9': case 'Control_Shift_K':
			header_text.innerHTML = 'BALL THIS MATCH';
			select = document.createElement('select');
			select.id = 'selectBatamanThisMatch';
			select.name = select.id;
			
			session_match.match.inning.forEach(function(inn){
				if(inn.inningNumber == document.getElementById('which_inning').value){
					inn.bowlingCard.forEach(function(boc){
						if(boc.status == 'CURRENTBOWLER'){
							option = document.createElement('option');
							option.value = boc.player.playerId;
							option.text = boc.player.full_name;
							select.appendChild(option);
						}
					});
					
					inn.bowlingCard.forEach(function(boc,boc_index,bc_arr){
						option = document.createElement('option');
						option.value = boc.playerId;
						option.text = boc.player.full_name;	
						select.appendChild(option);
					});
				}
			});
			
			select.setAttribute('onchange',"setDropdownOptionToSelectOptionArray(this, 0)");
			row.insertCell(cellCount).appendChild(select);
			//removeSelectDuplicates(select.id);
			setDropdownOptionToSelectOptionArray($(select),0);
			removeSelectDuplicates(select.id);
			cellCount = cellCount + 1;
			
			switch(whatToProcess) {	
			case 'Control_Shift_K':
				select = document.createElement('select');
				select.id = 'selectProfile';
				select.name = select.id;
				
				switch($('#selected_broadcaster').val().toUpperCase()){
					case 'TRI_SERIES': case 'MT20': case 'TG20':
						option = document.createElement('option');
						option.value = 'IT20';
						option.text = 'T20-I';
						select.appendChild(option);
						
						option = document.createElement('option');
						option.value = 'DT20';
						option.text = 'T20';
						select.appendChild(option);
						
						option = document.createElement('option');
						option.value = 'ODI';
						option.text = 'ODI';
						select.appendChild(option);
						
						option = document.createElement('option');
						option.value = 'TEST';
						option.text = 'TEST MATCHES';
						select.appendChild(option);
						break;	
				}
				select.setAttribute('onchange',"setDropdownOptionToSelectOptionArray(this, 1)");
				row.insertCell(cellCount).appendChild(select);
				setDropdownOptionToSelectOptionArray($(select),1);
				cellCount = cellCount + 1
				break;		
			}			
			break;
		case 'Control_c':
			header_text.innerHTML = 'BAT PLAYER PROFILE';
			
			select = document.createElement('select');
			select.id = 'selectPlayerName';
			select.name = select.id;
			
			session_match.match.inning.forEach(function(inn){
				if(inn.inningNumber == document.getElementById('which_inning').value){
					inn.battingCard.forEach(function(bc){
						if(bc.status == 'NOT OUT'){
							if(bc.onStrike == 'YES'){
								option = document.createElement('option');
								option.value = bc.player.playerId;
								option.text = bc.player.full_name;
								select.appendChild(option);
							}else{
								option = document.createElement('option');
								option.value = bc.player.playerId;
								option.text = bc.player.full_name;
								select.appendChild(option);
							}
						}
					});
					
					if(inn.battingTeamId == session_match.setup.homeTeamId){
						session_match.setup.homeSquad.forEach(function(hs){
							option = document.createElement('option');
							option.value = hs.playerId;
							option.text = hs.full_name;
							select.appendChild(option);
						});
						session_match.setup.homeOtherSquad.forEach(function(hos){
							option = document.createElement('option');
							option.value = hos.playerId;
							option.text = hos.full_name  + ' (OTHER)';
							select.appendChild(option);
						});
					}else {
						session_match.setup.awaySquad.forEach(function(as){
							option = document.createElement('option');
							option.value = as.playerId;
							option.text = as.full_name;
							select.appendChild(option);
						});
						session_match.setup.awayOtherSquad.forEach(function(aos){
							option = document.createElement('option');
							option.value = aos.playerId;
							option.text = aos.full_name  + ' (OTHER)';
							select.appendChild(option);
						});
					}
				}
			});

			select.setAttribute('onchange',"setDropdownOptionToSelectOptionArray(this, 0)");
			row.insertCell(cellCount).appendChild(select);
			//removeSelectDuplicates(select.id);
			setDropdownOptionToSelectOptionArray($(select),0);
			removeSelectDuplicates(select.id);
			cellCount = cellCount + 1;
			break;	
		case 'Shift_F9': //0,1,2
			
			switch(whatToProcess) {
			case 'Shift_F9':
				header_text.innerHTML = 'BALL 0,1,2';
				break;				
			}
			
			select = document.createElement('select');
			select.id = 'selectBatamanThisMatch';
			select.name = select.id;
			
			session_match.match.inning.forEach(function(inn){
				if(inn.inningNumber == document.getElementById('which_inning').value){
					inn.bowlingCard.forEach(function(boc){
						if(boc.status == 'CURRENTBOWLER'){
							option = document.createElement('option');
							option.value = boc.player.playerId;
							option.text = boc.player.full_name;
							select.appendChild(option);
						}
					});
					
					inn.bowlingCard.forEach(function(boc,boc_index,bc_arr){
						option = document.createElement('option');
						option.value = boc.playerId;
						option.text = boc.player.full_name;	
						select.appendChild(option);
					});
				}
			});
			
			select.setAttribute('onchange',"setDropdownOptionToSelectOptionArray(this, 0)");
			row.insertCell(cellCount).appendChild(select);
			//removeSelectDuplicates(select.id);
			setDropdownOptionToSelectOptionArray($(select),0);
			removeSelectDuplicates(select.id);
			cellCount = cellCount + 1;
			break;
		case 'Shift_F5': //0,1,2
			header_text.innerHTML = 'BAT 0,1,2';
			select = document.createElement('select');
			select.id = 'selectBatsmanThisMatch';
			select.name = select.id;
			
			session_match.match.inning.forEach(function(inn){
				if(inn.inningNumber == document.getElementById('which_inning').value){
					inn.battingCard.forEach(function(bc){
						if(bc.status == 'NOT OUT'){
							if(bc.onStrike == 'YES'){
								option = document.createElement('option');
								option.value = bc.playerId;
								option.text = bc.player.full_name + " - " + bc.status;
								select.appendChild(option);
							}else{
								option = document.createElement('option');
								option.value = bc.playerId;
								option.text = bc.player.full_name + " - " + bc.status;
								select.appendChild(option);
							}
						}
					});
					
					inn.battingCard.forEach(function(bc,bc_index,bc_arr){
						option = document.createElement('option');
						option.value = bc.playerId;
						option.text = bc.player.full_name + " - " + bc.status;	
						select.appendChild(option);
					});
				}
			});
			
			select.setAttribute('onchange',"setDropdownOptionToSelectOptionArray(this, 0)");
			row.insertCell(cellCount).appendChild(select);
			//removeSelectDuplicates(select.id);
			setDropdownOptionToSelectOptionArray($(select),0);
			removeSelectDuplicates(select.id);
			cellCount = cellCount + 1;
			break;
		case 'Alt_F12':
			header_text.innerHTML = 'TEAM 0,1,2';
			select = document.createElement('select');
			select.id = 'selectTeams';
			select.name = select.id;
			session_match.match.inning.forEach(function(inn){
				if(inn.isCurrentInning == 'YES'){
					option = document.createElement('option');
					option.value = inn.battingTeamId;
					option.text = inn.batting_team.teamName1;
					select.appendChild(option);
					
					option = document.createElement('option');
					option.value = inn.bowlingTeamId;
					option.text = inn.bowling_team.teamName1;
					select.appendChild(option);
				}
			});
			select.setAttribute('onchange',"setDropdownOptionToSelectOptionArray(this, 0)");
			row.insertCell(cellCount).appendChild(select);
			setDropdownOptionToSelectOptionArray($(select),0);
			cellCount = cellCount + 1;
			
			break;
		case 'Shift_E':
		 	header_text.innerHTML = 'LT - EXTRAS';
		
			select = document.createElement('select');
			select.id = 'selectExtras';
			select.name = select.id;
			
			option = document.createElement('option');
			option.value = '1';
			option.text = '1st Inning';
			select.appendChild(option);
			
			option = document.createElement('option');
			option.value = '2';
			option.text = '2nd Inning';
			select.appendChild(option);
			
			/*option = document.createElement('option');
			option.value = 'totalExtras';
			option.text = 'Total Extras';
			select.appendChild(option);*/
			
			select.setAttribute('onchange',"setDropdownOptionToSelectOptionArray(this, 0)");
			row.insertCell(cellCount).appendChild(select);
			setDropdownOptionToSelectOptionArray($(select),0);
			cellCount = cellCount + 1
			break;
		case 'y':
			select = document.createElement('select');
			select.id = 'selectBatsmanThisMatch';
			select.name = select.id;
			
			session_match.match.inning.forEach(function(inn){
				if(inn.inningNumber == document.getElementById('which_inning').value){
					inn.battingCard.forEach(function(bc){
						if(bc.status == 'NOT OUT'){
							if(bc.onStrike == 'YES'){
								option = document.createElement('option');
								option.value = bc.player.playerId;
								option.text = bc.player.full_name + " - " + bc.status;
								select.appendChild(option);
							}else{
								option = document.createElement('option');
								option.value = bc.player.playerId;
								option.text = bc.player.full_name + " - " + bc.status;
								select.appendChild(option);
							}
						}
					});
					
					inn.battingCard.forEach(function(bc,bc_index,bc_arr){
						option = document.createElement('option');
						option.value = bc.playerId;
						option.text = bc.player.full_name + " - " + bc.status;	
						select.appendChild(option);
					});
				}
			});
			
			select.setAttribute('onchange',"setDropdownOptionToSelectOptionArray(this, 0)");
			row.insertCell(cellCount).appendChild(select);
			//removeSelectDuplicates(select.id);
			setDropdownOptionToSelectOptionArray($(select),0);
			removeSelectDuplicates(select.id);
			cellCount = cellCount + 1;
			break;
		case 'k':
			select = document.createElement('select');
			select.style = 'width:400px';
			select.id = 'selectBugdb';
			select.name = select.id;
			
			dataToProcess.forEach(function(bug){
				option = document.createElement('option');
				option.value = bug.bugId;
				option.text = bug.prompt;
				select.appendChild(option);
			});
			select.setAttribute('onchange',"setDropdownOptionToSelectOptionArray(this, 0)");
			row.insertCell(cellCount).appendChild(select);
			setDropdownOptionToSelectOptionArray($(select),0);
			cellCount = cellCount + 1;
			$('#selectBugdb').select2();
			break;
		case 'g':
			select = document.createElement('select');
			select.id = 'selectBatamanThisMatch';
			select.name = select.id;
			
			session_match.match.inning.forEach(function(inn){
				if(inn.inningNumber == document.getElementById('which_inning').value){
					inn.bowlingCard.forEach(function(boc){
						if(boc.status == 'CURRENTBOWLER'){
							option = document.createElement('option');
							option.value = boc.player.playerId;
							option.text = boc.player.full_name;
							select.appendChild(option);
						}
					});
					
					inn.bowlingCard.forEach(function(boc,boc_index,bc_arr){
						option = document.createElement('option');
						option.value = boc.playerId;
						option.text = boc.player.full_name;	
						select.appendChild(option);
					});
				}
			});
			
			select.setAttribute('onchange',"setDropdownOptionToSelectOptionArray(this, 0)");
			row.insertCell(cellCount).appendChild(select);
			//removeSelectDuplicates(select.id);
			setDropdownOptionToSelectOptionArray($(select),0);
			removeSelectDuplicates(select.id);
			cellCount = cellCount + 1;
			break;
		case 'h':
			header_text.innerHTML = 'HIGHLIGHT BUG';
			
			select = document.createElement('select');
			select.id = 'selectHighlightBug';
			select.name = select.id;
			
			option = document.createElement('option');
			option.value = 'WITHOUT_SPONSOR';
			option.text = 'Without Sponsor';
			select.appendChild(option);
			
			option = document.createElement('option');
			option.value = 'WITH_SPONSOR';
			option.text = 'With Sponsor';
			select.appendChild(option);
			
			select.setAttribute('onchange',"setDropdownOptionToSelectOptionArray(this, 0)");
			row.insertCell(cellCount).appendChild(select);
			setDropdownOptionToSelectOptionArray($(select),0);
			cellCount = cellCount + 1;
			break;
		case 'Control_y':
			switch($('#selected_broadcaster').val().toUpperCase()){
				case 'BAN_AFG_SERIES': //case 'ACC':
					header_text.innerHTML = 'POWERPLAY';
	
					select = document.createElement('select');
					select.id = 'selectPowerplay';
					select.name = select.id;
					
					option = document.createElement('option');
					option.value = 'p1';
					option.text = 'Powerplay 1';
					select.appendChild(option);
					
					option = document.createElement('option');
					option.value = 'p2';
					option.text = 'Powerplay 2';
					select.appendChild(option);
					
					option = document.createElement('option');
					option.value = 'p3';
					option.text = 'Powerplay 3';
					select.appendChild(option);
					
					select.setAttribute('onchange',"setDropdownOptionToSelectOptionArray(this, 0)");
					row.insertCell(cellCount).appendChild(select);
					setDropdownOptionToSelectOptionArray($(select),0);
					cellCount = cellCount + 1
					break;
				default:
					header_text.innerHTML = 'POWERPLAY';
	
					select = document.createElement('select');
					select.style = 'width:130px';
					select.id = 'selectTeam';
					select.name = select.id;
					
					option = document.createElement('option');
					option.value = '1';
					option.text = '1st Inning';
					select.appendChild(option);
					
					option = document.createElement('option');
					option.value = '2';
					option.text = '2nd Inning';
					select.appendChild(option);
					
					select.setAttribute('onchange',"setDropdownOptionToSelectOptionArray(this, 0)");
					row.insertCell(cellCount).appendChild(select);
					setDropdownOptionToSelectOptionArray($(select),0);
					
					cellCount = cellCount + 1;
					break;	
				}
			break;
		case 'Control_Shift_*' :
			header_text.innerHTML = 'SPONSOR DATABASE';
			select = document.createElement('select');
			select.style = 'width:130px';
			select.id = 'selectSponsor';
			select.name = select.id;
			
			dataToProcess.forEach(function(ns){
				option = document.createElement('option');
				option.value = ns.bugId;
				option.text = ns.prompt ;
				select.appendChild(option);
			});
			
			select.setAttribute('onchange',"setDropdownOptionToSelectOptionArray(this, 0)");
			row.insertCell(cellCount).appendChild(select);
			setDropdownOptionToSelectOptionArray($(select),0);
			
			cellCount = cellCount + 1;
			break;
		case 'Shift_O':
			select = document.createElement('select');
			select.id = 'selectHowoutPlayers';
			select.name = select.id;
			
			session_match.match.inning.forEach(function(inn){
				if(inn.inningNumber == document.getElementById('which_inning').value){
					inn.battingCard.forEach(function(bc,bc_index,bc_arr){
						option = document.createElement('option');
						option.value = bc.playerId;
						option.text = bc.player.full_name + " - " + bc.status;	
						select.appendChild(option);
					});
				}
			});
			
			select.setAttribute('onchange','setDropdownOptionToSelectOptionArray(this, 0)');
			row.insertCell(cellCount).appendChild(select);
			setDropdownOptionToSelectOptionArray($(select),0);
			cellCount = cellCount + 1;
			
			select = document.createElement('select');
			select.id = 'selectsponsor';
			select.name = select.id;
			
			option = document.createElement('option');
			option.value = 'without';
			option.text = 'without sponsor';
			select.appendChild(option);
			
			option = document.createElement('option');
			option.value = 'with';
			option.text = 'with sponsor';
			select.appendChild(option);
			
			select.setAttribute('onchange',"setDropdownOptionToSelectOptionArray(this, 1)");
			row.insertCell(cellCount).appendChild(select);
			setDropdownOptionToSelectOptionArray($(select),1);
			cellCount = cellCount + 1;
			break;
		case 'Shift_F4':
			header_text.innerHTML = 'BUG MULTI PARTNERSHIP';
			select = document.createElement('select');
			select.id = 'selectPartnership';
			select.name = select.id;
			session_match.match.inning.forEach(function(inn){
				if(inn.inningNumber == document.getElementById('which_inning').value){
					for(var i=1; i<=inn.partnerships.length; i++) {
			            option = document.createElement('option');
						option.value = i;
						option.text = i;
						select.appendChild(option);
					}
				}
			});
			select.setAttribute('onchange',"setDropdownOptionToSelectOptionArray(this, 0)");
			row.insertCell(cellCount).appendChild(select);
			setDropdownOptionToSelectOptionArray($(select),0);
			cellCount = cellCount + 1;
			break;
		case 'Alt_p':
		
			header_text.innerHTML = 'BUG TOSS';
			
			select = document.createElement('select');
			select.id = 'selectTeams';
			select.name = select.id;
			
			switch($('#selected_broadcaster').val().toUpperCase()){
			case 'TG20':
				option = document.createElement('option');
				option.value = session_match.setup.homeTeam.teamName4 + ',' + 'BAT';
				option.text = session_match.setup.homeTeam.teamName3 + '-' + 'BAT';
				select.appendChild(option);
				
				option = document.createElement('option');
				option.value = session_match.setup.homeTeam.teamName4 + ',' + 'FIELD';
				option.text = session_match.setup.homeTeam.teamName3 + '-' + 'FIELD';
				select.appendChild(option);
				
				option = document.createElement('option');
				option.value = session_match.setup.awayTeam.teamName4 + ',' + 'BAT';
				option.text = session_match.setup.awayTeam.teamName3 + '-' + 'BAT';
				select.appendChild(option);
				
				option = document.createElement('option');
				option.value = session_match.setup.awayTeam.teamName4 + ',' + 'FIELD';
				option.text = session_match.setup.awayTeam.teamName3 + '-' + 'FIELD';
				select.appendChild(option);
				break;
			default:
				option = document.createElement('option');
				option.value = session_match.setup.homeTeam.teamName4 + '-' + 'BAT';
				option.text = session_match.setup.homeTeam.teamName3 + '-' + 'BAT';
				select.appendChild(option);
				
				option = document.createElement('option');
				option.value = session_match.setup.homeTeam.teamName4 + '-' + 'FIELD';
				option.text = session_match.setup.homeTeam.teamName3 + '-' + 'FIELD';
				select.appendChild(option);
				
				option = document.createElement('option');
				option.value = session_match.setup.awayTeam.teamName4 + '-' + 'BAT';
				option.text = session_match.setup.awayTeam.teamName3 + '-' + 'BAT';
				select.appendChild(option);
				
				option = document.createElement('option');
				option.value = session_match.setup.awayTeam.teamName4 + '-' + 'FIELD';
				option.text = session_match.setup.awayTeam.teamName3 + '-' + 'FIELD';
				select.appendChild(option);
				break;
			}
			
			
			
			select.setAttribute('onchange',"setDropdownOptionToSelectOptionArray(this, 0)");
			row.insertCell(cellCount).appendChild(select);
			setDropdownOptionToSelectOptionArray($(select),0);
			cellCount = cellCount + 1;
			break;
		case 'Control_Shift_U': 
			select = document.createElement('select');
			select.id = 'selectPlayer';
			select.name = select.id;
			
			/*session_match.match.inning.forEach(function(inn){
				if(inn.inningNumber == document.getElementById('which_inning').value){
					inn.battingCard.sort(function(a, b) {
				      if (b.runs === a.runs) {
						if(a.balls === b.balls){
							return b.fours - a.fours
					  	}
				        // If totalRuns are equal, sort by totalBalls (ascending)
				        return a.balls - b.balls;
				      }
				      // Otherwise, sort by totalRuns (descending)
				      return b.runs - a.runs;
				    });
					
					inn.battingCard.forEach(function(bc,bc_index,bc_arr){
						option = document.createElement('option');
						option.value = bc.playerId;
						option.text = bc.player.full_name + " - " + bc.status;	
						select.appendChild(option);
					});
				}
			});*/
			session_match.match.inning.forEach(function(inn) {
			    if (inn.inningNumber == document.getElementById('which_inning').value) {
			        inn.battingCard.sort(function(a, b) {
			            if (b.runs === a.runs) {
			                if (a.balls === b.balls) {
			                    return b.fours - a.fours;
			                }
			                return a.balls - b.balls;
			            }
			            return b.runs - a.runs;
			        });
			        select.innerHTML = "";
			        inn.battingCard.forEach(function(bc) {
			            let status = (bc.status || "").toUpperCase().replace(/\s+/g, "");
			            if (status === "STILLTOBAT") {
			                return;
			            }
			            let option = document.createElement("option");
			            option.value = bc.playerId;
			            option.text = bc.player.full_name + " - " + bc.status;
			            select.appendChild(option);
			        });
			    }
			});
			
			select.setAttribute('onchange',"setDropdownOptionToSelectOptionArray(this, 0)");
			row.insertCell(cellCount).appendChild(select);
			setDropdownOptionToSelectOptionArray($(select),0);
			removeSelectDuplicates(select.id);
			cellCount = cellCount + 1;
			
			select = document.createElement('select');
			select.id = 'selectStatsType';
			select.name = select.id;
			
			header_text.innerHTML = 'BAT POP UP';
			option = document.createElement('option');
			option.value = 'score';
			option.text = 'Score';
			select.appendChild(option);
			
			option = document.createElement('option');
			option.value = 'strikeRate';
			option.text = 'Strike Rate';
			select.appendChild(option);
			
			option = document.createElement('option');
			option.value = 'boundary';
			option.text = 'Boundary';
			select.appendChild(option);
			
			option = document.createElement('option');
			option.value = 'boundary_percent';
			option.text = 'Boundary %';
			select.appendChild(option);
			
			select.setAttribute('onchange',"setDropdownOptionToSelectOptionArray(this, 1)");
			row.insertCell(cellCount).appendChild(select);
			setDropdownOptionToSelectOptionArray($(select),1);
			cellCount = cellCount + 1;
			
			select = document.createElement('select');
			select.id = 'selectsponsor';
			select.name = select.id;
			
			header_text.innerHTML = 'BAT POP UP';
			option = document.createElement('option');
			option.value = 'without';
			option.text = 'without sponsor';
			select.appendChild(option);
			
			option = document.createElement('option');
			option.value = 'with';
			option.text = 'with sponsor';
			select.appendChild(option);
			
			select.setAttribute('onchange',"setDropdownOptionToSelectOptionArray(this, 2)");
			row.insertCell(cellCount).appendChild(select);
			setDropdownOptionToSelectOptionArray($(select),2);
			cellCount = cellCount + 1;
			break;
		case 'Control_Shift_V':
			
			select = document.createElement('select');
			select.id = 'selectPlayer';
			select.name = select.id;
			
			session_match.match.inning.forEach(function(inn){
				if(inn.inningNumber == document.getElementById('which_inning').value){
					inn.bowlingCard.sort(function(a, b) {
				      if (b.wickets === a.wickets) {
						if(a.economyRate === b.economyRate){
							return b.dots - a.dots;
					  	}
				        return a.economyRate - b.economyRate;
				      }
				      return b.wickets - a.wickets;
				    });
					
					inn.bowlingCard.forEach(function(boc,boc_index,bc_arr){
						option = document.createElement('option');
						option.value = boc.playerId;
						option.text = boc.player.full_name;	
						select.appendChild(option);
					});
				}
			});
			
			select.setAttribute('onchange',"setDropdownOptionToSelectOptionArray(this, 0)");
			row.insertCell(cellCount).appendChild(select);
			setDropdownOptionToSelectOptionArray($(select),0);
			removeSelectDuplicates(select.id);
			cellCount = cellCount + 1;
			
			select = document.createElement('select');
			select.id = 'selectStatsType';
			select.name = select.id;
			header_text.innerHTML = 'BALL POP UP';
			
			option = document.createElement('option');
			option.value = 'figure';
			option.text = 'Bowler Figure';
			select.appendChild(option);
			
			option = document.createElement('option');
			option.value = 'economy';
			option.text = 'Economy';
			select.appendChild(option);
			
			option = document.createElement('option');
			option.value = 'dot_percent';
			option.text = 'Dot Ball %';
			select.appendChild(option);
			
			select.setAttribute('onchange',"setDropdownOptionToSelectOptionArray(this, 1)");
			row.insertCell(cellCount).appendChild(select);
			setDropdownOptionToSelectOptionArray($(select),1);
			cellCount = cellCount + 1;
			
			select = document.createElement('select');
			select.id = 'selectsponsor';
			select.name = select.id;
			
			header_text.innerHTML = 'BAT POP UP';
			option = document.createElement('option');
			option.value = 'without';
			option.text = 'without sponsor';
			select.appendChild(option);
			
			option = document.createElement('option');
			option.value = 'with';
			option.text = 'with sponsor';
			select.appendChild(option);
			
			select.setAttribute('onchange',"setDropdownOptionToSelectOptionArray(this, 2)");
			row.insertCell(cellCount).appendChild(select);
			setDropdownOptionToSelectOptionArray($(select),2);
			cellCount = cellCount + 1;
			break;
		case 'Shift_F':
			header_text.innerHTML = 'WICKET SEQUENCE';
			select = document.createElement('select');
			select.id = 'selectWicketSequence';
			select.name = select.id;
			
			session_match.match.inning.forEach(function(inn){
				if(inn.inningNumber == document.getElementById('which_inning').value){
					inn.fallsOfWickets.forEach(function(fow,fow_index,fow_arr){
						inn.battingCard.forEach(function(bc,bc_index,bc_arr){
							if(fow.fowPlayerID == bc.playerId){
								option = document.createElement('option');
								option.value = bc.playerId;
								option.text = bc.player.full_name + " - " + bc.status;	
								select.appendChild(option);
							}
						});
					});
				}
			});
			
			select.setAttribute('onchange',"setDropdownOptionForWicketSequence(this, 0)");
			row.insertCell(cellCount).appendChild(select);
			setDropdownOptionForWicketSequence(0);
			cellCount = cellCount + 1;
			break;
		case 'Control_Shift_J':
			select = document.createElement('select');
			select.style = 'width:400px';
			select.id = 'selectPerformanceBugdb';
			select.name = select.id;
			
			dataToProcess.forEach(function(bug){
				option = document.createElement('option');
				option.value = bug.bugId;
				option.text = bug.prompt;
				select.appendChild(option);
			});
			select.setAttribute('onchange',"setDropdownOptionToSelectOptionArray(this, 0)");
			row.insertCell(cellCount).appendChild(select);
			setDropdownOptionToSelectOptionArray($(select),0);
			cellCount = cellCount + 1;
			$('#selectPerformanceBugdb').select2();
			break;
		 case 'Control_Shift_L': //MATCH-PROMO - PreviousMatchSummary 
			
			header_text.innerHTML = 'MATCH PROMO';
			select = document.createElement('select');
			select.id = 'selectMatchPromo';
			select.name = select.id;
			console.log(Array.isArray(dataToProcess), dataToProcess.length);

			dataToProcess.forEach(function(oop){	
				option = document.createElement('option');
	            option.value = oop.matchnumber;
	            option.text = oop.matchnumber + ' - ' +oop.home_Team.teamName1 + ' Vs ' + oop.away_Team.teamName1 ;
	            select.appendChild(option);
	        });
	        
			select.setAttribute('onchange',"setDropdownOptionToSelectOptionArray(this, 0)");
			row.insertCell(cellCount).appendChild(select);
			setDropdownOptionToSelectOptionArray($(select),0);
			
			cellCount = cellCount + 1;
			break;
		case 'Control_Shift_O':
			header_text.innerHTML = 'LINEUP';
			select = document.createElement('select');
			select.id = 'selectTeams';
			select.name = select.id;
			
			option = document.createElement('option');
			option.value = session_match.setup.homeTeamId;
			option.text = session_match.setup.homeTeam.teamName1;
			select.appendChild(option);
			
			option = document.createElement('option');
			option.value = session_match.setup.awayTeamId;
			option.text = session_match.setup.awayTeam.teamName1;
			select.appendChild(option);
			
			select.setAttribute('onchange',"setDropdownOptionToSelectOptionArray(this, 0)");
			row.insertCell(cellCount).appendChild(select);
			setDropdownOptionToSelectOptionArray($(select),0);
			cellCount = cellCount + 1;
			
			select = document.createElement('select');
			select.id = 'selectlineUp';
			select.name = select.id;
			
			option = document.createElement('option');
            option.value = 'BATTING_CARD';
            option.text = 'BATTING CARD';
            select.appendChild(option);
            
            option = document.createElement('option');
            option.value = 'ROLES';
            option.text = 'ROLES';
            select.appendChild(option);
			
			select.setAttribute('onchange',"setDropdownOptionToSelectOptionArray(this, 1)");
			row.insertCell(cellCount).appendChild(select);
			setDropdownOptionToSelectOptionArray($(select),1);
			cellCount = cellCount + 1;
			break;
		case 'Control_Shift_Q':
			header_text.innerHTML = 'Generic';
		
			select = document.createElement('select');
			select.id = 'selectGeneric';
			select.name = select.id;
			
			option = document.createElement('option');
			option.value = 'Curr_Part';
			option.text = 'Current Partnership';
			select.appendChild(option);
			
			option = document.createElement('option');
			option.value = 'last_wicket';
			option.text = 'Last Wicket';
			select.appendChild(option);
			
			option = document.createElement('option');
			option.value = 'Boundaries';
			option.text = 'Boundaries';
			select.appendChild(option);
			
			option = document.createElement('option');
			option.value = 'CRR';
			option.text = 'Current Run rate';
			select.appendChild(option);
			
			session_match.match.inning.forEach(function(inn){
				if(inn.isCurrentInning == 'YES'){
					if(inn.inningNumber == 2){
						option = document.createElement('option');
						option.value = 'RRR';
						option.text = 'Required Run Rate';
						select.appendChild(option);
						
						option = document.createElement('option');
						option.value = 'CRR_RRR';
						option.text = 'Current and Required Run Rate';
						select.appendChild(option);
						
						/*option = document.createElement('option');
						option.value = 'Runs_Balls';
						option.text = 'Runs And Balls';
						select.appendChild(option);*/
						
						option = document.createElement('option');
						option.value = 'Comparison';
						option.text = 'Comparison';
						select.appendChild(option);
					}
				}
			});
			
			select.setAttribute('onchange',"setDropdownOptionToSelectOptionArray(this, 0)");
			row.insertCell(cellCount).appendChild(select);
			setDropdownOptionToSelectOptionArray($(select),0);
			cellCount = cellCount + 1
			break;
		case 'Alt_F9':
			header_text.innerHTML = 'SINGLE TEAMS CAREER';
			
			select = document.createElement('select');
			select.id = 'selectTeams';
			select.name = select.id;
			session_match.match.inning.forEach(function(inn){
				if(inn.isCurrentInning == 'YES'){
					option = document.createElement('option');
					option.value = inn.battingTeamId;
					option.text = inn.batting_team.teamName1;
					select.appendChild(option);
					
					option = document.createElement('option');
					option.value = inn.bowlingTeamId;
					option.text = inn.bowling_team.teamName1;
					select.appendChild(option);
				}
			});
			select.setAttribute('onchange',"setDropdownOptionToSelectOptionArray(this, 0)");
			row.insertCell(cellCount).appendChild(select);
			setDropdownOptionToSelectOptionArray($(select),0);
			cellCount = cellCount + 1;
			
			select = document.createElement('select');
			select.id = 'selectStyle';
			select.name = select.id;
			
			option = document.createElement('option');
			option.value = 'age';
			option.text = 'Age';
			select.appendChild(option);
			
			option = document.createElement('option');
			option.value = 'batting';
			option.text = 'Batting';
			select.appendChild(option);
			
			option = document.createElement('option');
			option.value = 'bowling';
			option.text = 'Bowling';
			select.appendChild(option);
			
			select.setAttribute('onchange',"setDropdownOptionToSelectOptionArray(this, 1)");
			row.insertCell(cellCount).appendChild(select);
			setDropdownOptionToSelectOptionArray($(select),1);
			cellCount = cellCount + 1
			
			select = document.createElement('select');
			select.id = 'selectType';
			select.name = select.id;
			
			option = document.createElement('option');
			option.value = 'runs';
			option.text = 'Runs';
			select.appendChild(option);
			
			option = document.createElement('option');
			option.value = 's/r';
			option.text = 'Strike Rate';
			select.appendChild(option);
			
			option = document.createElement('option');
			option.value = 'wickets';
			option.text = 'Wickets';
			select.appendChild(option);
			
			option = document.createElement('option');
			option.value = 'economy';
			option.text = 'Economy';
			select.appendChild(option);
			
			select.setAttribute('onchange',"setDropdownOptionToSelectOptionArray(this, 2)");
			row.insertCell(cellCount).appendChild(select);
			setDropdownOptionToSelectOptionArray($(select),2);
			cellCount = cellCount + 1
			break;	
		case 'Control_Shift_F7': case 'Shift_T':
			header_text.innerHTML = 'FF PLAYING XI';
			select = document.createElement('select');
			select.id = 'selectTeams';
			select.name = select.id;
			session_match.match.inning.forEach(function(inn){
				if(inn.isCurrentInning == 'YES'){
					option = document.createElement('option');
					option.value = inn.battingTeamId;
					option.text = inn.batting_team.teamName1;
					select.appendChild(option);
					
					option = document.createElement('option');
					option.value = inn.bowlingTeamId;
					option.text = inn.bowling_team.teamName1;
					select.appendChild(option);
				}
			});
			select.setAttribute('onchange',"setDropdownOptionToSelectOptionArray(this, 0)");
			row.insertCell(cellCount).appendChild(select);
			setDropdownOptionToSelectOptionArray($(select),0);
			cellCount = cellCount + 1;
			
			break;			
		case 'Control_Shift_F1': case 'Control_Shift_F4':
			select = document.createElement('select');
			select.id = 'selectPlayer';
			select.name = select.id;
			
			session_match.match.inning.forEach(function(inn){
				if(inn.inningNumber == document.getElementById('which_inning').value){
					inn.battingCard.sort(function(a, b) {
				      if (b.runs === a.runs) {
						if(a.balls === b.balls){
							return b.fours - a.fours
					  	}
				        // If totalRuns are equal, sort by totalBalls (ascending)
				        return a.balls - b.balls;
				      }
				      // Otherwise, sort by totalRuns (descending)
				      return b.runs - a.runs;
				    });
					
					inn.battingCard.forEach(function(bc,bc_index,bc_arr){
						option = document.createElement('option');
						option.value = bc.playerId;
						option.text = bc.player.full_name + " - " + bc.status;	
						select.appendChild(option);
					});
				}
			});
			
			select.setAttribute('onchange',"setDropdownOptionToSelectOptionArray(this, 0)");
			row.insertCell(cellCount).appendChild(select);
			setDropdownOptionToSelectOptionArray($(select),0);
			removeSelectDuplicates(select.id);
			cellCount = cellCount + 1;
			
			select = document.createElement('select');
			select.id = 'selectStatsType';
			select.name = select.id;

			header_text.innerHTML = 'BAT PERFORMER/PARTNERSHIP';
			option = document.createElement('option');
			option.value = 'performer';
			option.text = 'Performer';
			select.appendChild(option);
			
			switch(whatToProcess) {
				case 'Control_Shift_F1':
					option = document.createElement('option');
					option.value = 'partnership';
					option.text = 'Partnership';
					select.appendChild(option);
				break;
			}
			

			select.setAttribute('onchange',"setDropdownOptionToSelectOptionArray(this, 1)");
			row.insertCell(cellCount).appendChild(select);
			setDropdownOptionToSelectOptionArray($(select),1);
			cellCount = cellCount + 1;
			break;
		case 'z': case 'x': case 'c': case 'v': case 'Control_z': case 'Control_x': case 'Control_Shift_Z':
		case 'Control_Shift_Y':
			switch(whatToProcess) {
			case 'z':
				header_text.innerHTML = 'LEADERBOARD - MOST RUNS';
				break;
			case 'x':
				header_text.innerHTML = 'LEADERBOARD - MOST WICKETS';
				break;	
			case 'c':
				header_text.innerHTML = 'LEADERBOARD - MOST FOURS';
				break;	
			case 'v':
				header_text.innerHTML = 'LEADERBOARD - MOST SIXES';
				break;
			case 'Control_z':
			    header_text.innerHTML = 'LEADERBOARD - HIGHEST SCORES';
				break;		
			case 'Control_x':
			    header_text.innerHTML = 'LEADERBOARD - BEST FIGURES';
				break;
			case 'Control_Shift_Z':
				header_text.innerHTML = 'LEADERBOARD - BEST STRIKE RATE'
				break;
			case 'Control_Shift_Y':
				header_text.innerHTML = 'LEADERBOARD - BEST ECONOMY'
				break;	
			}
			let num = 0;
			
			switch(whatToProcess){
				case 'Control_Shift_Y':
					select = document.createElement('select');
					select.id = 'selectPlayerName';
					select.name = select.id;
					num = 0;
					
					for(i=0;i<dataToProcess.length;i++){
						if(dataToProcess[i].ballsBowled>=30){
							if(num<5){
								option = document.createElement('option');
					            option.value = (num+1)+ "_" + dataToProcess[i].playerId;
					            option.text = dataToProcess[i].player.full_name;
					            select.appendChild(option);
					            num++;
							}
						}
					}	
					
					select.setAttribute('onchange',"setDropdownOptionToSelectOptionArray(this, 0)");
					row.insertCell(cellCount).appendChild(select);
					setDropdownOptionToSelectOptionArray($(select),0);
					cellCount = cellCount + 1
					break;
				case 'Control_Shift_Z':
					select = document.createElement('select');
					select.id = 'selectPlayerName';
					select.name = select.id;
					num = 0;
					for(i=0;i<dataToProcess.length;i++){
						if(dataToProcess[i].ballsFaced>=30){
							if(num<5){
								option = document.createElement('option');
					            option.value = (num+1)+ "_" + dataToProcess[i].playerId;
					            option.text = dataToProcess[i].player.full_name;
					            select.appendChild(option);
					            num++;
							}
						}
					}
					select.setAttribute('onchange',"setDropdownOptionToSelectOptionArray(this, 0)");
					row.insertCell(cellCount).appendChild(select);
					setDropdownOptionToSelectOptionArray($(select),0);
					cellCount = cellCount + 1
				
				break;
				
				default:
					select = document.createElement('select');
					select.id = 'selectPlayerName';
					select.name = select.id;
					num = 0;
					for(i=0;i<dataToProcess.length;i++){
						if(num<5){
							option = document.createElement('option');
				            option.value = (num+1)+ "_" + dataToProcess[i].playerId;
				            option.text = dataToProcess[i].player.full_name;
				            select.appendChild(option);
				            num++;
						}
					}
					select.setAttribute('onchange',"setDropdownOptionToSelectOptionArray(this, 0)");
					row.insertCell(cellCount).appendChild(select);
					setDropdownOptionToSelectOptionArray($(select),0);
					cellCount = cellCount + 1
				break;
			}
			
			select = document.createElement('select');
			select.id = 'selectmtch';
			select.name = select.id;
			
			option = document.createElement('option');
			option.value = 'WITH_CURRENT';
			option.text = 'WITH CURRENT MATCH';
			select.appendChild(option);
						
			option = document.createElement('option');
			option.value = 'WITHOUT_CURRENT';
			option.text = 'WITHOUT CURRENT MATCH';
			select.appendChild(option);
			
			select.setAttribute('onchange',"setDropdownOptionToSelectOptionArray(this, 1)");
			row.insertCell(cellCount).appendChild(select);
			setDropdownOptionToSelectOptionArray($(select),1);
			cellCount = cellCount + 1;
			
			break;	
		case 'Control_Shift_F2': case 'Control_Shift_F5':
			select = document.createElement('select');
			select.id = 'selectPlayer';
			select.name = select.id;
			
			session_match.match.inning.forEach(function(inn){
				if(inn.inningNumber == document.getElementById('which_inning').value){
					inn.bowlingCard.sort(function(a, b) {
				      if (b.wickets === a.wickets) {
						if(a.economyRate === b.economyRate){
							return b.dots - a.dots;
					  	}
				        return a.economyRate - b.economyRate;
				      }
				      return b.wickets - a.wickets;
				    });
					
					inn.bowlingCard.forEach(function(boc,boc_index,bc_arr){
						option = document.createElement('option');
						option.value = boc.playerId;
						option.text = boc.player.full_name;	
						select.appendChild(option);
					});
				}
			});
			
			select.setAttribute('onchange',"setDropdownOptionToSelectOptionArray(this, 0)");
			row.insertCell(cellCount).appendChild(select);
			setDropdownOptionToSelectOptionArray($(select),0);
			removeSelectDuplicates(select.id);
			cellCount = cellCount + 1;
			
			select = document.createElement('select');
			select.id = 'selectStatsType';
			select.name = select.id;

			header_text.innerHTML = 'BALL PERFORMER';
			option = document.createElement('option');
			option.value = 'performer';
			option.text = 'Performer';
			select.appendChild(option);

			select.setAttribute('onchange',"setDropdownOptionToSelectOptionArray(this, 1)");
			row.insertCell(cellCount).appendChild(select);
			setDropdownOptionToSelectOptionArray($(select),1);
			cellCount = cellCount + 1;
			break;
		case 'Shift_P':
			header_text.innerHTML = 'BATSMAN THIS SERIES';
			select = document.createElement('select');
			select.id = 'selectPlayerName';
			select.name = select.id;
			
			session_match.match.inning.forEach(function(inn){
				if(inn.inningNumber == document.getElementById('which_inning').value){
					inn.battingCard.forEach(function(bc){
						if(bc.status == 'NOT OUT'){
							if(bc.onStrike == 'YES'){
								option = document.createElement('option');
								option.value = bc.player.playerId;
								option.text = bc.player.full_name;
								select.appendChild(option);
							}else{
								option = document.createElement('option');
								option.value = bc.player.playerId;
								option.text = bc.player.full_name;
								select.appendChild(option);
							}
						}
					});
					
					if(inn.battingTeamId == session_match.setup.homeTeamId){
						session_match.setup.homeSquad.forEach(function(hs){
							option = document.createElement('option');
							option.value = hs.playerId;
							option.text = hs.full_name;
							select.appendChild(option);
						});
						session_match.setup.homeOtherSquad.forEach(function(hos){
							option = document.createElement('option');
							option.value = hos.playerId;
							option.text = hos.full_name  + ' (OTHER)';
							select.appendChild(option);
						});
					}else {
						session_match.setup.awaySquad.forEach(function(as){
							option = document.createElement('option');
							option.value = as.playerId;
							option.text = as.full_name;
							select.appendChild(option);
						});
						session_match.setup.awayOtherSquad.forEach(function(aos){
							option = document.createElement('option');
							option.value = aos.playerId;
							option.text = aos.full_name  + ' (OTHER)';
							select.appendChild(option);
						});
					}
				}
			});

			select.setAttribute('onchange',"setDropdownOptionToSelectOptionArray(this, 0)");
			row.insertCell(cellCount).appendChild(select);
			removeSelectDuplicates(select.id);
			setDropdownOptionToSelectOptionArray($(select),0);
			cellCount = cellCount + 1;
			
			select = document.createElement('select');
			select.id = 'selectType';
			select.name = select.id;
			
			option = document.createElement('option');
			option.value = 'WITH_CURRENT';
			option.text = 'With Current Match';
			select.appendChild(option);
			
			option = document.createElement('option');
			option.value = 'WITHOUT_CURRENT';
			option.text = 'Without Current Match';
			select.appendChild(option);
			
			select.setAttribute('onchange',"setDropdownOptionToSelectOptionArray(this, 1)");
			row.insertCell(cellCount).appendChild(select);
			setDropdownOptionToSelectOptionArray($(select),1);
			cellCount = cellCount + 1;
			
			
			select = document.createElement('select');
			select.id = 'selectImage';
			select.name = select.id;
			
			option = document.createElement('option');
			option.value = 'WITHOUT';
			option.text = 'WithOut Image';
			select.appendChild(option);
			
			option = document.createElement('option');
			option.value = 'WITH';
			option.text = 'With Image';
			select.appendChild(option);
			
			select.setAttribute('onchange',"setDropdownOptionToSelectOptionArray(this, 2)");
			row.insertCell(cellCount).appendChild(select);
			setDropdownOptionToSelectOptionArray($(select),2);
			cellCount = cellCount + 1
				
			break;
		case 'Shift_Q'://Bowler milestone
			
			header_text.innerHTML = 'BOWLER THIS SERIES';
			select = document.createElement('select');
			select.id = 'selectPlayerNames';
			select.name = select.id;
			
			session_match.match.inning.forEach(function(inn){
				if(inn.inningNumber == document.getElementById('which_inning').value){
					if(inn.bowlingCard != null){
						inn.bowlingCard.forEach(function(boc){
							if(boc.status != null){
								if(boc.status == 'CURRENTBOWLER'){
									option = document.createElement('option');
									option.value = boc.player.playerId;
									option.text = boc.player.full_name;
									select.appendChild(option);
								}
							}
						});
					}
					
					if(inn.bowlingTeamId == session_match.setup.homeTeamId){
						session_match.setup.homeSquad.forEach(function(hs){
							option = document.createElement('option');
							option.value = hs.playerId;
							option.text = hs.full_name;
							select.appendChild(option);
						});
						session_match.setup.homeOtherSquad.forEach(function(hos){
							option = document.createElement('option');
							option.value = hos.playerId;
							option.text = hos.full_name  + ' (OTHER)';
							select.appendChild(option);
						});
					}else {
						session_match.setup.awaySquad.forEach(function(as){
							option = document.createElement('option');
							option.value = as.playerId;
							option.text = as.full_name;
							select.appendChild(option);
						});
						session_match.setup.awayOtherSquad.forEach(function(aos){
							option = document.createElement('option');
							option.value = aos.playerId;
							option.text = aos.full_name  + ' (OTHER)';
							select.appendChild(option);
						});
					}
				}
			});

			select.setAttribute('onchange',"setDropdownOptionToSelectOptionArray(this, 0)");
			row.insertCell(cellCount).appendChild(select);
			removeSelectDuplicates(select.id);
			setDropdownOptionToSelectOptionArray($(select),0);
			cellCount = cellCount + 1;
			
			select = document.createElement('select');
			select.id = 'selectTypes';
			select.name = select.id;
			
			option = document.createElement('option');
			option.value = 'WITH_CURRENT';
			option.text = 'With Current Match';
			select.appendChild(option);
			
			option = document.createElement('option');
			option.value = 'WITHOUT_CURRENT';
			option.text = 'Without Current Match';
			select.appendChild(option);
			
			select.setAttribute('onchange',"setDropdownOptionToSelectOptionArray(this, 1)");
			row.insertCell(cellCount).appendChild(select);
			setDropdownOptionToSelectOptionArray($(select),1);
			cellCount = cellCount + 1;
			
			select = document.createElement('select');
			select.id = 'selectImages';
			select.name = select.id;
			
			option = document.createElement('option');
			option.value = 'WITHOUT';
			option.text = 'WithOut Image';
			select.appendChild(option);
			
			option = document.createElement('option');
			option.value = 'WITH';
			option.text = 'With Image';
			select.appendChild(option);
			
			select.setAttribute('onchange',"setDropdownOptionToSelectOptionArray(this, 2)");
			row.insertCell(cellCount).appendChild(select);
			setDropdownOptionToSelectOptionArray($(select),2);
			cellCount = cellCount + 1
			
			break;
		case 'Alt_F1':
			header_text.innerHTML = 'BAT GRIFF';
			select = document.createElement('select');
			select.id = 'selectBatsmanThisMatch';
			select.name = select.id;
			
			session_match.match.inning.forEach(function(inn){
				if(inn.inningNumber == document.getElementById('which_inning').value){
					inn.battingCard.forEach(function(bc){
						if(bc.status == 'NOT OUT'){
							if(bc.onStrike == 'YES'){
								option = document.createElement('option');
								option.value = bc.player.playerId;
								option.text = bc.player.full_name;
								select.appendChild(option);
							}else{
								option = document.createElement('option');
								option.value = bc.player.playerId;
								option.text = bc.player.full_name;
								select.appendChild(option);
							}
						}
					});
					
					if(inn.battingTeamId == session_match.setup.homeTeamId){
						session_match.setup.homeSquad.forEach(function(hs){
							option = document.createElement('option');
							option.value = hs.playerId;
							option.text = hs.full_name;
							select.appendChild(option);
						});
						session_match.setup.homeOtherSquad.forEach(function(hos){
							option = document.createElement('option');
							option.value = hos.playerId;
							option.text = hos.full_name  + ' (OTHER)';
							select.appendChild(option);
						});
					}else {
						session_match.setup.awaySquad.forEach(function(as){
							option = document.createElement('option');
							option.value = as.playerId;
							option.text = as.full_name;
							select.appendChild(option);
						});
						session_match.setup.awayOtherSquad.forEach(function(aos){
							option = document.createElement('option');
							option.value = aos.playerId;
							option.text = aos.full_name  + ' (OTHER)';
							select.appendChild(option);
						});
					}
				}
			});
			
			select.setAttribute('onchange',"setDropdownOptionToSelectOptionArray(this, 0)");
			row.insertCell(cellCount).appendChild(select);
			//removeSelectDuplicates(select.id);
			setDropdownOptionToSelectOptionArray($(select),0);
			removeSelectDuplicates(select.id);
			cellCount = cellCount + 1;
			break;
			
		case 'Alt_F2':
			header_text.innerHTML = 'BALL GRIFF';
			select = document.createElement('select');
			select.id = 'selectBatsmanThisMatch';
			select.name = select.id;
			
			session_match.match.inning.forEach(function(inn){
				if(inn.inningNumber == document.getElementById('which_inning').value){
					inn.bowlingCard.forEach(function(boc){
						if(boc.status == 'CURRENTBOWLER'){
							option = document.createElement('option');
							option.value = boc.player.playerId;
							option.text = boc.player.full_name;
							select.appendChild(option);
						}
					});
					
					if(inn.bowlingTeamId == session_match.setup.homeTeamId){
						session_match.setup.homeSquad.forEach(function(hs){
							option = document.createElement('option');
							option.value = hs.playerId;
							option.text = hs.full_name;
							select.appendChild(option);
						});
						session_match.setup.homeOtherSquad.forEach(function(hos){
							option = document.createElement('option');
							option.value = hos.playerId;
							option.text = hos.full_name  + ' (OTHER)';
							select.appendChild(option);
						});
					}else {
						session_match.setup.awaySquad.forEach(function(as){
							option = document.createElement('option');
							option.value = as.playerId;
							option.text = as.full_name;
							select.appendChild(option);
						});
						session_match.setup.awayOtherSquad.forEach(function(aos){
							option = document.createElement('option');
							option.value = aos.playerId;
							option.text = aos.full_name  + ' (OTHER)';
							select.appendChild(option);
						});
					}
				}
			});
			
			select.setAttribute('onchange',"setDropdownOptionToSelectOptionArray(this, 0)");
			row.insertCell(cellCount).appendChild(select);
			//removeSelectDuplicates(select.id);
			setDropdownOptionToSelectOptionArray($(select),0);
			removeSelectDuplicates(select.id);
			cellCount = cellCount + 1;
			break;					
		}
		
		if((whatToProcess == 'Control_Shift_U' || whatToProcess == 'Control_Shift_V')){
			option = document.createElement('input');
			option.type = 'button';
			option.name = 'pop_up_change_on';
			option.value = 'Change On';
		    option.id = option.name;
		    option.setAttribute('onclick','processUserSelection(this)');
		    
		    div = document.createElement('div');
		    div.append(option);
		    row.insertCell(cellCount).appendChild(div);
	    	cellCount = cellCount + 1;
		}
		
		if(whatToProcess == 'Control_F5' || whatToProcess == 'Control_F9'){
			option = document.createElement('input');
			option.type = 'button';
			option.name = 'change_on_to_stats';
			option.value = 'Change On To Stats';
		    option.id = option.name;
		    option.setAttribute('onclick','processUserSelection(this)');
		    
		    div = document.createElement('div');
		    div.append(option);
		    row.insertCell(cellCount).appendChild(div);
	    	cellCount = cellCount + 1;
	    	
	    	option = document.createElement('input');
			option.type = 'hidden';
			option.name = 'key_press_hidden_input';
			option.id = option.name;
			option.value = whatToProcess;
	
		    div.append(option);
		    
		    row.insertCell(cellCount).appendChild(div);
		    cellCount = cellCount + 1;
		}
		
		if(whatToProcess == 'Alt_3' || whatToProcess == 'Alt_4'){
			option = document.createElement('input');
			option.type = 'button';
			option.name = 'checkPlayerData';
			option.value = 'Preview';
		    option.id = option.name;
		    option.setAttribute('onclick','processUserSelection(this)');
		    
		    div = document.createElement('div');
		    div.append(option);
		    row.insertCell(cellCount).appendChild(div);
	    	cellCount = cellCount + 1;
	    	
	    	option = document.createElement('input');
			option.type = 'hidden';
			option.name = 'key_press_hidden_input';
			option.id = option.name;
			option.value = whatToProcess;
	
		    div.append(option);
		    
		    row.insertCell(cellCount).appendChild(div);
		    cellCount = cellCount + 1;
		}
		
		switch(whatToProcess){
		case 'F12': case 'Alt_1': case 'Alt_2': case 'Alt_6': case 'Alt_7': case 'Alt_0':case 'Alt_8':case "Alt_3": case 'Alt_4': case 'Control_F5': case 'Control_F9':
		case "Control_F12": case "Shift_F12":case "Alt_5": case 'Control_Shift_F1': case 'F8': case 'Alt_F8': case 'F10': case 'F6': case 'Shift_F6':
		case 'F7': case 'Control_d': case 'F11': case 'Control_s': case 'Control_f': case 'u': case 'F5': case 'F9': case 'Shift_F9': case 'Shift_F5':
		case 'Alt_F12': case 'Shift_E': case 'y': case 'k': case 'g': case 'h': case 'Control_y': case 'Shift_O': case 'Shift_F4': case 'Alt_p':
		case 'Control_Shift_U': case 'Control_Shift_V': case 'Control_d': case 'Control_e': case 'Control_m': case 'Shift_F': case 'Control_Shift_J':
		case 'Control_Shift_L': case 'Control_Shift_O': case 'Control_Shift_Q': case 'Control_Shift_F7': case 'Control_Shift_F2': case 'Shift_T':
		case 'Control_Shift_*': case 'Alt_F9': case 'Shift_F11': case 'z': case 'x': case 'c': case 'v': case 'Control_Shift_F4': case 'Control_Shift_F5': case 'Shift_P': 
		case 'Shift_Q': case 'Alt_F1': case 'Alt_F2': case 'Control_z': case 'Control_x': case 'Control_Shift_Z': case 'Control_c': case 'Control_Shift_X': 
		case 'Control_Shift_K': case 'Control_F11': case 'Control_Shift_Y': case 'Shift_C': case 'Control_p': case 'Alt_F7': case 'l': case 'Alt_Shift_F4':
		case 'Alt_d': case 'r': case 'Control_Shift_D': case 'Alt_z':
			option = document.createElement('input');
			option.type = 'button';
			option.name = 'populate_btn';
			option.value = 'Populate Data';
		    option.id = option.name;
		    option.setAttribute('onclick','processUserSelection(this)');
		    
		    div = document.createElement('div');
		    div.append(option);
		    
			option = document.createElement('input');
			option.type = 'button';
			option.name = 'cancel_graphics_btn';
			option.id = option.name;
			option.value = 'Cancel';
			option.setAttribute('onclick','processUserSelection(this)');
		    div.append(option);
	
			option = document.createElement('input');
			option.type = 'hidden';
			option.name = 'key_press_hidden_input';
			option.id = option.name;
			option.value = whatToProcess;
	
		    div.append(option);
		    
		    row.insertCell(cellCount).appendChild(div);
		    cellCount = cellCount + 1;
		    break;
		}
		
		document.getElementById('select_graphic_options_div').style.display = '';
		break;
	}
}
function setPlayerDropdown(dataToProcess) {
 	const playerCell = document.getElementById('Player');
 	playerCell.innerHTML = ''; 	
    const playerSelect = document.createElement('select');
    playerSelect.id = 'playerDropdown';
    
    dataToProcess.forEach(player => {
        const option = document.createElement('option');
        option.value = player.playerID;  
        option.text = player.player; 
        playerSelect.appendChild(option);
      });

	playerSelect.selectedIndex = 0;
    $(playerSelect).on('change', function() {
		setDropdownOptionToSelectOptionArray($(this), 1);
    });        
    playerCell.appendChild(playerSelect);
	setDropdownOptionToSelectOptionArray($(this), 1);
	$(playerSelect).trigger('change');
}
function ChallengeScore(match) {
    let Inning1Run = 0, Inning2Run = 0;
    
    match.eventFile.events.forEach(event => {
        
        if (event.eventType.toLowerCase() === "log_50_50") {
            
            if (event.eventExtra.trim().toLowerCase() === "+") {
                
                if (event.eventInningNumber === 1) {
                    Inning1Run += event.eventExtraRuns;
                } else if (event.eventInningNumber === 2) {
                    Inning2Run += event.eventExtraRuns;
                }
                
            } else if (event.eventExtra.trim().toLowerCase() === "-") {
                if (event.eventInningNumber === 1) {
                    Inning1Run -= event.eventExtraRuns;
                } else if (event.eventInningNumber === 2) {
                    Inning2Run -= event.eventExtraRuns;
                }
                
            }
        }
    });
     Inning1Run += match.match.inning[0].totalRuns;
     Inning2Run += match.match.inning[1].totalRuns;
    return `${Inning1Run},${Inning2Run}`;
}
