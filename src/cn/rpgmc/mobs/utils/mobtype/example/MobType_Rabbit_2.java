package cn.rpgmc.mobs.utils.mobtype.example;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Rabbit;

import cn.rpgmc.mobs.utils.mobtype.MobType;

public class MobType_Rabbit_2 extends MobType {
	public MobType_Rabbit_2() {
		super("ºÚ°×ÍÃ", EntityType.RABBIT);
	}

	public static double getStartWith() {
		return 1.8;
	}
	@Override
	public LivingEntity modify(LivingEntity e) {
		Rabbit r = (Rabbit) e;
		r.setAdult();
		r.setRabbitType(Rabbit.Type.BLACK_AND_WHITE);
		return e;
	}

}
