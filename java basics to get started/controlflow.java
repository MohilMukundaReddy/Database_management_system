import java.util.Scanner;

public class controlflow {
    public static void main(String[] args){

        int temp = 200_000; //other wayto represent number
        if(temp > 10)
        {
          System.out.printf("temp is %d",temp);
        }
        else if (temp > 100 && temp < 10)
        {
          System.out.println("not possible temp");
        }


        //ternaryt operator has 3 components ? and : depending on first condition the string is assigned 
        int income = 200000;
        String className = income >100000 ? "First" : "Economy" ;

        //switch state ment
        String role = "admin";

        switch (role){
            case "admin":
              System.out.println("you are admin");
              break;
            case "moderator":
              System.out.println("you are moderator");
              break;
            default:
              System.out.println("you are guest");

        }
  

        //for loops
         for (int i=0;i<5;i++)
         {
            System.out.println("Hello world");
         }

         //while loop used when we dont know how many times we need to loop
         String input= "";
        //  while(input != "quit") wrong as string is an object we cant compare addresses
         //use equals method for objects to compare
        //   while(!input.equals("quit"))
        //   {
        //     System.out.println("input: ");
        //     Scanner scanner = new Scanner(System.in) ;
        //     input = scanner.next();
        //   }
        //loop stops until you enter quit but every iteration new scanner object is created so avoid that
         
        Scanner scanner  =new Scanner(System.in);
          while(!input.equals("quit")){
            System.out.println("input :");
            input = scanner.next().toLowerCase();
            //suppose we enter (jfsg ghio sgfdg) in single line
            //it takes one at a time but scanner.next automatically recognige previous words to print

          }

          //break and continue is similar

          //for each loop used to loop around arrays and collection

          String[] fruits = {"Apple","Mango","orange"};
          for(int i=0;i<fruits.length;i++)
          {
            System.out.println(fruits[i]);
          }

          //for each loop 
          //but always forward only we dont have access to index
          for(String ft :fruits) //ft is varable
          {
            System.out.println(ft);
          }
    }
    
}
