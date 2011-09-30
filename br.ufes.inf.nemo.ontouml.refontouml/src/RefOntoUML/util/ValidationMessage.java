package RefOntoUML.util;

public class ValidationMessage
{
	public static String getFinalMessage (String message)
	{
		String[] out = ParseMessage(message);
		String d = getDescription(out[0]);
		
		return out[1] + " - " + d;
	}
	
	public static String[] ParseMessage (String message)
	{
		int i1, i2, j1, j2;
		String[] out = new String[2];
		out[0] = "";
		out[1] = "";
		
		i1 = message.indexOf("'", 0);
		i2 = message.indexOf("'", i1+1);
		j1 = message.indexOf("'", i2+1);
		j2 = message.indexOf("'", j1+1);
		
		//System.out.println(message);
		//System.out.println(i1 + " " + i2 + " " + j1 + " " + j2);
		
		// The constraint id
		out[0] += message.substring(i1+1, i2);
		// The model element
		out[1] += message.substring(j1+1, j2);

		return out;
	}
	
	public static String getDescription (String constraintid)
	{
		String description = null;
		
		if (constraintid.compareTo("PhaseConstraint2") == 0)
		{
			description = "A Phase must be grouped in exactly one {disjoint, complete} Generalization Set with other Phases";
		}
		else
		{
			description = constraintid;
		}
		
		
		return description;
	}
}
