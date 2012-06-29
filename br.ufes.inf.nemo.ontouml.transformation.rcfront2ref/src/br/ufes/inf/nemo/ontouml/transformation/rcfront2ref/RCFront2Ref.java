package br.ufes.inf.nemo.ontouml.transformation.rcfront2ref;

import java.io.File;
import java.util.Collections;
import java.util.Map;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import br.ufes.inf.nemo.ontouml.refontouml.util.RouXMIResourceFactory;

// add the library org.eclipse.uml2.uml and org.eclipse.uml2.common to the build path
import org.eclipse.uml2.uml.UMLPackage;
import org.eclipse.uml2.uml.resource.UMLResource;

public class RCFront2Ref
{
	public static void main (String[] args)
	{
		Transformation (args[0]);
	}
	
	public static void Transformation (String fileName)
	{		
		// Configure ResourceSet
		ResourceSet sourceRS = new ResourceSetImpl();
		sourceRS.getResourceFactoryRegistry().getExtensionToFactoryMap().put(UMLResource.FILE_EXTENSION, UMLResource.Factory.INSTANCE);	
		sourceRS.getPackageRegistry().put(UMLPackage.eNS_URI, UMLPackage.eINSTANCE);

		// Open the model
		File sourceFile = new File(fileName);
		if (!sourceFile.isFile())
		{
			System.out.println("Error accessing: " + sourceFile.getAbsolutePath());
			return;
		}		
		URI uri = URI.createFileURI(sourceFile.getAbsolutePath());
		
		Map<URI, URI> uriMap = sourceRS.getURIConverter().getURIMap();
		uriMap.put(URI.createURI(UMLResource.LIBRARIES_PATHMAP), uri.appendSegment("libraries").appendSegment(""));
		uriMap.put(URI.createURI(UMLResource.METAMODELS_PATHMAP), uri.appendSegment("metamodels").appendSegment(""));
		uriMap.put(URI.createURI(UMLResource.PROFILES_PATHMAP), uri.appendSegment("profiles").appendSegment(""));
		
		Transformation t = new Transformation();
		RefOntoUML.Model p2 = null;
		
		try
		{
			// Read the objects in the model
			Resource sourceR = sourceRS.getResource(uri, true);			
			EObject p1 = sourceR.getContents().get(0);
			
			if (p1 instanceof org.eclipse.uml2.uml.Package)
				p2 = t.createModel((org.eclipse.uml2.uml.Package)p1);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			System.exit(0);
		}
		
		// Save the model into a file
		ResourceSet targetRS = new ResourceSetImpl();
		targetRS.getResourceFactoryRegistry().getExtensionToFactoryMap().put(Resource.Factory.Registry.DEFAULT_EXTENSION, new RouXMIResourceFactory());
		URI targetURI = URI.createFileURI(new File(fileName.replace(".uml", ".refontouml")).getAbsolutePath());
		Resource targetR = targetRS.createResource(targetURI);
		
		targetR.getContents().add(p2);

		try
		{
			targetR.save(Collections.EMPTY_MAP);
		}
		catch (Exception e) {}
		
		System.out.println("Done.");
	}
}
