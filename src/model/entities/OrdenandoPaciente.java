package model.entities;

import java.util.Comparator;

public class OrdenandoPaciente implements Comparator<Pessoa> {
	/**
	 * Interface utilizado para ordenar o paciente pelo seu nivel de prioridade
	 */
	@Override
	public int compare(Pessoa o1, Pessoa o2) {
	return -o1.getNivel()+o2.getNivel();
	}

	
}
