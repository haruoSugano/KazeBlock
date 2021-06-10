package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import model.dao.DaoFactory;
import model.dao.PessoaDao;
import model.entities.Pessoa;
import java.awt.Font;

public class CadastrarPaciente extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField nomeField;
	private JTextField idadeField;
	private JTextField cpfField;
	private JTextField logradouroField;
	private JTextField numeroField;
	private JTextField bairroField;
	private JTextField cepField;
	private JTextField cidadeField;
	private JTextField ufField;
	private JTextField profissaoField;
	private ButtonGroup grupo;
	private JTextField dataField;

	Connection conn = null;
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	ImageIcon imagem = new ImageIcon(getClass().getResource("logoTipo8.JPG"));
	PessoaDao pessoaDao = DaoFactory.createPessoaDao();
	private JTable table;
	private JTextField cpfText;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadastrarPaciente frame = new CadastrarPaciente();
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
	public CadastrarPaciente() {
		setFont(new Font("Dialog", Font.PLAIN, 14));
		setForeground(SystemColor.textHighlight);
		setTitle("Tela de cadastro de paciente");
		setName("Cadastrar Paciente");
		
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 730, 623);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Cadastro de paciente", TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLACK));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		getContentPane().setBackground(SystemColor.activeCaption);
		
		nomeField = new JTextField();
		nomeField.setBackground(SystemColor.inactiveCaptionBorder);
		nomeField.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Nome", TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLACK));
		nomeField.setBounds(12, 21, 424, 41);
		contentPane.add(nomeField);
		nomeField.setColumns(10);
		
		idadeField = new JTextField();
		idadeField.setBackground(SystemColor.text);
		idadeField.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Idade", TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLACK));
		idadeField.setBounds(12, 73, 58, 35);
		contentPane.add(idadeField);
		idadeField.setColumns(10);
		
		cpfField = new JTextField();
		cpfField.setBackground(SystemColor.text);
		cpfField.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "CPF", TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLACK));
		cpfField.setBounds(12, 114, 424, 35);
		contentPane.add(cpfField);
		cpfField.setColumns(10);
		
		logradouroField = new JTextField();
		logradouroField.setBackground(SystemColor.text);
		logradouroField.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Logradouro", TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLACK));
		logradouroField.setBounds(12, 186, 424, 41);
		contentPane.add(logradouroField);
		logradouroField.setColumns(10);
		
		numeroField = new JTextField();
		numeroField.setBackground(SystemColor.text);
		numeroField.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "N\u00B0", TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLACK));
		numeroField.setBounds(12, 227, 75, 41);
		contentPane.add(numeroField);
		numeroField.setColumns(10);
		
		bairroField = new JTextField();
		bairroField.setBackground(SystemColor.text);
		bairroField.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Bairro", TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLACK));
		bairroField.setBounds(94, 227, 342, 41);
		contentPane.add(bairroField);
		bairroField.setColumns(10);
		
		cepField = new JTextField();
		cepField.setBackground(SystemColor.text);
		cepField.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "CEP", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		cepField.setBounds(249, 268, 187, 41);
		contentPane.add(cepField);
		cepField.setColumns(10);
		
		cidadeField = new JTextField();
		cidadeField.setBackground(SystemColor.text);
		cidadeField.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Cidade", TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLACK));
		cidadeField.setBounds(12, 268, 114, 41);
		contentPane.add(cidadeField);
		cidadeField.setColumns(10);
		
		ufField = new JTextField();
		ufField.setBackground(SystemColor.text);
		ufField.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "UF", TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLACK));
		ufField.setBounds(138, 268, 99, 41);
		contentPane.add(ufField);
		ufField.setColumns(10);
		
		JButton voltarButton = new JButton("Voltar");
		voltarButton.setBounds(603, 541, 99, 33);
		voltarButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaPrincipal_Adm principal = new TelaPrincipal_Adm();
				principal.setVisible(true);
				dispose();
			}
		});
		contentPane.add(voltarButton);
		
		JRadioButton simRadioButton = new JRadioButton("Sim");
		simRadioButton.setBackground(SystemColor.activeCaption);
		simRadioButton.setBorder(new TitledBorder(null, "\u00C1rea da sa\u00FAde", TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLACK));
		simRadioButton.setBounds(297, 150, 58, 27);
		contentPane.add(simRadioButton);
		
		JRadioButton naoRadioButton = new JRadioButton("N\u00E3o");
		naoRadioButton.setBackground(SystemColor.activeCaption);
		naoRadioButton.setBorder(new TitledBorder(null, "\u00C1rea da sa\u00FAde", TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLACK));
		naoRadioButton.setBounds(378, 150, 58, 25);
		contentPane.add(naoRadioButton);
		
		JButton cadastrarButton = new JButton("Cadastrar");
		cadastrarButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Pessoa pessoa = new Pessoa();
				Object[] opcoes = {"Sim", "Não"};
				pessoa.setNome(nomeField.getText());
				pessoa.setIdade(Integer.parseInt(idadeField.getText()));
				int idade = Integer.parseInt(idadeField.getText());
				pessoa.setProfissao(profissaoField.getText());
				pessoa.setCpf(cpfField.getText());
				if (simRadioButton.isSelected()) {
					pessoa.setAreaSaude(true);
					if (idade >= 70) {
						pessoa.setNivel(3);
					} else {
						pessoa.setNivel(2);
					}

				} else if (naoRadioButton.isSelected()) {
					pessoa.setAreaSaude(false);
					if (idade >= 70) {
						pessoa.setNivel(3);
					} else {
						pessoa.setNivel(1);
					}

				}
				pessoa.setLogradouro(logradouroField.getText());
				pessoa.setNumero(numeroField.getText());
				pessoa.setCidade(cidadeField.getText());
				pessoa.setUf(ufField.getText());
				pessoa.setCep(cepField.getText());
				Date dataNascimento = null;
				try {
					pessoa.setNascimento(sdf.parse(dataField.getText()));
					dataNascimento = sdf.parse(dataField.getText());
				} catch (ParseException e2) {
					e2.printStackTrace();
				}
				if (dataNascimento == null) {
					JOptionPane.showMessageDialog(null, "Data de nascimento incorreta!");
				} 
				else {
					if (nomeField.getText().isEmpty() || idadeField.getText().isEmpty()
							|| profissaoField.getText().isEmpty() || cpfField.getText().isEmpty()
							|| logradouroField.getText().isEmpty() || numeroField.getText().isEmpty()
							|| bairroField.getText().isEmpty() || cidadeField.getText().isEmpty()
							|| bairroField.getText().isEmpty() || cidadeField.getText().isEmpty()
							|| ufField.getText().isEmpty() || cepField.getText().isEmpty()
							|| dataField.getText().isEmpty()) {
						JOptionPane.showMessageDialog(null, "Necessário preencher os campos!");
					}
					else {
						if (pessoaDao.findByCpf(cpfField.getText()) == null) {
							int escolha = JOptionPane.showOptionDialog(null, "Confirmar?", "Cadastro", JOptionPane.YES_NO_CANCEL_OPTION,
									JOptionPane.WARNING_MESSAGE, null, opcoes, null);
							if (escolha == JOptionPane.YES_OPTION) {
								pessoaDao.insert(pessoa);
								JOptionPane.showMessageDialog(null, "Paciente: " + pessoa.getNome() + "\nCPF: "
										+ pessoa.getCpf() + "\nCadastrado com sucesso!!");
								pesquisar();
							} else {
								JOptionPane.showMessageDialog(null, "Cancelado!");
							}
						} else {
							JOptionPane.showMessageDialog(null, "CPF já existe!!");
						}
					}
				}
				nomeField.setText("");
				idadeField.setText("");
				profissaoField.setText("");
				cpfField.setText("");
				logradouroField.setText("");
				numeroField.setText("");
				bairroField.setText("");
				cidadeField.setText("");
				ufField.setText("");
				cepField.setText("");
				dataField.setText("");
			}
		});
		cadastrarButton.setBounds(12, 541, 99, 33);
		contentPane.add(cadastrarButton);
		
		JButton limparButton = new JButton("Limpar");
		limparButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				nomeField.setText("");
				idadeField.setText("");
				profissaoField.setText("");
				logradouroField.setText("");
				numeroField.setText("");
				bairroField.setText("");
				cidadeField.setText("");
				ufField.setText("");
				cepField.setText("");
				dataField.setText("");
			}
		});
		limparButton.setBounds(232, 319, 200, 21);
		contentPane.add(limparButton);
		
		profissaoField = new JTextField();
		profissaoField.setBackground(SystemColor.text);
		profissaoField.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Profiss\u00E3o", TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLACK));
		profissaoField.setBounds(82, 70, 354, 41);
		contentPane.add(profissaoField);
		profissaoField.setColumns(10);
		
		
		
		JLabel lblNewLabel_1 = new JLabel("\u00C1rea da sa\u00FAde?");
		lblNewLabel_1.setBounds(186, 161, 99, 13);
		contentPane.add(lblNewLabel_1);
		
		grupo = new ButtonGroup();
		grupo.add(naoRadioButton);
		grupo.add(simRadioButton);
		
		dataField = new JTextField();
		dataField.setBackground(SystemColor.text);
		dataField.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Data de nascimento", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		dataField.setBounds(12, 146, 146, 41);
		contentPane.add(dataField);
		dataField.setColumns(10);
		
		JButton listarButton = new JButton("Listar");
		listarButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pesquisar();
			}
		});
		listarButton.setBounds(123, 541, 99, 33);
		contentPane.add(listarButton);

		JLabel imagemLabel = new JLabel(imagem);
		imagemLabel.setBackground(SystemColor.activeCaption);
		imagemLabel.setBounds(448, 21, 254, 311);
		contentPane.add(imagemLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBackground(SystemColor.inactiveCaption);
		scrollPane.setBounds(12, 342, 690, 195);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Nivel", "Nascimento", "Nome", "CPF", "Idade", "Profiss\u00E3o", "\u00C1rea da Sa\u00FAde?"
			}
		) {
			Class[] columnTypes = new Class[] {
				Integer.class, Object.class, String.class, String.class, Integer.class, String.class, Boolean.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		
		JButton removerButton = new JButton("Remover");
		removerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			Pessoa pessoa = new Pessoa();
			pessoa.setCpf(cpfText.getText());
			if(pessoaDao.findByCpf(cpfText.getText()) != null) {
				if(cpfText.getText().isBlank()) {
					JOptionPane.showMessageDialog(null, "Campo vazio!!");
				}
				else {
					int escolha = JOptionPane.showConfirmDialog(null, "Realmente deseja remover?");
					if (escolha == JOptionPane.YES_OPTION) {
						pessoaDao.deleteByCpf(cpfText.getText());
						JOptionPane.showMessageDialog(null, "Usuário removido com sucesso.");
						pesquisar();
					}
					else {
						JOptionPane.showMessageDialog(null, "Cancelado!!");
					}
				}
			}
			else {
				JOptionPane.showMessageDialog(null, "Não existe!!");
			}
			cpfText.setText("");
			}
		});
		removerButton.setBounds(485, 541, 106, 33);
		contentPane.add(removerButton);
		
		cpfText = new JTextField();
		cpfText.setBackground(SystemColor.text);
		cpfText.setBorder(new TitledBorder(null, "CPF", TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLACK));
		cpfText.setBounds(232, 541, 241, 33);
		contentPane.add(cpfText);
		cpfText.setColumns(10);
		table.getColumnModel().getColumn(0).setPreferredWidth(48);
		table.getColumnModel().getColumn(2).setPreferredWidth(157);
		table.getColumnModel().getColumn(3).setPreferredWidth(131);
		table.getColumnModel().getColumn(4).setPreferredWidth(56);
		table.getColumnModel().getColumn(6).setPreferredWidth(93);
		
		setLocationRelativeTo(null);
	}
	
	public void pesquisar() {
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		model.setNumRows(0);
		for(Pessoa pessoa : pessoaDao.listarPessoas()) {
			model.addRow(new Object[] {
					pessoa.getNivel(),
					pessoa.getNascimento(),
					pessoa.getNome(), 
					pessoa.getCpf(), 
					pessoa.getIdade(),
					pessoa.getProfissao(),
					pessoa.getAreaSaude(),
			});
		}
	}
}
