package pw.owen.mobs.bean.mob;

import java.util.ArrayList;
import java.util.Collection;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.LivingEntity;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import pw.owen.mobs.utils.mobtype.MobType;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(value = { "hibernateLazyInitializer" })
public class LivingEntitySave {
	@JsonProperty
	public String w = "";
	@JsonProperty
	public double x = 0;
	@JsonProperty
	public double y = 0;
	@JsonProperty
	public double z = 0;
	@JsonProperty
	public Collection<PotionObj> potionEffects;
	@JsonProperty
	public boolean canPickupItems = false;
	@JsonProperty
	public String customName = "";
	@JsonProperty
	public boolean customNameVisible = false;
	@JsonProperty
	public float fallDistance = 0;
	@JsonProperty
	public int fireTicks = 0;
	@JsonProperty
	public double health = 0;
	@JsonProperty
	public double lastDamage = 0;
	// @JsonProperty
	// public EntityDamageEvent lastDamageCause;
	@JsonProperty
	public double maxHealth = 0;
	@JsonProperty
	public int maximumAir = 0;
	@JsonProperty
	public int maximumNoDamageTicks = 0;
	@JsonProperty
	public int noDamageTicks = 0;
	@JsonProperty
	public boolean op = false;
	@JsonProperty
	public int remainingAir = 0;
	@JsonProperty
	public int ticksLived = 0;
	@JsonProperty
	public boolean removeWhenFarAway = false;
	// @JsonProperty
	// public Vector velocity;
	// @JsonProperty
	// public ItemStack is = null;
	@JsonProperty
	public String type = "";
	private ItemStack[] eqpt;

	public LivingEntitySave() {
		// TODO 自动生成的构造函数存根
	}

	public LivingEntitySave(LivingEntity e, MobType t) {
		

		w = e.getLocation().getWorld().getName();
		x = e.getLocation().getX();
		y = e.getLocation().getY();
		z = e.getLocation().getZ();
		eqpt = new ItemStack[5];
		for (int i = 0; i < e.getEquipment().getArmorContents().length; i++)
			eqpt[i] = e.getEquipment().getArmorContents()[i];
		eqpt[4] = e.getEquipment().getItemInHand();
		canPickupItems = e.getCanPickupItems();
		customName = e.getCustomName();
		customNameVisible = e.isCustomNameVisible();
		fallDistance = e.getFallDistance();
		fireTicks = e.getFireTicks();
		health = e.getHealth();
		lastDamage = e.getLastDamage();
		maxHealth = e.getMaxHealth();
		maximumAir = e.getMaximumAir();
		maximumNoDamageTicks = e.getMaximumNoDamageTicks();
		noDamageTicks = e.getNoDamageTicks();
			op = false;
		remainingAir = e.getRemainingAir();
		removeWhenFarAway = e.getRemoveWhenFarAway();
		ticksLived = e.getTicksLived();
		type = t.getName();

		// velocity = e.getVelocity();
		potionEffects = new ArrayList<PotionObj>();
		for (int i = 0; i < e.getActivePotionEffects().size(); i++)
			potionEffects.add(PotionObj.load((PotionEffect) e
					.getActivePotionEffects().toArray()[i]));
		// lastDamageCause = e.getLastDamageCause();
		 
	}

	public LivingEntity news() {
		if (Bukkit.getWorld(w) == null)
			return null;

		LivingEntity e = MobType.create(MobType.fromName(type), new Location(
				Bukkit.getWorld(w), x, y, z));
		if (eqpt.length >= 5)
		e.getEquipment().setItemInHand(eqpt[4]);
		ItemStack[] im = new ItemStack[4];
		if (eqpt.length >= 1)
			im[0] = eqpt[0];
		if (eqpt.length >= 2)
			im[1] = eqpt[1];
		if (eqpt.length >= 3)
			im[2] = eqpt[2];
		if (eqpt.length >= 4)
			im[3] = eqpt[3];
		e.getEquipment().setArmorContents(im);
		e.setCanPickupItems(canPickupItems);
		e.setCustomName(customName);
		e.setCustomNameVisible(customNameVisible);
		e.setFallDistance(fallDistance);
		e.setFireTicks(fireTicks);
		e.setMaxHealth(maxHealth);
		e.setHealth(health);
		e.setLastDamage(lastDamage);
		e.setMaximumAir(maximumAir);
		e.setMaximumNoDamageTicks(maximumNoDamageTicks);
		e.setNoDamageTicks(noDamageTicks);
		e.setRemainingAir(remainingAir);
		e.setRemoveWhenFarAway(removeWhenFarAway);
		if (ticksLived >= 1)
			e.setTicksLived(ticksLived);

		PotionObj pe;
		for (int i = 0; i < potionEffects.size(); i++)
			{pe=(PotionObj)potionEffects.toArray()[i];
			e.addPotionEffect(new PotionEffect(PotionEffectType.getByName(pe.getName()), pe.getDuration(), pe.getAmplifier()));}
		// e.setLastDamageCause(lastDamageCause);
		// e.setVelocity(velocity);

		return e;

	}

	public ItemStack[] AEQPT() {

		return eqpt;
	}


	public void BEQPT(ItemStack[] list) {
		eqpt = list;
	}



}
