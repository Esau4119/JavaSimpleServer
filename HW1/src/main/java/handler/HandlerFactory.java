package handler;

import request.ParsedRequest;

public class HandlerFactory {
  public static BaseHandler creditPayment(ParsedRequest request){
    if (request.getMethod().equals("GET")){
      return defaultHandler();
    }else{
      return new CreditCardPaymentHandler();
    }

  }
  public static CashPaymentHandler cashPayment(){
    return new CashPaymentHandler();
  }
  public static GetPaymentHandler getPayment(){
    return new GetPaymentHandler();
  }
  public static FallbackHandler defaultHandler(){
    return new FallbackHandler();
  }



  public static BaseHandler getHandler(ParsedRequest request) {

    if (request.getPath().equals("/makeCreditCardPayment")) {
      return creditPayment(request);
    }
    else if (request.getPath().equals("/makeCashPayment")) {
      return cashPayment();
    }
    else if (request.getPath().equals("/getPayment")) {
      return getPayment();
    }
    else {
      return defaultHandler();
    }
  }
}