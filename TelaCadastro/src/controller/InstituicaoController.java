package controller;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import model.Instituicao;
import model.InstituicaoTableModel;
import view.JanelaVisualizarInstituicao;
import view.TelaCadastro;
import view.TelaListagem;

/**
 * Essa classe é o "cérebro" do sistema (Controller do padrão MVC).
 *
 * Ela guarda a lista de instituições (Model) e TAMBÉM é responsável por
 * registrar todos os ActionListener dos botões das telas (View).
 *
 * As telas (TelaCadastro, TelaListagem...) NÃO sabem o que acontece
 * quando um botão é clicado. Elas só criam os botões e o Controller,
 * de fora, registra o que cada botão deve fazer usando
 * botao.addActionListener(...).
 */
public class InstituicaoController {

	// Lista que guarda todas as instituições cadastradas (fica em memória).
	private ArrayList<Instituicao> lista;

	public InstituicaoController() {
		lista = new ArrayList<Instituicao>();
	}

	// ====================================================================
	// ABERTURA DAS TELAS (aqui é onde os ActionListener são registrados)
	// ====================================================================

	/**
	 * Cria a tela de Cadastro e registra os listeners dos botões dela.
	 * Esse é o método chamado pela classe Main para iniciar o programa.
	 */
	public void abrirTelaCadastro() {

		TelaCadastro tela = new TelaCadastro();

		// Cada botão da tela recebe aqui o que deve acontecer quando for clicado.
		tela.getBtnSalvar().addActionListener(e -> salvarCadastro(tela));
		tela.getBtnLimpar().addActionListener(e -> tela.limparCampos());
		tela.getBtnVerInstituicoes().addActionListener(e -> abrirTelaListagem(tela));

		tela.setLocationRelativeTo(null);
		tela.setVisible(true);
	}

	/**
	 * Cria a tela de Listagem (com a tabela) e registra os listeners
	 * dos botões dela.
	 */
	private void abrirTelaListagem(TelaCadastro telaAnterior) {

		InstituicaoTableModel tableModel = new InstituicaoTableModel(lista);
		TelaListagem tela = new TelaListagem(tableModel);

		tela.getBtnAdicionar().addActionListener(e -> abrirJanelaAdicionar(tela));
		tela.getBtnRemover().addActionListener(e -> removerSelecionado(tela));
		tela.getBtnVisualizar().addActionListener(e -> abrirJanelaVisualizar(tela));
		tela.getBtnVoltar().addActionListener(e -> voltarParaCadastro(tela));

		tela.setLocationRelativeTo(null);
		tela.setVisible(true);
		telaAnterior.dispose();
	}

	/**
	 * Cria a janela de Adicionar/Editar e registra os listeners dos
	 * botões dela. linhaEditando = -1 significa que é um cadastro novo.
	 */
	private JanelaVisualizarInstituicao criarJanela(TelaListagem telaListagem) {

		JanelaVisualizarInstituicao janela = new JanelaVisualizarInstituicao();

		janela.getBtnSalvar().addActionListener(e -> salvarJanela(janela, telaListagem));
		janela.getBtnCancelar().addActionListener(e -> janela.dispose());

		return janela;
	}

	// ====================================================================
	// AÇÕES DA TELA DE CADASTRO
	// ====================================================================

