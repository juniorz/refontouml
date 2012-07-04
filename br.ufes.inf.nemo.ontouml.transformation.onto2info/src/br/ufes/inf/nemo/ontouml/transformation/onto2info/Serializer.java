package br.ufes.inf.nemo.ontouml.transformation.onto2info;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;

import br.ufes.inf.nemo.ontouml.transformation.onto2info.decision.DecisionHandler;
import br.ufes.inf.nemo.ontouml.transformation.onto2info.decision.HistoryDecision;
import br.ufes.inf.nemo.ontouml.transformation.onto2info.decision.ScopeDecision;
import br.ufes.inf.nemo.ontouml.transformation.onto2info.decision.TimeDecision;
import br.ufes.inf.nemo.ontouml.transformation.onto2info.decision.UMLAttributeSlot;
import br.ufes.inf.nemo.ontouml.transformation.onto2info.decision.UMLAttributeSlotString;
import br.ufes.inf.nemo.ontouml.transformation.onto2info.uml.UMLModelAbstraction;

public class Serializer
{
	// Gets the UUID of an EObject, given its Resource
	public static String getUUID (Resource resource, EObject element)
	{
		return resource.getURIFragment(element);
		// Could have done: element.eResource().getURIFragment(element)
	}

	// Gets an EObject by UUID, given its Resource
	public static EObject getEObject (Resource resource, String uuid)
	{
		return resource.getEObject(uuid);
		// Could have done something like: model.eResource().getEObject(uuid)
	}
	
