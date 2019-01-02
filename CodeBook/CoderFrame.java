import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;
import javax.swing.JPasswordField;
import java.io.*;

public class CoderFrame extends JFrame
{
	private JTabbedPane tabbedPane;
	private JTextArea textArea1;
	private JTextArea textArea2;
	private JTextField codebookField;
	private JTextField codebookField1;
	private JTextField userName;
	private JTextField showUser;
	private JTextField showUserDup;
	private JLabel userLabel;
	private JLabel passwordLabel;
	private JPasswordField passwordField;
	private JButton encodeJButton;
	private JButton decodeJButton;
	private JButton changeCodebook;
	private JButton createCodebook;
	private JButton modifyCodebook;
	private JButton saveCodebook;
	private JButton exit;
	private JButton logIn;
	private JButton logOut;
	private JPanel tab1;
	private JPanel tab2;
	private JPanel buttonJPanel;
	private JPanel logInPanel;
	private JPanel codebookToolbar;
	private JPanel codebookToolbar1;
	private JPanel codebookToolbar2;
	private codeDisplay codebookDisplay;
	private String book = "default_codebook";
	private String[] user = loadUser();
	private String verifiedUser = "Please log in";
	
	public CoderFrame() throws IOException
	{
		super( "Epic Coder" );
		
		tabbedPane = new JTabbedPane();

//---------------------------Encoder Decoder-------------------------------------
		Box box = Box.createHorizontalBox();
		
		textArea1 = new JTextArea( null , 10 , 15 );
		textArea1.setLineWrap( true );
		textArea1.setToolTipText( "Enter your message here" );
		box.add( new JScrollPane( textArea1 ) );
		
		encodeJButton = new JButton( "Encode >>>" );
		encodeJButton.setToolTipText( "Encode text on the left side" );
		//box.add( encodeJButton );
		//box.add( decodeJButton );
		encodeJButton.addActionListener
		(
			new ActionListener()
			{
				public void actionPerformed( ActionEvent event )
				{
					String ss = textArea1.getText();
					try
					{
						ss = traslateCode( ss );
					}
					catch( IOException e )
					{
						textArea2.setText( "Error!" );
					}
					
					
					textArea2.setText( ss );
				}
			}
		);
		
		decodeJButton = new JButton( "<<< Decode" );
		decodeJButton.setToolTipText( "Decode text on the right side" );
		//box.add( decodeJButton );
		decodeJButton.addActionListener
		(
			new ActionListener()
			{
				public void actionPerformed( ActionEvent event )
				{
					String ss = textArea2.getText();
					//ss = ss.replaceAll( "\n" , "" );
					
					try
					{
						ss = traslateMessage( ss );
					}
					catch( IOException e )
					{
						textArea1.setText( "Error!" );
					}
					textArea1.setText( ss );
				}
			}
		);
		
		buttonJPanel = new JPanel();
		buttonJPanel.setLayout( new GridLayout( 2 , 1 ) );
		buttonJPanel.add( encodeJButton );
		buttonJPanel.add( decodeJButton );
		
		box.add( buttonJPanel );
		
		textArea2 = new JTextArea( null , 10 , 15 );
		textArea2.setLineWrap( true );
		textArea2.setToolTipText( "Enter code here" );
		box.add( new JScrollPane( textArea2 ) );
		
		codebookField = new JTextField( "Current Codebook: " + book + "  " );
		codebookField.setEditable( false );
		codebookField.setToolTipText( "You are now using: " + book );
		
		
		tab1 = new JPanel();
		tab1.setLayout( new BorderLayout() );
		tab1.add( box , BorderLayout.CENTER );
		tab1.add( codebookField , BorderLayout.SOUTH );
		
//-------------------------Codebook Manager------------------------------------------

		tab2 = new JPanel();
		tab2.setLayout( new BorderLayout() );
		
		userLabel = new JLabel( "Username:" );
		userName = new JTextField( "" , 10  );
		passwordLabel = new JLabel( "Password: " );
		passwordField = new JPasswordField( 10 );
		
		logInPanel = new JPanel();
		logInPanel.setLayout( new GridLayout( 2 , 2 ) );
		logInPanel.add( userLabel );
		logInPanel.add( userName );
		logInPanel.add( passwordLabel );
		logInPanel.add( passwordField );
		
		codebookToolbar = new JPanel();
		codebookToolbar1 = new JPanel();
		
		changeCodebook = new JButton( "Change" );
		changeCodebook.setToolTipText( "Change current codebook" );
		changeCodebook.setVisible( false );
		changeCodebook.addActionListener
		(
			new ActionListener()
			{
				public void actionPerformed( ActionEvent event )
				{
					boolean testing = true;
					String temper = "";
					try
					{
						temper = JOptionPane.showInputDialog( tabbedPane , "Please input codebook name" );
						codebookDisplay.loadCode( "temper" );
					}	
					catch( IOException e )
					{
						JOptionPane.showMessageDialog( tabbedPane , "Please check codebook name!" );
						testing = false;
					}
					if( testing == true )
					{
						book = temper;
						codebookField.setText( "Current codebook: " + book );
						codebookField1.setText( "Current codebook: " + book );
					}
				}
			}
		
		);
		createCodebook = new JButton( "Create" );
		createCodebook.setToolTipText( "Create a new codebook" );
		createCodebook.setVisible( false );
		createCodebook.addActionListener
		(
			new ActionListener()
			{
				public void actionPerformed( ActionEvent event )
				{
					try
					{
						codebookDisplay.loadCode( "empty_template" );
						book = "new_codebook";
						codebookField.setText( "Current codebook: " + book );
						codebookField1.setText( "Current codebook: " + book );
						
					}
					catch( IOException e )
					{
						JOptionPane.showMessageDialog( tabbedPane , "Error while reading" );
					}
				}
			}
		);
		modifyCodebook = new JButton( "Modify" );
		modifyCodebook.setToolTipText( "Modify current codebook" );
		modifyCodebook.setVisible( false );
		modifyCodebook.addActionListener
		(
			new ActionListener()
			{
				public void actionPerformed( ActionEvent event )
				{
					codebookDisplay.setAllEditable( true );
				}
			}
		);
		saveCodebook   = new JButton( "Save" );
		saveCodebook.setToolTipText( "Save current codebook" );
		saveCodebook.setVisible( false );
		saveCodebook.addActionListener
		(
			new ActionListener()
			{
				public void actionPerformed( ActionEvent event )
				{
					boolean v = codebookDisplay.validateCode();
					if( v == true )
					{
						codebookDisplay.setAllEditable( false );
						String[] options = { "Yes" , "no" };
						int c = JOptionPane.showOptionDialog( tabbedPane , "Do you want to save your codebook? " , "Save" , 0 , JOptionPane.WARNING_MESSAGE , null, options , null );
						if( c == 0 )
						{
							JPanel apanel = new JPanel();
							JLabel alabel = new JLabel( "Please give your codebook. (WARNING: will overwrite existing ones if have the same name!" );
							JTextField atextfield = new JTextField ( book , 10 );
							apanel.setLayout( new GridLayout( 2 , 1 ) );
							apanel.add( alabel );
							apanel.add( atextfield );
							//options = new String[2];
							String[] options1 = { "OK" , "Cancel" };						
							int cc = JOptionPane.showOptionDialog( tabbedPane , apanel , "Save" , 0 , 1 , null , options1 , null );
							if( cc == 0 );
							{
								try
								{
									codebookDisplay.saveToCodebook( atextfield.getText() );
									codebookField.setText( "Current codebook: " + book );
									codebookField1.setText( "Current codebook: " + book );
								}
								catch( IOException e )
								{
									JOptionPane.showMessageDialog( tabbedPane , "Error while saving" );
								}
							}
						}
					}
				}
			}
		);
		logOut         = new JButton( "Log Out" );
		logOut.setToolTipText( "Log Out current user" );
		logOut.setVisible( false );
		logOut.addActionListener
		(
			new ActionListener()
			{
				public void actionPerformed( ActionEvent event )
				{
					changeCodebook.setVisible( false );
					createCodebook.setVisible( false );
					modifyCodebook.setVisible( false );
					saveCodebook.setVisible( false );
					logOut.setVisible( false );
					logIn.setVisible( true );
					codebookDisplay.setAllVisible( false );
					JOptionPane.showMessageDialog( tabbedPane , "Logged out: " + verifiedUser );
					verifiedUser = "Please log in";
					showUser.setText( "User: " + verifiedUser );
				}
			}
		);
		logIn          = new JButton( "Log In" );
		logIn.setToolTipText( "Log In as a registed user" );
		logIn.setVisible( true );
		logIn.addActionListener
		(
			new ActionListener()
			{
				public void actionPerformed( ActionEvent event )
				{
					String[] opt = {"OK","Cancel"};
					int c = JOptionPane.showOptionDialog( tabbedPane , logInPanel , "Log In" , 0 , 1 , null , opt , opt[0] );
					if( c == 0 )
					{
						boolean b = false;
						String tempUser = userName.getText();
						char[] tempCharPassword = passwordField.getPassword();
						String tempPassword = "";
						for( int i = 0 ; i < tempCharPassword.length ; i++ )
							tempPassword = tempPassword + tempCharPassword[i];
						char[] tempChar = new char[100];
						for( int i = 0 ; i < ( user.length / 2 ) ; i++ )
						{
							String password = user[ i * 2 + 1 ];
							//tempChar = password.toCharArray();
							//System.out.println( i * 2 );
							//for( int a = 0 ; a < 10 ; a++ )
							//	System.out.println( user[a] );
							if( tempUser.equals( user[ i * 2 ] ) && tempPassword.equals( password ) )
							{
								b = true;
								verifiedUser = tempUser;
							}
						}
						
						if( b == true )
						{
							JOptionPane.showMessageDialog( tabbedPane , "Welcome back, " + verifiedUser );
							passwordField.setText( "" );
							changeCodebook.setVisible( true );
							modifyCodebook.setVisible( true );
							createCodebook.setVisible( true );
							saveCodebook.setVisible( true );
							logOut.setVisible( true );
							logIn.setVisible( false );
							codebookDisplay.setAllVisible( true );
							showUser.setText( "User: " + verifiedUser );
							
						}
						else
						{
							JOptionPane.showMessageDialog( tabbedPane , "Username or password is incorrect!" );
							passwordField.setText( "" );
						}
					}
				}
			}
		);
		
		
		codebookToolbar.setLayout( new GridLayout( 2 , 1 ) );
		codebookToolbar1.setLayout( new GridLayout( 1 , 6 ) );
		codebookToolbar1.add( changeCodebook );
		codebookToolbar1.add( createCodebook );
		codebookToolbar1.add( modifyCodebook );
		codebookToolbar1.add( saveCodebook );
		codebookToolbar1.add( logOut );
		codebookToolbar1.add( logIn );
		//codebookToolbar1.add( codebookField );
		
		
		codebookField1 = new JTextField( "Current Codebook: " + book + "  " );
		codebookField1.setEditable( false );
		codebookField1.setToolTipText( "You are now using: " + book );
		
		codebookToolbar2 = new JPanel();
		codebookToolbar2.setLayout( new GridLayout( 1 , 2 ) );
		
		showUser = new JTextField( "User: " + verifiedUser );
		showUser.setEditable( false );
		
		codebookToolbar2.add( codebookField1 );
		codebookToolbar2.add( showUser );
		
		codebookToolbar.add( codebookToolbar2 );
		//codebookToolbar.add( logIn );
		//logIn.setVisible( false );
		codebookToolbar.add( codebookToolbar1 );
		
		codebookDisplay = new codeDisplay( book );
		
		//tab2.add( codebookField , BorderLayout.SOUTH );
		tab2.add( codebookDisplay.panel , BorderLayout.CENTER );
		tab2.add( codebookToolbar , BorderLayout.SOUTH );
		
		
//-------------------------Codebook Toolbar------------------------------------------


		
		tabbedPane.addTab( "Encoder/Decoder" , null , tab1 , "Encode and decode message" );
		tabbedPane.addTab( "Codebook Manager" , null , tab2 , "Manage your codebook!" );
		
		add( tabbedPane );
	}
	
