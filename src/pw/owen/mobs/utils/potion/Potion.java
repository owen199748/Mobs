package pw.owen.mobs.utils.potion;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.potion.PotionEffectType;

public class Potion {
	private static Map<String, String> key = new HashMap<String, String>();
	static {

		key.put("速度", PotionEffectType.SPEED.getName());
		key.put("吸收伤害", PotionEffectType.ABSORPTION.getName());
		key.put("失明", PotionEffectType.BLINDNESS.getName());
		key.put("抗性提升", PotionEffectType.DAMAGE_RESISTANCE.getName());
		key.put("加速挖掘", PotionEffectType.FAST_DIGGING.getName());
		key.put("火焰抗性", PotionEffectType.FIRE_RESISTANCE.getName());
		key.put("瞬间伤害", PotionEffectType.HARM.getName());
		key.put("瞬间治愈", PotionEffectType.HEAL.getName());
		key.put("饥饿", PotionEffectType.HUNGER.getName());
		key.put("伤害提升", PotionEffectType.INCREASE_DAMAGE.getName());
		key.put("隐身", PotionEffectType.INVISIBILITY.getName());
		key.put("跳跃提升", PotionEffectType.JUMP.getName());
		key.put("夜视", PotionEffectType.NIGHT_VISION.getName());
		key.put("中毒", PotionEffectType.POISON.getName());
		key.put("再生", PotionEffectType.REGENERATION.getName());
		key.put("饱腹", PotionEffectType.SATURATION.getName());
		key.put("缓慢", PotionEffectType.SLOW.getName());
		key.put("挖掘缓慢", PotionEffectType.SLOW_DIGGING.getName());
		key.put("水下呼吸", PotionEffectType.WATER_BREATHING.getName());
		key.put("虚弱", PotionEffectType.WEAKNESS.getName());
		key.put("凋零", PotionEffectType.WITHER.getName());
		key.put("反胃", PotionEffectType.CONFUSION.getName());
		key.put("生命提升", PotionEffectType.HEALTH_BOOST.getName());

	}

	public static String fromCh(String ch) {

		return key.get(ch);

	}

	public static String fromEn(String en) {
		Object[] keys = key.keySet().toArray();
		for (int i = 0; i < keys.length; i++)
			if (key.get(keys[i]).equals(en))
				return (String) keys[i];
		return null;

	}
}
