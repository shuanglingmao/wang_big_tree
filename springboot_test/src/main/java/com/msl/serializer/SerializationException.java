package com.msl.serializer;

/**
 * Description: ${todo}
 *
 * @author shuangling.mao
 * @date 2019/5/9 10:11
 */
public class SerializationException extends RuntimeException {

    private static final long serialVersionUID = -612544049514558963L;

    public SerializationException(String message) {
        super(message);
    }

    public SerializationException(String message, Throwable cause) {
        super(message, cause);
    }
}
