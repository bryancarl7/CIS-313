import java.util.Scanner;

public class lab3 {
    public static void main(String[] args) {

        // Setup input and tree to take commmands
        Scanner sc = new Scanner(System.in);
        int  length = sc.nextInt();
        pQueue<Integer> que = new pQueue<>(length);

        // Use while loop to read input continuously
        while (sc.hasNextLine()){
            String input = sc.nextLine();
            String[] phrases = input.split(" ");

            String cmd = phrases[0];

            switch(cmd){
                case "insert":
                    // Put insertable through Integer parse to get int primitive
                    int insertable = Integer.parseInt(phrases[1]);
                    que.insert(insertable);
                    break;

                case "isEmpty":
                    // really simple, if true then reply empty, otherwise not empty
                    if (que.isEmpty())
                        System.out.println("Empty");
                    else
                        System.out.println("Not Empty");
                    break;

                case "maximum":
                    // Take the poll but dont return
                    System.out.println(que.maximum());
                    break;

                case "print":
                    // Print out the desired array
                    que.print();
                    break;

                case "extractMax":
                    // Take the top element off heap
                    System.out.println(que.extractMax());
                    break;

                case "build":
                    String arr = phrases[1];
                    Integer[] newarr = new Integer[arr.length()];
                    int j = 0;
                    for (int i = 1;i < arr.length()-1;i+=2){
                        try{
                            newarr[j] = Character.getNumericValue(arr.charAt(i));
                            j++;
                        } catch(NumberFormatException e){
                           throw e;
                        }
                    }
                    que.build(newarr);
                    que.print();
            }
        
    }
    // Always remember to close the scanner
    sc.close();
}}
