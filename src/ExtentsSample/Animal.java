package ExtentsSample;

public class Animal {
    public static String thisType="animal";

    public Animal(String name){
        System.out.println("这是父类的构造方法");
    }
    public static void main(String[] args) {
        Class animalClazz = Animal.class;
        Animal a  = new Animal("");
        Class b = a.getClass();
    }
    public void describe(){
        System.out.println("我是animal");
    }
}
