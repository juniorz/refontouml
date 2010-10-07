package br.ufes.inf.nemo.ontouml.transformation.ontouml2owl.verbose;

import java.util.List;

import br.ufes.inf.nemo.ontouml.transformation.ontouml2owl.auxiliary.MediatedEnd;
import br.ufes.inf.nemo.ontouml.transformation.ontouml2owl.auxiliary.MemberOfRelation;

public class DomainVerbose
{
	private static String allClasses (List<String> names)
	{
		String out = "";
		for (String n : names)
		{
			out += OWLVerbose.openCloseDescription(n);
		}
		return out;
	}
	
	public static String meronymic (String type, String part, String whole, boolean isShareable, boolean isInseparable)
	{		
		return
		MainVerbose.header(type + part + whole) +
		OWLVerbose.openObjectProperty(type + part + whole) +
			(!isShareable ? OWLVerbose.openCloseType("FunctionalProperty") : "")  +
			OWLVerbose.openCloseDomain(part) +
			OWLVerbose.openCloseRange(whole) +
			(isInseparable ? OWLVerbose.openCloseSubPropertyOf("inseparablePartOf") : "") +
			OWLVerbose.openCloseSubPropertyOf(type) +
		OWLVerbose.closeObjectProperty() +
		MainVerbose.sectionBreak();
	}
	
	public static String allKinds (List<String> names)
	{
		return
		OWLVerbose.openClass("FunctionalComplex") +
		
			OWLVerbose.openEquivalentClass() +
				OWLVerbose.openClass() +
					OWLVerbose.openUnionOf("Collection") +
						allClasses(names) +
					OWLVerbose.closeUnionOf() +
				OWLVerbose.closeClass() +
			OWLVerbose.closeEquivalentClass() +
			
			OWLVerbose.openCloseSubClassOf("Individual") +
		OWLVerbose.closeClass() +
		MainVerbose.sectionBreak();
	}
	
	public static String allCollectives (List<String> names)
	{
		return
		OWLVerbose.openClass("Collective") +
		
			// EquivalentClass 1
			OWLVerbose.openEquivalentClass() +
				OWLVerbose.openClass() +
					OWLVerbose.openUnionOf("Collection") +
					
						// Restriction 1
						OWLVerbose.openRestriction() +
							OWLVerbose.openOnProperty() +
								OWLVerbose.openObjectProperty() +
									OWLVerbose.openCloseInverseOf("memberOf") +
								OWLVerbose.closeObjectProperty() +
							OWLVerbose.closeOnProperty() +
							
							OWLVerbose.openSomeValuesFrom() +
								OWLVerbose.openClass() +
									OWLVerbose.openUnionOf("Collection") +
										OWLVerbose.openCloseDescription("Collective") +
										OWLVerbose.openCloseDescription("FunctionalComplex") +
									OWLVerbose.closeUnionOf() +
								OWLVerbose.closeClass() +
							OWLVerbose.closeSomeValuesFrom() +
						OWLVerbose.closeRestriction() +
						
						// Restriction 2
						OWLVerbose.openRestriction() +
							OWLVerbose.openOnProperty() +
								OWLVerbose.openObjectProperty() +
									OWLVerbose.openCloseInverseOf("subCollectionOf") +
								OWLVerbose.closeObjectProperty() +
							OWLVerbose.closeOnProperty() +
							
							OWLVerbose.openCloseSomeValuesFrom("Collective") +
						OWLVerbose.closeRestriction() +
						
					OWLVerbose.closeUnionOf() +
				OWLVerbose.closeClass() +
			OWLVerbose.closeEquivalentClass() +
			
			// EquivalentClass 2
			OWLVerbose.openEquivalentClass() +
				OWLVerbose.openClass() +
					OWLVerbose.openUnionOf("Collection") +
						allClasses(names) +
					OWLVerbose.closeUnionOf() +
				OWLVerbose.closeClass() +
			OWLVerbose.closeEquivalentClass() +
			
			// SubClassOf
			OWLVerbose.openCloseSubClassOf("Individual") +
			
		OWLVerbose.closeClass() +
		MainVerbose.sectionBreak();
	}
	
