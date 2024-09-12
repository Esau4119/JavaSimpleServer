package response;

import java.util.Map;
import java.util.Map.Entry;


public class CustomHttpResponse {
  public final Map<String,String> headers;
  public final String status;
  public final String version;
  public final String body;

  public CustomHttpResponse(Map<String, String> headers, String status, String version,
      String body) {
    this.headers = headers;
    this.status = status;
    this.version = version;
    this.body = body;
  }

  public String toString(){
    String response = this.version + " " + this.status + "\n" ;
    String key = "";
    String val = "";
    for (Map.Entry<String, String> entry : this.headers.entrySet()) {

       key = entry.getKey();
       val = entry.getValue();
    }
    if (this.body != null) {

      // for requests that contain bodies.
      response = this.version + " " + this.status + "\n" +key + ": " +val+"\n\n" + this.body;

        if (key == ""){

          // for requests with no parameters but it contains a body
          response = this.version + " " + this.status +"\n\n" + this.body;

        }
    }

    return response;
  }
}
