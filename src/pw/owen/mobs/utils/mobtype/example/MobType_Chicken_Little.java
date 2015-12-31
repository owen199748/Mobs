package pw.owen.mobs.utils.mobtype.example;

import org.bukkit.entity.Chicken;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;

import pw.owen.mobs.utils.mobtype.MobType;

public class MobType_Chicken_Little extends MobType {
	public MobType_Chicken_Little() {
		super("Ð¡¼¦", EntityType.CHICKEN);
	}
	@Override
	public LivingEntity modify(LivingEntity e) {
		Chicken c = (Chicken) e;
		c.setBaby();
		return c;
	}

}
