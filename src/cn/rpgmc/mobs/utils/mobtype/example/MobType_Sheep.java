package cn.rpgmc.mobs.utils.mobtype.example;

import org.bukkit.DyeColor;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Sheep;

import cn.rpgmc.mobs.utils.mobtype.MobType;

public abstract class MobType_Sheep extends MobType {
	private DyeColor dy;
	private boolean baby;

	protected MobType_Sheep(String c, DyeColor dy, boolean baby) {
		super(c, EntityType.SHEEP);
		this.dy = dy;
		this.baby = baby;
	}

	@Override
	public LivingEntity modify(LivingEntity e) {
		Sheep s = (Sheep) e;

		if (baby)
			s.setBaby();
		else
			s.setAdult();
		s.setColor(dy);
		return e;
	}

}
