package pw.owen.mobs.run;

import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntityTargetEvent;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.world.ChunkUnloadEvent;

import pw.owen.mobs.bean.mob.Mob;
import pw.owen.mobs.bean.skill.Skill;
import pw.owen.mobs.bean.spawn.Spawn;
import pw.owen.mobs.thread.TitleShows;
import pw.owen.mobs.utils.Send;

public class AutoListener implements Listener {
	private static AutoListener ls = null;

	public static AutoListener getLs() {

		return ls;

	}

	AutoListener() {
		ls = this;

	}

	@EventHandler
	public void ler(PlayerInteractEntityEvent e) {
		if (Mob.getMob(e.getRightClicked().getEntityId()) != null)
			e.setCancelled(true);
	}

	@EventHandler
	public void ecc(ChunkUnloadEvent e) {
		Entity[] es = e.getChunk().getEntities();
		for (int i = 0; i < es.length; i++)
			if (es[i].getMetadata("Mobs") != null
					&& es[i].getMetadata("Mobs").size() != 0) {
				e.setCancelled(true);
				return;
			}
	}

	@EventHandler
	public void e5(EntityDamageEvent e) {
		if(Mob.getMob(e.getEntity().getEntityId())!=null)
			if(Mob.getMob(e.getEntity().getEntityId()).isNoNatureDamage())
			{
				
				
				
				if (e.getCause() != EntityDamageEvent.DamageCause.CONTACT)
					if (e.getCause() != EntityDamageEvent.DamageCause.DROWNING)
						if (e.getCause() != EntityDamageEvent.DamageCause.FALL)
							if (e.getCause() != EntityDamageEvent.DamageCause.FALLING_BLOCK)
								if (e.getCause() != EntityDamageEvent.DamageCause.FIRE)
									if (e.getCause() != EntityDamageEvent.DamageCause.FIRE_TICK)
										if (e.getCause() != EntityDamageEvent.DamageCause.LAVA)
											if (e.getCause() != EntityDamageEvent.DamageCause.LIGHTNING)
												if (e.getCause() != EntityDamageEvent.DamageCause.MELTING)
													if (e.getCause() != EntityDamageEvent.DamageCause.SUFFOCATION)
														if (e.getCause() != EntityDamageEvent.DamageCause.VOID)
															return;

				e.setCancelled(true);
			}

	}

	@EventHandler
	public void ete(EntityTargetEvent ete) {

		if (Mob.isMob(ete.getEntity().getEntityId())) {
			Mob m = Mob.getMob(ete.getEntity().getEntityId());
			m.runSkill(Skill.TRIGGER_TARGET, ete.getTarget(), ete);

		}

		if (ete.getTarget() != null)
			if (Mob.isMob(ete.getTarget().getEntityId())) {
				Mob m = Mob.getMob(ete.getTarget().getEntityId());
				m.runSkill(Skill.TRIGGER_TARGET, ete.getEntity(), ete);

			}

	}

	@EventHandler
	public void pje(PlayerJoinEvent pje) {
		if (pje.getPlayer().isOp()) {
			if (!Main.getCfg().getString("Version")
					.equalsIgnoreCase(Main.getV())) {
				Send.sendPluginMessage(pje.getPlayer(),
						"插件与存在的配置版本不统一,请删除配置并重载插件.");
				Send.sendPluginMessage(pje.getPlayer(),
						"如果确认老版本配置支持当前版本请输入 /Mobs update 转换为新版本配置.");
				Send.sendPluginMessage(pje.getPlayer(),
						"插件与配置版本不一可能会导致恢复怪物时报错,输入/Mobs killall 杀死生物即可.");
			}

		}

	}

	@EventHandler
	public void bpe(PlayerInteractEvent bpe) {
		if (bpe.getClickedBlock() == null) {
			return;
		}
		if (bpe.getPlayer().isOp())
			if (bpe.getAction().name()
					.equalsIgnoreCase(Action.LEFT_CLICK_BLOCK.name()))

			{
				if (bpe.getItem() != null)
					if (bpe.getItem().getTypeId() == Main.getClickItem()) {

						Main.setO(bpe.getClickedBlock().getLocation());
						Send.sendPluginMessage(bpe.getPlayer(),
								"§6您已经选定了一个点:"
										+ bpe.getClickedBlock().getX()
										+ "x,"
										+ bpe.getClickedBlock().getY()
										+ "y,"
										+ bpe.getClickedBlock().getZ()
										+ "z,"
										+ "位于世界:"
										+ bpe.getClickedBlock().getWorld()
												.getName());

						bpe.setCancelled(true);
					}

			}

	}

	@EventHandler
	public void eceee(PlayerChangedWorldEvent e) {
		TitleShows.close(e.getPlayer());
	}

	@EventHandler
	public void edbee(EntityDamageByEntityEvent edbee) {


		if (Mob.isMob(edbee.getDamager().getEntityId())) {
			Mob m = Mob.getMob(edbee.getDamager().getEntityId());
			int dmg = m.getDmg();
			if (dmg != -1) {
				if (m.isAttrCover())
					edbee.setDamage(dmg);
				else
					edbee.setDamage(edbee.getDamage() + dmg);
			}

			m.runSkill(Skill.TRIGGER_ATTACK, edbee.getEntity(), edbee);


		}
		
		
		if (Mob.isMob(edbee.getEntity().getEntityId())) {
			Mob m = Mob.getMob(edbee.getEntity().getEntityId());
			m.runSkill(Skill.TRIGGER_HURT, edbee.getDamager(), edbee);
			
			if (m.isNoRepel() != null)
				if (m.isNoRepel()) {
					double dmg = edbee.getDamage();
					edbee.setCancelled(true);
					((LivingEntity) m.getE()).damage(dmg);
				}

		}
		
		
		
	}

	@EventHandler
	public void ede(EntityDeathEvent ede) {
		Spawn.removeEntity(ede.getEntity());
		if (Mob.isMob(ede.getEntity().getEntityId())) {
			Entity e = ede.getEntity();
			Mob m = Mob.getMob(e.getEntityId());

			if (m.isAttrCover())
				ede.setDroppedExp(m.getExp());
			else
				ede.setDroppedExp(ede.getDroppedExp() + m.getExp());

			m.runSkill(Skill.TRIGGER_HURT, ede.getEntity().getKiller(), ede);

			if (m.getDrop() != null) {
				ede.getDrops().clear();
				ede.getDrops().addAll(m.getDrop());
			}
			

	         
			m.onlyRemove();
		}

	}
}