	public String traslateMessage( String ss ) throws IOException
	{
		codefunc cf = new codefunc( book , " " , ss );
		ss = cf.message;
		char[] list = ss.toCharArray();
		//ss = "";
		//for( int i = 0 ; i < list.length ; i++ )
		//{
		//	ss = ss + list[i];
		//	if( ( i + 1 ) % 20 == 0 )
		//		ss = ss + "\n";
		//}
		return ss;
		
		//return cf.message;
	}
	
	public String traslateCode( String ss ) throws IOException
	{
		codefunc cf = new codefunc( book , ss , " " );
		ss = cf.cover;
		char[] list = ss.toCharArray();
		//ss = "";
		//for( int i = 0 ; i < list.length ; i++ )
		//{
		//	ss = ss + list[i];
		//	if( ( i + 1 ) % 18 == 0 )
		//		ss = ss + "\n";
		//}
		return ss;
		
		//return cf.cover;
	}
	
	public String[] loadUser() throws IOException
	{
		File f = new File( "user.txt" );
		FileReader fr = new FileReader( f );
		BufferedReader in = new BufferedReader( fr );
		String[] sa = new String[ 100 ];
		int c = 0;
		while( in.ready() )
		{
			sa[c] = in.readLine();
			c++;
		}
		in.close();
		return sa;
	}
}
