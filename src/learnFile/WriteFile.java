package learnFile;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class WriteFile {
    public static void main(String[] args) throws IOException {
        // 获取要写入的file
        File writeFile = new File("./src/learnFile/text.txt");

        try(
                // 获取输出到文件的流
                FileOutputStream fos = new FileOutputStream(writeFile);
                // 创建一个能够写入字符串的 连接输出文件流
                OutputStreamWriter osw = new OutputStreamWriter(fos);
                //
                ){
                    osw.write("123");
                    osw.flush();
        }


    }
}
