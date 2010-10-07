// Roberto Carraretto 2010
package br.ufes.inf.nemo.ontouml.transformation.rcfront2ref;

import org.eclipse.emf.common.util.ECollections;
import org.eclipse.emf.ecore.EObject;

public class Transformation
{
	Dealer mydealer;
	
	// Constructor
	public Transformation()
	{
		mydealer = new Dealer();
	}
		
	public RefOntoUML.Package DealPackage (EObject c1)
	{	
		if (!(c1 instanceof org.eclipse.uml2.uml.Package))
			return null;
		
		org.eclipse.uml2.uml.Package p1 = (org.eclipse.uml2.uml.Package) c1;
		RefOntoUML.Package p2 = RefOntoUML.RefOntoUMLFactory.eINSTANCE.createPackage();
		
		System.out.print("<package> ");
		mydealer.DealNamedElement(p1, p2);
		mydealer.RelateElements(p1, p2);
		
		// ElementImports
		/*for (org.eclipse.uml2.uml.ElementImport ei : p1.getElementImports())
		{
			DealElementImport (ei);
		}*/
		
		// PackageImport, PackageMerge, ownedRule...
						
		// Classes
		for (EObject obj : p1.getPackagedElements())
		{
			if (obj instanceof org.eclipse.uml2.uml.Class)
			{
				RefOntoUML.Class c2 = mydealer.DealClassStereotype ((org.eclipse.uml2.uml.Class) obj);
				p2.getPackagedElement().add(c2);
			}
		}
		
		// DataTypes
		for (EObject obj : p1.getPackagedElements())
		{
			if (obj instanceof org.eclipse.uml2.uml.DataType)
			{
				RefOntoUML.DataType dt2 = mydealer.DealDataType ((org.eclipse.uml2.uml.DataType) obj);
				p2.getPackagedElement().add(dt2);				
			}
		}
		
		// Associations
		for (EObject obj : p1.getPackagedElements())
		{
			if (obj instanceof org.eclipse.uml2.uml.Association)
			{
				RefOntoUML.Association a2 = mydealer.DealAssociationStereotype ((org.eclipse.uml2.uml.Association) obj);
				p2.getPackagedElement().add(a2);
			}
		}
		
		// (Process) Generalizations
		for (EObject obj : p1.getPackagedElements())
		{
			if (obj instanceof org.eclipse.uml2.uml.Classifier)
			{
				mydealer.ProcessGeneralizations((org.eclipse.uml2.uml.Classifier)obj);
			}
		}

		// Generalization Sets
		for (EObject obj : p1.getPackagedElements())
		{
			if (obj instanceof org.eclipse.uml2.uml.GeneralizationSet)
			{
				RefOntoUML.GeneralizationSet gs2 = mydealer.DealGeneralizationSet ((org.eclipse.uml2.uml.GeneralizationSet) obj);
				p2.getPackagedElement().add(gs2);
			}
		}
		
		// Dependency
		for (EObject obj : p1.getPackagedElements())
		{
			if (obj instanceof org.eclipse.uml2.uml.Dependency)
			{
				RefOntoUML.Dependency d2 = mydealer.DealDependency ((org.eclipse.uml2.uml.Dependency) obj);
				p2.getPackagedElement().add(d2);
			}
		}
		
		// Non Classifiers, Non GeneralizationSet, Non Dependency
		for (EObject obj : p1.getPackagedElements())
		{
			if (!(obj instanceof org.eclipse.uml2.uml.Classifier) &&
				!(obj instanceof org.eclipse.uml2.uml.GeneralizationSet) &&
				!(obj instanceof org.eclipse.uml2.uml.Dependency) &&
				!(obj instanceof org.eclipse.uml2.uml.Package))
			{
				System.err.print("# Unsupported Metaclass: ");
				System.out.println(obj);
			}
		}
		
		// (Process) Comments
		for (EObject obj : p1.getPackagedElements())
		{
			mydealer.ProcessComments ((org.eclipse.uml2.uml.Element) obj);
		}
		
		// Packages
		for (EObject obj : p1.getPackagedElements())
		{
			if (obj instanceof org.eclipse.uml2.uml.Package)
			{			
				RefOntoUML.Package son = DealPackage(obj);
				p2.getPackagedElement().add(son);								
			}
		}
		
		ECollections.sort(p2.getPackagedElement(), new RefOntoUMLPriorityHelper());
		// TODO: quem sabe tambem Collections.sort(p1.getPackagedElements()) no comeco
				
		return p2;
	}
}
