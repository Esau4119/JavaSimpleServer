package server;

import com.google.gson.Gson;
import dao.PaymentDao;
import dto.CashPayment;
import org.testng.Assert;
import org.testng.annotations.Test;

public class GetPaymentTest {

  private static final Gson gson = new Gson();

  @Test
  public void getPayment(){
    String value = String.valueOf(Math.random());
    CashPayment payment = new CashPayment(value, 123.3);

    String test1 = "GET /getPayment?id=" + value +" HTTP/1.1\n"
        + "User-Agent: made up browser\n"
        + "\n";
    PaymentDao dao = PaymentDao.getInstance();
    dao.put(payment);
    String response = Server.processRequest(test1);
    Assert.assertEquals(response, "HTTP/1.1 200 OK\n\n" + gson.toJson(payment));
  }

}
