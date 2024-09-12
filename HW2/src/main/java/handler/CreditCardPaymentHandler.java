package handler;

import com.google.gson.Gson;
import dao.PaymentDao;
import dto.CashPayment;
import dto.CreditCardPayment;
import request.ParsedRequest;
import response.CustomHttpResponse;
import response.ResponseBuilder;

public class CreditCardPaymentHandler  implements BaseHandler{

  private static final Gson gson = new Gson();

  // Only Post
  @Override
  public String handleRequest(ParsedRequest request) {
    var payment = gson.fromJson(request.getBody(), CreditCardPayment.class);
    PaymentDao.getInstance().put(payment);
    return serverResponse();
  }

  public static String serverResponse(){
    CustomHttpResponse serverResponse = new ResponseBuilder()
            .setVersion("HTTP/1.1")
            .setStatus("200 OK")
            .build();
    return serverResponse.toString();
  }

}
