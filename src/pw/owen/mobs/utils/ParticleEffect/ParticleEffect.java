package pw.owen.mobs.utils.ParticleEffect;

/*      */ import java.lang.reflect.Constructor;
/*      */ import java.lang.reflect.Field;
/*      */ import java.lang.reflect.Method;
/*      */ import java.util.Arrays;
/*      */ import java.util.HashMap;
/*      */ import java.util.List;
/*      */ import java.util.Map;

/*      */ import org.bukkit.Bukkit;
/*      */ import org.bukkit.Location;
/*      */ import org.bukkit.Material;
/*      */ import org.bukkit.entity.Player;
/*      */ import org.bukkit.util.Vector;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public enum ParticleEffect
/*      */ {
/*   45 */   EXPLOSION_NORMAL(
/*   52 */     "explode", 0, -1, new ParticleProperty[] { ParticleProperty.DIRECTIONAL }), 
/*   53 */   EXPLOSION_LARGE(
/*   60 */     "largeexplode", 1, -1, new ParticleProperty[0]), 
/*   61 */   EXPLOSION_HUGE(
/*   68 */     "hugeexplosion", 2, -1, new ParticleProperty[0]), 
/*   69 */   FIREWORKS_SPARK(
/*   76 */     "fireworksSpark", 3, -1, new ParticleProperty[] { ParticleProperty.DIRECTIONAL }), 
/*   77 */   WATER_BUBBLE(
/*   84 */     "bubble", 4, -1, new ParticleProperty[] { ParticleProperty.DIRECTIONAL, ParticleProperty.REQUIRES_WATER }), 
/*   85 */   WATER_SPLASH(
/*   92 */     "splash", 5, -1, new ParticleProperty[] { ParticleProperty.DIRECTIONAL }), 
/*   93 */   WATER_WAKE(
/*  100 */     "wake", 6, 7, new ParticleProperty[] { ParticleProperty.DIRECTIONAL }), 
/*  101 */   SUSPENDED(
/*  108 */     "suspended", 7, -1, new ParticleProperty[] { ParticleProperty.REQUIRES_WATER }), 
/*  109 */   SUSPENDED_DEPTH(
/*  116 */     "depthSuspend", 8, -1, new ParticleProperty[] { ParticleProperty.DIRECTIONAL }), 
/*  117 */   CRIT(
/*  124 */     "crit", 9, -1, new ParticleProperty[] { ParticleProperty.DIRECTIONAL }), 
/*  125 */   CRIT_MAGIC(
/*  132 */     "magicCrit", 10, -1, new ParticleProperty[] { ParticleProperty.DIRECTIONAL }), 
/*  133 */   SMOKE_NORMAL(
/*  140 */     "smoke", 11, -1, new ParticleProperty[] { ParticleProperty.DIRECTIONAL }), 
/*  141 */   SMOKE_LARGE(
/*  148 */     "largesmoke", 12, -1, new ParticleProperty[] { ParticleProperty.DIRECTIONAL }), 
/*  149 */   SPELL(
/*  157 */     "spell", 13, -1, new ParticleProperty[0]), 
/*  158 */   SPELL_INSTANT(
/*  166 */     "instantSpell", 14, -1, new ParticleProperty[0]), 
/*  167 */   SPELL_MOB(
/*  175 */     "mobSpell", 15, -1, new ParticleProperty[] { ParticleProperty.COLORABLE }), 
/*  176 */   SPELL_MOB_AMBIENT(
/*  184 */     "mobSpellAmbient", 16, -1, new ParticleProperty[] { ParticleProperty.COLORABLE }), 
/*  185 */   SPELL_WITCH(
/*  193 */     "witchMagic", 17, -1, new ParticleProperty[0]), 
/*  194 */   DRIP_WATER(
/*  201 */     "dripWater", 18, -1, new ParticleProperty[0]), 
/*  202 */   DRIP_LAVA(
/*  209 */     "dripLava", 19, -1, new ParticleProperty[0]), 
/*  210 */   VILLAGER_ANGRY(
/*  217 */     "angryVillager", 20, -1, new ParticleProperty[0]), 
/*  218 */   VILLAGER_HAPPY(
/*  225 */     "happyVillager", 21, -1, new ParticleProperty[] { ParticleProperty.DIRECTIONAL }), 
/*  226 */   TOWN_AURA(
/*  233 */     "townaura", 22, -1, new ParticleProperty[] { ParticleProperty.DIRECTIONAL }), 
/*  234 */   NOTE(
/*  241 */     "note", 23, -1, new ParticleProperty[] { ParticleProperty.COLORABLE }), 
/*  242 */   PORTAL(
/*  249 */     "portal", 24, -1, new ParticleProperty[] { ParticleProperty.DIRECTIONAL }), 
/*  250 */   ENCHANTMENT_TABLE(
/*  257 */     "enchantmenttable", 25, -1, new ParticleProperty[] { ParticleProperty.DIRECTIONAL }), 
/*  258 */   FLAME(
/*  265 */     "flame", 26, -1, new ParticleProperty[] { ParticleProperty.DIRECTIONAL }), 
/*  266 */   LAVA(
/*  273 */     "lava", 27, -1, new ParticleProperty[0]), 
/*  274 */   FOOTSTEP(
/*  281 */     "footstep", 28, -1, new ParticleProperty[0]), 
/*  282 */   CLOUD(
/*  289 */     "cloud", 29, -1, new ParticleProperty[] { ParticleProperty.DIRECTIONAL }), 
/*  290 */   REDSTONE(
/*  297 */     "reddust", 30, -1, new ParticleProperty[] { ParticleProperty.COLORABLE }), 
/*  298 */   SNOWBALL(
/*  305 */     "snowballpoof", 31, -1, new ParticleProperty[0]), 
/*  306 */   SNOW_SHOVEL(
/*  313 */     "snowshovel", 32, -1, new ParticleProperty[] { ParticleProperty.DIRECTIONAL }), 
/*  314 */   SLIME(
/*  321 */     "slime", 33, -1, new ParticleProperty[0]), 
/*  322 */   HEART(
/*  329 */     "heart", 34, -1, new ParticleProperty[0]), 
/*  330 */   BARRIER(
/*  337 */     "barrier", 35, 8, new ParticleProperty[0]), 
/*  338 */   ITEM_CRACK(
/*  344 */     "iconcrack", 36, -1, new ParticleProperty[] { ParticleProperty.DIRECTIONAL, ParticleProperty.REQUIRES_DATA }), 
/*  345 */   BLOCK_CRACK(
/*  352 */     "blockcrack", 37, -1, new ParticleProperty[] { ParticleProperty.DIRECTIONAL, ParticleProperty.REQUIRES_DATA }), 
/*  353 */   BLOCK_DUST(
/*  359 */     "blockdust", 38, 7, new ParticleProperty[] { ParticleProperty.DIRECTIONAL, ParticleProperty.REQUIRES_DATA }), 
/*  360 */   WATER_DROP(
/*  367 */     "droplet", 39, 8, new ParticleProperty[0]), 
/*  368 */   ITEM_TAKE(
/*  374 */     "take", 40, 8, new ParticleProperty[0]), 
/*  375 */   MOB_APPEARANCE(
/*  383 */     "mobappearance", 41, 8, new ParticleProperty[0]);
/*      */ 
/*      */   private static final Map<String, ParticleEffect> NAME_MAP;
/*      */   private static final Map<Integer, ParticleEffect> ID_MAP;
/*      */   private final String name;
/*      */   private final int id;
/*      */   private final int requiredVersion;
/*      */   private final List<ParticleProperty> properties;
/*      */ 
/*  385 */   static { NAME_MAP = new HashMap();
/*  386 */     ID_MAP = new HashMap();
/*      */ 
/*  394 */     for (ParticleEffect effect : values()) {
/*  395 */       NAME_MAP.put(effect.name, effect);
/*  396 */       ID_MAP.put(Integer.valueOf(effect.id), effect);
/*      */     }
/*      */   }
/*      */ 
/*      */   private ParticleEffect(String name, int id, int requiredVersion, ParticleProperty[] properties)
/*      */   {
/*  409 */     this.name = name;
/*  410 */     this.id = id;
/*  411 */     this.requiredVersion = requiredVersion;
/*  412 */     this.properties = Arrays.asList(properties);
/*      */   }
/*      */ 
/*      */   public String getName()
/*      */   {
/*  421 */     return this.name;
/*      */   }
/*      */ 
/*      */   public int getId()
/*      */   {
/*  430 */     return this.id;
/*      */   }
/*      */ 
/*      */   public int getRequiredVersion()
/*      */   {
/*  439 */     return this.requiredVersion;
/*      */   }
/*      */ 
/*      */   public boolean hasProperty(ParticleProperty property)
/*      */   {
/*  448 */     return this.properties.contains(property);
/*      */   }
/*      */ 
/*      */   public boolean isSupported()
/*      */   {

/*  457 */     if (this.requiredVersion == -1) {
/*  458 */       return true;
/*      */     }
/*  460 */     return ParticlePacket.getVersion() >= this.requiredVersion;
/*      */   }
/*      */ 
/*      */   public static ParticleEffect fromName(String name)
/*      */   {
/*  470 */     for (Map.Entry entry : NAME_MAP.entrySet())
/*  471 */       if (((String)entry.getKey()).equalsIgnoreCase(name))
/*      */       {
/*  474 */         return (ParticleEffect)entry.getValue();
/*      */       }
/*  476 */     return null;
/*      */   }
/*      */ 
/*      */   public static ParticleEffect fromId(int id)
/*      */   {
/*  486 */     for (Map.Entry entry : ID_MAP.entrySet())
/*  487 */       if (((Integer)entry.getKey()).intValue() == id)
/*      */       {
/*  490 */         return (ParticleEffect)entry.getValue();
/*      */       }
/*  492 */     return null;
/*      */   }
/*      */ 
/*      */   private static boolean isWater(Location location)
/*      */   {
/*  502 */     Material material = location.getBlock().getType();
/*  503 */     return (material == Material.WATER) || (material == Material.STATIONARY_WATER);
/*      */   }
/*      */ 
/*      */   private static boolean isLongDistance(Location location, List<Player> players)
/*      */   {
/*  513 */     String world = location.getWorld().getName();
/*  514 */     for (Player player : players) {
/*  515 */       Location playerLocation = player.getLocation();
/*  516 */       if ((world.equals(playerLocation.getWorld().getName())) && (playerLocation.distanceSquared(location) >= 65536.0D))
/*      */       {
/*  519 */         return true;
/*      */       }
/*      */     }
/*  521 */     return false;
/*      */   }
/*      */ 
/*      */   private static boolean isDataCorrect(ParticleEffect effect, ParticleData data)
/*      */   {
/*  532 */     return ((effect == BLOCK_CRACK) || (effect == BLOCK_DUST)) && (((data instanceof BlockData)) || ((effect == ITEM_CRACK) && ((data instanceof ItemData))));
/*      */   }
/*      */ 
/*      */   private static boolean isColorCorrect(ParticleEffect effect, ParticleColor color)
/*      */   {
/*  543 */     return ((effect == SPELL_MOB) || (effect == SPELL_MOB_AMBIENT) || (effect == REDSTONE)) && (((color instanceof OrdinaryColor)) || ((effect == NOTE) && ((color instanceof NoteColor))));
/*      */   }
/*      */ 
/*      */   public void display(float offsetX, float offsetY, float offsetZ, float speed, int amount, Location center, double range)
/*      */     throws ParticleEffect.ParticleVersionException, ParticleEffect.ParticleDataException, IllegalArgumentException
/*      */   {
/*  563 */     if (!isSupported()) {
			/* 564 */throw new ParticleVersionException(
					"This particle effect is not supported by your server version");
/*      */     }
/*  566 */     if (hasProperty(ParticleProperty.REQUIRES_DATA)) {
/*  567 */       throw new ParticleDataException("This particle effect requires additional data");
/*      */     }
/*  569 */     if ((hasProperty(ParticleProperty.REQUIRES_WATER)) && (!isWater(center))) {
/*  570 */       throw new IllegalArgumentException("There is no water at the center location");
/*      */     }
/*  572 */     new ParticlePacket(this, offsetX, offsetY, offsetZ, speed, amount, range > 256.0D, null).sendTo(center, range);
/*      */   }
/*      */ 
/*      */   public void display(float offsetX, float offsetY, float offsetZ, float speed, int amount, Location center, List<Player> players)
/*      */     throws ParticleEffect.ParticleVersionException, ParticleEffect.ParticleDataException, IllegalArgumentException
/*      */   {
/*  592 */     if (!isSupported()) {
			/* 593 */throw new ParticleVersionException(
					"This particle effect is not supported by your server version");
/*      */     }
/*  595 */     if (hasProperty(ParticleProperty.REQUIRES_DATA)) {
/*  596 */       throw new ParticleDataException("This particle effect requires additional data");
/*      */     }
/*  598 */     if ((hasProperty(ParticleProperty.REQUIRES_WATER)) && (!isWater(center))) {
/*  599 */       throw new IllegalArgumentException("There is no water at the center location");
/*      */     }
/*  601 */     new ParticlePacket(this, offsetX, offsetY, offsetZ, speed, amount, isLongDistance(center, players), null).sendTo(center, players);
/*      */   }
/*      */ 
/*      */   public void display(float offsetX, float offsetY, float offsetZ, float speed, int amount, Location center, Player[] players)
/*      */     throws ParticleEffect.ParticleVersionException, ParticleEffect.ParticleDataException, IllegalArgumentException
/*      */   {
/*  620 */     display(offsetX, offsetY, offsetZ, speed, amount, center, Arrays.asList(players));
/*      */   }
/*      */ 
/*      */   public void display(Vector direction, float speed, Location center, double range)
/*      */     throws ParticleEffect.ParticleVersionException, ParticleEffect.ParticleDataException, IllegalArgumentException
/*      */   {
/*  637 */     if (!isSupported()) {
			/* 638 */


			 throw new ParticleVersionException(
					"This particle effect is not supported by your server version");
			/*      */}


    if (hasProperty(ParticleProperty.REQUIRES_DATA)) {
			throw new ParticleDataException(
					"This particle effect requires additional data");
		}
		/* 643 */if (!hasProperty(ParticleProperty.DIRECTIONAL)) {
			/* 644 */throw new IllegalArgumentException(
					"This particle effect is not directional");
			/*      */}
		/* 646 */if ((hasProperty(ParticleProperty.REQUIRES_WATER))
				&& (!isWater(center))) {
			/* 647 */throw new IllegalArgumentException(
					"There is no water at the center location");
			/*      */}
		/* 649 */new ParticlePacket(this, direction, speed, range > 256.0D,
				null).sendTo(center, range);




/*      */   }
/*      */ 
/*      */   public void display(Vector direction, float speed, Location center, List<Player> players)
/*      */     throws ParticleEffect.ParticleVersionException, ParticleEffect.ParticleDataException, IllegalArgumentException
/*      */   {
/*  666 */     if (!isSupported()) {
			/* 667 */throw new ParticleVersionException(
					"This particle effect is not supported by your server version");
/*      */     }
/*  669 */     if (hasProperty(ParticleProperty.REQUIRES_DATA)) {
/*  670 */       throw new ParticleDataException("This particle effect requires additional data");
/*      */     }
/*  672 */     if (!hasProperty(ParticleProperty.DIRECTIONAL)) {
/*  673 */       throw new IllegalArgumentException("This particle effect is not directional");
/*      */     }
/*  675 */     if ((hasProperty(ParticleProperty.REQUIRES_WATER)) && (!isWater(center))) {
/*  676 */       throw new IllegalArgumentException("There is no water at the center location");
/*      */     }
/*  678 */     new ParticlePacket(this, direction, speed, isLongDistance(center, players), null).sendTo(center, players);
/*      */   }
/*      */ 
/*      */   public void display(Vector direction, float speed, Location center, Player[] players)
/*      */     throws ParticleEffect.ParticleVersionException, ParticleEffect.ParticleDataException, IllegalArgumentException
/*      */   {
/*  694 */     display(direction, speed, center, Arrays.asList(players));
/*      */   }
/*      */ 
/*      */   public void display(ParticleColor color, Location center, double range)
/*      */     throws ParticleEffect.ParticleVersionException, ParticleEffect.ParticleColorException
/*      */   {
/*  709 */     if (!isSupported()) {
/*  710 */       throw new ParticleVersionException("This particle effect is not supported by your server version");
/*      */     }
/*  712 */     if (!hasProperty(ParticleProperty.COLORABLE)) {
/*  713 */       throw new ParticleColorException("This particle effect is not colorable");
/*      */     }
/*  715 */     if (!isColorCorrect(this, color)) {
/*  716 */       throw new ParticleColorException("The particle color type is incorrect");
/*      */     }
/*  718 */     new ParticlePacket(this, color, range > 256.0D).sendTo(center, range);
/*      */   }
/*      */ 
/*      */   public void display(ParticleColor color, Location center, List<Player> players)
/*      */     throws ParticleEffect.ParticleVersionException, ParticleEffect.ParticleColorException
/*      */   {
/*  733 */     if (!isSupported()) {
/*  734 */       throw new ParticleVersionException("This particle effect is not supported by your server version");
/*      */     }
/*  736 */     if (!hasProperty(ParticleProperty.COLORABLE)) {
/*  737 */       throw new ParticleColorException("This particle effect is not colorable");
/*      */     }
/*  739 */     if (!isColorCorrect(this, color)) {
/*  740 */       throw new ParticleColorException("The particle color type is incorrect");
/*      */     }
/*  742 */     new ParticlePacket(this, color, isLongDistance(center, players)).sendTo(center, players);
/*      */   }
/*      */ 
/*      */   public void display(ParticleColor color, Location center, Player[] players)
/*      */     throws ParticleEffect.ParticleVersionException, ParticleEffect.ParticleColorException
/*      */   {
/*  756 */     display(color, center, Arrays.asList(players));
/*      */   }
/*      */ 
/*      */   public void display(ParticleData data, float offsetX, float offsetY, float offsetZ, float speed, int amount, Location center, double range)
/*      */     throws ParticleEffect.ParticleVersionException, ParticleEffect.ParticleDataException
/*      */   {
/*  776 */     if (!isSupported()) {
/*  777 */       throw new ParticleVersionException("This particle effect is not supported by your server version");
/*      */     }
/*  779 */     if (!hasProperty(ParticleProperty.REQUIRES_DATA)) {
/*  780 */       throw new ParticleDataException("This particle effect does not require additional data");
/*      */     }
/*  782 */     if (!isDataCorrect(this, data)) {
/*  783 */       throw new ParticleDataException("The particle data type is incorrect");
/*      */     }
/*  785 */     new ParticlePacket(this, offsetX, offsetY, offsetZ, speed, amount, range > 256.0D, data).sendTo(center, range);
/*      */   }
/*      */ 
/*      */   public void display(ParticleData data, float offsetX, float offsetY, float offsetZ, float speed, int amount, Location center, List<Player> players)
/*      */     throws ParticleEffect.ParticleVersionException, ParticleEffect.ParticleDataException
/*      */   {
/*  805 */     if (!isSupported()) {
/*  806 */       throw new ParticleVersionException("This particle effect is not supported by your server version");
/*      */     }
/*  808 */     if (!hasProperty(ParticleProperty.REQUIRES_DATA)) {
/*  809 */       throw new ParticleDataException("This particle effect does not require additional data");
/*      */     }
/*  811 */     if (!isDataCorrect(this, data)) {
/*  812 */       throw new ParticleDataException("The particle data type is incorrect");
/*      */     }
/*  814 */     new ParticlePacket(this, offsetX, offsetY, offsetZ, speed, amount, isLongDistance(center, players), data).sendTo(center, players);
/*      */   }
/*      */ 
/*      */   public void display(ParticleData data, float offsetX, float offsetY, float offsetZ, float speed, int amount, Location center, Player[] players)
/*      */     throws ParticleEffect.ParticleVersionException, ParticleEffect.ParticleDataException
/*      */   {
/*  833 */     display(data, offsetX, offsetY, offsetZ, speed, amount, center, Arrays.asList(players));
/*      */   }
/*      */ 
/*      */   public void display(ParticleData data, Vector direction, float speed, Location center, double range)
/*      */     throws ParticleEffect.ParticleVersionException, ParticleEffect.ParticleDataException
/*      */   {
/*  850 */     if (!isSupported()) {
/*  851 */       throw new ParticleVersionException("This particle effect is not supported by your server version");
/*      */     }
/*  853 */     if (!hasProperty(ParticleProperty.REQUIRES_DATA)) {
/*  854 */       throw new ParticleDataException("This particle effect does not require additional data");
/*      */     }
/*  856 */     if (!isDataCorrect(this, data)) {
/*  857 */       throw new ParticleDataException("The particle data type is incorrect");
/*      */     }
/*  859 */     new ParticlePacket(this, direction, speed, range > 256.0D, data).sendTo(center, range);
/*      */   }
/*      */ 
/*      */   public void display(ParticleData data, Vector direction, float speed, Location center, List<Player> players)
/*      */     throws ParticleEffect.ParticleVersionException, ParticleEffect.ParticleDataException
/*      */   {
/*  876 */     if (!isSupported()) {
/*  877 */       throw new ParticleVersionException("This particle effect is not supported by your server version");
/*      */     }
/*  879 */     if (!hasProperty(ParticleProperty.REQUIRES_DATA)) {
/*  880 */       throw new ParticleDataException("This particle effect does not require additional data");
/*      */     }
/*  882 */     if (!isDataCorrect(this, data)) {
/*  883 */       throw new ParticleDataException("The particle data type is incorrect");
/*      */     }
/*  885 */     new ParticlePacket(this, direction, speed, isLongDistance(center, players), data).sendTo(center, players);
/*      */   }
/*      */ 
/*      */   public void display(ParticleData data, Vector direction, float speed, Location center, Player[] players)
/*      */     throws ParticleEffect.ParticleVersionException, ParticleEffect.ParticleDataException
/*      */   {
/*  901 */     display(data, direction, speed, center, Arrays.asList(players));
/*      */   }
/*      */ 
/*      */   public static final class BlockData extends ParticleEffect.ParticleData
/*      */   {
/*      */     public BlockData(Material material, byte data)
/*      */       throws IllegalArgumentException
/*      */     {
			/* 1033 */super(material, data);
/* 1034 */       if (!material.isBlock())
/* 1035 */         throw new IllegalArgumentException("The material is not a block");
/*      */     }
/*      */   }
/*      */ 
/*      */   public static final class ItemData extends ParticleEffect.ParticleData
/*      */   {
/*      */     public ItemData(Material material, byte data)
/*      */     {
			/* 1011 */super(material, data);
/*      */     }
/*      */   }
/*      */ 
/*      */   public static final class NoteColor extends ParticleEffect.ParticleColor
/*      */   {
/*      */     private final int note;
/*      */ 
/*      */     public NoteColor(int note)
/*      */       throws IllegalArgumentException
/*      */     {
/* 1192 */       if (note < 0) {
/* 1193 */         throw new IllegalArgumentException("The note value is lower than 0");
/*      */       }
/* 1195 */       if (note > 24) {
/* 1196 */         throw new IllegalArgumentException("The note value is higher than 24");
/*      */       }
/* 1198 */       this.note = note;
/*      */     }
/*      */ 
/*      */     public float getValueX()
/*      */     {
/* 1208 */       return this.note / 24.0F;
/*      */     }
/*      */ 
/*      */     public float getValueY()
/*      */     {
/* 1218 */       return 0.0F;
/*      */     }
/*      */ 
/*      */     public float getValueZ()
/*      */     {
/* 1228 */       return 0.0F;
/*      */     }
/*      */   }
/*      */ 
/*      */   public static final class OrdinaryColor extends ParticleEffect.ParticleColor
/*      */   {
/*      */     private final int red;
/*      */     private final int green;
/*      */     private final int blue;
/*      */ 
/*      */     public OrdinaryColor(int red, int green, int blue)
/*      */       throws IllegalArgumentException
/*      */     {
/* 1093 */       if (red < 0) {
/* 1094 */         throw new IllegalArgumentException("The red value is lower than 0");
/*      */       }
/* 1096 */       if (red > 255) {
/* 1097 */         throw new IllegalArgumentException("The red value is higher than 255");
/*      */       }
/* 1099 */       this.red = red;
/* 1100 */       if (green < 0) {
/* 1101 */         throw new IllegalArgumentException("The green value is lower than 0");
/*      */       }
/* 1103 */       if (green > 255) {
/* 1104 */         throw new IllegalArgumentException("The green value is higher than 255");
/*      */       }
/* 1106 */       this.green = green;
/* 1107 */       if (blue < 0) {
/* 1108 */         throw new IllegalArgumentException("The blue value is lower than 0");
/*      */       }
/* 1110 */       if (blue > 255) {
/* 1111 */         throw new IllegalArgumentException("The blue value is higher than 255");
/*      */       }
/* 1113 */       this.blue = blue;
/*      */     }
/*      */ 
/*      */     public int getRed()
/*      */     {
/* 1122 */       return this.red;
/*      */     }
/*      */ 
/*      */     public int getGreen()
/*      */     {
/* 1131 */       return this.green;
/*      */     }
/*      */ 
/*      */     public int getBlue()
/*      */     {
/* 1140 */       return this.blue;
/*      */     }
/*      */ 
/*      */     public float getValueX()
/*      */     {
/* 1150 */       return this.red / 255.0F;
/*      */     }
/*      */ 
/*      */     public float getValueY()
/*      */     {
/* 1160 */       return this.green / 255.0F;
/*      */     }
/*      */ 
/*      */     public float getValueZ()
/*      */     {
/* 1170 */       return this.blue / 255.0F;
/*      */     }
/*      */   }
/*      */ 
/*      */   public static abstract class ParticleColor
/*      */   {
/*      */     public abstract float getValueX();
/*      */ 
/*      */     public abstract float getValueY();
/*      */ 
/*      */     public abstract float getValueZ();
/*      */   }
/*      */ 
/*      */   private static final class ParticleColorException extends RuntimeException
/*      */   {
/*      */     private static final long serialVersionUID = 3203085387160737484L;
/*      */ 
/*      */     public ParticleColorException(String message)
/*      */     {
/* 1271 */       super();
/*      */     }
/*      */   }
/*      */ 
/*      */   public static abstract class ParticleData
/*      */   {
/*      */     private final Material material;
/*      */     private final byte data;
/*      */     private final int[] packetData;
/*      */ 
/*      */     public ParticleData(Material material, byte data)
/*      */     {
/*  952 */       this.material = material;
/*  953 */       this.data = data;
/*  954 */       this.packetData = new int[] { material.getId(), data };
/*      */     }
/*      */ 
/*      */     public Material getMaterial()
/*      */     {
/*  963 */       return this.material;
/*      */     }
/*      */ 
/*      */     public byte getData()
/*      */     {
/*  972 */       return this.data;
/*      */     }
/*      */ 
/*      */     public int[] getPacketData()
/*      */     {
/*  981 */       return this.packetData;
/*      */     }
/*      */ 
/*      */     public String getPacketDataString()
/*      */     {
/*  990 */       return "_" + this.packetData[0] + "_" + this.packetData[1];
/*      */     }
/*      */   }
/*      */ 
/*      */   private static final class ParticleDataException extends RuntimeException
/*      */   {
/*      */     private static final long serialVersionUID = 3203085387160737484L;
/*      */ 
/*      */     public ParticleDataException(String message)
/*      */     {
/* 1250 */       super();
/*      */     }
/*      */   }
/*      */ 
/*      */   public static final class ParticlePacket
/*      */   {
/*      */     private static int version;
/*      */     private static Class<?> enumParticle;
/*      */     private static Constructor<?> packetConstructor;
/*      */     private static Method getHandle;
/*      */     private static Field playerConnection;
/*      */     private static Method sendPacket;
/*      */     private static boolean initialized;
/*      */     private final ParticleEffect effect;
/*      */     private final float offsetX;
/*      */     private final float offsetY;
/*      */     private final float offsetZ;
/*      */     private final float speed;
/*      */     private final int amount;
/*      */     private final boolean longDistance;
/*      */     private final ParticleEffect.ParticleData data;
/*      */     private Object packet;
/*      */ 
/*      */     public ParticlePacket(ParticleEffect effect, float offsetX, float offsetY, float offsetZ, float speed, int amount, boolean longDistance, ParticleEffect.ParticleData data)
/*      */       throws IllegalArgumentException
/*      */     {
/* 1337 */       initialize();
/* 1338 */       if (speed < 0.0F) {
/* 1339 */         throw new IllegalArgumentException("The speed is lower than 0");
/*      */       }
/* 1341 */       if (amount < 0) {
/* 1342 */         throw new IllegalArgumentException("The amount is lower than 0");
/*      */       }
/* 1344 */       this.effect = effect;
/* 1345 */       this.offsetX = offsetX;
/* 1346 */       this.offsetY = offsetY;
/* 1347 */       this.offsetZ = offsetZ;
/* 1348 */       this.speed = speed;
/* 1349 */       this.amount = amount;
/* 1350 */       this.longDistance = longDistance;
/* 1351 */       this.data = data;
/*      */     }
/*      */ 
/*      */     public ParticlePacket(ParticleEffect effect, Vector direction, float speed, boolean longDistance, ParticleEffect.ParticleData data)
/*      */       throws IllegalArgumentException
/*      */     {
/* 1366 */       this(effect, (float)direction.getX(), (float)direction.getY(), (float)direction.getZ(), speed, 0, longDistance, data);
/*      */     }
/*      */ 
/*      */     public ParticlePacket(ParticleEffect effect, ParticleEffect.ParticleColor color, boolean longDistance)
/*      */     {
/* 1378 */       this(effect, color.getValueX(), color.getValueY(), color.getValueZ(), 1.0F, 0, longDistance, null);
/*      */     }
/*      */ 
/*      */     public static void initialize()
/*      */       throws ParticleEffect.ParticlePacket.VersionIncompatibleException
/*      */     {
/* 1389 */       if (initialized)
/* 1390 */         return;
/*      */       try
/*      */       {
/* 1393 */         version = Integer.parseInt(Character.toString(ReflectionUtils.PackageType.getServerVersion().charAt(3)));
/* 1394 */         if (version > 7) {
/* 1395 */           enumParticle = ReflectionUtils.PackageType.MINECRAFT_SERVER.getClass("EnumParticle");
/*      */         }
/* 1397 */         Class packetClass = ReflectionUtils.PackageType.MINECRAFT_SERVER.getClass(version < 7 ? "Packet63WorldParticles" : "PacketPlayOutWorldParticles");
/* 1398 */         packetConstructor = ReflectionUtils.getConstructor(packetClass, new Class[0]);
/* 1399 */         getHandle = ReflectionUtils.getMethod("CraftPlayer", ReflectionUtils.PackageType.CRAFTBUKKIT_ENTITY, "getHandle", new Class[0]);
/* 1400 */         playerConnection = ReflectionUtils.getField("EntityPlayer", ReflectionUtils.PackageType.MINECRAFT_SERVER, false, "playerConnection");
/* 1401 */         sendPacket = ReflectionUtils.getMethod(playerConnection.getType(), "sendPacket", new Class[] { ReflectionUtils.PackageType.MINECRAFT_SERVER.getClass("Packet") });
/*      */       } catch (Exception exception) {
/* 1403 */         throw new VersionIncompatibleException("Your current bukkit version seems to be incompatible with this library", exception);
/*      */       }
/* 1405 */       initialized = true;
/*      */     }
/*      */ 
/*      */     public static int getVersion()
/*      */     {
/* 1414 */       if (!initialized) {
/* 1415 */         initialize();
/*      */       }
/* 1417 */       return version;
/*      */     }
/*      */ 
/*      */     public static boolean isInitialized()
/*      */     {
/* 1427 */       return initialized;
/*      */     }
/*      */ 
/*      */     private void initializePacket(Location center)
/*      */       throws ParticleEffect.ParticlePacket.PacketInstantiationException
/*      */     {
/* 1437 */       if (this.packet != null)
/* 1438 */         return;
/*      */       try
/*      */       {
/* 1441 */         this.packet = packetConstructor.newInstance(new Object[0]);
/* 1442 */         if (version < 8) {
/* 1443 */           String name = this.effect.getName();
/* 1444 */           if (this.data != null) {
/* 1445 */             name = name + this.data.getPacketDataString();
/*      */           }
/* 1447 */           ReflectionUtils.setValue(this.packet, true, "a", name);
/*      */         } else {
/* 1449 */           ReflectionUtils.setValue(this.packet, true, "a", enumParticle.getEnumConstants()[this.effect.getId()]);
/* 1450 */           ReflectionUtils.setValue(this.packet, true, "j", Boolean.valueOf(this.longDistance));
/* 1451 */           if (this.data != null) {
/* 1452 */             ReflectionUtils.setValue(this.packet, true, "k", this.data.getPacketData());
/*      */           }
/*      */         }
/* 1455 */         ReflectionUtils.setValue(this.packet, true, "b", Float.valueOf((float)center.getX()));
/* 1456 */         ReflectionUtils.setValue(this.packet, true, "c", Float.valueOf((float)center.getY()));
/* 1457 */         ReflectionUtils.setValue(this.packet, true, "d", Float.valueOf((float)center.getZ()));
/* 1458 */         ReflectionUtils.setValue(this.packet, true, "e", Float.valueOf(this.offsetX));
/* 1459 */         ReflectionUtils.setValue(this.packet, true, "f", Float.valueOf(this.offsetY));
/* 1460 */         ReflectionUtils.setValue(this.packet, true, "g", Float.valueOf(this.offsetZ));
/* 1461 */         ReflectionUtils.setValue(this.packet, true, "h", Float.valueOf(this.speed));
/* 1462 */         ReflectionUtils.setValue(this.packet, true, "i", Integer.valueOf(this.amount));
/*      */       } catch (Exception exception) {
/* 1464 */         throw new PacketInstantiationException("Packet instantiation failed", exception);
/*      */       }
/*      */     }
/*      */ 
/*      */     public void sendTo(Location center, Player player)
/*      */       throws ParticleEffect.ParticlePacket.PacketInstantiationException, ParticleEffect.ParticlePacket.PacketSendingException
/*      */     {
/* 1478 */       initializePacket(center);
/*      */       try {
/* 1480 */         sendPacket.invoke(playerConnection.get(getHandle.invoke(player, new Object[0])), new Object[] { this.packet });
/*      */       } catch (Exception exception) {
/* 1482 */         throw new PacketSendingException("Failed to send the packet to player '" + player.getName() + "'", exception);
/*      */       }
/*      */     }
/*      */ 
/*      */     public void sendTo(Location center, List<Player> players)
/*      */       throws IllegalArgumentException
/*      */     {
/* 1495 */       if (players.isEmpty()) {
/* 1496 */         throw new IllegalArgumentException("The player list is empty");
/*      */       }
/* 1498 */       for (Player player : players)
/* 1499 */         sendTo(center, player);
/*      */     }
/*      */ 
/*      */     public void sendTo(Location center, double range)
/*      */       throws IllegalArgumentException
/*      */     {
/* 1512 */       if (range < 1.0D) {
/* 1513 */         throw new IllegalArgumentException("The range is lower than 1");
/*      */       }
/* 1515 */       String worldName = center.getWorld().getName();
/* 1516 */       double squared = range * range;
/* 1517 */       for (Player player : Bukkit.getOnlinePlayers())
/* 1518 */         if ((player.getWorld().getName().equals(worldName)) && (player.getLocation().distanceSquared(center) <= squared))
/*      */         {
/* 1521 */           sendTo(center, player);
/*      */         }
/*      */     }
/*      */ 
/*      */     private static final class PacketInstantiationException extends RuntimeException
/*      */     {
/*      */       private static final long serialVersionUID = 3203085387160737484L;
/*      */ 
/*      */       public PacketInstantiationException(String message, Throwable cause)
/*      */       {
/* 1565 */         super(cause);
/*      */       }
/*      */     }
/*      */ 
/*      */     private static final class PacketSendingException extends RuntimeException
/*      */     {
/*      */       private static final long serialVersionUID = 3203085387160737484L;
/*      */ 
/*      */       public PacketSendingException(String message, Throwable cause)
/*      */       {
/* 1587 */         super(cause);
/*      */       }
/*      */     }
/*      */ 
/*      */     private static final class VersionIncompatibleException extends RuntimeException
/*      */     {
/*      */       private static final long serialVersionUID = 3203085387160737484L;
/*      */ 
/*      */       public VersionIncompatibleException(String message, Throwable cause)
/*      */       {
/* 1543 */         super(cause);
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*      */   public static enum ParticleProperty
/*      */   {
/*  913 */     REQUIRES_WATER, 
/*      */ 
/*  917 */     REQUIRES_DATA, 
/*      */ 
/*  921 */     DIRECTIONAL, 
/*      */ 
/*  925 */     COLORABLE;
/*      */   }
/*      */ 
/*      */   private static final class ParticleVersionException extends RuntimeException
/*      */   {
/*      */     private static final long serialVersionUID = 3203085387160737484L;
/*      */ 
/*      */     public ParticleVersionException(String message)
/*      */     {
/* 1292 */       super();
/*      */     }
/*      */   }
/*      */ }

/* Location:           C:\Users\11608\Downloads\VIPParticles.jar
 * Qualified Name:     me.seanliam2000.VIPParticles.ParticleEffect
 * JD-Core Version:    0.6.2
 */