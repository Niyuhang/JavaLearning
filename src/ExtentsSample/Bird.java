package ExtentsSample;

public class Bird extends Animal{
    public static String thisType="bird";
    public Bird(){
        super("");
        System.out.println("这是子类的构造方法");
    }

    public static void main(String[] args) {
        Bird aBird = new Bird();
    }
    public void describe(){
        System.out.println("我是bird");
    }
}

