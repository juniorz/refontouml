package br.ufes.inf.nemo.ontouml.transformation.onto2info.decision;

public enum AttributeType
{
	INT, STRING, CUSTOM;

	@Override
	public String toString()
	{		
		return super.toString().toLowerCase();
	}
}
