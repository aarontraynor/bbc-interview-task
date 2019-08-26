# URL Response Processor
This program is written in Java using Gradle to manage the build process and JUnit to implement unit tests of the
software.

A copy of this repository is available at https://github.com/aarontraynor/bbc-interview-task

## Setting up

#### Dependencies
Running this software requires Java to be installed on your machine.

> [Download Java Here](https://www.oracle.com/technetwork/java/javase/downloads/index.html)

This program has been tested using Java SE 18.9 (build 11.0.1), but should also be compatible with later versions.

#### Running the Program

To run the program on a *NIX-based operating system (including MacOS), follow these steps:
1. Save the tgz file to a location that you can find later (e.g. in your home directory).
2. Open a terminal window and navigate to this location.
3. Type `tar -xvzf bbc-interview-task_ATraynor.tgz` to extract the archive file.
4. Type `cd bbc-interview-task/build/distributions/bbc-interview-task-1.0/bin` to navigate to the location of the
compiled executable.
5. Type `./bbc-interview-task` to run the program.

Upon executing the program, you will be prompted to enter a file path. This can be an absolute path or a relative one,
but must include the full file name, including its extension.

# Programming Exercise

The task is to write a program that makes http (and https) requests to specified URLs and to report on certain
properties of the responses it receives; see requirements below.

Please write your code in Java, Ruby or Python for a Linux/Unix platform. We will accept other languages but
please check with us first.

Your code should be sent to us as a zipped tar archive (.tgz file). We would like to see your version control commit
history so please include .git or .svn directory structure in the tar archive. We prefer to see small incremental
commits so the order in which your solution is developed is apparent.

Please provide instructions so that we can install, test and run your program.

If we invite you for an interview we will ask you to modify your program to meet an additional requirement or make
other improvements.

## Main Requirements

- The program is invoked from the command line and it consumes its input from stdin.
- The program output is written to stdout and errors are written to stderr.
- Input format is a newline separated list of public web addresses, such as:
  ```
  http://www.bbc.co.uk/iplayer
  https://google.com
  bad://address
  http://www.bbc.co.uk/missing/thing
  http://not.exists.bbc.co.uk/
  http://www.oracle.com/technetwork/java/javase/downloads/index.html
  https://www.pets4homes.co.uk/images/articles/1646/large/kitten-emergencies-signs-to-look-out-for537479947ec1c.jpg
  http://site.mockito.org/
  ```
- The program should make an http GET request to each valid address in its input and record particular properties
of the http response in the program output.
- The properties of interest are status code, content length and date-time of the response. These are normally
available in the http response headers.
- Output is a stream of JSON format documents that provide information about the http response for each address
in the input, such as:
  ```json
  {
   "Url": "http://www.bbc.co.uk/iplayer",
   "Status_code": 200,
   "Content_length": 209127,
   "Date": "Tue, 25 Jul 2017 17:00:55 GMT"
  }
  {
   "Url": "https://google.com",
   "Status_code": 302,
   "Content_length": 262,
   "Date": "Tue, 25 Jul 2017 17:00:55 GMT"
  }
  {
   "Url": "bad://address",
   "Error": "invalid url"
  }
  ```
- The program should identify and report invalid URLs, e.g. those that don't start with http:// or https://, or contain
characters not allowed in a URL.
- The program should cope gracefully when it makes a request to a slow or non-responsive web server, e.g. it
could time out the request after ten seconds.
- The program should have a good set of unit tests.
- It must be possible to perform a test run, consisting of all unit tests, without accessing the Internet.

## Assessment Criteria

We will assess your submission based on the following:
- The quality of instructions for installing, running and testing the program.
- How well the program meets the requirements.
- The structure and clarity of your code and tests.
- Evidence that you have taken a test driven approach during development.

## Additional Requirement
If you would like to show your skills a little more and you have sufficient time we invite you to make your program
meet the following additional requirement.

After emitting the stream of JSON documents an additional JSON document should be output. This final document
summarises all the results providing a count of responses grouped by status code. Requests that receive no response
(e.g. timeout or malformed url) may be ignored.

The summary document should be something like this (i.e. array of objects):
  ```json
  [
   {
   "Status_code": 200,
   "Number_of_responses": 15
   },
   {
   "Status_code": 404,
   "Number_of_responses": 6
   },
   {
   "Status_code": 302,
   "Number_of_responses": 5
   },
   {
   "Status_code": 403,
   "Number_of_responses": 2
   }
  ]
  ```
