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
	private int xCenter = 195;
	private int yCenter = 168;
	private int radius = 10;
	
	public BooleanView(Gate gate) {

		super(300, 300);

		this.gate = gate;

		aField = new JCheckBox();
		bField = new JCheckBox();


		add(aField, 10, 30, 150, 25);

		aField.addActionListener(this);
		bField.addActionListener(this);



		if (isNotGate()) {
			add(aField, 10, 120, 150, 25);
			add(bField, 10, 190, 75, 25);
			
		}
		else {
			add(aField, 10, 155, 150, 25);
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
		double distancia = Math.pow((x-xCenter), 2) + Math.pow((y-yCenter), 2);
		System.out.println(distancia);
		if ( distancia <= Math.pow((radius), 2)) {

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

		g.drawImage(image, 20, 80, 175, 175, null);

		g.setColor(color);
		
		
		

		if (result) {
			g.fillOval(xCenter -radius , yCenter-radius, radius*2, radius*2);
			repaint();

		}
		
		else {
			g.drawOval(xCenter-radius, yCenter-radius, radius*2, radius*2);
			repaint();
		}


		getToolkit().sync();
	}
}
