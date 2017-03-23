package org.sioecp.service.reading.engine;

import org.sioecp.service.reading.tools.SqlConnector;
import org.sioecp.service.reading.vo.StationStats;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class ReadingEngine {

    private SqlConnector dbconnector;

    public ReadingEngine(SqlConnector sql){
        dbconnector = sql;
    }

    public List<StationStats> getStatisticsPerStationAndPeriod(Integer idStation, LocalDateTime startDate, LocalDateTime endDate) {
        List<StationStats> res = dbconnector.execRead("SELECT s.id, s.station_name, s.longitude, s.latitude, s.address, " +
                "ss.movement_mean, ss.availability_mean, ss.velib_nb_mean, ss.weather FROM DW_station s, DW_station_sampled ss " +
                "Where s.id = ss.id_station and s.id = " + idStation +
                " and from_unixtime(ss.timestamp_start) >= '" + Timestamp.valueOf(startDate)+
                "' and from_unixtime(ss.timestamp_end) <= '" + Timestamp.valueOf(endDate)+"'");
        return res;
    }

    public List<StationStats> getGlobalStatisticsPerStationAndDay(Integer idStation, LocalDate date) {
        List<StationStats> res = dbconnector.execRead("SELECT s.id,s.station_name,s.longitude,s.latitude,s.address," +
                "sm.movement_mean,sm.availability_mean,sm.velib_nb_mean,sm.movement_mean_rain,sm.movement_mean_sun FROM DW_station s, DW_station_means sm " +
                "Where s.id = sm.id_station and s.id = " + idStation +
                " and date(from_unixtime(sm.range_end)) = '" + Date.valueOf(date)+"'");

        return res;
    }

    public List<StationStats> getGlobalStatisticsPerDay(LocalDate date) {
        List<StationStats> res = dbconnector.execRead("SELECT s.id,s.station_name,s.longitude,s.latitude,s.address," +
                "sm.movement_mean,sm.availability_mean,sm.velib_nb_mean,sm.movement_mean_rain,sm.movement_mean_sun FROM DW_station s, DW_station_means sm " +
                "Where s.id = sm.id_station" +
                " and date(from_unixtime(sm.range_end)) = '" + Date.valueOf(date) + "'"+
                " group by s.id");
        return res;
    }
}
