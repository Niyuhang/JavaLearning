package ExceptionSample;

public class CatchException {

    public static void main(String[] args) {
        // 由于抛出的是unchecked error就不需要显式处理
        // int number = 1 / 0;

        // 这个错误就是checked exception 必须要显示handled
        try {
            Class clazz = Class.forName("");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        // 如果写了catch 一个checked exception 但是其实不会抛出 Java就会报错
//        try {
//            int number = 1 / 0;
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
    }

}
