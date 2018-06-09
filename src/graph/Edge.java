package graph;

import java.io.Serializable;


/**
* Classe de descrição de uma aresta do grafo
*
* @authors Jaine B. Rannow, Mayra D. Azevedo
*/

public class Edge implements Serializable {

	private Vertex origin; // vertice na "ponta de origem" da aresta 
	private Vertex destination; // vertice de destino da aresta
	private int weight; // peso da aresta

	/**
	 * Constrói objeto para a classe Edge, uma aresta não-direcionada
	 * @param origin Vértice de origem
	 * @param destination Vértice de destino
	 */
	public Edge( Vertex origin, Vertex destination ){
		this.origin = origin;
		this.destination = destination;
		this.weight = 1;
	}
	
	/**
	 * Constrói objeto para a classe Edge
	 * @param origin Vértice de origem
	 * @param destination Vértice de destino
	 * @param weight Peso da aresta
	 */
	public Edge( Vertex origin, Vertex destination, int weight ) {
		this.origin = origin;
		this.destination = destination;
		this.weight = weight;
	}

	/**
	 * Aumenta o peso da aresta em 1
	 */
	public void increaseWeight( ){
		weight++;
	}
	
	/**
	 * Recupera vértice de origem
	 * @return Vértice da aresta
	 */
	public Vertex getOrigin() {
		return origin;
	}

	/**
	 * Atualiza vértice de origem
	 * @param origin Novo vértice da aresta
	 */
	public void setOrigin(Vertex origin) {
		this.origin = origin;
	}

	/**
	 * Recupera vértice de destino
	 * @return Vértice da aresta
	 */
	public Vertex getDestination() {
		return destination;
	}

	/**
	 * Atualiza vértice de destino
	 * @param destination Novo vértice da aresta
	 */
	public void setDestination(Vertex destination) {
		this.destination = destination;
	}

	/**
	 * Recupera peso da aresta
	 * @return Peso da aresta
	 */
	public int getWeight() {
		return weight;
	}

	/**
	 * Atualiza peso da aresta
	 * @param weight Novo peso da aresta
	 */
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
	
	
}
