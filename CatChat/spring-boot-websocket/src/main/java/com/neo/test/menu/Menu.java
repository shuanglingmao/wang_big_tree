package com.neo.test.menu;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Description: 菜单
 * @Version V1.0.0
 * @Since 1.0
 * @Date 2019/6/29 0029
 * @Author 毛双领 <shuangling.mao@ucarinc.com>
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Menu {
    private Integer id;
    private String name;
    private String url;
    private Integer parentId;

    private List<Menu> list;

    public Menu(Integer id, String name, String url, Integer parentId) {
        this.id = id;
        this.name = name;
        this.url = url;
        this.parentId = parentId;
    }
}
