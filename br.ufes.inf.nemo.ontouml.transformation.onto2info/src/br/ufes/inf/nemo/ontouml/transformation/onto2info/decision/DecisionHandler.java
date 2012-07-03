package br.ufes.inf.nemo.ontouml.transformation.onto2info.decision;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import br.ufes.inf.nemo.ontouml.refontouml.util.RefOntoUMLModelAbstraction;

public class DecisionHandler
{
	public Map<RefOntoUML.Classifier, ScopeDecision> scopeMap;
	public Map<RefOntoUML.Class, HistoryDecision> historyMap;
	public Map<RefOntoUML.Class, TimeDecision> timeMap;
	public Map<RefOntoUML.Class, UMLAttributeSlot> attributeMap;
	
	public DecisionHandler(RefOntoUMLModelAbstraction ma)
	{
    	scopeMap = new HashMap<RefOntoUML.Classifier, ScopeDecision>();
    	historyMap = new HashMap<RefOntoUML.Class, HistoryDecision>();
    	timeMap = new HashMap<RefOntoUML.Class, TimeDecision>();
    	attributeMap = new HashMap<RefOntoUML.Class, UMLAttributeSlot>();
    	initializeDecisions(ma);
	}
	
	private void initializeDecisions (RefOntoUMLModelAbstraction ontoumlmodel)
	{
		// Scope Decisions (OntoUML.Class)
		for (RefOntoUML.Class c : ontoumlmodel.classes)
		{
			scopeMap.put(c, new ScopeDecision());
		}
		
		// TODO: make a list involving both SubstanceSortals and Relators
		// History and Time Tracking Decisions (OntoUML.SubstanceSortal and OntoUML.Relator)
		for (RefOntoUML.SubstanceSortal ss : ontoumlmodel.substanceSortals)
		{
			historyMap.put(ss, new HistoryDecision());
			timeMap.put(ss, new TimeDecision());
			attributeMap.put(ss, new UMLAttributeSlot());
		}
		for (RefOntoUML.Relator r : ontoumlmodel.relators)
		{
			historyMap.put(r, new HistoryDecision());
			timeMap.put(r, new TimeDecision());
			attributeMap.put(r, new UMLAttributeSlot());
		}
		
		// TODO: Reference Decisions
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
		for (Entry<RefOntoUML.Classifier, ScopeDecision> e : scopeMap.entrySet())
		{
			System.out.println(
					e.getKey().getName() +
					" " + e.getValue().scope);
		}
	}
}
