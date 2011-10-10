package tmitter.ui;

import org.eclipse.rwt.branding.AbstractBranding;

class UIBranding extends AbstractBranding {

  @Override
  public String getServletName() {
    return App.TMITTER_UI;
  }

  @Override
  public String getThemeId() {
    return App.TMITTER_UI;
  }
  
  @Override
  public String getTitle() {
    return "Tmitter";
  }
}