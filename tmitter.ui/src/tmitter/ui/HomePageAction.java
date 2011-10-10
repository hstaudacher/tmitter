package tmitter.ui;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.MessageBox;


class HomePageAction {

  void execute() {
    MessageBox messageBox = new MessageBox( Display.getCurrent().getActiveShell() );
    messageBox.setMessage( "Huhu" );
    messageBox.open();
  }
}
