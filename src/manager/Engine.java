package manager;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.Random;

import graph.Graph;
import graph.Vertex;
import graph.Edge;

public class Engine {
	
	private Graph graph;
	private ArrayList< ArrayList <String> > activities; 
	
	private Graph graphTemp;
	ArrayList<Vertex> vertexes;
	
	public Engine( ){
		activities = new ArrayList< ArrayList<String> >();
	}
	
	/**
	 * Lê arquivo de entrada e separa as atividades
	 */
	public void readArchive() {
		
		
		File f = new File("");
		String absolutePath = f.getAbsolutePath();
		
		
	    try {
	    	FileReader arq = new FileReader(absolutePath+"/src/data/teste4.txt");
	    	BufferedReader lerArq = new BufferedReader(arq);
	    	
	    	String linha = lerArq.readLine(); // lê a primeira linha
	    	
	    	
	    	while ( linha != null ) {
	    		ArrayList<String> activity = new ArrayList<String>(); 
	    		
	    		String element[] = linha.split(" "); // separa a linha pelo espaçamento
	    		for( int i=0; i < element.length; i++ ){
		    		activity.add(element[i]); // adiciona atividade
		    	}
		    	activities.add(activity);
	    		
	    		linha = lerArq.readLine(); // lê a próxima linha
	    	}
	    	
	    	arq.close();
	    } catch (IOException e) {
	        System.err.printf("Erro na abertura do arquivo: %s.\n",
	        e.getMessage());
	    }
	 
	    
	    // testando se deu certo
	    for( int i=0; i<activities.size(); i++ ){
	    	System.out.println(activities.get(i));
	    }
	    
	}
	
	
	/**
	 * Cria grafo a partir de arquivo de texto com as especificações do evento:
	 * Atividades: vertices; Relação entre vertices: arestas.
	 */
	public void createGraph(  ) {
				
		readArchive();
		
		graph = new Graph();
		
		
		for( int x=0; x < activities.size(); x++ ){
			
			ArrayList<String> activitiesUser = activities.get(x);

			Vertex[] vertexUser = new Vertex[activitiesUser.size()];
			
			// adiciona vertices da linha x ao grafo
			for( int i=0; i < activitiesUser.size(); i++ ){
				
				Vertex vertex = new Vertex( "v"+activitiesUser.get(i) );
				// se não contém vertice
				if( graph.containsVertex(vertex) == null ){
					graph.addVertex(vertex);
					vertexUser[i] = vertex;
				} else {
					vertexUser[i] = graph.containsVertex(vertex);
				}
				
			}
			
			// adiciona arestas da linha x ao grafo
			for( int j=0; j < activitiesUser.size(); j++ ){ // percorre todas as atividades
				for( int k=j+1; k < activitiesUser.size(); k++ ){ // percorre as atividades a partir de j
					
					Vertex v1 = vertexUser[j];
					Vertex v2 = vertexUser[k];

					Edge edge = new Edge( v1, v2 );
					
					if( graph.containsEdge(edge) == null ) {
						graph.addEdge(edge);
						v1.addAdjacent(edge);
						v2.addAdjacent(edge);
					} else {
						// aumenta o peso da aresta
						Edge e = graph.containsEdge(edge);
						e.increaseWeight();
					}
					
				}
				
			}
		}
				
		graph.showVextexList();
		//graph.showEdgeList();

				
	}
	
	/**
	 * Gera um grafo com numberVertex nós
	 * @param numberVertex Numero de nós no grafo
	 * @param density Densidade do grafo (0,1)
	 * @return Grafo gerado
	 */
	public Graph generate(int numberVertex, double density) {
		
		int count = (int) ((1 - density) * 10) ;
		
		// cria vetores que definem a ordem dos vertices
		// ex: [ 2 3 1] e [ 1 3 2 ]
		ArrayList<Integer> origins = new ArrayList<Integer>(numberVertex);
		ArrayList<String> destinations = new ArrayList<String>(numberVertex);
		
		Graph graph = new Graph();
		
		//preenche arrays
		for(Integer i = 0; i < numberVertex; i++) {
			origins.add(i);
			destinations.add(i.toString());
			Vertex v = new Vertex(  i.toString() );
			graph.addVertex(v);
		}
		
		long seed = System.nanoTime();
		Collections.shuffle(origins, new Random(seed));
		//Collections.shuffle(destinations, new Random(seed));
		int e = 1;
		
		// cria de fato o grafo, ignorando algumas arestas
		for (Integer i : origins) {
			
			Vertex origin = graph.getVertex(i);
			

			for (String s : destinations) {
				int j = Integer.parseInt(s);
			    
			    //cria aresta apenas entre vertices diferentes
				if ( i != j) {
					
					Vertex dest = graph.getVertex(j);
					
					// garante a densidade do grafo
					 if( e % 10 > count || density == 1.0) {
						 
						 Random rand = new Random();

						 // cria aresta com peso randomico
						 int  weight = rand.nextInt(300) + 1;
						 Edge ed = new Edge(origin, dest, weight);
						 
						 origin.addAdjacent(ed);
						 dest.addAdjacent(ed);
						 
						 graph.addEdge(ed);
					 } 
					 
					 e++;
					
				}
				
			}
			destinations.remove(i.toString());
			
		}
		
		return graph;
	}
	


	/**
	 * Ordena uma lista de vertices em ordem decrescente do grau
	 * @param list Lista de vertices
	 */
	public static void orderByDegree(ArrayList<Vertex> list) {
        Collections.sort(list, new Comparator<Vertex>() {

			public int compare(Vertex v1, Vertex v2) {
                return v1.compareTo(v2);
			}
			
     });
    }
	
