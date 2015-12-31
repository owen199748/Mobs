package pw.owen.mobs.utils.mobtype.example;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Villager;

import pw.owen.mobs.utils.mobtype.MobType;

public class MobType_Villager_2 extends MobType {
	public MobType_Villager_2() {
		super("¥Â√Ò2", EntityType.VILLAGER);
	}

	@Override
	public LivingEntity modify(LivingEntity e) {
		Villager v = (Villager) e;
		v.setProfession(Villager.Profession.BUTCHER);
		return e;
	}

}
