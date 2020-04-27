package genericSample;

public class MyGenericClass<first> {
    private first first_data;

    public first getFirst_data() {
        return first_data;
    }

    public void setFirst_data(first first_data) {
        this.first_data = first_data;
    }

    public <what> what getWhat() {
//        System.out.println(what.Class);
        what what = (what) "12";
        System.out.println(what);
        // 类型转换其实都是在返回后执行的 也就是Java在返回后执行了强制类型转换
        return what;
    }

    public static void main(String[] args) {
        MyGenericClass my_clazz = new MyGenericClass();
        my_clazz.setFirst_data(12);
        my_clazz.setFirst_data(new Object());
        System.out.println(my_clazz.getFirst_data());

        // 设置了范型的类型后就无法使用非该类型外的值
        MyGenericClass<Number> my_clazz2 = new MyGenericClass<>();
        my_clazz2.setFirst_data(12);
        System.out.println(my_clazz2.getFirst_data());
        String number = my_clazz2.getWhat();
    }
}
