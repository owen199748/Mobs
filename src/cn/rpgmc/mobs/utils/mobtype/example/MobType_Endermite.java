package cn.rpgmc.mobs.utils.mobtype.example;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;

import cn.rpgmc.mobs.utils.mobtype.MobType;

public class MobType_Endermite extends MobType {
	public MobType_Endermite() {
		super("Ä©Ó°òý", EntityType.ENDERMITE);
	}

	@Override
	public LivingEntity modify(LivingEntity e) {
		return e;
	}

}
