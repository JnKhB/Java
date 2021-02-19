package exercise; 

public class ClassicPointCalculator extends PointCalculator{ 
	Integer[] points = {10, 6, 4, 3, 2, 1};  
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