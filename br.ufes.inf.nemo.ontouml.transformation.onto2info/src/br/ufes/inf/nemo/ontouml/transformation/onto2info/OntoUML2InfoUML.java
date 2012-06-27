package br.ufes.inf.nemo.ontouml.transformation.onto2info;

import java.io.File;
import java.util.Collections;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.uml2.uml.UMLPackage;
import org.eclipse.uml2.uml.resource.UMLResource;

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
		
		DecisionHandler dh = new DecisionHandler(ma);
		Transformation t = new Transformation(fileAbsolutePath);
		new Onto2InfoInterface(ma, dh, t);
		//dh.printTimeDecisions();
	}
}
