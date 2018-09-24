package br.pro.hashi.ensino.desagil.rafaelogic.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.URL;

import javax.swing.JColorChooser;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;

import br.pro.hashi.ensino.desagil.rafaelogic.model.Gate;
import br.pro.hashi.ensino.desagil.rafaelogic.model.Source;

public class BooleanView extends SimplePanel implements ActionListener, MouseListener {
	private static final long serialVersionUID = 1L;

	private Gate gate;
	private JCheckBox aField;
	private JCheckBox bField;
	//private JCheckBox resultField;

	private Color color;
	private Image image;
	private boolean result;

	public BooleanView(Gate gate) {

		super(300, 300);

		this.gate = gate;

		aField = new JCheckBox();
		bField = new JCheckBox();
		//resultField = new JCheckBox();

		JLabel entradaLabel = new JLabel("Entrada:");

		//JLabel resultLabel = new JLabel("Saida:");

		add(entradaLabel, 10, 10, 75, 25);
		add(aField, 10, 30, 150, 25);
		
		//add(resultLabel, 10, 80, 75, 25);
		//add(resultField, 10, 100, 20, 20);

		aField.addActionListener(this);
		bField.addActionListener(this);

		//resultField.setEnabled(false);

		if (isNotGate()) {
			add(bField, 10, 50, 75, 25);

		}

		update();

		String path = "/" + gate.toString() + ".png";
		System.out.println(path);
		URL url = getClass().getResource(path);
		image = new ImageIcon(url).getImage();

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

		result = gate.read();
		//resultField.setSelected(result);
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

	@Override
	public void mouseClicked(MouseEvent event) {

		int x = event.getX();
		int y = event.getY();
		System.out.println(x);
		System.out.println(y);
		double distancia = Math.pow((x-220), 2) + Math.pow((y-170), 2);
		System.out.println(distancia);
		if ( distancia <= 400) {

			color = JColorChooser.showDialog(this, null, color);
			repaint();
		}
	}

	@Override
	public void mousePressed(MouseEvent event) {
	}

	@Override
	public void mouseReleased(MouseEvent event) {

	}

	@Override
	public void mouseEntered(MouseEvent event) {
	}

	@Override
	public void mouseExited(MouseEvent event) {
	}

	@Override
	public void paintComponent(Graphics g) {

		super.paintComponent(g);

		g.drawImage(image, 10, 80, 175, 175, null);

		g.setColor(color);
		
		if (result) {
			g.fillOval(200, 150, 40, 40);
			repaint();

		}
		
		else {
			g.drawOval(200, 150, 40, 40);
			repaint();
		}


		getToolkit().sync();
	}
}
