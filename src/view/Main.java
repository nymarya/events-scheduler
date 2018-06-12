package view;

import java.io.IOException;

import manager.Engine;

public class Main {

	public static void main( String [] args ) throws IOException {
			    
		Engine engine = new Engine();
		engine.createGraph();
		System.out.println("\n\n COLORACAO\n\n");
		//engine.generateColouringGraph();
		engine.colouringKColors(2);
	}
	
}
