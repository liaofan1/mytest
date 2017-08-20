package cn.itheima.ssm.po;

import java.util.List;

public class ItemVo {
	
	private Item item;

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}
	


	public List<Item> getItemList() {
		return itemList;
	}

	public void setItemList(List<Item> itemList) {
		this.itemList = itemList;
	}



	// 包装商品集合list
	private List<Item> itemList;
}
