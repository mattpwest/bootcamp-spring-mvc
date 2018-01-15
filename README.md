# Spring MVC Exercises

These are the practical exercises for the Spring MVC module of the Entelect Graduate
Bootcamp 2018. This will take you all the way from a simple command line application
through a basic MVC web application all the way to a simple REST API for driving
your web pages.

You have been provided with a basic Spring Boot command-line application that
contains a service which collects information about which superheroes are owned
by which publisher. The CLI application uses this service to produce a simple
numerical report, which you can see by running the CLI application.

Over the course of these exercises you will convert the existing functionality
to a web application and then begin adding new functionality.

Begin by checking out the tag `exercise1` with `git checkout tags/exercise1`.

## Exercise 1 (tag: tags/exercise1)

The Spring Boot application does not have web functionality enabled, so our
first steps are to add web capabilities and verify that they are working.

First change the packaging type of the Maven project from jar to war (a
web archive):

```
<packaging>war</packaging>
```

Add a dependency for the Spring Boot MVC starter to your `pom.xml`, this adds
all the Spring MVC classes needed to build a web application:

```
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
</dependency>
```

A Java web application needs to run in a JEE container, so normally you would
need to upload the WAR file generated when you build the application to an
application server like Tomcat to test... This is not very convenient during
development as you want to minimise the time between making a change and
testing that it works, so Spring Boot has added support for embedding 
Tomcat in the WAR and running the WAR file like a normal Java executable JAR.

To embed Tomcat add the following dependencies to your `pom.xml`:

```
<dependency>
    <groupId>org.apache.tomcat.embed</groupId>
    <artifactId>tomcat-embed-jasper</artifactId>
</dependency>

<dependency>
    <groupId>javax.servlet</groupId>
    <artifactId>jstl</artifactId>
</dependency>
```

_Note: This way of embedding Tomcat is a bit different from what you'd find in
the Spring Boot documentation - we're doing it this way to ensure that JSP
templates will work in later tutorials... One more reason why you shouldn't be
using JSP anymore, but we're teaching JSP, because it's still widely used at
our clients._

The last component you'll need is a Controller that will respond to a web
request. Controller in this case refers to the C in MVC (Model-View-
Controller design pattern). Create
`za.co.entelect.bootcamp.controllers.PublisherController`:

```
@Controller
public class PublisherController {

    @GetMapping
    @ResponseBody
    public String helloWorld() {
        return "Hello, world!";
    }
}
```

Run the application and visit `http://localhost:8080` and verify that it is
printing _"Hello, World!"_ in your browser.

If you are struggling to get this right you can have a look at
`tags/solution1` to see how we completed the task.