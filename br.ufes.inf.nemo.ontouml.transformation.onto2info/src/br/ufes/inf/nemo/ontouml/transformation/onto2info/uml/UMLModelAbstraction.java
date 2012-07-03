package br.ufes.inf.nemo.ontouml.transformation.onto2info.uml;

import java.io.File;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.uml2.uml.UMLPackage;
import org.eclipse.uml2.uml.resource.UMLResource;

import br.ufes.inf.nemo.ontouml.transformation.onto2info.Onto2InfoMap;

public class UMLModelAbstraction
{
	// Ecore ResourceSet
	ResourceSet resourceSet;
	// Ecore Resource
	public Resource resource;
	// UML Model
	public org.eclipse.uml2.uml.Model umlmodel;
	
	// PrimitiveTypes
	public org.eclipse.uml2.uml.DataType timeType;
	public org.eclipse.uml2.uml.DataType durationType;
	public org.eclipse.uml2.uml.PrimitiveType booleanType;
		
	// UML Factory	
	org.eclipse.uml2.uml.UMLFactory myfactory;
	
	boolean hasFile;
	String fileName;
	
	public UMLModelAbstraction()
	{
		myfactory = org.eclipse.uml2.uml.UMLFactory.eINSTANCE;
		hasFile = false;
		fileName = null;
	}
	
	public void createPrimitiveTypes()
	{
		createTimeType();
		createDurationType();
		createBooleanType();
	}
	
	// Loads a UML.Model from a file
	public boolean load (String fileAbsolutePath)
	{
		// ResourceSet
		resourceSet = new ResourceSetImpl();
		resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put(UMLResource.FILE_EXTENSION, UMLResource.Factory.INSTANCE);	
		resourceSet.getPackageRegistry().put(UMLPackage.eNS_URI, UMLPackage.eINSTANCE);

		// File handling
		fileName = fileAbsolutePath;
		File file = new File(fileName);
		if (!file.isFile())
		{
			hasFile = false;
			System.out.println("The corresponding UML model does not exist: " + file.getAbsolutePath());
		}
		else
		{
			// URI
			URI uri = URI.createFileURI(file.getAbsolutePath());
			
			// Additional things that I'm not sure if I need them
			Map<URI, URI> uriMap = resourceSet.getURIConverter().getURIMap();
			uriMap.put(URI.createURI(UMLResource.LIBRARIES_PATHMAP), uri.appendSegment("libraries").appendSegment(""));
			uriMap.put(URI.createURI(UMLResource.METAMODELS_PATHMAP), uri.appendSegment("metamodels").appendSegment(""));
			uriMap.put(URI.createURI(UMLResource.PROFILES_PATHMAP), uri.appendSegment("profiles").appendSegment(""));
			
			// Resource
			resource = resourceSet.getResource(uri, true);			
			EObject root = resource.getContents().get(0);
			
			if (root instanceof org.eclipse.uml2.uml.Model)
			{
				umlmodel = (org.eclipse.uml2.uml.Model) root;
				return true;
			}
			else
			{
				System.out.println("The root element is not a UML.Model");
			}
		}
		
		return false;
	}
	
