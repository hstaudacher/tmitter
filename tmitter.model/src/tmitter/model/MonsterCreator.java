package tmitter.model;



public class MonsterCreator {
  
  
  public static void main( String[] args ) {
    Monster herman = new Monster( "herman" );
    herman.setPassword( "munster" );
    herman.setSpecies( Species.ZOMBIE );
    
    herman.updateStatus( "My first Status" );
    sleep();
    herman.updateStatus( "My second Status" );
    sleep();
    herman.updateStatus( "My third Status" );
    sleep();
    
    Monster lilli = new Monster( "lily" );
    lilli.updateStatus( "I love @herman :)" );
    sleep();
    herman.updateStatus( "I know..." );
    sleep();
    Monster grandpa = new Monster( "grandpa" );
    grandpa.updateStatus( "I'm the count" );
    sleep();
    lilli.save();
    grandpa.save();
    
    herman.follow( lilli.getName() );
    herman.follow( grandpa.getName() );
    
    herman.save();
    
    Monster loadedHurman = MonsterUtil.loadMonster( "herman" );
    System.out.println( loadedHurman );
  }
  
  private static void sleep() {
    try {
      Thread.sleep( 1000 );
    } catch( InterruptedException e ) {
      e.printStackTrace();
    }
  }
  
}
