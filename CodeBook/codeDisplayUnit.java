import java.awt.GridLayout;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;

public class codeDisplayUnit extends JPanel
{
	private code acode;
	public JPanel panel;
	private JLabel textField1;
	private JTextField textField2;
	
	codeDisplayUnit( code c )
	{
		acode = c;
		String ss;
		if( c.decode() == '\n' )
			ss = "New Line";
		else if( c.decode() == ' ' )
			ss = "Space";
		else
			ss = c.decode() + "";
		textField1 = new JLabel( ss + " = " , JLabel.RIGHT );
		//textField1.setEditable( false );
		textField1.setToolTipText( "This is the message" );
		textField2 = new JTextField( "***" );
		textField2.setEditable( false );
		textField2.setToolTipText( "This is the code" );
		
		panel = new JPanel();
		panel.setLayout( new GridLayout( 1 , 2 ) );
		panel.add( textField1 );
		panel.add( textField2 );
		panel.setVisible( true );
		
	}
	
	public void setEditCode( boolean b )
	{
		textField2.setEditable( b );
	}
	
	public void setVisibleCode( boolean b )
	{
		if( b == true )
			textField2.setText( acode.encode() );
		else if( b == false )
			textField2.setText( "***" );
	}
	
	public String getCode()
	{
		
		return textField2.getText();
	}
	
	public void setCode( String ss )
	{
		textField2.setText( ss );
	}
}
