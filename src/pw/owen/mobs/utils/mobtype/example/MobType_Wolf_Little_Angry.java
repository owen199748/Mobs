package pw.owen.mobs.utils.mobtype.example;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Wolf;

import pw.owen.mobs.utils.mobtype.MobType;

public class MobType_Wolf_Little_Angry extends MobType {
	public MobType_Wolf_Little_Angry() {
		super("·ßÅ­µÄÐ¡ÀÇ", EntityType.WOLF);
	}

	@Override
	public LivingEntity modify(LivingEntity e) {
		Wolf w = (Wolf)e;
		w.setBaby();
		w.setAngry(true);
		return e;
	}

}
