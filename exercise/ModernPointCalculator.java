package exercise; 

public class ModernPointCalculator extends PointCalculator{ 
	Integer[] points = {10, 8, 6, 5, 4, 3, 2, 1};  
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