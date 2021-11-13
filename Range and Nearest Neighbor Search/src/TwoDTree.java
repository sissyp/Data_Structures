import java.util.Comparator;
import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.io.FileNotFoundException;
public class TwoDTree {
	protected boolean b=false; //used in search in order to find whether a tree contains a specific element
	protected static double distance_p=0; //distance between two points
	private class TreeNode {
		Point item; // an object of the class Point
		TreeNode l; // pointer to left subtree
		TreeNode r; // pointer to right subtree
		TreeNode(Point item){ //constructor that initializes item
			this.item=item;
		}
	}
	 private TreeNode head; //root of the tree
	 
	 public TwoDTree(){ // construct an empty tree
		head=null;
	 }
	 public boolean isEmpty(){ // is the tree empty?
		return(head==null);
	 }
	 public int size(){ // number of points in the tree
		return(size(head)); 
	 }
	 private int size(TreeNode node) { //returns the size of the tree
		if (node == null) return(0); 
		else { 
			return(size(node.l) + 1 + size(node.r)); 
		} 
	} 
	//comparator used to compare the x coordinate of two points
	private final Comparator<TreeNode> cmpX = new Comparator<TreeNode>() {

        @Override
        public int compare(TreeNode p1, TreeNode p2) {
            return Integer.compare(p1.item.x(), p2.item.x());
        }
    };
	//comparator used to compare the y coordinate of two points
    private final Comparator<TreeNode> cmpY = new Comparator<TreeNode>() {

        @Override
        public int compare(TreeNode p1, TreeNode p2) {
            return Integer.compare(p1.item.y(), p2.item.y());
        }
    };
	 public void insert(Point p){ // inserts the point p to the tree
		TreeNode node= new TreeNode(p);
		if (head == null) //if tree contains no elements
            head = node;
        else{
			if(!search(node.item)) //if tree doesn't contain the item
				insert(node, head, true); //inserts the item in the suitable position 
			else
				System.out.println("item already exists "); //else prints a message
		}
    }
	//helper function to insert an element in a tree
	private void insert(TreeNode node, TreeNode currentNode, boolean div) {
        if (node == null)
            return;
		//comparator that chooses whether to compare x or y coordinates of two points
        int cmp = (div ? cmpX : cmpY).compare(node, currentNode);
        if (cmp == -1) //if less(node,currentNode)
            if(currentNode.l == null) //continues to the left sub-tree
                currentNode.l = node;
            else
                insert(node, currentNode.l, !div);
        else
        if(currentNode.r == null) //else continues to the right sub-tree
            currentNode.r = node;
        else
            insert(node, currentNode.r, !div);
    }
			
	 public boolean search(Point p){ //search if p exists in tree
		TreeNode t= new TreeNode(p);
		 return searchR(head,t,true);
		 
	 }
	 //helper function in order to search an element in a tree
	 private boolean searchR(TreeNode h, TreeNode t,boolean div){
		 if (h == null) 
			return false;
		//comparator that chooses whether to compare x or y coordinates of two points
		int cmp = (div ? cmpX : cmpY).compare(h, t);
		 if (cmpX.compare(h,t)==0 && cmpY.compare(h,t)==0){ //if equals(h,t) point is found
			 return true;
		 }
		 else if (cmp == -1){ //continues to the right sub-tree if less(h,t)
			  b=searchR(h.r, t,!div);
		 }
		 else if(cmp==1){ //else continues to the left sub-tree
			 b=searchR(h.l,t,!div);
		 }
		 return b;
	 }
	
	 public Point nearestNeighbor(Point p){ // point in the tree that is closest to p (null if tree is empty)
		Rectangle rec=new Rectangle(0,0,100,100);
		return nearestNeighbor(head, p, rec, null);
	 }
	 //helper function to find the closest point of the tree to the point given
	 private Point nearestNeighbor(TreeNode n,Point p, Rectangle rec, Point close){
		 double distance_r=0; //distance between the point and the rectangle
		 boolean div=true; //to choose whether to compare the elements using x or y coordinate
		 Rectangle rec_l,rec_r=null; // left and right rectangles
		 
		 if(n==null)
			 return close;
		 if(close!=null){ //calculates the distances 
			 distance_p=p.squareDistanceTo(close);
			 distance_r=rec.squareDistanceTo(p);//distance between rectangle and the point
		 }

		 if((distance_p>distance_r)||(close==null)){
			 Point p2=new Point(n.item.x(),n.item.y());
			 
			 if((distance_p>p.squareDistanceTo(p2))||(close==null))
				 close=p2;
				 
			if(div){ //creates left and right rectangle with x-coordinate
				div=false;
				rec_l=new Rectangle(rec.xmin(),rec.ymin(),n.item.x(),rec.ymax());
				rec_r=new Rectangle(n.item.x(),rec.ymin(),rec.xmax(),rec.ymax());

				if(p.x()<n.item.x()){ //continues to the left and right sub-tree
					close=nearestNeighbor(n.l,p,rec_l,close);
					close=nearestNeighbor(n.r,p,rec_r,close);
				}
				
				else{
					close=nearestNeighbor(n.r,p,rec_r,close);
					close=nearestNeighbor(n.l,p,rec_l,close);
				}
			}
			else{
				div=true; //creates left and right rectangle with x-coordinate
				rec_l=new Rectangle(rec.xmin(),rec.ymin(),rec.xmax(),n.item.y());
				rec_r=new Rectangle(rec.xmin(),n.item.y(),rec.xmax(),rec.ymax());
				if(p.y()<n.item.y()){
					close=nearestNeighbor(n.l,p,rec_l,close);
					close=nearestNeighbor(n.r,p,rec_r,close);
				}
				else{
					close=nearestNeighbor(n.r,p,rec_r,close);
					close=nearestNeighbor(n.l,p,rec_l,close);
				}
			}
		 }
		
		return close;
	 }
			
