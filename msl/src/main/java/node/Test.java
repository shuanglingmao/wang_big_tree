package node;

import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;

/**
 * Description: ${todo}
 *
 * @author shuangling.mao
 * @date 2019/5/31 15:12
 */
public class Test {
    public static void main(String[] args) {
        NodeTest<Integer> nodeTest = new NodeTest<>();
        nodeTest.add(1);
        nodeTest.add(2);
        nodeTest.add(3);
        nodeTest.add(4);
        nodeTest.add(5);
        System.out.println(nodeTest.toString());
        System.out.println(nodeTest.getSize());
        nodeTest.delete(-1);
        System.out.println(nodeTest.toString());
        System.out.println(nodeTest.getSize());


    }
    @org.junit.Test
    public void delete() {
        LinkedList linkedList = new LinkedList();
        linkedList.add(1);
        linkedList.remove(-1);
        System.out.println(linkedList.toString());
    }

    @org.junit.Test
    public void remove() {
        LinkedList linkedList = new LinkedList();
        linkedList.add(1);
        linkedList.remove(Integer.valueOf(1));
        System.out.println(linkedList.toString());

    }

    @org.junit.Test
    public void hashMap() {
        List list = new LinkedList<>();
        for (int i = 0; i < 1000000; i++) {
            list.add("啊阿啊啊啊啊"+i);
        };
        System.out.println(list.size());
        System.out.println(list.toString());

    }

    @org.junit.Test
    public void hashMap1() {
        List list = new NodeTest();
        for (int i = 0; i < 100000; i++) {
            list.add("啊阿啊啊啊啊"+i);
        };
        System.out.println(list.size());

    }


}
