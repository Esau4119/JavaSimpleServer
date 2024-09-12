package handler;

import com.google.gson.Gson;
import dao.PaymentDao;
import request.ParsedRequest;
import response.CustomHttpResponse;
import response.ResponseBuilder;

import java.util.List;

public class GetAllPaymentsHandler implements BaseHandler {

  private static final Gson gson = new Gson();

  @Override
  public String handleRequest(ParsedRequest request) {
    String allPayments = gson.toJson(PaymentDao.getInstance().getAll());
    return serverResponse(allPayments);
  }

  public static String serverResponse(String payments) {
    CustomHttpResponse response = new ResponseBuilder()
            .setStatus("200 OK")
            .setVersion("HTTP/1.1")
            .build();
    return response.toString() + "\n" + payments;
  }
}