package view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import graph.Graph;
import manager.Engine;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;

import java.awt.Color;
import javax.swing.UIManager;
import java.awt.Font;

public class View_interface extends JFrame {

	private JPanel panel;
	private JTextField txt_testCaseIndex;
	private JTextField nSchedules;
	private JTextField textFieldGerador;
	

	/**
	 * Create the frame.
	 */
	public View_interface() {
		
		// configurações do panel
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		panel = new JPanel();
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(panel);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{440, 0};
		gbl_contentPane.rowHeights = new int[]{1, 0, 0};
		gbl_contentPane.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		panel.setLayout(gbl_contentPane);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(UIManager.getColor("CheckBoxMenuItem.acceleratorForeground"));
		panel_1.setLayout(null);
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.gridheight = 2;
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 0;
		panel.add(panel_1, gbc_panel);
		
		
		// recupera caminho absoluto da pasta data
		File f= new File("");
		String absolutePath = f.getAbsolutePath();
		
		File folder = new File(absolutePath + "/src/data");
		File[] listOfFiles = folder.listFiles();
		
		
		// Label para escolher arquivo de teste
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
		
		JLabel lblCasos = new JLabel("1 a "+listOfFiles.length+": casos de testes");
		lblCasos.setForeground(UIManager.getColor("Button.light"));
		lblCasos.setFont(new Font("Lato Light", Font.BOLD, 14));
		lblCasos.setBounds(101, 77, 260, 15);
		panel_1.add(lblCasos);
		
		// Campo de texto para escolher arquivo de teste
		txt_testCaseIndex = new JTextField();
		txt_testCaseIndex.setBackground(Color.WHITE);
		txt_testCaseIndex.setBounds(80, 95, 260, 28);
		panel_1.add(txt_testCaseIndex);
		txt_testCaseIndex.setColumns(10);
		
		// label para escolher quantidade de vertices desejados
		JLabel lblGerador = new JLabel("Caso 0, número de nós desejados");
		lblGerador.setForeground(UIManager.getColor("Button.light"));
		lblGerador.setFont(new Font("Lato Light", Font.BOLD, 14));
		lblGerador.setBounds(101, 130, 280, 15);
		panel_1.add(lblGerador);
		
		// campo de texto para escolher quantidade de vertices desejados
		textFieldGerador = new JTextField();
		textFieldGerador.setBackground(Color.WHITE);
		textFieldGerador.setBounds(80, 150, 260, 28);
		panel_1.add(textFieldGerador);
		textFieldGerador.setColumns(10);
		
		// label para escolher quantidade de horários do evento 
		JLabel lblQuantidadeDeHorrios = new JLabel("Quantidade de horários desejados");
		lblQuantidadeDeHorrios.setFont(new Font("Lato Light", Font.BOLD, 14));
		lblQuantidadeDeHorrios.setForeground(UIManager.getColor("Button.light"));
		lblQuantidadeDeHorrios.setBounds(108, 175, 250, 28);
		panel_1.add(lblQuantidadeDeHorrios);
		
		// campo de texto para escolher quantidade de horários do evento 
		nSchedules = new JTextField();
		nSchedules.setBounds(136, 200, 174, 28);
		panel_1.add(nSchedules);
		nSchedules.setColumns(10);
		
		// botão para rodar o algoritmo de coloração 
		JButton btnRodar = new JButton("RODAR");
		btnRodar.addActionListener( new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {

				String nHorarios = nSchedules.getText();
				Engine engine = new Engine( );
				String casoTesteIndex = txt_testCaseIndex.getText();
				int ctIndex = Integer.parseInt(casoTesteIndex);
				int nColors = Integer.parseInt(nHorarios);
				
				if( ctIndex !=  0) {
					// lê arquivo com o índice indicado
					engine.readArchive(ctIndex);
					// cria o grafo
					engine.createGraph();
					// gera grafo com a quantidade de cores desejadas
					engine.colouringKColors(nColors);
					// mostra ao usuário uma possível distribuição dos horários
					engine.showTimetable();
				} else {
					String gerador = textFieldGerador.getText();
					int g = Integer.parseInt(gerador);
					Graph graph = engine.generate(g, 1);
					engine.setGraph(graph);
					engine.colouringKColors(nColors);
					engine.showTimetable();
				}
				
				
				// salva resultado em arquivo
				PrintWriter writer;
				try {
					FileWriter f  = new FileWriter(absolutePath + "/results/result.txt");
					writer = new PrintWriter(f);
					writer.println(engine.getGraph().toString());
					writer.println(engine.getTimetable());
					writer.close();
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				} catch (UnsupportedEncodingException e1) {
					e1.printStackTrace();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				
				
			}
		} );
		
		btnRodar.setBounds(253, 230, 117, 25);
		panel_1.add(btnRodar);
		
		
		
	}
}