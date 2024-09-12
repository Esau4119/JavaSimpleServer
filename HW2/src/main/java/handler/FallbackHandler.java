package handler;

import request.ParsedRequest;
import response.CustomHttpResponse;
import response.ResponseBuilder;

public class FallbackHandler implements BaseHandler {

  @Override
  public String handleRequest(ParsedRequest request) {
    return serverResponse();
  }

  public static String serverResponse(){
    CustomHttpResponse serverResponse = new ResponseBuilder()
            .setVersion("HTTP/1.1")
            .setStatus("404 Not Found")
            .build();
    return serverResponse.toString();
  }
}
