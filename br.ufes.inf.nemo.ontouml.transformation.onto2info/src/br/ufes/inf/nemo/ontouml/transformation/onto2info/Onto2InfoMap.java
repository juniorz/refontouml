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

import br.ufes.inf.nemo.ontouml.transformation.onto2info.uml.Ref2UMLReplicator;

public class Onto2InfoMap
{	
	// Saves the OntoUML[ID]<->UML[ID] Map in a file
	public static void saveMap (Resource ontoumlResource, Resource umlResource, String fileName)
	{
		// Converts the OntoUML<->UML Map into an ID<->ID Map
		Map<String, String> idMap = convertToIDMap (ontoumlResource, umlResource, Ref2UMLReplicator.mymap);
		
		// Saves the ID<->ID Map into a file
		FileOutputStream fos = null;
		ObjectOutputStream out = null;
		try
		{
			fos = new FileOutputStream(fileName);
			out = new ObjectOutputStream(fos);
			out.writeObject(idMap);
			out.close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	public static Map<String, String> convertToIDMap (Resource ontoumlResource, Resource umlResource, Map<RefOntoUML.Element, org.eclipse.uml2.uml.Element> rMap)
	{
		Map<String, String> idMap = new HashMap<String, String>();
		
		for (Entry<RefOntoUML.Element, org.eclipse.uml2.uml.Element> entry : rMap.entrySet())
		{
			String ontoumlID = getUUID (ontoumlResource, entry.getKey());
			String umlID = getUUID (umlResource, entry.getValue());
			idMap.put(ontoumlID, umlID);
		}
		
		return idMap;
	}
	
	// Loads the OntoUML[ID]<->UML[ID] Map from a file
	@SuppressWarnings("unchecked")
	public static Map<RefOntoUML.Element, org.eclipse.uml2.uml.Element> loadMap (Resource ontoumlResource, Resource umlResource, String fileName)
	{
		Map<String, String> idMap = null;		
		FileInputStream fis = null;
		ObjectInputStream in = null;
		
		// Loads the ID <-> ID Map from file
		try
		{
			fis = new FileInputStream(fileName);
			in = new ObjectInputStream(fis);
			idMap = (Map<String, String>) in.readObject();
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
		Map<RefOntoUML.Element, org.eclipse.uml2.uml.Element> rMap = convertToRealMap (ontoumlResource, umlResource, idMap);
		
		return rMap;
	}
	
	public static Map<RefOntoUML.Element, org.eclipse.uml2.uml.Element> convertToRealMap (Resource ontoumlResource, Resource umlResource, Map<String, String> idMap)
	{
		Map<RefOntoUML.Element, org.eclipse.uml2.uml.Element> rMap = new HashMap<RefOntoUML.Element, org.eclipse.uml2.uml.Element>();
		
		for (Entry<String, String> entry : idMap.entrySet())
		{
			RefOntoUML.Element ontoumlObj = (RefOntoUML.Element) getEObject (ontoumlResource, entry.getKey());
			org.eclipse.uml2.uml.Element umlObj = (org.eclipse.uml2.uml.Element) getEObject (umlResource, entry.getValue());
			rMap.put(ontoumlObj, umlObj);
		}
		
		return rMap;
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
}
