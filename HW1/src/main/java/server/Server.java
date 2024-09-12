package server;

import handler.BaseHandler;
import handler.HandlerFactory;
import request.CustomParser;
import request.ParsedRequest;

public class Server {

    public static ParsedRequest getParsedRequest(String request){
        return CustomParser.parse(request);
    }


    public static String processRequest(String requestString){

    ParsedRequest request = getParsedRequest(requestString);
    BaseHandler gethandler = HandlerFactory.getHandler(request);
    return gethandler.handleRequest(request);

  }

}