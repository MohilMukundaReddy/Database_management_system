

    class Base{
        public int x;
    
        public int getX() {
            return x;
        }
    
        public void setX(int x) {
            System.out.println("I am in base and setting x now");
            this.x = x;
        }
    
        public void printMe(){
            System.out.println("I am a constructor");
        }
    }
    
    //inheritance gets all variables and methods from child available
    //but accessible or not depends
    class Derivedclass extends Base{
        public int y;
    
        public int getY() {
            return y;
        }
    
        public void setY(int y) {
            this.y = y;
        }
    }

//constructors in inheritance
class Base1{
    Base1(){
        System.out.println("I am a constructor");
    }
    Base1(int x){
        System.out.println("I am an overloaded constructor with value of x as: " + x);
    }
}

//as base1 already has constructor and derived also
//so first base1 runs then derived runs
//if only base has not derived then only base runs
class Derived1 extends Base1{
    Derived1(){
        super(0);
        System.out.println("I am a derived class constructor");
    }
    Derived1(int x, int y){
        super(x);
        System.out.println("I am an overloaded constructor of Derived with value of y as: " + y);
    }
}

class ChildOfDerived extends  Derived1{
    ChildOfDerived(){
        System.out.println("I am a child of derived constructor");
    }
    ChildOfDerived(int x, int y, int z){
        super(x, y);
        System.out.println("I am an overloaded constructor of Derived with value of z as: " + z);
    }
}




public class inheritance {
    public static void main(String[] args)
    {
          Derivedclass obje = new Derivedclass();
          obje.getX();

          Base1 b = new Base1();
        Derived1 d = new Derived1();
        Derived1 d = new Derived1(14, 9);
        ChildOfDerived cd = new ChildOfDerived();
        ChildOfDerived cd = new ChildOfDerived(12, 13, 15);
    }
}
