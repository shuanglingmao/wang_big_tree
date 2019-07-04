package com.neo.nio;

/**
 * @Description:
 * @Version V1.0.0
 * @Since 1.0
 * @Date 2019/7/1 0001
 * @Author 毛双领 <shuangling.mao>
 */
public class IORuntimeException extends RuntimeException {

    private static final long serialVersionUID = -2827780360137761322L;

    public IORuntimeException() {
        super();
    }

    public IORuntimeException(String message) {
        super(message);
    }

    public IORuntimeException(String message, Throwable cause) {
        super(message, cause);
    }

    public IORuntimeException(Throwable cause) {
        super(cause);
    }

}
