/**
 * @author Mayra D. de Azevedo
 * @date May 30, 2018
 */
package test;

import static org.junit.Assert.*;

import org.junit.Test;

import graph.Graph;
import manager.Engine;

/**
* Classe de testes para a função que gera grafos
*
* @authors Jaine B. Rannow, Mayra D. Azevedo
*/
public class GeneratorTest {

	@Test
	public void test() {
		Engine gen = new Engine();
		
		Graph graph = gen.generate(7, 0.8);
		
		assertEquals(7, graph.getVertexes().size());
		int nEdges = 7 * 3; //n (n-1) /2
		assertEquals(graph.getEdges().size() , (int) (nEdges * 0.8));
		
		
		graph = gen.generate(157, 0.7);
		
		assertEquals(157, graph.getVertexes().size());
		
		nEdges = (157 * 156) / 2; //n (n-1) /2
		assertTrue((int) (nEdges * 0.5) <= graph.getEdges().size() );
		
		
		
	}

}
