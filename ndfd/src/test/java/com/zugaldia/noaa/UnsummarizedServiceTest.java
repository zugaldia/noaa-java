package com.zugaldia.noaa;

import com.zugaldia.noaa.models.*;
import com.zugaldia.noaa.models.elements.TemperatureData;
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

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

/**
 * Created by antonio on 6/24/16.
 */
public class UnsummarizedServiceTest {

    private MockWebServer server;
    private HttpUrl mockUrl;

    @Before
    public void setUp() throws IOException {
        server = new MockWebServer();

        byte[] content = Files.readAllBytes(Paths.get("src/test/fixtures/response-unsummarized-01.xml"));
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
    public void testSanity() throws IOException {
        NdfdUnsummarized client = new NdfdUnsummarized.Builder().build();
        client.setBaseUrl(mockUrl.toString());
        Response<UnsummarizedResponse> response = client.executeCall();
        assertEquals(response.code(), 200);
    }

    @Test
    public void testProduct() throws IOException {
        NdfdUnsummarized client = new NdfdUnsummarized.Builder().build();
        client.setBaseUrl(mockUrl.toString());
        Response<UnsummarizedResponse> response = client.executeCall();
        assertEquals(response.body().getHead().getProduct().getTitle(), "NOAA's National Weather Service Forecast Data");
        assertEquals(response.body().getHead().getProduct().getField(), "meteorological");
        assertEquals(response.body().getHead().getProduct().getCategory(), "forecast");
        assertEquals(response.body().getHead().getProduct().getCreationDate(), "2016-06-24T14:33:55Z");
    }

    @Test
    public void testTemperatureMax() throws IOException {
        NdfdUnsummarized client = new NdfdUnsummarized.Builder().build();
        client.setBaseUrl(mockUrl.toString());
        Response<UnsummarizedResponse> response = client.executeCall();
        assertEquals(response.code(), 200);

        // Two temperatures
        ParametersData parameters = response.body().getData().getParameters();
        List<Object> list = parameters.getList();
        assertEquals(list.size(), 2);

        // First one is the max
        TemperatureData temperature = (TemperatureData) parameters.getElement(ElementModel.MAXT);
        assertEquals(temperature.getName(), "Daily Maximum Temperature");
        assertEquals(temperature.getElementType(), "maximum");
        assertEquals(temperature.getUnits(), "Fahrenheit");
        assertEquals(temperature.getTimeLayout(), "k-p24h-n7-1");
        assertArrayEquals(temperature.getValues(), new Integer[] {81, 84, 84, 84, 88, 82, 83});
    }

    @Test
    public void testTimeLayout() throws IOException {
        NdfdUnsummarized client = new NdfdUnsummarized.Builder().build();
        client.setBaseUrl(mockUrl.toString());
        Response<UnsummarizedResponse> response = client.executeCall();
        assertEquals(response.code(), 200);

        // Keys
        List<TimeLayoutData> layouts = response.body().getData().getTimeLayouts();
        assertEquals(layouts.size(), 2);
        assertEquals(layouts.get(0).getLayoutKey(), "k-p24h-n7-1");
        assertEquals(layouts.get(1).getLayoutKey(), "k-p24h-n6-2");

        // Values
        assertEquals(layouts.get(0).getStartList().size(), 7);
        assertEquals(layouts.get(0).getEndList().size(), 7);
        assertEquals(layouts.get(1).getStartList().size(), 6);
        assertEquals(layouts.get(1).getEndList().size(), 6);
    }

    @Test
    public void testTemperatureApi() throws IOException {
        NdfdUnsummarized client = new NdfdUnsummarized.Builder().build();
        client.setBaseUrl(mockUrl.toString());
        Response<UnsummarizedResponse> response = client.executeCall();
        assertEquals(response.code(), 200);

        ParametersData parameters = response.body().getData().getParameters();
        TemperatureData temperature = (TemperatureData) parameters.getElement(ElementModel.MAXT);
        Double[] values = temperature.getValues();
        String layoutKey = temperature.getTimeLayout();
        TimeLayoutData layout = response.body().getData().getTimeLayout(layoutKey);

        // Lists should match
        assertEquals(values.length, layout.getStartList().size());
        assertEquals(values.length, layout.getEndList().size());
    }

}
