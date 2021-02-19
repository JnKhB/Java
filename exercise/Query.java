package exercise; 
import java.io.*; 
import java.util.*; 


public class Query{
	private int year; 
	private int raceNum;
	private String type;
	private Boolean isYearQuery; 
	private HashMap<String, Double> finalResults = new HashMap<String, Double>();
	private PointCalculator pointCalculator; 
	public Query(){
		this.isYearQuery = null;
		this.type = null;
	}
	public Query(int year){
		this.year = year;
		this.isYearQuery = true;
		this.type = null;
	}
	public Query(int year, int raceNum){
		this.year = year;
		this.raceNum = raceNum;
		this.isYearQuery = false;
		this.type = null;
	}
	public void SetParam(int year){
		this.year = year;
		this.isYearQuery = true;
		this.type = null;
	}
	public HashMap<String, Double> getFinalResults(){
		return finalResults;
	}
	public void setFinalResults(HashMap<String, Double> givenHashMap){
		this.finalResults = givenHashMap;
	}
	public void SetParam(int year, int raceNum){
		this.year = year;
		this.raceNum = raceNum;	
		this.isYearQuery = false;
		this.type = null;
	}
	public void setType(String typeOfQuery) throws InputException{
		type = typeOfQuery;
		if(type.equals("CLASSIC")){
			pointCalculator = new ClassicPointCalculator();
		}
		else if(type.equals("MODERN")){
			pointCalculator = new ModernPointCalculator();
		}
		else if(type.equals("NEW")){
			pointCalculator = new NewPointCalculator();
		}
		else if(type.equals("PRESENT")){
			pointCalculator = new PresentPointCalculator();
		}
		else{
			throw new InputException("Type is not acceptable!");
		}
	}
	public String getType(){
		return type;
	}
	public Boolean isYearQuery(){
		return isYearQuery;
	}
	public int getYear(){
		return year; 
	}
	public int getRaceNum(){
		return raceNum; 
	}
	public void countPoint(Race race)
	{
		for(int i = 0; i < race.getResults().size(); i++){
			String key = race.getResults().get(i).getName();
			double point = pointCalculator.getPoint(race.getResults().get(i).getPosition(), key.equals(race.getFastestName())) * race.getMultiplier(); 
			if(getFinalResults().containsKey(key)){
				getFinalResults().put(key, getFinalResults().get(key) + point); 
			}
			else{
				getFinalResults().put(key, point); 
			}
		}
	}
	public void printResult(ArrayList<Race> raceList){
		for(Race race : raceList){
			if(race.getYear() == getYear()){
				if(isYearQuery() || race.getRaceNum() <= getRaceNum()){
					countPoint(race);
				}
			}									
		}
		if(isYearQuery()){
			System.out.println("NEW QUERY - Year:" + getYear());
		}
		else{
			System.out.println("NEW QUERY - Year:" + getYear() + " RaceNumber: " + getRaceNum());
		}
		List<String> result = sortByValue(getFinalResults());
		for(int i = 0; i < result.size(); i++)
		{
			System.out.println(result.get(i));
		}
		System.out.println("\n");
	}
	public static List<String> sortByValue(HashMap<String, Double> hashMapGiven) 
    {
        List<Map.Entry<String, Double> > list = 
               new LinkedList<Map.Entry<String, Double> >(hashMapGiven.entrySet()); 

        Collections.sort(list, new Comparator<Map.Entry<String, Double> >() { 
            public int compare(Map.Entry<String, Double> o1,  
                               Map.Entry<String, Double> o2) 
            { 
                return (o2.getValue()).compareTo(o1.getValue()); 
            } 
        }); 
        List<String> result = new ArrayList<String>();
        for (Map.Entry<String, Double> it : list) { 
            String concat = "NAME: " + it.getKey() + " - POINTS: " + it.getValue();
            result.add(concat);
        } 
        return result; 
    }

	

}