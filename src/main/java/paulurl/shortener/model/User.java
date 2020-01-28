package paulurl.shortener.model;

import java.util.UUID;

public class User {
  private final UUID userID;
  private final String username;
  private final String email;
  private final String password;
  private final String firstname;
  private final String lastname;

  public User(UUID userID, String username, String email, String password, String firstname, String lastname) {
    this.userID = userID;
    this.username = username;
    this.email = email;
    this.password = password;
    this.firstname = firstname;
    this.lastname = lastname;
  }

  public UUID getUserID() {
    return userID;
  }

  public String getUsername() {
    return username;
  }

  public String getEmail() {
    return email;
  }

  public String getPassword() {
    return password;
  }

  public String getFirstname() {
    return firstname;
  }

  public String getLastname() {
    return lastname;
  }
}