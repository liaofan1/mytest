package cn.itheima.ssm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.itheima.ssm.mapper.ItemMapper;
import cn.itheima.ssm.po.Item;
import cn.itheima.ssm.service.ItemService;

@Service
public class ItemServiceImpl implements ItemService{
	
	// 注入商品mapper接口
	@Autowired
	private ItemMapper itemMapper;

	@Override
	public List<Item> queryItemList() {
		// TODO Auto-generated method stub
		List<Item> list = itemMapper.selectByExample(null);
		return list;
	}

	@Override
	public Item queryItemById(Integer id) {
		Item item = itemMapper.selectByPrimaryKey(id);
		return item;
	}

	@Override
	public void updateItem(Item item) {
		itemMapper.updateByPrimaryKeySelective(item);	
	}

}
