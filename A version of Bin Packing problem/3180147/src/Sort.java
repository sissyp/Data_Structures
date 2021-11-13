import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.File;

public class Sort{
	public static void HeapSort(int[] array){
		
		int n = array.length; 
        for (int i = n / 2 -1; i >= 0; i--) //transforms array into a heap
            heapify(array, n, i);       //builds a max heap from the input data
  
         
        for (int i=n-1; i>=0; i--) { //moves root to the end 
            int temp = array[0]; 
            array[0] = array[i]; 
            array[i] = temp; 
 
            heapify(array, i, 0); //calls function to heapify the root of the tree 
        } 
    } 

    public static void heapify(int array[], int n, int i) { 
        int max = i; // Initialize max as root 
        int left = 2*i + 1; // left child  = 2*i + 1 
        int right = 2*i + 2; // right child = 2*i + 2 
  
        // If left child is greater than root 
        if (left < n && array[left] > array[max]) 
            max = left; 
  
        // If right child is greater than max
        if (right < n && array[right] > array[max]) 
            max = right; 
  
        // If largest is not the root swap the elements
        if (max != i){ 
            int swap = array[i]; 
            array[i] = array[max]; 
            array[max] = swap; 
  
            // Recursively heapify the affected sub-tree 
            heapify(array, n, max); 
        } 
    }
	
	public static void main(String[]args)throws FileNotFoundException{
		try{
			Path path=Paths.get(args[0]);
			Path fileName=path.getFileName();
		    List<Integer> f1=new List<Integer>();
			Scanner sc = new Scanner(new BufferedReader(new FileReader(fileName.toString())));
			while(sc.hasNextLine()) {
				String l=sc.nextLine();
				f1.insertAtBack(Integer.parseInt(l));
			 }
			 Greedy.sort=true;
			 Greedy.printData(f1);
		}
		catch (FileNotFoundException f){
		   System.out.println("file not found");
	    }
	}
		
}