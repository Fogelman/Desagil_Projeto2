package br.pro.hashi.ensino.desagil.rafaelogic.model;

public class NotGate extends Gate {
	private Emitter[] emitters;

	public NotGate() {
		emitters = new Emitter[1];
	}

	@Override
	public void connect(int pinIndex, Emitter emitter) {
		emitters[pinIndex] = emitter;
	}

	@Override
	public boolean read() {
		NandGate nand = new NandGate();
		nand.connect(0, emitters[0]);
		nand.connect(1, emitters[0]);
		return nand.read();
	}
}
