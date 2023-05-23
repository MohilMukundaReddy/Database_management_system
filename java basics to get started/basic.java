
import java.util.Arrays;
import java.util.Date;
import java.awt.*;
import java.util.Scanner;

public class basic{

public static void main(String[] args){
    int age = 30;
    int temp = 30;
    int views = 565_454_654; //in java underscore for clarity
    int herage = age; //copying age
    long viewcount = 3_559_447_897L; //by default java sees int so add L for float
    char letter = 'A';
    float  price = 123.54F;
    System.out.println(age);
    Date now = new Date(); //for non primitive type new is for allocating memeory
    //here now variable is an instance of date class ie now is an object(instance of class)
    now.getTime(); // we can use all methods of object
     System.out.println(now);
     Point point1 = new Point(1,1);
     Point point2 = point1;
     //for primitive data types value copied 
     //but for reference data types pointr is used
     //so point1(points the memory address of object) changes  point2 changes
     point1.x = 5; //point 2 also chnages
    System.out.println(point2);


    String message1 = new String("hello world");
    String message2 = "Hello world" + ">>>"; //we can concatenate
     //even though it look like string is primitive its not
    //as we use strings more often we can write like this
    //both are same
   //as message1 is now string object we can use various methods
    System.out.println(message1.endsWith("world"));
    System.out.println(message2);

    //in java strings are immutable so conside
    System.out.println(message1.replace("h", "s"));
    System.out.println(message1);
    //original do not change

    int[] nums = new int[5];
    nums[0] = 1;
    System.out.println(nums); //we get address
    System.out.println(Arrays.toString(nums));

    //other way to use
    int[] numbers = {4,454,4,4,84,8,8};
    System.out.println(numbers.length);

    Arrays.sort(numbers);
    System.out.println(Arrays.toString(numbers));
    
    //multidimentional array
    int[][]  a = new int[2][3];
    a[0][0] = 1;
    System.out.println(Arrays.deepToString(a));

    //alternatively
    int[][] b = {{1,2},{3,4},{5,6}};
    System.out.println(Arrays.deepToString(b));

    final float p = 3.14F;
    // pi = 1; cant be done as final is similar to const in python 
     
    double result = (double)10/3;

    //math class always there
    Math.round(1.1F);
    double res = Math.random();




}

}