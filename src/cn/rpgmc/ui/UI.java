package cn.rpgmc.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import org.jvnet.substance.SubstanceLookAndFeel;
import org.jvnet.substance.skin.AutumnSkin;
import org.jvnet.substance.skin.SubstanceMistAquaLookAndFeel;

public class UI extends JFrame {
	JButton b1;
	JButton b2;
	Runnable run = null;
	static UI ui = null;
	AcListener ac = null;
	JButton b3;
	JButton b4;
	JButton b5;

	public static void main(String args[]) {

		JFrame.setDefaultLookAndFeelDecorated(true);
		try {
			UIManager.setLookAndFeel(new SubstanceMistAquaLookAndFeel());
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
		SubstanceLookAndFeel.setSkin(new AutumnSkin());

		ui.setResizable(false);
		ui.setSize(520, 128);

		ui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ui.setVisible(true);
		ui.setLayout(new FlowLayout());


		addComponentsToPane(ui.getContentPane());

	}

	public void addComponentsToPane(Container pane) {

		JPanel xPanel = new JPanel();
		BoxLayout bx = new BoxLayout(xPanel, BoxLayout.X_AXIS);
		xPanel.setLayout(bx);
		JPanel yPanel = new JPanel();
		BoxLayout by = new BoxLayout(yPanel, BoxLayout.Y_AXIS);
		yPanel.setLayout(by);

		pane.setBackground(new Color(255, 255, 222));


		b1 = getButtonToY("导出<技能拓展方案开发教程.doc>");
		b2 = getButtonToX("查看发布页");
		b3 = getButtonToX("查看开源页");
		b4 = getButtonToX("赞助作者");
		b5 = getButtonToX("检查更新");

		addAButton(b1, yPanel);
		addAButton(b2, xPanel);
		addAButton(b3, xPanel);
		addAButton(b4, xPanel);
		addAButton(b5, xPanel);

		pane.add(yPanel, BorderLayout.PAGE_START);
		pane.add(xPanel, BorderLayout.PAGE_END);

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

	private static void addAButton(JButton b, Container container) {
		JButton button = b;
		button.setAlignmentX(Component.CENTER_ALIGNMENT);
		container.add(button);
	}



}
