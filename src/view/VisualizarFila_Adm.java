package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import model.dao.DaoFactory;
import model.dao.PessoaDao;
import model.entities.DadosPaciente;
import model.entities.Pessoa;
import java.awt.SystemColor;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTextField;
import javax.swing.JTextPane;

public class VisualizarFila_Adm extends JFrame {

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
					VisualizarFila_Adm frame = new VisualizarFila_Adm();
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
	public VisualizarFila_Adm() {
		setTitle("Administrador - Visualiza\u00E7\u00E3o de fila");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 807, 439);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.activeCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton voltarButton = new JButton("Voltar");
		voltarButton.setFont(new Font("Arial", Font.BOLD, 15));
		voltarButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaPrincipal_Adm principal = new TelaPrincipal_Adm();
				principal.setVisible(true);
				dispose();
			}
		});
		voltarButton.setBounds(627, 314, 152, 34);
		contentPane.add(voltarButton);
		
		JButton iniciarButton = new JButton("Iniciar");
		iniciarButton.setFont(new Font("Arial", Font.BOLD, 15));
		iniciarButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VisualizarFila_Confirmar_Adm visualizarConfirmar = new VisualizarFila_Confirmar_Adm();
				visualizarConfirmar.setVisible(true);
				dispose();
			}
		});
		iniciarButton.setBounds(12, 314, 152, 34);
		contentPane.add(iniciarButton);
		
		JLabel hojeLabel = new JLabel("Hoje");
		hojeLabel.setFont(new Font("Arial", Font.BOLD, 15));
		hojeLabel.setBorder(new TitledBorder(null, "Data atual", TitledBorder.LEADING, TitledBorder.TOP, null, UIManager.getColor("Button.focus")));
		hojeLabel.setBounds(12, 356, 99, 34);
		contentPane.add(hojeLabel);
		
		JButton visualizarButton = new JButton("Visualizar");
		visualizarButton.setFont(new Font("Arial", Font.BOLD, 15));
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
						DadosPaciente dado = new DadosPaciente();
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
						JOptionPane.showMessageDialog(null, dado, "Informação do paciente", JOptionPane.INFORMATION_MESSAGE);
					}
				}
			}
		});
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"N\u00EDvel", "Data vacinada", "Nome", "CPF", "Idade", "Profiss\u00E3o", "Vacinado?"
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
		
		JTextPane textPane = new JTextPane();
		textPane.setForeground(SystemColor.desktop);
		textPane.setBackground(SystemColor.activeCaption);
		textPane.setBorder(new TitledBorder(null, "Obs:", TitledBorder.LEADING, TitledBorder.TOP, null, SystemColor.desktop));
		textPane.setFont(new Font("Arial", Font.PLAIN, 12));
		textPane.setEditable(false);
		textPane.setBounds(176, 314, 152, 76);
		contentPane.add(textPane);
		textPane.setText("Níveis de prioridades: "
				+"\nNível 1: Baixa."
				+"\nNível 2: Intermediária."
				+"\nNível 3: Alta.");
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
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		model.setNumRows(0);
			for(Pessoa pessoa : pessoaDao.listarPessoas()) {
				String data = null;
				if(pessoa.getDataVacinado() !=null) {
					data = sdf.format(pessoa.getDataVacinado());
				}
				else {
					data = null;
				}
				
				model.addRow(new Object[] { 
							pessoa.getNivel(),
							data,
							pessoa.getNome(), 
							pessoa.getCpf(), 
							pessoa.getIdade(),
							pessoa.getProfissao(),
							pessoa.getVacinado()
						
				});
			}
	}
}
