package br.ufes.inf.nemo.ontouml.transformation.onto2info;

import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;

import br.ufes.inf.nemo.ontouml.transformation.onto2info.decision.*;
import br.ufes.inf.nemo.ontouml.refontouml.util.*;
import br.ufes.inf.nemo.ontouml.transformation.onto2info.ui.Onto2InfoInterface;
import br.ufes.inf.nemo.ontouml.transformation.onto2info.uml.UMLModelAbstraction;
import br.ufes.inf.nemo.ontouml.transformation.onto2info.uml.Onto2UMLReplicator;

public class Transformation
{
	// OntoUML Model (Abstraction)
	RefOntoUMLModelAbstraction ontoAbstraction;
	// UML Model (Abstraction)
	UMLModelAbstraction umlAbstraction;
	// UML Factory (Abstraction)
	Onto2UMLReplicator fa;
	// Decision Handler
	DecisionHandler dh;
	// Interface
	Onto2InfoInterface ui;

	// Number of additions in one transformation operation
	int numAdditions;
	// Number of removals in one transformation operation
	int numRemovals;

	public Transformation(RefOntoUMLModelAbstraction ontoAbstraction, UMLModelAbstraction umlAbstraction, Onto2InfoInterface ui)  
    { 
		this.ontoAbstraction = ontoAbstraction;
		this.umlAbstraction = umlAbstraction;
		this.ui = ui;
    	fa = new Onto2UMLReplicator();
    }
	
	public void dealHistoryTracking ()
	{
        // History Tracking
        for (Entry<RefOntoUML.Class, HistoryDecision> entry : dh.historyMap.entrySet())
        {
        	RefOntoUML.Class c1 = entry.getKey();
        	HistoryDecision decision = entry.getValue();
        	UMLAttributeSlot slot = dh.getAttributeSlot(c1);
        	
        	if (dh.inScope(c1))
        	{
        		// OntoUML.Class in scope        		
	        	if (decision.requiresAttribute())
	        	{
	        		// HistoryTracking = true
	        		if (slot.htAttribute == null)
	        		{
	        			// UML.Property (Attribute) does not exist
	        			slot.htAttribute = umlAbstraction.addHistoryTrackingAttribute(c1);
	        			
	        			ui.writeLog("Created UML.Property for " + c1.getName() + ": " + slot.htAttribute.getName());
	        			numAdditions++;
	        		}
	        	}
	        	else
	        	{
	        		// HistoryTracking = false
	        		if (slot.htAttribute != null)
	        		{
	        			// UML.Property (Attribute) exists
	        			// Remove it from the UML.Class
	        			umlAbstraction.removeClassAttribute(c1, slot.htAttribute);
	        			// Remove it from the HistoryDecision
	        			slot.htAttribute = null;
	        			
	        			ui.writeLog("Removed UML.Property for " + c1.getName() + " (History Tracking)");
	        			numRemovals++;
	        		}
	        	}
        	}
        	else
        	{
        		// OntoUML.Class out of scope
        		// UML.Class was already removed, but...
        		if (slot.htAttribute != null)
        		{
        			// Clear the UMLAttributeSlot
        			slot.htAttribute = null;
        			ui.writeLog("Removed UML.Property for " + c1.getName() + " (History Tracking)");
        			numRemovals++;
        		}
        	}
        }
	}
	
	interface SlotManipulator
	{
		public org.eclipse.uml2.uml.Property getSlotAttribute ();
		public void setSlotAttribute (org.eclipse.uml2.uml.Property p);
		public org.eclipse.uml2.uml.Property addTimeAttribute (RefOntoUML.Class c1);
	}
	
	class StartAttributeManipulator implements SlotManipulator
	{
		UMLAttributeSlot slot;
		
		public StartAttributeManipulator (UMLAttributeSlot slot)
		{
			this.slot = slot;
		}
		
		public org.eclipse.uml2.uml.Property getSlotAttribute ()
		{
			return slot.startAttribute;
		}
		
		public void setSlotAttribute (org.eclipse.uml2.uml.Property p)
		{
			slot.startAttribute = p;
		}
		
		public org.eclipse.uml2.uml.Property addTimeAttribute (RefOntoUML.Class c1)
		{
			return umlAbstraction.addStartTime(c1);
		}
	}
	
