package cn.rpgmc.mobs.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Font;
import java.awt.Image;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import cn.rpgmc.mobs.utils.ResourceManager;

public class UI extends JFrame {
	JButton b1;
	JButton b2;
	Runnable run = null;
	static UI ui = null;
	AcListener ac = null;
	JButton b3;
	JButton b4;
	JButton b5;
	JLabel l1;

	public static void main(String args[]) {

		JFrame.setDefaultLookAndFeelDecorated(true);
		try {
			// UIManager.setLookAndFeel(new SubstanceMistAquaLookAndFeel());
		} catch (Exception e) {
			System.out.println("Substance Raven Graphite failed to initialize");
		}
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				UI w = new UI();
				w.setVisible(true);
			}
		});

	}

	UI() {
		super("Mobs附带功能");
		ui = this;
		JPanel Xpanel = new JPanel();
		Xpanel.setLayout(new BoxLayout(Xpanel, BoxLayout.X_AXIS));
		JPanel Ypanel = new JPanel();
		Ypanel.setLayout(new BoxLayout(Ypanel, BoxLayout.Y_AXIS));

		ui.getContentPane().add(Ypanel, BorderLayout.PAGE_START);
		ui.getContentPane().add(Xpanel, BorderLayout.PAGE_END);
		this.setLocationRelativeTo(null);

		ac = new AcListener();
		ui.setResizable(false);
		ui.setSize(620, 315);

		ui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ui.setVisible(true);


		addComponentsToPane(ui.getContentPane());

	}

	public void addComponentsToPane(Container pane) {
		pane.setLayout(null);
		pane.setBackground(new Color(255, 255, 222));
		l1 = new JLabel();

		ImageIcon icon = new ImageIcon(ResourceManager.getResource("logo.png"));
		int x = ui.getWidth() - 30;
		l1.setSize(x, (int) ((double) x / 1183d * 392d));
		l1.setLocation(10, 10);
		icon.setImage(icon.getImage().getScaledInstance(l1.getWidth(),
				l1.getHeight(), Image.SCALE_DEFAULT));
		l1.setIcon(icon);
		b1 = getButtonToY("导出<技能拓展方案开发教程.doc>");
		b2 = getButtonToX("查看发布页");
		b3 = getButtonToX("查看开源页");
		b4 = getButtonToX("赞助作者");
		b5 = getButtonToX("检查更新");
		b1.setSize(ui.getWidth() - 30, 30);
		b1.setLocation(10, 240);
		b2.setSize(ui.getWidth() / 4 - 2 * 10, 20);
		b3.setSize(ui.getWidth() / 4 - 2 * 10, 20);
		b4.setSize(ui.getWidth() / 4 - 2 * 10, 20);
		b5.setSize(ui.getWidth() / 4 - 2 * 10, 20);
		b2.setLocation(17, 213);
		b3.setLocation(b2.getX() + b2.getWidth() + 10, 213);
		b4.setLocation(b3.getX() + b3.getWidth() + 10, 213);
		b5.setLocation(b4.getX() + b4.getWidth() + 10, 213);

		add(l1, pane);
		add(b1, pane);
		add(b2, pane);
		add(b3, pane);
		add(b4, pane);
		add(b5, pane);


	}

	private JButton getButtonToY(String string) {
		JButton b = getButton(string);
		b.setFont(new Font("宋体", Font.BOLD, 25));
		return b;
	}

	private JButton getButtonToX(String string) {
		JButton b = getButton(string);
		b.setFont(new Font("宋体", Font.BOLD, 16));
		return b;
	}

	private JButton getButton(String string) {
		JButton b = new JButton(string);
		b.addActionListener(ac);
		return b;
	}


	private static void add(JLabel p, Container container) {

		p.setAlignmentX(Component.CENTER_ALIGNMENT);
		container.add(p);
	}



}
