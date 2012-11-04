package br.ufes.inf.nemo.ontouml.transformation.onto2info.ui.content;

import java.util.LinkedList;

import br.ufes.inf.nemo.ontouml.refontouml.util.RefOntoUMLModelAbstraction;

public class ScopeModel
{
	public LinkedList<RefOntoUML.Class> scopeModel;
	
	public ScopeModel (RefOntoUMLModelAbstraction ontoumlmodel)
	{
		// Root nodes = Substance Sortal, Mixins, Relators
		scopeModel = new LinkedList<RefOntoUML.Class>();
		
		for (RefOntoUML.SubstanceSortal ss : ontoumlmodel.substanceSortals)
		{
			scopeModel.add(ss);
		}
		for (RefOntoUML.MixinClass mc : ontoumlmodel.allMixins)
		{
			scopeModel.add(mc);
		}
		for (RefOntoUML.Relator r : ontoumlmodel.relators)
		{
			scopeModel.add(r);
		}
	}
}
