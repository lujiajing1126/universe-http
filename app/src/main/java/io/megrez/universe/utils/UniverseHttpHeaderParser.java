package io.megrez.universe.utils;

import org.apache.http.protocol.HTTP;

import java.util.Map;

/**
 * Created by megrez on 15/1/16.
 */
public class UniverseHttpHeaderParser {

  /**
   * Returns the charset specified in the Content-Type of this header,
   * or the HTTP default (UTF-8) if none can be found.
   */
  public static String parseCharset(Map<String, String> headers) {
    String contentType = headers.get(HTTP.CONTENT_TYPE);
    if (contentType != null) {
      String[] params = contentType.split(";");
      for (int i = 1; i < params.length; i++) {
        String[] pair = params[i].trim().split("=");
        if (pair.length == 2) {
          if (pair[0].equals("charset")) {
            return pair[1];
          }
        }
      }
    }

    return HTTP.UTF_8;
  }
}
