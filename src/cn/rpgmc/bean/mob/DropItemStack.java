package cn.rpgmc.bean.mob;

import org.bukkit.inventory.ItemStack;

public class DropItemStack {
	private ItemStack item;
	private int i;

	public DropItemStack(ItemStack item, int i) {
		this.item = item;
		this.i = i;
	}

	public int getI() {
		return i;
	}

	public ItemStack getItem() {
		return item;
	}

	public void setI(int i) {
		this.i = i;
	}

	public void setItem(ItemStack item) {
		this.item = item;
	}
}
