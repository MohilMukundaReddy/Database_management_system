public class method_overload{
    //name can be same
     void foo()
     {
        System.out.println("good morning");
     }

     void foo(int a)
     {
        System.out.println("number is" + a);
     }

   // we can see int cant we changed but passing array can change array itself as it objects passes address
     void change(int  a)
     {
        a = 50;
     }

     void change(int [] arr)
     {
        arr[0] = 89;
     }
}

//by default classes public
class abc{
     static int e = 3;
    int i = 0;
    //toc call this method there is no nedd to instantiate an object
    static void show(){
        System.out.println("hi");
        // System.out.println(i); cant do as i is not static it is dependend on object
    }
    
}

public class methods{

    static int sum(int x,int y){
        return (x+y)*5;
    }

    int Summ(int x,int y){
        return (x+y)*5;
    }
    public static void main(String[] args){
        int a=5,b=7;
        int c = sum(5,7); //values are copied so cant change itself


        // static is used for method where objects are not created 
        //but static methods can only be called in static enviromment

        abc.show(); //no object needed
        System.out.println(abc.e);



        //calling a method
        //if we use static no need associate with object but if we do we need to creaate its respective obj then access
         methods createdobj = new methods();
         int d = createdobj.Summ(a,b);

         method_overload k = new method_overload();
        k.foo();
        k.foo(7);

        k.change(a);
        int[] arr = {6,4,8547,87,89};
        k.change(arr);
        System.out.println(a +"and" + arr);
        

    }
}
