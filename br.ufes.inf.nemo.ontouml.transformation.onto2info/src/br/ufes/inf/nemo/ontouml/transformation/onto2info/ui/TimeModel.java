package br.ufes.inf.nemo.ontouml.transformation.onto2info.ui;

import java.util.LinkedList;

import br.ufes.inf.nemo.ontouml.refontouml.util.RefOntoUMLModelAbstraction;

public class TimeModel
{
	public LinkedList<RefOntoUML.Class> timeModel;
	
	public TimeModel (RefOntoUMLModelAbstraction ontoumlmodel)
	{
		// Root nodes = Substance Sortal + Relators
		timeModel = new LinkedList<RefOntoUML.Class>();
		
		for (RefOntoUML.SubstanceSortal ss : ontoumlmodel.substanceSortals)
		{
			timeModel.add(ss);
		}
		for (RefOntoUML.Relator r : ontoumlmodel.relators)
		{
			timeModel.add(r);
		}
	}
}
