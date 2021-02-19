package exercise; 
import java.io.*; 
import java.util.*; 


public class Race{
	private int year; 
	private String raceName;
	private int raceNum; 
	private double multiplier; 
	private ArrayList<Result> resultList;
	private String fastestName;
	private String fastestTeam;
	public Race(){
		
	}
	public Race(int year, String raceName, int raceNum, double multiplier, ArrayList<Result> resultList){
		this.year = year; 
		this.raceName = raceName; 
		this.raceNum = raceNum; 
		this.multiplier = multiplier;
		this.resultList = resultList; 
	}
	@Override
	public String toString(){
		return "Year:" + getYear() + " RaceName:" +	getRaceName() + " RaceNum:" + getRaceNum() + " Multiplier:" + getMultiplier() + "\n resultList:" + getResults(); 
	}
	public void addResultToList(Result result){
		resultList.add(result);
	}
	public void setFastest(String name, String team){
		fastestName = name; 
		fastestTeam = team;
	}
	public int getYear(){
		return year;
	}
	public String getRaceName(){
		return raceName;
	}
	public int getRaceNum(){
		return raceNum;
	}
	public String getFastestName(){
		return fastestName;
	}
	public double getMultiplier(){
		return multiplier;
	}
	public ArrayList<Result> getResults(){
		return resultList;
	}
	public void SetParam(int year, String raceName, int raceNum, double multiplier, ArrayList<Result> resultList)
	{
		this.year = year; 
		this.raceName = raceName; 
		this.raceNum = raceNum; 
		this.multiplier = multiplier;
		this.resultList = resultList;
	}
	
	
}