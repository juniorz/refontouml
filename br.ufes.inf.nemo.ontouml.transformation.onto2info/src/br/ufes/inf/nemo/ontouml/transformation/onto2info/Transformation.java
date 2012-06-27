package br.ufes.inf.nemo.ontouml.transformation.onto2info;

import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;

import br.ufes.inf.nemo.ontouml.transformation.onto2info.decision.*;
import br.ufes.inf.nemo.ontouml.refontouml.util.*;
import br.ufes.inf.nemo.ontouml.transformation.onto2info.uml.Ref2UMLCreator;
import br.ufes.inf.nemo.ontouml.transformation.onto2info.uml.Ref2UMLReplicator;

public class Transformation
{
	// OntoUML Model (Abstraction)
	RefOntoUMLModelAbstraction ontoumlmodel;
	// UML Model
	org.eclipse.uml2.uml.Model umlmodel;
	// UML Factory (Abstraction)
	Ref2UMLReplicator fa;
	// UML Handler
	Ref2UMLCreator umlhandler;
	// Decision Handler
	DecisionHandler dh;
	// RefOntoUML model file path
	String fileAbsolutePath;
	
	// TODO: Shouldn't the Map<RefOntoUML.Element, org.eclipse.uml2.uml.Element> stay here?
	
	public Transformation(String fileAbsolutePath)  
    { 
    	fa = new Ref2UMLReplicator();
    	umlhandler = new Ref2UMLCreator();
    	this.fileAbsolutePath = fileAbsolutePath;
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
	        		umlhandler.addHistoryTrackingAttribute(entry.getKey());
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
	        		umlhandler.addStartTime(entry.getKey());
	        	if (entry.getValue().end)
	        		umlhandler.addEndTime(entry.getKey());
	        	if (entry.getValue().start)
	        		umlhandler.addDuration(entry.getKey());
        	}
        }		
	}
	
	public void createClasses ()
	{
		for (RefOntoUML.Class c : ontoumlmodel.classes)
		{
			if (!(c instanceof RefOntoUML.Role) && !(c instanceof RefOntoUML.Phase))
			{
				// Scope
				if (dh.scopeMap.get(c).scope)
				{
		        	org.eclipse.uml2.uml.Class c2 = fa.createClass(c); // FIXME: perhaps I could create put do not add it to the umlmodel
		        	umlmodel.getPackagedElements().add(c2);
		        	
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
        for (RefOntoUML.Role role : ontoumlmodel.roles)
        {
        	if (role.mediation() != null)
        	{
        		org.eclipse.uml2.uml.Association a2 = fa.createAssociationRepresentingRole(role);
        		umlmodel.getPackagedElements().add(a2);
        	}
        }     
        // RoleMixins (Associations) (as long as in scope)
        // (by the way, besides the RoleMixin, the Relator has to be in scope)(and perhaps at least one rigidSortal corresponding to the RoleMixin)
        for (RefOntoUML.RoleMixin roleMixin : ontoumlmodel.roleMixins)
        {
        	if (roleMixin.mediation() != null)
        	{
        		org.eclipse.uml2.uml.Association a2 = fa.createAssociationRepresentingRoleMixin (roleMixin);
        		umlmodel.getPackagedElements().add(a2);
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
        for (RefOntoUML.Classifier obj : ontoumlmodel.rigidSortals)
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
        for (RefOntoUML.Classifier obj : ontoumlmodel.allMixins)
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
        for (RefOntoUML.GeneralizationSet gs1 : ontoumlmodel.generalizationSets)
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
        			umlmodel.getPackagedElements().add(gs2);
        		}
        	}
        }
	}
	
	public void createArtificialGeneralizationsforRoleMixin ()
	{
        // Artificial Generalizations between RoleMixin Types and RigidSortal Types (as long as both are in scope)
        for (RefOntoUML.RoleMixin roleMixin : ontoumlmodel.roleMixins)
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
	    				gen = umlhandler.createArtificialGeneralization (rigidSortal, roleMixin);
	    				genlist.add(gen);
	    			}
	    		}
	    		
	    		// The GeneralizationSet is only necessary when there is at least two children (rigidSortals) in scope 
	    		if (genlist.size() > 1)
	    		{
	    			// Linking the GeneralizationSet and the Generalizations
	    			org.eclipse.uml2.uml.GeneralizationSet gset = umlhandler.createGeneralizationSetForRoleMixin (roleMixin, genlist);
	    			umlmodel.getPackagedElements().add(gset);
	    		}
        	}
        }
	}
	
	public void addPrimitiveTypes()
	{
        // Time DataType
        umlmodel.getPackagedElements().add(umlhandler.getTimeType());
        // Duration DataType
        umlmodel.getPackagedElements().add(umlhandler.getDurationType());
        // Boolean PrimitiveType
        umlmodel.getPackagedElements().add(umlhandler.getBooleanType());	
	}
	
	public org.eclipse.uml2.uml.Model transform (RefOntoUMLModelAbstraction ma, DecisionHandler dh)
	{
		ontoumlmodel = ma;
		umlmodel = fa.partiallyCreateModel(ontoumlmodel.model);
		this.dh = dh;
       
		createClasses();
		createAssociations();
		createGeneralizations();
        dealHistoryTracking();
        dealTimeTracking();
        addPrimitiveTypes();
                
        Ref2UMLCreator.saveUMLModel(umlmodel, fileAbsolutePath.replace(".refontouml", ".uml"));
        
        return umlmodel;
	}
}
