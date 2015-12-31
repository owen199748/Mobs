package pw.owen.mobs.utils.mobtype.example;

import org.bukkit.entity.Cow;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;

import pw.owen.mobs.utils.mobtype.MobType;

public class MobType_Cow_Little extends MobType {
	public MobType_Cow_Little() {
		super("Сţ", EntityType.COW);
	}

	@Override
	public LivingEntity modify(LivingEntity e) {
		Cow c = (Cow) e;
		c.setBaby();
		return e;
	}

}
