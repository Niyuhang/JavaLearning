package arrayListSample;

import java.util.ArrayList;
import java.util.Set;

public class ArrayListSample {
    public static void main(String[] args) {
        ArrayList nums = new ArrayList();
        nums.add(1);
        nums.add("2");
        nums.add("3");

        // 由于是范型 这里要使用Object 定义

        for (Object el : nums) {
            System.out.println(el);
        }
        Object num1 = nums.get(0);
        try{
            Set nums_set = (Set) nums;
            System.out.println(nums_set);
        }
        catch(Exception e){
            System.out.println(e);
        }


    }
}
