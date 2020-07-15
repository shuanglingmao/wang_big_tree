package com.msl.pipeline;

/**
 * @author msl on 2020/5/27.
 */
@FunctionalInterface
public interface Handler<I,O> {
    /**
     * description: handler接口 <br>
     *
     * @param input
     * @return O
     * @author: msl
     * @date: 2020/5/27 14:24
     */
    O process(I input);
}
