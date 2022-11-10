# Create your grading script here

CP=".:../lib/hamcrest-core-1.3.jar:../lib/junit-4.13.2.jar"

echo "Getting Submission"
rm -rf student-submission
git clone $1 student-submission 2> /dev/null
cp TestListExamples.java student-submission/
cd student-submission/

# Checking if the submission has the file we are using. If not, exit and give an error message
if [ -f "ListExamples.java" ]; then
	echo "Compiling Files"
else
	echo "WRONG FILENAME! Your submission should have a file named ListExamples.java"
	exit
fi

javac -cp $CP *.java 2> compiler_output.txt

# Checking if there are any compiling errors
if [ $? -eq "0" ]; then
       echo "Code Compiled"	
else
	echo "ERROR COMPILING! See error message below for more details"
	cat compiler_output.txt
	exit
fi

echo "Starting Tests"
java -cp $CP org.junit.runner.JUnitCore TestListExamples > test_output.txt
echo "Finished Tests"

# TNUM is the number of total tests, and ENUM is the number of failed tests
awk -F\. '{print NF-1}' test_output.txt > temp.txt
TNUM=$(sed -e '1d;3,$d' temp.txt)
awk -F\E '{print NF-1}' test_output.txt > temp.txt
ENUM=$(sed -e '1d;3,$d' temp.txt)

# PNUM takes the number of passed tests
PNUM=$(($TNUM - $ENUM))

echo "You have passed $PNUM out of $TNUM tests, giving you a score of $PNUM/$TNUM"
