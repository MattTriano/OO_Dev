package org.bitbucket.mtriano;

import org.bitbucket.mtriano.Order.Order;
import java.util.ArrayList;

/**
 * Created by Matt on 5/18/2016.
 */
public interface OrderLoader {

    void loadOrdersFromFile(String filePath);
    ArrayList<Order> getOrderList();
}
