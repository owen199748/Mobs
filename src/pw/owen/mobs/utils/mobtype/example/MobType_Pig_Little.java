package pw.owen.mobs.utils.mobtype.example;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Pig;

import pw.owen.mobs.utils.mobtype.MobType;

public class MobType_Pig_Little extends MobType {
	public MobType_Pig_Little() {
		super("–°÷Ì", EntityType.PIG);
	}
	@Override
	public LivingEntity modify(LivingEntity e) {
		Pig c = (Pig) e;
		c.setBaby();
		return c;
	}

}
