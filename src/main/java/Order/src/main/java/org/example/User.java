package Order.src.main.java.org.example;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicLong;

public class User {

  private final Long id;
  private static final AtomicLong nextValue = new AtomicLong(1);


  public User() {
    this.id = nextValue.getAndIncrement();
  }

  public Long getId() {
    return id;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    User user = (User) o;
    return Objects.equals(id, user.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }

}
