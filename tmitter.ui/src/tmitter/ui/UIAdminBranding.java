package tmitter.ui;

import org.eclipse.rwt.branding.AbstractBranding;


public class UIAdminBranding extends AbstractBranding {

  @Override
  public String getServletName() {
    return AdminApp.TMITTER_UI;
  }

  @Override
  public String getThemeId() {
    return AdminApp.TMITTER_UI;
  }
  
  @Override
  public String getTitle() {
    return "Tmitter Admin App";
  }
}
