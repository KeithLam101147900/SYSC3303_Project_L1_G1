package tests;

import static org.junit.Assert.*;

import elevatorSystem.*;
import org.junit.Test;

import java.util.ArrayList;

public class Test_ElevatorStates {

    @Test
    public void invalidDoorOpeningTest(){
        Elevator_System elevator_system = new Elevator_System(1,2, true);
        ElevatorCar elevator = elevator_system.getElevators()[0];
        //State is set to MovingUp, Elevator should not open door
        elevator.setElevatorState(elevator.getMovingUp());
        elevator.openDoor(); //Try to open elevator door
        assert(elevator.getDoors() == false); //Test elevator door didn't open
    }

    @Test
    public void invalidLoadingTest(){
        Elevator_System elevator_system = new Elevator_System(1,2, true);
        ElevatorCar elevator = elevator_system.getElevators()[0];
        //State is set to MovingUp, Elevator should not open door to load elevator
        elevator.setElevatorState(elevator.getMovingUp());
        elevator.loadElevator(1000); //Try to load elevator
        assert(elevator.getDoors() == false); //Test elevator door didn't open
    }

    @Test
    public void arrivedTest(){
        Elevator_System elevator_system = new Elevator_System(1,2, true );
        ElevatorCar elevator = elevator_system.getElevators()[0];
        //State is set to MovingUp, Elevator should not open door
        elevator.setElevatorState(elevator.getMovingUp());
        elevator.setPosition(3);
        ArrayList<ElevatorAction> tasks = new ArrayList<>();
        tasks.add(new ElevatorAction(1,0));
        elevator.setTasks(tasks);
        elevator.elevatorArrived();
        assert(elevator.getPosition() == 1.0); //Test elevator did move to 1 after calling elevatorArrived()
    }
}