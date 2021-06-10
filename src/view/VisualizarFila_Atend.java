package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import model.dao.DaoFactory;
import model.dao.PessoaDao;
import model.entities.Pessoa;
import java.awt.SystemColor;

public class VisualizarFila_Atend extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	Date hoje = new Date();
	
	PessoaDao pessoaDao = DaoFactory.createPessoaDao();
	private JTable table;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VisualizarFila_Atend frame = new VisualizarFila_Atend();
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
	public VisualizarFila_Atend() {
		setTitle("Atendente");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 807, 439);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.activeCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton voltarButton = new JButton("Voltar");
		voltarButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaPrincipal_Atend principal = new TelaPrincipal_Atend();
				principal.setVisible(true);
				dispose();
			}
		});
		voltarButton.setBounds(627, 314, 152, 34);
		contentPane.add(voltarButton);
		
		JButton iniciarButton = new JButton("Iniciar");
		iniciarButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VisualizarFila_Confirmar_Atend visualizarConfirmar = new VisualizarFila_Confirmar_Atend();
				visualizarConfirmar.setVisible(true);
				dispose();
			}
		});
		iniciarButton.setBounds(12, 314, 152, 34);
		contentPane.add(iniciarButton);
		
		JLabel hojeLabel = new JLabel("Hoje");
		hojeLabel.setBorder(new TitledBorder(null, "Data atual", TitledBorder.LEADING, TitledBorder.TOP, null, UIManager.getColor("Button.focus")));
		hojeLabel.setBounds(12, 356, 79, 34);
		contentPane.add(hojeLabel);
		
		JButton visualizarButton = new JButton("Visualizar");
		visualizarButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pesquisar();
			
			}
		});
		visualizarButton.setBounds(463, 314, 152, 34);
		contentPane.add(visualizarButton);
		
		/**
		 * Exibindo a data atual:
		 */
		hojeLabel.setText(sdf.format(hoje));
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBorder(new TitledBorder(null, "Paciente cadastrado", TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLACK));
		scrollPane.setBounds(12, 10, 767, 294);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"N\u00EDvel", "Nascimento", "Nome", "CPF", "Idade", "Profiss\u00E3o", "\u00C1rea da Sa\u00FAde?"
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
		table.getColumnModel().getColumn(2).setPreferredWidth(141);
		table.getColumnModel().getColumn(3).setPreferredWidth(124);
		table.getColumnModel().getColumn(4).setPreferredWidth(58);
		table.getColumnModel().getColumn(5).setPreferredWidth(94);
		table.getColumnModel().getColumn(6).setPreferredWidth(98);
		
		setLocationRelativeTo(null);
	}
	/**
	 * Inserir a tabela as pessoas cadastrada.
	 */
	public void pesquisar() {
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		model.setNumRows(0);
		int cont=1;
			for(Pessoa pessoa : pessoaDao.listarPessoas()) {
				model.addRow(new Object[] { 
							pessoa.getNivel(),
							pessoa.getNascimento(),
							pessoa.getNome(), 
							pessoa.getCpf(), 
							pessoa.getIdade(),
							pessoa.getProfissao(),
							pessoa.getAreaSaude()
						
				});
			}
	}
}
