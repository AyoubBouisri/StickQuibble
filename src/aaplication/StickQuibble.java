package aaplication;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import composants.Jeu;

public class StickQuibble extends JFrame {

	private static JPanel contentPane;

	
	private static int WIDTH_APP = 1360;
	private static int HEIGHT_APP = 700;
	
	
	private static Jeu jeu;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StickQuibble frame = new StickQuibble();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public StickQuibble() {
		setTitle("Stick Quibble");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, WIDTH_APP, HEIGHT_APP);
		contentPane = new JPanel();
		
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lancerJeu();
		
		
		
	}
	
	public static void lancerJeu() {
		jeu = new Jeu(WIDTH_APP,HEIGHT_APP);
		contentPane.add(jeu);
		contentPane.repaint();
	
	}

}
