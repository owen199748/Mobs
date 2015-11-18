package cn.rpgmc.mobs.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import cn.rpgmc.mobs.utils.ResourceManager;

public class AcListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource().equals(UI.ui.b1)) {// 导出技能教程
			JFileChooser jfc = new JFileChooser();
			jfc.setFileSelectionMode(1);
			int state = jfc.showOpenDialog(null);

			if (state == 1) {
				return;// 撤销则返回
			} else {
				File f = jfc.getSelectedFile();// f为选择到的文件

				File fff = new File(f, "技能拓展方案开发教程.docx");

				ResourceManager.copyOf(
						ResourceManager.getResource("技能拓展方案开发教程.docx"), fff);

			}

		} else if (e.getSource().equals(UI.ui.b2)) {// 查看发布页

			try {
				java.awt.Desktop.getDesktop().browse(
						new java.net.URI(
								"http://www.mcbbs.net/thread-494926-1-1.html"));
			} catch (Exception e1) {

			}
		} else if (e.getSource().equals(UI.ui.b3)) {// 查看开源页

			try {
				java.awt.Desktop.getDesktop().browse(
						new java.net.URI(
"https://github.com/owen199748/Mobs"));
			} catch (Exception e1) {

			}
		} else if (e.getSource().equals(UI.ui.b4)) {// 赞助
			try {
				java.awt.Desktop.getDesktop().browse(
						new java.net.URI("http://www.rpgmc.cn/pay.jsp"));
			} catch (Exception e1) {

			}

		} else if (e.getSource().equals(UI.ui.b5)) {// 检查更新
			update();

		}

	}

	private void update() {

		try {
			URL url = new URL("http://www.rpgmc.cn/Mobs/ver.info");
			InputStreamReader is = new InputStreamReader(
					((HttpURLConnection) url.openConnection()).getInputStream());
			int i = 0;
			List<Byte> bs = new ArrayList<Byte>();
			while ((i = is.read()) != -1) {
				bs.add((byte) i);
			}
			byte[] bytes = new byte[bs.size()];
			for (int r = 0; r < bytes.length; r++)
				bytes[r] = bs.get(r);

			String str = new String(bytes, is.getEncoding());
			InputStream pis = ClassLoader.getSystemResource("plugin.yml")
					.openStream();
			bs.clear();
			while ((i = pis.read()) != -1) {
				bs.add((byte) i);
			}
			bytes = new byte[bs.size()];
			for (int r = 0; r < bytes.length; r++)
				bytes[r] = bs.get(r);
			String[] strs = new String(bytes).split("\n");
			String str2 = "";
			for(int r=0;r<strs.length;r++){
				String s = strs[r].trim();
				if (s.length() > 8)
					if (s.substring(0, 8).equalsIgnoreCase("version:"))
						str2 = s.substring(8).trim();

			}

			if (str2.equalsIgnoreCase(str)) {
				JOptionPane.showMessageDialog(null, "您当前(" + str2
						+ ")是最新版本!", "更新检测",
 JOptionPane.INFORMATION_MESSAGE);
			} else {
				JOptionPane
						.showMessageDialog(null, "发现新版本(" + str
								+ "),请打开发布页面查看详细信息!",
						"更新检测",
						JOptionPane.QUESTION_MESSAGE);
			}

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getClass().getName(),
					"获取版本信息失败",
 JOptionPane.WARNING_MESSAGE);
			e.printStackTrace();
		}

	}

}
