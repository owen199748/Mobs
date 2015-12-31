package pw.owen.mobs.utils.mobtype.example;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Ocelot;

import pw.owen.mobs.utils.mobtype.MobType;

public class MobType_Ocelot_Wild extends MobType {
	public MobType_Ocelot_Wild() {
		super("±ªÃ¨", EntityType.OCELOT);
	}

	@Override
	public LivingEntity modify(LivingEntity e) {
		Ocelot o = (Ocelot) e;
	o.setCatType(Ocelot.Type.WILD_OCELOT);
		return e;
	}

}
