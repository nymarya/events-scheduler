package view;

import manager.Engine;

/**
* Classe principal
*
* @authors Jaine B. Rannow, Mayra D. Azevedo
*/
public class Main {

	public static void main( String [] args )  {
			    
		Engine engine = new Engine();
		engine.createGraph();
		engine.generateColouringGraph();

	}
	
}
