
<p align="center">
  

  <h3 align="center">ChaosBlade Platform</h3>

  <p align="center">
    A web based platform based on ChaosBlade to perform chaos experiments.
    <br />
  </p>
</p>



<!-- TABLE OF CONTENTS -->
## Table of Contents

* [About the Project](#about-the-project)
  * [Built With](#built-with)
* [Getting Started](#getting-started)
  * [Prerequisites](#prerequisites)
  * [Installation](#installation)
* [Usage](#usage)




<!-- ABOUT THE PROJECT -->
## About The Project

The ChaosBlade experiment management tool, including commands for creating experiments, destroying experiments, querying experiments, preparing experimental environments, revoking experimental environments, etc. Currently it provides two execution methods for performing chaos experiments; CLI and HTTP. 

The current problems of performing experiment via CLI or HTTP:

* Users must understand the commands and command parameters related to ChaosBlade.
* The user must login to the machine or execute instructions through the command channel.
* Lack of drill record summary, inconvenient for auditing

The project is intended to develop a web based chaos engineering platform to perform chaos experiments which increase the user experience and the ease of performing experiments. 

Project has following features :

* All fault scenarios of ChaosBlade can be displayed on the console, and fault scenarios can be filtered according to scenario categories;
  The scene parameters in ChaosBlade can be displayed in the console. user can configure the parameter values.
* User can manage(add, remove) target machines or clusters.
* Users can specify the target machine or cluster to perform fault injection through the web based platform.
* Users can view past experiment activity information and re perform past experiments with one click.



### Built With
* [Java](https://www.java.com/en/)
* [Spring-boot](https://spring.io/projects/spring-boot)
* [Angular](https://angular.io/)
* [Maven](https://maven.apache.org/)


<!-- GETTING STARTED -->
## Getting Started

This section guides how to set up the project.

### Prerequisites

These are the prerequisites that you need to set up the project.
* Java 1.8+
* Maven 3.6+
* npm
* Angular
* MySQL


### Installation

* Clone the repo
```sh
git clone https://github.com/rpjayasekara/chaosblade-platform.git
```
* Update database details in `record-manager/src/main/resources/application.properties`

* From the root directory run
```sh
mvn spring-boot:run
```

* CD into webapp directory and run
```sh
ng serve
```

* Visit [http://localhost:4200/](http://localhost:4200/)

<!-- USAGE EXAMPLES -->
## Usage

This section explains the main usages od the system.

* Create an Experiment

User can create a chaos experiment easily. All the fault scenarios of the ChaosBlade are shown in drop down menu and depending on what target is selected action, flag and matcher parameters are changes dynamically.
![create-experiment]
* Add host

User can add and save the host/target information. User is asked to enter the host IP, port and name to refer the host for referring the host easily when creating the experiments.
![add-host]
* View chaos experiment records

User can view his all the experiment records along with the relevant experiment parameters, host which the experiment executed and the status of the experiment.
![view-experiments]
* Destroy and re perform experiments

User can destroy active experiments and re perform past destroyed experiments with same set of experiment parameters with one click.
![destroy-experiment]
* View all hosts and remove

User can view all the added hosts and remove unwanted hosts from the database. 
![remove-host]





<!-- CONTACT -->
## Contact

Randika Jayasekara - rpjayasekara.16@cse.mrt.ac.lk







<!-- MARKDOWN LINKS & IMAGES -->
[create-experiment]: documentation/createnwew.gif
[add-host]: documentation/addhost.gif
[view-experiments]: documentation/viewrecords.gif
[destroy-experiment]: documentation/destroy.gif.gif
[remove-host]: documentation/removehost.gif
