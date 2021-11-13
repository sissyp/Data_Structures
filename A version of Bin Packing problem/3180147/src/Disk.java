public class Disk implements Comparable<Disk>{
	protected int id;   //each object has a unique id
	protected List<Integer> folders;  //list of folders stored in the disk
	protected int freeSpace;   //disk's free space
	
	public Disk(int id){  //constructor that creates a disk object
		this.id=id;  
		this.folders=new List<Integer>();
		this.freeSpace=1000000; //maximum value of free space 
	}
	
	public int getId(){ //returns the disk's id
		return id;
	}
	
	public int getFreeSpace(){ //returns the disk's free space
		return freeSpace;
	}
	
	public void setFreeSpace(int freeSpace){
		this.freeSpace=freeSpace;
	}
	
	@Override  //compares two disks and returns an integer
	public int compareTo(Disk B){
		if (this.getFreeSpace()>B.getFreeSpace()) //this refers to the current object
			return 1;
		else if (this.getFreeSpace()<B.getFreeSpace())
			return -1;
		else
			return 0;
	}
}
	
	