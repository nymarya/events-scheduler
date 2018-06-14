package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import graph.Graph;
import manager.Engine;

import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.Color;
import javax.swing.UIManager;
import java.awt.Font;

public class View_interface extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textFieldGerador;
	
	private JTextField txtArchive;

	/**
	 * Create the frame.
	 */
	public View_interface() {
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{440, 0};
		gbl_contentPane.rowHeights = new int[]{1, 0, 0};
		gbl_contentPane.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(UIManager.getColor("CheckBoxMenuItem.acceleratorForeground"));
		panel_1.setLayout(null);
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.gridheight = 2;
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.gridx = 0;
		gbc_panel_1.gridy = 0;
		contentPane.add(panel_1, gbc_panel_1);
		
		File f= new File("");
		String absolutePath = f.getAbsolutePath();
		
		File folder = new File(absolutePath + "/src/data");
		File[] listOfFiles = folder.listFiles();
		
		JLabel lblSelecioneUmArquivo = new JLabel("Escolha entre um número de 0 a " + listOfFiles.length);
		lblSelecioneUmArquivo.setForeground(UIManager.getColor("Button.light"));
		lblSelecioneUmArquivo.setFont(new Font("Lato Light", Font.BOLD, 14));
		lblSelecioneUmArquivo.setBounds(101, 47, 260, 15);
		panel_1.add(lblSelecioneUmArquivo);
		
		JLabel lblRand = new JLabel("0: grafo gerado randômicamente");
		lblRand.setForeground(UIManager.getColor("Button.light"));
		lblRand.setFont(new Font("Lato Light", Font.BOLD, 14));
		lblRand.setBounds(101, 62, 260, 15);
		panel_1.add(lblRand);
		
		JLabel lblCasos = new JLabel("1 a 30: casos de testes");
		lblCasos.setForeground(UIManager.getColor("Button.light"));
		lblCasos.setFont(new Font("Lato Light", Font.BOLD, 14));
		lblCasos.setBounds(101, 77, 260, 15);
		panel_1.add(lblCasos);
		
		textField = new JTextField();
		textField.setBackground(Color.WHITE);
		textField.setBounds(80, 95, 260, 28);
		panel_1.add(textField);
		textField.setColumns(10);
		
		JLabel lblGerador = new JLabel("Caso 0, número de nós desejados");
		lblGerador.setForeground(UIManager.getColor("Button.light"));
		lblGerador.setFont(new Font("Lato Light", Font.BOLD, 14));
		lblGerador.setBounds(101, 130, 280, 15);
		panel_1.add(lblGerador);
		
		textFieldGerador = new JTextField();
		textFieldGerador.setBackground(Color.WHITE);
		textFieldGerador.setBounds(80, 150, 260, 28);
		panel_1.add(textFieldGerador);
		textFieldGerador.setColumns(10);
		
		
		textField_1 = new JTextField();
		textField_1.setBounds(136, 200, 174, 28);
		panel_1.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblQuantidadeDeHorrios = new JLabel("Quantidade de horários desejados");
		lblQuantidadeDeHorrios.setFont(new Font("Lato Light", Font.BOLD, 14));
		lblQuantidadeDeHorrios.setForeground(UIManager.getColor("Button.light"));
		lblQuantidadeDeHorrios.setBounds(108, 175, 250, 28);
		panel_1.add(lblQuantidadeDeHorrios);
		
		JButton btnRodar = new JButton("RODAR");
		btnRodar.addActionListener( new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {

				String nHorarios = textField_1.getText();
				Engine engine = new Engine( );
				String casoTesteIndex = textField.getText();
				int ctIndex = Integer.parseInt(casoTesteIndex);
				int nColors = Integer.parseInt(nHorarios);
				System.out.println(ctIndex);
				
				if( ctIndex !=  0) {
					engine.readArchive(ctIndex);
					System.out.println("leu");
					engine.createGraph();
					System.out.println("cirou");
					engine.colouringKColors(nColors);
					engine.showTimetable();
				} else {
					String gerador = textFieldGerador.getText();
					int g = Integer.parseInt(gerador);
					Graph graph = engine.generate(g, 1);
					engine.setGraph(graph);
					engine.colouringKColors(nColors);
					engine.showTimetable();
				}
				
			}
		} );
		
		btnRodar.setBounds(253, 230, 117, 25);
		panel_1.add(btnRodar);
		
		
		
	}
}