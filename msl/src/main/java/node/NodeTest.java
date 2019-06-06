package node;

import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;
import java.util.stream.Stream;

/**
 * Description: ${todo}
 *
 * @author shuangling.mao
 * @date 2019/5/31 14:19
 */
public class NodeTest<E> implements List<E>{
    private Node<E> head;
    private Node<E> last;
    private int size = 0;


    public NodeTest() {
    }

    /**
     * 打印链表
     */
    @Override
    public String toString() {
        if (head == null) {
            return "[]";
        }
        StringBuilder sb = new StringBuilder("data:["+head.data+",");
        Node temp = head;
        while (temp.next != null) {
            sb.append(temp.next.data+",");
            temp = temp.next;
        }
        //去掉最后一个“,”
        return sb.toString().substring(0,sb.length()-1)+"]";
    }

    /**
     * 添加元素实际上是往尾部添加
     * @return
     */
    @Override
    public boolean add(E e) {
        linkLast(e);
        return true;
    }


    @Override
    public boolean remove(Object o) {
        return false;
    }


    @Override
    public boolean containsAll(@NotNull Collection<?> c) {
        return false;
    }


    @Override
    public boolean addAll(@NotNull Collection<? extends E> c) {
        return false;
    }


    @Override
    public boolean addAll(int index, @NotNull Collection<? extends E> c) {
        return false;
    }


    @Override
    public boolean removeAll(@NotNull Collection<?> c) {
        return false;
    }


    @Override
    public boolean removeIf(Predicate<? super E> filter) {
        return false;
    }


    @Override
    public boolean retainAll(@NotNull Collection<?> c) {
        return false;
    }


    @Override
    public void replaceAll(UnaryOperator<E> operator) {

    }


    @Override
    public void sort(Comparator<? super E> c) {

    }


    @Override
    public void clear() {

    }


    @Override
    public E get(int index) {
        return null;
    }


    @Override
    public E set(int index, E element) {
        return null;
    }


    @Override
    public void add(int index, E element) {

    }


    @Override
    public E remove(int index) {
        if (index > size-1 || index < 0) {
            throw new IndexOutOfBoundsException("Index:"+index+",Size:"+size);
        }
        int i = 0;
        Node<E> temp = head;
        Node<E> pre = null;
        while (temp.next != null) {
            if (i == index) {
                break;
            }
            pre = temp;
            temp = temp.next;
            i++;
        }
        //如果是头结点 把头结点的next 赋值给head
        if (pre == null) {
            head = temp.next;
        } else if (temp.next == null) {
            //如果是尾节点
            pre.next = null;
        } else {
            //中间节点  把要删除的前一个节点 指向  要删除的后一个节点
            pre.next = temp.next;
        }

        size--;
        return temp.data;
    }


    @Override
    public int indexOf(Object o) {
        return 0;
    }


    @Override
    public int lastIndexOf(Object o) {
        return 0;
    }


    @NotNull
    @Override
    public ListIterator<E> listIterator() {
        return null;
    }

    @NotNull
    @Override
    public ListIterator<E> listIterator(int index) {
        return null;
    }


    @NotNull
    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        return null;
    }


    @Override
    public Spliterator<E> spliterator() {
        return null;
    }


    @Override
    public Stream<E> stream() {
        return null;
    }


    @Override
    public Stream<E> parallelStream() {
        return null;
    }

    /**
     * 删除指定位置的数据
     * 找到这个节点以后  把他之前节点的next指向 他之后的节点
     * @param index
     * @return
     */
    Boolean delete(int index) {
        if (index > size-1 || index < 0) {
            throw new IndexOutOfBoundsException("Index:"+index+",Size:"+size);
        }
        int i = 0;
        Node temp = head;
        Node pre = null;
        while (temp.next != null) {
            if (i == index) {
                break;
            }
            pre = temp;
            temp = temp.next;
            i++;
        }
        //如果是头结点 把头结点的next 赋值给head
        if (pre == null) {
            head = temp.next;
        } else if (temp.next == null) {
            //如果是尾节点
            pre.next = null;
        } else {
            //中间节点  把要删除的前一个节点 指向  要删除的后一个节点
            pre.next = temp.next;
        }

        size--;
        return true;
    }


    @Override
    public int size() {
        return size;
    }


    @Override
    public boolean isEmpty() {
        return size == 0;
    }



    @Override
    public boolean contains(Object o) {
        return false;
    }


    @NotNull
    @Override
    public Iterator<E> iterator() {
        return null;
    }


    @Override
    public void forEach(Consumer<? super E> action) {

    }


    @NotNull
    @Override
    public Object[] toArray() {
        return new Object[0];
    }


    @NotNull
    @Override
    public <T> T[] toArray(@NotNull T[] a) {
        return null;
    }


    private void linkLast(E e) {
        final Node<E> newNode = new Node<>(e, null);
        //如果头结点为空 插入数据到头结点
        if (head == null) {
            head = newNode;
        } else {
            //否则 遍历链表找到 尾部节点  添加到尾部
            Node temp = head;
            while (temp.next != null) {
                temp = temp.next;
            }
            //插入数据到尾部节点
            temp.next = newNode;
        }
        size++;
    }

    public int getSize() {
        return size;
    }


    private class Node<E> {
        private E data;
        private Node next;

        public Node() {
        }

        public Node(E data, Node next) {
            this.data = data;
            this.next = next;
        }

        @Override
        public String toString() {
            return super.toString();
        }
    }
}



