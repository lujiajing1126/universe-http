package org.dajipai.universe.models;

/**
 * Created by megrez on 15/1/16.
 */
public class SigninResponse extends ResultResponse<SigninResponse>  {
  private String user_id;
  private String name;

  public String getName() {
    return name;
  }
}
