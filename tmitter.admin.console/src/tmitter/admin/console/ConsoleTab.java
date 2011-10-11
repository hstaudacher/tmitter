package tmitter.admin.console;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

import com.codeaffine.example.rwt.osgi.configurationadmin.console.OSGiConsole;
import com.codeaffine.example.rwt.osgi.ui.platform.UIContributor;


public class ConsoleTab implements UIContributor {

  @Override
  public String getId() {
    return "Console";
  }

  @Override
  public Control contribute( Composite parent ) {
    OSGiConsole osgiConsole = new OSGiConsole();
    osgiConsole.create( parent );
    osgiConsole.getControl().setFocus();
    return osgiConsole.getControl();
  }
}
