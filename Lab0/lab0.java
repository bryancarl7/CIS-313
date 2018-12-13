//----------------------------//
// Lab0 for CIS 313           //
// Author: Bryan Carl         //
//----------------------------//

// I hope this compiles alright, I had a bunch of trouble with the "calssnotfound" error.
// Ended up having to just redownload the template and start over.
// I couldn't get it up and running on the server becuase I am bad at linux,
// however on my local machine it did pass all the test cases I could throw at it
// so I hope it works for you too

import java.util.Scanner;                         //import Scanner package

public class lab0 {
  public static void main(String[] args){
    Scanner scanner = new Scanner(System.in);
    int numProblems = scanner.nextInt();          //retrieve the number of lines
  
    for(int i = 0; i < numProblems; ++i){
      int a = scanner.nextInt();                  //retrieve the first integer
      int b = scanner.nextInt();                  //retrieve the second integer
 
      int g = gcd(a,b);
      int l = lcm(a,b);
 
      System.out.println(g + " " + l);
    }

    scanner.close();
 }

    public static int gcd(int a, int b){
        // Make sure the gcd is 1 in case there is nothing greater
        int gcd = 1;
        int first = a;
        int second = b;

        // Throw out some test cases for when one of them is 0
        if (b == 0)
            return first;
        if (a == 0)
            return second;

        for (int i = 1; i <= a && i<= b;i++){
            // If theyre both divisible by i, then i is the gcd
            if (a % i == 0 && b % i == 0)
                gcd = i;
        }
        return gcd;
    }

    public static int lcm(int a, int b){
        // Math is cool and stuff like this works
        int lcm = (a * b / gcd(a, b));
        return lcm;
    }
}
