package pw.owen.mobs.run;

import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntityTargetEvent;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.world.ChunkLoadEvent;
import org.bukkit.event.world.ChunkUnloadEvent;

import pw.owen.mobs.bean.mob.Mob;
import pw.owen.mobs.bean.skill.Skill;
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
	public void ler15(ChunkLoadEvent e) {
		for (int i = 0; i < e.getChunk().getEntities().length; i++)
			Mob.changeEntity(e.getChunk().getEntities()[i]);

	}

	@EventHandler
	public void ler16(ChunkLoadEvent e) {
		for (int i = 0; i < e.getChunk().getEntities().length; i++)
			if (Mob.changeEntity(e.getChunk().getEntities()[i]))
 {// Bukkit.broadcastMessage("ChunkLoadEvent");
			}
	}

	@EventHandler
	public void lerXX16(ChunkUnloadEvent e) {

	}

	@EventHandler
	public void e5(EntityDamageEvent e) {
		Mob e1 = Mob.getMob(e.getEntity());
		if (e1 != null)
			if (e1.isNoNatureDamage())
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
	public void etse(CreatureSpawnEvent cse) {
		if(cse.getSpawnReason() != CreatureSpawnEvent.SpawnReason.CUSTOM)
			if(!Main.canSpawnEntity(cse.getEntity(),cse.getLocation().getWorld()))
				cse.setCancelled(true);
			}
	
	
	
	@EventHandler
	public void ete(EntityTargetEvent ete) {

		Mob m1 = Mob.getMob(ete.getEntity());
		Mob m2 = Mob.getMob(ete.getTarget());

		if (m1 != null)
			if (ete.getReason() != EntityTargetEvent.TargetReason.TARGET_DIED)
				if (!m1.isTarget(ete.getReason().name()))
 {
					ete.setCancelled(true);
					return;
				}

		if (m1 != null)
			m1.runSkill(Skill.TRIGGER_BETARGET, ete.getTarget(), ete);

		if (m2 != null)
			m2.runSkill(Skill.TRIGGER_TARGET, ete.getEntity(), ete);

	}

	@EventHandler
	public void pje(PlayerJoinEvent pje) {
		if (pje.getPlayer().isOp()) {
			if (!Main.getCfg().getString("Version")
					.equalsIgnoreCase(Main.getV())) {

				Send.sendPluginMessage(pje.getPlayer(),
						"插件与存在的配置版本不统一,请删除配置并重载插件.");
				Send.sendPluginMessage(pje.getPlayer(),
						"插件已經移除所有被保存的Mobs生物,請將配置升級成功后將會自動恢復.");
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
		Mob m1 = Mob.getMob(Mob.getId(edbee.getDamager()));

		if (m1 != null) {

			int dmg = m1.getDmg();
			if (dmg != -1) {
				if (m1.isAttrCover())
					edbee.setDamage(dmg);
				else
					edbee.setDamage(edbee.getDamage() + dmg);
			}

			m1.runSkill(Skill.TRIGGER_ATTACK, edbee.getEntity(), edbee);


		}
		
		Mob m2 = Mob.getMob(Mob.getId(edbee.getEntity()));
		if (m2 != null) {
			
			m2.runSkill(Skill.TRIGGER_HURT, edbee.getDamager(), edbee);

			if (m2.isNoRepel() != null)
				if (m2.isNoRepel()) {
					double dmg = edbee.getDamage();
					edbee.setCancelled(true);
					((LivingEntity) m2.getE()).damage(dmg);
				}

		}
		
		
		
	}


	@EventHandler
	public void ede(EntityDeathEvent ede) {
		Mob m = Mob.getMob(ede.getEntity());

		if (m != null) {
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
