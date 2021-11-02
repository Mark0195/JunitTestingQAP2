package QAP.Domain.Donor;

import java.time.LocalDate;

public class BloodDonor {
    private int id;
    private String firstname;
    private String lastname;
    private LocalDate dateofbirth;
    private String bloodtype;
    private LocalDate nextappointment;
    private LocalDate lastdonationdate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public LocalDate getDateofbirth() {
        return dateofbirth;
    }

    public void setDateofbirth(LocalDate dateofbirth) {
        this.dateofbirth = dateofbirth;
    }

    public String getBloodtype() {
        return bloodtype;
    }

    public void setBloodtype(String bloodtype) {
        this.bloodtype = bloodtype;
    }

    public LocalDate getNextappointment() {
        return nextappointment;
    }

    public void setNextappointment(LocalDate nextappointment) {
        this.nextappointment = nextappointment;
    }

    public LocalDate getLastdonationdate() {
        return lastdonationdate;
    }

    public void setLastdonationdate(LocalDate lastdonationdate) {
        this.lastdonationdate = lastdonationdate;
    }
}