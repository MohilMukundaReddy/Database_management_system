public class ops {
    //class is a blueprint for creating object
    //an object is an instantiation of a class



    //access modifiers
    // 1)private 2)public 3)default 4)protected 

    class myemployee
    {
        private int id;
        private String name;

        //constructor same name as class
        //no return type
        public myemployee(String myname,int iid){
            name = myname;
            id = iid;
        }

        public void setname(String n)
        {
            name = n;
        }

        public String getname()
        {
           return name;
        }

        public void setid(int k)
        {
           id = k;
        }
        public int getid()
        {
            return id;
        }
    }

    public static void main(String[] args)
    {
        myemployee a = new myemployee("harry",1);
        // harry.name = "harry potter"; cant set as they are private only methods related to that class can only access data
        
        //constructors in java
        //a member function used to initialize an object while creating it
    
    
    }
}
