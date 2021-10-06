package com.company.util.getHost;

import static com.company.util.constants.Server.LOCAL_HOST;
import static com.company.util.constants.Server.SERVER_HOST;

public class GetHostImpl implements GetHost {

    String local = LOCAL_HOST;
    String server = SERVER_HOST;

    @Override
    public String getHost() {
        return local;
    }

}
