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
		existentiallyDependentOf() +
		hasTimeExtent() +
		inheres() +
		inseparablePartOf() +
		mediates() +
		memberOf() +
		partOf() +
		subCollectionOf() +
		individual() +
		temporalExtent() +
		quaIndividual() +
		relator();
	}
	
	private static String existentiallyDependentOf()
	{
		return
		MainVerbose.header("existentiallyDependentOf") + 
		OWLVerbose.openCloseObjectProperty("existentiallyDependentOf") +
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
	
	private static String individual()
	{
		return
		MainVerbose.header("Individual") +
		OWLVerbose.openClass("Individual") +
			OWLVerbose.openCloseDisjointWith("TemporalExtent") +
		OWLVerbose.closeClass() +
		MainVerbose.sectionBreak();
	}
	
	private static String temporalExtent()
	{
		return
		MainVerbose.header("TemporalExtent") +
		OWLVerbose.openCloseClass("TemporalExtent") +
		MainVerbose.sectionBreak();
	}
		
	private static String quaIndividual()
	{
		return
		MainVerbose.header("QuaIndividual") +
		OWLVerbose.openClass("QuaIndividual") +
		 
			OWLVerbose.openCloseSubClassOf("Individual") +
			 
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
		
			OWLVerbose.openEquivalentClass() +
				OWLVerbose.openRestriction() +
					OWLVerbose.openCloseOnProperty("mediates") +
					OWLVerbose.openCloseOnClass("Individual") +
					OWLVerbose.openCloseMinQualifiedCardinality("2") +
				OWLVerbose.closeRestriction() +
			OWLVerbose.closeEquivalentClass() +
			
			OWLVerbose.openCloseSubClassOf("Individual") +
			
		OWLVerbose.closeClass() +
		MainVerbose.sectionBreak();
	}
}
