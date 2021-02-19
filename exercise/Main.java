package exercise; 
import java.io.*; 
import java.util.*; 

public class Main {
    public static void main(String[] args) throws InputException{
		File in = new File("input-hf.txt");
		ArrayList<Race> raceList = new ArrayList<Race>();
		int rowNum = 1; 
		Race newRace = new Race();
		Query query  = new Query();
		List<InputException> errors = new ArrayList<InputException>();
		ReadInput readInput = new ReadInput();
		readInput.readInput(in, raceList,rowNum, errors, newRace, query);
		
	}
}