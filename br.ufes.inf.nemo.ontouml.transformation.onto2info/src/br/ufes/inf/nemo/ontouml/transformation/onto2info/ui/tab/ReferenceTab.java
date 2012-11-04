package br.ufes.inf.nemo.ontouml.transformation.onto2info.ui.tab;

import org.eclipse.jface.viewers.CheckboxTreeViewer;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.TreeViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.TreeItem;

import br.ufes.inf.nemo.ontouml.refontouml.util.RefOntoUMLModelAbstraction;
import br.ufes.inf.nemo.ontouml.transformation.onto2info.decision.DecisionHandler;
import br.ufes.inf.nemo.ontouml.transformation.onto2info.ui.content.ReferenceModel;
import br.ufes.inf.nemo.ontouml.transformation.onto2info.ui.content.SimpleContentProvider;

public class ReferenceTab implements Tab
{
	@Override
	public String getName()
	{
		return "Reference";
	}
		
	@Override
	public Control getControl(Composite parent, RefOntoUMLModelAbstraction ma, DecisionHandler dh)
	{
		final CheckboxTreeViewer treeViewer = new CheckboxTreeViewer(parent, SWT.BORDER | SWT.FULL_SELECTION);
		treeViewer.getTree().setLinesVisible(true);
		treeViewer.getTree().setHeaderVisible(true);
		
		// TODO: checkbox listener?
		
		// Column 1
		TreeViewerColumn column = new TreeViewerColumn(treeViewer, SWT.LEFT);
		column.getColumn().setWidth(200);
		column.getColumn().setMoveable(true);
		column.getColumn().setText("Universal");
		column.setLabelProvider(new ColumnLabelProvider()
		{
			public String getText(Object element)
			{
				return ((RefOntoUML.Class) element).getName();
			}

		});
		
		treeViewer.setContentProvider(new SimpleContentProvider());
		treeViewer.setInput((new ReferenceModel(ma)).model);
		treeViewer.expandAll();
				
		// Initialize the values of checkboxes according to previous Reference Decisions
		// Passing the top nodes of the Tree as parameter
		initializeCheckboxes (treeViewer.getTree().getItems(), dh);
		
		return treeViewer.getTree();
	}

	public void initializeCheckboxes (TreeItem[] items, DecisionHandler dh)
	{
		for (int i = 0; i < items.length; i++)
		{
			Object obj = items[i].getData();
			boolean value = dh.getReferenceDecision((RefOntoUML.Class)obj);
			items[i].setChecked(value);
			
			TreeItem[] children = items[i].getItems();
			initializeCheckboxes(children, dh);
		}
	}
}
