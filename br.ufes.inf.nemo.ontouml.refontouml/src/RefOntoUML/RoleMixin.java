/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package RefOntoUML;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Role Mixin</b></em>'.
 * <!-- end-user-doc -->
 *
 *
 * @see RefOntoUML.RefOntoUMLPackage#getRoleMixin()
 * @model annotation="http://www.eclipse.org/emf/2002/Ecore constraints='RoleMixinConstraint1'"
 *        annotation="http://www.eclipse.org/ocl/examples/OCL RoleMixinConstraint1='Mediation.allInstances()->exists( x | allParents()->including(self)->includes(x.mediated()) )'"
 *        annotation="Comments RoleMixinConstraint1='A RoleMixin must be connected (directly or indirectly) to a Mediation'"
 * @generated
 */
public interface RoleMixin extends AntiRigidMixinClass
{
} // RoleMixin
