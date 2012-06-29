package br.ufes.inf.nemo.ontouml.transformation.onto2info;

import br.ufes.inf.nemo.ontouml.refontouml.util.*;
import br.ufes.inf.nemo.ontouml.transformation.onto2info.decision.DecisionHandler;
import br.ufes.inf.nemo.ontouml.transformation.onto2info.ui.Onto2InfoInterface;

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
		RefOntoUMLModelAbstraction ma = new RefOntoUMLModelAbstraction();

		if (!ma.load(fileAbsolutePath))
		{
			System.out.println("Unable to load " + fileAbsolutePath);
			return;
		}

		if (!ma.process())
		{
			System.out.println("Unable to process OntoUML model");
			return;	
		}
		
		// TODO: do a class that opens the UML model (UMLModelAbstraction), if it exists
		// UMLModeLAbstraction umlA;
		
		DecisionHandler dh = new DecisionHandler(ma);
		Transformation t = new Transformation(fileAbsolutePath);
		new Onto2InfoInterface(ma, dh, t);
		
		//Onto2InfoMap.saveMap(ma.resource, umlA.resource, fileAbsolutePath.replace(".refontouml", ".map"));
		//dh.printTimeDecisions();
		//dh.printScopeDecisions();
	}
}
