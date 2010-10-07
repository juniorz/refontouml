package plus;

import org.eclipse.emf.ocl.examples.interpreter.actions.ShowConsoleDelegate;
import org.eclipse.emf.ocl.examples.interpreter.console.OCLConsole;
import org.eclipse.emf.ocl.examples.interpreter.console.TargetMetamodel;

public class ShowRefOntoUMLConsoleAction
		extends ShowConsoleDelegate {

	public ShowRefOntoUMLConsoleAction() {
		super();
	}

	@Override
	protected void consoleOpened(OCLConsole console) {
		console.setTargetMetamodel(TargetMetamodel.Ecore);
	}
}
