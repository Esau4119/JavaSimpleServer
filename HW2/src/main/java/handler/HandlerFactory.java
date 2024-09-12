package handler;

import com.google.gson.Gson;
import dao.PaymentDao;
import dto.CashPayment;
import dto.CreditCardPayment;
import request.ParsedRequest;

public class HandlerFactory {
  public static BaseHandler getHandler(ParsedRequest request) {
    if (request.getMethod().equals("GET")){
      if (request.getPath().equals("/getAllPayments")) {
        return new GetAllPaymentsHandler();
      }
      else {
        return new GetPaymentHandler();

      }
    }else if (request.getMethod().equals("POST")) {
      if (request.getPath().equals("/makeCashPayment")) {
        return new CashPaymentHandler();
      }
      else if (request.getPath().equals("/makeCreditCardPayment")) {
        return new CreditCardPaymentHandler();
      }
    }
    return null;

  }

}
