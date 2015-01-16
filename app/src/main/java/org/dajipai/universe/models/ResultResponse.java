package org.dajipai.universe.models;

import io.megrez.universe.response.JSONResponse;

/**
 * Created by megrez on 15/1/16.
 */
public class ResultResponse<T> extends JSONResponse<T> {
  public String code;
  public String message;

  public String getCode() {
    return code;
  }

  public String getMessage() {
    return message;
  }
}
