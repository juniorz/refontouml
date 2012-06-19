package br.ufes.inf.nemo.ontouml.transformation.onto2info;

import java.util.HashMap;

public class UMLFactoryAbstraction
{		
	// Creates UML Objects	
	org.eclipse.uml2.uml.UMLFactory myfactory;
	// Maps RefOntoUML Elements to UML Elements (auxiliary for Properties, Generalizations and GeneralizationSets)
	HashMap <RefOntoUML.Element,org.eclipse.uml2.uml.Element> mymap;

	public UMLFactoryAbstraction()
	{
		myfactory = org.eclipse.uml2.uml.UMLFactory.eINSTANCE;
		mymap = new HashMap<RefOntoUML.Element, org.eclipse.uml2.uml.Element>();
	}

	// Relate Classifiers and Generalizations
	// This is important for solving Generalizations and Properties (relating Classifiers) and GeneralizationSets (relating Generalizations)
	public void RelateElements (RefOntoUML.Element c1, org.eclipse.uml2.uml.Element c2)
	{
		mymap.put(c1, c2);
	}

	public org.eclipse.uml2.uml.Element GetElement (RefOntoUML.Element e1)
	{
		return mymap.get(e1);
	}
	
	public void DealNamedElement (RefOntoUML.NamedElement ne1, org.eclipse.uml2.uml.NamedElement ne2)
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
    
	public void DealProperty (RefOntoUML.Property p1, org.eclipse.uml2.uml.Property p2, org.eclipse.uml2.uml.Type t2)
    {            
        DealNamedElement (p1, p2);
        
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
        if (t2 == null) t2 = (org.eclipse.uml2.uml.Type) GetElement(p1.getType());
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
	
	public void DealClassifier (RefOntoUML.Classifier c1, org.eclipse.uml2.uml.Classifier c2)
    {
        DealNamedElement (c1, c2);
        // Important for Generalization, Property
        RelateElements (c1, c2);
        // Is Abstract
        c2.setIsAbstract(c1.isIsAbstract());        
    }
	
	public void DealClass (RefOntoUML.Class c1, org.eclipse.uml2.uml.Class c2)
    {		
        DealClassifier (c1, c2);

        // Attributes
        org.eclipse.uml2.uml.Property p2;
        for (RefOntoUML.Property p1 : c1.getAttribute())
        {
            p2 = myfactory.createProperty();
            DealProperty(p1, p2, null);       
            c2.getOwnedAttributes().add(p2);
        }         
    }
	
	public org.eclipse.uml2.uml.Class createClass (RefOntoUML.Class c1)
	{
		org.eclipse.uml2.uml.Class c2 = myfactory.createClass();
		DealClass (c1, c2);
		return c2;
	}
		
	// FIXME: I probably won't use this method
	private void DealAssociation (RefOntoUML.Association a1, org.eclipse.uml2.uml.Association a2)
    {
        DealClassifier(a1, a2);
        
        // isDerived
        a2.setIsDerived(a1.isIsDerived());        

        // memberEnds 
        org.eclipse.uml2.uml.Property p2;
        for (RefOntoUML.Property p1 : a1.getMemberEnd())
        {
        	p2 = myfactory.createProperty();            
            DealProperty (p1, p2, null);
            
            a2.getMemberEnds().add(p2);
            // EOpposite
            p2.setAssociation(a2);
            
            RelateElements(p1, p2);
        }

		// ownedEnds
		for (RefOntoUML.Property p1 : a1.getOwnedEnd())
		{
			p2 = (org.eclipse.uml2.uml.Property) GetElement(p1);
			
			a2.getOwnedEnds().add(p2);
		}
		
		// navigableOwnedEnds
		for (RefOntoUML.Property p1 : a1.getNavigableOwnedEnd())
		{
			p2 = (org.eclipse.uml2.uml.Property) GetElement(p1);
			
			a2.getNavigableOwnedEnds().add(p2);
		}
    }
	
	// FIXME: I probably won't use this method
	private org.eclipse.uml2.uml.Association createAssociation (RefOntoUML.Association a1)
	{
		org.eclipse.uml2.uml.Association a2 = myfactory.createAssociation();
		DealAssociation(a1, a2);
		return a2;
	}
	
	public org.eclipse.uml2.uml.Association createAssociationRepresentingRole (RefOntoUML.Role role)
	{
		// This will be an association between the RelatorType and the more specific RigidParentType
		org.eclipse.uml2.uml.Association a2 = myfactory.createAssociation();
		
		RefOntoUML.Mediation mediation = role.mediation();		
		RefOntoUML.RigidSortalClass rigidParent = role.rigidParent();
		
		// The mapping will be between an RefOntoUML.Mediation and an UML.Association
		DealClassifier(mediation, a2);
		
		// isDerived (I don't believe Mediations are ever derived)
		a2.setIsDerived(false);        

		// memberEnds
		RefOntoUML.Property p1;
		org.eclipse.uml2.uml.Property p2;

		// Relator End
		p1 = mediation.relatorEnd();
		p2 = myfactory.createProperty();            
		DealProperty (p1, p2, null);
		a2.getMemberEnds().add(p2);
		// EOpposite
		p2.setAssociation(a2);
		RelateElements(p1, p2);
		// ownedEnd + navigableOwnedEnd
		a2.getOwnedEnds().add(p2);
		a2.getNavigableOwnedEnds().add(p2);
		
		// Relator End
		p1 = mediation.mediatedEnd();
		p2 = myfactory.createProperty();            
		// RigidParentType, since there is no RoleType
		DealProperty (p1, p2, (org.eclipse.uml2.uml.Type) GetElement(rigidParent));
		a2.getMemberEnds().add(p2);
		// EOpposite
		p2.setAssociation(a2);
		RelateElements(p1, p2);
		// ownedEnd + navigableOwnedEnd
		a2.getOwnedEnds().add(p2);
		a2.getNavigableOwnedEnds().add(p2);

		return a2;
	}

	// FIXME: I won't be using DataTypes, but, as a note: John's version does not deal with PrimitiveTypes and Enumeration Literals
    public org.eclipse.uml2.uml.DataType DealDataType (RefOntoUML.DataType dt1)
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
            
            DealClassifier (dt1, dt2);
           
            /* Attributes */
            org.eclipse.uml2.uml.Property p2; 
            for (RefOntoUML.Property p1 : dt1.getAttribute())
            {
                    p2 = myfactory.createProperty();
                    DealProperty(p1, p2, null);
                    dt2.getOwnedAttributes().add(p2);
            }
                         
            return dt2;
    }
	
