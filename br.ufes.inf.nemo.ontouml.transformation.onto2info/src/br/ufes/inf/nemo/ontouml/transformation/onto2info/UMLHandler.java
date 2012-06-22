package br.ufes.inf.nemo.ontouml.transformation.onto2info;

import br.ufes.inf.nemo.ontouml.transformation.onto2info.uml.UMLFactoryAbstraction;

public class UMLHandler
{
	org.eclipse.uml2.uml.DataType timeType;
	org.eclipse.uml2.uml.DataType durationType;
	org.eclipse.uml2.uml.PrimitiveType booleanType;
	
	public UMLHandler()
	{
		createTimeType();
		createDurationType();
		createBooleanType();
	}
	
	// The DataType that will be referred to by all time attributes
	private void createTimeType ()
	{
		timeType = UMLFactoryAbstraction.createDataType("Time");
	}
	
	public org.eclipse.uml2.uml.DataType getTimeType ()
	{
		return timeType;
	}
	
	// The DataType that will be referred to by all duration attributes
	private void createDurationType ()
	{
		durationType = UMLFactoryAbstraction.createDataType("Duration");
	}
	
	public org.eclipse.uml2.uml.DataType getDurationType ()
	{
		return durationType;
	}
	
	private void createBooleanType ()
	{
		booleanType = UMLFactoryAbstraction.createPrimitiveType("Boolean");
	}
	
	public org.eclipse.uml2.uml.PrimitiveType getBooleanType ()
	{
		return booleanType;
	}
	
	public void addStartTime (RefOntoUML.Class c1)
	{
		UMLFactoryAbstraction.addClassAttribute (c1, "start", timeType, true);
	}
	
	public void addEndTime (RefOntoUML.Class c1)
	{
		UMLFactoryAbstraction.addClassAttribute (c1, "end", timeType, false);
	}
	
	public void addDuration (RefOntoUML.Class c1)
	{
		UMLFactoryAbstraction.addClassAttribute (c1, "duration", durationType, true);
	}
	
	public void addHistoryTrackingAttribute (RefOntoUML.Class c1)
	{
		UMLFactoryAbstraction.addClassAttribute (c1, "current", booleanType, true);
	}
}
