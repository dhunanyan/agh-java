package Package;

import java.util.Scanner;
import java.util.Locale;

public class Fibo{
    public static void main(String[] args) {
        System.out.printf("Podaj liczbe w zakresie 0-45 \n");
        Scanner scan = new Scanner(System.in);
        int arrayLength = scan.nextInt();

        if(arrayLength >= 0 && arrayLength <= 45){
            int[] array = new int[arrayLength];
            array[0] = 0;
            array[1] = 1;
            System.out.printf("%d ", array[0]);
            System.out.printf("%d ", array[1]);
            for(int i = 2; i < array.length; i++){
                array[i] = array[i-1] + array[i-2];
                System.out.printf("%d ", array[i]);
            }
        } else {
            System.out.printf("Wprowadzona liczba znajduje sie poza podanym zakresem");
            return;
        }

    }
}
