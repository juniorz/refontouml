package br.ufes.inf.nemo.ontouml.transformation.rcfront2ref;

import java.util.Comparator;

public class RefOntoUMLPriorityHelper implements Comparator<RefOntoUML.PackageableElement>
{
	public int compare (RefOntoUML.PackageableElement p1, RefOntoUML.PackageableElement p2)
	{
		return getValue(p1).compareTo(getValue(p2));	
	}
	
	static RefOntoUMLPriority getValue (RefOntoUML.PackageableElement c1)
	{
		if (c1 instanceof RefOntoUML.Kind)
			return RefOntoUMLPriority.kind;
		else if (c1 instanceof RefOntoUML.Collective)
			return RefOntoUMLPriority.collective;
		else if (c1 instanceof RefOntoUML.Quantity)
			return RefOntoUMLPriority.quantity;
		else if (c1 instanceof RefOntoUML.SubKind)
			return RefOntoUMLPriority.subKind;
		else if (c1 instanceof RefOntoUML.Phase)
			return RefOntoUMLPriority.phase;
		else if (c1 instanceof RefOntoUML.Role)
			return RefOntoUMLPriority.role;
		else if (c1 instanceof RefOntoUML.Category)
			return RefOntoUMLPriority.category;
		else if (c1 instanceof RefOntoUML.Mixin)
			return RefOntoUMLPriority.mixin;
		else if (c1 instanceof RefOntoUML.RoleMixin)
			return RefOntoUMLPriority.roleMixin;
		else if (c1 instanceof RefOntoUML.Relator)
			return RefOntoUMLPriority.relator;
		else if (c1 instanceof RefOntoUML.Mode)
			return RefOntoUMLPriority.mode;
		else if (c1 instanceof RefOntoUML.Mediation)
			return RefOntoUMLPriority.mediation;
		else if (c1 instanceof RefOntoUML.Characterization)
			return RefOntoUMLPriority.characterization;
		else if (c1 instanceof RefOntoUML.Derivation)
			return RefOntoUMLPriority.derivation;
		else if (c1 instanceof RefOntoUML.componentOf)
			return RefOntoUMLPriority.componentOf;
		else if (c1 instanceof RefOntoUML.subCollectionOf)
			return RefOntoUMLPriority.subCollectionOf;
		else if (c1 instanceof RefOntoUML.memberOf)
			return RefOntoUMLPriority.memberOf;
		else if (c1 instanceof RefOntoUML.subQuantityOf)
			return RefOntoUMLPriority.subQuantityOf;		
		else if (c1 instanceof RefOntoUML.DataType)
			return RefOntoUMLPriority.dataType;
		else if (c1 instanceof RefOntoUML.GeneralizationSet)
			return RefOntoUMLPriority.generalizationSet;
		else if (c1 instanceof RefOntoUML.Association)
		{
			if (c1 instanceof RefOntoUML.MaterialAssociation)
				return RefOntoUMLPriority.material;
			else if (c1 instanceof RefOntoUML.FormalAssociation)
				return RefOntoUMLPriority.formal;
			else
				return RefOntoUMLPriority.simpleAssociation;
		}
		else if (c1 instanceof RefOntoUML.Package)
			return RefOntoUMLPriority.packages;
		else if (c1 instanceof RefOntoUML.Dependency)
			return RefOntoUMLPriority.dependency;
		
		System.err.print("unknown priority: ");
		System.out.println(c1);
		return RefOntoUMLPriority._invalid;
	}
}