	public static String  kind (String kindName, List<List<String>> subKindPartitions, List<String> disjointClasses)
	{
		String out1 = "";
		String out2 = "";
		
		for (List<String> partition : subKindPartitions)
		{
			OWLVerbose.increaseIdent();
			
			out1 += OWLVerbose.openEquivalentClass();
			out1 += OWLVerbose.openClass();
			out1 += OWLVerbose.openUnionOf("Collection");
			
			for (String subclass : partition)
			{
				out1 += OWLVerbose.openCloseDescription(subclass);
			}
			
			out1 += OWLVerbose.closeUnionOf();
			out1 += OWLVerbose.closeClass();
			out1 += OWLVerbose.closeEquivalentClass();
			
			OWLVerbose.decreaseIdent();
		}
		
		OWLVerbose.increaseIdent();
		for (String dclass : disjointClasses)
		{
			out2 += OWLVerbose.openCloseDisjointWith(dclass);
		}
		OWLVerbose.decreaseIdent();
		
		return
		MainVerbose.header(kindName) +
		OWLVerbose.openClass(kindName) +
			out1 +
			OWLVerbose.openCloseSubClassOf("FunctionalComplex") +
			out2 +
		OWLVerbose.closeClass() +
		MainVerbose.sectionBreak();
	}
	
	public static String subKind (String subKindName, String kindName, List<String> disjointClasses)
	{
		String out2 = "";
		
		OWLVerbose.increaseIdent();
		for (String dclass : disjointClasses)
		{
			out2 += OWLVerbose.openCloseDisjointWith(dclass);
		}
		OWLVerbose.decreaseIdent();
		
		return
		MainVerbose.header(subKindName) +
		OWLVerbose.openClass(subKindName) +
			OWLVerbose.openCloseSubClassOf(kindName) +
			out2 +
		OWLVerbose.closeClass() +
		MainVerbose.sectionBreak();
	}
	
	public static String category (String categoryName, List<List<String>> childPartitions)
	{
		String out1 = "";
		
		for (List<String> partition : childPartitions)
		{
			OWLVerbose.increaseIdent();
			
			out1 += OWLVerbose.openEquivalentClass();
			out1 += OWLVerbose.openClass();
			out1 += OWLVerbose.openUnionOf("Collection");
			
			for (String subclass : partition)
			{
				out1 += OWLVerbose.openCloseDescription(subclass);
			}
			
			out1 += OWLVerbose.closeUnionOf();
			out1 += OWLVerbose.closeClass();
			out1 += OWLVerbose.closeEquivalentClass();
			
			OWLVerbose.decreaseIdent();
		}
		
		return
		MainVerbose.header(categoryName) +
		OWLVerbose.openClass(categoryName) +
			out1 +
			OWLVerbose.openCloseSubClassOf("Individual") +
		OWLVerbose.closeClass() +
		MainVerbose.sectionBreak();
	}
	
	private static String allRoles (List<MediatedEnd> ends)
	{
		String out = "";
		for (MediatedEnd e : ends)
		{
			out += OWLVerbose.openCloseDescription("Qua" + e.roleName);
		}
		return out;
	}
	
	private static String allMediationsAndRigid (List<MediatedEnd> ends)
	{
		String out = "";
		for (MediatedEnd e : ends)
		{
			out += OWLVerbose.openRestriction();
				// Mediates
				out += OWLVerbose.openCloseOnProperty("mediates");
				
				// Substance Sortal
				out += OWLVerbose.openCloseOnClass(e.substanceSortalName);
				
				// Cardinalities
				if (e.minCardinality == e.maxCardinality)
				{
					out += OWLVerbose.openCloseQualifiedCardinality(Integer.toString(e.minCardinality));
				}
				else
				{
					out += OWLVerbose.openCloseMinQualifiedCardinality(Integer.toString(e.minCardinality));
					// TODO: What if maxCardinality is Infinite?
					if (e.maxCardinality != -1)
						out += OWLVerbose.openCloseMaxQualifiedCardinality(Integer.toString(e.maxCardinality));
				}
				
			out += OWLVerbose.closeRestriction();
		}
		return out;
	}
	
