package node;

/**
 * Description: ${todo}
 *
 * @author shuangling.mao
 * @date 2019/5/31 14:20
 */
public interface ILinkList {
    /**存储*/
    public void add(int e);
    /**插入*/
    public void insert(int e,int index);
    /**删除*/
    public void delete(int index);
    /**获取*/
    public int get(int index);

    public void set(int data);
    /**修改*/
    public void updata(int index,int newValue);
    /**获取元素个数*/
    public int getSize();

    public Node getNext();
    public void setNext(Node next);

}
