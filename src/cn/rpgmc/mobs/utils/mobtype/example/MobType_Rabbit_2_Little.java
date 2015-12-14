package cn.rpgmc.mobs.utils.mobtype.example;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Rabbit;

import cn.rpgmc.mobs.utils.mobtype.MobType;

public class MobType_Rabbit_2_Little extends MobType {
	public MobType_Rabbit_2_Little() {
		super("С�ڰ���", EntityType.RABBIT);
	}

	@Override
	public LivingEntity modify(LivingEntity e) {
		Rabbit r = (Rabbit) e;
		r.setBaby();
		r.setRabbitType(Rabbit.Type.BLACK_AND_WHITE);
		return e;
	}

}