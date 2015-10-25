package cn.rpgmc.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;

import cn.rpgmc.bean.utils.ResourceManager;

public class AcListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource().equals(UI.ui.b1)) {
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

		}

	}

}
