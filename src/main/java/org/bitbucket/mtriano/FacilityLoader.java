package org.bitbucket.mtriano;

import java.util.ArrayList;

/**
 * Created by Matt on 5/3/2016.
 */
public interface FacilityLoader {

    void loadNetworkFromFile(String filePath);
    ArrayList<Facility> getFacilities();
}
