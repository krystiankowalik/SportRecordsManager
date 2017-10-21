package com.krystiankowalik.sportrecordhelper.dao;

import com.krystiankowalik.sportrecordhelper.model.Athlete;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AthleteDao extends JpaRepository<Athlete,Integer> {


}
