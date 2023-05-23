
 -- 4.1
select count(ilkid),position
from players
group by position;

-- 4.2 
select year
from
(select ply_reg_sea.year as year,(ply_reg_sea.tot_gp + ply_off.tot_gp) as sumall_gp
from (select year ,sum(gp) as tot_gp 
      from player_regular_season 
      where team <> 'TOT'
      group by year)as ply_reg_sea,(select year,sum(gp) as tot_gp 
                                     from player_playoffs 
                                     group by year)as ply_off
where ply_reg_sea.year = ply_off.year
order by sumall_gp desc ,ply_reg_sea.year asc ) as productive_years
limit 5;


-- 4.3


create table player_regular_season_career_grouped as (select s.ilkid as ilkid,t.firstname as firstname,t.lastname as lastname,s.gp as gp,s.minutes as minutes,s.pts as pts,s.oreb as oreb,s.dreb as dreb,s.reb as reb,s.asts as asts,s.stl as stl,s.blk as blk,s.turnover as turnover,s.pf as pf,s.fga as fga,s.fgm as fgm,s.fta as fta,s.ftm as ftm,s.tpa as tpa,s.tpm as tpm
	from (select distinct ilkid ,firstname,lastname from player_regular_season_career) as t,(select ilkid,sum(gp) as gp,sum(minutes) as minutes,sum(pts) as pts,sum(oreb) as oreb,sum(dreb) as dreb,sum(reb) as reb, sum(asts) as asts,sum(stl) as stl,sum(blk) as blk,sum(pf) as pf,sum(fga) as fga,sum(fgm) as fgm,sum(fta) as fta,sum(ftm) as ftm,sum(tpa) as tpa,sum(tpm) as tpm,sum(turnover) as turnover
                                             from player_regular_season_career
                                             group by ilkid) as s
                          where t.ilkid = s.ilkid);
     
-- -- i use >  all but sqlite3 is not supporting all i did in sql workbench


alter table player_regular_season_career_grouped
add column eff float;
alter table player_regular_season_career_grouped
add primary key (ilkid);
update player_regular_season_career_grouped
set eff = (pts + reb + asts + stl + blk- ((fga - fgm) + (fta - ftm) + turnover));

drop table player_regular_season_career;

alter table player_regular_season_career_grouped rename player_regular_season_career;


select ilkid,firstname,lastname,eff
from player_regular_season_career
where (gp > 500)
order by eff desc
limit 10;

-- 4.4
with ply_gp_peryear as (select ilkid,year,sum(gp) as gp 
                        from player_regular_season 
                        where player_regular_season.team <> 'TOT'
						group by ilkid,year) 
select count(T.ilkid) as no_of_players
from  ply_gp_peryear as T
where (T.year = 1990) and ( T.gp > all (select gp 
                                        from ply_gp_peryear as S
					where T.ilkid = S.ilkid and  s.year <> 1990) );
                                        
                                        

-- 4.5
select t.ilkid as ilkid,t.firstname as firstname,t.lastname as lastname,t.gp as gp,t.eff as eff
from player_regular_season_career as t
where (select count(ilkid) as total from player_regular_season_career)  = (select count(x.ilkid)
                                                                 from player_regular_season_career as x,player_regular_season_career as y
						                 where (t.ilkid = y.ilkid) and (not ((x.gp > y.gp) and (x.eff > y.eff)) ) )
order by ilkid;
/*x do not dominate y scenario also contain itself not dominated */




