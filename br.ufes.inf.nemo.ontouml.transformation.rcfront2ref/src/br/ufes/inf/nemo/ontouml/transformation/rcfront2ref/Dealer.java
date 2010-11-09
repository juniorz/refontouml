package br.ufes.inf.nemo.ontouml.transformation.rcfront2ref;

import java.util.HashMap;

public class Dealer
{
	// Creates RefOntoUML Objects
	RefOntoUML.RefOntoUMLFactory myfactory;
	// Maps UML Elements to RefOntoUML Elements (auxiliar for Properties, Generalizations and GeneralizationSets)  
	HashMap<org.eclipse.uml2.uml.Element, RefOntoUML.Element> mymap;
	
	public Dealer()
	{
		myfactory = RefOntoUML.RefOntoUMLFactory.eINSTANCE;
		mymap = new HashMap<org.eclipse.uml2.uml.Element, RefOntoUML.Element>();
	}
		
	// Relate Classifiers and Generalizations
	// This is important for solving Generalizations and Properties (relating Classifiers) and GeneralizationSets (relating Generalizations)
	public void RelateElements (org.eclipse.uml2.uml.Element c1, RefOntoUML.Element c2)
	{
		mymap.put(c1, c2);
	}
	
	public RefOntoUML.Element GetElement (org.eclipse.uml2.uml.Element e1)
	{
		return mymap.get(e1);
	}

	public void DealNamedElement (org.eclipse.uml2.uml.NamedElement ne1, RefOntoUML.NamedElement ne2)
	{
		System.out.println(ne1.getName());
		
		// name
		ne2.setName(ne1.getName());
		
		// visibility
		org.eclipse.uml2.uml.VisibilityKind vk1 = ne1.getVisibility();

		if (vk1.getValue() == org.eclipse.uml2.uml.VisibilityKind.PUBLIC)
		{
			ne2.setVisibility(RefOntoUML.VisibilityKind.PUBLIC);
		}
		else if (vk1.getValue() == org.eclipse.uml2.uml.VisibilityKind.PRIVATE)
		{
			ne2.setVisibility(RefOntoUML.VisibilityKind.PRIVATE);
		}
		else if (vk1.getValue() == org.eclipse.uml2.uml.VisibilityKind.PROTECTED)
		{
			ne2.setVisibility(RefOntoUML.VisibilityKind.PROTECTED);
		}
		else if (vk1.getValue() == org.eclipse.uml2.uml.VisibilityKind.PACKAGE)
		{
			ne2.setVisibility(RefOntoUML.VisibilityKind.PACKAGE);
		}
	}
	
	public void DealProperty (org.eclipse.uml2.uml.Property p1, RefOntoUML.Property p2)
	{
		System.out.print("    Property (" + p1.getType().getName() + "): " + p1.getLower() + " " + p1.getUpper() + " ");
		
		DealNamedElement (p1, p2);
		// isLeaf (RedefinableElement)
		p2.setIsLeaf(p1.isLeaf());
		// isStatic (Feature)
		p2.setIsStatic(p1.isStatic());
		// isReadOnly (StructuralFeature)
		p2.setIsReadOnly(p1.isReadOnly());
		
		// lower, upper (MultiplicityElement)
		RefOntoUML.LiteralInteger lowerValue = myfactory.createLiteralInteger();
		RefOntoUML.LiteralUnlimitedNatural upperValue = myfactory.createLiteralUnlimitedNatural();
		lowerValue.setValue(p1.getLower());
		upperValue.setValue(p1.getUpper()); 
		
		p2.setLowerValue(lowerValue);
		p2.setUpperValue(upperValue);
			
		// Type (TypedElement)
		RefOntoUML.Type t2 = (RefOntoUML.Type) GetElement(p1.getType());
		p2.setType(t2);
		
		// isDerived
		p2.setIsDerived(p1.isDerived());
		
		// aggregation
		org.eclipse.uml2.uml.AggregationKind ak1 = p1.getAggregation();
		
		if (ak1.getValue() == org.eclipse.uml2.uml.AggregationKind.NONE)
		{
			p2.setAggregation(RefOntoUML.AggregationKind.NONE);			
		}
		else if (ak1.getValue() == org.eclipse.uml2.uml.AggregationKind.SHARED)
		{
			p2.setAggregation(RefOntoUML.AggregationKind.SHARED);
		}		
		else if (ak1.getValue() == org.eclipse.uml2.uml.AggregationKind.COMPOSITE)
		{
			p2.setAggregation(RefOntoUML.AggregationKind.COMPOSITE);
		}		
	}
	
