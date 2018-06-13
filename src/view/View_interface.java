package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

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
		
		JLabel lblSelecioneUmArquivo = new JLabel("Selecione um arquivo para teste");
		lblSelecioneUmArquivo.setForeground(UIManager.getColor("Button.light"));
		lblSelecioneUmArquivo.setFont(new Font("Lato Light", Font.BOLD, 14));
		lblSelecioneUmArquivo.setBounds(101, 57, 246, 15);
		panel_1.add(lblSelecioneUmArquivo);
		
		textField = new JTextField();
		textField.setBackground(Color.WHITE);
		textField.setEditable(false);
		textField.setBounds(70, 83, 174, 28);
		panel_1.add(textField);
		textField.setColumns(10);
		
		JButton btnSelecionar = new JButton("Procurar");
		btnSelecionar.addActionListener( new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				try {	
					JFileChooser fileChooser = new JFileChooser();
					fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
					int result = fileChooser.showOpenDialog(getParent());
					
					File selectedFile = null;
					if (result == JFileChooser.APPROVE_OPTION) {
						selectedFile = fileChooser.getSelectedFile();
					}
					
					txtArchive.setText(selectedFile.getAbsolutePath());
				} catch (Exception ex) {
					// empty
				}
				
			}
		} );
		
		btnSelecionar.setBounds(253, 84, 117, 25);
		panel_1.add(btnSelecionar);
		
		textField_1 = new JTextField();
		textField_1.setBounds(136, 164, 174, 28);
		panel_1.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblQuantidadeDeHorrios = new JLabel("Quantidade de horários desejada");
		lblQuantidadeDeHorrios.setFont(new Font("Lato Light", Font.BOLD, 14));
		lblQuantidadeDeHorrios.setForeground(UIManager.getColor("Button.light"));
		lblQuantidadeDeHorrios.setBounds(108, 123, 239, 28);
		panel_1.add(lblQuantidadeDeHorrios);
		
		JButton btnRodar = new JButton("RODAR");
		btnRodar.addActionListener( new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {

				String nHorarios = textField_1.getText();
				Engine engine = new Engine( txtArchive.getText(), nHorarios );
				
			}
		} );
		
		btnRodar.setBounds(253, 214, 117, 25);
		panel_1.add(btnRodar);
		
		
		
	}
}