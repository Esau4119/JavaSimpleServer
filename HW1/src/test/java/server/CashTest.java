package server;

import com.google.gson.Gson;
import dao.PaymentDao;
import dto.BasePaymentDto;
import dto.CashPayment;
import java.util.List;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CashTest {

  private static final Gson gson = new Gson();

  @Test
  public void makeCashPayment(){
    double value = Math.random();
    CashPayment cashPayment = new CashPayment(value);
    String test1 = "POST /makeCashPayment HTTP/1.1\n"
        + "User-Agent: made up browser\n"
        + "\n"
        + gson.toJson(cashPayment);
    String response = Server.processRequest(test1);
    Assert.assertEquals(response, "HTTP/1.1 200 OK\n");
    List<BasePaymentDto> paymentDtoList = PaymentDao.getInstance().getAll();
    Assert.assertEquals(paymentDtoList.size(), 1);
    Assert.assertEquals(paymentDtoList.get(0).amount, value);
    Assert.assertNotNull(paymentDtoList.get(0).getUniqueId());
  }

}
