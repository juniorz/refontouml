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
import br.ufes.inf.nemo.ontouml.transformation.onto2info.decision.UMLAttributeSlotString;

public class Onto2InfoMap
{
	// Maps RefOntoUML Elements to UML Elements (auxiliary for Properties, Generalizations and GeneralizationSets)
	private static HashMap <RefOntoUML.Element,org.eclipse.uml2.uml.Element> mymap;
	
	public static void initializeMap ()
	{
		mymap = new HashMap<RefOntoUML.Element, org.eclipse.uml2.uml.Element>();
	}
	
	// Relate Classifiers and Generalizations
	// This is important for solving Generalizations and Properties (relating Classifiers) and GeneralizationSets (relating Generalizations)
	public static void relateElements (RefOntoUML.Element c1, org.eclipse.uml2.uml.Element c2)
	{
		mymap.put(c1, c2);
	}

	public static org.eclipse.uml2.uml.Element getElement (RefOntoUML.Element e1)
	{
		return mymap.get(e1);
	}
	
	// TODO: do more methods like this
	public static org.eclipse.uml2.uml.Classifier getClassifier (RefOntoUML.Classifier c1)
	{
		return (org.eclipse.uml2.uml.Classifier) getElement(c1);
	}
		
	public static org.eclipse.uml2.uml.Generalization getGeneralization (RefOntoUML.Generalization gen1)
	{
		return (org.eclipse.uml2.uml.Generalization) getElement(gen1);
	}
	
	// This is used for Roles of a RoleMixin
	public static org.eclipse.uml2.uml.Generalization getGeneralization (RefOntoUML.Role role)
	{
		return (org.eclipse.uml2.uml.Generalization) getElement(role);
	}

	public static void removeElement (RefOntoUML.Element e1)
	{
		mymap.remove(e1);
	}
	
	// Saves the OntoUML[ID]<->UML[ID] Map in a file
	public static void saveMap (Resource ontoumlResource, Resource umlResource, String fileName, DecisionHandler dh)
	{
		// Converts the OntoUML<->UML Map into an ID<->ID Map
		Map<String, String> idMap = convertToIDMap (ontoumlResource, umlResource);
		Map<String, ScopeDecision> scopeMap = dh.convertScopeMap (ontoumlResource, umlResource);
		Map<String, HistoryDecision> historyMap = dh.convertHistoryMap (ontoumlResource, umlResource);
		Map<String, TimeDecision> timeMap = dh.convertTimeMap (ontoumlResource, umlResource);
		Map<String, UMLAttributeSlotString> attributeMap = dh.convertAttributeMap (ontoumlResource, umlResource);
		
		// Saves the ID<->ID Map into a file
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
			out.close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	public static Map<String, String> convertToIDMap (Resource ontoumlResource, Resource umlResource)
	{
		Map<String, String> idMap = new HashMap<String, String>();
		
		for (Entry<RefOntoUML.Element, org.eclipse.uml2.uml.Element> entry : mymap.entrySet())
		{
			String ontoumlID = getUUID (ontoumlResource, entry.getKey());
			String umlID = getUUID (umlResource, entry.getValue());
			idMap.put(ontoumlID, umlID);
		}
		
		return idMap;
	}
	
	// Loads the OntoUML[ID]<->UML[ID] Map from a file
	@SuppressWarnings("unchecked")
	public static void loadMap (Resource ontoumlResource, Resource umlResource, String fileName, DecisionHandler dh)
	{
		Map<String, String> idMap = null;
		Map<String, ScopeDecision> scopeMap = null;
		Map<String, HistoryDecision> historyMap = null;
		Map<String, TimeDecision> timeMap = null;
		Map<String, UMLAttributeSlotString> attributeMap = null;
				
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
		convertToRealMap (ontoumlResource, umlResource, idMap);
		dh.convertToRealScopeMap(ontoumlResource, umlResource, scopeMap);
		dh.convertToRealHistoryMap(ontoumlResource, umlResource, historyMap);
		dh.convertToRealTimeMap(ontoumlResource, umlResource, timeMap);
		dh.convertToRealAttributeMap(ontoumlResource, umlResource, attributeMap);
	}
	
	public static void convertToRealMap (Resource ontoumlResource, Resource umlResource, Map<String, String> idMap)
	{
		initializeMap();
		
		for (Entry<String, String> entry : idMap.entrySet())
		{
			RefOntoUML.Element ontoumlObj = (RefOntoUML.Element) getEObject (ontoumlResource, entry.getKey());
			org.eclipse.uml2.uml.Element umlObj = (org.eclipse.uml2.uml.Element) getEObject (umlResource, entry.getValue());
			mymap.put(ontoumlObj, umlObj);
		}
	}

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
	
	public static void printMap ()
	{
		for (Entry<RefOntoUML.Element, org.eclipse.uml2.uml.Element> entry : mymap.entrySet())
		{
			System.out.println(entry.getKey());	
			System.out.println(entry.getValue());
			System.out.println();
		}
	}
}
