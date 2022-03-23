package Package;

import java.util.Scanner;

public class Problem115A {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int employees = scan.nextInt();
        int[] array = new int[employees + 1];

        for(int i = 1; i < array.length; i++){
            array[i] = scan.nextInt();
        }

        int counter = 1;
        int counterMax = 0;
        int temp ;
        for(int i = 1; i < array.length; i++){
            temp = i;

            while(array[temp] != -1){
                temp = array[temp];
                ++counter;
            }

            if(counter > counterMax){
                counterMax = counter;
            }

            counter = 1;
        }

        System.out.printf("%d", counterMax);
    }
}
