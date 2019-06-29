package com.neo.test.menu;

import com.alibaba.fastjson.JSONObject;
import com.neo.model.Result;
import org.apache.jasper.tagplugins.jstl.core.If;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Collectors;

/**
 * @Description:
 * @Version V1.0.0
 * @Since 1.0
 * @Date 2019/6/29 0029
 * @Author 毛双领 <shuangling.mao@ucarinc.com>
 */
public class MenuTest {
    List<Menu> list;



    public void init() {
        list = new LinkedList<Menu>() {{
            //一级
            add(new Menu(1,"河北","4399.com",0));
            add(new Menu(2,"河南","4399.com",0));
            add(new Menu(3,"陕西","4399.com",0));
            //二级
            add(new Menu(4,"石家庄","4399.com",1));
            add(new Menu(5,"保定","4399.com",1));
            add(new Menu(6,"郑州","4399.com",2));
            add(new Menu(7,"驻马店","4399.com",2));
            add(new Menu(8,"宝鸡","4399.com",3));
            add(new Menu(9,"西安","4399.com",3));
            //三级
            add(new Menu(10,"桥西区","4399.com",4));
            add(new Menu(11,"新华区","4399.com",4));
            add(new Menu(12,"满城区","4399.com",5));
            add(new Menu(13,"徐水区","4399.com",5));
            add( new Menu(13,"二七区","4399.com",6));
            add(new Menu(13,"金水区","4399.com",6));
            add(new Menu(13,"驿城区","4399.com",7));
            add(new Menu(13,"狗屎区","4399.com",7));
            add(new Menu(13,"陈仓区","4399.com",8));
            add( new Menu(13,"金台区","4399.com",8));
            add(new Menu(13,"雁塔区","4399.com",9));
            add(new Menu(13,"未央区","4399.com",9));
        }};

    }

    @Test
    public void test1() {
        init();
//        System.out.println(list.toString());

//        list.stream().filter(menu -> menu.getParentId() == 0).forEach(System.out :: println);
        //所有的一级菜单
        final List<Menu> collect = list.stream().filter(menu -> menu.getParentId() == 0).collect(Collectors.toList());
        //所有的一级菜单ID
        final Set<Integer> oneIdSet = list.stream().filter(menu -> menu.getParentId() == 0).map(menu -> menu.getId()).collect(Collectors.toSet());

        //所有的二级菜单ID
        final Set<Integer> twoIdSet = list.stream().filter(menu -> oneIdSet.contains(menu.getParentId())).map(menu -> menu.getId()).collect(Collectors.toSet());

        //所有的三级菜单ID
        final Set<Integer> threeIdSet = list.stream().filter(menu -> twoIdSet.contains(menu.getParentId())).map(menu -> menu.getId()).collect(Collectors.toSet());


//        list.stream().filter(menu -> menu.getParentId() == 0)
//                .forEach(menu -> menu.setList(list.stream().filter(menu1 -> menu1.getParentId().equals(menu.getId())).collect(Collectors.toList())));


//        System.out.println(collect);


    }

    /**
     * 二级
     */
    @Test
    public void test4() {
        init();
        final List<Menu> process = process(list);

        //打印结果
        process.forEach(menu -> {
            System.out.println(menu.getName());
            System.out.println(menu.getList());
        });
    }

    private List<Menu> process(List<Menu> list) {
        //所有的一级菜单
        List<Menu> firstList = list.stream().filter(menu -> menu.getParentId() == 0).collect(Collectors.toList());

        //给每个一级菜单添加他们的二级菜单
        firstList.forEach(firstMenu -> firstMenu.setList(
                list.stream().filter(menu -> menu.getParentId().equals(firstMenu.getId())).collect(Collectors.toList())
        ));

        return firstList;
    }

    /**
     * 常规操作
     */
    @Test
    public  void processFor() {
        init();
        List<Menu> resultList = new ArrayList<>();

        Iterator<Menu> iterator = list.iterator();
        while (iterator.hasNext()) {
            Menu next = iterator.next();
            if (next.getParentId() == 0) {
                resultList.add(next);
                iterator.remove();
            }
        }

        for (Menu menu : resultList) {
            for (Menu menu1 : list) {
                if (menu.getId().equals(menu1.getParentId())) {
                    if (menu.getList() == null) {
                        menu.setList(new ArrayList<>());
                    }
                    menu.getList().add(menu1);
                }
            }
        }

        //打印结果
        for (Menu menu : resultList) {
            System.out.println(menu.getName());
            System.out.println(menu.getList());
        }




    }


    /**
     * 三级
     */
    @Test
    public void test2() {
        init();
        //所有的一级菜单
        List<Menu> firstList = list.stream().filter(menu -> menu.getParentId() == 0).collect(Collectors.toList());

        //为每个一级菜单 添加他们的二级菜单
        firstList.forEach(firstMenu -> {
            List<Menu> secondList = list.stream().filter(menu -> menu.getParentId().equals(firstMenu.getId())).collect(Collectors.toList());

            //为每个二级菜单添加他们的三级菜单
            secondList.forEach(secondMenu -> secondMenu.setList(
                    list.stream().filter(menu -> menu.getParentId().equals(secondMenu.getId())).collect(Collectors.toList())
            ));

            firstMenu.setList(secondList);
        });

        for (Menu menu : firstList) {
            System.out.println(menu.getName());
            System.out.println(JSONObject.toJSONString(menu.getList()));
        }
    }

}
