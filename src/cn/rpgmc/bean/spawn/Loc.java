package cn.rpgmc.bean.spawn;

import org.bukkit.Bukkit;
import org.bukkit.Location;

public class Loc {
	private int x;
	private int y;
	private int z;
	private String world;

	public Loc(Location loc) {
		world = loc.getWorld().getName();
		x = loc.getBlockX();
		y = loc.getBlockY();
		z = loc.getBlockZ();
	}

	public Loc(String world, int x, int y, int z) {
		this.world = world;
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public String getWorld() {
		return world;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getZ() {
		return z;
	}

	public Location getLocation() {
		return new Location(Bukkit.getWorld(world), x, y, z);
	}

	public void setWorld(String world) {
		this.world = world;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public void setZ(int z) {
		this.z = z;
	}

}
