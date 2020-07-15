package com.msl.pipeline;

/**
 * @author msl on 2020/5/27.
 */
public class Pipeline<I,O> {

    private final Handler<I,O> currentHandler;

    public Pipeline(Handler<I, O> nowHandler) {
        this.currentHandler = nowHandler;
    }


    <K> Pipeline<I, K> addHandler(Handler<O, K> newHandler) {
        return new Pipeline<>(input -> newHandler.process(currentHandler.process(input)));
    }


    O execute(I input) {
        return currentHandler.process(input);
    }


}
