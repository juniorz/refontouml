package br.ufes.inf.nemo.ontouml.transformation.onto2info;

import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;

import br.ufes.inf.nemo.ontouml.transformation.onto2info.decision.*;
import br.ufes.inf.nemo.ontouml.refontouml.util.*;
import br.ufes.inf.nemo.ontouml.transformation.onto2info.uml.UMLModelAbstraction;
import br.ufes.inf.nemo.ontouml.transformation.onto2info.uml.Ref2UMLReplicator;

public class Transformation
{
	// OntoUML Model (Abstraction)
	RefOntoUMLModelAbstraction ontoAbstraction;
	// UML Model (Abstraction)
	UMLModelAbstraction umlAbstraction;
	// UML Factory (Abstraction)
	Ref2UMLReplicator fa;
	// Decision Handler
	DecisionHandler dh;
	
	// TODO: Shouldn't the Map<RefOntoUML.Element, org.eclipse.uml2.uml.Element> stay here?
	
	public Transformation(RefOntoUMLModelAbstraction ontoAbstraction, UMLModelAbstraction umlAbstraction)  
    { 
		this.ontoAbstraction = ontoAbstraction;
		this.umlAbstraction = umlAbstraction;
    	fa = new Ref2UMLReplicator();
    }
	
	public void dealHistoryTracking ()
	{
        // History Tracking
        for (Entry<RefOntoUML.Class, HistoryDecision> entry : dh.historyMap.entrySet())
        {
        	ScopeDecision sd = dh.scopeMap.get(entry.getKey());
        	if (sd.scope) // TODO: perhaps I can add the attribute, even if the class is out of scope
        	{
	        	if (entry.getValue().requiresAttribute())
	        	{
	        		umlAbstraction.addHistoryTrackingAttribute(entry.getKey());
	        	}
        	}
        }
	}
	
	public void dealTimeTracking ()
	{
        // Time Tracking
        for (Entry<RefOntoUML.Class, TimeDecision> entry : dh.timeMap.entrySet())
        {
        	ScopeDecision sd = dh.scopeMap.get(entry.getKey());
        	if (sd.scope)
        	{
	        	if (entry.getValue().start)
	        		umlAbstraction.addStartTime(entry.getKey());
	        	if (entry.getValue().end)
	        		umlAbstraction.addEndTime(entry.getKey());
	        	if (entry.getValue().start)
	        		umlAbstraction.addDuration(entry.getKey());
        	}
        }		
	}
	
	public void createClasses ()
	{
		for (RefOntoUML.Class c : ontoAbstraction.classes)
		{
			if (!(c instanceof RefOntoUML.Role) && !(c instanceof RefOntoUML.Phase))
			{
				// Scope
				if (dh.scopeMap.get(c).scope)
				{
		        	org.eclipse.uml2.uml.Class c2 = fa.createClass(c); // FIXME: perhaps I could create put do not add it to the umlmodel
		        	umlAbstraction.addPackageableElement(c2);
		        	
		        	if (c instanceof RefOntoUML.RoleMixin)
		        	{
		        		c2.setName("Potential".concat(c2.getName())); // FIXME: do a later rotine to fix RoleMixin names?
		        	}
				}
			}
		}	
	}
	
	public void createAssociations ()
	{
        // Roles (Associations) (as long as in scope)
        // (by the way, besides the Role, the Relator and the RigidParent have to be in scope)
        for (RefOntoUML.Role role : ontoAbstraction.roles)
        {
        	if (role.mediation() != null)
        	{
        		org.eclipse.uml2.uml.Association a2 = fa.createAssociationRepresentingRole(role);
        		umlAbstraction.addPackageableElement(a2);
        	}
        }     
        // RoleMixins (Associations) (as long as in scope)
        // (by the way, besides the RoleMixin, the Relator has to be in scope)(and perhaps at least one rigidSortal corresponding to the RoleMixin)
        for (RefOntoUML.RoleMixin roleMixin : ontoAbstraction.roleMixins)
        {
        	if (roleMixin.mediation() != null)
        	{
        		org.eclipse.uml2.uml.Association a2 = fa.createAssociationRepresentingRoleMixin (roleMixin);
        		umlAbstraction.addPackageableElement(a2);
        	}
        }
	}
	
