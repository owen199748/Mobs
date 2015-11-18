package cn.rpgmc.mobs.bean.mob;

import org.bukkit.inventory.ItemStack;

/**
 * @author DayBreak
 *
 */
public class Eqpt {
	private ItemStack helmet = null;
	private ItemStack chestplate = null;
	private ItemStack leggings = null;
	private ItemStack boots = null;
	private ItemStack hand = null;

	public Eqpt() {

	}

	public Eqpt(ItemStack helmet, ItemStack chestplate, ItemStack leggings,
			ItemStack boots, ItemStack hand) {

		this.helmet = helmet;
		this.chestplate = chestplate;
		this.leggings = leggings;
		this.boots = boots;
		this.hand = hand;

	}

	public ItemStack getBoots() {
		return boots;
	}

	public ItemStack getChestplate() {
		return chestplate;
	}

	public ItemStack getHand() {
		return hand;
	}

	public ItemStack getHelmet() {
		return helmet;
	}

	public ItemStack getLeggings() {
		return leggings;
	}

	public void setBoots(ItemStack boots) {
		this.boots = boots;
	}

	public void setChestplate(ItemStack chestplate) {
		this.chestplate = chestplate;
	}

	public void setHand(ItemStack hand) {
		this.hand = hand;
	}

	public void setHelmet(ItemStack helmet) {
		this.helmet = helmet;
	}

	public void setLeggings(ItemStack leggings) {
		this.leggings = leggings;
	}

}
