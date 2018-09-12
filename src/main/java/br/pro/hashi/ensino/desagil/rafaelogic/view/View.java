package br.pro.hashi.ensino.desagil.rafaelogic.view;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import br.pro.hashi.ensino.desagil.rafaelogic.model.Gate;


public class View extends JPanel implements ActionListener {
	private static final long serialVersionUID = 1L;


	private JComboBox<Gate> menu;
	private BooleanView booleanView;


	public View(LinkedList<Gate> gates) {
		menu = new JComboBox<>();

		for(Gate gate: gates) {
			menu.addItem(gate);
		}

		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		add(menu);

		addCalculatorView(0);

		menu.addActionListener(this);
	}


	private void addCalculatorView(int index) {
		Gate gate = menu.getItemAt(index);
		booleanView = new BooleanView(gate);
		add(booleanView);
	}


	@Override
	public void actionPerformed(ActionEvent event) {
		remove(booleanView);
		int index = menu.getSelectedIndex();
		addCalculatorView(index);

		// Linha necessária para evitar um bug gráfico.
		((JFrame) SwingUtilities.getRoot(this)).pack();
	}
}
