package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import db.DB;
import model.dao.DaoFactory;
import model.dao.UsuarioDao;
import model.entities.Usuario;

public class TelaLogin extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField cpfFields;
	private JPasswordField senhaFields;

	UsuarioDao usuarioDao = DaoFactory.createUsuarioDao();
	Connection conn = null;
	ImageIcon imagem = new ImageIcon(getClass().getResource("logoTipo2.JPG"));
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaLogin frame = new TelaLogin();
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
	public TelaLogin() {
		
		conn = DB.getConnection();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 640, 345);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		getContentPane().setBackground(SystemColor.activeCaption);
	
		
		JLabel login = new JLabel(imagem);
		login.setFont(new Font("Times New Roman", Font.PLAIN, 26));
		login.setBounds(12, 10, 289, 286);
		contentPane.add(login);
		
		cpfFields = new JTextField();
		cpfFields.setBorder(new TitledBorder(null, "CPF", TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLACK));
		cpfFields.setBounds(323, 60, 289, 49);
		contentPane.add(cpfFields);
		cpfFields.setColumns(10);
		
		/**
		 * Realizando o login:
		 */
		JButton enterButton = new JButton("Entrar");
		enterButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Usuario usuario = new Usuario();
				boolean autenticadorAdm = false;
				boolean autenticadorAtend = false;
				List<Usuario> listUsuario = new ArrayList<>();
			
				for(Usuario user : usuarioDao.listUsuario()) {
					listUsuario.add(user);
				}
				usuario.setCpf(cpfFields.getText());
				usuario.setSenha(senhaFields.getText());
				if(usuarioDao.existe(usuario)) {
				for(int i=0; i<listUsuario.size(); i++) {
					if(listUsuario.get(i).getCpf().equals(cpfFields.getText()) && listUsuario.get(i).getSenha().equals(senhaFields.getText())
							&& listUsuario.get(i).getTipoUsuario().equals("administrador")) {
						JOptionPane.showMessageDialog(null, "Bem vindo!!");
						TelaPrincipal_Adm principal = new TelaPrincipal_Adm();
						principal.setVisible(true);
						dispose();//Fecha a janela de login 
						break;
					}
					else if(listUsuario.get(i).getCpf().equals(cpfFields.getText()) && listUsuario.get(i).getSenha().equals(senhaFields.getText())
							&& listUsuario.get(i).getTipoUsuario().equals("atendente")) {
						JOptionPane.showMessageDialog(null, "Bem vindo!!");
						TelaPrincipal_Atend principal = new TelaPrincipal_Atend();
						principal.setVisible(true);
						dispose();//Fecha a janela de login 
						break;
					}
				}
				}
				else {
					JOptionPane.showMessageDialog(null, "Usuário não existe!!");
				}
			}
			
		});
		enterButton.setBounds(323, 211, 142, 43);
		contentPane.add(enterButton);
		
		JLabel status = new JLabel("Status");
		status.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		status.setBounds(542, 283, 70, 13);
		contentPane.add(status);
		
		senhaFields = new JPasswordField();
		senhaFields.setBorder(new TitledBorder(null, "Senha", TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLACK));
		senhaFields.setBounds(323, 131, 289, 50);
		contentPane.add(senhaFields);
		/**
		 * Fechando o programa:
		 */
		JButton sairButton = new JButton("Sair");
		sairButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		sairButton.setBounds(470, 211, 142, 43);
		contentPane.add(sairButton);
		
		
		//Mudar a label Status na tela de login para:
		if (conn != null) {
			status.setText("Conectado");
		}
		else {
			status.setText("Desconectado");
		}
		
		setLocationRelativeTo(null);
	}

}
