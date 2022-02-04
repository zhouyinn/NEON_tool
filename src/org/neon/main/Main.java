package org.neon.main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.nio.file.Path;
import java.util.ArrayList;

import org.neon.engine.XMLWriter;
import org.neon.model.Result;
import org.neon.ui.OutputPanel;


public class Main {
	public static void main(String args[]) throws Exception{
		if (args.length == 0){
			OutputPanel gui = new OutputPanel();
		}else {
			run(args[0], args[1], args[2]);
		}
	}

	static void run(String inputTextPath, String inputXMLPath, String outputXMLPath) throws Exception {
		// read input
		BufferedReader br = new BufferedReader(new FileReader(inputTextPath));
		StringBuilder sb = new StringBuilder();
		String line = br.readLine();
		while (line!=null){
			sb.append(line+"\n");
			line = br.readLine();

		}
		String textToClassify = sb.toString();

		// analyze
		File strategy = Path.of(inputXMLPath).toFile();
		org.neon.engine.Parser parser = org.neon.engine.Parser.getInstance();
		ArrayList<Result> results = parser.extract(textToClassify, strategy);
		File destination = Path.of(outputXMLPath).toFile();
		XMLWriter.addXMLSentences(destination, results);
	}

}	
	
