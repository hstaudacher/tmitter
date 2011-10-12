package tmitter.mobile.ui;


import java.util.List;

import org.eclipse.rwt.graphics.Graphics;
import org.eclipse.rwt.lifecycle.IEntryPoint;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import tmitter.model.Monster;
import tmitter.model.MonsterUtil;
import tmitter.ui.TmeetsList;


public class UIEntryPoint implements IEntryPoint {

  private TmeetsList tmeetList;

  @Override
  public int createUI() {
    Display display = new Display();
    Shell shell = new Shell( display, SWT.NONE );
    shell.setBackground( Graphics.getColor( 255, 255, 255 ) );
    shell.setLayout( new FormLayout() );
    shell.setMaximized( true );
    final Text inputText = new Text( shell, SWT.SINGLE | SWT.BORDER );
    FormData inputTextData = new FormData();
    inputText.setLayoutData( inputTextData );
    inputTextData.left = new FormAttachment( 0, 3 );
    inputTextData.top = new FormAttachment( 0, 3 );
    inputTextData.right = new FormAttachment( 100, -3 );
    Composite tmeetContainer = new Composite( shell, SWT.NONE );
    tmeetContainer.setLayout( new FillLayout() );
    FormData tmeetContainerData = new FormData();
    tmeetContainer.setLayoutData( tmeetContainerData );
    tmeetContainerData.left = new FormAttachment( 0, 3 );
    tmeetContainerData.top = new FormAttachment( inputText, 3 );
    tmeetContainerData.right = new FormAttachment( 100, -3 );
    tmeetContainerData.bottom = new FormAttachment( 100, -3 );
    List<String> allMonsterNames = MonsterUtil.getAllMonsterNames();
    tmeetList = new TmeetsList( allMonsterNames, null, null );
    tmeetList.createContols( tmeetContainer );
    inputText.addSelectionListener( new SelectionAdapter() {
      private static final long serialVersionUID = 1L;
      
      public void widgetDefaultSelected( SelectionEvent e ) {
        Monster herman = MonsterUtil.loadMonster( "herman" );
        herman.updateStatus( inputText.getText() );
        inputText.setText( "" );
        herman.save();
        tmeetList.refresh( null );
      }
    } );
    shell.open();
    while( !shell.isDisposed() ) {
      if( !display.readAndDispatch() )
        display.sleep();
    }
    display.dispose();
    return 0;
  }


}