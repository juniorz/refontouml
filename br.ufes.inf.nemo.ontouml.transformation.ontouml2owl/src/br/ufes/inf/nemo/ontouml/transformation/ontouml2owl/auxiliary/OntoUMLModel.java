package br.ufes.inf.nemo.ontouml.transformation.ontouml2owl.auxiliary;

import java.util.LinkedList;
import java.util.List;

import RefOntoUML.Category;
import RefOntoUML.Collective;
import RefOntoUML.Kind;
import RefOntoUML.Meronymic;
import RefOntoUML.Package;
import RefOntoUML.PackageableElement;
import RefOntoUML.Relator;
import RefOntoUML.Role;
import RefOntoUML.SubKind;

public class OntoUMLModel
{
	public List<Category> categories;
	public List<Kind> kinds;
	public List<Collective> collectives;
	public List<SubKind> subKinds;
	public List<Role> roles;
	public List<Relator> relators;
	public List<Meronymic> meronymics;
	
	public OntoUMLModel (Package p)
	{
		categories = new LinkedList<Category>();
		kinds = new LinkedList<Kind>();
		collectives = new LinkedList<Collective>();
		subKinds = new LinkedList<SubKind>();
		roles = new LinkedList<Role>();
		relators = new LinkedList<Relator>();
		meronymics = new LinkedList<Meronymic>();		
		
		for (PackageableElement pe : p.getPackagedElement())
		{
			if (pe instanceof Category)
				categories.add((Category) pe);
			else if (pe instanceof Kind)
				kinds.add((Kind) pe);
			else if (pe instanceof Collective)
				collectives.add((Collective) pe);
			else if (pe instanceof SubKind)
				subKinds.add((SubKind) pe);
			else if (pe instanceof Role)
				roles.add((Role) pe);
			else if (pe instanceof Relator)
				relators.add((Relator) pe);
			else if (pe instanceof Meronymic)
				meronymics.add((Meronymic) pe);
		}
	}
}
