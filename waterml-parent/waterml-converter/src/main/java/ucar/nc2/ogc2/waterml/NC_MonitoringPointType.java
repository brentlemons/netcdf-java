package ucar.nc2.ogc2.waterml;

import net.opengis.gml.x32.CodeWithAuthorityType;
import net.opengis.gml.x32.StringOrRefType;
import net.opengis.samplingSpatial.x20.ShapeType;
import net.opengis.waterml.x20.MonitoringPointType;
import ucar.nc2.ft.StationTimeSeriesFeature;
import ucar.nc2.ogc2.gml.NC_CodeWithAuthorityType;
import ucar.nc2.ogc2.gml.NC_StringOrRefType;
import ucar.nc2.ogc2.spatialsampling.NC_Shape;

/**
 * Created by cwardgar on 2014/02/26.
 */
public abstract class NC_MonitoringPointType {
    // wml2:Collection/wml2:observationMember/om:OM_Observation/om:featureOfInterest/sam:SF_SamplingFeatureType
    public static MonitoringPointType createSfSamplingFeatureType(StationTimeSeriesFeature stationFeat) {
        MonitoringPointType sfSamplingFeatureType = MonitoringPointType.Factory.newInstance();

        // gml:id
        String id = generateId();
        sfSamplingFeatureType.setId(id);

        // gml:identifier
        CodeWithAuthorityType identifier = NC_CodeWithAuthorityType.createIdentifier(stationFeat);
        sfSamplingFeatureType.setIdentifier(identifier);

        // gml:description
        StringOrRefType description = NC_StringOrRefType.createDescription(stationFeat);
        if (description.getStringValue() != null && !description.getStringValue().isEmpty()) {
            sfSamplingFeatureType.setDescription(description);
        }

        // sams:shape
        ShapeType shape = NC_Shape.createShape(stationFeat);
        sfSamplingFeatureType.setShape(shape);

        return sfSamplingFeatureType;
    }

    public static MonitoringPointType initSfSamplingFeatureType(
            MonitoringPointType sfSamplingFeatureType, StationTimeSeriesFeature stationFeat) {
        // gml:id
        String id = generateId();
        sfSamplingFeatureType.setId(id);

        // gml:identifier
        NC_CodeWithAuthorityType.initIdentifier(sfSamplingFeatureType.addNewIdentifier(), stationFeat);

        // gml:description
        NC_StringOrRefType.initDescription(sfSamplingFeatureType.addNewDescription(), stationFeat);
        if (sfSamplingFeatureType.getDescription().getStringValue() == null ||
                sfSamplingFeatureType.getDescription().getStringValue().isEmpty()) {
            sfSamplingFeatureType.unsetDescription();
        }

        // sams:shape
        NC_Shape.initShape(sfSamplingFeatureType.addNewShape(), stationFeat);

        return sfSamplingFeatureType;
    }

    private static int numIds = 0;

    private static String generateId() {
        return MonitoringPointType.class.getSimpleName() + "." + ++numIds;
    }

    private NC_MonitoringPointType() { }
}
