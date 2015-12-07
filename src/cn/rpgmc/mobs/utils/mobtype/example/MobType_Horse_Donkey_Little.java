package cn.rpgmc.mobs.utils.mobtype.example;

import org.bukkit.entity.Horse;

public class MobType_Horse_Donkey_Little extends MobType_Horse {

	public MobType_Horse_Donkey_Little() {
		super("小驴", true);
		// TODO 自动生成的构造函数存根
	}

	@Override
	protected Horse modifyH(Horse h) {
		h.setVariant(Horse.Variant.DONKEY);
		return h;
	}


}
