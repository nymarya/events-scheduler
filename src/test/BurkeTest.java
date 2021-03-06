package test;

import static org.junit.Assert.*;


import org.junit.Test;

import graph.Edge;
import graph.Graph;
import graph.Vertex;
import manager.Engine;

/**
* Classe de testes para a coloração
*
* @authors Jaine B. Rannow, Mayra D. Azevedo
*/
public class BurkeTest {

	@Test
	public void test() {
		// cria grafo do artigo do burke
		Graph graph = new Graph();
		
		Vertex v1 = new Vertex("1"); 
		Vertex v2 = new Vertex("2"); 
		Vertex v3 = new Vertex("3"); 
		Vertex v4 = new Vertex("4"); 
		Vertex v5 = new Vertex("5"); 
		Vertex v6 = new Vertex("6"); 
		Vertex v7 = new Vertex("7"); 
		Vertex v8 = new Vertex("8"); 
		Vertex v9 = new Vertex("9"); 
		Vertex v10 = new Vertex("10"); 
		Vertex v11 = new Vertex("11");
		Vertex v12 = new Vertex("12");
		
		Edge e1 = new Edge(v1, v5);
		v1.addAdjacent(e1);
		v5.addAdjacent(e1);
		
		Edge e2 = new Edge(v1, v6);
		v1.addAdjacent(e2);
		v6.addAdjacent(e2);
		
		Edge e3 = new Edge(v2, v3);
		v2.addAdjacent(e3);
		v3.addAdjacent(e3);
		
		Edge e4 = new Edge(v2, v5);
		v2.addAdjacent(e4);
		v5.addAdjacent(e4);
		
		Edge e5 = new Edge(v3, v6);
		v3.addAdjacent(e5);
		v6.addAdjacent(e5);
		
		Edge e6 = new Edge(v3, v7);
		v3.addAdjacent(e6);
		v7.addAdjacent(e6);
		
		Edge e7 = new Edge(v4, v7);
		v4.addAdjacent(e7);
		v7.addAdjacent(e7);
		
		Edge e8 = new Edge(v5,  v6);
		v5.addAdjacent(e8);
		v6.addAdjacent(e8);
		
		Edge e9 = new Edge(v5, v8);
		v5.addAdjacent(e9);
		v8.addAdjacent(e9);
		
		Edge e10 = new Edge(v5,v9);
		v5.addAdjacent(e10);
		v9.addAdjacent(e10);
		
		Edge e11 = new Edge(v6, v9);
		v6.addAdjacent(e11);
		v9.addAdjacent(e11);
		
		Edge e12 = new Edge(v6,v10);
		v6.addAdjacent(e12);
		v10.addAdjacent(e12);
		
		Edge e13 = new Edge(v7, v10);
		v7.addAdjacent(e13);
		v10.addAdjacent(e13);
		
		Edge e14 = new Edge(v7, v11);
		v7.addAdjacent(e14);
		v11.addAdjacent(e14);
		
		Edge e15 = new Edge(v7, v12);
		v7.addAdjacent(e15);
		v12.addAdjacent(e15);
		
		Edge e16 = new Edge(v9, v10);
		v9.addAdjacent(e16);
		v10.addAdjacent(e16);
		
		graph.addVertex(v1);
		graph.addVertex(v2);
		graph.addVertex(v3);
		graph.addVertex(v4);
		graph.addVertex(v5);
		graph.addVertex(v6);
		graph.addVertex(v7);
		graph.addVertex(v8);
		graph.addVertex(v9);
		graph.addVertex(v10);
		graph.addVertex(v11);
		graph.addVertex(v12);
		graph.addEdge(e1);
		graph.addEdge(e2);
		graph.addEdge(e3);
		graph.addEdge(e4);
		graph.addEdge(e5);
		graph.addEdge(e6);
		graph.addEdge(e7);
		graph.addEdge(e8);
		graph.addEdge(e9);
		graph.addEdge(e10);
		graph.addEdge(e11);
		graph.addEdge(e12);
		graph.addEdge(e13);
		graph.addEdge(e14);
		graph.addEdge(e15);
		graph.addEdge(e16);
		
		
		// Testa metodo merge
		Engine engine = new Engine();
		engine.setGraphTemp(graph.clone());
		
		
		//Teste coloração
		engine.setGraph(graph);
		engine.generateColouringGraph();
		assertEquals(3, engine.getGraph().getChromaticNumber() );
		engine.showTimetable();
		
		v1 = new Vertex("1"); 
		v2 = new Vertex("2"); 
		v3 = new Vertex("3"); 
		v4 = new Vertex("4"); 
		v5 = new Vertex("5"); 
		v6 = new Vertex("6"); 
		v7 = new Vertex("7"); 
		v8 = new Vertex("8"); 
		v9 = new Vertex("9"); 
		v10 = new Vertex("10"); 
		
		
		e1 = new Edge(v1, v2);
		v1.addAdjacent(e1);
		v2.addAdjacent(e1);
		
		e2 = new Edge(v2, v3);
		v2.addAdjacent(e2);
		v3.addAdjacent(e2);
		
		e3 = new Edge(v2, v4);
		v2.addAdjacent(e3);
		v4.addAdjacent(e3);
		
		e4 = new Edge(v2, v5);
		v2.addAdjacent(e4);
		v5.addAdjacent(e4);
		
		e5 = new Edge(v2, v7);
		v2.addAdjacent(e5);
		v7.addAdjacent(e5);
		
		e6 = new Edge(v2, v9);
		v2.addAdjacent(e6);
		v9.addAdjacent(e6);
		
		e7 = new Edge(v3, v5);
		v3.addAdjacent(e7);
		v5.addAdjacent(e7);
		
		e8 = new Edge(v4, v5);
		v4.addAdjacent(e8);
		v5.addAdjacent(e8);
		
		e9 = new Edge(v5, v6);
		v5.addAdjacent(e9);
		v6.addAdjacent(e9);
		
		e10 = new Edge(v5, v7);
		v5.addAdjacent(e10);
		v7.addAdjacent(e10);
		
		e11 = new Edge(v5, v8);
		v5.addAdjacent(e11);
		v8.addAdjacent(e11);
		
		e12 = new Edge(v7, v9);
		v7.addAdjacent(e12);
		v9.addAdjacent(e12);
		
		e13 = new Edge(v7, v10);
		v7.addAdjacent(e13);
		v10.addAdjacent(e13);
		
		e14 = new Edge(v8, v9);
		v8.addAdjacent(e14);
		v9.addAdjacent(e14);
		
		e15 = new Edge(v8, v10);
		v8.addAdjacent(e15);
		v10.addAdjacent(e15);
		
		e16 = new Edge(v9, v10);
		v9.addAdjacent(e16);
		v10.addAdjacent(e16);

		//mais um testes
		graph = new Graph();
		
		graph.addVertex(v1);
		graph.addVertex(v2);
		graph.addVertex(v3);
		graph.addVertex(v4);
		graph.addVertex(v5);
		graph.addVertex(v6);
		graph.addVertex(v7);
		graph.addVertex(v8);
		graph.addVertex(v9);
		graph.addVertex(v10);
		graph.addEdge(e1);
		graph.addEdge(e2);
		graph.addEdge(e3);
		graph.addEdge(e4);
		graph.addEdge(e5);
		graph.addEdge(e6);
		graph.addEdge(e7);
		graph.addEdge(e8);
		graph.addEdge(e9);
		graph.addEdge(e10);
		graph.addEdge(e11);
		graph.addEdge(e12);
		graph.addEdge(e13);
		graph.addEdge(e14);
		graph.addEdge(e15);
		graph.addEdge(e16);
		
		
		engine.setGraph(graph);
		engine.generateColouringGraph();
		assertEquals(4, engine.getGraph().getChromaticNumber() );
		
		//mais um teste
		graph = engine.generate(6, 1);
		engine.setGraph(graph);
		engine.generateColouringGraph();
		assertEquals(6, engine.getGraph().getChromaticNumber() );
		
		graph = engine.generate(20, 1);
		engine.setGraph(graph);
		engine.generateColouringGraph();
		assertEquals(20, engine.getGraph().getChromaticNumber() );
		
		
	}

}
