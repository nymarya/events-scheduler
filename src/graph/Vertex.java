package graph;

import java.util.ArrayList;

//Classe de descrição de um vértice do grafo
public class Vertex {

	private String label; 
	private String color;
	private ArrayList<Edge> adjacent; // lista de adjacencia do vertice
	
	// construtor da classe
	public Vertex( String label ){
		this.label = label;
		this.adjacent = new ArrayList<Edge>();
		this.color = null;
	}

	/**
	 * Método para adicionar aresta que possui adjacencia com o vertice
	 * @param e Aresta a ser adicionada
	 */
	public void addAdjacent( Edge e ) {
		adjacent.add(e);
	}
	
	/**
	 * Exibe vértice
	 */
	public void showVertex( ) {
		System.out.println(label);
	}
	/**
	 * Recupera identificador do vértice
	 * @return 
	 */
	public String getLabel( ){
		return label;
	}
	
}
