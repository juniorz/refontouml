package br.ufes.inf.nemo.ontouml.transformation.ontouml2owl.auxiliary;

import java.util.LinkedList;
import java.util.List;

import br.ufes.inf.nemo.ontouml.transformation.ontouml2owl.tree.Node;

import RefOntoUML.Association;
import RefOntoUML.Classifier;
import RefOntoUML.Mediation;
import RefOntoUML.Property;
import RefOntoUML.Relator;
import RefOntoUML.SubstanceSortal;

public class RelatorStructure
{
	public Relator relator;
	public List<Classifier> mediatedRoles;
	public List<Mediation> mediations;
	
	public RelatorStructure (Node relatorNode)
	{
		relator = (Relator) relatorNode.getRelatedClass();
		mediatedRoles = new LinkedList<Classifier>();
		mediations = new LinkedList<Mediation>();
		
		for (Association a : relatorNode.getAssociations())
		{
			if (a instanceof Mediation)
			{				
				Mediation m = (Mediation) a;
				mediations.add(m);
				
				Classifier role = m.mediated();
				mediatedRoles.add(role);
			}
		}
	}
	
	public List<MediatedEnd> getMediatedEnds ()
	{
		List<MediatedEnd> ends = new LinkedList<MediatedEnd>();
		
		for (Mediation mediation : mediations)
		{
			Classifier role = mediation.mediated();
			Property p = mediation.mediatedEnd();
			
			MediatedEnd mediatedEnd = new MediatedEnd();
			mediatedEnd.roleName = role.getName();
			mediatedEnd.minCardinality = p.getLower();
			mediatedEnd.maxCardinality = p.getUpper();
			
			for (Classifier parent : role.parents())
			{
				if (parent instanceof SubstanceSortal)
				{
					mediatedEnd.substanceSortalName = parent.getName();
					break;
				}
			}
			
			ends.add(mediatedEnd);
		}
		
		return ends;
	}
}