	 public List<Point> rangeSearch(Rectangle rect){ // Returns a list with the Points that are contained in the rectangle
		List<Point> list=new List<Point>();
		boolean div=true;
		Rectangle rec=new Rectangle(0,0,100,100);//represents the rectangle of the root
		rangeSearch(head, rec,rect,div,list);
		list.printList(System.out); //list with the Points that are contained in the rectangle
        return list;
    }
	//helper function to find the points in a rectangle
	private void rangeSearch(TreeNode n, Rectangle rect1,Rectangle rect2,boolean div,List<Point> list) {
		if(n==null)
			return ;
        if (rect1.intersects(rect2))  { // if the two rectangles intersect 
            if(rect2.contains(n.item))  { 
                list.insertAtBack(n.item);//inserts the point at the back of the list 
			}
			if(div){ //create two rectangles using x-coordinate
				Rectangle rect3=new Rectangle(n.item.x(),rect1.ymin(),rect1.xmax(),rect1.ymax());
				Rectangle rect4=new Rectangle(rect1.xmin(),rect1.ymin(),n.item.x(),rect1.ymax());
				if (n.l != null) //searches in the left sub-tree
					rangeSearch(n.l, rect4,rect2,!div,list);
				if (n.r!=null) //searches in the right sub-tree
					rangeSearch(n.r, rect3,rect2,!div,list);
			}
			if(!div){ //create two rectangles using y-coordinate
				Rectangle rect3=new Rectangle(rect1.xmin(),n.item.y(),rect1.xmax(),rect1.ymax());
				Rectangle rect4=new Rectangle(rect1.xmin(),rect3.ymin(),rect3.xmax(),n.item.y());
				if (n.l != null)//searches in the left sub-tree
					rangeSearch(n.l, rect4,rect2,!div,list);
				if (n.r!=null)//searches in the right sub-tree
					rangeSearch(n.r, rect3,rect2,!div,list);
			}
        }
    }
	
	
	 public static void main(String args[]) throws FileNotFoundException{
	   try{
		  Path path=Paths.get(args[0]);
		  Path fileName=path.getFileName();
		  Scanner sc = new Scanner(new BufferedReader(new FileReader(fileName.toString())));
		  int numOfElements=Integer.parseInt(sc.nextLine());
		  String[][]elements= new String[numOfElements][2];
		  int lines=0;
		  while(sc.hasNextLine()) {
			if(lines<=numOfElements){
			  for(int i=0;i<numOfElements;i++){
				  if(sc.hasNextLine()){
					String[] line = sc.nextLine().trim().split(" ");
					lines+=1;
					for (int j=0; j<line.length; j++) {
						elements[i][j] = line[j];
					}
				}
			  }
			}
		  }
		  boolean exit=false;
		  if(lines!=numOfElements){
				System.out.println("wrong number of elements was given");
				exit=true;
		  }
		  for(int i=0;i<numOfElements;i++){
			  for(int j=0;j<2;j++){
				  if(Integer.parseInt(elements[i][j])<0 || Integer.parseInt(elements[i][j])>100){
					  exit=true;
				  System.out.println("wrong data given for (x,y)");
				  }
			  }
		  }
		  while(!exit){
			  TwoDTree tree= new TwoDTree();
			  for(int i=0;i<numOfElements;i++){
				Point p=new Point(Integer.parseInt(elements[i][0]),Integer.parseInt(elements[i][1]));
				tree.insert(p);
			  }
			  while(true){
				  System.out.println("1. Compute the size of the tree ");
				  System.out.println("2. Insert a new point ");
				  System.out.println("3. Search if a given point exists in the tree ");
				  System.out.println("4. Provide a query rectangle ");
				  System.out.println("5. Provide a query point ");
				  System.out.println("Please choose a number between 1 and 5 ");
				  Scanner input=new Scanner(System.in);
				  int choice=input.nextInt();
				  if(choice==1)
					  System.out.println("size of the tree is " +tree.size());
				  else if(choice==2){
					  System.out.println("give x ");
					  int x=input.nextInt();
					  System.out.println("give y ");
					  int y=input.nextInt();
					  Point point=new Point(x,y);
					  tree.insert(point);  
				  }
				  else if(choice==3){
					  System.out.println("give x ");
					  int x=input.nextInt();
					  System.out.println("give y ");
					  int y=input.nextInt();
					  Point point=new Point(x,y);
					  if(tree.search(point)==true)
						  System.out.println("point was found ");
					  
				  }
				  else if(choice==4){
					  System.out.println("give coordinates of the rectangle ");
					  Scanner scanner=new Scanner(System.in);
					  String input2 = scanner.nextLine();
					  String integers[] = input2.split(" ");
					  input2 = scanner.nextLine();
					  String integers2[] = input2.split(" ");
					  Rectangle rect=new Rectangle(Integer.parseInt(integers[0]),Integer.parseInt(integers2[0]),Integer.parseInt(integers[1]),Integer.parseInt(integers2[1]));
					  tree.rangeSearch(rect);
					}
				else if(choice==5){
					  System.out.println("give a query point ");
					  Scanner scanner=new Scanner(System.in);
					  String input3 = scanner.nextLine();
					  String integers1[] = input3.split(" ");
					  Point p3=new Point(Integer.parseInt(integers1[0]),Integer.parseInt(integers1[0]));
					  System.out.println(tree.nearestNeighbor(p3));
					  System.out.println("distance= "+distance_p);
				}
			}
	    }
	   }
	   catch (FileNotFoundException f){
		   System.out.println("file not found");
	   }
	 }
}