package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

import model.dao.DaoFactory;
import model.dao.PessoaDao;
import model.entities.OrdenandoPaciente;
import model.entities.Pessoa;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class VisualizarFila_Confirmar_Atend extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	
	PessoaDao pessoaDao = DaoFactory.createPessoaDao();
	
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	Date hoje = new Date();
	private JTable table;
	private JTable table_1;
	Object[] opcoes = {"Sim", "Não"};

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VisualizarFila_Confirmar_Atend frame = new VisualizarFila_Confirmar_Atend();
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
	public VisualizarFila_Confirmar_Atend() {
		setTitle("Atendente - Confirma\u00E7\u00E3o da vacina");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 686, 488);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.activeCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		ButtonGroup grupo = new ButtonGroup();
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Confirmar vacina\u00E7\u00E3o", TitledBorder.LEADING, TitledBorder.TOP, null, SystemColor.desktop));
		panel_1.setBackground(SystemColor.inactiveCaption);
		panel_1.setBounds(12, 10, 646, 429);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel dataHojeLabel = new JLabel("Data de Hoje");
		dataHojeLabel.setBounds(12, 20, 108, 34);
		panel_1.add(dataHojeLabel);
		dataHojeLabel.setFont(new Font("Arial", Font.BOLD, 15));
		dataHojeLabel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Data de hoje", TitledBorder.LEADING, TitledBorder.TOP, null, SystemColor.desktop));
				
		dataHojeLabel.setText(sdf.format(hoje));
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 64, 622, 217);
		panel_1.add(scrollPane);
		scrollPane.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Ordem de vacina\u00E7\u00E3o", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Fila", "Nivel de prioridade", "Nome", "Vacinado?", "Data"
			}
		) {
			Class[] columnTypes = new Class[] {
				Integer.class, Integer.class, String.class, Boolean.class, Object.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		
		JButton atualizarButton = new JButton("Ordenar");
		atualizarButton.setBounds(506, 291, 128, 59);
		panel_1.add(atualizarButton);
		atualizarButton.setFont(new Font("Arial", Font.BOLD, 15));
		
		JButton voltarButton = new JButton("Voltar");
		voltarButton.setBounds(543, 379, 91, 40);
		panel_1.add(voltarButton);
		voltarButton.setFont(new Font("Arial", Font.BOLD, 15));
		
		JButton okButton = new JButton("OK");
		okButton.setBounds(450, 380, 81, 39);
		panel_1.add(okButton);
		okButton.setFont(new Font("Arial", Font.BOLD, 15));
		
		JRadioButton ausenteRadioButton = new JRadioButton("Ausente");
		ausenteRadioButton.setBounds(320, 389, 91, 21);
		panel_1.add(ausenteRadioButton);
		ausenteRadioButton.setFont(new Font("Arial", Font.BOLD, 15));
		ausenteRadioButton.setBackground(SystemColor.inactiveCaption);
		grupo.add(ausenteRadioButton);
		
		JRadioButton confirmarRadioButton = new JRadioButton("Confirmar");
		confirmarRadioButton.setBounds(212, 389, 104, 21);
		panel_1.add(confirmarRadioButton);
		confirmarRadioButton.setFont(new Font("Arial", Font.BOLD, 15));
		confirmarRadioButton.setBackground(SystemColor.inactiveCaption);
		grupo.add(confirmarRadioButton);
		
		JFormattedTextField textField = new JFormattedTextField(setFormatar("###########"));
		textField.setBounds(12, 372, 200, 45);
		panel_1.add(textField);
		textField.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "CPF", TitledBorder.LEADING, TitledBorder.TOP, null, SystemColor.desktop));
		textField.setFont(new Font("Arial", Font.BOLD, 15));
		
		JPanel panel = new JPanel();
		panel.setBounds(12, 291, 482, 59);
		panel_1.add(panel);
		panel.setLayout(null);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		scrollPane_1.setBounds(0, 0, 482, 59);
		panel.add(scrollPane_1);
		scrollPane_1.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Sequ\u00EAncia", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		
		table_1 = new JTable();
		table_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				List<Pessoa> listPessoa = new ArrayList<>();
				int indiceLinha = table_1.getSelectedRow();
				String cpf = table_1.getModel().getValueAt(indiceLinha, 3).toString();
				for(Pessoa x : pessoaDao.listarDetalhe()) {
					listPessoa.add(x);
				}
				for(int i=0; i<listPessoa.size(); i++) {
					if(listPessoa.get(i).getCpf().equals(cpf)) {
						String vacinado = null;
						String areaSaude = null;
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
								+ "\nO seu nível de prioridade: " + listPessoa.get(i).getNivel();
						JOptionPane.showMessageDialog(null, dados, "Informação do paciente", JOptionPane.INFORMATION_MESSAGE);
					}
				}
			}
		});
		scrollPane_1.setViewportView(table_1);
		table_1.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Fila", "Vacinado?", "Nome", "CPF"
			}
		) {
			Class[] columnTypes = new Class[] {
				Integer.class, Boolean.class, String.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
				false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		
		
		table_1.getColumnModel().getColumn(2).setPreferredWidth(168);
		table_1.getColumnModel().getColumn(3).setPreferredWidth(124);
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Pessoa pessoa = new Pessoa();
				pessoa.setCpf(textField.getText());
				pessoa = pessoaDao.findByCpf(pessoa.getCpf());
				if(confirmarRadioButton.isSelected()) {
					int escolha = JOptionPane.showOptionDialog(null, "Confirmar?", "Cadastro", JOptionPane.YES_NO_CANCEL_OPTION,
							JOptionPane.WARNING_MESSAGE, null, opcoes, null);
					if(escolha == JOptionPane.YES_OPTION) {
					pessoa.setVacinado(true);
					pessoa.setDataVacinado(hoje);
					pessoaDao.confirmarVacina(pessoa);
					JOptionPane.showMessageDialog(null, "Vacinação confirmada!!");
					textField.setText("");
					atualizar();
					confirmar();
					}
					else {
						JOptionPane.showMessageDialog(null, "Cancelado!");
					}
				}
				else if(ausenteRadioButton.isSelected()) {
					int escolha = JOptionPane.showOptionDialog(null, "Confirmar?", "Cadastro", JOptionPane.YES_NO_CANCEL_OPTION,
							JOptionPane.WARNING_MESSAGE, null, opcoes, null);
					if(escolha == JOptionPane.YES_OPTION) {
					pessoaDao.deleteByCpf(textField.getText());
					JOptionPane.showMessageDialog(null, "Ausente.");
					textField.setText("");
					atualizar();
					confirmar();
					}
					else {
						JOptionPane.showMessageDialog(null, "Cancelado!");
					}
				}
			}
		});
		voltarButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VisualizarFila_Atend visualizarFila = new VisualizarFila_Atend();
				visualizarFila.setVisible(true);
				dispose();
			}
		});
		atualizarButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				atualizar();
				confirmar();
			}
		});
		table.getColumnModel().getColumn(1).setPreferredWidth(109);
		table.getColumnModel().getColumn(2).setPreferredWidth(164);

		
		
		setLocationRelativeTo(null);
	}
	
	public void atualizar() {
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		JTextPane sequencia = new JTextPane();
		PessoaDao pessoaDao = DaoFactory.createPessoaDao();
		Pessoa pessoa = new Pessoa();
		model.setNumRows(0);
		/**
		 * Adicionar nivel de prioridade no bd, e adicionar atraves do cadastro de
		 * paciente depois instanciar o nivel de prioridade neste método, e ordenar
		 * atraves deste niveis 1,2,3 jogando no if maior menor e depois listando a
		 * ordem.
		 */
		List<Pessoa> listP = new ArrayList<>();
		for(Pessoa dados : pessoaDao.listarPessoas()) {
			listP.add(dados);
		}
		Collections.sort(listP,new OrdenandoPaciente());
		//JOptionPane.showMessageDialog(null, listP);
		int i = 0;
		
		for(Pessoa p : listP) {
			if (p.getVacinado() == null || p.getVacinado() == false) {
			model.addRow(new Object[] { 
					(i+1),
					p.getNivel(),
					p.getNome(),
					p.getVacinado(),
					p.getDataVacinado()
			});
			i++;
			}
		}

	}

	public void confirmar() {
		DefaultTableModel model = (DefaultTableModel) table_1.getModel();
		PessoaDao pessoaDao = DaoFactory.createPessoaDao();
		model.setNumRows(0);
		
		List<Pessoa> listP = new ArrayList<>();
		for(Pessoa dados : pessoaDao.listarPessoas()) {
			listP.add(dados);
		}
		Collections.sort(listP,new OrdenandoPaciente());
		//JOptionPane.showMessageDialog(null, listP);
		int cont = 0;
	
		for(Pessoa p : listP) {
			if (p.getVacinado() == null || p.getVacinado() == false) {
				
				model.addRow(new Object[] { 
						(cont + 1), 
						p.getVacinado(), 
						p.getNome(), 
						p.getCpf() });
				cont++;
			}
		
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