	public void DealClassifier (org.eclipse.uml2.uml.Classifier c1, RefOntoUML.Classifier c2)
	{
		DealNamedElement (c1, c2);		
		// Important for Generalization, Property
		RelateElements (c1, c2);
		// isAbstract
		c2.setIsAbstract(c1.isAbstract());
	}
	
	public void DealClass (org.eclipse.uml2.uml.Class c1, RefOntoUML.Class c2)
	{
		DealClassifier (c1, c2);

		// Attributes
		RefOntoUML.Property p2;		
		for (org.eclipse.uml2.uml.Property p1 : c1.getAttributes())
		{
			p2 = myfactory.createProperty();
			DealProperty(p1, p2);
			c2.getOwnedAttribute().add(p2);
		}
	}
	
	public RefOntoUML.Class DealClassStereotype (org.eclipse.uml2.uml.Class c1)
	{
		System.out.print("<Class> ");
		RefOntoUML.Class c2 = null;
		
		if (c1.getAppliedStereotypes().size() == 1)
		{
			org.eclipse.uml2.uml.Stereotype s = c1.getAppliedStereotypes().get(0);
			
			System.out.print("<<" + s.getName() + ">> ");
			String stereoname = s.getName();
			
			if (stereoname.compareTo("Kind") == 0)
			{
				c2 = myfactory.createKind();
			}
			else if (stereoname.compareTo("SubKind") == 0)
			{
				c2 = myfactory.createSubKind();
			}
			else if (stereoname.compareTo("Collective") == 0)
			{
				c2 = myfactory.createCollective();
				
				boolean isExtensional = (Boolean) c1.getValue(s, "isExtensional");
				((RefOntoUML.Collective) c2).setIsExtensional(isExtensional);
			}
			else if (stereoname.compareTo("Quantity") == 0)
			{
				c2 = myfactory.createQuantity();
			}
			else if (stereoname.compareTo("Role") == 0)
			{
				c2 = myfactory.createRole();
			}
			else if (stereoname.compareTo("Phase") == 0)
			{
				c2 = myfactory.createPhase();
			}
			else if (stereoname.compareTo("Category") == 0)
			{
				c2 = myfactory.createCategory();
			}
			else if (stereoname.compareTo("Mixin") == 0)
			{
				c2 = myfactory.createMixin();
			}
			else if (stereoname.compareTo("RoleMixin") == 0)
			{
				c2 = myfactory.createRoleMixin();
			}
			else if (stereoname.compareTo("Relator") == 0)
			{
				c2 = myfactory.createRelator();
			}
			else if (stereoname.compareTo("Mode") == 0)
			{
				c2 = myfactory.createMode();
			}
		}
		else if (c1.getAppliedStereotypes().size() == 0)
		{
			c2 = myfactory.createSubKind();
		}
		
		DealClass(c1, c2);
		return c2;
	}
	
	public void DealAssociation (org.eclipse.uml2.uml.Association a1, RefOntoUML.Association a2)
	{
		DealClassifier(a1, a2);
		
		// isDerived
		a2.setIsDerived(a1.isDerived());
		
		// memberEnds
		RefOntoUML.Property p2;
		for (org.eclipse.uml2.uml.Property p1 : a1.getMemberEnds())
		{
			p2 = myfactory.createProperty();
			DealProperty(p1, p2);
			
			a2.getMemberEnd().add(p2);
			// EOpposite
			p2.setAssociation(a2);
			
			RelateElements(p1, p2);
		}
		
		// ownedEnds
		for (org.eclipse.uml2.uml.Property p1 : a1.getOwnedEnds())
		{
			p2 = (RefOntoUML.Property) GetElement(p1);
			
			a2.getOwnedEnd().add(p2);
		}
		
		// navigableOwnedEnds
		for (org.eclipse.uml2.uml.Property p1 : a1.getNavigableOwnedEnds())
		{
			p2 = (RefOntoUML.Property) GetElement(p1);
			
			a2.getNavigableOwnedEnd().add(p2);
		}
	}
	
