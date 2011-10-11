package tmitter.ui.mentions;

import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

import tmitter.model.Monster;
import tmitter.model.MonsterUtil;
import tmitter.ui.TmeetsList;

import com.codeaffine.example.rwt.osgi.ui.platform.ServiceProvider;
import com.codeaffine.example.rwt.osgi.ui.platform.UIContributor;


public class MentionsTab implements UIContributor {

  private ServiceProvider serviceProvider;

  @Override
  public String getId() {
    return "Mentions";
  }
  
  void setServiceProvider( ServiceProvider serviceProvider ) {
    this.serviceProvider = serviceProvider;
  }

  @Override
  public Control contribute( Composite parent ) {
    Composite result = new Composite( parent, SWT.NONE );
    result.setLayout( new FillLayout() );
    List<String> allMonsterNames = MonsterUtil.getAllMonsterNames();
    Monster monster = serviceProvider.get( Monster.class );
    if( monster != null ) {
      TmeetsList tmeetsList = new TmeetsList( allMonsterNames, monster, "@" + monster.getName() );
      tmeetsList.createContols( result );
    }
    return result;
  }
}
