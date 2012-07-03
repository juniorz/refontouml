package br.ufes.inf.nemo.ontouml.transformation.onto2info;

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
	// arg[0] on debug
	static String filename;
		
	public static void main(String[] args)
	{
		if (args.length == 1)
			transformation(args[0]);
		else
			System.out.println("args[0]: fileAbsolutePath.");
	}
	
	public static void transformation (String fileAbsolutePath)
	{
		try
		{
			ontoAbstraction = new RefOntoUMLModelAbstraction();
			umlAbstraction = new UMLModelAbstraction();
			filename = fileAbsolutePath;
			
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
			
			dh = new DecisionHandler(ontoAbstraction);	
			
			// UML Model, if any
			if (umlAbstraction.load(fileAbsolutePath.replace(".refontouml", ".uml")))
			{
				// TODO: Put the loaded Map in the Ref2UMLReplicator 
				// Loads the user Decisions, the OntoUML<->UML mappings
				Serializer.loadMap(ontoAbstraction.resource, umlAbstraction.resource, fileAbsolutePath.replace(".refontouml", ".map"), dh, umlAbstraction);
			}
			else
			{
				Onto2InfoMap.initializeMap();
				umlAbstraction.createPrimitiveTypes();
			}
			
			Onto2InfoInterface ui = new Onto2InfoInterface();
			Transformation t = new Transformation(ontoAbstraction, umlAbstraction, ui);
			
			ui.load(ontoAbstraction, dh, t);
			// Program execution stops here, until the user closes the window			
			
			//dh.printTimeDecisions();
			//dh.printScopeDecisions();
		}
		catch (Exception e)
		{
			System.out.println("A terrible execution problem has happened.");
		}
	}
	
	public static void saveMap ()
	{
		//if (true) throw new RuntimeException(); // for debug
		Serializer.saveMap(ontoAbstraction.resource, umlAbstraction.resource, filename.replace(".refontouml", ".map"), dh, umlAbstraction);
	}
}
