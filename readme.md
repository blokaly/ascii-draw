# ASCII Draw

__Description__

This simple console drawing program works as follows:
 1. Create a new canvas
 2. Draw on the canvas by issuing various commands
 3. Quit

__Supported Commands__

|Command 		|Description|
|----|----|
|C w h          | Create a new canvas of width w and height h.|
|L x1 y1 x2 y2  | Draw a new line from (x1,y1) to (x2,y2). Currently, only|
|               | horizontal or vertical lines are supported. Horizontal and vertical lines|
|               | will be drawn using the 'x' character.|
|R x1 y1 x2 y2  | Draw a rectangle whose upper left corner is (x1,y1) and|
|               | lower right corner is (x2,y2). Horizontal and vertical lines will be drawn|
|               | using the 'x' character.|
|B x y c        | Fill the entire area connected to (x,y) with "colour" c. The|
|               | behaviour of this is the same as that of the "bucket fill" tool in paint|
|               | programs.|
|Q              | Quit|

__How-To  Run__

1. **gradle wrapper:** 
    
    Under the project folder, run `> gradlew run`
   
2. **IDE:**    

    Run the `Main` class under src/main/java folder
    
3. **Executable file**

     unzip `ascii-draw-1.0.zip` or untar `ascii-draw-1.0.tar` under distribution folder,
     it will create a ascii-draw-1.0 folder. Then run the `ascii-draw` executable under the bin folder.

