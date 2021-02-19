package exercise; 
import java.io.*; 
import java.util.*; 

public class Result{
	private int position; 
	private String name; 
	private String team;
	
	public Result(int position, String name, String team){
		this.position = position; 
		this.name = name; 
		this.team = team; 
	}
	public String toString(){
		return "Position:" + this.position + " name:" + this.name + " team:" + this.team + "\n"; 
	}
	public int getPosition(){
		return position;
	}
	public String getName(){
		return name;
	}
	public String getTeam(){
		return team;
	}
	
}