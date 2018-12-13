import java.util.Scanner;

public class lab2 {
    public static void main(String[] args) {
        // Setup input and tree to take commmands
        Scanner sc = new Scanner(System.in);
        BST<String> tree = new BST<>();
        String length = sc.nextLine();

        // Use while loop to read input continuously
        while (sc.hasNextLine()){
            String input = sc.nextLine();
            String[] phrases = input.split(" ");

            String cmd = phrases[0];

            switch(cmd){
                case "insert":
                    tree.insert(phrases[1]);
                    break;
                case "delete":
                    tree.delete(phrases[1]);
                    break;
                case "inorder":
                    tree.traverse("inorder", tree.getRoot());
                    break;
                case "preorder":
                    tree.traverse("preorder", tree.getRoot());
                    break;
                case "postorder":
                    tree.traverse("postorder", tree.getRoot());
                    break;
            }

        }
        // Loop over the Scanner
        // Split each line into the task and the corresponding number (if one exists)
        // Depending on what the input task was, preform the corresponding function
        //      (ie) insert, delete, find, inoder, preorder, or postorder
        // Hint: Use a switch-case statement

        // Don't forget to close your Scanner!

    }
}