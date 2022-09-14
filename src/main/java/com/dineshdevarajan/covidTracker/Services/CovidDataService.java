package com.dineshdevarajan.covidTracker.Services;

import com.dineshdevarajan.covidTracker.Models.CountryStats;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

@Service
public class CovidDataService {

    private static String COVID_DATA_URL = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_daily_reports/08-14-2022.csv";

    private List<CountryStats> allStats = new ArrayList<>();

    public List<CountryStats> getAllStats() {
        return allStats;
    }

    @PostConstruct
@Scheduled(cron = "* * 1 * * *")
    public void fetchData() throws IOException, InterruptedException {
    List<CountryStats> newStats = new ArrayList<>();


        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(COVID_DATA_URL)).build();
        HttpResponse<String> httpResponse = client.send(request, HttpResponse.BodyHandlers.ofString());
    StringReader csvBodyReader = new StringReader(httpResponse.body());
        Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(csvBodyReader);
        for (CSVRecord record : records){
           CountryStats countryStat = new CountryStats();
           countryStat.setState(record.get("Province_State"));
           countryStat.setCountry(record.get("Country_Region"));
           countryStat.setLastUpdated (Integer.parseInt(record.get("Confirmed")));
           newStats.add(countryStat);

        }

        this.allStats = newStats;


    }
}
