package tmitter.mobile.ui;


import org.eclipse.rwt.application.ApplicationConfiguration;
import org.eclipse.rwt.application.ApplicationConfigurator;


public class App implements ApplicationConfigurator {
  static final String TMITTER_UI = "mobile";
  
  @Override
  public void configure( ApplicationConfiguration configuration ) {
    configuration.addEntryPoint( "default", UIEntryPoint.class );
//    configuration.addStyleSheet( TMITTER_UI, "theme/main/theme.css" );
    configuration.addStyleSheet( "org.eclipse.rap.rwt.theme.Default", "theme/theme.css" );
    configuration.addBranding( new UIBranding() );
  }
}
