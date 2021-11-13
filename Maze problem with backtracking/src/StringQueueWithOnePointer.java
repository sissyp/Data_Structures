import java.io.PrintStream;
import java.util.NoSuchElementException;
public class StringQueueWithOnePointer<T> extends CircularList<T> implements StringQueue<T> {
	//methods isEmpty() and size are inherited from super class CircularList
	
	public StringQueueWithOnePointer(){
		super("queue with one pointer");
	}
	public void put(T item){
		insert(item);
	}
	
	public T get() throws NoSuchElementException{
		return remove();
	}
	
	public T peek() throws NoSuchElementException{
		return firstItem();
	}
	
	public void printQueue(PrintStream stream){
		printList(stream);
	}
}
	