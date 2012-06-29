package br.ufes.inf.nemo.ontouml.refontouml.util;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;

public class RouXMIResourceFactory extends XMIResourceFactoryImpl
{
	public RouXMIResourceFactory()
	{
		super();
	}

	public Resource createResource(URI uri)
	{
		return new RouXMIResource(uri);
	}
}
