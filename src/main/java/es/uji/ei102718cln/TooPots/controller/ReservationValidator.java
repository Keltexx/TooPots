package es.uji.ei102718cln.TooPots.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import es.uji.ei102718cln.TooPots.dao.ActivityDao;
import es.uji.ei102718cln.TooPots.dao.ReservationDao;
import es.uji.ei102718cln.TooPots.model.Reservation;

public class ReservationValidator implements Validator {

	private ReservationDao reservationDao;

	@Autowired
	public void setReservationDao(ReservationDao reservationDao) {
		this.reservationDao = reservationDao;
	}

	private ActivityDao activityDao;

	@Autowired
	public void setActivityDao(ActivityDao activityDao) {
		this.activityDao = activityDao;
	}

	@Override
	public boolean supports(Class<?> cls) {
		return Reservation.class.equals(cls);

	}

	@Override
	public void validate(Object obj, Errors errors) {
		Reservation reservation = (Reservation) obj;

		if ((reservation.getNumberOfPersons()
				+ reservationDao.getPeople("" + reservation.getActivityID())) > activityDao
						.getActivity(""+reservation.getActivityID()).getNumberOfPeople()) {
			errors.rejectValue("numberOfPersons", "completo", "Supera el máximo de asistentes posibles");
		}
	}

}