package br.ufes.inf.nemo.ontouml.transformation.ontouml2sbvr;

import java.io.File;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;

import RefOntoUML.*;
import RefOntoUML.Class;
import RefOntoUML.Package;

public class Transformation
{
	FileManager myfile;
	TreeProcessor myprocessor;
	
	public Transformation (File sourceFile)
	{
		myfile = new FileManager(sourceFile);
		myprocessor = new TreeProcessor();
	}
	
	public void Transform (EObject o, boolean serial)
	{		
		if (!(o instanceof Package))
			return;
		
		Package p = (Package) o;
			
		myfile.serial = serial;
		
		Tree(p);
		
		myfile.Done();
	}
		
	public void Tree (Package p)
	{
		// Pre Process all classes
		for (PackageableElement pe : p.getPackagedElement())
		{			
			if (pe instanceof Class)
			{
				myprocessor.ProcessClass((Class) pe);
			}
		}
		
		// Process all associations
		for (PackageableElement pe : p.getPackagedElement())
		{			
			if (pe instanceof Association)
			{
				myprocessor.ProcessAssociation((Association) pe);
			}
		}
		
		// Set up the specialization tree
		myprocessor.ProcessNodes();
		
		// Deal the main nodes
		List<Node> mainNodes = myprocessor.getMainNodes();
		for (Node n : mainNodes)
		{
			myfile.DealNode(n, !myfile.serial);
		}
		
		// Deal the DataTypes
		for (PackageableElement pe : p.getPackagedElement())
		{			
			if (pe instanceof DataType)
			{
				myfile.DealDataType((DataType) pe);
			}
		}
		
		// Deal Association Roles
		for (Map.Entry<String, Classifier> entry : myprocessor.getAssociationRoles().entrySet())
		{
			myfile.DealAssociationRole(entry.getKey(), entry.getValue());
		}
	}
}
