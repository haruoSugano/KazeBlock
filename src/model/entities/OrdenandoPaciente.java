package model.entities;

import java.util.Comparator;

public class OrdenandoPaciente implements Comparator<Pessoa> {

	@Override
	public int compare(Pessoa o1, Pessoa o2) {
	return -o1.getNivel()+o2.getNivel();
	}

	
}
