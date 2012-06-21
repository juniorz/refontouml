package br.ufes.inf.nemo.ontouml.transformation.onto2info.decision;

public class HistoryDecision
{
	public boolean past;
	public boolean present;
	// current : Boolean [1,1] attribute
	public org.eclipse.uml2.uml.Property htAttribute;
	
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