	public void save ()
	{		
		if (!hasFile)
		{
			// Create file
			URI uri = URI.createFileURI(new File(fileName).getAbsolutePath());
			// Create Resource
			resource = resourceSet.createResource(uri);			
			// Put the UML.Model in the Resource
			resource.getContents().add(umlmodel);
		}
		
		try
		{
			resource.save(Collections.EMPTY_MAP);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	// Saves the UML model into a file
	public static void saveUMLModel (org.eclipse.uml2.uml.Model umlmodel, String fileAbsolutePath)
	{
		ResourceSet resourceSet = new ResourceSetImpl();
		resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put(UMLResource.FILE_EXTENSION, UMLResource.Factory.INSTANCE);
		resourceSet.getPackageRegistry().put(UMLPackage.eNS_URI, UMLPackage.eINSTANCE);
		
		URI uri = URI.createFileURI(new File(fileAbsolutePath).getAbsolutePath());
		
		Resource resource = resourceSet.createResource(uri);
		resource.getContents().add(umlmodel);
		try
		{
			resource.save(Collections.EMPTY_MAP);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	// Adds a UML.PackageableElement to the UML.Model
	public void addPackageableElement (org.eclipse.uml2.uml.PackageableElement pe)
	{
		umlmodel.getPackagedElements().add(pe);
	}
	
	public boolean hasPackageableElement (org.eclipse.uml2.uml.PackageableElement pe)
	{
		return umlmodel.getPackagedElements().contains(pe);
	}
	
	// Removes a UML.PackageableElement from the UML.Model
	public void removePackageableElement (org.eclipse.uml2.uml.PackageableElement pe)
	{
		umlmodel.getPackagedElements().remove(pe);
	}
	
	// Removes all UML.Generalizations from a UML.Classifier
	public void removeAllGeneralizations (org.eclipse.uml2.uml.Classifier c)
	{
		c.getGeneralizations().clear();
	}
		
	// The DataType that will be referred to by all time attributes
	private void createTimeType ()
	{
		timeType = createDataType("Time");
	}
	
	public org.eclipse.uml2.uml.DataType getTimeType ()
	{
		return timeType;
	}
	
	// The DataType that will be referred to by all duration attributes
	private void createDurationType ()
	{
		durationType = createDataType("Duration");
	}
	
	public org.eclipse.uml2.uml.DataType getDurationType ()
	{
		return durationType;
	}
	
	private void createBooleanType ()
	{
		booleanType = createPrimitiveType("Boolean");
	}
	
	public org.eclipse.uml2.uml.PrimitiveType getBooleanType ()
	{
		return booleanType;
	}
	
	public org.eclipse.uml2.uml.Property addStartTime (RefOntoUML.Class c1)
	{
		return addClassAttribute (c1, "start", timeType, true);
	}
	
	public org.eclipse.uml2.uml.Property addEndTime (RefOntoUML.Class c1)
	{
		return addClassAttribute (c1, "end", timeType, false);
	}
	
	public org.eclipse.uml2.uml.Property addDuration (RefOntoUML.Class c1)
	{
		return addClassAttribute (c1, "duration", durationType, true);
	}
	
	public org.eclipse.uml2.uml.Property addHistoryTrackingAttribute (RefOntoUML.Class c1)
	{
		return addClassAttribute (c1, "current", booleanType, true);
	}
		
	// Set the basic attributes of DataType
	private static void initializeDataType (org.eclipse.uml2.uml.DataType dataType, String name)
	{
		// visibility (Element)
		dataType.setVisibility(org.eclipse.uml2.uml.VisibilityKind.PUBLIC_LITERAL);
		// name (NamedElement)
		dataType.setName(name);
		// isAbstract (Classifier)
		dataType.setIsAbstract(false);
	}
	
	private org.eclipse.uml2.uml.DataType createDataType (String name)
	{
		org.eclipse.uml2.uml.DataType dt = myfactory.createDataType();
		initializeDataType (dt, name);
		return dt;
	}
	
	private org.eclipse.uml2.uml.PrimitiveType createPrimitiveType (String name)
	{
		org.eclipse.uml2.uml.PrimitiveType pt = myfactory.createPrimitiveType();
		initializeDataType (pt, name);
		return pt;
	}
		
	private org.eclipse.uml2.uml.Property addClassAttribute (RefOntoUML.Class c1, String name, org.eclipse.uml2.uml.Type type, boolean isRequired)
	{
		org.eclipse.uml2.uml.Class c2 = (org.eclipse.uml2.uml.Class) Onto2InfoMap.getElement(c1);
		
		org.eclipse.uml2.uml.Property p = myfactory.createProperty();
		
		// name (NamedElement)
		p.setName(name);
		// isLeaf (RedefinableElement)
		p.setIsLeaf(true);
		// isStatic (Feature)
		p.setIsStatic(false);
		// isReadOnly (StructuralFeature)
		p.setIsReadOnly(true);

		// lower, upper (MultiplicityElement)            
		org.eclipse.uml2.uml.LiteralInteger lowerValue = myfactory.createLiteralInteger();
		org.eclipse.uml2.uml.LiteralUnlimitedNatural upperValue = myfactory.createLiteralUnlimitedNatural();
		lowerValue.setValue(isRequired ? 1 : 0);
		upperValue.setValue(1);   
		p.setLowerValue(lowerValue);
		p.setUpperValue(upperValue);

		// Type (TypedElement)
		p.setType(type);

		// isDerived (Property)
		p.setIsDerived(false);        
		// aggregation (Property)
		p.setAggregation(org.eclipse.uml2.uml.AggregationKind.NONE_LITERAL);    
		
		// Linking Class and Property
		c2.getOwnedAttributes().add(p);
		
		return p;
	}
	
	public void removeClassAttribute (RefOntoUML.Class c1, org.eclipse.uml2.uml.Property p)
	{
		org.eclipse.uml2.uml.Class c2 = (org.eclipse.uml2.uml.Class) Onto2InfoMap.getElement(c1);
		c2.getOwnedAttributes().remove(p);
	}
	
	// Return a UML.Generalization between two UML.Classifiers, if it exists
	public org.eclipse.uml2.uml.Generalization getGeneralization (org.eclipse.uml2.uml.Classifier specific, org.eclipse.uml2.uml.Classifier general)
	{
		for (org.eclipse.uml2.uml.Generalization gen : specific.getGeneralizations())
		{
			if (gen.getGeneral() == general)
			{
				return gen;
			}
		}
		
		return null;
	}
	
	public void removeGeneralization (org.eclipse.uml2.uml.Classifier specific2, org.eclipse.uml2.uml.Generalization gen2)
	{
		specific2.getGeneralizations().remove(gen2);
	}
	
	// Created from scratch, no mapping
	public org.eclipse.uml2.uml.Generalization createGeneralization (org.eclipse.uml2.uml.Classifier specific, org.eclipse.uml2.uml.Classifier general)
	{
		org.eclipse.uml2.uml.Generalization gen = myfactory.createGeneralization();
		
		// specific
		gen.setSpecific(specific);
		specific.getGeneralizations().add(gen);		
		// general
		gen.setGeneral(general);

		return gen;
	}
	
	// Created from scratch, no mapping (for RoleMixin generalization only)
	public org.eclipse.uml2.uml.GeneralizationSet createGeneralizationSetForRoleMixin (RefOntoUML.RoleMixin roleMixin, List<org.eclipse.uml2.uml.Generalization> genlist)
	{
		org.eclipse.uml2.uml.GeneralizationSet gset = myfactory.createGeneralizationSet();
		
		// name
		gset.setName("");
		// visibility
		gset.setVisibility(org.eclipse.uml2.uml.VisibilityKind.PUBLIC_LITERAL);
		// isDisjoint
		gset.setIsDisjoint(true);
		// isCovering (not always, only when all rigidSortals are in scope)
		gset.setIsCovering(genlist.size() == roleMixin.rigidSortals().size());
		
		// Linking the GeneralizationSet and the Generalizations
		for (org.eclipse.uml2.uml.Generalization gen : genlist)
		{
			gset.getGeneralizations().add(gen);
			gen.getGeneralizationSets().add(gset);
		}
		
		return gset;
	}
}
