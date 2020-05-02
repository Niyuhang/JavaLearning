package ChatRoom.common;
import jdk.nashorn.internal.runtime.regexp.joni.exception.ValueException;

import java.util.regex.*;

public class Message {

    private String from;
    private String to;
    private String message;

    public Message(String from, String to, String message){
        this.from = from;
        this.to = to;
        this.message = message;

    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static void main(String[] args) {
        Message message = new Message("来自", "我的", "消息");
        String messageData = message.toString();
        assert messageData.equals("from:来自||to:我的||message:消息");
        System.out.println(Message.generateMessage(messageData));
    }

    /**
     * 根据message字符串生成消息
     * @param messageData
     * @return Message
     */
    static Message generateMessage (String messageData) throws ValueException{
        // 要用\\ 表示为正则表达式中的z\
        Pattern matchPattern = Pattern.compile("from:(\\S+)\\|\\|to:(\\S+)\\|\\|message:(\\S+)");
        Matcher matcher = matchPattern.matcher(messageData);
        if (matcher.find()){
            // group(0)就是当前匹配字符 1才是第一个匹配到的
            String from = matcher.group(1);
            String to = matcher.group(2);
            String message = matcher.group(3);
            return new Message(from, to, message);
        }
        else{
            throw new ValueException("无效的信息");
        }


    }

    /**
     * 消息生成字符串
     * @return String:
     */
    public String toString(){
        return String.format("from:%s||to:%s||message:%s", from, to, message);
    }

}
