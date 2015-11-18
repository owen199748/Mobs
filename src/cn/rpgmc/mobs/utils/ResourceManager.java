package cn.rpgmc.mobs.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class ResourceManager {

	public static URL getResource(String str) {
		return ClassLoader.getSystemResource("cn/rpgmc/mobs/resource/" + str);

	}

	public static void copyOf(URL url, File f) {
		if (url != null)
			if (f != null)

				{
		try {
			InputStream is = url.openStream();
			FileOutputStream fo = new FileOutputStream(f);
			int i = 0;
			while ((i = is.read()) != -1) {
				fo.write(i);
			}
			fo.flush();
			is.close();
			fo.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
				}


	}
}
