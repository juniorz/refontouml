package br.ufes.inf.nemo.ontouml.transformation.ontouml2sbvr;

import java.util.LinkedList;

import RefOntoUML.*;

public class ChildPartition
{
	GeneralizationSet gs;
	LinkedList<Node> children;	
		
	public ChildPartition(GeneralizationSet gset)
	{
		gs = gset;
		children = new LinkedList<Node>();
	}
	
	public void addChild (Node child)
	{
		children.add(child);
	}
	
	public LinkedList<Node> getChildren()
	{
		return children;
	}
	
	public GeneralizationSet getGS ()
	{
		return gs;
	}
}
