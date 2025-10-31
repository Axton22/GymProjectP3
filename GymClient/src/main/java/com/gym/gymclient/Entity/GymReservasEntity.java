package com.gym.gymclient.Entity;


public class GymReservasEntity {

    private Long reservationID;
    private Long clientID;
    private Long routineID;
    private String reservationDate;   // como String para facilitar JSON/JavaFX
    private String reservationHour;   // como String (HH:mm) igual
    private String state;

    public GymReservasEntity() {}

    public GymReservasEntity(String reservationDate, String reservationHour, String state) {

        this.reservationDate = reservationDate;
        this.reservationHour = reservationHour;
        this.state = state;
    }

    public GymReservasEntity(Long reservationID, Long clientID, Long routineID, String reservationDate, String reservationHour, String state) {
        this.reservationID = reservationID;
        this.clientID = clientID;
        this.routineID = routineID;
        this.reservationDate = reservationDate;
        this.reservationHour = reservationHour;
        this.state = state;
    }

    public Long getReservationID() { return reservationID; }
    public void setReservationID(Long reservationID) { this.reservationID = reservationID; }

    public Long getClientID() { return clientID; }
    public void setClientID(Long clientID) { this.clientID = clientID; }

    public Long getRoutineID() { return routineID; }
    public void setRoutineID(Long routineID) { this.routineID = routineID; }

    public String getReservationDate() { return reservationDate; }
    public void setReservationDate(String reservationDate) { this.reservationDate = reservationDate; }

    public String getReservationHour() { return reservationHour; }
    public void setReservationHour(String reservationHour) { this.reservationHour = reservationHour; }

    public String getState() { return state; }
    public void setState(String state) { this.state = state; }
}
