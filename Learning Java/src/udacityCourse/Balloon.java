package udacityCourse;

public class Balloon
{
    private double volume;
    private double pi = Math.PI;

	public Balloon()
	{
	    volume = 0;
	}
	
	public void addAir(double amount)
	{
	    volume += amount;
	}
	
	public double getVolume()
	{    
	    return volume;
	}
	
	public double getSurfaceArea()
	{
	    double surfaceArea = 4.0 * pi * Math.pow(this.getRadius(), 2.0);
	    return surfaceArea;
	}
	
	public double getRadius()
	{
	    double radius = Math.pow(((3.0 * volume) / (4.0 * pi)), (1.0/3.0));
	    return radius;
	}
}
