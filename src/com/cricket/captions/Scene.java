package com.cricket.captions;

import java.io.PrintWriter;
import java.util.List;

import com.cricket.model.Configuration;
import com.cricket.util.CricketFunctions;

public class Scene 
{
	public void LoadScene(String whatToProcess, List<PrintWriter> print_writers, 
		Configuration config) throws InterruptedException
	{
		CricketFunctions.DoadWriteCommandToAllViz("-1 SCENE CLEANUP\0", print_writers);
		CricketFunctions.DoadWriteCommandToAllViz("-1 IMAGE CLEANUP\0", print_writers);
		CricketFunctions.DoadWriteCommandToAllViz("-1 GEOM CLEANUP\0", print_writers);
		CricketFunctions.DoadWriteCommandToAllViz("-1 FONT CLEANUP\0", print_writers);
        CricketFunctions.DoadWriteCommandToAllViz("-1 IMAGE INFO\0", print_writers);
		
		switch (config.getBroadcaster().toUpperCase()) {
		case Constants.AFG_SL_SERIES:
			switch (whatToProcess) {
			case "FULL-FRAMERS":
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER SET_OBJECT SCENE*/Default/gfx_FullFrames\0", print_writers);
		        CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*SCENE_DATA INITIALIZE \0", print_writers);
		        CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*STAGE SHOW 0.0 \0", print_writers);
				break;
			case "OVERLAYS":
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER SET_OBJECT SCENE*/Default/gfx_Overlays\0", print_writers);
		        CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*SCENE_DATA INITIALIZE \0", print_writers);
		        CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*STAGE SHOW 0.0 \0", print_writers);
				break;
			case "PLOTTER":
				break;
			case "LOF_PLOTTER":
				break;	
			}
			break;
		case Constants.BAN_AFG_SERIES: case Constants.ACC:
			switch (whatToProcess) {
			case "FULL-FRAMERS":
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER SET_OBJECT SCENE*/Default/FullFrames\0", print_writers);
		        CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*SCENE_DATA INITIALIZE \0", print_writers);
		        CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*STAGE SHOW 0.0 \0", print_writers);
				break;
			case "OVERLAYS":
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER SET_OBJECT SCENE*/Default/Overlays\0", print_writers);
		        CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*SCENE_DATA INITIALIZE \0", print_writers);
		        CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*STAGE SHOW 0.0 \0", print_writers);
				break;
			case "PLOTTER":
				break;
			case "LOF_PLOTTER":
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER SET_OBJECT SCENE*/Default/FieldDimesnsion \0", print_writers);
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*SCENE_DATA INITIALIZE \0", print_writers);
		        CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*STAGE SHOW 0.0 \0", print_writers);
				break;	
			}
			break;
		case Constants.BCCI: case Constants.TRI_SERIES:  case Constants.MT20:
			switch (whatToProcess) {
			case "FULL-FRAMERS":
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER SET_OBJECT SCENE*/Default/gfx_FullFrames\0", print_writers);
		        CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*SCENE_DATA INITIALIZE \0", print_writers);
		        CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*BACK_LAYER*STAGE SHOW 0.0 \0", print_writers);
				break;
			case "OVERLAYS":
				CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER SET_OBJECT SCENE*/Default/gfx_Overlays\0", print_writers);
		        CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*SCENE_DATA INITIALIZE \0", print_writers);
		        CricketFunctions.DoadWriteCommandToAllViz("-1 RENDERER*FRONT_LAYER*STAGE SHOW 0.0 \0", print_writers);
				break;
			case "PLOTTER":
				break;
			case "LOF_PLOTTER":
				break;	
			}
			break;	
		}
	}	
}