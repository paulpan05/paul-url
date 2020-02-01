package paulurl.shortener;


import javax.persistence.*;

@Entity
public class CustomUrlTable {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", unique = true, nullable = false)
  private int id;

  @Column(name = "route", unique = true, nullable = false)
  private String route;

  @Column(name = "original_url", nullable = false)
  private String originalUrl;

  @Column(name = "description", nullable = false)
  private String description;
}
