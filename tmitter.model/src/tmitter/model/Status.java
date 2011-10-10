package tmitter.model;

import java.util.Date;


public class Status {
  
  private String message;
  private Date timestamp;
  
  public Status() {
    // no args constructor for gson
  }
  
  public Status( String message, Date timestamp ) {
    this.message = message;
    this.timestamp = timestamp;
  }

  public String getMessage() {
    return message;
  }
  
  public Date getTimestamp() {
    return timestamp;
  }
  
}
