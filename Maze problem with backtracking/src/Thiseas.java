import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Scanner;
import java.io.FileNotFoundException;

public class Thiseas {
   public static void main(String args[]) throws FileNotFoundException{
	   try{
		  Path path=Paths.get(args[0]);
		  Path fileName=path.getFileName();
		  Scanner sc = new Scanner(new BufferedReader(new FileReader(fileName.toString())));
		  String[] dimensions=sc.nextLine().trim().split(" ") ;
		  int rows =Integer.parseInt(dimensions[0]) ;
		  int columns = Integer.parseInt(dimensions[1]);
		  char [][] myArray = new char [rows][columns];
		  String []entrance=sc.nextLine().trim().split(" ") ;
		  int entrance_x=Integer.parseInt(entrance[0]) ;
		  int entrance_y=Integer.parseInt(entrance[1]) ;
		  int lines=0;
		  int numberOfColumns=0;
		  while(sc.hasNextLine()) {
			 if(lines<=rows){
				 for (int i=0; i<myArray.length; i++) {
					if(sc.hasNextLine()){
						String[] line = sc.nextLine().trim().split(" ");
						lines+=1;
						numberOfColumns=line.length;
						for (int j=0; j<line.length; j++) {
						   myArray[i][j] = line[j].charAt(0);
						}
					}
				 }
			 }
		  }
		if(lines==rows && numberOfColumns==columns && myArray[entrance_x][entrance_y]=='E'){
		  int[][] exits=new int[rows*2+columns*2-5][2];
					int k=0; int l=0;
					for (int i = 0; i < myArray.length; i++){ 
						for (int j = 0; j < myArray[i].length; j++){ 
							if (myArray[i][j]=='0' &&  (i==0 || i==myArray.length-1 || j==0 || j==myArray[i].length-1)){
								exits[k][l]=i;
								exits[k][l+1]=j;
								k++;
							}
						} 
					}
			
			boolean [][]visited=new boolean[rows][columns];
			for (int i = 0; i < visited.length; i++){ 
				for (int j = 0; j < visited[i].length; j++){ 
					visited[i][j] = false; 
				} 
			} 
			StringStackImpl<Node> stack=new StringStackImpl<>();
			Node current = new Node(entrance_x,entrance_y);
			stack.push(current);
			boolean exit_found=false;
			
			while (!stack.isEmpty() && exit_found==false){ 
   
            current = stack.peek(); 
            int direction = current.getDirection(); 
            int x = current.getX(); 
            int y = current.getY(); 
			boolean found=false;
			
  
            stack.pop(); 
            stack.push(current); 
			
            //If we find an exit ,we print the path 
			for (int i = 0; i < exits.length; i++){
					if (x == exits[i][0] && y ==exits[i][1] && exits[i][0]!=0 && exits[i][1]!=0 ) { 
						System.out.println("exit found !! ");
						Node n=stack.peek();
						System.out.println("The exit's coordinates are: x="+ n.getX() + " y=" + n.getY()); 
						exit_found=true;
					} 
			}
			
			if (direction == 0 && found==false){  
			current.setDirection(current.getDirection() + 1);
			direction = current.getDirection();
			
                // Checking the up direction. 
                if (x - 1 >= 0 && myArray[x - 1][y] == '0' &&  !visited[x - 1][y]) { 
                    Node temp = new Node(x - 1, y); 
                    visited[x - 1][y] = true; 
                    stack.push(temp); 
					found=true;
                } 
            }
			 
            if (direction == 1 && found==false)  { 
			current.setDirection(current.getDirection() + 1);
			direction = current.getDirection();
                // Checking the left direction 
                if (y - 1 >= 0 && myArray[x][y - 1] == '0' &&  !visited[x][y - 1]) { 
                    Node temp = new Node(x, y - 1); 
                    visited[x][y - 1] = true; 
                    stack.push(temp); 
					found=true;
                } 
            } 
			
            if (direction == 2 && found==false ){  
			current.setDirection(current.getDirection() + 1);
			direction = current.getDirection();
                // Checking the down direction 
                if (x + 1 < rows && myArray[x + 1][y] == '0' && visited[x + 1][y]==false) { 
                    Node temp = new Node(x + 1, y); 
                    visited[x + 1][y] = true; 
                    stack.push(temp); 
					found=true;
                } 
            }  
			
            if (direction == 3 && found==false) {  
			current.setDirection(current.getDirection() + 1);
			direction = current.getDirection();
                // Checking the right direction 
                if (y + 1 < columns && myArray[x][y + 1] == '0' && !visited[x][y + 1]) { 
                    Node temp = new Node(x, y + 1); 
                    visited[x][y + 1] = true; 
                    stack.push(temp);
					found=true;					
                } 
            } 
            // If none of the directions can lead us to a path ,we go back 
            // to the path where we came from. 
            if (found==false){ 
                visited[current.getX()][current.getY()] = true; 
                stack.pop(); 
            } 
			current.setDirection(0);
			direction = current.getDirection();
        } 
  
        // If the stack is empty there is no path that can lead us to the exit
		if (stack.isEmpty())
			System.out.println("no path found ");
		}
		else
			System.out.println("wrong data");
	   }
	   catch (FileNotFoundException f){
		   System.out.println("file not found");
	   }
   }
}