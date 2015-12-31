package pw.owen.mobs.utils.mobtype.example;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Skeleton;
import org.bukkit.entity.Skeleton.SkeletonType;

import pw.owen.mobs.utils.mobtype.MobType;

public class MobType_Skeleton extends MobType {

	public MobType_Skeleton() {
		super("÷¼÷Ã", EntityType.SKELETON);
	}

	@Override
	public LivingEntity modify(LivingEntity e) {
		Skeleton s = (Skeleton) e;
		s.setSkeletonType(SkeletonType.NORMAL);
		return e;
	}

}
