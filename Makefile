# Variables
JFLAGS = -g
JC = javac
JVM = java
MAIN = Main

# Source files
SRCS = Main.java Employee.java Department.java

# Default target: Compile everything
default: classes

# Rule to compile .java files into .class files
classes: $(SRCS:.java=.class)

.SUFFIXES: .java .class

.java.class:
	$(JC) $(JFLAGS) $*.java

# Target to run the program
run: classes
	$(JVM) $(MAIN)

# Target to clean up compiled files
clean:
	$(RM) *.class