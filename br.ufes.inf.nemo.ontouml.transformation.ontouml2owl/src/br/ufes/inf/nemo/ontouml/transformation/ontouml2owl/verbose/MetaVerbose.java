package br.ufes.inf.nemo.ontouml.transformation.ontouml2owl.verbose;

public class MetaVerbose
{	
	public static String metaVerboseInit()
	{
		return
	    MainVerbose.commentBlock("Object Properties");
	}
	
	public static String allMetaVerbose ()
	{
		return
		
		MainVerbose.commentBlock("NonChangeable Structural Object Properties") +
		existentiallyDependentOf() +
		invExistentiallyDependentOf() +
		hasTimeExtent() +
		inheres() +
		inseparablePartOf() +
		mediates() +
		memberOf() +
		partOf() +
		subCollectionOf() +
		componentOf() +

		MainVerbose.commentBlock("NonChangeable Structural Classes") +
		individual() +
		object() +
		moment() +
		mode() +
		relator() +
		quaIndividual() +
		temporalExtent();
	}
	
	private static String existentiallyDependentOf()
	{
		return
		MainVerbose.header("existentiallyDependentOf") + 
		OWLVerbose.openObjectProperty("existentiallyDependentOf") +
		OWLVerbose.openCloseInverseOf("invExistentiallyDependentOf") +
		OWLVerbose.closeObjectProperty() +
		MainVerbose.sectionBreak();
	}
	
	private static String invExistentiallyDependentOf()
	{
		return
		MainVerbose.header("invExistentiallyDependentOf") + 
		OWLVerbose.openCloseObjectProperty("invExistentiallyDependentOf") +
		MainVerbose.sectionBreak();
	}

	private static String hasTimeExtent()
	{
		return
		MainVerbose.header("hastimeExtent") +
		OWLVerbose.openObjectProperty("hasTimeExtent") +
	    	OWLVerbose.openCloseType("AsymmetricProperty") +
	    	OWLVerbose.openCloseType("FunctionalProperty") +
	    	OWLVerbose.openCloseType("IrreflexiveProperty") +
	    	OWLVerbose.openCloseDomain("Individual") +
	    	OWLVerbose.openCloseRange("TemporalExtent") +
	    OWLVerbose.closeObjectProperty() +
	    MainVerbose.sectionBreak();
	}
	
	private static String inheres()
	{
		return
		MainVerbose.header("inheres") +
		OWLVerbose.openObjectProperty("inheres") +
			OWLVerbose.openCloseType("AsymmetricProperty") +
			OWLVerbose.openCloseType("FunctionalProperty") +
			OWLVerbose.openCloseType("IrreflexiveProperty") +
			OWLVerbose.openCloseSubPropertyOf("existentiallyDependentOf") +
		OWLVerbose.closeObjectProperty() +
		MainVerbose.sectionBreak();
	}
	
	private static String inseparablePartOf()
	{
		return
		MainVerbose.header("inseparablePartOf") +
		OWLVerbose.openObjectProperty("inseparablePartOf") +
			OWLVerbose.openCloseSubPropertyOf("existentiallyDependentOf") +
			OWLVerbose.openCloseSubPropertyOf("partOf") +
		OWLVerbose.closeObjectProperty() +
		MainVerbose.sectionBreak();
	}
	
	private static String mediates()
	{
		return
		MainVerbose.header("mediates") +
		OWLVerbose.openObjectProperty("mediates") +
			OWLVerbose.openCloseType("AsymmetricProperty") +
			OWLVerbose.openCloseType("IrreflexiveProperty") +
			OWLVerbose.openCloseSubPropertyOf("existentiallyDependentOf") +
		OWLVerbose.closeObjectProperty() +
		MainVerbose.sectionBreak();
	}
	
	private static String memberOf()
	{
		return
		MainVerbose.header("memberOf") +
		OWLVerbose.openObjectProperty("memberOf") +
			OWLVerbose.openCloseRange("Collective") +
			OWLVerbose.openCloseSubPropertyOf("partOf") +
			OWLVerbose.openDomain() +
				OWLVerbose.openClass() +
					OWLVerbose.openUnionOf("Collection") +
						OWLVerbose.openCloseDescription("Collective") +
						OWLVerbose.openCloseDescription("FunctionalComplex") +
					OWLVerbose.closeUnionOf() +
				OWLVerbose.closeClass() +
			OWLVerbose.closeDomain() +
		OWLVerbose.closeObjectProperty() +
		MainVerbose.sectionBreak();
	}
	
	private static String partOf()
	{
		return
		MainVerbose.header("partOf") +
		OWLVerbose.openCloseObjectProperty("partOf") +
		MainVerbose.sectionBreak();
	}
	
	private static String subCollectionOf()
	{
		return
		MainVerbose.header("subCollectionOf") +
		OWLVerbose.openObjectProperty("subCollectionOf") +
			OWLVerbose.openCloseRange("Collective") +
			OWLVerbose.openCloseDomain("Collective") +
			OWLVerbose.openCloseSubPropertyOf("partOf") +
		OWLVerbose.closeObjectProperty() +
		MainVerbose.sectionBreak();
	}
	
