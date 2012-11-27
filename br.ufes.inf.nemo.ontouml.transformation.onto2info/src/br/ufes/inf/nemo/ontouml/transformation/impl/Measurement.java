package br.ufes.inf.nemo.ontouml.transformation.impl;

import java.util.Map.Entry;

import br.ufes.inf.nemo.ontouml.transformation.onto2info.Onto2InfoMap;
import br.ufes.inf.nemo.ontouml.transformation.onto2info.decision.MeasurementDecision;
import br.ufes.inf.nemo.ontouml.transformation.onto2info.decision.UMLAttributeSlot;

public class Measurement
{
	Transformation main;
	
	public Measurement (Transformation t)
	{
		this.main = t;
	}
	
	// TODO: history and time tracking
	public void dealMeasurement ()
	{
		for (Entry<RefOntoUML.Class, MeasurementDecision> entry : main.dh.measurementMap.entrySet())
		{
			// Quality
			RefOntoUML.Quality q1 = (RefOntoUML.Quality) entry.getKey();
			// Characterized Universal
			RefOntoUML.Class c1 = (RefOntoUML.Class) q1.characterized();
			
			MeasurementDecision decision = entry.getValue();
			UMLAttributeSlot slot = main.dh.getAttributeSlot(q1);
			
			if (main.dh.inScope(c1))
			{
				// Characterized Universal inside the scope				
				if (main.dh.inScope(q1))
				{
					// Quality inside the scope
					qualityInScope(q1, c1, decision, slot);
				}
				else
				{
					// Quality outside the scope
					cIn_qOut(slot);
				}
			}
			else
			{
				// Characterized Universal outside the scope
				cOut(slot);
			}
		}
	}
	
	private void qualityInScope (RefOntoUML.Quality q1, RefOntoUML.Class c1, MeasurementDecision decision, UMLAttributeSlot slot)
	{
		if (slot.measurementAttribute == null)
		{
			// Measurement attribute does NOT exist
			
			if (main.dh.getHTPastDecision(q1))
			{
				// History Tracking = true
				dealHistoryTracking(q1, c1, decision, slot);
			}
			else
			{
				// History Tracking = false
				// Measurement attribute goes to the "Characterized Type"
				addAttributeToCharacterizedType(q1, c1, decision, slot);
			}
		}
		else
		{
			// Measurement attribute exists
			
			// Check if the attribute has changed
			org.eclipse.uml2.uml.Type type = main.umlAbstraction.getMeasurementType (decision);
			org.eclipse.uml2.uml.Type pastType = slot.measurementAttribute.getType();
								
			// If the past type was Custom
			if (main.umlAbstraction.isCustomType(pastType))
			{
				// Simply delete the past CustomType (even if CustomType is chosen again FIXME)
				main.umlAbstraction.removePackageableElement(slot.measurementAttribute.getType());
			}
				
			if (pastType != type)
			{
				// A change of type happened	
				// Update the type
				slot.measurementAttribute.setType(type);
				
				// TODO: display in the ui that a change happened
			}
		}
	}
	
	private void addAttributeToCharacterizedType (RefOntoUML.Quality q1, RefOntoUML.Class c1, MeasurementDecision decision, UMLAttributeSlot slot)
	{
		slot.measurementAttribute = main.umlAbstraction.addMeasurementAttribute(q1, (org.eclipse.uml2.uml.Class) Onto2InfoMap.getElement(c1), decision);
		main.ui.writeLog("Created UML.Property for " + c1.getName() + ": " + slot.measurementAttribute.getName());
		Log.addition();
	}
	
	// TODO
	private void dealHistoryTracking(RefOntoUML.Quality q1, RefOntoUML.Class c1, MeasurementDecision decision, UMLAttributeSlot slot)
	{
		if (slot.measureType == null)
		{
			// create "Measure Type"
			slot.measureType = main.umlAbstraction.createMeasureType(q1.getName() + " Measure");
			// add the "Measurement Attribute" to the "Measure type"
			slot.measurementAttribute = main.umlAbstraction.addMeasurementAttribute(q1, slot.measureType, decision);
			// create "Measure Association" (between the "Characterized Type" and the "Measure Type")
			main.umlAbstraction.createMeasureAssociation((org.eclipse.uml2.uml.Class)Onto2InfoMap.getElement(c1), slot.measureType);
			
			if (main.dh.getStartTimeDecision(q1))
			{
				dealTimeTracking(slot);
			}
		}
		else
		{
			// "Measure Type" already exists
			// TODO
		}
	}
	
	private void dealTimeTracking(UMLAttributeSlot slot)
	{
		if (slot.startAttribute == null)
		{
			// create "Time Attribute"
			main.umlAbstraction.addClassAttribute (slot.measureType, "time", main.umlAbstraction.timeType, true);
		}
	}
	
	private void cIn_qOut (UMLAttributeSlot slot)
	{
		if (slot.measurementAttribute != null)
		{
			// The "Characterized Type" remains...
			
			// Remove the measurement attribute from the "Characterized Type"
			main.umlAbstraction.removeClassAttribute(slot.measurementAttribute);
			
			// Clear the UMLAttributeSlot
			slot.measurementAttribute = null;
			main.ui.writeLog("Removed UML.Property " + "(Measurement)");
			Log.removal();
		}
	}
	
	private void cOut (UMLAttributeSlot slot)
	{
		if (slot.measurementAttribute != null)
		{			
			// Clear the UMLAttributeSlot
			slot.measurementAttribute = null;
			main.ui.writeLog("Removed UML.Property " + "(Measurement)");
			Log.removal();
		}
	}
}
