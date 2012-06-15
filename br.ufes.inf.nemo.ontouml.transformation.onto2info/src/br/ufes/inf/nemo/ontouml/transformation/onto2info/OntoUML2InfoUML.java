package br.ufes.inf.nemo.ontouml.transformation.onto2info;

import java.io.File;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import br.ufes.inf.nemo.ontouml.refontouml.util.*;

public class OntoUML2InfoUML
{
	public static void main(String[] args)
	{
		
	}
	
	public static void Transformation (String fileAbsolutePath)
	{
		RefOntoUMLModelAbstraction ma = new RefOntoUMLModelAbstraction();
		if (!ma.load(fileAbsolutePath))
			return;
	}
	
	/*public static RefOntoUML.Model getModel (String fileAbsolutePath)
	{
		// Configure ResourceSet
		ResourceSet resourceSet = new ResourceSetImpl();
		resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put(Resource.Factory.Registry.DEFAULT_EXTENSION, new XMIResourceFactoryImpl());
		resourceSet.getPackageRegistry().put(RefOntoUML.RefOntoUMLPackage.eNS_URI,RefOntoUML.RefOntoUMLPackage.eINSTANCE);
				
		// Open the model
		File sourceFile = new File(fileAbsolutePath);
		if (!sourceFile.isFile())
		{
			System.out.println("Error accessing: " + sourceFile.getAbsolutePath());
			return null;
		}		
		URI uri = URI.createFileURI(sourceFile.getAbsolutePath());

		Resource resource = resourceSet.getResource(uri, true);
		EObject root = resource.getContents().get(0);
		
		if (root instanceof RefOntoUML.Model)
		{
			return (RefOntoUML.Model) root;
		}
		
		return null;
	}*/
}
