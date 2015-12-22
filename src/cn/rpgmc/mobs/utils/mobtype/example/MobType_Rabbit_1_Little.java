package cn.rpgmc.mobs.utils.mobtype.example;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Rabbit;

import cn.rpgmc.mobs.utils.mobtype.MobType;

public class MobType_Rabbit_1_Little extends MobType {
	public MobType_Rabbit_1_Little() {
		super("Ð¡ºÚÍÃ", EntityType.RABBIT);
	}

	public static double getStartWith() {
		return 1.8;
	}
	@Override
	public LivingEntity modify(LivingEntity e) {
		Rabbit r = (Rabbit) e;
		r.setBaby();
		r.setRabbitType(Rabbit.Type.BLACK);
		return e;
	}

}
