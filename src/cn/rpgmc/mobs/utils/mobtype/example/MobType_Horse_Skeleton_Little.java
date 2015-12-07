package cn.rpgmc.mobs.utils.mobtype.example;

import org.bukkit.entity.Horse;

public class MobType_Horse_Skeleton_Little extends MobType_Horse {

	public MobType_Horse_Skeleton_Little() {
		super("小骷髅马", true);
		// TODO 自动生成的构造函数存根
	}

	@Override
	protected Horse modifyH(Horse h) {
		h.setVariant(Horse.Variant.SKELETON_HORSE);
		return h;
	}


}
