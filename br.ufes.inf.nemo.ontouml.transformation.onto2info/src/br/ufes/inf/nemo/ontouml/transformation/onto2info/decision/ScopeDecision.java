package br.ufes.inf.nemo.ontouml.transformation.onto2info.decision;

import java.io.Serializable;

public class ScopeDecision implements Serializable
{
	private static final long serialVersionUID = -6108221956205708251L;
	
	public boolean scope;
	
	public ScopeDecision()
	{
		scope = true;
	}
}
