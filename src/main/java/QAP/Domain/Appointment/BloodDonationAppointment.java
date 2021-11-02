package QAP.Domain.Appointment;

import java.time.LocalTime;
import java.time.LocalDate;

public class BloodDonationAppointment {
    private int appointmentId;
    private LocalDate appointmentdate;
    private LocalTime appointmenttime;
    private LocalTime appointmentduration;
    private String location;
    private String bloodtype;
    private String firsttimedonor;
    private int donorid;

    public int getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(int appointmentId) {
        this.appointmentId = appointmentId;
    }

    public LocalDate getAppointmentdate() {
        return appointmentdate;
    }

    public void setAppointmentdate(LocalDate appointmentdate) {
        this.appointmentdate = appointmentdate;
    }

    public LocalTime getAppointmenttime() {
        return appointmenttime;
    }

    public void setAppointmenttime(LocalTime appointmenttime) {
        this.appointmenttime = appointmenttime;
    }

    public LocalTime getAppointmentduration() {
        return appointmentduration;
    }

    public void setAppointmentduration(LocalTime appointmentduration) {
        this.appointmentduration = appointmentduration;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getBloodtype() {
        return bloodtype;
    }

    public void setBloodtype(String bloodtype) {
        this.bloodtype = bloodtype;
    }

    public String isFirsttimedonor() {
        return firsttimedonor;
    }

    public void setFirsttimedonor(String firsttimedonor) {
        this.firsttimedonor = firsttimedonor;
    }

    public int getDonorid() {
        return donorid;
    }

    public void setDonorid(int donorid) {
        this.donorid = donorid;
    }
}

