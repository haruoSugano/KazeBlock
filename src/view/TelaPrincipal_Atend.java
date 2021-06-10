package view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.SystemColor;

public class TelaPrincipal_Atend extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaPrincipal_Atend frame = new TelaPrincipal_Atend();
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
	public TelaPrincipal_Atend() {
		setTitle("Atendente");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 347, 216);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.activeCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton visualizarFilaButton = new JButton("Visualizar Fila");
		visualizarFilaButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VisualizarFila_Atend visualizarFila = new VisualizarFila_Atend();
				visualizarFila.setVisible(true);
				dispose();
			}
		});
		visualizarFilaButton.setBounds(38, 29, 247, 55);
		contentPane.add(visualizarFilaButton);
		
		JButton sairButton = new JButton("Sair");
		sairButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaLogin telaLogin = new TelaLogin();
				telaLogin.setVisible(true);
				dispose();
			}
		});
		sairButton.setBounds(90, 111, 147, 56);
		contentPane.add(sairButton);
		
		setLocationRelativeTo(null);
	}
}
