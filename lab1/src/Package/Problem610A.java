package Package;

import java.util.Scanner;

public class Problem610A {
    public static void main(String[] args) {
        System.out.printf("Enter The Length of The Stick \n");
        Scanner scan = new Scanner(System.in);
        int stickLength = scan.nextInt();

        if(stickLength % 2 == 0 && stickLength > 0){
            int rectangles;

            if((stickLength / 2) % 2 == 0) {
                rectangles = ((stickLength / 2) - 2) / 2;
            } else {
                rectangles = ((stickLength / 2) - 1) / 2;
            }
            System.out.printf("The number of rectangles you can make is %d", rectangles);

        } else {
            System.out.printf("Can't split the stick into 4 peaces");
            return;
        }
    }
}
