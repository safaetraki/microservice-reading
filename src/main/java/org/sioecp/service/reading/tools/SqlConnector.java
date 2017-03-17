package org.sioecp.service.reading.tools;

import org.sioecp.service.reading.vo.StationStats;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Created by Safae on 01/03/2017.
 */
public class SqlConnector {

        private String dburl = "jdbc:mysql://localhost:3306/db";
        private String dbuser = "root";
        private String dbpassword = "";

        public SqlConnector(){
            try {
                Class.forName( "com.mysql.cj.jdbc.Driver" );
            } catch ( ClassNotFoundException e ) {
                System.out.println("SQL Driver not found.");
            }
        }

        public boolean importPropertiesFromFile(String filepath){
            Properties prop = new Properties();
            InputStream input = null;
            try {
                input = new FileInputStream(filepath);
                prop.load(input);
                dburl = prop.getProperty("dburl");
                dbuser = prop.getProperty("dbuser");
                dbpassword = prop.getProperty("dbpassword");

            } catch (IOException e) {
                e.printStackTrace();
                return false;
            } finally {
                try {
                    input.close();
                } catch (Exception ignore) {}
            }
            return true;
        }

        private Connection connect(){
            Connection connection;
            try {
                connection = DriverManager.getConnection(dburl, dbuser, dbpassword);
            }
            catch ( SQLException e ) {
                return null;
            }
            return connection;
        }

        private void disconnect(Connection connection){
            try {
                connection.close();
            } catch ( Exception ignore ) {}
        }

        public int execWrite(String query) {
            Connection connection = connect();
            int status = 1;
            if (connection == null)
                return status;

            try {
                Statement statement = connection.createStatement();
                status = statement.executeUpdate(query);
            }
            catch (SQLException e){
                return status;
            }
            finally {
                disconnect(connection);
            }
            return status;
        }

        public List<StationStats> execRead(String query){
            List<StationStats> resList;
            Connection connection = connect();
            if (connection == null)
                return null;

            try {
                Statement statement = connection.createStatement();
                ResultSet result = statement.executeQuery(query);
                ResultSetMetaData rsmd = result.getMetaData();
                int numCols = rsmd.getColumnCount();
                resList = new ArrayList<>();

                while (result.next()) {
                    StationStats row = new StationStats();
                    row.setIdStation(result.getString(1)!=null?Integer.valueOf(result.getString(1)):null);
                    row.setStationName(result.getString(2));
                    row.setLongitude(result.getString(3));
                    row.setLatitude(result.getString(4));
                    row.setAddress(result.getString(5));
                    row.setMovementMean(result.getString(6)!=null?Float.valueOf(result.getString(6)):null);
                    row.setAvailabilityMean(result.getString(7)!=null?Float.valueOf(result.getString(7)):null);
                    row.setVelibNbMean(result.getString(8)!=null?Float.valueOf(result.getString(8)):null);
                    if(numCols==9){
                        row.setWeather(result.getString(9));
                    } else {
                        row.setMovementMeanRain(result.getString(9)!=null?Float.valueOf(result.getString(9)):null);
                        row.setMovementMeanSun(result.getString(10)!=null?Float.valueOf(result.getString(10)):null);
                    }
                    resList.add(row);
                }
            }
            catch (SQLException e){
                return null;
            }
            finally {
                disconnect(connection);
            }
            return resList;
        }

}
