package arrayListSample;

import java.util.Arrays;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class MapSample {

    public static void main(String[] args) {
        ConcurrentHashMap <String, String> data = new ConcurrentHashMap<>();
        data.put("12", "334");
        data.put("234", "334");
        String res = data.keySet().stream().collect(Collectors.joining(";"));
        System.out.println(res);
    }
}
