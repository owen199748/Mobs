package cn.rpgmc.command;

import java.io.IOException;
import java.lang.reflect.Modifier;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import org.bukkit.entity.Player;

import cn.rpgmc.run.Main;

public class CommandManager {
	static List<PluginCommand> cmds = new ArrayList<PluginCommand>();
	static {
		try {
			
			List<Class<? extends PluginCommand>> cls = getJarClass(Main
					.getMain().getMainFile().getAbsolutePath());
			for(int i=0;i<cls.size();i++)
				cmds.add(cls.get(i).newInstance());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static List<Class<? extends PluginCommand>> getJarClass(
			String jarFile)
			throws IOException, ClassNotFoundException {
		ArrayList<Class<? extends PluginCommand>> l = new ArrayList<Class<? extends PluginCommand>>();
		ArrayList<String> l1 = new ArrayList<String>();

		URL url = new URL("file:" + jarFile);
		URLClassLoader cl = new URLClassLoader(new URL[] { url }, Main
.getCLoader());

		JarFile jar = new JarFile(jarFile);
		Enumeration<JarEntry> e = jar.entries();
		while (e.hasMoreElements()) {
			String name = isClass(e.nextElement().getName());
			if (name == null)
				continue;

			if (l1.contains(name))
				continue;
			Class<?> cla = cl.loadClass(name);
			if (cla == null)
				continue;
			if (!cla.isInterface())
				if (!Modifier.isAbstract(cla.getModifiers()))
					for (int i = 0; i < cla.getInterfaces().length; i++)
						if (cla.getInterfaces()[i] == PluginCommand.class) {

						l.add((Class<? extends PluginCommand>) cla);
						l1.add(cla.getSimpleName());
							break;
					}

		}
		jar.close();
		return l;
	}

	private static String isClass(String n) {

		if (n.length() > 6)
			if (n.substring(n.length() - 6, n.length()).equalsIgnoreCase(
					".class")) {

				return n.substring(0, n.length() - 6).replaceAll("/", ".");

			}

		return null;

	}

	public static boolean run(Player p, String[] args, String commandLabel)
			throws Exception {
		for (int i = 0; i < cmds.size(); i++) {
			PluginCommand cmd = cmds.get(i);
			String[] branch = cmd.getBranch();
			boolean is = false;
			if (branch.length <= args.length)
			for(int l=0;l<branch.length;l++)
					if (!branch[l].equalsIgnoreCase(args[l]))
					break;	
					else if (l == branch.length - 1)
					is=true;
			if (is) {
				String[] argss = new String[args.length - branch.length];
				for (int l = 0; l < argss.length; l++)
					argss[l] = args[l + branch.length];
				if (cmd.run(p, argss, commandLabel))
				return true;
			}
									

		}

		return false;
	}


}
