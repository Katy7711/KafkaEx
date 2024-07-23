package Order.src.main.java.org.example;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicLong;

public class Order {

  private final Long id;
  private String name;
  private double price;
  private int amount;
  private Status status;
  private User user;

  private static final AtomicLong nextValue = new AtomicLong(1);


  public Order() {
    this.id = nextValue.getAndIncrement();
  }

  public Order(Long id, String name, double price, int amount, Status status, User user) {
    this.id = nextValue.getAndIncrement();
    this.name = name;
    this.price = price;
    this.amount = amount;
    this.status = status;
    this.user = user;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public double getPrice() {
    return price;
  }

  public void setPrice(double price) {
    this.price = price;
  }

  public int getAmount() {
    return amount;
  }

  public void setAmount(int amount) {
    this.amount = amount;
  }

  public Status getStatus() {
    return status;
  }

  public void setStatus(Status status) {
    this.status = status;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public Long getId() {
    return id;
  }

  public User getUser() {
    return user;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Order order = (Order) o;
    return Double.compare(order.price, price) == 0 && amount == order.amount
        && Objects.equals(id, order.id) && Objects.equals(name, order.name)
        && status == order.status && Objects.equals(user, order.user);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, price, amount, status, user);
  }

  @Override
  public String toString() {
    return "Order{" +
        "id=" + id +
        ", name='" + name + '\'' +
        ", price=" + price +
        ", amount=" + amount +
        ", status=" + status +
        ", user=" + user +
        '}';
  }
}
