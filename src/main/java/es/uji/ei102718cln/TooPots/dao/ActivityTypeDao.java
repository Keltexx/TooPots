package es.uji.ei102718cln.TooPots.dao;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


import es.uji.ei102718cln.TooPots.model.ActivityType;

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

    /* Afegeix el activityType a la base de dades */
    public void addActivityType(ActivityType activityType) {
        jdbcTemplate.update("INSERT INTO ActivityType(nametype,risklevel,description) VALUES( ?, ?, ?)",
                activityType.getNameType(), activityType.getRiskLevel(),
                activityType.getDescription());
    }

    /* Esborra el activityType de la base de dades */
    public void deleteActivityType(ActivityType activityType) {
        jdbcTemplate.update("DELETE from ActivityType where nametype=?", activityType.getNameType());
    }
    
    public void deleteActivityType(String nameType) {
        jdbcTemplate.update("DELETE from ActivityType where nametype=?", nameType);
    }
    
    /* Actualitza els atributs del activityType
    (excepte el id, que és la clau primària) */
	 public void updateActivityType(ActivityType activityType) {
		jdbcTemplate.update("UPDATE activityType  SET riskLevel=?, description=? where nametype=?",
		activityType.getRiskLevel(),activityType.getDescription(),activityType.getNameType());
	 }
	 
		public ActivityType getActivityType(String nameType) {
			try {
				return jdbcTemplate.queryForObject("SELECT * from ActivityType WHERE nametype=?", new ActivityTypeRowMapper(),
						nameType);
			} catch (EmptyResultDataAccessException e) {
				return null;
			}
		}
		
		public List<ActivityType> getActivityTypes() {
			try {
				return jdbcTemplate.query("SELECT * from ActivityType", new ActivityTypeRowMapper());
			} catch (EmptyResultDataAccessException e) {
				return new ArrayList<ActivityType>();
			}
		}
 
}