	public void DealGeneralization (RefOntoUML.Generalization gen1)
    {	
		org.eclipse.uml2.uml.Generalization gen2 = myfactory.createGeneralization();
            
        // source (Specific)
        RefOntoUML.Classifier e1 = (RefOntoUML.Classifier) gen1.getSpecific();
        org.eclipse.uml2.uml.Classifier e2 = (org.eclipse.uml2.uml.Classifier) GetElement(e1);        
        
        // Poderia ter setado apenas um dos dois (Generalization::Specific, Classifier::Generalization), ja que sao EOpposites
        gen2.setSpecific(e2);
        // O Specific tem posse do generalization        
        e2.getGeneralizations().add(gen2);

        // target (General)
        e1 = (RefOntoUML.Classifier) gen1.getGeneral();
        e2 = (org.eclipse.uml2.uml.Classifier) GetElement(e1);        

        gen2.setGeneral(e2);
        
        // Important for GeneralizationSet
        RelateElements (gen1, gen2);
     }
	
     public void ProcessGeneralizations (RefOntoUML.Classifier c1)
     {
        // Generalizations
        for (RefOntoUML.Generalization gen : c1.getGeneralization())
        {
            DealGeneralization (gen);
        }       
     }
     
     public org.eclipse.uml2.uml.GeneralizationSet DealGeneralizationSet (RefOntoUML.GeneralizationSet gs1)
     {        
        org.eclipse.uml2.uml.GeneralizationSet gs2 = myfactory.createGeneralizationSet();
             
        DealNamedElement(gs1, gs2);
        		
        // Adds all the generalizations
        for  (RefOntoUML.Generalization gen1 : gs1.getGeneralization())
        {
        	org.eclipse.uml2.uml.Generalization gen2 = (org.eclipse.uml2.uml.Generalization) GetElement(gen1);

        	// Poderia ter setado apenas um dos dois (GeneralizationSet::Generalization, Generalization::GeneralizationSet), ja que sao EOpposites
            gs2.getGeneralizations().add(gen2);
            gen2.getGeneralizationSets().add(gs2);
        }
             
        // isCovering, isDisjoint
        gs2.setIsCovering(gs1.isIsCovering());
        gs2.setIsDisjoint(gs1.isIsDisjoint());
        
        // They are PackageableElements, don't forget it
        RelateElements (gs1, gs2);
             
        return gs2;
     }
}
