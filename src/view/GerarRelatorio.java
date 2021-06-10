package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import model.dao.DaoFactory;
import model.dao.PessoaDao;
import model.entities.Pessoa;
import javax.swing.border.EtchedBorder;
import javax.swing.JTextPane;
import java.awt.Font;

public class GerarRelatorio extends JFrame {

	private JPanel contentPane;
	private JTextField dataInicialText;
	private JTextField dataFinalText;

	PessoaDao pessoaDao = DaoFactory.createPessoaDao();

	Pessoa pessoa = new Pessoa();
	private JTable table;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GerarRelatorio frame = new GerarRelatorio();
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
	public GerarRelatorio() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 831, 395);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		getContentPane().setBackground(SystemColor.activeCaption);
		
		JTextPane painelRelatorio = new JTextPane();
		painelRelatorio.setSelectionColor(Color.BLACK);
		painelRelatorio.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		painelRelatorio.setForeground(Color.BLACK);
		painelRelatorio.setEnabled(false);
		painelRelatorio.setEditable(false);
		painelRelatorio.setBounds(133, 214, 670, 132);
		contentPane.add(painelRelatorio);
		
		dataInicialText = new JTextField();
		dataInicialText.setBackground(SystemColor.inactiveCaptionBorder);
		dataInicialText.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Data inicial", TitledBorder.LEADING, TitledBorder.TOP, null, SystemColor.desktop));
		dataInicialText.setBounds(12, 10, 156, 34);
		contentPane.add(dataInicialText);
		dataInicialText.setColumns(10);
		
		dataFinalText = new JTextField();
		dataFinalText.setBackground(SystemColor.text);
		dataFinalText.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Data final", TitledBorder.LEADING, TitledBorder.TOP, null, SystemColor.activeCaptionText));
		dataFinalText.setBounds(180, 10, 156, 34);
		contentPane.add(dataFinalText);
		dataFinalText.setColumns(10);
		
		JLabel data1Label = new JLabel("");
		data1Label.setBackground(SystemColor.menu);
		data1Label.setBorder(new TitledBorder(null, "Data Inicial", TitledBorder.LEADING, TitledBorder.BOTTOM, null, Color.BLACK));
		data1Label.setBounds(12, 214, 113, 39);
		contentPane.add(data1Label);
		
		JLabel data2Label = new JLabel("");
		data2Label.setBackground(SystemColor.inactiveCaption);
		data2Label.setBorder(new TitledBorder(null, "Data Final", TitledBorder.LEADING, TitledBorder.BOTTOM, null, Color.BLACK));
		data2Label.setBounds(12, 255, 113, 39);
		contentPane.add(data2Label);
		
		JButton gerarRelatorioButton = new JButton("Gerar relat\u00F3rio");
		gerarRelatorioButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				List<Pessoa> list = new ArrayList<>();
				for(Pessoa p : pessoaDao.listarPessoas()) {
					list.add(p);
				}
				
				Date dataFormat1 = null;
				Date dataFormat2 = null;
				try {
					dataFormat2 = sdf.parse(dataFinalText.getText());
					dataFormat1 = sdf.parse(dataInicialText.getText());
				} catch (ParseException e1) {
					e1.getMessage();
				}
				if(!(dataFormat1 == null && dataFormat2 == null)) {
					long diferenca = dataFormat2.getTime() - dataFormat1.getTime();
//				JOptionPane.showMessageDialog(null, diferenca);
					TimeUnit time = TimeUnit.DAYS;
					long dias = time.convert(diferenca, TimeUnit.MILLISECONDS);
//				JOptionPane.showMessageDialog(null, dias);
					String dataInicial = sdf.format(dataFormat1);
					String dataFinal = sdf.format(dataFormat2);
//				JOptionPane.showMessageDialog(null, dataInicial + dataFinal);
					LocalDate data1 = LocalDate.parse(dataInicial, dtf);

					int cont1 = 0, cont2 = 0, cont3 = 0, cont4 = 0, total = 0;

					for (int i = 0; i <= dias; i++) {

						for (int j = 0; j < list.size(); j++) {

							if (!(list.get(j).getDataVacinado() == null)) {
								Date contandoData = Date.from(data1.atStartOfDay(ZoneId.systemDefault()).toInstant());
								String stringFormat1 = sdf.format(contandoData);
								String stringFormat2 = sdf.format(list.get(j).getDataVacinado());
//								JOptionPane.showMessageDialog(null, stringFormat1);
								if (stringFormat1.equals(stringFormat2)) {
//									JOptionPane.showMessageDialog(null, stringFormat1);
//									JOptionPane.showMessageDialog(null, stringFormat2);

									if (list.get(j).getVacinado() == true) {
										if (list.get(j).getIdade() >= 90) {
											cont1++;
										} else if (list.get(j).getIdade() >= 70 && list.get(j).getIdade() < 90) {
											cont2++;
										} else if (list.get(j).getIdade() >= 50 && list.get(j).getIdade() < 70) {
											cont3++;
										} else {
											cont4++;
										}
									}
//								else {
//									JOptionPane.showMessageDialog(null, "Não esta vacinada");
//								}
								}
							}
						}
						data1 = data1.plusDays(1);
//					Date contandoData = Date.from(data1.atStartOfDay(ZoneId.systemDefault()).toInstant());
//
//					JOptionPane.showMessageDialog(null, sdf.format(contandoData));
					}
					total = cont1 + cont2 + cont3 + cont4;
//					JOptionPane.showMessageDialog(null,"I: " + cont1);
//					JOptionPane.showMessageDialog(null,"II: " + cont2);
//					JOptionPane.showMessageDialog(null,"III: " + cont3);
//					JOptionPane.showMessageDialog(null,"IV: " + cont4);
//					JOptionPane.showMessageDialog(null,"Total: " + total);
					
					painelRelatorio.setText("Relatório: "
							+ "\n-" + dataInicial + " até " + dataFinal
							+"\nI- Acima de 90 anos: " + (cont1*100/total) + "%"
							+"\nII- Entre 70 a 90 anos: " + (cont2*100/total) + "%"
							+"\nIII- Entre 50 a 70 anos: " + (cont3*100/total) + "%"
							+"\nIV- Abaixo de 50 anos: " + (cont4*100/total) + "%"
							+"\nTotal de vacinados:  " + total);
					model.addRow(new Object[] { dataInicialText.getText() + " - " + dataFinalText.getText(), cont1,
							cont2, cont3, cont4, total });
				data1Label.setText(dataInicialText.getText());
				data2Label.setText(dataFinalText.getText());
				
				dataInicialText.setText("");
				dataFinalText.setText("");
			}
				else {
					JOptionPane.showMessageDialog(null, "Valor inválido!!");
					dataInicialText.setText("");
					dataFinalText.setText("");
				}
			}

		});
		
		gerarRelatorioButton.setBounds(348, 10, 156, 34);
		contentPane.add(gerarRelatorioButton);
		
		
		
		JButton voltarButton = new JButton("Voltar");
		voltarButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaPrincipal_Adm principal = new TelaPrincipal_Adm();
				principal.setVisible(true);
				dispose();
			}
		});
		voltarButton.setBounds(516, 10, 123, 34);
		contentPane.add(voltarButton);
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date hoje = new Date();
		
		JLabel hojeLabel = new JLabel("hoje");
		hojeLabel.setBorder(new TitledBorder(null, "Data de hoje", TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLACK));
		hojeLabel.setBounds(12, 307, 98, 39);
		contentPane.add(hojeLabel);
		
		hojeLabel.setText(sdf.format(hoje));
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBorder(new TitledBorder(null, "Relat\u00F3rio", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		scrollPane.setBounds(12, 66, 791, 138);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Data", "I - Acima de 90 anos", "II - Entre 70 a 90 anos", "III - Entre 50 a 70 anos", "IV - Abaixo de 50 anos", "V - Total de vacinados"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, Integer.class, Integer.class, Integer.class, Integer.class, Integer.class
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
		
		JPanel panel = new JPanel();
		panel.setBounds(133, 214, 670, 132);
		contentPane.add(panel);
		
	
		
		table.getColumnModel().getColumn(0).setPreferredWidth(150);
		table.getColumnModel().getColumn(1).setPreferredWidth(127);
		table.getColumnModel().getColumn(2).setPreferredWidth(135);
		table.getColumnModel().getColumn(3).setPreferredWidth(138);
		table.getColumnModel().getColumn(4).setPreferredWidth(135);
		table.getColumnModel().getColumn(5).setPreferredWidth(135);
		
		
		
		setLocationRelativeTo(null);
	}
}
