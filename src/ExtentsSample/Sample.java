package ExtentsSample;

public class Sample {
    public static void main(String[] args) {
        Animal animal1 = new Bird();
        Animal animal2 = new Animal("name");

        System.out.println(animal1.thisType);
        System.out.println(animal2.thisType);

    }
}
