package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import net.miginfocom.swing.MigLayout;

/**
 * Tela de Cadastro de Instituição.
 *
 * IMPORTANTE (padrão MVC): essa classe só cria os componentes visuais
 * (campos, botões, cores, posições). Ela NÃO sabe o que acontece quando
 * um botão é clicado. Não tem nenhum addActionListener aqui dentro.
 *
 * Quem registra o que cada botão faz é o Controller, usando os métodos
 * getBtnSalvar(), getBtnLimpar() e getBtnVerInstituicoes() lá de fora.
 */
public class TelaCadastro extends JFrame {

	private static final long serialVersionUID = 1L;

	private JTextField txtNome;
	private JTextField txtCnpj;
	private JTextField txtEndereco;
	private JTextField txtTelefone;
	private JComboBox<String> cbTipo;

	private JButton btnSalvar;
	private JButton btnLimpar;
	private JButton btnVerInstituicoes;

	public TelaCadastro() {

		setTitle("Sistema Acadêmico - Cadastro de Instituição");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 550, 430);

		JPanel contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(15, 15, 15, 15));
		setContentPane(contentPane);

		// Layout principal (mantido igual ao original)
		contentPane.setLayout(new MigLayout("fillx", "[][40.00,grow,right][grow][grow]", "[38.00][][][bottom][][grow]"));

		// ===================== CABEÇALHO =====================
		JPanel cabecalho = new JPanel();
		contentPane.add(cabecalho, "cell 1 0 3 1,height 150,grow");
		cabecalho.setBackground(Color.BLACK);
		cabecalho.setLayout(new MigLayout("insets 0, align center center"));

		// Logo da aplicação ao lado do texto "JOBFY"
		ImageIcon iconeLogo = carregarIcone("/imagens/LOGO.png", 40, 40);
		if (iconeLogo != null) {
			JLabel lblLogo = new JLabel(iconeLogo);
			cabecalho.add(lblLogo, "split 2");
		}

		JLabel lblJobfy = new JLabel("JOBFY");
		lblJobfy.setForeground(Color.WHITE);
		lblJobfy.setFont(new Font("Times New Roman", Font.BOLD, 32));
		cabecalho.add(lblJobfy);

		// Título da tela
		JLabel lblTitulo = new JLabel("Cadastro de Instituição de Ensino");
		lblTitulo.setFont(new Font("Times New Roman", Font.BOLD, 18));
		contentPane.add(lblTitulo, "cell 2 1,alignx center,gapbottom 15");

		// ===================== PAINEL DE CAMPOS =====================
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		contentPane.add(panel, "cell 2 2,growx");
		panel.setLayout(new MigLayout("", "[][32.00,left][grow][28.00]", "[][][][][]"));
		panel.setBorder(new LineBorder(Color.BLACK, 1));

		// --- Campo Nome ---
		JLabel lblNome = new JLabel("Nome:");
		panel.add(lblNome, "cell 1 0");
		lblNome.setFont(new Font("Times New Roman", Font.PLAIN, 12));

		txtNome = new RoundedJTextField(1);
		panel.add(txtNome, "cell 2 0,growx");

		// --- Campo CNPJ ---
		JLabel lblCnpj = new JLabel("CNPJ:");
		panel.add(lblCnpj, "cell 1 1");
		lblCnpj.setFont(new Font("Times New Roman", Font.PLAIN, 12));

		txtCnpj = new RoundedJTextField(1);
		panel.add(txtCnpj, "cell 2 1,growx");

		// --- Campo Endereço ---
		JLabel lblEndereco = new JLabel("Endereço:");
		panel.add(lblEndereco, "cell 1 2");
		lblEndereco.setFont(new Font("Times New Roman", Font.PLAIN, 12));

		txtEndereco = new RoundedJTextField(1);
		panel.add(txtEndereco, "cell 2 2,growx");

		// --- Campo Telefone ---
		JLabel lblTelefone = new JLabel("Telefone:");
		panel.add(lblTelefone, "cell 1 3");
		lblTelefone.setFont(new Font("Times New Roman", Font.PLAIN, 12));

		txtTelefone = new RoundedJTextField(1);
		panel.add(txtTelefone, "cell 2 3,growx");

		// --- Campo Tipo (ComboBox) ---
		JLabel lblTipo = new JLabel("Tipo:");
		lblTipo.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		panel.add(lblTipo, "cell 1 4,alignx left");

		cbTipo = new JComboBox<String>();
		panel.add(cbTipo, "cell 2 4,growx");
		cbTipo.setFont(new Font("Times New Roman", Font.PLAIN, 11));
		cbTipo.setModel(new DefaultComboBoxModel<String>(
				new String[] { "Selecione...", "Pública Federal", "Pública Estadual", "Pública Municipal", "Privada" }));

		// ===================== BOTÕES =====================
		JPanel painelBotoes = new JPanel(new MigLayout("insets 0", "[100!][100!]"));
		painelBotoes.setBackground(Color.WHITE);

		btnSalvar = new JButton("Salvar");
		estilizarBotao(btnSalvar);
		painelBotoes.add(btnSalvar, "grow");

		btnLimpar = new JButton("Limpar");
		estilizarBotao(btnLimpar);
		painelBotoes.add(btnLimpar, "grow");

		contentPane.add(painelBotoes, "cell 2 3,alignx center,aligny center");

		btnVerInstituicoes = new JButton("Ver Instituições Cadastradas");
		estilizarBotao(btnVerInstituicoes);
		contentPane.add(btnVerInstituicoes, "cell 2 4,alignx center,aligny center,gapy 10");
	}

	// Deixa o botão preto com letra branca (padrão usado em todos os botões)
	private void estilizarBotao(JButton botao) {
		botao.setBackground(Color.BLACK);
		botao.setForeground(Color.WHITE);
		botao.setFont(new Font("Tahoma", Font.BOLD, 11));
	}

	/**
	 * Carrega uma imagem de dentro do projeto e a redimensiona.
	 * Se não encontrar a imagem, retorna null (e a tela continua
	 * funcionando normalmente, só sem a logo).
	 */
	private ImageIcon carregarIcone(String caminho, int largura, int altura) {
		URL url = getClass().getResource(caminho);
		if (url == null) {
			return null;
		}
		ImageIcon icone = new ImageIcon(url);
		Image imagemMenor = icone.getImage().getScaledInstance(largura, altura, Image.SCALE_SMOOTH);
		return new ImageIcon(imagemMenor);
	}

	/**
	 * Apaga o texto de todos os campos e volta o ComboBox para a opção inicial.
	 */
	public void limparCampos() {
		txtNome.setText("");
		txtCnpj.setText("");
		txtEndereco.setText("");
		txtTelefone.setText("");
		cbTipo.setSelectedIndex(0);
		txtNome.requestFocus();
	}

	// Getters dos campos: o controller usa para ler o que o usuário digitou
	public JTextField getTxtNome() {
		return txtNome;
	}

	public JTextField getTxtCnpj() {
		return txtCnpj;
	}

	public JTextField getTxtEndereco() {
		return txtEndereco;
	}

	public JTextField getTxtTelefone() {
		return txtTelefone;
	}

	public JComboBox<String> getCbTipo() {
		return cbTipo;
	}

	// Getters dos botões: o controller usa para registrar o addActionListener
	public JButton getBtnSalvar() {
		return btnSalvar;
	}

	public JButton getBtnLimpar() {
		return btnLimpar;
	}

	public JButton getBtnVerInstituicoes() {
		return btnVerInstituicoes;
	}
}
