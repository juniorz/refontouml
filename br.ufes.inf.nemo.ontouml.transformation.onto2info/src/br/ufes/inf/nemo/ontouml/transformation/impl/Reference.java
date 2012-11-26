package br.ufes.inf.nemo.ontouml.transformation.impl;

import java.util.Map.Entry;

import br.ufes.inf.nemo.ontouml.transformation.onto2info.decision.ReferenceDecision;
import br.ufes.inf.nemo.ontouml.transformation.onto2info.decision.UMLAttributeSlot;

public class Reference
{
	Transformation main;
	
	public Reference (Transformation t)
	{
		this.main = t;
	}
	
	public void dealReference ()
	{
		// Reference
		for (Entry<RefOntoUML.Class, ReferenceDecision> entry : main.dh.referenceMap.entrySet())
		{
			RefOntoUML.Class c1 = entry.getKey();
			ReferenceDecision decision = entry.getValue();
			UMLAttributeSlot slot = main.dh.getAttributeSlot(c1);
			
			if (main.dh.inScope(c1))
			{
				// Reference decision
				if (decision.reference)
				{
					// Reference = true
					if (slot.refAttribute == null)
					{
						// UML.Property (Attribute) does not exist
	        			slot.refAttribute = main.umlAbstraction.addReferenceAttribute(c1, decision);
	        			
	        			main.ui.writeLog("Created UML.Property for " + c1.getName() + ": " + slot.refAttribute.getName());
	        			main.numAdditions++;
					}
					else
					{
						// UML.Property (Attribute) exits
						// FIXME: when attribute has changed type
					}
				}
				else
				{
					// Reference = false
					if (slot.refAttribute != null)
					{
	        			// UML.Property (Attribute) exists
	        			// Remove it from the UML.Class
	        			main.umlAbstraction.removeClassAttribute(c1, slot.refAttribute);
	        			// Remove it from the UMLAttributeSlot
	        			slot.refAttribute = null;
	        			
	        			main.ui.writeLog("Removed UML.Property for " + c1.getName() + " (Reference)");
	        			main.numRemovals++;
					}
				}
			}
			else
			{
        		// OntoUML.Class out of scope
        		// UML.Class was already removed, but...
        		if (slot.refAttribute != null)
        		{
        			// Clear the UMLAttributeSlot
        			slot.refAttribute = null;
        			main.ui.writeLog("Removed UML.Property for " + c1.getName() + " (Reference)");
        			main.numRemovals++;
        		}
			}
		}
	}
}
