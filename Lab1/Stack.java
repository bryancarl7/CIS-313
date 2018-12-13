public class Stack<E> {
    private Node<E> top;
    private int count;

    public Stack(){
        top = null;
    }

    public void push(E newData){
        Node<E> newNode = new Node<E>(newData, this.top);
        this.count++;
        this.top =  newNode;
        System.out.println(count);
    }

    public Node<E> pop(){
        Node<E> returnNode = new Node<>(this.top.getData(), this.top.getNext());
        this.top = this.top.getNext();
        return returnNode;
    }

    public boolean isEmpty(){
        if (this.top == null)
            return true;
        else
            return false;
    }

    public void printStack(){
        Stack<E> stackeroo = new Stack<E>();
        for(int ctr = 0; !(isEmpty());ctr++){
            System.out.println(this.top.getData());
            // This needs some work
        }
    }

}
