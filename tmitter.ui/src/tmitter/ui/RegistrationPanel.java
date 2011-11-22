package tmitter.ui;

import org.eclipse.rwt.lifecycle.WidgetUtil;
import org.eclipse.rwt.widgets.DialogCallback;
import org.eclipse.rwt.widgets.DialogUtil;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import tmitter.model.Monster;
import tmitter.model.MonsterUtil;
import tmitter.model.Species;


public class RegistrationPanel {

  private Text userNameText;
  private Text passwordText;
  private Combo speciesCombo;
  private Shell signupShell;

  public void open( Shell parent ) {
    signupShell = new Shell( parent, SWT.APPLICATION_MODAL | SWT.TITLE | SWT.CLOSE );
    signupShell.setLayout( new GridLayout( 1, true ) );
    Composite signupParent = new Composite( signupShell, SWT.NONE );
    signupParent.setData( WidgetUtil.CUSTOM_VARIANT, AuthPanelContentProvider.LOGIN_VARIANT );
    GridData signupParentData = new GridData( SWT.CENTER, SWT.CENTER, true, true );
    signupParentData.widthHint = 250;
    signupParent.setLayoutData( signupParentData );
    GridLayout layout = new GridLayout( 2, false );
    layout.marginWidth = 15;
    layout.marginHeight = 15;
    signupParent.setLayout( layout );
    createLoginInputControls( signupParent, signupShell );
    createLoginButton( signupParent, signupShell );
    configureShell( signupShell );
    userNameText.setFocus();
    signupShell.pack();
    signupShell.open();
  }

  private void createLoginInputControls( Composite signupParent, final Shell signupShell ) {
    Label userNameLabel = new Label( signupParent, SWT.NONE );
    userNameLabel.setText( "username" );
    userNameLabel.setLayoutData( new GridData( SWT.LEFT, SWT.CENTER, false, false ) );
    userNameLabel.setData( WidgetUtil.CUSTOM_VARIANT, AuthPanelContentProvider.LOGIN_VARIANT );
    userNameText = new Text( signupParent, SWT.NONE );
    userNameText.setData( WidgetUtil.CUSTOM_VARIANT, AuthPanelContentProvider.LOGIN_VARIANT );
    userNameText.setLayoutData( new GridData( SWT.FILL, SWT.FILL, true, false ) );
    Label passwordLabel = new Label( signupParent, SWT.NONE );
    passwordLabel.setData( WidgetUtil.CUSTOM_VARIANT, AuthPanelContentProvider.LOGIN_VARIANT );
    passwordLabel.setText( "password" );
    passwordText = new Text( signupParent, SWT.PASSWORD );
    passwordText.setData( WidgetUtil.CUSTOM_VARIANT, AuthPanelContentProvider.LOGIN_VARIANT );
    passwordText.setLayoutData( new GridData( SWT.FILL, SWT.FILL, true, false ) );
    passwordText.addSelectionListener( new SelectionAdapter() {
      private static final long serialVersionUID = 1L;

      @Override
      public void widgetDefaultSelected( SelectionEvent e ) {
        signup();
      }
    } );
    Label speciesLabel = new Label( signupParent, SWT.NONE );
    speciesLabel.setData( WidgetUtil.CUSTOM_VARIANT, AuthPanelContentProvider.LOGIN_VARIANT );
    speciesLabel.setText( "species" );
    speciesCombo = new Combo( signupParent, SWT.READ_ONLY );
    Species[] species = Species.values();
    for( Species currentSpecies : species ) {
      speciesCombo.add( currentSpecies.toString() );
    }
    speciesCombo.select( speciesCombo.indexOf( Species.UNDISCOVERED.toString() ) );
    speciesCombo.setLayoutData( new GridData( SWT.FILL, SWT.FILL, true, false ) );
  }

  private void createLoginButton( Composite signupParent, final Shell signupShell ) {
    final Button signupButton = new Button( signupParent, SWT.PUSH );
    signupButton.setData( WidgetUtil.CUSTOM_VARIANT, AuthPanelContentProvider.LOGIN_VARIANT );
    GridData buttonLayoutData = new GridData( SWT.RIGHT, SWT.CENTER, false, false );
    buttonLayoutData.horizontalSpan = 2;
    signupButton.setLayoutData( buttonLayoutData );
    signupButton.setText( "Sign up");
    signupButton.addSelectionListener( new SelectionAdapter() {
      private static final long serialVersionUID = 1L;

      @Override
      public void widgetSelected( SelectionEvent e ) {
        signup();
      }
    } );
  }

  private void configureShell( Shell signupShell ) {
    signupShell.setData( WidgetUtil.CUSTOM_VARIANT, AuthPanelContentProvider.LOGIN_VARIANT );
    signupShell.setText( "Sign Up" );
    signupShell.setSize( 250, 160 );
    Rectangle bounds = signupShell.getDisplay().getBounds();
    Rectangle rect = signupShell.getBounds();
    int x = bounds.x + ( bounds.width - rect.width ) / 2;
    int y = bounds.y + ( bounds.height - rect.height ) / 2;
    signupShell.setLocation( x, y );
  }

  private void signup() {
    Monster monster = new Monster( userNameText.getText() );
    monster.setPassword( passwordText.getText() );
    String species = speciesCombo.getItem( speciesCombo.getSelectionIndex() );
    monster.setSpecies( Species.valueOf( species ) );
    if( MonsterUtil.isRegistered( monster.getName() ) ) {
      MessageBox box = new MessageBox( signupShell, SWT.ICON_ERROR | SWT.OK );
      box.setText( "Error during sign up" );
      box.setMessage( "Groar, your name is allready taken. Try another one..." );
      DialogUtil.open( box, new DialogCallback() {

        private static final long serialVersionUID = 1L;

        @Override
        public void dialogClosed( int returnCode ) {
          // TODO Auto-generated method stub
        }
      } );
    } else {
      MonsterUtil.saveMonster( monster );
      signupShell.close();
    }
  }
}
