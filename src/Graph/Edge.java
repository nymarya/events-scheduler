package Graph;


// Classe de descrição de uma aresta do grafo
public class Edge {

	private Vertex origin; // vertice na "ponta de origem" da aresta 
	private Vertex destination; // vertice de destino da aresta
	private int weight; // peso da aresta

	
	// Construtor da classe
	Edge( Vertex origin, Vertex destination, int weight ){
		this.origin = origin;
		this.destination = destination;
		this.weight = weight;
	}

	
	/**
	 * Métodos get e set
	 */
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
	
}