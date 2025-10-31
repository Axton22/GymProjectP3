
package com.gym.gymclient.Entity;


public class GymRutinaEntity {
    private Long routineID;
    private String routineName;
    private String dayWeek;
    private String starHour;
    private String endHour;

    public GymRutinaEntity() {}

    public GymRutinaEntity(String routineName, String dayWeek, String starHour, String endHour) {
        this.routineName = routineName;
        this.dayWeek = dayWeek;
        this.starHour = starHour;
        this.endHour = endHour;
    }

    public GymRutinaEntity(Long routineID, String routineName, String dayWeek, String starHour, String endHour) {
        this.routineID = routineID;
        this.routineName = routineName;
        this.dayWeek = dayWeek;
        this.starHour = starHour;
        this.endHour = endHour;
    }

    public Long getRoutineID() { return routineID; }
    public void setRoutineID(Long routineID) { this.routineID = routineID; }

    public String getRoutineName() { return routineName; }
    public void setRoutineName(String routineName) { this.routineName = routineName; }

    public String getDayWeek() { return dayWeek; }
    public void setDayWeek(String dayWeek) { this.dayWeek = dayWeek; }

    public String getStarHour() { return starHour; }
    public void setStarHour(String starHour) { this.starHour = starHour; }

    public String getEndHour() { return endHour; }
    public void setEndHour(String endHour) { this.endHour = endHour; }
}
