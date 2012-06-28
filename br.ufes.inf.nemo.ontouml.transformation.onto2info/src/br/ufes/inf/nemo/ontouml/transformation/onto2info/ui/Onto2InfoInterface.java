package br.ufes.inf.nemo.ontouml.transformation.onto2info.ui;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.CheckStateChangedEvent;
import org.eclipse.jface.viewers.CheckboxTreeViewer;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.ColumnViewerEditor;
import org.eclipse.jface.viewers.ColumnViewerEditorActivationStrategy;
import org.eclipse.jface.viewers.EditingSupport;
import org.eclipse.jface.viewers.FocusCellOwnerDrawHighlighter;
import org.eclipse.jface.viewers.ICheckStateListener;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.TableViewerEditor;
import org.eclipse.jface.viewers.TableViewerFocusCellManager;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.TreeViewerColumn;
import org.eclipse.jface.viewers.TreeViewerEditor;
import org.eclipse.jface.viewers.TreeViewerFocusCellManager;
import org.eclipse.jface.viewers.ViewerCell;
import org.eclipse.swt.*;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.*;

import br.ufes.inf.nemo.ontouml.refontouml.util.RefOntoUMLModelAbstraction;
import br.ufes.inf.nemo.ontouml.transformation.onto2info.Transformation;
import br.ufes.inf.nemo.ontouml.transformation.onto2info.decision.DecisionHandler;

