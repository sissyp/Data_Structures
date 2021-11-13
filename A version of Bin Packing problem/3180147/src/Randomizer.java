import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.io.BufferedReader;
import java.io.FileReader;

public class Randomizer{
	public static void main(String[]args) throws IOException{
		try {
				int n; //number of folders
				for(int j=0;j<30;j++){
					BufferedWriter writer = new BufferedWriter(new FileWriter(new File("random"+j+".txt")));
					if(j<=9)
						n=100; // the first 10 text files contain 100 folders
					else if (j<=19)
						n=500; // the next 10 text files contain 500 folders
					else
						n=1000; //the last 10 text files contain 1000 folders
					for(int i=0;i<n;i++){
						int line; 
						Random random = new Random();//creates random numbers
						line = random.nextInt(1000001);//between 0 and 1000000
						writer.write(String.valueOf(line));
						writer.newLine();
					}	
				writer.flush();
				writer.close();
				}
				Greedy.print=true;//in order to print only number of disks used
				int s1=0; int s2=0; int s3=0;//for n=100, n=500 and n=1000
				System.out.println("Greedy1");//without heapsort 
				int []sum=new int[30];
				int []sum1=new int[30];
				for(int j=0;j<30;j++){
					Greedy.numOfDisks=0; //reads data from the random texts files we created above
					List<Integer> f=new List<Integer>();
					Scanner sc = new Scanner(new BufferedReader(new FileReader(("random"+j+".txt"))));
					while(sc.hasNextLine()) {
						String l=sc.nextLine();
						f.insertAtBack(Integer.parseInt(l));//and stores them in a list
					 }
					System.out.println("for file random"+j+".txt");
					Greedy.printData(f); //calls function printData to print the results
					sum[j]=Greedy.numOfDisks;
				}
				for(int i=0;i<30;i++){
					if(i<=9)
						s1+=sum[i];
					else if(i<=19)
						s2+=sum[i];
					else
						s3+=sum[i];
				}
				System.out.println("Greedy1: average number of disks used for N=100: "+s1/10);
				System.out.println("Greedy1: average number of disks used for N=500: "+s2/10);
				System.out.println("Greedy1: average number of disks used for N=1000: "+s3/10);
				
				s1=0;s2=0;s3=0; //for n =100,n=500 and n=1000
				Greedy.sort=true; //heapsort
				System.out.println("Greedy2");
				for(int j=0;j<30;j++){ //we do the same thing for Greedy2
					Greedy.numOfDisks=0;
					List<Integer> f1=new List<Integer>();
					Scanner sc = new Scanner(new BufferedReader(new FileReader(("random"+j+".txt"))));
					while(sc.hasNextLine()) {
						String l=sc.nextLine();
						f1.insertAtBack(Integer.parseInt(l));
					 }
					System.out.println("for file random"+j+".txt");
					Greedy.printData(f1);
					sum[j]=Greedy.numOfDisks;
				}
				for(int i=0;i<30;i++){
					if(i<=9)
						s1+=sum[i];
					else if(i<=19)
						s2+=sum[i];
					else
						s3+=sum[i];
				}
				System.out.println("Greedy2: average number of disks used for N=100: "+s1/10);
				System.out.println("Greedy2: average number of disks used for N=500: "+s2/10);
				System.out.println("Greedy2: average number of disks used for N=1000: "+s3/10);
			}
		catch (IOException e) {
            System.out.println("error writing file");
        }
    }
}