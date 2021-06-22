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
import javax.swing.text.MaskFormatter;

import model.dao.DaoFactory;
import model.dao.UsuarioDao;
import model.entities.Pessoa;
import model.entities.Usuario;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.SystemColor;
import javax.swing.border.TitledBorder;
import javax.swing.JFormattedTextField;
import java.awt.Dimension;
import javax.swing.Icon;
import java.awt.Component;
import javax.swing.border.LineBorder;
import javax.swing.border.EtchedBorder;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class CadastrarUsuario extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	
	UsuarioDao usuarioDao = DaoFactory.createUsuarioDao();

	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JTextField idField;
	private JTable table;
	Object[] opcoes = {"Sim", "Não"};
	

	private JTextField nomeField;
	private JPasswordField senhaFields;
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
		setBounds(100, 100, 621, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		getContentPane().setBackground(SystemColor.activeCaption);
		/**
		 * Limpando os campo de texto
		 */
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Usu\u00E1rio", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBackground(SystemColor.inactiveCaption);
		panel.setBounds(12, 10, 581, 541);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel imagem2 = new JLabel(new ImageIcon("C:\\Users\\haruo\\eclipse-workspace\\KazeBlock\\src\\view\\imagem\\logoTipo3.JPG"));
		imagem2.setBorder(new LineBorder(SystemColor.desktop));
		imagem2.setBounds(414, 10, 155, 259);
		panel.add(imagem2);
		imagem2.setPreferredSize(new Dimension(300, 300));
		imagem2.setAlignmentY(Component.TOP_ALIGNMENT);
		imagem2.setMaximumSize(new Dimension(300, 300));
		
		nomeField = new JTextField();
		nomeField.setBounds(12, 26, 374, 49);
		panel.add(nomeField);
		nomeField.setFont(new Font("Arial", Font.BOLD, 15));
		nomeField.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Nome", TitledBorder.LEADING, TitledBorder.TOP, null, SystemColor.desktop));
		nomeField.setColumns(10);
		
		JFormattedTextField cpfField = new JFormattedTextField(setFormatar("###########"));
		cpfField.setBounds(12, 85, 374, 49);
		panel.add(cpfField);
		cpfField.setFont(new Font("Arial", Font.BOLD, 15));
		cpfField.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "CPF", TitledBorder.LEADING, TitledBorder.TOP, null, SystemColor.desktop));
		
		senhaFields = new JPasswordField();
		senhaFields.setBounds(12, 144, 374, 49);
		panel.add(senhaFields);
		senhaFields.setFont(new Font("Arial", Font.BOLD, 15));
		senhaFields.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Senha", TitledBorder.LEADING, TitledBorder.TOP, null, SystemColor.desktop));
		
		JRadioButton atendenteRadioButton = new JRadioButton("Atendente");
		atendenteRadioButton.setBounds(273, 206, 113, 21);
		panel.add(atendenteRadioButton);
		atendenteRadioButton.setFont(new Font("Arial", Font.BOLD, 15));
		atendenteRadioButton.setBackground(SystemColor.inactiveCaption);
		buttonGroup.add(atendenteRadioButton);
		
		JRadioButton administradorRadioButton = new JRadioButton("Administrador");
		administradorRadioButton.setBounds(139, 199, 130, 34);
		panel.add(administradorRadioButton);
		administradorRadioButton.setFont(new Font("Arial", Font.BOLD, 15));
		administradorRadioButton.setBackground(SystemColor.inactiveCaption);
		buttonGroup.add(administradorRadioButton);
		
		JLabel tipoUsuarioLabel = new JLabel("Tipo do usu\u00E1rio:");
		tipoUsuarioLabel.setBounds(12, 206, 123, 21);
		panel.add(tipoUsuarioLabel);
		tipoUsuarioLabel.setFont(new Font("Arial", Font.BOLD, 15));
		JButton limparButton = new JButton("Limpar");
		limparButton.setBounds(208, 241, 178, 39);
		panel.add(limparButton);
		limparButton.setFont(new Font("Arial", Font.BOLD, 15));
		
		JButton cadastrarButton = new JButton("Cadastrar");
		cadastrarButton.setBounds(12, 241, 178, 39);
		panel.add(cadastrarButton);
		cadastrarButton.setFont(new Font("Arial", Font.BOLD, 15));
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 290, 557, 187);
		panel.add(scrollPane);
		scrollPane.setFont(new Font("Arial", Font.PLAIN, 15));
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				List<Usuario> listUsuario = new ArrayList<>();
				int indiceLinha = table.getSelectedRow();
				for(Usuario x : usuarioDao.listUsuario()) {
					listUsuario.add(x);
				}
				for(int i=0; i<listUsuario.size(); i++) {
					String cpf = table.getModel().getValueAt(indiceLinha, 2).toString();
					if(listUsuario.get(i).getCpf().equals(cpf)) {
						idField.setText(listUsuario.get(i).getId().toString());
					}
			}
			}
		});
		
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
		
		JButton voltarButton = new JButton("Voltar");
		voltarButton.setBounds(471, 483, 98, 44);
		panel.add(voltarButton);
		voltarButton.setFont(new Font("Arial", Font.BOLD, 15));
		
		JButton listarButton = new JButton("Listar");
		listarButton.setBounds(319, 483, 140, 44);
		panel.add(listarButton);
		listarButton.setFont(new Font("Arial", Font.BOLD, 15));
		
		JButton removerButton = new JButton("Remover");
		removerButton.setBounds(103, 483, 110, 44);
		panel.add(removerButton);
		removerButton.setFont(new Font("Arial", Font.BOLD, 15));
		
		idField = new JTextField();
		idField.setFont(new Font("Arial", Font.BOLD, 12));
		idField.setBounds(16, 483, 73, 44);
		panel.add(idField);
		idField.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "ID", TitledBorder.LEADING, TitledBorder.TOP, null, SystemColor.desktop));
		idField.setColumns(10);
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
		listarButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pesquisar();
			}
		});
		voltarButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaPrincipal_Adm principal = new TelaPrincipal_Adm();
				principal.setVisible(true);
				dispose();
			}
		});
		table.getColumnModel().getColumn(0).setPreferredWidth(36);
		table.getColumnModel().getColumn(1).setPreferredWidth(174);
		table.getColumnModel().getColumn(2).setPreferredWidth(211);
		table.getColumnModel().getColumn(3).setPreferredWidth(173);
		cadastrarButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Usuario usuario = new Usuario();
				
				usuario.setNome(nomeField.getText());
				usuario.setCpf(cpfField.getText());
				usuario.setSenha(senhaFields.getText());
				/**
				 * Caso o campo de texto esteja vazio
				 */
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
				 * Adminstrador
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
					//Atendente
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
		limparButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				nomeField.setText("");
				senhaFields.setText("");
				cpfField.setText("");
				idField.setText("");
			}
		});
		
		setLocationRelativeTo(null);
		
	}
	/**
	 * Mostrando a tabela os usuários cadastrado.
	 */
	
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
	
	private MaskFormatter setFormatar(String formatar) {
		MaskFormatter mask = null;
		try {
			mask = new MaskFormatter(formatar);
			mask.setPlaceholderCharacter('_');
		}
		catch(Exception e) {
		}
		return mask;
	}
}
