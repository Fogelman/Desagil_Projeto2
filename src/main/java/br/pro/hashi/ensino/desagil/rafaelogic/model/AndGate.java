package br.pro.hashi.ensino.desagil.rafaelogic.model;

public class AndGate extends Gate {
	private Emitter[] emitters;
	
	public AndGate() {
		super("AND");
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
		nand1.connect(0, emitters[0]);
		nand1.connect(1, emitters[1]);
		nand2.connect(0, nand1);
		nand2.connect(1, nand1);
		return nand2.read();
	}

}
