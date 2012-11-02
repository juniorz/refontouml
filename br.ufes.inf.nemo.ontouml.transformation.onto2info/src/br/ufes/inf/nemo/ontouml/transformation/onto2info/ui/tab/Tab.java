package br.ufes.inf.nemo.ontouml.transformation.onto2info.ui.tab;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

import br.ufes.inf.nemo.ontouml.refontouml.util.RefOntoUMLModelAbstraction;
import br.ufes.inf.nemo.ontouml.transformation.onto2info.decision.DecisionHandler;

public interface Tab
{
	public String getName();
	public Control getControl(Composite parent, RefOntoUMLModelAbstraction ma, final DecisionHandler dh);
}
