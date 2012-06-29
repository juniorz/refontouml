package br.ufes.inf.nemo.ontouml.transformation.onto2info;

import br.ufes.inf.nemo.ontouml.refontouml.util.*;
import br.ufes.inf.nemo.ontouml.transformation.onto2info.decision.DecisionHandler;
import br.ufes.inf.nemo.ontouml.transformation.onto2info.ui.Onto2InfoInterface;
import br.ufes.inf.nemo.ontouml.transformation.onto2info.uml.UMLModelAbstraction;

public class OntoUML2InfoUML
{
	static RefOntoUMLModelAbstraction ontoAbstraction;
	static UMLModelAbstraction umlAbstraction;
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
		ontoAbstraction = new RefOntoUMLModelAbstraction();
		umlAbstraction = new UMLModelAbstraction();
		filename = fileAbsolutePath;
		
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
		
		if (umlAbstraction.load(fileAbsolutePath.replace(".refontouml", ".uml")))
		{
			// TODO: Put the loaded Map in the Ref2UMLReplicator 
			Onto2InfoMap.loadMap(ontoAbstraction.resource, umlAbstraction.resource, fileAbsolutePath.replace(".refontouml", ".map"));
			//Onto2InfoMap.printMap(Onto2InfoMap.loadMap(ontoAbstraction.resource, umlAbstraction.resource, fileAbsolutePath.replace(".refontouml", ".map")));
			// TODO: load decisions
		}
		else
		{
			Onto2InfoMap.initializeMap();
		}
				
		DecisionHandler dh = new DecisionHandler(ontoAbstraction);

		Transformation t = new Transformation(ontoAbstraction, umlAbstraction);

		new Onto2InfoInterface(ontoAbstraction, dh, t);
		
		//dh.printTimeDecisions();
		//dh.printScopeDecisions();
	}
	
	public static void saveMap ()
	{
		Onto2InfoMap.saveMap(ontoAbstraction.resource, umlAbstraction.resource, filename.replace(".refontouml", ".map"));
	}
}
