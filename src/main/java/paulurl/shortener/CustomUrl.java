package paulurl.shortener;

public class CustomUrl {
  private final String route;
  private final String originalUrl;
  private final String description;

  public CustomUrl(String route, String originalUrl, String description) {
    this.route = route;
    this.originalUrl = originalUrl;
    this.description = description;
  }

  public String getRoute() {
    return route;
  }

  public String getOriginalUrl() {
    return originalUrl;
  }

  public String getDescription() {
    return description;
  }
}
