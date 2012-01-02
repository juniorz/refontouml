package br.ufes.inf.nemo.ontouml.transformation.ref2uml;

import java.io.IOException;
import java.io.File;
import java.util.Collections;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;

import org.eclipse.uml2.uml.UMLPackage;
import org.eclipse.uml2.uml.resource.UMLResource;

public class Ref2UML {

	public static Resource Transformation (String refontoumlPath, boolean debug)
	{
	   /** print out the debug code */
	   Dealer.DEBUG = debug;	   
	   	   
	   Resource refontoumlResource = load(refontoumlPath).getResources().get(0);
	   	   
	   EObject model = refontoumlResource.getContents().get(0);    	
	   Resource umlResource = null;   
	   
	   if(model instanceof RefOntoUML.Model)
	   {		   		
		   Transformation t = new Transformation();			  
		   org.eclipse.uml2.uml.Model umlmodel = t.Transform(model);		   		   
		   umlResource = save (refontoumlPath,umlmodel);		   
		}
	   
	    return umlResource;
	}
        
	/** 
	 * Load the RefOntoUML Model to a Resource
	 */	
	public static ResourceSet load (String path) 
	{
		final ResourceSet rset = new ResourceSetImpl();
		
		rset.getResourceFactoryRegistry().getExtensionToFactoryMap().put("refontouml",new XMIResourceFactoryImpl());		
		rset.getPackageRegistry().put(RefOntoUML.RefOntoUMLPackage.eNS_URI, RefOntoUML.RefOntoUMLPackage.eINSTANCE);		
		
		File file = new File(path);
		URI fileURI = URI.createFileURI(file.getAbsolutePath());
		final Resource resource = rset.createResource(fileURI);	
		
		try{
			resource.load(Collections.emptyMap());
		}catch(IOException e){
			e.printStackTrace();
		}
		Dealer.out("\n<refontouml> Loaded: " + resource);		
		
		return rset;
	}	

    /** 
     * Procedure for creating a File
     */	
    public static File createFile (String path) 
    {    	
		File file = new File(path);		
		if (!file.exists()) {			
			try{
				file.createNewFile();
			}catch(IOException e){
				e.printStackTrace();
			}
		}
		return file;
	}
	
	/** 
	 * Save the UML Model to a Resource
	 */	
	public static Resource save (String path, org.eclipse.uml2.uml.Model umlmodel) 
	{
		final ResourceSet rset = new ResourceSetImpl();
		
		rset.getResourceFactoryRegistry().getExtensionToFactoryMap().put(UMLResource.FILE_EXTENSION, UMLResource.Factory.INSTANCE);	
		rset.getPackageRegistry().put(UMLPackage.eNS_URI, UMLPackage.eINSTANCE);			    	
    	
		File file = createFile(path.replace(".refontouml", ".uml"));
    	URI fileURI = URI.createFileURI(file.getAbsolutePath());    	
    	final Resource resource = rset.createResource(fileURI);    	
    	resource.getContents().add(umlmodel);    	
    	
    	try{
    		resource.save(Collections.emptyMap());
    	}catch(IOException e){
    		e.printStackTrace();
    	}
    	Dealer.out("<uml> Saved: " + resource +"\n\n");
    	
    	return resource;
	}		
}
