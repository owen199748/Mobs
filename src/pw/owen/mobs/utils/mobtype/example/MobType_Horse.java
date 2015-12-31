package pw.owen.mobs.utils.mobtype.example;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.Horse;
import org.bukkit.entity.LivingEntity;

import pw.owen.mobs.utils.mobtype.MobType;

public abstract class MobType_Horse extends MobType {
	private boolean baby;

	public MobType_Horse(String str, boolean baby) {
		super(str, EntityType.HORSE);
		this.baby = baby;
	}

	@Override
	public LivingEntity modify(LivingEntity e) {
		Horse h = (Horse)e;

		if (baby)
			h.setBaby();
		else
			h.setAdult();

		return modifyH(h);
	}

	protected abstract Horse modifyH(Horse h);

}
