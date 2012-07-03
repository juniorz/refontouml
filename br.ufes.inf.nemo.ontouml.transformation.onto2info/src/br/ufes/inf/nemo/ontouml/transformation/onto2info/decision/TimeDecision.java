package br.ufes.inf.nemo.ontouml.transformation.onto2info.decision;

import java.io.Serializable;

public class TimeDecision implements Serializable
{
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
