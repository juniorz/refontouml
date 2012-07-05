package br.ufes.inf.nemo.ontouml.transformation.onto2info;

import java.io.File;
import java.io.IOException;

import br.ufes.inf.nemo.ontouml.refontouml.util.*;
import br.ufes.inf.nemo.ontouml.transformation.onto2info.decision.DecisionHandler;
import br.ufes.inf.nemo.ontouml.transformation.onto2info.ui.Onto2InfoInterface;
import br.ufes.inf.nemo.ontouml.transformation.onto2info.uml.UMLModelAbstraction;

public class OntoUML2InfoUML
{
	// OntoUML Model wrapper
	static RefOntoUMLModelAbstraction ontoAbstraction;
	// UML Model wrapper
	static UMLModelAbstraction umlAbstraction;
	// Decisions made by the user
	static DecisionHandler dh;
	// User Interface
	static Onto2InfoInterface ui;
	
	// ontouml file (arg[0] on debug)
	static String ontofilename;
	// uml file
	static String umlfilename;
	// map file
	static String mapfilename;

	static // preloaded the UML Model, the Onto<->UML mappings and the user decisions
	boolean preloaded = false;
	
	public static void main(String[] args)
	{
		if (args.length == 1)
			transformation(args[0]);
		else
			System.out.println("args[0]: fileAbsolutePath.");
	}
	
	public static void transformation (String fileAbsolutePath)
	{
		ontofilename = fileAbsolutePath;
		umlfilename = fileAbsolutePath.replace(".refontouml", ".uml");
		mapfilename = fileAbsolutePath.replace(".refontouml", ".map");

		ontoAbstraction = new RefOntoUMLModelAbstraction();
		umlAbstraction = new UMLModelAbstraction();
		ui = new Onto2InfoInterface();
		
		try
		{
			// OntoUML Model
			if (!ontoAbstraction.load(fileAbsolutePath))
			{
				System.out.println("Unable to load " + fileAbsolutePath);
				return;
			}
	
			if (!ontoAbstraction.process())
			{
				System.out.println("Unable to process OntoUML model");
				return;	
			}
			
			dh = new DecisionHandler(ontoAbstraction); // TODO: initialize decisions only if they were not pre-loaded?
			
			// UML Model, if any
			if (umlAbstraction.load(umlfilename))
			{
				// TODO: in case of Exception here, delete the Map and the UML model (this.exception())
				// Loads the user Decisions, the OntoUML<->UML mappings
				Serializer.loadMap(ontoAbstraction.resource, umlAbstraction.resource, mapfilename, dh, umlAbstraction);
				preloaded = true;
			}
			else
			{
				Onto2InfoMap.initializeMap();
				umlAbstraction.createPrimitiveTypes();
				preloaded = false;
			}
			
			Transformation t = new Transformation(ontoAbstraction, umlAbstraction, ui);
			
			ui.load(ontoAbstraction, dh, t);
			// Program execution stops here, until the user closes the window			
		}
		catch (Exception e)
		{
			System.out.println("A terrible execution problem has happened.");
		}
	}
	
	public static void initialCallback ()
	{
		if (preloaded)
		{
			ui.writeText("Loaded the UML Model (" + umlfilename + ")");
			ui.writeText("Loaded the OntoUML<->UML correspondences and informational decisions (" + mapfilename + ")");
		}
	}
	
	public static void saveMap () throws IOException
	{
		//if (true) throw new RuntimeException(); // for debug
		Serializer.saveMap(ontoAbstraction.resource, umlAbstraction.resource, ontofilename.replace(".refontouml", ".map"), dh, umlAbstraction);
	}
	
	public static void exception()
	{
		// TODO: Dialog asking for permission?
		File f = new File(umlfilename);
		if (f.delete())
			ui.writeText("File " + umlfilename + " was deleted");

		f = new File (mapfilename);
		if (f.delete())
			ui.writeText("File " + mapfilename + " was deleted");
		
		// FIXME: reinitialize some variables
	}
}
