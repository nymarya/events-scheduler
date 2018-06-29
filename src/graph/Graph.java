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
import java.util.List;
import java.util.logging.Logger;

import graph.Edge;
import graph.Vertex;


/**
 * Classe de representação de um grafo
 *
 * @authors Jaine B. Rannow, Mayra D. Azevedo
 */
public class Graph implements Cloneable, Serializable {

	private ArrayList<Vertex> vertexList;
	private ArrayList<Edge> edgeList;
	private int chromaticNumber;

	private static final Logger LOGGER = Logger.getLogger( Graph.class.getName() );



	/**
	 * Constroi objeto da classe Graph
	 */
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
	 * Recupera vértice do grafo
	 * @param index Índice do vértice na lsta de vértices
	 */
	public Vertex getVertex( int index) {
		return vertexList.get(index);
	}

	/**
	 * Retorna arestas do grafo
	 * @return Lista de arestas do grafo
	 */
	public ArrayList<Edge> getEdges( ){	
		return edgeList;
	}

	/**
	 * Retorna vértices do grafo
	 * @return Lista de vértices do grafo
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

	/**
	 * Recupera número de cores do grafo
	 * @return Número de cores do grafo
	 */
	public int getChromaticNumber() {
		return chromaticNumber;
	}

	/**
	 * Atualiza o número de cores do grafo
	 * @param chromaticNumber O novo número de cores
	 */
	public void setChromaticNumber(int chromaticNumber) {
		this.chromaticNumber = chromaticNumber;
	}

	/**
	 * Verifica de aresta está no grafo
	 * @param e Aresta
	 * @return Aresta se aresta está no grafo, null caso contrário
	 */
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

	/**
	 * Verifica se vértice está no grafo
	 * @param v Vértice
	 * @return Vértice se vértice está no grafo, null caso contrário
	 */
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

	/**
	 * Encontra indice de vertice v na lista de vertices
	 * @param v Vertice
	 * @return Indice do vertice na lista
	 */
	public Vertex findVertexFromLabel (String label ){

		for( Vertex v : vertexList) {
			if(v.getLabel().equals(label)) {
				return v;
			}
		}

		return null;

	}




	/**
	 * Cria cópia do grafo através da serialização.
	 * @return Cópia do grafo
	 */
	@Override
	public Graph clone()
	{
		Graph object = null;
		//try-with-resources
		try( FileOutputStream fileOutputStream = new FileOutputStream("object.dat");
				ObjectOutputStream	objectOutputStream = new ObjectOutputStream(fileOutputStream);
				FileInputStream fileInputStream = new FileInputStream("object.dat");
				ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
				){


			objectOutputStream.writeObject(this);
			fileOutputStream.flush();
			objectOutputStream.flush();


			object = (Graph) objectInputStream.readObject();


		} catch (FileNotFoundException e) {
			LOGGER.info(e.getMessage());
		} catch (IOException e) {
			LOGGER.info(e.getMessage());
		} catch (ClassNotFoundException e) {
			LOGGER.info(e.getMessage());
		} 
		return object;
	}

	/**
	 * Remove vertice do grafo, retirando arestas;
	 * @param v Vértice a ser removido
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

	/**
	 * Une dois vértices
	 * @param v1       Vértice 1
	 * @param v2       Vértice 2 que será unido com v1
	 */
	public void mergeVertexes( Vertex v1, Vertex v2 ){


		ArrayList<Edge> adjacentsV1 = (ArrayList<Edge>) v1.getAdjacentVertexes();
		ArrayList<Edge> adjacentsV2 = (ArrayList<Edge>) v2.getAdjacentVertexes();


		// percorre lista de adjacencia de v2
		for (Edge adjacentV2 : adjacentsV2) {
			//recupera o vertice vizinho a v2 pela aresta atual
			Vertex vAdV2 = adjacentV2.getVertex(v2);

			//checa se vAdV2 e v1 são vizinhos
			boolean isAdjacent = false;
			for( Edge adjacentV1: adjacentsV1) {
				//recupera o vizinho de v1
				Vertex vAdV1 = adjacentV1.getVertex(v1);
				if( vAdV1 == vAdV2 ){
					isAdjacent = true;
				} 
			}

			//se o vAdV2 não for vizinho de v1, adiciona a aresta entre v1 e vAdV2
			if (!isAdjacent) {
				Edge edge = new Edge( v1, vAdV2 );
				addEdge(edge);

				// atualiza lista de adjacencia de v1
				adjacentsV1.add(edge);
				vAdV2.addAdjacent(edge);
			}

		}


		// remove da lista de vertices		
		removeVertex( v2 );

	}


	/**
	 * Remove aresta do grafo
	 * @param e Aresta a ser removida
	 */
	public void removeEdge(Edge e){

		// remove aresta da lista de adjacencia dos vertices
		for(int i = 0; i < vertexList.size() ; i++){
			Vertex vertex = vertexList.get(i);

			ArrayList<Edge> edges = vertex.getAdjacentVertexes();
			for(int j = 0; j < edges.size() ; j++){

				Edge adj = edges.get(j);

				if( adj == e ){
					int indexV = edges.indexOf(e);
					edges.remove(indexV);
				}
			}
		}


		// remove aresta da lista de arestas
		edgeList.remove( e );

	}

	/**
	 * Remove todas as cores dos vértices do grafo
	 */
	public void reset( ){

		for( int i=0; i<vertexList.size(); i++ ){
			vertexList.get(i).setColor(null);
		}

	}

	/**
	 * Retorna string que representa o grafo.
	 * @return String
	 */
	@Override
	public String toString() {

		StringBuilder bld = new StringBuilder();
		for( Edge e : edgeList) {
			bld.append(e.toString() + "\n");
		}
		
		return bld.toString();

	}


}