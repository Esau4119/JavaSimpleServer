package server;

import com.google.gson.Gson;
import dto.CashPayment;
import org.testng.Assert;
import org.testng.annotations.Test;

public class NotFoundTest {

  private static final Gson gson = new Gson();

  @Test
  public void do404(){
    double value = Math.random();
    CashPayment cashPayment = new CashPayment(value);
    String test1 = "GET /makeCreditCardPayment HTTP/1.1\n"
        + "\n"
        + gson.toJson(cashPayment);
    String response = Server.processRequest(test1);
    Assert.assertEquals(response, "HTTP/1.1 404 Not Found\n");
  }

  @Test
  public void do404Random(){
    double value = Math.random();
    CashPayment cashPayment = new CashPayment(value);
    String test1 = "GET /value HTTP/1.1\n"
        + "\n"
        + gson.toJson(cashPayment);
    String response = Server.processRequest(test1);
    Assert.assertEquals(response, "HTTP/1.1 404 Not Found\n");
  }
}
