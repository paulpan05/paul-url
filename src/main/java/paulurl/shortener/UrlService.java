package paulurl.shortener;

import org.apache.commons.validator.routines.UrlValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UrlService {
  private final UrlDataAccessObject urlDataAccessObject;

  @Autowired
  public UrlService(UrlDataAccessObject urlDataAccessObject) {
    this.urlDataAccessObject = urlDataAccessObject;
  }

  List<CustomUrl> selectAllUrls() {
    return urlDataAccessObject.selectAllUrls();
  }

  void insertUrl(CustomUrl customUrl) {
    if (!urlDataAccessObject.isRouteTaken(customUrl.getRoute())) {
      urlDataAccessObject.insertUrl(
              customUrl.getRoute(),
              customUrl.getOriginalUrl(),
              customUrl.getDescription()
      );
    } else {
      throw new IllegalStateException("URL is already taken");
    }
  }

  public void updateUrl(CustomUrl customUrl) {
    Optional.ofNullable(customUrl.getOriginalUrl())
            .ifPresent(originalUrl -> {
              boolean isValidUrl = new UrlValidator().isValid(originalUrl);
              if (isValidUrl) {
                urlDataAccessObject.updateOriginalUrl(customUrl.getRoute(), originalUrl);
              } else {
                throw new IllegalStateException("URL invalid in update");
              }
            });
    Optional.ofNullable(customUrl.getDescription())
            .ifPresent(description ->
              urlDataAccessObject.updateUrlDescription(customUrl.getRoute(), description)
            );
  }

  void removeUrl(String route) {
    urlDataAccessObject.removeUrl(route);
  }
}
