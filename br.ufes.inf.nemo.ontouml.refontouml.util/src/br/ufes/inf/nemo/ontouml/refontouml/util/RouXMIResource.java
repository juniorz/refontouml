package br.ufes.inf.nemo.ontouml.refontouml.util;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceImpl;

public class RouXMIResource extends XMIResourceImpl
{
	public RouXMIResource()
	{
		super();
	}

	public RouXMIResource(URI uri)
	{
		super(uri);
	}

	protected boolean useUUIDs()
	{
		return true;
	}
}
