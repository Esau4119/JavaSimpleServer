package handler;

import com.google.gson.Gson;
import dao.PaymentDao;
import dto.CashPayment;
import request.ParsedRequest;
import response.CustomHttpResponse;
import response.ResponseBuilder;

public class GetPaymentHandler implements BaseHandler {

  private static final Gson gson = new Gson();

  @Override
  public String handleRequest(ParsedRequest request) {
    var payment = PaymentDao.getInstance().get(request.getQueryParam("id"));
    payment.setUniqueId(request.getQueryParam("id"));
    String body = gson.toJson(payment);
    return serverResponse(body);
  }
    public static String serverResponse(String body){

        CustomHttpResponse serverResponse = new ResponseBuilder()
                .setVersion("HTTP/1.1")
                .setStatus("200 OK")
                .setBody(body)
                .build();
        return serverResponse.toString();
    }
}
