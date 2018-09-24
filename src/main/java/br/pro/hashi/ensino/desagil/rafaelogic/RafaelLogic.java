package br.pro.hashi.ensino.desagil.rafaelogic;

import java.util.LinkedList;

import javax.swing.JFrame;

import br.pro.hashi.ensino.desagil.rafaelogic.model.AndGate;
import br.pro.hashi.ensino.desagil.rafaelogic.model.Gate;
import br.pro.hashi.ensino.desagil.rafaelogic.model.NandGate;
import br.pro.hashi.ensino.desagil.rafaelogic.model.NotGate;
import br.pro.hashi.ensino.desagil.rafaelogic.model.OrGate;
import br.pro.hashi.ensino.desagil.rafaelogic.model.XorGate;
import br.pro.hashi.ensino.desagil.rafaelogic.view.View;

public class RafaelLogic {

	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				LinkedList<Gate> model = new LinkedList<>();
				model.add(new AndGate());
				model.add(new NandGate());
				model.add(new NotGate());
				model.add(new OrGate());
				model.add(new XorGate());

				View view = new View(model);

				JFrame frame = new JFrame();
				frame.setContentPane(view);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setResizable(false);
				frame.pack();
				frame.setVisible(true);
			}
		});
	}

}
