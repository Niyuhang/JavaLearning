package learnFile;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

public class WriteFile {
    public static void main(String[] args) throws IOException {
        // 获取要写入的file
        File writeFile = new File("./src/learnFile/text.txt");

        try(
                // 获取输出到文件的流
                FileOutputStream fos = new FileOutputStream(writeFile, true);
                // 创建一个能够写入字符串的 连接输出文件流
                OutputStreamWriter osw = new OutputStreamWriter(fos);
                // 也可以利用PrintWrite 可以更方便的写入 例如换行
                PrintWriter pw = new PrintWriter(osw);
                ){
                    osw.write("123");
                    osw.flush();

                    pw.println("测试呀");
                    pw.println("你好呀");


        }


    }
}
