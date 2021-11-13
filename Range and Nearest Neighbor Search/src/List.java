import java.io.PrintStream;
import java.util.NoSuchElementException;
public class List<T> {
	private ListNode<T> firstNode;
	private ListNode<T> lastNode;
	private String name; //the name of the data structure
	
	public List(){
		this("list");  //constructor that creates an empty list named list
	}
	public List(String listName){
		name=listName;
		firstNode=lastNode=null; //constructor that creates a list and initializes first and last node 
	}
	
	public void insertAtFront(T insertItem){
		if (isEmpty())
			firstNode=lastNode=new ListNode<T>(insertItem); // first and last node refer to the same object
		else
			firstNode= new ListNode<T>(insertItem,firstNode); //firstnode refers to the new node 
	}
	
	public void insertAtBack(T insertItem){
		if (isEmpty())
			firstNode=lastNode=new ListNode<T>(insertItem); // first and last node refer to the same object
		else
			lastNode=lastNode.nextNode=new ListNode<T>(insertItem); // the next node of lastnode refers to the new object
	}
	
	public T removeFromFront() throws NoSuchElementException{
		if (isEmpty()) //throws exception if the list is empty
			throw new NoSuchElementException();
			
		T removedItem= firstNode.data;  
		if(firstNode==lastNode)
			firstNode=lastNode=null; //since we removed the only item that was in the list , first and last node refer to null
		else
			firstNode=firstNode.nextNode; //the next node of the firstnode becomes the firstnode
		return removedItem;
	}
	
	public T firstItem()throws NoSuchElementException{ //returns the first item of the list
		if (isEmpty()) //throws exception if the list is empty
			throw new NoSuchElementException();
			
		return firstNode.data;
	}
	
	public T removeFromBack() throws NoSuchElementException{
		if (isEmpty()) //throws exception if the list is empty
			throw new NoSuchElementException();
			
		T removedItem= lastNode.data;
		if(firstNode==lastNode)
			firstNode=lastNode=null; //since we removed the only item that was in the list , first and last node refer to null
		else {// finds out the new last node 
			ListNode<T> currentNode=firstNode;
			while(currentNode.nextNode!=lastNode)
				currentNode=currentNode.nextNode;
			lastNode=currentNode;
			currentNode.nextNode=null;
		}
		return removedItem;
	}
 
	public boolean isEmpty(){
		return firstNode==null; // returns true if the list is empty
	}
	
	public void printList(PrintStream stream){ //prints the items of a list to the print stream given as argument.
		if (isEmpty()){
			stream.print(name+" is empty ");
			return ;
		}
		System.out.printf("the %s is: ", name);
		ListNode<T> currentNode=firstNode;
			while(currentNode!=null){  
				stream.print(" "+currentNode.data);
				currentNode=currentNode.nextNode;
			}
		stream.println();
	}
	
	public int size(){  //returns the number of elements currently in the list
		if (isEmpty())
			return 0;
		ListNode<T> currentNode=firstNode;
		int numOfElements=1;
			while(currentNode.nextNode!=null){ 
				currentNode=currentNode.nextNode;
				numOfElements++;
			}
		return numOfElements;
	}
}
				