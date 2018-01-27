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

Begin by checking out the branch `exercise1` with `git checkout exercise1`.

## Exercise 1

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

Solution branch: `solution1`.

Next exercise branch: `exercise2`.

## Exercise 2

### Part 1: Basic JSP Configuration

Web capabilities have been added to the project, now we need to use them to
produce a web version of the report that is printed out by the CLI. We will
use Java Server Pages (JSP) for view rendering.

To start have a look at the `mocks/index.html` - this is the design for the
web page provided by the business analyst - you will use this as a basis for
building your page template, by copying the contents into
`src/main/webapps/views/index.jsp`.

By default Spring Boot will look for Thymeleaf Templates in
`src/main/resources/templates`, so since we want to use JSP you need to
reconfigure Spring Boot's template engine, by creating
`src/main/resources/application.properties` with the following contents:

```
spring.mvc.view.prefix: /views/
spring.mvc.view.suffix: .jsp
```

Finally we need to make a few changes to `PublisherController.java`:

* Remove the `@ResponseBody` which tells Spring to take your return value
literally... it will now interpret your return value as a view name instead,
combining the values we configured in `application.properties` with the
returned value to create a view file path that it will use to render a
response to the user.
* Change the return value to `index`, which will result in a view name of
`views/index.jsp`.

Run the application at this point and verify that you're getting the designed
template back in the browser when hitting `http://localhost:8080/`.

### Part 2: Populating The Model

The controller needs to pass the values to render out to the view. To do this
add a Spring `ModelMap` parameter to your controller method... you can add values
you want to display in the view to this map. Then rewrite your `index.jsp` to use
the model values instead of showing static placeholder values.

Run the application at this point and verify that you're getting the designed
template back in the browser with the same values as in the command line report
`http://localhost:8080/`.

Tips:
 * You'll need to import the core taglib for JSP to gain access to `c:forEach`
   for iterating over reports: `<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>`
 * Here's an example of a JSP forEach loop:
```
<c:forEach items="${objects}" var="object">
    <h2>${object.name}</h2>
</c:forEach>
```

If you are struggling to get this right you can have a look at `tags/solution2`
to see how we completed the task.

Solution branch: `solution2`.

Next exercise branch: `exercise3`.

## Exercise 3

There's much richer data in the application than what is being exposed by the
current report. Let's use this report as an overview and then build some views
to drill into the underlying detail.

Specifically clicking on a publisher's name should navigate to
`http://localhost:8080/{publisherId}` and display a list of superheroes
belonging to that publisher.

### Part 1: PathVariables

Add a new controller method to your `PublisherController` that will handle
requests to `/{publisherId}`. Use Spring MVC's `@PathVariabe` annotation to
extract the `publisherId` as an `Integer`, then pass that into
`PublisherService.findOne(...)` to retrieve the publisher and finally
call `PublisherService.getSuperheroesByPublisher(...)` to retrieve the list
of superheroes.

Designing the JSP view for this page is left as an exercise, but the list
should show superheroes' names and gender and the title of the page should
show the name of the publisher. It would be a bonus if there's a link back
to the report to make navigation easier.

### Part 2: RequestParam

Add a new controller method to your `PublisherController` that will handle
requests to `/{publisherId}/filter?gender=Female`. Using a `@PatVariable`
as before to extract the `publisherId`. In addition use Spring MVC's
`@RequestParam` annotation to extract the gender parameter as a `Gender`
enum value.

You should extend the `PublisherService` with a method that can get a
filtered list of heroes for a publisher. Since the data set is small you
can simply use the Java 8 streams API to filter the data by gender.

You should be able to reuse the JSP template you designed for part 1 of
this exercise, but it would be nice if you extend it to detect when a
filter is being applied to show some hint to the user that they are
seeing a filtered version. An additional link for showing the unfiltered
version of the data would be nice to have.
 
Solution branch: `solution3`.
 
Next exercise branch: `exercise4`.

## Exercise 4

Our site, while useful, isn't very pretty... make it a look a bit better
by adding [Bootstrap](https://getbootstrap.com/docs/3.3/getting-started/)
stylesheets into your `webapp` folder.

Then have a look in `mocks/v2` where the designer has provided a new site
design based on Bootstrap. Convert your site to the new design by:
 * Renaming `index.jsp` to `report.jsp` and incorporating the new design.
 * Adding a new `index.jsp` with the static content for the new home page.
 * The designer forgot about `heroes.jsp`, so you should convert that look
   similar to `report.jsp` yourself.
 
***Note:**  Instead of relying on the CDN version of the Bootstrap CSS and
JS files, you should add them to your `webapp` folder, so everything is
served from your application server.*

Tips:
 * You will need a seperate controller method for the home page now, so you
   will need to start thinking about the URL structure for your pages and how
   that corresponds to your site navigation, for example:
     * / : Home Page
     * /report : Summary Report for all publishers
     * /report/{id} : Report for one publisher
     * /report/{id}/filter?{filters} : Filtered report for one publisher
 * Be sure to use absolute URIs for your CSS and image references:
   `images/banner.jpg` will work while your URL is `/`, but break once
   you navigate to `/report`, so use `/images/banner.jpg` instead.
 * You'll notice that you soon have a lot of repeating HTML in your JSP
   pages... to avoid this you can look at including fragments of JSP pages
   in other pages as an exercise in your own time (see 
   [this link](https://docs.oracle.com/javaee/5/tutorial/doc/bnajb.html)).

Solution branch: `solution4`.

Next exercise branch: `exercise5`.
   
## Exercise 5

The site has been in production for a while now and business is happy - it
has worked quite well to drive traffic to our and our affiliated comic shop
sites. One problem that has been identified though, is that whenever they
want to add a new superhero, they have to get you to make a change to the
code, so the lead time to add one is quite long...

To solve this problem they have asked you to make a couple of enhancements
to the site:
 * Add a form that can be used to add a new superhero to a publisher.
 * Add a button to delete a superhero from a publisher.

Tips:
 * You'll need to use the JSP form taglib:
 `<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>`
 * The `PublisherController` is getting a bit big, so you should probably
 put the new functionality in a separate controller...
 * Your form should validate the inputs and display errors if any required
 information is not filled in.

Solution branch: `solution5`.

Next exercise branch: `exercise6`.