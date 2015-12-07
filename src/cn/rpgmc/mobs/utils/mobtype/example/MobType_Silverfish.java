package cn.rpgmc.mobs.utils.mobtype.example;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;

import cn.rpgmc.mobs.utils.mobtype.MobType;

public class MobType_Silverfish extends MobType {
	public MobType_Silverfish() {
		super("ó¼³æ", EntityType.SILVERFISH);
	}

	@Override
	public LivingEntity modify(LivingEntity e) {
		return e;
	}

}
