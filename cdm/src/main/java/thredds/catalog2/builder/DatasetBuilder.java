package thredds.catalog2.builder;

import thredds.catalog2.Dataset;
import thredds.catalog.ServiceType;

import java.util.List;

/**
 * _more_
 *
 * @author edavis
 * @since 4.0
 */
public interface DatasetBuilder extends DatasetNodeBuilder
{
  public AccessBuilder addAccess( ServiceBuilder service, String urlPath );

  public boolean isAccessible();
  public List<AccessBuilder> getAccessBuilders();
  public AccessBuilder getAccessBuilderByType( ServiceType type );

  public boolean isFinished();
  public Dataset finish();
}
