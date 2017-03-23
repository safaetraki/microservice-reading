delete from `DW_station_means`;
delete from `DW_station_sampled`;
delete from `DW_station`;
delete from `DW_city`;

INSERT INTO `DW_city`
(`id`,
`name`)
VALUES
(1,
"city1");

INSERT INTO `DW_station`
(`id`,
`station_number`,
`city_id`,
`station_name`,
`address`,
`banking`,
`bonus`,
`latitude`,
`longitude`,
`elevation`)
VALUES
(1,
1,
1,
"station1",
"",
0,
0,
0,
1,
1);

INSERT INTO `DW_station_sampled`
(`id`,
`id_station`,
`timestamp_start`,
`timestamp_end`,
`movement_mean`,
`availability_mean`,
`velib_nb_mean`,
`weather`)
VALUES
(1,
1,
1488711600,
1488713400,
1.5,
3,
3,
'Rain');

INSERT INTO `DW_station_means`
(`id`,
`id_station`,
`week_day`,
`range_start`,
`range_end`,
`movement_mean`,
`availability_mean`,
`velib_nb_mean`,
`movement_mean_rain`,
`movement_mean_sun`)
VALUES
(1,
1,
1,
1488711600,
1488711840,
1.5,
3,
3,
1.5,
null);