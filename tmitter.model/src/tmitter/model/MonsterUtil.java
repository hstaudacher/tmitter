package tmitter.model;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


public class MonsterUtil {
  
  private static final String MONSTERS_FOLDER = "monsters";
  private static final String MONSTER_SUFIX = ".json";

  public static List<String> getAllMonsterNames() {
    ArrayList<String> result = new ArrayList<String>();
    File monsterDirectory = new File( getTmpLocation() + MONSTERS_FOLDER );
    String[] children = monsterDirectory.list();
    for( String child : children ) {
      int lastIndexOfSlash = child.lastIndexOf( File.separatorChar );
      int indexOfSufix = child.indexOf( MONSTER_SUFIX );
      result.add( child.substring( lastIndexOfSlash + 1, indexOfSufix ) );
    }
    return result;
  }
  
  public static boolean isRegistered( String monsterName ) {
    File monsterFile = getMonsterFile( monsterName );
    return monsterFile.exists();
  }
  
  public static List<Monster> getWatchedMonsters( Monster monster ) {
    ArrayList<Monster> result = new ArrayList<Monster>();
    List<String> watchedMonsters = monster.getWatchedMonsters();
    for( String monsterName : watchedMonsters ) {
      try {
        result.add( loadMonster( monsterName ) );
      } catch( IllegalArgumentException iae ) {
        //do nothing
      }
    }
    return result;
  }
  
  /**
   * @throws IllegalArgumentException when the monster does not exist
   * @param name the monster's name
   * 
   * @return the deserialized monster
   */
  public static Monster loadMonster( String name ) {
    File monsterFile = getMonsterFile( name );
    if( !monsterFile.exists() ) {
      throw new IllegalArgumentException( "Monster " + name + " does not exist." );
    }
    Gson gson = new Gson();
    return deserializeMonster( name, monsterFile, gson);
  }

  private static Monster deserializeMonster( String name, File monsterFile, Gson gson ) {
    try {
      Monster monster = gson.fromJson( new FileReader( monsterFile ), Monster.class );
      return monster;
    } catch( Exception shouldNotHappen ) {
      throw new IllegalStateException( "Could not load monster " + name, shouldNotHappen );
    }
  }

  /**
   * @throws IllegalStateException when the monster couldn't be saved
   * 
   * @param monster the monster to save
   */
  public static void saveMonster( Monster monster ) {
    String name = monster.getName();
    Gson gson = new GsonBuilder().setPrettyPrinting().create();
    String serializedMonster = gson.toJson( monster );
    File monsterFile = getMonsterFile( name );
    if( monsterFile.exists() ) {
      monsterFile.delete();
    }
    writeMonsterToFile( name, serializedMonster, monsterFile );
  }
  
  private static void writeMonsterToFile( String name,
                                          String serializedMonster,
                                          File monsterFile )
  {
    try {
      checkMonstersFolder();
      monsterFile.createNewFile();
      FileWriter fileWriter = new FileWriter( monsterFile );
      fileWriter.write( serializedMonster );
      fileWriter.close();
    } catch( IOException shouldNotHappen ) {
      throw new IllegalStateException( "Could not create file for monster " + name, 
                                       shouldNotHappen );
    }
  }
  
  private static void checkMonstersFolder() {
    File monstersFolder = new File( getTmpLocation() + MONSTERS_FOLDER );
    if( !monstersFolder.exists() ) {
      monstersFolder.mkdir();
    }
  }

  private static File getMonsterFile( String name ) {
    String tmpDir = getTmpLocation();
    return new File( tmpDir + MONSTERS_FOLDER + File.separator + name + MONSTER_SUFIX );
  }

  private static String getTmpLocation() {
    String tmpDir = System.getProperty( "java.io.tmpdir" );
    if( !tmpDir.endsWith( File.separator ) ) {
      tmpDir += File.separator;
    }
    return tmpDir;
  }
  
  public static String getMonsterImagePath( Monster currentMonster ) {
    return "/icons/" + currentMonster.getName() + ".png";
  }
  
  public static String getMonsterSmallImagePath( Monster currentMonster ) {
    return "/icons/" + currentMonster.getName() + "-small.png";
  }
  
  private MonsterUtil() {
    // prevent instantiation
  }

  
}
