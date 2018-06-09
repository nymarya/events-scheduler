package graph;

import java.io.Serializable;
import java.util.ArrayList;


/**
* Classe de descrição de um vértice do grafo
*
* @authors Jaine B. Rannow, Mayra D. Azevedo
*/
public class Vertex implements Comparable<Vertex>, Cloneable, Serializable {

	private String label; 
	private String color;
	private ArrayList<Edge> adjacents; // lista de adjacencia do vertice
	
	// construtor da classe
	public Vertex( String label ){
		this.label = label;
		this.adjacents = new ArrayList<Edge>();
		this.color = null;
	}

	/**
	 * Método para adicionar aresta que possui adjacencia com o vertice
	 * @param e Aresta a ser adicionada
	 */
	public void addAdjacent( Edge e ) {
		adjacents.add(e);
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
	
	/**
	 * Recupera o grau do vertice
	 * @return grau do vertice
	 */
	public int getDegree( ){
		return adjacents.size();
	}
	
	/**
	 * Recupera lista de adjacência do vértice
	 * @return Lista de arestas adjacentes
	 */
	public ArrayList<Edge> getAdjacentVertexes(){
		return adjacents;
	}

	/**
	 * Retorna cor do vértice
	 * @return Cor do vertice
	 */
	public String getColor() {
		return color;
	}

	/**
	 * Atualiza cor do vértice
	 * @param color Nova cor do vértice
	 */
	public void setColor(String color) {
		this.color = color;
	}
	
	/**
	 * Compara graus dois vértices.
	 * @param v2 Vértice a ser comparado.
	 * @return 0 se os dois vértices tem mesmo grau, -1 se grau do vértice é
	 * maior que o de v2, 1 caso contrário.
	 */
	public int compareTo(Vertex v2) {
        if (this.getDegree() > v2.getDegree()) {
            return -1;
        }
        if (this.getDegree() < v2.getDegree()) {
            return 1;
        }
        return 0;
    }
	
	/**
	 * Verifica se dois vértices são iguais
	 * @return Verdadeiro se os dois vértices tem etiquetas iguais, falso caso
	 *  contrário.
	 */
	@Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Vertex)) {
            return false;
        }
        final Vertex other = (Vertex) obj;
        return this.getLabel().equals(other.getLabel());
    }

	/**
	 * Checa se vértices são adjacentes
	 * @param v Vértice a ser verificado
	 * @return Verdadeiro se v é adjacente ao vértice, falso caso contrário
	 */
	public boolean isAdjacent(Vertex v) {
		for( Edge edge : adjacents) {
			if( edge.getVertex( this ) == v) {
				return true;
			}
		}
		
		return false;
	}
	
	
}
