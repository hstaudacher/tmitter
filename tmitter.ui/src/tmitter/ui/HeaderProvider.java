package tmitter.ui;

import org.eclipse.rwt.lifecycle.WidgetUtil;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;

import com.codeaffine.example.rwt.osgi.ui.platform.UIContributor;

class HeaderProvider implements UIContributor {
  public static final String HEADER_CONTROL = HeaderProvider.class.getName() + "#HEADER";
  static final int HEADER_HEIGHT = 90;
  
  @Override
  public String getId() {
    return HEADER_CONTROL;
  }
  
  @Override
  public Control contribute( Composite parent ) {
    Composite result = new Composite( parent, SWT.INHERIT_DEFAULT );
    result.setData( WidgetUtil.CUSTOM_VARIANT, "header" );
    result.setLayout( new FormLayout() );
    Label logo = new Label( result, SWT.NONE );
    logo.setData( WidgetUtil.CUSTOM_VARIANT, "logo" );
    FormData logoData = new FormData();
    logo.setLayoutData( logoData );
    logoData.top = new FormAttachment( 0, 8 );
    logoData.left = new FormAttachment( 0, 10 );
    logoData.width = 250;
    logoData.height = 67;
    return result;
  }
}