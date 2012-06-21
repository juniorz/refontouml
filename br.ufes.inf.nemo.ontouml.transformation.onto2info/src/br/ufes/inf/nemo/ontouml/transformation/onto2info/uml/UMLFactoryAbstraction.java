package br.ufes.inf.nemo.ontouml.transformation.onto2info.uml;

import java.util.HashMap;

public class UMLFactoryAbstraction
{		
	// Creates UML Objects	
	org.eclipse.uml2.uml.UMLFactory myfactory;
	// Maps RefOntoUML Elements to UML Elements (auxiliary for Properties, Generalizations and GeneralizationSets)
	HashMap <RefOntoUML.Element,org.eclipse.uml2.uml.Element> mymap;

	org.eclipse.uml2.uml.DataType timeType;
	org.eclipse.uml2.uml.DataType durationType;
	org.eclipse.uml2.uml.PrimitiveType booleanType;
	
	public UMLFactoryAbstraction()
	{
		myfactory = org.eclipse.uml2.uml.UMLFactory.eINSTANCE;
		mymap = new HashMap<RefOntoUML.Element, org.eclipse.uml2.uml.Element>();
		createTimeType();
		createDurationType();
		createBooleanType();
	}

	// Set the basic attributes of DataType
	private void setBasicDataType (org.eclipse.uml2.uml.DataType dataType, String name)
	{
		// visibility (Element)
		dataType.setVisibility(org.eclipse.uml2.uml.VisibilityKind.PUBLIC_LITERAL);
		// name (NamedElement)
		dataType.setName(name);
		// isAbstract (Classifier)
		dataType.setIsAbstract(false);
	}
	
	// The DataType that will be referred to by all time attributes
	private void createTimeType ()
	{
		timeType = myfactory.createDataType();
		setBasicDataType (timeType, "Time");
	}
	
	public org.eclipse.uml2.uml.DataType getTimeType ()
	{
		return timeType;
	}
	
	// The DataType that will be referred to by all duration attributes
	private void createDurationType ()
	{
		durationType = myfactory.createDataType();
		setBasicDataType (durationType, "Duration");
	}
	
	public org.eclipse.uml2.uml.DataType getDurationType ()
	{
		return durationType;
	}
	
