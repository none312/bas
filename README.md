CMSC 312 – Operating System Simulator Project

Authors: Khanh Tran, Shilpa Kalisetty

PROJECT DESCRIPTION

This project is a simulation of an Operating System implemented in Java language. The whole project conisted of a virtual memory of 256kB.



SCHEDULER

Our scheduler keeps track of all the processes that is being executed along with the states: ready state, run state, execute state, waiting state. We have implemented the First Come First Serve(FCFS) and the Round Robin(RR) Scheduling algorithms. First Come First Serve uses the FIFO data structure when scheduling processes. Round Robin is a pre-emptive scheduling algorithm since the scheduler kicks the processes out of the CPU once the time limit has been met. 

GUI

The GUI consists of the input command(textfield), the output command, the PCB table, the Gantt Chart, and the Statistics that occur throughout the simulation of this project.


COMMANDS 

load

exe

reset

exit

executeCmd

proc

createProcTable

