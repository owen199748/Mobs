package cn.rpgmc.mobs.bean.mob;

public class BossName {
	private boolean isEnable;
	private String value;
	private int nearby;

	public BossName() {
		isEnable = false;
		value = "%name% ¡ì6[%bar%¡ì6] ¡ì4%maxhp%¡ìe/¡ìc%hp%";
		nearby = 20;
	}

	public BossName(boolean isEnable, String value, int nearby) {
		this.isEnable = isEnable;
		this.value = value;
		this.nearby = nearby;

	}

	public int getNearby() {
		return nearby;
	}

	public String getValue() {
		return value;
	}

	public boolean isEnable() {
		return isEnable;
	}
	public void setEnable(boolean isEnable) {
		this.isEnable = isEnable;
	}

	public void setNearby(int nearby) {
		this.nearby = nearby;
	}

	public void setValue(String value) {
		this.value = value;
	}


}
