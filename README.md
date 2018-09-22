## To-Do List
Console simple To-Do list   

### How dependencies are added in a jar?
`pom.xml` include [maven-dependency-plugin](https://github.com/apache/maven-dependency-plugin) to copy all dependencies to target/lib/ folder, and used [maven-jar-plugin](https://github.com/apache/maven-jar-plugin) to added the dependency classpath.

### How to run
For compile used Maven.   
Build project with Maven (dependencies auto added to the lib folder next to the jar file)   
And run: `java -jar simple-todo-list-0.0.1.jar`

### Available commands
**add** - for adding a new task  
`add 'title' 'description' date`   

Date in the format: 'dd.mm.yyyy hh:mm', without single quotes.   
For entering an argument which has space in it you'll have to enclose it in single quotes 'arg with space'. 
Same thing with the description.

**delete** - for deleting a task by name   
`delete 'title'`   
If there are several tasks with a similar name, the program will ask: delete everything or delete a specific task.  
If enter YES, then all tasks are deleted and if enter NO, date will be requested (in the format: 'dd.mm.yyyy hh:mm', without single quotes)


**print** - for prints all created tasks   
`print`  

**find** - for word search in description   
`find 'word'`   
 
### Features
logging.log - log file   
data.ser - file with saved tasks (used serialization and deserialization )
