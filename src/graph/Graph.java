package graph;

import java.util.ArrayList;

import graph.Edge;
import graph.Vertex;

//classe de representação de um grafo
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
	
	/**
	 * Recupera vertice do grafo
	 * @param index
	 */
	public Vertex getVertex( int index) {
		return vertexList.get(index);
	}
	
	/**
	 * Retorna arestas do grafo
	 * @return
	 */
	public ArrayList<Edge> getEdges( ){	
		return edgeList;
	}
	
	/**
	 * rettorna vertices do grafo
	 * @return
	 */
	public ArrayList<Vertex> getVertexes( ) {
		return vertexList;
	}
	
	
	/**
	 * Exibe todos os vértices do grafo.
	 */
	public void showVextexList( ){
		
		for( int i=0; i<vertexList.size(); i++ ){
			vertexList.get(i).showVertex();
		}
	
	}
	
	/**
	 * Exibe todas as arestas do grafo
	 */
	public void showEdgeList( ){
		
		for( int i=0; i<edgeList.size(); i++ ){
			edgeList.get(i).showEdge();
		}
	
	}
	
	public Edge containsEdge( Edge e ){
		
		for( int i=0; i<edgeList.size(); i++ ){
			Vertex vOrigin = edgeList.get(i).getOrigin();
			Vertex vDestination = edgeList.get(i).getDestination();
			
			boolean condition1 = vOrigin == e.getOrigin() && vDestination == e.getDestination();
			boolean condition2 = vOrigin == e.getDestination() && vDestination == e.getOrigin();
			
			if( condition1 || condition2 ){
				return edgeList.get(i);
			}
		}
		return null;
		
	}
	
	public Vertex containsVertex( Vertex v ){
		
		for( int i=0; i<vertexList.size(); i++ ){
			if( vertexList.get(i).getLabel().equals( v.getLabel() ) ){
				return vertexList.get(i);
			}
		}
		return null;
		
	}
	
}