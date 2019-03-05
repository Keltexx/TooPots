package es.uji.ei1027.clubesportiu.dao;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Repository // En Spring els DAOs van anotats amb @Repository
public class ActivityTypeDao {

    private JdbcTemplate jdbcTemplate;

    // Obté el jdbcTemplate a partir del Data Source
    @Autowired
    public void setDataSource(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    /* Afegeix el nadador a la base de dades */
    public void addActivityType(ActivityType activityType) {
        jdbcTemplate.update("INSERT INTO Nadador VALUES(?, ?, ?, ?)",
                activityType.getId(), activityType.getNameType(), activityType.getRiskLevel(),
                activityType.getDescription());
    }

    /* Esborra el nadador de la base de dades */
    public void deleteActivityType(ActivityType activityType) {
        jdbcTemplate.update("DELETE from ActivityTypewhere id=?", activityType.getId());
    }