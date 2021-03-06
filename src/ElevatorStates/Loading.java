package ElevatorStates;

import elevatorSystem.ElevatorCar;
import elevatorSystem.ElevatorState;

public class Loading implements ElevatorState {

    private ElevatorCar elevator;

    public Loading(ElevatorCar elevator){
        this.elevator = elevator;
    }

    @Override
    public void moveElevator(long time) {
        System.out.println("Can Not move, Loading and door open for Elevator "+elevator.getElevatorNumber());
    }

    @Override
    public void openDoor() {
        System.out.println("Door already open and Loading for Elevator "+elevator.getElevatorNumber());
    }

    @Override
    public void closeDoor() {
        elevator.setDoors(false);
        elevator.setElevatorState(elevator.getDoorClosed()); // Set new state
    }

    @Override
    public void loadElevator(long time) {
        System.out.println("Already loading for Elevator "+elevator.getElevatorNumber());
    }

    @Override
    public void elevatorArrived() {
        System.out.println("Can not arrive, Loading for Elevator "+elevator.getElevatorNumber());
    }

    @Override
    public void elevatorOutOfService() {
        elevator.setDoors(true);
        elevator.setElevatorState(elevator.getOutOfService()); //Set to new state
        System.out.println("Elevator "+elevator.getElevatorNumber()+" is going Out of Service");
    }

}
