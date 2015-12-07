package cn.rpgmc.mobs.utils.mobtype.example;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Villager;

import cn.rpgmc.mobs.utils.mobtype.MobType;

public class MobType_Villager_1 extends MobType {
	public MobType_Villager_1() {
		super("¥Â√Ò1", EntityType.VILLAGER);
	}

	@Override
	public LivingEntity modify(LivingEntity e) {
		Villager v = (Villager) e;
		v.setProfession(Villager.Profession.BLACKSMITH);
		return e;
	}

}
