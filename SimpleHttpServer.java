package MITM;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.Headers;

public class SimpleHttpServer {

  public static void main(String[] args) throws Exception {
    HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);
    server.createContext("/", new InfoHandler());
    server.createContext("/get", new GetHandler());
    server.setExecutor(null); // creates a default executor
    server.start();
    System.out.println("Server listening on port 8080");
    System.out.println("");
    
  }

  static class InfoHandler implements HttpHandler {
    public void handle(HttpExchange t) throws IOException {
      	String requestMethod = t.getRequestMethod();
      	OutputStream responseBody;
      	
        if (requestMethod.equalsIgnoreCase("GET")) {
          Headers responseHeaders = t.getResponseHeaders();
          responseHeaders.set("Content-Type", "text/plain");
          t.sendResponseHeaders(200, 0);
          responseBody = t.getResponseBody();
          Headers requestHeaders = t.getRequestHeaders();
          Set<String> keySet = requestHeaders.keySet();
          Iterator<String> iter = keySet.iterator();
          String requestProtocol = t.getProtocol();
          System.out.println("Request Method: " + requestMethod);
          System.out.println("Request Protocol: " + requestProtocol);
          
          while (iter.hasNext()) {
            String key = iter.next();
            System.out.print(key + ": ");
            List<String> values = requestHeaders.get(key);
            System.out.println(values);
            String s = key + " = " + values.toString() + "\n";
            responseBody.write(s.getBytes());
          }
          
          System.out.println("");
          responseBody.close();
        }
    }
  }

  static class GetHandler implements HttpHandler {
    public void handle(HttpExchange t) throws IOException {
      
      // add the required response header for a PDF file
      Headers h = t.getResponseHeaders();
      h.add("Content-Type", "application/pdf");

      // supply a PDF
      File file = new File ("/Users/tschmidt/Downloads/mct-platform-1.7-user-guide.pdf");
      byte [] bytearray  = new byte [(int)file.length()];
      FileInputStream fis = new FileInputStream(file);
      BufferedInputStream bis = new BufferedInputStream(fis);
      bis.read(bytearray, 0, bytearray.length);

      // send the response.
      t.sendResponseHeaders(200, file.length());
      OutputStream os = t.getResponseBody();
      os.write(bytearray,0,bytearray.length);
      os.close();
      bis.close();
    }
  }
}
