package pw.owen.mobs.utils.mobtype.example;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Rabbit;

import pw.owen.mobs.utils.mobtype.MobType;

public class MobType_Rabbit_4 extends MobType {
	public MobType_Rabbit_4() {
		super("ฝ๐อร", EntityType.RABBIT);
	}

	public static double getStartWith() {
		return 1.8;
	}
	@Override
	public LivingEntity modify(LivingEntity e) {
		Rabbit r = (Rabbit) e;
		r.setAdult();
		r.setRabbitType(Rabbit.Type.GOLD);
		return e;
	}

}
