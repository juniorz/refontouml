package br.ufes.inf.nemo.ontouml.transformation.onto2info;

import br.ufes.inf.nemo.ontouml.refontouml.util.*;
import br.ufes.inf.nemo.ontouml.transformation.onto2info.uml.UMLFactoryAbstraction;

import org.eclipse.emf.ecore.EObject;

public class Transformation
{
	UMLFactoryAbstraction fa;	
	
	public Transformation()  
    { 
    	fa = new UMLFactoryAbstraction();  
    }	
	
	public org.eclipse.uml2.uml.Model transform (RefOntoUMLModelAbstraction ma)
	{
		RefOntoUML.Model ontoumlmodel = ma.model;            
		org.eclipse.uml2.uml.Model umlmodel = org.eclipse.uml2.uml.UMLFactory.eINSTANCE.createModel();
                        
        fa.replicateNamedElement(ontoumlmodel, umlmodel);
        fa.relateElements(ontoumlmodel, umlmodel);
       
        // Rigid Sortals (as long as in scope)
        for (RefOntoUML.Class obj : ma.rigidSortals)
        {
        	org.eclipse.uml2.uml.Class c2 = fa.createClass(obj);
        	umlmodel.getPackagedElements().add(c2);
        	// Time tracking
        	if (obj instanceof RefOntoUML.SubstanceSortal)
        	{
        		if (true) // TODO: time tracking = true
        		{
        			fa.addStartTime(obj);
        			fa.addEndTime(obj);
        			fa.addDuration(obj);
        		}
        	}
        }
        // All Mixins (as long as in scope)
        for (RefOntoUML.Class obj : ma.allMixins)
        {
        	org.eclipse.uml2.uml.Class c2 = fa.createClass(obj);
        	umlmodel.getPackagedElements().add(c2);
        	
        	if (obj instanceof RefOntoUML.RoleMixin)
        	{
        		c2.setName("Potential".concat(c2.getName()));
        	}
        }
        // Relators (as long as in scope)
        for (RefOntoUML.Class obj : ma.relators)
        {
        	org.eclipse.uml2.uml.Class c2 = fa.createClass(obj);
        	umlmodel.getPackagedElements().add(c2);
        	// Time tracking
    		if (true) // TODO: time tracking = true
    		{
    			fa.addStartTime(obj);
    			fa.addEndTime(obj);
    			fa.addDuration(obj);
    		}
        }

        // Roles (Associations) (as long as in scope)
        // (by the way, besides the Role, the Relator and the RigidParent have to be in scope)
        for (RefOntoUML.Role role : ma.roles)
        {
        	if (role.mediation() != null)
        	{
        		org.eclipse.uml2.uml.Association a2 = fa.createAssociationRepresentingRole(role);
        		umlmodel.getPackagedElements().add(a2);
        	}
        }     
        // RoleMixins (Associations) (as long as in scope)
        // (by the way, besides the RoleMixin, the Relator has to be in scope)(and perhaps at least one rigidSortal corresponding to the RoleMixin)
        for (RefOntoUML.RoleMixin roleMixin : ma.roleMixins)
        {
        	if (roleMixin.mediation() != null)
        	{
        		org.eclipse.uml2.uml.Association a2 = fa.createAssociationRepresentingRoleMixin (roleMixin);
        		umlmodel.getPackagedElements().add(a2);
        	}
        }
        
        // Associations
        /*for (EObject obj : ontoumlmodel.getPackagedElement())
        {
        	if(obj instanceof RefOntoUML.Association)
        	{        		        		  
        		org.eclipse.uml2.uml.Association a2 = fa.createAssociation ((RefOntoUML.Association) obj);
            	umlmodel.getPackagedElements().add(a2);
            	
        	}
        }*/
            
        // (Process) Generalizations (Rigid Sortals) (as long as both the specific and the general are in scope)
        for (RefOntoUML.Classifier obj : ma.rigidSortals)
        {
        	// TODO: perhaps work through a list of generalizations
			fa.createAllGeneralizations(obj);
        }
        // (Process) Generalizations (All Mixins) (as long as both the specific and the general are in scope)
        for (RefOntoUML.Classifier obj : ma.allMixins)
        {
			fa.createAllGeneralizations(obj);
        }
        // Artificial Generalizations between RoleMixin Types and RigidSortal Types (as long as both are in scope)
        for (RefOntoUML.RoleMixin roleMixin : ma.roleMixins)
        {
        	fa.createArtificialGeneralizations(roleMixin);
        }
        
        // Generalization Sets (as long as both the parent and (at least some) children are in scope)      
        for (RefOntoUML.GeneralizationSet obj : ma.generalizationSets)
        {
         	org.eclipse.uml2.uml.GeneralizationSet gs2 = fa.createGeneralizationSet ((RefOntoUML.GeneralizationSet) obj);        
            umlmodel.getPackagedElements().add(gs2);
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
        
        // Time DataType
        umlmodel.getPackagedElements().add(fa.getTimeDataType());
        // Duration DataType
        umlmodel.getPackagedElements().add(fa.getDurationDataType());
        
        return umlmodel;
	}
}
