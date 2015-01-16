package io.megrez.universe.request;

/**
 * Created by megrez on 15/1/16.
 */
public abstract class UniverseParam<T> implements IUniverseParam {
  protected String key;
  protected T value;

  @Override
  public String getKey() {
    return key;
  }
}
