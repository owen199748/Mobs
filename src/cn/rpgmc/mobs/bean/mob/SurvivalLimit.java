package cn.rpgmc.mobs.bean.mob;

public class SurvivalLimit {
	private boolean isNight = false;
	private boolean isDay = false;
	private boolean isRain = false;
	private boolean isSun = false;
	private boolean isThundering = false;

	public SurvivalLimit(boolean Night, boolean Day, boolean Rain, boolean Sun,
			boolean Thundering) {
		this.isNight = Night;
		this.isDay = Day;
		this.isRain = Rain;
		this.isSun = Sun;
		this.isThundering = Thundering;
	}

	public SurvivalLimit() {
		// TODO 自动生成的构造函数存根
	}

	public boolean isDay() {
		return isDay;
	}

	public void isDay(boolean isDay) {
		this.isDay = isDay;
	}

	public boolean isSun() {
		return isSun;
	}

	public void isSun(boolean isSun) {
		this.isSun = isSun;
	}

	public boolean isNight() {
		return isNight;
	}

	public void isNight(boolean isNight) {
		this.isNight = isNight;
	}

	public boolean isRain() {
		return isRain;
	}

	public void isRain(boolean isRain) {
		this.isRain = isRain;
	}

	public boolean isThundering() {
		return isThundering;
	}

	public void isThundering(boolean isThundering) {
		this.isThundering = isThundering;
	}

}
