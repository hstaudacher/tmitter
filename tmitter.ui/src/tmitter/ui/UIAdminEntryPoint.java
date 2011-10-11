package tmitter.ui;

import org.eclipse.rwt.lifecycle.IEntryPoint;
import org.eclipse.rwt.lifecycle.WidgetUtil;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;
import org.osgi.framework.ServiceReference;

import com.codeaffine.example.rwt.osgi.ui.platform.LayoutProvider;
import com.codeaffine.example.rwt.osgi.ui.platform.ServiceProvider;
import com.codeaffine.example.rwt.osgi.ui.platform.ShellConfigurator;
import com.codeaffine.example.rwt.osgi.ui.platform.UIContributor;


public class UIAdminEntryPoint implements IEntryPoint {
  
  private ServiceProvider serviceProvider;
  private Shell shell;

  @Override
  public int createUI() {
    initializeServiceProvider();
    configureShell();
    openShell();
    spinReadAndDispatch();
    return 0;
  }

  private void configureShell() {
    UIContributor[] pageStructureProviders = new UIContributor[] {
      new HeaderProvider(), 
      new MenuBarProvider( serviceProvider ),
      new AuthPanelContentProvider( serviceProvider ),
      new ContentProvider( serviceProvider ),
      new FooterProvider(),
      new BackgroundProvider() 
    };
    LayoutProvider layoutProvider = new LayoutProviderImpl();
    ShellConfigurator configurator = new ShellConfigurator( serviceProvider );
    shell = configurator.configure( pageStructureProviders, layoutProvider );
    shell.setData( WidgetUtil.CUSTOM_VARIANT, "mainShell" );
  }

  private void initializeServiceProvider() {
    getServiceProvider();
    serviceProvider.register( HomePageAction.class, new HomePageAction() );
  }

  private void getServiceProvider() {
    Class<ServiceProvider> type = ServiceProvider.class;
    BundleContext context = FrameworkUtil.getBundle( getClass() ).getBundleContext();
    ServiceReference<ServiceProvider> serviceReference = context.getServiceReference( type );
    serviceProvider = ( ServiceProvider )context.getService( serviceReference );
  }

  private void openShell() {
    shell.open();
  }

  private void spinReadAndDispatch() {
    Display display = shell.getDisplay();
    while( !shell.isDisposed() ) {
      if( !display.readAndDispatch() ) {
        display.sleep();
      }
    }
  }
}
