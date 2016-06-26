package com.zugaldia.noaa;

import com.zugaldia.noaa.models.*;
import com.zugaldia.noaa.models.elements.*;
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

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

/**
 * Created by antonio on 6/25/16.
 */
public class CommonElementsTest {

    private MockWebServer server;
    private HttpUrl mockUrl;

    @Before
    public void setUp() throws IOException {
        server = new MockWebServer();

        byte[] content = Files.readAllBytes(Paths.get("src/test/fixtures/response-unsummarized-16.xml"));
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
    public void testElementMaxt() throws IOException {
        NdfdUnsummarized client = new NdfdUnsummarized.Builder().build();
        client.setBaseUrl(mockUrl.toString());
        Response<UnsummarizedResponse> response = client.executeCall();

        ParametersData parameters = response.body().getData().getParameters();
        TemperatureData temperature = (TemperatureData) parameters.getElement(ElementModel.MAXT);
        assertArrayEquals(temperature.getValues(), new Double[] {86d, 86d, 87d, 83d, 85d, 85d, 87d});
    }

    @Test
    public void testElementMint() throws IOException {
        NdfdUnsummarized client = new NdfdUnsummarized.Builder().build();
        client.setBaseUrl(mockUrl.toString());
        Response<UnsummarizedResponse> response = client.executeCall();

        ParametersData parameters = response.body().getData().getParameters();
        TemperatureData temperature = (TemperatureData) parameters.getElement(ElementModel.MINT);
        assertArrayEquals(temperature.getValues(), new Double[] {69d, 64d, 71d, 68d, 66d, 67d, 68d});
    }

    @Test
    public void testElementTemp() throws IOException {
        NdfdUnsummarized client = new NdfdUnsummarized.Builder().build();
        client.setBaseUrl(mockUrl.toString());
        Response<UnsummarizedResponse> response = client.executeCall();

        ParametersData parameters = response.body().getData().getParameters();
        TemperatureData temperature = (TemperatureData) parameters.getElement(ElementModel.TEMP);
        assertArrayEquals(temperature.getValues(), new Double[] { 73d, 69d, 69d, 74d, 81d, 85d, 84d, 79d, 72d, 69d, 66d,
                71d, 80d, 84d, 85d, 81d, 76d, 74d, 73d, 76d, 83d, 86d, 85d, 81d, 72d, 73d, 82d, 77d, 70d, 72d, 83d, 79d,
                71d, 72d, 84d, 79d, 72d, 74d, 86d, 81d});
    }

    @Test
    public void testElementAppt() throws IOException {
        NdfdUnsummarized client = new NdfdUnsummarized.Builder().build();
        client.setBaseUrl(mockUrl.toString());
        Response<UnsummarizedResponse> response = client.executeCall();

        ParametersData parameters = response.body().getData().getParameters();
        TemperatureData temperature = (TemperatureData) parameters.getElement(ElementModel.APPT);
        assertArrayEquals(temperature.getValues(), new Double[] { 73d, 69d, 69d, 74d, 81d, 84d, 83d, 79d, 72d, 69d, 66d,
                71d, 82d, 85d, 87d, 84d, 76d, 74d, 73d, 76d, 87d, 89d, 88d, 84d, 72d, 73d, 82d, 77d, 70d, 72d, 82d, 79d,
                71d, 72d, 85d, 79d, 72d, 74d, 87d, 83d });
    }

    @Test
    public void testElementPop12() throws IOException {
        NdfdUnsummarized client = new NdfdUnsummarized.Builder().build();
        client.setBaseUrl(mockUrl.toString());
        Response<UnsummarizedResponse> response = client.executeCall();

        ParametersData parameters = response.body().getData().getParameters();
        POPData pop12 = (POPData) parameters.getElement(ElementModel.POP12);
        assertArrayEquals(pop12.getValues(), new Double[] { 1d, 2d, 4d, 37d, 42d, 38d, 38d, 23d, 9d, 9d, 20d, 38d, 38d,
                33d });
    }

    @Test
    public void testElementSnow() throws IOException {
        NdfdUnsummarized client = new NdfdUnsummarized.Builder().build();
        client.setBaseUrl(mockUrl.toString());
        Response<UnsummarizedResponse> response = client.executeCall();

        ParametersData parameters = response.body().getData().getParameters();
        PrecipitationData precipitation = (PrecipitationData) parameters.getElement(ElementModel.SNOW);
        assertArrayEquals(precipitation.getValues(), new Double[] { 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00,
                0.00, 0.00, 0.00, 0.00 });
    }

    @Test
    public void testElementRh() throws IOException {
        NdfdUnsummarized client = new NdfdUnsummarized.Builder().build();
        client.setBaseUrl(mockUrl.toString());
        Response<UnsummarizedResponse> response = client.executeCall();

        ParametersData parameters = response.body().getData().getParameters();
        HumidityData humidity = (HumidityData) parameters.getElement(ElementModel.RH);
        assertArrayEquals(humidity.getValues(), new Double[] { 66d, 76d, 76d, 62d, 42d, 36d, 37d, 47d, 68d, 76d, 84d,
                76d, 58d, 51d, 52d, 65d, 73d, 82d, 84d, 79d, 63d, 53d, 57d, 65d, 76d, 68d, 46d, 56d, 68d, 66d, 40d, 54d,
                71d, 76d, 49d, 64d, 82d, 79d, 47d, 63d });
    }

    @Test
    public void testElementWx() throws IOException {
        NdfdUnsummarized client = new NdfdUnsummarized.Builder().build();
        client.setBaseUrl(mockUrl.toString());
        Response<UnsummarizedResponse> response = client.executeCall();
        // TODO
    }

    @Test
    public void testElementWwa() throws IOException {
        NdfdUnsummarized client = new NdfdUnsummarized.Builder().build();
        client.setBaseUrl(mockUrl.toString());
        Response<UnsummarizedResponse> response = client.executeCall();
        // TODO
    }

    @Test
    public void testElementSky() throws IOException {
        NdfdUnsummarized client = new NdfdUnsummarized.Builder().build();
        client.setBaseUrl(mockUrl.toString());
        Response<UnsummarizedResponse> response = client.executeCall();

        ParametersData parameters = response.body().getData().getParameters();
        CloudAmountData cloudAmount = (CloudAmountData) parameters.getElement(ElementModel.SKY);
        assertArrayEquals(cloudAmount.getValues(), new Double[] { 3d, 2d, 1d, 1d, 11d, 9d, 6d, 4d, 5d, 15d, 17d, 28d,
                29d, 58d, 84d, 90d, 89d, 88d, 83d, 80d, 68d, 57d, 58d, 60d, 47d, 45d, 39d, 29d, 18d, 19d, 35d, 35d, 46d,
                54d, 62d, 55d, 45d, 47d, 42d, 44d });
    }

    @Test
    public void testElementIcons() throws IOException {
        NdfdUnsummarized client = new NdfdUnsummarized.Builder().build();
        client.setBaseUrl(mockUrl.toString());
        Response<UnsummarizedResponse> response = client.executeCall();
        // TODO
    }

}
