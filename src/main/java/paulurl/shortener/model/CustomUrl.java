package paulurl.shortener.model;

import java.util.UUID;

public class CustomUrl {
  private final String route;
  private final String originalURL;
  private final UUID userID;

  public CustomUrl(String route, String originalURL, UUID userID) {
    this.route = route;
    this.originalURL = originalURL;
    this.userID = userID;
  }

  public String getRoute() {
    return route;
  }

  public String getOriginalURL() {
    return originalURL;
  }

  public UUID getUserID() {
    return userID;
  }
}