	/**
	 * Une dois vertices
	 * @param ArrayList<Vertex> vertexes Copia da lista de vertices do grafo
	 * @param v1 Vertice 1
	 * @param v2 Vertice 2 que será unido com v1
	 */
	public void mergeVertexes( Vertex v1, Vertex v2 ){
		
		
		ArrayList<Edge> adjacentsV1 = v1.getAdjacentVertexes();
		ArrayList<Edge> adjacentsV2 = v2.getAdjacentVertexes();
		

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
				graphTemp.addEdge(edge);
				
				// atualiza lista de adjacencia de v1
				adjacentsV1.add(edge);
				vAdV2.addAdjacent(edge);
			}
			
		}

		
		// remove da lista de vertices		
		graphTemp.removeVertex( v2 );
		//graphTemp.showEdgeList();
		
	}
	
	
	/**
	 * Gera grafo colorido. Baseado no algoritmo de Burke 
	 * @link http://citeseerx.ist.psu.edu/viewdoc/download?doi=10.1.1.6.2734&rep=rep1&type=pdf
	 */
	public void generateColouringGraph( ){
		
		// clonando grafo, vertices e arestas
		graphTemp = graph.clone();
	
		vertexes = graphTemp.getVertexes();
		
		// ordena lista de vertices pelo grau
		orderByDegree(vertexes);
		
		// inicializa cor
		int color = 1;
		
		// percorre lista de vertices
		int nonAdjIndex = 0;
		int size = vertexes.size();
		while( size >= 2){
			
			// escolhe vertice de maior grau
			//Vertex vCurrent = itr.next();
			Vertex vCurrent = vertexes.get(0);
			nonAdjIndex = 0;
			System.out.println("vCurrent é " + vCurrent.getLabel());
			
			
			// colorir no grafo original
			int indexV = graphTemp.findVertexIndex(vCurrent);
			

			if( indexV != -1 ){
				Vertex v = graph.getVertex(indexV);
				v.setColor("color"+color);
			}
			
			
			System.out.print("COR " + color +" NO ");
			vCurrent.showVertex();
			
			//Enquanto existir vertice que possua vizinho comum a vCurrent
			// ou existir algum vértice que não é adjacente a vCurrent,
			// vCurrent não irá mudar
			
			//DEF de vertice 'conhecido': ambos possuem um vizinho em comum
			//não são adjacentes
			
			boolean continueColouring = true;

			ListIterator<Edge> vCurrentItr = vCurrent.getAdjacentVertexes().listIterator();
			while( continueColouring){
				vCurrentItr = vCurrent.getAdjacentVertexes().listIterator( vCurrentItr.nextIndex()  );
				if( vCurrentItr.hasNext()){
					Edge currentEdge = vCurrentItr.next();
					Vertex adjacent = currentEdge.getVertex(vCurrent);
					
				    @SuppressWarnings("unchecked")
					ArrayList<Edge> edges = (ArrayList<Edge>) adjacent.getAdjacentVertexes().clone();
					for( Edge adj : edges){
						Vertex acquainted = adj.getVertex(adjacent);
						
						//System.out.println("a " + acquainted.getLabel());
						if( !acquainted.isAdjacent(vCurrent) && acquainted != vCurrent){
							
							//System.out.println("vai colorir " + acquainted.getLabel());
							indexV = graphTemp.findVertexIndex(acquainted);
							
							if( indexV != -1 ){
								Vertex v = graph.getVertex(indexV);
								v.setColor("color"+color);
							}
							System.out.print("COR " + color +" NO ");
						    acquainted.showVertex();
							
							// merge vertices
							mergeVertexes(vCurrent, acquainted);
							size--;
							adjacent = currentEdge.getVertex(vCurrent);
						}
						
					}
					
					continue;
				}
				
				
				// se não houver mais 'conhecidos', pegar vertice de maior grau
				// que não são adjacentes a vCurrent
				while ( nonAdjIndex < size) {
					Vertex neighbor = vertexes.get(nonAdjIndex);
					
					if( !vCurrent.isAdjacent(neighbor) && neighbor != vCurrent ) {
						
						// se o vertice não estiver colorido, colore
						if( neighbor.getColor() == null) {
							indexV = graphTemp.findVertexIndex(neighbor);
							
							if( indexV != -1 ){
								Vertex v = graph.getVertex(indexV);
								v.setColor("color"+color);
							}
							System.out.print("COR " + color +" NO ");
						    neighbor.showVertex();
							
							// merge vertices
							mergeVertexes(vCurrent, neighbor);
							size--;
						}
					}else{
						nonAdjIndex++;
						continue;
					}
					
					
				} 
					
				continueColouring = false;
				
				
			}
			
			
			
			color++;
			graphTemp.removeVertex(vCurrent);
			size--;
			orderByDegree(vertexes);
		}
		
		// Trata caso de sobrar um vertice
		if( vertexes.size() == 1 && vertexes.get(0).getColor() == null) {
			vertexes.get(0).setColor("color"+color);
			System.out.print("COR " + color +" NO ");
			vertexes.get(0).showVertex();
		}
		
		graph.setChromaticNumber(color);
		
	}
	
	/**
	 * 
	 * @return
	 */
	public Graph getGraphTemp() {
		return this.graphTemp;
	}
	
	/**
	 * 
	 * @param graph
	 */
	public void setGraphTemp (Graph graph) {
		this.graphTemp = graph;
		vertexes = graphTemp.getVertexes();
	}
	
	/**
	 * 
	 * @return
	 */
	public Graph getGraph() {
		return this.graph;
	}
	
	/**
	 * 
	 * @param graph
	 */
	public void setGraph (Graph graph) {
		this.graph = graph;
	}
	
}
