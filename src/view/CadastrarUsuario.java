 package view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import model.dao.DaoFactory;
import model.dao.UsuarioDao;
import model.entities.Usuario;
import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;

public class CadastrarUsuario extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField nomeField;
	private JPasswordField senhaFields;
	
	UsuarioDao usuarioDao = DaoFactory.createUsuarioDao();

	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JTextField idField;
	private JTextField cpfField;
	private JTable table;
	Object[] opcoes = {"Sim", "Não"};
	
	ImageIcon imagem = new ImageIcon(getClass().getResource("logoTipo6.JPG"));
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadastrarUsuario frame = new CadastrarUsuario();
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
	public CadastrarUsuario() {
		setFont(new Font("Times New Roman", Font.PLAIN, 12));
		setBackground(SystemColor.activeCaption);
		setTitle("Cadastrar Usu\u00E1rio");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 569, 462);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		getContentPane().setBackground(SystemColor.activeCaption);
		JLabel nomeLabel = new JLabel("Nome:");
		nomeLabel.setBounds(12, 10, 110, 13);
		
		nomeField = new JTextField();
		nomeField.setBounds(12, 22, 362, 19);
		nomeField.setColumns(10);
		
		JLabel senhaLabel = new JLabel("Senha:");
		senhaLabel.setBounds(12, 92, 110, 13);
		
		senhaFields = new JPasswordField();
		senhaFields.setBounds(12, 104, 362, 19);
		
		JButton voltarButton = new JButton("Voltar");
		voltarButton.setBounds(443, 390, 98, 23);
		voltarButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaPrincipal_Adm principal = new TelaPrincipal_Adm();
				principal.setVisible(true);
				dispose();
			}
		});
		
		JButton limparButton = new JButton("Limpar");
		limparButton.setBounds(202, 160, 172, 21);
		limparButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				nomeField.setText("");
				senhaFields.setText("");
				cpfField.setText("");
				idField.setText("");
			}
		});
		
		JRadioButton administradorRadioButton = new JRadioButton("Administrador");
		administradorRadioButton.setBackground(SystemColor.activeCaption);
		buttonGroup.add(administradorRadioButton);
		administradorRadioButton.setBounds(100, 133, 113, 21);
		
		JRadioButton atendenteRadioButton = new JRadioButton("Atendente");
		atendenteRadioButton.setBackground(SystemColor.activeCaption);
		buttonGroup.add(atendenteRadioButton);
		atendenteRadioButton.setBounds(217, 133, 113, 21);
		
		JButton cadastrarButton = new JButton("Cadastrar");
		cadastrarButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Usuario usuario = new Usuario();
				
				usuario.setNome(nomeField.getText());
				usuario.setCpf(cpfField.getText());
				usuario.setSenha(senhaFields.getText());
				if(nomeField.getText().isEmpty() || cpfField.getText().isEmpty() || senhaFields.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Necessário preencher os campos!");
				}
				else {
				/**
				 * Para nao ter dois cadastro repetido:
				 */
				if(usuarioDao.UsuarioByCpf(cpfField.getText()) == null) {  // Buscando cpf no bd.
				/**
				 * Selecionando o tipo de usuario admin ou atend:
				 */
					if(administradorRadioButton.isSelected()) {
						usuario.setTipoUsuario("administrador");
							int escolha = JOptionPane.showOptionDialog(null, "Confirmar?", "Cadastro", JOptionPane.YES_NO_CANCEL_OPTION,
								JOptionPane.WARNING_MESSAGE, null, opcoes, null);
							if(escolha == JOptionPane.YES_OPTION) {
								usuarioDao.insert(usuario);
								JOptionPane.showMessageDialog(null, "Usuario: " + nomeField.getText() + "\nCadastrado com sucesso!!");
								pesquisar();
							}
							else {
								JOptionPane.showMessageDialog(null, "Cancelado!!");
							}
						}
					
					else if (atendenteRadioButton.isSelected()){
						usuario.setTipoUsuario("atendente");
						usuarioDao.insert(usuario);
						JOptionPane.showMessageDialog(null, "Usuario: " + nomeField.getText() + "\nCadastrado com sucesso!!");
						pesquisar();
				}
				}
				else {
					JOptionPane.showMessageDialog(null, "Usuário já existe!!");
				}
				/**
				 * Limpando os campos preenchido:
				 */
				nomeField.setText("");
				senhaFields.setText("");
				cpfField.setText("");
			}	
			}
			
		});
		cadastrarButton.setBounds(12, 160, 172, 21);
		contentPane.setLayout(null);
		contentPane.add(nomeLabel);
		contentPane.add(nomeField);
		contentPane.add(senhaLabel);
		contentPane.add(senhaFields);
		contentPane.add(voltarButton);
		contentPane.add(limparButton);
		contentPane.add(administradorRadioButton);
		contentPane.add(atendenteRadioButton);
		contentPane.add(cadastrarButton);
		
		JLabel tipoUsuarioLabel = new JLabel("Tipo do usu\u00E1rio:");
		tipoUsuarioLabel.setBounds(12, 137, 91, 13);
		contentPane.add(tipoUsuarioLabel);
		
		JLabel lblNewLabel_1 = new JLabel("ID:");
		lblNewLabel_1.setBounds(12, 395, 20, 13);
		contentPane.add(lblNewLabel_1);
		
		idField = new JTextField();
		idField.setBounds(32, 392, 39, 19);
		contentPane.add(idField);
		idField.setColumns(10);
		
		JButton removerButton = new JButton("Remover");
		removerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Usuario usuario = new Usuario();
				usuario.setId(Integer.parseInt(idField.getText()));//Inserir o id que deseja deletar do banco de dado.
				/**
				 * Impedindo que o usuario remova o administrador principal:
				 */
				if(!(Integer.parseInt(idField.getText()) == 1)) {
					/**
					 * Inserindo Id do usuario, faz a busca se o usuário a ser removido existe:
					 */
					if(idField.getText().isBlank()) {
						JOptionPane.showMessageDialog(null, "O campo está vazia!!");
					}
							if(usuarioDao.UsuarioById(Integer.parseInt(idField.getText())) == null) { // Buscando id no bd.
								JOptionPane.showMessageDialog(null, "Usuário não existe.");
							}
							/**
							 * Usuário encontrado, fazendo a remoção:
							 */
							else {
								int escolha = JOptionPane.showOptionDialog(null, "Deseja realmente remover?", "Cadastro", JOptionPane.YES_NO_CANCEL_OPTION,
										JOptionPane.WARNING_MESSAGE, null, opcoes, null);
								if (escolha == JOptionPane.YES_OPTION) {
									usuarioDao.deleteById(Integer.parseInt(idField.getText()));//removendo o usuario do bd.
									JOptionPane.showMessageDialog(null, "Usuário removido com sucesso.");
									pesquisar();
								}
								else {
									JOptionPane.showMessageDialog(null, "Cancelado!!");
								}
							}
					}
					else {
						JOptionPane.showMessageDialog(null, "Erro!!");
					}
					idField.setText("");
			}
		});
		removerButton.setBounds(83, 391, 91, 21);
		contentPane.add(removerButton);
		
		JLabel lblNewLabel_2 = new JLabel("CPF:");
		lblNewLabel_2.setBounds(12, 51, 50, 13);
		contentPane.add(lblNewLabel_2);
		
		cpfField = new JTextField();
		cpfField.setBounds(12, 63, 362, 19);
		contentPane.add(cpfField);
		cpfField.setColumns(10);
		
		JLabel imagem2 = new JLabel(imagem);
		imagem2.setBounds(355, 10, 222, 163);
		contentPane.add(imagem2);
		
		JButton listarButton = new JButton("Listar/Atualizar");
		listarButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pesquisar();
			}
		});
		listarButton.setBounds(186, 391, 130, 21);
		contentPane.add(listarButton);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 193, 529, 187);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "Nome", "CPF", "Tipo"
			}
		) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			Class[] columnTypes = new Class[] {
				Integer.class, String.class, String.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		table.getColumnModel().getColumn(0).setPreferredWidth(36);
		table.getColumnModel().getColumn(1).setPreferredWidth(174);
		table.getColumnModel().getColumn(2).setPreferredWidth(211);
		table.getColumnModel().getColumn(3).setPreferredWidth(173);
		
		setLocationRelativeTo(null);
		
	}
	
	public void pesquisar() {
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		model.setNumRows(0);
		for(Usuario user : usuarioDao.listUsuario()) {
			model.addRow(new Object[] {
					user.getId(), 
					user.getNome(), 
					user.getCpf(), 
					user.getTipoUsuario()
			});
		}
	}
}