	/**
	 * Executado quando clica em "Salvar" na tela de cadastro.
	 * Pega o texto dos campos, valida e, se estiver tudo certo, cadastra.
	 */
	private void salvarCadastro(TelaCadastro tela) {

		String nome = tela.getTxtNome().getText();
		String cnpj = tela.getTxtCnpj().getText();
		String endereco = tela.getTxtEndereco().getText();
		String telefone = tela.getTxtTelefone().getText();
		String tipo = tela.getCbTipo().getSelectedItem().toString();

		String erro = validar(nome, cnpj, endereco, telefone, tipo);

		if (erro != null) {
			JOptionPane.showMessageDialog(tela, erro, "Erro de Validação", JOptionPane.ERROR_MESSAGE);
			return;
		}

		Instituicao nova = new Instituicao(nome, cnpj, endereco, telefone, tipo);
		lista.add(nova);

		JOptionPane.showMessageDialog(tela, "Instituição cadastrada com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
		tela.limparCampos();
	}

	/**
	 * Executado quando clica em "Voltar ao Cadastro" na tela de listagem.
	 */
	private void voltarParaCadastro(TelaListagem telaAtual) {
		abrirTelaCadastro();
		telaAtual.dispose();
	}

	// ====================================================================
	// AÇÕES DA TELA DE LISTAGEM
	// ====================================================================

	/**
	 * Executado quando clica em "Adicionar" na tela de listagem.
	 * Abre a janela de cadastro rápido, já pronta para um item novo.
	 */
	private void abrirJanelaAdicionar(TelaListagem telaListagem) {

		JanelaVisualizarInstituicao janela = criarJanela(telaListagem);
		janela.setTitle("Adicionar Instituição");
		janela.getCbTipo().setSelectedIndex(0);
		janela.setLocationRelativeTo(telaListagem);
		janela.setVisible(true);
	}

	/**
	 * Executado quando clica em "Remover" na tela de listagem.
	 */
	private void removerSelecionado(TelaListagem telaListagem) {

		int linha = telaListagem.getTable().getSelectedRow();

		if (linha == -1) {
			JOptionPane.showMessageDialog(telaListagem, "Selecione um registro!");
			return;
		}

		int confirmacao = JOptionPane.showConfirmDialog(
				telaListagem,
				"Deseja realmente remover esta instituição?",
				"Confirmar remoção",
				JOptionPane.YES_NO_OPTION);

		if (confirmacao == JOptionPane.YES_OPTION) {
			// Remove uma única vez, direto da lista.
			lista.remove(linha);
			// Avisa a tabela que os dados mudaram, para ela atualizar a tela.
			telaListagem.atualizarTabela();
		}
	}

	/**
	 * Executado quando clica em "Visualizar / Editar" na tela de listagem.
	 * Abre a janela já preenchida com os dados da instituição selecionada.
	 */
	private void abrirJanelaVisualizar(TelaListagem telaListagem) {

		int linha = telaListagem.getTable().getSelectedRow();

		if (linha == -1) {
			JOptionPane.showMessageDialog(telaListagem, "Selecione um registro!");
			return;
		}

		Instituicao instituicao = lista.get(linha);

		JanelaVisualizarInstituicao janela = criarJanela(telaListagem);
		janela.setLinhaEditando(linha);

		janela.getTxtNome().setText(instituicao.getNome());
		janela.getTxtCnpj().setText(instituicao.getCnpj());
		janela.getTxtEndereco().setText(instituicao.getEndereco());
		janela.getTxtTelefone().setText(instituicao.getTelefone());
		janela.getCbTipo().setSelectedItem(instituicao.getTipo());

		janela.setLocationRelativeTo(telaListagem);
		janela.setVisible(true);
	}

	// ====================================================================
	// AÇÕES DA JANELA DE ADICIONAR / EDITAR
	// ====================================================================

	/**
	 * Executado quando clica em "Salvar" dentro da janela de adicionar/editar.
	 *
	 * Se "linhaEditando" for -1, significa que é um cadastro novo.
	 * Se for >= 0, significa que estamos editando uma instituição existente.
	 */
	private void salvarJanela(JanelaVisualizarInstituicao janela, TelaListagem telaListagem) {

		String nome = janela.getTxtNome().getText();
		String cnpj = janela.getTxtCnpj().getText();
		String endereco = janela.getTxtEndereco().getText();
		String telefone = janela.getTxtTelefone().getText();
		String tipo = janela.getCbTipo().getSelectedItem().toString();

		String erro = validar(nome, cnpj, endereco, telefone, tipo);

		if (erro != null) {
			JOptionPane.showMessageDialog(janela, erro, "Erro de Validação", JOptionPane.ERROR_MESSAGE);
			return;
		}

		int linha = janela.getLinhaEditando();

		if (linha == -1) {
			// É um cadastro novo
			Instituicao nova = new Instituicao(nome, cnpj, endereco, telefone, tipo);
			lista.add(nova);
		} else {
			// É uma edição: atualiza os dados da instituição que já existe
			Instituicao instituicao = lista.get(linha);
			instituicao.setNome(nome);
			instituicao.setCnpj(cnpj);
			instituicao.setEndereco(endereco);
			instituicao.setTelefone(telefone);
			instituicao.setTipo(tipo);
		}

		telaListagem.atualizarTabela();
		janela.dispose();
	}

	// ====================================================================
	// VALIDAÇÃO DOS CAMPOS (usada tanto no cadastro quanto na edição)
	// ====================================================================

	/**
	 * Verifica se os dados digitados são válidos.
	 * Retorna null se estiver tudo certo, ou uma mensagem de erro se algo
	 * estiver errado.
	 *
	 * Regras:
	 * - Nome: pelo menos 5 caracteres
	 * - CNPJ: só números
	 * - Endereço: obrigatório
	 * - Telefone: só números
	 * - Tipo: precisa escolher uma opção
	 */
	private String validar(String nome, String cnpj, String endereco, String telefone, String tipo) {

		if (nome == null || nome.trim().isEmpty()) {
			return "O nome da instituição é obrigatório.";
		}
		if (nome.trim().length() < 5) {
			return "O nome da instituição deve ter pelo menos 5 caracteres.";
		}

		if (cnpj == null || cnpj.trim().isEmpty()) {
			return "O CNPJ é obrigatório.";
		}
		if (!cnpj.matches("[0-9]+")) {
			return "O CNPJ deve conter somente números.";
		}

		if (endereco == null || endereco.trim().isEmpty()) {
			return "O endereço é obrigatório.";
		}

		if (telefone == null || telefone.trim().isEmpty()) {
			return "O telefone é obrigatório.";
		}
		if (!telefone.matches("[0-9]+")) {
			return "O telefone deve conter somente números.";
		}

		if (tipo == null || tipo.equals("Selecione...")) {
			return "Selecione um tipo de instituição válido.";
		}

		// Se passou por todas as verificações, está tudo certo
		return null;
	}
}
