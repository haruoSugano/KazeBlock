package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import model.dao.DaoFactory;
import model.dao.PessoaDao;
import model.entities.OrdenandoPaciente;
import model.entities.Pessoa;
import javax.swing.JTextArea;
import java.awt.SystemColor;

public class VisualizarFila_Confirmar_Adm extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	
	PessoaDao pessoaDao = DaoFactory.createPessoaDao();
	
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	Date hoje = new Date();
	private JTable table;
	private JTable table_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VisualizarFila_Confirmar_Adm frame = new VisualizarFila_Confirmar_Adm();
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
	public VisualizarFila_Confirmar_Adm() {
		setTitle("Administrador");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 625, 484);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.activeCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel dataHojeLabel = new JLabel("Data de Hoje");
		dataHojeLabel.setBorder(new TitledBorder(null, "Data de hoje", TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLACK));
		dataHojeLabel.setBounds(12, 10, 108, 34);
		contentPane.add(dataHojeLabel);
		
		dataHojeLabel.setText(sdf.format(hoje));
		
		JRadioButton confirmarRadioButton = new JRadioButton("Confirmar");
		confirmarRadioButton.setBackground(SystemColor.activeCaption);
		confirmarRadioButton.setBounds(220, 412, 104, 21);
		contentPane.add(confirmarRadioButton);
		
		JRadioButton ausenteRadioButton = new JRadioButton("Ausente");
		ausenteRadioButton.setBackground(SystemColor.activeCaption);
		ausenteRadioButton.setBounds(323, 412, 81, 21);
		contentPane.add(ausenteRadioButton);
		
		ButtonGroup grupo = new ButtonGroup();
		grupo.add(confirmarRadioButton);
		grupo.add(ausenteRadioButton);
		
		JButton voltarButton = new JButton("Voltar");
		voltarButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VisualizarFila_Adm visualizarFila = new VisualizarFila_Adm();
				visualizarFila.setVisible(true);
				dispose();
			}
		});
		voltarButton.setBounds(506, 412, 91, 21);
		contentPane.add(voltarButton);
		
		JButton okButton = new JButton("OK");
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Pessoa pessoa = new Pessoa();
				pessoa.setCpf(textField.getText());
				pessoa = pessoaDao.findByCpf(pessoa.getCpf());
				if(confirmarRadioButton.isSelected()) {
					//enviar true para o bd
					pessoa.setVacinado(true);
					pessoa.setDataVacinado(hoje);
					pessoaDao.confirmarVacina(pessoa);
					JOptionPane.showMessageDialog(null, "Vacinação confirmada!!");
					textField.setText("");
					atualizar();
					confirmar();
				}
				else if(ausenteRadioButton.isSelected()) {
					pessoaDao.deleteByCpf(textField.getText());
					JOptionPane.showMessageDialog(null, "Ausente.");
					textField.setText("");
					atualizar();
					confirmar();
				}
			}
		});
		okButton.setBounds(413, 413, 81, 21);
		contentPane.add(okButton);
		
		textField = new JTextField();
		textField.setBorder(new TitledBorder(null, "CPF", TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLACK));
		textField.setBounds(12, 401, 200, 34);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton atualizarButton = new JButton("Ordenar");
		atualizarButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				atualizar();
				confirmar();
			}
		});
		atualizarButton.setBounds(506, 281, 91, 21);
		contentPane.add(atualizarButton);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBorder(new TitledBorder(null, "Ordem de vacina\u00E7\u00E3o", TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLACK));
		scrollPane.setBounds(12, 54, 585, 217);
		contentPane.add(scrollPane);
		
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
		
		JPanel panel = new JPanel();
		panel.setBounds(12, 279, 482, 59);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(0, 0, 482, 59);
		panel.add(scrollPane_1);
		scrollPane_1.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Sequ\u00EAncia", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		
		table_1 = new JTable();
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
		table.getColumnModel().getColumn(1).setPreferredWidth(109);
		table.getColumnModel().getColumn(2).setPreferredWidth(164);

		
		
		setLocationRelativeTo(null);
	}
	
	public void atualizar() {
		DefaultTableModel model = (DefaultTableModel) table.getModel();
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
			
			}
			cont++;
		}
		}
	}

