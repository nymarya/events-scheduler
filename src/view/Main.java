package view;

import java.io.IOException;

import manager.Engine;

/**
* Classe principal
*
* @authors Jaine B. Rannow, Mayra D. Azevedo
*/
public class Main {

	public static void main( String [] args ) throws IOException {
			    
		Engine engine = new Engine();
		engine.createGraph();
		engine.colouringKColors(2);
		engine.showTimetable();
	}
	
}