// Tree with columns: Snippet 170, 193, 220, 226, 274, 312
public class Onto2InfoInterface
{
	public Onto2InfoInterface (final RefOntoUMLModelAbstraction ma, final DecisionHandler dh, final Transformation t)
	{
		Display display = new Display();
		final Shell shell = new Shell(display);
		shell.setText("OntoUML to UML Transformation");
		GridLayout layout = new GridLayout();
		layout.numColumns = 1;
		shell.setLayout(layout);
			
		// Tab Folder
		final TabFolder tabFolder = new TabFolder (shell, SWT.BORDER);
		Rectangle clientArea = shell.getClientArea();
		tabFolder.setLocation (clientArea.x, clientArea.y);
		for (int i=0; i<5; i++)
		{
			TabItem item = new TabItem (tabFolder, SWT.NONE);
			item.setText (getTabName(i));
			
			if (i == 0)
			{
				item.setControl(treeViewerScope(tabFolder, ma, dh).getTree());
			}
			else if (i == 2)
			{
				item.setControl(treeViewerHistory(tabFolder, ma, dh).getTree());
			}
			else if (i == 3)
			{
				//item.setControl(tableViewer(tabFolder, ma, dh).getTable());
				item.setControl(treeViewerTime(tabFolder, ma, dh).getTree());
			}
			else
			{
				//Button button = new Button (tabFolder, SWT.PUSH);
				//button.setText ("Page " + i);
				//item.setControl (button);
			}
		}
		tabFolder.pack ();
		
		// Transform Button
		Button tbutton = new Button(shell, SWT.PUSH);
		tbutton.setText("Transform");
		tbutton.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		
		// "Details" Group
		final Group group = new Group(shell, SWT.SHADOW_ETCHED_IN);
	    group.setText("Details");
	    group.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false, 1, 1));
	    
	    // "Details" Group Layout
	    GridLayout groupLayout = new GridLayout();
	    groupLayout.numColumns = 1;	    
	    group.setLayout(groupLayout); 
	    
	    // Text in "Details" Group
	    final Text text = new Text(group, SWT.READ_ONLY | SWT.H_SCROLL | SWT.V_SCROLL);
	    GridData textLayoutData = new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1);
	    textLayoutData.heightHint = 100;
	    text.setLayoutData(textLayoutData);
	    
	    // Display Text in "Details" Group
	    DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
		Date date = new Date();
		text.append(dateFormat.format(date) + "> Ready\n");
	    
	    // Transform Button Action
		tbutton.addSelectionListener(new SelectionAdapter()
		{
			public void widgetSelected(SelectionEvent e)
			{
				org.eclipse.uml2.uml.Model umlmodel = t.transform(ma, dh);
				
				// Display Text in "Details" Group
				DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
				Date date = new Date();
				text.append(dateFormat.format(date) + "> Transformation done\n");
			}
		});
		
		// Shell
		shell.pack();		
		shell.open();
		while (!shell.isDisposed())
		{
			if (!display.readAndDispatch()) display.sleep();
		}
		display.dispose();
	}
	
	@SuppressWarnings("deprecation")
	public TreeViewer treeViewerScope (Composite parent, RefOntoUMLModelAbstraction ma, final DecisionHandler dh)
	{
		final CheckboxTreeViewer treeViewer = new CheckboxTreeViewer(parent, SWT.BORDER | SWT.FULL_SELECTION);
		treeViewer.getTree().setLinesVisible(true);
		treeViewer.getTree().setHeaderVisible(true);
		
		// When user checks a checkbox in the tree, check all its children
		treeViewer.addCheckStateListener(new ICheckStateListener()
		{
			public void checkStateChanged(CheckStateChangedEvent event)
			{
				// if the item is checked, checks all children
				// if the item is unchecked, unchecks all children
				Object target = event.getElement();
				boolean value = event.getChecked();
				treeViewer.setSubtreeChecked(target, value);
	
				// Set Scope
				// The target element and all its (direct and indirect) children must change scope
				ITreeContentProvider cp = (ITreeContentProvider) treeViewer.getContentProvider();
				changeScope(target, value, cp);
			}
			
			public void changeScope (Object element, boolean value, ITreeContentProvider cp)
			{
				// Change the scope of "element"
				dh.setScopeDecision(element, value);
				
				Object[] children = cp.getChildren(element);
				for (int i = 0; i < children.length; i++)
				{
					changeScope (children[i], value, cp);
				}
			}
		});
		
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
		
		treeViewer.setContentProvider(new ScopeContentProvider());
		treeViewer.setInput(new ScopeModel(ma));
		treeViewer.expandAll();
		treeViewer.setAllChecked(true);
		return treeViewer;
	}
	
	public TreeViewer treeViewerHistoryAndTime (final Composite parent, RefOntoUMLModelAbstraction ma, final DecisionHandler dh)
	{
		final TreeViewer viewer = new TreeViewer(parent, SWT.BORDER | SWT.FULL_SELECTION);
		viewer.getTree().setLinesVisible(true);
		viewer.getTree().setHeaderVisible(true);
		viewer.getTree().setBackgroundMode(SWT.INHERIT_DEFAULT);
		
		// The focusing thing... FIXME: I'm not sure if I need those things
		FocusCellOwnerDrawHighlighter h = new FocusCellOwnerDrawHighlighter(viewer)
		{
			protected Color getSelectedCellBackgroundColorNoFocus(ViewerCell cell)
			{
				//return parent.getDisplay().getSystemColor(SWT.COLOR_WHITE);
				 return parent.getDisplay().getSystemColor(SWT.COLOR_LIST_SELECTION);
			}

			protected Color getSelectedCellForegroundColorNoFocus(ViewerCell cell)
			{
				// I'm not sure where this color appears
				//return parent.getDisplay().getSystemColor(SWT.COLOR_WHITE);
				return parent.getDisplay().getSystemColor(SWT.COLOR_WIDGET_FOREGROUND);
			}
		};

		TreeViewerFocusCellManager focusCellManager = new TreeViewerFocusCellManager(viewer, h);
		ColumnViewerEditorActivationStrategy actSupport = new ColumnViewerEditorActivationStrategy(viewer);

		TreeViewerEditor.create(viewer, focusCellManager, actSupport,
				ColumnViewerEditor.TABBING_HORIZONTAL |
				ColumnViewerEditor.TABBING_MOVE_TO_ROW_NEIGHBOR |
				ColumnViewerEditor.TABBING_VERTICAL |
				ColumnViewerEditor.KEYBOARD_ACTIVATION);
				
		// Column 1 (Universal)
		TreeViewerColumn column = new TreeViewerColumn(viewer, SWT.LEFT);
		column.getColumn().setText("Universal");
		column.getColumn().setWidth(200);
		column.setLabelProvider(new ColumnLabelProvider()
		{
			public String getText(Object element)
			{
				return ((RefOntoUML.Class)element).getName();
			}
		});		
		return viewer;
	}
	
	public TreeViewer treeViewerTime (final Composite parent, RefOntoUMLModelAbstraction ma, final DecisionHandler dh)
	{
		final TreeViewer viewer = treeViewerHistoryAndTime (parent, ma, dh);
		
		// BooleanCellEditor is a custom class created by a guy in JFace Snippets
		final BooleanCellEditor booleanCellEditor = new BooleanCellEditor(viewer.getTree());
		booleanCellEditor.setChangeOnActivation(true);
		
		// Column 2 (Start Time)
		TreeViewerColumn column = new TreeViewerColumn(viewer, SWT.CENTER);
		column.getColumn().setWidth(200);
		column.getColumn().setMoveable(true);
		column.getColumn().setText("Start Time");
		column.setLabelProvider(new EmulatedNativeCheckBoxLabelProvider(viewer)
		{
			protected boolean isChecked(Object element)
			{
				//System.out.println(((RefOntoUML.Class)element).getName());
				return dh.getStartTimeDecision((RefOntoUML.Class)element);
			}
		});
		column.setEditingSupport(new EditingSupport(viewer)
		{
			protected boolean canEdit(Object element)
			{
				return true;
			}

			protected CellEditor getCellEditor(Object element)
			{
				return booleanCellEditor;
			}

			protected Object getValue(Object element)
			{
				//System.out.println("getValue(" + ((RefOntoUML.Class)element).getName() + ")");
				return new Boolean(dh.getStartTimeDecision((RefOntoUML.Class)element));
			}

			protected void setValue(Object element, Object value)
			{
				//((File) element).read = ((Boolean) value).booleanValue();
				dh.setStartTimeDecision((RefOntoUML.Class)element, ((Boolean) value).booleanValue());
				viewer.update(element, null);
			}
		});
		
		// Column 3 (End Time)
		column = new TreeViewerColumn(viewer, SWT.CENTER);
		column.getColumn().setWidth(200);
		column.getColumn().setMoveable(true);
		column.getColumn().setText("End Time");
		column.setLabelProvider(new EmulatedNativeCheckBoxLabelProvider(viewer)
		{
			protected boolean isChecked(Object element)
			{
				//System.out.println(((RefOntoUML.Class)element).getName());
				return dh.getEndTimeDecision((RefOntoUML.Class)element);
			}
		});
		column.setEditingSupport(new EditingSupport(viewer)
		{
			protected boolean canEdit(Object element)
			{
				return true;
			}

			protected CellEditor getCellEditor(Object element)
			{
				return booleanCellEditor;
			}

			protected Object getValue(Object element)
			{
				//System.out.println("getValue(" + ((RefOntoUML.Class)element).getName() + ")");
				return new Boolean(dh.getEndTimeDecision((RefOntoUML.Class)element));
			}

			protected void setValue(Object element, Object value)
			{
				dh.setEndTimeDecision((RefOntoUML.Class)element, ((Boolean) value).booleanValue());
				viewer.update(element, null);
			}
		});
		
		// Column 4 (Duration)
		column = new TreeViewerColumn(viewer, SWT.CENTER);
		column.getColumn().setWidth(200);
		column.getColumn().setMoveable(true);
		column.getColumn().setText("Duration");
		column.setLabelProvider(new EmulatedNativeCheckBoxLabelProvider(viewer)
		{
			protected boolean isChecked(Object element)
			{
				//System.out.println(((RefOntoUML.Class)element).getName());
				return dh.getDurationDecision((RefOntoUML.Class)element);
			}
		});
		column.setEditingSupport(new EditingSupport(viewer)
		{
			protected boolean canEdit(Object element)
			{
				return true;
			}

			protected CellEditor getCellEditor(Object element)
			{
				return booleanCellEditor;
			}

			protected Object getValue(Object element)
			{
				//System.out.println("getValue(" + ((RefOntoUML.Class)element).getName() + ")");
				return new Boolean(dh.getDurationDecision((RefOntoUML.Class)element));
			}

			protected void setValue(Object element, Object value)
			{
				dh.setDurationDecision((RefOntoUML.Class)element, ((Boolean) value).booleanValue());
				viewer.update(element, null);
			}
		});
		
		// ContentProvider and Input
		viewer.setContentProvider(new TimeContentProvider());
		viewer.setInput((new TimeModel(ma)).timeModel);
		
		return viewer;
	}
	
	public TreeViewer treeViewerHistory (final Composite parent, RefOntoUMLModelAbstraction ma, final DecisionHandler dh)
	{
		final TreeViewer viewer = treeViewerHistoryAndTime (parent, ma, dh);
		
		// BooleanCellEditor is a custom class created by a guy in JFace Snippets
		final BooleanCellEditor booleanCellEditor = new BooleanCellEditor(viewer.getTree());
		booleanCellEditor.setChangeOnActivation(true);
		
		// Column 2 (Past)
		TreeViewerColumn column = new TreeViewerColumn(viewer, SWT.CENTER);
		column.getColumn().setWidth(200);
		column.getColumn().setMoveable(true);
		column.getColumn().setText("Past");
		column.setLabelProvider(new EmulatedNativeCheckBoxLabelProvider(viewer)
		{
			protected boolean isChecked(Object element)
			{
				//System.out.println(((RefOntoUML.Class)element).getName());
				return dh.getHTPastDecision((RefOntoUML.Class)element);
			}
		});
		column.setEditingSupport(new EditingSupport(viewer)
		{
			protected boolean canEdit(Object element)
			{
				return true;
			}

			protected CellEditor getCellEditor(Object element)
			{
				return booleanCellEditor;
			}

			protected Object getValue(Object element)
			{
				//System.out.println("getValue(" + ((RefOntoUML.Class)element).getName() + ")");
				return new Boolean(dh.getHTPastDecision((RefOntoUML.Class)element));
			}

			protected void setValue(Object element, Object value)
			{
				dh.setHTPastDecision((RefOntoUML.Class)element, ((Boolean) value).booleanValue());
				viewer.update(element, null);
			}
		});
		
		// Column 3 (Present)
		column = new TreeViewerColumn(viewer, SWT.CENTER);
		column.getColumn().setWidth(200);
		column.getColumn().setMoveable(true);
		column.getColumn().setText("Present");
		column.setLabelProvider(new EmulatedNativeCheckBoxLabelProvider(viewer)
		{
			protected boolean isChecked(Object element)
			{
				//System.out.println(((RefOntoUML.Class)element).getName());
				return dh.getHTPresentDecision((RefOntoUML.Class)element);
			}
		});
		column.setEditingSupport(new EditingSupport(viewer)
		{
			protected boolean canEdit(Object element)
			{
				return true;
			}

			protected CellEditor getCellEditor(Object element)
			{
				return booleanCellEditor;
			}

			protected Object getValue(Object element)
			{
				//System.out.println("getValue(" + ((RefOntoUML.Class)element).getName() + ")");
				return new Boolean(dh.getHTPresentDecision((RefOntoUML.Class)element));
			}

			protected void setValue(Object element, Object value)
			{
				dh.setHTPresentDecision((RefOntoUML.Class)element, ((Boolean) value).booleanValue());
				viewer.update(element, null);
			}
		});
		
		// ContentProvider and Input
		viewer.setContentProvider(new TimeContentProvider());
		viewer.setInput((new TimeModel(ma)).timeModel);
		
		return viewer;
	}
	
	private TableViewer tableViewer (final Composite parent, RefOntoUMLModelAbstraction ma, final DecisionHandler dh)
	{
		// FIXME: maybe using a Table is messing things out... this fake checkbox was made for Trees, perhaps it is something with the BooleanCellEditor
		final TableViewer tableViewer = new TableViewer(parent, SWT.BORDER | SWT.FULL_SELECTION);
		final Table table = tableViewer.getTable();
		table.setLinesVisible(true);
		table.setHeaderVisible(true);
		table.setBackgroundMode(SWT.INHERIT_DEFAULT);
		
		// The focusing thing... FIXME: I'm not sure if I need those things
		FocusCellOwnerDrawHighlighter h = new FocusCellOwnerDrawHighlighter(tableViewer)
		{
			protected Color getSelectedCellBackgroundColorNoFocus(ViewerCell cell)
			{
				//return parent.getDisplay().getSystemColor(SWT.COLOR_WHITE);
				 return parent.getDisplay().getSystemColor(SWT.COLOR_LIST_SELECTION);
			}

			protected Color getSelectedCellForegroundColorNoFocus(ViewerCell cell)
			{
				// I'm not sure where this color appears
				//return parent.getDisplay().getSystemColor(SWT.COLOR_WHITE);
				return parent.getDisplay().getSystemColor(SWT.COLOR_WIDGET_FOREGROUND);
			}
		};

		TableViewerFocusCellManager focusCellManager = new TableViewerFocusCellManager(tableViewer, h);
		ColumnViewerEditorActivationStrategy actSupport = new ColumnViewerEditorActivationStrategy(tableViewer);

		TableViewerEditor.create(tableViewer, focusCellManager, actSupport,
				ColumnViewerEditor.TABBING_HORIZONTAL |
				ColumnViewerEditor.TABBING_MOVE_TO_ROW_NEIGHBOR |
				ColumnViewerEditor.TABBING_VERTICAL |
				ColumnViewerEditor.KEYBOARD_ACTIVATION);
		
		final TextCellEditor textCellEditor = new TextCellEditor(table);
		// BooleanCellEditor is a custom class created by a guy in JFace Snippets
		final BooleanCellEditor booleanCellEditor = new BooleanCellEditor(table);
		booleanCellEditor.setChangeOnActivation(true);
		
		// Column 1
		TableViewerColumn column = new TableViewerColumn(tableViewer, SWT.LEFT);
		column.getColumn().setText("Universal");
		column.getColumn().setWidth(200);
		column.setLabelProvider(new ColumnLabelProvider()
		{
			public String getText(Object element)
			{
				return ((RefOntoUML.Class)element).getName();
			}
		});

		// Column 2
		column = new TableViewerColumn(tableViewer, SWT.CENTER);
		column.getColumn().setWidth(200);
		column.getColumn().setMoveable(true);
		column.getColumn().setText("Start Time");
		column.setLabelProvider(new EmulatedNativeCheckBoxLabelProvider(tableViewer)
		{
			protected boolean isChecked(Object element)
			{
				//System.out.println(((RefOntoUML.Class)element).getName());
				return dh.getStartTimeDecision((RefOntoUML.Class)element);
			}
		});
		column.setEditingSupport(new EditingSupport(tableViewer)
		{
			protected boolean canEdit(Object element)
			{
				return true;
			}

			protected CellEditor getCellEditor(Object element)
			{
				return booleanCellEditor;
			}

			protected Object getValue(Object element)
			{
				System.out.println("getValue(" + ((RefOntoUML.Class)element).getName() + ")");
				return new Boolean(dh.getStartTimeDecision((RefOntoUML.Class)element));
			}

			protected void setValue(Object element, Object value)
			{
				//((File) element).read = ((Boolean) value).booleanValue();
				dh.setStartTimeDecision((RefOntoUML.Class)element, ((Boolean) value).booleanValue());
				tableViewer.update(element, null);
			}
		});
		
		// ContentProvider and Input
		tableViewer.setContentProvider(new ArrayContentProvider());
		tableViewer.setInput((new TimeModel(ma)).timeModel.toArray());
		
		return tableViewer;
	}
	
	
	public static Tree tree (Composite parent)
	{
		Tree tree = new Tree(parent, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL | SWT.CHECK);
		tree.setHeaderVisible(true);
		
		TreeColumn column1 = new TreeColumn(tree, SWT.LEFT);
		column1.setText("Column 1");
		column1.setWidth(200);
		
		TreeColumn column2 = new TreeColumn(tree, SWT.CENTER);
		column2.setText("Column 2");
		column2.setWidth(200);
		
		TreeColumn column3 = new TreeColumn(tree, SWT.RIGHT);
		column3.setText("Column 3");
		column3.setWidth(200);
		
		for (int i = 0; i < 4; i++) {
			TreeItem item = new TreeItem(tree, SWT.NONE);
			item.setText(new String[] { "item " + i, "abc", "defghi" });
			for (int j = 0; j < 4; j++) {
				TreeItem subItem = new TreeItem(item, SWT.NONE);
				subItem.setText(new String[] { "subitem " + j, "jklmnop", "qrs" });
				for (int k = 0; k < 4; k++) {
					TreeItem subsubItem = new TreeItem(subItem, SWT.NONE);
					subsubItem.setText(new String[] { "subsubitem " + k, "tuv", "wxyz" });
				}
			}
		}
		return tree;
	}
	
	public static String getTabName (int i)
	{
		if (i == 0)
			return "Scope";
		if (i == 1)
			return "Reference";
		if (i == 2)
			return "History Tracking";
		if (i == 3)
			return "Time Tracking";
		if (i == 4)
			return "Measurement";
		return "";
	}
}
