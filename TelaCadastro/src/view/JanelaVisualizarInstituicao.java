package view;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.DefaultComboBoxModel;

import net.miginfocom.swing.MigLayout;

/**
 * Janela usada para ADICIONAR uma nova instituição ou EDITAR uma que já
 * existe (o controller decide qual dos dois casos é, usando o
 * "linhaEditando": se for -1, é cadastro novo; se for >= 0, é edição).
 *
 * IMPORTANTE (padrão MVC): essa classe só cria os componentes visuais.
 * Não tem nenhum addActionListener aqui. O Controller é quem registra
 * o que cada botão faz.
 */
public class JanelaVisualizarInstituicao extends JDialog {

	private static final long serialVersionUID = 1L;

	private JTextField txtNome;
	private JTextField txtCnpj;
	private JTextField txtEndereco;
	private JTextField txtTelefone;
	private JComboBox<String> cbTipo;

	private JButton btnSalvar;
	private JButton btnCancelar;

	// Guarda qual linha da tabela está sendo editada.
	// -1 significa que é um cadastro novo (não é edição de nada).
	private int linhaEditando = -1;

	public JanelaVisualizarInstituicao() {

		setModal(true);
		setTitle("Visualizar Instituição");
		setBounds(100, 100, 420, 300);

		getContentPane().setLayout(new MigLayout("", "[][grow]", "[][][][][][]"));

		// --- Campo Nome ---
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		add(lblNome);

		txtNome = new RoundedJTextField(1);
		add(txtNome, "growx, wrap");

		// --- Campo CNPJ ---
		JLabel lblCnpj = new JLabel("CNPJ:");
		lblCnpj.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		add(lblCnpj);

		txtCnpj = new RoundedJTextField(1);
		add(txtCnpj, "growx, wrap");

		// --- Campo Endereço ---
		JLabel lblEndereco = new JLabel("Endereço:");
		lblEndereco.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		add(lblEndereco);

		txtEndereco = new RoundedJTextField(1);
		add(txtEndereco, "growx, wrap");

		// --- Campo Telefone ---
		JLabel lblTelefone = new JLabel("Telefone:");
		lblTelefone.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		add(lblTelefone);

		txtTelefone = new RoundedJTextField(1);
		add(txtTelefone, "growx, wrap");

		// --- Campo Tipo (ComboBox) ---
		JLabel lblTipo = new JLabel("Tipo:");
		lblTipo.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		add(lblTipo);

		cbTipo = new JComboBox<String>();
		cbTipo.setFont(new Font("Times New Roman", Font.PLAIN, 11));
		cbTipo.setModel(new DefaultComboBoxModel<String>(
				new String[] { "Selecione...", "Pública Federal", "Pública Estadual", "Pública Municipal", "Privada" }));
		add(cbTipo, "growx, wrap");

		// --- Botões ---
		btnSalvar = new JButton("Salvar");
		estilizarBotao(btnSalvar);
		add(btnSalvar, "split 2, span, alignx right");

		btnCancelar = new JButton("Cancelar");
		estilizarBotao(btnCancelar);
		add(btnCancelar);
	}

	private void estilizarBotao(JButton botao) {
		botao.setBackground(Color.BLACK);
		botao.setForeground(Color.WHITE);
		botao.setFont(new Font("Tahoma", Font.BOLD, 11));
	}

	public int getLinhaEditando() {
		return linhaEditando;
	}

	public void setLinhaEditando(int linha) {
		this.linhaEditando = linha;
	}

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

	public JButton getBtnCancelar() {
		return btnCancelar;
	}
}
