import java.util.Scanner;
import java.util.Stack;

public class lab1 {
    public static void main(String[] args) {
        // Open the scanner to take the file input
        Scanner f = new Scanner(System.in);
        String line = "";
        int length = Integer.parseInt(f.nextLine());
        for(int i = 0; i < length;i++) {
            line = f.nextLine();
            if (isPalindrome(line))
                System.out.println("This is a Palindrome");
            else
                System.out.println("This is not a Palindrome");
        }

    }
    public static boolean isPalindrome(String s){

        // Initialize a length variable, along with the counter and Stack+Queue
        int length = s.length();
        int ctr = 0;
        Queue<String> que = new Queue<>();
        Stack<String> stak = new Stack<>();

        // Loop through word to push letters onto structures
        while (ctr < (length)){
            char character = s.charAt(ctr);
            String str = Character.toString(character);

            que.enqueue(str);
            stak.push(str);
            ctr++;
        }

        // Reset counter and create flag for returning
        int ctr2 = 0;
        boolean flag = true;

        // Check to see if the word is a palindrome
        while (ctr2 < (length)){
            if (!stak.pop().equals(que.dequeue().getData()))
                flag = false;
            ctr2++;
        }

        return flag;
    }


    public static boolean isPalindromeEC(String s){
        // Implement if you wish to do the extra credit.}
        return true;
    }
}
