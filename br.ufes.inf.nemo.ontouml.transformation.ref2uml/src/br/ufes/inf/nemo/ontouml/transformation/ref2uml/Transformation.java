package br.ufes.inf.nemo.ontouml.transformation.ref2uml;

import org.eclipse.emf.ecore.EObject;

public class Transformation {

	Dealer mydealer;	
	
	public Transformation()  
    { 
    	mydealer = new Dealer();  
    }
	
    public org.eclipse.uml2.uml.Model Transform (EObject eObject)
    {       
    	
        if (!(eObject instanceof RefOntoUML.Model)) return null;
           
        RefOntoUML.Model refmodel = (RefOntoUML.Model) eObject;            
        org.eclipse.uml2.uml.Model umlmodel = org.eclipse.uml2.uml.UMLFactory.eINSTANCE.createModel();
                        
        mydealer.DealNamedElement((RefOntoUML.NamedElement) refmodel, (org.eclipse.uml2.uml.NamedElement) umlmodel);
        mydealer.RelateElements((RefOntoUML.Element) refmodel, (org.eclipse.uml2.uml.Element) umlmodel);
       
        /** Classes */            
        for (EObject obj : refmodel.eContents())
        {
            if (obj instanceof RefOntoUML.Class)
            {
          		final org.eclipse.uml2.uml.Class umlclass = org.eclipse.uml2.uml.UMLFactory.eINSTANCE.createClass();  
           		mydealer.DealClass( (RefOntoUML.Class) obj, umlclass);
                umlmodel.getPackagedElements().add(umlclass);
            }
        }
         
        /** DataTypes */
        for (EObject obj : refmodel.eContents())
        {
            if (obj instanceof RefOntoUML.DataType)
            {
                 org.eclipse.uml2.uml.DataType dt2 = mydealer.DealDataType ((RefOntoUML.DataType) obj);
                 umlmodel.getPackagedElements().add(dt2);                               
            }
        }
        
        /** Associations */           
        for (EObject obj : refmodel.eContents())
        {
            if (obj instanceof RefOntoUML.Association)
            {
            	final org.eclipse.uml2.uml.Association assoc = org.eclipse.uml2.uml.UMLFactory.eINSTANCE.createAssociation();
            	mydealer.DealAssociation ((RefOntoUML.Association) obj,assoc);
            	umlmodel.getPackagedElements().add(assoc);
            }
        }
            
        /** (Process) Generalizations */        
        for (EObject obj : refmodel.eContents())
        {
            if (obj instanceof RefOntoUML.Classifier)
            {                    	    
                 mydealer.ProcessGeneralizations((RefOntoUML.Classifier)obj);
            }
        }            
        
        /** Generalization Sets */      
        for (EObject obj : refmodel.eContents())
        {
             if (obj instanceof RefOntoUML.GeneralizationSet)
             {
             	org.eclipse.uml2.uml.GeneralizationSet gs2 = mydealer.DealGeneralizationSet ((RefOntoUML.GeneralizationSet) obj);        
                umlmodel.getPackagedElements().add(gs2);
             }
        }
                                                     
        /** Dependency */ 
        for (EObject obj : refmodel.eContents())
        {
            if (obj instanceof RefOntoUML.Dependency)
            {
            	org.eclipse.uml2.uml.Dependency d2 = mydealer.DealDependency ((RefOntoUML.Dependency) obj);
            	umlmodel.getPackagedElements().add(d2);
            }
        }
            
        /** Non Classifiers, Non GeneralizationSet, Non Dependency */ 
        for (EObject obj : refmodel.eContents())
        {
        	if (!(obj instanceof RefOntoUML.Classifier) &&
        		!(obj instanceof RefOntoUML.GeneralizationSet) &&
        		!(obj instanceof RefOntoUML.Dependency) &&
                !(obj instanceof RefOntoUML.RefOntoUMLPackage))
            {
                System.err.print("# Unsupported Metaclass: ");
                System.out.println(obj);
            }
        }
            
        /** (Process) Comments */ 
        for (EObject obj : refmodel.eContents())
        {
            mydealer.ProcessComments ((RefOntoUML.Element) obj);
        }
        
        return umlmodel;
    }
}