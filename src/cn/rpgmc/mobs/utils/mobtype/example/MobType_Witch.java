package cn.rpgmc.mobs.utils.mobtype.example;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;

import cn.rpgmc.mobs.utils.mobtype.MobType;

public class MobType_Witch extends MobType {
	public MobType_Witch() {
		super("Å®Î×", EntityType.WITCH);
	}

	@Override
	public LivingEntity modify(LivingEntity e) {
		return e;
	}

}
