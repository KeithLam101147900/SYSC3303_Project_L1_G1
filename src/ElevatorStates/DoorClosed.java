package ElevatorStates;

import elevatorSystem.ElevatorCar;
import elevatorSystem.ElevatorState;

public class DoorClosed implements ElevatorState {

    private ElevatorCar elevator;

    public DoorClosed(ElevatorCar elevator){
        this.elevator = elevator;
    }

    @Override
    public synchronized void moveElevator(long time) {
        int elevatorNumber = elevator.getElevatorNumber();
        float startLocation = elevator.getPosition();
        int endLocation = elevator.getTasks().get(0);
        elevator.setMotors(true);

        if(startLocation < endLocation){
            System.out.println("Elevator "+elevatorNumber+" now Moving Up to floor "+endLocation);
            elevator.setElevatorState(elevator.getMovingUp());
        }
        else{
            System.out.println("Elevator "+elevatorNumber+" now Moving Down to floor "+endLocation);
            elevator.setElevatorState(elevator.getMovingDown());
        }

        //Wait for calculated time
        try{ wait(time); }
        catch (Exception e){}



        elevator.setMotors(false);
        elevator.setStatus("Stopped");
        //Set elevator to new position and remove task from Elevator's queue
        elevator.setPosition(endLocation);
        elevator.setButton(endLocation, false);
        elevator.getTasks().remove(0);
        System.out.println("Elevator "+elevatorNumber+" now arrived floor "+endLocation);
        //Set to new state
        elevator.setElevatorState(elevator.getArrived());
    }

    @Override
    public void openDoor() {
        System.out.println("Door opening");
        elevator.setElevatorState(elevator.getDoorOpen()); //Set to new state
    }

    @Override
    public void closeDoor() {
        System.out.println("Door already closed for Elevator "+elevator.getElevatorNumber());
    }

    @Override
    public synchronized void loadElevator(long time) {
        System.out.println("Can Not load, Door closed for Elevator "+elevator.getElevatorNumber());
    }

    @Override
    public void elevatorArrived() {
        System.out.println("Can Not arrive, Door closed for Elevator "+elevator.getElevatorNumber());
    }
}
