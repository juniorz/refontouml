package br.ufes.inf.nemo.ontouml.transformation.onto2info.ui.content;

import java.util.LinkedList;

import br.ufes.inf.nemo.ontouml.refontouml.util.RefOntoUMLModelAbstraction;

public class ReferenceModel
{
	public LinkedList<RefOntoUML.Class> model;
	
	public ReferenceModel (RefOntoUMLModelAbstraction ontoumlmodel)
	{
		// Root nodes = Substance Sortal + Relators + Category
		model = new LinkedList<RefOntoUML.Class>();
		
		for (RefOntoUML.Category cat : ontoumlmodel.categories)
		{
			model.add(cat);
		}
		for (RefOntoUML.SubstanceSortal ss : ontoumlmodel.substanceSortals)
		{
			model.add(ss);
		}
		for (RefOntoUML.Relator r : ontoumlmodel.relators)
		{
			model.add(r);
		}
	}
}