	private static String componentOf()
	{
		return
		MainVerbose.header("componentOf") +
		OWLVerbose.openObjectProperty("componentOf") +
			OWLVerbose.openCloseRange("FunctionalComplex") +
			OWLVerbose.openCloseDomain("FunctionalComplex") +
			OWLVerbose.openCloseSubPropertyOf("partOf") +
		OWLVerbose.closeObjectProperty() +
		MainVerbose.sectionBreak();
	}
	
	private static String individual()
	{
		return
		MainVerbose.header("Individual") +
		OWLVerbose.openClass("Individual") +
			OWLVerbose.openEquivalentClass() +
			OWLVerbose.openClass() +
				OWLVerbose.openUnionOf("Collection") +
				OWLVerbose.openCloseDescription("Object") +
				OWLVerbose.openCloseDescription("Moment") +
				OWLVerbose.closeUnionOf() +
			OWLVerbose.closeClass() +
			OWLVerbose.closeEquivalentClass() +
		
			OWLVerbose.openCloseDisjointWith("TemporalExtent") +
		OWLVerbose.closeClass() +
		MainVerbose.sectionBreak();
	}
	
	private static String object()
	{
		return
		MainVerbose.header("Object") +
		OWLVerbose.openClass("Object") +
			OWLVerbose.openCloseSubClassOf("Individual") +

			OWLVerbose.openEquivalentClass() +
			OWLVerbose.openClass() +
				OWLVerbose.openUnionOf("Collection") +
				OWLVerbose.openCloseDescription("FunctionalComplex") +
				OWLVerbose.openCloseDescription("Collective") +
//				OWLVerbose.openCloseDescription("Quantity") +
				OWLVerbose.closeUnionOf() +
			OWLVerbose.closeClass() +
			OWLVerbose.closeEquivalentClass() +

			OWLVerbose.openCloseDisjointWith("Moment") +
		OWLVerbose.closeClass() +
		MainVerbose.sectionBreak();
	}

	private static String moment()
	{
		return
		MainVerbose.header("Moment") +
		OWLVerbose.openClass("Moment") +
			OWLVerbose.openCloseSubClassOf("Individual") +

			OWLVerbose.openEquivalentClass() +
			OWLVerbose.openClass() +
				OWLVerbose.openUnionOf("Collection") +
				OWLVerbose.openCloseDescription("Mode") +
				OWLVerbose.openCloseDescription("Relator") +
				OWLVerbose.closeUnionOf() +
			OWLVerbose.closeClass() +
			OWLVerbose.closeEquivalentClass() +

			OWLVerbose.openCloseDisjointWith("Object") +
		OWLVerbose.closeClass() +
		MainVerbose.sectionBreak();
	}

	private static String mode()
	{
		return
		MainVerbose.header("Mode") +
		OWLVerbose.openClass("Mode") +
			OWLVerbose.openCloseSubClassOf("Moment") +
			OWLVerbose.openCloseDisjointWith("Relator") +
		OWLVerbose.closeClass() +
		MainVerbose.sectionBreak();
	}

	private static String temporalExtent()
	{
		return
		MainVerbose.header("TemporalExtent") +
		OWLVerbose.openClass("TemporalExtent") +
		OWLVerbose.openCloseDisjointWith("Individual") +
		OWLVerbose.closeClass() +
		MainVerbose.sectionBreak();
	}
		
	private static String quaIndividual()
	{
		return
		MainVerbose.header("QuaIndividual") +
		OWLVerbose.openClass("QuaIndividual") +
		 
			OWLVerbose.openCloseSubClassOf("Mode") +
			 
			 OWLVerbose.openSubClassOf() +
			 	OWLVerbose.openRestriction() +
			 		OWLVerbose.openCloseOnProperty("partOf") +
			 		OWLVerbose.openCloseSomeValuesFrom("Relator") +
			 	OWLVerbose.closeRestriction() +
			 OWLVerbose.closeSubClassOf() +
		 
			 OWLVerbose.openSubClassOf() +
			 	OWLVerbose.openRestriction() +
			 		OWLVerbose.openCloseOnProperty("existentiallyDependentOf") +
			 		OWLVerbose.openCloseSomeValuesFrom("QuaIndividual") +
			 	OWLVerbose.closeRestriction() +
			 OWLVerbose.closeSubClassOf() +
			 
			 OWLVerbose.openSubClassOf() +
			 	OWLVerbose.openRestriction() +
			 		OWLVerbose.openCloseOnProperty("inheres") +
			 		OWLVerbose.openCloseSomeValuesFrom("Individual") +
			 	OWLVerbose.closeRestriction() +
			 OWLVerbose.closeSubClassOf() +
			 
		OWLVerbose.closeClass() +
		MainVerbose.sectionBreak();
	}
	
	private static String relator()
	{
		return
		MainVerbose.header("Relator") +
		OWLVerbose.openClass("Relator") +
		
		OWLVerbose.openCloseSubClassOf("Moment") +

		OWLVerbose.openEquivalentClass() +
				OWLVerbose.openRestriction() +
					OWLVerbose.openCloseOnProperty("mediates") +
					OWLVerbose.openCloseOnClass("Individual") +
					OWLVerbose.openCloseMinQualifiedCardinality("2") +
				OWLVerbose.closeRestriction() +
			OWLVerbose.closeEquivalentClass() +
						
		OWLVerbose.closeClass() +
		MainVerbose.sectionBreak();
	}
}
