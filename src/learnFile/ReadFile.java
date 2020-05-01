package learnFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedReader;

public class ReadFile {
    public static void main(String[] args) throws IOException {
        File writeFile = new File("./src/learnFile/text.txt");
        try (
                // 获取文件输入流
                FileInputStream fis = new FileInputStream(writeFile);

                // 转换成字符串
                InputStreamReader isr = new InputStreamReader(fis);

                // 利用BufferedInputStream 处理可以更方便，并且还有缓存机制
                BufferedReader reader = new BufferedReader(isr);
                )

        {
//            String line;
//            while ((line = reader.readLine()) != null){
//                System.out.println(line.trim());
//            }
            reader.lines().map(String::trim).forEach(System.out::println);
        }
    }
}
