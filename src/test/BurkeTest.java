package test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import graph.Edge;
import graph.Graph;
import graph.Vertex;
import manager.Engine;

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
		
		
		Engine engine = new Engine();
		engine.setGraph(graph.clone());
		
		v5 = engine.getGraph().getVertex(4);
		v3 = engine.getGraph().getVertex(2);
		v7 = engine.getGraph().getVertex(6);
		v10 = engine.getGraph().getVertex(9);
		v11 = engine.getGraph().getVertex(10);
		v12 = engine.getGraph().getVertex(11);
		
		ArrayList<Edge> edges= v9.getAdjacentVertexes();
		for (Edge e : edges) {
			e.showEdge();
		}
		
		
		engine.mergeVertexes(v5, v3);
		
		assertTrue(v5.isAdjacent(v7));
		
		engine.mergeVertexes(v5, v7);
		
		assertTrue(v5.isAdjacent(v10));
		assertTrue(v5.isAdjacent(v12));
		assertTrue(v5.isAdjacent(v11));
		
	}

}
