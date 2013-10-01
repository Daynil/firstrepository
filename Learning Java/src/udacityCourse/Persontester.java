package udacityCourse;

public class Persontester 
{
	public static void main(String[] args)
	{
		Person danny = new Person("Danny Libin");
		Person laura = new Person("Laura Libin");
		Person serge = new Person("Serge Chelen");
		danny.addFriend(laura);
		danny.addFriend(serge);
		laura.addFriend(danny);
		System.out.println(danny.getFriends());
		danny.removeMeanFriends();
		System.out.println(danny.getFriends());
	}
}
