package tmitter.ui;

import org.eclipse.rwt.lifecycle.WidgetUtil;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;

import com.codeaffine.example.rwt.osgi.ui.platform.UIContributor;

class BackgroundProvider implements UIContributor {
  public static final String BACKGROUND_CONTROL
    = BackgroundProvider.class.getName() + "#Background";

  @Override
  public String getId() {
    return BACKGROUND_CONTROL;
  }

  @Override
  public Control contribute( Composite parent ) {
    Label result = new Label( parent, SWT.NONE );
    result.setData( WidgetUtil.CUSTOM_VARIANT, "content-background" );
    result.moveBelow( null );
    return result;
  }
}