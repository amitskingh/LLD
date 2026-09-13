package model;

import spot.ParkingSpot;

import java.time.LocalDateTime;

public class ParkingTicket {

    private final String ticketId;
    private final Vehicle vehicle;
    private final ParkingSpot parkingSpot;
    private final LocalDateTime entryTime;

    private LocalDateTime exitTime;
    private TicketStatus status;

    public ParkingTicket(
            String ticketId,
            Vehicle vehicle,
            ParkingSpot parkingSpot,
            LocalDateTime entryTime) {

        this.ticketId = ticketId;
        this.vehicle = vehicle;
        this.parkingSpot = parkingSpot;
        this.entryTime = entryTime;

        this.status = TicketStatus.ACTIVE;
    }

    public void markPaid() {
        if (status != TicketStatus.ACTIVE) {
            throw new IllegalStateException(
                    "Only an active ticket can be paid"
            );
        }

        status = TicketStatus.PAID;
    }

    public void complete(LocalDateTime exitTime) {
        if (status != TicketStatus.PAID) {
            throw new IllegalStateException(
                    "Only a paid ticket can be completed"
            );
        }

        this.exitTime = exitTime;
        this.status = TicketStatus.COMPLETED;
    }

    public String getTicketId() {
        return ticketId;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public ParkingSpot getParkingSpot() {
        return parkingSpot;
    }

    public LocalDateTime getEntryTime() {
        return entryTime;
    }

    public LocalDateTime getExitTime() {
        return exitTime;
    }

    public TicketStatus getStatus() {
        return status;
    }
}