	class EndAttributeManipulator implements SlotManipulator
	{
		UMLAttributeSlot slot;
		
		public EndAttributeManipulator (UMLAttributeSlot slot)
		{
			this.slot = slot;
		}
		
		public org.eclipse.uml2.uml.Property getSlotAttribute ()
		{
			return slot.endAttribute;
		}
		
		public void setSlotAttribute (org.eclipse.uml2.uml.Property p)
		{
			slot.endAttribute = p;
		}
		
		public org.eclipse.uml2.uml.Property addTimeAttribute (RefOntoUML.Class c1)
		{
			return umlAbstraction.addEndTime(c1);
		}
	}
	
	class DurationAttributeManipulator implements SlotManipulator
	{
		UMLAttributeSlot slot;
		
		public DurationAttributeManipulator (UMLAttributeSlot slot)
		{
			this.slot = slot;
		}
		
		public org.eclipse.uml2.uml.Property getSlotAttribute ()
		{
			return slot.durationAttribute;
		}
		
		public void setSlotAttribute (org.eclipse.uml2.uml.Property p)
		{
			slot.durationAttribute = p;
		}
		
		public org.eclipse.uml2.uml.Property addTimeAttribute (RefOntoUML.Class c1)
		{
			return umlAbstraction.addDuration(c1);
		}
	}
	
	public void dealTimeAttribute (RefOntoUML.Class c1, boolean decision, SlotManipulator sm)
	{
		// StartTime
    	if (decision)
    	{
    		// Decision = true
    		if (sm.getSlotAttribute() == null)
    		{
    			// UML.Property (Attribute) does not exist
    			
    			// 1- Create a new time attribute (start, end or duration) for the UML.Class corresponding to c1
    			// 2- Set this new time attribute in the slot
    			sm.setSlotAttribute(sm.addTimeAttribute(c1));
    			
    			ui.writeLog("Created UML.Property for " + c1.getName() + " (" + sm.getSlotAttribute().getName() + ")");
    			numAdditions++;
    		}
    	}
    	else
    	{
    		// Decision = false
    		if (sm.getSlotAttribute() != null)
    		{
    			// UML.Property (Attribute) exists
    			
    			// Remove it from the UML.Class
    			umlAbstraction.removeClassAttribute(c1, sm.getSlotAttribute());
    			
    			// The attribute must be removed from the Slot
    			setSlotAttributeToNull(c1, sm);
    		}
    	}
	}
	
	public void setSlotAttributeToNull (RefOntoUML.Class c1, SlotManipulator sm)
	{
		if (sm.getSlotAttribute() != null)
		{
			String attributeName = sm.getSlotAttribute().getName(); // Store the attribute name, before it becomes null (just for printing)
			// Set the attribute (start, end or duration) as null in the slot
			sm.setSlotAttribute(null);
			
			ui.writeLog("Removed UML.Property for " + c1.getName() + " (" + attributeName + ")");
			numRemovals++;
		}
	}
	
	public void dealTimeTracking ()
	{
        // Time Tracking
        for (Entry<RefOntoUML.Class, TimeDecision> entry : dh.timeMap.entrySet())
        {
        	RefOntoUML.Class c1 = entry.getKey();
        	TimeDecision decision = entry.getValue();
        	UMLAttributeSlot slot = dh.getAttributeSlot(c1);

        	SlotManipulator startSM = new StartAttributeManipulator(slot);
        	SlotManipulator endSM = new EndAttributeManipulator(slot);
        	SlotManipulator durationSM = new DurationAttributeManipulator(slot);
        	
        	if (dh.inScope(c1))
        	{
        		// OntoUML.Class in scope
        		dealTimeAttribute (c1, decision.start, startSM);
        		dealTimeAttribute (c1, decision.end, endSM);
				dealTimeAttribute (c1, decision.duration, durationSM);
        	}
        	else
        	{
        		// OntoUML.Class out of scope
        		// UML.Class was already removed, but...
        		// One must clean the references in the UMLAttributeSlot
        		setSlotAttributeToNull (c1, startSM);
        		setSlotAttributeToNull (c1, endSM);
        		setSlotAttributeToNull (c1, durationSM);
        	}
        }
	}
	
