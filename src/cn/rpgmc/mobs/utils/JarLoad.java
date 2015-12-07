package cn.rpgmc.mobs.utils;

import java.io.IOException;
import java.lang.reflect.Modifier;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class JarLoad {

	public static List<Class<?>> getJarClass(String jarFile,
 Class<?> type,
			ClassLoader loader) throws IOException, ClassNotFoundException {
		ArrayList<Class<?>> l = new ArrayList<Class<?>>();
		ArrayList<String> l1 = new ArrayList<String>();

		URL url = new URL("file:" + jarFile);
		URLClassLoader cl = new URLClassLoader(new URL[] { url }, loader);

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
				{Class<?> css = cla.getSuperclass();
					while(css!=Object.class)
 {
						if (css == type) {

							l.add((Class<?>) cla);
							l1.add(cla.getSimpleName());
							break;
						} else
							css = css.getSuperclass();
					}
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

}
