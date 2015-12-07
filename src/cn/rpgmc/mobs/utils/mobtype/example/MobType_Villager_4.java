package cn.rpgmc.mobs.utils.mobtype.example;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Villager;

import cn.rpgmc.mobs.utils.mobtype.MobType;

public class MobType_Villager_4 extends MobType {
	public MobType_Villager_4() {
		super("¥Â√Ò4", EntityType.VILLAGER);
	}

	@Override
	public LivingEntity modify(LivingEntity e) {
		Villager v = (Villager) e;
		v.setProfession(Villager.Profession.LIBRARIAN);
		return e;
	}

}
