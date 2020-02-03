package paulurl.shortener;

import javax.persistence.*;

@Entity
public class CustomUrl {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(unique = true, nullable = false)
  private int id;

  @Column(unique = true, nullable = false)
  private String route;

  @Column(nullable = false)
  private String originalUrl;

  @Column(nullable = false)
  private String description;

  public CustomUrl() {}

  public CustomUrl(int id, String route, String originalUrl, String description) {
    this.id = id;
    this.route = route;
    this.originalUrl = originalUrl;
    this.description = description;
  }

  public int getId() {
    return id;
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
