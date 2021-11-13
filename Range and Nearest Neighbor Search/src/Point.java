public class Point {
	protected int x,y;
	Point(int x, int y){ //constructor that initializes x and y coordinates
		this.x = x;
		this.y = y;
	}
	public int x(){ //returns the value of x
		return x;
	}
	public int y(){ //returns the value of y
		return y;
	}
	public double distanceTo(Point z){ //Euclidean distance between two points
		int dx = x - z.x;
		int dy = y - z.y;
		return Math.sqrt(dx*dx + dy*dy); 
	}
	 public int squareDistanceTo(Point z){ //square of the Euclidean distance between two points
		 int dx = x - z.x;
		 int dy = y - z.y;
		 return dx*dx + dy*dy;
		}
	 public String toString(){ // string representation: (x, y)
		 return "(" + x + "," + y + ")"; 
		} 
}