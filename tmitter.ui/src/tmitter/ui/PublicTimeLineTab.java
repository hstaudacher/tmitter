package tmitter.ui;

import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

import tmitter.model.Monster;
import tmitter.model.MonsterUtil;

import com.codeaffine.example.rwt.osgi.ui.platform.ServiceProvider;
import com.codeaffine.example.rwt.osgi.ui.platform.UIContributor;


public class PublicTimeLineTab implements UIContributor {
  
  public static final String ID = "All Tmeets";
  ServiceProvider serviceProvider;
  private TmeetsList tmeetsList;

  void setServiceProvider( ServiceProvider serviceProvider ) {
    this.serviceProvider = serviceProvider;
  }
  
  @Override
  public String getId() {
    return ID;
  }

  @Override
  public Control contribute( Composite parent ) {
    Composite result = new Composite( parent, SWT.NONE );
    result.setLayout( new FillLayout() );
    List<String> allMonsterNames = MonsterUtil.getAllMonsterNames();
    tmeetsList = new TmeetsList( allMonsterNames, serviceProvider.get( Monster.class ), null );
    tmeetsList.createContols( result );
    return result;
  }

  public void refreshList( Monster monster ) {
    if( tmeetsList != null ) {
      tmeetsList.refresh( monster );
    }
  }
}
