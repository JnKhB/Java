package exercise; 

public class NewPointCalculator extends PointCalculator{ 
	Integer[] points = {25, 18, 15, 12, 10, 8, 6, 4, 2, 1};  
	@Override
	public double getPoint(int position, Boolean isfastest){
		if(position <= points.length){
			return points[position-1];
		}
		else{
			return 0; 
		}
	}
}