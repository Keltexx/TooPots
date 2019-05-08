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
        jdbcTemplate.update("INSERT INTO ActivityType VALUES(?, ?, ?, ?)",
               activityType.getActivityTypeId(), activityType.getNameType(), activityType.getRiskLevel(),
                activityType.getDescription());
    }

    /* Esborra el activityType de la base de dades */
    public void deleteActivityType(ActivityType activityType) {
        jdbcTemplate.update("DELETE from ActivityType where activitytypeid=?", activityType.getActivityTypeId());
    }
	public void deleteActivityType(String activityType) {
		jdbcTemplate.update("DELETE from ActivityType where nametype=?", activityType);
	}
    
    /* Actualitza els atributs del activityType
    (excepte el id, que és la clau primària) */
	 public void updateActivityType(ActivityType activityType) {
		jdbcTemplate.update("UPDATE activityType  SET nameType=?, riskLevel=?, description=?",
		activityType.getNameType(), activityType.getRiskLevel(),activityType.getDescription());
	 }
	 
		public ActivityType getActivityType(String nameActivityType) {
			try {
				return jdbcTemplate.queryForObject("SELECT * from ActivityType WHERE nametype=?", new ActivityTypeRowMapper(),
						nameActivityType);
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