package paulurl.shortener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://paulurl.com/", maxAge = 3600)
@RestController
@RequestMapping("v1/api")
public class UrlController {
  private final UrlService urlService;

  @Autowired
  public UrlController(UrlService urlService) {
    this.urlService = urlService;
  }

  @GetMapping
  public List<CustomUrl> getAllUrls() {
    return urlService.selectAllUrls();
  }

  @GetMapping("{route}")
  public CustomUrl getSingleUrl(@PathVariable String route) {
    return urlService.selectSingleUrl(route);
  }

  @PostMapping
  public void insertUrl(@RequestBody CustomUrl customUrl) {
    urlService.insertUrl(customUrl);
  }

  @PutMapping
  public void updateUrl(@RequestBody CustomUrl customUrl) {
    urlService.updateUrl(customUrl);
  }

  @DeleteMapping("{route}")
  public void removeUrl(@PathVariable String route) {
    urlService.removeUrl(route);
  }
}
