package model;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

/**
 * Essa classe ensina a JTable como mostrar a lista de instituições
 * dentro de uma tabela (quais colunas existem e o que mostrar em cada
 * linha/coluna).
 */
public class InstituicaoTableModel extends AbstractTableModel {

	private ArrayList<Instituicao> lista;

	String colunas[] = new String[] { "Nome", "CNPJ", "Telefone", "Tipo" };

	public InstituicaoTableModel(ArrayList<Instituicao> lista) {
		this.lista = lista;
	}

	@Override
	public String getColumnName(int indice) {
		return colunas[indice];
	}

	@Override
	public int getRowCount() {
		return lista.size();
	}

	@Override
	public int getColumnCount() {
		return colunas.length;
	}

	@Override
	public String getValueAt(int linha, int coluna) {
		Instituicao instituicao = lista.get(linha);

		switch (coluna) {
			case 0:
				return instituicao.getNome();
			case 1:
				return instituicao.getCnpj();
			case 2:
				return instituicao.getTelefone();
			case 3:
				return instituicao.getTipo();
		}

		return null;
	}

	/**
	 * Avisa a JTable que a lista mudou (foi adicionado, removido ou
	 * editado algum item), para ela se redesenhar na tela.
	 */
	public void atualizar() {
		fireTableDataChanged();
	}
}
