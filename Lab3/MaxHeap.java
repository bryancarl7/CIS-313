import java.util.Comparator;
import java.lang.Math;

public class MaxHeap<E extends Comparable> {
    private E[] myArray;
    private int maxSize;
    private int length;

    public MaxHeap(int mSize){
    	// Took me a while to figure out
        // Thank you for responding to my piazza post, you were extremely helpful

        // MaxSize is the total length of elements (heap or not)
        this.maxSize = mSize;

        // length is the working size of the heap
        this.length = 0;

        // Borrowed from Piazza post about type conversion
        // Add mSize + 1 so that index 0 is empty
        this.myArray = (E[])(new Comparable[mSize+1]);
    }

    //------------------------------------------//
    //---------  Helper Functions  -------------//
    //------------------------------------------//

    public E[] getArray(){
        return myArray;
    }

    public void setArray(E[] newArray){
    	if (newArray.length > maxSize){
    	    System.out.println("Max Size is not large enough to contain new array");
    		return;
    	}
        myArray = newArray;
        length = newArray.length-1;
    }

    public int getMaxSize(){
        return maxSize;
    }

    public void setMaxSize(int ms){
        maxSize = ms;
    }

    public int getLength(){
        return length;
    }

    public void setLength(int l){
        length = l;
    }

    //------------------------------------------//
    //--------- Indexing Functions -------------//
    //------------------------------------------//


    private int parent(int index){
        if (index < 2)
            return 1;
        int parent  = index/2;
        return (int)Math.floor(parent);
    }

    private int leftChild(int index){
        return 2*index;
    }

    private int rightChild(int index){
        return 2*index+1;
    }

    private void swap(int first, int second) {
        E temp = this.myArray[first];
        this.myArray[first] = this.myArray[second];
        this.myArray[second] = temp;
    }

    //------------------------------------------//
    //--------- Main Functions to edit ---------//
    //------------------------------------------//


    private void increaseKey(int i, E key){
        // If its the first element, make it the root
        if (i == 1){
            this.myArray[1] = key;
            return;
        }

        // To deal with null pointer exceptions
        if (this.myArray[i] == null){
            this.myArray[i] = key;
        }

        // general procedure to find highest place to put
        this.myArray[i] = key;
        while ((i > 1) && (this.myArray[parent(i)].compareTo(this.myArray[i])< 0)){
            swap(i, parent(i));
            i = parent(i);
        }
    }

    public void insert(E data){
        // If the heap is full, heap throw heap overlfow
        if (this.length == this.maxSize){
            System.out.println("Heap is full - Heap Overflow");
            return;
        }

        // Increase working length of heap
        this.length = this.length + 1;

        // Let increase key do the heavy lifting
        increaseKey(this.length, data);

        // Make sure the heap maintains its heap structure
        heapify(1);
    }

    public Comparable<E> maximum(){
        // return the maximum value in the heap
        return this.myArray[1];
    }

    public Comparable<E> extractMax(){
        // remove and return the maximum value in the heap
        if (this.length < 1) {
            System.out.println("The Heap is Empty");
        }

        // Alias the root as a max value
        E max = this.myArray[1];

        // Re-assign the root
        this.myArray[1] = this.myArray[this.length];

        // Decrement the length
        this.length = this.length - 1;

        // Make sure the Heap maintains its structure
        heapify(1);

        // Return max
        return max;
    }
    
    public void heapify(int i){
    	// helper function for reshaping the array

        // I'd love to say I understood why this works, but I only understand it at a high level
        // This is straight from the book's psuedo-code because I suck at recursion
        int largest;
        int left = leftChild(i);
        int right = rightChild(i);

        if ((left <= this.length) && (this.myArray[left].compareTo(this.myArray[i])>0))
                largest = left;
        else{largest = i;}

        if ((right <= this.length) && (this.myArray[right].compareTo(this.myArray[largest]) > 0))
                largest = right;

        // If the largest isn't the parent, make it the parent, and move on
        if (largest != i){
            swap(i, largest);
            heapify(largest);
        }
    }
    
    public void buildHeap(E[] newArr){
		// used for the extra credit
        this.length = this.maxSize;
        int i = (int)Math.floor(this.maxSize/2);
        while (i > 0){
            heapify(i);
            i--;
        }
	}
}
