package cn.rpgmc.mobs.utils.mobtype.example;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Villager;

import cn.rpgmc.mobs.utils.mobtype.MobType;

public class MobType_Villager_5 extends MobType {
	public MobType_Villager_5() {
		super("¥Â√Ò5", EntityType.VILLAGER);
	}

	@Override
	public LivingEntity modify(LivingEntity e) {
		Villager v = (Villager) e;
		v.setProfession(Villager.Profession.PRIEST);
		return e;
	}

}
