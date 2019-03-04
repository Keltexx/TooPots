package es.uji.ei1027clp.TooPots.dao;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import es.uji.ei1027clp.TooPots.model.*;
import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Repository // En Spring els DAOs van anotats amb @Repository
public class ActivityDao {

    private JdbcTemplate jdbcTemplate;

    // Obté el jdbcTemplate a partir del Data Source
    @Autowired
    public void setDataSource(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    /* Afegeix el nadador a la base de dades */
    public void addActivity(Activity actividad) {
        jdbcTemplate.update("INSERT INTO Activity VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
                activity.getID(), activity.getPlace(), activity.getName(), activity.getSchedule(),activity.getDuracion(),
activity.getDescripcion, activity.getPriceByPerson, 
activity.getNumberOfPeople, activity.getInstructorID),
activity.getActivityTypeID);
    }

    /* Esborra el nadador de la base de dades */
    public void deleteActivity(Activity activity) {
        jdbcTemplate.update("DELETE from Activity where nom=?", activity.getName());
    }

    /* Actualitza els atributs del nadador
       (excepte el nom, que és la clau primària) */
    public void updateNadador(Nadador nadador) {
jdbcTemplate.update("UPDATE activity  SET (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
                activity.getID(), activity.getPlace(), activity.getName(), activity.getSchedule(),activity.getDuracion(),
activity.getDescripcion, activity.getPriceByPerson, 
activity.getNumberOfPeople, activity.getInstructorID),
activity.getActivityTypeID);
        
//jdbcTemplate.update("UPDATE activity SET place=?, name=?, schedule=?, duracion=?, desccripcion=?, priceByPerson=?, numberOfPeople=?, instructorID=?, activityType=?,      where id=?",
                activity.getPlace(), activity.getName(), activity.getSchedule(),activity.getDuracion(),
activity.getDescripcion, activity.getPriceByPerson, 
activity.getNumberOfPeople, activity.getInstructorID),
activity.getActivityTypeID, activity.getID());
    }


}
