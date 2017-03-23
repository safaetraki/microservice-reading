package org.sioecp.service.statistics;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.sioecp.service.reading.ReadingService;
import org.sioecp.service.reading.tools.SqlConnector;
import org.sioecp.service.reading.vo.StationStats;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


class ReadingServiceTest {
    private static final String CONFIG_FILE_PATH = "src/test/resources/config-test.properties";
    private static SqlConnector sql;
    private static ReadingService service;

    @BeforeAll
    static void setUp() throws Exception {
        sql = new SqlConnector();
        sql.importPropertiesFromFile(CONFIG_FILE_PATH);
        service = new ReadingService(CONFIG_FILE_PATH);
    }

    @Test
    void testReadStatsPerStationAndDay() {
        // Exec readStatsPerStationAndDay service
        String json= service.readStatsPerStationAndDay(1,"20170316");
        assertTrue(json!=null&&json.equals("[]"));

        json= service.readStatsPerStationAndDay(1,"20170305");
        assertTrue(json!=null&&!json.isEmpty());
        Gson gson = new Gson();
        List<StationStats> list = gson.fromJson(json, new TypeToken<List<StationStats>>(){}.getType());
        assertEquals(1,list.size());
    }

    @Test
    void testReadStatsPerDay() {
        // Exec readStatsPerDay service
        String json= service.readStatsPerDay("20170316");
        assertTrue(json!=null&&json.equals("[]"));

        json= service.readStatsPerDay("20170305");
        assertTrue(json!=null&&!json.isEmpty());
        Gson gson = new Gson();
        List<StationStats> list = gson.fromJson(json, new TypeToken<List<StationStats>>(){}.getType());
        assertEquals(1,list.size());
    }

    @Test
    void testReadStatsPerStationAndPeriod() {
        // Exec readStatsPerStationAndDay service
        String json= service.readStatsPerStationAndPeriod(1,"201603160000","201606160000");
        assertTrue(json!=null&&json.equals("[]"));

        json= service.readStatsPerStationAndPeriod(1,"201603160000","201703160000");
        assertTrue(json!=null&&!json.isEmpty());
        Gson gson = new Gson();
        List<StationStats> list = gson.fromJson(json, new TypeToken<List<StationStats>>(){}.getType());
        assertEquals(1,list.size());
    }

}