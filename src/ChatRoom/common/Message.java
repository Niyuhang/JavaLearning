package ChatRoom.common;

import java.net.Socket;

public class Message {

    private String from;
    private String to;
    private String message;

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

    /**
     * 根据message字符串生成消息
     * @param message
     * @return
     */
    public static Message generateMessage(String message){
        return new Message();

    }

    /**
     * 消息生成字符串
     * @return
     */
    public String toString(){
        return "";

    }

}
