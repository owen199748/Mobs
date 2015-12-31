package pw.owen.mobs.utils.mobtype.example;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Rabbit;

import pw.owen.mobs.utils.mobtype.MobType;

public class MobType_Rabbit_3_Little extends MobType {
	public MobType_Rabbit_3_Little() {
		super("аЁзиЭУ", EntityType.RABBIT);
	}

	public static double getStartWith() {
		return 1.8;
	}
	@Override
	public LivingEntity modify(LivingEntity e) {
		Rabbit r = (Rabbit) e;
		r.setBaby();
		r.setRabbitType(Rabbit.Type.BROWN);
		return e;
	}

}
