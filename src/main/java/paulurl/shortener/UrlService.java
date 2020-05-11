package paulurl.shortener;

import org.apache.commons.validator.routines.UrlValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class UrlService {
    private final UrlRepository urlRepository;

    @Autowired
    public UrlService(UrlRepository urlRepository) {
        this.urlRepository = urlRepository;
    }

    List<CustomUrl> selectAllUrls() {
        return urlRepository.selectAllUrls();
    }

    CustomUrl selectSingleUrl(String route) {
        try {
            return urlRepository.selectSingleUrl(route);
        } catch (DataAccessException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "URL route does not exist.");
        }
    }

    void insertUrl(CustomUrl customUrl) {
        String route = customUrl.getRoute();
        String originalUrl = customUrl.getOriginalUrl();
        String description = customUrl.getDescription();
        if (route == null || originalUrl == null || description == null) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Request body invalid, must be in the form: " +
                            "{route: string, originalUrl: string, description: string}."
            );
        } else if (!urlRepository.isRouteTaken(route)) {
            urlRepository.insertUrl(
                    route,
                    originalUrl,
                    description
            );
        } else {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "URL is already taken.");
        }
    }

    public void updateUrl(CustomUrl customUrl) {
        Optional.ofNullable(customUrl.getOriginalUrl())
                .ifPresent(originalUrl -> {
                    boolean isValidUrl = new UrlValidator().isValid(originalUrl);
                    if (isValidUrl) {
                        urlRepository.updateOriginalUrl(customUrl.getRoute(), originalUrl);
                    } else {
                        throw new ResponseStatusException(
                                HttpStatus.BAD_REQUEST,
                                "URL invalid in update."
                        );
                    }
                });
        Optional.ofNullable(customUrl.getDescription())
                .ifPresent(description ->
                        urlRepository.updateUrlDescription(customUrl.getRoute(), description)
                );
    }

    void removeUrl(String route) {
        int successStatus = urlRepository.removeUrl(route);
        if (successStatus == 0) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "URL to delete does not exist."
            );
        }
    }
}
