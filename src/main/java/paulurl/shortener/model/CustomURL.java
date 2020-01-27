package paulurl.shortener.model;

import java.util.UUID;

public class CustomURL {
  private final String route;
  private final String originalURL;
  private final UUID userID;

  public CustomURL(String route, String originalURL, UUID userID) {
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