	public void createClasses ()
	{
		// All OntoUML.Classes (except Roles and Phases)
		for (RefOntoUML.Class c : ontoAbstraction.classes)
		{
			if (!(c instanceof RefOntoUML.AntiRigidSortalClass))
			{
				// Corresponding UML.Class
				org.eclipse.uml2.uml.Class c2 = Onto2InfoMap.getClass(c);
				
				// Scope
				if (dh.inScope(c))
				{
					// In Scope
					if (c2 == null)
					{
						// Create corresponding UML.Class
			        	c2 = fa.createClass(c);
			        	umlAbstraction.addPackageableElement(c2);
			        	
			        	if (c instanceof RefOntoUML.RoleMixin)
			        	{
			        		c2.setName("Potential".concat(c2.getName()));
			        	}
			        	
			        	ui.writeLog("Created UML.Class " + c2.getName());
			        	numAdditions++;
					}
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
						// If some day OntoUML.Classes could have attributes, an OntoUML.Property<->UML.Property mapping would also have to be removed

						ui.writeLog("Removed UML.Class " + c2.getName());
						numRemovals++;
					}
				}
			}
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
        		a2 = Onto2InfoMap.getAssociation(mediation);
        	
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
        			
					ui.writeLog("Created UML.Association: " + umlAbstraction.associationToString(a2));
					numAdditions++;
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
					Onto2InfoMap.removeElement(mediation.relatorEnd()); // subtle detail (OntoUML.Property<->UML.Property mapping)
					Onto2InfoMap.removeElement(mediation.mediatedEnd()); // subtle detail (OntoUML.Property<->UML.Property mapping)
											
					ui.writeLog("Removed UML.Association: " + umlAbstraction.associationToString(a2));
					numRemovals++;
        		}
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
		// RigidSortals + AllMixins
		List<RefOntoUML.Class> specifics = new LinkedList<RefOntoUML.Class>(ontoAbstraction.rigidSortals);
		specifics.addAll(ontoAbstraction.allMixins);
		
