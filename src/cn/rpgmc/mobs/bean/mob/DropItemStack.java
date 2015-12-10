
package cn.rpgmc.mobs.bean.mob;

import org.bukkit.inventory.ItemStack;

import cn.rpgmc.mobs.utils.rangeint.DropNum;

public class DropItemStack {
	private ItemStack item;
	private DropNum dropSize;
	private int i;

	public DropItemStack(ItemStack item, int i) {
		this.item = item;
		this.i = i;
		this.dropSize = null;
	}

	public DropItemStack(ItemStack item, int i, DropNum dropSize) {
		this.item = item;
		this.i = i;
		this.dropSize = dropSize;
	}

	public int getI() {
		return i;
	}

	public ItemStack getItem() {
		if (dropSize == null)
		return item;
		else {
			ItemStack i1 = item.clone();
			i1.setAmount(dropSize.getInt());
			return i1;
		}
	}

	public DropNum getDropSize() {
		return dropSize;
	}
	public void setI(int i) {
		this.i = i;
	}

	public void setItem(ItemStack item) {
		this.item = item;
	}
}
