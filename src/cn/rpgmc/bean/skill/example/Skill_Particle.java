package cn.rpgmc.bean.skill.example;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import cn.rpgmc.bean.mob.Mob;
import cn.rpgmc.bean.skill.Skill;
import cn.rpgmc.utils.Calc;
import cn.rpgmc.utils.ParticleEffect.ParticleEffect;
import cn.rpgmc.utils.ParticleEffect.ParticleEffect.ParticleProperty;



public class Skill_Particle extends Skill {
	private String Ptype;
	private float speed;
	private int amount;
	private String x;
	private String y;
	private String z;
	private String world;

	public Skill_Particle() {

	}

	public Skill_Particle(ConfigurationSection cfg) {
		super(cfg);

	}

	public Skill_Particle(String s, ConfigurationSection cfg) {
		super(s, cfg);

	}

	@Override
	public String getType() {
		return "Particle";
	}
	@Override
	protected void skillNext(ConfigurationSection arg0) {
		// TODO 自动生成的方法存根

		this.Ptype = arg0.getString("Ptype");
		this.speed = (float) arg0.getDouble("speed");
		this.amount = arg0.getInt("amount");

		if (getCfg().getConfigurationSection("loc") == null)
			getCfg().createSection("loc");

		ConfigurationSection locm = getCfg().getConfigurationSection("loc");
		if (locm.getString("X") != null)
			if (locm.getString("Y") != null)
				if (locm.getString("Z") != null)
					if (locm.getString("World") != null) {
						this.world = locm.getString("World");
						this.x = locm.getString("X");
						this.y = locm.getString("Y");
						this.z = locm.getString("Z");
					}

	}

	@Override
	public String help() {
		// TODO 自动生成的方法存根

		String str = "";
		for (int i = 0; i < ParticleEffect.values().length; i++)
			if (ParticleEffect.values()[i].isSupported())
				if (ParticleEffect.values()[i]
						.hasProperty(ParticleProperty.DIRECTIONAL))
					if (!ParticleEffect.values()[i]
							.hasProperty(ParticleProperty.COLORABLE))
						if (!ParticleEffect.values()[i]
								.hasProperty(ParticleProperty.REQUIRES_DATA))
							if (!ParticleEffect.values()[i]
									.hasProperty(ParticleProperty.REQUIRES_WATER))
								if (i == 0)
									str = ParticleEffect.values()[i].getName();
								else
									str += ","
											+ ParticleEffect.values()[i]
													.getName();

		return "技能类型:粒子技能\n"
				+ "技能介绍:释放一个粒子效果.\n"
				+ "指令:\n"
				+ "  /mobs skill modify loc [x] [y] [z] [world] \n(其中x的%x1%代表使用技能者的x坐标,%x2%代表被施放者的x坐标(支持公式,例如:%x1%+(%x1%-%x2%)/3.),以此类推.world中的%w%代表所在世界)\n"
				+ "  /mobs skill modify type [type] \n 粒子类型"
				+ "  /mobs skill modify speed [speed] (最好在0-1之间,不包括0,该属性会影响粒子特效的大小)\n"
				+ "  /mobs skill modify amount [amount] 粒子数量\n" + "粒子类型:\n"
				+ str;

				

	}

	@Override
	protected void newSkillNext() {
		// TODO 自动生成的方法存根
		this.Ptype = "flame";
		this.speed = 0.1F;
		this.amount = 300;
		this.world = "%w%";
		this.x = "%x1%";
		this.y = "%y1%";
		this.z = "%z1%";
	}

	@Override
	protected void saveNext() {
		getCfg().set("Ptype", Ptype);
		getCfg().set("speed", speed);
		getCfg().set("amount", amount);
		if (getCfg().getConfigurationSection("loc") == null)
			getCfg().createSection("loc");

			getCfg().getConfigurationSection("loc").set("World", world);
		getCfg().getConfigurationSection("loc").set("X", x);
		getCfg().getConfigurationSection("loc").set("Y", y);
			getCfg().getConfigurationSection("loc").set("Z", z);


	}

	@Override
	public String seeNext() {
		return "  粒子类型:" + Ptype + "\n" + "  移动速度:" + speed + "\n" + "  粒子数量:"
				+ amount + "\n" + "  显示位置:\n" + "    X:" + x + "\n" + "    Y:"
				+ y + "\n" + "    Z:" + z + "\n" + "    World:" + world + "\n";
	}

	@Override
	protected boolean cmdElse(String[] args, Player p) {
		
		if (args[0].equalsIgnoreCase("loc")) {
			if (args.length != 5)
				return false;

			this.world = args[4];
			this.x = args[1];
			this.y = args[2];
			this.z = args[3];
			return true;
		}
		
		
		
		if (args.length != 2)
		return false;

		if (args[0].equalsIgnoreCase("Ptype")) {
			Ptype = args[1];
		} else if (args[0].equalsIgnoreCase("speed")) {
			speed = Float.parseFloat(args[1]);
		} else if (args[0].equalsIgnoreCase("amount")) {
			amount = Integer.parseInt(args[1]);
		} else
			return false;

	
		
		return true;
	}


	@Override
	public void run(Mob mob, Entity e) {
		ParticleEffect par = ParticleEffect.fromName(Ptype);
		if (par == null)
			return;
		if (!par.isSupported())
			return;
		if (!par.hasProperty(ParticleProperty.DIRECTIONAL))
			return;
		if (par.hasProperty(ParticleProperty.COLORABLE))
			return;
		if (par.hasProperty(ParticleProperty.REQUIRES_DATA))
			return;
		if (par.hasProperty(ParticleProperty.REQUIRES_WATER))
			return;

		World w = Bukkit.getWorld(world.replaceAll("%w%", e.getWorld()
				.getName()));
		Double X = Calc.calc(x.replaceAll("%x1%",
				String.valueOf(mob.getE().getLocation().getX())).replaceAll(
				"%x2%", String.valueOf(e.getLocation().getX())));
		Double Y = Calc.calc(y.replaceAll("%y1%",
				String.valueOf(mob.getE().getLocation().getY())).replaceAll(
				"%y2%", String.valueOf(e.getLocation().getY())));
		Double Z = Calc.calc(z.replaceAll("%z1%",
				String.valueOf(mob.getE().getLocation().getZ())).replaceAll(
				"%z2%", String.valueOf(e.getLocation().getZ())));
		Location loc=null;
		if (w != null)
			if (X != null)
				if (Y != null)
					if (Z != null)
						loc = new Location(w, X, Y, Z);
		if(loc==null)
			return;

		par.display(0, 0, 0, speed, amount, loc, 32);

	}


}
