package org.example.pojo.model;

public class Elevator {
    private String id;
    private int startedAt = 0;
    private int fromFloor = 0;
    private int toFloor = 0;

    private boolean free = true;
    private String usedBy;

    public Elevator(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public int getToFloor() {
        return toFloor;
    }

    public String getUsedBy() {
        return usedBy;
    }

    public int getAtFloor(int time) {
        if (time < startedAt) {
            return 0;
        } else if (time == startedAt) {
            return fromFloor;
        } else {
            int duration = time-startedAt;
            if (toFloor-fromFloor > duration) {
                return fromFloor+duration;
            } else {
                return toFloor;
            }
        }
    }

    public int direction() {
        if (free) {
            return 0;
        } else {
            return (fromFloor<toFloor) ? 1 : -1;
        }
    }

    public int getTime(int time, int floor) {
        if (free) {
            return Math.abs(floor-fromFloor);
        } else {
            int atFloor = getAtFloor(time);
            if (floor>atFloor) {
                if (direction()>0) {
                    return floor-atFloor;
                } else {
                    return (atFloor-toFloor) + (floor-toFloor);
                }
            } else {
                if (direction()>0) {
                    return (toFloor-atFloor) + (toFloor-floor);
                } else {
                    return atFloor-floor;
                }
            }
        }
    }

    public void useLift(Person person) {
        fromFloor = free ? toFloor: getAtFloor(person.getTime());
        startedAt = free ? person.getTime(): person.getTime();
        toFloor = person.getToFloor();
        free = false;
        usedBy = person.getId();
    }
}
