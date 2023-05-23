create table players(
ilkid varchar(9),
firstname varchar(25) not null,
lastname varchar(25) not null,
position char(1),
firstseason year,
lastseason year,
h_feet float,
h_inches float,
weight int,
college varchar(30),
birthdate datetime,
primary key(ilkid)
);

create table teams(
team char(3) not null,
location varchar(25) not null,
name varchar(25),
leag char(1) not null,
primary key(team,leag)
);

create table coaches_career(
coachid varchar(9),
firstname varchar(25) not null,
lastname varchar(25) not null,
season_win smallint,
season_loss smallint,
playoff_win smallint,
playoff_loss smallint,
primary key(coachid)
);

create table coaches_season(
coachid varchar(9),
year year,
yr_order char(1),
firstname varchar(25) not null,
lastname varchar(25) not null,
season_win int,
season_loss int,
playoff_win int,
playoff_loss int,
team char(3) not null,
primary key(coachid,year)
);

create table player_playoffs_career(
ilkid varchar(9),
firstname varchar(25) not null,
lastname varchar(25) not null,
leag char(1),
gp int,
minutes int,
pts int,
dreb int,
oreb int,
reb int,
asts int,
stl int,
blk int,
turnover int,
pf int,
fga int,
fgm int,
fta int,
ftm int,
tpa int,
tpm int,
primary key(ilkid)
);

create table player_playoffs(
ilkid varchar(9),
year year,
firstname varchar(25) not null,
lastname varchar(25) not null,
team char(3),
leag char(1),
gp int,
minutes int,
pts int,
dreb int,
oreb int,
reb int,
asts int,
stl int,
blk int,
turnover int,
pf int,
fga int,
fgm int,
fta int,
ftm int,
tpa int,
tpm int,
primary key(ilkid,year)
);

create table player_regular_season_career(
ilkid varchar(9),
firstname varchar(25) not null,
lastname varchar(25) not null,
leag char(1),
gp int,
minutes int,
pts int,
oreb int,
dreb int,
reb int,
asts int,
stl int,
blk int,
turnover int,
pf int,
fga int,
fgm int,
fta int,
ftm int,
tpa int,
tpm int,
primary key (ilkid,leag)
);

create table player_regular_season(
ilkid varchar(9),
year year,
firstname varchar(25) not null,
lastname varchar(25) not null,
team char(3),
leag char(1),
gp smallint,
minutes int,
pts int,
oreb int,
dreb int,
reb int,
asts int,
stl int,
blk int,
turnover int,
pf int,
fga int,
fgm int,
fta int,
ftm int,
tpa int,
tpm int,
primary key(ilkid,year,team)
);
