package br.ufes.inf.nemo.ontouml.transformation.onto2info.ui.tab;

import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.CheckStateChangedEvent;
import org.eclipse.jface.viewers.CheckboxTreeViewer;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.ColumnViewerEditor;
import org.eclipse.jface.viewers.ColumnViewerEditorActivationEvent;
import org.eclipse.jface.viewers.ColumnViewerEditorActivationStrategy;
import org.eclipse.jface.viewers.ComboBoxCellEditor;
import org.eclipse.jface.viewers.EditingSupport;
import org.eclipse.jface.viewers.FocusCellOwnerDrawHighlighter;
import org.eclipse.jface.viewers.ICheckStateListener;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.jface.viewers.TreeViewerColumn;
import org.eclipse.jface.viewers.TreeViewerEditor;
import org.eclipse.jface.viewers.TreeViewerFocusCellManager;
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
	public Control getControl(Composite parent, RefOntoUMLModelAbstraction ma, final DecisionHandler dh)
	{
		final CheckboxTreeViewer treeViewer = new CheckboxTreeViewer(parent, SWT.BORDER | SWT.FULL_SELECTION);
		treeViewer.getTree().setLinesVisible(true);
		treeViewer.getTree().setHeaderVisible(true);
		
		// Checkbox Listener
		treeViewer.addCheckStateListener(new ICheckStateListener()
		{
			public void checkStateChanged(CheckStateChangedEvent event)
			{
				dh.setReferenceDecision(event.getElement(), event.getChecked());
			}
		});
		
		// Actions when you focus (click, double click, etc.) the cell
		// Without this: one click = edit
		// With this: one click = select / double-click = edit
		/*TreeViewerFocusCellManager focusCellManager = new TreeViewerFocusCellManager(treeViewer, new FocusCellOwnerDrawHighlighter(treeViewer));
		ColumnViewerEditorActivationStrategy actSupport = new ColumnViewerEditorActivationStrategy(treeViewer)
		{
			protected boolean isEditorActivationEvent(ColumnViewerEditorActivationEvent event)
			{
				return event.eventType == ColumnViewerEditorActivationEvent.TRAVERSAL
						|| event.eventType == ColumnViewerEditorActivationEvent.MOUSE_DOUBLE_CLICK_SELECTION
						|| (event.eventType == ColumnViewerEditorActivationEvent.KEY_PRESSED && event.keyCode == SWT.CR)
						|| event.eventType == ColumnViewerEditorActivationEvent.PROGRAMMATIC;
			}
		};
		TreeViewerEditor.create(treeViewer, focusCellManager, actSupport,
				ColumnViewerEditor.TABBING_HORIZONTAL
						| ColumnViewerEditor.TABBING_MOVE_TO_ROW_NEIGHBOR
						| ColumnViewerEditor.TABBING_VERTICAL
						| ColumnViewerEditor.KEYBOARD_ACTIVATION);*/
		
		
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
		
		// Column 2
		column = new TreeViewerColumn(treeViewer, SWT.NONE);
		column.getColumn().setWidth(200);
		column.getColumn().setMoveable(true);
		column.getColumn().setText("Attribute Name");
		column.setLabelProvider(new ColumnLabelProvider()
		{
			public String getText(Object element)
			{
				return dh.getReferenceAttributeName((RefOntoUML.Class)element);
			}
		});
		// Based on org.eclipse.jface.snippets.viewers.snippet026
		final TextCellEditor textCellEditor = new TextCellEditor(treeViewer.getTree());		
		column.setEditingSupport(new EditingSupport(treeViewer)
		{
			protected boolean canEdit(Object element)
			{
				return true;
			}

			protected CellEditor getCellEditor(Object element)
			{
				return textCellEditor;
			}

			protected Object getValue(Object element)
			{
				return dh.getReferenceAttributeName((RefOntoUML.Class)element);
			}

			protected void setValue(Object element, Object value)
			{
				dh.setReferenceAttributeName(element, value.toString());
				treeViewer.update(element, null);
			}
		});
		
		// Column 3
		column = new TreeViewerColumn(treeViewer, SWT.NONE);
		column.getColumn().setWidth(200);
		column.getColumn().setMoveable(true);
		column.getColumn().setText("Attribute Type");
		column.setLabelProvider(new ColumnLabelProvider()
		{
			public String getText(Object element)
			{
				return "batata frita"; //dh.getReferenceAttributeName((RefOntoUML.Class)element); // FIXME
			}
		});
		// Based on org.eclipse.jface.snippets.viewers.snippet027
		final ComboBoxCellEditor comboBoxCellEditor = new ComboBoxCellEditor(treeViewer.getTree(), new String[] {"int", "string", "custom"});
		column.setEditingSupport(new EditingSupport(treeViewer)
		{
			protected boolean canEdit(Object element)
			{
				return true;
			}

			protected CellEditor getCellEditor(Object element)
			{
				return comboBoxCellEditor;
			}

			protected Object getValue(Object element)
			{
				return 0;//dh.getReferenceAttributeName((RefOntoUML.Class)element); // FIXME
			}

			protected void setValue(Object element, Object value)
			{
				System.out.println("setValue() " + value);
				//dh.setReferenceAttributeName(element, value.toString()); // FIXME
				treeViewer.update(element, null);
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
