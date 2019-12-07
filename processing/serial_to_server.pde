/**
 * Simple Read
 * 
 * Read data from the serial port and change the color of a rectangle
 * when a switch connected to a Wiring or Arduino board is pressed and released.
 * This example works with the Wiring / Arduino program that follows below.
 */


import processing.serial.*;

Serial myPort;  // Create object from Serial class
Serial myPort2;  
Serial myPort3;
float tp;
int[] pos;
float hum;
int light;
int sound;
float tp1;
int[] pos1;
float hum1;
int light1;
int sound1;
int count=0;
int state=0;
int state1=0;
int state2=0;
int state3=0;

void setup() 
{
  size(1000, 1000);
  //String portName3 = Serial.list()[2];
  myPort = new Serial(this, "COM3", 9600);
  myPort2 = new Serial(this, "COM8", 9600);
  //myPort3 = new Serial(this,portName3, 9600);
  pos= new int[12];
  pos1= new int[12];
  frameRate(1);
  smooth();
  fill(0);
  background(255);
  stroke(0);
  fill(255);
  rect(5,5, width-20, height*0.2);
  rect(5,210, width-20, height*0.2);
  rect(5,415, width-20, height*0.2);
  rect(5,620, width-20, height*0.2);
  line(70,45,70,190);
  line(70,158,width-100,158);
  line(70,250,70,395);
  line(70,380,width-100,380);
  line(70,455,70,600);
  line(70,585,width-100,585);
  line(70,660,70,805);
  line(70,790,width-100,790);
}

void axis(int start,float a1,float a2,float a3,float a4, String x) {
  fill(0);  
  textSize(12);
  text(a1+""+x,15,start+55);
  text(a2+""+x,15,start+95);
  text(a3+""+x,15,start+135);
  text(a4+""+x,15,start+175);
}
void axis(int start,int a1,int a2,int a3,int a4, String x) {
  fill(0);  
  textSize(12);
  text(a1+""+x,15,start+55);
  text(a2+""+x,15,start+95);
  text(a3+""+x,15,start+135);
  text(a4+""+x,15,start+175);
}
  
void graph() {
  stroke(255);
  fill(255);
  rect(10,10, width-60,30);    
  rect(10,10, 58,190);
  rect(10,215, width-60,30);    
  rect(10,215, 58,190);
  rect(10,420, width-60,30);    
  rect(10,420, 58,190);  
  rect(10,625, width-60,30);    
  rect(10,625, 58,190);
  
  axis(5,50.0,30.0,10.0,-10.0,"°C");
  axis(210,100.0,66.0,33.0,0.0,"%");
  axis(415,100,66,33,0,"%");  
  axis(620,100,66,33,0,"/min");
  
  fill(255, 0, 0);  
  textSize(26);
  text("Temperature : "+tp+" °C",10,30);  
  fill(0, 0, 255);  
  text("Humadity : "+hum+" %",10,235);  
  fill(255, 200, 0);  
  text("Light : "+light+" %",10,440);    
  fill(0, 100, 100);  
  text("Sound : "+sound+" /min",10,645);
  fill(0);  
  count+=3;
  stroke(255,0,0);
  fill(255, 0, 0);
  strokeWeight(3.0f);
  point(73+count, 190-190/6-(145/60*tp));
  stroke(0,0,255);
  fill(0, 0, 255);
  point(73+count, 380-(hum/100)*120);
  stroke(255,200,0);
  fill(255, 200, 0);
  point(73+count, 585-(light/100.0)*120);
  stroke(0,100,100);
  fill(0, 100, 100);    
  point(73+count, 790-(sound/100.0)*120);
  if(count>width-200) {
    count=0;
  }
}
void draw() {  
  if ( myPort.available() > 0 && myPort2.available()>0) {  // If data is available,    
    String data = myPort.readStringUntil('\n');     // read it and store it in val
    String a [] = data.split(",");
    String data2 = myPort2.readStringUntil('\n');
    String b [] = data2.split(",");
    /*
    String data3 = myPort3.readStringUntil('\n');
    String c [] = data3.split(",");*/
    if(a[0].equals("GRAPH")) {
      light = int(a[2]);
      sound = int(a[4]);
      tp = float(a[6]);
      hum = float(a[8]);
      state=1;
    }
    if(b[0].equals("GRAPH")) {
      int i=0;
      for(i=0;i<12;i++) {
        pos[i]= int(b[2*i+2]);
        
      }
      state1=1;
    }
    if(a[0].equals("APP")) {
      light1 = int(a[2]);
      sound1 = int(a[4]);
      tp1 = float(a[6]);
      hum1 = float(a[8]);
      state2=1;
    }
    if(b[0].equals("APP")) {
      int i=0;
      for(i=0;i<12;i++) {
        pos1[i]= int(b[2*i+2]);
      }
      state3=1;
    }
    
    println(state+" "+state1);
    if(state ==1 && state1 == 1){
      graph();
      state=0;
      state1=0;
    }
    if(state2 == 1&&state3 == 1) {
      String url = "http://localhost/test6.php/?&temperature="+tp1+"&humadity="+hum1+"&light="+light1+"&sound="+sound1+"&pos1="+pos1[0]+"&pos2="+pos1[1]+"&pos3="+pos1[2]+"&pos4="+pos1[3]+"&pos5="+pos1[4]+"&pos6="+pos1[5]+"&pos7="+pos1[6]+"&pos8="+pos1[7]+"&pos9="+pos1[8]+"&pos10="+pos1[9]+"&pos11="+pos1[10]+"&pos12="+pos1[11];
      String[] lines = loadStrings(url);
      state2=0;
      state3=0;
      println(url);
      println(lines[0]);
    }       
  }
}
