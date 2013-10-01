package udacityCourse;

import java.util.ArrayList;

public class TestingHelper 
{
	ArrayList<ArrayList> assignments;
	
	public TestingHelper()
	{
		assignments = new ArrayList<ArrayList>();
	}
	
	public void addNew(int i, String name)
	{
		ArrayList another = new ArrayList();
		another.add(i);
		another.add(name);
		assignments.add(another);
	}
	
}
