package cn.rpgmc.mobs.utils.mobtype.example;

import org.bukkit.entity.Creeper;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;

import cn.rpgmc.mobs.utils.mobtype.MobType;

public class MobType_Creeper_Powered extends MobType {
	public MobType_Creeper_Powered() {
		super("∏ﬂ—π≈¿––’ﬂ", EntityType.CREEPER);
	}

	@Override
	public LivingEntity modify(LivingEntity e) {
		Creeper c = (Creeper) e;
		c.setPowered(true);
		return e;
	}

}
