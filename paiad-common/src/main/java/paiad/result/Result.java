package paiad.result;

import lombok.Data;

import java.io.Serializable;

/**
 * 封装统一返回结果
 */
@Data
public class Result<T> implements Serializable {
    private Integer code;
    private String msg;
    private T data;

    public Result(Integer code, String msg){
        this.code = code;
        this.msg = msg;
    }
    public Result(Integer code, String msg,T data){
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static <T> Result<T> success(T data){
        return new Result<>(200, "操作成功",data);
    }

    public static Result<String> error(Integer code, String msg){
        return new Result<>(code, msg);
    }
}
