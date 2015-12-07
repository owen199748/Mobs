package cn.rpgmc.mobs.thread;

import java.util.ArrayList;

import org.bukkit.Location;
import org.bukkit.scheduler.BukkitRunnable;

import cn.rpgmc.mobs.bean.mob.DropItemStack;
import cn.rpgmc.mobs.bean.mob.Mob;
import cn.rpgmc.mobs.bean.skill.Skill;
import cn.rpgmc.mobs.bean.spawn.PointSpawn;
import cn.rpgmc.mobs.bean.spawn.Spawn;
import cn.rpgmc.mobs.bean.spawn.WorldSpawn;

public class Spawner extends BukkitRunnable {
	private long g = 0;
	private long startTime = 0;
	public static final int RUNS = 20;

	public Spawner() {

		startTime = System.currentTimeMillis();
	}

	@Override
	public void run() {

		g++;
		if (g % 10 == 0)
			g = 0;

		Spawn spawn = Spawn.getNextSpawn();
		if (spawn == null)
			return;

		if (g == 0)
 {
			lowSpawn(spawn);
			Skill.skillRunAll();
		}
		long n = System.currentTimeMillis() - startTime;
		if (spawn.getMobModel() == null)
			return;

		int a = spawn.getAll() - spawn.getMobs().size();// 计算刷新几个
		int rr = 0;
		if (spawn.getCreateType().equalsIgnoreCase(Spawn.POINTMOBCREATE)) {
			rr = ((PointSpawn) spawn).getOne();
		} else if (spawn.getCreateType().equalsIgnoreCase(Spawn.WORLDMOBCREATE)) {
			rr = 1;
			a = 1;
		}

		if (a <= 0) {
			spawn.setNow(n);
			return;
		} else if (a > rr)
			a = rr;

		ArrayList<Location> locs = new ArrayList<Location>();

		if (spawn.getCreateType().equalsIgnoreCase(Spawn.POINTMOBCREATE))

		{
			PointSpawn pmob = (PointSpawn) spawn;
			for (int l = 0; l < a; l++) {
				locs.add(pmob.getRandomLocation());
			}

		} else if (spawn.getCreateType().equalsIgnoreCase(Spawn.WORLDMOBCREATE)) {

			for (int l = 0; l < a; l++) {
				locs.addAll(((WorldSpawn) spawn).getLoc());
			}

		}

		if (n - (spawn.getTime() * 50) > spawn.getNow()) {
			// 达到这个进行一次刷怪

			if (spawn.getMobModel() != null)
				for (int x = 0; x < locs.size(); x++) {
					ArrayList<DropItemStack> item = spawn.getMobModel()
							.getDrop();
					spawn.spawnMob(locs.get(x));
				}

			spawn.setNow(n);

		}

	}

	private void lowSpawn(Spawn spawn) {
		Spawn mob = spawn;
		ArrayList<Mob> mobs = mob.getMobs();
		for (int l = 0; l < mobs.size(); l++) {
			Mob m = mobs.get(mobs.size() - (l + 1));
			if (m.getE().isDead()) {
				mobs.remove(mobs.size() - (l + 1));

			} else {
				if (mob instanceof PointSpawn) {
					PointSpawn pmob = (PointSpawn) mob;

					pmob.test();
				}


			}

		}


	}

}
