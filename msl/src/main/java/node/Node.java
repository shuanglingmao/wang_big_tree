package node;

/**
 * Description: 单链表
 *
 * @author shuangling.mao
 * @date 2019/5/31 14:11
 */
public class Node<T> {
    private T data;
    private Node next;

    public Node() {
    }

    public Node(T data, Node next) {
        this.data = data;
        this.next = next;
    }
}
