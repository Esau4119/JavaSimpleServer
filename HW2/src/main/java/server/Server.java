package server;

import com.google.gson.Gson;
import dao.PaymentDao;
import dto.CashPayment;
import dto.CreditCardPayment;
import handler.BaseHandler;
import handler.HandlerFactory;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

import org.bson.io.BsonOutput;
import request.CustomParser;
import request.ParsedRequest;
import response.CustomHttpResponse;
import response.ResponseBuilder;

public class Server {
  private static final Gson gson = new Gson();
  public static void main(String[] args) {
    ServerSocket ding;
    Socket dong = null;
    try {
      ding = new ServerSocket(1299);
      System.out.println("Opened socket " + 1299);
      while (true) {
        // keeps listening for new clients, one at a time
        try {
          dong = ding.accept(); // waits for client here
        } catch (IOException e) {
          System.out.println("Error opening socket");
          System.exit(1);
        }

        InputStream stream = dong.getInputStream();
        byte[] b = new byte[1024*20];
        stream.read(b);
        String input = new String(b).trim();
        System.out.println(input);

        BufferedOutputStream out = new BufferedOutputStream(dong.getOutputStream());
        PrintWriter writer = new PrintWriter(out, true);  // char output to the client

        // HTTP Response
        if(!input.isEmpty()){
          writer.println(processRequest(input));
        }else{
          writer.println("HTTP/1.1 200 OK");
          writer.println("Server: TEST");
          writer.println("Connection: close");
          writer.println("Content-type: text/html");
          writer.println("");
        }

        dong.close();
      }
    } catch (IOException e) {
      System.out.println("Error opening socket");
      System.exit(1);
    }
  }
  public static ParsedRequest getParsedRequest(String request){
    return CustomParser.parse(request);
  }
  public static String processRequest(String requestString){
    ParsedRequest request = getParsedRequest(requestString);
    BaseHandler getHandler = HandlerFactory.getHandler(request);
    return getHandler.handleRequest(request);
  }



}
