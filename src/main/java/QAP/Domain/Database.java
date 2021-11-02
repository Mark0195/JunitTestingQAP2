package QAP.Domain;

import QAP.Domain.Appointment.AppointmentSlot;
import QAP.Domain.Appointment.BloodDonationAppointment;
import QAP.Domain.Donor.BloodDonor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

public class Database {
    public List<AppointmentSlot> getAppointmentSlots(){
        ArrayList<AppointmentSlot> appointmentSlots = new ArrayList<AppointmentSlot>();
        AppointmentSlot appointmentSlot = new AppointmentSlot();
        appointmentSlot.setId(1);
        appointmentSlot.setLocation("14 Trails End. Paradise NL");
        appointmentSlot.setDate(LocalDate.of(2021, Month.JANUARY, 19));
        appointmentSlot.setStarttime(LocalTime.of(12, 30));
        appointmentSlot.setEndtime(LocalTime.of(1, 00));
        appointmentSlot.setBloodtype("A");
        appointmentSlots.add(appointmentSlot);
        return appointmentSlots;
    }
    public BloodDonor getDonor(){
        BloodDonor bloodDonor = new BloodDonor();
        bloodDonor.setId(1);
        bloodDonor.setFirstname("Peter");
        bloodDonor.setLastname("PumpkinEater");
        bloodDonor.setDateofbirth(LocalDate.of(2006, Month.MARCH, 16));
        bloodDonor.setBloodtype("A");
        bloodDonor.setNextappointment(LocalDate.of(2022, Month.FEBRUARY, 14));
        bloodDonor.setLastdonationdate(LocalDate.of(2021, Month.JULY, 04));
        return bloodDonor;
    }
    public BloodDonationAppointment getBloodDonationAppointment(int id){
        BloodDonationAppointment bloodDonationAppointment = new BloodDonationAppointment();
        bloodDonationAppointment.setAppointmentduration(LocalTime.of(0, 30));
        bloodDonationAppointment.setFirsttimedonor("True");
        return bloodDonationAppointment;
    }
}