package QAP.Manager.Test;

import QAP.Domain.Appointment.BloodDonationAppointment;
import QAP.Domain.Appointment.AppointmentSlot;
import QAP.Domain.Database;
import QAP.Domain.Donor.BloodDonor;
import QAP.Manager.BloodDonationAppointmentManager;
import QAP.Manager.InvalidDonationSchedulingException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.util.ArrayList;

@ExtendWith(MockitoExtension.class)
public class BloodDonationAppointmentManagerTest {

    @Mock
    private Database databaseMock;

    @Test
    public void Donation() {
        BloodDonor Donation = new BloodDonor();
        Donation.setId(1);
        Donation.setBloodtype("O");
        Donation.setFirstname("Carl");
        Donation.setLastname("Carlson");
        Donation.setDateofbirth(LocalDate.of(1998, Month.MARCH, 07));
        Mockito.when(databaseMock.getDonor()).thenReturn(Donation);

        BloodDonationAppointment BloodDonation = new BloodDonationAppointment();
        BloodDonation.setAppointmentduration(LocalTime.of(00, 30));
        BloodDonation.setFirsttimedonor("True");

        ArrayList<AppointmentSlot> appointmentSlots = new ArrayList<AppointmentSlot>();
        AppointmentSlot appointmentSlot = new AppointmentSlot();
        appointmentSlot.setId(1);
        appointmentSlot.setLocation("59 Goat St. Dildo NL");
        appointmentSlot.setBloodtype("O");
        appointmentSlots.add(appointmentSlot);
        Mockito.when(databaseMock.getAppointmentSlots()).thenReturn(appointmentSlots);
        BloodDonationAppointmentManager bloodDonationAppointmentManager = new BloodDonationAppointmentManager(databaseMock);
        try{
            BloodDonationAppointment bloodDonationAppointment = bloodDonationAppointmentManager.bookAppointment(1);
        } catch(InvalidDonationSchedulingException e){
            Assertions.assertTrue(e.getMessage().equalsIgnoreCase("Appointment not made"));
        }
    }

    @Test
    public void MatchingBloodType() {
        BloodDonor MatchingDonation = new BloodDonor();
        MatchingDonation.setId(1);
        MatchingDonation.setBloodtype("O");
        MatchingDonation.setFirstname("Carl");
        MatchingDonation.setLastname("Carlson");
        MatchingDonation.setDateofbirth(LocalDate.of(1998, Month.MARCH, 07));
        Mockito.when(databaseMock.getDonor()).thenReturn(MatchingDonation);

        ArrayList<AppointmentSlot> appointmentSlots = new ArrayList<AppointmentSlot>();
        AppointmentSlot appointmentSlot = new AppointmentSlot();
        appointmentSlot.setId(1);
        appointmentSlot.setLocation("59 Goat St. Dildo NL");
        appointmentSlot.setBloodtype("O");
        appointmentSlots.add(appointmentSlot);
        Mockito.when(databaseMock.getAppointmentSlots()).thenReturn(appointmentSlots);
        BloodDonationAppointmentManager bloodDonationAppointmentManager = new BloodDonationAppointmentManager(databaseMock);
        try{
            BloodDonationAppointment bloodDonationAppointment = bloodDonationAppointmentManager.bookAppointment(1);
        } catch(InvalidDonationSchedulingException e){
            Assertions.assertTrue(e.getMessage().equalsIgnoreCase("Invalid blood type"));
        }
    }

    @Test
    public void UnderAgeTest() {
        BloodDonor UnderAgeTest = new BloodDonor();
        UnderAgeTest.setId(1);
        UnderAgeTest.setFirstname("Lenny");
        UnderAgeTest.setLastname("Leonard");
        UnderAgeTest.setDateofbirth(LocalDate.of(2018, Month.JULY, 21));
        Mockito.when(databaseMock.getDonor()).thenReturn(UnderAgeTest);
        BloodDonationAppointmentManager bloodDonationAppointmentManager = new BloodDonationAppointmentManager(databaseMock);
        try{
            BloodDonationAppointment bloodDonationAppointment = bloodDonationAppointmentManager.bookAppointment(1);
        }catch(InvalidDonationSchedulingException e){
            Assertions.assertTrue(e.getMessage().equalsIgnoreCase("Underage"));
        }
    }

    @Test
    public void OverAgeTest() {
        BloodDonor OverAgeTest = new BloodDonor();
        OverAgeTest.setId(1);
        OverAgeTest.setFirstname("Chief");
        OverAgeTest.setLastname("Wiggum");
        OverAgeTest.setDateofbirth(LocalDate.of(1910, Month.NOVEMBER, 22));
        Mockito.when(databaseMock.getDonor()).thenReturn(OverAgeTest);
        BloodDonationAppointmentManager bloodDonationAppointmentManager = new BloodDonationAppointmentManager(databaseMock);
        try{
            BloodDonationAppointment bloodDonationAppointment = bloodDonationAppointmentManager.bookAppointment(1);
        }catch(InvalidDonationSchedulingException e){
            Assertions.assertTrue(e.getMessage().equalsIgnoreCase("Overage!"));
        }
    }

