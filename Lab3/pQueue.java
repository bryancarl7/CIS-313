public class pQueue<E extends Comparable> {
    private MaxHeap myHeap;

    public pQueue (int s) {
        // Build that heap
        myHeap = new MaxHeap(s);
    }

    public void insert(E data){ myHeap.insert(data); }

    public Comparable<E> maximum(){
        return myHeap.maximum();
    }

    public Comparable<E> extractMax(){
        return myHeap.extractMax();
    }

    public boolean isEmpty(){
        // Just check to see if current length is zero
        return myHeap.getLength() == 0;
    }

	public void build(E[] arr){
    	// used for the extra credit
        myHeap.setArray(arr);
        myHeap.buildHeap(myHeap.getArray());
    }
    
    public void print(){
        // Start at 1 because thats the root
        int i = 1;

        // Prelude to heap looping
        System.out.print("Current Queue: ");
        System.out.print(myHeap.getArray()[i]);
        i++;

        // Loop through the heap and don't forget commas with no spaces
        while (i < myHeap.getLength()+1){
            System.out.print(","+myHeap.getArray()[i]);
            i++;
        }
        System.out.print("\n");
    }
}
