package view;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
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
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import model.dao.DaoFactory;
import model.dao.PessoaDao;
import model.entities.Pessoa;
import javax.swing.border.EtchedBorder;
import java.awt.Label;
import java.awt.Panel;
import javax.swing.ImageIcon;

public class GerarRelatorio extends JFrame {

	private JPanel contentPane;

	PessoaDao pessoaDao = DaoFactory.createPessoaDao();
	public static final String RESULT = "C:\\temp\\RelatorioVacina.pdf";

	Pessoa pessoa = new Pessoa();
	private JTable table;
	Object[] opcoes = {"Sim", "Não"};
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
		setTitle("Tela - Relat\u00F3rio");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 852, 542);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		getContentPane().setBackground(SystemColor.activeCaption);
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date hoje = new Date();
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Gerar relat\u00F3rio", TitledBorder.LEADING, TitledBorder.TOP, null, SystemColor.desktop));
		panel_1.setBackground(SystemColor.inactiveCaption);
		panel_1.setBounds(12, 10, 812, 483);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel hojeLabel = new JLabel("hoje");
		hojeLabel.setBounds(678, 355, 122, 39);
		panel_1.add(hojeLabel);
		hojeLabel.setFont(new Font("Arial", Font.BOLD, 15));
		hojeLabel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Data de hoje", TitledBorder.LEADING, TitledBorder.TOP, null, SystemColor.desktop));
		
		hojeLabel.setText(sdf.format(hoje));
		
		JLabel data2Label = new JLabel("");
		data2Label.setBounds(678, 278, 122, 46);
		panel_1.add(data2Label);
		data2Label.setFont(new Font("Arial", Font.BOLD, 15));
		data2Label.setBackground(SystemColor.inactiveCaption);
		data2Label.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Data Final", TitledBorder.LEADING, TitledBorder.BOTTOM, null, SystemColor.desktop));
		
		JLabel data1Label = new JLabel("");
		data1Label.setBounds(678, 222, 122, 46);
		panel_1.add(data1Label);
		data1Label.setFont(new Font("Arial", Font.BOLD, 15));
		data1Label.setBackground(SystemColor.menu);
		data1Label.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Data Inicial", TitledBorder.LEADING, TitledBorder.BOTTOM, null, SystemColor.desktop));
		
		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.inactiveCaption);
		panel.setBounds(12, 222, 654, 251);
		panel_1.add(panel);
		panel.setLayout(null);
		
		JTextPane painelRelatorio = new JTextPane();
		painelRelatorio.setBounds(0, 0, 654, 251);
		panel.add(painelRelatorio);
		painelRelatorio.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Relat\u00F3rio", TitledBorder.LEADING, TitledBorder.TOP, null, SystemColor.desktop));
		painelRelatorio.setSelectionColor(Color.BLACK);
		painelRelatorio.setFont(new Font("Times New Roman", Font.BOLD, 16));
		painelRelatorio.setForeground(Color.BLACK);
		painelRelatorio.setEnabled(false);
		painelRelatorio.setEditable(false);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 74, 791, 138);
		panel_1.add(scrollPane);
		scrollPane.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Dados", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		
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
		
		JButton gerarRelatorioButton = new JButton("Gerar relat\u00F3rio");
		gerarRelatorioButton.setBounds(208, 25, 156, 39);
		panel_1.add(gerarRelatorioButton);
		gerarRelatorioButton.setFont(new Font("Arial", Font.BOLD, 15));
		
		JFormattedTextField dataFinalText = new JFormattedTextField(setFormatarData("##/##/####"));
		dataFinalText.setBounds(110, 25, 86, 39);
		panel_1.add(dataFinalText);
		dataFinalText.setHorizontalAlignment(SwingConstants.CENTER);
		dataFinalText.setFont(new Font("Arial", Font.BOLD, 15));
		dataFinalText.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Data final", TitledBorder.LEADING, TitledBorder.TOP, null, SystemColor.desktop));
		
		JFormattedTextField dataInicialText = new JFormattedTextField(setFormatarData("##/##/####"));
		dataInicialText.setBounds(12, 25, 86, 39);
		panel_1.add(dataInicialText);
		dataInicialText.setHorizontalAlignment(SwingConstants.CENTER);
		dataInicialText.setFont(new Font("Arial", Font.BOLD, 15));
		dataInicialText.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Data inicial", TitledBorder.LEADING, TitledBorder.TOP, null, SystemColor.desktop));
		
		
		
		JButton voltarButton = new JButton("Voltar");
		voltarButton.setBounds(678, 439, 123, 34);
		panel_1.add(voltarButton);
		voltarButton.setFont(new Font("Arial", Font.BOLD, 15));
		voltarButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaPrincipal_Adm principal = new TelaPrincipal_Adm();
				principal.setVisible(true);
				dispose();
			}
		});
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
				/**
				 * Entrada de datas: inicial e final 
				 */
				try {
					dataFormat2 = sdf.parse(dataFinalText.getText());
					dataFormat1 = sdf.parse(dataInicialText.getText());
				} catch (ParseException e1) {
					e1.getMessage();
				}
				/**
				 * subtraindo as duas datas para contar os dias.
				 */
				if(!(dataFormat1 == null && dataFormat2 == null)) {
					long diferenca = dataFormat2.getTime() - dataFormat1.getTime();
//				JOptionPane.showMessageDialog(null, diferenca);
					TimeUnit time = TimeUnit.DAYS;
					long dias = time.convert(diferenca, TimeUnit.MILLISECONDS);
//				JOptionPane.showMessageDialog(null, dias);
					/**
					 * Convetendo a data inicial e a final em uma String
					 */
					String dataInicial = sdf.format(dataFormat1);
					String dataFinal = sdf.format(dataFormat2);
//				JOptionPane.showMessageDialog(null, dataInicial + dataFinal);
					/**
					 * Transformando a data em uma localDate
					 */
					LocalDate data1 = LocalDate.parse(dataInicial, dtf);

					int cont1 = 0, cont2 = 0, cont3 = 0, cont4 = 0, total = 0, totalCadastrado = 0;
					/**
					 * Para que faca a busca de data e contando as pessoas vacinadas em determinado dia
					 */
					if(diferenca >= 0) {
					for (int i = 0; i <= dias; i++) {

						for (int j = 0; j < list.size(); j++) {

							if (!(list.get(j).getDataVacinado() == null)) {
								/**
								 * data1 sendo convertida para Date e depois para a String e transformando data do bd em uma String
								 */
								Date contandoData = Date.from(data1.atStartOfDay(ZoneId.systemDefault()).toInstant());
								String stringFormat1 = sdf.format(contandoData);
								String stringFormat2 = sdf.format(list.get(j).getDataVacinado());

								/**
								 * Comparando a stringFormat1 para buscar no banco de dados se essa data existe
								 */
								if (stringFormat1.equals(stringFormat2)) {
									/**
									 * contando pesssoas por faixa etária
									 */
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
							}//aqui
						}
						/**
						 * La em cima foi convertido o Date para LocalDate para poder usar esta funcionalidade.
						 * Somando o apenas o dia do data1
						 */
						data1 = data1.plusDays(1);
					}
					/**
					 * Somando total de pessoas vacinadas:
					 */
					total = cont1 + cont2 + cont3 + cont4;
					/**
					 * Somando total de cadastrado:
					 */
					totalCadastrado = list.size();
					/**
					 * Para mostrar a diferenca de pessoas já vacinada
					 */
					int falta = totalCadastrado - total;
					/**
					 * Mostrar em porcentagem pessoas que faltam:
					 */
					double x = 100 -((total*100)/(totalCadastrado));
					/**
					 * Mostrando no campo texto o resultado da média de vacinado:
					 */
					//coorigir o erro do zero
					String relatorio = null;
					if(cont1 == 0 && cont2 == 0 && cont3 == 0 && cont4 == 0) {
						JOptionPane.showMessageDialog(null, "Nesta data não existe nenhuma pessoa vacinada");
						painelRelatorio.setText("Nenhuma pessoa vacinada.");
					}
					else if(cont1 > 0 || cont2 > 0 || cont3 > 0 || cont4 > 0){
						relatorio = "Relatório: "
								+"\nDe Pessoas Vacinadas:"
								+ "\nNo período de " + dataInicial + " até " + dataFinal
								+"\nI- Acima de 90 anos: " + (cont1*100/total) + "%"
								+"\nII- Entre 70 a 90 anos: " + (cont2*100/total) + "%"
								+"\nIII- Entre 50 a 70 anos: " + (cont3*100/total) + "%"
								+"\nIV- Abaixo de 50 anos: " + (cont4*100/total) + "%"
								+"\nTotal de vacinados:  " + total
								+"\nTotal cadastrado: " + totalCadastrado
								+"\nFaltando: " + falta + " pessoas a serem vacinada."
								+"\nNo total de " + totalCadastrado + " pessoas cadastradas, " + x + "%" + " ainda não foram vacinada.";
						painelRelatorio.setText(relatorio);
					}
					/**
					 * Exibindo na tabela a quantidade de vacinado por faixa etária:
					 */
					model.addRow(new Object[] { dataInicialText.getText() + " - " + dataFinalText.getText(), cont1,
							cont2, cont3, cont4, total });
					/**
					 * Exibindo em uma Label a data Inicial e final:
					 */
				data1Label.setText(dataInicialText.getText());
				data2Label.setText(dataFinalText.getText());
				/**
				 * Gerar relatório em pdf:
				 */
						if (relatorio != null) {
							int escolha = JOptionPane.showOptionDialog(null, "Deseja gerar um pdf?", "PDF",
									JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE, null, opcoes, null);
							if (escolha == JOptionPane.YES_OPTION) {
								GerarRelatorio frame = new GerarRelatorio();
								SwingUtilities.updateComponentTreeUI(frame);

								Document document = new Document();
								try {
									PdfWriter.getInstance(document, new FileOutputStream(RESULT));
								} catch (FileNotFoundException | DocumentException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
								document.open();
								try {
									document.add(new Paragraph(relatorio));

								} catch (DocumentException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
								try {
									Desktop.getDesktop().open(new File("C:\\temp\\RelatorioVacina.pdf"));
								} catch (IOException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								} finally {
									document.close();
								}
							}
						}
			         
							
				dataInicialText.setText("");
				dataFinalText.setText("");
			}
					else {
						JOptionPane.showMessageDialog(null, "Data inicial ou a data final esta incorreta!!");
						dataInicialText.setText("");
						dataFinalText.setText("");
					}
				/**
				 * Caso o usuário digite um valor invalido:
				 */
				
				}
				else {
					JOptionPane.showMessageDialog(null, "Valor inválido!!");
					dataInicialText.setText("");
					dataFinalText.setText("");
				}
			}

		});
		

		table.getColumnModel().getColumn(0).setPreferredWidth(150);
		table.getColumnModel().getColumn(1).setPreferredWidth(127);
		table.getColumnModel().getColumn(2).setPreferredWidth(135);
		table.getColumnModel().getColumn(3).setPreferredWidth(138);
		table.getColumnModel().getColumn(4).setPreferredWidth(135);
		table.getColumnModel().getColumn(5).setPreferredWidth(135);
		
		setLocationRelativeTo(null);
	}
	
	private MaskFormatter setFormatarData(String formatarData) {
		MaskFormatter mask = null;
		try {
			mask = new MaskFormatter(formatarData);
		}
		catch(Exception e) {
		}
		return mask;
	}
}
