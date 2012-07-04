package br.ufes.inf.nemo.ontouml.transformation.onto2info.decision;

import java.io.Serializable;

public class TimeDecision implements Serializable
{
	private static final long serialVersionUID = -3355726825180675250L;
	
	public boolean start;
	public boolean end;
	public boolean duration;
	
	public TimeDecision()
	{
		start = true;
		end = true;
		duration = true;
	}
}
