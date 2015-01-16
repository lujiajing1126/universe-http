package io.megrez.universe.request;

/**
 * Created by megrez on 15/1/16.
 */
public class UniverseStringParam extends UniverseParam<String> {
  public UniverseStringParam(String key,String value) {
    this.key = key;
    this.value = value;
  }
  @Override
  public String getValue() {
    return value;
  }
}
