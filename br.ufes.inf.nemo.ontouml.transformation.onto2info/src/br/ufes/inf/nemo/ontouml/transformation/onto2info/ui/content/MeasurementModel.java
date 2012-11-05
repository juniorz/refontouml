package br.ufes.inf.nemo.ontouml.transformation.onto2info.ui.content;

import java.util.LinkedList;

import br.ufes.inf.nemo.ontouml.refontouml.util.RefOntoUMLModelAbstraction;

public class MeasurementModel
{
	public LinkedList<RefOntoUML.Class> model;
	
	public MeasurementModel (RefOntoUMLModelAbstraction ontoumlmodel)
	{
		// Qualities
		model = new LinkedList<RefOntoUML.Class>(ontoumlmodel.qualities);
	}
}
