package br.pro.hashi.ensino.desagil.rafaelogic.view;

import java.awt.Color;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JColorChooser;
import javax.swing.JLabel;
import javax.swing.JCheckBox;

import br.pro.hashi.ensino.desagil.rafaelogic.model.Gate;
import br.pro.hashi.ensino.desagil.rafaelogic.model.Source;

// Duas modificações em relação à versão da entrega anterior:
// (a) esta classe agora é subclasse de SimplePanel em vez
// de JPanel; e (b) esta classe agora implementa MouseListener,
// indicando que ela reage a eventos de interação com o mouse.
public class BooleanView extends SimplePanel implements ActionListener, MouseListener {
	private static final long serialVersionUID = 1L;

	private Gate gate;
	private JCheckBox aField;
	private JCheckBox bField;
	private JCheckBox resultField;

	// Estes nomes aqui são auto-explicativos, não?
	private Color color;

	public BooleanView(Gate gate) {

		// Como subclasse de SimplePanel, esta classe agora
		// exige que uma largura e uma altura sejam fixadas.
		super(150, 150);

		this.gate = gate;

		aField = new JCheckBox();
		bField = new JCheckBox();
		resultField = new JCheckBox();

		JLabel entradaLabel = new JLabel("Entrada:");

		JLabel resultLabel = new JLabel("Saida:");

		// Tiramos a chamada de setLayout, ou todo o trabalho feito
		// pela superclasse SimplePanel teria sido jogado no lixo!

		// Como subclasse de SimplePanel, agora podemos definir a
		// posição e o tamanho de cada componente ao adicioná-la.
		add(entradaLabel, 10, 10, 75, 25);
		add(aField, 10, 30, 150, 25);

		add(resultLabel, 10, 80, 75, 25);
		add(resultField, 10, 100, 150, 25);

		aField.addActionListener(this);
		bField.addActionListener(this);

		resultField.setEnabled(false);

		if (isNotGate()) {
			add(bField, 10, 50, 75, 25);

		}

		update();

		// Usamos isso no Projeto 1, vocês lembram?
//		String path = "/" + calculator.toString() + ".png";
//		URL url = getClass().getResource(path);
//		image = new ImageIcon(url).getImage();

		// Toda componente Swing possui este método, que
		// permite adicionar objetos que reagem a eventos
		// de mouse que acontecem nela. Ou seja, ao passar
		// this como parâmetro, estamos pedindo para a
		// componente reagir aos próprios eventos de mouse.
		addMouseListener(this);
	}

	private void update() {
		boolean a;
		boolean b;

		a = aField.isSelected();
		b = bField.isSelected();

		Source pinA = new Source();
		pinA.turn(a);
		Source pinB = new Source();
		pinB.turn(b);

		if (isNotGate()) {
			gate.connect(0, pinA);
			gate.connect(1, pinB);
		} else {
			gate.connect(0, pinA);
		}

		boolean result = gate.read();
		resultField.setSelected(result);
	}

	private boolean isNotGate() {
		String name = gate.toString();
		boolean c = name != "NOT";
		return c;
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		update();
	}

	// Conjunto de métodos que devem ser implementados por todo
	// objeto do tipo MouseListener. Estamos interessados em
	// reagir a um clique, ou seja, o ato de pressionar e soltar
	// um botão do mouse, sem sair do lugar entre os dois momentos.
	@Override
	public void mouseClicked(MouseEvent event) {

		// Descobre em qual posição o clique ocorreu.
		int x = event.getX();
		int y = event.getY();

		// Se o clique foi dentro do retângulo colorido...
		if (x >= 195 && x < 235 && y >= 80 && y < 255) {

			// ...então abrimos o seletor de cor...
			color = JColorChooser.showDialog(this, null, color);

			// ...e atualizamos a tela.
			repaint();
		}
	}

	@Override
	public void mousePressed(MouseEvent event) {
		// Não estamos interessados em reagir ao ato
		// de apenas pressionar. Deixamos este vazio.
	}

	@Override
	public void mouseReleased(MouseEvent event) {
		// Não estamos interessados em reagir ao ato
		// de apenas soltar. Deixamos este vazio.
	}

	@Override
	public void mouseEntered(MouseEvent event) {
		// Não estamos interessados em reagir ao ato
		// do cursor entrar. Deixamos este vazio.
	}

	@Override
	public void mouseExited(MouseEvent event) {
		// Não estamos interessados em reagir ao ato
		// do cursor sair. Deixamos este vazio.
	}

}
