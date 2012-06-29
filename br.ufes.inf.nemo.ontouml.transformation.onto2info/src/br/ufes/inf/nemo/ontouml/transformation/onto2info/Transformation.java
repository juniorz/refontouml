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
        	if (dh.inScope(entry.getKey())) // TODO: perhaps I can add the attribute, even if the class is out of scope
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
        	if (dh.inScope(entry.getKey()))
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
			// Not AntiRigidSortalClass (Phase or Role)
			if (!(c instanceof RefOntoUML.AntiRigidSortalClass))
			{
				// Corresponding UML.Class
				org.eclipse.uml2.uml.Class c2 = (org.eclipse.uml2.uml.Class) Onto2InfoMap.getElement(c);
				
				// Scope
				if (dh.inScope(c))
				{
					// In Scope
					if (c2 == null)
					{
						// Create corresponding UML.Class
			        	c2 = fa.createClass(c); // FIXME: perhaps I could create it but not add it to the umlmodel
			        	umlAbstraction.addPackageableElement(c2);
			        	
			        	if (c instanceof RefOntoUML.RoleMixin)
			        	{
			        		c2.setName("Potential".concat(c2.getName())); // FIXME: do a later rotine to fix RoleMixin names?
			        	}
			        	
			        	System.out.println("Created UML.Class " + c2.getName());
					}
					// TODO: else { // check the attributes of the already existing corresponding UML.Class }
				}
				else
				{
					// Not in Scope
					if (c2 != null)
					{
						// Remove the corresponding UML.Class
						umlAbstraction.removePackageableElement(c2);
						// Remove the mapping between the OntoUML.Class and the UML.Class
						Onto2InfoMap.removeElement(c);
						// FIXME: Remove the mapping between Properties and Generalizations and Associations(?) of the deleted UML.Class...
												
						System.out.println("Removed UML.Class " + c2.getName());
					}
				}
			}
		}	
	}
	
	public void removeReferencesTo (RefOntoUML.Class c1)
	{
		for (RefOntoUML.Generalization g1 : c1.getGeneralization())
		{
			// TODO 
		}
	}
	
	public void createAssociations ()
	{
		// Roles + RoleMixins
		List<RefOntoUML.Class> qroles = new LinkedList<RefOntoUML.Class>(ontoAbstraction.roles);
		qroles.addAll(ontoAbstraction.roleMixins);
		
		// For each Role/RoleMixin
        for (RefOntoUML.Class qrole : qroles)
        {
        	RefOntoUML.Role role = null;
        	RefOntoUML.RoleMixin roleMixin = null;
        	RefOntoUML.Mediation mediation = null;
        	boolean scope = true;
        	
        	if (qrole instanceof RefOntoUML.Role)
        	{
        		// Scope for Role
        		role = (RefOntoUML.Role) qrole;
        		RefOntoUML.Class relator = role.relator();
        		mediation = role.mediation();
        		RefOntoUML.Class rigidParent = role.rigidParent();
        		
        		// There must be a Relator/Mediation
        		if (relator!=null && mediation!=null && rigidParent!=null)
        		{
            		// The Role, the Relator and the RigidParent must be in Scope
        			scope = dh.inScope(role) && dh.inScope(relator) && dh.inScope(rigidParent);
        		}
        		else
        		{
        			scope = false;
        		}
        	}
        	else if (qrole instanceof RefOntoUML.RoleMixin)
        	{
        		// Scope for RoleMixin
        		roleMixin = (RefOntoUML.RoleMixin) qrole;
        		RefOntoUML.Class relator = roleMixin.relator();
        		mediation = roleMixin.mediation();

        		// There must be a Relator/Mediation
        		if (relator!=null && mediation!=null)
        		{
        			// The RoleMixin and the Relator must be in Scope
        			// FIXME: (and perhaps at least one rigidSortal corresponding to the RoleMixin must be in scope)
        			scope = dh.inScope(roleMixin) && dh.inScope(relator);
        		}
        		else
        		{
        			scope = false;
        		}
        	}
        	
        	// The corresponding UML.Association
        	org.eclipse.uml2.uml.Association a2 = null; 
        	if (mediation != null)	
        		a2 = (org.eclipse.uml2.uml.Association) Onto2InfoMap.getElement(mediation);
        	
        	if (scope)
        	{
        		// In Scope        		
        		if (a2 == null)
        		{
        			// No corresponding UML.Association yet
        			if (role != null)
        				a2 = fa.createAssociationRepresentingRole(role);
        			else if (roleMixin != null)
        				a2 = fa.createAssociationRepresentingRoleMixin(roleMixin);
        			
        			umlAbstraction.addPackageableElement(a2);
        		}	
        	}
        	else
        	{
        		// Out of Scope
        		if (a2 != null)
        		{					
					// Remove the corresponding UML.Association from the UML.Model
					umlAbstraction.removePackageableElement(a2);
					// Remove the mapping between the OntoUML.Mediation and the UML.Association
					Onto2InfoMap.removeElement(mediation); // mediation won't be null here, since a2 is not 
											
					System.out.println("Removed UML.Association " + a2);       			
        		}
        	}
        }
	}
	
	public void createGeneralizations ()
	{
		createReplicateGeneralizations();
		createReplicateGeneralizationSets();
        createArtificialGeneralizationsforRoleMixin(); // TODO: check if the UML.Generalizations should be removed because they went out of scope
	}
	
	public void createReplicateGeneralizations ()
	{
		// By the way, the following two "for" blocks are almost identical
		
        // Generalizations (Rigid Sortals) (as long as both the specific and the general are in scope)
        for (RefOntoUML.Classifier obj : ontoAbstraction.rigidSortals)
        {
        	// TODO: perhaps work through a list of generalizations
			// generalization.specific in scope
			if (dh.inScope(obj))
			{
				for (RefOntoUML.Generalization gen1 : obj.getGeneralization())
				{
					// TODO: static method getCorrespondingGeneralzation (gen1)
					org.eclipse.uml2.uml.Generalization gen2 = (org.eclipse.uml2.uml.Generalization) Onto2InfoMap.getElement(gen1);
					
					// generalization.general in scope
					if (dh.inScope(gen1.getGeneral()))
					{
						// In Scope
						if (gen2 == null)
						{
							// Create the corresponding UML.Generalization
							fa.createGeneralization(gen1);
						}
					}
				}
			}
			
			// If the generalization.specific is out of scope then:
			// The corresponding UML.Class will be absent/removed in the previously called method: createdClasses()
			// So, I won't need to remove the UML.Generalizations, because they are owned by the corresponding UML.Class
        }
        
        // Generalizations (All Mixins) (as long as both the specific and the general are in scope)
        for (RefOntoUML.Classifier obj : ontoAbstraction.allMixins)
        {
			// specific in scope
			if (dh.inScope(obj))
			{
				for (RefOntoUML.Generalization gen1 : obj.getGeneralization())
				{
					org.eclipse.uml2.uml.Generalization gen2 = (org.eclipse.uml2.uml.Generalization) Onto2InfoMap.getElement(gen1);
					
					// general in scope
					if (dh.inScope(gen1.getGeneral()))
					{
						// In Scope
						if (gen2 == null)
						{
							// Create the corresponding UML.Generalization
							fa.createGeneralization(gen1);
						}
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
        	if (dh.inScope(gs1.parent()))
        	{
        		// Couting OntoUML.GeneralizationSet.children() in scope
        		int childInScope = 0;
        		for (RefOntoUML.Classifier child : gs1.children())
        		{
        			if (dh.inScope(child))
        				childInScope++;
        		}
        		
        		org.eclipse.uml2.uml.GeneralizationSet gs2 = (org.eclipse.uml2.uml.GeneralizationSet) Onto2InfoMap.getElement(gs1);
        		
        		if (childInScope > 1)
        		{
        			// In Scope
        			if (gs2 == null)
        			{
        				// Creates the corresponding UML.GeneralizationSet
        				gs2 = fa.createGeneralizationSet ((RefOntoUML.GeneralizationSet) gs1);        
        				umlAbstraction.addPackageableElement(gs2);
        			}
        		}
        		else
        		{
        			// Out of Scope
        			if (gs2 != null)
        			{
						// Removes the links between the UML.GeneralizationSet and the related UML.Generalizations
        				gs2.getGeneralizations().clear();
        				// Removes the corresponding UML.GeneralizationSet from the UML.Model
        				umlAbstraction.removePackageableElement(gs2);
        				// Removes the mapping between the OntoUML.GeneralizationSet and the UML.GeneralizationSet
        				Onto2InfoMap.removeElement(gs1);
        				
						System.out.println("Removed UML.GeneralizationSet " + gs2);
        			}
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
			if (dh.inScope(roleMixin))
			{
				List<org.eclipse.uml2.uml.Generalization> genlist = new LinkedList<org.eclipse.uml2.uml.Generalization>();
	    		org.eclipse.uml2.uml.Generalization gen;
	    		
	    		// For each specific (RigidSortal)
	    		for (RefOntoUML.RigidSortalClass rigidSortal : roleMixin.rigidSortals())
	    		{
	    			// specific (RigidSortal) in scope
	    			if (dh.inScope(rigidSortal))
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
        OntoUML2InfoUML.saveMap();
        
        return umlAbstraction.umlmodel;
	}
}
