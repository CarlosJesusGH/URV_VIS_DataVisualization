#
#     NOTE Posts.xml processing is commented out since it takes a long time.
#          Remove the comments to run those programs
#

#
#	the script takes a file with StackOverflow posts,
#	builds a graph of users posting questions and answers, and
#	calculates some basic graph statistics
#
#	requirements:
#	  python installed
#	  Snap.py installed, http://snap.stanford.edu
#	  Posts.xml, https://archive.org/download/stackexchange/stackoverflow.com-Posts.7z, uncompress

echo `date` ... "START analysis of StackOverflow"

# get all the question posts and accepted answers (15min)
echo `date` ... "extracting questions ..."
#python getQuestions.py Posts.xml > questions.txt

# get all the answer posts (15min)
echo `date` ... "extracting answers ..."
#python getAnswers.py Posts.xml > answers.txt

# get all the Java question posts, id only (15min)
# echo `date` ... "identifying Java questions ..."
# python getTag.py Posts.xml java > java.txt
# echo `date` ... "identifying Python questions ..."
# python getTag.py Posts.xml python > python.txt
# echo `date` ... "identifying C++ questions ..."
# python getTag.py Posts.xml c++ > c++.txt
# echo `date` ... "identifying Raspberry questions ..."
# python getTag.py Posts.xml raspberry-pi > raspberry-pi.txt
# echo `date` ... "identifying Arduino questions ..."
# python getTag.py Posts.xml arduino > arduino.txt
# echo `date` ... "identifying Ros questions ..."
# python getTag.py Posts.xml ros > ros.txt
# echo `date` ... "identifying MongoDB questions ..."
# python getTag.py Posts.xml mongodb > mongodb.txt
# echo `date` ... "identifying Android questions ..."
# python getTag.py Posts.xml android > android.txt
# echo `date` ... "identifying Linux questions ..."
# python getTag.py Posts.xml linux > linux.txt


# select questions with a Java tag (20s)
# echo `date` ... "selecting Java questions ..."
# python doJoin.py java.txt questions.txt 1 1 > java-posts.txt
# echo `date` ... "selecting Python questions ..."
# python doJoin.py python.txt questions.txt 1 1 > python-posts.txt
# echo `date` ... "selecting C++ questions ..."
# python doJoin.py c++.txt questions.txt 1 1 > c++-posts.txt
# echo `date` ... "selecting Raspberry questions ..."
# python doJoin.py raspberry-pi.txt questions.txt 1 1 > raspberry-pi-posts.txt
# echo `date` ... "selecting Arduino questions ..."
# python doJoin.py arduino.txt questions.txt 1 1 > arduino-posts.txt
# echo `date` ... "selecting Ros questions ..."
# python doJoin.py ros.txt questions.txt 1 1 > ros-posts.txt
# echo `date` ... "selecting MongoDB questions ..."
# python doJoin.py mongodb.txt questions.txt 1 1 > mongodb-posts.txt
# echo `date` ... "selecting Android questions ..."
# python doJoin.py android.txt questions.txt 1 1 > android-posts.txt
# echo `date` ... "selecting Linux questions ..."
# python doJoin.py linux.txt questions.txt 1 1 > linux-posts.txt

# identify users of accepted answers (40s)
# echo `date` ... "finding owners of accepted answers ..."
# python doJoin.py answers.txt java-posts.txt 1 3 > qa-java.txt
# echo `date` ... "finding owners of accepted answers ..."
# python doJoin.py answers.txt python-posts.txt 1 3 > qa-python.txt
# echo `date` ... "finding owners of accepted answers ..."
# python doJoin.py answers.txt c++-posts.txt 1 3 > qa-c++.txt
# echo `date` ... "finding owners of accepted answers ..."
# python doJoin.py answers.txt raspberry-pi-posts.txt 1 3 > qa-raspberry-pi.txt
# echo `date` ... "finding owners of accepted answers ..."
# python doJoin.py answers.txt arduino-posts.txt 1 3 > qa-arduino.txt
# echo `date` ... "finding owners of accepted answers ..."
# python doJoin.py answers.txt ros-posts.txt 1 3 > qa-ros.txt
# echo `date` ... "finding owners of accepted answers ..."
# python doJoin.py answers.txt mongodb-posts.txt 1 3 > qa-mongodb.txt
# echo `date` ... "finding owners of accepted answers ..."
# python doJoin.py answers.txt android-posts.txt 1 3 > qa-android.txt
# echo `date` ... "finding owners of accepted answers ..."
# python doJoin.py answers.txt linux-posts.txt 1 3 > qa-linux.txt

# create a graph and find top users (5s)
# echo `date` ... "building a QA graph and calculating statistics ..."
#python getStats.py qa.txt 2 6 > stats.txt
#python getStats.py qa.txt 2 11 > stats.txt

echo `date` ... "building a QA graph and calculating statistics ..."
python getStats.py qa-java.txt 2 11 > stats-java.txt
echo `date` ... "building a QA graph and calculating statistics ..."
python getStats.py qa-python.txt 2 11 > stats-python.txt
echo `date` ... "building a QA graph and calculating statistics ..."
python getStats.py qa-c++.txt 2 11 > stats-c++.txt
echo `date` ... "building a QA graph and calculating statistics ..."
python getStats.py qa-raspberry-pi.txt 2 11 > stats-raspberry-pi.txt
echo `date` ... "building a QA graph and calculating statistics ..."
python getStats.py qa-arduino.txt 2 11 > stats-arduino.txt
echo `date` ... "building a QA graph and calculating statistics ..."
python getStats.py qa-ros.txt 2 11 > stats-ros.txt
echo `date` ... "building a QA graph and calculating statistics ..."
python getStats.py qa-mongodb.txt 2 11 > stats-mongodb.txt
echo `date` ... "building a QA graph and calculating statistics ..."
python getStats.py qa-android.txt 2 11 > stats-android.txt
echo `date` ... "building a QA graph and calculating statistics ..."
python getStats.py qa-linux.txt 2 11 > stats-linux.txt

echo `date` ... "results are stored in stats.txt"
echo `date` ... "END   analysis of StackOverflow"
