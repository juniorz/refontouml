package RefOntoUML.presentation;

import org.eclipse.emf.common.ui.dialogs.DiagnosticDialog;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.swt.widgets.Shell;

public class RCDiagnosticDialog extends DiagnosticDialog
{
	public RCDiagnosticDialog(Shell parentShell, String dialogTitle, String message, Diagnostic diagnostic, int severityMask)
	{
		super(parentShell, dialogTitle, message, diagnostic, severityMask);
	}

	public static int open(Shell parent, String dialogTitle, String message, Diagnostic diagnostic)
	{
		return open(parent, dialogTitle, message, diagnostic, Diagnostic.OK | Diagnostic.INFO | Diagnostic.WARNING | Diagnostic.ERROR);
	}

	public static int open(Shell parentShell, String title, String message, Diagnostic diagnostic, int displayMask)
	{
		RCDiagnosticDialog dialog = new RCDiagnosticDialog(parentShell, title, message, diagnostic, displayMask);
		return dialog.open();
	}
	
	@Override
	protected void configureShell(Shell newShell)
	{
		super.configureShell(newShell);
		newShell.setSize(700, 200);
		//newShell.setLocation(newShell.getLocation().x + 200, newShell.getLocation().y);
	}
	// Maybe another solution is to disable horizontal scroll in the details textarea...
}
