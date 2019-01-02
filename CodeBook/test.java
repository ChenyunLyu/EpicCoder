import javax.swing.JPanel;
import javax.swing.JFrame;

public class test extends JFrame
{
	public static void main( String args[] )
	{
		JFrame frame = new JFrame();
		codeDisplayUnit cdu = new codeDisplayUnit( new code( 'a' , "000" ) );
		frame.add( cdu.panel );
		frame.setDefaultCloseOperation( EXIT_ON_CLOSE );
		frame.setSize( 100 , 100 );
		frame.setVisible( true );
	}

}