	public RefOntoUML.Association DealAssociationStereotype (org.eclipse.uml2.uml.Association a1)
	{
		System.out.print("<Association> ");
		RefOntoUML.Association a2 = null;
		
		if (a1.getAppliedStereotypes().size() == 1)
		{
			org.eclipse.uml2.uml.Stereotype s = a1.getAppliedStereotypes().get(0);
			
			System.out.print("<<" + s.getName() + ">> ");
			String stereoname = s.getName();
			
			if (stereoname.compareTo("Formal") == 0)
			{
				a2 = myfactory.createFormalAssociation();
			}
			else if (stereoname.compareTo("Material") == 0)
			{
				a2 = myfactory.createMaterialAssociation();
			}
			else if (stereoname.compareTo("Mediation") == 0)
			{
				a2 = myfactory.createMediation();
			}
			else if (stereoname.compareTo("Derivation") == 0)
			{
				a2 = myfactory.createDerivation();
			}
			else if (stereoname.compareTo("Characterization") == 0)
			{
				a2 = myfactory.createCharacterization();
			}
			else if (stereoname.compareTo("componentOf") == 0)
			{
				a2 = myfactory.createcomponentOf();
			}
			else if (stereoname.compareTo("memberOf") == 0)
			{
				a2 = myfactory.creatememberOf();
			}
			else if (stereoname.compareTo("subCollectionOf") == 0)
			{
				a2 = myfactory.createsubCollectionOf();
			}
			else if (stereoname.compareTo("subQuantityOf") == 0)
			{
				a2 = myfactory.createsubQuantityOf();
			}
			
			if (a2 instanceof RefOntoUML.Meronymic)
			{
				boolean isShareable = (Boolean) a1.getValue(s, "isShareable");
				boolean isEssential = (Boolean) a1.getValue(s, "isEssential");
				boolean isInseparable = (Boolean) a1.getValue(s, "isInseparable");
				boolean isImmutablePart = (Boolean) a1.getValue(s, "isImmutablePart");
				boolean isImmutableWhole = (Boolean) a1.getValue(s, "isImmutableWhole");
				
				((RefOntoUML.Meronymic) a2).setIsShareable(isShareable);
				((RefOntoUML.Meronymic) a2).setIsEssential(isEssential);
				((RefOntoUML.Meronymic) a2).setIsInseparable(isInseparable);
				((RefOntoUML.Meronymic) a2).setIsImmutablePart(isImmutablePart);
				((RefOntoUML.Meronymic) a2).setIsImmutableWhole(isImmutableWhole);
			}
		}
		else if (a1.getAppliedStereotypes().size() == 0)
		{
			org.eclipse.uml2.uml.Property memberEnd2 = a1.getMemberEnds().get(1);
			org.eclipse.uml2.uml.AggregationKind ak = memberEnd2.getAggregation();
			
			if (ak.getValue() == org.eclipse.uml2.uml.AggregationKind.NONE)
			{
				a2 = myfactory.createAssociation();
			}
			else
			{
				a2 = myfactory.createcomponentOf();
			}
		}
		
		DealAssociation(a1, a2);
		return a2;
	}
	
	public RefOntoUML.DataType DealDataType (org.eclipse.uml2.uml.DataType dt1)
	{
		RefOntoUML.DataType dt2 = null;
		
		if (dt1 instanceof org.eclipse.uml2.uml.PrimitiveType)
		{
			// FIXME: Esperar consertarem esse Bug 262140 -  UML primitive types cannot be accessed
			System.out.print("<PrimitiveType> ");
			dt2 = myfactory.createPrimitiveType();
		}
		else if (dt1 instanceof org.eclipse.uml2.uml.Enumeration)
		{
			System.out.print("<Enumeration> ");
			dt2 = myfactory.createEnumeration();
		}
		else
		{
			System.out.print("<DataType> ");
			dt2 = myfactory.createDataType();
		}
		
		DealClassifier (dt1, dt2);
		
		// Attributes
		RefOntoUML.Property p2;	
		for (org.eclipse.uml2.uml.Property p1 : dt1.getAttributes())
		{
			p2 = myfactory.createProperty();
			DealProperty(p1, p2);
			dt2.getOwnedAttribute().add(p2);
		}
		
		// Enumeration Literals
		if (dt1 instanceof org.eclipse.uml2.uml.Enumeration)
		{
			org.eclipse.uml2.uml.Enumeration enum1 = (org.eclipse.uml2.uml.Enumeration) dt1;
			RefOntoUML.Enumeration enum2 = (RefOntoUML.Enumeration) dt2;
			
			RefOntoUML.EnumerationLiteral el2;
			for (org.eclipse.uml2.uml.EnumerationLiteral el1 : enum1.getOwnedLiterals())
			{
				System.out.print("    <EnumLiteral> ");
				el2 = myfactory.createEnumerationLiteral();
				DealNamedElement (el1, el2);
				
				enum2.getOwnedLiteral().add(el2);
			}
		}
		
		return dt2;
	}
		
