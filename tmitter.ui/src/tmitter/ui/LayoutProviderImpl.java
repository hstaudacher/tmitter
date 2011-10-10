package tmitter.ui;

import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;

import com.codeaffine.example.rwt.osgi.ui.platform.LayoutProvider;


class LayoutProviderImpl implements LayoutProvider {

  private static final int OFFSET = 20;

  private Control content;
  private Control header;
  private Control menuBar;
  private Control footer;
  private Control background;
  private Control loginPanel;

  @Override
  public void layout( LayoutContext context ) {
    readControls( context );
    configureLayout();
  }
  
  private void readControls( LayoutContext context ) {
    header = context.getControl( HeaderProvider.HEADER_CONTROL );
    menuBar = context.getControl( MenuBarProvider.MENU_BAR_CONTROL );
    menuBar.moveAbove( header );
    loginPanel = context.getControl( AuthPanelContentProvider.AUTH_CONTROL );
    loginPanel.moveAbove( header );
    content = context.getControl( ContentProvider.CONTENT_CONTROL );
    footer = context.getControl( FooterProvider.FOOTER_CONTROL );
    background = context.getControl( BackgroundProvider.BACKGROUND_CONTROL );
  }

  private void configureLayout() {
    layoutHeader();
    layoutMenuBar();
    layoutAuthPanel();
    layoutContent();
    layoutFooter();
    layoutBackground();
  }

  private void layoutHeader() {
    FormData layoutData = new FormData();
    header.setLayoutData( layoutData );
    layoutData.top = new FormAttachment( 0, 0 );
    layoutData.left = new FormAttachment( 0, 0 );
    layoutData.right = new FormAttachment( 100, 0 );
    layoutData.height = HeaderProvider.HEADER_HEIGHT;
  }

  private void layoutMenuBar() {
    FormData layoutData = new FormData();
    menuBar.setLayoutData( layoutData );
    layoutData.top = new FormAttachment( 0, 0 );
    layoutData.left = new FormAttachment( 0, 300 );
    layoutData.right = new FormAttachment( 100, 0 );
    layoutData.height = MenuBarProvider.MENU_BAR_HEIGHT;
  }

  private void layoutAuthPanel() {
    FormData layoutData = new FormData();
    loginPanel.setLayoutData( layoutData );
    layoutData.left = new FormAttachment( 0, 300 );
    layoutData.top = new FormAttachment( 0, MenuBarProvider.MENU_BAR_HEIGHT + 20 );
    layoutData.right = new FormAttachment( 100, 0 );
    layoutData.height = 30;
  }

  private void layoutContent() {
    FormData layoutData = new FormData();
    content.setLayoutData( layoutData );
    layoutData.top = new FormAttachment( 0, HeaderProvider.HEADER_HEIGHT );
    layoutData.left = new FormAttachment( 0, 0 );
    layoutData.right = new FormAttachment( 100, 0 );
    int height = calculateHeight();
    layoutData.height = height - 5;
    layoutData.width = content.getParent().getSize().x;
  }

  private int calculateHeight() {
    int height = content.getSize().y;
    int maximumHeight = getMaximumHeight();
    if( height < maximumHeight ) {
      height = maximumHeight;
    }
    return height;
  }

  private int getMaximumHeight() {
    int height;
    Rectangle bounds = Display.getCurrent().getBounds();
    height =   bounds.height 
             - FooterProvider.FOOTER_HEIGHT 
             - HeaderProvider.HEADER_HEIGHT 
             - MenuBarProvider.MENU_BAR_HEIGHT 
             - OFFSET;
    return height;
  }

  private void layoutFooter() {
    FormData layoutData = new FormData();
    footer.setLayoutData( layoutData );
    layoutData.top = new FormAttachment( content );
    layoutData.left = new FormAttachment( 0, 0 );
    layoutData.right = new FormAttachment( 100, 0 );
    layoutData.height = FooterProvider.FOOTER_HEIGHT;
  }

  private void layoutBackground() {
    FormData layoutData = new FormData();
    background.setLayoutData( layoutData );
    layoutData.top = new FormAttachment( 0, 0 );
    layoutData.left = new FormAttachment( 0, 0 );
    layoutData.right = new FormAttachment( 100, 0 );
    layoutData.bottom = new FormAttachment( 100, 0 );
  }
}