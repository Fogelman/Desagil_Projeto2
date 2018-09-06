package br.pro.hashi.ensino.desagil.rafaelogic.model;

public class OrGate extends Gate {
	private Emitter[] emitters;

	public OrGate() {
		emitters = new Emitter[2];
	}

	@Override
	public void connect(int pinIndex, Emitter emitter) {
		emitters[pinIndex] = emitter;
	}

	@Override
	public boolean read() {
		NandGate nand1 = new NandGate();
		NandGate nand2 = new NandGate();
		NandGate nand3 = new NandGate();
		
		nand1.connect(0, emitters[0]);
		nand1.connect(1, emitters[0]);
		nand2.connect(0, emitters[1]);
		nand2.connect(1, emitters[1]);
		nand3.connect(0, nand1);
		nand3.connect(1, nand2);
		return nand3.read();
	}
}
