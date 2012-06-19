package br.ufes.inf.nemo.ontouml.transformation.onto2info;

import br.ufes.inf.nemo.ontouml.refontouml.util.*;

public class OntoUML2InfoUML
{
	public static void main(String[] args)
	{
		if (args.length == 1)
			Transformation(args[0]);
	}
	
	public static void Transformation (String fileAbsolutePath)
	{
		RefOntoUMLModelAbstraction ma = new RefOntoUMLModelAbstraction();
		if (!ma.load(fileAbsolutePath))
			return;
		if (!ma.process())
			return;
		
		Transformation t = new Transformation();
		t.transform(ma);
	}
}
