package com.github.zugaldia.noaa;

import com.github.zugaldia.noaa.models.ElementModel;
import com.github.zugaldia.noaa.models.ParametersData;
import com.github.zugaldia.noaa.models.UnsummarizedResponse;
import com.github.zugaldia.noaa.models.elements.HazardConditions;
import com.github.zugaldia.noaa.models.elements.HazardConditionsData;
import com.github.zugaldia.noaa.models.elements.HazardsData;
import okhttp3.HttpUrl;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import retrofit2.Response;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by antonio on 6/26/16.
 */
public class HazardTest {

    private MockWebServer server;
    private HttpUrl mockUrl;

    @Before
    public void setUp() throws IOException {
        server = new MockWebServer();

        // This fixture has some non-empty <hazard-conditions> elements
        byte[] content = Files.readAllBytes(Paths.get("src/test/fixtures/response-summarized-06.xml"));
        String body = new String(content, StandardCharsets.UTF_8);
        server.enqueue(new MockResponse().setBody(body));

        server.start();
        mockUrl = server.url("");
    }

    @After
    public void tearDown() throws IOException {
        server.shutdown();
    }

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void testElementWwa() throws IOException {
        NdfdUnsummarized client = new NdfdUnsummarized.Builder().build();
        client.setBaseUrl(mockUrl.toString());
        Response<UnsummarizedResponse> response = client.executeCall();

        ParametersData parameters = response.body().getData().getParameter("point11");
        HazardsData hazards = (HazardsData) parameters.getElement(ElementModel.WWA);
        assertTrue(hazards.getName().equals("Watches, Warnings, and Advisories"));
        assertTrue(hazards.getTimeLayout().equals("k-p33h-n2-22"));
        assertEquals(hazards.getHazardConditions().size(), 1);

        // Hazards
        HazardConditions hazard = hazards.getDefault();
        List<HazardConditionsData> conditions = hazard.getHazardConditionsData();
        assertEquals(conditions.size(), 2);

        assertEquals(conditions.get(0).getHazardCode(), "SU.Y");
        assertEquals(conditions.get(0).getPhenomena(), "High Surf");
        assertEquals(conditions.get(0).getSignificance(), "Advisory");
        assertEquals(conditions.get(0).getHazardType(), "long duration");
        assertEquals(conditions.get(0).getHazardTextURL(),
                "http://forecast.weather.gov/wwamap/wwatxtget.php?cwa=lox&wwa=High%20Surf%20Advisory");

        assertEquals(conditions.get(1).getHazardCode(), "SU.Y");
        assertEquals(conditions.get(1).getPhenomena(), "High Surf");
        assertEquals(conditions.get(1).getSignificance(), "Advisory");
        assertEquals(conditions.get(1).getHazardType(), "long duration");
        assertEquals(conditions.get(1).getHazardTextURL(),
                "http://forecast.weather.gov/wwamap/wwatxtget.php?cwa=lox&wwa=High%20Surf%20Advisory");
    }
}
