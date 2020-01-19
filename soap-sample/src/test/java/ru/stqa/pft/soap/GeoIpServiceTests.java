package ru.stqa.pft.soap;

import net.webservicex.GeoIPService;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class GeoIpServiceTests {

    @Test
    public void testMyIp() {
        String ipLocation = new GeoIPService().getGeoIPServiceSoap12().getIpLocation("5.18.245.218");
        assertTrue(ipLocation.contains("RU"));
    }
}
