public class RBT<E extends Comparable<E>> {
    private Node<E> root;
    private Node<E> leaf;

    public RBT(){
        this.leaf = new Node<>(null);
        this.leaf.setColor('b');
        this.leaf.setLeftChild(this.leaf);
        this.leaf.setRightChild(this.leaf);
        this.leaf.setParent(this.leaf);
        this.root = this.leaf;
    }

    public Node<E> getRoot(){
        return root;
    }

    public Node<E> getUncle(Node<E> son) {
        if (son.getParent() == null || son.getParent().getParent() == null)
            return null;

        Node<E> parent = son.getParent();
        Node<E> grandparent = son.getParent().getParent();

        if (parent == grandparent.getLeftChild())
            return grandparent.getRightChild();
        else
            return grandparent.getLeftChild();
    }

    public void insert(E data){
        // Preform a regular insert
        // Check to make sure the tree remains an RBT tree

        Node<E> z = new Node<>(data);

        // Initialize first value as a root if there is no root
//        if (this.root == null) {
//            this.root = z;
//            System.out.println("The root is: "+ this.root.getData());
//            return;
//        }

        // 1) y = T.nil
        Node<E> y = this.leaf;

        // 2) x = T.root
        Node<E> x = getRoot();

        // 3) while (x != T.nil)
        while (x != this.leaf){
            // 4)   y = x
            y = x;

            // 5)   if (z.key < x.key)
            if (z.getData().compareTo(x.getData()) < 0)

                // 6)       x = x.left
                x = x.getLeftChild();

            // 7)   else x = x.right
            else
                x = x.getRightChild();

        }

        // 8) z.p = y
        z.setParent(y);

        // 9) if (y == T.nil)
        if (y == this.leaf) {
            // 10)  T.root = z
            this.root = z;
        }

        // 11) else if (z.key < y.key)
        else if (z.getData().compareTo(y.getData()) < 0)
            // 12)  y.left = z
            y.setLeftChild(z);

        // 13) else y.right = z
        else {y.setRightChild(z);}

        // 14) z.left = T.nil
        z.setLeftChild(this.leaf);

        // 15) z.right = T.nil
        z.setRightChild(this.leaf);

        // 16) z.color = RED
        z.setColor('r');

        // 17) insertFixup(z)
        insertFixup(z);
    }

    public void insertFixup(Node<E> z) {
        // 1) while z.p.color == RED
        while (z.getParent().getColor() == 'r') {
            // 2) if z.p == z.p.p.left
            if (z.getParent() == z.getParent().getParent().getLeftChild()) {
                // 3)   y = z.p.p.right
                Node<E> y = z.getParent().getParent().getRightChild();

                // 4)   if y.color == RED
                if (y.getColor() == 'r'){
                    // 5)       z.p.color = BLACK
                    z.getParent().setColor('b');

                    // 6)       y.color = Black
                    y.setColor('b');

                    // 7)       z.p.p.color = RED
                    z.getParent().getParent().setColor('r');

                    // 8)       z = z.p.p
                    z = z.getParent().getParent();
                }

                // 9)   else (if (z == z.p.right))
                else {
                    // 9)  if (z == z.p.right))
                    if (z == z.getParent().getRightChild()) {
                        // 10)          z = z.p
                        z = z.getParent();

                        // 11)          left-rotate(z)
                        leftRotate(z);
                    }
                    // 12)      z.p.color = Black
                    z.getParent().setColor('b');

                    // 13)      z.p.p.color = RED
                    z.getParent().getParent().setColor('r');

                    // 14)      right-rotate(z)
                    rightRotate(z.getParent().getParent());
                }

            }

            // repeat the same if statement while swapping "right" and "left"
            else{
                // 3)   y = z.p.p.left
                Node<E> y = z.getParent().getParent().getLeftChild();

                // 4)   if y.color == RED
                if (y.getColor() == 'r'){
                    // 5)       z.p.color = BLACK
                    z.getParent().setColor('b');

                    // 6)       y.color = Black
                    y.setColor('b');

                    // 7)       z.p.p.color = RED
                    z.getParent().getParent().setColor('r');

                    // 8)       z = z.p.p
                    z = z.getParent().getParent();
                }

                // 9)   else (if (z == z.p.left))
                else {
                    // 9)  if (z == z.p.left))
                    if (z == z.getParent().getLeftChild()) {
                        // 10)          z = z.p
                        z = z.getParent();

                        // 11)          right-rotate(z)
                        rightRotate(z);
                    }
                    // 12)      z.p.color = Black
                    z.getParent().setColor('b');

                    // 13)      z.p.p.color = RED
                    z.getParent().getParent().setColor('r');

                    // 14)      right-rotate(z)
                    leftRotate(z.getParent().getParent());
                }
            }
        }
        // 16 T.root.color = BLACK
        this.root.setColor('b');
    }

