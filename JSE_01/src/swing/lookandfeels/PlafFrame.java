package swing.lookandfeels;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class PlafFrame extends JFrame{
	
	private JPanel buttonPanel;
	
	public PlafFrame() {
	
		buttonPanel = new JPanel();
		
		// 获得系统支持的观感类型信息
		UIManager.LookAndFeelInfo[] infos = UIManager.getInstalledLookAndFeels();
		for(UIManager.LookAndFeelInfo info: infos){
			System.out.println("info.getName="+info.getName());
			System.out.println("info.getClassName="+info.getClassName());
			createButton(info.getName(), info.getClassName());
		}
		
		
		add(buttonPanel);
		setVisible(true);
		pack();
		
		this.addWindowListener(new WindowAdapter() {
			
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
			
			@Override
			public void windowDeiconified(WindowEvent e) {
				System.out.println("deiconified");
			}
			
		});
		
	}
	
	private void createButton(String title,final String style){
		
		JButton jButton = new JButton(title);
		buttonPanel.add(jButton);
		jButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				try {
					// 设置观感
					UIManager.setLookAndFeel(style);
					// 刷新UI
					SwingUtilities.updateComponentTreeUI(PlafFrame.this);
					
					
				} catch (ClassNotFoundException e1) {
					e1.printStackTrace();
				} catch (InstantiationException e1) {
					e1.printStackTrace();
				} catch (IllegalAccessException e1) {
					e1.printStackTrace();
				} catch (UnsupportedLookAndFeelException e1) {
					e1.printStackTrace();
				}

				
			}
		});
		
		
	}

	public static void main(String[] args) {
		
		new PlafFrame();

	}

}
