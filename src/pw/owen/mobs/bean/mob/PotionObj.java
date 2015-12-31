package pw.owen.mobs.bean.mob;

import org.bukkit.potion.PotionEffect;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(value = { "hibernateLazyInitializer" })
public class PotionObj {
	@JsonProperty
	private int amplifier;
	@JsonProperty
	private int duration;
	@JsonProperty
	private String name;

	public PotionObj() {
		// TODO 自动生成的构造函数存根
	}

	private PotionObj(String name, int a, int d) {
		this.name = name;
		this.amplifier = a;
		this.duration = d;
	}

	public String getName() {
		return name;
	}
	public int getAmplifier() {
		return amplifier;
	}

	public int getDuration() {
		return duration;
	}

	public static PotionObj load(PotionEffect pt) {
		return new PotionObj(pt.getType().getName(), pt.getAmplifier(),
				pt.getDuration());

	}

}
