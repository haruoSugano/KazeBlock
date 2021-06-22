package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;
import java.awt.Font;

public class TelaPrincipal_Adm extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaPrincipal_Adm frame = new TelaPrincipal_Adm();
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
	public TelaPrincipal_Adm() {
		setTitle("Administrador - Tela Principal");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 347, 405);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.activeCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton cadastrarUsuarioButton = new JButton("Cadastrar/Remover Usu\u00E1rio");
		cadastrarUsuarioButton.setFont(new Font("Arial", Font.BOLD, 15));
		cadastrarUsuarioButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Acessar Cadastrar Usuario:
				CadastrarUsuario cadastroUsuario = new CadastrarUsuario();
				cadastroUsuario.setVisible(true);
				dispose();
			}
		});
		cadastrarUsuarioButton.setBounds(43, 22, 247, 55);
		contentPane.add(cadastrarUsuarioButton);
		
		JButton btnNewButton_1 = new JButton("Cadastrar Paciente");
		btnNewButton_1.setFont(new Font("Arial", Font.BOLD, 15));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CadastrarPaciente cadastrarPaciente = new CadastrarPaciente();
				cadastrarPaciente.setVisible(true);
				dispose();
			}
		});
		btnNewButton_1.setBounds(43, 87, 247, 55);
		contentPane.add(btnNewButton_1);
		
		JButton gerarRelatorioButton = new JButton("Gerar Relat\u00F3rio");
		gerarRelatorioButton.setFont(new Font("Arial", Font.BOLD, 15));
		gerarRelatorioButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GerarRelatorio gerar = new GerarRelatorio();
				gerar.setVisible(true);
				dispose();
			}
		});
		gerarRelatorioButton.setBounds(43, 221, 247, 56);
		contentPane.add(gerarRelatorioButton);
		
		JButton visualizarFilaButton = new JButton("Visualizar Fila");
		visualizarFilaButton.setFont(new Font("Arial", Font.BOLD, 15));
		visualizarFilaButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VisualizarFila_Adm visualizarFila = new VisualizarFila_Adm();
				visualizarFila.setVisible(true);
				dispose();
			}
		});
		visualizarFilaButton.setBounds(43, 152, 247, 55);
		contentPane.add(visualizarFilaButton);
		
		JButton sairButton = new JButton("Sair");
		sairButton.setFont(new Font("Arial", Font.BOLD, 15));
		sairButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaLogin telaLogin = new TelaLogin();
				telaLogin.setVisible(true);
				dispose();
			}
		});
		sairButton.setBounds(94, 300, 147, 56);
		contentPane.add(sairButton);
		
		setLocationRelativeTo(null);
	}
}
