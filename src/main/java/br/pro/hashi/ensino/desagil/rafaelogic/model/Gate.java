package br.pro.hashi.ensino.desagil.rafaelogic.model;

public abstract class Gate implements Emitter, Receiver {
	private String name;

	protected Gate(String name) {
		this.name = name;
	}

	public String toString() {
		return name;
	}
	
}
