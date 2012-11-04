package br.ufes.inf.nemo.ontouml.transformation.onto2info.decision;

public class ReferenceDecision implements Decision
{
	private static final long serialVersionUID = 1516834064790927005L;

	public boolean reference;
	public String attributeName;
	public AttributeType attributeType;
	
	public ReferenceDecision()
	{
		reference = true;
		attributeName = "id";
		attributeType = AttributeType.INT;
	}
}
