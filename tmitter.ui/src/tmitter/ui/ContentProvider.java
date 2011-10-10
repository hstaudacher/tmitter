package tmitter.ui;

import org.eclipse.rwt.lifecycle.WidgetUtil;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

import com.codeaffine.example.rwt.osgi.ui.platform.PageService;
import com.codeaffine.example.rwt.osgi.ui.platform.ServiceProvider;
import com.codeaffine.example.rwt.osgi.ui.platform.UIContributor;

class ContentProvider implements UIContributor {
  public static final String CONTENT_CONTROL = ContentProvider.class.getName() + "#CONTENT";
  
  private final ServiceProvider serviceProvider;

  ContentProvider( ServiceProvider serviceProvider ) {
    this.serviceProvider = serviceProvider;
  }
  
  @Override
  public String getId() {
    return CONTENT_CONTROL;
  }
  
  @Override
  public Control contribute( Composite parent ) {
    Composite result = new Composite( parent, SWT.INHERIT_NONE );
    result.setData( WidgetUtil.CUSTOM_VARIANT, "content" );
    result.setLayout( new FillLayout() );
    PageService pageService = serviceProvider.get( PageService.class );
    pageService.registerContentParent( result );
    return result;
  }
}