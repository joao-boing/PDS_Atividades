package Atividade;


import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.ButtonGroup;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Academia extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField Field_Nome;
	private JTextField Field_Telefone;
	private JLabel lblNewLabel_Telefone;
	private JLabel lblNewLabel_3;
	private JRadioButton Basico_Button;
	private JRadioButton Inter_Button;
	private JRadioButton Premium_Button;
	private JLabel lblNewLabel_4;
	private JLabel lblNewLabel_5;
	private JRadioButton Mensal_Button;
	private JRadioButton Semestral_Button;
	private JRadioButton Anual_Button;
	private JRadioButton Duas_Button;
	private JRadioButton Tres_Button;
	private JRadioButton Cinco_Button;
	private JButton Calc_Button;
	private JLabel ValorFinal_lbl;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private final ButtonGroup buttonGroup_1 = new ButtonGroup();
	private final ButtonGroup buttonGroup_2 = new ButtonGroup();
	private JLabel Valor_lbl;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Academia frame = new Academia();
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
	public Academia() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[3.00,grow][70.00,grow][110.00,grow][110,grow][110,grow]", "[30.00,grow][30.00,grow][30.00,grow][30.00,grow][30.00,grow][30.00,grow][30.00,grow]"));
		
		JLabel lblNewLabel = new JLabel("CADASTRO DE CLIENTE - ACADEMIA TREINO FIT");
		contentPane.add(lblNewLabel, "cell 2 0 3 1");
		
		JLabel lblNewLabel_Nome = new JLabel("Nome");
		contentPane.add(lblNewLabel_Nome, "cell 1 1,alignx trailing");
		
		Field_Nome = new JTextField();
		contentPane.add(Field_Nome, "cell 2 1 2 1,growx");
		Field_Nome.setColumns(10);
		
		lblNewLabel_Telefone = new JLabel("Telefone");
		contentPane.add(lblNewLabel_Telefone, "cell 1 2,alignx trailing");
		
		Field_Telefone = new JTextField();
		contentPane.add(Field_Telefone, "cell 2 2 2 1,growx");
		Field_Telefone.setColumns(10);
		
		lblNewLabel_3 = new JLabel("Tipo de Plano");
		contentPane.add(lblNewLabel_3, "cell 1 3,alignx right");
		
		Basico_Button = new JRadioButton("Básico");
		buttonGroup.add(Basico_Button);
		contentPane.add(Basico_Button, "cell 2 3");
		
		Inter_Button = new JRadioButton("Intermediário");
		buttonGroup.add(Inter_Button);
		contentPane.add(Inter_Button, "cell 3 3");
		
		Premium_Button = new JRadioButton("Premium");
		buttonGroup.add(Premium_Button);
		contentPane.add(Premium_Button, "cell 4 3");
		
		lblNewLabel_4 = new JLabel("Duração");
		contentPane.add(lblNewLabel_4, "cell 1 4,alignx right");
		
		Mensal_Button = new JRadioButton("Mensal");
		buttonGroup_1.add(Mensal_Button);
		contentPane.add(Mensal_Button, "cell 2 4");
		
		Semestral_Button = new JRadioButton("Semestral");
		buttonGroup_1.add(Semestral_Button);
		contentPane.add(Semestral_Button, "cell 3 4");
		
		Anual_Button = new JRadioButton("Anual");
		buttonGroup_1.add(Anual_Button);
		contentPane.add(Anual_Button, "cell 4 4");
		
		lblNewLabel_5 = new JLabel("Frequência semanal");
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblNewLabel_5, "cell 1 5,alignx right");
		
		Duas_Button = new JRadioButton("2x por semana");
		buttonGroup_2.add(Duas_Button);
		contentPane.add(Duas_Button, "cell 2 5");
		
		Tres_Button = new JRadioButton("3x por semana");
		buttonGroup_2.add(Tres_Button);
		contentPane.add(Tres_Button, "cell 3 5");
		
		Cinco_Button = new JRadioButton("5x por semana");
		buttonGroup_2.add(Cinco_Button);
		contentPane.add(Cinco_Button, "cell 4 5");
		
		Calc_Button = new JButton("Calcular");
		Calc_Button.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				double valorInicial = 0;
				String Valor;
				
				if(Field_Nome.getText().isEmpty() || Field_Telefone.getText().isEmpty()) {
					javax.swing.JOptionPane.showMessageDialog(null, "Preencha nome e telefone");
					return;
				}
				
				if(!Basico_Button.isSelected() && !Inter_Button.isSelected() && !Premium_Button.isSelected()) {
					javax.swing.JOptionPane.showMessageDialog(null, "Selecione o tipo do Plano");
					return;
				}
				
				if(!Mensal_Button.isSelected() && !Semestral_Button.isSelected() && !Anual_Button.isSelected()) {
					javax.swing.JOptionPane.showMessageDialog(null, "Selecione o tempo do plano");
					return;
				}
				
				if(!Duas_Button.isSelected() && !Tres_Button.isSelected() && !Cinco_Button.isSelected()) {
					javax.swing.JOptionPane.showMessageDialog(null, "Selecione a frequência");
					return;
				}
				
				if(Basico_Button.isSelected()) valorInicial = 80; 
				else if (Inter_Button.isSelected()) valorInicial = 120;
				else if (Premium_Button.isSelected()) valorInicial = 180;
				
				if(Duas_Button.isSelected()) valorInicial = valorInicial; 
				else if (Tres_Button.isSelected()) valorInicial = valorInicial + ((valorInicial / 100) * 30);
				else if (Cinco_Button.isSelected()) valorInicial = valorInicial + ((valorInicial / 100) * 50);
				
				if(Mensal_Button.isSelected()) valorInicial = valorInicial;
				else if (Semestral_Button.isSelected()) valorInicial = valorInicial - ((valorInicial / 100) * 10);
				else if (Anual_Button.isSelected()) valorInicial = valorInicial - ((valorInicial / 100) * 20);
				
				Valor_lbl.setText("R$" + String.format("%.2f", valorInicial));
			}
		});
		contentPane.add(Calc_Button, "cell 2 6,alignx center,aligny center");
		
		ValorFinal_lbl = new JLabel("Valor Final:");
		contentPane.add(ValorFinal_lbl, "cell 3 6,alignx center");
		
		Valor_lbl = new JLabel("");
		contentPane.add(Valor_lbl, "cell 4 6,alignx left");

	}

}
