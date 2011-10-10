package tmitter.ui;

import org.eclipse.rwt.application.ApplicationConfiguration;
import org.eclipse.rwt.application.ApplicationConfigurator;

import com.codeaffine.example.rwt.osgi.ui.platform.ConfiguratorTracker;
import com.codeaffine.example.rwt.osgi.ui.platform.ShellPositioner;


public class App implements ApplicationConfigurator {
  static final String TMITTER_UI = "tmitter";
  
  @Override
  public void configure( ApplicationConfiguration configuration ) {
    configuration.addEntryPoint( "default", UIEntryPoint.class );
    configuration.addStyleSheet( TMITTER_UI, "theme/theme.css" );
    configuration.addBranding( new UIBranding() );
    configuration.addPhaseListener( new ShellPositioner() );
    new ConfiguratorTracker( this, configuration ).open();
  }
}
