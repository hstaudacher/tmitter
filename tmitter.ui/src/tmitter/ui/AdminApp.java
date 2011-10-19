package tmitter.ui;

import org.eclipse.rwt.application.ApplicationConfiguration;
import org.eclipse.rwt.application.ApplicationConfigurator;

import com.codeaffine.example.rwt.osgi.ui.platform.ConfiguratorTracker;
import com.codeaffine.example.rwt.osgi.ui.platform.ShellPositioner;


public class AdminApp implements ApplicationConfigurator {
  
  static final String TMITTER_UI = "tmitter-admin";
  
  @Override
  public void configure( ApplicationConfiguration configuration ) {
    configuration.addEntryPoint( "default", UIAdminEntryPoint.class );
    configuration.addStyleSheet( TMITTER_UI, "theme/admin/theme.css" );
    configuration.addBranding( new UIAdminBranding() );
    configuration.addPhaseListener( new ShellPositioner( false ) );
    new ConfiguratorTracker( this, configuration ).open();
  }
}
