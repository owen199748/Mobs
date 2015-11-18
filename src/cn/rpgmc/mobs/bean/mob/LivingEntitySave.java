package cn.rpgmc.mobs.bean.mob;

import java.util.Collection;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;

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
	public Collection<PotionEffect> potionEffects;
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

	public LivingEntitySave(LivingEntity e) {
		

		 w = e.getLocation().getWorld().getName(); x = e.getLocation().getX();
		y = e.getLocation().getY();
		z = e.getLocation().getZ();

		eqpt = e.getEquipment().getArmorContents();
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
		op = e.isOp();
		remainingAir = e.getRemainingAir();
		removeWhenFarAway = e.getRemoveWhenFarAway();
		ticksLived = e.getTicksLived();
		type = e.getType().name();

		// velocity = e.getVelocity();
		potionEffects = e.getActivePotionEffects();
		// lastDamageCause = e.getLastDamageCause();
		 
	}

	public LivingEntity news() {
		LivingEntity e = (LivingEntity) Bukkit.getWorld(w).spawnEntity(
				new Location(Bukkit.getWorld(w), x, y, z),
				EntityType.fromName(type));

		e.getEquipment().setArmorContents(eqpt);

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
		e.setOp(op);
		e.setRemainingAir(remainingAir);
		e.setRemoveWhenFarAway(removeWhenFarAway);
		if (ticksLived >= 1)
			e.setTicksLived(ticksLived);

		potionEffects = e.getActivePotionEffects();
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
