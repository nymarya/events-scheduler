package manager;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.ListIterator;
import java.util.Random;
import java.util.logging.Logger;


import graph.Graph;
import graph.Vertex;
import graph.Edge;

/**
 * Classe com os métodos e atributos correspondentes a leitura de arquivos e coloração.
 *
 * @authors Jaine B. Rannow, Mayra D. Azevedo
 */
public class Engine {

	private Graph graph;
	private ArrayList< ArrayList <String> > activities; 

	private Graph graphTemp;
	private ArrayList<Vertex> vertexes;	


	private static final Logger LOGGER = Logger.getLogger( Engine.class.getName() );
	private static final String COLOR_LABEL = "color"; 

	/**
	 * Constrói objeto da classe Engine.
	 */	
	public Engine() {
		activities = new ArrayList< ArrayList<String> >();
	}



	/**
	 * Lê arquivo de entrada e separa as atividades
	 * @param path Caminho do arquivo
	 */
	public void readArchive(String path) {


		try(FileReader arq = new FileReader(path);
				BufferedReader lerArq = new BufferedReader(arq);
				) {


			String linha = lerArq.readLine(); // lê a primeira linha


			while ( linha != null ) {
				ArrayList<String> activity = new ArrayList<String>(); 

				String[] element = linha.split(" "); // separa a linha pelo espaçamento
				for( int i=0; i < element.length; i++ ){
					activity.add(element[i]); // adiciona atividade
				}
				activities.add(activity);

				linha = lerArq.readLine(); // lê a próxima linha
			}

		} catch (IOException e) {
			LOGGER.info(e.getMessage());
		}

	}

	/**
	 * Lê arquivo de entrada e separa as atividades
	 * @param index Indice do arquivo
	 */
	public void readArchive(int index) {


		File f = new File("");
		String absolutePath = f.getAbsolutePath();

		File folder = new File(absolutePath + "/src/data");
		File[] listOfFiles = folder.listFiles();

		String filename = "";
		for (int i = 0; i < index; i++) {
			filename = listOfFiles[i].getName();
		}
		this.readArchive(absolutePath+"/src/data/" + filename);


	}

	/**
	 * Cria grafo a partir de arquivo de texto com as especificações do evento:
	 * Atividades: vertices; Relação entre vertices: arestas.
	 */
	public void createGraph(  ) {

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

		Graph newGraph = new Graph();

		//preenche arrays
		for(Integer i = 0; i < numberVertex; i++) {
			origins.add(i);
			destinations.add(i.toString());
			Vertex v = new Vertex(  i.toString() );
			newGraph.addVertex(v);
		}

		long seed = System.nanoTime();
		Collections.shuffle(origins, new Random(seed));

		int e = 1;

		// cria de fato o grafo, ignorando algumas arestas
		for (Integer i : origins) {

			Vertex origin = newGraph.getVertex(i);


			for (String s : destinations) {
				int j = Integer.parseInt(s);

				//cria aresta apenas entre vertices diferentes
				if ( i != j) {

					Vertex dest = newGraph.getVertex(j);

					// garante a densidade do grafo
					if( e % 10 > count || density == 1.0) {

						Random rand = new Random();

						// cria aresta com peso randomico
						int  weight = rand.nextInt(300) + 1;
						Edge ed = new Edge(origin, dest, weight);

						origin.addAdjacent(ed);
						dest.addAdjacent(ed);

						newGraph.addEdge(ed);
					} 

					e++;

				}

			}
			destinations.remove(i.toString());

		}

		return newGraph;
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
	 * Ordena uma lista de arestas em ordem crescente de peso
	 * @param list Lista de arestas
	 */
	public static void orderByWeight(ArrayList<Edge> list) {
		Collections.sort(list, new Comparator<Edge>() {

			public int compare(Edge e1, Edge e2) {
				return e1.compareTo(e2);
			}

		});
	}


	/**
	 * Colore grafo com n cores 
	 * @param n Número cromático ou número de cores que o grafo deve ser colorido
	 */
	public void colouringKColors( int n ){

		ArrayList<Edge> edges = graph.getEdges();

		// ordena arestas do grafo pelo peso
		orderByWeight( edges );

		// colore grafo e verifica com quantas cores foi colorido
		generateColouringGraph( );


		while( n != graph.getChromaticNumber() ){

			// retira a aresta de menor peso do grafo
			graph.removeEdge( edges.get(0) );

			graph.reset();

			// colore grafo novamente
			generateColouringGraph( );


		}


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
		while( vertexes.size() >= 2){


			// escolhe vertice de maior grau
			Vertex vCurrent = vertexes.get(0);
			nonAdjIndex = 0;


			// colorir no grafo original
			int indexV = graphTemp.findVertexIndex(vCurrent);


			if( indexV != -1 ){
				Vertex v = graph.findVertexFromLabel( vCurrent.getLabel() );
				v.setColor(COLOR_LABEL+color);
			}

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

						if( !acquainted.isAdjacent(vCurrent) && acquainted != vCurrent){


							indexV = graphTemp.findVertexIndex(acquainted);

							if( indexV != -1 ){
								Vertex v = graph.findVertexFromLabel( acquainted.getLabel() );
								v.setColor(COLOR_LABEL+color);
							}

							// merge vertices
							graphTemp.mergeVertexes(vCurrent, acquainted);

							adjacent = currentEdge.getVertex(vCurrent);
						}

					}

					continue;
				}



				// se não houver mais 'conhecidos', pegar vertice de maior grau
				// que não são adjacentes a vCurrent
				while ( nonAdjIndex < vertexes.size()) {
					Vertex neighbor = vertexes.get(nonAdjIndex);


					if( !vCurrent.isAdjacent(neighbor) && neighbor != vCurrent ) {


						// se o vertice não estiver colorido, colore
						if( neighbor.getColor() == null) {

							indexV = graphTemp.getVertexes().indexOf(neighbor);

							if( indexV != -1 ){
								Vertex v = graph.findVertexFromLabel( neighbor.getLabel() );
								v.setColor(COLOR_LABEL+color);
							}

							// merge vertices
							graphTemp.mergeVertexes(vCurrent, neighbor);
						}

					}else{

						nonAdjIndex++;
					}


				} 

				continueColouring = false;


			}

