package paulurl.shortener.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import paulurl.shortener.model.CustomUrl;

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
            "original_url " +
            "FROM custom_url";
    return jdbcTemplate.query(sql, mapCustomUrlFromDB());
  }

  int updateUrlRoute(String originalRoute, String modifiedRoute) {
    String sql = "" +
            "UPDATE custom_url " +
            "SET route = ? " +
            "WHERE route = ?";
    return jdbcTemplate.update(sql, modifiedRoute, originalRoute);
  }

  int updateOriginalUrl(String route, String curOriginal) {
    String sql = "" +
            "UPDATE custom_url " +
            "SET original_url = ? " +
            "WHERE route = ?";
    return jdbcTemplate.update(sql, curOriginal, route);
  }

  @SuppressWarnings("ConstantConditions")
  boolean isRouteTaken(String route) {
    String sql = "" +
            "SELECT EXISTS (" +
            "SELECT 1 " +
            "FROM custom_url " +
            "WHERE route = ?" +
            ")";
    return jdbcTemplate.queryForObject(
            sql,
            new Object[]{route},
            (resultSet, i) -> resultSet.getBoolean(1)
    );
  }

  int insertUrl(String route, String originalUrl) {
    String sql = "" +
            "INSERT INTO custom_url (" +
            "route, " +
            "original_url) " +
            "VALUES (?, ?)";
    return jdbcTemplate.update(sql, route, originalUrl);
  }

  int removeUrl(String route) {
    String sql = "" +
            "DELETE FROM custom_url " +
            "WHERE route = ?";
    return jdbcTemplate.update(sql, route);
  }

  private RowMapper<CustomUrl> mapCustomUrlFromDB() {
    return (resultSet, i) -> {
      Integer id = Integer.valueOf(resultSet.getString("id"));
      String route = resultSet.getString("route");
      String originalUrl = resultSet.getString("original_url");
      return new CustomUrl(id, route, originalUrl);
    };
  }
}
