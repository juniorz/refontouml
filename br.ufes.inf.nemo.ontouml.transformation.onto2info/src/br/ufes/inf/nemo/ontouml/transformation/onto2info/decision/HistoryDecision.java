package br.ufes.inf.nemo.ontouml.transformation.onto2info.decision;

import java.io.Serializable;

public class HistoryDecision implements Serializable
{
	private static final long serialVersionUID = -582351433775987751L;
	
	public boolean past;
	public boolean present;
	
	public HistoryDecision()
	{
		past = true;
		present = true;
	}
	
	public boolean requiresAttribute ()
	{
		return past && present;
	}
}
