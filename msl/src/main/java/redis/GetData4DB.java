package redis;

public interface GetData4DB<T> {

    /**
     * 从数据库获取数据
     *
     * @return T 结果数据
     */
    T getDate4DB();
}