	private void createBooleanType ()
	{
		booleanType = myfactory.createPrimitiveType();
		setBasicDataType (booleanType, "Boolean");
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
	
	private void addClassAttribute (RefOntoUML.Class c1, String name, org.eclipse.uml2.uml.Type type, boolean isRequired)
	{
		org.eclipse.uml2.uml.Class c2 = (org.eclipse.uml2.uml.Class) getElement (c1);
		
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
	
	// Relate Classifiers and Generalizations
	// This is important for solving Generalizations and Properties (relating Classifiers) and GeneralizationSets (relating Generalizations)
	public void relateElements (RefOntoUML.Element c1, org.eclipse.uml2.uml.Element c2)
	{
		mymap.put(c1, c2);
	}

	public org.eclipse.uml2.uml.Element getElement (RefOntoUML.Element e1)
	{
		return mymap.get(e1);
	}
	
	public void replicateNamedElement (RefOntoUML.NamedElement ne1, org.eclipse.uml2.uml.NamedElement ne2)
	{
		// name
		ne2.setName(ne1.getName());

		// visibility
        RefOntoUML.VisibilityKind vk1 = ne1.getVisibility();
        // FIXME
        if (vk1.getValue() == RefOntoUML.VisibilityKind.PUBLIC_VALUE)
        {
        	ne2.setVisibility(org.eclipse.uml2.uml.VisibilityKind.PUBLIC_LITERAL);
        }
        else if (vk1.getValue() == RefOntoUML.VisibilityKind.PRIVATE_VALUE)
        {
        	ne2.setVisibility(org.eclipse.uml2.uml.VisibilityKind.PRIVATE_LITERAL);
        }
        else if (vk1.getValue() == RefOntoUML.VisibilityKind.PROTECTED_VALUE)
        {
        	ne2.setVisibility(org.eclipse.uml2.uml.VisibilityKind.PROTECTED_LITERAL);
        }
        else if (vk1.getValue() == RefOntoUML.VisibilityKind.PACKAGE_VALUE)
        {
        	ne2.setVisibility(org.eclipse.uml2.uml.VisibilityKind.PACKAGE_LITERAL);
        }
    }
	
	public org.eclipse.uml2.uml.Model partiallyCreateModel (RefOntoUML.Model m1)
	{
		org.eclipse.uml2.uml.Model m2 = myfactory.createModel();
        replicateNamedElement(m1, m2);
        relateElements(m1, m2);
        return m2;
	}
    
	public void replicateProperty (RefOntoUML.Property p1, org.eclipse.uml2.uml.Property p2)
    {            
        replicateNamedElement (p1, p2);
        
        // isLeaf (RedefinableElement)
        p2.setIsLeaf(p1.isIsLeaf());
        // isStatic (Feature)
        p2.setIsStatic(p1.isIsStatic());
        // isReadOnly (StructuralFeature)
        p2.setIsReadOnly(p1.isIsReadOnly());                      

        // lower, upper (MultiplicityElement)            
        org.eclipse.uml2.uml.LiteralInteger lowerValue = myfactory.createLiteralInteger();
        org.eclipse.uml2.uml.LiteralUnlimitedNatural upperValue = myfactory.createLiteralUnlimitedNatural();
        lowerValue.setValue(p1.getLower());
        upperValue.setValue(p1.getUpper()); 
            
        p2.setLowerValue(lowerValue);
        p2.setUpperValue(upperValue);
                
        // Type (TypedElement)
        org.eclipse.uml2.uml.Type t2 = (org.eclipse.uml2.uml.Type) getElement(p1.getType());
        p2.setType(t2);              
        
        // isDerived
        p2.setIsDerived(p1.isIsDerived());
        
        // aggregation
        RefOntoUML.AggregationKind ak1 = p1.getAggregation();
        // FIXME
        if (ak1.getValue() == RefOntoUML.AggregationKind.NONE_VALUE)
        {
        	p2.setAggregation(org.eclipse.uml2.uml.AggregationKind.NONE_LITERAL);                     
        }
        else if (ak1.getValue() == RefOntoUML.AggregationKind.SHARED_VALUE)
        {
            p2.setAggregation(org.eclipse.uml2.uml.AggregationKind.SHARED_LITERAL);
        }               
        else if (ak1.getValue() == RefOntoUML.AggregationKind.COMPOSITE_VALUE)
        {
            p2.setAggregation(org.eclipse.uml2.uml.AggregationKind.COMPOSITE_LITERAL);
        }        
    }
	
	public org.eclipse.uml2.uml.Property createProperty (RefOntoUML.Property p1)
	{
		org.eclipse.uml2.uml.Property p2 = myfactory.createProperty();
		replicateProperty(p1, p2);
		return p2;
	}
	
	public void replicateClassifier (RefOntoUML.Classifier c1, org.eclipse.uml2.uml.Classifier c2)
    {
        replicateNamedElement (c1, c2);
        // Important for Generalization, Property
        relateElements (c1, c2);
        // Is Abstract
        c2.setIsAbstract(c1.isIsAbstract());        
    }
	
	public void replicateClass (RefOntoUML.Class c1, org.eclipse.uml2.uml.Class c2)
    {		
        replicateClassifier (c1, c2);

        // Attributes
        org.eclipse.uml2.uml.Property p2;
        for (RefOntoUML.Property p1 : c1.getAttribute())
        {
            p2 = createProperty(p1);       
            c2.getOwnedAttributes().add(p2);
        }         
    }
	
	public org.eclipse.uml2.uml.Class createClass (RefOntoUML.Class c1)
	{
		org.eclipse.uml2.uml.Class c2 = myfactory.createClass();
		replicateClass (c1, c2);
		return c2;
	}
		
	// FIXME: I probably won't use this method
	private void replicateAssociation (RefOntoUML.Association a1, org.eclipse.uml2.uml.Association a2)
    {
        replicateClassifier(a1, a2);
        
        // isDerived
        a2.setIsDerived(a1.isIsDerived());        

        // memberEnds 
        org.eclipse.uml2.uml.Property p2;
        for (RefOntoUML.Property p1 : a1.getMemberEnd())
        {
        	p2 = createProperty(p1);
            
            a2.getMemberEnds().add(p2);
            // EOpposite
            p2.setAssociation(a2);
            
            relateElements(p1, p2);
        }

		// ownedEnds
		for (RefOntoUML.Property p1 : a1.getOwnedEnd())
		{
			p2 = (org.eclipse.uml2.uml.Property) getElement(p1);
			
			a2.getOwnedEnds().add(p2);
		}
		
		// navigableOwnedEnds
		for (RefOntoUML.Property p1 : a1.getNavigableOwnedEnd())
		{
			p2 = (org.eclipse.uml2.uml.Property) getElement(p1);
			
			a2.getNavigableOwnedEnds().add(p2);
		}
    }
	
	// FIXME: I probably won't use this method
	private org.eclipse.uml2.uml.Association createAssociation (RefOntoUML.Association a1)
	{
		org.eclipse.uml2.uml.Association a2 = myfactory.createAssociation();
		replicateAssociation(a1, a2);
		return a2;
	}
	
	private org.eclipse.uml2.uml.Property createMemberEnd (org.eclipse.uml2.uml.Association a2, RefOntoUML.Property p1)
	{
		org.eclipse.uml2.uml.Property p2 = createProperty(p1);
		
		// memberEnd
		a2.getMemberEnds().add(p2);
		// EOpposite of memberEnd
		p2.setAssociation(a2);

		// ownedEnd
		a2.getOwnedEnds().add(p2);
		// navigableOwnedEnd
		a2.getNavigableOwnedEnds().add(p2);
		
		relateElements(p1, p2);
		
		return p2;
	}
	
	public org.eclipse.uml2.uml.Association createAssociationRepresentingRoleMixin (RefOntoUML.RoleMixin roleMixin)
	{
		return createAssociationRepresentingRoleClass (roleMixin.mediation(), null, roleMixin.getName());
	}
	
	public org.eclipse.uml2.uml.Association createAssociationRepresentingRole (RefOntoUML.Role role)
	{
		return createAssociationRepresentingRoleClass (role.mediation(), role.rigidParent(), role.getName());
	}
	
	private org.eclipse.uml2.uml.Association createAssociationRepresentingRoleClass (RefOntoUML.Mediation mediation, RefOntoUML.RigidSortalClass rigidParent, String roleName)
	{
		// This will be an association between the RelatorType and the more specific RigidParentType
		org.eclipse.uml2.uml.Association a2 = myfactory.createAssociation();

		// The mapping will be between an RefOntoUML.Mediation and a UML.Association
		replicateClassifier(mediation, a2);
		
		// isDerived (I don't believe Mediations are ever derived)
		a2.setIsDerived(false);        

		// Create memberEnds
		org.eclipse.uml2.uml.Property p2;

		// Create Relator End
		p2 = createMemberEnd(a2, mediation.relatorEnd());
		// Setting the cardinality to [0, *]
		org.eclipse.uml2.uml.LiteralInteger lowerValue = myfactory.createLiteralInteger();
        org.eclipse.uml2.uml.LiteralUnlimitedNatural upperValue = myfactory.createLiteralUnlimitedNatural();
        lowerValue.setValue(0);
        upperValue.setValue(-1); // FIXME: depends on history tracking
        p2.setLowerValue(lowerValue);
        p2.setUpperValue(upperValue);
		
		// Create Rigid Parent End
		p2 = createMemberEnd(a2, mediation.mediatedEnd());
		// Roles only
		if (rigidParent != null)
		{
			// Property Type -> RigidParentType (since there is no RoleType)
			p2.setType((org.eclipse.uml2.uml.Type) getElement(rigidParent));
		}
		// Property name -> Role name
		p2.setName(roleName);
		
		return a2;
	}

	// FIXME: I won't be using DataTypes, but, as a note: John's version does not deal with PrimitiveTypes and Enumeration Literals
    public org.eclipse.uml2.uml.DataType replicateDataType (RefOntoUML.DataType dt1)
    {
            org.eclipse.uml2.uml.DataType dt2 = null;
            
            if (dt1 instanceof RefOntoUML.DataType)
            {
            	dt2 = myfactory.createDataType();
            }             
            else if (dt1 instanceof RefOntoUML.Enumeration)
            {                     
                dt2 = myfactory.createEnumeration();
            }
            
            replicateClassifier (dt1, dt2);
           
            /* Attributes */
            org.eclipse.uml2.uml.Property p2; 
            for (RefOntoUML.Property p1 : dt1.getAttribute())
            {
                    p2 = createProperty(p1);
                    dt2.getOwnedAttributes().add(p2);
            }
                         
            return dt2;
    }
	
	private void replicateGeneralization (RefOntoUML.Generalization gen1, org.eclipse.uml2.uml.Generalization gen2)
    {            
        // source (Specific)
        RefOntoUML.Classifier e1 = (RefOntoUML.Classifier) gen1.getSpecific();
        org.eclipse.uml2.uml.Classifier e2 = (org.eclipse.uml2.uml.Classifier) getElement(e1);        
        
        // Poderia ter setado apenas um dos dois (Generalization::Specific, Classifier::Generalization), ja que sao EOpposites
        gen2.setSpecific(e2);
        // O Specific tem posse do generalization        
        e2.getGeneralizations().add(gen2);

        // target (General)
        e1 = (RefOntoUML.Classifier) gen1.getGeneral();
        e2 = (org.eclipse.uml2.uml.Classifier) getElement(e1);        

        gen2.setGeneral(e2);
        
        // Important for GeneralizationSet
        relateElements (gen1, gen2);
     }
	
	private org.eclipse.uml2.uml.Generalization createGeneralization (RefOntoUML.Generalization gen1)
	{
		org.eclipse.uml2.uml.Generalization gen2 = myfactory.createGeneralization();
        replicateGeneralization (gen1, gen2);
        return gen2;
	}
	
     public void createAllGeneralizations (RefOntoUML.Classifier c1)
     {
        // Generalizations
        for (RefOntoUML.Generalization gen1 : c1.getGeneralization())
        {
        	createGeneralization(gen1);
        }       
     }
     
     private org.eclipse.uml2.uml.Generalization createArtificialGeneralization (RefOntoUML.RigidSortalClass rigidSortal, RefOntoUML.RoleMixin roleMixin)
     {
    	 org.eclipse.uml2.uml.Generalization gen = myfactory.createGeneralization();
    	 
    	 // specific
    	 org.eclipse.uml2.uml.Classifier specific = (org.eclipse.uml2.uml.Classifier) getElement(rigidSortal);
    	 gen.setSpecific(specific);
    	 specific.getGeneralizations().add(gen);
    	 
    	 // general
    	 org.eclipse.uml2.uml.Classifier general = (org.eclipse.uml2.uml.Classifier) getElement(roleMixin);
    	 gen.setGeneral(general);
    	 
    	 return gen;
     }
     
	public void createArtificialGeneralizations (RefOntoUML.RoleMixin roleMixin)
	{
		// FIXME: The GeneralizationSet is only necessary when there is at least two children (i.e., rigidSortals)
		org.eclipse.uml2.uml.GeneralizationSet gset = myfactory.createGeneralizationSet();
		// GeneralizationSet name
		gset.setName("");
		// GeneralizationSet visibility
		gset.setVisibility(org.eclipse.uml2.uml.VisibilityKind.PUBLIC_LITERAL);
		// Disjoint
		gset.setIsDisjoint(true);
		// Complete FIXME: not always, only when all rigidSortals are in scope
		gset.setIsCovering(true);
        		
		// Artificial Generalization(s) // FIXME: depends on scope
		org.eclipse.uml2.uml.Generalization gen;		 
		for (RefOntoUML.RigidSortalClass rigidSortal : roleMixin.rigidSortals())
		{
			gen = createArtificialGeneralization (rigidSortal, roleMixin);
			// Linking the GeneralizationSet and the Generalization
			gset.getGeneralizations().add(gen);
            gen.getGeneralizationSets().add(gset);
		}
	}

     public org.eclipse.uml2.uml.GeneralizationSet createGeneralizationSet (RefOntoUML.GeneralizationSet gs1)
     {
        org.eclipse.uml2.uml.GeneralizationSet gs2 = myfactory.createGeneralizationSet();
             
        replicateNamedElement(gs1, gs2);
        		
        // Adds all the generalizations
        for  (RefOntoUML.Generalization gen1 : gs1.getGeneralization())
        {
        	org.eclipse.uml2.uml.Generalization gen2 = (org.eclipse.uml2.uml.Generalization) getElement(gen1);

        	// Poderia ter setado apenas um dos dois (GeneralizationSet::Generalization, Generalization::GeneralizationSet), ja que sao EOpposites
            gs2.getGeneralizations().add(gen2);
            gen2.getGeneralizationSets().add(gs2);
        }
             
        // isCovering, isDisjoint
        gs2.setIsCovering(gs1.isIsCovering());
        gs2.setIsDisjoint(gs1.isIsDisjoint());
        
        // They are PackageableElements, don't forget it
        relateElements (gs1, gs2);
             
        return gs2;
     }
}
