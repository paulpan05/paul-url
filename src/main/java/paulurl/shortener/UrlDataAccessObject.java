package paulurl.shortener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UrlDataAccessObject {
  private final JdbcTemplate jdbcTemplate;

  @Autowired
  public UrlDataAccessObject(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  List<CustomUrl> selectAllUrls() {
    String sql = "" +
            "SELECT " +
            "id, " +
            "route, " +
            "original_url, " +
            "description " +
            "FROM custom_url_table";
    return jdbcTemplate.query(sql, mapCustomUrlFromDB());
  }

  int updateOriginalUrl(String route, String curOriginal) {
    String sql = "" +
            "UPDATE custom_url_table " +
            "SET original_url = ? " +
            "WHERE route = ?";
    return jdbcTemplate.update(sql, curOriginal, route);
  }

  int updateUrlDescription(String route, String description) {
    String sql = "" +
            "UPDATE custom_url_table " +
            "SET description = ? " +
            "WHERE route = ?";
    return jdbcTemplate.update(sql, description, route);
  }

  @SuppressWarnings("ConstantConditions")
  boolean isRouteTaken(String route) {
    String sql = "" +
            "SELECT EXISTS (" +
            "SELECT 1 " +
            "FROM custom_url_table " +
            "WHERE route = ?" +
            ")";
    return jdbcTemplate.queryForObject(
            sql,
            new Object[]{route},
            (resultSet, i) -> resultSet.getBoolean(1)
    );
  }

  int insertUrl(String route, String originalUrl, String description) {
    String sql = "" +
            "INSERT INTO custom_url_table (" +
            "route, " +
            "original_url, " +
            "description) " +
            "VALUES (?, ?, ?)";
    return jdbcTemplate.update(sql, route, originalUrl, description);
  }

  int removeUrl(String route) {
    String sql = "" +
            "DELETE FROM custom_url_table " +
            "WHERE route = ?";
    return jdbcTemplate.update(sql, route);
  }

  private RowMapper<CustomUrl> mapCustomUrlFromDB() {
    return (resultSet, i) -> {
      String route = resultSet.getString("route");
      String originalUrl = resultSet.getString("original_url");
      String description = resultSet.getString("description");
      return new CustomUrl(route, originalUrl, description);
    };
  }
}
