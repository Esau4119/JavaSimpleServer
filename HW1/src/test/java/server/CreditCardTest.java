package server;

import com.google.gson.Gson;
import dao.PaymentDao;
import dto.BasePaymentDto;
import dto.CreditCardPayment;
import java.util.List;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CreditCardTest {

  private static final Gson gson = new Gson();

  @Test
  public void makeCreditCardPayment(){
    double value = Math.random();
    String number = String.valueOf(Math.random());
    String securityCode = String.valueOf(Math.random());
    CreditCardPayment payment = new CreditCardPayment(value, number, securityCode);
    String test1 = "POST /makeCreditCardPayment HTTP/1.1\n"
        + "Host: test\n"
        + "Accept-Language: en-us\n"
        + "Accept-Encoding: gzip, deflate\n"
        + "Connection: Keep-Alive\n"
        + "\n"
        + gson.toJson(payment);
    String response = Server.processRequest(test1);
    Assert.assertEquals(response, "HTTP/1.1 200 OK\n");
    List<BasePaymentDto> paymentDtoList = PaymentDao.getInstance().getAll();
    Assert.assertEquals(paymentDtoList.size(), 1);
    Assert.assertEquals(paymentDtoList.get(0).amount, value);
    Assert.assertNotNull(paymentDtoList.get(0).getUniqueId());
  }

}
