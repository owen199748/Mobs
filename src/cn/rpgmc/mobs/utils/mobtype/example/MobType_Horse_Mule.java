package cn.rpgmc.mobs.utils.mobtype.example;

import org.bukkit.entity.Horse;

public class MobType_Horse_Mule extends MobType_Horse {

	public MobType_Horse_Mule() {
		super("骡子", false);
		// TODO 自动生成的构造函数存根
	}

	@Override
	protected Horse modifyH(Horse h) {
		h.setVariant(Horse.Variant.MULE);
		return h;
	}


}
