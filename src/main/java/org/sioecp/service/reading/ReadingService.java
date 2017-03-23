package org.sioecp.service.reading;

import com.google.gson.Gson;
import org.sioecp.service.reading.engine.ReadingEngine;
import org.sioecp.service.reading.tools.SqlConnector;
import org.sioecp.service.reading.vo.StationStats;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;


@Path("/reading")
public class ReadingService {

        private String propertiesPath;

        public ReadingService(String propertiesPath){
            this.propertiesPath = propertiesPath;
        }

        @GET
        @Path("/statsPerStationAndDay/{station}/{day}")
        public String readStatsPerStationAndDay(@PathParam("station") Integer station, @PathParam("day") String day) {
            // Setup SQL connection
            SqlConnector sql = new SqlConnector();
            sql.importPropertiesFromFile(propertiesPath);

            // Init ReadingEngine class
            ReadingEngine engine = new ReadingEngine(sql);

            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMdd");
            LocalDate dt = LocalDate.parse(day, dtf);
            // Get statistics per station and day
            List<StationStats> list=engine.getGlobalStatisticsPerStationAndDay(station,dt);
            Gson gson = new Gson();
            return gson.toJson(list);
        }

    @GET
    @Path("/statsPerDay/{day}")
    public String readStatsPerDay(@PathParam("day") String day) {
        // Setup SQL connection
        SqlConnector sql = new SqlConnector();
        sql.importPropertiesFromFile(propertiesPath);

        // Init ReadingEngine class
        ReadingEngine engine = new ReadingEngine(sql);

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMdd");
        LocalDate dt = LocalDate.parse(day, dtf);
        // Get statistics per day
        List<StationStats> list=engine.getGlobalStatisticsPerDay(dt);
        Gson gson = new Gson();
        return gson.toJson(list);
    }

    @GET
    @Path("/statsPerStationAndPeriod/{station}/{start}/{end}")
    public String readStatsPerStationAndPeriod(@PathParam("station") Integer station, @PathParam("start") String periodStart, @PathParam("end") String periodEnd) {
        // Setup SQL connection
        SqlConnector sql = new SqlConnector();
        sql.importPropertiesFromFile(propertiesPath);

        // Init ReadingEngine class
        ReadingEngine engine = new ReadingEngine(sql);

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMddHHmm");
        LocalDateTime start = LocalDateTime.parse(periodStart, dtf);
        LocalDateTime end = LocalDateTime.parse(periodEnd, dtf);
        // Get statistics per station and day
        List<StationStats> list=engine.getStatisticsPerStationAndPeriod(station,start,end);
        Gson gson = new Gson();
        return gson.toJson(list);
    }


}
