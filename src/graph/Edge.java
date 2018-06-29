package graph;

import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
* Classe de descrição de uma aresta do grafo
*
* @authors Jaine B. Rannow, Mayra D. Azevedo
*/

public class Edge implements Serializable {

	private Vertex origin; // vertice na "ponta de origem" da aresta 
	private Vertex destination; // vertice de destino da aresta
	private int weight; // peso da aresta
	
	private static final Logger LOGGER = Logger.getLogger( Edge.class.getName() );

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
		LOGGER.info("Vertice origem: ");
		origin.showVertex();
		LOGGER.info("Vertice destino: ");
		destination.showVertex();
		LOGGER.log(Level.INFO, "Peso: {0}", weight);
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
	
	/**
	 * Compara duas arestas por peso.
	 * @param e2 Aresta a ser comparada
	 * @return 0 se as arestas possuem mesmo peso, 1 se o peso da aresta é maior que o peso de 
	 * e2, -1 caso contrário
	 */
	public int compareTo(Edge e2) {
        if (this.getWeight() > e2.getWeight()) {
            return 1;
        }
        if (this.getWeight() < e2.getWeight()) {
            return -1;
        }
        return 0;
    }
	
	@Override
	public String toString( ){
		String s = " ( " + origin.getLabel() + ", " + destination.getLabel() + ")";
		s += " peso: " + weight;
		
		return s;
	}
	
	
}
