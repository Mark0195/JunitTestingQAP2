package QAP.Manager;


import QAP.Domain.Appointment.AppointmentSlot;
import QAP.Domain.Appointment.BloodDonationAppointment;
import QAP.Domain.Donor.BloodDonor;
import QAP.Domain.Database;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

public class BloodDonationAppointmentManager {
    private Database database;

    public BloodDonationAppointmentManager(Database database) {
        this.database = database;
    }

    public BloodDonationAppointment bookAppointment(int bloodDonorId) throws InvalidDonationSchedulingException{
        BloodDonationAppointment bloodDonationAppointment = new BloodDonationAppointment();
        BloodDonor bloodDonor = database.getDonor();
        if (bloodDonor.getId() == bloodDonorId) {
            LocalDate Birth = database.getDonor().getDateofbirth();
            LocalDate Today = LocalDate.now();
            Period Age = Period.between(Birth, Today);
            int age = Age.getYears();
            if (age <= 18) {
                return null;
            } else if (age >= 80) {
                return null;
            }
        }
        if (bloodDonationAppointment.isFirsttimedonor() == "False") {
            if (bloodDonor.getId() == bloodDonorId) {
                LocalDate Nextdate = database.getDonor().getNextappointment();
                LocalDate Lastdate = database.getDonor().getLastdonationdate();
                Period TimeBetween = Period.between(Nextdate, Lastdate);
                int date = TimeBetween.getDays();
                if (date < 56) {
                    throw new InvalidDonationSchedulingException("Too soon");
                } else if (date > 365) {
                    throw new InvalidDonationSchedulingException("Too far");
                }
            }
        }
        List<AppointmentSlot> appointmentSlotList = database.getAppointmentSlots();
        for (AppointmentSlot appointmentSlot: appointmentSlotList) {
            if (appointmentSlot.getBloodtype().equalsIgnoreCase(bloodDonor.getBloodtype())) {
            } else {
                throw new InvalidDonationSchedulingException("Invalid blood type");
            }
            if(appointmentSlot.equals(2)){
                throw new InvalidDonationSchedulingException("Appointment already made");
            }
        }
        return bloodDonationAppointment;
    }
}
