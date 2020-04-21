package wuziqi;

public enum qiZi {
    BLACK(1, '●'),
    WHITE(2, '○'),
    CURRENT_BLACK(1, '■'),
    CURRENT_WHITE(2, '□');

    private int code;
    private char value;

    private qiZi(int code, char value){
        this.code = code;
        this.value = value;

    }

    public int getCode(){
        return this.code;
    }

    public char getValue(){
        return this.value;
    }
}
