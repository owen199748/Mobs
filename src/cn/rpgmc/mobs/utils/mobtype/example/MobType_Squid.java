package cn.rpgmc.mobs.utils.mobtype.example;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;

import cn.rpgmc.mobs.utils.mobtype.MobType;

public class MobType_Squid extends MobType {
	public MobType_Squid() {
		super("ˆœ”„", EntityType.SQUID);
	}

	@Override
	public LivingEntity modify(LivingEntity e) {
		return e;
	}

}
