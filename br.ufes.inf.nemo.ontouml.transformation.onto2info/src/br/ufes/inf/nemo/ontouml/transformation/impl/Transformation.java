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

	// Number of additions in one transformation operation
	public int numAdditions;
	// Number of removals in one transformation operation
	public int numRemovals;

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
		// Initialize additions and removals made so far
		numAdditions = 0;
		numRemovals = 0;
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
		
		printSuccessMessage(first);
		ui.refreshWorkspace();
        
        return umlAbstraction.umlmodel;
	}

	public void printSuccessMessage (boolean first)
	{
		String extraText = "";
		
		if (!first)
		{
			extraText += " (";
			
			if (numAdditions == 0 && numRemovals == 0)
			{
				extraText += "no changes";
			}
			else
			{
				if (numAdditions != 0)
				{
					extraText += numAdditions + " addition" + (numAdditions == 1 ? "" : "s");
					if (numRemovals != 0)
						extraText += ", ";
				}
				if (numRemovals != 0)
					extraText += numRemovals + " removal" + (numRemovals == 1 ? "" : "s");
			}
			
			extraText += ")";
		}
		
		ui.writeText("Transformation done" + extraText);
	}
}