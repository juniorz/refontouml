package br.ufes.inf.nemo.ontouml.transformation.abfront2ref;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;

import br.ufes.inf.nemo.ontouml.refontouml.util.RouXMIResourceFactory;

import java.io.File;
import java.util.Collections;

public class ABFront2Ref
{
	public static void Transformation (String fileName)
	{
		// Configure ResourceSet
		ResourceSet resourceSet = new ResourceSetImpl();
		resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put(Resource.Factory.Registry.DEFAULT_EXTENSION, new XMIResourceFactoryImpl());
		resourceSet.getPackageRegistry().put(OntoUML.OntoUMLPackage.eNS_URI,OntoUML.OntoUMLPackage.eINSTANCE);
				
		// Open the model
		File sourceFile = new File(fileName);
		if (!sourceFile.isFile())
		{
			System.out.println("Error accessing: " + sourceFile.getAbsolutePath());
			System.exit(1);
		}		
		URI uri = URI.createFileURI(sourceFile.getAbsolutePath());
		
		Transformation t = new Transformation();
		
		try
		{
			// Read the objects in the model
			Resource resource = resourceSet.getResource(uri, true);
			EObject tudo = resource.getContents().get(0);
			
			for (EObject obj : tudo.eContents())
			{
				t.ProcessEObject(obj);
			}

			t.ProcessAll();
		}				
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		// Save the model into a file
		ResourceSet rs = new ResourceSetImpl();
		rs.getResourceFactoryRegistry().getExtensionToFactoryMap().put(Resource.Factory.Registry.DEFAULT_EXTENSION, new RouXMIResourceFactory());
		URI fileURI = URI.createFileURI(new File(fileName.replace(".ontouml", ".refontouml")).getAbsolutePath());
		Resource r = rs.createResource(fileURI);
		
		r.getContents().add(t.getModel());
				
		try
		{
			r.save(Collections.EMPTY_MAP);
		}
		catch (Exception e) {}
		
		System.out.println("Done.");
	}
}
