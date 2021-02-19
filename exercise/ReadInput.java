package exercise; 
import java.io.*; 
import java.util.*; 

public class ReadInput{
	enum Status {RACEORQUERY, INRACE, INQUERY}
	enum PointType {CLASSIC, MODERN, NEW, PRESENT}
	private static Status status = Status.RACEORQUERY; 
	private int counterOfResults = 0; 
	private Boolean isExit = false;
	public ReadInput(){
		
	}
	public boolean checkIfPointType(String pointType){
		for (PointType value : PointType.values()) {
			if (value.name().equals(pointType)) {
				return true;
			}
		}
		return false;
    }
	public boolean isNumeric(String str){ 
		try {  
			Double.parseDouble(str);  
			return true;
		}
		catch(NumberFormatException e){  
			return false;  
		}  
	}
	public void readInput(File in, ArrayList<Race> raceList,int rowNum, List<InputException> errors, Race newRace, Query query)
	{
		
		try (BufferedReader reader = new BufferedReader(new FileReader(in))) {
			String line;
			while (null != (line = reader.readLine()) && !isExit) {
				rowNum++; 
				String[] data = line.split(";");
				checkInputData(data, rowNum, line);
				switch(data[0])
				{
					case "RACE": 
						newRace = new Race();
						ReadRaceLine(data, rowNum, newRace);
						break;
					case "QUERY": 
						query = new Query();
						ReadQueryLine(data, rowNum, errors, query);
						break;
					case "RESULT": 
						ReadResultLine(data, rowNum, newRace);
						break;
					case "FASTEST": 
						ReadFastestLine(data, rowNum, newRace);
						break;
					case "FINISH": 
						ReadFinishLine(data, rowNum, raceList, newRace);	
						break;
					case "POINT": 
						ReadPointLine(data, rowNum, raceList, query);
						break;
					case "EXIT": 
						isExit = true;
						break;
				}
			}
		}
		catch (IOException e) {
			System.out.println(e.getMessage());
		}
		catch (InputException e) {
			System.out.println(e.getMessage());
		}
		if(errors.size() > 0){
			for(int i = 0; i < errors.size(); i++){
				System.out.println(errors.get(i)); 
			}
		}
	}
	public void ReadRaceLine(String[] data, int rowNum, Race newRace) throws InputException{
		if(status.equals(Status.RACEORQUERY)){
			ArrayList<Result> resultList = new ArrayList<Result>(); 
			newRace.SetParam(Integer.parseInt(data[1]), data[2], Integer.parseInt(data[3]), Double.parseDouble(data[4]), resultList);
			status = Status.INRACE; 
		}
		else{
			throw new InputException("Error at line " + rowNum + ". Command " + data[0] + " is not allowed.");
		}
		
	}
	public static void ReadQueryLine (String[] data, int rowNum, List<InputException> errors, Query query)  throws InputException{
		
		if(status.equals(Status.RACEORQUERY)){
			if(data.length == 2){
				query.SetParam(Integer.parseInt(data[1]));
			}
			else if(data.length == 3){
				query.SetParam(Integer.parseInt(data[1]), Integer.parseInt(data[2]));
			}
			status = Status.INQUERY;
		}
		else if(status.equals(Status.INQUERY)){
			errors.add(new InputException("Error at line " + rowNum + ". Command " + data[0] + " is not allowed."));
			if(data.length == 2){
				query.SetParam(Integer.parseInt(data[1]));
			}
			else if(data.length == 3){
				query.SetParam(Integer.parseInt(data[1]), Integer.parseInt(data[2]));
			}
			status = Status.INQUERY;
		}
		else{
			throw new InputException("Error at line " + rowNum + ". Command " + data[0] + " is not allowed.");
		}
	}
	public void ReadResultLine(String[] data, int rowNum,  Race newRace) throws InputException{
		if(status.equals(Status.INRACE)){
			Result result = new Result(Integer.parseInt(data[1]), data[2], data[3]);
			newRace.addResultToList(result); 	
			counterOfResults++;
		}else{
			throw new InputException("Error at line " + rowNum + ". Command " + data[0] + " is not allowed.");
		}
	}
	public void ReadFastestLine(String[] data, int rowNum,  Race newRace) throws InputException{
		if(status.equals(Status.INRACE)){
			newRace.setFastest(data[1], data[2]);
		}else{
			throw new InputException("Error at line " + rowNum + ". Command " + data[0] + " is not allowed.");
		}
	}
	public void ReadFinishLine(String[] data, int rowNum, ArrayList<Race> raceList,  Race newRace) throws InputException{
		if(status.equals(Status.INRACE)){
			raceList.add(newRace);
			status = Status.RACEORQUERY; 
			counterOfResults = 0; 
		}
		else{
			throw new InputException("Error at line " + rowNum + ". Command " + data[0] + " is not allowed.");
		}
	}
	public void ReadPointLine(String[] data, int rowNum,ArrayList<Race> raceList, Query query) throws InputException{
		if(status.equals(Status.INQUERY)){
			
			query.setType(data[1]);
			query.printResult(raceList);
			status = Status.RACEORQUERY; 
		}else{
			throw new InputException("Error at line " + rowNum + ". Command " + data[0] + " is not allowed.");
		}
	}
	public void checkIfNumber(String str, int rowNum) throws InputException{
		 if(!isNumeric(str)){
			throw new InputException("Error at line " + rowNum + ". " + str + " must be a number.");
		}
	}
	public void checkInputData(String[] data, int rowNum, String line) throws InputException{
		switch(data[0])
		{
			case "RACE":
				if(data.length != 5){
				throw new InputException("Error at line " + rowNum + ". Number of elements are not correct.");
				}
				checkIfNumber(data[1], rowNum);
				checkIfNumber(data[3], rowNum);
				checkIfNumber(data[4], rowNum);
				break;
			case "QUERY":
				checkIfNumber(data[1], rowNum);
				if(data.length == 3){
					checkIfNumber(data[2], rowNum);
				}
				else if(data.length != 2 && data.length != 3){
					throw new InputException("Error at line " + rowNum + ". Number of elements are not correct.");
				}
				break;
			case "RESULT":
				if(data.length != 4){
					throw new InputException("Error at line " + rowNum + ". Number of elements are not correct.");
				}
				checkIfNumber(data[1], rowNum);
				break;
			case "FASTEST":
				if(data.length != 3){
					throw new InputException("Error at line " + rowNum + ". Number of elements are not correct.");
				}
				break;
			case "FINISH":
				if(data.length != 1){
					throw new InputException("Error at line " + rowNum + ". Number of elements are not correct.");
				}
				else if(counterOfResults < 10){
					throw new InputException("Error at line " + rowNum + ". Command " + data[0] + " is not allowed.");
				}
				break;
			case "POINT":
				if(data.length != 2){
					throw new InputException("Error at line " + rowNum + ". Number of elements are not correct.");
				}
				else if(!checkIfPointType(data[1])){
					throw new InputException("Error at line " + rowNum + ". " + data[1] + " is not correct.");
				}
				break;
			case "":
				break;
			case "EXIT":
				break;
			default: 
				throw new InputException("Error at line " + rowNum + ". Command " + data[0] + " is not allowed.");
		}
	}
}