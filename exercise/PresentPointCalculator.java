package exercise; 

public class PresentPointCalculator extends PointCalculator{ 
	Integer[] points = {25, 18, 15, 12, 10, 8, 6, 4, 2, 1};  
	@Override
	public double getPoint(int position, Boolean isFastest){
		if(position <= points.length){
			if(isFastest){
				return points[position-1] + 1;
			}
			else{
				return points[position-1]; 
			}	
		}
		else{
			return 0; 
		}
	}
}