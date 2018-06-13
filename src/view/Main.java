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
			    
		View_interface frame = new View_interface();
		frame.setVisible(true);
		
		//engine.createGraph();
		//engine.colouringKColors(2);
		//engine.showTimetable();
	}
	
}
