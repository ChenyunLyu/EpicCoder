import javax.swing.JFrame;
import java.io.*;

public class EpicCoder
{
	public static void main(String args[]) throws IOException
	{
		CoderFrame coderFrame = new CoderFrame();
		coderFrame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		coderFrame.setSize( 500 , 400 );
		coderFrame.setVisible( true );
	}
}
