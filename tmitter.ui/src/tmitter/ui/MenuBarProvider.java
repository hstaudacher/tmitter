package tmitter.ui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.rwt.lifecycle.WidgetUtil;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

import tmitter.model.Monster;

import com.codeaffine.example.rwt.osgi.ui.platform.PageService;
import com.codeaffine.example.rwt.osgi.ui.platform.PageTracker;
import com.codeaffine.example.rwt.osgi.ui.platform.ServiceProvider;
import com.codeaffine.example.rwt.osgi.ui.platform.UIContributor;

class MenuBarProvider implements UIContributor {
  public static final String MENU_BAR_CONTROL = MenuBarProvider.class.getName() + "#MENUBAR";
  // NOTE: this value reflects the height of the menubar_background image set via css
  static final int MENU_BAR_HEIGHT = 31;
  private static final String MENUBAR_BACKGROUND = "menubar_background";
  private static final String MENU_BUTTON = "menu_button";

  private final ServiceProvider serviceProvider;
  private Composite menuParent;
  private Map<UIContributor, Button> buttonMap;
  private List<UIContributor> pageQueue;

  MenuBarProvider( ServiceProvider serviceProvider ) {
    this.serviceProvider = serviceProvider;
    serviceProvider.register( MenuBarProvider.class, this );
    buttonMap = new HashMap<UIContributor, Button>();
    pageQueue = new ArrayList<UIContributor>();
  }

  @Override
  public String getId() {
    return MENU_BAR_CONTROL;
  }

  @Override
  public Control contribute( Composite parent ) {
    menuParent = new Composite( parent, SWT.INHERIT_NONE );
    menuParent.setData( WidgetUtil.CUSTOM_VARIANT, MENUBAR_BACKGROUND );
    menuParent.setLayout( new RowLayout() );
    
    final PageService pageService = serviceProvider.get( PageService.class );
    pageService.addPageTracker( new PageTracker() {

      @Override
      public void pageAdded( UIContributor page ) {
        if( page instanceof PublicTimeLineTab ) {
          pageService.setHomePageContributor( page );
        }
        addPage( page );
      }

      @Override
      public void pageRemoved( UIContributor page ) {
        removePage( page );
      }
    } );
    
    return menuParent;
  }

  protected void addPage( UIContributor page ) {
    Monster currentMonster = serviceProvider.get( Monster.class );
    if( currentMonster != null || isHomePage( page ) ) {
      Button menuButton = createMenuButton( page.getId() );
      buttonMap.put( page, menuButton );
    } else {
      addPageToQueue( page );
    }
    
  }

  private void addPageToQueue( UIContributor page ) {
    pageQueue.add( page );
  }
  
  public void flushPageQueue() {
    List<UIContributor> tmpPageQueue = new ArrayList<UIContributor>( pageQueue );
    pageQueue.clear();
    for( UIContributor contributor : tmpPageQueue ) {
      addPage( contributor );
    }
    recreateTweetList( serviceProvider.get( Monster.class ) );
  }
  
  public void hideMenuEntries() {
    Map<UIContributor, Button> tmpButtonMap = new HashMap<UIContributor, Button>( buttonMap );
    Set<UIContributor> keySet = tmpButtonMap.keySet();
    for( UIContributor uiContributor : keySet ) {
      if( !isHomePage( uiContributor ) ) {
        Button button = buttonMap.get( uiContributor );
        if( button != null && !button.isDisposed() ) {
          button.dispose();
          buttonMap.remove( uiContributor );
        }
        pageQueue.add( uiContributor );
      }
    }
    recreateTweetList( null );
  }

  private void recreateTweetList( Monster curentMonster ) {
    PageService pageService = serviceProvider.get( PageService.class );
    UIContributor homePageContributor = pageService.getHomePageContributor();
    if( homePageContributor != null && ( homePageContributor instanceof PublicTimeLineTab ) ) {
      PublicTimeLineTab tab = ( PublicTimeLineTab )homePageContributor;
      tab.refreshList( curentMonster );
    }
  }

  private boolean isHomePage( UIContributor uiContributor ) {
    PageService pageService = serviceProvider.get( PageService.class );
    return pageService.isHomePage( uiContributor );
  }

  protected void removePage( UIContributor page ) {
    Button button = buttonMap.get( page );
    buttonMap.remove( page );
    if( button != null && !button.isDisposed() ) {
      button.dispose();
    }
  }

  Button createMenuButton( final String pageId ) {
    final PageService pageService = serviceProvider.get( PageService.class );
    Button result = new Button( menuParent, SWT.PUSH );
    result.setData( WidgetUtil.CUSTOM_VARIANT, MENU_BUTTON );
    result.setText( pageId );
    result.addSelectionListener( new SelectionAdapter() {
      private static final long serialVersionUID = 1L;

      @Override
      public void widgetSelected( SelectionEvent evt ) {
        pageService.selectPage( pageId );
      }
    } );
    return result;
  }
}