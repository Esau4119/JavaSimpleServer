package handler;

import com.google.gson.Gson;
import dao.PaymentDao;
import dto.BasePaymentDto;
import request.ParsedRequest;
import response.CustomHttpResponse;
import response.ResponseBuilder;

import java.util.List;

public class GetPaymentHandler implements BaseHandler {

  private static final Gson gson = new Gson();

  @Override
  public String handleRequest(ParsedRequest request) {
    List<BasePaymentDto> paymentInstance = PaymentDao.getInstance().getAll();
    return serverResponse(paymentInstance);
  }

  public static String serverResponse(List instance){
    CustomHttpResponse response = new ResponseBuilder()
            .setStatus("200 OK")
              .setVersion("HTTP/1.1")
               .setBody(gson.toJson(instance.get(0)))
                .build();
    return response.toString();
  }

}
