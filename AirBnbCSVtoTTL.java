package project;

import com.hp.hpl.jena.rdf.model.ModelFactory;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;

import com.hp.hpl.jena.datatypes.xsd.XSDDatatype;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.Statement;
import com.hp.hpl.jena.rdf.model.StmtIterator;

import au.com.bytecode.opencsv.CSVReadProc;
import au.com.bytecode.opencsv.CSVReader;



public class AirBnbCSVtoTTL{
		
	public static void main(String[] args) throws IOException
	{
		CSVReader bfr = new CSVReader(new FileReader("C:\\Users\\suchi\\eclipse-workspace\\AirBnbProject\\src\\project\\AirBnb.csv"));
		List<String[]> rows = bfr.readAll();
		
		
		String headers[] = rows.get(0);
		rows.remove(0);
		
		
		Model data = ModelFactory.createDefaultModel();
		
		int row = 1;
		for(String[] rowArray: rows)
		{
			Resource actorResoruce = data.createResource("http://ex.org/resource/AirBnb#row-" + row++);
			for(int i = 0; i < rowArray.length; i++) {
				
				Property property = data.createProperty("http://ex.org/property/AirBnb#" +  headers[i]);
				actorResoruce.addProperty(property, rowArray[i]);
				
			}
		}
		data.write(System.out, "TURTLE");
		try {
			Writer wr = new FileWriter("C:\\Users\\suchi\\eclipse-workspace\\AirBnbProject\\src\\project\\Output.ttl");
			data.write(wr,"TURTLE");}
		catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	
	
}