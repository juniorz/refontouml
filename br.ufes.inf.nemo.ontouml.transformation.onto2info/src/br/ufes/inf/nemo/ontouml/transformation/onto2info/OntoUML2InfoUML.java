package br.ufes.inf.nemo.ontouml.transformation.onto2info;

import br.ufes.inf.nemo.ontouml.refontouml.util.*;
import br.ufes.inf.nemo.ontouml.transformation.onto2info.decision.DecisionHandler;
import br.ufes.inf.nemo.ontouml.transformation.onto2info.ui.Onto2InfoInterface;
import br.ufes.inf.nemo.ontouml.transformation.onto2info.uml.UMLModelAbstraction;

public class OntoUML2InfoUML
{
	public static void main(String[] args)
	{
		if (args.length == 1)
			transformation(args[0]);
		else
			System.out.println("args[0]: fileAbsolutePath.");
	}
	
	public static void transformation (String fileAbsolutePath)
	{
		RefOntoUMLModelAbstraction ontoAbstraction = new RefOntoUMLModelAbstraction();
		UMLModelAbstraction umlAbstraction = new UMLModelAbstraction();

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
		
		umlAbstraction.load(fileAbsolutePath.replace(".refontouml", ".uml"));
		
		DecisionHandler dh = new DecisionHandler(ontoAbstraction);

		Transformation t = new Transformation(ontoAbstraction, umlAbstraction);
		
		new Onto2InfoInterface(ontoAbstraction, dh, t);
		
		//Onto2InfoMap.saveMap(ontoAbstraction.resource, umlAbstraction.resource, fileAbsolutePath.replace(".refontouml", ".map"));
		//dh.printTimeDecisions();
		//dh.printScopeDecisions();
	}
}
