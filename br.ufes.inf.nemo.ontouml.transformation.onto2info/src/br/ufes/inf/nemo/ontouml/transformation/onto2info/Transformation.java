package br.ufes.inf.nemo.ontouml.transformation.onto2info;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import br.ufes.inf.nemo.ontouml.transformation.onto2info.decision.*;
import br.ufes.inf.nemo.ontouml.refontouml.util.*;
import br.ufes.inf.nemo.ontouml.transformation.onto2info.uml.UMLFactoryAbstraction;

public class Transformation
{
	// OntoUML Model (Abstraction)
	RefOntoUMLModelAbstraction ontoumlmodel;
	// UML Model
	org.eclipse.uml2.uml.Model umlmodel;
	// UML Factory (Abstraction)
	UMLFactoryAbstraction fa;
	// UML Handler
	UMLHandler umlhandler;
	
	// TODO: Shouldn't the Map<RefOntoUML.Element, org.eclipse.uml2.uml.Element> stay here?
	Map<RefOntoUML.Classifier, ScopeDecision> scopeMap;
	Map<RefOntoUML.Class, HistoryDecision> historyMap;
	Map<RefOntoUML.Class, TimeDecision> timeMap;
	
	public Transformation()  
    { 
    	fa = new UMLFactoryAbstraction();
    	umlhandler = new UMLHandler();
    	scopeMap = new HashMap<RefOntoUML.Classifier, ScopeDecision>();
    	historyMap = new HashMap<RefOntoUML.Class, HistoryDecision>();
    	timeMap = new HashMap<RefOntoUML.Class, TimeDecision>();
    }

	public void initializeDecisions ()
	{
		// Scope Decisions (OntoUML.Class)
		for (RefOntoUML.Class c : ontoumlmodel.classes)
		{
			scopeMap.put(c, new ScopeDecision());
		}
		
		// History and Time Tracking Decisions (OntoUML.SubstanceSortal and OntoUML.Relator)
		for (RefOntoUML.SubstanceSortal ss : ontoumlmodel.substanceSortals)
		{
			historyMap.put(ss, new HistoryDecision());
			timeMap.put(ss, new TimeDecision());
		}
		for (RefOntoUML.Relator r : ontoumlmodel.relators)
		{
			historyMap.put(r, new HistoryDecision());
			timeMap.put(r, new TimeDecision());
		}
		
		// TODO: Reference Decisions
		// TODO: Measurement Decisions
	}
	
	public void dealHistoryTracking ()
	{
        // History Tracking
        for (Entry<RefOntoUML.Class, HistoryDecision> entry : historyMap.entrySet())
        {
        	ScopeDecision sd = scopeMap.get(entry.getKey());
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
        for (Entry<RefOntoUML.Class, TimeDecision> entry : timeMap.entrySet())
        {
        	ScopeDecision sd = scopeMap.get(entry.getKey());
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
				if (scopeMap.get(c).scope)
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
        // (Process) Generalizations (Rigid Sortals) (as long as both the specific and the general are in scope)
        for (RefOntoUML.Classifier obj : ontoumlmodel.rigidSortals)
        {
        	// TODO: perhaps work through a list of generalizations
			fa.createAllGeneralizations(obj);
        }
        // (Process) Generalizations (All Mixins) (as long as both the specific and the general are in scope)
        for (RefOntoUML.Classifier obj : ontoumlmodel.allMixins)
        {
			fa.createAllGeneralizations(obj);
        }
        // Artificial Generalizations between RoleMixin Types and RigidSortal Types (as long as both are in scope)
        for (RefOntoUML.RoleMixin roleMixin : ontoumlmodel.roleMixins)
        {
        	fa.createArtificialGeneralizations(roleMixin);
        }
        
        // Generalization Sets (as long as both the parent and (at least some) children are in scope)      
        for (RefOntoUML.GeneralizationSet obj : ontoumlmodel.generalizationSets)
        {
         	org.eclipse.uml2.uml.GeneralizationSet gs2 = fa.createGeneralizationSet ((RefOntoUML.GeneralizationSet) obj);        
            umlmodel.getPackagedElements().add(gs2);
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
	
	public org.eclipse.uml2.uml.Model transform (RefOntoUMLModelAbstraction ma)
	{
		ontoumlmodel = ma;
		umlmodel = fa.partiallyCreateModel(ontoumlmodel.model);
		initializeDecisions();
       
		createClasses();
		createAssociations();
		createGeneralizations();
        dealHistoryTracking();
        dealTimeTracking();
        addPrimitiveTypes();
                
        return umlmodel;
	}
}
