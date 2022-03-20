package blog.explained.so;

import com.fasterxml.jackson.annotation.JsonView;

@AuthorityToJsonViewMappings({
  @AuthorityToJsonViewMapping(authority = "USER", view = Item.WithoutOwnerView.class),
  @AuthorityToJsonViewMapping(authority = "ADMIN", view = Item.WithOwnerView.class)
})
public class Item {

  @JsonView(WithoutOwnerView.class)
  private int id;

  @JsonView(WithoutOwnerView.class)
  private String name;

  @JsonView(WithOwnerView.class)
  private String owner;

  public interface WithoutOwnerView {};
  public interface WithOwnerView extends WithoutOwnerView {};

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getOwner() {
    return owner;
  }

  public void setOwner(String owner) {
    this.owner = owner;
  }
}