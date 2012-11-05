package br.ufes.inf.nemo.ontouml.transformation.onto2info.serialize;

import java.io.Serializable;
import java.util.Map;

import br.ufes.inf.nemo.ontouml.transformation.onto2info.decision.Decision;
import br.ufes.inf.nemo.ontouml.transformation.onto2info.decision.UMLAttributeSlotString;

public class SerializedContent implements Serializable
{
	private static final long serialVersionUID = 6228772205045573368L;
	
	Map<String, String> idMap;
	Map<String, Decision> scopeIdMap;
	Map<String, Decision> historyIdMap;
	Map<String, Decision> timeIdMap;
	Map<String, Decision> referenceIdMap;
	Map<String, Decision> measurementIdMap;
	Map<String, UMLAttributeSlotString> attributeIdMap;
	String timePrimitiveId;
	String durationPrimitiveId;
	String booleanPrimitiveId;
}
