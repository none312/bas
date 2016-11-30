CMSC 312 – Operating System Simulator Project
Authors: Khanh Tran, Shilpa Kalisetty

SUMMARY

The Operating System Simulator runs with a limited 256kB memory

SCHEDULER
Our scheduler keeps track of all the processes that is being executed along with the states: ready state, run state, execute state, waiting state. 
We have implemented the First Come First Serve(FCFS) and the Round Robin(RR) Scheduling algorithms. First Come First Serve uses the FIFO data structure when scheduling processes. Round Robin is a pre-emptive scheduling algorithm since the scheduler kicks the processes out of the CPU once the quantum

GUI
The GUI consists of the PCB table, the PROC table, the Gantt Chart, and the Statistics that occur throughout the simulation of this project.
