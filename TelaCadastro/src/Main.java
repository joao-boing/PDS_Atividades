import java.awt.EventQueue;

import controller.InstituicaoController;

/**
 * Essa é a única classe que tem o método main do programa.
 * Ela só faz uma coisa: criar o controller e mandar ele abrir a
 * primeira tela.
 */
public class Main {

	public static void main(String[] args) {

		// EventQueue.invokeLater é usado para abrir telas Swing de forma
		// segura (é assim que toda aplicação Swing deve ser iniciada).
		EventQueue.invokeLater(new Runnable() {
			public void run() {

				InstituicaoController controller = new InstituicaoController();
				controller.abrirTelaCadastro();
			}
		});
	}
}
