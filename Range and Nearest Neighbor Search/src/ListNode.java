import java.util.NoSuchElementException;
//class ListNode represents a list node 

class ListNode<T> {
	T data;                    //data for the specific node 
	ListNode<T> nextNode;     // reference to the next node 
	
	ListNode(T object){
		this(object, null);   //constructor that creates a ListNode that refers to an object
	}
		
	ListNode(T object, ListNode<T> node){
		data=object;
		nextNode=node;       //constructor that creates a ListNode that refers to
		                    // the specific object and to the next ListNode
	}
	
	T getData(){
		return data;        //returns a reference to the data of the node 
	}
	
	ListNode<T> getNextNode(){
		return nextNode;    //returns a reference to the next node 
	}
}
		
	
			
		
	
	