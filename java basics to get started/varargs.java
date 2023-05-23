public class varargs {
    static int sum(int ...arr) // ...represent gives in array form
    {
        int result = 0;
        for(int a :arr)
        {
           result +=a;
        }
        return result;
    }

    static void add(int x,int ...arr)
    {
         // need first parameter compulsory
    }

    public static void main(String[] args)
    {
        System.out.println(sum(4,3,5,6,7));
    }
}
