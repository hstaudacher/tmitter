package tmitter.admin.user;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.rwt.lifecycle.WidgetUtil;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;

import tmitter.model.Monster;
import tmitter.model.MonsterUtil;
import tmitter.ui.ImageUtil;

import com.codeaffine.example.rwt.osgi.ui.platform.ServiceProvider;
import com.codeaffine.example.rwt.osgi.ui.platform.UIContributor;


public class ManageUsersTab implements UIContributor {

  private ServiceProvider serviceProvider;
  private Composite userContainer;

  @Override
  public String getId() {
    return "Manage Users";
  }
  
  void setServiceProvider( ServiceProvider serviceProvider ) {
    this.serviceProvider = serviceProvider;
  }

  @Override
  public Control contribute( Composite parent ) {
    Composite container = new Composite( parent, SWT.NONE );
    container.setLayout( new FormLayout() );
    Label titleLabel = new Label( container, SWT.NONE );
    titleLabel.setText( "Manage Users" );
    titleLabel.setData( WidgetUtil.CUSTOM_VARIANT, "update" );
    FormData titleData = new FormData();
    titleLabel.setLayoutData( titleData );
    titleData.left = new FormAttachment( 0, 5 );
    titleData.top = new FormAttachment( 0, 5 );
    createUserContainer( container, titleLabel );
    createMonsterControls();
    return container;
  }

  private void createUserContainer( Composite container, Label titleLabel ) {
    userContainer = new Composite( container, SWT.NONE );
    userContainer.setLayout( new GridLayout( 1, false ) );
    FormData tmeetListContainerData = new FormData();
    userContainer.setLayoutData( tmeetListContainerData );
    tmeetListContainerData.left = new FormAttachment( 0, 5 );
    tmeetListContainerData.top = new FormAttachment( titleLabel, 15 );
    tmeetListContainerData.right = new FormAttachment( 100, -5 );
    tmeetListContainerData.bottom = new FormAttachment( 100, -5 );
  }
  
  private void createMonsterControls() {
    Monster currentMonster = serviceProvider.get( Monster.class );
    List<String> allMonsterNames = new ArrayList<String>( MonsterUtil.getAllMonsterNames() );
    allMonsterNames.remove( currentMonster.getName() );
    for( String name : allMonsterNames ) {
      createMonsterControl( MonsterUtil.loadMonster( name ) );
    }
  }
  
  private void createMonsterControl( Monster monster ) {
    Composite container = new Composite( userContainer, SWT.NONE );
    container.setData( WidgetUtil.CUSTOM_VARIANT, "tmeet" );
    container.setLayoutData( new GridData( SWT.FILL, SWT.CENTER, true, false ) );
    container.setLayout( new FormLayout() );
    Label imageLabel = createImageLabel( monster, container );
    createNameLabel( monster, container, imageLabel );
    createSpeciesLabel( monster, container, imageLabel );
    createFollowButton( monster, container );
  }

  private Label createImageLabel( final Monster monster, Composite container ) {
    Label imageLabel = new Label( container, SWT.NONE );
    imageLabel.setData( WidgetUtil.CUSTOM_VARIANT, "tmeet" );
    imageLabel.setImage( ImageUtil.createMonsterImage( monster ) );
    FormData imageLabelData = new FormData();
    imageLabel.setLayoutData( imageLabelData );
    imageLabelData.left = new FormAttachment( 0, 0 );
    imageLabelData.top = new FormAttachment( 0, 0 );
    return imageLabel;
  }

  private void createNameLabel( final Monster monster,
                                Composite container,
                                Label imageLabel )
  {
    Label nameLabel = new Label( container, SWT.NONE );
    nameLabel.setData( WidgetUtil.CUSTOM_VARIANT, "tmeet" );
    nameLabel.setText( monster.getName() );
    FormData nameLabelData = new FormData();
    nameLabel.setLayoutData( nameLabelData );
    nameLabelData.left = new FormAttachment( imageLabel, 25 );
    nameLabelData.top = new FormAttachment( 0, 5 );
  }

  private void createSpeciesLabel( final Monster monster,
                                   Composite container,
                                   Label imageLabel )
  {
    Label speciesLabel = new Label( container, SWT.NONE );
    speciesLabel.setData( WidgetUtil.CUSTOM_VARIANT, "timestamp" );
    speciesLabel.setText( monster.getSpecies().toString() );
    FormData speciesLabelData = new FormData();
    speciesLabel.setLayoutData( speciesLabelData );
    speciesLabelData.left = new FormAttachment( imageLabel, 35 );
    speciesLabelData.top = new FormAttachment( 0, 25 );
  }

  private void createFollowButton( final Monster monster, Composite container ) {
    Button followButton = new Button( container, SWT.NONE );
    followButton.setData( WidgetUtil.CUSTOM_VARIANT, "login" );
    followButton.setText( "Delete" );
    FormData followButtonData = new FormData();
    followButton.setLayoutData( followButtonData );
    followButtonData.right = new FormAttachment( 100, -5 );
    followButtonData.bottom = new FormAttachment( 100, -5 );
    followButton.addSelectionListener( new SelectionAdapter() {
      private static final long serialVersionUID = 1L;
  
      public void widgetSelected( SelectionEvent e ) {
        delete( monster.getName() );
      }
    } );
  }

  public void delete( String monsterName ) {
    Control[] children = userContainer.getChildren();
    for( Control child : children ) {
      child.dispose();
    }
    MonsterUtil.deleteMonster( monsterName );
    createMonsterControls();
  }
}
