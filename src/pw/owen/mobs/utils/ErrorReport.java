package pw.owen.mobs.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

import pw.owen.mobs.run.Main;


public class ErrorReport {
	private static List<String> es = new ArrayList<String>();
	private static int i = 0;
	private static File cfg = null;
	public static void report(String str) {
		es.add(str);
		i++;
		if (i > 20) {
		test();
			i = 0;
		}
	}



	public static void update() throws IOException {
		if (!Main.isAutoErrorReporting())
			return;
		test();
		if (es.size() == 0)
			return;
		Send.sendConsole("开始上报错误信息");
		Send.sendConsole("这有可能会包含您的部分隐私");
		Send.sendConsole("我保证将这些错误信息与排查错误之后立即删除并且不对外泄露");
		Send.sendConsole("开始上报收集的错误信息...");
		String str = "";
		str += "Mobs版本:" + Main.getV() + "\r\n";
		str += "服务端版本:" + Bukkit.getVersion() + "\r\n";
		str += "Bukkit端版本:" + Bukkit.getBukkitVersion() + "\r\n";
		// str += "服务器地址:" + Bukkit.getServer().getIp() + ":"
		// + Bukkit.getServer().getPort() + "\r\n";
		str += "系统信息:\r\n";
		Properties props=System.getProperties();
		
		str += "        操作系统名称:" + props.getProperty("os.name") + "\r\n";
		str += "        操作系统构架:" + props.getProperty("os.arch") + "\r\n";
		str += "        操作系统版本:" + props.getProperty("os.version") + "\r\n";
		str += "        Java 虚拟机中的内存总量:" + Runtime.getRuntime().totalMemory()
				+ "\r\n";
		str += "        Java 虚拟机试图使用的最大内存量:" + Runtime.getRuntime().maxMemory()
				+ "\r\n";
		str += "        Java 虚拟机中的空闲内存量:" + Runtime.getRuntime().freeMemory()
				+ "\r\n";
		str += "        Java 运行时环境版本:" + props.getProperty("java.version")
				+ "\r\n";
		str += "        Java 运行时环境供应商:" + props.getProperty("java.vendor")
				+ "\r\n";
		str += "        Java 安装目录:" + props.getProperty("java.home") + "\r\n";
		str += "        Java 虚拟机规范版本:"
				+ props.getProperty("java.vm.specification.version") + "\r\n";
		str += "        Java 虚拟机规范供应商:"
				+ props.getProperty("java.vm.specification.vendor") + "\r\n";
		str += "        Java 虚拟机规范名称:"
				+ props.getProperty("java.vm.specification.name") + "\r\n";
		str += "        Java 虚拟机实现版本:" + props.getProperty("java.vm.version")
				+ "\r\n";
		str += "        Java 虚拟机实现供应商:" + props.getProperty("java.vm.vendor")
				+ "\r\n";
		str += "        Java 虚拟机实现名称:" + props.getProperty("java.vm.name")
				+ "\r\n";
		str += "        Java 运行时环境规范版本:"
				+ props.getProperty("java.specification.version") + "\r\n";
		str += "        Java 运行时环境规范供应商:"
				+ props.getProperty("java.specification.vendor") + "\r\n";
		str += "        Java 运行时环境规范名称:"
				+ props.getProperty("java.specification.name") + "\r\n";

		String sst = "";
		Plugin[] ps = Bukkit.getPluginManager().getPlugins();
		for (int i = 0; i < ps.length; i++) {
			if (i != 0)
				sst += "\r\n";

			sst += "        " + ps[i].getName();

		}
		str += "插件列表:\r\n";
		str += sst + "\r\n";
		;
		str += "配置文件:\r\n";
		if(cfg!=null)
 {
			FileReader fr = new FileReader(cfg);
			int r = 0;
			List<Integer> cs = new ArrayList<Integer>();
			while ((r = fr.read()) != -1)
				cs.add(r);
			char[] ccs = new char[cs.size()];
			for (int ii = 0; ii < cs.size(); ii++) {
				ccs[ii] = (char) (int) cs.get(ii);
			}

			str += new String(ccs);
		} else
			str += "---无---";

		str += "\r\n========================";

		for (int l = 0; l < es.size(); l++) {
			if (l != 0)
				str += "\r\n*******************************";

			str += "\r\n" + es.get(l);
		}
		update(str);
		File file = new File(new File(Main.getMain().getDataFolder()
				.getAbsolutePath()), "report.txt");
		if (!file.exists())
			file.createNewFile();

		try (FileWriter fw = new FileWriter(file)) {
		fw.write(str);
		fw.close();

		}
		Send.sendConsole("上传成功,您可以到插件目录下/Mobs/report.txt查看最近一次上传的信息");
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
		}
	}

	private static void update(String str) throws UnsupportedEncodingException {
		str = new String(str.getBytes(), "GBK");
		String[] strs = str.split("\n");
		String add = "";
		try {
			InetAddress addr = InetAddress.getLocalHost();
			add = addr.getHostName();
		} catch (UnknownHostException e) {
			add = "UnknownHostException";
		}
		String name = "Mobs_" + add + "_" + System.currentTimeMillis();

		String h = "type=error_report_add&name=" + name + "&value=";
		int one = 1024 * 512;
		if (str.length() > one) {
			int ls = (int) (str.length() / one);
			List<String> sts = new ArrayList<String>();
			for (int ii = 0; ii < ls; ii++)
				sts.add(str.substring(ii * one, (ii + 1) * one));
			if (str.length() % one != 0)
				sts.add(str.substring(ls * one, str.length()));

			for (int ii = 0; ii < sts.size(); ii++)
				sendPost("http://www.rpgmc.cn/error_report.do",
						h + URLEncoder.encode(sts.get(ii), "GBK"));

		} else
			sendPost("http://www.rpgmc.cn/error_report.do",
					h + URLEncoder.encode(str, "GBK"));

	}

	public static String sendGet(String url, String param) {
		String result = "";
		BufferedReader in = null;
		try {
			String urlNameString = url + "?" + param;
			URL realUrl = new URL(urlNameString);
			// 打开和URL之间的连接
			URLConnection connection = realUrl.openConnection();
			// 设置通用的请求属性
			connection.setRequestProperty("accept", "*/*");
			connection.setRequestProperty("connection", "Keep-Alive");
			connection.setRequestProperty("user-agent",
					"Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			// 建立实际的连接
			connection.connect();
			// 获取所有响应头字段
			Map<String, List<String>> map = connection.getHeaderFields();
			// 遍历所有的响应头字段
			// for (String key : map.keySet()) {
				// System.out.println(key + "--->" + map.get(key));
			// }
			// 定义 BufferedReader输入流来读取URL的响应
			in = new BufferedReader(new InputStreamReader(
					connection.getInputStream()));
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
		} catch (Exception e) {
			System.out.println("发送GET请求出现异常！" + e);
			e.printStackTrace();
		}
		// 使用finally块来关闭输入流
		finally {
			try {
				if (in != null) {
					in.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return result;
	}

	public static String sendPost(String url, String param) {
		PrintWriter out = null;
		BufferedReader in = null;
		String result = "";
		try {
			URL realUrl = new URL(url);
			// 打开和URL之间的连接
			URLConnection conn = realUrl.openConnection();
			// 设置通用的请求属性
			conn.setRequestProperty("accept", "*/*");
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("user-agent",
					"Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			// 发送POST请求必须设置如下两行
			conn.setDoOutput(true);
			conn.setDoInput(true);
			// 获取URLConnection对象对应的输出流
			out = new PrintWriter(conn.getOutputStream());
			// 发送请求参数
			out.print(param);
			// flush输出流的缓冲
			out.flush();
			// 定义BufferedReader输入流来读取URL的响应
			in = new BufferedReader(
					new InputStreamReader(conn.getInputStream()));
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
		} catch (Exception e) {
			System.out.println("发送 POST 请求出现异常！" + e);
			e.printStackTrace();
		}
		// 使用finally块来关闭输出流、输入流
		finally {
			try {
				if (out != null) {
					out.close();
				}
				if (in != null) {
					in.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return result;
	}

	private static void test() {
		es = new ArrayList<String>(new HashSet<String>(es));

	}

	public static void setCfg(File c) {
		cfg = c;

	}
}
