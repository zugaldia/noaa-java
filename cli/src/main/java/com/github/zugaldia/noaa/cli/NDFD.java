package com.github.zugaldia.noaa.cli;

import com.github.zugaldia.noaa.NdfdUnsummarized;
import com.github.zugaldia.noaa.models.*;
import com.github.zugaldia.noaa.models.elements.*;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import retrofit2.Response;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by antonio on 6/24/16.
 */
public class NDFD {

    private static ResponseData data;

    public static void main(String[] args) throws IOException {
        System.out.println("Launching request...");

        // Build client
        NdfdUnsummarized client = new NdfdUnsummarized.Builder()
                .setLocation(38.90962, -77.04341) // Dupont Circle, Washington, DC
                .setProduct(ProductModel.TIME_SERIES)
                .setUnit(UnitModel.METRIC)
                .setUtcBegin(new DateTime(DateTimeZone.UTC).toDate())
                .setUtcEnd(new DateTime(DateTimeZone.UTC).plusDays(1).toDate())
                .requestElement(ElementModel.MAXT)
                .requestElement(ElementModel.MINT)
                .requestElement(ElementModel.TEMP)
                .requestElement(ElementModel.APPT)
                .requestElement(ElementModel.POP12)
                .requestElement(ElementModel.SNOW)
                .requestElement(ElementModel.RH)
                .requestElement(ElementModel.WX)
                .requestElement(ElementModel.WWA)
                .requestElement(ElementModel.SKY)
                .requestElement(ElementModel.ICONS)
                .build();

        // Enable debug
        client.setEnableDebug(true);

        // Get the response
        Response<UnsummarizedResponse> response = client.executeCall();
        data = response.body().getData();

        // Print data
        printMaxt();
        printMint();
        printTemp();
        printAppt();
        printPop12();
        printSnow();
        printRh();
        printWx();
        printWwa();
        printSky();
        printIcons();
    }

    private static void printMaxt() {
        TemperatureData element = (TemperatureData) data.getDefault().getElement(ElementModel.MAXT);
        System.out.println("\n" + element.getName());

        Double[] values = element.getValues();
        TimeLayoutData timeLayout = data.getTimeLayout(element.getTimeLayout());
        for (int i = 0; i < values.length; i++) {
            System.out.println(String.format(Locale.US,
                    "%.0f %s -- %s", values[i], element.getUnits(), formatTimeLayout(i, timeLayout)));
        }
    }

    private static void printMint() {
        TemperatureData element = (TemperatureData) data.getDefault().getElement(ElementModel.MINT);
        System.out.println("\n" + element.getName());

        Double[] values = element.getValues();
        TimeLayoutData timeLayout = data.getTimeLayout(element.getTimeLayout());
        for (int i = 0; i < values.length; i++) {
            System.out.println(String.format(Locale.US,
                    "%.0f %s -- %s", values[i], element.getUnits(), formatTimeLayout(i, timeLayout)));
        }
    }

    private static void printTemp() {
        TemperatureData element = (TemperatureData) data.getDefault().getElement(ElementModel.TEMP);
        System.out.println("\n" + element.getName());

        // Uses Joda-Time
        Date tomorrow = new DateTime().plusDays(1).toDate();

        Double[] values = element.getValues();
        TimeLayoutData timeLayout = data.getTimeLayout(element.getTimeLayout());
        for (int i = 0; i < values.length; i++) {
            // Only next 24 hours
            if (timeLayout.getUtcStartList().get(i).before(tomorrow)) {
                System.out.println(String.format(Locale.US,
                        "%.0f %s -- %s", values[i], element.getUnits(), formatTimeLayout(i, timeLayout)));
            }
        }
    }

    private static void printAppt() {
        TemperatureData element = (TemperatureData) data.getDefault().getElement(ElementModel.APPT);
        System.out.println("\n" + element.getName());

        // Uses Joda-Time
        Date tomorrow = new DateTime().plusDays(1).toDate();

        Double[] values = element.getValues();
        TimeLayoutData timeLayout = data.getTimeLayout(element.getTimeLayout());
        for (int i = 0; i < values.length; i++) {
            // Only next 24 hours
            if (timeLayout.getUtcStartList().get(i).before(tomorrow)) {
                System.out.println(String.format(Locale.US,
                        "%.0f %s -- %s", values[i], element.getUnits(), formatTimeLayout(i, timeLayout)));
            }
        }
    }

    private static void printPop12() {
        POPData element = (POPData) data.getDefault().getElement(ElementModel.POP12);
        System.out.println("\n" + element.getName());

        // Uses Joda-Time
        Date tomorrow = new DateTime().plusDays(1).toDate();

        Double[] values = element.getValues();
        TimeLayoutData timeLayout = data.getTimeLayout(element.getTimeLayout());
        for (int i = 0; i < values.length; i++) {
            // Only next 24 hours
            if (timeLayout.getUtcStartList().get(i).before(tomorrow)) {
                System.out.println(String.format(Locale.US,
                        "%.0f %s -- %s", values[i], element.getUnits(), formatTimeLayout(i, timeLayout)));
            }
        }
    }