	private static String allMediationsAndRoles (List<MediatedEnd> ends)
	{
		String out = "";
		for (MediatedEnd e : ends)
		{
			out += OWLVerbose.openRestriction();
				// inverseOf partOf
				out += OWLVerbose.openOnProperty();
					out += OWLVerbose.openObjectProperty();
						out += OWLVerbose.openCloseInverseOf("partOf");
					out += OWLVerbose.closeObjectProperty();
				out += OWLVerbose.closeOnProperty();
				
				// Role
				out += OWLVerbose.openCloseOnClass("Qua" + e.roleName);
				
				// Cardinalities
				if (e.minCardinality == e.maxCardinality)
				{
					out += OWLVerbose.openCloseQualifiedCardinality(Integer.toString(e.minCardinality));
				}
				else
				{
					out += OWLVerbose.openCloseMinQualifiedCardinality(Integer.toString(e.minCardinality));
					// TODO: What if maxCardinality is Infinite?
					if (e.maxCardinality != -1)
						out += OWLVerbose.openCloseMaxQualifiedCardinality(Integer.toString(e.maxCardinality));
				}
				
			out += OWLVerbose.closeRestriction();
		}
		return out;
	}
	
	private static String allRigids (List<MediatedEnd> ends)
	{
		String out = "";
		for (MediatedEnd e : ends)
		{
			out += OWLVerbose.openCloseDescription(e.substanceSortalName);
		}
		return out;
	}
	
	public static String relator (String relatorName, List<MediatedEnd> ends)
	{
		return
		MainVerbose.header(relatorName) +
		OWLVerbose.openClass(relatorName) +
			// SubClass of Relator
			OWLVerbose.openCloseSubClassOf("Relator") +
			
			// SubClass of the Union of Roles
			OWLVerbose.openSubClassOf() +
				OWLVerbose.openRestriction() +
				
					// OnProperty
					OWLVerbose.openOnProperty() +
						OWLVerbose.openObjectProperty() +
							OWLVerbose.openCloseInverseOf("partOf") +
						OWLVerbose.closeObjectProperty() +
					OWLVerbose.closeOnProperty() +
					
					// Roles
					OWLVerbose.openAllValuesFrom() +
						OWLVerbose.openClass() +
							OWLVerbose.openUnionOf("Collection") +
								allRoles(ends) +
							OWLVerbose.closeUnionOf() +
						OWLVerbose.closeClass() +
					OWLVerbose.closeAllValuesFrom() +
					
				OWLVerbose.closeRestriction() +	
			OWLVerbose.closeSubClassOf() +
			
			// Substance Sortal of Mediated Ends and Cardinality of Mediations
			OWLVerbose.openSubClassOf() +
				OWLVerbose.openClass() +
					OWLVerbose.openIntersectionOf("Collection") +
						allMediationsAndRigid(ends) +
					OWLVerbose.closeIntersectionOf() +
				OWLVerbose.closeClass() +
			OWLVerbose.closeSubClassOf() +
			
			// Roles of Mediated Ends and Cardinality of Mediations
			OWLVerbose.openSubClassOf() +
				OWLVerbose.openClass() +
					OWLVerbose.openIntersectionOf("Collection") +
						allMediationsAndRoles(ends) +
						OWLVerbose.closeIntersectionOf() +
				OWLVerbose.closeClass() +
			OWLVerbose.closeSubClassOf() +
		
			// All Substance Sortals
			OWLVerbose.openSubClassOf() +
				OWLVerbose.openRestriction() +
					OWLVerbose.openCloseOnProperty("mediates") +
					OWLVerbose.openAllValuesFrom() +
						OWLVerbose.openClass() +
							OWLVerbose.openUnionOf("Collection") +
								allRigids(ends) +
							OWLVerbose.closeUnionOf() +
						OWLVerbose.closeClass() +
					OWLVerbose.closeAllValuesFrom() +
				OWLVerbose.closeRestriction() +
			OWLVerbose.closeSubClassOf() +
			
		OWLVerbose.closeClass() +
		MainVerbose.sectionBreak();
	}
	
	private static String allMemberOfRelations (List<MemberOfRelation> memberOfRelations)
	{
		String out = "";
		
		for (MemberOfRelation relation : memberOfRelations)
		{
			out += OWLVerbose.openCloseInverseOf(relation.resourceName);
		}
		
		return out;
	}
	
