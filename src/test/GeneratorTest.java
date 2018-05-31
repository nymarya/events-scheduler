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
		
		Graph graph = gen.generate(7, 0.4);
		
		assertEquals(7, graph.getVertexes().size());
		
		System.out.println(graph.getEdges().size());
		
		graph = gen.generate(17, 0.4);
		
		assertEquals(17, graph.getVertexes().size());
		
		System.out.println(graph.getEdges().size());
	}

}
