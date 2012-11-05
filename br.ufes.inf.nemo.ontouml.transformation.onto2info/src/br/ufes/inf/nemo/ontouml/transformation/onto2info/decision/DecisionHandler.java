package br.ufes.inf.nemo.ontouml.transformation.onto2info.decision;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import br.ufes.inf.nemo.ontouml.refontouml.util.RefOntoUMLModelAbstraction;
import br.ufes.inf.nemo.ontouml.transformation.onto2info.ui.content.ReferenceModel;
import br.ufes.inf.nemo.ontouml.transformation.onto2info.ui.content.HistoryTimeModel;

public class DecisionHandler
{
	public Map<RefOntoUML.Class, ScopeDecision> scopeMap;
	public Map<RefOntoUML.Class, HistoryDecision> historyMap;
	public Map<RefOntoUML.Class, TimeDecision> timeMap;
	public Map<RefOntoUML.Class, ReferenceDecision> referenceMap;
	
	public Map<RefOntoUML.Class, UMLAttributeSlot> attributeMap;
	
	public DecisionHandler(RefOntoUMLModelAbstraction ma)
	{
    	scopeMap = new HashMap<RefOntoUML.Class, ScopeDecision>();
    	historyMap = new HashMap<RefOntoUML.Class, HistoryDecision>();
    	timeMap = new HashMap<RefOntoUML.Class, TimeDecision>();
    	referenceMap = new HashMap<RefOntoUML.Class, ReferenceDecision>();
    	attributeMap = new HashMap<RefOntoUML.Class, UMLAttributeSlot>();
    	initializeDecisions(ma);
	}
	
	private void initializeDecisions (RefOntoUMLModelAbstraction ontoAbstraction)
	{
		// Scope Decisions (OntoUML.Class)
		for (RefOntoUML.Class c : ontoAbstraction.classes)
		{
			scopeMap.put(c, new ScopeDecision());
		}
		
		// History and Time Tracking Decisions
		HistoryTimeModel htModel = new HistoryTimeModel(ontoAbstraction);
		for (RefOntoUML.Class c : htModel.model)
		{
			historyMap.put(c, new HistoryDecision());
			timeMap.put(c, new TimeDecision());
			attributeMap.put(c, new UMLAttributeSlot());
		}
		
		// Reference Decisions
		ReferenceModel refModel = new ReferenceModel(ontoAbstraction);
		for (RefOntoUML.Class c : refModel.model)
		{
			referenceMap.put(c, new ReferenceDecision());
		}
		
		// TODO: Measurement Decisions
	}
		
	public boolean inScope(RefOntoUML.Classifier c)
	{
		return scopeMap.get(c).scope;
	}
	
	public void setScopeDecision (Object o, boolean value)
	{
		//System.out.println("setting scope to " + value + " -> " + o);
		scopeMap.get(o).scope = value;
	}
	
	public void setStartTimeDecision (RefOntoUML.Class c, boolean value)
	{
		//System.out.println("setting -> " + c.getName() +  " -> " + value);
		timeMap.get(c).start = value;
	}
	
	public boolean getStartTimeDecision (RefOntoUML.Class c)
	{
		return timeMap.get(c).start;
	}
	
	public void setEndTimeDecision (RefOntoUML.Class c, boolean value)
	{
		//System.out.println("setting -> " + c.getName() +  " -> " + value);
		timeMap.get(c).end = value;
	}
	
	public boolean getEndTimeDecision (RefOntoUML.Class c)
	{
		return timeMap.get(c).end;
	}
	
	public void setDurationDecision (RefOntoUML.Class c, boolean value)
	{
		//System.out.println("setting -> " + c.getName() +  " -> " + value);
		timeMap.get(c).duration = value;
	}
	
	public boolean getDurationDecision (RefOntoUML.Class c)
	{
		return timeMap.get(c).duration;
	}

	public void setHTPastDecision (RefOntoUML.Class c, boolean value)
	{
		//System.out.println("HT: past: setting -> " + c.getName() +  " -> " + value);
		historyMap.get(c).past = value;
	}
	
	public boolean getHTPastDecision (RefOntoUML.Class c)
	{
		return historyMap.get(c).past;
	}
	
	public void setHTPresentDecision (RefOntoUML.Class c, boolean value)
	{
		//System.out.println("HT: present: setting -> " + c.getName() +  " -> " + value);
		historyMap.get(c).present = value;
	}
	
	public boolean getHTPresentDecision (RefOntoUML.Class c)
	{
		return historyMap.get(c).present;
	}
	
	public boolean getReferenceDecision (RefOntoUML.Class c)
	{
		return referenceMap.get(c).reference;
	}
	
	public String getReferenceAttributeName (RefOntoUML.Class c)
	{
		return referenceMap.get(c).attributeName;
	}
	
	public AttributeType getReferenceAttributeType (RefOntoUML.Class c)
	{
		return referenceMap.get(c).attributeType;
	}
	
	public String getReferenceTypeName (RefOntoUML.Class c)
	{
		return referenceMap.get(c).typeName;
	}
	
	public void setReferenceDecision (Object o, boolean value)
	{
		//System.out.println("setting reference to " + value + " -> " + o);
		referenceMap.get(o).reference = value;
	}
	
	public void setReferenceAttributeName (Object o, String attributeName)
	{
		referenceMap.get(o).attributeName = attributeName;
	}
	
	public void setReferenceAttributeType (Object o, AttributeType attributeType)
	{
		referenceMap.get(o).setAttributeType(attributeType, ((RefOntoUML.Class)o).getName());
	}
	
	public void setReferenceTypeName (Object o, String typeName)
	{
		referenceMap.get(o).typeName = typeName;
	}
	
	public UMLAttributeSlot getAttributeSlot (RefOntoUML.Class c)
	{
		return attributeMap.get(c);
	}
		
	public void printTimeDecisions ()
	{
		for (Entry<RefOntoUML.Class, TimeDecision> e : timeMap.entrySet())
		{
			System.out.println(
					e.getKey().getName() +
					" " + e.getValue().start +
					" " + e.getValue().end +
					" " + e.getValue().duration);
		}
	}
	
	public void printScopeDecisions ()
	{
		for (Entry<RefOntoUML.Class, ScopeDecision> e : scopeMap.entrySet())
		{
			System.out.println(
					e.getKey().getName() +
					" " + e.getValue().scope);
		}
	}
}
