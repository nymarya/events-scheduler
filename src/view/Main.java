package view;

import static org.junit.Assert.assertTrue;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import graph.*;
import manager.Engine;

public class Main {

	public static void main( String [] args ) throws IOException {
			    
		Engine engine = new Engine();
		engine.createGraph();
		System.out.println("\n\n COLORACAO\n\n");
		engine.generateColouringGraph();

	}
	
}
