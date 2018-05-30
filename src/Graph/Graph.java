package Graph;

import java.util.ArrayList;

// classe de representação de um grafo
public class Graph {

	ArrayList<Vertex> vertexList;
	ArrayList<Edge> edgeList;
	
	
	// construtor - inicializa listas
	public Graph( ){
		vertexList = new ArrayList<Vertex>();
		edgeList = new ArrayList<Edge>();
	}
	
	/**
	 * Método para adicionar nova aresta do grafo
	 * @param e Aresta a ser adicionada
	 */
	public void addEdge( Edge e ){	
		edgeList.add(e);
	}
	
	/**
	 * Método para adicionar novo vértice do grafo
	 * @param v Vertice a ser adicionado
	 */
	public void addVertex( Vertex v ){	
		vertexList.add(v);
	}
	
}