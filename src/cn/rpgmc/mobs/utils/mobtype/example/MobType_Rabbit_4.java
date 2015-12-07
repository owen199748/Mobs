package cn.rpgmc.mobs.utils.mobtype.example;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Rabbit;

import cn.rpgmc.mobs.utils.mobtype.MobType;

public class MobType_Rabbit_4 extends MobType {
	public MobType_Rabbit_4() {
		super("ฝ๐อร", EntityType.RABBIT);
	}

	@Override
	public LivingEntity modify(LivingEntity e) {
		Rabbit r = (Rabbit) e;
		r.setAdult();
		r.setRabbitType(Rabbit.Type.GOLD);
		return e;
	}

}
