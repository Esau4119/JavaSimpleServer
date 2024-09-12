package request;

public class CustomParser {

  public static ParsedRequest methodSetter(ParsedRequest a, String request){
    a.setMethod(request.split(" ")[0]);
    return a;

  }

  public static ParsedRequest pathAndParam(ParsedRequest reqObject, String request){
    String path = request;

    if ( request.contains("?")){

      // path for a request with parameters

      path = path.split(" ")[1];
      path = path.split("\\?")[0];
      reqObject.setPath(path);


      // setting the parameters of a request
      paramSetter(reqObject, request);


    }else{

      path = path.split(" ")[1];
      path = path.split(" ")[0];

      reqObject.setPath(path);
    }

    return reqObject;
  }

  public static void paramSetter(ParsedRequest a, String request){
    String paraKey = request;
    String paraVal = request;


    // pulling out the parameter key out of the request

    paraKey = paraKey.split("\\?")[1];
    paraKey = paraKey.split("=")[0];


    // getting value out of request

    paraVal = paraVal.split("\\?")[1];
    paraVal = paraVal.split("=")[1];
    paraVal = paraVal.split(" ")[0];

    // setting key and value to our request object

    a.setQueryParam(paraKey, paraVal);

  }

  public static ParsedRequest bodySetter(ParsedRequest reqObject, String request){

    String body = request;
    body = body.substring(body.lastIndexOf("\n")+1);

    reqObject.setBody(body);

    return reqObject;
  }
  public static ParsedRequest parse(String request){
    ParsedRequest parsedObject = new ParsedRequest();

    methodSetter(parsedObject, request);

    pathAndParam(parsedObject,request);

    bodySetter(parsedObject, request);


    return parsedObject;
  }
}
