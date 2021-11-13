import java.io.PrintStream;
import java.util.NoSuchElementException;

public class StringStackImpl<T> extends List<T> implements StringStack<T>{
	//methods isEmpty() and size are inherited from super class List
	
	public StringStackImpl(){
		super("stack");
	}
	public void push(T item){
		insertAtFront(item);
	}
	
	public T pop() throws NoSuchElementException{
		return removeFromFront();
	}
	
	public T peek() throws NoSuchElementException{
		return firstItem();
	}
	
	public void printStack(PrintStream stream){
		printList(stream);
	}
}
	
	