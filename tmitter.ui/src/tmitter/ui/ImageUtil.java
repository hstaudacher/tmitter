package tmitter.ui;

import java.net.URL;

import org.eclipse.rwt.graphics.Graphics;
import org.eclipse.swt.graphics.Image;

import tmitter.model.Monster;
import tmitter.model.MonsterUtil;


public class ImageUtil {

  public static Image createSmallMonsterImage( Monster monster ) {
    String path = MonsterUtil.getMonsterSmallImagePath( monster );
    URL monterImage = App.class.getResource( path );
    if( monterImage == null ) {
      path = "/icons/default-icon-small.png";
    }
    return Graphics.getImage( path, App.class.getClassLoader() );
  }
  
  public static Image createMonsterImage( Monster monster ) {
    String path = "/icons/default-icon.png";
    if( monster != null ) {
      URL monterImage = App.class.getResource( MonsterUtil.getMonsterImagePath( monster ) );
      if( monterImage != null ) {
        path = MonsterUtil.getMonsterImagePath( monster );
      }
    }
    return Graphics.getImage( path, App.class.getClassLoader() );
  }
}
