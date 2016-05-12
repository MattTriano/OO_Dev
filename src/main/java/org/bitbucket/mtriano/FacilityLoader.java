package org.bitbucket.mtriano;

import java.util.ArrayList;

/**
 * Facility Loader Interface
 */
public interface FacilityLoader {

    void loadNetworkFromFile(String filePath);
    ArrayList<Facility> getFacilities();
}
