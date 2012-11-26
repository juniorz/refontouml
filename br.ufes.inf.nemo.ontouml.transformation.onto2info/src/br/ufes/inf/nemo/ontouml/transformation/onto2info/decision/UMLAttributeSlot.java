package br.ufes.inf.nemo.ontouml.transformation.onto2info.decision;

public class UMLAttributeSlot
{
	public org.eclipse.uml2.uml.Property startAttribute;
	public org.eclipse.uml2.uml.Property endAttribute;
	public org.eclipse.uml2.uml.Property durationAttribute;
	// current : Boolean [1,1] attribute
	public org.eclipse.uml2.uml.Property htAttribute;
	
	// Reference attribute (identifier)
	public org.eclipse.uml2.uml.Property refAttribute;
	
	public UMLAttributeSlot()
	{
		startAttribute = null;
		endAttribute = null;
		durationAttribute = null;
		htAttribute = null;
		refAttribute = null;
	}
}
