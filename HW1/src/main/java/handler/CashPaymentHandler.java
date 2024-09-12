package handler;

import com.google.gson.Gson;
import request.ParsedRequest;
import response.CustomHttpResponse;
import response.ResponseBuilder;
import dto.CashPayment;
import dto.BasePaymentDto;
import dao.PaymentDao;
public class CashPaymentHandler  implements BaseHandler{

  private static final Gson gson = new Gson();

  // Only Post
  @Override
  public String handleRequest(ParsedRequest request) {
    PaymentDao paymentInstance = PaymentDao.getInstance();
    BasePaymentDto baseDTO = gson.fromJson(request.getBody(),CashPayment.class);
    paymentInstance.put(baseDTO.setUniqueId(String.valueOf(Math.random())));
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


