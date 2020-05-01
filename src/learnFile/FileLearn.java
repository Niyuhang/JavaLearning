package learnFile;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.io.File;
import java.util.stream.Collectors;

public class FileLearn {
    private static final Scanner sc = new Scanner(System.in);

    // 当前文件夹目录
    private static final String ROOT = "." + File.separator;

    public static void main(String[] args) {
        File dir = createDirs();
        File newDir = new File(dir.getParent(), "新文件名");
        dir.renameTo(newDir);
        newDir.delete();
    }

    public static File createDirs() {
        ArrayList<String> paths = new ArrayList<>();

        while (true) {
            System.out.println("请输入新的文件夹名称");
            String path = sc.nextLine();
            if (path.isEmpty()) {
                break;
            }
            paths.add(path);
        }
        // 改造成 list string
        return createDir(paths.toArray(new String[0]));

    }

    private static File createDir(String... dirs) {
        String filePath = joinDirName(dirs);
        System.out.println("将在" + ROOT + "目录下创建" + filePath);
        File dir = new File(ROOT, filePath);
        if (dir.isDirectory()) {
            System.out.println("当前目录已存在");
            return dir;
        } else {
            // 创建目录
            boolean createDirSuccess = dir.mkdirs();
            if (createDirSuccess) {
                System.out.println("创建成功");
                return dir;
            } else {
                throw new IllegalArgumentException("创建失败");
            }
        }
    }

    private static String joinDirName(String... dirs) {
        return Arrays.stream(dirs)
                .map(String::trim)   // 去除空格
                .collect(Collectors.joining(File.separator));   // 合并
    }
}
