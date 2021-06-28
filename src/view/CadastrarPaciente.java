package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

import model.dao.DaoFactory;
import model.dao.PessoaDao;
import model.entities.Pessoa;
import javax.swing.border.LineBorder;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CadastrarPaciente extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField logradouroField;
	private JTextField numeroField;
	private JTextField bairroField;
	private JTextField cidadeField;
	private ButtonGroup grupo;

	Connection conn = null;
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	PessoaDao pessoaDao = DaoFactory.createPessoaDao();
	Object[] opcoes = {"Sim", "Não"};
	private JTable table;
	private JTextField cpfText;
	private JFormattedTextField dataField;
	private JFormattedTextField cepField;
	private JFormattedTextField ufField;
	private JFormattedTextField profissaoField;
	private JFormattedTextField nomeField;
	private JFormattedTextField idadeField;
	private JPanel panel;
	private JTextField emailText;
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
		setBounds(100, 100, 763, 752);
		contentPane = new JPanel();
		contentPane.setFont(new Font("Arial", Font.PLAIN, 15));
		contentPane.setForeground(Color.BLACK);
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new LineBorder(new Color(0, 0, 0)));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		getContentPane().setBackground(SystemColor.activeCaption);
		
		grupo = new ButtonGroup();
		
		panel = new JPanel();
		panel.setBackground(SystemColor.inactiveCaption);
		panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Dados do paciente", TitledBorder.LEADING, TitledBorder.TOP, null, SystemColor.desktop));
		panel.setBounds(12, 10, 723, 693);
		contentPane.add(panel);
		panel.setLayout(null);
		
				JLabel imagemLabel = new JLabel(new ImageIcon("C:\\Users\\haruo\\eclipse-workspace\\KazeBlock\\src\\view\\imagem\\logoTipo8.JPG"));
				imagemLabel.setBounds(457, 10, 254, 413);
				panel.add(imagemLabel);
				imagemLabel.setBackground(SystemColor.activeCaption);
				
				nomeField = new JFormattedTextField();
				nomeField.setBounds(12, 20, 424, 46);
				panel.add(nomeField);
				nomeField.setFont(new Font("Arial", Font.BOLD, 15));
				nomeField.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Nome", TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLACK));
				
				cpfText = new JTextField();
				cpfText.setBounds(12, 638, 241, 43);
				panel.add(cpfText);
				cpfText.setFont(new Font("Arial", Font.BOLD, 15));
				cpfText.setBackground(SystemColor.text);
				cpfText.setBorder(new TitledBorder(null, "CPF", TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLACK));
				cpfText.setColumns(10);
				
				JButton voltarButton = new JButton("Voltar");
				voltarButton.setBounds(597, 638, 114, 46);
				panel.add(voltarButton);
				voltarButton.setFont(new Font("Arial", Font.BOLD, 15));
				
				JButton removerButton = new JButton("Remover");
				removerButton.setBounds(265, 638, 127, 46);
				panel.add(removerButton);
				removerButton.setFont(new Font("Arial", Font.BOLD, 15));
				
				JButton listarButton = new JButton("Listar");
				listarButton.setBounds(404, 638, 127, 46);
				panel.add(listarButton);
				listarButton.setFont(new Font("Arial", Font.BOLD, 15));
				
				JScrollPane scrollPane = new JScrollPane();
				scrollPane.setBounds(12, 433, 699, 195);
				panel.add(scrollPane);
				scrollPane.setFont(new Font("Arial", Font.PLAIN, 15));
				scrollPane.setBackground(SystemColor.inactiveCaption);
				
				table = new JTable();
				table.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						List<Pessoa> listPessoa = new ArrayList<>();
						int indiceLinha = table.getSelectedRow();
						for(Pessoa x : pessoaDao.listarDetalhe()) {
							listPessoa.add(x);
						}
						for(int i=0; i<listPessoa.size(); i++) {
							String cpf = table.getModel().getValueAt(indiceLinha, 3).toString();
							if(listPessoa.get(i).getCpf().equals(cpf)) {
								String vacinado = null;
								String areaSaude = null;
								String data = null;
								cpfText.setText(cpf);
								if(listPessoa.get(i).getVacinado() == true) {
									vacinado = "Sim.";
								}
								else {
									vacinado = "Não";
								}
								if(listPessoa.get(i).getAreaSaude() == true) {
									areaSaude = "Sim.";
								}
								else {
									areaSaude = "Não.";
								}
								if(listPessoa.get(i).getDataVacinado() != null) {
									data = sdf.format(listPessoa.get(i).getDataVacinado());
								}
								else {
									data = "Indefinida.";
								}
								String dados = "Dados deste paciente: "
										+ "\nNome: " + listPessoa.get(i).getNome() 
										+ "\nIdade: " + listPessoa.get(i).getIdade() 
										+ "\nProfissão: " + listPessoa.get(i).getProfissao() 
										+ "\nA profissão pertence à área da saúde?: " + areaSaude 
										+ "\nCPF: " + listPessoa.get(i).getCpf() 
										+ "\nTelefone: " + listPessoa.get(i).getTelefone()
										+ "\nEmail: " + listPessoa.get(i).getEmail()
										+ "\nNascimento: " + sdf.format(listPessoa.get(i).getNascimento())  
										+ "\nLogradouro: " + listPessoa.get(i).getLogradouro() 
										+ "\nNúmero: " + listPessoa.get(i).getNumero() 
										+ "\nCidade: " + listPessoa.get(i).getCidade() 
										+ "\nUF: " + listPessoa.get(i).getUf() 
										+ "\nCEP: " + listPessoa.get(i).getCep() 
										+ "\nJá foi vacinada?: " + vacinado 
										+ "\nData em que foi vacinada: " + data
										+ "\nO seu nível de prioridade: " + listPessoa.get(i).getNivel();
								JOptionPane.showMessageDialog(null, dados, "Informação do paciente", JOptionPane.INFORMATION_MESSAGE);
								
							}
						}
						
					}
				});
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
				
				JButton limparButton = new JButton("Limpar");
				limparButton.setBounds(236, 382, 200, 41);
				panel.add(limparButton);
				limparButton.setFont(new Font("Arial", Font.BOLD, 15));
				
				JButton cadastrarButton = new JButton("Cadastrar");
				cadastrarButton.setBounds(12, 382, 200, 41);
				panel.add(cadastrarButton);
				cadastrarButton.setFont(new Font("Arial", Font.BOLD, 15));
				
				cidadeField = new JTextField();
				cidadeField.setBounds(12, 331, 114, 41);
				panel.add(cidadeField);
				cidadeField.setFont(new Font("Arial", Font.BOLD, 15));
				cidadeField.setBackground(SystemColor.text);
				cidadeField.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Cidade", TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLACK));
				cidadeField.setColumns(10);
				
				ufField = new JFormattedTextField(setFormatar("UU"));
				ufField.setBounds(134, 331, 99, 41);
				panel.add(ufField);
				ufField.setFont(new Font("Arial", Font.BOLD, 15));
				ufField.setBorder(new TitledBorder(null, "UF", TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLACK));
				
				cepField = new JFormattedTextField(setFormatar("########"));
				cepField.setBounds(236, 331, 200, 41);
				panel.add(cepField);
				cepField.setFont(new Font("Arial", Font.BOLD, 15));
				cepField.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "CEP", TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLACK));
				
				numeroField = new JTextField();
				numeroField.setBounds(12, 280, 75, 41);
				panel.add(numeroField);
				numeroField.setFont(new Font("Arial", Font.BOLD, 15));
				numeroField.setBackground(SystemColor.text);
				numeroField.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "N\u00B0", TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLACK));
				numeroField.setColumns(10);
				
				bairroField = new JTextField();
				bairroField.setBounds(94, 280, 342, 41);
				panel.add(bairroField);
				bairroField.setFont(new Font("Arial", Font.BOLD, 15));
				bairroField.setBackground(SystemColor.text);
				bairroField.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Bairro", TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLACK));
				bairroField.setColumns(10);
				
				logradouroField = new JTextField();
				logradouroField.setBounds(12, 232, 424, 41);
				panel.add(logradouroField);
				logradouroField.setFont(new Font("Arial", Font.BOLD, 15));
				logradouroField.setBackground(SystemColor.text);
				logradouroField.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Logradouro", TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLACK));
				logradouroField.setColumns(10);
				
				dataField = new JFormattedTextField(setFormatar("##/##/####"));
				dataField.setBounds(12, 180, 146, 42);
				panel.add(dataField);
				dataField.setHorizontalAlignment(SwingConstants.LEFT);
				dataField.setFont(new Font("Arial", Font.BOLD, 15));
				dataField.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Nascimento", TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLACK));
				
				
				
				JLabel lblNewLabel_1 = new JLabel("\u00C1rea da sa\u00FAde?");
				lblNewLabel_1.setBounds(161, 196, 114, 18);
				panel.add(lblNewLabel_1);
				lblNewLabel_1.setFont(new Font("Arial", Font.BOLD, 15));
				
				JRadioButton simRadioButton = new JRadioButton("Sim");
				simRadioButton.setBounds(287, 187, 58, 27);
				panel.add(simRadioButton);
				simRadioButton.setFont(new Font("Arial", Font.BOLD, 15));
				simRadioButton.setBackground(SystemColor.inactiveCaption);
				simRadioButton.setBorder(new TitledBorder(null, "\u00C1rea da sa\u00FAde", TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLACK));
				grupo.add(simRadioButton);
				
				JRadioButton naoRadioButton = new JRadioButton("N\u00E3o");
				naoRadioButton.setBounds(357, 188, 58, 25);
				panel.add(naoRadioButton);
				naoRadioButton.setFont(new Font("Arial", Font.BOLD, 15));
				naoRadioButton.setBackground(SystemColor.inactiveCaption);
				naoRadioButton.setBorder(new TitledBorder(null, "\u00C1rea da sa\u00FAde", TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLACK));
				grupo.add(naoRadioButton);
				
				JFormattedTextField cpfField = new JFormattedTextField(setFormatar("###########"));
				cpfField.setBounds(12, 126, 200, 44);
				panel.add(cpfField);
				cpfField.setBackground(Color.WHITE);
				cpfField.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "CPF", TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLACK));
				cpfField.setFont(new Font("Arial", Font.BOLD, 15));
				
				profissaoField = new JFormattedTextField();
				profissaoField.setBounds(82, 76, 151, 40);
				panel.add(profissaoField);
				profissaoField.setFont(new Font("Arial", Font.BOLD, 15));
				profissaoField.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Profiss\u00E3o", TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLACK));
				
				idadeField = new JFormattedTextField();
				idadeField.setBounds(12, 76, 58, 38);
				panel.add(idadeField);
				idadeField.setFont(new Font("Arial", Font.BOLD, 15));
				idadeField.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Idade", TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLACK));
				
				emailText = new JTextField();
				emailText.setFont(new Font("Arial", Font.BOLD, 15));
				emailText.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Email", TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLACK));
				emailText.setBounds(249, 76, 187, 40);
				panel.add(emailText);
				emailText.setColumns(10);
				
				JFormattedTextField telefoneText = new JFormattedTextField(setFormatar("(##)####-####"));
				telefoneText.setBorder(new TitledBorder(null, "Telefone", TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLACK));
				telefoneText.setBounds(224, 127, 212, 41);
				panel.add(telefoneText);
				
				cadastrarButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						Pessoa pessoa = new Pessoa();
						Object[] opcoes = {"Sim", "Não"};
						pessoa.setNome(nomeField.getText());
						pessoa.setIdade(Integer.parseInt(idadeField.getText()));
						pessoa.setEmail(emailText.getText());
						pessoa.setTelefone(telefoneText.getText());
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
									|| dataField.getText().isEmpty() || telefoneText.getText().isEmpty()) {
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
						telefoneText.setText("");
						emailText.setText("");
					}
				});
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
						telefoneText.setText("");
						emailText.setText("");
					}
				});
				
				table.getColumnModel().getColumn(0).setPreferredWidth(48);
				table.getColumnModel().getColumn(2).setPreferredWidth(157);
				table.getColumnModel().getColumn(3).setPreferredWidth(131);
				table.getColumnModel().getColumn(4).setPreferredWidth(56);
				table.getColumnModel().getColumn(6).setPreferredWidth(93);
				listarButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						pesquisar();
					}
				});
				removerButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
					Pessoa pessoa = new Pessoa();
					pessoa.setCpf(cpfText.getText());
					if(pessoaDao.findByCpf(cpfText.getText()) != null) {
						if(cpfText.getText().isBlank()) {
							JOptionPane.showMessageDialog(null, "Campo vazio!!");
						}
						else {
							int escolha = JOptionPane.showOptionDialog(null, "Confirmar?", "Cadastro", JOptionPane.YES_NO_CANCEL_OPTION,
									JOptionPane.WARNING_MESSAGE, null, opcoes, null);
							if (escolha == JOptionPane.YES_OPTION) {
								pessoaDao.deleteByCpf(cpfText.getText());
								JOptionPane.showMessageDialog(null, "Paciente removido com sucesso.");
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
				voltarButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						TelaPrincipal_Adm principal = new TelaPrincipal_Adm();
						principal.setVisible(true);
						dispose();
					}
				});
		
		setLocationRelativeTo(null);
	}
	
	public void pesquisar() {
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		model.setNumRows(0);
		for(Pessoa pessoa : pessoaDao.listarPessoas()) {
			model.addRow(new Object[] {
					pessoa.getNivel(),
					sdf.format(pessoa.getNascimento()),
					pessoa.getNome(), 
					pessoa.getCpf(), 
					pessoa.getIdade(),
					pessoa.getProfissao(),
					pessoa.getAreaSaude(),
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