	private static String allMemberOfParts (List<MemberOfRelation> memberOfRelations)
	{
		String out = "";
		
		if (memberOfRelations.size() == 1)
		{
			out = OWLVerbose.openCloseAllValuesFrom(memberOfRelations.get(0).partName);
		}
		else
		{
			out += OWLVerbose.openAllValuesFrom();
				out += OWLVerbose.openClass();
					out += OWLVerbose.openUnionOf("Collection");
					
						for (MemberOfRelation relation : memberOfRelations)
						{
							out += OWLVerbose.openCloseDescription(relation.partName);
						}
					
					out += OWLVerbose.closeUnionOf();
				out += OWLVerbose.closeClass();
			out += OWLVerbose.closeAllValuesFrom();
		}
		
		return out;
	}
	
	private static String allMemberOfRelationsAndParts (List<MemberOfRelation> memberOfRelations)
	{
		String out = "";
		
		for (MemberOfRelation relation : memberOfRelations)
		{
			out += OWLVerbose.openRestriction();
			
				out+= OWLVerbose.openOnProperty();
					out += OWLVerbose.openObjectProperty();
						out += OWLVerbose.openCloseInverseOf(relation.resourceName);
					out += OWLVerbose.closeObjectProperty();
				out += OWLVerbose.closeOnProperty();
				
				out += OWLVerbose.openCloseSomeValuesFrom(relation.partName);
			out += OWLVerbose.closeRestriction();
		}
		
		return out;
	}
	
	public static String collective (String collectiveName, List<MemberOfRelation> memberOfRelations)
	{
		return
		MainVerbose.header(collectiveName) +
		OWLVerbose.openClass(collectiveName) +
			// SubClass of Collective
			OWLVerbose.openCloseSubClassOf("Collective") +
		
			//
			OWLVerbose.openSubClassOf() +
				OWLVerbose.openRestriction() +
					// <DUVIDA>
					OWLVerbose.openOnProperty() +
						OWLVerbose.openObjectProperty() +
							// FIXME: Uma linha por memberOf?
							allMemberOfRelations(memberOfRelations) +
						OWLVerbose.closeObjectProperty() +
					OWLVerbose.closeOnProperty() +
					// </DUVIDA>
					
					allMemberOfParts(memberOfRelations) +
				OWLVerbose.closeRestriction() +
			OWLVerbose.closeSubClassOf() +
			
			//
			OWLVerbose.openSubClassOf() +
				allMemberOfRelationsAndParts(memberOfRelations) +
			OWLVerbose.closeSubClassOf() +
		OWLVerbose.closeClass() +
		MainVerbose.sectionBreak();
	}
	
	private static String allConnectedRoles (List<String> connectedRoles)
	{
		String out = "";
		
		for (String name : connectedRoles)
		{
			out += OWLVerbose.openRestriction();
				out += OWLVerbose.openCloseOnProperty("existentiallyDependentOf");
				out += OWLVerbose.openCloseSomeValuesFrom("Qua" + name);
			out += OWLVerbose.closeRestriction();
		}
		
		return out;
	}
	
	private static String disjointRoles (List<String> connectedRoles)
	{
		String out = "";
		
		for (String name : connectedRoles)
		{
			out += OWLVerbose.openCloseDisjointWith(name);
		}
		
		return out;
	}
	
	public static String role (String roleName, String relatorName, String substanceSortalName, List<String> connectedRoles)
	{
		return
		MainVerbose.header(roleName) +
			OWLVerbose.openClass("Qua" + roleName) +
				// SubClass of QuaIndividual
				OWLVerbose.openCloseSubClassOf("QuaIndividual") +
				
				// All connected roles
				OWLVerbose.openSubClassOf() +
					allConnectedRoles (connectedRoles) +
				OWLVerbose.closeSubClassOf() +
				
				// Relator
				OWLVerbose.openSubClassOf() +
					OWLVerbose.openRestriction() +
						OWLVerbose.openCloseOnProperty("partOf") +
						OWLVerbose.openCloseSomeValuesFrom(relatorName) +
					OWLVerbose.closeRestriction() +
				OWLVerbose.closeSubClassOf() +
				
				// Substance Sortal
				OWLVerbose.openSubClassOf() +
					OWLVerbose.openRestriction() +
						OWLVerbose.openCloseOnProperty("inheres") +
						OWLVerbose.openCloseSomeValuesFrom(substanceSortalName) +
					OWLVerbose.closeRestriction() +
				OWLVerbose.closeSubClassOf() +
				
				// Disjoint from connected roles
				disjointRoles (connectedRoles) +
			OWLVerbose.closeClass() +
		MainVerbose.sectionBreak();
	}
}