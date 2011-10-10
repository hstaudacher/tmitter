package tmitter.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;


public class StatusUtil {
  
  public static List<Status> getAllStatusUpdates() {
    List<String> allMonsterNames = MonsterUtil.getAllMonsterNames();
    List<Monster> allMonsters = new ArrayList<Monster>();
    for( String name : allMonsterNames ) {
      allMonsters.add( MonsterUtil.loadMonster( name ) );
    }
    return createStatusUpdateListFromMonsters( allMonsters );
  }
  
  public static List<Status> getSortedStatusUpdates( Monster monster ) {
    ArrayList<Status> result = new ArrayList<Status>( monster.getTmeets() );
    Collections.sort( result, createStatusComparator() );
    return result;
  }
  
  public static List<Status> getWatchedMonsterStatusUpdates( Monster monster ) {
    List<Monster> watchedMonsters = MonsterUtil.getWatchedMonsters( monster );
    List<Status> result = createStatusUpdateListFromMonsters( watchedMonsters );
    Collections.sort( result, createStatusComparator() );
    return result;
  }

  public static List<Status> createStatusUpdateListFromMonsters( List<Monster> monsters )
  {
    List<Status> updates = new ArrayList<Status>();
    for( Monster monster : monsters ) {
      List<Status> tweets = monster.getTmeets();
      if( tweets != null ) {
        for( Status status : tweets ) {
          updates.add( status );
        }
      }
    }
    Collections.sort( updates, createStatusComparator() );
    return updates;
  }

  private static Comparator<Status> createStatusComparator() {
    return new Comparator<Status>() {
      @Override
      public int compare( Status o1, Status o2 ) {
        Date timestamp1 = o1.getTimestamp();
        Date timestamp2 = o2.getTimestamp();
        int result = 0;
        if( timestamp1.after( timestamp2 ) ) {
          result = -1;
        } else if( timestamp1.before( timestamp2 ) ) {
          result = 1;
        }
        return result;
      }
    };
  }
  
  private StatusUtil() {
    // do nothing
  }
  
}
