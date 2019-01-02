public class test
{
	public static void main( String args[] )
	{	
		//Animal a = new Animal();
		Bear b = new Bear();
		Cub c = new Cub();
		
		System.out.println( b instanceof Animal );
		System.out.println( b instanceof Bear );
		System.out.println( c instanceof Animal );
		System.out.println( c instanceof Bear );
		System.out.println( b instanceof Cub );
		//System.out.println( b.instanceof( a ) );
	}
}
