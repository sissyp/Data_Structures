public class Rectangle {
	protected int xmin, ymin, xmax, ymax;
	
	Rectangle(int xmin, int ymin, int xmax,int ymax){ //constructor that initializes the
		this.xmin=xmin;                              //minimum and maximum value of x and y
		this.ymin=ymin;                             //coordinates
		this.xmax=xmax;
		this.ymax=ymax;
	}
	
	public int xmin(){ // minimum x-coordinate of rectangle
		return xmin;
	}
	
	public int ymin(){ // minimum y-coordinate of rectangle
		return ymin;
	}
	public int xmax(){ // maximum x-coordinate of rectangle
		return xmax;
	}
	public int ymax(){ // maximum y-coordinate of rectangle
		return ymax;
	}
	public boolean contains(Point p){ //if p belongs to the rectangle return true
		if(p.x<=xmax && p.y<=ymax)
			return true;
		return false;
	}
	public boolean intersects(Rectangle that){ // returns true if the 2 rectangles intersect
		// If one rectangle is on left side of other  
        if (this.xmin() > that.xmax() || that.xmin() > this.xmax()) { 
            return false; 
        } 
  
        // If one rectangle is above other  
        if (this.ymax() < that.ymin() || that.ymax() < this.ymin()) { 
            return false; 
        } 
  
        return true; 
	}
		
	public double distanceTo(Point p){ // Euclidean distance from p to closest point in rectangle
		int dx = Math.max(this.xmin - p.x,Math.max(0, p.x - this.xmax));
		int dy = Math.max(this.ymin - p.y,Math.max(0, p.y - this.ymax));
		return Math.sqrt(dx*dx + dy*dy);
	}
 
	public int squareDistanceTo(Point p){ // square of Euclidean distance from p to closest point in rectangle
		int dx = Math.max(this.xmin - p.x, Math.max(0, p.x - this.xmax));
		int dy = Math.max(this.ymin - p.y, Math.max(0, p.y - this.ymax));
		return dx*dx + dy*dy;
	}
	public String toString(){ // string representation:[xmin, xmax] x [ymin, ymax]
		return "[" + xmin + ", " + xmax +" x " + "["+ymin+", "+ymax + "]";
	}
}