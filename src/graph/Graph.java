package graph;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

import graph.Edge;
import graph.Vertex;

//classe de representação de um grafo
public class Graph implements Cloneable, Serializable {

	private ArrayList<Vertex> vertexList;
	private ArrayList<Edge> edgeList;
	
	
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

	/**
	 * Encontra indice de vertice v na lista de vertices
	 * @param v Vertice
	 * @return Indice do vertice na lista
	 */
	public int findVertexIndex( Vertex v ){
		
		return vertexList.indexOf(v);
		
	}
	
	
	
	@Override
	public Graph clone()
	{
		Graph object = null;
		try {
			FileOutputStream fileOutputStream = new FileOutputStream("object.dat");
			ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
			objectOutputStream.writeObject(this);
			fileOutputStream.flush();
			fileOutputStream.close();
			objectOutputStream.flush();
			objectOutputStream.close();
			FileInputStream fileInputStream = new FileInputStream("object.dat");
			ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
			object = (Graph) objectInputStream.readObject();
	                fileInputStream.close();
			objectInputStream.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return object;
	}
	
	/**
	 * Remove vertice do grafo, retirando arestas;
	 * @param v
	 */
	public void removeVertex(Vertex v){
		
		
		//remove vértice dos adjacentes de todos os vertices
		for(int i = 0; i < vertexList.size() ; i++){
			Vertex vertex = vertexList.get(i);
			
			Iterator<Edge> edges = vertex.getAdjacentVertexes().iterator();
			while( edges.hasNext()) {
				Edge adj = edges.next();

				if( adj.getVertex(v) != null ){
					edges.remove();
				}
			}
		}
		
		//remove da lista de arestas
		Iterator<Edge> edges = edgeList.iterator();
		while( edges.hasNext()) {
			Edge adj = edges.next();

			if( adj.getVertex(v) != null ){
				edges.remove();
			}
		}
		

		vertexList.remove( v );
	}
	
	
}