        // Generalizations (Rigid Sortals) (as long as both the specific and the general are in scope)
        for (RefOntoUML.Classifier specific : specifics)
        {
        	// For each Generalization of an OntoUML.Class (excluding Roles and Phases)
			for (RefOntoUML.Generalization gen1 : specific.getGeneralization())
			{
				org.eclipse.uml2.uml.Generalization gen2 = Onto2InfoMap.getGeneralization(gen1);
				
				// generalization.specific and generalization.general in scope
				if (dh.inScope(specific) && dh.inScope(gen1.getGeneral()))
				{
					// In Scope
					if (gen2 == null)
					{
						// Create the corresponding UML.Generalization
						gen2 = fa.createGeneralization(gen1);
						
						ui.writeLog("Created UML.Generalization: " + gen2.getSpecific().getName() + "->" + gen2.getGeneral().getName());
						numAdditions++;
					}
				}
				else
				{
					// Out of Scope
					if (gen2 != null)
					{
						// Removes the reference in the map from the OntoUML.Generalization to the UML.Generalization
						Onto2InfoMap.removeElement(gen1);
						
						if (dh.inScope(specific))
						{
							// OntoUML.Specific is in scope
							// Get the UML.Classifier corresponding to the specific
							org.eclipse.uml2.uml.Classifier specific2 = Onto2InfoMap.getClassifier(specific);
							// Remove the UML.Generalization as an owned generalization of the specific UML.Classifier
							umlAbstraction.removeGeneralization(specific2, gen2); 
						}
												
						// If the OntoUML.Specific is out of scope then:
						// The corresponding UML.Classifier will be absent/removed from the UML.Model in the previously called method: createdClasses()
						// So, I won't need to remove the UML.Generalizations from the UML.Model or from the specific UML.Classifier

						// gen2.general and gen2.specific may be already gone, so I can't print them
						ui.writeLog("Removed UML.Generalization: " + gen1.getSpecific().getName() + "->" + gen1.getGeneral().getName());
						numRemovals++;
					}
				}
			}
        }
	}
	
	public org.eclipse.uml2.uml.Generalization createOrGetArtificialGeneralization (RefOntoUML.RoleMixin roleMixin, RefOntoUML.Role role, List<org.eclipse.uml2.uml.Generalization> genlist)
	{
		// UML.Generalization corresponding to OntoUML.Role (in case the artificial UML.Generalization already exists)
		org.eclipse.uml2.uml.Generalization gen2 = Onto2InfoMap.getGeneralization(role);

		// Role's rigid parent (e.g., OntoUML.Kind.Person from OntoUML.Role.PrivateCustomer)
		RefOntoUML.RigidSortalClass rigidParent = role.rigidParent();
		// UML.Class corresponding to the OntoUML.RigidParent (e.g., UML.Person corresponding to OntoUML.Person)
		org.eclipse.uml2.uml.Classifier specific2 = Onto2InfoMap.getClassifier(rigidParent);

		// OntoUML.Role (<->UML.Generalization), RigidSortal (<->UML.Generalization.specific) and RoleMixin (<->UML.Generalization.general) in scope
		// Maybe I should constrain: OntoUML.Role in scope -> OntoUML.RigidParent in scope
		if (dh.inScope(role) && dh.inScope(rigidParent) && dh.inScope(roleMixin))
		{
			// In Scope
			if (gen2 == null)
			{
				// UML.Generalization (RigidSortal->RoleMixin) does not exist
				// Create artificial Generalization (RigidSortal -> RoleMixin)
				org.eclipse.uml2.uml.Classifier general2 = Onto2InfoMap.getClassifier(roleMixin);    					
				
				gen2 = umlAbstraction.createGeneralization (specific2, general2);
				
				// Relates the OntoUML.Role and the UML.Generalization
				Onto2InfoMap.relateElements(role, gen2);
				
				ui.writeLog("Created UML.Generalization (artificial): " + gen2.getSpecific().getName() + "->" + gen2.getGeneral().getName());
				numAdditions++;
			}
			
			// The artificial UML.Generalization (that already existed or was just created) must be added to the return list
			genlist.add(gen2);
		}
		else
		{
			// Out of Scope
			if (gen2 != null)
			{
				// UML.Generalization exists    					
				// Removes the reference in the map from the OntoUML.Role to the UML.Generalization
				Onto2InfoMap.removeElement(role);
				
				if (dh.inScope(role)) // let us suppose this implies dh.inScope(rigidParent) (but, right now, this may cause a bug)
				{
					// OntoUML.Specific (UML.Generalization.specific) is in scope
					// Remove the UML.Generalization as an owned generalization of the specific UML.Classifier
					umlAbstraction.removeGeneralization(specific2, gen2); 
				}
										
				// If the OntoUML.Specific is out of scope then:
				// The corresponding UML.Classifier will be absent/removed from the UML.Model in the previously called method: createdClasses()
				// So, I won't need to remove the UML.Generalizations from the UML.Model or from the specific UML.Classifier
				
				// Can't print UML.Generalization.general or UML.Generalization.specific because they may be already gone
				ui.writeLog("Removed UML.Generalization (artificial): " + rigidParent.getName() + "->" + roleMixin.getName());
				numRemovals++;
			}
		}
		
		return gen2;
	}
	
	public void createOrGetArtificialGeneralizations (RefOntoUML.RoleMixin roleMixin)
	{
		// For each OntoUML.Role of a RoleMixin there will be an (artificial) UML.Generalization between the UML<->Role'sRigidParent and the UML<->RoleMixin
		// (e.g., UML.Person -> UML.Customer, corresponding to OntoUML.Role.PrivateCustomer)
		
		// An already existing artificial UML.GeneralizationSet related to the artificial UML.Generalizations, if any
		org.eclipse.uml2.uml.GeneralizationSet gset2 = null;
		
    	// UML.Generalization List (important to link them later with a UML.GeneralizationSet, in case there must be one)
		List<org.eclipse.uml2.uml.Generalization> genlist = new LinkedList<org.eclipse.uml2.uml.Generalization>();
				
		// For each Role (e.g., PrivateCustomer, CorporateCustomer) of a RoleMixin (e.g., Customer)
		for (RefOntoUML.Role role : roleMixin.roles())
		{
			// UML.Generalization corresponding to OntoUML.Role (in case the artificial UML.Generalization already exists)
			org.eclipse.uml2.uml.Generalization gen2 = createOrGetArtificialGeneralization (roleMixin, role, genlist);
			
			// One more thing... There may be an artificial UML.GeneralizationSet
			if (gen2 != null)
			{
				if (gen2.getGeneralizationSets().size() > 0)
				{
					gset2 = gen2.getGeneralizationSets().get(0);
				}
			}
		}
		
		createArtificialGeneralizationSet (roleMixin, gset2, genlist);
	}
		
	public void createArtificialGeneralizationSet (RefOntoUML.RoleMixin roleMixin, org.eclipse.uml2.uml.GeneralizationSet gset2, List<org.eclipse.uml2.uml.Generalization> genlist)
	{				
		// The UML.GeneralizationSet is only necessary when there is at least two children (rigidSortals) in scope 
		if (genlist.size() > 1)
		{
			// UML.GeneralizationSet "in scope"
			if (gset2 == null)
			{
				// UML.GeneralizationSet does not exist
				// Linking the GeneralizationSet and the Generalizations
    			gset2 = umlAbstraction.createGeneralizationSetForRoleMixin (roleMixin, genlist);
    			umlAbstraction.addPackageableElement(gset2);
    			
    			ui.writeLog("Created UML.GeneralizationSet (artificial): " + umlAbstraction.generalizationSetToString(gset2));
    			numAdditions++;
			}
		}
		else
		{
			// UML.GeneralizationSet "out of scope"
			if (gset2 != null)
			{
				// UML.GeneralizationSet exists
				// Remove it from the UML.Model
				umlAbstraction.removePackageableElement(gset2);
				
				// For each UML.Generalization in scope, remove its reference to the UML.GeneralizationSet
				for (org.eclipse.uml2.uml.Generalization sgen : genlist)
				{
					sgen.getGeneralizationSets().remove(gset2);
				}
				
				// Cannot print the UML.GeneralizationSet properly because references to UML.Generalizations, generals and specifics may be gone (TODO: print in OntoUML)
				ui.writeLog("Removed UML.GeneralizationSet (artificial): " + gset2.getName());
				numRemovals++;
			}
		}
	}
	
	public void createArtificialGeneralizationsforRoleMixin ()
	{
        // Artificial Generalizations between RoleMixin Types and RigidSortal Types (as long as both are in scope)
		
		// For each RoleMixin
        for (RefOntoUML.RoleMixin roleMixin : ontoAbstraction.roleMixins)
        {
        	// 1- Create or get (or remove) artificial UML.Generalizations, each corresponding to a Role of the RoleMixin
    		// 2- Create (or remove) an artificial UML.GeneralizationSet for the RoleMixin, if necessary
			createOrGetArtificialGeneralizations (roleMixin);
        }
	}
	
	List<RefOntoUML.Generalization> getGeneralizationsWithSpecificInScope (RefOntoUML.GeneralizationSet gset1)
	{
		List<RefOntoUML.Generalization> genList = new LinkedList<RefOntoUML.Generalization>();
		
		// For each OntoUML.Generalization in OntoUML.GeneralizationSet
		for (RefOntoUML.Generalization gen1 : gset1.getGeneralization())
		{
			// OntoUML.Generalization.specific in Scope
			if (dh.inScope(gen1.getSpecific()))
				genList.add(gen1);
		}
		
		return genList;
	}
	
	public void createReplicateGeneralizationSets ()
	{
		// Generalization Sets (as long as both the parent and (at least some) children are in scope)
		
		// For each OntoUML.GeneralizationSet
		for (RefOntoUML.GeneralizationSet gset1 : ontoAbstraction.generalizationSets)
		{
			// The corresponding UML.GeneralizationSet
			org.eclipse.uml2.uml.GeneralizationSet gset2 = Onto2InfoMap.getGeneralizationSet(gset1);
			
			// Couting OntoUML.GeneralizationSet.children() in scope
			List<RefOntoUML.Generalization> genInScope = getGeneralizationsWithSpecificInScope(gset1);
			
			// GeneralizationSet.parent and at least one GeneralizationSet.child in scope
			if (dh.inScope(gset1.parent()) && genInScope.size() > 1)
			{
				// In Scope
				if (gset2 == null)
				{
					// Creates the corresponding UML.GeneralizationSet
					gset2 = fa.createGeneralizationSet (gset1, genInScope);
					umlAbstraction.addPackageableElement(gset2);
					
					ui.writeLog("Created UML.GeneralizationSet: " + umlAbstraction.generalizationSetToString(gset2));
					numAdditions++;
				}
			}
			else
			{
				// Out of Scope
				if (gset2 != null)
				{
					// Removes the links between the UML.GeneralizationSet and the related UML.Generalizations
					gset2.getGeneralizations().clear();
					// Removes the corresponding UML.GeneralizationSet from the UML.Model
					umlAbstraction.removePackageableElement(gset2);
					// Removes the mapping between the OntoUML.GeneralizationSet and the UML.GeneralizationSet
					Onto2InfoMap.removeElement(gset1);
					
					// Cannot print the UML.GeneralizationSet properly because references to UML.Generalizations, generals and specifics may be gone (TODO: print in OntoUML)
					ui.writeLog("Removed UML.GeneralizationSet: " + gset2.getName());
					numRemovals++;
				}
			}
		}
	}
	
	public void addPrimitiveTypes()
	{
        // Time DataType
		if (!umlAbstraction.hasPackageableElement(umlAbstraction.getTimeType()))
		{
			umlAbstraction.addPackageableElement(umlAbstraction.getTimeType());
			ui.writeLog("Created UML.DataType: " + umlAbstraction.getTimeType().getName());
			numAdditions++;
		}
		
        // Duration DataType
		if (!umlAbstraction.hasPackageableElement(umlAbstraction.getDurationType()))
		{
			umlAbstraction.addPackageableElement(umlAbstraction.getDurationType());
			ui.writeLog("Created UML.DataType: " + umlAbstraction.getDurationType().getName());
			numAdditions++;
		}
		
        // Boolean PrimitiveType
		if (!umlAbstraction.hasPackageableElement(umlAbstraction.getBooleanType()))
		{
			umlAbstraction.addPackageableElement(umlAbstraction.getBooleanType());
			ui.writeLog("Created UML.PrimitiveType: " + umlAbstraction.getBooleanType().getName());
			numAdditions++;
		}
	}

	// TODO: this shouldn't be here, I guess...
	public void printSuccessMessage (boolean first)
	{
		String extraText = "";
		
		if (!first)
		{
			extraText += " (";
			
			if (numAdditions == 0 && numRemovals == 0)
			{
				extraText += "no changes";
			}
			else
			{
				if (numAdditions != 0)
				{
					extraText += numAdditions + " addition" + (numAdditions == 1 ? "" : "s");
					if (numRemovals != 0)
						extraText += ", ";
				}
				if (numRemovals != 0)
					extraText += numRemovals + " removal" + (numRemovals == 1 ? "" : "s");
			}
			
			extraText += ")";
		}
		
		ui.writeText("Transformation done" + extraText);
	}
	
	public org.eclipse.uml2.uml.Model transform (DecisionHandler dh)
	{
		// Transformation from scratch
		boolean first = true;
		// Initialize additions and removals made so far
		numAdditions = 0;
		numRemovals = 0;
		this.dh = dh;
		
		try
		{
			if (umlAbstraction.umlmodel == null)
			{
				// Transformation from scratch
				umlAbstraction.umlmodel = fa.partiallyCreateModel(ontoAbstraction.model);
			}
			else
			{
				// Transformation over a pre-loaded UML.Model
				first = false;
			}
			
			createClasses();
			createAssociations();
			createGeneralizations();
	        dealHistoryTracking();
	        dealTimeTracking();

	        // Adds the PrimitiveTypes (time, duration, boolean) to the UML.Model
	        // I like them at the end of the model, rather than at the beginning
	        addPrimitiveTypes();
	        
	        // Saves the UML.Model in a file
	        umlAbstraction.save();
	        // Saves the Onto<->UML Mappings and Decisions in a file
	        OntoUML2InfoUML.saveMap();
	        
			//if (true) throw new RuntimeException(); // for debug
		}
		catch (Exception e)
		{
			ui.writeText("Could not perform the transformation. A terrible exception has happened.");
			OntoUML2InfoUML.exception();
			e.printStackTrace();
			return null;
		}
		
		printSuccessMessage(first);
        
        return umlAbstraction.umlmodel;
	}
}
