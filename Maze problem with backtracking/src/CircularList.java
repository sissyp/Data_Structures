import java.io.PrintStream;
import java.util.NoSuchElementException;
public class CircularList<T>{
	private ListNode<T> lastNode;//circular list uses only one pointer which points to the last node
	private String name; //the name of the data structure
	private int size; //size of the list
	
	public CircularList(){
		this("circular list");  //constructor that creates an empty circular list named circular list
	}
	public CircularList(String listName){
		name=listName;
		this.lastNode=null; //constructor that creates a circular list and initializes last node 
		this.size=0;
	}
	
	public void insert(T insertItem){           //insertion at the back of the list
		ListNode<T> temp=new ListNode<>(insertItem);
		this.size+=1;
		if (lastNode == null) {
			lastNode=temp;                    //insert the new element at the back of the list
			lastNode.nextNode=lastNode;      //lastNode points at itself 
			return ;
		}
		else{
			temp.nextNode = lastNode.nextNode;  //new node points at the first node
			lastNode.nextNode = temp;          //last node points at the new node
			lastNode = temp;                  //last node's value is the new node's value
		}
	}
	
	public T remove() throws NoSuchElementException{ //removes the element from the front of the list
		if (isEmpty())   //throws exception if the list is empty
			throw new NoSuchElementException();
		else{
			T removedItem=lastNode.nextNode.data; //the item we want to remove is the value of the first node
			if (size==1)
				lastNode = null; //removes the only element in the list
			else
				lastNode.nextNode=(lastNode.nextNode).nextNode; //last node points to the second node which is now the first
		size-=1;
        return removedItem;	
		}
	}
	
	public T firstItem()throws NoSuchElementException{ //returns the first item of the list
		if (isEmpty()) //throws exception if the list is empty
			throw new NoSuchElementException();
		else
			return (lastNode.nextNode).data;//otherwise we return firstNode
	}
	
	public boolean isEmpty(){
		return lastNode==null; // returns true if the list is empty
	}
	
	public void printList(PrintStream stream){ //prints the items of a list to the print stream given as argument.
		if (isEmpty()){
			stream.print(name+" is empty ");
			return ;
		}
		else{
		System.out.printf("the %s is: ", name);
		ListNode<T> currentNode=lastNode.nextNode;//we start from the firstNode
			do{
			  stream.print(" "+currentNode.data);
			  currentNode=currentNode.nextNode;//moves to the next node
			}
			while(currentNode!=lastNode.nextNode);
		}
		stream.println();
		
	}

	public int size(){  //returns the number of elements currently in the list
		return this.size;
	}
}	