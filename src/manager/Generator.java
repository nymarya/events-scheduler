package manager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Random;

import graph.*;

/**
 * Classe responsável por gerar grafos aleatoriamente.
 * 
 * 
 * @author mayra
 *
 */
public class Generator {

	
	/**
	 * 
	 * @param numberVertex
	 * @param density
	 */
	public Graph generate(int numberVertex, double density) {
		
		int count = (int) (density * numberVertex);
		
		// cria vetores que definem a ordem dos vertices
		// ex: [ 2 3 1] e [ 1 3 2 ]
		ArrayList<Integer> origins = new ArrayList<Integer>(numberVertex);
		ArrayList<Integer> destinations = new ArrayList<Integer>(numberVertex);
		
		Graph graph = new Graph();
		
		//preenche arrays
		for(Integer i = 0; i < numberVertex; i++) {
			origins.add(i);
			destinations.add(i);
			Vertex v = new Vertex(  i.toString() );
			graph.addVertex(v);
		}
		
		long seed = System.nanoTime();
		Collections.shuffle(origins, new Random(seed));
		Collections.shuffle(destinations, new Random(seed));
		
		// cria de fato o grafo, ignorando algumas arestas
		for (int i : origins) {
			
			Vertex origin = graph.getVertex(i);
			
			Iterator<Integer> iter = destinations.iterator();

			while (iter.hasNext()) {
			    int j = iter.next();
			    
			    //cria aresta apenas entre vertices diferentes
				if ( i != j) {
					
					Vertex dest = graph.getVertex(j);
					
					// garante a densidade do grafo
					 if( i % count != 0) {
						 Random rand = new Random();

						 // cria aresta com peso randomico
						 int  weight = rand.nextInt(300) + 1;
						 Edge e = new Edge(origin, dest, weight);
						 Edge ed = new Edge(dest, origin, weight);
						 
						 origin.addAdjacent(e);
						 dest.addAdjacent(ed);
						 
						 graph.addEdge(e);
						 //graph.addEdge(ed);
					 }
					
					 iter.remove();
				}
			}
		}
		
		return graph;
	}
}
