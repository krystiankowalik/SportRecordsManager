package com.krystiankowalik.sportrecordhelper.dao;

import com.krystiankowalik.sportrecordhelper.model.Score;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScoreDao extends JpaRepository<Score,Integer> {


}
