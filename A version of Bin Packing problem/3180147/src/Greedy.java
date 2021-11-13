import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.File;

public class Greedy{
	protected static int numOfDisks=0; //number of disks used
	protected static boolean print; //defines which data are going to be printed
	protected static boolean sort; //if sort==true, function HeapSort is called
	
	public static void printData(List<Integer> f){
		
		boolean exit=false; //if wrong data were given
		int []myArray = new int [f.size()];//copies the elements of the list into an array
		for(int i=0;i<myArray.length;i++){
			myArray[i]=f.removeFromFront();
		}
		for(int j=0;j<myArray.length;j++){ 
			if (myArray[j]<0 || myArray[j]>1000000)
				exit=true;
		}
		if (exit)
			System.out.println("wrong data");
		else{
			if(sort){ //function heapSort is called 
				Sort.HeapSort(myArray);//after heapsort largest element is at the end of the array
				int[] b = new int[myArray.length]; // because of that we reverse the elements of the array
				int j = myArray.length; 
				for (int i = 0; i < myArray.length; i++) { 
					b[j - 1] = myArray[i]; 
					j = j - 1;
				}
				myArray=b;
			}
			MaxPQ pq=new MaxPQ(); //creates a priority queue
			Disk[] d=new Disk[myArray.length]; //creates an array with Disk elements
			for(int i=0;i<myArray.length;i++){
				d[i]=new Disk(i);
			}
			int sum=0; //calculates sum of all folders
			for(int i=0;i<myArray.length;i++){
				sum+=myArray[i];
			}
		
			for (int j=0;j<myArray.length;j++){
				int l=0; boolean done=false;
				while(l<myArray.length && done==false){
						if(myArray[j]<=d[l].getFreeSpace()){ //if there is avalaible space 
							d[l].folders.insertAtBack(myArray[j]);//insert the item in the disk's list
							d[l].setFreeSpace(d[l].getFreeSpace()-myArray[j]);//sub folder's size from the disk's free space
							done=true;
						}
						else 
							l++;
				}
			}
			
			for(int i=0;i<d.length;i++){ //if the disk contains at least one folder we insert it into the queue
				if (d[i].getFreeSpace()!=1000000){
					pq.insert(d[i]);
					numOfDisks++; //increase number of disks 
				}
			}
			if(!print)
				System.out.println("Sum of all folders = " +sum+" TB");
			System.out.println("Total number of disks used = "+numOfDisks);
			if(myArray.length<100 && !print){
				while(pq.size!=0){
					Disk max=pq.getMax();//we use getMax in order to print the elements in a descendings order
					System.out.print("id "+max.getId() +" "+ max.getFreeSpace() +": ");
					max.folders.printList(System.out);
				}
		}
				
	}
	}
			
	public static void main(String[]args)throws FileNotFoundException{
		try{
			Path path=Paths.get(args[0]);//reads file name 
			Path fileName=path.getFileName();
		    Scanner sc = new Scanner(new BufferedReader(new FileReader(fileName.toString())));
			List<Integer> f=new List<Integer>();
			while(sc.hasNextLine()) {
				String l=sc.nextLine();
				f.insertAtBack(Integer.parseInt(l));//creates a list that contains the integers of the text file
			 }
			 printData(f);
		}
		catch (FileNotFoundException f){
		   System.out.println("file not found");
	    }
		
	}
}