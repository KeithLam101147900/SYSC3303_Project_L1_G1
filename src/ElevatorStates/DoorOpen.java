package ElevatorStates;

import elevatorSystem.ElevatorCar;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import elevatorSystem.ElevatorState;

public class DoorOpen implements ElevatorState {

    private ElevatorCar elevator;

    public DoorOpen(ElevatorCar elevator){
        this.elevator = elevator;
    }

    @Override
    public void moveElevator(long time) {
        //Close door then move elevator
        elevator.setDoors(false);
        System.out.println("\nElevator "+elevator.getElevatorNumber() +" door is stuck open, closing door before moving.\n");
        this.elevator.setElevatorState(elevator.getDoorClosed());
        this.elevator.moveElevator(time);
    }

    @Override
    public void openDoor() {
        System.out.println("Door already open for Elevator "+elevator.getElevatorNumber());
    }

    @Override
    public void closeDoor() {
        System.out.println("Closing door for Elevator "+elevator.getElevatorNumber());
        elevator.setDoors(false);
        elevator.setElevatorState(elevator.getDoorClosed()); // set to new state
    }

    @Override

    public synchronized void loadElevator(long time) {
        int elevatorNumber = elevator.getElevatorNumber();
        try{
            wait(time);
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
            LocalDateTime now = LocalDateTime.now();
            System.out.println("Loading Elevator "+elevatorNumber+" completed at Time: "+dtf.format(now));
            this.elevator.setElevatorState(elevator.getLoading()); //Set to new state
        }
        catch (Exception e){
            System.out.println("Error occured while loading Elevator in thread.");
            e.printStackTrace();
        }
    }

    @Override
    public void elevatorArrived() {
        System.out.println("Can Not arrive for Elevator "+elevator.getElevatorNumber());

    }

    @Override
    public void elevatorOutOfService() {
        elevator.setDoors(true);
        elevator.setElevatorState(elevator.getOutOfService()); //Set to new state
        System.out.println("Elevator "+elevator.getElevatorNumber()+" is going Out of Service");
    }
}