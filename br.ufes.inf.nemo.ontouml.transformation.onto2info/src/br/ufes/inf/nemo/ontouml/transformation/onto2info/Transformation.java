package br.ufes.inf.nemo.ontouml.transformation.onto2info;

import br.ufes.inf.nemo.ontouml.refontouml.util.*;
import org.eclipse.emf.ecore.EObject;

public class Transformation
{
	UMLFactoryAbstraction fa;	
	
	public Transformation()  
    { 
    	fa = new UMLFactoryAbstraction();  
    }	
	
	public void transform (RefOntoUMLModelAbstraction ma)
	{
		RefOntoUML.Model ontoumlmodel = ma.model;            
		org.eclipse.uml2.uml.Model umlmodel = org.eclipse.uml2.uml.UMLFactory.eINSTANCE.createModel();
                        
        fa.DealNamedElement(ontoumlmodel, umlmodel);
        fa.RelateElements(ontoumlmodel, umlmodel);
       
        // Rigid Sortals
        for (RefOntoUML.Class obj : ma.rigidSortals)
        {
        	org.eclipse.uml2.uml.Class c2 = fa.createClass(obj);
        	umlmodel.getPackagedElements().add(c2);
        }
        // All Mixins
        for (RefOntoUML.Class obj : ma.allMixins)
        {
        	org.eclipse.uml2.uml.Class c2 = fa.createClass(obj);
        	umlmodel.getPackagedElements().add(c2);
        }
        // Relators
        for (RefOntoUML.Class obj : ma.relators)
        {
        	org.eclipse.uml2.uml.Class c2 = fa.createClass(obj);
        	umlmodel.getPackagedElements().add(c2);
        }

        // Roles
        for (RefOntoUML.Role role : ma.roles)
        {
        	RefOntoUML.RigidSortalClass rigidParent = role.rigidParent();
        	RefOntoUML.Relator relator = role.relator();
        	// TODO: next step
        }
        
        // TODO: mediations
        // Associations
        for (EObject obj : ontoumlmodel.getPackagedElement())
        {
        	if(obj instanceof RefOntoUML.Derivation)
        	{        		        		  
        		org.eclipse.uml2.uml.Association assoc = fa.createAssociation ((RefOntoUML.Association) obj);
            	umlmodel.getPackagedElements().add(assoc);
            	
        	} else if( obj instanceof RefOntoUML.Association)
        	{        		
        		org.eclipse.uml2.uml.Association assoc = fa.createAssociation ((RefOntoUML.Association) obj);
            	umlmodel.getPackagedElements().add(assoc);        		
            }
        }
            
        // (Process) Generalizations        
        for (EObject obj : ontoumlmodel.getPackagedElement())
        {
            if (obj instanceof RefOntoUML.Classifier)
            {                    	    
                 fa.ProcessGeneralizations((RefOntoUML.Classifier)obj);
            }
        }
        
        // Generalization Sets      
        for (EObject obj : ontoumlmodel.getPackagedElement())
        {
             if (obj instanceof RefOntoUML.GeneralizationSet)
             {
             	org.eclipse.uml2.uml.GeneralizationSet gs2 = fa.DealGeneralizationSet ((RefOntoUML.GeneralizationSet) obj);        
                umlmodel.getPackagedElements().add(gs2);
             }
        }
            
        // Non Classifiers, Non GeneralizationSet 
        for (EObject obj : ontoumlmodel.getPackagedElement())
        {
        	if (!(obj instanceof RefOntoUML.Classifier) &&
        		!(obj instanceof RefOntoUML.GeneralizationSet) &&
                !(obj instanceof RefOntoUML.RefOntoUMLPackage))
            {
                System.err.print("# Unsupported Metaclass: ");
                System.out.println(obj);
            }
        }
	}
}
