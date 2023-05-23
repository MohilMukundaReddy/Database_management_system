import java.util.Scanner;

public class A_02l {
     public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int a = sc.nextInt();
        String str = sc.nextLine();
        int b = sc.nextInt();
        int sum = a+b;
        System.out.println("sum is" + sum);
        System.out.println("reads until the end of the line" + str);
     
        // if we want to get individual words use next() method

        //we can use multiple methods at same time called method chaining
        //ex: scanner.nextLine().trim() executes left to right
        

     }

}
