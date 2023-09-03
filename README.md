# Plane Management
A project using the concepts of a graph and graph traversal to route 
aircraft along a graph as far as they can go for 24 hours using CSV 
data and a REPL interface.

Takes in 1 CSV with routes and another with aircraft and turns the 
route CSV into a graph while storing information about aircraft 
separately. When routes are assigned, BFS is used to route aircraft up 
until an aircraft hits a 24 hour total flying time. BFS was used to 
create the best path possible.

REPL Instructions:

Load command:
load [routes file] [aircraft file]

Assign Command:
assign