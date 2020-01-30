package paulurl.shortener.model;

public class CustomUrl {
  private final Integer id;
  private final String route;
  private final String originalUrl;

  public CustomUrl(Integer id, String route, String originalUrl) {
    this.id = id;
    this.route = route;
    this.originalUrl = originalUrl;
  }

  public Integer getId() {
    return id;
  }

  public String getRoute() {
    return route;
  }

  public String getOriginalUrl() {
    return originalUrl;
  }
}
