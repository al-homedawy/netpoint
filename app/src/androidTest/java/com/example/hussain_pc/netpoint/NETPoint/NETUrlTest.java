package com.example.hussain_pc.netpoint.NETPoint;

import com.example.netpoint.NETUrl;

import junit.framework.TestCase;

/**
 * Created by Hussain-PC on 2016-06-24.
 */
public class NETUrlTest extends TestCase {
    NETUrl netUrl;

    public void setUp() throws Exception {
        super.setUp();

        netUrl = new NETUrl("www.test.com/test.php", false);

    }

    public void testEmpty() throws Exception {
        assertEquals("www.test.com/test.php", netUrl.getConnectionString());
    }

    public void testParameters () throws Exception {
        netUrl.addParameter("A", "1");
        netUrl.addParameter("B", "2");
        netUrl.addParameter("C", "3");

        assertEquals("www.test.com/test.php?A=1&B=2&C=3", netUrl.getConnectionString());
    }

    public void testParametersWithNull () throws Exception {
        netUrl.addParameter("A", "1");
        netUrl.addParameter("B", "2");
        netUrl.addParameter("C", null);

        assertEquals("www.test.com/test.php?A=1&B=2", netUrl.getConnectionString());
    }
}