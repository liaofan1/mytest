package cn.itheima.ssm.service;

import java.util.List;

import cn.itheima.ssm.po.Item;

public interface ItemService {
	
	List<Item> queryItemList();
	
	//根据Id 查询商品
	Item queryItemById(Integer id);
	
	// 实现商品数据修改，保存数据库
	void updateItem(Item item);

}
