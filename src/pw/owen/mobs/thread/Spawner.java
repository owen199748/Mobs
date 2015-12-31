package pw.owen.mobs.thread;

import java.util.ArrayList;

import org.bukkit.Location;

import pw.owen.mobs.bean.mob.DropItemStack;
import pw.owen.mobs.bean.spawn.PointSpawn;
import pw.owen.mobs.bean.spawn.Spawn;
import pw.owen.mobs.bean.spawn.WorldSpawn;

public class Spawner implements Runnable {
	private long g = 0;
	private long startTime = 0;
	public static final int RUNS = 20;

	public Spawner() {

		startTime = System.currentTimeMillis();
	}

	@Override
	public void run() {
		Spawn spawn = Spawn.getNextSpawn();
		if (spawn == null)
			return;

		long n = System.currentTimeMillis() - startTime;
		if (spawn.getMobModel() == null)
			return;

		int a = spawn.getSurplusQuantity();// 计算刷新几个
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


}