	public void DealGeneralization (org.eclipse.uml2.uml.Generalization gen1)
	{
		System.out.print("[Generalization]: ");		
		RefOntoUML.Generalization gen2 = myfactory.createGeneralization();
		
		// source (Specific)
		org.eclipse.uml2.uml.Classifier e1 = (org.eclipse.uml2.uml.Classifier) gen1.getSpecific();
		RefOntoUML.Classifier e2 = (RefOntoUML.Classifier) GetElement(e1);
		System.out.print(e1.getName() + " -> ");
		
		// Poderia ter setado apenas um dos dois (Generalization::Specific, Classifier::Generalization), ja que sao EOpposites
		gen2.setSpecific(e2);
		// O Specific tem posse do generalization
		e2.getGeneralization().add(gen2);

		// target (General)
		e1 = (org.eclipse.uml2.uml.Classifier) gen1.getGeneral();
		e2 = (RefOntoUML.Classifier) GetElement(e1);
		System.out.println(e1.getName());

		gen2.setGeneral(e2);

		// Important for GeneralizationSet
		RelateElements (gen1, gen2);
	}
	
	public void ProcessGeneralizations (org.eclipse.uml2.uml.Classifier c1)
	{
		// Generalizations
		for (org.eclipse.uml2.uml.Generalization gen : c1.getGeneralizations())
		{
			DealGeneralization (gen);
		}	
	}
	
	public RefOntoUML.GeneralizationSet DealGeneralizationSet (org.eclipse.uml2.uml.GeneralizationSet gs1)
	{
		System.out.print("[Generalization Set] ");
		RefOntoUML.GeneralizationSet gs2 = myfactory.createGeneralizationSet();
		
		DealNamedElement(gs1, gs2);
				
		// Add all the generalizations
		for  (org.eclipse.uml2.uml.Generalization gen1 : gs1.getGeneralizations())
		{
			RefOntoUML.Generalization gen2 = (RefOntoUML.Generalization) GetElement(gen1);
			
			// Poderia ter setado apenas um dos dois (GeneralizationSet::Generalization, Generalization::GeneralizationSet), ja que sao EOpposites
			gs2.getGeneralization().add(gen2);
			gen2.getGeneralizationSet().add(gs2);
		}
		
		// isCovering, isDisjoint
		gs2.setIsCovering(gs1.isCovering());
		gs2.setIsDisjoint(gs1.isDisjoint());
		
		// They are PackageableElements, don't forget it
		RelateElements (gs1, gs2);
		
		return gs2;
	}
	
	public RefOntoUML.Dependency DealDependency (org.eclipse.uml2.uml.Dependency d1)
	{
		System.out.print("[Dependency] ");
		RefOntoUML.Dependency d2 = myfactory.createDependency();
		
		DealNamedElement(d1, d2);
		
		RefOntoUML.NamedElement ne2;
		
		// clients
		for (org.eclipse.uml2.uml.NamedElement ne1 : d1.getClients())
		{
			ne2 = (RefOntoUML.NamedElement) GetElement(ne1);
			d2.getClient().add(ne2);
		}
		
		// suppliers
		for (org.eclipse.uml2.uml.NamedElement ne1 : d1.getSuppliers())
		{
			ne2 = (RefOntoUML.NamedElement) GetElement(ne1);
			d2.getSupplier().add(ne2);
		}
		
		// They are PackageableElements, don't forget it
		RelateElements (d1, d2);
		
		return d2;
	}
	
	public void DealComment (org.eclipse.uml2.uml.Comment c1, RefOntoUML.Comment c2)
	{
		// ProcessComments (c1);
		System.out.println("// " + c1.getBody() + " ");
		
		// body
		c2.setBody(c1.getBody());
		// annotatedElements
		for (org.eclipse.uml2.uml.Element a1 : c1.getAnnotatedElements())
		{
			RefOntoUML.Element a2 = GetElement(a1);
			c2.getAnnotatedElement().add(a2);
		}
		
		RelateElements (c1, c2);
	}
	
	public void ProcessComments (org.eclipse.uml2.uml.Element e1)
	{
		RefOntoUML.Element e2 = GetElement (e1);
		
		// ownedComment
		RefOntoUML.Comment c2;
		for (org.eclipse.uml2.uml.Comment c1 : e1.getOwnedComments())
		{
			c2 = myfactory.createComment();
			DealComment (c1, c2);
			
			e2.getOwnedComment().add(c2);
		}
	}
	
	public void DealElementImport (org.eclipse.uml2.uml.ElementImport ei1)
	{
		System.out.print("<ElementImport> ");
		
		//org.eclipse.uml2.uml.PackageableElement pe1 = ei1.getImportedElement();
		// FIXME: I guess the Packageable element will be owned by the ElementImport and the ElementImport owned by the package...
	}
}
