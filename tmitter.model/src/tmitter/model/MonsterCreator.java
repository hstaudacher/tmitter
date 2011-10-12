package tmitter.model;



public class MonsterCreator {
  
  
  public static void main( String[] args ) {
    Monster herman = new Monster( "herman" );
    herman.setPassword( "munster" );
    herman.setSpecies( Species.ZOMBIE );
    
    Monster lily = new Monster( "lily" );
    lily.setPassword( "munster" );
    lily.setSpecies( Species.VAMPIRE );
    
    Monster grandpa = new Monster( "grandpa" );
    grandpa.setPassword( "munster" );
    grandpa.setSpecies( Species.VAMPIRE );
    
    Monster eddie = new Monster( "eddie" );
    eddie.setPassword( "munster" );
    eddie.setSpecies( Species.WEREWOLF );
    
    lily.updateStatus( "Oh dear, this is a network for us ;)" );
    sleep();
    grandpa.updateStatus( "@lily it seems so." );
    sleep();
    lily.updateStatus( "@grandpa we have to invite herman and eddie" );
    sleep();
    eddie.updateStatus( "@lily @grandpa no need. i found my way to Tmitter" );
    sleep();
    eddie.updateStatus( "Only dad misses this fun" );
    sleep();
    herman.updateStatus( "Nope. Just because I consist of ten men does not mean I can't tmeet" );
    sleep();
    grandpa.updateStatus( "Welcome @herman to Tmitter" );
    sleep();
    eddie.updateStatus( "@herman hi dad, I'm going to do my homework now" );
    sleep();
    herman.updateStatus( "Such a god baby dog" );
    sleep();
    lily.updateStatus( "@herman Hi my dear. Can you bring some poisen from the market?" );
    System.out.println( "Created tmeets" );
    sleep();
    
    herman.follow( "grandpa" );
    herman.follow( "lily" );
    
    grandpa.follow( "lily" );
    grandpa.follow( "grandpa" );
    grandpa.follow( "herman" );
    
    eddie.follow( "herman" );
    eddie.follow( "lily" );
    
    lily.follow( "grandpa" );
    lily.follow( "herman" );
    lily.follow( "eddie" );
    
    System.out.println( "Added followers" );
    
    lily.save();
    grandpa.save();
    herman.save();
    eddie.save();
    System.out.println( "Saved monsters" );
    System.out.println( "--- DONE ---" );
  }
  
  private static void sleep() {
    try {
      System.out.println( "Creating tmeet" );
      Thread.sleep( 1000 );
    } catch( InterruptedException e ) {
      e.printStackTrace();
    }
  }
  
}
