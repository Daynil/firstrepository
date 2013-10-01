package udacityCourse;


public class TestingCode
{
    public static void main(String[] args)
    {
    	TestingHelper assignment = new TestingHelper();
    	assignment.addNew(3, "Frog");
    	assignment.addNew(1, "Mosquito");
    	assignment.addNew(6, "Tiger");
    	System.out.println(assignment.assignments.toString());
    	System.out.println(assignment.assignments.get(0).get(0));
    	int sum = 2;
    	sum += (Integer)assignment.assignments.get(0).get(0);
    	System.out.println(sum);
    	String name = (String)assignment.assignments.get(0).get(1);
    	System.out.println(name);
    	
    	
    }
    	
}
	