    @Test
    public void UnMatchingBloodType() {
        BloodDonor UnMatchingBloodType = new BloodDonor();
        UnMatchingBloodType.setId(1);
        UnMatchingBloodType.setBloodtype("B");
        UnMatchingBloodType.setFirstname("Nelson");
        UnMatchingBloodType.setLastname("Muntz");
        UnMatchingBloodType.setDateofbirth(LocalDate.of(1989, Month.OCTOBER, 15));
        Mockito.when(databaseMock.getDonor()).thenReturn(UnMatchingBloodType);

        ArrayList<AppointmentSlot> appointmentSlots = new ArrayList<AppointmentSlot>();
        AppointmentSlot appointmentSlot = new AppointmentSlot();
        appointmentSlot.setId(1);
        appointmentSlot.setLocation("59 Goat St. Dildo NL");
        appointmentSlot.setBloodtype("A");
        appointmentSlots.add(appointmentSlot);
        Mockito.when(databaseMock.getAppointmentSlots()).thenReturn(appointmentSlots);
        BloodDonationAppointmentManager bloodDonationAppointmentManager = new BloodDonationAppointmentManager(databaseMock);
        try{
            BloodDonationAppointment bloodDonationAppointment = bloodDonationAppointmentManager.bookAppointment(1);
        } catch(InvalidDonationSchedulingException e){
            Assertions.assertTrue(e.getMessage().equalsIgnoreCase("Invalid blood type"));
        }
    }

    @Test
    public void TooEarlyTest(){
        BloodDonor TooEarlyTest = new BloodDonor();
        TooEarlyTest.setId(1);
        TooEarlyTest.setBloodtype("O");
        TooEarlyTest.setFirstname("Fat");
        TooEarlyTest.setLastname("Tony");
        TooEarlyTest.setDateofbirth(LocalDate.of(1992, Month.JUNE, 11));
        TooEarlyTest.setLastdonationdate(LocalDate.of(2021, Month.JULY, 8));
        TooEarlyTest.setNextappointment(LocalDate.of(2021, Month.JULY, 9));
        Mockito.when(databaseMock.getDonor()).thenReturn(TooEarlyTest);

        BloodDonationAppointment BloodDonation = new BloodDonationAppointment();
        BloodDonation.setAppointmentduration(LocalTime.of(12, 0));
        BloodDonation.setFirsttimedonor("False");

        BloodDonationAppointmentManager bloodDonationAppointmentManager = new BloodDonationAppointmentManager(databaseMock);
        try{
            BloodDonationAppointment bloodDonationAppointment = bloodDonationAppointmentManager.bookAppointment(1);
        }catch(InvalidDonationSchedulingException e){
            Assertions.assertTrue(e.getMessage().equalsIgnoreCase("Too early"));
        }
    }

    @Test
    public void TooLateTest(){
        BloodDonor TooLateTest = new BloodDonor();
        TooLateTest.setId(1);
        TooLateTest.setBloodtype("A");
        TooLateTest.setFirstname("Gil");
        TooLateTest.setLastname("Gunderson");
        TooLateTest.setDateofbirth(LocalDate.of(1991, Month.MAY, 27));
        TooLateTest.setLastdonationdate(LocalDate.of(2021, Month.APRIL, 25));
        TooLateTest.setNextappointment(LocalDate.of(2029, Month.JANUARY, 11));
        Mockito.when(databaseMock.getDonor()).thenReturn(TooLateTest);

        BloodDonationAppointment BloodDonation = new BloodDonationAppointment();
        BloodDonation.setAppointmentduration(LocalTime.of(12, 0));
        BloodDonation.setFirsttimedonor("False");

        BloodDonationAppointmentManager bloodDonationAppointmentManager = new BloodDonationAppointmentManager(databaseMock);
        try{
            BloodDonationAppointment bloodDonationAppointment = bloodDonationAppointmentManager.bookAppointment(1);
        }catch(InvalidDonationSchedulingException e){
            Assertions.assertTrue(e.getMessage().equalsIgnoreCase("Too late"));
        }
    }

    @Test
    public void TooManyAppointments() {
        BloodDonor TooManyAppointments = new BloodDonor();
        TooManyAppointments.setId(1);
        TooManyAppointments.setBloodtype("B");
        TooManyAppointments.setFirstname("Sideshow");
        TooManyAppointments.setLastname("Bob");
        TooManyAppointments.setDateofbirth(LocalDate.of(1993, Month.MARCH, 23));
        TooManyAppointments.setLastdonationdate(LocalDate.of(2021, Month.JUNE, 22));
        TooManyAppointments.setNextappointment(LocalDate.of(2022, Month.FEBRUARY, 20));
        Mockito.when(databaseMock.getDonor()).thenReturn(TooManyAppointments);

        BloodDonationAppointment BloodDonation = new BloodDonationAppointment();
        BloodDonation.setAppointmentduration(LocalTime.of(12, 0));
        BloodDonation.setFirsttimedonor("False");

        ArrayList<AppointmentSlot> appointmentSlots1 = new ArrayList<AppointmentSlot>();
        AppointmentSlot appointmentSlot = new AppointmentSlot();
        appointmentSlot.setId(1);
        appointmentSlot.setLocation("59 Goat St. Dildo NL");
        appointmentSlot.setBloodtype("B");
        appointmentSlots1.add(appointmentSlot);

        ArrayList<AppointmentSlot> appointmentSlots2 = new ArrayList<AppointmentSlot>();
        AppointmentSlot appointmentSlot2 = new AppointmentSlot();
        appointmentSlot2.setId(1);
        appointmentSlot2.setLocation("59 Goat St. Dildo NL");
        appointmentSlot2.setBloodtype("B");
        appointmentSlots2.add(appointmentSlot);
        Mockito.when(databaseMock.getAppointmentSlots()).thenReturn(appointmentSlots2);
        BloodDonationAppointmentManager bloodDonationAppointmentManager = new BloodDonationAppointmentManager(databaseMock);
        try{
            BloodDonationAppointment bloodDonationAppointment = bloodDonationAppointmentManager.bookAppointment(1);
        } catch(InvalidDonationSchedulingException e){
            Assertions.assertTrue(e.getMessage().equalsIgnoreCase("Appointment already made"));
        }
    }
}
