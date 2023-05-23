CREATE OR REPLACE FUNCTION teaching_clash_insert()   
					RETURNS TRIGGER AS $$     
			       BEGIN    
					  IF not  EXISTS
					 ( select * from
					  (     
					    SELECT time_slot_id
						FROM  section 
					    WHERE  (new.course_id,new.sec_id,NEW.semester,NEW.year)=(course_id,sec_id,semester,year)     
					  ) as timeslot_if_alloted
					   join 
					  ( select time_slot_id
					    from teaches natural join section
					     where (new.id,new.semester,new.year)=(id,semester,year)
					  ) as timeslot_alloted
					  on timeslot_alloted.time_slot_id = timeslot_if_alloted.time_slot_id )
					  THEN  
					     return new;
					  else
					    raise exception 'An instructor cannot teach two different sections in a semester in the same slot';
					 END IF;     
					END;     
					$$ LANGUAGE plpgsql;
					
CREATE OR REPLACE FUNCTION teaching_clash_update()   
					RETURNS TRIGGER AS $$     
			       BEGIN    
					  IF not  EXISTS
					 ( select * from
					  (     
					    SELECT time_slot_id
						FROM  section 
					    WHERE  (new.course_id,new.sec_id,NEW.semester,NEW.year)=(course_id,sec_id,semester,year)     
					  ) as timeslot_if_alloted
					   join 
					  ( select time_slot_id
					    from teaches natural join section
					     where (new.id,new.semester,new.year)=(id,semester,year) and (old.id,old.course_id,old.sec_id,old.semester,old.year)<>(id,course_id,sec_id,semester,year)
					  ) as timeslot_alloted
					  on timeslot_alloted.time_slot_id = timeslot_if_alloted.time_slot_id )
					  THEN  
					     return new;
					  else
					    raise exception 'An instructor cannot teach two different sections in a semester in the same slot';
					 END IF;     
					END;     
					$$ LANGUAGE plpgsql;
					
CREATE OR REPLACE TRIGGER teaching_clash_timming_insert
					BEFORE INSERT ON teaches 
					FOR EACH ROW 
					EXECUTE FUNCTION teaching_clash_insert();
					
CREATE OR REPLACE TRIGGER teaching_clash_timming_update
					BEFORE update ON teaches 
					FOR EACH ROW 
					EXECUTE FUNCTION teaching_clash_update();

CREATE OR REPLACE FUNCTION section_clash_update()   
					RETURNS TRIGGER AS $$     
			       BEGIN    
					  IF 
					  (
						  new.time_slot_id in 
						  (select time_slot_id
					     from teaches natural join section 
						  where (new.semester,new.year)=(semester,year) and (old.time_slot_id <> time_slot_id ) and 
						   ( id in (select id from teaches  where (course_id,sec_id,semester,year)=(new.course_id,new.sec_id,new.semester,new.year) ) ) ) 
					  )
					  THEN     
					   RAISE EXCEPTION 'An instructor cannot teach two different sections in a semester in the same slot';   
					   else
					    RETURN NEW;   
					 END IF;       
					END;     
					$$ LANGUAGE plpgsql;

					
CREATE OR REPLACE TRIGGER section_timing_update
					BEFORE  UPDATE ON section
					FOR EACH ROW 
					EXECUTE FUNCTION section_clash_update();



-- success full insertion
insert into teaches(id,course_id,sec_id,semester,year) values (43779,237,2,'Fall',2009);
DELETE FROM teaches WHERE (id,course_id,sec_id,semester,year)=('43779','237','2','Fall',2009);

-- clash case
insert into teaches(id,course_id,sec_id,semester,year) values (22591,735,1,'Spring',2003);
DELETE FROM teaches WHERE (id,course_id,sec_id,semester,year)=(22591,735,1,'Spring',2003);

-- success full update
update teaches set (id,course_id,sec_id,semester,year)=('22591','735','1','Spring',2003) where (id,course_id,sec_id,semester,year)=('22591','599','1','Spring',2003);
update teaches set (id,course_id,sec_id,semester,year)=('22591','599','1','Spring',2003) where (id,course_id,sec_id,semester,year)=('22591','735','1','Spring',2003);

-- clash in section
update section set (course_id,sec_id,semester,year,building,room_number,time_slot_id)=('571','1','Spring','2004','Power','972','K') where (course_id,sec_id,semester,year,building,room_number,time_slot_id)=('571','1','Spring','2004','Power','972','I');
update section set (course_id,sec_id,semester,year,building,room_number,time_slot_id)=('571','1','Spring','2004','Power','972','I') where (course_id,sec_id,semester,year,building,room_number,time_slot_id)=('571','1','Spring','2004','Power','972','K');

-- foregin key clash
update section set (course_id,sec_id,semester,year,building,room_number,time_slot_id)=('843','1','Fall','2010','Power','300','I') where (course_id,sec_id,semester,year,building,room_number,time_slot_id)=('843','1','Fall','2010','Fairchild','145','J');




-- tables used
select * from section order by year,semester ;

select * from teaches natural join section  order by id,year,semester  ;

select * from teaches  order by year,semester,course_id ;
				 
select * from information_schema.triggers;

