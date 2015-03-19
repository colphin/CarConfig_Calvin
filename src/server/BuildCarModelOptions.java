package server;

import model.Automotive;
import adapter.BuildAuto;

/**
 * Created by Calvin_Yin on 2/25/15.
 */
public class BuildCarModelOptions implements AutoServer{

    @Override
    public void buildAutoFromPropertyObj(Object propertyObj) {
        AutoServer auto = new BuildAuto();
        auto.buildAutoFromPropertyObj(propertyObj);
    }

}
