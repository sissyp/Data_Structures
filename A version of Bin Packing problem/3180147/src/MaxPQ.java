public class MaxPQ {
	protected Disk[] heap; // using heap to implement priority queue
    protected int size; // size of the priority queue
	private static final int capacity = 4; // default capacity
    private static final int auto_grow = 4; // default auto grow
	
	public MaxPQ(){ //constructor that creates a priority queue
		this.heap=new Disk[capacity + 1]; //initializes the heap as an array
		this.size = 0; 
	}
		
	public void insert(Disk item) { //inserts an element at the end of the heap
	   if (size == heap.length - 1) //checks if there is available space
            grow(); // grows the heap using auto_grow
	   size++; 
       heap[size] = item;
       swim(size); //node's key is greater than his parent node's key 
    }
	
	public Disk peek() { //returns the root without removing it from the heap
		if (size == 0)
			return null;

        return heap[1];
    }
	
	public Disk getMax() { //removes and returns the root of the heap
        if (size == 0)
            return null;

        Disk root = heap[1];
        heap[1] = heap[size]; //replaces the root with the rightmost leaf 
        size--;
        sink(1); //node's key is less than children nodes' keys 

        return root;
    }
	
	private void swim(int i) { 
        if (i == 1) //if i is the root 
            return;

        int parent = i / 2; //finds the parent

        while (i != 1 && heap[i].compareTo(heap[parent])==1) { //while child i is greater than parent
            swap(i, parent); //exchanges the values of the two nodes
            i = parent;     //repeat the function for the parent
            parent = i / 2;
        }
	}
	
	private void sink(int i) {
        int left = 2 * i; //left child
        int right = left + 1; //right child

        if (left > size) 
            return;

        while (left <= size) { //while we have not reached the leaves
            int max = left; // finds out the largest child of node i
            if (right <= size) {
                if (heap[left].compareTo(heap[right]) == -1)
                    max = right;
            }
			
            if (heap[i].compareTo(heap[max]) != -1)  //if node i is not less than the largest child
                return;
            else {          //exchange the values of i node and largest child 
                swap(i, max);
                i = max;   //repeat the function 
                left = 2*i;
                right = left + 1;
            }
        }
    }
	
	private void swap(int i, int j) { //exchange the values of two heap elements
        Disk temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }
	
	private void grow() { //increases the size of the heap
        Disk[] newHeap = new Disk[heap.length + auto_grow];
        for (int i = 0; i <= size; i++) { //copies the elements of the heap in a new heap 
            newHeap[i] = heap[i];
        }

        heap = newHeap;
    }
}