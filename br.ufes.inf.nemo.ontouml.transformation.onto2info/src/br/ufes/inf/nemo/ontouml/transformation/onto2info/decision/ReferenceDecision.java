package br.ufes.inf.nemo.ontouml.transformation.onto2info.decision;

import java.io.Serializable;

public class ReferenceDecision implements Serializable
{
	private static final long serialVersionUID = 1516834064790927005L;

	public boolean reference;
	
	public ReferenceDecision()
	{
		reference = true;
	}
}
