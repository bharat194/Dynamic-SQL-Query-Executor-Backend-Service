package com.main.project.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import com.main.project.dataBase.QuerydB;

public interface QueryRepo extends JpaRepository<QuerydB, Long>{

}
