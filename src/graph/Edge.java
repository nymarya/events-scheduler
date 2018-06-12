package graph;

import java.io.Serializable;

//Classe de descrição de uma aresta do grafo
public class Edge implements Serializable {

	private Vertex origin; // vertice na "ponta de origem" da aresta 
	private Vertex destination; // vertice de destino da aresta
	private int weight; // peso da aresta

	
	// Construtor da classe

	public Edge( Vertex origin, Vertex destination ){
		this.origin = origin;
		this.destination = destination;
		this.weight = 1;
	}
	
	public Edge( Vertex origin, Vertex destination, int weight ) {
		this.origin = origin;
		this.destination = destination;
		this.weight = weight;
	}

	
	/**
	 * Métodos get e set
	 */
	
	public void increaseWeight( ){
		weight++;
	}
	
	public Vertex getOrigin() {
		return origin;
	}


	public void setOrigin(Vertex origin) {
		this.origin = origin;
	}


	public Vertex getDestination() {
		return destination;
	}


	public void setDestination(Vertex destination) {
		this.destination = destination;
	}


	public int getWeight() {
		return weight;
	}


	public void setWeight(int weight) {
		this.weight = weight;
	}	
	
	/**
	 * Exibe aresta
	 */
	public void showEdge( ){
		System.out.print("Vertice origem: ");
		origin.showVertex();
		System.out.print("Vertice destino: ");
		destination.showVertex();
		System.out.print("Peso: " +weight);
		System.out.println();
	}
	
	/**
	 * Retorna o vertice associado na aresta
	 * @param v Vertice 
	 * @return vertice associado a v na aresta
	 */
	public Vertex getVertex( Vertex v ){
		
		if( this.origin == v ){
			return this.destination;
		} else if ( this.destination == v ) {
			return this.origin;
		} else {
			return null;
		}
		
	}
	

	public int compareTo(Edge e2) {
        if (this.getWeight() > e2.getWeight()) {
            return 1;
        }
        if (this.getWeight() < e2.getWeight()) {
            return -1;
        }
        return 0;
    }
	

	
	
}
