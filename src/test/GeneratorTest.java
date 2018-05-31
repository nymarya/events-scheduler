/**
 * @author Mayra D. de Azevedo
 * @date May 30, 2018
 */
package test;

import static org.junit.Assert.*;

import org.junit.Test;

import manager.Generator;
import graph.Graph;

/**
 * @author mayra
 *
 */
public class GeneratorTest {

	@Test
	public void test() {
		Generator gen = new Generator();
		
		Graph graph = gen.generate(7, 0.8);
		
		assertEquals(7, graph.getVertexes().size());
		int nEdges = 7 * 3; //n (n-1) /2
		assertEquals(graph.getEdges().size() , (int) (nEdges * 0.8));
		
		
		graph = gen.generate(157, 0.8);
		
		assertEquals(157, graph.getVertexes().size());
		
		nEdges = (graph.getVertexes().size() * (graph.getVertexes().size()-1 )) / 2; //n (n-1) /2
		assertTrue((int) (nEdges * 0.8) <= graph.getEdges().size() );
		
		
		
	}

}