			color++;
			graphTemp.removeVertex(vCurrent);
			orderByDegree(vertexes);
		}

		// Trata caso de sobrar um vertice
		if( vertexes.size() == 1 && vertexes.get(0).getColor() == null) {
			String label = vertexes.get(0).getLabel();
			Vertex v = graph.findVertexFromLabel(label);
			v.setColor(COLOR_LABEL+color);
		} else {
			color--;
		}

		// Atualiza número de cores utilizadas para colorir o grafo
		graph.setChromaticNumber(color);

	}

	/**
	 * Recupera cópia do grafo
	 * @return Grafo temporário
	 */
	public Graph getGraphTemp() {
		return this.graphTemp;
	}

	/**
	 * Atualiza grafo temporário
	 * @param graph Grafo
	 */
	public void setGraphTemp (Graph graph) {
		this.graphTemp = graph;
		vertexes = graphTemp.getVertexes();
	}

	/**
	 * Recupera grafo
	 * @return Grafo
	 */
	public Graph getGraph() {
		return this.graph;
	}

	/**
	 * Atualiza grafo
	 * @param graph Grafo
	 */
	public void setGraph (Graph graph) {
		this.graph = graph;
	}


	/**
	 * Recupera tabela com distribuição dos horários
	 * @return Tabela com distribuição
	 */
	public String getTimetable() {
		int palcos = 0;
		StringBuilder bld = new StringBuilder();

		for(int i = 1; i <= graph.getChromaticNumber(); i++) {
			bld.append("Horário ");
			bld.append(i);
			bld.append(": ");
			int palcosN = 0;
			for(Vertex v : graph.getVertexes()) {
				if(v.getColor().equals(COLOR_LABEL+i)) {
					bld.append(String.format("%1$-15s", "| "));
					bld.append(v.getLabel());
					palcosN++;
				}
			}
			
			bld.append("|\n");
			if( palcosN > palcos) {
				palcos = palcosN;
			}
		}
		
		StringBuilder timetable = new StringBuilder();
		timetable.append("           ");
		for(int i = 1; i <= palcos; i++) {
			timetable.append(String.format("%1$-15s", "| Palco "));
			timetable.append(i);
			timetable.append(" ");
		}

		timetable.append("|\n");
		timetable.append(bld);
		timetable.append("\n");
		return timetable.toString();
	}
	
	/**
	 * Monta tabela com distribuição de horários
	 */
	public void showTimetable() {

		System.out.println(this.getTimetable());

	}


}
