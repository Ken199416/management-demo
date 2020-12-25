package app.vo;

import lombok.Data;

@Data
public class ResponseData {
    private int code;
    private String message;
    private Object data;

    public ResponseData() {
    }

    public ResponseData(int code, String massage, Object data) {
        this.code = code;
        this.message = massage;
        this.data = data;
    }

    public static ResponseData success(Object data){
        return new ResponseData(200,"success",data);
    }
}
