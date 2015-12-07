package cn.rpgmc.mobs.utils.mobtype.example;

import org.bukkit.entity.Horse;

public class MobType_Horse_Undead extends MobType_Horse {

	public MobType_Horse_Undead() {
		super("僵尸马", false);
		// TODO 自动生成的构造函数存根
	}

	@Override
	protected Horse modifyH(Horse h) {
		h.setVariant(Horse.Variant.UNDEAD_HORSE);
		return h;
	}


}
