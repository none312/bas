CMSC 312 – Operating System Simulator Project
=============================================
Authors: Khanh Tran, Shilpa Kalisetty
------------------------------------

**PROJECT DESCRIPTION**

This project is a simulation of an Operating System, written in Java language. The whole OS contained a virtual memory of 256kB. It mainly uses the priority-queue for the scheduling algorithms implementation. We used the JAVAFX API vs the JAVAX API to represent our GUI + command interface display of all the processes with (wait time, turnaround time, arrival time, burst time) and the scheudling algorithms to make it appear as a rich application.

**SCHEDULER**

Our scheduler keeps track of all the processes that is being executed along with the states: ready state, run state, execute state, waiting state. We have implemented the First Come First Serve(FCFS) and the Round Robin(RR) Scheduling algorithms. First Come First Serve uses the FIFO data structure when scheduling processes. Round Robin is a pre-emptive scheduling algorithm since the scheduler kicks the processes out of the CPU once the time limit has been met.

**GUI**

The GUI consists of the input command(textfield), the output command, the PCB table, the Gantt Chart, and the Statistics(progress of the simulation) that occur throughout the simulation of this project. The statistics involves the calculation of the average waiting time and the average turnaround time for the CPU utilization of each processes.

**COMMANDS**

LOAD--- loads the job file to start the simulation process + PCB + Memory Usage

EXE--user assigns how many cycles needed to run before an I/O pause/interrupt and this will also be returned to the commandInterface if there are no processes left in the ready queue--> needed to be scheduled to execute

RESET--resets the clock and all of the programs--terminates all the processes and sets the clock to 0.

EXIT--exits out the simulation--> "EXIT_ON CLOSE"

PROC--all of the processes needed to execute is displayed

MEM--the amount of memory that is available + used
