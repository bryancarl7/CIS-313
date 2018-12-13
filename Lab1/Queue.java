public class Queue<E> {
    private Node<E> head;
    private Node<E> tail;

    public Queue(){
        this.head = null;
        this.tail = null;
    }

    public void enqueue(E newData){
        if (this.tail == null && this.head == null){
            Node<E> n1 = new Node<>(newData, null);
            this.head = n1;
            return;
        }
        else if(this.tail == null){
            Node<E> n2 = new Node<>(newData, null);
            this.tail = n2;
            this.head.setNext(n2);
        }
        else {
            Node<E> n3 = new Node<>(newData, null);
            this.tail.setNext(n3);
            this.tail = n3;
        }

    }

    public Node<E> dequeue(){
        if (this.isEmpty())
            return null;
        Node<E> n = new Node<>(this.head.getData(), this.head.getNext());
        this.head = this.head.getNext();
        return n;
    }

    public boolean isEmpty(){
        if (this.tail == null && this.head == null)
            return true;
        else
            return false;
    }

    public void printQueue(){
        Queue<Node> que = new Queue<>();
        for(int i = 0;(!this.isEmpty());i++){
            Node n = this.dequeue();
            System.out.println(n.getData());
            que.enqueue(n);
        }
        while (!que.isEmpty()){
            Node n = que.dequeue();
            //this.enqueue(n.getData());
            // Enqueue back to queue
        }
    }
}
