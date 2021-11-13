import java.io.PrintStream;
import java.util.NoSuchElementException;

public class StringQueueImpl<T> extends List<T> implements StringQueue<T>{
	//methods isEmpty() and size are inherited from super class List
	
	public StringQueueImpl(){
		super("queue");
	}
	public void put(T item){
		insertAtBack(item);
	}
	
	public T get() throws NoSuchElementException{
		return removeFromFront();
	}
	
	public T peek() throws NoSuchElementException{
		return firstItem();
	}
	
	public void printQueue(PrintStream stream){
		printList(stream);
	}
}