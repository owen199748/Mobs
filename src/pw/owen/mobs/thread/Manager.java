package pw.owen.mobs.thread;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

import pw.owen.mobs.bean.mob.Mob;
import pw.owen.mobs.bean.skill.Skill;
import pw.owen.mobs.bean.spawn.PointSpawn;
import pw.owen.mobs.bean.spawn.Spawn;
import pw.owen.mobs.utils.Send;

public class Manager implements Runnable {

	private long startTime = 0;
	private long g = 0;
	public static final int RUNS = 10;

	public Manager() {

		startTime = System.currentTimeMillis();
	}

	@Override
	public void run() {

		bossNameShow();
		runCycleSkill();
		Skill.skillRunAll();

		g++;
		if (g % 5 == 0)
 {
			checkSpawn();
		}

		if (g % (60 * 2) == 0)
 {
			mobSave();
		}

		if (g == Long.MAX_VALUE)
			g = 0;
	}

	private void runCycleSkill() {
		ArrayList<Mob> ms = Mob.getMobs();
		for (int i = 0; i < ms.size(); i++) {
			Mob m = ms.get(i);
			if (m == null)
				continue;
			if (m.getE().isDead())
				continue;

			m.runSkill(Skill.TRIGGER_CYCLE, null, null);
		}

	}

	private void mobSave() {
		Mob.checkAll();
		try {
			Mob.saveAll();
		} catch (IOException e) {
			Send.sendConsole(Send.COLOR.RED + "���ﶯ̬����ʧ��.");
		}

	}

	private void checkSpawn() {
		Mob.checkAll();
		for (int i = 0; i < Spawn.getSpawns().size(); i++) {
			Spawn mob = Spawn.getSpawns().get(i);
			List<Mob> ml = new ArrayList<Mob>(mob.getMobs());
			for (int l = 0; l < ml.size(); l++) {
				Mob m = ml.get(l);

				if (m == null || m.getE() == null)
 {
					mob.getMobs().remove(m);
					continue;
				}
				// if (m.getE().isDead() || !m.getE().isValid()) {
				if (m.getE().getLocation().getChunk().isLoaded())
				if (!m.getNewEntity())
					m.remove();
				// Bukkit.broadcastMessage(m.getE().isDead() + "||"
				// + m.getE().isValid());

				// }
				else if (mob instanceof PointSpawn)
					((PointSpawn) mob).locationRepush();

		}


		}

	}

	private void bossNameShow() {
		ArrayList<Mob> ms = Mob.getMobs();
		for (int i = 0; i < ms.size(); i++) {
			Mob m = ms.get(i);
			if (m == null)
				continue;
			if (m.getE().isDead())
				continue;

			if(!m.getBossName().isEnable())
				continue;

			int nb = m.getBossName().getNearby();
			List<Player> ps = new ArrayList<Player>();
			if(nb==-1)
				ps = new ArrayList<Player>(Bukkit.getOnlinePlayers());
			else if(nb==0)
				ps = m.getE().getWorld().getPlayers();
			else
			{List<Entity> pss =m.getE().getNearbyEntities(nb,nb,nb);
			for(int l=0;l<pss.size();l++)
				if(pss.get(l).getType()==EntityType.PLAYER)
					ps.add((Player) pss.get(l));
			}
			
			for (int l = 0; l < ps.size(); l++)
 {
				// if (TitleShows.getPlayers().get(ps.get(l)) == null)
				TitleShows.show(ps.get(l), m);
			}
			
			



		}


		
		


	}




}