	// Saves the Maps for OntoUML->UML, OntoUML->Decisions and OntoUML->UMLAttributes
	public static void saveMap (Resource ontoumlResource, Resource umlResource, String fileName, DecisionHandler dh, UMLModelAbstraction umlAbstraction)
	{
		// Converts the OntoUML<->UML Map into an ID<->ID Map
		Map<String, String> idMap = convertOntoInfoMap (ontoumlResource, umlResource);
		
		// Converts the OntoUML<->ScopeDecision Map into an ID<->ScopeDecision Map
		Map<String, ScopeDecision> scopeMap = convertScopeMap (ontoumlResource, dh);
		// Converts the OntoUML<->HistoryDecision Map into an ID<->HistoryDecision Map
		Map<String, HistoryDecision> historyMap = convertHistoryMap (ontoumlResource, dh);
		// Converts the OntoUML<->TimeDecision Map into an ID<->TimeDecision Map
		Map<String, TimeDecision> timeMap = convertTimeMap (ontoumlResource, dh);
		
		// Converts the OntoUML<->UMLAttributeSlot Map into an ID<->UMLAttributeSlotString Map
		Map<String, UMLAttributeSlotString> attributeMap = convertAttributeMap (ontoumlResource, umlResource, dh);
		
		String timePrimitiveStr = getUUID (umlResource, umlAbstraction.getTimeType());
		String durationPrimitiveStr = getUUID (umlResource, umlAbstraction.getDurationType());
		String booleanPrimitiveStr = getUUID (umlResource, umlAbstraction.getBooleanType());
		
		// Saves the fake Maps into a file
		FileOutputStream fos = null;
		ObjectOutputStream out = null;
		try
		{
			fos = new FileOutputStream(fileName);
			out = new ObjectOutputStream(fos);
			out.writeObject(idMap);
			out.writeObject(scopeMap);
			out.writeObject(historyMap);
			out.writeObject(timeMap);
			out.writeObject(attributeMap);
			out.writeObject(timePrimitiveStr);
			out.writeObject(durationPrimitiveStr);
			out.writeObject(booleanPrimitiveStr);
			out.close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	public static Map<String, String> convertOntoInfoMap (Resource ontoumlResource, Resource umlResource)
	{
		Map<String, String> idMap = new HashMap<String, String>();
		
		for (Entry<RefOntoUML.Element, org.eclipse.uml2.uml.Element> entry : Onto2InfoMap.mymap.entrySet())
		{
			// start debug FIXME
			// This is only provisory, unitl I remove the garbage
			if (entry.getKey() == null || entry.getValue() == null)
			{
				System.out.println("Something is null here!");
				System.out.println(entry.getKey());
				System.out.println(entry.getValue());
				if (entry.getKey() instanceof RefOntoUML.Property)
				{
					RefOntoUML.Property p = (RefOntoUML.Property) entry.getKey();
					if (p.getName().length() > 0) System.out.println(p.getName());
					System.out.println(p.getType());
				}
				System.out.println();
			}
			// end debug FIXME
			else
			{
				String ontoumlID = getUUID (ontoumlResource, entry.getKey());
				String umlID = getUUID (umlResource, entry.getValue());
				idMap.put(ontoumlID, umlID);
			}
		}
		
		return idMap;
	}
	
	public static Map<String, ScopeDecision> convertScopeMap (Resource ontoumlResource, DecisionHandler dh)
	{
		Map<String, ScopeDecision> idMap = new HashMap<String, ScopeDecision>();
		
		for (Entry<RefOntoUML.Classifier, ScopeDecision> entry : dh.scopeMap.entrySet())
		{
			String ontoumlID = getUUID (ontoumlResource, entry.getKey());
			idMap.put(ontoumlID, entry.getValue());
		}
		
		return idMap;
	}
	
	public static Map<String, HistoryDecision> convertHistoryMap (Resource ontoumlResource, DecisionHandler dh)
	{
		Map<String, HistoryDecision> idMap = new HashMap<String, HistoryDecision>();
		
		for (Entry<RefOntoUML.Class, HistoryDecision> entry : dh.historyMap.entrySet())
		{
			String ontoumlID = getUUID (ontoumlResource, entry.getKey());
			idMap.put(ontoumlID, entry.getValue());
		}
		
		return idMap;
	}
	
	public static Map<String, TimeDecision> convertTimeMap (Resource ontoumlResource, DecisionHandler dh)
	{
		Map<String, TimeDecision> idMap = new HashMap<String, TimeDecision>();
		
		for (Entry<RefOntoUML.Class, TimeDecision> entry : dh.timeMap.entrySet())
		{
			String ontoumlID = getUUID (ontoumlResource, entry.getKey());
			idMap.put(ontoumlID, entry.getValue());
		}
		
		return idMap;
	}
	
	public static Map<String, UMLAttributeSlotString> convertAttributeMap (Resource ontoumlResource, Resource umlResource, DecisionHandler dh)
	{
		Map<String, UMLAttributeSlotString> idMap = new HashMap<String, UMLAttributeSlotString>();
		
		for (Entry<RefOntoUML.Class, UMLAttributeSlot> entry : dh.attributeMap.entrySet())
		{
			String ontoumlID = getUUID (ontoumlResource, entry.getKey());
			
			UMLAttributeSlot slot = entry.getValue();
			UMLAttributeSlotString stringSlot = new UMLAttributeSlotString();
			
			if (slot.startAttribute != null)
				stringSlot.startAttribute = getUUID (umlResource, slot.startAttribute);
			else
				stringSlot.startAttribute = null;
			
			if (slot.endAttribute != null)
				stringSlot.endAttribute = getUUID (umlResource, slot.endAttribute);
			else
				stringSlot.endAttribute = null;
			
			if (slot.durationAttribute != null)
				stringSlot.durationAttribute = getUUID (umlResource, slot.durationAttribute);
			else
				stringSlot.durationAttribute = null;
			
			if (slot.htAttribute != null)
				stringSlot.htAttribute = getUUID (umlResource, slot.htAttribute);
			else
				stringSlot.htAttribute = null;
			
			idMap.put(ontoumlID, stringSlot);
		}
		
		return idMap;
	}
	
	
	
	
	
	
	
	
	// Loads the OntoUML[ID]<->UML[ID] Map from a file
	@SuppressWarnings("unchecked")
	public static void loadMap (Resource ontoumlResource, Resource umlResource, String fileName, DecisionHandler dh, UMLModelAbstraction umlAbstraction)
	{
		Map<String, String> idMap = null;
		Map<String, ScopeDecision> scopeMap = null;
		Map<String, HistoryDecision> historyMap = null;
		Map<String, TimeDecision> timeMap = null;
		Map<String, UMLAttributeSlotString> attributeMap = null;
		String timePrimitiveStr = null;
		String durationPrimitiveStr = null;
		String booleanPrimitiveStr = null;
				
		FileInputStream fis = null;
		ObjectInputStream in = null;
		
		// Loads the ID <-> ID Map from file
		try
		{
			fis = new FileInputStream(fileName);
			in = new ObjectInputStream(fis);
			
			idMap = (Map<String, String>) in.readObject();
			scopeMap = (Map<String, ScopeDecision>) in.readObject();
			historyMap = (Map<String, HistoryDecision>) in.readObject();
			timeMap = (Map<String, TimeDecision>) in.readObject();
			attributeMap = (Map<String, UMLAttributeSlotString>) in.readObject();
			timePrimitiveStr = (String) in.readObject();
			durationPrimitiveStr = (String) in.readObject();
			booleanPrimitiveStr = (String) in.readObject();
			
			in.close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		catch (ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		
		// Convert it to RefOntoUML.Element <-> UML.Element Map
		loadOntoInfoMap (ontoumlResource, umlResource, idMap);
		loadScopeMap(ontoumlResource, scopeMap, dh);
		loadHistoryMap(ontoumlResource, historyMap, dh);
		loadTimeMap(ontoumlResource, timeMap, dh);
		loadAttributeMap(ontoumlResource, umlResource, attributeMap, dh);
		
		umlAbstraction.timeType = (org.eclipse.uml2.uml.DataType) getEObject(umlResource, timePrimitiveStr);
		umlAbstraction.durationType = (org.eclipse.uml2.uml.DataType) getEObject(umlResource, durationPrimitiveStr);
		umlAbstraction.booleanType = (org.eclipse.uml2.uml.PrimitiveType) getEObject(umlResource, booleanPrimitiveStr);
	}
	
	public static void loadOntoInfoMap (Resource ontoumlResource, Resource umlResource, Map<String, String> idMap)
	{
		Onto2InfoMap.initializeMap();
		
		for (Entry<String, String> entry : idMap.entrySet())
		{
			RefOntoUML.Element ontoumlObj = (RefOntoUML.Element) getEObject (ontoumlResource, entry.getKey());
			org.eclipse.uml2.uml.Element umlObj = (org.eclipse.uml2.uml.Element) getEObject (umlResource, entry.getValue());
			Onto2InfoMap.mymap.put(ontoumlObj, umlObj);
		}
	}	
	
	public static void loadScopeMap (Resource ontoumlResource, Map<String, ScopeDecision> idMap, DecisionHandler dh)
	{
		dh.scopeMap = new HashMap<RefOntoUML.Classifier, ScopeDecision>();
		
		for (Entry<String, ScopeDecision> entry : idMap.entrySet())
		{
			RefOntoUML.Classifier ontoumlObj = (RefOntoUML.Classifier) getEObject (ontoumlResource, entry.getKey());
			dh.scopeMap.put(ontoumlObj, entry.getValue());
		}
	}
	
	public static void loadHistoryMap (Resource ontoumlResource, Map<String, HistoryDecision> idMap, DecisionHandler dh)
	{
		dh.historyMap = new HashMap<RefOntoUML.Class, HistoryDecision>();
		
		for (Entry<String, HistoryDecision> entry : idMap.entrySet())
		{
			RefOntoUML.Class ontoumlObj = (RefOntoUML.Class) getEObject (ontoumlResource, entry.getKey());
			dh.historyMap.put(ontoumlObj, entry.getValue());
		}
	}
	
	public static void loadTimeMap (Resource ontoumlResource, Map<String, TimeDecision> idMap, DecisionHandler dh)
	{
		dh.timeMap = new HashMap<RefOntoUML.Class, TimeDecision>();
		
		for (Entry<String, TimeDecision> entry : idMap.entrySet())
		{
			RefOntoUML.Class ontoumlObj = (RefOntoUML.Class) getEObject (ontoumlResource, entry.getKey());
			dh.timeMap.put(ontoumlObj, entry.getValue());
		}
	}
	
	public static void loadAttributeMap (Resource ontoumlResource, Resource umlResource, Map<String, UMLAttributeSlotString> idMap, DecisionHandler dh)
	{
		dh.attributeMap = new HashMap<RefOntoUML.Class, UMLAttributeSlot>();
		
		for (Entry<String, UMLAttributeSlotString> entry : idMap.entrySet())
		{
			RefOntoUML.Class ontoumlObj = (RefOntoUML.Class) getEObject (ontoumlResource, entry.getKey());
			
			UMLAttributeSlotString stringSlot = entry.getValue();
			UMLAttributeSlot slot = new UMLAttributeSlot();
			
			if (stringSlot.startAttribute != null)
				slot.startAttribute = (org.eclipse.uml2.uml.Property) getEObject(umlResource, stringSlot.startAttribute);
			
			if (stringSlot.endAttribute != null)
				slot.endAttribute = (org.eclipse.uml2.uml.Property) getEObject(umlResource, stringSlot.endAttribute);
			
			if (stringSlot.durationAttribute != null)
				slot.durationAttribute = (org.eclipse.uml2.uml.Property) getEObject(umlResource, stringSlot.durationAttribute);
			
			if (stringSlot.htAttribute != null)
				slot.htAttribute = (org.eclipse.uml2.uml.Property) getEObject(umlResource, stringSlot.htAttribute);
			
			dh.attributeMap.put(ontoumlObj, slot);
		}
	}	
}
