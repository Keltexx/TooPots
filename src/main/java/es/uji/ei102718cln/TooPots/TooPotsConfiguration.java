package es.uji.ei102718cln.TooPots;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.expression.ParseException;
import org.springframework.format.Formatter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import javax.sql.DataSource;

@Configuration
public class TooPotsConfiguration {
	@Bean
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource dataSource() {
        return DataSourceBuilder.create().build();
    }
//	@Bean
//	public Formatter<LocalDate> localDateFormatter() {
//	   return new Formatter<LocalDate>() {
//	       @Override
//	       public LocalDate parse(String text, Locale locale) throws ParseException {
//	           return LocalDate.parse(text, DateTimeFormatter.ISO_LOCAL_DATE);
//	       }
//
//	       @Override
//	       public String print(LocalDate object, Locale locale) {
//	           return DateTimeFormatter.ofPattern("dd-MM-yyyy").format(object);  
//	       }
//	   };
//	}

	

}
