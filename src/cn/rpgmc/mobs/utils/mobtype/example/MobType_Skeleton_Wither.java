package cn.rpgmc.mobs.utils.mobtype.example;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Skeleton;
import org.bukkit.entity.Skeleton.SkeletonType;

import cn.rpgmc.mobs.utils.mobtype.MobType;

public class MobType_Skeleton_Wither extends MobType {

	public MobType_Skeleton_Wither() {
		super("µòÁã÷¼÷Ã", EntityType.SKELETON);
	}

	@Override
	public LivingEntity modify(LivingEntity e) {
		Skeleton s = (Skeleton) e;
		s.setSkeletonType(SkeletonType.WITHER);
		return e;
	}

}
