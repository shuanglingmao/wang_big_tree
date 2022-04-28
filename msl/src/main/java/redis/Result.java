package redis;

import lombok.Data;

@Data
public class Result<T> {
    //200代表成功
    private Integer code;

    private String msg;

    private T t;
}
