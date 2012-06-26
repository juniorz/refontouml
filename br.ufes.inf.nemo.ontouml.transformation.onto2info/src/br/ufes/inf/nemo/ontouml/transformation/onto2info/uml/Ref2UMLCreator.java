package br.ufes.inf.nemo.ontouml.transformation.onto2info.uml;

import java.util.List;


public class Ref2UMLCreator
{
	// Creates UML Objects	
	org.eclipse.uml2.uml.UMLFactory myfactory;
	// Primitive Types
	org.eclipse.uml2.uml.DataType timeType;
	org.eclipse.uml2.uml.DataType durationType;
	org.eclipse.uml2.uml.PrimitiveType booleanType;
	
	public Ref2UMLCreator()
	{
		myfactory = org.eclipse.uml2.uml.UMLFactory.eINSTANCE;
		createTimeType();
		createDurationType();
		createBooleanType();
	}
	
	// The DataType that will be referred to by all time attributes
	private void createTimeType ()
	{
		timeType = createDataType("Time");
	}
	
	public org.eclipse.uml2.uml.DataType getTimeType ()
	{
		return timeType;
	}
	
	// The DataType that will be referred to by all duration attributes
	private void createDurationType ()
	{
		durationType = createDataType("Duration");
	}
	
	public org.eclipse.uml2.uml.DataType getDurationType ()
	{
		return durationType;
	}
	
	private void createBooleanType ()
	{
		booleanType = createPrimitiveType("Boolean");
	}
	
	public org.eclipse.uml2.uml.PrimitiveType getBooleanType ()
	{
		return booleanType;
	}
	
	public void addStartTime (RefOntoUML.Class c1)
	{
		addClassAttribute (c1, "start", timeType, true);
	}
	
	public void addEndTime (RefOntoUML.Class c1)
	{
		addClassAttribute (c1, "end", timeType, false);
	}
	
	public void addDuration (RefOntoUML.Class c1)
	{
		addClassAttribute (c1, "duration", durationType, true);
	}
	
	public void addHistoryTrackingAttribute (RefOntoUML.Class c1)
	{
		addClassAttribute (c1, "current", booleanType, true);
	}
	
	// Set the basic attributes of DataType
	private static void initializeDataType (org.eclipse.uml2.uml.DataType dataType, String name)
	{
		// visibility (Element)
		dataType.setVisibility(org.eclipse.uml2.uml.VisibilityKind.PUBLIC_LITERAL);
		// name (NamedElement)
		dataType.setName(name);
		// isAbstract (Classifier)
		dataType.setIsAbstract(false);
	}
	
	private org.eclipse.uml2.uml.DataType createDataType (String name)
	{
		org.eclipse.uml2.uml.DataType dt = myfactory.createDataType();
		initializeDataType (dt, name);
		return dt;
	}
	
	private org.eclipse.uml2.uml.PrimitiveType createPrimitiveType (String name)
	{
		org.eclipse.uml2.uml.PrimitiveType pt = myfactory.createPrimitiveType();
		initializeDataType (pt, name);
		return pt;
	}
		
	private void addClassAttribute (RefOntoUML.Class c1, String name, org.eclipse.uml2.uml.Type type, boolean isRequired)
	{
		org.eclipse.uml2.uml.Class c2 = (org.eclipse.uml2.uml.Class) Ref2UMLReplicator.getElement(c1);
		
		org.eclipse.uml2.uml.Property p = myfactory.createProperty();
		
		// name (NamedElement)
		p.setName(name);
		// isLeaf (RedefinableElement)
		p.setIsLeaf(true);
		// isStatic (Feature)
		p.setIsStatic(false);
		// isReadOnly (StructuralFeature)
		p.setIsReadOnly(true);

		// lower, upper (MultiplicityElement)            
		org.eclipse.uml2.uml.LiteralInteger lowerValue = myfactory.createLiteralInteger();
		org.eclipse.uml2.uml.LiteralUnlimitedNatural upperValue = myfactory.createLiteralUnlimitedNatural();
		lowerValue.setValue(isRequired ? 1 : 0);
		upperValue.setValue(1);   
		p.setLowerValue(lowerValue);
		p.setUpperValue(upperValue);

		// Type (TypedElement)
		p.setType(type);

		// isDerived (Property)
		p.setIsDerived(false);        
		// aggregation (Property)
		p.setAggregation(org.eclipse.uml2.uml.AggregationKind.NONE_LITERAL);    
		
		// Linking Class and Property
		c2.getOwnedAttributes().add(p);
	}
	
	// Created from scratch, no mapping
	public org.eclipse.uml2.uml.Generalization createArtificialGeneralization (RefOntoUML.RigidSortalClass rigidSortal, RefOntoUML.RoleMixin roleMixin)
	{
		org.eclipse.uml2.uml.Generalization gen = myfactory.createGeneralization();
		
		// specific
		org.eclipse.uml2.uml.Classifier specific = (org.eclipse.uml2.uml.Classifier) Ref2UMLReplicator.getElement(rigidSortal);
		 gen.setSpecific(specific);
		 specific.getGeneralizations().add(gen);
		
		// general
		org.eclipse.uml2.uml.Classifier general = (org.eclipse.uml2.uml.Classifier) Ref2UMLReplicator.getElement(roleMixin);
		gen.setGeneral(general);

		return gen;
	}
	
	// Created from scratch, no mapping (for RoleMixin generalization only)
	public org.eclipse.uml2.uml.GeneralizationSet createGeneralizationSetForRoleMixin (RefOntoUML.RoleMixin roleMixin, List<org.eclipse.uml2.uml.Generalization> genlist)
	{
		org.eclipse.uml2.uml.GeneralizationSet gset = myfactory.createGeneralizationSet();
		
		// name
		gset.setName("");
		// visibility
		gset.setVisibility(org.eclipse.uml2.uml.VisibilityKind.PUBLIC_LITERAL);
		// isDisjoint
		gset.setIsDisjoint(true);
		// isCovering FIXME: not always, only when all rigidSortals are in scope
		gset.setIsCovering(genlist.size() == roleMixin.rigidSortals().size());
		
		// Linking the GeneralizationSet and the Generalizations
		for (org.eclipse.uml2.uml.Generalization gen : genlist)
		{
			gset.getGeneralizations().add(gen);
			gen.getGeneralizationSets().add(gset);
		}
		
		return gset;
	}
}
