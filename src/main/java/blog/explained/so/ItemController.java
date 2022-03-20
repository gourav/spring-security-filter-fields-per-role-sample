package blog.explained.so;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ItemController {

  @GetMapping
  public ResponseEntity<Item> getItem() {

    Item item = new Item();
    item.setId(1);
    item.setName("item");
    item.setOwner("_gaurav");

    return ResponseEntity.ok(item);
  }

}
