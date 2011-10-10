package tmitter.ui;

import org.eclipse.rwt.lifecycle.WidgetUtil;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.*;
import org.eclipse.swt.widgets.*;
import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;
import org.osgi.framework.Version;

import com.codeaffine.example.rwt.osgi.ui.platform.UIContributor;

class FooterProvider implements UIContributor {
  public static final String FOOTER_CONTROL = FooterProvider.class.getName() + "#FOOTER";
  static final int FOOTER_HEIGHT = 20;
  // NOTE: this value reflects the height of the footer_separator background image set via css
  private static final int SEPARATOR_HEIGHT = 2;


  @Override
  public String getId() {
    return FOOTER_CONTROL;
  }
  
  @Override
  public Control contribute( Composite parent ) {
    Composite result = new Composite( parent, SWT.INHERIT_DEFAULT );
    result.setLayout( new FormLayout() );

    Label separator = new Label( result, SWT.NONE );
    separator.setData( WidgetUtil.CUSTOM_VARIANT, "footer_separator" );
    FormData separatorData = new FormData();
    separator.setLayoutData( separatorData );
    separatorData.top = new FormAttachment( 0, 0 );
    separatorData.left = new FormAttachment( 0, 0 );
    separatorData.right = new FormAttachment( 100, -50 );
    separatorData.height = SEPARATOR_HEIGHT;
    
    Label versionInfo = new Label( result, SWT.NONE );
    versionInfo.setData( WidgetUtil.CUSTOM_VARIANT, "versionInfo" );
    versionInfo.setText( getVersionInfo() );
    versionInfo.pack();
    FormData versionInfoData = new FormData();
    versionInfo.setLayoutData( versionInfoData );
    Point size = versionInfo.getSize();
    versionInfoData.top = new FormAttachment( 50, -( size.y / 2 ) + ( SEPARATOR_HEIGHT + 3) );
    versionInfoData.left = new FormAttachment( 0, 10 );
    return result;
  }

  private String getVersionInfo() {
    Bundle bundle = FrameworkUtil.getBundle( getClass() ).getBundleContext().getBundle();
    String name = bundle.getHeaders().get( "Bundle-Name" );
    Version version = bundle.getVersion();
    return name + " (" + version + ")";
  }
}