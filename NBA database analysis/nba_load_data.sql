
-- loading data into corresponding tables

.mode csv
.import --skip 1 ./data/players.csv players

.mode csv
.import --skip 1 ./data/teams.csv teams

.mode csv
.import --skip 1 ./data/coaches_career.csv  coaches_career

.mode csv
.import --skip 1 ./data/coaches_season.csv coaches_season

.mode csv
.import --skip 1 ./data/player_playoffs_career.csv player_playoffs_career

.mode csv
.import --skip 1 ./data/player_playoffs.csv player_playoffs

.mode csv
.import --skip 1 ./data/player_regular_season_career.csv player_regular_season_career

.mode csv
.import --skip 1 ./data/player_regular_season.csv player_regular_season







UPDATE players SET ilkid = trim(ilkid);

UPDATE players SET   ilkid = UPPER(ilkid);

UPDATE player_playoffs_career SET ilkid = trim(ilkid),
				  leag = trim(leag);

UPDATE coaches_career SET coachid = trim(coachid);


UPDATE coaches_season SET coachid = trim(coachid),
                             year = trim(year);
							
update teams SET leag = trim(leag),
		 team = trim(team);
                 
UPDATE player_playoffs SET ilkid = trim(ilkid),
			   year = trim(year),
		      	   team = trim(team);

                            
UPDATE player_regular_season SET ilkid = trim(ilkid),
				year = trim(year),
				team = trim(team);
                            

UPDATE player_regular_season_career SET ilkid = trim(ilkid),
					leag = trim(leag);

                                        
UPDATE player_regular_season SET ilkid = UPPER(ilkid);

UPDATE player_regular_season_career SET ilkid = UPPER(ilkid);

UPDATE player_playoffs SET ilkid = UPPER(ilkid);

UPDATE player_playoffs_career SET ilkid = UPPER(ilkid);



