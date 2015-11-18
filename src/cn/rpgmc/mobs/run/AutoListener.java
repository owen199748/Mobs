package cn.rpgmc.mobs.run;

import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntityTargetEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;

import cn.rpgmc.mobs.bean.mob.Mob;
import cn.rpgmc.mobs.bean.skill.Skill;
import cn.rpgmc.mobs.bean.spawn.Spawn;
import cn.rpgmc.mobs.utils.Send;

public class AutoListener implements Listener {
	private static AutoListener ls = null;

	public static AutoListener getLs() {

		return ls;

	}

	AutoListener() {
		ls = this;

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
						"如果确认老版本配置支持当前版本请输入 /Mobs reload 转换为新版本配置.");
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

			m.remove();
		}

	}
}
