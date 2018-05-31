package graph;

import java.util.ArrayList;

//Classe de descrição de um vértice do grafo
public class Vertex {

	String label; 
	ArrayList<Edge> adjacent; // lista de adjacencia do vertice
	
	// construtor da classe
	Vertex( String label ){
		this.label = label;
		this.adjacent = new ArrayList<Edge>();
	}

	/**
	 * Método para adicionar aresta que possui adjacencia com o vertice
	 * @param e Aresta a ser adicionada
	 */
	public void addAdjacent( Edge e ) {
		adjacent.add(e);
	}
	
}