	public void createGeneralizations ()
	{
		createReplicateGeneralizations();
		createReplicateGeneralizationSets();
        createArtificialGeneralizationsforRoleMixin();
	}
	
	public void createReplicateGeneralizations ()
	{
        // Generalizations (Rigid Sortals) (as long as both the specific and the general are in scope)
        for (RefOntoUML.Classifier obj : ontoAbstraction.rigidSortals)
        {
        	// TODO: perhaps work through a list of generalizations
			// specific in scope
			if (dh.scopeMap.get(obj).scope)
			{
				for (RefOntoUML.Generalization gen1 : obj.getGeneralization())
				{
					// general in scope
					if (dh.scopeMap.get(gen1.getGeneral()).scope)
					{
						fa.createGeneralization(gen1);
					}
				}
			}
        }
        // Generalizations (All Mixins) (as long as both the specific and the general are in scope)
        for (RefOntoUML.Classifier obj : ontoAbstraction.allMixins)
        {
			// specific in scope
			if (dh.scopeMap.get(obj).scope)
			{
				for (RefOntoUML.Generalization gen1 : obj.getGeneralization())
				{
					// general in scope
					if (dh.scopeMap.get(gen1.getGeneral()).scope)
					{
						fa.createGeneralization(gen1);
					}
				}
			}
        }
	}
	
	public void createReplicateGeneralizationSets ()
	{
        // Generalization Sets (as long as both the parent and (at least some) children are in scope)      
        for (RefOntoUML.GeneralizationSet gs1 : ontoAbstraction.generalizationSets)
        {
        	if (dh.scopeMap.get(gs1.parent()).scope)
        	{
        		int childInScope = 0;
        		for (RefOntoUML.Classifier child : gs1.children())
        		{
        			if (dh.scopeMap.get(child).scope)
        				childInScope++;
        		}
        		
        		if (childInScope > 1)
        		{
        			org.eclipse.uml2.uml.GeneralizationSet gs2 = fa.createGeneralizationSet ((RefOntoUML.GeneralizationSet) gs1);        
        			umlAbstraction.addPackageableElement(gs2);
        		}
        	}
        }
	}
	
	public void createArtificialGeneralizationsforRoleMixin ()
	{
        // Artificial Generalizations between RoleMixin Types and RigidSortal Types (as long as both are in scope)
        for (RefOntoUML.RoleMixin roleMixin : ontoAbstraction.roleMixins)
        {
        	// general (RoleMixin) in scope
			if (dh.scopeMap.get(roleMixin).scope)
			{
				List<org.eclipse.uml2.uml.Generalization> genlist = new LinkedList<org.eclipse.uml2.uml.Generalization>();
	    		org.eclipse.uml2.uml.Generalization gen;
	    		
	    		// For each specific (RigidSortal)
	    		for (RefOntoUML.RigidSortalClass rigidSortal : roleMixin.rigidSortals())
	    		{
	    			// specific (RigidSortal) in scope
	    			if (dh.scopeMap.get(rigidSortal).scope)
	    			{
	    				// Create artificial Generalization (RigidSortal -> RoleMixin)
	    				gen = umlAbstraction.createArtificialGeneralization (rigidSortal, roleMixin);
	    				genlist.add(gen);
	    			}
	    		}
	    		
	    		// The GeneralizationSet is only necessary when there is at least two children (rigidSortals) in scope 
	    		if (genlist.size() > 1)
	    		{
	    			// Linking the GeneralizationSet and the Generalizations
	    			org.eclipse.uml2.uml.GeneralizationSet gset = umlAbstraction.createGeneralizationSetForRoleMixin (roleMixin, genlist);
	    			umlAbstraction.addPackageableElement(gset);
	    		}
        	}
        }
	}
		
	public org.eclipse.uml2.uml.Model transform (DecisionHandler dh)
	{
		if (umlAbstraction.umlmodel == null)
			umlAbstraction.umlmodel = fa.partiallyCreateModel(ontoAbstraction.model);
		this.dh = dh;
       
		// FIXME: In case the UML.Model already exists, do not create UML things if they already exist
		// Also, delete UML things that will not exist anymore
		createClasses();
		createAssociations();
		createGeneralizations();
        dealHistoryTracking();
        dealTimeTracking();

        umlAbstraction.addPrimitiveTypes();
        umlAbstraction.save();
        
        return umlAbstraction.umlmodel;
	}
}
