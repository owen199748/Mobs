package cn.rpgmc.mobs.utils.mobtype.example;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Slime;

import cn.rpgmc.mobs.utils.mobtype.MobType;

public abstract class MobType_CanSize extends MobType {
	public MobType_CanSize(String str, EntityType e) {
		super(str, e);
	}

	@Override
	public LivingEntity modify(LivingEntity e) {
		return e;
	}



	public LivingEntity modify2(LivingEntity e, int size) {
		((Slime) e).setSize(size);
		return modify1(e);
	}


}
