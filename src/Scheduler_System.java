import java.util.ArrayList;
import java.util.HashMap;

/**
 * Scheduler class
 */
public class Scheduler_System implements Runnable{
    private ArrayList<ElevatorCar> elevators;
    private ArrayList<Floor> floors;
    private Elevator_System elevator_system;
    private ArrayList<Task> tasksQueue;
    private HashMap<Integer,ArrayList<Integer>> scheduledQueue;  //Key is Elevator Number, Values are next floor numbers to go to.

    /** Constructor for Scheduler_System */
    public Scheduler_System(ArrayList<ElevatorCar> elevators, ArrayList<Floor> floors, Elevator_System elevator_system){
        this.elevators = elevators;
        this.elevator_system = elevator_system;
        this.floors = floors;
        this.tasksQueue = new ArrayList<>();
        for (int i = 0; i < this.elevators.size(); i++) {
            scheduledQueue.put(i,new ArrayList<>());
        }
    }

    /**Get Scheduled tasks from Scheduler for a given elevator number*/
    public ArrayList<Task> getScheduledQueue(Integer elevatorNumber){return this.getScheduledQueue(elevatorNumber);}

    /**Add task to queue for Scheduler and schedule it*/
    public void addToQueue(Task task){
        tasksQueue.add(task);
        scheduleTask(task);
    }

    /**Remove task from all queues after finsihing a task*/
    public void removeFromQueue(Task task){
        tasksQueue.remove(task);
        scheduledQueue.remove(task);
    }

    /** Schedule tasks based off elevators & floors data */
    public void scheduleTask(Task task){
        //Get All Elevators position and status
        Integer bestElevatorNumber = 0;
        Integer bestTaskNumber = getTaskNumber(bestElevatorNumber);

        //Floor task
        if(task.getIsFloorTask()){
            if(elevators.size() > 1) {
                for (int i = 0; i < elevators.size(); i++) {
                    Integer currentTaskNumber = getTaskNumber(i);
                    if(bestTaskNumber > currentTaskNumber){
                        bestTaskNumber = currentTaskNumber;
                        bestElevatorNumber = i;
                    }
                }
            }
        }
        //Elevator Task
        else{
            bestElevatorNumber = task.getElevatorNumber();
            bestTaskNumber = getTaskNumber(bestElevatorNumber);
        }
        //Add task to scheduled Task list for best elevator number.
        ArrayList<Integer> queue = scheduledQueue.get(bestElevatorNumber);
        queue.add(bestTaskNumber,task.getFloorNumber());
        scheduledQueue.replace(task.getElevatorNumber(),queue);
    }

    /** Method to get position of task if added to a given elevator number to schedule most the recent task */
    public int getTaskNumber(Integer elevatorNumber){
        //Target Floor number of latest task added and status of elevator.
        Integer targetFloorNumber = tasksQueue.get(tasksQueue.size() - 1).getFloorNumber();
        Float elevatorPosition = elevators.get(elevatorNumber).getPosition();
        ArrayList<Integer> queue = scheduledQueue.get(elevatorNumber);
        Boolean condition1 = false;
        Boolean condition2 = false;
        Integer bestTaskNumber = 0;

        //Iterate task number until condition1 or condition2 becomes true, Or until queue.size reaches end.
        for (int i = 0; !condition1 && !condition2 && (i < queue.size()); i++){
            condition1 = (elevatorPosition > queue.get(i))&&(targetFloorNumber > queue.get(i))&&(elevatorPosition > targetFloorNumber);
            condition2 = (elevatorPosition < queue.get(i))&&(targetFloorNumber < queue.get(i))&&(elevatorPosition < targetFloorNumber);
            bestTaskNumber = i;
        }
        return bestTaskNumber;
    }

    @Override
    public void run() {
        while (true){
            //Wait until new task is added or task is complete
            //Then schedule tasks
            //Notify Elevator system
        }
    }
}
