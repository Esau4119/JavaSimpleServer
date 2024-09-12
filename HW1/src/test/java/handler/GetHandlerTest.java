package handler;

import org.testng.Assert;
import org.testng.annotations.Test;
import request.CustomParser;
import request.ParsedRequest;

public class GetHandlerTest {

  @Test
  public void basicTest(){
    String test1 = "POST /makeCreditCardPayment HTTP/1.1\n"
        + "Accept-Language: en-us\n"
        + "Accept-Encoding: gzip, deflate\n"
        + "Connection: Keep-Alive"
        + "\n"
        + "";
    ParsedRequest request = CustomParser.parse(test1);
    Assert.assertEquals(request.getPath(), "/makeCreditCardPayment");
    var test = HandlerFactory.getHandler(request);
    Assert.assertEquals(test.getClass(), CreditCardPaymentHandler.class);
  }

  @Test
  public void makeCashPayment(){
    String test1 = "POST /makeCashPayment HTTP/1.1\n"
        + "Accept-Language: en-us\n"
        + "Accept-Encoding: gzip, deflate\n"
        + "Connection: Keep-Alive"
        + "\n"
        + "";
    ParsedRequest request = CustomParser.parse(test1);
    Assert.assertEquals(request.getPath(), "/makeCashPayment");
    var test = HandlerFactory.getHandler(request);
    Assert.assertEquals(test.getClass(), CashPaymentHandler.class);
  }

  @Test
  public void getPayment(){
    String test1 = "GET /getPayment HTTP/1.1\n"
        + "Accept-Language: en-us\n"
        + "Accept-Encoding: gzip, deflate\n"
        + "Connection: Keep-Alive"
        + "\n"
        + "";
    ParsedRequest request = CustomParser.parse(test1);
    Assert.assertEquals(request.getPath(), "/getPayment");
    var test = HandlerFactory.getHandler(request);
    // just testing if right handler is picked up
    Assert.assertEquals(test.getClass(), GetPaymentHandler.class);
  }
}
