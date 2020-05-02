package ChatRoom.common;

import static ChatRoom.common.Constant.*;
import jdk.nashorn.internal.runtime.regexp.joni.exception.ValueException;
import java.util.Map;

public class Util {

    /**
     * 检查用户名是否有效
     * 1. 不能重复
     * 2. 不能是admin
     * 3. 不能包含@符号
     * 4. 不能为空
     * todo: 使用责任链模式
     *
     * @param existedUsers
     * @return
     */
    public static boolean isValidUserName(String userName, Map existedUsers) {
        if (userName.trim().length() == 0) {
            throw new ValueException("用户名不能为空");
        }
        else if (existedUsers.get(userName) != null){
            throw new ValueException("当前用户名已存在");
        }
        else if (userName.contains(CONNECT_WITH_CLIENT_PATTERN)){
            throw new ValueException(String.format("当前用户名不能包含%s字符", CONNECT_WITH_CLIENT_PATTERN));
        }
        else if (userName.trim().equals(ADMIN)){
            throw new ValueException("当前用户名不能和管理员重名");
        }
        return true;
    }
}

