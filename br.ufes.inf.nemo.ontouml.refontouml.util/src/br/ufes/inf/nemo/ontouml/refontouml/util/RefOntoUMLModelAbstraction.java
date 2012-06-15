package br.ufes.inf.nemo.ontouml.refontouml.util;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;

public class RefOntoUMLModelAbstraction
{
	public RefOntoUML.Model model;
	
	public List<RefOntoUML.Kind> kinds;
	public List<RefOntoUML.Quantity> quantity;
	public List<RefOntoUML.Collective> collective;
	public List<RefOntoUML.SubKind> subKinds;
	
	public List<RefOntoUML.Role> roles;
	public List<RefOntoUML.Phase> phases;
	
	public List<RefOntoUML.Category> categories;
	public List<RefOntoUML.Mixin> semiMixins;
	public List<RefOntoUML.RoleMixin> roleMixins;
	
	

	public RefOntoUMLModelAbstraction ()
	{
		
	}
	
	public boolean load (String fileAbsolutePath)
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
		}
		else
		{
			URI uri = URI.createFileURI(sourceFile.getAbsolutePath());
	
			Resource resource = resourceSet.getResource(uri, true);
			EObject root = resource.getContents().get(0);
			
			if (root instanceof RefOntoUML.Model)
			{
				model = (RefOntoUML.Model) root;
				return true;
			}
		}
		
		model = null;
		return false;
	}
}
