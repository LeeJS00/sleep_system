# sleep_system

## Introduction
This system is about sleep surrounding sensing and showing on application

## PHP
In php folder, there is 2 php codes 

1. giving data to server [test6.php]
2. get data from server as json type [get_data.php]

## Processing
From arduino serial ports, processing codes get data and plot the point graph.
Also processing code has connection between mysql and serial ports.

## Mysql
create table sensor (temperature float(5,2), humadity float(5,2) light int(5), sound int(5), pos1 int(5) ... pos 12 int(5));
select * from sensor;
drop table sensor;

## Version
### Processing
3.5.3 (3 February 2019)
### Andrioid Studio
Android Studio 3.5.2
Build #AI-191.8026.42.35.5977832, built on October 31, 2019
JRE: 1.8.0_202-release-1483-b03 amd64
JVM: OpenJDK 64-Bit Server VM by JetBrains s.r.o
Windows 10 10.0

### Server
XAMPP Control Panel v3.2.4  
Making localhost 
Use apache and mysql. Turn on that 2 buttons.



