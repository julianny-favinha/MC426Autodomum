package com.autodomum.modelo;

public enum Audio {
	PLAY(1),
	NEXT(2),
	STOP(3);

	private int id;

	Audio(int id) {
		this.id = id;
	}

	public int getId() {
        return id;
    }
}
