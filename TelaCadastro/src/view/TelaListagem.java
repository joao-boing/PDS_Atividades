package view;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;

import net.miginfocom.swing.MigLayout;

import model.InstituicaoTableModel;

/**
 * Tela que mostra a lista de instituições já cadastradas, em forma de
 * tabela.
 *
 * IMPORTANTE (padrão MVC): essa classe só cria os componentes visuais.
 * Não tem nenhum addActionListener aqui. O Controller é quem registra
 * o que cada botão faz, usando os getters dos botões.
 */
public class TelaListagem extends JFrame {

	private static final long serialVersionUID = 1L;

	private JTable table;
	private InstituicaoTableModel tableModel;

	private JButton btnAdicionar;
	private JButton btnRemover;
	private JButton btnVisualizar;
	private JButton btnVoltar;

	public TelaListagem(InstituicaoTableModel tableModel) {

		this.tableModel = tableModel;

		setTitle("Sistema Acadêmico - Instituições Cadastradas");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 650, 450);

		JPanel contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(15, 15, 15, 15));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("fillx", "[][grow][][]", "[][][grow][]"));

		JLabel lblTitulo = new JLabel("Instituições de Ensino Cadastradas");
		lblTitulo.setFont(new Font("Times New Roman", Font.BOLD, 18));
		contentPane.add(lblTitulo, "cell 0 0 4 1, alignx center, gapbottom 10");

		btnAdicionar = new JButton("Adicionar");
		estilizarBotao(btnAdicionar);
		contentPane.add(btnAdicionar, "cell 0 1");

		btnRemover = new JButton("Remover");
		estilizarBotao(btnRemover);
		contentPane.add(btnRemover, "cell 1 1");

		btnVisualizar = new JButton("Visualizar / Editar");
		estilizarBotao(btnVisualizar);
		contentPane.add(btnVisualizar, "cell 2 1");

		btnVoltar = new JButton("Voltar ao Cadastro");
		estilizarBotao(btnVoltar);
		contentPane.add(btnVoltar, "cell 3 1, alignx right");

		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, "cell 0 2 4 1, grow");

		table = new JTable(tableModel);
		scrollPane.setViewportView(table);
	}

	private void estilizarBotao(JButton botao) {
		botao.setBackground(Color.BLACK);
		botao.setForeground(Color.WHITE);
		botao.setFont(new Font("Tahoma", Font.BOLD, 11));
	}

	/**
	 * Avisa a tabela que a lista mudou (item adicionado, removido ou
	 * editado), para ela se redesenhar com os dados atualizados.
	 */
	public void atualizarTabela() {
		tableModel.atualizar();
	}

	public JTable getTable() {
		return table;
	}

	// Getters dos botões: o controller usa para registrar o addActionListener
	public JButton getBtnAdicionar() {
		return btnAdicionar;
	}

	public JButton getBtnRemover() {
		return btnRemover;
	}

	public JButton getBtnVisualizar() {
		return btnVisualizar;
	}

	public JButton getBtnVoltar() {
		return btnVoltar;
	}
}
