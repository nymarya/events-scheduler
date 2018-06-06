package manager;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;

import graph.Graph;
import graph.Vertex;
import graph.Edge;

public class Engine {
	
	private Graph graph;
	private ArrayList< ArrayList <String> > activities; 
	
	
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
	    	FileReader arq = new FileReader(absolutePath+"/src/data/teste2.txt");
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
				
		//graph.showVextexList();
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
					 if( e % 10 > count) {
						 
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
	


	
	private static void orderByWeight(ArrayList<Vertex> list) {
        Collections.sort(list, new Comparator<Vertex>() {

			@Override
			public int compare(Vertex v1, Vertex v2) {
                return v1.compareTo(v2);
			}
			
     });
    }
	
	public void generateColouringGraph( ){
		
		ArrayList<Vertex> vertexes = graph.getVertexes();
				
		// ordena lista de vertices pelo grau
		orderByWeight(vertexes);
		
		int color = 1;
		
		// percorre lista
		for( int i=0; i<vertexes.size(); i++ ){
			// escolhe vertice de maior grau
			Vertex vTemp = vertexes.get(i);
			
			// TESTEEEEEEEEE
			System.out.println("ITERAÇÃO "+(i+1)+": ");
			System.out.print("Vertice analisado: ");
			vTemp.showVertex();
			// FIM TESTEEEEEE
			
		
			// recupera vertices adjacentes a vTemp
			ArrayList<Edge> adjacents = vTemp.getAdjacentVertexes();
			
			// percorre lista de adjacentes a vTemp
			for( int j=0; j<adjacents.size(); j++ ){
				
				Edge eTemp = adjacents.get(j); // pega uma das arestas adjacentes	
				
				Vertex vAdj = eTemp.getVertex(vTemp); // pega o vertice dessa aresta
				
			
				System.out.print("Adjacentes: ");
				vAdj.showVertex();
				
				// recupera lista de adjacentes do vertice adjacente analisado
				ArrayList<Edge> adjAdjVertexes = vAdj.getAdjacentVertexes();
				
				// percorre lista de adjacentes do vertice adjacente analisado
				for( int k=0; k<adjAdjVertexes.size(); k++ ){
					
					
					
					Edge eAdjAdj = adjAdjVertexes.get(k); // pega uma das arestas adjacentes
					Vertex vAdjAdj = eAdjAdj.getVertex(vAdj); // pega o vertice dessa aresta
					
					int dif = 0;
					
					if( vAdjAdj != vTemp && vAdjAdj != vTemp ){
						
						
						/////////////////////////////////////////
						// ESSA PARTE AQUI VOU TENTAR ALTERAR AINDA, O CONTAINS 
						// DO ARRAYLIST N FUNCIONOU, POR ISSO FIZ ASSIM
						/////////////////////////////////////////	
						// percorre lista de adjacencia de vTemp
						for( int p=0; p<adjacents.size(); p++ ){
							
							Vertex v = adjacents.get(p).getVertex(vTemp);
							if( v == vAdjAdj ){
								break;
							} else {
								dif++;
							}
							
						}
						
						if( dif == adjacents.size() ){
							
							// MERGE - ULTIMA LINHA DO ALGORITMO
							
							/*
							vAdjAdj.setColor("color"+color);
							color++;
							System.out.print("ESSE VAAAAAI: ");
							vAdjAdj.showVertex();*/
						}
						
					}
						
	
					
					
				}
				
			}
			
		}
		
		
		
		
		
	}
	
}