    public Node<E> search(E data){
        // Return the node that corresponds with the given data
        // Note: No need to worry about duplicate values added to the tree
        boolean done = false;
        Node<E> temp = getRoot();

        while (!done){
            if (temp == this.leaf)
                return null;
            if (temp.getData().compareTo(data)==0)
                done = true;
            else if(temp.getData().compareTo(data) > 0)
                temp = temp.getLeftChild();
            else if(temp.getData().compareTo(data) < 0)
                temp = temp.getRightChild();
        }

        return temp;
    }

    public void delete(E data){
    	// Preform a regular delete
    	// Check to make sure the tree remains an RBT tree
        Node<E> z = search(data);
        Node<E> x;

        // 1) y = z
        Node<E> y = z;

        // 2) y-original-color = y.color
        char originalcolor = y.getColor();

        // 3) if z.left == T.nil
        if (z.getLeftChild() == this.leaf){
            // 4)   x = z.right
            x = z.getRightChild();

            // 5)   transplant(z, z.right)
            transplant(z,z.getRightChild());
        }
        // 6) elseif z.right == T.nil
        else if(z.getRightChild() == this.leaf){
            // 7)   x = z.left
            x = z.getLeftChild();

            // 8)   transplant(x, z.left)
            transplant(z, z.getLeftChild());
        }

        // 9) else Y = tree-minimum(z.right)
        else{
            // 9) else Y = tree-minimum(z.right)
            y = getMin(z.getRightChild());

            // 10)  y-original-color = y.color
            originalcolor = y.getColor();

            // 11)  x = y.right
            x = y.getRightChild();

            // 12)  if y.p == z
            if (y.getParent() == z)
                // 13)      x.p = y
                x.setParent(y);

            // 14)  else transplant(y,y.right)
            else{
                transplant(y,y.getRightChild());

                // 15)      y.right = z.right
                y.setRightChild(z.getRightChild());

                // 16)      y.right.p = y
                y.getRightChild().setParent(y);
            }

            // 17)  transplant(z,y)
            transplant(z,y);

            // 18)  y.left = z.left
            y.setLeftChild(z.getLeftChild());

            // 19)  y.left.p = y
            y.getLeftChild().setParent(y);

            // 20)  y.color = z.color
            y.setColor(z.getColor());
        }

        // 21) if y-original-color == black
        if (originalcolor == 'b'){
            deleteFixup(x);
        }
    }

    public void transplant(Node<E> u, Node<E> v){
        // 1) if u.p == T.nil
        if (u.getParent() == this.leaf){
            // 2) T.root = v
            this.root = v;
        }
        // 3) elseif u == u.p.left
        else if (u == u.getParent().getLeftChild()){
            // 4) u.p.left = v
            u.getParent().setLeftChild(v);
        }
        else{
            // 5) else u.p.right = v
            u.getParent().setRightChild(v);
        }
        // 6) v.p = u.p
        v.setParent(u.getParent());
    }

