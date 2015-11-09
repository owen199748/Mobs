package cn.rpgmc.bean.mob;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.LivingEntity;
import org.bukkit.inventory.ItemStack;

import cn.rpgmc.bean.skill.Skill;
import cn.rpgmc.bean.spawn.PointSpawn;
import cn.rpgmc.bean.spawn.Spawn;
import cn.rpgmc.bean.spawn.WorldSpawn;

import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

//@JsonIgnoreProperties(value = { "hibernateLazyInitializer" })
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class MobSave {

	@JsonProperty
	private LivingEntitySave e;
	@JsonProperty
	private boolean isAttrCover = true;
	@JsonProperty
	private int dmg = 0;
	@JsonProperty
	private int exp = 0;
	@JsonProperty
	private List<String> skills = new ArrayList<String>();
	// @JsonProperty
	private ArrayList<ItemStack> drop;
	@JsonProperty
	private BossName bossName = new BossName();
	@JsonProperty
	private String sName;
	@JsonProperty
	private String id;
	@JsonProperty
	private String rider = null;
	@JsonProperty
	private String spawner = null;
	@JsonProperty
	private String spawnType = Spawn.POINTMOBCREATE;

	public MobSave(Mob mob) {
		this.e = new LivingEntitySave((LivingEntity) mob.getE());
		this.isAttrCover = mob.isAttrCover();
		this.dmg = mob.getDmg();
		this.exp = mob.getExp();
		HashMap<Skill, Long> ss = mob.getSkills1();
		Set<Skill> ks = ss.keySet();
		for (int i = 0; i < ks.size(); i++)
			skills.add(((Skill) ks.toArray()[i]).getsName());
		this.drop = mob.getDrop();
		this.bossName = mob.getBossName();
		this.sName = mob.getsName();
		this.id = mob.getId();
		this.rider = mob.getRider();
		if (mob.getSpawner() instanceof Spawn) {
			spawner = ((Spawn) mob.getSpawner()).getcName();
			spawnType = ((Spawn) mob.getSpawner()).getCreateType();

		}

	}

	public MobSave() {
		// TODO 自动生成的构造函数存根
	}
	public String toJson() {
		try {
			ObjectMapper mapper = new ObjectMapper();
			// mapper.setVisibilityChecker(VisibilityChecker.Std.defaultInstance()
			// .withFieldVisibility(Visibility.ANY));
			mapper.setVisibility(PropertyAccessor.FIELD, Visibility.ANY);
			return mapper.writeValueAsString(this);

		} catch (JsonProcessingException e1) {

			e1.printStackTrace();
			return null;
		}

	}

	public Mob toMob() {
		if (e == null)
			return null;
		
		if(MobModel.getMobModel(sName)==null)
			return null;
		Spawn spawn = null;
		if (spawner != null) {

			if (spawnType.equals(Spawn.POINTMOBCREATE)) {
				PointSpawn pSpawn = PointSpawn.getPointSpawn(spawner);
				spawn = pSpawn;
			} else if (spawnType.equals(Spawn.WORLDMOBCREATE)) {
				WorldSpawn wSpawn = WorldSpawn.getWorldSpawn(spawner);
				spawn = wSpawn;
			}
		}

		Mob m = new Mob(spawn, id, dmg, e.news(), drop, asSkills(skills), exp,
				isAttrCover, bossName, sName, rider);

		MobModel.getMobModel(sName).addMob(m);
		return m;

	}




	private ArrayList<Skill> asSkills(List<String> sk) {
		ArrayList<Skill> sk1 = new ArrayList<Skill>();
		for (int i = 0; i < sk.size(); i++)
			if (Skill.getSkill(sk.get(i)) != null)
				sk1.add(Skill.getSkill(sk.get(i)));

		return sk1;
	}

	private LivingEntity getEntity(int e) {
		List<World> ws = Bukkit.getServer().getWorlds();

		for (int i = 0; i < ws.size(); i++) {
			List<LivingEntity> es = ws.get(i).getLivingEntities();
			for (int l = 0; l < es.size(); l++)
				if (es.get(l).getEntityId() == e)
					return es.get(l);
		}
		return null;
	}

	public static MobSave fromJson(String s) {
		try {

			ObjectMapper mapper = new ObjectMapper();
			mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,
					false);
			mapper.configure(
					DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT,
					true);
			mapper.configure(
					DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
			// mapper.configure(DeserializationFeature.EAGER_DESERIALIZER_FETCH,
			// true);

			mapper.configure(DeserializationFeature.READ_ENUMS_USING_TO_STRING,
					true);
			mapper.configure(
					DeserializationFeature.READ_UNKNOWN_ENUM_VALUES_AS_NULL,
					true);

			mapper.configure(
					DeserializationFeature.USE_JAVA_ARRAY_FOR_JSON_ARRAY, true);

			mapper.configure(DeserializationFeature.WRAP_EXCEPTIONS, true);

			// mapper.setVisibilityChecker(VisibilityChecker.Std.defaultInstance()
			// .withFieldVisibility(Visibility.ANY));

			return mapper.readValue(s, MobSave.class);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<ItemStack> ADrop() {
		// TODO 自动生成的方法存根
		return drop;
	}

	public void BDrop(List<ItemStack> list) {
		drop = (ArrayList<ItemStack>) list;

	}

	public ItemStack[] AEQPT() {
		return e.AEQPT();
	}



	public void BEQPT(List<ItemStack> list) {
		ItemStack[] is = new ItemStack[list.size()];
		for (int i = 0; i < list.size(); i++) {
			is[i] = list.get(i);
		}

		e.BEQPT(is);

	}



}
