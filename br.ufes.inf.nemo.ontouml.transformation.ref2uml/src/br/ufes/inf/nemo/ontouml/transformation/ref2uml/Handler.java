package br.ufes.inf.nemo.ontouml.transformation.ref2uml;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.handlers.HandlerUtil;

public class Handler extends AbstractHandler{

        @Override
        public Object execute(ExecutionEvent event) throws ExecutionException
        {
                IStructuredSelection selection = (IStructuredSelection) HandlerUtil.getActiveMenuSelection(event);
                Object firstElement = selection.getFirstElement();
                
                if (firstElement instanceof IFile)
                {
                        IFile f = (IFile) firstElement;
                                                
                        if (f.getFileExtension().compareTo("refontouml") == 0)
                        {
                                Ref2UML.Transformation(f.getLocation().toString(),true);
                                
                                try
                                {
                                        f.getProject().refreshLocal(IResource.DEPTH_INFINITE, null);
                                }
                                catch (CoreException e)
                                {
                                        MessageDialog.openError(HandlerUtil.getActiveShell(event), "Error", "Error on refreshing workspace");
                                        return null;
                                }
                        }
                        else
                        {
                                MessageDialog.openError(HandlerUtil.getActiveShell(event), "Wrong Input", "The file extension must be .ontouml");
                                return null;
                        }
                }
                else
                {
                        MessageDialog.openError(HandlerUtil.getActiveShell(event), "Wrong Input", "The input must be a file with .ontouml extension");
                        return null;
                }
                
                return null;
        }

}
