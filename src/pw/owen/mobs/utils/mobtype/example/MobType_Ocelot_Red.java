package pw.owen.mobs.utils.mobtype.example;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Ocelot;

import pw.owen.mobs.utils.mobtype.MobType;

public class MobType_Ocelot_Red extends MobType {
	public MobType_Ocelot_Red() {
		super("ºìÉ«±ªÃ¨", EntityType.OCELOT);
	}

	@Override
	public LivingEntity modify(LivingEntity e) {
		Ocelot o = (Ocelot) e;
	o.setCatType(Ocelot.Type.RED_CAT);
		return e;
	}

}
