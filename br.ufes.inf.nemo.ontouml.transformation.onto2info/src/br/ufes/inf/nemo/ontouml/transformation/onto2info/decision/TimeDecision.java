package br.ufes.inf.nemo.ontouml.transformation.onto2info.decision;

public class TimeDecision
{
	public boolean start;
	public boolean end;
	public boolean duration;
	public org.eclipse.uml2.uml.Property startAttribute;
	public org.eclipse.uml2.uml.Property endAttribute;
	public org.eclipse.uml2.uml.Property durationAttribute;
	
	public TimeDecision()
	{
		start = true;
		end = true;
		duration = true;
	}
}