    public void deleteFixup(Node<E> x){
        // Declare node for later usage

        // 1) while x != t.root and x.color == b
        while (x != this.root && x.getColor() == 'b') {
            // 2) if x == x.p.left
            Node<E> w;
            if (x  == x.getParent().getLeftChild()) {
                // 3)   w = x.p.right
                w = x.getParent().getRightChild();

                // 4)   if w.color == red
                if (w.getColor() == 'r') {
                    // 5)       w.color == black
                    w.setColor('b');

                    // 6)       x.p.color == red
                    x.getParent().setColor('r');

                    // 7)       left-rotate(x.p)ff
                    leftRotate(x.getParent());

                    // 8)       w = x.p.right
                    w = x.getParent().getRightChild();
                }

                // 9)   if w.left.color == black and w.right.color == black
                if (w.getLeftChild().getColor() == 'b' && w.getRightChild().getColor() == 'b') {
                    // 10)      w.color = red
                    w.setColor('r');

                    // 11)      x = x.p
                    x = x.getParent();
                }

                // 11.5)else
                else {
                    // 12)      if w.right.color == black
                    if (w.getRightChild().getColor() == 'b') {
                        // 13)          w.left.color = black
                        w.getLeftChild().setColor('b');

                        // 14)          w.color = red
                        w.setColor('r');

                        // 15)          right-rotate(w)
                        rightRotate(w);

                        // 16)          w = x.p.right
                        w = x.getParent().getRightChild();
                    }

                    // 17)      w.color = x.p.color
                    w.setColor(x.getParent().getColor());

                    // 18)      x.p.color = black
                    x.getParent().setColor('b');

                    // 19)      w.right.color = black
                    w.getRightChild().setColor('b');

                    // 20)      left-rotate(x.p)
                    leftRotate(x.getParent());

                    // 21)      x = T.root
                    x = getRoot();
                }
            }
            // 22) else but backwards this time
            else{
                // 3)   w = x.p.right
                w = x.getParent().getLeftChild(); //

                // 4)   if w.color == red
                if (w.getColor() == 'r') {
                    // 5)       w.color == black
                    w.setColor('b');

                    // 6)       x.p.color == red
                    x.getParent().setColor('r');

                    // 7)       left-rotate(x.p)
                    rightRotate(x.getParent()); //

                    // 8)       w = x.p.right
                    w = x.getParent().getLeftChild(); //
                }

                // 9)   if w.left.color == black and w.right.color == black
                if (w.getRightChild().getColor() == 'b' && w.getLeftChild().getColor() == 'b') { //
                    // 10)      w.color = red
                    w.setColor('r');

                    // 11)      x = x.p
                    x = x.getParent();
                }

                // 11.5)else
                else {
                    // 12)      if w.right.color == black
                    if (w.getLeftChild().getColor() == 'b') { //
                        // 13)          w.left.color = black
                        w.getLeftChild().setColor('b'); //   <<<-------------

                        // 14)          w.color = red
                        w.setColor('r');

                        // 15)          right-rotate(w)
                        leftRotate(w); //

                        // 16)          w = x.p.right
                        w = x.getParent().getLeftChild(); //
                    }

                    // 17)      w.color = x.p.color
                    w.setColor(x.getParent().getColor());

                    // 18)      x.p.color = black
                    x.getParent().setColor('b');

                    // 19)      w.right.color = black
                    w.getLeftChild().setColor('b'); //

                    // 20)      left-rotate(x.p)
                    rightRotate(x.getParent()); //

                    // 21)      x = T.root
                    x = getRoot();
                }
            }
        }
        // 23)x.color = black
        x.setColor('b');
    }

    public void traverse(String order, Node<E> top) {
        // Preform a preorder traversal of the tree
        if (top!=this.leaf) {
            System.out.print(top.getData().toString() + " ");
            traverse("preorder", top.getLeftChild());
            traverse("preorder", top.getRightChild());
        }
    }

    public void rightRotate(Node<E> x){
        // 1) y = x.left
        Node<E> y = x.getLeftChild();

        // 2) x.left = y.right
        x.setLeftChild(y.getRightChild());

        // 3) if (y.right != NIL)
        if (y.getRightChild() != this.leaf){
            // 4)   y.right.p = x
            y.getRightChild().setParent(x);
        }

        // 5) y.p = x.p
        y.setParent(x.getParent());

        // 6) if (x.p == NIL)
        if (x.getParent() == this.leaf) {
            // 7)   T.root = y
            this.root = y;
        }

        // 8) else if (x == x.p.right)
        else if (x == x.getParent().getRightChild()) {
            // 9)   x.p.right = y
            x.getParent().setRightChild(y);
        }

        // 10) else
        else {
            // 10) x.p.left = y
            x.getParent().setLeftChild(y); //////// ---------------------------<<<<<<<<<
        }
        // 11)  y.right = x
        y.setRightChild(x);

        // 12)  x.p = y
        x.setParent(y);
    }

    public void leftRotate(Node<E> x){
        //1) y = x.right
        Node <E> y = x.getRightChild();

        //2) x.right = y.left
        x.setRightChild(y.getLeftChild());

        //3) if y.left != T.nil (leaf)
        if (y.getLeftChild() != this.leaf) {

            //4) y.left.p = x
            y.getLeftChild().setParent(x);
        }

        // 5) y.p = x.p
        y.setParent(x.getParent());

        // 6) if x.p == T.nil
        if (x.getParent() == this.leaf){

            // 7) T.root = y
            this.root = y;
        }

        // 8) else if x == x.p.left
        else if (x == x.getParent().getLeftChild()){

            // 9) x.p.left = y
            x.getParent().setLeftChild(y);
        }

        // 10) else x.p.right = y
        else {
            x.getParent().setRightChild(y);
        }
        // 11) y.left = x
        y.setLeftChild(x);

        // 12) x.p = y
        x.setParent(y);

    }

    public Node<E> getMin(Node<E> subtreeroot){
        while(subtreeroot.getLeftChild() != this.leaf){
            subtreeroot = subtreeroot.getLeftChild();
        }
        return subtreeroot;
    }
}
