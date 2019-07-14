package com.neo.mapper;

import com.neo.model.Inventory;
import com.neo.model.Order;

import java.util.List;

/**
 * @Description:
 * @Version V1.0.0
 * @Since 1.0
 * @Date 2019/7/1 0001
 * @Author 毛双领 <shuangling.mao>
 */
public interface InventoryMapper {
    void insert(Inventory order);
    List<Inventory> getAll();
    Inventory getOne(Integer id);
    void delete(Integer id);

    void update(Inventory inventory);
}
