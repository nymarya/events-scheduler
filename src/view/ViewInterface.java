package view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


import manager.Engine;
import graph.Graph;

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
import java.util.logging.Logger;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;

import java.awt.Color;
import javax.swing.UIManager;
import java.awt.Font;

public class ViewInterface extends JFrame {

	private JPanel panel;
	private JTextField txtTestCaseIndex;
	private JTextField nSchedules;
	private JTextField textFieldGerador;
	
	private static final Logger LOGGER = Logger.getLogger( ViewInterface.class.getName() );
	private static final String BTN_COLOR = "Button.light";
	private static final String FONT = "Lato Light";
	

	/**
	 * Create the frame.
	 */
	public ViewInterface() {
		
		// configurações do panel
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		panel = new JPanel();
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(panel);
		GridBagLayout gblContentPane = new GridBagLayout();
		gblContentPane.columnWidths = new int[]{440, 0};
		gblContentPane.rowHeights = new int[]{1, 0, 0};
		gblContentPane.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gblContentPane.rowWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		panel.setLayout(gblContentPane);
		
		JPanel panel1 = new JPanel();
		panel1.setBackground(UIManager.getColor("CheckBoxMenuItem.acceleratorForeground"));
		panel1.setLayout(null);
		GridBagConstraints gbcPanel = new GridBagConstraints();
		gbcPanel.gridheight = 2;
		gbcPanel.fill = GridBagConstraints.BOTH;
		gbcPanel.gridx = 0;
		gbcPanel.gridy = 0;
		panel.add(panel1, gbcPanel);
		
		
		// recupera caminho absoluto da pasta data
		File f= new File("");
		String absolutePath = f.getAbsolutePath();
		
		File folder = new File(absolutePath + "/src/data");
		File[] listOfFiles = folder.listFiles();
		
		
		// Label para escolher arquivo de teste
		JLabel lblSelecioneUmArquivo = new JLabel("Escolha entre um número de 0 a " + listOfFiles.length);
		lblSelecioneUmArquivo.setForeground(UIManager.getColor(BTN_COLOR));
		lblSelecioneUmArquivo.setFont(new Font(FONT, Font.BOLD, 14));
		lblSelecioneUmArquivo.setBounds(101, 47, 260, 15);
		panel1.add(lblSelecioneUmArquivo);
		
		JLabel lblRand = new JLabel("0: grafo gerado randômicamente");
		lblRand.setForeground(UIManager.getColor(BTN_COLOR));
		lblRand.setFont(new Font(FONT, Font.BOLD, 14));
		lblRand.setBounds(101, 62, 260, 15);
		panel1.add(lblRand);
		
		JLabel lblCasos = new JLabel("1 a "+listOfFiles.length+": casos de testes");
		lblCasos.setForeground(UIManager.getColor(BTN_COLOR));
		lblCasos.setFont(new Font(FONT, Font.BOLD, 14));
		lblCasos.setBounds(101, 77, 260, 15);
		panel1.add(lblCasos);
		
		// Campo de texto para escolher arquivo de teste
		txtTestCaseIndex = new JTextField();
		txtTestCaseIndex.setBackground(Color.WHITE);
		txtTestCaseIndex.setBounds(80, 95, 260, 28);
		panel1.add(txtTestCaseIndex);
		txtTestCaseIndex.setColumns(10);
		
		// label para escolher quantidade de vertices desejados
		JLabel lblGerador = new JLabel("Caso 0, número de nós desejados");
		lblGerador.setForeground(UIManager.getColor(BTN_COLOR));
		lblGerador.setFont(new Font(FONT, Font.BOLD, 14));
		lblGerador.setBounds(101, 130, 280, 15);
		panel1.add(lblGerador);
		
		// campo de texto para escolher quantidade de vertices desejados
		textFieldGerador = new JTextField();
		textFieldGerador.setBackground(Color.WHITE);
		textFieldGerador.setBounds(80, 150, 260, 28);
		panel1.add(textFieldGerador);
		textFieldGerador.setColumns(10);
		
		// label para escolher quantidade de horários do evento 
		JLabel lblQuantidadeDeHorrios = new JLabel("Quantidade de horários desejados");
		lblQuantidadeDeHorrios.setFont(new Font(FONT, Font.BOLD, 14));
		lblQuantidadeDeHorrios.setForeground(UIManager.getColor(BTN_COLOR));
		lblQuantidadeDeHorrios.setBounds(108, 175, 250, 28);
		panel1.add(lblQuantidadeDeHorrios);
		
		// campo de texto para escolher quantidade de horários do evento 
		nSchedules = new JTextField();
		nSchedules.setBounds(136, 200, 174, 28);
		panel1.add(nSchedules);
		nSchedules.setColumns(10);
		
		// botão para rodar o algoritmo de coloração 
		JButton btnRodar = new JButton("RODAR");
		btnRodar.addActionListener( new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {

				String nHorarios = nSchedules.getText();
				Engine engine = new Engine( );
				String casoTesteIndex = txtTestCaseIndex.getText();
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
				
				File file = new File("");
				String absolutePath = file.getAbsolutePath();
				try (FileWriter fileWriter  = new FileWriter(absolutePath + "/results/result.txt"); ){
					
					writer = new PrintWriter(fileWriter);
					writer.println(engine.getGraph().toString());
					writer.println(engine.getTimetable());
					writer.close();
				} catch (FileNotFoundException e1) {
					LOGGER.info(e1.getMessage());
				} catch (UnsupportedEncodingException e1) {
					LOGGER.info(e1.getMessage());
				} catch (IOException e1) {
					LOGGER.info(e1.getMessage());
				}
				
				
			}
		} );
		
		btnRodar.setBounds(253, 230, 117, 25);
		panel1.add(btnRodar);
		
		
		
	}
}