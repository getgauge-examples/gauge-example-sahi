# Gauge example project in Java using [Sahi](http://sahipro.com/sahi-open-source/)
A sample project illustrating [Gauge](http://getgauge.io) features using [sahi](http://sahipro.com/sahi-open-source) to run browser tests.

## Prerequisites
- [Install Gauge](http://getgauge.io/download.html)
- [Install Gauge-Java plugin](http://getgauge.io/documentation/user/current/plugins/installation.html) by running ```gauge --install java```
- [Install Sahi open source](http://sahi.sourceforge.net/install.html)
- [Gauge Intellij plugin](http://getgauge.io/documentation/user/current/ide_support/intellij_idea.html) - To write and execute specs.

## Configuration
- Set the sahi installation path property in the **env/default/sahi_config.properties file**

````
# Path to sahi installation directory
sahi.install_dir = SAHI_INSTALLATION_PATH
````

# Setting up the System Under Test (SUT)

* Download [activeadmin-demo.war](https://bintray.com/artifact/download/gauge/activeadmin-demo/activeadmin-demo.war)
* Bring up the SUT by executing the below command
```
java -jar activeadmin-demo.war
```
* The SUT should now be available at [http://localhost:8080/](http://localhost:8080)


# Executing specs

## Command line
### All specs
````
gauge specs/
````

### Run against chrome browser
````
gauge --env chrome specs/
````
## Intellij plugin
* Right click on the spec or specs directory and select Run.
* [Lean more about the Intellij addon](http://getgauge.io/documentation/user/current/ide_support/intellij_idea.html#5-execution)

### Executing against Internet Explorer
- The [system proxy settings](http://windows.microsoft.com/en-in/windows/change-internet-explorer-proxy-server-settings#1TC=windows-7) need to be configured before executing spec on IE

````
host: localhost
port: 9999
````

# Copyright
Copyright 2015, ThoughtWorks Inc.
