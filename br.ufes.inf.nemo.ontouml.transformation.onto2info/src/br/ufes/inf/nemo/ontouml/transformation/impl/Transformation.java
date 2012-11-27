package br.ufes.inf.nemo.ontouml.transformation.impl;

import br.ufes.inf.nemo.ontouml.transformation.onto2info.OntoUML2InfoUML;
import br.ufes.inf.nemo.ontouml.transformation.onto2info.decision.*;
import br.ufes.inf.nemo.ontouml.refontouml.util.*;
import br.ufes.inf.nemo.ontouml.transformation.onto2info.ui.Onto2InfoInterface;
import br.ufes.inf.nemo.ontouml.transformation.onto2info.uml.UMLModelAbstraction;
import br.ufes.inf.nemo.ontouml.transformation.onto2info.uml.Onto2UMLReplicator;

public class Transformation
{
	// OntoUML Model (Abstraction)
	RefOntoUMLModelAbstraction ontoAbstraction;
	// UML Model (Abstraction)
	UMLModelAbstraction umlAbstraction;
	// UML Factory (Abstraction)
	Onto2UMLReplicator fa;
	// Decision Handler
	DecisionHandler dh;
	// Interface
	public Onto2InfoInterface ui;

	public Transformation(RefOntoUMLModelAbstraction ontoAbstraction, UMLModelAbstraction umlAbstraction, Onto2InfoInterface ui)  
    { 
		this.ontoAbstraction = ontoAbstraction;
		this.umlAbstraction = umlAbstraction;
		this.ui = ui;
    	fa = new Onto2UMLReplicator();
    }
	
	public org.eclipse.uml2.uml.Model transform (DecisionHandler dh)
	{
		// Transformation from scratch
		boolean first = true;
		// Reset change log (additions and removals)
		Log.reset();
		this.dh = dh;
		
		try
		{
			if (umlAbstraction.umlmodel == null)
			{
				// Transformation from scratch
				umlAbstraction.umlmodel = fa.partiallyCreateModel(ontoAbstraction.model);
			}
			else
			{
				// Transformation over a pre-loaded UML.Model
				first = false;
			}
			
			(new Scope(this)).dealScope();
	        (new HistoryTracking(this)).dealHistoryTracking();
	        (new TimeTracking(this)).dealTimeTracking();
	        (new Reference(this)).dealReference();
	        (new Measurement(this)).dealMeasurement();

	        // Adds the PrimitiveTypes (time, duration, boolean) to the UML.Model
	        // I like them at the end of the model, rather than at the beginning
	        umlAbstraction.addPrimitiveTypes(this);
	        
	        // Saves the UML.Model in a file
	        umlAbstraction.save();
	        // Saves the Onto<->UML Mappings and Decisions in a file
	        OntoUML2InfoUML.saveMap();
	        
			//if (true) throw new RuntimeException(); // for debug
		}
		catch (Exception e)
		{
			ui.writeText("Could not perform the transformation. A terrible exception has happened.");
			OntoUML2InfoUML.exception();
			e.printStackTrace();
			return null;
		}
		
		ui.writeText("Transformation done" + (first ? "" : Log.message()));
		ui.refreshWorkspace();
        
        return umlAbstraction.umlmodel;
	}
}
