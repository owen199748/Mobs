package cn.rpgmc.utils;


import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class TitleAPI {

	@Deprecated
	public static void sendTitle(Player player, Integer fadeIn, Integer stay,
			Integer fadeOut, String message) {
		sendTitle(player, fadeIn, stay, fadeOut, message, null);
	}

	@Deprecated
	public static void sendSubtitle(Player player, Integer fadeIn,
			Integer stay, Integer fadeOut, String message) {
		sendTitle(player, fadeIn, stay, fadeOut, null, message);
	}

	@Deprecated
	public static void sendFullTitle(Player player, Integer fadeIn,
			Integer stay, Integer fadeOut, String title, String subtitle) {
		sendTitle(player, fadeIn, stay, fadeOut, title, subtitle);
	}

	public static void sendPacket(Player player, Object packet) {
		try {
			Object handle = player.getClass()
					.getMethod("getHandle", new Class[0])
					.invoke(player, new Object[0]);
			Object playerConnection = handle.getClass()
					.getField("playerConnection").get(handle);
			playerConnection
					.getClass()
					.getMethod("sendPacket",
							new Class[] { getNMSClass("Packet") })
					.invoke(playerConnection, new Object[] { packet });
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static Class<?> getNMSClass(String name) {
		String version = Bukkit.getServer().getClass().getPackage().getName()
				.split("\\.")[3];
		try {
			return Class
					.forName("net.minecraft.server." + version + "." + name);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static boolean is() {

		String[] strs = Bukkit.getBukkitVersion().split("-")[0].split("\\.");
		if (strs.length >= 2)
			if (strs[0].equalsIgnoreCase("1"))
				if (Integer.parseInt(strs[1]) >= 8)
					return true;

		return false;

	}



	public static void sendActionBar(Player player, String message) {
		try {
			String nmsver = Bukkit.getServer().getClass().getPackage()
					.getName();
			nmsver = nmsver.substring(nmsver.lastIndexOf(".") + 1);
			Class c1 = Class.forName("org.bukkit.craftbukkit." + nmsver
					+ ".entity.CraftPlayer");
			Object p = c1.cast(player);
			Object ppoc = null;
			Class c4 = Class.forName("net.minecraft.server." + nmsver
					+ ".PacketPlayOutChat");
			Class c5 = Class.forName("net.minecraft.server." + nmsver
					+ ".Packet");
			if ((nmsver.equalsIgnoreCase("v1_8_R1"))
					|| (!nmsver.startsWith("v1_8_"))) {
				Class c2 = Class.forName("net.minecraft.server." + nmsver
						+ ".ChatSerializer");
				Class c3 = Class.forName("net.minecraft.server." + nmsver
						+ ".IChatBaseComponent");
				Method m3 = c2.getDeclaredMethod("a",
						new Class[] { String.class });
				Object cbc = c3.cast(m3.invoke(c2,
						new Object[] { "{\"text\": \"" + message + "\"}" }));
				ppoc = c4.getConstructor(new Class[] { c3, Byte.TYPE })
						.newInstance(
								new Object[] { cbc, Byte.valueOf((byte) 2) });
			} else {
				Class c2 = Class.forName("net.minecraft.server." + nmsver
						+ ".ChatComponentText");
				Class c3 = Class.forName("net.minecraft.server." + nmsver
						+ ".IChatBaseComponent");
				Object o = c2.getConstructor(new Class[] { String.class })
						.newInstance(new Object[] { message });
				ppoc = c4.getConstructor(new Class[] { c3, Byte.TYPE })
						.newInstance(new Object[] { o, Byte.valueOf((byte) 2) });
			}
			Method m1 = c1.getDeclaredMethod("getHandle", new Class[0]);
			Object h = m1.invoke(p, new Object[0]);
			Field f1 = h.getClass().getDeclaredField("playerConnection");
			Object pc = f1.get(h);
			Method m5 = pc.getClass().getDeclaredMethod("sendPacket",
					new Class[] { c5 });
			m5.invoke(pc, new Object[] { ppoc });
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public static void sendTitle(Player player, Integer fadeIn, Integer stay,
			Integer fadeOut, String title, String subtitle) {
		try {
			if (title != null) {
				title = ChatColor.translateAlternateColorCodes('&', title);
				title = title.replaceAll("%player%", player.getDisplayName());
				Object enumTitle = getNMSClass("PacketPlayOutTitle")
						.getDeclaredClasses()[0].getField("TITLE").get(null);
				Object chatTitle = getNMSClass("IChatBaseComponent")
						.getDeclaredClasses()[0].getMethod("a",
						new Class[] { String.class }).invoke(null,
						new Object[] { "{\"text\":\"" + title + "\"}" });
				Constructor titleConstructor = getNMSClass("PacketPlayOutTitle")
						.getConstructor(
								new Class[] {
										getNMSClass("PacketPlayOutTitle")
												.getDeclaredClasses()[0],
										getNMSClass("IChatBaseComponent"),
										Integer.TYPE, Integer.TYPE,
										Integer.TYPE });
				Object titlePacket = titleConstructor.newInstance(new Object[] {
						enumTitle, chatTitle, fadeIn, stay, fadeOut });
				sendPacket(player, titlePacket);
			}

			if (subtitle != null) {
				subtitle = ChatColor
						.translateAlternateColorCodes('&', subtitle);
				subtitle = subtitle.replaceAll("%player%",
						player.getDisplayName());
				Object enumSubtitle = getNMSClass("PacketPlayOutTitle")
						.getDeclaredClasses()[0].getField("SUBTITLE").get(null);
				Object chatSubtitle = getNMSClass("IChatBaseComponent")
						.getDeclaredClasses()[0].getMethod("a",
						new Class[] { String.class }).invoke(null,
						new Object[] { "{\"text\":\"" + subtitle + "\"}" });
				Constructor subtitleConstructor = getNMSClass(
						"PacketPlayOutTitle").getConstructor(
						new Class[] {
								getNMSClass("PacketPlayOutTitle")
										.getDeclaredClasses()[0],
								getNMSClass("IChatBaseComponent"),
								Integer.TYPE, Integer.TYPE, Integer.TYPE });
				Object subtitlePacket = subtitleConstructor
						.newInstance(new Object[] { enumSubtitle, chatSubtitle,
								fadeIn, stay, fadeOut });
				sendPacket(player, subtitlePacket);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void sendTabTitle(Player player, String header, String footer) {
		if (header == null)
			header = "";
		header = ChatColor.translateAlternateColorCodes('&', header);

		if (footer == null)
			footer = "";
		footer = ChatColor.translateAlternateColorCodes('&', footer);

		header = header.replaceAll("%player%", player.getDisplayName());
		footer = footer.replaceAll("%player%", player.getDisplayName());
		try {
			Object tabHeader = getNMSClass("IChatBaseComponent")
					.getDeclaredClasses()[0].getMethod("a",
					new Class[] { String.class }).invoke(null,
					new Object[] { "{\"text\":\"" + header + "\"}" });
			Object tabFooter = getNMSClass("IChatBaseComponent")
					.getDeclaredClasses()[0].getMethod("a",
					new Class[] { String.class }).invoke(null,
					new Object[] { "{\"text\":\"" + footer + "\"}" });
			Constructor titleConstructor = getNMSClass(
					"PacketPlayOutPlayerListHeaderFooter").getConstructor(
					new Class[] { getNMSClass("IChatBaseComponent") });
			Object packet = titleConstructor
					.newInstance(new Object[] { tabHeader });
			Field field = packet.getClass().getDeclaredField("b");
			field.setAccessible(true);
			field.set(packet, tabFooter);
			sendPacket(player, packet);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}






}