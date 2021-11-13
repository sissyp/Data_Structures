public class Node{
	private int x,y;
	private int direction;
	
	public Node(int i, int j){
		x=i;
		y=j;
		this.direction=0;
	}
	
	public int getX()  
    { 
        return x; 
    } 
  
    public void setX(int x)  
    { 
        this.x = x; 
    } 
  
    public int getY()  
    { 
        return y; 
    } 
  
    public void setY(int y) 
    { 
        this.y = y; 
    } 
  
    public int getDirection()  
    { 
        return direction; 
    } 
  
    public void setDirection(int direction)  
    { 
        this.direction = direction; 
    } 
} 
	