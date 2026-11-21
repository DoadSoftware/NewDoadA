package com.cricket.containers;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PredictorData {

	@JsonProperty("Overs")
    private String overs;

    @JsonProperty("TotalRuns")
    private int totalRuns;

    @JsonProperty("Wickets")
    private int wickets;

    @JsonProperty("Target")
    private int target;

    @JsonProperty("Predictor")
    private String predictor;

    @JsonProperty("Team")
    private String team;

    @JsonProperty("PredictedScore")
    private String predictedScore;

	public String getOvers() {
		return overs;
	}

	public void setOvers(String overs) {
		this.overs = overs;
	}

	public int getTotalRuns() {
		return totalRuns;
	}

	public void setTotalRuns(int totalRuns) {
		this.totalRuns = totalRuns;
	}

	public int getWickets() {
		return wickets;
	}

	public void setWickets(int wickets) {
		this.wickets = wickets;
	}

	public int getTarget() {
		return target;
	}

	public void setTarget(int target) {
		this.target = target;
	}

	public String getPredictor() {
		return predictor;
	}

	public void setPredictor(String predictor) {
		this.predictor = predictor;
	}

	public String getTeam() {
		return team;
	}

	public void setTeam(String team) {
		this.team = team;
	}

	public String getPredictedScore() {
		return predictedScore;
	}

	public void setPredictedScore(String predictedScore) {
		this.predictedScore = predictedScore;
	}

}