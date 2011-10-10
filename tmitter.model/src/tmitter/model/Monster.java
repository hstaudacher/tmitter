package tmitter.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class Monster {
  
  private String name;
  private String password;
  private Species species;
  private List<Status> tmeets;
  private List<String> watchedMonsters;
  
  Monster() {
    // no args constructor for gson
  }
  
  public Monster( String name ) {
    this.name = name;
    this.species = Species.UNDISCOVERED;
  }

  public String getName() {
    return name;
  }
  
  public void setPassword( String password ) {
    this.password = password;
  }

  public String getPassword() {
    return password;
  }
  
  public List<Status> getTmeets() {
    return tmeets;
  }
  
  public List<String> getWatchedMonsters() {
    return watchedMonsters;
  }

  public Species getSpecies() {
    return species;
  }

  public void setSpecies( Species species ) {
    this.species = species;
  }

  public void updateStatus( String statusMessage ) {
    if( tmeets == null ) {
      tmeets = new ArrayList<Status>();
    }
    tmeets.add( new Status( statusMessage, new Date() ) );
  }

  public void deleteStatus( Status tmeet ) {
    if( tmeets != null ) {
      List<Status> tmpTmeets = new ArrayList<Status>( tmeets );
      for( Status currentTmeet : tmpTmeets ) {
        if(    currentTmeet.getMessage().equals( tmeet.getMessage() ) 
            && currentTmeet.getTimestamp().equals( tmeet.getTimestamp() ) ) 
        {
          tmeets.remove( currentTmeet );
        }
      }
    }
  }

  public void follow( String monsterToFollowName ) {
    if( watchedMonsters == null ) {
      watchedMonsters = new ArrayList<String>();
    }
    watchedMonsters.add( monsterToFollowName );
  }

  public void unfollow( String monsterToStopFollowing ) {
    if( watchedMonsters != null ) {
      watchedMonsters.remove( monsterToStopFollowing );
    }
  }

  public void save() {
    MonsterUtil.saveMonster( this );
  }
  
}