    private static void printSnow() {
        PrecipitationData element = (PrecipitationData) data.getDefault().getElement(ElementModel.SNOW);
        System.out.println("\n" + element.getName());

        // Uses Joda-Time
        Date tomorrow = new DateTime().plusDays(1).toDate();

        Double[] values = element.getValues();
        TimeLayoutData timeLayout = data.getTimeLayout(element.getTimeLayout());
        for (int i = 0; i < values.length; i++) {
            // Only next 24 hours
            if (timeLayout.getUtcStartList().get(i).before(tomorrow)) {
                System.out.println(String.format(Locale.US,
                        "%.0f %s -- %s", values[i], element.getUnits(), formatTimeLayout(i, timeLayout)));
            }
        }
    }

    private static void printRh() {
        HumidityData element = (HumidityData) data.getDefault().getElement(ElementModel.RH);
        System.out.println("\n" + element.getName());

        Double[] values = element.getValues();
        TimeLayoutData timeLayout = data.getTimeLayout(element.getTimeLayout());
        for (int i = 0; i < values.length; i++) {
            System.out.println(String.format(Locale.US,
                    "%.0f %s -- %s", values[i], element.getUnits(), formatTimeLayout(i, timeLayout)));
        }
    }

    private static void printWx() {
        WeatherData element = (WeatherData) data.getDefault().getElement(ElementModel.WX);
        System.out.println("\n" + element.getName());

        // Uses Joda-Time
        Date tomorrow = new DateTime().plusDays(1).toDate();

        List<WeatherConditionsData> conditions = element.getWeatherConditions();
        TimeLayoutData timeLayout = data.getTimeLayout(element.getTimeLayout());
        for (int i = 0; i < conditions.size(); i++) {
            if (timeLayout.getUtcStartList().get(i).before(tomorrow)) {
                List<WeatherConditionsValueData> values = conditions.get(i).getValues();
                if (values != null) {
                    for (int j = 0; j < values.size(); j++) {
                        System.out.println(String.format(Locale.US, "%s %s - %s %s - %s",
                                values.get(j).getCoverage(), values.get(j).getWeatherType(),
                                values.get(j).getVisibility().getValue(), values.get(j).getVisibility().getUnits(),
                                formatTimeLayout(i, timeLayout)));
                    }
                }
            }
        }
    }

    private static void printWwa() {
        HazardsData element = (HazardsData) data.getDefault().getElement(ElementModel.WWA);
        System.out.println("\n" + element.getName());

        boolean printed = false;

        List<HazardConditions> conditions = element.getHazardConditions();
        TimeLayoutData timeLayout = data.getTimeLayout(element.getTimeLayout());
        for (int i = 0; i < conditions.size(); i++) {
            HazardConditions condition = conditions.get(i);
            List<HazardConditionsData> hazardDataList = condition.getHazardConditionsData();
            if (hazardDataList != null) {
                for (int j = 0; j < hazardDataList.size(); j++) {
                    printed = true;
                    HazardConditionsData hazardData = hazardDataList.get(j);
                    System.out.println(String.format(Locale.US,
                            "%s %s %s -- %s",
                            hazardData.getHazardType(), hazardData.getPhenomena(), hazardData.getSignificance(),
                            formatTimeLayout(i, timeLayout)));
                }
            }
        }

        if (!printed) {
            System.out.println("(None)");
        }
    }

    private static void printSky() {
        CloudAmountData element = (CloudAmountData) data.getDefault().getElement(ElementModel.SKY);
        System.out.println("\n" + element.getName());

        Double[] values = element.getValues();
        TimeLayoutData timeLayout = data.getTimeLayout(element.getTimeLayout());
        for (int i = 0; i < values.length; i++) {
            System.out.println(String.format(Locale.US,
                    "%.0f %s -- %s", values[i], element.getUnits(), formatTimeLayout(i, timeLayout)));
        }
    }

    private static void printIcons() {
        ConditionsIconData element = (ConditionsIconData) data.getDefault().getElement(ElementModel.ICONS);
        System.out.println("\n" + element.getName());

        // Uses Joda-Time
        Date tomorrow = new DateTime().plusDays(1).toDate();

        List<String> icons = element.getIconLinks();
        TimeLayoutData timeLayout = data.getTimeLayout(element.getTimeLayout());
        for (int i = 0; i < icons.size(); i++) {
            // Only next 24 hours
            if (timeLayout.getUtcStartList().get(i).before(tomorrow)) {
                System.out.println(String.format(Locale.US,
                        "%s -- %s", icons.get(i), formatTimeLayout(i, timeLayout)));
            }
        }
    }

    private static String formatTimeLayout(int i, TimeLayoutData timeLayout) {
        List<Date> start = timeLayout.getUtcStartList();
        List<Date> end = timeLayout.getUtcEndList();
        if (end != null) {
            return String.format(Locale.US, "From %s to %s", start.get(i), end.get(i));
        } else {
            return String.format(Locale.US, "At %s", start.get(i));
        }
    }
}
